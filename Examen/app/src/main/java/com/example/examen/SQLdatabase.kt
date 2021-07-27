package com.example.proyectoibimestre

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.examen.Cliente
import com.example.examen.Mascota

class SQLdatabase(contexto: Context?): SQLiteOpenHelper(
    contexto,
    "ClienteMascota",
    null,
    1
) {

    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearDesarrollador="""
            create table Cliente(
            	idCliente int primary key autoincrement,
            	nombre varchar(50),
            	apellido varchar(50),
            	direccion varchar(50),
            	cedula varchar(10),
            	numeroDeTelefono varchar(10)
            )
        """.trimIndent()
        Log.i("onCreateDataBase","Creando la tabla de Cliente")
        db?.execSQL(scriptCrearDesarrollador)

        val scriptCrearAplicacion="""
            create table Mascota(
            	idMascota int primary key autoincrement,
            	idMascotaCliente int foreign key references Cliente(idCliente),
            	nombre varchar(50),
            	especie varchar(50),
            	raza varchar(50),
            	fechaDeNacimiento date,
            	sexo character(1)
            );
        """.trimIndent()
        Log.i("onCreateDataBase","Creando la tabla de Mascota")
        db?.execSQL(scriptCrearAplicacion)
    }

    // PARA CREAR OBJETOS
    fun crearCliente(
        nombre: String,
        apellido: String,
        direccion: String,
        cedula: String,
        numeroDeTelefono: String
    ):Boolean {
        val conexionEscritura = writableDatabase
        val aGuardar = ContentValues()
        aGuardar.put("nombre",nombre)
        aGuardar.put("apellido",apellido)
        aGuardar.put("direccion",direccion)
        aGuardar.put("cedula",cedula)
        aGuardar.put("numeroDeTelefono",numeroDeTelefono)
        val resultadoEscritura: Long = conexionEscritura.insert(
            "Cliente",
            null,
            aGuardar
        )
        conexionEscritura.close()
        return resultadoEscritura.toInt() != -1
    }

    fun crearMascota(
        idCliente: String,
        nombre: String,
        especie: String,
        raza: String,
        fechaDeNacimiento: Boolean,
        sexo: Char
    ):Boolean {
        val conexionEscritura = writableDatabase
        val aGuardar = ContentValues()
        aGuardar.put("idMascotaCliente",idCliente)
        aGuardar.put("nombre",nombre)
        aGuardar.put("especie",especie)
        aGuardar.put("raza",raza)
        aGuardar.put("fechaDeNacimiento",fechaDeNacimiento)
        aGuardar.put("fsexo",sexo.toString())

        val resultadoEscritura: Long = conexionEscritura.insert(
            "Aplicacion",
            null,
            aGuardar
        )
        conexionEscritura.close()
        return resultadoEscritura.toInt() != -1
    }

    // PARA CONSULTAR LISTA DE OBJETOS

    fun consultarListaClientes(): ArrayList<Cliente>{
        val scriptConsultarUsuario = """
            select * from Cliente
        """.trimIndent()
        val baseDatosLectura = readableDatabase
        val resultadoLectura = baseDatosLectura.rawQuery(scriptConsultarUsuario, null)
        val listaClientes = arrayListOf<Cliente>()
        val clienteEncontrado = Cliente("","",0,"","","")
        if (resultadoLectura.moveToFirst()) {
            do {

                    if (resultadoLectura.getInt(0)!=null) {
                    clienteEncontrado.setIdCliente(resultadoLectura.getInt(0))
                    clienteEncontrado.setNombre(resultadoLectura.getString(1))
                    clienteEncontrado.setApellido(resultadoLectura.getString(2))
                    clienteEncontrado.setDireccion(resultadoLectura.getString(3))
                    clienteEncontrado.setCedula(resultadoLectura.getString(4))
                    clienteEncontrado.setNUmeroDeTelefono(resultadoLectura.getString(5))
                    listaClientes.add(clienteEncontrado)
                }
            } while (resultadoLectura.moveToNext())
        }
        baseDatosLectura.close()
        resultadoLectura.close()
        return listaClientes
    }

    fun consultarListaMascotas(idMascotaCliente: Int): ArrayList<Mascota>{
        val scriptConsultarUsuario = """
            select * from Mascota where idMascotaCliente=${idMascotaCliente}
        """.trimIndent()
        val baseDatosLectura = readableDatabase
        val resultadoLectura = baseDatosLectura.rawQuery(scriptConsultarUsuario, null)
        val listaMascotas = arrayListOf<Mascota>()
        val mascotaEncontrada = Mascota("","","",0,0,' ',"")
        if (resultadoLectura.moveToFirst()) {
            do {

                if (resultadoLectura.getInt(0)!=null) {
                    mascotaEncontrada.setIdMascota(resultadoLectura.getInt(0))
                    mascotaEncontrada.setIdCliente(resultadoLectura.getInt(1))
                    mascotaEncontrada.setNombre(resultadoLectura.getString(2))
                    mascotaEncontrada.setEspecie(resultadoLectura.getString(3))
                    mascotaEncontrada.setRaza(resultadoLectura.getString(4))
                    mascotaEncontrada.setFechaNac(resultadoLectura.getString(5))
                    mascotaEncontrada.setSexo(resultadoLectura.getString(6).toCharArray().get(0))


                    listaMascotas.add(mascotaEncontrada)
                }
            } while (resultadoLectura.moveToNext())
        }
        baseDatosLectura.close()
        resultadoLectura.close()
        return listaMascotas
    }

    // PARA CONSULTAR UN OBJETO UNITARIO

    fun consultarCliente(idCliente: Int): Cliente{
        val scriptConsultarUsuario = """
            select * from Desarrollador where idDesarrollador=${idCliente}
        """.trimIndent()
        val baseDatosLectura = readableDatabase
        val resultadoLectura = baseDatosLectura.rawQuery(scriptConsultarUsuario, null)
        val clienteEncontrado = Cliente("","",0,"","","")
        if (resultadoLectura.moveToFirst()) {
            do {
                if (resultadoLectura.getInt(0)!=null) {
                    clienteEncontrado.setIdCliente(resultadoLectura.getInt(0))
                    clienteEncontrado.setNombre(resultadoLectura.getString(1))
                    clienteEncontrado.setApellido(resultadoLectura.getString(2))
                    clienteEncontrado.setDireccion(resultadoLectura.getString(3))
                    clienteEncontrado.setCedula(resultadoLectura.getString(4))
                    clienteEncontrado.setNUmeroDeTelefono(resultadoLectura.getString(5))

                }
            } while (resultadoLectura.moveToNext())
        }
        baseDatosLectura.close()
        resultadoLectura.close()
        return clienteEncontrado
    }

    fun consultarMascota(idMascota: Int, idMascotaCliente: Int): Mascota{
        val scriptConsultarUsuario = """
            select * from Mascota where idMascota=${idMascota} and Cliente.idCliente=${idMascotaCliente}
        """.trimIndent()
        val baseDatosLectura = readableDatabase
        val resultadoLectura = baseDatosLectura.rawQuery(scriptConsultarUsuario, null)
        val mascotaEncontrada = Mascota("","","",0,0,' ',"")
        if (resultadoLectura.moveToFirst()) {
            do {
                val precio = resultadoLectura.getDouble(7)
                if (resultadoLectura.getInt(0)!=null) {
                    mascotaEncontrada.setIdMascota(resultadoLectura.getInt(0))
                    mascotaEncontrada.setIdCliente(resultadoLectura.getInt(1))
                    mascotaEncontrada.setNombre(resultadoLectura.getString(2))
                    mascotaEncontrada.setEspecie(resultadoLectura.getString(3))
                    mascotaEncontrada.setRaza(resultadoLectura.getString(4))
                    mascotaEncontrada.setFechaNac(resultadoLectura.getString(5))
                    mascotaEncontrada.setSexo(resultadoLectura.getString(6).toCharArray().get(0))

                }
            } while (resultadoLectura.moveToNext())
        }
        baseDatosLectura.close()
        resultadoLectura.close()
        return mascotaEncontrada
    }


    // PARA ACTUALIZAR OBJETOS

    fun actualizarCliente(
        objCliente: Cliente
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",objCliente.getNombre())
        valoresActualizar.put("apellido",objCliente.getApellido())
        valoresActualizar.put("cedula",objCliente.getCedula())
        valoresActualizar.put("direccion",objCliente.getDireccion())
        valoresActualizar.put("numeroDeTelefono",objCliente.getNUmeroDeTelefono())
        val resultadoActualizacion = conexionEscritura.update(
            "Cliente",
            valoresActualizar,
            "idcliente=${objCliente.getIdCliente()}",
            arrayOf(objCliente.getIdCliente().toString())
        )
        conexionEscritura.close()
        return resultadoActualizacion.toInt() != -1
    }

    fun actualizarMascota(
        objMascota: Mascota
    ): Boolean {
        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("nombre",objMascota.getNombre())
        valoresActualizar.put("especie",objMascota.getEspecie())
        valoresActualizar.put("raza",objMascota.getRaza())
        valoresActualizar.put("fechaDeNacimiento",objMascota.getFechaNac())
        valoresActualizar.put("sexo",objMascota.getSexo().toString())
        val resultadoActualizacion = conexionEscritura.update(
            "mascota",
            valoresActualizar,
            "idMascota=${objMascota.getIdMascota()} and Cliente.idCliente=${objMascota.getIdCliente()}",
            arrayOf(objMascota.getIdMascota().toString())
        )
        conexionEscritura.close()
        return resultadoActualizacion.toInt() != -1
    }


    // PARA ELIMINAR OBJETOS

    fun eliminarCliente(idCliente: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura.delete(
            "CLiente",
            "idCliente${idCliente}",
            arrayOf(idCliente.toString())
        )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }

    fun eliminarMascota(idMascota: Int, idMascotaCliente: Int): Boolean {
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura.delete(
            "Mascota",
            "idMascota=${idMascota} and Cliente.idCliente=${idMascotaCliente}",
            arrayOf(idMascota.toString())
        )
        conexionEscritura.close()
        return resultadoEliminacion.toInt() != -1
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}