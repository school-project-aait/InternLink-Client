package com.site7x24learn.internshipfrontend.ui.screens.admin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationReviewScreen(navController: NavHostController) {
    val sampleApps = remember {
        mutableStateListOf(
            Application(1, "Jane Doe", "pending"),
            Application(2, "John Smith", "accepted"),
            Application(3, "Mary Johnson", "rejected")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color(0xFF2196F3))) {
                            append("Intern")
                        }
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("Link")
                        }
                    })
                },
                actions = {
                    Button(
                        onClick = { navController.navigate("landing") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
                    ) {
                        Text("Logout", color = Color.White)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(sampleApps) { app ->
                    ApplicationCard(
                        application = app,
                        onStatusChange = { newStatus ->
                            val index = sampleApps.indexOfFirst { it.id == app.id }
                            if (index != -1) sampleApps[index] = app.copy(status = newStatus)
                        },
                        onSeeDetails = {
                            navController.navigate("see_details/${app.name}/${app.status}")
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("admin_dashboard") },
                modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text("Back to Dashboard", color = Color.White)
            }
        }
    }
}

@Composable
fun ApplicationCard(
    application: Application,
    onStatusChange: (String) -> Unit,
    onSeeDetails: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedStatus by remember { mutableStateOf(application.status) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${application.name}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Status: $selectedStatus", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))
            Box {
                OutlinedButton(onClick = { expanded = true }) {
                    Text("Action")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Accept") },
                        onClick = {
                            selectedStatus = "accepted"
                            expanded = false
                            onStatusChange("accepted")
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Reject") },
                        onClick = {
                            selectedStatus = "rejected"
                            expanded = false
                            onStatusChange("rejected")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "See Details",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onSeeDetails() }
            )
        }
    }
}

data class Application(
    val id: Int,
    val name: String,
    var status: String
)

