package es.crmone.app.repository.map

sealed class CheckPointOperation {
    class Success(val locationsDTO: List<CRMMapDTO>): CheckPointOperation()
    object Error: CheckPointOperation()
}
interface CRMMapRepository {
    suspend fun getCheckPoint(): CheckPointOperation
}
