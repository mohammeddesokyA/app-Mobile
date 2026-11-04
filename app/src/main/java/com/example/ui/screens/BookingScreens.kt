package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Course
import com.example.ui.components.AppAssetImage
import com.example.ui.components.PrimaryGoldButton
import com.example.ui.components.ScreenHeader
import com.example.ui.components.StarBadgeLogo
import com.example.ui.theme.*

@Composable
fun FreeTrialBookingScreen(
    course: Course,
    onBackClick: () -> Unit,
    onConfirmTrial: (childName: String, phone: String) -> Unit,
    onNavigateToFullRegistration: () -> Unit,
    modifier: Modifier = Modifier
) {
    var childName by remember { mutableStateOf("") }
    var childAge by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedCourseTitle by remember { mutableStateOf(course.title) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceWhite)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Image of smiling child with Back Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            AppAssetImage(
                assetName = "Course_Details___Booking_dfc517d3.png",
                contentDescription = "طفلة مبتسمة في الفصل",
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .size(44.dp)
                    .background(SurfaceWhite.copy(alpha = 0.9f), CircleShape)
                    .shadow(4.dp, CircleShape)
                    .testTag("trial_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "رجوع",
                    tint = DarkNavy
                )
            }
        }

        // Card Content
        Surface(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = SurfaceWhite,
            modifier = Modifier
                .offset(y = (-20).dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Drag handle
                Box(
                    modifier = Modifier
                        .width(44.dp)
                        .height(5.dp)
                        .background(Color(0xFFE2E8F0), RoundedCornerShape(3.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "حجز حصة تجريبية مجانية",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Yellow Badge
                Surface(
                    color = Color(0xFFFEF3C7),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "مدة الحصة: 30 دقيقة (مجانًا بالكامل)",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF92400E)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Outlined.AccessTime,
                            contentDescription = null,
                            tint = Color(0xFF92400E),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Course selector field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "اختر الكورس",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = CoralPink
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                        color = Color(0xFFF8FAFC),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = selectedCourseTitle,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = DarkNavy
                            )
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = null,
                                tint = CoralPink,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Child Name
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "اسم الطفل",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = CoralPink
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = childName,
                        onValueChange = { childName = it },
                        placeholder = { Text("أدخل اسم الطفل بالكامل", color = LightGray) },
                        singleLine = true,
                        trailingIcon = {
                            Icon(Icons.Outlined.Person, contentDescription = null, tint = SoftPurple)
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StarAmber,
                            unfocusedBorderColor = CardBorder,
                            focusedContainerColor = Color(0xFFF8FAFC),
                            unfocusedContainerColor = Color(0xFFF8FAFC)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("trial_child_name_input")
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Age and Phone Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Column(modifier = Modifier.weight(0.4f)) {
                        Text(
                            text = "عمر الطفل",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = CoralPink
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = childAge,
                            onValueChange = { childAge = it },
                            placeholder = { Text("7", color = LightGray) },
                            singleLine = true,
                            trailingIcon = {
                                Icon(Icons.Outlined.Cake, contentDescription = null, tint = CoralPink)
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = StarAmber,
                                unfocusedBorderColor = CardBorder,
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Column(modifier = Modifier.weight(0.6f)) {
                        Text(
                            text = "رقم الموبايل",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = CoralPink
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            placeholder = { Text("010xxxxxxxx", color = LightGray) },
                            singleLine = true,
                            trailingIcon = {
                                Icon(Icons.Outlined.Phone, contentDescription = null, tint = CoralPink)
                            },
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = StarAmber,
                                unfocusedBorderColor = CardBorder,
                                focusedContainerColor = Color(0xFFF8FAFC),
                                unfocusedContainerColor = Color(0xFFF8FAFC)
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("trial_phone_input")
                        )
                    }
                }

                Spacer(modifier = Modifier.height(28.dp))

                PrimaryGoldButton(
                    text = "تأكيد حجز الحصة التجريبية",
                    onClick = {
                        val name = if (childName.isBlank()) "طفلنا البطل" else childName
                        val p = if (phone.isBlank()) "01012345678" else phone
                        onConfirmTrial(name, p)
                    },
                    testTag = "confirm_trial_button"
                )

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = onNavigateToFullRegistration) {
                    Text(
                        text = "تريد الاشتراك مباشرة في الكورس كامل؟ اضغط هنا >",
                        color = SoftPurple,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun FullCourseRegistrationScreen(
    course: Course,
    onBackClick: () -> Unit,
    onRegistrationConfirmed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var childName by remember { mutableStateOf("") }
    var childAgeAndGrade by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("مصر") }
    var whatsappPhone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var termsAccepted by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "تسجيل البيانات والالتحاق",
                onBackClick = onBackClick
            )
        },
        containerColor = SurfaceWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            StarBadgeLogo(size = 72.dp)

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "انضم إلينا الآن",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "سعداء جداً بقرارك لانضمام طفلك إلى أكاديمية النجوم. املأ البيانات التالية لنبدأ الرحلة معاً!",
                fontSize = 13.sp,
                color = CoralPink,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Child Name
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "اسم الطفل ثلاثي", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = childName,
                    onValueChange = { childName = it },
                    placeholder = { Text("أدخل الاسم هنا...", color = LightGray) },
                    singleLine = true,
                    trailingIcon = { Icon(Icons.Outlined.Person, contentDescription = null, tint = SkyBlue) },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Country and Age/Grade row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Country
                Column(modifier = Modifier.weight(0.45f)) {
                    Text(text = "بلد الإقامة", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                    Spacer(modifier = Modifier.height(6.dp))
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                        color = SurfaceWhite,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = country, fontSize = 14.sp, color = DarkNavy, fontWeight = FontWeight.Medium)
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = SkyBlue)
                        }
                    }
                }

                // Child Age & Grade
                Column(modifier = Modifier.weight(0.55f)) {
                    Text(text = "عمر الطفل والمرحلة", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = childAgeAndGrade,
                        onValueChange = { childAgeAndGrade = it },
                        placeholder = { Text("مثلاً: 7 سنوات - الصف الأول", color = LightGray, fontSize = 11.sp) },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = StarAmber,
                            unfocusedBorderColor = CardBorder
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // WhatsApp Phone
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "رقم هاتف ولي الأمر (واتساب)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = whatsappPhone,
                    onValueChange = { whatsappPhone = it },
                    placeholder = { Text("01xxxxxxxxx", color = LightGray) },
                    singleLine = true,
                    trailingIcon = { Icon(Icons.Outlined.Phone, contentDescription = null, tint = SkyBlue) },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Email
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "البريد الإلكتروني (إن وجد)", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("example@mail.com", color = LightGray) },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Selected Course
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "اختيار الكورس", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                Spacer(modifier = Modifier.height(6.dp))
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                    color = SurfaceWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = course.title, fontSize = 13.sp, color = DarkNavy, fontWeight = FontWeight.Medium)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null, tint = SkyBlue)
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Terms Checkbox
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { termsAccepted = !termsAccepted },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = termsAccepted,
                    onCheckedChange = { termsAccepted = it },
                    colors = CheckboxDefaults.colors(checkedColor = CoralPink)
                )
                Text(
                    text = "أوافق على الشروط والأحكام الخاصة بالأكاديمية وسياسة الخصوصية المتعلقة بحماية بيانات الطلاب.",
                    fontSize = 11.sp,
                    color = TextGray,
                    lineHeight = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryGoldButton(
                text = "تأكيد التسجيل والاشتراك في الكورس",
                onClick = onRegistrationConfirmed,
                testTag = "full_registration_button"
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SessionBookingScreen(
    course: Course,
    onBackClick: () -> Unit,
    onConfirmBooking: (date: String, time: String) -> Unit,
    modifier: Modifier = Modifier
) {
    val dates = listOf(
        Pair("السبت", "11"),
        Pair("الأحد", "12"),
        Pair("الاثنين", "13"),
        Pair("الثلاثاء", "14"),
        Pair("الأربعاء", "15"),
        Pair("الخميس", "16")
    )
    var selectedDateIndex by remember { mutableIntStateOf(1) } // الأحد 12 selected in screenshot

    val times = listOf(
        "04:00 م", "04:30 م", "05:00 م",
        "05:30 م", "06:00 م", "06:30 م",
        "07:00 م", "07:30 م", "08:00 م"
    )
    var selectedTime by remember { mutableStateOf("05:00 م") }

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "حجز حصة جديدة",
                onBackClick = onBackClick
            )
        },
        containerColor = SurfaceWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // 3-step progress indicator
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.weight(1f).height(6.dp).background(Color(0xFFCBD5E1), RoundedCornerShape(3.dp)))
                Box(modifier = Modifier.weight(1f).height(6.dp).background(DarkNavy, RoundedCornerShape(3.dp)))
                Box(modifier = Modifier.weight(1f).height(6.dp).background(DarkNavy, RoundedCornerShape(3.dp)))
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Date Selection Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.CalendarMonth, contentDescription = null, tint = TextGray, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "مايو 2024", fontSize = 14.sp, color = TextGray, fontWeight = FontWeight.Medium)
                }
                Text(
                    text = "اختر التاريخ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Date Chips Row
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(dates.size) { index ->
                    val (dayName, dayNum) = dates[index]
                    val isSelected = index == selectedDateIndex
                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = if (isSelected) DarkNavy else Color(0xFFF8FAFC),
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) DarkNavy else CardBorder),
                        shadowElevation = if (isSelected) 4.dp else 0.dp,
                        modifier = Modifier
                            .width(68.dp)
                            .height(96.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .clickable { selectedDateIndex = index }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = dayName,
                                fontSize = 12.sp,
                                color = if (isSelected) SurfaceWhite.copy(alpha = 0.8f) else CoralPink,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = dayNum,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) SurfaceWhite else DarkNavy
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Time Selection Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.AccessTime, contentDescription = null, tint = CoralPink, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "توقيت القاهرة", fontSize = 13.sp, color = CoralPink, fontWeight = FontWeight.Medium)
                }
                Text(
                    text = "اختر الوقت",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 3x3 Time Grid
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                times.chunked(3).forEach { rowTimes ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowTimes.forEach { timeSlot ->
                            val isSelected = timeSlot == selectedTime
                            Surface(
                                shape = RoundedCornerShape(18.dp),
                                color = if (isSelected) StarYellow else Color(0xFFF8FAFC),
                                border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) StarYellowDark else CardBorder),
                                shadowElevation = if (isSelected) 3.dp else 0.dp,
                                modifier = Modifier
                                    .weight(1f)
                                    .height(54.dp)
                                    .clip(RoundedCornerShape(18.dp))
                                    .clickable { selectedTime = timeSlot }
                                    .testTag("time_slot_${timeSlot}")
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Text(
                                        text = timeSlot,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkNavy
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            PrimaryGoldButton(
                text = "تأكيد الحجز الآن",
                trailingIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onClick = {
                    val dateStr = "${dates[selectedDateIndex].first} ${dates[selectedDateIndex].second} مايو"
                    onConfirmBooking(dateStr, selectedTime)
                },
                testTag = "confirm_session_booking_button"
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
