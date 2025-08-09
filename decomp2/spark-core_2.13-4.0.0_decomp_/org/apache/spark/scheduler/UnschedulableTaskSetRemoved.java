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
   bytes = "\u0006\u0005\u0005=c!B\r\u001b\u0001j\u0011\u0003\u0002C\u001f\u0001\u0005+\u0007I\u0011\u0001 \t\u0011\t\u0003!\u0011#Q\u0001\n}B\u0001b\u0011\u0001\u0003\u0016\u0004%\tA\u0010\u0005\t\t\u0002\u0011\t\u0012)A\u0005\u007f!)Q\t\u0001C\u0001\r\"9!\nAA\u0001\n\u0003Y\u0005b\u0002(\u0001#\u0003%\ta\u0014\u0005\b5\u0002\t\n\u0011\"\u0001P\u0011\u001dY\u0006!!A\u0005BqCq!\u001a\u0001\u0002\u0002\u0013\u0005a\bC\u0004g\u0001\u0005\u0005I\u0011A4\t\u000f5\u0004\u0011\u0011!C!]\"9Q\u000fAA\u0001\n\u00031\bbB>\u0001\u0003\u0003%\t\u0005 \u0005\b}\u0002\t\t\u0011\"\u0011\u0000\u0011%\t\t\u0001AA\u0001\n\u0003\n\u0019\u0001C\u0005\u0002\u0006\u0001\t\t\u0011\"\u0011\u0002\b\u001dQ\u00111\u0002\u000e\u0002\u0002#\u0005!$!\u0004\u0007\u0013eQ\u0012\u0011!E\u00015\u0005=\u0001BB#\u0014\t\u0003\t9\u0003C\u0005\u0002\u0002M\t\t\u0011\"\u0012\u0002\u0004!I\u0011\u0011F\n\u0002\u0002\u0013\u0005\u00151\u0006\u0005\n\u0003c\u0019\u0012\u0011!CA\u0003gA\u0011\"!\u0012\u0014\u0003\u0003%I!a\u0012\u00037Us7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u%\u0016lwN^3e\u0015\tYB$A\u0005tG\",G-\u001e7fe*\u0011QDH\u0001\u0006gB\f'o\u001b\u0006\u0003?\u0001\na!\u00199bG\",'\"A\u0011\u0002\u0007=\u0014xmE\u0003\u0001G%j\u0003\u0007\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASE\u0001\u0004B]f\u0014VM\u001a\t\u0003U-j\u0011AG\u0005\u0003Yi\u0011\u0011\u0003R!H'\u000eDW\rZ;mKJ,e/\u001a8u!\t!c&\u0003\u00020K\t9\u0001K]8ek\u000e$\bCA\u0019;\u001d\t\u0011\u0004H\u0004\u00024o5\tAG\u0003\u00026m\u00051AH]8piz\u001a\u0001!C\u0001'\u0013\tIT%A\u0004qC\u000e\\\u0017mZ3\n\u0005mb$\u0001D*fe&\fG.\u001b>bE2,'BA\u001d&\u0003\u001d\u0019H/Y4f\u0013\u0012,\u0012a\u0010\t\u0003I\u0001K!!Q\u0013\u0003\u0007%sG/\u0001\u0005ti\u0006<W-\u00133!\u00039\u0019H/Y4f\u0003R$X-\u001c9u\u0013\u0012\fqb\u001d;bO\u0016\fE\u000f^3naRLE\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007\u001dC\u0015\n\u0005\u0002+\u0001!)Q(\u0002a\u0001\u007f!)1)\u0002a\u0001\u007f\u0005!1m\u001c9z)\r9E*\u0014\u0005\b{\u0019\u0001\n\u00111\u0001@\u0011\u001d\u0019e\u0001%AA\u0002}\nabY8qs\u0012\"WMZ1vYR$\u0013'F\u0001QU\ty\u0014kK\u0001S!\t\u0019\u0006,D\u0001U\u0015\t)f+A\u0005v]\u000eDWmY6fI*\u0011q+J\u0001\u000bC:tw\u000e^1uS>t\u0017BA-U\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\tQ\f\u0005\u0002_G6\tqL\u0003\u0002aC\u0006!A.\u00198h\u0015\u0005\u0011\u0017\u0001\u00026bm\u0006L!\u0001Z0\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$\"\u0001[6\u0011\u0005\u0011J\u0017B\u00016&\u0005\r\te.\u001f\u0005\bY.\t\t\u00111\u0001@\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\tq\u000eE\u0002qg\"l\u0011!\u001d\u0006\u0003e\u0016\n!bY8mY\u0016\u001cG/[8o\u0013\t!\u0018O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dGCA<{!\t!\u00030\u0003\u0002zK\t9!i\\8mK\u0006t\u0007b\u00027\u000e\u0003\u0003\u0005\r\u0001[\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0002^{\"9ANDA\u0001\u0002\u0004y\u0014\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003}\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002;\u00061Q-];bYN$2a^A\u0005\u0011\u001da\u0017#!AA\u0002!\f1$\u00168tG\",G-\u001e7bE2,G+Y:l'\u0016$(+Z7pm\u0016$\u0007C\u0001\u0016\u0014'\u0015\u0019\u0012\u0011CA\u000f!\u001d\t\u0019\"!\u0007@\u007f\u001dk!!!\u0006\u000b\u0007\u0005]Q%A\u0004sk:$\u0018.\\3\n\t\u0005m\u0011Q\u0003\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014\u0004\u0003BA\u0010\u0003Ki!!!\t\u000b\u0007\u0005\r\u0012-\u0001\u0002j_&\u00191(!\t\u0015\u0005\u00055\u0011!B1qa2LH#B$\u0002.\u0005=\u0002\"B\u001f\u0017\u0001\u0004y\u0004\"B\"\u0017\u0001\u0004y\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0003k\t\t\u0005E\u0003%\u0003o\tY$C\u0002\u0002:\u0015\u0012aa\u00149uS>t\u0007#\u0002\u0013\u0002>}z\u0014bAA K\t1A+\u001e9mKJB\u0001\"a\u0011\u0018\u0003\u0003\u0005\raR\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA%!\rq\u00161J\u0005\u0004\u0003\u001bz&AB(cU\u0016\u001cG\u000f"
)
public class UnschedulableTaskSetRemoved implements DAGSchedulerEvent, Product, Serializable {
   private final int stageId;
   private final int stageAttemptId;

   public static Option unapply(final UnschedulableTaskSetRemoved x$0) {
      return UnschedulableTaskSetRemoved$.MODULE$.unapply(x$0);
   }

   public static UnschedulableTaskSetRemoved apply(final int stageId, final int stageAttemptId) {
      return UnschedulableTaskSetRemoved$.MODULE$.apply(stageId, stageAttemptId);
   }

   public static Function1 tupled() {
      return UnschedulableTaskSetRemoved$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return UnschedulableTaskSetRemoved$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public UnschedulableTaskSetRemoved copy(final int stageId, final int stageAttemptId) {
      return new UnschedulableTaskSetRemoved(stageId, stageAttemptId);
   }

   public int copy$default$1() {
      return this.stageId();
   }

   public int copy$default$2() {
      return this.stageAttemptId();
   }

   public String productPrefix() {
      return "UnschedulableTaskSetRemoved";
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
      return x$1 instanceof UnschedulableTaskSetRemoved;
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
            if (x$1 instanceof UnschedulableTaskSetRemoved) {
               UnschedulableTaskSetRemoved var4 = (UnschedulableTaskSetRemoved)x$1;
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

   public UnschedulableTaskSetRemoved(final int stageId, final int stageAttemptId) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      Product.$init$(this);
   }
}
