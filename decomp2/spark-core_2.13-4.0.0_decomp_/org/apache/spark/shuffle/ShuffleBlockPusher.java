package org.apache.spark.shuffle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.ConnectException;
import java.nio.ByteBuffer;
import java.util.Map;
import org.apache.spark.SecurityManager;
import org.apache.spark.SecurityManager$;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.executor.CoarseGrainedExecutorBackend;
import org.apache.spark.executor.ExecutorBackend;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.network.buffer.FileSegmentManagedBuffer;
import org.apache.spark.network.buffer.ManagedBuffer;
import org.apache.spark.network.buffer.NioManagedBuffer;
import org.apache.spark.network.netty.SparkTransportConf$;
import org.apache.spark.network.server.BlockPushNonFatalFailure;
import org.apache.spark.network.shuffle.BlockPushingListener;
import org.apache.spark.network.shuffle.ErrorHandler;
import org.apache.spark.network.util.TransportConf;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManagerId;
import org.apache.spark.storage.ShufflePushBlockId;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.AbstractFunction2;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.LongRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011}a!B0a\u0001\tD\u0007\u0002C;\u0001\u0005\u0003\u0005\u000b\u0011B<\t\u000bm\u0004A\u0011\u0001?\t\u0011\u0005\u0005\u0001\u0001)A\u0005\u0003\u0007A\u0001\"!\u0003\u0001A\u0003%\u00111\u0001\u0005\t\u0003\u0017\u0001\u0001\u0015!\u0003\u0002\u0004!A\u0011Q\u0002\u0001!\u0002\u0013\ty\u0001\u0003\u0005\u0002\u0016\u0001\u0001\u000b\u0011BA\b\u0011)\t9\u0002\u0001a\u0001\n\u0003\u0001\u0017\u0011\u0004\u0005\u000b\u00037\u0001\u0001\u0019!C\u0001A\u0006u\u0001\u0002CA\u0015\u0001\u0001\u0006K!a\u0001\t\u0011\u0005-\u0002\u0001)Q\u0005\u0003\u001fA\u0001\"!\f\u0001A\u0003%\u0011q\u0006\u0005\t\u0003\u0017\u0002\u0001\u0015!\u0003\u0002N!A11\u0004\u0001!\u0002\u0013\ty\u0005\u0003\u0005\u0004\u001e\u0001\u0001\u000b\u0011BB\u0010\u0011)\u00199\u0004\u0001b\u0001\n\u0003\u00017\u0011\b\u0005\t\u0007\u0003\u0002\u0001\u0015!\u0003\u0004<!A11\t\u0001!B\u0013\ty\u0001\u0003\u0005\u0004F\u0001\u0001\u000b\u0015BA\b\u0011!\u00199\u0005\u0001Q!\n\u0005=\u0001\u0002CB%\u0001\u0001\u0006KA!\u000f\t\u0011\r-\u0003\u0001\"\u0001a\u0007\u001bB\u0001ba\u0014\u0001\t\u0003\u00017\u0011\u000b\u0005\t\u0007'\u0002A\u0011\u00011\u0004V!A1Q\u0013\u0001\u0005\u0002\u0001\u001cI\u0002C\u0004\u0004\u0018\u0002!\tb!'\t\u000f\r\u0015\u0006\u0001\"\u0003\u0004\u001a!91q\u0015\u0001\u0005\n\r%\u0006bBBX\u0001\u0011%1\u0011\u0017\u0005\b\u0007{\u0003A\u0011BB`\u0011\u001d\u0019\u0019\u000e\u0001C\t\u00073A\u0001b!6\u0001\t\u0003\u00017q\u001b\u0005\b\u0007\u007f\u0004A\u0011\u0003C\u0001\u000f!\ty\u0007\u0019E\u0001E\u0006EdaB0a\u0011\u0003\u0011\u00171\u000f\u0005\u0007w\u000e\"\t!!\u001e\u0007\u000f\u0005]4\u0005\u00112\u0002z!Q\u00111S\u0013\u0003\u0016\u0004%\t!!&\t\u0015\u0005]UE!E!\u0002\u0013\ty\u0004\u0003\u0006\u0002\u001a\u0016\u0012)\u001a!C\u0001\u00037C!\"a,&\u0005#\u0005\u000b\u0011BAO\u0011)\t\t,\nBK\u0002\u0013\u0005\u00111\u0017\u0005\u000b\u0003\u000b,#\u0011#Q\u0001\n\u0005U\u0006BB>&\t\u0003\t9\rC\u0005\u0002T\u0016\u0012\r\u0011\"\u0001\u0002V\"A\u0011q[\u0013!\u0002\u0013\ty\u0001C\u0005\u0002Z\u0016\n\t\u0011\"\u0001\u0002\\\"I\u00111]\u0013\u0012\u0002\u0013\u0005\u0011Q\u001d\u0005\n\u0003w,\u0013\u0013!C\u0001\u0003{D\u0011B!\u0001&#\u0003%\tAa\u0001\t\u0013\t\u001dQ%!A\u0005B\t%\u0001\"\u0003B\u000eK\u0005\u0005I\u0011AAk\u0011%\u0011i\"JA\u0001\n\u0003\u0011y\u0002C\u0005\u0003*\u0015\n\t\u0011\"\u0011\u0003,!I!QG\u0013\u0002\u0002\u0013\u0005!q\u0007\u0005\n\u0005\u0003*\u0013\u0011!C!\u0005\u0007B\u0011Ba\u0012&\u0003\u0003%\tE!\u0013\t\u0013\t-S%!A\u0005B\t5\u0003\"\u0003B(K\u0005\u0005I\u0011\tB)\u000f)\u0011)fIA\u0001\u0012\u0003\u0011'q\u000b\u0004\u000b\u0003o\u001a\u0013\u0011!E\u0001E\ne\u0003BB>>\t\u0003\u0011\t\bC\u0005\u0003Lu\n\t\u0011\"\u0012\u0003N!I!1O\u001f\u0002\u0002\u0013\u0005%Q\u000f\u0005\n\u0005{j\u0014\u0011!CA\u0005\u007fB\u0011B!%>\u0003\u0003%IAa%\u0007\r\tm5\u0005\u0012BO\u0011)\u0011yj\u0011BK\u0002\u0013\u0005!\u0011\u0015\u0005\u000b\u0005c\u001b%\u0011#Q\u0001\n\t\r\u0006B\u0003BZ\u0007\nU\r\u0011\"\u0001\u00036\"Q!QX\"\u0003\u0012\u0003\u0006IAa.\t\rm\u001cE\u0011\u0001B`\u0011%\tInQA\u0001\n\u0003\u00119\rC\u0005\u0002d\u000e\u000b\n\u0011\"\u0001\u0003N\"I\u00111`\"\u0012\u0002\u0013\u0005!\u0011\u001b\u0005\n\u0005\u000f\u0019\u0015\u0011!C!\u0005\u0013A\u0011Ba\u0007D\u0003\u0003%\t!!6\t\u0013\tu1)!A\u0005\u0002\tU\u0007\"\u0003B\u0015\u0007\u0006\u0005I\u0011\tB\u0016\u0011%\u0011)dQA\u0001\n\u0003\u0011I\u000eC\u0005\u0003B\r\u000b\t\u0011\"\u0011\u0003^\"I!qI\"\u0002\u0002\u0013\u0005#\u0011\n\u0005\n\u0005\u0017\u001a\u0015\u0011!C!\u0005\u001bB\u0011Ba\u0014D\u0003\u0003%\tE!9\b\u0013\t\u00158%!A\t\n\t\u001dh!\u0003BNG\u0005\u0005\t\u0012\u0002Bu\u0011\u0019Yh\u000b\"\u0001\u0003r\"I!1\n,\u0002\u0002\u0013\u0015#Q\n\u0005\n\u0005g2\u0016\u0011!CA\u0005gD\u0011B! W\u0003\u0003%\tI!?\t\u0013\tEe+!A\u0005\n\tM\u0005\"CB\u0001G\t\u0007I\u0011BB\u0002\u0011!\u0019)b\tQ\u0001\n\r\u0015\u0001\u0002CB\fG\u0011\u0005!m!\u0007\u0003%MCWO\u001a4mK\ncwnY6QkNDWM\u001d\u0006\u0003C\n\fqa\u001d5vM\u001adWM\u0003\u0002dI\u0006)1\u000f]1sW*\u0011QMZ\u0001\u0007CB\f7\r[3\u000b\u0003\u001d\f1a\u001c:h'\r\u0001\u0011n\u001c\t\u0003U6l\u0011a\u001b\u0006\u0002Y\u0006)1oY1mC&\u0011an\u001b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005A\u001cX\"A9\u000b\u0005I\u0014\u0017\u0001C5oi\u0016\u0014h.\u00197\n\u0005Q\f(a\u0002'pO\u001eLgnZ\u0001\u0005G>tgm\u0001\u0001\u0011\u0005aLX\"\u00012\n\u0005i\u0014'!C*qCJ\\7i\u001c8g\u0003\u0019a\u0014N\\5u}Q\u0011Qp \t\u0003}\u0002i\u0011\u0001\u0019\u0005\u0006k\n\u0001\ra^\u0001\u0013[\u0006D(\t\\8dWNK'0\u001a+p!V\u001c\b\u000eE\u0002k\u0003\u000bI1!a\u0002l\u0005\u0011auN\\4\u0002#5\f\u0007P\u00117pG.\u0014\u0015\r^2i'&TX-\u0001\tnCb\u0014\u0015\u0010^3t\u0013:4E.[4ii\u0006yQ.\u0019=SKF\u001c\u0018J\u001c$mS\u001eDG\u000fE\u0002k\u0003#I1!a\u0005l\u0005\rIe\u000e^\u0001\u001c[\u0006D(\t\\8dWNLeN\u00127jO\"$\b+\u001a:BI\u0012\u0014Xm]:\u0002\u001b\tLH/Z:J]\u001ac\u0017n\u001a5u+\t\t\u0019!A\tcsR,7/\u00138GY&<\u0007\u000e^0%KF$B!a\b\u0002&A\u0019!.!\t\n\u0007\u0005\r2N\u0001\u0003V]&$\b\"CA\u0014\u0013\u0005\u0005\t\u0019AA\u0002\u0003\rAH%M\u0001\u000fEf$Xm]%o\r2Lw\r\u001b;!\u00031\u0011X-]:J]\u001ac\u0017n\u001a5u\u0003mqW/\u001c\"m_\u000e\\7/\u00138GY&<\u0007\u000e\u001e)fe\u0006#GM]3tgBA\u0011\u0011GA\u001e\u0003\u007f\ty!\u0004\u0002\u00024)!\u0011QGA\u001c\u0003\u001diW\u000f^1cY\u0016T1!!\u000fl\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003{\t\u0019DA\u0004ICNDW*\u00199\u0011\t\u0005\u0005\u0013qI\u0007\u0003\u0003\u0007R1!!\u0012c\u0003\u001d\u0019Ho\u001c:bO\u0016LA!!\u0013\u0002D\tq!\t\\8dW6\u000bg.Y4fe&#\u0017\u0001\u00063fM\u0016\u0014(/\u001a3QkND'+Z9vKN$8\u000f\u0005\u0005\u00022\u0005m\u0012qHA(!\u0019\t\t$!\u0015\u0002V%!\u00111KA\u001a\u0005\u0015\tV/Z;f!\r\t9&\n\b\u0004\u00033\u0012c\u0002BA.\u0003[rA!!\u0018\u0002l9!\u0011qLA5\u001d\u0011\t\t'a\u001a\u000e\u0005\u0005\r$bAA3m\u00061AH]8pizJ\u0011aZ\u0005\u0003K\u001aL!a\u00193\n\u0005\u0005\u0014\u0017AE*ik\u001a4G.\u001a\"m_\u000e\\\u0007+^:iKJ\u0004\"A`\u0012\u0014\u0005\rJGCAA9\u0005-\u0001Vo\u001d5SKF,Xm\u001d;\u0014\r\u0015J\u00171PAA!\rQ\u0017QP\u0005\u0004\u0003\u007fZ'a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003\u0007\u000biI\u0004\u0003\u0002\u0006\u0006%e\u0002BA1\u0003\u000fK\u0011\u0001\\\u0005\u0004\u0003\u0017[\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003\u001f\u000b\tJ\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\f.\fq!\u00193ee\u0016\u001c8/\u0006\u0002\u0002@\u0005A\u0011\r\u001a3sKN\u001c\b%\u0001\u0004cY>\u001c7n]\u000b\u0003\u0003;\u0003b!a!\u0002 \u0006\r\u0016\u0002BAQ\u0003#\u00131aU3r!\u001dQ\u0017QUAU\u0003\u001fI1!a*l\u0005\u0019!V\u000f\u001d7feA!\u0011\u0011IAV\u0013\u0011\ti+a\u0011\u0003\u000f\tcwnY6JI\u00069!\r\\8dWN\u0004\u0013!\u0003:fc\n+hMZ3s+\t\t)\f\u0005\u0003\u00028\u0006\u0005WBAA]\u0015\u0011\tY,!0\u0002\r\t,hMZ3s\u0015\r\tyLY\u0001\b]\u0016$xo\u001c:l\u0013\u0011\t\u0019-!/\u0003\u001b5\u000bg.Y4fI\n+hMZ3s\u0003)\u0011X-\u001d\"vM\u001a,'\u000f\t\u000b\t\u0003\u0013\fi-a4\u0002RB\u0019\u00111Z\u0013\u000e\u0003\rBq!a%-\u0001\u0004\ty\u0004C\u0004\u0002\u001a2\u0002\r!!(\t\u000f\u0005EF\u00061\u0001\u00026\u0006!1/\u001b>f+\t\ty!A\u0003tSj,\u0007%\u0001\u0003d_BLH\u0003CAe\u0003;\fy.!9\t\u0013\u0005Mu\u0006%AA\u0002\u0005}\u0002\"CAM_A\u0005\t\u0019AAO\u0011%\t\tl\fI\u0001\u0002\u0004\t),\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u001d(\u0006BA \u0003S\\#!a;\u0011\t\u00055\u0018q_\u0007\u0003\u0003_TA!!=\u0002t\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003k\\\u0017AC1o]>$\u0018\r^5p]&!\u0011\u0011`Ax\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\t\tyP\u000b\u0003\u0002\u001e\u0006%\u0018AD2paf$C-\u001a4bk2$HeM\u000b\u0003\u0005\u000bQC!!.\u0002j\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"Aa\u0003\u0011\t\t5!qC\u0007\u0003\u0005\u001fQAA!\u0005\u0003\u0014\u0005!A.\u00198h\u0015\t\u0011)\"\u0001\u0003kCZ\f\u0017\u0002\u0002B\r\u0005\u001f\u0011aa\u0015;sS:<\u0017\u0001\u00049s_\u0012,8\r^!sSRL\u0018A\u00049s_\u0012,8\r^#mK6,g\u000e\u001e\u000b\u0005\u0005C\u00119\u0003E\u0002k\u0005GI1A!\nl\u0005\r\te.\u001f\u0005\n\u0003O)\u0014\u0011!a\u0001\u0003\u001f\tq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0005[\u0001bAa\f\u00032\t\u0005RBAA\u001c\u0013\u0011\u0011\u0019$a\u000e\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0005s\u0011y\u0004E\u0002k\u0005wI1A!\u0010l\u0005\u001d\u0011un\u001c7fC:D\u0011\"a\n8\u0003\u0003\u0005\rA!\t\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0005\u0017\u0011)\u0005C\u0005\u0002(a\n\t\u00111\u0001\u0002\u0010\u0005A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002\u0010\u0005AAo\\*ue&tw\r\u0006\u0002\u0003\f\u00051Q-];bYN$BA!\u000f\u0003T!I\u0011qE\u001e\u0002\u0002\u0003\u0007!\u0011E\u0001\f!V\u001c\bNU3rk\u0016\u001cH\u000fE\u0002\u0002Lv\u001aR!\u0010B.\u0005O\u0002BB!\u0018\u0003d\u0005}\u0012QTA[\u0003\u0013l!Aa\u0018\u000b\u0007\t\u00054.A\u0004sk:$\u0018.\\3\n\t\t\u0015$q\f\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\u001c\u0004\u0003\u0002B5\u0005_j!Aa\u001b\u000b\t\t5$1C\u0001\u0003S>LA!a$\u0003lQ\u0011!qK\u0001\u0006CB\u0004H.\u001f\u000b\t\u0003\u0013\u00149H!\u001f\u0003|!9\u00111\u0013!A\u0002\u0005}\u0002bBAM\u0001\u0002\u0007\u0011Q\u0014\u0005\b\u0003c\u0003\u0005\u0019AA[\u0003\u001d)h.\u00199qYf$BA!!\u0003\u000eB)!Na!\u0003\b&\u0019!QQ6\u0003\r=\u0003H/[8o!%Q'\u0011RA \u0003;\u000b),C\u0002\u0003\f.\u0014a\u0001V;qY\u0016\u001c\u0004\"\u0003BH\u0003\u0006\u0005\t\u0019AAe\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005+\u0003BA!\u0004\u0003\u0018&!!\u0011\u0014B\b\u0005\u0019y%M[3di\nQ\u0001+^:i%\u0016\u001cX\u000f\u001c;\u0014\r\rK\u00171PAA\u0003\u001d\u0011Gn\\2l\u0013\u0012,\"Aa)\u0011\t\t\u0015&Q\u0016\b\u0005\u0005O\u0013I\u000bE\u0002\u0002b-L1Aa+l\u0003\u0019\u0001&/\u001a3fM&!!\u0011\u0004BX\u0015\r\u0011Yk[\u0001\tE2|7m[%eA\u00059a-Y5mkJ,WC\u0001B\\!\u0011\t\u0019I!/\n\t\tm\u0016\u0011\u0013\u0002\n)\"\u0014xn^1cY\u0016\f\u0001BZ1jYV\u0014X\r\t\u000b\u0007\u0005\u0003\u0014\u0019M!2\u0011\u0007\u0005-7\tC\u0004\u0003 \"\u0003\rAa)\t\u000f\tM\u0006\n1\u0001\u00038R1!\u0011\u0019Be\u0005\u0017D\u0011Ba(J!\u0003\u0005\rAa)\t\u0013\tM\u0016\n%AA\u0002\t]VC\u0001BhU\u0011\u0011\u0019+!;\u0016\u0005\tM'\u0006\u0002B\\\u0003S$BA!\t\u0003X\"I\u0011q\u0005(\u0002\u0002\u0003\u0007\u0011q\u0002\u000b\u0005\u0005s\u0011Y\u000eC\u0005\u0002(A\u000b\t\u00111\u0001\u0003\"Q!!1\u0002Bp\u0011%\t9#UA\u0001\u0002\u0004\ty\u0001\u0006\u0003\u0003:\t\r\b\"CA\u0014)\u0006\u0005\t\u0019\u0001B\u0011\u0003)\u0001Vo\u001d5SKN,H\u000e\u001e\t\u0004\u0003\u001746#\u0002,\u0003l\n\u001d\u0004C\u0003B/\u0005[\u0014\u0019Ka.\u0003B&!!q\u001eB0\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|gN\r\u000b\u0003\u0005O$bA!1\u0003v\n]\bb\u0002BP3\u0002\u0007!1\u0015\u0005\b\u0005gK\u0006\u0019\u0001B\\)\u0011\u0011YPa@\u0011\u000b)\u0014\u0019I!@\u0011\u000f)\f)Ka)\u00038\"I!q\u0012.\u0002\u0002\u0003\u0007!\u0011Y\u0001\u0012\u00052{5iS0Q+NCUIU0Q\u001f>cUCAB\u0003!\u0011\u00199a!\u0005\u000e\u0005\r%!\u0002BB\u0006\u0007\u001b\t!bY8oGV\u0014(/\u001a8u\u0015\u0011\u0019yAa\u0005\u0002\tU$\u0018\u000e\\\u0005\u0005\u0007'\u0019IAA\bFq\u0016\u001cW\u000f^8s'\u0016\u0014h/[2f\u0003I\u0011EjT\"L?B+6\u000bS#S?B{u\n\u0014\u0011\u0002\tM$x\u000e\u001d\u000b\u0003\u0003?\tA\u0002];tQJ+\u0017/^3tiN\fA\"\u001a:s_JD\u0015M\u001c3mKJ\u0004Ba!\t\u000429!11EB\u0016\u001d\u0011\u0019)c!\u000b\u000f\t\u0005m3qE\u0005\u0004\u0003\u007f\u0013\u0017bA1\u0002>&!1QFB\u0018\u00031)%O]8s\u0011\u0006tG\r\\3s\u0015\r\t\u0017QX\u0005\u0005\u0007g\u0019)DA\u000bCY>\u001c7\u000eU;tQ\u0016\u0013(o\u001c:IC:$G.\u001a:\u000b\t\r52qF\u0001\u0015k:\u0014X-Y2iC\ndWM\u00117pG.luM]:\u0016\u0005\rm\u0002CBA\u0019\u0007{\ty$\u0003\u0003\u0004@\u0005M\"a\u0002%bg\"\u001cV\r^\u0001\u0016k:\u0014X-Y2iC\ndWM\u00117pG.luM]:!\u0003%\u0019\b.\u001e4gY\u0016LE-\u0001\u0005nCBLe\u000eZ3y\u00039\u0019\b.\u001e4gY\u0016lUM]4f\u0013\u0012\fa\u0003];tQ\u000e{W\u000e\u001d7fi&|gNT8uS\u001aLW\rZ\u0001\u0013GJ,\u0017\r^3FeJ|'\u000fS1oI2,'\u000f\u0006\u0002\u0004 \u0005A\u0012n\u001d)vg\"\u001cu.\u001c9mKRLwN\u001c(pi&4\u0017.\u001a3\u0016\u0005\te\u0012!E5oSRL\u0017\r^3CY>\u001c7\u000eU;tQRQ\u0011qDB,\u0007C\u001aYga%\t\u000f\re\u0003\u00041\u0001\u0004\\\u0005AA-\u0019;b\r&dW\r\u0005\u0003\u0003j\ru\u0013\u0002BB0\u0005W\u0012AAR5mK\"911\r\rA\u0002\r\u0015\u0014\u0001\u00059beRLG/[8o\u0019\u0016tw\r\u001e5t!\u0015Q7qMA\u0002\u0013\r\u0019Ig\u001b\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0007[B\u0002\u0019AB8\u0003\r!W\r\u001d\u0019\t\u0007c\u001aYh!#\u0004\u0010BI\u0001pa\u001d\u0004x\r\u001d5QR\u0005\u0004\u0007k\u0012'!E*ik\u001a4G.\u001a#fa\u0016tG-\u001a8dsB!1\u0011PB>\u0019\u0001!Ab! \u0004l\u0005\u0005\t\u0011!B\u0001\u0007\u007f\u00121a\u0018\u00132#\u0011\u0019\tI!\t\u0011\u0007)\u001c\u0019)C\u0002\u0004\u0006.\u0014qAT8uQ&tw\r\u0005\u0003\u0004z\r%E\u0001DBF\u0007W\n\t\u0011!A\u0003\u0002\r}$aA0%eA!1\u0011PBH\t1\u0019\tja\u001b\u0002\u0002\u0003\u0005)\u0011AB@\u0005\ryFe\r\u0005\b\u0007\u000bB\u0002\u0019AA\b\u00039!(/\u001f)vg\",\u0006\u000fV8NCb\f!b];c[&$H+Y:l)\u0011\tyba'\t\u000f\ru%\u00041\u0001\u0004 \u0006!A/Y:l!\u0011\u0011ia!)\n\t\r\r&q\u0002\u0002\t%Vtg.\u00192mK\u0006Y\u0001/^:i+B$v.T1y\u0003-\u0019XM\u001c3SKF,Xm\u001d;\u0015\t\u0005}11\u0016\u0005\b\u0007[c\u0002\u0019AA+\u0003\u001d\u0011X-];fgR\fad\u001d7jG\u0016\u0014V-\u001d\"vM\u001a,'/\u00138u_\ncwnY6Ck\u001a4WM]:\u0015\r\rM6QWB\\!\u0015Q7qMA[\u0011\u001d\t\t,\ba\u0001\u0003kCqa!/\u001e\u0001\u0004\u0019Y,\u0001\u0006cY>\u001c7nU5{KN\u0004b!a!\u0002 \u0006=\u0011!H;qI\u0006$Xm\u0015;bi\u0016\fe\u000eZ\"iK\u000e\\\u0017J\u001a)vg\"luN]3\u0015\u0015\te2\u0011YBc\u0007\u000f\u001ci\rC\u0004\u0004Dz\u0001\r!a\u0001\u0002\u0017\tLH/Z:QkNDW\r\u001a\u0005\b\u0003's\u0002\u0019AA \u0011\u001d\u0019IM\ba\u0001\u0007\u0017\fqB]3nC&t\u0017N\\4CY>\u001c7n\u001d\t\u0007\u0003c\u0019iDa)\t\u000f\r=g\u00041\u0001\u0004R\u0006Q\u0001/^:i%\u0016\u001cX\u000f\u001c;\u0011\u0007\u0005]3)A\u0010o_RLg-\u001f#sSZ,'/\u00112pkR\u0004Vo\u001d5D_6\u0004H.\u001a;j_:\f\u0001\u0004\u001d:fa\u0006\u0014XM\u00117pG.\u0004Vo\u001d5SKF,Xm\u001d;t)I\u0019Ina7\u0004`\u000e\r8Q]Bt\u0007S\u001cYo!=\u0011\r\u0005\r\u0015qTA+\u0011\u001d\u0019i\u000e\ta\u0001\u0003\u001f\tQB\\;n!\u0006\u0014H/\u001b;j_:\u001c\bbBBqA\u0001\u0007\u0011qB\u0001\fa\u0006\u0014H/\u001b;j_:LE\rC\u0004\u0004D\u0001\u0002\r!a\u0004\t\u000f\r\u001d\u0003\u00051\u0001\u0002\u0010!91\u0011\f\u0011A\u0002\rm\u0003bBB2A\u0001\u00071Q\r\u0005\b\u0007[\u0004\u0003\u0019ABx\u0003)iWM]4fe2{7m\u001d\t\u0007\u0003\u0007\u000by*a\u0010\t\u000f\rM\b\u00051\u0001\u0004v\u0006iAO]1ogB|'\u000f^\"p]\u001a\u0004Baa>\u0004|6\u00111\u0011 \u0006\u0005\u0007\u001f\ti,\u0003\u0003\u0004~\u000ee(!\u0004+sC:\u001c\bo\u001c:u\u0007>tg-A\nde\u0016\fG/\u001a*fcV,7\u000f\u001e\"vM\u001a,'\u000f\u0006\u0006\u00026\u0012\rAQ\u0001C\u0004\t\u0017Aa!^\u0011A\u0002\rU\bbBB-C\u0001\u000711\f\u0005\b\t\u0013\t\u0003\u0019AA\u0002\u0003\u0019ygMZ:fi\"9AQB\u0011A\u0002\u0005\r\u0011A\u00027f]\u001e$\b\u000eK\u0003\u0001\t#!Y\u0002\u0005\u0003\u0005\u0014\u0011]QB\u0001C\u000b\u0015\r\t)PY\u0005\u0005\t3!)BA\u0003TS:\u001cW-\t\u0002\u0005\u001e\u0005)1G\f\u001a/a\u0001"
)
public class ShuffleBlockPusher implements Logging {
   private final SparkConf conf;
   private final long maxBlockSizeToPush;
   private final long maxBlockBatchSize;
   private final long maxBytesInFlight;
   private final int maxReqsInFlight;
   private final int maxBlocksInFlightPerAddress;
   private long bytesInFlight;
   private int reqsInFlight;
   private final HashMap numBlocksInFlightPerAddress;
   private final HashMap deferredPushRequests;
   private final Queue pushRequests;
   public final ErrorHandler.BlockPushErrorHandler org$apache$spark$shuffle$ShuffleBlockPusher$$errorHandler;
   private final HashSet unreachableBlockMgrs;
   private int shuffleId;
   private int mapIndex;
   private int shuffleMergeId;
   private boolean pushCompletionNotified;
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

   public long bytesInFlight() {
      return this.bytesInFlight;
   }

   public void bytesInFlight_$eq(final long x$1) {
      this.bytesInFlight = x$1;
   }

   public HashSet unreachableBlockMgrs() {
      return this.unreachableBlockMgrs;
   }

   public ErrorHandler.BlockPushErrorHandler createErrorHandler() {
      return new ErrorHandler.BlockPushErrorHandler() {
         public boolean shouldRetryError(final Throwable t) {
            if (t.getCause() != null && t.getCause() instanceof FileNotFoundException) {
               return false;
            } else {
               return !(t instanceof BlockPushNonFatalFailure) || !BlockPushNonFatalFailure.shouldNotRetryErrorCode(((BlockPushNonFatalFailure)t).getReturnCode());
            }
         }
      };
   }

   public boolean isPushCompletionNotified() {
      return this.pushCompletionNotified;
   }

   public void initiateBlockPush(final File dataFile, final long[] partitionLengths, final ShuffleDependency dep, final int mapIndex) {
      int numPartitions = dep.partitioner().numPartitions();
      SecurityManager securityManager = new SecurityManager(this.conf, SecurityManager$.MODULE$.$lessinit$greater$default$2(), SecurityManager$.MODULE$.$lessinit$greater$default$3());
      SparkConf x$1 = this.conf;
      String x$2 = "shuffle";
      Some x$3 = new Some(securityManager.getRpcSSLOptions());
      int x$4 = SparkTransportConf$.MODULE$.fromSparkConf$default$3();
      Option x$5 = SparkTransportConf$.MODULE$.fromSparkConf$default$4();
      TransportConf transportConf = SparkTransportConf$.MODULE$.fromSparkConf(x$1, "shuffle", x$4, x$5, x$3);
      this.shuffleId = dep.shuffleId();
      this.shuffleMergeId = dep.shuffleMergeId();
      this.mapIndex = mapIndex;
      Seq requests = this.prepareBlockPushRequests(numPartitions, mapIndex, dep.shuffleId(), dep.shuffleMergeId(), dataFile, partitionLengths, dep.getMergerLocs(), transportConf);
      this.pushRequests.$plus$plus$eq(Utils$.MODULE$.randomize(requests, .MODULE$.apply(PushRequest.class)));
      if (this.pushRequests.isEmpty()) {
         this.notifyDriverAboutPushCompletion();
      } else {
         this.submitTask(() -> this.tryPushUpToMax());
      }
   }

   public void tryPushUpToMax() {
      try {
         this.pushUpToMax();
      } catch (Throwable var5) {
         if (var5 == null || !scala.util.control.NonFatal..MODULE$.apply(var5)) {
            throw var5;
         }

         this.logWarning((Function0)(() -> "Failure during push so stopping the block push"), var5);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public void submitTask(final Runnable task) {
      if (ShuffleBlockPusher$.MODULE$.org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL() != null && !ShuffleBlockPusher$.MODULE$.org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL().isShutdown()) {
         ShuffleBlockPusher$.MODULE$.org$apache$spark$shuffle$ShuffleBlockPusher$$BLOCK_PUSHER_POOL().execute(task);
      }
   }

   private synchronized void pushUpToMax() {
      if (this.deferredPushRequests.nonEmpty()) {
         this.deferredPushRequests.withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$pushUpToMax$1(check$ifrefutable$1))).foreach((x$1) -> {
            $anonfun$pushUpToMax$2(this, x$1);
            return BoxedUnit.UNIT;
         });
      }

      while(this.isRemoteBlockPushable$1(this.pushRequests)) {
         PushRequest request = (PushRequest)this.pushRequests.dequeue();
         BlockManagerId remoteAddress = request.address();
         if (this.isRemoteAddressMaxedOut$1(remoteAddress, request)) {
            this.logDebug((Function0)(() -> "Deferring push request for " + remoteAddress + " with " + request.blocks().size() + " blocks"));
            ((Queue)this.deferredPushRequests.getOrElseUpdate(remoteAddress, () -> new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1()))).enqueue(request);
         } else {
            this.sendRequest(request);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      }

   }

   private void sendRequest(final PushRequest request) {
      this.bytesInFlight_$eq(this.bytesInFlight() + (long)request.size());
      ++this.reqsInFlight;
      this.numBlocksInFlightPerAddress.update(request.address(), BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.numBlocksInFlightPerAddress.getOrElseUpdate(request.address(), (JFunction0.mcI.sp)() -> 0)) + request.blocks().length()));
      scala.collection.immutable.Map sizeMap = ((IterableOnceOps)request.blocks().map((x0$1) -> {
         if (x0$1 != null) {
            BlockId blockId = (BlockId)x0$1._1();
            int size = x0$1._2$mcI$sp();
            return new Tuple2(blockId.toString(), BoxesRunTime.boxToInteger(size));
         } else {
            throw new MatchError(x0$1);
         }
      })).toMap(scala..less.colon.less..MODULE$.refl());
      BlockManagerId address = request.address();
      Seq blockIds = (Seq)request.blocks().map((x$2) -> ((BlockId)x$2._1()).toString());
      HashSet remainingBlocks = (HashSet)(new HashSet()).$plus$plus$eq(blockIds);
      BlockPushingListener blockPushListener = new BlockPushingListener(sizeMap, address, remainingBlocks) {
         // $FF: synthetic field
         private final ShuffleBlockPusher $outer;
         private final scala.collection.immutable.Map sizeMap$1;
         private final BlockManagerId address$1;
         private final HashSet remainingBlocks$1;

         public void onBlockTransferSuccess(final String x$1, final ManagedBuffer x$2) {
            super.onBlockTransferSuccess(x$1, x$2);
         }

         public void onBlockTransferFailure(final String x$1, final Throwable x$2) {
            super.onBlockTransferFailure(x$1, x$2);
         }

         public String getTransferType() {
            return super.getTransferType();
         }

         public void handleResult(final PushResult result) {
            this.$outer.submitTask(() -> {
               if (this.$outer.org$apache$spark$shuffle$ShuffleBlockPusher$$updateStateAndCheckIfPushMore((long)BoxesRunTime.unboxToInt(this.sizeMap$1.apply(result.blockId())), this.address$1, this.remainingBlocks$1, result)) {
                  this.$outer.tryPushUpToMax();
               }
            });
         }

         public void onBlockPushSuccess(final String blockId, final ManagedBuffer data) {
            this.$outer.logTrace((Function0)(() -> "Push for block " + blockId + " to " + this.address$1 + " successful."));
            this.handleResult(new PushResult(blockId, (Throwable)null));
         }

         public void onBlockPushFailure(final String blockId, final Throwable exception) {
            if (!this.$outer.org$apache$spark$shuffle$ShuffleBlockPusher$$errorHandler.shouldLogError(exception)) {
               this.$outer.logTrace((Function0)(() -> "Pushing block " + blockId + " to " + this.address$1 + " failed."), exception);
            } else {
               this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Pushing block ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, blockId)}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"to ", " failed."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, this.address$1)}))))), exception);
            }

            this.handleResult(new PushResult(blockId, exception));
         }

         public {
            if (ShuffleBlockPusher.this == null) {
               throw null;
            } else {
               this.$outer = ShuffleBlockPusher.this;
               this.sizeMap$1 = sizeMap$1;
               this.address$1 = address$1;
               this.remainingBlocks$1 = remainingBlocks$1;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      Tuple2 var9 = Utils$.MODULE$.randomize((IterableOnce)blockIds.zip(scala.Predef..MODULE$.wrapRefArray((Object[])this.sliceReqBufferIntoBlockBuffers(request.reqBuffer(), (Seq)request.blocks().map((x$3) -> BoxesRunTime.boxToInteger($anonfun$sendRequest$4(x$3)))))), .MODULE$.apply(Tuple2.class)).unzip(scala.Predef..MODULE$.$conforms());
      if (var9 != null) {
         Seq blockPushIds = (Seq)var9._1();
         Seq blockPushBuffers = (Seq)var9._2();
         Tuple2 var8 = new Tuple2(blockPushIds, blockPushBuffers);
         Seq blockPushIds = (Seq)var8._1();
         Seq blockPushBuffers = (Seq)var8._2();
         SparkEnv$.MODULE$.get().blockManager().blockStoreClient().pushBlocks(address.host(), address.port(), (String[])blockPushIds.toArray(.MODULE$.apply(String.class)), (ManagedBuffer[])blockPushBuffers.toArray(.MODULE$.apply(ManagedBuffer.class)), blockPushListener);
      } else {
         throw new MatchError(var9);
      }
   }

   private ManagedBuffer[] sliceReqBufferIntoBlockBuffers(final ManagedBuffer reqBuffer, final Seq blockSizes) {
      if (blockSizes.size() == 1) {
         return (ManagedBuffer[])((Object[])(new ManagedBuffer[]{reqBuffer}));
      } else {
         ByteBuffer inMemoryBuffer = reqBuffer.nioByteBuffer();
         int[] blockOffsets = new int[blockSizes.size()];
         IntRef offset = IntRef.create(0);
         blockSizes.indices().foreach$mVc$sp((JFunction1.mcVI.sp)(index) -> {
            blockOffsets[index] = offset.elem;
            offset.elem += BoxesRunTime.unboxToInt(blockSizes.apply(index));
         });
         return (ManagedBuffer[])scala.collection.ArrayOps..MODULE$.toArray$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.intArrayOps(blockOffsets), blockSizes)), (x0$1) -> {
            if (x0$1 != null) {
               int offset = x0$1._1$mcI$sp();
               int size = x0$1._2$mcI$sp();
               return new NioManagedBuffer(inMemoryBuffer.duplicate().position(offset).limit(offset + size).slice());
            } else {
               throw new MatchError(x0$1);
            }
         }, .MODULE$.apply(NioManagedBuffer.class))), .MODULE$.apply(ManagedBuffer.class));
      }
   }

   public synchronized boolean org$apache$spark$shuffle$ShuffleBlockPusher$$updateStateAndCheckIfPushMore(final long bytesPushed, final BlockManagerId address, final HashSet remainingBlocks, final PushResult pushResult) {
      remainingBlocks.$minus$eq(pushResult.blockId());
      this.bytesInFlight_$eq(this.bytesInFlight() - bytesPushed);
      this.numBlocksInFlightPerAddress.update(address, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.numBlocksInFlightPerAddress.apply(address)) - 1));
      if (remainingBlocks.isEmpty()) {
         --this.reqsInFlight;
      }

      if (pushResult.failure() != null && pushResult.failure().getCause() instanceof ConnectException && !this.unreachableBlockMgrs().contains(address)) {
         IntRef removed = IntRef.create(0);
         this.unreachableBlockMgrs().add(address);
         removed.elem += this.pushRequests.dequeueAll((req) -> BoxesRunTime.boxToBoolean($anonfun$updateStateAndCheckIfPushMore$1(address, req))).length();
         removed.elem += BoxesRunTime.unboxToInt(this.deferredPushRequests.remove(address).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$updateStateAndCheckIfPushMore$2(x$5))).getOrElse((JFunction0.mcI.sp)() -> 0));
         this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Received a ConnectException from ", ". "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, address)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dropping ", " push-requests and "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_REQUESTS..MODULE$, BoxesRunTime.boxToInteger(removed.elem))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"not pushing any more blocks to this address."})))).log(scala.collection.immutable.Nil..MODULE$))));
      }

      if (pushResult.failure() != null && !this.org$apache$spark$shuffle$ShuffleBlockPusher$$errorHandler.shouldRetryError(pushResult.failure())) {
         this.logDebug((Function0)(() -> "Encountered an exception from " + address + " which indicates that push needs to stop."));
         return false;
      } else {
         if (this.reqsInFlight <= 0 && this.pushRequests.isEmpty() && this.deferredPushRequests.isEmpty()) {
            this.notifyDriverAboutPushCompletion();
         }

         return remainingBlocks.isEmpty() && (this.pushRequests.nonEmpty() || this.deferredPushRequests.nonEmpty());
      }
   }

   public void notifyDriverAboutPushCompletion() {
      scala.Predef..MODULE$.assert(this.shuffleId >= 0 && this.mapIndex >= 0);
      if (!this.pushCompletionNotified) {
         label44: {
            boolean var2 = false;
            Some var3 = null;
            Option var4 = SparkEnv$.MODULE$.get().executorBackend();
            if (var4 instanceof Some) {
               var2 = true;
               var3 = (Some)var4;
               ExecutorBackend cb = (ExecutorBackend)var3.value();
               if (cb instanceof CoarseGrainedExecutorBackend) {
                  CoarseGrainedExecutorBackend var6 = (CoarseGrainedExecutorBackend)cb;
                  var6.notifyDriverAboutPushCompletion(this.shuffleId, this.shuffleMergeId, this.mapIndex);
                  BoxedUnit var10 = BoxedUnit.UNIT;
                  break label44;
               }
            }

            if (var2) {
               ExecutorBackend eb = (ExecutorBackend)var3.value();
               if (eb != null) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Currently ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_BACKEND..MODULE$, eb)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"doesn't support push-based shuffle"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  BoxedUnit var9 = BoxedUnit.UNIT;
                  break label44;
               }
            }

            if (!scala.None..MODULE$.equals(var4)) {
               throw new MatchError(var4);
            }

            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         this.pushCompletionNotified = true;
      }
   }

   public Seq prepareBlockPushRequests(final int numPartitions, final int partitionId, final int shuffleId, final int shuffleMergeId, final File dataFile, final long[] partitionLengths, final Seq mergerLocs, final TransportConf transportConf) {
      LongRef offset = LongRef.create(0L);
      IntRef currentReqSize = IntRef.create(0);
      LongRef currentReqOffset = LongRef.create(0L);
      IntRef currentMergerId = IntRef.create(0);
      int numMergers = mergerLocs.length();
      ArrayBuffer requests = new ArrayBuffer();
      ObjectRef blocks = ObjectRef.create(new ArrayBuffer());
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numPartitions).foreach$mVc$sp((JFunction1.mcVI.sp)(reduceId) -> {
         long blockSize = partitionLengths[reduceId];
         this.logDebug((Function0)(() -> {
            ShufflePushBlockId var10000 = new ShufflePushBlockId(shuffleId, shuffleMergeId, partitionId, reduceId);
            return "Block " + var10000 + " is of size " + blockSize;
         }));
         if (blockSize > 0L) {
            int mergerId = (int)scala.math.package..MODULE$.min(scala.math.package..MODULE$.floor((double)reduceId * (double)1.0F / (double)numPartitions * (double)numMergers), (double)(numMergers - 1));
            if ((long)currentReqSize.elem + blockSize <= this.maxBlockBatchSize && ((ArrayBuffer)blocks.elem).size() < this.maxBlocksInFlightPerAddress && mergerId == currentMergerId.elem && blockSize <= this.maxBlockSizeToPush) {
               currentReqSize.elem += (int)blockSize;
            } else {
               if (((ArrayBuffer)blocks.elem).nonEmpty()) {
                  requests.$plus$eq(new PushRequest((BlockManagerId)mergerLocs.apply(currentMergerId.elem), ((ArrayBuffer)blocks.elem).toSeq(), this.createRequestBuffer(transportConf, dataFile, currentReqOffset.elem, (long)currentReqSize.elem)));
                  blocks.elem = new ArrayBuffer();
               }

               currentReqSize.elem = 0;
               currentReqOffset.elem = -1L;
               currentMergerId.elem = mergerId;
            }

            if (blockSize <= this.maxBlockSizeToPush) {
               int blockSizeInt = (int)blockSize;
               ((ArrayBuffer)blocks.elem).$plus$eq(new Tuple2(new ShufflePushBlockId(shuffleId, shuffleMergeId, partitionId, reduceId), BoxesRunTime.boxToInteger(blockSizeInt)));
               if (currentReqOffset.elem == -1L) {
                  currentReqOffset.elem = offset.elem;
               }

               if (currentReqSize.elem == 0) {
                  currentReqSize.elem += blockSizeInt;
               }
            }
         }

         offset.elem += blockSize;
      });
      if (((ArrayBuffer)blocks.elem).nonEmpty()) {
         requests.$plus$eq(new PushRequest((BlockManagerId)mergerLocs.apply(currentMergerId.elem), ((ArrayBuffer)blocks.elem).toSeq(), this.createRequestBuffer(transportConf, dataFile, currentReqOffset.elem, (long)currentReqSize.elem)));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      return requests.toSeq();
   }

   public ManagedBuffer createRequestBuffer(final TransportConf conf, final File dataFile, final long offset, final long length) {
      return new FileSegmentManagedBuffer(conf, dataFile, offset, length);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$pushUpToMax$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$pushUpToMax$2(final ShuffleBlockPusher $this, final Tuple2 x$1) {
      if (x$1 == null) {
         throw new MatchError(x$1);
      } else {
         BlockManagerId remoteAddress = (BlockManagerId)x$1._1();
         Queue defReqQueue = (Queue)x$1._2();

         while($this.isRemoteBlockPushable$1(defReqQueue) && !$this.isRemoteAddressMaxedOut$1(remoteAddress, (PushRequest)defReqQueue.front())) {
            PushRequest request = (PushRequest)defReqQueue.dequeue();
            $this.logDebug((Function0)(() -> "Processing deferred push request for " + remoteAddress + " with " + request.blocks().length() + " blocks"));
            $this.sendRequest(request);
            if (defReqQueue.isEmpty()) {
               $this.deferredPushRequests.$minus$eq(remoteAddress);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         BoxedUnit var7 = BoxedUnit.UNIT;
      }
   }

   private final boolean isRemoteBlockPushable$1(final Queue pushReqQueue) {
      return pushReqQueue.nonEmpty() && (this.bytesInFlight() == 0L || this.reqsInFlight + 1 <= this.maxReqsInFlight && this.bytesInFlight() + (long)((PushRequest)pushReqQueue.front()).size() <= this.maxBytesInFlight);
   }

   private final boolean isRemoteAddressMaxedOut$1(final BlockManagerId remoteAddress, final PushRequest request) {
      return BoxesRunTime.unboxToInt(this.numBlocksInFlightPerAddress.getOrElse(remoteAddress, (JFunction0.mcI.sp)() -> 0)) + request.blocks().size() > this.maxBlocksInFlightPerAddress;
   }

   // $FF: synthetic method
   public static final int $anonfun$sendRequest$4(final Tuple2 x$3) {
      return x$3._2$mcI$sp();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$updateStateAndCheckIfPushMore$1(final BlockManagerId address$2, final PushRequest req) {
      boolean var3;
      label23: {
         BlockManagerId var10000 = req.address();
         if (var10000 == null) {
            if (address$2 == null) {
               break label23;
            }
         } else if (var10000.equals(address$2)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final int $anonfun$updateStateAndCheckIfPushMore$2(final Queue x$5) {
      return x$5.length();
   }

   public ShuffleBlockPusher(final SparkConf conf) {
      this.conf = conf;
      Logging.$init$(this);
      this.maxBlockSizeToPush = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.SHUFFLE_MAX_BLOCK_SIZE_TO_PUSH()));
      this.maxBlockBatchSize = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.SHUFFLE_MAX_BLOCK_BATCH_SIZE_FOR_PUSH()));
      this.maxBytesInFlight = BoxesRunTime.unboxToLong(conf.get(package$.MODULE$.REDUCER_MAX_SIZE_IN_FLIGHT())) * 1024L * 1024L;
      this.maxReqsInFlight = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.REDUCER_MAX_REQS_IN_FLIGHT()));
      this.maxBlocksInFlightPerAddress = BoxesRunTime.unboxToInt(conf.get(package$.MODULE$.REDUCER_MAX_BLOCKS_IN_FLIGHT_PER_ADDRESS()));
      this.bytesInFlight = 0L;
      this.reqsInFlight = 0;
      this.numBlocksInFlightPerAddress = new HashMap();
      this.deferredPushRequests = new HashMap();
      this.pushRequests = new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
      this.org$apache$spark$shuffle$ShuffleBlockPusher$$errorHandler = this.createErrorHandler();
      this.unreachableBlockMgrs = new HashSet();
      this.shuffleId = -1;
      this.mapIndex = -1;
      this.shuffleMergeId = -1;
      this.pushCompletionNotified = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class PushRequest implements Product, Serializable {
      private final BlockManagerId address;
      private final Seq blocks;
      private final ManagedBuffer reqBuffer;
      private final int size;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public BlockManagerId address() {
         return this.address;
      }

      public Seq blocks() {
         return this.blocks;
      }

      public ManagedBuffer reqBuffer() {
         return this.reqBuffer;
      }

      public int size() {
         return this.size;
      }

      public PushRequest copy(final BlockManagerId address, final Seq blocks, final ManagedBuffer reqBuffer) {
         return new PushRequest(address, blocks, reqBuffer);
      }

      public BlockManagerId copy$default$1() {
         return this.address();
      }

      public Seq copy$default$2() {
         return this.blocks();
      }

      public ManagedBuffer copy$default$3() {
         return this.reqBuffer();
      }

      public String productPrefix() {
         return "PushRequest";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.address();
            }
            case 1 -> {
               return this.blocks();
            }
            case 2 -> {
               return this.reqBuffer();
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
         return x$1 instanceof PushRequest;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "address";
            }
            case 1 -> {
               return "blocks";
            }
            case 2 -> {
               return "reqBuffer";
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
            label63: {
               if (x$1 instanceof PushRequest) {
                  label56: {
                     PushRequest var4 = (PushRequest)x$1;
                     BlockManagerId var10000 = this.address();
                     BlockManagerId var5 = var4.address();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     Seq var8 = this.blocks();
                     Seq var6 = var4.blocks();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var8.equals(var6)) {
                        break label56;
                     }

                     ManagedBuffer var9 = this.reqBuffer();
                     ManagedBuffer var7 = var4.reqBuffer();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label56;
                        }
                     } else if (!var9.equals(var7)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
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
      public static final int $anonfun$size$1(final Tuple2 x$6) {
         return x$6._2$mcI$sp();
      }

      public PushRequest(final BlockManagerId address, final Seq blocks, final ManagedBuffer reqBuffer) {
         this.address = address;
         this.blocks = blocks;
         this.reqBuffer = reqBuffer;
         Product.$init$(this);
         this.size = BoxesRunTime.unboxToInt(((IterableOnceOps)blocks.map((x$6) -> BoxesRunTime.boxToInteger($anonfun$size$1(x$6)))).sum(scala.math.Numeric.IntIsIntegral..MODULE$));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class PushRequest$ extends AbstractFunction3 implements Serializable {
      public static final PushRequest$ MODULE$ = new PushRequest$();

      public final String toString() {
         return "PushRequest";
      }

      public PushRequest apply(final BlockManagerId address, final Seq blocks, final ManagedBuffer reqBuffer) {
         return new PushRequest(address, blocks, reqBuffer);
      }

      public Option unapply(final PushRequest x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.address(), x$0.blocks(), x$0.reqBuffer())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(PushRequest$.class);
      }
   }

   private static class PushResult implements Product, Serializable {
      private final String blockId;
      private final Throwable failure;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String blockId() {
         return this.blockId;
      }

      public Throwable failure() {
         return this.failure;
      }

      public PushResult copy(final String blockId, final Throwable failure) {
         return new PushResult(blockId, failure);
      }

      public String copy$default$1() {
         return this.blockId();
      }

      public Throwable copy$default$2() {
         return this.failure();
      }

      public String productPrefix() {
         return "PushResult";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.blockId();
            }
            case 1 -> {
               return this.failure();
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
         return x$1 instanceof PushResult;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "blockId";
            }
            case 1 -> {
               return "failure";
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
         boolean var8;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof PushResult) {
                  label48: {
                     PushResult var4 = (PushResult)x$1;
                     String var10000 = this.blockId();
                     String var5 = var4.blockId();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label48;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label48;
                     }

                     Throwable var7 = this.failure();
                     Throwable var6 = var4.failure();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label48;
                        }
                     } else if (!var7.equals(var6)) {
                        break label48;
                     }

                     if (var4.canEqual(this)) {
                        break label55;
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

      public PushResult(final String blockId, final Throwable failure) {
         this.blockId = blockId;
         this.failure = failure;
         Product.$init$(this);
      }
   }

   private static class PushResult$ extends AbstractFunction2 implements Serializable {
      public static final PushResult$ MODULE$ = new PushResult$();

      public final String toString() {
         return "PushResult";
      }

      public PushResult apply(final String blockId, final Throwable failure) {
         return new PushResult(blockId, failure);
      }

      public Option unapply(final PushResult x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.blockId(), x$0.failure())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(PushResult$.class);
      }

      public PushResult$() {
      }
   }
}
