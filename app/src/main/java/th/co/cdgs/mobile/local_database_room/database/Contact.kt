package th.co.cdgs.mobile.local_database_room.database

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Contact(
    @ColumnInfo(name = "facebook")
    @SerializedName("facebook")
    var facebook: String? = null,
    @ColumnInfo(name = "line_id")
    @SerializedName("lineId")
    var lineId: String? = null,
    @ColumnInfo(name = "instagram")
    @SerializedName("instagram")
    var instagram: String? = null
)