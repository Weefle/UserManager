package com.example.android
/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.roomwordssample.User
import com.example.android.roomwordssample.UserDao
import com.example.android.roomwordssample.UserRoomDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.*

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: UserRoomDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, UserRoomDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() = runBlocking {
        val user = User(0, "John","Doe", Date(1974, 1, 1).time)
        userDao.insert(user)
        val allUsers = userDao.getUsers().first()
        assertEquals(allUsers[0].id, user.id)
    }

    @Test
    @Throws(Exception::class)
    fun getAllWords() = runBlocking {
        val user = User(0, "John","Doe", Date(1974, 1, 1).time)
        userDao.insert(user)
        val user2 = User(1, "Baby","Doe", Date(1994, 1, 1).time)
        userDao.insert(user2)
        val allUsers = userDao.getUsers().first()
        assertEquals(allUsers[0].id, user.id)
        assertEquals(allUsers[1].id, user2.id)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() = runBlocking {
        val user = User(0, "John","Doe", Date(1974, 1, 1).time)
        userDao.insert(user)
        val user2 = User(1, "Baby","Doe", Date(1994, 1, 1).time)
        userDao.insert(user2)
        userDao.deleteAll()
        val allUsers = userDao.getUsers().first()
        assertTrue(allUsers.isEmpty())
    }
}