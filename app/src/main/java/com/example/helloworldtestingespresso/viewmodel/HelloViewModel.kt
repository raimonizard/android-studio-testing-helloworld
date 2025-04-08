package com.example.helloworldtestingespresso.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HelloViewModel: ViewModel {
    private val _textValue: MutableLiveData<String>
    public val textValue: LiveData<String>

    private val _numberOfClicks: MutableLiveData<Int>
    public val numberOfClicks: LiveData<Int>

    /**
     * Constructor de la classe HelloViewModel
     * que inicialitzen els atributs
     */
    constructor() : super() {
        this._textValue = MutableLiveData<String>("")
        this.textValue = this._textValue
        this._numberOfClicks = MutableLiveData<Int>(0)
        this.numberOfClicks = this._numberOfClicks
    }

    fun setTextValue(text: String) {
        this._textValue.value = text
    }

    fun clickIncrement() {
        this._numberOfClicks.value = this._numberOfClicks.value?.plus(1)
    }

    fun resetValues() {
        this._textValue.value = ""
        this._numberOfClicks.value = 0
    }
}
