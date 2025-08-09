package com.univocity.parsers.fixed;

import java.util.LinkedHashMap;

/** @deprecated */
@Deprecated
public class FixedWidthFieldLengths extends FixedWidthFields {
   public FixedWidthFieldLengths(LinkedHashMap fields) {
      super(fields);
   }

   public FixedWidthFieldLengths(String[] headers, int[] lengths) {
      super(headers, lengths);
   }

   public FixedWidthFieldLengths(int... fieldLengths) {
      super(fieldLengths);
   }
}
