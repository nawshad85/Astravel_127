package com.example.astravel

//Column(
//modifier = Modifier
//.fillMaxWidth()
//.verticalScroll(rememberScrollState())
//.imePadding()
//.padding(top = 72.dp)
//) {
//    // Cox's Bazar card
//    Box(
//        modifier = Modifier
//            .height(200.dp)
//            .fillMaxWidth()
//            .padding(start = 20.dp, top = 20.dp, bottom = 10.dp, end = 20.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(Color.Gray, shape = RoundedCornerShape(16.dp))
//            .clickable { navController.navigate("coxs") }
//    ) {
//        AsyncImage(
//            model = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=1200&q=60",
//            contentDescription = "Cox's Bazar",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomStart)
//                .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
//        ) {
//            Text(
//                "Cox's Bazar",
//                fontFamily = PoppinsLight,
//                fontSize = 16.sp,
//                color = Color.White,
//                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
//            )
//        }
//    }
//
//    // Kuakata card
//    Box(
//        modifier = Modifier
//            .height(200.dp)
//            .fillMaxWidth()
//            .padding(start = 20.dp, top = 10.dp, bottom = 20.dp, end = 20.dp)
//            .clip(RoundedCornerShape(16.dp))
//            .background(Color.Gray, shape = RoundedCornerShape(16.dp))
//            .clickable { navController.navigate("kuakata") }
//    ) {
//        AsyncImage(
//            model = "https://images.unsplash.com/photo-1518837695005-2083093ee35b?auto=format&fit=crop&w=1200&q=60",
//            contentDescription = "Kuakata",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomStart)
//                .background(Color(0x80000000), shape = RoundedCornerShape(topEnd = 12.dp))
//        ) {
//            Text(
//                "Kuakata",
//                fontFamily = PoppinsLight,
//                fontSize = 16.sp,
//                color = Color.White,
//                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
//            )
//        }
//    }
//}