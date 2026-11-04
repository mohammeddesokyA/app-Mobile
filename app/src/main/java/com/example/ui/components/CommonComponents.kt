package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.ui.NavigationTab
import com.example.ui.theme.*

@Composable
fun AppAssetImage(
    assetName: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data("file:///android_asset/$assetName")
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun StarBadgeLogo(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .shadow(12.dp, RoundedCornerShape(26.dp), spotColor = StarYellow.copy(alpha = 0.5f))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF7DC), Color(0xFFFFE89A))
                ),
                shape = RoundedCornerShape(26.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.AutoAwesome,
            contentDescription = "شعار النجوم الصغار",
            tint = StarAmber,
            modifier = Modifier.size(size * 0.55f)
        )
    }
}

@Composable
fun PrimaryGoldButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    enabled: Boolean = true,
    testTag: String = "primary_button"
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = StarYellow,
            contentColor = DarkNavy
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp, pressedElevation = 1.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .testTag(testTag)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = DarkNavy
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy
            )
            if (trailingIcon != null) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = DarkNavy
                )
            }
        }
    }
}

@Composable
fun ScreenHeader(
    title: String,
    onBackClick: (() -> Unit)? = null,
    onHomeClick: (() -> Unit)? = null,
    onShareClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .size(44.dp)
                    .background(Color(0xFFF1F5F9), CircleShape)
                    .testTag("back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "رجوع",
                    tint = DarkNavy
                )
            }
        } else if (onShareClick != null) {
            IconButton(
                onClick = onShareClick,
                modifier = Modifier
                    .size(44.dp)
                    .background(SurfaceWhite.copy(alpha = 0.9f), CircleShape)
                    .shadow(4.dp, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "مشاركة",
                    tint = DarkNavy
                )
            }
        } else {
            Spacer(modifier = Modifier.size(44.dp))
        }

        Surface(
            color = Color(0xFFF1F5F9),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
            )
        }

        if (onHomeClick != null) {
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
        } else {
            Spacer(modifier = Modifier.size(44.dp))
        }
    }
}

@Composable
fun JuniorBottomNavigationBar(
    currentTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .shadow(16.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        color = SurfaceWhite,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                title = "الرئيسية",
                icon = Icons.Filled.Home,
                isSelected = currentTab == NavigationTab.HOME,
                onClick = { onTabSelected(NavigationTab.HOME) },
                testTag = "nav_home"
            )
            BottomNavItem(
                title = "الكورسات",
                icon = Icons.Filled.MenuBook,
                isSelected = currentTab == NavigationTab.COURSES,
                onClick = { onTabSelected(NavigationTab.COURSES) },
                testTag = "nav_courses"
            )
            BottomNavItem(
                title = "مباشر",
                icon = Icons.Filled.Videocam,
                isSelected = currentTab == NavigationTab.LIVE,
                onClick = { onTabSelected(NavigationTab.LIVE) },
                testTag = "nav_live"
            )
            BottomNavItem(
                title = "حسابي",
                icon = Icons.Filled.Person,
                isSelected = currentTab == NavigationTab.PROFILE,
                onClick = { onTabSelected(NavigationTab.PROFILE) },
                testTag = "nav_profile"
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    title: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit,
    testTag: String
) {
    val tint by animateColorAsState(
        targetValue = if (isSelected) StarYellowDark else LightGray,
        label = "nav_color"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 14.dp, vertical = 6.dp)
            .testTag(testTag)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = tint
        )
    }
}

@Composable
fun CircularProgressBadge(
    percent: Int,
    color: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 6.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.size(54.dp)
    ) {
        CircularProgressIndicator(
            progress = { percent / 100f },
            modifier = Modifier.fillMaxSize(),
            color = color,
            trackColor = color.copy(alpha = 0.2f),
            strokeWidth = strokeWidth
        )
        Text(
            text = "$percent%",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = DarkNavy
        )
    }
}
