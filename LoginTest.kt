package com.logintest.tests
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

object LoginScreen {
    // Using testTags is more stable than text or implementation details
    const val USERNAME_INPUT = "username_input_tag"
    const val PASSWORD_INPUT = "password_input_tag"
    const val LOGIN_BUTTON = "login_button_tag"
    const val ERROR_MESSAGE = "error_banner_tag"
    const val SUCCESS_MESSAGE = "success_banner_tag"

    class LoginTest {
        @get:Rule
        val composeTestRule = createComposeRule()

        @Test
        fun login_validCredentials_showsSuccess() {
            // Setup: Launch the app content
            // In a real app, it would be like composeTestRule.setContent { MyApp() }
            // Enter Valid Username
            composeTestRule.onNodeWithTag(LoginScreen.USERNAME_INPUT)
                .performTextInput("tomsmith")
            // Enter Valid Password
            composeTestRule.onNodeWithTag(LoginScreen.PASSWORD_INPUT)
                .performTextInput("SuperSecretPassword!")
            // Click Login
            // Using performScrollTo ensures visibility on small screens, preventing flakiness.
            composeTestRule.onNodeWithTag(LoginScreen.LOGIN_BUTTON)
                .performScrollTo()
                .performClick()
            // Assert Success
            composeTestRule.onNodeWithTag(LoginScreen.SUCCESS_MESSAGE)
                .assertIsDisplayed()
        }

        @Test
        fun login_invalidCredentials_showsError() {
            // Enter Invalid Username
            composeTestRule.onNodeWithTag(LoginScreen.USERNAME_INPUT)
                .performTextInput("bad_user")
            // Enter Invalid Password
            composeTestRule.onNodeWithTag(LoginScreen.PASSWORD_INPUT)
                .performTextInput("bad_password")
            // Click Login
            composeTestRule.onNodeWithTag(LoginScreen.LOGIN_BUTTON)
                .performScrollTo()
                .performClick()
            // Assert Error Message
            composeTestRule.onNodeWithTag(LoginScreen.ERROR_MESSAGE)
                .assertIsDisplayed()
            // Assert we are still on Login Screen
            composeTestRule.onNodeWithTag(LoginScreen.LOGIN_BUTTON)
                .assertIsDisplayed()
        }
    }

}
