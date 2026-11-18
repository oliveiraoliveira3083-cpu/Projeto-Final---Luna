package com.example.lunaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.lunaapp.ui.auth.PasswordScreen
import com.example.lunaapp.ui.contacts.ContactsScreen
import com.example.lunaapp.ui.moon.MoonPhaseScreen
import com.example.lunaapp.ui.theme.LunaAppTheme

sealed class AppScreen {
    object MoonPhase : AppScreen()
    object PasswordAuth : AppScreen()
    object Contacts : AppScreen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LunaAppTheme {
                var currentScreen by remember { mutableStateOf<AppScreen>(AppScreen.MoonPhase) }

                when (currentScreen) {
                    is AppScreen.MoonPhase -> {
                        MoonPhaseScreen(
                            onSettingsClick = {
                                currentScreen = AppScreen.PasswordAuth
                            }
                        )
                    }
                    is AppScreen.PasswordAuth -> {
                        PasswordScreen(
                            onSuccess = {
                                currentScreen = AppScreen.Contacts
                            },
                            onBack = {
                                currentScreen = AppScreen.MoonPhase
                            }
                        )
                    }
                    is AppScreen.Contacts -> {
                        ContactsScreen(
                            onBack = {
                                currentScreen = AppScreen.MoonPhase
                            }
                        )
                    }
                }
            }
        }
    }
}
