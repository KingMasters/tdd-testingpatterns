package com.designpattern.imposter;

class EmailSenderImposter implements EmailSender {

    boolean sendCalled = false;
    String sentTo;
    String sentMessage;

    @Override
    public void send(String to, String message) {
        sendCalled = true;
        sentTo = to;
        sentMessage = message;
    }
}
