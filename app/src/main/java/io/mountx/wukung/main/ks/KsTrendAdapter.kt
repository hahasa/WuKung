package io.mountx.wukung.main.ks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.mountx.wukung.R

class KsTrendAdapter(private val itemCount: Int) : RecyclerView.Adapter<PlaceHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        return PlaceHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_trend, parent, false)
        )
    }

    override fun getItemCount() = itemCount

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.updateView()
    }
}