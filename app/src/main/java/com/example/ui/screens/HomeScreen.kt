package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Course
import com.example.data.model.SubjectCategory
import com.example.ui.NavigationTab
import com.example.ui.UserRole
import com.example.ui.components.AppAssetImage
import com.example.ui.components.JuniorBottomNavigationBar
import com.example.ui.theme.*

@Composable
fun HomeScreen(
    categories: List<SubjectCategory>,
    courses: List<Course>,
    currentTab: NavigationTab,
    userRole: UserRole = UserRole.PARENT,
    onTabSelected: (NavigationTab) -> Unit,
    onCourseClick: (String) -> Unit,
    onCategoryClick: (String) -> Unit,
    onBookTrialClick: () -> Unit,
    onProfileClick: () -> Unit,
    onViewAllCourses: () -> Unit,
    onToggleRole: () -> Unit = {},
    onJoinLiveNow: () -> Unit = {},
    onOpenAssignments: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            JuniorBottomNavigationBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected
            )
        },
        modifier = modifier.fillMaxSize(),
        containerColor = BackgroundOffWhite
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 24.dp)
        ) {
            // Top App Bar
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Right: Logo + App Name
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .background(Color(0xFFEBF5FF), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.School,
                                contentDescription = null,
                                tint = SoftPurple,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "أكاديمية النجوم الصغار",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                            Text(
                                text = if (userRole == UserRole.PARENT) "بوابة أولياء الأمور" else "بوابة البطل الصغير",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = if (userRole == UserRole.PARENT) StarAmber else SoftPurple
                            )
                        }
                    }

                    // Left: Account Chip Button
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                        color = Color(0xFFF8FAFC),
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable(onClick = onProfileClick)
                            .testTag("home_profile_chip")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Icon(
                                imageVector = if (userRole == UserRole.PARENT) Icons.Outlined.SupervisorAccount else Icons.Outlined.Face,
                                contentDescription = "حسابي",
                                tint = if (userRole == UserRole.PARENT) StarAmber else SoftPurple,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (userRole == UserRole.PARENT) "أحمد (ولي أمر)" else "مريم (طالبة) ⭐",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                        }
                    }
                }

                // Quick Switch Role Banner
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (userRole == UserRole.PARENT) Color(0xFFF3EDFF) else Color(0xFFFEF9C3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(onClick = onToggleRole)
                        .testTag("toggle_role_banner")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.SwapHoriz,
                                contentDescription = null,
                                tint = if (userRole == UserRole.PARENT) SoftPurple else Color(0xFF92400E),
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (userRole == UserRole.PARENT)
                                    "أنت الآن بحساب ولي الأمر • انقر للتبديل لحساب الطالب (مريم)"
                                else
                                    "أنتِ الآن بحساب الطالبة مريم • انقري للتبديل لحساب ولي الأمر",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (userRole == UserRole.PARENT) SoftPurple else Color(0xFF92400E)
                            )
                        }
                        Text(
                            text = "تبديل 🔄",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (userRole == UserRole.PARENT) SoftPurple else Color(0xFF92400E)
                        )
                    }
                }
            }

            // Role-Specific Snapshot Section
            item {
                if (userRole == UserRole.PARENT) {
                    // Parent Snapshot Card
                    Surface(
                        shape = RoundedCornerShape(22.dp),
                        color = SurfaceWhite,
                        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                        shadowElevation = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(onClick = onProfileClick) {
                                    Text(text = "لوحة التحكم كاملة ←", fontSize = 12.sp, color = StarAmber, fontWeight = FontWeight.Bold)
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "متابعة تقدم أبنائك",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkNavy
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(Icons.Outlined.FamilyRestroom, contentDescription = null, tint = StarAmber, modifier = Modifier.size(18.dp))
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            // Child 1
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "75%", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = StarAmber)
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(text = "مريم علي - نور البيان", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                                    Text(text = "الحصة القادمة: 12 مايو (05:00 م)", fontSize = 11.sp, color = TextGray)
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { 0.75f },
                                color = StarAmber,
                                trackColor = Color(0xFFFEF3C7),
                                modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            // Child 2
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "40%", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = SkyBlue)
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(text = "ياسين علي - أساسيات العلوم", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                                    Text(text = "الحصة القادمة: 14 مايو (07:00 م)", fontSize = 11.sp, color = TextGray)
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { 0.40f },
                                color = SkyBlue,
                                trackColor = Color(0xFFEAF8FF),
                                modifier = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp))
                            )
                        }
                    }
                } else {
                    // Student Snapshot Card
                    Surface(
                        shape = RoundedCornerShape(22.dp),
                        color = Color(0xFFFBF8FF),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE9D5FF)),
                        shadowElevation = 1.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    color = Color(0xFFFEF3C7),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text(
                                        text = "⭐ 850 نقطة",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFB45309),
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    )
                                }
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "مهامك يا بطلتنا مريم",
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkNavy
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(text = "👧", fontSize = 18.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Button(
                                    onClick = onJoinLiveNow,
                                    colors = ButtonDefaults.buttonColors(containerColor = MintGreen),
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(text = "حصة الرسم الآن 🔴", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                                }
                                OutlinedButton(
                                    onClick = onOpenAssignments,
                                    shape = RoundedCornerShape(14.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(text = "واجب القراءة 📝", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = SoftPurple)
                                }
                            }
                        }
                    }
                }
            }

            // Hero Banner
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .shadow(12.dp, RoundedCornerShape(32.dp), spotColor = CoralPink.copy(alpha = 0.3f))
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFF5E8A),
                                    Color(0xFFFF85A6)
                                )
                            ),
                            shape = RoundedCornerShape(32.dp)
                        )
                        .clip(RoundedCornerShape(32.dp))
                        .padding(20.dp)
                ) {
                    // Chat bubble top-left
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(46.dp)
                            .background(Color(0xFFFFF0B8), CircleShape)
                            .shadow(4.dp, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ChatBubbleOutline,
                            contentDescription = "محادثة",
                            tint = DarkNavy,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // Text Content
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.62f)
                            .align(Alignment.TopEnd),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.End
                    ) {
                        // Tag
                        Surface(
                            color = SurfaceWhite.copy(alpha = 0.25f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "مرحباً بك في عالمنا ✨",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = SurfaceWhite,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        // Big Title
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "تعليم ممتع\nلأطفالنا النوابغ",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = SurfaceWhite,
                                lineHeight = 28.sp
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "نكتشف المواهب ونبني المستقبل في بيئة آمنة ومحفزة لكل طفل.",
                                fontSize = 12.sp,
                                color = SurfaceWhite.copy(alpha = 0.9f),
                                lineHeight = 16.sp
                            )
                        }

                        // Trial booking button
                        Button(
                            onClick = onBookTrialClick,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFF1B8),
                                contentColor = DarkNavy
                            ),
                            shape = RoundedCornerShape(18.dp),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                            modifier = Modifier.testTag("home_trial_button")
                        ) {
                            Text(
                                text = "احجز تجريبي الآن",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                        }
                    }

                    // Graphic image of kid on star
                    Box(
                        modifier = Modifier
                            .size(160.dp)
                            .align(Alignment.BottomStart)
                    ) {
                        AppAssetImage(
                            assetName = "Home_Screen_a5503bb9.png",
                            contentDescription = "طفل يبتسم على نجمة",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(20.dp))
                        )
                    }
                }
            }

            // Categories Section Header
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "التصنيفات",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                    TextButton(onClick = onViewAllCourses) {
                        Text(
                            text = "عرض الكل",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextGray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // 4 Categories Horizontal Row
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    categories.forEach { category ->
                        CategoryCard(
                            category = category,
                            onClick = { onCategoryClick(category.id) }
                        )
                    }
                }
            }

            // Featured Courses List
            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Quran Course Card
            item {
                HomeCourseCard(
                    title = "تحفيظ القرآن الكريم",
                    subtitle = "تعليم التجويد والحفظ بطرق مبسطة",
                    durationText = "45 دقيقة",
                    ageText = "4-12 سنة",
                    iconColor = StarYellowDark,
                    bgColor = Color(0xFFFEF7E6),
                    onClick = { onCourseClick("quran_kids") }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Languages Course Card
            item {
                HomeCourseCard(
                    title = "اللغات الأجنبية",
                    subtitle = "الإنجليزية والفرنسية بأسلوب تفاعلي",
                    durationText = "60 دقيقة",
                    ageText = "6-15 سنة",
                    iconColor = Color(0xFF818CF8),
                    bgColor = Color(0xFFEEF2FF),
                    onClick = { onCourseClick("english_phonics") }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Science Course Card
            item {
                HomeCourseCard(
                    title = "العلوم",
                    subtitle = "تجارب علمية مذهلة للعقول الصغيرة",
                    durationText = "60 دقيقة",
                    ageText = "8-14 سنة",
                    iconColor = MintGreen,
                    bgColor = Color(0xFFE8F7F0),
                    onClick = { onCourseClick("science_explorer") }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Programming Course Card
            item {
                HomeCourseCard(
                    title = "البرمجة والذكاء الاصطناعي",
                    subtitle = "تطوير ألعاب سكراتش وتفكير منطقي",
                    durationText = "60 دقيقة",
                    ageText = "5-14 سنة",
                    iconColor = SoftPurple,
                    bgColor = Color(0xFFF3EDFF),
                    onClick = { onCourseClick("programming_scratch") }
                )
            }
        }
    }
}

@Composable
private fun CategoryCard(
    category: SubjectCategory,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onClick)
            .padding(4.dp)
            .testTag("category_${category.id}")
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(Color(category.bgHex), RoundedCornerShape(20.dp))
                .border(1.dp, Color(category.bgHex), RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            when (category.iconType) {
                "book" -> Icon(Icons.Default.MenuBook, contentDescription = null, tint = Color(category.colorHex), modifier = Modifier.size(32.dp))
                "translate" -> Icon(Icons.Default.Translate, contentDescription = null, tint = Color(category.colorHex), modifier = Modifier.size(32.dp))
                "atom" -> Icon(Icons.Default.Science, contentDescription = null, tint = Color(category.colorHex), modifier = Modifier.size(32.dp))
                "code" -> Icon(Icons.Default.Code, contentDescription = null, tint = Color(category.colorHex), modifier = Modifier.size(32.dp))
                else -> Icon(Icons.Default.Star, contentDescription = null, tint = Color(category.colorHex), modifier = Modifier.size(32.dp))
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = category.title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )
    }
}

@Composable
private fun HomeCourseCard(
    title: String,
    subtitle: String,
    durationText: String,
    ageText: String,
    iconColor: Color,
    bgColor: Color,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = SurfaceWhite,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .testTag("home_card_${title}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Calendar action indicator
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFFFFBEB), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null,
                    tint = StarYellowDark,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Title and Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 14.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextGray
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = durationText,
                            fontSize = 11.sp,
                            color = TextGray,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = ageText,
                            fontSize = 11.sp,
                            color = TextGray,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                        )
                    }
                }
            }

            // Right category symbol badge
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(bgColor, RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PauseCircle,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
