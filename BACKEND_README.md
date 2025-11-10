# 🎓 Backend do Sistema de Cadastro Acadêmico

Backend simples e funcional para gerenciamento acadêmico usando **Java + JPA + PostgreSQL**.

## 📁 Estrutura Criada

```
src/main/java/
├── model/
│   ├── entities/          # Entidades JPA
│   │   ├── Aluno.java
│   │   ├── Curso.java
│   │   ├── Disciplina.java
│   │   └── Matricula.java
│   └── dao/               # Data Access Objects
│       ├── GenericDAO.java
│       ├── AlunoDAO.java
│       ├── CursoDAO.java
│       ├── DisciplinaDAO.java
│       └── MatriculaDAO.java
├── util/                  # Utilitários
│   ├── JPAUtil.java       # Gerenciador JPA
│   └── Config.java        # Configurações
└── TestBackend.java       # Teste do backend

src/main/resources/
└── META-INF/
    └── persistence.xml    # Configuração JPA
```

## 🚀 Como Usar

### 1. Instalar Dependências

```bash
mvn clean install
```

### 2. Configurar Banco de Dados

Siga o guia completo em **[RAILWAY_SETUP.md](RAILWAY_SETUP.md)**

**Resumo rápido:**
- Crie um banco PostgreSQL no [Railway.app](https://railway.app)
- Configure as variáveis de ambiente:

```bash
export DB_URL="jdbc:postgresql://seu-host:5432/railway"
export DB_USER="postgres"
export DB_PASSWORD="sua_senha"
```

### 3. Testar o Backend

```bash
mvn compile exec:java -Dexec.mainClass="TestBackend"
```

Você deve ver:
```
✓ EntityManagerFactory criado com sucesso!
✓ Conexão JPA estabelecida com sucesso!
✓ TODOS OS TESTES PASSARAM COM SUCESSO!
```

## 📚 Como Usar os DAOs

### Exemplo: Criar e Salvar um Aluno

```java
import model.dao.AlunoDAO;
import model.entities.Aluno;
import java.time.LocalDate;

// Criar DAO
AlunoDAO alunoDAO = new AlunoDAO();

// Criar aluno
Aluno aluno = new Aluno("Maria Santos", "2024002", "maria@email.com");
aluno.setDataNascimento(LocalDate.of(2001, 3, 20));
aluno.setTelefone("11988888888");

// Salvar no banco
aluno = alunoDAO.save(aluno);
System.out.println("Aluno salvo com ID: " + aluno.getId());
```

### Exemplo: Buscar Alunos

```java
// Buscar todos
List<Aluno> todos = alunoDAO.findAll();

// Buscar por ID
Aluno aluno = alunoDAO.findById(1L);

// Buscar por matrícula
Aluno aluno = alunoDAO.findByMatricula("2024001");

// Buscar por nome (parcial)
List<Aluno> alunos = alunoDAO.findByNome("Maria");
```

### Exemplo: Atualizar Aluno

```java
Aluno aluno = alunoDAO.findById(1L);
aluno.setTelefone("11977777777");
aluno = alunoDAO.update(aluno);
```

### Exemplo: Deletar Aluno

```java
alunoDAO.delete(1L);
```

### Exemplo: Criar Matrícula

```java
import model.dao.*;
import model.entities.*;

AlunoDAO alunoDAO = new AlunoDAO();
CursoDAO cursoDAO = new CursoDAO();
MatriculaDAO matriculaDAO = new MatriculaDAO();

// Buscar aluno e curso
Aluno aluno = alunoDAO.findById(1L);
Curso curso = cursoDAO.findById(1L);

// Verificar se já existe matrícula ativa
if (!matriculaDAO.existsActiveMatricula(aluno.getId(), curso.getId())) {
    // Criar matrícula
    Matricula matricula = new Matricula(aluno, curso);
    matricula = matriculaDAO.save(matricula);
    System.out.println("Matrícula criada: " + matricula);
} else {
    System.out.println("Aluno já está matriculado neste curso!");
}
```

## 🔧 Operações Disponíveis

### GenericDAO (Todas as entidades herdam)

- `save(entity)` - Salvar nova entidade
- `update(entity)` - Atualizar entidade existente
- `delete(id)` - Deletar por ID
- `findById(id)` - Buscar por ID
- `findAll()` - Listar todas
- `count()` - Contar total de registros

### AlunoDAO (Específicas)

- `findByMatricula(matricula)` - Buscar por matrícula
- `findByEmail(email)` - Buscar por email
- `findByNome(nome)` - Buscar por nome (parcial)

### CursoDAO (Específicas)

- `findByCodigo(codigo)` - Buscar por código
- `findByNome(nome)` - Buscar por nome (parcial)

### DisciplinaDAO (Específicas)

- `findByCodigo(codigo)` - Buscar por código
- `findByNome(nome)` - Buscar por nome (parcial)

### MatriculaDAO (Específicas)

- `findByAluno(alunoId)` - Listar matrículas de um aluno
- `findByCurso(cursoId)` - Listar matrículas de um curso
- `findByStatus(status)` - Buscar por status
- `existsActiveMatricula(alunoId, cursoId)` - Verificar se já existe matrícula ativa

## 🗄️ Modelo de Dados

### Aluno
- `id` (Long) - Chave primária
- `nome` (String) - Nome completo
- `matricula` (String) - Matrícula única
- `email` (String) - Email único
- `dataNascimento` (LocalDate) - Data de nascimento
- `telefone` (String) - Telefone

### Curso
- `id` (Long) - Chave primária
- `nome` (String) - Nome do curso
- `codigo` (String) - Código único
- `descricao` (String) - Descrição
- `cargaHoraria` (Integer) - Carga horária total

### Disciplina
- `id` (Long) - Chave primária
- `nome` (String) - Nome da disciplina
- `codigo` (String) - Código único
- `cargaHoraria` (Integer) - Carga horária
- `ementa` (String) - Ementa

### Matricula
- `id` (Long) - Chave primária
- `aluno` (Aluno) - Referência ao aluno
- `curso` (Curso) - Referência ao curso
- `dataMatricula` (LocalDate) - Data da matrícula
- `status` (StatusMatricula) - Status: ATIVA, TRANCADA, CONCLUIDA, CANCELADA

## ⚙️ Configuração Automática

O Hibernate está configurado para:
- ✅ Criar tabelas automaticamente (`hibernate.hbm2ddl.auto=update`)
- ✅ Mostrar queries SQL no console (útil para debug)
- ✅ Formatar SQL para melhor legibilidade
- ✅ Usar pool de conexões (10 conexões)

## 🔒 Segurança

**IMPORTANTE**: Nunca commite suas credenciais!

O `.gitignore` já está configurado para proteger:
- `.env`
- `application.properties`
- Outros arquivos sensíveis

## 📝 Próximos Passos

1. ✅ Backend implementado
2. 🔜 Criar Controllers para JavaFX
3. 🔜 Desenvolver interface gráfica (FXML)
4. 🔜 Conectar View → Controller → DAO
5. 🔜 Adicionar validações
6. 🔜 Implementar tratamento de erros

## 💡 Dicas

### Fechar Recursos

Sempre feche o `EntityManagerFactory` ao encerrar a aplicação:

```java
// No final da aplicação
JPAUtil.close();
```

### Debug de Queries

As queries SQL aparecem no console. Se quiser desabilitar:

```xml
<!-- Em persistence.xml -->
<property name="hibernate.show_sql" value="false"/>
```

### Transações

Os DAOs já gerenciam transações automaticamente. Não precisa se preocupar!

### Erros Comuns

1. **EntityManagerFactory não inicializa**
   - Verifique credenciais do banco
   - Confirme que o PostgreSQL está acessível

2. **Tabelas não são criadas**
   - Verifique `hibernate.hbm2ddl.auto=update`
   - Confirme que as entidades estão listadas no `persistence.xml`

3. **Erro de conexão SSL**
   - Adicione `?sslmode=require` na URL do banco

## 📖 Recursos

- [Documentação JPA](https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm)
- [Hibernate Guide](https://hibernate.org/orm/documentation/)
- [Railway Docs](https://docs.railway.app/)

---

**Desenvolvido com ❤️ para aprendizado de Java + JPA + PostgreSQL**
