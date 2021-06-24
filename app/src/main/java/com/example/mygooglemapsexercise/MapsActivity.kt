package com.example.mygooglemapsexercise

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mygooglemapsexercise.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var buttonSend: Button
    private lateinit var editTextLatitude: EditText
    private lateinit var editTextLongitude: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        buttonSend = findViewById(R.id.buttonSend)
        editTextLatitude = findViewById(R.id.editTextLatitude)
        editTextLongitude = findViewById(R.id.editTextLongitude)

        val height = 100
        val width = 100
        val b = BitmapFactory.decodeResource(resources, R.drawable.icon)
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, true)
        val smallMarkerIcon = BitmapDescriptorFactory.fromBitmap(smallMarker)

        buttonSend.setOnClickListener {
            val latitude = editTextLatitude.text.toString()
            val longitude = editTextLongitude.text.toString()
            val location = LatLng(latitude.toDouble(), longitude.toDouble())
            mMap.addMarker(
                MarkerOptions().position(location).title("You are here.")
                    .icon(smallMarkerIcon)
            )
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}