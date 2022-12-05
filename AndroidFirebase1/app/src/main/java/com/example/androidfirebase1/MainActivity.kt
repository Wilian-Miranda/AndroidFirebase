package com.example.androidfirebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPass:EditText
    private lateinit var btLogin:Button
    private lateinit var btRegistrar:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etEmail = findViewById(R.id.lv_login_et_email)
        etPass = findViewById(R.id.lv_login_et_pass)
        btLogin = findViewById(R.id.lv_login_bt_login)
        btRegistrar = findViewById(R.id.lv_login_bt_register)

        ejecutarAnalityc()
        setup()
    }


    //indicamos que se haa usado la aplicacion
    fun ejecutarAnalityc(){
        val analitycs:FirebaseAnalytics =FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("mensaje","Integracion de firabase analitycs")
        analitycs.logEvent("InitScreen",bundle)
    }

    fun setup(){
        title = "Autentificación"
        btRegistrar.setOnClickListener {
            if (etEmail.text.trim().isNotEmpty() && etEmail.text.trim().isNotBlank()){
                if (etPass.text.trim().isNotEmpty() && etPass.text.trim().isNotBlank()){
                    SUser.registerUser(this,etEmail.text.toString(),etPass.text.toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        println("Usuario registrado?: ${User.getUserState()}")
                        if (User.getUserState()){
                            mostrarPrincipal()
                        }
                    }
                }else{
                    Util.mostraAlert(this,"Campo de contraseña vacío o nulo")
                }
            }else{
                Util.mostraAlert(this,"Campo de email vacío o nulo")
            }
        }
        btLogin.setOnClickListener {
            if (etEmail.text.trim().isNotEmpty() && etEmail.text.trim().isNotBlank()){
                if (etPass.text.trim().isNotEmpty() && etPass.text.trim().isNotBlank()) {
                    SUser.loginUser(this, etEmail.text.toString(), etPass.text.toString())
                    CoroutineScope(Dispatchers.IO).launch {
                        Thread.sleep(5000)
                        if (User.getUserState()) {
                            mostrarPrincipal()
                        }
                    }

                }else{
                    Util.mostraAlert(this,"Campo de contraseña vacío o nulo")
                }
            }else{
                Util.mostraAlert(this,"Campo de email vacío o nulo")
            }
        }
    }
    fun mostrarPrincipal(){
        val ventanaIntent = Intent(this,PrincipalActivity::class.java).apply {
            putExtra("email",User.getEmail())
            putExtra("proveedor",User.getProveedor())
        }
        startActivity(ventanaIntent)
    }

}
enum class TiposProveedor{
    BASICO
}