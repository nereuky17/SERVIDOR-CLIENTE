document.getElementById('messageForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const message = document.getElementById('message').value;
    
    fetch('/sendMessage', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ message: document.getElementById('message').value }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => console.log(data))
    .catch(error => console.error('Error:', error));
    });

