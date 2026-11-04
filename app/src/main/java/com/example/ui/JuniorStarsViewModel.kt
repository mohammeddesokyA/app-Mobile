package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.*
import com.example.data.repository.JuniorStarsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class Screen {
    object Splash : Screen()
    object Welcome : Screen()
    object Login : Screen()
    object SignUp : Screen()
    object ForgotPassword : Screen()
    object OtpVerification : Screen()
    object NewPassword : Screen()
    object Home : Screen()
    object AllCourses : Screen()
    data class CourseDetails(val courseId: String) : Screen()
    data class FreeTrialBooking(val courseId: String) : Screen()
    data class FullCourseRegistration(val courseId: String) : Screen()
    data class SessionBooking(val courseId: String) : Screen()
    object LiveSession : Screen()
    object Assignments : Screen()
    object ChatSupport : Screen()
    object ParentDashboard : Screen()
    object StudentProfile : Screen()
    object TeacherProfile : Screen()
}

enum class UserRole {
    PARENT,
    STUDENT
}

enum class NavigationTab {
    HOME,
    COURSES,
    LIVE,
    PROFILE
}

class JuniorStarsViewModel(
    val repository: JuniorStarsRepository = JuniorStarsRepository()
) : ViewModel() {

    private val _currentUserRole = MutableStateFlow(UserRole.PARENT)
    val currentUserRole: StateFlow<UserRole> = _currentUserRole.asStateFlow()

    fun setUserRole(role: UserRole) {
        _currentUserRole.value = role
    }

    fun toggleUserRole() {
        val newRole = if (_currentUserRole.value == UserRole.PARENT) UserRole.STUDENT else UserRole.PARENT
        _currentUserRole.value = newRole
        _userMessage.value = if (newRole == UserRole.PARENT) {
            "تم التبديل إلى حساب ولي الأمر (أحمد محمد) 👨‍👧"
        } else {
            "تم التبديل إلى حساب الطالب البطل (مريم علي) 🌟"
        }
    }

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Splash)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    private val _screenHistory = mutableListOf<Screen>()

    private val _selectedTab = MutableStateFlow(NavigationTab.HOME)
    val selectedTab: StateFlow<NavigationTab> = _selectedTab.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(true)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    val courses = repository.courses
    val homeCategories = repository.homeCategories
    val allSubjects = repository.allSubjects
    val badges = repository.badges
    val children = repository.children
    val upcomingClasses = repository.upcomingClasses
    val studentProfile = repository.studentProfile
    val enrolledCourses = repository.enrolledCourses
    val todaySchedule = repository.todaySchedule
    val assignment = repository.assignment
    val teacherChat = repository.teacherChat
    val supportChat = repository.supportChat
    val liveMessages = repository.liveMessages
    val teacherInfo = repository.teacherInfo

    // Live session controls state
    private val _isMicMuted = MutableStateFlow(false)
    val isMicMuted: StateFlow<Boolean> = _isMicMuted.asStateFlow()

    private val _isCameraOn = MutableStateFlow(true)
    val isCameraOn: StateFlow<Boolean> = _isCameraOn.asStateFlow()

    // Notification toast / alert message
    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    fun navigateTo(screen: Screen) {
        _screenHistory.add(_currentScreen.value)
        _currentScreen.value = screen
        // Sync bottom bar tab
        when (screen) {
            is Screen.Home -> _selectedTab.value = NavigationTab.HOME
            is Screen.AllCourses -> _selectedTab.value = NavigationTab.COURSES
            is Screen.LiveSession -> _selectedTab.value = NavigationTab.LIVE
            is Screen.StudentProfile, is Screen.ParentDashboard -> _selectedTab.value = NavigationTab.PROFILE
            else -> {}
        }
    }

    fun navigateBack(): Boolean {
        if (_screenHistory.isNotEmpty()) {
            val previous = _screenHistory.removeAt(_screenHistory.lastIndex)
            _currentScreen.value = previous
            when (previous) {
                is Screen.Home -> _selectedTab.value = NavigationTab.HOME
                is Screen.AllCourses -> _selectedTab.value = NavigationTab.COURSES
                is Screen.LiveSession -> _selectedTab.value = NavigationTab.LIVE
                is Screen.StudentProfile, is Screen.ParentDashboard -> _selectedTab.value = NavigationTab.PROFILE
                else -> {}
            }
            return true
        }
        return false
    }

    fun selectTab(tab: NavigationTab) {
        _selectedTab.value = tab
        when (tab) {
            NavigationTab.HOME -> navigateTo(Screen.Home)
            NavigationTab.COURSES -> navigateTo(Screen.AllCourses)
            NavigationTab.LIVE -> navigateTo(Screen.LiveSession)
            NavigationTab.PROFILE -> {
                if (_currentUserRole.value == UserRole.PARENT) {
                    navigateTo(Screen.ParentDashboard)
                } else {
                    navigateTo(Screen.StudentProfile)
                }
            }
        }
    }

    fun toggleMic() {
        _isMicMuted.update { !it }
    }

    fun toggleCamera() {
        _isCameraOn.update { !it }
    }

    fun submitAssignment(fileName: String) {
        repository.submitAssignment(fileName)
        _userMessage.value = "تم رفع الواجب بنجاح! سيتم تصحيحه بواسطة المعلمة"
    }

    fun sendTeacherChat(text: String) {
        if (text.isNotBlank()) {
            repository.sendTeacherMessage(text)
        }
    }

    fun sendSupportChat(text: String) {
        if (text.isNotBlank()) {
            repository.sendSupportMessage(text)
        }
    }

    fun sendLiveMessage(text: String) {
        if (text.isNotBlank()) {
            repository.sendLiveMessage(text)
        }
    }

    fun showMessage(msg: String) {
        _userMessage.value = msg
    }

    fun clearMessage() {
        _userMessage.value = null
    }

    fun getCourseById(id: String): Course {
        return courses.firstOrNull { it.id == id } ?: courses.first()
    }
}
