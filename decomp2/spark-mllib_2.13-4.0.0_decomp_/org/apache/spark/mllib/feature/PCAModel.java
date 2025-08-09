package org.apache.spark.mllib.feature;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]3A!\u0003\u0006\u0001+!A\u0001\u0005\u0001BC\u0002\u0013\u0005\u0011\u0005\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003#\u0011!\u0001\u0004A!b\u0001\n\u0003\t\u0004\u0002C\u001d\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001a\t\u0011m\u0002!Q1A\u0005\u0002qB\u0001b\u0011\u0001\u0003\u0002\u0003\u0006I!\u0010\u0005\u0007\u000b\u0002!\tA\u0004$\t\u000b9\u0003A\u0011I(\u0003\u0011A\u001b\u0015)T8eK2T!a\u0003\u0007\u0002\u000f\u0019,\u0017\r^;sK*\u0011QBD\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sO\u000e\u00011c\u0001\u0001\u00179A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\u0004\"!\b\u0010\u000e\u0003)I!a\b\u0006\u0003#Y+7\r^8s)J\fgn\u001d4pe6,'/A\u0001l+\u0005\u0011\u0003CA\f$\u0013\t!\u0003DA\u0002J]RD3!\u0001\u0014-!\t9#&D\u0001)\u0015\tIc\"\u0001\u0006b]:|G/\u0019;j_:L!a\u000b\u0015\u0003\u000bMKgnY3\"\u00035\nQ!\r\u00185]A\n!a\u001b\u0011)\u0007\t1C&\u0001\u0002qGV\t!\u0007\u0005\u00024m5\tAG\u0003\u00026\u0019\u00051A.\u001b8bY\u001eL!a\u000e\u001b\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\u0015\u0004\u0007\u0019b\u0013a\u00019dA!\u001aAA\n\u0017\u0002#\u0015D\b\u000f\\1j]\u0016$g+\u0019:jC:\u001cW-F\u0001>!\t\u0019d(\u0003\u0002@i\tYA)\u001a8tKZ+7\r^8sQ\r)a%Q\u0011\u0002\u0005\u0006)\u0011G\f\u001c/a\u0005\u0011R\r\u001f9mC&tW\r\u001a,be&\fgnY3!Q\r1a%Q\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u001dC%\n\u0014\t\u0003;\u0001AQ\u0001I\u0004A\u0002\tB3\u0001\u0013\u0014-\u0011\u0015\u0001t\u00011\u00013Q\rQe\u0005\f\u0005\u0006w\u001d\u0001\r!\u0010\u0015\u0004\u0019\u001a\n\u0015!\u0003;sC:\u001chm\u001c:n)\t\u00016\u000b\u0005\u00024#&\u0011!\u000b\u000e\u0002\u0007-\u0016\u001cGo\u001c:\t\u000bQC\u0001\u0019\u0001)\u0002\rY,7\r^8sQ\rAa\u0005\f\u0015\u0004\u0001\u0019b\u0003"
)
public class PCAModel implements VectorTransformer {
   private final int k;
   private final DenseMatrix pc;
   private final DenseVector explainedVariance;

   public RDD transform(final RDD data) {
      return VectorTransformer.transform$(this, (RDD)data);
   }

   public JavaRDD transform(final JavaRDD data) {
      return VectorTransformer.transform$(this, (JavaRDD)data);
   }

   public int k() {
      return this.k;
   }

   public DenseMatrix pc() {
      return this.pc;
   }

   public DenseVector explainedVariance() {
      return this.explainedVariance;
   }

   public Vector transform(final Vector vector) {
      return this.pc().transpose().multiply(vector);
   }

   public PCAModel(final int k, final DenseMatrix pc, final DenseVector explainedVariance) {
      this.k = k;
      this.pc = pc;
      this.explainedVariance = explainedVariance;
      VectorTransformer.$init$(this);
   }
}
