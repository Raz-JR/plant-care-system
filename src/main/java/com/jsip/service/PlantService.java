package com.jsip.service;

import com.jsip.model.Plant;
import com.jsip.repository.PlantRepository;
import com.jsip.utils.FileUtilities;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PlantService implements InitializingBean {

    //public static final byte GREEN = 0;
    public static final byte LIGHT_GREEN = 1;
    public static final byte YELLOW = -1;
    public static final byte RED = -2;

    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public void saveFile(InputStream fileReader) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        InputStreamReader in = new InputStreamReader(fileReader, "UTF-8");
        Object parse = parser.parse(in);
        JSONArray jsonArray = (JSONArray) parse;
        storePlant(jsonArray);
    }

    public void storePlant(Object file) {
        JSONArray flowerList = (JSONArray) file;
        JSONObject currentObject;
        List<Plant> plantList = new ArrayList<>();
        Iterator<JSONObject> iterator = flowerList.iterator();
        while (iterator.hasNext()) {
            currentObject = iterator.next();
            Optional<Plant> plantToStore = plantRepository.findByName((String) currentObject.get("Name"));
            Plant plant = plantToStore.isPresent() ? plantToStore.get() : new Plant();
            plant.setWateringFrequency((String) currentObject.get("Watering frequency"));
            plant.setName((String) currentObject.get("Name"));
            plant.setLastWaterDate((String) currentObject.get("Last Watered Date"));
            decidePlantStatus(plant);
            plantList.add(plant);
            plantRepository.save(plant);
        }
    }

    public void decidePlantStatus(Plant plant) {
        int remainingWateringDay =
                FileUtilities.takeNumberOutOfString(plant.getWateringFrequency()) - FileUtilities.takeNumberOutOfString(plant.getLastWaterDate());

        if (remainingWateringDay < RED)
            plant.setStatus("Red");
        else if (remainingWateringDay <= YELLOW)
            plant.setStatus("Yellow");
        else if (remainingWateringDay <= LIGHT_GREEN)
            plant.setStatus("LightGreen");
        else
            plant.setStatus("Green");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        saveFile(new FileInputStream(new File("src/main/resources/PlantsWateringSchedule.json")));
    }
}
