package spire.macros;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.Names;
import scala.reflect.api.Trees;
import scala.reflect.macros.whitebox.Context;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}h\u0001B\r\u001b\u0001~A\u0001B\u000e\u0001\u0003\u0016\u0004%\ta\u000e\u0005\t#\u0002\u0011\t\u0012)A\u0005q!)!\u000b\u0001C\u0001'\")q\u000b\u0001C\u00011\")Q\u000f\u0001C\u0001m\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001\"CA\u001a\u0001\u0005\u0005I\u0011AA\u001b\u0011%\t9\u0005AI\u0001\n\u0003\tI\u0005C\u0005\u0002j\u0001\t\t\u0011\"\u0011\u0002l!I\u00111\u0010\u0001\u0002\u0002\u0013\u0005\u0011Q\u0010\u0005\n\u0003\u000b\u0003\u0011\u0011!C\u0001\u0003\u000fC\u0011\"!$\u0001\u0003\u0003%\t%a$\t\u0013\u0005e\u0005!!A\u0005\u0002\u0005m\u0005\"CAP\u0001\u0005\u0005I\u0011IAQ\u0011%\t)\u000bAA\u0001\n\u0003\n9\u000bC\u0005\u0002*\u0002\t\t\u0011\"\u0011\u0002,\"I\u0011Q\u0016\u0001\u0002\u0002\u0013\u0005\u0013qV\u0004\n\u0003gS\u0012\u0011!E\u0001\u0003k3\u0001\"\u0007\u000e\u0002\u0002#\u0005\u0011q\u0017\u0005\u0007%N!\t!a1\t\u0013\u0005%6#!A\u0005F\u0005-\u0006\"CAc'\u0005\u0005I\u0011QAd\u0011%\tInEA\u0001\n\u0003\u000bY\u000eC\u0005\u0002vN\t\t\u0011\"\u0003\u0002x\nQ1+\u001f8uCb,F/\u001b7\u000b\u0005ma\u0012AB7bGJ|7OC\u0001\u001e\u0003\u0015\u0019\b/\u001b:f\u0007\u0001)\"\u0001\t\u001e\u0014\t\u0001\tsE\u000b\t\u0003E\u0015j\u0011a\t\u0006\u0002I\u0005)1oY1mC&\u0011ae\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\tB\u0013BA\u0015$\u0005\u001d\u0001&o\u001c3vGR\u0004\"aK\u001a\u000f\u00051\ndBA\u00171\u001b\u0005q#BA\u0018\u001f\u0003\u0019a$o\\8u}%\tA%\u0003\u00023G\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001b6\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u00114%A\u0001d+\u0005A\u0004CA\u001d;\u0019\u0001!Qa\u000f\u0001C\u0002q\u0012\u0011aQ\t\u0003{\u0001\u0003\"A\t \n\u0005}\u001a#a\u0002(pi\"Lgn\u001a\n\u0004\u0003\u000ese\u0001\u0002\"\u0001\u0001\u0001\u0013A\u0002\u0010:fM&tW-\\3oiz\u0002\"\u0001R&\u000f\u0005\u0015KeB\u0001$I\u001d\tis)C\u0001\u001e\u0013\tYB$\u0003\u0002K5\u000511m\\7qCRL!\u0001T'\u0003\u000f\r{g\u000e^3yi*\u0011!J\u0007\t\u0003E=K!\u0001U\u0012\u0003\u0013MKgn\u001a7fi>t\u0017AA2!\u0003\u0019a\u0014N\\5u}Q\u0011AK\u0016\t\u0004+\u0002AT\"\u0001\u000e\t\u000bY\u001a\u0001\u0019\u0001\u001d\u0002\t9\fW.\u001a\u000b\u00033.\u0004\"AW3\u000f\u0005mkfB\u0001/\u0002\u001b\u0005\u0001\u0011B\u00010`\u0003!)h.\u001b<feN,\u0017B\u0001'a\u0015\t\t'-\u0001\u0005cY\u0006\u001c7NY8y\u0015\tY2M\u0003\u0002eG\u00059!/\u001a4mK\u000e$\u0018B\u00014h\u0005!!VM]7OC6,\u0017B\u00015j\u0005\u0015q\u0015-\\3t\u0015\tQ7-A\u0002ba&DQ\u0001\u001c\u0003A\u00025\f\u0011a\u001d\t\u0003]Jt!a\u001c9\u0011\u00055\u001a\u0013BA9$\u0003\u0019\u0001&/\u001a3fM&\u00111\u000f\u001e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005E\u001c\u0013!\u00028b[\u0016\u001cHCA<\u0000!\rAX0W\u0007\u0002s*\u0011!p_\u0001\nS6lW\u000f^1cY\u0016T!\u0001`\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002\u007fs\n!A*[:u\u0011\u001d\t\t!\u0002a\u0001\u0003\u0007\t!AY:\u0011\t\t\n)!\\\u0005\u0004\u0003\u000f\u0019#A\u0003\u001fsKB,\u0017\r^3e}\u00059\u0011n]\"mK\u0006tG\u0003BA\u0007\u0003'\u00012AIA\b\u0013\r\t\tb\t\u0002\b\u0005>|G.Z1o\u0011\u001d\t)B\u0002a\u0001\u0003/\t!!Z:\u0011\u000b\t\n)!!\u00071\t\u0005m\u0011q\u0005\t\u00067\u0006u\u0011QE\u0005\u0005\u0003?\t\tC\u0001\u0003FqB\u0014\u0018bAA\u0012E\n9\u0011\t\\5bg\u0016\u001c\bcA\u001d\u0002(\u0011a\u0011\u0011FA\n\u0003\u0003\u0005\tQ!\u0001\u0002,\t\u0019q\fJ\u0019\u0012\u0007u\ni\u0003E\u0002#\u0003_I1!!\r$\u0005\r\te._\u0001\u0005G>\u0004\u00180\u0006\u0003\u00028\u0005uB\u0003BA\u001d\u0003\u000b\u0002B!\u0016\u0001\u0002<A\u0019\u0011(!\u0010\u0005\rm:!\u0019AA #\ri\u0014\u0011\t\n\u0005\u0003\u0007\u001aeJB\u0003C\u0001\u0001\t\t\u0005\u0003\u00057\u000fA\u0005\t\u0019AA\u001e\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B!a\u0013\u0002bU\u0011\u0011Q\n\u0016\u0004q\u0005=3FAA)!\u0011\t\u0019&!\u0018\u000e\u0005\u0005U#\u0002BA,\u00033\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005m3%\u0001\u0006b]:|G/\u0019;j_:LA!a\u0018\u0002V\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\rmB!\u0019AA2#\ri\u0014Q\r\n\u0005\u0003O\u001aeJB\u0003C\u0001\u0001\t)'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003[\u0002B!a\u001c\u0002z5\u0011\u0011\u0011\u000f\u0006\u0005\u0003g\n)(\u0001\u0003mC:<'BAA<\u0003\u0011Q\u0017M^1\n\u0007M\f\t(\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002\u0000A\u0019!%!!\n\u0007\u0005\r5EA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002.\u0005%\u0005\"CAF\u0017\u0005\u0005\t\u0019AA@\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011\u0011\u0013\t\u0007\u0003'\u000b)*!\f\u000e\u0003mL1!a&|\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u0011Q\u0014\u0005\n\u0003\u0017k\u0011\u0011!a\u0001\u0003[\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QNAR\u0011%\tYIDA\u0001\u0002\u0004\ty(\u0001\u0005iCND7i\u001c3f)\t\ty(\u0001\u0005u_N#(/\u001b8h)\t\ti'\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u001b\t\t\fC\u0005\u0002\fF\t\t\u00111\u0001\u0002.\u0005Q1+\u001f8uCb,F/\u001b7\u0011\u0005U\u001b2\u0003B\n\"\u0003s\u0003B!a/\u0002B6\u0011\u0011Q\u0018\u0006\u0005\u0003\u007f\u000b)(\u0001\u0002j_&\u0019A'!0\u0015\u0005\u0005U\u0016!B1qa2LX\u0003BAe\u0003\u001f$B!a3\u0002XB!Q\u000bAAg!\rI\u0014q\u001a\u0003\u0007wY\u0011\r!!5\u0012\u0007u\n\u0019N\u0005\u0003\u0002V\u000ese!\u0002\"\u0014\u0001\u0005M\u0007B\u0002\u001c\u0017\u0001\u0004\ti-A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0005u\u0017q\u001d\u000b\u0005\u0003?\fy\u000fE\u0003#\u0003C\f)/C\u0002\u0002d\u000e\u0012aa\u00149uS>t\u0007cA\u001d\u0002h\u001211h\u0006b\u0001\u0003S\f2!PAv%\u0011\tio\u0011(\u0007\u000b\t\u001b\u0002!a;\t\u0013\u0005Ex#!AA\u0002\u0005M\u0018a\u0001=%aA!Q\u000bAAs\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tI\u0010\u0005\u0003\u0002p\u0005m\u0018\u0002BA\u007f\u0003c\u0012aa\u00142kK\u000e$\b"
)
public class SyntaxUtil implements Product, Serializable {
   private final Context c;

   public static Option unapply(final SyntaxUtil x$0) {
      return SyntaxUtil$.MODULE$.unapply(x$0);
   }

   public static SyntaxUtil apply(final Context c) {
      return SyntaxUtil$.MODULE$.apply(c);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Context c() {
      return this.c;
   }

   public Names.TermNameApi name(final String s) {
      return compat$.MODULE$.freshTermName(this.c(), (new StringBuilder(1)).append(s).append("$").toString());
   }

   public List names(final Seq bs) {
      return bs.toList().map((s) -> this.name(s));
   }

   public boolean isClean(final Seq es) {
      return es.forall((x$4) -> BoxesRunTime.boxToBoolean($anonfun$isClean$1(this, x$4)));
   }

   public SyntaxUtil copy(final Context c) {
      return new SyntaxUtil(c);
   }

   public Context copy$default$1() {
      return this.c();
   }

   public String productPrefix() {
      return "SyntaxUtil";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.c();
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
      return x$1 instanceof SyntaxUtil;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "c";
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
      boolean var7;
      if (this != x$1) {
         label53: {
            boolean var2;
            if (x$1 instanceof SyntaxUtil) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     SyntaxUtil var4 = (SyntaxUtil)x$1;
                     Context var10000 = this.c();
                     Context var5 = var4.c();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label35;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label35;
                     }

                     if (var4.canEqual(this)) {
                        var7 = true;
                        break label36;
                     }
                  }

                  var7 = false;
               }

               if (var7) {
                  break label53;
               }
            }

            var7 = false;
            return var7;
         }
      }

      var7 = true;
      return var7;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$isClean$1(final SyntaxUtil $this, final Exprs.Expr x$4) {
      Trees.TreeApi var3 = x$4.tree();
      boolean var2;
      if (var3 != null) {
         Option var4 = $this.c().universe().IdentTag().unapply(var3);
         if (!var4.isEmpty()) {
            Trees.IdentApi var5 = (Trees.IdentApi)var4.get();
            if (var5 != null) {
               Option var6 = $this.c().universe().Ident().unapply(var5);
               if (!var6.isEmpty()) {
                  Names.NameApi var7 = (Names.NameApi)var6.get();
                  if (var7 != null) {
                     Option var8 = $this.c().universe().TermNameTag().unapply(var7);
                     if (!var8.isEmpty() && var8.get() != null && ((Trees.SymTreeApi)var3).symbol().asTerm().isStable()) {
                        var2 = true;
                        return var2;
                     }
                  }
               }
            }
         }
      }

      if (var3 != null) {
         Option var9 = $this.c().universe().FunctionTag().unapply(var3);
         if (!var9.isEmpty()) {
            Trees.FunctionApi var10 = (Trees.FunctionApi)var9.get();
            if (var10 != null) {
               Option var11 = $this.c().universe().Function().unapply(var10);
               if (!var11.isEmpty()) {
                  var2 = true;
                  return var2;
               }
            }
         }
      }

      var2 = false;
      return var2;
   }

   public SyntaxUtil(final Context c) {
      this.c = c;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
