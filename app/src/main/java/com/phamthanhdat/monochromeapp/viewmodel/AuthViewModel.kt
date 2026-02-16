package com.phamthanhdat.monochromeapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class User(
    val email: String,
    val password: String,
    val name: String = ""
)

class AuthViewModel : ViewModel() {
    // Danh sách users đã đăng ký (giả lập database)
    private val registeredUsers = mutableListOf<User>()

    // Trạng thái đăng nhập
    var isLoggedIn by mutableStateOf(false)
        private set

    var currentUser by mutableStateOf<User?>(null)
        private set

    // Thông báo lỗi
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Đăng ký tài khoản mới
    fun register(name: String, email: String, password: String, confirmPassword: String): Boolean {
        errorMessage = null

        // Validate
        when {
            name.isBlank() -> {
                errorMessage = "Vui lòng nhập tên"
                return false
            }
            email.isBlank() -> {
                errorMessage = "Vui lòng nhập email"
                return false
            }
            !email.contains("@") -> {
                errorMessage = "Email không hợp lệ"
                return false
            }
            password.isBlank() -> {
                errorMessage = "Vui lòng nhập mật khẩu"
                return false
            }
            password.length < 6 -> {
                errorMessage = "Mật khẩu phải có ít nhất 6 ký tự"
                return false
            }
            password != confirmPassword -> {
                errorMessage = "Mật khẩu xác nhận không khớp"
                return false
            }
            registeredUsers.any { it.email == email } -> {
                errorMessage = "Email đã được đăng ký"
                return false
            }
        }

        // Thêm user mới
        val newUser = User(email = email, password = password, name = name)
        registeredUsers.add(newUser)

        return true
    }

    // Đăng nhập
    fun login(email: String, password: String): Boolean {
        errorMessage = null

        // Validate
        when {
            email.isBlank() -> {
                errorMessage = "Vui lòng nhập email"
                return false
            }
            password.isBlank() -> {
                errorMessage = "Vui lòng nhập mật khẩu"
                return false
            }
        }

        // Tìm user
        val user = registeredUsers.find { it.email == email && it.password == password }

        if (user != null) {
            currentUser = user
            isLoggedIn = true
            return true
        } else {
            errorMessage = "Email hoặc mật khẩu không đúng"
            return false
        }
    }

    // Đăng xuất
    fun logout() {
        currentUser = null
        isLoggedIn = false
    }

    // Xóa thông báo lỗi
    fun clearError() {
        errorMessage = null
    }
}