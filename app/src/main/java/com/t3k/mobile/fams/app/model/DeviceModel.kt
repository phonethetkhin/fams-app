package com.t3k.mobile.fams.app.model

class DeviceModel {
    var id = 0
    var deviceCode: String? = null
    var deviceName: String? = null
    var brand: String? = null
    var model: String? = null
    var osVersion: String? = null
    var device: String? = null
    var macWifi: String? = null
    var deviceId1: String? = null
    var deviceId2: String? = null
    var serialNo: String? = null
    var androidId: String? = null
    var fingerPrint: String? = null
    var imei: String? = null

    constructor()
    {


    }
    constructor(
        id: Int,
        deviceCode: String?,
        deviceName: String?,
        brand: String?,
        model: String?,
        osVersion: String?,
        device: String?,
        macWifi: String?,
        deviceId1: String?,
        deviceId2: String?,
        serialNo: String?,
        androidId: String?,
        fingerPrint: String?,
        imei: String?
    ) {
        this.id = id
        this.deviceCode = deviceCode
        this.deviceName = deviceName
        this.brand = brand
        this.model = model
        this.osVersion = osVersion
        this.device = device
        this.macWifi = macWifi
        this.deviceId1 = deviceId1
        this.deviceId2 = deviceId2
        this.serialNo = serialNo
        this.androidId = androidId
        this.fingerPrint = fingerPrint
        this.imei = imei
    }


}
