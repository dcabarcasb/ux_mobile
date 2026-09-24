package com.ejemplo.alarmafamiliar.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.ejemplo.alarmafamiliar.ui.components.BrandHeader
import com.ejemplo.alarmafamiliar.ui.components.PrimaryButton
import com.ejemplo.alarmafamiliar.ui.theme.Navy
import com.ejemplo.alarmafamiliar.ui.theme.PrimaryBlue

@Composable
fun LoginScreen(onLogin: () -> Unit, onRegister: () -> Unit) {
    var email by remember { mutableStateOf("correo@ejemplo.com") }
    var password by remember { mutableStateOf("contrasena123") }

    Column(modifier = Modifier.fillMaxSize()) {
        BrandHeader()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(28.dp))
            Text(
                text = "Iniciar sesión",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Navy
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Ingresa con tu cuenta para continuar",
                fontSize = 14.sp,
                color = Navy.copy(alpha = 0.6f)
            )
            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                leadingIcon = { Icon(Icons.Filled.Mail, contentDescription = null, tint = PrimaryBlue) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null, tint = PrimaryBlue) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    focusedLabelColor = PrimaryBlue
                )
            )

            Spacer(Modifier.height(28.dp))
            PrimaryButton(text = "Ingresar", onClick = onLogin)
            Spacer(Modifier.height(12.dp))

            TextButton(
                onClick = onLogin,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = PrimaryBlue,
                    fontSize = 14.sp
                )
            }

            Spacer(Modifier.height(16.dp))
            HorizontalDivider(color = Navy.copy(alpha = 0.1f))
            Spacer(Modifier.height(12.dp))

            Text(
                text = "¿No tienes una cuenta?",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 14.sp,
                color = Navy.copy(alpha = 0.6f)
            )
            Spacer(Modifier.height(4.dp))
            TextButton(
                onClick = onRegister,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "REGISTRARME",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryBlue,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(Modifier.height(32.dp))
        }
    }
}