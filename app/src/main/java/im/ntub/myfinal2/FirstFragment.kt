package im.ntub.myfinal2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
class FirstFragment : Fragment() {
    private lateinit var viewModel: ContactViewModel
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        viewModel = (requireActivity() as MainActivity).viewModel

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        contactAdapter = ContactAdapter(emptyList(), viewModel, requireContext())
        recyclerView.adapter = contactAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.contacts.observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let {
                contactAdapter.updateData(it)
            }
        })

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = FirstFragment()
    }
}
