package org.apache.spark.executor;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.apache.spark.InternalAccumulator;
import org.apache.spark.InternalAccumulator$;
import org.apache.spark.SparkContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.config.Tests$;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CollectionAccumulator;
import org.apache.spark.util.LongAccumulator;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.MapOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.LinkedHashMap;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0011\u0005b\u0001B/_\u0001\u001dDaA\u001f\u0001\u0005\u0002\u0001\\\bb\u0002@\u0001\u0005\u0004%Ia \u0005\t\u0003\u001b\u0001\u0001\u0015!\u0003\u0002\u0002!A\u0011q\u0002\u0001C\u0002\u0013%q\u0010\u0003\u0005\u0002\u0012\u0001\u0001\u000b\u0011BA\u0001\u0011!\t\u0019\u0002\u0001b\u0001\n\u0013y\b\u0002CA\u000b\u0001\u0001\u0006I!!\u0001\t\u0011\u0005]\u0001A1A\u0005\n}D\u0001\"!\u0007\u0001A\u0003%\u0011\u0011\u0001\u0005\t\u00037\u0001!\u0019!C\u0005\u007f\"A\u0011Q\u0004\u0001!\u0002\u0013\t\t\u0001\u0003\u0005\u0002 \u0001\u0011\r\u0011\"\u0003\u0000\u0011!\t\t\u0003\u0001Q\u0001\n\u0005\u0005\u0001\u0002CA\u0012\u0001\t\u0007I\u0011B@\t\u0011\u0005\u0015\u0002\u0001)A\u0005\u0003\u0003A\u0001\"a\n\u0001\u0005\u0004%Ia \u0005\t\u0003S\u0001\u0001\u0015!\u0003\u0002\u0002!A\u00111\u0006\u0001C\u0002\u0013%q\u0010\u0003\u0005\u0002.\u0001\u0001\u000b\u0011BA\u0001\u0011!\ty\u0003\u0001b\u0001\n\u0013y\b\u0002CA\u0019\u0001\u0001\u0006I!!\u0001\t\u0011\u0005M\u0002A1A\u0005\n}D\u0001\"!\u000e\u0001A\u0003%\u0011\u0011\u0001\u0005\t\u0003o\u0001!\u0019!C\u0005\u007f\"A\u0011\u0011\b\u0001!\u0002\u0013\t\t\u0001C\u0005\u0002<\u0001\u0011\r\u0011\"\u0003\u0002>!A\u0011Q\f\u0001!\u0002\u0013\ty\u0004C\u0004\u0002`\u0001!\t!!\u0019\t\u000f\u0005%\u0004\u0001\"\u0001\u0002b!9\u00111\u000e\u0001\u0005\u0002\u0005\u0005\u0004bBA7\u0001\u0011\u0005\u0011\u0011\r\u0005\b\u0003_\u0002A\u0011AA1\u0011\u001d\t\t\b\u0001C\u0001\u0003CBq!a\u001d\u0001\t\u0003\t\t\u0007C\u0004\u0002v\u0001!\t!!\u0019\t\u000f\u0005]\u0004\u0001\"\u0001\u0002b!9\u0011\u0011\u0010\u0001\u0005\u0002\u0005\u0005\u0004bBA>\u0001\u0011\u0005\u0011\u0011\r\u0005\b\u0003{\u0002A\u0011AA1\u0011\u001d\ty\b\u0001C\u0001\u0003\u0003C\u0001\"!#\u0001\t\u0003\u0001\u00171\u0012\u0005\t\u0003/\u0003A\u0011\u00011\u0002\u001a\"A\u0011Q\u0014\u0001\u0005\u0002\u0001\fy\n\u0003\u0005\u0002$\u0002!\t\u0001YAS\u0011!\tI\u000b\u0001C\u0001A\u0006-\u0006\u0002CAX\u0001\u0011\u0005\u0001-!-\t\u0011\u0005U\u0006\u0001\"\u0001a\u0003oC\u0001\"a/\u0001\t\u0003\u0001\u0017Q\u0018\u0005\t\u0003\u0003\u0004A\u0011\u00011\u0002D\"A\u0011q\u0019\u0001\u0005\u0002\u0001\fI\r\u0003\u0005\u0002N\u0002!\t\u0001YAh\u0011!\t\u0019\u000e\u0001C\u0001A\u0006U\u0007\u0002CAm\u0001\u0011\u0005\u0001-a7\t\u0011\u0005}\u0007\u0001\"\u0001a\u0003CD\u0001\"!:\u0001\t\u0003\u0001\u0017q\u001d\u0005\t\u0003K\u0004A\u0011\u00011\u0002z\"a\u0011Q \u0001\u0011\u0002\u0003\r\t\u0015!\u0003\u0002\u0000\"I!1\u0004\u0001C\u0002\u0013%!Q\u0004\u0005\t\u0005?\u0001\u0001\u0015!\u0003\u0003\u0002!I!\u0011\u0005\u0001C\u0002\u0013%!1\u0005\u0005\t\u0005K\u0001\u0001\u0015!\u0003\u0003\u0016!I!q\u0005\u0001C\u0002\u0013\u0005!\u0011\u0006\u0005\t\u0005c\u0001\u0001\u0015!\u0003\u0003,!I!1\u0007\u0001C\u0002\u0013\u0005!Q\u0007\u0005\t\u0005{\u0001\u0001\u0015!\u0003\u00038!I!q\b\u0001C\u0002\u0013\u0005!\u0011\t\u0005\t\u0005\u0013\u0002\u0001\u0015!\u0003\u0003D!I!1\n\u0001C\u0002\u0013\u0005!Q\n\u0005\t\u0005+\u0002\u0001\u0015!\u0003\u0003P!Q!q\u000b\u0001\t\u0006\u0004%IA!\u0017\t\u0011\te\u0004\u0001\"\u0001a\u0005wB\u0001B! \u0001\t\u0003\u0001'q\u0010\u0005\u000b\u0005\u0003\u0003!\u0019!C\u0001A\n\r\u0005\u0002\u0003BF\u0001\u0001\u0006IA!\"\t\u0017\t5\u0005\u0001#b\u0001\n\u0003\u0001'q\u0012\u0005\f\u0005C\u0004\u0001R1A\u0005\u0002\u0001\u0014\u0019\u000f\u0003\u0005\u0004\b\u0001!\t\u0001YB\u0005\u0011-\u00199\u0002\u0001EC\u0002\u0013\u0005\u0001m!\u0007\t\u0011\r=\u0002\u0001\"\u0001a\u0007cAqa!\u0018\u0001\t\u0013\u0019y\u0006C\u0004\u0004t\u0001!Ia!\u001e\t\u0011\r\u0005\u0005\u0001\"\u0001a\u0007\u0007C\u0001b!'\u0001\t\u0003\u000171\u0014\u0005\t\u0007_\u0003A\u0011\u00011\u00042\u001eA11\u001b0\t\u0002\u0001\u001c)NB\u0004^=\"\u0005\u0001ma6\t\ri4F\u0011ABs\u0011\u001d\u00199O\u0016C\u0001\u0007SDqaa;W\t\u0003\u0019I\u000fC\u0004\u0004nZ#\taa<\t\u000f\u0011\ra\u000b\"\u0001\u0005\u0006!IAQ\u0004,\u0002\u0002\u0013%Aq\u0004\u0002\f)\u0006\u001c8.T3ue&\u001c7O\u0003\u0002`A\u0006AQ\r_3dkR|'O\u0003\u0002bE\u0006)1\u000f]1sW*\u00111\rZ\u0001\u0007CB\f7\r[3\u000b\u0003\u0015\f1a\u001c:h\u0007\u0001\u00192\u0001\u00015o!\tIG.D\u0001k\u0015\u0005Y\u0017!B:dC2\f\u0017BA7k\u0005\u0019\te.\u001f*fMB\u0011qn\u001e\b\u0003aVt!!\u001d;\u000e\u0003IT!a\u001d4\u0002\rq\u0012xn\u001c;?\u0013\u0005Y\u0017B\u0001<k\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001_=\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005YT\u0017A\u0002\u001fj]&$h\bF\u0001}!\ti\b!D\u0001_\u0003ayV\r_3dkR|'\u000fR3tKJL\u0017\r\\5{KRKW.Z\u000b\u0003\u0003\u0003\u0001B!a\u0001\u0002\n5\u0011\u0011Q\u0001\u0006\u0004\u0003\u000f\u0001\u0017\u0001B;uS2LA!a\u0003\u0002\u0006\tyAj\u001c8h\u0003\u000e\u001cW/\\;mCR|'/A\r`Kb,7-\u001e;pe\u0012+7/\u001a:jC2L'0\u001a+j[\u0016\u0004\u0013aG0fq\u0016\u001cW\u000f^8s\t\u0016\u001cXM]5bY&TXm\u00119v)&lW-\u0001\u000f`Kb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.\u001a\u0011\u0002!}+\u00070Z2vi>\u0014(+\u001e8US6,\u0017!E0fq\u0016\u001cW\u000f^8s%VtG+[7fA\u0005\u0001r,\u001a=fGV$xN]\"qkRKW.Z\u0001\u0012?\u0016DXmY;u_J\u001c\u0005/\u001e+j[\u0016\u0004\u0013aC0sKN,H\u000e^*ju\u0016\fAb\u0018:fgVdGoU5{K\u0002\n!b\u00186w[\u001e\u001bE+[7f\u0003-y&N^7H\u0007RKW.\u001a\u0011\u00021}\u0013Xm];miN+'/[1mSj\fG/[8o)&lW-A\r`e\u0016\u001cX\u000f\u001c;TKJL\u0017\r\\5{CRLwN\u001c+j[\u0016\u0004\u0013aE0nK6|'/\u001f\"zi\u0016\u001c8\u000b]5mY\u0016$\u0017\u0001F0nK6|'/\u001f\"zi\u0016\u001c8\u000b]5mY\u0016$\u0007%A\t`I&\u001c8NQ=uKN\u001c\u0006/\u001b7mK\u0012\f!c\u00183jg.\u0014\u0015\u0010^3t'BLG\u000e\\3eA\u0005!r\f]3bW\u0016CXmY;uS>tW*Z7pef\fQc\u00189fC.,\u00050Z2vi&|g.T3n_JL\b%\u0001\u000e`a\u0016\f7n\u00148IK\u0006\u0004X\t_3dkRLwN\\'f[>\u0014\u00180A\u000e`a\u0016\f7n\u00148IK\u0006\u0004X\t_3dkRLwN\\'f[>\u0014\u0018\u0010I\u0001\u001c?B,\u0017m[(gM\"+\u0017\r]#yK\u000e,H/[8o\u001b\u0016lwN]=\u00029}\u0003X-Y6PM\u001aDU-\u00199Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:zA\u0005)r,\u001e9eCR,GM\u00117pG.\u001cF/\u0019;vg\u0016\u001cXCAA !\u0019\t\u0019!!\u0011\u0002F%!\u00111IA\u0003\u0005U\u0019u\u000e\u001c7fGRLwN\\!dGVlW\u000f\\1u_J\u0004r![A$\u0003\u0017\n9&C\u0002\u0002J)\u0014a\u0001V;qY\u0016\u0014\u0004\u0003BA'\u0003'j!!a\u0014\u000b\u0007\u0005E\u0003-A\u0004ti>\u0014\u0018mZ3\n\t\u0005U\u0013q\n\u0002\b\u00052|7m[%e!\u0011\ti%!\u0017\n\t\u0005m\u0013q\n\u0002\f\u00052|7m[*uCR,8/\u0001\f`kB$\u0017\r^3e\u00052|7m[*uCR,8/Z:!\u0003])\u00070Z2vi>\u0014H)Z:fe&\fG.\u001b>f)&lW-\u0006\u0002\u0002dA\u0019\u0011.!\u001a\n\u0007\u0005\u001d$N\u0001\u0003M_:<\u0017AG3yK\u000e,Ho\u001c:EKN,'/[1mSj,7\t];US6,\u0017aD3yK\u000e,Ho\u001c:Sk:$\u0016.\\3\u0002\u001f\u0015DXmY;u_J\u001c\u0005/\u001e+j[\u0016\f!B]3tk2$8+\u001b>f\u0003%Qg/\\$D)&lW-A\fsKN,H\u000e^*fe&\fG.\u001b>bi&|g\u000eV5nK\u0006\u0011R.Z7pef\u0014\u0015\u0010^3t'BLG\u000e\\3e\u0003A!\u0017n]6CsR,7o\u00159jY2,G-A\nqK\u0006\\W\t_3dkRLwN\\'f[>\u0014\u00180A\rqK\u0006\\wJ\u001c%fCB,\u00050Z2vi&|g.T3n_JL\u0018A\u00079fC.|eM\u001a%fCB,\u00050Z2vi&|g.T3n_JL\u0018\u0001F;qI\u0006$X\r\u001a\"m_\u000e\\7\u000b^1ukN,7/\u0006\u0002\u0002\u0004B)q.!\"\u0002F%\u0019\u0011qQ=\u0003\u0007M+\u0017/\u0001\u000etKR,\u00050Z2vi>\u0014H)Z:fe&\fG.\u001b>f)&lW\r\u0006\u0003\u0002\u000e\u0006M\u0005cA5\u0002\u0010&\u0019\u0011\u0011\u00136\u0003\tUs\u0017\u000e\u001e\u0005\b\u0003+K\u0003\u0019AA2\u0003\u00051\u0018!H:fi\u0016CXmY;u_J$Um]3sS\u0006d\u0017N_3DaV$\u0016.\\3\u0015\t\u00055\u00151\u0014\u0005\b\u0003+S\u0003\u0019AA2\u0003I\u0019X\r^#yK\u000e,Ho\u001c:Sk:$\u0016.\\3\u0015\t\u00055\u0015\u0011\u0015\u0005\b\u0003+[\u0003\u0019AA2\u0003I\u0019X\r^#yK\u000e,Ho\u001c:DaV$\u0016.\\3\u0015\t\u00055\u0015q\u0015\u0005\b\u0003+c\u0003\u0019AA2\u00035\u0019X\r\u001e*fgVdGoU5{KR!\u0011QRAW\u0011\u001d\t)*\fa\u0001\u0003G\nAb]3u\u0015Zlwi\u0011+j[\u0016$B!!$\u00024\"9\u0011Q\u0013\u0018A\u0002\u0005\r\u0014AG:fiJ+7/\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8US6,G\u0003BAG\u0003sCq!!&0\u0001\u0004\t\u0019'\u0001\ftKR\u0004V-Y6Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:z)\u0011\ti)a0\t\u000f\u0005U\u0005\u00071\u0001\u0002d\u0005a2/\u001a;QK\u0006\\wJ\u001c%fCB,\u00050Z2vi&|g.T3n_JLH\u0003BAG\u0003\u000bDq!!&2\u0001\u0004\t\u0019'A\u000ftKR\u0004V-Y6PM\u001aDU-\u00199Fq\u0016\u001cW\u000f^5p]6+Wn\u001c:z)\u0011\ti)a3\t\u000f\u0005U%\u00071\u0001\u0002d\u0005)\u0012N\\2NK6|'/\u001f\"zi\u0016\u001c8\u000b]5mY\u0016$G\u0003BAG\u0003#Dq!!&4\u0001\u0004\t\u0019'A\nj]\u000e$\u0015n]6CsR,7o\u00159jY2,G\r\u0006\u0003\u0002\u000e\u0006]\u0007bBAKi\u0001\u0007\u00111M\u0001\u0017S:\u001c\u0007+Z1l\u000bb,7-\u001e;j_:lU-\\8ssR!\u0011QRAo\u0011\u001d\t)*\u000ea\u0001\u0003G\nq#\u001b8d+B$\u0017\r^3e\u00052|7m[*uCR,8/Z:\u0015\t\u00055\u00151\u001d\u0005\b\u0003+3\u0004\u0019AA#\u0003]\u0019X\r^+qI\u0006$X\r\u001a\"m_\u000e\\7\u000b^1ukN,7\u000f\u0006\u0003\u0002\u000e\u0006%\bbBAKo\u0001\u0007\u00111\u001e\t\u0007\u0003[\f)0!\u0012\u000e\u0005\u0005=(\u0002BA\u0004\u0003cT!!a=\u0002\t)\fg/Y\u0005\u0005\u0003o\fyO\u0001\u0003MSN$H\u0003BAG\u0003wDq!!&9\u0001\u0004\t\u0019)A\u0002yIE\u0002r![A$\u0005\u0003\u0011)\u0002\u0005\u0003\u0003\u0004\tEQB\u0001B\u0003\u0015\u0011\u00119A!\u0003\u0002-I+WM\u001c;sC:$(+Z1e/JLG/\u001a'pG.TAAa\u0003\u0003\u000e\u0005)An\\2lg*!!qBAx\u0003)\u0019wN\\2veJ,g\u000e^\u0005\u0005\u0005'\u0011)A\u0001\u0005SK\u0006$Gj\\2l!\u0011\u0011\u0019Aa\u0006\n\t\te!Q\u0001\u0002\n/JLG/\u001a'pG.\f\u0001B]3bI2{7m[\u000b\u0003\u0005\u0003\t\u0011B]3bI2{7m\u001b\u0011\u0002\u0013]\u0014\u0018\u000e^3M_\u000e\\WC\u0001B\u000b\u0003)9(/\u001b;f\u0019>\u001c7\u000eI\u0001\rS:\u0004X\u000f^'fiJL7m]\u000b\u0003\u0005W\u00012! B\u0017\u0013\r\u0011yC\u0018\u0002\r\u0013:\u0004X\u000f^'fiJL7m]\u0001\u000eS:\u0004X\u000f^'fiJL7m\u001d\u0011\u0002\u001b=,H\u000f];u\u001b\u0016$(/[2t+\t\u00119\u0004E\u0002~\u0005sI1Aa\u000f_\u00055yU\u000f\u001e9vi6+GO]5dg\u0006qq.\u001e;qkRlU\r\u001e:jGN\u0004\u0013AE:ik\u001a4G.\u001a*fC\u0012lU\r\u001e:jGN,\"Aa\u0011\u0011\u0007u\u0014)%C\u0002\u0003Hy\u0013!c\u00155vM\u001adWMU3bI6+GO]5dg\u0006\u00192\u000f[;gM2,'+Z1e\u001b\u0016$(/[2tA\u0005\u00192\u000f[;gM2,wK]5uK6+GO]5dgV\u0011!q\n\t\u0004{\nE\u0013b\u0001B*=\n\u00192\u000b[;gM2,wK]5uK6+GO]5dg\u0006!2\u000f[;gM2,wK]5uK6+GO]5dg\u0002\na\u0003^3naNCWO\u001a4mKJ+\u0017\rZ'fiJL7m]\u000b\u0003\u00057\u0002bA!\u0018\u0003h\t-TB\u0001B0\u0015\u0011\u0011\tGa\u0019\u0002\u000f5,H/\u00192mK*\u0019!Q\r6\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0003j\t}#aC!se\u0006L()\u001e4gKJ\u00042! B7\u0013\r\u0011yG\u0018\u0002\u0017)\u0016l\u0007o\u00155vM\u001adWMU3bI6+GO]5dg\"\u001aaIa\u001d\u0011\u0007%\u0014)(C\u0002\u0003x)\u0014\u0011\u0002\u001e:b]NLWM\u001c;\u00029\r\u0014X-\u0019;f)\u0016l\u0007o\u00155vM\u001adWMU3bI6+GO]5dgR\u0011!1N\u0001\u0018[\u0016\u0014x-Z*ik\u001a4G.\u001a*fC\u0012lU\r\u001e:jGN$\"!!$\u0002\u0013Q,7\u000f^!dGVlWC\u0001BC!\u0015I'qQA\u0001\u0013\r\u0011II\u001b\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u0015Q,7\u000f^!dGVl\u0007%\u0001\u0007oC6,Gk\\!dGVl7/\u0006\u0002\u0003\u0012BA!Q\fBJ\u0005/\u0013\u0019+\u0003\u0003\u0003\u0016\n}#!\u0004'j].,G\rS1tQ6\u000b\u0007\u000f\u0005\u0003\u0003\u001a\n}UB\u0001BN\u0015\u0011\u0011i*!=\u0002\t1\fgnZ\u0005\u0005\u0005C\u0013YJ\u0001\u0004TiJLgn\u001a\u0019\u0007\u0005K\u0013yK!6\u0011\u0011\u0005\r!q\u0015BV\u0005'LAA!+\u0002\u0006\ti\u0011iY2v[Vd\u0017\r^8s-J\u0002BA!,\u000302\u0001Aa\u0003BY\u0001\u0005\u0005\t\u0011!B\u0001\u0005g\u0013!aX\u0019\u0012\t\tU&q\u0018\n\u0007\u0005o\u000b)Ea/\u0007\r\te\u0006\u0001\u0001B[\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0011\u0011IJ!0\n\t\u0005\u001d$1\u0014\n\u0007\u0005\u0003\u0014\u0019M!3\u0007\r\te\u0006\u0001\u0001B`!\u0011\u0011IJ!2\n\t\t\u001d'1\u0014\u0002\u0007\u001f\nTWm\u0019;\u0011\t\t-'\u0011[\u0007\u0003\u0005\u001bTAAa4\u0002r\u0006\u0011\u0011n\\\u0005\u0004q\n5\u0007\u0003\u0002BW\u0005+$1Ba6\u0001\u0003\u0003\u0005\tQ!\u0001\u0003Z\n\u0011qLM\t\u0005\u00057\u0014\u0019M\u0005\u0004\u0003^\u0006-(1\u0018\u0004\u0007\u0005s\u0003\u0001Aa7)\u0007-\u0013\u0019(\u0001\bj]R,'O\\1m\u0003\u000e\u001cW/\\:\u0016\u0005\t\u0015\b#B8\u0002\u0006\n\u001d\bG\u0002Bu\u0005[\u001c\t\u0001\u0005\u0005\u0002\u0004\t\u001d&1\u001eB\u0000!\u0011\u0011iK!<\u0005\u0017\t=H*!A\u0001\u0002\u000b\u0005!\u0011\u001f\u0002\u0004?\u0012\n\u0014\u0003\u0002Bz\u0005s\u00042!\u001bB{\u0013\r\u00119P\u001b\u0002\b\u001d>$\b.\u001b8h!\rI'1`\u0005\u0004\u0005{T'aA!osB!!QVB\u0001\t-\u0019\u0019\u0001TA\u0001\u0002\u0003\u0015\tA!=\u0003\u0007}##\u0007K\u0002M\u0005g\n\u0001B]3hSN$XM\u001d\u000b\u0005\u0003\u001b\u001bY\u0001C\u0004\u0004\u000e5\u0003\raa\u0004\u0002\u0005M\u001c\u0007\u0003BB\t\u0007'i\u0011\u0001Y\u0005\u0004\u0007+\u0001'\u0001D*qCJ\\7i\u001c8uKb$\u0018aD0fqR,'O\\1m\u0003\u000e\u001cW/\\:\u0016\u0005\rm\u0001C\u0002B/\u0005O\u001ai\u0002\r\u0004\u0004 \r\r2\u0011\u0006\t\t\u0003\u0007\u00119k!\t\u0004(A!!QVB\u0012\t-\u0019)CTA\u0001\u0002\u0003\u0015\tA!=\u0003\u0007}#3\u0007\u0005\u0003\u0003.\u000e%BaCB\u0016\u001d\u0006\u0005\t\u0011!B\u0001\u0005c\u00141a\u0018\u00135Q\rq%1O\u0001\u0013o&$\b.\u0012=uKJt\u0017\r\\!dGVl7/\u0006\u0003\u00044\r]B\u0003BB\u001b\u0007w\u0001BA!,\u00048\u001191\u0011H(C\u0002\tE(!\u0001+\t\u000f\rur\n1\u0001\u0004@\u0005\u0011q\u000e\u001d\t\bS\u000e\u00053QIB\u001b\u0013\r\u0019\u0019E\u001b\u0002\n\rVt7\r^5p]F\u0002bA!\u0018\u0003h\r\u001d\u0003GBB%\u0007\u001b\u001aI\u0006\u0005\u0005\u0002\u0004\t\u001d61JB,!\u0011\u0011ik!\u0014\u0005\u0019\r=3\u0011KA\u0001\u0002\u0003\u0015\tA!=\u0003\u0007}#S\u0007C\u0004\u0004>=\u0003\raa\u0015\u0011\u000f%\u001c\te!\u0012\u0004VA!!QVB\u001c!\u0011\u0011ik!\u0017\u0005\u0019\rm3\u0011KA\u0001\u0002\u0003\u0015\tA!=\u0003\u0007}#c'\u0001\u0007xSRD'+Z1e\u0019>\u001c7.\u0006\u0003\u0004b\r\u0015D\u0003BB2\u0007S\u0002BA!,\u0004f\u001191q\r)C\u0002\tE(!\u0001\"\t\u0011\r-\u0004\u000b\"a\u0001\u0007[\n!A\u001a8\u0011\u000b%\u001cyga\u0019\n\u0007\rE$N\u0001\u0005=Eft\u0017-\\3?\u000359\u0018\u000e\u001e5Xe&$X\rT8dWV!1qOB>)\u0011\u0019Ih! \u0011\t\t561\u0010\u0003\b\u0007O\n&\u0019\u0001By\u0011!\u0019Y'\u0015CA\u0002\r}\u0004#B5\u0004p\re\u0014a\u0005:fO&\u001cH/\u001a:BG\u000e,X.\u001e7bi>\u0014H\u0003BAG\u0007\u000bCqaa\"S\u0001\u0004\u0019I)A\u0001ba\u0019\u0019Yia$\u0004\u0016BA\u00111\u0001BT\u0007\u001b\u001b\u0019\n\u0005\u0003\u0003.\u000e=E\u0001DBI\u0007\u000b\u000b\t\u0011!A\u0003\u0002\tE(aA0%oA!!QVBK\t1\u00199j!\"\u0002\u0002\u0003\u0005)\u0011\u0001By\u0005\ryF\u0005O\u0001\rC\u000e\u001cW/\\;mCR|'o\u001d\u000b\u0003\u0007;\u0003Ra\\AC\u0007?\u0003da!)\u0004&\u000e-\u0006\u0003CA\u0002\u0005O\u001b\u0019k!+\u0011\t\t56Q\u0015\u0003\f\u0007O\u001b\u0016\u0011!A\u0001\u0006\u0003\u0011\tPA\u0002`Ie\u0002BA!,\u0004,\u0012Y1QV*\u0002\u0002\u0003\u0005)\u0011\u0001By\u0005\u0011yF%\r\u0019\u0002+9|gNW3s_&sG/\u001a:oC2\f5mY;ngR\u001111\u0017\t\u0006_\u0006\u00155Q\u0017\u0019\u0007\u0007o\u001bYl!1\u0011\u0011\u0005\r!qUB]\u0007\u007f\u0003BA!,\u0004<\u0012Y1Q\u0018+\u0002\u0002\u0003\u0005)\u0011\u0001By\u0005\u0011yF%M\u0019\u0011\t\t56\u0011\u0019\u0003\f\u0007\u0007$\u0016\u0011!A\u0001\u0006\u0003\u0011\tP\u0001\u0003`IE\u0012\u0004f\u0001\u0001\u0004HB!1\u0011ZBh\u001b\t\u0019YMC\u0002\u0004N\u0002\f!\"\u00198o_R\fG/[8o\u0013\u0011\u0019\tna3\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5\u0002\u0017Q\u000b7o['fiJL7m\u001d\t\u0003{Z\u001bbA\u00165\u0004Z\n%\u0007\u0003BBn\u0007Cl!a!8\u000b\u0007\r}\u0007-\u0001\u0005j]R,'O\\1m\u0013\u0011\u0019\u0019o!8\u0003\u000f1{wmZ5oOR\u00111Q[\u0001\u0006K6\u0004H/_\u000b\u0002y\u0006Q!/Z4jgR,'/\u001a3\u0002)\u0019\u0014x.\\!dGVlW\u000f\\1u_JLeNZ8t)\ra8\u0011\u001f\u0005\b\u0007gT\u0006\u0019AB{\u0003\u0015IgNZ8t!\u0015y\u0017QQB|!\u0011\u0019Ipa@\u000e\u0005\rm(bAB\u007fA\u0006I1o\u00195fIVdWM]\u0005\u0005\t\u0003\u0019YPA\bBG\u000e,X.\u001e7bE2,\u0017J\u001c4p\u0003A1'o\\7BG\u000e,X.\u001e7bi>\u00148\u000fF\u0002}\t\u000fAq\u0001\"\u0003\\\u0001\u0004!Y!\u0001\u0004bG\u000e,Xn\u001d\t\u0006_\u0006\u0015EQ\u0002\u0019\u0007\t\u001f!\u0019\u0002\"\u0007\u0011\u0011\u0005\r!q\u0015C\t\t/\u0001BA!,\u0005\u0014\u0011aAQ\u0003C\u0004\u0003\u0003\u0005\tQ!\u0001\u0003r\n!q\fJ\u00194!\u0011\u0011i\u000b\"\u0007\u0005\u0019\u0011mAqAA\u0001\u0002\u0003\u0015\tA!=\u0003\t}#\u0013\u0007N\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005\u0007\u0004"
)
public class TaskMetrics implements Serializable {
   private transient ArrayBuffer tempShuffleReadMetrics;
   private transient LinkedHashMap nameToAccums;
   private transient Seq internalAccums;
   private transient ArrayBuffer _externalAccums;
   private final LongAccumulator _executorDeserializeTime = new LongAccumulator();
   private final LongAccumulator _executorDeserializeCpuTime = new LongAccumulator();
   private final LongAccumulator _executorRunTime = new LongAccumulator();
   private final LongAccumulator _executorCpuTime = new LongAccumulator();
   private final LongAccumulator _resultSize = new LongAccumulator();
   private final LongAccumulator _jvmGCTime = new LongAccumulator();
   private final LongAccumulator _resultSerializationTime = new LongAccumulator();
   private final LongAccumulator _memoryBytesSpilled = new LongAccumulator();
   private final LongAccumulator _diskBytesSpilled = new LongAccumulator();
   private final LongAccumulator _peakExecutionMemory = new LongAccumulator();
   private final LongAccumulator _peakOnHeapExecutionMemory = new LongAccumulator();
   private final LongAccumulator _peakOffHeapExecutionMemory = new LongAccumulator();
   private final CollectionAccumulator _updatedBlockStatuses = new CollectionAccumulator();
   // $FF: synthetic field
   private final Tuple2 x$1;
   private final ReentrantReadWriteLock.ReadLock readLock;
   private final ReentrantReadWriteLock.WriteLock writeLock;
   private final InputMetrics inputMetrics;
   private final OutputMetrics outputMetrics;
   private final ShuffleReadMetrics shuffleReadMetrics;
   private final ShuffleWriteMetrics shuffleWriteMetrics;
   private final Option testAccum;
   private transient volatile byte bitmap$trans$0;

   public static TaskMetrics fromAccumulators(final Seq accums) {
      return TaskMetrics$.MODULE$.fromAccumulators(accums);
   }

   public static TaskMetrics fromAccumulatorInfos(final Seq infos) {
      return TaskMetrics$.MODULE$.fromAccumulatorInfos(infos);
   }

   public static TaskMetrics registered() {
      return TaskMetrics$.MODULE$.registered();
   }

   public static TaskMetrics empty() {
      return TaskMetrics$.MODULE$.empty();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return TaskMetrics$.MODULE$.LogStringContext(sc);
   }

   private LongAccumulator _executorDeserializeTime() {
      return this._executorDeserializeTime;
   }

   private LongAccumulator _executorDeserializeCpuTime() {
      return this._executorDeserializeCpuTime;
   }

   private LongAccumulator _executorRunTime() {
      return this._executorRunTime;
   }

   private LongAccumulator _executorCpuTime() {
      return this._executorCpuTime;
   }

   private LongAccumulator _resultSize() {
      return this._resultSize;
   }

   private LongAccumulator _jvmGCTime() {
      return this._jvmGCTime;
   }

   private LongAccumulator _resultSerializationTime() {
      return this._resultSerializationTime;
   }

   private LongAccumulator _memoryBytesSpilled() {
      return this._memoryBytesSpilled;
   }

   private LongAccumulator _diskBytesSpilled() {
      return this._diskBytesSpilled;
   }

   private LongAccumulator _peakExecutionMemory() {
      return this._peakExecutionMemory;
   }

   private LongAccumulator _peakOnHeapExecutionMemory() {
      return this._peakOnHeapExecutionMemory;
   }

   private LongAccumulator _peakOffHeapExecutionMemory() {
      return this._peakOffHeapExecutionMemory;
   }

   private CollectionAccumulator _updatedBlockStatuses() {
      return this._updatedBlockStatuses;
   }

   public long executorDeserializeTime() {
      return this._executorDeserializeTime().sum();
   }

   public long executorDeserializeCpuTime() {
      return this._executorDeserializeCpuTime().sum();
   }

   public long executorRunTime() {
      return this._executorRunTime().sum();
   }

   public long executorCpuTime() {
      return this._executorCpuTime().sum();
   }

   public long resultSize() {
      return this._resultSize().sum();
   }

   public long jvmGCTime() {
      return this._jvmGCTime().sum();
   }

   public long resultSerializationTime() {
      return this._resultSerializationTime().sum();
   }

   public long memoryBytesSpilled() {
      return this._memoryBytesSpilled().sum();
   }

   public long diskBytesSpilled() {
      return this._diskBytesSpilled().sum();
   }

   public long peakExecutionMemory() {
      return this._peakExecutionMemory().sum();
   }

   public long peakOnHeapExecutionMemory() {
      return this._peakOnHeapExecutionMemory().sum();
   }

   public long peakOffHeapExecutionMemory() {
      return this._peakOffHeapExecutionMemory().sum();
   }

   public Seq updatedBlockStatuses() {
      return .MODULE$.ListHasAsScala(this._updatedBlockStatuses().value()).asScala().toSeq();
   }

   public void setExecutorDeserializeTime(final long v) {
      this._executorDeserializeTime().setValue(v);
   }

   public void setExecutorDeserializeCpuTime(final long v) {
      this._executorDeserializeCpuTime().setValue(v);
   }

   public void setExecutorRunTime(final long v) {
      this._executorRunTime().setValue(v);
   }

   public void setExecutorCpuTime(final long v) {
      this._executorCpuTime().setValue(v);
   }

   public void setResultSize(final long v) {
      this._resultSize().setValue(v);
   }

   public void setJvmGCTime(final long v) {
      this._jvmGCTime().setValue(v);
   }

   public void setResultSerializationTime(final long v) {
      this._resultSerializationTime().setValue(v);
   }

   public void setPeakExecutionMemory(final long v) {
      this._peakExecutionMemory().setValue(v);
   }

   public void setPeakOnHeapExecutionMemory(final long v) {
      this._peakOnHeapExecutionMemory().setValue(v);
   }

   public void setPeakOffHeapExecutionMemory(final long v) {
      this._peakOffHeapExecutionMemory().setValue(v);
   }

   public void incMemoryBytesSpilled(final long v) {
      this._memoryBytesSpilled().add(v);
   }

   public void incDiskBytesSpilled(final long v) {
      this._diskBytesSpilled().add(v);
   }

   public void incPeakExecutionMemory(final long v) {
      this._peakExecutionMemory().add(v);
   }

   public void incUpdatedBlockStatuses(final Tuple2 v) {
      this._updatedBlockStatuses().add(v);
   }

   public void setUpdatedBlockStatuses(final List v) {
      this._updatedBlockStatuses().setValue(v);
   }

   public void setUpdatedBlockStatuses(final Seq v) {
      this._updatedBlockStatuses().setValue(.MODULE$.SeqHasAsJava(v).asJava());
   }

   private ReentrantReadWriteLock.ReadLock readLock() {
      return this.readLock;
   }

   private ReentrantReadWriteLock.WriteLock writeLock() {
      return this.writeLock;
   }

   public InputMetrics inputMetrics() {
      return this.inputMetrics;
   }

   public OutputMetrics outputMetrics() {
      return this.outputMetrics;
   }

   public ShuffleReadMetrics shuffleReadMetrics() {
      return this.shuffleReadMetrics;
   }

   public ShuffleWriteMetrics shuffleWriteMetrics() {
      return this.shuffleWriteMetrics;
   }

   private ArrayBuffer tempShuffleReadMetrics$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.tempShuffleReadMetrics = new ArrayBuffer();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.tempShuffleReadMetrics;
   }

   private ArrayBuffer tempShuffleReadMetrics() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.tempShuffleReadMetrics$lzycompute() : this.tempShuffleReadMetrics;
   }

   public synchronized TempShuffleReadMetrics createTempShuffleReadMetrics() {
      TempShuffleReadMetrics readMetrics = new TempShuffleReadMetrics();
      this.tempShuffleReadMetrics().$plus$eq(readMetrics);
      return readMetrics;
   }

   public synchronized void mergeShuffleReadMetrics() {
      if (this.tempShuffleReadMetrics().nonEmpty()) {
         this.shuffleReadMetrics().setMergeValues(this.tempShuffleReadMetrics().toSeq());
      }
   }

   public Option testAccum() {
      return this.testAccum;
   }

   private LinkedHashMap nameToAccums$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.nameToAccums = (LinkedHashMap)((MapOps)scala.collection.mutable.LinkedHashMap..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.EXECUTOR_DESERIALIZE_TIME()), this._executorDeserializeTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.EXECUTOR_DESERIALIZE_CPU_TIME()), this._executorDeserializeCpuTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.EXECUTOR_RUN_TIME()), this._executorRunTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.EXECUTOR_CPU_TIME()), this._executorCpuTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.RESULT_SIZE()), this._resultSize()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.JVM_GC_TIME()), this._jvmGCTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.RESULT_SERIALIZATION_TIME()), this._resultSerializationTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.MEMORY_BYTES_SPILLED()), this._memoryBytesSpilled()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.DISK_BYTES_SPILLED()), this._diskBytesSpilled()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.PEAK_EXECUTION_MEMORY()), this._peakExecutionMemory()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.PEAK_ON_HEAP_EXECUTION_MEMORY()), this._peakOnHeapExecutionMemory()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.PEAK_OFF_HEAP_EXECUTION_MEMORY()), this._peakOffHeapExecutionMemory()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.UPDATED_BLOCK_STATUSES()), this._updatedBlockStatuses()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_BLOCKS_FETCHED()), this.shuffleReadMetrics()._remoteBlocksFetched()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.LOCAL_BLOCKS_FETCHED()), this.shuffleReadMetrics()._localBlocksFetched()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_BYTES_READ()), this.shuffleReadMetrics()._remoteBytesRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_BYTES_READ_TO_DISK()), this.shuffleReadMetrics()._remoteBytesReadToDisk()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.LOCAL_BYTES_READ()), this.shuffleReadMetrics()._localBytesRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.FETCH_WAIT_TIME()), this.shuffleReadMetrics()._fetchWaitTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.RECORDS_READ()), this.shuffleReadMetrics()._recordsRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.CORRUPT_MERGED_BLOCK_CHUNKS()), this.shuffleReadMetrics()._corruptMergedBlockChunks()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.MERGED_FETCH_FALLBACK_COUNT()), this.shuffleReadMetrics()._mergedFetchFallbackCount()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_MERGED_BLOCKS_FETCHED()), this.shuffleReadMetrics()._remoteMergedBlocksFetched()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.LOCAL_MERGED_BLOCKS_FETCHED()), this.shuffleReadMetrics()._localMergedBlocksFetched()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_MERGED_CHUNKS_FETCHED()), this.shuffleReadMetrics()._remoteMergedChunksFetched()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.LOCAL_MERGED_CHUNKS_FETCHED()), this.shuffleReadMetrics()._localMergedChunksFetched()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_MERGED_BYTES_READ()), this.shuffleReadMetrics()._remoteMergedBytesRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.LOCAL_MERGED_BYTES_READ()), this.shuffleReadMetrics()._localMergedBytesRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_REQS_DURATION()), this.shuffleReadMetrics()._remoteReqsDuration()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleRead$.MODULE$.REMOTE_MERGED_REQS_DURATION()), this.shuffleReadMetrics()._remoteMergedReqsDuration()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleWrite$.MODULE$.BYTES_WRITTEN()), this.shuffleWriteMetrics()._bytesWritten()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleWrite$.MODULE$.RECORDS_WRITTEN()), this.shuffleWriteMetrics()._recordsWritten()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.shuffleWrite$.MODULE$.WRITE_TIME()), this.shuffleWriteMetrics()._writeTime()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.input$.MODULE$.BYTES_READ()), this.inputMetrics()._bytesRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.input$.MODULE$.RECORDS_READ()), this.inputMetrics()._recordsRead()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.output$.MODULE$.BYTES_WRITTEN()), this.outputMetrics()._bytesWritten()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator.output$.MODULE$.RECORDS_WRITTEN()), this.outputMetrics()._recordsWritten())})))).$plus$plus(this.testAccum().map((x$3) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(InternalAccumulator$.MODULE$.TEST_ACCUM()), x$3)));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.nameToAccums;
   }

   public LinkedHashMap nameToAccums() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.nameToAccums$lzycompute() : this.nameToAccums;
   }

   private Seq internalAccums$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.internalAccums = this.nameToAccums().values().toIndexedSeq();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.internalAccums;
   }

   public Seq internalAccums() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.internalAccums$lzycompute() : this.internalAccums;
   }

   public void register(final SparkContext sc) {
      this.nameToAccums().foreach((x0$1) -> {
         $anonfun$register$1(sc, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   private ArrayBuffer _externalAccums$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 8) == 0) {
            this._externalAccums = new ArrayBuffer();
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 8);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this._externalAccums;
   }

   public ArrayBuffer _externalAccums() {
      return (byte)(this.bitmap$trans$0 & 8) == 0 ? this._externalAccums$lzycompute() : this._externalAccums;
   }

   public Object withExternalAccums(final Function1 op) {
      return this.withReadLock(() -> op.apply(this._externalAccums()));
   }

   private Object withReadLock(final Function0 fn) {
      this.readLock().lock();

      Object var10000;
      try {
         var10000 = fn.apply();
      } finally {
         this.readLock().unlock();
      }

      return var10000;
   }

   private Object withWriteLock(final Function0 fn) {
      this.writeLock().lock();

      Object var10000;
      try {
         var10000 = fn.apply();
      } finally {
         this.writeLock().unlock();
      }

      return var10000;
   }

   public void registerAccumulator(final AccumulatorV2 a) {
      this.withWriteLock(() -> (ArrayBuffer)this._externalAccums().$plus$eq(a));
   }

   public Seq accumulators() {
      return (Seq)this.withReadLock(() -> (Seq)this.internalAccums().$plus$plus(this._externalAccums()));
   }

   public Seq nonZeroInternalAccums() {
      return (Seq)this.internalAccums().filter((a) -> BoxesRunTime.boxToBoolean($anonfun$nonZeroInternalAccums$1(this, a)));
   }

   // $FF: synthetic method
   public static final void $anonfun$register$1(final SparkContext sc$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         AccumulatorV2 acc = (AccumulatorV2)x0$1._2();
         acc.register(sc$1, new Some(name), true);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$nonZeroInternalAccums$1(final TaskMetrics $this, final AccumulatorV2 a) {
      boolean var10000;
      if (a.isZero()) {
         label29: {
            LongAccumulator var2 = $this._resultSize();
            if (a == null) {
               if (var2 == null) {
                  break label29;
               }
            } else if (a.equals(var2)) {
               break label29;
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   public TaskMetrics() {
      ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
      Tuple2 var2 = new Tuple2(lock.readLock(), lock.writeLock());
      if (var2 != null) {
         ReentrantReadWriteLock.ReadLock readLock = (ReentrantReadWriteLock.ReadLock)var2._1();
         ReentrantReadWriteLock.WriteLock writeLock = (ReentrantReadWriteLock.WriteLock)var2._2();
         this.x$1 = new Tuple2(readLock, writeLock);
         this.readLock = (ReentrantReadWriteLock.ReadLock)this.x$1._1();
         this.writeLock = (ReentrantReadWriteLock.WriteLock)this.x$1._2();
         this.inputMetrics = new InputMetrics();
         this.outputMetrics = new OutputMetrics();
         this.shuffleReadMetrics = new ShuffleReadMetrics();
         this.shuffleWriteMetrics = new ShuffleWriteMetrics();
         this.testAccum = scala.sys.package..MODULE$.props().get(Tests$.MODULE$.IS_TESTING().key()).map((x$2) -> new LongAccumulator());
      } else {
         throw new MatchError(var2);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
