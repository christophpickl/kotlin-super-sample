import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document
import kotlin.dom.clear
import kotlin.dom.createElement

fun main() {
    HtmlApi.button.onclick = {
        Context.controller.load()
    }

    println("Hello from Kotlin/JS")
    val foo = js("nativeJavascriptFunction()") as String
    println("and: $foo")
}

object HtmlApi {
    val div = document.getElementById("someDiv") as HTMLDivElement
    val button = document.getElementById("btnLoad") as HTMLButtonElement
}

class Controller(
    private val server: ServerApi
) {
    fun load() {
        println("Controller#load()")
        HtmlApi.div.innerHTML = "loading ..."
        server.fetchClasses { classes ->
            val ul = document.createElement("ul")
            classes.forEach {
                ul.append(document.createElement("li") {
                    textContent = "${it.id} - ${it.title}"
                })
            }
            HtmlApi.div.clear()
            HtmlApi.div.append(ul)
        }
    }
}

object Context {
    val server: ServerApi = ServerApiImpl("http://localhost:9090")
    val controller = Controller(server)
}

data class ClassDto(
    val id: String,
    val title: String
)

interface ServerApi {
    fun fetchClasses(callback: (Array<ClassDto>) -> Unit)
}

class ServerApiImpl(
    private val baseUrl: String
) : ServerApi {
    override fun fetchClasses(callback: (Array<ClassDto>) -> Unit) {
        println("ServerApiImpl#fetchClasses()")
        val request = XMLHttpRequest()
        request.open("GET", "$baseUrl/")
        request.onload = {
            if (request.readyState == 4.toShort() && request.status == 200.toShort()) {
                val text = request.responseText
                println("Response: <<$text>>")
                val classes = JSON.parse<Array<ClassDto>>(text)
                println("classes: $classes")
                callback(classes)
            } else {
                println("error!")
            }
        }
        request.send()
    }
}
