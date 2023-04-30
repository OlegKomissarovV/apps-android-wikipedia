package org.wikipedia.rules

import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.rules.RuleChain.outerRule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.wikipedia.main.MainActivity
import org.wikipedia.screens.onboarding.OnBoardingScreen


/**
 * Правило, которое кликает на кнопку "Пропустить" на экране OnBoarding перед выполнением тестов.
 * Оно обернуто в [TestRule], поэтому его метод apply будет вызван перед каждым тестом.
 */
class SkipOnBoardingRule : TestRule {
    // Правило, которое запускает MainActivity перед тестом
    private val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    override fun apply(base: Statement, description: Description): Statement {
        val ruleChain = outerRule(activityRule)
        return ruleChain.apply(object : Statement() {
            // Вызывает метод клика на экране OnBoarding, чтобы пропустить его
            override fun evaluate() {
                OnBoardingScreen {
                    clickOnboardSkip()
                }
                base.evaluate()
            }
        }, description)
    }
}

/**
 * Правило, которое инициализирует [Intents] перед выполнением тестов и освобождает их после выполнения тестов.
 * Оно обернуто в [TestRule], поэтому его метод apply будет вызван перед каждым тестом.
 */
class IntentsRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                // Инициализация фреймворка для работы с Intent-ами
                Intents.init()
                base.evaluate()
                // Освобождение ресурсов после теста
                Intents.release()
            }
        }
    }
}

/**
 * Правило, которое объединяет правила [SkipOnBoardingRule] и [IntentsRule], и добавляет к ним [ActivityScenarioRule].
 * Оно обернуто в [TestRule], поэтому его метод apply будет вызван перед каждым тестом.
 */
class SkipOnBoardingWithIntentsRule : TestRule {
    override fun apply(base: Statement, description: Description): Statement {
        return outerRule(SkipOnBoardingRule())
            .around(IntentsRule())
            .apply(base, description)
    }
}
