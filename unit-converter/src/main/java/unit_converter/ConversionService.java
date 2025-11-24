package unit_converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

// service class: handle conversion logic
@Service
public class ConversionService {

    private final Map<String, Double> lengthConversions = new HashMap<>();
    private final Map<String, Double> massConversions = new HashMap<>();
    private final Map<String, Double> temperatureOffsets = new HashMap<>();
    private final Map<String, Double> volumeConversions = new HashMap<>();
    private final Map<String, Double> timeConversions = new HashMap<>();
    private final Map<String, Double> powerConversions = new HashMap<>();

public Set<String> getLengthUnits() {
    return lengthConversions.keySet();
}     
public Set<String> getMassUnits() {
    return massConversions.keySet();
}     
public Set<String> getTemperatureUnits() {
    return temperatureOffsets.keySet();
}     
public Set<String> getVolumeUnits() {
    return volumeConversions.keySet();
}     
public Set<String> getTimeUnits() {
    return timeConversions.keySet();
}     
public Set<String> getPowerUnits() {
    return powerConversions.keySet();
}

    // constructor
    public ConversionService() {
        // initialize conversion factors if needed

        // using base unit meters for length
        lengthConversions.put("meters", 1.0);
        lengthConversions.put("feet", 0.3048);
        lengthConversions.put("kilometers", 1000.0);
        lengthConversions.put("miles", 1609.34);

        // using base unit kilograms for weight
        massConversions.put("kilograms", 1.0);
        massConversions.put("pounds", 0.453592);
        massConversions.put("grams", 0.001);
        massConversions.put("ounces", 0.0283495);

        // using base unit liters for volume      
        volumeConversions.put("liters", 1.0);
        volumeConversions.put("gallons", 3.78541);
        volumeConversions.put("cups", 0.24);
        volumeConversions.put("milliliters", 0.001);
        volumeConversions.put("teaspoons", 0.00492892);
        volumeConversions.put("tablespoons", 0.0147868);

        // using base unit seconds for time      
        timeConversions.put("seconds", 1.0);
        timeConversions.put("minutes", 60.0);
        timeConversions.put("hours", 3600.0);
        timeConversions.put("days", 86400.0);
        timeConversions.put("weeks", 604800.0);
        timeConversions.put("months", 2592000.0);
        timeConversions.put("years", 31536000.0);

        // using base unit kilowatts for power
        powerConversions.put("kilowatts", 1.0);
        powerConversions.put("horsepower", 0.7457);
        powerConversions.put("watts", 0.001);
        powerConversions.put("lumens", 0.0795775);
        powerConversions.put("amperes", 1.0);

        // using offsets for temperature      
        temperatureOffsets.put("celsius", 0.0);
        temperatureOffsets.put("fahrenheit", 32.0);
    }

    // convertUnits method takes ConversionRequestDTO and returns ConversionResponseDTO
    public ConversionResponseDTO convertUnits(ConversionRequestDTO requestDTO) {
        double value = requestDTO.getValue();
        String fromUnit = requestDTO.getFromUnit();
        String toUnit = requestDTO.getToUnit();

        double convertedValue = 0.0;
if (fromUnit == null || toUnit == null) {
    throw new IllegalArgumentException("Unit values cannot be null");
}
        if((fromUnit.equals("celsius") && toUnit.equals("fahrenheit"))) {
            convertedValue = (value * 9/5) + 32;
        } else if((fromUnit.equals("fahrenheit") && toUnit.equals("celsius"))) {
            convertedValue = (value - 32) * 5/9;
        } 

        // Length conversions
        // if both units are in lengthConversions map
        else if(lengthConversions.containsKey(fromUnit) && lengthConversions.containsKey(toUnit)) {
             // convert to base unit (meters) first and then to target unit from base unit
            double valueInMeters = value * lengthConversions.get(fromUnit); 
            convertedValue = valueInMeters / lengthConversions.get(toUnit);
        } 

        // Mass conversions
        else if(massConversions.containsKey(fromUnit) && massConversions.containsKey(toUnit)) {
            double valueInKilograms = value * massConversions.get(fromUnit);
            convertedValue = valueInKilograms / massConversions.get(toUnit);
        } 

        // Volume conversions
        else if(volumeConversions.containsKey(fromUnit) && volumeConversions.containsKey(toUnit)) {
            double valueInLiters = value * volumeConversions.get(fromUnit);
            convertedValue = valueInLiters / volumeConversions.get(toUnit);
        } 

        // Time conversions
        else if(timeConversions.containsKey(fromUnit) && timeConversions.containsKey(toUnit)) {
            double valueInSeconds = value * timeConversions.get(fromUnit);
            convertedValue = valueInSeconds / timeConversions.get(toUnit);
        } 
        
            // Power conversions
        else if(powerConversions.containsKey(fromUnit) && powerConversions.containsKey(toUnit)) {
            double valueInKilowatts = value * powerConversions.get(fromUnit);
            convertedValue = valueInKilowatts / powerConversions.get(toUnit);
        } else {
            throw new IllegalArgumentException("Unsupported conversion from " + fromUnit + " to " + toUnit);
        }

        // if(fromUnit.equals("meters") && toUnit.equals("feet")) {
        //       convertedValue = value * 3.28084;
        // } else if(fromUnit.equals("meters") && toUnit.equals("kilometers")) {
        //       convertedValue = value / 1000;
        // }else if(fromUnit.equals("feet") && toUnit.equals("meters")) {
        //       convertedValue = value / 3.28084;
        // } else if(fromUnit.equals("kilograms") && toUnit.equals("pounds")) {
        //       convertedValue = value * 2.20462;
        // } else if(fromUnit.equals("pounds") && toUnit.equals("kilograms")) {
        //       convertedValue = value / 2.20462;
        // } else if(fromUnit.equals("celsius") && toUnit.equals("fahrenheit")) {
        //       convertedValue = (value * 9/5) + 32;
        // } else if(fromUnit.equals("fahrenheit") && toUnit.equals("celsius")) {
        //       convertedValue = (value - 32) * 5/9;
        // } else if(fromUnit.equals("liters") && toUnit.equals("gallons")) {
        //       convertedValue = value * 0.264172;
        // } else if(fromUnit.equals("gallons") && toUnit.equals("liters")) {
        //       convertedValue = value / 0.264172;
        // } else if(fromUnit.equals("kilometers") && toUnit.equals("miles")) {
        //       convertedValue = value * 0.621371;
        // } else if(fromUnit.equals("miles") && toUnit.equals("kilometers")) {
        //       convertedValue = value / 0.621371;
        // } else if(fromUnit.equals("grams") && toUnit.equals("ounces")) {
        //       convertedValue = value * 0.035274;
        // } else if(fromUnit.equals("ounces") && toUnit.equals("grams")) {
        //       convertedValue = value / 0.035274;
        // } else if(fromUnit.equals("cups") && toUnit.equals("milliliters")) {
        //       convertedValue = value * 236.588;
        // } else if(fromUnit.equals("milliliters") && toUnit.equals("cups")) {
        //       convertedValue = value / 236.588;
        // } else if(fromUnit.equals("teaspoons") && toUnit.equals("milliliters")) {
        //       convertedValue = value * 4.92892;
        // } else if(fromUnit.equals("milliliters") && toUnit.equals("teaspoons")) {
        //       convertedValue = value / 4.92892;
        // } else if(fromUnit.equals("tablespoons") && toUnit.equals("milliliters")) {
        //       convertedValue = value * 14.7868;
        // } else if(fromUnit.equals("milliliters") && toUnit.equals("tablespoons")) {
        //       convertedValue = value / 14.7868;
        // } else if(fromUnit.equals("teaspoons") && toUnit.equals("tablespoons")) {
        //       convertedValue = value / 3;
        // } else if(fromUnit.equals("tablespoons") && toUnit.equals("teaspoons")) {
        //       convertedValue = value * 3;
        // } else if(fromUnit.equals("hours") && toUnit.equals("minutes")) {
        //       convertedValue = value * 60;
        // } else if(fromUnit.equals("minutes") && toUnit.equals("hours")) {
        //       convertedValue = value / 60;
        // } else if(fromUnit.equals("days") && toUnit.equals("hours")) {
        //       convertedValue = value * 24;
        // } else if(fromUnit.equals("hours") && toUnit.equals("days")) {
        //       convertedValue = value / 24;
        // } else if(fromUnit.equals("weeks") && toUnit.equals("days")) {
        //       convertedValue = value * 7;
        // } else if(fromUnit.equals("days") && toUnit.equals("weeks")) {
        //       convertedValue = value / 7;
        // } else if(fromUnit.equals("months") && toUnit.equals("days")) {
        //       convertedValue = value * 30;
        // } else if(fromUnit.equals("days") && toUnit.equals("months")) {
        //       convertedValue = value / 30;
        // } else if(fromUnit.equals("years") && toUnit.equals("days")) {
        //       convertedValue = value * 365;
        // } else if(fromUnit.equals("days") && toUnit.equals("years")) {
        //       convertedValue = value / 365;
        // } else if(fromUnit.equals("kilowatts") && toUnit.equals("horsepower")) {
        //       convertedValue = value * 1.34102;
        // } else if(fromUnit.equals("horsepower") && toUnit.equals("kilowatts")) {
        //       convertedValue = value / 1.34102;
        // } else if(fromUnit.equals("watts") && toUnit.equals("lumens")) {
        //       convertedValue = value * 12.57;
        // } else if(fromUnit.equals("lumens") && toUnit.equals("watts")) {
        //       convertedValue = value / 12.57;
        // } else if(fromUnit.equals("amperes") && toUnit.equals("coulombs_per_second")) {
        //       convertedValue = value * 1;
        // } else if(fromUnit.equals("coulombs_per_second") && toUnit.equals("amperes")) {
        //       convertedValue = value / 1;
        // } else if(fromUnit.equals(toUnit)) {
        //       convertedValue = value; // No conversion needed
        // } else {
        //       throw new IllegalArgumentException("Unsupported conversion from " + fromUnit + " to " + toUnit);
        // }
        return new ConversionResponseDTO(value, fromUnit, convertedValue, toUnit);
    }
}
