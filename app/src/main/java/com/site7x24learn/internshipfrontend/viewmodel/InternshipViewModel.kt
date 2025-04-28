package com.site7x24learn.internshipfrontend.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InternshipViewModel : ViewModel() {
    var formState by mutableStateOf(FormState())
        private set

    fun onEvent(event: InternshipEvent) {
        when(event) {
            is InternshipEvent.FieldUpdated -> updateField(event.field, event.value)
            InternshipEvent.Save -> saveForm()
            InternshipEvent.Cancel -> resetForm()
        }
    }

    private fun updateField(field: FormField, value: String) {
        formState = formState.copy(
            when(field) {
                FormField.JOB -> formState.copy(job = value)
                FormField.COMPANY -> formState.copy(company = value)
                FormField.REQUIREMENTS -> formState.copy(requirements = value)
                FormField.DEADLINE -> formState.copy(deadline = value)
                FormField.CATEGORY -> formState.copy(category = value)
            }.toString()
        )
    }

    private fun saveForm() {
        // Add save logic here
    }

    private fun resetForm() {
        formState = FormState()
    }
}

data class FormState(
    val job: String = "",
    val company: String = "",
    val requirements: String = "",
    val deadline: String = "",
    val category: String = ""
)

sealed class InternshipEvent {
    data class FieldUpdated(val field: FormField, val value: String) : InternshipEvent()
    object Save : InternshipEvent()
    object Cancel : InternshipEvent()
}

enum class FormField {
    JOB, COMPANY, REQUIREMENTS, DEADLINE, CATEGORY
}