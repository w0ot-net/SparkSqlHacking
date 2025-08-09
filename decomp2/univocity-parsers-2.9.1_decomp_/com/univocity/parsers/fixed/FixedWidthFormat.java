package com.univocity.parsers.fixed;

import com.univocity.parsers.common.Format;
import java.util.TreeMap;

public class FixedWidthFormat extends Format {
   private char padding = ' ';
   private char lookupWildcard = '?';

   public char getPadding() {
      return this.padding;
   }

   public void setPadding(char padding) {
      this.padding = padding;
   }

   public boolean isPadding(char padding) {
      return this.padding == padding;
   }

   protected TreeMap getConfiguration() {
      TreeMap<String, Object> out = new TreeMap();
      out.put("Padding", this.padding);
      return out;
   }

   public final FixedWidthFormat clone() {
      return (FixedWidthFormat)super.clone();
   }

   public char getLookupWildcard() {
      return this.lookupWildcard;
   }

   public void setLookupWildcard(char lookupWildcard) {
      this.lookupWildcard = lookupWildcard;
   }
}
