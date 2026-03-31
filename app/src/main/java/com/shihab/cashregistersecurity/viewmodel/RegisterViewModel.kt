package com.shihab.cashregistersecurity.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.shihab.cashregistersecurity.models.SessionState
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class RegisterViewModel : ViewModel() {
    var isRegisterOpen by mutableStateOf(false)
        private set

    var openedDate by mutableStateOf<LocalDate?>(null)
        private set

    var simulatedCurrentDate by mutableStateOf(LocalDate.now())
        private set

    val currentSessionState by derivedStateOf {
        if (!isRegisterOpen) {
            SessionState.CLOSED
        } else {
            if (openedDate != null && openedDate!!.isBefore(simulatedCurrentDate)) {
                SessionState.STALE
            } else {
                SessionState.ACTIVE
            }
        }
    }

    fun openRegister() {
        isRegisterOpen = true
        openedDate = simulatedCurrentDate
    }

    fun closeRegister() {
        isRegisterOpen = false
        openedDate = null
    }

    fun simulateNextDay() {
        simulatedCurrentDate = simulatedCurrentDate.plusDays(1)
    }
}