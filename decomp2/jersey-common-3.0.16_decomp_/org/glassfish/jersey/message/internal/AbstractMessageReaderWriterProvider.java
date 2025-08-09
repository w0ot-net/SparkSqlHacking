package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public abstract class AbstractMessageReaderWriterProvider implements MessageBodyReader, MessageBodyWriter {
   /** @deprecated */
   @Deprecated
   public static final Charset UTF8;

   /** @deprecated */
   @Deprecated
   public static void writeTo(InputStream in, OutputStream out) throws IOException {
      ReaderWriter.writeTo(in, out);
   }

   /** @deprecated */
   @Deprecated
   public static void writeTo(Reader in, Writer out) throws IOException {
      ReaderWriter.writeTo(in, out);
   }

   /** @deprecated */
   @Deprecated
   public static Charset getCharset(MediaType m) {
      return ReaderWriter.getCharset(m);
   }

   /** @deprecated */
   @Deprecated
   public static String readFromAsString(InputStream in, MediaType type) throws IOException {
      return ReaderWriter.readFromAsString(in, type);
   }

   /** @deprecated */
   @Deprecated
   public static void writeToAsString(String s, OutputStream out, MediaType type) throws IOException {
      ReaderWriter.writeToAsString(s, out, type);
   }

   public long getSize(Object t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
      return -1L;
   }

   static {
      UTF8 = StandardCharsets.UTF_8;
   }
}
