package jodd.typeconverter.impl;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

public class URIConverter implements TypeConverter {
   public URI convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof URI) {
         return (URI)value;
      } else if (value instanceof File) {
         File file = (File)value;
         return file.toURI();
      } else if (value instanceof URL) {
         URL url = (URL)value;

         try {
            return url.toURI();
         } catch (URISyntaxException usex) {
            throw new TypeConversionException(value, usex);
         }
      } else {
         try {
            return new URI(value.toString());
         } catch (URISyntaxException usex) {
            throw new TypeConversionException(value, usex);
         }
      }
   }
}
