import kotlin.browser.document
import kotlin.dom.clear
import kotlin.dom.createElement

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
