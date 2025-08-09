package org.apache.spark.streaming.receiver;

import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011#Q\u0001\nyBQa\u0011\u0001\u0005\u0002\u0011Cqa\u0012\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004K\u0001E\u0005I\u0011A&\t\u000fY\u0003\u0011\u0011!C!/\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0015\u0005%q#!A\t\u0002e\tYAB\u0005\u0017/\u0005\u0005\t\u0012A\r\u0002\u000e!11\t\u0005C\u0001\u0003KA\u0001b \t\u0002\u0002\u0013\u0015\u0013\u0011\u0001\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005m\u0002#!A\u0005\n\u0005u\"\u0001E\"mK\u0006tW\u000f](mI\ncwnY6t\u0015\tA\u0012$\u0001\u0005sK\u000e,\u0017N^3s\u0015\tQ2$A\u0005tiJ,\u0017-\\5oO*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xmE\u0003\u0001E!bs\u0006\u0005\u0002$M5\tAEC\u0001&\u0003\u0015\u00198-\u00197b\u0013\t9CE\u0001\u0004B]f\u0014VM\u001a\t\u0003S)j\u0011aF\u0005\u0003W]\u0011qBU3dK&4XM]'fgN\fw-\u001a\t\u0003G5J!A\f\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001'\u000f\b\u0003c]r!A\r\u001c\u000e\u0003MR!\u0001N\u001b\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!J\u0005\u0003q\u0011\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001\bJ\u0001\u000bi\"\u0014Xm\u001d5US6,W#\u0001 \u0011\u0005}\u0002U\"A\r\n\u0005\u0005K\"\u0001\u0002+j[\u0016\f1\u0002\u001e5sKNDG+[7fA\u00051A(\u001b8jiz\"\"!\u0012$\u0011\u0005%\u0002\u0001\"\u0002\u001f\u0004\u0001\u0004q\u0014\u0001B2paf$\"!R%\t\u000fq\"\u0001\u0013!a\u0001}\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#\u0001'+\u0005yj5&\u0001(\u0011\u0005=#V\"\u0001)\u000b\u0005E\u0013\u0016!C;oG\",7m[3e\u0015\t\u0019F%\u0001\u0006b]:|G/\u0019;j_:L!!\u0016)\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u00021B\u0011\u0011LX\u0007\u00025*\u00111\fX\u0001\u0005Y\u0006twMC\u0001^\u0003\u0011Q\u0017M^1\n\u0005}S&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001c!\t\u00193-\u0003\u0002eI\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011qM\u001b\t\u0003G!L!!\u001b\u0013\u0003\u0007\u0005s\u0017\u0010C\u0004l\u0011\u0005\u0005\t\u0019\u00012\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005q\u0007cA8sO6\t\u0001O\u0003\u0002rI\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0005M\u0004(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"A^=\u0011\u0005\r:\u0018B\u0001=%\u0005\u001d\u0011un\u001c7fC:Dqa\u001b\u0006\u0002\u0002\u0003\u0007q-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GC\u0001-}\u0011\u001dY7\"!AA\u0002\t\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002E\u0006AAo\\*ue&tw\rF\u0001Y\u0003\u0019)\u0017/^1mgR\u0019a/a\u0002\t\u000f-t\u0011\u0011!a\u0001O\u0006\u00012\t\\3b]V\u0004x\n\u001c3CY>\u001c7n\u001d\t\u0003SA\u0019R\u0001EA\b\u00037\u0001b!!\u0005\u0002\u0018y*UBAA\n\u0015\r\t)\u0002J\u0001\beVtG/[7f\u0013\u0011\tI\"a\u0005\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u001e\u0005\rRBAA\u0010\u0015\r\t\t\u0003X\u0001\u0003S>L1AOA\u0010)\t\tY!A\u0003baBd\u0017\u0010F\u0002F\u0003WAQ\u0001P\nA\u0002y\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u00022\u0005]\u0002\u0003B\u0012\u00024yJ1!!\u000e%\u0005\u0019y\u0005\u000f^5p]\"A\u0011\u0011\b\u000b\u0002\u0002\u0003\u0007Q)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\u0010\u0011\u0007e\u000b\t%C\u0002\u0002Di\u0013aa\u00142kK\u000e$\b"
)
public class CleanupOldBlocks implements ReceiverMessage, Product {
   private final Time threshTime;

   public static Option unapply(final CleanupOldBlocks x$0) {
      return CleanupOldBlocks$.MODULE$.unapply(x$0);
   }

   public static CleanupOldBlocks apply(final Time threshTime) {
      return CleanupOldBlocks$.MODULE$.apply(threshTime);
   }

   public static Function1 andThen(final Function1 g) {
      return CleanupOldBlocks$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return CleanupOldBlocks$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time threshTime() {
      return this.threshTime;
   }

   public CleanupOldBlocks copy(final Time threshTime) {
      return new CleanupOldBlocks(threshTime);
   }

   public Time copy$default$1() {
      return this.threshTime();
   }

   public String productPrefix() {
      return "CleanupOldBlocks";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.threshTime();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof CleanupOldBlocks;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "threshTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof CleanupOldBlocks) {
               label40: {
                  CleanupOldBlocks var4 = (CleanupOldBlocks)x$1;
                  Time var10000 = this.threshTime();
                  Time var5 = var4.threshTime();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label40;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label40;
                  }

                  if (var4.canEqual(this)) {
                     break label47;
                  }
               }
            }

            var6 = false;
            return var6;
         }
      }

      var6 = true;
      return var6;
   }

   public CleanupOldBlocks(final Time threshTime) {
      this.threshTime = threshTime;
      Product.$init$(this);
   }
}
