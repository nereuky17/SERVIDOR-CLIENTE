document.getElementById('kafkaForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const message = document.getElementById('message').value;
    
    fetch('/sendMessage', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ message: message }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            addMessageToTable(data.id, message);
        } else {
            alert('Error al enviar el mensaje');
        }
    });
});

function addMessageToTable(id, message) {
    const table = document.getElementById('messagesTable').getElementsByTagName('tbody')[0];
    const newRow = table.insertRow();
    const idCell = newRow.insertCell(0);
    const messageCell = newRow.insertCell(1);

    idCell.textContent = id;
    messageCell.textContent = message;
}
