import java.util.*

fun main() {
    println("hola mundo")
    //java
    //tipo nombre= valor
    //koklim
    var edadProfesor = 32
    //var edadProfesor : Int = 32
    var sueldoProfesor = 1.23
    edadProfesor = 25

    //Vatiables mutables e inmutables

    //mutables (se pueden reasignar)

    var edadCachorro = 0
    edadCachorro = 1
    edadCachorro = 2

    //inmutables (no se pueden reasignar)

    val numeroCedula = 321564864
    //numeroCedula = 12

    //Tipos de variales

    val nombreProfesor: String = "Adrian"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 's'
    val fechaNacimiento: Date = Date();

    val estadoCivilWhen = "S"

    when (estadoCivilWhen) {
        "S" -> {
            println("Acercarse")
        }
        "C" -> {
            println("Alejarse")
        }
        else -> {
            println("No reconocido")
        }
    }
    imprimirNombre("Juan")
    calcularSueldo(100.00)
    calcularSueldo(100.00, 14.00)
    calcularSueldo(100.00, 14.00,25.00)

    //named Parameters
    calcularSueldo(
        bonoEspecial = 15.00,
        sueldo = 25.00,
        tasa = 12.00
    )

    //tipos de arreglos
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    val arregloDinamico:ArrayList<Int> = arrayListOf(1,2,3,4,5)

    println(arregloDinamico)
    arregloDinamico.add(6)
    arregloDinamico.add(7)
    println(arregloDinamico)

    // Operadores -> sirven para los arreglos estaticos y dinamicos

    //FOR EACH  -> Unit
    //Iterar un arreglo
    var respuestaForEach: Unit = arregloDinamico.
    forEach { valorActual:Int ->
        println("valor actual: ${valorActual}")
    }
    println(respuestaForEach)

   arregloDinamico.forEach {
        println("valor actual: ${it}")
    }
    arregloDinamico.forEachIndexed() { Indice:Int, valorActual:Int ->
        println("valor actual: ${valorActual} Indice: ${Indice}")
    }

    //OPERADOR MAP -> MUTA EL ARREGLO
    // 1) Enviemos el nuevo valor de la iteración
    // 2) Nos devuelve es un    nuebvo arreglo  con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual : Int ->
        return@map valorActual.toDouble()
    }
    arregloDinamico.map { it + 15 } //formato reducido
    println(respuestaMap)
    println(arregloDinamico)


    //FILTER ->Filtrar los arreglos
    // retorna una expresion (TRUE O FALSE)
    val respuestaFilter: List<Int> = arregloDinamico.filter {
        val mayorACuatro: Boolean = it > 5
        return@filter  mayorACuatro
    }
    arregloDinamico.filter { it>5 } //formato reducido

    println(respuestaFilter)

    //
    val respuestasAny: Boolean = arregloDinamico.any {valorActual:Int ->
        return@any (valorActual > 5)
    }
    println(respuestasAny)
    val respuestasAll: Boolean = arregloDinamico.all {valorActual:Int ->
        return@all (valorActual > 5)
    }
    println(respuestasAll)

    //REDUCE -> VALOR ACUMULADO
    /*
        Devuelve el acumulado=> 0
        en que valor empieza =>
        [1,2,3,4,5]
        Valor Iternacion 1=0 +1
     */
    val respuestaReduce:Int = arregloDinamico.reduce {
            acumulado:Int, valorActual:Int ->
        return@reduce (acumulado+valorActual) }
    println(respuestaReduce)

    val arregloDanico = arrayListOf<Int>(12,15,8,10)

    val respuestaReduceFold = arregloDanico.fold(100,{ acumulado, valoractualIteracion ->
        return@fold acumulado - valoractualIteracion
    })
    println(respuestaReduceFold)

    /*val vidaactual: Double = arregloDinamico
        .map { it * 2.3 }
        .filter { it>20 }
        .fold(100,{acc,i -> acc-i})
        .also { println(it) }
    println("valor vida actual ${vidaactual}")
*/
    val ejemploUno = Suma(1,2)
    val ejemploDos = Suma(null,2)
    val ejemplotres = Suma(1,null)
    println(ejemploUno.sumar())
    println(ejemploDos.sumar())
    println(ejemplotres.sumar())


}

fun imprimirNombre(nombre: String): Unit {   //unit tipo de retorno similar a void
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.00, //Opcional (valor por defecto)
    bonoEspecial: Double? = null//Opcional (Null) ->nullable
): Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }

}


abstract class NumerosJaava{
    protected val numeroUno:Int
    private val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){
        numeroUno=uno
        numeroDos=dos
        println("Inicializar")
    }


}

abstract class Numeros(//constructor primario
    protected val numeroUno:Int, //propiedad
    protected val numeroDos: Int
){
    init {//bloque de inicio del constructor primario
        println("Inicializar")
    }
}

class Suma(
    uno: Int,   //parametros requeridos
    dos: Int
): Numeros(uno,dos){    //constructor papa super --usando :
    init {
        this.numeroUno
        this.numeroDos
    }
    constructor(
        uno: Int?,
        dos:Int
    ): this( if (uno == null) 0 else uno,dos)
    constructor(
        uno: Int,
        dos:Int?
    ): this( uno, if (dos == null) 0 else dos)

    public fun sumar():Int{
        val total:Int = numeroUno +numeroDos
        agregarHistorial(total)
        return total
    }
    //SINGLETON
    companion object{
        val historialSumas = arrayListOf<Int>()

        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }
}