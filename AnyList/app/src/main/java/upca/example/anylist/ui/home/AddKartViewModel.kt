package upca.example.myshoppinglist.ui.home.kart

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import upca.example.anylist.models.Kart


data class AddKartViewState (
    var kartName : String = "",
    var error : String? = null,
    var isLoading : Boolean = false
    )

class AddKartViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AddKartViewState())
    val uiState: StateFlow<AddKartViewState> = _uiState.asStateFlow()

    fun editKartName(name: String){
        _uiState.value = _uiState.value.copy(
            kartName = name)
    }

    fun addKart () {
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error =  null)
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser
        val kart = Kart(
            name = _uiState.value.kartName,
            userIds = arrayListOf<String>(currentUser!!.uid)
        )

        db.collection("karts")
            .add(kart)
    }
}
