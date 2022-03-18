package libreria;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.ServicioAutor;
import libreria.servicios.ServicioEditorial;
import libreria.servicios.ServicioLibro;

public class Libreria {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        final String SALIR = "s";
        String opcion;
        ServicioAutor sa = new ServicioAutor();
        ServicioEditorial se = new ServicioEditorial();
        ServicioLibro sl = new ServicioLibro();

        try {
            //creo autores
            Autor a1 = new Autor("Oscar Wilde");
            Autor a2 = new Autor("Jane Austen");
            Autor a3 = new Autor("Cortazar");
            Autor a4 = new Autor("Sabato");
            sa.crearAutor(a1);
            sa.crearAutor(a2);
            sa.crearAutor(a3);
            sa.crearAutor(a4);

            //creo editoriales
            Editorial e1 = new Editorial("Planeta");
            Editorial e2 = new Editorial("Adisson");
            Editorial e3 = new Editorial("Puerto");
            se.crearEditorial(e1);
            se.crearEditorial(e2);
            se.crearEditorial(e3);

            //creo Libros
            Libro l1 = new Libro("Orgullo", 1850, 100, 50, 25, a2, e1);
            Libro l2 = new Libro("Oliver", 1895, 200, 50, 75, a1, e1);
            Libro l3 = new Libro("Rayuela", 1948, 300, 50, 45, a3, e3);
            Libro l4 = new Libro("Tunel", 1920, 150, 75, 20, a4, e2);
            sl.crearLibro(l1);
            sl.crearLibro(l2);
            sl.crearLibro(l3);
            sl.crearLibro(l4);

            do {
                System.out.println("----------------------------");
                System.out.println("Ingrese la opción que desea realizar");
                System.out.println("a) Crear un autor \nb) Crear una editorial");
                System.out.println("c) Crear un libro");
                System.out.println("d) Cambiar la condición de alta de autor \ne) Cambiar la condición de alta de editorial");
                System.out.println("f) Cambiar la condición de alta de un libro");
                System.out.println("g) Buscar libro por ISBN");
                System.out.println("h) Buscar libro por Título");
                System.out.println("i) Buscar libro/s por nombre de autor");
                System.out.println("j) Buscar libro/s por nombre de editorial");
                System.out.println("s) Salir");
                opcion = leer.next();
                Libro libro;
                Autor autor;
                Editorial editorial;

                switch (opcion) {
                    case "a":
                        autor = sa.pedirAutor();
                        sa.crearAutor(autor);
                        break;
                    case "b":
                        editorial = se.pedirEditorial();
                        se.crearEditorial(editorial);
                        break;
                    case "c":
                        libro = sl.pedirLibro();
                        sl.crearLibro(libro);
                        break;
                    case "d":
                        System.out.println("Ingrese el nombre del autor que desea modificar");
                        autor = sa.buscarAutorPorNombre(leer.next());
                        sa.modificarAlta(autor);
                        break;
                    case "e":
                        System.out.println("Ingrese el nombre de la editorial que desea modificar");
                        editorial = se.buscarEditorialPorNombre(leer.next());
                        se.modificarAlta(editorial);
                        break;
                    case "f":
                        System.out.println(sl.listarLibrosDisponibles().toString());
                        System.out.println("Ingrese el título del libro que desea modificar:");
                        libro = sl.buscarLibroPorTitulo(leer.next());
                        sl.modificarAlta(libro);
                    case "g":
                        System.out.println("Ingrese el isbn del libro que desea visualizar");
                        libro = sl.buscarLibroPorISBN(leer.nextLong());
                        System.out.println(libro.toString());
                        break;
                    case "h":
                        System.out.println("Ingrese el titulo del libro que desea visualizar");
                        libro = sl.buscarLibroPorTitulo(leer.next());
                        System.out.println(libro.toString());
                        break;
                    case "i":
                        System.out.println("Ingrese el nombre del autor");
                        System.out.println(sl.buscarLibroPorAutor(leer.next()).toString());
//                        List<Libro> libros = sl.buscarLibroPorAutor(leer.next());
//                        for (Libro libro1 : libros) {
//                            libro1.toString();
//                        }
                        break;
                    case "j":
                        System.out.println("Ingrese el nombre de la editorial");
                        System.out.println(sl.buscarLibroPorEditorial(leer.next()).toString());
//                        List<Libro> libros2 = sl.buscarLibroPorEditorial(leer.next());
//                        for (Libro libro2 : libros2) {
//                            libro2.toString();
//                        }
                }

            } while (!opcion.equalsIgnoreCase(SALIR));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
