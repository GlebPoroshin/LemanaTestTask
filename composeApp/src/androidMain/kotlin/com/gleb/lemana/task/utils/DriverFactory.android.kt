package com.gleb.lemana.task.utils

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.gleb.lemana.task.AndroidApp
import com.gleb.lemana.task.LemenaTestAppDb

internal actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(LemenaTestAppDb.Schema, AndroidApp.INSTANCE, "lemana.db")
}
