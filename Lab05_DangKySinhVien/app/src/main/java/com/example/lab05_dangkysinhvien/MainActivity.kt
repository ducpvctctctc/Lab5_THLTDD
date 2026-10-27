package com.example.lab05_dangkysinhvien

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.example.lab05_dangkysinhvien.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var lastClickTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupEditTextEvents()
        setupRadioEvents()
        setupCheckBoxEvents()
        setupButtonEvents()
    }

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }

    // Sự kiện nhập liệu EditText
    private fun setupEditTextEvents() {

        binding.edtHoTen.doAfterTextChanged {
            binding.tilHoTen.error = null

            if (!it.isNullOrEmpty()) {
                showToast("Đang nhập họ tên: $it")
            }
        }

        binding.edtEmail.doAfterTextChanged {
            binding.tilEmail.error = null

            if (!it.isNullOrEmpty()) {
                showToast("Đang nhập Email")
            }
        }

        binding.edtMatKhau.doAfterTextChanged {
            binding.tilMatKhau.error = null

            if (!it.isNullOrEmpty()) {
                showToast("Đang nhập mật khẩu")
            }
        }

        binding.edtSoDienThoai.doAfterTextChanged {
            binding.tilSoDienThoai.error = null

            if (!it.isNullOrEmpty()) {
                showToast("Đang nhập số điện thoại: $it")
            }
        }
    }

    // Sự kiện RadioButton
    private fun setupRadioEvents() {

        binding.rgGioiTinh.setOnCheckedChangeListener {
                _, checkedId ->

            val gioiTinh = when (checkedId) {
                R.id.rbNam -> "Nam"
                R.id.rbNu -> "Nữ"
                R.id.rbKhac -> "Khác"
                else -> "Chưa chọn"
            }

            showToast("Bạn đã chọn giới tính: $gioiTinh")
        }
    }

    // Sự kiện CheckBox
    private fun setupCheckBoxEvents() {

        val checkBoxes = listOf(
            binding.cbFacebook,
            binding.cbZalo,
            binding.cbTikTok,
            binding.cbInstagram
        )

        checkBoxes.forEach { checkBox ->

            checkBox.setOnCheckedChangeListener {
                    buttonView, isChecked ->

                val trangThai = if (isChecked) {
                    "Đã chọn"
                } else {
                    "Đã bỏ chọn"
                }

                showToast(
                    "$trangThai ${buttonView.text}"
                )
            }
        }
    }

    // Kiểm tra dữ liệu
    private fun validateForm(): Boolean {

        var isValid = true

        val hoTen =
            binding.edtHoTen.text.toString().trim()

        val email =
            binding.edtEmail.text.toString().trim()

        val matKhau =
            binding.edtMatKhau.text.toString()

        val soDienThoai =
            binding.edtSoDienThoai.text.toString().trim()

        if (!FormValidator.isValidName(hoTen)) {
            binding.tilHoTen.error =
                "Họ tên phải có ít nhất 3 ký tự"
            isValid = false
        }

        if (!FormValidator.isValidEmail(email)) {
            binding.tilEmail.error =
                "Email không đúng định dạng"
            isValid = false
        }

        if (!FormValidator.isValidPassword(matKhau)) {
            binding.tilMatKhau.error =
                "Mật khẩu phải có ít nhất 6 ký tự"
            isValid = false
        }

        if (!FormValidator.isValidPhone(soDienThoai)) {
            binding.tilSoDienThoai.error =
                "Số điện thoại phải có 10 số hợp lệ"
            isValid = false
        }

        if (binding.rgGioiTinh.checkedRadioButtonId == -1) {
            showToast("Vui lòng chọn giới tính")
            isValid = false
        }

        return isValid
    }

    // Lấy mạng xã hội đã chọn
    private fun getMangXaHoi(): String {

        val danhSach = mutableListOf<String>()

        if (binding.cbFacebook.isChecked) {
            danhSach.add("Facebook")
        }

        if (binding.cbZalo.isChecked) {
            danhSach.add("Zalo")
        }

        if (binding.cbTikTok.isChecked) {
            danhSach.add("TikTok")
        }

        if (binding.cbInstagram.isChecked) {
            danhSach.add("Instagram")
        }

        return if (danhSach.isEmpty()) {
            "Không chọn"
        } else {
            danhSach.joinToString(", ")
        }
    }

    // Sự kiện đăng ký
    private fun setupButtonEvents() {

        binding.btnDangKy.setOnClickListener {

            // Chống nhấn liên tục
            val currentTime =
                SystemClock.elapsedRealtime()

            if (currentTime - lastClickTime < 1000L) {
                return@setOnClickListener
            }

            lastClickTime = currentTime

            if (!validateForm()) {
                showToast("Vui lòng kiểm tra lại thông tin")
                return@setOnClickListener
            }

            val gioiTinh = when (
                binding.rgGioiTinh.checkedRadioButtonId
            ) {
                R.id.rbNam -> "Nam"
                R.id.rbNu -> "Nữ"
                R.id.rbKhac -> "Khác"
                else -> "Chưa chọn"
            }

            val intent = Intent(
                this,
                ChiTietActivity::class.java
            )

            intent.putExtra(
                "hoTen",
                binding.edtHoTen.text.toString().trim()
            )

            intent.putExtra(
                "email",
                binding.edtEmail.text.toString().trim()
            )

            intent.putExtra(
                "soDienThoai",
                binding.edtSoDienThoai.text.toString().trim()
            )

            intent.putExtra("gioiTinh", gioiTinh)

            intent.putExtra(
                "mangXaHoi",
                getMangXaHoi()
            )

            showToast("Đăng ký thành công!")

            startActivity(intent)
        }
    }
}
