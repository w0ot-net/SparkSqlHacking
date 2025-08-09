package org.apache.spark.sql.catalyst.analysis;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.catalyst.util.QuotingUtils$;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001\u0002\u000e\u001c\u0001\"B\u0001b\u0010\u0001\u0003\u0016\u0004%\t\u0001\u0011\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0003\"AQ\n\u0001BK\u0002\u0013\u0005a\n\u0003\u0005P\u0001\tE\t\u0015!\u0003E\u0011!\u0001\u0006A!f\u0001\n\u0003\n\u0006\u0002\u0003-\u0001\u0005#\u0005\u000b\u0011\u0002*\t\u000be\u0003A\u0011\u0001.\t\u000be\u0003A\u0011\u00011\t\u000f\t\u0004\u0011\u0011!C!G\"91\u000eAA\u0001\n\u0003a\u0007b\u00029\u0001\u0003\u0003%\t!\u001d\u0005\bo\u0002\t\t\u0011\"\u0011y\u0011!y\b!!A\u0005\u0002\u0005\u0005\u0001\"CA\u0006\u0001\u0005\u0005I\u0011IA\u0007\u0011%\t\t\u0002AA\u0001\n\u0003\n\u0019\u0002C\u0005\u0002\u0016\u0001\t\t\u0011\"\u0011\u0002\u0018\u001dI\u00111D\u000e\u0002\u0002#\u0005\u0011Q\u0004\u0004\t5m\t\t\u0011#\u0001\u0002 !1\u0011L\u0005C\u0001\u0003oA\u0011\"!\u000f\u0013\u0003\u0003%)%a\u000f\t\u0013\u0005u\"#!A\u0005\u0002\u0006}\u0002\"CA$%E\u0005I\u0011AA%\u0011%\tyFEA\u0001\n\u0003\u000b\t\u0007C\u0005\u0002pI\t\n\u0011\"\u0001\u0002J!I\u0011\u0011\u000f\n\u0002\u0002\u0013%\u00111\u000f\u0002\u001b\u001d>tW)\u001c9us:\u000bW.Z:qC\u000e,W\t_2faRLwN\u001c\u0006\u00039u\t\u0001\"\u00198bYf\u001c\u0018n\u001d\u0006\u0003=}\t\u0001bY1uC2L8\u000f\u001e\u0006\u0003A\u0005\n1a]9m\u0015\t\u00113%A\u0003ta\u0006\u00148N\u0003\u0002%K\u00051\u0011\r]1dQ\u0016T\u0011AJ\u0001\u0004_J<7\u0001A\n\u0005\u0001%j3\u0007\u0005\u0002+W5\tq$\u0003\u0002-?\t\t\u0012I\\1msNL7/\u0012=dKB$\u0018n\u001c8\u0011\u00059\nT\"A\u0018\u000b\u0003A\nQa]2bY\u0006L!AM\u0018\u0003\u000fA\u0013x\u000eZ;diB\u0011A\u0007\u0010\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001O\u0014\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0001\u0014BA\u001e0\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005mz\u0013!\u00038b[\u0016\u001c\b/Y2f+\u0005\t\u0005c\u0001\u0018C\t&\u00111i\f\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\u000b&s!AR$\u0011\u0005Yz\u0013B\u0001%0\u0003\u0019\u0001&/\u001a3fM&\u0011!j\u0013\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!{\u0013A\u00038b[\u0016\u001c\b/Y2fA\u00059A-\u001a;bS2\u001cX#\u0001#\u0002\u0011\u0011,G/Y5mg\u0002\nQaY1vg\u0016,\u0012A\u0015\t\u0004]M+\u0016B\u0001+0\u0005\u0019y\u0005\u000f^5p]B\u0011AGV\u0005\u0003/z\u0012\u0011\u0002\u00165s_^\f'\r\\3\u0002\r\r\fWo]3!\u0003\u0019a\u0014N\\5u}Q!1,\u00180`!\ta\u0006!D\u0001\u001c\u0011\u0015yt\u00011\u0001B\u0011\u0015iu\u00011\u0001E\u0011\u001d\u0001v\u0001%AA\u0002I#\"aW1\t\u000b}B\u0001\u0019A!\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005!\u0007CA3k\u001b\u00051'BA4i\u0003\u0011a\u0017M\\4\u000b\u0003%\fAA[1wC&\u0011!JZ\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002[B\u0011aF\\\u0005\u0003_>\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"A];\u0011\u00059\u001a\u0018B\u0001;0\u0005\r\te.\u001f\u0005\bm.\t\t\u00111\u0001n\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\t\u0011\u0010E\u0002{{Jl\u0011a\u001f\u0006\u0003y>\n!bY8mY\u0016\u001cG/[8o\u0013\tq8P\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA\u0002\u0003\u0013\u00012ALA\u0003\u0013\r\t9a\f\u0002\b\u0005>|G.Z1o\u0011\u001d1X\"!AA\u0002I\f!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019A-a\u0004\t\u000fYt\u0011\u0011!a\u0001[\u0006A\u0001.Y:i\u0007>$W\rF\u0001n\u0003\u0019)\u0017/^1mgR!\u00111AA\r\u0011\u001d1\b#!AA\u0002I\f!DT8o\u000b6\u0004H/\u001f(b[\u0016\u001c\b/Y2f\u000bb\u001cW\r\u001d;j_:\u0004\"\u0001\u0018\n\u0014\u000bI\t\t#!\f\u0011\u0011\u0005\r\u0012\u0011F!E%nk!!!\n\u000b\u0007\u0005\u001dr&A\u0004sk:$\u0018.\\3\n\t\u0005-\u0012Q\u0005\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA\u0018\u0003ki!!!\r\u000b\u0007\u0005M\u0002.\u0001\u0002j_&\u0019Q(!\r\u0015\u0005\u0005u\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0011\fQ!\u00199qYf$raWA!\u0003\u0007\n)\u0005C\u0003@+\u0001\u0007\u0011\tC\u0003N+\u0001\u0007A\tC\u0004Q+A\u0005\t\u0019\u0001*\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uIM*\"!a\u0013+\u0007I\u000bie\u000b\u0002\u0002PA!\u0011\u0011KA.\u001b\t\t\u0019F\u0003\u0003\u0002V\u0005]\u0013!C;oG\",7m[3e\u0015\r\tIfL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA/\u0003'\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u0003\u001d)h.\u00199qYf$B!a\u0019\u0002lA!afUA3!\u0019q\u0013qM!E%&\u0019\u0011\u0011N\u0018\u0003\rQ+\b\u000f\\34\u0011!\tigFA\u0001\u0002\u0004Y\u0016a\u0001=%a\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001e\u0011\u0007\u0015\f9(C\u0002\u0002z\u0019\u0014aa\u00142kK\u000e$\b"
)
public class NonEmptyNamespaceException extends AnalysisException implements Product {
   private final String[] namespace;
   private final String details;
   private final Option cause;

   public static Option $lessinit$greater$default$3() {
      return NonEmptyNamespaceException$.MODULE$.$lessinit$greater$default$3();
   }

   public static Option unapply(final NonEmptyNamespaceException x$0) {
      return NonEmptyNamespaceException$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$3() {
      return NonEmptyNamespaceException$.MODULE$.apply$default$3();
   }

   public static NonEmptyNamespaceException apply(final String[] namespace, final String details, final Option cause) {
      return NonEmptyNamespaceException$.MODULE$.apply(namespace, details, cause);
   }

   public static Function1 tupled() {
      return NonEmptyNamespaceException$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return NonEmptyNamespaceException$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String[] namespace() {
      return this.namespace;
   }

   public String details() {
      return this.details;
   }

   public Option cause() {
      return this.cause;
   }

   public String productPrefix() {
      return "NonEmptyNamespaceException";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.namespace();
         }
         case 1 -> {
            return this.details();
         }
         case 2 -> {
            return this.cause();
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
      return x$1 instanceof NonEmptyNamespaceException;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "namespace";
         }
         case 1 -> {
            return "details";
         }
         case 2 -> {
            return "cause";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof NonEmptyNamespaceException) {
               NonEmptyNamespaceException var4 = (NonEmptyNamespaceException)x$1;
               if (this.namespace() == var4.namespace()) {
                  label52: {
                     String var10000 = this.details();
                     String var5 = var4.details();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     Option var7 = this.cause();
                     Option var6 = var4.cause();
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

   public NonEmptyNamespaceException(final String[] namespace, final String details, final Option cause) {
      super("_LEGACY_ERROR_TEMP_3103", (Map)scala.Predef..MODULE$.Map().apply(.MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("namespace"), QuotingUtils$.MODULE$.quoted(namespace)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("details"), details)}))));
      this.namespace = namespace;
      this.details = details;
      this.cause = cause;
      Product.$init$(this);
   }

   public NonEmptyNamespaceException(final String[] namespace) {
      this(namespace, "", scala.None..MODULE$);
   }
}
