package ru.fefu.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.fefu.android.ui.startactivity.StartActivityFragment
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class ActivityActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.menu_fragment, StartActivityFragment.newInstance())
                .commitNow()
        }
        Configuration.getInstance().userAgentValue = packageName
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)

        mapView.setBuiltInZoomControls(true)
        mapView.controller.setZoom(18)
        mapView.controller.setCenter(GeoPoint(43.0293429, 131.889512))
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }
}