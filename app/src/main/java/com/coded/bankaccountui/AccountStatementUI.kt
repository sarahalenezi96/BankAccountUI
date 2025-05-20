package com.coded.bankaccountui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coded.bankaccountui.model.AccountStatement

@Composable
fun AccountStatementCard(statement: AccountStatement) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = statement.date, style = MaterialTheme.typography.labelSmall)
            Text(text = statement.description, style = MaterialTheme.typography.titleMedium)
            Text(text = "Type: ${statement.transactionType}")
            Text(text = "Amount: ${statement.amount}")
            Text(text = "Balance After: ${statement.balanceAfter}")
        }
    }
}

@Composable
fun AccountStatementList(statements: List<AccountStatement>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(statements) { statement ->
            AccountStatementCard(statement = statement)
        }
    }
}

@Composable
fun AccountStatementScreen(modifier: Modifier = Modifier) {
    val dummyStatements = listOf(
        AccountStatement("2025-05-01", "Salary", "Deposit", 1500.0, 1500.0),
        AccountStatement("2025-05-02", "Grocery", "Withdraw", 100.0, 1400.0),
        AccountStatement("2025-05-03", "Shopping", "Withdraw", 75.0, 1325.0),
        AccountStatement("2025-05-04", "Refund", "Deposit", 50.0, 1375.0),
        AccountStatement("2025-05-05", "Fuel", "Withdraw", 10.0, 1365.0)
    )

    AccountStatementList(statements = dummyStatements)
}

