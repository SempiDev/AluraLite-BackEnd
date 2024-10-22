package com.example.LiterAluraFinal.Principal;

import com.example.LiterAluraFinal.Repositorio.RepositorioAutor;
import com.example.LiterAluraFinal.Repositorio.RepositorioLibro;
import com.example.LiterAluraFinal.models.Autor;
import com.example.LiterAluraFinal.models.DatosLibro;
import com.example.LiterAluraFinal.models.Libro;
import com.example.LiterAluraFinal.service.ConversorDatos;
import com.example.LiterAluraFinal.service.PedirApi;

import java.util.List;
import java.util.Scanner;


public class Principal {
private final PedirApi pedirApi = new PedirApi ();
private final Scanner lectura = new Scanner(System.in);
private final String urlBase = "https://gutendex.com/books/";
private final ConversorDatos conversorDatos = new ConversorDatos();
private RepositorioLibro repositorioLibro;
private RepositorioAutor repositorioAutor;
private List<Autor> autorList;
private List<Libro> libroList;

public Principal(){
    this.repositorioLibro = repositorioLibro;
    this.repositorioAutor = repositorioAutor;
}

public void mostrarMenu(){

    var opcion= -1;
    while (opcion != 9){
        var menu = """
                -------------------------------------------------------
                LiterAlura (Version final) Busqueda de libros y Autores
                -------------------------------------------------------
                
                Selecciona una opcion:
                
                1.Buscar libro
                2.Consultar historial de libros buscaods
                3.Consultar Autores disponibles
                4.Consultar Autores por año (Año a eleccion)
                5.Consultar libros por idioma 
                
                
                9.Salir
                """;
        try {
            System.out.println(menu);
            opcion = lectura.nextInt();
            lectura.nextLine();
        }catch (Exception e){

            System.out.println("Ingresa una opcion valida");
        }

        switch (opcion){
            case 1:
                buscarLibro();
                break;
            case 2:
                librosBuscados();
                break;
            case 3:
                autoresDisponibles();
                break;
            case 4:
                buscarAutoresPorAno();
                break;
            case 5:
                consultarLibrosLenguaje();
                break;
            case 0:
                System.out.println("Hasta luego");
                break;
            default:
                System.out.println("Ingresa una opcion valida");
        }


    }

}
private DatosLibro getinformacionlibros(){
    System.out.println("Ingrese nombre del libro que desea buscar");
    var busqueda = lectura.nextLine().toLowerCase().replace("","%20");
    var json = pedirApi.getData(urlBase + "?search=" + busqueda);

    DatosLibro datosLibro = conversorDatos.traerDatos(json, DatosLibro.class);
    return datosLibro;
}
private void buscarLibro() {

    DatosLibro datosLibro = getinformacionlibros();

    try {
        Libro libro = new Libro(datosLibro.resultados().get(0));
        Autor autor = new Autor(datosLibro.resultados().get(0).listaAutor());
        System.out.printf("""
                        Libro :
                                 titulo: %s
                                 autor: %s
                                 idioma: %s
                                 descargas: %s
                        
                        %n""", libro.getTitulo(),
        libro.getAutor(),
        libro.getIdioma(),
        libro.getNumeroDescarga().toString());

        this.repositorioLibro.save(libro);
        this.repositorioAutor.save(autor);

    } catch (Exception e) {
        System.out.println("No se encontro el libro buscado");
    }
}
    private void librosBuscados(){
        for (Libro l : libroList) {
            System.out.printf("""
                            Titulo: %s
                            Autor: %s
                            Idioma: %s
                            Descargas: %s
                            %n""", l.getTitulo(),
            l.getAutor(),
            l.getIdioma(),
            l.getNumeroDescarga().toString());
        }
    }
    private void autoresDisponibles() {
        autorList = repositorioAutor.findAll();
        for (Autor a : autorList) {
            System.out.printf("""
                            Autor: %s
                            Año de nacimiento: %s
                            %n""", a.getAutor(),
                    a.getNacimiento().toString());

        }
    }
        public void buscarAutoresPorAno() {
            System.out.println("Ingrese el año que desea buscar");
            var anoBusqueda = lectura.nextInt();
            lectura.nextLine();

            List<Autor> autors = repositorioAutor.autorPorFecha(anoBusqueda);
            autors.forEach(a -> {
                System.out.printf("""
                        Nombre: %s
                        Fecha de nacimiento: %s
                        %n""", a.getAutor(), a.getNacimiento().toString());

            });
        }
        private void consultarLibrosLenguaje() {
            System.out.println("""
                    ----------------------------------
                    Seleccione el idioma de los libros
                    ----------------------------------
                    
                    1.En ingles
                    2.En español
                    """);
            try {
                var opcion2 = lectura.nextInt();
                lectura.nextLine();

                switch (opcion2) {
                    case 1:
                        List<Libro> Libro = repositorioLibro.findByLenguaje("en");
                        break;

                    case 2:
                        Libro = repositorioLibro.findByLenguaje("es");
                        break;

                    default:
                        System.out.println("Opcion invalida porfavor ingrese otra");
                }
                libroList.stream().forEach(l -> {
                    System.out.printf("""
                                        Titulo: %s
                                        Autor: %s
                                        Idioma: %s
                                        Descargas: %s
                                    %n""", l.getTitulo(),
                    l.getAutor(),
                    l.getIdioma(),
                    l.getNumeroDescarga().toString());

                });
            } catch (Exception e) {
                System.out.println("Ingresa un valor valido");
            }
        }

    }
