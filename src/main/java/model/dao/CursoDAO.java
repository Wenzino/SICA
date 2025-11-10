package model.dao;

import model.entities.Curso;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO para operações com a entidade Curso
 */
public class CursoDAO extends GenericDAO<Curso> {
    
    public CursoDAO() {
        super(Curso.class);
    }
    
    /**
     * Busca curso por código
     * @param codigo Código do curso
     * @return Curso encontrado ou null
     */
    public Curso findByCodigo(String codigo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Curso> query = em.createQuery(
                "SELECT c FROM Curso c WHERE c.codigo = :codigo", Curso.class);
            query.setParameter("codigo", codigo);
            List<Curso> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca cursos por nome (busca parcial)
     * @param nome Nome ou parte do nome
     * @return Lista de cursos encontrados
     */
    public List<Curso> findByNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Curso> query = em.createQuery(
                "SELECT c FROM Curso c WHERE LOWER(c.nome) LIKE LOWER(:nome)", Curso.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
