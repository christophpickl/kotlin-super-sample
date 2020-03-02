import kss.sharedlib.common.ClassRto
import org.w3c.xhr.XMLHttpRequest

interface ServerApi {
    fun fetchClasses(callback: (Array<ClassRto>) -> Unit)
}

class ServerApiImpl(
    private val baseUrl: String
) : ServerApi {
    override fun fetchClasses(callback: (Array<ClassRto>) -> Unit) {
        println("ServerApiImpl#fetchClasses()")
        val request = XMLHttpRequest()
        request.open("GET", "$baseUrl/")
        request.onload = {
            if (request.readyState == 4.toShort() && request.status == 200.toShort()) {
                val text = request.responseText
                println("Response: <<$text>>")
                val classes = JSON.parse<Array<ClassRto>>(text)
                println("classes: $classes")
                callback(classes)
            } else {
                println("error!")
            }
        }
        request.send()
    }
}
