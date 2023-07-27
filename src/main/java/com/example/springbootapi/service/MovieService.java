package com.example.springbootapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MovieService {




    public Object buildConsecutivePrizeIntervalObject(List<List<Object>> consecutiveWinners) {

        Map<String, List<Map<String, Object>>> prizesInterval = new HashMap<>();

        consecutiveWinners.forEach(winner -> {
            Map<String, Object> winnerMap = new HashMap<>();
            winnerMap.put("producer", winner.get(0));
            winnerMap.put("interval", (Integer) winner.get(2) - (Integer)winner.get(1));
            winnerMap.put("previousWin", winner.get(1));
            winnerMap.put("followingWin", winner.get(2));

            if(prizesInterval.containsKey("min")) {
                if((Integer) winnerMap.get("interval") < (Integer) prizesInterval.get("min").get(0).get("interval")) {
                    prizesInterval.put("min", new ArrayList<>(){{add(winnerMap);}});
                } else if((Integer) winnerMap.get("interval") == (Integer) prizesInterval.get("min").get(0).get("interval")) {
                    prizesInterval.get("min").add(winnerMap);
                }
            } else {
                prizesInterval.put("min", new ArrayList<>(){{add(winnerMap);}});
            }

            if(prizesInterval.containsKey("max")) {
                if((Integer) winnerMap.get("interval") > (Integer) prizesInterval.get("max").get(0).get("interval")) {
                    prizesInterval.put("max", new ArrayList<>(){{add(winnerMap);}});
                } else if((Integer) winnerMap.get("interval") == (Integer) prizesInterval.get("max").get(0).get("interval")) {
                    prizesInterval.get("max").add(winnerMap);
                }
            } else {
                prizesInterval.put("max", new ArrayList<>(){{add(winnerMap);}});
            }
        });

        return prizesInterval;
    }
}
