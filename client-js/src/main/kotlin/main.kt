fun main() {
    HtmlApi.button.onclick = {
        Context.controller.load()
    }

    println("Hello from Kotlin/JS")
    val foo = js("nativeJavascriptFunction()") as String
    println("and: $foo")
}
