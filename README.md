# 💰 Sistema de Gestão de Pagamentos

Sistema completo desenvolvido em Spring Boot para gestão de empresas, funcionários e pagamentos, com interface web moderna e validações de negócio.

## 🚀 Funcionalidades

### 📊 Módulo Empresas
- **Cadastro completo** de empresas com dados corporativos
- **Validação de CNPJ** único e formato válido
- **Gestão de dados bancários** (conta, agência, chave PIX)
- **Edição e exclusão** com validação de vínculos
- **Exclusão em cascata** de empresas com pagamentos vinculados

### 💸 Módulo Pagamentos  
- **Registro de pagamentos** com dados completos
- **Vinculação automática** com empresas existentes
- **Validação de valores** (positivos, não-zero)
- **Gestão de boletos** com código de barras
- **Filtros e consultas** por empresa e período

### 👥 Módulo Funcionários
- **Cadastro de funcionários** com dados de contato
- **Vinculação a departamentos**
- **Associação a pagamentos** como responsáveis

## 🛠️ Tecnologias Utilizadas

- **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA, Spring MVC
- **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript
- **Banco de Dados:** MySQL
- **Build Tool:** Maven
- **Validações:** Bean Validation, Custom Validators
- **Segurança:** Spring Security (se aplicável)

## 📋 Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL 8.0+
- Git

## 🎯 Instalação e Execução

### 1. Clone o repositório
```bash
git clone https://github.com/alanmsilva94/sistema-pagamentos-spring.git
cd sistema-pagamentos-spring
