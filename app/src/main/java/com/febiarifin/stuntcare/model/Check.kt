package com.febiarifin.stuntcare.model

data class Check(
    val id: Long,
    val name: String,
    val sex: String,
    val age: Int,
    val birth_weight: Double,
    val birth_length: Double,
    val body_weight: Double,
    val body_length: Double,
    val asi_ekslusif: String,
    val status_stunting: Double,
)

val dummyCheck = listOf(
    Check(1, "Anak 1", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 1.0),
    Check(2, "Anak 2", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.6),
    Check(3, "Anak 3", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.8),
    Check(4, "Anak 4", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 1.0),
    Check(5, "Anak 5", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.3),
    Check(6, "Anak 6", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(7, "Anak 7", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(8, "Anak 8", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(9, "Anak 9", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(10, "Anak 10", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(11, "Anak 11", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(12, "Anak 12", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 0.4),
    Check(13, "Anak 13", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 1.0),
    Check(14, "Anak 14", "Laki-laki", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 1.0),
    Check(15, "Anak 15", "Perempuan", 12, 2.8, 48.0, 7.2, 70.2, "Yes", 1.0),
)
