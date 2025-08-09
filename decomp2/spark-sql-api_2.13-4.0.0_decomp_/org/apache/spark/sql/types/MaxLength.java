package org.apache.spark.sql.types;

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
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002uBq\u0001\u0019\u0001\u0002\u0002\u0013\u0005\u0011\rC\u0004h\u0001\u0005\u0005I\u0011\t5\t\u000f=\u0004\u0011\u0011!C\u0001a\"9Q\u000fAA\u0001\n\u00032\bb\u0002=\u0001\u0003\u0003%\t%\u001f\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011\u001da\b!!A\u0005Bu<\u0001b`\f\u0002\u0002#\u0005\u0011\u0011\u0001\u0004\t-]\t\t\u0011#\u0001\u0002\u0004!1!\t\u0005C\u0001\u00037AqA\u001f\t\u0002\u0002\u0013\u00153\u0010C\u0005\u0002\u001eA\t\t\u0011\"!\u0002 !I\u00111\u0005\t\u0002\u0002\u0013\u0005\u0015Q\u0005\u0005\n\u0003c\u0001\u0012\u0011!C\u0005\u0003g\u0011\u0011\"T1y\u0019\u0016tw\r\u001e5\u000b\u0005aI\u0012!\u0002;za\u0016\u001c(B\u0001\u000e\u001c\u0003\r\u0019\u0018\u000f\u001c\u0006\u00039u\tQa\u001d9be.T!AH\u0010\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0013aA8sO\u000e\u00011#\u0002\u0001$S5\u0002\u0004C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#AB!osJ+g\r\u0005\u0002+W5\tq#\u0003\u0002-/\t\u00012\u000b\u001e:j]\u001e\u001cuN\\:ue\u0006Lg\u000e\u001e\t\u0003I9J!aL\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0011'\u000f\b\u0003e]r!a\r\u001c\u000e\u0003QR!!N\u0011\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0013B\u0001\u001d&\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a*\u0013A\u00027f]\u001e$\b.F\u0001?!\t!s(\u0003\u0002AK\t\u0019\u0011J\u001c;\u0002\u000f1,gn\u001a;iA\u00051A(\u001b8jiz\"\"\u0001R#\u0011\u0005)\u0002\u0001\"\u0002\u001f\u0004\u0001\u0004q\u0014\u0001B2paf$\"\u0001\u0012%\t\u000fq\"\u0001\u0013!a\u0001}\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT#A&+\u0005yb5&A'\u0011\u00059\u001bV\"A(\u000b\u0005A\u000b\u0016!C;oG\",7m[3e\u0015\t\u0011V%\u0001\u0006b]:|G/\u0019;j_:L!\u0001V(\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\u0005Y\u0006twMC\u0001]\u0003\u0011Q\u0017M^1\n\u0005yK&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0005\t,\u0007C\u0001\u0013d\u0013\t!WEA\u0002B]fDqA\u001a\u0005\u0002\u0002\u0003\u0007a(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0002SB\u0019!.\u001c2\u000e\u0003-T!\u0001\\\u0013\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002oW\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\t\tH\u000f\u0005\u0002%e&\u00111/\n\u0002\b\u0005>|G.Z1o\u0011\u001d1'\"!AA\u0002\t\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0011qk\u001e\u0005\bM.\t\t\u00111\u0001?\u0003!A\u0017m\u001d5D_\u0012,G#\u0001 \u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012aV\u0001\u0007KF,\u0018\r\\:\u0015\u0005Et\bb\u00024\u000f\u0003\u0003\u0005\rAY\u0001\n\u001b\u0006DH*\u001a8hi\"\u0004\"A\u000b\t\u0014\u000bA\t)!!\u0005\u0011\r\u0005\u001d\u0011Q\u0002 E\u001b\t\tIAC\u0002\u0002\f\u0015\nqA];oi&lW-\u0003\u0003\u0002\u0010\u0005%!!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ocA!\u00111CA\r\u001b\t\t)BC\u0002\u0002\u0018m\u000b!![8\n\u0007i\n)\u0002\u0006\u0002\u0002\u0002\u0005)\u0011\r\u001d9msR\u0019A)!\t\t\u000bq\u001a\u0002\u0019\u0001 \u0002\u000fUt\u0017\r\u001d9msR!\u0011qEA\u0017!\u0011!\u0013\u0011\u0006 \n\u0007\u0005-RE\u0001\u0004PaRLwN\u001c\u0005\t\u0003_!\u0012\u0011!a\u0001\t\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005U\u0002c\u0001-\u00028%\u0019\u0011\u0011H-\u0003\r=\u0013'.Z2u\u0001"
)
public class MaxLength implements StringConstraint, Product, Serializable {
   private final int length;

   public static Option unapply(final MaxLength x$0) {
      return MaxLength$.MODULE$.unapply(x$0);
   }

   public static MaxLength apply(final int length) {
      return MaxLength$.MODULE$.apply(length);
   }

   public static Function1 andThen(final Function1 g) {
      return MaxLength$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return MaxLength$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int length() {
      return this.length;
   }

   public MaxLength copy(final int length) {
      return new MaxLength(length);
   }

   public int copy$default$1() {
      return this.length();
   }

   public String productPrefix() {
      return "MaxLength";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.length());
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
      return x$1 instanceof MaxLength;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "length";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.length());
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof MaxLength) {
               MaxLength var4 = (MaxLength)x$1;
               if (this.length() == var4.length() && var4.canEqual(this)) {
                  break label36;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public MaxLength(final int length) {
      this.length = length;
      Product.$init$(this);
   }
}
