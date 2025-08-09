package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ua\u0001\u0002\u0003\u0006\u00011AQ!\u001a\u0001\u0005\u0002\u0019DQ\u0001\u001b\u0001\u0005\u0002%DQ!\u001f\u0001\u0005\u0002i\u0014\u0011\u0003V;qY\u0016\f\u0014hU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016)5q\u0002f\u000b\u00182i]RT\bQ\"G\u00132{%+\u0016-\\'\r\u0001a\"\u0018\t\u0004\u001fM1bB\u0001\t\u0012\u001b\u0005)\u0011B\u0001\n\u0006\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001F\u000b\u0003\u0017-\u001bVM]5bY&TXM\u001d\u0006\u0003%\u0015\u0001Rc\u0006\u000e\u001dO)j\u0003g\r\u001c:y}\u0012U\tS&O#R;&,D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u001d!V\u000f\u001d7fce\u0002\"!\b\u0010\r\u0001\u0011)q\u0004\u0001b\u0001A\t\t\u0011)\u0005\u0002\"IA\u0011qCI\u0005\u0003Ga\u0011qAT8uQ&tw\r\u0005\u0002\u0018K%\u0011a\u0005\u0007\u0002\u0004\u0003:L\bCA\u000f)\t\u0015I\u0003A1\u0001!\u0005\u0005\u0011\u0005CA\u000f,\t\u0015a\u0003A1\u0001!\u0005\u0005\u0019\u0005CA\u000f/\t\u0015y\u0003A1\u0001!\u0005\u0005!\u0005CA\u000f2\t\u0015\u0011\u0004A1\u0001!\u0005\u0005)\u0005CA\u000f5\t\u0015)\u0004A1\u0001!\u0005\u00051\u0005CA\u000f8\t\u0015A\u0004A1\u0001!\u0005\u00059\u0005CA\u000f;\t\u0015Y\u0004A1\u0001!\u0005\u0005A\u0005CA\u000f>\t\u0015q\u0004A1\u0001!\u0005\u0005I\u0005CA\u000fA\t\u0015\t\u0005A1\u0001!\u0005\u0005Q\u0005CA\u000fD\t\u0015!\u0005A1\u0001!\u0005\u0005Y\u0005CA\u000fG\t\u00159\u0005A1\u0001!\u0005\u0005a\u0005CA\u000fJ\t\u0015Q\u0005A1\u0001!\u0005\u0005i\u0005CA\u000fM\t\u0015i\u0005A1\u0001!\u0005\u0005q\u0005CA\u000fP\t\u0015\u0001\u0006A1\u0001!\u0005\u0005y\u0005CA\u000fS\t\u0015\u0019\u0006A1\u0001!\u0005\u0005\u0001\u0006CA\u000fV\t\u00151\u0006A1\u0001!\u0005\u0005\t\u0006CA\u000fY\t\u0015I\u0006A1\u0001!\u0005\u0005\u0011\u0006CA\u000f\\\t\u0015a\u0006A1\u0001!\u0005\u0005\u0019\u0006C\u00010d\u001b\u0005y&B\u00011b\u0003\tIwNC\u0001c\u0003\u0011Q\u0017M^1\n\u0005\u0011|&\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001h!U\u0001\u0002\u0001H\u0014+[A\u001ad'\u000f\u001f@\u0005\u0016C5JT)U/j\u000bQa\u001e:ji\u0016$BA[7soB\u0011qc[\u0005\u0003Yb\u0011A!\u00168ji\")aN\u0001a\u0001_\u0006!1n]3s!\ty\u0001/\u0003\u0002r+\t!1J]=p\u0011\u0015\u0019(\u00011\u0001u\u0003\ryW\u000f\u001e\t\u0003\u001fUL!A^\u000b\u0003\r=+H\u000f];u\u0011\u0015A(\u00011\u0001\u0017\u0003\ry'M[\u0001\u0005e\u0016\fG\rF\u0003\u0017wr\f\u0019\u0001C\u0003o\u0007\u0001\u0007q\u000eC\u0003~\u0007\u0001\u0007a0\u0001\u0002j]B\u0011qb`\u0005\u0004\u0003\u0003)\"!B%oaV$\bbBA\u0003\u0007\u0001\u0007\u0011qA\u0001\u0004G2\u001c\b#BA\u0005\u0003/1b\u0002BA\u0006\u0003'\u00012!!\u0004\u0019\u001b\t\tyAC\u0002\u0002\u0012-\ta\u0001\u0010:p_Rt\u0014bAA\u000b1\u00051\u0001K]3eK\u001aLA!!\u0007\u0002\u001c\t)1\t\\1tg*\u0019\u0011Q\u0003\r"
)
public class Tuple19Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple19 obj) {
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
      kser.writeClassAndObject(out, obj._19());
      out.flush();
   }

   public Tuple19 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple19(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple19Serializer() {
      this.setImmutable(true);
   }
}
