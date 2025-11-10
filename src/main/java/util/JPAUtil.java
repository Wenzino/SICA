package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe utilitária para gerenciar o EntityManager do JPA
 * Implementa o padrão Singleton para garantir uma única instância do EntityManagerFactory
 */
public class JPAUtil {
    
    private static EntityManagerFactory emf;
    
    // Bloco estático para inicializar o EntityManagerFactory
    static {
        try {
            // Configurar credenciais do banco via código
            Map<String, String> properties = new HashMap<>();
            
            // Obter credenciais (prioriza variáveis de ambiente, depois application.properties, depois padrão)
            String dbUrl = Config.getProperty("DB_URL", "jdbc:postgresql://localhost:5432/sistema_academico");
            String dbUser = Config.getProperty("DB_USER", "postgres");
            String dbPassword = Config.getProperty("DB_PASSWORD", "postgres");
            
            properties.put("javax.persistence.jdbc.url", dbUrl);
            properties.put("javax.persistence.jdbc.user", dbUser);
            properties.put("javax.persistence.jdbc.password", dbPassword);
            
            // Carrega as configurações do persistence.xml com as propriedades adicionais
            emf = Persistence.createEntityManagerFactory("sicaPU", properties);
            System.out.println("EntityManagerFactory criado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao criar EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }
    
    /**
     * Retorna um novo EntityManager
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory não foi inicializado!");
        }
        return emf.createEntityManager();
    }
    
    /**
     * Fecha o EntityManagerFactory
     * Deve ser chamado ao encerrar a aplicação
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("EntityManagerFactory fechado!");
        }
    }
    
    /**
     * Verifica se o EntityManagerFactory está aberto
     * @return true se estiver aberto
     */
    public static boolean isOpen() {
        return emf != null && emf.isOpen();
    }
}
