package com.he.pan.pad

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.app_info_item.view.*
import me.drakeet.multitype.ItemViewBinder

/**
 * Created by hepan on 2018/9/21.
 */

class AppInfoBinder(var callback: (pos: Int) -> Unit) : ItemViewBinder<ResolveInfo, AppInfoBinder.ViewHolder>() {


    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.app_info_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ResolveInfo) {
        holder.itemData = item
        holder.setData()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var itemData: ResolveInfo
        fun setData() {
            with(itemView) {
                rootView.click {
                    var compant = ComponentName(itemData.activityInfo.packageName,itemData.activityInfo.name)
                    var myintent = Intent().apply {
                        component = compant
                    }
                    context.startActivity(myintent)
                    callback(adapterPosition)
                }
                ivIcon.setImageDrawable(itemData.activityInfo.loadIcon(context.packageManager))
                tvName.text = itemData.loadLabel(context.packageManager)
            }
        }
    }
}