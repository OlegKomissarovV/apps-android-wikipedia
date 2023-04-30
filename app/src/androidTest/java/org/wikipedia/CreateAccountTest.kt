package org.wikipedia

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.rules.SkipOnBoardingRule
import org.wikipedia.screens.createaccount.CreateAccountScreen
import org.wikipedia.screens.main.BottomSheetMainScreen
import org.wikipedia.screens.main.MainScreen


@RunWith(AndroidJUnit4::class)
class CreateAccountTest {
    @get:Rule
    val rule = SkipOnBoardingRule()

    @Test
    fun passwordVisibilityInLoginTest() {
        MainScreen {
            clickMore()
        }
        BottomSheetMainScreen {
            clickLogin()
        }
        CreateAccountScreen {
            val password = R.id.forgot_password_link
            inputPassword(getStringResId(password))
            clickInputEndIconImageButton()
            checkInputPassword(getStringResId(password))
            clickInputEndIconImageButton()
            val pattern = "\u2022"
            checkPasswordTextChanged(pattern)
        }
    }

    @Test
    fun passwordValidationFieldErrorMessageTest() {
        MainScreen {
            clickMore()
        }
        BottomSheetMainScreen {
            clickLogin()
        }
        CreateAccountScreen {
            val userName = "TestMyName"
            inputUserName(userName)
            val password = R.id.forgot_password_link
            inputPassword(getStringResId(password))
            clickCreateAccountSubmitButton()
            val expectedColorResId = R.color.red700
            val expectedErrorTextResId = R.string.create_account_password_error
            checkPasswordErrorTextColor(expectedColorResId)
            checkPasswordErrorText(expectedErrorTextResId)
        }
    }
}