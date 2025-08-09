package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005u3qAA\u0002\u0011\u0002G\u0005!\u0002C\u0003\u0012\u0001\u0019\u0005!C\u0001\u0007K_\n\u001cVOY7jiR,'O\u0003\u0002\u0005\u000b\u0005)1\u000f]1sW*\u0011aaB\u0001\u0007CB\f7\r[3\u000b\u0003!\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0006\u0011\u00051yQ\"A\u0007\u000b\u00039\tQa]2bY\u0006L!\u0001E\u0007\u0003\r\u0005s\u0017PU3g\u0003%\u0019XOY7ji*{'-\u0006\u0003\u0014W}RBC\u0002\u000b$[\u0005K\u0015\u000bE\u0002\u0016-ai\u0011aA\u0005\u0003/\r\u0011ABR;ukJ,\u0017i\u0019;j_:\u0004\"!\u0007\u000e\r\u0001\u0011)1$\u0001b\u00019\t\t!+\u0005\u0002\u001eAA\u0011ABH\u0005\u0003?5\u0011qAT8uQ&tw\r\u0005\u0002\rC%\u0011!%\u0004\u0002\u0004\u0003:L\b\"\u0002\u0013\u0002\u0001\u0004)\u0013a\u0001:eIB\u0019a\u0005\u000b\u0016\u000e\u0003\u001dR!\u0001J\u0002\n\u0005%:#a\u0001*E\tB\u0011\u0011d\u000b\u0003\u0006Y\u0005\u0011\r\u0001\b\u0002\u0002)\")a&\u0001a\u0001_\u0005\u0001\u0002O]8dKN\u001c\b+\u0019:uSRLwN\u001c\t\u0005\u0019A\u0012d(\u0003\u00022\u001b\tIa)\u001e8di&|g.\r\t\u0004gmRcB\u0001\u001b:\u001d\t)\u0004(D\u00017\u0015\t9\u0014\"\u0001\u0004=e>|GOP\u0005\u0002\u001d%\u0011!(D\u0001\ba\u0006\u001c7.Y4f\u0013\taTH\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tQT\u0002\u0005\u0002\u001a\u007f\u0011)\u0001)\u0001b\u00019\t\tQ\u000bC\u0003C\u0003\u0001\u00071)\u0001\u0006qCJ$\u0018\u000e^5p]N\u00042a\r#G\u0013\t)UHA\u0002TKF\u0004\"\u0001D$\n\u0005!k!aA%oi\")!*\u0001a\u0001\u0017\u0006i!/Z:vYRD\u0015M\u001c3mKJ\u0004R\u0001\u0004'G}9K!!T\u0007\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004C\u0001\u0007P\u0013\t\u0001VB\u0001\u0003V]&$\bB\u0002*\u0002\t\u0003\u00071+\u0001\u0006sKN,H\u000e\u001e$v]\u000e\u00042\u0001\u0004+\u0019\u0013\t)VB\u0001\u0005=Eft\u0017-\\3?Q\t\u0001q\u000b\u0005\u0002Y76\t\u0011L\u0003\u0002[\u0007\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005qK&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public interface JobSubmitter {
   FutureAction submitJob(final RDD rdd, final Function1 processPartition, final Seq partitions, final Function2 resultHandler, final Function0 resultFunc);
}
