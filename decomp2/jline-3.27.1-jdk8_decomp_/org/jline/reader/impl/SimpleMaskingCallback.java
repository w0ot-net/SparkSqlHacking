package org.jline.reader.impl;

import java.util.Objects;
import org.jline.reader.MaskingCallback;

public final class SimpleMaskingCallback implements MaskingCallback {
   private final Character mask;

   public SimpleMaskingCallback(Character mask) {
      this.mask = (Character)Objects.requireNonNull(mask, "mask must be a non null character");
   }

   public String display(String line) {
      if (this.mask.equals('\u0000')) {
         return "";
      } else {
         StringBuilder sb = new StringBuilder(line.length());
         int i = line.length();

         while(i-- > 0) {
            sb.append(this.mask);
         }

         return sb.toString();
      }
   }

   public String history(String line) {
      return null;
   }
}
