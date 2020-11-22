package es.crmone.app.presentation.client_detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import es.crmone.app.presentation.client_detail.info.ClientInfoFragment
import es.crmone.app.presentation.client_detail.reports.ReportsFragment


class ClientDetailPagesAdapter(fa: Fragment, idClient: Int) : FragmentStateAdapter(fa) {

    private val numPages = 2
    private val fragments = arrayOf(ReportsFragment.create(idClient), ClientInfoFragment.create(idClient))

    override fun getItemCount() = numPages
    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> fragments[0]
        1 -> fragments[1]
        else -> throw Exception("err")
    }

}
