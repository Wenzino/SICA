#!/bin/bash

echo "=========================================="
echo "  TESTE DE CONEXAO - RAILWAY POSTGRESQL"
echo "=========================================="
echo ""

# Verificar se as variáveis estão configuradas
if [ -z "$DB_URL" ]; then
    echo "DB_URL não está configurada!"
    echo ""
    echo "Configure assim:"
    echo "  export DB_URL='jdbc:postgresql://SEU_HOST:5432/railway'"
    echo "  export DB_USER='postgres'"
    echo "  export DB_PASSWORD='SUA_SENHA'"
    echo ""
    echo "Ou crie o arquivo: src/main/resources/application.properties"
    exit 1
fi

echo "Variáveis de ambiente configuradas:"
echo "  DB_URL: $DB_URL"
echo "  DB_USER: $DB_USER"
echo "  DB_PASSWORD: ****"
echo ""

echo "Compilando e testando..."
mvn compile exec:java -Dexec.mainClass="TestBackend"
