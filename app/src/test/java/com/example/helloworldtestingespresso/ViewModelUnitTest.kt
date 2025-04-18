package com.example.helloworldtestingespresso

import com.example.helloworldtestingespresso.viewmodel.HelloViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Exemples de Unit Testing que validaran el funcionament dels mètodes
 * del ViewModel.
 * És independent de la UI, aquí farem tests del mètodes i atributs del ViewModel.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ViewModelUnitTest {
    /** Fem ús de @get:Rule per tal de que l'assignació 'val instantExecutorRule = InstantTaskExecutorRule()'
     * es faci just abans de cada Unit Testing.
     * InstantTaskExecutorRule() ens serveix per tal de que s'actualitzin els valors dels atributs de LiveData immediatament.
     */
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HelloViewModel

    /**
     * Assignem el viewModel abans d'executar cadascun dels tests.
     * Aquesta acció es farà just abans de cada Unit Test.
     * @author RIS
     */
    @Before
    fun setup() {
        viewModel = HelloViewModel()
    }

    /**
     * Dummy test per comprovar que el sistema de Unit Testing funciona.
     * @author RIS
     */
    @Test
    fun checkSumaTest() {
        assertEquals(4, 2 + 2)
    }

    /**
     * Unit testing de l'estat inicial dels valors dels atributs del ViewModel,
     * tal i com els inicialitza el seu constructor:
     * textValue, numberOfClicks, checkBoxMulti, counterValue.
     * @author RIS
     */
    @Test
    fun initialState() {
        assertSame("", viewModel.textValue.value)
        assertSame(0, viewModel.numberOfClicks.value)
        assertEquals(false, viewModel.checkBoxMulti.value)
        assertEquals(0, viewModel.counterValue.value)
    }

    /**
     * Test del valor de l'atribut 'textValue' després de fer-li un set
     * @author RIS
     */
    @Test
    fun checkSetTextValue() {
        viewModel.setTextValue("Maria Antonia")

        assertEquals("Maria Antonia", viewModel.textValue.value)
    }

    /**
     * Test del valor de l'atrbut 'numberOfClicks' després d'accionar el botó que l'incrementa
     * @author RIS
     */
    @Test
    fun checkClickIncrement() {
        viewModel.clickIncrement()
        assertEquals(1, viewModel.numberOfClicks.value)

        viewModel.clickIncrement()
        assertEquals(2, viewModel.numberOfClicks.value)
    }

    /**
     * Test del comportament del mètode toogleCheckBoxMulti() del ViewModel.
     * @author RIS
     */
    @Test
    fun checkToogleCheckBoxMulti(){
        viewModel.toggleCheckBoxMulti()
        assertEquals(true, viewModel.checkBoxMulti.value)

        viewModel.toggleCheckBoxMulti()
        assertEquals(false, viewModel.checkBoxMulti.value)
    }

    /**
     * Test dels valors dels atributs 'textValue' i 'numberOfClicks' després
     * de fer vàries accions usant els mètodes públics del ViewModel.
     * @author RIS
     */
    @Test
    fun checkResetValues() {
        viewModel.setTextValue("Test")
        viewModel.clickIncrement()
        viewModel.clickIncrement()
        viewModel.updateCounterValue()
        viewModel.toggleCheckBoxMulti()
        viewModel.resetValues()

        assertSame("", viewModel.textValue.value)
        assertSame(0, viewModel.numberOfClicks.value)
        assertEquals(false, viewModel.checkBoxMulti.value)
        assertEquals(0, viewModel.counterValue.value)
    }
}