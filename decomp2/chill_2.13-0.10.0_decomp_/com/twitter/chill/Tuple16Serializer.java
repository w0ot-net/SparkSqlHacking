package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple16;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-a\u0001\u0002\u0003\u0006\u00011AQ\u0001\u0018\u0001\u0005\u0002uCQa\u0018\u0001\u0005\u0002\u0001DQ\u0001\u001d\u0001\u0005\u0002E\u0014\u0011\u0003V;qY\u0016\fdgU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016#5q\u0002f\u000b\u00182i]RT\bQ\"G\u00132{%kE\u0002\u0001\u001dQ\u00032aD\n\u0017\u001d\t\u0001\u0012#D\u0001\u0006\u0013\t\u0011R!A\u0004qC\u000e\\\u0017mZ3\n\u0005Q)\"aC&TKJL\u0017\r\\5{KJT!AE\u0003\u0011%]QBd\n\u0016.aM2\u0014\bP C\u000b\"[e*U\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t9A+\u001e9mKF2\u0004CA\u000f\u001f\u0019\u0001!Qa\b\u0001C\u0002\u0001\u0012\u0011!Q\t\u0003C\u0011\u0002\"a\u0006\u0012\n\u0005\rB\"a\u0002(pi\"Lgn\u001a\t\u0003/\u0015J!A\n\r\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001eQ\u0011)\u0011\u0006\u0001b\u0001A\t\t!\t\u0005\u0002\u001eW\u0011)A\u0006\u0001b\u0001A\t\t1\t\u0005\u0002\u001e]\u0011)q\u0006\u0001b\u0001A\t\tA\t\u0005\u0002\u001ec\u0011)!\u0007\u0001b\u0001A\t\tQ\t\u0005\u0002\u001ei\u0011)Q\u0007\u0001b\u0001A\t\ta\t\u0005\u0002\u001eo\u0011)\u0001\b\u0001b\u0001A\t\tq\t\u0005\u0002\u001eu\u0011)1\b\u0001b\u0001A\t\t\u0001\n\u0005\u0002\u001e{\u0011)a\b\u0001b\u0001A\t\t\u0011\n\u0005\u0002\u001e\u0001\u0012)\u0011\t\u0001b\u0001A\t\t!\n\u0005\u0002\u001e\u0007\u0012)A\t\u0001b\u0001A\t\t1\n\u0005\u0002\u001e\r\u0012)q\t\u0001b\u0001A\t\tA\n\u0005\u0002\u001e\u0013\u0012)!\n\u0001b\u0001A\t\tQ\n\u0005\u0002\u001e\u0019\u0012)Q\n\u0001b\u0001A\t\ta\n\u0005\u0002\u001e\u001f\u0012)\u0001\u000b\u0001b\u0001A\t\tq\n\u0005\u0002\u001e%\u0012)1\u000b\u0001b\u0001A\t\t\u0001\u000b\u0005\u0002V56\taK\u0003\u0002X1\u0006\u0011\u0011n\u001c\u0006\u00023\u0006!!.\u0019<b\u0013\tYfK\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002=B\u0011\u0002\u0003\u0001\u000f(U5\u00024GN\u001d=\u007f\t+\u0005j\u0013(R\u0003\u00159(/\u001b;f)\u0011\tG-\u001b8\u0011\u0005]\u0011\u0017BA2\u0019\u0005\u0011)f.\u001b;\t\u000b\u0015\u0014\u0001\u0019\u00014\u0002\t-\u001cXM\u001d\t\u0003\u001f\u001dL!\u0001[\u000b\u0003\t-\u0013\u0018p\u001c\u0005\u0006U\n\u0001\ra[\u0001\u0004_V$\bCA\bm\u0013\tiWC\u0001\u0004PkR\u0004X\u000f\u001e\u0005\u0006_\n\u0001\rAF\u0001\u0004_\nT\u0017\u0001\u0002:fC\u0012$BA\u0006:tq\")Qm\u0001a\u0001M\")Ao\u0001a\u0001k\u0006\u0011\u0011N\u001c\t\u0003\u001fYL!a^\u000b\u0003\u000b%s\u0007/\u001e;\t\u000be\u001c\u0001\u0019\u0001>\u0002\u0007\rd7\u000f\u0005\u0003|\u0003\u000b1bb\u0001?\u0002\u0002A\u0011Q\u0010G\u0007\u0002}*\u0011qpC\u0001\u0007yI|w\u000e\u001e \n\u0007\u0005\r\u0001$\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003\u000f\tIAA\u0003DY\u0006\u001c8OC\u0002\u0002\u0004a\u0001"
)
public class Tuple16Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple16 obj) {
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
   }

   public Tuple16 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple16(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple16Serializer() {
      this.setImmutable(true);
   }
}
