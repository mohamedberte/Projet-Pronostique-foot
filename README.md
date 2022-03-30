# Projet-Pronostique-foot
On veut réaliser un jeu de pronostic de football axé sur des cartes représentant des joueurs.


## Cartes
- Exemples de carte : carte rare de Paul Pogba n°3 ; carte commune de Paul Pogba n°3. (Explications ci-dessous.)
- Chaque carte correspond à un joueur de football existant.
- Chaque carte a un niveau de rareté.
- Il existe trois niveaux de rareté pour les cartes :  commune, peu commune, rare.
- Chaque carte porte un numéro.
- Le système doit assurer que chaque joueur a un nombre maximal de cartes à son nom, ce nombre variant selon le niveau de rareté. Plus précisément : on aura en circulation au maximum 10 cartes rares d'un même joueur, numérotées de 1 à 10 ; on aura au maximum 100 cartes peu communes d'un même joueur, numérotées de 1 à 100 ; et on aura au maximum 1000 cartes communes d'un même joueur, numérotées de 1 à 1000.
- Le système doit assurer que chaque carte est unique : il n'existera pas deux cartes du même joueur ayant à la fois la même rareté et le même numéro de carte. On peut avoir en circulation une carte Paul Pogba rare n°3 et une carte Paul Pogba commune n°3 ; on peut avoir une carte Paul Pogba rare n°3 et une carte Paul Pogba rare n°4 ; on ne peut pas avoir en circulation deux cartes Paul Pogba rare n°3.
- Un utilisateur (un joueur du jeu) peut posséder (dans le jeu) des cartes. Il peut en acheter avec la monnaie du jeu et en revendre aux autres utilisateurs.
- Chaque joueur de foot est classifié soit comme gardien de but, soit comme joueur de champ.

## Compétitions sportives
- Un joueur de foot peut être dans une équipe.
- Une équipe est constituée de joueurs.
- Un match concerne toujours deux équipes. Exemple : Nantes contre Rennes.
## Temps
- Le jeu se découpe en phases d'une semaine.
- Les semaines sont numérotés par semaines calendaires dans l'année. Exemple : semaine 42 de 2021.
- Performance individuelle
- Lors de chaque semaine écoulée, un joueur de foot peut avoir pris part à un match. Sa performance individuelle est évaluée par un service externe.
- Chaque semaine les performances de chaque joueur sont mises à disposition dans un fichier.
- Le format de fichier est le même chaque semaine.
## Jeu
- À chaque instant, un utilisateur peut engager dans le jeu pour la semaine suivante une équipe composée de 4 cartes qu'il possède.
- Chaque semaine, un utilisateur peut ainsi composer une nouvelle équipe pour la semaine à suivre.
- Chaque équipe doit comporter une carte gardien de but et une seule.
- À l'issue de chaque semaine, quand le fichier des données réelles est disponible, on comptabilise les points des équipes engagées dans le jeu. Les points d'une équipe dans le jeu sont constitués de la somme des points des cartes de cette équipe. Les points d'une carte dépendent de la performance du joueur et d'un facteur lié à la rareté expliqué plus loin.
- Un même joueur ne peut apparaître qu'une fois dans une même équipe (même si on a deux cartes différentes).

## Points dans dans le jeu
- Les points obtenus par une carte sont proportionnels à la performance du joueur lors de son match et à un facteur dépendant de la rareté de cette carte. Les facteurs liés à la rareté sont fixés comme suit : +0% pour une carte commune, +5% pour une carte peu commune et +10% pour une carte rare (c'est à dire des facteurs 1, 1.05 et 1.1).
- Si un joueur n'a pas de match dans la semaine, ou si il n'a pas joué lors du match de son équipe, sa performance est de 0 point. Dans ce cas un carte de ce joueur rapporte 0 point cette semaine-là.
À l'issue de chaque semaine, l'utilisateur ayant remporté le plus de points remporte une carte rare. Le second remporte une carte peu commune. Le troisième remporte une carte de la troisième commune.
- Les cartes à gagner chaque semaine ne sont pas spécifiées à l'avance. Toutefois, le système ne doit pas mettre en circulation plus de cartes que la limite imposée par la rareté.
- On connaît avant chaque semaine de jeu la liste des matchs ayant lieu cette semaine-là (nom des deux équipes, compétition, horaire). Ces informations seront fournies par un service externe dans un fichier chaque semaine.

## Données
- Les fichiers contenant la liste des joueurs considérés, la liste des équipes, l'appartenance des joueurs aux équipes, seront partagés à tous les binômes.
- Les fichiers contenant la liste des matchs d'une semaine sera partagée à tous les binômes (sur Madoc).
- Les fichiers contenant les résultats sportifs de la semaine seront partagés à tous les binômes (sur Madoc).
## Utilisateurs
- Un utilisateur est identifié par un pseudo unique.
- Lorsqu'un utilisateur arrive dans le jeu, il reçoit 10000 (dix-mille) pièces. Cette monnaie est utilisée pour acheter et vendre des cartes. Le nom de la monnaie est libre mais ne doit pas pouvoir être confondu avec une monnaie existante.
## Acquisition des cartes
- Une bourse d'échange permet de publier des offres de deux types : vente ou achat. On peux répondre favorablement à une annonce. Le système vérifie que la transaction est possible puis l'effectue.
- Certaines cartes sont mises en vente non pas par des utilisateurs mais par le jeu. Le système doit assurer que le jeu ne peut pas vendre trop de cartes et ne plus être en capacité d'honorer les gains hebdomadaires pour une année (le nombre de cartes possibles étant limité).
