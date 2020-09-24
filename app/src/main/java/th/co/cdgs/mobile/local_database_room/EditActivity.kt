package th.co.cdgs.mobile.local_database_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_edit.*
import th.co.cdgs.mobile.local_database_room.database.User

class EditActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_edit)
        title = "แก้ไข User"
        viewModel = MainViewModel(this)
        val id = intent.getIntExtra(Constants.KEY_EDIT_ACTIVITY_USER_ID, 0)
        viewModel?.getUserByUserId(id)
            ?.observe(this, Observer {
                edt_id.text = it.userId.toString().toEditable()
                edt_first_name.text = it.firstName?.toEditable()
                edt_last_name.text = it.lastName?.toEditable()
                edt_age.text = it.age.toString().toEditable()
                edt_facebook.text = it.contact?.facebook?.toEditable()
                edt_lind_id.text = it.contact?.lineId?.toEditable()
                edt_instagram.text = it.contact?.instagram?.toEditable()
            })

        btn_edit.setOnClickListener {
            User().apply {
                userId = edt_id.text.toString().toInt()
                firstName = edt_first_name.text.toString()
                lastName = edt_last_name.text.toString()
                age = edt_age.text.toString().toInt()
                contact?.facebook = edt_facebook.text.toString()
                contact?.lineId = edt_lind_id.text.toString()
                contact?.instagram = edt_instagram.text.toString()
            }.also {
                viewModel?.updateUser(it)
            }.run {
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}