package model.dao;

import model.entities.Disciplina;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO para operações com a entidade Disciplina
 */
public class DisciplinaDAO extends GenericDAO<Disciplina> {
    
    public DisciplinaDAO() {
        super(Disciplina.class);
    }
    
    /**
     * Busca disciplina por código
     * @param codigo Código da disciplina
     * @return Disciplina encontrada ou null
     */
    public Disciplina findByCodigo(String codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Disciplina> query = em.createQuery(
                "SELECT d FROM Disciplina d WHERE d.codigo = :codigo", Disciplina.class);
            query.setParameter("codigo", codigo);
            List<Disciplina> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca disciplinas por nome (busca parcial)
     * @param nome Nome ou parte do nome
     * @return Lista de disciplinas encontradas
     */
    public List<Disciplina> findByNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Disciplina> query = em.createQuery(
                "SELECT d FROM Disciplina d WHERE LOWER(d.nome) LIKE LOWER(:nome)", Disciplina.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
