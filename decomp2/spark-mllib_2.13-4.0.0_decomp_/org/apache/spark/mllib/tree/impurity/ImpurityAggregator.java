package org.apache.spark.mllib.tree.impurity;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3aa\u0002\u0005\u0002\u00029!\u0002\u0002\u0003\u0015\u0001\u0005\u000b\u0007I\u0011A\u0015\t\u00115\u0002!\u0011!Q\u0001\n)BQA\f\u0001\u0005\u0002=BQa\r\u0001\u0005\u0002QBQ\u0001\u0012\u0001\u0007\u0002\u0015CQA\u0014\u0001\u0007\u0002=\u0013!#S7qkJLG/_!hOJ,w-\u0019;pe*\u0011\u0011BC\u0001\tS6\u0004XO]5us*\u00111\u0002D\u0001\u0005iJ,WM\u0003\u0002\u000e\u001d\u0005)Q\u000e\u001c7jE*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xmE\u0002\u0001+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007C\u0001\u000f&\u001d\ti2E\u0004\u0002\u001fE5\tqD\u0003\u0002!C\u00051AH]8piz\u001a\u0001!C\u0001\u0019\u0013\t!s#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0019:#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0013\u0018\u0003%\u0019H/\u0019;t'&TX-F\u0001+!\t12&\u0003\u0002-/\t\u0019\u0011J\u001c;\u0002\u0015M$\u0018\r^:TSj,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003aI\u0002\"!\r\u0001\u000e\u0003!AQ\u0001K\u0002A\u0002)\nQ!\\3sO\u0016$B!\u000e\u001dA\u0005B\u0011aCN\u0005\u0003o]\u0011A!\u00168ji\")\u0011\b\u0002a\u0001u\u0005A\u0011\r\u001c7Ti\u0006$8\u000fE\u0002\u0017wuJ!\u0001P\f\u0003\u000b\u0005\u0013(/Y=\u0011\u0005Yq\u0014BA \u0018\u0005\u0019!u.\u001e2mK\")\u0011\t\u0002a\u0001U\u00051qN\u001a4tKRDQa\u0011\u0003A\u0002)\n1b\u001c;iKJ|eMZ:fi\u00061Q\u000f\u001d3bi\u0016$b!\u000e$H\u0011*c\u0005\"B\u001d\u0006\u0001\u0004Q\u0004\"B!\u0006\u0001\u0004Q\u0003\"B%\u0006\u0001\u0004i\u0014!\u00027bE\u0016d\u0007\"B&\u0006\u0001\u0004Q\u0013A\u00038v[N\u000bW\u000e\u001d7fg\")Q*\u0002a\u0001{\u0005a1/Y7qY\u0016<V-[4ii\u0006iq-\u001a;DC2\u001cW\u000f\\1u_J$2\u0001U*U!\t\t\u0014+\u0003\u0002S\u0011\t\u0011\u0012*\u001c9ve&$\u0018pQ1mGVd\u0017\r^8s\u0011\u0015Id\u00011\u0001;\u0011\u0015\te\u00011\u0001+\u0001"
)
public abstract class ImpurityAggregator implements Serializable {
   private final int statsSize;

   public int statsSize() {
      return this.statsSize;
   }

   public void merge(final double[] allStats, final int offset, final int otherOffset) {
      for(int i = 0; i < this.statsSize(); ++i) {
         int var5 = offset + i;
         allStats[var5] += allStats[otherOffset + i];
      }

   }

   public abstract void update(final double[] allStats, final int offset, final double label, final int numSamples, final double sampleWeight);

   public abstract ImpurityCalculator getCalculator(final double[] allStats, final int offset);

   public ImpurityAggregator(final int statsSize) {
      this.statsSize = statsSize;
   }
}
