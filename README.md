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

## 📝 Licença
Esté projeto é de uso acadêmico e é livre para estudo.
