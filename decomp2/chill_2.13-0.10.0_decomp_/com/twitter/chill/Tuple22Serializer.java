package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple22;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=b\u0001\u0002\u0003\u0006\u00011AQA\u001c\u0001\u0005\u0002=DQ!\u001d\u0001\u0005\u0002IDq!!\u0002\u0001\t\u0003\t9AA\tUkBdWM\r\u001aTKJL\u0017\r\\5{KJT!AB\u0004\u0002\u000b\rD\u0017\u000e\u001c7\u000b\u0005!I\u0011a\u0002;xSR$XM\u001d\u0006\u0002\u0015\u0005\u00191m\\7\u0004\u0001U9RB\b\u0015,]E\"tGO\u001fA\u0007\u001aKEj\u0014*V1ns\u0016\rZ\n\u0004\u000191\u0007cA\b\u0014-9\u0011\u0001#E\u0007\u0002\u000b%\u0011!#B\u0001\ba\u0006\u001c7.Y4f\u0013\t!RCA\u0006L'\u0016\u0014\u0018.\u00197ju\u0016\u0014(B\u0001\n\u0006!a9\"\u0004H\u0014+[A\u001ad'\u000f\u001f@\u0005\u0016C5JT)U/jk\u0006mY\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t9A+\u001e9mKJ\u0012\u0004CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"a\u0006\u0012\n\u0005\rB\"a\u0002(pi\"Lgn\u001a\t\u0003/\u0015J!A\n\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001eQ\u0011)\u0011\u0006\u0001b\u0001A\t\t!\t\u0005\u0002\u001eW\u0011)A\u0006\u0001b\u0001A\t\t1\t\u0005\u0002\u001e]\u0011)q\u0006\u0001b\u0001A\t\tA\t\u0005\u0002\u001ec\u0011)!\u0007\u0001b\u0001A\t\tQ\t\u0005\u0002\u001ei\u0011)Q\u0007\u0001b\u0001A\t\ta\t\u0005\u0002\u001eo\u0011)\u0001\b\u0001b\u0001A\t\tq\t\u0005\u0002\u001eu\u0011)1\b\u0001b\u0001A\t\t\u0001\n\u0005\u0002\u001e{\u0011)a\b\u0001b\u0001A\t\t\u0011\n\u0005\u0002\u001e\u0001\u0012)\u0011\t\u0001b\u0001A\t\t!\n\u0005\u0002\u001e\u0007\u0012)A\t\u0001b\u0001A\t\t1\n\u0005\u0002\u001e\r\u0012)q\t\u0001b\u0001A\t\tA\n\u0005\u0002\u001e\u0013\u0012)!\n\u0001b\u0001A\t\tQ\n\u0005\u0002\u001e\u0019\u0012)Q\n\u0001b\u0001A\t\ta\n\u0005\u0002\u001e\u001f\u0012)\u0001\u000b\u0001b\u0001A\t\tq\n\u0005\u0002\u001e%\u0012)1\u000b\u0001b\u0001A\t\t\u0001\u000b\u0005\u0002\u001e+\u0012)a\u000b\u0001b\u0001A\t\t\u0011\u000b\u0005\u0002\u001e1\u0012)\u0011\f\u0001b\u0001A\t\t!\u000b\u0005\u0002\u001e7\u0012)A\f\u0001b\u0001A\t\t1\u000b\u0005\u0002\u001e=\u0012)q\f\u0001b\u0001A\t\tA\u000b\u0005\u0002\u001eC\u0012)!\r\u0001b\u0001A\t\tQ\u000b\u0005\u0002\u001eI\u0012)Q\r\u0001b\u0001A\t\ta\u000b\u0005\u0002hY6\t\u0001N\u0003\u0002jU\u0006\u0011\u0011n\u001c\u0006\u0002W\u0006!!.\u0019<b\u0013\ti\u0007N\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002aBA\u0002\u0003\u0001\u000f(U5\u00024GN\u001d=\u007f\t+\u0005j\u0013(R)^SV\fY2\u0002\u000b]\u0014\u0018\u000e^3\u0015\u000bM480!\u0001\u0011\u0005]!\u0018BA;\u0019\u0005\u0011)f.\u001b;\t\u000b]\u0014\u0001\u0019\u0001=\u0002\t-\u001cXM\u001d\t\u0003\u001feL!A_\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006y\n\u0001\r!`\u0001\u0004_V$\bCA\b\u007f\u0013\tyXC\u0001\u0004PkR\u0004X\u000f\u001e\u0005\u0007\u0003\u0007\u0011\u0001\u0019\u0001\f\u0002\u0007=\u0014'.\u0001\u0003sK\u0006$Gc\u0002\f\u0002\n\u0005-\u0011Q\u0003\u0005\u0006o\u000e\u0001\r\u0001\u001f\u0005\b\u0003\u001b\u0019\u0001\u0019AA\b\u0003\tIg\u000eE\u0002\u0010\u0003#I1!a\u0005\u0016\u0005\u0015Ie\u000e];u\u0011\u001d\t9b\u0001a\u0001\u00033\t1a\u00197t!\u0015\tY\"!\u000b\u0017\u001d\u0011\ti\"!\n\u0011\u0007\u0005}\u0001$\u0004\u0002\u0002\")\u0019\u00111E\u0006\u0002\rq\u0012xn\u001c;?\u0013\r\t9\u0003G\u0001\u0007!J,G-\u001a4\n\t\u0005-\u0012Q\u0006\u0002\u0006\u00072\f7o\u001d\u0006\u0004\u0003OA\u0002"
)
public class Tuple22Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple22 obj) {
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
      kser.writeClassAndObject(out, obj._20());
      out.flush();
      kser.writeClassAndObject(out, obj._21());
      out.flush();
      kser.writeClassAndObject(out, obj._22());
      out.flush();
   }

   public Tuple22 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple22(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple22Serializer() {
      this.setImmutable(true);
   }
}
