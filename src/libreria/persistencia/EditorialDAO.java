package libreria.persistencia;

import libreria.entidades.Editorial;


public class EditorialDAO extends DAO{
    
    public void crear(Editorial editorial) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(editorial);
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
    
    public void modificar(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.merge(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al modificar una editorial");
        }
    }
    
    public void eliminar(Editorial editorial) throws Exception{
        try {
            em.getTransaction().begin();
            em.remove(editorial);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new Exception("Error al eliminar una editorial");
        }
    }
    
    public Editorial buscarEditorialPorNombre(String nombre){
        return (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre").setParameter("nombre", nombre).getSingleResult();
    }
    
}
