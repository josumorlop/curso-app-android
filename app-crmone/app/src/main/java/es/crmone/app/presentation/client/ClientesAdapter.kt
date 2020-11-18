package es.crmone.app.presentation.client

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.crmone.app.R
import es.crmone.app.databinding.CellClientBinding
typealias ClientListener = (client: Client) -> Unit
class ClientesAdapter(private val clients: List<Client>, val listener: ClientListener): RecyclerView.Adapter<ClientesAdapter.ClienteVH>() {

    class ClienteVH internal constructor(itemView: View, val listener: ClientListener): RecyclerView.ViewHolder(itemView) {
        private val binding = CellClientBinding.bind(itemView)
        var client: Client? = null
            set(value) {
                field = value
                if (value != null) {
                    binding.tvCif.text = value.cif
                    binding.tvRazonSocial.text = value.razonSocial
                    itemView.setOnClickListener {
                        listener(value)
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClienteVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_client, parent, false)
        return ClienteVH(view, listener)
    }
    override fun onBindViewHolder(holder: ClienteVH, position: Int) {
        holder.client = clients[position]
    }
    override fun getItemCount() = clients.size
}
