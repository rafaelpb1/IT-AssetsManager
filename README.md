IT-Asset Manager
Sistema de gestão de ativos e inventário de TI desenvolvido como parte de uma Masterclass em Spring Boot. O sistema permite o controle de equipamentos, colaboradores e o registro de empréstimos, garantindo a integridade dos dados e regras de negócio corporativas.

🛠️ Tecnologias Utilizadas
Java 17+ (com foco em Records e Modern Java)

Spring Boot 3.x

Spring Data JPA (Persistência)

Spring Validation (Bean Validation)

Spring Web (REST API)

Banco de Dados: H2 Database (em memória para desenvolvimento)

Lombok: Redução de boilerplate code

Maven: Gerenciamento de dependências

🏛️ Arquitetura do Projeto
O projeto segue os princípios de Clean Architecture e camadas bem definidas:

Model (Entities): Mapeamento direto com o banco de dados (JPA).

Repository: Interface de comunicação com o banco via Spring Data.

DTOs (Records): Camada de transferência de dados para segurança e validação, evitando a exposição das entidades.

Service: O "coração" do sistema, onde residem as regras de negócio e o controle transacional.

Controller: Porta de entrada da API, gerenciando os endpoints REST.

📋 Regras de Negócio Implementadas
Integridade de Dados: CPF de colaboradores e Patrimônio de equipamentos são únicos.

Validação de Entrada: Uso de @CPF do Hibernate Validator e @FutureOrPresent para datas de devolução.

Fluxo de Empréstimo:

Um equipamento só pode ser emprestado se seu status for DISPONIVEL.

Ao realizar um empréstimo, o status do equipamento muda automaticamente para EMPRESTADO.

Uso de @Transactional para garantir que o empréstimo e a atualização do status ocorram de forma atômica.

Soft Delete: Implementação de desativação lógica de colaboradores (coluna ativo), preservando o histórico para auditoria.

🚀 Endpoints Principais
Colaboradores
POST /colaboradores: Cadastra um novo colaborador (valida CPF).

GET /colaboradores: Lista colaboradores ativos.

DELETE /colaboradores/{id}: Desativação lógica do colaborador.

Equipamentos
POST /equipamentos: Cadastra novo hardware (Notebook, Totem, etc).

GET /equipamentos: Lista o inventário completo.

Empréstimos
POST /emprestimos: Realiza um novo empréstimo (valida disponibilidade e IDs).

GET /emprestimos: Histórico de movimentações.

🛡️ Tratamento de Erros
O projeto conta com um Global Exception Handler (@RestControllerAdvice) que padroniza as respostas de erro da API, tratando:

400 Bad Request: Erros de validação de campos.

404 Not Found: Recursos não encontrados.

409 Conflict: Violação de unicidade (CPF/Patrimônio).
