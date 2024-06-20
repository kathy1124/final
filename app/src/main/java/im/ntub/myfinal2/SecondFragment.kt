package im.ntub.myfinal2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ContactViewModel : ViewModel() {
    private val _contacts = MutableLiveData<List<Contact>>()

    init {
        _contacts.value = emptyList()
    }

    val contacts: LiveData<List<Contact>>
        get() = _contacts

    init {
        _contacts.value = emptyList()
    }

    fun addContact(contact: Contact) {
        val currentList = _contacts.value.orEmpty().toMutableList()
        currentList.add(contact)
        _contacts.value = currentList
    }

    fun getAllContacts(): List<Contact> {
        return _contacts.value.orEmpty()
    }

    fun deleteContact(contact: Contact) {
        val currentList = _contacts.value.orEmpty().toMutableList()
        currentList.remove(contact)
        _contacts.value = currentList
    }
}
class SecondFragment : Fragment() {
    private lateinit var viewModel: ContactViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        viewModel = (requireActivity() as MainActivity).viewModel

        val btnSave = view.findViewById<Button>(R.id.btnSave)
        val txtName = view.findViewById<EditText>(R.id.txtName)
        val txtPhone = view.findViewById<EditText>(R.id.txtPhone)

        btnSave.setOnClickListener {
            val name = txtName.text.toString()
            val phone = txtPhone.text.toString()

            viewModel.addContact(Contact(name, phone))

            txtName.text.clear()
            txtPhone.text.clear()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = SecondFragment()
    }
}
