package org.apache.spark.graphx;

import scala.Some;
import scala.Tuple5;
import scala.runtime.BoxesRunTime;

public final class EdgeContext$ {
   public static final EdgeContext$ MODULE$ = new EdgeContext$();

   public Some unapply(final EdgeContext edge) {
      return new Some(new Tuple5(BoxesRunTime.boxToLong(edge.srcId()), BoxesRunTime.boxToLong(edge.dstId()), edge.srcAttr(), edge.dstAttr(), edge.attr()));
   }

   private EdgeContext$() {
   }
}
