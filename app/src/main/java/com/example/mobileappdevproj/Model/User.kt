package com.example.mobileappdevproj.Model

class User {
    var id: Int = 0
    var name: String? = null
    var email: String? = null
    var address: String? = null
    var num: String? = null
    var pass: String? = null

    constructor() { }

    constructor(id:Int, name: String, email: String, address: String, num: String, pass: String)
    {
        this.id = id
        this.name = name
        this.email = email
        this.address = address
        this.num = num
        this.pass = pass
    }
}