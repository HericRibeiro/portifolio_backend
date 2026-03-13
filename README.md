# 🚀 Portfolio Fullstack - Java + React

Este projeto é meu portfolio pessoal, desenvolvido como uma aplicação fullstack real, aplicando conceitos de arquitetura, segurança, monitoramento e deploy em produção.

Mais do que um site institucional, o objetivo foi simular um ambiente próximo ao de aplicações corporativas.

---

## 🧱 Arquitetura

A aplicação é dividida em:

- Backend (Java + Spring Boot)
- Frontend (React + Tailwind)
- Banco de dados PostgreSQL
- Deploy em produção
- Backend containerizado com Docker

---

## 🛠️ Stack Utilizada

### 🔹 Backend
- Java
- Spring Boot
- Spring Security (JWT)
- JPA / Hibernate
- PostgreSQL
- Docker
- Scheduler (@Scheduled)

### 🔹 Frontend
- React
- TailwindCSS
- Vite

### 🔹 Infraestrutura
- Docker
- Deploy na Render
- Banco PostgreSQL em produção

---

## 🔐 Segurança

- Autenticação baseada em JWT
- Controle de acesso por Roles
- Rotas administrativas protegidas
- Apenas usuários com role ADMIN acessam endpoints específicos

---

## 📊 Monitoramento

Implementei um sistema interno para:

- Registrar acessos ao portfolio
- Armazenar dados relevantes de visita
- Gerar relatório diário automatizado
- Enviar resumo por e-mail em horário programado

Isso permite acompanhar:

- Total de acessos por dia
- Dados agregados de visitas
- Controle básico de tráfego

---

## 📦 Docker

O backend está totalmente dockerizado.

### Build da imagem:

```bash
docker compose build
```

---

### Rodar o docker:

```bash
docker compose run
```

---

## Banco de Dados

PostgreSQL utilizado como banco principal.

### Configuração via variáveis de ambiente:

```
DB_HOST
DB_PORT
DB_NAME
DB_USER
DB_PASSWORD
```

---

### Variáveis de Ambiente

```
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
JWT_SECRET=
MAIL_USERNAME=
MAIL_PASSWORD=
```

---

## 📬 Relatório Automático

Utilização de scheduler para envio automático de resumo diário contendo:

Número total de acessos

Informações agregadas

Data do relatório

---

## 🌍 Deploy

Aplicação publicada em ambiente de produção utilizando Render.

---

## 🎯 Objetivo do Projeto

### Este projeto foi desenvolvido com foco em:

Aplicar conceitos reais de backend

Trabalhar segurança com Spring Security

Implementar controle de acesso

Simular monitoramento de aplicação

Publicar aplicação em produção

Trabalhar containerização com Docker

---

## 📌 Próximos Passos

Implementar logs estruturados

Adicionar documentação com Swagger

Melhorar observabilidade

Implementar testes automatizados

---

## 📎 Link do Projeto

https://heric-willian.onrender.com/

Desenvolvido por Heric Willian