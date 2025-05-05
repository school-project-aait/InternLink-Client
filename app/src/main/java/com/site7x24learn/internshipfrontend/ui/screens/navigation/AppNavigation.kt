package com.site7x24learn.internshipfrontend.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.site7x24learn.internshipfrontend.ui.screens.admin.*
import com.site7x24learn.internshipfrontend.ui.screens.common.WaitingPage
import com.site7x24learn.internshipfrontend.ui.screens.home.HomePage
import com.site7x24learn.internshipfrontend.ui.screens.login.LoginScreen
import com.site7x24learn.internshipfrontend.ui.screens.login.SignUpScreen
import com.site7x24learn.internshipfrontend.ui.screens.student.StudentDashboardScreen
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing") {
            HomePage(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("admin_dashboard") {
            AdminDashboardScreen(navController)
        }
        composable("student_dashboard") {
            StudentDashboardScreen(navController)
        }
        composable("add_internship") {
            AddInternships(navController)
        }
        composable("waiting") {
            WaitingPage(navController)
        }
        composable("application_review") {
            ApplicationReviewScreen(navController)
        }

//        composable("application_review") {
//            val sampleApps = listOf(
//                Application(1, "Jane Doe", "pending"),
//                Application(2, "John Smith", "accepted"),
//                Application(3, "Mary Johnson", "rejected")
//            )
//            ApplicationReviewScreen(
//                applications = sampleApps,
//                onLogoutClick = { navController.navigate("landing") },
//                onSeeDetailsClick = {
//                    navController.navigate("see_details/${it.name}/${it.status}")
//                },
//                onStatusChange = { application, newStatus ->
//                    application.status = newStatus
//                }
//            )
//        }
        composable(
            route = "see_details/{name}/{status}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("status") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val status = backStackEntry.arguments?.getString("status") ?: ""

            SeeDetailsScreen(
                name = name,
                gender = "Female",
                email = "jane@example.com",
                phone = "+123456789",
                address = "123 Main Street",
                linkedIn = "https://linkedin.com/in/janedoe",
                university = "Tech University",
                degree = "Computer Science",
                graduationYear = "2025",
                resumePath = "/data/user/0/com.site7x24learn.internshipfrontend/files/resume_jane_doe.pdf", // Update if dynamic
                onBack = { navController.popBackStack() }
            )
        }
    }
}
