package upca.example.anylist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isLoading : Boolean = false,
    val error : String? = null
)

class LoginViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(LoginViewState())
    val uiState : StateFlow<LoginViewState> = _uiState

    fun updateEmail(email : String){
        _uiState.value = _uiState.value.copy(
            email = email
        )
    }

    fun updatePassword(password : String){
        _uiState.value = _uiState.value.copy(
            password = password
        )
    }

    fun login(onSuccess : ()-> Unit){
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        onSuccess()
    }
}