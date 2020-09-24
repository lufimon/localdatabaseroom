package th.co.cdgs.mobile.local_database_room

import android.util.Log
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

    fun addUser(
        user: User,
        isShow: Boolean? = null,
        callback: ((Map<String, Any>) -> Unit)? = null
    ) {

        user.createBy = CREATE_BY

        api?.addUser(user)?.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(
                call: Call<Map<String, Any>>,
                response: Response<Map<String, Any>>
            ) {
                callback?.invoke(response.body() ?: mapOf())
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Log.e("addUser", t.message ?: "NO MESSAGE")
            }

        })
    }

    fun getAllUser(callback: ((MutableList<User>) -> Unit)?) {
        api?.getAllUser(CREATE_BY)?.enqueue(object : Callback<MutableList<User>> {
            override fun onResponse(
                call: Call<MutableList<User>>,
                response: Response<MutableList<User>>
            ) {
                callback?.invoke(response.body() ?: mutableListOf())
            }

            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                Log.e("getAllUser", t.message ?: "NO MESSAGE")
            }

        })
    }

    fun deleteUser(userId: Int, callback: () -> Unit) {
        api?.deleteUser(CREATE_BY, userId)?.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(
                call: Call<Map<String, Any>>,
                response: Response<Map<String, Any>>
            ) {
                callback.invoke()
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Log.e("deleteUser", t.message ?: "NO MESSAGE")
            }

        })
    }

    fun getUserByUserId(userId: Int, callback: (User) -> Unit) {
        api?.getUserByUserId(CREATE_BY, userId)?.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                callback.invoke(response.body() ?: User())
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("getUserByUserId", t.message ?: "NO MESSAGE")
            }

        })
    }

    fun updateUser(user: User, callback: () -> Unit) {
        user.createBy = CREATE_BY
        api?.updateUser(user)?.enqueue(object : Callback<Map<String, Any>> {
            override fun onResponse(
                call: Call<Map<String, Any>>,
                response: Response<Map<String, Any>>
            ) {
                callback.invoke()
            }

            override fun onFailure(call: Call<Map<String, Any>>, t: Throwable) {
                Log.e("updateUser", t.message ?: "NO MESSAGE")
            }

        })
    }
}