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

package com.example.android.roomwordssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import java.sql.Date

/**
 * Activity for entering a word.
 */

class NewWordActivity : AppCompatActivity() {

    private val wordViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as UsersApplication).repository)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)
        val editWordView_firstName = findViewById<EditText>(R.id.edit_firstName)
        val editWordView_lastName = findViewById<EditText>(R.id.edit_lastName)
        val editWordView_birthDate = findViewById<EditText>(R.id.edit_birthDate)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView_firstName.text) || TextUtils.isEmpty(editWordView_lastName.text) || TextUtils.isEmpty(editWordView_birthDate.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val firstName = editWordView_firstName.text.toString()
                val lastName = editWordView_lastName.text.toString()
                val birthDate = editWordView_birthDate.text.toString()
                val user = User(firstName = firstName, lastName = lastName, birthDate = Date.parse(birthDate))
                wordViewModel.insert(user)
                /*replyIntent.putExtra(EXTRA_REPLY, firstName)
                setResult(Activity.RESULT_OK, replyIntent)*/
            }
            finish()
        }
    }

    /*companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }*/
}
