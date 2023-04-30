package org.wikipedia

import androidx.test.espresso.intent.Intents.intended
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.rules.SkipOnBoardingWithIntentsRule
import org.wikipedia.screens.main.BottomSheetMainScreen
import org.wikipedia.screens.main.MainScreen
import org.wikipedia.screens.settings.SettingsScreen


@RunWith(AndroidJUnit4::class)
class ExternalPageTransitionTest {
    @get:Rule
    val rule = SkipOnBoardingWithIntentsRule()

    @Test
    fun privacyPolicyBrowserTransitionTest() {
        MainScreen {
            clickMore()
        }
        BottomSheetMainScreen {
            clickSettings()
        }
        SettingsScreen {
            val intentMatcher =
                intentMatchers.hasIntentActionViewWithUriResId(R.string.privacy_policy_url)
            clickPrivacyPolicy()
            intended(intentMatcher)
        }
    }
}