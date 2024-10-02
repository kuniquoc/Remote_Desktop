
const typeOptions = {
    1: 'Một',
    2: 'Hai',
    3: 'Ba'
};

const studyMethodOptions = {
    1: 'Lecture',
    2: 'Discussion',
    3: 'Self-study'
};

const daysOfWeekOptions = {
    'MONDAY': 'Thứ Hai',
    'TUESDAY': 'Thứ Ba',
    'WEDNESDAY': 'Thứ Tư',
    'THURSDAY': 'Thứ Năm',
    'FRIDAY': 'Thứ Sáu',
    'SATURDAY': 'Thứ Bảy',
    'SUNDAY': 'Chủ Nhật'
};


document.addEventListener('DOMContentLoaded', function () {

    const apiBaseUrl = 'http://localhost:8080/api/schedules';
    // const apiBaseUrl = 'http://localhost:3000/schedules'; // Mock API

    // Updated API base URL to match backend
    const addEventBtn = document.getElementById('addEventBtn');
    const eventFormPopup = document.getElementById('eventFormPopup');
    const closeBtn = document.querySelector('.close');
    const eventForm = document.getElementById('eventForm');
    const eventList = document.getElementById('eventList').getElementsByTagName('tbody')[0];
    const eventTableBody = document.getElementById('eventTableBody');
    const typeIdsSelect = document.getElementById('typeIds');
    const studyMethodIdsSelect = document.getElementById('studyMethodIds');
    const recurrenceFrequency = document.getElementById('recurrenceFrequency');
    const recurrenceInterval = document.getElementById('recurrenceInterval');
    const daysOfWeekSelect = document.getElementById('daysOfWeek');
    const endRecurrenceInput = document.getElementById('endRecurrence');
    const remindersMethodSelect = document.getElementById('remindersMethod');
    const remindersMinutesBefore = document.getElementById('remindersMinutesBefore');

    // Check if the user is authenticated by checking for the token
    const token = localStorage.getItem('token');
    if (!token) {
        // Redirect to login page if token is not found
        window.location.href = '../views/login.html';
        return;
    }

    function formatDateTime(dateTimeString) {
        const date = new Date(dateTimeString);
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');

        return `${day}-${month}-${year} ${hours}:${minutes}:${seconds}`;
    }

    // Debug
    console.log('Add Event Button:', addEventBtn);
    console.log('Event Form Popup:', eventFormPopup);

    // Options
    populateSelectOptions(typeIdsSelect, typeOptions);
    populateSelectOptions(studyMethodIdsSelect, studyMethodOptions);
    populateSelectOptions(daysOfWeekSelect, daysOfWeekOptions);
    function populateSelectOptions(selectElement, options) {
        if (selectElement) {
            for (const [value, label] of Object.entries(options)) {
                const option = document.createElement('option');
                option.value = value;
                option.textContent = label;
                selectElement.appendChild(option);
            }
        }
    }

    // Fetch and display events when page loads
    fetchEvents();

    // Show the form popup to add a new event if addEventBtn exists
    if (addEventBtn) {
        addEventBtn.onclick = function () {
            console.log("Add Event button clicked");  // Debugging log
            if (eventFormPopup) {
                eventFormPopup.classList.add('show');
                console.log("Event Form Popup activated");  // Debugging log
            } else {
                console.error("Event Form Popup element not found");
            }
        };
    } else {
        console.error("Add Event Button element not found");
    }

    // Close the form popup if closeBtn exists
    if (closeBtn) {
        closeBtn.onclick = function () {
            if (eventFormPopup) {
                eventFormPopup.classList.remove('show');
                eventForm.reset();
                console.log("Event Form Popup closed");  // Debugging log
            } else {
                console.error("Event Form Popup element not found");
            }
        };
    } else {
        console.error("Close Button element not found");
    }

    // Handle form submission to save event if eventForm exists
    if (eventForm) {
        eventForm.onsubmit = async function (e) {
            e.preventDefault();
            const formData = new FormData(eventForm);
            const eventData = Object.fromEntries(formData.entries());

            // Convert selected options to arrays
            eventData.typeIds = Array.from(typeIdsSelect.selectedOptions).map(option => parseInt(option.value));
            eventData.studyMethodIds = Array.from(studyMethodIdsSelect.selectedOptions).map(option => parseInt(option.value));


            // Convert selected options to arrays
            eventData.typeIds = Array.from(typeIdsSelect.selectedOptions).map(option => parseInt(option.value));
            eventData.studyMethodIds = Array.from(studyMethodIdsSelect.selectedOptions).map(option => parseInt(option.value));
            eventData.daysOfWeek = Array.from(daysOfWeekSelect.selectedOptions).map(option => option.value);

            eventData.startDateTime = new Date(eventData.startDateTime).toISOString();
            eventData.endDateTime = new Date(eventData.endDateTime).toISOString();


            console.log('Submitting event data:', eventData);

            // Call createEvent function
            console.log(color);
            await createEvent(eventData);
            fetchEvents();
            if (eventFormPopup) {
                eventFormPopup.classList.remove('show');
            }
            eventForm.reset();
        };
    } else {
        console.error("Event Form element not found");
    }

    // Fetch Events
    async function fetchEvents() {
        try {
            const response = await fetch(`${apiBaseUrl}/mode/month`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
            });

            const events = await response.json();

            if (Array.isArray(events.data)) {
                renderEvents(events.data);
            } else {
                throw new TypeError('Expected an array of events, but received:', events);
            }
        } catch (error) {
            console.error('Error fetching events:', error);
        }
    }

    // Function to render events
    function renderEvents(events) {
        if (!Array.isArray(events)) {
            console.error('renderEvents: expected an array but received:', events);
            return;
        }

        eventList.innerHTML = '';
        events.forEach(event => {
            const row = document.createElement('tr');


            row.innerHTML = `
            <td>${event.title}</td>
            <td>${formatDateTime(event.startDateTime)}</td>
            <td>${formatDateTime(event.endDateTime)}</td>
            <td>${event.location}</td>
            <td>
                <button class="view-btn" data-id="${event.id}">👁️</button>
                <button class="edit-btn" data-id="${event.id}">✏️</button>
                <button class="delete-btn" data-id="${event.id}">🗑️</button>
            </td>
        `;
            eventList.appendChild(row);
        });

        // // Add event listeners for buttons
        // document.querySelectorAll('.view-btn').forEach(btn => {
        //     btn.addEventListener('click', viewEvent);
        // });

        // document.querySelectorAll('.edit-btn').forEach(btn => {
        //     btn.addEventListener('click', editEvent);
        // });

        // document.querySelectorAll('.delete-btn').forEach(btn => {
        //     btn.addEventListener('click', deleteEvent);
        // });
    }

    // create new event

    async function createEvent(eventData) {
        try {
            const eventData = {
                title: document.getElementById('title').value,
                description: document.getElementById('description').value,
                location: document.getElementById('location').value,
                startDateTime: document.getElementById('startDateTime').value,
                endDateTime: document.getElementById('endDateTime').value,
                color: document.getElementById('color').value || "#000000", // Default color if not specified
                typeIds: Array.from(typeIdsSelect.selectedOptions).map(option => parseInt(option.value)),
                studyMethodIds: Array.from(studyMethodIdsSelect.selectedOptions).map(option => parseInt(option.value)),
                recurrence: {
                    frequency: recurrenceFrequency.value,
                    interval: parseInt(recurrenceInterval.value),
                    daysOfWeek: Array.from(daysOfWeekSelect.selectedOptions).map(option => option.value),
                    endRecurrence: endRecurrenceInput.value
                },
                reminders: [
                    {
                        method: remindersMethodSelect.value,
                        minutesBefore: parseInt(remindersMinutesBefore.value)
                    }
                ],
                notes: document.getElementById('eventNotes').value
            };

            // Validate required fields
            if (!eventData.title) {
                throw new Error("Title is required");
            }
            if (!eventData.startDateTime || !eventData.endDateTime) {
                throw new Error("Start and End Date/Time are required");

            }


            if (!/^#[0-9A-Fa-f]{6}$/.test(eventData.color)) {
                throw new Error('Color must be in the format "#rrggbb"');
            }

            eventData.typeIds = eventData.typeIds || [];
            eventData.studyMethodIds = eventData.studyMethodIds || [];

            eventData.recurrence = eventData.recurrence || null;

            eventData.reminders = eventData.reminders || [];


            const response = await fetch(`${apiBaseUrl}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify(eventData)
            });

            if (!response.ok) {
                throw new Error(`Failed to create event: ${response.statusText}`);
            }

            console.log('Event created successfully');
        } catch (error) {
            console.error('Error creating event:', error);
        }
    }
})
