package org.json4s;

import scala.Option;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub\u0001B\u000e\u001d\u0001\u0006B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f\u0015!!\t\u0001\u0001;\u0011\u0015\u0019\u0005\u0001\"\u0001:\u0011\u001d!\u0005!!A\u0005\u0002\u0015Cqa\u0012\u0001\u0012\u0002\u0013\u0005\u0001\nC\u0004T\u0001\u0005\u0005I\u0011\t+\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAA\u0001\n\u0003\u0019\u0007bB5\u0001\u0003\u0003%\tE\u001b\u0005\bc\u0002\t\t\u0011\"\u0001s\u0011\u001d!\b!!A\u0005BUDqa\u001e\u0001\u0002\u0002\u0013\u0005\u0003\u0010C\u0004z\u0001\u0005\u0005I\u0011\t>\t\u000fm\u0004\u0011\u0011!C!y\u001e)a\u0010\bE\u0001\u007f\u001a11\u0004\bE\u0001\u0003\u0003AaA\u0010\n\u0005\u0002\u0005M\u0001bBA\u000b%\u0011\u0005\u0011q\u0003\u0005\n\u00037\u0011\"\u0019!C\u0001\u0003;Aq!a\b\u0013A\u0003%\u0001\tC\u0005\u0002\"I\u0011\r\u0011\"\u0001\u0002\u001e!9\u00111\u0005\n!\u0002\u0013\u0001\u0005\"CA\u0013%\u0005\u0005I\u0011QA\u0014\u0011%\t\u0019DEA\u0001\n\u0013\t)DA\u0003K\u0005>|GN\u0003\u0002\u001e=\u00051!n]8oiMT\u0011aH\u0001\u0004_J<7\u0001A\n\u0005\u0001\t2C\u0006\u0005\u0002$I5\tA$\u0003\u0002&9\t1!JV1mk\u0016\u0004\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002.k9\u0011af\r\b\u0003_Ij\u0011\u0001\r\u0006\u0003c\u0001\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005QB\u0013a\u00029bG.\fw-Z\u0005\u0003m]\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000e\u0015\u0002\u000bY\fG.^3\u0016\u0003i\u0002\"aJ\u001e\n\u0005qB#a\u0002\"p_2,\u0017M\\\u0001\u0007m\u0006dW/\u001a\u0011\u0002\rqJg.\u001b;?)\t\u0001\u0015\t\u0005\u0002$\u0001!)\u0001h\u0001a\u0001u\t1a+\u00197vKN\faA^1mk\u0016\u001c\u0018\u0001B2paf$\"\u0001\u0011$\t\u000fa2\u0001\u0013!a\u0001u\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A%+\u0005iR5&A&\u0011\u00051\u000bV\"A'\u000b\u00059{\u0015!C;oG\",7m[3e\u0015\t\u0001\u0006&\u0001\u0006b]:|G/\u0019;j_:L!AU'\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002+B\u0011akW\u0007\u0002/*\u0011\u0001,W\u0001\u0005Y\u0006twMC\u0001[\u0003\u0011Q\u0017M^1\n\u0005q;&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001`!\t9\u0003-\u0003\u0002bQ\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011Am\u001a\t\u0003O\u0015L!A\u001a\u0015\u0003\u0007\u0005s\u0017\u0010C\u0004i\u0015\u0005\u0005\t\u0019A0\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005Y\u0007c\u00017pI6\tQN\u0003\u0002oQ\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005Al'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"AO:\t\u000f!d\u0011\u0011!a\u0001I\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\t)f\u000fC\u0004i\u001b\u0005\u0005\t\u0019A0\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012aX\u0001\ti>\u001cFO]5oOR\tQ+\u0001\u0004fcV\fGn\u001d\u000b\u0003uuDq\u0001\u001b\t\u0002\u0002\u0003\u0007A-A\u0003K\u0005>|G\u000e\u0005\u0002$%M)!#a\u0001\u0002\nA\u0019q%!\u0002\n\u0007\u0005\u001d\u0001F\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003\u0017\t\t\"\u0004\u0002\u0002\u000e)\u0019\u0011qB-\u0002\u0005%|\u0017b\u0001\u001c\u0002\u000eQ\tq0A\u0003baBd\u0017\u0010F\u0002A\u00033AQ\u0001\u000f\u000bA\u0002i\nA\u0001\u0016:vKV\t\u0001)A\u0003UeV,\u0007%A\u0003GC2\u001cX-\u0001\u0004GC2\u001cX\rI\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\tI#a\f\u0011\t\u001d\nYCO\u0005\u0004\u0003[A#AB(qi&|g\u000e\u0003\u0005\u00022e\t\t\u00111\u0001A\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003o\u00012AVA\u001d\u0013\r\tYd\u0016\u0002\u0007\u001f\nTWm\u0019;"
)
public class JBool extends JValue {
   private final boolean value;

   public static Option unapply(final JBool x$0) {
      return JBool$.MODULE$.unapply(x$0);
   }

   public static JBool False() {
      return JBool$.MODULE$.False();
   }

   public static JBool True() {
      return JBool$.MODULE$.True();
   }

   public boolean value() {
      return this.value;
   }

   public boolean values() {
      return this.value();
   }

   public JBool copy(final boolean value) {
      return new JBool(value);
   }

   public boolean copy$default$1() {
      return this.value();
   }

   public String productPrefix() {
      return "JBool";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToBoolean(this.value());
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
      return x$1 instanceof JBool;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "value";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.value() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof JBool) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               JBool var4 = (JBool)x$1;
               if (this.value() == var4.value() && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public JBool(final boolean value) {
      this.value = value;
   }
}
