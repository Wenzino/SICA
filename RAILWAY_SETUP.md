# 🚂 Guia de Configuração do Railway PostgreSQL

Este guia mostra como configurar um banco de dados PostgreSQL no Railway para o Sistema de Cadastro Acadêmico.

## 📋 Pré-requisitos

- Conta no [Railway.app](https://railway.app) (gratuita)
- Java 17+ instalado
- Maven instalado

## 🚀 Passo a Passo

### 1. Criar Projeto no Railway

1. Acesse [railway.app](https://railway.app)
2. Faça login ou crie uma conta (pode usar GitHub)
3. Clique em **"New Project"**
4. Selecione **"Provision PostgreSQL"**
5. Aguarde a criação do banco de dados

### 2. Obter Credenciais do Banco

1. No painel do Railway, clique no serviço PostgreSQL criado
2. Vá na aba **"Variables"** ou **"Connect"**
3. Você verá as seguintes variáveis:
   - `PGHOST` - Host do banco
   - `PGPORT` - Porta (geralmente 5432)
   - `PGDATABASE` - Nome do banco
   - `PGUSER` - Usuário
   - `PGPASSWORD` - Senha
   - `DATABASE_URL` - URL completa de conexão

### 3. Configurar o Projeto

Você tem **3 opções** para configurar as credenciais:

#### **Opção 1: Variáveis de Ambiente (RECOMENDADO)**

No terminal, antes de executar o projeto:

```bash
# Linux/Mac
export DB_URL="jdbc:postgresql://SEU_HOST:5432/SEU_DATABASE"
export DB_USER="seu_usuario"
export DB_PASSWORD="sua_senha"

# Windows (CMD)
set DB_URL=jdbc:postgresql://SEU_HOST:5432/SEU_DATABASE
set DB_USER=seu_usuario
set DB_PASSWORD=sua_senha

# Windows (PowerShell)
$env:DB_URL="jdbc:postgresql://SEU_HOST:5432/SEU_DATABASE"
$env:DB_USER="seu_usuario"
$env:DB_PASSWORD="sua_senha"
```

#### **Opção 2: Arquivo application.properties**

Crie o arquivo `src/main/resources/application.properties`:

```properties
DB_URL=jdbc:postgresql://SEU_HOST:5432/SEU_DATABASE
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

⚠️ **IMPORTANTE**: Adicione este arquivo ao `.gitignore` para não expor suas credenciais!

#### **Opção 3: Editar persistence.xml diretamente**

Edite `src/main/resources/META-INF/persistence.xml` e substitua os valores:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://SEU_HOST:5432/SEU_DATABASE"/>
<property name="javax.persistence.jdbc.user" value="seu_usuario"/>
<property name="javax.persistence.jdbc.password" value="sua_senha"/>
```

⚠️ **NÃO RECOMENDADO**: Suas credenciais ficarão no código!

### 4. Construir URL de Conexão

A URL do Railway geralmente vem no formato:

```
postgresql://user:password@host:port/database
```

Você precisa converter para o formato JDBC:

```
jdbc:postgresql://host:port/database
```

**Exemplo:**
- Railway: `postgresql://postgres:abc123@containers-us-west-1.railway.app:5432/railway`
- JDBC: `jdbc:postgresql://containers-us-west-1.railway.app:5432/railway`

### 5. Testar a Conexão

Execute a classe de teste:

```bash
mvn clean compile exec:java -Dexec.mainClass="TestBackend"
```

Se tudo estiver correto, você verá:

```
✓ EntityManagerFactory criado com sucesso!
✓ Conexão JPA estabelecida com sucesso!
✓ TODOS OS TESTES PASSARAM COM SUCESSO!
```

## 🔧 Solução de Problemas

### Erro: "Connection refused"

- Verifique se o host e porta estão corretos
- Certifique-se de que o serviço PostgreSQL está ativo no Railway

### Erro: "Authentication failed"

- Verifique usuário e senha
- Certifique-se de que não há espaços extras nas credenciais

### Erro: "SSL connection required"

Adicione `?sslmode=require` na URL:

```
jdbc:postgresql://host:port/database?sslmode=require
```

### Tabelas não são criadas

Verifique se `hibernate.hbm2ddl.auto` está configurado como `update` no `persistence.xml`

## 📊 Visualizar Dados no Railway

1. No painel do Railway, clique no serviço PostgreSQL
2. Vá na aba **"Data"**
3. Você pode executar queries SQL diretamente no navegador

Exemplo de query para ver alunos:

```sql
SELECT * FROM alunos;
```

## 💰 Plano Gratuito do Railway

- **$5 de crédito grátis por mês**
- Suficiente para desenvolvimento e testes
- Sem necessidade de cartão de crédito inicialmente
- Banco de dados fica ativo 24/7

## 🔒 Segurança

### ✅ Boas Práticas

1. **NUNCA** commite credenciais no Git
2. Use variáveis de ambiente
3. Adicione `application.properties` ao `.gitignore`
4. Rotacione senhas periodicamente

### Adicionar ao .gitignore

```gitignore
# Configurações locais
src/main/resources/application.properties
*.properties

# Credenciais
.env
```

## 📚 Próximos Passos

Agora que o backend está configurado:

1. ✅ Banco de dados configurado
2. ✅ Entidades criadas
3. ✅ DAOs implementados
4. 🔜 Criar controllers
5. 🔜 Desenvolver interface JavaFX
6. 🔜 Conectar frontend ao backend

## 🆘 Precisa de Ajuda?

- [Documentação do Railway](https://docs.railway.app/)
- [Documentação do Hibernate](https://hibernate.org/orm/documentation/)
- [Tutorial JPA](https://www.baeldung.com/jpa-hibernate-persistence-context)

---

**Dica**: Mantenha o Railway aberto em uma aba do navegador para monitorar o uso e logs do banco de dados!
