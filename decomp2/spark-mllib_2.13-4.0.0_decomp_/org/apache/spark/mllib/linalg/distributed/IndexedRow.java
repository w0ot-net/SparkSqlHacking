package org.apache.spark.mllib.linalg.distributed;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]d\u0001B\r\u001b\u0001\u001eB\u0001\"\u0010\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\u0005\u0002\u0011\t\u0012)A\u0005\u007f!A1\t\u0001BK\u0002\u0013\u0005A\t\u0003\u0005J\u0001\tE\t\u0015!\u0003F\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u001d\u0001\u0006!!A\u0005\u0002ECq\u0001\u0016\u0001\u0012\u0002\u0013\u0005Q\u000bC\u0004a\u0001E\u0005I\u0011A1\t\u000f\r\u0004\u0011\u0011!C!I\"9Q\u000eAA\u0001\n\u0003q\u0007b\u0002:\u0001\u0003\u0003%\ta\u001d\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011%\t\u0019\u0001AA\u0001\n\u0003\t)\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u00033\u0001\u0011\u0011!C!\u00037A\u0011\"!\b\u0001\u0003\u0003%\t%a\b\b\u0013\u0005M\"$!A\t\u0002\u0005Ub\u0001C\r\u001b\u0003\u0003E\t!a\u000e\t\r)\u001bB\u0011AA(\u0011%\tIbEA\u0001\n\u000b\nY\u0002C\u0005\u0002RM\t\t\u0011\"!\u0002T!I\u0011\u0011L\n\u0002\u0002\u0013\u0005\u00151\f\u0005\n\u0003[\u001a\u0012\u0011!C\u0005\u0003_\u0012!\"\u00138eKb,GMU8x\u0015\tYB$A\u0006eSN$(/\u001b2vi\u0016$'BA\u000f\u001f\u0003\u0019a\u0017N\\1mO*\u0011q\u0004I\u0001\u0006[2d\u0017N\u0019\u0006\u0003C\t\nQa\u001d9be.T!a\t\u0013\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0013aA8sO\u000e\u00011\u0003\u0002\u0001)]E\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00150\u0013\t\u0001$FA\u0004Qe>$Wo\u0019;\u0011\u0005IRdBA\u001a9\u001d\t!t'D\u00016\u0015\t1d%\u0001\u0004=e>|GOP\u0005\u0002W%\u0011\u0011HK\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002:U\u0005)\u0011N\u001c3fqV\tq\b\u0005\u0002*\u0001&\u0011\u0011I\u000b\u0002\u0005\u0019>tw-\u0001\u0004j]\u0012,\u0007\u0010I\u0001\u0007m\u0016\u001cGo\u001c:\u0016\u0003\u0015\u0003\"AR$\u000e\u0003qI!\u0001\u0013\u000f\u0003\rY+7\r^8s\u0003\u001d1Xm\u0019;pe\u0002\na\u0001P5oSRtDc\u0001'O\u001fB\u0011Q\nA\u0007\u00025!)Q(\u0002a\u0001\u007f!)1)\u0002a\u0001\u000b\u0006!1m\u001c9z)\ra%k\u0015\u0005\b{\u0019\u0001\n\u00111\u0001@\u0011\u001d\u0019e\u0001%AA\u0002\u0015\u000babY8qs\u0012\"WMZ1vYR$\u0013'F\u0001WU\tytkK\u0001Y!\tIf,D\u0001[\u0015\tYF,A\u0005v]\u000eDWmY6fI*\u0011QLK\u0001\u000bC:tw\u000e^1uS>t\u0017BA0[\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0005\u0011'FA#X\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tQ\r\u0005\u0002gW6\tqM\u0003\u0002iS\u0006!A.\u00198h\u0015\u0005Q\u0017\u0001\u00026bm\u0006L!\u0001\\4\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\u0005y\u0007CA\u0015q\u0013\t\t(FA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0002uoB\u0011\u0011&^\u0005\u0003m*\u00121!\u00118z\u0011\u001dA8\"!AA\u0002=\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014X#A>\u0011\u0007q|H/D\u0001~\u0015\tq(&\u0001\u0006d_2dWm\u0019;j_:L1!!\u0001~\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u001d\u0011Q\u0002\t\u0004S\u0005%\u0011bAA\u0006U\t9!i\\8mK\u0006t\u0007b\u0002=\u000e\u0003\u0003\u0005\r\u0001^\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002f\u0003'Aq\u0001\u001f\b\u0002\u0002\u0003\u0007q.\u0001\u0005iCND7i\u001c3f)\u0005y\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015\fa!Z9vC2\u001cH\u0003BA\u0004\u0003CAq\u0001_\t\u0002\u0002\u0003\u0007A\u000fK\u0003\u0001\u0003K\ty\u0003\u0005\u0003\u0002(\u0005-RBAA\u0015\u0015\ti\u0006%\u0003\u0003\u0002.\u0005%\"!B*j]\u000e,\u0017EAA\u0019\u0003\u0015\td\u0006\r\u00181\u0003)Ie\u000eZ3yK\u0012\u0014vn\u001e\t\u0003\u001bN\u0019RaEA\u001d\u0003\u000b\u0002r!a\u000f\u0002B}*E*\u0004\u0002\u0002>)\u0019\u0011q\b\u0016\u0002\u000fI,h\u000e^5nK&!\u00111IA\u001f\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\t\u0005\u0003\u000f\ni%\u0004\u0002\u0002J)\u0019\u00111J5\u0002\u0005%|\u0017bA\u001e\u0002JQ\u0011\u0011QG\u0001\u0006CB\u0004H.\u001f\u000b\u0006\u0019\u0006U\u0013q\u000b\u0005\u0006{Y\u0001\ra\u0010\u0005\u0006\u0007Z\u0001\r!R\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti&!\u001b\u0011\u000b%\ny&a\u0019\n\u0007\u0005\u0005$F\u0001\u0004PaRLwN\u001c\t\u0006S\u0005\u0015t(R\u0005\u0004\u0003OR#A\u0002+va2,'\u0007\u0003\u0005\u0002l]\t\t\u00111\u0001M\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003c\u00022AZA:\u0013\r\t)h\u001a\u0002\u0007\u001f\nTWm\u0019;"
)
public class IndexedRow implements Product, Serializable {
   private final long index;
   private final Vector vector;

   public static Option unapply(final IndexedRow x$0) {
      return IndexedRow$.MODULE$.unapply(x$0);
   }

   public static IndexedRow apply(final long index, final Vector vector) {
      return IndexedRow$.MODULE$.apply(index, vector);
   }

   public static Function1 tupled() {
      return IndexedRow$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return IndexedRow$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long index() {
      return this.index;
   }

   public Vector vector() {
      return this.vector;
   }

   public IndexedRow copy(final long index, final Vector vector) {
      return new IndexedRow(index, vector);
   }

   public long copy$default$1() {
      return this.index();
   }

   public Vector copy$default$2() {
      return this.vector();
   }

   public String productPrefix() {
      return "IndexedRow";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.index());
         }
         case 1 -> {
            return this.vector();
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
      return x$1 instanceof IndexedRow;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "index";
         }
         case 1 -> {
            return "vector";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.index()));
      var1 = Statics.mix(var1, Statics.anyHash(this.vector()));
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label51: {
            if (x$1 instanceof IndexedRow) {
               IndexedRow var4 = (IndexedRow)x$1;
               if (this.index() == var4.index()) {
                  label44: {
                     Vector var10000 = this.vector();
                     Vector var5 = var4.vector();
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

   public IndexedRow(final long index, final Vector vector) {
      this.index = index;
      this.vector = vector;
      Product.$init$(this);
   }
}
