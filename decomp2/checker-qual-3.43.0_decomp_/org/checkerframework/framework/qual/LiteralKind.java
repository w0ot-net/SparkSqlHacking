package org.checkerframework.framework.qual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum LiteralKind {
   NULL,
   INT,
   LONG,
   FLOAT,
   DOUBLE,
   BOOLEAN,
   CHAR,
   STRING,
   ALL,
   PRIMITIVE;

   public static List allLiteralKinds() {
      List<LiteralKind> list = new ArrayList(Arrays.asList(values()));
      list.remove(ALL);
      list.remove(PRIMITIVE);
      return list;
   }

   public static List primitiveLiteralKinds() {
      return Arrays.asList(INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR);
   }

   // $FF: synthetic method
   private static LiteralKind[] $values() {
      return new LiteralKind[]{NULL, INT, LONG, FLOAT, DOUBLE, BOOLEAN, CHAR, STRING, ALL, PRIMITIVE};
   }
}
