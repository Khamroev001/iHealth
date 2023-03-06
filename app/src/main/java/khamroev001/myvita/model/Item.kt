package khamroev001.myvita.model

data class Item(var name:String, var img:Int, var price: Int, var isFavourite:Boolean=false, var addToCart:Boolean=false)
