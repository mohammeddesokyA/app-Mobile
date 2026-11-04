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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.ChatMessage
import com.example.ui.components.AppAssetImage
import com.example.ui.theme.*

@Composable
fun LiveSessionScreen(
    messages: List<ChatMessage>,
    isMicMuted: Boolean,
    isCameraOn: Boolean,
    onToggleMic: () -> Unit,
    onToggleCamera: () -> Unit,
    onLeaveSession: () -> Unit,
    onSendMessage: (String) -> Unit,
    onHomeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var textInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(SurfaceWhite)
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        // Teacher Live Video Feed Area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.1f)
                .background(Color.Black)
        ) {
            // Live video asset / image
            AppAssetImage(
                assetName = "Live_Session_4e45efae.png",
                contentDescription = "بث مباشر للمعلمة",
                modifier = Modifier.fillMaxSize()
            )

            // Top Header Overlay
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Attendees Badge
                Surface(
                    color = Color.Black.copy(alpha = 0.55f),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "12+",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = SurfaceWhite
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = null,
                            tint = SurfaceWhite,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                // Course and Live Status
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "كورس الرسم للأطفال",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = SurfaceWhite
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(Color.Red, CircleShape)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "بث مباشر الآن",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = SurfaceWhite.copy(alpha = 0.9f)
                        )
                    }
                }

                // Home / Exit icon
                IconButton(
                    onClick = onHomeClick,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Home,
                        contentDescription = "الرئيسية",
                        tint = SurfaceWhite
                    )
                }
            }

            // Teacher Name Badge
            Surface(
                color = Color.Black.copy(alpha = 0.55f),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp, top = 20.dp)
            ) {
                Text(
                    text = "أ/ سارة أحمد",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SurfaceWhite,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }

            // PIP Student Video Preview (bottom left)
            Surface(
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(2.dp, SurfaceWhite),
                shadowElevation = 8.dp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .width(100.dp)
                    .height(130.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(if (isCameraOn) Color(0xFF334155) else Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCameraOn) {
                        AppAssetImage(
                            assetName = "Student_Profile_27f0e10a.png",
                            contentDescription = "فيديو الطالب",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.VideocamOff,
                            contentDescription = null,
                            tint = SurfaceWhite.copy(alpha = 0.5f)
                        )
                    }
                    Surface(
                        color = Color.Black.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 6.dp)
                    ) {
                        Text(
                            text = "أنا",
                            fontSize = 11.sp,
                            color = SurfaceWhite,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }

        // Bottom Chat & Controls Pane
        Surface(
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            color = SurfaceWhite,
            shadowElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                // Header of Chat
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "${messages.size + 20} رسالة",
                            fontSize = 12.sp,
                            color = MintGreen,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = "الدردشة التفاعلية",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Messages List
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(messages) { msg ->
                        LiveChatMessageRow(message = msg)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Chat Input Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            if (textInput.isNotBlank()) {
                                onSendMessage(textInput)
                                textInput = ""
                            }
                        },
                        modifier = Modifier
                            .size(46.dp)
                            .background(MintGreen, CircleShape)
                            .testTag("live_send_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "إرسال",
                            tint = SurfaceWhite,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    OutlinedTextField(
                        value = textInput,
                        onValueChange = { textInput = it },
                        placeholder = { Text("اكتب رسالة...", color = LightGray, fontSize = 13.sp) },
                        singleLine = true,
                        trailingIcon = {
                            IconButton(onClick = { textInput += " 😊" }) {
                                Icon(
                                    imageVector = Icons.Outlined.SentimentSatisfied,
                                    contentDescription = "رمز تعبيري",
                                    tint = LightGray
                                )
                            }
                        },
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MintGreen,
                            unfocusedBorderColor = CardBorder,
                            focusedContainerColor = Color(0xFFF8FAFC),
                            unfocusedContainerColor = Color(0xFFF8FAFC)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("live_chat_input")
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Bottom Call Action Controls
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Big Red Leave Button
                    Button(
                        onClick = onLeaveSession,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEF4444),
                            contentColor = SurfaceWhite
                        ),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .testTag("live_leave_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CallEnd,
                                contentDescription = null,
                                tint = SurfaceWhite,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "مغادرة",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    // Video Camera Toggle
                    IconButton(
                        onClick = onToggleCamera,
                        modifier = Modifier
                            .size(52.dp)
                            .background(
                                if (isCameraOn) Color(0xFFF1F5F9) else Color(0xFFFFECEF),
                                CircleShape
                            )
                            .testTag("live_camera_toggle")
                    ) {
                        Icon(
                            imageVector = if (isCameraOn) Icons.Default.Videocam else Icons.Default.VideocamOff,
                            contentDescription = "الكاميرا",
                            tint = if (isCameraOn) DarkNavy else Color(0xFFEF4444)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Mic Toggle
                    IconButton(
                        onClick = onToggleMic,
                        modifier = Modifier
                            .size(52.dp)
                            .background(
                                if (!isMicMuted) Color(0xFFF1F5F9) else Color(0xFFFFECEF),
                                CircleShape
                            )
                            .testTag("live_mic_toggle")
                    ) {
                        Icon(
                            imageVector = if (!isMicMuted) Icons.Default.Mic else Icons.Default.MicOff,
                            contentDescription = "المايكروفون",
                            tint = if (!isMicMuted) DarkNavy else Color(0xFFEF4444)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LiveChatMessageRow(message: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.Top
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(
                text = message.senderName,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = SoftPurple
            )
            Spacer(modifier = Modifier.height(2.dp))
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = if (message.isMe) Color(0xFFEBF5FF) else Color(0xFFF8FAFC),
                border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder)
            ) {
                Text(
                    text = message.text,
                    fontSize = 13.sp,
                    color = DarkNavy,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .size(34.dp)
                .background(Color(0xFFFEF3C7), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (message.isMe) "👧" else "👦",
                fontSize = 18.sp
            )
        }
    }
}
