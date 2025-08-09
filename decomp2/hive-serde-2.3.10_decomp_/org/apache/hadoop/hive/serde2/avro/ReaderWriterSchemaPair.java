package org.apache.hadoop.hive.serde2.avro;

import org.apache.avro.Schema;

class ReaderWriterSchemaPair {
   final Schema reader;
   final Schema writer;

   public ReaderWriterSchemaPair(Schema writer, Schema reader) {
      this.reader = reader;
      this.writer = writer;
   }

   public Schema getReader() {
      return this.reader;
   }

   public Schema getWriter() {
      return this.writer;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         ReaderWriterSchemaPair that = (ReaderWriterSchemaPair)o;
         if (!this.reader.equals(that.reader)) {
            return false;
         } else {
            return this.writer.equals(that.writer);
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int result = this.reader.hashCode();
      result = 31 * result + this.writer.hashCode();
      return result;
   }
}
