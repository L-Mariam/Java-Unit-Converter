package unit_converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class ConversionController {

    @Autowired
    private ConversionService convertService;

    @GetMapping("/converter")
    public String showForm(Model model) {

        model.addAttribute("conversionRequest", new ConversionRequestDTO());

        // dynamically populate unit options from ConversionService
        model.addAttribute("lengthUnits", List.of("Meters", "Kilometers", "Miles", "Feet"));
        model.addAttribute("massUnits", List.of("Kilograms", "Grams", "Pounds", "Ounces"));
        model.addAttribute("volumeUnits", List.of("Liters", "Milliliters", "Gallons", "Teaspoons", "Tablespoons", "Cups"));
        model.addAttribute("temperatureUnits", List.of("Celsius", "Fahrenheit", "Kelvin"));
        model.addAttribute("timeUnits", List.of("Seconds", "Minutes", "Hours", "Days", "Weeks", "Months", "Years"));
        model.addAttribute("powerUnits", List.of("Watts", "Kilowatts", "Horsepower", "Lumens", "Amperes"));

        return "converter";
    }

    // handle form submission
    @PostMapping("/converter")
    public String convertLength(@ModelAttribute ConversionRequestDTO conversionRequest, Model model) {

        ConversionResponseDTO converted = convertService.convertUnits(conversionRequest);
        DecimalFormat df = new DecimalFormat("#.##");
        String formattedValue = df.format(converted.getConvertedValue()); // as String, hides .0

        ConversionResponseDTO response = new ConversionResponseDTO(
                conversionRequest.getValue(),
                conversionRequest.getFromUnit(),
                converted.getConvertedValue(),
                conversionRequest.getToUnit()
        );


    // Add original form object back for Thymeleaf binding
    model.addAttribute("conversionRequest", conversionRequest);

    // Optional: formatted string for display
    String formattedOriginal = df.format(conversionRequest.getValue());
    model.addAttribute("formattedOriginal", formattedOriginal);

    model.addAttribute("response", response);
    model.addAttribute("formattedValue", formattedValue);
    
        // re-add units to the model for the form
        model.addAttribute("lengthUnits", convertService.getLengthUnits().keySet());
        model.addAttribute("massUnits", convertService.getMassUnits().keySet());
        model.addAttribute("volumeUnits", convertService.getVolumeUnits().keySet());
        model.addAttribute("timeUnits", convertService.getTimeUnits().keySet());
        model.addAttribute("powerUnits", convertService.getPowerUnits().keySet());
        // model.addAttribute("temperatureUnits", List.of("celsius", "fahrenheit")); // add Kelvin if you implement

        return "converter";
    }
}
