package org.apache.spark.scheduler;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.scheduler.cluster.ExecutorInfo;
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
   bytes = "\u0006\u0005\u0005me\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011!\t\u0006A!f\u0001\n\u0003\u0011\u0006\u0002C-\u0001\u0005#\u0005\u000b\u0011B*\t\u000bi\u0003A\u0011A.\t\u000f\u0001\u0004\u0011\u0011!C\u0001C\"9Q\rAI\u0001\n\u00031\u0007bB9\u0001#\u0003%\tA\u001d\u0005\bi\u0002\t\n\u0011\"\u0001v\u0011\u001d9\b!!A\u0005BaD\u0011\"!\u0001\u0001\u0003\u0003%\t!a\u0001\t\u0013\u0005-\u0001!!A\u0005\u0002\u00055\u0001\"CA\r\u0001\u0005\u0005I\u0011IA\u000e\u0011%\tI\u0003AA\u0001\n\u0003\tY\u0003C\u0005\u00026\u0001\t\t\u0011\"\u0011\u00028!I\u00111\b\u0001\u0002\u0002\u0013\u0005\u0013Q\b\u0005\n\u0003\u007f\u0001\u0011\u0011!C!\u0003\u0003B\u0011\"a\u0011\u0001\u0003\u0003%\t%!\u0012\b\u0013\u0005US$!A\t\u0002\u0005]c\u0001\u0003\u000f\u001e\u0003\u0003E\t!!\u0017\t\ri3B\u0011AA9\u0011%\tyDFA\u0001\n\u000b\n\t\u0005C\u0005\u0002tY\t\t\u0011\"!\u0002v!I\u0011Q\u0010\f\u0002\u0002\u0013\u0005\u0015q\u0010\u0005\n\u0003#3\u0012\u0011!C\u0005\u0003'\u0013!d\u00159be.d\u0015n\u001d;f]\u0016\u0014X\t_3dkR|'/\u00113eK\u0012T!AH\u0010\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(B\u0001\u0011\"\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00113%\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002I\u0005\u0019qN]4\u0004\u0001M)\u0001aJ\u00172iA\u0011\u0001fK\u0007\u0002S)\t!&A\u0003tG\u0006d\u0017-\u0003\u0002-S\t1\u0011I\\=SK\u001a\u0004\"AL\u0018\u000e\u0003uI!\u0001M\u000f\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003QIJ!aM\u0015\u0003\u000fA\u0013x\u000eZ;diB\u0011Q'\u0010\b\u0003mmr!a\u000e\u001e\u000e\u0003aR!!O\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0013B\u0001\u001f*\u0003\u001d\u0001\u0018mY6bO\u0016L!AP \u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005qJ\u0013\u0001\u0002;j[\u0016,\u0012A\u0011\t\u0003Q\rK!\u0001R\u0015\u0003\t1{gnZ\u0001\u0006i&lW\rI\u0001\u000bKb,7-\u001e;pe&#W#\u0001%\u0011\u0005%keB\u0001&L!\t9\u0014&\u0003\u0002MS\u00051\u0001K]3eK\u001aL!AT(\u0003\rM#(/\u001b8h\u0015\ta\u0015&A\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012\u0004\u0013\u0001D3yK\u000e,Ho\u001c:J]\u001a|W#A*\u0011\u0005Q;V\"A+\u000b\u0005Yk\u0012aB2mkN$XM]\u0005\u00031V\u0013A\"\u0012=fGV$xN]%oM>\fQ\"\u001a=fGV$xN]%oM>\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0003];z{\u0006C\u0001\u0018\u0001\u0011\u0015\u0001u\u00011\u0001C\u0011\u00151u\u00011\u0001I\u0011\u0015\tv\u00011\u0001T\u0003\u0011\u0019w\u000e]=\u0015\tq\u00137\r\u001a\u0005\b\u0001\"\u0001\n\u00111\u0001C\u0011\u001d1\u0005\u0002%AA\u0002!Cq!\u0015\u0005\u0011\u0002\u0003\u00071+\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0003\u001dT#A\u00115,\u0003%\u0004\"A[8\u000e\u0003-T!\u0001\\7\u0002\u0013Ut7\r[3dW\u0016$'B\u00018*\u0003)\tgN\\8uCRLwN\\\u0005\u0003a.\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\u0012a\u001d\u0016\u0003\u0011\"\fabY8qs\u0012\"WMZ1vYR$3'F\u0001wU\t\u0019\u0006.A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0002sB\u0011!p`\u0007\u0002w*\u0011A0`\u0001\u0005Y\u0006twMC\u0001\u007f\u0003\u0011Q\u0017M^1\n\u00059[\u0018\u0001\u00049s_\u0012,8\r^!sSRLXCAA\u0003!\rA\u0013qA\u0005\u0004\u0003\u0013I#aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA\b\u0003+\u00012\u0001KA\t\u0013\r\t\u0019\"\u000b\u0002\u0004\u0003:L\b\"CA\f\u001d\u0005\u0005\t\u0019AA\u0003\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0004\t\u0007\u0003?\t)#a\u0004\u000e\u0005\u0005\u0005\"bAA\u0012S\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u001d\u0012\u0011\u0005\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002.\u0005M\u0002c\u0001\u0015\u00020%\u0019\u0011\u0011G\u0015\u0003\u000f\t{w\u000e\\3b]\"I\u0011q\u0003\t\u0002\u0002\u0003\u0007\u0011qB\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\rF\u0002z\u0003sA\u0011\"a\u0006\u0012\u0003\u0003\u0005\r!!\u0002\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0002\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012!_\u0001\u0007KF,\u0018\r\\:\u0015\t\u00055\u0012q\t\u0005\n\u0003/!\u0012\u0011!a\u0001\u0003\u001fA3\u0001AA&!\u0011\ti%!\u0015\u000e\u0005\u0005=#B\u00018 \u0013\u0011\t\u0019&a\u0014\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u00025M\u0003\u0018M]6MSN$XM\\3s\u000bb,7-\u001e;pe\u0006#G-\u001a3\u0011\u0005922#\u0002\f\u0002\\\u0005\u001d\u0004\u0003CA/\u0003G\u0012\u0005j\u0015/\u000e\u0005\u0005}#bAA1S\u00059!/\u001e8uS6,\u0017\u0002BA3\u0003?\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\tI'a\u001c\u000e\u0005\u0005-$bAA7{\u0006\u0011\u0011n\\\u0005\u0004}\u0005-DCAA,\u0003\u0015\t\u0007\u000f\u001d7z)\u001da\u0016qOA=\u0003wBQ\u0001Q\rA\u0002\tCQAR\rA\u0002!CQ!U\rA\u0002M\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002\u0002\u00065\u0005#\u0002\u0015\u0002\u0004\u0006\u001d\u0015bAACS\t1q\n\u001d;j_:\u0004b\u0001KAE\u0005\"\u001b\u0016bAAFS\t1A+\u001e9mKNB\u0001\"a$\u001b\u0003\u0003\u0005\r\u0001X\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAK!\rQ\u0018qS\u0005\u0004\u00033[(AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerExecutorAdded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String executorId;
   private final ExecutorInfo executorInfo;

   public static Option unapply(final SparkListenerExecutorAdded x$0) {
      return SparkListenerExecutorAdded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerExecutorAdded apply(final long time, final String executorId, final ExecutorInfo executorInfo) {
      return SparkListenerExecutorAdded$.MODULE$.apply(time, executorId, executorInfo);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorAdded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorAdded$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public long time() {
      return this.time;
   }

   public String executorId() {
      return this.executorId;
   }

   public ExecutorInfo executorInfo() {
      return this.executorInfo;
   }

   public SparkListenerExecutorAdded copy(final long time, final String executorId, final ExecutorInfo executorInfo) {
      return new SparkListenerExecutorAdded(time, executorId, executorInfo);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public ExecutorInfo copy$default$3() {
      return this.executorInfo();
   }

   public String productPrefix() {
      return "SparkListenerExecutorAdded";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 1 -> {
            return this.executorId();
         }
         case 2 -> {
            return this.executorInfo();
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
      return x$1 instanceof SparkListenerExecutorAdded;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "time";
         }
         case 1 -> {
            return "executorId";
         }
         case 2 -> {
            return "executorInfo";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.executorInfo()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label59: {
            if (x$1 instanceof SparkListenerExecutorAdded) {
               SparkListenerExecutorAdded var4 = (SparkListenerExecutorAdded)x$1;
               if (this.time() == var4.time()) {
                  label52: {
                     String var10000 = this.executorId();
                     String var5 = var4.executorId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label52;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label52;
                     }

                     ExecutorInfo var7 = this.executorInfo();
                     ExecutorInfo var6 = var4.executorInfo();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label52;
                        }
                     } else if (!var7.equals(var6)) {
                        break label52;
                     }

                     if (var4.canEqual(this)) {
                        break label59;
                     }
                  }
               }
            }

            var8 = false;
            return var8;
         }
      }

      var8 = true;
      return var8;
   }

   public SparkListenerExecutorAdded(final long time, final String executorId, final ExecutorInfo executorInfo) {
      this.time = time;
      this.executorId = executorId;
      this.executorInfo = executorInfo;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
