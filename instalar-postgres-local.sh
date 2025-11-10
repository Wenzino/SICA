#!/bin/bash

echo "=========================================="
echo "  INSTALAR POSTGRESQL LOCAL (FEDORA)"
echo "=========================================="
echo ""

echo "Este script vai:"
echo "1. Instalar PostgreSQL"
echo "2. Inicializar o banco"
echo "3. Criar o banco 'sistema_academico'"
echo "4. Configurar senha do usuario postgres"
echo ""
read -p "Continuar? (s/n) " -n 1 -r
echo ""

if [[ ! $REPLY =~ ^[Ss]$ ]]; then
    echo "Cancelado."
    exit 1
fi

echo ""
echo "1. Instalando PostgreSQL..."
sudo dnf install -y postgresql-server postgresql-contrib

echo ""
echo "2. Inicializando banco de dados..."
sudo postgresql-setup --initdb

echo ""
echo "3. Iniciando servico PostgreSQL..."
sudo systemctl start postgresql
sudo systemctl enable postgresql

echo ""
echo "4. Criando banco de dados..."
sudo -u postgres psql -c "CREATE DATABASE sistema_academico;"

echo ""
echo "5. Configurando senha do usuario postgres..."
sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'postgres';"

echo ""
echo "6. Configurando autenticacao..."
sudo sed -i 's/ident/md5/g' /var/lib/pgsql/data/pg_hba.conf
sudo sed -i 's/peer/md5/g' /var/lib/pgsql/data/pg_hba.conf

echo ""
echo "7. Reiniciando PostgreSQL..."
sudo systemctl restart postgresql

echo ""
echo "=========================================="
echo "  INSTALACAO CONCLUIDA!"
echo "=========================================="
echo ""
echo "Agora voce pode testar:"
echo "  mvn compile exec:java -Dexec.mainClass=\"TestBackend\""
echo ""
echo "Credenciais:"
echo "  Host: localhost"
echo "  Porta: 5432"
echo "  Database: sistema_academico"
echo "  Usuario: postgres"
echo "  Senha: postgres"
