package hr.ferit.ivoojvan.finalproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrencyFragment : Fragment() {

    private lateinit var recyclerAdapter : CurrencyRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_currency, container, false)

        val currencyRecycler = view.findViewById<RecyclerView>(R.id.currencyRecyclerView)

        val imgBtnBack = view.findViewById<ImageButton>(R.id.imageButtonBack)
        val imgBtnCurrencyDropdown = view.findViewById<ImageButton>(R.id.imgBtnBaseCurrencyDropdown)

        val btnCalculate = view.findViewById<Button>(R.id.buttonCalculate)

        val etAmount = view.findViewById<EditText>(R.id.editTextAmount)
        val etCurr = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewCurrency)


        val currencies = arrayOf("HRK", "AUD", "CAD", "CZK", "DKK", "HUF", "NOK", "SEK", "CHF", "GBP", "RSD", "EUR", "BAM", "PLN")
        var currAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, currencies)
        etCurr.setAdapter(currAdapter)


        imgBtnCurrencyDropdown.setOnClickListener {
            etCurr.showDropDown()
        }


        imgBtnBack.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, MainFragment())
            fragmentTransaction?.commit()
        }


        btnCalculate.setOnClickListener {

            val targetCurrencies = ArrayList<String>()
            for(curr in currencies){
                if(curr != etCurr.text.toString()){
                    targetCurrencies.add(curr)
                }
            }

            val items = ArrayList<ConvertedCurrency>()

            for(curr in targetCurrencies) {
                val request = ServiceBuilder.buildService(ExchangeRateAPI::class.java)
                val call =
                    request.getNewCurrency(etCurr.text.toString(), curr, etAmount.text.toString())

                call.enqueue(object : Callback<ConvertedCurrency> {
                    override fun onResponse(
                        call: Call<ConvertedCurrency>,
                        response: Response<ConvertedCurrency>
                    ) {
                        if (response.isSuccessful) {

                            val item = response.body() as ConvertedCurrency
                            items.add(item)

                            recyclerAdapter = CurrencyRecyclerAdapter(items)
                            currencyRecycler.apply {
                                layoutManager = LinearLayoutManager(context)
                                adapter = recyclerAdapter
                            }

                        }
                    }

                    override fun onFailure(call: Call<ConvertedCurrency>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
            }

        }

        return view
    }

}