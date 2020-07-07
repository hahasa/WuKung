package io.mountx.wukung.main.ks

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.mountx.wukung.R

class PlaceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvText = itemView.findViewById<TextView>(R.id.tv_text)

    fun updateView() {
        tvText.text = "adapterPosition:$absoluteAdapterPosition"
    }
}