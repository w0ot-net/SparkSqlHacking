package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4A\u0001B\u0003\u0001\u0019!)a\u000b\u0001C\u0001/\")\u0011\f\u0001C\u00015\")!\u000e\u0001C\u0001W\n\tB+\u001e9mKF\"4+\u001a:jC2L'0\u001a:\u000b\u0005\u00199\u0011!B2iS2d'B\u0001\u0005\n\u0003\u001d!x/\u001b;uKJT\u0011AC\u0001\u0004G>l7\u0001A\u000b\u0010\u001byA3FL\u00195oij\u0004i\u0011$J\u0019N\u0019\u0001A\u0004(\u0011\u0007=\u0019bC\u0004\u0002\u0011#5\tQ!\u0003\u0002\u0013\u000b\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u000b\u0016\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005I)\u0001\u0003E\f\u001b9\u001dRS\u0006M\u001a7sqz$)\u0012%L\u001b\u0005A\"\"A\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005mA\"a\u0002+va2,\u0017\u0007\u000e\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001B#\t\tC\u0005\u0005\u0002\u0018E%\u00111\u0005\u0007\u0002\b\u001d>$\b.\u001b8h!\t9R%\u0003\u0002'1\t\u0019\u0011I\\=\u0011\u0005uAC!B\u0015\u0001\u0005\u0004\u0001#!\u0001\"\u0011\u0005uYC!\u0002\u0017\u0001\u0005\u0004\u0001#!A\"\u0011\u0005uqC!B\u0018\u0001\u0005\u0004\u0001#!\u0001#\u0011\u0005u\tD!\u0002\u001a\u0001\u0005\u0004\u0001#!A#\u0011\u0005u!D!B\u001b\u0001\u0005\u0004\u0001#!\u0001$\u0011\u0005u9D!\u0002\u001d\u0001\u0005\u0004\u0001#!A$\u0011\u0005uQD!B\u001e\u0001\u0005\u0004\u0001#!\u0001%\u0011\u0005uiD!\u0002 \u0001\u0005\u0004\u0001#!A%\u0011\u0005u\u0001E!B!\u0001\u0005\u0004\u0001#!\u0001&\u0011\u0005u\u0019E!\u0002#\u0001\u0005\u0004\u0001#!A&\u0011\u0005u1E!B$\u0001\u0005\u0004\u0001#!\u0001'\u0011\u0005uIE!\u0002&\u0001\u0005\u0004\u0001#!A'\u0011\u0005uaE!B'\u0001\u0005\u0004\u0001#!\u0001(\u0011\u0005=#V\"\u0001)\u000b\u0005E\u0013\u0016AA5p\u0015\u0005\u0019\u0016\u0001\u00026bm\u0006L!!\u0016)\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?)\u0005A\u0006\u0003\u0005\t\u00019\u001dRS\u0006M\u001a7sqz$)\u0012%L\u0003\u00159(/\u001b;f)\u0011Yfl\u00195\u0011\u0005]a\u0016BA/\u0019\u0005\u0011)f.\u001b;\t\u000b}\u0013\u0001\u0019\u00011\u0002\t-\u001cXM\u001d\t\u0003\u001f\u0005L!AY\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006I\n\u0001\r!Z\u0001\u0004_V$\bCA\bg\u0013\t9WC\u0001\u0004PkR\u0004X\u000f\u001e\u0005\u0006S\n\u0001\rAF\u0001\u0004_\nT\u0017\u0001\u0002:fC\u0012$BA\u00067ne\")ql\u0001a\u0001A\")an\u0001a\u0001_\u0006\u0011\u0011N\u001c\t\u0003\u001fAL!!]\u000b\u0003\u000b%s\u0007/\u001e;\t\u000bM\u001c\u0001\u0019\u0001;\u0002\u0007\rd7\u000fE\u0002vyZq!A\u001e>\u0011\u0005]DR\"\u0001=\u000b\u0005e\\\u0011A\u0002\u001fs_>$h(\u0003\u0002|1\u00051\u0001K]3eK\u001aL!! @\u0003\u000b\rc\u0017m]:\u000b\u0005mD\u0002"
)
public class Tuple14Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple14 obj) {
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
   }

   public Tuple14 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple14(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple14Serializer() {
      this.setImmutable(true);
   }
}
