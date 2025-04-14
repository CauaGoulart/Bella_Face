# BellaFace - Sistema de Pedidos de Cosméticos

Sistema web desenvolvido para a BellaFace, uma empresa especializada na distribuição e venda de cosméticos.  
Este sistema permite que **clientes jurídicos** visualizem produtos, adicionem ao carrinho, deixem comentários e finalizem pedidos — exceto em finais de semana.

---

## Funcionalidades

- Login com autenticação para clientes
- Visualização e adição de produtos ao carrinho
- Comentários no pedido antes de finalizar
- Restrição de pedidos aos dias úteis (segunda à sexta)
- Visualização e exclusão de itens no carrinho
- Feedback visual de ações (mensagens por 3 segundos)
- Interface responsiva com Bootstrap 5

---

## Como executar o projeto

### Pré-requisitos

- Java 17+
- Maven 3.8+
- PostgreSQL ou H2 (para testes)
- IDE de sua preferência (IntelliJ, VSCode, Eclipse)

---

### 1. Clone o repositório

```bash
git clone https://github.com/CauaGoulart/Bella_Face.git
cd Bella_Face

### 2. Configure o banco de dados
Baixe o arquivo bancoDados.sql incluído no repositório

Crie um banco no PostgreSQL chamado bellaface_db

### 3. Execute o projeto
Abra o projeto na sua IDE

Rode a classe BellaFaceApplication.java

Login	Nome	Senha
saojoao	Farmácia São João	senha123
pacheco	Drogaria Pacheco	abc123
panvel	Panvel Ltda	panvelpass
drogasil	Drogasil S/A	drog@2025
