package com.example.data.repository

import com.example.data.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class JuniorStarsRepository {

    val courses = listOf(
        Course(
            id = "arabic_noor_bayan",
            title = "كورس نور البيان لتأسيس القراءة",
            categoryName = "اللغة العربية",
            rating = 4.9,
            reviewsCount = 120,
            level = "مبتدئ",
            ageGroup = "4-12 سنة",
            durationMinutes = 60,
            priceEgp = 250,
            description = "كورس متخصص للأطفال لتعلم القراءة الصحيحة من خلال منهج نور البيان، مع التركيز على مخارج الحروف والنطق السليم بطريقة ممتعة وتفاعلية تناسب عقولهم الصغيرة.",
            assetImage = "Arabic_Course_c9b58b22.png",
            tag = "اللغة العربية"
        ),
        Course(
            id = "quran_kids",
            title = "تحفيظ وتجويد القرآن الكريم للأطفال",
            categoryName = "القرآن الكريم",
            rating = 4.9,
            reviewsCount = 120,
            level = "متدرج",
            ageGroup = "5-14 سنة",
            durationMinutes = 45,
            priceEgp = 250,
            description = "برنامج مخصص لتحفيظ القرآن الكريم بالتجويد للأطفال برواية حفص عن عاصم، مع شرح مبسط لأحكام التجويد والقصص القرآنية لربط الطفل بكتاب الله.",
            assetImage = "Quran_Course_3fd0ee0a.png",
            tag = "القرآن الكريم"
        ),
        Course(
            id = "english_phonics",
            title = "كورس الفونكس والتأسيس الشامل (PHONICS PLUS)",
            categoryName = "اللغة الإنجليزية",
            rating = 4.9,
            reviewsCount = 120,
            level = "جميع المستويات",
            ageGroup = "5-14 سنة",
            durationMinutes = 60,
            priceEgp = 250,
            description = "رحلة ممتعة لتأسيس طفلك في اللغة الإنجليزية من الصفر! يركز الكورس على دمج الأصوات (Phonics)، القراءة السلسة، والمحادثة التفاعلية لبناء ثقة كاملة بالنفس أثناء النطق.",
            assetImage = "English_Course_4b6caaae.png",
            tag = "اللغة الإنجليزية"
        ),
        Course(
            id = "science_explorer",
            title = "كورس العلوم للمستكشف الصغير",
            categoryName = "العلوم والاستكشاف",
            rating = 4.9,
            reviewsCount = 120,
            level = "متوسط",
            ageGroup = "5-14 سنة",
            durationMinutes = 60,
            priceEgp = 250,
            description = "كورس تفاعلي غني بالتجارب العلمية والأنشطة البصرية لتبسيط مفاهيم العلوم، الفيزياء، والأحياء للأطفال لتنمية مهارات التفكير العلمي والبحث لديهم بطريقة مسلية ومحفزة على الاستكشاف.",
            assetImage = "Science_Course_666993fc.png",
            tag = "العلوم والاستكشاف"
        ),
        Course(
            id = "programming_scratch",
            title = "كورس مبرمج المستقبل وتطوير الألعاب",
            categoryName = "التكنولوجيا والبرمجة",
            rating = 4.9,
            reviewsCount = 120,
            level = "مبتدئ",
            ageGroup = "5-14 سنة",
            durationMinutes = 60,
            priceEgp = 250,
            description = "كورس تطبيقي ممتع لتعليم الأطفال أساسيات التفكير البرمجي والمنطقي! سيتعلم الطفل كيفية بناء ألعابه وقصصه التفاعلية الخاصة باستخدام Scratch والانتقال التمهيدي للغة Python، مع مقدمة مبسطة وممتعة عن الذكاء الاصطناعي.",
            assetImage = "Programming_Course_d667ad86.png",
            tag = "التكنولوجيا والبرمجة"
        )
    )

    val homeCategories = listOf(
        SubjectCategory("arabic", "عربي", "book", 0xFFECA72C, 0xFFFEF7E6),
        SubjectCategory("languages", "لغات", "translate", 0xFF4A90E2, 0xFFEBF5FF),
        SubjectCategory("science", "علوم", "atom", 0xFF10B981, 0xFFE8F7F0),
        SubjectCategory("coding", "برمجة", "code", 0xFF8B5CF6, 0xFFF3EDFF)
    )

    val allSubjects = listOf(
        SubjectCategory("math_arabic", "الرياضيات", "calculator", 0xFF4F46E5, 0xFFEEF2FF),
        SubjectCategory("math_english", "الماث (Math)", "calculate", 0xFFE11D48, 0xFFFFECEF),
        SubjectCategory("science_arabic", "العلوم", "science", 0xFF059669, 0xFFE8F7F0),
        SubjectCategory("science_english", "الساينس (Science)", "biotech", 0xFF7C3AED, 0xFFF3EDFF),
        SubjectCategory("art", "الرسم والفنون", "palette", 0xFFEA580C, 0xFFFFF3E8),
        SubjectCategory("social", "الدراسات", "map", 0xFF0284C7, 0xFFEAF8FF),
        SubjectCategory("islamic", "التربية الإسلامية", "mosque", 0xFF16A34A, 0xFFECFDF5),
        SubjectCategory("english", "اللغة الإنجليزية", "translate", 0xFF6366F1, 0xFFEEF2FF),
        SubjectCategory("arabic", "اللغة العربية", "font_download", 0xFFD97706, 0xFFFEF3C7),
        SubjectCategory("quran", "القرآن الكريم", "menu_book", 0xFF0D9488, 0xFFCCFBF1),
        SubjectCategory("discover", "ديسكفر", "explore", 0xFFDB2777, 0xFFFDF2F8)
    )

    val badges = listOf(
        BadgeAward("1", "مثابر يومي", "7 أيام", "🔥", 0xFFFFECEF),
        BadgeAward("2", "جامع النجوم", "متميز", "💎", 0xFFEAF8FF),
        BadgeAward("3", "أول حصة", "منطلق", "🚀", 0xFFF3EDFF),
        BadgeAward("4", "الفنان الصغير", "مبدع", "🎨", 0xFFFEF7E6)
    )

    private val _children = MutableStateFlow(
        listOf(
            ChildProgress("1", "مريم علي", "نور البيان", "المستوى 2", 75, 0xFFF4BF38),
            ChildProgress("2", "ياسين علي", "أساسيات العلوم", "المستوى 1", 40, 0xFF38BDF8)
        )
    )
    val children: StateFlow<List<ChildProgress>> = _children.asStateFlow()

    private val _upcomingClasses = MutableStateFlow(
        listOf(
            UpcomingClass("1", "نور البيان - مريم", "مريم", "أ/ سارة", "12 مايو", "05:00 م", hasJoinButton = true),
            UpcomingClass("2", "علوم - ياسين", "ياسين", "أ/ محمد", "14 مايو", "07:00 م", hasJoinButton = false)
        )
    )
    val upcomingClasses: StateFlow<List<UpcomingClass>> = _upcomingClasses.asStateFlow()

    val studentProfile = StudentProfile()

    val enrolledCourses = listOf(
        EnrolledCourse("1", "تحفيظ القرآن الكريم", 4, 65, 0xFFF4BF38, "pause"),
        EnrolledCourse("2", "تأسيس إنجليزي", 8, 30, 0xFF60A5FA, "ad")
    )

    val todaySchedule = listOf(
        ScheduleItem("1", "04:00", "مساءً", "كورس الرسم", "أ/ سارة - بث مباشر", isLive = true, isCompleted = false),
        ScheduleItem("2", "07:30", "مساءً", "لغة عربية", "أ/ محمد - مسجل", isLive = false, isCompleted = true)
    )

    private val _assignment = MutableStateFlow(
        AssignmentItem(
            id = "assign_1",
            title = "واجب درس القراءة - نور البيان",
            subject = "لغة عربية",
            dueDate = "25 مايو",
            instructions = "يرجى قراءة الدرس جيداً وحل التمارين الموجودة في الملف المرفق أدناه بخط يدك الجميل.",
            pdfFileName = "assignment_quran.pdf",
            pdfFileSize = "1.2 MB",
            isSubmitted = false
        )
    )
    val assignment: StateFlow<AssignmentItem> = _assignment.asStateFlow()

    private val _teacherChat = MutableStateFlow(
        listOf(
            ChatMessage("1", "أ/ سارة", "أهلاً بك يا بطل! هل أتممت واجب درس نور البيان لليوم؟ ✨", "09:41 ص", false),
            ChatMessage("2", "أنا", "أهلاً يا معلمتي، نعم لقد انتهيت منه وسأرفعه الآن للمراجعة 👍", "09:45 ص", true),
            ChatMessage("3", "أ/ سارة", "ممتاز جداً! أنا بانتظارك. إذا واجهت أي صعوبة في مخارج حروف الصفير أخبرني فوراً.", "09:46 ص", false)
        )
    )
    val teacherChat: StateFlow<List<ChatMessage>> = _teacherChat.asStateFlow()

    private val _supportChat = MutableStateFlow(
        listOf(
            ChatMessage("s1", "الدعم الفني", "مرحباً بكم في الدعم الفني لأكاديمية النجوم الصغار 🌟 كيف يمكننا مساعدتكم اليوم؟", "10:00 ص", false)
        )
    )
    val supportChat: StateFlow<List<ChatMessage>> = _supportChat.asStateFlow()

    private val _liveMessages = MutableStateFlow(
        listOf(
            ChatMessage("m1", "أحمد محمد", "هل ترون رسمتي؟", "16:02", false),
            ChatMessage("m2", "ليلى علي", "نعم رسمك جميل جداً! 😍", "16:03", false),
            ChatMessage("m3", "ياسين كمال", "يا ميس، ممكن تعيد شرح تلوين العين؟", "16:04", false)
        )
    )
    val liveMessages: StateFlow<List<ChatMessage>> = _liveMessages.asStateFlow()

    val teacherInfo = TeacherInfo()

    fun submitAssignment(fileName: String) {
        _assignment.update {
            it.copy(isSubmitted = true, submittedFileName = fileName)
        }
    }

    fun sendTeacherMessage(text: String) {
        val newMsg = ChatMessage(
            id = System.currentTimeMillis().toString(),
            senderName = "أنا",
            text = text,
            time = "الآن",
            isMe = true
        )
        _teacherChat.update { it + newMsg }
    }

    fun sendSupportMessage(text: String) {
        val newMsg = ChatMessage(
            id = System.currentTimeMillis().toString(),
            senderName = "أنا",
            text = text,
            time = "الآن",
            isMe = true
        )
        _supportChat.update { it + newMsg }
    }

    fun sendLiveMessage(text: String) {
        val newMsg = ChatMessage(
            id = System.currentTimeMillis().toString(),
            senderName = "أنا (مريم)",
            text = text,
            time = "الآن",
            isMe = true
        )
        _liveMessages.update { it + newMsg }
    }

    fun addChild(name: String, course: String, level: String) {
        val newChild = ChildProgress(
            id = System.currentTimeMillis().toString(),
            name = name,
            courseName = course,
            levelText = level,
            progressPercent = 15,
            colorHex = 0xFF10B981
        )
        _children.update { it + newChild }
    }
}
