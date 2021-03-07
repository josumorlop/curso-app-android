package es.crmone.app.presentation.calendar

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.R
import es.crmone.app.databinding.CellCalendarBinding
import es.crmone.app.presentation.client.Client
import es.crmone.app.presentation.client.ClientFragment

import java.lang.StringBuilder

typealias CheckoutListener = (calendar: CalendarOne) -> Unit

class CalendarAdapter(private val calendar: List<CalendarOne>, val listener: CheckoutListener): RecyclerView.Adapter<CalendarAdapter.CalendarVH>() {

    class CalendarVH internal constructor(itemView: View, val listener: CheckoutListener): RecyclerView.ViewHolder(itemView) {
        private val binding = CellCalendarBinding.bind(itemView)
        var calendar: CalendarOne? = null
            set(value) {
                field = value
                if (value != null) {

                    binding.tvItemCalendarDateTimeUser.text = value.usuarioRegistro.nombre+" "+value.usuarioRegistro.apellido1+" · "+value.fecha

                    binding.tvItemCalendarComment.text = value.comentarios
                    binding.tvItemCalendarComment.isVisible = !value.comentarios.isNullOrEmpty()

                    binding.tvItemCalendarComment2.text = value.comentarios2
                    binding.tvItemCalendarComment2.isVisible = !value.comentarios2.isNullOrEmpty()

                    binding.tvItemCalendarCheckInHour.text = value.checkin
                    if (value.checkin.isNullOrEmpty()) {
                        binding.ivIconLocation.isVisible = false
                        binding.tvItemCalendarCheckInHour.isVisible = false
                        binding.llCheckin.isVisible = false
                    } else {
                        binding.ivIconLocation.isVisible = true
                        binding.tvItemCalendarCheckInHour.isVisible = true
                        binding.llCheckin.isVisible = true
                    }

                    binding.tvItemCalendarCheckOutHour.text = value.checkout
                    if (value.checkout.isNullOrEmpty()) {
                        binding.tvItemCalendarSeparador.isVisible = false
                        binding.tvItemCalendarCheckOutHour.isVisible = false
                    } else {
                        binding.tvItemCalendarSeparador.isVisible = true
                        binding.tvItemCalendarCheckOutHour.isVisible = true
                    }

                    binding.tvItemCalendarClient.isVisible = true
                    binding.tvItemCalendarClient.text = value.cliente.razonSocial+" "+value.cliente.cif

                    binding.btCheckOut.isVisible = value.permisoCheckOut

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
