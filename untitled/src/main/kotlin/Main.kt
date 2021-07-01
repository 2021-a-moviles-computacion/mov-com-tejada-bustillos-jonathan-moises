import java.io.File

fun main() {
    for (num in 1..3){
        println("Esto es un menu")
        val cliente=nuevoCliente()

    }





}

fun nuevoCliente():Cliente {

    println("Crear Nuevo CLienta")
    var objCliente=Cliente()
    println("Ingrese el nombre")
    objCliente.setNombre(readLine()!!.toString())
    println("Ingrese el apellido")
    objCliente.setApellido(readLine()!!.toString())
    println("Ingrese el Direccion")
    objCliente.setDireccion(readLine()!!.toString())
    println("Ingrese el cedula")
    objCliente.setCedula(readLine()!!.toString())
    objCliente.setIdCliente()
    grabarClienteEnArchivo(objCliente)
    return objCliente
}

fun grabarClienteEnArchivo(cliente:Cliente):Boolean{
    val control=true

    val fileName="src/main/resources/cliente.txt"
    val fileContents= cliente.toString()
    var file = File("${fileName}")
    file.appendText(fileContents)



    return control
}