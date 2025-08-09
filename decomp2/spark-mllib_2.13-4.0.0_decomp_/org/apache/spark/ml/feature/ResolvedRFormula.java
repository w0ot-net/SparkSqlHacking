package org.apache.spark.ml.feature;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015e!\u0002\u000f\u001e\u0001~9\u0003\u0002\u0003 \u0001\u0005+\u0007I\u0011A \t\u0011!\u0003!\u0011#Q\u0001\n\u0001C\u0001\"\u0013\u0001\u0003\u0016\u0004%\tA\u0013\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u0017\"A\u0001\u000b\u0001BK\u0002\u0013\u0005\u0011\u000b\u0003\u0005V\u0001\tE\t\u0015!\u0003S\u0011\u00151\u0006\u0001\"\u0001X\u0011\u0015i\u0006\u0001\"\u0011_\u0011\u001dy\u0006!!A\u0005\u0002\u0001Dq\u0001\u001a\u0001\u0012\u0002\u0013\u0005Q\rC\u0004q\u0001E\u0005I\u0011A9\t\u000fM\u0004\u0011\u0013!C\u0001i\"9a\u000fAA\u0001\n\u0003:\b\u0002C@\u0001\u0003\u0003%\t!!\u0001\t\u0013\u0005%\u0001!!A\u0005\u0002\u0005-\u0001\"CA\f\u0001\u0005\u0005I\u0011IA\r\u0011%\t9\u0003AA\u0001\n\u0003\tI\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u00111\u0007\u0001\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003s9!\"!\u0010\u001e\u0003\u0003E\taHA \r%aR$!A\t\u0002}\t\t\u0005\u0003\u0004W-\u0011\u0005\u0011\u0011\f\u0005\t;Z\t\t\u0011\"\u0012\u0002\\!I\u0011Q\f\f\u0002\u0002\u0013\u0005\u0015q\f\u0005\n\u0003O2\u0012\u0011!CA\u0003SB\u0011\"a\u001f\u0017\u0003\u0003%I!! \u0003!I+7o\u001c7wK\u0012\u0014fi\u001c:nk2\f'B\u0001\u0010 \u0003\u001d1W-\u0019;ve\u0016T!\u0001I\u0011\u0002\u00055d'B\u0001\u0012$\u0003\u0015\u0019\b/\u0019:l\u0015\t!S%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002M\u0005\u0019qN]4\u0014\t\u0001Ac&\r\t\u0003S1j\u0011A\u000b\u0006\u0002W\u0005)1oY1mC&\u0011QF\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%z\u0013B\u0001\u0019+\u0005\u001d\u0001&o\u001c3vGR\u0004\"AM\u001e\u000f\u0005MJdB\u0001\u001b9\u001b\u0005)$B\u0001\u001c8\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0016\n\u0005iR\u0013a\u00029bG.\fw-Z\u0005\u0003yu\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!A\u000f\u0016\u0002\u000b1\f'-\u001a7\u0016\u0003\u0001\u0003\"!Q#\u000f\u0005\t\u001b\u0005C\u0001\u001b+\u0013\t!%&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\r\u001e\u0013aa\u0015;sS:<'B\u0001#+\u0003\u0019a\u0017MY3mA\u0005)A/\u001a:ngV\t1\nE\u00023\u0019:K!!T\u001f\u0003\u0007M+\u0017\u000fE\u00023\u0019\u0002\u000ba\u0001^3s[N\u0004\u0013\u0001\u00045bg&sG/\u001a:dKB$X#\u0001*\u0011\u0005%\u001a\u0016B\u0001++\u0005\u001d\u0011un\u001c7fC:\fQ\u0002[1t\u0013:$XM]2faR\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003Y5nc\u0006CA-\u0001\u001b\u0005i\u0002\"\u0002 \b\u0001\u0004\u0001\u0005\"B%\b\u0001\u0004Y\u0005\"\u0002)\b\u0001\u0004\u0011\u0016\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0001\u000bAaY8qsR!\u0001,\u00192d\u0011\u001dq\u0014\u0002%AA\u0002\u0001Cq!S\u0005\u0011\u0002\u0003\u00071\nC\u0004Q\u0013A\u0005\t\u0019\u0001*\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\taM\u000b\u0002AO.\n\u0001\u000e\u0005\u0002j]6\t!N\u0003\u0002lY\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003[*\n!\"\u00198o_R\fG/[8o\u0013\ty'NA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001sU\tYu-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003UT#AU4\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\bCA=\u007f\u001b\u0005Q(BA>}\u0003\u0011a\u0017M\\4\u000b\u0003u\fAA[1wC&\u0011aI_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0007\u00012!KA\u0003\u0013\r\t9A\u000b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u001b\t\u0019\u0002E\u0002*\u0003\u001fI1!!\u0005+\u0005\r\te.\u001f\u0005\n\u0003+y\u0011\u0011!a\u0001\u0003\u0007\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u000e!\u0019\ti\"a\t\u0002\u000e5\u0011\u0011q\u0004\u0006\u0004\u0003CQ\u0013AC2pY2,7\r^5p]&!\u0011QEA\u0010\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007I\u000bY\u0003C\u0005\u0002\u0016E\t\t\u00111\u0001\u0002\u000e\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rA\u0018\u0011\u0007\u0005\n\u0003+\u0011\u0012\u0011!a\u0001\u0003\u0007\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0007\ta!Z9vC2\u001cHc\u0001*\u0002<!I\u0011Q\u0003\u000b\u0002\u0002\u0003\u0007\u0011QB\u0001\u0011%\u0016\u001cx\u000e\u001c<fIJ3uN]7vY\u0006\u0004\"!\u0017\f\u0014\u000bY\t\u0019%a\u0014\u0011\u0011\u0005\u0015\u00131\n!L%bk!!a\u0012\u000b\u0007\u0005%#&A\u0004sk:$\u0018.\\3\n\t\u00055\u0013q\t\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA)\u0003/j!!a\u0015\u000b\u0007\u0005UC0\u0001\u0002j_&\u0019A(a\u0015\u0015\u0005\u0005}B#\u0001=\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fa\u000b\t'a\u0019\u0002f!)a(\u0007a\u0001\u0001\")\u0011*\u0007a\u0001\u0017\")\u0001+\u0007a\u0001%\u00069QO\\1qa2LH\u0003BA6\u0003o\u0002R!KA7\u0003cJ1!a\u001c+\u0005\u0019y\u0005\u000f^5p]B1\u0011&a\u001dA\u0017JK1!!\u001e+\u0005\u0019!V\u000f\u001d7fg!A\u0011\u0011\u0010\u000e\u0002\u0002\u0003\u0007\u0001,A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a \u0011\u0007e\f\t)C\u0002\u0002\u0004j\u0014aa\u00142kK\u000e$\b"
)
public class ResolvedRFormula implements Product, Serializable {
   private final String label;
   private final Seq terms;
   private final boolean hasIntercept;

   public static Option unapply(final ResolvedRFormula x$0) {
      return ResolvedRFormula$.MODULE$.unapply(x$0);
   }

   public static ResolvedRFormula apply(final String label, final Seq terms, final boolean hasIntercept) {
      return ResolvedRFormula$.MODULE$.apply(label, terms, hasIntercept);
   }

   public static Function1 tupled() {
      return ResolvedRFormula$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ResolvedRFormula$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String label() {
      return this.label;
   }

   public Seq terms() {
      return this.terms;
   }

   public boolean hasIntercept() {
      return this.hasIntercept;
   }

   public String toString() {
      Seq ts = (Seq)this.terms().map((x0$1) -> x0$1.length() > 1 ? String.valueOf(x0$1.mkString("{", ",", "}")) : x0$1.mkString());
      String termStr = ts.mkString("[", ",", "]");
      return "ResolvedRFormula(label=" + this.label() + ", terms=" + termStr + ", hasIntercept=" + this.hasIntercept() + ")";
   }

   public ResolvedRFormula copy(final String label, final Seq terms, final boolean hasIntercept) {
      return new ResolvedRFormula(label, terms, hasIntercept);
   }

   public String copy$default$1() {
      return this.label();
   }

   public Seq copy$default$2() {
      return this.terms();
   }

   public boolean copy$default$3() {
      return this.hasIntercept();
   }

   public String productPrefix() {
      return "ResolvedRFormula";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.label();
         }
         case 1 -> {
            return this.terms();
         }
         case 2 -> {
            return BoxesRunTime.boxToBoolean(this.hasIntercept());
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
      return x$1 instanceof ResolvedRFormula;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "label";
         }
         case 1 -> {
            return "terms";
         }
         case 2 -> {
            return "hasIntercept";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.label()));
      var1 = Statics.mix(var1, Statics.anyHash(this.terms()));
      var1 = Statics.mix(var1, this.hasIntercept() ? 1231 : 1237);
      return Statics.finalizeHash(var1, 3);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof ResolvedRFormula) {
               ResolvedRFormula var4 = (ResolvedRFormula)x$1;
               if (this.hasIntercept() == var4.hasIntercept()) {
                  label52: {
                     String var10000 = this.label();
                     String var5 = var4.label();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Seq var7 = this.terms();
                     Seq var6 = var4.terms();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public ResolvedRFormula(final String label, final Seq terms, final boolean hasIntercept) {
      this.label = label;
      this.terms = terms;
      this.hasIntercept = hasIntercept;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
