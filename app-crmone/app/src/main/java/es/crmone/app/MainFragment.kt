package es.crmone.app

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.fragment.NavHostFragment
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentMainBinding

class MainFragment: BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMainBinding.bind(view)



    }


}