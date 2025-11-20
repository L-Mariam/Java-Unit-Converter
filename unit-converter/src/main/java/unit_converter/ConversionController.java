package unit_converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

// controller class: handle web requests
@Controller
public class ConversionController {

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/length")
    public String lengthForm(Model model) {
        model.addAttribute("conversionRequest", new ConversionRequestDTO());
        return "length"; // Thymeleaf template name return each time a reuqest is made
    }

    @PostMapping("/length")
    public String convertLength(@ModelAttribute ConversionRequestDTO conversionRequest, Model model) {
        ConversionResponseDTO response = conversionService.convertUnits(conversionRequest);
        model.addAttribute("response", response);
        return "length";
    }

    // Repeat for weight and temperature if desired
}
