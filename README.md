
# Projeto de Gestão de Clientes

## Sumário
1. [Objetivo](#objetivo)
2. [Camadas da Aplicação](#camadas-da-aplicação)
3. [Tecnologias Utilizadas](#tecnologias-utilizadas)
4. [Padrões e Boas Práticas](#padrões-e-boas-práticas)
5. [Arquitetura e Fluxo](#arquitetura-e-fluxo)
6. [Qualidade de Código](#qualidade-de-código)
7. [Como Executar](#como-executar)
8. [Testes](#testes)

---

## Objetivo
O projeto de **Gestão de Clientes** tem como objetivo a implementação de um sistema para gerenciar clientes, onde é possível realizar operações de CRUD e registrar múltiplos endereços e telefones para cada cliente. Além disso, o sistema registra logs de consultas em um banco de dados NoSQL e implementa integração para busca de CEP.

Este projeto foi desenvolvido utilizando uma arquitetura organizada e aderente aos princípios do SOLID, com foco em boas práticas, separação de responsabilidades e reutilização de código.

---

## Camadas da Aplicação

### 1. **Controller**
As controllers são responsáveis por receber as requisições e retornar as respostas em formato DTO. Nenhuma entidade do sistema é exposta diretamente. Para transformar as entidades em DTOs, utilizamos o **MapStruct**, um framework que facilita o mapeamento de objetos.

### 2. **Facade**
A camada de **Facade** atua como intermediária entre as controllers e os services, orquestrando a lógica de negócio e delegando responsabilidades às diversas camadas de serviço envolvidas.

### 3. **Service**
Os services encapsulam a lógica de negócio. Cada service é responsável por um aspecto específico da aplicação, garantindo coesão e facilitando a manutenção.

### 4. **Repository**
A camada de repositório é responsável pela comunicação com o banco de dados relacional e NoSQL, implementando a persistência de dados. A consulta de logs é feita diretamente no MongoDB, enquanto os dados dos clientes são gerenciados em um banco relacional.

### 5. **Filter (Auditoria)**
Um filtro de auditoria registra automaticamente cada requisição e resposta, armazenando detalhes no MongoDB, incluindo método HTTP, endpoint acessado e status de resposta.

---

## Tecnologias Utilizadas
- **Java 21**: Linguagem principal.
- **Spring Boot**: Framework para simplificação do desenvolvimento de aplicações Java.
- **Spring Data JPA**: Integração com banco de dados relacional.
- **Spring Data MongoDB**: Persistência de dados NoSQL.
- **Docker**: Conteinerização dos serviços, incluindo o banco NoSQL (MongoDB).
- **MapStruct**: Facilita a conversão entre entidades e DTOs.
- **Lombok**: Simplifica a criação de construtores, getters e setters com anotações como `@Builder` e `@Data`.
- **SonarQube**: Ferramenta de análise estática para garantir a qualidade do código.

---

## Padrões e Boas Práticas

### 1. **Princípios SOLID**
- **S**: Princípio da Responsabilidade Única (Single Responsibility Principle) aplicado em cada camada, garantindo que cada classe tenha apenas uma razão para mudar.
- **O**: Princípio Aberto/Fechado (Open/Closed Principle) através do uso de interfaces e abstrações.
- **L**: Substituição de Liskov (Liskov Substitution Principle) garantindo que subclasses possam ser usadas no lugar de suas superclasses sem alterar o comportamento do programa.
- **I**: Princípio da Segregação de Interfaces (Interface Segregation Principle) separando interfaces para evitar implementações desnecessárias.
- **D**: Inversão de Dependência (Dependency Inversion Principle) ao injetar dependências através do Spring Framework.

### 2. **DTOs**
Os DTOs (Data Transfer Objects) são usados para encapsular os dados trafegados entre o cliente e o servidor, garantindo que as entidades não sejam expostas diretamente. A anotação `@Builder` é utilizada para facilitar a construção dos objetos.

---

## Arquitetura e Fluxo

A arquitetura do projeto segue o modelo **MVC (Model-View-Controller)**, onde:
- **Controller** lida com a camada de apresentação e faz chamadas para a **Facade**.
- **Facade** orquestra as operações de **Service**.
- **Service** acessa os **Repositories** e utiliza o **Mapper** para conversão de entidades.
- **Repository** é responsável pela manipulação de dados.

Diagrama simplificado da arquitetura:

```plaintext
+------------+       +---------+       +---------+       +------------+
| Controller | --->  | Facade  | --->  | Service | --->  | Repository |
+------------+       +---------+       +---------+       +------------+
```

---

## Qualidade de Código
Foi utilizado o **SonarQube** para garantir a qualidade do código, analisando métricas como complexidade e aderência a boas práticas.

---

## Como Executar
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/gestao-clientes.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd gestao-clientes
   ```
3. Execute o projeto com Docker:
   ```bash
   docker-compose up
   ```
4. Acesse a aplicação em: `http://localhost:8080`

---
