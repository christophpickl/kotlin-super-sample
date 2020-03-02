package kss.sharedlib.js

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kss.sharedlib.common.ServerApi
import kss.sharedlib.common.UserRto
import kss.sharedlib.common.parseUserTos

class JsServerApi : ServerApi {

    private val json = Json(configuration = JsonConfiguration.Default)

    override fun fetchUsers(): List<UserRto> {
//        return JSON.parse("", UserRto.listSerializer)
        return json.parseUserTos("[]")
    }
}
