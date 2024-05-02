package com.aluracursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamineto;

    public Episodio(Integer numero, DatosEpisodio d) {
        //titulo(), numeroEpisodio(), evaluacion() son metodos getter del record  DatosEpisodio
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();

        // Este try catch es por que hay evaluaciones que estan "N/A" entonces dan error
        // por que Double.valueOf(d.evaluacion())  no puede hacer el casteo, para eso este try catch
        // si no puede castear en retorna la evaluación como 0.0
        try {
            // Casteo en el record evaluaciones es String y aquí se necesita castearlos a Doble
            this.evaluacion = Double.valueOf(d.evaluacion());
        }catch (NumberFormatException e){
            this.evaluacion = 0.0;
        }

        try {
            // conversion de la fecha que viene en formato string a un objeto LocalDate
            // esto permite trabajarlas
            // en futuro esto puede generar un ERROR
            this.fechaDeLanzamineto = LocalDate.parse(d.fechaDeLanzamiento());
        } catch (DateTimeParseException e) {
            this.fechaDeLanzamineto = null;

        }


    }


    public Integer getTemporada() {
        return temporada;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public LocalDate getFechaDeLanzamineto() {
        return fechaDeLanzamineto;
    }

    public void setFechaDeLanzamineto(LocalDate fechaDeLanzamineto) {
        this.fechaDeLanzamineto = fechaDeLanzamineto;
    }

    @Override
    public String toString() {
        return
                "temporada=" + temporada +
                        ", titulo='" + titulo + '\'' +
                        ", numeroEpisodio=" + numeroEpisodio +
                        ", evaluacion=" + evaluacion +
                        ", fechaDeLanzamineto=" + fechaDeLanzamineto;
    }
}
