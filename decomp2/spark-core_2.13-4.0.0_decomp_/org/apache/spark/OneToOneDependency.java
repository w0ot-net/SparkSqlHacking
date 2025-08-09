package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00113A\u0001B\u0003\u0001\u0019!A\u0001\u0005\u0001B\u0001B\u0003%\u0011\u0005C\u0003'\u0001\u0011\u0005q\u0005C\u0003+\u0001\u0011\u00053F\u0001\nP]\u0016$vn\u00148f\t\u0016\u0004XM\u001c3f]\u000eL(B\u0001\u0004\b\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0011\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0015\u0005\u0019qN]4\u0004\u0001U\u0011Q\u0002F\n\u0003\u00019\u00012a\u0004\t\u0013\u001b\u0005)\u0011BA\t\u0006\u0005Aq\u0015M\u001d:po\u0012+\u0007/\u001a8eK:\u001c\u0017\u0010\u0005\u0002\u0014)1\u0001A!B\u000b\u0001\u0005\u00041\"!\u0001+\u0012\u0005]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"a\u0002(pi\"Lgn\u001a\t\u00031yI!aH\r\u0003\u0007\u0005s\u00170A\u0002sI\u0012\u00042A\t\u0013\u0013\u001b\u0005\u0019#B\u0001\u0011\u0006\u0013\t)3EA\u0002S\t\u0012\u000ba\u0001P5oSRtDC\u0001\u0015*!\ry\u0001A\u0005\u0005\u0006A\t\u0001\r!I\u0001\u000bO\u0016$\b+\u0019:f]R\u001cHC\u0001\u0017<!\riS\u0007\u000f\b\u0003]Mr!a\f\u001a\u000e\u0003AR!!M\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0012B\u0001\u001b\u001a\u0003\u001d\u0001\u0018mY6bO\u0016L!AN\u001c\u0003\t1K7\u000f\u001e\u0006\u0003ie\u0001\"\u0001G\u001d\n\u0005iJ\"aA%oi\")Ah\u0001a\u0001q\u0005Y\u0001/\u0019:uSRLwN\\%eQ\t\u0001a\b\u0005\u0002@\u00056\t\u0001I\u0003\u0002B\u000b\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005\r\u0003%\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class OneToOneDependency extends NarrowDependency {
   public List getParents(final int partitionId) {
      return (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{partitionId}));
   }

   public OneToOneDependency(final RDD rdd) {
      super(rdd);
   }
}
