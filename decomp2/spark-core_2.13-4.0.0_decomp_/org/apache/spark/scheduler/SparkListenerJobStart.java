package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Properties;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg\u0001B\u0012%\u00016B\u0001b\u0012\u0001\u0003\u0016\u0004%\t\u0001\u0013\u0005\t\u0019\u0002\u0011\t\u0012)A\u0005\u0013\"AQ\n\u0001BK\u0002\u0013\u0005a\n\u0003\u0005S\u0001\tE\t\u0015!\u0003P\u0011!\u0019\u0006A!f\u0001\n\u0003!\u0006\u0002C.\u0001\u0005#\u0005\u000b\u0011B+\t\u0011q\u0003!Q3A\u0005\u0002uC\u0001B\u001a\u0001\u0003\u0012\u0003\u0006IA\u0018\u0005\u0006O\u0002!\t\u0001\u001b\u0005\b]\u0002\u0011\r\u0011\"\u0001p\u0011\u0019\t\b\u0001)A\u0005a\"9!\u000fAA\u0001\n\u0003\u0019\bb\u0002=\u0001#\u0003%\t!\u001f\u0005\n\u0003\u0013\u0001\u0011\u0013!C\u0001\u0003\u0017A\u0011\"a\u0004\u0001#\u0003%\t!!\u0005\t\u0013\u0005U\u0001!%A\u0005\u0002\u0005]\u0001\"CA\u000e\u0001\u0005\u0005I\u0011IA\u000f\u0011!\tY\u0003AA\u0001\n\u0003A\u0005\"CA\u0017\u0001\u0005\u0005I\u0011AA\u0018\u0011%\tY\u0004AA\u0001\n\u0003\ni\u0004C\u0005\u0002L\u0001\t\t\u0011\"\u0001\u0002N!I\u0011q\u000b\u0001\u0002\u0002\u0013\u0005\u0013\u0011\f\u0005\n\u0003;\u0002\u0011\u0011!C!\u0003?B\u0011\"!\u0019\u0001\u0003\u0003%\t%a\u0019\t\u0013\u0005\u0015\u0004!!A\u0005B\u0005\u001dt!CA<I\u0005\u0005\t\u0012AA=\r!\u0019C%!A\t\u0002\u0005m\u0004BB4\u001c\t\u0003\t\u0019\nC\u0005\u0002bm\t\t\u0011\"\u0012\u0002d!I\u0011QS\u000e\u0002\u0002\u0013\u0005\u0015q\u0013\u0005\n\u0003C[\u0012\u0013!C\u0001\u0003/A\u0011\"a)\u001c\u0003\u0003%\t)!*\t\u0013\u0005]6$%A\u0005\u0002\u0005]\u0001\"CA]7\u0005\u0005I\u0011BA^\u0005U\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe*{'m\u0015;beRT!!\n\u0014\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014(BA\u0014)\u0003\u0015\u0019\b/\u0019:l\u0015\tI#&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002W\u0005\u0019qN]4\u0004\u0001M)\u0001A\f\u001b9wA\u0011qFM\u0007\u0002a)\t\u0011'A\u0003tG\u0006d\u0017-\u0003\u00024a\t1\u0011I\\=SK\u001a\u0004\"!\u000e\u001c\u000e\u0003\u0011J!a\u000e\u0013\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e\u001e\t\u0003_eJ!A\u000f\u0019\u0003\u000fA\u0013x\u000eZ;diB\u0011A\b\u0012\b\u0003{\ts!AP!\u000e\u0003}R!\u0001\u0011\u0017\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0014BA\"1\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0012$\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005\r\u0003\u0014!\u00026pE&#W#A%\u0011\u0005=R\u0015BA&1\u0005\rIe\u000e^\u0001\u0007U>\u0014\u0017\n\u001a\u0011\u0002\tQLW.Z\u000b\u0002\u001fB\u0011q\u0006U\u0005\u0003#B\u0012A\u0001T8oO\u0006)A/[7fA\u0005Q1\u000f^1hK&sgm\\:\u0016\u0003U\u00032\u0001\u0010,Y\u0013\t9fIA\u0002TKF\u0004\"!N-\n\u0005i##!C*uC\u001e,\u0017J\u001c4p\u0003-\u0019H/Y4f\u0013:4wn\u001d\u0011\u0002\u0015A\u0014x\u000e]3si&,7/F\u0001_!\tyF-D\u0001a\u0015\t\t'-\u0001\u0003vi&d'\"A2\u0002\t)\fg/Y\u0005\u0003K\u0002\u0014!\u0002\u0015:pa\u0016\u0014H/[3t\u0003-\u0001(o\u001c9feRLWm\u001d\u0011\u0002\rqJg.\u001b;?)\u0015I'n\u001b7n!\t)\u0004\u0001C\u0003H\u0013\u0001\u0007\u0011\nC\u0003N\u0013\u0001\u0007q\nC\u0003T\u0013\u0001\u0007Q\u000bC\u0004]\u0013A\u0005\t\u0019\u00010\u0002\u0011M$\u0018mZ3JIN,\u0012\u0001\u001d\t\u0004yYK\u0015!C:uC\u001e,\u0017\nZ:!\u0003\u0011\u0019w\u000e]=\u0015\u000b%$XO^<\t\u000f\u001dc\u0001\u0013!a\u0001\u0013\"9Q\n\u0004I\u0001\u0002\u0004y\u0005bB*\r!\u0003\u0005\r!\u0016\u0005\b92\u0001\n\u00111\u0001_\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012A\u001f\u0016\u0003\u0013n\\\u0013\u0001 \t\u0004{\u0006\u0015Q\"\u0001@\u000b\u0007}\f\t!A\u0005v]\u000eDWmY6fI*\u0019\u00111\u0001\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.C\u0002\u0002\by\u0014\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"!!\u0004+\u0005=[\u0018AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0003'Q#!V>\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%iU\u0011\u0011\u0011\u0004\u0016\u0003=n\fQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u0010!\u0011\t\t#a\n\u000e\u0005\u0005\r\"bAA\u0013E\u0006!A.\u00198h\u0013\u0011\tI#a\t\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!!\r\u00028A\u0019q&a\r\n\u0007\u0005U\u0002GA\u0002B]fD\u0001\"!\u000f\u0014\u0003\u0003\u0005\r!S\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0005}\u0002CBA!\u0003\u000f\n\t$\u0004\u0002\u0002D)\u0019\u0011Q\t\u0019\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002J\u0005\r#\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0014\u0002VA\u0019q&!\u0015\n\u0007\u0005M\u0003GA\u0004C_>dW-\u00198\t\u0013\u0005eR#!AA\u0002\u0005E\u0012A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!a\b\u0002\\!A\u0011\u0011\b\f\u0002\u0002\u0003\u0007\u0011*\u0001\u0005iCND7i\u001c3f)\u0005I\u0015\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005}\u0011AB3rk\u0006d7\u000f\u0006\u0003\u0002P\u0005%\u0004\"CA\u001d3\u0005\u0005\t\u0019AA\u0019Q\r\u0001\u0011Q\u000e\t\u0005\u0003_\n\u0019(\u0004\u0002\u0002r)\u0019\u00111\u0001\u0014\n\t\u0005U\u0014\u0011\u000f\u0002\r\t\u00164X\r\\8qKJ\f\u0005/[\u0001\u0016'B\f'o\u001b'jgR,g.\u001a:K_\n\u001cF/\u0019:u!\t)4dE\u0003\u001c\u0003{\nI\tE\u0005\u0002\u0000\u0005\u0015\u0015jT+_S6\u0011\u0011\u0011\u0011\u0006\u0004\u0003\u0007\u0003\u0014a\u0002:v]RLW.Z\u0005\u0005\u0003\u000f\u000b\tIA\tBEN$(/Y2u\rVt7\r^5p]R\u0002B!a#\u0002\u00126\u0011\u0011Q\u0012\u0006\u0004\u0003\u001f\u0013\u0017AA5p\u0013\r)\u0015Q\u0012\u000b\u0003\u0003s\nQ!\u00199qYf$\u0012\"[AM\u00037\u000bi*a(\t\u000b\u001ds\u0002\u0019A%\t\u000b5s\u0002\u0019A(\t\u000bMs\u0002\u0019A+\t\u000fqs\u0002\u0013!a\u0001=\u0006y\u0011\r\u001d9ms\u0012\"WMZ1vYR$C'A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005\u001d\u00161\u0017\t\u0006_\u0005%\u0016QV\u0005\u0004\u0003W\u0003$AB(qi&|g\u000eE\u00040\u0003_Ku*\u00160\n\u0007\u0005E\u0006G\u0001\u0004UkBdW\r\u000e\u0005\t\u0003k\u0003\u0013\u0011!a\u0001S\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\ti\f\u0005\u0003\u0002\"\u0005}\u0016\u0002BAa\u0003G\u0011aa\u00142kK\u000e$\b"
)
public class SparkListenerJobStart implements SparkListenerEvent, Product, Serializable {
   private final int jobId;
   private final long time;
   private final Seq stageInfos;
   private final Properties properties;
   private final Seq stageIds;

   public static Properties $lessinit$greater$default$4() {
      return SparkListenerJobStart$.MODULE$.$lessinit$greater$default$4();
   }

   public static Option unapply(final SparkListenerJobStart x$0) {
      return SparkListenerJobStart$.MODULE$.unapply(x$0);
   }

   public static Properties apply$default$4() {
      return SparkListenerJobStart$.MODULE$.apply$default$4();
   }

   public static SparkListenerJobStart apply(final int jobId, final long time, final Seq stageInfos, final Properties properties) {
      return SparkListenerJobStart$.MODULE$.apply(jobId, time, stageInfos, properties);
   }

   public static Function1 tupled() {
      return SparkListenerJobStart$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerJobStart$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public int jobId() {
      return this.jobId;
   }

   public long time() {
      return this.time;
   }

   public Seq stageInfos() {
      return this.stageInfos;
   }

   public Properties properties() {
      return this.properties;
   }

   public Seq stageIds() {
      return this.stageIds;
   }

   public SparkListenerJobStart copy(final int jobId, final long time, final Seq stageInfos, final Properties properties) {
      return new SparkListenerJobStart(jobId, time, stageInfos, properties);
   }

   public int copy$default$1() {
      return this.jobId();
   }

   public long copy$default$2() {
      return this.time();
   }

   public Seq copy$default$3() {
      return this.stageInfos();
   }

   public Properties copy$default$4() {
      return this.properties();
   }

   public String productPrefix() {
      return "SparkListenerJobStart";
   }

   public int productArity() {
      return 4;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.jobId());
         }
         case 1 -> {
            return BoxesRunTime.boxToLong(this.time());
         }
         case 2 -> {
            return this.stageInfos();
         }
         case 3 -> {
            return this.properties();
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
      return x$1 instanceof SparkListenerJobStart;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jobId";
         }
         case 1 -> {
            return "time";
         }
         case 2 -> {
            return "stageInfos";
         }
         case 3 -> {
            return "properties";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.jobId());
      var1 = Statics.mix(var1, Statics.longHash(this.time()));
      var1 = Statics.mix(var1, Statics.anyHash(this.stageInfos()));
      var1 = Statics.mix(var1, Statics.anyHash(this.properties()));
      return Statics.finalizeHash(var1, 4);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label63: {
            if (x$1 instanceof SparkListenerJobStart) {
               SparkListenerJobStart var4 = (SparkListenerJobStart)x$1;
               if (this.jobId() == var4.jobId() && this.time() == var4.time()) {
                  label56: {
                     Seq var10000 = this.stageInfos();
                     Seq var5 = var4.stageInfos();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     Properties var7 = this.properties();
                     Properties var6 = var4.properties();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var7.equals(var6)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
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

   // $FF: synthetic method
   public static final int $anonfun$stageIds$1(final StageInfo x$1) {
      return x$1.stageId();
   }

   public SparkListenerJobStart(final int jobId, final long time, final Seq stageInfos, final Properties properties) {
      this.jobId = jobId;
      this.time = time;
      this.stageInfos = stageInfos;
      this.properties = properties;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
      this.stageIds = (Seq)stageInfos.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$stageIds$1(x$1)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
