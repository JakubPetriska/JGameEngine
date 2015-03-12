package com.monolith.api.external;

/**
 * Allows sending messages into the engine and receiving messages coming from the engine.
 *
 * Instance of this class is passed outside of the engine to allow this functionality.
 */
public interface InputMessenger {

    /**
     * Sends message into the engine.
     * @param message Message to send.
     */
    void sendMessage(Object message);

    /**
     * Registers {@link com.monolith.api.external.InputMessenger.MessageReceiver} for receiving
     * incoming messages. Receiver will receive only messages of class T.
     * @param messageReceiver Receiver to register.
     * @param messageClass Class object of the messages.
     * @param <T> Type of messages this receiver receives.
     */
    <T> void registerMessageReceiver(MessageReceiver<T> messageReceiver, Class<T> messageClass);

    /**
     * Unregisters {@link com.monolith.api.external.InputMessenger.MessageReceiver} from receiving
     * incoming messages.
     * @param messageReceiver Receiver to unregister.
     * @param messageClass Class object of the messages.
     * @param <T> Type of messages this receiver receives.
     */
    <T> void unregisterMessageReceiver(InputMessenger.MessageReceiver<T> messageReceiver, Class<T> messageClass);

    /**
     * Allows receiving of incoming messages.
     * @param <T> Type of messages to receive.
     */
    interface MessageReceiver<T> {

        /**
         * Gets caled when new message arrives.
         * @param message Message object.
         */
        void onNewMessage(T message);
    }
}