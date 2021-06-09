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