package org.wikipedia.screens.main

import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen


/**
 * Класс, представляющий нижнюю панель "Главной" экрана.
 * Унаследован от базового класса "BaseScreen"
 */
class BottomSheetMainScreen : BaseScreen() {
    companion object {
        //Поле "Настройки"
        private val SETTINGS_HEADING = R.string.settings_item_preferences

        //Поле "Войти в Википедию"
        private val LOGIN_ID = R.id.main_drawer_login_button

        //Компонент, содержащий поле "Настройки"
        private val DRAWER_SETTINGS_ID = R.id.main_drawer_settings_container

        //Компонент, содeржащий поле "Войти в Википедию"
        private val DRAWER_ACCOUNT_ID = R.id.main_drawer_account_container

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса.
         */
        inline operator fun invoke(crossinline block: BottomSheetMainScreen.() -> Unit) =
            BottomSheetMainScreen().block()
    }

    // Matcher для идентификации элемента с текстом "Настройки"
    private val settingsLinearLayoutMatcher = allOf(
        withId(DRAWER_SETTINGS_ID),
        hasDescendant(withText(SETTINGS_HEADING)),
        instanceOf(LinearLayout::class.java),
    )

    // Matcher для идентификации элемента с текстом "Войти в Википедию"
    private val loginLinearLayoutMatcher = allOf(
        withId(DRAWER_ACCOUNT_ID),
        hasDescendant(withId(LOGIN_ID)),
        instanceOf(LinearLayout::class.java),
    )

    /**
     * Выполняет клик на элементе с текстом "Настройки".
     */
    fun clickSettings() {
        viewClickListener.performClick(settingsLinearLayoutMatcher)
    }

    /**
     * Выполняет клик на элементе с текстом "Войти в Википедию".
     */
    fun clickLogin() {
        viewClickListener.performClick(loginLinearLayoutMatcher)
    }
}