package sa.factory.app1289420.data

import sa.factory.app1289420.model.OrderRequest
import sa.factory.app1289420.model.CatalogItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface OrderRequestRepository {
    fun getAvailableCatalog(): Flow<List<CatalogItem>>
    fun getActiveRequest(): Flow<OrderRequest?>
    suspend fun submitRequest(request: OrderRequest): Result<String>
}

class InMemoryOrderRequestRepository : OrderRequestRepository {
    private val _catalog = MutableStateFlow<List<CatalogItem>>(listOf(
        CatalogItem("1", "الخدمة الأساسية المعتمدة", "تنفيذ متقن ومطابق للمواصفات", 5000L, "verified"),
        CatalogItem("2", "الباقة المتقدمة", "دعم فني وضمان جودة الأداء", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<OrderRequest?>(null)

    override fun getAvailableCatalog(): Flow<List<CatalogItem>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<OrderRequest?> = _active.asStateFlow()

    override suspend fun submitRequest(request: OrderRequest): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
