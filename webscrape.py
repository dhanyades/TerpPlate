import requests
from bs4 import BeautifulSoup

#navigate to the menu
url = 'https://nutrition.umd.edu/'
response = requests.get(url)

# Check if the request was successful
if response.status_code == 200:
    print("Successfully accessed the page.")
    page_content = response.text
    # You can now parse this content as needed
else:
    print(f"Failed to access the page. Status code: {response.status_code}")


#collect all links (for each food item)
def get_menu_links(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    links = []

    # Adjust the selector to find the links to each food item
    for item in soup.select('.menu-item-class a'):
        full_link = item['href']  # Get the link
        links.append(full_link)
    
    return links