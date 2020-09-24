package th.co.cdgs.mobile.local_database_room

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null
    private var userAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "รายการ"

        viewModel = MainViewModel(this)
        userAdapter = UserAdapter()

        userAdapter?.onDelete = { userId ->
            AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog)
                .setTitle("ลบ")
                .setMessage("ลบหรอ?")
                .setCancelable(false)
                .setNegativeButton(
                    "ยกเลิก"
                ) { dialog, _ ->
                    dialog.dismiss()
                }.setPositiveButton(
                    "ตกลง"
                ) { dialog, _ ->
                    viewModel?.deleteUserByUserId(userId)
                    dialog.dismiss()
                }
                .create()
                .show()
        }

        userAdapter?.onEdit = { userId ->
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            intent.putExtra(Constants.KEY_EDIT_ACTIVITY_USER_ID, userId)
            startActivity(intent)
        }

        btn_insert.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        viewModel?.getAllUsers()?.observe(this, Observer {
            userAdapter?.setListData(it)
        })


    }
}