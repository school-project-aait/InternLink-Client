package com.site7x24learn.internshipfrontend.ui.screens.admin



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.site7x24learn.internshipfrontend.ui.components.*
import com.site7x24learn.internshipfrontend.viewmodel.InternshipViewModel
import com.site7x24learn.internshipfrontend.viewmodel.InternshipEvent
import com.site7x24learn.internshipfrontend.viewmodel.FormField

@Composable
fun AddInternships(
    viewModel: InternshipViewModel = viewModel()
) {
    val state = viewModel.formState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(6.dp)
            .paddingFromBaseline(90.dp, 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderComponent(onLogout = { /* Handle logout */ })

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                FormSection(title = "Job Title") {
                    OutlinedTextField(
                        value = state.job,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.JOB, it)) },
                        label = { Text("Enter Job Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Company Name") {
                    OutlinedTextField(
                        value = state.company,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.COMPANY, it)) },
                        label = { Text("Enter company name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Requirements") {
                    OutlinedTextField(
                        value = state.requirements,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.REQUIREMENTS, it)) },
                        label = { Text("Enter Requirements") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Deadline") {
                    OutlinedTextField(
                        value = state.deadline,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.DEADLINE, it)) },
                        label = { Text("Enter deadline") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Category") {
                    CategoryDropdown(
                        selectedCategory = state.category,
                        onCategorySelected = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.CATEGORY, it)) }
                    )
                }

                FormButtons(
                    onSave = { viewModel.onEvent(InternshipEvent.Save) },
                    onCancel = { viewModel.onEvent(InternshipEvent.Cancel) }
                )
            }
        }
    }
}

@Preview
@Composable
fun AddInternshipsPreview() {
    AddInternships()
}
