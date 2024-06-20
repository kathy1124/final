package im.ntub.myfinal2

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private var contacts: List<Contact>,
    private val viewModel: ContactViewModel, // Use ContactViewModel instead of Context
    private val context: Context
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun updateData(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }



    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.txtContactName)
        private val phoneTextView: TextView = itemView.findViewById(R.id.txtContactPhone)
        private val btnCall: ImageButton = itemView.findViewById(R.id.btnCall)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)


        fun bind(contact: Contact) {
            nameTextView.text = contact.name
            phoneTextView.text = contact.phone

            btnCall.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:${contact.phone}")
                context.startActivity(intent)
            }

            btnDelete.setOnClickListener {
                showDeleteConfirmationDialog(contact)
            }
        }

        private fun showDeleteConfirmationDialog(contact: Contact) {
            AlertDialog.Builder(context)
                .setTitle("刪除聯絡人")
                .setMessage("是否確認刪除${contact.name}?")
                .setPositiveButton("刪除") { dialog, _ ->
                    viewModel.deleteContact(contact)
                    dialog.dismiss()
                }
                .setNegativeButton("取消") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
