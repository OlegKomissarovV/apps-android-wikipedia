package org.wikipedia.components

import android.view.View
import org.hamcrest.Matcher


/**
 * Интерфейс для обработки событий клика на определенном View.
 */
interface OnClickListener {
    /**
     * Выполняет действие клика на View, соответствующему переданному матчеру.
     */
    fun performClick(viewMatcher: Matcher<View>)
}