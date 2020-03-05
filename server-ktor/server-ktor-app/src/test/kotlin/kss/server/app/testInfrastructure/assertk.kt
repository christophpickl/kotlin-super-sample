package kss.server.app.testInfrastructure

import assertk.Assert
import assertk.fail
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode

fun Assert<String>.isJsonEquals(expectedJson: String, mode: JsonMode = JsonMode.Strict) {
    given {
        try {
            JSONAssert.assertEquals(expectedJson, it, mode.jsonAssertMode)
        } catch (e: JSONException) {
            fail("${e.message}\nGiven JSON: <<${tryFormat(it)}>>")
        } catch (e: AssertionError) {
            fail("${e.message}\nGiven JSON: <<${tryFormat(it)}>>")
        }
    }
}

enum class JsonMode(
    val jsonAssertMode: JSONCompareMode
) {
    /** Extensible, and non-strict array ordering. */
    IgnoreUnknown(JSONCompareMode.LENIENT),
    /** Not extensible, and non-strict array ordering. */
    Strict(JSONCompareMode.NON_EXTENSIBLE)
}

private fun tryFormat(json: String) =
    try {
        JSONObject(json).toString(2)
    } catch (e: JSONException) {
        try {
            JSONArray(json).toString(2)
        } catch (e: JSONException) {
            json
        }
    }
