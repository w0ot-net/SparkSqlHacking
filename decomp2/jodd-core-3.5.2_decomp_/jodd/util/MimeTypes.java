package jodd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import jodd.io.StreamUtil;

public class MimeTypes {
   public static final String MIME_APPLICATION_ATOM_XML = "application/atom+xml";
   public static final String MIME_APPLICATION_JAVASCRIPT = "application/javascript";
   public static final String MIME_APPLICATION_JSON = "application/json";
   public static final String MIME_APPLICATION_OCTET_STREAM = "application/octet-stream";
   public static final String MIME_APPLICATION_XML = "application/xml";
   public static final String MIME_TEXT_CSS = "text/css";
   public static final String MIME_TEXT_PLAIN = "text/plain";
   public static final String MIME_TEXT_HTML = "text/html";
   private static final HashMap MIME_TYPE_MAP;

   public static void registerMimeType(String ext, String mimeType) {
      MIME_TYPE_MAP.put(ext, mimeType);
   }

   public static String getMimeType(String ext) {
      String mimeType = lookupMimeType(ext);
      if (mimeType == null) {
         mimeType = "application/octet-stream";
      }

      return mimeType;
   }

   public static String lookupMimeType(String ext) {
      return (String)MIME_TYPE_MAP.get(ext.toLowerCase());
   }

   public static String[] findExtensionsByMimeTypes(String mimeType, boolean useWildcard) {
      ArrayList<String> extensions = new ArrayList();
      mimeType = mimeType.toLowerCase();
      String[] mimeTypes = StringUtil.splitc(mimeType, ", ");

      for(Map.Entry entry : MIME_TYPE_MAP.entrySet()) {
         String entryExtension = (String)entry.getKey();
         String entryMimeType = ((String)entry.getValue()).toLowerCase();
         int matchResult = useWildcard ? Wildcard.matchOne(entryMimeType, mimeTypes) : StringUtil.equalsOne(entryMimeType, mimeTypes);
         if (matchResult != -1) {
            extensions.add(entryExtension);
         }
      }

      if (extensions.isEmpty()) {
         return StringPool.EMPTY_ARRAY;
      } else {
         return (String[])extensions.toArray(new String[extensions.size()]);
      }
   }

   static {
      Properties mimes = new Properties();
      InputStream is = MimeTypes.class.getResourceAsStream(MimeTypes.class.getSimpleName() + ".properties");
      if (is == null) {
         throw new IllegalStateException("Mime types file missing");
      } else {
         try {
            mimes.load(is);
         } catch (IOException ioex) {
            throw new IllegalStateException(ioex.getMessage());
         } finally {
            StreamUtil.close(is);
         }

         MIME_TYPE_MAP = new HashMap(mimes.size() * 2);
         Enumeration ioex = mimes.propertyNames();

         while(ioex.hasMoreElements()) {
            String mimeType = (String)ioex.nextElement();
            String extensions = mimes.getProperty(mimeType);
            if (mimeType.startsWith("/")) {
               mimeType = "application" + mimeType;
            } else if (mimeType.startsWith("a/")) {
               mimeType = "audio" + mimeType.substring(1);
            } else if (mimeType.startsWith("i/")) {
               mimeType = "image" + mimeType.substring(1);
            } else if (mimeType.startsWith("t/")) {
               mimeType = "text" + mimeType.substring(1);
            } else if (mimeType.startsWith("v/")) {
               mimeType = "video" + mimeType.substring(1);
            }

            String[] allExtensions = StringUtil.splitc(extensions, ' ');

            for(String extension : allExtensions) {
               if (MIME_TYPE_MAP.put(extension, mimeType) != null) {
                  throw new IllegalArgumentException("Duplicated extension: " + extension);
               }
            }
         }

      }
   }
}
