package jodd.typeconverter.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import jodd.io.FileUtil;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;

public class URLConverter implements TypeConverter {
   public URL convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof URL) {
         return (URL)value;
      } else if (value instanceof File) {
         File file = (File)value;

         try {
            return FileUtil.toURL(file);
         } catch (MalformedURLException muex) {
            throw new TypeConversionException(value, muex);
         }
      } else if (value instanceof URI) {
         URI uri = (URI)value;

         try {
            return uri.toURL();
         } catch (MalformedURLException muex) {
            throw new TypeConversionException(value, muex);
         }
      } else {
         try {
            return new URL(value.toString());
         } catch (MalformedURLException muex) {
            throw new TypeConversionException(value, muex);
         }
      }
   }
}
