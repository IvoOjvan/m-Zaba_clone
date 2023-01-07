package hr.ferit.ivoojvan.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AccountFragment : Fragment() {
    val db = Firebase.firestore
    private lateinit var recyclerAdapter: TransactionRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        val imgBtnBack = view.findViewById<ImageButton>(R.id.imgBtnAccountBack)
        val imgBtnAccountDropdowns = view.findViewById<ImageButton>(R.id.imgBtnAccounts)

        val accountsDropdown = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewAccount)

        val tvAccountName = view.findViewById<TextView>(R.id.tvAccountName)
        val tvIBAN = view.findViewById<TextView>(R.id.tvIBAN)
        val tvAmount = view.findViewById<TextView>(R.id.tvAccountAmount)
        val tvAmountEUR = view.findViewById<TextView>(R.id.tvAccountAmountEUR)

        val transactionRecycler = view.findViewById<RecyclerView>(R.id.transactionRecycler)

        val bankAccounts = ArrayList<BankAccount>()
        db.collection("BankAccounts")
            .get()
            .addOnSuccessListener { result ->
                val ibans = ArrayList<String>()

                for (data in result.documents){
                    val bankAccount = data.toObject(BankAccount::class.java)
                    ibans.add(bankAccount?.iban.toString())
                    if(bankAccount != null)
                        bankAccounts.add(bankAccount)

                }

                val accountAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, ibans)
                accountsDropdown.setAdapter(accountAdapter)


                tvAccountName.text = bankAccounts[0].name.toString()
                tvIBAN.text = bankAccounts[0].iban.toString()
                tvAmount.text = bankAccounts[0].amount + "HRK"

                accountsDropdown.setText(accountsDropdown.adapter.getItem(0).toString(), false)

                val request = ServiceBuilder.buildService(ExchangeRateAPI::class.java)
                val call = request.getNewCurrency("HRK", "EUR", bankAccounts[0].amount.toString())

                call.enqueue(object: Callback<ConvertedCurrency> {
                    override fun onResponse(
                        call: Call<ConvertedCurrency>,
                        response: Response<ConvertedCurrency>
                    ) {
                        if(response.isSuccessful){
                            tvAmountEUR.text = response.body()?.new_amount.toString() + " EUR"
                            Toast.makeText(context, "Size: " + response.body()?.new_amount.toString(), Toast.LENGTH_LONG).show()

                        }
                    }
                    override fun onFailure(call: Call<ConvertedCurrency>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

            }
            .addOnFailureListener{
                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            }


            db.collection("transactions")
                .get()
                .addOnSuccessListener { result ->
                    val transactions = ArrayList<Transaction>()
                    for(data in result.documents){
                        val transaction = data.toObject(Transaction::class.java)
                        if(transaction != null && transaction.accountId == bankAccounts[0].id){
                            transactions.add(transaction)
                        }
                    }
                    recyclerAdapter = TransactionRecyclerAdapter(transactions)
                    transactionRecycler.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = recyclerAdapter
                    }
                }
                .addOnFailureListener {  }


        imgBtnBack.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, MainFragment())
            fragmentTransaction?.commit()
        }

        accountsDropdown.setOnItemClickListener { parent, view, position, id ->
            tvAccountName.text = bankAccounts[position].name
            tvIBAN.text = bankAccounts[position].iban
            tvAmount.text = bankAccounts[position].amount + "HRK"

            val request = ServiceBuilder.buildService(ExchangeRateAPI::class.java)
            val call = request.getNewCurrency("HRK", "EUR", bankAccounts[position].amount.toString())

            call.enqueue(object: Callback<ConvertedCurrency> {
                override fun onResponse(
                    call: Call<ConvertedCurrency>,
                    response: Response<ConvertedCurrency>
                ) {
                    if(response.isSuccessful){
                        tvAmountEUR.text = response.body()?.new_amount.toString() + " EUR"
                    }
                }
                override fun onFailure(call: Call<ConvertedCurrency>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

            db.collection("transactions")
                .get()
                .addOnSuccessListener { result ->
                    val transactions = ArrayList<Transaction>()
                    for(data in result.documents){
                        val transaction = data.toObject(Transaction::class.java)
                        if(transaction != null && transaction.accountId == bankAccounts[position].id)
                            transactions.add(transaction)
                    }

                    recyclerAdapter = TransactionRecyclerAdapter(transactions)
                    transactionRecycler.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = recyclerAdapter
                    }
                }
                .addOnFailureListener {  }


        }

        imgBtnAccountDropdowns.setOnClickListener {
            accountsDropdown.showDropDown()
        }

        return view
    }

}