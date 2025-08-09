package org.apache.spark.streaming.receiver;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.LogEntry.;
import org.apache.spark.storage.StreamBlockId;
import org.apache.spark.streaming.StreamingConf$;
import org.apache.spark.streaming.util.RecurringTimer;
import org.apache.spark.util.Clock;
import scala.Enumeration;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\rEa!B&M\u000193\u0006\u0002C1\u0001\u0005\u0003\u0005\u000b\u0011B2\t\u0011\u0019\u0004!\u0011!Q\u0001\n\u001dD\u0001\"\u001c\u0001\u0003\u0002\u0003\u0006IA\u001c\u0005\te\u0002\u0011\t\u0011)A\u0005g\")\u0011\u0010\u0001C\u0001u\u001a1\u0011\u0011\u0001\u0001E\u0003\u0007A!\"!\u000b\u0007\u0005+\u0007I\u0011AA\u0016\u0011)\tID\u0002B\tB\u0003%\u0011Q\u0006\u0005\u000b\u0003w1!Q3A\u0005\u0002\u0005u\u0002BCA+\r\tE\t\u0015!\u0003\u0002@!1\u0011P\u0002C\u0001\u0003/B\u0011\"!\u0019\u0007\u0003\u0003%\t!a\u0019\t\u0013\u0005%d!%A\u0005\u0002\u0005-\u0004\"CAA\rE\u0005I\u0011AAB\u0011%\t9IBA\u0001\n\u0003\nI\tC\u0005\u0002\u001c\u001a\t\t\u0011\"\u0001\u0002\u001e\"I\u0011q\u0014\u0004\u0002\u0002\u0013\u0005\u0011\u0011\u0015\u0005\n\u0003O3\u0011\u0011!C!\u0003SC\u0011\"a-\u0007\u0003\u0003%\t!!.\t\u0013\u0005}f!!A\u0005B\u0005\u0005\u0007\"CAc\r\u0005\u0005I\u0011IAd\u0011%\tIMBA\u0001\n\u0003\nY\rC\u0005\u0002N\u001a\t\t\u0011\"\u0011\u0002P\u001eI\u00111\u001b\u0001\u0002\u0002#%\u0011Q\u001b\u0004\n\u0003\u0003\u0001\u0011\u0011!E\u0005\u0003/Da!_\r\u0005\u0002\u0005=\b\"CAe3\u0005\u0005IQIAf\u0011%\t\t0GA\u0001\n\u0003\u000b\u0019\u0010C\u0005\u0002zf\t\t\u0011\"!\u0002|\u001e9!Q\u0002\u0001\t\n\t=aa\u0002B\t\u0001!%!1\u0003\u0005\u0007s~!\tAa\u0007\u0006\r\tEq\u0004\u0001B\u000f\u0011%\u0011)c\bb\u0001\n\u0003\u00119\u0003\u0003\u0005\u0003*}\u0001\u000b\u0011\u0002B\u000f\u0011%\u0011Yc\bb\u0001\n\u0003\u00119\u0003\u0003\u0005\u0003.}\u0001\u000b\u0011\u0002B\u000f\u0011%\u0011yc\bb\u0001\n\u0003\u00119\u0003\u0003\u0005\u00032}\u0001\u000b\u0011\u0002B\u000f\u0011%\u0011\u0019d\bb\u0001\n\u0003\u00119\u0003\u0003\u0005\u00036}\u0001\u000b\u0011\u0002B\u000f\u0011%\u00119d\bb\u0001\n\u0003\u00119\u0003\u0003\u0005\u0003:}\u0001\u000b\u0011\u0002B\u000f\u0011%\u0011Y\u0004\u0001b\u0001\n\u0013\u0011i\u0004\u0003\u0005\u0003F\u0001\u0001\u000b\u0011\u0002B \u0011%\u00119\u0005\u0001b\u0001\n\u0013\u0011I\u0005\u0003\u0005\u0003V\u0001\u0001\u000b\u0011\u0002B&\u0011%\u00119\u0006\u0001b\u0001\n\u0013\ti\nC\u0004\u0003Z\u0001\u0001\u000b\u0011B4\t\u0013\tm\u0003A1A\u0005\n\tu\u0003\u0002\u0003B7\u0001\u0001\u0006IAa\u0018\t\u0013\t=\u0004A1A\u0005\n\tE\u0004\u0002\u0003B=\u0001\u0001\u0006IAa\u001d\t\u0013\tm\u0004\u00011A\u0005\n\u0005u\u0002\"\u0003B?\u0001\u0001\u0007I\u0011\u0002B@\u0011!\u0011I\t\u0001Q!\n\u0005}\u0002\"\u0003BJ\u0001\u0001\u0007I\u0011\u0002BK\u0011%\u0011Y\n\u0001a\u0001\n\u0013\u0011i\n\u0003\u0005\u0003\"\u0002\u0001\u000b\u0015\u0002BL\u0011\u001d\u0011)\u000b\u0001C\u0001\u0005OCqA!+\u0001\t\u0003\u00119\u000bC\u0004\u0003,\u0002!\tA!,\t\u000f\tM\u0006\u0001\"\u0001\u00036\"9!Q\u0018\u0001\u0005\u0002\t}\u0006b\u0002Bf\u0001\u0011\u0005!Q\u001a\u0005\b\u0005\u001f\u0004A\u0011\u0001Bg\u0011\u001d\u0011\t\u000e\u0001C\u0005\u0005'DqA!7\u0001\t\u0013\u00119\u000bC\u0004\u0003\\\u0002!IA!8\t\u000f\tm\b\u0001\"\u0003\u0003~\u001eQ11\u0001'\u0002\u0002#\u0005aj!\u0002\u0007\u0013-c\u0015\u0011!E\u0001\u001d\u000e\u001d\u0001BB=I\t\u0003\u0019I\u0001C\u0005\u0004\f!\u000b\n\u0011\"\u0001\u0004\u000e\tq!\t\\8dW\u001e+g.\u001a:bi>\u0014(BA'O\u0003!\u0011XmY3jm\u0016\u0014(BA(Q\u0003%\u0019HO]3b[&twM\u0003\u0002R%\u0006)1\u000f]1sW*\u00111\u000bV\u0001\u0007CB\f7\r[3\u000b\u0003U\u000b1a\u001c:h'\r\u0001qk\u0017\t\u00031fk\u0011\u0001T\u0005\u000352\u00131BU1uK2KW.\u001b;feB\u0011AlX\u0007\u0002;*\u0011a\fU\u0001\tS:$XM\u001d8bY&\u0011\u0001-\u0018\u0002\b\u0019><w-\u001b8h\u0003!a\u0017n\u001d;f]\u0016\u00148\u0001\u0001\t\u00031\u0012L!!\u001a'\u0003-\tcwnY6HK:,'/\u0019;pe2K7\u000f^3oKJ\f!B]3dK&4XM]%e!\tA7.D\u0001j\u0015\u0005Q\u0017!B:dC2\f\u0017B\u00017j\u0005\rIe\u000e^\u0001\u0005G>tg\r\u0005\u0002pa6\t\u0001+\u0003\u0002r!\nI1\u000b]1sW\u000e{gNZ\u0001\u0006G2|7m\u001b\t\u0003i^l\u0011!\u001e\u0006\u0003mB\u000bA!\u001e;jY&\u0011\u00010\u001e\u0002\u0006\u00072|7m[\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bmdXP`@\u0011\u0005a\u0003\u0001\"B1\u0006\u0001\u0004\u0019\u0007\"\u00024\u0006\u0001\u00049\u0007\"B7\u0006\u0001\u0004q\u0007b\u0002:\u0006!\u0003\u0005\ra\u001d\u0002\u0006\u00052|7m[\n\b\r\u0005\u0015\u00111BA\t!\rA\u0017qA\u0005\u0004\u0003\u0013I'AB!osJ+g\rE\u0002i\u0003\u001bI1!a\u0004j\u0005\u001d\u0001&o\u001c3vGR\u0004B!a\u0005\u0002$9!\u0011QCA\u0010\u001d\u0011\t9\"!\b\u000e\u0005\u0005e!bAA\u000eE\u00061AH]8pizJ\u0011A[\u0005\u0004\u0003CI\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003K\t9C\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\"%\f!!\u001b3\u0016\u0005\u00055\u0002\u0003BA\u0018\u0003ki!!!\r\u000b\u0007\u0005M\u0002+A\u0004ti>\u0014\u0018mZ3\n\t\u0005]\u0012\u0011\u0007\u0002\u000e'R\u0014X-Y7CY>\u001c7.\u00133\u0002\u0007%$\u0007%\u0001\u0004ck\u001a4WM]\u000b\u0003\u0003\u007f\u0001b!!\u0011\u0002L\u0005=SBAA\"\u0015\u0011\t)%a\u0012\u0002\u000f5,H/\u00192mK*\u0019\u0011\u0011J5\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0002N\u0005\r#aC!se\u0006L()\u001e4gKJ\u00042\u0001[A)\u0013\r\t\u0019&\u001b\u0002\u0004\u0003:L\u0018a\u00022vM\u001a,'\u000f\t\u000b\u0007\u00033\ni&a\u0018\u0011\u0007\u0005mc!D\u0001\u0001\u0011\u001d\tIc\u0003a\u0001\u0003[Aq!a\u000f\f\u0001\u0004\ty$\u0001\u0003d_BLHCBA-\u0003K\n9\u0007C\u0005\u0002*1\u0001\n\u00111\u0001\u0002.!I\u00111\b\u0007\u0011\u0002\u0003\u0007\u0011qH\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\tiG\u000b\u0003\u0002.\u0005=4FAA9!\u0011\t\u0019(! \u000e\u0005\u0005U$\u0002BA<\u0003s\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005m\u0014.\u0001\u0006b]:|G/\u0019;j_:LA!a \u0002v\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u0011Q\u0011\u0016\u0005\u0003\u007f\ty'A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003\u0017\u0003B!!$\u0002\u00186\u0011\u0011q\u0012\u0006\u0005\u0003#\u000b\u0019*\u0001\u0003mC:<'BAAK\u0003\u0011Q\u0017M^1\n\t\u0005e\u0015q\u0012\u0002\u0007'R\u0014\u0018N\\4\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003\u001d\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002P\u0005\r\u0006\u0002CAS#\u0005\u0005\t\u0019A4\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\tY\u000b\u0005\u0004\u0002.\u0006=\u0016qJ\u0007\u0003\u0003\u000fJA!!-\u0002H\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t9,!0\u0011\u0007!\fI,C\u0002\u0002<&\u0014qAQ8pY\u0016\fg\u000eC\u0005\u0002&N\t\t\u00111\u0001\u0002P\u0005\u0011\u0002O]8ek\u000e$X\t\\3nK:$h*Y7f)\u0011\tY)a1\t\u0011\u0005\u0015F#!AA\u0002\u001d\f\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002O\u0006AAo\\*ue&tw\r\u0006\u0002\u0002\f\u00061Q-];bYN$B!a.\u0002R\"I\u0011QU\f\u0002\u0002\u0003\u0007\u0011qJ\u0001\u0006\u00052|7m\u001b\t\u0004\u00037J2#B\r\u0002Z\u0006\u0015\bCCAn\u0003C\fi#a\u0010\u0002Z5\u0011\u0011Q\u001c\u0006\u0004\u0003?L\u0017a\u0002:v]RLW.Z\u0005\u0005\u0003G\fiNA\tBEN$(/Y2u\rVt7\r^5p]J\u0002B!a:\u0002n6\u0011\u0011\u0011\u001e\u0006\u0005\u0003W\f\u0019*\u0001\u0002j_&!\u0011QEAu)\t\t).A\u0003baBd\u0017\u0010\u0006\u0004\u0002Z\u0005U\u0018q\u001f\u0005\b\u0003Sa\u0002\u0019AA\u0017\u0011\u001d\tY\u0004\ba\u0001\u0003\u007f\tq!\u001e8baBd\u0017\u0010\u0006\u0003\u0002~\n%\u0001#\u00025\u0002\u0000\n\r\u0011b\u0001B\u0001S\n1q\n\u001d;j_:\u0004r\u0001\u001bB\u0003\u0003[\ty$C\u0002\u0003\b%\u0014a\u0001V;qY\u0016\u0014\u0004\"\u0003B\u0006;\u0005\u0005\t\u0019AA-\u0003\rAH\u0005M\u0001\u000f\u000f\u0016tWM]1u_J\u001cF/\u0019;f!\r\tYf\b\u0002\u000f\u000f\u0016tWM]1u_J\u001cF/\u0019;f'\ry\"Q\u0003\t\u0004Q\n]\u0011b\u0001B\rS\nYQI\\;nKJ\fG/[8o)\t\u0011y\u0001\u0005\u0003\u0003 \t\u0005R\"A\u0010\n\t\t\r\"q\u0003\u0002\u0006-\u0006dW/Z\u0001\f\u0013:LG/[1mSj,G-\u0006\u0002\u0003\u001e\u0005a\u0011J\\5uS\u0006d\u0017N_3eA\u00051\u0011i\u0019;jm\u0016\fq!Q2uSZ,\u0007%A\tTi>\u0004\b/\u001a3BI\u0012Lgn\u001a#bi\u0006\f!c\u0015;paB,G-\u00113eS:<G)\u0019;bA\u000592\u000b^8qa\u0016$w)\u001a8fe\u0006$\u0018N\\4CY>\u001c7n]\u0001\u0019'R|\u0007\u000f]3e\u000f\u0016tWM]1uS:<'\t\\8dWN\u0004\u0013AC*u_B\u0004X\rZ!mY\u0006Y1\u000b^8qa\u0016$\u0017\t\u001c7!\u0003=\u0011Gn\\2l\u0013:$XM\u001d<bY6\u001bXC\u0001B !\rA'\u0011I\u0005\u0004\u0005\u0007J'\u0001\u0002'p]\u001e\f\u0001C\u00197pG.Le\u000e^3sm\u0006dWj\u001d\u0011\u0002%\tdwnY6J]R,'O^1m)&lWM]\u000b\u0003\u0005\u0017\u0002BA!\u0014\u0003R5\u0011!q\n\u0006\u0003m:KAAa\u0015\u0003P\tq!+Z2veJLgn\u001a+j[\u0016\u0014\u0018a\u00052m_\u000e\\\u0017J\u001c;feZ\fG\u000eV5nKJ\u0004\u0013A\u00042m_\u000e\\\u0017+^3vKNK'0Z\u0001\u0010E2|7m[)vKV,7+\u001b>fA\u0005\u0001\"\r\\8dWN4uN\u001d)vg\"LgnZ\u000b\u0003\u0005?\u0002bA!\u0019\u0003j\u0005eSB\u0001B2\u0015\u0011\u0011)Ga\u001a\u0002\u0015\r|gnY;se\u0016tGOC\u0002w\u0003'KAAa\u001b\u0003d\t\u0011\u0012I\u001d:bs\ncwnY6j]\u001e\fV/Z;f\u0003E\u0011Gn\\2lg\u001a{'\u000fU;tQ&tw\rI\u0001\u0013E2|7m\u001b)vg\"Lgn\u001a+ie\u0016\fG-\u0006\u0002\u0003tA!\u0011Q\u0012B;\u0013\u0011\u00119(a$\u0003\rQC'/Z1e\u0003M\u0011Gn\\2l!V\u001c\b.\u001b8h)\"\u0014X-\u00193!\u00035\u0019WO\u001d:f]R\u0014UO\u001a4fe\u0006\t2-\u001e:sK:$()\u001e4gKJ|F%Z9\u0015\t\t\u0005%q\u0011\t\u0004Q\n\r\u0015b\u0001BCS\n!QK\\5u\u0011%\t)kNA\u0001\u0002\u0004\ty$\u0001\bdkJ\u0014XM\u001c;Ck\u001a4WM\u001d\u0011)\u0007a\u0012i\tE\u0002i\u0005\u001fK1A!%j\u0005!1x\u000e\\1uS2,\u0017!B:uCR,WC\u0001BL!\u0011\u0011IJ!\t\u000f\u0007\u0005mc$A\u0005ti\u0006$Xm\u0018\u0013fcR!!\u0011\u0011BP\u0011%\t)KOA\u0001\u0002\u0004\u00119*\u0001\u0004ti\u0006$X\r\t\u0015\u0004w\t5\u0015!B:uCJ$HC\u0001BA\u0003\u0011\u0019Ho\u001c9\u0002\u000f\u0005$G\rR1uCR!!\u0011\u0011BX\u0011\u001d\u0011\tL\u0010a\u0001\u0003\u001f\nA\u0001Z1uC\u0006\u0019\u0012\r\u001a3ECR\fw+\u001b;i\u0007\u0006dGNY1dWR1!\u0011\u0011B\\\u0005sCqA!-@\u0001\u0004\ty\u0005C\u0004\u0003<~\u0002\r!a\u0014\u0002\u00115,G/\u00193bi\u0006\f1$\u00193e\u001bVdG/\u001b9mK\u0012\u000bG/Y,ji\"\u001c\u0015\r\u001c7cC\u000e\\GC\u0002BA\u0005\u0003\u0014I\rC\u0004\u0003D\u0002\u0003\rA!2\u0002\u0019\u0011\fG/Y%uKJ\fGo\u001c:\u0011\r\u0005M!qYA(\u0013\u0011\t\t,a\n\t\u000f\tm\u0006\t1\u0001\u0002P\u0005A\u0011n]!di&4X\r\u0006\u0002\u00028\u0006I\u0011n]*u_B\u0004X\rZ\u0001\u0014kB$\u0017\r^3DkJ\u0014XM\u001c;Ck\u001a4WM\u001d\u000b\u0005\u0005\u0003\u0013)\u000eC\u0004\u0003X\u000e\u0003\rAa\u0010\u0002\tQLW.Z\u0001\u0012W\u0016,\u0007\u000fU;tQ&twM\u00117pG.\u001c\u0018a\u0003:fa>\u0014H/\u0012:s_J$bA!!\u0003`\nE\bb\u0002Bq\u000b\u0002\u0007!1]\u0001\b[\u0016\u001c8/Y4f!\u0011\u0011)O!<\u000f\t\t\u001d(\u0011\u001e\t\u0004\u0003/I\u0017b\u0001BvS\u00061\u0001K]3eK\u001aLA!!'\u0003p*\u0019!1^5\t\u000f\tMX\t1\u0001\u0003v\u0006\tA\u000f\u0005\u0003\u0002\u0014\t]\u0018\u0002\u0002B}\u0003O\u0011\u0011\u0002\u00165s_^\f'\r\\3\u0002\u0013A,8\u000f\u001b\"m_\u000e\\G\u0003\u0002BA\u0005\u007fDqa!\u0001G\u0001\u0004\tI&A\u0003cY>\u001c7.\u0001\bCY>\u001c7nR3oKJ\fGo\u001c:\u0011\u0005aC5c\u0001%\u0002\u0006Q\u00111QA\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\r=!fA:\u0002p\u0001"
)
public class BlockGenerator extends RateLimiter {
   private volatile Block$ Block$module;
   private volatile GeneratorState$ GeneratorState$module;
   private final BlockGeneratorListener listener;
   private final int receiverId;
   private final long blockIntervalMs;
   private final RecurringTimer blockIntervalTimer;
   private final int blockQueueSize;
   private final ArrayBlockingQueue blocksForPushing;
   private final Thread blockPushingThread;
   private volatile ArrayBuffer currentBuffer;
   private volatile Enumeration.Value state;

   public static Clock $lessinit$greater$default$4() {
      return BlockGenerator$.MODULE$.$lessinit$greater$default$4();
   }

   private Block$ Block() {
      if (this.Block$module == null) {
         this.Block$lzycompute$1();
      }

      return this.Block$module;
   }

   private GeneratorState$ GeneratorState() {
      if (this.GeneratorState$module == null) {
         this.GeneratorState$lzycompute$1();
      }

      return this.GeneratorState$module;
   }

   private long blockIntervalMs() {
      return this.blockIntervalMs;
   }

   private RecurringTimer blockIntervalTimer() {
      return this.blockIntervalTimer;
   }

   private int blockQueueSize() {
      return this.blockQueueSize;
   }

   private ArrayBlockingQueue blocksForPushing() {
      return this.blocksForPushing;
   }

   private Thread blockPushingThread() {
      return this.blockPushingThread;
   }

   private ArrayBuffer currentBuffer() {
      return this.currentBuffer;
   }

   private void currentBuffer_$eq(final ArrayBuffer x$1) {
      this.currentBuffer = x$1;
   }

   private Enumeration.Value state() {
      return this.state;
   }

   private void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public synchronized void start() {
      label14: {
         Enumeration.Value var10000 = this.state();
         Enumeration.Value var1 = this.GeneratorState().Initialized();
         if (var10000 == null) {
            if (var1 == null) {
               break label14;
            }
         } else if (var10000.equals(var1)) {
            break label14;
         }

         throw new SparkException("Cannot start BlockGenerator as its not in the Initialized state [state = " + this.state() + "]");
      }

      this.state_$eq(this.GeneratorState().Active());
      this.blockIntervalTimer().start();
      this.blockPushingThread().start();
      this.logInfo(() -> "Started BlockGenerator");
   }

   public void stop() {
      synchronized(this){}

      try {
         label164: {
            Enumeration.Value var10000 = this.state();
            Enumeration.Value var2 = this.GeneratorState().Active();
            if (var10000 == null) {
               if (var2 == null) {
                  break label164;
               }
            } else if (var10000.equals(var2)) {
               break label164;
            }

            this.logWarning(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cannot stop BlockGenerator as its not in the Active state "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"[state = ", "]"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_GENERATOR_STATUS..MODULE$, this.state())}))))));
            return;
         }

         this.state_$eq(this.GeneratorState().StoppedAddingData());
      } catch (Throwable var16) {
         throw var16;
      }

      this.logInfo(() -> "Stopping BlockGenerator");
      this.blockIntervalTimer().stop(false);
      synchronized(this){}

      try {
         this.state_$eq(this.GeneratorState().StoppedGeneratingBlocks());
      } catch (Throwable var15) {
         throw var15;
      }

      this.logInfo(() -> "Waiting for block pushing thread to terminate");
      this.blockPushingThread().join();
      synchronized(this){}

      try {
         this.state_$eq(this.GeneratorState().StoppedAll());
      } catch (Throwable var14) {
         throw var14;
      }

      this.logInfo(() -> "Stopped BlockGenerator");
   }

   public void addData(final Object data) {
      Enumeration.Value var10000 = this.state();
      Enumeration.Value var2 = this.GeneratorState().Active();
      if (var10000 == null) {
         if (var2 != null) {
            throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
         }
      } else if (!var10000.equals(var2)) {
         throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
      }

      this.waitToPush();
      synchronized(this){}

      try {
         label59: {
            var10000 = this.state();
            Enumeration.Value var4 = this.GeneratorState().Active();
            if (var10000 == null) {
               if (var4 == null) {
                  break label59;
               }
            } else if (var10000.equals(var4)) {
               break label59;
            }

            throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
         }

         ArrayBuffer var8 = (ArrayBuffer)this.currentBuffer().$plus$eq(data);
      } catch (Throwable var6) {
         throw var6;
      }

   }

   public void addDataWithCallback(final Object data, final Object metadata) {
      Enumeration.Value var10000 = this.state();
      Enumeration.Value var3 = this.GeneratorState().Active();
      if (var10000 == null) {
         if (var3 != null) {
            throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
         }
      } else if (!var10000.equals(var3)) {
         throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
      }

      this.waitToPush();
      synchronized(this){}

      try {
         label58: {
            var10000 = this.state();
            Enumeration.Value var5 = this.GeneratorState().Active();
            if (var10000 == null) {
               if (var5 == null) {
                  break label58;
               }
            } else if (var10000.equals(var5)) {
               break label58;
            }

            throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
         }

         this.currentBuffer().$plus$eq(data);
         this.listener.onAddData(data, metadata);
      } catch (Throwable var7) {
         throw var7;
      }

   }

   public void addMultipleDataWithCallback(final Iterator dataIterator, final Object metadata) {
      Enumeration.Value var10000 = this.state();
      Enumeration.Value var3 = this.GeneratorState().Active();
      if (var10000 == null) {
         if (var3 != null) {
            throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
         }
      } else if (!var10000.equals(var3)) {
         throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
      }

      ArrayBuffer tempBuffer = new ArrayBuffer();
      dataIterator.foreach((data) -> {
         this.waitToPush();
         return (ArrayBuffer)tempBuffer.$plus$eq(data);
      });
      synchronized(this){}

      try {
         label58: {
            var10000 = this.state();
            Enumeration.Value var6 = this.GeneratorState().Active();
            if (var10000 == null) {
               if (var6 == null) {
                  break label58;
               }
            } else if (var10000.equals(var6)) {
               break label58;
            }

            throw new SparkException("Cannot add data as BlockGenerator has not been started or has been stopped");
         }

         this.currentBuffer().$plus$plus$eq(tempBuffer);
         this.listener.onAddData(tempBuffer, metadata);
      } catch (Throwable var8) {
         throw var8;
      }

   }

   public boolean isActive() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.state();
         Enumeration.Value var1 = this.GeneratorState().Active();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public boolean isStopped() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.state();
         Enumeration.Value var1 = this.GeneratorState().StoppedAll();
         if (var10000 == null) {
            if (var1 == null) {
               break label23;
            }
         } else if (var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   private void updateCurrentBuffer(final long time) {
      try {
         Block newBlock = null;
         synchronized(this){}

         try {
            if (this.currentBuffer().nonEmpty()) {
               ArrayBuffer newBlockBuffer = this.currentBuffer();
               this.currentBuffer_$eq(new ArrayBuffer());
               StreamBlockId blockId = new StreamBlockId(this.receiverId, time - this.blockIntervalMs());
               this.listener.onGenerateBlock(blockId);
               newBlock = new Block(blockId, newBlockBuffer);
            }
         } catch (Throwable var12) {
            throw var12;
         }

         if (newBlock != null) {
            this.blocksForPushing().put(newBlock);
         }
      } catch (InterruptedException var13) {
         this.logInfo(() -> "Block updating timer thread was interrupted");
      } catch (Exception var14) {
         this.reportError("Error in block updating thread", var14);
      }

   }

   public void org$apache$spark$streaming$receiver$BlockGenerator$$keepPushingBlocks() {
      this.logInfo(() -> "Started block pushing thread");

      try {
         while(this.areBlocksBeingGenerated$1()) {
            Option var2 = scala.Option..MODULE$.apply(this.blocksForPushing().poll(10L, TimeUnit.MILLISECONDS));
            if (var2 instanceof Some var3) {
               Block block = (Block)var3.value();
               this.pushBlock(block);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               if (!scala.None..MODULE$.equals(var2)) {
                  throw new MatchError(var2);
               }

               BoxedUnit var10 = BoxedUnit.UNIT;
            }
         }

         this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Pushing out the last "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " blocks"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BLOCK_IDS..MODULE$, BoxesRunTime.boxToInteger(this.blocksForPushing().size()))}))))));

         while(!this.blocksForPushing().isEmpty()) {
            Block block = (Block)this.blocksForPushing().take();
            this.logDebug(() -> "Pushing block " + block);
            this.pushBlock(block);
            this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Blocks left to push ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BLOCK_IDS..MODULE$, BoxesRunTime.boxToInteger(this.blocksForPushing().size()))})))));
         }

         this.logInfo(() -> "Stopped block pushing thread");
      } catch (InterruptedException var8) {
         this.logInfo(() -> "Block pushing thread was interrupted");
      } catch (Exception var9) {
         this.reportError("Error in block pushing thread", var9);
      }

   }

   private void reportError(final String message, final Throwable t) {
      this.logError(() -> message, t);
      this.listener.onError(message, t);
   }

   private void pushBlock(final Block block) {
      this.listener.onPushBlock(block.id(), block.buffer());
      this.logInfo(.MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Pushed block ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BLOCK_ID..MODULE$, block.id())})))));
   }

   private final void Block$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.Block$module == null) {
            this.Block$module = new Block$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final void GeneratorState$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.GeneratorState$module == null) {
            this.GeneratorState$module = new GeneratorState$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   private final synchronized boolean areBlocksBeingGenerated$1() {
      boolean var2;
      label23: {
         Enumeration.Value var10000 = this.state();
         Enumeration.Value var1 = this.GeneratorState().StoppedGeneratingBlocks();
         if (var10000 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!var10000.equals(var1)) {
            break label23;
         }

         var2 = false;
         return var2;
      }

      var2 = true;
      return var2;
   }

   public BlockGenerator(final BlockGeneratorListener listener, final int receiverId, final SparkConf conf, final Clock clock) {
      super(conf);
      this.listener = listener;
      this.receiverId = receiverId;
      this.blockIntervalMs = BoxesRunTime.unboxToLong(conf.get(StreamingConf$.MODULE$.BLOCK_INTERVAL()));
      scala.Predef..MODULE$.require(this.blockIntervalMs() > 0L, () -> "'" + StreamingConf$.MODULE$.BLOCK_INTERVAL().key() + "' should be a positive value");
      this.blockIntervalTimer = new RecurringTimer(clock, this.blockIntervalMs(), (JFunction1.mcVJ.sp)(time) -> this.updateCurrentBuffer(time), "BlockGenerator");
      this.blockQueueSize = conf.getInt("spark.streaming.blockQueueSize", 10);
      this.blocksForPushing = new ArrayBlockingQueue(this.blockQueueSize());
      this.blockPushingThread = new Thread() {
         // $FF: synthetic field
         private final BlockGenerator $outer;

         public void run() {
            this.$outer.org$apache$spark$streaming$receiver$BlockGenerator$$keepPushingBlocks();
         }

         public {
            if (BlockGenerator.this == null) {
               throw null;
            } else {
               this.$outer = BlockGenerator.this;
            }
         }
      };
      this.currentBuffer = new ArrayBuffer();
      this.state = this.GeneratorState().Initialized();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private class Block implements Product, Serializable {
      private final StreamBlockId id;
      private final ArrayBuffer buffer;
      // $FF: synthetic field
      public final BlockGenerator $outer;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public StreamBlockId id() {
         return this.id;
      }

      public ArrayBuffer buffer() {
         return this.buffer;
      }

      public Block copy(final StreamBlockId id, final ArrayBuffer buffer) {
         return this.org$apache$spark$streaming$receiver$BlockGenerator$Block$$$outer().new Block(id, buffer);
      }

      public StreamBlockId copy$default$1() {
         return this.id();
      }

      public ArrayBuffer copy$default$2() {
         return this.buffer();
      }

      public String productPrefix() {
         return "Block";
      }

      public int productArity() {
         return 2;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.id();
            }
            case 1 -> {
               return this.buffer();
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
         return x$1 instanceof Block;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "id";
            }
            case 1 -> {
               return "buffer";
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
            label60: {
               if (x$1 instanceof Block && ((Block)x$1).org$apache$spark$streaming$receiver$BlockGenerator$Block$$$outer() == this.org$apache$spark$streaming$receiver$BlockGenerator$Block$$$outer()) {
                  label50: {
                     Block var4 = (Block)x$1;
                     StreamBlockId var10000 = this.id();
                     StreamBlockId var5 = var4.id();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label50;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label50;
                     }

                     ArrayBuffer var7 = this.buffer();
                     ArrayBuffer var6 = var4.buffer();
                     if (var7 == null) {
                        if (var6 != null) {
                           break label50;
                        }
                     } else if (!var7.equals(var6)) {
                        break label50;
                     }

                     if (var4.canEqual(this)) {
                        break label60;
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

      // $FF: synthetic method
      public BlockGenerator org$apache$spark$streaming$receiver$BlockGenerator$Block$$$outer() {
         return this.$outer;
      }

      public Block(final StreamBlockId id, final ArrayBuffer buffer) {
         this.id = id;
         this.buffer = buffer;
         if (BlockGenerator.this == null) {
            throw null;
         } else {
            this.$outer = BlockGenerator.this;
            super();
            Product.$init$(this);
         }
      }
   }

   private class Block$ extends AbstractFunction2 implements Serializable {
      // $FF: synthetic field
      private final BlockGenerator $outer;

      public final String toString() {
         return "Block";
      }

      public Block apply(final StreamBlockId id, final ArrayBuffer buffer) {
         return this.$outer.new Block(id, buffer);
      }

      public Option unapply(final Block x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple2(x$0.id(), x$0.buffer())));
      }

      public Block$() {
         if (BlockGenerator.this == null) {
            throw null;
         } else {
            this.$outer = BlockGenerator.this;
            super();
         }
      }
   }

   private class GeneratorState$ extends Enumeration {
      private final Enumeration.Value Initialized = this.Value();
      private final Enumeration.Value Active = this.Value();
      private final Enumeration.Value StoppedAddingData = this.Value();
      private final Enumeration.Value StoppedGeneratingBlocks = this.Value();
      private final Enumeration.Value StoppedAll = this.Value();

      public Enumeration.Value Initialized() {
         return this.Initialized;
      }

      public Enumeration.Value Active() {
         return this.Active;
      }

      public Enumeration.Value StoppedAddingData() {
         return this.StoppedAddingData;
      }

      public Enumeration.Value StoppedGeneratingBlocks() {
         return this.StoppedGeneratingBlocks;
      }

      public Enumeration.Value StoppedAll() {
         return this.StoppedAll;
      }

      public GeneratorState$() {
      }
   }
}
