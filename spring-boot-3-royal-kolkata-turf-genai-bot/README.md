
export LOCAL_KEYCLOAK_CLIENT_ID=admin-cli
export LOCAL_KEYCLOAK_CLIENT_SECRET=na
export LOCAL_KEYCLOAK_TOKEN_ENDPOINT=http://localhost:8080/realms/master/protocol/openid-connect/token
export LOCAL_KEYCLOAK_USERNAME=system
export LOCAL_KEYCLOAK_PASSWORD=K3ycloak@dmin4fe007c7fd5761ba

export STACK_KEYCLOAK_CLIENT_ID=admin-cli
export STACK_KEYCLOAK_CLIENT_SECRET=na
export STACK_KEYCLOAK_TOKEN_ENDPOINT=***
export STACK_KEYCLOAK_USERNAME=system
export STACK_KEYCLOAK_PASSWORD=***
export KEYCLOAK_CLIENT_URL=***

@GetMapping("/total-horses")
public ResponseEntity<Integer> getTotalHorses() {
return ResponseEntity.ok(horseService.getTotalHorses());
}

    @GetMapping("/horses-by-category")
    public ResponseEntity<List<Map<String, Object>>> getHorsesByCategory() {
        return ResponseEntity.ok(horseService.getHorsesByCategory());
    }

    @GetMapping("/total-races")
    public ResponseEntity<Integer> getTotalRaces() {
        return ResponseEntity.ok(horseService.getTotalRaces());
    }

    @GetMapping("/races-won-by-horse")
    public ResponseEntity<List<Map<String, Object>>> getRacesWonByHorse() {
        return ResponseEntity.ok(horseService.getRacesWonByHorse());
    }

    @GetMapping("/total-jockeys")
    public ResponseEntity<Integer> getTotalJockeys() {
        return ResponseEntity.ok(horseService.getTotalJockeys());
    }

    @GetMapping("/jockey-race-count")
    public ResponseEntity<List<Map<String, Object>>> getJockeyRaceCount() {
        return ResponseEntity.ok(horseService.getJockeyRaceCount());
    }

    @GetMapping("/fastest-race-time")
    public ResponseEntity<Map<String, Object>> getFastestRaceTime() {
        return ResponseEntity.ok(horseService.getFastestRaceTime());
    }

    @GetMapping("/most-expensive-party-booking")
    public ResponseEntity<Map<String, Object>> getMostExpensivePartyBooking() {
        return ResponseEntity.ok(horseService.getMostExpensivePartyBooking());
    }



    @GetMapping("/food-and-drink-count")
    public ResponseEntity<List<Map<String, Object>>> getFoodAndDrinksCount() {
        return ResponseEntity.ok(horseService.getFoodAndDrinksCount());
    }

    @GetMapping("/total-club-members")
    public ResponseEntity<Integer> getTotalClubMembers() {
        return ResponseEntity.ok(horseService.getTotalClubMembers());
    }