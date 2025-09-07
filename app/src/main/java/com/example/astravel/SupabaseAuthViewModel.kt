package com.example.astravel

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astravel.data.model.UserState
import com.example.astravel.data.network.SupabaseClient.client
import com.example.astravel.utils.SharedPreferenceHelper
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class SupabaseAuthViewModel : ViewModel(){
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    var currentEmail by mutableStateOf<String?>(null)
        private set
    init {
        currentEmail = client.auth.currentUserOrNull()?.email
    }

    // Profile editable fields
    var firstName by mutableStateOf("")
        private set
    var lastName by mutableStateOf("")
        private set
    var phone by mutableStateOf("")
        private set

    var profileLoading by mutableStateOf(false)
        private set
    var profileMessage by mutableStateOf<String?>(null)
        private set

    @Serializable
    data class ProfileData(
        val email: String,
        val first_name: String? = null,
        val last_name: String? = null,
        val phone: String? = null
    )

    fun onFirstNameChange(v: String){ firstName = v }
    fun onLastNameChange(v: String){ lastName = v }
    fun onPhoneChange(v: String){ phone = v }

    fun signUp(
        context: Context,
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                client.auth.signUpWith(Email){
                    email = userEmail
                    password = userPassword
                }
                // refresh and grab email
                currentEmail = client.auth.currentUserOrNull()?.email
                saveToken(context)
                _userState.value = UserState.Success("Sign Up Successful")
            }
            catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(context: Context){
        val accessToken = client.auth.currentAccessTokenOrNull()
        val sharedPref = SharedPreferenceHelper(context)
        sharedPref.saveStringData("access_token", accessToken)
    }


    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("access_token")
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                client.auth.signInWith(Email){
                    email = userEmail
                    password = userPassword
                }
                currentEmail = client.auth.currentUserOrNull()?.email
                saveToken(context)
                _userState.value = UserState.Success("Login Successful")
            }
            catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun resetProfileFields(){
        firstName = ""
        lastName = ""
        phone = ""
        profileMessage = null
        profileLoading = false
    }

    fun logout(context: Context){
        val sharedPref = SharedPreferenceHelper(context)
        viewModelScope.launch {
            _userState.value = UserState.Loading
            try {
                client.auth.signOut()
                currentEmail = null
                resetProfileFields()
                sharedPref.clearPreferences()
                _userState.value = UserState.Success("Logout Successful")
            }
            catch (e: Exception){
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun isUserLoggedIn(context: Context){
        viewModelScope.launch {
            try {
                val token = getToken(context)
                if(token.isNullOrEmpty()){
                    currentEmail = null
                    _userState.value = UserState.Error("User not logged in")
                } else {
                    val user = client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    currentEmail = user.email
                    saveToken(context)
                    _userState.value = UserState.Success("User is logged in")
                }
            } catch (e: Exception){
                currentEmail = null
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun loadProfile(){
        val email = currentEmail ?: return
        viewModelScope.launch {
            profileLoading = true
            profileMessage = null
            try {
                val list = client.postgrest["profiles"].select {
                    filter { eq("email", email) }
                    limit(1)
                }.decodeList<ProfileData>()
                val p = list.firstOrNull()
                firstName = p?.first_name ?: ""
                lastName = p?.last_name ?: ""
                phone = p?.phone ?: ""
                profileMessage = null
            } catch (e: Exception){
                profileMessage = "Failed to load profile: ${e.message}";
            } finally { profileLoading = false }
        }
    }

    fun saveProfile(){
        val email = currentEmail ?: return
        viewModelScope.launch {
            profileLoading = true
            profileMessage = null
            try {
                val payload = ProfileData(
                    email = email,
                    first_name = firstName.ifBlank { null },
                    last_name = lastName.ifBlank { null },
                    phone = phone.ifBlank { null }
                )
                client.postgrest["profiles"].upsert(payload){
                    onConflict = "email"
                }
                profileMessage = "Profile updated"
            } catch (e: Exception){
                profileMessage = "Update failed: ${e.message}";
            } finally { profileLoading = false }
        }
    }
}