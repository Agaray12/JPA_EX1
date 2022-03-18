package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAO {
    protected EntityManager em = Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
}
