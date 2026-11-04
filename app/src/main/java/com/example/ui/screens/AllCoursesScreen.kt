package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SubjectCategory
import com.example.ui.NavigationTab
import com.example.ui.components.JuniorBottomNavigationBar
import com.example.ui.components.ScreenHeader
import com.example.ui.theme.*

@Composable
fun AllCoursesScreen(
    subjects: List<SubjectCategory>,
    currentTab: NavigationTab,
    onTabSelected: (NavigationTab) -> Unit,
    onBackClick: () -> Unit,
    onSubjectClick: (String) -> Unit,
    onBookAllClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    val filteredSubjects = remember(searchQuery) {
        if (searchQuery.isBlank()) subjects
        else subjects.filter { it.title.contains(searchQuery.trim(), ignoreCase = true) }
    }

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "الكورسات والمناهج",
                onBackClick = onBackClick
            )
        },
        bottomBar = {
            JuniorBottomNavigationBar(
                currentTab = currentTab,
                onTabSelected = onTabSelected
            )
        },
        containerColor = BackgroundOffWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Search Bar
            item(span = { GridItemSpan(2) }) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("ابحث عن مادتك أو كورس...", color = LightGray, fontSize = 14.sp) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "بحث",
                            tint = CoralPink
                        )
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = SurfaceWhite,
                        unfocusedContainerColor = SurfaceWhite
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .testTag("courses_search_bar")
                )
            }

            // Subject Cards Grid
            items(filteredSubjects) { subject ->
                SubjectCard(
                    subject = subject,
                    onClick = { onSubjectClick(subject.id) }
                )
            }

            // Bottom Purple Banner
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    shape = RoundedCornerShape(28.dp),
                    shadowElevation = 8.dp,
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(28.dp))
                        .clickable(onClick = onBookAllClick)
                        .testTag("all_courses_banner")
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF7C3AED),
                                        Color(0xFF6366F1)
                                    )
                                )
                            )
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(54.dp)
                                    .background(SurfaceWhite.copy(alpha = 0.2f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Apps,
                                    contentDescription = null,
                                    tint = SurfaceWhite,
                                    modifier = Modifier.size(28.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "كل المواد الدراسية وكل المناهج",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Bold,
                                color = SurfaceWhite,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = "تصفح جميع المراحل التعليمية في مكان واحد",
                                fontSize = 13.sp,
                                color = SurfaceWhite.copy(alpha = 0.85f),
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = SurfaceWhite.copy(alpha = 0.25f)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                ) {
                                    Text(
                                        text = "احجز الآن",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = StarYellow
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null,
                                        tint = StarYellow,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SubjectCard(
    subject: SubjectCategory,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = SurfaceWhite,
        border = androidx.compose.foundation.BorderStroke(1.2.dp, Color(subject.bgHex).copy(alpha = 0.8f)),
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(124.dp)
            .clip(RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .testTag("subject_card_${subject.id}")
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(Color(subject.bgHex), RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getSubjectIcon(subject.iconType),
                    contentDescription = subject.title,
                    tint = Color(subject.colorHex),
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = subject.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = DarkNavy,
                textAlign = TextAlign.Center
            )
        }
    }
}

private fun getSubjectIcon(type: String): ImageVector {
    return when (type) {
        "calculator", "calculate" -> Icons.Default.Calculate
        "science", "biotech" -> Icons.Default.Science
        "palette" -> Icons.Default.Palette
        "map" -> Icons.Default.Map
        "mosque", "menu_book" -> Icons.Default.MenuBook
        "translate" -> Icons.Default.Translate
        "font_download" -> Icons.Default.FontDownload
        "explore" -> Icons.Default.Explore
        else -> Icons.Default.AutoStories
    }
}
