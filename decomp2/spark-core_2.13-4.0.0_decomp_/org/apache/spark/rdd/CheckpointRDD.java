package org.apache.spark.rdd;

import org.apache.spark.SparkContext;
import scala.collection.immutable.Nil.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00013aa\u0002\u0005\u0002\u0002)\u0001\u0002\u0002C\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u0011)\u0002!1!Q\u0001\f-BQ!\r\u0001\u0005\u0002IBQa\u000e\u0001\u0005BaBQ\u0001\u0010\u0001\u0005BaBQ!\u0010\u0001\u0005By\u0012Qb\u00115fG.\u0004x.\u001b8u%\u0012#%BA\u0005\u000b\u0003\r\u0011H\r\u001a\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sOV\u0011\u0011\u0003G\n\u0003\u0001I\u00012a\u0005\u000b\u0017\u001b\u0005A\u0011BA\u000b\t\u0005\r\u0011F\t\u0012\t\u0003/aa\u0001\u0001B\u0003\u001a\u0001\t\u00071DA\u0001U\u0007\u0001\t\"\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\u000f9{G\u000f[5oOB\u0011QdI\u0005\u0003Iy\u00111!\u00118z\u0003\t\u00198\r\u0005\u0002(Q5\t!\"\u0003\u0002*\u0015\ta1\u000b]1sW\u000e{g\u000e^3yi\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u00071zc#D\u0001.\u0015\tqc$A\u0004sK\u001adWm\u0019;\n\u0005Aj#\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\t\u0019d\u0007\u0006\u00025kA\u00191\u0003\u0001\f\t\u000b)\u001a\u00019A\u0016\t\u000b\u0015\u001a\u0001\u0019\u0001\u0014\u0002\u0019\u0011|7\t[3dWB|\u0017N\u001c;\u0015\u0003e\u0002\"!\b\u001e\n\u0005mr\"\u0001B+oSR\f!b\u00195fG.\u0004x.\u001b8u\u0003=awnY1m\u0007\",7m\u001b9pS:$H#A \u000e\u0003\u0001\u0001"
)
public abstract class CheckpointRDD extends RDD {
   public void doCheckpoint() {
   }

   public void checkpoint() {
   }

   public CheckpointRDD localCheckpoint() {
      return this;
   }

   public CheckpointRDD(final SparkContext sc, final ClassTag evidence$1) {
      super(sc, .MODULE$, evidence$1);
   }
}
