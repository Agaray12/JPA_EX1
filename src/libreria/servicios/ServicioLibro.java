package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;


public class ServicioLibro {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    LibroDAO ldao = new LibroDAO();
    
    public Libro pedirLibro(){
        Libro libro = new Libro();
        ServicioAutor aser = new ServicioAutor();
        ServicioEditorial eser = new ServicioEditorial();
        
        System.out.println("Ingrese el título del libro");
        libro.setTitulo(leer.next());
        System.out.println("Ingrese el nombre del autor");
        libro.setAutor(aser.buscarAutorPorNombre(leer.next()));
        System.out.println("Ingrese el nombre de la editorial");
        libro.setEditorial(eser.buscarEditorialPorNombre(leer.next()));
        System.out.println("Ingrese el año de publicación del libro");
        libro.setAnio(leer.nextInt());
        System.out.println("Ingrese la cantidad de ejemplares");
        libro.setEjemplares(leer.nextInt());
        System.out.println("Ingrese la cantidad de ejemplares prestados");
        libro.setEjemplaresPrestados(leer.nextInt());
        libro.setEjemplaresRestantes((libro.getEjemplares())-(libro.getEjemplaresPrestados()));
        
        return libro;
    }
    
    public void crearLibro(Libro libro) throws Exception{
        validaciones(libro);
        
        ldao.crear(libro);
    }
    
    public void modificarAlta(Libro libro) throws Exception{
        if(libro.getAlta()){
            libro.setAlta(Boolean.FALSE);
        }else if(!libro.getAlta()){
            libro.setAlta(Boolean.TRUE);
        }        
    }
    
    public void validaciones(Libro libro) throws Exception{
        if(libro.getTitulo().equalsIgnoreCase(buscarLibroPorTitulo(libro.getTitulo()).getTitulo())){
            throw new Exception("El título no puede ser repetido");
        }      
        if(libro.getTitulo() == null || libro.getTitulo().isEmpty()){
            throw new Exception("El titulo es vacio o nulo");
        }
        if(libro.getEjemplares() < 0){
            throw new Exception("La cantidad de ejemplares es inválida");
        }
        if(libro.getEjemplaresPrestados() > libro.getEjemplares()){
            throw new Exception("La cantidad de libros prestada no puede ser mayor que la cantidad total de libros");
        }
        if(libro.getAutor() == null){
            throw new Exception("El autor es nulo");
        }
        if(libro.getEditorial() == null){
            throw new Exception("La editorial es nula");
        }
    }
    
    public Libro buscarLibroPorISBN(Long isbn){
        return ldao.buscarLibroPorISBN(isbn);
    }
    
    public Libro buscarLibroPorTitulo(String titulo){
        return ldao.buscarLibroPorTitulo(titulo);
    }
    
    public List<Libro> buscarLibroPorAutor(String autor){
        return ldao.buscarLibroPorAutor(autor);
    }
    
    public List<Libro> buscarLibroPorEditorial(String editorial){
        return ldao.buscarLibroPorEditorial(editorial);
    }
    
    public List<Libro> listarLibrosDisponibles(){
        return ldao.listarLibrosDisponibles();
    }
}
