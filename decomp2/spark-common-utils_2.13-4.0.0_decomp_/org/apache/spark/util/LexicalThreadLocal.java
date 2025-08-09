package org.apache.spark.util;

import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005]3qa\u0003\u0007\u0011\u0002\u0007\u0005Q\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007I\u0011B\u0012\t\u000b]\u0002A\u0011\u0002\u001d\t\u000by\u0002A\u0011C \t\u000bU\u0003A\u0011\u0001,\u0007\t\t\u0003!a\u0011\u0005\tu\u0019\u0011)\u0019!C\u0005\t\"AQI\u0002B\u0001B\u0003%1\b\u0003\u0004G\r\u0011\u0005\u0001a\u0012\u0005\u0006\u0013\u001a!\tA\u0013\u0002\u0013\u0019\u0016D\u0018nY1m)\"\u0014X-\u00193M_\u000e\fGN\u0003\u0002\u000e\u001d\u0005!Q\u000f^5m\u0015\ty\u0001#A\u0003ta\u0006\u00148N\u0003\u0002\u0012%\u00051\u0011\r]1dQ\u0016T\u0011aE\u0001\u0004_J<7\u0001A\u000b\u0003-9\u001a\"\u0001A\f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tq\u0004\u0005\u0002\u0019A%\u0011\u0011%\u0007\u0002\u0005+:LG/\u0001\u0002uYV\tA\u0005E\u0002&U1j\u0011A\n\u0006\u0003O!\nA\u0001\\1oO*\t\u0011&\u0001\u0003kCZ\f\u0017BA\u0016'\u0005-!\u0006N]3bI2{7-\u00197\u0011\u00055rC\u0002\u0001\u0003\u0006_\u0001\u0011\r\u0001\r\u0002\u0002)F\u0011\u0011\u0007\u000e\t\u00031IJ!aM\r\u0003\u000f9{G\u000f[5oOB\u0011\u0001$N\u0005\u0003me\u00111!\u00118z\u0003\r\u0019X\r\u001e\u000b\u0003?eBQAO\u0002A\u0002m\n1a\u001c9u!\rAB\bL\u0005\u0003{e\u0011aa\u00149uS>t\u0017\u0001D2sK\u0006$X\rS1oI2,GC\u0001!U!\t\te!D\u0001\u0001\u0005\u0019A\u0015M\u001c3mKN\u0011aaF\u000b\u0002w\u0005!q\u000e\u001d;!\u0003\u0019a\u0014N\\5u}Q\u0011\u0001\t\u0013\u0005\u0006u%\u0001\raO\u0001\beVtw+\u001b;i+\tYU\n\u0006\u0002M\u001fB\u0011Q&\u0014\u0003\u0006\u001d*\u0011\r\u0001\r\u0002\u0002%\"1\u0001K\u0003CA\u0002E\u000b\u0011A\u001a\t\u00041Ic\u0015BA*\u001a\u0005!a$-\u001f8b[\u0016t\u0004\"\u0002\u001e\u0005\u0001\u0004Y\u0014aA4fiR\t1\b"
)
public interface LexicalThreadLocal {
   void org$apache$spark$util$LexicalThreadLocal$_setter_$org$apache$spark$util$LexicalThreadLocal$$tl_$eq(final ThreadLocal x$1);

   ThreadLocal org$apache$spark$util$LexicalThreadLocal$$tl();

   // $FF: synthetic method
   static void org$apache$spark$util$LexicalThreadLocal$$set$(final LexicalThreadLocal $this, final Option opt) {
      $this.org$apache$spark$util$LexicalThreadLocal$$set(opt);
   }

   default void org$apache$spark$util$LexicalThreadLocal$$set(final Option opt) {
      if (opt instanceof Some var4) {
         Object x = var4.value();
         this.org$apache$spark$util$LexicalThreadLocal$$tl().set(x);
         BoxedUnit var6 = BoxedUnit.UNIT;
      } else if (.MODULE$.equals(opt)) {
         this.org$apache$spark$util$LexicalThreadLocal$$tl().remove();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(opt);
      }
   }

   // $FF: synthetic method
   static Handle createHandle$(final LexicalThreadLocal $this, final Option opt) {
      return $this.createHandle(opt);
   }

   default Handle createHandle(final Option opt) {
      return new Handle(opt);
   }

   // $FF: synthetic method
   static Option get$(final LexicalThreadLocal $this) {
      return $this.get();
   }

   default Option get() {
      return scala.Option..MODULE$.apply(this.org$apache$spark$util$LexicalThreadLocal$$tl().get());
   }

   static void $init$(final LexicalThreadLocal $this) {
      $this.org$apache$spark$util$LexicalThreadLocal$_setter_$org$apache$spark$util$LexicalThreadLocal$$tl_$eq(new ThreadLocal());
   }

   public final class Handle {
      private final Option opt;
      // $FF: synthetic field
      private final LexicalThreadLocal $outer;

      private Option opt() {
         return this.opt;
      }

      public Object runWith(final Function0 f) {
         Option old = this.$outer.get();
         this.$outer.org$apache$spark$util$LexicalThreadLocal$$set(this.opt());

         Object var10000;
         try {
            var10000 = f.apply();
         } finally {
            this.$outer.org$apache$spark$util$LexicalThreadLocal$$set(old);
         }

         return var10000;
      }

      public Handle(final Option opt) {
         this.opt = opt;
         if (LexicalThreadLocal.this == null) {
            throw null;
         } else {
            this.$outer = LexicalThreadLocal.this;
            super();
         }
      }
   }
}
