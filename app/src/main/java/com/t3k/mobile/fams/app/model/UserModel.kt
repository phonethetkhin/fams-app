package com.t3k.mobile.fams.app.model

class UserModel {
    var id = 0
    var userId = 0
    var loginId: String? = null
    var name: String? = null
    var password: String? = null
    var joinedDate: String? = null
    var lastLoginDate: String? = null

    constructor() {

    }
    constructor(
        id: Int,
        userId: Int,
        loginId: String?,
        name: String?,
        password: String?,
        joinedDate: String?,
        lastLoginDate: String?
    ) {
        this.id = id
        this.userId = userId
        this.loginId = loginId
        this.name = name
        this.password = password
        this.joinedDate = joinedDate
        this.lastLoginDate = lastLoginDate
    }

}