package com.pl.prem_league_data.Repository;
import com.pl.prem_league_data.Entity.Player;
import com.pl.prem_league_data.Entity.Position;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PlayerCsvLoader implements CommandLineRunner {

    private final PlayerRepository repo;

    public PlayerCsvLoader(PlayerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        ClassPathResource resource = new ClassPathResource("cleaned_players.csv");

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {

            String headerLine = br.readLine();
            if (headerLine == null) return;

            Map<String, Integer> idx = headerIndexMap(headerLine);

            List<Player> batch = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                String[] cols = line.split(",", -1);

                String first = get(cols, idx, "first_name");
                String second = get(cols, idx, "second_name");
                String name = (first + " " + second).trim();
                if (name.isBlank()) continue;

                String elementType = get(cols, idx, "element_type");
                Position position = mapPosition(elementType);

                Integer nowCost = parseInt(get(cols, idx, "now_cost"));
                BigDecimal price = nowCost == null ? null
                        : new BigDecimal(nowCost).divide(BigDecimal.TEN);

                Integer minutes = parseInt(get(cols, idx, "minutes"));
                Integer goals = parseInt(get(cols, idx, "goals_scored"));
                Integer assists = parseInt(get(cols, idx, "assists"));
                Integer totalPoints = parseInt(get(cols, idx, "total_points"));

                if (repo.existsByNameAndPosition(name, position)) continue;

                batch.add(new Player(name, position, minutes, goals, assists, totalPoints, price));

                if (batch.size() >= 200) {
                    repo.saveAll(batch);
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) repo.saveAll(batch);

            System.out.println("[CSV Loader] Players in DB: " + repo.count());
        }
    }

    private Map<String, Integer> headerIndexMap(String headerLine) {
        String[] headers = headerLine.split(",", -1);
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            map.put(headers[i].trim().toLowerCase(), i);
        }
        return map;
    }

    private String get(String[] cols, Map<String, Integer> idx, String colName) {
        Integer i = idx.get(colName.toLowerCase());
        if (i == null) throw new IllegalArgumentException("CSV missing column: " + colName);
        return i < cols.length ? cols[i].trim() : "";
    }

    private Position mapPosition(String elementType) {
        if (elementType == null) throw new IllegalArgumentException("element_type is missing");

        String v = elementType.trim().toUpperCase();

        return switch (v) {
            case "GK", "GKP", "GOALKEEPER" -> Position.GK;
            case "DEF", "DF", "DEFENDER"   -> Position.DEF;
            case "MID", "MF", "MIDFIELDER" -> Position.MID;
            case "FWD", "FW", "FORWARD"    -> Position.FWD;
            default -> throw new IllegalArgumentException("Unknown element_type: " + elementType);
        };
    }


    private Integer parseInt(String s) {
        if (s == null) return null;
        s = s.trim();
        if (s.isEmpty()) return null;
        return Integer.valueOf(s);
    }
}
