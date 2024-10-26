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

        'Calories per serving' : 'Calories',
        'Total Fat' : 'Fat',
        'Total Sugars' : 'Sugars',
        'Protein' : 'Protein'

    }

    for key, label in nutrients.items():
        curr_elt = nutrition_section.find(text=key)

        if curr_elt:
            value = curr_elt.find_next('p').text.strip()
            nutrition_data[label] = value
        else:
            nutrition_data[label] = 'Not found'

    for key, value in nutrition_data.items():
        print(f"{key}: {value}")

else:
    print(f"Failed to access the page. Status code: {response.status_code}")

