package scala.xml.dtd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.Utility$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d\u0001\u0002\u000e\u001c\u0001\nB\u0001b\u000e\u0001\u0003\u0016\u0004%\t\u0001\u000f\u0005\ty\u0001\u0011\t\u0012)A\u0005s!AQ\b\u0001BK\u0002\u0013\u0005a\b\u0003\u0005H\u0001\tE\t\u0015!\u0003@\u0011\u0015A\u0005\u0001\"\u0001J\u0011\u0015i\u0005\u0001\"\u0011?\u0011\u0015q\u0005\u0001\"\u0011P\u0011\u001d1\u0006!!A\u0005\u0002]CqA\u0017\u0001\u0012\u0002\u0013\u00051\fC\u0004g\u0001E\u0005I\u0011A4\t\u000f%\u0004\u0011\u0011!C!U\"9!\u000fAA\u0001\n\u0003\u0019\bbB<\u0001\u0003\u0003%\t\u0001\u001f\u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\ti\u0001AA\u0001\n\u0003\ty\u0001C\u0005\u0002\u0014\u0001\t\t\u0011\"\u0011\u0002\u0016!I\u0011\u0011\u0004\u0001\u0002\u0002\u0013\u0005\u00131\u0004\u0005\n\u0003;\u0001\u0011\u0011!C!\u0003?9\u0011\"a\t\u001c\u0003\u0003E\t!!\n\u0007\u0011iY\u0012\u0011!E\u0001\u0003OAa\u0001\u0013\u000b\u0005\u0002\u0005}\u0002\u0002C'\u0015\u0003\u0003%)%!\u0011\t\u0013\u0005\rC#!A\u0005\u0002\u0006\u0015\u0003\"CA&)\u0005\u0005I\u0011QA'\u0011%\ty\u0006FA\u0001\n\u0013\t\tGA\u0004E\u000b\u001a\u000bU\u000b\u0014+\u000b\u0005qi\u0012a\u00013uI*\u0011adH\u0001\u0004q6d'\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001aI\u0014,!\t!S%D\u0001\u001c\u0013\t13DA\u0006EK\u001a\fW\u000f\u001c;EK\u000ed\u0007C\u0001\u0015*\u001b\u0005y\u0012B\u0001\u0016 \u0005\u001d\u0001&o\u001c3vGR\u0004\"\u0001\f\u001b\u000f\u00055\u0012dB\u0001\u00182\u001b\u0005y#B\u0001\u0019\"\u0003\u0019a$o\\8u}%\t\u0001%\u0003\u00024?\u00059\u0001/Y2lC\u001e,\u0017BA\u001b7\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0019t$A\u0003gSb,G-F\u0001:!\tA#(\u0003\u0002<?\t9!i\\8mK\u0006t\u0017A\u00024jq\u0016$\u0007%\u0001\u0005biR4\u0016\r\\;f+\u0005y\u0004C\u0001!E\u001d\t\t%\t\u0005\u0002/?%\u00111iH\u0001\u0007!J,G-\u001a4\n\u0005\u00153%AB*ue&twM\u0003\u0002D?\u0005I\u0011\r\u001e;WC2,X\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007)[E\n\u0005\u0002%\u0001!)q'\u0002a\u0001s!)Q(\u0002a\u0001\u007f\u0005AAo\\*ue&tw-A\u0006ck&dGm\u0015;sS:<GC\u0001)U!\t\t&K\u0004\u0002)e%\u00111K\u000e\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\t\u000bU;\u0001\u0019\u0001)\u0002\u0005M\u0014\u0017\u0001B2paf$2A\u0013-Z\u0011\u001d9\u0004\u0002%AA\u0002eBq!\u0010\u0005\u0011\u0002\u0003\u0007q(\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003qS#!O/,\u0003y\u0003\"a\u00183\u000e\u0003\u0001T!!\u00192\u0002\u0013Ut7\r[3dW\u0016$'BA2 \u0003)\tgN\\8uCRLwN\\\u0005\u0003K\u0002\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012\u0001\u001b\u0016\u0003\u007fu\u000bQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DX#A6\u0011\u00051\fX\"A7\u000b\u00059|\u0017\u0001\u00027b]\u001eT\u0011\u0001]\u0001\u0005U\u00064\u0018-\u0003\u0002F[\u0006a\u0001O]8ek\u000e$\u0018I]5usV\tA\u000f\u0005\u0002)k&\u0011ao\b\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003sr\u0004\"\u0001\u000b>\n\u0005m|\"aA!os\"9Q0DA\u0001\u0002\u0004!\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0002A)\u00111AA\u0005s6\u0011\u0011Q\u0001\u0006\u0004\u0003\u000fy\u0012AC2pY2,7\r^5p]&!\u00111BA\u0003\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\u0007e\n\t\u0002C\u0004~\u001f\u0005\u0005\t\u0019A=\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004W\u0006]\u0001bB?\u0011\u0003\u0003\u0005\r\u0001^\u0001\tQ\u0006\u001c\bnQ8eKR\tA/\u0001\u0004fcV\fGn\u001d\u000b\u0004s\u0005\u0005\u0002bB?\u0013\u0003\u0003\u0005\r!_\u0001\b\t\u00163\u0015)\u0016'U!\t!CcE\u0003\u0015\u0003S\t)\u0004E\u0004\u0002,\u0005E\u0012h\u0010&\u000e\u0005\u00055\"bAA\u0018?\u00059!/\u001e8uS6,\u0017\u0002BA\u001a\u0003[\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\t9$!\u0010\u000e\u0005\u0005e\"bAA\u001e_\u0006\u0011\u0011n\\\u0005\u0004k\u0005eBCAA\u0013)\u0005Y\u0017!B1qa2LH#\u0002&\u0002H\u0005%\u0003\"B\u001c\u0018\u0001\u0004I\u0004\"B\u001f\u0018\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003\u001f\nY\u0006E\u0003)\u0003#\n)&C\u0002\u0002T}\u0011aa\u00149uS>t\u0007#\u0002\u0015\u0002Xez\u0014bAA-?\t1A+\u001e9mKJB\u0001\"!\u0018\u0019\u0003\u0003\u0005\rAS\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA2!\ra\u0017QM\u0005\u0004\u0003Oj'AB(cU\u0016\u001cG\u000f"
)
public class DEFAULT extends DefaultDecl implements Product, Serializable {
   private final boolean fixed;
   private final String attValue;

   public static Option unapply(final DEFAULT x$0) {
      return DEFAULT$.MODULE$.unapply(x$0);
   }

   public static DEFAULT apply(final boolean fixed, final String attValue) {
      return DEFAULT$.MODULE$.apply(fixed, attValue);
   }

   public static Function1 tupled() {
      return DEFAULT$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return DEFAULT$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean fixed() {
      return this.fixed;
   }

   public String attValue() {
      return this.attValue;
   }

   public String toString() {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toString$3(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public StringBuilder buildString(final StringBuilder sb) {
      if (this.fixed()) {
         sb.append("#FIXED ");
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return Utility$.MODULE$.appendEscapedQuoted(this.attValue(), sb);
   }

   public DEFAULT copy(final boolean fixed, final String attValue) {
      return new DEFAULT(fixed, attValue);
   }

   public boolean copy$default$1() {
      return this.fixed();
   }

   public String copy$default$2() {
      return this.attValue();
   }

   public String productPrefix() {
      return "DEFAULT";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0:
            return BoxesRunTime.boxToBoolean(this.fixed());
         case 1:
            return this.attValue();
         default:
            return Statics.ioobe(x$1);
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof DEFAULT;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0:
            return "fixed";
         case 1:
            return "attValue";
         default:
            return (String)Statics.ioobe(x$1);
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.fixed() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.attValue()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof DEFAULT) {
               DEFAULT var4 = (DEFAULT)x$1;
               if (this.fixed() == var4.fixed()) {
                  label44: {
                     String var10000 = this.attValue();
                     String var5 = var4.attValue();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label44;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label44;
                     }

                     if (var4.canEqual(this)) {
                        break label51;
                     }
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

   // $FF: synthetic method
   public static final void $anonfun$toString$3(final DEFAULT $this, final StringBuilder sb) {
      $this.buildString(sb);
   }

   public DEFAULT(final boolean fixed, final String attValue) {
      this.fixed = fixed;
      this.attValue = attValue;
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
