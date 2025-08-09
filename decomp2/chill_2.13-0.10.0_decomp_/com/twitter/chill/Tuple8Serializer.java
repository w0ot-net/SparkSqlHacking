package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000554A\u0001B\u0003\u0001\u0019!)A\t\u0001C\u0001\u000b\")q\t\u0001C\u0001\u0011\")\u0001\f\u0001C\u00013\n\u0001B+\u001e9mKb\u001aVM]5bY&TXM\u001d\u0006\u0003\r\u001d\tQa\u00195jY2T!\u0001C\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t!\"A\u0002d_6\u001c\u0001!F\u0005\u000e=!Zc&\r\u001b8uM\u0019\u0001A\u0004\u001f\u0011\u0007=\u0019bC\u0004\u0002\u0011#5\tQ!\u0003\u0002\u0013\u000b\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u000b\u0016\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005I)\u0001CC\f\u001b9\u001dRS\u0006M\u001a7s5\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004UkBdW\r\u000f\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0018E%\u00111\u0005\u0007\u0002\b\u001d>$\b.\u001b8h!\t9R%\u0003\u0002'1\t\u0019\u0011I\\=\u0011\u0005uAC!B\u0015\u0001\u0005\u0004\u0001#!\u0001\"\u0011\u0005uYC!\u0002\u0017\u0001\u0005\u0004\u0001#!A\"\u0011\u0005uqC!B\u0018\u0001\u0005\u0004\u0001#!\u0001#\u0011\u0005u\tD!\u0002\u001a\u0001\u0005\u0004\u0001#!A#\u0011\u0005u!D!B\u001b\u0001\u0005\u0004\u0001#!\u0001$\u0011\u0005u9D!\u0002\u001d\u0001\u0005\u0004\u0001#!A$\u0011\u0005uQD!B\u001e\u0001\u0005\u0004\u0001#!\u0001%\u0011\u0005u\u0012U\"\u0001 \u000b\u0005}\u0002\u0015AA5p\u0015\u0005\t\u0015\u0001\u00026bm\u0006L!a\u0011 \u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u00051\u0005C\u0003\t\u00019\u001dRS\u0006M\u001a7s\u0005)qO]5uKR!\u0011\nT)W!\t9\"*\u0003\u0002L1\t!QK\\5u\u0011\u0015i%\u00011\u0001O\u0003\u0011Y7/\u001a:\u0011\u0005=y\u0015B\u0001)\u0016\u0005\u0011Y%/_8\t\u000bI\u0013\u0001\u0019A*\u0002\u0007=,H\u000f\u0005\u0002\u0010)&\u0011Q+\u0006\u0002\u0007\u001fV$\b/\u001e;\t\u000b]\u0013\u0001\u0019\u0001\f\u0002\u0007=\u0014'.\u0001\u0003sK\u0006$G\u0003\u0002\f[7\u0002DQ!T\u0002A\u00029CQ\u0001X\u0002A\u0002u\u000b!!\u001b8\u0011\u0005=q\u0016BA0\u0016\u0005\u0015Ie\u000e];u\u0011\u0015\t7\u00011\u0001c\u0003\r\u0019Gn\u001d\t\u0004G*4bB\u00013i!\t)\u0007$D\u0001g\u0015\t97\"\u0001\u0004=e>|GOP\u0005\u0003Sb\ta\u0001\u0015:fI\u00164\u0017BA6m\u0005\u0015\u0019E.Y:t\u0015\tI\u0007\u0004"
)
public class Tuple8Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple8 obj) {
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
   }

   public Tuple8 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple8(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple8Serializer() {
      this.setImmutable(true);
   }
}
