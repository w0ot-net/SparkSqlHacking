package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a3A\u0001B\u0003\u0001\u0019!)q\u0006\u0001C\u0001a!)!\u0007\u0001C\u0001g!)1\t\u0001C\u0001\t\n\u0001B+\u001e9mKF\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!\u0006\u0002\u000e=M\u0019\u0001AD\u0014\u0011\u0007=\u0019bC\u0004\u0002\u0011#5\tQ!\u0003\u0002\u0013\u000b\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u000b\u0016\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005I)\u0001cA\f\u001b95\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004UkBdW-\r\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0018E%\u00111\u0005\u0007\u0002\b\u001d>$\b.\u001b8h!\t9R%\u0003\u0002'1\t\u0019\u0011I\\=\u0011\u0005!jS\"A\u0015\u000b\u0005)Z\u0013AA5p\u0015\u0005a\u0013\u0001\u00026bm\u0006L!AL\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005\t\u0004c\u0001\t\u00019\u0005)qO]5uKR!Ag\u000e\u001fB!\t9R'\u0003\u000271\t!QK\\5u\u0011\u0015A$\u00011\u0001:\u0003\u0011Y7/\u001a:\u0011\u0005=Q\u0014BA\u001e\u0016\u0005\u0011Y%/_8\t\u000bu\u0012\u0001\u0019\u0001 \u0002\u0007=,H\u000f\u0005\u0002\u0010\u007f%\u0011\u0001)\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000b\t\u0013\u0001\u0019\u0001\f\u0002\u0007=\u0014'.\u0001\u0003sK\u0006$G\u0003\u0002\fF\r.CQ\u0001O\u0002A\u0002eBQaR\u0002A\u0002!\u000b!!\u001b8\u0011\u0005=I\u0015B\u0001&\u0016\u0005\u0015Ie\u000e];u\u0011\u0015a5\u00011\u0001N\u0003\r\u0019Gn\u001d\t\u0004\u001dV3bBA(T!\t\u0001\u0006$D\u0001R\u0015\t\u00116\"\u0001\u0004=e>|GOP\u0005\u0003)b\ta\u0001\u0015:fI\u00164\u0017B\u0001,X\u0005\u0015\u0019E.Y:t\u0015\t!\u0006\u0004"
)
public class Tuple1Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple1 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
   }

   public Tuple1 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple1(kser.readClassAndObject(in));
   }

   public Tuple1Serializer() {
      this.setImmutable(true);
   }
}
