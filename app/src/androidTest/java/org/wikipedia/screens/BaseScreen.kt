package org.wikipedia.screens

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.hamcrest.Matcher
import org.wikipedia.components.MyRecyclerViewHandler
import org.wikipedia.components.MyTextViewChangeListener
import org.wikipedia.components.MyViewClickListener
import org.wikipedia.matchers.IntentMatchers
import org.wikipedia.matchers.ViewMatchers


/**
 * Базовый класс, представляющий экран в приложении.
 * Открытый класс, который может быть унаследован другими классами
 */
open class BaseScreen {
    val recyclerViewHandler = MyRecyclerViewHandler()
    val textViewChangeListener = MyTextViewChangeListener()
    val viewClickListener = MyViewClickListener()
    val intentMatchers = IntentMatchers()
    private val viewMatchers = ViewMatchers()

    /**
     * Проверяет, отображается ли элемент, идентифицированный с помощью переданного Matcherа.
     *
     * @param viewMatcher Matcher для идентификации элемента, который нужно проверить на отображение
     */
    fun checkViewIsDisplayed(viewMatcher: Matcher<View>) {
        onView(viewMatcher).check(matches(isCompletelyDisplayed()))
    }

    /**
     * Метод для проверки соответствия текста в представлении View заданному тексту.
     *
     * @param viewMatcher Matcher для определения представления View, в котором необходимо проверить текст
     * @param expectedText Текст, с которым необходимо сравнить текст в представлении (View)
     **/
    fun checkTextMatchesInView(viewMatcher: Matcher<View>, expectedText: String) {
        onView(viewMatcher).check(matches(withText(expectedText)))
    }

    /**
     * Проверяет, соответствует ли текст ошибки на ViewMatcher ожидаемому тексту ошибки из ресурсов.
     *
     * @param viewMatcher Matcher<View> для поиска соответствующей View
     * @param expectedErrorTextResId Ресурсный идентификатор ожидаемого текста ошибки
     */
    fun checkErrorTextInViewMatchesResId(
        viewMatcher: Matcher<View>,
        expectedErrorTextResId: Int
    ) {
        onView(viewMatcher).check(matches(viewMatchers.hasErrorText(expectedErrorTextResId)))
    }

    /**
     * Проверяет, соответствует ли цвет текста ошибки, отображаемой в заданном представлении,
     * заданному ресурсному идентификатору ожидаемого цвета.
     *
     * @param viewMatcher Matcher, представляющий проверяемое представление
     * @param expectedErrorTextColorResId Ресурсный идентификатор цвета текста ошибки,
     * которому должен соответствовать цвет текста в заданном представлении
     */
    fun checkErrorTextColorMatchesResId(
        viewMatcher: Matcher<View>,
        expectedErrorTextColorResId: Int
    ) {
        onView(viewMatcher).check(matches(viewMatchers.hasTextColor(expectedErrorTextColorResId)))
    }

    /**
     * Проверяет соответствие текста в указанной View переданному шаблону.
     *
     * @param viewMatcher Matcher<View> для поиска View, в которой нужно проверить текст
     * @param pattern Шаблон для поиска символов в тексте View
     */
    fun checkTextInViewMatchesPattern(viewMatcher: Matcher<View>, pattern: String) {
        onView(viewMatcher).check(matches(viewMatchers.hatPatternText(pattern)))
    }

    /**
     * Получает строку из ресурсов приложения по ее ресурсному идентификатору.
     *
     * @param stringResId Ресурсный идентификатор строки
     * @return Строка, соответствующая заданному ресурсному идентификатору
     */
    internal fun getStringResId(stringResId: Int): String =
        getInstrumentation().targetContext.getString(stringResId)
}