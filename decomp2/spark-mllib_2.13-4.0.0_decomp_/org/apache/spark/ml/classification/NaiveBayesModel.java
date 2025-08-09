package org.apache.spark.ml.classification;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.Matrix;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasWeightCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.json4s.DefaultFormats;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005c\u0001\u0002&L\u0001YC\u0001b\u001b\u0001\u0003\u0006\u0004%\t\u0005\u001c\u0005\n\u0003\u000f\u0001!\u0011!Q\u0001\n5D!\"a\u0003\u0001\u0005\u000b\u0007I\u0011AA\u0007\u0011%\t)\u0002\u0001B\u0001B\u0003%1\f\u0003\u0006\u0002\u001a\u0001\u0011)\u0019!C\u0001\u00037A!\"!\n\u0001\u0005\u0003\u0005\u000b\u0011BA\u000f\u0011)\tI\u0003\u0001BC\u0002\u0013\u0005\u00111\u0004\u0005\u000b\u0003c\u0001!\u0011!Q\u0001\n\u0005u\u0001\u0002CA\u001b\u0001\u0011\u0005Q*a\u000e\t\u0011\u0005U\u0002\u0001\"\u0001N\u0003\u0013B!\"a\u0013\u0001\u0001\u0004%\taTA'\u0011)\ti\u0006\u0001a\u0001\n\u0003y\u0015q\f\u0005\t\u0003W\u0002\u0001\u0015)\u0003\u0002P!A\u0011Q\u000e\u0001\u0005\u0002=\u000by\u0007\u0003\u0006\u0002x\u0001A)\u0019!C\u0005\u00037A!\"!!\u0001\u0011\u000b\u0007I\u0011BAB\u0011)\ti\t\u0001EC\u0002\u0013%\u0011Q\n\u0005\n\u0003#\u0003!\u0019!C!\u0003'C\u0001\"!)\u0001A\u0003%\u0011Q\u0013\u0005\n\u0003K\u0003!\u0019!C!\u0003'C\u0001\"!+\u0001A\u0003%\u0011Q\u0013\u0005\b\u0003[\u0003A\u0011BAX\u0011\u001d\t)\f\u0001C\u0005\u0003oCq!a/\u0001\t\u0013\ti\fC\u0004\u0002B\u0002!I!a1\t\u0015\u0005\u001d\u0007\u0001#b\u0001\n\u0013\tI\rC\u0004\u0002T\u0002!\t%!6\t\u000f\u0005m\u0007\u0001\"\u0015\u0002^\"9\u00111\u001d\u0001\u0005B\u0005\u0015\bbBA}\u0001\u0011\u0005\u00131 \u0005\b\u0003\u007f\u0004A\u0011\tB\u0001\u000f\u001d\u0011ia\u0013E\u0001\u0005\u001f1aAS&\t\u0002\tE\u0001bBA\u001bC\u0011\u0005!q\u0006\u0005\b\u0005c\tC\u0011\tB\u001a\u0011\u001d\u0011i$\tC!\u0005\u007f1qAa\u0012\"\u0001\u0005\u0012I\u0005C\u0005\u0003L\u0015\u0012\t\u0011)A\u0005C\"9\u0011QG\u0013\u0005\u0002\t5cA\u0002B+K\u0011\u00139\u0006\u0003\u0006\u0002\f!\u0012)\u001a!C\u0001\u0003\u001bA\u0011\"!\u0006)\u0005#\u0005\u000b\u0011B.\t\u0015\u0005e\u0001F!f\u0001\n\u0003\tY\u0002\u0003\u0006\u0002&!\u0012\t\u0012)A\u0005\u0003;A!\"!\u000b)\u0005+\u0007I\u0011AA\u000e\u0011)\t\t\u0004\u000bB\tB\u0003%\u0011Q\u0004\u0005\b\u0003kAC\u0011\u0001B8\u0011%\t\u0019\u000fKA\u0001\n\u0003\u0011Y\bC\u0005\u0003\u0004\"\n\n\u0011\"\u0001\u0003\u0006\"I!\u0011\u0014\u0015\u0012\u0002\u0013\u0005!1\u0014\u0005\n\u0005?C\u0013\u0013!C\u0001\u00057C\u0011B!))\u0003\u0003%\tEa)\t\u0013\t=\u0006&!A\u0005\u0002\u0005M\u0005\"\u0003BYQ\u0005\u0005I\u0011\u0001BZ\u0011%\u0011i\fKA\u0001\n\u0003\u0012y\fC\u0005\u0003N\"\n\t\u0011\"\u0001\u0003P\"I!\u0011\u001c\u0015\u0002\u0002\u0013\u0005#1\u001c\u0005\n\u0005?D\u0013\u0011!C!\u0005CD\u0011\"!?)\u0003\u0003%\tEa9\t\u0013\t\u0015\b&!A\u0005B\t\u001dx!\u0003BvK\u0005\u0005\t\u0012\u0002Bw\r%\u0011)&JA\u0001\u0012\u0013\u0011y\u000fC\u0004\u00026y\"\tA!@\t\u0013\u0005eh(!A\u0005F\t\r\b\"\u0003B\u0000}\u0005\u0005I\u0011QB\u0001\u0011%\u0019IAPA\u0001\n\u0003\u001bY\u0001C\u0004\u0004\u001e\u0015\"\tfa\b\u0007\r\r\r\u0012\u0005BB\u0013\u0011\u001d\t)\u0004\u0012C\u0001\u0007OA\u0011ba\u000bE\u0005\u0004%IAa)\t\u0011\r5B\t)A\u0005\u0005KCqA!\u0010E\t\u0003\u001ay\u0003C\u0005\u00044\u0005\n\t\u0011\"\u0003\u00046\tya*Y5wK\n\u000b\u00170Z:N_\u0012,GN\u0003\u0002M\u001b\u0006q1\r\\1tg&4\u0017nY1uS>t'B\u0001(P\u0003\tiGN\u0003\u0002Q#\u0006)1\u000f]1sW*\u0011!kU\u0001\u0007CB\f7\r[3\u000b\u0003Q\u000b1a\u001c:h\u0007\u0001\u0019B\u0001A,cKB!\u0001,W.b\u001b\u0005Y\u0015B\u0001.L\u0005\u0001\u0002&o\u001c2bE&d\u0017n\u001d;jG\u000ec\u0017m]:jM&\u001c\u0017\r^5p]6{G-\u001a7\u0011\u0005q{V\"A/\u000b\u0005yk\u0015A\u00027j]\u0006dw-\u0003\u0002a;\n1a+Z2u_J\u0004\"\u0001\u0017\u0001\u0011\u0005a\u001b\u0017B\u00013L\u0005Aq\u0015-\u001b<f\u0005\u0006LXm\u001d)be\u0006l7\u000f\u0005\u0002gS6\tqM\u0003\u0002i\u001b\u0006!Q\u000f^5m\u0013\tQwM\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005i\u0007C\u00018x\u001d\tyW\u000f\u0005\u0002qg6\t\u0011O\u0003\u0002s+\u00061AH]8pizR\u0011\u0001^\u0001\u0006g\u000e\fG.Y\u0005\u0003mN\fa\u0001\u0015:fI\u00164\u0017B\u0001=z\u0005\u0019\u0019FO]5oO*\u0011ao\u001d\u0015\u0005\u0003m\f\u0019\u0001\u0005\u0002}\u007f6\tQP\u0003\u0002\u007f\u001f\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0007\u0005\u0005QPA\u0003TS:\u001cW-\t\u0002\u0002\u0006\u0005)\u0011GL\u001b/a\u0005!Q/\u001b3!Q\u0011\u001110a\u0001\u0002\u0005ALW#A.)\t\rY\u0018\u0011C\u0011\u0003\u0003'\tQA\r\u00181]A\n1\u0001]5!Q\u0011!10!\u0005\u0002\u000bQDW\r^1\u0016\u0005\u0005u\u0001c\u0001/\u0002 %\u0019\u0011\u0011E/\u0003\r5\u000bGO]5yQ\u0011)10!\u0005\u0002\rQDW\r^1!Q\u0011110!\u0005\u0002\u000bMLw-\\1)\t\u001dY\u0018QF\u0011\u0003\u0003_\tQa\r\u00181]A\naa]5h[\u0006\u0004\u0003\u0006\u0002\u0005|\u0003[\ta\u0001P5oSRtD#C1\u0002:\u0005u\u0012\u0011IA#\u0011\u0015Y\u0017\u00021\u0001nQ\u0015\tId_A\u0002\u0011\u0019\tY!\u0003a\u00017\"*\u0011QH>\u0002\u0012!9\u0011\u0011D\u0005A\u0002\u0005u\u0001&BA!w\u0006E\u0001bBA\u0015\u0013\u0001\u0007\u0011Q\u0004\u0015\u0006\u0003\u000bZ\u0018Q\u0006\u000b\u0002C\u0006Iq\u000e\u001c3MC\n,Gn]\u000b\u0003\u0003\u001f\u0002b!!\u0015\u0002T\u0005]S\"A:\n\u0007\u0005U3OA\u0003BeJ\f\u0017\u0010\u0005\u0003\u0002R\u0005e\u0013bAA.g\n1Ai\\;cY\u0016\fQb\u001c7e\u0019\u0006\u0014W\r\\:`I\u0015\fH\u0003BA1\u0003O\u0002B!!\u0015\u0002d%\u0019\u0011QM:\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003Sb\u0011\u0011!a\u0001\u0003\u001f\n1\u0001\u001f\u00132\u0003)yG\u000e\u001a'bE\u0016d7\u000fI\u0001\rg\u0016$x\n\u001c3MC\n,Gn\u001d\u000b\u0005\u0003c\n\u0019(D\u0001\u0001\u0011\u001d\t)H\u0004a\u0001\u0003\u001f\na\u0001\\1cK2\u001c\u0018A\u0005;iKR\fW*\u001b8vg:+w\r\u00165fi\u0006D3aDA>!\u0011\t\t&! \n\u0007\u0005}4OA\u0005ue\u0006t7/[3oi\u0006y\u0001/['j]V\u001cH\u000b[3uCN+X.\u0006\u0002\u0002\u0006B\u0019A,a\"\n\u0007\u0005%ULA\u0006EK:\u001cXMV3di>\u0014\bf\u0001\t\u0002|\u0005IAn\\4WCJ\u001cV/\u001c\u0015\u0004#\u0005m\u0014a\u00038v[\u001a+\u0017\r^;sKN,\"!!&\u0011\t\u0005E\u0013qS\u0005\u0004\u00033\u001b(aA%oi\"\"!c_AOC\t\ty*A\u00032]Yr\u0003'\u0001\u0007ok64U-\u0019;ve\u0016\u001c\b\u0005\u000b\u0003\u0014w\u0006u\u0015A\u00038v[\u000ec\u0017m]:fg\"\"Ac_A\u0002\u0003-qW/\\\"mCN\u001cXm\u001d\u0011)\tUY\u00181A\u0001\u0017[VdG/\u001b8p[&\fGnQ1mGVd\u0017\r^5p]R!\u0011QQAY\u0011\u0019\t\u0019L\u0006a\u00017\u0006Aa-Z1ukJ,7/A\u000bd_6\u0004H.Z7f]R\u001c\u0015\r\\2vY\u0006$\u0018n\u001c8\u0015\u0007m\u000bI\f\u0003\u0004\u00024^\u0001\raW\u0001\u0015E\u0016\u0014hn\\;mY&\u001c\u0015\r\\2vY\u0006$\u0018n\u001c8\u0015\t\u0005\u0015\u0015q\u0018\u0005\u0007\u0003gC\u0002\u0019A.\u0002'\u001d\fWo]:jC:\u001c\u0015\r\\2vY\u0006$\u0018n\u001c8\u0015\u0007m\u000b)\r\u0003\u0004\u00024f\u0001\raW\u0001\u000faJ,G-[2u%\u0006<h)\u001e8d+\t\tY\r\u0005\u0004\u0002R\u000557lW\u0005\u0004\u0003\u001f\u001c(!\u0003$v]\u000e$\u0018n\u001c82Q\rQ\u00121P\u0001\u000baJ,G-[2u%\u0006<HcA.\u0002X\"1\u00111W\u000eA\u0002mCCaG>\u0002.\u00051\"/Y<3aJ|'-\u00192jY&$\u00180\u00138QY\u0006\u001cW\rF\u0002\\\u0003?Da!!9\u001d\u0001\u0004Y\u0016!\u0004:boB\u0013X\rZ5di&|g.\u0001\u0003d_BLHcA1\u0002h\"9\u0011\u0011^\u000fA\u0002\u0005-\u0018!B3yiJ\f\u0007\u0003BAw\u0003gl!!a<\u000b\u0007\u0005EX*A\u0003qCJ\fW.\u0003\u0003\u0002v\u0006=(\u0001\u0003)be\u0006lW*\u00199)\tuY\u00181A\u0001\ti>\u001cFO]5oOR\tQ\u000e\u000b\u0003\u001fw\u0006\r\u0011!B<sSR,WC\u0001B\u0002!\r1'QA\u0005\u0004\u0005\u000f9'\u0001C'M/JLG/\u001a:)\t}Y\u0018Q\u0014\u0015\u0005\u0001m\f\u0019!A\bOC&4XMQ1zKNlu\u000eZ3m!\tA\u0016eE\u0004\"\u0005'\u0011IBa\b\u0011\t\u0005E#QC\u0005\u0004\u0005/\u0019(AB!osJ+g\r\u0005\u0003g\u00057\t\u0017b\u0001B\u000fO\nQQ\n\u0014*fC\u0012\f'\r\\3\u0011\t\t\u0005\"1F\u0007\u0003\u0005GQAA!\n\u0003(\u0005\u0011\u0011n\u001c\u0006\u0003\u0005S\tAA[1wC&!!Q\u0006B\u0012\u00051\u0019VM]5bY&T\u0018M\u00197f)\t\u0011y!\u0001\u0003sK\u0006$WC\u0001B\u001b!\u00111'qG1\n\u0007\terM\u0001\u0005N\u0019J+\u0017\rZ3sQ\u0011\u001930!(\u0002\t1|\u0017\r\u001a\u000b\u0004C\n\u0005\u0003B\u0002B\"I\u0001\u0007Q.\u0001\u0003qCRD\u0007\u0006\u0002\u0013|\u0003;\u0013QCT1jm\u0016\u0014\u0015-_3t\u001b>$W\r\\,sSR,'oE\u0002&\u0005\u0007\t\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u0005\u001f\u0012\u0019\u0006E\u0002\u0003R\u0015j\u0011!\t\u0005\u0007\u0005\u0017:\u0003\u0019A1\u0003\t\u0011\u000bG/Y\n\bQ\tM!\u0011\fB0!\u0011\t\tFa\u0017\n\u0007\tu3OA\u0004Qe>$Wo\u0019;\u0011\t\t\u0005$1\u000e\b\u0005\u0005G\u00129GD\u0002q\u0005KJ\u0011\u0001^\u0005\u0004\u0005S\u001a\u0018a\u00029bG.\fw-Z\u0005\u0005\u0005[\u0011iGC\u0002\u0003jM$\u0002B!\u001d\u0003v\t]$\u0011\u0010\t\u0004\u0005gBS\"A\u0013\t\r\u0005-q\u00061\u0001\\\u0011\u001d\tIb\fa\u0001\u0003;Aq!!\u000b0\u0001\u0004\ti\u0002\u0006\u0005\u0003r\tu$q\u0010BA\u0011!\tY\u0001\rI\u0001\u0002\u0004Y\u0006\"CA\raA\u0005\t\u0019AA\u000f\u0011%\tI\u0003\rI\u0001\u0002\u0004\ti\"\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\t\u001d%fA.\u0003\n.\u0012!1\u0012\t\u0005\u0005\u001b\u0013)*\u0004\u0002\u0003\u0010*!!\u0011\u0013BJ\u0003%)hn\u00195fG.,GM\u0003\u0002\u007fg&!!q\u0013BH\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\u0011iJ\u000b\u0003\u0002\u001e\t%\u0015AD2paf$C-\u001a4bk2$HeM\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t\u0015\u0006\u0003\u0002BT\u0005[k!A!+\u000b\t\t-&qE\u0001\u0005Y\u0006tw-C\u0002y\u0005S\u000bA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u00036\nm\u0006\u0003BA)\u0005oK1A!/t\u0005\r\te.\u001f\u0005\n\u0003S2\u0014\u0011!a\u0001\u0003+\u000bq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005\u0003\u0004bAa1\u0003J\nUVB\u0001Bc\u0015\r\u00119m]\u0001\u000bG>dG.Z2uS>t\u0017\u0002\u0002Bf\u0005\u000b\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!\u0011\u001bBl!\u0011\t\tFa5\n\u0007\tU7OA\u0004C_>dW-\u00198\t\u0013\u0005%\u0004(!AA\u0002\tU\u0016A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BA!*\u0003^\"I\u0011\u0011N\u001d\u0002\u0002\u0003\u0007\u0011QS\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u0011Q\u0013\u000b\u0003\u0005K\u000ba!Z9vC2\u001cH\u0003\u0002Bi\u0005SD\u0011\"!\u001b=\u0003\u0003\u0005\rA!.\u0002\t\u0011\u000bG/\u0019\t\u0004\u0005gr4#\u0002 \u0003r\n}\u0001c\u0003Bz\u0005s\\\u0016QDA\u000f\u0005cj!A!>\u000b\u0007\t]8/A\u0004sk:$\u0018.\\3\n\t\tm(Q\u001f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001cDC\u0001Bw\u0003\u0015\t\u0007\u000f\u001d7z)!\u0011\tha\u0001\u0004\u0006\r\u001d\u0001BBA\u0006\u0003\u0002\u00071\fC\u0004\u0002\u001a\u0005\u0003\r!!\b\t\u000f\u0005%\u0012\t1\u0001\u0002\u001e\u00059QO\\1qa2LH\u0003BB\u0007\u00073\u0001b!!\u0015\u0004\u0010\rM\u0011bAB\tg\n1q\n\u001d;j_:\u0004\u0012\"!\u0015\u0004\u0016m\u000bi\"!\b\n\u0007\r]1O\u0001\u0004UkBdWm\r\u0005\n\u00077\u0011\u0015\u0011!a\u0001\u0005c\n1\u0001\u001f\u00131\u0003!\u0019\u0018M^3J[BdG\u0003BA1\u0007CAaAa\u0011D\u0001\u0004i'!\u0006(bSZ,')Y=fg6{G-\u001a7SK\u0006$WM]\n\u0004\t\nUBCAB\u0015!\r\u0011\t\u0006R\u0001\nG2\f7o\u001d(b[\u0016\f!b\u00197bgNt\u0015-\\3!)\r\t7\u0011\u0007\u0005\u0007\u0005\u0007B\u0005\u0019A7\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r]\u0002\u0003\u0002BT\u0007sIAaa\u000f\u0003*\n1qJ\u00196fGRDC!I>\u0002\u001e\"\"\u0001e_AO\u0001"
)
public class NaiveBayesModel extends ProbabilisticClassificationModel implements NaiveBayesParams, MLWritable {
   private transient Matrix thetaMinusNegTheta;
   private transient DenseVector piMinusThetaSum;
   private transient double[] logVarSum;
   private transient Function1 predictRawFunc;
   private final String uid;
   private final Vector pi;
   private final Matrix theta;
   private final Matrix sigma;
   private double[] oldLabels;
   private final int numFeatures;
   private final int numClasses;
   private DoubleParam smoothing;
   private Param modelType;
   private Param weightCol;
   private transient volatile byte bitmap$trans$0;

   public static NaiveBayesModel load(final String path) {
      return NaiveBayesModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return NaiveBayesModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final double getSmoothing() {
      return NaiveBayesParams.getSmoothing$(this);
   }

   public final String getModelType() {
      return NaiveBayesParams.getModelType$(this);
   }

   public final String getWeightCol() {
      return HasWeightCol.getWeightCol$(this);
   }

   public final DoubleParam smoothing() {
      return this.smoothing;
   }

   public final Param modelType() {
      return this.modelType;
   }

   public final void org$apache$spark$ml$classification$NaiveBayesParams$_setter_$smoothing_$eq(final DoubleParam x$1) {
      this.smoothing = x$1;
   }

   public final void org$apache$spark$ml$classification$NaiveBayesParams$_setter_$modelType_$eq(final Param x$1) {
      this.modelType = x$1;
   }

   public final Param weightCol() {
      return this.weightCol;
   }

   public final void org$apache$spark$ml$param$shared$HasWeightCol$_setter_$weightCol_$eq(final Param x$1) {
      this.weightCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public Vector pi() {
      return this.pi;
   }

   public Matrix theta() {
      return this.theta;
   }

   public Matrix sigma() {
      return this.sigma;
   }

   public double[] oldLabels() {
      return this.oldLabels;
   }

   public void oldLabels_$eq(final double[] x$1) {
      this.oldLabels = x$1;
   }

   public NaiveBayesModel setOldLabels(final double[] labels) {
      this.oldLabels_$eq(labels);
      return this;
   }

   private Matrix thetaMinusNegTheta$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            label55: {
               String var3 = (String)this.$(this.modelType());
               String var10001 = NaiveBayes$.MODULE$.Bernoulli();
               if (var10001 == null) {
                  if (var3 == null) {
                     break label55;
                  }
               } else if (var10001.equals(var3)) {
                  break label55;
               }

               throw new IllegalArgumentException("Invalid modelType: " + this.$(this.modelType()) + ". Variables thetaMinusNegTheta should only be precomputed in Bernoulli NB.");
            }

            this.thetaMinusNegTheta = this.theta().map((JFunction1.mcDD.sp)(value) -> value - .MODULE$.log1p(-.MODULE$.exp(value)));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return this.thetaMinusNegTheta;
   }

   private Matrix thetaMinusNegTheta() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.thetaMinusNegTheta$lzycompute() : this.thetaMinusNegTheta;
   }

   private DenseVector piMinusThetaSum$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            label55: {
               String var3 = (String)this.$(this.modelType());
               String var10001 = NaiveBayes$.MODULE$.Bernoulli();
               if (var10001 == null) {
                  if (var3 == null) {
                     break label55;
                  }
               } else if (var10001.equals(var3)) {
                  break label55;
               }

               throw new IllegalArgumentException("Invalid modelType: " + this.$(this.modelType()) + ". Variables piMinusThetaSum should only be precomputed in Bernoulli NB.");
            }

            Matrix negTheta = this.theta().map((JFunction1.mcDD.sp)(value) -> .MODULE$.log1p(-.MODULE$.exp(value)));
            DenseVector ones = new DenseVector((double[])scala.Array..MODULE$.fill(this.theta().numCols(), (JFunction0.mcD.sp)() -> (double)1.0F, scala.reflect.ClassTag..MODULE$.Double()));
            DenseVector piMinusThetaSum = this.pi().toDense().copy();
            org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, negTheta, ones, (double)1.0F, piMinusThetaSum);
            this.piMinusThetaSum = piMinusThetaSum;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var9) {
         throw var9;
      }

      return this.piMinusThetaSum;
   }

   private DenseVector piMinusThetaSum() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.piMinusThetaSum$lzycompute() : this.piMinusThetaSum;
   }

   private double[] logVarSum$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            label55: {
               String var3 = (String)this.$(this.modelType());
               String var10001 = NaiveBayes$.MODULE$.Gaussian();
               if (var10001 == null) {
                  if (var3 == null) {
                     break label55;
                  }
               } else if (var10001.equals(var3)) {
                  break label55;
               }

               throw new IllegalArgumentException("Invalid modelType: " + this.$(this.modelType()) + ". Variables logVarSum should only be precomputed in Gaussian NB.");
            }

            this.logVarSum = (double[])scala.Array..MODULE$.tabulate(this.numClasses(), (JFunction1.mcDI.sp)(i) -> BoxesRunTime.unboxToDouble(scala.package..MODULE$.Iterator().range(0, this.numFeatures()).map((JFunction1.mcDI.sp)(j) -> .MODULE$.log(this.sigma().apply(i, j))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)), scala.reflect.ClassTag..MODULE$.Double());
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var6) {
         throw var6;
      }

      return this.logVarSum;
   }

   private double[] logVarSum() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.logVarSum$lzycompute() : this.logVarSum;
   }

   public int numFeatures() {
      return this.numFeatures;
   }

   public int numClasses() {
      return this.numClasses;
   }

   private DenseVector multinomialCalculation(final Vector features) {
      NaiveBayes$.MODULE$.requireNonnegativeValues(features);
      DenseVector prob = this.pi().toDense().copy();
      org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, this.theta(), features, (double)1.0F, prob);
      return prob;
   }

   private Vector complementCalculation(final Vector features) {
      NaiveBayes$.MODULE$.requireNonnegativeValues(features);
      double[] probArray = this.theta().multiply(features).toArray();
      double max = BoxesRunTime.unboxToDouble(scala.Predef..MODULE$.wrapDoubleArray(probArray).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
      double sumExp = (double)0.0F;

      for(int j = 0; j < probArray.length; ++j) {
         sumExp += .MODULE$.exp(probArray[j] - max);
      }

      double logSumExp = .MODULE$.log(sumExp) + max;

      for(int var11 = 0; var11 < probArray.length; ++var11) {
         probArray[var11] -= logSumExp;
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(probArray);
   }

   private DenseVector bernoulliCalculation(final Vector features) {
      NaiveBayes$.MODULE$.requireZeroOneBernoulliValues(features);
      DenseVector prob = this.piMinusThetaSum().copy();
      org.apache.spark.ml.linalg.BLAS..MODULE$.gemv((double)1.0F, this.thetaMinusNegTheta(), features, (double)1.0F, prob);
      return prob;
   }

   private Vector gaussianCalculation(final Vector features) {
      double[] prob = (double[])scala.Array..MODULE$.ofDim(this.numClasses(), scala.reflect.ClassTag..MODULE$.Double());

      for(int i = 0; i < this.numClasses(); ++i) {
         double s = (double)0.0F;

         for(int j = 0; j < this.numFeatures(); ++j) {
            double d = features.apply(j) - this.theta().apply(i, j);
            s += d * d / this.sigma().apply(i, j);
         }

         prob[i] = this.pi().apply(i) - (s + this.logVarSum()[i]) / (double)2;
      }

      return org.apache.spark.ml.linalg.Vectors..MODULE$.dense(prob);
   }

   private Function1 predictRawFunc$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            Function1 var13;
            label132: {
               label141: {
                  String var3 = (String)this.$(this.modelType());
                  String var10001 = NaiveBayes$.MODULE$.Multinomial();
                  if (var10001 == null) {
                     if (var3 == null) {
                        break label141;
                     }
                  } else if (var10001.equals(var3)) {
                     break label141;
                  }

                  label142: {
                     var10001 = NaiveBayes$.MODULE$.Complement();
                     if (var10001 == null) {
                        if (var3 == null) {
                           break label142;
                        }
                     } else if (var10001.equals(var3)) {
                        break label142;
                     }

                     label143: {
                        var10001 = NaiveBayes$.MODULE$.Bernoulli();
                        if (var10001 == null) {
                           if (var3 == null) {
                              break label143;
                           }
                        } else if (var10001.equals(var3)) {
                           break label143;
                        }

                        var10001 = NaiveBayes$.MODULE$.Gaussian();
                        if (var10001 == null) {
                           if (var3 != null) {
                              throw new MatchError(var3);
                           }
                        } else if (!var10001.equals(var3)) {
                           throw new MatchError(var3);
                        }

                        var13 = (features) -> this.gaussianCalculation(features);
                        break label132;
                     }

                     var13 = (features) -> this.bernoulliCalculation(features);
                     break label132;
                  }

                  var13 = (features) -> this.complementCalculation(features);
                  break label132;
               }

               var13 = (features) -> this.multinomialCalculation(features);
            }

            this.predictRawFunc = var13;
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
            return this.predictRawFunc;
         }
      } catch (Throwable var9) {
         throw var9;
      }

      return this.predictRawFunc;
   }

   private Function1 predictRawFunc() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this.predictRawFunc$lzycompute() : this.predictRawFunc;
   }

   public Vector predictRaw(final Vector features) {
      return (Vector)this.predictRawFunc().apply(features);
   }

   public Vector raw2probabilityInPlace(final Vector rawPrediction) {
      if (rawPrediction instanceof DenseVector var4) {
         org.apache.spark.ml.impl.Utils..MODULE$.softmax(var4.values());
         return var4;
      } else if (rawPrediction instanceof SparseVector) {
         throw new RuntimeException("Unexpected error in NaiveBayesModel: raw2probabilityInPlace encountered SparseVector");
      } else {
         throw new MatchError(rawPrediction);
      }
   }

   public NaiveBayesModel copy(final ParamMap extra) {
      return (NaiveBayesModel)this.copyValues((new NaiveBayesModel(this.uid(), this.pi(), this.theta(), this.sigma())).setParent(this.parent()), extra);
   }

   public String toString() {
      String var10000 = this.uid();
      return "NaiveBayesModel: uid=" + var10000 + ", modelType=" + this.$(this.modelType()) + ", numClasses=" + this.numClasses() + ", numFeatures=" + this.numFeatures();
   }

   public MLWriter write() {
      return new NaiveBayesModelWriter(this);
   }

   public NaiveBayesModel(final String uid, final Vector pi, final Matrix theta, final Matrix sigma) {
      this.uid = uid;
      this.pi = pi;
      this.theta = theta;
      this.sigma = sigma;
      HasWeightCol.$init$(this);
      NaiveBayesParams.$init$(this);
      MLWritable.$init$(this);
      this.oldLabels = null;
      this.numFeatures = theta.numCols();
      this.numClasses = pi.size();
      Statics.releaseFence();
   }

   public NaiveBayesModel() {
      this("", org.apache.spark.ml.linalg.Vectors..MODULE$.empty(), org.apache.spark.ml.linalg.Matrices..MODULE$.empty(), org.apache.spark.ml.linalg.Matrices..MODULE$.empty());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class NaiveBayesModelWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final NaiveBayesModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         String dataPath;
         String var5;
         boolean var15;
         label81: {
            label84: {
               DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
               dataPath = (new Path(path, "data")).toString();
               var5 = this.instance.getModelType();
               String var10000 = NaiveBayes$.MODULE$.Multinomial();
               if (var10000 == null) {
                  if (var5 == null) {
                     break label84;
                  }
               } else if (var10000.equals(var5)) {
                  break label84;
               }

               label85: {
                  var10000 = NaiveBayes$.MODULE$.Bernoulli();
                  if (var10000 == null) {
                     if (var5 == null) {
                        break label85;
                     }
                  } else if (var10000.equals(var5)) {
                     break label85;
                  }

                  label66: {
                     var10000 = NaiveBayes$.MODULE$.Complement();
                     if (var10000 == null) {
                        if (var5 == null) {
                           break label66;
                        }
                     } else if (var10000.equals(var5)) {
                        break label66;
                     }

                     var15 = false;
                     break label81;
                  }

                  var15 = true;
                  break label81;
               }

               var15 = true;
               break label81;
            }

            var15 = true;
         }

         if (var15) {
            scala.Predef..MODULE$.require(this.instance.sigma().numRows() == 0 && this.instance.sigma().numCols() == 0);
            BoxedUnit var16 = BoxedUnit.UNIT;
         } else {
            String var17 = NaiveBayes$.MODULE$.Gaussian();
            if (var17 == null) {
               if (var5 != null) {
                  throw new MatchError(var5);
               }
            } else if (!var17.equals(var5)) {
               throw new MatchError(var5);
            }

            scala.Predef..MODULE$.require(this.instance.sigma().numRows() != 0 && this.instance.sigma().numCols() != 0);
            BoxedUnit var18 = BoxedUnit.UNIT;
         }

         Data data = new Data(this.instance.pi(), this.instance.theta(), this.instance.sigma());
         SparkSession var19 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(NaiveBayesModelWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.classification.NaiveBayesModel.NaiveBayesModelWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.classification.NaiveBayesModel.NaiveBayesModelWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var19.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
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

      public NaiveBayesModelWriter(final NaiveBayesModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Vector pi;
         private final Matrix theta;
         private final Matrix sigma;
         // $FF: synthetic field
         public final NaiveBayesModelWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Vector pi() {
            return this.pi;
         }

         public Matrix theta() {
            return this.theta;
         }

         public Matrix sigma() {
            return this.sigma;
         }

         public Data copy(final Vector pi, final Matrix theta, final Matrix sigma) {
            return this.org$apache$spark$ml$classification$NaiveBayesModel$NaiveBayesModelWriter$Data$$$outer().new Data(pi, theta, sigma);
         }

         public Vector copy$default$1() {
            return this.pi();
         }

         public Matrix copy$default$2() {
            return this.theta();
         }

         public Matrix copy$default$3() {
            return this.sigma();
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
                  return this.pi();
               }
               case 1 -> {
                  return this.theta();
               }
               case 2 -> {
                  return this.sigma();
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
                  return "pi";
               }
               case 1 -> {
                  return "theta";
               }
               case 2 -> {
                  return "sigma";
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
            boolean var10;
            if (this != x$1) {
               label68: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$classification$NaiveBayesModel$NaiveBayesModelWriter$Data$$$outer() == this.org$apache$spark$ml$classification$NaiveBayesModel$NaiveBayesModelWriter$Data$$$outer()) {
                     label58: {
                        Data var4 = (Data)x$1;
                        Vector var10000 = this.pi();
                        Vector var5 = var4.pi();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label58;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label58;
                        }

                        Matrix var8 = this.theta();
                        Matrix var6 = var4.theta();
                        if (var8 == null) {
                           if (var6 != null) {
                              break label58;
                           }
                        } else if (!var8.equals(var6)) {
                           break label58;
                        }

                        var8 = this.sigma();
                        Matrix var7 = var4.sigma();
                        if (var8 == null) {
                           if (var7 != null) {
                              break label58;
                           }
                        } else if (!var8.equals(var7)) {
                           break label58;
                        }

                        if (var4.canEqual(this)) {
                           break label68;
                        }
                     }
                  }

                  var10 = false;
                  return var10;
               }
            }

            var10 = true;
            return var10;
         }

         // $FF: synthetic method
         public NaiveBayesModelWriter org$apache$spark$ml$classification$NaiveBayesModel$NaiveBayesModelWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Vector pi, final Matrix theta, final Matrix sigma) {
            this.pi = pi;
            this.theta = theta;
            this.sigma = sigma;
            if (NaiveBayesModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = NaiveBayesModelWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final NaiveBayesModelWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Vector pi, final Matrix theta, final Matrix sigma) {
            return this.$outer.new Data(pi, theta, sigma);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.pi(), x$0.theta(), x$0.sigma())));
         }

         public Data$() {
            if (NaiveBayesModelWriter.this == null) {
               throw null;
            } else {
               this.$outer = NaiveBayesModelWriter.this;
               super();
            }
         }
      }
   }

   private static class NaiveBayesModelReader extends MLReader {
      private final String className = NaiveBayesModel.class.getName();

      private String className() {
         return this.className;
      }

      public NaiveBayesModel load(final String path) {
         DefaultFormats format = org.json4s.DefaultFormats..MODULE$;
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         Tuple2 var8 = org.apache.spark.util.VersionUtils..MODULE$.majorMinorVersion(metadata.sparkVersion());
         if (var8 == null) {
            throw new MatchError(var8);
         } else {
            int major = var8._1$mcI$sp();
            int minor = var8._2$mcI$sp();
            Tuple2.mcII.sp var7 = new Tuple2.mcII.sp(major, minor);
            int major = ((Tuple2)var7)._1$mcI$sp();
            int var12 = ((Tuple2)var7)._2$mcI$sp();
            String dataPath = (new Path(path, "data")).toString();
            Dataset data = this.sparkSession().read().parquet(dataPath);
            Dataset vecConverted = MLUtils$.MODULE$.convertVectorColumnsToML(data, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"pi"})));
            NaiveBayesModel var10000;
            if (major < 3) {
               Row var18 = (Row)MLUtils$.MODULE$.convertMatrixColumnsToML(vecConverted, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"theta"}))).select("pi", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"theta"}))).head();
               if (var18 == null) {
                  throw new MatchError(var18);
               }

               Some var19 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var18);
               if (var19.isEmpty() || var19.get() == null || ((SeqOps)var19.get()).lengthCompare(2) != 0) {
                  throw new MatchError(var18);
               }

               Object pi = ((SeqOps)var19.get()).apply(0);
               Object theta = ((SeqOps)var19.get()).apply(1);
               if (!(pi instanceof Vector)) {
                  throw new MatchError(var18);
               }

               Vector var22 = (Vector)pi;
               if (!(theta instanceof Matrix)) {
                  throw new MatchError(var18);
               }

               Matrix var23 = (Matrix)theta;
               Tuple2 var17 = new Tuple2(var22, var23);
               Vector pi = (Vector)var17._1();
               Matrix theta = (Matrix)var17._2();
               var10000 = new NaiveBayesModel(metadata.uid(), pi, theta, org.apache.spark.ml.linalg.Matrices..MODULE$.zeros(0, 0));
            } else {
               Row var27 = (Row)MLUtils$.MODULE$.convertMatrixColumnsToML(vecConverted, (Seq)scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"theta", "sigma"}))).select("pi", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"theta", "sigma"}))).head();
               if (var27 == null) {
                  throw new MatchError(var27);
               }

               Some var28 = org.apache.spark.sql.Row..MODULE$.unapplySeq(var27);
               if (var28.isEmpty() || var28.get() == null || ((SeqOps)var28.get()).lengthCompare(3) != 0) {
                  throw new MatchError(var27);
               }

               Object pi = ((SeqOps)var28.get()).apply(0);
               Object theta = ((SeqOps)var28.get()).apply(1);
               Object sigma = ((SeqOps)var28.get()).apply(2);
               if (!(pi instanceof Vector)) {
                  throw new MatchError(var27);
               }

               Vector var32 = (Vector)pi;
               if (!(theta instanceof Matrix)) {
                  throw new MatchError(var27);
               }

               Matrix var33 = (Matrix)theta;
               if (!(sigma instanceof Matrix)) {
                  throw new MatchError(var27);
               }

               Matrix var34 = (Matrix)sigma;
               Tuple3 var26 = new Tuple3(var32, var33, var34);
               Vector pi = (Vector)var26._1();
               Matrix theta = (Matrix)var26._2();
               Matrix sigma = (Matrix)var26._3();
               var10000 = new NaiveBayesModel(metadata.uid(), pi, theta, sigma);
            }

            NaiveBayesModel model = var10000;
            metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
            return model;
         }
      }

      public NaiveBayesModelReader() {
      }
   }
}
