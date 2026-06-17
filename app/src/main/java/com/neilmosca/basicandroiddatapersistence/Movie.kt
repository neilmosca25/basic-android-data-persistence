package com.neilmosca.basicandroiddatapersistence

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Movie() : RealmObject {
    @PrimaryKey
    var id: Long = 0
    var title: String = ""
    var genre: String = ""
    var year: Int = 0

    override fun toString(): String {
        return "id: $id, title: $title, genre: $genre, year: $year"
    }
}