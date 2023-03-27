package pl.grabla.lacknergraphqlconsumer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pl.grabla.lacknergraphqlconsumer.domain.DetailedCountry
import pl.grabla.lacknergraphqlconsumer.domain.GetCountriesUseCase
import pl.grabla.lacknergraphqlconsumer.domain.GetCountryUseCase
import pl.grabla.lacknergraphqlconsumer.domain.SimpleCountry

class CountriesViewModel(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code:String){
        viewModelScope.launch {
            _state.update {it.copy(
                selectedCountry = getCountryUseCase.execute(code)
                )
            }
        }
    }

    fun dismissCountryDialog(){
        _state.update {
            it.copy(
                selectedCountry = null
            )
        }
    }

    data class CountriesState(
        val countries: List<SimpleCountry> = emptyList(),
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )
}