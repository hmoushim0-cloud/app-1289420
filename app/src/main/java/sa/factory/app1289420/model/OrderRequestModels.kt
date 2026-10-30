package sa.factory.app1289420.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogItem(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class OrderRequest(
    val requestId: String,
    val items: List<CatalogItem>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: OrderRequestStatus = OrderRequestStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class OrderRequestStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
