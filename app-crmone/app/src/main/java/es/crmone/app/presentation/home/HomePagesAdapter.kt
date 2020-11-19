package es.crmone.app.presentation.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Matias on 01/01/2018.
 */
class HomePagesAdapter(fa: Fragment/*, val barrio: Barrio*/) : FragmentStateAdapter(fa) {
    private val numPages = 2
    private val fragments = arrayOf(Fragment1(), Fragment2())
    override fun getItemCount() = numPages
    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> fragments[0]
        1 -> fragments[1]
        else -> throw Exception("ERrrrroooooor la hemos liado")
    }
//    fun actualizar(position: Int) {
//        (fragments[position] as? Fragment1)?.also {
//            it.actualizaaaar()
//        }
//    }
}
