package org.apache.spark.network.server;

import org.apache.spark.network.protocol.Message;

public abstract class MessageHandler {
   public abstract void handle(Message var1) throws Exception;

   public abstract void channelActive();

   public abstract void exceptionCaught(Throwable var1);

   public abstract void channelInactive();
}
