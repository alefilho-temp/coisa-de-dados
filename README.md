# README - Marketplace JavaFX

Este projeto implementa um aplicativo de marketplace simples utilizando JavaFX e um banco de dados SQL Server. O aplicativo permite a visualização de produtos, busca por produtos, categorias e vendedores, gerenciamento de produtos (para administradores) e um carrinho de compras.

## Tecnologias Utilizadas

* **Java:** Linguagem de programação principal.
* **JavaFX:** Framework para criação da interface gráfica do usuário (GUI).
* **SQL Server:** Sistema de gerenciamento de banco de dados relacional (RDBMS) para armazenamento de dados.
* **JDBC:** API para conectar o Java ao banco de dados SQL Server.
* **Maven (implícito):** Provavelmente utilizado para gerenciamento de dependências (embora não explicitamente mencionado no código fornecido).

## Estrutura do Projeto

O projeto provavelmente segue uma estrutura MVC (Model-View-Controller).  A estrutura é organizada da seguinte forma:

* **`src`:** Pasta principal contendo o código-fonte.
    * **`models`:** Classes que representam os dados do domínio (ex: `ProductModel`, `CategoryModel`, `SellerModel`, `CartItemModel`).
    * **`daos`:** Classes de acesso a dados (Data Access Objects), responsáveis pela interação com o banco de dados (ex: `ProductDAO`, `ProductDAOImpl`).
    * **`controllers`:** Classes que controlam a lógica da aplicação e a interação entre os modelos e as views (ex: `ProductControl`).
    * **`common`:** Classes utilitárias e classes que gerenciam a navegação entre as views (ex: `DBConnection`, `Utils`, `ViewController`).
    * **`components`:** Classes que representam componentes reutilizáveis da interface do usuário (ex: `NavComponent`, `ProductComponent`, `ContainerComponent`).
    * **`views`:** Classes que representam as diferentes telas da aplicação (ex: `HomeView`, `ProductView`, `SearchView`, `CartView`, `ProductManagementView`).
* **`lib`:** Pasta contendo as dependências externas, incluindo drivers JDBC para o SQL Server e outras bibliotecas.
* **`assets`:** Pasta contendo imagens da interface do usuário (UI).
* **`products`:** Pasta contendo imagens dos produtos.


## Funcionalidades

* **Página Inicial (`HomeView`):** Mostra um banner, categorias de produtos e uma lista de produtos em destaque.
* **Visualização de Produto (`ProductView`):** Exibe detalhes completos de um produto, incluindo imagem, descrição, preço, informações do vendedor e produtos relacionados.
* **Busca (`SearchView`):** Permite pesquisar produtos por nome, categoria ou vendedor.
* **Carrinho de Compras (`CartView`):** Exibe os itens adicionados ao carrinho, o preço total e permite a finalização da compra (a funcionalidade de finalização ainda não está implementada).
* **Gerenciamento de Produtos (`ProductManagementView`):** Permite adicionar, editar, atualizar e deletar produtos. Esta funcionalidade provavelmente é restrita a usuários com privilégios de administrador.

## Pré-requisitos

* **Java Development Kit (JDK):** Versão compatível com JavaFX.  (Verifique a versão necessária no arquivo `pom.xml` se existir)
* **SQL Server:** Instalação e configuração do banco de dados SQL Server com o banco de dados `Marketplace` criado.  Consulte o arquivo `db.txt` para o script de criação do banco de dados. As credenciais de acesso ao banco de dados estão codificadas em `DBConnection.java` (considere usar uma solução mais segura para gerenciar credenciais em produção, como variáveis de ambiente).
* **Driver JDBC para SQL Server:**  Disponível na pasta `lib`.  Certifique-se de que o driver esteja configurado corretamente no classpath.


## Como Executar

1. **Configuração do Banco de Dados:** Execute o script `db.txt` para criar o banco de dados e as tabelas no SQL Server.  Adapte as credenciais no script se necessário.
2. **Configuração do Projeto (se necessário):**  Se o projeto usa Maven, execute `mvn install` para baixar as dependências. Caso contrário, certifique-se de que todas as bibliotecas (incluindo o driver JDBC) na pasta `lib` estejam no classpath.
3. **Compilação e Execução:** Compile e execute o arquivo `App.java`.  Você pode precisar usar um IDE como o Eclipse ou IntelliJ IDEA para compilar e executar o projeto.


## Considerações

* **Segurança:** As credenciais do banco de dados estão embutidas no código. Em um ambiente de produção, é crucial usar uma solução mais segura para gerenciar essas informações (ex: variáveis de ambiente, arquivos de configuração externos).
* **Tratamento de Erros:** O tratamento de erros poderia ser aprimorado em várias partes do código.
* **Escalabilidade:** Este é um aplicativo simples e não está otimizado para escalabilidade. Para um sistema em produção, considere arquiteturas mais robustas.
* **Testes:** A inclusão de testes unitários e de integração melhoraria significativamente a qualidade do código.

Este README fornece uma visão geral do projeto. Para obter detalhes mais específicos, examine o código-fonte.
