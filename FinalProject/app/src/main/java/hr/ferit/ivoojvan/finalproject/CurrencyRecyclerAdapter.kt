package hr.ferit.ivoojvan.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class CurrencyRecyclerAdapter(val items : ArrayList<ConvertedCurrency>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CurrencyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.currency_recycler_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CurrencyViewHolder ->{
                holder.bind(position, items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class CurrencyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        private val currencyName = view.findViewById<TextView>(R.id.tvCurrencyTitle)
        private val rate = view.findViewById<TextView>(R.id.tvCurrCal)
        private val amount = view.findViewById<TextView>(R.id.tvCurrencyAmount)

        fun bind(index: Int, currency: ConvertedCurrency){
            currencyName.text = currency.new_currency

            val sb = StringBuilder()
            val currencyRate : Double = currency.old_amount/currency.new_amount
            val df = DecimalFormat("#.####")
            sb.append("1 " + currency.new_currency + " = ").append(df.format(currencyRate)).append(currency.old_currency)
            rate.text = sb.toString()

            sb.clear()
            sb.append(currency.new_amount.toString()).append(currency.new_currency)

            amount.text = sb.toString()
        }
    }
}