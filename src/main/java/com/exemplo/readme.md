# Guia de Manutenção e Evolução da Aplicação

Este documento contém regras e diretrizes para manutenção e evolução da aplicação, garantindo que o comportamento atual seja preservado e que novos desenvolvedores possam trabalhar no projeto sem introduzir problemas.

## Estrutura Geral da Aplicação

- **Framework**: A aplicação utiliza o framework Vaadin com Spring Boot.
- **Layout Principal**: O layout principal é implementado na classe `MainLayout` (pacote `com.exemplo`), que utiliza o componente `AppLayout` do Vaadin para criar um layout com um menu lateral (drawer) à esquerda e uma área principal de conteúdo à direita.

## Versões Utilizadas

- **Java**: Versão 8 (1.8)
  - A aplicação utiliza Java 8, configurado no `pom.xml` com `<maven.compiler.source>1.8</maven.compiler.source>` e `<maven.compiler.target>1.8</maven.compiler.target>`. Certifique-se de que o ambiente de desenvolvimento e produção esteja configurado para usar o Java 8.
- **Banco de Dados**: Sybase ASA 9.0.2.3951
  - A aplicação utiliza o Sybase ASA (Adaptive Server Anywhere) na versão 9.0.2.3951 como banco de dados, com o driver JDBC `com.sybase:jconnect:6.05` especificado no `pom.xml`. Certifique-se de que o banco de dados está configurado corretamente e que o driver JDBC está disponível.
- **Vaadin**: Versão 23.2.9
  - A aplicação utiliza o Vaadin 23.2.9, especificado no `pom.xml` com `<vaadin.version>23.2.9</vaadin.version>`. Certifique-se de que a dependência do Vaadin está configurada corretamente no arquivo `pom.xml`.
- **Spring Boot**: Versão 2.7.18
  - A aplicação utiliza o Spring Boot 2.7.18, especificado no `pom.xml` com `<spring-boot.version>2.7.18</spring-boot.version>`. Certifique-se de que as dependências do Spring Boot estão configuradas corretamente.

## Uso do Menu do Vaadin (AppLayout)

- O `AppLayout` é usado para criar o layout com um drawer (menu lateral) e uma área principal.
- **Drawer**: Contém apenas as abas de navegação (`menuTabs`) com as opções "Clientes", "Relatório DRE", "Relatório de Vendas", e "Listagem de Pedidos".
- **Área Principal**: O conteúdo das views (`ClienteView`, `DREView`, `VendasView`, `ListagemPedidoView`) é exibido na área principal (`contentArea`).

## Navegação Manual

- A navegação é gerenciada manualmente pelo `MainLayout`, sem usar rotas do Vaadin (`@Route`).
- O conteúdo é atualizado no `contentArea` com base na aba selecionada no drawer (`menuTabs.addSelectedChangeListener`) ou no `MainMenuModal` através do método `selectTab`.

## Restrições sobre `@Route`

- **Importante**: As views (`ClienteView`, `DREView`, `VendasView`, `ListagemPedidoView`, etc.) **NÃO DEVEM** ter anotações `@Route`.
- Se uma view tiver `@Route`, o Vaadin tentará navegar automaticamente para ela, exibindo o conteúdo dentro do drawer (lateral esquerda), o que quebrará o layout. O conteúdo deve ser exibido na área principal (`contentArea`) à direita.

## Adição de Novas Abas no Menu Lateral (Drawer)

Para adicionar uma nova aba ao menu lateral:
1. Crie uma nova `Tab` no `MainLayout`:
   ```java
   Tab novaTab = new Tab("Nova Aba");