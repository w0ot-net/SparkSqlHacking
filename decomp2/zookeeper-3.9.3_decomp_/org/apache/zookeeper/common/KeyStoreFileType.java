package org.apache.zookeeper.common;

public enum KeyStoreFileType {
   JKS(".jks"),
   PEM(".pem"),
   PKCS12(".p12"),
   BCFKS(".bcfks");

   private final String defaultFileExtension;

   private KeyStoreFileType(String defaultFileExtension) {
      this.defaultFileExtension = defaultFileExtension;
   }

   public String getPropertyValue() {
      return this.name();
   }

   public String getDefaultFileExtension() {
      return this.defaultFileExtension;
   }

   public static KeyStoreFileType fromPropertyValue(String propertyValue) {
      return propertyValue != null && propertyValue.length() != 0 ? valueOf(propertyValue.toUpperCase()) : null;
   }

   public static KeyStoreFileType fromFilename(String filename) {
      int i = filename.lastIndexOf(46);
      if (i >= 0) {
         String extension = filename.substring(i);

         for(KeyStoreFileType storeFileType : values()) {
            if (storeFileType.getDefaultFileExtension().equals(extension)) {
               return storeFileType;
            }
         }
      }

      throw new IllegalArgumentException("Unable to auto-detect store file type from file name: " + filename);
   }

   public static KeyStoreFileType fromPropertyValueOrFileName(String propertyValue, String filename) {
      KeyStoreFileType result = fromPropertyValue(propertyValue);
      if (result == null) {
         result = fromFilename(filename);
      }

      return result;
   }
}
