package org.json4s;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%c\u0001B\r\u001b\u0001~A\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\tw\u0001\u0011\t\u0012)A\u0005q!)A\b\u0001C\u0001{\u0015!\u0001\t\u0001\u0001B\u0011\u0015)\u0005\u0001\"\u0001G\u0011\u0015I\u0005\u0001\"\u0011K\u0011\u001d\u0001\u0006!!A\u0005\u0002ECqa\u0015\u0001\u0012\u0002\u0013\u0005A\u000bC\u0004`\u0001\u0005\u0005I\u0011\t1\t\u000f%\u0004\u0011\u0011!C\u0001U\"91\u000eAA\u0001\n\u0003a\u0007bB8\u0001\u0003\u0003%\t\u0005\u001d\u0005\bo\u0002\t\t\u0011\"\u0001y\u0011\u001di\b!!A\u0005ByD\u0011\"!\u0001\u0001\u0003\u0003%\t%a\u0001\t\u0013\u0005\u0015\u0001!!A\u0005B\u0005\u001d\u0001\"CA\u0005\u0001\u0005\u0005I\u0011IA\u0006\u000f%\tyAGA\u0001\u0012\u0003\t\tB\u0002\u0005\u001a5\u0005\u0005\t\u0012AA\n\u0011\u0019a4\u0003\"\u0001\u0002,!I\u0011QA\n\u0002\u0002\u0013\u0015\u0013q\u0001\u0005\t\u0013N\t\t\u0011\"!\u0002.!I\u0011\u0011G\n\u0002\u0002\u0013\u0005\u00151\u0007\u0005\n\u0003\u007f\u0019\u0012\u0011!C\u0005\u0003\u0003\u0012aAS!se\u0006L(BA\u000e\u001d\u0003\u0019Q7o\u001c85g*\tQ$A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001A\u0011R\u0003CA\u0011#\u001b\u0005Q\u0012BA\u0012\u001b\u0005\u0019Qe+\u00197vKB\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t9\u0001K]8ek\u000e$\bCA\u00164\u001d\ta\u0013G\u0004\u0002.a5\taF\u0003\u00020=\u00051AH]8pizJ\u0011aJ\u0005\u0003e\u0019\nq\u0001]1dW\u0006<W-\u0003\u00025k\ta1+\u001a:jC2L'0\u00192mK*\u0011!GJ\u0001\u0004CJ\u0014X#\u0001\u001d\u0011\u0007-J\u0004%\u0003\u0002;k\t!A*[:u\u0003\u0011\t'O\u001d\u0011\u0002\rqJg.\u001b;?)\tqt\b\u0005\u0002\"\u0001!)ag\u0001a\u0001q\t1a+\u00197vKN\u00042aK\u001dC!\t)3)\u0003\u0002EM\t\u0019\u0011I\\=\u0002\rY\fG.^3t+\u00059\u0005C\u0001%\u0005\u001b\u0005\u0001\u0011!B1qa2LHC\u0001\u0011L\u0011\u0015ae\u00011\u0001N\u0003\u0005I\u0007CA\u0013O\u0013\tyeEA\u0002J]R\fAaY8qsR\u0011aH\u0015\u0005\bm\u001d\u0001\n\u00111\u00019\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012!\u0016\u0016\u0003qY[\u0013a\u0016\t\u00031vk\u0011!\u0017\u0006\u00035n\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005q3\u0013AC1o]>$\u0018\r^5p]&\u0011a,\u0017\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001b!\t\u0011w-D\u0001d\u0015\t!W-\u0001\u0003mC:<'\"\u00014\u0002\t)\fg/Y\u0005\u0003Q\u000e\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A'\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!)\u001c\u0005\b].\t\t\u00111\u0001N\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u000fE\u0002sk\nk\u0011a\u001d\u0006\u0003i\u001a\n!bY8mY\u0016\u001cG/[8o\u0013\t18O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA=}!\t)#0\u0003\u0002|M\t9!i\\8mK\u0006t\u0007b\u00028\u000e\u0003\u0003\u0005\rAQ\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002b\u007f\"9aNDA\u0001\u0002\u0004i\u0015\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u00035\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002C\u00061Q-];bYN$2!_A\u0007\u0011\u001dq\u0017#!AA\u0002\t\u000baAS!se\u0006L\bCA\u0011\u0014'\u0015\u0019\u0012QCA\u0011!\u0019\t9\"!\b9}5\u0011\u0011\u0011\u0004\u0006\u0004\u000371\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003?\tIBA\tBEN$(/Y2u\rVt7\r^5p]F\u0002B!a\t\u0002*5\u0011\u0011Q\u0005\u0006\u0004\u0003O)\u0017AA5p\u0013\r!\u0014Q\u0005\u000b\u0003\u0003#!2APA\u0018\u0011\u00151d\u00031\u00019\u0003\u001d)h.\u00199qYf$B!!\u000e\u0002<A!Q%a\u000e9\u0013\r\tID\n\u0002\u0007\u001fB$\u0018n\u001c8\t\u0011\u0005ur#!AA\u0002y\n1\u0001\u001f\u00131\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0005E\u0002c\u0003\u000bJ1!a\u0012d\u0005\u0019y%M[3di\u0002"
)
public class JArray extends JValue {
   private final List arr;

   public static Option unapply(final JArray x$0) {
      return JArray$.MODULE$.unapply(x$0);
   }

   public static Function1 andThen(final Function1 g) {
      return JArray$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return JArray$.MODULE$.compose(g);
   }

   public List arr() {
      return this.arr;
   }

   public List values() {
      return this.arr().map((x$2) -> x$2.values());
   }

   public JValue apply(final int i) {
      return (JValue)this.arr().apply(i);
   }

   public JArray copy(final List arr) {
      return new JArray(arr);
   }

   public List copy$default$1() {
      return this.arr();
   }

   public String productPrefix() {
      return "JArray";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.arr();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JArray;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "arr";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof JArray) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     JArray var4 = (JArray)x$1;
                     List var10000 = this.arr();
                     List var5 = var4.arr();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   public JArray(final List arr) {
      this.arr = arr;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
