package seunghee.helloandroid._02_25_02_room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao() : Bookdao

    companion object{

        private var INSTANCE : BookDatabase? = null

        fun getDatabase(context : Context) : BookDatabase?{
            if(INSTANCE == null){
                synchronized(BookDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "seunghee4") // 기존 데이터베이스 명과 동일하게
                        .build()
                }
            }
            return INSTANCE
        }
    }

}