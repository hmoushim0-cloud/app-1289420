package sa.factory.app1289420.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1289420.data.OrderRequestRepository
import sa.factory.app1289420.model.OrderRequest
import sa.factory.app1289420.model.CatalogItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OrderRequestViewModel(
    private val repository: OrderRequestRepository
) : ViewModel() {
    val catalog: StateFlow<List<CatalogItem>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<OrderRequest?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: OrderRequest) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
