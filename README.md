
# Sistema de Cadastro Acadêmico

## Visão Geral

Este projeto é um **Sistema de Cadastro Acadêmico** desenvolvido em **Java**, seguindo a arquitetura **Model-View-Controller (MVC)**.  
O objetivo é permitir o **gerenciamento de estudantes, cursos, disciplinas e matrículas** através de uma interface gráfica construída com **JavaFX**.  

O backend está **100% implementado e funcional**, utilizando **JPA/Hibernate** para persistência de dados com banco de dados **PostgreSQL**.  
O sistema pode ser configurado para usar PostgreSQL local ou hospedado na nuvem através do **Railway**.

---

## Status do Projeto

- **Backend**: Completo e funcional
  - 4 entidades JPA (Aluno, Curso, Disciplina, Matricula)
  - 5 DAOs com operações CRUD completas
  - Configuração JPA/Hibernate
  - Suporte a Railway PostgreSQL e PostgreSQL local
  
- **Frontend**: Em desenvolvimento
  - Controllers JavaFX (pendente)
  - Views FXML (pendente)

---

## Documentação Disponível

Este projeto inclui documentação completa para facilitar o desenvolvimento e uso:

### Guias de Configuração
- **[QUICK_START.md](QUICK_START.md)** - Guia rápido para começar em 5 minutos
- **[RAILWAY_SETUP.md](RAILWAY_SETUP.md)** - Configuração detalhada do Railway PostgreSQL
- **[CONFIGURAR_RAILWAY.md](CONFIGURAR_RAILWAY.md)** - Passo a passo para configurar credenciais

### Documentação Técnica
- **[BACKEND_README.md](BACKEND_README.md)** - Documentação completa do backend com exemplos de uso
- **[IMPLEMENTACAO.md](IMPLEMENTACAO.md)** - Resumo técnico de tudo que foi implementado

### Scripts Utilitários
- **`testar-conexao.sh`** - Script para testar conexão com Railway
- **`instalar-postgres-local.sh`** - Script para instalar PostgreSQL local no Fedora

---

## Estrutura do Projeto

A organização do código segue o padrão **MVC**, garantindo separação clara entre as camadas de **modelo**, **controle** e **visão**.

```
SICA/
├── src/main/java/
│   ├── model/
│   │   ├── entities/          # Entidades JPA
│   │   │   ├── Aluno.java
│   │   │   ├── Curso.java
│   │   │   ├── Disciplina.java
│   │   │   └── Matricula.java
│   │   └── dao/               # Data Access Objects
│   │       ├── GenericDAO.java
│   │       ├── AlunoDAO.java
│   │       ├── CursoDAO.java
│   │       ├── DisciplinaDAO.java
│   │       └── MatriculaDAO.java
│   ├── controller/            # Controllers JavaFX (em desenvolvimento)
│   ├── view/                  # Views JavaFX (em desenvolvimento)
│   ├── util/                  # Classes utilitárias
│   │   ├── JPAUtil.java       # Gerenciador JPA
│   │   └── Config.java        # Configurações
│   └── TestBackend.java       # Classe de teste do backend
│
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml    # Configuração JPA
│
├── pom.xml                    # Configuração Maven
├── .gitignore                 # Arquivos ignorados pelo Git
│
├── QUICK_START.md             # Guia rápido
├── RAILWAY_SETUP.md           # Setup Railway
├── CONFIGURAR_RAILWAY.md      # Configurar credenciais
├── BACKEND_README.md          # Documentação do backend
├── IMPLEMENTACAO.md           # Resumo técnico
│
├── testar-conexao.sh          # Script teste Railway
└── instalar-postgres-local.sh # Script instalação PostgreSQL
```

---

## Como Funciona o Padrão MVC

O padrão **MVC (Model-View-Controller)** é uma forma de estruturar aplicações que separa a lógica de negócios, a interface do usuário e o controle do fluxo de dados.  
Essa abordagem facilita a manutenção, a escalabilidade e o reaproveitamento de código.

### 1. Model (Modelo)
A camada **Model** é responsável por representar os **dados e regras de negócio** do sistema.  
Ela contém:
- As **entidades** (como `Aluno`, `Curso`, `Disciplina`, `Matricula`);
- As **classes DAO** (Data Access Object) que fazem a comunicação com o banco de dados via **JPA** ou **JDBC**;
- A lógica de persistência e consultas SQL.  

Exemplo de fluxo:
> O controller solicita ao DAO uma lista de alunos → o DAO consulta o banco → retorna os dados ao controller.

### 2. View (Visão)
A camada **View** é responsável pela **interface gráfica** e interação com o usuário.  
Neste projeto, é construída com **JavaFX**, utilizando arquivos **FXML** e **CSS** para layout e estilo.  

Exemplo de fluxo:
> O usuário clica em “Cadastrar Aluno” → a View chama um método do Controller → o Controller processa a lógica e atualiza a tela.

### 3. Controller (Controle)
A camada **Controller** faz a **ponte entre a View e o Model**.  
Ela:
- Recebe ações do usuário (como cliques, envios de formulário);
- Valida os dados recebidos;
- Chama os métodos do Model (DAO ou repositório);
- Atualiza a View com as respostas.  

Exemplo de fluxo:
> O usuário preenche o formulário de aluno → o Controller valida os campos → chama `AlunoDAO.save()` → atualiza a tabela na View.

### Fluxo Resumido do Sistema

```
[Usuário] ⇄ [View (JavaFX)] ⇄ [Controller] ⇄ [Model (DAO/JPA)] ⇄ [Banco PostgreSQL]
```

Essa separação permite que a interface (View) mude sem alterar a lógica de negócio (Model), e vice-versa, tornando o sistema modular e de fácil manutenção.

---

## Tecnologias Utilizadas

- **Java 17+**
- **JavaFX 17+**
- **JPA / JDBC**
- **PostgreSQL**
- **Maven** (gerenciador de dependências)
- **Railway.app** (opcional, para hospedagem do banco de dados)
- **Scene Builder** (para design das telas em FXML)

---

## Funcionalidades

- Cadastro e gerenciamento de alunos, cursos e disciplinas  
- Matrícula de alunos em cursos  
- Edição e exclusão de registros  
- Pesquisa e listagem de entidades  
- Armazenamento persistente com PostgreSQL  
- Interface gráfica desenvolvida com JavaFX e FXML  
- Estrutura modular e organizada em MVC  

---

## Inicio Rápido

### Opção 1: PostgreSQL Local (Recomendado para Testes)

Execute o script de instalação:

```bash
./instalar-postgres-local.sh
```

Teste o backend:

```bash
mvn compile exec:java -Dexec.mainClass="TestBackend"
```

### Opção 2: Railway PostgreSQL (Recomendado para Produção)

1. Crie um banco PostgreSQL no [Railway.app](https://railway.app)
2. Copie as credenciais (host público, usuário, senha)
3. Crie o arquivo `src/main/resources/application.properties`:

```properties
DB_URL=jdbc:postgresql://SEU_HOST_PUBLICO:5432/railway
DB_USER=postgres
DB_PASSWORD=SUA_SENHA
```

4. Teste o backend:

```bash
mvn compile exec:java -Dexec.mainClass="TestBackend"
```

Para mais detalhes, consulte:
- **[QUICK_START.md](QUICK_START.md)** - Guia completo de 5 minutos
- **[RAILWAY_SETUP.md](RAILWAY_SETUP.md)** - Configuração detalhada do Railway
- **[CONFIGURAR_RAILWAY.md](CONFIGURAR_RAILWAY.md)** - Solução de problemas

---

## Configuração do Banco de Dados

O sistema suporta duas formas de configuração:

### 1. Arquivo application.properties (Recomendado)

Crie `src/main/resources/application.properties`:

```properties
DB_URL=jdbc:postgresql://localhost:5432/sistema_academico
DB_USER=postgres
DB_PASSWORD=postgres
```

### 2. Variáveis de Ambiente

```bash
export DB_URL="jdbc:postgresql://localhost:5432/sistema_academico"
export DB_USER="postgres"
export DB_PASSWORD="postgres"
```

O sistema prioriza variáveis de ambiente sobre o arquivo properties.

**Nota**: O arquivo `application.properties` está no `.gitignore` para proteger suas credenciais.

---

## Execução do Projeto

### 1. Instalar Dependências

```bash
mvn clean install
```

### 2. Testar o Backend

```bash
mvn compile exec:java -Dexec.mainClass="TestBackend"
```

Se tudo estiver correto, você verá:

```
EntityManagerFactory criado com sucesso!
Conexao JPA estabelecida com sucesso!
TODOS OS TESTES PASSARAM COM SUCESSO!
```

### 3. Executar via IDE (IntelliJ / Eclipse)

1. Clone o repositório
2. Abra o projeto na IDE
3. Execute `mvn clean install`
4. Execute a classe `TestBackend.java` para testar o backend
5. (Futuro) Execute `MainApp.java` para iniciar a interface gráfica

### 4. Comandos Úteis

```bash
# Compilar o projeto
mvn clean compile

# Executar testes do backend
mvn exec:java -Dexec.mainClass="TestBackend"

# Limpar build anterior
mvn clean

# Ver dependências
mvn dependency:tree
```

---

## Usando os DAOs

O backend está pronto para uso. Exemplo de como usar os DAOs:

```java
import model.dao.AlunoDAO;
import model.entities.Aluno;
import util.JPAUtil;

public class ExemploUso {
    public static void main(String[] args) {
        // Criar DAO
        AlunoDAO alunoDAO = new AlunoDAO();
        
        // Criar e salvar aluno
        Aluno aluno = new Aluno("Maria Santos", "2024001", "maria@email.com");
        aluno = alunoDAO.save(aluno);
        
        // Buscar todos os alunos
        alunoDAO.findAll().forEach(System.out::println);
        
        // Buscar por matrícula
        Aluno encontrado = alunoDAO.findByMatricula("2024001");
        
        // Atualizar
        encontrado.setTelefone("11988888888");
        alunoDAO.update(encontrado);
        
        // Deletar
        alunoDAO.delete(encontrado.getId());
        
        // Fechar recursos
        JPAUtil.close();
    }
}
```

Para mais exemplos, consulte **[BACKEND_README.md](BACKEND_README.md)**.

---

## Objetivos do Projeto

O sistema foi desenvolvido com fins educacionais, abordando conceitos como:
- Manipulação de dados com **JPA/Hibernate**
- Aplicação da arquitetura **MVC** em sistemas desktop
- Integração com banco de dados **PostgreSQL** local e remoto (Railway)
- Separação de camadas e boas práticas de desenvolvimento em Java
- Padrão DAO (Data Access Object) para acesso a dados
- Configuração de projetos Maven
- Gestão de credenciais e segurança

---

## Roadmap

### Fase 1 - Backend (Concluído)
- [x] Entidades JPA (Aluno, Curso, Disciplina, Matricula)
- [x] DAOs com operações CRUD
- [x] Configuração JPA/Hibernate
- [x] Suporte a Railway PostgreSQL
- [x] Suporte a PostgreSQL local
- [x] Documentação completa

### Fase 2 - Controllers JavaFX (Próximo)
- [ ] AlunoController
- [ ] CursoController
- [ ] DisciplinaController
- [ ] MatriculaController

### Fase 3 - Views JavaFX
- [ ] Telas FXML
- [ ] Estilização CSS
- [ ] Validações de formulário
- [ ] Mensagens de feedback

### Fase 4 - Melhorias
- [ ] Autenticação e controle de usuários
- [ ] Geração de relatórios em PDF
- [ ] Testes unitários com JUnit
- [ ] Logs de auditoria
- [ ] Exportação de dados

---

## Contribuindo

Este é um projeto educacional. Contribuições são bem-vindas:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

---

## Suporte

Para dúvidas e problemas:

1. Consulte a documentação disponível nos arquivos `.md`
2. Verifique a seção de troubleshooting em **[RAILWAY_SETUP.md](RAILWAY_SETUP.md)**
3. Execute `TestBackend.java` para verificar se o backend está funcionando
4. Abra uma issue no repositório

---

## Recursos Adicionais

- [Documentação JPA](https://docs.oracle.com/javaee/7/tutorial/persistence-intro.htm)
- [Hibernate Guide](https://hibernate.org/orm/documentation/)
- [JavaFX Documentation](https://openjfx.io/)
- [Railway Documentation](https://docs.railway.app/)
- [Maven Guide](https://maven.apache.org/guides/)

---

## Licença

Este projeto está licenciado sob a **MIT License** — livre para uso, modificação e distribuição para fins educacionais ou pessoais.

---

## Autores

Desenvolvido como projeto educacional para aprendizado de:
- Java
- JPA/Hibernate
- Arquitetura MVC
- PostgreSQL
- Maven
