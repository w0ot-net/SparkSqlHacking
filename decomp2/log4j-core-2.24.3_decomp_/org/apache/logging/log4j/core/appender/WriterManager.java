package org.apache.logging.log4j.core.appender;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.StringLayout;

public class WriterManager extends AbstractManager {
   protected final StringLayout layout;
   private volatile Writer writer;

   public static WriterManager getManager(final String name, final Object data, final ManagerFactory factory) {
      return (WriterManager)AbstractManager.getManager(name, factory, data);
   }

   public WriterManager(final Writer writer, final String streamName, final StringLayout layout, final boolean writeHeader) {
      super((LoggerContext)null, streamName);
      this.writer = writer;
      this.layout = layout;
      if (writeHeader && layout != null) {
         byte[] header = layout.getHeader();
         if (header != null) {
            try {
               this.writer.write(new String(header, layout.getCharset()));
            } catch (IOException e) {
               this.logError("Unable to write header", e);
            }
         }
      }

   }

   protected synchronized void closeWriter() {
      Writer w = this.writer;

      try {
         w.close();
      } catch (IOException ex) {
         this.logError("Unable to close stream", ex);
      }

   }

   public synchronized void flush() {
      try {
         this.writer.flush();
      } catch (IOException ex) {
         String msg = "Error flushing stream " + this.getName();
         throw new AppenderLoggingException(msg, ex);
      }
   }

   protected Writer getWriter() {
      return this.writer;
   }

   public boolean isOpen() {
      return this.getCount() > 0;
   }

   public boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
      this.writeFooter();
      this.closeWriter();
      return true;
   }

   protected void setWriter(final Writer writer) {
      byte[] header = this.layout.getHeader();
      if (header != null) {
         try {
            writer.write(new String(header, this.layout.getCharset()));
            this.writer = writer;
         } catch (IOException ioe) {
            this.logError("Unable to write header", ioe);
         }
      } else {
         this.writer = writer;
      }

   }

   protected synchronized void write(final String str) {
      try {
         this.writer.write(str);
      } catch (IOException ex) {
         String msg = "Error writing to stream " + this.getName();
         throw new AppenderLoggingException(msg, ex);
      }
   }

   protected void writeFooter() {
      if (this.layout != null) {
         byte[] footer = this.layout.getFooter();
         if (footer != null && footer.length > 0) {
            this.write(new String(footer, this.layout.getCharset()));
         }

      }
   }
}
