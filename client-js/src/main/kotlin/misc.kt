import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document

object HtmlApi {
    val div = document.getElementById("someDiv") as HTMLDivElement
    val button = document.getElementById("btnLoad") as HTMLButtonElement
    // TODO OnlyJsVisible not visible?!
}

object Context {
    val server: ServerApi = ServerApiImpl("http://localhost:9090")
    val controller = Controller(server)
}
