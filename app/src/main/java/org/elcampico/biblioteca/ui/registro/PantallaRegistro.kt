package org.elcampico.biblioteca.ui.registro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elcampico.biblioteca.R
import org.elcampico.biblioteca.ui.login.Montserrat
import org.elcampico.biblioteca.ui.theme.BibliotecaTheme

private val Oscurecido = Brush.verticalGradient(
    listOf(Color.Black.copy(alpha = 0.3f), Color.Black.copy(alpha = 0.75f))
)

@Composable
fun PantallaRegistro(
    onCrear: (nombre: String, correo: String, clave: String) -> Unit,
    onIrALogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var clave by rememberSaveable { mutableStateOf("") }
    var aceptaNormas by rememberSaveable { mutableStateOf(false) }
    var claveVisible by rememberSaveable { mutableStateOf(false) }

    // Capas: foto, degradado y contenido, igual que en el login
    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.fondo_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(Modifier.matchParentSize().background(Oscurecido))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crear cuenta",
                color = Color.White,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp
            )
            Text(
                text = "Solo te llevará un minuto",
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                        .copy(alpha = 0.92f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        leadingIcon = {
                            Icon(Icons.Default.Person, contentDescription = null)
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        leadingIcon = {
                            Icon(Icons.Default.Email, contentDescription = null)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña") },
                        leadingIcon = {
                            Icon(Icons.Default.Lock, contentDescription = null)
                        },
                        // Ampliación: icono final que muestra u oculta la contraseña
                        trailingIcon = {
                            IconButton(onClick = { claveVisible = !claveVisible }) {
                                Icon(
                                    painter = painterResource(
                                        if (claveVisible) R.drawable.ic_visibilidad_off
                                        else R.drawable.ic_visibilidad
                                    ),
                                    contentDescription =
                                        if (claveVisible) "Ocultar contraseña"
                                        else "Mostrar contraseña"
                                )
                            }
                        },
                        visualTransformation =
                            if (claveVisible) VisualTransformation.None
                            else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Row pone la casilla y su texto uno al lado del otro
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = aceptaNormas,
                            onCheckedChange = { aceptaNormas = it }
                        )
                        Text(
                            text = "Acepto las normas de la biblioteca",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Button(
                        onClick = { onCrear(nombre, correo, clave) },
                        enabled = nombre.isNotBlank() && correo.isNotBlank() &&
                            clave.isNotBlank() && aceptaNormas,
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Text(
                            "Crear cuenta",
                            fontFamily = Montserrat,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Dos piezas: un Text normal y un TextButton, que es el único pulsable
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "¿Ya tienes cuenta?",
                    color = Color.White.copy(alpha = 0.85f),
                    style = MaterialTheme.typography.bodyMedium
                )
                TextButton(onClick = onIrALogin) {
                    Text(
                        "Inicia sesión",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PantallaRegistroPreview() {
    BibliotecaTheme {
        PantallaRegistro(onCrear = { _, _, _ -> }, onIrALogin = {})
    }
}
