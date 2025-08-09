package com.twitter.chill;

import java.lang.reflect.Constructor;
import org.objenesis.instantiator.ObjectInstantiator;
import scala.Function0;
import scala.collection.IterableOnce;
import scala.reflect.ScalaSignature;
import scala.util.Try;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mq!\u0002\u0005\n\u0011\u0003\u0001b!\u0002\n\n\u0011\u0003\u0019\u0002\"\u0002\u000e\u0002\t\u0003Y\u0002\"\u0002\u000f\u0002\t\u0003i\u0002\"\u0002.\u0002\t\u0003Y\u0006\"B5\u0002\t\u0003Q\u0007\"\u0002:\u0002\t\u0003\u0019\bbBA\u0005\u0003\u0011\u0005\u00111B\u0001\u000e\u0013:\u001cH/\u00198uS\u0006$xN]:\u000b\u0005)Y\u0011!B2iS2d'B\u0001\u0007\u000e\u0003\u001d!x/\u001b;uKJT\u0011AD\u0001\u0004G>l7\u0001\u0001\t\u0003#\u0005i\u0011!\u0003\u0002\u000e\u0013:\u001cH/\u00198uS\u0006$xN]:\u0014\u0005\u0005!\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002!\u0005Ia.Z<Pe\u0016c7/Z\u000b\u0003=-\"Ba\b\u001bB+B\u0019\u0001eJ\u0015\u000e\u0003\u0005R!AI\u0012\u0002\u0019%t7\u000f^1oi&\fGo\u001c:\u000b\u0005\u0011*\u0013!C8cU\u0016tWm]5t\u0015\u00051\u0013aA8sO&\u0011\u0001&\t\u0002\u0013\u001f\nTWm\u0019;J]N$\u0018M\u001c;jCR|'\u000f\u0005\u0002+W1\u0001A!\u0002\u0017\u0004\u0005\u0004i#!\u0001+\u0012\u00059\n\u0004CA\u000b0\u0013\t\u0001dCA\u0004O_RD\u0017N\\4\u0011\u0005U\u0011\u0014BA\u001a\u0017\u0005\r\te.\u001f\u0005\u0006k\r\u0001\rAN\u0001\u0004G2\u001c\bcA\u001c?S9\u0011\u0001\b\u0010\t\u0003sYi\u0011A\u000f\u0006\u0003w=\ta\u0001\u0010:p_Rt\u0014BA\u001f\u0017\u0003\u0019\u0001&/\u001a3fM&\u0011q\b\u0011\u0002\u0006\u00072\f7o\u001d\u0006\u0003{YAQAQ\u0002A\u0002\r\u000b!!\u001b;\u0011\u0007\u0011KEJ\u0004\u0002F\u000f:\u0011\u0011HR\u0005\u0002/%\u0011\u0001JF\u0001\ba\u0006\u001c7.Y4f\u0013\tQ5JA\bUe\u00064XM]:bE2,wJ\\2f\u0015\tAe\u0003\u0005\u0003\u0016\u001bZz\u0015B\u0001(\u0017\u0005%1UO\\2uS>t\u0017\u0007E\u0002Q'~i\u0011!\u0015\u0006\u0003%Z\tA!\u001e;jY&\u0011A+\u0015\u0002\u0004)JL\bB\u0002,\u0004\t\u0003\u0007q+\u0001\u0004fYN,gM\u001c\t\u0004+a{\u0012BA-\u0017\u0005!a$-\u001f8b[\u0016t\u0014\u0001\u00034pe\u000ec\u0017m]:\u0016\u0005q\u0003GCA/g)\tq\u0016\rE\u0002!O}\u0003\"A\u000b1\u0005\u000b1\"!\u0019A\u0017\t\u000b\t$\u0001\u0019A2\u0002\u0005\u0019t\u0007cA\u000be?&\u0011QM\u0006\u0002\n\rVt7\r^5p]BBQa\u001a\u0003A\u0002!\f\u0011\u0001\u001e\t\u0004oyz\u0016A\u0003:fM2,7\r^!t[V\u00111n\u001c\u000b\u0003YB\u00042\u0001U*n!\r\u0001sE\u001c\t\u0003U=$Q\u0001L\u0003C\u00025BQaZ\u0003A\u0002E\u00042a\u000e o\u000399W\r^\"p]N$(/^2u_J,2\u0001^A\u0001)\r)\u00181\u0001\t\u0004mv|X\"A<\u000b\u0005aL\u0018a\u0002:fM2,7\r\u001e\u0006\u0003un\fA\u0001\\1oO*\tA0\u0001\u0003kCZ\f\u0017B\u0001@x\u0005-\u0019uN\\:ueV\u001cGo\u001c:\u0011\u0007)\n\t\u0001B\u0003-\r\t\u0007Q\u0006C\u0004\u0002\u0006\u0019\u0001\r!a\u0002\u0002\u0003\r\u00042a\u000e \u0000\u0003)qwN]7bY*\u000bg/Y\u000b\u0005\u0003\u001b\t)\u0002\u0006\u0003\u0002\u0010\u0005]\u0001\u0003\u0002)T\u0003#\u0001B\u0001I\u0014\u0002\u0014A\u0019!&!\u0006\u0005\u000b1:!\u0019A\u0017\t\r\u001d<\u0001\u0019AA\r!\u00119d(a\u0005"
)
public final class Instantiators {
   public static Try normalJava(final Class t) {
      return Instantiators$.MODULE$.normalJava(t);
   }

   public static Constructor getConstructor(final Class c) {
      return Instantiators$.MODULE$.getConstructor(c);
   }

   public static Try reflectAsm(final Class t) {
      return Instantiators$.MODULE$.reflectAsm(t);
   }

   public static ObjectInstantiator forClass(final Class t, final Function0 fn) {
      return Instantiators$.MODULE$.forClass(t, fn);
   }

   public static ObjectInstantiator newOrElse(final Class cls, final IterableOnce it, final Function0 elsefn) {
      return Instantiators$.MODULE$.newOrElse(cls, it, elsefn);
   }
}
