package org.apache.spark.ml.classification;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkException;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseMatrix;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasElasticNetParam;
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
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Tuple4;
import scala.Tuple5;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\rb\u0001\u00021b\u00011D!\"a\u0004\u0001\u0005\u000b\u0007I\u0011IA\t\u0011)\ty\u0004\u0001B\u0001B\u0003%\u00111\u0003\u0005\u000b\u0003\u0007\u0002!Q1A\u0005\u0002\u0005\u0015\u0003BCA*\u0001\t\u0005\t\u0015!\u0003\u0002H!Q\u0011q\u000b\u0001\u0003\u0006\u0004%\t!!\u0017\t\u0013\u0005u\u0003A!A!\u0002\u0013\t\bBCA1\u0001\t\u0015\r\u0011\"\u0011\u0002d!Q\u00111\u000f\u0001\u0003\u0002\u0003\u0006I!!\u001a\t\u0015\u0005]\u0004A!b\u0001\n\u0013\tI\b\u0003\u0006\u0002\u0002\u0002\u0011\t\u0011)A\u0005\u0003wB\u0001\"a!\u0001\t\u0003)\u0017Q\u0011\u0005\t\u0003\u0007\u0003A\u0011A3\u0002\u001a\"A\u00111\u0011\u0001\u0005\u0002\r\fY\u000bC\u0004\u0002 \u0002!\t!!\u0017\t\u0015\u0005M\u0006\u0001#b\u0001\n\u0013\tI\u0006C\u0004\u0002$\u0002!\t!!.\t\u0013\u0005e\u0006A1A\u0005\n\u0005m\u0006\u0002CAb\u0001\u0001\u0006I!!0\t\u0013\u0005\u0015\u0007A1A\u0005\n\u0005U\u0006\u0002CAd\u0001\u0001\u0006I!!*\t\u0013\u0005%\u0007\u00011A\u0005\n\u0005-\u0007\"CAj\u0001\u0001\u0007I\u0011BAk\u0011!\t\t\u000f\u0001Q!\n\u00055\u0007\u0002CAr\u0001\u0011\u00053-!:\t\u000f\t5\u0001\u0001\"\u0011\u0003\u0010!9!Q\u0004\u0001\u0005B\u0005U\u0006b\u0002B\u0011\u0001\u0011\u0005#1\u0005\u0005\b\u0005S\u0001A\u0011IAf\u0011%\u0011i\u0003\u0001b\u0001\n\u0013\u0011y\u0003\u0003\u0005\u00038\u0001\u0001\u000b\u0011\u0002B\u0019\u0011%\u0011I\u0004\u0001b\u0001\n\u0013\u0011Y\u0004\u0003\u0005\u0003@\u0001\u0001\u000b\u0011\u0002B\u001f\u0011%\u0011\t\u0005\u0001b\u0001\n\u0013\u0011y\u0003\u0003\u0005\u0003D\u0001\u0001\u000b\u0011\u0002B\u0019\u0011%\u0011)\u0005\u0001b\u0001\n\u0003\n\u0019\u0007\u0003\u0005\u0003N\u0001\u0001\u000b\u0011BA3\u0011\u001d\u0011\t\u0006\u0001C!\u0005'BqAa\u0016\u0001\t\u0003\u0011I\u0006C\u0004\u0003h\u0001!\tA!\u001b\t\u000f\t-\u0005\u0001\"\u0011\u0003\u000e\"9!1\u0013\u0001\u0005R\tU\u0005b\u0002BN\u0001\u0011\u0005#Q\u0014\u0005\b\u0005O\u0003A\u0011\tBU\u0011\u001d\u00119\f\u0001C)\u0005sCqA!0\u0001\t#\u0012y\fC\u0004\u0003F\u0002!\tEa2\t\u000f\tE\u0007\u0001\"\u0011\u0003T\u001e9!q[1\t\u0002\tegA\u00021b\u0011\u0003\u0011Y\u000eC\u0004\u0002\u0004F\"\tA!?\t\u000f\tm\u0018\u0007\"\u0011\u0003~\"91qA\u0019\u0005B\r%aaBB\tc\u0001\t41\u0003\u0005\n\u0007C)$\u0011!Q\u0001\n]Dq!a!6\t\u0003\u0019\u0019C\u0002\u0004\u0004,U\"5Q\u0006\u0005\u000b\u0003CB$Q3A\u0005\u0002\u0005\r\u0004BCA:q\tE\t\u0015!\u0003\u0002f!Q!Q\t\u001d\u0003\u0016\u0004%\t!a\u0019\t\u0015\t5\u0003H!E!\u0002\u0013\t)\u0007\u0003\u0006\u0002Xa\u0012)\u001a!C\u0001\u00033B\u0011\"!\u00189\u0005#\u0005\u000b\u0011B9\t\u0015\u0005\r\u0003H!f\u0001\n\u0003\t)\u0005\u0003\u0006\u0002Ta\u0012\t\u0012)A\u0005\u0003\u000fB!\"a\u001e9\u0005+\u0007I\u0011AA=\u0011)\t\t\t\u000fB\tB\u0003%\u00111\u0010\u0005\b\u0003\u0007CD\u0011AB#\u0011%\u00119\u000bOA\u0001\n\u0003\u0019)\u0006C\u0005\u0004ba\n\n\u0011\"\u0001\u0004d!I1q\u000f\u001d\u0012\u0002\u0013\u000511\r\u0005\n\u0007sB\u0014\u0013!C\u0001\u0007wB\u0011ba 9#\u0003%\ta!!\t\u0013\r\u0015\u0005(%A\u0005\u0002\r\u001d\u0005\"CBFq\u0005\u0005I\u0011IBG\u0011%\u0019I\nOA\u0001\n\u0003\t\u0019\u0007C\u0005\u0004\u001cb\n\t\u0011\"\u0001\u0004\u001e\"I1\u0011\u0015\u001d\u0002\u0002\u0013\u000531\u0015\u0005\n\u0007cC\u0014\u0011!C\u0001\u0007gC\u0011ba.9\u0003\u0003%\te!/\t\u0013\ru\u0006(!A\u0005B\r}\u0006\"\u0003Biq\u0005\u0005I\u0011IBa\u0011%\u0019\u0019\rOA\u0001\n\u0003\u001a)mB\u0005\u0004JV\n\t\u0011#\u0003\u0004L\u001aI11F\u001b\u0002\u0002#%1Q\u001a\u0005\b\u0003\u0007#F\u0011ABn\u0011%\u0011\t\u000eVA\u0001\n\u000b\u001a\t\rC\u0005\u0004^R\u000b\t\u0011\"!\u0004`\"I11\u001e+\u0002\u0002\u0013\u00055Q\u001e\u0005\b\u0007\u007f,D\u0011\u000bC\u0001\r\u0019!)!\r\u0003\u0005\b!9\u00111\u0011.\u0005\u0002\u0011%\u0001\"\u0003C\u00075\n\u0007I\u0011BBG\u0011!!yA\u0017Q\u0001\n\r=\u0005bBB\u00045\u0012\u0005C\u0011\u0003\u0005\n\t+\t\u0014\u0011!C\u0005\t/\u0011q\u0003T8hSN$\u0018n\u0019*fOJ,7o]5p]6{G-\u001a7\u000b\u0005\t\u001c\u0017AD2mCN\u001c\u0018NZ5dCRLwN\u001c\u0006\u0003I\u0016\f!!\u001c7\u000b\u0005\u0019<\u0017!B:qCJ\\'B\u00015j\u0003\u0019\t\u0007/Y2iK*\t!.A\u0002pe\u001e\u001c\u0001a\u0005\u0004\u0001[bt\u00181\u0001\t\u0005]>\fx/D\u0001b\u0013\t\u0001\u0018M\u0001\u0011Qe>\u0014\u0017MY5mSN$\u0018nY\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016d\u0007C\u0001:v\u001b\u0005\u0019(B\u0001;d\u0003\u0019a\u0017N\\1mO&\u0011ao\u001d\u0002\u0007-\u0016\u001cGo\u001c:\u0011\u00059\u0004\u0001CA=}\u001b\u0005Q(BA>d\u0003\u0011)H/\u001b7\n\u0005uT(AC'M/JLG/\u00192mKB\u0011an`\u0005\u0004\u0003\u0003\t'\u0001\u0007'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:\u0004\u0016M]1ngB)\u00110!\u0002\u0002\n%\u0019\u0011q\u0001>\u0003%!\u000b7\u000f\u0016:bS:LgnZ*v[6\f'/\u001f\t\u0004]\u0006-\u0011bAA\u0007C\n\tCj\\4jgRL7MU3he\u0016\u001c8/[8o)J\f\u0017N\\5oON+X.\\1ss\u0006\u0019Q/\u001b3\u0016\u0005\u0005M\u0001\u0003BA\u000b\u0003OqA!a\u0006\u0002$A!\u0011\u0011DA\u0010\u001b\t\tYBC\u0002\u0002\u001e-\fa\u0001\u0010:p_Rt$BAA\u0011\u0003\u0015\u00198-\u00197b\u0013\u0011\t)#a\b\u0002\rA\u0013X\rZ3g\u0013\u0011\tI#a\u000b\u0003\rM#(/\u001b8h\u0015\u0011\t)#a\b)\u000b\u0005\ty#a\u000f\u0011\t\u0005E\u0012qG\u0007\u0003\u0003gQ1!!\u000ef\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003s\t\u0019DA\u0003TS:\u001cW-\t\u0002\u0002>\u0005)\u0011G\f\u001b/a\u0005!Q/\u001b3!Q\u0015\u0011\u0011qFA\u001e\u0003E\u0019w.\u001a4gS\u000eLWM\u001c;NCR\u0014\u0018\u000e_\u000b\u0003\u0003\u000f\u00022A]A%\u0013\r\tYe\u001d\u0002\u0007\u001b\u0006$(/\u001b=)\u000b\r\ty#a\u0014\"\u0005\u0005E\u0013!\u0002\u001a/c9\u0002\u0014AE2pK\u001a4\u0017nY5f]Rl\u0015\r\u001e:jq\u0002BS\u0001BA\u0018\u0003\u001f\nq\"\u001b8uKJ\u001cW\r\u001d;WK\u000e$xN]\u000b\u0002c\"*Q!a\f\u0002P\u0005\u0001\u0012N\u001c;fe\u000e,\u0007\u000f\u001e,fGR|'\u000f\t\u0015\u0006\r\u0005=\u0012qJ\u0001\u000b]Vl7\t\\1tg\u0016\u001cXCAA3!\u0011\t9'!\u001b\u000e\u0005\u0005}\u0011\u0002BA6\u0003?\u00111!\u00138uQ\u00159\u0011qFA8C\t\t\t(A\u00032]Mr\u0003'A\u0006ok6\u001cE.Y:tKN\u0004\u0003&\u0002\u0005\u00020\u0005=\u0014!D5t\u001bVdG/\u001b8p[&\fG.\u0006\u0002\u0002|A!\u0011qMA?\u0013\u0011\ty(a\b\u0003\u000f\t{w\u000e\\3b]\u0006q\u0011n]'vYRLgn\\7jC2\u0004\u0013A\u0002\u001fj]&$h\bF\u0006x\u0003\u000f\u000bY)a$\u0002\u0014\u0006]\u0005bBA\b\u0017\u0001\u0007\u00111\u0003\u0015\u0007\u0003\u000f\u000by#a\u000f\t\u000f\u0005\r3\u00021\u0001\u0002H!2\u00111RA\u0018\u0003\u001fBa!a\u0016\f\u0001\u0004\t\bFBAH\u0003_\ty\u0005C\u0004\u0002b-\u0001\r!!\u001a)\r\u0005M\u0015qFA8\u0011\u001d\t9h\u0003a\u0001\u0003w\"ra^AN\u0003;\u000b\t\u000bC\u0004\u0002\u00101\u0001\r!a\u0005\t\r\u0005}E\u00021\u0001r\u00031\u0019w.\u001a4gS\u000eLWM\u001c;t\u0011\u001d\t\u0019\u000b\u0004a\u0001\u0003K\u000b\u0011\"\u001b8uKJ\u001cW\r\u001d;\u0011\t\u0005\u001d\u0014qU\u0005\u0005\u0003S\u000byB\u0001\u0004E_V\u0014G.\u001a\u000b\u0002o\"*a\"a\f\u00020\u0006\u0012\u0011\u0011W\u0001\u0006e9\u0002d\u0006M\u0001\u000e?\u000e|WM\u001a4jG&,g\u000e^:\u0016\u0005\u0005\u0015\u0006&\u0002\t\u00020\u0005=\u0014\u0001E0j]R,'oY3qiZ+7\r^8s+\t\ti\fE\u0002s\u0003\u007fK1!!1t\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0002#}Kg\u000e^3sG\u0016\u0004HOV3di>\u0014\b%\u0001\u0006`S:$XM]2faR\f1bX5oi\u0016\u00148-\u001a9uA\u0005\trLY5oCJLH\u000b\u001b:fg\"|G\u000eZ:\u0016\u0005\u00055\u0007CBA4\u0003\u001f\f)+\u0003\u0003\u0002R\u0006}!!B!se\u0006L\u0018!F0cS:\f'/\u001f+ie\u0016\u001c\bn\u001c7eg~#S-\u001d\u000b\u0005\u0003/\fi\u000e\u0005\u0003\u0002h\u0005e\u0017\u0002BAn\u0003?\u0011A!\u00168ji\"I\u0011q\u001c\f\u0002\u0002\u0003\u0007\u0011QZ\u0001\u0004q\u0012\n\u0014AE0cS:\f'/\u001f+ie\u0016\u001c\bn\u001c7eg\u0002\nQb\u001c8QCJ\fWn\u00115b]\u001e,G\u0003BAl\u0003ODq!!;\u0019\u0001\u0004\tY/A\u0003qCJ\fW\u000e\r\u0003\u0002n\u0006m\bCBAx\u0003g\f90\u0004\u0002\u0002r*\u0019\u0011\u0011^2\n\t\u0005U\u0018\u0011\u001f\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0005\u0003s\fY\u0010\u0004\u0001\u0005\u0019\u0005u\u0018q]A\u0001\u0002\u0003\u0015\t!a@\u0003\u0007}#3'\u0005\u0003\u0003\u0002\t\u001d\u0001\u0003BA4\u0005\u0007IAA!\u0002\u0002 \t9aj\u001c;iS:<\u0007\u0003BA4\u0005\u0013IAAa\u0003\u0002 \t\u0019\u0011I\\=\u0002\u0019M,G\u000f\u00165sKNDw\u000e\u001c3\u0015\t\tE!1C\u0007\u0002\u0001!9!QC\rA\u0002\u0005\u0015\u0016!\u0002<bYV,\u0007&B\r\u00020\te\u0011E\u0001B\u000e\u0003\u0015\td&\u000e\u00181\u000319W\r\u001e+ie\u0016\u001c\bn\u001c7eQ\u0015Q\u0012q\u0006B\r\u00035\u0019X\r\u001e+ie\u0016\u001c\bn\u001c7egR!!\u0011\u0003B\u0013\u0011\u001d\u0011)b\u0007a\u0001\u0003\u001bDSaGA\u0018\u00053\tQbZ3u)\"\u0014Xm\u001d5pY\u0012\u001c\b&\u0002\u000f\u00020\te\u0011AB7be\u001eLg.\u0006\u0002\u00032A9\u0011q\rB\u001ac\u0006\u0015\u0016\u0002\u0002B\u001b\u0003?\u0011\u0011BR;oGRLwN\\\u0019\u0002\u000f5\f'oZ5oA\u00059Q.\u0019:hS:\u001cXC\u0001B\u001f!\u0019\t9Ga\rrc\u0006AQ.\u0019:hS:\u001c\b%A\u0003tG>\u0014X-\u0001\u0004tG>\u0014X\rI\u0001\f]Vlg)Z1ukJ,7\u000fK\u0003$\u0003_\u0011I%\t\u0002\u0003L\u0005)\u0011G\f\u001c/a\u0005aa.^7GK\u0006$XO]3tA!*A%a\f\u0003J\u000591/^7nCJLXCAA\u0005Q\u0015)\u0013q\u0006B\r\u00035\u0011\u0017N\\1ssN+X.\\1ssV\u0011!1\f\t\u0004]\nu\u0013b\u0001B0C\n9#)\u001b8befdunZ5ti&\u001c'+Z4sKN\u001c\u0018n\u001c8Ue\u0006Lg.\u001b8h'VlW.\u0019:zQ\u00151\u0013q\u0006B2C\t\u0011)'A\u00033]Mr\u0003'\u0001\u0005fm\u0006dW/\u0019;f)\u0011\u0011YG!\u001d\u0011\u00079\u0014i'C\u0002\u0003p\u0005\u0014\u0011\u0004T8hSN$\u0018n\u0019*fOJ,7o]5p]N+X.\\1ss\"9!1O\u0014A\u0002\tU\u0014a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0005o\u0012)\t\u0005\u0004\u0003z\t}$1Q\u0007\u0003\u0005wR1A! f\u0003\r\u0019\u0018\u000f\\\u0005\u0005\u0005\u0003\u0013YHA\u0004ECR\f7/\u001a;\u0011\t\u0005e(Q\u0011\u0003\r\u0005\u000f\u0013\t(!A\u0001\u0002\u000b\u0005\u0011q \u0002\u0004?\u0012\"\u0004&B\u0014\u00020\u0005=\u0016a\u00029sK\u0012L7\r\u001e\u000b\u0005\u0003K\u0013y\t\u0003\u0004\u0003\u0012\"\u0002\r!]\u0001\tM\u0016\fG/\u001e:fg\u00061\"/Y<3aJ|'-\u00192jY&$\u00180\u00138QY\u0006\u001cW\rF\u0002r\u0005/CaA!'*\u0001\u0004\t\u0018!\u0004:boB\u0013X\rZ5di&|g.\u0001\u0006qe\u0016$\u0017n\u0019;SC^$2!\u001dBP\u0011\u0019\u0011\tJ\u000ba\u0001c\"*!&a\f\u0003$\u0006\u0012!QU\u0001\u0006g9\u0002d\u0006M\u0001\u0005G>\u0004\u0018\u0010F\u0002x\u0005WCqA!,,\u0001\u0004\u0011y+A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002p\nE\u0016\u0002\u0002BZ\u0003c\u0014\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0006W\u0005=\u00121H\u0001\u000fe\u0006<(\u0007\u001d:fI&\u001cG/[8o)\u0011\t)Ka/\t\r\teE\u00061\u0001r\u0003Y\u0001(o\u001c2bE&d\u0017\u000e^=3aJ,G-[2uS>tG\u0003BAS\u0005\u0003DaAa1.\u0001\u0004\t\u0018a\u00039s_\n\f'-\u001b7jif\fQa\u001e:ji\u0016,\"A!3\u0011\u0007e\u0014Y-C\u0002\u0003Nj\u0014\u0001\"\u0014'Xe&$XM\u001d\u0015\u0006]\u0005=\"\u0011J\u0001\ti>\u001cFO]5oOR\u0011\u00111\u0003\u0015\u0006\u0001\u0005=\u00121H\u0001\u0018\u0019><\u0017n\u001d;jGJ+wM]3tg&|g.T8eK2\u0004\"A\\\u0019\u0014\u000fE\u0012iNa9\u0003jB!\u0011q\rBp\u0013\u0011\u0011\t/a\b\u0003\r\u0005s\u0017PU3g!\u0011I(Q]<\n\u0007\t\u001d(P\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004BAa;\u0003v6\u0011!Q\u001e\u0006\u0005\u0005_\u0014\t0\u0001\u0002j_*\u0011!1_\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003x\n5(\u0001D*fe&\fG.\u001b>bE2,GC\u0001Bm\u0003\u0011\u0011X-\u00193\u0016\u0005\t}\b\u0003B=\u0004\u0002]L1aa\u0001{\u0005!iEJU3bI\u0016\u0014\b&B\u001a\u00020\t%\u0013\u0001\u00027pC\u0012$2a^B\u0006\u0011\u001d\u0019i\u0001\u000ea\u0001\u0003'\tA\u0001]1uQ\"*A'a\f\u0003J\tiBj\\4jgRL7MU3he\u0016\u001c8/[8o\u001b>$W\r\\,sSR,'oE\u00036\u0005\u0013\u001c)\u0002\u0005\u0003\u0004\u0018\ruQBAB\r\u0015\r\u0019Y\"Z\u0001\tS:$XM\u001d8bY&!1qDB\r\u0005\u001daunZ4j]\u001e\f\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0007K\u0019I\u0003E\u0002\u0004(Uj\u0011!\r\u0005\u0007\u0007C9\u0004\u0019A<\u0003\t\u0011\u000bG/Y\n\bq\tu7qFB\u001b!\u0011\t9g!\r\n\t\rM\u0012q\u0004\u0002\b!J|G-^2u!\u0011\u00199d!\u0011\u000f\t\re2Q\b\b\u0005\u00033\u0019Y$\u0003\u0002\u0002\"%!1qHA\u0010\u0003\u001d\u0001\u0018mY6bO\u0016LAAa>\u0004D)!1qHA\u0010)1\u00199ea\u0013\u0004N\r=3\u0011KB*!\r\u0019I\u0005O\u0007\u0002k!9\u0011\u0011M\"A\u0002\u0005\u0015\u0004b\u0002B#\u0007\u0002\u0007\u0011Q\r\u0005\u0007\u0003/\u001a\u0005\u0019A9\t\u000f\u0005\r3\t1\u0001\u0002H!9\u0011qO\"A\u0002\u0005mD\u0003DB$\u0007/\u001aIfa\u0017\u0004^\r}\u0003\"CA1\tB\u0005\t\u0019AA3\u0011%\u0011)\u0005\u0012I\u0001\u0002\u0004\t)\u0007\u0003\u0005\u0002X\u0011\u0003\n\u00111\u0001r\u0011%\t\u0019\u0005\u0012I\u0001\u0002\u0004\t9\u0005C\u0005\u0002x\u0011\u0003\n\u00111\u0001\u0002|\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAB3U\u0011\t)ga\u001a,\u0005\r%\u0004\u0003BB6\u0007gj!a!\u001c\u000b\t\r=4\u0011O\u0001\nk:\u001c\u0007.Z2lK\u0012TA!!\u000e\u0002 %!1QOB7\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM*\"a! +\u0007E\u001c9'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\r\r%\u0006BA$\u0007O\nabY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0004\n*\"\u00111PB4\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u00111q\u0012\t\u0005\u0007#\u001b9*\u0004\u0002\u0004\u0014*!1Q\u0013By\u0003\u0011a\u0017M\\4\n\t\u0005%21S\u0001\raJ|G-^2u\u0003JLG/_\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\u00119aa(\t\u0013\u0005}G*!AA\u0002\u0005\u0015\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\r\u0015\u0006CBBT\u0007[\u00139!\u0004\u0002\u0004**!11VA\u0010\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0007_\u001bIK\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA>\u0007kC\u0011\"a8O\u0003\u0003\u0005\rAa\u0002\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0007\u001f\u001bY\fC\u0005\u0002`>\u000b\t\u00111\u0001\u0002f\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002fQ\u00111qR\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005m4q\u0019\u0005\n\u0003?\u0014\u0016\u0011!a\u0001\u0005\u000f\tA\u0001R1uCB\u00191\u0011\n+\u0014\u000bQ\u001byM!;\u0011\u001f\rE7q[A3\u0003K\n\u0018qIA>\u0007\u000fj!aa5\u000b\t\rU\u0017qD\u0001\beVtG/[7f\u0013\u0011\u0019Ina5\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tW\u0007\u0006\u0002\u0004L\u0006)\u0011\r\u001d9msRa1qIBq\u0007G\u001c)oa:\u0004j\"9\u0011\u0011M,A\u0002\u0005\u0015\u0004b\u0002B#/\u0002\u0007\u0011Q\r\u0005\u0007\u0003/:\u0006\u0019A9\t\u000f\u0005\rs\u000b1\u0001\u0002H!9\u0011qO,A\u0002\u0005m\u0014aB;oCB\u0004H.\u001f\u000b\u0005\u0007_\u001cY\u0010\u0005\u0004\u0002h\rE8Q_\u0005\u0005\u0007g\fyB\u0001\u0004PaRLwN\u001c\t\u000e\u0003O\u001a90!\u001a\u0002fE\f9%a\u001f\n\t\re\u0018q\u0004\u0002\u0007)V\u0004H.Z\u001b\t\u0013\ru\b,!AA\u0002\r\u001d\u0013a\u0001=%a\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0002X\u0012\r\u0001bBB\u00073\u0002\u0007\u00111\u0003\u0002\u001e\u0019><\u0017n\u001d;jGJ+wM]3tg&|g.T8eK2\u0014V-\u00193feN\u0019!La@\u0015\u0005\u0011-\u0001cAB\u00145\u0006I1\r\\1tg:\u000bW.Z\u0001\u000bG2\f7o\u001d(b[\u0016\u0004CcA<\u0005\u0014!91Q\u00020A\u0002\u0005M\u0011\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001C\r!\u0011\u0019\t\nb\u0007\n\t\u0011u11\u0013\u0002\u0007\u001f\nTWm\u0019;)\u000bE\nyC!\u0013)\u000bA\nyC!\u0013"
)
public class LogisticRegressionModel extends ProbabilisticClassificationModel implements MLWritable, LogisticRegressionParams, HasTrainingSummary {
   private Vector _coefficients;
   private final String uid;
   private final Matrix coefficientMatrix;
   private final Vector interceptVector;
   private final int numClasses;
   private final boolean org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial;
   private final DenseVector _interceptVector;
   private final double _intercept;
   private double[] _binaryThresholds;
   private final Function1 margin;
   private final Function1 margins;
   private final Function1 score;
   private final int numFeatures;
   private Option trainingSummary;
   private Param family;
   private Param lowerBoundsOnCoefficients;
   private Param upperBoundsOnCoefficients;
   private Param lowerBoundsOnIntercepts;
   private Param upperBoundsOnIntercepts;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private DoubleParam threshold;
   private Param weightCol;
   private BooleanParam standardization;
   private DoubleParam tol;
   private BooleanParam fitIntercept;
   private IntParam maxIter;
   private DoubleParam elasticNetParam;
   private DoubleParam regParam;
   private volatile boolean bitmap$0;

   public static LogisticRegressionModel load(final String path) {
      return LogisticRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LogisticRegressionModel$.MODULE$.read();
   }

   public boolean hasSummary() {
      return HasTrainingSummary.hasSummary$(this);
   }

   public HasTrainingSummary setSummary(final Option summary) {
      return HasTrainingSummary.setSummary$(this, summary);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$classification$LogisticRegressionParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return ProbabilisticClassifierParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public String getFamily() {
      return LogisticRegressionParams.getFamily$(this);
   }

   public void checkThresholdConsistency() {
      LogisticRegressionParams.checkThresholdConsistency$(this);
   }

   public Matrix getLowerBoundsOnCoefficients() {
      return LogisticRegressionParams.getLowerBoundsOnCoefficients$(this);
   }

   public Matrix getUpperBoundsOnCoefficients() {
      return LogisticRegressionParams.getUpperBoundsOnCoefficients$(this);
   }

   public Vector getLowerBoundsOnIntercepts() {
      return LogisticRegressionParams.getLowerBoundsOnIntercepts$(this);
   }

   public Vector getUpperBoundsOnIntercepts() {
      return LogisticRegressionParams.getUpperBoundsOnIntercepts$(this);
   }

   public boolean usingBoundConstrainedOptimization() {
      return LogisticRegressionParams.usingBoundConstrainedOptimization$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return LogisticRegressionParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
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

   public final double getElasticNetParam() {
      return HasElasticNetParam.getElasticNetParam$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
   }

   public final Param family() {
      return this.family;
   }

   public Param lowerBoundsOnCoefficients() {
      return this.lowerBoundsOnCoefficients;
   }

   public Param upperBoundsOnCoefficients() {
      return this.upperBoundsOnCoefficients;
   }

   public Param lowerBoundsOnIntercepts() {
      return this.lowerBoundsOnIntercepts;
   }

   public Param upperBoundsOnIntercepts() {
      return this.upperBoundsOnIntercepts;
   }

   public final void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$family_$eq(final Param x$1) {
      this.family = x$1;
   }

   public void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$lowerBoundsOnCoefficients_$eq(final Param x$1) {
      this.lowerBoundsOnCoefficients = x$1;
   }

   public void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$upperBoundsOnCoefficients_$eq(final Param x$1) {
      this.upperBoundsOnCoefficients = x$1;
   }

   public void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$lowerBoundsOnIntercepts_$eq(final Param x$1) {
      this.lowerBoundsOnIntercepts = x$1;
   }

   public void org$apache$spark$ml$classification$LogisticRegressionParams$_setter_$upperBoundsOnIntercepts_$eq(final Param x$1) {
      this.upperBoundsOnIntercepts = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public DoubleParam threshold() {
      return this.threshold;
   }

   public void org$apache$spark$ml$param$shared$HasThreshold$_setter_$threshold_$eq(final DoubleParam x$1) {
      this.threshold = x$1;
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

   public final DoubleParam elasticNetParam() {
      return this.elasticNetParam;
   }

   public final void org$apache$spark$ml$param$shared$HasElasticNetParam$_setter_$elasticNetParam_$eq(final DoubleParam x$1) {
      this.elasticNetParam = x$1;
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

   public Matrix coefficientMatrix() {
      return this.coefficientMatrix;
   }

   public Vector interceptVector() {
      return this.interceptVector;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public boolean org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial() {
      return this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial;
   }

   public Vector coefficients() {
      if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         throw new SparkException("Multinomial models contain a matrix of coefficients, use coefficientMatrix instead.");
      } else {
         return this._coefficients();
      }
   }

   private Vector _coefficients$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            .MODULE$.require(this.coefficientMatrix().isTransposed(), () -> "LogisticRegressionModel coefficients should be row major for binomial model.");
            Matrix var3 = this.coefficientMatrix();
            Vector var10001;
            if (var3 instanceof DenseMatrix) {
               DenseMatrix var4 = (DenseMatrix)var3;
               var10001 = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(var4.values());
            } else {
               if (!(var3 instanceof SparseMatrix)) {
                  throw new MatchError(var3);
               }

               SparseMatrix var5 = (SparseMatrix)var3;
               var10001 = org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(this.coefficientMatrix().numCols(), var5.rowIndices(), var5.values());
            }

            this._coefficients = var10001;
            this.bitmap$0 = true;
         }
      } catch (Throwable var7) {
         throw var7;
      }

      return this._coefficients;
   }

   private Vector _coefficients() {
      return !this.bitmap$0 ? this._coefficients$lzycompute() : this._coefficients;
   }

   public double intercept() {
      if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         throw new SparkException("Multinomial models contain a vector of intercepts, use interceptVector instead.");
      } else {
         return this._intercept();
      }
   }

   private DenseVector _interceptVector() {
      return this._interceptVector;
   }

   private double _intercept() {
      return this._intercept;
   }

   private double[] _binaryThresholds() {
      return this._binaryThresholds;
   }

   private void _binaryThresholds_$eq(final double[] x$1) {
      this._binaryThresholds = x$1;
   }

   public void onParamChange(final Param param) {
      if (!this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         label38: {
            String var10000 = param.name();
            String var2 = "threshold";
            if (var10000 == null) {
               if (var2 == null) {
                  break label38;
               }
            } else if (var10000.equals(var2)) {
               break label38;
            }

            var10000 = param.name();
            String var3 = "thresholds";
            if (var10000 == null) {
               if (var3 != null) {
                  return;
               }
            } else if (!var10000.equals(var3)) {
               return;
            }
         }

         if (!this.isDefined(this.threshold()) && !this.isDefined(this.thresholds())) {
            this._binaryThresholds_$eq((double[])null);
         } else {
            double _threshold = this.getThreshold();
            if (_threshold == (double)0.0F) {
               this._binaryThresholds_$eq(new double[]{_threshold, Double.NEGATIVE_INFINITY});
            } else if (_threshold == (double)1.0F) {
               this._binaryThresholds_$eq(new double[]{_threshold, Double.POSITIVE_INFINITY});
            } else {
               this._binaryThresholds_$eq(new double[]{_threshold, scala.math.package..MODULE$.log(_threshold / ((double)1.0F - _threshold))});
            }
         }
      }
   }

   public LogisticRegressionModel setThreshold(final double value) {
      return (LogisticRegressionModel)LogisticRegressionParams.setThreshold$(this, value);
   }

   public double getThreshold() {
      return LogisticRegressionParams.getThreshold$(this);
   }

   public LogisticRegressionModel setThresholds(final double[] value) {
      return (LogisticRegressionModel)LogisticRegressionParams.setThresholds$(this, value);
   }

   public double[] getThresholds() {
      return LogisticRegressionParams.getThresholds$(this);
   }

   private Function1 margin() {
      return this.margin;
   }

   private Function1 margins() {
      return this.margins;
   }

   private Function1 score() {
      return this.score;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public LogisticRegressionTrainingSummary summary() {
      return (LogisticRegressionTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public BinaryLogisticRegressionTrainingSummary binarySummary() {
      LogisticRegressionTrainingSummary var2 = this.summary();
      if (var2 instanceof BinaryLogisticRegressionTrainingSummary var3) {
         return var3;
      } else {
         throw new RuntimeException("Cannot create a binary summary for a non-binary model(numClasses=" + this.numClasses() + "), use summary instead.");
      }
   }

   public LogisticRegressionSummary evaluate(final Dataset dataset) {
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var5 = this.findSummaryModel();
      if (var5 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var5._1();
         String probabilityColName = (String)var5._2();
         String predictionColName = (String)var5._3();
         Tuple3 var4 = new Tuple3(summaryModel, probabilityColName, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var4._1();
         String probabilityColName = (String)var4._2();
         String predictionColName = (String)var4._3();
         return (LogisticRegressionSummary)(this.numClasses() > 2 ? new LogisticRegressionSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), weightColName) : new BinaryLogisticRegressionSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), weightColName));
      } else {
         throw new MatchError(var5);
      }
   }

   public double predict(final Vector features) {
      if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         return super.predict(features);
      } else {
         return BoxesRunTime.unboxToDouble(this.score().apply(features)) > this._binaryThresholds()[0] ? (double)1.0F : (double)0.0F;
      }
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      if (rawPrediction instanceof DenseVector var4) {
         double[] values = var4.values();
         if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
            org.apache.spark.ml.impl.Utils..MODULE$.softmax(values);
         } else {
            values[0] = (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(-values[0]));
            values[1] = (double)1.0F - values[0];
         }

         return var4;
      } else if (rawPrediction instanceof SparseVector) {
         throw new RuntimeException("Unexpected error in LogisticRegressionModel: raw2probabilitiesInPlace encountered SparseVector");
      } else {
         throw new MatchError(rawPrediction);
      }
   }

   public Vector predictRaw(final Vector features) {
      if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         return (Vector)this.margins().apply(features);
      } else {
         double m = BoxesRunTime.unboxToDouble(this.margin().apply(features));
         return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(-m, scala.runtime.ScalaRunTime..MODULE$.wrapDoubleArray(new double[]{m}));
      }
   }

   public LogisticRegressionModel copy(final ParamMap extra) {
      LogisticRegressionModel newModel = (LogisticRegressionModel)this.copyValues(new LogisticRegressionModel(this.uid(), this.coefficientMatrix(), this.interceptVector(), this.numClasses(), this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()), extra);
      return (LogisticRegressionModel)((Model)newModel.setSummary(this.trainingSummary())).setParent(this.parent());
   }

   public double raw2prediction(final Vector rawPrediction) {
      if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         return super.raw2prediction(rawPrediction);
      } else {
         return rawPrediction.apply(1) > this._binaryThresholds()[1] ? (double)1.0F : (double)0.0F;
      }
   }

   public double probability2prediction(final Vector probability) {
      if (this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial()) {
         return super.probability2prediction(probability);
      } else {
         return probability.apply(1) > this._binaryThresholds()[0] ? (double)1.0F : (double)0.0F;
      }
   }

   public MLWriter write() {
      return new LogisticRegressionModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "LogisticRegressionModel: uid=" + var10000 + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   // $FF: synthetic method
   public static final double $anonfun$margin$1(final LogisticRegressionModel $this, final Vector features) {
      return org.apache.spark.ml.linalg.BLAS..MODULE$.dot(features, $this._coefficients()) + $this._intercept();
   }

   // $FF: synthetic method
   public static final double $anonfun$score$1(final LogisticRegressionModel $this, final Vector features) {
      double m = BoxesRunTime.unboxToDouble($this.margin().apply(features));
      return (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(-m));
   }

   public LogisticRegressionModel(final String uid, final Matrix coefficientMatrix, final Vector interceptVector, final int numClasses, final boolean isMultinomial) {
      this.uid = uid;
      this.coefficientMatrix = coefficientMatrix;
      this.interceptVector = interceptVector;
      this.numClasses = numClasses;
      this.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial = isMultinomial;
      MLWritable.$init$(this);
      HasRegParam.$init$(this);
      HasElasticNetParam.$init$(this);
      HasMaxIter.$init$(this);
      HasFitIntercept.$init$(this);
      HasTol.$init$(this);
      HasStandardization.$init$(this);
      HasWeightCol.$init$(this);
      HasThreshold.$init$(this);
      HasAggregationDepth.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      LogisticRegressionParams.$init$(this);
      HasTrainingSummary.$init$(this);
      .MODULE$.require(coefficientMatrix.numRows() == interceptVector.size(), () -> {
         int var10000 = this.coefficientMatrix().numRows();
         return "Dimension mismatch! Expected coefficientMatrix.numRows == interceptVector.size, but " + var10000 + " != " + this.interceptVector().size();
      });
      this._interceptVector = isMultinomial ? interceptVector.toDense() : null;
      this._intercept = !isMultinomial ? interceptVector.apply(0) : Double.NaN;
      this._binaryThresholds = !isMultinomial ? new double[]{(double)0.5F, (double)0.0F} : null;
      this.margin = (features) -> BoxesRunTime.boxToDouble($anonfun$margin$1(this, features));
      this.margins = (features) -> {
         DenseVector m = this._interceptVector().copy();
         org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, this.coefficientMatrix(), features, (double)1.0F, m);
         return m;
      };
      this.score = (features) -> BoxesRunTime.boxToDouble($anonfun$score$1(this, features));
      this.numFeatures = coefficientMatrix.numCols();
      Statics.releaseFence();
   }

   public LogisticRegressionModel(final String uid, final Vector coefficients, final double intercept) {
      this(uid, new DenseMatrix(1, coefficients.size(), coefficients.toArray(), true), org.apache.spark.ml.linalg.Vectors..MODULE$.dense(intercept, scala.collection.immutable.Nil..MODULE$), 2, false);
   }

   public LogisticRegressionModel() {
      this("", org.apache.spark.ml.linalg.Matrices..MODULE$.empty(), org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), -1, false);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class LogisticRegressionModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final LogisticRegressionModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.numClasses(), this.instance.numFeatures(), this.instance.interceptVector(), this.instance.coefficientMatrix(), this.instance.org$apache$spark$ml$classification$LogisticRegressionModel$$isMultinomial());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(LogisticRegressionModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.classification.LogisticRegressionModel.LogisticRegressionModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.classification.LogisticRegressionModel.LogisticRegressionModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public LogisticRegressionModelWriter(final LogisticRegressionModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final int numClasses;
         private final int numFeatures;
         private final Vector interceptVector;
         private final Matrix coefficientMatrix;
         private final boolean isMultinomial;
         // $FF: synthetic field
         public final LogisticRegressionModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int numClasses() {
            return this.numClasses;
         }

         public int numFeatures() {
            return this.numFeatures;
         }

         public Vector interceptVector() {
            return this.interceptVector;
         }

         public Matrix coefficientMatrix() {
            return this.coefficientMatrix;
         }

         public boolean isMultinomial() {
            return this.isMultinomial;
         }

         public Data copy(final int numClasses, final int numFeatures, final Vector interceptVector, final Matrix coefficientMatrix, final boolean isMultinomial) {
            return this.org$apache$spark$ml$classification$LogisticRegressionModel$LogisticRegressionModelWriter$Data$$$outer().new Data(numClasses, numFeatures, interceptVector, coefficientMatrix, isMultinomial);
         }

         public int copy$default$1() {
            return this.numClasses();
         }

         public int copy$default$2() {
            return this.numFeatures();
         }

         public Vector copy$default$3() {
            return this.interceptVector();
         }

         public Matrix copy$default$4() {
            return this.coefficientMatrix();
         }

         public boolean copy$default$5() {
            return this.isMultinomial();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 5;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToInteger(this.numClasses());
               }
               case 1 -> {
                  return BoxesRunTime.boxToInteger(this.numFeatures());
               }
               case 2 -> {
                  return this.interceptVector();
               }
               case 3 -> {
                  return this.coefficientMatrix();
               }
               case 4 -> {
                  return BoxesRunTime.boxToBoolean(this.isMultinomial());
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
                  return "numClasses";
               }
               case 1 -> {
                  return "numFeatures";
               }
               case 2 -> {
                  return "interceptVector";
               }
               case 3 -> {
                  return "coefficientMatrix";
               }
               case 4 -> {
                  return "isMultinomial";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, this.numClasses());
            var1 = Statics.mix(var1, this.numFeatures());
            var1 = Statics.mix(var1, Statics.anyHash(this.interceptVector()));
            var1 = Statics.mix(var1, Statics.anyHash(this.coefficientMatrix()));
            var1 = Statics.mix(var1, this.isMultinomial() ? 1231 : 1237);
            return Statics.finalizeHash(var1, 5);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var8;
            if (this != x$1) {
               label72: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$classification$LogisticRegressionModel$LogisticRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$classification$LogisticRegressionModel$LogisticRegressionModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.numClasses() == var4.numClasses() && this.numFeatures() == var4.numFeatures() && this.isMultinomial() == var4.isMultinomial()) {
                        label62: {
                           Vector var10000 = this.interceptVector();
                           Vector var5 = var4.interceptVector();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label62;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label62;
                           }

                           Matrix var7 = this.coefficientMatrix();
                           Matrix var6 = var4.coefficientMatrix();
                           if (var7 == null) {
                              if (var6 != null) {
                                 break label62;
                              }
                           } else if (!var7.equals(var6)) {
                              break label62;
                           }

                           if (var4.canEqual(this)) {
                              break label72;
                           }
                        }
                     }
                  }

                  var8 = false;
                  return var8;
               }
            }

            var8 = true;
            return var8;
         }

         // $FF: synthetic method
         public LogisticRegressionModelWriter org$apache$spark$ml$classification$LogisticRegressionModel$LogisticRegressionModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final int numClasses, final int numFeatures, final Vector interceptVector, final Matrix coefficientMatrix, final boolean isMultinomial) {
            this.numClasses = numClasses;
            this.numFeatures = numFeatures;
            this.interceptVector = interceptVector;
            this.coefficientMatrix = coefficientMatrix;
            this.isMultinomial = isMultinomial;
            if (LogisticRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = LogisticRegressionModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction5 implements Serializable {
         // $FF: synthetic field
         private final LogisticRegressionModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final int numClasses, final int numFeatures, final Vector interceptVector, final Matrix coefficientMatrix, final boolean isMultinomial) {
            return this.$outer.new Data(numClasses, numFeatures, interceptVector, coefficientMatrix, isMultinomial);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple5(BoxesRunTime.boxToInteger(x$0.numClasses()), BoxesRunTime.boxToInteger(x$0.numFeatures()), x$0.interceptVector(), x$0.coefficientMatrix(), BoxesRunTime.boxToBoolean(x$0.isMultinomial()))));
         }

         public Data$() {
            if (LogisticRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = LogisticRegressionModelWriter.this;
               super();
            }
         }
      }
   }

   private static class LogisticRegressionModelReader extends MLReader {
      private final String className = LogisticRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public LogisticRegressionModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         Tuple2 var7 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var7 == null) {
            throw new MatchError(var7);
         } else {
            int major = var7._1$mcI$sp();
            int minor = var7._2$mcI$sp();
            Tuple2.mcII.sp var6 = new Tuple2.mcII.sp(major, minor);
            int major = ((Tuple2)var6)._1$mcI$sp();
            int minor = ((Tuple2)var6)._2$mcI$sp();
            String dataPath = (new Path(path, "data")).toString();
            Dataset data = this.sparkSession().read().format("parquet").load(dataPath);
            LogisticRegressionModel var10000;
            if (major >= 2 && (major != 2 || minor != 0)) {
               Row var35 = (Row)data.select("numClasses", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"numFeatures", "interceptVector", "coefficientMatrix", "isMultinomial"}))).head();
               if (var35 == null) {
                  throw new MatchError(var35);
               }

               Some var36 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var35);
               if (var36.isEmpty() || var36.get() == null || ((SeqOps)var36.get()).lengthCompare(5) != 0) {
                  throw new MatchError(var35);
               }

               Object numClasses = ((SeqOps)var36.get()).apply(0);
               Object numFeatures = ((SeqOps)var36.get()).apply(1);
               Object interceptVector = ((SeqOps)var36.get()).apply(2);
               Object coefficientMatrix = ((SeqOps)var36.get()).apply(3);
               Object isMultinomial = ((SeqOps)var36.get()).apply(4);
               if (!(numClasses instanceof Integer)) {
                  throw new MatchError(var35);
               }

               int var42 = BoxesRunTime.unboxToInt(numClasses);
               if (!(numFeatures instanceof Integer)) {
                  throw new MatchError(var35);
               }

               int var43 = BoxesRunTime.unboxToInt(numFeatures);
               if (!(interceptVector instanceof Vector)) {
                  throw new MatchError(var35);
               }

               Vector var44 = (Vector)interceptVector;
               if (!(coefficientMatrix instanceof Matrix)) {
                  throw new MatchError(var35);
               }

               Matrix var45 = (Matrix)coefficientMatrix;
               if (!(isMultinomial instanceof Boolean)) {
                  throw new MatchError(var35);
               }

               boolean var46 = BoxesRunTime.unboxToBoolean(isMultinomial);
               Tuple5 var34 = new Tuple5(BoxesRunTime.boxToInteger(var42), BoxesRunTime.boxToInteger(var43), var44, var45, BoxesRunTime.boxToBoolean(var46));
               int numClasses = BoxesRunTime.unboxToInt(var34._1());
               int var48 = BoxesRunTime.unboxToInt(var34._2());
               Vector interceptVector = (Vector)var34._3();
               Matrix coefficientMatrix = (Matrix)var34._4();
               boolean isMultinomial = BoxesRunTime.unboxToBoolean(var34._5());
               var10000 = new LogisticRegressionModel(metadata.uid(), coefficientMatrix, interceptVector, numClasses, isMultinomial);
            } else {
               Row var16 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"coefficients"}))).select("numClasses", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"numFeatures", "intercept", "coefficients"}))).head();
               if (var16 == null) {
                  throw new MatchError(var16);
               }

               Some var17 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var16);
               if (var17.isEmpty() || var17.get() == null || ((SeqOps)var17.get()).lengthCompare(4) != 0) {
                  throw new MatchError(var16);
               }

               Object numClasses = ((SeqOps)var17.get()).apply(0);
               Object numFeatures = ((SeqOps)var17.get()).apply(1);
               Object intercept = ((SeqOps)var17.get()).apply(2);
               Object coefficients = ((SeqOps)var17.get()).apply(3);
               if (!(numClasses instanceof Integer)) {
                  throw new MatchError(var16);
               }

               int var22 = BoxesRunTime.unboxToInt(numClasses);
               if (!(numFeatures instanceof Integer)) {
                  throw new MatchError(var16);
               }

               int var23 = BoxesRunTime.unboxToInt(numFeatures);
               if (!(intercept instanceof Double)) {
                  throw new MatchError(var16);
               }

               double var24 = BoxesRunTime.unboxToDouble(intercept);
               if (!(coefficients instanceof Vector)) {
                  throw new MatchError(var16);
               }

               Vector var26 = (Vector)coefficients;
               Tuple4 var15 = new Tuple4(BoxesRunTime.boxToInteger(var22), BoxesRunTime.boxToInteger(var23), BoxesRunTime.boxToDouble(var24), var26);
               int numClasses = BoxesRunTime.unboxToInt(var15._1());
               int var28 = BoxesRunTime.unboxToInt(var15._2());
               double intercept = BoxesRunTime.unboxToDouble(var15._3());
               Vector coefficients = (Vector)var15._4();
               DenseMatrix coefficientMatrix = new DenseMatrix(1, coefficients.size(), coefficients.toArray(), true);
               Vector interceptVector = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(intercept, scala.collection.immutable.Nil..MODULE$);
               var10000 = new LogisticRegressionModel(metadata.uid(), coefficientMatrix, interceptVector, numClasses, false);
            }

            LogisticRegressionModel model = var10000;
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         }
      }

      public LogisticRegressionModelReader() {
      }
   }
}
