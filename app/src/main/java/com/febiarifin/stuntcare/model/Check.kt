package com.febiarifin.stuntcare.model

data class Check(
    val id: Int,
    val name: String,
    val sex: String,
    val age: String,
    val birth_weight: String,
    val birth_length: String,
    val body_weight: String,
    val body_length: String,
    val asi_ekslusif: String,
    val result: Double,
)

val dummyCheck = listOf(
    Check(1, "Anak 1", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(2, "Anak 2", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.6),
    Check(3, "Anak 3", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.8),
    Check(4, "Anak 4", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 1.0),
    Check(5, "Anak 5", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.3),
    Check(6, "Anak 6", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(7, "Anak 7", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(8, "Anak 8", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(9, "Anak 9", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(10, "Anak 10", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(11, "Anak 11", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(12, "Anak 12", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(13, "Anak 13", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(14, "Anak 14", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
    Check(15, "Anak 15", "Laki-laki", "2", "1.5", "40", "4", "80", "Ya", 0.4),
)
