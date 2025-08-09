package org.sparkproject.jetty.server;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.BufferUtil;
import org.sparkproject.jetty.util.ByteArrayOutputStream2;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.QuotedStringTokenizer;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.thread.AutoLock;

public class MultiPartFormInputStream {
   private static final Logger LOG = LoggerFactory.getLogger(MultiPartFormInputStream.class);
   private final AutoLock _lock;
   private final MultiMap _parts;
   private final EnumSet _nonComplianceWarnings;
   private final InputStream _in;
   private final MultipartConfigElement _config;
   private final File _contextTmpDir;
   private final String _contentType;
   private final int _maxParts;
   private int _numParts;
   private volatile Throwable _err;
   private volatile Path _tmpDir;
   private volatile boolean _deleteOnExit;
   private volatile boolean _writeFilesWithFilenames;
   private volatile int _bufferSize;
   private State state;

   public EnumSet getNonComplianceWarnings() {
      return this._nonComplianceWarnings;
   }

   public MultiPartFormInputStream(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir) {
      this(in, contentType, config, contextTmpDir, 1000);
   }

   public MultiPartFormInputStream(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir, int maxParts) {
      this._lock = new AutoLock();
      this._parts = new MultiMap();
      this._nonComplianceWarnings = EnumSet.noneOf(MultiParts.NonCompliance.class);
      this._bufferSize = 16384;
      this.state = MultiPartFormInputStream.State.UNPARSED;
      this._contentType = contentType;
      if (this._contentType != null && this._contentType.startsWith("multipart/form-data")) {
         this._contextTmpDir = contextTmpDir != null ? contextTmpDir : new File(System.getProperty("java.io.tmpdir"));
         this._config = config != null ? config : new MultipartConfigElement(this._contextTmpDir.getAbsolutePath());
         this._maxParts = maxParts;
         if (in instanceof ServletInputStream && ((ServletInputStream)in).isFinished()) {
            this._in = null;
            this.state = MultiPartFormInputStream.State.PARSED;
         } else {
            this._in = new BufferedInputStream(in);
         }
      } else {
         throw new IllegalArgumentException("content type is not multipart/form-data");
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean isEmpty() {
      if (this._parts.isEmpty()) {
         return true;
      } else {
         for(List partList : this._parts.values()) {
            if (!partList.isEmpty()) {
               return false;
            }
         }

         return true;
      }
   }

   public void deleteParts() {
      try (AutoLock l = this._lock.lock()) {
         switch (this.state.ordinal()) {
            case 0:
               this.state = MultiPartFormInputStream.State.DELETED;
               return;
            case 1:
               this.state = MultiPartFormInputStream.State.DELETING;
               return;
            case 2:
               this.state = MultiPartFormInputStream.State.DELETED;
               break;
            case 3:
            case 4:
               return;
         }
      }

      this.delete();
   }

   private void delete() {
      MultiException err = null;

      for(List parts : this._parts.values()) {
         for(Part p : parts) {
            try {
               ((MultiPart)p).cleanUp();
            } catch (Exception e) {
               if (err == null) {
                  err = new MultiException();
               }

               err.add(e);
            }
         }
      }

      this._parts.clear();
      if (err != null) {
         err.ifExceptionThrowRuntime();
      }

   }

   public Collection getParts() throws IOException {
      this.parse();
      this.throwIfError();
      return (Collection)this._parts.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
   }

   public Part getPart(String name) throws IOException {
      this.parse();
      this.throwIfError();
      return (Part)this._parts.getValue(name, 0);
   }

   protected void throwIfError() throws IOException {
      if (this._err != null) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("MultiPart parsing failure ", this._err);
         }

         this._err.addSuppressed(new Throwable());
         if (this._err instanceof IOException) {
            throw (IOException)this._err;
         } else if (this._err instanceof IllegalStateException) {
            throw (IllegalStateException)this._err;
         } else {
            throw new IllegalStateException(this._err);
         }
      }
   }

   protected void parse() {
      try (AutoLock l = this._lock.lock()) {
         switch (this.state.ordinal()) {
            case 0:
               this.state = MultiPartFormInputStream.State.PARSING;
               break;
            case 2:
               return;
            default:
               this._err = new IOException(this.state.name());
               return;
         }
      }

      MultiPartParser parser = null;
      boolean var36 = false;

      label689: {
         label690: {
            label752: {
               label692: {
                  try {
                     AutoLock l;
                     label693: {
                        var36 = true;
                        if (StringUtil.isBlank(this._config.getLocation())) {
                           this._tmpDir = this._contextTmpDir.toPath();
                        } else {
                           Path location = FileSystems.getDefault().getPath(this._config.getLocation());
                           this._tmpDir = location.isAbsolute() ? location : this._contextTmpDir.toPath().resolve(location);
                        }

                        if (!Files.exists(this._tmpDir, new LinkOption[0])) {
                           Files.createDirectories(this._tmpDir);
                        }

                        String contentTypeBoundary = "";
                        int bstart = this._contentType.indexOf("boundary=");
                        if (bstart >= 0) {
                           int bend = this._contentType.indexOf(";", bstart);
                           bend = bend < 0 ? this._contentType.length() : bend;
                           contentTypeBoundary = QuotedStringTokenizer.unquote(value(this._contentType.substring(bstart, bend)).trim());
                        }

                        parser = new MultiPartParser(new Handler(), contentTypeBoundary);
                        byte[] data = new byte[this._bufferSize];
                        long total = 0L;

                        while(true) {
                           l = this._lock.lock();

                           try {
                              if (this.state != MultiPartFormInputStream.State.PARSING) {
                                 this._err = new IOException(this.state.name());
                                 break label693;
                              }
                           } catch (Throwable var51) {
                              if (l != null) {
                                 try {
                                    l.close();
                                 } catch (Throwable var44) {
                                    var51.addSuppressed(var44);
                                 }
                              }

                              throw var51;
                           }

                           if (l != null) {
                              l.close();
                           }

                           int len = this._in.read(data);
                           if (len > 0) {
                              total += (long)len;
                              if (this._config.getMaxRequestSize() > 0L && total > this._config.getMaxRequestSize()) {
                                 this._err = new IllegalStateException("Request exceeds maxRequestSize (" + this._config.getMaxRequestSize() + ")");
                                 var36 = false;
                                 break label752;
                              }

                              ByteBuffer buffer = BufferUtil.toBuffer(data);
                              buffer.limit(len);
                              if (parser.parse(buffer, false)) {
                                 break;
                              }

                              if (buffer.hasRemaining()) {
                                 throw new IllegalStateException("Buffer did not fully consume");
                              }
                           } else if (len == -1) {
                              parser.parse(BufferUtil.EMPTY_BUFFER, true);
                              break;
                           }
                        }

                        if (this._err != null) {
                           var36 = false;
                           break label690;
                        }

                        if (parser.getState() != MultiPartParser.State.END) {
                           if (parser.getState() == MultiPartParser.State.PREAMBLE) {
                              this._err = new IOException("Missing initial multi part boundary");
                           } else {
                              this._err = new IOException("Incomplete Multipart");
                           }
                        }

                        if (LOG.isDebugEnabled()) {
                           LOG.debug("Parsing Complete {} err={}", parser, this._err);
                           var36 = false;
                        } else {
                           var36 = false;
                        }
                        break label689;
                     }

                     if (l != null) {
                        l.close();
                        var36 = false;
                     } else {
                        var36 = false;
                     }
                  } catch (Throwable e) {
                     this._err = e;
                     if (parser != null) {
                        parser.parse(BufferUtil.EMPTY_BUFFER, true);
                        var36 = false;
                        break label692;
                     }

                     var36 = false;
                     break label692;
                  } finally {
                     if (var36) {
                        boolean cleanup = false;

                        try (AutoLock var15 = this._lock.lock()) {
                           switch (this.state.ordinal()) {
                              case 1:
                                 this.state = MultiPartFormInputStream.State.PARSED;
                                 break;
                              case 3:
                                 this.state = MultiPartFormInputStream.State.DELETED;
                                 cleanup = true;
                                 break;
                              default:
                                 this._err = new IllegalStateException(this.state.name());
                           }
                        }

                        if (cleanup) {
                           this.delete();
                        }

                     }
                  }

                  boolean cleanup = false;

                  try (AutoLock l = this._lock.lock()) {
                     switch (this.state.ordinal()) {
                        case 1:
                           this.state = MultiPartFormInputStream.State.PARSED;
                           break;
                        case 3:
                           this.state = MultiPartFormInputStream.State.DELETED;
                           cleanup = true;
                           break;
                        default:
                           this._err = new IllegalStateException(this.state.name());
                     }
                  }

                  if (cleanup) {
                     this.delete();
                  }

                  return;
               }

               boolean cleanup = false;

               try (AutoLock l = this._lock.lock()) {
                  switch (this.state.ordinal()) {
                     case 1:
                        this.state = MultiPartFormInputStream.State.PARSED;
                        break;
                     case 3:
                        this.state = MultiPartFormInputStream.State.DELETED;
                        cleanup = true;
                        break;
                     default:
                        this._err = new IllegalStateException(this.state.name());
                  }
               }

               if (cleanup) {
                  this.delete();
               }

               return;
            }

            boolean cleanup = false;

            try (AutoLock l = this._lock.lock()) {
               switch (this.state.ordinal()) {
                  case 1:
                     this.state = MultiPartFormInputStream.State.PARSED;
                     break;
                  case 3:
                     this.state = MultiPartFormInputStream.State.DELETED;
                     cleanup = true;
                     break;
                  default:
                     this._err = new IllegalStateException(this.state.name());
               }
            }

            if (cleanup) {
               this.delete();
            }

            return;
         }

         boolean cleanup = false;

         try (AutoLock l = this._lock.lock()) {
            switch (this.state.ordinal()) {
               case 1:
                  this.state = MultiPartFormInputStream.State.PARSED;
                  break;
               case 3:
                  this.state = MultiPartFormInputStream.State.DELETED;
                  cleanup = true;
                  break;
               default:
                  this._err = new IllegalStateException(this.state.name());
            }
         }

         if (cleanup) {
            this.delete();
         }

         return;
      }

      boolean cleanup = false;

      try (AutoLock l = this._lock.lock()) {
         switch (this.state.ordinal()) {
            case 1:
               this.state = MultiPartFormInputStream.State.PARSED;
               break;
            case 3:
               this.state = MultiPartFormInputStream.State.DELETED;
               cleanup = true;
               break;
            default:
               this._err = new IllegalStateException(this.state.name());
         }
      }

      if (cleanup) {
         this.delete();
      }

   }

   /** @deprecated */
   @Deprecated
   public void setDeleteOnExit(boolean deleteOnExit) {
   }

   public void setWriteFilesWithFilenames(boolean writeFilesWithFilenames) {
      this._writeFilesWithFilenames = writeFilesWithFilenames;
   }

   public boolean isWriteFilesWithFilenames() {
      return this._writeFilesWithFilenames;
   }

   /** @deprecated */
   @Deprecated
   public boolean isDeleteOnExit() {
      return false;
   }

   private static String value(String nameEqualsValue) {
      int idx = nameEqualsValue.indexOf(61);
      String value = nameEqualsValue.substring(idx + 1).trim();
      return QuotedStringTokenizer.unquoteOnly(value);
   }

   private static String filenameValue(String nameEqualsValue) {
      int idx = nameEqualsValue.indexOf(61);
      String value = nameEqualsValue.substring(idx + 1).trim();
      if (!value.matches(".??[a-z,A-Z]\\:\\\\[^\\\\].*")) {
         return QuotedStringTokenizer.unquoteOnly(value, true);
      } else {
         char first = value.charAt(0);
         if (first == '"' || first == '\'') {
            value = value.substring(1);
         }

         char last = value.charAt(value.length() - 1);
         if (last == '"' || last == '\'') {
            value = value.substring(0, value.length() - 1);
         }

         return value;
      }
   }

   public int getBufferSize() {
      return this._bufferSize;
   }

   public void setBufferSize(int bufferSize) {
      this._bufferSize = bufferSize;
   }

   private static enum State {
      UNPARSED,
      PARSING,
      PARSED,
      DELETING,
      DELETED;

      // $FF: synthetic method
      private static State[] $values() {
         return new State[]{UNPARSED, PARSING, PARSED, DELETING, DELETED};
      }
   }

   public class MultiPart implements Part {
      protected String _name;
      protected String _filename;
      protected File _file;
      protected OutputStream _out;
      protected ByteArrayOutputStream2 _bout;
      protected String _contentType;
      protected MultiMap _headers;
      protected long _size = 0L;
      protected boolean _temporary = true;

      public MultiPart(String name, String filename) {
         this._name = name;
         this._filename = filename;
      }

      public String toString() {
         return String.format("Part{n=%s,fn=%s,ct=%s,s=%d,tmp=%b,file=%s}", this._name, this._filename, this._contentType, this._size, this._temporary, this._file);
      }

      protected void setContentType(String contentType) {
         this._contentType = contentType;
      }

      protected void open() throws IOException {
         if (MultiPartFormInputStream.this.isWriteFilesWithFilenames() && this._filename != null && !this._filename.trim().isEmpty()) {
            this.createFile();
         } else {
            this._out = this._bout = new ByteArrayOutputStream2();
         }

      }

      protected void close() throws IOException {
         this._out.close();
      }

      protected void write(int b) throws IOException {
         if (MultiPartFormInputStream.this._config.getMaxFileSize() > 0L && this._size + 1L > MultiPartFormInputStream.this._config.getMaxFileSize()) {
            throw new IllegalStateException("Multipart Mime part " + this._name + " exceeds max filesize");
         } else {
            if (MultiPartFormInputStream.this._config.getFileSizeThreshold() > 0 && this._size + 1L > (long)MultiPartFormInputStream.this._config.getFileSizeThreshold() && this._file == null) {
               this.createFile();
            }

            this._out.write(b);
            ++this._size;
         }
      }

      protected void write(byte[] bytes, int offset, int length) throws IOException {
         if (MultiPartFormInputStream.this._config.getMaxFileSize() > 0L && this._size + (long)length > MultiPartFormInputStream.this._config.getMaxFileSize()) {
            throw new IllegalStateException("Multipart Mime part " + this._name + " exceeds max filesize");
         } else {
            if (MultiPartFormInputStream.this._config.getFileSizeThreshold() > 0 && this._size + (long)length > (long)MultiPartFormInputStream.this._config.getFileSizeThreshold() && this._file == null) {
               this.createFile();
            }

            this._out.write(bytes, offset, length);
            this._size += (long)length;
         }
      }

      public void write(String fileName) throws IOException {
         Path p = Path.of(fileName);
         if (!p.isAbsolute()) {
            p = MultiPartFormInputStream.this._tmpDir.resolve(p);
         }

         if (this._file == null) {
            this._temporary = false;
            this._file = Files.createFile(p).toFile();

            try {
               BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(this._file));

               try {
                  this._bout.writeTo(bos);
                  bos.flush();
               } catch (Throwable var11) {
                  try {
                     bos.close();
                  } catch (Throwable var10) {
                     var11.addSuppressed(var10);
                  }

                  throw var11;
               }

               bos.close();
            } finally {
               this._bout = null;
            }
         } else {
            this._temporary = false;
            Path src = this._file.toPath();
            Files.move(src, p, StandardCopyOption.REPLACE_EXISTING);
            this._file = p.toFile();
         }

      }

      protected void createFile() throws IOException {
         Path parent = MultiPartFormInputStream.this._tmpDir;
         Path tempFile = Files.createTempFile(parent, "MultiPart", "");
         this._file = tempFile.toFile();
         OutputStream fos = Files.newOutputStream(tempFile, StandardOpenOption.WRITE);
         BufferedOutputStream bos = new BufferedOutputStream(fos);
         if (this._size > 0L && this._out != null) {
            this._out.flush();
            this._bout.writeTo(bos);
            this._out.close();
         }

         this._bout = null;
         this._out = bos;
      }

      protected void setHeaders(MultiMap headers) {
         this._headers = headers;
      }

      public String getContentType() {
         return this._contentType;
      }

      public String getHeader(String name) {
         return name == null ? null : (String)this._headers.getValue(StringUtil.asciiToLowerCase(name), 0);
      }

      public Collection getHeaderNames() {
         return this._headers.keySet();
      }

      public Collection getHeaders(String name) {
         Collection<String> headers = this._headers.getValues(name);
         return (Collection)(headers == null ? Collections.emptyList() : headers);
      }

      public InputStream getInputStream() throws IOException {
         return (InputStream)(this._file != null ? new BufferedInputStream(new FileInputStream(this._file)) : new ByteArrayInputStream(this._bout.getBuf(), 0, this._bout.size()));
      }

      public String getSubmittedFileName() {
         return this.getContentDispositionFilename();
      }

      public byte[] getBytes() {
         return this._bout != null ? this._bout.toByteArray() : null;
      }

      public String getName() {
         return this._name;
      }

      public long getSize() {
         return this._size;
      }

      public void delete() throws IOException {
         if (this._file != null && this._file.exists() && !this._file.delete()) {
            throw new IOException("Could Not Delete File");
         }
      }

      public void cleanUp() throws IOException {
         if (this._temporary) {
            this.delete();
         }

      }

      public File getFile() {
         return this._file;
      }

      public String getContentDispositionFilename() {
         return this._filename;
      }
   }

   class Handler implements MultiPartParser.Handler {
      private MultiPart _part = null;
      private String contentDisposition = null;
      private String contentType = null;
      private MultiMap headers = new MultiMap();

      public boolean messageComplete() {
         return true;
      }

      public void parsedField(String key, String value) {
         this.headers.put(StringUtil.asciiToLowerCase(key), value);
         if (key.equalsIgnoreCase("content-disposition")) {
            this.contentDisposition = value;
         } else if (key.equalsIgnoreCase("content-type")) {
            this.contentType = value;
         }

         if (key.equalsIgnoreCase("content-transfer-encoding") && !"8bit".equalsIgnoreCase(value) && !"binary".equalsIgnoreCase(value)) {
            MultiPartFormInputStream.this._nonComplianceWarnings.add(MultiParts.NonCompliance.TRANSFER_ENCODING);
         }

      }

      public boolean headerComplete() {
         if (MultiPartFormInputStream.LOG.isDebugEnabled()) {
            MultiPartFormInputStream.LOG.debug("headerComplete {}", this);
         }

         try {
            boolean formData = false;
            if (this.contentDisposition == null) {
               throw new IOException("Missing content-disposition");
            } else {
               QuotedStringTokenizer tok = new QuotedStringTokenizer(this.contentDisposition, ";", false, true);
               String name = null;
               String filename = null;

               while(tok.hasMoreTokens()) {
                  String t = tok.nextToken().trim();
                  String tl = StringUtil.asciiToLowerCase(t);
                  if (tl.startsWith("form-data")) {
                     formData = true;
                  } else if (tl.startsWith("name=")) {
                     name = MultiPartFormInputStream.value(t);
                  } else if (tl.startsWith("filename=")) {
                     filename = MultiPartFormInputStream.filenameValue(t);
                  }
               }

               if (!formData) {
                  throw new IOException("Part not form-data");
               } else if (name == null) {
                  throw new IOException("No name in part");
               } else {
                  this._part = MultiPartFormInputStream.this.new MultiPart(name, filename);
                  this._part.setHeaders(this.headers);
                  this._part.setContentType(this.contentType);
                  MultiPartFormInputStream.this._parts.add(name, this._part);

                  try {
                     this._part.open();
                     return false;
                  } catch (IOException e) {
                     MultiPartFormInputStream.this._err = e;
                     return true;
                  }
               }
            }
         } catch (Exception e) {
            MultiPartFormInputStream.this._err = e;
            return true;
         }
      }

      public boolean content(ByteBuffer buffer, boolean last) {
         if (this._part == null) {
            return false;
         } else {
            if (BufferUtil.hasContent(buffer)) {
               try {
                  this._part.write(buffer.array(), buffer.arrayOffset() + buffer.position(), buffer.remaining());
               } catch (IOException e) {
                  MultiPartFormInputStream.this._err = e;
                  return true;
               }
            }

            if (last) {
               try {
                  this._part.close();
               } catch (IOException e) {
                  MultiPartFormInputStream.this._err = e;
                  return true;
               }
            }

            return false;
         }
      }

      public void startPart() {
         this.reset();
         ++MultiPartFormInputStream.this._numParts;
         if (MultiPartFormInputStream.this._maxParts >= 0 && MultiPartFormInputStream.this._numParts > MultiPartFormInputStream.this._maxParts) {
            throw new IllegalStateException(String.format("Form with too many parts [%d > %d]", MultiPartFormInputStream.this._numParts, MultiPartFormInputStream.this._maxParts));
         }
      }

      public void earlyEOF() {
         if (MultiPartFormInputStream.LOG.isDebugEnabled()) {
            MultiPartFormInputStream.LOG.debug("Early EOF {}", MultiPartFormInputStream.this);
         }

         try {
            if (this._part != null) {
               this._part.close();
            }
         } catch (IOException e) {
            MultiPartFormInputStream.LOG.warn("part could not be closed", e);
         }

      }

      public void reset() {
         this._part = null;
         this.contentDisposition = null;
         this.contentType = null;
         this.headers = new MultiMap();
      }
   }
}
