package org.wikipedia.components

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import org.hamcrest.Matcher


/**
 * Реализация интерфейса `OnTextChangeListener` для ввода текста в View.
 */
class MyTextViewChangeListener : OnTextChangeListener {
    /**
     * Выполняет ввод текста в указанное View с использованием действия typeText() из Espresso.
     *
     * @param viewMatcher Matcher<View> для поиска целевого View, в которое нужно ввести текст
     * @param inputText Текст, который нужно ввести в указанное View
     */
    override fun typeText(viewMatcher: Matcher<View>, inputText: String) {
        Espresso.onView(viewMatcher)
            .perform(ViewActions.typeText(inputText), ViewActions.closeSoftKeyboard())
    }
}