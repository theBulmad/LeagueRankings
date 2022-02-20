# LeagueRankings
Java CLI to generate a log table based on match results

## Instructions
### Note
* For windows machines use `mvnw` when running the maven commands
* For OSX/*nix machines use `./mvnw`
### 1. Clone repo
`git clone https://github.com/theBulmad/LeagueRankings.git`

### 2. Build
`cd LeagueRankings`

* Windows : `mvnw clean package`
* OSX/*nix : `./mvnw clean package`

### 2.1 Possible Issues
> ./mvnw clean package - permission denied 

> Fix: `chmod +x mvnw` then test with `./mvnw -v` (can take 5 mins on first try)


### 3. Run
`java -jar target/LeagueRankings-0.1.0.jar` (or whatever the version number is post successful build)

### 4. Run Unit Tests
`mvnw test`

## Notes
* Built using java 8 (jdk 1.8)
