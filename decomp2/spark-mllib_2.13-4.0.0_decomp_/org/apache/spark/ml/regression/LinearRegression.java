package org.apache.spark.ml.regression;

import breeze.linalg.DenseVector;
import breeze.optimize.CachedDiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.LBFGS;
import breeze.optimize.LBFGSB;
import breeze.optimize.OWLQN;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.feature.InstanceBlock$;
import org.apache.spark.ml.feature.StandardScalerModel$;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.optim.WeightedLeastSquares;
import org.apache.spark.ml.optim.WeightedLeastSquares$;
import org.apache.spark.ml.optim.WeightedLeastSquaresModel;
import org.apache.spark.ml.optim.aggregator.HuberBlockAggregator;
import org.apache.spark.ml.optim.aggregator.LeastSquaresBlockAggregator;
import org.apache.spark.ml.optim.loss.L2Regularization;
import org.apache.spark.ml.optim.loss.RDDLossFunction;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasElasticNetParam;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasLoss;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.Summarizer$;
import org.apache.spark.ml.stat.SummarizerBuffer;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.ml.util.Instrumentation$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.OptionalInstrumentation$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r5a\u0001B\u0017/\u0001eB\u0001b\u0016\u0001\u0003\u0006\u0004%\t\u0005\u0017\u0005\t_\u0002\u0011\t\u0011)A\u00053\")\u0011\u000f\u0001C\u0001e\")\u0011\u000f\u0001C\u0001m\")!\u0010\u0001C\u0001w\"9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\u000e\u0001\u0011\u0005\u0011Q\u0004\u0005\b\u0003G\u0001A\u0011AA\u0013\u0011\u001d\tY\u0003\u0001C\u0001\u0003[Aq!!\u000f\u0001\t\u0003\tY\u0004C\u0004\u0002B\u0001!\t!a\u0011\t\u000f\u00055\u0003\u0001\"\u0001\u0002P!9\u0011Q\u000b\u0001\u0005\u0002\u0005]\u0003bBA1\u0001\u0011\u0005\u00111\r\u0005\b\u0003[\u0002A\u0011AA8\u0011\u001d\t)\b\u0001C\u0001\u0003oBq!!!\u0001\t#\n\u0019\tC\u0004\u0002.\u0002!I!a,\t\u000f\u0005\r\b\u0001\"\u0003\u0002f\"9\u0011Q \u0001\u0005\n\u0005}\bb\u0002B\u001b\u0001\u0011%!q\u0007\u0005\b\u0005g\u0002A\u0011\u0002B;\u0011\u001d\u0011\u0019\t\u0001C!\u0005\u000b;qAa'/\u0011\u0003\u0011iJ\u0002\u0004.]!\u0005!q\u0014\u0005\u0007cf!\tA!0\t\u000f\t}\u0016\u0004\"\u0011\u0003B\"I!\u0011Z\rC\u0002\u0013\u0005!1\u001a\u0005\t\u0005\u001fL\u0002\u0015!\u0003\u00022!Q!1[\rC\u0002\u0013\u0005aF!6\t\u0011\t\u0005\u0018\u0004)A\u0005\u0005/D!Ba9\u001a\u0005\u0004%\tA\fBk\u0011!\u0011)/\u0007Q\u0001\n\t]\u0007B\u0003Bt3\t\u0007I\u0011\u0001\u0018\u0003V\"A!\u0011^\r!\u0002\u0013\u00119\u000e\u0003\u0006\u0003lf\u0011\r\u0011\"\u0001/\u0005[D\u0001B!=\u001aA\u0003%!q\u001e\u0005\u000b\u0005gL\"\u0019!C\u0001]\tU\u0007\u0002\u0003B{3\u0001\u0006IAa6\t\u0015\t]\u0018D1A\u0005\u00029\u0012)\u000e\u0003\u0005\u0003zf\u0001\u000b\u0011\u0002Bl\u0011)\u0011Y0\u0007b\u0001\n\u0003q#Q\u001e\u0005\t\u0005{L\u0002\u0015!\u0003\u0003p\"I!q`\r\u0002\u0002\u0013%1\u0011\u0001\u0002\u0011\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:T!a\f\u0019\u0002\u0015I,wM]3tg&|gN\u0003\u00022e\u0005\u0011Q\u000e\u001c\u0006\u0003gQ\nQa\u001d9be.T!!\u000e\u001c\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0014aA8sO\u000e\u00011#\u0002\u0001;\u0011.\u000b\u0006#B\u001e=}\u0011+U\"\u0001\u0018\n\u0005ur#!\u0003*fOJ,7o]8s!\ty$)D\u0001A\u0015\t\t\u0005'\u0001\u0004mS:\fGnZ\u0005\u0003\u0007\u0002\u0013aAV3di>\u0014\bCA\u001e\u0001!\tYd)\u0003\u0002H]\t)B*\u001b8fCJ\u0014Vm\u001a:fgNLwN\\'pI\u0016d\u0007CA\u001eJ\u0013\tQeF\u0001\fMS:,\u0017M\u001d*fOJ,7o]5p]B\u000b'/Y7t!\tau*D\u0001N\u0015\tq\u0005'\u0001\u0003vi&d\u0017B\u0001)N\u0005U!UMZ1vYR\u0004\u0016M]1ng^\u0013\u0018\u000e^1cY\u0016\u0004\"AU+\u000e\u0003MS!\u0001\u0016\u001a\u0002\u0011%tG/\u001a:oC2L!AV*\u0003\u000f1{wmZ5oO\u0006\u0019Q/\u001b3\u0016\u0003e\u0003\"AW2\u000f\u0005m\u000b\u0007C\u0001/`\u001b\u0005i&B\u000109\u0003\u0019a$o\\8u})\t\u0001-A\u0003tG\u0006d\u0017-\u0003\u0002c?\u00061\u0001K]3eK\u001aL!\u0001Z3\u0003\rM#(/\u001b8h\u0015\t\u0011w\fK\u0002\u0002O6\u0004\"\u0001[6\u000e\u0003%T!A\u001b\u001a\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002mS\n)1+\u001b8dK\u0006\na.A\u00032]Mr\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002h[\u00061A(\u001b8jiz\"\"\u0001R:\t\u000b]\u001b\u0001\u0019A-)\u0007M<W\u000eK\u0002\u0004O6$\u0012\u0001\u0012\u0015\u0004\t\u001dD\u0018%A=\u0002\u000bErCG\f\u0019\u0002\u0017M,GOU3h!\u0006\u0014\u0018-\u001c\u000b\u0003yvl\u0011\u0001\u0001\u0005\u0006}\u0016\u0001\ra`\u0001\u0006m\u0006dW/\u001a\t\u0005\u0003\u0003\t\u0019!D\u0001`\u0013\r\t)a\u0018\u0002\u0007\t>,(\r\\3)\u0007\u00159W.A\btKR4\u0015\u000e^%oi\u0016\u00148-\u001a9u)\ra\u0018Q\u0002\u0005\u0007}\u001a\u0001\r!a\u0004\u0011\t\u0005\u0005\u0011\u0011C\u0005\u0004\u0003'y&a\u0002\"p_2,\u0017M\u001c\u0015\u0005\r\u001d\f9\"\t\u0002\u0002\u001a\u0005)\u0011GL\u001b/a\u0005\u00112/\u001a;Ti\u0006tG-\u0019:eSj\fG/[8o)\ra\u0018q\u0004\u0005\u0007}\u001e\u0001\r!a\u0004)\t\u001d9\u0017qC\u0001\u0013g\u0016$X\t\\1ti&\u001cg*\u001a;QCJ\fW\u000eF\u0002}\u0003OAQA \u0005A\u0002}D3\u0001C4y\u0003)\u0019X\r^'bq&#XM\u001d\u000b\u0004y\u0006=\u0002B\u0002@\n\u0001\u0004\t\t\u0004\u0005\u0003\u0002\u0002\u0005M\u0012bAA\u001b?\n\u0019\u0011J\u001c;)\u0007%9W.\u0001\u0004tKR$v\u000e\u001c\u000b\u0004y\u0006u\u0002\"\u0002@\u000b\u0001\u0004y\bf\u0001\u0006hq\u0006a1/\u001a;XK&<\u0007\u000e^\"pYR\u0019A0!\u0012\t\u000by\\\u0001\u0019A-)\t-9\u0017\u0011J\u0011\u0003\u0003\u0017\nQ!\r\u00187]A\n\u0011b]3u'>dg/\u001a:\u0015\u0007q\f\t\u0006C\u0003\u007f\u0019\u0001\u0007\u0011\f\u000b\u0003\rO\u0006%\u0013aE:fi\u0006;wM]3hCRLwN\u001c#faRDGc\u0001?\u0002Z!1a0\u0004a\u0001\u0003cAC!D4\u0002^\u0005\u0012\u0011qL\u0001\u0006e9\nd\u0006M\u0001\bg\u0016$Hj\\:t)\ra\u0018Q\r\u0005\u0006}:\u0001\r!\u0017\u0015\u0005\u001d\u001d\fI'\t\u0002\u0002l\u0005)!GL\u001a/a\u0005Q1/\u001a;FaNLGn\u001c8\u0015\u0007q\f\t\bC\u0003\u007f\u001f\u0001\u0007q\u0010\u000b\u0003\u0010O\u0006%\u0014aE:fi6\u000b\u0007P\u00117pG.\u001c\u0016N_3J]6\u0013Ec\u0001?\u0002z!)a\u0010\u0005a\u0001\u007f\"\"\u0001cZA?C\t\ty(A\u00034]Er\u0003'A\u0003ue\u0006Lg\u000eF\u0002F\u0003\u000bCq!a\"\u0012\u0001\u0004\tI)A\u0004eCR\f7/\u001a;1\t\u0005-\u00151\u0014\t\u0007\u0003\u001b\u000b\u0019*a&\u000e\u0005\u0005=%bAAIe\u0005\u00191/\u001d7\n\t\u0005U\u0015q\u0012\u0002\b\t\u0006$\u0018m]3u!\u0011\tI*a'\r\u0001\u0011a\u0011QTAC\u0003\u0003\u0005\tQ!\u0001\u0002 \n\u0019q\fJ\u0019\u0012\t\u0005\u0005\u0016q\u0015\t\u0005\u0003\u0003\t\u0019+C\u0002\u0002&~\u0013qAT8uQ&tw\r\u0005\u0003\u0002\u0002\u0005%\u0016bAAV?\n\u0019\u0011I\\=\u0002\u001fQ\u0014\u0018-\u001b8XSRDgj\u001c:nC2$r!RAY\u0003{\u000bI\u000eC\u0004\u0002\bJ\u0001\r!a-1\t\u0005U\u0016\u0011\u0018\t\u0007\u0003\u001b\u000b\u0019*a.\u0011\t\u0005e\u0015\u0011\u0018\u0003\r\u0003w\u000b\t,!A\u0001\u0002\u000b\u0005\u0011q\u0014\u0002\u0004?\u0012\u0012\u0004bBA`%\u0001\u0007\u0011\u0011Y\u0001\nS:\u001cH/\u00198dKN\u0004b!a1\u0002J\u00065WBAAc\u0015\r\t9MM\u0001\u0004e\u0012$\u0017\u0002BAf\u0003\u000b\u00141A\u0015#E!\u0011\ty-!6\u000e\u0005\u0005E'bAAja\u00059a-Z1ukJ,\u0017\u0002BAl\u0003#\u0014\u0001\"\u00138ti\u0006t7-\u001a\u0005\b\u00037\u0014\u0002\u0019AAo\u0003\u0015Ign\u001d;s!\ra\u0015q\\\u0005\u0004\u0003Cl%aD%ogR\u0014X/\\3oi\u0006$\u0018n\u001c8\u0002-Q\u0014\u0018-\u001b8XSRD7i\u001c8ti\u0006tG\u000fT1cK2$\u0012\"RAt\u0003g\f)0!?\t\u000f\u0005\u001d5\u00031\u0001\u0002jB\"\u00111^Ax!\u0019\ti)a%\u0002nB!\u0011\u0011TAx\t1\t\t0a:\u0002\u0002\u0003\u0005)\u0011AAP\u0005\ryFe\r\u0005\b\u00037\u001c\u0002\u0019AAo\u0011\u001d\t9p\u0005a\u0001\u0003c\t1B\\;n\r\u0016\fG/\u001e:fg\"1\u00111`\nA\u0002}\fQ!_'fC:\fqb\u0019:fCR,w\n\u001d;j[&TXM\u001d\u000b\u000b\u0005\u0003\u0011\tC!\n\u0003*\t-\u0002\u0003\u0003B\u0002\u0005\u001b\u0011\tBa\u0007\u000e\u0005\t\u0015!\u0002\u0002B\u0004\u0005\u0013\t\u0001b\u001c9uS6L'0\u001a\u0006\u0003\u0005\u0017\taA\u0019:fKj,\u0017\u0002\u0002B\b\u0005\u000b\u00111CR5sgR|%\u000fZ3s\u001b&t\u0017.\\5{KJ\u0004RAa\u0005\u0003\u0018}l!A!\u0006\u000b\u0007\u0005\u0013I!\u0003\u0003\u0003\u001a\tU!a\u0003#f]N,g+Z2u_J\u0004bAa\u0001\u0003\u001e\tE\u0011\u0002\u0002B\u0010\u0005\u000b\u0011A\u0002R5gM\u001a+hn\u0019;j_:DaAa\t\u0015\u0001\u0004y\u0018!E3gM\u0016\u001cG/\u001b<f%\u0016<\u0007+\u0019:b[\"1!q\u0005\u000bA\u0002}\f1#\u001a4gK\u000e$\u0018N^3McI+w\rU1sC6Dq!a>\u0015\u0001\u0004\t\t\u0004C\u0004\u0003.Q\u0001\rAa\f\u0002\u0017\u0019,\u0017\r^;sKN\u001cF\u000f\u001a\t\u0006\u0003\u0003\u0011\td`\u0005\u0004\u0005gy&!B!se\u0006L\u0018!\u0003;sC&t\u0017*\u001c9m)Q\u0011IDa\u0010\u0003B\t\u0015#q\tB&\u0005\u001f\u0012\tF!\u0016\u0003pAA\u0011\u0011\u0001B\u001e\u0005_\u0011y#C\u0002\u0003>}\u0013a\u0001V;qY\u0016\u0014\u0004bBA`+\u0001\u0007\u0011\u0011\u0019\u0005\u0007\u0005\u0007*\u0002\u0019A@\u0002'\u0005\u001cG/^1m\u00052|7m[*ju\u0016Le.\u0014\"\t\r\u0005mX\u00031\u0001\u0000\u0011\u0019\u0011I%\u0006a\u0001\u007f\u0006!\u0011p\u0015;e\u0011\u001d\u0011i%\u0006a\u0001\u0005_\tABZ3biV\u0014Xm]'fC:DqA!\f\u0016\u0001\u0004\u0011y\u0003C\u0004\u0003TU\u0001\rAa\f\u0002\u001f%t\u0017\u000e^5bYN{G.\u001e;j_:DqAa\u0016\u0016\u0001\u0004\u0011I&\u0001\bsK\u001e,H.\u0019:ju\u0006$\u0018n\u001c8\u0011\r\u0005\u0005!1\fB0\u0013\r\u0011if\u0018\u0002\u0007\u001fB$\u0018n\u001c8\u0011\t\t\u0005$1N\u0007\u0003\u0005GRAA!\u001a\u0003h\u0005!An\\:t\u0015\r\u0011I\u0007M\u0001\u0006_B$\u0018.\\\u0005\u0005\u0005[\u0012\u0019G\u0001\tMeI+w-\u001e7be&T\u0018\r^5p]\"9!\u0011O\u000bA\u0002\t\u0005\u0011!C8qi&l\u0017N_3s\u0003-\u0019'/Z1uK6{G-\u001a7\u0015\u0017\u0015\u00139Ha\u001f\u0003~\t}$\u0011\u0011\u0005\b\u0005s2\u0002\u0019\u0001B\u0018\u0003!\u0019x\u000e\\;uS>t\u0007BBA~-\u0001\u0007q\u0010\u0003\u0004\u0003JY\u0001\ra \u0005\b\u0005\u001b2\u0002\u0019\u0001B\u0018\u0011\u001d\u0011iC\u0006a\u0001\u0005_\tAaY8qsR\u0019AIa\"\t\u000f\t%u\u00031\u0001\u0003\f\u0006)Q\r\u001f;sCB!!Q\u0012BJ\u001b\t\u0011yIC\u0002\u0003\u0012B\nQ\u0001]1sC6LAA!&\u0003\u0010\nA\u0001+\u0019:b[6\u000b\u0007\u000fK\u0002\u0018ObD3\u0001A4n\u0003Aa\u0015N\\3beJ+wM]3tg&|g\u000e\u0005\u0002<3M9\u0011D!)\u0003(\n5\u0006\u0003BA\u0001\u0005GK1A!*`\u0005\u0019\te.\u001f*fMB!AJ!+E\u0013\r\u0011Y+\u0014\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:SK\u0006$\u0017M\u00197f!\u0011\u0011yK!/\u000e\u0005\tE&\u0002\u0002BZ\u0005k\u000b!![8\u000b\u0005\t]\u0016\u0001\u00026bm\u0006LAAa/\u00032\na1+\u001a:jC2L'0\u00192mKR\u0011!QT\u0001\u0005Y>\fG\rF\u0002E\u0005\u0007DaA!2\u001c\u0001\u0004I\u0016\u0001\u00029bi\"DCaG4\u0002J\u0005qR*\u0011-`\r\u0016\u000bE+\u0016*F'~3uJU0O\u001fJk\u0015\tT0T\u001f23VIU\u000b\u0003\u0003cAC\u0001H4\u0002^\u0005yR*\u0011-`\r\u0016\u000bE+\u0016*F'~3uJU0O\u001fJk\u0015\tT0T\u001f23VI\u0015\u0011)\tu9\u0017QL\u0001\u0005\u0003V$x.\u0006\u0002\u0003XB!!\u0011\u001cBp\u001b\t\u0011YN\u0003\u0003\u0003^\nU\u0016\u0001\u00027b]\u001eL1\u0001\u001aBn\u0003\u0015\tU\u000f^8!\u0003\u0019quN]7bY\u00069aj\u001c:nC2\u0004\u0013!\u0002'C\r\u001e\u001b\u0016A\u0002'C\r\u001e\u001b\u0006%\u0001\ttkB\u0004xN\u001d;fIN{GN^3sgV\u0011!q\u001e\t\u0007\u0003\u0003\u0011\tDa6\u0002#M,\b\u000f]8si\u0016$7k\u001c7wKJ\u001c\b%\u0001\u0007TcV\f'/\u001a3FeJ|'/A\u0007TcV\f'/\u001a3FeJ|'\u000fI\u0001\u0006\u0011V\u0014WM]\u0001\u0007\u0011V\u0014WM\u001d\u0011\u0002\u001fM,\b\u000f]8si\u0016$Gj\\:tKN\f\u0001c];qa>\u0014H/\u001a3M_N\u001cXm\u001d\u0011\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r\r\u0001\u0003\u0002Bm\u0007\u000bIAaa\u0002\u0003\\\n1qJ\u00196fGRDC!G4\u0002J!\"\u0001dZA%\u0001"
)
public class LinearRegression extends Regressor implements LinearRegressionParams, DefaultParamsWritable {
   private final String uid;
   private Param solver;
   private Param loss;
   private DoubleParam epsilon;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private Param weightCol;
   private BooleanParam standardization;
   private BooleanParam fitIntercept;
   private DoubleParam tol;
   private IntParam maxIter;
   private DoubleParam elasticNetParam;
   private DoubleParam regParam;

   public static int MAX_FEATURES_FOR_NORMAL_SOLVER() {
      return LinearRegression$.MODULE$.MAX_FEATURES_FOR_NORMAL_SOLVER();
   }

   public static LinearRegression load(final String path) {
      return LinearRegression$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LinearRegression$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   // $FF: synthetic method
   public StructType org$apache$spark$ml$regression$LinearRegressionParams$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public double getEpsilon() {
      return LinearRegressionParams.getEpsilon$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return LinearRegressionParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public final String getLoss() {
      return HasLoss.getLoss$(this);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final boolean getStandardization() {
      return HasStandardization.getStandardization$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
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

   public final Param solver() {
      return this.solver;
   }

   public final Param loss() {
      return this.loss;
   }

   public final DoubleParam epsilon() {
      return this.epsilon;
   }

   public final void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$loss_$eq(final Param x$1) {
      this.loss = x$1;
   }

   public final void org$apache$spark$ml$regression$LinearRegressionParams$_setter_$epsilon_$eq(final DoubleParam x$1) {
      this.epsilon = x$1;
   }

   public final DoubleParam maxBlockSizeInMB() {
      return this.maxBlockSizeInMB;
   }

   public final void org$apache$spark$ml$param$shared$HasMaxBlockSizeInMB$_setter_$maxBlockSizeInMB_$eq(final DoubleParam x$1) {
      this.maxBlockSizeInMB = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasLoss$_setter_$loss_$eq(final Param x$1) {
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
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

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
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

   public LinearRegression setRegParam(final double value) {
      return (LinearRegression)this.set(this.regParam(), BoxesRunTime.boxToDouble(value));
   }

   public LinearRegression setFitIntercept(final boolean value) {
      return (LinearRegression)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public LinearRegression setStandardization(final boolean value) {
      return (LinearRegression)this.set(this.standardization(), BoxesRunTime.boxToBoolean(value));
   }

   public LinearRegression setElasticNetParam(final double value) {
      return (LinearRegression)this.set(this.elasticNetParam(), BoxesRunTime.boxToDouble(value));
   }

   public LinearRegression setMaxIter(final int value) {
      return (LinearRegression)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public LinearRegression setTol(final double value) {
      return (LinearRegression)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public LinearRegression setWeightCol(final String value) {
      return (LinearRegression)this.set(this.weightCol(), value);
   }

   public LinearRegression setSolver(final String value) {
      return (LinearRegression)this.set(this.solver(), value);
   }

   public LinearRegression setAggregationDepth(final int value) {
      return (LinearRegression)this.set(this.aggregationDepth(), BoxesRunTime.boxToInteger(value));
   }

   public LinearRegression setLoss(final String value) {
      return (LinearRegression)this.set(this.loss(), value);
   }

   public LinearRegression setEpsilon(final double value) {
      return (LinearRegression)this.set(this.epsilon(), BoxesRunTime.boxToDouble(value));
   }

   public LinearRegression setMaxBlockSizeInMB(final double value) {
      return (LinearRegression)this.set(this.maxBlockSizeInMB(), BoxesRunTime.boxToDouble(value));
   }

   public LinearRegressionModel train(final Dataset dataset) {
      Object var2 = new Object();

      LinearRegressionModel var10000;
      try {
         var10000 = (LinearRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
            label179: {
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, .MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.featuresCol(), this.weightCol(), this.predictionCol(), this.solver(), this.tol(), this.elasticNetParam(), this.fitIntercept(), this.maxIter(), this.regParam(), this.standardization(), this.aggregationDepth(), this.loss(), this.epsilon(), this.maxBlockSizeInMB()}));
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var10 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var10 == null) {
                     break label179;
                  }
               } else if (var10000.equals(var10)) {
                  break label179;
               }

               instr.logWarning((Function0)(() -> "Input instances will be standardized, blockified to blocks, and then cached during training. Be careful of double caching!"));
            }

            int numFeatures;
            RDD instances;
            label183: {
               numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.featuresCol()));
               instr.logNumFeatures((long)numFeatures);
               instances = dataset.select(.MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkRegressionLabels((String)this.$(this.labelCol())), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
                  if (x0$1 != null) {
                     Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
                     if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(3) == 0) {
                        Object l = ((SeqOps)var3.get()).apply(0);
                        Object w = ((SeqOps)var3.get()).apply(1);
                        Object v = ((SeqOps)var3.get()).apply(2);
                        if (l instanceof Double) {
                           double var7 = BoxesRunTime.unboxToDouble(l);
                           if (w instanceof Double) {
                              double var9 = BoxesRunTime.unboxToDouble(w);
                              if (v instanceof Vector) {
                                 Vector var11 = (Vector)v;
                                 return new Instance(var7, var9, var11);
                              }
                           }
                        }
                     }
                  }

                  throw new MatchError(x0$1);
               }, scala.reflect.ClassTag..MODULE$.apply(Instance.class)).setName("training instances");
               Object var64 = this.$(this.loss());
               String var13 = LinearRegression$.MODULE$.SquaredError();
               if (var64 == null) {
                  if (var13 != null) {
                     break label183;
                  }
               } else if (!var64.equals(var13)) {
                  break label183;
               }

               label170: {
                  var64 = this.$(this.solver());
                  String var14 = LinearRegression$.MODULE$.Auto();
                  if (var64 == null) {
                     if (var14 != null) {
                        break label170;
                     }
                  } else if (!var64.equals(var14)) {
                     break label170;
                  }

                  if (numFeatures <= WeightedLeastSquares$.MODULE$.MAX_NUM_FEATURES()) {
                     throw new NonLocalReturnControl(var2, this.trainWithNormal(dataset, instances, instr));
                  }
               }

               var64 = this.$(this.solver());
               String var15 = LinearRegression$.MODULE$.Normal();
               if (var64 == null) {
                  if (var15 == null) {
                     throw new NonLocalReturnControl(var2, this.trainWithNormal(dataset, instances, instr));
                  }
               } else if (var64.equals(var15)) {
                  throw new NonLocalReturnControl(var2, this.trainWithNormal(dataset, instances, instr));
               }
            }

            Tuple2 var17 = Summarizer$.MODULE$.getRegressionSummarizers(instances, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("std", new scala.collection.immutable..colon.colon("count", scala.collection.immutable.Nil..MODULE$))));
            if (var17 != null) {
               SummarizerBuffer summarizer = (SummarizerBuffer)var17._1();
               SummarizerBuffer labelSummarizer = (SummarizerBuffer)var17._2();
               Tuple2 var16 = new Tuple2(summarizer, labelSummarizer);
               SummarizerBuffer summarizerx = (SummarizerBuffer)var16._1();
               SummarizerBuffer labelSummarizer = (SummarizerBuffer)var16._2();
               double yMean = labelSummarizer.mean().apply(0);
               double rawYStd = labelSummarizer.std().apply(0);
               instr.logNumExamples(labelSummarizer.count());
               instr.logNamedValue(Instrumentation.loggerTags$.MODULE$.meanOfLabels(), yMean);
               instr.logNamedValue(Instrumentation.loggerTags$.MODULE$.varianceOfLabels(), rawYStd);
               instr.logSumOfWeights(summarizerx.weightSum());
               double actualBlockSizeInMB = BoxesRunTime.unboxToDouble(this.$(this.maxBlockSizeInMB()));
               if (actualBlockSizeInMB == (double)0) {
                  actualBlockSizeInMB = InstanceBlock$.MODULE$.DefaultBlockSizeInMB();
                  scala.Predef..MODULE$.require(actualBlockSizeInMB > (double)0, () -> "inferred actual BlockSizeInMB must > 0");
                  instr.logNamedValue("actualBlockSizeInMB", Double.toString(actualBlockSizeInMB));
               }

               if (rawYStd == (double)0.0F) {
                  if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) || yMean == (double)0.0F) {
                     throw new NonLocalReturnControl(var2, this.trainWithConstantLabel(dataset, instr, numFeatures, yMean));
                  }

                  scala.Predef..MODULE$.require(BoxesRunTime.unboxToDouble(this.$(this.regParam())) == (double)0.0F, () -> "The standard deviation of the label is zero. Model cannot be regularized.");
                  instr.logWarning((Function0)(() -> "The standard deviation of the label is zero. Consider setting fitIntercept=true."));
               }

               double yStd = rawYStd > (double)0 ? rawYStd : scala.math.package..MODULE$.abs(yMean);
               double[] featuresMean = summarizerx.mean().toArray();
               double[] featuresStd = summarizerx.std().toArray();
               if (!BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numFeatures).exists((JFunction1.mcZI.sp)(i) -> featuresStd[i] == (double)0.0F && featuresMean[i] != (double)0.0F)) {
                  instr.logWarning((Function0)(() -> "Fitting LinearRegressionModel without intercept on dataset with constant nonzero column, Spark MLlib outputs zero coefficients for constant nonzero columns. This behavior is the same as R glmnet but different from LIBSVM."));
               }

               double var69;
               label139: {
                  label188: {
                     String var34 = (String)this.$(this.loss());
                     String var67 = LinearRegression$.MODULE$.SquaredError();
                     if (var67 == null) {
                        if (var34 == null) {
                           break label188;
                        }
                     } else if (var67.equals(var34)) {
                        break label188;
                     }

                     var67 = LinearRegression$.MODULE$.Huber();
                     if (var67 == null) {
                        if (var34 != null) {
                           throw new MatchError(var34);
                        }
                     } else if (!var67.equals(var34)) {
                        throw new MatchError(var34);
                     }

                     var69 = BoxesRunTime.unboxToDouble(this.$(this.regParam()));
                     break label139;
                  }

                  var69 = BoxesRunTime.unboxToDouble(this.$(this.regParam())) / yStd;
               }

               double effectiveRegParam = var69;
               double effectiveL1RegParam = BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())) * effectiveRegParam;
               double effectiveL2RegParam = ((double)1.0F - BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam()))) * effectiveRegParam;
               Function1 getFeaturesStd = (j) -> j >= 0 && j < numFeatures ? featuresStd[j] : (double)0.0F;
               Object var70;
               if (effectiveL2RegParam != (double)0.0F) {
                  Function1 shouldApply = (idx) -> idx >= 0 && idx < numFeatures;
                  var70 = new Some(new L2Regularization(effectiveL2RegParam, shouldApply, (Option)(BoxesRunTime.unboxToBoolean(this.$(this.standardization())) ? scala.None..MODULE$ : new Some(getFeaturesStd))));
               } else {
                  var70 = scala.None..MODULE$;
               }

               Option regularization;
               FirstOrderMinimizer optimizer;
               label123: {
                  label190: {
                     regularization = (Option)var70;
                     optimizer = this.createOptimizer(effectiveRegParam, effectiveL1RegParam, numFeatures, featuresStd);
                     String var46 = (String)this.$(this.loss());
                     String var71 = LinearRegression$.MODULE$.SquaredError();
                     if (var71 == null) {
                        if (var46 == null) {
                           break label190;
                        }
                     } else if (var71.equals(var46)) {
                        break label190;
                     }

                     var71 = LinearRegression$.MODULE$.Huber();
                     if (var71 == null) {
                        if (var46 != null) {
                           throw new MatchError(var46);
                        }
                     } else if (!var71.equals(var46)) {
                        throw new MatchError(var46);
                     }

                     int dim = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? numFeatures + 2 : numFeatures + 1;
                     var73 = (double[])scala.Array..MODULE$.fill(dim, (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double());
                     break label123;
                  }

                  var73 = (double[])scala.Array..MODULE$.ofDim(numFeatures, scala.reflect.ClassTag..MODULE$.Double());
               }

               double[] initialSolution = var73;
               Tuple2 var51 = this.trainImpl(instances, actualBlockSizeInMB, yMean, yStd, featuresMean, featuresStd, initialSolution, regularization, optimizer);
               if (var51 != null) {
                  double[] parameters = (double[])var51._1();
                  double[] objectiveHistory = (double[])var51._2();
                  Tuple2 var50 = new Tuple2(parameters, objectiveHistory);
                  double[] parametersx = (double[])var50._1();
                  double[] objectiveHistoryx = (double[])var50._2();
                  if (parametersx == null) {
                     MLUtils$.MODULE$.optimizerFailed(instr, optimizer.getClass());
                  }

                  LinearRegressionModel model = this.createModel(parametersx, yMean, yStd, featuresMean, featuresStd);
                  Tuple2 var58 = model.findSummaryModelAndPredictionCol();
                  if (var58 != null) {
                     LinearRegressionModel summaryModel = (LinearRegressionModel)var58._1();
                     String predictionColName = (String)var58._2();
                     Tuple2 var57 = new Tuple2(summaryModel, predictionColName);
                     LinearRegressionModel summaryModelx = (LinearRegressionModel)var57._1();
                     String predictionColNamex = (String)var57._2();
                     LinearRegressionTrainingSummary trainingSummary = new LinearRegressionTrainingSummary(summaryModelx.transform(dataset), predictionColNamex, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), model, new double[]{(double)0.0F}, objectiveHistoryx);
                     return (LinearRegressionModel)model.setSummary(new Some(trainingSummary));
                  } else {
                     throw new MatchError(var58);
                  }
               } else {
                  throw new MatchError(var51);
               }
            } else {
               throw new MatchError(var17);
            }
         });
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = (LinearRegressionModel)var4.value();
      }

      return var10000;
   }

   private LinearRegressionModel trainWithNormal(final Dataset dataset, final RDD instances, final Instrumentation instr) {
      WeightedLeastSquares optimizer = new WeightedLeastSquares(BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToDouble(this.$(this.regParam())), BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())), BoxesRunTime.unboxToBoolean(this.$(this.standardization())), true, WeightedLeastSquares.Auto$.MODULE$, BoxesRunTime.unboxToInt(this.$(this.maxIter())), BoxesRunTime.unboxToDouble(this.$(this.tol())));
      WeightedLeastSquaresModel model = optimizer.fit(instances, OptionalInstrumentation$.MODULE$.create(instr), optimizer.fit$default$3());
      LinearRegressionModel lrModel = (LinearRegressionModel)this.copyValues(new LinearRegressionModel(this.uid(), model.coefficients(), model.intercept()), this.copyValues$default$2());
      Tuple2 var9 = lrModel.findSummaryModelAndPredictionCol();
      if (var9 != null) {
         LinearRegressionModel summaryModel = (LinearRegressionModel)var9._1();
         String predictionColName = (String)var9._2();
         Tuple2 var8 = new Tuple2(summaryModel, predictionColName);
         LinearRegressionModel summaryModel = (LinearRegressionModel)var8._1();
         String predictionColName = (String)var8._2();
         LinearRegressionTrainingSummary trainingSummary = new LinearRegressionTrainingSummary(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), summaryModel, model.diagInvAtWA().toArray(), model.objectiveHistory());
         return (LinearRegressionModel)lrModel.setSummary(new Some(trainingSummary));
      } else {
         throw new MatchError(var9);
      }
   }

   private LinearRegressionModel trainWithConstantLabel(final Dataset dataset, final Instrumentation instr, final int numFeatures, final double yMean) {
      if (yMean == (double)0.0F) {
         instr.logWarning((Function0)(() -> "Mean and standard deviation of the label are zero, so the coefficients and the intercept will all be zero; as a result, training is not needed."));
      } else {
         instr.logWarning((Function0)(() -> "The standard deviation of the label is zero, so the coefficients will be zeros and the intercept will be the mean of the label; as a result, training is not needed."));
      }

      Vector coefficients = org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(numFeatures, (Seq)scala.package..MODULE$.Seq().empty());
      LinearRegressionModel model = (LinearRegressionModel)this.copyValues(new LinearRegressionModel(this.uid(), coefficients, yMean), this.copyValues$default$2());
      Tuple2 var12 = model.findSummaryModelAndPredictionCol();
      if (var12 != null) {
         LinearRegressionModel summaryModel = (LinearRegressionModel)var12._1();
         String predictionColName = (String)var12._2();
         Tuple2 var11 = new Tuple2(summaryModel, predictionColName);
         LinearRegressionModel summaryModel = (LinearRegressionModel)var11._1();
         String predictionColName = (String)var11._2();
         LinearRegressionTrainingSummary trainingSummary = new LinearRegressionTrainingSummary(summaryModel.transform(dataset), predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), model, new double[]{(double)0.0F}, new double[]{(double)0.0F});
         return (LinearRegressionModel)model.setSummary(new Some(trainingSummary));
      } else {
         throw new MatchError(var12);
      }
   }

   private FirstOrderMinimizer createOptimizer(final double effectiveRegParam, final double effectiveL1RegParam, final int numFeatures, final double[] featuresStd) {
      label50: {
         String var8 = (String)this.$(this.loss());
         String var10000 = LinearRegression$.MODULE$.SquaredError();
         if (var10000 == null) {
            if (var8 == null) {
               break label50;
            }
         } else if (var10000.equals(var8)) {
            break label50;
         }

         var10000 = LinearRegression$.MODULE$.Huber();
         if (var10000 == null) {
            if (var8 != null) {
               throw new MatchError(var8);
            }
         } else if (!var10000.equals(var8)) {
            throw new MatchError(var8);
         }

         int dim = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? numFeatures + 2 : numFeatures + 1;
         DenseVector lowerBounds = breeze.linalg.DenseVector..MODULE$.apply(scala.Array..MODULE$.fill(dim, (JFunction0.mcD.sp)() -> -Double.MAX_VALUE, scala.reflect.ClassTag..MODULE$.Double()));
         lowerBounds.update$mcD$sp(dim - 1, Double.MIN_VALUE);
         DenseVector upperBounds = breeze.linalg.DenseVector..MODULE$.apply(scala.Array..MODULE$.fill(dim, (JFunction0.mcD.sp)() -> Double.MAX_VALUE, scala.reflect.ClassTag..MODULE$.Double()));
         return new LBFGSB(lowerBounds, upperBounds, BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.optimize.LBFGSB..MODULE$.$lessinit$greater$default$6(), breeze.optimize.LBFGSB..MODULE$.$lessinit$greater$default$7());
      }

      if (BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())) != (double)0.0F && effectiveRegParam != (double)0.0F) {
         boolean standardizationParam = BoxesRunTime.unboxToBoolean(this.$(this.standardization()));
         return new OWLQN(BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, effectiveL1RegFun$1(standardizationParam, effectiveL1RegParam, featuresStd), BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.linalg.DenseVector..MODULE$.space_Double());
      } else {
         return new LBFGS(BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.linalg.DenseVector..MODULE$.space_Double());
      }
   }

   private Tuple2 trainImpl(final RDD instances, final double actualBlockSizeInMB, final double yMean, final double yStd, final double[] featuresMean, final double[] featuresStd, final double[] initialSolution, final Option regularization, final FirstOrderMinimizer optimizer) {
      int numFeatures = featuresStd.length;
      double[] inverseStd = (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps(featuresStd), (JFunction1.mcDD.sp)(std) -> std != (double)0 ? (double)1.0F / std : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
      double[] scaledMean = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> inverseStd[i] * featuresMean[i], scala.reflect.ClassTag..MODULE$.Double());
      Broadcast bcInverseStd = instances.context().broadcast(inverseStd, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)));
      Broadcast bcScaledMean = instances.context().broadcast(scaledMean, scala.reflect.ClassTag..MODULE$.apply(.MODULE$.arrayClass(Double.TYPE)));
      RDD standardized = instances.mapPartitions((iter) -> {
         Function1 func = StandardScalerModel$.MODULE$.getTransformFunc((double[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Double()), (double[])bcInverseStd.value(), false, true);
         return iter.map((x0$1) -> {
            if (x0$1 != null) {
               double label = x0$1.label();
               double weight = x0$1.weight();
               Vector vec = x0$1.features();
               return new Instance(label, weight, (Vector)func.apply(vec));
            } else {
               throw new MatchError(x0$1);
            }
         });
      }, instances.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Instance.class));
      long maxMemUsage = (long)scala.runtime.RichDouble..MODULE$.ceil$extension(scala.Predef..MODULE$.doubleWrapper(actualBlockSizeInMB * (double)1024L * (double)1024L));
      RDD var10000 = InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(standardized, maxMemUsage).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      String var10001 = this.uid();
      RDD blocks = var10000.setName(var10001 + ": training blocks (blockSizeInMB=" + actualBlockSizeInMB + ")");
      if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         label72: {
            Object var39 = this.$(this.loss());
            String var23 = LinearRegression$.MODULE$.Huber();
            if (var39 == null) {
               if (var23 != null) {
                  break label72;
               }
            } else if (!var39.equals(var23)) {
               break label72;
            }

            double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, initialSolution, 1, scaledMean, 1);
            initialSolution[numFeatures] += adapt;
         }
      }

      label65: {
         label77: {
            String var27 = (String)this.$(this.loss());
            String var40 = LinearRegression$.MODULE$.SquaredError();
            if (var40 == null) {
               if (var27 == null) {
                  break label77;
               }
            } else if (var40.equals(var27)) {
               break label77;
            }

            var40 = LinearRegression$.MODULE$.Huber();
            if (var40 == null) {
               if (var27 != null) {
                  throw new MatchError(var27);
               }
            } else if (!var40.equals(var27)) {
               throw new MatchError(var27);
            }

            Function1 getAggregatorFunc = (x$7) -> new HuberBlockAggregator(bcInverseStd, bcScaledMean, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), BoxesRunTime.unboxToDouble(this.$(this.epsilon())), x$7);
            var42 = new RDDLossFunction(blocks, getAggregatorFunc, regularization, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(InstanceBlock.class), scala.reflect.ClassTag..MODULE$.apply(HuberBlockAggregator.class));
            break label65;
         }

         Function1 getAggregatorFunc = (x$6) -> new LeastSquaresBlockAggregator(bcInverseStd, bcScaledMean, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), yStd, yMean, x$6);
         var42 = new RDDLossFunction(blocks, getAggregatorFunc, regularization, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(InstanceBlock.class), scala.reflect.ClassTag..MODULE$.apply(LeastSquaresBlockAggregator.class));
      }

      RDDLossFunction costFun = var42;
      Iterator states = optimizer.iterations(new CachedDiffFunction(costFun, breeze.linalg.DenseVector..MODULE$.canCopyDenseVector(scala.reflect.ClassTag..MODULE$.Double())), new DenseVector.mcD.sp(initialSolution));
      ArrayBuilder arrayBuilder = scala.collection.mutable.ArrayBuilder..MODULE$.make(scala.reflect.ClassTag..MODULE$.Double());
      FirstOrderMinimizer.State state = null;

      while(states.hasNext()) {
         state = (FirstOrderMinimizer.State)states.next();
         arrayBuilder.$plus$eq(BoxesRunTime.boxToDouble(state.adjustedValue()));
      }

      blocks.unpersist(blocks.unpersist$default$1());
      bcInverseStd.destroy();
      bcScaledMean.destroy();
      double[] solution = state == null ? null : ((DenseVector)state.x()).toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         Object var43 = this.$(this.loss());
         String var36 = LinearRegression$.MODULE$.Huber();
         if (var43 == null) {
            if (var36 != null) {
               return new Tuple2(solution, arrayBuilder.result());
            }
         } else if (!var43.equals(var36)) {
            return new Tuple2(solution, arrayBuilder.result());
         }

         if (solution != null) {
            double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, solution, 1, scaledMean, 1);
            solution[numFeatures] -= adapt;
         }
      }

      return new Tuple2(solution, arrayBuilder.result());
   }

   private LinearRegressionModel createModel(final double[] solution, final double yMean, final double yStd, final double[] featuresMean, final double[] featuresStd) {
      int numFeatures;
      double var24;
      label64: {
         label63: {
            numFeatures = featuresStd.length;
            Object var10000 = this.$(this.loss());
            String var13 = LinearRegression$.MODULE$.Huber();
            if (var10000 == null) {
               if (var13 == null) {
                  break label63;
               }
            } else if (var10000.equals(var13)) {
               break label63;
            }

            var24 = yStd;
            break label64;
         }

         var24 = (double)1.0F;
      }

      double multiplier = var24;
      double[] rawCoefficients = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> featuresStd[i] != (double)0 ? solution[i] * multiplier / featuresStd[i] : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
      if (!BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         var24 = (double)0.0F;
      } else {
         label70: {
            label69: {
               String var17 = (String)this.$(this.loss());
               String var25 = LinearRegression$.MODULE$.SquaredError();
               if (var25 == null) {
                  if (var17 == null) {
                     break label69;
                  }
               } else if (var25.equals(var17)) {
                  break label69;
               }

               var25 = LinearRegression$.MODULE$.Huber();
               if (var25 == null) {
                  if (var17 != null) {
                     throw new MatchError(var17);
                  }
               } else if (!var25.equals(var17)) {
                  throw new MatchError(var17);
               }

               var24 = solution[numFeatures];
               break label70;
            }

            var24 = yMean - org.apache.spark.ml.linalg.BLAS..MODULE$.dot(org.apache.spark.ml.linalg.Vectors..MODULE$.dense(rawCoefficients), org.apache.spark.ml.linalg.Vectors..MODULE$.dense(featuresMean));
         }
      }

      double intercept;
      Vector coefficients;
      label38: {
         label37: {
            intercept = var24;
            coefficients = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(rawCoefficients).compressed();
            Object var28 = this.$(this.loss());
            String var23 = LinearRegression$.MODULE$.Huber();
            if (var28 == null) {
               if (var23 == null) {
                  break label37;
               }
            } else if (var28.equals(var23)) {
               break label37;
            }

            var24 = (double)1.0F;
            break label38;
         }

         var24 = BoxesRunTime.unboxToDouble(scala.collection.ArrayOps..MODULE$.last$extension(scala.Predef..MODULE$.doubleArrayOps(solution)));
      }

      double scale = var24;
      return (LinearRegressionModel)this.copyValues(new LinearRegressionModel(this.uid(), coefficients, intercept, scale), this.copyValues$default$2());
   }

   public LinearRegression copy(final ParamMap extra) {
      return (LinearRegression)this.defaultCopy(extra);
   }

   private static final Function1 effectiveL1RegFun$1(final boolean standardizationParam$1, final double effectiveL1RegParam$1, final double[] featuresStd$2) {
      return (JFunction1.mcDI.sp)(index) -> {
         if (standardizationParam$1) {
            return effectiveL1RegParam$1;
         } else {
            return featuresStd$2[index] != (double)0.0F ? effectiveL1RegParam$1 / featuresStd$2[index] : (double)0.0F;
         }
      };
   }

   public LinearRegression(final String uid) {
      this.uid = uid;
      HasRegParam.$init$(this);
      HasElasticNetParam.$init$(this);
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasFitIntercept.$init$(this);
      HasStandardization.$init$(this);
      HasWeightCol.$init$(this);
      HasSolver.$init$(this);
      HasAggregationDepth.$init$(this);
      HasLoss.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      LinearRegressionParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public LinearRegression() {
      this(Identifiable$.MODULE$.randomUID("linReg"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
