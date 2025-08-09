package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.spark.ExecutorAllocationClient;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.util.Clock;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Set;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LongRef;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0015e!\u0002/^\u0001u+\u0007\u0002\u0003:\u0001\u0005\u000b\u0007I\u0011\u0002;\t\u0011e\u0004!\u0011!Q\u0001\nUD\u0001B\u001f\u0001\u0003\u0002\u0003\u0006Ia\u001f\u0005\n\u007f\u0002\u0011\t\u0011)A\u0005\u0003\u0003A!\"!\u0004\u0001\u0005\u0003\u0005\u000b\u0011BA\b\u0011\u001d\tY\u0002\u0001C\u0001\u0003;Aq!a\u0007\u0001\t\u0003\tI\u0003C\u0005\u00028\u0001\u0011\r\u0011\"\u0003\u0002:!A\u0011\u0011\t\u0001!\u0002\u0013\tY\u0004C\u0005\u0002D\u0001\u0011\r\u0011\"\u0003\u0002:!A\u0011Q\t\u0001!\u0002\u0013\tY\u0004C\u0005\u0002H\u0001\u0011\r\u0011\"\u0001\u0002J!A\u0011\u0011\u000b\u0001!\u0002\u0013\tY\u0005C\u0005\u0002T\u0001\u0011\r\u0011\"\u0003\u0002V!A\u0011Q\f\u0001!\u0002\u0013\t9\u0006C\u0005\u0002`\u0001\u0011\r\u0011\"\u0003\u0002V!A\u0011\u0011\r\u0001!\u0002\u0013\t9\u0006C\u0005\u0002d\u0001\u0011\r\u0011\"\u0003\u0002f!A!\u0011\u0018\u0001!\u0002\u0013\t9\u0007C\u0005\u0003<\u0002\u0011\r\u0011\"\u0001\u0003>\"A!q\u0019\u0001!\u0002\u0013\u0011y\fC\u0005\u0003J\u0002\u0011\r\u0011\"\u0001\u0003L\"A!q\u001a\u0001!\u0002\u0013\u0011i\rC\u0005\u0003R\u0002\u0011\r\u0011\"\u0003\u0003T\"A!Q\u001e\u0001!\u0002\u0013\u0011)\u000eC\u0005\u0003p\u0002\u0001\r\u0011\"\u0001\u0002J!I!\u0011\u001f\u0001A\u0002\u0013\u0005!1\u001f\u0005\t\u0005o\u0004\u0001\u0015)\u0003\u0002L!I!\u0011 \u0001C\u0002\u0013\u0005!1 \u0005\t\u0007\u000b\u0001\u0001\u0015!\u0003\u0003~\"91q\u0001\u0001\u0005\u0002\r%\u0001bBB\u0006\u0001\u0011%1\u0011\u0002\u0005\b\u0007\u001b\u0001A\u0011BB\b\u0011\u001d\u0019I\u0002\u0001C\u0005\u00077A\u0001ba\b\u0001\t\u0003i6\u0011\u0005\u0005\b\u0007K\u0001A\u0011BB\u0014\u0011\u001d\u0019i\u0003\u0001C\u0001\u0007_Aqaa\u000e\u0001\t\u0003\u0019I\u0004C\u0004\u0004J\u0001!\taa\u0013\t\u000f\rE\u0003\u0001\"\u0001\u0004T!91Q\u000b\u0001\u0005\u0002\r]\u0003bBB.\u0001\u0011\u00051Q\f\u0004\b\u0003#\u0003!!XAJ\u0011\u001d\tYb\u000bC\u0001\u0003+3a!a&,\t\u0006e\u0005BCAZ[\tU\r\u0011\"\u0001\u0002:!Q\u0011QW\u0017\u0003\u0012\u0003\u0006I!a\u000f\t\u0015\u0005]VF!f\u0001\n\u0003\tI\u0004\u0003\u0006\u0002:6\u0012\t\u0012)A\u0005\u0003wA!\"a/.\u0005+\u0007I\u0011AA\u001d\u0011)\ti,\fB\tB\u0003%\u00111\b\u0005\b\u00037iC\u0011AA`\u0011%\tY-LA\u0001\n\u0003\ti\rC\u0005\u0002V6\n\n\u0011\"\u0001\u0002X\"I\u0011Q^\u0017\u0012\u0002\u0013\u0005\u0011q\u001b\u0005\n\u0003_l\u0013\u0013!C\u0001\u0003/D\u0011\"!=.\u0003\u0003%\t%a=\t\u0013\t\rQ&!A\u0005\u0002\u0005e\u0002\"\u0003B\u0003[\u0005\u0005I\u0011\u0001B\u0004\u0011%\u0011\u0019\"LA\u0001\n\u0003\u0012)\u0002C\u0005\u0003 5\n\t\u0011\"\u0001\u0003\"!I!QE\u0017\u0002\u0002\u0013\u0005#q\u0005\u0005\n\u0005Wi\u0013\u0011!C!\u0005[A\u0011Ba\f.\u0003\u0003%\tE!\r\t\u0013\tMR&!A\u0005B\tUr!\u0003B\u001dW\u0005\u0005\t\u0012\u0002B\u001e\r%\t9jKA\u0001\u0012\u0013\u0011i\u0004C\u0004\u0002\u001c\r#\tA!\u0016\t\u0013\t=2)!A\u0005F\tE\u0002\"\u0003B,\u0007\u0006\u0005I\u0011\u0011B-\u0011%\u0011\tgQA\u0001\n\u0003\u0013\u0019\u0007C\u0005\u0003r-\u0002\r\u0011\"\u0003\u0003t!I!\u0011Q\u0016A\u0002\u0013%!1\u0011\u0005\t\u0005\u001b[\u0003\u0015)\u0003\u0003v!I!qR\u0016A\u0002\u0013%\u0011\u0011\n\u0005\n\u0005#[\u0003\u0019!C\u0005\u0005'C\u0001Ba&,A\u0003&\u00111\n\u0005\b\u00053[C\u0011\u0001BN\u0011\u001d\u0011Yk\u000bC\u0001\u0003sAqA!,,\t\u0003\t)\u0006C\u0004\u00030.\"\tA!-\t\u000f\t=2\u0006\"\u0011\u00038\u001eA1\u0011M/\t\u0002}\u001b\u0019GB\u0004];\"\u0005ql!\u001a\t\u000f\u0005mA\u000b\"\u0001\u0004h!I1\u0011\u000e+C\u0002\u0013%\u00111\u001f\u0005\t\u0007W\"\u0006\u0015!\u0003\u0002v\"91Q\u000e+\u0005\u0002\r=\u0004bBB:)\u0012\u00051Q\u000f\u0005\b\u0007s\"F\u0011AB>\u0011%\u0019y\bVI\u0001\n\u0003\u0019\tIA\u0007IK\u0006dG\u000f\u001b+sC\u000e\\WM\u001d\u0006\u0003=~\u000b\u0011b]2iK\u0012,H.\u001a:\u000b\u0005\u0001\f\u0017!B:qCJ\\'B\u00012d\u0003\u0019\t\u0007/Y2iK*\tA-A\u0002pe\u001e\u001c2\u0001\u00014m!\t9'.D\u0001i\u0015\u0005I\u0017!B:dC2\f\u0017BA6i\u0005\u0019\te.\u001f*fMB\u0011Q\u000e]\u0007\u0002]*\u0011qnX\u0001\tS:$XM\u001d8bY&\u0011\u0011O\u001c\u0002\b\u0019><w-\u001b8h\u0003-a\u0017n\u001d;f]\u0016\u0014()^:\u0004\u0001U\tQ\u000f\u0005\u0002wo6\tQ,\u0003\u0002y;\nyA*\u001b<f\u0019&\u001cH/\u001a8fe\n+8/\u0001\u0007mSN$XM\\3s\u0005V\u001c\b%\u0001\u0003d_:4\u0007C\u0001?~\u001b\u0005y\u0016B\u0001@`\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\tbY2|7-\u0019;j_:\u001cE.[3oiB)q-a\u0001\u0002\b%\u0019\u0011Q\u00015\u0003\r=\u0003H/[8o!\ra\u0018\u0011B\u0005\u0004\u0003\u0017y&\u0001G#yK\u000e,Ho\u001c:BY2|7-\u0019;j_:\u001cE.[3oi\u0006)1\r\\8dWB!\u0011\u0011CA\f\u001b\t\t\u0019BC\u0002\u0002\u0016}\u000bA!\u001e;jY&!\u0011\u0011DA\n\u0005\u0015\u0019En\\2l\u0003\u0019a\u0014N\\5u}QQ\u0011qDA\u0011\u0003G\t)#a\n\u0011\u0005Y\u0004\u0001\"\u0002:\u0007\u0001\u0004)\b\"\u0002>\u0007\u0001\u0004Y\bBB@\u0007\u0001\u0004\t\t\u0001C\u0005\u0002\u000e\u0019\u0001\n\u00111\u0001\u0002\u0010Q1\u0011qDA\u0016\u0003kAq!!\f\b\u0001\u0004\ty#\u0001\u0002tGB\u0019A0!\r\n\u0007\u0005MrL\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH\u000f\u0003\u0004\u0000\u000f\u0001\u0007\u0011\u0011A\u0001\u0016\u001b\u0006CvLR!J\u0019V\u0013ViU0Q\u000bJ{V\tW#D+\t\tY\u0004E\u0002h\u0003{I1!a\u0010i\u0005\rIe\u000e^\u0001\u0017\u001b\u0006CvLR!J\u0019V\u0013ViU0Q\u000bJ{V\tW#DA\u0005AR*\u0011-`\r\u0006KE*\u0012#`\u000bb+5i\u0018)F%~su\nR#\u000235\u000b\u0005l\u0018$B\u00132+EiX#Y\u000b\u000e{\u0006+\u0012*`\u001d>#U\tI\u0001\"\u000bb\u001bE*\u0016#F?>suLR!J\u0019V\u0013Vi\u0018+J\u001b\u0016{U\u000bV0N\u00132c\u0015jU\u000b\u0003\u0003\u0017\u00022aZA'\u0013\r\ty\u0005\u001b\u0002\u0005\u0019>tw-\u0001\u0012F1\u000ecU\u000bR#`\u001f:{f)Q%M+J+u\fV%N\u000b>+FkX'J\u00192K5\u000bI\u0001\u001e\u000bb\u001bE*\u0016#F?\u001a+Ek\u0011%`\r\u0006KE*\u0016*F?\u0016s\u0015I\u0011'F\tV\u0011\u0011q\u000b\t\u0004O\u0006e\u0013bAA.Q\n9!i\\8mK\u0006t\u0017AH#Y\u00072+F)R0G\u000bR\u001b\u0005j\u0018$B\u00132+&+R0F\u001d\u0006\u0013E*\u0012#!\u0003\u001d*\u0005l\u0011'V\t\u0016{vJT0G\u0003&cUKU#`\t\u0016\u001bu*T'J'NKuJT0F\u001d\u0006\u0013E*\u0012#\u0002Q\u0015C6\tT+E\u000b~{ej\u0018$B\u00132+&+R0E\u000b\u000e{U*T%T'&{ejX#O\u0003\ncU\t\u0012\u0011\u0002/\u0015DXmY;u_JLE\rV8GC&dWO]3MSN$XCAA4!!\tI'a\u001d\u0002x\u00055UBAA6\u0015\u0011\ti'a\u001c\u0002\u000f5,H/\u00192mK*\u0019\u0011\u0011\u000f5\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002v\u0005-$a\u0002%bg\"l\u0015\r\u001d\t\u0005\u0003s\n9I\u0004\u0003\u0002|\u0005\r\u0005cAA?Q6\u0011\u0011q\u0010\u0006\u0004\u0003\u0003\u001b\u0018A\u0002\u001fs_>$h(C\u0002\u0002\u0006\"\fa\u0001\u0015:fI\u00164\u0017\u0002BAE\u0003\u0017\u0013aa\u0015;sS:<'bAACQB\u0019\u0011qR\u0016\u000e\u0003\u0001\u00111#\u0012=fGV$xN\u001d$bS2,(/\u001a'jgR\u001c2a\u000b4m)\t\tiI\u0001\u0004UCN\\\u0017\nZ\n\u0007[\u0019\fY*!)\u0011\u0007\u001d\fi*C\u0002\u0002 \"\u0014q\u0001\u0015:pIV\u001cG\u000f\u0005\u0003\u0002$\u00065f\u0002BAS\u0003SsA!! \u0002(&\t\u0011.C\u0002\u0002,\"\fq\u0001]1dW\u0006<W-\u0003\u0003\u00020\u0006E&\u0001D*fe&\fG.\u001b>bE2,'bAAVQ\u0006)1\u000f^1hK\u000611\u000f^1hK\u0002\nAb\u001d;bO\u0016\fE\u000f^3naR\fQb\u001d;bO\u0016\fE\u000f^3naR\u0004\u0013!\u0003;bg.Le\u000eZ3y\u0003)!\u0018m]6J]\u0012,\u0007\u0010\t\u000b\t\u0003\u0003\f)-a2\u0002JB\u0019\u00111Y\u0017\u000e\u0003-Bq!a-5\u0001\u0004\tY\u0004C\u0004\u00028R\u0002\r!a\u000f\t\u000f\u0005mF\u00071\u0001\u0002<\u0005!1m\u001c9z)!\t\t-a4\u0002R\u0006M\u0007\"CAZkA\u0005\t\u0019AA\u001e\u0011%\t9,\u000eI\u0001\u0002\u0004\tY\u0004C\u0005\u0002<V\u0002\n\u00111\u0001\u0002<\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCAAmU\u0011\tY$a7,\u0005\u0005u\u0007\u0003BAp\u0003Sl!!!9\u000b\t\u0005\r\u0018Q]\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a:i\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003W\f\tOA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fabY8qs\u0012\"WMZ1vYR$#'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u001a\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t)\u0010\u0005\u0003\u0002x\n\u0005QBAA}\u0015\u0011\tY0!@\u0002\t1\fgn\u001a\u0006\u0003\u0003\u007f\fAA[1wC&!\u0011\u0011RA}\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$BA!\u0003\u0003\u0010A\u0019qMa\u0003\n\u0007\t5\u0001NA\u0002B]fD\u0011B!\u0005<\u0003\u0003\u0005\r!a\u000f\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\u00119\u0002\u0005\u0004\u0003\u001a\tm!\u0011B\u0007\u0003\u0003_JAA!\b\u0002p\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9Fa\t\t\u0013\tEQ(!AA\u0002\t%\u0011A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$B!!>\u0003*!I!\u0011\u0003 \u0002\u0002\u0003\u0007\u00111H\u0001\tQ\u0006\u001c\bnQ8eKR\u0011\u00111H\u0001\ti>\u001cFO]5oOR\u0011\u0011Q_\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005]#q\u0007\u0005\n\u0005#\t\u0015\u0011!a\u0001\u0005\u0013\ta\u0001V1tW&#\u0007cAAb\u0007N)1Ia\u0010\u0003LAa!\u0011\tB$\u0003w\tY$a\u000f\u0002B6\u0011!1\t\u0006\u0004\u0005\u000bB\u0017a\u0002:v]RLW.Z\u0005\u0005\u0005\u0013\u0012\u0019EA\tBEN$(/Y2u\rVt7\r^5p]N\u0002BA!\u0014\u0003T5\u0011!q\n\u0006\u0005\u0005#\ni0\u0001\u0002j_&!\u0011q\u0016B()\t\u0011Y$A\u0003baBd\u0017\u0010\u0006\u0005\u0002B\nm#Q\fB0\u0011\u001d\t\u0019L\u0012a\u0001\u0003wAq!a.G\u0001\u0004\tY\u0004C\u0004\u0002<\u001a\u0003\r!a\u000f\u0002\u000fUt\u0017\r\u001d9msR!!Q\rB7!\u00159\u00171\u0001B4!%9'\u0011NA\u001e\u0003w\tY$C\u0002\u0003l!\u0014a\u0001V;qY\u0016\u001c\u0004\"\u0003B8\u000f\u0006\u0005\t\u0019AAa\u0003\rAH\u0005M\u0001\u0017M\u0006LG.\u001e:fg\u0006sG-\u0012=qSJLH+[7fgV\u0011!Q\u000f\t\u0007\u0003S\u00129Ha\u001f\n\t\te\u00141\u000e\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000fE\u0004h\u0005{\n\t-a\u0013\n\u0007\t}\u0004N\u0001\u0004UkBdWMM\u0001\u001bM\u0006LG.\u001e:fg\u0006sG-\u0012=qSJLH+[7fg~#S-\u001d\u000b\u0005\u0005\u000b\u0013Y\tE\u0002h\u0005\u000fK1A!#i\u0005\u0011)f.\u001b;\t\u0013\tE\u0011*!AA\u0002\tU\u0014a\u00064bS2,(/Z:B]\u0012,\u0005\u0010]5ssRKW.Z:!\u00035i\u0017N\\#ya&\u0014\u0018\u0010V5nK\u0006\tR.\u001b8FqBL'/\u001f+j[\u0016|F%Z9\u0015\t\t\u0015%Q\u0013\u0005\n\u0005#a\u0015\u0011!a\u0001\u0003\u0017\na\"\\5o\u000bb\u0004\u0018N]=US6,\u0007%A\u0006bI\u00124\u0015-\u001b7ve\u0016\u001cH\u0003\u0003BC\u0005;\u0013yJ!)\t\u000f\u0005Mf\n1\u0001\u0002<!9\u0011q\u0017(A\u0002\u0005m\u0002b\u0002BR\u001d\u0002\u0007!QU\u0001\u0012M\u0006LG.\u001e:fg&sG+Y:l'\u0016$\bc\u0001<\u0003(&\u0019!\u0011V/\u00033\u0015CXmY;u_J4\u0015-\u001b7ve\u0016\u001c\u0018J\u001c+bg.\u001cV\r^\u0001\u0016]VlWK\\5rk\u0016$\u0016m]6GC&dWO]3t\u0003\u001dI7/R7qif\fQ\u0004\u001a:pa\u001a\u000b\u0017\u000e\\;sKN<\u0016\u000e\u001e5US6,w.\u001e;CK\u001a|'/\u001a\u000b\u0005\u0005\u000b\u0013\u0019\fC\u0004\u00036F\u0003\r!a\u0013\u0002\u0015\u0011\u0014x\u000e\u001d\"fM>\u0014X\r\u0006\u0002\u0002x\u0005AR\r_3dkR|'/\u00133U_\u001a\u000b\u0017\u000e\\;sK2K7\u000f\u001e\u0011\u00025\u0015DXmY;u_JLE\rV8Fq\u000edW\u000fZ3e'R\fG/^:\u0016\u0005\t}\u0006\u0003CA5\u0003g\n9H!1\u0011\u0007Y\u0014\u0019-C\u0002\u0003Fv\u0013\u0001#\u0012=dYV$W\rZ#yK\u000e,Ho\u001c:\u00027\u0015DXmY;u_JLE\rV8Fq\u000edW\u000fZ3e'R\fG/^:!\u0003iqw\u000eZ3JIR{W\t_2mk\u0012,G-\u0012=qSJLH+[7f+\t\u0011i\r\u0005\u0005\u0002j\u0005M\u0014qOA&\u0003mqw\u000eZ3JIR{W\t_2mk\u0012,G-\u0012=qSJLH+[7fA\u0005\tr,\u001a=dYV$W\r\u001a(pI\u0016d\u0015n\u001d;\u0016\u0005\tU\u0007C\u0002Bl\u0005G\u00149/\u0004\u0002\u0003Z*!!1\u001cBo\u0003\u0019\tGo\\7jG*!!q\u001cBq\u0003)\u0019wN\\2veJ,g\u000e\u001e\u0006\u0005\u0003+\ti0\u0003\u0003\u0003f\ne'aD!u_6L7MU3gKJ,gnY3\u0011\r\u0005e$\u0011^A<\u0013\u0011\u0011Y/a#\u0003\u0007M+G/\u0001\n`Kb\u001cG.\u001e3fI:{G-\u001a'jgR\u0004\u0013A\u00048fqR,\u0005\u0010]5ssRKW.Z\u0001\u0013]\u0016DH/\u0012=qSJLH+[7f?\u0012*\u0017\u000f\u0006\u0003\u0003\u0006\nU\b\"\u0003B\t7\u0005\u0005\t\u0019AA&\u0003=qW\r\u001f;FqBL'/\u001f+j[\u0016\u0004\u0013a\u00058pI\u0016$v.\u0012=dYV$W\rZ#yK\u000e\u001cXC\u0001B\u007f!!\tI'a\u001d\u0002x\t}\bCBA5\u0007\u0003\t9(\u0003\u0003\u0004\u0004\u0005-$a\u0002%bg\"\u001cV\r^\u0001\u0015]>$W\rV8Fq\u000edW\u000fZ3e\u000bb,7m\u001d\u0011\u00029\u0005\u0004\b\u000f\\=Fq\u000edW\u000fZ3P]\u001a\u000b\u0017\u000e\\;sKRKW.Z8viR\u0011!QQ\u0001\u0015kB$\u0017\r^3OKb$X\t\u001f9jef$\u0016.\\3\u0002\u0019-LG\u000e\\#yK\u000e,Ho\u001c:\u0015\r\t\u00155\u0011CB\u000b\u0011\u001d\u0019\u0019\"\ta\u0001\u0003o\nA!\u001a=fG\"91qC\u0011A\u0002\u0005]\u0014aA7tO\u0006!2.\u001b7m\u000bb\u001cG.\u001e3fI\u0016CXmY;u_J$BA!\"\u0004\u001e!911\u0003\u0012A\u0002\u0005]\u0014\u0001G6jY2,\u0005p\u00197vI\u0016$\u0017\n\u001a7f\u000bb,7-\u001e;peR!!QQB\u0012\u0011\u001d\u0019\u0019b\ta\u0001\u0003o\n1d[5mY\u0016CXmY;u_J\u001cxJ\\#yG2,H-\u001a3O_\u0012,G\u0003\u0002BC\u0007SAqaa\u000b%\u0001\u0004\t9(\u0001\u0003o_\u0012,\u0017!H;qI\u0006$X-\u0012=dYV$W\r\u001a$pe\u001a+Go\u00195GC&dWO]3\u0015\r\t\u00155\u0011GB\u001b\u0011\u001d\u0019\u0019$\na\u0001\u0003o\nA\u0001[8ti\"911C\u0013A\u0002\u0005]\u0014AI;qI\u0006$X-\u0012=dYV$W\r\u001a$peN+8mY3tg\u001a,H\u000eV1tWN+G\u000f\u0006\u0005\u0003\u0006\u000em2qHB\"\u0011\u001d\u0019iD\na\u0001\u0003w\tqa\u001d;bO\u0016LE\rC\u0004\u0004B\u0019\u0002\r!a\u000f\u0002\u001dM$\u0018mZ3BiR,W\u000e\u001d;JI\"91Q\t\u0014A\u0002\r\u001d\u0013A\u00044bS2,(/Z:Cs\u0016CXm\u0019\t\t\u0003S\n\u0019(a\u001e\u0003&\u0006\u0011\u0012n]#yK\u000e,Ho\u001c:Fq\u000edW\u000fZ3e)\u0011\t9f!\u0014\t\u000f\r=s\u00051\u0001\u0002x\u0005QQ\r_3dkR|'/\u00133\u0002!\u0015D8\r\\;eK\u0012tu\u000eZ3MSN$HC\u0001Bt\u00039I7OT8eK\u0016C8\r\\;eK\u0012$B!a\u0016\u0004Z!911F\u0015A\u0002\u0005]\u0014!\u00065b]\u0012dWMU3n_Z,G-\u0012=fGV$xN\u001d\u000b\u0005\u0005\u000b\u001by\u0006C\u0004\u0004P)\u0002\r!a\u001e\u0002\u001b!+\u0017\r\u001c;i)J\f7m[3s!\t1HkE\u0002UM2$\"aa\u0019\u0002\u001f\u0011+e)Q+M)~#\u0016*T#P+R\u000b\u0001\u0003R#G\u0003VcEk\u0018+J\u001b\u0016{U\u000b\u0016\u0011\u00023%\u001cX\t_2mk\u0012,wJ\u001c$bS2,(/Z#oC\ndW\r\u001a\u000b\u0005\u0003/\u001a\t\bC\u0003{1\u0002\u000710\u0001\u000ehKR,\u0005p\u00197vI\u0016|eNR1jYV\u0014X\rV5nK>,H\u000f\u0006\u0003\u0002L\r]\u0004\"\u0002>Z\u0001\u0004Y\u0018!\b<bY&$\u0017\r^3Fq\u000edW\u000fZ3P]\u001a\u000b\u0017\u000e\\;sK\u000e{gNZ:\u0015\t\t\u00155Q\u0010\u0005\u0006uj\u0003\ra_\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\r\r%\u0006BA\b\u00037\u0004"
)
public class HealthTracker implements Logging {
   private final LiveListenerBus listenerBus;
   private final SparkConf conf;
   private final Option allocationClient;
   private final Clock clock;
   private final int MAX_FAILURES_PER_EXEC;
   private final int MAX_FAILED_EXEC_PER_NODE;
   private final long EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS;
   private final boolean EXCLUDE_FETCH_FAILURE_ENABLED;
   private final boolean EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED;
   private final HashMap executorIdToFailureList;
   private final HashMap executorIdToExcludedStatus;
   private final HashMap nodeIdToExcludedExpiryTime;
   private final AtomicReference _excludedNodeList;
   private long nextExpiryTime;
   private final HashMap nodeToExcludedExecs;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Clock $lessinit$greater$default$4() {
      return HealthTracker$.MODULE$.$lessinit$greater$default$4();
   }

   public static void validateExcludeOnFailureConfs(final SparkConf conf) {
      HealthTracker$.MODULE$.validateExcludeOnFailureConfs(conf);
   }

   public static long getExcludeOnFailureTimeout(final SparkConf conf) {
      return HealthTracker$.MODULE$.getExcludeOnFailureTimeout(conf);
   }

   public static boolean isExcludeOnFailureEnabled(final SparkConf conf) {
      return HealthTracker$.MODULE$.isExcludeOnFailureEnabled(conf);
   }

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

   private LiveListenerBus listenerBus() {
      return this.listenerBus;
   }

   private int MAX_FAILURES_PER_EXEC() {
      return this.MAX_FAILURES_PER_EXEC;
   }

   private int MAX_FAILED_EXEC_PER_NODE() {
      return this.MAX_FAILED_EXEC_PER_NODE;
   }

   public long EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS() {
      return this.EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS;
   }

   private boolean EXCLUDE_FETCH_FAILURE_ENABLED() {
      return this.EXCLUDE_FETCH_FAILURE_ENABLED;
   }

   private boolean EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED() {
      return this.EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED;
   }

   private HashMap executorIdToFailureList() {
      return this.executorIdToFailureList;
   }

   public HashMap executorIdToExcludedStatus() {
      return this.executorIdToExcludedStatus;
   }

   public HashMap nodeIdToExcludedExpiryTime() {
      return this.nodeIdToExcludedExpiryTime;
   }

   private AtomicReference _excludedNodeList() {
      return this._excludedNodeList;
   }

   public long nextExpiryTime() {
      return this.nextExpiryTime;
   }

   public void nextExpiryTime_$eq(final long x$1) {
      this.nextExpiryTime = x$1;
   }

   public HashMap nodeToExcludedExecs() {
      return this.nodeToExcludedExecs;
   }

   public void applyExcludeOnFailureTimeout() {
      long now = this.clock.getTimeMillis();
      if (now > this.nextExpiryTime()) {
         Iterable execsToInclude = ((MapOps)this.executorIdToExcludedStatus().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$applyExcludeOnFailureTimeout$1(now, x$1)))).keys();
         if (execsToInclude.nonEmpty()) {
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing executors ", " from "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, execsToInclude)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"exclude list because the executors have reached the timed out"})))).log(scala.collection.immutable.Nil..MODULE$))));
            execsToInclude.foreach((exec) -> {
               ExcludedExecutor status = (ExcludedExecutor)this.executorIdToExcludedStatus().remove(exec).get();
               HashSet failedExecsOnNode = (HashSet)this.nodeToExcludedExecs().apply(status.node());
               this.listenerBus().post(new SparkListenerExecutorUnblacklisted(now, exec));
               this.listenerBus().post(new SparkListenerExecutorUnexcluded(now, exec));
               failedExecsOnNode.remove(exec);
               return failedExecsOnNode.isEmpty() ? this.nodeToExcludedExecs().remove(status.node()) : BoxedUnit.UNIT;
            });
         }

         Iterable nodesToInclude = ((MapOps)this.nodeIdToExcludedExpiryTime().filter((x$2) -> BoxesRunTime.boxToBoolean($anonfun$applyExcludeOnFailureTimeout$4(now, x$2)))).keys();
         if (nodesToInclude.nonEmpty()) {
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Removing nodes ", " from exclude list because the "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NODES..MODULE$, nodesToInclude)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"nodes have reached has timed out"})))).log(scala.collection.immutable.Nil..MODULE$))));
            nodesToInclude.foreach((node) -> {
               $anonfun$applyExcludeOnFailureTimeout$6(this, now, node);
               return BoxedUnit.UNIT;
            });
            this._excludedNodeList().set(this.nodeIdToExcludedExpiryTime().keySet().toSet());
         }

         this.updateNextExpiryTime();
      }
   }

   private void updateNextExpiryTime() {
      long execMinExpiry = this.executorIdToExcludedStatus().nonEmpty() ? BoxesRunTime.unboxToLong(((IterableOnceOps)this.executorIdToExcludedStatus().map((x$3) -> BoxesRunTime.boxToLong($anonfun$updateNextExpiryTime$1(x$3)))).min(scala.math.Ordering.Long..MODULE$)) : Long.MAX_VALUE;
      long nodeMinExpiry = this.nodeIdToExcludedExpiryTime().nonEmpty() ? BoxesRunTime.unboxToLong(this.nodeIdToExcludedExpiryTime().values().min(scala.math.Ordering.Long..MODULE$)) : Long.MAX_VALUE;
      this.nextExpiryTime_$eq(scala.math.package..MODULE$.min(execMinExpiry, nodeMinExpiry));
   }

   private void killExecutor(final String exec, final String msg) {
      String fullMsg = this.EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED() ? msg + " (actually decommissioning)" : msg;
      Option var5 = this.allocationClient;
      if (var5 instanceof Some var6) {
         ExecutorAllocationClient a = (ExecutorAllocationClient)var6.value();
         this.logInfo((Function0)(() -> fullMsg));
         if (this.EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED()) {
            a.decommissionExecutor(exec, new ExecutorDecommissionInfo(fullMsg, ExecutorDecommissionInfo$.MODULE$.apply$default$2()), false, a.decommissionExecutor$default$4());
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            a.killExecutors(new scala.collection.immutable..colon.colon(exec, scala.collection.immutable.Nil..MODULE$), false, false, true);
            BoxedUnit var8 = BoxedUnit.UNIT;
         }
      } else if (scala.None..MODULE$.equals(var5)) {
         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not attempting to kill excluded executor id ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, exec)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" since allocation client is not defined."})))).log(scala.collection.immutable.Nil..MODULE$))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var5);
      }
   }

   private void killExcludedExecutor(final String exec) {
      if (BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_KILL_ENABLED()))) {
         this.killExecutor(exec, "Killing excluded executor id " + exec + " since " + org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_KILL_ENABLED().key() + " is set.");
      }
   }

   public void killExcludedIdleExecutor(final String exec) {
      this.killExecutor(exec, "Killing excluded idle executor id " + exec + " because of task unschedulability and trying to acquire a new executor.");
   }

   private void killExecutorsOnExcludedNode(final String node) {
      if (BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_KILL_ENABLED()))) {
         Option var3 = this.allocationClient;
         if (var3 instanceof Some) {
            Some var4 = (Some)var3;
            ExecutorAllocationClient a = (ExecutorAllocationClient)var4.value();
            if (this.EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED()) {
               this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommissioning all executors on excluded host ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, node)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"since ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_KILL_ENABLED().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is set."})))).log(scala.collection.immutable.Nil..MODULE$))));
               if (!a.decommissionExecutorsOnHost(node)) {
                  this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Decommissioning executors on ", " failed."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, node)})))));
                  BoxedUnit var9 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var8 = BoxedUnit.UNIT;
               }
            } else {
               this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killing all executors on excluded host ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, node)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"since ", " is set."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_KILL_ENABLED().key())}))))));
               if (!a.killExecutorsOnHost(node)) {
                  this.logError(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Killing executors on node ", " failed."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, node)})))));
                  BoxedUnit var7 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var6 = BoxedUnit.UNIT;
               }
            }
         } else if (scala.None..MODULE$.equals(var3)) {
            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Not attempting to kill executors on excluded host ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST_PORT..MODULE$, node)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"since allocation client is not defined."})))).log(scala.collection.immutable.Nil..MODULE$))));
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(var3);
         }
      }
   }

   public void updateExcludedForFetchFailure(final String host, final String exec) {
      if (this.EXCLUDE_FETCH_FAILURE_ENABLED()) {
         long now = this.clock.getTimeMillis();
         long expiryTimeForNewExcludes = now + this.EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS();
         if (BoxesRunTime.unboxToBoolean(this.conf.get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_SERVICE_ENABLED()))) {
            if (!this.nodeIdToExcludedExpiryTime().contains(host)) {
               this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"excluding node ", " due to fetch failure of "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, host)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"external shuffle service"})))).log(scala.collection.immutable.Nil..MODULE$))));
               this.nodeIdToExcludedExpiryTime().put(host, BoxesRunTime.boxToLong(expiryTimeForNewExcludes));
               this.listenerBus().post(new SparkListenerNodeBlacklisted(now, host, 1));
               this.listenerBus().post(new SparkListenerNodeExcluded(now, host, 1));
               this._excludedNodeList().set(this.nodeIdToExcludedExpiryTime().keySet().toSet());
               this.killExecutorsOnExcludedNode(host);
               this.updateNextExpiryTime();
            }
         } else if (!this.executorIdToExcludedStatus().contains(exec)) {
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Excluding executor ", " due to fetch failure"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, exec)})))));
            this.executorIdToExcludedStatus().put(exec, new ExcludedExecutor(host, expiryTimeForNewExcludes));
            this.listenerBus().post(new SparkListenerExecutorBlacklisted(now, exec, 1));
            this.listenerBus().post(new SparkListenerExecutorExcluded(now, exec, 1));
            this.updateNextExpiryTime();
            this.killExcludedExecutor(exec);
            HashSet excludedExecsOnNode = (HashSet)this.nodeToExcludedExecs().getOrElseUpdate(host, () -> (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
            excludedExecsOnNode.$plus$eq(exec);
         }
      }
   }

   public void updateExcludedForSuccessfulTaskSet(final int stageId, final int stageAttemptId, final HashMap failuresByExec) {
      long now = this.clock.getTimeMillis();
      failuresByExec.foreach((x0$1) -> {
         $anonfun$updateExcludedForSuccessfulTaskSet$1(this, stageId, stageAttemptId, now, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public boolean isExecutorExcluded(final String executorId) {
      return this.executorIdToExcludedStatus().contains(executorId);
   }

   public Set excludedNodeList() {
      return (Set)this._excludedNodeList().get();
   }

   public boolean isNodeExcluded(final String node) {
      return this.nodeIdToExcludedExpiryTime().contains(node);
   }

   public void handleRemovedExecutor(final String executorId) {
      this.executorIdToFailureList().$minus$eq(executorId);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$applyExcludeOnFailureTimeout$1(final long now$1, final Tuple2 x$1) {
      return ((ExcludedExecutor)x$1._2()).expiryTime() < now$1;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$applyExcludeOnFailureTimeout$4(final long now$1, final Tuple2 x$2) {
      return x$2._2$mcJ$sp() < now$1;
   }

   // $FF: synthetic method
   public static final void $anonfun$applyExcludeOnFailureTimeout$6(final HealthTracker $this, final long now$1, final String node) {
      $this.nodeIdToExcludedExpiryTime().remove(node);
      $this.listenerBus().post(new SparkListenerNodeUnblacklisted(now$1, node));
      $this.listenerBus().post(new SparkListenerNodeUnexcluded(now$1, node));
   }

   // $FF: synthetic method
   public static final long $anonfun$updateNextExpiryTime$1(final Tuple2 x$3) {
      return ((ExcludedExecutor)x$3._2()).expiryTime();
   }

   // $FF: synthetic method
   public static final void $anonfun$updateExcludedForSuccessfulTaskSet$1(final HealthTracker $this, final int stageId$1, final int stageAttemptId$1, final long now$2, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String exec = (String)x0$1._1();
         ExecutorFailuresInTaskSet failuresInTaskSet = (ExecutorFailuresInTaskSet)x0$1._2();
         ExecutorFailureList appFailuresOnExecutor = (ExecutorFailureList)$this.executorIdToFailureList().getOrElseUpdate(exec, () -> $this.new ExecutorFailureList());
         appFailuresOnExecutor.addFailures(stageId$1, stageAttemptId$1, failuresInTaskSet);
         appFailuresOnExecutor.dropFailuresWithTimeoutBefore(now$2);
         int newTotal = appFailuresOnExecutor.numUniqueTaskFailures();
         long expiryTimeForNewExcludes = now$2 + $this.EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS();
         if (newTotal >= $this.MAX_FAILURES_PER_EXEC() && !$this.executorIdToExcludedStatus().contains(exec)) {
            $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Excluding executor id: ", " because it has "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_ID..MODULE$, exec)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " task failures in successful task sets"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL..MODULE$, BoxesRunTime.boxToInteger(newTotal))}))))));
            String node = failuresInTaskSet.node();
            $this.executorIdToExcludedStatus().put(exec, new ExcludedExecutor(node, expiryTimeForNewExcludes));
            $this.listenerBus().post(new SparkListenerExecutorBlacklisted(now$2, exec, newTotal));
            $this.listenerBus().post(new SparkListenerExecutorExcluded(now$2, exec, newTotal));
            $this.executorIdToFailureList().remove(exec);
            $this.updateNextExpiryTime();
            $this.killExcludedExecutor(exec);
            HashSet excludedExecsOnNode = (HashSet)$this.nodeToExcludedExecs().getOrElseUpdate(node, () -> (HashSet)scala.collection.mutable.HashSet..MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
            excludedExecsOnNode.$plus$eq(exec);
            if (excludedExecsOnNode.size() >= $this.MAX_FAILED_EXEC_PER_NODE() && !$this.nodeIdToExcludedExpiryTime().contains(node)) {
               $this.logInfo(.MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Excluding node ", " because it has "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.HOST..MODULE$, node)}))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " executors "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTORS..MODULE$, BoxesRunTime.boxToInteger(excludedExecsOnNode.size()))})))).$plus($this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"excluded: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.EXECUTOR_IDS..MODULE$, excludedExecsOnNode)}))))));
               $this.nodeIdToExcludedExpiryTime().put(node, BoxesRunTime.boxToLong(expiryTimeForNewExcludes));
               $this.listenerBus().post(new SparkListenerNodeBlacklisted(now$2, node, excludedExecsOnNode.size()));
               $this.listenerBus().post(new SparkListenerNodeExcluded(now$2, node, excludedExecsOnNode.size()));
               $this._excludedNodeList().set($this.nodeIdToExcludedExpiryTime().keySet().toSet());
               $this.killExecutorsOnExcludedNode(node);
               BoxedUnit var17 = BoxedUnit.UNIT;
            } else {
               BoxedUnit var16 = BoxedUnit.UNIT;
            }
         } else {
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }
      } else {
         throw new MatchError(x0$1);
      }
   }

   public HealthTracker(final LiveListenerBus listenerBus, final SparkConf conf, final Option allocationClient, final Clock clock) {
      this.listenerBus = listenerBus;
      this.conf = conf;
      this.allocationClient = allocationClient;
      this.clock = clock;
      Logging.$init$(this);
      HealthTracker$.MODULE$.validateExcludeOnFailureConfs(conf);
      this.MAX_FAILURES_PER_EXEC = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILURES_PER_EXEC()));
      this.MAX_FAILED_EXEC_PER_NODE = BoxesRunTime.unboxToInt(conf.get(org.apache.spark.internal.config.package$.MODULE$.MAX_FAILED_EXEC_PER_NODE()));
      this.EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS = HealthTracker$.MODULE$.getExcludeOnFailureTimeout(conf);
      this.EXCLUDE_FETCH_FAILURE_ENABLED = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_FETCH_FAILURE_ENABLED()));
      this.EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED = BoxesRunTime.unboxToBoolean(conf.get(org.apache.spark.internal.config.package$.MODULE$.EXCLUDE_ON_FAILURE_DECOMMISSION_ENABLED()));
      this.executorIdToFailureList = new HashMap();
      this.executorIdToExcludedStatus = new HashMap();
      this.nodeIdToExcludedExpiryTime = new HashMap();
      this._excludedNodeList = new AtomicReference(scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$));
      this.nextExpiryTime = Long.MAX_VALUE;
      this.nodeToExcludedExecs = new HashMap();
   }

   public HealthTracker(final SparkContext sc, final Option allocationClient) {
      this(sc.listenerBus(), sc.conf(), allocationClient, HealthTracker$.MODULE$.$lessinit$greater$default$4());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public final class ExecutorFailureList implements Logging {
      private volatile TaskId$ TaskId$module;
      private ArrayBuffer failuresAndExpiryTimes;
      private long minExpiryTime;
      private transient Logger org$apache$spark$internal$Logging$$log_;
      // $FF: synthetic field
      private final HealthTracker $outer;

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

      private TaskId$ TaskId() {
         if (this.TaskId$module == null) {
            this.TaskId$lzycompute$1();
         }

         return this.TaskId$module;
      }

      public Logger org$apache$spark$internal$Logging$$log_() {
         return this.org$apache$spark$internal$Logging$$log_;
      }

      public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
         this.org$apache$spark$internal$Logging$$log_ = x$1;
      }

      private ArrayBuffer failuresAndExpiryTimes() {
         return this.failuresAndExpiryTimes;
      }

      private void failuresAndExpiryTimes_$eq(final ArrayBuffer x$1) {
         this.failuresAndExpiryTimes = x$1;
      }

      private long minExpiryTime() {
         return this.minExpiryTime;
      }

      private void minExpiryTime_$eq(final long x$1) {
         this.minExpiryTime = x$1;
      }

      public void addFailures(final int stage, final int stageAttempt, final ExecutorFailuresInTaskSet failuresInTaskSet) {
         failuresInTaskSet.taskToFailureCountAndFailureTime().foreach((x0$1) -> {
            $anonfun$addFailures$1(this, stage, stageAttempt, x0$1);
            return BoxedUnit.UNIT;
         });
      }

      public int numUniqueTaskFailures() {
         return this.failuresAndExpiryTimes().size();
      }

      public boolean isEmpty() {
         return this.failuresAndExpiryTimes().isEmpty();
      }

      public void dropFailuresWithTimeoutBefore(final long dropBefore) {
         if (this.minExpiryTime() < dropBefore) {
            LongRef newMinExpiry = LongRef.create(Long.MAX_VALUE);
            ArrayBuffer newFailures = new ArrayBuffer();
            this.failuresAndExpiryTimes().foreach((x0$1) -> {
               $anonfun$dropFailuresWithTimeoutBefore$1(dropBefore, newFailures, newMinExpiry, x0$1);
               return BoxedUnit.UNIT;
            });
            this.failuresAndExpiryTimes_$eq(newFailures);
            this.minExpiryTime_$eq(newMinExpiry.elem);
         }
      }

      public String toString() {
         return "failures = " + this.failuresAndExpiryTimes();
      }

      private final void TaskId$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.TaskId$module == null) {
               this.TaskId$module = new TaskId$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$addFailures$1(final ExecutorFailureList $this, final int stage$1, final int stageAttempt$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            int taskIdx = x0$1._1$mcI$sp();
            Tuple2 var7 = (Tuple2)x0$1._2();
            if (var7 != null) {
               long failureTime = var7._2$mcJ$sp();
               long expiryTime = failureTime + $this.$outer.EXCLUDE_ON_FAILURE_TIMEOUT_MILLIS();
               $this.failuresAndExpiryTimes().$plus$eq(new Tuple2($this.new TaskId(stage$1, stageAttempt$1, taskIdx), BoxesRunTime.boxToLong(expiryTime)));
               if (expiryTime < $this.minExpiryTime()) {
                  $this.minExpiryTime_$eq(expiryTime);
                  BoxedUnit var12 = BoxedUnit.UNIT;
                  return;
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
               return;
            }
         }

         throw new MatchError(x0$1);
      }

      // $FF: synthetic method
      public static final void $anonfun$dropFailuresWithTimeoutBefore$1(final long dropBefore$1, final ArrayBuffer newFailures$1, final LongRef newMinExpiry$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            TaskId task = (TaskId)x0$1._1();
            long expiryTime = x0$1._2$mcJ$sp();
            if (expiryTime >= dropBefore$1) {
               newFailures$1.$plus$eq(new Tuple2(task, BoxesRunTime.boxToLong(expiryTime)));
               if (expiryTime < newMinExpiry$1.elem) {
                  newMinExpiry$1.elem = expiryTime;
                  BoxedUnit var11 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         } else {
            throw new MatchError(x0$1);
         }
      }

      public ExecutorFailureList() {
         if (HealthTracker.this == null) {
            throw null;
         } else {
            this.$outer = HealthTracker.this;
            super();
            Logging.$init$(this);
            this.failuresAndExpiryTimes = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.minExpiryTime = Long.MAX_VALUE;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class TaskId implements Product, Serializable {
         private final int stage;
         private final int stageAttempt;
         private final int taskIndex;
         // $FF: synthetic field
         public final ExecutorFailureList $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public int stage() {
            return this.stage;
         }

         public int stageAttempt() {
            return this.stageAttempt;
         }

         public int taskIndex() {
            return this.taskIndex;
         }

         public TaskId copy(final int stage, final int stageAttempt, final int taskIndex) {
            return this.org$apache$spark$scheduler$HealthTracker$ExecutorFailureList$TaskId$$$outer().new TaskId(stage, stageAttempt, taskIndex);
         }

         public int copy$default$1() {
            return this.stage();
         }

         public int copy$default$2() {
            return this.stageAttempt();
         }

         public int copy$default$3() {
            return this.taskIndex();
         }

         public String productPrefix() {
            return "TaskId";
         }

         public int productArity() {
            return 3;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return BoxesRunTime.boxToInteger(this.stage());
               }
               case 1 -> {
                  return BoxesRunTime.boxToInteger(this.stageAttempt());
               }
               case 2 -> {
                  return BoxesRunTime.boxToInteger(this.taskIndex());
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
            return x$1 instanceof TaskId;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "stage";
               }
               case 1 -> {
                  return "stageAttempt";
               }
               case 2 -> {
                  return "taskIndex";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            int var1 = -889275714;
            var1 = Statics.mix(var1, this.productPrefix().hashCode());
            var1 = Statics.mix(var1, this.stage());
            var1 = Statics.mix(var1, this.stageAttempt());
            var1 = Statics.mix(var1, this.taskIndex());
            return Statics.finalizeHash(var1, 3);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label45: {
                  if (x$1 instanceof TaskId && ((TaskId)x$1).org$apache$spark$scheduler$HealthTracker$ExecutorFailureList$TaskId$$$outer() == this.org$apache$spark$scheduler$HealthTracker$ExecutorFailureList$TaskId$$$outer()) {
                     TaskId var4 = (TaskId)x$1;
                     if (this.stage() == var4.stage() && this.stageAttempt() == var4.stageAttempt() && this.taskIndex() == var4.taskIndex() && var4.canEqual(this)) {
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
         public ExecutorFailureList org$apache$spark$scheduler$HealthTracker$ExecutorFailureList$TaskId$$$outer() {
            return this.$outer;
         }

         public TaskId(final int stage, final int stageAttempt, final int taskIndex) {
            this.stage = stage;
            this.stageAttempt = stageAttempt;
            this.taskIndex = taskIndex;
            if (ExecutorFailureList.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorFailureList.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class TaskId$ extends AbstractFunction3 implements Serializable {
         // $FF: synthetic field
         private final ExecutorFailureList $outer;

         public final String toString() {
            return "TaskId";
         }

         public TaskId apply(final int stage, final int stageAttempt, final int taskIndex) {
            return this.$outer.new TaskId(stage, stageAttempt, taskIndex);
         }

         public Option unapply(final TaskId x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToInteger(x$0.stage()), BoxesRunTime.boxToInteger(x$0.stageAttempt()), BoxesRunTime.boxToInteger(x$0.taskIndex()))));
         }

         public TaskId$() {
            if (ExecutorFailureList.this == null) {
               throw null;
            } else {
               this.$outer = ExecutorFailureList.this;
               super();
            }
         }
      }
   }
}
