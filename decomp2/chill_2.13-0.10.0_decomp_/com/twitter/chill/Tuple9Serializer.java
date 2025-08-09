package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4A\u0001B\u0003\u0001\u0019!)q\t\u0001C\u0001\u0011\")!\n\u0001C\u0001\u0017\")1\f\u0001C\u00019\n\u0001B+\u001e9mKf\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!\u0006\u0006\u000e=!Zc&\r\u001b8uu\u001a2\u0001\u0001\b@!\ry1C\u0006\b\u0003!Ei\u0011!B\u0005\u0003%\u0015\tq\u0001]1dW\u0006<W-\u0003\u0002\u0015+\tY1jU3sS\u0006d\u0017N_3s\u0015\t\u0011R\u0001E\u0006\u00185q9#&\f\u00194mebT\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\rQ+\b\u000f\\3:!\tib\u0004\u0004\u0001\u0005\u000b}\u0001!\u0019\u0001\u0011\u0003\u0003\u0005\u000b\"!\t\u0013\u0011\u0005]\u0011\u0013BA\u0012\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u0013\n\u0005\u0019B\"aA!osB\u0011Q\u0004\u000b\u0003\u0006S\u0001\u0011\r\u0001\t\u0002\u0002\u0005B\u0011Qd\u000b\u0003\u0006Y\u0001\u0011\r\u0001\t\u0002\u0002\u0007B\u0011QD\f\u0003\u0006_\u0001\u0011\r\u0001\t\u0002\u0002\tB\u0011Q$\r\u0003\u0006e\u0001\u0011\r\u0001\t\u0002\u0002\u000bB\u0011Q\u0004\u000e\u0003\u0006k\u0001\u0011\r\u0001\t\u0002\u0002\rB\u0011Qd\u000e\u0003\u0006q\u0001\u0011\r\u0001\t\u0002\u0002\u000fB\u0011QD\u000f\u0003\u0006w\u0001\u0011\r\u0001\t\u0002\u0002\u0011B\u0011Q$\u0010\u0003\u0006}\u0001\u0011\r\u0001\t\u0002\u0002\u0013B\u0011\u0001)R\u0007\u0002\u0003*\u0011!iQ\u0001\u0003S>T\u0011\u0001R\u0001\u0005U\u00064\u0018-\u0003\u0002G\u0003\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!\u0013\t\f!\u0001arEK\u00171gYJD(A\u0003xe&$X\r\u0006\u0003M\u001fRK\u0006CA\fN\u0013\tq\u0005D\u0001\u0003V]&$\b\"\u0002)\u0003\u0001\u0004\t\u0016\u0001B6tKJ\u0004\"a\u0004*\n\u0005M+\"\u0001B&ss>DQ!\u0016\u0002A\u0002Y\u000b1a\\;u!\tyq+\u0003\u0002Y+\t1q*\u001e;qkRDQA\u0017\u0002A\u0002Y\t1a\u001c2k\u0003\u0011\u0011X-\u00193\u0015\tYifl\u0019\u0005\u0006!\u000e\u0001\r!\u0015\u0005\u0006?\u000e\u0001\r\u0001Y\u0001\u0003S:\u0004\"aD1\n\u0005\t,\"!B%oaV$\b\"\u00023\u0004\u0001\u0004)\u0017aA2mgB\u0019a-\u001c\f\u000f\u0005\u001d\\\u0007C\u00015\u0019\u001b\u0005I'B\u00016\f\u0003\u0019a$o\\8u}%\u0011A\u000eG\u0001\u0007!J,G-\u001a4\n\u00059|'!B\"mCN\u001c(B\u00017\u0019\u0001"
)
public class Tuple9Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple9 obj) {
      kser.writeClassAndObject(out, obj._1());
      out.flush();
      kser.writeClassAndObject(out, obj._2());
      out.flush();
      kser.writeClassAndObject(out, obj._3());
      out.flush();
      kser.writeClassAndObject(out, obj._4());
      out.flush();
      kser.writeClassAndObject(out, obj._5());
      out.flush();
      kser.writeClassAndObject(out, obj._6());
      out.flush();
      kser.writeClassAndObject(out, obj._7());
      out.flush();
      kser.writeClassAndObject(out, obj._8());
      out.flush();
      kser.writeClassAndObject(out, obj._9());
      out.flush();
   }

   public Tuple9 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple9(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple9Serializer() {
      this.setImmutable(true);
   }
}
