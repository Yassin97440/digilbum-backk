# DigilBum Backend

## 📝 Description

Backend robuste et scalable pour l'application DigilBum, développé avec Spring Boot. Ce service gère toute la logique métier, l'authentification, la gestion des fichiers et les interactions avec la base de données.

## ✨ Fonctionnalités Actuelles

### Authentification et Sécurité
- Authentification JWT
- Gestion des rôles et permissions
- Sécurité des endpoints avec Spring Security
- Protection contre les attaques CSRF et XSS
- Création de compte et Login
- Sauvegarde de photos sur le serveur et en BDD

### Gestion des Données
- API RESTful complète
- Gestion des utilisateurs et groupes
- Organisation des albums par événements
- Stockage et gestion des fichiers multimédias

### Infrastructure
- Base de données MySQL
- Disponibilité des fichiers avec Nginx
- Gestion des uploads avec FormData

## 🚀 Fonctionnalités à Venir

### Améliorations Techniques
- Optimisation des requêtes SQL
- Logging d'erreurs par mail
- Monitoring des performances
- Système de backup automatique

### Nouvelles Fonctionnalités
- Intégration avec des services cloud (AWS S3)
- Calendrier d'anniversaire pour les groupes

## 🛠️ Technologies Utilisées

- **Framework**: Spring Boot 3.x
- **Base de données**: MySQL
- **ORM**: Hibernate
- **Sécurité**: Spring Security
- **Tests**: JUnit, Mockito
- **CI/CD**: GitHub Actions

## ⚙️ Configuration

### Prérequis
- Java 17 ou supérieur
- MySQL 8.0
- Maven

### Installation
```bash
# Cloner le projet
git clone [url-du-projet]

# Installer les dépendances
mvn clean install

# Lancer l'application
mvn spring-boot:run
```

### Configuration de la Base de Données
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/digilbum
spring.datasource.username=your_username
spring.datasource.password=your_password
```

## 📚 Architecture

### Vue d'Ensemble du Système

DigilBum repose sur une architecture modulaire et distribuée, composée de plusieurs services spécialisés :

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│                 │     │                 │     │                 │
│  Frontend Nuxt  │◄───►│  Spring Boot    │◄───►│     MySQL      │
│                 │     │  (Backend)      │     │                 │
└─────────────────┘     └─────────────────┘     └─────────────────┘
                              │
                              ▼
                       ┌─────────────────┐
                       │                 │
                       │     Nginx       │
                       │                 │
                       └─────────────────┘
```

### Composants Principaux

#### 1. Frontend (Nuxt 3)
- **Rôle** : Interface utilisateur et expérience client
- **Responsabilités** :
  - Affichage des données
  - Gestion de l'interface utilisateur
  - Communication avec l'API Spring Boot
  - Gestion du state avec Pinia
  - Optimisation des performances côté client

#### 2. Backend (Spring Boot)
- **Rôle** : Logique métier et gestion des données
- **Responsabilités** :
  - Authentification et autorisation
  - Gestion des utilisateurs et groupes
  - Traitement des requêtes API
  - Gestion des albums et photos
  - Communication avec la base de données
  - Validation des données
  - Gestion des fichiers uploadés

#### 3. Base de Données (MySQL)
- **Rôle** : Stockage persistant des données
- **Responsabilités** :
  - Stockage des informations utilisateurs
  - Gestion des albums et photos
  - Stockage des métadonnées
  - Gestion des relations entre entités

#### 4. Serveur Web (Nginx)
- **Rôle** : Service de fichiers statiques
- **Responsabilités** :
  - Hébergement des images uploadées
  - Optimisation de la distribution des fichiers
  - Gestion du cache
  - Sécurisation de l'accès aux fichiers

### Communication entre les Composants

1. **Frontend ↔ Backend**
   - Communication via API REST
   - Authentification avec JWT
   - Échange de données en JSON
   - Gestion des erreurs et des timeouts

2. **Backend ↔ Base de Données**
   - Utilisation de Hibernate/JPA
   - Optimisation des requêtes
   - Gestion des transactions
   - Mise en cache des données fréquemment utilisées

3. **Backend ↔ Nginx**
   - Stockage des fichiers uploadés
   - Génération des URLs d'accès aux fichiers
   - Gestion des permissions d'accès

### Structure du Projet Spring Boot

```
src/
├── main/
│   ├── java/
│   │   └── com/digilbum/
│   │       ├── config/        # Configurations Spring
│   │       │   ├── SecurityConfig.java
│   │       │   ├── WebConfig.java
│   │       │   └── JwtConfig.java
│   │       ├── controller/    # Contrôleurs REST
│   │       │   ├── AuthController.java
│   │       │   ├── AlbumController.java
│   │       │   └── UserController.java
│   │       ├── model/         # Entités JPA
│   │       │   ├── User.java
│   │       │   ├── Album.java
│   │       │   └── Photo.java
│   │       ├── repository/    # Repositories Spring Data
│   │       │   ├── UserRepository.java
│   │       │   └── AlbumRepository.java
│   │       ├── service/       # Services métier
│   │       │   ├── AuthService.java
│   │       │   ├── UserService.java
│   │       │   └── FileService.java
│   │       └── security/      # Configuration de sécurité
│   │           ├── JwtTokenProvider.java
│   │           └── UserDetailsServiceImpl.java
│   └── resources/
│       ├── application.yml    # Configuration principale
│       └── application-dev.yml # Configuration de développement
```

### Flux de Données

1. **Upload d'Image**
   ```
   Frontend → Backend → Stockage fichier → Nginx → URL retournée → Frontend
   ```

2. **Authentification**
   ```
   Frontend → Backend → Vérification credentials → Génération JWT → Frontend
   ```

3. **Accès aux Données**
   ```
   Frontend → Backend → MySQL → Traitement → Frontend
   ```

## 🔒 Sécurité

- Authentification JWT
- Protection CSRF
- TO-DO: Validation des entrées
- TO-DO: Logging des actions sensibles
- TO-DO: Gestion des erreurs sécurisée

## 📄 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.


