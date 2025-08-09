package org.apache.logging.log4j.message;

import aQute.bnd.annotation.spi.ServiceConsumer;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Lazy;
import org.apache.logging.log4j.util.ServiceLoaderUtil;
import org.apache.logging.log4j.util.StringBuilderFormattable;

@AsynchronouslyFormattable
@ServiceConsumer(
   value = ThreadInfoFactory.class,
   resolution = "optional",
   cardinality = "single"
)
public class ThreadDumpMessage implements Message, StringBuilderFormattable {
   private static final long serialVersionUID = -1103400781608841088L;
   private static final Lazy FACTORY = Lazy.lazy(ThreadDumpMessage::initFactory);
   private volatile Map threads;
   private final String title;
   private String formattedMessage;

   public ThreadDumpMessage(final String title) {
      this.title = title == null ? "" : title;
      this.threads = ((ThreadInfoFactory)FACTORY.get()).createThreadInfo();
   }

   private ThreadDumpMessage(final String formattedMsg, final String title) {
      this.formattedMessage = formattedMsg;
      this.title = title == null ? "" : title;
   }

   private static ThreadInfoFactory initFactory() {
      return (ThreadInfoFactory)ServiceLoaderUtil.safeStream(ThreadInfoFactory.class, ServiceLoader.load(ThreadInfoFactory.class, ThreadDumpMessage.class.getClassLoader()), StatusLogger.getLogger()).findFirst().orElseGet(() -> new BasicThreadInfoFactory());
   }

   public String toString() {
      return this.getFormattedMessage();
   }

   public String getFormattedMessage() {
      if (this.formattedMessage != null) {
         return this.formattedMessage;
      } else {
         StringBuilder sb = new StringBuilder(255);
         this.formatTo(sb);
         return sb.toString();
      }
   }

   public void formatTo(final StringBuilder sb) {
      sb.append(this.title);
      if (this.title.length() > 0) {
         sb.append('\n');
      }

      for(Map.Entry entry : this.threads.entrySet()) {
         ThreadInformation info = (ThreadInformation)entry.getKey();
         info.printThreadInfo(sb);
         info.printStack(sb, (StackTraceElement[])entry.getValue());
         sb.append('\n');
      }

   }

   public String getFormat() {
      return this.title == null ? "" : this.title;
   }

   public Object[] getParameters() {
      return null;
   }

   protected Object writeReplace() {
      return new ThreadDumpMessageProxy(this);
   }

   private void readObject(final ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Proxy required");
   }

   public Throwable getThrowable() {
      return null;
   }

   private static class ThreadDumpMessageProxy implements Serializable {
      private static final long serialVersionUID = -3476620450287648269L;
      private final String formattedMsg;
      private final String title;

      ThreadDumpMessageProxy(final ThreadDumpMessage msg) {
         this.formattedMsg = msg.getFormattedMessage();
         this.title = msg.title;
      }

      protected Object readResolve() {
         return new ThreadDumpMessage(this.formattedMsg, this.title);
      }
   }

   private static class BasicThreadInfoFactory implements ThreadInfoFactory {
      private BasicThreadInfoFactory() {
      }

      public Map createThreadInfo() {
         Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
         Map<ThreadInformation, StackTraceElement[]> threads = new HashMap(map.size());

         for(Map.Entry entry : map.entrySet()) {
            threads.put(new BasicThreadInformation((Thread)entry.getKey()), (StackTraceElement[])entry.getValue());
         }

         return threads;
      }
   }

   public interface ThreadInfoFactory {
      Map createThreadInfo();
   }
}
