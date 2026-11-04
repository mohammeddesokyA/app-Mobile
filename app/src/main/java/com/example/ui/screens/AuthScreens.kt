package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.UserRole
import com.example.ui.components.PrimaryGoldButton
import com.example.ui.components.StarBadgeLogo
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onLoginSuccess: (UserRole) -> Unit,
    onNavigateToSignUp: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    onNavigateToGuest: (() -> Unit)? = null,
    initialRole: UserRole = UserRole.PARENT,
    modifier: Modifier = Modifier
) {
    var isParentRole by remember { mutableStateOf(initialRole == UserRole.PARENT) }
    var identifier by remember { mutableStateOf(if (isParentRole) "01012345678" else "maryam_star") }
    var password by remember { mutableStateOf("••••••••") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFDF5),
                        Color(0xFFF9FAFC),
                        Color(0xFFFFFFFF)
                    )
                )
            )
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Bar with Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (onBackClick != null) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color(0xFFF1F5F9), CircleShape)
                        .testTag("login_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع",
                        tint = DarkNavy
                    )
                }
            } else {
                Spacer(modifier = Modifier.size(42.dp))
            }

            Surface(
                color = Color(0xFFFEF3C7),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Icon(
                        imageVector = if (isParentRole) Icons.Outlined.SupervisorAccount else Icons.Outlined.Face,
                        contentDescription = null,
                        tint = StarAmber,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (isParentRole) "دخول ولي الأمر" else "دخول الطالب البطل",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF92400E)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Clean typography header - without any bulky images
        Text(
            text = "أكاديمية النجوم الصغار",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "تسجيل الدخول إلى حسابك",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (isParentRole) StarAmber else SoftPurple
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = if (isParentRole)
                "تابع تقارير أداء طفلك، الحصص القادمة، واشتراكات الكورسات"
            else
                "أهلاً بك يا بطل! ادخل لحصصك المباشرة وتحدياتك الممتعة",
            fontSize = 13.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Role Switcher Tabs (Parent vs Student)
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = Color(0xFFF1F5F9),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                // Parent Tab
                val parentBg by animateColorAsState(
                    targetValue = if (isParentRole) SurfaceWhite else Color.Transparent,
                    label = "parent_tab_bg"
                )
                val parentText by animateColorAsState(
                    targetValue = if (isParentRole) DarkNavy else TextGray,
                    label = "parent_tab_text"
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(parentBg)
                        .clickable { isParentRole = true }
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.SupervisorAccount,
                            contentDescription = null,
                            tint = if (isParentRole) StarAmber else TextGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "ولي الأمر",
                            fontSize = 13.sp,
                            fontWeight = if (isParentRole) FontWeight.Bold else FontWeight.Medium,
                            color = parentText
                        )
                    }
                }

                // Student Tab
                val studentBg by animateColorAsState(
                    targetValue = if (!isParentRole) SurfaceWhite else Color.Transparent,
                    label = "student_tab_bg"
                )
                val studentText by animateColorAsState(
                    targetValue = if (!isParentRole) DarkNavy else TextGray,
                    label = "student_tab_text"
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(studentBg)
                        .clickable { isParentRole = false }
                        .padding(horizontal = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Face,
                            contentDescription = null,
                            tint = if (!isParentRole) SoftPurple else TextGray,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "البطل الصغير",
                            fontSize = 13.sp,
                            fontWeight = if (!isParentRole) FontWeight.Bold else FontWeight.Medium,
                            color = studentText
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Main Login Card
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = SurfaceWhite,
            border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Email / Phone Field
                Text(
                    text = if (isParentRole) "البريد الإلكتروني أو رقم الهاتف" else "اسم المستخدم أو رقم القيد",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = identifier,
                    onValueChange = {
                        identifier = it
                        errorMessage = null
                    },
                    placeholder = {
                        Text(
                            text = if (isParentRole) "010xxxxxxxx أو example@mail.com" else "أدخل اسمك أو رقم حسابك...",
                            color = LightGray,
                            fontSize = 13.sp
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = if (isParentRole) Icons.Outlined.Phone else Icons.Outlined.Person,
                            contentDescription = null,
                            tint = if (isParentRole) StarAmber else SoftPurple,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color(0xFFFBFBFD),
                        unfocusedContainerColor = Color(0xFFFBFBFD)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("login_identifier_input")
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password Field
                Text(
                    text = "كلمة السر",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        errorMessage = null
                    },
                    placeholder = { Text("••••••••", color = LightGray) },
                    singleLine = true,
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = CoralPink,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (isPasswordVisible) "إخفاء كلمة المرور" else "إظهار كلمة المرور",
                                tint = LightGray
                            )
                        }
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color(0xFFFBFBFD),
                        unfocusedContainerColor = Color(0xFFFBFBFD)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("login_password_input")
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Error alert if empty
                if (errorMessage != null) {
                    Text(
                        text = errorMessage ?: "",
                        color = Color(0xFFDC2626),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Remember Me + Forgot Password Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clickable { rememberMe = !rememberMe }
                            .padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = rememberMe,
                            onCheckedChange = { rememberMe = it },
                            colors = CheckboxDefaults.colors(checkedColor = StarAmber)
                        )
                        Text(
                            text = "تذكر بياناتي",
                            fontSize = 12.sp,
                            color = TextGray
                        )
                    }

                    TextButton(
                        onClick = onNavigateToForgotPassword,
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "نسيت كلمة السر؟",
                            color = CoralPink,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Quick Demo Account Helpers
                Surface(
                    color = if (isParentRole) Color(0xFFFEF9C3) else Color(0xFFF3EDFF),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if (isParentRole) {
                                    identifier = "01012345678"
                                    password = "parent_secret"
                                } else {
                                    identifier = "maryam_star"
                                    password = "star_student"
                                }
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = if (isParentRole) "حساب تجريبي: أحمد محمد (ولي أمر)" else "حساب تجريبي: مريم علي (طالبة)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isParentRole) Color(0xFF92400E) else SoftPurple
                        )
                        Text(
                            text = "ملء تلقائي ⚡",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isParentRole) StarAmber else SoftPurple
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Submit Button
                PrimaryGoldButton(
                    text = if (isParentRole) "تسجيل الدخول كولي أمر" else "تسجيل الدخول كطالب بطل",
                    icon = Icons.AutoMirrored.Filled.Login,
                    onClick = {
                        val role = if (isParentRole) UserRole.PARENT else UserRole.STUDENT
                        onLoginSuccess(role)
                    },
                    testTag = "login_submit_button"
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Guest Explorer Entry
        if (onNavigateToGuest != null) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF8FAFC),
                border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable(onClick = onNavigateToGuest)
                    .testTag("login_guest_button")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "الدخول كزائر وتصفح الكورسات والمناهج",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = SoftPurple
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = null,
                        tint = SoftPurple,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(18.dp))
        }

        // Bottom Sign Up Link
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(text = "ليس لديك حساب؟ ", color = TextGray, fontSize = 14.sp)
            Text(
                text = "إنشاء حساب جديد",
                color = CoralPink,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onNavigateToSignUp)
                    .padding(horizontal = 4.dp, vertical = 4.dp)
                    .testTag("login_goto_signup")
            )
        }
    }
}

@Composable
fun SignUpScreen(
    onSignUpSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var parentName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var serialNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceWhite)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        StarBadgeLogo(size = 80.dp)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "أكاديمية النجوم الصغار",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )

        Text(
            text = "إنشاء حساب جديد",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "انضم لعائلتنا التعليمية الممتعة اليوم!",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Parent Name
        OutlinedFieldGroup(
            label = "اسم ولي الأمر ثلاثي",
            value = parentName,
            onValueChange = { parentName = it },
            placeholder = "أدخل اسمك بالكامل",
            trailingIcon = Icons.Outlined.Person,
            testTag = "signup_name_input"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Phone
        OutlinedFieldGroup(
            label = "رقم الهاتف (واتساب)",
            value = phone,
            onValueChange = { phone = it },
            placeholder = "01xxxxxxxxx",
            trailingIcon = Icons.Outlined.Phone,
            keyboardType = KeyboardType.Phone,
            testTag = "signup_phone_input"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Serial Number
        OutlinedFieldGroup(
            label = "الرقم التسلسلي",
            value = serialNumber,
            onValueChange = { serialNumber = it },
            placeholder = "3156xxxxxxx",
            keyboardType = KeyboardType.Number,
            testTag = "signup_serial_input"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email
        OutlinedFieldGroup(
            label = "البريد الإلكتروني",
            value = email,
            onValueChange = { email = it },
            placeholder = "example@mail.com",
            trailingIcon = Icons.Outlined.Email,
            keyboardType = KeyboardType.Email,
            testTag = "signup_email_input"
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "كلمة السر",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy
            )
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("••••••••", color = LightGray) },
                singleLine = true,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null,
                                tint = LightGray
                            )
                        }
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = LightGray,
                            modifier = Modifier.padding(end = 12.dp)
                        )
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = StarAmber,
                    unfocusedBorderColor = CardBorder,
                    focusedContainerColor = SurfaceWhite,
                    unfocusedContainerColor = SurfaceWhite
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("signup_password_input")
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        PrimaryGoldButton(
            text = "إنشاء الحساب والتفعيل",
            onClick = onSignUpSuccess,
            testTag = "signup_submit_button"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "لديك حساب بالفعل؟ ", color = TextGray, fontSize = 14.sp)
            Text(
                text = "تسجيل الدخول",
                color = SoftPurple,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable(onClick = onNavigateToLogin)
                    .testTag("signup_goto_login")
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun ForgotPasswordScreen(
    onSendOtp: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var emailOrPhone by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceWhite)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (onBackClick != null) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color(0xFFF1F5F9), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع",
                        tint = DarkNavy
                    )
                }
            }
        }

        StarBadgeLogo(size = 90.dp)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "أكاديمية النجوم الصغار",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "استعادة كلمة السر",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "أدخل بريدك الإلكتروني أو رقم هاتفك لإرسال رمز التحقق الخاص بحسابك.",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(36.dp))

        OutlinedFieldGroup(
            label = "البريد الإلكتروني أو رقم الهاتف",
            value = emailOrPhone,
            onValueChange = { emailOrPhone = it },
            placeholder = "010xxxxxxxx أو example@mail.com",
            trailingIcon = Icons.Outlined.Email,
            testTag = "forgot_password_input"
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryGoldButton(
            text = "إرسال رمز التحقق",
            onClick = onSendOtp,
            testTag = "forgot_password_submit"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "تذكرت كلمة السر؟ ", color = TextGray, fontSize = 14.sp)
            Text(
                text = "تسجيل الدخول",
                color = SoftPurple,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable(onClick = onNavigateToLogin)
                    .testTag("forgot_goto_login")
            )
        }
    }
}

@Composable
fun OtpVerificationScreen(
    onVerificationSuccess: () -> Unit,
    onContactSupport: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var digit1 by remember { mutableStateOf("5") }
    var digit2 by remember { mutableStateOf("8") }
    var digit3 by remember { mutableStateOf("2") }
    var digit4 by remember { mutableStateOf("4") }
    var secondsRemaining by remember { mutableIntStateOf(59) }

    LaunchedEffect(Unit) {
        while (secondsRemaining > 0) {
            delay(1000)
            secondsRemaining--
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceWhite)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (onBackClick != null) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(42.dp)
                        .background(Color(0xFFF1F5F9), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "رجوع",
                        tint = DarkNavy
                    )
                }
            }
        }

        StarBadgeLogo(size = 90.dp)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "أكاديمية النجوم الصغار",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "رمز التحقق",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "أدخل الرمز المكون من 4 أرقام المرسل إلى هاتفك الجوال.",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(36.dp))

        // 4 OTP Boxes
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OtpDigitBox(value = digit1, onValueChange = { if (it.length <= 1) digit1 = it })
            OtpDigitBox(value = digit2, onValueChange = { if (it.length <= 1) digit2 = it })
            OtpDigitBox(value = digit3, onValueChange = { if (it.length <= 1) digit3 = it })
            OtpDigitBox(value = digit4, onValueChange = { if (it.length <= 1) digit4 = it })
        }

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Timer,
                contentDescription = null,
                tint = SoftPurple,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "إعادة إرسال الرمز خلال 00:${secondsRemaining.toString().padStart(2, '0')}",
                fontSize = 14.sp,
                color = SoftPurple,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { secondsRemaining = 59 },
            enabled = secondsRemaining == 0
        ) {
            Text(
                text = "إرسال الرمز مرة أخرى",
                color = if (secondsRemaining == 0) SoftPurple else LightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryGoldButton(
            text = "تأكيد والتحقق",
            onClick = onVerificationSuccess,
            testTag = "otp_confirm_button"
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "تواجه مشكلة؟ ", color = TextGray, fontSize = 14.sp)
            Text(
                text = "اتصل بالدعم",
                color = SoftPurple,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable(onClick = onContactSupport)
            )
        }
    }
}

@Composable
fun NewPasswordScreen(
    onPasswordResetSuccess: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isNewPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    var validationError by remember { mutableStateOf<String?>(null) }

    val isLengthValid = newPassword.length >= 6
    val isMatching = newPassword.isNotEmpty() && newPassword == confirmPassword

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFDF5),
                        Color(0xFFF9FAFC),
                        Color(0xFFFFFFFF)
                    )
                )
            )
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Back Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(42.dp)
                    .background(Color(0xFFF1F5F9), CircleShape)
                    .testTag("new_password_back")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "رجوع",
                    tint = DarkNavy
                )
            }
        }

        // Shield / Security Emblem
        Box(
            modifier = Modifier
                .size(88.dp)
                .shadow(8.dp, CircleShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFF7DC), Color(0xFFFFE89A))
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.LockReset,
                contentDescription = null,
                tint = StarAmber,
                modifier = Modifier.size(46.dp)
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "تعيين كلمة سر جديدة",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkNavy
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "أدخل كلمة السر الجديدة الخاصة بحسابك، واحرص أن تكون قوية وسهلة التذكر.",
            fontSize = 14.sp,
            color = TextGray,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Main Card
        Surface(
            shape = RoundedCornerShape(24.dp),
            color = SurfaceWhite,
            border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
            shadowElevation = 2.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // New Password Field
                Text(
                    text = "كلمة السر الجديدة",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newPassword,
                    onValueChange = {
                        newPassword = it
                        validationError = null
                    },
                    placeholder = { Text("6 أحرف أو أرقام على الأقل...", color = LightGray, fontSize = 13.sp) },
                    singleLine = true,
                    visualTransformation = if (isNewPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            contentDescription = null,
                            tint = StarAmber,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isNewPasswordVisible = !isNewPasswordVisible }) {
                            Icon(
                                imageVector = if (isNewPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (isNewPasswordVisible) "إخفاء كلمة المرور" else "إظهار كلمة المرور",
                                tint = LightGray
                            )
                        }
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color(0xFFFBFBFD),
                        unfocusedContainerColor = Color(0xFFFBFBFD)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("new_password_input")
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Confirm Password Field
                Text(
                    text = "تأكيد كلمة السر الجديدة",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        validationError = null
                    },
                    placeholder = { Text("أعد كتابة كلمة السر للتأكيد...", color = LightGray, fontSize = 13.sp) },
                    singleLine = true,
                    visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircleOutline,
                            contentDescription = null,
                            tint = if (isMatching) MintGreen else CoralPink,
                            modifier = Modifier.size(20.dp)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                            Icon(
                                imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (isConfirmPasswordVisible) "إخفاء كلمة المرور" else "إظهار كلمة المرور",
                                tint = LightGray
                            )
                        }
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = if (isMatching) MintGreen else StarAmber,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color(0xFFFBFBFD),
                        unfocusedContainerColor = Color(0xFFFBFBFD)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("confirm_password_input")
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Dynamic helper checks
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    PasswordRequirementRow(
                        isValid = isLengthValid,
                        text = "يجب أن تحتوي على 6 خانات على الأقل"
                    )
                    PasswordRequirementRow(
                        isValid = isMatching,
                        text = "تطابق كلمتي السر"
                    )
                }

                if (validationError != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = validationError ?: "",
                        color = Color(0xFFDC2626),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                // Submit Button
                PrimaryGoldButton(
                    text = "حفظ كلمة السر وتأكيد الدخول",
                    icon = Icons.Default.CheckCircle,
                    onClick = {
                        if (newPassword.length < 6) {
                            validationError = "كلمة السر يجب ألا تقل عن 6 خانات."
                        } else if (newPassword != confirmPassword) {
                            validationError = "كلمتا السر غير متطابقتين، يرجى التأكد."
                        } else {
                            onPasswordResetSuccess()
                        }
                    },
                    testTag = "save_new_password_button"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Return to login link
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "تذكرت كلمة السر السابقة؟ ", color = TextGray, fontSize = 14.sp)
            Text(
                text = "تسجيل الدخول",
                color = SoftPurple,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onBackClick)
                    .padding(4.dp)
            )
        }
    }
}

@Composable
private fun PasswordRequirementRow(
    isValid: Boolean,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = if (isValid) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
            contentDescription = null,
            tint = if (isValid) MintGreen else LightGray,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            fontSize = 12.sp,
            color = if (isValid) MintGreen else TextGray
        )
    }
}

@Composable
private fun OtpDigitBox(
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(Color(0xFFF8FAFC), RoundedCornerShape(16.dp))
            .border(
                width = if (value.isNotEmpty()) 1.5.dp else 1.dp,
                color = if (value.isNotEmpty()) StarAmber else CardBorder,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = DarkNavy
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun OutlinedFieldGroup(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    trailingIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    testTag: String = ""
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = LightGray) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            trailingIcon = {
                if (trailingIcon != null) {
                    Icon(
                        imageVector = trailingIcon,
                        contentDescription = null,
                        tint = SoftPurple,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = StarAmber,
                unfocusedBorderColor = CardBorder,
                focusedContainerColor = SurfaceWhite,
                unfocusedContainerColor = SurfaceWhite
            ),
            modifier = Modifier
                .fillMaxWidth()
                .testTag(testTag)
        )
    }
}
