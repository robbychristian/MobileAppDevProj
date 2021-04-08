package com.example.mobileappdevproj.Model

class User {
    var user_id: Int = 1
    var user_name: String? = null
    var user_email: String? = null
    var user_address: String? = null
    var user_num: String? = null
    var user_pass: String? = null

    constructor() { }

    constructor(name: String, email: String, address: String, num: String, pass: String)
    {
        this.user_name = name
        this.user_email = email
        this.user_address = address
        this.user_num = num
        this.user_pass = pass
    }
}