package th.co.cdgs.mobile.local_database_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import th.co.cdgs.mobile.local_database_room.database.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var items: MutableList<User> = mutableListOf()
    var onDelete: ((userId: Int) -> Unit)? = null
    var onEdit: ((userId: Int) -> Unit)? = null

    fun setListData(items: MutableList<User>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtId = view.txt_val_id
        val txtFirstName = view.txt_val_firstname
        val txtLastName = view.txt_val_lastname
        val txtAge = view.txt_val_age
        val txtFacebook = view.txt_val_facebook
        val txtLineId = view.txt_val_lineid
        val txtInstagram = view.txt_val_instagram

        val btnEdit = view.btn_edit
        val btnDelete = view.btn_delete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].also {
            holder.txtId.text = it.userId.toString()
            holder.txtFirstName.text = it.firstName
            holder.txtLastName.text = it.lastName
            holder.txtAge.text = it.age.toString()
            holder.txtFacebook.text = it.contact?.facebook
            holder.txtLineId.text = it.contact?.lineId
            holder.txtInstagram.text = it.contact?.instagram

            holder.btnDelete.setOnClickListener { view ->
                onDelete?.invoke(it.userId ?: 0)
            }

            holder.btnEdit.setOnClickListener { view ->
                onEdit?.invoke(it.userId ?: 0)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}