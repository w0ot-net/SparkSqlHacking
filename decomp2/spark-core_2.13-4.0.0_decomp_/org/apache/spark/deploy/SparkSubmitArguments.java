package org.apache.spark.deploy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.SparkUserAppException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.internal.config.package$;
import org.apache.spark.launcher.SparkSubmitArgumentsParser;
import org.apache.spark.network.util.JavaUtils;
import org.slf4j.Logger;
import scala.Enumeration;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011mb!CA\u0018\u0003c\u0001\u0011\u0011GA!\u0011)\tY\u0006\u0001B\u0001B\u0003%\u0011q\f\u0005\u000b\u0003\u0017\u0003!\u0011!Q\u0001\n\u00055\u0005bBAJ\u0001\u0011\u0005\u0011Q\u0013\u0005\n\u0003?\u0003\u0001\u0019!C\u0001\u0003CC\u0011\"a+\u0001\u0001\u0004%\t!!,\t\u0011\u0005e\u0006\u0001)Q\u0005\u0003GCq!a/\u0001\t\u0003\ti\fC\u0005\u0002@\u0002\u0001\r\u0011\"\u0001\u0002\"\"I\u0011\u0011\u0019\u0001A\u0002\u0013\u0005\u00111\u0019\u0005\t\u0003\u000f\u0004\u0001\u0015)\u0003\u0002$\"I\u0011\u0011\u001a\u0001A\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0003\u0017\u0004\u0001\u0019!C\u0001\u0003\u001bD\u0001\"!5\u0001A\u0003&\u00111\u0010\u0005\n\u0003'\u0004\u0001\u0019!C\u0001\u0003{C\u0011\"!6\u0001\u0001\u0004%\t!a6\t\u0011\u0005m\u0007\u0001)Q\u0005\u0003wB\u0011\"!8\u0001\u0001\u0004%\t!!0\t\u0013\u0005}\u0007\u00011A\u0005\u0002\u0005\u0005\b\u0002CAs\u0001\u0001\u0006K!a\u001f\t\u0013\u0005\u001d\b\u00011A\u0005\u0002\u0005u\u0006\"CAu\u0001\u0001\u0007I\u0011AAv\u0011!\ty\u000f\u0001Q!\n\u0005m\u0004\"CAy\u0001\u0001\u0007I\u0011AA_\u0011%\t\u0019\u0010\u0001a\u0001\n\u0003\t)\u0010\u0003\u0005\u0002z\u0002\u0001\u000b\u0015BA>\u0011%\tY\u0010\u0001a\u0001\n\u0013\ti\u0010C\u0005\u0003\u0006\u0001\u0001\r\u0011\"\u0003\u0003\b!A!1\u0002\u0001!B\u0013\ty\u0010C\u0005\u0003\u000e\u0001\u0001\r\u0011\"\u0001\u0002>\"I!q\u0002\u0001A\u0002\u0013\u0005!\u0011\u0003\u0005\t\u0005+\u0001\u0001\u0015)\u0003\u0002|!I!q\u0003\u0001A\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u00053\u0001\u0001\u0019!C\u0001\u00057A\u0001Ba\b\u0001A\u0003&\u00111\u0010\u0005\n\u0005C\u0001\u0001\u0019!C\u0001\u0003{C\u0011Ba\t\u0001\u0001\u0004%\tA!\n\t\u0011\t%\u0002\u0001)Q\u0005\u0003wB\u0011Ba\u000b\u0001\u0001\u0004%\t!!0\t\u0013\t5\u0002\u00011A\u0005\u0002\t=\u0002\u0002\u0003B\u001a\u0001\u0001\u0006K!a\u001f\t\u0013\tU\u0002\u00011A\u0005\u0002\u0005u\u0006\"\u0003B\u001c\u0001\u0001\u0007I\u0011\u0001B\u001d\u0011!\u0011i\u0004\u0001Q!\n\u0005m\u0004\"\u0003B \u0001\u0001\u0007I\u0011AA_\u0011%\u0011\t\u0005\u0001a\u0001\n\u0003\u0011\u0019\u0005\u0003\u0005\u0003H\u0001\u0001\u000b\u0015BA>\u0011%\u0011I\u0005\u0001a\u0001\n\u0003\ti\fC\u0005\u0003L\u0001\u0001\r\u0011\"\u0001\u0003N!A!\u0011\u000b\u0001!B\u0013\tY\bC\u0005\u0003T\u0001\u0001\r\u0011\"\u0001\u0002>\"I!Q\u000b\u0001A\u0002\u0013\u0005!q\u000b\u0005\t\u00057\u0002\u0001\u0015)\u0003\u0002|!I!Q\f\u0001A\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0005?\u0002\u0001\u0019!C\u0001\u0005CB\u0001B!\u001a\u0001A\u0003&\u00111\u0010\u0005\n\u0005O\u0002\u0001\u0019!C\u0001\u0003{C\u0011B!\u001b\u0001\u0001\u0004%\tAa\u001b\t\u0011\t=\u0004\u0001)Q\u0005\u0003wB\u0011B!\u001d\u0001\u0001\u0004%\t!!0\t\u0013\tM\u0004\u00011A\u0005\u0002\tU\u0004\u0002\u0003B=\u0001\u0001\u0006K!a\u001f\t\u0013\tm\u0004\u00011A\u0005\u0002\tu\u0004\"\u0003BH\u0001\u0001\u0007I\u0011\u0001BI\u0011!\u0011)\n\u0001Q!\n\t}\u0004\"\u0003BL\u0001\u0001\u0007I\u0011AA_\u0011%\u0011I\n\u0001a\u0001\n\u0003\u0011Y\n\u0003\u0005\u0003 \u0002\u0001\u000b\u0015BA>\u0011%\u0011\t\u000b\u0001a\u0001\n\u0003\ti\fC\u0005\u0003$\u0002\u0001\r\u0011\"\u0001\u0003&\"A!\u0011\u0016\u0001!B\u0013\tY\bC\u0005\u0003,\u0002\u0001\r\u0011\"\u0001\u0002>\"I!Q\u0016\u0001A\u0002\u0013\u0005!q\u0016\u0005\t\u0005g\u0003\u0001\u0015)\u0003\u0002|!I!Q\u0017\u0001A\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0005o\u0003\u0001\u0019!C\u0001\u0005sC\u0001B!0\u0001A\u0003&\u00111\u0010\u0005\n\u0005\u007f\u0003\u0001\u0019!C\u0001\u0003CC\u0011B!1\u0001\u0001\u0004%\tAa1\t\u0011\t\u001d\u0007\u0001)Q\u0005\u0003GC\u0011B!3\u0001\u0001\u0004%\t!!0\t\u0013\t-\u0007\u00011A\u0005\u0002\t5\u0007\u0002\u0003Bi\u0001\u0001\u0006K!a\u001f\t\u0013\tM\u0007\u00011A\u0005\u0002\u0005u\b\"\u0003Bk\u0001\u0001\u0007I\u0011\u0001Bl\u0011!\u0011Y\u000e\u0001Q!\n\u0005}\b\"\u0003Bo\u0001\u0001\u0007I\u0011AA\u007f\u0011%\u0011y\u000e\u0001a\u0001\n\u0003\u0011\t\u000f\u0003\u0005\u0003f\u0002\u0001\u000b\u0015BA\u0000\u0011%\u00119\u000f\u0001a\u0001\n\u0003\ti\fC\u0005\u0003j\u0002\u0001\r\u0011\"\u0001\u0003l\"A!q\u001e\u0001!B\u0013\tY\bC\u0005\u0003r\u0002\u0001\r\u0011\"\u0001\u0002~\"I!1\u001f\u0001A\u0002\u0013\u0005!Q\u001f\u0005\t\u0005s\u0004\u0001\u0015)\u0003\u0002\u0000\"I!1 \u0001A\u0002\u0013\u0005!Q \u0005\n\u0007;\u0001\u0001\u0019!C\u0001\u0007?A\u0001ba\t\u0001A\u0003&!q \u0005\n\u0007K\u0001!\u0019!C\u0001\u0007OA\u0001ba\f\u0001A\u0003%1\u0011\u0006\u0005\n\u0007c\u0001\u0001\u0019!C\u0001\u0003{C\u0011ba\r\u0001\u0001\u0004%\ta!\u000e\t\u0011\re\u0002\u0001)Q\u0005\u0003wB\u0011ba\u000f\u0001\u0001\u0004%\t!!0\t\u0013\ru\u0002\u00011A\u0005\u0002\r}\u0002\u0002CB\"\u0001\u0001\u0006K!a\u001f\t\u0013\r\u0015\u0003\u00011A\u0005\u0002\u0005u\u0006\"CB$\u0001\u0001\u0007I\u0011AB%\u0011!\u0019i\u0005\u0001Q!\n\u0005m\u0004\"CB(\u0001\u0001\u0007I\u0011BA\u007f\u0011%\u0019\t\u0006\u0001a\u0001\n\u0013\u0019\u0019\u0006\u0003\u0005\u0004X\u0001\u0001\u000b\u0015BA\u0000\u0011%\u0019I\u0006\u0001a\u0001\n\u0003\ti\u0010C\u0005\u0004\\\u0001\u0001\r\u0011\"\u0001\u0004^!A1\u0011\r\u0001!B\u0013\ty\u0010C\u0005\u0004d\u0001\u0001\r\u0011\"\u0001\u0002>\"I1Q\r\u0001A\u0002\u0013\u00051q\r\u0005\t\u0007W\u0002\u0001\u0015)\u0003\u0002|!I1Q\u000e\u0001A\u0002\u0013\u0005\u0011Q\u0018\u0005\n\u0007_\u0002\u0001\u0019!C\u0001\u0007cB\u0001b!\u001e\u0001A\u0003&\u00111\u0010\u0005\n\u0007o\u0002\u0001\u0019!C\u0001\u0003{C\u0011b!\u001f\u0001\u0001\u0004%\taa\u001f\t\u0011\r}\u0004\u0001)Q\u0005\u0003wB\u0011b!!\u0001\u0001\u0004%\t!!@\t\u0013\r\r\u0005\u00011A\u0005\u0002\r\u0015\u0005\u0002CBE\u0001\u0001\u0006K!a@\t\u000f\r-\u0005\u0001\"\u0015\u0002>\"91Q\u0012\u0001\u0005\n\r=\u0005bBBK\u0001\u0011%1q\u0013\u0005\b\u00073\u0003A\u0011BBL\u0011\u001d\u0019Y\n\u0001C\u0005\u0007/Cqa!(\u0001\t\u0013\u00199\nC\u0004\u0004 \u0002!Iaa&\t\u000f\r\u0005\u0006\u0001\"\u0003\u0004\u0018\"911\u0015\u0001\u0005\n\r]\u0005bBBS\u0001\u0011\u0005\u0011Q \u0005\b\u0007O\u0003A\u0011IBU\u0011\u001d\u0019Y\u000b\u0001C)\u0007[Cqaa.\u0001\t#\u001aI\fC\u0004\u0004>\u0002!\tfa0\t\u000f\rU\u0007\u0001\"\u0003\u0004X\"I1Q\u001e\u0001\u0012\u0002\u0013%1q\u001e\u0005\b\t\u000b\u0001A\u0011BBU\u0011\u001d!9\u0001\u0001C\u0005\t\u0013A\u0011\u0002b\u0004\u0001\t\u0003\t\t\u0004\"\u0005\t\u0017\u0011\u0005\u0002!%A\u0005\u0002\u0005EB1E\u0004\r\tO\t\t$!A\t\u0002\u0005EB\u0011\u0006\u0004\r\u0003_\t\t$!A\t\u0002\u0005EB1\u0006\u0005\t\u0003'\u000bI\u0003\"\u0001\u00054!QAQGA\u0015#\u0003%\t\u0001b\u000e\u0003)M\u0003\u0018M]6Tk\nl\u0017\u000e^!sOVlWM\u001c;t\u0015\u0011\t\u0019$!\u000e\u0002\r\u0011,\u0007\u000f\\8z\u0015\u0011\t9$!\u000f\u0002\u000bM\u0004\u0018M]6\u000b\t\u0005m\u0012QH\u0001\u0007CB\f7\r[3\u000b\u0005\u0005}\u0012aA8sON)\u0001!a\u0011\u0002PA!\u0011QIA&\u001b\t\t9E\u0003\u0003\u0002J\u0005U\u0012\u0001\u00037bk:\u001c\u0007.\u001a:\n\t\u00055\u0013q\t\u0002\u001b'B\f'o[*vE6LG/\u0011:hk6,g\u000e^:QCJ\u001cXM\u001d\t\u0005\u0003#\n9&\u0004\u0002\u0002T)!\u0011QKA\u001b\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA-\u0003'\u0012q\u0001T8hO&tw-\u0001\u0003be\u001e\u001c8\u0001\u0001\t\u0007\u0003C\n)(a\u001f\u000f\t\u0005\r\u0014q\u000e\b\u0005\u0003K\nY'\u0004\u0002\u0002h)!\u0011\u0011NA/\u0003\u0019a$o\\8u}%\u0011\u0011QN\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0003c\n\u0019(A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u00055\u0014\u0002BA<\u0003s\u00121aU3r\u0015\u0011\t\t(a\u001d\u0011\t\u0005u\u0014Q\u0011\b\u0005\u0003\u007f\n\t\t\u0005\u0003\u0002f\u0005M\u0014\u0002BAB\u0003g\na\u0001\u0015:fI\u00164\u0017\u0002BAD\u0003\u0013\u0013aa\u0015;sS:<'\u0002BAB\u0003g\n1!\u001a8w!!\ti(a$\u0002|\u0005m\u0014\u0002BAI\u0003\u0013\u00131!T1q\u0003\u0019a\u0014N\\5u}Q1\u0011qSAN\u0003;\u00032!!'\u0001\u001b\t\t\t\u0004C\u0004\u0002\\\r\u0001\r!a\u0018\t\u0013\u0005-5\u0001%AA\u0002\u00055\u0015aC7bs\n,W*Y:uKJ,\"!a)\u0011\r\u0005\u0015\u0016qUA>\u001b\t\t\u0019(\u0003\u0003\u0002*\u0006M$AB(qi&|g.A\bnCf\u0014W-T1ti\u0016\u0014x\fJ3r)\u0011\ty+!.\u0011\t\u0005\u0015\u0016\u0011W\u0005\u0005\u0003g\u000b\u0019H\u0001\u0003V]&$\b\"CA\\\u000b\u0005\u0005\t\u0019AAR\u0003\rAH%M\u0001\r[\u0006L(-Z'bgR,'\u000fI\u0001\u0007[\u0006\u001cH/\u001a:\u0016\u0005\u0005m\u0014aC7bs\n,'+Z7pi\u0016\fq\"\\1zE\u0016\u0014V-\\8uK~#S-\u001d\u000b\u0005\u0003_\u000b)\rC\u0005\u00028&\t\t\u00111\u0001\u0002$\u0006aQ.Y=cKJ+Wn\u001c;fA\u0005QA-\u001a9m_flu\u000eZ3\u0002\u001d\u0011,\u0007\u000f\\8z\u001b>$Wm\u0018\u0013fcR!\u0011qVAh\u0011%\t9\fDA\u0001\u0002\u0004\tY(A\u0006eKBdw._'pI\u0016\u0004\u0013AD3yK\u000e,Ho\u001c:NK6|'/_\u0001\u0013Kb,7-\u001e;pe6+Wn\u001c:z?\u0012*\u0017\u000f\u0006\u0003\u00020\u0006e\u0007\"CA\\\u001f\u0005\u0005\t\u0019AA>\u0003=)\u00070Z2vi>\u0014X*Z7pef\u0004\u0013!D3yK\u000e,Ho\u001c:D_J,7/A\tfq\u0016\u001cW\u000f^8s\u0007>\u0014Xm]0%KF$B!a,\u0002d\"I\u0011q\u0017\n\u0002\u0002\u0003\u0007\u00111P\u0001\u000fKb,7-\u001e;pe\u000e{'/Z:!\u0003I!x\u000e^1m\u000bb,7-\u001e;pe\u000e{'/Z:\u0002-Q|G/\u00197Fq\u0016\u001cW\u000f^8s\u0007>\u0014Xm]0%KF$B!a,\u0002n\"I\u0011qW\u000b\u0002\u0002\u0003\u0007\u00111P\u0001\u0014i>$\u0018\r\\#yK\u000e,Ho\u001c:D_J,7\u000fI\u0001\u000faJ|\u0007/\u001a:uS\u0016\u001ch)\u001b7f\u0003I\u0001(o\u001c9feRLWm\u001d$jY\u0016|F%Z9\u0015\t\u0005=\u0016q\u001f\u0005\n\u0003oC\u0012\u0011!a\u0001\u0003w\nq\u0002\u001d:pa\u0016\u0014H/[3t\r&dW\rI\u0001\u0012Y>\fGm\u00159be.$UMZ1vYR\u001cXCAA\u0000!\u0011\t)K!\u0001\n\t\t\r\u00111\u000f\u0002\b\u0005>|G.Z1o\u0003Uaw.\u00193Ta\u0006\u00148\u000eR3gCVdGo]0%KF$B!a,\u0003\n!I\u0011qW\u000e\u0002\u0002\u0003\u0007\u0011q`\u0001\u0013Y>\fGm\u00159be.$UMZ1vYR\u001c\b%\u0001\u0007ee&4XM]'f[>\u0014\u00180\u0001\tee&4XM]'f[>\u0014\u0018p\u0018\u0013fcR!\u0011q\u0016B\n\u0011%\t9LHA\u0001\u0002\u0004\tY(A\u0007ee&4XM]'f[>\u0014\u0018\u0010I\u0001\u0015IJLg/\u001a:FqR\u0014\u0018m\u00117bgN\u0004\u0016\r\u001e5\u00021\u0011\u0014\u0018N^3s\u000bb$(/Y\"mCN\u001c\b+\u0019;i?\u0012*\u0017\u000f\u0006\u0003\u00020\nu\u0001\"CA\\C\u0005\u0005\t\u0019AA>\u0003U!'/\u001b<fe\u0016CHO]1DY\u0006\u001c8\u000fU1uQ\u0002\na\u0003\u001a:jm\u0016\u0014X\t\u001f;sC2K'M]1ssB\u000bG\u000f[\u0001\u001bIJLg/\u001a:FqR\u0014\u0018\rT5ce\u0006\u0014\u0018\u0010U1uQ~#S-\u001d\u000b\u0005\u0003_\u00139\u0003C\u0005\u00028\u0012\n\t\u00111\u0001\u0002|\u00059BM]5wKJ,\u0005\u0010\u001e:b\u0019&\u0014'/\u0019:z!\u0006$\b\u000eI\u0001\u0017IJLg/\u001a:FqR\u0014\u0018MS1wC>\u0003H/[8og\u0006QBM]5wKJ,\u0005\u0010\u001e:b\u0015\u00064\u0018m\u00149uS>t7o\u0018\u0013fcR!\u0011q\u0016B\u0019\u0011%\t9lJA\u0001\u0002\u0004\tY(A\fee&4XM]#yiJ\f'*\u0019<b\u001fB$\u0018n\u001c8tA\u0005)\u0011/^3vK\u0006I\u0011/^3vK~#S-\u001d\u000b\u0005\u0003_\u0013Y\u0004C\u0005\u00028*\n\t\u00111\u0001\u0002|\u00051\u0011/^3vK\u0002\nAB\\;n\u000bb,7-\u001e;peN\f\u0001C\\;n\u000bb,7-\u001e;peN|F%Z9\u0015\t\u0005=&Q\t\u0005\n\u0003ok\u0013\u0011!a\u0001\u0003w\nQB\\;n\u000bb,7-\u001e;peN\u0004\u0013!\u00024jY\u0016\u001c\u0018!\u00034jY\u0016\u001cx\fJ3r)\u0011\tyKa\u0014\t\u0013\u0005]\u0006'!AA\u0002\u0005m\u0014A\u00024jY\u0016\u001c\b%\u0001\u0005be\u000eD\u0017N^3t\u00031\t'o\u00195jm\u0016\u001cx\fJ3r)\u0011\tyK!\u0017\t\u0013\u0005]6'!AA\u0002\u0005m\u0014!C1sG\"Lg/Z:!\u0003%i\u0017-\u001b8DY\u0006\u001c8/A\u0007nC&t7\t\\1tg~#S-\u001d\u000b\u0005\u0003_\u0013\u0019\u0007C\u0005\u00028Z\n\t\u00111\u0001\u0002|\u0005QQ.Y5o\u00072\f7o\u001d\u0011\u0002\u001fA\u0014\u0018.\\1ssJ+7o\\;sG\u0016\f1\u0003\u001d:j[\u0006\u0014\u0018PU3t_V\u00148-Z0%KF$B!a,\u0003n!I\u0011qW\u001d\u0002\u0002\u0003\u0007\u00111P\u0001\u0011aJLW.\u0019:z%\u0016\u001cx.\u001e:dK\u0002\nAA\\1nK\u0006Aa.Y7f?\u0012*\u0017\u000f\u0006\u0003\u00020\n]\u0004\"CA\\y\u0005\u0005\t\u0019AA>\u0003\u0015q\u0017-\\3!\u0003%\u0019\u0007.\u001b7e\u0003J<7/\u0006\u0002\u0003\u0000A1!\u0011\u0011BF\u0003wj!Aa!\u000b\t\t\u0015%qQ\u0001\b[V$\u0018M\u00197f\u0015\u0011\u0011I)a\u001d\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003\u000e\n\r%aC!se\u0006L()\u001e4gKJ\fQb\u00195jY\u0012\f%oZ:`I\u0015\fH\u0003BAX\u0005'C\u0011\"a.@\u0003\u0003\u0005\rAa \u0002\u0015\rD\u0017\u000e\u001c3Be\u001e\u001c\b%\u0001\u0003kCJ\u001c\u0018\u0001\u00036beN|F%Z9\u0015\t\u0005=&Q\u0014\u0005\n\u0003o\u0013\u0015\u0011!a\u0001\u0003w\nQA[1sg\u0002\n\u0001\u0002]1dW\u0006<Wm]\u0001\ra\u0006\u001c7.Y4fg~#S-\u001d\u000b\u0005\u0003_\u00139\u000bC\u0005\u00028\u0016\u000b\t\u00111\u0001\u0002|\u0005I\u0001/Y2lC\u001e,7\u000fI\u0001\re\u0016\u0004xn]5u_JLWm]\u0001\u0011e\u0016\u0004xn]5u_JLWm]0%KF$B!a,\u00032\"I\u0011q\u0017%\u0002\u0002\u0003\u0007\u00111P\u0001\u000ee\u0016\u0004xn]5u_JLWm\u001d\u0011\u0002\u0017%4\u0018PU3q_B\u000bG\u000f[\u0001\u0010SZL(+\u001a9p!\u0006$\bn\u0018\u0013fcR!\u0011q\u0016B^\u0011%\t9lSA\u0001\u0002\u0004\tY(\u0001\u0007jmf\u0014V\r]8QCRD\u0007%A\bjmf\u001cV\r\u001e;j]\u001e\u001c\b+\u0019;i\u0003MIg/_*fiRLgnZ:QCRDw\fJ3r)\u0011\tyK!2\t\u0013\u0005]f*!AA\u0002\u0005\r\u0016\u0001E5wsN+G\u000f^5oON\u0004\u0016\r\u001e5!\u0003I\u0001\u0018mY6bO\u0016\u001cX\t_2mkNLwN\\:\u0002-A\f7m[1hKN,\u0005p\u00197vg&|gn]0%KF$B!a,\u0003P\"I\u0011qW)\u0002\u0002\u0003\u0007\u00111P\u0001\u0014a\u0006\u001c7.Y4fg\u0016C8\r\\;tS>t7\u000fI\u0001\bm\u0016\u0014(m\\:f\u0003-1XM\u001d2pg\u0016|F%Z9\u0015\t\u0005=&\u0011\u001c\u0005\n\u0003o#\u0016\u0011!a\u0001\u0003\u007f\f\u0001B^3sE>\u001cX\rI\u0001\tSN\u0004\u0016\u0010\u001e5p]\u0006a\u0011n\u001d)zi\"|gn\u0018\u0013fcR!\u0011q\u0016Br\u0011%\t9lVA\u0001\u0002\u0004\ty0A\u0005jgBKH\u000f[8oA\u00059\u0001/\u001f$jY\u0016\u001c\u0018a\u00039z\r&dWm]0%KF$B!a,\u0003n\"I\u0011q\u0017.\u0002\u0002\u0003\u0007\u00111P\u0001\taf4\u0015\u000e\\3tA\u0005\u0019\u0011n\u001d*\u0002\u000f%\u001c(k\u0018\u0013fcR!\u0011q\u0016B|\u0011%\t9,XA\u0001\u0002\u0004\ty0\u0001\u0003jgJ\u0003\u0013AB1di&|g.\u0006\u0002\u0003\u0000B!1\u0011AB\f\u001d\u0011\u0019\u0019aa\u0005\u000f\t\r\u00151\u0011\u0003\b\u0005\u0007\u000f\u0019yA\u0004\u0003\u0004\n\r5a\u0002BA3\u0007\u0017I!!a\u0010\n\t\u0005m\u0012QH\u0005\u0005\u0003o\tI$\u0003\u0003\u00024\u0005U\u0012\u0002BB\u000b\u0003c\t\u0011c\u00159be.\u001cVOY7ji\u0006\u001bG/[8o\u0013\u0011\u0019Iba\u0007\u0003#M\u0003\u0018M]6Tk\nl\u0017\u000e^!di&|gN\u0003\u0003\u0004\u0016\u0005E\u0012AC1di&|gn\u0018\u0013fcR!\u0011qVB\u0011\u0011%\t9\fYA\u0001\u0002\u0004\u0011y0A\u0004bGRLwN\u001c\u0011\u0002\u001fM\u0004\u0018M]6Qe>\u0004XM\u001d;jKN,\"a!\u000b\u0011\u0011\t\u000551FA>\u0003wJAa!\f\u0003\u0004\n9\u0001*Y:i\u001b\u0006\u0004\u0018\u0001E:qCJ\\\u0007K]8qKJ$\u0018.Z:!\u0003%\u0001(o\u001c=z+N,'/A\u0007qe>D\u00180V:fe~#S-\u001d\u000b\u0005\u0003_\u001b9\u0004C\u0005\u00028\u0016\f\t\u00111\u0001\u0002|\u0005Q\u0001O]8ysV\u001bXM\u001d\u0011\u0002\u0013A\u0014\u0018N\\2ja\u0006d\u0017!\u00049sS:\u001c\u0017\u000e]1m?\u0012*\u0017\u000f\u0006\u0003\u00020\u000e\u0005\u0003\"CA\\Q\u0006\u0005\t\u0019AA>\u0003)\u0001(/\u001b8dSB\fG\u000eI\u0001\u0007W\u0016LH/\u00192\u0002\u0015-,\u0017\u0010^1c?\u0012*\u0017\u000f\u0006\u0003\u00020\u000e-\u0003\"CA\\W\u0006\u0005\t\u0019AA>\u0003\u001dYW-\u001f;bE\u0002\n\u0001\u0004Z=oC6L7-\u00117m_\u000e\fG/[8o\u000b:\f'\r\\3e\u0003q!\u0017P\\1nS\u000e\fE\u000e\\8dCRLwN\\#oC\ndW\rZ0%KF$B!a,\u0004V!I\u0011q\u00178\u0002\u0002\u0003\u0007\u0011q`\u0001\u001aIft\u0017-\\5d\u00032dwnY1uS>tWI\\1cY\u0016$\u0007%A\u0005tkB,'O^5tK\u0006i1/\u001e9feZL7/Z0%KF$B!a,\u0004`!I\u0011qW9\u0002\u0002\u0003\u0007\u0011q`\u0001\u000bgV\u0004XM\u001d<jg\u0016\u0004\u0013a\u00033sSZ,'oQ8sKN\fq\u0002\u001a:jm\u0016\u00148i\u001c:fg~#S-\u001d\u000b\u0005\u0003_\u001bI\u0007C\u0005\u00028R\f\t\u00111\u0001\u0002|\u0005aAM]5wKJ\u001cuN]3tA\u0005\u00012/\u001e2nSN\u001c\u0018n\u001c8U_.KG\u000e\\\u0001\u0015gV\u0014W.[:tS>tGk\\&jY2|F%Z9\u0015\t\u0005=61\u000f\u0005\n\u0003o;\u0018\u0011!a\u0001\u0003w\n\u0011c];c[&\u001c8/[8o)>\\\u0015\u000e\u001c7!\u0003q\u0019XOY7jgNLwN\u001c+p%\u0016\fX/Z:u'R\fG/^:G_J\f\u0001e];c[&\u001c8/[8o)>\u0014V-];fgR\u001cF/\u0019;vg\u001a{'o\u0018\u0013fcR!\u0011qVB?\u0011%\t9L_A\u0001\u0002\u0004\tY(A\u000ftk\nl\u0017n]:j_:$vNU3rk\u0016\u001cHo\u0015;biV\u001chi\u001c:!\u0003\u001d)8/\u001a*fgR\f1\"^:f%\u0016\u001cHo\u0018\u0013fcR!\u0011qVBD\u0011%\t9,`A\u0001\u0002\u0004\ty0\u0001\u0005vg\u0016\u0014Vm\u001d;!\u0003\u001dawn\u001a(b[\u0016\fa\u0003\\8bIB\u0013x\u000e]3si&,7O\u0012:p[\u001aKG.\u001a\u000b\u0005\u0003_\u001b\t\n\u0003\u0005\u0004\u0014\u0006\u0005\u0001\u0019AA>\u0003!1\u0017\u000e\\3QCRD\u0017aG7fe\u001e,G)\u001a4bk2$8\u000b]1sWB\u0013x\u000e]3si&,7\u000f\u0006\u0002\u00020\u0006A\u0012n\u001a8pe\u0016tuN\\*qCJ\\\u0007K]8qKJ$\u0018.Z:\u000211|\u0017\rZ#om&\u0014xN\\7f]R\f%oZ;nK:$8/A\twC2LG-\u0019;f\u0003J<W/\\3oiN\fqC^1mS\u0012\fG/Z*vE6LG/\u0011:hk6,g\u000e^:\u0002+Y\fG.\u001b3bi\u0016\\\u0015\u000e\u001c7Be\u001e,X.\u001a8ug\u0006qb/\u00197jI\u0006$Xm\u0015;biV\u001c(+Z9vKN$\u0018I]4v[\u0016tGo]\u0001\u0014SN\u001cF/\u00198eC2|g.Z\"mkN$XM]\u0001\ti>\u001cFO]5oOR\u0011\u00111P\u0001\u0007Q\u0006tG\r\\3\u0015\r\u0005}8qVBZ\u0011!\u0019\t,!\u0006A\u0002\u0005m\u0014aA8qi\"A1QWA\u000b\u0001\u0004\tY(A\u0003wC2,X-A\u0007iC:$G.Z+oW:|wO\u001c\u000b\u0005\u0003\u007f\u001cY\f\u0003\u0005\u00042\u0006]\u0001\u0019AA>\u0003=A\u0017M\u001c3mK\u0016CHO]1Be\u001e\u001cH\u0003BAX\u0007\u0003D\u0001ba1\u0002\u001a\u0001\u00071QY\u0001\u0006Kb$(/\u0019\t\u0007\u0007\u000f\u001c\t.a\u001f\u000e\u0005\r%'\u0002BBf\u0007\u001b\fA!\u001e;jY*\u00111qZ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0004T\u000e%'\u0001\u0002'jgR\f\u0011\u0003\u001d:j]R,6/Y4f\u0003:$W\t_5u)\u0019\tyk!7\u0004d\"A11\\A\u000e\u0001\u0004\u0019i.\u0001\u0005fq&$8i\u001c3f!\u0011\t)ka8\n\t\r\u0005\u00181\u000f\u0002\u0004\u0013:$\bBCBs\u00037\u0001\n\u00111\u0001\u0004h\u0006aQO\\6o_^t\u0007+\u0019:b[B!\u0011QUBu\u0013\u0011\u0019Y/a\u001d\u0003\u0007\u0005s\u00170A\u000eqe&tG/V:bO\u0016\fe\u000eZ#ySR$C-\u001a4bk2$HEM\u000b\u0003\u0007cTCaa:\u0004t.\u00121Q\u001f\t\u0005\u0007o$\t!\u0004\u0002\u0004z*!11`B\u007f\u0003%)hn\u00195fG.,GM\u0003\u0003\u0004\u0000\u0006M\u0014AC1o]>$\u0018\r^5p]&!A1AB}\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0013O\u0016$8+\u001d7TQ\u0016dGn\u00149uS>t7/A\u0003feJ|'\u000f\u0006\u0003\u00020\u0012-\u0001\u0002\u0003C\u0007\u0003C\u0001\r!a\u001f\u0002\u00075\u001cx-A\u0006u_N\u0003\u0018M]6D_:4G\u0003\u0002C\n\t7\u0001B\u0001\"\u0006\u0005\u00185\u0011\u0011QG\u0005\u0005\t3\t)DA\u0005Ta\u0006\u00148nQ8oM\"QAQDA\u0012!\u0003\u0005\r\u0001b\b\u0002\u0013M\u0004\u0018M]6D_:4\u0007CBAS\u0003O#\u0019\"A\u000bu_N\u0003\u0018M]6D_:4G\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0011\u0015\"\u0006\u0002C\u0010\u0007g\fAc\u00159be.\u001cVOY7ji\u0006\u0013x-^7f]R\u001c\b\u0003BAM\u0003S\u0019B!!\u000b\u0005.A!\u0011Q\u0015C\u0018\u0013\u0011!\t$a\u001d\u0003\r\u0005s\u0017PU3g)\t!I#A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0003\tsQC!!$\u0004t\u0002"
)
public class SparkSubmitArguments extends SparkSubmitArgumentsParser implements Logging {
   private final Seq args;
   private final Map env;
   private Option maybeMaster;
   private Option maybeRemote;
   private String deployMode;
   private String executorMemory;
   private String executorCores;
   private String totalExecutorCores;
   private String propertiesFile;
   private boolean loadSparkDefaults;
   private String driverMemory;
   private String driverExtraClassPath;
   private String driverExtraLibraryPath;
   private String driverExtraJavaOptions;
   private String queue;
   private String numExecutors;
   private String files;
   private String archives;
   private String mainClass;
   private String primaryResource;
   private String name;
   private ArrayBuffer childArgs;
   private String jars;
   private String packages;
   private String repositories;
   private String ivyRepoPath;
   private Option ivySettingsPath;
   private String packagesExclusions;
   private boolean verbose;
   private boolean isPython;
   private String pyFiles;
   private boolean isR;
   private Enumeration.Value action;
   private final HashMap sparkProperties;
   private String proxyUser;
   private String principal;
   private String keytab;
   private boolean dynamicAllocationEnabled;
   private boolean supervise;
   private String driverCores;
   private String submissionToKill;
   private String submissionToRequestStatusFor;
   private boolean useRest;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Map $lessinit$greater$default$2() {
      return SparkSubmitArguments$.MODULE$.$lessinit$greater$default$2();
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Option maybeMaster() {
      return this.maybeMaster;
   }

   public void maybeMaster_$eq(final Option x$1) {
      this.maybeMaster = x$1;
   }

   public String master() {
      return (String)this.maybeMaster().getOrElse(() -> System.getProperty("spark.test.master", "local[*]"));
   }

   public Option maybeRemote() {
      return this.maybeRemote;
   }

   public void maybeRemote_$eq(final Option x$1) {
      this.maybeRemote = x$1;
   }

   public String deployMode() {
      return this.deployMode;
   }

   public void deployMode_$eq(final String x$1) {
      this.deployMode = x$1;
   }

   public String executorMemory() {
      return this.executorMemory;
   }

   public void executorMemory_$eq(final String x$1) {
      this.executorMemory = x$1;
   }

   public String executorCores() {
      return this.executorCores;
   }

   public void executorCores_$eq(final String x$1) {
      this.executorCores = x$1;
   }

   public String totalExecutorCores() {
      return this.totalExecutorCores;
   }

   public void totalExecutorCores_$eq(final String x$1) {
      this.totalExecutorCores = x$1;
   }

   public String propertiesFile() {
      return this.propertiesFile;
   }

   public void propertiesFile_$eq(final String x$1) {
      this.propertiesFile = x$1;
   }

   private boolean loadSparkDefaults() {
      return this.loadSparkDefaults;
   }

   private void loadSparkDefaults_$eq(final boolean x$1) {
      this.loadSparkDefaults = x$1;
   }

   public String driverMemory() {
      return this.driverMemory;
   }

   public void driverMemory_$eq(final String x$1) {
      this.driverMemory = x$1;
   }

   public String driverExtraClassPath() {
      return this.driverExtraClassPath;
   }

   public void driverExtraClassPath_$eq(final String x$1) {
      this.driverExtraClassPath = x$1;
   }

   public String driverExtraLibraryPath() {
      return this.driverExtraLibraryPath;
   }

   public void driverExtraLibraryPath_$eq(final String x$1) {
      this.driverExtraLibraryPath = x$1;
   }

   public String driverExtraJavaOptions() {
      return this.driverExtraJavaOptions;
   }

   public void driverExtraJavaOptions_$eq(final String x$1) {
      this.driverExtraJavaOptions = x$1;
   }

   public String queue() {
      return this.queue;
   }

   public void queue_$eq(final String x$1) {
      this.queue = x$1;
   }

   public String numExecutors() {
      return this.numExecutors;
   }

   public void numExecutors_$eq(final String x$1) {
      this.numExecutors = x$1;
   }

   public String files() {
      return this.files;
   }

   public void files_$eq(final String x$1) {
      this.files = x$1;
   }

   public String archives() {
      return this.archives;
   }

   public void archives_$eq(final String x$1) {
      this.archives = x$1;
   }

   public String mainClass() {
      return this.mainClass;
   }

   public void mainClass_$eq(final String x$1) {
      this.mainClass = x$1;
   }

   public String primaryResource() {
      return this.primaryResource;
   }

   public void primaryResource_$eq(final String x$1) {
      this.primaryResource = x$1;
   }

   public String name() {
      return this.name;
   }

   public void name_$eq(final String x$1) {
      this.name = x$1;
   }

   public ArrayBuffer childArgs() {
      return this.childArgs;
   }

   public void childArgs_$eq(final ArrayBuffer x$1) {
      this.childArgs = x$1;
   }

   public String jars() {
      return this.jars;
   }

   public void jars_$eq(final String x$1) {
      this.jars = x$1;
   }

   public String packages() {
      return this.packages;
   }

   public void packages_$eq(final String x$1) {
      this.packages = x$1;
   }

   public String repositories() {
      return this.repositories;
   }

   public void repositories_$eq(final String x$1) {
      this.repositories = x$1;
   }

   public String ivyRepoPath() {
      return this.ivyRepoPath;
   }

   public void ivyRepoPath_$eq(final String x$1) {
      this.ivyRepoPath = x$1;
   }

   public Option ivySettingsPath() {
      return this.ivySettingsPath;
   }

   public void ivySettingsPath_$eq(final Option x$1) {
      this.ivySettingsPath = x$1;
   }

   public String packagesExclusions() {
      return this.packagesExclusions;
   }

   public void packagesExclusions_$eq(final String x$1) {
      this.packagesExclusions = x$1;
   }

   public boolean verbose() {
      return this.verbose;
   }

   public void verbose_$eq(final boolean x$1) {
      this.verbose = x$1;
   }

   public boolean isPython() {
      return this.isPython;
   }

   public void isPython_$eq(final boolean x$1) {
      this.isPython = x$1;
   }

   public String pyFiles() {
      return this.pyFiles;
   }

   public void pyFiles_$eq(final String x$1) {
      this.pyFiles = x$1;
   }

   public boolean isR() {
      return this.isR;
   }

   public void isR_$eq(final boolean x$1) {
      this.isR = x$1;
   }

   public Enumeration.Value action() {
      return this.action;
   }

   public void action_$eq(final Enumeration.Value x$1) {
      this.action = x$1;
   }

   public HashMap sparkProperties() {
      return this.sparkProperties;
   }

   public String proxyUser() {
      return this.proxyUser;
   }

   public void proxyUser_$eq(final String x$1) {
      this.proxyUser = x$1;
   }

   public String principal() {
      return this.principal;
   }

   public void principal_$eq(final String x$1) {
      this.principal = x$1;
   }

   public String keytab() {
      return this.keytab;
   }

   public void keytab_$eq(final String x$1) {
      this.keytab = x$1;
   }

   private boolean dynamicAllocationEnabled() {
      return this.dynamicAllocationEnabled;
   }

   private void dynamicAllocationEnabled_$eq(final boolean x$1) {
      this.dynamicAllocationEnabled = x$1;
   }

   public boolean supervise() {
      return this.supervise;
   }

   public void supervise_$eq(final boolean x$1) {
      this.supervise = x$1;
   }

   public String driverCores() {
      return this.driverCores;
   }

   public void driverCores_$eq(final String x$1) {
      this.driverCores = x$1;
   }

   public String submissionToKill() {
      return this.submissionToKill;
   }

   public void submissionToKill_$eq(final String x$1) {
      this.submissionToKill = x$1;
   }

   public String submissionToRequestStatusFor() {
      return this.submissionToRequestStatusFor;
   }

   public void submissionToRequestStatusFor_$eq(final String x$1) {
      this.submissionToRequestStatusFor = x$1;
   }

   public boolean useRest() {
      return this.useRest;
   }

   public void useRest_$eq(final boolean x$1) {
      this.useRest = x$1;
   }

   public String logName() {
      return SparkSubmitArguments.class.getName();
   }

   private void loadPropertiesFromFile(final String filePath) {
      if (filePath != null) {
         if (this.verbose()) {
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Using properties file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, filePath)})))));
         }

         scala.collection.Map properties = org.apache.spark.util.Utils$.MODULE$.getPropertiesFromFile(filePath);
         properties.foreach((x0$1) -> {
            $anonfun$loadPropertiesFromFile$2(this, x0$1);
            return BoxedUnit.UNIT;
         });
         if (this.verbose()) {
            org.apache.spark.util.Utils$.MODULE$.redact(properties).foreach((x0$2) -> {
               $anonfun$loadPropertiesFromFile$3(this, x0$2);
               return BoxedUnit.UNIT;
            });
         }
      }
   }

   private void mergeDefaultSparkProperties() {
      this.loadPropertiesFromFile(this.propertiesFile());
      if (this.propertiesFile() == null || this.loadSparkDefaults()) {
         this.loadPropertiesFromFile(org.apache.spark.util.Utils$.MODULE$.getDefaultPropertiesFile(this.env));
      }
   }

   private void ignoreNonSparkProperties() {
      this.sparkProperties().keys().foreach((k) -> {
         $anonfun$ignoreNonSparkProperties$1(this, k);
         return BoxedUnit.UNIT;
      });
   }

   private void loadEnvironmentArguments() {
      this.maybeMaster_$eq(this.maybeMaster().orElse(() -> this.sparkProperties().get("spark.master")).orElse(() -> this.env.get("MASTER")));
      this.maybeRemote_$eq(this.maybeRemote().orElse(() -> this.sparkProperties().get("spark.remote")).orElse(() -> this.env.get("SPARK_REMOTE")));
      this.driverExtraClassPath_$eq((String)scala.Option..MODULE$.apply(this.driverExtraClassPath()).orElse(() -> this.sparkProperties().get(package$.MODULE$.DRIVER_CLASS_PATH().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.driverExtraJavaOptions_$eq((String)scala.Option..MODULE$.apply(this.driverExtraJavaOptions()).orElse(() -> this.sparkProperties().get(package$.MODULE$.DRIVER_JAVA_OPTIONS().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.driverExtraLibraryPath_$eq((String)scala.Option..MODULE$.apply(this.driverExtraLibraryPath()).orElse(() -> this.sparkProperties().get(package$.MODULE$.DRIVER_LIBRARY_PATH().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.driverMemory_$eq((String)scala.Option..MODULE$.apply(this.driverMemory()).orElse(() -> this.sparkProperties().get(package$.MODULE$.DRIVER_MEMORY().key())).orElse(() -> this.env.get("SPARK_DRIVER_MEMORY")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.driverCores_$eq((String)scala.Option..MODULE$.apply(this.driverCores()).orElse(() -> this.sparkProperties().get(package$.MODULE$.DRIVER_CORES().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.executorMemory_$eq((String)scala.Option..MODULE$.apply(this.executorMemory()).orElse(() -> this.sparkProperties().get(package$.MODULE$.EXECUTOR_MEMORY().key())).orElse(() -> this.env.get("SPARK_EXECUTOR_MEMORY")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.executorCores_$eq((String)scala.Option..MODULE$.apply(this.executorCores()).orElse(() -> this.sparkProperties().get(package$.MODULE$.EXECUTOR_CORES().key())).orElse(() -> this.env.get("SPARK_EXECUTOR_CORES")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.totalExecutorCores_$eq((String)scala.Option..MODULE$.apply(this.totalExecutorCores()).orElse(() -> this.sparkProperties().get(package$.MODULE$.CORES_MAX().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.name_$eq((String)scala.Option..MODULE$.apply(this.name()).orElse(() -> this.sparkProperties().get("spark.app.name")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.jars_$eq((String)scala.Option..MODULE$.apply(this.jars()).orElse(() -> this.sparkProperties().get(package$.MODULE$.JARS().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.files_$eq((String)scala.Option..MODULE$.apply(this.files()).orElse(() -> this.sparkProperties().get(package$.MODULE$.FILES().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.archives_$eq((String)scala.Option..MODULE$.apply(this.archives()).orElse(() -> this.sparkProperties().get(package$.MODULE$.ARCHIVES().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.pyFiles_$eq((String)scala.Option..MODULE$.apply(this.pyFiles()).orElse(() -> this.sparkProperties().get(package$.MODULE$.SUBMIT_PYTHON_FILES().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.ivyRepoPath_$eq((String)this.sparkProperties().get(package$.MODULE$.JAR_IVY_REPO_PATH().key()).orNull(scala..less.colon.less..MODULE$.refl()));
      this.ivySettingsPath_$eq(this.sparkProperties().get(package$.MODULE$.JAR_IVY_SETTING_PATH().key()));
      this.packages_$eq((String)scala.Option..MODULE$.apply(this.packages()).orElse(() -> this.sparkProperties().get(package$.MODULE$.JAR_PACKAGES().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.packagesExclusions_$eq((String)scala.Option..MODULE$.apply(this.packagesExclusions()).orElse(() -> this.sparkProperties().get(package$.MODULE$.JAR_PACKAGES_EXCLUSIONS().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.repositories_$eq((String)scala.Option..MODULE$.apply(this.repositories()).orElse(() -> this.sparkProperties().get(package$.MODULE$.JAR_REPOSITORIES().key())).orNull(scala..less.colon.less..MODULE$.refl()));
      this.deployMode_$eq((String)scala.Option..MODULE$.apply(this.deployMode()).orElse(() -> this.sparkProperties().get(package$.MODULE$.SUBMIT_DEPLOY_MODE().key())).orElse(() -> this.env.get("DEPLOY_MODE")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.numExecutors_$eq((String)scala.Option..MODULE$.apply(this.numExecutors()).getOrElse(() -> (String)this.sparkProperties().get(package$.MODULE$.EXECUTOR_INSTANCES().key()).orNull(scala..less.colon.less..MODULE$.refl())));
      this.queue_$eq((String)scala.Option..MODULE$.apply(this.queue()).orElse(() -> this.sparkProperties().get("spark.yarn.queue")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.keytab_$eq((String)scala.Option..MODULE$.apply(this.keytab()).orElse(() -> this.sparkProperties().get(package$.MODULE$.KEYTAB().key())).orElse(() -> this.sparkProperties().get("spark.yarn.keytab")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.principal_$eq((String)scala.Option..MODULE$.apply(this.principal()).orElse(() -> this.sparkProperties().get(package$.MODULE$.PRINCIPAL().key())).orElse(() -> this.sparkProperties().get("spark.yarn.principal")).orNull(scala..less.colon.less..MODULE$.refl()));
      this.dynamicAllocationEnabled_$eq(this.sparkProperties().get(package$.MODULE$.DYN_ALLOCATION_ENABLED().key()).exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$loadEnvironmentArguments$32(x$1))));
      if (this.master().startsWith("yarn")) {
         this.name_$eq((String)scala.Option..MODULE$.apply(this.name()).orElse(() -> this.env.get("SPARK_YARN_APP_NAME")).orNull(scala..less.colon.less..MODULE$.refl()));
      }

      this.name_$eq((String)scala.Option..MODULE$.apply(this.name()).orElse(() -> scala.Option..MODULE$.apply(this.mainClass())).orNull(scala..less.colon.less..MODULE$.refl()));
      if (this.name() == null && this.primaryResource() != null) {
         this.name_$eq((new File(this.primaryResource())).getName());
      }

      this.action_$eq((Enumeration.Value)scala.Option..MODULE$.apply(this.action()).getOrElse(() -> SparkSubmitAction$.MODULE$.SUBMIT()));
   }

   private void validateArguments() {
      label56: {
         Enumeration.Value var2 = this.action();
         Enumeration.Value var10000 = SparkSubmitAction$.MODULE$.SUBMIT();
         if (var10000 == null) {
            if (var2 == null) {
               break label56;
            }
         } else if (var10000.equals(var2)) {
            break label56;
         }

         label57: {
            var10000 = SparkSubmitAction$.MODULE$.KILL();
            if (var10000 == null) {
               if (var2 == null) {
                  break label57;
               }
            } else if (var10000.equals(var2)) {
               break label57;
            }

            label58: {
               var10000 = SparkSubmitAction$.MODULE$.REQUEST_STATUS();
               if (var10000 == null) {
                  if (var2 == null) {
                     break label58;
                  }
               } else if (var10000.equals(var2)) {
                  break label58;
               }

               label34: {
                  var10000 = SparkSubmitAction$.MODULE$.PRINT_VERSION();
                  if (var10000 == null) {
                     if (var2 == null) {
                        break label34;
                     }
                  } else if (var10000.equals(var2)) {
                     break label34;
                  }

                  throw new MatchError(var2);
               }

               BoxedUnit var10 = BoxedUnit.UNIT;
               return;
            }

            this.validateStatusRequestArguments();
            BoxedUnit var11 = BoxedUnit.UNIT;
            return;
         }

         this.validateKillArguments();
         BoxedUnit var12 = BoxedUnit.UNIT;
         return;
      }

      this.validateSubmitArguments();
      BoxedUnit var13 = BoxedUnit.UNIT;
   }

   private void validateSubmitArguments() {
      if (this.args.length() == 0) {
         this.printUsageAndExit(-1, this.printUsageAndExit$default$2());
      }

      if (this.maybeRemote().isDefined() && (this.maybeMaster().isDefined() || this.deployMode() != null)) {
         this.error("Remote cannot be specified with master and/or deploy mode.");
      }

      if (this.primaryResource() == null) {
         this.error("Must specify a primary resource (JAR or Python or R file)");
      }

      if (this.driverMemory() != null && BoxesRunTime.unboxToLong(scala.util.Try..MODULE$.apply((JFunction0.mcJ.sp)() -> JavaUtils.byteStringAsBytes(this.driverMemory())).getOrElse((JFunction0.mcJ.sp)() -> -1L)) <= 0L) {
         this.error("Driver memory must be a positive number");
      }

      if (this.executorMemory() != null && BoxesRunTime.unboxToLong(scala.util.Try..MODULE$.apply((JFunction0.mcJ.sp)() -> JavaUtils.byteStringAsBytes(this.executorMemory())).getOrElse((JFunction0.mcJ.sp)() -> -1L)) <= 0L) {
         this.error("Executor memory must be a positive number");
      }

      if (this.driverCores() != null && BoxesRunTime.unboxToInt(scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(this.driverCores()))).getOrElse((JFunction0.mcI.sp)() -> -1)) <= 0) {
         this.error("Driver cores must be a positive number");
      }

      if (this.executorCores() != null && BoxesRunTime.unboxToInt(scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(this.executorCores()))).getOrElse((JFunction0.mcI.sp)() -> -1)) <= 0) {
         this.error("Executor cores must be a positive number");
      }

      if (this.totalExecutorCores() != null && BoxesRunTime.unboxToInt(scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(this.totalExecutorCores()))).getOrElse((JFunction0.mcI.sp)() -> -1)) <= 0) {
         this.error("Total executor cores must be a positive number");
      }

      if (!this.dynamicAllocationEnabled() && this.numExecutors() != null && BoxesRunTime.unboxToInt(scala.util.Try..MODULE$.apply((JFunction0.mcI.sp)() -> scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(this.numExecutors()))).getOrElse((JFunction0.mcI.sp)() -> -1)) <= 0) {
         this.error("Number of executors must be a positive number");
      }

      if (this.master().startsWith("yarn")) {
         boolean hasHadoopEnv = this.env.contains("HADOOP_CONF_DIR") || this.env.contains("YARN_CONF_DIR");
         if (!hasHadoopEnv && !org.apache.spark.util.Utils$.MODULE$.isTesting()) {
            this.error("When running with master '" + this.master() + "' either HADOOP_CONF_DIR or YARN_CONF_DIR must be set in the environment.");
         }
      }

      if (this.proxyUser() != null && this.principal() != null) {
         this.error("Only one of --proxy-user or --principal can be provided.");
      }
   }

   private void validateKillArguments() {
      if (this.submissionToKill() == null) {
         this.error("Please specify a submission to kill.");
      }
   }

   private void validateStatusRequestArguments() {
      if (this.submissionToRequestStatusFor() == null) {
         this.error("Please specify a submission to request status for.");
      }
   }

   public boolean isStandaloneCluster() {
      boolean var2;
      label25: {
         if (this.master().startsWith("spark://")) {
            String var10000 = this.deployMode();
            String var1 = "cluster";
            if (var10000 == null) {
               if (var1 == null) {
                  break label25;
               }
            } else if (var10000.equals(var1)) {
               break label25;
            }
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public String toString() {
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      String var10002 = this.master();
      return var10000.stripMargin$extension(var10001.augmentString("Parsed arguments:\n    |  master                  " + var10002 + "\n    |  remote                  " + this.maybeRemote().orNull(scala..less.colon.less..MODULE$.refl()) + "\n    |  deployMode              " + this.deployMode() + "\n    |  executorMemory          " + this.executorMemory() + "\n    |  executorCores           " + this.executorCores() + "\n    |  totalExecutorCores      " + this.totalExecutorCores() + "\n    |  propertiesFile          " + this.propertiesFile() + "\n    |  driverMemory            " + this.driverMemory() + "\n    |  driverCores             " + this.driverCores() + "\n    |  driverExtraClassPath    " + this.driverExtraClassPath() + "\n    |  driverExtraLibraryPath  " + this.driverExtraLibraryPath() + "\n    |  driverExtraJavaOptions  " + this.driverExtraJavaOptions() + "\n    |  supervise               " + this.supervise() + "\n    |  queue                   " + this.queue() + "\n    |  numExecutors            " + this.numExecutors() + "\n    |  files                   " + this.files() + "\n    |  pyFiles                 " + this.pyFiles() + "\n    |  archives                " + this.archives() + "\n    |  mainClass               " + this.mainClass() + "\n    |  primaryResource         " + this.primaryResource() + "\n    |  name                    " + this.name() + "\n    |  childArgs               [" + this.childArgs().mkString(" ") + "]\n    |  jars                    " + this.jars() + "\n    |  packages                " + this.packages() + "\n    |  packagesExclusions      " + this.packagesExclusions() + "\n    |  repositories            " + this.repositories() + "\n    |  verbose                 " + this.verbose() + "\n    |\n    |Spark properties used, including those specified through\n    | --conf and those from the properties file " + this.propertiesFile() + ":\n    |" + ((IterableOnceOps)org.apache.spark.util.Utils$.MODULE$.redact(this.sparkProperties()).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$))).mkString("  ", "\n  ", "\n") + "\n    "));
   }

   public boolean handle(final String opt, final String value) {
      label203: {
         switch (opt == null ? 0 : opt.hashCode()) {
            case -1862225754:
               if ("--driver-memory".equals(opt)) {
                  this.driverMemory_$eq(value);
                  break label203;
               }
               break;
            case -1855573605:
               if ("--executor-memory".equals(opt)) {
                  this.executorMemory_$eq(value);
                  break label203;
               }
               break;
            case -1790075175:
               if ("--num-executors".equals(opt)) {
                  this.numExecutors_$eq(value);
                  break label203;
               }
               break;
            case -1700553327:
               if ("--archives".equals(opt)) {
                  this.archives_$eq(org.apache.spark.util.Utils$.MODULE$.resolveURIs(value));
                  break label203;
               }
               break;
            case -1628952616:
               if ("--class".equals(opt)) {
                  this.mainClass_$eq(value);
                  break label203;
               }
               break;
            case -1626261289:
               if ("--files".equals(opt)) {
                  this.files_$eq(org.apache.spark.util.Utils$.MODULE$.resolveURIs(value));
                  break label203;
               }
               break;
            case -1615751311:
               if ("--queue".equals(opt)) {
                  this.queue_$eq(value);
                  break label203;
               }
               break;
            case -1323647346:
               if ("--principal".equals(opt)) {
                  this.principal_$eq(value);
                  break label203;
               }
               break;
            case -1177168678:
               if ("--executor-cores".equals(opt)) {
                  this.executorCores_$eq(value);
                  break label203;
               }
               break;
            case -1080163991:
               if ("--load-spark-defaults".equals(opt)) {
                  this.loadSparkDefaults_$eq(true);
                  break label203;
               }
               break;
            case -958474413:
               if ("--py-files".equals(opt)) {
                  this.pyFiles_$eq(org.apache.spark.util.Utils$.MODULE$.resolveURIs(value));
                  break label203;
               }
               break;
            case -573735293:
               if ("--total-executor-cores".equals(opt)) {
                  this.totalExecutorCores_$eq(value);
                  break label203;
               }
               break;
            case 156550304:
               if ("--supervise".equals(opt)) {
                  this.supervise_$eq(true);
                  break label203;
               }
               break;
            case 348263786:
               if ("--proxy-user".equals(opt)) {
                  this.proxyUser_$eq(value);
                  break label203;
               }
               break;
            case 473675040:
               if ("--exclude-packages".equals(opt)) {
                  this.packagesExclusions_$eq(value);
                  break label203;
               }
               break;
            case 520197896:
               if ("--repositories".equals(opt)) {
                  this.repositories_$eq(value);
                  break label203;
               }
               break;
            case 741979608:
               if ("--driver-java-options".equals(opt)) {
                  this.driverExtraJavaOptions_$eq(value);
                  break label203;
               }
               break;
            case 766622349:
               if ("--packages".equals(opt)) {
                  this.packages_$eq(value);
                  break label203;
               }
               break;
            case 1001463100:
               if ("--usage-error".equals(opt)) {
                  this.printUsageAndExit(1, this.printUsageAndExit$default$2());
                  break label203;
               }
               break;
            case 1074897023:
               if ("--driver-class-path".equals(opt)) {
                  this.driverExtraClassPath_$eq(value);
                  break label203;
               }
               break;
            case 1132239145:
               if ("--deploy-mode".equals(opt)) {
                  label207: {
                     String var5 = "client";
                     if (value == null) {
                        if (var5 == null) {
                           break label207;
                        }
                     } else if (value.equals(var5)) {
                        break label207;
                     }

                     String var6 = "cluster";
                     if (value == null) {
                        if (var6 == null) {
                           break label207;
                        }
                     } else if (value.equals(var6)) {
                        break label207;
                     }

                     this.error("--deploy-mode must be either \"client\" or \"cluster\"");
                  }

                  this.deployMode_$eq(value);
                  break label203;
               }
               break;
            case 1265360502:
               if ("--keytab".equals(opt)) {
                  this.keytab_$eq(value);
                  break label203;
               }
               break;
            case 1318746114:
               if ("--master".equals(opt)) {
                  this.maybeMaster_$eq(scala.Option..MODULE$.apply(value));
                  break label203;
               }
               break;
            case 1332929732:
               if ("--conf".equals(opt)) {
                  Tuple2 var8 = SparkSubmitUtils$.MODULE$.parseSparkConfProperty(value);
                  if (var8 == null) {
                     throw new MatchError(var8);
                  }

                  String confName = (String)var8._1();
                  String confValue = (String)var8._2();
                  Tuple2 var7 = new Tuple2(confName, confValue);
                  String confName = (String)var7._1();
                  String confValue = (String)var7._2();
                  this.sparkProperties().update(confName, confValue);
                  break label203;
               }
               break;
            case 1333069025:
               if ("--help".equals(opt)) {
                  this.printUsageAndExit(0, this.printUsageAndExit$default$2());
                  break label203;
               }
               break;
            case 1333124952:
               if ("--jars".equals(opt)) {
                  this.jars_$eq(org.apache.spark.util.Utils$.MODULE$.resolveURIs(value));
                  break label203;
               }
               break;
            case 1333162238:
               if ("--kill".equals(opt)) {
                  this.submissionToKill_$eq(value);
                  if (this.action() != null) {
                     Enumeration.Value var15 = this.action();
                     this.error("Action cannot be both " + var15 + " and " + SparkSubmitAction$.MODULE$.KILL() + ".");
                  }

                  this.action_$eq(SparkSubmitAction$.MODULE$.KILL());
                  break label203;
               }
               break;
            case 1333243947:
               if ("--name".equals(opt)) {
                  this.name_$eq(value);
                  break label203;
               }
               break;
            case 1455016047:
               if ("--driver-cores".equals(opt)) {
                  this.driverCores_$eq(value);
                  break label203;
               }
               break;
            case 1465402854:
               if ("--remote".equals(opt)) {
                  this.maybeRemote_$eq(scala.Option..MODULE$.apply(value));
                  break label203;
               }
               break;
            case 1507532178:
               if ("--status".equals(opt)) {
                  this.submissionToRequestStatusFor_$eq(value);
                  if (this.action() != null) {
                     Enumeration.Value var10001 = this.action();
                     this.error("Action cannot be both " + var10001 + " and " + SparkSubmitAction$.MODULE$.REQUEST_STATUS() + ".");
                  }

                  this.action_$eq(SparkSubmitAction$.MODULE$.REQUEST_STATUS());
                  break label203;
               }
               break;
            case 1737088994:
               if ("--verbose".equals(opt)) {
                  this.verbose_$eq(true);
                  break label203;
               }
               break;
            case 1737589560:
               if ("--version".equals(opt)) {
                  this.action_$eq(SparkSubmitAction$.MODULE$.PRINT_VERSION());
                  break label203;
               }
               break;
            case 1769406940:
               if ("--driver-library-path".equals(opt)) {
                  this.driverExtraLibraryPath_$eq(value);
                  break label203;
               }
               break;
            case 2027149366:
               if ("--properties-file".equals(opt)) {
                  this.propertiesFile_$eq(value);
                  break label203;
               }
         }

         this.error("Unexpected argument '" + opt + "'.");
      }

      boolean var14;
      label214: {
         Enumeration.Value var10000 = this.action();
         Enumeration.Value var13 = SparkSubmitAction$.MODULE$.PRINT_VERSION();
         if (var10000 == null) {
            if (var13 != null) {
               break label214;
            }
         } else if (!var10000.equals(var13)) {
            break label214;
         }

         var14 = false;
         return var14;
      }

      var14 = true;
      return var14;
   }

   public boolean handleUnknown(final String opt) {
      if (opt.startsWith("-")) {
         this.error("Unrecognized option '" + opt + "'.");
      }

      this.primaryResource_$eq(!SparkSubmit$.MODULE$.isShell(opt) && !SparkSubmit$.MODULE$.isInternal(opt) ? org.apache.spark.util.Utils$.MODULE$.resolveURI(opt).toString() : opt);
      this.isPython_$eq(SparkSubmit$.MODULE$.isPython(opt));
      this.isR_$eq(SparkSubmit$.MODULE$.isR(opt));
      return false;
   }

   public void handleExtraArgs(final List extra) {
      this.childArgs().$plus$plus$eq(scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(extra).asScala());
   }

   private void printUsageAndExit(final int exitCode, final Object unknownParam) {
      if (unknownParam != null) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Unknown/unsupported param ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.UNKNOWN_PARAM..MODULE$, unknownParam)})))));
      }

      String command = (String)scala.sys.package..MODULE$.env().getOrElse("_SPARK_CMD_USAGE", () -> scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("Usage: spark-submit [options] <app jar | python file | R file> [app arguments]\n        |Usage: spark-submit --kill [submission ID] --master [spark://...]\n        |Usage: spark-submit --status [submission ID] --master [spark://...]\n        |Usage: spark-submit run-example [options] example-class [example args]")));
      this.logInfo((Function0)(() -> command));
      int mem_mb = org.apache.spark.util.Utils$.MODULE$.DEFAULT_DRIVER_MEM_MB();
      this.logInfo((Function0)(() -> scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n        |Options:\n        |  --master MASTER_URL         spark://host:port, yarn,\n        |                              k8s://https://host:port, or local (Default: local[*]).\n        |  --deploy-mode DEPLOY_MODE   Whether to launch the driver program locally (\"client\") or\n        |                              on one of the worker machines inside the cluster (\"cluster\")\n        |                              (Default: client).\n        |  --class CLASS_NAME          Your application's main class (for Java / Scala apps).\n        |  --name NAME                 A name of your application.\n        |  --jars JARS                 Comma-separated list of jars to include on the driver\n        |                              and executor classpaths.\n        |  --packages                  Comma-separated list of maven coordinates of jars to include\n        |                              on the driver and executor classpaths. Will search the local\n        |                              maven repo, then maven central and any additional remote\n        |                              repositories given by --repositories. The format for the\n        |                              coordinates should be groupId:artifactId:version.\n        |  --exclude-packages          Comma-separated list of groupId:artifactId, to exclude while\n        |                              resolving the dependencies provided in --packages to avoid\n        |                              dependency conflicts.\n        |  --repositories              Comma-separated list of additional remote repositories to\n        |                              search for the maven coordinates given with --packages.\n        |  --py-files PY_FILES         Comma-separated list of .zip, .egg, or .py files to place\n        |                              on the PYTHONPATH for Python apps.\n        |  --files FILES               Comma-separated list of files to be placed in the working\n        |                              directory of each executor. File paths of these files\n        |                              in executors can be accessed via SparkFiles.get(fileName).\n        |  --archives ARCHIVES         Comma-separated list of archives to be extracted into the\n        |                              working directory of each executor.\n        |\n        |  --conf, -c PROP=VALUE       Arbitrary Spark configuration property.\n        |  --properties-file FILE      Path to a file from which to load extra properties. If not\n        |                              specified, this will look for conf/spark-defaults.conf.\n        |  --load-spark-defaults       Whether to load properties from conf/spark-defaults.conf,\n        |                              even if --properties-file is specified. Configurations\n        |                              specified in --properties-file will take precedence over\n        |                              those in conf/spark-defaults.conf.\n        |\n        |  --driver-memory MEM         Memory for driver (e.g. 1000M, 2G) (Default: " + mem_mb + "M).\n        |  --driver-java-options       Extra Java options to pass to the driver.\n        |  --driver-library-path       Extra library path entries to pass to the driver.\n        |  --driver-class-path         Extra class path entries to pass to the driver. Note that\n        |                              jars added with --jars are automatically included in the\n        |                              classpath.\n        |\n        |  --executor-memory MEM       Memory per executor (e.g. 1000M, 2G) (Default: 1G).\n        |\n        |  --proxy-user NAME           User to impersonate when submitting the application.\n        |                              This argument does not work with --principal / --keytab.\n        |\n        |  --help, -h                  Show this help message and exit.\n        |  --verbose, -v               Print additional debug output.\n        |  --version,                  Print the version of current Spark.\n        |\n        | Spark Connect only:\n        |   --remote CONNECT_URL       URL to connect to the server for Spark Connect, e.g.,\n        |                              sc://host:port. --master and --deploy-mode cannot be set\n        |                              together with this option.\n        |\n        | Cluster deploy mode only:\n        |  --driver-cores NUM          Number of cores used by the driver, only in cluster mode\n        |                              (Default: 1).\n        |\n        | Spark standalone with cluster deploy mode only:\n        |  --supervise                 If given, restarts the driver on failure.\n        |\n        | Spark standalone or K8s with cluster deploy mode only:\n        |  --kill SUBMISSION_ID        If given, kills the driver specified.\n        |  --status SUBMISSION_ID      If given, requests the status of the driver specified.\n        |\n        | Spark standalone only:\n        |  --total-executor-cores NUM  Total cores for all executors.\n        |\n        | Spark standalone, YARN and Kubernetes only:\n        |  --executor-cores NUM        Number of cores used by each executor. (Default: 1 in\n        |                              YARN and K8S modes, or all available cores on the worker\n        |                              in standalone mode).\n        |\n        | Spark on YARN and Kubernetes only:\n        |  --num-executors NUM         Number of executors to launch (Default: 2).\n        |                              If dynamic allocation is enabled, the initial number of\n        |                              executors will be at least NUM.\n        |  --principal PRINCIPAL       Principal to be used to login to KDC.\n        |  --keytab KEYTAB             The full path to the file that contains the keytab for the\n        |                              principal specified above.\n        |\n        | Spark on YARN only:\n        |  --queue QUEUE_NAME          The YARN queue to submit to (Default: \"default\").\n      "))));
      if (SparkSubmit$.MODULE$.isSqlShell(this.mainClass())) {
         this.logInfo((Function0)(() -> "CLI options:"));
         this.logInfo((Function0)(() -> this.getSqlShellOptions()));
      }

      throw new SparkUserAppException(exitCode);
   }

   private Object printUsageAndExit$default$2() {
      return null;
   }

   private String getSqlShellOptions() {
      PrintStream currentOut = System.out;
      PrintStream currentErr = System.err;

      String var10000;
      try {
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         PrintStream stream = new PrintStream(out);
         System.setOut(stream);
         System.setErr(stream);
         org.apache.spark.util.Utils$.MODULE$.classForName(this.mainClass(), org.apache.spark.util.Utils$.MODULE$.classForName$default$2(), org.apache.spark.util.Utils$.MODULE$.classForName$default$3()).getMethod("printUsage").invoke((Object)null);
         stream.flush();
         var10000 = scala.io.Source..MODULE$.fromString(new String(out.toByteArray(), StandardCharsets.UTF_8)).getLines().filter((line) -> BoxesRunTime.boxToBoolean($anonfun$getSqlShellOptions$1(line))).mkString("\n");
      } finally {
         System.setOut(currentOut);
         System.setErr(currentErr);
      }

      return var10000;
   }

   private void error(final String msg) {
      throw new SparkException(msg);
   }

   public SparkConf toSparkConf(final Option sparkConf) {
      return (SparkConf)this.sparkProperties().foldLeft(sparkConf.getOrElse(() -> new SparkConf()), (x0$1, x1$1) -> {
         Tuple2 var3 = new Tuple2(x0$1, x1$1);
         if (var3 != null) {
            SparkConf conf = (SparkConf)var3._1();
            Tuple2 var5 = (Tuple2)var3._2();
            if (var5 != null) {
               String k = (String)var5._1();
               String v = (String)var5._2();
               return conf.set(k, v);
            }
         }

         throw new MatchError(var3);
      });
   }

   public Option toSparkConf$default$1() {
      return scala.None..MODULE$;
   }

   // $FF: synthetic method
   public static final void $anonfun$loadPropertiesFromFile$2(final SparkSubmitArguments $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         String v = (String)x0$1._2();
         if (!$this.sparkProperties().contains(k)) {
            $this.sparkProperties().update(k, v);
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$loadPropertiesFromFile$3(final SparkSubmitArguments $this, final Tuple2 x0$2) {
      if (x0$2 != null) {
         String k = (String)x0$2._1();
         String v = (String)x0$2._2();
         $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Adding default property: ", "=", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.KEY..MODULE$, k), new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, v)})))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$ignoreNonSparkProperties$1(final SparkSubmitArguments $this, final String k) {
      if (!k.startsWith("spark.")) {
         $this.sparkProperties().$minus$eq(k);
         $this.logWarning(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Ignoring non-Spark config property: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, k)})))));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadEnvironmentArguments$32(final String x$1) {
      return "true".equalsIgnoreCase(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getSqlShellOptions$1(final String line) {
      return !line.startsWith("log4j") && !line.startsWith("usage");
   }

   public SparkSubmitArguments(final Seq args, final Map env) {
      this.args = args;
      this.env = env;
      Logging.$init$(this);
      this.maybeMaster = scala.None..MODULE$;
      this.maybeRemote = scala.None..MODULE$;
      this.deployMode = null;
      this.executorMemory = null;
      this.executorCores = null;
      this.totalExecutorCores = null;
      this.propertiesFile = null;
      this.loadSparkDefaults = false;
      this.driverMemory = null;
      this.driverExtraClassPath = null;
      this.driverExtraLibraryPath = null;
      this.driverExtraJavaOptions = null;
      this.queue = null;
      this.numExecutors = null;
      this.files = null;
      this.archives = null;
      this.mainClass = null;
      this.primaryResource = null;
      this.name = null;
      this.childArgs = new ArrayBuffer();
      this.jars = null;
      this.packages = null;
      this.repositories = null;
      this.ivyRepoPath = null;
      this.ivySettingsPath = scala.None..MODULE$;
      this.packagesExclusions = null;
      this.verbose = false;
      this.isPython = false;
      this.pyFiles = null;
      this.isR = false;
      this.action = null;
      this.sparkProperties = new HashMap();
      this.proxyUser = null;
      this.principal = null;
      this.keytab = null;
      this.dynamicAllocationEnabled = false;
      this.supervise = false;
      this.driverCores = null;
      this.submissionToKill = null;
      this.submissionToRequestStatusFor = null;
      this.useRest = false;
      this.parse(scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(args).asJava());
      this.mergeDefaultSparkProperties();
      this.ignoreNonSparkProperties();
      this.loadEnvironmentArguments();
      this.useRest_$eq(scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString((String)this.sparkProperties().getOrElse("spark.master.rest.enabled", () -> "false"))));
      this.validateArguments();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
