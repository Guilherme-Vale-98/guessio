# Guessio 

**Guess the video game from a pixelated screenshot.**

A new match picks a random game and renders its screenshot heavily pixelated on an HTML canvas. You have **5 hearts**: every wrong guess costs one and de-pixelates another region of the image. Guess the game before your hearts run out.

## How it works

- **Match flow:** the backend creates a match with a random answer drawn from the games collection. Guesses are validated server-side and the match state (attempts, status) is persisted, so a match survives a page refresh.
- **Pixelation reveal:** the frontend renders the screenshot on a canvas and animates region-by-region de-pixelation as attempts are used.
- **Autocomplete:** guesses are picked from the real game-name list, so no typo frustration.
- **Game data:** a seeding script imports games (name, release, rating, genres, platforms, screenshots, developers, publishers) from the [RAWG Video Games Database API](https://rawg.io/apidocs) into MongoDB.

## Tech stack

| Layer | Tech |
|---|---|
| Backend | Java, Spring Boot 3 (Web, Data MongoDB) |
| Database | MongoDB |
| Frontend | Angular 18 (standalone components), Tailwind CSS, Canvas API |
| Data source | RAWG API |

## API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/v1/match` | Start a match (random answer) |
| POST | `/api/v1/matches/{id}` | Submit a guess for a match |
| GET | `/api/v1/games` | List games (used for autocomplete) |

## Running locally

**Backend** — create `backend/src/main/resources/application.properties` (not committed):

```properties
spring.application.name=guessio
server.port=8000
spring.data.mongodb.uri=<your MongoDB connection string>
rawgAPI=<your RAWG API key>
```

```bash
cd backend
./mvnw spring-boot:run    # runs on http://localhost:8000
```

**Frontend:**

```bash
cd frontend
npm install
npm start                 # runs on http://localhost:4200
```
