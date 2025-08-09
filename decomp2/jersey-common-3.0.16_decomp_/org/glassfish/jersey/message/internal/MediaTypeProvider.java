package org.glassfish.jersey.message.internal;

import jakarta.inject.Singleton;
import jakarta.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Map;
import org.glassfish.jersey.internal.LocalizationMessages;
import org.glassfish.jersey.spi.HeaderDelegateProvider;

@Singleton
public class MediaTypeProvider implements HeaderDelegateProvider {
   private static final String MEDIA_TYPE_IS_NULL = LocalizationMessages.MEDIA_TYPE_IS_NULL();

   public boolean supports(Class type) {
      return MediaType.class.isAssignableFrom(type);
   }

   public String toString(MediaType header) {
      Utils.throwIllegalArgumentExceptionIfNull(header, MEDIA_TYPE_IS_NULL);
      StringBuilder b = new StringBuilder();
      b.append(header.getType()).append('/').append(header.getSubtype());

      for(Map.Entry e : header.getParameters().entrySet()) {
         b.append(";").append((String)e.getKey()).append('=');
         StringBuilderUtils.appendQuotedIfNonToken(b, (String)e.getValue());
      }

      return b.toString();
   }

   public MediaType fromString(String header) {
      Utils.throwIllegalArgumentExceptionIfNull(header, MEDIA_TYPE_IS_NULL);

      try {
         return valueOf(HttpHeaderReader.newInstance(header));
      } catch (ParseException ex) {
         throw new IllegalArgumentException("Error parsing media type '" + header + "'", ex);
      }
   }

   public static MediaType valueOf(HttpHeaderReader reader) throws ParseException {
      reader.hasNext();
      String type = reader.nextToken().toString();
      reader.nextSeparator('/');
      String subType = reader.nextToken().toString();
      Map<String, String> params = null;
      if (reader.hasNext()) {
         params = HttpHeaderReader.readParameters(reader);
      }

      return new MediaType(type, subType, params);
   }
}
