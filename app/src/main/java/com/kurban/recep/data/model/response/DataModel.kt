package com.kurban.recep.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kurban.recep.data.model.entity.WrapperType
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*


@Parcelize
data class DataModel(
    @SerializedName("wrapperType")
    val wrapperType: WrapperType,
    val advisories: List<String>,
    @SerializedName("appletvScreenshotUrls")
    val appleTvScreenshotUrls: List<String>,
    val artistId: Int,
    val artistName: String,
    val artistViewUrl: String,
    val artworkUrl100: String,
    val artworkUrl512: String,
    val artworkUrl60: String,
    val averageUserRating: Double,
    val averageUserRatingForCurrentVersion: Double,
    val bundleId: String,
    val contentAdvisoryRating: String,
    val currency: String,
    val currentVersionReleaseDate: String,
    val description: String,
    val features: List<String>,
    val fileSizeBytes: String,
    val formattedPrice: String,
    val genreIds: List<String>,
    val genres: List<String>,
    val ipadScreenshotUrls: List<String>,
    val isGameCenterEnabled: Boolean,
    val isVppDeviceBasedLicensingEnabled: Boolean,
    val kind: String,
    val languageCodesISO2A: List<String>,
    val minimumOsVersion: String,
    val price: Double,
    val primaryGenreId: Int,
    val primaryGenreName: String,
    val releaseDate: String,
    val releaseNotes: String,
    val screenshotUrls: List<String>,
    val sellerName: String,
    val sellerUrl: String,
    val supportedDevices: List<String>,
    val trackCensoredName: String,
    val trackContentRating: String,
    val trackId: Int,
    val trackName: String,
    val trackViewUrl: String,
    val userRatingCount: Int,
    val userRatingCountForCurrentVersion: Int,
    val version: String,
    val artworkUrl30: String,
    val collectionArtistId: Int,
    val collectionArtistViewUrl: String,
    val collectionCensoredName: String,
    val collectionExplicitness: String,
    val collectionHdPrice: Double,
    val collectionId: Int,
    val collectionName: String,
    val collectionPrice: Double,
    val collectionViewUrl: String,
    val country: String,
    val discCount: Int,
    val discNumber: Int,
    val longDescription: String,
    val previewUrl: String,
    val shortDescription: String,
    val trackCount: Int,
    val trackExplicitness: String,
    val trackHdPrice: Double,
    val trackHdRentalPrice: Double,
    val trackNumber: Int,
    val trackPrice: Double,
    val trackRentalPrice: Double,
    val trackTimeMillis: Int,
    val collectionArtistName: String,
    val isStreamable: Boolean,
    val artistIds: List<Int>,
) : Parcelable {

    fun releaseDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        calendar.timeInMillis = dateFormat.parse(releaseDate).time
        return calendar.get(Calendar.YEAR).toString()
    }
}