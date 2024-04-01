# Red-Black-Tree
### L'arbre rouge-noir est un arbre de recherche qui possède un bit de stockage de plus par noeuds, c'est la couleur.
Grace à cette contrainte on est sur que sur n'importe quel chemin de la racine aux feuilles, l'arbre rouge noir assure qu'il n ya pas de chemin tel qu'il y a plus de 2 fois la longueur qu'un autre chemin. donc l'arbre est "équilibré"
#### En effet l'arbre rouge noir avec n clés possède une taille de 2log(n+1) soit log(n) complexité
### Chaque noeuds de l'arbre contiennnent les attributs couleurs,clé,gauche,droite et p (data).

#### les propriétées sont : 
- chaque noeud est rouge ou noir
- la racien est noir
- chaque feuilles (nulles) sont noirs
- si un noeud est rouge, alors ses enfants sont noirs (racismo ?)
- pour chaque noeuds, les chemins simples du noeuds au racines contienent le même nombre de noeuds

#
### Lemma : " A red black tree with n interval nodes has height at most 2lg(n+1) "

#### Preuve : 
- Nous commençons par démontrer que le sous-arbre enraciné à n'importe quel nœud `x` contient au moins `2^bh(x) - 1` nœuds internes. 
- Nous prouvons cette affirmation par induction sur la hauteur de `x`. 
- Si la hauteur de `x` est 0, alors `x` doit être une feuille (`T.nil`), et le sous-arbre enraciné à `x` contient en effet au moins `2^bh(x) - 1 = 2^0 - 1 = 0` nœuds internes. 
- Pour l'étape inductive, considérons un nœud `x` qui a une hauteur positive et qui est un nœud interne. 
- Alors le nœud `x` a deux enfants, chacun pouvant être une feuille. 
- Si un enfant est noir, alors il contribue de 1 à la hauteur noire de `x` mais pas à la sienne. 
- Si un enfant est rouge, alors il ne contribue ni à la hauteur noire de `x` ni à la sienne. Par conséquent, chaque enfant a une hauteur noire soit de `bh(x) - 1` (s'il est noir) soit de `bh(x)` (s'il est rouge). 
- Étant donné que la hauteur d'un enfant de `x` est inférieure à la hauteur de `x` lui-même, nous pouvons appliquer l'hypothèse inductive pour conclure que chaque enfant a au moins `2^bh(x)-1 - 1` nœuds internes. 
- Ainsi, le sous-arbre enraciné à `x` contient au moins `(2^bh(x)-1 - 1) + (2^bh(x)-1 - 1) + 1 = 2^bh(x) - 1` nœuds internes, ce qui prouve l'affirmation.

Pour compléter la preuve du lemme, soit `h` la hauteur de l'arbre. Selon la propriété 4, au moins la moitié des nœuds sur n'importe quel chemin simple de la racine vers une feuille, sans compter la racine, doit être noire. Par conséquent, la hauteur noire de la racine doit être d'au moins `h/2`, et donc,
`n >= 2^(h/2) - 1`.

En déplaçant le 1 du côté gauche et en prenant les logarithmes des deux côtés, on obtient `lg(n + 1) >= h/2`, ou `h <= 2 lg(n + 1)`.


#### Maintenant les exercices
