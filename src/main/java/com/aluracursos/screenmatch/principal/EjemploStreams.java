package com.aluracursos.screenmatch.principal;

/*Esta clase   es solo para ejemplo para ver como se usa Streams
* */

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {

    public void muestraEjemplo(){
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Maria  Fernanda", "Eric", "Genesys");

        nombres.stream()
                .sorted() //Sorted los va ordenar por orden alfabetico
                .limit(4) // nos limitaria a los cuatro primeros elementos en este caso
                .filter(n -> n.startsWith("L"))// filtrar y uso también una función lambda
                .map(n -> n.toUpperCase())
                .forEach(System.out::println); // los va imprimir

    }
}




	/*Claro, aquí tienes una lista de algunas operaciones que se pueden realizar con streams en Java:

filter: Permite filtrar elementos según un predicado.

map: Transforma cada elemento aplicando una función.

forEach: Ejecuta una acción para cada elemento.

sorted: Ordena los elementos.

limit: Limita la cantidad de elementos.

collect: Recolecta los elementos en una colección.

reduce: Combina los elementos en un solo resultado.

anyMatch: Verifica si algún elemento cumple con cierta condición.

allMatch: Verifica si todos los elementos cumplen con cierta condición.

noneMatch: Verifica si ningún elemento cumple con cierta condición.

Estas son solo algunas de las operaciones que se pueden realizar con streams en Java.
 Cada una de ellas proporciona una forma poderosa y flexible de
 manipular colecciones de datos.

 //////////////////////////////////////////////////////////////////////////////////////////
 Claro, aquí tienes la lista de operaciones de streams en Java con un ejemplo para cada una:
filter: Permite filtrar elementos según un predicado.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
nombres.stream()
    .filter(nombre -> nombre.startsWith("A"))
    .forEach(System.out::println);



map: Transforma cada elemento aplicando una función.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
nombres.stream()
    .map(nombre -> nombre.toUpperCase())
    .forEach(System.out::println);



forEach: Ejecuta una acción para cada elemento.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
nombres.stream()
    .forEach(System.out::println);



sorted: Ordena los elementos.Ejemplo:

List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
nombres.stream()
    .sorted()
    .forEach(System.out::println);



limit: Limita la cantidad de elementos.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
nombres.stream()
    .limit(2)
    .forEach(System.out::println);



collect: Recolecta los elementos en una colección.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
List<String> nombresMayusculas = nombres.stream()
    .map(nombre -> nombre.toUpperCase())
    .collect(Collectors.toList());



reduce: Combina los elementos en un solo resultado.Ejemplo:


List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
int suma = numeros.stream()
    .reduce(0, (a, b) -> a + b);



anyMatch: Verifica si algún elemento cumple con cierta condición.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
boolean hayNombresLargos = nombres.stream()
    .anyMatch(nombre -> nombre.length() > 4);



allMatch: Verifica si todos los elementos cumplen con cierta condición.Ejemplo:


 List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
boolean todosEmpiezanConA = nombres.stream()
    .allMatch(nombre -> nombre.startsWith("A"));



noneMatch: Verifica si ningún elemento cumple con cierta condición.Ejemplo:


List<String> nombres = Arrays.asList("Ana", "Juan", "Pedro", "María");
boolean ningunNombreVacio = nombres.stream()
    .noneMatch(String::isEmpty);




Estos ejemplos ilustran cómo se utilizan algunas de las operaciones de
streams en Java para manipular colecciones de datos de manera concisa y poderosa.


 */