package io.vertx.core.http.impl;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.multipart.FileUpload;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.vertx.core.Context;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.streams.ReadStream;
import io.vertx.core.streams.impl.InboundBuffer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

final class NettyFileUpload implements FileUpload, ReadStream {
   private final String name;
   private String contentType;
   private String filename;
   private String contentTransferEncoding;
   private Charset charset;
   private boolean completed;
   private long maxSize = -1L;
   private final HttpServerRequest request;
   private final InboundBuffer pending;
   private Handler endHandler;
   private Handler exceptionHandler;
   private Handler dataHandler;
   private final long size;

   NettyFileUpload(Context context, HttpServerRequest request, String name, String filename, String contentType, String contentTransferEncoding, Charset charset, long size) {
      this.name = name;
      this.filename = filename;
      this.contentType = contentType;
      this.contentTransferEncoding = contentTransferEncoding;
      this.charset = charset;
      this.request = request;
      this.size = size;
      this.pending = (new InboundBuffer(context)).drainHandler((v) -> request.resume()).handler((buff) -> {
         if (buff == InboundBuffer.END_SENTINEL) {
            Handler<Void> handler = this.endHandler();
            if (handler != null) {
               handler.handle((Object)null);
            }
         } else {
            Handler<Buffer> handler = this.handler();
            if (handler != null) {
               handler.handle((Buffer)buff);
            }
         }

      });
   }

   public synchronized NettyFileUpload exceptionHandler(Handler handler) {
      this.exceptionHandler = handler;
      return this;
   }

   private Handler handler() {
      return this.dataHandler;
   }

   public synchronized NettyFileUpload handler(Handler handler) {
      this.dataHandler = handler;
      return this;
   }

   public NettyFileUpload pause() {
      this.pending.pause();
      return this;
   }

   public NettyFileUpload resume() {
      return this.fetch(Long.MAX_VALUE);
   }

   public NettyFileUpload fetch(long amount) {
      this.pending.fetch(amount);
      return this;
   }

   private synchronized Handler endHandler() {
      return this.endHandler;
   }

   public synchronized NettyFileUpload endHandler(Handler handler) {
      this.endHandler = handler;
      return this;
   }

   private void receiveData(Buffer data) {
      if (data.length() != 0 && !this.pending.write((Object)data)) {
         this.request.pause();
      }

   }

   private void end() {
      this.pending.write(InboundBuffer.END_SENTINEL);
   }

   public void handleException(Throwable err) {
      Handler<Throwable> handler;
      synchronized(this) {
         handler = this.exceptionHandler;
      }

      if (handler != null) {
         handler.handle(err);
      }

   }

   public void setContent(ByteBuf channelBuffer) throws IOException {
      this.completed = true;
      this.receiveData(Buffer.buffer(channelBuffer));
      this.end();
   }

   public void addContent(ByteBuf channelBuffer, boolean last) throws IOException {
      this.receiveData(Buffer.buffer(channelBuffer));
      if (last) {
         this.completed = true;
         this.end();
      }

   }

   public void setContent(File file) throws IOException {
      throw new UnsupportedOperationException();
   }

   public void setContent(InputStream inputStream) throws IOException {
      throw new UnsupportedOperationException();
   }

   public boolean isCompleted() {
      return this.completed;
   }

   public long length() {
      return this.size;
   }

   public void delete() {
      throw new UnsupportedOperationException();
   }

   public long definedLength() {
      return this.size;
   }

   public void checkSize(long newSize) throws IOException {
      if (this.maxSize >= 0L && newSize > this.maxSize) {
         throw new IOException("Size exceed allowed maximum capacity");
      }
   }

   public long getMaxSize() {
      return this.maxSize;
   }

   public void setMaxSize(long maxSize) {
      this.maxSize = maxSize;
   }

   public byte[] get() throws IOException {
      throw new UnsupportedOperationException();
   }

   public ByteBuf getChunk(int i) throws IOException {
      throw new UnsupportedOperationException();
   }

   public String getString() throws IOException {
      throw new UnsupportedOperationException();
   }

   public String getString(Charset charset) throws IOException {
      throw new UnsupportedOperationException();
   }

   public void setCharset(Charset charset) {
      this.charset = charset;
   }

   public Charset getCharset() {
      return this.charset;
   }

   public boolean renameTo(File file) throws IOException {
      throw new UnsupportedOperationException();
   }

   public boolean isInMemory() {
      return false;
   }

   public File getFile() throws IOException {
      throw new UnsupportedOperationException();
   }

   public String getName() {
      return this.name;
   }

   public InterfaceHttpData.HttpDataType getHttpDataType() {
      throw new UnsupportedOperationException();
   }

   public int compareTo(InterfaceHttpData o) {
      return 0;
   }

   public String getFilename() {
      return this.filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public void setContentType(String contentType) {
      this.contentType = contentType;
   }

   public String getContentType() {
      return this.contentType;
   }

   public void setContentTransferEncoding(String contentTransferEncoding) {
      this.contentTransferEncoding = contentTransferEncoding;
   }

   public String getContentTransferEncoding() {
      return this.contentTransferEncoding;
   }

   public ByteBuf getByteBuf() throws IOException {
      throw new UnsupportedOperationException();
   }

   public FileUpload copy() {
      throw new UnsupportedOperationException();
   }

   public FileUpload duplicate() {
      throw new UnsupportedOperationException();
   }

   public FileUpload retainedDuplicate() {
      throw new UnsupportedOperationException();
   }

   public FileUpload replace(ByteBuf content) {
      throw new UnsupportedOperationException();
   }

   public FileUpload retain() {
      return this;
   }

   public FileUpload retain(int increment) {
      return this;
   }

   public FileUpload touch(Object hint) {
      return this;
   }

   public FileUpload touch() {
      return this;
   }

   public ByteBuf content() {
      throw new UnsupportedOperationException();
   }

   public int refCnt() {
      return 1;
   }

   public boolean release() {
      return false;
   }

   public boolean release(int decrement) {
      return false;
   }
}
