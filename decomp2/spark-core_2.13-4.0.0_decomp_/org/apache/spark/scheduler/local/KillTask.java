package org.apache.spark.scheduler.local;

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
   bytes = "\u0006\u0005\u0005\u0005e\u0001\u0002\u000f\u001e\t\"B\u0001B\u0010\u0001\u0003\u0016\u0004%\ta\u0010\u0005\t\u0007\u0002\u0011\t\u0012)A\u0005\u0001\"AA\t\u0001BK\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\tE\t\u0015!\u0003G\u0011!Q\u0005A!f\u0001\n\u0003Y\u0005\u0002\u0003+\u0001\u0005#\u0005\u000b\u0011\u0002'\t\u000bU\u0003A\u0011\u0001,\t\u000fq\u0003\u0011\u0011!C\u0001;\"9\u0011\rAI\u0001\n\u0003\u0011\u0007bB7\u0001#\u0003%\tA\u001c\u0005\ba\u0002\t\n\u0011\"\u0001r\u0011\u001d\u0019\b!!A\u0005BQDq\u0001 \u0001\u0002\u0002\u0013\u0005Q\u0010C\u0005\u0002\u0004\u0001\t\t\u0011\"\u0001\u0002\u0006!I\u0011\u0011\u0003\u0001\u0002\u0002\u0013\u0005\u00131\u0003\u0005\n\u0003C\u0001\u0011\u0011!C\u0001\u0003GA\u0011\"a\n\u0001\u0003\u0003%\t%!\u000b\t\u0013\u00055\u0002!!A\u0005B\u0005=\u0002\"CA\u0019\u0001\u0005\u0005I\u0011IA\u001a\u0011%\t)\u0004AA\u0001\n\u0003\n9dB\u0005\u0002<u\t\t\u0011#\u0003\u0002>\u0019AA$HA\u0001\u0012\u0013\ty\u0004\u0003\u0004V-\u0011\u0005\u0011q\u000b\u0005\n\u0003c1\u0012\u0011!C#\u0003gA\u0011\"!\u0017\u0017\u0003\u0003%\t)a\u0017\t\u0013\u0005\rd#!A\u0005\u0002\u0006\u0015\u0004\"CA<-\u0005\u0005I\u0011BA=\u0005!Y\u0015\u000e\u001c7UCN\\'B\u0001\u0010 \u0003\u0015awnY1m\u0015\t\u0001\u0013%A\u0005tG\",G-\u001e7fe*\u0011!eI\u0001\u0006gB\f'o\u001b\u0006\u0003I\u0015\na!\u00199bG\",'\"\u0001\u0014\u0002\u0007=\u0014xm\u0001\u0001\u0014\t\u0001IsF\r\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005)\u0002\u0014BA\u0019,\u0005\u001d\u0001&o\u001c3vGR\u0004\"aM\u001e\u000f\u0005QJdBA\u001b9\u001b\u00051$BA\u001c(\u0003\u0019a$o\\8u}%\tA&\u0003\u0002;W\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u001f>\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQ4&\u0001\u0004uCN\\\u0017\nZ\u000b\u0002\u0001B\u0011!&Q\u0005\u0003\u0005.\u0012A\u0001T8oO\u00069A/Y:l\u0013\u0012\u0004\u0013aD5oi\u0016\u0014(/\u001e9u)\"\u0014X-\u00193\u0016\u0003\u0019\u0003\"AK$\n\u0005![#a\u0002\"p_2,\u0017M\\\u0001\u0011S:$XM\u001d:vaR$\u0006N]3bI\u0002\naA]3bg>tW#\u0001'\u0011\u00055\u000bfB\u0001(P!\t)4&\u0003\u0002QW\u00051\u0001K]3eK\u001aL!AU*\u0003\rM#(/\u001b8h\u0015\t\u00016&A\u0004sK\u0006\u001cxN\u001c\u0011\u0002\rqJg.\u001b;?)\u00119\u0016LW.\u0011\u0005a\u0003Q\"A\u000f\t\u000by:\u0001\u0019\u0001!\t\u000b\u0011;\u0001\u0019\u0001$\t\u000b);\u0001\u0019\u0001'\u0002\t\r|\u0007/\u001f\u000b\u0005/z{\u0006\rC\u0004?\u0011A\u0005\t\u0019\u0001!\t\u000f\u0011C\u0001\u0013!a\u0001\r\"9!\n\u0003I\u0001\u0002\u0004a\u0015AD2paf$C-\u001a4bk2$H%M\u000b\u0002G*\u0012\u0001\tZ\u0016\u0002KB\u0011am[\u0007\u0002O*\u0011\u0001.[\u0001\nk:\u001c\u0007.Z2lK\u0012T!A[\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002mO\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\tqN\u000b\u0002GI\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aT#\u0001:+\u00051#\u0017!\u00049s_\u0012,8\r\u001e)sK\u001aL\u00070F\u0001v!\t180D\u0001x\u0015\tA\u00180\u0001\u0003mC:<'\"\u0001>\u0002\t)\fg/Y\u0005\u0003%^\fA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A \t\u0003U}L1!!\u0001,\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t9!!\u0004\u0011\u0007)\nI!C\u0002\u0002\f-\u00121!\u00118z\u0011!\tyADA\u0001\u0002\u0004q\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002\u0016A1\u0011qCA\u000f\u0003\u000fi!!!\u0007\u000b\u0007\u0005m1&\u0001\u0006d_2dWm\u0019;j_:LA!a\b\u0002\u001a\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\r1\u0015Q\u0005\u0005\n\u0003\u001f\u0001\u0012\u0011!a\u0001\u0003\u000f\t!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR\u0019Q/a\u000b\t\u0011\u0005=\u0011#!AA\u0002y\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002}\u0006AAo\\*ue&tw\rF\u0001v\u0003\u0019)\u0017/^1mgR\u0019a)!\u000f\t\u0013\u0005=A#!AA\u0002\u0005\u001d\u0011\u0001C&jY2$\u0016m]6\u0011\u0005a32#\u0002\f\u0002B\u00055\u0003\u0003CA\"\u0003\u0013\u0002e\tT,\u000e\u0005\u0005\u0015#bAA$W\u00059!/\u001e8uS6,\u0017\u0002BA&\u0003\u000b\u0012\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84!\u0011\ty%!\u0016\u000e\u0005\u0005E#bAA*s\u0006\u0011\u0011n\\\u0005\u0004y\u0005ECCAA\u001f\u0003\u0015\t\u0007\u000f\u001d7z)\u001d9\u0016QLA0\u0003CBQAP\rA\u0002\u0001CQ\u0001R\rA\u0002\u0019CQAS\rA\u00021\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002h\u0005M\u0004#\u0002\u0016\u0002j\u00055\u0014bAA6W\t1q\n\u001d;j_:\u0004bAKA8\u0001\u001ac\u0015bAA9W\t1A+\u001e9mKNB\u0001\"!\u001e\u001b\u0003\u0003\u0005\raV\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAA>!\r1\u0018QP\u0005\u0004\u0003\u007f:(AB(cU\u0016\u001cG\u000f"
)
public class KillTask implements Product, Serializable {
   private final long taskId;
   private final boolean interruptThread;
   private final String reason;

   public static Option unapply(final KillTask x$0) {
      return KillTask$.MODULE$.unapply(x$0);
   }

   public static KillTask apply(final long taskId, final boolean interruptThread, final String reason) {
      return KillTask$.MODULE$.apply(taskId, interruptThread, reason);
   }

   public static Function1 tupled() {
      return KillTask$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return KillTask$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public long taskId() {
      return this.taskId;
   }

   public boolean interruptThread() {
      return this.interruptThread;
   }

   public String reason() {
      return this.reason;
   }

   public KillTask copy(final long taskId, final boolean interruptThread, final String reason) {
      return new KillTask(taskId, interruptThread, reason);
   }

   public long copy$default$1() {
      return this.taskId();
   }

   public boolean copy$default$2() {
      return this.interruptThread();
   }

   public String copy$default$3() {
      return this.reason();
   }

   public String productPrefix() {
      return "KillTask";
   }

   public int productArity() {
      return 3;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToLong(this.taskId());
         }
         case 1 -> {
            return BoxesRunTime.boxToBoolean(this.interruptThread());
         }
         case 2 -> {
            return this.reason();
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
      return x$1 instanceof KillTask;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "taskId";
         }
         case 1 -> {
            return "interruptThread";
         }
         case 2 -> {
            return "reason";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.longHash(this.taskId()));
      var1 = Statics.mix(var1, this.interruptThread() ? 1231 : 1237);
      var1 = Statics.mix(var1, Statics.anyHash(this.reason()));
      return Statics.finalizeHash(var1, 3);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var6;
      if (this != x$1) {
         label55: {
            if (x$1 instanceof KillTask) {
               KillTask var4 = (KillTask)x$1;
               if (this.taskId() == var4.taskId() && this.interruptThread() == var4.interruptThread()) {
                  label48: {
                     String var10000 = this.reason();
                     String var5 = var4.reason();
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

   public KillTask(final long taskId, final boolean interruptThread, final String reason) {
      this.taskId = taskId;
      this.interruptThread = interruptThread;
      this.reason = reason;
      Product.$init$(this);
   }
}
