function scrollToSection() {
    const section = document.getElementById('bottom-section');
    section.scrollIntoView({ behavior: 'smooth' });
}

/*function storeInput() {
    const inputField = document.getElementById('meal-input');
    const inputValue = inputField.value.trim();

    if (inputValue) {
        storedInputs.push(inputValue);
        console.log(storedInputs);
        
        // Create a text file from the array
        const blob = new Blob([storedInputs.join('\n')], { type: 'text/plain' });
        const url = URL.createObjectURL(blob);

        // Create a link element to download the file
        const a = document.createElement('a');
        a.href = url;
        a.download = 'nutrition_questions.txt'; // Name of the file
        a.click(); // Trigger the download

        // Clear the input field
        inputField.value = '';
    } else {
        alert('Please enter a value!');
    }
}*/