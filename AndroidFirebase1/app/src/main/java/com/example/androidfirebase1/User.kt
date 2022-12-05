package com.example.androidfirebase1

object User {
    private lateinit var email:String
    private lateinit var proveedor:TiposProveedor
    private var userState:Boolean = false
    fun getEmail():String{
        return this.email
    }
    fun setEmail(_email:String){
        this.email = _email
    }
    fun getProveedor():TiposProveedor{
        return this.proveedor
    }
    fun setProveedor(_proveedor:TiposProveedor){
        this.proveedor = _proveedor
    }
    fun getUserState():Boolean{
        return this.userState
    }
    fun setUserState(_userState:Boolean){
        this.userState = _userState
    }
}