package com.example.bookReader.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookReader.model.MUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel : ViewModel() {
    val loadingState = MutableStateFlow(LoadingState.IDLE)
    val auth: FirebaseAuth = Firebase.auth
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("LoginViewModel task successful", "${task.result}")
                        home()
                        // TODO: take to home
                    } else {
                        Log.d("LoginViewModel", "${task.result}")
                    }

                }
            } catch (ex: Exception) {
                println(ex.message)
            }
        }


    fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) =
        viewModelScope.launch {
            try {
                if (_loading.value == false) {
                    _loading.value = true
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val displayName = task.result.user?.email?.split("@")?.get(0)
                                createUser(displayName)
                                Log.d("LoginViewModel user created successful", "${task.result}")
                                home()
                            } else {
                                Log.d("LoginViewModel create user", "${task.result}")
                            }

                        }
                    _loading.value = false
                }
            } catch (ex: Exception) {
                println(ex.message)
            }
        }

    private fun createUser(displayName: String?) {

        val user = MUser(
            id = null,
            userId = auth.currentUser?.uid.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is to easy",
            profession = "Android Developer"
        ).toMap()


        FirebaseFirestore.getInstance().collection("user")
            .add(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("add user", task.toString())
                } else {
                    Log.d("add user", "error")
                }
            }

    }


}