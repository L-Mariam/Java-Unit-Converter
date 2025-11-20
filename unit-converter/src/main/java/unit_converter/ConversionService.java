package unit_converter;

import org.springframework.stereotype.Service;
// service class: handle conversion logic
@Service
public class ConversionService {

      // convertUnits method 
      public ConversionResponseDTO convertUnits(ConversionRequestDTO requestDTO) {
            double value = requestDTO.getValue();
            String fromUnit = requestDTO.getFromUnit();
            String toUnit = requestDTO.getToUnit();

            double convertedValue = 0.0;

            if(fromUnit.equals("meters") && toUnit.equals("feet")) {
                  convertedValue = value * 3.28084;
            } else if(fromUnit.equals("feet") && toUnit.equals("meters")) {
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
            }
            return new ConversionResponseDTO(value, fromUnit, convertedValue, toUnit);

      }
}