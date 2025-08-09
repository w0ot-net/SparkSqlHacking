package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.types.IntegerType$;
import org.apache.spark.sql.types.LongType$;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class WindowFrame$ implements Serializable {
   public static final WindowFrame$ MODULE$ = new WindowFrame$();

   public WindowFrame.Value value(final int i) {
      return new WindowFrame.Value(new Literal(BoxesRunTime.boxToInteger(i), new Some(IntegerType$.MODULE$), Literal$.MODULE$.apply$default$3()));
   }

   public WindowFrame.Value value(final long l) {
      return new WindowFrame.Value(new Literal(BoxesRunTime.boxToLong(l), new Some(LongType$.MODULE$), Literal$.MODULE$.apply$default$3()));
   }

   public WindowFrame apply(final WindowFrame.FrameType frameType, final WindowFrame.FrameBoundary lower, final WindowFrame.FrameBoundary upper) {
      return new WindowFrame(frameType, lower, upper);
   }

   public Option unapply(final WindowFrame x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.frameType(), x$0.lower(), x$0.upper())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(WindowFrame$.class);
   }

   private WindowFrame$() {
   }
}
