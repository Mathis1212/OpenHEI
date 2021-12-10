# OpenHEI
### _Un site pour les recenser tous..._


OpenHEI est un site facile d'accès, destiné aux étudiants désireux d'accéder à leurs cours via une interface intuitive.
Les rubriques ci-dessous vous expliqueront comment lancer le projet et exploiter ses fonctionnalités.
## Fonctionnalités

- Pouvoir accéder à des matières contenant différents cours via la page "Matières"
- Télécharger les cours disponibles
- Mettre à jour les cours disponibles
- Ajouter des cours dans une matière
- Supprimer des cours d'une matière
- Créer un compte utilisateur unique
- S'y connecter avec sa combinaison login/mot de passe

## Prérequis d'utilisation

De manière à pouvoir utiliser OpenHEI, vous devez installer les logiciels et plug-ins intégrés suivants :

- WampServer : logiciel nécessaire à la mise en place d'une base de données via PHPMyAdmin
- IntelliJ IDEA : logiciel essentiel pour la création et l'exécution du produit
- Tomcat : nécessaire au déploiement du site web grâce aux artéfacts
- Maven : nécessaire à la gestion du projet et à la mise en place de dépendances
- Un compte dans l'environnement Git, pour télécharger le projet

Et bien sûr, les fichiers de OpenHEI ! 

## Mise en place de OpenHEI

Étape préliminaire : installer l'ensemble des logiciels et plug-ins cités dans la section ci-dessus.

Étape n°1 : lancer WampServer et PHPMyAdmin, puis exécuter les lignes de commandes successivement des fichiers suivants :
```sh
Data_Base.txt
Requetes_Matiere_Cours.txt
```

Étape n°2 : Ajouter dans les configurations d'IntelliJ un serveur Tomcat, avec l'URL suivante :
```sh
http://localhost:8080/Accueil
```
Déployer un artéfact "war exploded" avec l'Application Context suivant :
```sh

```

Étape n°3 : Exécuter le projet en utilisant le raccourci suivant :
```sh
Maj+F10
```
Le site OpenHEI est maintenant opérationnel, bonne utilisation !

## Tutoriels : obtenir le statut Administrateur et l'accorder à d'autres utilisateurs

### Obtenir le statut Administrateur
Étape n°1 : Exécuter le projet (cf Étape n°3 de la rubrique ci-dessus).  
Étape n°2 : Créez un compte sur le site via la page "Sign In".  
Étape n°3 : Après avoir créé votre compte, rendez-vous sur la page de PHPMyAdmin avec votre BDD.  
Étape n°4 : Allez sur la table "usager" de la BDD.  
Étape n°5 : Modifiez la propriété "user_admin" en modifiant le champ à "1".  
Étape n°6 : Redémarrez le site en exécutant le raccourci suivant :
```sh
Maj+F10
```
Étape n°7 : Connectez-vous sur votre profil via la page "Login".

Vous pouvez à présent profiter du statut Administrateur !  
NB : un Administrateur avec un login/mdp est ajouté de base à la BDD. Vous pouvez utiliser celui-ci aussi.
### Accorder le statut Administrateur 
Étape n°1 : Connectez-vous sur un profil Administrateur.  
Étape n°2 : Rendez-vous sur la page "Gestion User". Sur cette page est renseigné l'ensemble des utilisateurs du site.  
Étape n°3 : Cliquez sur le bouton "Set Admin" à la ligne de l'utilisateur à qui vous voulez donner le statut Administrateur.  

Bravo, vous avez créé un nouveau Administrateur !
## Tutoriels : ajouter, supprimer et mettre à jour un cours

Prérequis : Avoir le statut Administrateur sur le site ; veuillez consulter la rubrique ci-dessus avant de continuer.
NB : l'ensemble de ces fonctionnalités sont disponibles en se rendant sur la page "Matières".

### Ajouter un cours

Étape n°1 : Remplissez les champs nécessaires juste en-dessous du texte "Thèmes".  
Étape n°2 : Choisissez la matière dans laquelle vous ajouterez le cours via la liste déroulante.  
Étape n°3 : Cliquez sur le bouton "Ajouter" au bout de la ligne.  

/ ! \ ATTENTION : Veillez à bien renseigner l'URL du cours, sinon ça ne marchera pas !!  
Vous avez ajouté un cours à la BDD !

### Supprimer un cours

Étape n°1 : Cliquez sur le bouton "Cours" de la matière que vous voulez.  
Étape n°2 : Parcourez la liste jusqu'à trouver le cours que vous voulez supprimer.
Étape n°3 : Cliquez sur le bouton "Delete" sur la ligne du cours que vous souhaitez supprimer.

Vous avez supprimé un cours de la BDD !

### Mettre à jour un cours

Étape n°1 : Cliquez sur le bouton "Cours" de la matière que vous voulez.  
Étape n°2 : Remplissez les champs nécessaires sur la ligne du cours que vous souhaitez mettre à jour.  
Étape n°3 : Cliquez sur le bouton "Update" en-dessous des champs que vous venez de remplir.  
/ ! \ ATTENTION : Veillez à bien renseigner l'URL du cours, sinon ça ne marchera pas !!  

Vous avez mis à jour un cours de la BDD !

## Remerciements
Merci à l'ensemble des professeurs et intervenants de HEI que nous avons sollicité au cours du développement de notre site !