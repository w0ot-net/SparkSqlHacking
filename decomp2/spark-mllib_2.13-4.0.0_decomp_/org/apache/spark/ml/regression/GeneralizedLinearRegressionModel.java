package org.apache.spark.ml.regression;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.BLAS.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructType;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
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
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r5c\u0001\u0002 @\u0001)C\u0001\"\u001a\u0001\u0003\u0006\u0004%\tE\u001a\u0005\t{\u0002\u0011\t\u0011)A\u0005O\"Iq\u0010\u0001BC\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u0003\u000b\u0001!\u0011!Q\u0001\n=C!\"!\u0003\u0001\u0005\u000b\u0007I\u0011AA\u0006\u0011)\t9\u0002\u0001B\u0001B\u0003%\u0011Q\u0002\u0005\t\u00037\u0001A\u0011A!\u0002\u001e!A\u00111\u0004\u0001\u0005\u0002\u0005\u000bY\u0003C\u0004\u0002.\u0001!\t!a\f\t\u0015\u0005e\u0002\u0001#b\u0001\n\u0013\tY\u0004C\u0004\u0002L\u0001!\t%!\u0014\t\u000f\u0005-\u0003\u0001\"\u0003\u0002T!9\u00111\f\u0001\u0005\n\u0005u\u0003bBA2\u0001\u0011\u0005\u0013Q\r\u0005\b\u0003[\u0003A\u0011KAX\u0011\u001d\ti\f\u0001C!\u0003\u007fCq!a1\u0001\t\u0003\t)\rC\u0004\u0002\\\u0002!\t%!8\t\u000f\u0005E\b\u0001\"\u0011\u0002t\"I\u0011Q \u0001C\u0002\u0013\u0005\u0013q \u0005\t\u0005\u000f\u0001\u0001\u0015!\u0003\u0003\u0002!9!\u0011\u0002\u0001\u0005B\t-qa\u0002B\u000b\u007f!\u0005!q\u0003\u0004\u0007}}B\tA!\u0007\t\u000f\u0005m\u0001\u0004\"\u0001\u00038!9!\u0011\b\r\u0005B\tm\u0002b\u0002B#1\u0011\u0005#q\t\u0004\b\u0005\u001fB\u0002\u0001\u0007B)\u0011%\u0011y\u0006\bB\u0001B\u0003%Q\u000bC\u0004\u0002\u001cq!\tA!\u0019\u0007\r\t%D\u0004\u0012B6\u0011)\tIa\bBK\u0002\u0013\u0005\u00111\u0002\u0005\u000b\u0003/y\"\u0011#Q\u0001\n\u00055\u0001\"C@ \u0005+\u0007I\u0011AA\u0001\u0011%\t)a\bB\tB\u0003%q\nC\u0004\u0002\u001c}!\tA!!\t\u0013\u0005mw$!A\u0005\u0002\t-\u0005\"\u0003BI?E\u0005I\u0011\u0001BJ\u0011%\u00119kHI\u0001\n\u0003\u0011I\u000bC\u0005\u0003.~\t\t\u0011\"\u0011\u00030\"I!1X\u0010\u0002\u0002\u0013\u0005\u0011q \u0005\n\u0005{{\u0012\u0011!C\u0001\u0005\u007fC\u0011B!2 \u0003\u0003%\tEa2\t\u0013\tUw$!A\u0005\u0002\t]\u0007\"\u0003Bq?\u0005\u0005I\u0011\tBr\u0011%\u00119oHA\u0001\n\u0003\u0012I\u000fC\u0005\u0003\n}\t\t\u0011\"\u0011\u0003l\"I!Q^\u0010\u0002\u0002\u0013\u0005#q^\u0004\n\u0005gd\u0012\u0011!E\u0005\u0005k4\u0011B!\u001b\u001d\u0003\u0003EIAa>\t\u000f\u0005m!\u0007\"\u0001\u0004\u0006!I!\u0011\u0002\u001a\u0002\u0002\u0013\u0015#1\u001e\u0005\n\u0007\u000f\u0011\u0014\u0011!CA\u0007\u0013A\u0011ba\u00043\u0003\u0003%\ti!\u0005\t\u000f\r\rB\u0004\"\u0015\u0004&\u001911q\u0006\r\u0005\u0007cAq!a\u00079\t\u0003\u0019\u0019\u0004C\u0005\u00048a\u0012\r\u0011\"\u0003\u00030\"A1\u0011\b\u001d!\u0002\u0013\u0011\t\fC\u0004\u0003Fa\"\tea\u000f\t\u0013\r}\u0002$!A\u0005\n\r\u0005#\u0001I$f]\u0016\u0014\u0018\r\\5{K\u0012d\u0015N\\3beJ+wM]3tg&|g.T8eK2T!\u0001Q!\u0002\u0015I,wM]3tg&|gN\u0003\u0002C\u0007\u0006\u0011Q\u000e\u001c\u0006\u0003\t\u0016\u000bQa\u001d9be.T!AR$\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0015aA8sO\u000e\u00011#\u0002\u0001L-f{\u0006\u0003\u0002'N\u001fVk\u0011aP\u0005\u0003\u001d~\u0012qBU3he\u0016\u001c8/[8o\u001b>$W\r\u001c\t\u0003!Nk\u0011!\u0015\u0006\u0003%\u0006\u000ba\u0001\\5oC2<\u0017B\u0001+R\u0005\u00191Vm\u0019;peB\u0011A\n\u0001\t\u0003\u0019^K!\u0001W \u0003?\u001d+g.\u001a:bY&TX\r\u001a'j]\u0016\f'OU3he\u0016\u001c8/[8o\u0005\u0006\u001cX\r\u0005\u0002[;6\t1L\u0003\u0002]\u0003\u0006!Q\u000f^5m\u0013\tq6L\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\u00042A\u00171c\u0013\t\t7L\u0001\nICN$&/Y5oS:<7+^7nCJL\bC\u0001'd\u0013\t!wH\u0001\u0016HK:,'/\u00197ju\u0016$G*\u001b8fCJ\u0014Vm\u001a:fgNLwN\u001c+sC&t\u0017N\\4Tk6l\u0017M]=\u0002\u0007ULG-F\u0001h!\tA\u0017O\u0004\u0002j_B\u0011!.\\\u0007\u0002W*\u0011A.S\u0001\u0007yI|w\u000e\u001e \u000b\u00039\fQa]2bY\u0006L!\u0001]7\u0002\rA\u0013X\rZ3g\u0013\t\u00118O\u0001\u0004TiJLgn\u001a\u0006\u0003a6D3!A;|!\t1\u00180D\u0001x\u0015\tA8)\u0001\u0006b]:|G/\u0019;j_:L!A_<\u0003\u000bMKgnY3\"\u0003q\fQA\r\u00181]A\nA!^5eA!\u001a!!^>\u0002\u0019\r|WM\u001a4jG&,g\u000e^:\u0016\u0003=C3aA;|\u00035\u0019w.\u001a4gS\u000eLWM\u001c;tA!\u001aA!^>\u0002\u0013%tG/\u001a:dKB$XCAA\u0007!\u0011\ty!!\u0005\u000e\u00035L1!a\u0005n\u0005\u0019!u.\u001e2mK\"\u001aQ!^>\u0002\u0015%tG/\u001a:dKB$\b\u0005K\u0002\u0007kn\fa\u0001P5oSRtDcB+\u0002 \u0005\r\u0012q\u0005\u0005\u0006K\u001e\u0001\ra\u001a\u0015\u0005\u0003?)8\u0010C\u0003\u0000\u000f\u0001\u0007q\n\u000b\u0003\u0002$U\\\bbBA\u0005\u000f\u0001\u0007\u0011Q\u0002\u0015\u0005\u0003O)8\u0010F\u0001V\u0003Q\u0019X\r\u001e'j].\u0004&/\u001a3jGRLwN\\\"pYR!\u0011\u0011GA\u001a\u001b\u0005\u0001\u0001BBA\u001b\u0013\u0001\u0007q-A\u0003wC2,X\rK\u0002\nkn\fQBZ1nS2L\u0018I\u001c3MS:\\WCAA\u001f!\u0011\ty$!\u0012\u000f\u00071\u000b\t%C\u0002\u0002D}\n1dR3oKJ\fG.\u001b>fI2Kg.Z1s%\u0016<'/Z:tS>t\u0017\u0002BA$\u0003\u0013\u0012QBR1nS2L\u0018I\u001c3MS:\\'bAA\"\u007f\u00059\u0001O]3eS\u000e$H\u0003BA\u0007\u0003\u001fBa!!\u0015\f\u0001\u0004y\u0015\u0001\u00034fCR,(/Z:\u0015\r\u00055\u0011QKA,\u0011\u0019\t\t\u0006\u0004a\u0001\u001f\"9\u0011\u0011\f\u0007A\u0002\u00055\u0011AB8gMN,G/A\u0006qe\u0016$\u0017n\u0019;MS:\\GCBA\u0007\u0003?\n\t\u0007\u0003\u0004\u0002R5\u0001\ra\u0014\u0005\b\u00033j\u0001\u0019AA\u0007\u0003%!(/\u00198tM>\u0014X\u000e\u0006\u0003\u0002h\u0005%\u0005\u0003BA5\u0003\u0007sA!a\u001b\u0002~9!\u0011QNA=\u001d\u0011\ty'a\u001e\u000f\t\u0005E\u0014Q\u000f\b\u0004U\u0006M\u0014\"\u0001%\n\u0005\u0019;\u0015B\u0001#F\u0013\r\tYhQ\u0001\u0004gFd\u0017\u0002BA@\u0003\u0003\u000bq\u0001]1dW\u0006<WMC\u0002\u0002|\rKA!!\"\u0002\b\nIA)\u0019;b\rJ\fW.\u001a\u0006\u0005\u0003\u007f\n\t\tC\u0004\u0002\f:\u0001\r!!$\u0002\u000f\u0011\fG/Y:fiB\"\u0011qRAN!\u0019\t\t*a%\u0002\u00186\u0011\u0011\u0011Q\u0005\u0005\u0003+\u000b\tIA\u0004ECR\f7/\u001a;\u0011\t\u0005e\u00151\u0014\u0007\u0001\t1\ti*!#\u0002\u0002\u0003\u0005)\u0011AAP\u0005\ryFEM\t\u0005\u0003C\u000b9\u000b\u0005\u0003\u0002\u0010\u0005\r\u0016bAAS[\n9aj\u001c;iS:<\u0007\u0003BA\b\u0003SK1!a+n\u0005\r\te._\u0001\u000eiJ\fgn\u001d4pe6LU\u000e\u001d7\u0015\t\u0005\u001d\u0014\u0011\u0017\u0005\b\u0003\u0017{\u0001\u0019AAZa\u0011\t),!/\u0011\r\u0005E\u00151SA\\!\u0011\tI*!/\u0005\u0019\u0005m\u0016\u0011WA\u0001\u0002\u0003\u0015\t!a(\u0003\u0007}#3'A\u0004tk6l\u0017M]=\u0016\u0003\tD3\u0001E;|\u0003!)g/\u00197vCR,G\u0003BAd\u0003\u001b\u00042\u0001TAe\u0013\r\tYm\u0010\u0002#\u000f\u0016tWM]1mSj,G\rT5oK\u0006\u0014(+Z4sKN\u001c\u0018n\u001c8Tk6l\u0017M]=\t\u000f\u0005-\u0015\u00031\u0001\u0002PB\"\u0011\u0011[Ak!\u0019\t\t*a%\u0002TB!\u0011\u0011TAk\t1\t9.!4\u0002\u0002\u0003\u0005)\u0011AAP\u0005\ryF\u0005\u000e\u0015\u0004#U\\\u0018\u0001B2paf$2!VAp\u0011\u001d\t\tO\u0005a\u0001\u0003G\fQ!\u001a=ue\u0006\u0004B!!:\u0002l6\u0011\u0011q\u001d\u0006\u0004\u0003S\f\u0015!\u00029be\u0006l\u0017\u0002BAw\u0003O\u0014\u0001\u0002U1sC6l\u0015\r\u001d\u0015\u0004%U\\\u0018!B<sSR,WCAA{!\rQ\u0016q_\u0005\u0004\u0003s\\&\u0001C'M/JLG/\u001a:)\u0007M)80A\u0006ok64U-\u0019;ve\u0016\u001cXC\u0001B\u0001!\u0011\tyAa\u0001\n\u0007\t\u0015QNA\u0002J]R\fAB\\;n\r\u0016\fG/\u001e:fg\u0002\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002O\"\"a#\u001eB\bC\t\u0011\t\"A\u00034]Ar\u0003\u0007K\u0002\u0001kn\f\u0001eR3oKJ\fG.\u001b>fI2Kg.Z1s%\u0016<'/Z:tS>tWj\u001c3fYB\u0011A\nG\n\b1\tm!\u0011\u0005B\u0014!\u0011\tyA!\b\n\u0007\t}QN\u0001\u0004B]f\u0014VM\u001a\t\u00055\n\rR+C\u0002\u0003&m\u0013!\"\u0014'SK\u0006$\u0017M\u00197f!\u0011\u0011ICa\r\u000e\u0005\t-\"\u0002\u0002B\u0017\u0005_\t!![8\u000b\u0005\tE\u0012\u0001\u00026bm\u0006LAA!\u000e\u0003,\ta1+\u001a:jC2L'0\u00192mKR\u0011!qC\u0001\u0005e\u0016\fG-\u0006\u0002\u0003>A!!La\u0010V\u0013\r\u0011\te\u0017\u0002\t\u001b2\u0013V-\u00193fe\"\u001a!$^>\u0002\t1|\u0017\r\u001a\u000b\u0004+\n%\u0003B\u0002B&7\u0001\u0007q-\u0001\u0003qCRD\u0007fA\u000evw\n1s)\u001a8fe\u0006d\u0017N_3e\u0019&tW-\u0019:SK\u001e\u0014Xm]:j_:lu\u000eZ3m/JLG/\u001a:\u0014\u000bq\t)Pa\u0015\u0011\t\tU#1L\u0007\u0003\u0005/R1A!\u0017D\u0003!Ig\u000e^3s]\u0006d\u0017\u0002\u0002B/\u0005/\u0012q\u0001T8hO&tw-\u0001\u0005j]N$\u0018M\\2f)\u0011\u0011\u0019Ga\u001a\u0011\u0007\t\u0015D$D\u0001\u0019\u0011\u0019\u0011yF\ba\u0001+\n!A)\u0019;b'\u001dy\"1\u0004B7\u0005g\u0002B!a\u0004\u0003p%\u0019!\u0011O7\u0003\u000fA\u0013x\u000eZ;diB!!Q\u000fB?\u001d\u0011\u00119Ha\u001f\u000f\u0007)\u0014I(C\u0001o\u0013\r\ty(\\\u0005\u0005\u0005k\u0011yHC\u0002\u0002\u00005$bAa!\u0003\b\n%\u0005c\u0001BC?5\tA\u0004C\u0004\u0002\n\u0011\u0002\r!!\u0004\t\u000b}$\u0003\u0019A(\u0015\r\t\r%Q\u0012BH\u0011%\tI!\nI\u0001\u0002\u0004\ti\u0001C\u0004\u0000KA\u0005\t\u0019A(\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!Q\u0013\u0016\u0005\u0003\u001b\u00119j\u000b\u0002\u0003\u001aB!!1\u0014BR\u001b\t\u0011iJ\u0003\u0003\u0003 \n\u0005\u0016!C;oG\",7m[3e\u0015\tAX.\u0003\u0003\u0003&\nu%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\u0001BVU\ry%qS\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\tE\u0006\u0003\u0002BZ\u0005sk!A!.\u000b\t\t]&qF\u0001\u0005Y\u0006tw-C\u0002s\u0005k\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002(\n\u0005\u0007\"\u0003BbU\u0005\u0005\t\u0019\u0001B\u0001\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011!\u0011\u001a\t\u0007\u0005\u0017\u0014\t.a*\u000e\u0005\t5'b\u0001Bh[\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\tM'Q\u001a\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003Z\n}\u0007\u0003BA\b\u00057L1A!8n\u0005\u001d\u0011un\u001c7fC:D\u0011Ba1-\u0003\u0003\u0005\r!a*\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005c\u0013)\u000fC\u0005\u0003D6\n\t\u00111\u0001\u0003\u0002\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0003\u0002Q\u0011!\u0011W\u0001\u0007KF,\u0018\r\\:\u0015\t\te'\u0011\u001f\u0005\n\u0005\u0007\u0004\u0014\u0011!a\u0001\u0003O\u000bA\u0001R1uCB\u0019!Q\u0011\u001a\u0014\u000bI\u0012IPa\n\u0011\u0013\tm8\u0011AA\u0007\u001f\n\rUB\u0001B\u007f\u0015\r\u0011y0\\\u0001\beVtG/[7f\u0013\u0011\u0019\u0019A!@\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>t'\u0007\u0006\u0002\u0003v\u0006)\u0011\r\u001d9msR1!1QB\u0006\u0007\u001bAq!!\u00036\u0001\u0004\ti\u0001C\u0003\u0000k\u0001\u0007q*A\u0004v]\u0006\u0004\b\u000f\\=\u0015\t\rM1q\u0004\t\u0007\u0003\u001f\u0019)b!\u0007\n\u0007\r]QN\u0001\u0004PaRLwN\u001c\t\b\u0003\u001f\u0019Y\"!\u0004P\u0013\r\u0019i\"\u001c\u0002\u0007)V\u0004H.\u001a\u001a\t\u0013\r\u0005b'!AA\u0002\t\r\u0015a\u0001=%a\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0004(\r5\u0002\u0003BA\b\u0007SI1aa\u000bn\u0005\u0011)f.\u001b;\t\r\t-s\u00071\u0001h\u0005\u0019:UM\\3sC2L'0\u001a3MS:,\u0017M\u001d*fOJ,7o]5p]6{G-\u001a7SK\u0006$WM]\n\u0004q\tuBCAB\u001b!\r\u0011)\u0007O\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!)\r)6Q\b\u0005\u0007\u0005\u0017b\u0004\u0019A4\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r\r\u0003\u0003\u0002BZ\u0007\u000bJAaa\u0012\u00036\n1qJ\u00196fGRD3\u0001G;|Q\r9Ro\u001f"
)
public class GeneralizedLinearRegressionModel extends RegressionModel implements GeneralizedLinearRegressionBase, MLWritable, HasTrainingSummary {
   private GeneralizedLinearRegression.FamilyAndLink familyAndLink;
   private final String uid;
   private final Vector coefficients;
   private final double intercept;
   private final int numFeatures;
   private Option trainingSummary;
   private Param family;
   private DoubleParam variancePower;
   private Param link;
   private DoubleParam linkPower;
   private Param linkPredictionCol;
   private Param offsetCol;
   private Param solver;
   private IntParam aggregationDepth;
   private Param weightCol;
   private DoubleParam regParam;
   private DoubleParam tol;
   private IntParam maxIter;
   private BooleanParam fitIntercept;
   private volatile boolean bitmap$0;

   public static GeneralizedLinearRegressionModel load(final String path) {
      return GeneralizedLinearRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GeneralizedLinearRegressionModel$.MODULE$.read();
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

   // $FF: synthetic method
   public StructType org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$$super$validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return PredictorParams.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
   }

   public String getFamily() {
      return GeneralizedLinearRegressionBase.getFamily$(this);
   }

   public double getVariancePower() {
      return GeneralizedLinearRegressionBase.getVariancePower$(this);
   }

   public String getLink() {
      return GeneralizedLinearRegressionBase.getLink$(this);
   }

   public double getLinkPower() {
      return GeneralizedLinearRegressionBase.getLinkPower$(this);
   }

   public String getLinkPredictionCol() {
      return GeneralizedLinearRegressionBase.getLinkPredictionCol$(this);
   }

   public String getOffsetCol() {
      return GeneralizedLinearRegressionBase.getOffsetCol$(this);
   }

   public boolean hasWeightCol() {
      return GeneralizedLinearRegressionBase.hasWeightCol$(this);
   }

   public boolean hasOffsetCol() {
      return GeneralizedLinearRegressionBase.hasOffsetCol$(this);
   }

   public boolean hasLinkPredictionCol() {
      return GeneralizedLinearRegressionBase.hasLinkPredictionCol$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting, final DataType featuresDataType) {
      return GeneralizedLinearRegressionBase.validateAndTransformSchema$(this, schema, fitting, featuresDataType);
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

   public final double getRegParam() {
      return HasRegParam.getRegParam$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final int getMaxIter() {
      return HasMaxIter.getMaxIter$(this);
   }

   public final boolean getFitIntercept() {
      return HasFitIntercept.getFitIntercept$(this);
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

   public final DoubleParam variancePower() {
      return this.variancePower;
   }

   public final Param link() {
      return this.link;
   }

   public final DoubleParam linkPower() {
      return this.linkPower;
   }

   public final Param linkPredictionCol() {
      return this.linkPredictionCol;
   }

   public final Param offsetCol() {
      return this.offsetCol;
   }

   public final Param solver() {
      return this.solver;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$family_$eq(final Param x$1) {
      this.family = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$variancePower_$eq(final DoubleParam x$1) {
      this.variancePower = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$link_$eq(final Param x$1) {
      this.link = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPower_$eq(final DoubleParam x$1) {
      this.linkPower = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$linkPredictionCol_$eq(final Param x$1) {
      this.linkPredictionCol = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$offsetCol_$eq(final Param x$1) {
      this.offsetCol = x$1;
   }

   public final void org$apache$spark$ml$regression$GeneralizedLinearRegressionBase$_setter_$solver_$eq(final Param x$1) {
      this.solver = x$1;
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

   public final DoubleParam regParam() {
      return this.regParam;
   }

   public final void org$apache$spark$ml$param$shared$HasRegParam$_setter_$regParam_$eq(final DoubleParam x$1) {
      this.regParam = x$1;
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

   public final BooleanParam fitIntercept() {
      return this.fitIntercept;
   }

   public final void org$apache$spark$ml$param$shared$HasFitIntercept$_setter_$fitIntercept_$eq(final BooleanParam x$1) {
      this.fitIntercept = x$1;
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

   public GeneralizedLinearRegressionModel setLinkPredictionCol(final String value) {
      return (GeneralizedLinearRegressionModel)this.set(this.linkPredictionCol(), value);
   }

   private GeneralizedLinearRegression.FamilyAndLink familyAndLink$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.familyAndLink = GeneralizedLinearRegression.FamilyAndLink$.MODULE$.apply(this);
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.familyAndLink;
   }

   private GeneralizedLinearRegression.FamilyAndLink familyAndLink() {
      return !this.bitmap$0 ? this.familyAndLink$lzycompute() : this.familyAndLink;
   }

   public double predict(final Vector features) {
      return this.predict(features, (double)0.0F);
   }

   private double predict(final Vector features, final double offset) {
      double eta = this.predictLink(features, offset);
      return this.familyAndLink().fitted(eta);
   }

   private double predictLink(final Vector features, final double offset) {
      return .MODULE$.dot(features, this.coefficients()) + this.intercept() + offset;
   }

   public Dataset transform(final Dataset dataset) {
      this.transformSchema(dataset.schema());
      return this.transformImpl(dataset);
   }

   public Dataset transformImpl(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Column offset = !this.hasOffsetCol() ? org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToDouble((double)0.0F)) : org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.offsetCol())).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
      Dataset outputData = dataset;
      int numColsOutput = 0;
      if (this.hasLinkPredictionCol()) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function2 var10001 = (features, offsetx) -> BoxesRunTime.boxToDouble($anonfun$transformImpl$1(this, features, BoxesRunTime.unboxToDouble(offsetx)));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GeneralizedLinearRegressionModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         UserDefinedFunction predLinkUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
         outputData = dataset.withColumn((String)this.$(this.linkPredictionCol()), predLinkUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol())), offset}))), outputSchema.apply((String)this.$(this.linkPredictionCol())).metadata());
         ++numColsOutput;
      }

      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         if (this.hasLinkPredictionCol()) {
            UserDefinedFunction predUDF = org.apache.spark.sql.functions..MODULE$.udf((JFunction1.mcDD.sp)(eta) -> this.familyAndLink().fitted(eta), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double(), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
            outputData = outputData.withColumn((String)this.$(this.predictionCol()), predUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.linkPredictionCol()))}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
         } else {
            functions var13 = org.apache.spark.sql.functions..MODULE$;
            Function2 var14 = (features, offsetx) -> BoxesRunTime.boxToDouble($anonfun$transformImpl$3(this, features, BoxesRunTime.unboxToDouble(offsetx)));
            TypeTags.TypeTag var15 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GeneralizedLinearRegressionModel.class.getClassLoader());

            final class $typecreator2$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator2$1() {
               }
            }

            UserDefinedFunction predUDF = var13.udf(var14, var15, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double());
            outputData = outputData.withColumn((String)this.$(this.predictionCol()), predUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol())), offset}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
         }

         ++numColsOutput;
      }

      if (numColsOutput == 0) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": GeneralizedLinearRegressionModel.transform()"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" does nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return outputData.toDF();
   }

   public GeneralizedLinearRegressionTrainingSummary summary() {
      return (GeneralizedLinearRegressionTrainingSummary)HasTrainingSummary.summary$(this);
   }

   public GeneralizedLinearRegressionSummary evaluate(final Dataset dataset) {
      return new GeneralizedLinearRegressionSummary(dataset, this);
   }

   public GeneralizedLinearRegressionModel copy(final ParamMap extra) {
      GeneralizedLinearRegressionModel copied = (GeneralizedLinearRegressionModel)this.copyValues(new GeneralizedLinearRegressionModel(this.uid(), this.coefficients(), this.intercept()), extra);
      return (GeneralizedLinearRegressionModel)((Model)copied.setSummary(this.trainingSummary())).setParent(this.parent());
   }

   public MLWriter write() {
      return new GeneralizedLinearRegressionModelWriter(this);
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public String toString() {
      String var10000 = this.uid();
      return "GeneralizedLinearRegressionModel: uid=" + var10000 + ", family=" + this.$(this.family()) + ", link=" + this.$(this.link()) + ", numFeatures=" + this.numFeatures();
   }

   // $FF: synthetic method
   public static final double $anonfun$transformImpl$1(final GeneralizedLinearRegressionModel $this, final Vector features, final double offset) {
      return $this.predictLink(features, offset);
   }

   // $FF: synthetic method
   public static final double $anonfun$transformImpl$3(final GeneralizedLinearRegressionModel $this, final Vector features, final double offset) {
      return $this.predict(features, offset);
   }

   public GeneralizedLinearRegressionModel(final String uid, final Vector coefficients, final double intercept) {
      this.uid = uid;
      this.coefficients = coefficients;
      this.intercept = intercept;
      HasFitIntercept.$init$(this);
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasRegParam.$init$(this);
      HasWeightCol.$init$(this);
      HasSolver.$init$(this);
      HasAggregationDepth.$init$(this);
      GeneralizedLinearRegressionBase.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      this.numFeatures = coefficients.size();
      Statics.releaseFence();
   }

   public GeneralizedLinearRegressionModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), Double.NaN);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class GeneralizedLinearRegressionModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final GeneralizedLinearRegressionModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.intercept(), this.instance.coefficients());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GeneralizedLinearRegressionModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.regression.GeneralizedLinearRegressionModel.GeneralizedLinearRegressionModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.regression.GeneralizedLinearRegressionModel.GeneralizedLinearRegressionModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$2() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).write().parquet(dataPath);
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

      public GeneralizedLinearRegressionModelWriter(final GeneralizedLinearRegressionModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final double intercept;
         private final Vector coefficients;
         // $FF: synthetic field
         public final GeneralizedLinearRegressionModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public double intercept() {
            return this.intercept;
         }

         public Vector coefficients() {
            return this.coefficients;
         }

         public Data copy(final double intercept, final Vector coefficients) {
            return this.org$apache$spark$ml$regression$GeneralizedLinearRegressionModel$GeneralizedLinearRegressionModelWriter$Data$$$outer().new Data(intercept, coefficients);
         }

         public double copy$default$1() {
            return this.intercept();
         }

         public Vector copy$default$2() {
            return this.coefficients();
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
                  return BoxesRunTime.boxToDouble(this.intercept());
               }
               case 1 -> {
                  return this.coefficients();
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
                  return "coefficients";
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
            var1 = Statics.mix(var1, Statics.anyHash(this.coefficients()));
            return Statics.finalizeHash(var1, 2);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label56: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$regression$GeneralizedLinearRegressionModel$GeneralizedLinearRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$regression$GeneralizedLinearRegressionModel$GeneralizedLinearRegressionModelWriter$Data$$$outer()) {
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
         public GeneralizedLinearRegressionModelWriter org$apache$spark$ml$regression$GeneralizedLinearRegressionModel$GeneralizedLinearRegressionModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final double intercept, final Vector coefficients) {
            this.intercept = intercept;
            this.coefficients = coefficients;
            if (GeneralizedLinearRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = GeneralizedLinearRegressionModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction2 implements Serializable {
         // $FF: synthetic field
         private final GeneralizedLinearRegressionModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final double intercept, final Vector coefficients) {
            return this.$outer.new Data(intercept, coefficients);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToDouble(x$0.intercept()), x$0.coefficients())));
         }

         public Data$() {
            if (GeneralizedLinearRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = GeneralizedLinearRegressionModelWriter.this;
               super();
            }
         }
      }
   }

   private static class GeneralizedLinearRegressionModelReader extends MLReader {
      private final String className = GeneralizedLinearRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public GeneralizedLinearRegressionModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("intercept", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"coefficients"}))).head();
         double intercept = data.getDouble(0);
         Vector coefficients = (Vector)data.getAs(1);
         GeneralizedLinearRegressionModel model = new GeneralizedLinearRegressionModel(metadata.uid(), coefficients, intercept);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public GeneralizedLinearRegressionModelReader() {
      }
   }
}
