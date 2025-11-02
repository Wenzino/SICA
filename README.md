
# Sistema de Cadastro Acadêmico

## Visão Geral

Este projeto é um **Sistema de Cadastro Acadêmico** desenvolvido em **Java**, seguindo a arquitetura **Model-View-Controller (MVC)**.  
O objetivo é permitir o **gerenciamento de estudantes, cursos, disciplinas e matrículas** através de uma interface gráfica construída com **JavaFX**.  

O backend é responsável pela persistência de dados utilizando **JPA** (Java Persistence API) ou **JDBC**, com o banco de dados **PostgreSQL**.  
Opcionalmente, o sistema pode ser configurado para conectar-se a um banco hospedado na nuvem através do **Railway**, facilitando testes e implantação.

---

## Estrutura do Projeto

A organização do código segue o padrão **MVC**, garantindo separação clara entre as camadas de **modelo**, **controle** e **visão**.

```
src/
 ├── model/
 │    ├── entities/
 │    │     ├── Aluno.java
 │    │     ├── Curso.java
 │    │     ├── Disciplina.java
 │    │     └── Matricula.java
 │    ├── dao/
 │    │     ├── AlunoDAO.java
 │    │     ├── CursoDAO.java
 │    │     └── ConnectionFactory.java
 │    └── repository/
 │          └── JPAUtil.java
 │
 ├── controller/
 │    ├── AlunoController.java
 │    ├── CursoController.java
 │    └── MatriculaController.java
 │
 ├── view/
 │    ├── main/
 │    │     └── MainApp.java
 │    ├── fxml/
 │    │     ├── aluno.fxml
 │    │     ├── curso.fxml
 │    │     └── matricula.fxml
 │    └── css/
 │          └── style.css
 │
 ├── util/
 │    └── Config.java
 │
 └── resources/
      └── META-INF/persistence.xml
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

## Configuração do Banco de Dados

### Ambiente Local
1. Instale o **PostgreSQL** e crie um novo banco de dados:
   ```sql
   CREATE DATABASE sistema_academico;
   ```
2. Atualize a configuração de conexão em `src/util/Config.java` ou no arquivo `persistence.xml`:
   ```xml
   <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:5432/sistema_academico"/>
   <property name="javax.persistence.jdbc.user" value="postgres"/>
   <property name="javax.persistence.jdbc.password" value="sua_senha"/>
   ```

### Railway (opcional)
1. Crie um projeto no [Railway.app](https://railway.app).  
2. Adicione o serviço PostgreSQL.  
3. Copie a URL de conexão e credenciais.  
4. Substitua os valores no arquivo de configuração.

---

## Execução do Projeto

### Através de IDE (IntelliJ / Eclipse)
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-cadastro-academico.git
   ```
2. Abra o projeto na sua IDE.  
3. Execute o build via Maven para instalar dependências.  
4. Inicie a aplicação executando a classe principal:
   ```
   src/view/main/MainApp.java
   ```

### Através do terminal
```bash
mvn clean javafx:run
```

---

## Objetivos do Projeto

O sistema foi desenvolvido com fins educacionais, abordando conceitos como:
- Construção de interfaces com **JavaFX**
- Manipulação de dados com **JPA/JDBC**
- Aplicação da arquitetura **MVC** em sistemas desktop
- Integração com banco de dados **PostgreSQL** local e remoto
- Separação de camadas e boas práticas de desenvolvimento em Java

---

## Melhorias Futuras

- Autenticação e controle de usuários (Administrador, Estudante)  
- Geração de relatórios em PDF ou Excel  
- Integração com API REST  
- Testes unitários com JUnit  
- Suporte a Docker para implantação simplificada  

---

## Licença

Este projeto está licenciado sob a **MIT License** — livre para uso, modificação e distribuição para fins educacionais ou pessoais.
