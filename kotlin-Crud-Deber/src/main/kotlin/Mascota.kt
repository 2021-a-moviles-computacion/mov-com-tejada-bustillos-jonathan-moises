import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class Mascota (
    private var nombre:String ?=null,
    private var especie:String ?=null,
    private var fechaNac:LocalDate ?=null,
    private var idMascota:Int ?=null,
    private var idCliente:Int ?=null,
    private var sexo:Char ?=null){


    fun getNombre():String?{
        return this.nombre
    }

    fun setNombre(nombre:String){
        this.nombre=nombre
    }

    fun getEspecie():String?{
        return this.especie
    }

    fun setEspecie(especie:String){
        this.especie=especie
    }
    fun getFechaNac():LocalDate?{
        return this.fechaNac
    }

    fun setFechaNac(fechaNac: LocalDate?){
        this.fechaNac=fechaNac
    }
    fun getIdCliente():Int?{
        return this.idCliente
    }

    fun setIdCliente(idCliente: Int?){
        this.idCliente=idCliente
    }
    fun getIdMascota():Int?{
        return this.idMascota
    }

    fun setIdMascota(idMascota: Int?){
        this.idMascota=idMascota
    }
    fun getSexo():Char?{
        return this.sexo
    }

    fun setSexo(sexo:Char){
        this.sexo=sexo
    }


    override fun toString(): String {


        return idCliente.toString()+","+idMascota+","+nombre+","+especie+","+ (fechaNac?.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) +","+sexo.toString()+"\n"
    }

}

