package org.iq80.leveldb;

public class Range {
   private final byte[] start;
   private final byte[] limit;

   public byte[] limit() {
      return this.limit;
   }

   public byte[] start() {
      return this.start;
   }

   public Range(byte[] start, byte[] limit) {
      Options.checkArgNotNull(start, "start");
      Options.checkArgNotNull(limit, "limit");
      this.limit = limit;
      this.start = start;
   }
}
