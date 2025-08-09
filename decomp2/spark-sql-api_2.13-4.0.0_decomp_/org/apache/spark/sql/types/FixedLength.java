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
   bytes = "\u0006\u0005\u0005mb\u0001\u0002\f\u0018\u0001\nB\u0001\u0002\u0010\u0001\u0003\u0016\u0004%\t!\u0010\u0005\t\u0003\u0002\u0011\t\u0012)A\u0005}!)!\t\u0001C\u0001\u0007\"9a\tAA\u0001\n\u00039\u0005bB%\u0001#\u0003%\tA\u0013\u0005\b+\u0002\t\t\u0011\"\u0011W\u0011\u001dy\u0006!!A\u0005\u0002uBq\u0001\u0019\u0001\u0002\u0002\u0013\u0005\u0011\rC\u0004h\u0001\u0005\u0005I\u0011\t5\t\u000f=\u0004\u0011\u0011!C\u0001a\"9Q\u000fAA\u0001\n\u00032\bb\u0002=\u0001\u0003\u0003%\t%\u001f\u0005\bu\u0002\t\t\u0011\"\u0011|\u0011\u001da\b!!A\u0005Bu<\u0001b`\f\u0002\u0002#\u0005\u0011\u0011\u0001\u0004\t-]\t\t\u0011#\u0001\u0002\u0004!1!\t\u0005C\u0001\u00037AqA\u001f\t\u0002\u0002\u0013\u00153\u0010C\u0005\u0002\u001eA\t\t\u0011\"!\u0002 !I\u00111\u0005\t\u0002\u0002\u0013\u0005\u0015Q\u0005\u0005\n\u0003c\u0001\u0012\u0011!C\u0005\u0003g\u00111BR5yK\u0012dUM\\4uQ*\u0011\u0001$G\u0001\u0006if\u0004Xm\u001d\u0006\u00035m\t1a]9m\u0015\taR$A\u0003ta\u0006\u00148N\u0003\u0002\u001f?\u00051\u0011\r]1dQ\u0016T\u0011\u0001I\u0001\u0004_J<7\u0001A\n\u0006\u0001\rJS\u0006\r\t\u0003I\u001dj\u0011!\n\u0006\u0002M\u0005)1oY1mC&\u0011\u0001&\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)ZS\"A\f\n\u00051:\"\u0001E*ue&twmQ8ogR\u0014\u0018-\u001b8u!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019:\u001d\t\u0011tG\u0004\u00024m5\tAG\u0003\u00026C\u00051AH]8pizJ\u0011AJ\u0005\u0003q\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001(J\u0001\u0007Y\u0016tw\r\u001e5\u0016\u0003y\u0002\"\u0001J \n\u0005\u0001+#aA%oi\u00069A.\u001a8hi\"\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002E\u000bB\u0011!\u0006\u0001\u0005\u0006y\r\u0001\rAP\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002E\u0011\"9A\b\u0002I\u0001\u0002\u0004q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0017*\u0012a\bT\u0016\u0002\u001bB\u0011ajU\u0007\u0002\u001f*\u0011\u0001+U\u0001\nk:\u001c\u0007.Z2lK\u0012T!AU\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002U\u001f\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00059\u0006C\u0001-^\u001b\u0005I&B\u0001.\\\u0003\u0011a\u0017M\\4\u000b\u0003q\u000bAA[1wC&\u0011a,\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0011!-\u001a\t\u0003I\rL!\u0001Z\u0013\u0003\u0007\u0005s\u0017\u0010C\u0004g\u0011\u0005\u0005\t\u0019\u0001 \u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\u0005I\u0007c\u00016nE6\t1N\u0003\u0002mK\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u00059\\'\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$\"!\u001d;\u0011\u0005\u0011\u0012\u0018BA:&\u0005\u001d\u0011un\u001c7fC:DqA\u001a\u0006\u0002\u0002\u0003\u0007!-\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,GCA,x\u0011\u001d17\"!AA\u0002y\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002}\u0005AAo\\*ue&tw\rF\u0001X\u0003\u0019)\u0017/^1mgR\u0011\u0011O \u0005\bM:\t\t\u00111\u0001c\u0003-1\u0015\u000e_3e\u0019\u0016tw\r\u001e5\u0011\u0005)\u00022#\u0002\t\u0002\u0006\u0005E\u0001CBA\u0004\u0003\u001bqD)\u0004\u0002\u0002\n)\u0019\u00111B\u0013\u0002\u000fI,h\u000e^5nK&!\u0011qBA\u0005\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u0003'\tI\"\u0004\u0002\u0002\u0016)\u0019\u0011qC.\u0002\u0005%|\u0017b\u0001\u001e\u0002\u0016Q\u0011\u0011\u0011A\u0001\u0006CB\u0004H.\u001f\u000b\u0004\t\u0006\u0005\u0002\"\u0002\u001f\u0014\u0001\u0004q\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003O\ti\u0003\u0005\u0003%\u0003Sq\u0014bAA\u0016K\t1q\n\u001d;j_:D\u0001\"a\f\u0015\u0003\u0003\u0005\r\u0001R\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA\u001b!\rA\u0016qG\u0005\u0004\u0003sI&AB(cU\u0016\u001cG\u000f"
)
public class FixedLength implements StringConstraint, Product, Serializable {
   private final int length;

   public static Option unapply(final FixedLength x$0) {
      return FixedLength$.MODULE$.unapply(x$0);
   }

   public static FixedLength apply(final int length) {
      return FixedLength$.MODULE$.apply(length);
   }

   public static Function1 andThen(final Function1 g) {
      return FixedLength$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return FixedLength$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int length() {
      return this.length;
   }

   public FixedLength copy(final int length) {
      return new FixedLength(length);
   }

   public int copy$default$1() {
      return this.length();
   }

   public String productPrefix() {
      return "FixedLength";
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
      return x$1 instanceof FixedLength;
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
            if (x$1 instanceof FixedLength) {
               FixedLength var4 = (FixedLength)x$1;
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

   public FixedLength(final int length) {
      this.length = length;
      Product.$init$(this);
   }
}
