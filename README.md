# 🖥️ IT-Asset Manager
![Status](https://img.shields.io/badge/status-Em%20desenvolvimento-yellow)

> Sistema de Gestão de Equipamentos de TI  
> Desenvolvido como prática de **Spring Boot**, aplicando boas práticas, regras de negócio reais e arquitetura limpa.

O **IT-Asset Manager** permite o controle de equipamentos de TI, colaboradores e o registro de empréstimos, garantindo **integridade dos dados**, **consistência transacional** e **validações corporativas**.

---

## ✨ Funcionalidades

- 📦 Cadastro e gerenciamento de **equipamentos**
- 👥 Controle de **colaboradores**
- 🔄 Registro de **empréstimos e devoluções**
- 🔐 Validações de dados sensíveis (CPF, patrimônio)
- 🧾 Histórico completo de movimentações


## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot 4.0**
- **Spring Web**
- **Spring Data JPA**
- **Spring Validation**
- **Hibernate Validator**
- **H2 Database**
- **Lombok**
- **Maven**


## 🏛️ Arquitetura do Projeto

O projeto segue os princípios de **Clean Architecture**, com responsabilidades bem definidas:


### 📐 Camadas

- **Model (Entities)**  
  Mapeamento direto com o banco de dados via JPA.

- **Repository**  
  Interface de acesso a dados com Spring Data JPA.

- **DTOs (Records)**  
  Garantem segurança, validação e evitam a exposição das entidades.

- **Mappers (MapStruct)**

  Conversão entre DTOs e entidades de forma automática e segura.

- **Service**  
  Núcleo do sistema, responsável pelas regras de negócio e controle transacional.

- **Controller**  
  Exposição dos endpoints REST da aplicação.


## 📋 Regras de Negócio Implementadas

### 🔒 Integridade de Dados
- CPF de colaboradores é **único**
- Patrimônio de equipamentos é **único**

### ✅ Validações
- `@CPF` para validação de documentos
- `@FutureOrPresent` para datas de devolução
- Bean Validation em todos os DTOs

### 🔄 Fluxo de Empréstimo
- Um equipamento só pode ser emprestado se estiver com status **DISPONIVEL**
- Ao ser emprestado, o status muda automaticamente para **EMPRESTADO**
- Uso de `@Transactional` para garantir atomicidade da operação
  
🗑️ Exclusões

- Ao excluir um empréstimo, o equipamento associado retorna automaticamente para DISPONIVEL

- Exclusão de equipamentos respeita as regras de integridade do sistema


## 🚀 Endpoints Principais

### 👥 Colaboradores
| Método | Endpoint | Descrição                     |
|--------|---------|-------------------------------|
| POST   | `/colaboradores` | Cadastra novo colaborador     |
| GET    | `/colaboradores` | Lista colaboradores           |
| GET    | `/colaboradores/{id}` | Buscar colaborador específico |
| PUT    | `/colaboradores/{id}` | Atualizar colaborador         |
| DELETE | `/colaboradores/{id}` | Excluir colaborador           |

---

### 💻 Equipamentos
| Método | Endpoint                    | Descrição                       |
|--------|-----------------------------|---------------------------------|
| POST   | `/equipamentos`             | Cadastra novo equipamento       |
| GET    | `/equipamentos`             | Lista todo o inventário         |
| GET    | `/equipamentos/{id}`        | Buscar equipamento específico             |
| PUT    | `/equipamentos/{id}`        | Atualizar equipamento           |
| PATCH  | `/equipamentos/{id}/status` | Atualizar status do equipamento |
| DELETE | `/equipamentos/{id}`        | Excluir equipamento             |

---

### 🔄 Empréstimos
| Método | Endpoint | Descrição              |
|--------|---------|------------------------|
| POST   | `/emprestimos` | Realiza um empréstimo  |
| GET    | `/emprestimos` | Histórico de empréstimos |
| GET    | `/emprestimos/{id}` | Buscar empréstimo específico     |
| PUT    | `/emprestimos/{id}` | Atualizar empréstimo   |
| DELETE | `/emprestimos/{id}` | Excluir empréstimo     |


## 🛡️ Tratamento de Erros

A aplicação possui um **Global Exception Handler** (`@RestControllerAdvice`) que padroniza as respostas da API:

- **400 Bad Request** → Erros de validação
- **404 Not Found** → Recurso não encontrado
- **409 Conflict** → Violação de unicidade (CPF / Patrimônio)

---

👨‍💻 Autor

Rafael Luna de Souza
