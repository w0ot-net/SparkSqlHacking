package org.apache.spark.ml.feature;

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
   bytes = "\u0006\u0005\u0005ub!\u0002\f\u0018\u0001f\t\u0003\u0002\u0003\u001f\u0001\u0005+\u0007I\u0011A\u001f\t\u0011\u0005\u0003!\u0011#Q\u0001\nyBQA\u0011\u0001\u0005\u0002\rCqA\u0012\u0001\u0002\u0002\u0013\u0005q\tC\u0004J\u0001E\u0005I\u0011\u0001&\t\u000fU\u0003\u0011\u0011!C!-\"9q\fAA\u0001\n\u0003\u0001\u0007b\u00023\u0001\u0003\u0003%\t!\u001a\u0005\bW\u0002\t\t\u0011\"\u0011m\u0011\u001d\u0019\b!!A\u0005\u0002QDqA\u001e\u0001\u0002\u0002\u0013\u0005s\u000fC\u0004z\u0001\u0005\u0005I\u0011\t>\t\u000fm\u0004\u0011\u0011!C!y\"9Q\u0010AA\u0001\n\u0003rxACA\u0001/\u0005\u0005\t\u0012A\r\u0002\u0004\u0019IacFA\u0001\u0012\u0003I\u0012Q\u0001\u0005\u0007\u0005B!\t!!\b\t\u000fm\u0004\u0012\u0011!C#y\"I\u0011q\u0004\t\u0002\u0002\u0013\u0005\u0015\u0011\u0005\u0005\n\u0003K\u0001\u0012\u0011!CA\u0003OA\u0011\"a\r\u0011\u0003\u0003%I!!\u000e\u0003\u0013%sG/\u001a:dKB$(B\u0001\r\u001a\u0003\u001d1W-\u0019;ve\u0016T!AG\u000e\u0002\u00055d'B\u0001\u000f\u001e\u0003\u0015\u0019\b/\u0019:l\u0015\tqr$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002A\u0005\u0019qN]4\u0014\u000b\u0001\u0011\u0003\u0006L\u0018\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\tI#&D\u0001\u0018\u0013\tYsC\u0001\u0003UKJl\u0007CA\u0012.\u0013\tqCEA\u0004Qe>$Wo\u0019;\u0011\u0005AJdBA\u00198\u001d\t\u0011d'D\u00014\u0015\t!T'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005)\u0013B\u0001\u001d%\u0003\u001d\u0001\u0018mY6bO\u0016L!AO\u001e\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005a\"\u0013aB3oC\ndW\rZ\u000b\u0002}A\u00111eP\u0005\u0003\u0001\u0012\u0012qAQ8pY\u0016\fg.\u0001\u0005f]\u0006\u0014G.\u001a3!\u0003\u0019a\u0014N\\5u}Q\u0011A)\u0012\t\u0003S\u0001AQ\u0001P\u0002A\u0002y\nAaY8qsR\u0011A\t\u0013\u0005\by\u0011\u0001\n\u00111\u0001?\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012a\u0013\u0016\u0003}1[\u0013!\u0014\t\u0003\u001dNk\u0011a\u0014\u0006\u0003!F\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005I#\u0013AC1o]>$\u0018\r^5p]&\u0011Ak\u0014\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001X!\tAV,D\u0001Z\u0015\tQ6,\u0001\u0003mC:<'\"\u0001/\u0002\t)\fg/Y\u0005\u0003=f\u0013aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRLX#A1\u0011\u0005\r\u0012\u0017BA2%\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\t1\u0017\u000e\u0005\u0002$O&\u0011\u0001\u000e\n\u0002\u0004\u0003:L\bb\u00026\t\u0003\u0003\u0005\r!Y\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u00035\u00042A\\9g\u001b\u0005y'B\u00019%\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003e>\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR\u0011a(\u001e\u0005\bU*\t\t\u00111\u0001g\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0005]C\bb\u00026\f\u0003\u0003\u0005\r!Y\u0001\tQ\u0006\u001c\bnQ8eKR\t\u0011-\u0001\u0005u_N#(/\u001b8h)\u00059\u0016AB3rk\u0006d7\u000f\u0006\u0002?\u007f\"9!NDA\u0001\u0002\u00041\u0017!C%oi\u0016\u00148-\u001a9u!\tI\u0003cE\u0003\u0011\u0003\u000f\t\u0019\u0002\u0005\u0004\u0002\n\u0005=a\bR\u0007\u0003\u0003\u0017Q1!!\u0004%\u0003\u001d\u0011XO\u001c;j[\u0016LA!!\u0005\u0002\f\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\u0005U\u00111D\u0007\u0003\u0003/Q1!!\u0007\\\u0003\tIw.C\u0002;\u0003/!\"!a\u0001\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0007\u0011\u000b\u0019\u0003C\u0003='\u0001\u0007a(A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005%\u0012q\u0006\t\u0005G\u0005-b(C\u0002\u0002.\u0011\u0012aa\u00149uS>t\u0007\u0002CA\u0019)\u0005\u0005\t\u0019\u0001#\u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00028A\u0019\u0001,!\u000f\n\u0007\u0005m\u0012L\u0001\u0004PE*,7\r\u001e"
)
public class Intercept implements Term, Product, Serializable {
   private final boolean enabled;

   public static Option unapply(final Intercept x$0) {
      return Intercept$.MODULE$.unapply(x$0);
   }

   public static Intercept apply(final boolean enabled) {
      return Intercept$.MODULE$.apply(enabled);
   }

   public static Function1 andThen(final Function1 g) {
      return Intercept$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return Intercept$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Terms asTerms() {
      return Term.asTerms$(this);
   }

   public Term add(final Term other) {
      return Term.add$(this, other);
   }

   public Term subtract(final Term other) {
      return Term.subtract$(this, other);
   }

   public Term interact(final Term other) {
      return Term.interact$(this, other);
   }

   public boolean enabled() {
      return this.enabled;
   }

   public Intercept copy(final boolean enabled) {
      return new Intercept(enabled);
   }

   public boolean copy$default$1() {
      return this.enabled();
   }

   public String productPrefix() {
      return "Intercept";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToBoolean(this.enabled());
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
      return x$1 instanceof Intercept;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "enabled";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.enabled() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 1);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label36: {
            if (x$1 instanceof Intercept) {
               Intercept var4 = (Intercept)x$1;
               if (this.enabled() == var4.enabled() && var4.canEqual(this)) {
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

   public Intercept(final boolean enabled) {
      this.enabled = enabled;
      Term.$init$(this);
      Product.$init$(this);
   }
}
