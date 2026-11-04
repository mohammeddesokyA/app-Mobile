package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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
import com.example.data.model.*
import com.example.ui.NavigationTab
import com.example.ui.components.*
import com.example.ui.theme.*

@Composable
fun ParentDashboardScreen(
    children: List<ChildProgress>,
    badges: List<BadgeAward>,
    upcomingClasses: List<UpcomingClass>,
    currentTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    onJoinLiveClass: () -> Unit,
    onAddChildClick: () -> Unit,
    onSwitchToStudentProfile: () -> Unit,
    onTeacherProfileClick: () -> Unit,
    onOpenAssignments: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            JuniorBottomNavigationBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected
            )
        },
        containerColor = BackgroundOffWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
        ) {
            // Parent Header Card
            item {
                Surface(
                    shape = RoundedCornerShape(26.dp),
                    color = SurfaceWhite,
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        // Action Icons Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = onSwitchToStudentProfile,
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(Icons.Outlined.Settings, contentDescription = "الإعدادات", tint = TextGray)
                                }
                                IconButton(
                                    onClick = { },
                                    modifier = Modifier.size(36.dp)
                                ) {
                                    Icon(Icons.Outlined.Notifications, contentDescription = "الإشعارات", tint = CoralPink)
                                }
                            }

                            Text(
                                text = "لوحة التحكم",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // Parent Profile Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Points Badge
                            Surface(
                                color = Color(0xFFFEF3C7),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                ) {
                                    Text(
                                        text = "2,450",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFB45309)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = StarAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }

                            // Info and Avatar
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(
                                        text = "أحمد محمد",
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkNavy
                                    )
                                    Text(
                                        text = "أب لـ مريم وياسين",
                                        fontSize = 12.sp,
                                        color = CoralPink
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Box(
                                    modifier = Modifier
                                        .size(54.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFE2E8F0)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AppAssetImage(
                                        assetName = "Parent_Dashboard_93c96d38.png",
                                        contentDescription = "صورة ولي الأمر",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Section: Children Progress
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onAddChildClick) {
                        Text(
                            text = "إضافة طفل",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = StarAmber
                        )
                    }
                    Text(
                        text = "تقدم الأطفال",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Children Cards
            items(children) { child ->
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = SurfaceWhite,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = onSwitchToStudentProfile,
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color(0xFFF1F5F9), CircleShape)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "عرض الملف",
                                tint = LightGray,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f).padding(horizontal = 14.dp)
                        ) {
                            Text(
                                text = child.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${child.courseName} - ${child.levelText}",
                                fontSize = 12.sp,
                                color = CoralPink
                            )
                        }

                        CircularProgressBadge(
                            percent = child.progressPercent,
                            color = Color(child.colorHex)
                        )
                    }
                }
            }

            // Badges Section
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "الأوسمة والجوائز",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    badges.forEach { badge ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(62.dp)
                                    .background(Color(badge.bgHex), CircleShape)
                                    .shadow(2.dp, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = badge.emoji, fontSize = 26.sp)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = badge.title,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }

            // Upcoming Sessions Section
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onOpenAssignments) {
                        Text(
                            text = "الواجبات",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = StarAmber
                        )
                    }
                    Text(
                        text = "الحصص القادمة",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // Upcoming Classes List
            items(upcomingClasses) { upClass ->
                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = SurfaceWhite,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (upClass.hasJoinButton) {
                            Button(
                                onClick = onJoinLiveClass,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFFEF08A),
                                    contentColor = DarkNavy
                                ),
                                shape = RoundedCornerShape(14.dp),
                                modifier = Modifier.testTag("join_live_session_button")
                            ) {
                                Text(
                                    text = "دخول",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            Surface(
                                color = Color(0xFFF1F5F9),
                                shape = RoundedCornerShape(14.dp),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(14.dp))
                                    .clickable(onClick = onTeacherProfileClick)
                            ) {
                                Text(
                                    text = "تفاصيل",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextGray,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f).padding(horizontal = 14.dp)
                        ) {
                            Text(
                                text = upClass.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "${upClass.timeText} • مع ${upClass.teacherName}",
                                fontSize = 12.sp,
                                color = CoralPink
                            )
                        }

                        // Date badge
                        Surface(
                            color = Color(0xFFF0FDF4),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.size(52.dp)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = upClass.dateText.split(" ").firstOrNull() ?: "12",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MintGreen
                                )
                                Text(
                                    text = upClass.dateText.split(" ").getOrNull(1) ?: "مايو",
                                    fontSize = 10.sp,
                                    color = MintGreen
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StudentProfileScreen(
    student: StudentProfile,
    enrolledCourses: List<EnrolledCourse>,
    todaySchedule: List<ScheduleItem>,
    currentTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    onJoinLiveClass: () -> Unit,
    onOpenParentDashboard: () -> Unit,
    onOpenAssignments: () -> Unit,
    onOpenChat: () -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    var notificationsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            JuniorBottomNavigationBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected
            )
        },
        containerColor = BackgroundOffWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onOpenParentDashboard,
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color(0xFFF1F5F9), CircleShape)
                    ) {
                        Icon(Icons.Default.Dashboard, contentDescription = "لوحة ولي الأمر", tint = DarkNavy)
                    }

                    Text(
                        text = "حسابي",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
            }

            // Student Profile Card
            item {
                Surface(
                    shape = RoundedCornerShape(26.dp),
                    color = SurfaceWhite,
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Avatar and Level
                        Box(
                            modifier = Modifier
                                .size(84.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFEF3C7)),
                            contentAlignment = Alignment.Center
                        ) {
                            AppAssetImage(
                                assetName = student.avatarAsset,
                                contentDescription = student.name,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = student.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkNavy
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Surface(
                            color = Color(0xFFFEF3C7),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "⭐ ${student.levelTitle}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB45309),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Stats Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "${student.activeCoursesCount}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                                Text(text = "كورسات نشطة", fontSize = 11.sp, color = TextGray)
                            }
                            Box(modifier = Modifier.width(1.dp).height(30.dp).background(CardBorder))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "${student.certificatesCount}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                                Text(text = "شهادات", fontSize = 11.sp, color = TextGray)
                            }
                            Box(modifier = Modifier.width(1.dp).height(30.dp).background(CardBorder))
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "${student.points}", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                                Text(text = "نقطة نجوم", fontSize = 11.sp, color = MintGreen)
                            }
                        }
                    }
                }
            }

            // Section: Current Courses
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { }) {
                        Text(text = "الكل", fontSize = 13.sp, color = MintGreen, fontWeight = FontWeight.Bold)
                    }
                    Text(
                        text = "كورساتي الحالية",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    enrolledCourses.forEach { c ->
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = SurfaceWhite,
                            shadowElevation = 1.dp,
                            modifier = Modifier
                                .weight(1f)
                                .height(140.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(14.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .background(Color(c.colorHex).copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MenuBook,
                                        contentDescription = null,
                                        tint = Color(c.colorHex),
                                        modifier = Modifier.size(22.dp)
                                    )
                                }

                                Text(
                                    text = c.title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkNavy,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = "متبقي ${c.remainingSessions} حصص",
                                    fontSize = 11.sp,
                                    color = MintGreen
                                )

                                LinearProgressIndicator(
                                    progress = { c.progressPercent / 100f },
                                    color = Color(c.colorHex),
                                    trackColor = Color(c.colorHex).copy(alpha = 0.2f),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(6.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                )
                            }
                        }
                    }
                }
            }

            // Section: Today's Schedule
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "جدول اليوم",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(todaySchedule) { item ->
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = SurfaceWhite,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (item.isLive) {
                            Button(
                                onClick = onJoinLiveClass,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MintGreen,
                                    contentColor = SurfaceWhite
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(text = "دخول", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        } else {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MintGreen, modifier = Modifier.size(24.dp))
                        }

                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.weight(1f).padding(horizontal = 14.dp)
                        ) {
                            Text(text = item.title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                            Text(text = item.teacherName, fontSize = 12.sp, color = TextGray)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = item.timeText, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = DarkNavy)
                            Text(text = item.periodText, fontSize = 11.sp, color = TextGray)
                        }
                    }
                }
            }

            // Quick Actions & Settings List
            item {
                Spacer(modifier = Modifier.height(24.dp))

                Surface(
                    shape = RoundedCornerShape(22.dp),
                    color = SurfaceWhite,
                    shadowElevation = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
                        // Homework
                        SettingsRow(
                            title = "الواجبات المدرسية",
                            icon = Icons.Outlined.Assignment,
                            iconTint = SoftPurple,
                            onClick = onOpenAssignments
                        )
                        Divider(color = CardBorder, thickness = 0.8.dp)

                        // Teacher & Support Chat
                        SettingsRow(
                            title = "محادثة المعلمة والدعم",
                            icon = Icons.Outlined.Chat,
                            iconTint = SkyBlue,
                            onClick = onOpenChat
                        )
                        Divider(color = CardBorder, thickness = 0.8.dp)

                        // Notifications Toggle
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Switch(
                                checked = notificationsEnabled,
                                onCheckedChange = { notificationsEnabled = it },
                                colors = SwitchDefaults.colors(checkedThumbColor = MintGreen, checkedTrackColor = MintGreen.copy(alpha = 0.3f))
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "تنبيهات الحصص", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = DarkNavy)
                                Spacer(modifier = Modifier.width(12.dp))
                                Box(
                                    modifier = Modifier.size(38.dp).background(Color(0xFFE8F7F0), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Notifications, contentDescription = null, tint = MintGreen, modifier = Modifier.size(20.dp))
                                }
                            }
                        }
                        Divider(color = CardBorder, thickness = 0.8.dp)

                        // Logout
                        SettingsRow(
                            title = "تسجيل الخروج",
                            icon = Icons.Default.ExitToApp,
                            iconTint = Color(0xFFEF4444),
                            textColor = Color(0xFFEF4444),
                            onClick = onLogout
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TeacherProfileScreen(
    teacher: TeacherInfo,
    onBackClick: () -> Unit,
    onBookAppointment: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .shadow(12.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                color = SurfaceWhite,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    PrimaryGoldButton(
                        text = "احجز موعد مع المعلمة",
                        onClick = onBookAppointment,
                        testTag = "book_teacher_appointment_button"
                    )
                }
            }
        },
        containerColor = BackgroundOffWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Hero Photo of Teacher
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            ) {
                AppAssetImage(
                    assetName = teacher.assetImage,
                    contentDescription = teacher.name,
                    modifier = Modifier.fillMaxSize()
                )

                // Top Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { },
                        modifier = Modifier
                            .size(44.dp)
                            .background(SurfaceWhite.copy(alpha = 0.9f), CircleShape)
                            .shadow(4.dp, CircleShape)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = "مشاركة", tint = DarkNavy)
                    }

                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(44.dp)
                            .background(SurfaceWhite.copy(alpha = 0.9f), CircleShape)
                            .shadow(4.dp, CircleShape)
                    ) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع", tint = DarkNavy)
                    }
                }
            }

            // Info Card
            Surface(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = SurfaceWhite,
                shadowElevation = 4.dp,
                modifier = Modifier
                    .offset(y = (-24).dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Badge
                    Surface(
                        color = Color(0xFFE8F7F0),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Text(
                            text = teacher.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = MintGreen,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = teacher.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkNavy
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = teacher.specialization,
                        fontSize = 14.sp,
                        color = TextGray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Stats Cards Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TeacherStatBox(title = "سنوات خبرة", value = teacher.experienceYears, modifier = Modifier.weight(1f))
                        TeacherStatBox(title = "تقييم عام", value = "${teacher.rating}", modifier = Modifier.weight(1f), isGold = true)
                        TeacherStatBox(title = "طالب", value = teacher.studentsCount, modifier = Modifier.weight(1f))
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Bio Section
                    Text(
                        text = "نبذة عن المعلمة",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Surface(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = teacher.bio,
                            fontSize = 14.sp,
                            color = TextGray,
                            lineHeight = 22.sp,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun TeacherStatBox(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    isGold: Boolean = false
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = SurfaceWhite,
        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
        shadowElevation = 1.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isGold) StarAmber else DarkNavy
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 11.sp,
                color = TextGray
            )
        }
    }
}

@Composable
private fun SettingsRow(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconTint: Color,
    textColor: Color = DarkNavy,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = null,
            tint = LightGray,
            modifier = Modifier.size(18.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .background(iconTint.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}
