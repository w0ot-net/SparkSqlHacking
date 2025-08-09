package org.apache.spark.resource;

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
   bytes = "\u0006\u0005\u0005]d!\u0002\u0010 \u0001\u0006:\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011!\u0003!\u0011#Q\u0001\n\u0001C\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u001d\u0002\u0011\t\u0012)A\u0005\u0017\"Aq\n\u0001BK\u0002\u0013\u0005!\n\u0003\u0005Q\u0001\tE\t\u0015!\u0003L\u0011\u0015\t\u0006\u0001\"\u0001S\u0011\u001dA\u0006!!A\u0005\u0002eCq!\u0018\u0001\u0012\u0002\u0013\u0005a\fC\u0004j\u0001E\u0005I\u0011\u00016\t\u000f1\u0004\u0011\u0013!C\u0001U\"9Q\u000eAA\u0001\n\u0003r\u0007b\u0002<\u0001\u0003\u0003%\tA\u0013\u0005\bo\u0002\t\t\u0011\"\u0001y\u0011\u001dq\b!!A\u0005B}D\u0011\"!\u0004\u0001\u0003\u0003%\t!a\u0004\t\u0013\u0005e\u0001!!A\u0005B\u0005m\u0001\"CA\u0010\u0001\u0005\u0005I\u0011IA\u0011\u0011%\t\u0019\u0003AA\u0001\n\u0003\n)\u0003C\u0005\u0002(\u0001\t\t\u0011\"\u0011\u0002*\u001dQ\u0011QF\u0010\u0002\u0002#\u0005\u0011%a\f\u0007\u0013yy\u0012\u0011!E\u0001C\u0005E\u0002BB)\u0017\t\u0003\tI\u0005C\u0005\u0002$Y\t\t\u0011\"\u0012\u0002&!I\u00111\n\f\u0002\u0002\u0013\u0005\u0015Q\n\u0005\t\u0003+2\u0012\u0013!C\u0001U\"I\u0011q\u000b\f\u0002\u0002\u0013\u0005\u0015\u0011\f\u0005\t\u0003W2\u0012\u0013!C\u0001U\"I\u0011Q\u000e\f\u0002\u0002\u0013%\u0011q\u000e\u0002\u0014%\u0016\u001cx.\u001e:dKJ+\u0017/^5sK6,g\u000e\u001e\u0006\u0003A\u0005\n\u0001B]3t_V\u00148-\u001a\u0006\u0003E\r\nQa\u001d9be.T!\u0001J\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00051\u0013aA8sON!\u0001\u0001\u000b\u00182!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u0019\te.\u001f*fMB\u0011\u0011fL\u0005\u0003a)\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023w9\u00111'\u000f\b\u0003iaj\u0011!\u000e\u0006\u0003m]\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002W%\u0011!HK\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002;U\u0005a!/Z:pkJ\u001cWMT1nKV\t\u0001\t\u0005\u0002B\u000b:\u0011!i\u0011\t\u0003i)J!\u0001\u0012\u0016\u0002\rA\u0013X\rZ3g\u0013\t1uI\u0001\u0004TiJLgn\u001a\u0006\u0003\t*\nQB]3t_V\u00148-\u001a(b[\u0016\u0004\u0013AB1n_VtG/F\u0001L!\tIC*\u0003\u0002NU\t\u0019\u0011J\u001c;\u0002\u000f\u0005lw.\u001e8uA\u0005Aa.^7QCJ$8/A\u0005ok6\u0004\u0016M\u001d;tA\u00051A(\u001b8jiz\"BaU+W/B\u0011A\u000bA\u0007\u0002?!)ah\u0002a\u0001\u0001\")\u0011j\u0002a\u0001\u0017\"9qj\u0002I\u0001\u0002\u0004Y\u0015\u0001B2paf$Ba\u0015.\\9\"9a\b\u0003I\u0001\u0002\u0004\u0001\u0005bB%\t!\u0003\u0005\ra\u0013\u0005\b\u001f\"\u0001\n\u00111\u0001L\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0018\u0016\u0003\u0001\u0002\\\u0013!\u0019\t\u0003E\u001el\u0011a\u0019\u0006\u0003I\u0016\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005\u0019T\u0013AC1o]>$\u0018\r^5p]&\u0011\u0001n\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0002W*\u00121\nY\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tq\u000e\u0005\u0002qk6\t\u0011O\u0003\u0002sg\u0006!A.\u00198h\u0015\u0005!\u0018\u0001\u00026bm\u0006L!AR9\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011\u0011\u0010 \t\u0003SiL!a\u001f\u0016\u0003\u0007\u0005s\u0017\u0010C\u0004~\u001d\u0005\u0005\t\u0019A&\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\t\u0001E\u0003\u0002\u0004\u0005%\u00110\u0004\u0002\u0002\u0006)\u0019\u0011q\u0001\u0016\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\f\u0005\u0015!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0005\u0002\u0018A\u0019\u0011&a\u0005\n\u0007\u0005U!FA\u0004C_>dW-\u00198\t\u000fu\u0004\u0012\u0011!a\u0001s\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ry\u0017Q\u0004\u0005\b{F\t\t\u00111\u0001L\u0003!A\u0017m\u001d5D_\u0012,G#A&\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012a\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005E\u00111\u0006\u0005\b{R\t\t\u00111\u0001z\u0003M\u0011Vm]8ve\u000e,'+Z9vSJ,W.\u001a8u!\t!fcE\u0003\u0017\u0003g\ty\u0004\u0005\u0005\u00026\u0005m\u0002iS&T\u001b\t\t9DC\u0002\u0002:)\nqA];oi&lW-\u0003\u0003\u0002>\u0005]\"!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ogA!\u0011\u0011IA$\u001b\t\t\u0019EC\u0002\u0002FM\f!![8\n\u0007q\n\u0019\u0005\u0006\u0002\u00020\u0005)\u0011\r\u001d9msR91+a\u0014\u0002R\u0005M\u0003\"\u0002 \u001a\u0001\u0004\u0001\u0005\"B%\u001a\u0001\u0004Y\u0005bB(\u001a!\u0003\u0005\raS\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%g\u00059QO\\1qa2LH\u0003BA.\u0003O\u0002R!KA/\u0003CJ1!a\u0018+\u0005\u0019y\u0005\u000f^5p]B1\u0011&a\u0019A\u0017.K1!!\u001a+\u0005\u0019!V\u000f\u001d7fg!A\u0011\u0011N\u000e\u0002\u0002\u0003\u00071+A\u0002yIA\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\u001a\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA9!\r\u0001\u00181O\u0005\u0004\u0003k\n(AB(cU\u0016\u001cG\u000f"
)
public class ResourceRequirement implements Product, Serializable {
   private final String resourceName;
   private final int amount;
   private final int numParts;

   public static int $lessinit$greater$default$3() {
      return ResourceRequirement$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final ResourceRequirement x$0) {
      return ResourceRequirement$.MODULE$.unapply(x$0);
   }

   public static int apply$default$3() {
      return ResourceRequirement$.MODULE$.apply$default$3();
   }

   public static ResourceRequirement apply(final String resourceName, final int amount, final int numParts) {
      return ResourceRequirement$.MODULE$.apply(resourceName, amount, numParts);
   }

   public static Function1 tupled() {
      return ResourceRequirement$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ResourceRequirement$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String resourceName() {
      return this.resourceName;
   }

   public int amount() {
      return this.amount;
   }

   public int numParts() {
      return this.numParts;
   }

   public ResourceRequirement copy(final String resourceName, final int amount, final int numParts) {
      return new ResourceRequirement(resourceName, amount, numParts);
   }

   public String copy$default$1() {
      return this.resourceName();
   }

   public int copy$default$2() {
      return this.amount();
   }

   public int copy$default$3() {
      return this.numParts();
   }

   public String productPrefix() {
      return "ResourceRequirement";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.resourceName();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.amount());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.numParts());
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
      return x$1 instanceof ResourceRequirement;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "resourceName";
         }
         case 1 -> {
            return "amount";
         }
         case 2 -> {
            return "numParts";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.resourceName()));
      var1 = Statics.mix(var1, this.amount());
      var1 = Statics.mix(var1, this.numParts());
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof ResourceRequirement) {
               ResourceRequirement var4 = (ResourceRequirement)x$1;
               if (this.amount() == var4.amount() && this.numParts() == var4.numParts()) {
                  label48: {
                     String var10000 = this.resourceName();
                     String var5 = var4.resourceName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
                     }
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

   public ResourceRequirement(final String resourceName, final int amount, final int numParts) {
      this.resourceName = resourceName;
      this.amount = amount;
      this.numParts = numParts;
      Product.$init$(this);
   }
}
