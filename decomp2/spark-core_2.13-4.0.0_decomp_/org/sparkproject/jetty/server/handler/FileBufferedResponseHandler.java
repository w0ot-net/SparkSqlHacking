package org.sparkproject.jetty.server.handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.server.HttpChannel;
import org.sparkproject.jetty.server.HttpOutput;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.Callback;
import org.sparkproject.jetty.util.IO;
import org.sparkproject.jetty.util.IteratingCallback;

public class FileBufferedResponseHandler extends BufferedResponseHandler {
   private static final Logger LOG = LoggerFactory.getLogger(FileBufferedResponseHandler.class);
   private Path _tempDir = (new File(System.getProperty("java.io.tmpdir"))).toPath();

   public Path getTempDir() {
      return this._tempDir;
   }

   public void setTempDir(Path tempDir) {
      this._tempDir = (Path)Objects.requireNonNull(tempDir);
   }

   protected BufferedResponseHandler.BufferedInterceptor newBufferedInterceptor(HttpChannel httpChannel, HttpOutput.Interceptor interceptor) {
      return new FileBufferedInterceptor(httpChannel, interceptor);
   }

   class FileBufferedInterceptor implements BufferedResponseHandler.BufferedInterceptor {
      private static final int MAX_MAPPED_BUFFER_SIZE = 1073741823;
      private final HttpOutput.Interceptor _next;
      private final HttpChannel _channel;
      private Boolean _aggregating;
      private Path _filePath;
      private OutputStream _fileOutputStream;

      public FileBufferedInterceptor(HttpChannel httpChannel, HttpOutput.Interceptor interceptor) {
         this._next = interceptor;
         this._channel = httpChannel;
      }

      public HttpOutput.Interceptor getNextInterceptor() {
         return this._next;
      }

      public void resetBuffer() {
         this.dispose();
         BufferedResponseHandler.BufferedInterceptor.super.resetBuffer();
      }

      protected void dispose() {
         IO.close(this._fileOutputStream);
         this._fileOutputStream = null;
         this._aggregating = null;
         if (this._filePath != null) {
            try {
               Files.delete(this._filePath);
            } catch (Throwable t) {
               if (FileBufferedResponseHandler.LOG.isDebugEnabled()) {
                  FileBufferedResponseHandler.LOG.debug("Could not immediately delete file (delaying to jvm exit) {}", this._filePath, t);
               }

               this._filePath.toFile().deleteOnExit();
            }

            this._filePath = null;
         }

      }

      public void write(ByteBuffer content, boolean last, Callback callback) {
         if (FileBufferedResponseHandler.LOG.isDebugEnabled()) {
            FileBufferedResponseHandler.LOG.debug("{} write last={} {}", new Object[]{this, last, BufferUtil.toDetailString(content)});
         }

         if (this._aggregating == null) {
            this._aggregating = FileBufferedResponseHandler.this.shouldBuffer(this._channel, last);
         }

         if (!this._aggregating) {
            this.getNextInterceptor().write(content, last, callback);
         } else {
            if (FileBufferedResponseHandler.LOG.isDebugEnabled()) {
               FileBufferedResponseHandler.LOG.debug("{} aggregating", this);
            }

            try {
               if (BufferUtil.hasContent(content)) {
                  this.aggregate(content);
               }
            } catch (Throwable t) {
               this.dispose();
               callback.failed(t);
               return;
            }

            if (last) {
               this.commit(callback);
            } else {
               callback.succeeded();
            }

         }
      }

      private void aggregate(ByteBuffer content) throws IOException {
         if (this._fileOutputStream == null) {
            this._filePath = Files.createTempFile(FileBufferedResponseHandler.this._tempDir, "BufferedResponse", "");
            this._fileOutputStream = Files.newOutputStream(this._filePath, StandardOpenOption.WRITE);
         }

         BufferUtil.writeTo(content, this._fileOutputStream);
      }

      private void commit(final Callback callback) {
         if (this._fileOutputStream == null) {
            this.getNextInterceptor().write(BufferUtil.EMPTY_BUFFER, true, callback);
         } else {
            try {
               this._fileOutputStream.close();
               this._fileOutputStream = null;
            } catch (Throwable t) {
               this.dispose();
               callback.failed(t);
               return;
            }

            IteratingCallback icb = new IteratingCallback() {
               private final long fileLength;
               private long _pos;
               private boolean _last;

               {
                  this.fileLength = FileBufferedInterceptor.this._filePath.toFile().length();
                  this._pos = 0L;
                  this._last = false;
               }

               protected IteratingCallback.Action process() throws Exception {
                  if (this._last) {
                     return IteratingCallback.Action.SUCCEEDED;
                  } else {
                     long len = Math.min(1073741823L, this.fileLength - this._pos);
                     this._last = this._pos + len == this.fileLength;
                     ByteBuffer buffer = BufferUtil.toMappedBuffer(FileBufferedInterceptor.this._filePath, this._pos, len);
                     FileBufferedInterceptor.this.getNextInterceptor().write(buffer, this._last, this);
                     this._pos += len;
                     return IteratingCallback.Action.SCHEDULED;
                  }
               }

               protected void onCompleteSuccess() {
                  FileBufferedInterceptor.this.dispose();
                  callback.succeeded();
               }

               protected void onCompleteFailure(Throwable cause) {
                  FileBufferedInterceptor.this.dispose();
                  callback.failed(cause);
               }
            };
            icb.iterate();
         }
      }
   }
}
