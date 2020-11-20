package es.crmone.app.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<T: ViewBinding>(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    var _binding: T? = null
    protected val binding get() = _binding!!


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}