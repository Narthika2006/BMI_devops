package com.example.bmi_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BmiController {

    // Display BMI form
    @GetMapping("/")
    public String showForm() {
        return "bmiForm";  // loads templates/bmiForm.html
    }

    // Process form submission
    @PostMapping("/calculate")
    public String calculateBmi(@RequestParam("weight") double weight,
                               @RequestParam("height") double heightCm,
                               Model model) {
        System.out.println("DEBUG: weight = " + weight);
        System.out.println("DEBUG: height in cm = " + heightCm);

        // Convert cm to meters
        double height = heightCm / 100.0;

        // Calculate BMI
        double bmi = weight / (height * height);

        // Determine BMI category
        String category;
        if (bmi < 18.5) {
            category = "Underweight";
        } else if (bmi < 24.9) {
            category = "Normal weight";
        } else if (bmi < 29.9) {
            category = "Overweight";
        } else {
            category = "Obese";
        }

        // Pass values to the view
        model.addAttribute("bmi", String.format("%.2f", bmi));
        model.addAttribute("category", category);

        return "bmiResult";  // loads templates/bmiResult.html
    }
}
