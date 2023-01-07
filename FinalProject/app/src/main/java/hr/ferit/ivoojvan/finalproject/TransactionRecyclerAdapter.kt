package hr.ferit.ivoojvan.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class TransactionRecyclerAdapter(val items:ArrayList<Transaction>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TransactionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is TransactionViewHolder ->{
                holder.bind(position, items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }



    class TransactionViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        private val date = view.findViewById<TextView>(R.id.textViewDate)
        private val describtion = view.findViewById<TextView>(R.id.textViewTransaction)
        private val status = view.findViewById<TextView>(R.id.textViewStatus)

        fun bind(index: Int, transaction: Transaction){

            val sb = StringBuilder()
            sb.append(transaction.describtion + " ").append(transaction.target + "\n")
                .append(transaction.payer)
            describtion.text = sb.toString()

            sb.clear()
            sb.append(transaction.amount.toString() + " ").append(transaction.currency + "\n")
            status.text = sb.toString()

            val parts = transaction.date?.split("-")
            sb.clear()
            sb.append(parts?.get(2) + "\n").append(month(parts?.get(1).toString().toInt()))
            date.text = sb.toString()

        }

        private fun month(m : Int):String{
            if(m == 1)
                return "SIJ"
            else if(m == 2)
                return "VELJ"
            else if(m == 3)
                return "OŽU"
            else if(m == 4)
                return "TRA"
            else if(m == 5)
                return "SVI"
            else if(m == 6)
                return "LIP"
            else if(m == 7)
                return "SRP"
            else if(m == 8)
                return "KOL"
            else if(m == 9)
                return "RUJ"
            else if(m == 10)
                return "LIS"
            else if(m == 11)
                return "STU"
            else
                return "PRO"
        }

    }
}