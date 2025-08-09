package scala.util.parsing.input;

import java.io.Serializable;
import java.util.Map;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class OffsetPosition$ extends AbstractFunction2 implements PositionCache, Serializable {
   public static final OffsetPosition$ MODULE$ = new OffsetPosition$();
   private static ThreadLocal scala$util$parsing$input$PositionCache$$indexCacheTL;
   private static volatile boolean bitmap$0;

   static {
      PositionCache.$init$(MODULE$);
   }

   public Map indexCache() {
      return PositionCache.indexCache$(this);
   }

   private ThreadLocal scala$util$parsing$input$PositionCache$$indexCacheTL$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            scala$util$parsing$input$PositionCache$$indexCacheTL = PositionCache.scala$util$parsing$input$PositionCache$$indexCacheTL$(this);
            bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return scala$util$parsing$input$PositionCache$$indexCacheTL;
   }

   public ThreadLocal scala$util$parsing$input$PositionCache$$indexCacheTL() {
      return !bitmap$0 ? this.scala$util$parsing$input$PositionCache$$indexCacheTL$lzycompute() : scala$util$parsing$input$PositionCache$$indexCacheTL;
   }

   public OffsetPosition apply(final CharSequence source, final int offset) {
      return new OffsetPosition(source, offset);
   }

   public Option unapply(final OffsetPosition x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.source(), BoxesRunTime.boxToInteger(x$0.offset()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(OffsetPosition$.class);
   }

   private OffsetPosition$() {
   }
}
