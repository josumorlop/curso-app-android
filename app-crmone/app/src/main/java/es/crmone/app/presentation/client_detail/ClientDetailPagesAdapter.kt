package es.crmone.app.presentation.client_detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class ClientDetailPagesAdapter(fa: Fragment) : FragmentStateAdapter(fa) {

    private val numPages = 2
    private val fragments = arrayOf(Fragment1(), Fragment2())

    override fun getItemCount() = numPages
    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> fragments[0]
        1 -> fragments[1]
        else -> throw Exception("err")
    }

}
