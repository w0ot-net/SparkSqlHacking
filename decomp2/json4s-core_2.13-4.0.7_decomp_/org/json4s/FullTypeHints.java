package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.json4s.reflect.Reflector$;
import org.json4s.reflect.ScalaType;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eh\u0001B\u000f\u001f\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u007f!Aq\u000b\u0001BK\u0002\u0013\u0005\u0003\f\u0003\u0005]\u0001\tE\t\u0015!\u0003Z\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u00159\u0007\u0001\"\u0001i\u0011\u0015Q\b\u0001\"\u0001|\u0011%\t\u0019\u0003AA\u0001\n\u0003\t)\u0003C\u0005\u0002,\u0001\t\n\u0011\"\u0001\u0002.!I\u00111\t\u0001\u0012\u0002\u0013\u0005\u0011Q\t\u0005\n\u0003\u0013\u0002\u0011\u0011!C!\u0003\u0017B\u0011\"!\u0014\u0001\u0003\u0003%\t!a\u0014\t\u0013\u0005]\u0003!!A\u0005\u0002\u0005e\u0003\"CA0\u0001\u0005\u0005I\u0011IA1\u0011%\ty\u0007AA\u0001\n\u0003\t\t\bC\u0005\u0002|\u0001\t\t\u0011\"\u0011\u0002~!I\u0011\u0011\u0011\u0001\u0002\u0002\u0013\u0005\u00131\u0011\u0005\n\u0003\u000b\u0003\u0011\u0011!C!\u0003\u000fC\u0011\"!#\u0001\u0003\u0003%\t%a#\b\u0013\u0005=e$!A\t\u0002\u0005Ee\u0001C\u000f\u001f\u0003\u0003E\t!a%\t\ru+B\u0011AA[\u0011%\t))FA\u0001\n\u000b\n9\tC\u0005\u00028V\t\t\u0011\"!\u0002:\"I\u0011\u0011Z\u000b\u0012\u0002\u0013\u0005\u0011Q\t\u0005\n\u0003\u0017,\u0012\u0011!CA\u0003\u001bD\u0011\"!:\u0016#\u0003%\t!!\u0012\t\u0013\u0005\u001dX#!A\u0005\n\u0005%(!\u0004$vY2$\u0016\u0010]3IS:$8O\u0003\u0002 A\u00051!n]8oiMT\u0011!I\u0001\u0004_J<7\u0001A\n\u0006\u0001\u0011Rc&\r\t\u0003K!j\u0011A\n\u0006\u0002O\u0005)1oY1mC&\u0011\u0011F\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005-bS\"\u0001\u0010\n\u00055r\"!\u0003+za\u0016D\u0015N\u001c;t!\t)s&\u0003\u00021M\t9\u0001K]8ek\u000e$\bC\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027E\u00051AH]8pizJ\u0011aJ\u0005\u0003s\u0019\nq\u0001]1dW\u0006<W-\u0003\u0002<y\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0011HJ\u0001\u0006Q&tGo]\u000b\u0002\u007fA\u0019!\u0007\u0011\"\n\u0005\u0005c$\u0001\u0002'jgR\u0004$aQ'\u0011\u0007\u0011C5J\u0004\u0002F\rB\u0011AGJ\u0005\u0003\u000f\u001a\na\u0001\u0015:fI\u00164\u0017BA%K\u0005\u0015\u0019E.Y:t\u0015\t9e\u0005\u0005\u0002M\u001b2\u0001A!\u0003(\u0003\u0003\u0003\u0005\tQ!\u0001Q\u0005\ryF%M\u0001\u0007Q&tGo\u001d\u0011\u0012\u0005E#\u0006CA\u0013S\u0013\t\u0019fEA\u0004O_RD\u0017N\\4\u0011\u0005\u0015*\u0016B\u0001,'\u0005\r\te._\u0001\u0012if\u0004X\rS5oi\u001aKW\r\u001c3OC6,W#A-\u0011\u0005\u0011S\u0016BA.K\u0005\u0019\u0019FO]5oO\u0006\u0011B/\u001f9f\u0011&tGOR5fY\u0012t\u0015-\\3!\u0003\u0019a\u0014N\\5u}Q\u0019q\f\u00194\u0011\u0005-\u0002\u0001\"B\u001f\u0006\u0001\u0004\t\u0007c\u0001\u001aAEB\u00121-\u001a\t\u0004\t\"#\u0007C\u0001'f\t%q\u0005-!A\u0001\u0002\u000b\u0005\u0001\u000bC\u0004X\u000bA\u0005\t\u0019A-\u0002\u000f!Lg\u000e\u001e$peR\u0011\u0011n\u001d\t\u0004K)d\u0017BA6'\u0005\u0011\u0019v.\\3\u0011\u00055\u0014X\"\u00018\u000b\u0005=\u0004\u0018\u0001\u00027b]\u001eT\u0011!]\u0001\u0005U\u00064\u0018-\u0003\u0002\\]\")AO\u0002a\u0001k\u0006)1\r\\1{uB\u0012a\u000f\u001f\t\u0004\t\";\bC\u0001'y\t%I8/!A\u0001\u0002\u000b\u0005\u0001KA\u0002`II\n\u0001b\u00197bgN4uN\u001d\u000b\u0006y\u0006E\u0011Q\u0003\t\u0004Ku|\u0018B\u0001@'\u0005\u0019y\u0005\u000f^5p]B\"\u0011\u0011AA\u0004!\u0015i\u00171AA\u0003\u0013\tIe\u000eE\u0002M\u0003\u000f!1\"!\u0003\u0002\f\u0005\u0005\t\u0011!B\u0001!\n!q\fJ\u00191\u0011%\tiaBA\u0001\u0002\u0003\ty!\u0001\u0005%C:|gNZ;o\u0017\u0001Aa!a\u0005\b\u0001\u0004I\u0016\u0001\u00025j]RDq!a\u0006\b\u0001\u0004\tI\"\u0001\u0004qCJ,g\u000e\u001e\u0019\u0005\u00037\ty\u0002\u0005\u0003E\u0011\u0006u\u0001c\u0001'\u0002 \u0011Y\u0011\u0011EA\u000b\u0003\u0003\u0005\tQ!\u0001Q\u0005\ryFeM\u0001\u0005G>\u0004\u0018\u0010F\u0003`\u0003O\tI\u0003C\u0004>\u0011A\u0005\t\u0019A1\t\u000f]C\u0001\u0013!a\u00013\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0018U\ry\u0014\u0011G\u0016\u0003\u0003g\u0001B!!\u000e\u0002@5\u0011\u0011q\u0007\u0006\u0005\u0003s\tY$A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\b\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002B\u0005]\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA$U\rI\u0016\u0011G\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u00031\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!!\u0015\u0011\u0007\u0015\n\u0019&C\u0002\u0002V\u0019\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$2\u0001VA.\u0011%\ti&DA\u0001\u0002\u0004\t\t&A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003G\u0002R!!\u001a\u0002lQk!!a\u001a\u000b\u0007\u0005%d%\u0001\u0006d_2dWm\u0019;j_:LA!!\u001c\u0002h\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\u0019(!\u001f\u0011\u0007\u0015\n)(C\u0002\u0002x\u0019\u0012qAQ8pY\u0016\fg\u000e\u0003\u0005\u0002^=\t\t\u00111\u0001U\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u00071\fy\bC\u0005\u0002^A\t\t\u00111\u0001\u0002R\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002R\u0005AAo\\*ue&tw\rF\u0001m\u0003\u0019)\u0017/^1mgR!\u00111OAG\u0011!\tifEA\u0001\u0002\u0004!\u0016!\u0004$vY2$\u0016\u0010]3IS:$8\u000f\u0005\u0002,+M)Q#!&\u0002,BA\u0011qSAO\u0003CKv,\u0004\u0002\u0002\u001a*\u0019\u00111\u0014\u0014\u0002\u000fI,h\u000e^5nK&!\u0011qTAM\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005e\u0001\u000b\u0019\u000b\r\u0003\u0002&\u0006%\u0006\u0003\u0002#I\u0003O\u00032\u0001TAU\t%qU#!A\u0001\u0002\u000b\u0005\u0001\u000b\u0005\u0003\u0002.\u0006MVBAAX\u0015\r\t\t\f]\u0001\u0003S>L1aOAX)\t\t\t*A\u0003baBd\u0017\u0010F\u0003`\u0003w\u000b9\r\u0003\u0004>1\u0001\u0007\u0011Q\u0018\t\u0005e\u0001\u000by\f\r\u0003\u0002B\u0006\u0015\u0007\u0003\u0002#I\u0003\u0007\u00042\u0001TAc\t)q\u00151XA\u0001\u0002\u0003\u0015\t\u0001\u0015\u0005\b/b\u0001\n\u00111\u0001Z\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u001f\f\t\u000f\u0005\u0003&{\u0006E\u0007CB\u0013\u0002T\u0006]\u0017,C\u0002\u0002V\u001a\u0012a\u0001V;qY\u0016\u0014\u0004\u0003\u0002\u001aA\u00033\u0004D!a7\u0002`B!A\tSAo!\ra\u0015q\u001c\u0003\n\u001dj\t\t\u0011!A\u0003\u0002AC\u0001\"a9\u001b\u0003\u0003\u0005\raX\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002lB\u0019Q.!<\n\u0007\u0005=hN\u0001\u0004PE*,7\r\u001e"
)
public class FullTypeHints implements TypeHints, Product, Serializable {
   private final List hints;
   private final String typeHintFieldName;

   public static String $lessinit$greater$default$2() {
      return FullTypeHints$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final FullTypeHints x$0) {
      return FullTypeHints$.MODULE$.unapply(x$0);
   }

   public static String apply$default$2() {
      return FullTypeHints$.MODULE$.apply$default$2();
   }

   public static FullTypeHints apply(final List hints, final String typeHintFieldName) {
      return FullTypeHints$.MODULE$.apply(hints, typeHintFieldName);
   }

   public static Function1 tupled() {
      return FullTypeHints$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return FullTypeHints$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean isTypeHintField(final Tuple2 f, final Class parent) {
      return TypeHints.isTypeHintField$(this, f, parent);
   }

   public Option typeHintFieldNameForHint(final String hint, final Class parent) {
      return TypeHints.typeHintFieldNameForHint$(this, hint, parent);
   }

   public Option typeHintFieldNameForClass(final Class clazz) {
      return TypeHints.typeHintFieldNameForClass$(this, clazz);
   }

   public boolean containsHint(final Class clazz) {
      return TypeHints.containsHint$(this, clazz);
   }

   public boolean shouldExtractHints(final Class clazz) {
      return TypeHints.shouldExtractHints$(this, clazz);
   }

   public PartialFunction deserialize() {
      return TypeHints.deserialize$(this);
   }

   public PartialFunction serialize() {
      return TypeHints.serialize$(this);
   }

   public List components() {
      return TypeHints.components$(this);
   }

   public TypeHints $plus(final TypeHints hints) {
      return TypeHints.$plus$(this, hints);
   }

   public List hints() {
      return this.hints;
   }

   public String typeHintFieldName() {
      return this.typeHintFieldName;
   }

   public Some hintFor(final Class clazz) {
      return new Some(clazz.getName());
   }

   public Option classFor(final String hint, final Class parent) {
      return .MODULE$.option2Iterable(Reflector$.MODULE$.scalaTypeOf(hint)).find((h) -> BoxesRunTime.boxToBoolean($anonfun$classFor$1(this, h))).map((x$1) -> x$1.erasure());
   }

   public FullTypeHints copy(final List hints, final String typeHintFieldName) {
      return new FullTypeHints(hints, typeHintFieldName);
   }

   public List copy$default$1() {
      return this.hints();
   }

   public String copy$default$2() {
      return this.typeHintFieldName();
   }

   public String productPrefix() {
      return "FullTypeHints";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.hints();
            break;
         case 1:
            var10000 = this.typeHintFieldName();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof FullTypeHints;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "hints";
            break;
         case 1:
            var10000 = "typeHintFieldName";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof FullTypeHints) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     FullTypeHints var4 = (FullTypeHints)x$1;
                     List var10000 = this.hints();
                     List var5 = var4.hints();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label54;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label54;
                     }

                     String var7 = this.typeHintFieldName();
                     String var6 = var4.typeHintFieldName();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label54;
                        }
                     } else if (!var7.equals(var6)) {
                        break label54;
                     }

                     if (var4.canEqual(this)) {
                        var9 = true;
                        break label45;
                     }
                  }

                  var9 = false;
               }

               if (var9) {
                  break label63;
               }
            }

            var9 = false;
            return var9;
         }
      }

      var9 = true;
      return var9;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$classFor$2(final ScalaType h$1, final Class l) {
      return l.isAssignableFrom(h$1.erasure());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$classFor$1(final FullTypeHints $this, final ScalaType h) {
      return $this.hints().exists((l) -> BoxesRunTime.boxToBoolean($anonfun$classFor$2(h, l)));
   }

   public FullTypeHints(final List hints, final String typeHintFieldName) {
      this.hints = hints;
      this.typeHintFieldName = typeHintFieldName;
      TypeHints.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
