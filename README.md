### Objetivo

Criação de um sistema de escalação de times.

### Overview
O backend do sistema foi desenvolvido utilizando Java, com um padrão MVC e inspiração no modelo de arquitetura
DDD (Domain Driven Design), além do banco de dados em PostgreSQL.

Para o front-end foi utilizado React.js, TypeScript e Vite para criar a aplicação. O programa possui duas telas,
sendo uma para cadastro de Integrantes e outra para a escalação de times. Integrado com a API, é possível,
de forma simples, executar a persistência dos dados na base.

<div align="center">
 <img src="/readme-extras/tela-integrante.png" height="350" />
 <br/>
 <img src="/readme-extras/tela-composicao.png" height="350" />
</div>

### Utilização
A criação da base de dados pode ser feita manualmente, ou se preferível, através da pasta [base-de-dados](https://github.com/rafaeltxc/duxus-challenge/tree/main/base-de-dados),
onde o arquivo [comando-start-container.txt](https://github.com/rafaeltxc/duxus-challenge/blob/main/comando-start-container.txt)
contém o comando para a criação de um container PostgreSQL,
utilizando Docker e já com as configurações necessárias.

Se desejar usar outras configuraçoes para a base de dados, altere o [application.properties](https://github.com/rafaeltxc/duxus-challenge/blob/main/src/main/resources/application.properties)
de acordo com a sua preferência.

Caso escolha por iniciar a aplicação manualmente, são necessários dois passos. Primeiro, inicialize o Back-End, o qual pode ser feito
normalmente da mesma forma padrão que o SpringBoot utiliza, através da IDE ou da linha de comando.
Com o servidor Back-End ativo, acesse a pasta [front-end](https://github.com/rafaeltxc/duxus-challenge/tree/main/front-end)
e execute o comando yarn ou yarn install para instalar os pacotes necessários.
Após a instalação dos pacotes, o comando yarn dev irá inicializar o servidor, o qual estará disponível na URL:

 - http://localhost:5173/

O Hibernate irá executar o código SQL automaticamente para a criação de tabelas na base de dados.
Caso deseje fazer essa operação manualmente, o arquivo esquema-base.sql possui o SQL para executar a criação de
tabelas no banco PostgreSQL.

Após finalizados os passos, a aplicação já estara ativa para receber requisições. O arquivo Após finalizados os passos,
a aplicação já estara ativa para receber requisições.
O arquivo [mockup-base.json](https://github.com/rafaeltxc/duxus-challenge/blob/main/base-de-dados/mockup-base.json)
possui alguns objetos já definidos, que podem ser utilizados para popular a base de dados após a sua inicialização.

### Documentação
Pode ser acessada através da interface pelo Front-End, ou diretamente pela seguinte URL:
 - http://localhost:8080/doc
