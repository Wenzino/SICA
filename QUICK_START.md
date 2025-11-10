# Guia Rápido - Começar em 5 Minutos

## Objetivo

Ter o backend funcionando com Railway PostgreSQL em poucos passos.

## 📋 Checklist Rápido

### 1️⃣ Criar Banco no Railway (2 min)

1. Acesse [railway.app](https://railway.app)
2. Login com GitHub
3. **New Project** → **Provision PostgreSQL**
4. Pronto! 

### 2️⃣ Copiar Credenciais (1 min)

No Railway, clique no PostgreSQL → aba **Variables**:

- Copie `PGHOST`
- Copie `PGDATABASE`
- Copie `PGUSER`
- Copie `PGPASSWORD`

### 3️⃣ Configurar Projeto (1 min)

**Opção A - Terminal (Linux/Mac):**
```bash
export DB_URL="jdbc:postgresql://SEU_PGHOST:5432/SEU_PGDATABASE"
export DB_USER="SEU_PGUSER"
export DB_PASSWORD="SEU_PGPASSWORD"
```

**Opção B - Terminal (Windows PowerShell):**
```powershell
$env:DB_URL="jdbc:postgresql://SEU_PGHOST:5432/SEU_PGDATABASE"
$env:DB_USER="SEU_PGUSER"
$env:DB_PASSWORD="SEU_PGPASSWORD"
```

**Opção C - Arquivo (mais fácil):**

Crie `src/main/resources/application.properties`:
```properties
DB_URL=jdbc:postgresql://SEU_PGHOST:5432/SEU_PGDATABASE
DB_USER=SEU_PGUSER
DB_PASSWORD=SEU_PGPASSWORD
```

### 4️⃣ Testar (1 min)

```bash
mvn clean compile exec:java -Dexec.mainClass="TestBackend"
```

Se ver isso, está funcionando! 
```
✓ EntityManagerFactory criado com sucesso!
✓ Conexão JPA estabelecida com sucesso!
✓ TODOS OS TESTES PASSARAM COM SUCESSO!
```

##  Deu Erro?

### "Connection refused"
- Verifique se copiou o `PGHOST` correto
- Confirme que o PostgreSQL está ativo no Railway

### "Authentication failed"
- Verifique `PGUSER` e `PGPASSWORD`
- Não deixe espaços extras

### "SSL connection required"
Mude a URL para:
```
jdbc:postgresql://SEU_PGHOST:5432/SEU_PGDATABASE?sslmode=require
```

## Próximos Passos

Agora que funciona:

1. Backend configurado
2. Leia [BACKEND_README.md](BACKEND_README.md) - Como usar os DAOs
3. Leia [RAILWAY_SETUP.md](RAILWAY_SETUP.md) - Guia completo
4. Comece a desenvolver!

## Exemplo Rápido de Uso

```java
import model.dao.AlunoDAO;
import model.entities.Aluno;
import util.JPAUtil;

public class MeuTeste {
    public static void main(String[] args) {
        // Criar DAO
        AlunoDAO dao = new AlunoDAO();
        
        // Criar e salvar aluno
        Aluno aluno = new Aluno("João", "2024001", "joao@email.com");
        aluno = dao.save(aluno);
        
        System.out.println("Aluno salvo: " + aluno);
        
        // Buscar todos
        dao.findAll().forEach(System.out::println);
        
        // Fechar recursos
        JPAUtil.close();
    }
}
```

## Estrutura Criada

```
Entidades: Aluno, Curso, Disciplina, Matricula
DAOs: Com operações CRUD completas
Configuração: JPA + Hibernate + PostgreSQL
Testes: Classe TestBackend.java
Documentação: 3 guias completos
```

## Dicas Importantes

1. **Segurança**: Nunca commite `application.properties` (já está no `.gitignore`)
2. **Railway**: Plano grátis dá $5/mês (suficiente para estudar)
3. **Debug**: As queries SQL aparecem no console
4. **Tabelas**: São criadas automaticamente pelo Hibernate

---

**Pronto para começar? Execute o teste e veja a mágica acontecer! ✨**
