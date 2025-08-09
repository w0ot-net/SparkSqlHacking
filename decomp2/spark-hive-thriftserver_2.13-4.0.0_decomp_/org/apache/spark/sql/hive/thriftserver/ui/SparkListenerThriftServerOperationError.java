package org.apache.spark.sql.hive.thriftserver.ui;

import java.io.Serializable;
import org.apache.spark.scheduler.SparkListenerEvent;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005f!B\u0010!\u0001\nr\u0003\u0002C&\u0001\u0005+\u0007I\u0011\u0001'\t\u0011U\u0003!\u0011#Q\u0001\n5C\u0001B\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t/\u0002\u0011\t\u0012)A\u0005\u001b\"A\u0001\f\u0001BK\u0002\u0013\u0005A\n\u0003\u0005Z\u0001\tE\t\u0015!\u0003N\u0011!Q\u0006A!f\u0001\n\u0003Y\u0006\u0002C0\u0001\u0005#\u0005\u000b\u0011\u0002/\t\u000b\u0001\u0004A\u0011A1\t\u000f!\u0004\u0011\u0011!C\u0001S\"9a\u000eAI\u0001\n\u0003y\u0007b\u0002>\u0001#\u0003%\ta\u001c\u0005\bw\u0002\t\n\u0011\"\u0001p\u0011\u001da\b!%A\u0005\u0002uD\u0001b \u0001\u0002\u0002\u0013\u0005\u0013\u0011\u0001\u0005\n\u0003#\u0001\u0011\u0011!C\u0001\u0003'A\u0011\"a\u0007\u0001\u0003\u0003%\t!!\b\t\u0013\u0005%\u0002!!A\u0005B\u0005-\u0002\"CA\u001d\u0001\u0005\u0005I\u0011AA\u001e\u0011%\t)\u0005AA\u0001\n\u0003\n9\u0005C\u0005\u0002L\u0001\t\t\u0011\"\u0011\u0002N!I\u0011q\n\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000b\u0005\n\u0003'\u0002\u0011\u0011!C!\u0003+:!\"!\u0017!\u0003\u0003E\tAIA.\r%y\u0002%!A\t\u0002\t\ni\u0006\u0003\u0004a3\u0011\u0005\u0011Q\u000f\u0005\n\u0003\u001fJ\u0012\u0011!C#\u0003#B\u0011\"a\u001e\u001a\u0003\u0003%\t)!\u001f\t\u0013\u0005\r\u0015$!A\u0005\u0002\u0006\u0015\u0005\"CAL3\u0005\u0005I\u0011BAM\u0005\u001d\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u0016\u0013(o\u001c:\u000b\u0005\u0005\u0012\u0013AA;j\u0015\t\u0019C%\u0001\u0007uQJLg\r^:feZ,'O\u0003\u0002&M\u0005!\u0001.\u001b<f\u0015\t9\u0003&A\u0002tc2T!!\u000b\u0016\u0002\u000bM\u0004\u0018M]6\u000b\u0005-b\u0013AB1qC\u000eDWMC\u0001.\u0003\ry'oZ\n\u0006\u0001=*4H\u0010\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005YJT\"A\u001c\u000b\u0005aB\u0013!C:dQ\u0016$W\u000f\\3s\u0013\tQtG\u0001\nTa\u0006\u00148\u000eT5ti\u0016tWM]#wK:$\bC\u0001\u0019=\u0013\ti\u0014GA\u0004Qe>$Wo\u0019;\u0011\u0005}BeB\u0001!G\u001d\t\tU)D\u0001C\u0015\t\u0019E)\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0011\u0014BA$2\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0013&\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u001d\u000b\u0014AA5e+\u0005i\u0005C\u0001(S\u001d\ty\u0005\u000b\u0005\u0002Bc%\u0011\u0011+M\u0001\u0007!J,G-\u001a4\n\u0005M#&AB*ue&twM\u0003\u0002Rc\u0005\u0019\u0011\u000e\u001a\u0011\u0002\u0011\u0015\u0014(o\u001c:Ng\u001e\f\u0011\"\u001a:s_Jl5o\u001a\u0011\u0002\u0015\u0015\u0014(o\u001c:Ue\u0006\u001cW-A\u0006feJ|'\u000f\u0016:bG\u0016\u0004\u0013A\u00034j]&\u001c\b\u000eV5nKV\tA\f\u0005\u00021;&\u0011a,\r\u0002\u0005\u0019>tw-A\u0006gS:L7\u000f\u001b+j[\u0016\u0004\u0013A\u0002\u001fj]&$h\bF\u0003cI\u00164w\r\u0005\u0002d\u00015\t\u0001\u0005C\u0003L\u0013\u0001\u0007Q\nC\u0003W\u0013\u0001\u0007Q\nC\u0003Y\u0013\u0001\u0007Q\nC\u0003[\u0013\u0001\u0007A,\u0001\u0003d_BLH#\u00022kW2l\u0007bB&\u000b!\u0003\u0005\r!\u0014\u0005\b-*\u0001\n\u00111\u0001N\u0011\u001dA&\u0002%AA\u00025CqA\u0017\u0006\u0011\u0002\u0003\u0007A,\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003AT#!T9,\u0003I\u0004\"a\u001d=\u000e\u0003QT!!\u001e<\u0002\u0013Ut7\r[3dW\u0016$'BA<2\u0003)\tgN\\8uCRLwN\\\u0005\u0003sR\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0003yT#\u0001X9\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t\u0019\u0001\u0005\u0003\u0002\u0006\u0005=QBAA\u0004\u0015\u0011\tI!a\u0003\u0002\t1\fgn\u001a\u0006\u0003\u0003\u001b\tAA[1wC&\u00191+a\u0002\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0005\u0005U\u0001c\u0001\u0019\u0002\u0018%\u0019\u0011\u0011D\u0019\u0003\u0007%sG/\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005}\u0011Q\u0005\t\u0004a\u0005\u0005\u0012bAA\u0012c\t\u0019\u0011I\\=\t\u0013\u0005\u001d\u0012#!AA\u0002\u0005U\u0011a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002.A1\u0011qFA\u001b\u0003?i!!!\r\u000b\u0007\u0005M\u0012'\u0001\u0006d_2dWm\u0019;j_:LA!a\u000e\u00022\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti$a\u0011\u0011\u0007A\ny$C\u0002\u0002BE\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002(M\t\t\u00111\u0001\u0002 \u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\t\u0019!!\u0013\t\u0013\u0005\u001dB#!AA\u0002\u0005U\u0011\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0005\u0005U\u0011\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\r\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002>\u0005]\u0003\"CA\u0014/\u0005\u0005\t\u0019AA\u0010\u0003\u001d\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8feRC'/\u001b4u'\u0016\u0014h/\u001a:Pa\u0016\u0014\u0018\r^5p]\u0016\u0013(o\u001c:\u0011\u0005\rL2#B\r\u0002`\u0005-\u0004#CA1\u0003OjU*\u0014/c\u001b\t\t\u0019GC\u0002\u0002fE\nqA];oi&lW-\u0003\u0003\u0002j\u0005\r$!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oiA!\u0011QNA:\u001b\t\tyG\u0003\u0003\u0002r\u0005-\u0011AA5p\u0013\rI\u0015q\u000e\u000b\u0003\u00037\nQ!\u00199qYf$\u0012BYA>\u0003{\ny(!!\t\u000b-c\u0002\u0019A'\t\u000bYc\u0002\u0019A'\t\u000bac\u0002\u0019A'\t\u000bic\u0002\u0019\u0001/\u0002\u000fUt\u0017\r\u001d9msR!\u0011qQAJ!\u0015\u0001\u0014\u0011RAG\u0013\r\tY)\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000fA\ny)T'N9&\u0019\u0011\u0011S\u0019\u0003\rQ+\b\u000f\\35\u0011!\t)*HA\u0001\u0002\u0004\u0011\u0017a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0014\t\u0005\u0003\u000b\ti*\u0003\u0003\u0002 \u0006\u001d!AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerThriftServerOperationError implements SparkListenerEvent, Product, Serializable {
   private final String id;
   private final String errorMsg;
   private final String errorTrace;
   private final long finishTime;

   public static Option unapply(final SparkListenerThriftServerOperationError x$0) {
      return SparkListenerThriftServerOperationError$.MODULE$.unapply(x$0);
   }

   public static SparkListenerThriftServerOperationError apply(final String id, final String errorMsg, final String errorTrace, final long finishTime) {
      return SparkListenerThriftServerOperationError$.MODULE$.apply(id, errorMsg, errorTrace, finishTime);
   }

   public static Function1 tupled() {
      return SparkListenerThriftServerOperationError$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerThriftServerOperationError$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String id() {
      return this.id;
   }

   public String errorMsg() {
      return this.errorMsg;
   }

   public String errorTrace() {
      return this.errorTrace;
   }

   public long finishTime() {
      return this.finishTime;
   }

   public SparkListenerThriftServerOperationError copy(final String id, final String errorMsg, final String errorTrace, final long finishTime) {
      return new SparkListenerThriftServerOperationError(id, errorMsg, errorTrace, finishTime);
   }

   public String copy$default$1() {
      return this.id();
   }

   public String copy$default$2() {
      return this.errorMsg();
   }

   public String copy$default$3() {
      return this.errorTrace();
   }

   public long copy$default$4() {
      return this.finishTime();
   }

   public String productPrefix() {
      return "SparkListenerThriftServerOperationError";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.id();
         }
         case 1 -> {
            return this.errorMsg();
         }
         case 2 -> {
            return this.errorTrace();
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.finishTime());
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
      return x$1 instanceof SparkListenerThriftServerOperationError;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "id";
         }
         case 1 -> {
            return "errorMsg";
         }
         case 2 -> {
            return "errorTrace";
         }
         case 3 -> {
            return "finishTime";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.id()));
      var1 = Statics.mix(var1, Statics.anyHash(this.errorMsg()));
      var1 = Statics.mix(var1, Statics.anyHash(this.errorTrace()));
      var1 = Statics.mix(var1, Statics.longHash(this.finishTime()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label67: {
            if (x$1 instanceof SparkListenerThriftServerOperationError) {
               SparkListenerThriftServerOperationError var4 = (SparkListenerThriftServerOperationError)x$1;
               if (this.finishTime() == var4.finishTime()) {
                  label60: {
                     String var10000 = this.id();
                     String var5 = var4.id();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label60;
                     }

                     var10000 = this.errorMsg();
                     String var6 = var4.errorMsg();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label60;
                     }

                     var10000 = this.errorTrace();
                     String var7 = var4.errorTrace();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label60;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label60;
                     }

                     if (var4.canEqual(this)) {
                        break label67;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }
      }

      var10 = true;
      return var10;
   }

   public SparkListenerThriftServerOperationError(final String id, final String errorMsg, final String errorTrace, final long finishTime) {
      this.id = id;
      this.errorMsg = errorMsg;
      this.errorTrace = errorTrace;
      this.finishTime = finishTime;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
