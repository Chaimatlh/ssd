package application;
public class Message {
    private int messageId;
    private String sender;
    private String receiver;
    private byte[] encryptedContent;
    private byte[] encryptedKey;

    public Message(String sender, String receiver, byte[] encryptedContent, byte[] encryptedKey) {
        this.messageId = generateMessageId();
        this.sender = sender;
        this.receiver = receiver;
        this.encryptedContent = encryptedContent;
        this.encryptedKey = encryptedKey;
    }
    private static int messageCounter = 0;

    private static int generateMessageId() {
        return ++messageCounter;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }
    public byte[] getEncryptedContent() {
        return encryptedContent;
    }

    public byte[] getEncryptedKey() {
        return encryptedKey;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    
}