package org.apache.parquet.hadoop.rewrite;

import org.apache.parquet.Preconditions;

public enum MaskMode {
   NULLIFY("nullify"),
   HASH("hash"),
   REDACT("redact");

   private String mode;

   private MaskMode(String text) {
      Preconditions.checkArgument(text != null, "Text of mask mode is required");
      this.mode = text;
   }

   public String getMode() {
      return this.mode;
   }
}
