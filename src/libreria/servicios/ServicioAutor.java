package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;


public class ServicioAutor {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    AutorDAO adao = new AutorDAO();
    
    
    public Autor pedirAutor(){
        Autor autor = new Autor();
        System.out.println("Ingrese el nombre del autor");
        autor.setNombre(leer.next());
        return autor;
    }
    
    
    public void crearAutor(Autor autor) throws Exception{
        
        adao.crear(autor);
    }
    
    public void validaciones(Autor autor) throws Exception{
        if(autor.getNombre() == null || autor.getNombre().isEmpty()){
            throw new Exception("El nombre ingresado es nulo o vacío");
        }
        if(autor.getNombre().equals(buscarAutorPorNombre(autor.getNombre()).getNombre())){
            throw new Exception("El nombre del autor no puede ser repetido");
        }
    }
    
    public Autor buscarAutorPorNombre(String nombre){
        return adao.buscarAutorPorNombre(nombre);
    }
    
    public void modificarAlta(Autor autor){
        if(autor.getAlta()){
            autor.setAlta(Boolean.FALSE);
        }else if(!autor.getAlta()){
            autor.setAlta(Boolean.TRUE);
        }
    }
    
}
