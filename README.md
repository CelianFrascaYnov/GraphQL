# TP GraphQL

Réalisation d'une API GraphQL avec Spring Boot dans le cadre d'un TP pour le cours de WebService permettant la gestion d'une liste de jeux vidéo 

## Prérequis

- Docker
- Docker Compose (si vous utilisez un fichier `docker-compose.yml`)

## Installation et Lancement

### Cloner le Répertoire

Clonez le dépôt Git :

```bash
git clone https://github.com/CelianFrascaYnov/GraphQL.git
cd GraphQL
mvn clean install
```

## Construire l'Image Docker et Démarrer avec Docker Compose

Construisez l'image Docker et lancer votre application avec :
```bash
docker-compose up --build
```

Votre application devrait maintenant être accessible à l'adresse `http://localhost:8080`.

## Utilisation

Vous pouvez maintenant accéder à l'API GraphQL à `http://localhost:8080/graphql`

Pour effectuer des requêtes GraphQL, vous pouvez utiliser des outils comme GraphQL Playground, Postman, etc.

### Exemple d'utilisation avec Postman

