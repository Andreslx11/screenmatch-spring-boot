package com.aluracursos.screenmatch.service;

public interface IConvierteDatos {


    /*  es la clase datos genericos <T> y este es el nombre comun T para indicar que estamos
     * trabajando con tipos de datos genericos*/
    /* del json no sabemos cual es el retorno por eso trabajos datos de tipo generico,
     * recordar que en la clase ConsumoApi no rertona ese reponse en json*/
    /*
     <T> T  el tipo de dato en estwe caso es la representación de generico
     obtenerDatos el nombre que le dimos
     String json es campo que indica que recibir un String aqui ese campo lo nombramos json, de seguro ahi va ir
     el json que retorna de Class ConsumoApi
     Class<T> que es una clase generica, esto es para que nos permita trabajr con cualquier tipo de clase
     clase es nopmbre que se le dio a ese campo
     */

    <T> T obtenerDatos(String json, Class<T> clase);


}
