#Projet 3 : Escape Game ONLINE

Le projet consiste à créer un **mécanisme de recherche d’une combinaison à X chiffres**.

Avant tout :  Récupérer le dossier Projet3.

###I - Éxécuter le programme :
Étape à suivre:
1.  Ouvrir le terminal et se rendre dans le dossier Projet3.
2.  Éxécuter la commande :
>			java -jar projet3.jar
3.  Suivre les instructions du terminal.

###II - Explication du fichier modifiable configuration.json
1.  *sizeCombination* correspond à la taille de la combinaison.
    * ex : 5 -> '56482' (chaine d'entier de taille 5).
2.  *nbTest* correspond au nombre de tour de jeu.
    * ex : 3 -> le joueur attaquant aura 3 tentatives pour trouver la solution.
3.  *modeDev* correspond à une variable boolean pour rentrer dans un mode de développeur.
    *   *true* : l'ordinateur montre au lancement sa combinaison à découvrir.
    *	*false* : elle le cache.

###III - Modifier les paramètres du jeu
Étape à suivre:
1.  Aller dans le dossier P3_EscapeGameOnline.
2.  Ouvrir le fichier *configuration.json* avec votre éditeur de texte.
3.  Modifier les paramètres 
					
###IV - LOG
Après le lancement de votre partie un dossier log va se créer. À l'intérieur de ce dossier, un fichier *rollingtest.log* va se créer. Il décrira le comportement du programme.
> ex: 2019-11-04 04:30:42,717 TRACE Console [main] Entrée méthode DisplaySelectMode 
>* ici : Date horaire TypeDeLog ObjetClasse puis le message de l'activité
généralement la trace va annoncer l'entrée/sortie d'une méthodes ou l'instanciation d'un objet, Debug énonce les variables utilisés.

Si le programme est lancé plusieurs fois alors une journalisation est créé sous cette forme :
test1-DATE-HORAIRE-version.log  qui enregistrera rollingtest.log 
>ex :  test1-11-04-19-05-21-1.log

La version que vous possédez ne vous permet pas de modifier le level du log, de modifier le comportement du programme.

###V - Sources
Le dossier sources est une copies des classes.java utilisé dans le projet ainsi qu'une copie du fichier *log4j2-test.properties*.

La javadoc n'a pas été généré avec ce dossier mais vous pouvez la lire dans les classes.java