import java.io.File
import java.util.*

class Cliente {
    private var nombre:String?=null
    private var apellido:String?=null
    private var idCliente:Int=0
    private var cedula:String?=null
    private var direccion:String?=null
    init {
        agregarCliente()
    }




    fun getNombre():String?{
        return this.nombre
    }

    fun setNombre(nombre:String?){
        this.nombre=nombre
    }

    fun getApellido():String?{
        return this.apellido
    }

    fun setApellido(apellido:String){
        this.apellido=apellido
    }



    fun getIdCliente():Int?{

        return this.idCliente
    }

    fun setIdCliente(){

        this.idCliente= ultimoIdCliente
    }

    fun getDireccion():String?{
        return this.direccion
    }

    fun setDireccion(direccion:String){

        this.direccion=direccion
    }

    fun getCedula():String?{
        return this.cedula
    }

    fun setCedula(cedula:String){
        this.cedula=cedula
    }

    fun grabarClienteEnArchivo(cliente:Cliente):Boolean{
        val control=true

        val fileName="src/main/resources/cliente.txt"
        val fileContents= cliente.toString()
        var file = File("${fileName}")
        file.appendText(fileContents)



        return control
    }

    override fun toString(): String {
        return idCliente.toString()+","+nombre+","+apellido+","+cedula+","+direccion+"\n"

    }

    companion object{
        var ultimoIdCliente=0
        fun agregarCliente(){
            ultimoIdCliente= ultimoIdCliente+1
            println(ultimoIdCliente)
        }
    }
}