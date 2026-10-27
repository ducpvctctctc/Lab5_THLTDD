package com.example.lab05_dangkysinhvien

object FormValidator {

    private val emailRegex =
        Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    private val phoneRegex =
        Regex("^(03|05|07|08|09)\\d{8}$")

    fun isValidName(name: String): Boolean {
        return name.trim().length >= 3
    }

    fun isValidEmail(email: String): Boolean {
        return emailRegex.matches(email.trim())
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidPhone(phone: String): Boolean {
        return phoneRegex.matches(phone.trim())
    }
}
