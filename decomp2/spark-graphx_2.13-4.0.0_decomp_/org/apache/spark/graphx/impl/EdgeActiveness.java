package org.apache.spark.graphx.impl;

public enum EdgeActiveness {
   Neither,
   SrcOnly,
   DstOnly,
   Both,
   Either;

   // $FF: synthetic method
   private static EdgeActiveness[] $values() {
      return new EdgeActiveness[]{Neither, SrcOnly, DstOnly, Both, Either};
   }
}
