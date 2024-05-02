package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6e942bb9";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        System.out.println("Escribe el nombre de la série que deseas buscar");
        //Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Busca los datos de todas las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalTemporadas(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            DatosTemporada datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporada);
        }
        // temporadas.forEach(System.out::println);

        //Mostrar solo el titulo de los episodios para las temporadas
//        for (int i = 0; i < datos.totalTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporadas.size(); j++) {
//                System.out.println(episodiosTemporadas.get(j).titulo());
//            }
//        }
        // Mejoría usando funciones Lambda
        // imprime todo los episodios
        // temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        // Convertir todas las informaciones a una lista de DatosEpsosdios

        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())// flatmap desetructura las listas en una, mapea
                .collect(Collectors.toList()); // le da la estructura de lista para que sea mutable, en vez  .collect(Collectors.toList())
        // podria .toList pero seria una lista inmutable


        // top 5 episodios
        System.out.println("Top 5 episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed()) /* sorted solo los ordenaria de menos a mayor,
                                                                                    reversed para que traiga los datos de mayor a menor */
                .limit(5)
                .forEach(System.out::println);

        // Convirtiendo los datos  a lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()// flatmap desetructura las listas en una
                        .map(d -> new Episodio(t.numero(), d)))// transformar en un episodio nuevo, que va contener  tambien algunos datos nuevos
                .collect(Collectors.toList());// le da la estructura de lista para que sea mutable

        episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de x años
        System.out.println("Indica el año a partir del cual  deseas ver los episodios ");
        var fecha = teclado.nextInt();
        teclado.nextLine();/* Es por que al usar nextInt() al dar enter se interpreta como \n y esto puede generar un error,
                            como es un  \n no es un numero se deja en bufer entonces al siguiente genera un error al tomarlo
                            como entrada, con nextLine() lo consume limpiando el bufer para siguiente entrada
                           */

        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);/* Aqui seria fecha el año ingresado por el usuario, y los otros
                                                                              datos los ingremos como desde el mes 1 y desde el dia 1*/

        // Como la fecha viene en un formato year-day-month asi 2024-01-30 la vamos a formatear
        //  a un formato mas usado en latino America

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");


        /* trabajamos con lista episodios para hacer la busqueda
         primero que filtre y traiga las fehcas  diferentes de null y a su vex esa fecha sea despues de nuestra
         fecha de busqueda
          se formate con e*/
        episodios.stream()
                .filter(e -> e.getFechaDeLanzamineto() != null && e.getFechaDeLanzamineto().isBefore(fechaBusqueda))
                .forEach(e -> System.out.println(
                        "Temporada " + e.getTemporada() +
                                 " Episodio " + e.getTitulo() +
                                 " Fecha de lanzamiento " + e.getFechaDeLanzamineto().format(dtf)

                ));




    }
}
