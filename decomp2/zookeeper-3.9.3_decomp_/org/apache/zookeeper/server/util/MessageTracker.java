package org.apache.zookeeper.server.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.zookeeper.server.quorum.Leader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageTracker {
   private static final Logger LOG = LoggerFactory.getLogger(MessageTracker.class);
   private final CircularBuffer sentBuffer;
   private final CircularBuffer receivedBuffer;
   public static final String MESSAGE_TRACKER_BUFFER_SIZE = "zookeeper.messageTracker.BufferSize";
   public static final String MESSAGE_TRACKER_ENABLED = "zookeeper.messageTracker.Enabled";
   public static final int BUFFERED_MESSAGE_SIZE = Integer.getInteger("zookeeper.messageTracker.BufferSize", 10);
   private static final boolean enabled = Boolean.getBoolean("zookeeper.messageTracker.Enabled");

   public MessageTracker(int buffer_size) {
      this.sentBuffer = new CircularBuffer(BufferedMessage.class, buffer_size);
      this.receivedBuffer = new CircularBuffer(BufferedMessage.class, buffer_size);
   }

   public void trackSent(long timestamp) {
      if (enabled) {
         this.sentBuffer.write(new BufferedMessage(timestamp));
      }

   }

   public void trackSent(int packetType) {
      if (enabled) {
         this.sentBuffer.write(new BufferedMessage(packetType));
      }

   }

   public void trackReceived(long timestamp) {
      if (enabled) {
         this.receivedBuffer.write(new BufferedMessage(timestamp));
      }

   }

   public void trackReceived(int packetType) {
      if (enabled) {
         this.receivedBuffer.write(new BufferedMessage(packetType));
      }

   }

   public final BufferedMessage peekSent() {
      return (BufferedMessage)this.sentBuffer.peek();
   }

   public final BufferedMessage peekReceived() {
      return (BufferedMessage)this.receivedBuffer.peek();
   }

   public final long peekSentTimestamp() {
      return enabled ? ((BufferedMessage)this.sentBuffer.peek()).getTimestamp() : 0L;
   }

   public final long peekReceivedTimestamp() {
      return enabled ? ((BufferedMessage)this.receivedBuffer.peek()).getTimestamp() : 0L;
   }

   public void dumpToLog(String serverAddress) {
      if (enabled) {
         logMessages(serverAddress, this.receivedBuffer, MessageTracker.Direction.RECEIVED);
         logMessages(serverAddress, this.sentBuffer, MessageTracker.Direction.SENT);
      }
   }

   private static void logMessages(String serverAddr, CircularBuffer messages, Direction direction) {
      String sentOrReceivedText = direction == MessageTracker.Direction.SENT ? "sentBuffer to" : "receivedBuffer from";
      if (messages.isEmpty()) {
         LOG.info("No buffered timestamps for messages {} {}", sentOrReceivedText, serverAddr);
      } else {
         LOG.warn("Last {} timestamps for messages {} {}:", new Object[]{messages.size(), sentOrReceivedText, serverAddr});

         while(!messages.isEmpty()) {
            LOG.warn("{} {}  {}", new Object[]{sentOrReceivedText, serverAddr, ((BufferedMessage)messages.take()).toString()});
         }
      }

   }

   private static enum Direction {
      SENT,
      RECEIVED;
   }

   private static class BufferedMessage {
      private long timestamp;
      private int messageType;

      private long getTimestamp() {
         return this.timestamp;
      }

      BufferedMessage(int messageType) {
         this.messageType = messageType;
         this.timestamp = System.currentTimeMillis();
      }

      BufferedMessage(long timestamp) {
         this.messageType = -1;
         this.timestamp = timestamp;
      }

      public String toString() {
         return this.messageType == -1 ? "TimeStamp: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS")).format(new Date(this.timestamp)) : "TimeStamp: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS")).format(new Date(this.timestamp)) + " Type: " + Leader.getPacketType(this.messageType);
      }
   }
}
