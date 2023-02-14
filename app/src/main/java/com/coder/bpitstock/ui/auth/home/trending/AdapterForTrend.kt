package com.coder.bpitstock.ui.auth.home.trending


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coder.bpitstock.R
import com.coder.bpitstock.data.model.Item
import com.coder.bpitstock.ui.auth.home.iRecLis
import java.lang.StringBuilder

class AdapterForTrend(var context: Context, var trendingItems: ArrayList<Item>, var type:String):
    RecyclerView.Adapter<AdapterForTrend.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        var rank: TextView? = null
        var name: TextView? = null
        var marks: TextView? = null
        private var lis:iRecLis?=null
        fun setListener(listener: iRecLis) {
            this.lis = listener
        }







        init {
            rank = itemView.findViewById(R.id.rank_no) as TextView
            name = itemView.findViewById(R.id.rank_holder_name) as TextView
            marks = itemView.findViewById(R.id.marks_otained_holer) as TextView
            itemView.setOnClickListener(this)


        }

        override fun onClick(p0: View?) {
            lis!!.onItemClick(p0!!,adapterPosition)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.trending_item, parent, false)
        )


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var fullName=StringBuilder("")
        fullName.append(trendingItems[position].itemName).append(" ")
        holder.rank!!.text = (position+1).toString()

        holder.name!!.text = fullName
        holder.marks!!.text = trendingItems[position].recievedQuantity.toString()
        holder.setListener(object :iRecLis{
            override fun onItemClick(view: View, pos: Int) {
                if (type!="N"){
                    trendingItems.removeAt(pos)
                    holder.itemView.setBackgroundColor(Color.YELLOW)

                }
            }

        })

    }


    override fun getItemCount(): Int {
        return trendingItems.size
    }
}