import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger


private val logger: Logger = LogManager.getLogger()
fun double(x: Int = 4) :Int {
    return 2 * x
}
fun foo(bar: Int = 0, baz: Int) : Int {
    return baz - bar
}
fun main(args: Array<String>) {
    logger.info("The application is starting...");

    var name = "Guillaume"
    name = "Someone else"
    val names: MutableList<String> = mutableListOf()
    names.add("reda")
    names.add("zakaria")
    println("Hello EMSE I am $name")
    val result = double(2)
    println("le double de 2 est : $result")
}





