### Overview
No teste foi utilizado o código base recebido. A aplicação foi modificada em diversos aspectos
para uma melhor implementação do projeto. Possui um padrão MVC e com inspiração no modelo de arquitetura
DDD (Domain Driven Design). Não foram adicionadas novas Queries e novos testes em ordem de preservar
o foco nos requisitos mais importantes. Todos os requisitos foram atendidos e a aplicação propriamente testada.

 - **Base de dados**
    - O esquema relacional da base da base de dados permanece o mesmo, com apenas uma única alteração
    na tabela de times.
    - Há uma pasta no repositório chamada [base-de-dados](https://github.com/rafaeltxc/duxus-challenge/tree/main/base-de-dados),
    nela é possível encontrar o código .sql para criar a base de dados, ou se preferível, o comando docker
    para inicializar o container com as configurações já estabelecidas. É também possível encontrar um arquivo
    .json com os dados que podem ser usados parar popular a base de dados quando ela for iniciada. Por último, há
    uma imagem para a visualização do esquema relacional da base de dados.

<br>

 - **Testes**
    - Os testes sendo executado são os mesmos recebidos. Houve apenas uma única alteração, no teste
    do método "contagemPorFranquias". Foi alterado o seu resultado, visto que o mesmo não coincidia com os
    argumentos que estavam sendo passados.

<br>

 - **Tratamento de erros**
    - O programa possui um sistema de tratamento de erros básico, e que pode ser aprimorado para abranger
    um maior número de erros tratados. Não foram criados tratamentos de erros customizados,
    apenas utilizadas as classes de erros padrões que a linguagem oferece.

<br>

 - **Segurança**
    - O sistema possui uma configuração básica de segurança, lidando apenas com a configuração do CORS.

<br>

 - **Documentação**
    - O código possui documetação por toda a sua extensão, com exceção dos modelos e dos testes. Classes e métodos,
    também possuem detalhamento dos seus funcionamentos, tanto quanto os que já estavam presentes,
    quanto os que foram criados. Também é possível encontrar a documentação da API após a inicialização do sistema,
    presente na URL (troque a porta do servidor, caso esteja utilizando uma diferente):

        - http://localhost:8080/doc

<br>

 - **Configurações**
    - Diversas configurações do programa foram modificadas ou atualizadas. O [application.properties](https://github.com/rafaeltxc/duxus-challenge/blob/main/src/main/resources/application.properties)
    possui como já de padrão, as configurações para a conexão com a base de dados, mas também possui as
    configurações de como a aplicação deve manifestar os erros do sistema.
    - No [pom.xml](https://github.com/rafaeltxc/duxus-challenge/blob/main/pom.xml) foi necessário atualizar a versão
    do java (Java 17) e de alguns plugins, por conta de problemas de compatibilidade com a execução de testes
    pelo SpringBoot, e também foram inseridos novos plugins para a implementação do sistema.

### Front-End
O Front-End foi desenvolvido com React e TypeScript, utilizando Vite para criar a aplicação. O programa possui
duas telas, uma para a inserção de **Integrantes**, e uma para a criação de novas **Composições** e seus respectivos
**Times**. Integrado com a API, é possível, de forma simples, executar a persistência dos dados na base.

### Utilização
**Observação:** Devido ao uso do pacote "react-router-dom" no projeto Front-End, não foi possível fazer o bundle
do projeto para uma inicialização mais simples da aplicação, com todas as URLs no mesmo lugar, devido
a uma inconsistência de renderização nas trocas de páginas e redirecionamento de URLs quando os projetos estavam
acoplados.

Para iniciar, são necessários dois passos. Primeiro, inicialize o Back-End, o qual pode ser feito normalmente
da mesma forma padrão que o SpringBoot utiliza, através da IDE ou da linha de comando. Com o servidor
Back-End ativo, acesse a pasta [front-end](https://github.com/rafaeltxc/duxus-challenge/tree/main/front-end)
e execute o comando yarn ou yarn install para instalar os pacotes necessários. Após a instalação dos pacotes,
o comando yarn dev irá inicializar o servidor, o qual estará disponível na URL:

 - http://localhost:5173/

Após finalizados os passos, a aplicação já estara ativa para receber qualquer request.

### Observações pessoais & Sugestões
Embora o teste tivesse uma base sólida para dar inicio as ideias, notei alguns detalhes que poderiam ser
aprimorados para uma melhor compreensão. Alguns requisitos e instruções do teste me aparentaram ser um pouco vagos
em alguns aspectos, visto que até mesmo algumas informações apresentadas dentro do arquivo de instruçoes,
como os metódos pré estabelecidos para avaliação de como os dados são tratados,
possuiam parametros diferentes dos de dentro do codigo, e os resutados de seus
testes, também dentro da aplicação, possuiam requisitos de resultados diferentes
do qual as instruções pediam e seus nomes informavam.
Por esses motivos, não houveram tantas alterações na base do projeto em si, visto desconhecer o como essas
alterações poderiam afetar no critério de avaliação. Penso que algumas melhorias na clareza e na consistência
do teste, proderiam contribuir para uma execução mais precisa.

