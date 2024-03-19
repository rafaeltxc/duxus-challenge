### Objetivo

Manusear a composição de novos times e seus repectivos integrantes, com suas funções e de quais franquias fazem parte.

### Overview
O sistema possui um padrão MVC e com inspiração no modelo de arquitetura DDD (Domain Driven Design), com uso do banco
PostgreSQL.

O Front-End foi desenvolvido com React e TypeScript, utilizando Vite para criar a aplicação. O programa possui
duas telas, uma para a inserção de **Integrantes**, e uma para a criação de novas **Composições** e seus respectivos
**Times**. Integrado com a API, é possível, de forma simples, executar a persistência dos dados na base.
<div align="center">
 <img src="/readme-extras/tela-integrante.png" height="350" />
 <br/>
 <img src="/readme-extras/tela-composicao.png" height="350" />
</div>

### Utilização
A criação da base de dados pode ser feita manualmente, ou se preferível, há dentro da pasta [base-de-dados](https://github.com/rafaeltxc/duxus-challenge/tree/main/base-de-dados),
o arquivo [comando-start-container.txt](https://github.com/rafaeltxc/duxus-challenge/blob/main/base-de-dados/comando-start-container.txt)
que contém o comando para a criação de um container PostgreSQL, utilizando docker e já com as configuração da base de dados.

Caso seja escolhido criar a a base de dados manualmente, as configurações utilizadas foram:

 - User: "admin"
 - Senha: "admin"
 - Nome da base: "duxus"

Se desejar usar outras configuraçoes para a base de dados, altere também as configurações de conexão do arquivo
[pom.xml](https://github.com/rafaeltxc/duxus-challenge/blob/main/pom.xml)
de acordo.

Para iniciar a aplicação, são necessários dois passos. Primeiro, inicialize o Back-End, o qual pode ser feito normalmente
da mesma forma padrão que o SpringBoot utiliza, através da IDE ou da linha de comando. Com o servidor
Back-End ativo, acesse a pasta [front-end](https://github.com/rafaeltxc/duxus-challenge/tree/main/front-end)
e execute o comando yarn ou yarn install para instalar os pacotes necessários. Após a instalação dos pacotes,
o comando yarn dev irá inicializar o servidor, o qual estará disponível na URL:

 - http://localhost:5173/

O Hibernate, automaticamente, já irá executar o código SQL para a criação de tabelas na base de dados,
porém, caso deseje fazer essa operação manualmente, o arquivo [esquema-base.sql](https://github.com/rafaeltxc/duxus-challenge/blob/main/base-de-dados/esquema-base.sql)
possui o SQL para executar a criação de tabelas no banco PostgreSQL.

Após finalizados os passos, a aplicação já estara ativa para receber requisições. O arquivo [mockup-base.json](https://github.com/rafaeltxc/duxus-challenge/blob/main/base-de-dados/mockup-base.json)
possui alguns objetos já definidos, que podem ser utilizados para popular a base de dados após a sua inicialização.

### Documentação
Pode ser acessada através da interface pelo Front-End, ou diretamente pela seguinte URL:
 - http://localhost:8080/doc
