package th.co.cdgs.mobile.local_database_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class],version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        var INSTANCE: AppDatabase? = null
        var sLock = Any()

        fun getInstance(context: Context): AppDatabase? {
            synchronized(sLock){
                if(INSTANCE == null) {
                    INSTANCE = Room
                        .databaseBuilder(context, AppDatabase::class.java, "user.dbx")
                        .addMigrations(MIGRATION_1_to_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION_1_to_2: Migration = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN age INTEGER")
            }
        }
    }

    abstract fun userDao(): UserDao
}