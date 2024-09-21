// Check if the user is logged in by checking the token
const token = localStorage.getItem('token');

if (!token) {
    // Redirect to login page if token is not found
    window.location.href = '../views/login.html';
}

document.addEventListener('DOMContentLoaded', function() {
    const eventList = document.getElementById('event-list');
    const modal = document.getElementById('event-modal');
    const closeModal = document.querySelector('.close');
    const addEventBtn = document.getElementById('add-event-btn');
    const form = document.getElementById('event-form');
    const successMessage = document.getElementById('success-message');
    const saveBtn = document.getElementById('save-btn');
    const mockAPI = 'http://localhost:3000/events'; // Point to local JSON Server
    let currentEventId = null;

    // Function to format datetime in DD-MM-YYYY HH:mm format (24-hour)
    function formatDateTime(dateTimeStr) {
        const date = new Date(dateTimeStr);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');

        return `${day}-${month}-${year} ${hours}:${minutes}`;
    }


    // Fetch events from the mock API
    async function fetchEvents() {
        const response = await fetch(mockAPI);
        const events = await response.json();
        displayEvents(events);
    }

    // Filter events based on event type
    document.getElementById('eventTypeFilter').addEventListener('change', function() {
        const selectedType = this.value;
        fetchEvents(selectedType);
    });

    // Updated fetchEvents function to apply filtering
    async function fetchEvents(filterType = 'all') {
        const response = await fetch(mockAPI);
        const events = await response.json();
        const filteredEvents = (filterType === 'all') ? events : events.filter(event => event.eventType === filterType);
        displayEvents(filteredEvents);
    }


    // Display events in a table
    function displayEvents(events) {
        eventList.innerHTML = '';
        events.forEach(event => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${event.title}</td>
                <td>${formatDateTime(event.startDateTime)}</td>
                <td>${formatDateTime(event.endDateTime)}</td>
                <td>${event.eventType}</td>
                <td>${event.location}</td>
                <td>
                    <button class="view-btn" data-id="${event.id}">👁️</button>
                    <button class="edit-btn" data-id="${event.id}">✏️</button>
                    <button class="delete-btn" data-id="${event.id}">🗑️</button>
                </td>
            `;
            eventList.appendChild(row);
        });

        // Add event listeners for the action buttons
        document.querySelectorAll('.view-btn').forEach(btn => {
            btn.addEventListener('click', viewEvent);
        });

        document.querySelectorAll('.edit-btn').forEach(btn => {
            btn.addEventListener('click', editEvent);
        });

        document.querySelectorAll('.delete-btn').forEach(btn => {
            btn.addEventListener('click', deleteEvent);
        });
    }

    // View event details
    function viewEvent(e) {
        const eventId = e.target.getAttribute('data-id');
        openModal('view', eventId);
    }

    // Edit event details
    function editEvent(e) {
        const eventId = e.target.getAttribute('data-id');
        openModal('edit', eventId);
    }

    // Delete event
    async function deleteEvent(e) {
        const eventId = e.target.getAttribute('data-id');
        const confirmDelete = confirm('Are you sure you want to delete this event?');
        if (confirmDelete) {
            await fetch(`${mockAPI}/${eventId}`, { method: 'DELETE' });
            fetchEvents(); // Refresh event list after deletion
        }
    }

    // Open modal for viewing, editing, or adding
    async function openModal(mode, eventId) {
        if (eventId) {
            const response = await fetch(`${mockAPI}/${eventId}`);
            const event = await response.json();

            // Populate form with event data if eventId exists (for view/edit modes)
            document.getElementById('title').value = event.title;
            document.getElementById('description').value = event.description;
            document.getElementById('location').value = event.location;
            document.getElementById('courseCode').value = event.courseCode;
            document.getElementById('startDateTime').value = event.startDateTime;
            document.getElementById('endDateTime').value = event.endDateTime;
            document.getElementById('eventType').value = event.eventType;
            document.getElementById('reminder').value = event.reminders[0].minutesBefore || 60;

            if (event.recurrence) {
                document.getElementById('frequency').value = event.recurrence.frequency;
                document.getElementById('interval').value = event.recurrence.interval || 1;
                document.getElementById('daysOfWeek').value = event.recurrence.daysOfWeek.join(', ');
                document.getElementById('endRecurrence').value = event.recurrence.endRecurrence || '';
            } else {
                document.getElementById('frequency').value = 'none';
                document.getElementById('interval').value = '';
                document.getElementById('daysOfWeek').value = '';
                document.getElementById('endRecurrence').value = '';
            }
        }

        if (mode === 'view') {
            disableForm(); // Disable fields in view mode
            successMessage.innerHTML = '';
            saveBtn.classList.add('hidden');
        } else if (mode === 'edit') {
            enableForm(); // Enable fields in edit mode
            successMessage.innerHTML = '';
            saveBtn.classList.remove('hidden');
            currentEventId = eventId; // Set current event ID for edit mode
        } else if (mode === 'add') {
            enableForm(); // Enable fields in add mode
            form.reset(); // Reset the form to empty for adding new event
            successMessage.innerHTML = '';
            saveBtn.classList.remove('hidden');
            currentEventId = null; // No event ID for new event
        }

        modal.style.display = 'block'; // Show the modal
        document.body.classList.add('modal-open'); // Darken background
    }

    // Close modal
    closeModal.addEventListener('click', function() {
        modal.style.display = 'none';
        document.body.classList.remove('modal-open'); // Remove dark background
    });

    // Disable form fields for viewing
    function disableForm() {
        Array.from(form.elements).forEach(element => {
            element.disabled = true;
        });
    }

    // Enable form fields for editing (including Title)
    function enableForm() {
        Array.from(form.elements).forEach(element => {
            element.disabled = false; // Make all fields editable
        });
    }

    // Handle form submission for adding/editing event
    form.addEventListener('submit', async function(e) {
        e.preventDefault();

        const newEvent = {
            title: document.getElementById('title').value,
            description: document.getElementById('description').value,
            location: document.getElementById('location').value,
            courseCode: document.getElementById('courseCode').value,
            startDateTime: document.getElementById('startDateTime').value,
            endDateTime: document.getElementById('endDateTime').value,
            eventType: document.getElementById('eventType').value,
            reminders: [
                {
                    method: 'email',
                    minutesBefore: document.getElementById('reminder').value
                }
            ],
            recurrence: {
                frequency: document.getElementById('frequency').value,
                interval: document.getElementById('interval').value,
                daysOfWeek: document.getElementById('daysOfWeek').value.split(',').map(day => day.trim()),
                endRecurrence: document.getElementById('endRecurrence').value
            }
        };

        if (!currentEventId) {
            // If no event ID (new event), make a POST request to create the event
            const response = await fetch(mockAPI, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newEvent) // Send new event data to the server
            });

            if (response.ok) {
                successMessage.innerHTML = 'Thêm lịch mới thành công!';
                successMessage.style.color = 'green';
                fetchEvents(); // Refresh event list
            }
        } else {
            // If event ID exists, make a PUT request to update the event
            const response = await fetch(`${mockAPI}/${currentEventId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(newEvent)
            });

            if (response.ok) {
                successMessage.innerHTML = 'Cập nhật lịch thành công!';
                successMessage.style.color = 'green';
                fetchEvents(); // Refresh event list
            }
        }

        modal.style.display = 'none'; // Close modal after saving
        document.body.classList.remove('modal-open'); // Remove dark background
    });

    // Open form for adding new event
    addEventBtn.addEventListener('click', function() {
        openModal('add', null); // Change 'edit' to 'add' for adding new event
        successMessage.innerHTML = '';
        form.reset(); // Reset the form fields for adding a new event
    });


    // Fetch initial event list on page load
    fetchEvents();
});
