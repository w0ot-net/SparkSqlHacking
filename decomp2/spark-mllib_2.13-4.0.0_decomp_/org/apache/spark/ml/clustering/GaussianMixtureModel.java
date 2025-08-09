package org.apache.spark.ml.clustering;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasPredictionCol;
import org.apache.spark.ml.param.shared.HasProbabilityCol;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.stat.distribution.MultivariateGaussian;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.HasTrainingSummary;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.ArraySeq;
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
   bytes = "\u0006\u0005\r5e\u0001B!C\u00015C\u0001b\u0019\u0001\u0003\u0006\u0004%\t\u0005\u001a\u0005\tw\u0002\u0011\t\u0011)A\u0005K\"AQ\u0010\u0001BC\u0002\u0013\u0005a\u0010C\u0005\u0002\u0010\u0001\u0011\t\u0011)A\u0005\u007f\"Q\u00111\u0003\u0001\u0003\u0006\u0004%\t!!\u0006\t\u0015\u0005-\u0002A!A!\u0002\u0013\t9\u0002\u0003\u0005\u00020\u0001!\t\u0001RA\u0019\u0011!\ty\u0003\u0001C\u0001\t\u0006}\u0002BCA!\u0001!\u0015\r\u0011\"\u0001\u0002D!9\u0011\u0011\u000b\u0001\u0005\u0002\u0005M\u0003bBA1\u0001\u0011\u0005\u00111\r\u0005\b\u0003S\u0002A\u0011AA6\u0011\u001d\t\t\b\u0001C!\u0003gBq!a\"\u0001\t\u0003\nI\tC\u0004\u0002T\u0002!\t%!6\t\u000f\u0005%\b\u0001\"\u0001\u0002l\"9\u0011q \u0001\u0005\u0002\t\u0005\u0001b\u0002B\u0004\u0001\u0011\u0005!\u0011\u0002\u0005\b\u0005\u001b\u0001A\u0011\tB\b\u0011\u001d\u0011I\u0002\u0001C!\u00057AqAa\b\u0001\t\u0003\u0012\tcB\u0004\u0003(\tC\tA!\u000b\u0007\r\u0005\u0013\u0005\u0012\u0001B\u0016\u0011\u001d\tyc\u0006C\u0001\u0005\u0013BqAa\u0013\u0018\t\u0003\u0012i\u0005C\u0004\u0003X]!\tE!\u0017\u0007\u000f\t\u0005t\u0003A\f\u0003d!I!QM\u000e\u0003\u0002\u0003\u0006IA\u0015\u0005\b\u0003_YB\u0011\u0001B4\r\u0019\u0011yg\u0007#\u0003r!AQP\bBK\u0002\u0013\u0005a\u0010C\u0005\u0002\u0010y\u0011\t\u0012)A\u0005\u007f\"Q!q\u0011\u0010\u0003\u0016\u0004%\tA!#\t\u0015\teeD!E!\u0002\u0013\u0011Y\t\u0003\u0006\u0003\u001cz\u0011)\u001a!C\u0001\u0005;C!Ba*\u001f\u0005#\u0005\u000b\u0011\u0002BP\u0011\u001d\tyC\bC\u0001\u0005SC\u0011\"!\u001d\u001f\u0003\u0003%\tA!.\t\u0013\tuf$%A\u0005\u0002\t}\u0006\"\u0003Bj=E\u0005I\u0011\u0001Bk\u0011%\u0011INHI\u0001\n\u0003\u0011Y\u000eC\u0005\u0003`z\t\t\u0011\"\u0011\u0003b\"I!Q\u001e\u0010\u0002\u0002\u0013\u0005\u00111\t\u0005\n\u0005_t\u0012\u0011!C\u0001\u0005cD\u0011Ba>\u001f\u0003\u0003%\tE!?\t\u0013\r\u001da$!A\u0005\u0002\r%\u0001\"CB\n=\u0005\u0005I\u0011IB\u000b\u0011%\u0019IBHA\u0001\n\u0003\u001aY\u0002C\u0005\u0003\u001ay\t\t\u0011\"\u0011\u0004\u001e!I1q\u0004\u0010\u0002\u0002\u0013\u00053\u0011E\u0004\n\u0007KY\u0012\u0011!E\u0005\u0007O1\u0011Ba\u001c\u001c\u0003\u0003EIa!\u000b\t\u000f\u0005=B\u0007\"\u0001\u00048!I!\u0011\u0004\u001b\u0002\u0002\u0013\u00153Q\u0004\u0005\n\u0007s!\u0014\u0011!CA\u0007wA\u0011ba\u00115\u0003\u0003%\ti!\u0012\t\u000f\r]3\u0004\"\u0015\u0004Z\u0019111M\f\u0005\u0007KBq!a\f;\t\u0003\u00199\u0007C\u0005\u0004li\u0012\r\u0011\"\u0003\u0003b\"A1Q\u000e\u001e!\u0002\u0013\u0011\u0019\u000fC\u0004\u0003Xi\"\tea\u001c\t\u0011\rMt\u0003\"\u0001C\u0007kB\u0011ba \u0018\u0003\u0003%Ia!!\u0003)\u001d\u000bWo]:jC:l\u0015\u000e\u001f;ve\u0016lu\u000eZ3m\u0015\t\u0019E)\u0001\u0006dYV\u001cH/\u001a:j]\u001eT!!\u0012$\u0002\u00055d'BA$I\u0003\u0015\u0019\b/\u0019:l\u0015\tI%*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0006\u0019qN]4\u0004\u0001M)\u0001A\u0014+X;B\u0019q\n\u0015*\u000e\u0003\u0011K!!\u0015#\u0003\u000b5{G-\u001a7\u0011\u0005M\u0003Q\"\u0001\"\u0011\u0005M+\u0016B\u0001,C\u0005U9\u0015-^:tS\u0006tW*\u001b=ukJ,\u0007+\u0019:b[N\u0004\"\u0001W.\u000e\u0003eS!A\u0017#\u0002\tU$\u0018\u000e\\\u0005\u00039f\u0013!\"\u0014'Xe&$\u0018M\u00197f!\rAf\fY\u0005\u0003?f\u0013!\u0003S1t)J\f\u0017N\\5oON+X.\\1ssB\u00111+Y\u0005\u0003E\n\u0013acR1vgNL\u0017M\\'jqR,(/Z*v[6\f'/_\u0001\u0004k&$W#A3\u0011\u0005\u0019|gBA4n!\tA7.D\u0001j\u0015\tQG*\u0001\u0004=e>|GO\u0010\u0006\u0002Y\u0006)1oY1mC&\u0011an[\u0001\u0007!J,G-\u001a4\n\u0005A\f(AB*ue&twM\u0003\u0002oW\"\u001a\u0011a]=\u0011\u0005Q<X\"A;\u000b\u0005Y4\u0015AC1o]>$\u0018\r^5p]&\u0011\u00010\u001e\u0002\u0006'&t7-Z\u0011\u0002u\u0006)!G\f\u0019/a\u0005!Q/\u001b3!Q\r\u00111/_\u0001\bo\u0016Lw\r\u001b;t+\u0005y\bCBA\u0001\u0003\u0007\t9!D\u0001l\u0013\r\t)a\u001b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0005\u0003\u0003\tI!C\u0002\u0002\f-\u0014a\u0001R8vE2,\u0007fA\u0002ts\u0006Aq/Z5hQR\u001c\b\u0005K\u0002\u0005gf\f\u0011bZ1vgNL\u0017M\\:\u0016\u0005\u0005]\u0001CBA\u0001\u0003\u0007\tI\u0002\u0005\u0003\u0002\u001c\u0005\u0015RBAA\u000f\u0015\u0011\ty\"!\t\u0002\u0019\u0011L7\u000f\u001e:jEV$\u0018n\u001c8\u000b\u0007\u0005\rB)\u0001\u0003ti\u0006$\u0018\u0002BA\u0014\u0003;\u0011A#T;mi&4\u0018M]5bi\u0016<\u0015-^:tS\u0006t\u0007fA\u0003ts\u0006Qq-Y;tg&\fgn\u001d\u0011)\u0007\u0019\u0019\u00180\u0001\u0004=S:LGO\u0010\u000b\b%\u0006M\u0012qGA\u001e\u0011\u0015\u0019w\u00011\u0001fQ\u0011\t\u0019d]=\t\u000bu<\u0001\u0019A@)\t\u0005]2/\u001f\u0005\b\u0003'9\u0001\u0019AA\fQ\u0011\tYd]=\u0015\u0003I\u000b1B\\;n\r\u0016\fG/\u001e:fgV\u0011\u0011Q\t\t\u0005\u0003\u0003\t9%C\u0002\u0002J-\u00141!\u00138uQ\u0011I1/!\u0014\"\u0005\u0005=\u0013!B\u001a/a9\u0002\u0014AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0005\u0003+\n9&D\u0001\u0001\u0011\u0019\tIF\u0003a\u0001K\u0006)a/\u00197vK\"\"!b]A/C\t\ty&A\u00033]Er\u0003'\u0001\ttKR\u0004&/\u001a3jGRLwN\\\"pYR!\u0011QKA3\u0011\u0019\tIf\u0003a\u0001K\"\"1b]A/\u0003E\u0019X\r\u001e)s_\n\f'-\u001b7jif\u001cu\u000e\u001c\u000b\u0005\u0003+\ni\u0007\u0003\u0004\u0002Z1\u0001\r!\u001a\u0015\u0005\u0019M\fi&\u0001\u0003d_BLHc\u0001*\u0002v!9\u0011qO\u0007A\u0002\u0005e\u0014!B3yiJ\f\u0007\u0003BA>\u0003\u0003k!!! \u000b\u0007\u0005}D)A\u0003qCJ\fW.\u0003\u0003\u0002\u0004\u0006u$\u0001\u0003)be\u0006lW*\u00199)\u00075\u0019\u00180A\u0005ue\u0006t7OZ8s[R!\u00111RAW!\u0011\ti)a*\u000f\t\u0005=\u0015\u0011\u0015\b\u0005\u0003#\u000biJ\u0004\u0003\u0002\u0014\u0006me\u0002BAK\u00033s1\u0001[AL\u0013\u0005Y\u0015BA%K\u0013\t9\u0005*C\u0002\u0002 \u001a\u000b1a]9m\u0013\u0011\t\u0019+!*\u0002\u000fA\f7m[1hK*\u0019\u0011q\u0014$\n\t\u0005%\u00161\u0016\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!a)\u0002&\"9\u0011q\u0016\bA\u0002\u0005E\u0016a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003g\u000by\f\u0005\u0004\u00026\u0006]\u00161X\u0007\u0003\u0003KKA!!/\u0002&\n9A)\u0019;bg\u0016$\b\u0003BA_\u0003\u007fc\u0001\u0001\u0002\u0007\u0002B\u00065\u0016\u0011!A\u0001\u0006\u0003\t\u0019MA\u0002`IE\nB!!2\u0002LB!\u0011\u0011AAd\u0013\r\tIm\u001b\u0002\b\u001d>$\b.\u001b8h!\u0011\t\t!!4\n\u0007\u0005=7NA\u0002B]fD3AD:z\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BAl\u0003G\u0004B!!7\u0002`6\u0011\u00111\u001c\u0006\u0005\u0003;\f)+A\u0003usB,7/\u0003\u0003\u0002b\u0006m'AC*ueV\u001cG\u000fV=qK\"9\u0011Q]\bA\u0002\u0005]\u0017AB:dQ\u0016l\u0017\rK\u0002\u0010gf\fq\u0001\u001d:fI&\u001cG\u000f\u0006\u0003\u0002F\u00055\bbBAx!\u0001\u0007\u0011\u0011_\u0001\tM\u0016\fG/\u001e:fgB!\u00111_A}\u001b\t\t)PC\u0002\u0002x\u0012\u000ba\u0001\\5oC2<\u0017\u0002BA~\u0003k\u0014aAV3di>\u0014\b\u0006\u0002\tt\u0003\u001b\n!\u0003\u001d:fI&\u001cG\u000f\u0015:pE\u0006\u0014\u0017\u000e\\5usR!\u0011\u0011\u001fB\u0002\u0011\u001d\ty/\u0005a\u0001\u0003cDC!E:\u0002N\u0005Yq-Y;tg&\fgn\u001d#G+\t\tY\tK\u0002\u0013gf\fQa\u001e:ji\u0016,\"A!\u0005\u0011\u0007a\u0013\u0019\"C\u0002\u0003\u0016e\u0013\u0001\"\u0014'Xe&$XM\u001d\u0015\u0004'ML\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0003\u0015DC\u0001F:\u0002N\u000591/^7nCJLX#\u00011)\u0007U\u0019\u0018\u0010K\u0002\u0001gf\fAcR1vgNL\u0017M\\'jqR,(/Z'pI\u0016d\u0007CA*\u0018'\u001d9\"Q\u0006B\u001a\u0005s\u0001B!!\u0001\u00030%\u0019!\u0011G6\u0003\r\u0005s\u0017PU3g!\u0011A&Q\u0007*\n\u0007\t]\u0012L\u0001\u0006N\u0019J+\u0017\rZ1cY\u0016\u0004BAa\u000f\u0003F5\u0011!Q\b\u0006\u0005\u0005\u007f\u0011\t%\u0001\u0002j_*\u0011!1I\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003H\tu\"\u0001D*fe&\fG.\u001b>bE2,GC\u0001B\u0015\u0003\u0011\u0011X-\u00193\u0016\u0005\t=\u0003\u0003\u0002-\u0003RIK1Aa\u0015Z\u0005!iEJU3bI\u0016\u0014\bfA\rts\u0006!An\\1e)\r\u0011&1\f\u0005\u0007\u0005;R\u0002\u0019A3\u0002\tA\fG\u000f\u001b\u0015\u00045ML(AG$bkN\u001c\u0018.\u00198NSb$XO]3N_\u0012,Gn\u0016:ji\u0016\u00148cA\u000e\u0003\u0012\u0005A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0003j\t5\u0004c\u0001B675\tq\u0003\u0003\u0004\u0003fu\u0001\rA\u0015\u0002\u0005\t\u0006$\u0018mE\u0004\u001f\u0005[\u0011\u0019H!\u001f\u0011\t\u0005\u0005!QO\u0005\u0004\u0005oZ'a\u0002)s_\u0012,8\r\u001e\t\u0005\u0005w\u0012\u0019I\u0004\u0003\u0003~\t\u0005eb\u00015\u0003\u0000%\tA.C\u0002\u0002$.LAAa\u0012\u0003\u0006*\u0019\u00111U6\u0002\u00075,8/\u0006\u0002\u0003\fB1\u0011\u0011AA\u0002\u0005\u001b\u0003BAa$\u0003\u00186\u0011!\u0011\u0013\u0006\u0005\u0003o\u0014\u0019JC\u0002\u0003\u0016\u001a\u000bQ!\u001c7mS\nLA!a?\u0003\u0012\u0006!Q.^:!\u0003\u0019\u0019\u0018nZ7bgV\u0011!q\u0014\t\u0007\u0003\u0003\t\u0019A!)\u0011\t\t=%1U\u0005\u0005\u0005K\u0013\tJ\u0001\u0004NCR\u0014\u0018\u000e_\u0001\bg&<W.Y:!)!\u0011YKa,\u00032\nM\u0006c\u0001BW=5\t1\u0004C\u0003~K\u0001\u0007q\u0010C\u0004\u0003\b\u0016\u0002\rAa#\t\u000f\tmU\u00051\u0001\u0003 RA!1\u0016B\\\u0005s\u0013Y\fC\u0004~MA\u0005\t\u0019A@\t\u0013\t\u001de\u0005%AA\u0002\t-\u0005\"\u0003BNMA\u0005\t\u0019\u0001BP\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*\"A!1+\u0007}\u0014\u0019m\u000b\u0002\u0003FB!!q\u0019Bh\u001b\t\u0011IM\u0003\u0003\u0003L\n5\u0017!C;oG\",7m[3e\u0015\t18.\u0003\u0003\u0003R\n%'!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u0012TC\u0001BlU\u0011\u0011YIa1\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%gU\u0011!Q\u001c\u0016\u0005\u0005?\u0013\u0019-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005G\u0004BA!:\u0003l6\u0011!q\u001d\u0006\u0005\u0005S\u0014\t%\u0001\u0003mC:<\u0017b\u00019\u0003h\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAf\u0005gD\u0011B!>-\u0003\u0003\u0005\r!!\u0012\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u0011Y\u0010\u0005\u0004\u0003~\u000e\r\u00111Z\u0007\u0003\u0005\u007fT1a!\u0001l\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0007\u000b\u0011yP\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BB\u0006\u0007#\u0001B!!\u0001\u0004\u000e%\u00191qB6\u0003\u000f\t{w\u000e\\3b]\"I!Q\u001f\u0018\u0002\u0002\u0003\u0007\u00111Z\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003d\u000e]\u0001\"\u0003B{_\u0005\u0005\t\u0019AA#\u0003!A\u0017m\u001d5D_\u0012,GCAA#)\t\u0011\u0019/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0007\u0017\u0019\u0019\u0003C\u0005\u0003vJ\n\t\u00111\u0001\u0002L\u0006!A)\u0019;b!\r\u0011i\u000bN\n\u0006i\r-\"\u0011\b\t\f\u0007[\u0019\u0019d BF\u0005?\u0013Y+\u0004\u0002\u00040)\u00191\u0011G6\u0002\u000fI,h\u000e^5nK&!1QGB\u0018\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gn\r\u000b\u0003\u0007O\tQ!\u00199qYf$\u0002Ba+\u0004>\r}2\u0011\t\u0005\u0006{^\u0002\ra \u0005\b\u0005\u000f;\u0004\u0019\u0001BF\u0011\u001d\u0011Yj\u000ea\u0001\u0005?\u000bq!\u001e8baBd\u0017\u0010\u0006\u0003\u0004H\rM\u0003CBA\u0001\u0007\u0013\u001ai%C\u0002\u0004L-\u0014aa\u00149uS>t\u0007#CA\u0001\u0007\u001fz(1\u0012BP\u0013\r\u0019\tf\u001b\u0002\u0007)V\u0004H.Z\u001a\t\u0013\rU\u0003(!AA\u0002\t-\u0016a\u0001=%a\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0004\\\r\u0005\u0004\u0003BA\u0001\u0007;J1aa\u0018l\u0005\u0011)f.\u001b;\t\r\tu\u0013\b1\u0001f\u0005i9\u0015-^:tS\u0006tW*\u001b=ukJ,Wj\u001c3fYJ+\u0017\rZ3s'\rQ$q\n\u000b\u0003\u0007S\u00022Aa\u001b;\u0003%\u0019G.Y:t\u001d\u0006lW-\u0001\u0006dY\u0006\u001c8OT1nK\u0002\"2AUB9\u0011\u0019\u0011iF\u0010a\u0001K\u0006!2m\\7qkR,\u0007K]8cC\nLG.\u001b;jKN$ra`B<\u0007s\u001ai\bC\u0004\u0002p~\u0002\r!!=\t\u000f\rmt\b1\u0001\u0002\u0018\u0005)A-[:ug\")Qp\u0010a\u0001\u007f\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u001111\u0011\t\u0005\u0005K\u001c))\u0003\u0003\u0004\b\n\u001d(AB(cU\u0016\u001cG\u000fK\u0002\u0018gfD3AF:z\u0001"
)
public class GaussianMixtureModel extends Model implements GaussianMixtureParams, MLWritable, HasTrainingSummary {
   private int numFeatures;
   private final String uid;
   private final double[] weights;
   private final MultivariateGaussian[] gaussians;
   private Option trainingSummary;
   private IntParam k;
   private IntParam aggregationDepth;
   private DoubleParam tol;
   private Param probabilityCol;
   private Param weightCol;
   private Param predictionCol;
   private LongParam seed;
   private Param featuresCol;
   private IntParam maxIter;
   private volatile boolean bitmap$0;

   public static GaussianMixtureModel load(final String path) {
      return GaussianMixtureModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return GaussianMixtureModel$.MODULE$.read();
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

   public int getK() {
      return GaussianMixtureParams.getK$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema) {
      return GaussianMixtureParams.validateAndTransformSchema$(this, schema);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
   }

   public final double getTol() {
      return HasTol.getTol$(this);
   }

   public final String getProbabilityCol() {
      return HasProbabilityCol.getProbabilityCol$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final String getPredictionCol() {
      return HasPredictionCol.getPredictionCol$(this);
   }

   public final long getSeed() {
      return HasSeed.getSeed$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
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

   public final IntParam k() {
      return this.k;
   }

   public final void org$apache$spark$ml$clustering$GaussianMixtureParams$_setter_$k_$eq(final IntParam x$1) {
      this.k = x$1;
   }

   public final IntParam aggregationDepth() {
      return this.aggregationDepth;
   }

   public final void org$apache$spark$ml$param$shared$HasAggregationDepth$_setter_$aggregationDepth_$eq(final IntParam x$1) {
      this.aggregationDepth = x$1;
   }

   public final DoubleParam tol() {
      return this.tol;
   }

   public final void org$apache$spark$ml$param$shared$HasTol$_setter_$tol_$eq(final DoubleParam x$1) {
      this.tol = x$1;
   }

   public final Param probabilityCol() {
      return this.probabilityCol;
   }

   public final void org$apache$spark$ml$param$shared$HasProbabilityCol$_setter_$probabilityCol_$eq(final Param x$1) {
      this.probabilityCol = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public final Param predictionCol() {
      return this.predictionCol;
   }

   public final void org$apache$spark$ml$param$shared$HasPredictionCol$_setter_$predictionCol_$eq(final Param x$1) {
      this.predictionCol = x$1;
   }

   public final LongParam seed() {
      return this.seed;
   }

   public final void org$apache$spark$ml$param$shared$HasSeed$_setter_$seed_$eq(final LongParam x$1) {
      this.seed = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
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

   public double[] weights() {
      return this.weights;
   }

   public MultivariateGaussian[] gaussians() {
      return this.gaussians;
   }

   private int numFeatures$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.numFeatures = ((MultivariateGaussian).MODULE$.head$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.gaussians()))).mean().size();
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.numFeatures;
   }

   public int numFeatures() {
      return !this.bitmap$0 ? this.numFeatures$lzycompute() : this.numFeatures;
   }

   public GaussianMixtureModel setFeaturesCol(final String value) {
      return (GaussianMixtureModel)this.set(this.featuresCol(), value);
   }

   public GaussianMixtureModel setPredictionCol(final String value) {
      return (GaussianMixtureModel)this.set(this.predictionCol(), value);
   }

   public GaussianMixtureModel setProbabilityCol(final String value) {
      return (GaussianMixtureModel)this.set(this.probabilityCol(), value);
   }

   public GaussianMixtureModel copy(final ParamMap extra) {
      GaussianMixtureModel copied = (GaussianMixtureModel)this.copyValues(new GaussianMixtureModel(this.uid(), this.weights(), this.gaussians()), extra);
      return (GaussianMixtureModel)((Model)copied.setSummary(this.trainingSummary())).setParent(this.parent());
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Column vectorCol = DatasetUtils$.MODULE$.columnToVector(dataset, (String)this.$(this.featuresCol()));
      Dataset outputData = dataset;
      int numColsOutput = 0;
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.probabilityCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (vector) -> this.predictProbability(vector);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         TypeTags.TypeTag var10002 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1());
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureModel.class.getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         UserDefinedFunction probUDF = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()));
         outputData = dataset.withColumn((String)this.$(this.probabilityCol()), probUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{vectorCol}))), outputSchema.apply((String)this.$(this.probabilityCol())).metadata());
         ++numColsOutput;
      }

      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.probabilityCol())))) {
            functions var17 = org.apache.spark.sql.functions..MODULE$;
            Function1 var19 = (vector) -> BoxesRunTime.boxToInteger($anonfun$transform$2(vector));
            TypeTags.TypeTag var21 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Int();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureModel.class.getClassLoader());

            final class $typecreator3$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator3$1() {
               }
            }

            UserDefinedFunction predUDF = var17.udf(var19, var21, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1()));
            outputData = outputData.withColumn((String)this.$(this.predictionCol()), predUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.probabilityCol()))}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
         } else {
            functions var18 = org.apache.spark.sql.functions..MODULE$;
            Function1 var20 = (vector) -> BoxesRunTime.boxToInteger($anonfun$transform$3(this, vector));
            TypeTags.TypeTag var22 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Int();
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureModel.class.getClassLoader());

            final class $typecreator4$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator4$1() {
               }
            }

            UserDefinedFunction predUDF = var18.udf(var20, var22, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1()));
            outputData = outputData.withColumn((String)this.$(this.predictionCol()), predUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{vectorCol}))), outputSchema.apply((String)this.$(this.predictionCol())).metadata());
         }

         ++numColsOutput;
      }

      if (numColsOutput == 0) {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": GaussianMixtureModel.transform() does "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      return outputData.toDF();
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumValues(outputSchema, (String)this.$(this.predictionCol()), this.weights().length);
      }

      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.probabilityCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.probabilityCol()), this.weights().length);
      }

      return outputSchema;
   }

   public int predict(final Vector features) {
      Vector r = this.predictProbability(features);
      return r.argmax();
   }

   public Vector predictProbability(final Vector features) {
      double[] probs = GaussianMixtureModel$.MODULE$.computeProbabilities(features, this.gaussians(), this.weights());
      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(probs);
   }

   public Dataset gaussiansDF() {
      ArraySeq modelGaussians = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(.MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.gaussians()), (gaussian) -> new Tuple2(Vectors$.MODULE$.fromML(gaussian.mean()), Matrices$.MODULE$.fromML(gaussian.cov())), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toImmutableArraySeq();
      SparkSession var10000 = org.apache.spark.sql.SparkSession..MODULE$.builder().getOrCreate();
      JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
      JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureModel.class.getClassLoader());

      final class $typecreator1$2 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple2"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.mllib.linalg.Vector").asType().toTypeConstructor(), new scala.collection.immutable..colon.colon($m$untyped.staticClass("org.apache.spark.mllib.linalg.Matrix").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$)));
         }

         public $typecreator1$2() {
         }
      }

      return var10000.createDataFrame(modelGaussians, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())).toDF(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mean", "cov"})));
   }

   public MLWriter write() {
      return new GaussianMixtureModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "GaussianMixtureModel: uid=" + var10000 + ", k=" + this.weights().length + ", numFeatures=" + this.numFeatures();
   }

   public GaussianMixtureSummary summary() {
      return (GaussianMixtureSummary)HasTrainingSummary.summary$(this);
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$2(final Vector vector) {
      return vector.argmax();
   }

   // $FF: synthetic method
   public static final int $anonfun$transform$3(final GaussianMixtureModel $this, final Vector vector) {
      return $this.predict(vector);
   }

   public GaussianMixtureModel(final String uid, final double[] weights, final MultivariateGaussian[] gaussians) {
      this.uid = uid;
      this.weights = weights;
      this.gaussians = gaussians;
      HasMaxIter.$init$(this);
      HasFeaturesCol.$init$(this);
      HasSeed.$init$(this);
      HasPredictionCol.$init$(this);
      HasWeightCol.$init$(this);
      HasProbabilityCol.$init$(this);
      HasTol.$init$(this);
      HasAggregationDepth.$init$(this);
      GaussianMixtureParams.$init$(this);
      MLWritable.$init$(this);
      HasTrainingSummary.$init$(this);
      Statics.releaseFence();
   }

   public GaussianMixtureModel() {
      this("", scala.Array..MODULE$.emptyDoubleArray(), (MultivariateGaussian[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class GaussianMixtureModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final GaussianMixtureModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         double[] weights = this.instance.weights();
         MultivariateGaussian[] gaussians = this.instance.gaussians();
         org.apache.spark.mllib.linalg.Vector[] mus = (org.apache.spark.mllib.linalg.Vector[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])gaussians), (g) -> Vectors$.MODULE$.fromML(g.mean()), scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class));
         Matrix[] sigmas = (Matrix[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])gaussians), (c) -> Matrices$.MODULE$.fromML(c.cov()), scala.reflect.ClassTag..MODULE$.apply(Matrix.class));
         Data data = new Data(weights, mus, sigmas);
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(GaussianMixtureModelWriter.class.getClassLoader());

         final class $typecreator1$3 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.clustering.GaussianMixtureModel.GaussianMixtureModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.clustering.GaussianMixtureModel.GaussianMixtureModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$3() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$3())).write().parquet(dataPath);
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

      public GaussianMixtureModelWriter(final GaussianMixtureModel instance) {
         this.instance = instance;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class Data implements Product, Serializable {
         private final double[] weights;
         private final org.apache.spark.mllib.linalg.Vector[] mus;
         private final Matrix[] sigmas;
         // $FF: synthetic field
         public final GaussianMixtureModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public double[] weights() {
            return this.weights;
         }

         public org.apache.spark.mllib.linalg.Vector[] mus() {
            return this.mus;
         }

         public Matrix[] sigmas() {
            return this.sigmas;
         }

         public Data copy(final double[] weights, final org.apache.spark.mllib.linalg.Vector[] mus, final Matrix[] sigmas) {
            return this.org$apache$spark$ml$clustering$GaussianMixtureModel$GaussianMixtureModelWriter$Data$$$outer().new Data(weights, mus, sigmas);
         }

         public double[] copy$default$1() {
            return this.weights();
         }

         public org.apache.spark.mllib.linalg.Vector[] copy$default$2() {
            return this.mus();
         }

         public Matrix[] copy$default$3() {
            return this.sigmas();
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
                  return this.weights();
               }
               case 1 -> {
                  return this.mus();
               }
               case 2 -> {
                  return this.sigmas();
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
                  return "weights";
               }
               case 1 -> {
                  return "mus";
               }
               case 2 -> {
                  return "sigmas";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label45: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$clustering$GaussianMixtureModel$GaussianMixtureModelWriter$Data$$$outer() == this.org$apache$spark$ml$clustering$GaussianMixtureModel$GaussianMixtureModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.weights() == var4.weights() && this.mus() == var4.mus() && this.sigmas() == var4.sigmas() && var4.canEqual(this)) {
                        break label45;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public GaussianMixtureModelWriter org$apache$spark$ml$clustering$GaussianMixtureModel$GaussianMixtureModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final double[] weights, final org.apache.spark.mllib.linalg.Vector[] mus, final Matrix[] sigmas) {
            this.weights = weights;
            this.mus = mus;
            this.sigmas = sigmas;
            if (GaussianMixtureModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = GaussianMixtureModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final GaussianMixtureModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final double[] weights, final org.apache.spark.mllib.linalg.Vector[] mus, final Matrix[] sigmas) {
            return this.$outer.new Data(weights, mus, sigmas);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.weights(), x$0.mus(), x$0.sigmas())));
         }

         public Data$() {
            if (GaussianMixtureModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = GaussianMixtureModelWriter.this;
               super();
            }
         }
      }
   }

   private static class GaussianMixtureModelReader extends MLReader {
      private final String className = GaussianMixtureModel.class.getName();

      private String className() {
         return this.className;
      }

      public GaussianMixtureModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row row = (Row)this.sparkSession().read().parquet(dataPath).select("weights", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"mus", "sigmas"}))).head();
         double[] weights = (double[])row.getSeq(0).toArray(scala.reflect.ClassTag..MODULE$.Double());
         org.apache.spark.mllib.linalg.Vector[] mus = (org.apache.spark.mllib.linalg.Vector[])row.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.apply(org.apache.spark.mllib.linalg.Vector.class));
         Matrix[] sigmas = (Matrix[])row.getSeq(2).toArray(scala.reflect.ClassTag..MODULE$.apply(Matrix.class));
         scala.Predef..MODULE$.require(mus.length == sigmas.length, () -> "Length of Mu and Sigma array must match");
         scala.Predef..MODULE$.require(mus.length == weights.length, () -> "Length of weight and Gaussian array must match");
         MultivariateGaussian[] gaussians = (MultivariateGaussian[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps(mus), scala.Predef..MODULE$.wrapRefArray(sigmas))), (x0$1) -> {
            if (x0$1 != null) {
               org.apache.spark.mllib.linalg.Vector mu = (org.apache.spark.mllib.linalg.Vector)x0$1._1();
               Matrix sigma = (Matrix)x0$1._2();
               return new MultivariateGaussian(mu.asML(), sigma.asML());
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.apply(MultivariateGaussian.class));
         GaussianMixtureModel model = new GaussianMixtureModel(metadata.uid(), weights, gaussians);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public GaussianMixtureModelReader() {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
