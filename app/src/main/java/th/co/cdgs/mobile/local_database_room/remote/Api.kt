package th.co.cdgs.mobile.local_database_room.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import th.co.cdgs.mobile.local_database_room.database.User

interface Api {

//    @FormUrlEncoded
//    @POST()
//    fun getUrlEncode(@Field("id") id :String)

//    @Multipart
//    @POST
//    fun getMultipart(@Part("id") id: ResponseBody)
    @GET("user/{by}")
    fun getAllUser(@Path("by") createBy: String): Call<MutableList<User>>

    //    @GET("user")
//    fun getAllUser1(@Query("by") createBy: String)
    @GET("user/{by}/{id}")
    fun getUserByUserId(
        @Path("by") createBy: String,
        @Path("id") userId: Int
    ): Call<User>

    @POST("user")
    fun addUser(@Body user: User): Call<Map<String, Any>>

    @PUT("user")
    fun updateUser(@Body user: User): Call<Map<String, Any>>

    @DELETE("user/{by}/{id}")
    fun deleteUser(
        @Path("by") createBy: String,
        @Path("id") userId: Int
     ): Call<Map<String, Any>>
}