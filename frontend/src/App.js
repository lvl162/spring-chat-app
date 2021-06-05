// import React, { Component } from 'react';
// import SockJsClient from 'react-stomp';
// import './App.css';
// import Button from '@material-ui/core/Button';
// import TextField from '@material-ui/core/TextField';
// import './css/MessageStyle.css';
// import NameComponent from "./components/NameComponent";

// class App extends Component {

//     customHeaders = {
//         "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdmwzIiwiaWF0IjoxNjIyODYxMjQ3LCJleHAiOjE2MjI5NDc2NDd9.Uuc6Y8r9vONANyJfQjx6ulOrl4AYEbFIzKe9UiovOI5urX061Ceyu6aGcNT2xUrNdfB5qItRf8kt71FtwsUE8Q"
//     };
//     constructor(props) {
//         super(props);
//         this.state = {
//             messages: [],
//             typedMessage: "",
//             name: ""
//         }
//     }

//     setName = (name) => {
//         console.log(name);
//         this.setState({ name: name });
//     };

//     sendMessage = () => {
//         this.clientRef.sendMessage('https://chatchit69.herokuapp.com/app/chat', JSON.stringify({
//             name: this.state.name,
//             message: this.state.typedMessage
//         }));
//     };

//     displayMessages = () => {
//         return (
//             <div>
//                 {this.state.messages.map(msg => {
//                     return (
//                         <div>
//                             {this.state.name === msg.name ?
//                                 <div>
//                                     <p className="title1">{msg.name} : </p><br />
//                                     <p>{msg.message}</p>
//                                 </div> :
//                                 <div>
//                                     <p className="title2">{msg.name} : </p><br />
//                                     <p>{msg.message}</p>
//                                 </div>
//                             }
//                         </div>)
//                 })}
//             </div>
//         );
//     };

//     render() {
//         return (
//             <div>
//                 <NameComponent setName={this.setName} />
//                 <div className="align-center">
//                     <h1>Welcome to Web Sockets</h1>
//                     <br /><br />
//                 </div>
//                 <div className="align-center">
//                     User : <p className="title1"> {this.state.name}</p>
//                 </div>
//                 <div className="align-center">
//                     <br /><br />
//                     <table>
//                         <tr>
//                             <td>
//                                 <TextField id="outlined-basic" label="Enter Message to Send" variant="outlined"
//                                     onChange={(event) => {
//                                         this.setState({ typedMessage: event.target.value });
//                                     }} />
//                             </td>
//                             <td>
//                                 <Button variant="contained" color="primary"
//                                     onClick={this.sendMessage}>Send</Button>
//                             </td>
//                         </tr>
//                     </table>
//                 </div>
//                 <br /><br />
//                 <div className="align-center">
//                     {this.displayMessages()}
//                 </div>
//                 <SockJsClient url='https://chatchit69.herokuapp.com/websocket-chat'
//                     topics={['https://chatchit69.herokuapp.com/topic/queue/messages']}
//                     header={this.customHeaders}
//                     onConnect={() => {
//                         console.log("connected");
//                     }}
//                     onDisconnect={() => {
//                         console.log("Disconnected");
//                     }}
//                     onMessage={(msg) => {
//                         var jobs = this.state.messages;
//                         jobs.push(msg);
//                         this.setState({ messages: jobs });
//                         console.log(this.state);
//                     }}
//                     ref={(client) => {
//                         this.clientRef = client
//                     }} />
//             </div>
//         )
//     }
// }

// export default App;

import React, { useState } from 'react';
import SockJsClient from 'react-stomp';

const SOCKET_URL = 'http://localhost:8080/websocket-chat';
// const SOCKET_URL = 'https://chatchit69.herokuapp.com/websocket-chat';
const customHeaders = {
    "Authorization": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsdmwzIiwiaWF0IjoxNjIyODYxMjQ3LCJleHAiOjE2MjI5NDc2NDd9.Uuc6Y8r9vONANyJfQjx6ulOrl4AYEbFIzKe9UiovOI5urX061Ceyu6aGcNT2xUrNdfB5qItRf8kt71FtwsUE8Q"
};
const App = () => {
    const [message, setMessage] = useState('You server message here.');

    let onConnected = () => {
        console.log("Connected!!")
    }

    let onMessageReceived = (msg) => {
        setMessage(msg.message);
    }

    return (
        <div>
            <SockJsClient
                url={SOCKET_URL}
                headers={customHeaders}
                topics={['/topic/message']}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={msg => onMessageReceived(msg)}
                debug={false}
            />
            <div>{message}</div>
        </div>
    );
}
export default App;
