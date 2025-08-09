package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.SortedSet;
import scala.collection.immutable.SortedSet.;
import scala.collection.mutable.Builder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005U3A\u0001B\u0003\u0001\u0019!)A\u0006\u0001C\u0001[!)q\u0006\u0001C\u0001a!)\u0001\t\u0001C\u0001\u0003\n\u00192k\u001c:uK\u0012\u001cV\r^*fe&\fG.\u001b>fe*\u0011aaB\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u0011%\tq\u0001^<jiR,'OC\u0001\u000b\u0003\r\u0019w.\\\u0002\u0001+\ti!e\u0005\u0002\u0001\u001dA\u0019qb\u0005\f\u000f\u0005A\tR\"A\u0003\n\u0005I)\u0011a\u00029bG.\fw-Z\u0005\u0003)U\u00111bS*fe&\fG.\u001b>fe*\u0011!#\u0002\t\u0004/y\u0001S\"\u0001\r\u000b\u0005eQ\u0012!C5n[V$\u0018M\u00197f\u0015\tYB$\u0001\u0006d_2dWm\u0019;j_:T\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?a\u0011\u0011bU8si\u0016$7+\u001a;\u0011\u0005\u0005\u0012C\u0002\u0001\u0003\u0006G\u0001\u0011\r\u0001\n\u0002\u0002)F\u0011Q%\u000b\t\u0003M\u001dj\u0011\u0001H\u0005\u0003Qq\u0011qAT8uQ&tw\r\u0005\u0002'U%\u00111\u0006\b\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\bF\u0001/!\r\u0001\u0002\u0001I\u0001\u0006oJLG/\u001a\u000b\u0005cQJd\b\u0005\u0002'e%\u00111\u0007\b\u0002\u0005+:LG\u000fC\u00036\u0005\u0001\u0007a'\u0001\u0003lg\u0016\u0014\bCA\b8\u0013\tATC\u0001\u0003Lef|\u0007\"\u0002\u001e\u0003\u0001\u0004Y\u0014aA8viB\u0011q\u0002P\u0005\u0003{U\u0011aaT;uaV$\b\"B \u0003\u0001\u00041\u0012aA:fi\u0006!!/Z1e)\u00111\"i\u0011%\t\u000bU\u001a\u0001\u0019\u0001\u001c\t\u000b\u0011\u001b\u0001\u0019A#\u0002\u0005%t\u0007CA\bG\u0013\t9UCA\u0003J]B,H\u000fC\u0003J\u0007\u0001\u0007!*A\u0002dYN\u00042a\u0013*\u0017\u001d\ta\u0005\u000b\u0005\u0002N95\taJ\u0003\u0002P\u0017\u00051AH]8pizJ!!\u0015\u000f\u0002\rA\u0013X\rZ3g\u0013\t\u0019FKA\u0003DY\u0006\u001c8O\u0003\u0002R9\u0001"
)
public class SortedSetSerializer extends Serializer {
   public void write(final Kryo kser, final Output out, final SortedSet set) {
      out.writeInt(set.size(), true);
      kser.writeClassAndObject(out, set.ordering());
      set.foreach((t) -> {
         $anonfun$write$1(kser, out, t);
         return BoxedUnit.UNIT;
      });
   }

   public SortedSet read(final Kryo kser, final Input in, final Class cls) {
      int size = in.readInt(true);
      Ordering ordering = (Ordering)kser.readClassAndObject(in);
      int idx = 0;
      Builder builder = .MODULE$.newBuilder(ordering);
      builder.sizeHint(size);

      while(idx < size) {
         Object item = kser.readClassAndObject(in);
         builder.$plus$eq(item);
         ++idx;
      }

      return (SortedSet)builder.result();
   }

   // $FF: synthetic method
   public static final void $anonfun$write$1(final Kryo kser$1, final Output out$1, final Object t) {
      kser$1.writeClassAndObject(out$1, t);
      out$1.flush();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
