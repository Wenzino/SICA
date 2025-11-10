package model.dao;

import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * DAO genérico com operações CRUD básicas
 * Todas as outras DAOs podem estender esta classe
 * @param <T> Tipo da entidade
 */
public abstract class GenericDAO<T> {
    
    private final Class<T> entityClass;
    
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     * Salva uma nova entidade no banco
     * @param entity Entidade a ser salva
     * @return Entidade salva com ID gerado
     */
    public T save(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao salvar " + entityClass.getSimpleName(), e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Atualiza uma entidade existente
     * @param entity Entidade a ser atualizada
     * @return Entidade atualizada
     */
    public T update(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T updated = em.merge(entity);
            em.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar " + entityClass.getSimpleName(), e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Remove uma entidade pelo ID
     * @param id ID da entidade
     */
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar " + entityClass.getSimpleName(), e);
        } finally {
            em.close();
        }
    }
    
    /**
     * Busca uma entidade pelo ID
     * @param id ID da entidade
     * @return Entidade encontrada ou null
     */
    public T findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }
    
    /**
     * Lista todas as entidades
     * @return Lista de entidades
     */
    public List<T> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    /**
     * Conta o total de registros
     * @return Número total de registros
     */
    public Long count() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }
}
