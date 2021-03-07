package es.crmone.app.repository.map




interface CRMMapRepository {
    interface CRMMapCallBack {
        fun onSuccess(map: List<CRMMapDTO>)
        fun onError()
    }
    fun getCheckPoint(callback: CRMMapRepository.CRMMapCallBack)


}
