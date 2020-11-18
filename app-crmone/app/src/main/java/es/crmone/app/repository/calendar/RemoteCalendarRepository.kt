package es.crmone.app.repository.calendar

import es.crmone.app.repository.EndPoints
import es.crmone.app.repository.RetrofitService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteCalendarRepository(private val api: EndPoints = RetrofitService.endpoints) : CalendarRepository {
    override fun getCalendar(callback: CalendarRepository.CalendarCallback) {
        api.getCalendar().enqueue(object : Callback<List<CalendarDTO>> {
            override fun onResponse(call: Call<List<CalendarDTO>>, response: Response<List<CalendarDTO>>) {
                if (response.isSuccessful) {
                    val calendar = response.body()
                    if (calendar!=null) {
                        callback.onSuccess(calendar)
                        return
                    }
                }
                callback.onError()
            }
            override fun onFailure(call: Call<List<CalendarDTO>>, t: Throwable) {
                callback.onError()
            }
        })
    }
}