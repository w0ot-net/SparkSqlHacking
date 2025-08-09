package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Qa\u0003\u0007\u0001\u001dQA\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\ta\u0001\u0011)\u0019!C\u0001c!A\u0011\b\u0001B\u0001B\u0003%!\u0007C\u0003;\u0001\u0011\u00051\bC\u0003A\u0001\u0011\u0005\u0013i\u0002\u0005C\u0019\u0005\u0005\t\u0012\u0001\bD\r!YA\"!A\t\u00029!\u0005\"\u0002\u001e\b\t\u0003\u0001\u0006bB)\b#\u0003%\tA\u0015\u0005\b;\u001e\t\t\u0011\"\u0003_\u0005}!\u0016m]6D_6\u0004H.\u001a;j_:d\u0015n\u001d;f]\u0016\u0014X\t_2faRLwN\u001c\u0006\u0003\u001b9\tA!\u001e;jY*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0005\u0002\u0001+A\u0011a#\t\b\u0003/yq!\u0001\u0007\u000f\u000e\u0003eQ!AG\u000e\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?\u0001\nq\u0001]1dW\u0006<WMC\u0001\u001e\u0013\t\u00113E\u0001\tSk:$\u0018.\\3Fq\u000e,\u0007\u000f^5p]*\u0011q\u0004I\u0001\u000eKJ\u0014xN]'fgN\fw-Z:\u0011\u0007Y1\u0003&\u0003\u0002(G\t\u00191+Z9\u0011\u0005%jcB\u0001\u0016,!\tA\u0002%\u0003\u0002-A\u00051\u0001K]3eK\u001aL!AL\u0018\u0003\rM#(/\u001b8h\u0015\ta\u0003%A\u0007qe\u00164\u0018n\\;t\u000bJ\u0014xN]\u000b\u0002eA\u00191\u0007\u000e\u001c\u000e\u0003\u0001J!!\u000e\u0011\u0003\r=\u0003H/[8o!\t1r'\u0003\u00029G\tIA\u000b\u001b:po\u0006\u0014G.Z\u0001\u000faJ,g/[8vg\u0016\u0013(o\u001c:!\u0003\u0019a\u0014N\\5u}Q\u0019AHP \u0011\u0005u\u0002Q\"\u0001\u0007\t\u000b\u0011\"\u0001\u0019A\u0013\t\u000fA\"\u0001\u0013!a\u0001e\u0005Qq-\u001a;NKN\u001c\u0018mZ3\u0015\u0003!\nq\u0004V1tW\u000e{W\u000e\u001d7fi&|g\u000eT5ti\u0016tWM]#yG\u0016\u0004H/[8o!\titaE\u0002\b\u000b\"\u0003\"a\r$\n\u0005\u001d\u0003#AB!osJ+g\r\u0005\u0002J\u001d6\t!J\u0003\u0002L\u0019\u0006\u0011\u0011n\u001c\u0006\u0002\u001b\u0006!!.\u0019<b\u0013\ty%J\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001D\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%eU\t1K\u000b\u00023).\nQ\u000b\u0005\u0002W76\tqK\u0003\u0002Y3\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u00035\u0002\n!\"\u00198o_R\fG/[8o\u0013\tavKA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012a\u0018\t\u0003A\u000el\u0011!\u0019\u0006\u0003E2\u000bA\u0001\\1oO&\u0011A-\u0019\u0002\u0007\u001f\nTWm\u0019;"
)
public class TaskCompletionListenerException extends RuntimeException {
   private final Seq errorMessages;
   private final Option previousError;

   public static Option $lessinit$greater$default$2() {
      return TaskCompletionListenerException$.MODULE$.$lessinit$greater$default$2();
   }

   public Option previousError() {
      return this.previousError;
   }

   public String getMessage() {
      String listenerErrorMessage = this.errorMessages.size() == 1 ? (String)this.errorMessages.head() : ((IterableOnceOps)((IterableOps)this.errorMessages.zipWithIndex()).map((x0$1) -> {
         if (x0$1 != null) {
            String msg = (String)x0$1._1();
            int i = x0$1._2$mcI$sp();
            return "Exception " + i + ": " + msg;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("\n");
      String previousErrorMessage = (String)this.previousError().map((e) -> {
         String var10000 = e.getMessage();
         return "\n\nPrevious exception in task: " + var10000 + "\n" + .MODULE$.wrapRefArray((Object[])e.getStackTrace()).mkString("\t", "\n\t", "");
      }).getOrElse(() -> "");
      return listenerErrorMessage + previousErrorMessage;
   }

   public TaskCompletionListenerException(final Seq errorMessages, final Option previousError) {
      this.errorMessages = errorMessages;
      this.previousError = previousError;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
