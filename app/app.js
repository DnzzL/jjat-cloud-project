var app = require('express')(),
    server = require('http').createServer(app),
    io = require('socket.io').listen(server),
    ent = require('ent'), // Prevent issues with html (js injection)
    fs = require('fs');
    wstream = fs.createWriteStream('myOutput.txt');
    exec = require('child_process').exec, child;

// Load index.html
app.get('/', function (req, res) {
  res.sendFile(__dirname + '/index.html');
});


io.sockets.on('connection', function (socket, pseudo) {
    // When a nickname is given, store it as a session variable and alert other users
    socket.on('nouveau_client', function(pseudo) {
        pseudo = ent.encode(pseudo);
        socket.pseudo = pseudo;
        socket.broadcast.emit('nouveau_client', pseudo);
    });

    // When a message is received, we collect the pseudo of the author and broadcast it
    socket.on('message', function (message) {
        message = ent.encode(message);
        var mes = "pseudo:" + socket.pseudo + " message:" + message
        socket.broadcast.emit('message', {pseudo: socket.pseudo, message: message});
        wstream.write(mes + " " +  Date().valueOf() + '\n');
    }); 

    // When stats are aksed, run the jar file
    socket.on('request', function (message) { 
        console.log('Request :  ' + message);
        child = exec('/usr/bin/java -jar ~/Users/thomas-legrand/Documents/DCU/Cloud-Technologies/app/topNwords',
        function (error, stdout, stderr){
        console.log('stdout: ' + stdout);
        console.log('stderr: ' + stderr);
        if(error !== null){
            console.log('exec error: ' + error);
    }
});      
    });

    // When the chat is disconnected, stop writing into myOutput.txt
    socket.on('disconnect', function () { 
        wstream.end();      
    });

});


server.listen(8080);