package org.glassfish.jersey.message.internal;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public abstract class AbstractFormProvider extends AbstractMessageReaderWriterProvider {
   public MultivaluedMap readFrom(MultivaluedMap map, MediaType mediaType, boolean decode, InputStream entityStream) throws IOException {
      String encoded = ReaderWriter.readFromAsString(entityStream, mediaType);
      String charsetName = ReaderWriter.getCharset(mediaType).name();
      StringTokenizer tokenizer = new StringTokenizer(encoded, "&");

      try {
         while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            int idx = token.indexOf(61);
            if (idx < 0) {
               map.add(decode ? URLDecoder.decode(token, charsetName) : token, (Object)null);
            } else if (idx > 0) {
               if (decode) {
                  map.add(URLDecoder.decode(token.substring(0, idx), charsetName), URLDecoder.decode(token.substring(idx + 1), charsetName));
               } else {
                  map.add(token.substring(0, idx), token.substring(idx + 1));
               }
            }
         }

         return map;
      } catch (IllegalArgumentException ex) {
         throw new BadRequestException(ex);
      }
   }

   public void writeTo(MultivaluedMap t, MediaType mediaType, OutputStream entityStream) throws IOException {
      String charsetName = ReaderWriter.getCharset(mediaType).name();
      StringBuilder sb = new StringBuilder();

      for(Map.Entry e : t.entrySet()) {
         for(String value : (List)e.getValue()) {
            if (sb.length() > 0) {
               sb.append('&');
            }

            sb.append(URLEncoder.encode((String)e.getKey(), charsetName));
            if (value != null) {
               sb.append('=');
               sb.append(URLEncoder.encode(value, charsetName));
            }
         }
      }

      ReaderWriter.writeToAsString(sb.toString(), entityStream, mediaType);
   }
}
