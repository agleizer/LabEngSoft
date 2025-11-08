<div align="center">
  <img src="src/main/resources/static/assets/img/logoCF.png" alt="Caminho Fácil Logo" height="150"/>
  <p></p>
  <img src="https://img.shields.io/badge/python-3.10-blue" alt="Python 3.10" height="18"/>
  <img src="https://img.shields.io/badge/docker-compose%20ready-brightgreen" alt="Docker Compose Ready" height="18"/>
  <img src="https://img.shields.io/badge/student%20project-yes-lightgrey" alt="Student Project" height="18"/>
  <img src="https://img.shields.io/badge/status-work%20in%20progress-yellow" alt="Status WIP" height="18"/>
  <img src="https://img.shields.io/badge/license-MIT-blueviolet" alt="MIT License" height="18"/>
</div>

---

# Caminho Fácil

**Caminho Fácil** é um aplicativo web colaborativo que permite que cidadãos registrem e consultem informações sobre **calçadas** em um **mapa interativo**.  
O objetivo é promover **mobilidade urbana inclusiva**, fornecendo dados que auxiliem **prefeituras, associações e cidadãos** a identificar problemas e melhorar a acessibilidade nas cidades brasileiras.

O projeto foi desenvolvido no contexto da disciplina **Laboratório de Engenharia de Software** da **Universidade Presbiteriana Mackenzie**, atendendo ao **ODS 11 - Cidades e Comunidades Sustentáveis (ONU)**.

---

## Objetivo

Facilitar a locomoção segura e digna em áreas urbanas, especialmente para:

- Pessoas com deficiência (PCD)
- Idosos
- Pais com carrinhos de bebê
- Cidadãos com mobilidade reduzida

O sistema permite:

- Avaliar calçadas (nota, fotos, comentários, itens de acessibilidade)
- Consultar avaliações de outras pessoas diretamente no mapa
- Gerar relatórios sobre condições e acessibilidade por região

---

## Arquitetura do Sistema

O **Caminho Fácil** é uma aplicação **web full-stack** composta por:

- **Backend:** API REST em Python (Flask)
- **Frontend:** Interface web estática (HTML, CSS, JS)
- **Banco de Dados:** PostgreSQL
- **Containerização:** Docker + Docker Compose
- **CI/CD:** Pipeline de integração e entrega contínua
- **Hospedagem:** AWS (para deploy futuro)

Arquitetura modular com separação entre camadas de apresentação, aplicação e persistência, conforme boas práticas de Engenharia de Software.

---

## Instalação e Execução

### Pré-requisitos

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

### Passos

Clone o repositório e execute o ambiente com Docker Compose:

```bash
git clone https://github.com/agleizer/LabEngSoft.git
cd LabEngSoft
docker compose up --build
```

### Acesso

Após a inicialização, o site estará disponível em:

> 🌐 **http://localhost/**

## 🧩 Funcionalidades Principais

| Tipo          | Descrição                                                      |
| ------------- | -------------------------------------------------------------- |
| **Consulta**  | Exibe mapa interativo com notas e fotos de calçadas.           |
| **Avaliação** | Permite registrar avaliações, fotos e itens de acessibilidade. |

## Principais casos de uso: **Consultar calçadas**, **Avaliar calçadas**, **Gerar relatórios**.

## Documentação Completa

A documentação detalhada com requisitos, protótipos, modelagem, arquitetura e resultados está disponível na **Wiki** do projeto:

> **[Acessar Wiki do Projeto](https://github.com/agleizer/LabEngSoft/wiki)**

---

## Próximos Passos

- Implementação de **algoritmo de rotas acessíveis** (caminho ideal evitando calçadas em más condições)
- Geração automática de **relatórios para prefeituras**
- **Gamificação** (usuários ganham pontos por contribuições)
- **Autenticação e perfis de usuário**
- **Notificações** sobre mudanças próximas

---

## Licença

Este projeto é de uso acadêmico e está sob a licença **MIT**.

---

<div align="center">
  <sub>Projeto desenvolvido como parte da disciplina <b>Laboratório de Engenharia de Software</b> – Universidade Presbiteriana Mackenzie (2025)</sub>
</div>
