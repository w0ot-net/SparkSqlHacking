package org.apache.logging.log4j.core.appender.rolling;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.appender.ConfigurationFactoryData;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.util.FileUtils;
import org.apache.logging.log4j.core.util.NullOutputStream;

public class RollingRandomAccessFileManager extends RollingFileManager {
   public static final int DEFAULT_BUFFER_SIZE = 262144;
   private static final RollingRandomAccessFileManagerFactory FACTORY = new RollingRandomAccessFileManagerFactory();
   private RandomAccessFile randomAccessFile;

   /** @deprecated */
   @Deprecated
   public RollingRandomAccessFileManager(final LoggerContext loggerContext, final RandomAccessFile raf, final String fileName, final String pattern, final OutputStream os, final boolean append, final boolean immediateFlush, final int bufferSize, final long size, final long time, final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI, final Layout layout, final boolean writeHeader) {
      this(loggerContext, raf, fileName, pattern, os, append, immediateFlush, bufferSize, size, time, policy, strategy, advertiseURI, layout, (String)null, (String)null, (String)null, writeHeader);
   }

   public RollingRandomAccessFileManager(final LoggerContext loggerContext, final RandomAccessFile raf, final String fileName, final String pattern, final OutputStream os, final boolean append, final boolean immediateFlush, final int bufferSize, final long size, final long initialTime, final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI, final Layout layout, final String filePermissions, final String fileOwner, final String fileGroup, final boolean writeHeader) {
      super(loggerContext, fileName, pattern, os, append, false, size, initialTime, policy, strategy, advertiseURI, layout, filePermissions, fileOwner, fileGroup, writeHeader, ByteBuffer.wrap(new byte[bufferSize]));
      this.randomAccessFile = raf;
      this.writeHeader();
   }

   private void writeHeader() {
      if (this.layout != null) {
         byte[] header = this.layout.getHeader();
         if (header != null) {
            try {
               if (this.randomAccessFile != null && this.randomAccessFile.length() == 0L) {
                  this.randomAccessFile.write(header, 0, header.length);
               }
            } catch (IOException e) {
               this.logError("Unable to write header", e);
            }

         }
      }
   }

   public static RollingRandomAccessFileManager getRollingRandomAccessFileManager(final String fileName, final String filePattern, final boolean isAppend, final boolean immediateFlush, final int bufferSize, final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI, final Layout layout, final String filePermissions, final String fileOwner, final String fileGroup, final Configuration configuration) {
      if (strategy instanceof DirectWriteRolloverStrategy && fileName != null) {
         LOGGER.error("The fileName attribute must not be specified with the DirectWriteRolloverStrategy");
         return null;
      } else {
         String name = fileName == null ? filePattern : fileName;
         return (RollingRandomAccessFileManager)narrow(RollingRandomAccessFileManager.class, getManager(name, new FactoryData(fileName, filePattern, isAppend, immediateFlush, bufferSize, policy, strategy, advertiseURI, layout, filePermissions, fileOwner, fileGroup, configuration), FACTORY));
      }
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

   protected synchronized void write(final byte[] bytes, final int offset, final int length, final boolean immediateFlush) {
      super.write(bytes, offset, length, immediateFlush);
   }

   protected synchronized void writeToDestination(final byte[] bytes, final int offset, final int length) {
      try {
         if (this.randomAccessFile == null) {
            this.createFileAfterRollover();
         }

         this.randomAccessFile.write(bytes, offset, length);
         this.size += (long)length;
      } catch (IOException ex) {
         String msg = "Error writing to RandomAccessFile " + this.getName();
         throw new AppenderLoggingException(msg, ex);
      }
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The name of the accessed files is based on a configuration value."
   )
   protected void createFileAfterRollover() throws IOException {
      String fileName = this.getFileName();
      File file = new File(fileName);
      FileUtils.makeParentDirs(file);
      this.createFileAfterRollover(fileName);
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The name of the accessed files is based on a configuration value."
   )
   private void createFileAfterRollover(final String fileName) throws IOException {
      this.randomAccessFile = new RandomAccessFile(fileName, "rw");
      if (this.isAttributeViewEnabled()) {
         this.defineAttributeView(Paths.get(fileName));
      }

      if (this.isAppend()) {
         this.randomAccessFile.seek(this.randomAccessFile.length());
      }

      this.writeHeader();
   }

   public synchronized void flush() {
      this.flushBuffer(this.byteBuffer);
   }

   public synchronized boolean closeOutputStream() {
      this.flush();
      if (this.randomAccessFile != null) {
         try {
            this.randomAccessFile.close();
            return true;
         } catch (IOException e) {
            this.logError("Unable to close RandomAccessFile", e);
            return false;
         }
      } else {
         return true;
      }
   }

   public int getBufferSize() {
      return this.byteBuffer.capacity();
   }

   public void updateData(final Object data) {
      FactoryData factoryData = (FactoryData)data;
      this.setRolloverStrategy(factoryData.getRolloverStrategy());
      this.setPatternProcessor(new PatternProcessor(factoryData.getPattern(), this.getPatternProcessor()));
      this.setTriggeringPolicy(factoryData.getTriggeringPolicy());
   }

   private static class RollingRandomAccessFileManagerFactory implements ManagerFactory {
      private RollingRandomAccessFileManagerFactory() {
      }

      @SuppressFBWarnings(
         value = {"PATH_TRAVERSAL_IN"},
         justification = "The name of the accessed files is based on a configuration value."
      )
      public RollingRandomAccessFileManager createManager(final String name, final FactoryData data) {
         File file = null;
         long size = 0L;
         long time = System.currentTimeMillis();
         RandomAccessFile raf = null;
         if (data.fileName != null) {
            file = new File(name);
            if (!data.append) {
               file.delete();
            }

            size = data.append ? file.length() : 0L;
            if (file.exists()) {
               time = file.lastModified();
            }

            try {
               FileUtils.makeParentDirs(file);
               raf = new RandomAccessFile(name, "rw");
               if (data.append) {
                  long length = raf.length();
                  RollingRandomAccessFileManager.LOGGER.trace("RandomAccessFile {} seek to {}", name, length);
                  raf.seek(length);
               } else {
                  RollingRandomAccessFileManager.LOGGER.trace("RandomAccessFile {} set length to 0", name);
                  raf.setLength(0L);
               }
            } catch (IOException ex) {
               RollingRandomAccessFileManager.LOGGER.error("Cannot access RandomAccessFile " + ex, ex);
               if (raf != null) {
                  try {
                     raf.close();
                  } catch (IOException e) {
                     RollingRandomAccessFileManager.LOGGER.error("Cannot close RandomAccessFile {}", name, e);
                  }
               }

               return null;
            }
         }

         boolean writeHeader = !data.append || file == null || !file.exists();
         RollingRandomAccessFileManager rrm = new RollingRandomAccessFileManager(data.getLoggerContext(), raf, name, data.pattern, NullOutputStream.getInstance(), data.append, data.immediateFlush, data.bufferSize, size, time, data.policy, data.strategy, data.advertiseURI, data.layout, data.filePermissions, data.fileOwner, data.fileGroup, writeHeader);
         if (rrm.isAttributeViewEnabled()) {
            rrm.defineAttributeView(file.toPath());
         }

         return rrm;
      }
   }

   private static class FactoryData extends ConfigurationFactoryData {
      private final String fileName;
      private final String pattern;
      private final boolean append;
      private final boolean immediateFlush;
      private final int bufferSize;
      private final TriggeringPolicy policy;
      private final RolloverStrategy strategy;
      private final String advertiseURI;
      private final Layout layout;
      private final String filePermissions;
      private final String fileOwner;
      private final String fileGroup;

      public FactoryData(final String fileName, final String pattern, final boolean append, final boolean immediateFlush, final int bufferSize, final TriggeringPolicy policy, final RolloverStrategy strategy, final String advertiseURI, final Layout layout, final String filePermissions, final String fileOwner, final String fileGroup, final Configuration configuration) {
         super(configuration);
         this.fileName = fileName;
         this.pattern = pattern;
         this.append = append;
         this.immediateFlush = immediateFlush;
         this.bufferSize = bufferSize;
         this.policy = policy;
         this.strategy = strategy;
         this.advertiseURI = advertiseURI;
         this.layout = layout;
         this.filePermissions = filePermissions;
         this.fileOwner = fileOwner;
         this.fileGroup = fileGroup;
      }

      public String getPattern() {
         return this.pattern;
      }

      public TriggeringPolicy getTriggeringPolicy() {
         return this.policy;
      }

      public RolloverStrategy getRolloverStrategy() {
         return this.strategy;
      }
   }
}
