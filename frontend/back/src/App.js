import React, { Component, useState, useRef, useEffect } from 'react';
import SockJsClient from 'react-stomp';
import './App.css';
import './css/MessageStyle.css';
import NameComponent from "./components/NameComponent";

import {
    
    Button,
    
    TextField,
} from "@material-ui/core";
const senderId = 1001 //i id ban than
const recipientId = 1003 // id nguoi nhan

const chatId = 2401 // chatroomid =  get {nguoi gui, nguoi nhan}

const SOCKET_URL = '/websocket-chat';

function App(props) {
    const [messages, setMessages] = useState([])
    const [typedMessage, setTypeMessage] = useState("")
    const [name, setName] = useState("")
    // useEffect()
    // useSubscription(`/topic/${chatId}/queue/messages`, (message) => setLastMessage(message.body.content));
    let clientRefx = useRef(null)
    const sendMessage = () => {
        clientRefx.sendMessage('/app/chat', JSON.stringify({
            recipientId: recipientId,
            senderId: senderId,
            content: typedMessage,
        }));
    };
    const displayMessages = () => {
        return (
            <div>
                {messages.map(msg => {
                    return (
                        <div>
                            {name === msg.name ?
                                <div>
                                    <p className="title1">{msg.senderId} : </p><br />
                                    <p>{msg.content}</p>
                                </div> :
                                <div>
                                    <p className="title2">{msg.senderId} : </p><br />
                                    <p>{msg.content}</p>
                                </div>
                            }
                        </div>)
                })}
            </div>
        );
    };

    return (
        <div>
            <NameComponent setName={setName} />
            <div className="align-center">
                <h1>Welcome to Web Sockets</h1>
                <br /><br />
            </div>
            <div className="align-center">
                User : <p className="title1"> {name}</p>
            </div>
            <div className="align-center">
                <br /><br />
                <table>
                    <tr>
                        <td>
                            <TextField id="outlined-basic" label="Enter Message to Send" variant="outlined"
                                onChange={(event) => {
                                    setTypeMessage(event.target.value);
                                }} />
                        </td>
                        <td>
                            <Button variant="contained" color="primary"
                                onClick={sendMessage}>Send</Button>
                        </td>
                    </tr>
                </table>
            </div>
            <br /><br />

            <div className="align-center">
                {displayMessages()}
            </div>
            <SockJsClient url={SOCKET_URL}
                topics={[`/topic/${chatId}/queue/messages`]}
                onConnect={() => {
                    console.log("connected");
                }}
                onDisconnect={() => {
                    console.log("Disconnected");
                }}
                onMessage={(msg) => {
                    console.log(msg);
                    messages.push(msg)
                    setMessages(messages);
                    console.log("set xong");
                    // console.log(state);
                }}
                ref={(client) => {
                    clientRef = client
                }} />
        </div>
    )
}

export default App;