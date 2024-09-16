// const button = document.getElementById('loginButton');

//         button.addEventListener('click', function() {
//             window.location.href = 'views/login.html';
//         });

// // Khi ấn đăng nhập
// document.querySelector("#login-button").addEventListener("click", () => {
//     const username = document.getElementById("username").value;
//     const password = document.getElementById("password").value;

//     const data = {
//       username: username,
//       password: password,
//     };

//     window.electron.login(data);
//   });

// // Khi ấn đăng xuất
// window.addEventListener('DOMContentLoaded', () => {
//     window.electron.getUser().then((user) => {
//       document.getElementById("username").innerHTML = `${user.profile.firstName} ${user.profile.lastName}`
//     });
//   });

//   document.getElementById('logout-button').addEventListener('click', () => {
//     window.electron.logout();
//   });