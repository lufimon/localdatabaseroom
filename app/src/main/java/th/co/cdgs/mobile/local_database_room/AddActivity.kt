package th.co.cdgs.mobile.local_database_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_add.*
import th.co.cdgs.mobile.local_database_room.database.User
import java.io.InputStream

class AddActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "เพิ่ม User"

        viewModel = MainViewModel(this)

        btn_add.setOnClickListener {
            User().apply {
                firstName = edt_first_name.text.toString()
                lastName = edt_last_name.text.toString()
                age = edt_age.text.toString().toInt()
                contact?.facebook = edt_facebook.text.toString()
                contact?.lineId = edt_lind_id.text.toString()
                contact?.instagram = edt_instagram.text.toString()
            }.also {
                viewModel?.insertUser(it)
            }.run {
                finish()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        this@AddActivity.finish()
    }
}