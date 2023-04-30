package org.wikipedia.matchers

import android.content.res.Resources
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description


/**
 * Класс содержит набор предопределенных `BoundedMatcher` для сопоставления элементов интерфейса в UI-тестах.
 */
open class ViewMatchers {

    companion object {

        // Сообщение об ошибке при несоответствии текста поля ввода заданному шаблону
        private const val ERROR_MESSAGE_PATTERN_MISMATCH =
            "Field contains text '%s' that does not match the pattern '%s'"

        // Сообщение об ошибке при несоответствии цвета текста ошибки в поле ввода ожидаемому цвету
        private const val ERROR_MESSAGE_COLOR_MISMATCH =
            "TextInputLayout.errorCurrentTextColors is '%s' does not match with expected color '%s'"

        // Сообщение об ошибке при несоответствии текста поля ввода заданному тексту
        private const val ERROR_MESSAGE_TEXT_MISMATCH =
            "Field contains text '%s' that does not match the text '%s'"
    }

    /**
     * Возвращает `BoundedMatcher`, который проверяет,
     * соответствует ли текст `TextView` заданному шаблону.
     *
     * @param pattern шаблон, с которым должен соответствовать текст
     * @return `BoundedMatcher`, который проверяет,
     * соответствует ли текст `TextView` заданному шаблону
     */
    fun hatPatternText(pattern: String): BoundedMatcher<View, TextView> {
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            private var transformedText: String = ""
            private val patternToMatch: String = pattern
            override fun describeTo(description: Description) {
                description.appendText(
                    ERROR_MESSAGE_PATTERN_MISMATCH.format(
                        transformedText,
                        patternToMatch
                    )
                )
            }

            override fun matchesSafely(textView: TextView): Boolean {
                transformedText = textView.transformationMethod.getTransformation(
                    textView.text,
                    textView
                ).toString()
                return transformedText.replace(patternToMatch, "").isEmpty()
            }
        }
    }

    /**
     * Возвращает `BoundedMatcher`, который проверяет,
     * соответствует ли текст ошибки ввода в `TextInputLayout` ожидаемому тексту ошибки.
     *
     * @param expectedErrorTextResId идентификатор ресурса строки,
     * которая должна соответствовать тексту ошибки ввода
     * @return `BoundedMatcher`, который проверяет,
     * соответствует ли текст ошибки ввода в `TextInputLayout` ожидаемому тексту ошибки
     */
    fun hasErrorText(expectedErrorTextResId: Int): BoundedMatcher<View, TextInputLayout> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            private var viewErrorText: String = ""
            private var expectedErrorText: String = ""
            override fun describeTo(description: Description) {
                description.appendText(
                    ERROR_MESSAGE_TEXT_MISMATCH.format(
                        viewErrorText,
                        expectedErrorText,
                    )
                )
            }

            override fun matchesSafely(textInputLayout: TextInputLayout): Boolean {
                expectedErrorText =
                    getInstrumentation().targetContext.getString(expectedErrorTextResId)
                viewErrorText = textInputLayout.error.toString()
                return viewErrorText == expectedErrorText
            }
        }
    }

    /**
     * Возвращает `BoundedMatcher`, который проверяет,
     * соответствует ли цвет текста ошибки ввода в `TextInputLayout` ожидаемому цвету.
     *
     * @param expectedColorResId идентификатор ресурса цвета,
     * который должен соответствовать цвету текста ошибки ввода
     * @return `BoundedMatcher`, который проверяет,
     * соответствует ли цвет текста ошибки ввода в `TextInputLayout` ожидаемому цвету
     */
    fun hasTextColor(expectedColorResId: Int): BoundedMatcher<View, TextInputLayout> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            private var errorTextColor = 0
            private var expectedColor = colorRes(expectedColorResId)
            override fun describeTo(description: Description) {
                description.appendText(
                    ERROR_MESSAGE_COLOR_MISMATCH.format(
                        getHexColor(errorTextColor),
                        getHexColor(expectedColor),
                    )
                )
            }

            override fun matchesSafely(textInputLayout: TextInputLayout): Boolean {
                errorTextColor = textInputLayout.errorCurrentTextColors
                return errorTextColor == expectedColor
            }

            private fun colorRes(@ColorRes colorResId: Int, theme: Resources.Theme? = null): Int {
                return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    getInstrumentation().targetContext.resources.getColor(
                        colorResId,
                        theme
                    )
                } else {
                    getInstrumentation().targetContext.resources.getColor(colorResId)
                }
            }

            @Suppress("MagicNumber")
            private fun getHexColor(color: Int): String {
                return String.format("#%06X", 0xFFFFFF and color)
            }
        }
    }
}