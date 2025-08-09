package org.glassfish.jersey.message.internal;

import java.util.ArrayList;
import java.util.List;

final class TracingInfo {
   private final List messageList = new ArrayList();

   public static String formatDuration(long duration) {
      return duration == 0L ? " ----" : String.format("%5.2f", (double)duration / (double)1000000.0F);
   }

   public static String formatDuration(long fromTimestamp, long toTimestamp) {
      return formatDuration(toTimestamp - fromTimestamp);
   }

   public static String formatPercent(long value, long top) {
      return value == 0L ? "  ----" : String.format("%6.2f", (double)100.0F * (double)value / (double)top);
   }

   public String[] getMessages() {
      long fromTimestamp = ((Message)this.messageList.get(0)).getTimestamp() - ((Message)this.messageList.get(0)).getDuration();
      long toTimestamp = ((Message)this.messageList.get(this.messageList.size() - 1)).getTimestamp();
      String[] messages = new String[this.messageList.size()];

      for(int i = 0; i < messages.length; ++i) {
         Message message = (Message)this.messageList.get(i);
         StringBuilder textSB = new StringBuilder();
         textSB.append(String.format("%-11s ", message.getEvent().category()));
         textSB.append('[').append(formatDuration(message.getDuration())).append(" / ").append(formatDuration(fromTimestamp, message.getTimestamp())).append(" ms |").append(formatPercent(message.getDuration(), toTimestamp - fromTimestamp)).append(" %] ");
         textSB.append(message.toString());
         messages[i] = textSB.toString();
      }

      return messages;
   }

   public void addMessage(Message message) {
      this.messageList.add(message);
   }

   public static class Message {
      private final TracingLogger.Event event;
      private final long duration;
      private final long timestamp;
      private final String text;

      public Message(TracingLogger.Event event, long duration, String[] args) {
         this.event = event;
         this.duration = duration;
         this.timestamp = System.nanoTime();
         if (event.messageFormat() != null) {
            this.text = String.format(event.messageFormat(), (Object[])args);
         } else {
            StringBuilder textSB = new StringBuilder();

            for(String arg : args) {
               textSB.append(arg).append(' ');
            }

            this.text = textSB.toString();
         }

      }

      private TracingLogger.Event getEvent() {
         return this.event;
      }

      private long getDuration() {
         return this.duration;
      }

      private long getTimestamp() {
         return this.timestamp;
      }

      public String toString() {
         return this.text;
      }
   }
}
