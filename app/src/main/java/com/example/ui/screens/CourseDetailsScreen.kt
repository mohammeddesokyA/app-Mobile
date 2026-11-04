package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Course
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*

@Composable
fun CourseDetailsScreen(
    course: Course,
    onBackClick: () -> Unit,
    onHomeClick: () -> Unit,
    onBookNowClick: () -> Unit,
    onBookTrialClick: () -> Unit,
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Booking Button
                    Button(
                        onClick = onBookNowClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = StarYellow,
                            contentColor = DarkNavy
                        ),
                        shape = RoundedCornerShape(18.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                        modifier = Modifier
                            .weight(1.2f)
                            .height(52.dp)
                            .testTag("course_book_now_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = DarkNavy,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "احجز الآن",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Price Section
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.weight(0.8f)
                    ) {
                        Text(
                            text = "السعر الكلي",
                            fontSize = 12.sp,
                            color = CoralPink,
                            fontWeight = FontWeight.Medium
                        )
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "${course.priceEgp}",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = DarkNavy
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "EGP",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextGray
                            )
                        }
                    }
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
            // Hero Image Container with floating action buttons
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            ) {
                AppAssetImage(
                    assetName = course.assetImage,
                    contentDescription = course.title,
                    modifier = Modifier.fillMaxSize()
                )

                // Top floating action buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .windowInsetsPadding(WindowInsets.statusBars)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(44.dp)
                            .background(SurfaceWhite.copy(alpha = 0.9f), CircleShape)
                            .shadow(4.dp, CircleShape)
                            .testTag("course_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "رجوع",
                            tint = DarkNavy
                        )
                    }

                    IconButton(
                        onClick = onHomeClick,
                        modifier = Modifier
                            .size(44.dp)
                            .background(SurfaceWhite.copy(alpha = 0.9f), CircleShape)
                            .shadow(4.dp, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = "الرئيسية",
                            tint = CoralPink
                        )
                    }
                }
            }

            // Overlapping Card with Title and Ratings
            Surface(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = SurfaceWhite,
                shadowElevation = 6.dp,
                modifier = Modifier
                    .offset(y = (-24).dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                ) {
                    // Tag and Rating
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "(${course.reviewsCount})",
                                fontSize = 13.sp,
                                color = CoralPink
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${course.rating}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = null,
                                tint = StarAmber,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Surface(
                            color = Color(0xFFFEF3C7),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = course.tag,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = course.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkNavy,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 3 Feature Cards
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FeatureInfoCard(
                            title = "المدة",
                            value = "${course.durationMinutes} دقيقة",
                            icon = Icons.Outlined.AccessTime,
                            iconTint = SoftPurple,
                            modifier = Modifier.weight(1f)
                        )
                        FeatureInfoCard(
                            title = "الفئة العمرية",
                            value = course.ageGroup,
                            icon = Icons.Outlined.People,
                            iconTint = SoftPurple,
                            modifier = Modifier.weight(1f)
                        )
                        FeatureInfoCard(
                            title = "المستوى",
                            value = course.level,
                            icon = Icons.Outlined.BarChart,
                            iconTint = SoftPurple,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // About Section
                    Text(
                        text = "عن الكورس",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = course.description,
                        fontSize = 14.sp,
                        color = TextGray,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Free trial offer banner
                    Surface(
                        color = Color(0xFFFFF9E6),
                        shape = RoundedCornerShape(16.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, StarYellow),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = onBookTrialClick,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = StarYellow,
                                    contentColor = DarkNavy
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "احجز مجاناً",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = "حصة تجريبية مجانية 30 دقيقة",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = DarkNavy
                                )
                                Text(
                                    text = "جرّب الأكاديمية مع طفلك بدون أي التزام",
                                    fontSize = 11.sp,
                                    color = TextGray
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
private fun FeatureInfoCard(
    title: String,
    value: String,
    icon: ImageVector,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = SurfaceWhite,
        border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
        shadowElevation = 1.dp,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                fontSize = 11.sp,
                color = CoralPink,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = value,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy,
                textAlign = TextAlign.Center
            )
        }
    }
}
