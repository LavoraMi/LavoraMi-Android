package com.andreafilice.lavorami

import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.circleLayer
import com.mapbox.maps.extension.style.layers.generated.lineLayer
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.layers.properties.generated.TextAnchor
import com.mapbox.maps.extension.style.expressions.dsl.generated.get
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.plugin.animation.easeTo
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import com.mapbox.maps.extension.style.layers.generated.fillLayer

object MapboxHelper {
    //*INITIALIZE MAP
    /// In this section, we initialize the values of the MapBox API with the LavoraMi token.
    private var initialized = false
    private var cachedKey: String? = null
    @JvmStatic
    fun init(token: String) {
        if (initialized && token != null && token == cachedKey) return
        cachedKey = token
        initialized = true
        com.mapbox.common.MapboxOptions.accessToken = token }

    //*API-INTERACTION METHODS
    /// This methods are used to interact with MapBox API, developed for Kotlin language
    @JvmStatic
    fun loadMap(mapView: MapView, darkMode: Boolean, onReady: MapReadyCallback) {
        /** In this function, we get the current mapView element (from XML file) and apply some changes, base on User Preferences.
         * @param mapView is the Map Fragment from the Activity Layout.
         * @param darkMode checks if the DarkMode is enabled by the User and apply the current style.
         * @param onReady is the function callback to enable when the Map is Ready and Loaded.
        */
        val style = if (darkMode) "mapbox://styles/mapbox/dark-v11" else "mapbox://styles/mapbox/streets-v12"
        mapView.mapboxMap.loadStyle(style) { onReady.onReady(mapView) }
    }

    @JvmStatic
    fun setCamera(mapView: MapView, latitude: Double, longitude: Double, zoom: Double) {
        /** In this function, we setup the camera view based on some datas from the StationsDB script.
         * @param mapView is the Map Fragment from the Activity Layout.
         * @param latitude is the Center Latitude of all stations calculated into the LinesDetailActivity script.
         * @param longitude is the Center Longitude of all stations calculated into the LinesDetailActivity script.
         * @param zoom is the zoom that the map assumes, change based on the type of transport map.
         */

        mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .center(Point.fromLngLat(longitude, latitude))
                .zoom(zoom)
                .build()
        )
    }

    @JvmStatic
    fun addCircleLayer(mapView: MapView, features: List<Feature>, hexColor: String, textHexColor: String = "#FFFFFF") {
        /** In this function, we draw Circle points on the map, based on the Line color and other variables.
         * @param mapView is the Map Fragment from the Activity Layout.
         * @param features is the list of the points to draw into the mapView Fragment.
         * @param hexColor is the color of the line in hexa notation.
         * @param textHexColor is the hex color of labels, if is light, set to black.
         */
        mapView.mapboxMap.getStyle { style ->
            style.addSource(geoJsonSource("marker-source") { featureCollection(FeatureCollection.fromFeatures(features)) })
            style.addLayer(circleLayer("marker-layer", "marker-source") {
                circleColor(literal(hexColor))
                circleRadius(5.0)
                circleStrokeColor(literal("#FFFFFF"))
                circleStrokeWidth(2.0)
            })
            style.addLayer(symbolLayer("marker-label-layer", "marker-source") {
                textField(get("name"))
                textSize(11.0)
                textColor(literal(textHexColor))
                textHaloWidth(1.5)
                textOffset(listOf(0.0, 1.5))
                textAnchor(TextAnchor.TOP)
                textAllowOverlap(false)
                textIgnorePlacement(false)
            })
        }
    }

    @JvmStatic
    fun makeStationFeature(latitude: Double, longitude: Double, name: String): Feature {
        /** This function gets the name of the station and draw the point with that Text.
         * @param latitude is the latitude of the point.
         * @param longitude is the longitude of the point.
         * @param name is the name of the station to display.
         */
        val props = com.google.gson.JsonObject()
        props.addProperty("name", name)

        return Feature.fromGeometry(Point.fromLngLat(longitude, latitude), props)
    }

    @JvmStatic
    fun addLineLayer(mapView: MapView, sourceId: String, layerId: String, points: List<Point>, hexColor: String, dashed: Boolean) {
        /** In this function, we draw the effective colored line of the layer of the selected Line, this is also used for multi-branching.
         * @param mapView is the Map Fragment from the Activity Layout.
         * @param sourceId is the ID of the Line to draw in the MapBox Fragment.
         * @param layerId is the Layer ID of the current branch selected.
         * @param points is the List of points to draw into the map.
         * @param hexColor is the color of the line in hexa notation.
         * @param dashed is a condition if the branch is now under construction or not.
         */
        mapView.mapboxMap.getStyle { style ->
            style.addSource(geoJsonSource(sourceId) { feature(Feature.fromGeometry(LineString.fromLngLats(points))) })
            style.addLayer(lineLayer(layerId, sourceId) {
                lineColor(literal(hexColor))
                lineWidth(4.0)
                lineCap(com.mapbox.maps.extension.style.layers.properties.generated.LineCap.ROUND)
                lineJoin(com.mapbox.maps.extension.style.layers.properties.generated.LineJoin.ROUND)

                if (dashed) lineDasharray(listOf(2.0, 2.0))
            })
        }
    }

    @JvmStatic
    fun enableUserLocation(mapView: MapView, followUser: Boolean) {
        /** Enables the blue user position dot on the map.
         * @param mapView is the map fragment in the Activity layout.
         * @param followUser If true, centers the camera on the user position at the first fix.
         */

        mapView.location.updateSettings {
            enabled = true
            pulsingEnabled = true
        }

        if (followUser) {
            val listener = OnIndicatorPositionChangedListener { point ->
                mapView.mapboxMap.setCamera(
                    CameraOptions.Builder()
                        .center(point)
                        .zoom(14.0)
                        .build()
                )
            }
            mapView.location.addOnIndicatorPositionChangedListener(listener)
        }
    }
    @JvmStatic
    fun zoomToUserLocation(mapView: MapView) {
        val listener = object : OnIndicatorPositionChangedListener {
            override fun onIndicatorPositionChanged(point: Point) {
                mapView.location.removeOnIndicatorPositionChangedListener(this)
                mapView.mapboxMap.easeTo(
                    CameraOptions.Builder()
                        .center(point)
                        .build(),
                    com.mapbox.maps.plugin.animation.MapAnimationOptions.mapAnimationOptions {
                        duration(600L)
                    },
                    object : android.animation.Animator.AnimatorListener {
                        override fun onAnimationEnd(animation: android.animation.Animator) {
                            mapView.mapboxMap.easeTo(
                                CameraOptions.Builder()
                                    .zoom(14.0)
                                    .build(),
                                com.mapbox.maps.plugin.animation.MapAnimationOptions.mapAnimationOptions {
                                    duration(400L)
                                }
                            )
                        }
                        override fun onAnimationStart(animation: android.animation.Animator) {}
                        override fun onAnimationCancel(animation: android.animation.Animator) {}
                        override fun onAnimationRepeat(animation: android.animation.Animator) {}
                    }
                )
            }
        }
        mapView.location.addOnIndicatorPositionChangedListener(listener)
    }

    @JvmStatic
    fun clearAllLineLayers(mapView: MapView) {
        mapView.mapboxMap.getStyle { style ->
            val layerIds = style.styleLayers.map { it.id }
            val sourceIds = style.styleSources.map { it.id }

            for (layerId in layerIds) {
                if (layerId == "line-layer-main" || layerId.startsWith("line-layer-branch-")) {
                    style.removeStyleLayer(layerId)
                }
            }
            for (sourceId in sourceIds) {
                if (sourceId == "line-source-main" || sourceId.startsWith("line-source-branch-")) {
                    style.removeStyleSource(sourceId)
                }
            }
        }
    }

    @JvmStatic
    fun clearMarkers(mapView: MapView) {
        mapView.mapboxMap.getStyle { style ->
            if (style.styleLayerExists("marker-label-layer")) style.removeStyleLayer("marker-label-layer")
            if (style.styleLayerExists("marker-layer")) style.removeStyleLayer("marker-layer")
            if (style.styleSourceExists("marker-source")) style.removeStyleSource("marker-source")
        }
    }
    interface MapReadyCallback {
        //*INTERFACE CLASS
        ///This is the interface to implement into LinesDetailActivity.
        fun onReady(mapView: MapView)
    }
}