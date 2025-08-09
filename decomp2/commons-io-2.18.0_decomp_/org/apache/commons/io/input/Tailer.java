package org.apache.commons.io.input;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Duration;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.ThreadUtils;
import org.apache.commons.io.build.AbstractOrigin;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.io.file.attribute.FileTimes;

public class Tailer implements Runnable, AutoCloseable {
   private static final int DEFAULT_DELAY_MILLIS = 1000;
   private static final String RAF_READ_ONLY_MODE = "r";
   private static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
   private final byte[] inbuf;
   private final Tailable tailable;
   private final Charset charset;
   private final Duration delayDuration;
   private final boolean tailAtEnd;
   private final TailerListener listener;
   private final boolean reOpen;
   private volatile boolean run;

   public static Builder builder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, Charset charset, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufferSize) {
      return ((Builder)((Builder)((Builder)builder().setFile(file)).setTailerListener(listener).setCharset(charset)).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setReOpen(reOpen).setBufferSize(bufferSize)).get();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, TailerListener listener) {
      return ((Builder)builder().setFile(file)).setTailerListener(listener).get();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, TailerListener listener, long delayMillis) {
      return ((Builder)builder().setFile(file)).setTailerListener(listener).setDelayDuration(Duration.ofMillis(delayMillis)).get();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end) {
      return ((Builder)builder().setFile(file)).setTailerListener(listener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).get();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen) {
      return ((Builder)builder().setFile(file)).setTailerListener(listener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setReOpen(reOpen).get();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufferSize) {
      return ((Builder)((Builder)builder().setFile(file)).setTailerListener(listener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setReOpen(reOpen).setBufferSize(bufferSize)).get();
   }

   /** @deprecated */
   @Deprecated
   public static Tailer create(File file, TailerListener listener, long delayMillis, boolean end, int bufferSize) {
      return ((Builder)((Builder)builder().setFile(file)).setTailerListener(listener).setDelayDuration(Duration.ofMillis(delayMillis)).setTailFromEnd(end).setBufferSize(bufferSize)).get();
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, Charset charset, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufSize) {
      this(new TailablePath(file.toPath(), new LinkOption[0]), charset, listener, Duration.ofMillis(delayMillis), end, reOpen, bufSize);
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, TailerListener listener) {
      this(file, listener, 1000L);
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, TailerListener listener, long delayMillis) {
      this(file, listener, delayMillis, false);
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, TailerListener listener, long delayMillis, boolean end) {
      this(file, listener, delayMillis, end, 8192);
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen) {
      this(file, listener, delayMillis, end, reOpen, 8192);
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, TailerListener listener, long delayMillis, boolean end, boolean reOpen, int bufferSize) {
      this(file, DEFAULT_CHARSET, listener, delayMillis, end, reOpen, bufferSize);
   }

   /** @deprecated */
   @Deprecated
   public Tailer(File file, TailerListener listener, long delayMillis, boolean end, int bufferSize) {
      this(file, listener, delayMillis, end, false, bufferSize);
   }

   private Tailer(Tailable tailable, Charset charset, TailerListener listener, Duration delayDuration, boolean end, boolean reOpen, int bufferSize) {
      this.run = true;
      this.tailable = (Tailable)Objects.requireNonNull(tailable, "tailable");
      this.listener = (TailerListener)Objects.requireNonNull(listener, "listener");
      this.delayDuration = delayDuration;
      this.tailAtEnd = end;
      this.inbuf = IOUtils.byteArray(bufferSize);
      listener.init(this);
      this.reOpen = reOpen;
      this.charset = charset;
   }

   public void close() {
      this.run = false;
   }

   /** @deprecated */
   @Deprecated
   public long getDelay() {
      return this.delayDuration.toMillis();
   }

   public Duration getDelayDuration() {
      return this.delayDuration;
   }

   public File getFile() {
      if (this.tailable instanceof TailablePath) {
         return ((TailablePath)this.tailable).getPath().toFile();
      } else {
         throw new IllegalStateException("Cannot extract java.io.File from " + this.tailable.getClass().getName());
      }
   }

   protected boolean getRun() {
      return this.run;
   }

   public Tailable getTailable() {
      return this.tailable;
   }

   private long readLines(RandomAccessResourceBridge reader) throws IOException {
      ByteArrayOutputStream lineBuf = new ByteArrayOutputStream(64);

      long var13;
      try {
         long pos = reader.getPointer();
         long rePos = pos;

         int num;
         for(boolean seenCR = false; this.getRun() && (num = reader.read(this.inbuf)) != -1; pos = reader.getPointer()) {
            for(int i = 0; i < num; ++i) {
               byte ch = this.inbuf[i];
               switch (ch) {
                  case 10:
                     seenCR = false;
                     this.listener.handle(new String(lineBuf.toByteArray(), this.charset));
                     lineBuf.reset();
                     rePos = pos + (long)i + 1L;
                     break;
                  case 13:
                     if (seenCR) {
                        lineBuf.write(13);
                     }

                     seenCR = true;
                     break;
                  default:
                     if (seenCR) {
                        seenCR = false;
                        this.listener.handle(new String(lineBuf.toByteArray(), this.charset));
                        lineBuf.reset();
                        rePos = pos + (long)i + 1L;
                     }

                     lineBuf.write(ch);
               }
            }
         }

         reader.seek(rePos);
         if (this.listener instanceof TailerListenerAdapter) {
            ((TailerListenerAdapter)this.listener).endOfFileReached();
         }

         var13 = rePos;
      } catch (Throwable var12) {
         try {
            lineBuf.close();
         } catch (Throwable var11) {
            var12.addSuppressed(var11);
         }

         throw var12;
      }

      lineBuf.close();
      return var13;
   }

   public void run() {
      RandomAccessResourceBridge reader = null;

      try {
         FileTime last = FileTimes.EPOCH;
         long position = 0L;

         while(this.getRun() && reader == null) {
            try {
               reader = this.tailable.getRandomAccess("r");
            } catch (FileNotFoundException var28) {
               this.listener.fileNotFound();
            }

            if (reader == null) {
               ThreadUtils.sleep(this.delayDuration);
            } else {
               position = this.tailAtEnd ? this.tailable.size() : 0L;
               last = this.tailable.lastModifiedFileTime();
               reader.seek(position);
            }
         }

         while(this.getRun()) {
            boolean newer = this.tailable.isNewer(last);
            long length = this.tailable.size();
            if (length < position) {
               this.listener.fileRotated();

               try {
                  RandomAccessResourceBridge save = reader;

                  try {
                     reader = this.tailable.getRandomAccess("r");

                     try {
                        this.readLines(save);
                     } catch (IOException ioe) {
                        this.listener.handle((Exception)ioe);
                     }

                     position = 0L;
                  } catch (Throwable var29) {
                     if (reader != null) {
                        try {
                           save.close();
                        } catch (Throwable var26) {
                           var29.addSuppressed(var26);
                        }
                     }

                     throw var29;
                  }

                  if (save != null) {
                     save.close();
                  }
               } catch (FileNotFoundException var30) {
                  this.listener.fileNotFound();
                  ThreadUtils.sleep(this.delayDuration);
               }
            } else {
               if (length > position) {
                  position = this.readLines(reader);
                  last = this.tailable.lastModifiedFileTime();
               } else if (newer) {
                  position = 0L;
                  reader.seek(position);
                  position = this.readLines(reader);
                  last = this.tailable.lastModifiedFileTime();
               }

               if (this.reOpen && reader != null) {
                  reader.close();
               }

               ThreadUtils.sleep(this.delayDuration);
               if (this.getRun() && this.reOpen) {
                  reader = this.tailable.getRandomAccess("r");
                  reader.seek(position);
               }
            }
         }
      } catch (InterruptedException e) {
         Thread.currentThread().interrupt();
         this.listener.handle((Exception)e);
      } catch (Exception e) {
         this.listener.handle(e);
      } finally {
         try {
            IOUtils.close((Closeable)reader);
         } catch (IOException e) {
            this.listener.handle((Exception)e);
         }

         this.close();
      }

   }

   /** @deprecated */
   @Deprecated
   public void stop() {
      this.close();
   }

   public static class Builder extends AbstractStreamBuilder {
      private static final Duration DEFAULT_DELAY_DURATION = Duration.ofMillis(1000L);
      private Tailable tailable;
      private TailerListener tailerListener;
      private Duration delayDuration;
      private boolean tailFromEnd;
      private boolean reOpen;
      private boolean startThread;
      private ExecutorService executorService;

      public Builder() {
         this.delayDuration = DEFAULT_DELAY_DURATION;
         this.startThread = true;
         this.executorService = Executors.newSingleThreadExecutor(Builder::newDaemonThread);
      }

      private static Thread newDaemonThread(Runnable runnable) {
         Thread thread = new Thread(runnable, "commons-io-tailer");
         thread.setDaemon(true);
         return thread;
      }

      public Tailer get() {
         Tailer tailer = new Tailer(this.tailable, this.getCharset(), this.tailerListener, this.delayDuration, this.tailFromEnd, this.reOpen, this.getBufferSize());
         if (this.startThread) {
            this.executorService.submit(tailer);
         }

         return tailer;
      }

      public Builder setDelayDuration(Duration delayDuration) {
         this.delayDuration = delayDuration != null ? delayDuration : DEFAULT_DELAY_DURATION;
         return this;
      }

      public Builder setExecutorService(ExecutorService executorService) {
         this.executorService = (ExecutorService)Objects.requireNonNull(executorService, "executorService");
         return this;
      }

      protected Builder setOrigin(AbstractOrigin origin) {
         this.setTailable(new TailablePath(origin.getPath(), new LinkOption[0]));
         return (Builder)super.setOrigin(origin);
      }

      public Builder setReOpen(boolean reOpen) {
         this.reOpen = reOpen;
         return this;
      }

      public Builder setStartThread(boolean startThread) {
         this.startThread = startThread;
         return this;
      }

      public Builder setTailable(Tailable tailable) {
         this.tailable = (Tailable)Objects.requireNonNull(tailable, "tailable");
         return this;
      }

      public Builder setTailerListener(TailerListener tailerListener) {
         this.tailerListener = (TailerListener)Objects.requireNonNull(tailerListener, "tailerListener");
         return this;
      }

      public Builder setTailFromEnd(boolean end) {
         this.tailFromEnd = end;
         return this;
      }
   }

   private static final class RandomAccessFileBridge implements RandomAccessResourceBridge {
      private final RandomAccessFile randomAccessFile;

      private RandomAccessFileBridge(File file, String mode) throws FileNotFoundException {
         this.randomAccessFile = new RandomAccessFile(file, mode);
      }

      public void close() throws IOException {
         this.randomAccessFile.close();
      }

      public long getPointer() throws IOException {
         return this.randomAccessFile.getFilePointer();
      }

      public int read(byte[] b) throws IOException {
         return this.randomAccessFile.read(b);
      }

      public void seek(long position) throws IOException {
         this.randomAccessFile.seek(position);
      }
   }

   private static final class TailablePath implements Tailable {
      private final Path path;
      private final LinkOption[] linkOptions;

      private TailablePath(Path path, LinkOption... linkOptions) {
         this.path = (Path)Objects.requireNonNull(path, "path");
         this.linkOptions = linkOptions;
      }

      Path getPath() {
         return this.path;
      }

      public RandomAccessResourceBridge getRandomAccess(String mode) throws FileNotFoundException {
         return new RandomAccessFileBridge(this.path.toFile(), mode);
      }

      public boolean isNewer(FileTime fileTime) throws IOException {
         return PathUtils.isNewer(this.path, fileTime, this.linkOptions);
      }

      public FileTime lastModifiedFileTime() throws IOException {
         return Files.getLastModifiedTime(this.path, this.linkOptions);
      }

      public long size() throws IOException {
         return Files.size(this.path);
      }

      public String toString() {
         return "TailablePath [file=" + this.path + ", linkOptions=" + Arrays.toString(this.linkOptions) + "]";
      }
   }

   public interface RandomAccessResourceBridge extends Closeable {
      long getPointer() throws IOException;

      int read(byte[] var1) throws IOException;

      void seek(long var1) throws IOException;
   }

   public interface Tailable {
      RandomAccessResourceBridge getRandomAccess(String var1) throws FileNotFoundException;

      boolean isNewer(FileTime var1) throws IOException;

      FileTime lastModifiedFileTime() throws IOException;

      long size() throws IOException;
   }
}
