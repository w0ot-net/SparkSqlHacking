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
   bytes = "\u0006\u0005\u0005ue\u0001\u0002\u0013&\u0001:B\u0001\u0002\u0013\u0001\u0003\u0016\u0004%\t!\u0013\u0005\t\u001b\u0002\u0011\t\u0012)A\u0005\u0015\"Aa\n\u0001BK\u0002\u0013\u0005\u0011\n\u0003\u0005P\u0001\tE\t\u0015!\u0003K\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u001d)\u0006\u00011A\u0005\n%CqA\u0016\u0001A\u0002\u0013%q\u000b\u0003\u0004^\u0001\u0001\u0006KA\u0013\u0005\b=\u0002\u0001\r\u0011\"\u0003J\u0011\u001dy\u0006\u00011A\u0005\n\u0001DaA\u0019\u0001!B\u0013Q\u0005\"B2\u0001\t\u0003I\u0005\"\u00023\u0001\t\u0003I\u0005\"\u0002)\u0001\t\u0003)\u0007b\u00026\u0001\u0003\u0003%\ta\u001b\u0005\b]\u0002\t\n\u0011\"\u0001p\u0011\u001dQ\b!%A\u0005\u0002=Dqa\u001f\u0001\u0002\u0002\u0013\u0005C\u0010\u0003\u0005\u0002\f\u0001\t\t\u0011\"\u0001J\u0011%\ti\u0001AA\u0001\n\u0003\ty\u0001C\u0005\u0002\u001a\u0001\t\t\u0011\"\u0011\u0002\u001c!I\u0011\u0011\u0006\u0001\u0002\u0002\u0013\u0005\u00111\u0006\u0005\n\u0003k\u0001\u0011\u0011!C!\u0003oA\u0011\"a\u000f\u0001\u0003\u0003%\t%!\u0010\t\u0013\u0005}\u0002!!A\u0005B\u0005\u0005\u0003\"CA\"\u0001\u0005\u0005I\u0011IA#\u000f%\t)&JA\u0001\u0012\u0003\t9F\u0002\u0005%K\u0005\u0005\t\u0012AA-\u0011\u0019\u0001F\u0004\"\u0001\u0002r!I\u0011q\b\u000f\u0002\u0002\u0013\u0015\u0013\u0011\t\u0005\n\u0003gb\u0012\u0011!CA\u0003kB\u0001\"a\u001f\u001d#\u0003%\ta\u001c\u0005\n\u0003{b\u0012\u0011!CA\u0003\u007fB\u0001\"!%\u001d#\u0003%\ta\u001c\u0005\n\u0003'c\u0012\u0011!C\u0005\u0003+\u0013Qe\u00159be.d\u0015n\u001d;f]\u0016\u00148\u000b]3dk2\fG/\u001b<f)\u0006\u001c8nU;c[&$H/\u001a3\u000b\u0005\u0019:\u0013!C:dQ\u0016$W\u000f\\3s\u0015\tA\u0013&A\u0003ta\u0006\u00148N\u0003\u0002+W\u00051\u0011\r]1dQ\u0016T\u0011\u0001L\u0001\u0004_J<7\u0001A\n\u0006\u0001=*\u0014\b\u0010\t\u0003aMj\u0011!\r\u0006\u0002e\u0005)1oY1mC&\u0011A'\r\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y:T\"A\u0013\n\u0005a*#AE*qCJ\\G*[:uK:,'/\u0012<f]R\u0004\"\u0001\r\u001e\n\u0005m\n$a\u0002)s_\u0012,8\r\u001e\t\u0003{\u0015s!AP\"\u000f\u0005}\u0012U\"\u0001!\u000b\u0005\u0005k\u0013A\u0002\u001fs_>$h(C\u00013\u0013\t!\u0015'A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0019;%\u0001D*fe&\fG.\u001b>bE2,'B\u0001#2\u0003\u001d\u0019H/Y4f\u0013\u0012,\u0012A\u0013\t\u0003a-K!\u0001T\u0019\u0003\u0007%sG/\u0001\u0005ti\u0006<W-\u00133!\u00039\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\fqb\u001d;bO\u0016\fE\u000f^3naRLE\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007I\u001bF\u000b\u0005\u00027\u0001!)\u0001*\u0002a\u0001\u0015\"9a*\u0002I\u0001\u0002\u0004Q\u0015AC0uCN\\\u0017J\u001c3fq\u0006qq\f^1tW&sG-\u001a=`I\u0015\fHC\u0001-\\!\t\u0001\u0014,\u0003\u0002[c\t!QK\\5u\u0011\u001dav!!AA\u0002)\u000b1\u0001\u001f\u00132\u0003-yF/Y:l\u0013:$W\r\u001f\u0011\u0002\u0019}\u0003\u0018M\u001d;ji&|g.\u00133\u0002!}\u0003\u0018M\u001d;ji&|g.\u00133`I\u0015\fHC\u0001-b\u0011\u001da&\"!AA\u0002)\u000bQb\u00189beRLG/[8o\u0013\u0012\u0004\u0013!\u0003;bg.Le\u000eZ3y\u0003-\u0001\u0018M\u001d;ji&|g.\u00133\u0015\u000bI3w\r[5\t\u000b!s\u0001\u0019\u0001&\t\u000b9s\u0001\u0019\u0001&\t\u000b\rt\u0001\u0019\u0001&\t\u000b\u0011t\u0001\u0019\u0001&\u0002\t\r|\u0007/\u001f\u000b\u0004%2l\u0007b\u0002%\u0010!\u0003\u0005\rA\u0013\u0005\b\u001d>\u0001\n\u00111\u0001K\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\u0012\u0001\u001d\u0016\u0003\u0015F\\\u0013A\u001d\t\u0003gbl\u0011\u0001\u001e\u0006\u0003kZ\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005]\f\u0014AC1o]>$\u0018\r^5p]&\u0011\u0011\u0010\u001e\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0003u\u00042A`A\u0004\u001b\u0005y(\u0002BA\u0001\u0003\u0007\tA\u0001\\1oO*\u0011\u0011QA\u0001\u0005U\u00064\u0018-C\u0002\u0002\n}\u0014aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003#\t9\u0002E\u00021\u0003'I1!!\u00062\u0005\r\te.\u001f\u0005\b9R\t\t\u00111\u0001K\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XCAA\u000f!\u0019\ty\"!\n\u0002\u00125\u0011\u0011\u0011\u0005\u0006\u0004\u0003G\t\u0014AC2pY2,7\r^5p]&!\u0011qEA\u0011\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\u00055\u00121\u0007\t\u0004a\u0005=\u0012bAA\u0019c\t9!i\\8mK\u0006t\u0007\u0002\u0003/\u0017\u0003\u0003\u0005\r!!\u0005\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0004{\u0006e\u0002b\u0002/\u0018\u0003\u0003\u0005\rAS\u0001\tQ\u0006\u001c\bnQ8eKR\t!*\u0001\u0005u_N#(/\u001b8h)\u0005i\u0018AB3rk\u0006d7\u000f\u0006\u0003\u0002.\u0005\u001d\u0003\u0002\u0003/\u001b\u0003\u0003\u0005\r!!\u0005)\u0007\u0001\tY\u0005\u0005\u0003\u0002N\u0005ESBAA(\u0015\t9x%\u0003\u0003\u0002T\u0005=#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!J*qCJ\\G*[:uK:,'o\u00159fGVd\u0017\r^5wKR\u000b7o[*vE6LG\u000f^3e!\t1DdE\u0003\u001d\u00037\n9\u0007E\u0004\u0002^\u0005\r$J\u0013*\u000e\u0005\u0005}#bAA1c\u00059!/\u001e8uS6,\u0017\u0002BA3\u0003?\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c83!\u0011\tI'a\u001c\u000e\u0005\u0005-$\u0002BA7\u0003\u0007\t!![8\n\u0007\u0019\u000bY\u0007\u0006\u0002\u0002X\u0005)\u0011\r\u001d9msR)!+a\u001e\u0002z!)\u0001j\ba\u0001\u0015\"9aj\bI\u0001\u0002\u0004Q\u0015aD1qa2LH\u0005Z3gCVdG\u000f\n\u001a\u0002\u000fUt\u0017\r\u001d9msR!\u0011\u0011QAG!\u0015\u0001\u00141QAD\u0013\r\t))\r\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000bA\nII\u0013&\n\u0007\u0005-\u0015G\u0001\u0004UkBdWM\r\u0005\t\u0003\u001f\u000b\u0013\u0011!a\u0001%\u0006\u0019\u0001\u0010\n\u0019\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t9\nE\u0002\u007f\u00033K1!a'\u0000\u0005\u0019y%M[3di\u0002"
)
public class SparkListenerSpeculativeTaskSubmitted implements SparkListenerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;
   private int _taskIndex;
   private int _partitionId;

   public static int $lessinit$greater$default$2() {
      return SparkListenerSpeculativeTaskSubmitted$.MODULE$.$lessinit$greater$default$2();
   }

   public static Option unapply(final SparkListenerSpeculativeTaskSubmitted x$0) {
      return SparkListenerSpeculativeTaskSubmitted$.MODULE$.unapply(x$0);
   }

   public static int apply$default$2() {
      return SparkListenerSpeculativeTaskSubmitted$.MODULE$.apply$default$2();
   }

   public static SparkListenerSpeculativeTaskSubmitted apply(final int stageId, final int stageAttemptId) {
      return SparkListenerSpeculativeTaskSubmitted$.MODULE$.apply(stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return SparkListenerSpeculativeTaskSubmitted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return SparkListenerSpeculativeTaskSubmitted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public boolean logEvent() {
      return SparkListenerEvent.logEvent$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   private int _taskIndex() {
      return this._taskIndex;
   }

   private void _taskIndex_$eq(final int x$1) {
      this._taskIndex = x$1;
   }

   private int _partitionId() {
      return this._partitionId;
   }

   private void _partitionId_$eq(final int x$1) {
      this._partitionId = x$1;
   }

   public int taskIndex() {
      return this._taskIndex();
   }

   public int partitionId() {
      return this._partitionId();
   }

   public SparkListenerSpeculativeTaskSubmitted copy(final int stageId, final int stageAttemptId) {
      return new SparkListenerSpeculativeTaskSubmitted(stageId, stageAttemptId);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "SparkListenerSpeculativeTaskSubmitted";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
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
      return x$1 instanceof SparkListenerSpeculativeTaskSubmitted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "stageId";
         }
         case 1 -> {
            return "stageAttemptId";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      return Statics.finalizeHash(var1, 2);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label38: {
            if (x$1 instanceof SparkListenerSpeculativeTaskSubmitted) {
               SparkListenerSpeculativeTaskSubmitted var4 = (SparkListenerSpeculativeTaskSubmitted)x$1;
               if (this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId() && var4.canEqual(this)) {
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

   public SparkListenerSpeculativeTaskSubmitted(final int stageId, final int stageAttemptId) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      SparkListenerEvent.$init$(this);
      Product.$init$(this);
      this._taskIndex = -1;
      this._partitionId = -1;
   }

   public SparkListenerSpeculativeTaskSubmitted(final int stageId, final int stageAttemptId, final int taskIndex, final int partitionId) {
      this(stageId, stageAttemptId);
      this._partitionId_$eq(partitionId);
      this._taskIndex_$eq(taskIndex);
   }
}
