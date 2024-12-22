package com.example.petbook;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EducationalContentActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);


        tvContent = findViewById(R.id.tvContent);

        Button btnUnderstandingDogNutrition = findViewById(R.id.btnUnderstandingDogNutrition);
        Button btnTipsForFeedingYourDog = findViewById(R.id.btnTipsForFeedingYourDog);
        Button btnHealthyTreatOptions = findViewById(R.id.btnHealthyTreatOptions);
        Button btnMonitoringYourDogsHealth = findViewById(R.id.btnMonitoringYourDogsHealth);

        btnUnderstandingDogNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderstandingDogNutritionContent();
            }
        });

        btnTipsForFeedingYourDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipsForFeedingYourDogContent();
            }
        });

        btnHealthyTreatOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHealthyTreatOptionsContent();
            }
        });

        btnMonitoringYourDogsHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonitoringYourDogsHealthContent();
            }
        });
    }

    private void showUnderstandingDogNutritionContent() {
        String content = "1. Importance of a Balanced Diet:\n\n" +
                "A balanced diet is crucial for maintaining your dog's overall health. It provides the necessary nutrients to support their growth, energy levels, and immune system.\n\n" +
                "2. Essential Nutrients:\n\n" +
                "Proteins: Vital for growth, repair, and maintaining healthy muscles. Good sources include meat, fish, and eggs.\n" +
                "Carbohydrates: Provide energy and should come from digestible sources like grains and vegetables.\n" +
                "Fats: Important for energy, skin health, and vitamin absorption. Include healthy fats like fish oil and chicken fat.\n" +
                "Vitamins and Minerals: Essential for various bodily functions. Ensure a mix of fruits, vegetables, and supplements if needed.\n\n" +
                "3. Age-Specific Nutrition:\n\n" +
                "Puppies: Require more protein and fat for growth. Choose specially formulated puppy food.\n" +
                "Adults: Balanced diet with moderate protein and fat levels.\n" +
                "Seniors: Lower in calories and higher in fiber to support slower metabolism and digestive health.\n\n" ;
        tvContent.setText(content);
    }

    private void showTipsForFeedingYourDogContent() {
        String content = "1. Consistent Feeding Schedule:\n\n" +
                "Feed your dog at the same times each day to help regulate digestion and prevent overeating.\n\n" +
                "2. Portion Control:\n\n" +
                "Follow the feeding guidelines on the dog food packaging based on your dog's weight, age, and activity level.\n\n" +
                "3. Hydration:\n\n" +
                "Always provide fresh, clean water. Hydration is crucial for your dog’s health.\n\n" +
                "4. Avoid Human Foods:\n\n" +
                "Many human foods are toxic to dogs (e.g., chocolate, grapes, onions). Stick to dog-specific treats and foods.";
        tvContent.setText(content);
    }

    private void showHealthyTreatOptionsContent() {
        String content = "1. Fruits and Vegetables:\n\n" +
                "Safe options include apples (without seeds), carrots, blueberries, and green beans. These provide vitamins and are low in calories.\n\n" +
                "2. Homemade Treats:\n\n" +
                "Make your own dog treats using simple recipes with safe ingredients like peanut butter (xylitol-free), pumpkin, and oatmeal.";
        tvContent.setText(content);
    }

    private void showMonitoringYourDogsHealthContent() {
        String content = "1. Regular Vet Check-ups:\n\n" +
                "Schedule regular visits to monitor your dog’s health and adjust their diet as needed.\n\n" +
                "2. Weight Management:\n\n" +
                "Keep an eye on your dog's weight. Adjust their diet and exercise routine if they start gaining or losing weight unexpectedly.\n\n" +
                "3. Signs of Nutritional Deficiency:\n\n" +
                "Watch for signs like dull coat, lethargy, digestive issues, or excessive itching. Consult your vet if you notice any of these symptoms.";
        tvContent.setText(content);
    }
}
