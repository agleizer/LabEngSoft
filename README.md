<div align="center">
  <img src="src/main/resources/static/assets/img/logoCF.png" alt="Caminho Fácil Logo" height="150"/>
  <p></p>
  <img src="https://img.shields.io/badge/java-21-red" alt="Java 21" height="18"/>
  <img src="https://img.shields.io/badge/spring%20boot-3.3-green" alt="Spring Boot 3" height="18"/>
  <img src="https://img.shields.io/badge/postgresql-15-blue" alt="PostgreSQL 15" height="18"/>
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

## Objetivo do Sistema

Facilitar a locomoção segura e digna em ambientes urbanos, especialmente para:

- Pessoas com deficiência (PCD)
- Idosos
- Pais e responsáveis com carrinhos de bebê
- Pessoas com mobilidade reduzida em geral

O sistema permite:

- Avaliar calçadas (nota, fotos, comentários, itens de acessibilidade)
- Consultar avaliações existentes diretamente no mapa
- Gerar relatórios sobre condições e acessibilidade por região (ponto + raio) [func. ainda sem intergação com o banco de dados]

---

## Principais Funcionalidades

- **Consultar calçadas**

  - Visualização de calçadas avaliadas em mapa interativo
  - Exibição de nota média, fotos, comentários e indicadores de acessibilidade

- **Avaliar calçada**

  - Seleção de um ponto/calda no mapa
  - Registro de avaliação com:
    - Nota (1 a 5 estrelas)
    - Comentário opcional
    - Foto opcional
    - Itens de acessibilidade (cadeira de rodas, carrinho de bebê, piso tátil, largura adequada, etc.)

- **Gerar relatório** [funcionalidade incompleta, sem integração com o BD]
  - Seleção de uma área (ponto + raio)
  - Consolidação das calçadas avaliadas na região
  - Estatísticas de notas e problemas mais frequentes
  - Exportação em CSV

---

## Arquitetura do Sistema

O **Caminho Fácil** é uma aplicação **web full-stack** com a seguinte arquitetura:

### Backend – API REST

- **Framework:** Spring Boot 3.3
- **Linguagem:** Java 21
- **Padrão:** API REST
- **Camadas principais:**
  - `controller` – recursos REST expostos ao frontend
  - `service` – regras de negócio (por exemplo, cálculo de médias de avaliação)
  - `repository` – acesso a dados via Spring Data JPA
  - `domain/model` – entidades de domínio (Calçada, Avaliação, Usuário etc.)

### Frontend

- **Tecnologias:** HTML, CSS e JavaScript
- Interface web simples, responsiva, consumindo a API REST
- Telas principais:
  - Mapa inicial
  - Detalhes da calçada
  - Formulário de avaliação
  - Tela de geração de relatórios

### Banco de Dados

- **SGBD:** PostgreSQL 15
- Armazena:
  - Dados georreferenciados das calçadas
  - Avaliações
  - Comentários
  - Metadados necessários para relatórios

### Infraestrutura e DevOps

- **Containerização:** Docker + Docker Compose
- **Nuvem:** AWS (planejado para deploy futuro)
- **CI/CD:** esteira de integração e entrega contínua (detalhada na documentação do projeto)

---

## Guia de Instalação

### Pré-requisitos

- Docker instalado
- Docker Compose instalado
- Portas **8080** (backend) e **5432** (PostgreSQL) livres
- Acesso à internet (para baixar imagens Docker na primeira execução)

---

### Executar com Docker (modo recomendado)

```bash
git clone https://github.com/agleizer/LabEngSoft.git
cd LabEngSoft
docker compose up --build
```

### Após a inicialização:

- Interface web disponível em: http://localhost:8080/index.html

### Variáveis de Ambiente

Dependendo da configuração final do grupo, podem ser utilizadas variáveis de ambiente como:

```bash
# PostgreSQL
POSTGRES_DB=caminhofacil
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres

# Configurações do Spring Boot
SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/caminhofacil
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
SPRING_JPA_HIBERNATE_DDL_AUTO=create
SPRING_SQL_INIT_MODE=always
SPRING_DATASOURCE_INITIALIZATION_MODE=always
SPRING_JPA_SHOW_SQL=true
```

> Importante: Atualizar este bloco quando as variáveis forem definidas na versão final do deploy.

---

## Manual de Utilização

Esta seção descreve, em linguagem mais próxima do usuário final, como utilizar o sistema.

### Consultar Calçadas

- Acesse a página inicial do sistema (mapa).
- Navegue pelo mapa arrastando e aproximando/afastando o zoom.
- Clique em uma área de interesse ou utilize a barra de busca (quando disponível).
- O sistema exibirá:
  > - Nota média da calçada/região
  > - Fotos enviadas por outros usuários
  > - Comentários
  > - Indicadores de acessibilidade (por exemplo: adequado para cadeirantes, presença de piso tátil etc.)
- Nenhuma alteração é feita no sistema nesta funcionalidade; é apenas consulta.

### Avaliar Calçada

- Selecione uma calçada no mapa.
- Clique na opção “Avaliar calçada”.
- Preencha os campos do formulário:
  > - Nota (obrigatória, de 1 a 5 estrelas)
  > - Comentário (opcional)
  > - Upload de foto (opcional)
  > - Itens de acessibilidade (checkboxes, por exemplo: adequado para cadeira de rodas, piso tátil, carrinho de bebê, etc.)
  > - Clique em Enviar.
- O sistema valida os dados e registra a avaliação.
- A média da calçada é atualizada e a nova avaliação passa a aparecer na listagem.

### Gerar Relatório

- Acesse a funcionalidade “Gerar relatório”.
- Informe:
  > - Ponto de referência (endereço ou coordenadas)
  > - Raio de abrangência (por exemplo, 500m, 1km etc.)
  > - Confirme a geração.
- O sistema consolida as informações das calçadas da região e apresenta:
- Lista de calçadas avaliadas
- Nota média da área
- Principais problemas reportados
- Síntese de acessibilidade (por categoria)
- Opcionalmente, exporte o relatório em formato CSV para uso em planilhas ou sistemas externos.

---

## Documentação Completa

A documentação detalhada do projeto (capítulos 1 a 9: introdução, definição da demanda, requisitos, protótipos, modelagem, arquitetura, desenvolvimento, resultados e conclusão) está disponível em:

Wiki do Projeto[https://github.com/agleizer/LabEngSoft/wiki]

---

## Estrutura do Repositório

Exemplo de visão geral da estrutura do projeto:

```bash
LabEngSoft/
├── src/
│   ├── main/
│   │   ├── java/               # Código-fonte do backend (Spring Boot)
│   │   └── resources/
│   │       ├── static/         # Arquivos estáticos do frontend (HTML, CSS, JS)
│   │       └── application.properties (ou .yml)
│   └── test/                   # Testes automatizados
├── docker-compose.yml          # Orquestração de containers
├── Dockerfile (se aplicável)   # Imagem do backend
├── README.md                   # Este arquivo
└── docs/                       # Documentação adicional (opcional)
```

---

## Testes Automatizados

Conforme os requisitos da disciplina, o projeto deve possuir testes automatizados para parte relevante das funcionalidades.

```bash
./mvnw test
```

Para executar os testes (via Maven Wrapper):

---

## Integração Contínua e Entrega Contínua (CI/CD)

O projeto prevê uma esteira de CI/CD com:

- Build automático do backend
- Execução de testes
- Geração de imagem Docker
- Deploy em ambiente de teste (e futuramente produção em AWS)
- Detalhes técnicos da pipeline (arquivo de configuração, gatilhos, ambientes) estão descritos na documentação completa do projeto (Wiki/relatório).

---

## Próximos Passos e Trabalhos Futuros

Algumas melhorias planejadas:

- Implementação de algoritmo de rotas acessíveis (caminho “ideal” que evita calçadas em más condições)
- Geração de relatórios
- Gamificação (usuários ganham pontos/badges por contribuições)
- Sistema de notificações sobre mudanças em áreas próximas de interesse
- Autenticação e perfis de usuário (histórico de contribuições, preferências, etc.)
- Esses itens podem ser explorados como trabalhos futuros, tanto em aspectos técnicos (engenharia de software, arquitetura, desempenho) quanto em aspectos de extensão universitária.

---

## Licença

Este projeto é de uso acadêmico e está sob a licença **MIT**.

---

<div align="center">
  <sub>Projeto desenvolvido como parte da disciplina <b>Laboratório de Engenharia de Software</b> – Universidade Presbiteriana Mackenzie (2025)</sub>
</div>
