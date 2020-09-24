package th.co.cdgs.mobile.local_database_room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import th.co.cdgs.mobile.local_database_room.database.AppDatabase
import th.co.cdgs.mobile.local_database_room.database.User
import kotlin.concurrent.thread

class MainViewModel(context: Context) {
    private var appDatabase: AppDatabase? = null

    init {
        appDatabase = AppDatabase.getInstance(context)
    }

    fun insertUser(user: User){
        thread {
            appDatabase?.userDao()?.insertUser(user)
        }
    }

    fun getAllUsers(): LiveData<MutableList<User>>? {
        return appDatabase?.userDao()?.getAllUsers()
    }

    fun deleteUserByUserId(userId: Int){
        thread {
            appDatabase?.userDao()?.deleteUserByUserId(userId)
        }
    }

    fun getUserByUserId(userId: Int) = appDatabase?.userDao()?.getUserByUserId(userId)

    fun updateUser(user: User){
        thread {
            appDatabase?.userDao()?.updateUser(user)
        }
    }
}