package org.wikipedia

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.rules.SkipOnBoardingRule
import org.wikipedia.screens.about.AboutScreen
import org.wikipedia.screens.main.BottomSheetMainScreen
import org.wikipedia.screens.main.MainScreen
import org.wikipedia.screens.settings.SettingsScreen


@RunWith(AndroidJUnit4::class)
class AboutTest {
    @get:Rule
    val rule = SkipOnBoardingRule()

    @Test
    fun sectionsContributorsTranslatorsLicenseDisplayedTest() {
        MainScreen {
            clickMore()
        }
        BottomSheetMainScreen {
            clickSettings()
        }
        SettingsScreen {
            clickAboutDescription()
        }
        AboutScreen {
            checkContributorsIsDisplayed()
            checkTranslatorsIsDisplayed()
            checkLicenseIsDisplayed()
        }
    }
}