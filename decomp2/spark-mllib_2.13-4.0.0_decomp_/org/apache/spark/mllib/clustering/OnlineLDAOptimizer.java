package org.apache.spark.mllib.clustering;

import breeze.linalg.DenseMatrix;
import breeze.linalg.DenseVector;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.package;
import breeze.stats.distributions.Gamma;
import breeze.stats.distributions.RandBasis;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Random;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Matrices$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\t-h\u0001\u0002&L\u0005YCQa\u001a\u0001\u0005\u0002!DqA\u001b\u0001A\u0002\u0013%1\u000eC\u0004p\u0001\u0001\u0007I\u0011\u00029\t\rY\u0004\u0001\u0015)\u0003m\u0011\u001d9\b\u00011A\u0005\naDq\u0001 \u0001A\u0002\u0013%Q\u0010\u0003\u0004\u0000\u0001\u0001\u0006K!\u001f\u0005\t\u0003\u0003\u0001\u0001\u0019!C\u0005W\"I\u00111\u0001\u0001A\u0002\u0013%\u0011Q\u0001\u0005\b\u0003\u0013\u0001\u0001\u0015)\u0003m\u0011%\tY\u0001\u0001a\u0001\n\u0013\ti\u0001C\u0005\u0002\u001c\u0001\u0001\r\u0011\"\u0003\u0002\u001e!A\u0011\u0011\u0005\u0001!B\u0013\ty\u0001\u0003\u0005\u0002$\u0001!\taSA\u0007\u0011%\t)\u0003\u0001a\u0001\n\u0013\t9\u0003C\u0005\u00020\u0001\u0001\r\u0011\"\u0003\u00022!A\u0011Q\u0007\u0001!B\u0013\tI\u0003\u0003\u0005\u00028\u0001!\taSA\u0014\u0011%\tI\u0004\u0001a\u0001\n\u0013\tY\u0004C\u0005\u0002N\u0001\u0001\r\u0011\"\u0003\u0002P!A\u00111\u000b\u0001!B\u0013\ti\u0004C\u0005\u0002V\u0001\u0001\r\u0011\"\u0003\u0002X!I\u0011q\f\u0001A\u0002\u0013%\u0011\u0011\r\u0005\t\u0003K\u0002\u0001\u0015)\u0003\u0002Z!I\u0011q\r\u0001A\u0002\u0013%\u0011q\u0005\u0005\n\u0003S\u0002\u0001\u0019!C\u0005\u0003WB\u0001\"a\u001c\u0001A\u0003&\u0011\u0011\u0006\u0005\n\u0003c\u0002\u0001\u0019!C\u0005\u0003OA\u0011\"a\u001d\u0001\u0001\u0004%I!!\u001e\t\u0011\u0005e\u0004\u0001)Q\u0005\u0003SA\u0011\"a\u001f\u0001\u0001\u0004%I!a\n\t\u0013\u0005u\u0004\u00011A\u0005\n\u0005}\u0004\u0002CAB\u0001\u0001\u0006K!!\u000b\t\u0013\u0005\u0015\u0005\u00011A\u0005\n\u0005]\u0003\"CAD\u0001\u0001\u0007I\u0011BAE\u0011!\ti\t\u0001Q!\n\u0005e\u0003\"CAH\u0001\u0001\u0007I\u0011BAI\u0011%\t)\u000b\u0001a\u0001\n\u0013\t9\u000b\u0003\u0005\u0002,\u0002\u0001\u000b\u0015BAJ\u0011%\ti\u000b\u0001a\u0001\n\u0013\ty\u000bC\u0005\u0002@\u0002\u0001\r\u0011\"\u0003\u0002B\"A\u0011Q\u0019\u0001!B\u0013\t\t\f\u0003\u0005\u0002H\u0002!\taSAX\u0011!\tI\r\u0001a\u0001\n\u0013Y\u0007\"CAf\u0001\u0001\u0007I\u0011BAg\u0011\u001d\t\t\u000e\u0001Q!\n1D\u0011\"a5\u0001\u0001\u0004%I!a\n\t\u0013\u0005U\u0007\u00011A\u0005\n\u0005]\u0007\u0002CAn\u0001\u0001\u0006K!!\u000b\t\u000f\u0005u\u0007\u0001\"\u0001\u0002(!9\u0011\u0011\u001f\u0001\u0005\u0002\u0005M\bbBA~\u0001\u0011\u0005\u0011q\u0005\u0005\b\u0003\u007f\u0004A\u0011\u0001B\u0001\u0011\u001d\u00119\u0001\u0001C\u0001\u0003OAqAa\u0003\u0001\t\u0003\u0011i\u0001C\u0004\u0003\u0014\u0001!\t!a\u0016\t\u000f\tm\u0001\u0001\"\u0001\u0003\u001e!A!1\u0005\u0001\u0005\u0002-\u0013)\u0003\u0003\u0005\u0003*\u0001!\ta\u0013B\u0016\u0011!\u0011\t\u0004\u0001C\u0001\u0017\nM\u0002\u0002\u0003B\u001d\u0001\u0011\u00053Ja\u000f\t\u000f\t%\u0003\u0001\"\u0011LQ\"A!1\n\u0001\u0005\u0002-\u0013i\u0005C\u0004\u0003T\u0001!IA!\u0016\t\u000f\t}\u0003\u0001\"\u0003\u0003b!9!\u0011\u000f\u0001\u0005\n\tM\u0004b\u0002B;\u0001\u0011%!q\u000f\u0005\t\u0005\u0003\u0003A\u0011I&\u0003\u0004\u001eA!qS&\t\u0002=\u0013IJB\u0004K\u0017\"\u0005qJa'\t\r\u001d4E\u0011\u0001BO\u0011!\u0011yJ\u0012C\u0001\u001f\n\u0005\u0006\u0002\u0003BP\r\u0012\u00051Ja7\u0003%=sG.\u001b8f\u0019\u0012\u000bu\n\u001d;j[&TXM\u001d\u0006\u0003\u00196\u000b!b\u00197vgR,'/\u001b8h\u0015\tqu*A\u0003nY2L'M\u0003\u0002Q#\u0006)1\u000f]1sW*\u0011!kU\u0001\u0007CB\f7\r[3\u000b\u0003Q\u000b1a\u001c:h\u0007\u0001\u0019B\u0001A,^CB\u0011\u0001lW\u0007\u00023*\t!,A\u0003tG\u0006d\u0017-\u0003\u0002]3\n1\u0011I\\=SK\u001a\u0004\"AX0\u000e\u0003-K!\u0001Y&\u0003\u00191#\u0015i\u00149uS6L'0\u001a:\u0011\u0005\t,W\"A2\u000b\u0005\u0011|\u0015\u0001C5oi\u0016\u0014h.\u00197\n\u0005\u0019\u001c'a\u0002'pO\u001eLgnZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003%\u0004\"A\u0018\u0001\u0002\u0003-,\u0012\u0001\u001c\t\u000316L!A\\-\u0003\u0007%sG/A\u0003l?\u0012*\u0017\u000f\u0006\u0002riB\u0011\u0001L]\u0005\u0003gf\u0013A!\u00168ji\"9QoAA\u0001\u0002\u0004a\u0017a\u0001=%c\u0005\u00111\u000eI\u0001\u000bG>\u0014\b/^:TSj,W#A=\u0011\u0005aS\u0018BA>Z\u0005\u0011auN\\4\u0002\u001d\r|'\u000f];t'&TXm\u0018\u0013fcR\u0011\u0011O \u0005\bk\u001a\t\t\u00111\u0001z\u0003-\u0019wN\u001d9vgNK'0\u001a\u0011\u0002\u0013Y|7-\u00192TSj,\u0017!\u0004<pG\u0006\u00147+\u001b>f?\u0012*\u0017\u000fF\u0002r\u0003\u000fAq!^\u0005\u0002\u0002\u0003\u0007A.\u0001\u0006w_\u000e\f'mU5{K\u0002\nQ!\u00197qQ\u0006,\"!a\u0004\u0011\t\u0005E\u0011qC\u0007\u0003\u0003'Q1!!\u0006N\u0003\u0019a\u0017N\\1mO&!\u0011\u0011DA\n\u0005\u00191Vm\u0019;pe\u0006I\u0011\r\u001c9iC~#S-\u001d\u000b\u0004c\u0006}\u0001\u0002C;\r\u0003\u0003\u0005\r!a\u0004\u0002\r\u0005d\u0007\u000f[1!\u0003!9W\r^!ma\"\f\u0017aA3uCV\u0011\u0011\u0011\u0006\t\u00041\u0006-\u0012bAA\u00173\n1Ai\\;cY\u0016\fq!\u001a;b?\u0012*\u0017\u000fF\u0002r\u0003gA\u0001\"\u001e\t\u0002\u0002\u0003\u0007\u0011\u0011F\u0001\u0005KR\f\u0007%\u0001\u0004hKR,E/Y\u0001\u0010e\u0006tGm\\7HK:,'/\u0019;peV\u0011\u0011Q\b\t\u0005\u0003\u007f\tI%\u0004\u0002\u0002B)!\u00111IA#\u0003\u0011)H/\u001b7\u000b\u0005\u0005\u001d\u0013\u0001\u00026bm\u0006LA!a\u0013\u0002B\t1!+\u00198e_6\f1C]1oI>lw)\u001a8fe\u0006$xN]0%KF$2!]A)\u0011!)H#!AA\u0002\u0005u\u0012\u0001\u0005:b]\u0012|WnR3oKJ\fGo\u001c:!\u0003U\u0019\u0018-\u001c9mK^KG\u000f\u001b*fa2\f7-Z7f]R,\"!!\u0017\u0011\u0007a\u000bY&C\u0002\u0002^e\u0013qAQ8pY\u0016\fg.A\rtC6\u0004H.Z,ji\"\u0014V\r\u001d7bG\u0016lWM\u001c;`I\u0015\fHcA9\u0002d!AQoFA\u0001\u0002\u0004\tI&\u0001\ftC6\u0004H.Z,ji\"\u0014V\r\u001d7bG\u0016lWM\u001c;!\u0003\u0011!\u0018-\u001e\u0019\u0002\u0011Q\fW\u000fM0%KF$2!]A7\u0011!)($!AA\u0002\u0005%\u0012!\u0002;bkB\u0002\u0013!B6baB\f\u0017!C6baB\fw\fJ3r)\r\t\u0018q\u000f\u0005\tkv\t\t\u00111\u0001\u0002*\u000511.\u00199qC\u0002\n\u0011#\\5oS\n\u000bGo\u00195Ge\u0006\u001cG/[8o\u0003Ui\u0017N\\5CCR\u001c\u0007N\u0012:bGRLwN\\0%KF$2!]AA\u0011!)\b%!AA\u0002\u0005%\u0012AE7j]&\u0014\u0015\r^2i\rJ\f7\r^5p]\u0002\n\u0001d\u001c9uS6L'0\u001a#pG\u000e{gnY3oiJ\fG/[8o\u0003qy\u0007\u000f^5nSj,Gi\\2D_:\u001cWM\u001c;sCRLwN\\0%KF$2!]AF\u0011!)8%!AA\u0002\u0005e\u0013!G8qi&l\u0017N_3E_\u000e\u001cuN\\2f]R\u0014\u0018\r^5p]\u0002\nA\u0001Z8dgV\u0011\u00111\u0013\t\u0007\u0003+\u000bY*a(\u000e\u0005\u0005]%bAAM\u001f\u0006\u0019!\u000f\u001a3\n\t\u0005u\u0015q\u0013\u0002\u0004%\u0012#\u0005C\u0002-\u0002\"f\fy!C\u0002\u0002$f\u0013a\u0001V;qY\u0016\u0014\u0014\u0001\u00033pGN|F%Z9\u0015\u0007E\fI\u000b\u0003\u0005vM\u0005\u0005\t\u0019AAJ\u0003\u0015!wnY:!\u0003\u0019a\u0017-\u001c2eCV\u0011\u0011\u0011\u0017\t\u0007\u0003g\u000bY,!\u000b\u000e\u0005\u0005U&\u0002BA\u000b\u0003oS!!!/\u0002\r\t\u0014X-\u001a>f\u0013\u0011\ti,!.\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e_\u0001\u000bY\u0006l'\rZ1`I\u0015\fHcA9\u0002D\"AQ/KA\u0001\u0002\u0004\t\t,A\u0004mC6\u0014G-\u0019\u0011\u0002\u0013\u001d,G\u000fT1nE\u0012\f\u0017!C5uKJ\fG/[8o\u00035IG/\u001a:bi&|gn\u0018\u0013fcR\u0019\u0011/a4\t\u000fUl\u0013\u0011!a\u0001Y\u0006Q\u0011\u000e^3sCRLwN\u001c\u0011\u0002\u0015\u001d\fW.\\1TQ\u0006\u0004X-\u0001\bhC6l\u0017m\u00155ba\u0016|F%Z9\u0015\u0007E\fI\u000e\u0003\u0005va\u0005\u0005\t\u0019AA\u0015\u0003-9\u0017-\\7b'\"\f\u0007/\u001a\u0011\u0002\u000f\u001d,G\u000fV1va!*!'!9\u0002nB!\u00111]Au\u001b\t\t)OC\u0002\u0002h>\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\tY/!:\u0003\u000bMKgnY3\"\u0005\u0005=\u0018!B\u0019/i9\u0002\u0014aB:fiR\u000bW\u000f\r\u000b\u0005\u0003k\f90D\u0001\u0001\u0011\u001d\t9g\ra\u0001\u0003SASaMAq\u0003[\f\u0001bZ3u\u0017\u0006\u0004\b/\u0019\u0015\u0006i\u0005\u0005\u0018Q^\u0001\tg\u0016$8*\u00199qCR!\u0011Q\u001fB\u0002\u0011\u001d\t\t(\u000ea\u0001\u0003SAS!NAq\u0003[\fAcZ3u\u001b&t\u0017NQ1uG\"4%/Y2uS>t\u0007&\u0002\u001c\u0002b\u00065\u0018\u0001F:fi6Kg.\u001b\"bi\u000eDgI]1di&|g\u000e\u0006\u0003\u0002v\n=\u0001bBA>o\u0001\u0007\u0011\u0011\u0006\u0015\u0006o\u0005\u0005\u0018Q^\u0001\u001cO\u0016$x\n\u001d;j[&TX\rR8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8)\u000ba\n\tOa\u0006\"\u0005\te\u0011!B\u0019/k9\u0002\u0014aG:fi>\u0003H/[7ju\u0016$unY\"p]\u000e,g\u000e\u001e:bi&|g\u000e\u0006\u0003\u0002v\n}\u0001bBACs\u0001\u0007\u0011\u0011\f\u0015\u0006s\u0005\u0005(qC\u0001\ng\u0016$H*Y7cI\u0006$B!!>\u0003(!9\u0011Q\u0016\u001eA\u0002\u0005E\u0016!D:fi\u001e\u000bW.\\1TQ\u0006\u0004X\r\u0006\u0003\u0002v\n5\u0002b\u0002B\u0018w\u0001\u0007\u0011\u0011F\u0001\u0006g\"\f\u0007/Z\u0001\u0019g\u0016$8+Y7qY\u0016<\u0016\u000e\u001e5SKBd\u0017mY3nK:$H\u0003BA{\u0005kAqAa\u000e=\u0001\u0004\tI&A\u0004sKBd\u0017mY3\u0002\u0015%t\u0017\u000e^5bY&TX\rF\u0003j\u0005{\u0011y\u0004C\u0004\u0002\u0010v\u0002\r!a%\t\u000f\t\u0005S\b1\u0001\u0003D\u0005\u0019A\u000eZ1\u0011\u0007y\u0013)%C\u0002\u0003H-\u00131\u0001\u0014#B\u0003\u0011qW\r\u001f;\u0002\u001fM,(-\\5u\u001b&t\u0017NQ1uG\"$2!\u001bB(\u0011\u001d\u0011\tf\u0010a\u0001\u0003'\u000bQAY1uG\"\fA\"\u001e9eCR,G*Y7cI\u0006$R!\u001dB,\u00057BqA!\u0017A\u0001\u0004\t\t,\u0001\u0003ti\u0006$\bB\u0002B/\u0001\u0002\u0007A.A\u0005cCR\u001c\u0007nU5{K\u0006YQ\u000f\u001d3bi\u0016\fE\u000e\u001d5b)\u0015\t(1\rB7\u0011\u001d\u0011)'\u0011a\u0001\u0005O\nq\u0001\\8ha\"\fG\u000f\u0005\u0004\u00024\n%\u0014\u0011F\u0005\u0005\u0005W\n)LA\u0006EK:\u001cXMV3di>\u0014\bb\u0002B8\u0003\u0002\u0007\u0011\u0011F\u0001\u000e]>tW)\u001c9us\u0012{7m\u001d(\u0002\u0007IDw\u000e\u0006\u0002\u0002*\u0005qq-\u001a;HC6l\u0017-T1ue&DHCBAY\u0005s\u0012i\b\u0003\u0004\u0003|\r\u0003\r\u0001\\\u0001\u0004e><\bB\u0002B@\u0007\u0002\u0007A.A\u0002d_2\f1bZ3u\u0019\u0012\u000bUj\u001c3fYR!!Q\u0011BF!\rq&qQ\u0005\u0004\u0005\u0013[%\u0001\u0003'E\u00036{G-\u001a7\t\u000f\t5E\t1\u0001\u0003\u0010\u0006q\u0011\u000e^3sCRLwN\u001c+j[\u0016\u001c\b#\u0002-\u0003\u0012\u0006%\u0012b\u0001BJ3\n)\u0011I\u001d:bs\"*\u0001!!9\u0002n\u0006\u0011rJ\u001c7j]\u0016dE)Q(qi&l\u0017N_3s!\tqfi\u0005\u0002G/R\u0011!\u0011T\u0001\u001am\u0006\u0014\u0018.\u0019;j_:\fG\u000eV8qS\u000eLeNZ3sK:\u001cW\r\u0006\t\u0003$\n\u0005'Q\u0019Be\u0005\u001b\u0014\u0019N!6\u0003XBI\u0001L!*\u0003h\u0005E&\u0011V\u0005\u0004\u0005OK&A\u0002+va2,7\u0007E\u0003\u0003,\nmFN\u0004\u0003\u0003.\n]f\u0002\u0002BX\u0005kk!A!-\u000b\u0007\tMV+\u0001\u0004=e>|GOP\u0005\u00025&\u0019!\u0011X-\u0002\u000fA\f7m[1hK&!!Q\u0018B`\u0005\u0011a\u0015n\u001d;\u000b\u0007\te\u0016\fC\u0004\u0003D\"\u0003\rA!+\u0002\u000f%tG-[2fg\"9!q\u0019%A\u0002\t=\u0015A\u0002<bYV,7\u000fC\u0004\u0003L\"\u0003\r!!-\u0002\u0017\u0015D\b/\u00127pO\n,G/\u0019\u0005\b\u0003\u0017A\u0005\u0019\u0001Bh!\u0019\t\u0019L!5\u0002*%!\u0011\u0011DA[\u0011\u001d\t\u0019\u000e\u0013a\u0001\u0003SAQA\u001b%A\u00021DaA!7I\u0001\u0004I\u0018\u0001B:fK\u0012$bBa)\u0003^\n\u0005(1\u001dBs\u0005O\u0014I\u000fC\u0004\u0003`&\u0003\r!a\u0004\u0002\u0015Q,'/\\\"pk:$8\u000fC\u0004\u0003L&\u0003\r!!-\t\u000f\u0005-\u0011\n1\u0001\u0003P\"9\u00111[%A\u0002\u0005%\u0002\"\u00026J\u0001\u0004a\u0007B\u0002Bm\u0013\u0002\u0007\u0011\u0010"
)
public final class OnlineLDAOptimizer implements LDAOptimizer, Logging {
   private int k;
   private long corpusSize;
   private int vocabSize;
   private Vector alpha;
   private double eta;
   private Random randomGenerator;
   private boolean sampleWithReplacement;
   private double tau0;
   private double kappa;
   private double miniBatchFraction;
   private boolean optimizeDocConcentration;
   private RDD docs;
   private DenseMatrix lambda;
   private int iteration;
   private double gammaShape;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private int k() {
      return this.k;
   }

   private void k_$eq(final int x$1) {
      this.k = x$1;
   }

   private long corpusSize() {
      return this.corpusSize;
   }

   private void corpusSize_$eq(final long x$1) {
      this.corpusSize = x$1;
   }

   private int vocabSize() {
      return this.vocabSize;
   }

   private void vocabSize_$eq(final int x$1) {
      this.vocabSize = x$1;
   }

   private Vector alpha() {
      return this.alpha;
   }

   private void alpha_$eq(final Vector x$1) {
      this.alpha = x$1;
   }

   public Vector getAlpha() {
      return this.alpha();
   }

   private double eta() {
      return this.eta;
   }

   private void eta_$eq(final double x$1) {
      this.eta = x$1;
   }

   public double getEta() {
      return this.eta();
   }

   private Random randomGenerator() {
      return this.randomGenerator;
   }

   private void randomGenerator_$eq(final Random x$1) {
      this.randomGenerator = x$1;
   }

   private boolean sampleWithReplacement() {
      return this.sampleWithReplacement;
   }

   private void sampleWithReplacement_$eq(final boolean x$1) {
      this.sampleWithReplacement = x$1;
   }

   private double tau0() {
      return this.tau0;
   }

   private void tau0_$eq(final double x$1) {
      this.tau0 = x$1;
   }

   private double kappa() {
      return this.kappa;
   }

   private void kappa_$eq(final double x$1) {
      this.kappa = x$1;
   }

   private double miniBatchFraction() {
      return this.miniBatchFraction;
   }

   private void miniBatchFraction_$eq(final double x$1) {
      this.miniBatchFraction = x$1;
   }

   private boolean optimizeDocConcentration() {
      return this.optimizeDocConcentration;
   }

   private void optimizeDocConcentration_$eq(final boolean x$1) {
      this.optimizeDocConcentration = x$1;
   }

   private RDD docs() {
      return this.docs;
   }

   private void docs_$eq(final RDD x$1) {
      this.docs = x$1;
   }

   private DenseMatrix lambda() {
      return this.lambda;
   }

   private void lambda_$eq(final DenseMatrix x$1) {
      this.lambda = x$1;
   }

   public DenseMatrix getLambda() {
      return this.lambda();
   }

   private int iteration() {
      return this.iteration;
   }

   private void iteration_$eq(final int x$1) {
      this.iteration = x$1;
   }

   private double gammaShape() {
      return this.gammaShape;
   }

   private void gammaShape_$eq(final double x$1) {
      this.gammaShape = x$1;
   }

   public double getTau0() {
      return this.tau0();
   }

   public OnlineLDAOptimizer setTau0(final double tau0) {
      .MODULE$.require(tau0 > (double)0, () -> "LDA tau0 must be positive, but was set to " + tau0);
      this.tau0_$eq(tau0);
      return this;
   }

   public double getKappa() {
      return this.kappa();
   }

   public OnlineLDAOptimizer setKappa(final double kappa) {
      .MODULE$.require(kappa >= (double)0, () -> "Online LDA kappa must be nonnegative, but was set to " + kappa);
      this.kappa_$eq(kappa);
      return this;
   }

   public double getMiniBatchFraction() {
      return this.miniBatchFraction();
   }

   public OnlineLDAOptimizer setMiniBatchFraction(final double miniBatchFraction) {
      .MODULE$.require(miniBatchFraction > (double)0.0F && miniBatchFraction <= (double)1.0F, () -> "Online LDA miniBatchFraction must be in range (0,1], but was set to " + miniBatchFraction);
      this.miniBatchFraction_$eq(miniBatchFraction);
      return this;
   }

   public boolean getOptimizeDocConcentration() {
      return this.optimizeDocConcentration();
   }

   public OnlineLDAOptimizer setOptimizeDocConcentration(final boolean optimizeDocConcentration) {
      this.optimizeDocConcentration_$eq(optimizeDocConcentration);
      return this;
   }

   public OnlineLDAOptimizer setLambda(final DenseMatrix lambda) {
      this.lambda_$eq(lambda);
      return this;
   }

   public OnlineLDAOptimizer setGammaShape(final double shape) {
      this.gammaShape_$eq(shape);
      return this;
   }

   public OnlineLDAOptimizer setSampleWithReplacement(final boolean replace) {
      this.sampleWithReplacement_$eq(replace);
      return this;
   }

   public OnlineLDAOptimizer initialize(final RDD docs, final LDA lda) {
      this.k_$eq(lda.getK());
      this.corpusSize_$eq(docs.count());
      this.vocabSize_$eq(((Vector)((Tuple2)docs.first())._2()).size());
      Vector var10001;
      if (lda.getAsymmetricDocConcentration().size() == 1) {
         if (lda.getAsymmetricDocConcentration().apply(0) == (double)-1) {
            var10001 = Vectors$.MODULE$.dense((double[])scala.Array..MODULE$.fill(this.k(), (JFunction0.mcD.sp)() -> (double)1.0F / (double)this.k(), scala.reflect.ClassTag..MODULE$.Double()));
         } else {
            .MODULE$.require(lda.getAsymmetricDocConcentration().apply(0) >= (double)0, () -> "all entries in alpha must be >=0, got: " + this.alpha());
            var10001 = Vectors$.MODULE$.dense((double[])scala.Array..MODULE$.fill(this.k(), (JFunction0.mcD.sp)() -> lda.getAsymmetricDocConcentration().apply(0), scala.reflect.ClassTag..MODULE$.Double()));
         }
      } else {
         .MODULE$.require(lda.getAsymmetricDocConcentration().size() == this.k(), () -> "alpha must have length k, got: " + this.alpha());
         lda.getAsymmetricDocConcentration().foreachActive((JFunction2.mcVID.sp)(x0$1, x1$1) -> {
            Tuple2.mcID.sp var5 = new Tuple2.mcID.sp(x0$1, x1$1);
            if (var5 != null) {
               double x = ((Tuple2)var5)._2$mcD$sp();
               .MODULE$.require(x >= (double)0, () -> "all entries in alpha must be >= 0, got: " + this.alpha());
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               throw new MatchError(var5);
            }
         });
         var10001 = lda.getAsymmetricDocConcentration();
      }

      label36: {
         this.alpha_$eq(var10001);
         this.eta_$eq(lda.getTopicConcentration() == (double)-1 ? (double)1.0F / (double)this.k() : lda.getTopicConcentration());
         this.randomGenerator_$eq(new Random(lda.getSeed()));
         this.docs_$eq(docs);
         StorageLevel var10000 = this.docs().getStorageLevel();
         StorageLevel var3 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
         if (var10000 == null) {
            if (var3 != null) {
               break label36;
            }
         } else if (!var10000.equals(var3)) {
            break label36;
         }

         this.logWarning((Function0)(() -> "The input data is not directly cached, which may hurt performance if its parent RDDs are also uncached."));
      }

      this.lambda_$eq(this.getGammaMatrix(this.k(), this.vocabSize()));
      this.iteration_$eq(0);
      return this;
   }

   public OnlineLDAOptimizer next() {
      RDD batch = this.docs().sample(this.sampleWithReplacement(), this.miniBatchFraction(), this.randomGenerator().nextLong());
      return batch.isEmpty() ? this : this.submitMiniBatch(batch);
   }

   public OnlineLDAOptimizer submitMiniBatch(final RDD batch) {
      this.iteration_$eq(this.iteration() + 1);
      int k = this.k();
      int vocabSize = this.vocabSize();
      DenseMatrix expElogbeta = (DenseMatrix)((ImmutableNumericOps)breeze.numerics.package.exp..MODULE$.apply(LDAUtils$.MODULE$.dirichletExpectation(this.lambda()), breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseMatrix..MODULE$.scalarOf(), breeze.numerics.package.exp.expDoubleImpl..MODULE$, breeze.linalg.operators.HasOps..MODULE$.canMapValues_DM$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double())))).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM());
      Broadcast expElogbetaBc = batch.sparkContext().broadcast(expElogbeta, scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
      breeze.linalg.Vector alpha = this.alpha().asBreeze();
      double gammaShape = this.gammaShape();
      boolean optimizeDocConcentration = this.optimizeDocConcentration();
      long seed = this.randomGenerator().nextLong();
      Function0 logphatPartOptionBase = () -> (Option)(optimizeDocConcentration ? new Some(breeze.linalg.DenseVector..MODULE$.zeros$mDc$sp(k, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero())) : scala.None..MODULE$);
      RDD stats = batch.mapPartitionsWithIndex((index, docs) -> $anonfun$submitMiniBatch$2(k, vocabSize, logphatPartOptionBase, expElogbetaBc, alpha, gammaShape, seed, BoxesRunTime.unboxToInt(index), docs), batch.mapPartitionsWithIndex$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      Tuple3 x$1 = new Tuple3((Object)null, logphatPartOptionBase.apply(), BoxesRunTime.boxToLong(0L));
      Function2 x$2 = (u, v) -> elementWiseSum$1(u, v);
      Function2 x$3 = (u, v) -> elementWiseSum$1(u, v);
      int x$4 = stats.treeAggregate$default$4(x$1);
      Tuple3 var16 = (Tuple3)stats.treeAggregate(x$1, x$2, x$3, x$4, scala.reflect.ClassTag..MODULE$.apply(Tuple3.class));
      if (var16 != null) {
         DenseMatrix statsSum = (DenseMatrix)var16._1();
         Option logphatOption = (Option)var16._2();
         long nonEmptyDocsN = BoxesRunTime.unboxToLong(var16._3());
         if (statsSum != null && logphatOption != null && true) {
            Tuple3 var15 = new Tuple3(statsSum, logphatOption, BoxesRunTime.boxToLong(nonEmptyDocsN));
            DenseMatrix statsSum = (DenseMatrix)var15._1();
            Option logphatOption = (Option)var15._2();
            long nonEmptyDocsN = BoxesRunTime.unboxToLong(var15._3());
            expElogbetaBc.destroy();
            if (nonEmptyDocsN == 0L) {
               this.logWarning((Function0)(() -> "No non-empty documents were submitted in the batch."));
               return this;
            }

            DenseMatrix batchResult = (DenseMatrix)statsSum.$times$colon$times(expElogbeta.t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM()), breeze.linalg.operators.HasOps..MODULE$.op_DM_DM_Double_OpMulScalar());
            int batchSize = (int)scala.runtime.RichDouble..MODULE$.ceil$extension(.MODULE$.doubleWrapper(this.miniBatchFraction() * (double)this.corpusSize()));
            this.updateLambda(batchResult, batchSize);
            logphatOption.foreach((x$11) -> (DenseVector)x$11.$div$eq(BoxesRunTime.boxToDouble((double)nonEmptyDocsN), breeze.linalg.operators.HasOps..MODULE$.impl_Op_InPlace_DV_S_Double_OpDiv()));
            logphatOption.foreach((x$12) -> {
               $anonfun$submitMiniBatch$11(this, nonEmptyDocsN, x$12);
               return BoxedUnit.UNIT;
            });
            return this;
         }
      }

      throw new MatchError(var16);
   }

   private void updateLambda(final DenseMatrix stat, final int batchSize) {
      double weight = this.rho();
      this.lambda().$colon$eq(((NumericOps)(new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble((double)1 - weight)))).$times(this.lambda(), breeze.linalg.operators.HasOps..MODULE$.s_dm_op_Double_OpMulMatrix())).$plus((new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(weight)))).$times(((NumericOps)stat.$times(BoxesRunTime.boxToDouble((double)this.corpusSize() / (double)batchSize), breeze.linalg.operators.HasOps..MODULE$.op_DM_S_Double_OpMulMatrix())).$plus(BoxesRunTime.boxToDouble(this.eta()), breeze.linalg.operators.HasOps..MODULE$.op_DM_S_Double_OpAdd()), breeze.linalg.operators.HasOps..MODULE$.s_dm_op_Double_OpMulMatrix()), breeze.linalg.operators.HasOps..MODULE$.op_DM_DM_Double_OpAdd()), breeze.linalg.operators.HasOps..MODULE$.dm_dm_UpdateOp_Double_OpSet());
   }

   private void updateAlpha(final DenseVector logphat, final double nonEmptyDocsN) {
      double weight = this.rho();
      DenseVector alpha = this.alpha().asBreeze().toDenseVector$mcD$sp(scala.reflect.ClassTag..MODULE$.Double());
      DenseVector gradf = (DenseVector)(new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(nonEmptyDocsN)))).$times(((NumericOps)LDAUtils$.MODULE$.dirichletExpectation(alpha).unary_$minus(breeze.linalg.operators.HasOps..MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.math.Ring..MODULE$.ringD(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()))).$plus(logphat, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_DV_DV_eq_DV_Double()), breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_DV_eq_DV_Double_OpMulMatrix());
      double c = nonEmptyDocsN * breeze.numerics.package.trigamma..MODULE$.apply$mDDc$sp(BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(alpha, breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))), breeze.numerics.package.trigamma.trigammaImplDouble..MODULE$);
      DenseVector q = (DenseVector)(new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(-nonEmptyDocsN)))).$times(breeze.numerics.package.trigamma..MODULE$.apply(alpha, breeze.linalg.operators.HasOps..MODULE$.fromLowOrderCanMapValues(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.numerics.package.trigamma.trigammaImplDouble..MODULE$, breeze.linalg.DenseVector..MODULE$.DV_canMapValues$mDDc$sp(scala.reflect.ClassTag..MODULE$.Double()))), breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_DV_eq_DV_Double_OpMulMatrix());
      double b = BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply(gradf.$div(q, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))) / ((double)1.0F / c + BoxesRunTime.unboxToDouble(breeze.linalg.sum..MODULE$.apply((new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble((double)1.0F)))).$div(q, breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_DV_eq_DV_Double_OpDiv()), breeze.linalg.sum..MODULE$.reduce_Double(breeze.linalg.operators.HasOps..MODULE$.DV_canIterateValues()))));
      DenseVector dalpha = (DenseVector)((ImmutableNumericOps)((ImmutableNumericOps)gradf.$minus(BoxesRunTime.boxToDouble(b), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpSub())).unary_$minus(breeze.linalg.operators.HasOps..MODULE$.impl_OpNeg_T_Generic_from_OpMulScalar(breeze.linalg.DenseVector..MODULE$.DV_scalarOf(), breeze.math.Ring..MODULE$.ringD(), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_DV_Double_OpMulScalar()))).$div(q, breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_DV_eq_DV_Double_OpDiv());
      if (BoxesRunTime.unboxToBoolean(breeze.linalg.all..MODULE$.apply(((NumericOps)((NumericOps)(new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(weight)))).$times(dalpha, breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_DV_eq_DV_Double_OpMulMatrix())).$plus(alpha, breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_DV_DV_eq_DV_Double())).$greater$colon$greater(BoxesRunTime.boxToDouble((double)0.0F), breeze.linalg.operators.HasOps..MODULE$.impl_Op_DV_S_eq_BV_comparison_Double_OpGT()), breeze.linalg.operators.HasOps..MODULE$.impl_all_BV_eq_Boolean()))) {
         alpha.$colon$plus$eq((new package.InjectNumericOps(breeze.linalg.package..MODULE$.InjectNumericOps(BoxesRunTime.boxToDouble(weight)))).$times(dalpha, breeze.linalg.operators.HasOps..MODULE$.impl_Op_S_DV_eq_DV_Double_OpMulMatrix()), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double());
         this.alpha_$eq(Vectors$.MODULE$.dense(alpha.toArray$mcD$sp(scala.reflect.ClassTag..MODULE$.Double())));
      }
   }

   private double rho() {
      return scala.math.package..MODULE$.pow(this.getTau0() + (double)this.iteration(), -this.getKappa());
   }

   private DenseMatrix getGammaMatrix(final int row, final int col) {
      RandBasis randBasis = new RandBasis(new MersenneTwister(this.randomGenerator().nextLong()));
      Gamma gammaRandomGenerator = new Gamma(this.gammaShape(), (double)1.0F / this.gammaShape(), randBasis);
      double[] temp = (double[])gammaRandomGenerator.sample(row * col).toArray(scala.reflect.ClassTag..MODULE$.Double());
      return (DenseMatrix)(new DenseMatrix.mcD.sp(col, row, temp)).t(breeze.linalg.operators.HasOps..MODULE$.canTranspose_DM());
   }

   public LDAModel getLDAModel(final double[] iterationTimes) {
      return (new LocalLDAModel(Matrices$.MODULE$.fromBreeze(this.lambda()).transpose(), this.alpha(), this.eta(), LocalLDAModel$.MODULE$.$lessinit$greater$default$4())).setSeed(this.randomGenerator().nextLong());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$submitMiniBatch$3(final Tuple2 x$6) {
      return ((Vector)x$6._2()).numNonzeros() > 0;
   }

   // $FF: synthetic method
   public static final void $anonfun$submitMiniBatch$4(final LongRef nonEmptyDocCount$1, final Broadcast expElogbetaBc$1, final breeze.linalg.Vector alpha$2, final double gammaShape$1, final int k$2, final long seed$1, final int index$1, final DenseMatrix stat$1, final Option logphatPartOption$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         Vector termCounts = (Vector)x0$1._2();
         if (termCounts != null) {
            ++nonEmptyDocCount$1.elem;
            Tuple3 var18 = OnlineLDAOptimizer$.MODULE$.variationalTopicInference(termCounts, (DenseMatrix)expElogbetaBc$1.value(), alpha$2, gammaShape$1, k$2, seed$1 + (long)index$1);
            if (var18 != null) {
               DenseVector gammad = (DenseVector)var18._1();
               DenseMatrix sstats = (DenseMatrix)var18._2();
               List ids = (List)var18._3();
               Tuple3 var17 = new Tuple3(gammad, sstats, ids);
               DenseVector gammad = (DenseVector)var17._1();
               DenseMatrix sstats = (DenseMatrix)var17._2();
               List ids = (List)var17._3();
               ((NumericOps)stat$1.apply(scala.package..MODULE$.$colon$colon(), ids, breeze.linalg.operators.HasOps..MODULE$.canSliceWeirdCols(breeze.math.Semiring..MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double()))).$colon$eq(((NumericOps)stat$1.apply(scala.package..MODULE$.$colon$colon(), ids, breeze.linalg.operators.HasOps..MODULE$.canSliceWeirdCols(breeze.math.Semiring..MODULE$.semiringD(), scala.reflect.ClassTag..MODULE$.Double()))).$plus(sstats, breeze.linalg.operators.HasOps..MODULE$.castOps_M_M(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.op_M_DM_Double_OpAdd())), breeze.linalg.operators.HasOps..MODULE$.castUpdateOps_M_M(scala..less.colon.less..MODULE$.refl(), scala..less.colon.less..MODULE$.refl(), breeze.gymnastics.NotGiven..MODULE$.neq(), breeze.linalg.operators.HasOps..MODULE$.m_m_UpdateOp_Double_OpSet()));
               logphatPartOption$1.foreach((x$8) -> (DenseVector)x$8.$plus$eq(LDAUtils$.MODULE$.dirichletExpectation(gammad), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double()));
               BoxedUnit var10000 = BoxedUnit.UNIT;
               return;
            }

            throw new MatchError(var18);
         }
      }

      throw new MatchError(x0$1);
   }

   // $FF: synthetic method
   public static final Iterator $anonfun$submitMiniBatch$2(final int k$2, final int vocabSize$1, final Function0 logphatPartOptionBase$1, final Broadcast expElogbetaBc$1, final breeze.linalg.Vector alpha$2, final double gammaShape$1, final long seed$1, final int index, final Iterator docs) {
      Iterator nonEmptyDocs = docs.filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$submitMiniBatch$3(x$6)));
      DenseMatrix stat = breeze.linalg.DenseMatrix..MODULE$.zeros$mDc$sp(k$2, vocabSize$1, scala.reflect.ClassTag..MODULE$.Double(), breeze.storage.Zero..MODULE$.DoubleZero());
      Option logphatPartOption = (Option)logphatPartOptionBase$1.apply();
      LongRef nonEmptyDocCount = LongRef.create(0L);
      nonEmptyDocs.foreach((x0$1) -> {
         $anonfun$submitMiniBatch$4(nonEmptyDocCount, expElogbetaBc$1, alpha$2, gammaShape$1, k$2, seed$1, index, stat, logphatPartOption, x0$1);
         return BoxedUnit.UNIT;
      });
      return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple3[]{new Tuple3(stat, logphatPartOption, BoxesRunTime.boxToLong(nonEmptyDocCount.elem))})));
   }

   private static final Tuple3 elementWiseSum$1(final Tuple3 u, final Tuple3 v) {
      DenseMatrix var10000;
      if (u._1() == null) {
         var10000 = (DenseMatrix)v._1();
      } else if (v._1() == null) {
         var10000 = (DenseMatrix)u._1();
      } else {
         ((NumericOps)u._1()).$plus$eq(v._1(), breeze.linalg.operators.HasOps..MODULE$.dm_dm_UpdateOp_Double_OpAdd());
         var10000 = (DenseMatrix)u._1();
      }

      DenseMatrix vec = var10000;
      ((Option)u._2()).foreach((x$9) -> (DenseVector)x$9.$plus$eq(((Option)v._2()).get(), breeze.linalg.operators.HasOps..MODULE$.impl_OpAdd_InPlace_DV_DV_Double()));
      return new Tuple3(vec, u._2(), BoxesRunTime.boxToLong(BoxesRunTime.unboxToLong(u._3()) + BoxesRunTime.unboxToLong(v._3())));
   }

   // $FF: synthetic method
   public static final void $anonfun$submitMiniBatch$11(final OnlineLDAOptimizer $this, final long nonEmptyDocsN$1, final DenseVector x$12) {
      $this.updateAlpha(x$12, (double)nonEmptyDocsN$1);
   }

   public OnlineLDAOptimizer() {
      Logging.$init$(this);
      this.k = 0;
      this.corpusSize = 0L;
      this.vocabSize = 0;
      this.alpha = Vectors$.MODULE$.dense((double)0.0F, (Seq)scala.collection.immutable.Nil..MODULE$);
      this.eta = (double)0.0F;
      this.randomGenerator = null;
      this.sampleWithReplacement = true;
      this.tau0 = (double)1024.0F;
      this.kappa = 0.51;
      this.miniBatchFraction = 0.05;
      this.optimizeDocConcentration = false;
      this.docs = null;
      this.lambda = null;
      this.iteration = 0;
      this.gammaShape = (double)100.0F;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
