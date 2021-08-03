package io.rusha.bnet_market.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.rusha.bnet_market.R

class AppsAdapter(
    private val apps: List<App>,
    private val viewModel: ListViewModel
) : RecyclerView.Adapter<AppsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val app = apps[position]
        holder.itemView.setOnClickListener() {
            viewModel.onAppClick()
        }
        holder.appText.text = app.name
//        Glide.with(holder.itemView.context).load(app.url).into(holder.crossImage)
    }

    override fun getItemCount(): Int {
        return apps.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val appText: TextView = view.findViewById(R.id.appText)
        val crossImage: ImageView = view.findViewById(R.id.crossImage)
    }
}
