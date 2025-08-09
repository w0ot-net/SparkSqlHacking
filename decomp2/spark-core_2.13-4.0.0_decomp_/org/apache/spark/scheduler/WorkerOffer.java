package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005Eg!B\u0016-\u0001:\"\u0004\u0002C&\u0001\u0005+\u0007I\u0011\u0001'\t\u0011U\u0003!\u0011#Q\u0001\n5C\u0001B\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0014\u0005\t/\u0002\u0011\t\u0012)A\u0005\u001b\"A\u0001\f\u0001BK\u0002\u0013\u0005\u0011\f\u0003\u0005^\u0001\tE\t\u0015!\u0003[\u0011!q\u0006A!f\u0001\n\u0003y\u0006\u0002C2\u0001\u0005#\u0005\u000b\u0011\u00021\t\u0011\u0011\u0004!Q3A\u0005\u0002\u0015D\u0001B\u001b\u0001\u0003\u0012\u0003\u0006IA\u001a\u0005\tW\u0002\u0011)\u001a!C\u00013\"AA\u000e\u0001B\tB\u0003%!\fC\u0003n\u0001\u0011\u0005a\u000eC\u0004w\u0001\u0005\u0005I\u0011A<\t\u000fy\u0004\u0011\u0013!C\u0001\u007f\"A\u0011Q\u0003\u0001\u0012\u0002\u0013\u0005q\u0010C\u0005\u0002\u0018\u0001\t\n\u0011\"\u0001\u0002\u001a!I\u0011Q\u0004\u0001\u0012\u0002\u0013\u0005\u0011q\u0004\u0005\n\u0003G\u0001\u0011\u0013!C\u0001\u0003KA\u0011\"!\u000b\u0001#\u0003%\t!!\u0007\t\u0013\u0005-\u0002!!A\u0005B\u00055\u0002\u0002CA\u001f\u0001\u0005\u0005I\u0011A-\t\u0013\u0005}\u0002!!A\u0005\u0002\u0005\u0005\u0003\"CA'\u0001\u0005\u0005I\u0011IA(\u0011%\ti\u0006AA\u0001\n\u0003\ty\u0006C\u0005\u0002j\u0001\t\t\u0011\"\u0011\u0002l!I\u0011q\u000e\u0001\u0002\u0002\u0013\u0005\u0013\u0011\u000f\u0005\n\u0003g\u0002\u0011\u0011!C!\u0003kB\u0011\"a\u001e\u0001\u0003\u0003%\t%!\u001f\b\u0015\u0005uD&!A\t\u00029\nyHB\u0005,Y\u0005\u0005\t\u0012\u0001\u0018\u0002\u0002\"1Qn\bC\u0001\u00033C\u0011\"a\u001d \u0003\u0003%)%!\u001e\t\u0013\u0005mu$!A\u0005\u0002\u0006u\u0005\"CAV?E\u0005I\u0011AA\u0010\u0011%\tikHI\u0001\n\u0003\t)\u0003C\u0005\u00020~\t\n\u0011\"\u0001\u0002\u001a!I\u0011\u0011W\u0010\u0002\u0002\u0013\u0005\u00151\u0017\u0005\n\u0003\u0003|\u0012\u0013!C\u0001\u0003?A\u0011\"a1 #\u0003%\t!!\n\t\u0013\u0005\u0015w$%A\u0005\u0002\u0005e\u0001\"CAd?\u0005\u0005I\u0011BAe\u0005-9vN]6fe>3g-\u001a:\u000b\u00055r\u0013!C:dQ\u0016$W\u000f\\3s\u0015\ty\u0003'A\u0003ta\u0006\u00148N\u0003\u00022e\u00051\u0011\r]1dQ\u0016T\u0011aM\u0001\u0004_J<7\u0003\u0002\u00016wy\u0002\"AN\u001d\u000e\u0003]R\u0011\u0001O\u0001\u0006g\u000e\fG.Y\u0005\u0003u]\u0012a!\u00118z%\u00164\u0007C\u0001\u001c=\u0013\titGA\u0004Qe>$Wo\u0019;\u0011\u0005}BeB\u0001!G\u001d\t\tU)D\u0001C\u0015\t\u0019E)\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0014BA$8\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0013&\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\u001d;\u0014AC3yK\u000e,Ho\u001c:JIV\tQ\n\u0005\u0002O%:\u0011q\n\u0015\t\u0003\u0003^J!!U\u001c\u0002\rA\u0013X\rZ3g\u0013\t\u0019FK\u0001\u0004TiJLgn\u001a\u0006\u0003#^\n1\"\u001a=fGV$xN]%eA\u0005!\u0001n\\:u\u0003\u0015Awn\u001d;!\u0003\u0015\u0019wN]3t+\u0005Q\u0006C\u0001\u001c\\\u0013\tavGA\u0002J]R\faaY8sKN\u0004\u0013aB1eIJ,7o]\u000b\u0002AB\u0019a'Y'\n\u0005\t<$AB(qi&|g.\u0001\u0005bI\u0012\u0014Xm]:!\u0003%\u0011Xm]8ve\u000e,7/F\u0001g!\t9\u0007.D\u0001-\u0013\tIGF\u0001\rFq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKN\fUn\\;oiN\f!B]3t_V\u00148-Z:!\u0003E\u0011Xm]8ve\u000e,\u0007K]8gS2,\u0017\nZ\u0001\u0013e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\b_B\f(o\u001d;v!\t9\u0007\u0001C\u0003L\u001b\u0001\u0007Q\nC\u0003W\u001b\u0001\u0007Q\nC\u0003Y\u001b\u0001\u0007!\fC\u0004_\u001bA\u0005\t\u0019\u00011\t\u000f\u0011l\u0001\u0013!a\u0001M\"91.\u0004I\u0001\u0002\u0004Q\u0016\u0001B2paf$ra\u001c=zundX\u0010C\u0004L\u001dA\u0005\t\u0019A'\t\u000fYs\u0001\u0013!a\u0001\u001b\"9\u0001L\u0004I\u0001\u0002\u0004Q\u0006b\u00020\u000f!\u0003\u0005\r\u0001\u0019\u0005\bI:\u0001\n\u00111\u0001g\u0011\u001dYg\u0002%AA\u0002i\u000babY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0002\u0002)\u001aQ*a\u0001,\u0005\u0005\u0015\u0001\u0003BA\u0004\u0003#i!!!\u0003\u000b\t\u0005-\u0011QB\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\u00048\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003'\tIAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005m!f\u0001.\u0002\u0004\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\"TCAA\u0011U\r\u0001\u00171A\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00136+\t\t9CK\u0002g\u0003\u0007\tabY8qs\u0012\"WMZ1vYR$c'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003_\u0001B!!\r\u0002<5\u0011\u00111\u0007\u0006\u0005\u0003k\t9$\u0001\u0003mC:<'BAA\u001d\u0003\u0011Q\u0017M^1\n\u0007M\u000b\u0019$\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005\r\u0013\u0011\n\t\u0004m\u0005\u0015\u0013bAA$o\t\u0019\u0011I\\=\t\u0011\u0005-s#!AA\u0002i\u000b1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA)!\u0019\t\u0019&!\u0017\u0002D5\u0011\u0011Q\u000b\u0006\u0004\u0003/:\u0014AC2pY2,7\r^5p]&!\u00111LA+\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u0005\u0005\u0014q\r\t\u0004m\u0005\r\u0014bAA3o\t9!i\\8mK\u0006t\u0007\"CA&3\u0005\u0005\t\u0019AA\"\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005=\u0012Q\u000e\u0005\t\u0003\u0017R\u0012\u0011!a\u00015\u0006A\u0001.Y:i\u0007>$W\rF\u0001[\u0003!!xn\u0015;sS:<GCAA\u0018\u0003\u0019)\u0017/^1mgR!\u0011\u0011MA>\u0011%\tY%HA\u0001\u0002\u0004\t\u0019%A\u0006X_J\\WM](gM\u0016\u0014\bCA4 '\u0015y\u00121QAH!-\t))a#N\u001bj\u0003gMW8\u000e\u0005\u0005\u001d%bAAEo\u00059!/\u001e8uS6,\u0017\u0002BAG\u0003\u000f\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c87!\u0011\t\t*a&\u000e\u0005\u0005M%\u0002BAK\u0003o\t!![8\n\u0007%\u000b\u0019\n\u0006\u0002\u0002\u0000\u0005)\u0011\r\u001d9msRiq.a(\u0002\"\u0006\r\u0016QUAT\u0003SCQa\u0013\u0012A\u00025CQA\u0016\u0012A\u00025CQ\u0001\u0017\u0012A\u0002iCqA\u0018\u0012\u0011\u0002\u0003\u0007\u0001\rC\u0004eEA\u0005\t\u0019\u00014\t\u000f-\u0014\u0003\u0013!a\u00015\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00136\u0003=\t\u0007\u000f\u001d7zI\u0011,g-Y;mi\u00122\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003k\u000bi\f\u0005\u00037C\u0006]\u0006#\u0003\u001c\u0002:6k%\f\u00194[\u0013\r\tYl\u000e\u0002\u0007)V\u0004H.\u001a\u001c\t\u0011\u0005}f%!AA\u0002=\f1\u0001\u001f\u00131\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%i\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIU\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u00122\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAf!\u0011\t\t$!4\n\t\u0005=\u00171\u0007\u0002\u0007\u001f\nTWm\u0019;"
)
public class WorkerOffer implements Product, Serializable {
   private final String executorId;
   private final String host;
   private final int cores;
   private final Option address;
   private final ExecutorResourcesAmounts resources;
   private final int resourceProfileId;

   public static int $lessinit$greater$default$6() {
      return WorkerOffer$.MODULE$.$lessinit$greater$default$6();
   }

   public static ExecutorResourcesAmounts $lessinit$greater$default$5() {
      return WorkerOffer$.MODULE$.$lessinit$greater$default$5();
   }

   public static Option $lessinit$greater$default$4() {
      return WorkerOffer$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final WorkerOffer x$0) {
      return WorkerOffer$.MODULE$.unapply(x$0);
   }

   public static int apply$default$6() {
      return WorkerOffer$.MODULE$.apply$default$6();
   }

   public static ExecutorResourcesAmounts apply$default$5() {
      return WorkerOffer$.MODULE$.apply$default$5();
   }

   public static Option apply$default$4() {
      return WorkerOffer$.MODULE$.apply$default$4();
   }

   public static WorkerOffer apply(final String executorId, final String host, final int cores, final Option address, final ExecutorResourcesAmounts resources, final int resourceProfileId) {
      return WorkerOffer$.MODULE$.apply(executorId, host, cores, address, resources, resourceProfileId);
   }

   public static Function1 tupled() {
      return WorkerOffer$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return WorkerOffer$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public String executorId() {
      return this.executorId;
   }

   public String host() {
      return this.host;
   }

   public int cores() {
      return this.cores;
   }

   public Option address() {
      return this.address;
   }

   public ExecutorResourcesAmounts resources() {
      return this.resources;
   }

   public int resourceProfileId() {
      return this.resourceProfileId;
   }

   public WorkerOffer copy(final String executorId, final String host, final int cores, final Option address, final ExecutorResourcesAmounts resources, final int resourceProfileId) {
      return new WorkerOffer(executorId, host, cores, address, resources, resourceProfileId);
   }

   public String copy$default$1() {
      return this.executorId();
   }

   public String copy$default$2() {
      return this.host();
   }

   public int copy$default$3() {
      return this.cores();
   }

   public Option copy$default$4() {
      return this.address();
   }

   public ExecutorResourcesAmounts copy$default$5() {
      return this.resources();
   }

   public int copy$default$6() {
      return this.resourceProfileId();
   }

   public String productPrefix() {
      return "WorkerOffer";
   }

   public int productArity() {
      return 6;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.executorId();
         }
         case 1 -> {
            return this.host();
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.cores());
         }
         case 3 -> {
            return this.address();
         }
         case 4 -> {
            return this.resources();
         }
         case 5 -> {
            return BoxesRunTime.boxToInteger(this.resourceProfileId());
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
      return x$1 instanceof WorkerOffer;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "executorId";
         }
         case 1 -> {
            return "host";
         }
         case 2 -> {
            return "cores";
         }
         case 3 -> {
            return "address";
         }
         case 4 -> {
            return "resources";
         }
         case 5 -> {
            return "resourceProfileId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.host()));
      var1 = Statics.mix(var1, this.cores());
      var1 = Statics.mix(var1, Statics.anyHash(this.address()));
      var1 = Statics.mix(var1, Statics.anyHash(this.resources()));
      var1 = Statics.mix(var1, this.resourceProfileId());
      return Statics.finalizeHash(var1, 6);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label79: {
            if (x$1 instanceof WorkerOffer) {
               WorkerOffer var4 = (WorkerOffer)x$1;
               if (this.cores() == var4.cores() && this.resourceProfileId() == var4.resourceProfileId()) {
                  label72: {
                     String var10000 = this.executorId();
                     String var5 = var4.executorId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     var10000 = this.host();
                     String var6 = var4.host();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label72;
                     }

                     Option var10 = this.address();
                     Option var7 = var4.address();
                     if (var10 == null) {
                        if (var7 != null) {
                           break label72;
                        }
                     } else if (!var10.equals(var7)) {
                        break label72;
                     }

                     ExecutorResourcesAmounts var11 = this.resources();
                     ExecutorResourcesAmounts var8 = var4.resources();
                     if (var11 == null) {
                        if (var8 != null) {
                           break label72;
                        }
                     } else if (!var11.equals(var8)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        break label79;
                     }
                  }
               }
            }

            var12 = false;
            return var12;
         }
      }

      var12 = true;
      return var12;
   }

   public WorkerOffer(final String executorId, final String host, final int cores, final Option address, final ExecutorResourcesAmounts resources, final int resourceProfileId) {
      this.executorId = executorId;
      this.host = host;
      this.cores = cores;
      this.address = address;
      this.resources = resources;
      this.resourceProfileId = resourceProfileId;
      Product.$init$(this);
   }
}
