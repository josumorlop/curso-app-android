package es.crmone.app.presentation.client_detail.reports

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.R
import es.crmone.app.databinding.CellCalendarBinding
import es.crmone.app.presentation.calendar.CalendarOne

class ReportAdapter(private val calendar: List<CalendarOne>): RecyclerView.Adapter<ReportAdapter.CalendarVH>() {

    class CalendarVH internal constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = CellCalendarBinding.bind(itemView)
        var calendar: CalendarOne? = null
            set(value) {
                field = value
                if (value != null) {
                    binding.tvItemCalendarHour.text = value.hora
                    binding.tvItemCalendarDate.text = value.fecha
                    binding.tvItemCalendarComment.text = value.comentarios
                    binding.tvItemCalendarTag.text = "Pendiente"
                    binding.tvItemCalendarClient.text = "Wikitic S.L"
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_calendar, parent, false)
        return CalendarVH(view)
    }
    override fun onBindViewHolder(holder: CalendarVH, position: Int) {
        holder.calendar = calendar[position]
    }
    override fun getItemCount() = calendar.size
}
