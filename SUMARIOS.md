# Sumários — Programação de Dispositivos Móveis

Ano letivo 2026/2027. Período de 16 de setembro a 11 de novembro de 2026.

## Horário
**quarta-feira**: 14h-17h

---

## 16/09/2026 — quarta-feira

**Título:** Apresentação da unidade curricular e fundamentos de Kotlin

**Descrição:** Apresentação dos objetivos, dos conteúdos programáticos, da metodologia e da avaliação. Enquadramento do desenvolvimento de aplicações para plataformas móveis. Sintaxe de Kotlin: variáveis imutáveis e mutáveis, tipos e inferência de tipos, funções e parâmetros com valor por omissão. Nulidade e chamadas seguras. Funções de ordem superior e lambdas.

## 23/09/2026 — quarta-feira

**Título:** Estrutura com Jetpack Compose, estado e eventos

**Descrição:** Projeto Android com Jetpack Compose. `Activity`, `setContent`, tema e primeiro ecrã dentro de um `Scaffold`. Funções composable, disposição de texto e botão em `Column`. Estado com `remember` e `mutableStateOf`. Evento de clique que atualiza o texto apresentado.

## 30/09/2026 — quarta-feira

**Título:** Calculadora: grelha, estado e lógica de operações

**Descrição:** Grelha com `Column` e `Row`, distribuição do espaço e botão reutilizável que distingue dígitos e operações. Estado do visor e introdução de dígitos e do separador decimal. Extração da lógica para `CalculatorBrain`, com um enumerado para as operações. Operações binárias e unárias, igualdade, limpeza do acumulador e reposição do visor.

## 07/10/2026 — quarta-feira

**Título:** Execução do exemplo da calculadora

**Descrição:** Execução, pelos alunos, do exemplo da aula anterior: grelha, estado do visor, separação entre a interface e a lógica, e operações. Sem código novo.

## 14/10/2026 — quarta-feira

**Título:** Listas, pedidos HTTP e ViewModel

**Descrição:** Modelo de artigo e apresentação da coleção com `LazyColumn`. Pedidos HTTP com OkHttp, interpretação de JSON e conversão da data recebida do servidor. Arquitetura com `ViewModel`, `StateFlow` e corrotinas. Exposição dos artigos, do estado de carregamento e dos erros.

## 21/10/2026 — quarta-feira

**Título:** Imagens remotas, WebView e navegação

**Descrição:** Linha de artigo com imagem remota e formatação da data de publicação. Visualização da notícia numa `WebView`. Navegação entre a lista e o detalhe, com o título e o endereço passados como argumentos da rota.

## 28/10/2026 — quarta-feira

**Título:** Barra superior, partilha e navegação inferior

**Descrição:** Barra superior, regresso na pilha de ecrãs e `Intent` implícito para partilhar o título e o endereço da notícia. Organização do código em pacotes por responsabilidade. Barra de navegação inferior e destinos que filtram as notícias por categoria.

## 11/11/2026 — quarta-feira

**Título:** Firebase Auth, Cloud Firestore e navegação

**Descrição:** Início de sessão com email e palavra-passe. Estado do formulário no `ViewModel`, validação dos campos e estado de carregamento. Firebase Auth para iniciar e terminar a sessão, e destino inicial conforme exista um utilizador autenticado. Cloud Firestore: escrita de um documento, consulta filtrada e escuta em tempo real com `addSnapshotListener`. Subcoleção, criação e edição de um documento, e atualização de um campo. Lista com `LazyColumn`. Navegação com argumentos de rota. Barra superior com regresso na pilha e uma ação.
