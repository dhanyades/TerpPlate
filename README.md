**#FeedTheTurtle**

Looking to eat healthier at the dining hall but unsure what to choose or how to track your nutrition? Without nutrition labels on dining hall food, it can be challenging to know exactly what you're eating.

TerpPlate brings University of Maryland dining hall menu suggestions right to your fingertips, tailored specifically to your nutrition goals. Whether you're craving a veggie-packed lunch or a hearty dinner, TerpPlate curates meal ideas designed to meet your preferences with balanced and healthy options!

**Overview**
TerpPlate is designed to generate healthy meal ideas based on available dining hall options, making it easier than ever for busy college students to select balanced, nutritious meals. Users can specify their calorie, sugar, sodium, and protein preferences, and TerpPlate will generate a meal suggestion that meets those goals. With daily data on all available dining hall foods, each meal is curated specifically to what’s being served that day, so you can always plan your meals accordingly. 

**Key Features**
1. Personalized Meal Generation: Specify your nutrition goals for calories, sugar, sodium, and protein, and let TerpPlate’s AI technology craft a balanced meal from that day’s dining hall menu. Each suggestion includes a comprehensive nutritional breakdown, empowering you to choose meals that best support your health goals.
2. Nutrition Facts Repository: All nutritional information for dining hall items is ingested daily, ensuring that meal suggestions are aligned with the current offerings and their nutritional profiles.
3. AI Chatbot Assistance: If you have any questions about the nutrition facts of different foods at the dining hall or general nutrition inquiries, our AI Chatbot is here to help! It has access to detailed nutritional information, making it a reliable source for your dietary questions.
4. Fun Nutrition Facts: Curious about intriguing nutrition insights? Generate a fun nutrition fact by clicking the "CLICK ME!" button, and discover random facts about the foods you love, adding an element of enjoyment to your healthy eating journey.

**How to Use TerpPlate**


- Set Your Nutrition Goals: Input your specific per-meal goals for calories, sugar, sodium, and protein, or let TerpPlate work with default values based on average national nutritional standards. If no values are entered, the model will use the following recommended defaults, which are aligned with an average 2,000-calorie daily intake:
  - Calories: 600 cal
  - Sugar: 600 mg
  - Sodium: 8 g
  - Protein: 30 g
- Generate a Meal: Click "Generate" to receive a balanced meal suggestion based on your input. TerpPlate will attempt to match your goals with dining hall menu items as closely as possible.

**How we built it:**
- Ingesting Food Information and Nutritional Data: We developed a Python web scraper to gather menu and nutritional data from UMD’s Dining Services website. This data is then organized into a structured format to feed into our AI model.
- Large Language Model (LLM) Integration: Using the Langchain4j framework and OpenAI’s GPT4oMini model, we implemented the AI components. The model uses data from dining hall menu via webscraping to generate balanced meal suggestions based on user preferences. Additionally, we implemented a Nutritional Chatbot using AI to provide helpful dietary guidance.
- Front-End Development: We designed the user interface with HTML and CSS, ensuring a clean, responsive experience for users.
- Backend and Build Tools: We used Spring Boot for our backend API development, providing a reliable infrastructure to handle user requests and data processing, and Maven for managing dependencies and project configuration.

