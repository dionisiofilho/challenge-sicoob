package com.dionisiofilho.sicoob.enums

enum class ImageSize {

    W154 {
        override fun getSize(): String {
            return "w154/"
        }
    },
    W185 {
        override fun getSize(): String {
            return "w185/"
        }
    },
    W342 {
        override fun getSize(): String {
            return "w342/"
        }
    },
    W500 {
        override fun getSize(): String {
            return "w500/"
        }
    },
    W780 {
        override fun getSize(): String {
            return "w780/"
        }
    };

    abstract fun getSize(): String

}