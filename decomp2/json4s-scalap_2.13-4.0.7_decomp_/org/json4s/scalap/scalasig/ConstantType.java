package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001\u0002\f\u0018\u0001\u0002B\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!)Q\b\u0001C\u0001}!9\u0011\tAA\u0001\n\u0003\u0011\u0005b\u0002#\u0001#\u0003%\t!\u0012\u0005\b!\u0002\t\t\u0011\"\u0011R\u0011\u001dQ\u0006!!A\u0005\u0002mCqa\u0018\u0001\u0002\u0002\u0013\u0005\u0001\rC\u0004d\u0001\u0005\u0005I\u0011\t3\t\u000f-\u0004\u0011\u0011!C\u0001Y\"9\u0011\u000fAA\u0001\n\u0003\u0012\bb\u0002;\u0001\u0003\u0003%\t%\u001e\u0005\bm\u0002\t\t\u0011\"\u0011x\u0011\u001dA\b!!A\u0005Be<qa_\f\u0002\u0002#\u0005APB\u0004\u0017/\u0005\u0005\t\u0012A?\t\ru\u0002B\u0011AA\n\u0011\u001d1\b#!A\u0005F]D\u0011\"!\u0006\u0011\u0003\u0003%\t)a\u0006\t\u0013\u0005m\u0001#!A\u0005\u0002\u0006u\u0001\"CA\u0015!\u0005\u0005I\u0011BA\u0016\u00051\u0019uN\\:uC:$H+\u001f9f\u0015\tA\u0012$\u0001\u0005tG\u0006d\u0017m]5h\u0015\tQ2$\u0001\u0004tG\u0006d\u0017\r\u001d\u0006\u00039u\taA[:p]R\u001a(\"\u0001\u0010\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001\tSe\u000b\t\u0003E\rj\u0011aF\u0005\u0003I]\u0011A\u0001V=qKB\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t9\u0001K]8ek\u000e$\bC\u0001\u00175\u001d\ti#G\u0004\u0002/c5\tqF\u0003\u00021?\u00051AH]8pizJ\u0011\u0001K\u0005\u0003g\u001d\nq\u0001]1dW\u0006<W-\u0003\u00026m\ta1+\u001a:jC2L'0\u00192mK*\u00111gJ\u0001\tG>t7\u000f^1oiV\t\u0011\b\u0005\u0002'u%\u00111h\n\u0002\u0004\u0003:L\u0018!C2p]N$\u0018M\u001c;!\u0003\u0019a\u0014N\\5u}Q\u0011q\b\u0011\t\u0003E\u0001AQaN\u0002A\u0002e\nAaY8qsR\u0011qh\u0011\u0005\bo\u0011\u0001\n\u00111\u0001:\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u0012\u0016\u0003s\u001d[\u0013\u0001\u0013\t\u0003\u0013:k\u0011A\u0013\u0006\u0003\u00172\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u00055;\u0013AC1o]>$\u0018\r^5p]&\u0011qJ\u0013\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001S!\t\u0019\u0006,D\u0001U\u0015\t)f+\u0001\u0003mC:<'\"A,\u0002\t)\fg/Y\u0005\u00033R\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#\u0001/\u0011\u0005\u0019j\u0016B\u00010(\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\tI\u0014\rC\u0004c\u0011\u0005\u0005\t\u0019\u0001/\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005)\u0007c\u00014js5\tqM\u0003\u0002iO\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005)<'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"!\u001c9\u0011\u0005\u0019r\u0017BA8(\u0005\u001d\u0011un\u001c7fC:DqA\u0019\u0006\u0002\u0002\u0003\u0007\u0011(\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GC\u0001*t\u0011\u001d\u00117\"!AA\u0002q\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u00029\u0006AAo\\*ue&tw\rF\u0001S\u0003\u0019)\u0017/^1mgR\u0011QN\u001f\u0005\bE:\t\t\u00111\u0001:\u00031\u0019uN\\:uC:$H+\u001f9f!\t\u0011\u0003c\u0005\u0003\u0011}\u0006%\u0001#B@\u0002\u0006ezTBAA\u0001\u0015\r\t\u0019aJ\u0001\beVtG/[7f\u0013\u0011\t9!!\u0001\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\f\u0005EQBAA\u0007\u0015\r\tyAV\u0001\u0003S>L1!NA\u0007)\u0005a\u0018!B1qa2LHcA \u0002\u001a!)qg\u0005a\u0001s\u00059QO\\1qa2LH\u0003BA\u0010\u0003K\u0001BAJA\u0011s%\u0019\u00111E\u0014\u0003\r=\u0003H/[8o\u0011!\t9\u0003FA\u0001\u0002\u0004y\u0014a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0006\t\u0004'\u0006=\u0012bAA\u0019)\n1qJ\u00196fGR\u0004"
)
public class ConstantType extends Type implements Product, Serializable {
   private final Object constant;

   public static Option unapply(final ConstantType x$0) {
      return ConstantType$.MODULE$.unapply(x$0);
   }

   public static ConstantType apply(final Object constant) {
      return ConstantType$.MODULE$.apply(constant);
   }

   public static Function1 andThen(final Function1 g) {
      return ConstantType$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ConstantType$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object constant() {
      return this.constant;
   }

   public ConstantType copy(final Object constant) {
      return new ConstantType(constant);
   }

   public Object copy$default$1() {
      return this.constant();
   }

   public String productPrefix() {
      return "ConstantType";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.constant();
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
      return x$1 instanceof ConstantType;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "constant";
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
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof ConstantType) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               ConstantType var4 = (ConstantType)x$1;
               if (BoxesRunTime.equals(this.constant(), var4.constant()) && var4.canEqual(this)) {
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

   public ConstantType(final Object constant) {
      this.constant = constant;
      Product.$init$(this);
   }
}
