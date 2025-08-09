package org.apache.spark.ml.classification;

import breeze.linalg.DenseVector;
import breeze.optimize.CachedDiffFunction;
import breeze.optimize.FirstOrderMinimizer;
import breeze.optimize.LBFGS;
import breeze.optimize.LBFGSB;
import breeze.optimize.OWLQN;
import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.SparkException;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.ml.feature.Instance;
import org.apache.spark.ml.feature.InstanceBlock;
import org.apache.spark.ml.feature.InstanceBlock$;
import org.apache.spark.ml.feature.StandardScalerModel$;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseMatrix;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.optim.aggregator.BinaryLogisticBlockAggregator;
import org.apache.spark.ml.optim.aggregator.MultinomialLogisticBlockAggregator;
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
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasStandardization;
import org.apache.spark.ml.param.shared.HasThreshold;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.MultiClassSummarizer;
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
import org.apache.spark.ml.util.MetadataUtils$;
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
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\rEc\u0001B\u0016-\u0001]B\u0001\"\u0016\u0001\u0003\u0006\u0004%\tE\u0016\u0005\t[\u0002\u0011\t\u0011)A\u0005/\")q\u000e\u0001C\u0001a\")q\u000e\u0001C\u0001m\")\u0001\u0010\u0001C\u0001s\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\t\u0019\u0003\u0001C\u0001\u0003KAq!!\r\u0001\t\u0003\t\u0019\u0004C\u0004\u0002>\u0001!\t!a\u0010\t\u000f\u0005%\u0003\u0001\"\u0011\u0002L!9\u0011\u0011\u000b\u0001\u0005B\u0005M\u0003bBA,\u0001\u0011\u0005\u0011\u0011\f\u0005\b\u0003G\u0002A\u0011IA3\u0011\u001d\t\t\b\u0001C!\u0003gBq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002\u0000\u0001!\t!!!\t\u000f\u0005E\u0005\u0001\"\u0001\u0002\u0014\"9\u0011\u0011\u0014\u0001\u0005\u0002\u0005m\u0005bBAQ\u0001\u0011\u0005\u00111\u0015\u0005\b\u0003S\u0003A\u0011AAV\u0011\u001d\t)\f\u0001C\u0005\u0003oC\u0011\"a2\u0001\u0001\u0004%I!!3\t\u0013\u0005E\u0007\u00011A\u0005\n\u0005M\u0007\u0002CAm\u0001\u0001\u0006K!a3\t\u000f\u0005m\u0007\u0001\"\u0001\u0002^\"A\u0011\u0011\u001e\u0001\u0005\u0012A\nY\u000fC\u0004\u0003\u0016\u0001!IAa\u0006\t\u000f\tu\u0001\u0001\"\u0003\u0003 !9!1\b\u0001\u0005\n\tu\u0002b\u0002B'\u0001\u0011%!q\n\u0005\b\u0005\u007f\u0002A\u0011\u0002BA\u0011\u001d\u0011\t\u000b\u0001C\u0005\u0005GCqAa;\u0001\t\u0003\u0012ioB\u0004\u0004\u00041B\ta!\u0002\u0007\r-b\u0003\u0012AB\u0004\u0011\u0019yW\u0005\"\u0001\u0004&!91qE\u0013\u0005B\r%\u0002BCB\u0019K\t\u0007I\u0011\u0001\u0017\u00044!A1\u0011I\u0013!\u0002\u0013\u0019)\u0004C\u0005\u0004D\u0015\n\t\u0011\"\u0003\u0004F\t\u0011Bj\\4jgRL7MU3he\u0016\u001c8/[8o\u0015\tic&\u0001\bdY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8\u000b\u0005=\u0002\u0014AA7m\u0015\t\t$'A\u0003ta\u0006\u00148N\u0003\u00024i\u00051\u0011\r]1dQ\u0016T\u0011!N\u0001\u0004_J<7\u0001A\n\u0006\u0001a2\u0015j\u0014\t\u0006sib$iQ\u0007\u0002Y%\u00111\b\f\u0002\u0018!J|'-\u00192jY&\u001cH/[2DY\u0006\u001c8/\u001b4jKJ\u0004\"!\u0010!\u000e\u0003yR!a\u0010\u0018\u0002\r1Lg.\u00197h\u0013\t\teH\u0001\u0004WK\u000e$xN\u001d\t\u0003s\u0001\u0001\"!\u000f#\n\u0005\u0015c#a\u0006'pO&\u001cH/[2SK\u001e\u0014Xm]:j_:lu\u000eZ3m!\tIt)\u0003\u0002IY\tABj\\4jgRL7MU3he\u0016\u001c8/[8o!\u0006\u0014\u0018-\\:\u0011\u0005)kU\"A&\u000b\u00051s\u0013\u0001B;uS2L!AT&\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mKB\u0011\u0001kU\u0007\u0002#*\u0011!\u000bM\u0001\tS:$XM\u001d8bY&\u0011A+\u0015\u0002\b\u0019><w-\u001b8h\u0003\r)\u0018\u000eZ\u000b\u0002/B\u0011\u0001,\u0019\b\u00033~\u0003\"AW/\u000e\u0003mS!\u0001\u0018\u001c\u0002\rq\u0012xn\u001c;?\u0015\u0005q\u0016!B:dC2\f\u0017B\u00011^\u0003\u0019\u0001&/\u001a3fM&\u0011!m\u0019\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0001l\u0006fA\u0001fWB\u0011a-[\u0007\u0002O*\u0011\u0001\u000eM\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00016h\u0005\u0015\u0019\u0016N\\2fC\u0005a\u0017!B\u0019/i9\u0002\u0014\u0001B;jI\u0002B3AA3l\u0003\u0019a\u0014N\\5u}Q\u0011!)\u001d\u0005\u0006+\u000e\u0001\ra\u0016\u0015\u0004c\u0016\\\u0007fA\u0002fi\u0006\nQ/A\u00032]Ir\u0003\u0007F\u0001CQ\r!Qm[\u0001\fg\u0016$(+Z4QCJ\fW\u000e\u0006\u0002{w6\t\u0001\u0001C\u0003}\u000b\u0001\u0007Q0A\u0003wC2,X\r\u0005\u0002\u007f\u007f6\tQ,C\u0002\u0002\u0002u\u0013a\u0001R8vE2,\u0007fA\u0003fi\u0006\u00112/\u001a;FY\u0006\u001cH/[2OKR\u0004\u0016M]1n)\rQ\u0018\u0011\u0002\u0005\u0006y\u001a\u0001\r! \u0015\u0004\r\u0015\\\u0017AC:fi6\u000b\u00070\u0013;feR\u0019!0!\u0005\t\rq<\u0001\u0019AA\n!\rq\u0018QC\u0005\u0004\u0003/i&aA%oi\"\u001aq!\u001a;\u0002\rM,G\u000fV8m)\rQ\u0018q\u0004\u0005\u0006y\"\u0001\r! \u0015\u0004\u0011\u0015\\\u0017aD:fi\u001aKG/\u00138uKJ\u001cW\r\u001d;\u0015\u0007i\f9\u0003\u0003\u0004}\u0013\u0001\u0007\u0011\u0011\u0006\t\u0004}\u0006-\u0012bAA\u0017;\n9!i\\8mK\u0006t\u0007fA\u0005fW\u0006I1/\u001a;GC6LG.\u001f\u000b\u0004u\u0006U\u0002\"\u0002?\u000b\u0001\u00049\u0006\u0006\u0002\u0006f\u0003s\t#!a\u000f\u0002\u000bIr\u0013G\f\u0019\u0002%M,Go\u0015;b]\u0012\f'\u000fZ5{CRLwN\u001c\u000b\u0004u\u0006\u0005\u0003B\u0002?\f\u0001\u0004\tI\u0003\u000b\u0003\fK\u0006\u0015\u0013EAA$\u0003\u0015\td&\u000e\u00181\u00031\u0019X\r\u001e+ie\u0016\u001c\bn\u001c7e)\rQ\u0018Q\n\u0005\u0006y2\u0001\r! \u0015\u0005\u0019\u0015\f)%\u0001\u0007hKR$\u0006N]3tQ>dG-F\u0001~Q\u0011iQ-!\u0012\u0002\u0019M,GoV3jO\"$8i\u001c7\u0015\u0007i\fY\u0006C\u0003}\u001d\u0001\u0007q\u000b\u000b\u0003\u000fK\u0006}\u0013EAA1\u0003\u0015\tdF\u000e\u00181\u00035\u0019X\r\u001e+ie\u0016\u001c\bn\u001c7egR\u0019!0a\u001a\t\rq|\u0001\u0019AA5!\u0011q\u00181N?\n\u0007\u00055TLA\u0003BeJ\f\u0017\u0010\u000b\u0003\u0010K\u0006\u0015\u0013!D4fiRC'/Z:i_2$7/\u0006\u0002\u0002j!\"\u0001#ZA#\u0003M\u0019X\r^!hOJ,w-\u0019;j_:$U\r\u001d;i)\rQ\u00181\u0010\u0005\u0007yF\u0001\r!a\u0005)\tE)\u0017\u0011H\u0001\u001dg\u0016$Hj\\<fe\n{WO\u001c3t\u001f:\u001cu.\u001a4gS\u000eLWM\u001c;t)\rQ\u00181\u0011\u0005\u0007yJ\u0001\r!!\"\u0011\u0007u\n9)C\u0002\u0002\nz\u0012a!T1ue&D\b\u0006\u0002\nf\u0003\u001b\u000b#!a$\u0002\u000bIr#G\f\u0019\u00029M,G/\u00169qKJ\u0014u.\u001e8eg>s7i\\3gM&\u001c\u0017.\u001a8ugR\u0019!0!&\t\rq\u001c\u0002\u0019AACQ\u0011\u0019R-!$\u00025M,G\u000fT8xKJ\u0014u.\u001e8eg>s\u0017J\u001c;fe\u000e,\u0007\u000f^:\u0015\u0007i\fi\nC\u0003})\u0001\u0007A\b\u000b\u0003\u0015K\u00065\u0015AG:fiV\u0003\b/\u001a:C_VtGm](o\u0013:$XM]2faR\u001cHc\u0001>\u0002&\")A0\u0006a\u0001y!\"Q#ZAG\u0003M\u0019X\r^'bq\ncwnY6TSj,\u0017J\\'C)\rQ\u0018Q\u0016\u0005\u0006yZ\u0001\r! \u0015\u0005-\u0015\f\t,\t\u0002\u00024\u0006)1GL\u0019/a\u0005i\u0013m]:feR\u0014u.\u001e8e\u0007>t7\u000f\u001e:bS:,Gm\u00149uS6L'0\u0019;j_:\u0004\u0016M]1ngZ\u000bG.\u001b3\u0015\r\u0005e\u0016qXAb!\rq\u00181X\u0005\u0004\u0003{k&\u0001B+oSRDq!!1\u0018\u0001\u0004\t\u0019\"\u0001\nok6\u001cu.\u001a4gS\u000eLWM\u001c;TKR\u001c\bbBAc/\u0001\u0007\u00111C\u0001\f]Vlg)Z1ukJ,7/A\bpaRLe.\u001b;jC2lu\u000eZ3m+\t\tY\r\u0005\u0003\u007f\u0003\u001b\u001c\u0015bAAh;\n1q\n\u001d;j_:\f1c\u001c9u\u0013:LG/[1m\u001b>$W\r\\0%KF$B!!/\u0002V\"I\u0011q[\r\u0002\u0002\u0003\u0007\u00111Z\u0001\u0004q\u0012\n\u0014\u0001E8qi&s\u0017\u000e^5bY6{G-\u001a7!\u0003=\u0019X\r^%oSRL\u0017\r\\'pI\u0016dGc\u0001>\u0002`\"1\u0011\u0011]\u000eA\u0002\r\u000bQ!\\8eK2DCaG3\u0002f\u0006\u0012\u0011q]\u0001\u0006g9\u001ad\u0006M\u0001\u0006iJ\f\u0017N\u001c\u000b\u0004\u0007\u00065\bbBAx9\u0001\u0007\u0011\u0011_\u0001\bI\u0006$\u0018m]3ua\u0011\t\u0019Pa\u0001\u0011\r\u0005U\u00181`A\u0000\u001b\t\t9PC\u0002\u0002zB\n1a]9m\u0013\u0011\ti0a>\u0003\u000f\u0011\u000bG/Y:fiB!!\u0011\u0001B\u0002\u0019\u0001!AB!\u0002\u0002n\u0006\u0005\t\u0011!B\u0001\u0005\u000f\u00111a\u0018\u00132#\u0011\u0011IAa\u0004\u0011\u0007y\u0014Y!C\u0002\u0003\u000eu\u0013qAT8uQ&tw\rE\u0002\u007f\u0005#I1Aa\u0005^\u0005\r\te._\u0001\u0011G\",7m['vYRLgn\\7jC2$B!!\u000b\u0003\u001a!9!1D\u000fA\u0002\u0005M\u0011A\u00038v[\u000ec\u0017m]:fg\u0006Y1M]3bi\u0016lu\u000eZ3m)-\u0019%\u0011\u0005B\u0017\u0005_\u0011\u0019Da\u000e\t\u000f\u0005=h\u00041\u0001\u0003$A\"!Q\u0005B\u0015!\u0019\t)0a?\u0003(A!!\u0011\u0001B\u0015\t1\u0011YC!\t\u0002\u0002\u0003\u0005)\u0011\u0001B\u0004\u0005\ryFE\r\u0005\b\u00057q\u0002\u0019AA\n\u0011\u001d\u0011\tD\ba\u0001\u0003\u000b\u000b\u0011cY8fM\u001aL7-[3oi6\u000bGO]5y\u0011\u0019\u0011)D\ba\u0001y\u0005y\u0011N\u001c;fe\u000e,\u0007\u000f\u001e,fGR|'\u000fC\u0004\u0003:y\u0001\r!!\u001b\u0002!=\u0014'.Z2uSZ,\u0007*[:u_JL\u0018\u0001D2sK\u0006$XMQ8v]\u0012\u001cH\u0003\u0003B \u0005\u000b\u00129E!\u0013\u0011\u000fy\u0014\t%!\u001b\u0002j%\u0019!1I/\u0003\rQ+\b\u000f\\33\u0011\u001d\u0011Yb\ba\u0001\u0003'Aq!!2 \u0001\u0004\t\u0019\u0002C\u0004\u0003L}\u0001\r!!\u001b\u0002\u0017\u0019,\u0017\r^;sKN\u001cF\u000fZ\u0001\u0010GJ,\u0017\r^3PaRLW.\u001b>feRa!\u0011\u000bB9\u0005g\u0012)Ha\u001e\u0003|AA!1\u000bB/\u0005C\u0012Y'\u0004\u0002\u0003V)!!q\u000bB-\u0003!y\u0007\u000f^5nSj,'B\u0001B.\u0003\u0019\u0011'/Z3{K&!!q\fB+\u0005M1\u0015N]:u\u001fJ$WM]'j]&l\u0017N_3s!\u0015\u0011\u0019Ga\u001a~\u001b\t\u0011)GC\u0002@\u00053JAA!\u001b\u0003f\tYA)\u001a8tKZ+7\r^8s!\u0019\u0011\u0019F!\u001c\u0003b%!!q\u000eB+\u00051!\u0015N\u001a4Gk:\u001cG/[8o\u0011\u001d\u0011Y\u0002\ta\u0001\u0003'Aq!!2!\u0001\u0004\t\u0019\u0002C\u0004\u0003L\u0001\u0002\r!!\u001b\t\u000f\te\u0004\u00051\u0001\u0002j\u0005YAn\\<fe\n{WO\u001c3t\u0011\u001d\u0011i\b\ta\u0001\u0003S\n1\"\u001e9qKJ\u0014u.\u001e8eg\u0006)2M]3bi\u0016Le.\u001b;jC2\u001cv\u000e\\;uS>tG\u0003\u0005BB\u0005\u0013\u0013YI!$\u0003\u0012\nM%Q\u0013BL!\ri$QQ\u0005\u0004\u0005\u000fs$a\u0003#f]N,W*\u0019;sSbDqAa\u0007\"\u0001\u0004\t\u0019\u0002C\u0004\u0002F\u0006\u0002\r!a\u0005\t\u000f\t=\u0015\u00051\u0001\u0002j\u0005I\u0001.[:u_\u001e\u0014\u0018-\u001c\u0005\b\u0005\u0017\n\u0003\u0019AA5\u0011\u001d\u0011I(\ta\u0001\u0003SBqA! \"\u0001\u0004\tI\u0007C\u0004\u0003\u001a\u0006\u0002\rAa'\u0002\u000b%t7\u000f\u001e:\u0011\u0007)\u0013i*C\u0002\u0003 .\u0013q\"\u00138tiJ,X.\u001a8uCRLwN\\\u0001\niJ\f\u0017N\\%na2$\"Ca\u0010\u0003&\n\u0005'Q\u0019Bd\u0005\u0017\u0014iM!5\u0003h\"9!q\u0015\u0012A\u0002\t%\u0016!C5ogR\fgnY3t!\u0019\u0011YK!-\u000366\u0011!Q\u0016\u0006\u0004\u0005_\u0003\u0014a\u0001:eI&!!1\u0017BW\u0005\r\u0011F\t\u0012\t\u0005\u0005o\u0013i,\u0004\u0002\u0003:*\u0019!1\u0018\u0018\u0002\u000f\u0019,\u0017\r^;sK&!!q\u0018B]\u0005!Ien\u001d;b]\u000e,\u0007B\u0002BbE\u0001\u0007Q0A\nbGR,\u0018\r\u001c\"m_\u000e\\7+\u001b>f\u0013:l%\tC\u0004\u0003L\t\u0002\r!!\u001b\t\u000f\t%'\u00051\u0001\u0002j\u0005aa-Z1ukJ,7/T3b]\"9!1\u0004\u0012A\u0002\u0005M\u0001b\u0002BhE\u0001\u0007\u0011\u0011N\u0001\u0010S:LG/[1m'>dW\u000f^5p]\"9!1\u001b\u0012A\u0002\tU\u0017A\u0004:fOVd\u0017M]5{CRLwN\u001c\t\u0006}\u00065'q\u001b\t\u0005\u00053\u0014\u0019/\u0004\u0002\u0003\\*!!Q\u001cBp\u0003\u0011awn]:\u000b\u0007\t\u0005h&A\u0003paRLW.\u0003\u0003\u0003f\nm'\u0001\u0005'3%\u0016<W\u000f\\1sSj\fG/[8o\u0011\u001d\u0011IO\ta\u0001\u0005#\n\u0011b\u001c9uS6L'0\u001a:\u0002\t\r|\u0007/\u001f\u000b\u0004\u0005\n=\bb\u0002ByG\u0001\u0007!1_\u0001\u0006Kb$(/\u0019\t\u0005\u0005k\u0014Y0\u0004\u0002\u0003x*\u0019!\u0011 \u0018\u0002\u000bA\f'/Y7\n\t\tu(q\u001f\u0002\t!\u0006\u0014\u0018-\\'ba\"\u001a1%Z6)\u0007\u0001)G/\u0001\nM_\u001eL7\u000f^5d%\u0016<'/Z:tS>t\u0007CA\u001d&'\u001d)3\u0011BB\b\u0007+\u00012A`B\u0006\u0013\r\u0019i!\u0018\u0002\u0007\u0003:L(+\u001a4\u0011\t)\u001b\tBQ\u0005\u0004\u0007'Y%!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0006\u0014G.\u001a\t\u0005\u0007/\u0019\t#\u0004\u0002\u0004\u001a)!11DB\u000f\u0003\tIwN\u0003\u0002\u0004 \u0005!!.\u0019<b\u0013\u0011\u0019\u0019c!\u0007\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\r\u0015\u0011\u0001\u00027pC\u0012$2AQB\u0016\u0011\u0019\u0019ic\na\u0001/\u0006!\u0001/\u0019;iQ\u00119S-a\u0018\u0002)M,\b\u000f]8si\u0016$g)Y7jYft\u0015-\\3t+\t\u0019)\u0004E\u0003\u007f\u0003W\u001a9\u0004\u0005\u0003\u0004:\r}RBAB\u001e\u0015\u0011\u0019id!\b\u0002\t1\fgnZ\u0005\u0004E\u000em\u0012!F:vaB|'\u000f^3e\r\u0006l\u0017\u000e\\=OC6,7\u000fI\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007\u000f\u0002Ba!\u000f\u0004J%!11JB\u001e\u0005\u0019y%M[3di\"\"Q%ZA0Q\u0011!S-a\u0018"
)
public class LogisticRegression extends ProbabilisticClassifier implements LogisticRegressionParams, DefaultParamsWritable {
   private final String uid;
   private Option optInitialModel;
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

   public static LogisticRegression load(final String path) {
      return LogisticRegression$.MODULE$.load(path);
   }

   public static MLReader read() {
      return LogisticRegression$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
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

   public LogisticRegression setRegParam(final double value) {
      return (LogisticRegression)this.set(this.regParam(), BoxesRunTime.boxToDouble(value));
   }

   public LogisticRegression setElasticNetParam(final double value) {
      return (LogisticRegression)this.set(this.elasticNetParam(), BoxesRunTime.boxToDouble(value));
   }

   public LogisticRegression setMaxIter(final int value) {
      return (LogisticRegression)this.set(this.maxIter(), BoxesRunTime.boxToInteger(value));
   }

   public LogisticRegression setTol(final double value) {
      return (LogisticRegression)this.set(this.tol(), BoxesRunTime.boxToDouble(value));
   }

   public LogisticRegression setFitIntercept(final boolean value) {
      return (LogisticRegression)this.set(this.fitIntercept(), BoxesRunTime.boxToBoolean(value));
   }

   public LogisticRegression setFamily(final String value) {
      return (LogisticRegression)this.set(this.family(), value);
   }

   public LogisticRegression setStandardization(final boolean value) {
      return (LogisticRegression)this.set(this.standardization(), BoxesRunTime.boxToBoolean(value));
   }

   public LogisticRegression setThreshold(final double value) {
      return (LogisticRegression)LogisticRegressionParams.setThreshold$(this, value);
   }

   public double getThreshold() {
      return LogisticRegressionParams.getThreshold$(this);
   }

   public LogisticRegression setWeightCol(final String value) {
      return (LogisticRegression)this.set(this.weightCol(), value);
   }

   public LogisticRegression setThresholds(final double[] value) {
      return (LogisticRegression)LogisticRegressionParams.setThresholds$(this, value);
   }

   public double[] getThresholds() {
      return LogisticRegressionParams.getThresholds$(this);
   }

   public LogisticRegression setAggregationDepth(final int value) {
      return (LogisticRegression)this.set(this.aggregationDepth(), BoxesRunTime.boxToInteger(value));
   }

   public LogisticRegression setLowerBoundsOnCoefficients(final Matrix value) {
      return (LogisticRegression)this.set(this.lowerBoundsOnCoefficients(), value);
   }

   public LogisticRegression setUpperBoundsOnCoefficients(final Matrix value) {
      return (LogisticRegression)this.set(this.upperBoundsOnCoefficients(), value);
   }

   public LogisticRegression setLowerBoundsOnIntercepts(final Vector value) {
      return (LogisticRegression)this.set(this.lowerBoundsOnIntercepts(), value);
   }

   public LogisticRegression setUpperBoundsOnIntercepts(final Vector value) {
      return (LogisticRegression)this.set(this.upperBoundsOnIntercepts(), value);
   }

   public LogisticRegression setMaxBlockSizeInMB(final double value) {
      return (LogisticRegression)this.set(this.maxBlockSizeInMB(), BoxesRunTime.boxToDouble(value));
   }

   private void assertBoundConstrainedOptimizationParamsValid(final int numCoefficientSets, final int numFeatures) {
      if (this.isSet(this.lowerBoundsOnCoefficients())) {
         .MODULE$.require(((Matrix)this.$(this.lowerBoundsOnCoefficients())).numRows() == numCoefficientSets && ((Matrix)this.$(this.lowerBoundsOnCoefficients())).numCols() == numFeatures, () -> {
            int var10000 = this.getLowerBoundsOnCoefficients().numRows();
            return "The shape of LowerBoundsOnCoefficients must be compatible with (1, number of features) for binomial regression, or (number of classes, number of features) for multinomial regression, but found: (" + var10000 + ", " + this.getLowerBoundsOnCoefficients().numCols() + ").";
         });
      }

      if (this.isSet(this.upperBoundsOnCoefficients())) {
         .MODULE$.require(((Matrix)this.$(this.upperBoundsOnCoefficients())).numRows() == numCoefficientSets && ((Matrix)this.$(this.upperBoundsOnCoefficients())).numCols() == numFeatures, () -> {
            int var10000 = this.getUpperBoundsOnCoefficients().numRows();
            return "The shape of upperBoundsOnCoefficients must be compatible with (1, number of features) for binomial regression, or (number of classes, number of features) for multinomial regression, but found: (" + var10000 + ", " + this.getUpperBoundsOnCoefficients().numCols() + ").";
         });
      }

      if (this.isSet(this.lowerBoundsOnIntercepts())) {
         .MODULE$.require(((Vector)this.$(this.lowerBoundsOnIntercepts())).size() == numCoefficientSets, () -> "The size of lowerBoundsOnIntercepts must be equal to 1 for binomial regression, or the number of classes for multinomial regression, but found: " + this.getLowerBoundsOnIntercepts().size() + ".");
      }

      if (this.isSet(this.upperBoundsOnIntercepts())) {
         .MODULE$.require(((Vector)this.$(this.upperBoundsOnIntercepts())).size() == numCoefficientSets, () -> "The size of upperBoundsOnIntercepts must be equal to 1 for binomial regression, or the number of classes for multinomial regression, but found: " + this.getUpperBoundsOnIntercepts().size() + ".");
      }

      if (this.isSet(this.lowerBoundsOnCoefficients()) && this.isSet(this.upperBoundsOnCoefficients())) {
         .MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.doubleArrayOps(((Matrix)this.$(this.lowerBoundsOnCoefficients())).toArray()), .MODULE$.wrapDoubleArray(((Matrix)this.$(this.upperBoundsOnCoefficients())).toArray()))), (x) -> BoxesRunTime.boxToBoolean($anonfun$assertBoundConstrainedOptimizationParamsValid$5(x))), () -> {
            Matrix var10000 = this.getLowerBoundsOnCoefficients();
            return "LowerBoundsOnCoefficients should always be less than or equal to upperBoundsOnCoefficients, but found: lowerBoundsOnCoefficients = " + var10000 + ", upperBoundsOnCoefficients = " + this.getUpperBoundsOnCoefficients() + ".";
         });
      }

      if (this.isSet(this.lowerBoundsOnIntercepts()) && this.isSet(this.upperBoundsOnIntercepts())) {
         .MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(.MODULE$.doubleArrayOps(((Vector)this.$(this.lowerBoundsOnIntercepts())).toArray()), .MODULE$.wrapDoubleArray(((Vector)this.$(this.upperBoundsOnIntercepts())).toArray()))), (x) -> BoxesRunTime.boxToBoolean($anonfun$assertBoundConstrainedOptimizationParamsValid$7(x))), () -> {
            Vector var10000 = this.getLowerBoundsOnIntercepts();
            return "LowerBoundsOnIntercepts should always be less than or equal to upperBoundsOnIntercepts, but found: lowerBoundsOnIntercepts = " + var10000 + ", upperBoundsOnIntercepts = " + this.getUpperBoundsOnIntercepts() + ".";
         });
      }
   }

   private Option optInitialModel() {
      return this.optInitialModel;
   }

   private void optInitialModel_$eq(final Option x$1) {
      this.optInitialModel = x$1;
   }

   public LogisticRegression setInitialModel(final LogisticRegressionModel model) {
      this.optInitialModel_$eq(new Some(model));
      return this;
   }

   public LogisticRegressionModel train(final Dataset dataset) {
      Object var2 = new Object();

      LogisticRegressionModel var10000;
      try {
         var10000 = (LogisticRegressionModel)Instrumentation$.MODULE$.instrumented((instr) -> {
            label163: {
               instr.logPipelineStage(this);
               instr.logDataset(dataset);
               instr.logParams(this, scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Param[]{this.labelCol(), this.weightCol(), this.featuresCol(), this.predictionCol(), this.rawPredictionCol(), this.probabilityCol(), this.regParam(), this.elasticNetParam(), this.standardization(), this.threshold(), this.thresholds(), this.maxIter(), this.tol(), this.fitIntercept(), this.maxBlockSizeInMB()}));
               StorageLevel var10000 = dataset.storageLevel();
               StorageLevel var8 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
               if (var10000 == null) {
                  if (var8 == null) {
                     break label163;
                  }
               } else if (var10000.equals(var8)) {
                  break label163;
               }

               instr.logWarning((Function0)(() -> "Input instances will be standardized, blockified to blocks, and then cached during training. Be careful of double caching!"));
            }

            RDD instances = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{DatasetUtils$.MODULE$.checkClassificationLabels((String)this.$(this.labelCol()), scala.None..MODULE$), DatasetUtils$.MODULE$.checkNonNegativeWeights(this.get(this.weightCol())), DatasetUtils$.MODULE$.checkNonNanVectors((String)this.$(this.featuresCol()))}))).rdd().map((x0$1) -> {
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
            Tuple2 var11 = Summarizer$.MODULE$.getClassificationSummarizers(instances, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), new scala.collection.immutable..colon.colon("mean", new scala.collection.immutable..colon.colon("std", new scala.collection.immutable..colon.colon("count", scala.collection.immutable.Nil..MODULE$))));
            if (var11 != null) {
               SummarizerBuffer summarizerx;
               MultiClassSummarizer labelSummarizerx;
               int numFeatures;
               double[] histogram;
               long numInvalid;
               int numFeaturesPlusIntercept;
               int var63;
               label167: {
                  SummarizerBuffer summarizer = (SummarizerBuffer)var11._1();
                  MultiClassSummarizer labelSummarizer = (MultiClassSummarizer)var11._2();
                  Tuple2 var10 = new Tuple2(summarizer, labelSummarizer);
                  summarizerx = (SummarizerBuffer)var10._1();
                  labelSummarizerx = (MultiClassSummarizer)var10._2();
                  numFeatures = summarizerx.mean().size();
                  histogram = labelSummarizerx.histogram();
                  numInvalid = labelSummarizerx.countInvalid();
                  numFeaturesPlusIntercept = this.getFitIntercept() ? numFeatures + 1 : numFeatures;
                  Option var22 = MetadataUtils$.MODULE$.getNumClasses(dataset.schema().apply((String)this.$(this.labelCol())));
                  if (var22 instanceof Some) {
                     Some var23 = (Some)var22;
                     int n = BoxesRunTime.unboxToInt(var23.value());
                     if (true) {
                        .MODULE$.require(n >= histogram.length, () -> "Specified number of classes " + n + " was less than the number of unique labels " + histogram.length + ".");
                        var63 = n;
                        break label167;
                     }
                  }

                  if (!scala.None..MODULE$.equals(var22)) {
                     throw new MatchError(var22);
                  }

                  var63 = histogram.length;
               }

               int numClasses = var63;
               if (numInvalid != 0L) {
                  MessageWithContext msg = this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Classification labels should be in "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RANGE..MODULE$, "[0 to " + (numClasses - 1) + "]")})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Found ", " invalid labels."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COUNT..MODULE$, BoxesRunTime.boxToLong(numInvalid))}))));
                  instr.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> msg));
                  throw new SparkException(msg.message());
               } else {
                  instr.logNumClasses((long)numClasses);
                  instr.logNumFeatures((long)numFeatures);
                  instr.logNumExamples(summarizerx.count());
                  instr.logNamedValue("lowestLabelWeight", .MODULE$.wrapDoubleArray(labelSummarizerx.histogram()).min(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).toString());
                  instr.logNamedValue("highestLabelWeight", .MODULE$.wrapDoubleArray(labelSummarizerx.histogram()).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$).toString());
                  instr.logSumOfWeights(summarizerx.weightSum());
                  double actualBlockSizeInMB = BoxesRunTime.unboxToDouble(this.$(this.maxBlockSizeInMB()));
                  if (actualBlockSizeInMB == (double)0) {
                     actualBlockSizeInMB = InstanceBlock$.MODULE$.DefaultBlockSizeInMB();
                     .MODULE$.require(actualBlockSizeInMB > (double)0, () -> "inferred actual BlockSizeInMB must > 0");
                     instr.logNamedValue("actualBlockSizeInMB", Double.toString(actualBlockSizeInMB));
                  }

                  boolean isMultinomial = this.checkMultinomial(numClasses);
                  int numCoefficientSets = isMultinomial ? numClasses : 1;
                  if (this.usingBoundConstrainedOptimization()) {
                     this.assertBoundConstrainedOptimizationParamsValid(numCoefficientSets, numFeatures);
                  }

                  if (this.isDefined(this.thresholds())) {
                     .MODULE$.require(((double[])this.$(this.thresholds())).length == numClasses, () -> this.getClass().getSimpleName() + ".train() called with non-matching numClasses and thresholds.length. numClasses=" + numClasses + ", but thresholds has length " + ((double[])this.$(this.thresholds())).length);
                  }

                  boolean isConstantLabel = scala.collection.ArrayOps..MODULE$.count$extension(.MODULE$.doubleArrayOps(histogram), (JFunction1.mcZD.sp)(x$2) -> x$2 != (double)0.0F) == 1;
                  if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && isConstantLabel && !this.usingBoundConstrainedOptimization()) {
                     instr.logWarning((Function0)(() -> "All labels are the same value and fitIntercept=true, so the coefficients will be zeros. Training is not needed."));
                     int constantLabelIndex = org.apache.spark.ml.linalg.Vectors..MODULE$.dense(histogram).argmax();
                     Matrix coefMatrix = (new SparseMatrix(numCoefficientSets, numFeatures, new int[numCoefficientSets + 1], scala.Array..MODULE$.emptyIntArray(), scala.Array..MODULE$.emptyDoubleArray(), true)).compressed();
                     Vector interceptVec = isMultinomial ? org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(numClasses, new scala.collection.immutable..colon.colon(new Tuple2.mcID.sp(constantLabelIndex, Double.POSITIVE_INFINITY), scala.collection.immutable.Nil..MODULE$)) : org.apache.spark.ml.linalg.Vectors..MODULE$.dense(numClasses == 2 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY, scala.collection.immutable.Nil..MODULE$);
                     throw new NonLocalReturnControl(var2, this.createModel(dataset, numClasses, coefMatrix, interceptVec, new double[]{(double)0.0F}));
                  } else {
                     if (!BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && isConstantLabel) {
                        instr.logWarning((Function0)(() -> "All labels belong to a single class and fitIntercept=false. It's a dangerous ground, so the algorithm may not converge."));
                     }

                     double[] featuresMean = summarizerx.mean().toArray();
                     double[] featuresStd = summarizerx.std().toArray();
                     if (!BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), numFeatures).exists((JFunction1.mcZI.sp)(i) -> featuresStd[i] == (double)0.0F && featuresMean[i] != (double)0.0F)) {
                        instr.logWarning((Function0)(() -> "Fitting LogisticRegressionModel without intercept on dataset with constant nonzero column, Spark MLlib outputs zero coefficients for constant nonzero columns. This behavior is the same as R glmnet but different from LIBSVM."));
                     }

                     double regParamL2 = ((double)1.0F - BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam()))) * BoxesRunTime.unboxToDouble(this.$(this.regParam()));
                     Object var64;
                     if (regParamL2 != (double)0.0F) {
                        Function1 getFeaturesStd = (j) -> j >= 0 && j < numCoefficientSets * numFeatures ? featuresStd[j / numCoefficientSets] : (double)0.0F;
                        Function1 shouldApply = (idx) -> idx >= 0 && idx < numFeatures * numCoefficientSets;
                        var64 = new Some(new L2Regularization(regParamL2, shouldApply, (Option)(BoxesRunTime.unboxToBoolean(this.$(this.standardization())) ? scala.None..MODULE$ : new Some(getFeaturesStd))));
                     } else {
                        var64 = scala.None..MODULE$;
                     }

                     Option regularization = (Option)var64;
                     Tuple2 var43 = this.createBounds(numClasses, numFeatures, featuresStd);
                     if (var43 == null) {
                        throw new MatchError(var43);
                     } else {
                        double[] lowerBounds = (double[])var43._1();
                        double[] upperBounds = (double[])var43._2();
                        Tuple2 var42 = new Tuple2(lowerBounds, upperBounds);
                        double[] lowerBoundsx = (double[])var42._1();
                        double[] upperBoundsx = (double[])var42._2();
                        FirstOrderMinimizer optimizer = this.createOptimizer(numClasses, numFeatures, featuresStd, lowerBoundsx, upperBoundsx);
                        DenseMatrix initialSolution = this.createInitialSolution(numClasses, numFeatures, histogram, featuresStd, lowerBoundsx, upperBoundsx, instr);
                        Tuple2 var51 = this.trainImpl(instances, actualBlockSizeInMB, featuresStd, featuresMean, numClasses, initialSolution.toArray(), regularization, optimizer);
                        if (var51 != null) {
                           double[] allCoefficients = (double[])var51._1();
                           double[] objectiveHistory = (double[])var51._2();
                           Tuple2 var50 = new Tuple2(allCoefficients, objectiveHistory);
                           double[] allCoefficientsx = (double[])var50._1();
                           double[] objectiveHistoryx = (double[])var50._2();
                           if (allCoefficientsx == null) {
                              MLUtils$.MODULE$.optimizerFailed(instr, optimizer.getClass());
                           }

                           DenseMatrix allCoefMatrix = new DenseMatrix(numCoefficientSets, numFeaturesPlusIntercept, allCoefficientsx);
                           DenseMatrix denseCoefficientMatrix = new DenseMatrix(numCoefficientSets, numFeatures, new double[numCoefficientSets * numFeatures], true);
                           Vector interceptVec = !BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && isMultinomial ? org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(numCoefficientSets, (Seq)scala.package..MODULE$.Seq().empty()) : org.apache.spark.ml.linalg.Vectors..MODULE$.zeros(numCoefficientSets);
                           allCoefMatrix.foreachActive((classIndex, featureIndex, value) -> {
                              $anonfun$train$15(this, numFeatures, featuresStd, denseCoefficientMatrix, interceptVec, BoxesRunTime.unboxToInt(classIndex), BoxesRunTime.unboxToInt(featureIndex), BoxesRunTime.unboxToDouble(value));
                              return BoxedUnit.UNIT;
                           });
                           if (BoxesRunTime.unboxToDouble(this.$(this.regParam())) == (double)0.0F && isMultinomial && !this.usingBoundConstrainedOptimization()) {
                              double[] centers = (double[])scala.Array..MODULE$.ofDim(numFeatures, scala.reflect.ClassTag..MODULE$.Double());
                              denseCoefficientMatrix.foreachActive((x0$2, x1$1, x2$1) -> {
                                 $anonfun$train$16(centers, BoxesRunTime.unboxToInt(x0$2), BoxesRunTime.unboxToInt(x1$1), BoxesRunTime.unboxToDouble(x2$1));
                                 return BoxedUnit.UNIT;
                              });
                              scala.collection.ArrayOps..MODULE$.mapInPlace$extension(.MODULE$.doubleArrayOps(centers), (JFunction1.mcDD.sp)(x$5) -> x$5 / (double)numCoefficientSets);
                              denseCoefficientMatrix.foreachActive((x0$3, x1$2, x2$2) -> {
                                 $anonfun$train$18(denseCoefficientMatrix, centers, BoxesRunTime.unboxToInt(x0$3), BoxesRunTime.unboxToInt(x1$2), BoxesRunTime.unboxToDouble(x2$2));
                                 return BoxedUnit.UNIT;
                              });
                           }

                           if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && isMultinomial && !this.usingBoundConstrainedOptimization()) {
                              double[] interceptArray = interceptVec.toArray();
                              double interceptMean = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(interceptArray).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)interceptArray.length;
                              scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), interceptVec.size()).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> interceptArray[i] -= interceptMean);
                           }

                           throw new NonLocalReturnControl(var2, this.createModel(dataset, numClasses, denseCoefficientMatrix.compressed(), interceptVec.compressed(), objectiveHistoryx));
                        } else {
                           throw new MatchError(var51);
                        }
                     }
                  }
               }
            } else {
               throw new MatchError(var11);
            }
         });
      } catch (NonLocalReturnControl var4) {
         if (var4.key() != var2) {
            throw var4;
         }

         var10000 = (LogisticRegressionModel)var4.value();
      }

      return var10000;
   }

   private boolean checkMultinomial(final int numClasses) {
      String var3 = ((String)this.$(this.family())).toLowerCase(Locale.ROOT);
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 3005871:
            if ("auto".equals(var3)) {
               return numClasses > 2;
            }
            break;
         case 508210817:
            if ("multinomial".equals(var3)) {
               return true;
            }
            break;
         case 950395663:
            if ("binomial".equals(var3)) {
               .MODULE$.require(numClasses == 1 || numClasses == 2, () -> "Binomial family only supports 1 or 2 outcome classes but found " + numClasses + ".");
               return false;
            }
      }

      throw new IllegalArgumentException("Unsupported family: " + var3);
   }

   private LogisticRegressionModel createModel(final Dataset dataset, final int numClasses, final Matrix coefficientMatrix, final Vector interceptVector, final double[] objectiveHistory) {
      LogisticRegressionModel model = (LogisticRegressionModel)this.copyValues(new LogisticRegressionModel(this.uid(), coefficientMatrix, interceptVector, numClasses, this.checkMultinomial(numClasses)), this.copyValues$default$2());
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var10 = model.findSummaryModel();
      if (var10 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var10._1();
         String probabilityColName = (String)var10._2();
         String predictionColName = (String)var10._3();
         Tuple3 var9 = new Tuple3(summaryModel, probabilityColName, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var9._1();
         String probabilityColName = (String)var9._2();
         String predictionColName = (String)var9._3();
         LogisticRegressionSummaryImpl logRegSummary = (LogisticRegressionSummaryImpl)(numClasses <= 2 ? new BinaryLogisticRegressionTrainingSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), weightColName, objectiveHistory) : new LogisticRegressionTrainingSummaryImpl(summaryModel.transform(dataset), probabilityColName, predictionColName, (String)this.$(this.labelCol()), (String)this.$(this.featuresCol()), weightColName, objectiveHistory));
         return (LogisticRegressionModel)model.setSummary(new Some(logRegSummary));
      } else {
         throw new MatchError(var10);
      }
   }

   private Tuple2 createBounds(final int numClasses, final int numFeatures, final double[] featuresStd) {
      boolean isMultinomial = this.checkMultinomial(numClasses);
      int numFeaturesPlusIntercept = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? numFeatures + 1 : numFeatures;
      int numCoefficientSets = isMultinomial ? numClasses : 1;
      int numCoeffsPlusIntercepts = numFeaturesPlusIntercept * numCoefficientSets;
      if (this.usingBoundConstrainedOptimization()) {
         double[] lowerBounds = (double[])scala.Array..MODULE$.fill(numCoeffsPlusIntercepts, (JFunction0.mcD.sp)() -> Double.NEGATIVE_INFINITY, scala.reflect.ClassTag..MODULE$.Double());
         double[] upperBounds = (double[])scala.Array..MODULE$.fill(numCoeffsPlusIntercepts, (JFunction0.mcD.sp)() -> Double.POSITIVE_INFINITY, scala.reflect.ClassTag..MODULE$.Double());
         boolean isSetLowerBoundsOnCoefficients = this.isSet(this.lowerBoundsOnCoefficients());
         boolean isSetUpperBoundsOnCoefficients = this.isSet(this.upperBoundsOnCoefficients());
         boolean isSetLowerBoundsOnIntercepts = this.isSet(this.lowerBoundsOnIntercepts());
         boolean isSetUpperBoundsOnIntercepts = this.isSet(this.upperBoundsOnIntercepts());

         for(int i = 0; i < numCoeffsPlusIntercepts; ++i) {
            int coefficientSetIndex = i % numCoefficientSets;
            int featureIndex = i / numCoefficientSets;
            if (featureIndex < numFeatures) {
               if (isSetLowerBoundsOnCoefficients) {
                  lowerBounds[i] = ((Matrix)this.$(this.lowerBoundsOnCoefficients())).apply(coefficientSetIndex, featureIndex) * featuresStd[featureIndex];
               }

               if (isSetUpperBoundsOnCoefficients) {
                  upperBounds[i] = ((Matrix)this.$(this.upperBoundsOnCoefficients())).apply(coefficientSetIndex, featureIndex) * featuresStd[featureIndex];
               }
            } else {
               if (isSetLowerBoundsOnIntercepts) {
                  lowerBounds[i] = ((Vector)this.$(this.lowerBoundsOnIntercepts())).apply(coefficientSetIndex);
               }

               if (isSetUpperBoundsOnIntercepts) {
                  upperBounds[i] = ((Vector)this.$(this.upperBoundsOnIntercepts())).apply(coefficientSetIndex);
               }
            }
         }

         return new Tuple2(lowerBounds, upperBounds);
      } else {
         return new Tuple2((Object)null, (Object)null);
      }
   }

   private FirstOrderMinimizer createOptimizer(final int numClasses, final int numFeatures, final double[] featuresStd, final double[] lowerBounds, final double[] upperBounds) {
      boolean isMultinomial = this.checkMultinomial(numClasses);
      double regParamL1 = BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())) * BoxesRunTime.unboxToDouble(this.$(this.regParam()));
      int numCoefficientSets = isMultinomial ? numClasses : 1;
      if (BoxesRunTime.unboxToDouble(this.$(this.elasticNetParam())) != (double)0.0F && BoxesRunTime.unboxToDouble(this.$(this.regParam())) != (double)0.0F) {
         boolean standardizationParam = BoxesRunTime.unboxToBoolean(this.$(this.standardization()));
         return new OWLQN(BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, this.regParamL1Fun$1(numFeatures, numCoefficientSets, standardizationParam, regParamL1, featuresStd), BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.linalg.DenseVector..MODULE$.space_Double());
      } else {
         return (FirstOrderMinimizer)(lowerBounds != null && upperBounds != null ? new LBFGSB(breeze.linalg.DenseVector..MODULE$.apply(lowerBounds), breeze.linalg.DenseVector..MODULE$.apply(upperBounds), BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.optimize.LBFGSB..MODULE$.$lessinit$greater$default$6(), breeze.optimize.LBFGSB..MODULE$.$lessinit$greater$default$7()) : new LBFGS(BoxesRunTime.unboxToInt(this.$(this.maxIter())), 10, BoxesRunTime.unboxToDouble(this.$(this.tol())), breeze.linalg.DenseVector..MODULE$.space_Double()));
      }
   }

   private DenseMatrix createInitialSolution(final int numClasses, final int numFeatures, final double[] histogram, final double[] featuresStd, final double[] lowerBounds, final double[] upperBounds, final Instrumentation instr) {
      boolean isMultinomial = this.checkMultinomial(numClasses);
      int numFeaturesPlusIntercept = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) ? numFeatures + 1 : numFeatures;
      int numCoefficientSets = isMultinomial ? numClasses : 1;
      int numCoeffsPlusIntercepts = numFeaturesPlusIntercept * numCoefficientSets;
      DenseMatrix initialCoefWithInterceptMatrix = org.apache.spark.ml.linalg.DenseMatrix..MODULE$.zeros(numCoefficientSets, numFeaturesPlusIntercept);
      Option var15 = this.optInitialModel();
      boolean var10000;
      if (var15 instanceof Some var16) {
         LogisticRegressionModel _initialModel = (LogisticRegressionModel)var16.value();
         Matrix providedCoefs = _initialModel.coefficientMatrix();
         boolean modelIsValid = providedCoefs.numRows() == numCoefficientSets && providedCoefs.numCols() == numFeatures && _initialModel.interceptVector().size() == numCoefficientSets && _initialModel.getFitIntercept() == BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()));
         if (!modelIsValid) {
            instr.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initial coefficients will be ignored! Its dimensions "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "}, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_ROWS..MODULE$, BoxesRunTime.boxToInteger(providedCoefs.numRows()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ") did not match the "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_COLUMNS..MODULE$, BoxesRunTime.boxToInteger(providedCoefs.numCols()))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"expected size (", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_COEFFICIENTS..MODULE$, BoxesRunTime.boxToInteger(numCoefficientSets))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ")"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FEATURES..MODULE$, BoxesRunTime.boxToInteger(numFeatures))}))))));
         }

         var10000 = modelIsValid;
      } else {
         if (!scala.None..MODULE$.equals(var15)) {
            throw new MatchError(var15);
         }

         var10000 = false;
      }

      boolean initialModelIsValid = var10000;
      if (initialModelIsValid) {
         Matrix providedCoef = ((LogisticRegressionModel)this.optInitialModel().get()).coefficientMatrix();
         providedCoef.foreachActive((classIndex, featureIndexx, value) -> {
            $anonfun$createInitialSolution$2(initialCoefWithInterceptMatrix, featuresStd, BoxesRunTime.unboxToInt(classIndex), BoxesRunTime.unboxToInt(featureIndexx), BoxesRunTime.unboxToDouble(value));
            return BoxedUnit.UNIT;
         });
         if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
            ((LogisticRegressionModel)this.optInitialModel().get()).interceptVector().foreachNonZero((JFunction2.mcVID.sp)(classIndex, value) -> initialCoefWithInterceptMatrix.update(classIndex, numFeatures, value));
         }
      } else if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && isMultinomial) {
         double[] rawIntercepts = (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(histogram), (JFunction1.mcDD.sp)(x) -> scala.math.package..MODULE$.log1p(x), scala.reflect.ClassTag..MODULE$.Double());
         double rawMean = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray(rawIntercepts).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) / (double)rawIntercepts.length;
         scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.doubleArrayOps(rawIntercepts)).foreach$mVc$sp((JFunction1.mcVI.sp)(ix) -> initialCoefWithInterceptMatrix.update(ix, numFeatures, rawIntercepts[ix] - rawMean));
      } else if (BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept()))) {
         initialCoefWithInterceptMatrix.update(0, numFeatures, scala.math.package..MODULE$.log(histogram[1] / histogram[0]));
      }

      if (this.usingBoundConstrainedOptimization()) {
         for(int i = 0; i < numCoeffsPlusIntercepts; ++i) {
            int coefficientSetIndex = i % numCoefficientSets;
            int featureIndex = i / numCoefficientSets;
            if (initialCoefWithInterceptMatrix.apply(coefficientSetIndex, featureIndex) < lowerBounds[i]) {
               initialCoefWithInterceptMatrix.update(coefficientSetIndex, featureIndex, lowerBounds[i]);
            } else if (initialCoefWithInterceptMatrix.apply(coefficientSetIndex, featureIndex) > upperBounds[i]) {
               initialCoefWithInterceptMatrix.update(coefficientSetIndex, featureIndex, upperBounds[i]);
            }
         }
      }

      return initialCoefWithInterceptMatrix;
   }

   private Tuple2 trainImpl(final RDD instances, final double actualBlockSizeInMB, final double[] featuresStd, final double[] featuresMean, final int numClasses, final double[] initialSolution, final Option regularization, final FirstOrderMinimizer optimizer) {
      boolean multinomial = this.checkMultinomial(numClasses);
      boolean fitWithMean = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && (!this.isSet(this.lowerBoundsOnIntercepts()) || scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.doubleArrayOps(((Vector)this.$(this.lowerBoundsOnIntercepts())).toArray()), (JFunction1.mcZD.sp)(x$7) -> scala.runtime.RichDouble..MODULE$.isNegInfinity$extension(.MODULE$.doubleWrapper(x$7)))) && (!this.isSet(this.upperBoundsOnIntercepts()) || scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.doubleArrayOps(((Vector)this.$(this.upperBoundsOnIntercepts())).toArray()), (JFunction1.mcZD.sp)(x$8) -> scala.runtime.RichDouble..MODULE$.isPosInfinity$extension(.MODULE$.doubleWrapper(x$8))));
      int numFeatures = featuresStd.length;
      double[] inverseStd = (double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.doubleArrayOps(featuresStd), (JFunction1.mcDD.sp)(std) -> std != (double)0 ? (double)1.0F / std : (double)0.0F, scala.reflect.ClassTag..MODULE$.Double());
      double[] scaledMean = (double[])scala.Array..MODULE$.tabulate(numFeatures, (JFunction1.mcDI.sp)(i) -> inverseStd[i] * featuresMean[i], scala.reflect.ClassTag..MODULE$.Double());
      Broadcast bcInverseStd = instances.context().broadcast(inverseStd, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      Broadcast bcScaledMean = instances.context().broadcast(scaledMean, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(Double.TYPE)));
      RDD scaled = instances.mapPartitions((iter) -> {
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
      long maxMemUsage = (long)scala.runtime.RichDouble..MODULE$.ceil$extension(.MODULE$.doubleWrapper(actualBlockSizeInMB * (double)1024L * (double)1024L));
      RDD var10000 = InstanceBlock$.MODULE$.blokifyWithMaxMemUsage(scaled, maxMemUsage).persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      String var10001 = this.uid();
      RDD blocks = var10000.setName(var10001 + ": training blocks (blockSizeInMB=" + actualBlockSizeInMB + ")");
      RDDLossFunction var34;
      if (multinomial) {
         Function1 getAggregatorFunc = (x$9) -> new MultinomialLogisticBlockAggregator(bcInverseStd, bcScaledMean, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), fitWithMean, x$9);
         var34 = new RDDLossFunction(blocks, getAggregatorFunc, regularization, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(InstanceBlock.class), scala.reflect.ClassTag..MODULE$.apply(MultinomialLogisticBlockAggregator.class));
      } else {
         Function1 getAggregatorFunc = (x$10) -> new BinaryLogisticBlockAggregator(bcInverseStd, bcScaledMean, BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())), fitWithMean, x$10);
         var34 = new RDDLossFunction(blocks, getAggregatorFunc, regularization, BoxesRunTime.unboxToInt(this.$(this.aggregationDepth())), scala.reflect.ClassTag..MODULE$.apply(InstanceBlock.class), scala.reflect.ClassTag..MODULE$.apply(BinaryLogisticBlockAggregator.class));
      }

      RDDLossFunction costFun = var34;
      if (fitWithMean) {
         if (multinomial) {
            double[] adapt = (double[])scala.Array..MODULE$.ofDim(numClasses, scala.reflect.ClassTag..MODULE$.Double());
            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().dgemv("N", numClasses, numFeatures, (double)1.0F, initialSolution, numClasses, scaledMean, 1, (double)0.0F, adapt, 1);
            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(numClasses, (double)1.0F, adapt, 0, 1, initialSolution, numClasses * numFeatures, 1);
         } else {
            double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, initialSolution, 1, scaledMean, 1);
            initialSolution[numFeatures] += adapt;
         }
      }

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
      if (fitWithMean && solution != null) {
         if (multinomial) {
            double[] adapt = (double[])scala.Array..MODULE$.ofDim(numClasses, scala.reflect.ClassTag..MODULE$.Double());
            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().dgemv("N", numClasses, numFeatures, (double)1.0F, solution, numClasses, scaledMean, 1, (double)0.0F, adapt, 1);
            org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().daxpy(numClasses, (double)-1.0F, adapt, 0, 1, solution, numClasses * numFeatures, 1);
         } else {
            double adapt = org.apache.spark.ml.linalg.BLAS..MODULE$.javaBLAS().ddot(numFeatures, solution, 1, scaledMean, 1);
            solution[numFeatures] -= adapt;
         }
      }

      return new Tuple2(solution, arrayBuilder.result());
   }

   public LogisticRegression copy(final ParamMap extra) {
      return (LogisticRegression)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertBoundConstrainedOptimizationParamsValid$5(final Tuple2 x) {
      return x._1$mcD$sp() <= x._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$assertBoundConstrainedOptimizationParamsValid$7(final Tuple2 x) {
      return x._1$mcD$sp() <= x._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final void $anonfun$train$15(final LogisticRegression $this, final int numFeatures$1, final double[] featuresStd$1, final DenseMatrix denseCoefficientMatrix$1, final Vector interceptVec$1, final int classIndex, final int featureIndex, final double value) {
      boolean isIntercept = BoxesRunTime.unboxToBoolean($this.$($this.fitIntercept())) && featureIndex == numFeatures$1;
      if (!isIntercept && featuresStd$1[featureIndex] != (double)0.0F) {
         denseCoefficientMatrix$1.update(classIndex, featureIndex, value / featuresStd$1[featureIndex]);
      }

      if (isIntercept) {
         interceptVec$1.toArray()[classIndex] = value;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$train$16(final double[] centers$1, final int x0$2, final int x1$1, final double x2$1) {
      Tuple3 var6 = new Tuple3(BoxesRunTime.boxToInteger(x0$2), BoxesRunTime.boxToInteger(x1$1), BoxesRunTime.boxToDouble(x2$1));
      if (var6 != null) {
         int j = BoxesRunTime.unboxToInt(var6._2());
         double v = BoxesRunTime.unboxToDouble(var6._3());
         centers$1[j] += v;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$train$18(final DenseMatrix denseCoefficientMatrix$1, final double[] centers$1, final int x0$3, final int x1$2, final double x2$2) {
      Tuple3 var7 = new Tuple3(BoxesRunTime.boxToInteger(x0$3), BoxesRunTime.boxToInteger(x1$2), BoxesRunTime.boxToDouble(x2$2));
      if (var7 != null) {
         int i = BoxesRunTime.unboxToInt(var7._1());
         int j = BoxesRunTime.unboxToInt(var7._2());
         double v = BoxesRunTime.unboxToDouble(var7._3());
         denseCoefficientMatrix$1.update(i, j, v - centers$1[j]);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var7);
      }
   }

   private final Function1 regParamL1Fun$1(final int numFeatures$2, final int numCoefficientSets$2, final boolean standardizationParam$1, final double regParamL1$1, final double[] featuresStd$2) {
      return (JFunction1.mcDI.sp)(index) -> {
         boolean isIntercept = BoxesRunTime.unboxToBoolean(this.$(this.fitIntercept())) && index >= numFeatures$2 * numCoefficientSets$2;
         if (isIntercept) {
            return (double)0.0F;
         } else if (standardizationParam$1) {
            return regParamL1$1;
         } else {
            int featureIndex = index / numCoefficientSets$2;
            return featuresStd$2[featureIndex] != (double)0.0F ? regParamL1$1 / featuresStd$2[featureIndex] : (double)0.0F;
         }
      };
   }

   // $FF: synthetic method
   public static final void $anonfun$createInitialSolution$2(final DenseMatrix initialCoefWithInterceptMatrix$1, final double[] featuresStd$3, final int classIndex, final int featureIndex, final double value) {
      initialCoefWithInterceptMatrix$1.update(classIndex, featureIndex, value * featuresStd$3[featureIndex]);
   }

   public LogisticRegression(final String uid) {
      this.uid = uid;
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
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      this.optInitialModel = scala.None..MODULE$;
      Statics.releaseFence();
   }

   public LogisticRegression() {
      this(Identifiable$.MODULE$.randomUID("logreg"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
