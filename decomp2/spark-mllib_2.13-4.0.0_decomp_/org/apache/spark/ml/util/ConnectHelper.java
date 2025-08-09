package org.apache.spark.ml.util;

import org.apache.spark.ml.Model;
import org.apache.spark.ml.clustering.PowerIterationClustering;
import org.apache.spark.ml.feature.CountVectorizerModel;
import org.apache.spark.ml.feature.StopWordsRemover$;
import org.apache.spark.ml.feature.StringIndexerModel;
import org.apache.spark.ml.fpm.PrefixSpan;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.stat.ChiSquareTest$;
import org.apache.spark.ml.stat.Correlation$;
import org.apache.spark.ml.stat.KolmogorovSmirnovTest$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uf!\u0002\u000b\u0016\u0001ey\u0002\u0002\u0003\u0014\u0001\u0005\u000b\u0007I\u0011\t\u0015\t\u0011Y\u0002!\u0011!Q\u0001\n%BQa\u000e\u0001\u0005\u0002aBQa\u000e\u0001\u0005\u0002iBQa\u000f\u0001\u0005\u0002qBQ!\u0012\u0001\u0005\u0002\u0019CQa\u0015\u0001\u0005\u0002QCQ!\u0017\u0001\u0005\u0002iCQ!\u0019\u0001\u0005\u0002\tDQ!\u001a\u0001\u0005\u0002!BQA\u001a\u0001\u0005\u0002\u001dDq!a\u0001\u0001\t\u0003\t)\u0001C\u0004\u0002\u0012\u0001!\t!a\u0005\t\u000f\u0005-\u0002\u0001\"\u0001\u0002.!9\u0011q\n\u0001\u0005\u0002\u0005E\u0003bBA6\u0001\u0011\u0005\u0013Q\u000e\u0005\b\u0003\u007f\u0002A\u0011IAA\u0011\u001d\t)\u000b\u0001C!\u0003OCq!!/\u0001\t\u0003\nYLA\u0007D_:tWm\u0019;IK2\u0004XM\u001d\u0006\u0003-]\tA!\u001e;jY*\u0011\u0001$G\u0001\u0003[2T!AG\u000e\u0002\u000bM\u0004\u0018M]6\u000b\u0005qi\u0012AB1qC\u000eDWMC\u0001\u001f\u0003\ry'oZ\n\u0003\u0001\u0001\u00022!\t\u0012%\u001b\u00059\u0012BA\u0012\u0018\u0005\u0015iu\u000eZ3m!\t)\u0003!D\u0001\u0016\u0003\r)\u0018\u000eZ\u0002\u0001+\u0005I\u0003C\u0001\u00164\u001d\tY\u0013\u0007\u0005\u0002-_5\tQF\u0003\u0002/O\u00051AH]8pizR\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\na\u0001\u0015:fI\u00164\u0017B\u0001\u001b6\u0005\u0019\u0019FO]5oO*\u0011!gL\u0001\u0005k&$\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003IeBQAJ\u0002A\u0002%\"\u0012\u0001J\u0001\u0010Q\u0006tG\r\\3Pm\u0016\u0014xO]5uKR\u0019Q(Q\"\u0011\u0005yzT\"A\u0018\n\u0005\u0001{#a\u0002\"p_2,\u0017M\u001c\u0005\u0006\u0005\u0016\u0001\r!K\u0001\u0005a\u0006$\b\u000eC\u0003E\u000b\u0001\u0007Q(A\btQ>,H\u000eZ(wKJ<(/\u001b;f\u0003q\u0019HO]5oO&sG-\u001a=fe6{G-\u001a7Ge>lG*\u00192fYN$2aR'O!\tA5*D\u0001J\u0015\tQu#A\u0004gK\u0006$XO]3\n\u00051K%AE*ue&tw-\u00138eKb,'/T8eK2DQA\n\u0004A\u0002%BQa\u0014\u0004A\u0002A\u000ba\u0001\\1cK2\u001c\bc\u0001 RS%\u0011!k\f\u0002\u0006\u0003J\u0014\u0018-_\u0001\"gR\u0014\u0018N\\4J]\u0012,\u00070\u001a:N_\u0012,GN\u0012:p[2\u000b'-\u001a7t\u0003J\u0014\u0018-\u001f\u000b\u0004\u000fV3\u0006\"\u0002\u0014\b\u0001\u0004I\u0003\"B,\b\u0001\u0004A\u0016a\u00037bE\u0016d7/\u0011:sCf\u00042AP)Q\u0003\t\u001aw.\u001e8u-\u0016\u001cGo\u001c:ju\u0016\u0014Xj\u001c3fY\u001a\u0013x.\u001c,pG\u0006\u0014W\u000f\\1ssR\u00191LX0\u0011\u0005!c\u0016BA/J\u0005Q\u0019u.\u001e8u-\u0016\u001cGo\u001c:ju\u0016\u0014Xj\u001c3fY\")a\u0005\u0003a\u0001S!)\u0001\r\u0003a\u0001!\u0006Qao\\2bEVd\u0017M]=\u0002IM$x\u000e],pe\u0012\u001c(+Z7pm\u0016\u0014Hj\\1e\t\u00164\u0017-\u001e7u'R|\u0007oV8sIN$\"\u0001U2\t\u000b\u0011L\u0001\u0019A\u0015\u0002\u00111\fgnZ;bO\u0016\fad\u001d;pa^{'\u000fZ:SK6|g/\u001a:HKR$UMZ1vYR|%/V*\u0002\u001b\rD\u0017nU9vCJ,G+Z:u)\u0015A\u0017p_?\u0000!\tIgO\u0004\u0002kg:\u00111.\u001d\b\u0003YBt!!\\8\u000f\u00051r\u0017\"\u0001\u0010\n\u0005qi\u0012B\u0001\u000e\u001c\u0013\t\u0011\u0018$A\u0002tc2L!\u0001^;\u0002\u000fA\f7m[1hK*\u0011!/G\u0005\u0003ob\u0014\u0011\u0002R1uC\u001a\u0013\u0018-\\3\u000b\u0005Q,\b\"\u0002>\f\u0001\u0004A\u0017a\u00023bi\u0006\u001cX\r\u001e\u0005\u0006y.\u0001\r!K\u0001\fM\u0016\fG/\u001e:fg\u000e{G\u000eC\u0003\u007f\u0017\u0001\u0007\u0011&\u0001\u0005mC\n,GnQ8m\u0011\u0019\t\ta\u0003a\u0001{\u00059a\r\\1ui\u0016t\u0017aC2peJ,G.\u0019;j_:$r\u0001[A\u0004\u0003\u0013\ti\u0001C\u0003{\u0019\u0001\u0007\u0001\u000e\u0003\u0004\u0002\f1\u0001\r!K\u0001\u0007G>dW/\u001c8\t\r\u0005=A\u00021\u0001*\u0003\u0019iW\r\u001e5pI\u0006)2n\u001c7n_\u001e|'o\u001c<T[&\u0014hn\u001c<UKN$H#\u00035\u0002\u0016\u0005]\u00111DA\u0010\u0011\u0015QX\u00021\u0001i\u0011\u0019\tI\"\u0004a\u0001S\u0005I1/Y7qY\u0016\u001cu\u000e\u001c\u0005\u0007\u0003;i\u0001\u0019A\u0015\u0002\u0011\u0011L7\u000f\u001e(b[\u0016Dq!!\t\u000e\u0001\u0004\t\u0019#\u0001\u0004qCJ\fWn\u001d\t\u0005}E\u000b)\u0003E\u0002?\u0003OI1!!\u000b0\u0005\u0019!u.\u001e2mK\u00061\u0003o\\<fe&#XM]1uS>t7\t\\;ti\u0016\u0014\u0018N\\4BgNLwM\\\"mkN$XM]:\u0015\u001f!\fy#!\r\u0002<\u0005}\u00121IA$\u0003\u0017BQA\u001f\bA\u0002!Dq!a\r\u000f\u0001\u0004\t)$A\u0001l!\rq\u0014qG\u0005\u0004\u0003sy#aA%oi\"9\u0011Q\b\bA\u0002\u0005U\u0012aB7bq&#XM\u001d\u0005\u0007\u0003\u0003r\u0001\u0019A\u0015\u0002\u0011%t\u0017\u000e^'pI\u0016Da!!\u0012\u000f\u0001\u0004I\u0013AB:sG\u000e{G\u000e\u0003\u0004\u0002J9\u0001\r!K\u0001\u0007IN$8i\u001c7\t\r\u00055c\u00021\u0001*\u0003%9X-[4ii\u000e{G.\u0001\u0015qe\u00164\u0017\u000e_*qC:4\u0015N\u001c3Ge\u0016\fX/\u001a8u'\u0016\fX/\u001a8uS\u0006d\u0007+\u0019;uKJt7\u000fF\u0006i\u0003'\n)&!\u0017\u0002^\u0005\u001d\u0004\"\u0002>\u0010\u0001\u0004A\u0007bBA,\u001f\u0001\u0007\u0011QE\u0001\u000b[&t7+\u001e9q_J$\bbBA.\u001f\u0001\u0007\u0011QG\u0001\u0011[\u0006D\b+\u0019;uKJtG*\u001a8hi\"Dq!a\u0018\u0010\u0001\u0004\t\t'\u0001\nnCbdunY1m!J|'\u000e\u0012\"TSj,\u0007c\u0001 \u0002d%\u0019\u0011QM\u0018\u0003\t1{gn\u001a\u0005\u0007\u0003Sz\u0001\u0019A\u0015\u0002\u0017M,\u0017/^3oG\u0016\u001cu\u000e\\\u0001\u0005G>\u0004\u0018\u0010F\u0002%\u0003_Bq!!\u001d\u0011\u0001\u0004\t\u0019(A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002v\u0005mTBAA<\u0015\r\tIhF\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003{\n9H\u0001\u0005QCJ\fW.T1q\u0003%!(/\u00198tM>\u0014X\u000eF\u0002i\u0003\u0007CaA_\tA\u0002\u0005\u0015\u0005\u0007BAD\u0003'\u0003b!!#\u0002\f\u0006=U\"A;\n\u0007\u00055UOA\u0004ECR\f7/\u001a;\u0011\t\u0005E\u00151\u0013\u0007\u0001\t1\t)*a!\u0002\u0002\u0003\u0005)\u0011AAL\u0005\ryF%M\t\u0005\u00033\u000by\nE\u0002?\u00037K1!!(0\u0005\u001dqu\u000e\u001e5j]\u001e\u00042APAQ\u0013\r\t\u0019k\f\u0002\u0004\u0003:L\u0018a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005%\u0016Q\u0017\t\u0005\u0003W\u000b\t,\u0004\u0002\u0002.*\u0019\u0011qV;\u0002\u000bQL\b/Z:\n\t\u0005M\u0016Q\u0016\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA\\%\u0001\u0007\u0011\u0011V\u0001\u0007g\u000eDW-\\1\u0002\u0013!\f7\u000fU1sK:$X#A\u001f"
)
public class ConnectHelper extends Model {
   private final String uid;

   public String uid() {
      return this.uid;
   }

   public boolean handleOverwrite(final String path, final boolean shouldOverwrite) {
      SparkSession spark = .MODULE$.builder().getOrCreate();
      (new FileSystemOverwrite()).handleOverwrite(path, shouldOverwrite, spark);
      return true;
   }

   public StringIndexerModel stringIndexerModelFromLabels(final String uid, final String[] labels) {
      return new StringIndexerModel(uid, labels);
   }

   public StringIndexerModel stringIndexerModelFromLabelsArray(final String uid, final String[][] labelsArray) {
      return new StringIndexerModel(uid, labelsArray);
   }

   public CountVectorizerModel countVectorizerModelFromVocabulary(final String uid, final String[] vocabulary) {
      return new CountVectorizerModel(uid, vocabulary);
   }

   public String[] stopWordsRemoverLoadDefaultStopWords(final String language) {
      return StopWordsRemover$.MODULE$.loadDefaultStopWords(language);
   }

   public String stopWordsRemoverGetDefaultOrUS() {
      return StopWordsRemover$.MODULE$.getDefaultOrUS().toString();
   }

   public Dataset chiSquareTest(final Dataset dataset, final String featuresCol, final String labelCol, final boolean flatten) {
      return ChiSquareTest$.MODULE$.test(dataset, featuresCol, labelCol, flatten);
   }

   public Dataset correlation(final Dataset dataset, final String column, final String method) {
      return Correlation$.MODULE$.corr(dataset, column, method);
   }

   public Dataset kolmogorovSmirnovTest(final Dataset dataset, final String sampleCol, final String distName, final double[] params) {
      return KolmogorovSmirnovTest$.MODULE$.test(dataset, sampleCol, distName, (Seq)scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.doubleArrayOps(params)));
   }

   public Dataset powerIterationClusteringAssignClusters(final Dataset dataset, final int k, final int maxIter, final String initMode, final String srcCol, final String dstCol, final String weightCol) {
      PowerIterationClustering pic = (new PowerIterationClustering()).setK(k).setMaxIter(maxIter).setInitMode(initMode).setSrcCol(srcCol).setDstCol(dstCol);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(weightCol))) {
         pic.setWeightCol(weightCol);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return pic.assignClusters(dataset);
   }

   public Dataset prefixSpanFindFrequentSequentialPatterns(final Dataset dataset, final double minSupport, final int maxPatternLength, final long maxLocalProjDBSize, final String sequenceCol) {
      PrefixSpan prefixSpan = (new PrefixSpan()).setMinSupport(minSupport).setMaxPatternLength(maxPatternLength).setMaxLocalProjDBSize(maxLocalProjDBSize).setSequenceCol(sequenceCol);
      return prefixSpan.findFrequentSequentialPatterns(dataset);
   }

   public ConnectHelper copy(final ParamMap extra) {
      return (ConnectHelper)this.defaultCopy(extra);
   }

   public Dataset transform(final Dataset dataset) {
      return dataset.toDF();
   }

   public StructType transformSchema(final StructType schema) {
      return schema;
   }

   public boolean hasParent() {
      return false;
   }

   public ConnectHelper(final String uid) {
      this.uid = uid;
   }

   public ConnectHelper() {
      this(Identifiable$.MODULE$.randomUID("ConnectHelper"));
   }
}
