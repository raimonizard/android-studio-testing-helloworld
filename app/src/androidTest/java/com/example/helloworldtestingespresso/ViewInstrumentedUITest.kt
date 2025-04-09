package com.example.helloworldtestingespresso

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import kotlin.concurrent.thread

/**
 * Instrumented test, which will execute on an Android device.
 *
 * Aquests són tests de UI, que ens permeten validar que la interfície reacciona correctament a accions de l'usuari.
 * No són tests unitaris, perquè no proven funcions aïllades, sinó el comportament complet de la UI en execució.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class ViewInstrumentedUITest {

    // Setting del context dels test de UI: li especifiquem quina vista volem provar 'MainActivity'
    // @get:Rule és una anotació especial de JUnit que s’utilitza per aplicar una regla abans de cada test.
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Comprovem el nom del package de la app.
     * @author RIS
     */
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.helloworldtestingespresso", appContext.packageName)
    }

    /**
     * Comprovem el contingut inicial del composable 'initialText_id' abans de que l'usuari@ faci cap acció.
     * @author RIS
     */
    @Test
    fun checkInitialComposableValues() {
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello !")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("", substring = false)
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("0")
        composeTestRule.onNodeWithTag("checkBoxMulti_id").assertIsOff()
    }

    /**
     * Comprovem que el funcionament del TextField és correcte.
     * @author RIS
     */
    @Test
    fun checkOutlinedTextField(){

        var inputText = "Sean"
        var expectedText = "Hello Sean!"

        /// Comprovar que el Composable OutlinedTextField existeix
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertExists()

        /// Comprovar que el Composable OutlinedTextField és visible
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertIsDisplayed()

        /// Comprovar que el Composable OutlinedTextField és accionable
        composeTestRule.onNodeWithTag("outlinedTextField_id").assert(hasSetTextAction())

        // Preparem el test de UI:
        /// Fer click al Composable OutlinedTextField
        composeTestRule.onNodeWithTag("outlinedTextField_id").performClick()
        /// Escriure el contingut 'Android' dins del Composable OutlinedTextField
        composeTestRule.onNodeWithTag("outlinedTextField_id").performTextInput("Android")
        /// Eliminar el contingut del Composable OutlinedTextField
        composeTestRule.onNodeWithTag("outlinedTextField_id").performTextClearance()
        /// Escriure el contingut 'Sean' dins del Composable OutlinedTextField
        composeTestRule.onNodeWithTag("outlinedTextField_id").performTextInput(inputText)

        // Fem el test del resultat de l'estat de UI després de les accions anteriors:
        /// Comprovem el text del Composable etiquetat com a 'initialText_id' de dues maneres diferents
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello Sean!")
        composeTestRule.onNodeWithTag("initialText_id").assert(hasText(expectedText))

        // Comprovem el resultat dins del composable 'counterText_id'
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("0")
    }

    /**
     * Comprovem que el funcionament del botó de reset és correcte.
     * @author RIS
     */
    @Test
    fun checkResetButton() {
        val inputText = "Espresso Test"

        // Escrivim text al OutlinedTextField
        composeTestRule.onNodeWithTag("outlinedTextField_id").performTextInput(inputText)

        // Comprovem que el text s'ha actualitzat
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello Espresso Test!")

        // Fem clic al botó Reset
        composeTestRule.onNodeWithTag("resetButton_id").performClick()

        // Diferents maneres de comprovar que el text ha estat esborrat
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello !")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("", substring = false)

        // Comprovem que el composable counterText_id torna a tenir el valor inicial
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("0")

        // Comprovem que el composable checkBoxMulti_id torna a estar sense clickar
        composeTestRule.onNodeWithTag("checkBoxMulti_id").assertIsOff()
    }

    /**
     * Comprovem que el funcionament del botó d'increment és correcte.
     * @author RIS
     */
    @Test
    fun checkIncrementButton(){
        // Preparem test fent click al button:
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()

        // Comprovem l'efecte que ha provocat al composable 'counterText_id'
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("2")

        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()

        // Comprovem l'efecte que ha provocat al composable 'counterText_id'
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("6")
    }

    /**
     * Comprovem el funcionament del CheckBox Multi
     * @author RIS
     */
    @Test
    fun checkCheckBoxMulti(){
        // Preparem test fent click al button:
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()

        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("3")

        composeTestRule.onNodeWithTag("checkBoxMulti_id").performClick()
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("6")

        composeTestRule.onNodeWithTag("incrementButton_id").performClick()
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("8")

        composeTestRule.onNodeWithTag("checkBoxMulti_id").performClick()
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("4")

        composeTestRule.onNodeWithTag("checkBoxMulti_id").performClick()
        composeTestRule.onNodeWithTag("checkBoxMulti_id").performClick()
        composeTestRule.onNodeWithTag("checkBoxMulti_id").performClick()
        composeTestRule.onNodeWithTag("checkBoxMulti_id").performClick()
        composeTestRule.onNodeWithTag("counterText_id").assertTextEquals("4")
    }
}