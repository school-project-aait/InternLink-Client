// File: SeeDetailsScreen.kt
package com.site7x24learn.internshipfrontend.ui.screens.admin

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import java.io.File
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment

// Data class for application details
data class ApplicationDetail(
    val name: String,
    val gender: String,
    val email: String,
    val phone: String,
    val address: String,
    val linkedIn: String,
    val university: String,
    val degree: String,
    val graduationYear: String,
    val resumeUri: String // Use a String path if stored locally
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeDetailsScreen(
    name: String = "Jane Doe",
    gender: String = "Female",
    email: String = "jane@example.com",
    phone: String = "+123456789",
    address: String = "123 Main Street",
    linkedIn: String = "https://linkedin.com/in/janedoe",
    university: String = "Tech University",
    degree: String = "Computer Science",
    graduationYear: String = "2025",
    resumePath: String = "", // Should be provided when called
    onBack: () -> Unit = {} // Default empty callback
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Intern Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Personal Information Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Personal Information", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    DetailRow("Name", name)
                    DetailRow("Gender", gender)
                    DetailRow("Email", email)
                    DetailRow("Phone", phone)
                    DetailRow("Address", address)
                    DetailRow("LinkedIn", linkedIn)
                }
            }

            Spacer(Modifier.height(16.dp))

            // Educational Background Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Educational Background", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))
                    DetailRow("University", university)
                    DetailRow("Degree", degree)
                    DetailRow("Graduation Year", graduationYear)
                }
            }

            Spacer(Modifier.height(24.dp))

            // Open Resume Button (only shown if resumePath is provided)
            if (resumePath.isNotEmpty()) {
                Button(
                    onClick = {
                        val file = File(resumePath)
                        val uri: Uri = FileProvider.getUriForFile(
                            context,
                            "${context.packageName}.provider",
                            file
                        )

                        val intent = Intent(Intent.ACTION_VIEW).apply {
                            setDataAndType(uri, "application/pdf")
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }

                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Open Resume", color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("$label:", fontWeight = FontWeight.Bold)
        Text(value)
    }
    Spacer(modifier = Modifier.height(4.dp))
}