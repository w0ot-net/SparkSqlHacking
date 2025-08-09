package org.json4s.scalap;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%<Q\u0001D\u0007\t\u0002Q1QAF\u0007\t\u0002]AQAH\u0001\u0005\u0002}Aq\u0001I\u0001A\u0002\u0013\u0005\u0011\u0005C\u0004&\u0003\u0001\u0007I\u0011\u0001\u0014\t\r1\n\u0001\u0015)\u0003#\r\u001d1R\u0002%A\u0002\u00025BQ!\r\u0004\u0005\u0002IBqa\r\u0004C\u0002\u0013EA\u0007C\u0003A\r\u0011\u0005\u0011\tC\u0003S\r\u0011E1\u000bC\u0003[\r\u0011E1,A\tEK\u001a\fW\u000f\u001c;NK6|\u0017n]1cY\u0016T!AD\b\u0002\rM\u001c\u0017\r\\1q\u0015\t\u0001\u0012#\u0001\u0004kg>tGg\u001d\u0006\u0002%\u0005\u0019qN]4\u0004\u0001A\u0011Q#A\u0007\u0002\u001b\t\tB)\u001a4bk2$X*Z7pSN\f'\r\\3\u0014\u0005\u0005A\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002)\u0005)A-\u001a2vOV\t!\u0005\u0005\u0002\u001aG%\u0011AE\u0007\u0002\b\u0005>|G.Z1o\u0003%!WMY;h?\u0012*\u0017\u000f\u0006\u0002(UA\u0011\u0011\u0004K\u0005\u0003Si\u0011A!\u00168ji\"91\u0006BA\u0001\u0002\u0004\u0011\u0013a\u0001=%c\u00051A-\u001a2vO\u0002\u001a2A\u0002\r/!\t)r&\u0003\u00021\u001b\tQQ*Z7pSN\f'\r\\3\u0002\r\u0011Jg.\u001b;%)\u00059\u0013aA7baV\tQ\u0007\u0005\u00037waiT\"A\u001c\u000b\u0005aJ\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0003ui\t!bY8mY\u0016\u001cG/[8o\u0013\tatGA\u0004ICNDW*\u00199\u0011\u0005eq\u0014BA \u001b\u0005\r\te._\u0001\u0005[\u0016lw.\u0006\u0002C\u000bR\u00191iS'\u0011\u0005\u0011+E\u0002\u0001\u0003\u0006\r&\u0011\ra\u0012\u0002\u0002\u0003F\u0011\u0001*\u0010\t\u00033%K!A\u0013\u000e\u0003\u000f9{G\u000f[5oO\")A*\u0003a\u00011\u0005\u00191.Z=\t\r9KA\u00111\u0001P\u0003\u0005\t\u0007cA\rQ\u0007&\u0011\u0011K\u0007\u0002\ty\tLh.Y7f}\u000591m\\7qkR,WC\u0001+Z)\riTK\u0016\u0005\u0006\u0019*\u0001\r\u0001\u0007\u0005\u0007\u001d*!\t\u0019A,\u0011\u0007e\u0001\u0006\f\u0005\u0002E3\u0012)aI\u0003b\u0001\u000f\u0006IqN\\*vG\u000e,7o]\u000b\u00049\u0012<GcA\u0014^=\")Aj\u0003a\u00011!)ql\u0003a\u0001A\u00061!/Z:vYR\u0004B!F1dM&\u0011!-\u0004\u0002\b'V\u001c7-Z:t!\t!E\rB\u0003f\u0017\t\u0007qIA\u0001T!\t!u\rB\u0003i\u0017\t\u0007qIA\u0001U\u0001"
)
public interface DefaultMemoisable extends Memoisable {
   static void debug_$eq(final boolean x$1) {
      DefaultMemoisable$.MODULE$.debug_$eq(x$1);
   }

   static boolean debug() {
      return DefaultMemoisable$.MODULE$.debug();
   }

   void org$json4s$scalap$DefaultMemoisable$_setter_$map_$eq(final HashMap x$1);

   HashMap map();

   // $FF: synthetic method
   static Object memo$(final DefaultMemoisable $this, final Object key, final Function0 a) {
      return $this.memo(key, a);
   }

   default Object memo(final Object key, final Function0 a) {
      return this.map().getOrElseUpdate(key, () -> this.compute(key, a));
   }

   // $FF: synthetic method
   static Object compute$(final DefaultMemoisable $this, final Object key, final Function0 a) {
      return $this.compute(key, a);
   }

   default Object compute(final Object key, final Function0 a) {
      Object var4 = a.apply();
      Object var3;
      if (var4 instanceof Success) {
         Success var5 = (Success)var4;
         this.onSuccess(key, var5);
         var3 = var5;
      } else {
         if (DefaultMemoisable$.MODULE$.debug()) {
            .MODULE$.println((new StringBuilder(4)).append(key).append(" -> ").append(var4).toString());
         }

         var3 = var4;
      }

      return var3;
   }

   // $FF: synthetic method
   static void onSuccess$(final DefaultMemoisable $this, final Object key, final Success result) {
      $this.onSuccess(key, result);
   }

   default void onSuccess(final Object key, final Success result) {
      if (result != null) {
         Object out = result.out();
         Object t = result.value();
         Tuple2 var3 = new Tuple2(out, t);
         Object out = var3._1();
         Object t = var3._2();
         if (DefaultMemoisable$.MODULE$.debug()) {
            .MODULE$.println((new StringBuilder(7)).append(key).append(" -> ").append(t).append(" (").append(out).append(")").toString());
         }

      } else {
         throw new MatchError(result);
      }
   }

   static void $init$(final DefaultMemoisable $this) {
      $this.org$json4s$scalap$DefaultMemoisable$_setter_$map_$eq(new HashMap());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
