package org.apache.spark.sql.catalyst;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055f\u0001\u0002\u0012$\u0001:B\u0001\u0002\u0012\u0001\u0003\u0006\u0004%I!\u0012\u0005\t#\u0002\u0011\t\u0012)A\u0005\r\")!\u000b\u0001C\u0001'\")q\u000b\u0001C\u00011\")1\f\u0001C\u00019\")a\f\u0001C\u0001?\")!\r\u0001C\u0001G\")\u0001\u000e\u0001C\u0001S\")1\u000e\u0001C\u0001Y\")a\u000e\u0001C\u0001_\")1\u000f\u0001C!i\")Q\u000f\u0001C\u0001\u000b\")a\u000f\u0001C\u0005o\"9!\u0010AA\u0001\n\u0003Y\bbB?\u0001#\u0003%\tA \u0005\t\u0003'\u00011\u0012!C\u0001\u000b\"I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u0003O\u0001\u0011\u0011!C\u0001\u0003SA\u0011\"!\r\u0001\u0003\u0003%\t!a\r\t\u0013\u0005}\u0002!!A\u0005B\u0005\u0005\u0003\"CA(\u0001\u0005\u0005I\u0011AA)\u0011%\tY\u0006AA\u0001\n\u0003\ni\u0006C\u0005\u0002b\u0001\t\t\u0011\"\u0011\u0002d!I\u0011Q\r\u0001\u0002\u0002\u0013\u0005\u0013qM\u0004\n\u0003W\u001a\u0013\u0011!E\u0001\u0003[2\u0001BI\u0012\u0002\u0002#\u0005\u0011q\u000e\u0005\u0007%j!\t!a\"\t\u0011MT\u0012\u0011!C#\u0003\u0013C\u0011\"a#\u001b\u0003\u0003%\t)!$\t\u0011\u0005E%$%A\u0005\u0002yD\u0011\"a%\u001b\u0003\u0003%\t)!&\t\u0011\u0005\u0005&$%A\u0005\u0002yD\u0011\"a)\u001b\u0003\u0003%I!!*\u0003\u001d]\u000bGn[3e)f\u0004X\rU1uQ*\u0011A%J\u0001\tG\u0006$\u0018\r\\=ti*\u0011aeJ\u0001\u0004gFd'B\u0001\u0015*\u0003\u0015\u0019\b/\u0019:l\u0015\tQ3&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002Y\u0005\u0019qN]4\u0004\u0001M!\u0001aL\u001b9!\t\u00014'D\u00012\u0015\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b2\u0005\u0019\te.\u001f*fMB\u0011\u0001GN\u0005\u0003oE\u0012q\u0001\u0015:pIV\u001cG\u000f\u0005\u0002:\u0003:\u0011!h\u0010\b\u0003wyj\u0011\u0001\u0010\u0006\u0003{5\na\u0001\u0010:p_Rt\u0014\"\u0001\u001a\n\u0005\u0001\u000b\u0014a\u00029bG.\fw-Z\u0005\u0003\u0005\u000e\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001Q\u0019\u0002\u0017]\fGn[3e!\u0006$\bn]\u000b\u0002\rB\u0019\u0011hR%\n\u0005!\u001b%aA*fcB\u0011!J\u0014\b\u0003\u00172\u0003\"aO\u0019\n\u00055\u000b\u0014A\u0002)sK\u0012,g-\u0003\u0002P!\n11\u000b\u001e:j]\u001eT!!T\u0019\u0002\u0019]\fGn[3e!\u0006$\bn\u001d\u0011\u0002\rqJg.\u001b;?)\t!f\u000b\u0005\u0002V\u00015\t1\u0005C\u0004E\u0007A\u0005\t\u0019\u0001$\u0002\u0015I,7m\u001c:e%>|G\u000f\u0006\u0002U3\")!\f\u0002a\u0001\u0013\u0006I1\r\\1tg:\u000bW.Z\u0001\re\u0016\u001cwN\u001d3PaRLwN\u001c\u000b\u0003)vCQAW\u0003A\u0002%\u000b1B]3d_J$\u0017I\u001d:bsR\u0011A\u000b\u0019\u0005\u0006C\u001a\u0001\r!S\u0001\u0011K2,W.\u001a8u\u00072\f7o\u001d(b[\u0016\f\u0011B]3d_J$W*\u00199\u0015\u0007Q#g\rC\u0003f\u000f\u0001\u0007\u0011*\u0001\u0007lKf\u001cE.Y:t\u001d\u0006lW\rC\u0003h\u000f\u0001\u0007\u0011*\u0001\bwC2,Xm\u00117bgNt\u0015-\\3\u0002\u001fI,7m\u001c:e\u0017\u0016Lhi\u001c:NCB$\"\u0001\u00166\t\u000b\u0015D\u0001\u0019A%\u0002#I,7m\u001c:e-\u0006dW/\u001a$pe6\u000b\u0007\u000f\u0006\u0002U[\")q-\u0003a\u0001\u0013\u0006Y!/Z2pe\u00124\u0015.\u001a7e)\r!\u0006/\u001d\u0005\u00065*\u0001\r!\u0013\u0005\u0006e*\u0001\r!S\u0001\nM&,G\u000e\u001a(b[\u0016\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002\u0013\u0006Aq-\u001a;QCRD7/A\u0006oK^Len\u001d;b]\u000e,GC\u0001+y\u0011\u0015IX\u00021\u0001J\u0003%qWm\u001e*fG>\u0014H-\u0001\u0003d_BLHC\u0001+}\u0011\u001d!e\u0002%AA\u0002\u0019\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001\u0000U\r1\u0015\u0011A\u0016\u0003\u0003\u0007\u0001B!!\u0002\u0002\u00105\u0011\u0011q\u0001\u0006\u0005\u0003\u0013\tY!A\u0005v]\u000eDWmY6fI*\u0019\u0011QB\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0012\u0005\u001d!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!r/\u00197lK\u0012\u0004\u0016\r\u001e5tI\u0005\u001c7-Z:tIA\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\r!\u0011\tY\"!\n\u000e\u0005\u0005u!\u0002BA\u0010\u0003C\tA\u0001\\1oO*\u0011\u00111E\u0001\u0005U\u00064\u0018-C\u0002P\u0003;\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\"!a\u000b\u0011\u0007A\ni#C\u0002\u00020E\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\u000e\u0002<A\u0019\u0001'a\u000e\n\u0007\u0005e\u0012GA\u0002B]fD\u0011\"!\u0010\u0014\u0003\u0003\u0005\r!a\u000b\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\u0005\u0005\u0004\u0002F\u0005-\u0013QG\u0007\u0003\u0003\u000fR1!!\u00132\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003\u001b\n9E\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA*\u00033\u00022\u0001MA+\u0013\r\t9&\r\u0002\b\u0005>|G.Z1o\u0011%\ti$FA\u0001\u0002\u0004\t)$\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA\r\u0003?B\u0011\"!\u0010\u0017\u0003\u0003\u0005\r!a\u000b\u0002\u0011!\f7\u000f[\"pI\u0016$\"!a\u000b\u0002\r\u0015\fX/\u00197t)\u0011\t\u0019&!\u001b\t\u0013\u0005u\u0002$!AA\u0002\u0005U\u0012AD,bY.,G\rV=qKB\u000bG\u000f\u001b\t\u0003+j\u0019RAGA9\u0003{\u0002b!a\u001d\u0002z\u0019#VBAA;\u0015\r\t9(M\u0001\beVtG/[7f\u0013\u0011\tY(!\u001e\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t\u0017\u0007\u0005\u0003\u0002\u0000\u0005\u0015UBAAA\u0015\u0011\t\u0019)!\t\u0002\u0005%|\u0017b\u0001\"\u0002\u0002R\u0011\u0011Q\u000e\u000b\u0003\u00033\tQ!\u00199qYf$2\u0001VAH\u0011\u001d!U\u0004%AA\u0002\u0019\u000bq\"\u00199qYf$C-\u001a4bk2$H%M\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\t9*!(\u0011\tA\nIJR\u0005\u0004\u00037\u000b$AB(qi&|g\u000e\u0003\u0005\u0002 ~\t\t\u00111\u0001U\u0003\rAH\u0005M\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u001d\u0006\u0003BA\u000e\u0003SKA!a+\u0002\u001e\t1qJ\u00196fGR\u0004"
)
public class WalkedTypePath implements Product, Serializable {
   private final Seq org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths;

   public static Seq $lessinit$greater$default$1() {
      return WalkedTypePath$.MODULE$.$lessinit$greater$default$1();
   }

   public static Option unapply(final WalkedTypePath x$0) {
      return WalkedTypePath$.MODULE$.unapply(x$0);
   }

   public static Seq apply$default$1() {
      return WalkedTypePath$.MODULE$.apply$default$1();
   }

   public static WalkedTypePath apply(final Seq walkedPaths) {
      return WalkedTypePath$.MODULE$.apply(walkedPaths);
   }

   public static Function1 andThen(final Function1 g) {
      return WalkedTypePath$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return WalkedTypePath$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Seq walkedPaths$access$0() {
      return this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths;
   }

   public Seq org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths() {
      return this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths;
   }

   public WalkedTypePath recordRoot(final String className) {
      return this.newInstance("- root class: \"" + className + "\"");
   }

   public WalkedTypePath recordOption(final String className) {
      return this.newInstance("- option value class: \"" + className + "\"");
   }

   public WalkedTypePath recordArray(final String elementClassName) {
      return this.newInstance("- array element class: \"" + elementClassName + "\"");
   }

   public WalkedTypePath recordMap(final String keyClassName, final String valueClassName) {
      return this.newInstance("- map key class: \"" + keyClassName + "\", value class: \"" + valueClassName + "\"");
   }

   public WalkedTypePath recordKeyForMap(final String keyClassName) {
      return this.newInstance("- map key class: \"" + keyClassName + "\"");
   }

   public WalkedTypePath recordValueForMap(final String valueClassName) {
      return this.newInstance("- map value class: \"" + valueClassName + "\"");
   }

   public WalkedTypePath recordField(final String className, final String fieldName) {
      return this.newInstance("- field (class: \"" + className + "\", name: \"" + fieldName + "\")");
   }

   public String toString() {
      return this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths().mkString("\n");
   }

   public Seq getPaths() {
      return this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths();
   }

   private WalkedTypePath newInstance(final String newRecord) {
      return new WalkedTypePath((Seq)this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths().$plus$colon(newRecord));
   }

   public WalkedTypePath copy(final Seq walkedPaths) {
      return new WalkedTypePath(walkedPaths);
   }

   public Seq copy$default$1() {
      return this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths();
   }

   public String productPrefix() {
      return "WalkedTypePath";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.walkedPaths$access$0();
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
      return x$1 instanceof WalkedTypePath;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "walkedPaths";
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
      boolean var6;
      if (this != x$1) {
         label47: {
            if (x$1 instanceof WalkedTypePath) {
               label40: {
                  WalkedTypePath var4 = (WalkedTypePath)x$1;
                  Seq var10000 = this.walkedPaths$access$0();
                  Seq var5 = var4.walkedPaths$access$0();
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

   public WalkedTypePath(final Seq walkedPaths) {
      this.org$apache$spark$sql$catalyst$WalkedTypePath$$walkedPaths = walkedPaths;
      Product.$init$(this);
   }
}
