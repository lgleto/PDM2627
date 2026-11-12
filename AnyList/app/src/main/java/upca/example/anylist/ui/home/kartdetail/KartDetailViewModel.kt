package upca.example.anylist.ui.home.kartdetail

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import upca.example.anylist.models.Kart
import upca.example.anylist.models.Product

data class KartDetailViewState (
    var kart: Kart? = null,
    var products : List<Product> = emptyList(),
    var error : String? = null,
    var isLoading : Boolean = false
    )

class KartDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(KartDetailViewState())
    val uiState: StateFlow<KartDetailViewState> = _uiState.asStateFlow()

    var kartId : String? = null

    fun fetchKart (kartId:String) {
         this.kartId = kartId
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error =  null)
        val db = Firebase.firestore

        db.collection("karts")
            .document(kartId)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        error =  e.localizedMessage)
                    return@addSnapshotListener
                }

                val kart = value?.toObject(Kart::class.java)
                _uiState.value = _uiState.value.copy(
                    kart = kart,
                    isLoading = false,
                    error =  null)


            }
        db.collection("karts")
            .document(kartId)
            .collection("products")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = true,
                        error =  e.localizedMessage)
                    return@addSnapshotListener
                }

                val products = ArrayList<Product>()
                for (doc in value!!) {
                    val product = doc.toObject(Product::class.java)
                    product.id = doc.id
                    products.add(product)
                }
                _uiState.value = _uiState.value.copy(
                    products = products,
                    isLoading = false,
                    error =  null)
            }
    }

    fun updateProduct(productId: String, checked: Boolean){
        val db = Firebase.firestore
        db.collection("karts")
            .document(kartId!!)
            .collection("products")
            .document(productId)
            .update("checked", checked)

    }
}
