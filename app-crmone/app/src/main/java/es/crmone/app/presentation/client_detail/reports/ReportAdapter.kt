package es.crmone.app.presentation.client_detail.reports

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.R
import es.crmone.app.databinding.CellCalendarBinding
import es.crmone.app.presentation.calendar.CalendarOne

typealias CheckoutListener = (calendar: CalendarOne) -> Unit

class ReportAdapter(private val calendar: List<CalendarOne>, val listener: CheckoutListener): RecyclerView.Adapter<ReportAdapter.CalendarVH>() {

    class CalendarVH internal constructor(itemView: View, val listener: CheckoutListener): RecyclerView.ViewHolder(itemView) {
        private val binding = CellCalendarBinding.bind(itemView)
        var calendar: CalendarOne? = null
            set(value) {
                field = value
                if (value != null) {

                    binding.tvItemCalendarDateTimeUser.text = value.usuarioRegistro.nombre+" "+value.usuarioRegistro.apellido1+" · "+value.fecha

                    binding.tvItemCalendarComment.text = value.comentarios
                    if (value.comentarios.isNullOrEmpty())
                        binding.tvItemCalendarComment.isVisible = false

                    binding.tvItemCalendarComment2.text = value.comentarios2
                    if (value.comentarios2.isNullOrEmpty())
                        binding.tvItemCalendarComment2.isVisible = false

                    binding.tvItemCalendarCheckInHour.text = value.checkin
                    if (value.checkin.isNullOrEmpty()) {
                        binding.ivIconLocation.isVisible = false
                        binding.tvItemCalendarCheckInHour.isVisible = false
                        binding.llCheckin.isVisible = false
                    }

                    binding.tvItemCalendarCheckOutHour.text = value.checkout
                    if (value.checkout.isNullOrEmpty()) {
                        binding.tvItemCalendarSeparador.isVisible = false
                        binding.tvItemCalendarCheckOutHour.isVisible = false
                    }

                    binding.tvItemCalendarClient.isVisible = true
                    binding.tvItemCalendarClient.text = value.cliente.razonSocial+" "+value.cliente.cif

                    if (value.permisoCheckOut)
                        binding.btCheckOut.isVisible = true

                    binding.btCheckOut.setOnClickListener {
                        listener(value)
                    }


                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_calendar, parent, false)
        return CalendarVH(view, listener)
    }
    override fun onBindViewHolder(holder: CalendarVH, position: Int) {
        holder.calendar = calendar[position]
    }
    override fun getItemCount() = calendar.size
}


