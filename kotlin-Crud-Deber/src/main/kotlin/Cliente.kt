import java.io.File
import java.util.*

class Cliente(
    private var nombre:String ?=null,
    private var apellido:String ?=null,
    private var idCliente:Int ?=null,
    private var cedula:String ?=null,
    private var direccion:String ?=null
) {






    fun getNombre():String?{
        return this.nombre
    }

    fun setNombre(nombre:String){
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

    fun setIdCliente(idCliente:Int) {

        this.idCliente = idCliente
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



    override fun toString(): String {
        return idCliente.toString()+","+nombre+","+apellido+","+cedula+","+direccion+"\n"

    }


}