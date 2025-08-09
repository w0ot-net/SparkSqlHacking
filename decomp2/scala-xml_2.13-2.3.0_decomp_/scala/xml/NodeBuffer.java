package scala.xml;

import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Growable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00112Aa\u0001\u0003\u0001\u0013!)\u0011\u0004\u0001C\u00015!)A\u0004\u0001C\u0001;\tQaj\u001c3f\u0005V4g-\u001a:\u000b\u0005\u00151\u0011a\u0001=nY*\tq!A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001Qa\u0003E\u0002\f!Ii\u0011\u0001\u0004\u0006\u0003\u001b9\tq!\\;uC\ndWM\u0003\u0002\u0010\r\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Ea!aC!se\u0006L()\u001e4gKJ\u0004\"a\u0005\u000b\u000e\u0003\u0011I!!\u0006\u0003\u0003\t9{G-\u001a\t\u0003']I!\u0001\u0007\u0003\u0003=M\u001b\u0017\r\\1WKJ\u001c\u0018n\u001c8Ta\u0016\u001c\u0017NZ5d\u001d>$WMQ;gM\u0016\u0014\u0018A\u0002\u001fj]&$h\bF\u0001\u001c!\t\u0019\u0002!A\u0005%C6\u0004H\u0005\u001d7vgR\u00111D\b\u0005\u0006?\t\u0001\r\u0001I\u0001\u0002_B\u0011\u0011EI\u0007\u0002\r%\u00111E\u0002\u0002\u0004\u0003:L\b"
)
public class NodeBuffer extends ArrayBuffer implements ScalaVersionSpecificNodeBuffer {
   public String className() {
      return ScalaVersionSpecificNodeBuffer.className$(this);
   }

   public NodeBuffer $amp$plus(final Object o) {
      boolean var10000;
      if (o == null) {
         var10000 = true;
      } else if (o instanceof BoxedUnit) {
         var10000 = true;
      } else {
         label45: {
            if (o != null) {
               Option var5 = Text$.MODULE$.unapply(o);
               if (!var5.isEmpty()) {
                  String var6 = (String)var5.get();
                  if ("".equals(var6)) {
                     var10000 = true;
                     break label45;
                  }
               }
            }

            var10000 = false;
         }
      }

      if (var10000) {
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (o instanceof Iterator) {
         Iterator var7 = (Iterator)o;
         var7.foreach((ox) -> this.$amp$plus(ox));
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (o instanceof Node) {
         Node var8 = (Node)o;
         Growable.$plus$eq$(this, var8);
      } else if (o instanceof Iterable) {
         Iterable var9 = (Iterable)o;
         this.$amp$plus(var9.iterator());
      } else if (.MODULE$.isArray(o, 1)) {
         this.$amp$plus(scala.collection.ArrayOps..MODULE$.iterator$extension(scala.Predef..MODULE$.genericArrayOps(o)));
      } else {
         Growable.$plus$eq$(this, new Atom(o));
      }

      return this;
   }

   public NodeBuffer() {
      ScalaVersionSpecificNodeBuffer.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
