package org.wikipedia.screens.feedconfigure

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen


/**
 * Класс, представляющий экран "Настройки ленты".
 * Наследуется от базового класса "BaseScreen"
 */
class FeedConfigureScreen : BaseScreen() {

    companion object {
        //RecyclerView "Настройки ленты"
        private val RECYCLERVIEW_ID = R.id.content_types_recycler

        //Чекбокс "Настройки ленты"
        private val CHECKBOX_ID = R.id.feed_content_type_checkbox

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса.
         */
        inline operator fun invoke(crossinline block: FeedConfigureScreen.() -> Unit) =
            FeedConfigureScreen().block()
    }

    // Matcher для идентификации элемента RecyclerView ленты "Настройки ленты"
    private val contentTypesRecyclerMatcher = Matchers.allOf(
        withId(RECYCLERVIEW_ID),
        instanceOf(RecyclerView::class.java),
    )

    init {
        recyclerViewHandler.recyclerViewMatcher = contentTypesRecyclerMatcher
    }

    /**
     * Проверяет все элементы CheckBox на состояние "выбран" в указанном RecyclerView,
     * с использованием переданного матчера для идентификации RecyclerView и идентификатора CheckBox.
     */
    fun checkAllCheckBoxesChecked() {
        val checkBoxId: Int = CHECKBOX_ID
        checkRecyclerViewForCheckedCheckBoxes(checkBoxId)
    }

    /**
     * Проверяет все элементы RecyclerView на наличие отмеченного состояния для указанного CheckBox.
     *
     * @param checkboxId Идентификатор ресурса CheckBox,
     * который будет проверяться на отмеченное состояние
     */
    private fun checkRecyclerViewForCheckedCheckBoxes(
        checkboxId: Int
    ) {
        val itemCount = recyclerViewHandler.getRecyclerViewItemCount()
        for (position in 0 until itemCount) {
            recyclerViewHandler.scrollRecyclerViewItemAtPosition(position)
            recyclerViewHandler.checkRecyclerViewItemAtPosition(
                position,
                checkboxId,
                ViewMatchers.isChecked()
            )
        }
    }
}