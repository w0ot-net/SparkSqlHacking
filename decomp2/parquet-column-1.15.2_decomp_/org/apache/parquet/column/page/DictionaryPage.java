package org.apache.parquet.column.page;

import java.io.IOException;
import java.util.Objects;
import org.apache.parquet.bytes.BytesInput;
import org.apache.parquet.column.Encoding;

public class DictionaryPage extends Page {
   private final BytesInput bytes;
   private final int dictionarySize;
   private final Encoding encoding;

   public DictionaryPage(BytesInput bytes, int dictionarySize, Encoding encoding) {
      this(bytes, (int)bytes.size(), dictionarySize, encoding);
   }

   public DictionaryPage(BytesInput bytes, int uncompressedSize, int dictionarySize, Encoding encoding) {
      super(Math.toIntExact(bytes.size()), uncompressedSize);
      this.bytes = (BytesInput)Objects.requireNonNull(bytes, "bytes cannot be null");
      this.dictionarySize = dictionarySize;
      this.encoding = (Encoding)Objects.requireNonNull(encoding, "encoding cannot be null");
   }

   public BytesInput getBytes() {
      return this.bytes;
   }

   public int getDictionarySize() {
      return this.dictionarySize;
   }

   public Encoding getEncoding() {
      return this.encoding;
   }

   public DictionaryPage copy() throws IOException {
      return new DictionaryPage(BytesInput.copy(this.bytes), this.getUncompressedSize(), this.dictionarySize, this.encoding);
   }

   public String toString() {
      return "Page [bytes.size=" + this.bytes.size() + ", entryCount=" + this.dictionarySize + ", uncompressedSize=" + this.getUncompressedSize() + ", encoding=" + this.encoding + "]";
   }
}
