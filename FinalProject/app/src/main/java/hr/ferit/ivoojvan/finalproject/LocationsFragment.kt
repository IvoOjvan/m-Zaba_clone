package hr.ferit.ivoojvan.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class LocationsFragment : Fragment() {

    private lateinit var bankInfoAdapter : BankRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_locations, container, false)

        bankInfoAdapter = BankRecyclerAdapter()
        bankInfoAdapter.postItemsList(setupData())


        view.findViewById<RecyclerView>(R.id.locationsRecycler).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bankInfoAdapter
        }


        val imgBtnLocaionBack = view.findViewById<ImageButton>(R.id.imgBtnLocBack)
        imgBtnLocaionBack.setOnClickListener {
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_layout, MainFragment())
            fragmentTransaction?.commit()
        }


        return view
    }

    private fun setupData():ArrayList<BankInfo>{
        val list = ArrayList<BankInfo>()
        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )
        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )

        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )

        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )

        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )

        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )

        list.add(
            BankInfo(
                "Zagrebačka banka d.d",
                "Osijek",
                "031 252 325",
                "Open",
                "Closes 7PM"
            )
        )
        return list
    }


}