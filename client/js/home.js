// Check if the user is logged in by checking the token
const token = localStorage.getItem('token');

if (!token) {
    // Redirect to login page if token is not found
    window.location.href = '../views/login.html';
}

function logout() {
    localStorage.removeItem('token');
    // Redirect to the index page
    window.location.href = 'index.html';
}

// Redirect to Event Calendar page
document.getElementById('event-calendar-btn').addEventListener('click', function() {
    window.location.href = '../views/event_calendar.html';  // Redirect to calendar page
});
