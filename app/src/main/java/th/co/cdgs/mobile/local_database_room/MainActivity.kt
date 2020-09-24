package th.co.cdgs.mobile.local_database_room

import android.app.Activity
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

    private var remoteViewModel: RemoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "รายการ"

        viewModel = MainViewModel(this)
        userAdapter = UserAdapter()

        remoteViewModel = RemoteViewModel(Service.getRetrofitService())

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
                    remoteViewModel?.deleteUser(userId){
                        dialog.dismiss()
                        callAllUser()
                    }
//                    viewModel?.deleteUserByUserId(userId)
//                    dialog.dismiss()
                }
                .create()
                .show()
        }

        userAdapter?.onEdit = { userId ->
            val intent = Intent(this@MainActivity, EditActivity::class.java)
            intent.putExtra(Constants.KEY_EDIT_ACTIVITY_USER_ID, userId)
            startActivityForResult(intent, 0x56)
        }

        btn_insert.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent,0x55)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        //room get all user
//        viewModel?.getAllUsers()?.observe(this, Observer {
//            userAdapter?.setListData(it)
//        })

        callAllUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            when(requestCode){
                0x55,0x56 -> {
                    callAllUser()
                }
            }
        }
    }

    private fun callAllUser(){
        remoteViewModel?.getAllUser {
            userAdapter?.setListData(it)
        }
    }
}