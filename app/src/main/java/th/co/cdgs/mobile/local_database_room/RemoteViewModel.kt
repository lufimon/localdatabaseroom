package th.co.cdgs.mobile.local_database_room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import th.co.cdgs.mobile.local_database_room.database.User
import th.co.cdgs.mobile.local_database_room.remote.Api

class RemoteViewModel(api: Api){

    companion object {
        val CREATE_BY = "tam"
    }

    private var api: Api? = null

    init {
        this.api = api
    }

    fun addUser(user: User, callback: ((Map<String, Any>) -> Unit)? = null){

        user.createBy = CREATE_BY

        api?.addUser(user)?.enqueue(object: Callback<Map<String, Any>>{
            override fun onResponse(
                call: Call<Map<String, Any>>,
                response: Response<Map<String, Any>>
            ) {
                callback?.invoke(response.body() ?: mapOf())
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Log.d("addUser", t.message ?: "NO MESSAGE")
            }

        })
    }
}