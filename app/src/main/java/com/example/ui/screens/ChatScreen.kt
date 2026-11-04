package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChatMessage
import com.example.ui.components.AppAssetImage
import com.example.ui.components.ScreenHeader
import com.example.ui.theme.*

@Composable
fun ChatScreen(
    teacherMessages: List<ChatMessage>,
    supportMessages: List<ChatMessage>,
    onBackClick: () -> Unit,
    onSendTeacherMessage: (String) -> Unit,
    onSendSupportMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf("teacher") } // "teacher" or "support"
    var messageText by remember { mutableStateOf("") }

    val currentMessages = if (selectedTab == "teacher") teacherMessages else supportMessages

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "المحادثة والدعم",
                onBackClick = onBackClick
            )
        },
        containerColor = SurfaceWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Two Switcher Tabs
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Support Tab
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (selectedTab == "support") Color(0xFFFFECEF) else Color(0xFFF8FAFC),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (selectedTab == "support") CoralPink else CardBorder
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { selectedTab = "support" }
                        .testTag("chat_tab_support")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "الدعم الفني",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == "support") CoralPink else TextGray
                        )
                    }
                }

                // Teacher Tab
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (selectedTab == "teacher") Color(0xFFFEF3C7) else Color(0xFFF8FAFC),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (selectedTab == "teacher") StarYellowDark else CardBorder
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { selectedTab = "teacher" }
                        .testTag("chat_tab_teacher")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "المعلمة",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (selectedTab == "teacher") DarkNavy else TextGray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Date Divider
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFF1F5F9)
                ) {
                    Text(
                        text = "اليوم",
                        fontSize = 12.sp,
                        color = TextGray,
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Messages List
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(currentMessages) { message ->
                    ChatMessageItem(message = message)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Chat Input Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Golden Send Button
                IconButton(
                    onClick = {
                        if (messageText.isNotBlank()) {
                            if (selectedTab == "teacher") onSendTeacherMessage(messageText)
                            else onSendSupportMessage(messageText)
                            messageText = ""
                        }
                    },
                    modifier = Modifier
                        .size(50.dp)
                        .background(StarYellow, CircleShape)
                        .testTag("chat_send_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "إرسال",
                        tint = DarkNavy,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                // Text Field
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    placeholder = { Text("اكتب رسالتك هنا...", color = LightGray, fontSize = 14.sp) },
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = StarAmber,
                        unfocusedBorderColor = CardBorder,
                        focusedContainerColor = Color(0xFFF8FAFC),
                        unfocusedContainerColor = Color(0xFFF8FAFC)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .testTag("chat_input_field")
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Attachment Clip Icon
                IconButton(
                    onClick = { messageText += " 📎 [ملف مرفق]" },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AttachFile,
                        contentDescription = "إرفاق ملف",
                        tint = CoralPink,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ChatMessageItem(message: ChatMessage) {
    if (message.isMe) {
        // Student / Parent Message (Right aligned in RTL, or left aligned depending on context)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(SoftPurple, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "أنا", fontSize = 12.sp, color = SurfaceWhite, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column(horizontalAlignment = Alignment.Start) {
                Surface(
                    shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomEnd = 18.dp, bottomStart = 4.dp),
                    color = SurfaceWhite,
                    border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
                ) {
                    Text(
                        text = message.text,
                        fontSize = 14.sp,
                        color = DarkNavy,
                        modifier = Modifier.padding(14.dp)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = message.time,
                    fontSize = 11.sp,
                    color = LightGray
                )
            }
        }
    } else {
        // Teacher / Support Message
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Bottom
        ) {
            Column(horizontalAlignment = Alignment.End) {
                Surface(
                    shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp, bottomStart = 18.dp, bottomEnd = 4.dp),
                    color = Color(0xFFFEF9C3)
                ) {
                    Text(
                        text = message.text,
                        fontSize = 14.sp,
                        color = DarkNavy,
                        modifier = Modifier.padding(14.dp)
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = message.time,
                    fontSize = 11.sp,
                    color = CoralPink
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFEF3C7)),
                contentAlignment = Alignment.Center
            ) {
                AppAssetImage(
                    assetName = "Teacher_Profile_34e5f675.png",
                    contentDescription = "صورة المعلمة",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
