package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.*
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    JuniorStarsApp()
                }
            }
        }
    }
}

@Composable
fun JuniorStarsApp(
    viewModel: JuniorStarsViewModel = viewModel()
) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val selectedTab by viewModel.selectedTab.collectAsState()
    val currentUserRole by viewModel.currentUserRole.collectAsState()
    val userMessage by viewModel.userMessage.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(userMessage) {
        userMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearMessage()
        }
    }

    // Hardware and gesture Back button handling
    BackHandler(enabled = currentScreen !is Screen.Home && currentScreen !is Screen.Splash && currentScreen !is Screen.Welcome) {
        val handled = viewModel.navigateBack()
        if (!handled) {
            viewModel.navigateTo(Screen.Home)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp)
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedContent(
                targetState = currentScreen,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "screen_transition"
            ) { screen ->
                when (screen) {
                    is Screen.Splash -> {
                        SplashScreen(
                            onNavigateNext = { viewModel.navigateTo(Screen.Welcome) }
                        )
                    }

                    is Screen.Welcome -> {
                        WelcomeScreen(
                            onNavigateToLogin = { isParent ->
                                viewModel.setUserRole(if (isParent) UserRole.PARENT else UserRole.STUDENT)
                                viewModel.navigateTo(Screen.Login)
                            },
                            onNavigateToSignUp = { isParent ->
                                viewModel.setUserRole(if (isParent) UserRole.PARENT else UserRole.STUDENT)
                                viewModel.navigateTo(Screen.SignUp)
                            },
                            onNavigateToHomeAsGuest = {
                                viewModel.navigateTo(Screen.Home)
                            }
                        )
                    }

                    is Screen.Login -> {
                        LoginScreen(
                            initialRole = currentUserRole,
                            onLoginSuccess = { role ->
                                viewModel.setUserRole(role)
                                viewModel.showMessage(
                                    if (role == UserRole.PARENT)
                                        "مرحباً بك يا أحمد! تم تسجيل الدخول بحساب ولي الأمر 👨‍👧"
                                    else
                                        "أهلاً بك يا بطلتنا مريم! تم تسجيل الدخول بحساب الطالب 🌟"
                                )
                                viewModel.navigateTo(Screen.Home)
                            },
                            onNavigateToSignUp = { viewModel.navigateTo(Screen.SignUp) },
                            onNavigateToForgotPassword = { viewModel.navigateTo(Screen.ForgotPassword) },
                            onBackClick = { viewModel.navigateTo(Screen.Welcome) },
                            onNavigateToGuest = { viewModel.navigateTo(Screen.Home) }
                        )
                    }

                    is Screen.SignUp -> {
                        SignUpScreen(
                            onSignUpSuccess = {
                                viewModel.showMessage("تم إنشاء الحساب بنجاح! مرحباً بك في أكاديمية النجوم الصغار")
                                viewModel.navigateTo(Screen.Home)
                            },
                            onNavigateToLogin = { viewModel.navigateTo(Screen.Login) }
                        )
                    }

                    is Screen.ForgotPassword -> {
                        ForgotPasswordScreen(
                            onSendOtp = { viewModel.navigateTo(Screen.OtpVerification) },
                            onNavigateToLogin = { viewModel.navigateTo(Screen.Login) },
                            onBackClick = { viewModel.navigateTo(Screen.Login) }
                        )
                    }

                    is Screen.OtpVerification -> {
                        OtpVerificationScreen(
                            onVerificationSuccess = {
                                viewModel.showMessage("تم التحقق من الرمز بنجاح! يرجى تعيين كلمة سر جديدة")
                                viewModel.navigateTo(Screen.NewPassword)
                            },
                            onContactSupport = { viewModel.navigateTo(Screen.ChatSupport) },
                            onBackClick = { viewModel.navigateTo(Screen.ForgotPassword) }
                        )
                    }

                    is Screen.NewPassword -> {
                        NewPasswordScreen(
                            onPasswordResetSuccess = {
                                viewModel.showMessage("تم تغيير كلمة السر بنجاح! يمكنك الآن تسجيل الدخول بكلمة السر الجديدة")
                                viewModel.navigateTo(Screen.Login)
                            },
                            onBackClick = { viewModel.navigateTo(Screen.Login) }
                        )
                    }

                    is Screen.Home -> {
                        HomeScreen(
                            categories = viewModel.homeCategories,
                            courses = viewModel.courses,
                            currentTab = selectedTab,
                            userRole = currentUserRole,
                            onTabSelected = { tab -> viewModel.selectTab(tab) },
                            onCourseClick = { courseId -> viewModel.navigateTo(Screen.CourseDetails(courseId)) },
                            onCategoryClick = { _ -> viewModel.navigateTo(Screen.AllCourses) },
                            onBookTrialClick = { viewModel.navigateTo(Screen.FreeTrialBooking(viewModel.courses.first().id)) },
                            onProfileClick = {
                                if (currentUserRole == UserRole.PARENT) {
                                    viewModel.navigateTo(Screen.ParentDashboard)
                                } else {
                                    viewModel.navigateTo(Screen.StudentProfile)
                                }
                            },
                            onViewAllCourses = { viewModel.navigateTo(Screen.AllCourses) },
                            onToggleRole = { viewModel.toggleUserRole() },
                            onJoinLiveNow = { viewModel.navigateTo(Screen.LiveSession) },
                            onOpenAssignments = { viewModel.navigateTo(Screen.Assignments) }
                        )
                    }

                    is Screen.AllCourses -> {
                        AllCoursesScreen(
                            subjects = viewModel.allSubjects,
                            currentTab = selectedTab,
                            onTabSelected = { tab -> viewModel.selectTab(tab) },
                            onBackClick = { viewModel.navigateBack() },
                            onSubjectClick = { _ -> viewModel.navigateTo(Screen.CourseDetails(viewModel.courses.first().id)) },
                            onBookAllClick = { viewModel.navigateTo(Screen.FullCourseRegistration(viewModel.courses.first().id)) }
                        )
                    }

                    is Screen.CourseDetails -> {
                        val course = viewModel.getCourseById(screen.courseId)
                        CourseDetailsScreen(
                            course = course,
                            onBackClick = { viewModel.navigateBack() },
                            onHomeClick = { viewModel.navigateTo(Screen.Home) },
                            onBookNowClick = { viewModel.navigateTo(Screen.SessionBooking(course.id)) },
                            onBookTrialClick = { viewModel.navigateTo(Screen.FreeTrialBooking(course.id)) }
                        )
                    }

                    is Screen.FreeTrialBooking -> {
                        val course = viewModel.getCourseById(screen.courseId)
                        FreeTrialBookingScreen(
                            course = course,
                            onBackClick = { viewModel.navigateBack() },
                            onConfirmTrial = { childName, phone ->
                                viewModel.showMessage("تم تأكيد حجز الحصة التجريبية بنجاح لـ $childName!")
                                viewModel.navigateTo(Screen.ParentDashboard)
                            },
                            onNavigateToFullRegistration = {
                                viewModel.navigateTo(Screen.FullCourseRegistration(course.id))
                            }
                        )
                    }

                    is Screen.FullCourseRegistration -> {
                        val course = viewModel.getCourseById(screen.courseId)
                        FullCourseRegistrationScreen(
                            course = course,
                            onBackClick = { viewModel.navigateBack() },
                            onRegistrationConfirmed = {
                                viewModel.showMessage("تم تأكيد التسجيل في كورس ${course.title} بنجاح!")
                                viewModel.navigateTo(Screen.ParentDashboard)
                            }
                        )
                    }

                    is Screen.SessionBooking -> {
                        val course = viewModel.getCourseById(screen.courseId)
                        SessionBookingScreen(
                            course = course,
                            onBackClick = { viewModel.navigateBack() },
                            onConfirmBooking = { date, time ->
                                viewModel.showMessage("تم حجز الحصة في موعد $date - $time بنجاح!")
                                viewModel.navigateTo(Screen.ParentDashboard)
                            }
                        )
                    }

                    is Screen.LiveSession -> {
                        val liveMessages by viewModel.liveMessages.collectAsState()
                        val isMicMuted by viewModel.isMicMuted.collectAsState()
                        val isCameraOn by viewModel.isCameraOn.collectAsState()

                        LiveSessionScreen(
                            messages = liveMessages,
                            isMicMuted = isMicMuted,
                            isCameraOn = isCameraOn,
                            onToggleMic = { viewModel.toggleMic() },
                            onToggleCamera = { viewModel.toggleCamera() },
                            onLeaveSession = { viewModel.navigateTo(Screen.Home) },
                            onSendMessage = { text -> viewModel.sendLiveMessage(text) },
                            onHomeClick = { viewModel.navigateTo(Screen.Home) }
                        )
                    }

                    is Screen.Assignments -> {
                        val assignment by viewModel.assignment.collectAsState()
                        AssignmentsScreen(
                            assignment = assignment,
                            onBackClick = { viewModel.navigateBack() },
                            onSubmitSolution = { fileName -> viewModel.submitAssignment(fileName) }
                        )
                    }

                    is Screen.ChatSupport -> {
                        val teacherMessages by viewModel.teacherChat.collectAsState()
                        val supportMessages by viewModel.supportChat.collectAsState()
                        ChatScreen(
                            teacherMessages = teacherMessages,
                            supportMessages = supportMessages,
                            onBackClick = { viewModel.navigateBack() },
                            onSendTeacherMessage = { text -> viewModel.sendTeacherChat(text) },
                            onSendSupportMessage = { text -> viewModel.sendSupportChat(text) }
                        )
                    }

                    is Screen.ParentDashboard -> {
                        val children by viewModel.children.collectAsState()
                        val upcomingClasses by viewModel.upcomingClasses.collectAsState()
                        ParentDashboardScreen(
                            children = children,
                            badges = viewModel.badges,
                            upcomingClasses = upcomingClasses,
                            currentTab = selectedTab,
                            onTabSelected = { tab -> viewModel.selectTab(tab) },
                            onJoinLiveClass = { viewModel.navigateTo(Screen.LiveSession) },
                            onAddChildClick = {
                                viewModel.repository.addChild("عمر علي", "مبرمج المستقبل", "المستوى 1")
                                viewModel.showMessage("تمت إضافة الطفل عمر بنجاح!")
                            },
                            onSwitchToStudentProfile = {
                                viewModel.setUserRole(UserRole.STUDENT)
                                viewModel.navigateTo(Screen.StudentProfile)
                            },
                            onTeacherProfileClick = { viewModel.navigateTo(Screen.TeacherProfile) },
                            onOpenAssignments = { viewModel.navigateTo(Screen.Assignments) }
                        )
                    }

                    is Screen.StudentProfile -> {
                        StudentProfileScreen(
                            student = viewModel.studentProfile,
                            enrolledCourses = viewModel.enrolledCourses,
                            todaySchedule = viewModel.todaySchedule,
                            currentTab = selectedTab,
                            onTabSelected = { tab -> viewModel.selectTab(tab) },
                            onJoinLiveClass = { viewModel.navigateTo(Screen.LiveSession) },
                            onOpenParentDashboard = {
                                viewModel.setUserRole(UserRole.PARENT)
                                viewModel.navigateTo(Screen.ParentDashboard)
                            },
                            onOpenAssignments = { viewModel.navigateTo(Screen.Assignments) },
                            onOpenChat = { viewModel.navigateTo(Screen.ChatSupport) },
                            onLogout = { viewModel.navigateTo(Screen.Welcome) }
                        )
                    }

                    is Screen.TeacherProfile -> {
                        TeacherProfileScreen(
                            teacher = viewModel.teacherInfo,
                            onBackClick = { viewModel.navigateBack() },
                            onBookAppointment = {
                                viewModel.navigateTo(Screen.SessionBooking(viewModel.courses.first().id))
                            }
                        )
                    }
                }
            }
        }
    }
}
