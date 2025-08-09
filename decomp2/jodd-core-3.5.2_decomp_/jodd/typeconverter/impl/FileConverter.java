package jodd.typeconverter.impl;

import java.io.File;
import java.io.IOException;
import jodd.io.FileUtil;
import jodd.typeconverter.TypeConversionException;
import jodd.typeconverter.TypeConverter;
import jodd.util.ArraysUtil;

public class FileConverter implements TypeConverter {
   protected TypeConverter[] addonFileConverters;

   public void registerAddonConverter(TypeConverter fileTypeConverter) {
      if (this.addonFileConverters == null) {
         this.addonFileConverters = new TypeConverter[0];
      }

      ArraysUtil.append((Object[])this.addonFileConverters, (Object)fileTypeConverter);
   }

   public File convert(Object value) {
      if (value == null) {
         return null;
      } else if (value instanceof File) {
         return (File)value;
      } else {
         if (this.addonFileConverters != null) {
            for(TypeConverter addonFileConverter : this.addonFileConverters) {
               File file = (File)addonFileConverter.convert(value);
               if (file != null) {
                  return file;
               }
            }
         }

         Class type = value.getClass();
         if (type == byte[].class) {
            try {
               File tempFile = FileUtil.createTempFile();
               FileUtil.writeBytes(tempFile, (byte[])value);
               return tempFile;
            } catch (IOException ioex) {
               throw new TypeConversionException(ioex);
            }
         } else if (type == String.class) {
            try {
               File tempFile = FileUtil.createTempFile();
               FileUtil.writeString(tempFile, value.toString());
               return tempFile;
            } catch (IOException ioex) {
               throw new TypeConversionException(ioex);
            }
         } else {
            throw new TypeConversionException(value);
         }
      }
   }
}
