package es.crmone.app.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import es.crmone.app.MainFragment
import es.crmone.app.R
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var adapter: HomePagesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        adapter = HomePagesAdapter(this)
        setupViews()
    }
    private fun setupViews() {
        with(binding) {
            myToolbar.setNavigationOnClickListener {
                val mainFragment: MainFragment?  = (parentFragment?.parentFragment as? MainFragment)
                mainFragment?.also {
                    it.openDrawer()
                }
            }
            vp.adapter = adapter
            TabLayoutMediator(tabs, vp) { tab, position ->
                tab.text = when(position) {
                    0 -> "Opcion 1"
                    1 -> "Opcion 2 AOAO"
                    else -> "jodeeeer"
                }
            }.attach()
        }
    }
}