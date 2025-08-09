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
   bytes = "\u0006\u0005\u0005]h\u0001\u0002\u0017.\u0001ZB\u0001\u0002\u0015\u0001\u0003\u0016\u0004%\t!\u0015\u0005\t5\u0002\u0011\t\u0012)A\u0005%\"A1\f\u0001BK\u0002\u0013\u0005A\f\u0003\u0005a\u0001\tE\t\u0015!\u0003^\u0011!\t\u0007A!f\u0001\n\u0003\u0011\u0007\u0002\u00034\u0001\u0005#\u0005\u000b\u0011B2\t\u0011\u001d\u0004!Q3A\u0005\u0002EC\u0001\u0002\u001b\u0001\u0003\u0012\u0003\u0006IA\u0015\u0005\tS\u0002\u0011)\u001a!C\u00019\"A!\u000e\u0001B\tB\u0003%Q\f\u0003\u0005l\u0001\tU\r\u0011\"\u0001m\u0011!!\bA!E!\u0002\u0013i\u0007\u0002C;\u0001\u0005+\u0007I\u0011\u00017\t\u0011Y\u0004!\u0011#Q\u0001\n5DQa\u001e\u0001\u0005\u0002aD\u0011\"a\u0001\u0001\u0003\u0003%\t!!\u0002\t\u0013\u0005U\u0001!%A\u0005\u0002\u0005]\u0001\"CA\u0017\u0001E\u0005I\u0011AA\u0018\u0011%\t\u0019\u0004AI\u0001\n\u0003\t)\u0004C\u0005\u0002:\u0001\t\n\u0011\"\u0001\u0002\u0018!I\u00111\b\u0001\u0012\u0002\u0013\u0005\u0011q\u0006\u0005\n\u0003{\u0001\u0011\u0013!C\u0001\u0003\u007fA\u0011\"a\u0011\u0001#\u0003%\t!a\u0010\t\u0013\u0005\u0015\u0003!!A\u0005B\u0005\u001d\u0003\"CA,\u0001\u0005\u0005I\u0011AA-\u0011%\t\t\u0007AA\u0001\n\u0003\t\u0019\u0007C\u0005\u0002p\u0001\t\t\u0011\"\u0011\u0002r!I\u0011\u0011\u0010\u0001\u0002\u0002\u0013\u0005\u00111\u0010\u0005\n\u0003\u000b\u0003\u0011\u0011!C!\u0003\u000fC\u0011\"a#\u0001\u0003\u0003%\t%!$\t\u0013\u0005=\u0005!!A\u0005B\u0005E\u0005\"CAJ\u0001\u0005\u0005I\u0011IAK\u000f%\t)+LA\u0001\u0012\u0003\t9K\u0002\u0005-[\u0005\u0005\t\u0012AAU\u0011\u00199(\u0005\"\u0001\u0002B\"I\u0011q\u0012\u0012\u0002\u0002\u0013\u0015\u0013\u0011\u0013\u0005\n\u0003\u0007\u0014\u0013\u0011!CA\u0003\u000bD\u0011\"!6##\u0003%\t!a\u0010\t\u0013\u0005]'%%A\u0005\u0002\u0005}\u0002\"CAmE\u0005\u0005I\u0011QAn\u0011%\tIOII\u0001\n\u0003\ty\u0004C\u0005\u0002l\n\n\n\u0011\"\u0001\u0002@!I\u0011Q\u001e\u0012\u0002\u0002\u0013%\u0011q\u001e\u0002\u001e'B\f'o\u001b'jgR,g.\u001a:BaBd\u0017nY1uS>t7\u000b^1si*\u0011afL\u0001\ng\u000eDW\rZ;mKJT!\u0001M\u0019\u0002\u000bM\u0004\u0018M]6\u000b\u0005I\u001a\u0014AB1qC\u000eDWMC\u00015\u0003\ry'oZ\u0002\u0001'\u0015\u0001q'P!E!\tA4(D\u0001:\u0015\u0005Q\u0014!B:dC2\f\u0017B\u0001\u001f:\u0005\u0019\te.\u001f*fMB\u0011ahP\u0007\u0002[%\u0011\u0001)\f\u0002\u0013'B\f'o\u001b'jgR,g.\u001a:Fm\u0016tG\u000f\u0005\u00029\u0005&\u00111)\u000f\u0002\b!J|G-^2u!\t)UJ\u0004\u0002G\u0017:\u0011qIS\u0007\u0002\u0011*\u0011\u0011*N\u0001\u0007yI|w\u000e\u001e \n\u0003iJ!\u0001T\u001d\u0002\u000fA\f7m[1hK&\u0011aj\u0014\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0019f\nq!\u00199q\u001d\u0006lW-F\u0001S!\t\u0019vK\u0004\u0002U+B\u0011q)O\u0005\u0003-f\na\u0001\u0015:fI\u00164\u0017B\u0001-Z\u0005\u0019\u0019FO]5oO*\u0011a+O\u0001\tCB\u0004h*Y7fA\u0005)\u0011\r\u001d9JIV\tQ\fE\u00029=JK!aX\u001d\u0003\r=\u0003H/[8o\u0003\u0019\t\u0007\u000f]%eA\u0005!A/[7f+\u0005\u0019\u0007C\u0001\u001de\u0013\t)\u0017H\u0001\u0003M_:<\u0017!\u0002;j[\u0016\u0004\u0013!C:qCJ\\Wk]3s\u0003)\u0019\b/\u0019:l+N,'\u000fI\u0001\rCB\u0004\u0018\t\u001e;f[B$\u0018\nZ\u0001\u000eCB\u0004\u0018\t\u001e;f[B$\u0018\n\u001a\u0011\u0002\u0015\u0011\u0014\u0018N^3s\u0019><7/F\u0001n!\rAdL\u001c\t\u0005_J\u0014&+D\u0001q\u0015\t\t\u0018(\u0001\u0006d_2dWm\u0019;j_:L!a\u001d9\u0003\u00075\u000b\u0007/A\u0006ee&4XM\u001d'pON\u0004\u0013\u0001\u00053sSZ,'/\u0011;ue&\u0014W\u000f^3t\u0003E!'/\u001b<fe\u0006#HO]5ckR,7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0013eT8\u0010`?\u007f\u007f\u0006\u0005\u0001C\u0001 \u0001\u0011\u0015\u0001v\u00021\u0001S\u0011\u0015Yv\u00021\u0001^\u0011\u0015\tw\u00021\u0001d\u0011\u00159w\u00021\u0001S\u0011\u0015Iw\u00021\u0001^\u0011\u001dYw\u0002%AA\u00025Dq!^\b\u0011\u0002\u0003\u0007Q.\u0001\u0003d_BLHcD=\u0002\b\u0005%\u00111BA\u0007\u0003\u001f\t\t\"a\u0005\t\u000fA\u0003\u0002\u0013!a\u0001%\"91\f\u0005I\u0001\u0002\u0004i\u0006bB1\u0011!\u0003\u0005\ra\u0019\u0005\bOB\u0001\n\u00111\u0001S\u0011\u001dI\u0007\u0003%AA\u0002uCqa\u001b\t\u0011\u0002\u0003\u0007Q\u000eC\u0004v!A\u0005\t\u0019A7\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011\u0011\u0004\u0016\u0004%\u0006m1FAA\u000f!\u0011\ty\"!\u000b\u000e\u0005\u0005\u0005\"\u0002BA\u0012\u0003K\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005\u001d\u0012(\u0001\u0006b]:|G/\u0019;j_:LA!a\u000b\u0002\"\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011\u0011\u0007\u0016\u0004;\u0006m\u0011AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003oQ3aYA\u000e\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nabY8qs\u0012\"WMZ1vYR$S'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0005\u0005#fA7\u0002\u001c\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012:\u0014!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070\u0006\u0002\u0002JA!\u00111JA+\u001b\t\tiE\u0003\u0003\u0002P\u0005E\u0013\u0001\u00027b]\u001eT!!a\u0015\u0002\t)\fg/Y\u0005\u00041\u00065\u0013\u0001\u00049s_\u0012,8\r^!sSRLXCAA.!\rA\u0014QL\u0005\u0004\u0003?J$aA%oi\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BA3\u0003W\u00022\u0001OA4\u0013\r\tI'\u000f\u0002\u0004\u0003:L\b\"CA75\u0005\u0005\t\u0019AA.\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u00111\u000f\t\u0006_\u0006U\u0014QM\u0005\u0004\u0003o\u0002(\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!! \u0002\u0004B\u0019\u0001(a \n\u0007\u0005\u0005\u0015HA\u0004C_>dW-\u00198\t\u0013\u00055D$!AA\u0002\u0005\u0015\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!\u0013\u0002\n\"I\u0011QN\u000f\u0002\u0002\u0003\u0007\u00111L\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111L\u0001\ti>\u001cFO]5oOR\u0011\u0011\u0011J\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005u\u0014q\u0013\u0005\n\u0003[\u0002\u0013\u0011!a\u0001\u0003KB3\u0001AAN!\u0011\ti*!)\u000e\u0005\u0005}%bAA\u0014_%!\u00111UAP\u00051!UM^3m_B,'/\u00119j\u0003u\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0006\u0003\b\u000f\\5dCRLwN\\*uCJ$\bC\u0001 #'\u0015\u0011\u00131VA\\!1\ti+a-S;\u000e\u0014V,\\7z\u001b\t\tyKC\u0002\u00022f\nqA];oi&lW-\u0003\u0003\u00026\u0006=&!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8ooA!\u0011\u0011XA`\u001b\t\tYL\u0003\u0003\u0002>\u0006E\u0013AA5p\u0013\rq\u00151\u0018\u000b\u0003\u0003O\u000bQ!\u00199qYf$r\"_Ad\u0003\u0013\fY-!4\u0002P\u0006E\u00171\u001b\u0005\u0006!\u0016\u0002\rA\u0015\u0005\u00067\u0016\u0002\r!\u0018\u0005\u0006C\u0016\u0002\ra\u0019\u0005\u0006O\u0016\u0002\rA\u0015\u0005\u0006S\u0016\u0002\r!\u0018\u0005\bW\u0016\u0002\n\u00111\u0001n\u0011\u001d)X\u0005%AA\u00025\fq\"\u00199qYf$C-\u001a4bk2$HEN\u0001\u0010CB\u0004H.\u001f\u0013eK\u001a\fW\u000f\u001c;%o\u00059QO\\1qa2LH\u0003BAo\u0003K\u0004B\u0001\u000f0\u0002`BQ\u0001(!9S;\u000e\u0014V,\\7\n\u0007\u0005\r\u0018H\u0001\u0004UkBdWm\u000e\u0005\t\u0003OD\u0013\u0011!a\u0001s\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00137\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%o\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011\u0011\u001f\t\u0005\u0003\u0017\n\u00190\u0003\u0003\u0002v\u00065#AB(cU\u0016\u001cG\u000f"
)
public class SparkListenerApplicationStart implements SparkListenerEvent, Product, Serializable {
   private final String appName;
   private final Option appId;
   private final long time;
   private final String sparkUser;
   private final Option appAttemptId;
   private final Option driverLogs;
   private final Option driverAttributes;

   public static Option $lessinit$greater$default$7() {
      return SparkListenerApplicationStart$.MODULE$.$lessinit$greater$default$7();
   }

   public static Option $lessinit$greater$default$6() {
      return SparkListenerApplicationStart$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final SparkListenerApplicationStart x$0) {
      return SparkListenerApplicationStart$.MODULE$.unapply(x$0);
   }

   public static Option apply$default$7() {
      return SparkListenerApplicationStart$.MODULE$.apply$default$7();
   }

   public static Option apply$default$6() {
      return SparkListenerApplicationStart$.MODULE$.apply$default$6();
   }

   public static SparkListenerApplicationStart apply(final String appName, final Option appId, final long time, final String sparkUser, final Option appAttemptId, final Option driverLogs, final Option driverAttributes) {
      return SparkListenerApplicationStart$.MODULE$.apply(appName, appId, time, sparkUser, appAttemptId, driverLogs, driverAttributes);
   }

   public static Function1 tupled() {
      return SparkListenerApplicationStart$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerApplicationStart$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public String appName() {
      return this.appName;
   }

   public Option appId() {
      return this.appId;
   }

   public long time() {
      return this.time;
   }

   public String sparkUser() {
      return this.sparkUser;
   }

   public Option appAttemptId() {
      return this.appAttemptId;
   }

   public Option driverLogs() {
      return this.driverLogs;
   }

   public Option driverAttributes() {
      return this.driverAttributes;
   }

   public SparkListenerApplicationStart copy(final String appName, final Option appId, final long time, final String sparkUser, final Option appAttemptId, final Option driverLogs, final Option driverAttributes) {
      return new SparkListenerApplicationStart(appName, appId, time, sparkUser, appAttemptId, driverLogs, driverAttributes);
   }

   public String copy$default$1() {
      return this.appName();
   }

   public Option copy$default$2() {
      return this.appId();
   }

   public long copy$default$3() {
      return this.time();
   }

   public String copy$default$4() {
      return this.sparkUser();
   }

   public Option copy$default$5() {
      return this.appAttemptId();
   }

   public Option copy$default$6() {
      return this.driverLogs();
   }

   public Option copy$default$7() {
      return this.driverAttributes();
   }

   public String productPrefix() {
      return "SparkListenerApplicationStart";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.appName();
         }
         case 1 -> {
            return this.appId();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 3 -> {
            return this.sparkUser();
         }
         case 4 -> {
            return this.appAttemptId();
         }
         case 5 -> {
            return this.driverLogs();
         }
         case 6 -> {
            return this.driverAttributes();
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
      return x$1 instanceof SparkListenerApplicationStart;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "appName";
         }
         case 1 -> {
            return "appId";
         }
         case 2 -> {
            return "time";
         }
         case 3 -> {
            return "sparkUser";
         }
         case 4 -> {
            return "appAttemptId";
         }
         case 5 -> {
            return "driverLogs";
         }
         case 6 -> {
            return "driverAttributes";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.appName()));
      var1 = Statics.mix(var1, Statics.anyHash(this.appId()));
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.sparkUser()));
      var1 = Statics.mix(var1, Statics.anyHash(this.appAttemptId()));
      var1 = Statics.mix(var1, Statics.anyHash(this.driverLogs()));
      var1 = Statics.mix(var1, Statics.anyHash(this.driverAttributes()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof SparkListenerApplicationStart) {
               SparkListenerApplicationStart var4 = (SparkListenerApplicationStart)x$1;
               if (this.time() == var4.time()) {
                  label84: {
                     String var10000 = this.appName();
                     String var5 = var4.appName();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     Option var11 = this.appId();
                     Option var6 = var4.appId();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var6)) {
                        break label84;
                     }

                     String var12 = this.sparkUser();
                     String var7 = var4.sparkUser();
                     if (var12 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var12.equals(var7)) {
                        break label84;
                     }

                     Option var13 = this.appAttemptId();
                     Option var8 = var4.appAttemptId();
                     if (var13 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var8)) {
                        break label84;
                     }

                     var13 = this.driverLogs();
                     Option var9 = var4.driverLogs();
                     if (var13 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var9)) {
                        break label84;
                     }

                     var13 = this.driverAttributes();
                     Option var10 = var4.driverAttributes();
                     if (var13 == null) {
                        if (var10 != null) {
                           break label84;
                        }
                     } else if (!var13.equals(var10)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
                     }
                  }
               }
            }

            var16 = false;
            return var16;
         }
      }

      var16 = true;
      return var16;
   }

   public SparkListenerApplicationStart(final String appName, final Option appId, final long time, final String sparkUser, final Option appAttemptId, final Option driverLogs, final Option driverAttributes) {
      this.appName = appName;
      this.appId = appId;
      this.time = time;
      this.sparkUser = sparkUser;
      this.appAttemptId = appAttemptId;
      this.driverLogs = driverLogs;
      this.driverAttributes = driverAttributes;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
   }
}
