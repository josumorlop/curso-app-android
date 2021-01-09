package es.crmone.app.presentation.client_create

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import es.crmone.app.MainFragment
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentClientCreateBinding



class ClientCreateFragment : BaseFragment<FragmentClientCreateBinding>(R.layout.fragment_client_create) {

    private val viewModel by viewModels<ClientCreateViewModel>() {
        ClientCreateVMFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentClientCreateBinding.bind(view)
        setupBackButton(binding.myToolbar)

        with(binding) {
            btGuardar.setOnClickListener { it ->

                val cif = tiCif.editText?.text.toString()
                val literal = tiLiteral.editText?.text.toString()

                var success = true

                if (cif.isEmpty()) {
                    tiCif.isErrorEnabled = true
                    tiCif.error = "Campo obligatorio"
                    success = false
                } else {
                    tiCif.isErrorEnabled = false
                }

                if (literal.isEmpty()) {
                    tiLiteral.isErrorEnabled = true
                    tiLiteral.error = "Campo obligatorio"
                    success = false
                } else {
                    tiLiteral.isErrorEnabled = false
                }


                if (success) {
                    viewModel.insertClient(cif, literal)
                }


            }

        }

        viewModel.successInsert.observe(viewLifecycleOwner) { success ->
            checkSuccess(success)
        }

    }


    private fun checkSuccess(success: Boolean) {
        if (success) {
            Toast.makeText(requireContext(), "YEAH Baby!!", Toast.LENGTH_SHORT).show()
        } else {
            binding.tiCif.isErrorEnabled = true
            binding.tiCif.error = "CIF ya existente"
        }

    }



}