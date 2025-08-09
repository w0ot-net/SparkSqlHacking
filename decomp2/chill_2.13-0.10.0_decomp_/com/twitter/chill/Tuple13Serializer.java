package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q4A\u0001B\u0003\u0001\u0019!)1\u000b\u0001C\u0001)\")a\u000b\u0001C\u0001/\")q\r\u0001C\u0001Q\n\tB+\u001e9mKF\u001a4+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011!B2iS2d'B\u0001\u0005\n\u0003\u001d!x/\u001b;uKJT\u0011AC\u0001\u0004G>l7\u0001A\u000b\u000f\u001byA3FL\u00195oij\u0004i\u0011$J'\r\u0001ab\u0013\t\u0004\u001fM1bB\u0001\t\u0012\u001b\u0005)\u0011B\u0001\n\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001F\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003%\u0015\u0001rb\u0006\u000e\u001dO)j\u0003g\r\u001c:y}\u0012U\tS\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t9A+\u001e9mKF\u001a\u0004CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"a\u0006\u0012\n\u0005\rB\"a\u0002(pi\"Lgn\u001a\t\u0003/\u0015J!A\n\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001eQ\u0011)\u0011\u0006\u0001b\u0001A\t\t!\t\u0005\u0002\u001eW\u0011)A\u0006\u0001b\u0001A\t\t1\t\u0005\u0002\u001e]\u0011)q\u0006\u0001b\u0001A\t\tA\t\u0005\u0002\u001ec\u0011)!\u0007\u0001b\u0001A\t\tQ\t\u0005\u0002\u001ei\u0011)Q\u0007\u0001b\u0001A\t\ta\t\u0005\u0002\u001eo\u0011)\u0001\b\u0001b\u0001A\t\tq\t\u0005\u0002\u001eu\u0011)1\b\u0001b\u0001A\t\t\u0001\n\u0005\u0002\u001e{\u0011)a\b\u0001b\u0001A\t\t\u0011\n\u0005\u0002\u001e\u0001\u0012)\u0011\t\u0001b\u0001A\t\t!\n\u0005\u0002\u001e\u0007\u0012)A\t\u0001b\u0001A\t\t1\n\u0005\u0002\u001e\r\u0012)q\t\u0001b\u0001A\t\tA\n\u0005\u0002\u001e\u0013\u0012)!\n\u0001b\u0001A\t\tQ\n\u0005\u0002M#6\tQJ\u0003\u0002O\u001f\u0006\u0011\u0011n\u001c\u0006\u0002!\u0006!!.\u0019<b\u0013\t\u0011VJ\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002+By\u0001\u0003\u0001\u000f(U5\u00024GN\u001d=\u007f\t+\u0005*A\u0003xe&$X\r\u0006\u0003Y7\u0002,\u0007CA\fZ\u0013\tQ\u0006D\u0001\u0003V]&$\b\"\u0002/\u0003\u0001\u0004i\u0016\u0001B6tKJ\u0004\"a\u00040\n\u0005}+\"\u0001B&ss>DQ!\u0019\u0002A\u0002\t\f1a\\;u!\ty1-\u0003\u0002e+\t1q*\u001e;qkRDQA\u001a\u0002A\u0002Y\t1a\u001c2k\u0003\u0011\u0011X-\u00193\u0015\tYI'n\u001c\u0005\u00069\u000e\u0001\r!\u0018\u0005\u0006W\u000e\u0001\r\u0001\\\u0001\u0003S:\u0004\"aD7\n\u00059,\"!B%oaV$\b\"\u00029\u0004\u0001\u0004\t\u0018aA2mgB\u0019!/\u001f\f\u000f\u0005M<\bC\u0001;\u0019\u001b\u0005)(B\u0001<\f\u0003\u0019a$o\\8u}%\u0011\u0001\u0010G\u0001\u0007!J,G-\u001a4\n\u0005i\\(!B\"mCN\u001c(B\u0001=\u0019\u0001"
)
public class Tuple13Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple13 obj) {
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
      kser.writeClassAndObject(out, obj._10());
      out.flush();
      kser.writeClassAndObject(out, obj._11());
      out.flush();
      kser.writeClassAndObject(out, obj._12());
      out.flush();
      kser.writeClassAndObject(out, obj._13());
      out.flush();
   }

   public Tuple13 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple13(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple13Serializer() {
      this.setImmutable(true);
   }
}
