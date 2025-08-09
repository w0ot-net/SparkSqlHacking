package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple20;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb\u0001\u0002\u0003\u0006\u00011AQ\u0001\u001b\u0001\u0005\u0002%DQa\u001b\u0001\u0005\u00021DQ\u0001 \u0001\u0005\u0002u\u0014\u0011\u0003V;qY\u0016\u0014\u0004gU3sS\u0006d\u0017N_3s\u0015\t1q!A\u0003dQ&dGN\u0003\u0002\t\u0013\u00059Ao^5ui\u0016\u0014(\"\u0001\u0006\u0002\u0007\r|Wn\u0001\u0001\u0016+5q\u0002f\u000b\u00182i]RT\bQ\"G\u00132{%+\u0016-\\=N\u0019\u0001A\u00041\u0011\u0007=\u0019bC\u0004\u0002\u0011#5\tQ!\u0003\u0002\u0013\u000b\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u000b\u0016\u0005-Y5+\u001a:jC2L'0\u001a:\u000b\u0005I)\u0001CF\f\u001b9\u001dRS\u0006M\u001a7sqz$)\u0012%L\u001dF#vKW/\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011q\u0001V;qY\u0016\u0014\u0004\u0007\u0005\u0002\u001e=1\u0001A!B\u0010\u0001\u0005\u0004\u0001#!A!\u0012\u0005\u0005\"\u0003CA\f#\u0013\t\u0019\u0003DA\u0004O_RD\u0017N\\4\u0011\u0005])\u0013B\u0001\u0014\u0019\u0005\r\te.\u001f\t\u0003;!\"Q!\u000b\u0001C\u0002\u0001\u0012\u0011A\u0011\t\u0003;-\"Q\u0001\f\u0001C\u0002\u0001\u0012\u0011a\u0011\t\u0003;9\"Qa\f\u0001C\u0002\u0001\u0012\u0011\u0001\u0012\t\u0003;E\"QA\r\u0001C\u0002\u0001\u0012\u0011!\u0012\t\u0003;Q\"Q!\u000e\u0001C\u0002\u0001\u0012\u0011A\u0012\t\u0003;]\"Q\u0001\u000f\u0001C\u0002\u0001\u0012\u0011a\u0012\t\u0003;i\"Qa\u000f\u0001C\u0002\u0001\u0012\u0011\u0001\u0013\t\u0003;u\"QA\u0010\u0001C\u0002\u0001\u0012\u0011!\u0013\t\u0003;\u0001#Q!\u0011\u0001C\u0002\u0001\u0012\u0011A\u0013\t\u0003;\r#Q\u0001\u0012\u0001C\u0002\u0001\u0012\u0011a\u0013\t\u0003;\u0019#Qa\u0012\u0001C\u0002\u0001\u0012\u0011\u0001\u0014\t\u0003;%#QA\u0013\u0001C\u0002\u0001\u0012\u0011!\u0014\t\u0003;1#Q!\u0014\u0001C\u0002\u0001\u0012\u0011A\u0014\t\u0003;=#Q\u0001\u0015\u0001C\u0002\u0001\u0012\u0011a\u0014\t\u0003;I#Qa\u0015\u0001C\u0002\u0001\u0012\u0011\u0001\u0015\t\u0003;U#QA\u0016\u0001C\u0002\u0001\u0012\u0011!\u0015\t\u0003;a#Q!\u0017\u0001C\u0002\u0001\u0012\u0011A\u0015\t\u0003;m#Q\u0001\u0018\u0001C\u0002\u0001\u0012\u0011a\u0015\t\u0003;y#Qa\u0018\u0001C\u0002\u0001\u0012\u0011\u0001\u0016\t\u0003C\u001al\u0011A\u0019\u0006\u0003G\u0012\f!![8\u000b\u0003\u0015\fAA[1wC&\u0011qM\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003)\u0004b\u0003\u0005\u0001\u001dO)j\u0003g\r\u001c:y}\u0012U\tS&O#R;&,X\u0001\u0006oJLG/\u001a\u000b\u0005[B,(\u0010\u0005\u0002\u0018]&\u0011q\u000e\u0007\u0002\u0005+:LG\u000fC\u0003r\u0005\u0001\u0007!/\u0001\u0003lg\u0016\u0014\bCA\bt\u0013\t!XC\u0001\u0003Lef|\u0007\"\u0002<\u0003\u0001\u00049\u0018aA8viB\u0011q\u0002_\u0005\u0003sV\u0011aaT;uaV$\b\"B>\u0003\u0001\u00041\u0012aA8cU\u0006!!/Z1e)\u00151bp`A\u0005\u0011\u0015\t8\u00011\u0001s\u0011\u001d\t\ta\u0001a\u0001\u0003\u0007\t!!\u001b8\u0011\u0007=\t)!C\u0002\u0002\bU\u0011Q!\u00138qkRDq!a\u0003\u0004\u0001\u0004\ti!A\u0002dYN\u0004R!a\u0004\u0002\u001eYqA!!\u0005\u0002\u001aA\u0019\u00111\u0003\r\u000e\u0005\u0005U!bAA\f\u0017\u00051AH]8pizJ1!a\u0007\u0019\u0003\u0019\u0001&/\u001a3fM&!\u0011qDA\u0011\u0005\u0015\u0019E.Y:t\u0015\r\tY\u0002\u0007"
)
public class Tuple20Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple20 obj) {
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
   }

   public Tuple20 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple20(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple20Serializer() {
      this.setImmutable(true);
   }
}
