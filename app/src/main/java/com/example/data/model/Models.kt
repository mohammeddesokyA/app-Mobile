package com.example.data.model

data class Course(
    val id: String,
    val title: String,
    val categoryName: String,
    val rating: Double = 4.9,
    val reviewsCount: Int = 120,
    val level: String,
    val ageGroup: String,
    val durationMinutes: Int,
    val priceEgp: Int = 250,
    val description: String,
    val assetImage: String,
    val tag: String
)

data class SubjectCategory(
    val id: String,
    val title: String,
    val iconType: String,
    val colorHex: Long,
    val bgHex: Long
)

data class ChildProgress(
    val id: String,
    val name: String,
    val courseName: String,
    val levelText: String,
    val progressPercent: Int,
    val colorHex: Long
)

data class BadgeAward(
    val id: String,
    val title: String,
    val subtitle: String,
    val emoji: String,
    val bgHex: Long
)

data class UpcomingClass(
    val id: String,
    val title: String,
    val childName: String,
    val teacherName: String,
    val dateText: String,
    val timeText: String,
    val hasJoinButton: Boolean,
    val isCompleted: Boolean = false
)

data class StudentProfile(
    val name: String = "مريم علي",
    val levelTitle: String = "المستوى الذهبي",
    val points: Int = 850,
    val certificatesCount: Int = 12,
    val activeCoursesCount: Int = 4,
    val avatarAsset: String = "Student_Profile_27f0e10a.png"
)

data class EnrolledCourse(
    val id: String,
    val title: String,
    val remainingSessions: Int,
    val progressPercent: Int,
    val colorHex: Long,
    val iconType: String
)

data class ScheduleItem(
    val id: String,
    val timeText: String,
    val periodText: String,
    val title: String,
    val teacherName: String,
    val isLive: Boolean,
    val isCompleted: Boolean
)

data class AssignmentItem(
    val id: String,
    val title: String,
    val subject: String,
    val dueDate: String,
    val instructions: String,
    val pdfFileName: String,
    val pdfFileSize: String,
    val isSubmitted: Boolean = false,
    val submittedFileName: String? = null
)

data class ChatMessage(
    val id: String,
    val senderName: String,
    val text: String,
    val time: String,
    val isMe: Boolean,
    val avatarEmoji: String = "👩‍🏫",
    val senderRole: String = "teacher"
)

data class TeacherInfo(
    val name: String = "أ/ سارة أحمد",
    val title: String = "معلمة قرآن معتمدة",
    val specialization: String = "متخصصة في نور البيان وتجويد الأطفال",
    val rating: Double = 4.9,
    val studentsCount: String = "+500",
    val experienceYears: String = "+10",
    val bio: String = "أؤمن بأن تحبيب الأطفال في كتاب الله يبدأ بالصبر والمرح. خبرة طويلة في تدريس منهج نور البيان للأعمار الصغيرة، مع استخدام وسائل تعليمية تفاعلية تجعل الحصة رحلة شيقة للطفل.",
    val assetImage: String = "Teacher_Profile_34e5f675.png"
)
