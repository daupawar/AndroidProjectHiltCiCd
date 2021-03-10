package com.anzu.evepatient.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.androidtemplatedsl.ui.base.BaseActivity

/**
 * ༼-つ-◕_◕-༽つ
 * @author Rohan Pawar on 14/02/2020.
 */

abstract class BaseDialogFragment<T : ViewDataBinding> : DialogFragment() {

    @get:LayoutRes
    abstract val layoutId: Int

    var bindView: T? = null
        private set

    private var mActivity: BaseActivity<*>? = null



    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindView = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return bindView?.root
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = super.onCreateDialog(savedInstanceState)
//        DialogUtils.setMargins(dialog, 50, 50, 50, 50)
//
//        return dialog
//
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = LinearLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        // creating the fullscreen dialog
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
        }
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }


    override fun show(fragmentManager: FragmentManager, tag: String?) {
        val transaction = fragmentManager.beginTransaction()
        val prevFragment = fragmentManager.findFragmentByTag(tag)
        if (prevFragment != null) {
            transaction.remove(prevFragment)
        }
        transaction.addToBackStack(null)
        show(transaction, tag)
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    fun dismissDialog(fragmentManager: FragmentManager, tag: String) {
        dismiss()
        onFragmentDetached(fragmentManager, tag)
    }

    private fun onFragmentDetached(fragmentManager: FragmentManager, tag: String) {

        val fragment = fragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow()
        }
    }

}
