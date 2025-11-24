package unit_converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;

@Controller
public class ConversionController {

    @Autowired
    private ConversionService convertService;

    @GetMapping("/converter")
    public String showForm(Model model) {               
         System.out.println("Length units: " + convertService.getLengthUnits());
        ConversionRequestDTO conversionRequest = new ConversionRequestDTO();

        // set default units to avoid null
        conversionRequest.setFromUnit("meters");
        conversionRequest.setToUnit("kilometers");
        
        model.addAttribute("conversionRequest", conversionRequest);
        
        // dynamically populate unit options from ConversionService
        model.addAttribute("lengthUnits", new ArrayList<>(convertService.getLengthUnits()));
        model.addAttribute("massUnits", new ArrayList<>(convertService.getMassUnits()));
        model.addAttribute("temperatureUnits", new ArrayList<>(convertService.getTemperatureUnits()));
        model.addAttribute("volumeUnits", new ArrayList<>(convertService.getVolumeUnits()));
        model.addAttribute("timeUnits", new ArrayList<>(convertService.getTimeUnits()));
        model.addAttribute("powerUnits", new ArrayList<>(convertService.getPowerUnits()));

        return "converter";
    }

    @PostMapping("/converter")
    public String convertLength(@ModelAttribute ConversionRequestDTO conversionRequest, Model model) {
        try {
            ConversionResponseDTO convertedResponse = convertService.convertUnits(conversionRequest);
            model.addAttribute("response", convertedResponse);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }
        
        // re-add units to the model for the form
        model.addAttribute("lengthUnits", new ArrayList<>(convertService.getLengthUnits()));
        model.addAttribute("massUnits", new ArrayList<>(convertService.getMassUnits()));
        model.addAttribute("temperatureUnits", new ArrayList<>(convertService.getTemperatureUnits()));
        model.addAttribute("volumeUnits", new ArrayList<>(convertService.getVolumeUnits()));
        model.addAttribute("timeUnits", new ArrayList<>(convertService.getTimeUnits()));
        model.addAttribute("powerUnits", new ArrayList<>(convertService.getPowerUnits()));
        
        return "converter";
    }
}