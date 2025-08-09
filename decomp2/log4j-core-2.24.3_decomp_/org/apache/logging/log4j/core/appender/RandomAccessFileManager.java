package org.apache.logging.log4j.core.appender;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.logging.log4j.core.util.NullOutputStream;

public class RandomAccessFileManager extends OutputStreamManager {
   static final int DEFAULT_BUFFER_SIZE = 262144;
   private static final RandomAccessFileManagerFactory FACTORY = new RandomAccessFileManagerFactory();
   private final String advertiseURI;
   private final RandomAccessFile randomAccessFile;

   protected RandomAccessFileManager(final LoggerContext loggerContext, final RandomAccessFile file, final String fileName, final OutputStream os, final int bufferSize, final String advertiseURI, final Layout layout, final boolean writeHeader) {
      super(loggerContext, os, fileName, false, layout, writeHeader, ByteBuffer.wrap(new byte[bufferSize]));
      this.randomAccessFile = file;
      this.advertiseURI = advertiseURI;
   }

   public static RandomAccessFileManager getFileManager(final String fileName, final boolean append, final boolean immediateFlush, final int bufferSize, final String advertiseURI, final Layout layout, final Configuration configuration) {
      return (RandomAccessFileManager)narrow(RandomAccessFileManager.class, getManager(fileName, new FactoryData(append, immediateFlush, bufferSize, advertiseURI, layout, configuration), FACTORY));
   }

   /** @deprecated */
   @Deprecated
   public Boolean isEndOfBatch() {
      return Boolean.FALSE;
   }

   /** @deprecated */
   @Deprecated
   public void setEndOfBatch(final boolean endOfBatch) {
   }

   protected void writeToDestination(final byte[] bytes, final int offset, final int length) {
      try {
         this.randomAccessFile.write(bytes, offset, length);
      } catch (IOException ex) {
         String msg = "Error writing to RandomAccessFile " + this.getName();
         throw new AppenderLoggingException(msg, ex);
      }
   }

   public synchronized void flush() {
      this.flushBuffer(this.byteBuffer);
   }

   public synchronized boolean closeOutputStream() {
      this.flush();

      try {
         this.randomAccessFile.close();
         return true;
      } catch (IOException ex) {
         this.logError("Unable to close RandomAccessFile", ex);
         return false;
      }
   }

   public String getFileName() {
      return this.getName();
   }

   public int getBufferSize() {
      return this.byteBuffer.capacity();
   }

   public Map getContentFormat() {
      Map<String, String> result = new HashMap(super.getContentFormat());
      result.put("fileURI", this.advertiseURI);
      return result;
   }

   private static class FactoryData extends ConfigurationFactoryData {
      private final boolean append;
      private final boolean immediateFlush;
      private final int bufferSize;
      private final String advertiseURI;
      private final Layout layout;

      public FactoryData(final boolean append, final boolean immediateFlush, final int bufferSize, final String advertiseURI, final Layout layout, final Configuration configuration) {
         super(configuration);
         this.append = append;
         this.immediateFlush = immediateFlush;
         this.bufferSize = bufferSize;
         this.advertiseURI = advertiseURI;
         this.layout = layout;
      }
   }

   private static class RandomAccessFileManagerFactory implements ManagerFactory {
      private RandomAccessFileManagerFactory() {
      }

      @SuppressFBWarnings(
         value = {"PATH_TRAVERSAL_IN"},
         justification = "The destination file should be specified in the configuration file."
      )
      public RandomAccessFileManager createManager(final String name, final FactoryData data) {
         File file = new File(name);
         if (!data.append) {
            file.delete();
         }

         boolean writeHeader = !data.append || !file.exists();
         OutputStream os = NullOutputStream.getInstance();

         try {
            FileUtils.makeParentDirs(file);
            RandomAccessFile raf = new RandomAccessFile(name, "rw");
            if (data.append) {
               raf.seek(raf.length());
            } else {
               raf.setLength(0L);
            }

            return new RandomAccessFileManager(data.getLoggerContext(), raf, name, os, data.bufferSize, data.advertiseURI, data.layout, writeHeader);
         } catch (Exception ex) {
            AbstractManager.LOGGER.error("RandomAccessFileManager (" + name + ") " + ex, ex);
            return null;
         }
      }
   }
}
