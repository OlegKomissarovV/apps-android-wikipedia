package org.wikipedia.components

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.withId
import de.mrapp.android.util.view.ViewHolder
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.wikipedia.R


/**
 * Реализация интерфейса `RecyclerViewHandler` для управления RecyclerView.
 */
class MyRecyclerViewHandler : RecyclerViewHandler {
    companion object {
        // Сообщение об ошибке, возникающей при несоответствии элемента RecyclerView
        // на заданной позиции ожидаемому элементу
        private const val ERROR_MESSAGE_TARGET_VIEW_POSITION_MISMATCH =
            "The view at position %d with id %d does not match: %s"
    }

    override var recyclerViewMatcher: Matcher<View> = withId(R.id.recycler_view)

    /**
     * Выполняет клик на элементе в RecyclerView по указанной позиции.
     *
     * @param position Позиция элемента в RecyclerView
     */
    override fun clickRecyclerViewItemAtPosition(position: Int) {
        onView(recyclerViewMatcher).perform(
            actionOnItemAtPosition<ViewHolder>(
                position,
                click()
            )
        )
    }

    /**
     * Возвращает количество элементов в RecyclerView.
     *
     * @return Количество элементов в RecyclerView
     */
    override fun getRecyclerViewItemCount(): Int {
        var itemCount = 0
        onView(recyclerViewMatcher).check { view, _ ->
            val recyclerView = view as? RecyclerView
            recyclerView?.adapter?.let { adapter ->
                itemCount = adapter.itemCount
            }
        }
        return itemCount
    }

    /**
     * Выполняет клик на элементе в RecyclerView, соответствующем указанному Matcherу.
     *
     * @param targetMatcher Matcher для идентификации целевого элемента в RecyclerView
     */
    override fun clickRecyclerViewItem(targetMatcher: Matcher<View>) {
        onView(recyclerViewMatcher).perform(
            actionOnItem<ViewHolder>(targetMatcher, click())
        )
    }

    /**
     * Выполняет скроллинг к элементу в RecyclerView по указанной позиции.
     *
     * @param position Позиция элемента в RecyclerView
     */
    override fun scrollRecyclerViewItemAtPosition(position: Int) {
        onView(recyclerViewMatcher).perform(scrollToPosition<ViewHolder>(position))
    }

    /**
     * Проверяет элемент в RecyclerView на указанной позиции на соответствие указанным параметрам.
     *
     * @param position Позиция элемента в RecyclerView
     * @param targetViewId Идентификатор целевого элемента внутри ViewHolder
     * @param targetMatcher Matcher для проверки целевого элемента
     */
    override fun checkRecyclerViewItemAtPosition(
        position: Int,
        targetViewId: Int,
        targetMatcher: Matcher<View>
    ) {
        onView(recyclerViewMatcher).check(
            ViewAssertions.matches(
                matchRecyclerViewItemAtPosition(
                    position,
                    targetViewId,
                    targetMatcher
                )
            )
        )
    }


    /**
     * Создает Matcher для проверки элемента в RecyclerView на указанной позиции.
     *
     * @param position Позиция элемента в RecyclerView.
     * @param targetViewId Идентификатор целевого элемента внутри ViewHolder.
     * @param targetMatcher Matcher для проверки целевого элемента.
     * @return Созданный Matcher.
     */
    override fun matchRecyclerViewItemAtPosition(
        position: Int,
        targetViewId: Int,
        targetMatcher: Matcher<View>
    ): BoundedMatcher<View, RecyclerView> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText(
                    ERROR_MESSAGE_TARGET_VIEW_POSITION_MISMATCH.format(
                        position, targetViewId, targetMatcher
                    )
                )
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && targetMatcher.matches(
                    viewHolder.itemView.findViewById(targetViewId)
                )
            }
        }
    }

}