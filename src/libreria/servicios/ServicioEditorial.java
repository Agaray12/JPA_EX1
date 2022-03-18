package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;


public class ServicioEditorial {
    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    EditorialDAO edao = new EditorialDAO();
    
    public Editorial pedirEditorial(){
        Editorial editorial = new Editorial();
        System.out.println("Ingrese el nombre de la editorial");
        editorial.setNombre(leer.next());
        return editorial;
    }
    
    public void crearEditorial(Editorial editorial) throws Exception{
        validaciones(editorial);
        
        edao.crear(editorial);
    }
    
    public void validaciones(Editorial editorial) throws Exception{
        if(editorial.getNombre().equals(buscarEditorialPorNombre(editorial.getNombre()).getNombre())){
            throw new Exception("La editorial no puede ser repetida");
        }
        
        if(editorial.getNombre()==null || editorial.getNombre().isEmpty()){
            throw new Exception("El nombre ingresado es nulo o vacío");
        }
    }
    
    public Editorial buscarEditorialPorNombre(String nombre){
        return edao.buscarEditorialPorNombre(nombre);
    }
    
    public void modificarAlta(Editorial editorial){
        if(editorial.getAlta()){
            editorial.setAlta(Boolean.FALSE);
        }else if(!editorial.getAlta()){
            editorial.setAlta(Boolean.TRUE);
        }
    }
    
}
