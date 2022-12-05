package com.example.androidfirebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class PrincipalActivity : AppCompatActivity() {
    private lateinit var tvEmail:TextView
    private lateinit var tvProveedor: TextView
    private lateinit var btCerrar:Button

    private lateinit var etNombre:EditText
    private lateinit var etTelefono:EditText
    private lateinit var etMunincipio:EditText

    private lateinit var btConsultar:Button
    private lateinit var btBorrar:Button
    private lateinit var btGuardar:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        tvEmail = findViewById(R.id.lv_principal_tv_email)
        tvProveedor = findViewById(R.id.lv_principal_tv_proveedor)
        etMunincipio = findViewById(R.id.lv_principal_et_municipio)
        etTelefono = findViewById(R.id.lv_principal_et_telefono)
        etNombre = findViewById(R.id.lv_principal_et_nombre)

        btCerrar = findViewById(R.id.lv_principal_bt_cerrar)

        btGuardar = findViewById(R.id.lv_principal_bt_guardar)
        btConsultar = findViewById(R.id.lv_principal_bt_consultar)
        btBorrar = findViewById(R.id.lv_principal_bt_borrar)

        val email = this.intent.extras?.getString("email").toString()
        val proveedor = this.intent.extras?.get("proveedor") as TiposProveedor
        setup(email,proveedor.toString())
        println(email +" "+proveedor)
    }

    fun setup(_email:String,_proveedor:String){
        title = "Pricipal"
        tvEmail.text = _email
        tvProveedor.text = _proveedor

        btCerrar.setOnClickListener {
            FirebaseConnection.firebaseAuth.signOut()
        }
        btGuardar.setOnClickListener {
            FirebaseConnection.refGlobal.set(hashMapOf (
                "nombre" to etNombre.text.toString(),
                "proveedor" to tvProveedor.text.toString(),
                "email" to tvEmail.text.toString(),
                "telefono" to etTelefono.text.toString(),
                "municipio" to etMunincipio.text.toString()
                ))
        }
        btConsultar.setOnClickListener {
            FirebaseConnection.refGlobal.get().addOnCompleteListener {
                etNombre.setText(it.result.getString("nombre"))
                tvProveedor.setText(it.result.getString("proveedor"))
                tvEmail.setText(it.result.getString("email"))
                etTelefono.setText(it.result.getString("telefono"))
                etMunincipio.setText(it.result.getString("municipio"))
            }
        }
        btBorrar.setOnClickListener {
            FirebaseConnection.refGlobal.delete()
        }
    }


}