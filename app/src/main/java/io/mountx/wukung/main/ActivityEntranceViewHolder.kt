package io.mountx.wukung.main

import android.content.Intent
import android.content.pm.ActivityInfo
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.mountx.wukung.R

/**
 * @author hhs
 * Created on 2019-11-22
 */
class ActivityEntranceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvEntrance: TextView = itemView.findViewById(R.id.tv_text)

    fun updateView(activityInfo: ActivityInfo?) {
        tvEntrance.text = activityInfo?.name?.split(".")?.lastOrNull()?.replace("Activity", "")
        tvEntrance.setOnClickListener(activityInfo?.name?.let { name ->
            View.OnClickListener {
                itemView.context.run {
                    startActivity(Intent(this, Class.forName(name)))
                }
            }
        })
    }
}