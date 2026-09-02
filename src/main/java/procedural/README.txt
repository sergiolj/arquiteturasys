A Arquitetura Procedural representa a 1ª Geração do desenvolvimento de software comercial. Dominante nas décadas de 1970 a 1990 — com linguagens como C, COBOL, Pascal e Clipper —, 
essa abordagem influenciou fortemente a forma como os primeiros programas em linguagens Orientadas a Objetos (como C++ e Java) foram escritos na virada do milênio.

A ideia central da Arquitetura Procedural é organizar o software em uma sequência de instruções imperativas, onde procedimentos e funções manipulam estruturas de dados passivas.

Os Pilares do Modelo Procedural
1. Estruturas de Dados Passivas (Anemic Data Structs)
Os dados são representados por registros ou estruturas (structs) que contêm apenas atributos, desprovidos de comportamento, métodos ou regras de validação.

Papel: Armazenar temporariamente as informações em memória.

Característica: Atributos expostos e manipulados diretamente de fora por funções globais ou scripts.

2. Procedimentos e Funções Manipuladoras
Toda a lógica de controle, cálculo e transformação de dados reside em funções isoladas ou em blocos sequenciais.

Papel: Executar o algoritmo "passo a passo", extraindo os dados da estrutura passiva, aplicando a regra e devolvendo o resultado.

Característica: As funções são donas do algoritmo, mas os dados não pertencem a elas.

3. Controle de Fluxo Centralizado (Scripting/Monólito)
O ciclo de vida completo da aplicação (leitura da entrada, execução das regras e saída de dados) é orquestrado por um laço principal (while) ou uma sequência fixa de chamadas de métodos estáticos.

Principais Características e Limitações do Modelo
Acoplamento Elevado: Regras de negócio, entrada de dados (interface) e persistência (arquivos/banco) vivem no mesmo arquivo ou módulo.

Inexistência de Encapsulamento: Os dados ficam expostos e podem ser alterados por qualquer parte do programa sem validação prévia.

Baixa Testabilidade: Impossibilidade de realizar testes unitários isolados para regras de negócio sem acionar telas ou criar arquivos físicos.

Dificuldade de Extensão: Adicionar um novo requisito (ex.: um novo tipo de imposto ou salvar em uma nova base) exige editar estruturas condicionais (if/else ou switch/case) espalhadas pelo código principal
 (Violação do Princípio Aberto/Fechado - OCP).

Propósito Deste Módulo no Projeto
Esta implementação foi desenvolvida como modelo de referência e benchmark para o estudo comparativo de evolução arquitetural. Ela ilustra o ponto de partida 
histórico da engenharia de software antes do surgimento da Arquitetura em Camadas (2ª Geração)
 e do DDD / Arquitetura Hexagonal (3ª Geração).