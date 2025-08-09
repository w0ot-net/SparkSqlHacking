package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d4A\u0001B\u0003\u0001\u0019!)a\b\u0001C\u0001\u007f!)\u0011\t\u0001C\u0001\u0005\")!\u000b\u0001C\u0001'\n\u0001B+\u001e9mKZ\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!F\u0004\u000e=!Zc&\r\u001b\u0014\u0007\u0001qa\u0007E\u0002\u0010'Yq!\u0001E\t\u000e\u0003\u0015I!AE\u0003\u0002\u000fA\f7m[1hK&\u0011A#\u0006\u0002\f\u0017N+'/[1mSj,'O\u0003\u0002\u0013\u000bAAqC\u0007\u000f(U5\u00024'D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019!V\u000f\u001d7fmA\u0011QD\b\u0007\u0001\t\u0015y\u0002A1\u0001!\u0005\u0005\t\u0015CA\u0011%!\t9\"%\u0003\u0002$1\t9aj\u001c;iS:<\u0007CA\f&\u0013\t1\u0003DA\u0002B]f\u0004\"!\b\u0015\u0005\u000b%\u0002!\u0019\u0001\u0011\u0003\u0003\t\u0003\"!H\u0016\u0005\u000b1\u0002!\u0019\u0001\u0011\u0003\u0003\r\u0003\"!\b\u0018\u0005\u000b=\u0002!\u0019\u0001\u0011\u0003\u0003\u0011\u0003\"!H\u0019\u0005\u000bI\u0002!\u0019\u0001\u0011\u0003\u0003\u0015\u0003\"!\b\u001b\u0005\u000bU\u0002!\u0019\u0001\u0011\u0003\u0003\u0019\u0003\"a\u000e\u001f\u000e\u0003aR!!\u000f\u001e\u0002\u0005%|'\"A\u001e\u0002\t)\fg/Y\u0005\u0003{a\u0012AbU3sS\u0006d\u0017N_1cY\u0016\fa\u0001P5oSRtD#\u0001!\u0011\u0011A\u0001Ad\n\u0016.aM\nQa\u001e:ji\u0016$Ba\u0011$L!B\u0011q\u0003R\u0005\u0003\u000bb\u0011A!\u00168ji\")qI\u0001a\u0001\u0011\u0006!1n]3s!\ty\u0011*\u0003\u0002K+\t!1J]=p\u0011\u0015a%\u00011\u0001N\u0003\ryW\u000f\u001e\t\u0003\u001f9K!aT\u000b\u0003\r=+H\u000f];u\u0011\u0015\t&\u00011\u0001\u0017\u0003\ry'M[\u0001\u0005e\u0016\fG\r\u0006\u0003\u0017)VS\u0006\"B$\u0004\u0001\u0004A\u0005\"\u0002,\u0004\u0001\u00049\u0016AA5o!\ty\u0001,\u0003\u0002Z+\t)\u0011J\u001c9vi\")1l\u0001a\u00019\u0006\u00191\r\\:\u0011\u0007u#gC\u0004\u0002_EB\u0011q\fG\u0007\u0002A*\u0011\u0011mC\u0001\u0007yI|w\u000e\u001e \n\u0005\rD\u0012A\u0002)sK\u0012,g-\u0003\u0002fM\n)1\t\\1tg*\u00111\r\u0007"
)
public class Tuple6Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple6 obj) {
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
   }

   public Tuple6 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple6(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple6Serializer() {
      this.setImmutable(true);
   }
}
