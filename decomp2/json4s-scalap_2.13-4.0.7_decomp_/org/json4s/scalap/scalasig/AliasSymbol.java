package org.json4s.scalap.scalasig;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c\u0001B\f\u0019\u0001\u0006B\u0001\u0002\u000f\u0001\u0003\u0016\u0004%\t!\u000f\u0005\t{\u0001\u0011\t\u0012)A\u0005u!)a\b\u0001C\u0001\u007f!)!\t\u0001C!\u0007\"9A\nAA\u0001\n\u0003i\u0005bB(\u0001#\u0003%\t\u0001\u0015\u0005\b7\u0002\t\t\u0011\"\u0011]\u0011\u001d!\u0007!!A\u0005\u0002\u0015Dq!\u001b\u0001\u0002\u0002\u0013\u0005!\u000eC\u0004q\u0001\u0005\u0005I\u0011I9\t\u000fa\u0004\u0011\u0011!C\u0001s\"9a\u0010AA\u0001\n\u0003z\b\"CA\u0002\u0001\u0005\u0005I\u0011IA\u0003\u0011%\t9\u0001AA\u0001\n\u0003\nI\u0001C\u0005\u0002\f\u0001\t\t\u0011\"\u0011\u0002\u000e\u001dI\u0011\u0011\u0003\r\u0002\u0002#\u0005\u00111\u0003\u0004\t/a\t\t\u0011#\u0001\u0002\u0016!1a(\u0005C\u0001\u0003[A\u0011\"a\u0002\u0012\u0003\u0003%)%!\u0003\t\u0013\u0005=\u0012#!A\u0005\u0002\u0006E\u0002\"CA\u001b#\u0005\u0005I\u0011QA\u001c\u0011%\t\u0019%EA\u0001\n\u0013\t)EA\u0006BY&\f7oU=nE>d'BA\r\u001b\u0003!\u00198-\u00197bg&<'BA\u000e\u001d\u0003\u0019\u00198-\u00197ba*\u0011QDH\u0001\u0007UN|g\u000eN:\u000b\u0003}\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0012'YA\u00111\u0005J\u0007\u00021%\u0011Q\u0005\u0007\u0002\u0011'fl'm\u001c7J]\u001a|7+_7c_2\u0004\"a\n\u0016\u000e\u0003!R\u0011!K\u0001\u0006g\u000e\fG.Y\u0005\u0003W!\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002.k9\u0011af\r\b\u0003_Ij\u0011\u0001\r\u0006\u0003c\u0001\na\u0001\u0010:p_Rt\u0014\"A\u0015\n\u0005QB\u0013a\u00029bG.\fw-Z\u0005\u0003m]\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001\u000e\u0015\u0002\u0015MLXNY8m\u0013:4w.F\u0001;!\t\u00193(\u0003\u0002=1\tQ1+_7c_2LeNZ8\u0002\u0017MLXNY8m\u0013:4w\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0001\u000b\u0005CA\u0012\u0001\u0011\u0015A4\u00011\u0001;\u0003\u0011\u0001\u0018\r\u001e5\u0016\u0003\u0011\u0003\"!R%\u000f\u0005\u0019;\u0005CA\u0018)\u0013\tA\u0005&\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0015.\u0013aa\u0015;sS:<'B\u0001%)\u0003\u0011\u0019w\u000e]=\u0015\u0005\u0001s\u0005b\u0002\u001d\u0006!\u0003\u0005\rAO\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0005\t&F\u0001\u001eSW\u0005\u0019\u0006C\u0001+Z\u001b\u0005)&B\u0001,X\u0003%)hn\u00195fG.,GM\u0003\u0002YQ\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005i+&!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006i\u0001O]8ek\u000e$\bK]3gSb,\u0012!\u0018\t\u0003=\u000el\u0011a\u0018\u0006\u0003A\u0006\fA\u0001\\1oO*\t!-\u0001\u0003kCZ\f\u0017B\u0001&`\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u00051\u0007CA\u0014h\u0013\tA\u0007FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002l]B\u0011q\u0005\\\u0005\u0003[\"\u00121!\u00118z\u0011\u001dy\u0017\"!AA\u0002\u0019\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#\u0001:\u0011\u0007M48.D\u0001u\u0015\t)\b&\u0001\u0006d_2dWm\u0019;j_:L!a\u001e;\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0003uv\u0004\"aJ>\n\u0005qD#a\u0002\"p_2,\u0017M\u001c\u0005\b_.\t\t\u00111\u0001l\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007u\u000b\t\u0001C\u0004p\u0019\u0005\u0005\t\u0019\u00014\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012AZ\u0001\ti>\u001cFO]5oOR\tQ,\u0001\u0004fcV\fGn\u001d\u000b\u0004u\u0006=\u0001bB8\u0010\u0003\u0003\u0005\ra[\u0001\f\u00032L\u0017m]*z[\n|G\u000e\u0005\u0002$#M)\u0011#a\u0006\u0002$A1\u0011\u0011DA\u0010u\u0001k!!a\u0007\u000b\u0007\u0005u\u0001&A\u0004sk:$\u0018.\\3\n\t\u0005\u0005\u00121\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003BA\u0013\u0003Wi!!a\n\u000b\u0007\u0005%\u0012-\u0001\u0002j_&\u0019a'a\n\u0015\u0005\u0005M\u0011!B1qa2LHc\u0001!\u00024!)\u0001\b\u0006a\u0001u\u00059QO\\1qa2LH\u0003BA\u001d\u0003\u007f\u0001BaJA\u001eu%\u0019\u0011Q\b\u0015\u0003\r=\u0003H/[8o\u0011!\t\t%FA\u0001\u0002\u0004\u0001\u0015a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011q\t\t\u0004=\u0006%\u0013bAA&?\n1qJ\u00196fGR\u0004"
)
public class AliasSymbol extends SymbolInfoSymbol implements Product, Serializable {
   private final SymbolInfo symbolInfo;

   public static Option unapply(final AliasSymbol x$0) {
      return AliasSymbol$.MODULE$.unapply(x$0);
   }

   public static AliasSymbol apply(final SymbolInfo symbolInfo) {
      return AliasSymbol$.MODULE$.apply(symbolInfo);
   }

   public static Function1 andThen(final Function1 g) {
      return AliasSymbol$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return AliasSymbol$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public SymbolInfo symbolInfo() {
      return this.symbolInfo;
   }

   public String path() {
      return this.name();
   }

   public AliasSymbol copy(final SymbolInfo symbolInfo) {
      return new AliasSymbol(symbolInfo);
   }

   public SymbolInfo copy$default$1() {
      return this.symbolInfo();
   }

   public String productPrefix() {
      return "AliasSymbol";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.symbolInfo();
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
      return x$1 instanceof AliasSymbol;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "symbolInfo";
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
            if (x$1 instanceof AliasSymbol) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label36: {
                  label35: {
                     AliasSymbol var4 = (AliasSymbol)x$1;
                     SymbolInfo var10000 = this.symbolInfo();
                     SymbolInfo var5 = var4.symbolInfo();
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

   public AliasSymbol(final SymbolInfo symbolInfo) {
      this.symbolInfo = symbolInfo;
      Product.$init$(this);
   }
}
