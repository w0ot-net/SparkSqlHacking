package org.apache.spark.storage;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Md\u0001B\r\u001b\u0001\u000eB\u0001B\u000f\u0001\u0003\u0016\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0012)A\u0005y!A\u0001\t\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005F\u0001\tE\t\u0015!\u0003C\u0011\u00151\u0005\u0001\"\u0001H\u0011\u0015Y\u0005\u0001\"\u0011M\u0011\u001d)\u0006!!A\u0005\u0002YCq!\u0017\u0001\u0012\u0002\u0013\u0005!\fC\u0004f\u0001E\u0005I\u0011\u00014\t\u000f!\u0004\u0011\u0011!C!S\"9\u0011\u000fAA\u0001\n\u0003Y\u0004b\u0002:\u0001\u0003\u0003%\ta\u001d\u0005\bs\u0002\t\t\u0011\"\u0011{\u0011%\t\u0019\u0001AA\u0001\n\u0003\t)\u0001C\u0005\u0002\u0010\u0001\t\t\u0011\"\u0011\u0002\u0012!I\u0011Q\u0003\u0001\u0002\u0002\u0013\u0005\u0013q\u0003\u0005\n\u00033\u0001\u0011\u0011!C!\u000379\u0011\"a\u000b\u001b\u0003\u0003E\t!!\f\u0007\u0011eQ\u0012\u0011!E\u0001\u0003_AaAR\n\u0005\u0002\u0005\u001d\u0003\"CA%'\u0005\u0005IQIA&\u0011%\tieEA\u0001\n\u0003\u000by\u0005C\u0005\u0002VM\t\t\u0011\"!\u0002X!I\u0011\u0011N\n\u0002\u0002\u0013%\u00111\u000e\u0002\u0014!f$\bn\u001c8TiJ,\u0017-\u001c\"m_\u000e\\\u0017\n\u001a\u0006\u00037q\tqa\u001d;pe\u0006<WM\u0003\u0002\u001e=\u0005)1\u000f]1sW*\u0011q\u0004I\u0001\u0007CB\f7\r[3\u000b\u0003\u0005\n1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u0013)]A\u0011QEJ\u0007\u00025%\u0011qE\u0007\u0002\b\u00052|7m[%e!\tIC&D\u0001+\u0015\u0005Y\u0013!B:dC2\f\u0017BA\u0017+\u0005\u001d\u0001&o\u001c3vGR\u0004\"aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a#\u0003\u0019a$o\\8u}%\t1&\u0003\u00027U\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001d:\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t1$&\u0001\u0005tiJ,\u0017-\\%e+\u0005a\u0004CA\u0015>\u0013\tq$FA\u0002J]R\f\u0011b\u001d;sK\u0006l\u0017\n\u001a\u0011\u0002\u0011Ut\u0017.];f\u0013\u0012,\u0012A\u0011\t\u0003S\rK!\u0001\u0012\u0016\u0003\t1{gnZ\u0001\nk:L\u0017/^3JI\u0002\na\u0001P5oSRtDc\u0001%J\u0015B\u0011Q\u0005\u0001\u0005\u0006u\u0015\u0001\r\u0001\u0010\u0005\u0006\u0001\u0016\u0001\rAQ\u0001\u0005]\u0006lW-F\u0001N!\tq%K\u0004\u0002P!B\u0011\u0011GK\u0005\u0003#*\na\u0001\u0015:fI\u00164\u0017BA*U\u0005\u0019\u0019FO]5oO*\u0011\u0011KK\u0001\u0005G>\u0004\u0018\u0010F\u0002I/bCqAO\u0004\u0011\u0002\u0003\u0007A\bC\u0004A\u000fA\u0005\t\u0019\u0001\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\t1L\u000b\u0002=9.\nQ\f\u0005\u0002_G6\tqL\u0003\u0002aC\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003E*\n!\"\u00198o_R\fG/[8o\u0013\t!wLA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001hU\t\u0011E,A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002UB\u00111\u000e]\u0007\u0002Y*\u0011QN\\\u0001\u0005Y\u0006twMC\u0001p\u0003\u0011Q\u0017M^1\n\u0005Mc\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0003i^\u0004\"!K;\n\u0005YT#aA!os\"9\u0001\u0010DA\u0001\u0002\u0004a\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/F\u0001|!\rax\u0010^\u0007\u0002{*\u0011aPK\u0001\u000bG>dG.Z2uS>t\u0017bAA\u0001{\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9!!\u0004\u0011\u0007%\nI!C\u0002\u0002\f)\u0012qAQ8pY\u0016\fg\u000eC\u0004y\u001d\u0005\u0005\t\u0019\u0001;\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004U\u0006M\u0001b\u0002=\u0010\u0003\u0003\u0005\r\u0001P\u0001\tQ\u0006\u001c\bnQ8eKR\tA(\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u000f\ti\u0002C\u0004y#\u0005\u0005\t\u0019\u0001;)\u0007\u0001\t\t\u0003\u0005\u0003\u0002$\u0005\u001dRBAA\u0013\u0015\t\u0011G$\u0003\u0003\u0002*\u0005\u0015\"\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017a\u0005)zi\"|gn\u0015;sK\u0006l'\t\\8dW&#\u0007CA\u0013\u0014'\u0015\u0019\u0012\u0011GA\u001f!\u001d\t\u0019$!\u000f=\u0005\"k!!!\u000e\u000b\u0007\u0005]\"&A\u0004sk:$\u0018.\\3\n\t\u0005m\u0012Q\u0007\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA \u0003\u000bj!!!\u0011\u000b\u0007\u0005\rc.\u0001\u0002j_&\u0019\u0001(!\u0011\u0015\u0005\u00055\u0012\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003)\fQ!\u00199qYf$R\u0001SA)\u0003'BQA\u000f\fA\u0002qBQ\u0001\u0011\fA\u0002\t\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002Z\u0005\u0015\u0004#B\u0015\u0002\\\u0005}\u0013bAA/U\t1q\n\u001d;j_:\u0004R!KA1y\tK1!a\u0019+\u0005\u0019!V\u000f\u001d7fe!A\u0011qM\f\u0002\u0002\u0003\u0007\u0001*A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!\u001c\u0011\u0007-\fy'C\u0002\u0002r1\u0014aa\u00142kK\u000e$\b"
)
public class PythonStreamBlockId extends BlockId implements Product, Serializable {
   private final int streamId;
   private final long uniqueId;

   public static Option unapply(final PythonStreamBlockId x$0) {
      return PythonStreamBlockId$.MODULE$.unapply(x$0);
   }

   public static PythonStreamBlockId apply(final int streamId, final long uniqueId) {
      return PythonStreamBlockId$.MODULE$.apply(streamId, uniqueId);
   }

   public static Function1 tupled() {
      return PythonStreamBlockId$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return PythonStreamBlockId$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int streamId() {
      return this.streamId;
   }

   public long uniqueId() {
      return this.uniqueId;
   }

   public String name() {
      int var10000 = this.streamId();
      return "python-stream-" + var10000 + "-" + this.uniqueId();
   }

   public PythonStreamBlockId copy(final int streamId, final long uniqueId) {
      return new PythonStreamBlockId(streamId, uniqueId);
   }

   public int copy$default$1() {
      return this.streamId();
   }

   public long copy$default$2() {
      return this.uniqueId();
   }

   public String productPrefix() {
      return "PythonStreamBlockId";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.streamId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.uniqueId());
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
      return x$1 instanceof PythonStreamBlockId;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "streamId";
         }
         case 1 -> {
            return "uniqueId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.streamId());
      var1 = Statics.mix(var1, Statics.longHash(this.uniqueId()));
      return Statics.finalizeHash(var1, 2);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof PythonStreamBlockId) {
               PythonStreamBlockId var4 = (PythonStreamBlockId)x$1;
               if (this.streamId() == var4.streamId() && this.uniqueId() == var4.uniqueId() && var4.canEqual(this)) {
                  break label38;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public PythonStreamBlockId(final int streamId, final long uniqueId) {
      this.streamId = streamId;
      this.uniqueId = uniqueId;
      Product.$init$(this);
   }
}
