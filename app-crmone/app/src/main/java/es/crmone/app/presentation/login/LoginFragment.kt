package es.crmone.app.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewModel by viewModels<LoginViewModel>() {
        LoginVMFactory()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentLoginBinding.bind(view)


        //Haciendo click en botón login
        binding.btAccederHome.setOnClickListener {
            viewModel.login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
        }


        viewModel.loginSuccess.observe(viewLifecycleOwner) {success ->

            if (success) {
                findNavController().navigate(LoginFragmentDirections.actionLoginToHome())
            } else {
                //Mostramos un error.
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }

        }

    }



}