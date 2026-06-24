# Basic Android Data Persistence

Simple Android application for managing a Movie catalog, that demonstrates different data persistence implementations using **Room** and **Realm** across different branches. 
And also this application implement the complete CRUD functions.

## Branches Overview

This project is organized into three main branches, each showcasing a different approach to data persistence:

- **`main`**: The base implementation using an in-memory repository.
- **`room`**: Implementation using the **Room Persistence Library**.
- **`realm`**: Implementation using the **Realm Kotlin SDK**.

---

## 1. Main Branch (In-Memory)

The `main` branch serves as the foundation. It uses a simple in-memory list to store data, which is lost whenever the application process is killed.

- **Model**: `Movie` is a standard Kotlin `data class`.
- **Persistence**: Data is stored in a `MutableList` within the `MovieRepository`.
- **ID Generation**: Uses `UUID.randomUUID().toString()`.

---

## 2. Room Implementation (`room` branch)

The `room` branch implements persistence using Google's Room library, which provides an abstraction layer over SQLite.

### Key Implementation Details:
- **Entity**: The `Movie` class is annotated with `@Entity` and `@PrimaryKey(autoGenerate = true)`.
- **DAO**: A `MovieDao` interface defines the database operations (Insert, Query, Update, Delete) using Room annotations.
- **Database**: `AppDatabase` extends `RoomDatabase` to provide the DAO instance.
- **Repository**: Updated to use `MovieDao` for all operations.

### Changes from `main`:
- **Dependencies**: Added `androidx.room` libraries and the KSP plugin.
- **Model Change**: `Movie` changed from a simple data class to a Room entity with an auto-generating `Long` ID.
- **Storage**: Data is persisted in a local SQLite database.

---

## 3. Realm Implementation (`realm` branch)

The `realm` branch utilizes the Realm Kotlin SDK, a mobile-first object database.

### Key Implementation Details:
- **RealmObject**: The `Movie` class inherits from `RealmObject`. It must have a zero-argument constructor and properties must be `var`.
- **DAO Pattern**: Uses a `MovieDao` interface and a `MovieDaoImpl` that interacts with a `Realm` instance.
- **ID Management**: Since Realm Kotlin doesn't support auto-incrementing IDs out of the box, the ID is manually managed (typically by finding the max ID and incrementing).
- **Configuration**: A `Realm` instance is configured and opened, then passed to the repository/DAO.

### Changes from `main`:
- **Dependencies**: Added the `io.realm.kotlin` plugin and library dependencies.
- **Model Change**: `Movie` changed from a `data class` to a regular `class` extending `RealmObject`. The ID type changed from `String` to `Long`.
- **Storage**: Data is persisted in Realm's proprietary database format.

---

## How to Switch Branches

To explore a specific implementation, use the following git commands:

```bash
# To see Room implementation
git checkout room

# To see Realm implementation
git checkout realm

# To go back to the base version
git checkout main
```
