package es.crmone.app.common

import androidx.activity.addCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import es.crmone.app.R

open class BaseFragment<T: ViewBinding>(@LayoutRes contentLayoutId: Int): Fragment(contentLayoutId) {

    var _binding: T? = null
    protected val binding get() = _binding!!


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
    open fun setupBackButton(toolbar: Toolbar){
        toolbar.setNavigationOnClickListener{ findNavController().navigateUp() }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
    }

    fun fragmentAnimation(): NavOptions.Builder {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.enter)
            .setExitAnim(R.anim.exit)
            .setPopEnterAnim(R.anim.pop_enter)
            .setPopExitAnim(R.anim.pop_exit)
    }
    fun showAlertDialog(dialogBuilder: AlertDialog.Builder.() -> Unit) {
        context?.also {
            val builder = AlertDialog.Builder(it)
            builder.dialogBuilder()
            val dialog = builder.create()
            dialog.show()
        }
    }

}