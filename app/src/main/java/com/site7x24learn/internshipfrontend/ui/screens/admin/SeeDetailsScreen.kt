package com.site7x24learn.internshipfrontend.ui.screens.admin

// File: SeeDetailsScreen.kt


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import java.io.File
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.core.content.FileProvider
import androidx.compose.ui.Alignment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this // fix unresolved reference to context

        setContent {
            MaterialTheme {
                SeeDetailsScreen(
                    name = "Jane Doe",
                    gender = "Female",
                    email = "jane@example.com",
                    phone = "+123456789",
                    address = "123 Main Street",
                    linkedIn = "https://linkedin.com/in/janedoe",
                    university = "Tech University",
                    degree = "Computer Science",
                    graduationYear = "2025",
                    resumePath = "${context.filesDir}/resume_jane_doe.pdf",
                    onBack = {
                        Log.d("Navigation", "Back to Application Review")
                        // Add navigation code here later
                    }
                )
            }
        }
    }
}


// Data class
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
    name: String,
    gender: String,
    email: String,
    phone: String,
    address: String,
    linkedIn: String,
    university: String,
    degree: String,
    graduationYear: String,
    resumePath: String,
    onBack: () -> Unit
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
                    Text("Name: $name")
                    Text("Gender: $gender")
                    Text("Email: $email")
                    Text("Phone: $phone")
                    Text("Address: $address")
                    Text("LinkedIn: $linkedIn")
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
                    Text("University: $university")
                    Text("Degree: $degree")
                    Text("Graduation Year: $graduationYear")
                }
            }

            Spacer(Modifier.height(24.dp))

            // Open Resume Button
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

