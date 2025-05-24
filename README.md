# 🚀 Projeto Zeus: Sistema de gestão da Comp Júnior


## ✨ Funcionalidades Principais

### 🔒 Autenticação e Segurança

**Login de Usuários:** Sistema de login seguro para acesso às funcionalidades administrativas.
**Cadastro de Usuários:** Permite o registro de novos usuários com validação.
**Recuperação de Senha:** Funcionalidade para redefinir a senha via e-mail.
**Gerenciamento de Sessão:** Utiliza cookies para manter a sessão do usuário.

### 👱Gerenciamento de Membros

**Cadastro Completo:** Registre novos membros com informações detalhadas, incluindo nome, data de nascimento, e-mail institucional, cargo, telefone, gênero e data de ingresso.
**Validação de Dados:** Assegura a integridade dos dados com validações robustas em campos como e-mail (com padrão @compjunior.com), datas e campos obrigatórios.
**Visão geral:** Visualize todos os membros cadastrados em uma tabela organizada.
**Edição e Exclusão:** Altere informações de membros existentes ou exclua-os do sistema.

### 📈 Gerenciamento de Orçamentos de Projetos

**Registro de Orçamentos:** Cadastre orçamentos de projetos com detalhes como status, responsável associado, data de criação, valor previsto.
**Listagem e Detalhes:** Visualize a lista de todos os orçamentos e suas informações relevantes.
**Edição e Exclusão:** Altere orçamentos existentes ou os exclua conforme a necessidade.


## 🛠️ Tecnologias Utilizadas

### Back-end:
- Spring Boot: Framework robusto para o desenvolvimento rápido de aplicações Java.
- Spring Data JPA: Simplifica a interação com o banco de dados.
- Thymeleaf: Motor de template para renderização das páginas HTML.
- Jakarta Bean Validation: Para validação de dados de entrada.
### Front-end:
- HTML5 & CSS3: Estrutura e estilização das páginas.
- Bootstrap 4: Framework CSS para um design responsivo e moderno.

### Banco de dados:
- MySQL: Escolhi este banco de dados relacional por sua facilidade de configuração e por ser gratuito. Além disso, o framework Spring Data JPA possui métodos que simplificam a sincronização da aplicação com o banco.


## 🚀 Como Rodar o Projeto

### Pré-requisitos:
- Java Development Kit (JDK) 17 ou superior
- Maven

1. Clonar o repositório
```
git clone https://github.com/italo-rabelo/SistemaZeus.git
cd SistemaZeus
```
2. Configuração do ```application.properties```
```
spring.application.name=zeus

# Configurações do MySQL (para uso com Docker Compose)
spring.datasource.url=jdbc:mysql://db:3306/seu_banco_de_dados?useSSL=false&serverTimezone=UTC
spring.datasource.username=seu_usuario_mysql
spring.datasource.password=sua_senha_mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update # Use 'update' para desenvolvimento. Para produção, 'none' ou 'validate' com Flyway/Liquibase.
spring.jpa.show-sql=true

# Configurações de E-mail (opcional: usar variáveis de ambiente para credenciais sensíveis)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```
**Atenção**: Lembre-se de substituir seu_banco_de_dados, seu_usuario_mysql, sua_senha_mysql e as credenciais de e-mail pelos seus valores reais.

3. Rode o projeto
Navegue até a pasta raíz do projeto (src/) e no terminal, execute o comando ```spring-boot:run```

4. Acesse no navegador ```http://localhost:8080/login```

## 🔗 Testando os Endpoints

Você pode testar os endpoints utilizando ferramentas como **Postman**, **Insomnia** ou diretamente pelo navegador (para métodos GET).

---

## 🔑 Endpoints de Login e Autenticação

| Método | Endpoint                | Descrição                              |
|--------|--------------------------|-----------------------------------------|
| GET    | `/login`                 | Tela de login                          |
| POST   | `/logar`                 | Realiza login                          |
| GET    | `/`                      | Página inicial (Dashboard, após login) |
| GET    | `/sair`                  | Faz logout                             |
| GET    | `/cadastroUsuario`       | Tela de cadastro de novo usuário       |
| POST   | `/cadastroUsuario`       | Cadastra um novo usuário               |
| GET    | `/recuperarSenha`        | Tela para solicitar recuperação de senha|
| POST   | `/enviarCodigo`          | Envia código de verificação por e-mail |
| GET    | `/verificarCodigo`       | Tela para verificar código enviado     |
| POST   | `/verificarCodigo`       | Valida o código digitado               |
| POST   | `/novaSenha`             | Define uma nova senha                  |

---

### 🧪 Como testar os endpoints de login:

- **Login**
  - Acesse: `http://localhost:8080/login`
  - Preencha o formulário com e-mail e senha válidos.
  - Clique em "Entrar".

- **Cadastro de novo usuário**
  - Acesse: `http://localhost:8080/cadastroUsuario`
  - Preencha os campos: nome, email e senha.
  - Clique em "Cadastrar".

- **Logout**
  - Acesse: `http://localhost:8080/sair`
  - Você será redirecionado para a tela de login.

---

### 🔒 Recuperação de senha - Fluxo completo

1. **Solicitar recuperação**
   - Acesse: `http://localhost:8080/recuperarSenha`
   - Informe seu e-mail cadastrado e clique em "Enviar Código".

2. **Verificar código**
   - Acesse o e-mail, pegue o código enviado.
   - Digite o código na tela ou acesse diretamente:  
   `http://localhost:8080/verificarCodigo?email=SEU_EMAIL&codigo=SEU_CODIGO`

3. **Definir nova senha**
   - Após a validação do código, você será direcionado para a tela de nova senha.
   - Informe a nova senha e confirme.

---

## 🗂️ Observações importantes

- É necessário ter usuários cadastrados no sistema para testar o login.
- O código de recuperação de senha é enviado via serviço de e-mail simulado na aplicação (`EmailService`).
- As sessões de autenticação são controladas via cookies (`CookieService`).

### 🧑‍💼 Endpoints de Membros (`/membros`)

| Método | Endpoint                     | Descrição                              |
|--------|-------------------------------|-----------------------------------------|
| GET    | `/membros`                    | Lista todos os membros                  |
| GET    | `/membros/novo`               | Abre o formulário de cadastro           |
| POST   | `/membros/salvar`             | Salva um novo membro                    |
| GET    | `/membros/editar/{id}`        | Abre o formulário para editar um membro |
| GET    | `/membros/excluir/{id}`       | Exclui um membro pelo ID                |

**🧪 Como testar:**

- **Listar membros**
  - Acesse: `http://localhost:8080/membros`

- **Cadastrar membro**
  - Acesse: `http://localhost:8080/membros/novo` (formulário)
  - Ou envie um POST para `/membros/salvar` com os campos:
    - `nomeCompleto`
    - `dataNascimento`
    - `email`
    - `cargo`
    - `telefone`
    - `genero`
    - `dataIngresso`
    - `habilidades`

- **Editar membro**
  - Acesse: `http://localhost:8080/membros/editar/{id}`

- **Excluir membro**
  - Acesse: `http://localhost:8080/membros/excluir/{id}`

---

### 📑 Endpoints de Orçamentos (`/orcamentos`)

| Método | Endpoint                         | Descrição                                |
|--------|-----------------------------------|--------------------------------------------|
| GET    | `/orcamentos`                    | Lista todos os orçamentos                 |
| GET    | `/orcamentos/novo`               | Abre o formulário de cadastro             |
| POST   | `/orcamentos/salvar`             | Salva um novo orçamento                   |
| GET    | `/orcamentos/editar/{id}`        | Abre o formulário para editar um orçamento|
| GET    | `/orcamentos/excluir/{id}`       | Exclui um orçamento pelo ID               |

**🧪 Como testar:**

- **Listar orçamentos**
  - Acesse: `http://localhost:8080/orcamentos`

- **Cadastrar orçamento**
  - Acesse: `http://localhost:8080/orcamentos/novo` (formulário)
  - Ou envie um POST para `/orcamentos/salvar` com os campos:
    - `numeroOrcamento`
    - `descricao`
    - `cliente`
    - `responsavel`
    - `valorEstimado`
    - `custoPrevisto`
    - `status` (EM_ANALISE, APROVADO, REPROVADO)

- **Editar orçamento**
  - Acesse: `http://localhost:8080/orcamentos/editar/{id}`

- **Excluir orçamento**
  - Acesse: `http://localhost:8080/orcamentos/excluir/{id}`

---

## 📦 Observações
- O projeto roda localmente no endereço: `http://localhost:8080`.

### 🧑‍💼 Endpoints de Membros (`/membros`)

| Método | Endpoint                     | Descrição                              |
|--------|-------------------------------|-----------------------------------------|
| GET    | `/membros`                    | Lista todos os membros                  |
| GET    | `/membros/novo`               | Abre o formulário de cadastro           |
| POST   | `/membros/salvar`             | Salva um novo membro                    |
| GET    | `/membros/editar/{id}`        | Abre o formulário para editar um membro |
| GET    | `/membros/excluir/{id}`       | Exclui um membro pelo ID                |

**🧪 Como testar:**

- **Listar membros**
  - Acesse: `http://localhost:8080/membros`

- **Cadastrar membro**
  - Acesse: `http://localhost:8080/membros/novo` (formulário no navegador)
  - Ou envie um **POST** para `/membros/salvar` com os seguintes campos no **Body (x-www-form-urlencoded ou form-data)**:
    - `nomeCompleto`
    - `dataNascimento` (formato: `AAAA-MM-DD`)
    - `email`
    - `cargo`
    - `telefone`
    - `genero`
    - `dataIngresso` (formato: `AAAA-MM-DD`)
    - `habilidades`

- **Editar membro**
  - Acesse: `http://localhost:8080/membros/editar/{id}`
  - Substitua `{id}` pelo ID do membro que deseja editar.

- **Excluir membro**
  - Acesse: `http://localhost:8080/membros/excluir/{id}`
  - Substitua `{id}` pelo ID do membro que deseja excluir.

---


## 📝 Licença
Esté projeto é de uso acadêmico e é livre para estudo.
