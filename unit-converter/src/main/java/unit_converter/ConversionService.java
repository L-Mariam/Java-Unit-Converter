package unit_converter;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

// service class: handle conversion logic
@Service
public class ConversionService {

    private final Map<String, Double> length = new HashMap<>();
    private final Map<String, Double> mass = new HashMap<>();
    private final Map<String, Double> volume = new HashMap<>();
    private final Map<String, Double> time = new HashMap<>();
    private final Map<String, Double> power = new HashMap<>();

    public Map<String, Double> getLengthUnits() {
        return length;
    }
    public Map<String, Double> getMassUnits() {
        return mass;
    }
    public Map<String, Double> getVolumeUnits() {
        return volume;
    }
    public Map<String, Double> getTimeUnits() {
        return time;        
    }
    public Map<String, Double> getPowerUnits() {
        return power;        
    }

    public ConversionService() {
length.put("meters", 1.0);
length.put("feet", 0.3048);
length.put("kilometers", 1000.0);
length.put("miles", 1609.34);

mass.put("kilograms", 1.0);
mass.put("milligrams", 0.000001);
mass.put("grams", 0.001);
mass.put("pounds", 0.453592);
mass.put("ounces", 0.0283495);

volume.put("liters", 1.0);
volume.put("milliliters", 0.001);
volume.put("gallons", 3.78541);
volume.put("cups", 0.24);
volume.put("teaspoons", 0.00492892);
volume.put("tablespoons", 0.0147868);

time.put("seconds", 1.0);
time.put("minutes", 60.0);
time.put("hours", 3600.0);
time.put("days", 86400.0);
time.put("weeks", 604800.0);
time.put("months", 2592000.0);
time.put("years", 31536000.0);

power.put("kilowatts", 1.0);
power.put("watts", 0.001);
power.put("horsepower", 0.7457);
power.put("lumens", 0.00134102);
power.put("amperes", 1.0);
}


    public ConversionResponseDTO convertUnits(ConversionRequestDTO req) {

        double v = req.getValue();
        String from = req.getFromUnit().toLowerCase();
        String to = req.getToUnit().toLowerCase();

        // same unit
        if (from.equals(to)) {
            return new ConversionResponseDTO(v, from, v, to);
        }

        // temperature
        if (from.equals("celsius") && to.equals("fahrenheit")) {
            return new ConversionResponseDTO(v, from, (v * 9/5) + 32, to);
        }

        if (from.equals("fahrenheit") && to.equals("celsius")) {
            return new ConversionResponseDTO(v, from, (v - 32) * 5/9, to);
        }

        // length
        if (length.containsKey(from) && length.containsKey(to)) {
            double base = v * length.get(from);
            return new ConversionResponseDTO(v, from, base / length.get(to), to);
        }

        // mass
        if (mass.containsKey(from) && mass.containsKey(to)) {
            double base = v * mass.get(from);
            return new ConversionResponseDTO(v, from, base / mass.get(to), to);
        }

        // volume
        if (volume.containsKey(from) && volume.containsKey(to)) {
            double base = v * volume.get(from);
            return new ConversionResponseDTO(v, from, base / volume.get(to), to);
        }

        // time
        if (time.containsKey(from) && time.containsKey(to)) {
            double base = v * time.get(from);
            return new ConversionResponseDTO(v, from, base / time.get(to), to);
        }

        // power
        if (power.containsKey(from) && power.containsKey(to)) {
            double base = v * power.get(from);
            return new ConversionResponseDTO(v, from, base / power.get(to), to);
        }

        throw new IllegalArgumentException("Unsupported conversion " + from + " → " + to);
    }

}


                                    /*  if(fromUnit.equals("meters") && toUnit.equals("feet")) {
              convertedValue = value * 3.28084;
        } else if(fromUnit.equals("meters") && toUnit.equals("kilometers")) {
              convertedValue = value / 1000;
        }else if(fromUnit.equals("feet") && toUnit.equals("meters")) {
              convertedValue = value / 3.28084;
        } else if(fromUnit.equals("kilograms") && toUnit.equals("pounds")) {
              convertedValue = value * 2.20462;
        } else if(fromUnit.equals("pounds") && toUnit.equals("kilograms")) {
              convertedValue = value / 2.20462;
        } else if(fromUnit.equals("celsius") && toUnit.equals("fahrenheit")) {
              convertedValue = (value * 9/5) + 32;
        } else if(fromUnit.equals("fahrenheit") && toUnit.equals("celsius")) {
              convertedValue = (value - 32) * 5/9;
        } else if(fromUnit.equals("liters") && toUnit.equals("gallons")) {
              convertedValue = value * 0.264172;
        } else if(fromUnit.equals("gallons") && toUnit.equals("liters")) {
              convertedValue = value / 0.264172;
        } else if(fromUnit.equals("kilometers") && toUnit.equals("miles")) {
              convertedValue = value * 0.621371;
        } else if(fromUnit.equals("miles") && toUnit.equals("kilometers")) {
              convertedValue = value / 0.621371;
        } else if(fromUnit.equals("grams") && toUnit.equals("ounces")) {
              convertedValue = value * 0.035274;
        } else if(fromUnit.equals("ounces") && toUnit.equals("grams")) {
              convertedValue = value / 0.035274;
        } else if(fromUnit.equals("cups") && toUnit.equals("milliliters")) {
              convertedValue = value * 236.588;
        } else if(fromUnit.equals("milliliters") && toUnit.equals("cups")) {
              convertedValue = value / 236.588;
        } else if(fromUnit.equals("teaspoons") && toUnit.equals("milliliters")) {
              convertedValue = value * 4.92892;
        } else if(fromUnit.equals("milliliters") && toUnit.equals("teaspoons")) {
              convertedValue = value / 4.92892;
        } else if(fromUnit.equals("tablespoons") && toUnit.equals("milliliters")) {
              convertedValue = value * 14.7868;
        } else if(fromUnit.equals("milliliters") && toUnit.equals("tablespoons")) {
              convertedValue = value / 14.7868;
        } else if(fromUnit.equals("teaspoons") && toUnit.equals("tablespoons")) {
              convertedValue = value / 3;
        } else if(fromUnit.equals("tablespoons") && toUnit.equals("teaspoons")) {
              convertedValue = value * 3;
        } else if(fromUnit.equals("hours") && toUnit.equals("minutes")) {
              convertedValue = value * 60;
        } else if(fromUnit.equals("minutes") && toUnit.equals("hours")) {
              convertedValue = value / 60;
        } else if(fromUnit.equals("days") && toUnit.equals("hours")) {
              convertedValue = value * 24;
        } else if(fromUnit.equals("hours") && toUnit.equals("days")) {
              convertedValue = value / 24;
        } else if(fromUnit.equals("weeks") && toUnit.equals("days")) {
              convertedValue = value * 7;
        } else if(fromUnit.equals("days") && toUnit.equals("weeks")) {
              convertedValue = value / 7;
        } else if(fromUnit.equals("months") && toUnit.equals("days")) {
              convertedValue = value * 30;
        } else if(fromUnit.equals("days") && toUnit.equals("months")) {
              convertedValue = value / 30;
        } else if(fromUnit.equals("years") && toUnit.equals("days")) {
              convertedValue = value * 365;
        } else if(fromUnit.equals("days") && toUnit.equals("years")) {
              convertedValue = value / 365;
        } else if(fromUnit.equals("kilowatts") && toUnit.equals("horsepower")) {
              convertedValue = value * 1.34102;
        } else if(fromUnit.equals("horsepower") && toUnit.equals("kilowatts")) {
              convertedValue = value / 1.34102;
        } else if(fromUnit.equals("watts") && toUnit.equals("lumens")) {
              convertedValue = value * 12.57;
        } else if(fromUnit.equals("lumens") && toUnit.equals("watts")) {
              convertedValue = value / 12.57;
        } else if(fromUnit.equals("amperes") && toUnit.equals("coulombs_per_second")) {
              convertedValue = value * 1;
        } else if(fromUnit.equals("coulombs_per_second") && toUnit.equals("amperes")) {
              convertedValue = value / 1;
        } else if(fromUnit.equals(toUnit)) {
              convertedValue = value; // No conversion needed
        } else {
              throw new IllegalArgumentException("Unsupported conversion from " + fromUnit + " to " + toUnit);
        } */
