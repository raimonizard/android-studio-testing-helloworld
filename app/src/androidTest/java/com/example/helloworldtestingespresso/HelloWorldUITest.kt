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
class HelloWorldUITest {

    // Setting del context dels test de UI: li especifiquem quina vista volem provar 'MainActivity'
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
    fun checkInitialTextValues() {
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello !")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("", substring = false)
    }

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
    }

    @Test
    fun checkResetButton() {
        val inputText = "Espresso Test"

        /*
        val viewModel = HelloViewModel()

        composeTestRule.setContent {
            MyView(myViewModel = viewModel)
        }*/

        // Escrivim text al TextField
        composeTestRule.onNodeWithTag("outlinedTextField_id").performTextInput(inputText)

        // Comprovem que el text s'ha actualitzat
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello Espresso Test!")

        // Fem clic al botó Reset
        composeTestRule.onNodeWithTag("resetButton_id").performClick()

        // Comprovem que el text ha estat esborrat
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello !")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("", substring = false)
    }

    @Test
    fun checkIncrementButton(){
        // Preparem test fent click al button:
        composeTestRule.onNodeWithTag("incrementButton_id").performClick()

        // Comprovem l'efecte que ha provocat:
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello 1!")

        composeTestRule.onNodeWithTag("incrementButton_id").performClick()

        // Comprovem l'efecte que ha provocat a 'initialText_id'
        composeTestRule.onNodeWithTag("initialText_id").assertTextEquals("Hello 12!")

        // Comprovem l'efecte que ha provocat a 'outlinedTextField_id'
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("12")
    }

}