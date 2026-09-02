O MVC (Model-View-Controller) é um dos padrões de arquitetura de software mais influentes da história. Criado no final dos anos 1970 por Trygve Reenskaug para a linguagem Smalltalk, 
ele popularizou a separação de responsabilidades nas interfaces de usuário e serviu de fundação para a 2ª Geração das arquiteturas de software.

1. Model (Modelo)
É a representação dos dados e das regras de negócio da aplicação. O Model é o coração do sistema: ele sabe como manipular, validar e calcular as informações, independentemente de como elas serão exibidas na tela.

Responsabilidade: Armazenar o estado da aplicação, aplicar validações e executar cálculos (ex.: calcular o imposto do produto).

O que NÃO faz: Não conhece telas, não formatada textos para exibição e não lê entradas do teclado.

2. View (Visão / Interface)
É a camada responsável por apresentar os dados ao usuário e capturar suas interações.

Responsabilidade: Renderizar os dados recebidos do Model em um formato legível (seja um menu no console, um HTML, uma tela Swing/JavaFX ou um JSON) e capturar os cliques ou textos digitados.

O que NÃO faz: Não executa cálculos de regras de negócio e não acessa o banco de dados diretamente.

3. Controller (Controlador)
É o intermediário / orquestrador. Ele escuta as ações do usuário enviadas pela View, interpreta o que o usuário quer fazer, aciona os métodos apropriados do Model e decide qual View deve ser exibida em resposta.

Responsabilidade: Receber a requisição/evento, invocar o Model correto e repassar o resultado atualizado para a View.

O que NÃO faz: Não contém regras de negócio complexas e não desenha a interface.

DAO - Data Access Object