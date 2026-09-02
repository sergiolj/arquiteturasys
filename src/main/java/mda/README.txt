Primeira aplicação simulando um modelo Model Driven Architecture

Neste caso as etapas abstratas de CIM (Computation Independent Model) e PIM (Platform Independent Model) foram suprimidas, sendo executada em java apenas a PSM (Plataform Specifc Model)



Os Níveis de Abstração da MDA (OMG)
Para estruturar a arquitetura de forma independente de tecnologia e facilitar a evolução do sistema, o padrão MDA (definido pelo OMG) organiza os modelos em três níveis principais de abstração:

CIM (Computation Independent Model):

Foco: O domínio do negócio e seus requisitos.

Características: Oculta detalhes de sistemas de informação e computação. Descreve o que o negócio faz (casos de uso, fluxogramas de processos de negócio, regras de domínio).

PIM (Platform Independent Model):

Foco: A arquitetura lógica e funcional do software.

Características: Descreve a estrutura e os comportamentos do sistema sem se vincular a nenhuma tecnologia específica (sem citar Java, .NET, bancos relacionais, etc.). Foca em componentes, entidades de domínio e contratos de serviço.

PSM (Platform Specific Model):

Foco: A implementação vinculada à tecnologia de destino.

Características: O PIM é transformado em PSM ao incorporar detalhes da plataforma escolhida (ex.: mapeamentos JPA/Hibernate, anotações de controladores Spring/REST, tipos de dados do PostgreSQL, gerenciamento de transações).


O modelo CIM (Computation Independent Model) é o nível de abstração mais alto dentro da arquitetura MDA.

Ele é considerado computacionalmente independente porque descreve o negócio, seus processos e suas regras sem se preocupar se o sistema será implementado em software ou até mesmo de forma manual.

Por que ele é tão abstrato?
Linguagem do Negócio, não da TI: O CIM usa a terminologia direta dos especialistas do domínio (ex.: "Cliente", "Pedido", "Aprovação de Crédito", "Fluxo de Caixa"). Ele não menciona tabelas, telas, APIs, bancos de dados, algoritmos ou classes.

Oculta a Tecnologia: Se todo o sistema computacional da empresa deixasse de existir amanhã e o processo passasse a ser feito em papel e caneta, o modelo CIM continuaria rigorosamente correto, pois a lógica do negócio não mudou.

Foco no "O quê" e no "Por quê": Ele mapeia os objetivos da organização, os atores envolvidos, as entradas, saídas e restrições de cada processo.

Exemplo Prático de Abstração no CIM
Imagine o processo de venda em um e-commerce:

Visão no CIM (Alta Abstração):

"Um Cliente seleciona um Item do Catálogo, realiza o Pagamento e aguarda a Confirmação do Pedido para que o produto seja liberado pelo Estoque."
(Aqui não importa se o pagamento é via PIX, se o estoque usa fila RabbitMQ ou se a confirmação envia um e-mail em Java).

Visão no PIM (Abstração Média):

O modelo conceitual passa a ter um componente de software PedidoService que consome um PagamentoService e gera um evento de PedidoCriado.

Visão no PSM (Baixa Abstração / Específico):

A classe @RestController em Spring Boot mapeia o endpoint POST /pedidos, persiste a entidade @Entity no PostgreSQL via JPA e publica uma mensagem no tópico do Kafka.