package upca.example.anylist.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import upca.example.anylist.TAG
import upca.example.anylist.models.User


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

        if (_uiState.value.email.isEmpty()){
            _uiState.value = _uiState.value.copy(
                error = "preencha o email"
            )
            return
        }
        if (_uiState.value.password.isEmpty()){
            _uiState.value = _uiState.value.copy(
                error = "preencha a password"
            )
            return
        }

        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(
            uiState.value.email.trim(),
            uiState.value.password.trim())
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    //val userDoc = hashMapOf(
                    //    "name" to user?.displayName,
                    //    "email" to user?.email,
                    //)
                    val userDoc = User(
                        name = user?.displayName,
                        email = user?.email
                    )
                    val db = Firebase.firestore
                    db.collection("users")
                        .document(user?.uid!!)
                        .set(userDoc)
                        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
                    onSuccess()
                    _uiState.value = _uiState.value.copy(
                        isLoading = false
                    )
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    _uiState.value = _uiState.value.copy(
                        error = "Authentication failed.",
                        isLoading = false
                    )
                }
            }

    }
}