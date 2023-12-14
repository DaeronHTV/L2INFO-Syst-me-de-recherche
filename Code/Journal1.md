# Journal de bord INF354

*__Nom du Groupe :__* Usb
--------
*_Composition du groupe_ :* Arthur - Aurélien - Haci-Yusuf - Maxime - Mahrez - Victor
--------
## Semaine du 5 au 9 Novembre

### Objectif de la semaine
1. Utilisation de Gitlab
	* Apprendre les différentes commandes
	* Savoir les utiliser
2. Utilisation du langage MarkDown
	* Apprendre les balises du langage
	* Utiliser le langage pour créer et mettre à jour le journal de bord
3. Terminer DictionnaireNaïf.java
	* Terminer les méthodes
	* Commencer à faire les tests de ces derniers

### Répartition des tâches
1. Utilisation de Gitlab
   * Arthur et Maxime   
2. Utilisation de MarkDown
	* Aurélien et Haci-Yusuf   
3. Terminer DictionnaireNaif.java
	* Arthur, Aurélien, Victor, Mahrez, Maxime et Haci-Yusuf

### Difficultés rencontrées
1. Utilisation de Gitlab
	* Création d'un dépôt local avec **git clone** entrainant l'erreur suivante : _unable to access '...' Handshake failed_

### Modalités de résolution
1. Utilisation de Gitlab
	* Création d'un dépôt local avec **git clone** : Problème résolu -> Cela était dû à une mauvaise URL renseigné durant son utilisation 

-------------
## Semaine du 12 au 16 Novembre

### Mercredi 14 Octobre

#### Objectif de la semaine
1. Travailler sur l'interface "MatriceIndex"
	* Classe permettant d'indexer dans une matrice le nombre d'occurence de mots présent dans le dictionnaire par texte
	* Créer la classe implémentant l'interface MatriceIndex.java ("MatriceIndexNaif.java")
	* Construire les méthodes 
2. Travailler sur l'interface "AccesSequentielModele1.java"
	* Classe permettant d'effectuer des parcours d'elts en fonction de leur type
	* Créer la classe implémentant l'interface MatriceIndex.java ("MatriceIndexNaif.java")
	* Construire les méthodes 

#### Répartition du travail
1. Création de la classe "MatriceIndex"
	* Arthur et Maxime et Victor
2. Création de la classe "AccesSequentielModele1"
	* Aurélien et Haci-Yusuf
  
#### Problèmes rencontrés
1. Utilisation de Gitlab
	* Impossibilité d'envoyer les nouvelles versions du fichier 
2. Classe "AccesSequentielModele1"
	* Problème de compilation par rapport aux types des variables qui est générique (<T>)

#### Modalité(s) de résolution
1. Utilisation de Gitlab
	* Suppression des dépôts locaux et recréation de ces derniers par l'utilisation de **git clone** 
2. Classe "AccesSequentielModele1"
	* Aucune solution trouvée actuellement
  
### Vendredi 16 Novembre

#### Objectif(s) de la semaine
1. Terminer les tests de la classe "DictionnaireNaif.java"
2. Terminer "AccesSequentielModele1.java"
	* Essayer de trouver une solution par rapport aux types générique
3. Continuer "MatriceIndex.java"
	* Terminer les méthodes de lecture et indexation

#### Répartition du travail
1. Tests "DictionnaireNaif.java"
	* Arthur et Aurélien
2. Terminer "AccesSequentielModele1.java"
	* Arthur et Aurélien
3. Continuer "MatriceIndexNaif.java"
	* Victor et Maxime

#### Problème(s) rencontré(s)
2. Terminer "AccésSéquentielModele1.java"
	* Problème sur le type générique <T>
  
#### Modalité(s) de résolution
2. Terminer "AccesSequentielModele.java"
	* Créer le tableau en utiisant la commande "Object"
	* Ou spécifier directement le type utilisé ("Méthode choisie actuellement")

-----------------------------
## Semaine 19 du au 23 Novembre

### Mercredi 21 Novembre

#### Objectif(s)
1. Continuer MatriceIndexNaif.java
	* Ajouter un schéma de parcours effectuant le parcours de dossier de fichier et ajoutant les mots et les textes dans des dictionnaires
2. Création de la classe "LecteurdeMots.java"	
	* Classe permetttant de lire les textes présent dans différents dossier.
	* Effectue un parcours de chaque texte et renvoie chaque mot 1 par 1
	* Renvoie ce dernier afin d'être utilisé par la suite par la classe "MatriceIndexNaif.java"

#### Répartition du travail
1. Terminer MatriceIndexNaif.java
	* Arthur, Maxime et Victor
2. Création de "LecteurdeMots.java"
	* Haci-Yusuf et Aurélien

#### Difficulté(s) rencontrée(s)
2. Création de la classe "LecteurdeMots.java"
	* Problème au niveau de la déclaration des variables en attribut
	* Problème au niveau des ";" à la fin des lignes de codes

#### Modalités de résolution
2. Création de la classe "LecteurdeMots.java"
	* Suppression du tableau "Separateur" contenant les différents séparateurs dans un texte
	* Création d'une méthode "Séparateur" afin de séparer les mots
	* Rajout des ";" ouliés sur les lignes de codes

### Vendredi 23 Novemebre

#### Objectif(s)
1. Terminer "MatriceIndex"
	* Finir le parcours des fichiers
	* Commencer à faire les tests de la classe
2. Terminer "LecteurdeMots.java"
	* Commencer à faire les tests de la classe
	* Modifier le code pour prendre en compte les exceptions
3. Commencer à réflechir sur la classe "Recherche"
	* Prend en paramètre des mots rentrés par l'utilisateur
	* Effetue une recherche en fonction des paremètres défnis
	* Utilise l'indexation effectuée par "MatriceIndex"
	* Renvoie une liste de documents suivant leur pertinence
4. Faire les tests de "AccesSequentielModele1"

#### Répartition du travail
1. Terminer "MatriceIndex"
	* Arthur, Maxime et Victor
2. Terminer "LecteurdeMots.java"
	* Haci-Yusuf et Aurélien
3. Faire les tests de "AccesSequentielModele1"
	* Aurélien

#### Difficulté(s) rencontrée(s)
2. Terminer "LecteurdeMots.java"
	* Mauvaise méthode de parcours des textes effectuée
3. Faire les tests de "AccesSequentielModele1"
	* Aucun problème rencontré car nous utilisons plus cette classe étant donné que nous avons maintenant la classe "LecteurdeMots"

#### Modalités de résolution
2. Terminer "LecteuredeMots.java"
	* Suppresion du tableau créer en attribut
	* Suppresion de la varibale "longueur" en attribut
	* Ajout des variables "Poscarac" (Donnant la position du caractère) et "posMot" (Donnant la position du dernier mot
	* Modification de la méthode "demarrer", "avancer", et "elemenCourant"

--------------------------
## Semaine du 26 au 30 Novembre

### Mercredi 28 Novembre

#### Objectif(s)
1. Faire la classe "Recherche"
	* Permet de prendre l'ensemble des mots tapés par l'utilisateur
	* Effectue une recherche dans le corpus des textes
	* Rangera l'ensemble des textes pertients dans une liste qui sera renvoyé à l'utilisateur
	*Triera les textes en fonction de la pertinence des mots (NB de fois dans le texte)
2. Terminer "LecteurdeMots"
	* Ajouter l'ouverture des fichiers dans demarrer
	* Changement de nom en "AccesSequentielModele1Naif" -> Suppression de l'ancienne version de l'AccesSequentielModele1

#### Répartition du travail
1. Faire la classe "Recherche"
	* Aurélien, Arthur et Maxime
2. Terminer "LecteurdeMots"
	* Victor, Arthur, Maxime et Mahrez

#### Difficulté(s) rencontré(s)
1. Faire la classe "Recherche"
	* Difficultés au niveau de la pertinence des fichiers en fonction de l'occurence des mots donnés. 

#### Modalité(s) de résolution :
1. Faire la classe "Recherche"
	* Solution en cours de recherche actuellement

-------------------------
## Semaine 3 au 7 Décembre

### Mercredi 5 Décembre

#### Objectif(s)
1. Commencer l'écriture des tests de "MatriceIndexNaif" et "AccesSequentielModele1Naif"
	* Ecrire les tests maven de cette classe
	* Faire en sorte qu'il corresponde à la nouvelle version de ces classes
2. Modifier "MatriceIndex" pour correspondre aux nouvelles attentes
	* Supprimer les zéros de la Matrice
	* Représentée cette dernière par une séquence cahinée
3. Continuer la classe "Recherche"
	* Résoudre les problèmes au niveau de la pertinence
	* Terminer la partie recherche des documents

#### Répartition du travail
1. Commencer l'écriture des tests de "MatriceIndexNaif" et "AccesSequentielModele1Naif"
	* Aurélien
2. Modifier "MatriceIndex"
	* Maxime et Arthur
3. Continuer la classe "Recherche"
	* Haci-Yusuf et Mahrez

#### Difficulté(s) rencontrées
1. Commencer l'écriture des tests de "MatriceIndexNaif" et "AccesSequentielModele1Naif"
	* Difficultés sur la lecture des fichiers txt et sur la sauvegarde de la matrice dans un txt -> Spécification du chemin pour atteindre les fichiers txt
	* Problème de compilation de "TestAccesSequentielModele1Naif"
2. Modifier "MatriceIndex" pour correspondre aux nouvelles attentes
	* Modifictaion de la cellule afin de prendre en compte deux elts

#### Modalités de résolution
1. Commencer l'écriture des tests de "MatriceIndex" et "AccesSequentielModele1Naif"
	* Problème sur la lecture non résolue actuellement
	* Oublie d'importation de org.unit.Test
2. Modifier "MatriceIndex" pour correspondre aux nouvelles attentes
	* Création d'un objet avec deux paramètres et qui sera par la suite le paramètre de la cellule