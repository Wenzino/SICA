package model.dao;

import model.entities.Aluno;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO para operações com a entidade Aluno
 */
public class AlunoDAO extends GenericDAO<Aluno> {
    
    public AlunoDAO() {
        super(Aluno.class);
    }
    
    /**
     * Busca aluno por matrícula
     * @param matricula Matrícula do aluno
     * @return Aluno encontrado ou null
     */
    public Aluno findByMatricula(String matricula) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Aluno> query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.matricula = :matricula", Aluno.class);
            query.setParameter("matricula", matricula);
            List<Aluno> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca aluno por email
     * @param email Email do aluno
     * @return Aluno encontrado ou null
     */
    public Aluno findByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Aluno> query = em.createQuery(
                "SELECT a FROM Aluno a WHERE a.email = :email", Aluno.class);
            query.setParameter("email", email);
            List<Aluno> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca alunos por nome (busca parcial)
     * @param nome Nome ou parte do nome
     * @return Lista de alunos encontrados
     */
    public List<Aluno> findByNome(String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Aluno> query = em.createQuery(
                "SELECT a FROM Aluno a WHERE LOWER(a.nome) LIKE LOWER(:nome)", Aluno.class);
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
