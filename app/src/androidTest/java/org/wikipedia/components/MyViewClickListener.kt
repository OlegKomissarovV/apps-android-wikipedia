package org.wikipedia.components

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import org.hamcrest.Matcher


/**
 * Реализация интерфейса `OnClickListener` для выполнения клика на View.
 */
class MyViewClickListener : OnClickListener {
    /**
     * Выполняет клик на элементе FrameLayout, идентифицированном с помощью переданного Matcherа.
     *
     * @param viewMatcher Matcher для идентификации элемента, на котором нужно выполнить клик
     */
    override fun performClick(viewMatcher: Matcher<View>) {
        Espresso.onView(viewMatcher).perform(ViewActions.click())
    }
}