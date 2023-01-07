package hr.ferit.ivoojvan.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class BankRecyclerAdapter
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<BankInfo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BankViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.bank_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is BankViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun postItemsList(data: ArrayList<BankInfo>){
        items = data
    }

    class BankViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        private val bankTitle = itemView.findViewById<TextView>(R.id.tvBankTitle)
        private val bankLocation = itemView.findViewById<TextView>(R.id.tvLocation)
        private val bankStatus = itemView.findViewById<TextView>(R.id.tvStatus)
        private val bankNumber = itemView.findViewById<TextView>(R.id.tvNumber)
        private val bankCloseTime = itemView.findViewById<TextView>(R.id.tvCloseTime)

        fun bind(bankInfo : BankInfo){
            bankTitle.text = bankInfo.title
            bankLocation.text = bankInfo.location
            bankStatus.text = bankInfo.status
            bankNumber.text = bankInfo.number
            bankCloseTime.text = bankInfo.close_time
        }
    }

}