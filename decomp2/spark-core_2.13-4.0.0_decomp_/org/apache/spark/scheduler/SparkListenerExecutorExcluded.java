package org.apache.spark.scheduler;

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
   bytes = "\u0006\u0005\u0005ee\u0001\u0002\u000f\u001e\u0001\u001aB\u0001\u0002\u0011\u0001\u0003\u0016\u0004%\t!\u0011\u0005\t\u000b\u0002\u0011\t\u0012)A\u0005\u0005\"Aa\t\u0001BK\u0002\u0013\u0005q\t\u0003\u0005Q\u0001\tE\t\u0015!\u0003I\u0011!\t\u0006A!f\u0001\n\u0003\u0011\u0006\u0002\u0003,\u0001\u0005#\u0005\u000b\u0011B*\t\u000b]\u0003A\u0011\u0001-\t\u000fu\u0003\u0011\u0011!C\u0001=\"9!\rAI\u0001\n\u0003\u0019\u0007b\u00028\u0001#\u0003%\ta\u001c\u0005\bc\u0002\t\n\u0011\"\u0001s\u0011\u001d!\b!!A\u0005BUDq! \u0001\u0002\u0002\u0013\u0005!\u000bC\u0004\u007f\u0001\u0005\u0005I\u0011A@\t\u0013\u0005-\u0001!!A\u0005B\u00055\u0001\"CA\u000e\u0001\u0005\u0005I\u0011AA\u000f\u0011%\t9\u0003AA\u0001\n\u0003\nI\u0003C\u0005\u0002.\u0001\t\t\u0011\"\u0011\u00020!I\u0011\u0011\u0007\u0001\u0002\u0002\u0013\u0005\u00131\u0007\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003o9\u0011\"a\u0015\u001e\u0003\u0003E\t!!\u0016\u0007\u0011qi\u0012\u0011!E\u0001\u0003/Baa\u0016\f\u0005\u0002\u0005=\u0004\"CA\u0019-\u0005\u0005IQIA\u001a\u0011%\t\tHFA\u0001\n\u0003\u000b\u0019\bC\u0005\u0002|Y\t\t\u0011\"!\u0002~!I\u0011q\u0012\f\u0002\u0002\u0013%\u0011\u0011\u0013\u0002\u001e'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s\u000bb\u001cG.\u001e3fI*\u0011adH\u0001\ng\u000eDW\rZ;mKJT!\u0001I\u0011\u0002\u000bM\u0004\u0018M]6\u000b\u0005\t\u001a\u0013AB1qC\u000eDWMC\u0001%\u0003\ry'oZ\u0002\u0001'\u0015\u0001q%L\u00195!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011afL\u0007\u0002;%\u0011\u0001'\b\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u0002)e%\u00111'\u000b\u0002\b!J|G-^2u!\t)TH\u0004\u00027w9\u0011qGO\u0007\u0002q)\u0011\u0011(J\u0001\u0007yI|w\u000e\u001e \n\u0003)J!\u0001P\u0015\u0002\u000fA\f7m[1hK&\u0011ah\u0010\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003y%\nA\u0001^5nKV\t!\t\u0005\u0002)\u0007&\u0011A)\u000b\u0002\u0005\u0019>tw-A\u0003uS6,\u0007%\u0001\u0006fq\u0016\u001cW\u000f^8s\u0013\u0012,\u0012\u0001\u0013\t\u0003\u00136s!AS&\u0011\u0005]J\u0013B\u0001'*\u0003\u0019\u0001&/\u001a3fM&\u0011aj\u0014\u0002\u0007'R\u0014\u0018N\\4\u000b\u00051K\u0013aC3yK\u000e,Ho\u001c:JI\u0002\nA\u0002^1tW\u001a\u000b\u0017\u000e\\;sKN,\u0012a\u0015\t\u0003QQK!!V\u0015\u0003\u0007%sG/A\u0007uCN\\g)Y5mkJ,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\teS6\f\u0018\t\u0003]\u0001AQ\u0001Q\u0004A\u0002\tCQAR\u0004A\u0002!CQ!U\u0004A\u0002M\u000bAaY8qsR!\u0011l\u00181b\u0011\u001d\u0001\u0005\u0002%AA\u0002\tCqA\u0012\u0005\u0011\u0002\u0003\u0007\u0001\nC\u0004R\u0011A\u0005\t\u0019A*\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\tAM\u000b\u0002CK.\na\r\u0005\u0002hY6\t\u0001N\u0003\u0002jU\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003W&\n!\"\u00198o_R\fG/[8o\u0013\ti\u0007NA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'F\u0001qU\tAU-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0016\u0003MT#aU3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\u00051\bCA<}\u001b\u0005A(BA={\u0003\u0011a\u0017M\\4\u000b\u0003m\fAA[1wC&\u0011a\n_\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\t!a\u0002\u0011\u0007!\n\u0019!C\u0002\u0002\u0006%\u00121!\u00118z\u0011!\tIADA\u0001\u0002\u0004\u0019\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0010A1\u0011\u0011CA\f\u0003\u0003i!!a\u0005\u000b\u0007\u0005U\u0011&\u0001\u0006d_2dWm\u0019;j_:LA!!\u0007\u0002\u0014\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ty\"!\n\u0011\u0007!\n\t#C\u0002\u0002$%\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002\nA\t\t\u00111\u0001\u0002\u0002\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\r1\u00181\u0006\u0005\t\u0003\u0013\t\u0012\u0011!a\u0001'\u0006A\u0001.Y:i\u0007>$W\rF\u0001T\u0003!!xn\u0015;sS:<G#\u0001<\u0002\r\u0015\fX/\u00197t)\u0011\ty\"!\u000f\t\u0013\u0005%A#!AA\u0002\u0005\u0005\u0001f\u0001\u0001\u0002>A!\u0011qHA\"\u001b\t\t\tE\u0003\u0002l?%!\u0011QIA!\u00051!UM^3m_B,'/\u00119jQ\u0015\u0001\u0011\u0011JA(!\u0011\ty$a\u0013\n\t\u00055\u0013\u0011\t\u0002\u0006'&t7-Z\u0011\u0003\u0003#\nQa\r\u00182]A\nQd\u00159be.d\u0015n\u001d;f]\u0016\u0014X\t_3dkR|'/\u0012=dYV$W\r\u001a\t\u0003]Y\u0019RAFA-\u0003K\u0002\u0002\"a\u0017\u0002b\tC5+W\u0007\u0003\u0003;R1!a\u0018*\u0003\u001d\u0011XO\u001c;j[\u0016LA!a\u0019\u0002^\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u001a\u0011\t\u0005\u001d\u0014QN\u0007\u0003\u0003SR1!a\u001b{\u0003\tIw.C\u0002?\u0003S\"\"!!\u0016\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fe\u000b)(a\u001e\u0002z!)\u0001)\u0007a\u0001\u0005\")a)\u0007a\u0001\u0011\")\u0011+\u0007a\u0001'\u00069QO\\1qa2LH\u0003BA@\u0003\u0017\u0003R\u0001KAA\u0003\u000bK1!a!*\u0005\u0019y\u0005\u000f^5p]B1\u0001&a\"C\u0011NK1!!#*\u0005\u0019!V\u000f\u001d7fg!A\u0011Q\u0012\u000e\u0002\u0002\u0003\u0007\u0011,A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a%\u0011\u0007]\f)*C\u0002\u0002\u0018b\u0014aa\u00142kK\u000e$\b"
)
public class SparkListenerExecutorExcluded implements SparkListenerEvent, Product, Serializable {
   private final long time;
   private final String executorId;
   private final int taskFailures;

   public static Option unapply(final SparkListenerExecutorExcluded x$0) {
      return SparkListenerExecutorExcluded$.MODULE$.unapply(x$0);
   }

   public static SparkListenerExecutorExcluded apply(final long time, final String executorId, final int taskFailures) {
      return SparkListenerExecutorExcluded$.MODULE$.apply(time, executorId, taskFailures);
   }

   public static Function1 tupled() {
      return SparkListenerExecutorExcluded$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerExecutorExcluded$.MODULE$.curried();
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

   public int taskFailures() {
      return this.taskFailures;
   }

   public SparkListenerExecutorExcluded copy(final long time, final String executorId, final int taskFailures) {
      return new SparkListenerExecutorExcluded(time, executorId, taskFailures);
   }

   public long copy$default$1() {
      return this.time();
   }

   public String copy$default$2() {
      return this.executorId();
   }

   public int copy$default$3() {
      return this.taskFailures();
   }

   public String productPrefix() {
      return "SparkListenerExecutorExcluded";
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
            return BoxesRunTime.boxToInteger(this.taskFailures());
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
      return x$1 instanceof SparkListenerExecutorExcluded;
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
            return "taskFailures";
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
      var1 = Statics.mix(var1, this.taskFailures());
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof SparkListenerExecutorExcluded) {
               SparkListenerExecutorExcluded var4 = (SparkListenerExecutorExcluded)x$1;
               if (this.time() == var4.time() && this.taskFailures() == var4.taskFailures()) {
                  label48: {
                     String var10000 = this.executorId();
                     String var5 = var4.executorId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
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

   public SparkListenerExecutorExcluded(final long time, final String executorId, final int taskFailures) {
      this.time = time;
      this.executorId = executorId;
      this.taskFailures = taskFailures;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
