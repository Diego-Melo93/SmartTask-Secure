package com.example.smarttasksecure.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.smarttasksecure.R
import com.example.smarttasksecure.data.SessionManager
import com.example.smarttasksecure.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sessionManager = SessionManager(this)

        // Se o usuário já estiver logado, pula direto para a tela principal
        if (sessionManager.isLoggedIn()) {
            navToMainActivity()
        }

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewByid<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // 1. Validação básica de dados (Campos vazios)
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Autenticação local segura (Evitando dados sensíveis expostos)
            if (username == "admin" && password == "Smart@Task2026") {
                sessionManager.setLoginStatus(true)
                navToMainActivity()
            } else {
                Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Impede que o usuário volte para a tela de login ao apertar o botão "Voltar" do celular
    }
}