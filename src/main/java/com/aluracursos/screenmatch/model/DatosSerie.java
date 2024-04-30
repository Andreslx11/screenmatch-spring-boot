package com.aluracursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/*IMPORTANTE POR PADRON ASI SOLO HAYAMOS PASADO UNOS CAMPOS, SE VAN PASAR TODOS Y ESO
 * VA DAR ERROR EN LA EJECUCIÓN DEL PROGRAMA , PARA EVITAR ESO SE USA LA ANOTACIÓN
 * @JsonIgnoreProperties(ignoreUnknown = true) QUE ES QUE IGNORE TODOS LOS DESCONOSIDOS
 * OSEA LOS CAMPOS QUE NO PASAMOS PARA QUE NO DE ERROR
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons")Integer totalTemporadas,
        @JsonAlias("imdbRating")String evaluacion) {
/*

    Anotaciones que trae la libreria Jakson
    Esto es util por que en api el atributo tiene
    nombre , entonces sque si por ejemplo encuentra Titile
    en la api va ser tiutlo en la pelicula

    @JsonAlias //  solo sirve para leer datos de api

    @JsonProperty //  sirve para leer y escribir datos de una api, nos permitiria enviar
    datos en esa modelo


 */
}
