package com.shihab.cashregistersecurity.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shihab.cashregistersecurity.models.SessionState
import com.shihab.cashregistersecurity.viewmodel.RegisterViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterScreen(viewModel: RegisterViewModel = viewModel()
) {

    val headerDate = viewModel.simulatedCurrentDate.toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "System Date: $headerDate",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (viewModel.currentSessionState) {
            SessionState.CLOSED -> ClosedStateUI(viewModel)
            SessionState.ACTIVE -> ActiveDashboardUI(viewModel)
            SessionState.STALE -> StaleSessionBlockedUI(viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClosedStateUI(viewModel: RegisterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Lock,
            contentDescription = "Closed",
            modifier = Modifier.size(80.dp),
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Register is Closed", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Please open the register to start POS sales.", color = Color.Gray)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { viewModel.openRegister() }) {
            Text("Open Register Now")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ActiveDashboardUI(viewModel: RegisterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "✅ POS Dashboard Unlocked\nReady for Sales!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(
            onClick = { viewModel.simulateNextDay() },
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
        ) {
            Text("⏳ Simulate Next Day (Time Travel)")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.closeRegister() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Close Register (End of Day)")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StaleSessionBlockedUI(viewModel: RegisterViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Warning,
            contentDescription = "Warning",
            modifier = Modifier.size(80.dp),
            tint = Color(0xFFD32F2F)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "ACCESS DENIED!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFD32F2F)
        )
        Text(
            "You cannot process sales today.\nPlease close yesterday's register first.",
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.closeRegister() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text("Close Yesterday's Register")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen()
    }
}