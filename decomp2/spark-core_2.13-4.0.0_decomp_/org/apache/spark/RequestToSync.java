package org.apache.spark;

import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055h!B\u0016-\u00012\u0012\u0004\u0002C'\u0001\u0005+\u0007I\u0011\u0001(\t\u0011I\u0003!\u0011#Q\u0001\n=C\u0001b\u0015\u0001\u0003\u0016\u0004%\tA\u0014\u0005\t)\u0002\u0011\t\u0012)A\u0005\u001f\"AQ\u000b\u0001BK\u0002\u0013\u0005a\n\u0003\u0005W\u0001\tE\t\u0015!\u0003P\u0011!9\u0006A!f\u0001\n\u0003A\u0006\u0002\u0003/\u0001\u0005#\u0005\u000b\u0011B-\t\u0011u\u0003!Q3A\u0005\u00029C\u0001B\u0018\u0001\u0003\u0012\u0003\u0006Ia\u0014\u0005\t?\u0002\u0011)\u001a!C\u0001\u001d\"A\u0001\r\u0001B\tB\u0003%q\n\u0003\u0005b\u0001\tU\r\u0011\"\u0001c\u0011!Y\u0007A!E!\u0002\u0013\u0019\u0007\u0002\u00037\u0001\u0005+\u0007I\u0011A7\t\u0011Y\u0004!\u0011#Q\u0001\n9DQa\u001e\u0001\u0005\u0002aD\u0011\"!\u0002\u0001\u0003\u0003%\t!a\u0002\t\u0013\u0005e\u0001!%A\u0005\u0002\u0005m\u0001\"CA\u0019\u0001E\u0005I\u0011AA\u000e\u0011%\t\u0019\u0004AI\u0001\n\u0003\tY\u0002C\u0005\u00026\u0001\t\n\u0011\"\u0001\u00028!I\u00111\b\u0001\u0012\u0002\u0013\u0005\u00111\u0004\u0005\n\u0003{\u0001\u0011\u0013!C\u0001\u00037A\u0011\"a\u0010\u0001#\u0003%\t!!\u0011\t\u0013\u0005\u0015\u0003!%A\u0005\u0002\u0005\u001d\u0003\"CA&\u0001\u0005\u0005I\u0011IA'\u0011!\ti\u0006AA\u0001\n\u0003q\u0005\"CA0\u0001\u0005\u0005I\u0011AA1\u0011%\ti\u0007AA\u0001\n\u0003\ny\u0007C\u0005\u0002~\u0001\t\t\u0011\"\u0001\u0002\u0000!I\u0011\u0011\u0012\u0001\u0002\u0002\u0013\u0005\u00131\u0012\u0005\n\u0003\u001f\u0003\u0011\u0011!C!\u0003#C\u0011\"a%\u0001\u0003\u0003%\t%!&\t\u0013\u0005]\u0005!!A\u0005B\u0005euACAOY\u0005\u0005\t\u0012\u0001\u0017\u0002 \u001aI1\u0006LA\u0001\u0012\u0003a\u0013\u0011\u0015\u0005\u0007o\u0016\"\t!!/\t\u0013\u0005MU%!A\u0005F\u0005U\u0005\"CA^K\u0005\u0005I\u0011QA_\u0011%\ty-JA\u0001\n\u0003\u000b\t\u000eC\u0005\u0002d\u0016\n\t\u0011\"\u0003\u0002f\ni!+Z9vKN$Hk\\*z]\u000eT!!\f\u0018\u0002\u000bM\u0004\u0018M]6\u000b\u0005=\u0002\u0014AB1qC\u000eDWMC\u00012\u0003\ry'oZ\n\u0006\u0001MJT\b\u0011\t\u0003i]j\u0011!\u000e\u0006\u0002m\u0005)1oY1mC&\u0011\u0001(\u000e\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iZT\"\u0001\u0017\n\u0005qb#!\u0007\"beJLWM]\"p_J$\u0017N\\1u_JlUm]:bO\u0016\u0004\"\u0001\u000e \n\u0005}*$a\u0002)s_\u0012,8\r\u001e\t\u0003\u0003*s!A\u0011%\u000f\u0005\r;U\"\u0001#\u000b\u0005\u00153\u0015A\u0002\u001fs_>$hh\u0001\u0001\n\u0003YJ!!S\u001b\u0002\u000fA\f7m[1hK&\u00111\n\u0014\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003\u0013V\n\u0001B\\;n)\u0006\u001c8n]\u000b\u0002\u001fB\u0011A\u0007U\u0005\u0003#V\u00121!\u00138u\u0003%qW/\u001c+bg.\u001c\b%A\u0004ti\u0006<W-\u00133\u0002\u0011M$\u0018mZ3JI\u0002\nab\u001d;bO\u0016\fE\u000f^3naRLE-A\bti\u0006<W-\u0011;uK6\u0004H/\u00133!\u00035!\u0018m]6BiR,W\u000e\u001d;JIV\t\u0011\f\u0005\u000255&\u00111,\u000e\u0002\u0005\u0019>tw-\u0001\buCN\\\u0017\t\u001e;f[B$\u0018\n\u001a\u0011\u0002\u0019\t\f'O]5fe\u0016\u0003xn\u00195\u0002\u001b\t\f'O]5fe\u0016\u0003xn\u00195!\u0003-\u0001\u0018M\u001d;ji&|g.\u00133\u0002\u0019A\f'\u000f^5uS>t\u0017\n\u001a\u0011\u0002\u000f5,7o]1hKV\t1\r\u0005\u0002eQ:\u0011QM\u001a\t\u0003\u0007VJ!aZ\u001b\u0002\rA\u0013X\rZ3g\u0013\tI'N\u0001\u0004TiJLgn\u001a\u0006\u0003OV\n\u0001\"\\3tg\u0006<W\rI\u0001\u000ee\u0016\fX/Z:u\u001b\u0016$\bn\u001c3\u0016\u00039\u0004\"a\u001c:\u000f\u0005i\u0002\u0018BA9-\u00035\u0011V-];fgRlU\r\u001e5pI&\u00111\u000f\u001e\u0002\u0006-\u0006dW/Z\u0005\u0003kV\u00121\"\u00128v[\u0016\u0014\u0018\r^5p]\u0006q!/Z9vKN$X*\u001a;i_\u0012\u0004\u0013A\u0002\u001fj]&$h\bF\u0006zundXP`@\u0002\u0002\u0005\r\u0001C\u0001\u001e\u0001\u0011\u0015i\u0015\u00031\u0001P\u0011\u0015\u0019\u0016\u00031\u0001P\u0011\u0015)\u0016\u00031\u0001P\u0011\u00159\u0016\u00031\u0001Z\u0011\u0015i\u0016\u00031\u0001P\u0011\u0015y\u0016\u00031\u0001P\u0011\u0015\t\u0017\u00031\u0001d\u0011\u0015a\u0017\u00031\u0001o\u0003\u0011\u0019w\u000e]=\u0015#e\fI!a\u0003\u0002\u000e\u0005=\u0011\u0011CA\n\u0003+\t9\u0002C\u0004N%A\u0005\t\u0019A(\t\u000fM\u0013\u0002\u0013!a\u0001\u001f\"9QK\u0005I\u0001\u0002\u0004y\u0005bB,\u0013!\u0003\u0005\r!\u0017\u0005\b;J\u0001\n\u00111\u0001P\u0011\u001dy&\u0003%AA\u0002=Cq!\u0019\n\u0011\u0002\u0003\u00071\rC\u0004m%A\u0005\t\u0019\u00018\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\u0004\u0016\u0004\u001f\u0006}1FAA\u0011!\u0011\t\u0019#!\f\u000e\u0005\u0005\u0015\"\u0002BA\u0014\u0003S\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005-R'\u0001\u0006b]:|G/\u0019;j_:LA!a\f\u0002&\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%e\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0003sQ3!WA\u0010\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU\nabY8qs\u0012\"WMZ1vYR$c'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001c\u0016\u0005\u0005\r#fA2\u0002 \u0005q1m\u001c9zI\u0011,g-Y;mi\u0012BTCAA%U\rq\u0017qD\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005=\u0003\u0003BA)\u00037j!!a\u0015\u000b\t\u0005U\u0013qK\u0001\u0005Y\u0006twM\u0003\u0002\u0002Z\u0005!!.\u0019<b\u0013\rI\u00171K\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t\u0019'!\u001b\u0011\u0007Q\n)'C\u0002\u0002hU\u00121!\u00118z\u0011!\tY'HA\u0001\u0002\u0004y\u0015a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002rA1\u00111OA=\u0003Gj!!!\u001e\u000b\u0007\u0005]T'\u0001\u0006d_2dWm\u0019;j_:LA!a\u001f\u0002v\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\t)a\"\u0011\u0007Q\n\u0019)C\u0002\u0002\u0006V\u0012qAQ8pY\u0016\fg\u000eC\u0005\u0002l}\t\t\u00111\u0001\u0002d\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\ty%!$\t\u0011\u0005-\u0004%!AA\u0002=\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002\u001f\u0006AAo\\*ue&tw\r\u0006\u0002\u0002P\u00051Q-];bYN$B!!!\u0002\u001c\"I\u00111N\u0012\u0002\u0002\u0003\u0007\u00111M\u0001\u000e%\u0016\fX/Z:u)>\u001c\u0016P\\2\u0011\u0005i*3#B\u0013\u0002$\u0006=\u0006#DAS\u0003W{ujT-P\u001f\u000et\u00170\u0004\u0002\u0002(*\u0019\u0011\u0011V\u001b\u0002\u000fI,h\u000e^5nK&!\u0011QVAT\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g\u000e\u000f\t\u0005\u0003c\u000b9,\u0004\u0002\u00024*!\u0011QWA,\u0003\tIw.C\u0002L\u0003g#\"!a(\u0002\u000b\u0005\u0004\b\u000f\\=\u0015#e\fy,!1\u0002D\u0006\u0015\u0017qYAe\u0003\u0017\fi\rC\u0003NQ\u0001\u0007q\nC\u0003TQ\u0001\u0007q\nC\u0003VQ\u0001\u0007q\nC\u0003XQ\u0001\u0007\u0011\fC\u0003^Q\u0001\u0007q\nC\u0003`Q\u0001\u0007q\nC\u0003bQ\u0001\u00071\rC\u0003mQ\u0001\u0007a.A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\u0005M\u0017q\u001c\t\u0006i\u0005U\u0017\u0011\\\u0005\u0004\u0003/,$AB(qi&|g\u000eE\u00065\u00037|ujT-P\u001f\u000et\u0017bAAok\t1A+\u001e9mKbB\u0001\"!9*\u0003\u0003\u0005\r!_\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GCAAt!\u0011\t\t&!;\n\t\u0005-\u00181\u000b\u0002\u0007\u001f\nTWm\u0019;"
)
public class RequestToSync implements BarrierCoordinatorMessage, Product {
   private final int numTasks;
   private final int stageId;
   private final int stageAttemptId;
   private final long taskAttemptId;
   private final int barrierEpoch;
   private final int partitionId;
   private final String message;
   private final Enumeration.Value requestMethod;

   public static Option unapply(final RequestToSync x$0) {
      return RequestToSync$.MODULE$.unapply(x$0);
   }

   public static RequestToSync apply(final int numTasks, final int stageId, final int stageAttemptId, final long taskAttemptId, final int barrierEpoch, final int partitionId, final String message, final Enumeration.Value requestMethod) {
      return RequestToSync$.MODULE$.apply(numTasks, stageId, stageAttemptId, taskAttemptId, barrierEpoch, partitionId, message, requestMethod);
   }

   public static Function1 tupled() {
      return RequestToSync$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return RequestToSync$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int numTasks() {
      return this.numTasks;
   }

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public long taskAttemptId() {
      return this.taskAttemptId;
   }

   public int barrierEpoch() {
      return this.barrierEpoch;
   }

   public int partitionId() {
      return this.partitionId;
   }

   public String message() {
      return this.message;
   }

   public Enumeration.Value requestMethod() {
      return this.requestMethod;
   }

   public RequestToSync copy(final int numTasks, final int stageId, final int stageAttemptId, final long taskAttemptId, final int barrierEpoch, final int partitionId, final String message, final Enumeration.Value requestMethod) {
      return new RequestToSync(numTasks, stageId, stageAttemptId, taskAttemptId, barrierEpoch, partitionId, message, requestMethod);
   }

   public int copy$default$1() {
      return this.numTasks();
   }

   public int copy$default$2() {
      return this.stageId();
   }

   public int copy$default$3() {
      return this.stageAttemptId();
   }

   public long copy$default$4() {
      return this.taskAttemptId();
   }

   public int copy$default$5() {
      return this.barrierEpoch();
   }

   public int copy$default$6() {
      return this.partitionId();
   }

   public String copy$default$7() {
      return this.message();
   }

   public Enumeration.Value copy$default$8() {
      return this.requestMethod();
   }

   public String productPrefix() {
      return "RequestToSync";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.numTasks());
         }
         case 1 -> {
            return BoxesRunTime.boxToInteger(this.stageId());
         }
         case 2 -> {
            return BoxesRunTime.boxToInteger(this.stageAttemptId());
         }
         case 3 -> {
            return BoxesRunTime.boxToLong(this.taskAttemptId());
         }
         case 4 -> {
            return BoxesRunTime.boxToInteger(this.barrierEpoch());
         }
         case 5 -> {
            return BoxesRunTime.boxToInteger(this.partitionId());
         }
         case 6 -> {
            return this.message();
         }
         case 7 -> {
            return this.requestMethod();
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
      return x$1 instanceof RequestToSync;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "numTasks";
         }
         case 1 -> {
            return "stageId";
         }
         case 2 -> {
            return "stageAttemptId";
         }
         case 3 -> {
            return "taskAttemptId";
         }
         case 4 -> {
            return "barrierEpoch";
         }
         case 5 -> {
            return "partitionId";
         }
         case 6 -> {
            return "message";
         }
         case 7 -> {
            return "requestMethod";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.numTasks());
      var1 = Statics.mix(var1, this.stageId());
      var1 = Statics.mix(var1, this.stageAttemptId());
      var1 = Statics.mix(var1, Statics.longHash(this.taskAttemptId()));
      var1 = Statics.mix(var1, this.barrierEpoch());
      var1 = Statics.mix(var1, this.partitionId());
      var1 = Statics.mix(var1, Statics.anyHash(this.message()));
      var1 = Statics.mix(var1, Statics.anyHash(this.requestMethod()));
      return Statics.finalizeHash(var1, 8);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var8;
      if (this != x$1) {
         label79: {
            if (x$1 instanceof RequestToSync) {
               RequestToSync var4 = (RequestToSync)x$1;
               if (this.numTasks() == var4.numTasks() && this.stageId() == var4.stageId() && this.stageAttemptId() == var4.stageAttemptId() && this.taskAttemptId() == var4.taskAttemptId() && this.barrierEpoch() == var4.barrierEpoch() && this.partitionId() == var4.partitionId()) {
                  label72: {
                     String var10000 = this.message();
                     String var5 = var4.message();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label72;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label72;
                     }

                     Enumeration.Value var7 = this.requestMethod();
                     Enumeration.Value var6 = var4.requestMethod();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label72;
                        }
                     } else if (!var7.equals(var6)) {
                        break label72;
                     }

                     if (var4.canEqual(this)) {
                        break label79;
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

   public RequestToSync(final int numTasks, final int stageId, final int stageAttemptId, final long taskAttemptId, final int barrierEpoch, final int partitionId, final String message, final Enumeration.Value requestMethod) {
      this.numTasks = numTasks;
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.taskAttemptId = taskAttemptId;
      this.barrierEpoch = barrierEpoch;
      this.partitionId = partitionId;
      this.message = message;
      this.requestMethod = requestMethod;
      Product.$init$(this);
   }
}
