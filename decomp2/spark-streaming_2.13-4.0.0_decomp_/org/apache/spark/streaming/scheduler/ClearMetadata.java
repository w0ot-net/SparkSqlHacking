package org.apache.spark.streaming.scheduler;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015c!\u0002\f\u0018\u0001^\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011#Q\u0001\nyBQa\u0011\u0001\u0005\u0002\u0011Cqa\u0012\u0001\u0002\u0002\u0013\u0005\u0001\nC\u0004K\u0001E\u0005I\u0011A&\t\u000fY\u0003\u0011\u0011!C!/\"9\u0001\rAA\u0001\n\u0003\t\u0007bB3\u0001\u0003\u0003%\tA\u001a\u0005\bY\u0002\t\t\u0011\"\u0011n\u0011\u001d!\b!!A\u0005\u0002UDqA\u001f\u0001\u0002\u0002\u0013\u00053\u0010C\u0004~\u0001\u0005\u0005I\u0011\t@\t\u0011}\u0004\u0011\u0011!C!\u0003\u0003A\u0011\"a\u0001\u0001\u0003\u0003%\t%!\u0002\b\u0015\u0005%q#!A\t\u0002]\tYAB\u0005\u0017/\u0005\u0005\t\u0012A\f\u0002\u000e!11\t\u0005C\u0001\u0003KA\u0001b \t\u0002\u0002\u0013\u0015\u0013\u0011\u0001\u0005\n\u0003O\u0001\u0012\u0011!CA\u0003SA\u0011\"!\f\u0011\u0003\u0003%\t)a\f\t\u0013\u0005m\u0002#!A\u0005\n\u0005u\"!D\"mK\u0006\u0014X*\u001a;bI\u0006$\u0018M\u0003\u0002\u00193\u0005I1o\u00195fIVdWM\u001d\u0006\u00035m\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005qi\u0012!B:qCJ\\'B\u0001\u0010 \u0003\u0019\t\u0007/Y2iK*\t\u0001%A\u0002pe\u001e\u001cR\u0001\u0001\u0012)Y=\u0002\"a\t\u0014\u000e\u0003\u0011R\u0011!J\u0001\u0006g\u000e\fG.Y\u0005\u0003O\u0011\u0012a!\u00118z%\u00164\u0007CA\u0015+\u001b\u00059\u0012BA\u0016\u0018\u0005EQuNY$f]\u0016\u0014\u0018\r^8s\u000bZ,g\u000e\u001e\t\u0003G5J!A\f\u0013\u0003\u000fA\u0013x\u000eZ;diB\u0011\u0001'\u000f\b\u0003c]r!A\r\u001c\u000e\u0003MR!\u0001N\u001b\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!J\u0005\u0003q\u0011\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001\bJ\u0001\u0005i&lW-F\u0001?!\ty\u0004)D\u0001\u001a\u0013\t\t\u0015D\u0001\u0003US6,\u0017!\u0002;j[\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002F\rB\u0011\u0011\u0006\u0001\u0005\u0006y\r\u0001\rAP\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002F\u0013\"9A\b\u0002I\u0001\u0002\u0004q\u0014AD2paf$C-\u001a4bk2$H%M\u000b\u0002\u0019*\u0012a(T\u0016\u0002\u001dB\u0011q\nV\u0007\u0002!*\u0011\u0011KU\u0001\nk:\u001c\u0007.Z2lK\u0012T!a\u0015\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002V!\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\u0006CA-_\u001b\u0005Q&BA.]\u0003\u0011a\u0017M\\4\u000b\u0003u\u000bAA[1wC&\u0011qL\u0017\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\t\u0004\"aI2\n\u0005\u0011$#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$HCA4k!\t\u0019\u0003.\u0003\u0002jI\t\u0019\u0011I\\=\t\u000f-D\u0011\u0011!a\u0001E\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\u0012A\u001c\t\u0004_J<W\"\u00019\u000b\u0005E$\u0013AC2pY2,7\r^5p]&\u00111\u000f\u001d\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0002wsB\u00111e^\u0005\u0003q\u0012\u0012qAQ8pY\u0016\fg\u000eC\u0004l\u0015\u0005\u0005\t\u0019A4\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u00031rDqa[\u0006\u0002\u0002\u0003\u0007!-\u0001\u0005iCND7i\u001c3f)\u0005\u0011\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003a\u000ba!Z9vC2\u001cHc\u0001<\u0002\b!91NDA\u0001\u0002\u00049\u0017!D\"mK\u0006\u0014X*\u001a;bI\u0006$\u0018\r\u0005\u0002*!M)\u0001#a\u0004\u0002\u001cA1\u0011\u0011CA\f}\u0015k!!a\u0005\u000b\u0007\u0005UA%A\u0004sk:$\u0018.\\3\n\t\u0005e\u00111\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u000f\u0003Gi!!a\b\u000b\u0007\u0005\u0005B,\u0001\u0002j_&\u0019!(a\b\u0015\u0005\u0005-\u0011!B1qa2LHcA#\u0002,!)Ah\u0005a\u0001}\u00059QO\\1qa2LH\u0003BA\u0019\u0003o\u0001BaIA\u001a}%\u0019\u0011Q\u0007\u0013\u0003\r=\u0003H/[8o\u0011!\tI\u0004FA\u0001\u0002\u0004)\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\b\t\u00043\u0006\u0005\u0013bAA\"5\n1qJ\u00196fGR\u0004"
)
public class ClearMetadata implements JobGeneratorEvent, Product, Serializable {
   private final Time time;

   public static Option unapply(final ClearMetadata x$0) {
      return ClearMetadata$.MODULE$.unapply(x$0);
   }

   public static ClearMetadata apply(final Time time) {
      return ClearMetadata$.MODULE$.apply(time);
   }

   public static Function1 andThen(final Function1 g) {
      return ClearMetadata$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return ClearMetadata$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time time() {
      return this.time;
   }

   public ClearMetadata copy(final Time time) {
      return new ClearMetadata(time);
   }

   public Time copy$default$1() {
      return this.time();
   }

   public String productPrefix() {
      return "ClearMetadata";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.time();
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
      return x$1 instanceof ClearMetadata;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
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
            if (x$1 instanceof ClearMetadata) {
               label40: {
                  ClearMetadata var4 = (ClearMetadata)x$1;
                  Time var10000 = this.time();
                  Time var5 = var4.time();
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

   public ClearMetadata(final Time time) {
      this.time = time;
      Product.$init$(this);
   }
}
