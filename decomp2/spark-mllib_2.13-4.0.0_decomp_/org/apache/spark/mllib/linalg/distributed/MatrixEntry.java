package org.apache.spark.mllib.linalg.distributed;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de\u0001\u0002\u000f\u001e\u0001*B\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005H\u0001\tE\t\u0015!\u0003C\u0011!A\u0005A!f\u0001\n\u0003I\u0005\u0002C'\u0001\u0005#\u0005\u000b\u0011\u0002&\t\u000b9\u0003A\u0011A(\t\u000fU\u0003\u0011\u0011!C\u0001-\"9!\fAI\u0001\n\u0003Y\u0006b\u00024\u0001#\u0003%\ta\u0017\u0005\bO\u0002\t\n\u0011\"\u0001i\u0011\u001dQ\u0007!!A\u0005B-Dq\u0001\u001e\u0001\u0002\u0002\u0013\u0005Q\u000fC\u0004z\u0001\u0005\u0005I\u0011\u0001>\t\u0013\u0005\u0005\u0001!!A\u0005B\u0005\r\u0001\"CA\t\u0001\u0005\u0005I\u0011AA\n\u0011%\ti\u0002AA\u0001\n\u0003\ny\u0002C\u0005\u0002$\u0001\t\t\u0011\"\u0011\u0002&!I\u0011q\u0005\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0006\u0005\n\u0003W\u0001\u0011\u0011!C!\u0003[9\u0011\"!\u0011\u001e\u0003\u0003E\t!a\u0011\u0007\u0011qi\u0012\u0011!E\u0001\u0003\u000bBaA\u0014\f\u0005\u0002\u0005u\u0003\"CA\u0014-\u0005\u0005IQIA\u0015\u0011%\tyFFA\u0001\n\u0003\u000b\t\u0007C\u0005\u0002jY\t\t\u0011\"!\u0002l!I\u0011Q\u0010\f\u0002\u0002\u0013%\u0011q\u0010\u0002\f\u001b\u0006$(/\u001b=F]R\u0014\u0018P\u0003\u0002\u001f?\u0005YA-[:ue&\u0014W\u000f^3e\u0015\t\u0001\u0013%\u0001\u0004mS:\fGn\u001a\u0006\u0003E\r\nQ!\u001c7mS\nT!\u0001J\u0013\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0019:\u0013AB1qC\u000eDWMC\u0001)\u0003\ry'oZ\u0002\u0001'\u0011\u00011&\r\u001b\u0011\u00051zS\"A\u0017\u000b\u00039\nQa]2bY\u0006L!\u0001M\u0017\u0003\r\u0005s\u0017PU3g!\ta#'\u0003\u00024[\t9\u0001K]8ek\u000e$\bCA\u001b>\u001d\t14H\u0004\u00028u5\t\u0001H\u0003\u0002:S\u00051AH]8pizJ\u0011AL\u0005\u0003y5\nq\u0001]1dW\u0006<W-\u0003\u0002?\u007f\ta1+\u001a:jC2L'0\u00192mK*\u0011A(L\u0001\u0002SV\t!\t\u0005\u0002-\u0007&\u0011A)\f\u0002\u0005\u0019>tw-\u0001\u0002jA\u0005\t!.\u0001\u0002kA\u0005)a/\u00197vKV\t!\n\u0005\u0002-\u0017&\u0011A*\f\u0002\u0007\t>,(\r\\3\u0002\rY\fG.^3!\u0003\u0019a\u0014N\\5u}Q!\u0001KU*U!\t\t\u0006!D\u0001\u001e\u0011\u0015\u0001u\u00011\u0001C\u0011\u00151u\u00011\u0001C\u0011\u0015Au\u00011\u0001K\u0003\u0011\u0019w\u000e]=\u0015\tA;\u0006,\u0017\u0005\b\u0001\"\u0001\n\u00111\u0001C\u0011\u001d1\u0005\u0002%AA\u0002\tCq\u0001\u0013\u0005\u0011\u0002\u0003\u0007!*\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003qS#AQ/,\u0003y\u0003\"a\u00183\u000e\u0003\u0001T!!\u00192\u0002\u0013Ut7\r[3dW\u0016$'BA2.\u0003)\tgN\\8uCRLwN\\\u0005\u0003K\u0002\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'F\u0001jU\tQU,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002YB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\u0005Y\u0006twMC\u0001r\u0003\u0011Q\u0017M^1\n\u0005Mt'AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180F\u0001w!\tas/\u0003\u0002y[\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u00111P \t\u0003YqL!!`\u0017\u0003\u0007\u0005s\u0017\u0010C\u0004\u0000\u001d\u0005\u0005\t\u0019\u0001<\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t)\u0001E\u0003\u0002\b\u0005510\u0004\u0002\u0002\n)\u0019\u00111B\u0017\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\u0010\u0005%!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!!\u0006\u0002\u001cA\u0019A&a\u0006\n\u0007\u0005eQFA\u0004C_>dW-\u00198\t\u000f}\u0004\u0012\u0011!a\u0001w\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\ra\u0017\u0011\u0005\u0005\b\u007fF\t\t\u00111\u0001w\u0003!A\u0017m\u001d5D_\u0012,G#\u0001<\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001\\\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005U\u0011q\u0006\u0005\b\u007fR\t\t\u00111\u0001|Q\u0015\u0001\u00111GA\u001f!\u0011\t)$!\u000f\u000e\u0005\u0005]\"BA2$\u0013\u0011\tY$a\u000e\u0003\u000bMKgnY3\"\u0005\u0005}\u0012!B\u0019/a9\u0002\u0014aC'biJL\u00070\u00128uef\u0004\"!\u0015\f\u0014\u000bY\t9%a\u0015\u0011\u0011\u0005%\u0013q\n\"C\u0015Bk!!a\u0013\u000b\u0007\u00055S&A\u0004sk:$\u0018.\\3\n\t\u0005E\u00131\n\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003BA+\u00037j!!a\u0016\u000b\u0007\u0005e\u0003/\u0001\u0002j_&\u0019a(a\u0016\u0015\u0005\u0005\r\u0013!B1qa2LHc\u0002)\u0002d\u0005\u0015\u0014q\r\u0005\u0006\u0001f\u0001\rA\u0011\u0005\u0006\rf\u0001\rA\u0011\u0005\u0006\u0011f\u0001\rAS\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\ti'!\u001f\u0011\u000b1\ny'a\u001d\n\u0007\u0005ETF\u0001\u0004PaRLwN\u001c\t\u0007Y\u0005U$I\u0011&\n\u0007\u0005]TF\u0001\u0004UkBdWm\r\u0005\t\u0003wR\u0012\u0011!a\u0001!\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0005\u0005cA7\u0002\u0004&\u0019\u0011Q\u00118\u0003\r=\u0013'.Z2u\u0001"
)
public class MatrixEntry implements Product, Serializable {
   private final long i;
   private final long j;
   private final double value;

   public static Option unapply(final MatrixEntry x$0) {
      return MatrixEntry$.MODULE$.unapply(x$0);
   }

   public static MatrixEntry apply(final long i, final long j, final double value) {
      return MatrixEntry$.MODULE$.apply(i, j, value);
   }

   public static Function1 tupled() {
      return MatrixEntry$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return MatrixEntry$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long i() {
      return this.i;
   }

   public long j() {
      return this.j;
   }

   public double value() {
      return this.value;
   }

   public MatrixEntry copy(final long i, final long j, final double value) {
      return new MatrixEntry(i, j, value);
   }

   public long copy$default$1() {
      return this.i();
   }

   public long copy$default$2() {
      return this.j();
   }

   public double copy$default$3() {
      return this.value();
   }

   public String productPrefix() {
      return "MatrixEntry";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.i());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.j());
         }
         case 2 -> {
            return BoxesRunTime.boxToDouble(this.value());
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
      return x$1 instanceof MatrixEntry;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "i";
         }
         case 1 -> {
            return "j";
         }
         case 2 -> {
            return "value";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.i()));
      var1 = Statics.mix(var1, Statics.longHash(this.j()));
      var1 = Statics.mix(var1, Statics.doubleHash(this.value()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label40: {
            if (x$1 instanceof MatrixEntry) {
               MatrixEntry var4 = (MatrixEntry)x$1;
               if (this.i() == var4.i() && this.j() == var4.j() && this.value() == var4.value() && var4.canEqual(this)) {
                  break label40;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public MatrixEntry(final long i, final long j, final double value) {
      this.i = i;
      this.j = j;
      this.value = value;
      Product.$init$(this);
   }
}
