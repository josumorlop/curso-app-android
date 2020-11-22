package es.crmone.app.common

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import es.crmone.app.R

open class BaseFragment<T: ViewBinding>(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    var _binding: T? = null
    protected val binding get() = _binding!!


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun fragmentAnimation(): NavOptions.Builder {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
    }

}