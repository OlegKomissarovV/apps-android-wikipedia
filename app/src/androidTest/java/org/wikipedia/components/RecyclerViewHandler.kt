package org.wikipedia.components

import android.view.View
import org.hamcrest.Matcher


/**
 * Интерфейс для вспомогательных методов работы с RecyclerView.
 */
interface RecyclerViewHandler {
    var recyclerViewMatcher: Matcher<View>

    /**
     * Выполняет клик на элементе в RecyclerView по указанной позиции.
     */
    fun clickRecyclerViewItemAtPosition(position: Int)

    /**
     * Возвращает количество элементов в RecyclerView.
     */
    fun getRecyclerViewItemCount(): Int

    /**
     * Выполняет клик на элементе в RecyclerView, соответствующем указанному матчеру.
     */
    fun clickRecyclerViewItem(targetMatcher: Matcher<View>)

    /**
     * Выполняет скроллинг к элементу в RecyclerView по указанной позиции.
     */
    fun scrollRecyclerViewItemAtPosition(position: Int)

    /**
     * Проверяет элемент в RecyclerView на указанной позиции на соответствие указанным параметрам.
     */
    fun checkRecyclerViewItemAtPosition(
        position: Int,
        targetViewId: Int,
        targetMatcher: Matcher<View>
    )

    /**
     * Создает матчер для проверки элемента в RecyclerView на указанной позиции.
     */
    fun matchRecyclerViewItemAtPosition(
        position: Int,
        targetViewId: Int,
        targetMatcher: Matcher<View>
    ): Matcher<View>


}
