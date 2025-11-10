package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe para carregar configurações da aplicação
 * Permite configurar o banco de dados via variáveis de ambiente ou arquivo properties
 */
public class Config {
    
    private static Properties properties = new Properties();
    
    static {
        // Tenta carregar arquivo de configuração (opcional)
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
                System.out.println("✓ Arquivo application.properties carregado!");
            }
        } catch (IOException e) {
            System.out.println("ℹ Arquivo application.properties não encontrado. Usando variáveis de ambiente.");
        }
    }
    
    /**
     * Obtém uma propriedade, priorizando variáveis de ambiente
     * @param key Nome da propriedade
     * @param defaultValue Valor padrão se não encontrar
     * @return Valor da propriedade
     */
    public static String getProperty(String key, String defaultValue) {
        // 1. Tenta obter de variável de ambiente
        String envValue = System.getenv(key);
        if (envValue != null && !envValue.isEmpty()) {
            return envValue;
        }
        
        // 2. Tenta obter do arquivo properties
        String propValue = properties.getProperty(key);
        if (propValue != null && !propValue.isEmpty()) {
            return propValue;
        }
        
        // 3. Retorna valor padrão
        return defaultValue;
    }
    
    /**
     * Configura as propriedades do sistema para o JPA usar
     * Útil para configurar credenciais do Railway via código
     */
    public static void configureDatabaseFromEnv() {
        String dbUrl = getProperty("DB_URL", "jdbc:postgresql://localhost:5432/sistema_academico");
        String dbUser = getProperty("DB_USER", "postgres");
        String dbPassword = getProperty("DB_PASSWORD", "postgres");
        
        System.setProperty("DB_URL", dbUrl);
        System.setProperty("DB_USER", dbUser);
        System.setProperty("DB_PASSWORD", dbPassword);
        
        System.out.println("✓ Configuração do banco de dados carregada!");
        System.out.println("  URL: " + maskPassword(dbUrl));
        System.out.println("  User: " + dbUser);
    }
    
    /**
     * Mascara a senha na URL para não exibir no console
     */
    private static String maskPassword(String url) {
        if (url.contains("password=")) {
            return url.replaceAll("password=[^&]*", "password=****");
        }
        return url;
    }
}
