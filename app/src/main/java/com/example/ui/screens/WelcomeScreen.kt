package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AppAssetImage
import com.example.ui.components.PrimaryGoldButton
import com.example.ui.components.StarBadgeLogo
import com.example.ui.theme.*

@Composable
fun WelcomeScreen(
    onNavigateToLogin: (isParent: Boolean) -> Unit,
    onNavigateToSignUp: (isParent: Boolean) -> Unit,
    onNavigateToHomeAsGuest: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedRoleIsParent by remember { mutableStateOf(true) }

    val infiniteTransition = rememberInfiniteTransition(label = "hero_float")
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = -6f,
        targetValue = 6f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDE9FE), // Soft Lavender top
                        Color(0xFFFEF9C3), // Warm pastel cream
                        Color(0xFFFFFFFF)  // Clean white bottom
                    )
                )
            )
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            // Logo with gentle floating effect
            Box(
                modifier = Modifier
                    .offset(y = floatOffset.dp)
                    .size(100.dp),
                contentAlignment = Alignment.Center
            ) {
                StarBadgeLogo(size = 92.dp)
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Title
            Text(
                text = "أكاديمية النجوم الصغار",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkNavy,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Subtitle
            Text(
                text = "منصة التعليم التفاعلي والممتع لأطفالنا النوابغ",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = TextGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Hero Graphic from bundle
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .shadow(4.dp, RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center
            ) {
                AppAssetImage(
                    assetName = "Splash_Screen_445e0997.png",
                    contentDescription = "طفل يقرأ كتاب العلم السحري",
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Role Switcher: Parent vs Child
            Text(
                text = "اختر نوع الحساب للمتابعة:",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Parent Choice Card
                RoleSelectionCard(
                    title = "ولي الأمر",
                    subtitle = "متابعة وتقارير الأداء",
                    icon = Icons.Outlined.SupervisorAccount,
                    isSelected = selectedRoleIsParent,
                    activeColor = StarYellowDark,
                    activeBg = Color(0xFFFEF3C7),
                    onClick = { selectedRoleIsParent = true },
                    modifier = Modifier.weight(1f),
                    testTag = "welcome_role_parent"
                )

                // Child Choice Card
                RoleSelectionCard(
                    title = "البطل الصغير",
                    subtitle = "حصص، ألعاب وجوائز",
                    icon = Icons.Outlined.Face,
                    isSelected = !selectedRoleIsParent,
                    activeColor = SoftPurple,
                    activeBg = Color(0xFFF3EDFF),
                    onClick = { selectedRoleIsParent = false },
                    modifier = Modifier.weight(1f),
                    testTag = "welcome_role_child"
                )
            }
        }

        Spacer(modifier = Modifier.height(26.dp))

        // Action Buttons Section
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Primary Login Button
            PrimaryGoldButton(
                text = if (selectedRoleIsParent) "تسجيل الدخول كولي أمر" else "تسجيل الدخول كطالب",
                icon = Icons.AutoMirrored.Filled.Login,
                onClick = { onNavigateToLogin(selectedRoleIsParent) },
                testTag = "welcome_login_button"
            )

            Spacer(modifier = Modifier.height(14.dp))

            // 2. Outlined Sign Up Button
            OutlinedButton(
                onClick = { onNavigateToSignUp(selectedRoleIsParent) },
                shape = RoundedCornerShape(20.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, StarYellowDark),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = DarkNavy
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .testTag("welcome_signup_button")
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PersonAdd,
                        contentDescription = null,
                        tint = StarYellowDark,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "إنشاء حساب جديد",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // 3. Guest Explorer Link
            TextButton(
                onClick = onNavigateToHomeAsGuest,
                modifier = Modifier.testTag("welcome_guest_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "تصفح الكورسات والمناهج كزائر",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CoralPink
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null,
                        tint = CoralPink,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Trust highlights row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TrustPill(icon = "🛡️", text = "بيئة آمنة للأطفال")
                Box(modifier = Modifier.size(4.dp).background(LightGray, CircleShape))
                TrustPill(icon = "⭐", text = "معلمون معتمدون")
                Box(modifier = Modifier.size(4.dp).background(LightGray, CircleShape))
                TrustPill(icon = "📈", text = "متابعة أسبوعية")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun RoleSelectionCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    activeColor: Color,
    activeBg: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = ""
) {
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) activeColor else CardBorder,
        label = "border"
    )
    val bgColor by animateColorAsState(
        targetValue = if (isSelected) activeBg else SurfaceWhite,
        label = "bg"
    )

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = bgColor,
        border = androidx.compose.foundation.BorderStroke(if (isSelected) 2.dp else 1.dp, borderColor),
        shadowElevation = if (isSelected) 4.dp else 1.dp,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag(testTag)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(
                        if (isSelected) activeColor.copy(alpha = 0.2f) else Color(0xFFF1F5F9),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = if (isSelected) activeColor else TextGray,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = subtitle,
                fontSize = 11.sp,
                color = TextGray,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun TrustPill(
    icon: String,
    text: String
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = icon, fontSize = 12.sp)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 11.sp,
            color = TextGray,
            fontWeight = FontWeight.Medium
        )
    }
}
