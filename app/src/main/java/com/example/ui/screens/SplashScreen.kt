package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.Screen
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableFloatStateOf(0.1f) }

    LaunchedEffect(Unit) {
        val startTime = System.currentTimeMillis()
        while (progress < 1.0f) {
            delay(150)
            progress = (progress + 0.15f).coerceAtMost(1.0f)
        }
        delay(400)
        onNavigateNext()
    }

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.96f,
        targetValue = 1.04f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFEDE9FE), // Soft Lavender top
                        Color(0xFFFEF9C3), // Soft Warm Cream middle
                        Color(0xFFFFFBEB)  // Sunny Yellow bottom
                    )
                )
            )
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Cute Schoolhouse Badge
            Box(
                modifier = Modifier
                    .size(92.dp)
                    .background(Color(0xFFFFF7D6), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = CoralPink,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Kid reading magic book graphic from asset or animated card
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .scale(pulseScale),
                contentAlignment = Alignment.Center
            ) {
                AppAssetImage(
                    assetName = "Splash_Screen_445e0997.png",
                    contentDescription = "طفل يقرأ كتاب العلم والنجوم",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(32.dp))
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // App Name
            Text(
                text = "أكاديمية النجوم الصغار",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkNavy,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "ترحب بكم",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Slogan Tag
            Surface(
                color = CoralPink,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 4.dp
            ) {
                Text(
                    text = "نتعلم • نلعب • نكبر معًا",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = SurfaceWhite,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Loading bar
            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .width(200.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
                color = CoralPink,
                trackColor = CoralPinkLight
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "جاري التحميل...",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = TextGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Instant skip/enter button
            Button(
                onClick = onNavigateNext,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = StarYellow,
                    contentColor = DarkNavy
                ),
                modifier = Modifier.testTag("splash_start_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = DarkNavy, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "ابدأ الآن", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
