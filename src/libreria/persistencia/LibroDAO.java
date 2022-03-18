package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;


public class LibroDAO extends DAO{
    
    public void crear(Libro libro) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }
            
            throw new Exception("Error al persitir una editorial");
        }
    }
    
    public void modificar(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una editorial");
        }
    }
    
    public void eliminar(Libro libro) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar una editorial");
        }
    }
    
    public Libro buscarLibroPorISBN(Long isbn){
        return em.find(Libro.class, isbn);
    }
    
    public Libro buscarLibroPorTitulo(String titulo){
        return (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
    }
    
    public List<Libro> buscarLibroPorAutor(String autor){
        return em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre = :autor").setParameter("autor", autor).getResultList();
    }
    
    public List<Libro> buscarLibroPorEditorial(String editorial){
        return em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :editorial").setParameter("editorial", editorial).getResultList();
    }
    
    public List<Libro> listarLibrosDisponibles(){
        return em.createQuery("SELECT l FROM Libro l WHERE l.alta LIKE TRUE").getResultList();
    }
    
}
