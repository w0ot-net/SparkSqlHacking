package org.apache.spark.ml.clustering;

import java.io.IOException;
import org.apache.spark.ml.Transformer;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b!\u0002\b\u0010\u0001MI\u0002\u0002\u0003\u0015\u0001\u0005\u000b\u0007I\u0011\t\u0016\t\u0011a\u0002!\u0011!Q\u0001\n-BQ!\u000f\u0001\u0005\u0002iBQ!\u000f\u0001\u0005\u0002uBQA\u0010\u0001\u0005B}BQ\u0001\u001a\u0001\u0005B\u0015DQA\u001c\u0001\u0005B=DQ\u0001\u001f\u0001\u0005Be<a!`\b\t\u0002MqhA\u0002\b\u0010\u0011\u0003\u0019r\u0010\u0003\u0004:\u0015\u0011\u0005\u0011Q\u0004\u0005\b\u0003?QA\u0011IA\u0011\u0011%\t9CCA\u0001\n\u0013\tICA\u0010Q_^,'/\u0013;fe\u0006$\u0018n\u001c8DYV\u001cH/\u001a:j]\u001e<&/\u00199qKJT!\u0001E\t\u0002\u0015\rdWo\u001d;fe&twM\u0003\u0002\u0013'\u0005\u0011Q\u000e\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sON!\u0001A\u0007\u0010#!\tYB$D\u0001\u0012\u0013\ti\u0012CA\u0006Ue\u0006t7OZ8s[\u0016\u0014\bCA\u0010!\u001b\u0005y\u0011BA\u0011\u0010\u0005y\u0001vn^3s\u0013R,'/\u0019;j_:\u001cE.^:uKJLgn\u001a)be\u0006l7\u000f\u0005\u0002$M5\tAE\u0003\u0002&#\u0005!Q\u000f^5m\u0013\t9CEA\u000bEK\u001a\fW\u000f\u001c;QCJ\fWn],sSR\f'\r\\3\u0002\u0007ULGm\u0001\u0001\u0016\u0003-\u0002\"\u0001L\u001b\u000f\u00055\u001a\u0004C\u0001\u00182\u001b\u0005y#B\u0001\u0019*\u0003\u0019a$o\\8u})\t!'A\u0003tG\u0006d\u0017-\u0003\u00025c\u00051\u0001K]3eK\u001aL!AN\u001c\u0003\rM#(/\u001b8h\u0015\t!\u0014'\u0001\u0003vS\u0012\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002<yA\u0011q\u0004\u0001\u0005\u0006Q\r\u0001\ra\u000b\u000b\u0002w\u0005IAO]1og\u001a|'/\u001c\u000b\u0003\u0001F\u0003\"!\u0011(\u000f\u0005\t[eBA\"J\u001d\t!\u0005J\u0004\u0002F\u000f:\u0011aFR\u0005\u00021%\u0011acF\u0005\u0003)UI!AS\n\u0002\u0007M\fH.\u0003\u0002M\u001b\u00069\u0001/Y2lC\u001e,'B\u0001&\u0014\u0013\ty\u0005KA\u0005ECR\fgI]1nK*\u0011A*\u0014\u0005\u0006%\u0016\u0001\raU\u0001\bI\u0006$\u0018m]3ua\t!&\fE\u0002V-bk\u0011!T\u0005\u0003/6\u0013q\u0001R1uCN,G\u000f\u0005\u0002Z52\u0001A!C.R\u0003\u0003\u0005\tQ!\u0001]\u0005\ryFEM\t\u0003;\u0006\u0004\"AX0\u000e\u0003EJ!\u0001Y\u0019\u0003\u000f9{G\u000f[5oOB\u0011aLY\u0005\u0003GF\u00121!\u00118z\u0003=!(/\u00198tM>\u0014XnU2iK6\fGC\u00014m!\t9'.D\u0001i\u0015\tIW*A\u0003usB,7/\u0003\u0002lQ\nQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000b54\u0001\u0019\u00014\u0002\rM\u001c\u0007.Z7b\u0003\u0011\u0019w\u000e]=\u0015\u0005m\u0002\b\"B9\b\u0001\u0004\u0011\u0018!B3yiJ\f\u0007CA:w\u001b\u0005!(BA;\u0012\u0003\u0015\u0001\u0018M]1n\u0013\t9HO\u0001\u0005QCJ\fW.T1q\u0003\u00159(/\u001b;f+\u0005Q\bCA\u0012|\u0013\taHE\u0001\u0005N\u0019^\u0013\u0018\u000e^3s\u0003}\u0001vn^3s\u0013R,'/\u0019;j_:\u001cE.^:uKJLgnZ,sCB\u0004XM\u001d\t\u0003?)\u0019rACA\u0001\u0003\u000f\ti\u0001E\u0002_\u0003\u0007I1!!\u00022\u0005\u0019\te.\u001f*fMB!1%!\u0003<\u0013\r\tY\u0001\n\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\ty!!\u0007\u000e\u0005\u0005E!\u0002BA\n\u0003+\t!![8\u000b\u0005\u0005]\u0011\u0001\u00026bm\u0006LA!a\u0007\u0002\u0012\ta1+\u001a:jC2L'0\u00192mKR\ta0\u0001\u0003m_\u0006$GcA\u001e\u0002$!1\u0011Q\u0005\u0007A\u0002-\nA\u0001]1uQ\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u00111\u0006\t\u0005\u0003[\t\u0019$\u0004\u0002\u00020)!\u0011\u0011GA\u000b\u0003\u0011a\u0017M\\4\n\t\u0005U\u0012q\u0006\u0002\u0007\u001f\nTWm\u0019;"
)
public class PowerIterationClusteringWrapper extends Transformer implements PowerIterationClusteringParams, DefaultParamsWritable {
   private final String uid;
   private IntParam k;
   private Param initMode;
   private Param srcCol;
   private Param dstCol;
   private Param weightCol;
   private IntParam maxIter;

   public static PowerIterationClusteringWrapper load(final String path) {
      return PowerIterationClusteringWrapper$.MODULE$.load(path);
   }

   public static MLReader read() {
      return PowerIterationClusteringWrapper$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getK() {
      return PowerIterationClusteringParams.getK$(this);
   }

   public String getInitMode() {
      return PowerIterationClusteringParams.getInitMode$(this);
   }

   public String getSrcCol() {
      return PowerIterationClusteringParams.getSrcCol$(this);
   }

   public String getDstCol() {
      return PowerIterationClusteringParams.getDstCol$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final IntParam k() {
      return this.k;
   }

   public final Param initMode() {
      return this.initMode;
   }

   public Param srcCol() {
      return this.srcCol;
   }

   public Param dstCol() {
      return this.dstCol;
   }

   public final void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$initMode_$eq(final Param x$1) {
      this.initMode = x$1;
   }

   public void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$srcCol_$eq(final Param x$1) {
      this.srcCol = x$1;
   }

   public void org$apache$spark$ml$clustering$PowerIterationClusteringParams$_setter_$dstCol_$eq(final Param x$1) {
      this.dstCol = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Dataset transform(final Dataset dataset) {
      throw new UnsupportedOperationException("transform not supported");
   }

   public StructType transformSchema(final StructType schema) {
      throw new UnsupportedOperationException("transformSchema not supported");
   }

   public PowerIterationClusteringWrapper copy(final ParamMap extra) {
      return (PowerIterationClusteringWrapper)this.defaultCopy(extra);
   }

   public MLWriter write() {
      return new MLWriter() {
         // $FF: synthetic field
         private final PowerIterationClusteringWrapper $outer;

         public void saveImpl(final String path) {
            (new PowerIterationClustering(this.$outer.uid())).copy(this.$outer.paramMap()).save(path);
         }

         public {
            if (PowerIterationClusteringWrapper.this == null) {
               throw null;
            } else {
               this.$outer = PowerIterationClusteringWrapper.this;
            }
         }
      };
   }

   public PowerIterationClusteringWrapper(final String uid) {
      this.uid = uid;
      HasMaxIter.$init$(this);
      HasWeightCol.$init$(this);
      PowerIterationClusteringParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public PowerIterationClusteringWrapper() {
      this(Identifiable$.MODULE$.randomUID("PowerIterationClusteringWrapper"));
   }
}
