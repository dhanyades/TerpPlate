import requests
from bs4 import BeautifulSoup

#navigate to the menu
url = 'https://nutrition.umd.edu/'
response = requests.get(url)
html_content = response.text

# Check if the request was successful
if response.status_code == 200:
    print("Successfully accessed the page.")
    page_content = response.text
    # You can now parse this content as needed
else:
    print(f"Failed to access the page. Status code: {response.status_code}")


#collect all links (for each food item)
menu_items = {}
soup = BeautifulSoup(html_content, 'html.parser')
stations = soup.find('div', class_='container').find('div', class_='row').find('div', class_='section section-umd_terp_basic_page section-ut_tabs mt-0').find('div', class_='tab-content editor-content').find('div', class_='tab-pane fade active show')
for station in stations.find_all('div', class_='card'):
   info = station.find('div', class_='card-body')
   for item in info.find_all('div', class_='row menu-item-row'):
       link_tag = item.find('a', class_='menu-item-name')
       link = link_tag['href']   #LINK FOR EACH FOOD ITEM
       item_name = link_tag.text
       menu_items[item_name] = link #add to dictionary
    
print(menu_items)



