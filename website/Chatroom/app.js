var app = require('express')();
var http = require('http').createServer(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
  res.sendFile(__dirname + '/index.html');
});

io.on('connection', function(socket){
    console.log('a user connected');
    
    //socket.emit("message", {sender: "test", text:"Hallo hallo"});
    
    socket.on('disconnect', function(){
        console.log('user disconnected');
    });
    
    socket.on('message', function(msg){
        io.emit("message", msg);
    });
});

http.listen(8000, function(){
  console.log('listening on *:3000');
});