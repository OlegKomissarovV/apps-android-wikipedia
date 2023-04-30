package org.wikipedia.screens.onboarding

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.button.MaterialButton
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen


/**
 * Класс, представляющий экран онбординга приложения,
 * наследующий базовый класс экранов BaseScreen
 */
class OnBoardingScreen : BaseScreen() {
    companion object {
        //Кнопка "Пропустить"
        private val ONBOARD_SKIP_ID = R.id.fragment_onboarding_skip_button

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса.
         */
        inline operator fun invoke(crossinline block: OnBoardingScreen.() -> Unit) =
            OnBoardingScreen().block()
    }

    /**
     * Матчер, используемый для идентификации кнопки "Пропустить" на экране онбординга.
     */
    private val onboardSkipMaterialButtonMatcher = allOf(
        withId(ONBOARD_SKIP_ID),
        instanceOf(MaterialButton::class.java),
    )

    /**
     * Выполняет клик на кнопке "Пропустить" на экране онбординга.
     */
    fun clickOnboardSkip() {
        viewClickListener.performClick(onboardSkipMaterialButtonMatcher)
    }
}