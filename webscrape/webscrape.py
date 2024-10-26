import requests
from bs4 import BeautifulSoup
import json

# Navigate to the menu
url = 'https://nutrition.umd.edu/'
response = requests.get(url)

# Check if the request was successful
if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')
    menu_items = {}

    # Helper to parse macros
    def parse_macros(item_url):
        response = requests.get(item_url)
        if response.status_code == 200:
            soup = BeautifulSoup(response.text, 'html.parser')
            # Main section with nutrition
            nutrition_section = soup.find('div', class_='section section-umd-terp_basic_page section-ut_table')
            nutrition_data = {}
            nutrients = {
                'Total Sugars': 'Sugar',
                'Sodium': 'Sodium',
                'Protein': 'Protein'
            }
            if nutrition_section:
                calories = nutrition_section.find(string='Calories per serving')
                
                if calories:  # Check if calories exists
                    nutrition_data['Calories'] = calories.find_next('p').text.strip()

                     # Parses remaining nutrients
                    rows = nutrition_section.find_all('tr')
                    for row in rows:
                        cells = row.find_all('td')
                        for cell in cells:
                            nutrient_info = cell.find_all('span', class_='nutfactstopnutrient')
                            for nutrient in nutrient_info:
                                nutrient_text = nutrient.get_text(strip=True)
                                for key in nutrients:
                                    if key in nutrient_text:
                                        nutrient_value = nutrient_text.split('\xa0')[-1]
                                        nutrition_data[nutrients[key]] = nutrient_value
                
            return nutrition_data  # Ensure you return the data

    # Collect all links (for each food item)
    stations = soup.find('div', class_='container').find('div', class_='row').find('div', class_='section section-umd_terp_basic_page section-ut_tabs mt-0').find('div', class_='tab-content editor-content').find('div', class_='tab-pane fade active show')
    for station in stations.find_all('div', class_='card'):
        info = station.find('div', class_='card-body')
        for item in info.find_all('div', class_='row menu-item-row'):
            link_tag = item.find('a', class_='menu-item-name')
            if link_tag:  # Check if link_tag exists
                item_name = link_tag.text
                item_url = 'https://nutrition.umd.edu/' + link_tag['href']
                print(item_name)
                macros = parse_macros(item_url)
                
                print(macros)
                if macros:  # Ensure macros are returned
                    menu_items[item_name] = macros  # Add to dictionary

    # Write to JSON file
    with open('food.txt', 'w') as file:
        json.dump(menu_items, file, indent=4)

