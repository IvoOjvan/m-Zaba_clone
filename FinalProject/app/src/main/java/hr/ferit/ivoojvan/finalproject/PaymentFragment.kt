package hr.ferit.ivoojvan.finalproject

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date


class PaymentFragment : Fragment() {

    private val db = Firebase.firestore

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        val imgBtnPaymentBack = view.findViewById<ImageButton>(R.id.imgBtnPaymentBack)
        val imgBtnAccount = view.findViewById<ImageButton>(R.id.imgBtnAccountDropdown)
        val imgBtnReason = view.findViewById<ImageButton>(R.id.imgBtnReasonDropdown)

        val accountDropdown = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewAccount)
        val reasonDropdown = view.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewReason)

        val etPaymentAmount = view.findViewById<EditText>(R.id.etPaymentAmount)
        val etReciver = view.findViewById<EditText>(R.id.etReciver)
        val etCurrency = view.findViewById<EditText>(R.id.etCurrency)

        val btnPayment = view.findViewById<Button>(R.id.buttonPay)

        val reasons = arrayOf("HRANA I PIĆE", "DŽEPARAC", "POKLON", "KUĆANSTVO", "VRAĆANJE/POSUDBA", "TULUM", "PUTOVANJE", "OSTALO")
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, reasons)
        reasonDropdown.setAdapter(adapter)

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
                accountDropdown.setAdapter(accountAdapter)
            }
            .addOnFailureListener{

                Toast.makeText(context, "Failed", Toast.LENGTH_LONG).show()
            }


        btnPayment.setOnClickListener {
            var acc = BankAccount()
            for(account in bankAccounts){
                if(account.iban == accountDropdown.text.toString()){

                    acc = account
                    account.amount = (account.amount?.toDouble()
                        ?.minus(etPaymentAmount.text.toString().toDouble())).toString()

                    db.collection("BankAccounts").document(account.id).set(account)
                }
            }

            /*val calendar = Calendar.getInstance()
            val currentDate = LocalDateTime.of(
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.MONTH)
            )*/

            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val date = Date()
            val current = formatter.format(date)

            val transaction = db.collection("transactions").document()
            transaction.set(
                Transaction(
                    transaction.id,
                    current.toString(),
                    -(etPaymentAmount.text.toString().toDouble()),
                    accountDropdown.text.toString(),
                    "pero perić",
                    etReciver.text.toString(),
                    etCurrency.text.toString(),
                    reasonDropdown.text.toString(),
                    acc.id
                )
            )
        }



       imgBtnPaymentBack.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, MainFragment())
            fragmentTransaction?.commit()
        }

        imgBtnAccount.setOnClickListener {
            accountDropdown.showDropDown()
        }

        imgBtnReason.setOnClickListener {
            reasonDropdown.showDropDown()
        }

        return view
    }


}