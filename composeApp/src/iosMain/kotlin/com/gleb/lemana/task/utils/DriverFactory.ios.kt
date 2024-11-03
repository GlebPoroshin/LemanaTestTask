package com.gleb.lemana.task.utils

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.gleb.lemana.task.LemenaTestAppDb

internal actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(LemenaTestAppDb.Schema, "lemana.db")
}
