package org.apache.spark.streaming.api.java;

import java.io.Serializable;
import org.apache.spark.streaming.Time;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]g!\u0002\u0015*\u00016*\u0004\u0002\u0003'\u0001\u0005+\u0007I\u0011A'\t\u0011I\u0003!\u0011#Q\u0001\n9C\u0001b\u0015\u0001\u0003\u0016\u0004%\t\u0001\u0016\u0005\t1\u0002\u0011\t\u0012)A\u0005+\"A\u0011\f\u0001BK\u0002\u0013\u0005!\f\u0003\u0005d\u0001\tE\t\u0015!\u0003\\\u0011!!\u0007A!f\u0001\n\u0003Q\u0006\u0002C3\u0001\u0005#\u0005\u000b\u0011B.\t\u0011\u0019\u0004!Q3A\u0005\u0002\u001dD\u0001b\u001b\u0001\u0003\u0012\u0003\u0006I\u0001\u001b\u0005\tY\u0002\u0011)\u001a!C\u0001O\"AQ\u000e\u0001B\tB\u0003%\u0001\u000e\u0003\u0005o\u0001\tU\r\u0011\"\u0001[\u0011!y\u0007A!E!\u0002\u0013Y\u0006\"\u00029\u0001\t\u0003\t\bbB>\u0001\u0003\u0003%\t\u0001 \u0005\n\u0003\u0013\u0001\u0011\u0013!C\u0001\u0003\u0017A\u0011\"!\t\u0001#\u0003%\t!a\t\t\u0013\u0005\u001d\u0002!%A\u0005\u0002\u0005%\u0002\"CA\u0017\u0001E\u0005I\u0011AA\u0015\u0011%\ty\u0003AI\u0001\n\u0003\t\t\u0004C\u0005\u00026\u0001\t\n\u0011\"\u0001\u00022!I\u0011q\u0007\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0006\u0005\n\u0003s\u0001\u0011\u0011!C!\u0003wA\u0001\"!\u0013\u0001\u0003\u0003%\t\u0001\u0016\u0005\n\u0003\u0017\u0002\u0011\u0011!C\u0001\u0003\u001bB\u0011\"!\u0017\u0001\u0003\u0003%\t%a\u0017\t\u0013\u0005%\u0004!!A\u0005\u0002\u0005-\u0004\"CA;\u0001\u0005\u0005I\u0011IA<\u0011%\tY\bAA\u0001\n\u0003\ni\bC\u0005\u0002\u0000\u0001\t\t\u0011\"\u0011\u0002\u0002\"I\u00111\u0011\u0001\u0002\u0002\u0013\u0005\u0013QQ\u0004\u000b\u0003\u0013K\u0013\u0011!E\u0001[\u0005-e!\u0003\u0015*\u0003\u0003E\t!LAG\u0011\u0019\u0001(\u0005\"\u0001\u0002&\"I\u0011q\u0010\u0012\u0002\u0002\u0013\u0015\u0013\u0011\u0011\u0005\n\u0003O\u0013\u0013\u0011!CA\u0003SC\u0011\"!/#\u0003\u0003%\t)a/\t\u0013\u00055'%!A\u0005\n\u0005='a\u0006&bm\u0006|U\u000f\u001e9vi>\u0003XM]1uS>t\u0017J\u001c4p\u0015\tQ3&\u0001\u0003kCZ\f'B\u0001\u0017.\u0003\r\t\u0007/\u001b\u0006\u0003]=\n\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005A\n\u0014!B:qCJ\\'B\u0001\u001a4\u0003\u0019\t\u0007/Y2iK*\tA'A\u0002pe\u001e\u001cB\u0001\u0001\u001c=\u007fA\u0011qGO\u0007\u0002q)\t\u0011(A\u0003tG\u0006d\u0017-\u0003\u0002<q\t1\u0011I\\=SK\u001a\u0004\"aN\u001f\n\u0005yB$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0001&s!!Q$\u000f\u0005\t3U\"A\"\u000b\u0005\u0011+\u0015A\u0002\u001fs_>$hh\u0001\u0001\n\u0003eJ!\u0001\u0013\u001d\u0002\u000fA\f7m[1hK&\u0011!j\u0013\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0011b\n\u0011BY1uG\"$\u0016.\\3\u0016\u00039\u0003\"a\u0014)\u000e\u00035J!!U\u0017\u0003\tQKW.Z\u0001\u000bE\u0006$8\r\u001b+j[\u0016\u0004\u0013AA5e+\u0005)\u0006CA\u001cW\u0013\t9\u0006HA\u0002J]R\f1!\u001b3!\u0003\u0011q\u0017-\\3\u0016\u0003m\u0003\"\u0001\u00181\u000f\u0005us\u0006C\u0001\"9\u0013\ty\u0006(\u0001\u0004Qe\u0016$WMZ\u0005\u0003C\n\u0014aa\u0015;sS:<'BA09\u0003\u0015q\u0017-\\3!\u0003-!Wm]2sSB$\u0018n\u001c8\u0002\u0019\u0011,7o\u0019:jaRLwN\u001c\u0011\u0002\u0013M$\u0018M\u001d;US6,W#\u00015\u0011\u0005]J\u0017B\u000169\u0005\u0011auN\\4\u0002\u0015M$\u0018M\u001d;US6,\u0007%A\u0004f]\u0012$\u0016.\\3\u0002\u0011\u0015tG\rV5nK\u0002\nQBZ1jYV\u0014XMU3bg>t\u0017A\u00044bS2,(/\u001a*fCN|g\u000eI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0011I$XO^<ysj\u0004\"a\u001d\u0001\u000e\u0003%BQ\u0001T\bA\u00029CQaU\bA\u0002UCQ!W\bA\u0002mCQ\u0001Z\bA\u0002mCQAZ\bA\u0002!DQ\u0001\\\bA\u0002!DQA\\\bA\u0002m\u000bAaY8qsRa!/ @\u0000\u0003\u0003\t\u0019!!\u0002\u0002\b!9A\n\u0005I\u0001\u0002\u0004q\u0005bB*\u0011!\u0003\u0005\r!\u0016\u0005\b3B\u0001\n\u00111\u0001\\\u0011\u001d!\u0007\u0003%AA\u0002mCqA\u001a\t\u0011\u0002\u0003\u0007\u0001\u000eC\u0004m!A\u0005\t\u0019\u00015\t\u000f9\u0004\u0002\u0013!a\u00017\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAA\u0007U\rq\u0015qB\u0016\u0003\u0003#\u0001B!a\u0005\u0002\u001e5\u0011\u0011Q\u0003\u0006\u0005\u0003/\tI\"A\u0005v]\u000eDWmY6fI*\u0019\u00111\u0004\u001d\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002 \u0005U!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TCAA\u0013U\r)\u0016qB\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134+\t\tYCK\u0002\\\u0003\u001f\tabY8qs\u0012\"WMZ1vYR$C'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001b\u0016\u0005\u0005M\"f\u00015\u0002\u0010\u0005q1m\u001c9zI\u0011,g-Y;mi\u00122\u0014AD2paf$C-\u001a4bk2$HeN\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005u\u0002\u0003BA \u0003\u000fj!!!\u0011\u000b\t\u0005\r\u0013QI\u0001\u0005Y\u0006twMC\u0001+\u0013\r\t\u0017\u0011I\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\ty%!\u0016\u0011\u0007]\n\t&C\u0002\u0002Ta\u00121!\u00118z\u0011!\t9FGA\u0001\u0002\u0004)\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002^A1\u0011qLA3\u0003\u001fj!!!\u0019\u000b\u0007\u0005\r\u0004(\u0001\u0006d_2dWm\u0019;j_:LA!a\u001a\u0002b\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\ti'a\u001d\u0011\u0007]\ny'C\u0002\u0002ra\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002Xq\t\t\u00111\u0001\u0002P\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ti$!\u001f\t\u0011\u0005]S$!AA\u0002U\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002+\u0006AAo\\*ue&tw\r\u0006\u0002\u0002>\u00051Q-];bYN$B!!\u001c\u0002\b\"I\u0011q\u000b\u0011\u0002\u0002\u0003\u0007\u0011qJ\u0001\u0018\u0015\u00064\u0018mT;uaV$x\n]3sCRLwN\\%oM>\u0004\"a\u001d\u0012\u0014\u000b\t\ny)a'\u0011\u0019\u0005E\u0015q\u0013(V7nC\u0007n\u0017:\u000e\u0005\u0005M%bAAKq\u00059!/\u001e8uS6,\u0017\u0002BAM\u0003'\u0013\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c88!\u0011\ti*a)\u000e\u0005\u0005}%\u0002BAQ\u0003\u000b\n!![8\n\u0007)\u000by\n\u0006\u0002\u0002\f\u0006)\u0011\r\u001d9msRy!/a+\u0002.\u0006=\u0016\u0011WAZ\u0003k\u000b9\fC\u0003MK\u0001\u0007a\nC\u0003TK\u0001\u0007Q\u000bC\u0003ZK\u0001\u00071\fC\u0003eK\u0001\u00071\fC\u0003gK\u0001\u0007\u0001\u000eC\u0003mK\u0001\u0007\u0001\u000eC\u0003oK\u0001\u00071,A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005u\u0016\u0011\u001a\t\u0006o\u0005}\u00161Y\u0005\u0004\u0003\u0003D$AB(qi&|g\u000e\u0005\u00068\u0003\u000btUkW.iQnK1!a29\u0005\u0019!V\u000f\u001d7fo!A\u00111\u001a\u0014\u0002\u0002\u0003\u0007!/A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!5\u0011\t\u0005}\u00121[\u0005\u0005\u0003+\f\tE\u0001\u0004PE*,7\r\u001e"
)
public class JavaOutputOperationInfo implements Product, Serializable {
   private final Time batchTime;
   private final int id;
   private final String name;
   private final String description;
   private final long startTime;
   private final long endTime;
   private final String failureReason;

   public static Option unapply(final JavaOutputOperationInfo x$0) {
      return JavaOutputOperationInfo$.MODULE$.unapply(x$0);
   }

   public static JavaOutputOperationInfo apply(final Time batchTime, final int id, final String name, final String description, final long startTime, final long endTime, final String failureReason) {
      return JavaOutputOperationInfo$.MODULE$.apply(batchTime, id, name, description, startTime, endTime, failureReason);
   }

   public static Function1 tupled() {
      return JavaOutputOperationInfo$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JavaOutputOperationInfo$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time batchTime() {
      return this.batchTime;
   }

   public int id() {
      return this.id;
   }

   public String name() {
      return this.name;
   }

   public String description() {
      return this.description;
   }

   public long startTime() {
      return this.startTime;
   }

   public long endTime() {
      return this.endTime;
   }

   public String failureReason() {
      return this.failureReason;
   }

   public JavaOutputOperationInfo copy(final Time batchTime, final int id, final String name, final String description, final long startTime, final long endTime, final String failureReason) {
      return new JavaOutputOperationInfo(batchTime, id, name, description, startTime, endTime, failureReason);
   }

   public Time copy$default$1() {
      return this.batchTime();
   }

   public int copy$default$2() {
      return this.id();
   }

   public String copy$default$3() {
      return this.name();
   }

   public String copy$default$4() {
      return this.description();
   }

   public long copy$default$5() {
      return this.startTime();
   }

   public long copy$default$6() {
      return this.endTime();
   }

   public String copy$default$7() {
      return this.failureReason();
   }

   public String productPrefix() {
      return "JavaOutputOperationInfo";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.batchTime();
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.id());
         }
         case 2 -> {
            return this.name();
         }
         case 3 -> {
            return this.description();
         }
         case 4 -> {
            return BoxesRunTime.boxToLong(this.startTime());
         }
         case 5 -> {
            return BoxesRunTime.boxToLong(this.endTime());
         }
         case 6 -> {
            return this.failureReason();
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
      return x$1 instanceof JavaOutputOperationInfo;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "batchTime";
         }
         case 1 -> {
            return "id";
         }
         case 2 -> {
            return "name";
         }
         case 3 -> {
            return "description";
         }
         case 4 -> {
            return "startTime";
         }
         case 5 -> {
            return "endTime";
         }
         case 6 -> {
            return "failureReason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.batchTime()));
      var1 = Statics.mix(var1, this.id());
      var1 = Statics.mix(var1, Statics.anyHash(this.name()));
      var1 = Statics.mix(var1, Statics.anyHash(this.description()));
      var1 = Statics.mix(var1, Statics.longHash(this.startTime()));
      var1 = Statics.mix(var1, Statics.longHash(this.endTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.failureReason()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var12;
      if (this != x$1) {
         label83: {
            if (x$1 instanceof JavaOutputOperationInfo) {
               JavaOutputOperationInfo var4 = (JavaOutputOperationInfo)x$1;
               if (this.id() == var4.id() && this.startTime() == var4.startTime() && this.endTime() == var4.endTime()) {
                  label76: {
                     Time var10000 = this.batchTime();
                     Time var5 = var4.batchTime();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label76;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label76;
                     }

                     String var9 = this.name();
                     String var6 = var4.name();
                     if (var9 == null) {
                        if (var6 != null) {
                           break label76;
                        }
                     } else if (!var9.equals(var6)) {
                        break label76;
                     }

                     var9 = this.description();
                     String var7 = var4.description();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label76;
                        }
                     } else if (!var9.equals(var7)) {
                        break label76;
                     }

                     var9 = this.failureReason();
                     String var8 = var4.failureReason();
                     if (var9 == null) {
                        if (var8 != null) {
                           break label76;
                        }
                     } else if (!var9.equals(var8)) {
                        break label76;
                     }

                     if (var4.canEqual(this)) {
                        break label83;
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

   public JavaOutputOperationInfo(final Time batchTime, final int id, final String name, final String description, final long startTime, final long endTime, final String failureReason) {
      this.batchTime = batchTime;
      this.id = id;
      this.name = name;
      this.description = description;
      this.startTime = startTime;
      this.endTime = endTime;
      this.failureReason = failureReason;
      Product.$init$(this);
   }
}
