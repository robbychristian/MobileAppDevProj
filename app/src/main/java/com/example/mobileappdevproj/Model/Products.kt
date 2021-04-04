package com.example.mobileappdevproj.Model

class Products {
    var prod_id: Int = 1
    var prod_name: String? = null
    var prod_desc: String? = null
    var prod_size: String? = null
    var prod_price: String? = null
    var prod_img: String? = null

    constructor() { }

    constructor(prod_name: String, prod_desc: String, prod_size: String, prod_price: String, prod_img: String)
    {
        this.prod_name = prod_name
        this.prod_desc = prod_desc
        this.prod_size = prod_size
        this.prod_price = prod_price
        this.prod_img = prod_img
    }
}