package org.wikipedia.screens.main

import android.widget.FrameLayout
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen


/**
 * Класс, представляющий "Главный" экрана.
 * Унаследован от базового класса "BaseScreen"
 */
class MainScreen : BaseScreen() {
    companion object {
        //Поле "Еще"
        private val MORE_HEADING = R.string.nav_item_more

        //Блок "Еще"
        private val MORE_ID = R.id.nav_more_container

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса.
         */
        inline operator fun invoke(crossinline block: MainScreen.() -> Unit) =
            MainScreen().block()
    }

    // Matcher для идентификации элемента "иконки меню" с элементом текста "Еще"
    private val moreFrameLayoutMatcher = allOf(
        withId(MORE_ID),
        hasDescendant(withText(MORE_HEADING)),
        instanceOf(FrameLayout::class.java),
    )

    /**
     * Выполняет клик на элементе "иконки меню" с соседним текстом "Еще".
     */
    fun clickMore() {
        viewClickListener.performClick(moreFrameLayoutMatcher)
    }
}