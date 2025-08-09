package org.apache.spark.mllib.tree.model;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.util.Loader$;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructType;
import org.json4s.JValue;
import scala.Enumeration;
import scala.MatchError;
import scala.Predef;
import scala.Some;
import scala.StringContext;
import scala.Tuple1;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.Map;
import scala.collection.immutable.Range;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015-aaBA\t\u0003'\u0001\u0011Q\u0006\u0005\u000b\u0003?\u0002!Q1A\u0005\u0002\u0005\u0005\u0004BCA?\u0001\t\u0005\t\u0015!\u0003\u0002d!Q\u0011\u0011\u0011\u0001\u0003\u0006\u0004%\t!a!\t\u0015\u0005E\u0006A!A!\u0002\u0013\t)\tC\u0004\u00026\u0002!\t!a.\t\u000f\u0005\u0015\u0007\u0001\"\u0001\u0002H\"9\u0011Q\u0019\u0001\u0005\u0002\u0005\u0005\bbBAc\u0001\u0011\u0005\u0011Q\u001f\u0005\b\u0005;\u0001A\u0011\u0001B\u0010\u0011\u001d\u0011i\u0003\u0001C\u0001\u0005?AqA!\r\u0001\t\u0003\u0012\u0019\u0004C\u0004\u0003F\u0001!\tAa\u0012\t\u000f\t-\u0003\u0001\"\u0011\u0003N\u001dA!QNA\n\u0011\u0003\u0011yG\u0002\u0005\u0002\u0012\u0005M\u0001\u0012\u0001B9\u0011\u001d\t)l\u0004C\u0001\u0005\u001f;\u0011B!%\u0010\u0011\u0003\t9Ba%\u0007\u0013\t]u\u0002#\u0001\u0002\u0018\te\u0005bBA[%\u0011\u0005!1\u0014\u0005\b\u0005;\u0013B\u0011\u0001B$\u0011\u001d\u0011yJ\u0005C\u0001\u0005\u000f2aA!)\u0013\u0001\n\r\u0006BCAc-\tU\r\u0011\"\u0001\u0003,\"Q!Q\u0016\f\u0003\u0012\u0003\u0006I!!3\t\u0015\t=fC!f\u0001\n\u0003\u0011Y\u000b\u0003\u0006\u00032Z\u0011\t\u0012)A\u0005\u0003\u0013Dq!!.\u0017\t\u0003\u0011\u0019\fC\u0004\u0003>Z!\tAa0\t\u0013\t\u001dg#!A\u0005\u0002\t%\u0007\"\u0003Bh-E\u0005I\u0011\u0001Bi\u0011%\u0011)OFI\u0001\n\u0003\u0011\t\u000eC\u0005\u0003hZ\t\t\u0011\"\u0011\u0003j\"I!q\u001e\f\u0002\u0002\u0013\u0005!q\u0004\u0005\n\u0005c4\u0012\u0011!C\u0001\u0005gD\u0011Ba@\u0017\u0003\u0003%\te!\u0001\t\u0013\r=a#!A\u0005\u0002\rE\u0001\"CB\u000e-\u0005\u0005I\u0011IB\u000f\u0011%\u0019\tCFA\u0001\n\u0003\u001a\u0019\u0003C\u0005\u00032Y\t\t\u0011\"\u0011\u0004&!I1q\u0005\f\u0002\u0002\u0013\u00053\u0011F\u0004\b\u0007[\u0011\u0002\u0012AB\u0018\r\u001d\u0011\tK\u0005E\u0001\u0007cAq!!.+\t\u0003\u0019\u0019\u0004C\u0004\u00046)\"\taa\u000e\t\u000f\rU\"\u0006\"\u0001\u0004>!I1Q\u0007\u0016\u0002\u0002\u0013\u00055q\n\u0005\n\u0007+R\u0013\u0011!CA\u0007/B\u0011b!\u001b+\u0003\u0003%Iaa\u001b\u0007\r\rM$\u0003QB;\u0011)\u00199(\rBK\u0002\u0013\u0005!q\u0004\u0005\u000b\u0007s\n$\u0011#Q\u0001\n\t\u0005\u0002BCB>c\tU\r\u0011\"\u0001\u0003,\"Q1QP\u0019\u0003\u0012\u0003\u0006I!!3\t\u0015\r}\u0014G!f\u0001\n\u0003\u0011y\u0002\u0003\u0006\u0004\u0002F\u0012\t\u0012)A\u0005\u0005CA!ba!2\u0005+\u0007I\u0011ABC\u0011)\u0019i)\rB\tB\u0003%1q\u0011\u0005\b\u0003k\u000bD\u0011ABH\u0011\u001d\u0019Y*\rC\u0001\u0007;C\u0011Ba22\u0003\u0003%\ta!*\t\u0013\t=\u0017'%A\u0005\u0002\r=\u0006\"\u0003BscE\u0005I\u0011\u0001Bi\u0011%\u0019\u0019,MI\u0001\n\u0003\u0019y\u000bC\u0005\u00046F\n\n\u0011\"\u0001\u00048\"I!q]\u0019\u0002\u0002\u0013\u0005#\u0011\u001e\u0005\n\u0005_\f\u0014\u0011!C\u0001\u0005?A\u0011B!=2\u0003\u0003%\taa/\t\u0013\t}\u0018'!A\u0005B\r\u0005\u0001\"CB\bc\u0005\u0005I\u0011AB`\u0011%\u0019Y\"MA\u0001\n\u0003\u001a\u0019\rC\u0005\u0004\"E\n\t\u0011\"\u0011\u0004$!I!\u0011G\u0019\u0002\u0002\u0013\u00053Q\u0005\u0005\n\u0007O\t\u0014\u0011!C!\u0007\u000f<qaa3\u0013\u0011\u0003\u0019iMB\u0004\u0004tIA\taa4\t\u000f\u0005U6\n\"\u0001\u0004R\"91QG&\u0005\u0002\rM\u0007bBB\u001b\u0017\u0012\u00051\u0011\u001c\u0005\n\u0007kY\u0015\u0011!CA\u0007;D\u0011b!\u0016L\u0003\u0003%\tia:\t\u0013\r%4*!A\u0005\n\r-dABBz%\u0001\u001b)\u0010\u0003\u0006\u0004xJ\u0013)\u001a!C\u0001\u0005?A!b!?S\u0005#\u0005\u000b\u0011\u0002B\u0011\u0011)\u0019YP\u0015BK\u0002\u0013\u0005!q\u0004\u0005\u000b\u0007{\u0014&\u0011#Q\u0001\n\t\u0005\u0002BCAc%\nU\r\u0011\"\u0001\u0004\u0000\"Q!Q\u0016*\u0003\u0012\u0003\u0006IA!.\t\u0015\u0011\u0005!K!f\u0001\n\u0003\u0011Y\u000b\u0003\u0006\u0005\u0004I\u0013\t\u0012)A\u0005\u0003\u0013D!\u0002\"\u0002S\u0005+\u0007I\u0011\u0001C\u0004\u0011)!IA\u0015B\tB\u0003%11\u0003\u0005\u000b\t\u0017\u0011&Q3A\u0005\u0002\u00115\u0001B\u0003C\t%\nE\t\u0015!\u0003\u0005\u0010!QA1\u0003*\u0003\u0016\u0004%\t\u0001\"\u0006\t\u0015\u0011e!K!E!\u0002\u0013!9\u0002\u0003\u0006\u0005\u001cI\u0013)\u001a!C\u0001\t+A!\u0002\"\bS\u0005#\u0005\u000b\u0011\u0002C\f\u0011)!yB\u0015BK\u0002\u0013\u0005A\u0011\u0005\u0005\u000b\tK\u0011&\u0011#Q\u0001\n\u0011\r\u0002bBA[%\u0012\u0005Aq\u0005\u0005\n\u0005\u000f\u0014\u0016\u0011!C\u0001\t{A\u0011Ba4S#\u0003%\taa,\t\u0013\t\u0015(+%A\u0005\u0002\r=\u0006\"CBZ%F\u0005I\u0011\u0001C)\u0011%\u0019)LUI\u0001\n\u0003\u0011\t\u000eC\u0005\u0005VI\u000b\n\u0011\"\u0001\u0005X!IA1\f*\u0012\u0002\u0013\u0005AQ\f\u0005\n\tC\u0012\u0016\u0013!C\u0001\tGB\u0011\u0002b\u001aS#\u0003%\t\u0001b\u0019\t\u0013\u0011%$+%A\u0005\u0002\u0011-\u0004\"\u0003Bt%\u0006\u0005I\u0011\tBu\u0011%\u0011yOUA\u0001\n\u0003\u0011y\u0002C\u0005\u0003rJ\u000b\t\u0011\"\u0001\u0005p!I!q *\u0002\u0002\u0013\u00053\u0011\u0001\u0005\n\u0007\u001f\u0011\u0016\u0011!C\u0001\tgB\u0011ba\u0007S\u0003\u0003%\t\u0005b\u001e\t\u0013\r\u0005\"+!A\u0005B\r\r\u0002\"\u0003B\u0019%\u0006\u0005I\u0011IB\u0013\u0011%\u00199CUA\u0001\n\u0003\"YhB\u0004\u0005\u0000IA\t\u0001\"!\u0007\u000f\rM(\u0003#\u0001\u0005\u0004\"9\u0011Q\u0017>\u0005\u0002\u0011\u0015\u0005bBB\u001bu\u0012\u0005Aq\u0011\u0005\b\u0007kQH\u0011\u0001CH\u0011%\u0019)D_A\u0001\n\u0003#\u0019\nC\u0005\u0004Vi\f\t\u0011\"!\u0005(\"I1\u0011\u000e>\u0002\u0002\u0013%11\u000e\u0005\b\u0005\u0017\u0012B\u0011\u0001CZ\u0011\u001d!YL\u0005C\u0001\t{Cq\u0001b2\u0013\t\u0003!I\rC\u0004\u0005XJ!\t\u0001\"7\t\u000f\u0011\u0005(\u0003\"\u0003\u0005d\"9A1X\b\u0005B\u0011}\b\"CB5\u001f\u0005\u0005I\u0011BB6\u0005E!UmY5tS>tGK]3f\u001b>$W\r\u001c\u0006\u0005\u0003+\t9\"A\u0003n_\u0012,GN\u0003\u0003\u0002\u001a\u0005m\u0011\u0001\u0002;sK\u0016TA!!\b\u0002 \u0005)Q\u000e\u001c7jE*!\u0011\u0011EA\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\u0011\t)#a\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\t\tI#A\u0002pe\u001e\u001c\u0001aE\u0004\u0001\u0003_\tY$a\u0015\u0011\t\u0005E\u0012qG\u0007\u0003\u0003gQ!!!\u000e\u0002\u000bM\u001c\u0017\r\\1\n\t\u0005e\u00121\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005u\u0012Q\n\b\u0005\u0003\u007f\tIE\u0004\u0003\u0002B\u0005\u001dSBAA\"\u0015\u0011\t)%a\u000b\u0002\rq\u0012xn\u001c;?\u0013\t\t)$\u0003\u0003\u0002L\u0005M\u0012a\u00029bG.\fw-Z\u0005\u0005\u0003\u001f\n\tF\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0003\u0002L\u0005M\u0002\u0003BA+\u00037j!!a\u0016\u000b\t\u0005e\u00131D\u0001\u0005kRLG.\u0003\u0003\u0002^\u0005]#\u0001C*bm\u0016\f'\r\\3\u0002\u000fQ|\u0007OT8eKV\u0011\u00111\r\t\u0005\u0003K\n9'\u0004\u0002\u0002\u0014%!\u0011\u0011NA\n\u0005\u0011qu\u000eZ3)\u000b\u0005\ti'!\u001f\u0011\t\u0005=\u0014QO\u0007\u0003\u0003cRA!a\u001d\u0002 \u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005]\u0014\u0011\u000f\u0002\u0006'&t7-Z\u0011\u0003\u0003w\nQ!\r\u00181]A\n\u0001\u0002^8q\u001d>$W\r\t\u0015\u0006\u0005\u00055\u0014\u0011P\u0001\u0005C2<w.\u0006\u0002\u0002\u0006B!\u0011qQAU\u001d\u0011\tI)a)\u000f\t\u0005-\u0015q\u0014\b\u0005\u0003\u001b\u000biJ\u0004\u0003\u0002\u0010\u0006me\u0002BAI\u00033sA!a%\u0002\u0018:!\u0011\u0011IAK\u0013\t\tI#\u0003\u0003\u0002&\u0005\u001d\u0012\u0002BA\u0011\u0003GIA!!\b\u0002 %!\u0011\u0011DA\u000e\u0013\u0011\t\t+a\u0006\u0002\u001b\r|gNZ5hkJ\fG/[8o\u0013\u0011\t)+a*\u0002\t\u0005cwm\u001c\u0006\u0005\u0003C\u000b9\"\u0003\u0003\u0002,\u00065&\u0001B!mO>TA!!*\u0002(\"*1!!\u001c\u0002z\u0005)\u0011\r\\4pA!*A!!\u001c\u0002z\u00051A(\u001b8jiz\"b!!/\u0002<\u0006}\u0006cAA3\u0001!9\u0011qL\u0003A\u0002\u0005\r\u0004FBA^\u0003[\nI\bC\u0004\u0002\u0002\u0016\u0001\r!!\")\r\u0005}\u0016QNA=Q\u0015)\u0011QNA=\u0003\u001d\u0001(/\u001a3jGR$B!!3\u0002PB!\u0011\u0011GAf\u0013\u0011\ti-a\r\u0003\r\u0011{WO\u00197f\u0011\u001d\t\tN\u0002a\u0001\u0003'\f\u0001BZ3biV\u0014Xm\u001d\t\u0005\u0003+\fY.\u0004\u0002\u0002X*!\u0011\u0011\\A\u000e\u0003\u0019a\u0017N\\1mO&!\u0011Q\\Al\u0005\u00191Vm\u0019;pe\"*a!!\u001c\u0002zQ!\u00111]Ax!\u0019\t)/a;\u0002J6\u0011\u0011q\u001d\u0006\u0005\u0003S\fy\"A\u0002sI\u0012LA!!<\u0002h\n\u0019!\u000b\u0012#\t\u000f\u0005Ew\u00011\u0001\u0002rB1\u0011Q]Av\u0003'DSaBA7\u0003s\"B!a>\u0003\u0014A1\u0011\u0011 B\u0002\u0005\u000fi!!a?\u000b\t\u0005u\u0018q`\u0001\u0005U\u00064\u0018M\u0003\u0003\u0003\u0002\u0005}\u0011aA1qS&!!QAA~\u0005\u001dQ\u0015M^1S\t\u0012\u0003BA!\u0003\u0003\u00125\u0011!1\u0002\u0006\u0005\u0005\u001b\u0011y!\u0001\u0003mC:<'BAA\u007f\u0013\u0011\tiMa\u0003\t\u000f\u0005E\u0007\u00021\u0001\u0003\u0016A1\u0011\u0011 B\u0002\u0003'DS\u0001CA7\u00053\t#Aa\u0007\u0002\u000bEr#G\f\u0019\u0002\u00119,XNT8eKN,\"A!\t\u0011\t\u0005E\"1E\u0005\u0005\u0005K\t\u0019DA\u0002J]RDS!CA7\u0005S\t#Aa\u000b\u0002\u000bEr\u0013G\f\u0019\u0002\u000b\u0011,\u0007\u000f\u001e5)\u000b)\tiG!\u000b\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"A!\u000e\u0011\t\t]\"q\b\b\u0005\u0005s\u0011Y\u0004\u0005\u0003\u0002B\u0005M\u0012\u0002\u0002B\u001f\u0003g\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002B!\u0005\u0007\u0012aa\u0015;sS:<'\u0002\u0002B\u001f\u0003g\tQ\u0002^8EK\n,xm\u0015;sS:<WC\u0001B\u001bQ\u0015a\u0011Q\u000eB\r\u0003\u0011\u0019\u0018M^3\u0015\r\t=#Q\u000bB1!\u0011\t\tD!\u0015\n\t\tM\u00131\u0007\u0002\u0005+:LG\u000fC\u0004\u0003X5\u0001\rA!\u0017\u0002\u0005M\u001c\u0007\u0003\u0002B.\u0005;j!!a\b\n\t\t}\u0013q\u0004\u0002\r'B\f'o[\"p]R,\u0007\u0010\u001e\u0005\b\u0005Gj\u0001\u0019\u0001B\u001b\u0003\u0011\u0001\u0018\r\u001e5)\u000b5\tiGa\u001a\"\u0005\t%\u0014!B\u0019/g9\u0002\u0004&\u0002\u0001\u0002n\u0005e\u0014!\u0005#fG&\u001c\u0018n\u001c8Ue\u0016,Wj\u001c3fYB\u0019\u0011QM\b\u0014\u0013=\tyCa\u001d\u0003z\t\u0015\u0005CBA+\u0005k\nI,\u0003\u0003\u0003x\u0005]#A\u0002'pC\u0012,'\u000f\u0005\u0003\u0003|\t\u0005UB\u0001B?\u0015\u0011\u0011y(a\b\u0002\u0011%tG/\u001a:oC2LAAa!\u0003~\t9Aj\\4hS:<\u0007\u0003\u0002BD\u0005\u001bk!A!#\u000b\t\t-%qB\u0001\u0003S>LA!a\u0014\u0003\nR\u0011!qN\u0001\r'\u00064X\rT8bIZ\u000bt\f\r\t\u0004\u0005+\u0013R\"A\b\u0003\u0019M\u000bg/\u001a'pC\u00124\u0016g\u0018\u0019\u0014\u0007I\ty\u0003\u0006\u0002\u0003\u0014\u0006\tB\u000f[5t\r>\u0014X.\u0019;WKJ\u001c\u0018n\u001c8\u0002\u001bQD\u0017n]\"mCN\u001ch*Y7f\u0005-\u0001&/\u001a3jGR$\u0015\r^1\u0014\u000fY\tyC!*\u0002<A!\u0011\u0011\u0007BT\u0013\u0011\u0011I+a\r\u0003\u000fA\u0013x\u000eZ;diV\u0011\u0011\u0011Z\u0001\taJ,G-[2uA\u0005!\u0001O]8c\u0003\u0015\u0001(o\u001c2!)\u0019\u0011)L!/\u0003<B\u0019!q\u0017\f\u000e\u0003IAq!!2\u001c\u0001\u0004\tI\rC\u0004\u00030n\u0001\r!!3\u0002\u0013Q|\u0007K]3eS\u000e$XC\u0001Ba!\u0011\t)Ga1\n\t\t\u0015\u00171\u0003\u0002\b!J,G-[2u\u0003\u0011\u0019w\u000e]=\u0015\r\tU&1\u001aBg\u0011%\t)-\bI\u0001\u0002\u0004\tI\rC\u0005\u00030v\u0001\n\u00111\u0001\u0002J\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001BjU\u0011\tIM!6,\u0005\t]\u0007\u0003\u0002Bm\u0005Cl!Aa7\u000b\t\tu'q\\\u0001\nk:\u001c\u0007.Z2lK\u0012TA!a\u001d\u00024%!!1\u001dBn\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011!1\u001e\t\u0005\u0005\u0013\u0011i/\u0003\u0003\u0003B\t-\u0011\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0005k\u0014Y\u0010\u0005\u0003\u00022\t]\u0018\u0002\u0002B}\u0003g\u00111!\u00118z\u0011%\u0011iPIA\u0001\u0002\u0004\u0011\t#A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0007\u0007\u0001ba!\u0002\u0004\f\tUXBAB\u0004\u0015\u0011\u0019I!a\r\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0004\u000e\r\u001d!\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$Baa\u0005\u0004\u001aA!\u0011\u0011GB\u000b\u0013\u0011\u00199\"a\r\u0003\u000f\t{w\u000e\\3b]\"I!Q \u0013\u0002\u0002\u0003\u0007!Q_\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003l\u000e}\u0001\"\u0003B\u007fK\u0005\u0005\t\u0019\u0001B\u0011\u0003!A\u0017m\u001d5D_\u0012,GC\u0001B\u0011)\t\u0011Y/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0007'\u0019Y\u0003C\u0005\u0003~\"\n\t\u00111\u0001\u0003v\u0006Y\u0001K]3eS\u000e$H)\u0019;b!\r\u00119LK\n\u0006U\u0005=\"Q\u0011\u000b\u0003\u0007_\tQ!\u00199qYf$BA!.\u0004:!911\b\u0017A\u0002\t\u0005\u0017!\u00019\u0015\t\tU6q\b\u0005\b\u0007\u0003j\u0003\u0019AB\"\u0003\u0005\u0011\b\u0003BB#\u0007\u0017j!aa\u0012\u000b\t\r%\u0013qD\u0001\u0004gFd\u0017\u0002BB'\u0007\u000f\u00121AU8x)\u0019\u0011)l!\u0015\u0004T!9\u0011Q\u0019\u0018A\u0002\u0005%\u0007b\u0002BX]\u0001\u0007\u0011\u0011Z\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u0019If!\u001a\u0011\r\u0005E21LB0\u0013\u0011\u0019i&a\r\u0003\r=\u0003H/[8o!!\t\td!\u0019\u0002J\u0006%\u0017\u0002BB2\u0003g\u0011a\u0001V;qY\u0016\u0014\u0004\"CB4_\u0005\u0005\t\u0019\u0001B[\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0007[\u0002BA!\u0003\u0004p%!1\u0011\u000fB\u0006\u0005\u0019y%M[3di\nI1\u000b\u001d7ji\u0012\u000bG/Y\n\bc\u0005=\"QUA\u001e\u0003\u001d1W-\u0019;ve\u0016\f\u0001BZ3biV\u0014X\rI\u0001\ni\"\u0014Xm\u001d5pY\u0012\f!\u0002\u001e5sKNDw\u000e\u001c3!\u0003-1W-\u0019;ve\u0016$\u0016\u0010]3\u0002\u0019\u0019,\u0017\r^;sKRK\b/\u001a\u0011\u0002\u0015\r\fG/Z4pe&,7/\u0006\u0002\u0004\bB1\u0011QHBE\u0003\u0013LAaa#\u0002R\t\u00191+Z9\u0002\u0017\r\fG/Z4pe&,7\u000f\t\u000b\u000b\u0007#\u001b\u0019j!&\u0004\u0018\u000ee\u0005c\u0001B\\c!91q\u000f\u001eA\u0002\t\u0005\u0002bBB>u\u0001\u0007\u0011\u0011\u001a\u0005\b\u0007\u007fR\u0004\u0019\u0001B\u0011\u0011\u001d\u0019\u0019I\u000fa\u0001\u0007\u000f\u000bq\u0001^8Ta2LG/\u0006\u0002\u0004 B!\u0011QMBQ\u0013\u0011\u0019\u0019+a\u0005\u0003\u000bM\u0003H.\u001b;\u0015\u0015\rE5qUBU\u0007W\u001bi\u000bC\u0005\u0004xq\u0002\n\u00111\u0001\u0003\"!I11\u0010\u001f\u0011\u0002\u0003\u0007\u0011\u0011\u001a\u0005\n\u0007\u007fb\u0004\u0013!a\u0001\u0005CA\u0011ba!=!\u0003\u0005\raa\"\u0016\u0005\rE&\u0006\u0002B\u0011\u0005+\fabY8qs\u0012\"WMZ1vYR$3'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\re&\u0006BBD\u0005+$BA!>\u0004>\"I!Q`\"\u0002\u0002\u0003\u0007!\u0011\u0005\u000b\u0005\u0007'\u0019\t\rC\u0005\u0003~\u0016\u000b\t\u00111\u0001\u0003vR!!1^Bc\u0011%\u0011iPRA\u0001\u0002\u0004\u0011\t\u0003\u0006\u0003\u0004\u0014\r%\u0007\"\u0003B\u007f\u0013\u0006\u0005\t\u0019\u0001B{\u0003%\u0019\u0006\u000f\\5u\t\u0006$\u0018\rE\u0002\u00038.\u001bRaSA\u0018\u0005\u000b#\"a!4\u0015\t\rE5Q\u001b\u0005\b\u0007/l\u0005\u0019ABP\u0003\u0005\u0019H\u0003BBI\u00077Dqa!\u0011O\u0001\u0004\u0019\u0019\u0005\u0006\u0006\u0004\u0012\u000e}7\u0011]Br\u0007KDqaa\u001eP\u0001\u0004\u0011\t\u0003C\u0004\u0004|=\u0003\r!!3\t\u000f\r}t\n1\u0001\u0003\"!911Q(A\u0002\r\u001dE\u0003BBu\u0007c\u0004b!!\r\u0004\\\r-\b\u0003DA\u0019\u0007[\u0014\t#!3\u0003\"\r\u001d\u0015\u0002BBx\u0003g\u0011a\u0001V;qY\u0016$\u0004\"CB4!\u0006\u0005\t\u0019ABI\u0005!qu\u000eZ3ECR\f7c\u0002*\u00020\t\u0015\u00161H\u0001\u0007iJ,W-\u00133\u0002\u000fQ\u0014X-Z%eA\u00051an\u001c3f\u0013\u0012\fqA\\8eK&#\u0007%\u0006\u0002\u00036\u0006A\u0011.\u001c9ve&$\u00180A\u0005j[B,(/\u001b;zA\u00051\u0011n\u001d'fC\u001a,\"aa\u0005\u0002\u000f%\u001cH*Z1gA\u0005)1\u000f\u001d7jiV\u0011Aq\u0002\t\u0007\u0003c\u0019Yf!%\u0002\rM\u0004H.\u001b;!\u0003)aWM\u001a;O_\u0012,\u0017\nZ\u000b\u0003\t/\u0001b!!\r\u0004\\\t\u0005\u0012a\u00037fMRtu\u000eZ3JI\u0002\n1B]5hQRtu\u000eZ3JI\u0006a!/[4ii:{G-Z%eA\u0005A\u0011N\u001c4p\u000f\u0006Lg.\u0006\u0002\u0005$A1\u0011\u0011GB.\u0003\u0013\f\u0011\"\u001b8g_\u001e\u000b\u0017N\u001c\u0011\u0015)\u0011%B1\u0006C\u0017\t_!\t\u0004b\r\u00056\u0011]B\u0011\bC\u001e!\r\u00119L\u0015\u0005\b\u0007o,\u0007\u0019\u0001B\u0011\u0011\u001d\u0019Y0\u001aa\u0001\u0005CAq!!2f\u0001\u0004\u0011)\fC\u0004\u0005\u0002\u0015\u0004\r!!3\t\u000f\u0011\u0015Q\r1\u0001\u0004\u0014!9A1B3A\u0002\u0011=\u0001b\u0002C\nK\u0002\u0007Aq\u0003\u0005\b\t7)\u0007\u0019\u0001C\f\u0011\u001d!y\"\u001aa\u0001\tG!B\u0003\"\u000b\u0005@\u0011\u0005C1\tC#\t\u000f\"I\u0005b\u0013\u0005N\u0011=\u0003\"CB|MB\u0005\t\u0019\u0001B\u0011\u0011%\u0019YP\u001aI\u0001\u0002\u0004\u0011\t\u0003C\u0005\u0002F\u001a\u0004\n\u00111\u0001\u00036\"IA\u0011\u00014\u0011\u0002\u0003\u0007\u0011\u0011\u001a\u0005\n\t\u000b1\u0007\u0013!a\u0001\u0007'A\u0011\u0002b\u0003g!\u0003\u0005\r\u0001b\u0004\t\u0013\u0011Ma\r%AA\u0002\u0011]\u0001\"\u0003C\u000eMB\u0005\t\u0019\u0001C\f\u0011%!yB\u001aI\u0001\u0002\u0004!\u0019#\u0006\u0002\u0005T)\"!Q\u0017Bk\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIU*\"\u0001\"\u0017+\t\rM!Q[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00137+\t!yF\u000b\u0003\u0005\u0010\tU\u0017AD2paf$C-\u001a4bk2$HeN\u000b\u0003\tKRC\u0001b\u0006\u0003V\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012B\u0014AD2paf$C-\u001a4bk2$H%O\u000b\u0003\t[RC\u0001b\t\u0003VR!!Q\u001fC9\u0011%\u0011iP]A\u0001\u0002\u0004\u0011\t\u0003\u0006\u0003\u0004\u0014\u0011U\u0004\"\u0003B\u007fi\u0006\u0005\t\u0019\u0001B{)\u0011\u0011Y\u000f\"\u001f\t\u0013\tuX/!AA\u0002\t\u0005B\u0003BB\n\t{B\u0011B!@y\u0003\u0003\u0005\rA!>\u0002\u00119{G-\u001a#bi\u0006\u00042Aa.{'\u0015Q\u0018q\u0006BC)\t!\t\t\u0006\u0004\u0005*\u0011%E1\u0012\u0005\b\u0007od\b\u0019\u0001B\u0011\u0011\u001d!i\t a\u0001\u0003G\n\u0011A\u001c\u000b\u0005\tS!\t\nC\u0004\u0004Bu\u0004\raa\u0011\u0015)\u0011%BQ\u0013CL\t3#Y\n\"(\u0005 \u0012\u0005F1\u0015CS\u0011\u001d\u00199P a\u0001\u0005CAqaa?\u007f\u0001\u0004\u0011\t\u0003C\u0004\u0002Fz\u0004\rA!.\t\u000f\u0011\u0005a\u00101\u0001\u0002J\"9AQ\u0001@A\u0002\rM\u0001b\u0002C\u0006}\u0002\u0007Aq\u0002\u0005\b\t'q\b\u0019\u0001C\f\u0011\u001d!YB a\u0001\t/Aq\u0001b\b\u007f\u0001\u0004!\u0019\u0003\u0006\u0003\u0005*\u0012E\u0006CBA\u0019\u00077\"Y\u000b\u0005\f\u00022\u00115&\u0011\u0005B\u0011\u0005k\u000bIma\u0005\u0005\u0010\u0011]Aq\u0003C\u0012\u0013\u0011!y+a\r\u0003\rQ+\b\u000f\\3:\u0011%\u00199g`A\u0001\u0002\u0004!I\u0003\u0006\u0005\u0003P\u0011UFq\u0017C]\u0011!\u00119&a\u0001A\u0002\te\u0003\u0002\u0003B2\u0003\u0007\u0001\rA!\u000e\t\u0011\u0005U\u00111\u0001a\u0001\u0003s\u000bA\u0001\\8bIRQ\u0011\u0011\u0018C`\t\u0003$\u0019\r\"2\t\u0011\t]\u0013Q\u0001a\u0001\u00053B\u0001Ba\u0019\u0002\u0006\u0001\u0007!Q\u0007\u0005\t\u0003\u0003\u000b)\u00011\u0001\u00036!A!QDA\u0003\u0001\u0004\u0011\t#\u0001\bd_:\u001cHO];diR\u0013X-Z:\u0015\t\u0011-G\u0011\u001b\t\u0007\u0003c!i-a\u0019\n\t\u0011=\u00171\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\t\t'\f9\u00011\u0001\u0005V\u0006)an\u001c3fgB1\u0011Q]Av\tS\tQbY8ogR\u0014Xo\u0019;Ue\u0016,G\u0003BA2\t7D\u0001\u0002\"8\u0002\n\u0001\u0007Aq\\\u0001\u0005I\u0006$\u0018\r\u0005\u0004\u00022\u00115G\u0011F\u0001\u000eG>t7\u000f\u001e:vGRtu\u000eZ3\u0015\u0011\u0005\rDQ\u001dCu\tgD\u0001\u0002b:\u0002\f\u0001\u0007!\u0011E\u0001\u0003S\u0012D\u0001\u0002b;\u0002\f\u0001\u0007AQ^\u0001\bI\u0006$\u0018-T1q!!\u00119\u0004b<\u0003\"\u0011%\u0012\u0002\u0002Cy\u0005\u0007\u00121!T1q\u0011!!\u0019.a\u0003A\u0002\u0011U\b\u0003\u0003C|\t{\u0014\t#a\u0019\u000e\u0005\u0011e(\u0002\u0002C~\u0007\u000f\tq!\\;uC\ndW-\u0003\u0003\u0005r\u0012eHCBA]\u000b\u0003)\u0019\u0001\u0003\u0005\u0003X\u00055\u0001\u0019\u0001B-\u0011!\u0011\u0019'!\u0004A\u0002\tU\u0002FBA\u0007\u0003[\u00129\u0007K\u0003\u0010\u0003[\u00129\u0007K\u0003\u000f\u0003[\u00129\u0007"
)
public class DecisionTreeModel implements Serializable, Saveable {
   private final Node topNode;
   private final Enumeration.Value algo;

   public static DecisionTreeModel load(final SparkContext sc, final String path) {
      return DecisionTreeModel$.MODULE$.load(sc, path);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return DecisionTreeModel$.MODULE$.LogStringContext(sc);
   }

   public Node topNode() {
      return this.topNode;
   }

   public Enumeration.Value algo() {
      return this.algo;
   }

   public double predict(final Vector features) {
      return this.topNode().predict(features);
   }

   public RDD predict(final RDD features) {
      return features.map((x) -> BoxesRunTime.boxToDouble($anonfun$predict$1(this, x)), .MODULE$.Double());
   }

   public JavaRDD predict(final JavaRDD features) {
      return this.predict(features.rdd()).toJavaRDD();
   }

   public int numNodes() {
      return 1 + this.topNode().numDescendants();
   }

   public int depth() {
      return this.topNode().subtreeDepth();
   }

   public String toString() {
      label30: {
         Enumeration.Value var2 = this.algo();
         Enumeration.Value var10000 = Algo$.MODULE$.Classification();
         if (var10000 == null) {
            if (var2 == null) {
               break label30;
            }
         } else if (var10000.equals(var2)) {
            break label30;
         }

         label22: {
            var10000 = Algo$.MODULE$.Regression();
            if (var10000 == null) {
               if (var2 == null) {
                  break label22;
               }
            } else if (var10000.equals(var2)) {
               break label22;
            }

            throw new IllegalArgumentException("DecisionTreeModel given unknown algo parameter: " + this.algo() + ".");
         }

         int var6 = this.depth();
         return "DecisionTreeModel regressor of depth " + var6 + " with " + this.numNodes() + " nodes";
      }

      int var7 = this.depth();
      return "DecisionTreeModel classifier of depth " + var7 + " with " + this.numNodes() + " nodes";
   }

   public String toDebugString() {
      String header = this.toString() + "\n";
      return header + this.topNode().subtreeToString(2);
   }

   public void save(final SparkContext sc, final String path) {
      DecisionTreeModel.SaveLoadV1_0$.MODULE$.save(sc, path, this);
   }

   // $FF: synthetic method
   public static final double $anonfun$predict$1(final DecisionTreeModel $this, final Vector x) {
      return $this.predict(x);
   }

   public DecisionTreeModel(final Node topNode, final Enumeration.Value algo) {
      this.topNode = topNode;
      this.algo = algo;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisFormatVersion() {
         return "1.0";
      }

      public String thisClassName() {
         return "org.apache.spark.mllib.tree.DecisionTreeModel";
      }

      public void save(final SparkContext sc, final String path, final DecisionTreeModel model) {
         int memThreshold = 768;
         if (sc.isLocal()) {
            int driverMemory = BoxesRunTime.unboxToInt(sc.getReadOnlyConf().getOption("spark.driver.memory").orElse(() -> scala.Option..MODULE$.apply(System.getenv("SPARK_DRIVER_MEMORY"))).map((str) -> BoxesRunTime.boxToInteger($anonfun$save$2(str))).getOrElse((JFunction0.mcI.sp)() -> org.apache.spark.util.Utils..MODULE$.DEFAULT_DRIVER_MEM_MB()));
            if (driverMemory <= memThreshold) {
               DecisionTreeModel$.MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ".save() was called, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, MODULE$.thisClassName())}))).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but it may fail because of too little driver memory "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "m). If failure occurs, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DRIVER_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(driverMemory))})))).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"try setting driver-memory ", "m "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_THRESHOLD_SIZE..MODULE$, BoxesRunTime.boxToInteger(memThreshold))})))).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(or larger)."})))).log(scala.collection.immutable.Nil..MODULE$))));
            }
         } else if (sc.executorMemory() <= memThreshold) {
            DecisionTreeModel$.MODULE$.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ".save() was called, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLASS_NAME..MODULE$, MODULE$.thisClassName())}))).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"but it may fail because of too little executor memory "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(", "m). If failure occurs, "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_MEMORY_SIZE..MODULE$, BoxesRunTime.boxToInteger(sc.executorMemory()))})))).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"try setting executor-memory ", "m "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MEMORY_THRESHOLD_SIZE..MODULE$, BoxesRunTime.boxToInteger(memThreshold))})))).$plus(DecisionTreeModel$.MODULE$.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"(or larger)."})))).log(scala.collection.immutable.Nil..MODULE$))));
         }

         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String metadata = org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(org.json4s.JsonAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.pair2Assoc(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("class"), this.thisClassName()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("version"), this.thisFormatVersion()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("algo"), model.algo().toString()), (x) -> org.json4s.JsonDSL..MODULE$.string2jvalue(x)))), org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("numNodes"), BoxesRunTime.boxToInteger(model.numNodes())), (x) -> $anonfun$save$10(BoxesRunTime.unboxToInt(x)))), org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3()));
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(new Tuple1(metadata), scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Tuple1"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$));
            }

            public $typecreator1$1() {
            }
         }

         spark.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().text(Loader$.MODULE$.metadataPath(path));
         Seq nodes = model.topNode().subtreeIterator().toSeq();
         RDD dataRDD = sc.parallelize(nodes, sc.parallelize$default$2(), .MODULE$.apply(Node.class)).map((x$4) -> DecisionTreeModel$SaveLoadV1_0$NodeData$.MODULE$.apply(0, x$4), .MODULE$.apply(DecisionTreeModel$SaveLoadV1_0$NodeData.class));
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator2$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.tree.model.DecisionTreeModel.SaveLoadV1_0.NodeData").asType().toTypeConstructor();
            }

            public $typecreator2$1() {
            }
         }

         spark.createDataFrame(dataRDD, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())).write().parquet(Loader$.MODULE$.dataPath(path));
      }

      public DecisionTreeModel load(final SparkContext sc, final String path, final String algo, final int numNodes) {
         SparkSession spark = org.apache.spark.sql.SparkSession..MODULE$.builder().sparkContext(sc).getOrCreate();
         String dataPath = Loader$.MODULE$.dataPath(path);
         Dataset dataRDD = spark.read().parquet(dataPath);
         Loader$ var10000 = Loader$.MODULE$;
         StructType var10001 = dataRDD.schema();
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("org.apache.spark.mllib.tree.model.DecisionTreeModel.SaveLoadV1_0.NodeData").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         var10000.checkSchema(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2()));
         RDD nodes = dataRDD.rdd().map((r) -> DecisionTreeModel$SaveLoadV1_0$NodeData$.MODULE$.apply(r), .MODULE$.apply(DecisionTreeModel$SaveLoadV1_0$NodeData.class));
         Node[] trees = this.constructTrees(nodes);
         scala.Predef..MODULE$.assert(trees.length == 1, () -> "Decision tree should contain exactly one tree but got " + trees.length + " trees.");
         DecisionTreeModel model = new DecisionTreeModel(trees[0], Algo$.MODULE$.fromString(algo));
         scala.Predef..MODULE$.assert(model.numNodes() == numNodes, () -> "Unable to load DecisionTreeModel data from: " + dataPath + ". Expected " + numNodes + " nodes but found " + model.numNodes());
         return model;
      }

      public Node[] constructTrees(final RDD nodes) {
         Tuple2[] trees;
         ArraySeq treeIndices;
         Predef var10000;
         boolean var10001;
         label17: {
            label16: {
               trees = (Tuple2[])scala.collection.ArrayOps..MODULE$.sortBy$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(nodes.groupBy((x$5) -> BoxesRunTime.boxToInteger($anonfun$constructTrees$1(x$5)), .MODULE$.Int()), .MODULE$.Int(), .MODULE$.apply(Iterable.class), scala.math.Ordering.Int..MODULE$).mapValues((x$6) -> (DecisionTreeModel$SaveLoadV1_0$NodeData[])x$6.toArray(.MODULE$.apply(DecisionTreeModel$SaveLoadV1_0$NodeData.class))).collect()), (x0$1) -> {
                  if (x0$1 != null) {
                     int treeId = x0$1._1$mcI$sp();
                     DecisionTreeModel$SaveLoadV1_0$NodeData[] data = (DecisionTreeModel$SaveLoadV1_0$NodeData[])x0$1._2();
                     return new Tuple2(BoxesRunTime.boxToInteger(treeId), MODULE$.constructTree(data));
                  } else {
                     throw new MatchError(x0$1);
                  }
               }, .MODULE$.apply(Tuple2.class))), (x$7) -> BoxesRunTime.boxToInteger($anonfun$constructTrees$4(x$7)), scala.math.Ordering.Int..MODULE$);
               int numTrees = trees.length;
               treeIndices = org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])trees), (x$8) -> BoxesRunTime.boxToInteger($anonfun$constructTrees$5(x$8)), .MODULE$.Int())).toImmutableArraySeq();
               var10000 = scala.Predef..MODULE$;
               Range var5 = scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numTrees);
               if (treeIndices == null) {
                  if (var5 == null) {
                     break label16;
                  }
               } else if (treeIndices.equals(var5)) {
                  break label16;
               }

               var10001 = false;
               break label17;
            }

            var10001 = true;
         }

         var10000.assert(var10001, () -> "Tree indices must start from 0 and increment by 1, but we found " + treeIndices + ".");
         return (Node[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])trees), (x$9) -> (Node)x$9._2(), .MODULE$.apply(Node.class));
      }

      public Node constructTree(final DecisionTreeModel$SaveLoadV1_0$NodeData[] data) {
         Map dataMap = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(data), (n) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(n.nodeId())), n), .MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
         scala.Predef..MODULE$.assert(dataMap.contains(BoxesRunTime.boxToInteger(1)), () -> "DecisionTree missing root node (id = 1).");
         return this.constructNode(1, dataMap, (scala.collection.mutable.Map)scala.collection.mutable.Map..MODULE$.empty());
      }

      private Node constructNode(final int id, final Map dataMap, final scala.collection.mutable.Map nodes) {
         if (nodes.contains(BoxesRunTime.boxToInteger(id))) {
            return (Node)nodes.apply(BoxesRunTime.boxToInteger(id));
         } else {
            DecisionTreeModel$SaveLoadV1_0$NodeData data = (DecisionTreeModel$SaveLoadV1_0$NodeData)dataMap.apply(BoxesRunTime.boxToInteger(id));
            Node var10000;
            if (data.isLeaf()) {
               var10000 = Node$.MODULE$.apply(data.nodeId(), data.predict().toPredict(), data.impurity(), data.isLeaf());
            } else {
               Node leftNode = this.constructNode(BoxesRunTime.unboxToInt(data.leftNodeId().get()), dataMap, nodes);
               Node rightNode = this.constructNode(BoxesRunTime.unboxToInt(data.rightNodeId().get()), dataMap, nodes);
               InformationGainStats stats = new InformationGainStats(BoxesRunTime.unboxToDouble(data.infoGain().get()), data.impurity(), leftNode.impurity(), rightNode.impurity(), leftNode.predict(), rightNode.predict());
               var10000 = new Node(data.nodeId(), data.predict().toPredict(), data.impurity(), data.isLeaf(), data.split().map((x$10) -> x$10.toSplit()), new Some(leftNode), new Some(rightNode), new Some(stats));
            }

            Node node = var10000;
            nodes.$plus$eq(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToInteger(node.id())), node));
            return node;
         }
      }

      // $FF: synthetic method
      public static final int $anonfun$save$2(final String str) {
         return org.apache.spark.util.Utils..MODULE$.memoryStringToMb(str);
      }

      // $FF: synthetic method
      public static final JValue $anonfun$save$10(final int x) {
         return org.json4s.JsonDSL..MODULE$.int2jvalue(x);
      }

      // $FF: synthetic method
      public static final int $anonfun$constructTrees$1(final DecisionTreeModel$SaveLoadV1_0$NodeData x$5) {
         return x$5.treeId();
      }

      // $FF: synthetic method
      public static final int $anonfun$constructTrees$4(final Tuple2 x$7) {
         return x$7._1$mcI$sp();
      }

      // $FF: synthetic method
      public static final int $anonfun$constructTrees$5(final Tuple2 x$8) {
         return x$8._1$mcI$sp();
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
