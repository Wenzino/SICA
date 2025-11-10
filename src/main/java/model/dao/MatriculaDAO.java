package model.dao;

import model.entities.Matricula;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO para operacoes com a entidade Matricula
 */
public class MatriculaDAO extends GenericDAO<Matricula> {
    
    public MatriculaDAO() {
        super(Matricula.class);
    }
    
    /**
     * Busca todas as matriculas de um aluno
     * @param alunoId ID do aluno
     * @return Lista de matriculas
     */
    public List<Matricula> findByAluno(Long alunoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Matricula> query = em.createQuery(
                "SELECT m FROM Matricula m WHERE m.aluno.id = :alunoId", Matricula.class);
            query.setParameter("alunoId", alunoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca todas as matriculas de um curso
     * @param cursoId ID do curso
     * @return Lista de matriculas
     */
    public List<Matricula> findByCurso(Long cursoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Matricula> query = em.createQuery(
                "SELECT m FROM Matricula m WHERE m.curso.id = :cursoId", Matricula.class);
            query.setParameter("cursoId", cursoId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca matriculas por status
     * @param status Status da matricula
     * @return Lista de matriculas
     */
    public List<Matricula> findByStatus(Matricula.StatusMatricula status) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Matricula> query = em.createQuery(
                "SELECT m FROM Matricula m WHERE m.status = :status", Matricula.class);
            query.setParameter("status", status);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Verifica se ja existe uma matricula ativa para o aluno no curso
     * @param alunoId ID do aluno
     * @param cursoId ID do curso
     * @return true se ja existe matricula ativa
     */
    public boolean existsActiveMatricula(Long alunoId, Long cursoId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(m) FROM Matricula m WHERE m.aluno.id = :alunoId " +
                "AND m.curso.id = :cursoId AND m.status = :status", Long.class);
            query.setParameter("alunoId", alunoId);
            query.setParameter("cursoId", cursoId);
            query.setParameter("status", Matricula.StatusMatricula.ATIVA);
            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }
}
