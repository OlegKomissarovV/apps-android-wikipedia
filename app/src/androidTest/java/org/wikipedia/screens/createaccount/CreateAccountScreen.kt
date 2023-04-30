package org.wikipedia.screens.createaccount

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.material.button.MaterialButton
import com.google.android.material.internal.CheckableImageButton
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.wikipedia.R
import org.wikipedia.screens.BaseScreen
import org.wikipedia.views.PlainPasteEditText


/**
 * Класс, представляющий экран создания аккаунта.
 * Унаследован от базового класса "BaseScreen"
 */
class CreateAccountScreen : BaseScreen() {
    companion object {
        //Кнопка "Далее"
        private val CREATE_ACCOUNT_SUBMIT_BUTTON_ID = R.id.create_account_submit_button

        //Поле ввода "Пароль"
        private val CREATE_ACCOUNT_PASSWORD_INPUT_ID = R.id.create_account_password_input

        //Поле ввода "Имя участника"
        private val CREATE_ACCOUNT_USERNAME_INPUT_ID = R.id.create_account_username

        //Кнопка окончания ввода в поле пароля ("глазик")
        private val PASSWORD_INPUT_END_ICON_BUTTON_ID =
            com.google.android.material.R.id.text_input_end_icon

        /**
         * Функция для создания экземпляра класса и выполнения предоставленного блока кода.
         *
         * @param block Блок кода, который будет выполнен на созданном экземпляре класса
         */
        inline operator fun invoke(crossinline block: CreateAccountScreen.() -> Unit) =
            CreateAccountScreen().block()
    }

    // Matcher для идентификации кнопки "Далее" на экране создания учетной записи
    private val createAccountSubmitMaterialButtonMatcher: Matcher<View> =
        allOf(
            withId(CREATE_ACCOUNT_SUBMIT_BUTTON_ID),
            instanceOf(MaterialButton::class.java),
        )

    // Matcher для идентификации поля ввода имени участника
    private val userNamePlainPasteEditTextMatcher = allOf(
        instanceOf(PlainPasteEditText::class.java),
        isDescendantOfA(
            withId(CREATE_ACCOUNT_USERNAME_INPUT_ID),
        ),
    )

    // Matcher для идентификации поля ввода пароля
    private val passwordInputPlainPasteEditTextMatcher = allOf(
        instanceOf(PlainPasteEditText::class.java),
        isDescendantOfA(
            withId(CREATE_ACCOUNT_PASSWORD_INPUT_ID),
        ),
    )

    // Matcher для родительского элемента поля ввода пароля TextInputLayout
    private val passwordTextInputLayoutMatcher = allOf(
        instanceOf(TextInputLayout::class.java),
        withId(CREATE_ACCOUNT_PASSWORD_INPUT_ID),
    )

    // Matcher для идентификации кнопки окончания ввода в поле пароля ("глазик")
    private val inputEndPasswordCheckableImageButtonMatcher = allOf(
        withId(PASSWORD_INPUT_END_ICON_BUTTON_ID),
        isDescendantOfA(
            withId(CREATE_ACCOUNT_PASSWORD_INPUT_ID),
        ),
        instanceOf(CheckableImageButton::class.java),
    )

    /**
     * Вводит текст в поле ввода пароля.
     * @param inputText Текст, который требуется ввести в поле ввода пароля
     */
    fun inputPassword(inputText: String) {
        textViewChangeListener.typeText(passwordInputPlainPasteEditTextMatcher, inputText)
    }

    /**
     * Вводит текст в поле ввода пароля.
     * @param inputText Текст, который требуется ввести в поле имени участника
     */
    fun inputUserName(inputText: String) {
        textViewChangeListener.typeText(userNamePlainPasteEditTextMatcher, inputText)
    }

    /**
     * Проверяет соответствие введенного текста в поле ввода пароля с заданным текстом.
     *
     * @param expectedText Текст, с которым сравнивается введенный текст
     */
    fun checkInputPassword(expectedText: String) {
        checkTextMatchesInView(passwordInputPlainPasteEditTextMatcher, expectedText)
    }

    /**
     * Выполняет клик на кнопке окончания ввода в поле пароля ("глазик").
     */
    fun clickInputEndIconImageButton() {
        viewClickListener.performClick(inputEndPasswordCheckableImageButtonMatcher)
    }

    /**
     * Выполняет клик на кнопке "Далее" на экране создания учетной записи.
     */
    fun clickCreateAccountSubmitButton() {
        viewClickListener.performClick(createAccountSubmitMaterialButtonMatcher)
    }

    /**
     * Проверяет изменение текста в поле ввода пароля и соответствие этого текста указанному шаблону
     * с использованием точек для отображения скрытого текста.
     *
     */
    fun checkPasswordTextChanged(pattern: String) {
        checkTextInViewMatchesPattern(passwordInputPlainPasteEditTextMatcher, pattern)
    }

    /**
     * Метод для проверки соответствия текста ошибки при создании аккаунта
     * с ожидаемым текстом ошибки для поля пароля.
     *
     * @param expectedErrorTextResId Ресурсный идентификатор ожидаемой строки с текстом ошибки для поля пароля
     */
    fun checkPasswordErrorText(expectedErrorTextResId: Int) {
        checkErrorTextInViewMatchesResId(passwordTextInputLayoutMatcher, expectedErrorTextResId)
    }

    /**
     * Метод для проверки соответствия цвета текста ошибки при создании аккаунта
     * с ожидаемым цветом для поля пароля.
     *
     * @param expectedColorResId Ресурсный идентификатор ожидаемого цвета текста ошибки для поля пароля
     */
    fun checkPasswordErrorTextColor(expectedColorResId: Int) {
        checkErrorTextColorMatchesResId(passwordTextInputLayoutMatcher, expectedColorResId)
    }
}