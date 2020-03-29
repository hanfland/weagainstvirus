const socket = io('http://localhost:8000');

socket.on('sendMessage', function(data){
    console.log(data);
});