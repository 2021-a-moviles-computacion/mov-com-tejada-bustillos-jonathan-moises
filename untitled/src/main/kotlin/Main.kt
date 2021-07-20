import java.io.File
import java.io.PrintWriter
import java.util.concurrent.ConcurrentLinkedDeque

fun main() {
    var menuPrincipal=0;
    var submenu=0;
    do{
        //consultar ficheros para actualizar el programa
        var listaClientes: ArrayList<Cliente> = arrayListOf()
        listaClientes=consultarListaClientes(false)
       println("*********** -- Menu Principal -- ******************")
        println("1.- Cliente")
        println("2.- Mascota")
        println("0.- Salir")
        menuPrincipal= readLine().toString().toInt()
        when(menuPrincipal){
            1 ->{

                do {
                    println("*********** -- Menu Cliente -- ******************")
                    println("1.- Crear Nuevo")
                    println("2.- Consultar")
                    println("3.- Editar")
                    println("4.- Eliminar")
                    println("0.- Salir al menu Principal")
                    submenu =readLine().toString().toInt()
                    when (submenu){
                        1-> {
                            nuevoCliente(listaClientes.get(listaClientes.lastIndex).getIdCliente().toString().toInt())
                            listaClientes=consultarListaClientes(false)
                        }
                        2-> {
                            consultarListaClientes(true)
                        }
                        3-> {
                            listaClientes=editarCliente(listaClientes)
                            grabarLista(listaClientes)
                        }
                        4-> {
                            listaClientes=eliminarCliente(listaClientes)
                            grabarLista(listaClientes)
                        }
                        0-> {
                            println("Retorno al menu principal")
                        }
                        else -> {
                            println("Opcion No valida ")
                        }

                    }
                }while(submenu!=0)



            }
            2 ->{
                println("*********** -- Menu Mascota -- ******************")
                println("1.- Crear Nuevo")
                println("2.- Consultar")
                println("3.- Editar")
                println("4.- Eliminar")
                println("0.- Salir al menu Principal")
                submenu =readLine().toString().toInt()
            }
            0 ->{
                println("Salir")
            }

            else ->{
                println("Opción no valida")
            }
        }

    }while (menuPrincipal!=0)





}

fun grabarLista(listaClientes: ArrayList<Cliente>) {
    val filePrintWriter = PrintWriter(File("src/main/resources/cliente.txt"))
    filePrintWriter.close()

    val fileName="src/main/resources/cliente.txt"


    var file = File("${fileName}")
    listaClientes.forEach {
        file.appendText(it.toString())
    }


}

fun eliminarCliente(lista:ArrayList<Cliente>):ArrayList<Cliente> {
    do{
        println("*********** -- Eliminar Cliente -- ******************")
        println("Ingrese la cedula del Cliente a eliminar")
        var control:Boolean = true
        // var control2:Boolean = true
        var aux:String = readLine().toString()
        lista.forEach{

            if(it.getCedula()== aux && control){
                control=false
                // control2=false

                lista.remove(it)
            }

        }
        if (control){
            println("No se encuentra esa cedula")
        }

    }while(control)




    return lista
}

fun editarCliente(lista:ArrayList<Cliente>):ArrayList<Cliente> {
    do{
        println("*********** -- Editar Cliente -- ******************")
        println("Ingrese la cedula del Cliente a modificar")
        var control:Boolean = true
       // var control2:Boolean = true
        var aux:String = readLine().toString()
         lista.forEach{

            if(it.getCedula()== aux && control){
                control=false
               // control2=false
                var cambios:Int=0
                do{
                    println("*********** -- Editar Cliente -- ******************")
                    println("Seleccione los parametros a modificar")
                    println("1- Nombre")
                    println("2- Apellido")
                    println("3- Direccion")
                    println("4- Cedula")
                    println("0- salir")
                    cambios= readLine().toString().toInt()
                    when(cambios){
                        1-> {
                            it.setNombre(readLine().toString())
                        }
                        2-> {
                            it.setApellido(readLine().toString())
                        }
                        3-> {
                            it.setDireccion(readLine().toString())
                        }
                        4-> {
                            it.setCedula(readLine().toString())
                        }
                        0-> {
                            println("cambios realizados con exito")
                            break
                        }
                        else -> {
                            println("opcion no valida")
                        }
                    }
                }while(cambios!=0)
            }

        }
        if (control){
            println("No se encuentra esa cedula")
        }

    }while(control)




    return lista
}

fun consultarListaClientes (printNeed:Boolean):ArrayList<Cliente> {
    val fileName = "src/main/resources/cliente.txt"
    var file = File("${fileName}")

    var listaClientes: ArrayList<Cliente> = arrayListOf()

    var fileContent = file.readText()
    var SplitedContent = fileContent.split("\n")



    SplitedContent.forEach { linea: String ->
        val datosCliente = linea.split(",")
        if (datosCliente.size == 5) {
            //println(datosCliente[0])
            //println(datosCliente[1])
            val clienteAux=Cliente()
            clienteAux.setIdCliente(datosCliente[0].toInt())
            clienteAux.setNombre(datosCliente[1])
            clienteAux.setApellido(datosCliente[2])
            clienteAux.setDireccion(datosCliente[4])
            clienteAux.setCedula(datosCliente[3])
            //clienteAux.toString()
            listaClientes.add(clienteAux)
        }


    }
    if (printNeed) {
        listaClientes.forEach {
            println(it.toString())
        }
    }
    return listaClientes
}



fun nuevoCliente(indexP:Int):Cliente {

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
    objCliente.setIdCliente(indexP+1)
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