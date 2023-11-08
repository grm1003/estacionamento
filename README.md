# Estacionamento Sistema
# Introdução
O sistema proposto pelo nosso grupo é um sistema de gerenciamento de estacionamento em modelo de API REST, no qual as vagas estão localizadas em 2 pisos (piso 0 e piso 1), sendo o primeiro com 50 vagas e o segundo com 30 vagas. Para que os carros possam estacionar, é necessário informar na “portaria” (informações serão passadas para o sistema) a placa, o modelo e a marca do carro e a vaga desejada, então é feita uma verificação para determinar se ela está ocupada e caso não esteja, o carro poderá ser estacionado, salvando o registro de mais um carro caso não o tenha registrado. O cliente recebe um cartão que será gerado e salvo pelo sistema que registra a hora de entrada e a informação da placa do carro, e quando ele desejar sair do estacionamento, é registrado o horário de saída e será calculado o valor que ele terá que pagar pelo período em que deixou seu automóvel estacionado. O cliente realiza o pagamento (através de cartão de crédito, cartão de débito ou pix), assim liberando o carro para sair da vaga e deixar o estacionamento.
## Motivação:
desenvolvimento de um servidor local de gerenciamento de vagas de estacionamento que seja adaptável a vários estacionamentos e com código organizado e pronto para melhorias e extensões de funcionalidades, entregando um projeto de alto nível e de fácil manutenção e atualização, o qual preza pela praticidade e otimização do tempo gasto pelo cliente, calculando por meio de um cartão os valores a serem pagos e tempo gasto no estacionamento, visando a fácil identificação de erros por meio de um sistema de logs e armazenamento das informações e movimentações diárias do estacionamento, sendo mais eficiente em casos de necessidade de ver os dados e problemas.
# Padrões de Projeto
##  Fábrica:
foi utilizado para centralizar a criação dos carros, colocando possibilidades de várias marcas para criação, facilitando a criação de carros e colocando elas em uma classe com somente essa responsabilidade, tornando a classe mais eficiente e concisa.
.

##  Fachada:
foi utilizado para simplificar o Estacionamento na utilização das funcionalidades, deixando rituais/sequência de comandos prontos no Estacionamento Fachada para simplificar a utilização das funções nas camadas mais exteriores e perto do usuário . Nesse caso, criando um método único com toda a sequência de entrada de um carro no estacionamento.

##  Observer:
foi utilizado para notificar a entrada e saída de carros nos pisos do Estacionamento e ter o controle do número de vagas, atualizando sempre a quantidade de vagas disponíveis em cada piso.

##  Singleton: 
foi utilizado para fazer uma classe Logger que é usada no registro das ações do Estacionamento para estar no console da aplicação para melhor identificação de possíveis erros, assim reaproveitando o objeto, evitando desperdício de memória.

##  Strategy:
foi utilizado para tornar o algoritmo,de forma de realizar o pagamento,  “aberto” para extensões de novos tipos de pagamentos,.


# Princípios de Projeto
##  Responsabilidade Única: 
foi utilizado para deixar o sistema mais coeso e não misturar as finalidades dos métodos, cada método tem apenas uma função.

##  Segregação de Interfaces:
foi utilizado para deixar o sistema mais coeso e não “engessar” classes que usam interfaces; todas as interfaces são simples, pequenas e coesas e as classes que as implementam podem cumprir sua função com suas particularidades.

##  Demeter: 
foi utilizado para evitar a quebra de encapsulamento e manter as informações ocultas; utilizado especialmente com responsabilidade, pois ainda restaram cadeias de chamadas que não podiam ser desfeitas, caso contrário alteraria o funcionamento do sistema.

## Prefira Composição à Herança: 
foi utilizado para não correr risco de haver o uso impróprio de herança.


# Framework
	O framework utilizado foi o framework Spring Boot, utilizado para facilitar o começo do desenvolvimento da aplicação, tendo uma criação de projeto mais fácil com a possibilidade da utilização de várias dependências que apoiam e suportar melhor o desenvolvimento do sistema e auxilia na conexão com o banco de dados, como a ideia era um estacionamento, para calcular o tempo que o carro ficaria no estacionamento houve a necessidade da aplicação subir um servidor para poder continuar rodando e guardando as vagas preenchidas e disponíveis, além de possibilidade de salvar a data de entrada e a depois a data de saída e após isso calcular o valor a ser pago pelo tempo. Depois de utilizar esse framework percebe que nas vantagens está a facilitação de desenvolvimento e injeção de dependências facilitadores e úteis, entre as desvantagens é que o framework possui uma sequência de padrões e estruturas muito bem definidas e para alguns projetos se torna muito complexo altera-la para o projeto ter a funcionalidade desejada, além da grande complexidade oculta, na qual em casos de alguns erros quando são internos no sistema, não conseguimos identificar muito bem o que causou, pois a complexidade está “escondida”.


# Conclusão
Foi mais difícil do que achávamos incorporar os padrões de projeto no nosso sistema, pois tivemos que criar novas funções para que eles tivessem alguma utilização no sistema pelo o que foi definido como nosso tema de sistema a ser feito. Os princípios de projeto foram mais fáceis de integrar que são implementados pelos Padrões de Projeto já. Tivemos um pouco de dificuldade com a conexão com o banco de dados pela estrutura robusta do Spring, foi demorado mas conseguimos fazer ela funcionar, além disso tivemos dificuldades em relação a algumas injeção de dependência do Spring, pois algumas notations só aceitam injeção de objetos com contrutor sem parametros o que fomos descobrir após vários testes, além de dificuldade com as request que davam erro na desereaização do json para objeto para poder ser utilizado no sistema.

## Autores
> Andressa Oliveira Bernardes - 12121BSI201
> Elton Calebe Martins Prates - 11911BSI215
> Gabriel Rezende Machado - 12121BSI217
> Guilherme Martins de Oliveira - 12121BSI202

