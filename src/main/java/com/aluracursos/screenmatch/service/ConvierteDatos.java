package com.aluracursos.screenmatch.service;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos   implements IConvierteDatos {


    /*Claro, el ObjectMapper es una clase proporcionada por la biblioteca
     Jackson en Java, que se utiliza para mapear objetos Java a JSON y viceversa.
      Básicamente, nos permite convertir datos entre objetos Java y JSON de una
      manera sencilla y eficiente. Es muy útil cuando trabajamos con APIs que nos
       devuelven datos en formato JSON y necesitamos convertirlos a objetos Java
       para manipularlos en nuestra aplicación.*/
    private ObjectMapper objectMapper = new ObjectMapper();




    //    @Override  recordar que el dato esta sobrescrito
    // .readValue() es el metodo que hace la converción
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



    /* una forma de transformas los datos, sew comento para usar una forma mas generica
    que nos permita convertir mas clase de datos ejmplo DatosActores para eso se hace la
    interface

    public DatosSerie objectMapper(String json) {
    }
    //////////*/

}
