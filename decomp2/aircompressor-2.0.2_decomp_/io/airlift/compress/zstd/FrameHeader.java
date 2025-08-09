package io.airlift.compress.zstd;

import java.util.Objects;
import java.util.StringJoiner;

class FrameHeader {
   final long headerSize;
   final int windowSize;
   final long contentSize;
   final long dictionaryId;
   final boolean hasChecksum;

   public FrameHeader(long headerSize, int windowSize, long contentSize, long dictionaryId, boolean hasChecksum) {
      Util.checkState(windowSize >= 0 || contentSize >= 0L, "Invalid frame header: contentSize or windowSize must be set");
      this.headerSize = headerSize;
      this.windowSize = windowSize;
      this.contentSize = contentSize;
      this.dictionaryId = dictionaryId;
      this.hasChecksum = hasChecksum;
   }

   public int computeRequiredOutputBufferLookBackSize() {
      if (this.contentSize < 0L) {
         return this.windowSize;
      } else {
         return this.windowSize < 0 ? Math.toIntExact(this.contentSize) : Math.toIntExact(Math.min((long)this.windowSize, this.contentSize));
      }
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         FrameHeader that = (FrameHeader)o;
         return this.headerSize == that.headerSize && this.windowSize == that.windowSize && this.contentSize == that.contentSize && this.dictionaryId == that.dictionaryId && this.hasChecksum == that.hasChecksum;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.headerSize, this.windowSize, this.contentSize, this.dictionaryId, this.hasChecksum});
   }

   public String toString() {
      return (new StringJoiner(", ", FrameHeader.class.getSimpleName() + "[", "]")).add("headerSize=" + this.headerSize).add("windowSize=" + this.windowSize).add("contentSize=" + this.contentSize).add("dictionaryId=" + this.dictionaryId).add("hasChecksum=" + this.hasChecksum).toString();
   }
}
