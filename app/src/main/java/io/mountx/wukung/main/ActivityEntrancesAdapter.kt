package io.mountx.wukung.main

import android.content.pm.ActivityInfo
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.mountx.wukung.R

/**
 * @author hhs
 * Created on 2019-11-22
 */
class ActivityEntrancesAdapter(private val activityInfoList: List<ActivityInfo>?) : RecyclerView.Adapter<ActivityEntranceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityEntranceViewHolder {
        return ActivityEntranceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_activity_entrance, parent, false))
    }

    override fun getItemCount(): Int {
        return activityInfoList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ActivityEntranceViewHolder, position: Int) {
        holder.updateView(activityInfoList?.getOrNull(position))
    }
}