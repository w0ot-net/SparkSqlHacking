package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3A\u0001B\u0003\u0001\u0019!)!\u0007\u0001C\u0001g!)Q\u0007\u0001C\u0001m!)a\t\u0001C\u0001\u000f\n\u0001B+\u001e9mKJ\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!F\u0002\u000e=!\u001a2\u0001\u0001\b+!\ry1C\u0006\b\u0003!Ei\u0011!B\u0005\u0003%\u0015\tq\u0001]1dW\u0006<W-\u0003\u0002\u0015+\tY1jU3sS\u0006d\u0017N_3s\u0015\t\u0011R\u0001\u0005\u0003\u00185q9S\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\rQ+\b\u000f\\33!\tib\u0004\u0004\u0001\u0005\u000b}\u0001!\u0019\u0001\u0011\u0003\u0003\u0005\u000b\"!\t\u0013\u0011\u0005]\u0011\u0013BA\u0012\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u0013\n\u0005\u0019B\"aA!osB\u0011Q\u0004\u000b\u0003\u0006S\u0001\u0011\r\u0001\t\u0002\u0002\u0005B\u00111\u0006M\u0007\u0002Y)\u0011QFL\u0001\u0003S>T\u0011aL\u0001\u0005U\u00064\u0018-\u0003\u00022Y\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012\u0001\u000e\t\u0005!\u0001ar%A\u0003xe&$X\r\u0006\u00038u}\"\u0005CA\f9\u0013\tI\u0004D\u0001\u0003V]&$\b\"B\u001e\u0003\u0001\u0004a\u0014\u0001B6tKJ\u0004\"aD\u001f\n\u0005y*\"\u0001B&ss>DQ\u0001\u0011\u0002A\u0002\u0005\u000b1a\\;u!\ty!)\u0003\u0002D+\t1q*\u001e;qkRDQ!\u0012\u0002A\u0002Y\t1a\u001c2k\u0003\u0011\u0011X-\u00193\u0015\tYA\u0015J\u0014\u0005\u0006w\r\u0001\r\u0001\u0010\u0005\u0006\u0015\u000e\u0001\raS\u0001\u0003S:\u0004\"a\u0004'\n\u00055+\"!B%oaV$\b\"B(\u0004\u0001\u0004\u0001\u0016aA2mgB\u0019\u0011\u000b\u0017\f\u000f\u0005I3\u0006CA*\u0019\u001b\u0005!&BA+\f\u0003\u0019a$o\\8u}%\u0011q\u000bG\u0001\u0007!J,G-\u001a4\n\u0005eS&!B\"mCN\u001c(BA,\u0019\u0001"
)
public class Tuple2Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple2 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
      kser.writeClassAndObject(out, obj._2());
      out.flush();
   }

   public Tuple2 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple2(kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple2Serializer() {
      this.setImmutable(true);
   }
}
