package scala.xml.dtd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.collection.immutable.Set;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.xml.Utility$;
import scala.xml.dtd.impl.Base;
import scala.xml.dtd.impl.WordBerrySethi;
import scala.xml.dtd.impl.WordExp;

@ScalaSignature(
   bytes = "\u0006\u0005\tMr!\u0002\u0017.\u0011\u0003!d!\u0002\u001c.\u0011\u00039\u0004\"\u0002 \u0002\t\u0003yT\u0001\u0002!\u0002A\u0005+a!a\u0011\u0002A\u0005\u0015c!CA(\u0003A\u0005\u0019\u0013AA)\u000f\u001d\ti'\u0001E\u0001\u0003_2q!a\u0014\u0002\u0011\u0003\t\t\b\u0003\u0004?\u000f\u0011\u0005\u0011Q\u000f\u0005\tw\u001e\u0011\r\u0011\"\u0011\u0002x!9\u0011\u0011P\u0004!\u0002\u0013\u0011e\u0001B\"\u0002\u0001\u0012C\u0001\u0002W\u0006\u0003\u0016\u0004%\t!\u0017\u0005\tE.\u0011\t\u0012)A\u00055\")ah\u0003C\u0001G\")Qm\u0003C!M\"9qmCA\u0001\n\u0003A\u0007b\u00026\f#\u0003%\ta\u001b\u0005\bm.\t\t\u0011\"\u0011x\u0011!y8\"!A\u0005\u0002\u0005\u0005\u0001\"CA\u0005\u0017\u0005\u0005I\u0011AA\u0006\u0011%\t9bCA\u0001\n\u0003\nI\u0002C\u0005\u0002(-\t\t\u0011\"\u0001\u0002*!I\u00111G\u0006\u0002\u0002\u0013\u0005\u0013Q\u0007\u0005\n\u0003sY\u0011\u0011!C!\u0003wA\u0011\"!\u0010\f\u0003\u0003%\t%a\u0010\b\u0013\u0005m\u0014!!A\t\u0002\u0005ud\u0001C\"\u0002\u0003\u0003E\t!a \t\ryZB\u0011AAL\u0011!)7$!A\u0005F\u0005e\u0005\"CAN7\u0005\u0005I\u0011QAO\u0011%\t\tkGA\u0001\n\u0003\u000b\u0019\u000bC\u0005\u00020n\t\t\u0011\"\u0003\u00022\"9\u0011\u0011X\u0001\u0005\u0002\u0005m\u0006bBAx\u0003\u0011\u0005\u0011\u0011\u001f\u0005\b\u0003k\fA\u0011AA|\u0011\u001d\ti-\u0001C\u0001\u0005\u0007Aq!!4\u0002\t\u0013\u00119\u0001C\u0004\u0002N\u0006!\tA!\n\t\u000f\u00055\u0017\u0001\"\u0001\u0003.\u00191a'LA\u0011\u0003\u0007DaA\u0010\u0015\u0005\u0002\u0005-\u0007\"B3)\t\u00032\u0007bBAgQ\u0019\u0005\u0011qZ\u0001\r\u0007>tG/\u001a8u\u001b>$W\r\u001c\u0006\u0003]=\n1\u0001\u001a;e\u0015\t\u0001\u0014'A\u0002y[2T\u0011AM\u0001\u0006g\u000e\fG.Y\u0002\u0001!\t)\u0014!D\u0001.\u00051\u0019uN\u001c;f]Rlu\u000eZ3m'\t\t\u0001\b\u0005\u0002:y5\t!H\u0003\u0002<[\u0005!\u0011.\u001c9m\u0013\ti$HA\u0004X_J$W\t\u001f9\u0002\rqJg.\u001b;?)\u0005!$aB0mC\n,G\u000e\u0016\t\u0003\u0005.i\u0011!\u0001\u0002\t\u000b2,WNT1nKN!1\"\u0012%M!\t\u0011e)\u0003\u0002Hy\t)A*\u00192fYB\u0011\u0011JS\u0007\u0002c%\u00111*\r\u0002\b!J|G-^2u!\tiUK\u0004\u0002O':\u0011qJU\u0007\u0002!*\u0011\u0011kM\u0001\u0007yI|w\u000e\u001e \n\u0003IJ!\u0001V\u0019\u0002\u000fA\f7m[1hK&\u0011ak\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003)F\nAA\\1nKV\t!\f\u0005\u0002\\?:\u0011A,\u0018\t\u0003\u001fFJ!AX\u0019\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0017M\u0001\u0004TiJLgn\u001a\u0006\u0003=F\nQA\\1nK\u0002\"\"!\u00113\t\u000bas\u0001\u0019\u0001.\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012AW\u0001\u0005G>\u0004\u0018\u0010\u0006\u0002BS\"9\u0001\f\u0005I\u0001\u0002\u0004Q\u0016AD2paf$C-\u001a4bk2$H%M\u000b\u0002Y*\u0012!,\\\u0016\u0002]B\u0011q\u000e^\u0007\u0002a*\u0011\u0011O]\u0001\nk:\u001c\u0007.Z2lK\u0012T!a]\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002va\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005A\bCA=\u007f\u001b\u0005Q(BA>}\u0003\u0011a\u0017M\\4\u000b\u0003u\fAA[1wC&\u0011\u0001M_\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0003\u0003\u0007\u00012!SA\u0003\u0013\r\t9!\r\u0002\u0004\u0013:$\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003\u001b\t\u0019\u0002E\u0002J\u0003\u001fI1!!\u00052\u0005\r\te.\u001f\u0005\n\u0003+!\u0012\u0011!a\u0001\u0003\u0007\t1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u000e!\u0019\ti\"a\t\u0002\u000e5\u0011\u0011q\u0004\u0006\u0004\u0003C\t\u0014AC2pY2,7\r^5p]&!\u0011QEA\u0010\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005-\u0012\u0011\u0007\t\u0004\u0013\u00065\u0012bAA\u0018c\t9!i\\8mK\u0006t\u0007\"CA\u000b-\u0005\u0005\t\u0019AA\u0007\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\u0007a\f9\u0004C\u0005\u0002\u0016]\t\t\u00111\u0001\u0002\u0004\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0004\u00051Q-];bYN$B!a\u000b\u0002B!I\u0011QC\r\u0002\u0002\u0003\u0007\u0011Q\u0002\u0002\t?J,w-\u001a=q)B\u0019!)a\u0012\n\t\u0005%\u00131\n\u0002\u0007%\u0016<W\t\u001f9\n\u0007\u00055#H\u0001\u0003CCN,'A\u0003+sC:\u001cH.\u0019;peN\u0019Q!a\u0015\u0011\u0007e\n)&C\u0002\u0002Xi\u0012abV8sI\n+'O]=TKRD\u0017\u000eK\u0006\u0006\u00037\n\t'a\u0019\u0002h\u0005%\u0004cA%\u0002^%\u0019\u0011qL\u0019\u0003\u0015\u0011,\u0007O]3dCR,G-A\u0004nKN\u001c\u0018mZ3\"\u0005\u0005\u0015\u0014!C!w_&$\u0017M\\2f\u0003\u0015\u0019\u0018N\\2fC\t\tY'\u0001\u00033]E\u0002\u0014A\u0003+sC:\u001cH.\u0019;peB\u0011!iB\n\u0006\u000f\u0005M\u00131\u000f\t\u0003\u0005\u0016!\"!a\u001c\u0016\u0003\t\u000bQ\u0001\\1oO\u0002\n\u0001\"\u00127f[:\u000bW.\u001a\t\u0003\u0005n\u0019RaGAA\u0003\u001b\u0003b!a!\u0002\nj\u000bUBAAC\u0015\r\t9)M\u0001\beVtG/[7f\u0013\u0011\tY)!\"\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0010\u0006UUBAAI\u0015\r\t\u0019\n`\u0001\u0003S>L1AVAI)\t\ti\bF\u0001y\u0003\u0015\t\u0007\u000f\u001d7z)\r\t\u0015q\u0014\u0005\u00061z\u0001\rAW\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t)+a+\u0011\t%\u000b9KW\u0005\u0004\u0003S\u000b$AB(qi&|g\u000e\u0003\u0005\u0002.~\t\t\u00111\u0001B\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003g\u00032!_A[\u0013\r\t9L\u001f\u0002\u0007\u001f\nTWm\u0019;\u0002\u000f%\u001cX*\u001b=fIR!\u00111FA_\u0011\u001d\ty,\ta\u0001\u0003\u0003\f!aY7\u0011\u0005UB3c\u0001\u0015\u0002FB\u0019\u0011*a2\n\u0007\u0005%\u0017G\u0001\u0004B]f\u0014VM\u001a\u000b\u0003\u0003\u0003\f1BY;jY\u0012\u001cFO]5oOR!\u0011\u0011[Am!\u0011\t\u0019.!6\u000f\u0005%\u001b\u0016bAAl/\ni1\u000b\u001e:j]\u001e\u0014U/\u001b7eKJDq!a7,\u0001\u0004\t\t.\u0001\u0002tE&J\u0001&a8\u0002d\u0006\u001d\u00181\u001e\u0006\u0004\u0003Cl\u0013aA!O3&\u0019\u0011Q]\u0017\u0003\u001f\u00113\u0015iQ8oi\u0016tG/T8eK2T1!!;.\u0003\u0015)U\n\u0015+Z\u0015\r\ti/L\u0001\u0007!\u000e#\u0015\tV!\u0002\u0019\r|g\u000e^1j]N$V\r\u001f;\u0015\t\u0005-\u00121\u001f\u0005\b\u0003\u007f\u0013\u0003\u0019AAa\u0003%9W\r\u001e'bE\u0016d7\u000f\u0006\u0003\u0002z\u0006}\b\u0003B.\u0002|jK1!!@b\u0005\r\u0019V\r\u001e\u0005\b\u0005\u0003\u0019\u0003\u0019AA#\u0003\u0005\u0011Hc\u0001.\u0003\u0006!9!\u0011\u0001\u0013A\u0002\u0005\u0015C\u0003\u0003B\u0005\u0005\u001f\u0011IBa\u0007\u0011\u0007%\u0013Y!C\u0002\u0003\u000eE\u0012A!\u00168ji\"9!\u0011C\u0013A\u0002\tM\u0011A\u0001:t!\u0019\tiB!\u0006\u0002F%!!qCA\u0010\u0005\r\u0019V-\u001d\u0005\b\u00037,\u0003\u0019AAi\u0011\u001d\u0011i\"\na\u0001\u0005?\t1a]3q!\rI%\u0011E\u0005\u0004\u0005G\t$\u0001B\"iCJ$b!!5\u0003(\t-\u0002b\u0002B\u0015M\u0001\u0007\u0011\u0011Y\u0001\u0002G\"9\u00111\u001c\u0014A\u0002\u0005EGCBAi\u0005_\u0011\t\u0004C\u0004\u0003\u0002\u001d\u0002\r!!\u0012\t\u000f\u0005mw\u00051\u0001\u0002R\u0002"
)
public abstract class ContentModel {
   public static Set getLabels(final Base.RegExp r) {
      return ContentModel$.MODULE$.getLabels(r);
   }

   public static boolean containsText(final ContentModel cm) {
      return ContentModel$.MODULE$.containsText(cm);
   }

   public static boolean isMixed(final ContentModel cm) {
      return ContentModel$.MODULE$.isMixed(cm);
   }

   public static WordExp.Wildcard$ Wildcard() {
      return ContentModel$.MODULE$.Wildcard();
   }

   public static WordExp.Letter$ Letter() {
      return ContentModel$.MODULE$.Letter();
   }

   public static Base.Eps$ Eps() {
      return ContentModel$.MODULE$.Eps();
   }

   public static Base.Star$ Star() {
      return ContentModel$.MODULE$.Star();
   }

   public static Base.Sequ$ Sequ() {
      return ContentModel$.MODULE$.Sequ();
   }

   public static Base.Alt$ Alt() {
      return ContentModel$.MODULE$.Alt();
   }

   public String toString() {
      return Utility$.MODULE$.sbToString((sb) -> {
         $anonfun$toString$1(this, sb);
         return BoxedUnit.UNIT;
      });
   }

   public abstract StringBuilder buildString(final StringBuilder sb);

   // $FF: synthetic method
   public static final void $anonfun$toString$1(final ContentModel $this, final StringBuilder sb) {
      $this.buildString(sb);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Translator$ extends WordBerrySethi implements Translator {
      public static final Translator$ MODULE$ = new Translator$();
      private static final ContentModel$ lang;

      static {
         lang = ContentModel$.MODULE$;
      }

      public ContentModel$ lang() {
         return lang;
      }
   }

   public static class ElemName extends WordExp.Label implements Product, Serializable {
      private final String name;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String name() {
         return this.name;
      }

      public String toString() {
         return (new java.lang.StringBuilder(12)).append("ElemName(\"").append(this.name()).append("\")").toString();
      }

      public ElemName copy(final String name) {
         return new ElemName(name);
      }

      public String copy$default$1() {
         return this.name();
      }

      public String productPrefix() {
         return "ElemName";
      }

      public int productArity() {
         return 1;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0:
               return this.name();
            default:
               return Statics.ioobe(x$1);
         }
      }

      public Iterator productIterator() {
         return .MODULE$.typedProductIterator(this);
      }

      public boolean canEqual(final Object x$1) {
         return x$1 instanceof ElemName;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0:
               return "name";
            default:
               return (String)Statics.ioobe(x$1);
         }
      }

      public int hashCode() {
         return .MODULE$._hashCode(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label47: {
               if (x$1 instanceof ElemName) {
                  label40: {
                     ElemName var4 = (ElemName)x$1;
                     String var10000 = this.name();
                     String var5 = var4.name();
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

      public ElemName(final String name) {
         this.name = name;
         Product.$init$(this);
      }
   }

   public static class ElemName$ extends AbstractFunction1 implements Serializable {
      public static final ElemName$ MODULE$ = new ElemName$();

      public final String toString() {
         return "ElemName";
      }

      public ElemName apply(final String name) {
         return new ElemName(name);
      }

      public Option unapply(final ElemName x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.name()));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ElemName$.class);
      }
   }

   /** @deprecated */
   public interface Translator {
   }
}
