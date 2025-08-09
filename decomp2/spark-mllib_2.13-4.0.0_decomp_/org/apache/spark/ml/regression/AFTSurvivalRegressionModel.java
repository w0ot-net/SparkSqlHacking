package org.apache.spark.ml.regression;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleArrayParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasAggregationDepth;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxBlockSizeInMB;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
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
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r-d\u0001\u0002#F\u0001AC\u0001\"\u001a\u0001\u0003\u0006\u0004%\tE\u001a\u0005\t{\u0002\u0011\t\u0011)A\u0005O\"Iq\u0010\u0001BC\u0002\u0013\u0005\u0011\u0011\u0001\u0005\n\u0003\u0013\u0001!\u0011!Q\u0001\nUC!\"!\u0004\u0001\u0005\u000b\u0007I\u0011AA\b\u0011)\tY\u0002\u0001B\u0001B\u0003%\u0011\u0011\u0003\u0005\u000b\u0003?\u0001!Q1A\u0005\u0002\u0005=\u0001BCA\u0012\u0001\t\u0005\t\u0015!\u0003\u0002\u0012!A\u0011q\u0005\u0001\u0005\u0002\u001d\u000bI\u0003\u0003\u0005\u0002(\u0001!\taRA\u001e\u0011\u001d\ti\u0004\u0001C!\u0003\u007fAq!!\u0014\u0001\t\u0003\ty\u0005C\u0004\u0002`\u0001!\t!!\u0019\t\u0017\u0005\u001d\u0004\u00011AA\u0002\u0013%\u0011\u0011\u0001\u0005\f\u0003S\u0002\u0001\u0019!a\u0001\n\u0013\tY\u0007\u0003\u0006\u0002x\u0001\u0001\r\u0011!Q!\nUC\u0001\"!\u001f\u0001\t\u0003:\u00151\u0010\u0005\b\u0003G\u0003A\u0011BAS\u0011\u001d\tY\u000b\u0001C\u0001\u0003[Cq!!.\u0001\t\u0003\t9\fC\u0004\u0002>\u0002!\t%a0\t\u000f\u0005e\b\u0001\"\u0011\u0002|\"9!q\u0002\u0001\u0005B\tE\u0001b\u0002B\u0010\u0001\u0011\u0005#\u0011\u0005\u0005\b\u0005W\u0001A\u0011\tB\u0017\u000f\u001d\u0011\u0019$\u0012E\u0001\u0005k1a\u0001R#\t\u0002\t]\u0002bBA\u00147\u0011\u0005!Q\u000b\u0005\b\u0005/ZB\u0011\tB-\u0011\u001d\u0011\u0019g\u0007C!\u0005K2qA!\u001c\u001c\u0001m\u0011y\u0007C\u0005\u0003~}\u0011\t\u0011)A\u00057\"9\u0011qE\u0010\u0005\u0002\t}dA\u0002BD?\u0011\u0013I\tC\u0005\u0000E\tU\r\u0011\"\u0001\u0002\u0002!I\u0011\u0011\u0002\u0012\u0003\u0012\u0003\u0006I!\u0016\u0005\u000b\u0003\u001b\u0011#Q3A\u0005\u0002\u0005=\u0001BCA\u000eE\tE\t\u0015!\u0003\u0002\u0012!Q\u0011q\u0004\u0012\u0003\u0016\u0004%\t!a\u0004\t\u0015\u0005\r\"E!E!\u0002\u0013\t\t\u0002C\u0004\u0002(\t\"\tAa(\t\u0013\t=!%!A\u0005\u0002\t-\u0006\"\u0003BZEE\u0005I\u0011\u0001B[\u0011%\u0011IMII\u0001\n\u0003\u0011Y\rC\u0005\u0003P\n\n\n\u0011\"\u0001\u0003L\"I!\u0011\u001b\u0012\u0002\u0002\u0013\u0005#1\u001b\u0005\n\u0005?\u0014\u0013\u0011!C\u0001\u0003\u007fA\u0011B!9#\u0003\u0003%\tAa9\t\u0013\t\u001d(%!A\u0005B\t%\b\"\u0003B|E\u0005\u0005I\u0011\u0001B}\u0011%\u0019\u0019AIA\u0001\n\u0003\u001a)\u0001C\u0005\u0004\n\t\n\t\u0011\"\u0011\u0004\f!I!1\u0006\u0012\u0002\u0002\u0013\u00053Q\u0002\u0005\n\u0007\u001f\u0011\u0013\u0011!C!\u0007#9\u0011b!\u0006 \u0003\u0003EIaa\u0006\u0007\u0013\t\u001du$!A\t\n\re\u0001bBA\u0014q\u0011\u00051q\u0005\u0005\n\u0005WA\u0014\u0011!C#\u0007\u001bA\u0011b!\u000b9\u0003\u0003%\tia\u000b\t\u0013\rM\u0002(!A\u0005\u0002\u000eU\u0002bBB$?\u0011E3\u0011\n\u0004\u0007\u0007\u001bZBaa\u0014\t\u000f\u0005\u001db\b\"\u0001\u0004R!I1Q\u000b C\u0002\u0013%!1\u001b\u0005\t\u0007/r\u0004\u0015!\u0003\u0003V\"9!1\r \u0005B\re\u0003\"CB/7\u0005\u0005I\u0011BB0\u0005i\te\tV*veZLg/\u00197SK\u001e\u0014Xm]:j_:lu\u000eZ3m\u0015\t1u)\u0001\u0006sK\u001e\u0014Xm]:j_:T!\u0001S%\u0002\u00055d'B\u0001&L\u0003\u0015\u0019\b/\u0019:l\u0015\taU*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001d\u0006\u0019qN]4\u0004\u0001M!\u0001!\u0015/`!\u0011\u00116+V.\u000e\u0003\u0015K!\u0001V#\u0003\u001fI+wM]3tg&|g.T8eK2\u0004\"AV-\u000e\u0003]S!\u0001W$\u0002\r1Lg.\u00197h\u0013\tQvK\u0001\u0004WK\u000e$xN\u001d\t\u0003%\u0002\u0001\"AU/\n\u0005y+%aG!G)N+(O^5wC2\u0014Vm\u001a:fgNLwN\u001c)be\u0006l7\u000f\u0005\u0002aG6\t\u0011M\u0003\u0002c\u000f\u0006!Q\u000f^5m\u0013\t!\u0017M\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u00059\u0007C\u00015r\u001d\tIw\u000e\u0005\u0002k[6\t1N\u0003\u0002m\u001f\u00061AH]8pizR\u0011A\\\u0001\u0006g\u000e\fG.Y\u0005\u0003a6\fa\u0001\u0015:fI\u00164\u0017B\u0001:t\u0005\u0019\u0019FO]5oO*\u0011\u0001/\u001c\u0015\u0004\u0003U\\\bC\u0001<z\u001b\u00059(B\u0001=J\u0003)\tgN\\8uCRLwN\\\u0005\u0003u^\u0014QaU5oG\u0016\f\u0013\u0001`\u0001\u0006c92d\u0006M\u0001\u0005k&$\u0007\u0005K\u0002\u0003kn\fAbY8fM\u001aL7-[3oiN,\u0012!\u0016\u0015\u0005\u0007U\f)!\t\u0002\u0002\b\u0005)!G\f\u0019/a\u0005i1m\\3gM&\u001c\u0017.\u001a8ug\u0002BC\u0001B;\u0002\u0006\u0005I\u0011N\u001c;fe\u000e,\u0007\u000f^\u000b\u0003\u0003#\u0001B!a\u0005\u0002\u00165\tQ.C\u0002\u0002\u00185\u0014a\u0001R8vE2,\u0007fA\u0003vw\u0006Q\u0011N\u001c;fe\u000e,\u0007\u000f\u001e\u0011)\u0007\u0019)80A\u0003tG\u0006dW\rK\u0002\bkn\faa]2bY\u0016\u0004\u0003f\u0001\u0005vw\u00061A(\u001b8jiz\"\u0012bWA\u0016\u0003_\t\u0019$a\u000e\t\u000b\u0015L\u0001\u0019A4)\t\u0005-Ro\u001f\u0005\u0006\u007f&\u0001\r!\u0016\u0015\u0006\u0003_)\u0018Q\u0001\u0005\b\u0003\u001bI\u0001\u0019AA\tQ\u0011\t\u0019$^>\t\u000f\u0005}\u0011\u00021\u0001\u0002\u0012!\"\u0011qG;|)\u0005Y\u0016a\u00038v[\u001a+\u0017\r^;sKN,\"!!\u0011\u0011\t\u0005M\u00111I\u0005\u0004\u0003\u000bj'aA%oi\"\"1\"^A%C\t\tY%A\u00034]Ar\u0003'\u0001\rtKR\fV/\u00198uS2,\u0007K]8cC\nLG.\u001b;jKN$B!!\u0015\u0002T5\t\u0001\u0001C\u0004\u0002V1\u0001\r!a\u0016\u0002\u000bY\fG.^3\u0011\r\u0005M\u0011\u0011LA\t\u0013\r\tY&\u001c\u0002\u0006\u0003J\u0014\u0018-\u001f\u0015\u0004\u0019U\\\u0018aD:fiF+\u0018M\u001c;jY\u0016\u001c8i\u001c7\u0015\t\u0005E\u00131\r\u0005\u0007\u0003+j\u0001\u0019A4)\u00075)80\u0001\u0006`cV\fg\u000e^5mKN\fabX9vC:$\u0018\u000e\\3t?\u0012*\u0017\u000f\u0006\u0003\u0002n\u0005M\u0004\u0003BA\n\u0003_J1!!\u001dn\u0005\u0011)f.\u001b;\t\u0011\u0005Ut\"!AA\u0002U\u000b1\u0001\u001f\u00132\u0003-y\u0016/^1oi&dWm\u001d\u0011\u0002\u001b=t\u0007+\u0019:b[\u000eC\u0017M\\4f)\u0011\ti'! \t\u000f\u0005}\u0014\u00031\u0001\u0002\u0002\u0006)\u0001/\u0019:b[B\"\u00111QAI!\u0019\t))!#\u0002\u000e6\u0011\u0011q\u0011\u0006\u0004\u0003\u007f:\u0015\u0002BAF\u0003\u000f\u0013Q\u0001U1sC6\u0004B!a$\u0002\u00122\u0001A\u0001DAJ\u0003{\n\t\u0011!A\u0003\u0002\u0005U%aA0%eE!\u0011qSAO!\u0011\t\u0019\"!'\n\u0007\u0005mUNA\u0004O_RD\u0017N\\4\u0011\t\u0005M\u0011qT\u0005\u0004\u0003Ck'aA!os\u0006\u0001B.Y7cI\u0006\u0014\u0014+^1oi&dWm\u001d\u000b\u0004+\u0006\u001d\u0006bBAU%\u0001\u0007\u0011\u0011C\u0001\u0007Y\u0006l'\rZ1\u0002!A\u0014X\rZ5diF+\u0018M\u001c;jY\u0016\u001cHcA+\u00020\"1\u0011\u0011W\nA\u0002U\u000b\u0001BZ3biV\u0014Xm\u001d\u0015\u0005'U\f)!A\u0004qe\u0016$\u0017n\u0019;\u0015\t\u0005E\u0011\u0011\u0018\u0005\u0007\u0003c#\u0002\u0019A+)\tQ)\u0018QA\u0001\niJ\fgn\u001d4pe6$B!!1\u0002dB!\u00111YAo\u001d\u0011\t)-a6\u000f\t\u0005\u001d\u00171\u001b\b\u0005\u0003\u0013\f\tN\u0004\u0003\u0002L\u0006=gb\u00016\u0002N&\ta*\u0003\u0002M\u001b&\u0011!jS\u0005\u0004\u0003+L\u0015aA:rY&!\u0011\u0011\\An\u0003\u001d\u0001\u0018mY6bO\u0016T1!!6J\u0013\u0011\ty.!9\u0003\u0013\u0011\u000bG/\u0019$sC6,'\u0002BAm\u00037Dq!!:\u0016\u0001\u0004\t9/A\u0004eCR\f7/\u001a;1\t\u0005%\u00181\u001f\t\u0007\u0003W\fi/!=\u000e\u0005\u0005m\u0017\u0002BAx\u00037\u0014q\u0001R1uCN,G\u000f\u0005\u0003\u0002\u0010\u0006MH\u0001DA{\u0003G\f\t\u0011!A\u0003\u0002\u0005U%aA0%g!\"Q#^A\u0003\u0003=!(/\u00198tM>\u0014XnU2iK6\fG\u0003BA\u007f\u0005\u0013\u0001B!a@\u0003\u00065\u0011!\u0011\u0001\u0006\u0005\u0005\u0007\tY.A\u0003usB,7/\u0003\u0003\u0003\b\t\u0005!AC*ueV\u001cG\u000fV=qK\"9!1\u0002\fA\u0002\u0005u\u0018AB:dQ\u0016l\u0017\rK\u0002\u0017kn\fAaY8qsR\u00191La\u0005\t\u000f\tUq\u00031\u0001\u0003\u0018\u0005)Q\r\u001f;sCB!\u0011Q\u0011B\r\u0013\u0011\u0011Y\"a\"\u0003\u0011A\u000b'/Y7NCBD3aF;|\u0003\u00159(/\u001b;f+\t\u0011\u0019\u0003E\u0002a\u0005KI1Aa\nb\u0005!iEj\u0016:ji\u0016\u0014\bf\u0001\rvw\u0006AAo\\*ue&tw\rF\u0001hQ\u0011IR/!\u0013)\u0007\u0001)80\u0001\u000eB\rR\u001bVO\u001d<jm\u0006d'+Z4sKN\u001c\u0018n\u001c8N_\u0012,G\u000e\u0005\u0002S7M91D!\u000f\u0003@\t\u0015\u0003\u0003BA\n\u0005wI1A!\u0010n\u0005\u0019\te.\u001f*fMB!\u0001M!\u0011\\\u0013\r\u0011\u0019%\u0019\u0002\u000b\u001b2\u0013V-\u00193bE2,\u0007\u0003\u0002B$\u0005#j!A!\u0013\u000b\t\t-#QJ\u0001\u0003S>T!Aa\u0014\u0002\t)\fg/Y\u0005\u0005\u0005'\u0012IE\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u00036\u0005!!/Z1e+\t\u0011Y\u0006\u0005\u0003a\u0005;Z\u0016b\u0001B0C\nAQ\n\u0014*fC\u0012,'\u000fK\u0002\u001ekn\fA\u0001\\8bIR\u00191La\u001a\t\r\t%d\u00041\u0001h\u0003\u0011\u0001\u0018\r\u001e5)\u0007y)8P\u0001\u0011B\rR\u001bVO\u001d<jm\u0006d'+Z4sKN\u001c\u0018n\u001c8N_\u0012,Gn\u0016:ji\u0016\u00148#B\u0010\u0003$\tE\u0004\u0003\u0002B:\u0005sj!A!\u001e\u000b\u0007\t]\u0014*\u0001\u0005j]R,'O\\1m\u0013\u0011\u0011YH!\u001e\u0003\u000f1{wmZ5oO\u0006A\u0011N\\:uC:\u001cW\r\u0006\u0003\u0003\u0002\n\u0015\u0005c\u0001BB?5\t1\u0004\u0003\u0004\u0003~\u0005\u0002\ra\u0017\u0002\u0005\t\u0006$\u0018mE\u0004#\u0005s\u0011YI!%\u0011\t\u0005M!QR\u0005\u0004\u0005\u001fk'a\u0002)s_\u0012,8\r\u001e\t\u0005\u0005'\u0013YJ\u0004\u0003\u0003\u0016\neeb\u00016\u0003\u0018&\ta.C\u0002\u0002Z6LAAa\u0015\u0003\u001e*\u0019\u0011\u0011\\7\u0015\u0011\t\u0005&Q\u0015BT\u0005S\u00032Aa)#\u001b\u0005y\u0002\"B@*\u0001\u0004)\u0006bBA\u0007S\u0001\u0007\u0011\u0011\u0003\u0005\b\u0003?I\u0003\u0019AA\t)!\u0011\tK!,\u00030\nE\u0006bB@+!\u0003\u0005\r!\u0016\u0005\n\u0003\u001bQ\u0003\u0013!a\u0001\u0003#A\u0011\"a\b+!\u0003\u0005\r!!\u0005\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011!q\u0017\u0016\u0004+\ne6F\u0001B^!\u0011\u0011iL!2\u000e\u0005\t}&\u0002\u0002Ba\u0005\u0007\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0005al\u0017\u0002\u0002Bd\u0005\u007f\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII*\"A!4+\t\u0005E!\u0011X\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00134\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!Q\u001b\t\u0005\u0005/\u0014i.\u0004\u0002\u0003Z*!!1\u001cB'\u0003\u0011a\u0017M\\4\n\u0007I\u0014I.\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005u%Q\u001d\u0005\n\u0003k\u0002\u0014\u0011!a\u0001\u0003\u0003\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005W\u0004bA!<\u0003t\u0006uUB\u0001Bx\u0015\r\u0011\t0\\\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002B{\u0005_\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!1`B\u0001!\u0011\t\u0019B!@\n\u0007\t}XNA\u0004C_>dW-\u00198\t\u0013\u0005U$'!AA\u0002\u0005u\u0015A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BA!6\u0004\b!I\u0011QO\u001a\u0002\u0002\u0003\u0007\u0011\u0011I\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011\u0011\t\u000b\u0003\u0005+\fa!Z9vC2\u001cH\u0003\u0002B~\u0007'A\u0011\"!\u001e7\u0003\u0003\u0005\r!!(\u0002\t\u0011\u000bG/\u0019\t\u0004\u0005GC4#\u0002\u001d\u0004\u001c\t\u0015\u0003cCB\u000f\u0007G)\u0016\u0011CA\t\u0005Ck!aa\b\u000b\u0007\r\u0005R.A\u0004sk:$\u0018.\\3\n\t\r\u00152q\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cDCAB\f\u0003\u0015\t\u0007\u000f\u001d7z)!\u0011\tk!\f\u00040\rE\u0002\"B@<\u0001\u0004)\u0006bBA\u0007w\u0001\u0007\u0011\u0011\u0003\u0005\b\u0003?Y\u0004\u0019AA\t\u0003\u001d)h.\u00199qYf$Baa\u000e\u0004DA1\u00111CB\u001d\u0007{I1aa\u000fn\u0005\u0019y\u0005\u000f^5p]BI\u00111CB +\u0006E\u0011\u0011C\u0005\u0004\u0007\u0003j'A\u0002+va2,7\u0007C\u0005\u0004Fq\n\t\u00111\u0001\u0003\"\u0006\u0019\u0001\u0010\n\u0019\u0002\u0011M\fg/Z%na2$B!!\u001c\u0004L!1!\u0011N\u001fA\u0002\u001d\u0014\u0001%\u0011$U'V\u0014h/\u001b<bYJ+wM]3tg&|g.T8eK2\u0014V-\u00193feN\u0019aHa\u0017\u0015\u0005\rM\u0003c\u0001BB}\u0005I1\r\\1tg:\u000bW.Z\u0001\u000bG2\f7o\u001d(b[\u0016\u0004CcA.\u0004\\!1!\u0011\u000e\"A\u0002\u001d\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"a!\u0019\u0011\t\t]71M\u0005\u0005\u0007K\u0012IN\u0001\u0004PE*,7\r\u001e\u0015\u00047U\\\bf\u0001\u000evw\u0002"
)
public class AFTSurvivalRegressionModel extends RegressionModel implements AFTSurvivalRegressionParams, MLWritable {
   private final String uid;
   private final Vector coefficients;
   private final double intercept;
   private final double scale;
   private Vector _quantiles;
   private Param censorCol;
   private DoubleArrayParam quantileProbabilities;
   private Param quantilesCol;
   private DoubleParam maxBlockSizeInMB;
   private IntParam aggregationDepth;
   private BooleanParam fitIntercept;
   private DoubleParam tol;
   private IntParam maxIter;

   public static AFTSurvivalRegressionModel load(final String path) {
      return AFTSurvivalRegressionModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return AFTSurvivalRegressionModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getCensorCol() {
      return AFTSurvivalRegressionParams.getCensorCol$(this);
   }

   public double[] getQuantileProbabilities() {
      return AFTSurvivalRegressionParams.getQuantileProbabilities$(this);
   }

   public String getQuantilesCol() {
      return AFTSurvivalRegressionParams.getQuantilesCol$(this);
   }

   public boolean hasQuantilesCol() {
      return AFTSurvivalRegressionParams.hasQuantilesCol$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean fitting) {
      return AFTSurvivalRegressionParams.validateAndTransformSchema$(this, schema, fitting);
   }

   public final double getMaxBlockSizeInMB() {
      return HasMaxBlockSizeInMB.getMaxBlockSizeInMB$(this);
   }

   public final int getAggregationDepth() {
      return HasAggregationDepth.getAggregationDepth$(this);
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

   public final Param censorCol() {
      return this.censorCol;
   }

   public final DoubleArrayParam quantileProbabilities() {
      return this.quantileProbabilities;
   }

   public final Param quantilesCol() {
      return this.quantilesCol;
   }

   public final void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$censorCol_$eq(final Param x$1) {
      this.censorCol = x$1;
   }

   public final void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantileProbabilities_$eq(final DoubleArrayParam x$1) {
      this.quantileProbabilities = x$1;
   }

   public final void org$apache$spark$ml$regression$AFTSurvivalRegressionParams$_setter_$quantilesCol_$eq(final Param x$1) {
      this.quantilesCol = x$1;
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

   public String uid() {
      return this.uid;
   }

   public Vector coefficients() {
      return this.coefficients;
   }

   public double intercept() {
      return this.intercept;
   }

   public double scale() {
      return this.scale;
   }

   public int numFeatures() {
      return this.coefficients().size();
   }

   public AFTSurvivalRegressionModel setQuantileProbabilities(final double[] value) {
      return (AFTSurvivalRegressionModel)this.set(this.quantileProbabilities(), value);
   }

   public AFTSurvivalRegressionModel setQuantilesCol(final String value) {
      return (AFTSurvivalRegressionModel)this.set(this.quantilesCol(), value);
   }

   private Vector _quantiles() {
      return this._quantiles;
   }

   private void _quantiles_$eq(final Vector x$1) {
      this._quantiles = x$1;
   }

   public void onParamChange(final Param param) {
      String var10000 = param.name();
      String var2 = "quantileProbabilities";
      if (var10000 == null) {
         if (var2 != null) {
            return;
         }
      } else if (!var10000.equals(var2)) {
         return;
      }

      if (this.isDefined(this.quantileProbabilities())) {
         this._quantiles_$eq(.MODULE$.dense((double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.doubleArrayOps((double[])this.$(this.quantileProbabilities())), (JFunction1.mcDD.sp)(q) -> scala.math.package..MODULE$.exp(scala.math.package..MODULE$.log(-scala.math.package..MODULE$.log1p(-q)) * this.scale()), scala.reflect.ClassTag..MODULE$.Double())));
      } else {
         this._quantiles_$eq((Vector)null);
      }
   }

   private Vector lambda2Quantiles(final double lambda) {
      Vector quantiles = this._quantiles().copy();
      org.apache.spark.ml.linalg.BLAS..MODULE$.scal(lambda, quantiles);
      return quantiles;
   }

   public Vector predictQuantiles(final Vector features) {
      double lambda = this.predict(features);
      return this.lambda2Quantiles(lambda);
   }

   public double predict(final Vector features) {
      return scala.math.package..MODULE$.exp(org.apache.spark.ml.linalg.BLAS..MODULE$.dot(this.coefficients(), features) + this.intercept());
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      Seq predictionColNames = (Seq)scala.package..MODULE$.Seq().empty();
      Seq predictionColumns = (Seq)scala.package..MODULE$.Seq().empty();
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         functions var10000 = org.apache.spark.sql.functions..MODULE$;
         Function1 var10001 = (features) -> BoxesRunTime.boxToDouble($anonfun$transform$1(this, features));
         TypeTags.TypeTag var10002 = ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(AFTSurvivalRegressionModel.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
            }

            public $typecreator1$1() {
            }
         }

         Column predCol = var10000.udf(var10001, var10002, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))})));
         predictionColNames = (Seq)predictionColNames.$colon$plus(this.$(this.predictionCol()));
         predictionColumns = (Seq)predictionColumns.$colon$plus(predCol.as((String)this.$(this.predictionCol()), outputSchema.apply((String)this.$(this.predictionCol())).metadata()));
      }

      if (this.hasQuantilesCol()) {
         Column var16;
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
            functions var15 = org.apache.spark.sql.functions..MODULE$;
            Function1 var18 = (lambda) -> $anonfun$transform$2(this, BoxesRunTime.unboxToDouble(lambda));
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(AFTSurvivalRegressionModel.class.getClassLoader());

            final class $typecreator2$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator2$1() {
               }
            }

            var16 = var15.udf(var18, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1()), ((TypeTags)scala.reflect.runtime.package..MODULE$.universe()).TypeTag().Double()).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{(Column)predictionColumns.head()})));
         } else {
            functions var17 = org.apache.spark.sql.functions..MODULE$;
            Function1 var19 = (features) -> this.predictQuantiles(features);
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(AFTSurvivalRegressionModel.class.getClassLoader());

            final class $typecreator3$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator3$1() {
               }
            }

            TypeTags.TypeTag var20 = ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1());
            JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
            JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(AFTSurvivalRegressionModel.class.getClassLoader());

            final class $typecreator4$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("org.apache.spark.ml.linalg.Vector").asType().toTypeConstructor();
               }

               public $typecreator4$1() {
               }
            }

            var16 = var17.udf(var19, var20, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1())).apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.col((String)this.$(this.featuresCol()))})));
         }

         Column quanCol = var16;
         predictionColNames = (Seq)predictionColNames.$colon$plus(this.$(this.quantilesCol()));
         predictionColumns = (Seq)predictionColumns.$colon$plus(quanCol.as((String)this.$(this.quantilesCol()), outputSchema.apply((String)this.$(this.quantilesCol())).metadata()));
      }

      if (predictionColNames.nonEmpty()) {
         return dataset.withColumns(predictionColNames, predictionColumns);
      } else {
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": AFTSurvivalRegressionModel.transform() "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UUID..MODULE$, this.uid())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"does nothing because no output columns were set."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return dataset.toDF();
      }
   }

   public StructType transformSchema(final StructType schema) {
      StructType outputSchema = this.validateAndTransformSchema(schema, false);
      if (scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.predictionCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateNumeric(outputSchema, (String)this.$(this.predictionCol()));
      }

      if (this.isDefined(this.quantilesCol()) && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString((String)this.$(this.quantilesCol())))) {
         outputSchema = SchemaUtils$.MODULE$.updateAttributeGroupSize(outputSchema, (String)this.$(this.quantilesCol()), ((double[])this.$(this.quantileProbabilities())).length);
      }

      return outputSchema;
   }

   public AFTSurvivalRegressionModel copy(final ParamMap extra) {
      return (AFTSurvivalRegressionModel)((Model)this.copyValues(new AFTSurvivalRegressionModel(this.uid(), this.coefficients(), this.intercept(), this.scale()), extra)).setParent(this.parent());
   }

   public MLWriter write() {
      return new AFTSurvivalRegressionModelWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "AFTSurvivalRegressionModel: uid=" + var10000 + ", numFeatures=" + this.numFeatures();
   }

   // $FF: synthetic method
   public static final double $anonfun$transform$1(final AFTSurvivalRegressionModel $this, final Vector features) {
      return $this.predict(features);
   }

   // $FF: synthetic method
   public static final Vector $anonfun$transform$2(final AFTSurvivalRegressionModel $this, final double lambda) {
      return $this.lambda2Quantiles(lambda);
   }

   public AFTSurvivalRegressionModel(final String uid, final Vector coefficients, final double intercept, final double scale) {
      this.uid = uid;
      this.coefficients = coefficients;
      this.intercept = intercept;
      this.scale = scale;
      HasMaxIter.$init$(this);
      HasTol.$init$(this);
      HasFitIntercept.$init$(this);
      HasAggregationDepth.$init$(this);
      HasMaxBlockSizeInMB.$init$(this);
      AFTSurvivalRegressionParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }

   public AFTSurvivalRegressionModel() {
      this("", .MODULE$.empty(), Double.NaN, Double.NaN);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class AFTSurvivalRegressionModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final AFTSurvivalRegressionModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(this.instance.coefficients(), this.instance.intercept(), this.instance.scale());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(AFTSurvivalRegressionModelWriter.class.getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.regression.AFTSurvivalRegressionModel.AFTSurvivalRegressionModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.regression.AFTSurvivalRegressionModel.AFTSurvivalRegressionModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
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

      public AFTSurvivalRegressionModelWriter(final AFTSurvivalRegressionModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector coefficients;
         private final double intercept;
         private final double scale;
         // $FF: synthetic field
         public final AFTSurvivalRegressionModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector coefficients() {
            return this.coefficients;
         }

         public double intercept() {
            return this.intercept;
         }

         public double scale() {
            return this.scale;
         }

         public Data copy(final Vector coefficients, final double intercept, final double scale) {
            return this.org$apache$spark$ml$regression$AFTSurvivalRegressionModel$AFTSurvivalRegressionModelWriter$Data$$$outer().new Data(coefficients, intercept, scale);
         }

         public Vector copy$default$1() {
            return this.coefficients();
         }

         public double copy$default$2() {
            return this.intercept();
         }

         public double copy$default$3() {
            return this.scale();
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
                  return this.coefficients();
               }
               case 1 -> {
                  return BoxesRunTime.boxToDouble(this.intercept());
               }
               case 2 -> {
                  return BoxesRunTime.boxToDouble(this.scale());
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
               case 2 -> {
                  return "scale";
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
            var1 = Statics.mix(var1, Statics.doubleHash(this.scale()));
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label60: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$regression$AFTSurvivalRegressionModel$AFTSurvivalRegressionModelWriter$Data$$$outer() == this.org$apache$spark$ml$regression$AFTSurvivalRegressionModel$AFTSurvivalRegressionModelWriter$Data$$$outer()) {
                     Data var4 = (Data)x$1;
                     if (this.intercept() == var4.intercept() && this.scale() == var4.scale()) {
                        label50: {
                           Vector var10000 = this.coefficients();
                           Vector var5 = var4.coefficients();
                           if (var10000 == null) {
                              if (var5 != null) {
                                 break label50;
                              }
                           } else if (!var10000.equals(var5)) {
                              break label50;
                           }

                           if (var4.canEqual(this)) {
                              break label60;
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
         public AFTSurvivalRegressionModelWriter org$apache$spark$ml$regression$AFTSurvivalRegressionModel$AFTSurvivalRegressionModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector coefficients, final double intercept, final double scale) {
            this.coefficients = coefficients;
            this.intercept = intercept;
            this.scale = scale;
            if (AFTSurvivalRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = AFTSurvivalRegressionModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final AFTSurvivalRegressionModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector coefficients, final double intercept, final double scale) {
            return this.$outer.new Data(coefficients, intercept, scale);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.coefficients(), BoxesRunTime.boxToDouble(x$0.intercept()), BoxesRunTime.boxToDouble(x$0.scale()))));
         }

         public Data$() {
            if (AFTSurvivalRegressionModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = AFTSurvivalRegressionModelWriter.this;
               super();
            }
         }
      }
   }

   private static class AFTSurvivalRegressionModelReader extends MLReader {
      private final String className = AFTSurvivalRegressionModel.class.getName();

      private String className() {
         return this.className;
      }

      public AFTSurvivalRegressionModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Dataset data = this.sparkSession().read().parquet(dataPath);
         Row var7 = (Row)MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"coefficients"}))).select("coefficients", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"intercept", "scale"}))).head();
         if (var7 != null) {
            Some var8 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var7);
            if (!var8.isEmpty() && var8.get() != null && ((SeqOps)var8.get()).lengthCompare(3) == 0) {
               Object coefficients = ((SeqOps)var8.get()).apply(0);
               Object intercept = ((SeqOps)var8.get()).apply(1);
               Object scale = ((SeqOps)var8.get()).apply(2);
               if (coefficients instanceof Vector) {
                  Vector var12 = (Vector)coefficients;
                  if (intercept instanceof Double) {
                     double var13 = BoxesRunTime.unboxToDouble(intercept);
                     if (scale instanceof Double) {
                        double var15 = BoxesRunTime.unboxToDouble(scale);
                        Tuple3 var6 = new Tuple3(var12, BoxesRunTime.boxToDouble(var13), BoxesRunTime.boxToDouble(var15));
                        Vector coefficients = (Vector)var6._1();
                        double intercept = BoxesRunTime.unboxToDouble(var6._2());
                        double scale = BoxesRunTime.unboxToDouble(var6._3());
                        AFTSurvivalRegressionModel model = new AFTSurvivalRegressionModel(metadata.uid(), coefficients, intercept, scale);
                        metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
                        return model;
                     }
                  }
               }
            }
         }

         throw new MatchError(var7);
      }

      public AFTSurvivalRegressionModelReader() {
      }
   }
}
