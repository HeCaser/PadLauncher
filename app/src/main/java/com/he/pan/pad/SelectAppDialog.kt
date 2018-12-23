package com.he.pan.pad

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.dialog_select_app.view.*
import me.drakeet.multitype.Items
import me.drakeet.multitype.MultiTypeAdapter


class SelectAppDialog : DialogFragment() {

    companion object {
        var nameList = mutableListOf("注意力训练","注意力100","最强大脑记忆",
            "节拍器","舒尔特表","舒尔特方格","Speed Reading")
        fun newInstance(): SelectAppDialog {
            return SelectAppDialog().apply {

            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window.apply {
            val para = this.attributes.apply {
                gravity = Gravity.BOTTOM
                width = WindowManager.LayoutParams.MATCH_PARENT

            }
            attributes = para
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setWindowAnimations(R.style.DialogBottomInOutAnim)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater.inflate(R.layout.dialog_select_app, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View) {
        with(view) {
            var mAdapter = MultiTypeAdapter()
            var items = Items()
            mAdapter.register(ResolveInfo::class.java, AppInfoBinder() {
                dialog!!.dismiss()
            })
            mAdapter.items = items
            rvList.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(context, LINE_APP_NUMBER)
                adapter = mAdapter
            }

            var apps = getApps(context)
            if (apps != null && apps.isNotEmpty()) {
                items.clear()
                items.addAll(apps)
                mAdapter.notifyDataSetChanged()
            }
        }

    }


    fun getApps(ctx: Context): List<ResolveInfo>? {
        var pack = ctx.packageManager
        var myi = Intent(Intent.ACTION_MAIN, null)
        myi.addCategory(Intent.CATEGORY_LAUNCHER)
        var apps = pack.queryIntentActivities(myi, PackageManager.MATCH_ALL).filter {
            nameList.contains(it.loadLabel(ctx.packageManager))
        }

        return apps
    }
}