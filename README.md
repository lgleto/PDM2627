# Programação de Dispositivos Móveis

Exemplos das aulas de 2026/2027, regime laboral. Todos os projetos estão escritos em Kotlin e usam os padrões da linguagem trabalhados nas aulas. Cada pasta é um projeto Android que se abre à parte no Android Studio.

Horário: quarta-feira, 14h–17h. Os sumários estão em [SUMARIOS.md](SUMARIOS.md).

## Padrões de Kotlin

O código segue os padrões de Kotlin usados nas aulas, em vez de equivalentes de Java.

- Tipos anuláveis e chamadas seguras (`?.`, `?:`, `?.let`), para tratar valores que podem não existir.
- Funções de extensão sobre `String` e `Date`, para ler a data do servidor e formatá-la.
- `companion object` para membros da classe, como a fábrica de um artigo a partir de JSON.
- Enumerados com propriedades e `when`, para as operações da calculadora.
- `data class` para o estado do ecrã e para os modelos.
- `sealed class` para as rotas de navegação.
- Lambdas e tipos função nos eventos dos botões e nos pedidos assíncronos.
- Parâmetros com valor por omissão e cadeias de caracteres com modelo.

| Exemplo | O que mostra | Aulas |
|---|---|---|
| [HelloWorld](HelloWorld) | `Scaffold`, estado e evento de clique | 23/09 |
| [Calculator](Calculator) | Grelha, botão reutilizável e `CalculatorBrain` | 30/09 e 07/10 |
| [MyNews](MyNews) | Lista, HTTP, `ViewModel`, imagens, `WebView` e barras | 14/10, 21/10 e 28/10 |
| [AnyList](AnyList) | Sessão, Firestore, carrinhos e produtos | 11/11 |

## HelloWorld

Primeiro ecrã com Jetpack Compose. `MainActivity` monta o tema e o `Scaffold`. `MainView` guarda o texto com `remember` e `mutableStateOf` e muda-o no clique do botão.

## Calculator

Calculadora no ponto em que as aulas separam a interface da lógica.

- `CalculatorView` desenha o visor e a grelha com `Column` e `Row`, e trata a introdução de dígitos e do ponto decimal.
- `CalcButton` é o botão reutilizável, com cor do tema diferente para as operações.
- `CalculatorBrain` e o enumerado `Operation` fazem as operações binárias e unárias e guardam o acumulador.

## MyNews

Aplicação de notícias que junta as aulas de listas, rede e navegação. O código está organizado por pacotes (`ui/home`, `ui/components`, `models`).

- `ArticlesView` apresenta os artigos numa `LazyColumn`.
- `ArticlesViewModel` pede os destaques com OkHttp, lê o JSON em corrotinas e expõe artigos, carregamento e erro num `StateFlow`.
- `ArticleViewRow` mostra a imagem remota e formata a data de publicação.
- `ArticleDetailView` abre o endereço da notícia numa `WebView`. A rota recebe o título e o endereço.
- `MyTopBar` volta atrás na pilha e partilha o artigo com um `Intent`.
- `MyBottomBar` muda de categoria (geral, saúde, desporto e tecnologia) e filtra as notícias.

## AnyList

Aplicação de listas de compras com Firebase Auth e Cloud Firestore. O código está organizado por pacotes (`ui/login`, `ui/home`, `ui/home/kartdetail`, `ui/components`, `models`).

- `LoginView` pede o email e a palavra-passe. `LoginViewModel` valida os campos, inicia sessão com Firebase Auth e grava o utilizador na coleção `users`.
- `MainActivity` abre a lista ou o início de sessão conforme exista sessão, e declara as rotas num `sealed class`, com o identificador do carrinho e, na edição, o do produto.
- `KartView` lista os carrinhos do utilizador numa `LazyColumn`. `KartViewModel` escuta a coleção `karts` em tempo real e filtra por `userIds`.
- `AddKartView` cria um carrinho associado ao utilizador atual.
- `KartDetailView` mostra os produtos da subcoleção. `ProductViewRow` apresenta o nome, a quantidade e a caixa de verificação, que atualiza o campo `checked`.
- `AddProductView` cria um produto ou edita o nome e a quantidade de um existente.
- `MyTopBar` volta atrás na pilha nos ecrãs interiores e, na lista, termina a sessão.

## Licença

Estes exemplos são distribuídos sob a licença MIT. O texto completo está em [LICENSE](LICENSE).
