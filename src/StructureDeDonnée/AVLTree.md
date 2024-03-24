# Route 1: Fondations et Structures de Base
## But: Mettre en place les fondations de l'arbre rouge-noir en Java.

### Structure Initiale:

#### Classe RedBlackTree: Classe principale pour l'arbre.
#### Classe RedBlackNode: Classe pour les nœuds.
#### Exercices d'Implémentation:

- Implémentez le constructeur pour RedBlackNode qui initialise les clés, les couleurs, et les pointeurs de nœuds enfants/parent.
- Ajoutez une méthode colorFlip() pour inverser les couleurs d'un nœud et de ses enfants.
- Implémentez la méthode isRed() qui vérifie si un nœud est rouge.
- Créez la méthode rotateLeft() qui effectue une rotation gauche d'un nœud.
- Créez la méthode rotateRight() qui effectue une rotation droite d'un nœud.
- Écrivez une fonction insertRecursive() qui insère une clé dans l'arbre de manière récursive.
- Implémentez la méthode fixUp() qui maintient les propriétés de l'arbre rouge-noir après l'insertion.
- Ajoutez une fonction find() pour retrouver un nœud avec une clé spécifique.
- Mettez en place une méthode inOrderTraversal() qui parcourt l'arbre et imprime les clés dans l'ordre croissant.
- Écrivez une fonction deleteMin() pour supprimer le nœud avec la clé minimale.


# Route 2: Opérations d'Insertion et de Suppression
## But: Implémenter et perfectionner les opérations d'insertion et de suppression avec le rééquilibrage approprié.

### Exercices d'Implémentation:

- Élaborez une méthode insert() qui utilise insertRecursive() et s'assure que la racine reste noire.
- Implémentez delete() qui supprime un nœud avec la clé donnée et rééquilibre l'arbre.
- Écrivez une méthode privée moveRedLeft() pour préparer un nœud pour la suppression ou la fusion.
- Créez une méthode moveRedRight() qui fait de même de l'autre côté.
- Implémentez une méthode deleteMax() pour supprimer le nœud avec la clé maximale.
- Ajoutez une méthode replaceNode() pour échanger la position de deux nœuds dans l'arbre.
- Écrivez une méthode getMinNode() pour trouver le nœud avec la clé la plus petite.
- Créez une méthode getMaxNode() pour trouver le nœud avec la clé la plus grande.
- Mettez en place une méthode fixUp() qui s'occupe du rééquilibrage après suppression.
- Élaborez des tests unitaires pour vérifier l'intégrité de l'arbre après chaque insertion et suppression.
# Route 3: Optimisation et Fonctionnalités Avancées
## but: Améliorer les performances et ajouter des fonctionnalités supplémentaires pour compléter la structure de données.

### Exercices d'Implémentation:

- Optimisez les rotations pour minimiser les opérations de mise à jour des pointeurs.
- Implémentez la méthode successor() pour trouver le successeur d'un nœud donné.
- Implémentez la méthode predecessor() pour trouver le prédécesseur d'un nœud donné.
- Ajoutez une fonction getRank() pour retourner la position d'une clé dans l'ordre trié.
- Créez une fonction select(int rank) pour trouver la clé à la rankième position.
- Élaborez une méthode rangeCount() pour compter les nœuds dans une plage de clés donnée.
- Mettez en œuvre un itérateur pour parcourir l'arbre en ordre croissant ou décroissant.
- Développez une interface graphique simple pour visualiser l'arbre et ses propriétés en temps réel.
- Implémentez la Sérialisation 


# faire des benchmark avec tableau