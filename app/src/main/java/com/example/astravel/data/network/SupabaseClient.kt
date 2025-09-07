package com.example.astravel.data.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseUrl = "https://lsntuzjiyhmcduaskptt.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImxzbnR1emppeWhtY2R1YXNrcHR0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTY0Nzk3MzMsImV4cCI6MjA3MjA1NTczM30.Kna7gFEyzpDmV67DlAlV3WGL88MsGuvqUnflvJ1gjQw"
    ) {
        install(Postgrest)
        install(Auth)
        install(Realtime)
    }
}