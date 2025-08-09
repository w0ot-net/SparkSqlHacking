package org.apache.commons.io.build;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.function.IntUnaryOperator;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.file.PathUtils;

public abstract class AbstractStreamBuilder extends AbstractOriginSupplier {
   private static final int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;
   private static final OpenOption[] DEFAULT_OPEN_OPTIONS;
   private int bufferSize = 8192;
   private int bufferSizeDefault = 8192;
   private int bufferSizeMax = Integer.MAX_VALUE;
   private Charset charset = Charset.defaultCharset();
   private Charset charsetDefault = Charset.defaultCharset();
   private OpenOption[] openOptions;
   private final IntUnaryOperator defaultSizeChecker;
   private IntUnaryOperator bufferSizeChecker;

   public AbstractStreamBuilder() {
      this.openOptions = DEFAULT_OPEN_OPTIONS;
      this.defaultSizeChecker = (size) -> size > this.bufferSizeMax ? this.throwIae(size, this.bufferSizeMax) : size;
      this.bufferSizeChecker = this.defaultSizeChecker;
   }

   private int checkBufferSize(int size) {
      return this.bufferSizeChecker.applyAsInt(size);
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public int getBufferSizeDefault() {
      return this.bufferSizeDefault;
   }

   public CharSequence getCharSequence() throws IOException {
      return this.checkOrigin().getCharSequence(this.getCharset());
   }

   public Charset getCharset() {
      return this.charset;
   }

   public Charset getCharsetDefault() {
      return this.charsetDefault;
   }

   public File getFile() {
      return this.checkOrigin().getFile();
   }

   public InputStream getInputStream() throws IOException {
      return this.checkOrigin().getInputStream(this.getOpenOptions());
   }

   public OpenOption[] getOpenOptions() {
      return this.openOptions;
   }

   public OutputStream getOutputStream() throws IOException {
      return this.checkOrigin().getOutputStream(this.getOpenOptions());
   }

   public Path getPath() {
      return this.checkOrigin().getPath();
   }

   public RandomAccessFile getRandomAccessFile() throws IOException {
      return this.checkOrigin().getRandomAccessFile(this.getOpenOptions());
   }

   public Reader getReader() throws IOException {
      return this.checkOrigin().getReader(this.getCharset());
   }

   public Writer getWriter() throws IOException {
      return this.checkOrigin().getWriter(this.getCharset(), this.getOpenOptions());
   }

   public AbstractStreamBuilder setBufferSize(int bufferSize) {
      this.bufferSize = this.checkBufferSize(bufferSize > 0 ? bufferSize : this.bufferSizeDefault);
      return (AbstractStreamBuilder)this.asThis();
   }

   public AbstractStreamBuilder setBufferSize(Integer bufferSize) {
      this.setBufferSize(bufferSize != null ? bufferSize : this.bufferSizeDefault);
      return (AbstractStreamBuilder)this.asThis();
   }

   public AbstractStreamBuilder setBufferSizeChecker(IntUnaryOperator bufferSizeChecker) {
      this.bufferSizeChecker = bufferSizeChecker != null ? bufferSizeChecker : this.defaultSizeChecker;
      return (AbstractStreamBuilder)this.asThis();
   }

   protected AbstractStreamBuilder setBufferSizeDefault(int bufferSizeDefault) {
      this.bufferSizeDefault = bufferSizeDefault;
      return (AbstractStreamBuilder)this.asThis();
   }

   public AbstractStreamBuilder setBufferSizeMax(int bufferSizeMax) {
      this.bufferSizeMax = bufferSizeMax > 0 ? bufferSizeMax : Integer.MAX_VALUE;
      return (AbstractStreamBuilder)this.asThis();
   }

   public AbstractStreamBuilder setCharset(Charset charset) {
      this.charset = Charsets.toCharset(charset, this.charsetDefault);
      return (AbstractStreamBuilder)this.asThis();
   }

   public AbstractStreamBuilder setCharset(String charset) {
      return this.setCharset(Charsets.toCharset(charset, this.charsetDefault));
   }

   protected AbstractStreamBuilder setCharsetDefault(Charset defaultCharset) {
      this.charsetDefault = defaultCharset;
      return (AbstractStreamBuilder)this.asThis();
   }

   public AbstractStreamBuilder setOpenOptions(OpenOption... openOptions) {
      this.openOptions = openOptions != null ? openOptions : DEFAULT_OPEN_OPTIONS;
      return (AbstractStreamBuilder)this.asThis();
   }

   private int throwIae(int size, int max) {
      throw new IllegalArgumentException(String.format("Request %,d exceeds maximum %,d", size, max));
   }

   static {
      DEFAULT_OPEN_OPTIONS = PathUtils.EMPTY_OPEN_OPTION_ARRAY;
   }
}
