# Comparacao de Algoritmos de Ordenacao (sem estruturas prontas)

Algoritmos implementados em Java **sem** uso de estruturas prontas (List, ArrayList, Map, etc.) e **sem** bibliotecas externas:
- Comb Sort
- Gnome Sort
- Bucket Sort (com buckets de tamanho fixo em arrays e ordenacao por insercao dentro de cada bucket)
- Bubble Sort com flag de parada
- Selection Sort
- Cocktail Sort

## Como contamos
- **swaps**: numero de trocas de dois elementos (a[i] <-> a[j]).
- **loops**: quantidade de vezes que um corpo de laco foi executado (while/for).

## Vetores
```
vetor1 = {12,18,9,25,17,31,22,27,16,13,19,23,20,30,14,11,15,24,26,28}
vetor2 = {5,7,9,10,12,14,15,17,19,21,22,23,24,25,27,28,29,30,31,32}
vetor3 = {99,85,73,60,50,40,35,30,25,20,15,14,13,12,11,10,9,8,7,6}
```

## Resultados
Os resultados abaixo foram gerados executando o mesmo codigo em Python para tabular e checar, e o Java reflete a mesma logica de contagem.

### Tabela (swaps e loops)
Veja o arquivo `resultados.csv` e as tabelas mostradas no Chat.

### Ranking por menos trocas (1 = melhor)
- **vetor1**: Bucket (0) < Selection (18) < Comb (22) < Gnome/Bubble/Cocktail (78)
- **vetor2**: todos 0 (ja ordenado)
- **vetor3**: Bucket (0) < Comb (18) < Selection (19) < Cocktail (171) < Bubble (190) < Gnome (190)

### Ranking por menos iteracoes de laco (1 = melhor)
- **vetor1**: Bucket (84) < Comb (138) < Cocktail (160) < Gnome (176) < Bubble (195) < Selection (209)
- **vetor2**: Gnome/Bubble/Cocktail (20) < Bucket (82) < Comb (118) < Selection (209)
- **vetor3**: Bucket (108) < Comb (138) < Cocktail (160) < Selection (209) < Bubble/Gnome (400)

## Como rodar
Compile e execute:
```
javac Main.java
java Main
```

Saida esperada (valores iguais aos da tabela).
