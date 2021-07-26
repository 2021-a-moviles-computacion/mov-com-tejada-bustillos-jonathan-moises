import java.io.File
import java.io.PrintWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main() {
    var menuPrincipal=0;
    var submenu=0;
    do{
        //consultar ficheros para actualizar el programa
            //clientes
        var listaClientes: ArrayList<Cliente> = arrayListOf()
        listaClientes=consultarListaClientes(false)
            //mascotas
        var listaMascota: ArrayList<Mascota> = arrayListOf()

        listaMascota=consultarListaMascotas(false)



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
                            grabarListaClientes(listaClientes)
                        }
                        4-> {
                            listaClientes=eliminarCliente(listaClientes)
                            grabarListaClientes(listaClientes)
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
                do {
                    println("*********** -- Menu Mascota -- ******************")
                    println("1.- Crear Nuevo")
                    println("2.- Consultar")
                    println("3.- Editar")
                    println("4.- Eliminar")
                    println("0.- Salir al menu Principal")
                    submenu =readLine().toString().toInt()
                    when (submenu){
                        1-> {
                            nuevaMascota(listaMascota.get(listaMascota.lastIndex).getIdMascota().toString().toInt())
                            listaMascota=consultarListaMascotas(false)
                        }
                        2-> {
                            consultarListaMascotas(true)
                        }
                        3-> {
                            listaMascota=editarMascota(listaMascota)
                            grabarListaMascotas(listaMascota)
                        }
                        4-> {
                            listaMascota=eliminarMascota(listaMascota)
                            grabarListaMascotas(listaMascota)
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
            0 ->{
                println("Salir")
            }

            else ->{
                println("Opción no valida")
            }
        }

    }while (menuPrincipal!=0)





}

fun consultarListaMascotas (printNeed:Boolean):ArrayList<Mascota> {


    val fileName = "src/main/resources/mascota.txt"
    var file = File("${fileName}")

    var listaMascotas: ArrayList<Mascota> = arrayListOf()

    var fileContent = file.readText()

    var SplitedContent = fileContent.split("\n")



    SplitedContent.forEach { linea: String ->
        val datosMascota = linea.split(",")
        if (datosMascota.size == 6) {


            val mascotaAux=Mascota()
            mascotaAux.setIdCliente(datosMascota[0].toInt())
            mascotaAux.setIdMascota(datosMascota[1].toInt())
            mascotaAux.setNombre(datosMascota[2])
            mascotaAux.setEspecie(datosMascota[3])
            var formato= DateTimeFormatter.ofPattern("dd-MM-yyyy")
            mascotaAux.setFechaNac(LocalDate.parse(datosMascota[4],formato))
            mascotaAux.setSexo(datosMascota[5].toCharArray()[0])

            listaMascotas.add(mascotaAux)
        }


    }
    if (printNeed) {
        listaMascotas.forEach {
            println(it.toString())
        }
    }
    return listaMascotas
}


fun nuevaMascota(indexP: Int):Mascota {
    println("Crear una Nueva Mascota")
    var objMascota=Mascota()
    println("Ingrese el nombre")
    objMascota.setNombre(readLine()!!.toString())
    println("Ingrese la especie")
    objMascota.setEspecie(readLine()!!.toString())
    println("Ingrese la fecha nacimiento ")
    var formato= DateTimeFormatter.ofPattern("dd-MM-yyyy")
    objMascota.setFechaNac(LocalDate.parse(readLine()!!.toString(),formato))
    println("Ingrese el sexo")
    objMascota.setSexo(readLine()!!.toCharArray()[0])
    println("Ingrese el id del Cliente")
    objMascota.setIdCliente(readLine()!!.toInt())
    objMascota.setIdMascota(indexP+1)
    grabarMascotaEnArchivo(objMascota)
    return objMascota

}

fun grabarMascotaEnArchivo(mascota:Mascota):Boolean{
    val control=true

    val fileName="src/main/resources/mascota.txt"
    val fileContents= mascota.toString()
    var file = File("${fileName}")
    file.appendText(fileContents)



    return control
}



fun grabarListaClientes(listaClientes: ArrayList<Cliente>) {
    val filePrintWriter = PrintWriter(File("src/main/resources/cliente.txt"))
    filePrintWriter.close()

    val fileName="src/main/resources/cliente.txt"


    var file = File("${fileName}")
    listaClientes.forEach {
        file.appendText(it.toString())
    }


}

fun grabarListaMascotas(listaMascotas: ArrayList<Mascota>) {
    val filePrintWriter = PrintWriter(File("src/main/resources/mascota.txt"))
    filePrintWriter.close()

    val fileName="src/main/resources/mascota.txt"


    var file = File("${fileName}")
    listaMascotas.forEach {
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
        var objCliente= Cliente()
        lista.forEach{

            if(it.getCedula()== aux && control){
                control=false
                // control2=false

                objCliente=it

            }

        }

        if (control){
            println("No se encuentra esa cedula")
        }
        else {
            lista.remove(objCliente)
            println("Eliminado el registro del Cliente ")
        }

    }while(control)




    return lista
}

fun eliminarMascota(lista:ArrayList<Mascota>):ArrayList<Mascota> {
    do{
        println("*********** -- Eliminar Mascota -- ******************")
        println("Ingrese el ID de la Mascota a eliminar")
        var control:Boolean = true
        // var control2:Boolean = true
        var objMascota=Mascota()
        var aux:String = readLine().toString()
        lista.forEach{

            if(it.getIdMascota()== aux.toInt() && control){
                control=false
                // control2=false
                objMascota=it

            }

        }
        if (control){
            println("No se encuentra ese ID")
        }
        else{
            lista.remove(objMascota)
            println("Eliminado el registro de la Mascota ")
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

fun editarMascota(lista:ArrayList<Mascota>):ArrayList<Mascota> {
    do{
        println("*********** -- Editar Mascota -- ******************")
        println("Ingrese el Id  de la Mascota a modificar")
        var control:Boolean = true
        // var control2:Boolean = true
        var aux:String = readLine().toString()
        lista.forEach{

            if(it.getIdMascota()== aux.toInt() && control){
                control=false
                // control2=false
                var cambios:Int=0
                do{
                    println("*********** -- Editar Mascota -- ******************")
                    println("Seleccione los parametros a modificar")
                    println("1- Nombre")
                    println("2- Especie")
                    println("3- Sexo")
                    println("4- FechaNac")
                    println("0- salir")
                    cambios= readLine().toString().toInt()
                    when(cambios){
                        1-> {
                            it.setNombre(readLine().toString())
                        }
                        2-> {
                            it.setEspecie(readLine().toString())
                        }
                        3-> {
                            it.setSexo(readLine().toString().toCharArray()[0])
                        }
                        4-> {

                            var formato= DateTimeFormatter.ofPattern("dd-MM-yyyy")
                            it.setFechaNac(LocalDate.parse(readLine()!!.toString(),formato))
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
            println("No se encuentra ese ID")
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