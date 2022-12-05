package com.example.androidfirebase1

import android.content.Context
import android.util.Log

object SUser {
    fun registerUser(_context: Context, _email:String, _pass:String){
        FirebaseConnection.firebaseAuth.createUserWithEmailAndPassword(_email,_pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    User.setProveedor(TiposProveedor.BASICO)
                    User.setUserState(true)
                    Log.d("SUser", "Usuario ${_email} agregado con exito")
                    it.result.user?.email?.toString()?.let { it1 -> User.setEmail(it1) }
                    println(""+User.getUserState()+" "+User.getEmail())
                }
            }.addOnFailureListener {
                Util.mostraAlert(_context,"Error al registrar usuario. Detalles: \n${it}")
            }
    }
    fun loginUser(_context: Context, _email:String, _pass:String){
        FirebaseConnection.firebaseAuth.signInWithEmailAndPassword(_email,_pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    User.setProveedor(TiposProveedor.BASICO)
                    User.setUserState(true)
                    Log.d("SUser", "Usuario ${_email} logeado con exito")
                    it.result.user?.email?.toString()?.let { it1 -> User.setEmail(it1) }
                }
            }.addOnFailureListener {
                Util.mostraAlert(_context,"Error al iniciar sesion. Detalles: \n${it}")
            }
    }
}