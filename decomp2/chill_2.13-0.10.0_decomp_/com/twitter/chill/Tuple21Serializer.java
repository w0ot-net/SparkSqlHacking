package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.Serializable;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%b\u0001\u0002\u0003\u0006\u00011AQa\u001b\u0001\u0005\u00021DQA\u001c\u0001\u0005\u0002=Daa \u0001\u0005\u0002\u0005\u0005!!\u0005+va2,''M*fe&\fG.\u001b>fe*\u0011aaB\u0001\u0006G\"LG\u000e\u001c\u0006\u0003\u0011%\tq\u0001^<jiR,'OC\u0001\u000b\u0003\r\u0019w.\\\u0002\u0001+Yia\u0004K\u0016/cQ:$(\u0010!D\r&cuJU+Y7z\u000b7c\u0001\u0001\u000fGB\u0019qb\u0005\f\u000f\u0005A\tR\"A\u0003\n\u0005I)\u0011a\u00029bG.\fw-Z\u0005\u0003)U\u00111bS*fe&\fG.\u001b>fe*\u0011!#\u0002\t\u0018/iarEK\u00171gYJDh\u0010\"F\u0011.s\u0015\u000bV,[;\u0002l\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\b)V\u0004H.\u001a\u001a2!\tib\u0004\u0004\u0001\u0005\u000b}\u0001!\u0019\u0001\u0011\u0003\u0003\u0005\u000b\"!\t\u0013\u0011\u0005]\u0011\u0013BA\u0012\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u0013\n\u0005\u0019B\"aA!osB\u0011Q\u0004\u000b\u0003\u0006S\u0001\u0011\r\u0001\t\u0002\u0002\u0005B\u0011Qd\u000b\u0003\u0006Y\u0001\u0011\r\u0001\t\u0002\u0002\u0007B\u0011QD\f\u0003\u0006_\u0001\u0011\r\u0001\t\u0002\u0002\tB\u0011Q$\r\u0003\u0006e\u0001\u0011\r\u0001\t\u0002\u0002\u000bB\u0011Q\u0004\u000e\u0003\u0006k\u0001\u0011\r\u0001\t\u0002\u0002\rB\u0011Qd\u000e\u0003\u0006q\u0001\u0011\r\u0001\t\u0002\u0002\u000fB\u0011QD\u000f\u0003\u0006w\u0001\u0011\r\u0001\t\u0002\u0002\u0011B\u0011Q$\u0010\u0003\u0006}\u0001\u0011\r\u0001\t\u0002\u0002\u0013B\u0011Q\u0004\u0011\u0003\u0006\u0003\u0002\u0011\r\u0001\t\u0002\u0002\u0015B\u0011Qd\u0011\u0003\u0006\t\u0002\u0011\r\u0001\t\u0002\u0002\u0017B\u0011QD\u0012\u0003\u0006\u000f\u0002\u0011\r\u0001\t\u0002\u0002\u0019B\u0011Q$\u0013\u0003\u0006\u0015\u0002\u0011\r\u0001\t\u0002\u0002\u001bB\u0011Q\u0004\u0014\u0003\u0006\u001b\u0002\u0011\r\u0001\t\u0002\u0002\u001dB\u0011Qd\u0014\u0003\u0006!\u0002\u0011\r\u0001\t\u0002\u0002\u001fB\u0011QD\u0015\u0003\u0006'\u0002\u0011\r\u0001\t\u0002\u0002!B\u0011Q$\u0016\u0003\u0006-\u0002\u0011\r\u0001\t\u0002\u0002#B\u0011Q\u0004\u0017\u0003\u00063\u0002\u0011\r\u0001\t\u0002\u0002%B\u0011Qd\u0017\u0003\u00069\u0002\u0011\r\u0001\t\u0002\u0002'B\u0011QD\u0018\u0003\u0006?\u0002\u0011\r\u0001\t\u0002\u0002)B\u0011Q$\u0019\u0003\u0006E\u0002\u0011\r\u0001\t\u0002\u0002+B\u0011A-[\u0007\u0002K*\u0011amZ\u0001\u0003S>T\u0011\u0001[\u0001\u0005U\u00064\u0018-\u0003\u0002kK\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!\u001c\t\u0018!\u0001arEK\u00171gYJDh\u0010\"F\u0011.s\u0015\u000bV,[;\u0002\fQa\u001e:ji\u0016$B\u0001]:y{B\u0011q#]\u0005\u0003eb\u0011A!\u00168ji\")AO\u0001a\u0001k\u0006!1n]3s!\tya/\u0003\u0002x+\t!1J]=p\u0011\u0015I(\u00011\u0001{\u0003\ryW\u000f\u001e\t\u0003\u001fmL!\u0001`\u000b\u0003\r=+H\u000f];u\u0011\u0015q(\u00011\u0001\u0017\u0003\ry'M[\u0001\u0005e\u0016\fG\rF\u0004\u0017\u0003\u0007\t)!a\u0004\t\u000bQ\u001c\u0001\u0019A;\t\u000f\u0005\u001d1\u00011\u0001\u0002\n\u0005\u0011\u0011N\u001c\t\u0004\u001f\u0005-\u0011bAA\u0007+\t)\u0011J\u001c9vi\"9\u0011\u0011C\u0002A\u0002\u0005M\u0011aA2mgB)\u0011QCA\u0012-9!\u0011qCA\u0010!\r\tI\u0002G\u0007\u0003\u00037Q1!!\b\f\u0003\u0019a$o\\8u}%\u0019\u0011\u0011\u0005\r\u0002\rA\u0013X\rZ3g\u0013\u0011\t)#a\n\u0003\u000b\rc\u0017m]:\u000b\u0007\u0005\u0005\u0002\u0004"
)
public class Tuple21Serializer extends Serializer implements Serializable {
   public void write(final Kryo kser, final Output out, final Tuple21 obj) {
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
   }

   public Tuple21 read(final Kryo kser, final Input in, final Class cls) {
      return new Tuple21(kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in), kser.readClassAndObject(in));
   }

   public Tuple21Serializer() {
      this.setImmutable(true);
   }
}
