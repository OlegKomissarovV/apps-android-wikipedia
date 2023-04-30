package org.wikipedia.matchers

import android.content.Intent
import android.net.Uri
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.hamcrest.Matchers

/**
 * Класс, содержащий утилиты для создания matchers для Intent-фильтров.
 */
open class IntentMatchers {
    /**
     * Создает Matcher<Intent> для ACTION_VIEW с URI из заданного ресурса.
     *
     * @param UriResId Ресурсный идентификатор, содержащий URI для ACTION_VIEW
     * @return Matcher<Intent>, который соответствует URI из заданного ресурса
     */
    internal fun hasIntentActionViewWithUriResId(UriResId: Int): Matcher<Intent> {
        val uriString = InstrumentationRegistry.getInstrumentation()
            .targetContext.getString(UriResId)
        return Matchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_VIEW),
            IntentMatchers.hasData(Uri.parse(uriString))
        )
    }
}