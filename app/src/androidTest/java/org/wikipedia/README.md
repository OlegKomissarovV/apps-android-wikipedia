## Project Description

### Wikipedia Android app

## Requirements:

## UI Test 1
### Verify default feed settings

1. Open the app
2. Tap "More" button
3. Tap "Settings" button
4. Tap "Customize the Explore feed" button
5. Verify that each checkbox is in the checked state

## UI TEST 2
### Verify blocks on the "About the Wikipedia app" screen

1. Open the app
2. Tap "More" button
3. Tap "Settings" button
4. Tap "About the Wikipedia app" button
5. Verify that the "Contributors", "Translators", and "License" blocks are displayed on the screen

## UI TEST 3
### Verify transition to browser

1. Open the app
2. Tap "More" button
3. Tap "Settings" button
4. Tap "Privacy policy" button
5. Verify that the browser selection window is displayed

## UI TEST 4
### Verify that the password is hidden when the eye icon is tapped twice
1. Open the app
2. Tap "More" button
3. Tap "Log in / Join Wikipedia" button
4. Enter any data in the password field
5. Tap the eye icon
6. Verify that the entered password is displayed
7. Tap the eye icon again
8. Verify that dots are displayed (password is hidden)

## UI TEST 5
### Verify password field validation

1. Open the app
2. Tap "More" button
3. Tap "Join Wikipedia" button
4. Fill out the form, entering a password that is less than 8 characters long
5. Tap "Next" button
6. Verify that the "Password" field has a red header
7. Verify that a red error message appears: "The password must be at least 8 characters"


## Environment:

- JDK 17 Oracle OpenJDK version 17.0.6
- Android Gradle plugin version AGP 8.0.0-rc01

## Kotlin:

- Kotlin version 1.8.20-release-327

## References

- `org.wikipedia.FeedConfigureTest.defaultFeedSettingsTest` – containing the test UI TEST 1

- `org.wikipedia.AboutTest.sectionsContributorsTranslatorsLicenseDisplayedTest` – containing the test UI TEST 2

- `org.wikipedia.ExternalPageTransitionTest.privacyPolicyBrowserTransitionTest` – containing the test UI TEST 3

- `org.wikipedia.CreateAccountTest.passwordVisibilityInLoginTest` – containing the test UI TEST 4

- `org.wikipedia.CreateAccountTest.passwordValidationFieldErrorMessageTest` – containing the test UI TEST 5

- `org.wikipedia.components.RecyclerViewHandler` – contains the interface for helper methods of working with RecyclerView

- `org.wikipedia.components.MyRecyclerViewHandler` – contains the implementation of the `RecyclerViewHandler` interface for managing RecyclerView

- `org.wikipedia.components.OnTextChangeListener` – contains the interface for handling text change events in a specific View

- `org.wikipedia.components.MyTextViewChangeListener` – contains the implementation of the `OnTextChangeListener` interface for entering text in a View

- `org.wikipedia.components.OnClickListener` – contains the interface for handling click events on a specific View

- `org.wikipedia.components.MyViewClickListener` – contains the implementation of the `OnClickListener` interface for performing a click on a View

- `org.wikipedia.matchers.IntentMatchers` – contains utilities for creating matchers for Intent filters

- `org.wikipedia.matchers.ViewMatchers` – contains utilities for creating matchers for matching interface elements in UI tests

- `org.wikipedia.rules.TestRules` – contains test rules that will be called before each test

- `org.wikipedia.screens.BaseScreen` – contains the base class representing a screen in the application

- `org.wikipedia.screens.onboarding.OnBoardingScreen` – contains the class representing the onboarding screen of the application

- `org.wikipedia.screens.main.MainScreen` – contains the class representing the main screen

- `org.wikipedia.screens.main.BottomSheetMainScreen` – contains the class representing the bottom sheet of the main screen

- `org.wikipedia.screens.feedconfigure.FeedConfigureScreen` – contains the class representing the feed configuration screen

- `org.wikipedia.screens.createaccount.CreateAccountScreen` - contains the class representing the account creation screen

- `org.wikipedia.screens.about.AboutScreen` - contains the class representing the about screen of the application
