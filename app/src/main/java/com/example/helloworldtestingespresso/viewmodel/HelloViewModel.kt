package com.example.helloworldtestingespresso.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelloViewModel: ViewModel {
    // Atributs
    private val _textValue: MutableLiveData<String>
    public val textValue: LiveData<String>

    private val _numberOfClicks: MutableLiveData<Int>
    public val numberOfClicks: LiveData<Int>

    private val _checkBoxMulti: MutableLiveData<Boolean>
    public val checkBoxMulti: LiveData<Boolean>

    private val _counterValue: MutableLiveData<Int>
    public val counterValue: LiveData<Int>
    
    /**
     * Constructor de la classe HelloViewModel
     * que inicialitzen els atributs
     */
    constructor() : super() {
        this._textValue = MutableLiveData<String>("")
        this.textValue = this._textValue

        this._numberOfClicks = MutableLiveData<Int>(0)
        this.numberOfClicks = this._numberOfClicks

        this._checkBoxMulti = MutableLiveData<Boolean>(false)
        this.checkBoxMulti = this._checkBoxMulti

        this._counterValue = MutableLiveData<Int>(0)
        this.counterValue = this._counterValue
    }

    fun setTextValue(text: String) {
        this._textValue.value = text
    }

    fun clickIncrement() {
        this._numberOfClicks.value = this._numberOfClicks.value?.plus(1)
        updateCounterValue()
    }

    fun updateCounterValue(){
        if (this._checkBoxMulti.value == false){
            this._counterValue.value = this._numberOfClicks.value
        }else{
            this._counterValue.value = this._numberOfClicks.value!! * 2
        }
    }

    fun toggleCheckBoxMulti(){
        this._checkBoxMulti.value = !(this._checkBoxMulti.value)!!
        updateCounterValue()
    }

    fun resetValues() {
        this._textValue.value = ""
        this._numberOfClicks.value = 0
        this._checkBoxMulti.value = false
        this._counterValue.value = 0
    }
}
