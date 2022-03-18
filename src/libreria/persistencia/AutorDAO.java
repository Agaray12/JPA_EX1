package libreria.persistencia;

import libreria.entidades.Autor;


public class AutorDAO extends DAO{
    
    public void crear(Autor autor) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            try {
                em.getTransaction().rollback();
            } catch (Exception ex) {
                throw new Exception("Error haciendo un rollback");
            }
            
            throw new Exception("Error al persitir un autor");
        }
    }
    
    public void modificar(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una editorial");
        }
    }
    
    public void eliminar(Autor autor) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar una editorial");
        }
    }
    
    public Autor buscarAutorPorNombre(String nombre){
        return (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
    }
}
