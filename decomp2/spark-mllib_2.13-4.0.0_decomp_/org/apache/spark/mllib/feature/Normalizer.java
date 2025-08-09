package org.apache.spark.mllib.feature;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.DenseVector$;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.SparseVector$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.Option;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m2A!\u0002\u0004\u0001#!AA\u0004\u0001B\u0001B\u0003%Q\u0004C\u0003!\u0001\u0011\u0005\u0011\u0005C\u0003!\u0001\u0011\u0005Q\u0006C\u00030\u0001\u0011\u0005\u0003G\u0001\u0006O_Jl\u0017\r\\5{KJT!a\u0002\u0005\u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0011BC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sO\u000e\u00011c\u0001\u0001\u00131A\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u0004\"!\u0007\u000e\u000e\u0003\u0019I!a\u0007\u0004\u0003#Y+7\r^8s)J\fgn\u001d4pe6,'/A\u0001q!\t\u0019b$\u0003\u0002 )\t1Ai\\;cY\u0016\fa\u0001P5oSRtDC\u0001\u0012$!\tI\u0002\u0001C\u0003\u001d\u0005\u0001\u0007Q\u0004K\u0002\u0003K-\u0002\"AJ\u0015\u000e\u0003\u001dR!\u0001\u000b\u0006\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002+O\t)1+\u001b8dK\u0006\nA&A\u00032]Er\u0003\u0007F\u0001#Q\r\u0019QeK\u0001\niJ\fgn\u001d4pe6$\"!M\u001c\u0011\u0005I*T\"A\u001a\u000b\u0005QB\u0011A\u00027j]\u0006dw-\u0003\u00027g\t1a+Z2u_JDQ\u0001\u000f\u0003A\u0002E\naA^3di>\u0014\bf\u0001\u0003&W!\u001a\u0001!J\u0016"
)
public class Normalizer implements VectorTransformer {
   private final double p;

   public RDD transform(final RDD data) {
      return VectorTransformer.transform$(this, (RDD)data);
   }

   public JavaRDD transform(final JavaRDD data) {
      return VectorTransformer.transform$(this, (JavaRDD)data);
   }

   public Vector transform(final Vector vector) {
      double norm = Vectors$.MODULE$.norm(vector, this.p);
      if (norm == (double)0.0F) {
         return vector;
      } else {
         if (vector instanceof DenseVector) {
            DenseVector var6 = (DenseVector)vector;
            Option var7 = DenseVector$.MODULE$.unapply(var6);
            if (!var7.isEmpty()) {
               double[] vs = (double[])var7.get();
               double[] values = (double[])(([D)vs).clone();
               int size = values.length;

               for(int i = 0; i < size; ++i) {
                  values[i] /= norm;
               }

               return Vectors$.MODULE$.dense(values);
            }
         }

         if (vector instanceof SparseVector) {
            SparseVector var13 = (SparseVector)vector;
            Option var14 = SparseVector$.MODULE$.unapply(var13);
            if (!var14.isEmpty()) {
               int size = BoxesRunTime.unboxToInt(((Tuple3)var14.get())._1());
               int[] ids = (int[])((Tuple3)var14.get())._2();
               double[] vs = (double[])((Tuple3)var14.get())._3();
               double[] values = (double[])(([D)vs).clone();
               int nnz = values.length;

               for(int i = 0; i < nnz; ++i) {
                  values[i] /= norm;
               }

               return Vectors$.MODULE$.sparse(size, ids, values);
            }
         }

         throw new IllegalArgumentException("Do not support vector type " + vector.getClass());
      }
   }

   public Normalizer(final double p) {
      this.p = p;
      VectorTransformer.$init$(this);
      .MODULE$.require(p >= (double)1.0F);
   }

   public Normalizer() {
      this((double)2.0F);
   }
}
