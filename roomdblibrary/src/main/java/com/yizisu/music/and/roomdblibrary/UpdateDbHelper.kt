package com.yizisu.music.and.roomdblibrary

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.github.yuweiguocn.library.greendao.MigrationHelper
import com.greendao.gen.AlbumInfoTableDao
import com.greendao.gen.DaoMaster
import com.greendao.gen.SingerInfoTableDao
import com.greendao.gen.SongInfoTableDao
import org.greenrobot.greendao.database.Database

class UpdateDbHelper(context: Context, name: String, factory: SQLiteDatabase.CursorFactory?) :
    DaoMaster.OpenHelper(context, name, factory) {
    override fun onUpgrade(db: Database, oldVersion: Int, newVersion: Int) {
        MigrationHelper.migrate(
            db,
            object : MigrationHelper.ReCreateAllTableListener {

                override fun onCreateAllTables(db: Database, ifNotExists: Boolean) {
                    DaoMaster.createAllTables(db, ifNotExists)
                }

                override fun onDropAllTables(db: Database, ifExists: Boolean) {
                    DaoMaster.dropAllTables(db, ifExists)
                }
            },
            AlbumInfoTableDao::class.java,
            SingerInfoTableDao::class.java,
            SongInfoTableDao::class.java
        )
    }
}
