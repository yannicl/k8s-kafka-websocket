function connect() {
    ws = new WebSocket(((window.location.protocol === "https:") ? "wss://" : "ws://") + window.location.host + "/transactions");
    ws.onmessage = function(data){
        showTransactions(data.data);
        ws.send(data.data);
    }
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    console.log("Disconnected");
}

function showTransactions(message) {
    console.log(message);
}

connect();