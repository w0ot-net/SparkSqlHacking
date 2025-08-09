package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.build.AbstractStreamBuilder;
import org.apache.commons.io.charset.CharsetDecoders;

public class WriterOutputStream extends OutputStream {
   private static final int BUFFER_SIZE = 8192;
   private final Writer writer;
   private final CharsetDecoder decoder;
   private final boolean writeImmediately;
   private final ByteBuffer decoderIn;
   private final CharBuffer decoderOut;

   public static Builder builder() {
      return new Builder();
   }

   private static void checkIbmJdkWithBrokenUTF16(Charset charset) {
      if (StandardCharsets.UTF_16.name().equals(charset.name())) {
         String TEST_STRING_2 = "vés";
         byte[] bytes = "vés".getBytes(charset);
         CharsetDecoder charsetDecoder2 = charset.newDecoder();
         ByteBuffer bb2 = ByteBuffer.allocate(16);
         CharBuffer cb2 = CharBuffer.allocate("vés".length());
         int len = bytes.length;

         for(int i = 0; i < len; ++i) {
            bb2.put(bytes[i]);
            bb2.flip();

            try {
               charsetDecoder2.decode(bb2, cb2, i == len - 1);
            } catch (IllegalArgumentException var9) {
               throw new UnsupportedOperationException("UTF-16 requested when running on an IBM JDK with broken UTF-16 support. Please find a JDK that supports UTF-16 if you intend to use UF-16 with WriterOutputStream");
            }

            bb2.compact();
         }

         cb2.rewind();
         if (!"vés".equals(cb2.toString())) {
            throw new UnsupportedOperationException("UTF-16 requested when running on an IBM JDK with broken UTF-16 support. Please find a JDK that supports UTF-16 if you intend to use UF-16 with WriterOutputStream");
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer) {
      this(writer, (Charset)Charset.defaultCharset(), 8192, false);
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer, Charset charset) {
      this(writer, (Charset)charset, 8192, false);
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer, Charset charset, int bufferSize, boolean writeImmediately) {
      this(writer, Charsets.toCharset(charset).newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith("?"), bufferSize, writeImmediately);
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer, CharsetDecoder decoder) {
      this(writer, (CharsetDecoder)decoder, 8192, false);
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer, CharsetDecoder decoder, int bufferSize, boolean writeImmediately) {
      this.decoderIn = ByteBuffer.allocate(128);
      checkIbmJdkWithBrokenUTF16(CharsetDecoders.toCharsetDecoder(decoder).charset());
      this.writer = writer;
      this.decoder = CharsetDecoders.toCharsetDecoder(decoder);
      this.writeImmediately = writeImmediately;
      this.decoderOut = CharBuffer.allocate(bufferSize);
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer, String charsetName) {
      this(writer, (String)charsetName, 8192, false);
   }

   /** @deprecated */
   @Deprecated
   public WriterOutputStream(Writer writer, String charsetName, int bufferSize, boolean writeImmediately) {
      this(writer, Charsets.toCharset(charsetName), bufferSize, writeImmediately);
   }

   public void close() throws IOException {
      this.processInput(true);
      this.flushOutput();
      this.writer.close();
   }

   public void flush() throws IOException {
      this.flushOutput();
      this.writer.flush();
   }

   private void flushOutput() throws IOException {
      if (this.decoderOut.position() > 0) {
         this.writer.write(this.decoderOut.array(), 0, this.decoderOut.position());
         this.decoderOut.rewind();
      }

   }

   private void processInput(boolean endOfInput) throws IOException {
      this.decoderIn.flip();

      while(true) {
         CoderResult coderResult = this.decoder.decode(this.decoderIn, this.decoderOut, endOfInput);
         if (!coderResult.isOverflow()) {
            if (coderResult.isUnderflow()) {
               this.decoderIn.compact();
               return;
            } else {
               throw new IOException("Unexpected coder result");
            }
         }

         this.flushOutput();
      }
   }

   public void write(byte[] b) throws IOException {
      this.write(b, 0, b.length);
   }

   public void write(byte[] b, int off, int len) throws IOException {
      while(len > 0) {
         int c = Math.min(len, this.decoderIn.remaining());
         this.decoderIn.put(b, off, c);
         this.processInput(false);
         len -= c;
         off += c;
      }

      if (this.writeImmediately) {
         this.flushOutput();
      }

   }

   public void write(int b) throws IOException {
      this.write(new byte[]{(byte)b}, 0, 1);
   }

   public static class Builder extends AbstractStreamBuilder {
      private CharsetDecoder charsetDecoder = this.getCharset().newDecoder();
      private boolean writeImmediately;

      public WriterOutputStream get() throws IOException {
         return new WriterOutputStream(this.getWriter(), this.charsetDecoder, this.getBufferSize(), this.writeImmediately);
      }

      public Builder setCharset(Charset charset) {
         super.setCharset(charset);
         this.charsetDecoder = this.getCharset().newDecoder();
         return this;
      }

      public Builder setCharset(String charset) {
         super.setCharset(charset);
         this.charsetDecoder = this.getCharset().newDecoder();
         return this;
      }

      public Builder setCharsetDecoder(CharsetDecoder charsetDecoder) {
         this.charsetDecoder = charsetDecoder != null ? charsetDecoder : this.getCharsetDefault().newDecoder();
         super.setCharset(this.charsetDecoder.charset());
         return this;
      }

      public Builder setWriteImmediately(boolean writeImmediately) {
         this.writeImmediately = writeImmediately;
         return this;
      }
   }
}
