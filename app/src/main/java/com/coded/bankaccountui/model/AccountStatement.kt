package com.coded.bankaccountui.model

data class AccountStatement(
    val date: String,
    val description: String,
    val transactionType: String,
    val amount: Double,
    val balanceAfter: Double
)