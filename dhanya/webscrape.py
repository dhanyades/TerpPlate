import requests
from bs4 import BeautifulSoup

#navigate thru specific item

#replace later with links from list
url = 'https://nutrition.umd.edu/label.aspx?RecNumAndPort=126301*1'

# request HTTP for URL
response = requests.get(url)

# Check if the request was successful
if response.status_code == 200:
    print("Successfully accessed the page.")

    soup = BeautifulSoup(response.text, 'html.parser')

    # main section with nutrition
    nutrition_section = soup.find('div', class_='section section-umd-terp_basic_page section-ut_table')

    nutrition_data = {}

    nutrients = {
        'Total Fat' : 'Fat',
        'Total Carbohydrate' : "Carbs",
        'Sodium' : 'Sodium',
        'Protein' : 'Protein'

    }
    
    # parses the calories
    value = nutrition_section.find(string='Calories per serving').find_next('p').text.strip()
    nutrition_data['Calories'] = value


    # ignore
    value = nutrition_section.find().find()

    # ignore
    nutrfacts = nutrition_section.find_all('span', class_='nutfactstopnutrient')


    rows = nutrition_section.find_all('tr')

    for key, label in nutrients.items():

        for row in rows:
            curr_elt = row.find(string=key)
            if curr_elt:
                value = curr_elt.find_next('p').text.strip()
                nutrition_data[label] = value
            
    # from old push, only gets calories
    for key, label in nutrients.items():
        curr_elt = nutrition_section.find_all('tr')

        if curr_elt:
            value = curr_elt.find_next('p').text.strip()
            nutrition_data[label] = value
        else:
            nutrition_data[label] = 'Not found'

    # loop to print!!
    for key, value in nutrition_data.items():
            print(f"{key}: {value}")

    """
    rows = nutrition_section.find_all('tr')
    for row in rows:
        nutrients = row.find_all('span', class_='nutfactstopnutrient')

        for nutrient in nutrients:
            line = nutrient.get_text(strip=True)

            if ' ' in text:
                name, value = line.split(maxsplit=1)
                nutrition_data[name.strip()] = value.strip()
    """

        

else:
    print(f"Failed to access the page. Status code: {response.status_code}")

