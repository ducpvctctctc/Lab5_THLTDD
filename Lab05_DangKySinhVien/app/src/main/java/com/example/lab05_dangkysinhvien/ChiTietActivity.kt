package com.example.lab05_dangkysinhvien

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab05_dangkysinhvien.databinding.ActivityChiTietBinding

class ChiTietActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChiTietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChiTietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hoTen = intent.getStringExtra("hoTen")
            ?: "Không có dữ liệu"

        val email = intent.getStringExtra("email")
            ?: "Không có dữ liệu"

        val soDienThoai =
            intent.getStringExtra("soDienThoai")
                ?: "Không có dữ liệu"

        val gioiTinh =
            intent.getStringExtra("gioiTinh")
                ?: "Chưa chọn"

        val mangXaHoi =
            intent.getStringExtra("mangXaHoi")
                ?: "Không chọn"

        binding.tvHoTen.text =
            "Họ và tên: $hoTen"

        binding.tvEmail.text =
            "Email: $email"

        binding.tvSoDienThoai.text =
            "Số điện thoại: $soDienThoai"

        binding.tvGioiTinh.text =
            "Giới tính: $gioiTinh"

        binding.tvMangXaHoi.text =
            "Mạng xã hội: $mangXaHoi"

        binding.btnQuayLai.setOnClickListener {
            finish()
        }
    }
}
