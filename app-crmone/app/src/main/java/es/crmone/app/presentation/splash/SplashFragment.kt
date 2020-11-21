package es.crmone.app.presentation.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.common.zipLiveData
import es.crmone.app.databinding.FragmentSplashBinding

class SplashFragment: BaseFragment<FragmentSplashBinding>(R.layout.fragment_splash) {

    private val viewModel by viewModels<SplashViewModel>() {
        SplashVMFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSplashBinding.bind(view)

        zipLiveData(viewModel.loadSplashLD, viewModel.userLoggedLD).observe(viewLifecycleOwner) {
            val userLogged = it.second
            if (userLogged) {
                findNavController().navigate(SplashFragmentDirections.actionToMain())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionToLogin())
            }

        }
    }

}