package org.apache.parquet.format;

public class InvalidParquetMetadataException extends RuntimeException {
   InvalidParquetMetadataException(String message) {
      super(message);
   }
}
