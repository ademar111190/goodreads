package ademar.goodreads.core.database

import com.raizlabs.android.dbflow.annotation.Database

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
class AppDatabase {

    companion object {

        const val NAME = "goodreads";
        const val VERSION = 1;

    }

}
