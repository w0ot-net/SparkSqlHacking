package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed!\u0002\u000f\u001e\u0001v)\u0003\u0002\u0003!\u0001\u0005+\u0007I\u0011A!\t\u0011)\u0003!\u0011#Q\u0001\n\tC\u0001b\u0013\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0005\"AQ\n\u0001BK\u0002\u0013\u0005\u0011\t\u0003\u0005O\u0001\tE\t\u0015!\u0003C\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u001d)\u0006!!A\u0005\u0002YCqA\u0017\u0001\u0012\u0002\u0013\u00051\fC\u0004g\u0001E\u0005I\u0011A.\t\u000f\u001d\u0004\u0011\u0013!C\u00017\"9\u0001\u000eAA\u0001\n\u0003J\u0007bB9\u0001\u0003\u0003%\tA\u001d\u0005\bm\u0002\t\t\u0011\"\u0001x\u0011\u001di\b!!A\u0005ByD\u0011\"a\u0003\u0001\u0003\u0003%\t!!\u0004\t\u0013\u0005]\u0001!!A\u0005B\u0005e\u0001\"CA\u000f\u0001\u0005\u0005I\u0011IA\u0010\u0011%\t\t\u0003AA\u0001\n\u0003\n\u0019\u0003C\u0005\u0002&\u0001\t\t\u0011\"\u0011\u0002(\u001dQ\u00111F\u000f\u0002\u0002#\u0005Q$!\f\u0007\u0013qi\u0012\u0011!E\u0001;\u0005=\u0002BB(\u0017\t\u0003\t9\u0005C\u0005\u0002\"Y\t\t\u0011\"\u0012\u0002$!I\u0011\u0011\n\f\u0002\u0002\u0013\u0005\u00151\n\u0005\n\u0003'2\u0012\u0011!CA\u0003+B\u0011\"a\u001a\u0017\u0003\u0003%I!!\u001b\u0003\u001b]{'o[3s%\u0016lwN^3e\u0015\tqr$A\u0005tG\",G-\u001e7fe*\u0011\u0001%I\u0001\u0006gB\f'o\u001b\u0006\u0003E\r\na!\u00199bG\",'\"\u0001\u0013\u0002\u0007=\u0014xmE\u0003\u0001M1\u00024\u0007\u0005\u0002(U5\t\u0001FC\u0001*\u0003\u0015\u00198-\u00197b\u0013\tY\u0003F\u0001\u0004B]f\u0014VM\u001a\t\u0003[9j\u0011!H\u0005\u0003_u\u0011\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t9\u0013'\u0003\u00023Q\t9\u0001K]8ek\u000e$\bC\u0001\u001b>\u001d\t)4H\u0004\u00027u5\tqG\u0003\u00029s\u00051AH]8piz\u001a\u0001!C\u0001*\u0013\ta\u0004&A\u0004qC\u000e\\\u0017mZ3\n\u0005yz$\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u001f)\u0003!9xN]6fe&#W#\u0001\"\u0011\u0005\r;eB\u0001#F!\t1\u0004&\u0003\u0002GQ\u00051\u0001K]3eK\u001aL!\u0001S%\u0003\rM#(/\u001b8h\u0015\t1\u0005&A\u0005x_J\\WM]%eA\u0005!\u0001n\\:u\u0003\u0015Awn\u001d;!\u0003\u001diWm]:bO\u0016\f\u0001\"\\3tg\u0006<W\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\tE\u00136\u000b\u0016\t\u0003[\u0001AQ\u0001Q\u0004A\u0002\tCQaS\u0004A\u0002\tCQ!T\u0004A\u0002\t\u000bAaY8qsR!\u0011k\u0016-Z\u0011\u001d\u0001\u0005\u0002%AA\u0002\tCqa\u0013\u0005\u0011\u0002\u0003\u0007!\tC\u0004N\u0011A\u0005\t\u0019\u0001\"\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAL\u000b\u0002C;.\na\f\u0005\u0002`I6\t\u0001M\u0003\u0002bE\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003G\"\n!\"\u00198o_R\fG/[8o\u0013\t)\u0007MA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u0005Q\u0007CA6q\u001b\u0005a'BA7o\u0003\u0011a\u0017M\\4\u000b\u0003=\fAA[1wC&\u0011\u0001\n\\\u0001\raJ|G-^2u\u0003JLG/_\u000b\u0002gB\u0011q\u0005^\u0005\u0003k\"\u00121!\u00138u\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001_>\u0011\u0005\u001dJ\u0018B\u0001>)\u0005\r\te.\u001f\u0005\by:\t\t\u00111\u0001t\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\u0010E\u0003\u0002\u0002\u0005\u001d\u00010\u0004\u0002\u0002\u0004)\u0019\u0011Q\u0001\u0015\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002\n\u0005\r!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0004\u0002\u0016A\u0019q%!\u0005\n\u0007\u0005M\u0001FA\u0004C_>dW-\u00198\t\u000fq\u0004\u0012\u0011!a\u0001q\u0006\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\rQ\u00171\u0004\u0005\byF\t\t\u00111\u0001t\u0003!A\u0017m\u001d5D_\u0012,G#A:\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012A[\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005=\u0011\u0011\u0006\u0005\byR\t\t\u00111\u0001y\u000359vN]6feJ+Wn\u001c<fIB\u0011QFF\n\u0006-\u0005E\u0012Q\b\t\t\u0003g\tID\u0011\"C#6\u0011\u0011Q\u0007\u0006\u0004\u0003oA\u0013a\u0002:v]RLW.Z\u0005\u0005\u0003w\t)DA\tBEN$(/Y2u\rVt7\r^5p]N\u0002B!a\u0010\u0002F5\u0011\u0011\u0011\t\u0006\u0004\u0003\u0007r\u0017AA5p\u0013\rq\u0014\u0011\t\u000b\u0003\u0003[\tQ!\u00199qYf$r!UA'\u0003\u001f\n\t\u0006C\u0003A3\u0001\u0007!\tC\u0003L3\u0001\u0007!\tC\u0003N3\u0001\u0007!)A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005]\u00131\r\t\u0006O\u0005e\u0013QL\u0005\u0004\u00037B#AB(qi&|g\u000e\u0005\u0004(\u0003?\u0012%IQ\u0005\u0004\u0003CB#A\u0002+va2,7\u0007\u0003\u0005\u0002fi\t\t\u00111\u0001R\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003W\u00022a[A7\u0013\r\ty\u0007\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class WorkerRemoved implements DAGSchedulerEvent, Product, Serializable {
   private final String workerId;
   private final String host;
   private final String message;

   public static Option unapply(final WorkerRemoved x$0) {
      return WorkerRemoved$.MODULE$.unapply(x$0);
   }

   public static WorkerRemoved apply(final String workerId, final String host, final String message) {
      return WorkerRemoved$.MODULE$.apply(workerId, host, message);
   }

   public static Function1 tupled() {
      return WorkerRemoved$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return WorkerRemoved$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String workerId() {
      return this.workerId;
   }

   public String host() {
      return this.host;
   }

   public String message() {
      return this.message;
   }

   public WorkerRemoved copy(final String workerId, final String host, final String message) {
      return new WorkerRemoved(workerId, host, message);
   }

   public String copy$default$1() {
      return this.workerId();
   }

   public String copy$default$2() {
      return this.host();
   }

   public String copy$default$3() {
      return this.message();
   }

   public String productPrefix() {
      return "WorkerRemoved";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.workerId();
         }
         case 1 -> {
            return this.host();
         }
         case 2 -> {
            return this.message();
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
      return x$1 instanceof WorkerRemoved;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "workerId";
         }
         case 1 -> {
            return "host";
         }
         case 2 -> {
            return "message";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof WorkerRemoved) {
               label56: {
                  WorkerRemoved var4 = (WorkerRemoved)x$1;
                  String var10000 = this.workerId();
                  String var5 = var4.workerId();
                  if (var10000 == null) {
                     if (var5 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var5)) {
                     break label56;
                  }

                  var10000 = this.host();
                  String var6 = var4.host();
                  if (var10000 == null) {
                     if (var6 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var6)) {
                     break label56;
                  }

                  var10000 = this.message();
                  String var7 = var4.message();
                  if (var10000 == null) {
                     if (var7 != null) {
                        break label56;
                     }
                  } else if (!var10000.equals(var7)) {
                     break label56;
                  }

                  if (var4.canEqual(this)) {
                     break label63;
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

   public WorkerRemoved(final String workerId, final String host, final String message) {
      this.workerId = workerId;
      this.host = host;
      this.message = message;
      Product.$init$(this);
   }
}
