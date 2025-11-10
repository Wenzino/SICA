# Como Configurar o Railway - Passo a Passo

## Problema Atual

O erro `Connection to localhost:5432 refused` acontece porque você não configurou as credenciais do Railway.

## Solução Rápida

### 1. Obter Credenciais do Railway

1. Acesse [railway.app](https://railway.app)
2. Abra seu projeto PostgreSQL
3. Clique na aba **"Connect"** ou **"Variables"**
4. Copie estas informações:
   - **PGHOST** (exemplo: `containers-us-west-1.railway.app` ou similar)
   - **PGPORT** (geralmente `5432`)
   - **PGDATABASE** (geralmente `railway`)
   - **PGUSER** (geralmente `postgres`)
   - **PGPASSWORD** (uma senha longa gerada automaticamente)

**IMPORTANTE**: Use o **PGHOST público**, NÃO o `postgres.railway.internal`!

### 2. Criar Arquivo de Configuração

Crie o arquivo `src/main/resources/application.properties` com:

```properties
DB_URL=jdbc:postgresql://SEU_PGHOST:5432/railway
DB_USER=postgres
DB_PASSWORD=SUA_SENHA_AQUI
```

**Exemplo real:**
```properties
DB_URL=jdbc:postgresql://containers-us-west-1.railway.app:5432/railway
DB_USER=postgres
DB_PASSWORD=NOaiXIkcLMFJnxZFqHKWcAYJIFkVFrDD
```

### 3. Testar

```bash
mvn compile exec:java -Dexec.mainClass="TestBackend"
```

## Alternativa: Usar Variáveis de Ambiente

Se preferir não criar o arquivo (mais seguro):

```bash
export DB_URL="jdbc:postgresql://SEU_PGHOST:5432/railway"
export DB_USER="postgres"
export DB_PASSWORD="SUA_SENHA"

mvn compile exec:java -Dexec.mainClass="TestBackend"
```

## Como Saber se Funcionou?

Você verá:
```
EntityManagerFactory criado com sucesso!
Conexao JPA estabelecida com sucesso!
```

## Ainda com Erro?

### Se aparecer "Connection refused":
- Verifique se copiou o PGHOST correto (deve ser algo como `containers-us-west-XXX.railway.app`)
- NÃO use `postgres.railway.internal` (isso só funciona dentro do Railway)
- NÃO use `localhost` (você não tem PostgreSQL local)

### Se aparecer "Authentication failed":
- Verifique usuário e senha
- Copie novamente do Railway (sem espaços extras)

### Se aparecer "SSL connection required":
Mude a URL para:
```properties
DB_URL=jdbc:postgresql://SEU_PGHOST:5432/railway?sslmode=require
```

## Resumo Visual

```
Railway Dashboard
    ↓
PostgreSQL Service
    ↓
Tab "Connect" ou "Variables"
    ↓
Copiar PGHOST (público!)
    ↓
Criar application.properties
    ↓
Testar!
```

---

**Precisa de ajuda? Me mostre o PGHOST que você copiou do Railway (sem a senha)!**
