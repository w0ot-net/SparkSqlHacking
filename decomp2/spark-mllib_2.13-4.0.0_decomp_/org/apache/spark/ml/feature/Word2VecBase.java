package org.apache.spark.ml.feature;

import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005m3\u0001\"\u0004\b\u0011\u0002\u0007\u0005a\u0002\u0007\u0005\u0006o\u0001!\t!\u000f\u0005\b{\u0001\u0011\r\u0011\"\u0002?\u0011\u0015\u0011\u0005\u0001\"\u0001D\u0011\u001d9\u0005A1A\u0005\u0006yBQ\u0001\u0013\u0001\u0005\u0002\rCq!\u0013\u0001C\u0002\u0013\u0015a\bC\u0003K\u0001\u0011\u00051\tC\u0004L\u0001\t\u0007IQ\u0001 \t\u000b1\u0003A\u0011A\"\t\u000f5\u0003!\u0019!C\u0003}!)a\n\u0001C\u0001\u0007\")q\n\u0001C\t!\naqk\u001c:eeY+7MQ1tK*\u0011q\u0002E\u0001\bM\u0016\fG/\u001e:f\u0015\t\t\"#\u0001\u0002nY*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xm\u0005\u0005\u00013})3FL\u00195!\tQR$D\u0001\u001c\u0015\u0005a\u0012!B:dC2\f\u0017B\u0001\u0010\u001c\u0005\u0019\te.\u001f*fMB\u0011\u0001eI\u0007\u0002C)\u0011!\u0005E\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003I\u0005\u0012a\u0001U1sC6\u001c\bC\u0001\u0014*\u001b\u00059#B\u0001\u0015\"\u0003\u0019\u0019\b.\u0019:fI&\u0011!f\n\u0002\f\u0011\u0006\u001c\u0018J\u001c9vi\u000e{G\u000e\u0005\u0002'Y%\u0011Qf\n\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\u001c\t\u0003M=J!\u0001M\u0014\u0003\u0015!\u000b7/T1y\u0013R,'\u000f\u0005\u0002'e%\u00111g\n\u0002\f\u0011\u0006\u001c8\u000b^3q'&TX\r\u0005\u0002'k%\u0011ag\n\u0002\b\u0011\u0006\u001c8+Z3e\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u001e\u0011\u0005iY\u0014B\u0001\u001f\u001c\u0005\u0011)f.\u001b;\u0002\u0015Y,7\r^8s'&TX-F\u0001@!\t\u0001\u0003)\u0003\u0002BC\tA\u0011J\u001c;QCJ\fW.A\u0007hKR4Vm\u0019;peNK'0Z\u000b\u0002\tB\u0011!$R\u0005\u0003\rn\u00111!\u00138u\u0003)9\u0018N\u001c3poNK'0Z\u0001\u000eO\u0016$x+\u001b8e_^\u001c\u0016N_3\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u0003A9W\r\u001e(v[B\u000b'\u000f^5uS>t7/\u0001\u0005nS:\u001cu.\u001e8u\u0003-9W\r^'j]\u000e{WO\u001c;\u0002#5\f\u0007pU3oi\u0016t7-\u001a'f]\u001e$\b.\u0001\u000bhKRl\u0015\r_*f]R,gnY3MK:<G\u000f[\u0001\u001bm\u0006d\u0017\u000eZ1uK\u0006sG\r\u0016:b]N4wN]7TG\",W.\u0019\u000b\u0003#f\u0003\"AU,\u000e\u0003MS!\u0001V+\u0002\u000bQL\b/Z:\u000b\u0005Y\u0013\u0012aA:rY&\u0011\u0001l\u0015\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007\"\u0002.\r\u0001\u0004\t\u0016AB:dQ\u0016l\u0017\r"
)
public interface Word2VecBase extends HasInputCol, HasOutputCol, HasMaxIter, HasStepSize, HasSeed {
   void org$apache$spark$ml$feature$Word2VecBase$_setter_$vectorSize_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$Word2VecBase$_setter_$windowSize_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$Word2VecBase$_setter_$numPartitions_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$Word2VecBase$_setter_$minCount_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$Word2VecBase$_setter_$maxSentenceLength_$eq(final IntParam x$1);

   IntParam vectorSize();

   // $FF: synthetic method
   static int getVectorSize$(final Word2VecBase $this) {
      return $this.getVectorSize();
   }

   default int getVectorSize() {
      return BoxesRunTime.unboxToInt(this.$(this.vectorSize()));
   }

   IntParam windowSize();

   // $FF: synthetic method
   static int getWindowSize$(final Word2VecBase $this) {
      return $this.getWindowSize();
   }

   default int getWindowSize() {
      return BoxesRunTime.unboxToInt(this.$(this.windowSize()));
   }

   IntParam numPartitions();

   // $FF: synthetic method
   static int getNumPartitions$(final Word2VecBase $this) {
      return $this.getNumPartitions();
   }

   default int getNumPartitions() {
      return BoxesRunTime.unboxToInt(this.$(this.numPartitions()));
   }

   IntParam minCount();

   // $FF: synthetic method
   static int getMinCount$(final Word2VecBase $this) {
      return $this.getMinCount();
   }

   default int getMinCount() {
      return BoxesRunTime.unboxToInt(this.$(this.minCount()));
   }

   IntParam maxSentenceLength();

   // $FF: synthetic method
   static int getMaxSentenceLength$(final Word2VecBase $this) {
      return $this.getMaxSentenceLength();
   }

   default int getMaxSentenceLength() {
      return BoxesRunTime.unboxToInt(this.$(this.maxSentenceLength()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final Word2VecBase $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      List typeCandidates = new .colon.colon(new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, true), new .colon.colon(new ArrayType(org.apache.spark.sql.types.StringType..MODULE$, false), scala.collection.immutable.Nil..MODULE$));
      SchemaUtils$.MODULE$.checkColumnTypes(schema, (String)this.$(this.inputCol()), typeCandidates, SchemaUtils$.MODULE$.checkColumnTypes$default$4());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), new VectorUDT(), SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   static void $init$(final Word2VecBase $this) {
      $this.org$apache$spark$ml$feature$Word2VecBase$_setter_$vectorSize_$eq(new IntParam($this, "vectorSize", "the dimension of codes after transforming from words (> 0)", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$feature$Word2VecBase$_setter_$windowSize_$eq(new IntParam($this, "windowSize", "the window size (context words from [-window, window]) (> 0)", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$feature$Word2VecBase$_setter_$numPartitions_$eq(new IntParam($this, "numPartitions", "number of partitions for sentences of words (> 0)", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$feature$Word2VecBase$_setter_$minCount_$eq(new IntParam($this, "minCount", "the minimum number of times a token must appear to be included in the word2vec model's vocabulary (>= 0)", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$feature$Word2VecBase$_setter_$maxSentenceLength_$eq(new IntParam($this, "maxSentenceLength", "Maximum length (in words) of each sentence in the input data. Any sentence longer than this threshold will be divided into chunks up to the size (> 0)", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.vectorSize().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.windowSize().$minus$greater(BoxesRunTime.boxToInteger(5)), $this.numPartitions().$minus$greater(BoxesRunTime.boxToInteger(1)), $this.minCount().$minus$greater(BoxesRunTime.boxToInteger(5)), $this.maxSentenceLength().$minus$greater(BoxesRunTime.boxToInteger(1000)), $this.stepSize().$minus$greater(BoxesRunTime.boxToDouble(0.025)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(1))}));
   }
}
