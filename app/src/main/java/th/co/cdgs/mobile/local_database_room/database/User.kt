package th.co.cdgs.mobile.local_database_room.database

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    @SerializedName("userId")
    var userId: Int? = null,
    @ColumnInfo(name = "first_name")
    @SerializedName("firstName")
    var firstName: String? = null,
    @ColumnInfo(name = "last_name")
    @SerializedName("lastName")
    var lastName: String? = null,
    @Embedded
    @SerializedName("contact")
    var contact: Contact? = Contact(),
    @Ignore
    @SerializedName("birthDay")
    var birthDay: String? = null,
    @ColumnInfo(name = "age")
    @SerializedName("age")
    var age: Int? = null,
    @Ignore
    @SerializedName("createBy")
    var createBy: String? = null
)
