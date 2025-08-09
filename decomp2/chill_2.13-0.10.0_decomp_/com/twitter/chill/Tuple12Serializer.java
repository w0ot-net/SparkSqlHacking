package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple12;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4A\u0001B\u0003\u0001\u0019!)\u0001\u000b\u0001C\u0001#\")1\u000b\u0001C\u0001)\")A\r\u0001C\u0001K\n\tB+\u001e9mKF\u00124+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011!B2iS2d'B\u0001\u0005\n\u0003\u001d!x/\u001b;uKJT\u0011AC\u0001\u0004G>l7\u0001A\u000b\u000e\u001byA3FL\u00195oij\u0004i\u0011$\u0014\u0007\u0001q\u0001\nE\u0002\u0010'Yq!\u0001E\t\u000e\u0003\u0015I!AE\u0003\u0002\u000fA\f7m[1hK&\u0011A#\u0006\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0013\u000bAqqC\u0007\u000f(U5\u00024GN\u001d=\u007f\t+U\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\u000fQ+\b\u000f\\32eA\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001!\u0005\u0005\t\u0015CA\u0011%!\t9\"%\u0003\u0002$1\t9aj\u001c;iS:<\u0007CA\f&\u0013\t1\u0003DA\u0002B]f\u0004\"!\b\u0015\u0005\u000b%\u0002!\u0019\u0001\u0011\u0003\u0003\t\u0003\"!H\u0016\u0005\u000b1\u0002!\u0019\u0001\u0011\u0003\u0003\r\u0003\"!\b\u0018\u0005\u000b=\u0002!\u0019\u0001\u0011\u0003\u0003\u0011\u0003\"!H\u0019\u0005\u000bI\u0002!\u0019\u0001\u0011\u0003\u0003\u0015\u0003\"!\b\u001b\u0005\u000bU\u0002!\u0019\u0001\u0011\u0003\u0003\u0019\u0003\"!H\u001c\u0005\u000ba\u0002!\u0019\u0001\u0011\u0003\u0003\u001d\u0003\"!\b\u001e\u0005\u000bm\u0002!\u0019\u0001\u0011\u0003\u0003!\u0003\"!H\u001f\u0005\u000by\u0002!\u0019\u0001\u0011\u0003\u0003%\u0003\"!\b!\u0005\u000b\u0005\u0003!\u0019\u0001\u0011\u0003\u0003)\u0003\"!H\"\u0005\u000b\u0011\u0003!\u0019\u0001\u0011\u0003\u0003-\u0003\"!\b$\u0005\u000b\u001d\u0003!\u0019\u0001\u0011\u0003\u00031\u0003\"!\u0013(\u000e\u0003)S!a\u0013'\u0002\u0005%|'\"A'\u0002\t)\fg/Y\u0005\u0003\u001f*\u0013AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001*\u0011\u001dA\u0001Ad\n\u0016.aM2\u0014\bP C\u000b\u0006)qO]5uKR!Q\u000bW/c!\t9b+\u0003\u0002X1\t!QK\\5u\u0011\u0015I&\u00011\u0001[\u0003\u0011Y7/\u001a:\u0011\u0005=Y\u0016B\u0001/\u0016\u0005\u0011Y%/_8\t\u000by\u0013\u0001\u0019A0\u0002\u0007=,H\u000f\u0005\u0002\u0010A&\u0011\u0011-\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000b\r\u0014\u0001\u0019\u0001\f\u0002\u0007=\u0014'.\u0001\u0003sK\u0006$G\u0003\u0002\fgO2DQ!W\u0002A\u0002iCQ\u0001[\u0002A\u0002%\f!!\u001b8\u0011\u0005=Q\u0017BA6\u0016\u0005\u0015Ie\u000e];u\u0011\u0015i7\u00011\u0001o\u0003\r\u0019Gn\u001d\t\u0004_Z4bB\u00019u!\t\t\b$D\u0001s\u0015\t\u00198\"\u0001\u0004=e>|GOP\u0005\u0003kb\ta\u0001\u0015:fI\u00164\u0017BA<y\u0005\u0015\u0019E.Y:t\u0015\t)\b\u0004"
)
public class Tuple12Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple12 obj) {
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
   }

   public Tuple12 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple12(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple12Serializer() {
      this.setImmutable(true);
   }
}
