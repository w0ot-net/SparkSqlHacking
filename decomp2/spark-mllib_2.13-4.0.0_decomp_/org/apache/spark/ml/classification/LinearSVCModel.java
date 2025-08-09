package org.apache.spark.ml.classification;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasThreshold;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rua\u0001B A\u0001-C\u0001B\u001a\u0001\u0003\u0006\u0004%\te\u001a\u0005\t}\u0002\u0011\t\u0011)A\u0005Q\"Q\u0011\u0011\u0001\u0001\u0003\u0006\u0004%\t!a\u0001\t\u0013\u0005\u001d\u0001A!A!\u0002\u0013\u0001\u0006BCA\u0006\u0001\t\u0015\r\u0011\"\u0001\u0002\u000e!Q\u0011\u0011\u0004\u0001\u0003\u0002\u0003\u0006I!a\u0004\t\u0011\u0005u\u0001\u0001\"\u0001A\u0003?A\u0001\"!\b\u0001\t\u0003\u0011\u0015Q\u0006\u0005\n\u0003_\u0001!\u0019!C!\u0003cA\u0001\"a\u000f\u0001A\u0003%\u00111\u0007\u0005\n\u0003\u007f\u0001!\u0019!C!\u0003cA\u0001\"a\u0011\u0001A\u0003%\u00111\u0007\u0005\b\u0003\u000f\u0002A\u0011AA%\u0011%\t\u0019\u0006\u0001b\u0001\n\u0013\t)\u0006\u0003\u0005\u0002^\u0001\u0001\u000b\u0011BA,\u0011\u001d\ty\u0006\u0001C!\u0003CBq!!\u001b\u0001\t\u0003\tY\u0007C\u0004\u0002\u001e\u0002!\t%a(\t\u000f\u0005\u0015\u0006\u0001\"\u0011\u0002(\"9\u0011\u0011\u0017\u0001\u0005R\u0005M\u0006bBA]\u0001\u0011\u0005\u00131\u0018\u0005\b\u0003\u001f\u0004A\u0011IAi\u0011\u001d\tY\u000e\u0001C!\u0003;<q!a9A\u0011\u0003\t)O\u0002\u0004@\u0001\"\u0005\u0011q\u001d\u0005\b\u0003;IB\u0011\u0001B\u0003\u0011\u001d\u00119!\u0007C!\u0005\u0013AqAa\u0005\u001a\t\u0003\u0012)BB\u0004\u0003\u001ee\u0001\u0011Da\b\t\u0013\t5RD!A!\u0002\u00131\u0006bBA\u000f;\u0011\u0005!q\u0006\u0004\u0007\u0005oiBI!\u000f\t\u0015\u0005\u0005\u0001E!f\u0001\n\u0003\t\u0019\u0001C\u0005\u0002\b\u0001\u0012\t\u0012)A\u0005!\"Q\u00111\u0002\u0011\u0003\u0016\u0004%\t!!\u0004\t\u0015\u0005e\u0001E!E!\u0002\u0013\ty\u0001C\u0004\u0002\u001e\u0001\"\tA!\u0015\t\u0013\u0005e\u0006%!A\u0005\u0002\tm\u0003\"\u0003B1AE\u0005I\u0011\u0001B2\u0011%\u00119\bII\u0001\n\u0003\u0011I\bC\u0005\u0003~\u0001\n\t\u0011\"\u0011\u0003\u0000!I!1\u0012\u0011\u0002\u0002\u0013\u0005\u0011\u0011\u0007\u0005\n\u0005\u001b\u0003\u0013\u0011!C\u0001\u0005\u001fC\u0011B!&!\u0003\u0003%\tEa&\t\u0013\t\u0015\u0006%!A\u0005\u0002\t\u001d\u0006\"\u0003BYA\u0005\u0005I\u0011\tBZ\u0011%\u00119\fIA\u0001\n\u0003\u0012I\fC\u0005\u0002\\\u0002\n\t\u0011\"\u0011\u0003<\"I!Q\u0018\u0011\u0002\u0002\u0013\u0005#qX\u0004\n\u0005\u0007l\u0012\u0011!E\u0005\u0005\u000b4\u0011Ba\u000e\u001e\u0003\u0003EIAa2\t\u000f\u0005u1\u0007\"\u0001\u0003V\"I\u00111\\\u001a\u0002\u0002\u0013\u0015#1\u0018\u0005\n\u0005/\u001c\u0014\u0011!CA\u00053D\u0011Ba84\u0003\u0003%\tI!9\t\u000f\tMX\u0004\"\u0015\u0003v\u001a1!q`\r\u0005\u0007\u0003Aq!!\b:\t\u0003\u0019\u0019\u0001C\u0005\u0004\be\u0012\r\u0011\"\u0003\u0003\u0000!A1\u0011B\u001d!\u0002\u0013\u0011\t\tC\u0004\u0003\u0014e\"\tea\u0003\t\u0013\r=\u0011$!A\u0005\n\rE!A\u0004'j]\u0016\f'o\u0015,D\u001b>$W\r\u001c\u0006\u0003\u0003\n\u000bab\u00197bgNLg-[2bi&|gN\u0003\u0002D\t\u0006\u0011Q\u000e\u001c\u0006\u0003\u000b\u001a\u000bQa\u001d9be.T!a\u0012%\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0015aA8sO\u000e\u00011#\u0002\u0001M/j\u0003\u0007\u0003B'O!Zk\u0011\u0001Q\u0005\u0003\u001f\u0002\u00131c\u00117bgNLg-[2bi&|g.T8eK2\u0004\"!\u0015+\u000e\u0003IS!a\u0015\"\u0002\r1Lg.\u00197h\u0013\t)&K\u0001\u0004WK\u000e$xN\u001d\t\u0003\u001b\u0002\u0001\"!\u0014-\n\u0005e\u0003%a\u0004'j]\u0016\f'o\u0015,D!\u0006\u0014\u0018-\\:\u0011\u0005msV\"\u0001/\u000b\u0005u\u0013\u0015\u0001B;uS2L!a\u0018/\u0003\u00155cuK]5uC\ndW\rE\u0002\\C\u000eL!A\u0019/\u0003%!\u000b7\u000f\u0016:bS:LgnZ*v[6\f'/\u001f\t\u0003\u001b\u0012L!!\u001a!\u000311Kg.Z1s'Z\u001bEK]1j]&twmU;n[\u0006\u0014\u00180A\u0002vS\u0012,\u0012\u0001\u001b\t\u0003SJt!A\u001b9\u0011\u0005-tW\"\u00017\u000b\u00055T\u0015A\u0002\u001fs_>$hHC\u0001p\u0003\u0015\u00198-\u00197b\u0013\t\th.\u0001\u0004Qe\u0016$WMZ\u0005\u0003gR\u0014aa\u0015;sS:<'BA9oQ\r\ta\u000f \t\u0003ojl\u0011\u0001\u001f\u0006\u0003s\u0012\u000b!\"\u00198o_R\fG/[8o\u0013\tY\bPA\u0003TS:\u001cW-I\u0001~\u0003\u0015\u0011dF\r\u00181\u0003\u0011)\u0018\u000e\u001a\u0011)\u0007\t1H0\u0001\u0007d_\u00164g-[2jK:$8/F\u0001QQ\r\u0019a\u000f`\u0001\u000eG>,gMZ5dS\u0016tGo\u001d\u0011)\u0007\u00111H0A\u0005j]R,'oY3qiV\u0011\u0011q\u0002\t\u0005\u0003#\t\u0019\"D\u0001o\u0013\r\t)B\u001c\u0002\u0007\t>,(\r\\3)\u0007\u00151H0\u0001\u0006j]R,'oY3qi\u0002B3A\u0002<}\u0003\u0019a\u0014N\\5u}Q9a+!\t\u0002&\u0005%\u0002\"\u00024\b\u0001\u0004A\u0007\u0006BA\u0011mrDa!!\u0001\b\u0001\u0004\u0001\u0006\u0006BA\u0013mrDq!a\u0003\b\u0001\u0004\ty\u0001\u000b\u0003\u0002*YdH#\u0001,\u0002\u00159,Xn\u00117bgN,7/\u0006\u0002\u00024A!\u0011\u0011CA\u001b\u0013\r\t9D\u001c\u0002\u0004\u0013:$\bfA\u0005wy\u0006Ya.^7DY\u0006\u001c8/Z:!Q\rQa\u000f`\u0001\f]Vlg)Z1ukJ,7\u000fK\u0002\fmr\fAB\\;n\r\u0016\fG/\u001e:fg\u0002B3\u0001\u0004<}\u00031\u0019X\r\u001e+ie\u0016\u001c\bn\u001c7e)\u0011\tY%!\u0014\u000e\u0003\u0001Aq!a\u0014\u000e\u0001\u0004\ty!A\u0003wC2,X\rK\u0002\u000emr\fa!\\1sO&tWCAA,!\u001d\t\t\"!\u0017Q\u0003\u001fI1!a\u0017o\u0005%1UO\\2uS>t\u0017'A\u0004nCJ<\u0017N\u001c\u0011\u0002\u000fM,X.\\1ssV\t1\r\u000b\u0003\u0011m\u0006\u0015\u0014EAA4\u0003\u0015\u0019d&\r\u00181\u0003!)g/\u00197vCR,G\u0003BA7\u0003g\u00022!TA8\u0013\r\t\t\b\u0011\u0002\u0011\u0019&tW-\u0019:T-\u000e\u001bV/\\7befDq!!\u001e\u0012\u0001\u0004\t9(A\u0004eCR\f7/\u001a;1\t\u0005e\u0014\u0011\u0012\t\u0007\u0003w\n\t)!\"\u000e\u0005\u0005u$bAA@\t\u0006\u00191/\u001d7\n\t\u0005\r\u0015Q\u0010\u0002\b\t\u0006$\u0018m]3u!\u0011\t9)!#\r\u0001\u0011a\u00111RA:\u0003\u0003\u0005\tQ!\u0001\u0002\u000e\n\u0019q\fJ\u001a\u0012\t\u0005=\u0015Q\u0013\t\u0005\u0003#\t\t*C\u0002\u0002\u0014:\u0014qAT8uQ&tw\r\u0005\u0003\u0002\u0012\u0005]\u0015bAAM]\n\u0019\u0011I\\=)\tE1\u0018QM\u0001\baJ,G-[2u)\u0011\ty!!)\t\r\u0005\r&\u00031\u0001Q\u0003!1W-\u0019;ve\u0016\u001c\u0018A\u00039sK\u0012L7\r\u001e*boR\u0019\u0001+!+\t\r\u0005\r6\u00031\u0001QQ\u0011\u0019b/!,\"\u0005\u0005=\u0016!B\u001a/a9\u0002\u0014A\u0004:boJ\u0002(/\u001a3jGRLwN\u001c\u000b\u0005\u0003\u001f\t)\f\u0003\u0004\u00028R\u0001\r\u0001U\u0001\u000ee\u0006<\bK]3eS\u000e$\u0018n\u001c8\u0002\t\r|\u0007/\u001f\u000b\u0004-\u0006u\u0006bBA`+\u0001\u0007\u0011\u0011Y\u0001\u0006Kb$(/\u0019\t\u0005\u0003\u0007\fI-\u0004\u0002\u0002F*\u0019\u0011q\u0019\"\u0002\u000bA\f'/Y7\n\t\u0005-\u0017Q\u0019\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001aQC\u001e?\u0002\u000b]\u0014\u0018\u000e^3\u0016\u0005\u0005M\u0007cA.\u0002V&\u0019\u0011q\u001b/\u0003\u00115cuK]5uKJD3A\u0006<}\u0003!!xn\u0015;sS:<G#\u00015)\t]1\u0018Q\u0016\u0015\u0004\u0001Yd\u0018A\u0004'j]\u0016\f'o\u0015,D\u001b>$W\r\u001c\t\u0003\u001bf\u0019r!GAu\u0003_\f)\u0010\u0005\u0003\u0002\u0012\u0005-\u0018bAAw]\n1\u0011I\\=SK\u001a\u0004BaWAy-&\u0019\u00111\u001f/\u0003\u00155c%+Z1eC\ndW\r\u0005\u0003\u0002x\n\u0005QBAA}\u0015\u0011\tY0!@\u0002\u0005%|'BAA\u0000\u0003\u0011Q\u0017M^1\n\t\t\r\u0011\u0011 \u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003K\fAA]3bIV\u0011!1\u0002\t\u00057\n5a+C\u0002\u0003\u0010q\u0013\u0001\"\u0014'SK\u0006$WM\u001d\u0015\u00047Yd\u0018\u0001\u00027pC\u0012$2A\u0016B\f\u0011\u0019\u0011I\u0002\ba\u0001Q\u0006!\u0001/\u0019;iQ\rab\u000f \u0002\u0010\u0019&tW-\u0019:T-\u000e;&/\u001b;feN)Q$a5\u0003\"A!!1\u0005B\u0015\u001b\t\u0011)CC\u0002\u0003(\u0011\u000b\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0005\u0005W\u0011)CA\u0004M_\u001e<\u0017N\\4\u0002\u0011%t7\u000f^1oG\u0016$BA!\r\u00036A\u0019!1G\u000f\u000e\u0003eAaA!\f \u0001\u00041&\u0001\u0002#bi\u0006\u001cr\u0001IAu\u0005w\u0011\t\u0005\u0005\u0003\u0002\u0012\tu\u0012b\u0001B ]\n9\u0001K]8ek\u000e$\b\u0003\u0002B\"\u0005\u001brAA!\u0012\u0003J9\u00191Na\u0012\n\u0003=L1Aa\u0013o\u0003\u001d\u0001\u0018mY6bO\u0016LAAa\u0001\u0003P)\u0019!1\n8\u0015\r\tM#q\u000bB-!\r\u0011)\u0006I\u0007\u0002;!1\u0011\u0011A\u0013A\u0002ACq!a\u0003&\u0001\u0004\ty\u0001\u0006\u0004\u0003T\tu#q\f\u0005\t\u0003\u00031\u0003\u0013!a\u0001!\"I\u00111\u0002\u0014\u0011\u0002\u0003\u0007\u0011qB\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011)GK\u0002Q\u0005OZ#A!\u001b\u0011\t\t-$1O\u0007\u0003\u0005[RAAa\u001c\u0003r\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0003s:LAA!\u001e\u0003n\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011!1\u0010\u0016\u0005\u0003\u001f\u00119'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005\u0003\u0003BAa!\u0003\n6\u0011!Q\u0011\u0006\u0005\u0005\u000f\u000bi0\u0001\u0003mC:<\u0017bA:\u0003\u0006\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAK\u0005#C\u0011Ba%,\u0003\u0003\u0005\r!a\r\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011I\n\u0005\u0004\u0003\u001c\n\u0005\u0016QS\u0007\u0003\u0005;S1Aa(o\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0005G\u0013iJ\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003\u0002BU\u0005_\u0003B!!\u0005\u0003,&\u0019!Q\u00168\u0003\u000f\t{w\u000e\\3b]\"I!1S\u0017\u0002\u0002\u0003\u0007\u0011QS\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003\u0002\nU\u0006\"\u0003BJ]\u0005\u0005\t\u0019AA\u001a\u0003!A\u0017m\u001d5D_\u0012,GCAA\u001a)\t\u0011\t)\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005S\u0013\t\rC\u0005\u0003\u0014F\n\t\u00111\u0001\u0002\u0016\u0006!A)\u0019;b!\r\u0011)fM\n\u0006g\t%\u0017Q\u001f\t\n\u0005\u0017\u0014\t\u000eUA\b\u0005'j!A!4\u000b\u0007\t=g.A\u0004sk:$\u0018.\\3\n\t\tM'Q\u001a\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u0014DC\u0001Bc\u0003\u0015\t\u0007\u000f\u001d7z)\u0019\u0011\u0019Fa7\u0003^\"1\u0011\u0011\u0001\u001cA\u0002ACq!a\u00037\u0001\u0004\ty!A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\t\r(q\u001e\t\u0007\u0003#\u0011)O!;\n\u0007\t\u001dhN\u0001\u0004PaRLwN\u001c\t\b\u0003#\u0011Y\u000fUA\b\u0013\r\u0011iO\u001c\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\tEx'!AA\u0002\tM\u0013a\u0001=%a\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003x\nu\b\u0003BA\t\u0005sL1Aa?o\u0005\u0011)f.\u001b;\t\r\te\u0001\b1\u0001i\u0005=a\u0015N\\3beN36IU3bI\u0016\u00148cA\u001d\u0003\fQ\u00111Q\u0001\t\u0004\u0005gI\u0014!C2mCN\u001ch*Y7f\u0003)\u0019G.Y:t\u001d\u0006lW\r\t\u000b\u0004-\u000e5\u0001B\u0002B\r{\u0001\u0007\u0001.\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004\u0014A!!1QB\u000b\u0013\u0011\u00199B!\"\u0003\r=\u0013'.Z2uQ\rIb\u000f \u0015\u00041Yd\b"
)
public class LinearSVCModel extends ClassificationModel implements LinearSVCParams, MLWritable, HasTrainingSummary {
   private final String uid;
   private final Vector coefficients;
   private final double intercept;
   private final int numClasses;
   private final int numFeatures;
   private final Function1 margin;
   private Option trainingSummary;
   private DoubleParam threshold;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private Param weightCol;
   private BooleanParam standardization;
   private DoubleParam tol;
   private BooleanParam fitIntercept;
   private IntParam maxIter;
   private DoubleParam regParam;

   public static LinearSVCModel load(final String path) {
      return LinearSVCModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LinearSVCModel$.MODULE$.read();
   }

   public boolean hasSummary() {
      return HasTrainingSummary.hasSummary$(this);
   }

   public HasTrainingSummary setSummary(final Option summary) {
      return HasTrainingSummary.setSummary$(this, summary);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public double getThreshold() {
      return HasThreshold.getThreshold$(this);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final boolean getStandardization() {
      return HasStandardization.getStandardization$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
   }

   public final DoubleParam threshold() {
      return this.threshold;
   }

   public final void org$apache$spark$ml$classification$LinearSVCParams$_setter_$threshold_$eq(final DoubleParam x$1) {
      this.threshold = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasThreshold$_setter_$threshold_$eq(final DoubleParam x$1) {
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final BooleanParam standardization() {
      return this.standardization;
   }

   public final void org$apache$spark$ml$param$shared$HasStandardization$_setter_$standardization_$eq(final BooleanParam x$1) {
      this.standardization = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final IntParam maxIter() {
      return this.maxIter;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxIter$_setter_$maxIter_$eq(final IntParam x$1) {
      this.maxIter = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Vector coefficients() {
      return this.coefficients;
   }

   public double intercept() {
      return this.intercept;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public LinearSVCModel setThreshold(final double value) {
      return (LinearSVCModel)this.set(this.threshold(), BoxesRunTime.boxToDouble(value));
   }

   private Function1 margin() {
      return this.margin;
   }

   public LinearSVCTrainingSummary summary() {
      return (LinearSVCTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public LinearSVCSummary evaluate(final Dataset dataset) {
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var5 = this.findSummaryModel();
      if (var5 != null) {
         ClassificationModel summaryModel = (ClassificationModel)var5._1();
         String rawPrediction = (String)var5._2();
         String predictionColName = (String)var5._3();
         Tuple3 var4 = new Tuple3(summaryModel, rawPrediction, predictionColName);
         ClassificationModel summaryModel = (ClassificationModel)var4._1();
         String rawPrediction = (String)var4._2();
         String predictionColName = (String)var4._3();
         return new LinearSVCSummaryImpl(summaryModel.transform(dataset), rawPrediction, predictionColName, (String)this.$(this.labelCol()), weightColName);
      } else {
         throw new MatchError(var5);
      }
   }

   public double predict(final Vector features) {
      return BoxesRunTime.unboxToDouble(this.margin().apply(features)) > BoxesRunTime.unboxToDouble(this.$(this.threshold())) ? (double)1.0F : (double)0.0F;
   }

   public Vector predictRaw(final Vector features) {
      double m = BoxesRunTime.unboxToDouble(this.margin().apply(features));
      return .MODULE$.dense(-m, scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{m}));
   }

   public double raw2prediction(final Vector rawPrediction) {
      return rawPrediction.apply(1) > BoxesRunTime.unboxToDouble(this.$(this.threshold())) ? (double)1.0F : (double)0.0F;
   }

   public LinearSVCModel copy(final ParamMap extra) {
      return (LinearSVCModel)((Model)this.copyValues(new LinearSVCModel(this.uid(), this.coefficients(), this.intercept()), extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new LinearSVCWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "LinearSVCModel: uid=" + var10000 + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   // $FF: synthetic method
   public static final double $anonfun$margin$1(final LinearSVCModel $this, final Vector features) {
      return org.apache.spark.ml.linalg.BLAS..MODULE$.dot(features, $this.coefficients()) + $this.intercept();
   }

   public LinearSVCModel(final String uid, final Vector coefficients, final double intercept) {
      this.uid = uid;
      this.coefficients = coefficients;
      this.intercept = intercept;
      HasRegParam.$init$(this);
      HasMaxIter.$init$(this);
      HasFitIntercept.$init$(this);
      HasTol.$init$(this);
      HasStandardization.$init$(this);
      HasWeightCol.$init$(this);
      HasAggregationDepth.$init$(this);
      HasThreshold.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      LinearSVCParams.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      this.numClasses = 2;
      this.numFeatures = coefficients.size();
      this.margin = (features) -> BoxesRunTime.boxToDouble($anonfun$margin$1(this, features));
      Statics.releaseFence();
   }

   public LinearSVCModel() {
      this("", .MODULE$.empty(), Double.NaN);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class LinearSVCWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final LinearSVCModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.coefficients(), this.instance.intercept());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LinearSVCWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.classification.LinearSVCModel.LinearSVCWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.classification.LinearSVCModel.LinearSVCWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public LinearSVCWriter(final LinearSVCModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector coefficients;
         private final double intercept;
         // $FF: synthetic field
         public final LinearSVCWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector coefficients() {
            return this.coefficients;
         }

         public double intercept() {
            return this.intercept;
         }

         public Data copy(final Vector coefficients, final double intercept) {
            return this.org$apache$spark$ml$classification$LinearSVCModel$LinearSVCWriter$Data$$$outer().new Data(coefficients, intercept);
         }

         public Vector copy$default$1() {
            return this.coefficients();
         }

         public double copy$default$2() {
            return this.intercept();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 2;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.coefficients();
               }
               case 1 -> {
                  return BoxesRunTime.boxToDouble(this.intercept());
               }
               default -> {
                  return Statics.ioobe(x$1);
               }
            }
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "coefficients";
               }
               case 1 -> {
                  return "intercept";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.anyHash(this.coefficients()));
            var1 = Statics.mix(var1, Statics.doubleHash(this.intercept()));
            return Statics.finalizeHash(var1, 2);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label56: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$classification$LinearSVCModel$LinearSVCWriter$Data$$$outer() == this.org$apache$spark$ml$classification$LinearSVCModel$LinearSVCWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.intercept() == var4.intercept()) {
                        label46: {
                           Vector var10000 = this.coefficients();
                           Vector var5 = var4.coefficients();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label46;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label46;
                           }

                           if (var4.canEqual(this)) {
                              break label56;
                           }
                        }
                     }
                  }

                  var6 = false;
                  return var6;
               }
            }

            var6 = true;
            return var6;
         }

         // $FF: synthetic method
         public LinearSVCWriter org$apache$spark$ml$classification$LinearSVCModel$LinearSVCWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector coefficients, final double intercept) {
            this.coefficients = coefficients;
            this.intercept = intercept;
            if (LinearSVCWriter.this == null) {
               throw null;
            } else {
               this.$outer = LinearSVCWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final LinearSVCWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector coefficients, final double intercept) {
            return this.$outer.new Data(coefficients, intercept);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.coefficients(), BoxesRunTime.boxToDouble(x$0.intercept()))));
         }

         public Data$() {
            if (LinearSVCWriter.this == null) {
               throw null;
            } else {
               this.$outer = LinearSVCWriter.this;
               super();
            }
         }
      }
   }

   private static class LinearSVCReader extends MLReader {
      private final String className = LinearSVCModel.class.getName();

      private String className() {
         return this.className;
      }

      public LinearSVCModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().format("parquet").load(dataPath);
         Row var7 = (Row)data.select("coefficients", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"intercept"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(2) == 0) {
               Object coefficients = ((SeqOps)var8.get()).apply(0);
               Object intercept = ((SeqOps)var8.get()).apply(1);
               if (coefficients instanceof Vector) {
                  Vector var11 = (Vector)coefficients;
                  if (intercept instanceof Double) {
                     double var12 = BoxesRunTime.unboxToDouble(intercept);
                     Tuple2 var6 = new Tuple2(var11, BoxesRunTime.boxToDouble(var12));
                     Vector coefficients = (Vector)var6._1();
                     double intercept = var6._2$mcD$sp();
                     LinearSVCModel model = new LinearSVCModel(metadata.uid(), coefficients, intercept);
                     metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                     return model;
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public LinearSVCReader() {
      }
   }
}
