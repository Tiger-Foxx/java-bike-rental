# 🚲 Système de Location de Vélos

## À propos
Un exercice simple de Programmation Orientée Objet en Java, implémentant un système de gestion pour un magasin de location de vélos local.

## Fonctionnalités
- Gestion des clients (max 10)
- Gestion des vélos (max 20)
- Système de location avec limite de 5 vélos par client
- Interface en ligne de commande intuitive
- Gestion des dates avec LocalDate
- Système de numérotation automatique

## Structure du Projet
```
src/
├── Main.java           // Point d'entrée
├── models/            
│   ├── Client.java    
│   ├── Bike.java      
│   └── Rental.java    
├── services/
│   ├── ClientService.java
│   ├── BikeService.java
│   └── RentalService.java
└── utils/
    ├── InputUtils.java
    └── DateUtils.java
```

## Principes POO Appliqués
- 📦 **Encapsulation**: Toutes les données sont privées et accessibles via des getters/setters
- 🔄 **Composition**: Les services gèrent les relations entre les objets
- 📝 **Responsabilité unique**: Chaque classe a une responsabilité bien définie
- 🛠 **Réutilisabilité**: Utilisation de classes utilitaires pour le code commun

## Comment Utiliser
1. Clonez le repository
2. Compilez le projet : `javac src/Main.java`
3. Exécutez : `java src.Main`

## Notes Techniques
- Java 11+ requis
- Pas de dépendances externes
- Données de test incluses dans `initializeData()`

---

🦊 **Fun Fact**: Pendant que vous lisez ce README, un renard quelque part essaie probablement de louer un vélo avec une fausse carte d'identité. Malheureusement, notre système de validation est trop malin pour lui... ou peut-être qu'il a juste oublié son portefeuille dans son autre fourrure ! 🦊