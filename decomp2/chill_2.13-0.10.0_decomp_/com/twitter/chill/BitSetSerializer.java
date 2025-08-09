package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.lang.invoke.SerializedLambda;
import scala.collection.immutable.BitSet;
import scala.math.Ordering.Int.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005%3A\u0001B\u0003\u0001\u0019!)q\u0004\u0001C\u0001A!)!\u0005\u0001C\u0001G!)A\u0007\u0001C\u0001k\t\u0001\")\u001b;TKR\u001cVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001a\u0005\u0002\u0001\u001bA\u0019aBE\u000b\u000f\u0005=\u0001R\"A\u0003\n\u0005E)\u0011a\u00029bG.\fw-Z\u0005\u0003'Q\u00111bS*fe&\fG.\u001b>fe*\u0011\u0011#\u0002\t\u0003-ui\u0011a\u0006\u0006\u00031e\t\u0011\"[7nkR\f'\r\\3\u000b\u0005iY\u0012AC2pY2,7\r^5p]*\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f/\t1!)\u001b;TKR\fa\u0001P5oSRtD#A\u0011\u0011\u0005=\u0001\u0011!B<sSR,G\u0003\u0002\u0013)[I\u0002\"!\n\u0014\u000e\u0003mI!aJ\u000e\u0003\tUs\u0017\u000e\u001e\u0005\u0006S\t\u0001\rAK\u0001\u0002WB\u0011abK\u0005\u0003YQ\u0011Aa\u0013:z_\")aF\u0001a\u0001_\u0005\tq\u000e\u0005\u0002\u000fa%\u0011\u0011\u0007\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000bM\u0012\u0001\u0019A\u000b\u0002\u0003Y\fAA]3bIR!QCN\u001c=\u0011\u0015I3\u00011\u0001+\u0011\u0015A4\u00011\u0001:\u0003\u0005I\u0007C\u0001\b;\u0013\tYDCA\u0003J]B,H\u000fC\u0003>\u0007\u0001\u0007a(A\u0001d!\ryd)\u0006\b\u0003\u0001\u0012\u0003\"!Q\u000e\u000e\u0003\tS!aQ\u0006\u0002\rq\u0012xn\u001c;?\u0013\t)5$\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u000f\"\u0013Qa\u00117bgNT!!R\u000e"
)
public class BitSetSerializer extends Serializer {
   public void write(final Kryo k, final Output o, final BitSet v) {
      int size = v.size();
      o.writeInt(size, true);
      if (size > 0) {
         BoxesRunTime.boxToInteger(o.writeInt(v.max(.MODULE$), true));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      IntRef previous = IntRef.create(-1);
      v.foreach((JFunction1.mcVI.sp)(vi) -> {
         if (previous.elem >= 0) {
            o.writeInt(vi - previous.elem, true);
         } else {
            o.writeInt(vi, true);
         }

         previous.elem = vi;
      });
   }

   public BitSet read(final Kryo k, final Input i, final Class c) {
      int size = i.readInt(true);
      BitSet var10000;
      if (size == 0) {
         var10000 = scala.collection.immutable.BitSet..MODULE$.empty();
      } else {
         IntRef sum = IntRef.create(0);
         long[] bits = new long[i.readInt(true) / 64 + 1];
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), size).foreach$mVc$sp((JFunction1.mcVI.sp)(step) -> {
            sum.elem += i.readInt(true);
            int var4 = sum.elem / 64;
            bits[var4] |= 1L << sum.elem % 64;
         });
         var10000 = scala.collection.immutable.BitSet..MODULE$.fromBitMask(bits);
      }

      return var10000;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
