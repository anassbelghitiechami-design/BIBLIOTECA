package org.elcampico.biblioteca

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.elcampico.biblioteca.ui.login.PantallaLogin
import org.elcampico.biblioteca.ui.theme.BibliotecaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // fondo bajo la barra de estado
        setContent {
            BibliotecaTheme {
                PantallaLogin(onEntrar = { usuario, _ ->
                    Toast.makeText(
                        this@MainActivity, "Hola, $usuario", Toast.LENGTH_SHORT
                    ).show()
                })
            }
        }
    }
}
