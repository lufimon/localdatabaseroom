package th.co.cdgs.mobile.local_database_room.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user WHERE user_id = :userId")
    fun deleteUserByUserId(userId: Int)

    @Query("SELECT * FROM user ORDER BY user_id DESC")
    fun getAllUsers(): LiveData<MutableList<User>>

    @Query("SELECT * FROM user WHERE user_id = :userId")
    fun getUserByUserId(userId: Int): LiveData<User>
}