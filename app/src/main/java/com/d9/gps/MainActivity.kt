package com.d9.gps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.d9.gps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : ListLocationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkAndRequestPermission()
        adapter = ListLocationAdapter()
        binding.rvLocation.adapter = adapter
        binding.rvLocation.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        val locManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0.0f, LocationListener {
            val data = LocationData(lat = it.latitude, lng = it.longitude, accuracy = it.accuracy)
            adapter.append(data)
            binding.rvLocation.scrollToPosition(adapter.itemCount)
        })
    }

    private fun checkAndRequestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WAKE_LOCK), 1999)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}