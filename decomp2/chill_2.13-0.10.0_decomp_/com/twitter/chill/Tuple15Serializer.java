package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a\u0001\u0002\u0003\u0006\u00011AQ!\u0017\u0001\u0005\u0002iCQ\u0001\u0018\u0001\u0005\u0002uCQ!\u001c\u0001\u0005\u00029\u0014\u0011\u0003V;qY\u0016\fTgU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016!5q\u0002f\u000b\u00182i]RT\bQ\"G\u00132{5c\u0001\u0001\u000f#B\u0019qb\u0005\f\u000f\u0005A\tR\"A\u0003\n\u0005I)\u0011a\u00029bG.\fw-Z\u0005\u0003)U\u00111bS*fe&\fG.\u001b>fe*\u0011!#\u0002\t\u0012/iarEK\u00171gYJDh\u0010\"F\u0011.sU\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u000fQ+\b\u000f\\32kA\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001!\u0005\u0005\t\u0015CA\u0011%!\t9\"%\u0003\u0002$1\t9aj\u001c;iS:<\u0007CA\f&\u0013\t1\u0003DA\u0002B]f\u0004\"!\b\u0015\u0005\u000b%\u0002!\u0019\u0001\u0011\u0003\u0003\t\u0003\"!H\u0016\u0005\u000b1\u0002!\u0019\u0001\u0011\u0003\u0003\r\u0003\"!\b\u0018\u0005\u000b=\u0002!\u0019\u0001\u0011\u0003\u0003\u0011\u0003\"!H\u0019\u0005\u000bI\u0002!\u0019\u0001\u0011\u0003\u0003\u0015\u0003\"!\b\u001b\u0005\u000bU\u0002!\u0019\u0001\u0011\u0003\u0003\u0019\u0003\"!H\u001c\u0005\u000ba\u0002!\u0019\u0001\u0011\u0003\u0003\u001d\u0003\"!\b\u001e\u0005\u000bm\u0002!\u0019\u0001\u0011\u0003\u0003!\u0003\"!H\u001f\u0005\u000by\u0002!\u0019\u0001\u0011\u0003\u0003%\u0003\"!\b!\u0005\u000b\u0005\u0003!\u0019\u0001\u0011\u0003\u0003)\u0003\"!H\"\u0005\u000b\u0011\u0003!\u0019\u0001\u0011\u0003\u0003-\u0003\"!\b$\u0005\u000b\u001d\u0003!\u0019\u0001\u0011\u0003\u00031\u0003\"!H%\u0005\u000b)\u0003!\u0019\u0001\u0011\u0003\u00035\u0003\"!\b'\u0005\u000b5\u0003!\u0019\u0001\u0011\u0003\u00039\u0003\"!H(\u0005\u000bA\u0003!\u0019\u0001\u0011\u0003\u0003=\u0003\"AU,\u000e\u0003MS!\u0001V+\u0002\u0005%|'\"\u0001,\u0002\t)\fg/Y\u0005\u00031N\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#A.\u0011#A\u0001Ad\n\u0016.aM2\u0014\bP C\u000b\"[e*A\u0003xe&$X\r\u0006\u0003_C\u001a\\\u0007CA\f`\u0013\t\u0001\u0007D\u0001\u0003V]&$\b\"\u00022\u0003\u0001\u0004\u0019\u0017\u0001B6tKJ\u0004\"a\u00043\n\u0005\u0015,\"\u0001B&ss>DQa\u001a\u0002A\u0002!\f1a\\;u!\ty\u0011.\u0003\u0002k+\t1q*\u001e;qkRDQ\u0001\u001c\u0002A\u0002Y\t1a\u001c2k\u0003\u0011\u0011X-\u00193\u0015\tYy\u0007/\u001e\u0005\u0006E\u000e\u0001\ra\u0019\u0005\u0006c\u000e\u0001\rA]\u0001\u0003S:\u0004\"aD:\n\u0005Q,\"!B%oaV$\b\"\u0002<\u0004\u0001\u00049\u0018aA2mgB\u0019\u0001p \f\u000f\u0005el\bC\u0001>\u0019\u001b\u0005Y(B\u0001?\f\u0003\u0019a$o\\8u}%\u0011a\u0010G\u0001\u0007!J,G-\u001a4\n\t\u0005\u0005\u00111\u0001\u0002\u0006\u00072\f7o\u001d\u0006\u0003}b\u0001"
)
public class Tuple15Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple15 obj) {
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
   }

   public Tuple15 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple15(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple15Serializer() {
      this.setImmutable(true);
   }
}
