package com.site7x24learn.internshipfrontend.ui.screens.admin
// ApplicationReview.kt
//package com.yourpackage.admin.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// Data class for application information
data class Application(
    val id: String,
    val name: String,
    val email: String,
    val status: String // "Pending", "Approved", or "Rejected"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationReviewScreen(
    navController: NavController
) {
    // Sample data - replace with your actual data source
    val applications = remember {
        listOf(
            Application(
                id = "1",
                name = "John Doe",
                email = "john.doe@example.com",
                status = "Pending"
            ),
            Application(
                id = "2",
                name = "Jane Smith",
                email = "jane.smith@example.com",
                status = "Pending"
            ),
            Application(
                id = "3",
                name = "Robert Johnson",
                email = "robert.j@example.com",
                status = "Pending"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // InternLink logo - fixed syntax
                        Text(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color(0xFF2196F3), fontWeight = FontWeight.Bold)) {
                                    append("Intern")
                                }
                                withStyle(style = SpanStyle(color = Color.Black, fontWeight = FontWeight.Bold)) {
                                    append("Link")
                                }
                            },
                            fontSize = 20.sp
                        )
                    }
                },
                actions = {
                    // Logout button
                    Button(
                        onClick = { /* Handle logout */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Logout")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Logout")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            // Back to Dashboard button
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { navController.popBackStack() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Text("Back to Dashboard")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Application cards
            applications.forEach { application ->
                ApplicationCard(
                    application = application,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun ApplicationCard(
    application: Application,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = application.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = application.email,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Status: ${application.status}",
                        fontSize = 14.sp,
                        color = when (application.status) {
                            "Approved" -> Color.Green
                            "Rejected" -> Color.Red
                            else -> Color(0xFF2196F3) // Blue for pending
                        }
                    )
                }

                // Dropdown menu
                Box {
                    Button(
                        onClick = { expanded = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Text("Action")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Actions")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Accept") },
                            onClick = {
                                expanded = false
                                // Handle accept action
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Reject") },
                            onClick = {
                                expanded = false
                                // Handle reject action
                            }
                        )
                    }
                }
            }
        }
    }
}