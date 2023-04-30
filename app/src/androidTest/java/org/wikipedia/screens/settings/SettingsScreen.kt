package org.wikipedia.screens.settings

import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen


/**
 * Класс, представляющий экран "Настройки".
 * Наследуется от базового класса "BaseScreen"
 */
class SettingsScreen : BaseScreen() {
    companion object {
        //RecyclerView "Настройки"
        private val RECYCLERVIEW_ID = R.id.recycler_view

        //Поле "Настроить ленту"
        private val CONFIGURE_FEED_HEADING = R.string.preference_summary_customize_explore_feed

        //Поле "О приложении "Википедия"
        private val DESCRIPTION_HEADING = R.string.about_description

        //Поле "Политика конфиденциальности"
        private val PRIVACY_POLICY_HEADING = R.string.privacy_policy_description

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса.
         */
        inline operator fun invoke(crossinline block: SettingsScreen.() -> Unit) =
            SettingsScreen().block()
    }

    // Matcher для идентификации элемента RecyclerView ленты "Настройки"
    private val feedSettingsRecyclerViewMatcher = allOf(
        withId(RECYCLERVIEW_ID),
        instanceOf(RecyclerView::class.java),
    )

    // Matcher для идентификации кнопки "Политика конфиденциальности"
    private val privacyPolicyLinearLayoutMatcher = allOf(
        hasDescendant(withText(PRIVACY_POLICY_HEADING)),
        instanceOf(LinearLayout::class.java),
    )

    // Matcher для идентификации кнопки "Настроить ленту"
    private val configureFeedLinearLayoutMatcher = allOf(
        hasDescendant(withText(CONFIGURE_FEED_HEADING)),
        instanceOf(LinearLayout::class.java),
    )

    // Matcher для идентификации елемента "О приложении "Википедия"
    private val aboutAppTextViewMatcher = hasDescendant(withText(DESCRIPTION_HEADING))

    init {
        recyclerViewHandler.recyclerViewMatcher = feedSettingsRecyclerViewMatcher
    }

    /**
     * Выполняет клик на элементе "Настроить ленту" в элементе RecyclerView.
     */
    fun clickConfigureFeed() {
        recyclerViewHandler.clickRecyclerViewItem(configureFeedLinearLayoutMatcher)
    }

    /**
     * Выполняет клик на кнопке "О приложении "Википедия" в элементе RecyclerView.
     */
    fun clickAboutDescription() {
        recyclerViewHandler.clickRecyclerViewItem(aboutAppTextViewMatcher)
    }

    /**
     * Выполняет клик на кнопке "О приложении "Википедия" в элементе RecyclerView.
     */
    fun clickPrivacyPolicy() {
        recyclerViewHandler.clickRecyclerViewItem(privacyPolicyLinearLayoutMatcher)
    }
}