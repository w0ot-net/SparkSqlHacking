package org.json4s;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}g\u0001B\u000f\u001f\u0001\u000eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u001f\u0002\u0011\t\u0012)A\u0005\u007f!Aq\u000b\u0001BK\u0002\u0013\u0005\u0003\f\u0003\u0005]\u0001\tE\t\u0015!\u0003Z\u0011\u0015i\u0006\u0001\"\u0001_\u0011\u00159\u0007\u0001\"\u0001i\u0011\u0015Q\b\u0001\"\u0001|\u0011%\t\t\u0002AA\u0001\n\u0003\t\u0019\u0002C\u0005\u0002\u001a\u0001\t\n\u0011\"\u0001\u0002\u001c!I\u0011\u0011\u0007\u0001\u0012\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003o\u0001\u0011\u0011!C!\u0003sA\u0011\"a\u000f\u0001\u0003\u0003%\t!!\u0010\t\u0013\u0005\u0015\u0003!!A\u0005\u0002\u0005\u001d\u0003\"CA'\u0001\u0005\u0005I\u0011IA(\u0011%\ti\u0006AA\u0001\n\u0003\ty\u0006C\u0005\u0002j\u0001\t\t\u0011\"\u0011\u0002l!I\u0011q\u000e\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000f\u0005\n\u0003g\u0002\u0011\u0011!C!\u0003kB\u0011\"a\u001e\u0001\u0003\u0003%\t%!\u001f\b\u0013\u0005ud$!A\t\u0002\u0005}d\u0001C\u000f\u001f\u0003\u0003E\t!!!\t\ru+B\u0011AAR\u0011%\t\u0019(FA\u0001\n\u000b\n)\bC\u0005\u0002&V\t\t\u0011\"!\u0002(\"I\u0011qW\u000b\u0012\u0002\u0013\u0005\u00111\u0007\u0005\n\u0003s+\u0012\u0011!CA\u0003wC\u0011\"a5\u0016#\u0003%\t!a\r\t\u0013\u0005UW#!A\u0005\n\u0005]'AD*i_J$H+\u001f9f\u0011&tGo\u001d\u0006\u0003?\u0001\naA[:p]R\u001a(\"A\u0011\u0002\u0007=\u0014xm\u0001\u0001\u0014\u000b\u0001!#FL\u0019\u0011\u0005\u0015BS\"\u0001\u0014\u000b\u0003\u001d\nQa]2bY\u0006L!!\u000b\u0014\u0003\r\u0005s\u0017PU3g!\tYC&D\u0001\u001f\u0013\ticDA\u0005UsB,\u0007*\u001b8ugB\u0011QeL\u0005\u0003a\u0019\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u00023u9\u00111\u0007\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003m\t\na\u0001\u0010:p_Rt\u0014\"A\u0014\n\u0005e2\u0013a\u00029bG.\fw-Z\u0005\u0003wq\u0012AbU3sS\u0006d\u0017N_1cY\u0016T!!\u000f\u0014\u0002\u000b!Lg\u000e^:\u0016\u0003}\u00022A\r!C\u0013\t\tEH\u0001\u0003MSN$\bGA\"N!\r!\u0005j\u0013\b\u0003\u000b\u001a\u0003\"\u0001\u000e\u0014\n\u0005\u001d3\u0013A\u0002)sK\u0012,g-\u0003\u0002J\u0015\n)1\t\\1tg*\u0011qI\n\t\u0003\u00196c\u0001\u0001B\u0005O\u0005\u0005\u0005\t\u0011!B\u0001!\n\u0019q\fJ\u0019\u0002\r!Lg\u000e^:!#\t\tF\u000b\u0005\u0002&%&\u00111K\n\u0002\b\u001d>$\b.\u001b8h!\t)S+\u0003\u0002WM\t\u0019\u0011I\\=\u0002#QL\b/\u001a%j]R4\u0015.\u001a7e\u001d\u0006lW-F\u0001Z!\t!%,\u0003\u0002\\\u0015\n11\u000b\u001e:j]\u001e\f!\u0003^=qK\"Kg\u000e\u001e$jK2$g*Y7fA\u00051A(\u001b8jiz\"2a\u00181g!\tY\u0003\u0001C\u0003>\u000b\u0001\u0007\u0011\rE\u00023\u0001\n\u0004$aY3\u0011\u0007\u0011CE\r\u0005\u0002MK\u0012Ia\nYA\u0001\u0002\u0003\u0015\t\u0001\u0015\u0005\b/\u0016\u0001\n\u00111\u0001Z\u0003\u001dA\u0017N\u001c;G_J$\"![:\u0011\u0007\u0015RG.\u0003\u0002lM\t!1k\\7f!\ti'/D\u0001o\u0015\ty\u0007/\u0001\u0003mC:<'\"A9\u0002\t)\fg/Y\u0005\u00037:DQ\u0001\u001e\u0004A\u0002U\fQa\u00197buj\u0004$A\u001e=\u0011\u0007\u0011Cu\u000f\u0005\u0002Mq\u0012I\u0011p]A\u0001\u0002\u0003\u0015\t\u0001\u0015\u0002\u0004?\u0012\u0012\u0014\u0001C2mCN\u001chi\u001c:\u0015\tq|\u00181\u0001\t\u0004Ku\u0014\u0015B\u0001@'\u0005\u0019y\u0005\u000f^5p]\"1\u0011\u0011A\u0004A\u0002e\u000bA\u0001[5oi\"9\u0011QA\u0004A\u0002\u0005\u001d\u0011A\u00029be\u0016tG\u000f\r\u0003\u0002\n\u00055\u0001\u0003\u0002#I\u0003\u0017\u00012\u0001TA\u0007\t-\ty!a\u0001\u0002\u0002\u0003\u0005)\u0011\u0001)\u0003\u0007}#3'\u0001\u0003d_BLH#B0\u0002\u0016\u0005]\u0001bB\u001f\t!\u0003\u0005\r!\u0019\u0005\b/\"\u0001\n\u00111\u0001Z\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"!!\b+\u0007}\nyb\u000b\u0002\u0002\"A!\u00111EA\u0017\u001b\t\t)C\u0003\u0003\u0002(\u0005%\u0012!C;oG\",7m[3e\u0015\r\tYCJ\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA\u0018\u0003K\u0011\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!\u000e+\u0007e\u000by\"A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002Y\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011q\b\t\u0004K\u0005\u0005\u0013bAA\"M\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019A+!\u0013\t\u0013\u0005-S\"!AA\u0002\u0005}\u0012a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002RA)\u00111KA-)6\u0011\u0011Q\u000b\u0006\u0004\u0003/2\u0013AC2pY2,7\r^5p]&!\u00111LA+\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0005\u0014q\r\t\u0004K\u0005\r\u0014bAA3M\t9!i\\8mK\u0006t\u0007\u0002CA&\u001f\u0005\u0005\t\u0019\u0001+\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004Y\u00065\u0004\"CA&!\u0005\u0005\t\u0019AA \u0003!A\u0017m\u001d5D_\u0012,GCAA \u0003!!xn\u0015;sS:<G#\u00017\u0002\r\u0015\fX/\u00197t)\u0011\t\t'a\u001f\t\u0011\u0005-3#!AA\u0002Q\u000bab\u00155peR$\u0016\u0010]3IS:$8\u000f\u0005\u0002,+M)Q#a!\u0002\u001aBA\u0011QQAF\u0003\u001fKv,\u0004\u0002\u0002\b*\u0019\u0011\u0011\u0012\u0014\u0002\u000fI,h\u000e^5nK&!\u0011QRAD\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005e\u0001\u000b\t\n\r\u0003\u0002\u0014\u0006]\u0005\u0003\u0002#I\u0003+\u00032\u0001TAL\t%qU#!A\u0001\u0002\u000b\u0005\u0001\u000b\u0005\u0003\u0002\u001c\u0006\u0005VBAAO\u0015\r\ty\n]\u0001\u0003S>L1aOAO)\t\ty(A\u0003baBd\u0017\u0010F\u0003`\u0003S\u000b)\f\u0003\u0004>1\u0001\u0007\u00111\u0016\t\u0005e\u0001\u000bi\u000b\r\u0003\u00020\u0006M\u0006\u0003\u0002#I\u0003c\u00032\u0001TAZ\t)q\u0015\u0011VA\u0001\u0002\u0003\u0015\t\u0001\u0015\u0005\b/b\u0001\n\u00111\u0001Z\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u0012\u0012\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003{\u000by\r\u0005\u0003&{\u0006}\u0006CB\u0013\u0002B\u0006\u0015\u0017,C\u0002\u0002D\u001a\u0012a\u0001V;qY\u0016\u0014\u0004\u0003\u0002\u001aA\u0003\u000f\u0004D!!3\u0002NB!A\tSAf!\ra\u0015Q\u001a\u0003\n\u001dj\t\t\u0011!A\u0003\u0002AC\u0001\"!5\u001b\u0003\u0003\u0005\raX\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$#'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002ZB\u0019Q.a7\n\u0007\u0005ugN\u0001\u0004PE*,7\r\u001e"
)
public class ShortTypeHints implements TypeHints, Product, Serializable {
   private final List hints;
   private final String typeHintFieldName;

   public static String $lessinit$greater$default$2() {
      return ShortTypeHints$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final ShortTypeHints x$0) {
      return ShortTypeHints$.MODULE$.unapply(x$0);
   }

   public static String apply$default$2() {
      return ShortTypeHints$.MODULE$.apply$default$2();
   }

   public static ShortTypeHints apply(final List hints, final String typeHintFieldName) {
      return ShortTypeHints$.MODULE$.apply(hints, typeHintFieldName);
   }

   public static Function1 tupled() {
      return ShortTypeHints$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return ShortTypeHints$.MODULE$.curried();
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
      return new Some(clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1));
   }

   public Option classFor(final String hint, final Class parent) {
      return this.hints().find((x$1) -> BoxesRunTime.boxToBoolean($anonfun$classFor$1(this, hint, x$1)));
   }

   public ShortTypeHints copy(final List hints, final String typeHintFieldName) {
      return new ShortTypeHints(hints, typeHintFieldName);
   }

   public List copy$default$1() {
      return this.hints();
   }

   public String copy$default$2() {
      return this.typeHintFieldName();
   }

   public String productPrefix() {
      return "ShortTypeHints";
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
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof ShortTypeHints;
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
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var9;
      if (this != x$1) {
         label63: {
            boolean var2;
            if (x$1 instanceof ShortTypeHints) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               label45: {
                  label54: {
                     ShortTypeHints var4 = (ShortTypeHints)x$1;
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
   public static final boolean $anonfun$classFor$2(final String hint$1, final String x$2) {
      boolean var10000;
      label23: {
         if (x$2 == null) {
            if (hint$1 == null) {
               break label23;
            }
         } else if (x$2.equals(hint$1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$classFor$1(final ShortTypeHints $this, final String hint$1, final Class x$1) {
      return $this.hintFor(x$1).exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$classFor$2(hint$1, x$2)));
   }

   public ShortTypeHints(final List hints, final String typeHintFieldName) {
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
