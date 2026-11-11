package upca.example.anylist.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import upca.example.anylist.TAG
import upca.example.anylist.models.Kart
import upca.example.anylist.models.User


data class KartViewState(
    val karts: List<Kart> = emptyList(),
    val isLoading : Boolean = false,
    val error : String? = null
)

class KartViewModel : ViewModel(){

    private val _uiState = MutableStateFlow(KartViewState())
    val uiState : StateFlow<KartViewState> = _uiState



    fun fetchKarts(){

        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        val auth = Firebase.auth
        val currentUser = auth.currentUser ?: return
        val db = Firebase.firestore
        db.collection("karts")
            .whereArrayContains("userIds", currentUser.uid)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = e.localizedMessage
                    )
                    return@addSnapshotListener
                }

                val karts = ArrayList<Kart>()
                for (doc in value!!) {
                    val kart = doc.toObject(Kart::class.java)
                    kart.id = doc.id
                    karts.add(kart)
                }
                _uiState.value = _uiState.value.copy(
                    karts = karts,
                    isLoading = false,
                    error = null
                )


            }
    }
}