# Fantasy Premier League Draft Backend

A Spring Boot backend application that allows users to search for football players and build a fantasy draft team under real-world constraints like budget and position limits.

---

##  Features

### 🔍 Player Search API
- Filter players by:
    - Name (partial search)
    - Position (GK, DEF, MID, FWD)
    - Price range (`minPrice`, `maxPrice`)
    - Minimum total points
- Pagination and sorting support
- Dynamic filtering using **JPA Specifications**

---

### Draft Team Management
- Create draft teams with a fixed **£100.0 budget**
- Add players with rules:
    - No duplicate players
    - Maximum 15 players
    - Position limits:
        - GK: 2
        - DEF: 5
        - MID: 5
        - FWD: 3
    - Budget cannot be exceeded
- Remove players and automatically refund budget

---

### Backend Architecture
- Layered design:
    - Controller → Service → Repository
- DTO-based API responses (no entity exposure)
- Custom exception handling with `@ControllerAdvice`
- Transaction management using `@Transactional`

---

##  Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL (Dockerized)
- Maven
- Swagger / OpenAPI

---
