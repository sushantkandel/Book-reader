package com.example.bookReader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookReader.screens.ReaderSplashScreen
import com.example.bookReader.screens.home.HomeScreen
import com.example.bookReader.screens.login.LoginScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreen.SplashScreen.name) {

        composable(ReaderScreen.SplashScreen.name) {

            ReaderSplashScreen(navController = navController)

        }

        composable(ReaderScreen.LoginScreen.name) {

            LoginScreen(navController = navController)

        }

        composable(ReaderScreen.ReaderHomeScreen.name) {

            HomeScreen(navController = navController)

        }

    }

}