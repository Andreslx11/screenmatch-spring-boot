package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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
        // peek -> consulta nos permite ver como java esta ejecutando esas operaciones intermedias
//        System.out.println("Top 5 episodios");
//        datosEpisodios.stream()
//                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("Primer filtro (N/A)" + e))// primer filtro mirando como esta filtrando e-> episodio,   e -> resultado final
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed()) /* sorted solo los ordenaria de menos a mayor,
//                                                                                    reversed para que traiga los datos de mayor a menor */
//                .peek(e -> System.out.println("Segundo ordenación (M>m)" + e)) //segundo filtro mirando como esta ordenando
//                .map(e -> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("Tercer filtro Mayúculas (m->M)" + e))// tercer filtro mirando como esta convertiendo minuculas a mayuculas
//                .limit(5)
//
//                .forEach(System.out::println);

        // Convirtiendo los datos  a lista del tipo Episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()// flatmap desetructura las listas en una
                        .map(d -> new Episodio(t.numero(), d)))// transformar en un episodio nuevo, que va contener  tambien algunos datos nuevos
                .collect(Collectors.toList());// le da la estructura de lista para que sea mutable
        //episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de x años
//        System.out.println("Indica el año a partir del cual  deseas ver los episodios ");
//        var fecha = teclado.nextInt();
//        teclado.nextLine();/* Es por que al usar nextInt() al dar enter se interpreta como \n y esto puede generar un error,
//                            como es un  \n no es un numero se deja en bufer entonces al siguiente genera un error al tomarlo
//                            como entrada, con nextLine() lo consume limpiando el bufer para siguiente entrada
//                           */

//        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);/* Aqui seria fecha el año ingresado por el usuario, y los otros
//                                                                              datos los ingremos como desde el mes 1 y desde el dia 1*/

        // Como la fecha viene en un formato year-day-month asi 2024-01-30 la vamos a formatear
        //  a un formato mas usado en latino America

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");


        /* trabajamos con lista episodios para hacer la busqueda
         primero que filtre y traiga las fehcas  diferentes de null y a su vex esa fecha sea despues de nuestra
         fecha de busqueda
          se formate con e*/
//        episodios.stream()
//                .filter(e -> e.getFechaDeLanzamineto() != null && e.getFechaDeLanzamineto().isBefore(fechaBusqueda))
//                .forEach(e -> System.out.println(
//                        "Temporada " + e.getTemporada() +
//                                 " Episodio " + e.getTitulo() +
//                                 " Fecha de lanzamiento " + e.getFechaDeLanzamineto().format(dtf)
//
//                ));


        // busca Episodios por un pedazo del titulo
//        System.out.println("Por favor escriba el titulo del episodio que desea ver:");
//        var pedazoTtiulo = teclado.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream() // es una opciones trae algunos metodos como en este caso findFirst
//                // es opcional referente a que puede retonar algo como no entonces para
//                // que no de  error
//                .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTtiulo.toUpperCase())) // filtra la busqueda, selecciona las coincidencias
//                .findFirst();// trae la primer  concidencias que se filtro
//
//        if (episodioBuscado.isPresent()) {
//            System.out.println("Episodio encontrado");
//            System.out.println("Los datos son " + episodioBuscado.get());// con get trea todos los datos si hacemos get().titulo() nos
//            // solo el titulo
//        } else {
//            System.out.println("Episodio no encontrado ");
//        }


        // Evaluaciones temporada
        /* primero lista episodios, filter es por que en el la clase  Episodio creamos un try catch en el constructor
           para evaluaciones si evaluacion era null de un episodio devolviera 0.0 para que no diera un error por una
           excepción pero ahora  debemos filtrar esas para que no tengan encuenta para que afecte la evaluacion de la
           temporada de forma negativa, el metodo collect para tratar los datos,  groupingBy va agrupar episodios
           se va agrupar por numero de temprada Episodio::getTemporada, luego con el metodo averingDouble se van a
           promediar a traves de las obtencion de las evaluaciones en conjunto de cada temporada, y eso va retornar
           Integer que ser el numero de la temporada y Double que ser la evalaucion de la temporada respetiva
          */
        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
       // System.out.println(evaluacionesPorTemporada);

        // Esto lo agregue por no quiero tantos decimales
        evaluacionesPorTemporada.forEach((temporada, promedio) -> {
            String formatoPromedio = String.format("%.2f", promedio);
            System.out.println("Temporada " + temporada + ": " + formatoPromedio);
        });

        // DoubleSummaryStatistics  nos va generar de una forma preestablecida algunas estadisticas
        DoubleSummaryStatistics est =episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        // Se imprime toda las estadisticas
        System.out.println(est);
        // Personalición de las cosas que quermos mostrarle al usuario
        System.out.println("Media de las evaluaciones: " + String.format( "%.2f"  ,est.getAverage()));
        System.out.println("Episodio mejor evaluado: " + est.getMax());
        System.out.println("Episodio peor evaluado: " + est.getMin());

    }
}
