package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AssignmentItem
import com.example.ui.components.ScreenHeader
import com.example.ui.theme.*

@Composable
fun AssignmentsScreen(
    assignment: AssignmentItem,
    onBackClick: () -> Unit,
    onSubmitSolution: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var hasSimulatedUpload by remember { mutableStateOf(assignment.isSubmitted) }
    var uploadedFileName by remember { mutableStateOf(assignment.submittedFileName ?: "solution_homework_maryam.pdf") }

    Scaffold(
        topBar = {
            ScreenHeader(
                title = "الواجبات المدرسية",
                onBackClick = onBackClick
            )
        },
        containerColor = BackgroundOffWhite,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 12.dp)
        ) {
            // Main Assignment Card
            Surface(
                shape = RoundedCornerShape(26.dp),
                color = SurfaceWhite,
                shadowElevation = 2.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Badge and Due Date
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Color(0xFFFEF3C7),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = assignment.subject,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF92400E),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }

                        Text(
                            text = "تاريخ التسليم: ${assignment.dueDate}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = SkyBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = assignment.title,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkNavy,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = assignment.instructions,
                        fontSize = 14.sp,
                        color = TextGray,
                        lineHeight = 22.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Section 1 Header: Teacher Uploaded File
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "المعلمة رفعت الواجب الحالي",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(18.dp)
                        .background(SoftPurple, RoundedCornerShape(2.dp))
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Downloadable File Card
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = SurfaceWhite,
                border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Download Button
                    Surface(
                        color = Color(0xFFF1F5F9),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { /* simulated download */ }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = null,
                                tint = DarkNavy,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "تحميل",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = DarkNavy
                            )
                        }
                    }

                    // File Name and Size
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = assignment.pdfFileName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkNavy
                        )
                        Text(
                            text = assignment.pdfFileSize,
                            fontSize = 12.sp,
                            color = TextGray
                        )
                    }

                    // PDF Icon
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .background(Color(0xFFFFECEF), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = null,
                            tint = Color(0xFFEF4444),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // Section 2 Header: Solution Upload Area
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "منطقة رفع الحل",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkNavy
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .height(18.dp)
                        .background(SkyBlue, RoundedCornerShape(2.dp))
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dashed Upload Box
            Surface(
                shape = RoundedCornerShape(24.dp),
                color = SurfaceWhite,
                border = androidx.compose.foundation.BorderStroke(1.5.dp, Color(0xFFC7D2FE)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Upload Icon Button
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .shadow(6.dp, CircleShape)
                            .background(Color(0xFFF5F3FF), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = null,
                            tint = SoftPurple,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = if (hasSimulatedUpload) "تم تجهيز ملف الحل: $uploadedFileName" else "صور حلك أو ارفع الملف هنا",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (hasSimulatedUpload) MintGreen else DarkNavy,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "يدعم الصور و ملفات PDF",
                        fontSize = 12.sp,
                        color = TextGray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Purple Upload Action Button
                    Button(
                        onClick = {
                            hasSimulatedUpload = true
                            onSubmitSolution(uploadedFileName)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (hasSimulatedUpload) MintGreen else SoftPurple,
                            contentColor = SurfaceWhite
                        ),
                        shape = RoundedCornerShape(18.dp),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .height(50.dp)
                            .testTag("upload_solution_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (hasSimulatedUpload) {
                                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SurfaceWhite, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(text = "تم الرفع بنجاح", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            } else {
                                Text(text = "رفع الحل بعد الإنجاز", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
