package com.coded.bankaccountui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.coded.bankaccountui.ui.theme.BankAccountUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankAccountUITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                AccountStatementScreen(modifier = Modifier.padding(innerPadding))
            }

            }
        }
    }
}
