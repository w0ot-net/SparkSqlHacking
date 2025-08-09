package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]a\u0001\u0002\u0003\u0006\u00011AQA\u0019\u0001\u0005\u0002\rDQ!\u001a\u0001\u0005\u0002\u0019DQA\u001e\u0001\u0005\u0002]\u0014\u0011\u0003V;qY\u0016\f\u0004hU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016'5q\u0002f\u000b\u00182i]RT\bQ\"G\u00132{%+\u0016-\u0014\u0007\u0001q!\fE\u0002\u0010'Yq!\u0001E\t\u000e\u0003\u0015I!AE\u0003\u0002\u000fA\f7m[1hK&\u0011A#\u0006\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0013\u000bA!rC\u0007\u000f(U5\u00024GN\u001d=\u007f\t+\u0005j\u0013(R)^k\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\b)V\u0004H.Z\u00199!\tib\u0004\u0004\u0001\u0005\u000b}\u0001!\u0019\u0001\u0011\u0003\u0003\u0005\u000b\"!\t\u0013\u0011\u0005]\u0011\u0013BA\u0012\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u0013\n\u0005\u0019B\"aA!osB\u0011Q\u0004\u000b\u0003\u0006S\u0001\u0011\r\u0001\t\u0002\u0002\u0005B\u0011Qd\u000b\u0003\u0006Y\u0001\u0011\r\u0001\t\u0002\u0002\u0007B\u0011QD\f\u0003\u0006_\u0001\u0011\r\u0001\t\u0002\u0002\tB\u0011Q$\r\u0003\u0006e\u0001\u0011\r\u0001\t\u0002\u0002\u000bB\u0011Q\u0004\u000e\u0003\u0006k\u0001\u0011\r\u0001\t\u0002\u0002\rB\u0011Qd\u000e\u0003\u0006q\u0001\u0011\r\u0001\t\u0002\u0002\u000fB\u0011QD\u000f\u0003\u0006w\u0001\u0011\r\u0001\t\u0002\u0002\u0011B\u0011Q$\u0010\u0003\u0006}\u0001\u0011\r\u0001\t\u0002\u0002\u0013B\u0011Q\u0004\u0011\u0003\u0006\u0003\u0002\u0011\r\u0001\t\u0002\u0002\u0015B\u0011Qd\u0011\u0003\u0006\t\u0002\u0011\r\u0001\t\u0002\u0002\u0017B\u0011QD\u0012\u0003\u0006\u000f\u0002\u0011\r\u0001\t\u0002\u0002\u0019B\u0011Q$\u0013\u0003\u0006\u0015\u0002\u0011\r\u0001\t\u0002\u0002\u001bB\u0011Q\u0004\u0014\u0003\u0006\u001b\u0002\u0011\r\u0001\t\u0002\u0002\u001dB\u0011Qd\u0014\u0003\u0006!\u0002\u0011\r\u0001\t\u0002\u0002\u001fB\u0011QD\u0015\u0003\u0006'\u0002\u0011\r\u0001\t\u0002\u0002!B\u0011Q$\u0016\u0003\u0006-\u0002\u0011\r\u0001\t\u0002\u0002#B\u0011Q\u0004\u0017\u0003\u00063\u0002\u0011\r\u0001\t\u0002\u0002%B\u00111\fY\u0007\u00029*\u0011QLX\u0001\u0003S>T\u0011aX\u0001\u0005U\u00064\u0018-\u0003\u0002b9\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012\u0001\u001a\t\u0015!\u0001arEK\u00171gYJDh\u0010\"F\u0011.s\u0015\u000bV,\u0002\u000b]\u0014\u0018\u000e^3\u0015\t\u001dTw\u000e\u001e\t\u0003/!L!!\u001b\r\u0003\tUs\u0017\u000e\u001e\u0005\u0006W\n\u0001\r\u0001\\\u0001\u0005WN,'\u000f\u0005\u0002\u0010[&\u0011a.\u0006\u0002\u0005\u0017JLx\u000eC\u0003q\u0005\u0001\u0007\u0011/A\u0002pkR\u0004\"a\u0004:\n\u0005M,\"AB(viB,H\u000fC\u0003v\u0005\u0001\u0007a#A\u0002pE*\fAA]3bIR!a\u0003_=\u007f\u0011\u0015Y7\u00011\u0001m\u0011\u0015Q8\u00011\u0001|\u0003\tIg\u000e\u0005\u0002\u0010y&\u0011Q0\u0006\u0002\u0006\u0013:\u0004X\u000f\u001e\u0005\u0007\u007f\u000e\u0001\r!!\u0001\u0002\u0007\rd7\u000fE\u0003\u0002\u0004\u0005EaC\u0004\u0003\u0002\u0006\u00055\u0001cAA\u000415\u0011\u0011\u0011\u0002\u0006\u0004\u0003\u0017Y\u0011A\u0002\u001fs_>$h(C\u0002\u0002\u0010a\ta\u0001\u0015:fI\u00164\u0017\u0002BA\n\u0003+\u0011Qa\u00117bgNT1!a\u0004\u0019\u0001"
)
public class Tuple18Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple18 obj) {
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
      kser.writeClassAndObject(out, obj._14());
      out.flush();
      kser.writeClassAndObject(out, obj._15());
      out.flush();
      kser.writeClassAndObject(out, obj._16());
      out.flush();
      kser.writeClassAndObject(out, obj._17());
      out.flush();
      kser.writeClassAndObject(out, obj._18());
      out.flush();
   }

   public Tuple18 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple18(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple18Serializer() {
      this.setImmutable(true);
   }
}
