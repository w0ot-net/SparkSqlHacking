package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001B\u0006\r\u0001MA\u0001B\u000f\u0001\u0003\u0006\u0004%\te\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005y!A\u0001\t\u0001B\u0001B\u0003-\u0011\tC\u0003H\u0001\u0011\u0005\u0001\nC\u0003N\u0001\u0011\u0005a\nC\u0003_\u0001\u0011\u0005qlB\u0004q\u0019\u0005\u0005\t\u0012A9\u0007\u000f-a\u0011\u0011!E\u0001e\")q\t\u0003C\u0001m\"9q\u000fCI\u0001\n\u0003A(!\u0006+sCZ,'o]1cY\u0016\u001cVM]5bY&TXM\u001d\u0006\u0003\u001b9\tQa\u00195jY2T!a\u0004\t\u0002\u000fQ<\u0018\u000e\u001e;fe*\t\u0011#A\u0002d_6\u001c\u0001!F\u0002\u0015i}\u0019\"\u0001A\u000b\u0011\u0007YQRD\u0004\u0002\u001815\tA\"\u0003\u0002\u001a\u0019\u00059\u0001/Y2lC\u001e,\u0017BA\u000e\u001d\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005ea\u0001C\u0001\u0010 \u0019\u0001!Q\u0001\t\u0001C\u0002\u0005\u0012\u0011aQ\t\u0003E!\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012qAT8uQ&tw\rE\u0002*aMr!AK\u0018\u000f\u0005-rS\"\u0001\u0017\u000b\u00055\u0012\u0012A\u0002\u001fs_>$h(C\u0001&\u0013\tIB%\u0003\u00022e\tA\u0011\n^3sC\ndWM\u0003\u0002\u001aIA\u0011a\u0004\u000e\u0003\u0006k\u0001\u0011\rA\u000e\u0002\u0002)F\u0011!e\u000e\t\u0003GaJ!!\u000f\u0013\u0003\u0007\u0005s\u00170A\u0006jg&kW.\u001e;bE2,W#\u0001\u001f\u0011\u0005\rj\u0014B\u0001 %\u0005\u001d\u0011un\u001c7fC:\fA\"[:J[6,H/\u00192mK\u0002\n1a\u00192g!\u0011\u0011UiM\u000f\u000e\u0003\rS!\u0001\u0012\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002G\u0007\n9a)Y2u_JL\u0018A\u0002\u001fj]&$h\b\u0006\u0002J\u0019R\u0011!j\u0013\t\u0005/\u0001\u0019T\u0004C\u0003A\t\u0001\u000f\u0011\tC\u0004;\tA\u0005\t\u0019\u0001\u001f\u0002\u000b]\u0014\u0018\u000e^3\u0015\t=\u0013v\u000b\u0018\t\u0003GAK!!\u0015\u0013\u0003\tUs\u0017\u000e\u001e\u0005\u0006'\u0016\u0001\r\u0001V\u0001\u0005WN,'\u000f\u0005\u0002\u0017+&\u0011a\u000b\b\u0002\u0005\u0017JLx\u000eC\u0003Y\u000b\u0001\u0007\u0011,A\u0002pkR\u0004\"A\u0006.\n\u0005mc\"AB(viB,H\u000fC\u0003^\u000b\u0001\u0007Q$A\u0002pE*\fAA]3bIR!Q\u0004Y1g\u0011\u0015\u0019f\u00011\u0001U\u0011\u0015\u0011g\u00011\u0001d\u0003\tIg\u000e\u0005\u0002\u0017I&\u0011Q\r\b\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0006O\u001a\u0001\r\u0001[\u0001\u0004G2\u001c\bcA5n;9\u0011!n\u001b\t\u0003W\u0011J!\u0001\u001c\u0013\u0002\rA\u0013X\rZ3g\u0013\tqwNA\u0003DY\u0006\u001c8O\u0003\u0002mI\u0005)BK]1wKJ\u001c\u0018M\u00197f'\u0016\u0014\u0018.\u00197ju\u0016\u0014\bCA\f\t'\tA1\u000f\u0005\u0002$i&\u0011Q\u000f\n\u0002\u0007\u0003:L(+\u001a4\u0015\u0003E\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nT#B=\u0002\n\u0005-Q#\u0001>+\u0005qZ8&\u0001?\u0011\u0007u\f)!D\u0001\u007f\u0015\ry\u0018\u0011A\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u0001%\u0003)\tgN\\8uCRLwN\\\u0005\u0004\u0003\u000fq(!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)QG\u0003b\u0001m\u00111\u0001E\u0003b\u0001\u0003\u001b\t2AIA\b!\u0011I\u0003'!\u0005\u0011\u0007y\tI\u0001"
)
public class TraversableSerializer extends Serializer {
   private final boolean isImmutable;
   private final Factory cbf;

   public static boolean $lessinit$greater$default$1() {
      return TraversableSerializer$.MODULE$.$lessinit$greater$default$1();
   }

   public boolean isImmutable() {
      return this.isImmutable;
   }

   public void write(final Kryo kser, final Output out, final Iterable obj) {
      out.writeInt(obj.size(), true);
      obj.foreach((t) -> {
         $anonfun$write$1(kser, out, t);
         return BoxedUnit.UNIT;
      });
   }

   public Iterable read(final Kryo kser, final Input in, final Class cls) {
      int size = in.readInt(true);
      int idx = 0;
      Builder builder = this.cbf.newBuilder();
      builder.sizeHint(size);

      while(idx < size) {
         Object item = kser.readClassAndObject(in);
         builder.$plus$eq(item);
         ++idx;
      }

      return (Iterable)builder.result();
   }

   // $FF: synthetic method
   public static final void $anonfun$write$1(final Kryo kser$1, final Output out$1, final Object t) {
      kser$1.writeClassAndObject(out$1, t);
      out$1.flush();
   }

   public TraversableSerializer(final boolean isImmutable, final Factory cbf) {
      this.isImmutable = isImmutable;
      this.cbf = cbf;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
