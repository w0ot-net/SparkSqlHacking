package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.rdd.RDD;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u001d3Q!\u0002\u0004\u0002\u00025A\u0001\"\t\u0001\u0003\u0002\u0003\u0006IA\t\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006Y\u00011\t!\f\u0005\u0006K\u0001!\te\u0010\u0002\u0011\u001d\u0006\u0014(o\\<EKB,g\u000eZ3oGfT!a\u0002\u0005\u0002\u000bM\u0004\u0018M]6\u000b\u0005%Q\u0011AB1qC\u000eDWMC\u0001\f\u0003\ry'oZ\u0002\u0001+\tqQc\u0005\u0002\u0001\u001fA\u0019\u0001#E\n\u000e\u0003\u0019I!A\u0005\u0004\u0003\u0015\u0011+\u0007/\u001a8eK:\u001c\u0017\u0010\u0005\u0002\u0015+1\u0001A!\u0002\f\u0001\u0005\u00049\"!\u0001+\u0012\u0005aq\u0002CA\r\u001d\u001b\u0005Q\"\"A\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005uQ\"a\u0002(pi\"Lgn\u001a\t\u00033}I!\u0001\t\u000e\u0003\u0007\u0005s\u00170\u0001\u0003`e\u0012$\u0007cA\u0012''5\tAE\u0003\u0002&\r\u0005\u0019!\u000f\u001a3\n\u0005\u001d\"#a\u0001*E\t\u00061A(\u001b8jiz\"\"AK\u0016\u0011\u0007A\u00011\u0003C\u0003\"\u0005\u0001\u0007!%\u0001\u0006hKR\u0004\u0016M]3oiN$\"AL\u001f\u0011\u0007=:$H\u0004\u00021k9\u0011\u0011\u0007N\u0007\u0002e)\u00111\u0007D\u0001\u0007yI|w\u000e\u001e \n\u0003mI!A\u000e\u000e\u0002\u000fA\f7m[1hK&\u0011\u0001(\u000f\u0002\u0004'\u0016\f(B\u0001\u001c\u001b!\tI2(\u0003\u0002=5\t\u0019\u0011J\u001c;\t\u000by\u001a\u0001\u0019\u0001\u001e\u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u000b\u0002E!\u0012\u0001!\u0011\t\u0003\u0005\u0016k\u0011a\u0011\u0006\u0003\t\u001a\t!\"\u00198o_R\fG/[8o\u0013\t15I\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public abstract class NarrowDependency extends Dependency {
   private final RDD _rdd;

   public abstract Seq getParents(final int partitionId);

   public RDD rdd() {
      return this._rdd;
   }

   public NarrowDependency(final RDD _rdd) {
      this._rdd = _rdd;
   }
}
