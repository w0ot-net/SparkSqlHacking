package org.apache.spark.ml.classification;

import java.io.IOException;
import java.io.Serializable;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.regression.FactorizationMachines$;
import org.apache.spark.ml.regression.FactorizationMachinesParams;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.Identifiable;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
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
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\rma\u0001\u0002!B\u00011C\u0001b\u001a\u0001\u0003\u0006\u0004%\t\u0005\u001b\u0005\t\u007f\u0002\u0011\t\u0011)A\u0005S\"Q\u00111\u0001\u0001\u0003\u0006\u0004%\t!!\u0002\t\u0015\u0005E\u0001A!A!\u0002\u0013\t9\u0001\u0003\u0006\u0002\u0016\u0001\u0011)\u0019!C\u0001\u0003/A\u0011\"a\u0007\u0001\u0005\u0003\u0005\u000b\u0011B)\t\u0015\u0005}\u0001A!b\u0001\n\u0003\t\t\u0003\u0003\u0006\u0002,\u0001\u0011\t\u0011)A\u0005\u0003GA\u0001\"a\f\u0001\t\u0003\t\u0015\u0011\u0007\u0005\t\u0003_\u0001A\u0011A\"\u0002D!I\u0011Q\t\u0001C\u0002\u0013\u0005\u0013q\t\u0005\t\u0003#\u0002\u0001\u0015!\u0003\u0002J!I\u0011Q\u000b\u0001C\u0002\u0013\u0005\u0013q\t\u0005\t\u00033\u0002\u0001\u0015!\u0003\u0002J!9\u0011Q\f\u0001\u0005B\u0005}\u0003bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u00037\u0003A\u0011IAO\u0011\u001d\t)\u000b\u0001C)\u0003OCq!!,\u0001\t\u0003\ny\u000bC\u0004\u0002D\u0002!\t%!2\t\u000f\u0005=\u0007\u0001\"\u0011\u0002R\u001e9\u0011Q[!\t\u0002\u0005]gA\u0002!B\u0011\u0003\tI\u000eC\u0004\u00020]!\t!a>\t\u000f\u0005ex\u0003\"\u0011\u0002|\"9!QA\f\u0005B\t\u001daa\u0002B\b/\u00019\"\u0011\u0003\u0005\n\u0005?Y\"\u0011!Q\u0001\n]Cq!a\f\u001c\t\u0003\u0011\tC\u0002\u0004\u0003*m!%1\u0006\u0005\u000b\u0003\u0007q\"Q3A\u0005\u0002\u0005\u0015\u0001BCA\t=\tE\t\u0015!\u0003\u0002\b!Q\u0011Q\u0003\u0010\u0003\u0016\u0004%\t!a\u0006\t\u0013\u0005maD!E!\u0002\u0013\t\u0006BCA\u0010=\tU\r\u0011\"\u0001\u0002\"!Q\u00111\u0006\u0010\u0003\u0012\u0003\u0006I!a\t\t\u000f\u0005=b\u0004\"\u0001\u0003D!I\u0011Q\u0016\u0010\u0002\u0002\u0013\u0005!q\n\u0005\n\u0005/r\u0012\u0013!C\u0001\u00053B\u0011B!\u001c\u001f#\u0003%\tAa\u001c\t\u0013\tMd$%A\u0005\u0002\tU\u0004\"\u0003B==\u0005\u0005I\u0011\tB>\u0011%\u00119IHA\u0001\n\u0003\t9\u0005C\u0005\u0003\nz\t\t\u0011\"\u0001\u0003\f\"I!\u0011\u0013\u0010\u0002\u0002\u0013\u0005#1\u0013\u0005\n\u0005Cs\u0012\u0011!C\u0001\u0005GC\u0011B!,\u001f\u0003\u0003%\tEa,\t\u0013\tMf$!A\u0005B\tU\u0006\"CAh=\u0005\u0005I\u0011\tB\\\u0011%\u0011ILHA\u0001\n\u0003\u0012YlB\u0005\u0003@n\t\t\u0011#\u0003\u0003B\u001aI!\u0011F\u000e\u0002\u0002#%!1\u0019\u0005\b\u0003_!D\u0011\u0001Bi\u0011%\ty\rNA\u0001\n\u000b\u00129\fC\u0005\u0003TR\n\t\u0011\"!\u0003V\"I!Q\u001c\u001b\u0002\u0002\u0013\u0005%q\u001c\u0005\b\u0005c\\B\u0011\u000bBz\r\u0019\u0011ip\u0006\u0003\u0003\u0000\"9\u0011q\u0006\u001e\u0005\u0002\r\u0005\u0001\"CB\u0003u\t\u0007I\u0011\u0002B>\u0011!\u00199A\u000fQ\u0001\n\tu\u0004b\u0002B\u0003u\u0011\u00053\u0011\u0002\u0005\n\u0007\u001b9\u0012\u0011!C\u0005\u0007\u001f\u0011QCR'DY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8N_\u0012,GN\u0003\u0002C\u0007\u0006q1\r\\1tg&4\u0017nY1uS>t'B\u0001#F\u0003\tiGN\u0003\u0002G\u000f\u0006)1\u000f]1sW*\u0011\u0001*S\u0001\u0007CB\f7\r[3\u000b\u0003)\u000b1a\u001c:h\u0007\u0001\u0019R\u0001A'Y7\u0006\u0004BAT(R/6\t\u0011)\u0003\u0002Q\u0003\n\u0001\u0003K]8cC\nLG.[:uS\u000e\u001cE.Y:tS\u001aL7-\u0019;j_:lu\u000eZ3m!\t\u0011V+D\u0001T\u0015\t!6)\u0001\u0004mS:\fGnZ\u0005\u0003-N\u0013aAV3di>\u0014\bC\u0001(\u0001!\tq\u0015,\u0003\u0002[\u0003\n\u0011b)T\"mCN\u001c\u0018NZ5feB\u000b'/Y7t!\tav,D\u0001^\u0015\tq6)\u0001\u0003vi&d\u0017B\u00011^\u0005)iEj\u0016:ji\u0006\u0014G.\u001a\t\u00049\n$\u0017BA2^\u0005IA\u0015m\u001d+sC&t\u0017N\\4Tk6l\u0017M]=\u0011\u00059+\u0017B\u00014B\u0005}1Uj\u00117bgNLg-[2bi&|g\u000e\u0016:bS:LgnZ*v[6\f'/_\u0001\u0004k&$W#A5\u0011\u0005)\u001chBA6r!\taw.D\u0001n\u0015\tq7*\u0001\u0004=e>|GO\u0010\u0006\u0002a\u0006)1oY1mC&\u0011!o\\\u0001\u0007!J,G-\u001a4\n\u0005Q,(AB*ue&twM\u0003\u0002s_\"\u001a\u0011a^?\u0011\u0005a\\X\"A=\u000b\u0005i,\u0015AC1o]>$\u0018\r^5p]&\u0011A0\u001f\u0002\u0006'&t7-Z\u0011\u0002}\u0006)1G\f\u0019/a\u0005!Q/\u001b3!Q\r\u0011q/`\u0001\nS:$XM]2faR,\"!a\u0002\u0011\t\u0005%\u00111B\u0007\u0002_&\u0019\u0011QB8\u0003\r\u0011{WO\u00197fQ\r\u0019q/`\u0001\u000bS:$XM]2faR\u0004\u0003f\u0001\u0003x{\u00061A.\u001b8fCJ,\u0012!\u0015\u0015\u0004\u000b]l\u0018a\u00027j]\u0016\f'\u000f\t\u0015\u0004\r]l\u0018a\u00024bGR|'o]\u000b\u0003\u0003G\u00012AUA\u0013\u0013\r\t9c\u0015\u0002\u0007\u001b\u0006$(/\u001b=)\u0007\u001d9X0\u0001\u0005gC\u000e$xN]:!Q\rAq/`\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0013]\u000b\u0019$a\u000e\u0002<\u0005}\u0002\"B4\n\u0001\u0004I\u0007\u0006BA\u001aovDq!a\u0001\n\u0001\u0004\t9\u0001\u000b\u0003\u00028]l\bBBA\u000b\u0013\u0001\u0007\u0011\u000b\u000b\u0003\u0002<]l\bbBA\u0010\u0013\u0001\u0007\u00111\u0005\u0015\u0005\u0003\u007f9X\u0010F\u0001X\u0003)qW/\\\"mCN\u001cXm]\u000b\u0003\u0003\u0013\u0002B!!\u0003\u0002L%\u0019\u0011QJ8\u0003\u0007%sG\u000fK\u0002\fov\f1B\\;n\u00072\f7o]3tA!\u001aAb^?\u0002\u00179,XNR3biV\u0014Xm\u001d\u0015\u0004\u001b]l\u0018\u0001\u00048v[\u001a+\u0017\r^;sKN\u0004\u0003f\u0001\bx{\u000691/^7nCJLX#\u00013)\t=9\u00181M\u0011\u0003\u0003K\nQa\r\u00182]A\n\u0001\"\u001a<bYV\fG/\u001a\u000b\u0005\u0003W\n\t\bE\u0002O\u0003[J1!a\u001cB\u0005]1Uj\u00117bgNLg-[2bi&|gnU;n[\u0006\u0014\u0018\u0010C\u0004\u0002tA\u0001\r!!\u001e\u0002\u000f\u0011\fG/Y:fiB\"\u0011qOAD!\u0019\tI(a \u0002\u00046\u0011\u00111\u0010\u0006\u0004\u0003{*\u0015aA:rY&!\u0011\u0011QA>\u0005\u001d!\u0015\r^1tKR\u0004B!!\"\u0002\b2\u0001A\u0001DAE\u0003c\n\t\u0011!A\u0003\u0002\u0005-%aA0%gE!\u0011QRAJ!\u0011\tI!a$\n\u0007\u0005EuNA\u0004O_RD\u0017N\\4\u0011\t\u0005%\u0011QS\u0005\u0004\u0003/{'aA!os\"\"\u0001c^A2\u0003)\u0001(/\u001a3jGR\u0014\u0016m\u001e\u000b\u0004#\u0006}\u0005BBAQ#\u0001\u0007\u0011+\u0001\u0005gK\u0006$XO]3tQ\r\tr/`\u0001\u0017e\u0006<(\u0007\u001d:pE\u0006\u0014\u0017\u000e\\5us&s\u0007\u000b\\1dKR\u0019\u0011+!+\t\r\u0005-&\u00031\u0001R\u00035\u0011\u0018m\u001e)sK\u0012L7\r^5p]\u0006!1m\u001c9z)\r9\u0016\u0011\u0017\u0005\b\u0003g\u001b\u0002\u0019AA[\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t9,!0\u000e\u0005\u0005e&bAA^\u0007\u0006)\u0001/\u0019:b[&!\u0011qXA]\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bfA\nx{\u0006)qO]5uKV\u0011\u0011q\u0019\t\u00049\u0006%\u0017bAAf;\nAQ\nT,sSR,'\u000fK\u0002\u0015ov\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002S\"\u001a\u0001a^?\u0002+\u0019k5\t\\1tg&4\u0017nY1uS>tWj\u001c3fYB\u0011ajF\n\b/\u0005m\u0017\u0011]At!\u0011\tI!!8\n\u0007\u0005}wN\u0001\u0004B]f\u0014VM\u001a\t\u00059\u0006\rx+C\u0002\u0002fv\u0013!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\tI/a=\u000e\u0005\u0005-(\u0002BAw\u0003_\f!![8\u000b\u0005\u0005E\u0018\u0001\u00026bm\u0006LA!!>\u0002l\na1+\u001a:jC2L'0\u00192mKR\u0011\u0011q[\u0001\u0005e\u0016\fG-\u0006\u0002\u0002~B!A,a@X\u0013\r\u0011\t!\u0018\u0002\t\u001b2\u0013V-\u00193fe\"\u001a\u0011d^?\u0002\t1|\u0017\r\u001a\u000b\u0004/\n%\u0001B\u0002B\u00065\u0001\u0007\u0011.\u0001\u0003qCRD\u0007f\u0001\u000ex{\nYb)T\"mCN\u001c\u0018NZ5dCRLwN\\'pI\u0016dwK]5uKJ\u001cRaGAd\u0005'\u0001BA!\u0006\u0003\u001c5\u0011!q\u0003\u0006\u0004\u00053)\u0015\u0001C5oi\u0016\u0014h.\u00197\n\t\tu!q\u0003\u0002\b\u0019><w-\u001b8h\u0003!Ign\u001d;b]\u000e,G\u0003\u0002B\u0012\u0005O\u00012A!\n\u001c\u001b\u00059\u0002B\u0002B\u0010;\u0001\u0007qK\u0001\u0003ECR\f7c\u0002\u0010\u0002\\\n5\"1\u0007\t\u0005\u0003\u0013\u0011y#C\u0002\u00032=\u0014q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u00036\t}b\u0002\u0002B\u001c\u0005wq1\u0001\u001cB\u001d\u0013\u0005\u0001\u0018b\u0001B\u001f_\u00069\u0001/Y2lC\u001e,\u0017\u0002BA{\u0005\u0003R1A!\u0010p)!\u0011)E!\u0013\u0003L\t5\u0003c\u0001B$=5\t1\u0004C\u0004\u0002\u0004\u0015\u0002\r!a\u0002\t\r\u0005UQ\u00051\u0001R\u0011\u001d\ty\"\na\u0001\u0003G!\u0002B!\u0012\u0003R\tM#Q\u000b\u0005\n\u0003\u00071\u0003\u0013!a\u0001\u0003\u000fA\u0001\"!\u0006'!\u0003\u0005\r!\u0015\u0005\n\u0003?1\u0003\u0013!a\u0001\u0003G\tabY8qs\u0012\"WMZ1vYR$\u0013'\u0006\u0002\u0003\\)\"\u0011q\u0001B/W\t\u0011y\u0006\u0005\u0003\u0003b\t%TB\u0001B2\u0015\u0011\u0011)Ga\u001a\u0002\u0013Ut7\r[3dW\u0016$'B\u0001>p\u0013\u0011\u0011YGa\u0019\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\tE$fA)\u0003^\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTC\u0001B<U\u0011\t\u0019C!\u0018\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\u0011i\b\u0005\u0003\u0003\u0000\t\u0015UB\u0001BA\u0015\u0011\u0011\u0019)a<\u0002\t1\fgnZ\u0005\u0004i\n\u0005\u0015\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0003'\u0013i\tC\u0005\u0003\u00102\n\t\u00111\u0001\u0002J\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!&\u0011\r\t]%QTAJ\u001b\t\u0011IJC\u0002\u0003\u001c>\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011yJ!'\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005K\u0013Y\u000b\u0005\u0003\u0002\n\t\u001d\u0016b\u0001BU_\n9!i\\8mK\u0006t\u0007\"\u0003BH]\u0005\u0005\t\u0019AAJ\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\tu$\u0011\u0017\u0005\n\u0005\u001f{\u0013\u0011!a\u0001\u0003\u0013\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0013\"\"A! \u0002\r\u0015\fX/\u00197t)\u0011\u0011)K!0\t\u0013\t=%'!AA\u0002\u0005M\u0015\u0001\u0002#bi\u0006\u00042Aa\u00125'\u0015!$QYAt!-\u00119M!4\u0002\bE\u000b\u0019C!\u0012\u000e\u0005\t%'b\u0001Bf_\u00069!/\u001e8uS6,\u0017\u0002\u0002Bh\u0005\u0013\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84)\t\u0011\t-A\u0003baBd\u0017\u0010\u0006\u0005\u0003F\t]'\u0011\u001cBn\u0011\u001d\t\u0019a\u000ea\u0001\u0003\u000fAa!!\u00068\u0001\u0004\t\u0006bBA\u0010o\u0001\u0007\u00111E\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0011\tO!<\u0011\r\u0005%!1\u001dBt\u0013\r\u0011)o\u001c\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013\u0005%!\u0011^A\u0004#\u0006\r\u0012b\u0001Bv_\n1A+\u001e9mKNB\u0011Ba<9\u0003\u0003\u0005\rA!\u0012\u0002\u0007a$\u0003'\u0001\u0005tCZ,\u0017*\u001c9m)\u0011\u0011)Pa?\u0011\t\u0005%!q_\u0005\u0004\u0005s|'\u0001B+oSRDaAa\u0003:\u0001\u0004I'a\u0007$N\u00072\f7o]5gS\u000e\fG/[8o\u001b>$W\r\u001c*fC\u0012,'oE\u0002;\u0003{$\"aa\u0001\u0011\u0007\t\u0015\"(A\u0005dY\u0006\u001c8OT1nK\u0006Q1\r\\1tg:\u000bW.\u001a\u0011\u0015\u0007]\u001bY\u0001\u0003\u0004\u0003\fy\u0002\r![\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007#\u0001BAa \u0004\u0014%!1Q\u0003BA\u0005\u0019y%M[3di\"\u001aqc^?)\u0007Y9X\u0010"
)
public class FMClassificationModel extends ProbabilisticClassificationModel implements FMClassifierParams, MLWritable, HasTrainingSummary {
   private final String uid;
   private final double intercept;
   private final Vector linear;
   private final Matrix factors;
   private final int numClasses;
   private final int numFeatures;
   private Option trainingSummary;
   private IntParam factorSize;
   private BooleanParam fitLinear;
   private DoubleParam miniBatchFraction;
   private DoubleParam initStd;
   private Param solver;
   private Param weightCol;
   private DoubleParam regParam;
   private BooleanParam fitIntercept;
   private LongParam seed;
   private DoubleParam tol;
   private DoubleParam stepSize;
   private IntParam maxIter;

   public static FMClassificationModel load(final String path) {
      return FMClassificationModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return FMClassificationModel$.MODULE$.read();
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

   public final int getFactorSize() {
      return FactorizationMachinesParams.getFactorSize$(this);
   }

   public final boolean getFitLinear() {
      return FactorizationMachinesParams.getFitLinear$(this);
   }

   public final double getMiniBatchFraction() {
      return FactorizationMachinesParams.getMiniBatchFraction$(this);
   }

   public final double getInitStd() {
      return FactorizationMachinesParams.getInitStd$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getSolver() {
      return HasSolver.getSolver$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final double getStepSize() {
      return HasStepSize.getStepSize$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final Option trainingSummary() {
      return this.trainingSummary;
   }

   public final void trainingSummary_$eq(final Option x$1) {
      this.trainingSummary = x$1;
   }

   public final IntParam factorSize() {
      return this.factorSize;
   }

   public final BooleanParam fitLinear() {
      return this.fitLinear;
   }

   public final DoubleParam miniBatchFraction() {
      return this.miniBatchFraction;
   }

   public final DoubleParam initStd() {
      return this.initStd;
   }

   public final Param solver() {
      return this.solver;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$factorSize_$eq(final IntParam x$1) {
      this.factorSize = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$fitLinear_$eq(final BooleanParam x$1) {
      this.fitLinear = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$miniBatchFraction_$eq(final DoubleParam x$1) {
      this.miniBatchFraction = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$initStd_$eq(final DoubleParam x$1) {
      this.initStd = x$1;
   }

   public final void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
   }

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasSolver$_setter_$solver_$eq(final Param x$1) {
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public DoubleParam stepSize() {
      return this.stepSize;
   }

   public void org$apache$spark$ml$param$shared$HasStepSize$_setter_$stepSize_$eq(final DoubleParam x$1) {
      this.stepSize = x$1;
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

   public double intercept() {
      return this.intercept;
   }

   public Vector linear() {
      return this.linear;
   }

   public Matrix factors() {
      return this.factors;
   }

   public int numClasses() {
      return this.numClasses;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public FMClassificationTrainingSummary summary() {
      return (FMClassificationTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public FMClassificationSummary evaluate(final Dataset dataset) {
      String weightColName = !this.isDefined(this.weightCol()) ? "weightCol" : (String)this.$(this.weightCol());
      Tuple3 var5 = this.findSummaryModel();
      if (var5 != null) {
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var5._1();
         String probability = (String)var5._2();
         String predictionColName = (String)var5._3();
         Tuple3 var4 = new Tuple3(summaryModel, probability, predictionColName);
         ProbabilisticClassificationModel summaryModel = (ProbabilisticClassificationModel)var4._1();
         String probability = (String)var4._2();
         String predictionColName = (String)var4._3();
         return new FMClassificationSummaryImpl(summaryModel.transform(dataset), probability, predictionColName, (String)this.$(this.labelCol()), weightColName);
      } else {
         throw new MatchError(var5);
      }
   }

   public Vector predictRaw(final Vector features) {
      double rawPrediction = FactorizationMachines$.MODULE$.getRawPrediction(features, this.intercept(), this.linear(), this.factors());
      return .MODULE$.dense(new double[]{-rawPrediction, rawPrediction});
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      if (rawPrediction instanceof DenseVector var4) {
         var4.values()[1] = (double)1.0F / ((double)1.0F + scala.math.package..MODULE$.exp(-var4.values()[1]));
         var4.values()[0] = (double)1.0F - var4.values()[1];
         return var4;
      } else if (rawPrediction instanceof SparseVector) {
         throw new RuntimeException("Unexpected error in FMClassificationModel: raw2probabilityInPlace encountered SparseVector");
      } else {
         throw new MatchError(rawPrediction);
      }
   }

   public FMClassificationModel copy(final ParamMap extra) {
      return (FMClassificationModel)this.copyValues(new FMClassificationModel(this.uid(), this.intercept(), this.linear(), this.factors()), extra);
   }

   public MLWriter write() {
      return new FMClassificationModelWriter(this);
   }

   public String toString() {
      String var10000 = Identifiable.toString$(this);
      return "FMClassificationModel: uid=" + var10000 + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures() + ", factorSize=" + this.$(this.factorSize()) + ", fitLinear=" + this.$(this.fitLinear()) + ", fitIntercept=" + this.$(this.fitIntercept());
   }

   public FMClassificationModel(final String uid, final double intercept, final Vector linear, final Matrix factors) {
      this.uid = uid;
      this.intercept = intercept;
      this.linear = linear;
      this.factors = factors;
      HasMaxIter.$init$(this);
      HasStepSize.$init$(this);
      HasTol.$init$(this);
      HasSolver.$init$(this);
      HasSeed.$init$(this);
      HasFitIntercept.$init$(this);
      HasRegParam.$init$(this);
      HasWeightCol.$init$(this);
      FactorizationMachinesParams.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      this.numClasses = 2;
      this.numFeatures = linear.size();
      Statics.releaseFence();
   }

   public FMClassificationModel() {
      this("", Double.NaN, .MODULE$.empty(), org.apache.spark.ml.linalg.Matrices..MODULE$.empty());
   }

   public static class FMClassificationModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final FMClassificationModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.intercept(), this.instance.linear(), this.instance.factors());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(FMClassificationModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.classification.FMClassificationModel.FMClassificationModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.classification.FMClassificationModel.FMClassificationModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public FMClassificationModelWriter(final FMClassificationModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final double intercept;
         private final Vector linear;
         private final Matrix factors;
         // $FF: synthetic field
         public final FMClassificationModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public double intercept() {
            return this.intercept;
         }

         public Vector linear() {
            return this.linear;
         }

         public Matrix factors() {
            return this.factors;
         }

         public Data copy(final double intercept, final Vector linear, final Matrix factors) {
            return this.org$apache$spark$ml$classification$FMClassificationModel$FMClassificationModelWriter$Data$$$outer().new Data(intercept, linear, factors);
         }

         public double copy$default$1() {
            return this.intercept();
         }

         public Vector copy$default$2() {
            return this.linear();
         }

         public Matrix copy$default$3() {
            return this.factors();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToDouble(this.intercept());
               }
               case 1 -> {
                  return this.linear();
               }
               case 2 -> {
                  return this.factors();
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
                  return "intercept";
               }
               case 1 -> {
                  return "linear";
               }
               case 2 -> {
                  return "factors";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, Statics.doubleHash(this.intercept()));
            var1 = Statics.mix(var1, Statics.anyHash(this.linear()));
            var1 = Statics.mix(var1, Statics.anyHash(this.factors()));
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var8;
            if (this != x$1) {
               label64: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$classification$FMClassificationModel$FMClassificationModelWriter$Data$$$outer() == this.org$apache$spark$ml$classification$FMClassificationModel$FMClassificationModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.intercept() == var4.intercept()) {
                        label54: {
                           Vector var10000 = this.linear();
                           Vector var5 = var4.linear();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label54;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label54;
                           }

                           Matrix var7 = this.factors();
                           Matrix var6 = var4.factors();
                           if (var7 == null) {
                              if (var6 != null) {
                                 break label54;
                              }
                           } else if (!var7.equals(var6)) {
                              break label54;
                           }

                           if (var4.canEqual(this)) {
                              break label64;
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
         public FMClassificationModelWriter org$apache$spark$ml$classification$FMClassificationModel$FMClassificationModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final double intercept, final Vector linear, final Matrix factors) {
            this.intercept = intercept;
            this.linear = linear;
            this.factors = factors;
            if (FMClassificationModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = FMClassificationModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final FMClassificationModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final double intercept, final Vector linear, final Matrix factors) {
            return this.$outer.new Data(intercept, linear, factors);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToDouble(x$0.intercept()), x$0.linear(), x$0.factors())));
         }

         public Data$() {
            if (FMClassificationModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = FMClassificationModelWriter.this;
               super();
            }
         }
      }
   }

   private static class FMClassificationModelReader extends MLReader {
      private final String className = FMClassificationModel.class.getName();

      private String className() {
         return this.className;
      }

      public FMClassificationModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().format("parquet").load(dataPath);
         Row var7 = (Row)data.select("intercept", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"linear", "factors"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(3) == 0) {
               Object intercept = ((SeqOps)var8.get()).apply(0);
               Object linear = ((SeqOps)var8.get()).apply(1);
               Object factors = ((SeqOps)var8.get()).apply(2);
               if (intercept instanceof Double) {
                  double var12 = BoxesRunTime.unboxToDouble(intercept);
                  if (linear instanceof Vector) {
                     Vector var14 = (Vector)linear;
                     if (factors instanceof Matrix) {
                        Matrix var15 = (Matrix)factors;
                        Tuple3 var6 = new Tuple3(BoxesRunTime.boxToDouble(var12), var14, var15);
                        double intercept = BoxesRunTime.unboxToDouble(var6._1());
                        Vector linear = (Vector)var6._2();
                        Matrix factors = (Matrix)var6._3();
                        FMClassificationModel model = new FMClassificationModel(metadata.uid(), intercept, linear, factors);
                        metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                        return model;
                     }
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public FMClassificationModelReader() {
      }
   }
}
