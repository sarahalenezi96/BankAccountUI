package com.coded.bankaccountui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coded.bankaccountui.model.AccountStatement
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.Image
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp

@Composable
fun AccountStatementCard(statement: AccountStatement, isEven: Boolean) {
    val backgroundColor = if (isEven) Color.White else Color(0xFFD3D8DC)
    val textColor = Color(0xFF041325)

    val amountColor = if (statement.transactionType == "Deposit") Color(0xFF0F934A) else Color(0xFFF43332)
    val signedAmount = if (statement.transactionType == "Deposit") "${statement.amount}" else "${statement.amount}"
    val iconRes = if (statement.transactionType == "Deposit") R.drawable.deposit else R.drawable.withdraw

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = statement.description,
                    color = textColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = statement.date,
                    color = textColor,
                    fontSize = 13.sp
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(17.dp)
                        .padding(end = 6.dp)
                )
                Text(
                    text = signedAmount,
                    color = amountColor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
        itemsIndexed(statements) { index, statement ->
            AccountStatementCard(statement = statement, isEven = index % 2 == 0)
            // show divider between cards, not after the last one
            if (index != statements.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun AccountStatementScreen(modifier: Modifier = Modifier) {
    var selectedFilter by remember { mutableStateOf("All") }
    var sortDescending by remember { mutableStateOf(true) }
    var dropdownExpanded by remember { mutableStateOf(false) }

    val dummyStatements = listOf(
        AccountStatement("2025-05-01", "Salary", "Deposit", 1500.0, 1500.0),
        AccountStatement("2025-05-02", "Grocery", "Withdraw", 100.0, 1400.0),
        AccountStatement("2025-05-03", "Shopping", "Withdraw", 75.0, 1325.0),
        AccountStatement("2025-05-04", "Refund", "Deposit", 50.0, 1375.0),
        AccountStatement("2025-05-05", "Fuel", "Withdraw", 10.0, 1365.0),
        AccountStatement("2025-05-06", "Bonus", "Deposit", 200.0, 1565.0),
        AccountStatement("2025-05-07", "Dinner", "Withdraw", 30.0, 1535.0),
        AccountStatement("2025-05-08", "Online Order", "Withdraw", 45.0, 1490.0),
        AccountStatement("2025-05-09", "Interest", "Deposit", 15.0, 1505.0),
        AccountStatement("2025-05-10", "Phone Bill", "Withdraw", 20.0, 1485.0)
    )

    val filteredList = dummyStatements.filter {
        selectedFilter == "All" || it.transactionType == selectedFilter
    }

    val sortedList = if (sortDescending) {
        filteredList.sortedByDescending { it.date }
    } else {
        filteredList.sortedBy { it.date }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF011734), Color(0xFF1C4E75))
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp), //  spacing above title
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = "Account Statement",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp, bottom = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 16.dp, top = 4.dp, bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Filter (Chips)
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                listOf("All", "Deposit", "Withdraw").forEach { option ->
                    val isSelected = selectedFilter == option

                    if (isSelected) {
                        ElevatedFilterChip(
                            selected = true,
                            onClick = { selectedFilter = option },
                            label = {
                                Text(
                                    text = option,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF0A2647)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color.White
                            )
                        )
                    } else {
                        FilterChip(
                            selected = false,
                            onClick = { selectedFilter = option },
                            label = {
                                Text(
                                    text = option,
                                    fontSize = 14.sp,
                                    color = Color(0xFFCCCCCC)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                containerColor = Color(0xFF2E3B4E)
                            )
                        )
                    }
                }
            }
//sort
            Box {
                OutlinedButton(
                    onClick = { dropdownExpanded = true },
                    modifier = Modifier.height(32.dp),
                    shape = FilterChipDefaults.shape,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0xFF2E3B4E),
                        contentColor = Color(0xFFCCCCCC)
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Sort",
                            fontSize = 14.sp,
                            color = Color(0xFFCCCCCC)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown, // an arrow to indicate a dropdown menu
                            contentDescription = "Dropdown",
                            tint = Color(0xFFCCCCCC),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Recent to Oldest") },
                        onClick = {
                            sortDescending = true
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Oldest to Recent") },
                        onClick = {
                            sortDescending = false
                            dropdownExpanded = false
                        }
                    )
                }
            }
        }

        // statements list
        AccountStatementList(statements = sortedList)
    }
}