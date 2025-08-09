package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple10;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4A\u0001B\u0003\u0001\u0019!)!\n\u0001C\u0001\u0017\")Q\n\u0001C\u0001\u001d\")a\f\u0001C\u0001?\n\tB+\u001e9mKF\u00024+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011!B2iS2d'B\u0001\u0005\n\u0003\u001d!x/\u001b;uKJT\u0011AC\u0001\u0004G>l7\u0001A\u000b\f\u001byA3FL\u00195oij\u0004iE\u0002\u0001\u001d\t\u00032aD\n\u0017\u001d\t\u0001\u0012#D\u0001\u0006\u0013\t\u0011R!A\u0004qC\u000e\\\u0017mZ3\n\u0005Q)\"aC&TKJL\u0017\r\\5{KJT!AE\u0003\u0011\u0019]QBd\n\u0016.aM2\u0014\bP \u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011q\u0001V;qY\u0016\f\u0004\u0007\u0005\u0002\u001e=1\u0001A!B\u0010\u0001\u0005\u0004\u0001#!A!\u0012\u0005\u0005\"\u0003CA\f#\u0013\t\u0019\u0003DA\u0004O_RD\u0017N\\4\u0011\u0005])\u0013B\u0001\u0014\u0019\u0005\r\te.\u001f\t\u0003;!\"Q!\u000b\u0001C\u0002\u0001\u0012\u0011A\u0011\t\u0003;-\"Q\u0001\f\u0001C\u0002\u0001\u0012\u0011a\u0011\t\u0003;9\"Qa\f\u0001C\u0002\u0001\u0012\u0011\u0001\u0012\t\u0003;E\"QA\r\u0001C\u0002\u0001\u0012\u0011!\u0012\t\u0003;Q\"Q!\u000e\u0001C\u0002\u0001\u0012\u0011A\u0012\t\u0003;]\"Q\u0001\u000f\u0001C\u0002\u0001\u0012\u0011a\u0012\t\u0003;i\"Qa\u000f\u0001C\u0002\u0001\u0012\u0011\u0001\u0013\t\u0003;u\"QA\u0010\u0001C\u0002\u0001\u0012\u0011!\u0013\t\u0003;\u0001#Q!\u0011\u0001C\u0002\u0001\u0012\u0011A\u0013\t\u0003\u0007\"k\u0011\u0001\u0012\u0006\u0003\u000b\u001a\u000b!![8\u000b\u0003\u001d\u000bAA[1wC&\u0011\u0011\n\u0012\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00031\u0003B\u0002\u0005\u0001\u001dO)j\u0003g\r\u001c:y}\nQa\u001e:ji\u0016$Ba\u0014*X9B\u0011q\u0003U\u0005\u0003#b\u0011A!\u00168ji\")1K\u0001a\u0001)\u0006!1n]3s!\tyQ+\u0003\u0002W+\t!1J]=p\u0011\u0015A&\u00011\u0001Z\u0003\ryW\u000f\u001e\t\u0003\u001fiK!aW\u000b\u0003\r=+H\u000f];u\u0011\u0015i&\u00011\u0001\u0017\u0003\ry'M[\u0001\u0005e\u0016\fG\r\u0006\u0003\u0017A\u00064\u0007\"B*\u0004\u0001\u0004!\u0006\"\u00022\u0004\u0001\u0004\u0019\u0017AA5o!\tyA-\u0003\u0002f+\t)\u0011J\u001c9vi\")qm\u0001a\u0001Q\u0006\u00191\r\\:\u0011\u0007%\u0004hC\u0004\u0002k]B\u00111\u000eG\u0007\u0002Y*\u0011QnC\u0001\u0007yI|w\u000e\u001e \n\u0005=D\u0012A\u0002)sK\u0012,g-\u0003\u0002re\n)1\t\\1tg*\u0011q\u000e\u0007"
)
public class Tuple10Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple10 obj) {
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
   }

   public Tuple10 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple10(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple10Serializer() {
      this.setImmutable(true);
   }
}
