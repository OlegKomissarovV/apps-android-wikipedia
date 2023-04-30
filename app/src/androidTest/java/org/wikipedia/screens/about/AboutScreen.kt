package org.wikipedia.screens.about

import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.textview.MaterialTextView
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen


/**
 * Класс, представляющий экран "О приложении".
 * Наследуется от базового класса "BaseScreen"
 */
class AboutScreen : BaseScreen() {
    companion object {
        //Блок "Авторы"
        private val CONTRIBUTORS_HEADING = R.string.about_contributors_heading

        //Блок "Переводчики"
        private val TRANSLATORS_HEADING = R.string.about_translators_heading

        //Блок "Лицензия"
        private val LICENSE_HEADING = R.string.about_app_license_heading

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса.
         */
        inline operator fun invoke(crossinline block: AboutScreen.() -> Unit) =
            AboutScreen().block()
    }

    /**
     * Matcher для раздела "Авторы" на экране "О приложении".
     * Составляется из Matcher'ов, проверяющих наличие текста заголовка и наличие
     * одержания "Авторы" с указанным идентификатором
     */
    private val contributorsMaterialTextViewMatcher = allOf(
        withText(CONTRIBUTORS_HEADING),
        instanceOf(MaterialTextView::class.java),
    )

    /**
     * Matcher для раздела "Переводчики" на экране "О приложении".
     * Составляется из Matcher'ов, проверяющих наличие текста заголовка и наличие
     * содержания "Переводчики" с указанным идентификатором
     */
    private val translatorsMaterialTextViewMatcher = allOf(
        withText(TRANSLATORS_HEADING),
        instanceOf(MaterialTextView::class.java),
    )

    /**
     * Matcher для раздела "Лицензия" на экране "О приложении".
     * Составляется из Matcher'ов, проверяющих наличие текста заголовка и наличие
     * содержания "Лицензия" с указанным идентификатором
     */
    private val licenseMaterialTextViewMatcher = allOf(
        withText(LICENSE_HEADING),
        instanceOf(MaterialTextView::class.java),
    )

    /**
     * Проверяет, что раздел "Авторы" полностью отображается на экране "О приложении".
     */
    fun checkContributorsIsDisplayed() {
        checkViewIsDisplayed(contributorsMaterialTextViewMatcher)
    }

    /**
     * Проверяет, что раздел "Переводчики" полностью отображается на экране "О приложении".
     */
    fun checkTranslatorsIsDisplayed() {
        checkViewIsDisplayed(translatorsMaterialTextViewMatcher)
    }

    /**
     * Проверяет, что раздел "Лицензия" полностью отображается на экране "О приложении".
     */
    fun checkLicenseIsDisplayed() {
        checkViewIsDisplayed(licenseMaterialTextViewMatcher)
    }
}