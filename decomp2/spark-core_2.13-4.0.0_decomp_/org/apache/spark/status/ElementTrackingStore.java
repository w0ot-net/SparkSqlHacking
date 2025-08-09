package org.apache.spark.status;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.Status$;
import org.apache.spark.util.ThreadUtils$;
import org.apache.spark.util.Utils$;
import org.apache.spark.util.kvstore.InMemoryStore;
import org.apache.spark.util.kvstore.KVStore;
import org.apache.spark.util.kvstore.KVStoreView;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u00115a!\u0002%J\u0001-\u000b\u0006\u0002\u00032\u0001\u0005\u0003\u0005\u000b\u0011\u0002.\t\u0011\u0011\u0004!\u0011!Q\u0001\n\u0015DQ!\u001b\u0001\u0005\u0002)4Aa\u001c\u0001\u0005a\"Aq\u000f\u0002BC\u0002\u0013\u0005\u0001\u0010C\u0005\u0002h\u0012\u0011\t\u0011)A\u0005s\"1\u0011\u000e\u0002C\u0001\u0003SD\u0011\"!?\u0005\u0005\u0004%I!a?\t\u0011\t=A\u0001)A\u0005\u0003{DqA!\u0005\u0005\t\u0003\u0011\u0019\u0002C\u0004\u0003b\u0011!\tAa\u0019\t\u0011]\u0004!\u0019!C\u0005\u0005gB\u0001\"a:\u0001A\u0003%!Q\u000f\u0005\n\u0005\u001f\u0003!\u0019!C\u0005\u0005#C\u0001Ba(\u0001A\u0003%!1\u0013\u0005\n\u0005C\u0003!\u0019!C\u0005\u0005GC\u0001B!,\u0001A\u0003%!Q\u0015\u0005\n\u0005_\u0003\u0001\u0019!C\u0005\u0005cC\u0011Ba-\u0001\u0001\u0004%IA!.\t\u0011\te\u0006\u0001)Q\u0005\u0003\u000bDqAa1\u0001\t\u0003\u0011)\rC\u0004\u0003h\u0002!\tA!;\t\u000f\tM\b\u0001\"\u0001\u0003v\"9!1 \u0001\u0005B\tu\bbBB\u0007\u0001\u0011\u00053q\u0002\u0005\b\u0007\u001b\u0001A\u0011AB\u000b\u0011\u001d\u0019i\u0002\u0001C\u0001\u0007?Aqa!\b\u0001\t\u0003\u001a)\u0005C\u0004\u0004f\u0001!\tea\u001a\t\u000f\r]\u0004\u0001\"\u0011\u0004z!91Q\u0011\u0001\u0005B\r\u001d\u0005bBBF\u0001\u0011\u00053Q\u0012\u0005\b\u0007?\u0003A\u0011IBQ\u0011\u001d\u0019y\n\u0001C!\u0007_Cqaa1\u0001\t\u0003\u001a)\rC\u0004\u0004D\u0002!\taa2\t\u000f\r5\u0007\u0001\"\u0001\u00032\u001a1\u0011\u0011\u0003\u0001E\u0003'A!\"a\t'\u0005+\u0007I\u0011AA\u0013\u0011)\tiC\nB\tB\u0003%\u0011q\u0005\u0005\u000b\u0003_1#Q3A\u0005\u0002\u0005E\u0002BCA M\tE\t\u0015!\u0003\u00024!1\u0011N\nC\u0001\u0003\u0003B\u0011\"a\u0018'\u0003\u0003%\t!!\u0019\t\u0013\u0005=d%%A\u0005\u0002\u0005E\u0004\"CAFME\u0005I\u0011AAG\u0011%\t)JJA\u0001\n\u0003\n9\nC\u0005\u0002 \u001a\n\t\u0011\"\u0001\u0002\"\"I\u0011\u0011\u0016\u0014\u0002\u0002\u0013\u0005\u00111\u0016\u0005\n\u0003c3\u0013\u0011!C!\u0003gC\u0011\"!1'\u0003\u0003%\t!a1\t\u0013\u00055g%!A\u0005B\u0005=\u0007\"CAjM\u0005\u0005I\u0011IAk\u0011%\t9NJA\u0001\n\u0003\nI\u000eC\u0005\u0002\\\u001a\n\t\u0011\"\u0011\u0002^\u001eI1q\u001a\u0001\u0002\u0002#%1\u0011\u001b\u0004\n\u0003#\u0001\u0011\u0011!E\u0005\u0007'Da![\u001d\u0005\u0002\r}\u0007\"CAls\u0005\u0005IQIAm\u0011%\u0019\t/OA\u0001\n\u0003\u001b\u0019\u000fC\u0005\u0004rf\n\t\u0011\"!\u0004t\u001eA!\u0011F%\t\u0002-\u0013YCB\u0004I\u0013\"\u00051J!\f\t\r%|D\u0011\u0001B\u0018\r%\u0011\td\u0010I\u0001$C\u0011\u0019dB\u0004\u0003L}B\tA!\u0011\u0007\u000f\t]r\b#\u0001\u0003:!1\u0011n\u0011C\u0001\u0005\u007f9qA!\u0014@\u0011\u0003\u0011IEB\u0004\u0003D}B\tA!\u0012\t\r%4E\u0011\u0001B$\u0005Q)E.Z7f]R$&/Y2lS:<7\u000b^8sK*\u0011!jS\u0001\u0007gR\fG/^:\u000b\u00051k\u0015!B:qCJ\\'B\u0001(P\u0003\u0019\t\u0007/Y2iK*\t\u0001+A\u0002pe\u001e\u001c2\u0001\u0001*[!\t\u0019\u0006,D\u0001U\u0015\t)f+\u0001\u0003mC:<'\"A,\u0002\t)\fg/Y\u0005\u00033R\u0013aa\u00142kK\u000e$\bCA.a\u001b\u0005a&BA/_\u0003\u001dYgo\u001d;pe\u0016T!aX&\u0002\tU$\u0018\u000e\\\u0005\u0003Cr\u0013qa\u0013,Ti>\u0014X-A\u0003ti>\u0014Xm\u0001\u0001\u0002\t\r|gN\u001a\t\u0003M\u001el\u0011aS\u0005\u0003Q.\u0013\u0011b\u00159be.\u001cuN\u001c4\u0002\rqJg.\u001b;?)\rYWN\u001c\t\u0003Y\u0002i\u0011!\u0013\u0005\u0006E\u000e\u0001\rA\u0017\u0005\u0006I\u000e\u0001\r!\u001a\u0002\u0010\u0019\u0006$8\r[3e)JLwmZ3sgN\u0011A!\u001d\t\u0003eVl\u0011a\u001d\u0006\u0002i\u0006)1oY1mC&\u0011ao\u001d\u0002\u0007\u0003:L(+\u001a4\u0002\u0011Q\u0014\u0018nZ4feN,\u0012!\u001f\t\u0006u\u0006\u0015\u00111\u0002\b\u0004w\u0006\u0005aB\u0001?\u0000\u001b\u0005i(B\u0001@d\u0003\u0019a$o\\8u}%\tA/C\u0002\u0002\u0004M\fq\u0001]1dW\u0006<W-\u0003\u0003\u0002\b\u0005%!aA*fc*\u0019\u00111A:1\t\u00055\u00111\u001d\t\u0006\u0003\u001f1\u0013\u0011]\u0007\u0002\u0001\t9AK]5hO\u0016\u0014X\u0003BA\u000b\u0003\u0013\u001abAJ9\u0002\u0018\u0005u\u0001c\u0001:\u0002\u001a%\u0019\u00111D:\u0003\u000fA\u0013x\u000eZ;diB\u0019!0a\b\n\t\u0005\u0005\u0012\u0011\u0002\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\ni\"\u0014Xm\u001d5pY\u0012,\"!a\n\u0011\u0007I\fI#C\u0002\u0002,M\u0014A\u0001T8oO\u0006QA\u000f\u001b:fg\"|G\u000e\u001a\u0011\u0002\r\u0005\u001cG/[8o+\t\t\u0019\u0004E\u0004s\u0003k\t9#!\u000f\n\u0007\u0005]2OA\u0005Gk:\u001cG/[8ocA\u0019!/a\u000f\n\u0007\u0005u2O\u0001\u0003V]&$\u0018aB1di&|g\u000e\t\u000b\u0007\u0003\u0007\nY&!\u0018\u0011\u000b\u0005=a%!\u0012\u0011\t\u0005\u001d\u0013\u0011\n\u0007\u0001\t\u001d\tYE\nb\u0001\u0003\u001b\u0012\u0011\u0001V\t\u0005\u0003\u001f\n)\u0006E\u0002s\u0003#J1!a\u0015t\u0005\u001dqu\u000e\u001e5j]\u001e\u00042A]A,\u0013\r\tIf\u001d\u0002\u0004\u0003:L\bbBA\u0012W\u0001\u0007\u0011q\u0005\u0005\b\u0003_Y\u0003\u0019AA\u001a\u0003\u0011\u0019w\u000e]=\u0016\t\u0005\r\u0014\u0011\u000e\u000b\u0007\u0003K\nY'!\u001c\u0011\u000b\u0005=a%a\u001a\u0011\t\u0005\u001d\u0013\u0011\u000e\u0003\b\u0003\u0017b#\u0019AA'\u0011%\t\u0019\u0003\fI\u0001\u0002\u0004\t9\u0003C\u0005\u000201\u0002\n\u00111\u0001\u00024\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nT\u0003BA:\u0003\u0013+\"!!\u001e+\t\u0005\u001d\u0012qO\u0016\u0003\u0003s\u0002B!a\u001f\u0002\u00066\u0011\u0011Q\u0010\u0006\u0005\u0003\u007f\n\t)A\u0005v]\u000eDWmY6fI*\u0019\u00111Q:\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\b\u0006u$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00129\u00111J\u0017C\u0002\u00055\u0013AD2paf$C-\u001a4bk2$HEM\u000b\u0005\u0003\u001f\u000b\u0019*\u0006\u0002\u0002\u0012*\"\u00111GA<\t\u001d\tYE\fb\u0001\u0003\u001b\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAAM!\r\u0019\u00161T\u0005\u0004\u0003;#&AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002$B\u0019!/!*\n\u0007\u0005\u001d6OA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002V\u00055\u0006\"CAXc\u0005\u0005\t\u0019AAR\u0003\rAH%M\u0001\u0010aJ|G-^2u\u0013R,'/\u0019;peV\u0011\u0011Q\u0017\t\u0007\u0003o\u000bi,!\u0016\u000e\u0005\u0005e&bAA^g\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005}\u0016\u0011\u0018\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002F\u0006-\u0007c\u0001:\u0002H&\u0019\u0011\u0011Z:\u0003\u000f\t{w\u000e\\3b]\"I\u0011qV\u001a\u0002\u0002\u0003\u0007\u0011QK\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002\u001a\u0006E\u0007\"CAXi\u0005\u0005\t\u0019AAR\u0003!A\u0017m\u001d5D_\u0012,GCAAR\u0003!!xn\u0015;sS:<GCAAM\u0003\u0019)\u0017/^1mgR!\u0011QYAp\u0011%\tykNA\u0001\u0002\u0004\t)\u0006\u0005\u0003\u0002H\u0005\rHaCAs\r\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00121a\u0018\u00132\u0003%!(/[4hKJ\u001c\b\u0005\u0006\u0003\u0002l\u00065\bcAA\b\t!1qo\u0002a\u0001\u0003_\u0004RA_A\u0003\u0003c\u0004D!a=\u0002xB)\u0011q\u0002\u0014\u0002vB!\u0011qIA|\t1\t)/!<\u0002\u0002\u0003\u0005)\u0011AA'\u0003\u001d\u0001XM\u001c3j]\u001e,\"!!@\u0011\t\u0005}(1B\u0007\u0003\u0005\u0003QAAa\u0001\u0003\u0006\u00051\u0011\r^8nS\u000eTAAa\u0002\u0003\n\u0005Q1m\u001c8dkJ\u0014XM\u001c;\u000b\u0005}3\u0016\u0002\u0002B\u0007\u0005\u0003\u0011Q\"\u0011;p[&\u001c'i\\8mK\u0006t\u0017\u0001\u00039f]\u0012Lgn\u001a\u0011\u0002\u0011\u0019L'/Z(oG\u0016$BA!\u0006\u0003PA\u0019!qC!\u000f\u0007\teaH\u0004\u0003\u0003\u001c\t\u001db\u0002\u0002B\u000f\u0005KqAAa\b\u0003$9\u0019AP!\t\n\u0003AK!AT(\n\u00051k\u0015B\u0001&L\u0003Q)E.Z7f]R$&/Y2lS:<7\u000b^8sKB\u0011AnP\n\u0003\u007fE$\"Aa\u000b\u0003!]\u0013\u0018\u000e^3Rk\u0016,XMU3tk2$8CA!rS\r\t5I\u0012\u0002\f/JLG/Z)vKV,Gm\u0005\u0003Dc\nm\u0002c\u0001B\u001f\u00036\tq\b\u0006\u0002\u0003BA\u0019!QH\"\u0003#]\u0013\u0018\u000e^3TW&\u0004\b/\u001a3Rk\u0016,Xm\u0005\u0003Gc\nmBC\u0001B%!\r\u0011iDR\u0001\f/JLG/Z)vKV,G-A\tXe&$XmU6jaB,G-U;fk\u0016DqA!\u0015\u000b\u0001\u0004\u0011\u0019&A\u0001g!\u001d\u0011\u0018Q\u0007B+\u0003s\u0001RA_A\u0003\u0005/\u0002DA!\u0017\u0003^A)\u0011q\u0002\u0014\u0003\\A!\u0011q\tB/\t1\u0011yFa\u0014\u0002\u0002\u0003\u0005)\u0011AA'\u0005\ryFEM\u0001\fI\r|Gn\u001c8%a2,8\u000f\u0006\u0003\u0002l\n\u0015\u0004b\u0002B4\u0017\u0001\u0007!\u0011N\u0001\fC\u0012$G\u000e\u0016:jO\u001e,'\u000f\r\u0003\u0003l\t=\u0004#BA\bM\t5\u0004\u0003BA$\u0005_\"AB!\u001d\u0003f\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00121a\u0018\u00134+\t\u0011)\b\u0005\u0005\u0003x\tu$\u0011QAv\u001b\t\u0011IH\u0003\u0003\u0003|\u0005e\u0016aB7vi\u0006\u0014G.Z\u0005\u0005\u0005\u007f\u0012IHA\u0004ICNDW*\u001991\t\t\r%1\u0012\t\u0006'\n\u0015%\u0011R\u0005\u0004\u0005\u000f#&!B\"mCN\u001c\b\u0003BA$\u0005\u0017#1B!$\u000e\u0003\u0003\u0005\tQ!\u0001\u0002N\t\u0019q\f\n\u001b\u0002\u001b\u0019dWo\u001d5Ue&<w-\u001a:t+\t\u0011\u0019\n\u0005\u0004\u0003x\tU%\u0011T\u0005\u0005\u0005/\u0013IH\u0001\u0006MSN$()\u001e4gKJ\u0004RA\u001dBN\u0003sI1A!(t\u0005%1UO\\2uS>t\u0007'\u0001\bgYV\u001c\b\u000e\u0016:jO\u001e,'o\u001d\u0011\u0002\u0011\u0015DXmY;u_J,\"A!*\u0011\t\t\u001d&\u0011V\u0007\u0003\u0005\u000bIAAa+\u0003\u0006\tyQ\t_3dkR|'oU3sm&\u001cW-A\u0005fq\u0016\u001cW\u000f^8sA\u000591\u000f^8qa\u0016$WCAAc\u0003-\u0019Ho\u001c9qK\u0012|F%Z9\u0015\t\u0005e\"q\u0017\u0005\n\u0003_\u001b\u0012\u0011!a\u0001\u0003\u000b\f\u0001b\u001d;paB,G\r\t\u0015\u0004)\tu\u0006c\u0001:\u0003@&\u0019!\u0011Y:\u0003\u0011Y|G.\u0019;jY\u0016\f!\"\u00193e)JLwmZ3s)\u0019\u00119Ma3\u0003fR!\u0011\u0011\bBe\u0011\u001d\ty#\u0006a\u0001\u0003gAqA!4\u0016\u0001\u0004\u0011y-A\u0003lY\u0006\u001c8\u000f\r\u0003\u0003R\n\u0005\bC\u0002Bj\u00057\u0014yN\u0004\u0003\u0003V\n]\u0007C\u0001?t\u0013\r\u0011In]\u0001\u0007!J,G-\u001a4\n\t\t\u001d%Q\u001c\u0006\u0004\u00053\u001c\b\u0003BA$\u0005C$ABa9\u0003L\u0006\u0005\t\u0011!B\u0001\u0003\u001b\u00121a\u0018\u00136\u0011\u001d\t\u0019#\u0006a\u0001\u0003O\tqa\u001c8GYV\u001c\b\u000e\u0006\u0003\u0002:\t-\b\u0002CA\u0018-\u0011\u0005\rA!<\u0011\u000bI\u0014y/!\u000f\n\u0007\tE8O\u0001\u0005=Eft\u0017-\\3?\u0003\u001d!w.Q:z]\u000e$B!!\u000f\u0003x\"A!\u0011`\f\u0005\u0002\u0004\u0011i/\u0001\u0002g]\u0006!!/Z1e+\u0011\u0011ypa\u0001\u0015\r\r\u00051QAB\u0005!\u0011\t9ea\u0001\u0005\u000f\u0005-\u0003D1\u0001\u0002N!9!Q\u001a\rA\u0002\r\u001d\u0001C\u0002Bj\u00057\u001c\t\u0001C\u0004\u0004\fa\u0001\r!!\u0016\u0002\u00159\fG/\u001e:bY.+\u00170A\u0003xe&$X\r\u0006\u0003\u0002:\rE\u0001bBB\n3\u0001\u0007\u0011QK\u0001\u0006m\u0006dW/\u001a\u000b\u0007\u0005+\u00199b!\u0007\t\u000f\rM!\u00041\u0001\u0002V!911\u0004\u000eA\u0002\u0005\u0015\u0017!D2iK\u000e\\GK]5hO\u0016\u00148/\u0001\fsK6|g/Z!mY\nK\u0018J\u001c3fqZ\u000bG.^3t+\u0011\u0019\tc!\u000b\u0015\u0011\u0005\u001571EB\u0016\u0007gAqA!4\u001c\u0001\u0004\u0019)\u0003\u0005\u0004\u0003T\nm7q\u0005\t\u0005\u0003\u000f\u001aI\u0003B\u0004\u0002Lm\u0011\r!!\u0014\t\u000f\r52\u00041\u0001\u00040\u0005)\u0011N\u001c3fqB!!1[B\u0019\u0013\u0011\tiJ!8\t\u000f\rU2\u00041\u0001\u00048\u0005Y\u0011N\u001c3fqZ\u000bG.^3ta\u0011\u0019Id!\u0011\u0011\u000bi\u001cYda\u0010\n\t\ru\u0012\u0011\u0002\u0002\t\u0013R,'/\u00192mKB!\u0011qIB!\t1\u0019\u0019ea\r\u0002\u0002\u0003\u0005)\u0011AA'\u0005\ryFEN\u000b\u0005\u0007\u000f\u001ay\u0005\u0006\u0005\u0002F\u000e%3\u0011KB*\u0011\u001d\u0011i\r\ba\u0001\u0007\u0017\u0002bAa5\u0003\\\u000e5\u0003\u0003BA$\u0007\u001f\"q!a\u0013\u001d\u0005\u0004\ti\u0005C\u0004\u0004.q\u0001\raa\f\t\u000f\rUB\u00041\u0001\u0004VA\"1qKB1!\u0019\u0019Ifa\u0017\u0004`5\u0011!\u0011B\u0005\u0005\u0007;\u0012IA\u0001\u0006D_2dWm\u0019;j_:\u0004B!a\u0012\u0004b\u0011a11MB*\u0003\u0003\u0005\tQ!\u0001\u0002N\t\u0019q\fJ\u001c\u0002\r\u0011,G.\u001a;f)\u0019\tId!\u001b\u0004v!9!QZ\u000fA\u0002\r-\u0004\u0007BB7\u0007c\u0002bAa5\u0003\\\u000e=\u0004\u0003BA$\u0007c\"Aba\u001d\u0004j\u0005\u0005\t\u0011!B\u0001\u0003\u001b\u00121a\u0018\u00139\u0011\u001d\u0019Y!\ba\u0001\u0003+\n1bZ3u\u001b\u0016$\u0018\rZ1uCV!11PB@)\u0011\u0019ih!!\u0011\t\u0005\u001d3q\u0010\u0003\b\u0003\u0017r\"\u0019AA'\u0011\u001d\u0011iM\ba\u0001\u0007\u0007\u0003bAa5\u0003\\\u000eu\u0014aC:fi6+G/\u00193bi\u0006$B!!\u000f\u0004\n\"911C\u0010A\u0002\u0005U\u0013\u0001\u0002<jK^,Baa$\u0004\u001aR!1\u0011SBN!\u0015Y61SBL\u0013\r\u0019)\n\u0018\u0002\f\u0017Z\u001bFo\u001c:f-&,w\u000f\u0005\u0003\u0002H\reEaBA&A\t\u0007\u0011Q\n\u0005\b\u0005\u001b\u0004\u0003\u0019ABO!\u0019\u0011\u0019Na7\u0004\u0018\u0006)1m\\;oiR!\u0011qEBR\u0011\u001d\u0011i-\ta\u0001\u0007K\u0003Daa*\u0004,B1!1\u001bBn\u0007S\u0003B!a\u0012\u0004,\u0012a1QVBR\u0003\u0003\u0005\tQ!\u0001\u0002N\t\u0019q\fJ\u001d\u0015\u0011\u0005\u001d2\u0011WB_\u0007\u007fCqA!4#\u0001\u0004\u0019\u0019\f\r\u0003\u00046\u000ee\u0006C\u0002Bj\u00057\u001c9\f\u0005\u0003\u0002H\reF\u0001DB^\u0007c\u000b\t\u0011!A\u0003\u0002\u00055#\u0001B0%cABqa!\f#\u0001\u0004\u0019y\u0003C\u0004\u0004B\n\u0002\r!!\u0016\u0002\u0019%tG-\u001a=fIZ\u000bG.^3\u0002\u000b\rdwn]3\u0015\u0005\u0005eB\u0003BA\u001d\u0007\u0013Dqaa3%\u0001\u0004\t)-A\u0006dY>\u001cX\rU1sK:$\u0018AE;tS:<\u0017J\\'f[>\u0014\u0018p\u0015;pe\u0016\fq\u0001\u0016:jO\u001e,'\u000fE\u0002\u0002\u0010e\u001aB!O9\u0004VB!1q[Bo\u001b\t\u0019INC\u0002\u0004\\Z\u000b!![8\n\t\u0005\u00052\u0011\u001c\u000b\u0003\u0007#\fQ!\u00199qYf,Ba!:\u0004lR11q]Bw\u0007_\u0004R!a\u0004'\u0007S\u0004B!a\u0012\u0004l\u00129\u00111\n\u001fC\u0002\u00055\u0003bBA\u0012y\u0001\u0007\u0011q\u0005\u0005\b\u0003_a\u0004\u0019AA\u001a\u0003\u001d)h.\u00199qYf,Ba!>\u0005\fQ!1q\u001fC\u0002!\u0015\u00118\u0011`B\u007f\u0013\r\u0019Yp\u001d\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000fI\u001cy0a\n\u00024%\u0019A\u0011A:\u0003\rQ+\b\u000f\\33\u0011%!)!PA\u0001\u0002\u0004!9!A\u0002yIA\u0002R!a\u0004'\t\u0013\u0001B!a\u0012\u0005\f\u00119\u00111J\u001fC\u0002\u00055\u0003"
)
public class ElementTrackingStore implements KVStore {
   private volatile Trigger$ Trigger$module;
   private final KVStore store;
   private final HashMap triggers;
   private final ListBuffer flushTriggers;
   private final ExecutorService executor;
   private volatile boolean stopped;

   private Trigger$ Trigger() {
      if (this.Trigger$module == null) {
         this.Trigger$lzycompute$1();
      }

      return this.Trigger$module;
   }

   private HashMap triggers() {
      return this.triggers;
   }

   private ListBuffer flushTriggers() {
      return this.flushTriggers;
   }

   private ExecutorService executor() {
      return this.executor;
   }

   private boolean stopped() {
      return this.stopped;
   }

   private void stopped_$eq(final boolean x$1) {
      this.stopped = x$1;
   }

   public void addTrigger(final Class klass, final long threshold, final Function1 action) {
      Trigger newTrigger = new Trigger(threshold, action);
      Option var7 = this.triggers().get(klass);
      if (.MODULE$.equals(var7)) {
         this.triggers().update(klass, new LatchedTriggers(new scala.collection.immutable..colon.colon(newTrigger, scala.collection.immutable.Nil..MODULE$)));
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else if (var7 instanceof Some) {
         Some var8 = (Some)var7;
         LatchedTriggers latchedTrigger = (LatchedTriggers)var8.value();
         this.triggers().update(klass, latchedTrigger.$colon$plus(newTrigger));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var7);
      }
   }

   public void onFlush(final Function0 action) {
      this.flushTriggers().$plus$eq(action);
   }

   public void doAsync(final Function0 fn) {
      this.executor().submit(new Runnable(fn) {
         private final Function0 fn$1;

         public void run() {
            Utils$.MODULE$.tryLog(this.fn$1);
         }

         public {
            this.fn$1 = fn$1;
         }
      });
   }

   public Object read(final Class klass, final Object naturalKey) {
      return this.store.read(klass, naturalKey);
   }

   public void write(final Object value) {
      this.store.write(value);
   }

   public WriteQueueResult write(final Object value, final boolean checkTriggers) {
      this.write(value);
      return (WriteQueueResult)(checkTriggers && !this.stopped() ? (WriteQueueResult)this.triggers().get(value.getClass()).map((latchedList) -> latchedList.fireOnce((list) -> {
            $anonfun$write$2(this, value, list);
            return BoxedUnit.UNIT;
         })).getOrElse(() -> ElementTrackingStore.WriteSkippedQueue$.MODULE$) : ElementTrackingStore.WriteSkippedQueue$.MODULE$);
   }

   public boolean removeAllByIndexValues(final Class klass, final String index, final Iterable indexValues) {
      return this.removeAllByIndexValues(klass, index, scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(indexValues).asJavaCollection());
   }

   public boolean removeAllByIndexValues(final Class klass, final String index, final Collection indexValues) {
      return this.store.removeAllByIndexValues(klass, index, indexValues);
   }

   public void delete(final Class klass, final Object naturalKey) {
      this.store.delete(klass, naturalKey);
   }

   public Object getMetadata(final Class klass) {
      return this.store.getMetadata(klass);
   }

   public void setMetadata(final Object value) {
      this.store.setMetadata(value);
   }

   public KVStoreView view(final Class klass) {
      return this.store.view(klass);
   }

   public long count(final Class klass) {
      return this.store.count(klass);
   }

   public long count(final Class klass, final String index, final Object indexedValue) {
      return this.store.count(klass, index, indexedValue);
   }

   public void close() {
      this.close(true);
   }

   public synchronized void close(final boolean closeParent) {
      if (!this.stopped()) {
         this.stopped_$eq(true);
         ThreadUtils$.MODULE$.shutdown(this.executor(), scala.concurrent.duration.FiniteDuration..MODULE$.apply(5L, TimeUnit.SECONDS));
         this.flushTriggers().foreach((trigger) -> Utils$.MODULE$.tryLog(trigger));
         if (closeParent) {
            this.store.close();
         }
      }
   }

   public boolean usingInMemoryStore() {
      return this.store instanceof InMemoryStore;
   }

   private final void Trigger$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Trigger$module == null) {
            this.Trigger$module = new Trigger$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final void $anonfun$write$3(final long count$1, final Trigger t) {
      if (count$1 > t.threshold()) {
         t.action().apply$mcVJ$sp(count$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$write$2(final ElementTrackingStore $this, final Object value$1, final Seq list) {
      long count = $this.store.count(value$1.getClass());
      list.foreach((t) -> {
         $anonfun$write$3(count, t);
         return BoxedUnit.UNIT;
      });
   }

   public ElementTrackingStore(final KVStore store, final SparkConf conf) {
      this.store = store;
      this.triggers = new HashMap();
      this.flushTriggers = new ListBuffer();
      this.executor = (ExecutorService)(BoxesRunTime.unboxToBoolean(conf.get(Status$.MODULE$.ASYNC_TRACKING_ENABLED())) ? ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("element-tracking-store-worker") : ThreadUtils$.MODULE$.sameThreadExecutorService());
      this.stopped = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class LatchedTriggers {
      private final Seq triggers;
      private final AtomicBoolean pending;
      // $FF: synthetic field
      public final ElementTrackingStore $outer;

      public Seq triggers() {
         return this.triggers;
      }

      private AtomicBoolean pending() {
         return this.pending;
      }

      public WriteQueueResult fireOnce(final Function1 f) {
         if (this.pending().compareAndSet(false, true)) {
            this.org$apache$spark$status$ElementTrackingStore$LatchedTriggers$$$outer().doAsync((JFunction0.mcV.sp)() -> {
               this.pending().set(false);
               f.apply(this.triggers());
            });
            return ElementTrackingStore.WriteQueued$.MODULE$;
         } else {
            return ElementTrackingStore.WriteSkippedQueue$.MODULE$;
         }
      }

      public LatchedTriggers $colon$plus(final Trigger addlTrigger) {
         return this.org$apache$spark$status$ElementTrackingStore$LatchedTriggers$$$outer().new LatchedTriggers((Seq)this.triggers().$colon$plus(addlTrigger));
      }

      // $FF: synthetic method
      public ElementTrackingStore org$apache$spark$status$ElementTrackingStore$LatchedTriggers$$$outer() {
         return this.$outer;
      }

      public LatchedTriggers(final Seq triggers) {
         this.triggers = triggers;
         if (ElementTrackingStore.this == null) {
            throw null;
         } else {
            this.$outer = ElementTrackingStore.this;
            super();
            this.pending = new AtomicBoolean(false);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class Trigger implements Product, Serializable {
      private final long threshold;
      private final Function1 action;
      // $FF: synthetic field
      public final ElementTrackingStore $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long threshold() {
         return this.threshold;
      }

      public Function1 action() {
         return this.action;
      }

      public Trigger copy(final long threshold, final Function1 action) {
         return this.org$apache$spark$status$ElementTrackingStore$Trigger$$$outer().new Trigger(threshold, action);
      }

      public long copy$default$1() {
         return this.threshold();
      }

      public Function1 copy$default$2() {
         return this.action();
      }

      public String productPrefix() {
         return "Trigger";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.threshold());
            }
            case 1 -> {
               return this.action();
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
         return x$1 instanceof Trigger;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "threshold";
            }
            case 1 -> {
               return "action";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.threshold()));
         var1 = Statics.mix(var1, Statics.anyHash(this.action()));
         return Statics.finalizeHash(var1, 2);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label56: {
               if (x$1 instanceof Trigger && ((Trigger)x$1).org$apache$spark$status$ElementTrackingStore$Trigger$$$outer() == this.org$apache$spark$status$ElementTrackingStore$Trigger$$$outer()) {
                  Trigger var4 = (Trigger)x$1;
                  if (this.threshold() == var4.threshold()) {
                     label46: {
                        Function1 var10000 = this.action();
                        Function1 var5 = var4.action();
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
      public ElementTrackingStore org$apache$spark$status$ElementTrackingStore$Trigger$$$outer() {
         return this.$outer;
      }

      public Trigger(final long threshold, final Function1 action) {
         this.threshold = threshold;
         this.action = action;
         if (ElementTrackingStore.this == null) {
            throw null;
         } else {
            this.$outer = ElementTrackingStore.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class Trigger$ implements Serializable {
      // $FF: synthetic field
      private final ElementTrackingStore $outer;

      public final String toString() {
         return "Trigger";
      }

      public Trigger apply(final long threshold, final Function1 action) {
         return this.$outer.new Trigger(threshold, action);
      }

      public Option unapply(final Trigger x$0) {
         return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.threshold()), x$0.action())));
      }

      public Trigger$() {
         if (ElementTrackingStore.this == null) {
            throw null;
         } else {
            this.$outer = ElementTrackingStore.this;
            super();
         }
      }
   }

   public static class WriteQueued$ implements WriteQueueResult {
      public static final WriteQueued$ MODULE$ = new WriteQueued$();
   }

   public static class WriteSkippedQueue$ implements WriteQueueResult {
      public static final WriteSkippedQueue$ MODULE$ = new WriteSkippedQueue$();
   }

   public interface WriteQueueResult {
   }
}
