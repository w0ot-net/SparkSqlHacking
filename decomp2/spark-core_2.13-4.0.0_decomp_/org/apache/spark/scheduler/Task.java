package org.apache.spark.scheduler;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Properties;
import org.apache.spark.BarrierTaskContext;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.TaskContextImpl;
import org.apache.spark.executor.TaskMetrics;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.plugin.PluginContainer;
import org.apache.spark.memory.MemoryManager;
import org.apache.spark.memory.MemoryMode;
import org.apache.spark.memory.TaskMemoryManager;
import org.apache.spark.metrics.MetricsSystem;
import org.apache.spark.rdd.InputFileBlockHolder$;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CallerContext;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Option;
import scala.StringContext;
import scala.collection.IterableOnce;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\r%bA\u0002\"D\u0003\u0003)5\n\u0003\u0005g\u0001\t\u0015\r\u0011\"\u0001h\u0011!Y\u0007A!A!\u0002\u0013A\u0007\u0002\u00037\u0001\u0005\u000b\u0007I\u0011A4\t\u00115\u0004!\u0011!Q\u0001\n!D\u0001B\u001c\u0001\u0003\u0006\u0004%\ta\u001a\u0005\t_\u0002\u0011\t\u0011)A\u0005Q\"A\u0001\u000f\u0001BC\u0002\u0013\u0005q\r\u0003\u0005r\u0001\t\u0005\t\u0015!\u0003i\u0011!\u0011\bA!b\u0001\n\u0003\u0019\b\u0002\u0003=\u0001\u0005\u0003\u0005\u000b\u0011\u0002;\t\u0011e\u0004!\u00111A\u0005\u0002iD!\"a\u0002\u0001\u0005\u0003\u0007I\u0011AA\u0005\u0011%\t)\u0002\u0001B\u0001B\u0003&1\u0010\u0003\u0006\u0002 \u0001\u0011\t\u0011)A\u0005\u0003CA!\"!\f\u0001\u0005\u000b\u0007I\u0011AA\u0018\u0011)\t9\u0004\u0001B\u0001B\u0003%\u0011\u0011\u0007\u0005\u000b\u0003s\u0001!Q1A\u0005\u0002\u0005m\u0002BCA(\u0001\t\u0005\t\u0015!\u0003\u0002>!Q\u0011\u0011\u000b\u0001\u0003\u0006\u0004%\t!a\u000f\t\u0015\u0005M\u0003A!A!\u0002\u0013\ti\u0004\u0003\u0006\u0002V\u0001\u0011)\u0019!C\u0001\u0003/B!\"a\u0018\u0001\u0005\u0003\u0005\u000b\u0011BA-\u0011\u001d\t\t\u0007\u0001C\u0001\u0003GB!\"!&\u0001\u0011\u000b\u0007I\u0011AAL\u0011\u001d\t9\u000b\u0001C\u0003\u0003SC1\"a=\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002v\"Y!1\u0001\u0001A\u0002\u0003\u0007I\u0011\u0002B\u0003\u0011-\u0011I\u0001\u0001a\u0001\u0002\u0003\u0006K!a>\t\u000f\t-\u0001\u0001\"\u0001\u0003\u000e!9!\u0011\u0003\u0001\u0007\u0002\tM\u0001b\u0002B\u0010\u0001\u0011\u0005!\u0011\u0005\u0005\n\u0005_\u0001\u0001\u0019!C\u0001\u0005cA\u0011Ba\r\u0001\u0001\u0004%\tA!\u000e\t\u0011\te\u0002\u0001)Q\u0005\u0003_C1Ba\u0006\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0003<!Y!Q\b\u0001A\u0002\u0003\u0007I\u0011\u0001B \u0011-\u0011\u0019\u0005\u0001a\u0001\u0002\u0003\u0006KA!\u0007\t\u0017\t\u001d\u0003\u00011AA\u0002\u0013%!\u0011\n\u0005\f\u0005/\u0002\u0001\u0019!a\u0001\n\u0013\u0011I\u0006C\u0006\u0003^\u0001\u0001\r\u0011!Q!\n\t-\u0003\"\u0003B5\u0001\u0001\u0007I\u0011\u0002B6\u0011%\u0011i\u0007\u0001a\u0001\n\u0013\u0011y\u0007\u0003\u0005\u0003t\u0001\u0001\u000b\u0015BA \u0011%\u0011I\b\u0001a\u0001\n#\u0011\t\u0004C\u0005\u0003|\u0001\u0001\r\u0011\"\u0005\u0003~!A!\u0011\u0011\u0001!B\u0013\ty\u000bC\u0005\u0003\u0004\u0002\u0001\r\u0011\"\u0005\u00032!I!Q\u0011\u0001A\u0002\u0013E!q\u0011\u0005\t\u0005\u0017\u0003\u0001\u0015)\u0003\u00020\"9!Q\u0012\u0001\u0005\u0002\u0005m\u0002b\u0002BH\u0001\u0011\u0005!\u0011\u0007\u0005\b\u0005#\u0003A\u0011\u0001B\u0019\u0011\u001d\u0011\u0019\n\u0001C\u0001\u0005+C\u0011B!.\u0001#\u0003%\tAa.\t\u000f\t5\u0007\u0001\"\u0001\u0003P\u001eQ!\u0011\\\"\u0002\u0002#\u0005QIa7\u0007\u0013\t\u001b\u0015\u0011!E\u0001\u000b\nu\u0007bBA1s\u0011\u0005!\u0011\u001e\u0005\n\u0005WL\u0014\u0013!C\u0001\u0005[D\u0011B!>:#\u0003%\tAa>\t\u0013\t}\u0018(%A\u0005\u0002\r\u0005\u0001\"CB\u0005sE\u0005I\u0011AB\u0006\u0011%\u0019\u0019\"OI\u0001\n\u0003\u0019)\u0002C\u0005\u0004\u001ae\n\n\u0011\"\u0001\u0004\u001c!I1qD\u001d\u0002\u0002\u0013%1\u0011\u0005\u0002\u0005)\u0006\u001c8N\u0003\u0002E\u000b\u0006I1o\u00195fIVdWM\u001d\u0006\u0003\r\u001e\u000bQa\u001d9be.T!\u0001S%\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005Q\u0015aA8sOV\u0019A*!\u001c\u0014\t\u0001i5\u000b\u0019\t\u0003\u001dFk\u0011a\u0014\u0006\u0002!\u0006)1oY1mC&\u0011!k\u0014\u0002\u0007\u0003:L(+\u001a4\u0011\u0005QkfBA+\\\u001d\t1&,D\u0001X\u0015\tA\u0016,\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0001\u0016B\u0001/P\u0003\u001d\u0001\u0018mY6bO\u0016L!AX0\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005q{\u0005CA1e\u001b\u0005\u0011'BA2F\u0003!Ig\u000e^3s]\u0006d\u0017BA3c\u0005\u001daunZ4j]\u001e\fqa\u001d;bO\u0016LE-F\u0001i!\tq\u0015.\u0003\u0002k\u001f\n\u0019\u0011J\u001c;\u0002\u0011M$\u0018mZ3JI\u0002\nab\u001d;bO\u0016\fE\u000f^3naRLE-A\bti\u0006<W-\u0011;uK6\u0004H/\u00133!\u0003-\u0001\u0018M\u001d;ji&|g.\u00133\u0002\u0019A\f'\u000f^5uS>t\u0017\n\u001a\u0011\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u00039qW/\u001c)beRLG/[8og\u0002\n\u0011\"\u0019:uS\u001a\f7\r^:\u0016\u0003Q\u0004\"!\u001e<\u000e\u0003\u0015K!a^#\u0003\u001d){'-\u0011:uS\u001a\f7\r^*fi\u0006Q\u0011M\u001d;jM\u0006\u001cGo\u001d\u0011\u0002\u001f1|7-\u00197Qe>\u0004XM\u001d;jKN,\u0012a\u001f\t\u0004y\u0006\rQ\"A?\u000b\u0005y|\u0018\u0001B;uS2T!!!\u0001\u0002\t)\fg/Y\u0005\u0004\u0003\u000bi(A\u0003)s_B,'\u000f^5fg\u0006\u0019Bn\\2bYB\u0013x\u000e]3si&,7o\u0018\u0013fcR!\u00111BA\t!\rq\u0015QB\u0005\u0004\u0003\u001fy%\u0001B+oSRD\u0001\"a\u0005\r\u0003\u0003\u0005\ra_\u0001\u0004q\u0012\n\u0014\u0001\u00057pG\u0006d\u0007K]8qKJ$\u0018.Z:!Q\ri\u0011\u0011\u0004\t\u0004\u001d\u0006m\u0011bAA\u000f\u001f\nIAO]1og&,g\u000e^\u0001\u0016g\u0016\u0014\u0018.\u00197ju\u0016$G+Y:l\u001b\u0016$(/[2t!\u0015q\u00151EA\u0014\u0013\r\t)c\u0014\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004\u001d\u0006%\u0012bAA\u0016\u001f\n!!)\u001f;f\u0003\u0015QwNY%e+\t\t\t\u0004\u0005\u0003O\u0003gA\u0017bAA\u001b\u001f\n1q\n\u001d;j_:\faA[8c\u0013\u0012\u0004\u0013!B1qa&#WCAA\u001f!\u0015q\u00151GA !\u0011\t\t%!\u0013\u000f\t\u0005\r\u0013Q\t\t\u0003->K1!a\u0012P\u0003\u0019\u0001&/\u001a3fM&!\u00111JA'\u0005\u0019\u0019FO]5oO*\u0019\u0011qI(\u0002\r\u0005\u0004\b/\u00133!\u00031\t\u0007\u000f]!ui\u0016l\u0007\u000f^%e\u00035\t\u0007\u000f]!ui\u0016l\u0007\u000f^%eA\u0005I\u0011n\u001d\"beJLWM]\u000b\u0003\u00033\u00022ATA.\u0013\r\tif\u0014\u0002\b\u0005>|G.Z1o\u0003)I7OQ1se&,'\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u00151\u0005\u0015\u0014qPAA\u0003\u0007\u000b))a\"\u0002\n\u0006-\u0015QRAH\u0003#\u000b\u0019\nE\u0003\u0002h\u0001\tI'D\u0001D!\u0011\tY'!\u001c\r\u0001\u00119\u0011q\u000e\u0001C\u0002\u0005E$!\u0001+\u0012\t\u0005M\u0014\u0011\u0010\t\u0004\u001d\u0006U\u0014bAA<\u001f\n9aj\u001c;iS:<\u0007c\u0001(\u0002|%\u0019\u0011QP(\u0003\u0007\u0005s\u0017\u0010C\u0003g/\u0001\u0007\u0001\u000eC\u0003m/\u0001\u0007\u0001\u000eC\u0003o/\u0001\u0007\u0001\u000eC\u0003q/\u0001\u0007\u0001\u000eC\u0003s/\u0001\u0007A\u000fC\u0004z/A\u0005\t\u0019A>\t\u0013\u0005}q\u0003%AA\u0002\u0005\u0005\u0002\"CA\u0017/A\u0005\t\u0019AA\u0019\u0011%\tId\u0006I\u0001\u0002\u0004\ti\u0004C\u0005\u0002R]\u0001\n\u00111\u0001\u0002>!I\u0011QK\f\u0011\u0002\u0003\u0007\u0011\u0011L\u0001\b[\u0016$(/[2t+\t\tI\n\u0005\u0003\u0002\u001c\u0006\u0005VBAAO\u0015\r\ty*R\u0001\tKb,7-\u001e;pe&!\u00111UAO\u0005-!\u0016m]6NKR\u0014\u0018nY:)\u0007a\tI\"A\u0002sk:$b\"!\u001b\u0002,\u0006U\u0016\u0011XAd\u0003\u0017\f\t\u000fC\u0004\u0002.f\u0001\r!a,\u0002\u001bQ\f7o[!ui\u0016l\u0007\u000f^%e!\rq\u0015\u0011W\u0005\u0004\u0003g{%\u0001\u0002'p]\u001eDa!a.\u001a\u0001\u0004A\u0017!D1ui\u0016l\u0007\u000f\u001e(v[\n,'\u000fC\u0004\u0002<f\u0001\r!!0\u0002\u001b5,GO]5dgNK8\u000f^3n!\u0011\ty,a1\u000e\u0005\u0005\u0005'bAAK\u000b&!\u0011QYAa\u00055iU\r\u001e:jGN\u001c\u0016p\u001d;f[\"1\u0011\u0011Z\rA\u0002!\fAa\u00199vg\"9\u0011QZ\rA\u0002\u0005=\u0017!\u0003:fg>,(oY3t!!\t\t%!5\u0002@\u0005U\u0017\u0002BAj\u0003\u001b\u00121!T1q!\u0011\t9.!8\u000e\u0005\u0005e'bAAn\u000b\u0006A!/Z:pkJ\u001cW-\u0003\u0003\u0002`\u0006e'a\u0005*fg>,(oY3J]\u001a|'/\\1uS>t\u0007bBAr3\u0001\u0007\u0011Q]\u0001\ba2,x-\u001b8t!\u0015q\u00151GAt!\u0011\tI/a<\u000e\u0005\u0005-(bAAwE\u00061\u0001\u000f\\;hS:LA!!=\u0002l\ny\u0001\u000b\\;hS:\u001cuN\u001c;bS:,'/A\tuCN\\W*Z7pefl\u0015M\\1hKJ,\"!a>\u0011\t\u0005e\u0018q`\u0007\u0003\u0003wT1!!@F\u0003\u0019iW-\\8ss&!!\u0011AA~\u0005E!\u0016m]6NK6|'/_'b]\u0006<WM]\u0001\u0016i\u0006\u001c8.T3n_JLX*\u00198bO\u0016\u0014x\fJ3r)\u0011\tYAa\u0002\t\u0013\u0005M1$!AA\u0002\u0005]\u0018A\u0005;bg.lU-\\8ss6\u000bg.Y4fe\u0002\nAc]3u)\u0006\u001c8.T3n_JLX*\u00198bO\u0016\u0014H\u0003BA\u0006\u0005\u001fAq!a=\u001e\u0001\u0004\t90A\u0004sk:$\u0016m]6\u0015\t\u0005%$Q\u0003\u0005\b\u0005/q\u0002\u0019\u0001B\r\u0003\u001d\u0019wN\u001c;fqR\u00042!\u001eB\u000e\u0013\r\u0011i\"\u0012\u0002\f)\u0006\u001c8nQ8oi\u0016DH/\u0001\nqe\u00164WM\u001d:fI2{7-\u0019;j_:\u001cXC\u0001B\u0012!\u0015!&Q\u0005B\u0015\u0013\r\u00119c\u0018\u0002\u0004'\u0016\f\b\u0003BA4\u0005WI1A!\fD\u00051!\u0016m]6M_\u000e\fG/[8o\u0003\u0015)\u0007o\\2i+\t\ty+A\u0005fa>\u001c\u0007n\u0018\u0013fcR!\u00111\u0002B\u001c\u0011%\t\u0019\"IA\u0001\u0002\u0004\ty+\u0001\u0004fa>\u001c\u0007\u000eI\u000b\u0003\u00053\t1bY8oi\u0016DHo\u0018\u0013fcR!\u00111\u0002B!\u0011%\t\u0019\u0002JA\u0001\u0002\u0004\u0011I\"\u0001\u0005d_:$X\r\u001f;!Q\r)\u0013\u0011D\u0001\u000bi\u0006\u001c8\u000e\u00165sK\u0006$WC\u0001B&!\u0011\u0011iEa\u0015\u000e\u0005\t=#b\u0001B)\u007f\u0006!A.\u00198h\u0013\u0011\u0011)Fa\u0014\u0003\rQC'/Z1e\u00039!\u0018m]6UQJ,\u0017\rZ0%KF$B!a\u0003\u0003\\!I\u00111C\u0014\u0002\u0002\u0003\u0007!1J\u0001\fi\u0006\u001c8\u000e\u00165sK\u0006$\u0007\u0005K\u0002)\u0005C\u00022A\u0014B2\u0013\r\u0011)g\u0014\u0002\tm>d\u0017\r^5mK\"\u001a\u0001&!\u0007\u0002\u001f}\u0013X-Y:p]&37*\u001b7mK\u0012,\"!a\u0010\u0002'}\u0013X-Y:p]&37*\u001b7mK\u0012|F%Z9\u0015\t\u0005-!\u0011\u000f\u0005\n\u0003'Q\u0013\u0011!a\u0001\u0003\u007f\t\u0001c\u0018:fCN|g.\u00134LS2dW\r\u001a\u0011)\u0007-\u0012\t\u0007K\u0002,\u00033\t!dX3yK\u000e,Ho\u001c:EKN,'/[1mSj,G+[7f\u001dN\fadX3yK\u000e,Ho\u001c:EKN,'/[1mSj,G+[7f\u001dN|F%Z9\u0015\t\u0005-!q\u0010\u0005\n\u0003'i\u0013\u0011!a\u0001\u0003_\u000b1dX3yK\u000e,Ho\u001c:EKN,'/[1mSj,G+[7f\u001dN\u0004\u0013aG0fq\u0016\u001cW\u000f^8s\t\u0016\u001cXM]5bY&TXm\u00119v)&lW-A\u0010`Kb,7-\u001e;pe\u0012+7/\u001a:jC2L'0Z\"qkRKW.Z0%KF$B!a\u0003\u0003\n\"I\u00111\u0003\u0019\u0002\u0002\u0003\u0007\u0011qV\u0001\u001d?\u0016DXmY;u_J$Um]3sS\u0006d\u0017N_3DaV$\u0016.\\3!\u00039\u0011X-Y:p]&37*\u001b7mK\u0012\f\u0011$\u001a=fGV$xN\u001d#fg\u0016\u0014\u0018.\u00197ju\u0016$\u0016.\\3Og\u0006QR\r_3dkR|'\u000fR3tKJL\u0017\r\\5{K\u000e\u0003X\u000fV5nK\u0006I2m\u001c7mK\u000e$\u0018iY2v[Vd\u0017\r^8s+B$\u0017\r^3t)\u0011\u00119J!-\u0011\u000bQ\u0013)C!'1\r\tm%q\u0015BW!!\u0011iJ!)\u0003&\n-VB\u0001BP\u0015\tqX)\u0003\u0003\u0003$\n}%!D!dGVlW\u000f\\1u_J4&\u0007\u0005\u0003\u0002l\t\u001dFa\u0003BUk\u0005\u0005\t\u0011!B\u0001\u0003c\u00121a\u0018\u00132!\u0011\tYG!,\u0005\u0017\t=V'!A\u0001\u0002\u000b\u0005\u0011\u0011\u000f\u0002\u0004?\u0012\u0012\u0004\"\u0003BZkA\u0005\t\u0019AA-\u0003)!\u0018m]6GC&dW\rZ\u0001$G>dG.Z2u\u0003\u000e\u001cW/\\;mCR|'/\u00169eCR,7\u000f\n3fM\u0006,H\u000e\u001e\u00132+\t\u0011IL\u000b\u0003\u0002Z\tm6F\u0001B_!\u0011\u0011yL!3\u000e\u0005\t\u0005'\u0002\u0002Bb\u0005\u000b\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\t\u001dw*\u0001\u0006b]:|G/\u0019;j_:LAAa3\u0003B\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\t-LG\u000e\u001c\u000b\u0007\u0003\u0017\u0011\tN!6\t\u000f\tMw\u00071\u0001\u0002Z\u0005y\u0011N\u001c;feJ,\b\u000f\u001e+ie\u0016\fG\rC\u0004\u0003X^\u0002\r!a\u0010\u0002\rI,\u0017m]8o\u0003\u0011!\u0016m]6\u0011\u0007\u0005\u001d\u0014h\u0005\u0003:\u001b\n}\u0007\u0003\u0002Bq\u0005Ol!Aa9\u000b\u0007\t\u0015x0\u0001\u0002j_&\u0019aLa9\u0015\u0005\tm\u0017a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$c'\u0006\u0003\u0003p\nMXC\u0001ByU\rY(1\u0018\u0003\b\u0003_Z$\u0019AA9\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%oU!!\u0011 B\u007f+\t\u0011YP\u000b\u0003\u0002\"\tmFaBA8y\t\u0007\u0011\u0011O\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001d\u0016\t\r\r1qA\u000b\u0003\u0007\u000bQC!!\r\u0003<\u00129\u0011qN\u001fC\u0002\u0005E\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0013(\u0006\u0003\u0004\u000e\rEQCAB\bU\u0011\tiDa/\u0005\u000f\u0005=dH1\u0001\u0002r\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u0002T\u0003BB\u0007\u0007/!q!a\u001c@\u0005\u0004\t\t(\u0001\u000f%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%M\u0019\u0016\t\t]6Q\u0004\u0003\b\u0003_\u0002%\u0019AA9\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0019\u0019\u0003\u0005\u0003\u0003N\r\u0015\u0012\u0002BB\u0014\u0005\u001f\u0012aa\u00142kK\u000e$\b"
)
public abstract class Task implements Serializable, Logging {
   private transient TaskMetrics metrics;
   private final int stageId;
   private final int stageAttemptId;
   private final int partitionId;
   private final int numPartitions;
   private final JobArtifactSet artifacts;
   private transient Properties localProperties;
   private final byte[] serializedTaskMetrics;
   private final Option jobId;
   private final Option appId;
   private final Option appAttemptId;
   private final boolean isBarrier;
   private TaskMemoryManager taskMemoryManager;
   private long epoch;
   private transient TaskContext context;
   private transient volatile Thread taskThread;
   private transient volatile String _reasonIfKilled;
   private long _executorDeserializeTimeNs;
   private long _executorDeserializeCpuTime;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private transient volatile boolean bitmap$trans$0;

   public static boolean $lessinit$greater$default$11() {
      return Task$.MODULE$.$lessinit$greater$default$11();
   }

   public static Option $lessinit$greater$default$10() {
      return Task$.MODULE$.$lessinit$greater$default$10();
   }

   public static Option $lessinit$greater$default$9() {
      return Task$.MODULE$.$lessinit$greater$default$9();
   }

   public static Option $lessinit$greater$default$8() {
      return Task$.MODULE$.$lessinit$greater$default$8();
   }

   public static byte[] $lessinit$greater$default$7() {
      return Task$.MODULE$.$lessinit$greater$default$7();
   }

   public static Properties $lessinit$greater$default$6() {
      return Task$.MODULE$.$lessinit$greater$default$6();
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

   public int stageId() {
      return this.stageId;
   }

   public int stageAttemptId() {
      return this.stageAttemptId;
   }

   public int partitionId() {
      return this.partitionId;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public JobArtifactSet artifacts() {
      return this.artifacts;
   }

   public Properties localProperties() {
      return this.localProperties;
   }

   public void localProperties_$eq(final Properties x$1) {
      this.localProperties = x$1;
   }

   public Option jobId() {
      return this.jobId;
   }

   public Option appId() {
      return this.appId;
   }

   public Option appAttemptId() {
      return this.appAttemptId;
   }

   public boolean isBarrier() {
      return this.isBarrier;
   }

   private TaskMetrics metrics$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.metrics = (TaskMetrics)SparkEnv$.MODULE$.get().closureSerializer().newInstance().deserialize(ByteBuffer.wrap(this.serializedTaskMetrics), .MODULE$.Nothing());
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.metrics;
   }

   public TaskMetrics metrics() {
      return !this.bitmap$trans$0 ? this.metrics$lzycompute() : this.metrics;
   }

   public final Object run(final long taskAttemptId, final int attemptNumber, final MetricsSystem metricsSystem, final int cpus, final scala.collection.immutable.Map resources, final Option plugins) {
      scala.Predef..MODULE$.require(cpus > 0, () -> "CPUs per task should be > 0");
      BlockManager blockManager = SparkEnv$.MODULE$.get().blockManager();
      blockManager.registerTask(taskAttemptId);
      TaskContextImpl taskContext = new TaskContextImpl(this.stageId(), this.stageAttemptId(), this.partitionId(), taskAttemptId, attemptNumber, this.numPartitions(), this.taskMemoryManager(), this.localProperties(), metricsSystem, this.metrics(), cpus, resources);
      this.context_$eq((TaskContext)(this.isBarrier() ? new BarrierTaskContext(taskContext) : taskContext));
      InputFileBlockHolder$.MODULE$.initialize();
      TaskContext$.MODULE$.setTaskContext(this.context());
      this.taskThread_$eq(Thread.currentThread());
      if (this._reasonIfKilled() != null) {
         this.kill(false, this._reasonIfKilled());
      }

      (new CallerContext("TASK", (Option)SparkEnv$.MODULE$.get().conf().get((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.APP_CALLER_CONTEXT()), this.appId(), this.appAttemptId(), this.jobId(), scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(this.stageId())), scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(this.stageAttemptId())), scala.Option..MODULE$.apply(BoxesRunTime.boxToLong(taskAttemptId)), scala.Option..MODULE$.apply(BoxesRunTime.boxToInteger(attemptNumber)))).setCurrentContext();
      plugins.foreach((x$1) -> {
         $anonfun$run$2(x$1);
         return BoxedUnit.UNIT;
      });
      boolean var23 = false;

      Object var10000;
      try {
         var23 = true;
         var10000 = this.context().runTaskWithListeners(this);
         var23 = false;
      } finally {
         if (var23) {
            try {
               Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
                  blockManager.memoryStore().releaseUnrollMemoryForThisTask(MemoryMode.ON_HEAP, blockManager.memoryStore().releaseUnrollMemoryForThisTask$default$2());
                  blockManager.memoryStore().releaseUnrollMemoryForThisTask(MemoryMode.OFF_HEAP, blockManager.memoryStore().releaseUnrollMemoryForThisTask$default$2());
                  MemoryManager memoryManager = blockManager.memoryManager();
                  synchronized(memoryManager){}

                  try {
                     memoryManager.notifyAll();
                  } catch (Throwable var4) {
                     throw var4;
                  }

               });
            } finally {
               TaskContext$.MODULE$.unset();
               InputFileBlockHolder$.MODULE$.unset();
            }
         }
      }

      Object var10 = var10000;

      try {
         Utils$.MODULE$.tryLogNonFatalError((JFunction0.mcV.sp)() -> {
            blockManager.memoryStore().releaseUnrollMemoryForThisTask(MemoryMode.ON_HEAP, blockManager.memoryStore().releaseUnrollMemoryForThisTask$default$2());
            blockManager.memoryStore().releaseUnrollMemoryForThisTask(MemoryMode.OFF_HEAP, blockManager.memoryStore().releaseUnrollMemoryForThisTask$default$2());
            MemoryManager memoryManager = blockManager.memoryManager();
            synchronized(memoryManager){}

            try {
               memoryManager.notifyAll();
            } catch (Throwable var4) {
               throw var4;
            }

         });
      } finally {
         TaskContext$.MODULE$.unset();
         InputFileBlockHolder$.MODULE$.unset();
      }

      return var10;
   }

   private TaskMemoryManager taskMemoryManager() {
      return this.taskMemoryManager;
   }

   private void taskMemoryManager_$eq(final TaskMemoryManager x$1) {
      this.taskMemoryManager = x$1;
   }

   public void setTaskMemoryManager(final TaskMemoryManager taskMemoryManager) {
      this.taskMemoryManager_$eq(taskMemoryManager);
   }

   public abstract Object runTask(final TaskContext context);

   public Seq preferredLocations() {
      return scala.collection.immutable.Nil..MODULE$;
   }

   public long epoch() {
      return this.epoch;
   }

   public void epoch_$eq(final long x$1) {
      this.epoch = x$1;
   }

   public TaskContext context() {
      return this.context;
   }

   public void context_$eq(final TaskContext x$1) {
      this.context = x$1;
   }

   private Thread taskThread() {
      return this.taskThread;
   }

   private void taskThread_$eq(final Thread x$1) {
      this.taskThread = x$1;
   }

   private String _reasonIfKilled() {
      return this._reasonIfKilled;
   }

   private void _reasonIfKilled_$eq(final String x$1) {
      this._reasonIfKilled = x$1;
   }

   public long _executorDeserializeTimeNs() {
      return this._executorDeserializeTimeNs;
   }

   public void _executorDeserializeTimeNs_$eq(final long x$1) {
      this._executorDeserializeTimeNs = x$1;
   }

   public long _executorDeserializeCpuTime() {
      return this._executorDeserializeCpuTime;
   }

   public void _executorDeserializeCpuTime_$eq(final long x$1) {
      this._executorDeserializeCpuTime = x$1;
   }

   public Option reasonIfKilled() {
      return scala.Option..MODULE$.apply(this._reasonIfKilled());
   }

   public long executorDeserializeTimeNs() {
      return this._executorDeserializeTimeNs();
   }

   public long executorDeserializeCpuTime() {
      return this._executorDeserializeCpuTime();
   }

   public Seq collectAccumulatorUpdates(final boolean taskFailed) {
      return this.context() != null ? (Seq)this.context().taskMetrics().nonZeroInternalAccums().$plus$plus((IterableOnce)this.context().taskMetrics().withExternalAccums((x$5) -> (ArrayBuffer)x$5.filter((a) -> BoxesRunTime.boxToBoolean($anonfun$collectAccumulatorUpdates$2(taskFailed, a))))) : (Seq)scala.package..MODULE$.Seq().empty();
   }

   public boolean collectAccumulatorUpdates$default$1() {
      return false;
   }

   public void kill(final boolean interruptThread, final String reason) {
      scala.Predef..MODULE$.require(reason != null);
      this._reasonIfKilled_$eq(reason);
      if (this.context() != null) {
         synchronized(TaskContext$.MODULE$){}

         try {
            if (this.context().interruptible()) {
               this.context().markInterrupted(reason);
               if (interruptThread && this.taskThread() != null) {
                  this.taskThread().interrupt();
               }
            } else {
               this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(this.context().taskAttemptId()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is currently not interruptible. "})))).log(scala.collection.immutable.Nil..MODULE$))));
               Option threadToInterrupt = (Option)(interruptThread ? scala.Option..MODULE$.apply(this.taskThread()) : scala.None..MODULE$);
               this.context().pendingInterrupt(threadToInterrupt, reason);
            }
         } catch (Throwable var6) {
            throw var6;
         }

      }
   }

   // $FF: synthetic method
   public static final void $anonfun$run$2(final PluginContainer x$1) {
      x$1.onTaskStart();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$collectAccumulatorUpdates$2(final boolean taskFailed$1, final AccumulatorV2 a) {
      return !taskFailed$1 || a.countFailedValues();
   }

   public Task(final int stageId, final int stageAttemptId, final int partitionId, final int numPartitions, final JobArtifactSet artifacts, final Properties localProperties, final byte[] serializedTaskMetrics, final Option jobId, final Option appId, final Option appAttemptId, final boolean isBarrier) {
      this.stageId = stageId;
      this.stageAttemptId = stageAttemptId;
      this.partitionId = partitionId;
      this.numPartitions = numPartitions;
      this.artifacts = artifacts;
      this.localProperties = localProperties;
      this.serializedTaskMetrics = serializedTaskMetrics;
      this.jobId = jobId;
      this.appId = appId;
      this.appAttemptId = appAttemptId;
      this.isBarrier = isBarrier;
      super();
      Logging.$init$(this);
      this.epoch = -1L;
      this._reasonIfKilled = null;
      this._executorDeserializeTimeNs = 0L;
      this._executorDeserializeCpuTime = 0L;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
