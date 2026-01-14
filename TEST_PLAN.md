TEST_PLAN.md

## Team Members:
Camilo Castro - PO and Rodrigo Souza - QA (Manual and Automation)

## Manual & Exploratory Testing:

### Manual Test Cases

**Test Case 1: Verify successful login with valid credentials**
* **Title:** Successful Login - Valid Credentials
* **Steps:**
    1. Open the app: https://the-internet.herokuapp.com/login
    2. Tap on the "Username" field and enter: "tomsmith"
    3. Tap on the "Password" field and enter: "SuperSecretPassword!"
    4. Tap the "Login" button.

* **Expected Result:**
    * The user is navigated to the "Secure Area" (Home) screen.
    * A success message banner like "You logged into a secure area!" is displayed.

**Test Case 2: Verify error message with invalid user name**
* **Title:** Failed Login - Invalid User Name
* **Steps:**
    1. Open the app: https://the-internet.herokuapp.com/login
    2. Tap on the "Username" field and enter: "wronguser"
    3. Tap on the "Password" field and enter: "SuperSecretPassword!"
    4. Tap the "Login" button.

* **Expected Result:**
    * The user remains on the Login screen.
    * An error message banner "Your username is invalid!" is displayed at the top of the screen.

### Additional Test Coverage:
* Verify login functionality works when password visibility is toggled (Mask/Unmask).
* Verify login behavior with empty Username and Password fields (since the login button is always enabled).
* Verify UI responsiveness when rotating the device from Portrait to Landscape mode.

### Bug Report

**Title:** Login button is obscured by the software keyboard on small screen devices
* **Severity:** Major (Blocks core flow on specific devices)
* **Steps to reproduce:**
    1. Launch the app on a device with a small screen.
    2. Tap the "Password" input field to focus.
    3. Observe the bottom of the screen when the software keyboard appears.

* **Expected Result:** The content should scroll up, or the "Login" button should remain visible above the keyboard.
* **Actual Result:** The software keyboard covers the "Login" button, preventing the user from submitting the form without hiding the keyboard first.

*Title:** Username and Password fields lose input data after device rotation
* **Severity:** Medium
* **Steps to reproduce:**
    1. Open the application in Portrait mode.
    2. Tap on the "Username" field and enter text.
    3. Rotate the device to Landscape mode.
* **Expected Result:** The text tomsmith should remain populated in the "Username" field.
* **Actual Result:** The "Username" field is cleared/empty, we need to type the credentials again.
## Automation Strategy

### 1. What would you automate first and why?
I would prioritize the **Smoke Test Suite**, specifically the "Happy Path" (Valid Login) and the "Critical Negative" (Invalid Login).
* **Reasoning (ROI & Risk):** Login is a "gatekeeper" feature. If this fails, no other part of the app can be accessed, representing the highest risk to the business. Automating these provides the highest Return on Investment (ROI) by catching blocking issues immediately on every commit. This aligns with the base of the Test Pyramid (Unit/Integration/Critical UI flows).

### 2. How would you run these tests in CI using GitHub Actions?
To run these tests in a Continuous Integration, I would ask to our devops team to define a workflow in the CI then I would run the command to start it if it was not already automated.
* **High-Level Steps:**
    1.  Pull the repository code using the "actions/checkout" action.
    2.  Configure the environment for Android builds.
    3.  Enable KVM to allow the emulator to run efficiently.
    4.  Use a GitHub Action (like "reactivecircus/android-emulator-runner") to spin up a headless Android emulator.
    5.  Run the Gradle command "./gradlew connectedAndroidTest" to execute the Espresso/Compose tests.
    6.  Upload the test reports (XML/HTML) to the GitHub Actions run summary for review.