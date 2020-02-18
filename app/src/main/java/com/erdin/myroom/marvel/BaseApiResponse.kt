package com.erdin.myroom.marvel

import com.google.gson.annotations.SerializedName

open class BaseApiResponse {

    @SerializedName("code")
    var code: String = ""

    @SerializedName("status")
    var status: String = ""

    @SerializedName("copyright")
    var copyright: String = ""

    @SerializedName("attributionText")
    var attributionText: String = ""

    @SerializedName("attributionHTML")
    var attributionHTML: String = ""

    @SerializedName("etag")
    var etag: String = ""

}