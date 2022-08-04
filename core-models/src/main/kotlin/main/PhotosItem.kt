package main

data class PhotosItem(
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int,
    val next_page: String
) {
    data class Photo(
        val id: Int,
        val url: String,
        val photographer: String,
        val src: Src,
    ) {
        data class Src(
            val large: String,
            val medium: String,
            val small: String,
        )
    }
}