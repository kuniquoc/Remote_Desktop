const { contextBridge, ipcRenderer } = require('electron');
const axios = require('axios');


contextBridge.exposeInMainWorld("electron", {
  login: (data) => ipcRenderer.send("user:login", data),
  getUser: async () => {
    const user = await ipcRenderer.invoke("user:get");
    return user;
  },
  logout: () => ipcRenderer.send("user:logout"),
});

ipcRenderer.on("login-failed", (event, message) => {
  document.getElementById("error-message").innerHTML = message;
});


// contextBridge.exposeInMainWorld('api', {
//   fetchData: async () => {
//     const response = await axios.get('https://jsonplaceholder.typicode.com/todos/1');
//     return response.data;
//   }
// });
