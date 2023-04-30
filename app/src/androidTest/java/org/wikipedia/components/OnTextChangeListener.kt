package org.wikipedia.components

import android.view.View
import org.hamcrest.Matcher


/**
 * Интерфейс для обработки событий изменения текста в определенном View.
 */
interface OnTextChangeListener {
    /**
     * Выполняет ввод текста в указанное View, соответствующее переданному матчеру.
     */
    fun typeText(viewMatcher: Matcher<View>, inputText: String)
}