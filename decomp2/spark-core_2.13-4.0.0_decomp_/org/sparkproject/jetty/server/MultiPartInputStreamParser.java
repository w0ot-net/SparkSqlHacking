package org.sparkproject.jetty.server;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.util.ByteArrayOutputStream2;
import org.sparkproject.jetty.util.LazyList;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.MultiMap;
import org.sparkproject.jetty.util.QuotedStringTokenizer;
import org.sparkproject.jetty.util.ReadLineInputStream;

/** @deprecated */
@Deprecated
public class MultiPartInputStreamParser {
   private static final Logger LOG = LoggerFactory.getLogger(MultiPartInputStreamParser.class);
   public static final MultipartConfigElement __DEFAULT_MULTIPART_CONFIG = new MultipartConfigElement(System.getProperty("java.io.tmpdir"));
   public static final MultiMap EMPTY_MAP = new MultiMap(Collections.emptyMap());
   private final int _maxParts;
   private int _numParts;
   protected InputStream _in;
   protected MultipartConfigElement _config;
   protected String _contentType;
   protected MultiMap _parts;
   protected Exception _err;
   protected File _tmpDir;
   protected File _contextTmpDir;
   protected boolean _writeFilesWithFilenames;
   protected boolean _parsed;
   private final EnumSet nonComplianceWarnings;

   public EnumSet getNonComplianceWarnings() {
      return this.nonComplianceWarnings;
   }

   public MultiPartInputStreamParser(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir) {
      this(in, contentType, config, contextTmpDir, 1000);
   }

   public MultiPartInputStreamParser(InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir, int maxParts) {
      this.nonComplianceWarnings = EnumSet.noneOf(MultiParts.NonCompliance.class);
      this._contentType = contentType;
      this._config = config;
      this._contextTmpDir = contextTmpDir;
      this._maxParts = maxParts;
      if (this._contextTmpDir == null) {
         this._contextTmpDir = new File(System.getProperty("java.io.tmpdir"));
      }

      if (this._config == null) {
         this._config = new MultipartConfigElement(this._contextTmpDir.getAbsolutePath());
      }

      if (in instanceof ServletInputStream && ((ServletInputStream)in).isFinished()) {
         this._parts = EMPTY_MAP;
         this._parsed = true;
      } else {
         this._in = new ReadLineInputStream(in);
      }
   }

   public Collection getParsedParts() {
      if (this._parts == null) {
         return Collections.emptyList();
      } else {
         Collection<List<Part>> values = this._parts.values();
         List<Part> parts = new ArrayList();

         for(List o : values) {
            List<Part> asList = LazyList.getList(o, false);
            parts.addAll(asList);
         }

         return parts;
      }
   }

   public void deleteParts() {
      if (this._parsed) {
         Collection<Part> parts = this.getParsedParts();
         MultiException err = new MultiException();

         for(Part p : parts) {
            try {
               ((MultiPart)p).cleanUp();
            } catch (Exception e) {
               err.add(e);
            }
         }

         this._parts.clear();
         err.ifExceptionThrowRuntime();
      }
   }

   public Collection getParts() throws IOException {
      if (!this._parsed) {
         this.parse();
      }

      this.throwIfError();
      Collection<List<Part>> values = this._parts.values();
      List<Part> parts = new ArrayList();

      for(List o : values) {
         List<Part> asList = LazyList.getList(o, false);
         parts.addAll(asList);
      }

      return parts;
   }

   public Part getPart(String name) throws IOException {
      if (!this._parsed) {
         this.parse();
      }

      this.throwIfError();
      return (Part)this._parts.getValue(name, 0);
   }

   protected void throwIfError() throws IOException {
      if (this._err != null) {
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
      if (!this._parsed) {
         this._parsed = true;
         long total = 0L;
         this._parts = new MultiMap();
         if (this._contentType != null && this._contentType.startsWith("multipart/form-data")) {
            try {
               if (this._config.getLocation() == null) {
                  this._tmpDir = this._contextTmpDir;
               } else if ("".equals(this._config.getLocation())) {
                  this._tmpDir = this._contextTmpDir;
               } else {
                  File f = new File(this._config.getLocation());
                  if (f.isAbsolute()) {
                     this._tmpDir = f;
                  } else {
                     this._tmpDir = new File(this._contextTmpDir, this._config.getLocation());
                  }
               }

               if (!this._tmpDir.exists()) {
                  this._tmpDir.mkdirs();
               }

               String contentTypeBoundary = "";
               int bstart = this._contentType.indexOf("boundary=");
               if (bstart >= 0) {
                  int bend = this._contentType.indexOf(";", bstart);
                  bend = bend < 0 ? this._contentType.length() : bend;
                  contentTypeBoundary = QuotedStringTokenizer.unquote(this.value(this._contentType.substring(bstart, bend)).trim());
               }

               String boundary = "--" + contentTypeBoundary;
               String lastBoundary = boundary + "--";
               byte[] byteBoundary = lastBoundary.getBytes(StandardCharsets.ISO_8859_1);
               String line = null;

               try {
                  line = ((ReadLineInputStream)this._in).readLine();
               } catch (IOException e) {
                  LOG.warn("Badly formatted multipart request");
                  throw e;
               }

               if (line == null) {
                  throw new IOException("Missing content for multipart request");
               }

               boolean badFormatLogged = false;
               String untrimmed = line;
               line = line.trim();

               while(line != null && !line.equals(boundary) && !line.equals(lastBoundary)) {
                  if (!badFormatLogged) {
                     LOG.warn("Badly formatted multipart request");
                     badFormatLogged = true;
                  }

                  line = ((ReadLineInputStream)this._in).readLine();
                  untrimmed = line;
                  if (line != null) {
                     line = line.trim();
                  }
               }

               if (line == null || line.length() == 0) {
                  throw new IOException("Missing initial multi part boundary");
               }

               if (line.equals(lastBoundary)) {
                  return;
               }

               if (Character.isWhitespace(untrimmed.charAt(0))) {
                  this.nonComplianceWarnings.add(MultiParts.NonCompliance.NO_CRLF_AFTER_PREAMBLE);
               }

               boolean lastPart = false;

               label632:
               while(!lastPart) {
                  String contentDisposition = null;
                  String contentType = null;
                  String contentTransferEncoding = null;
                  MultiMap<String> headers = new MultiMap();

                  while(true) {
                     line = ((ReadLineInputStream)this._in).readLine();
                     if (line == null) {
                        break label632;
                     }

                     if ("".equals(line)) {
                        boolean formData = false;
                        if (contentDisposition == null) {
                           throw new IOException("Missing content-disposition");
                        }

                        QuotedStringTokenizer tok = new QuotedStringTokenizer(contentDisposition, ";", false, true);
                        String name = null;
                        String filename = null;

                        while(tok.hasMoreTokens()) {
                           String t = tok.nextToken().trim();
                           String tl = t.toLowerCase(Locale.ENGLISH);
                           if (tl.startsWith("form-data")) {
                              formData = true;
                           } else if (tl.startsWith("name=")) {
                              name = this.value(t);
                           } else if (tl.startsWith("filename=")) {
                              filename = this.filenameValue(t);
                           }
                        }

                        if (formData && name != null) {
                           ++this._numParts;
                           if (this._maxParts >= 0 && this._numParts > this._maxParts) {
                              throw new IllegalStateException(String.format("Form with too many parts [%d > %d]", this._numParts, this._maxParts));
                           }

                           MultiPart part = new MultiPart(name, filename);
                           part.setHeaders(headers);
                           part.setContentType(contentType);
                           this._parts.add(name, part);
                           part.open();
                           InputStream partInput = null;
                           if ("base64".equalsIgnoreCase(contentTransferEncoding)) {
                              this.nonComplianceWarnings.add(MultiParts.NonCompliance.BASE64_TRANSFER_ENCODING);
                              partInput = new Base64InputStream((ReadLineInputStream)this._in);
                           } else if ("quoted-printable".equalsIgnoreCase(contentTransferEncoding)) {
                              this.nonComplianceWarnings.add(MultiParts.NonCompliance.QUOTED_PRINTABLE_TRANSFER_ENCODING);
                              partInput = new FilterInputStream(this._in) {
                                 public int read() throws IOException {
                                    int c = this.in.read();
                                    if (c >= 0 && c == 61) {
                                       int hi = this.in.read();
                                       int lo = this.in.read();
                                       if (hi < 0 || lo < 0) {
                                          throw new IOException("Unexpected end to quoted-printable byte");
                                       }

                                       char[] chars = new char[]{(char)hi, (char)lo};
                                       c = Integer.parseInt(new String(chars), 16);
                                    }

                                    return c;
                                 }
                              };
                           } else {
                              partInput = this._in;
                           }

                           try {
                              int state = -2;
                              boolean cr = false;
                              boolean lf = false;

                              while(true) {
                                 int b = 0;

                                 int c;
                                 while((c = state != -2 ? state : partInput.read()) != -1) {
                                    ++total;
                                    if (this._config.getMaxRequestSize() > 0L && total > this._config.getMaxRequestSize()) {
                                       throw new IllegalStateException("Request exceeds maxRequestSize (" + this._config.getMaxRequestSize() + ")");
                                    }

                                    state = -2;
                                    if (c == 13 || c == 10) {
                                       if (c == 13) {
                                          partInput.mark(1);
                                          int tmp = partInput.read();
                                          if (tmp != 10) {
                                             partInput.reset();
                                          } else {
                                             state = tmp;
                                          }
                                       }
                                       break;
                                    }

                                    if (b >= 0 && b < byteBoundary.length && c == byteBoundary[b]) {
                                       ++b;
                                    } else {
                                       if (cr) {
                                          part.write(13);
                                       }

                                       if (lf) {
                                          part.write(10);
                                       }

                                       lf = false;
                                       cr = false;
                                       if (b > 0) {
                                          part.write(byteBoundary, 0, b);
                                       }

                                       b = -1;
                                       part.write(c);
                                    }
                                 }

                                 if (b > 0 && b < byteBoundary.length - 2 || b == byteBoundary.length - 1) {
                                    if (cr) {
                                       part.write(13);
                                    }

                                    if (lf) {
                                       part.write(10);
                                    }

                                    lf = false;
                                    cr = false;
                                    part.write(byteBoundary, 0, b);
                                    b = -1;
                                 }

                                 if (b > 0 || c == -1) {
                                    if (b == byteBoundary.length) {
                                       lastPart = true;
                                    }

                                    if (state == 10) {
                                       state = -2;
                                    }
                                    continue label632;
                                 }

                                 if (cr) {
                                    part.write(13);
                                 }

                                 if (lf) {
                                    part.write(10);
                                 }

                                 cr = c == 13;
                                 lf = c == 10 || state == 10;
                                 if (state == 10) {
                                    state = -2;
                                 }
                              }
                           } finally {
                              part.close();
                           }
                        }
                        break;
                     }

                     total += (long)line.length();
                     if (this._config.getMaxRequestSize() > 0L && total > this._config.getMaxRequestSize()) {
                        throw new IllegalStateException("Request exceeds maxRequestSize (" + this._config.getMaxRequestSize() + ")");
                     }

                     int c = line.indexOf(58);
                     if (c > 0) {
                        String key = line.substring(0, c).trim().toLowerCase(Locale.ENGLISH);
                        String value = line.substring(c + 1).trim();
                        headers.put(key, value);
                        if (key.equalsIgnoreCase("content-disposition")) {
                           contentDisposition = value;
                        }

                        if (key.equalsIgnoreCase("content-type")) {
                           contentType = value;
                        }

                        if (key.equals("content-transfer-encoding")) {
                           contentTransferEncoding = value;
                        }
                     }
                  }
               }

               if (!lastPart) {
                  throw new IOException("Incomplete parts");
               }

               while(line != null) {
                  line = ((ReadLineInputStream)this._in).readLine();
               }

               EnumSet<ReadLineInputStream.Termination> term = ((ReadLineInputStream)this._in).getLineTerminations();
               if (term.contains(ReadLineInputStream.Termination.CR)) {
                  this.nonComplianceWarnings.add(MultiParts.NonCompliance.CR_LINE_TERMINATION);
               }

               if (term.contains(ReadLineInputStream.Termination.LF)) {
                  this.nonComplianceWarnings.add(MultiParts.NonCompliance.LF_LINE_TERMINATION);
               }
            } catch (Exception e) {
               this._err = e;
            }

         }
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

   private String value(String nameEqualsValue) {
      int idx = nameEqualsValue.indexOf(61);
      String value = nameEqualsValue.substring(idx + 1).trim();
      return QuotedStringTokenizer.unquoteOnly(value);
   }

   private String filenameValue(String nameEqualsValue) {
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

      public MultiPart(String name, String filename) throws IOException {
         this._name = name;
         this._filename = filename;
      }

      public String toString() {
         return String.format("Part{n=%s,fn=%s,ct=%s,s=%d,t=%b,f=%s}", this._name, this._filename, this._contentType, this._size, this._temporary, this._file);
      }

      protected void setContentType(String contentType) {
         this._contentType = contentType;
      }

      protected void open() throws IOException {
         if (MultiPartInputStreamParser.this.isWriteFilesWithFilenames() && this._filename != null && this._filename.trim().length() > 0) {
            this.createFile();
         } else {
            this._out = this._bout = new ByteArrayOutputStream2();
         }

      }

      protected void close() throws IOException {
         this._out.close();
      }

      protected void write(int b) throws IOException {
         if (MultiPartInputStreamParser.this._config.getMaxFileSize() > 0L && this._size + 1L > MultiPartInputStreamParser.this._config.getMaxFileSize()) {
            throw new IllegalStateException("Multipart Mime part " + this._name + " exceeds max filesize");
         } else {
            if (MultiPartInputStreamParser.this._config.getFileSizeThreshold() > 0 && this._size + 1L > (long)MultiPartInputStreamParser.this._config.getFileSizeThreshold() && this._file == null) {
               this.createFile();
            }

            this._out.write(b);
            ++this._size;
         }
      }

      protected void write(byte[] bytes, int offset, int length) throws IOException {
         if (MultiPartInputStreamParser.this._config.getMaxFileSize() > 0L && this._size + (long)length > MultiPartInputStreamParser.this._config.getMaxFileSize()) {
            throw new IllegalStateException("Multipart Mime part " + this._name + " exceeds max filesize");
         } else {
            if (MultiPartInputStreamParser.this._config.getFileSizeThreshold() > 0 && this._size + (long)length > (long)MultiPartInputStreamParser.this._config.getFileSizeThreshold() && this._file == null) {
               this.createFile();
            }

            this._out.write(bytes, offset, length);
            this._size += (long)length;
         }
      }

      protected void createFile() throws IOException {
         Path parent = MultiPartInputStreamParser.this._tmpDir.toPath();
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
         return name == null ? null : (String)this._headers.getValue(name.toLowerCase(Locale.ENGLISH), 0);
      }

      public Collection getHeaderNames() {
         return this._headers.keySet();
      }

      public Collection getHeaders(String name) {
         return this._headers.getValues(name);
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

      public void write(String fileName) throws IOException {
         if (this._file == null) {
            this._temporary = false;
            this._file = new File(MultiPartInputStreamParser.this._tmpDir, fileName);
            BufferedOutputStream bos = null;

            try {
               bos = new BufferedOutputStream(new FileOutputStream(this._file));
               this._bout.writeTo(bos);
               bos.flush();
            } finally {
               if (bos != null) {
                  bos.close();
               }

               this._bout = null;
            }
         } else {
            this._temporary = false;
            Path src = this._file.toPath();
            Path target = src.resolveSibling(fileName);
            Files.move(src, target, StandardCopyOption.REPLACE_EXISTING);
            this._file = target.toFile();
         }

      }

      public void delete() throws IOException {
         if (this._file != null && this._file.exists()) {
            this._file.delete();
         }

      }

      public void cleanUp() throws IOException {
         if (this._temporary && this._file != null && this._file.exists()) {
            this._file.delete();
         }

      }

      public File getFile() {
         return this._file;
      }

      public String getContentDispositionFilename() {
         return this._filename;
      }
   }

   private static class Base64InputStream extends InputStream {
      ReadLineInputStream _in;
      String _line;
      byte[] _buffer;
      int _pos;
      Base64.Decoder base64Decoder = Base64.getDecoder();

      public Base64InputStream(ReadLineInputStream rlis) {
         this._in = rlis;
      }

      public int read() throws IOException {
         if (this._buffer == null || this._pos >= this._buffer.length) {
            this._line = this._in.readLine();
            if (this._line == null) {
               return -1;
            }

            if (this._line.startsWith("--")) {
               this._buffer = (this._line + "\r\n").getBytes();
            } else if (this._line.length() == 0) {
               this._buffer = "\r\n".getBytes();
            } else {
               ByteArrayOutputStream baos = new ByteArrayOutputStream(4 * this._line.length() / 3 + 2);
               baos.write(this.base64Decoder.decode(this._line));
               baos.write(13);
               baos.write(10);
               this._buffer = baos.toByteArray();
            }

            this._pos = 0;
         }

         return this._buffer[this._pos++] & 255;
      }
   }
}
