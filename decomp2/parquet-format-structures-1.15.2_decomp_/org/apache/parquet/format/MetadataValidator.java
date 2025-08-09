package org.apache.parquet.format;

public class MetadataValidator {
   static PageHeader validate(PageHeader pageHeader) {
      int compressed_page_size = pageHeader.getCompressed_page_size();
      validateValue(compressed_page_size >= 0, String.format("Compressed page size must not be negative but was: %s", compressed_page_size));
      return pageHeader;
   }

   private static void validateValue(boolean valid, String message) {
      if (!valid) {
         throw new InvalidParquetMetadataException(message);
      }
   }

   private MetadataValidator() {
   }
}
