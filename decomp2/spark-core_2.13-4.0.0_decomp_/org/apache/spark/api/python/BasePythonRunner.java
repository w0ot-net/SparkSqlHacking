package org.apache.spark.api.python;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.spark.BarrierTaskContext;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv;
import org.apache.spark.SparkEnv$;
import org.apache.spark.SparkException;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskKilledException;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Python$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rdd.InputFileBlockHolder$;
import org.apache.spark.resource.ResourceInformation;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.security.SocketAuthHelper;
import org.apache.spark.util.AccumulatorV2;
import org.apache.spark.util.CollectionAccumulator;
import org.apache.spark.util.DirectByteBufferOutputStream;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015-qACA\t\u0003'A\t!a\u0007\u0002(\u0019Q\u00111FA\n\u0011\u0003\tY\"!\f\t\u000f\u0005\u001d\u0013\u0001\"\u0001\u0002L!a\u0011QJ\u0001\t\u0006\u0004%\t!a\u0007\u0002P!I\u0011\u0011M\u0001\u0005\u0002\u0005m\u00111\r\u0005\n\u0003\u007f\nA\u0011AA\u000e\u0003\u00033!\"a\u000b\u0002\u0014\u0005\u0005\u00111DAZ\u0011)\t9L\u0002BC\u0002\u0013E\u0011\u0011\u0018\u0005\u000b\u000334!\u0011!Q\u0001\n\u0005m\u0006BCAn\r\t\u0015\r\u0011\"\u0005\u0002^\"Q\u0011q\u001c\u0004\u0003\u0002\u0003\u0006I!!\u001f\t\u0015\u0005\u0005hA!b\u0001\n#\t\u0019\u000f\u0003\u0006\u0002n\u001a\u0011\t\u0011)A\u0005\u0003KD!\"a<\u0007\u0005\u000b\u0007I\u0011CAy\u0011)\u0011)A\u0002B\u0001B\u0003%\u00111\u001f\u0005\u000b\u0005\u000f1!Q1A\u0005\u0012\t%\u0001B\u0003B\u0012\r\t\u0005\t\u0015!\u0003\u0003\f!9\u0011q\t\u0004\u0005\u0002\t\u0015\u0002\"\u0003B(\r\t\u0007I\u0011\u0002B)\u0011!\u0011YF\u0002Q\u0001\n\tM\u0003\"\u0003B/\r\t\u0007I\u0011CAo\u0011!\u0011yF\u0002Q\u0001\n\u0005e\u0004\"\u0003B1\r\t\u0007I\u0011\u0003B2\u0011!\u0011)G\u0002Q\u0001\n\u00055\u0006\"\u0003B4\r\t\u0007I\u0011\u0003B5\u0011!\u0011YG\u0002Q\u0001\n\tu\u0001\"\u0003B7\r\t\u0007I\u0011\u0003B5\u0011!\u0011yG\u0002Q\u0001\n\tu\u0001\"\u0003B9\r\t\u0007I\u0011\u0002B2\u0011!\u0011\u0019H\u0002Q\u0001\n\u00055\u0006\"\u0003B;\r\t\u0007I\u0011\u0003B2\u0011!\u00119H\u0002Q\u0001\n\u00055\u0006\"\u0003B=\r\t\u0007I\u0011\u0003B5\u0011!\u0011YH\u0002Q\u0001\n\tu\u0001\"\u0003B?\r\t\u0007I\u0011\u0003B2\u0011!\u0011yH\u0002Q\u0001\n\u00055\u0006\"\u0003BA\r\t\u0007I\u0011\u0003B2\u0011!\u0011\u0019I\u0002Q\u0001\n\u00055\u0006\"\u0003BC\r\t\u0007I\u0011\u0003BD\u0011!\u0011\tJ\u0002Q\u0001\n\t%\u0005\"\u0003BJ\r\t\u0007I\u0011\u0003BK\u0011!\u00119J\u0002Q\u0001\n\u0005U\b\"\u0003BM\r\t\u0007I\u0011\u0003BK\u0011!\u0011YJ\u0002Q\u0001\n\u0005U\b\"\u0003BO\r\t\u0007I\u0011CAo\u0011!\u0011yJ\u0002Q\u0001\n\u0005e\u0004\"\u0003BQ\r\t\u0007I\u0011\u0002BK\u0011!\u0011\u0019K\u0002Q\u0001\n\u0005U\b\"\u0003BS\r\t\u0007I\u0011\u0002BK\u0011!\u00119K\u0002Q\u0001\n\u0005U\b\"\u0003BU\r\t\u0007I\u0011\u0003BV\u0011!\u0011yM\u0002Q\u0001\n\t5\u0006\"\u0003Bi\r\t\u0007I\u0011\u0002Bj\u0011!\u00119N\u0002Q\u0001\n\tU\u0007b\u0003Bm\r\u0001\u0007I\u0011AA\u000e\u00057D1Ba;\u0007\u0001\u0004%\t!a\u0007\u0003n\"A!\u0011 \u0004!B\u0013\u0011i\u000e\u0003\u0006\u0003|\u001aA)\u0019!C\u0005\u0005{Dqaa\u0003\u0007\t\u0013\u0019i\u0001C\u0004\u0004\u001a\u0019!\taa\u0007\t\u000f\r]bA\"\u0005\u0004:!9A1\u0002\u0004\u0007\u0012\u00115aaBB \r\u0005\u00051\u0011\t\u0005\u000b\u0007\u0007r$\u0011!Q\u0001\n\r\u0015\u0003BCAQ}\t\u0005\t\u0015!\u0003\u0002$\"Q1Q\u0005 \u0003\u0002\u0003\u0006Iaa\n\t\u0015\r-bH!A!\u0002\u0013\tI\b\u0003\u0006\u00040y\u0012\t\u0011)A\u0005\u0007cAq!a\u0012?\t\u0003\u0019Y\u0005C\u0006\u0004Xy\u0002\r\u00111A\u0005\n\re\u0003bCB1}\u0001\u0007\t\u0019!C\u0005\u0007GB1ba\u001a?\u0001\u0004\u0005\t\u0015)\u0003\u0004\\!I1\u0011\u000f C\u0002\u0013%11\u000f\u0005\t\u0007\u000bs\u0004\u0015!\u0003\u0004v!I1q\u0011 C\u0002\u0013%1\u0011\u0012\u0005\t\u0007Cs\u0004\u0015!\u0003\u0004\f\"911\u0015 \u0005\u0002\r\u0015\u0006bBBU}\u0019E11\u0016\u0005\b\u0007osd\u0011AB]\u0011\u001d\u0019iL\u0010C\u0001\u0007\u007fCqaa1?\t\u0003\u0019)\rC\u0004\u0004Jz\"\taa3\t\u0013\r}g(%A\u0005\u0002\r\u0005\bbBB|}\u0011\u00051\u0011 \u0004\b\t\u007f1\u0011\u0011\u0001C!\u0011)!\t\u0002\u0016B\u0001B\u0003%A1\u0003\u0005\u000b\t7!&\u0011!Q\u0001\n\rm\u0002B\u0003C\u0010)\n\u0005\t\u0015!\u0003\u0003\u001e!Q11\t+\u0003\u0002\u0003\u0006Ia!\u0012\t\u0015\u0005\u0005FK!A!\u0002\u0013\t\u0019\u000b\u0003\u0006\u0002xQ\u0013\t\u0011)A\u0005\tOA!\u0002b\u000bU\u0005\u0003\u0005\u000b\u0011\u0002C\u0017\u0011)\u0019y\u0003\u0016B\u0001B\u0003%1\u0011\u0007\u0005\b\u0003\u000f\"F\u0011\u0001C\"\u0011-!9\u0006\u0016a\u0001\u0002\u0004%I\u0001\"\u0017\t\u0017\u0011mC\u000b1AA\u0002\u0013%AQ\f\u0005\f\tC\"\u0006\u0019!A!B\u0013\u0011y\u0004C\u0005\u0005dQ\u0003\r\u0011\"\u0003\u0003d!IAQ\r+A\u0002\u0013%Aq\r\u0005\t\tW\"\u0006\u0015)\u0003\u0002.\"9AQ\u000e+\u0005B\t\r\u0004b\u0002C8)\u0012\u0005C\u0011\u000f\u0005\b\tg\"f\u0011\u0003C9\u0011\u001d!)\b\u0016C\t\toBq\u0001\"\u001fU\t#!Y\bC\u0004\u0005\u0004R#\t\u0002b\u001e\t\u0013\u0011\u0015EK1A\u0005\u0012\u0011\u001d\u0005\u0002\u0003CH)\u0002\u0006I\u0001\"#\u0007\r\u0011Ee\u0001\u0001CJ\u0011)\u0019\u0019\u0005\u001cB\u0001B\u0003%1Q\t\u0005\u000b\u0003Cc'\u0011!Q\u0001\n\u0005\r\u0006BCB\u0018Y\n\u0005\t\u0015!\u0003\u00042!9\u0011q\t7\u0005\u0002\u0011m\u0005\"\u0003CSY\n\u0007I\u0011\u0002B5\u0011!!9\u000b\u001cQ\u0001\n\tu\u0001b\u0002CUY\u0012%Aq\u000f\u0005\b\tWcG\u0011\tC<\r\u0019!iK\u0002\u0001\u00050\"Q\u0011\u0011U;\u0003\u0002\u0003\u0006I!a)\t\u0015\u0011mQO!A!\u0002\u0013\u0019Y\u0004\u0003\u0006\u0002\fV\u0014\t\u0011)A\u0005\u0003\u001bC!B!\u001fv\u0005\u0003\u0005\u000b\u0011\u0002B\u000f\u0011\u001d\t9%\u001eC\u0001\toC\u0001\u0002b1vA\u0003&AQ\u0019\u0005\t\t\u0017,\b\u0015!\u0003\u0005N\"AAQ[;!\u0002\u0013!9\u000eC\u0006\u0005^V\u0004\r\u0011!Q!\n\u0011}\u0007\u0002\u0003Ctk\u0002\u0006K!!,\t\u000f\u0011MT\u000f\"\u0011\u0005j\"AA1^;!\u0002\u0013\u0011i\u0002C\u0004\u0005tU$\t\u0005\"<\t\u0013\u0011mX\u000f1A\u0005\n\t%\u0004\"\u0003C\u007fk\u0002\u0007I\u0011\u0002C\u0000\u0011!)\u0019!\u001eQ!\n\tu\u0001bBC\u0003k\u0012%Qq\u0001\u0005\b\u000b\u0013)H\u0011\u0002C<\u0003A\u0011\u0015m]3QsRDwN\u001c*v]:,'O\u0003\u0003\u0002\u0016\u0005]\u0011A\u00029zi\"|gN\u0003\u0003\u0002\u001a\u0005m\u0011aA1qS*!\u0011QDA\u0010\u0003\u0015\u0019\b/\u0019:l\u0015\u0011\t\t#a\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\t\t)#A\u0002pe\u001e\u00042!!\u000b\u0002\u001b\t\t\u0019B\u0001\tCCN,\u0007+\u001f;i_:\u0014VO\u001c8feN)\u0011!a\f\u0002<A!\u0011\u0011GA\u001c\u001b\t\t\u0019D\u0003\u0002\u00026\u0005)1oY1mC&!\u0011\u0011HA\u001a\u0005\u0019\te.\u001f*fMB!\u0011QHA\"\u001b\t\tyD\u0003\u0003\u0002B\u0005m\u0011\u0001C5oi\u0016\u0014h.\u00197\n\t\u0005\u0015\u0013q\b\u0002\b\u0019><w-\u001b8h\u0003\u0019a\u0014N\\5u}\r\u0001ACAA\u0014\u0003I1\u0017-\u001e7u\u0011\u0006tG\r\\3s\u0019><G)\u001b:\u0016\u0005\u0005E\u0003\u0003BA*\u0003;j!!!\u0016\u000b\t\u0005]\u0013\u0011L\u0001\u0003S>T!!a\u0017\u0002\t)\fg/Y\u0005\u0005\u0003?\n)F\u0001\u0003GS2,\u0017a\u00054bk2$\b*\u00198eY\u0016\u0014Hj\\4QCRDG\u0003BA3\u0003k\u0002B!a\u001a\u0002r5\u0011\u0011\u0011\u000e\u0006\u0005\u0003W\ni'\u0001\u0003gS2,'\u0002BA8\u00033\n1A\\5p\u0013\u0011\t\u0019(!\u001b\u0003\tA\u000bG\u000f\u001b\u0005\b\u0003o\"\u0001\u0019AA=\u0003\r\u0001\u0018\u000e\u001a\t\u0005\u0003c\tY(\u0003\u0003\u0002~\u0005M\"aA%oi\u0006!\u0003/\u001f;i_:<vN]6feN#\u0018\r^;t\u001b\u0016\u001c8/Y4f/&$\bnQ8oi\u0016DH\u000f\u0006\u0005\u0002\u0004\u0006%\u0015qTAU!\u0011\ti$!\"\n\t\u0005\u001d\u0015q\b\u0002\u0013\u001b\u0016\u001c8/Y4f/&$\bnQ8oi\u0016DH\u000fC\u0004\u0002\f\u0016\u0001\r!!$\u0002\r!\fg\u000e\u001a7f!\u0019\t\t$a$\u0002\u0014&!\u0011\u0011SA\u001a\u0005\u0019y\u0005\u000f^5p]B!\u0011QSAN\u001b\t\t9J\u0003\u0003\u0002\u001a\u0006e\u0013\u0001\u00027b]\u001eLA!!(\u0002\u0018\ni\u0001K]8dKN\u001c\b*\u00198eY\u0016Dq!!)\u0006\u0001\u0004\t\u0019+\u0001\u0004x_J\\WM\u001d\t\u0005\u0003S\t)+\u0003\u0003\u0002(\u0006M!\u0001\u0004)zi\"|gnV8sW\u0016\u0014\bbBAV\u000b\u0001\u0007\u0011QV\u0001\nQ\u0006\u001c\u0018J\u001c9viN\u0004B!!\r\u00020&!\u0011\u0011WA\u001a\u0005\u001d\u0011un\u001c7fC:,b!!.\u0003.\t\u00053#\u0002\u0004\u00020\u0005m\u0012!\u00024v]\u000e\u001cXCAA^!\u0019\ti,!4\u0002T:!\u0011qXAe\u001d\u0011\t\t-a2\u000e\u0005\u0005\r'\u0002BAc\u0003\u0013\na\u0001\u0010:p_Rt\u0014BAA\u001b\u0013\u0011\tY-a\r\u0002\u000fA\f7m[1hK&!\u0011qZAi\u0005\r\u0019V-\u001d\u0006\u0005\u0003\u0017\f\u0019\u0004\u0005\u0003\u0002*\u0005U\u0017\u0002BAl\u0003'\u0011ac\u00115bS:,G\rU=uQ>tg)\u001e8di&|gn]\u0001\u0007MVt7m\u001d\u0011\u0002\u0011\u00154\u0018\r\u001c+za\u0016,\"!!\u001f\u0002\u0013\u00154\u0018\r\u001c+za\u0016\u0004\u0013AC1sO>3gm]3ugV\u0011\u0011Q\u001d\t\u0007\u0003c\t9/a;\n\t\u0005%\u00181\u0007\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0007\u0003c\t9/!\u001f\u0002\u0017\u0005\u0014xm\u00144gg\u0016$8\u000fI\u0001\u0010U>\u0014\u0017I\u001d;jM\u0006\u001cG/V+J\tV\u0011\u00111\u001f\t\u0007\u0003c\ty)!>\u0011\t\u0005]\u0018q \b\u0005\u0003s\fY\u0010\u0005\u0003\u0002B\u0006M\u0012\u0002BA\u007f\u0003g\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002B\u0001\u0005\u0007\u0011aa\u0015;sS:<'\u0002BA\u007f\u0003g\t\u0001C[8c\u0003J$\u0018NZ1diV+\u0016\n\u0012\u0011\u0002\u000f5,GO]5dgV\u0011!1\u0002\t\t\u0003o\u0014i!!>\u0003\u0012%!!q\u0002B\u0002\u0005\ri\u0015\r\u001d\t\t\u0005'\u0011IB!\b\u0003\u001e5\u0011!Q\u0003\u0006\u0005\u0005/\tY\"\u0001\u0003vi&d\u0017\u0002\u0002B\u000e\u0005+\u0011Q\"Q2dk6,H.\u0019;peZ\u0013\u0004\u0003BA\u0019\u0005?IAA!\t\u00024\t!Aj\u001c8h\u0003!iW\r\u001e:jGN\u0004C\u0003\u0004B\u0014\u0005\u000b\u00129E!\u0013\u0003L\t5\u0003cBA\u0015\r\t%\"q\b\t\u0005\u0005W\u0011i\u0003\u0004\u0001\u0005\u000f\t=bA1\u0001\u00032\t\u0011\u0011JT\t\u0005\u0005g\u0011I\u0004\u0005\u0003\u00022\tU\u0012\u0002\u0002B\u001c\u0003g\u0011qAT8uQ&tw\r\u0005\u0003\u00022\tm\u0012\u0002\u0002B\u001f\u0003g\u00111!\u00118z!\u0011\u0011YC!\u0011\u0005\u000f\t\rcA1\u0001\u00032\t\u0019q*\u0016+\t\u000f\u0005]\u0016\u00031\u0001\u0002<\"9\u00111\\\tA\u0002\u0005e\u0004bBAq#\u0001\u0007\u0011Q\u001d\u0005\b\u0003_\f\u0002\u0019AAz\u0011\u001d\u00119!\u0005a\u0001\u0005\u0017\tAaY8oMV\u0011!1\u000b\t\u0005\u0005+\u00129&\u0004\u0002\u0002\u001c%!!\u0011LA\u000e\u0005%\u0019\u0006/\u0019:l\u0007>tg-A\u0003d_:4\u0007%\u0001\u0006ck\u001a4WM]*ju\u0016\f1BY;gM\u0016\u00148+\u001b>fA\u0005\u0011B/[7fYf4E.^:i\u000b:\f'\r\\3e+\t\ti+A\nuS6,G.\u001f$mkNDWI\\1cY\u0016$\u0007%A\fuS6,G.\u001f$mkNDG+[7f_V$h*\u00198pgV\u0011!QD\u0001\u0019i&lW\r\\=GYV\u001c\b\u000eV5nK>,HOT1o_N\u0004\u0013!E1vi\"\u001cvnY6fiRKW.Z8vi\u0006\u0011\u0012-\u001e;i'>\u001c7.\u001a;US6,w.\u001e;!\u0003-\u0011X-^:f/>\u00148.\u001a:\u0002\u0019I,Wo]3X_J\\WM\u001d\u0011\u0002'\u0019\fW\u000f\u001c;IC:$G.\u001a:F]\u0006\u0014G.\u001a3\u0002)\u0019\fW\u000f\u001c;IC:$G.\u001a:F]\u0006\u0014G.\u001a3!\u0003IIG\r\\3US6,w.\u001e;TK\u000e|g\u000eZ:\u0002'%$G.\u001a+j[\u0016|W\u000f^*fG>tGm\u001d\u0011\u0002\u001b!LG-\u001a+sC\u000e,'-Y2l\u00039A\u0017\u000eZ3Ue\u0006\u001cWMY1dW\u0002\n1c]5na2Lg-[3e)J\f7-\u001a2bG.\fAc]5na2Lg-[3e)J\f7-\u001a2bG.\u0004\u0013aB3omZ\u000b'o]\u000b\u0003\u0005\u0013\u0003\u0002Ba#\u0003\u0010\u0006U\u0018Q_\u0007\u0003\u0005\u001bSAAa\u0006\u0002Z%!!q\u0002BG\u0003!)gN\u001e,beN\u0004\u0013A\u00039zi\"|g.\u0012=fGV\u0011\u0011Q_\u0001\faf$\bn\u001c8Fq\u0016\u001c\u0007%A\u0005qsRDwN\u001c,fe\u0006Q\u0001/\u001f;i_:4VM\u001d\u0011\u0002+\t\fGo\u00195TSj,gi\u001c:QsRDwN\\+E\r\u00061\"-\u0019;dQNK'0\u001a$peBKH\u000f[8o+\u00123\u0005%\u0001\u0007eC\u0016lwN\\'pIVdW-A\u0007eC\u0016lwN\\'pIVdW\rI\u0001\ro>\u00148.\u001a:N_\u0012,H.Z\u0001\u000eo>\u00148.\u001a:N_\u0012,H.\u001a\u0011\u0002\u0017\u0005\u001c7-^7vY\u0006$xN]\u000b\u0003\u0005[\u0003BAa,\u0003J:!!\u0011\u0017Bc\u001d\u0011\u0011\u0019La1\u000f\t\tU&\u0011\u0019\b\u0005\u0005o\u0013yL\u0004\u0003\u0003:\nuf\u0002BAa\u0005wK!!!\n\n\t\u0005\u0005\u00121E\u0005\u0005\u0003;\ty\"\u0003\u0003\u0002\u001a\u0005m\u0011\u0002BA\u000b\u0003/IAAa2\u0002\u0014\u0005q\u0001+\u001f;i_:4UO\\2uS>t\u0017\u0002\u0002Bf\u0005\u001b\u0014\u0011\u0003U=uQ>t\u0017iY2v[Vd\u0017\r^8s\u0015\u0011\u00119-a\u0005\u0002\u0019\u0005\u001c7-^7vY\u0006$xN\u001d\u0011\u0002!5\f\u0017PY3BG\u000e,X.\u001e7bi>\u0014XC\u0001Bk!\u0019\t\t$a$\u0003.\u0006\tR.Y=cK\u0006\u001b7-^7vY\u0006$xN\u001d\u0011\u0002\u0019M,'O^3s'>\u001c7.\u001a;\u0016\u0005\tu\u0007CBA\u0019\u0003\u001f\u0013y\u000e\u0005\u0003\u0003b\n\u001dXB\u0001Br\u0015\u0011\u0011)/!\u0017\u0002\u00079,G/\u0003\u0003\u0003j\n\r(\u0001D*feZ,'oU8dW\u0016$\u0018\u0001E:feZ,'oU8dW\u0016$x\fJ3r)\u0011\u0011yO!>\u0011\t\u0005E\"\u0011_\u0005\u0005\u0005g\f\u0019D\u0001\u0003V]&$\b\"\u0003B|o\u0005\u0005\t\u0019\u0001Bo\u0003\rAH%M\u0001\u000eg\u0016\u0014h/\u001a:T_\u000e\\W\r\u001e\u0011\u0002\u0015\u0005,H\u000f\u001b%fYB,'/\u0006\u0002\u0003\u0000B!1\u0011AB\u0004\u001b\t\u0019\u0019A\u0003\u0003\u0004\u0006\u0005m\u0011\u0001C:fGV\u0014\u0018\u000e^=\n\t\r%11\u0001\u0002\u0011'>\u001c7.\u001a;BkRD\u0007*\u001a7qKJ\f\u0011cZ3u/>\u00148.\u001a:NK6|'/_'c)\u0019\u0019ya!\u0005\u0004\u0016A1\u0011\u0011GAH\u0005;Aqaa\u0005;\u0001\u0004\u0019y!A\u0002nK6Dqaa\u0006;\u0001\u0004\tI(A\u0003d_J,7/A\u0004d_6\u0004X\u000f^3\u0015\u0011\ru11EB\u0015\u0007[\u0001b!!0\u0004 \t}\u0012\u0002BB\u0011\u0003#\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0005\b\u0007KY\u0004\u0019AB\u0014\u00035Ig\u000e];u\u0013R,'/\u0019;peB1\u0011QXB\u0010\u0005SAqaa\u000b<\u0001\u0004\tI(\u0001\bqCJ$\u0018\u000e^5p]&sG-\u001a=\t\u000f\r=2\b1\u0001\u00042\u000591m\u001c8uKb$\b\u0003\u0002B+\u0007gIAa!\u000e\u0002\u001c\tYA+Y:l\u0007>tG/\u001a=u\u0003%qWm^,sSR,'\u000f\u0006\u0007\u0004<\u0011\u0005A1\u0001C\u0003\t\u000f!I\u0001E\u0002\u0004>yj\u0011A\u0002\u0002\u0007/JLG/\u001a:\u0014\u0007y\ny#A\u0002f]Z\u0004BA!\u0016\u0004H%!1\u0011JA\u000e\u0005!\u0019\u0006/\u0019:l\u000b:4H\u0003DB\u001e\u0007\u001b\u001aye!\u0015\u0004T\rU\u0003bBB\"\t\u0002\u00071Q\t\u0005\b\u0003C#\u0005\u0019AAR\u0011\u001d\u0019)\u0003\u0012a\u0001\u0007OAqaa\u000bE\u0001\u0004\tI\bC\u0004\u00040\u0011\u0003\ra!\r\u0002\u0015}+\u0007pY3qi&|g.\u0006\u0002\u0004\\A!\u0011QXB/\u0013\u0011\u0019y&!5\u0003\u0013QC'o\\<bE2,\u0017AD0fq\u000e,\u0007\u000f^5p]~#S-\u001d\u000b\u0005\u0005_\u001c)\u0007C\u0005\u0003x\u001a\u000b\t\u00111\u0001\u0004\\\u0005Yq,\u001a=dKB$\u0018n\u001c8!Q\r951\u000e\t\u0005\u0003c\u0019i'\u0003\u0003\u0004p\u0005M\"\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002\u001dALH\u000f[8o\u0013:\u001cG.\u001e3fgV\u00111Q\u000f\t\u0007\u0007o\u001a\t)!>\u000e\u0005\re$\u0002BB>\u0007{\n\u0011\"[7nkR\f'\r\\3\u000b\t\r}\u00141G\u0001\u000bG>dG.Z2uS>t\u0017\u0002BBB\u0007s\u00121aU3u\u0003=\u0001\u0018\u0010\u001e5p]&s7\r\\;eKN\u0004\u0013!\u00042s_\u0006$7-Y:u-\u0006\u00148/\u0006\u0002\u0004\fB11qOBG\u0007\u001fKA!a4\u0004zA11\u0011SBL\u00077k!aa%\u000b\t\rU\u00151D\u0001\nEJ|\u0017\rZ2bgRLAa!'\u0004\u0014\nI!I]8bI\u000e\f7\u000f\u001e\t\u0005\u0003S\u0019i*\u0003\u0003\u0004 \u0006M!a\u0004)zi\"|gN\u0011:pC\u0012\u001c\u0017m\u001d;\u0002\u001d\t\u0014x.\u00193dCN$h+\u0019:tA\u0005IQ\r_2faRLwN\\\u000b\u0003\u0007O\u0003b!!\r\u0002\u0010\u000em\u0013\u0001D<sSR,7i\\7nC:$G\u0003\u0002Bx\u0007[Cqaa,N\u0001\u0004\u0019\t,A\u0004eCR\fw*\u001e;\u0011\t\u0005M31W\u0005\u0005\u0007k\u000b)F\u0001\tECR\fw*\u001e;qkR\u001cFO]3b[\u00061rO]5uK:+\u0007\u0010^%oaV$Hk\\*ue\u0016\fW\u000e\u0006\u0003\u0002.\u000em\u0006bBBX\u001d\u0002\u00071\u0011W\u0001\u0005_B,g\u000e\u0006\u0003\u0003p\u000e\u0005\u0007bBBX\u001f\u0002\u00071\u0011W\u0001\u0006G2|7/\u001a\u000b\u0005\u0005_\u001c9\rC\u0004\u00040B\u0003\ra!-\u0002\u001f\t\f'O]5fe\u0006sGmU3sm\u0016$\u0002Ba<\u0004N\u000eE71\u001c\u0005\b\u0007\u001f\f\u0006\u0019AA=\u00035\u0011X-];fgRlU\r\u001e5pI\"911[)A\u0002\rU\u0017\u0001B:pG.\u0004BA!9\u0004X&!1\u0011\u001cBr\u0005\u0019\u0019vnY6fi\"I1Q\\)\u0011\u0002\u0003\u0007\u0011Q_\u0001\b[\u0016\u001c8/Y4f\u0003e\u0011\u0017M\u001d:jKJ\fe\u000eZ*feZ,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\r\r(\u0006BA{\u0007K\\#aa:\u0011\t\r%81_\u0007\u0003\u0007WTAa!<\u0004p\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0005\u0007c\f\u0019$\u0001\u0006b]:|G/\u0019;j_:LAa!>\u0004l\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0011]\u0014\u0018\u000e^3V)\u001a#bAa<\u0004|\u000e}\bbBB\u007f'\u0002\u0007\u0011Q_\u0001\u0004gR\u0014\bbBBX'\u0002\u00071\u0011\u0017\u0005\b\u0007\u0007b\u0004\u0019AB#\u0011\u001d\t\t\u000b\u0010a\u0001\u0003GCqa!\n=\u0001\u0004\u00199\u0003C\u0004\u0004,q\u0002\r!!\u001f\t\u000f\r=B\b1\u0001\u00042\u0005\tb.Z<SK\u0006$WM]%uKJ\fGo\u001c:\u0015%\ruAq\u0002C\r\t;!\t\u0003b\t\u0005&\u0011%BQ\b\u0005\b\t#i\u0004\u0019\u0001C\n\u0003\u0019\u0019HO]3b[B!\u00111\u000bC\u000b\u0013\u0011!9\"!\u0016\u0003\u001f\u0011\u000bG/Y%oaV$8\u000b\u001e:fC6Dq\u0001b\u0007>\u0001\u0004\u0019Y$\u0001\u0004xe&$XM\u001d\u0005\b\t?i\u0004\u0019\u0001B\u000f\u0003%\u0019H/\u0019:u)&lW\rC\u0004\u0004Du\u0002\ra!\u0012\t\u000f\u0005\u0005V\b1\u0001\u0002$\"9\u0011qO\u001fA\u0002\u0011\u001d\u0002CBA\u0019\u0003\u001f\u000bI\bC\u0004\u0005,u\u0002\r\u0001\"\f\u0002!I,G.Z1tK\u0012|%o\u00117pg\u0016$\u0007\u0003\u0002C\u0018\tsi!\u0001\"\r\u000b\t\u0011MBQG\u0001\u0007CR|W.[2\u000b\t\u0011]\"QR\u0001\u000bG>t7-\u001e:sK:$\u0018\u0002\u0002C\u001e\tc\u0011Q\"\u0011;p[&\u001c'i\\8mK\u0006t\u0007bBB\u0018{\u0001\u00071\u0011\u0007\u0002\u000f%\u0016\fG-\u001a:Ji\u0016\u0014\u0018\r^8s'\u0015!\u0016qFB\u000f)I!)\u0005b\u0012\u0005J\u0011-CQ\nC(\t#\"\u0019\u0006\"\u0016\u0011\u0007\ruB\u000bC\u0004\u0005\u0012u\u0003\r\u0001b\u0005\t\u000f\u0011mQ\f1\u0001\u0004<!9AqD/A\u0002\tu\u0001bBB\";\u0002\u00071Q\t\u0005\b\u0003Ck\u0006\u0019AAR\u0011\u001d\t9(\u0018a\u0001\tOAq\u0001b\u000b^\u0001\u0004!i\u0003C\u0004\u00040u\u0003\ra!\r\u0002\u000f9,\u0007\u0010^(cUV\u0011!qH\u0001\f]\u0016DHo\u00142k?\u0012*\u0017\u000f\u0006\u0003\u0003p\u0012}\u0003\"\u0003B|?\u0006\u0005\t\u0019\u0001B \u0003!qW\r\u001f;PE*\u0004\u0013aA3pg\u00069Qm\\:`I\u0015\fH\u0003\u0002Bx\tSB\u0011Ba>c\u0003\u0003\u0005\r!!,\u0002\t\u0015|7\u000fI\u0001\bQ\u0006\u001ch*\u001a=u\u0003\u0011qW\r\u001f;\u0015\u0005\t}\u0012\u0001\u0002:fC\u0012\f\u0001\u0003[1oI2,G+[7j]\u001e$\u0015\r^1\u0015\u0005\t=\u0018!\u00065b]\u0012dW\rU=uQ>tW\t_2faRLwN\u001c\u000b\u0003\t{\u0002B!!\u000b\u0005\u0000%!A\u0011QA\n\u0005=\u0001\u0016\u0010\u001e5p]\u0016C8-\u001a9uS>t\u0017A\u00065b]\u0012dW-\u00128e\u001f\u001a$\u0015\r^1TK\u000e$\u0018n\u001c8\u0002\u001f!\fg\u000e\u001a7f\u000bb\u001cW\r\u001d;j_:,\"\u0001\"#\u0011\u0011\u0005EB1RB.\u0005\u007fIA\u0001\"$\u00024\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g.\u0001\tiC:$G.Z#yG\u0016\u0004H/[8oA\tiQj\u001c8ji>\u0014H\u000b\u001b:fC\u0012\u001c2\u0001\u001cCK!\u0011\t)\nb&\n\t\u0011e\u0015q\u0013\u0002\u0007)\"\u0014X-\u00193\u0015\u0011\u0011uEq\u0014CQ\tG\u00032a!\u0010m\u0011\u001d\u0019\u0019\u0005\u001da\u0001\u0007\u000bBq!!)q\u0001\u0004\t\u0019\u000bC\u0004\u00040A\u0004\ra!\r\u0002\u001fQ\f7o[&jY2$\u0016.\\3pkR\f\u0001\u0003^1tW.KG\u000e\u001c+j[\u0016|W\u000f\u001e\u0011\u0002\u001b5|g.\u001b;pe^{'o[3s\u0003\r\u0011XO\u001c\u0002\u0012%\u0016\fG-\u001a:J]B,Ho\u0015;sK\u0006l7cA;\u00052B!\u00111\u000bCZ\u0013\u0011!),!\u0016\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u000b\ts#Y\f\"0\u0005@\u0012\u0005\u0007cAB\u001fk\"9\u0011\u0011\u0015>A\u0002\u0005\r\u0006b\u0002C\u000eu\u0002\u000711\b\u0005\b\u0003\u0017S\b\u0019AAG\u0011\u001d\u0011IH\u001fa\u0001\u0005;\t!d\u001e:ji\u0016\u0014\u0018J\u001a2i)\"\u0014X-\u00193M_\u000e\fGNV1mk\u0016\u0004B!!&\u0005H&!A\u0011ZAL\u0005\u0019y%M[3di\u0006!A/Z7q!\u0019\t\t$a:\u0005PB!\u0011\u0011\u0007Ci\u0013\u0011!\u0019.a\r\u0003\t\tKH/Z\u0001\rEV4g-\u001a:TiJ,\u0017-\u001c\t\u0005\u0005'!I.\u0003\u0003\u0005\\\nU!\u0001\b#je\u0016\u001cGOQ=uK\n+hMZ3s\u001fV$\b/\u001e;TiJ,\u0017-\\\u0001\u0007EV4g-\u001a:\u0011\t\u0011\u0005H1]\u0007\u0003\u0003[JA\u0001\":\u0002n\tQ!)\u001f;f\u0005V4g-\u001a:\u0002\u0011!\f7/\u00138qkR$\"!!\u001f\u0002#%$G.\u001a+j[\u0016|W\u000f^'jY2L7\u000f\u0006\u0005\u0002z\u0011=H1\u001fC|\u0011!!\t0!\u0002A\u0002\u00115\u0017!\u00012\t\u0011\u0011U\u0018Q\u0001a\u0001\u0003s\n1a\u001c4g\u0011!!I0!\u0002A\u0002\u0005e\u0014a\u00017f]\u0006iA.Y:u\r2,8\u000f\u001b+j[\u0016\f\u0011\u0003\\1ti\u001acWo\u001d5US6,w\fJ3r)\u0011\u0011y/\"\u0001\t\u0015\t]\u0018\u0011BA\u0001\u0002\u0004\u0011i\"\u0001\bmCN$h\t\\;tQRKW.\u001a\u0011\u0002\u0017MDw.\u001e7e\r2,8\u000f\u001b\u000b\u0003\u0003[\u000b!e\u001e:ji\u0016\fE\rZ5uS>t\u0017\r\\%oaV$Hk\u001c)zi\"|gnV8sW\u0016\u0014\b"
)
public abstract class BasePythonRunner implements Logging {
   private SocketAuthHelper org$apache$spark$api$python$BasePythonRunner$$authHelper;
   private final Seq funcs;
   private final int evalType;
   private final int[][] argOffsets;
   private final Option jobArtifactUUID;
   private final Map metrics;
   private final SparkConf conf;
   private final int bufferSize;
   private final boolean timelyFlushEnabled;
   private final long timelyFlushTimeoutNanos;
   private final long authSocketTimeout;
   private final boolean org$apache$spark$api$python$BasePythonRunner$$reuseWorker;
   private final boolean faultHandlerEnabled;
   private final long idleTimeoutSeconds;
   private final boolean hideTraceback;
   private final boolean simplifiedTraceback;
   private final java.util.Map envVars;
   private final String pythonExec;
   private final String pythonVer;
   private final int batchSizeForPythonUDF;
   private final String org$apache$spark$api$python$BasePythonRunner$$daemonModule;
   private final String org$apache$spark$api$python$BasePythonRunner$$workerModule;
   private final CollectionAccumulator accumulator;
   private final Option org$apache$spark$api$python$BasePythonRunner$$maybeAccumulator;
   private Option serverSocket;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private volatile boolean bitmap$0;

   public String logName() {
      return Logging.logName$(this);
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

   public Seq funcs() {
      return this.funcs;
   }

   public int evalType() {
      return this.evalType;
   }

   public int[][] argOffsets() {
      return this.argOffsets;
   }

   public Option jobArtifactUUID() {
      return this.jobArtifactUUID;
   }

   public Map metrics() {
      return this.metrics;
   }

   private SparkConf conf() {
      return this.conf;
   }

   public int bufferSize() {
      return this.bufferSize;
   }

   public boolean timelyFlushEnabled() {
      return this.timelyFlushEnabled;
   }

   public long timelyFlushTimeoutNanos() {
      return this.timelyFlushTimeoutNanos;
   }

   public long authSocketTimeout() {
      return this.authSocketTimeout;
   }

   public boolean org$apache$spark$api$python$BasePythonRunner$$reuseWorker() {
      return this.org$apache$spark$api$python$BasePythonRunner$$reuseWorker;
   }

   public boolean faultHandlerEnabled() {
      return this.faultHandlerEnabled;
   }

   public long idleTimeoutSeconds() {
      return this.idleTimeoutSeconds;
   }

   public boolean hideTraceback() {
      return this.hideTraceback;
   }

   public boolean simplifiedTraceback() {
      return this.simplifiedTraceback;
   }

   public java.util.Map envVars() {
      return this.envVars;
   }

   public String pythonExec() {
      return this.pythonExec;
   }

   public String pythonVer() {
      return this.pythonVer;
   }

   public int batchSizeForPythonUDF() {
      return this.batchSizeForPythonUDF;
   }

   public String org$apache$spark$api$python$BasePythonRunner$$daemonModule() {
      return this.org$apache$spark$api$python$BasePythonRunner$$daemonModule;
   }

   public String org$apache$spark$api$python$BasePythonRunner$$workerModule() {
      return this.org$apache$spark$api$python$BasePythonRunner$$workerModule;
   }

   public CollectionAccumulator accumulator() {
      return this.accumulator;
   }

   public Option org$apache$spark$api$python$BasePythonRunner$$maybeAccumulator() {
      return this.org$apache$spark$api$python$BasePythonRunner$$maybeAccumulator;
   }

   public Option serverSocket() {
      return this.serverSocket;
   }

   public void serverSocket_$eq(final Option x$1) {
      this.serverSocket = x$1;
   }

   private SocketAuthHelper authHelper$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.org$apache$spark$api$python$BasePythonRunner$$authHelper = new SocketAuthHelper(this.conf());
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.org$apache$spark$api$python$BasePythonRunner$$authHelper;
   }

   public SocketAuthHelper org$apache$spark$api$python$BasePythonRunner$$authHelper() {
      return !this.bitmap$0 ? this.authHelper$lzycompute() : this.org$apache$spark$api$python$BasePythonRunner$$authHelper;
   }

   private Option getWorkerMemoryMb(final Option mem, final int cores) {
      return mem.map((JFunction1.mcJJ.sp)(x$2) -> x$2 / (long)cores);
   }

   public Iterator compute(final Iterator inputIterator, final int partitionIndex, final TaskContext context) {
      long startTime = System.currentTimeMillis();
      SparkEnv env = SparkEnv$.MODULE$.get();
      Option execCoresProp = .MODULE$.apply(context.getLocalProperty(ResourceProfile$.MODULE$.EXECUTOR_CORES_LOCAL_PROPERTY()));
      Option memoryMb = .MODULE$.apply(context.getLocalProperty(ResourceProfile$.MODULE$.PYSPARK_MEMORY_LOCAL_PROPERTY())).map((x$3) -> BoxesRunTime.boxToLong($anonfun$compute$1(x$3)));
      String localdir = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])env.blockManager().diskBlockManager().localDirs()), (f) -> f.getPath(), scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString(",");
      if (this.conf().getOption("spark.executorEnv.OMP_NUM_THREADS").isEmpty()) {
         this.envVars().put("OMP_NUM_THREADS", this.conf().get("spark.task.cpus", "1"));
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.envVars().put("SPARK_LOCAL_DIRS", localdir);
      if (this.org$apache$spark$api$python$BasePythonRunner$$reuseWorker()) {
         this.envVars().put("SPARK_REUSE_WORKER", "1");
      } else {
         BoxedUnit var26 = BoxedUnit.UNIT;
      }

      if (this.hideTraceback()) {
         this.envVars().put("SPARK_HIDE_TRACEBACK", "1");
      } else {
         BoxedUnit var27 = BoxedUnit.UNIT;
      }

      if (this.simplifiedTraceback()) {
         this.envVars().put("SPARK_SIMPLIFIED_TRACEBACK", "1");
      } else {
         BoxedUnit var28 = BoxedUnit.UNIT;
      }

      int execCores = BoxesRunTime.unboxToInt(execCoresProp.map((x$4) -> BoxesRunTime.boxToInteger($anonfun$compute$3(x$4))).getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.EXECUTOR_CORES()))));
      Option workerMemoryMb = this.getWorkerMemoryMb(memoryMb, execCores);
      if (workerMemoryMb.isDefined()) {
         this.envVars().put("PYSPARK_EXECUTOR_MEMORY_MB", workerMemoryMb.get().toString());
      } else {
         BoxedUnit var29 = BoxedUnit.UNIT;
      }

      this.envVars().put("SPARK_AUTH_SOCKET_TIMEOUT", Long.toString(this.authSocketTimeout()));
      this.envVars().put("SPARK_BUFFER_SIZE", Integer.toString(this.bufferSize()));
      if (this.faultHandlerEnabled()) {
         this.envVars().put("PYTHON_FAULTHANDLER_DIR", BasePythonRunner$.MODULE$.faultHandlerLogDir().toString());
      } else {
         BoxedUnit var30 = BoxedUnit.UNIT;
      }

      this.envVars().put("PYTHON_UDF_BATCH_SIZE", Integer.toString(this.batchSizeForPythonUDF()));
      this.envVars().put("SPARK_JOB_ARTIFACT_UUID", this.jobArtifactUUID().getOrElse(() -> "default"));
      Tuple2 var14 = env.createPythonWorker(this.pythonExec(), this.org$apache$spark$api$python$BasePythonRunner$$workerModule(), this.org$apache$spark$api$python$BasePythonRunner$$daemonModule(), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.envVars()).asScala().toMap(scala..less.colon.less..MODULE$.refl()));
      if (var14 != null) {
         PythonWorker worker = (PythonWorker)var14._1();
         Option handle = (Option)var14._2();
         if (worker != null && handle != null) {
            Tuple2 var13 = new Tuple2(worker, handle);
            PythonWorker worker = (PythonWorker)var13._1();
            Option handle = (Option)var13._2();
            AtomicBoolean releasedOrClosed = new AtomicBoolean(false);
            Writer writer = this.newWriter(env, worker, inputIterator, partitionIndex, context);
            context.addTaskCompletionListener((Function1)((x$6) -> {
               $anonfun$compute$6(this, releasedOrClosed, worker, x$6);
               return BoxedUnit.UNIT;
            }));
            if (this.org$apache$spark$api$python$BasePythonRunner$$reuseWorker()) {
               Tuple2 key = new Tuple2(worker, BoxesRunTime.boxToLong(context.taskAttemptId()));
               if (PythonRunner$.MODULE$.runningMonitorThreads().add(key)) {
                  (new MonitorThread(SparkEnv$.MODULE$.get(), worker, context)).start();
               }
            } else {
               (new MonitorThread(SparkEnv$.MODULE$.get(), worker, context)).start();
            }

            DataInputStream dataIn = new DataInputStream(new BufferedInputStream(new ReaderInputStream(worker, writer, handle, this.idleTimeoutSeconds()), this.bufferSize()));
            Iterator stdoutIterator = this.newReaderIterator(dataIn, writer, startTime, env, worker, handle.map((x$7) -> BoxesRunTime.boxToInteger($anonfun$compute$8(x$7))), releasedOrClosed, context);
            return new InterruptibleIterator(context, stdoutIterator);
         }
      }

      throw new MatchError(var14);
   }

   public abstract Writer newWriter(final SparkEnv env, final PythonWorker worker, final Iterator inputIterator, final int partitionIndex, final TaskContext context);

   public abstract Iterator newReaderIterator(final DataInputStream stream, final Writer writer, final long startTime, final SparkEnv env, final PythonWorker worker, final Option pid, final AtomicBoolean releasedOrClosed, final TaskContext context);

   // $FF: synthetic method
   public static final long $anonfun$compute$1(final String x$3) {
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(x$3));
   }

   // $FF: synthetic method
   public static final int $anonfun$compute$3(final String x$4) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   public static final void $anonfun$compute$6(final BasePythonRunner $this, final AtomicBoolean releasedOrClosed$1, final PythonWorker worker$2, final TaskContext x$6) {
      if (!$this.org$apache$spark$api$python$BasePythonRunner$$reuseWorker() || releasedOrClosed$1.compareAndSet(false, true)) {
         try {
            worker$2.stop();
         } catch (Exception var5) {
            $this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to stop worker"})))).log(scala.collection.immutable.Nil..MODULE$)), var5);
         }

      }
   }

   // $FF: synthetic method
   public static final int $anonfun$compute$8(final ProcessHandle x$7) {
      return (int)x$7.pid();
   }

   public BasePythonRunner(final Seq funcs, final int evalType, final int[][] argOffsets, final Option jobArtifactUUID, final Map metrics) {
      this.funcs = funcs;
      this.evalType = evalType;
      this.argOffsets = argOffsets;
      this.jobArtifactUUID = jobArtifactUUID;
      this.metrics = metrics;
      Logging.$init$(this);
      scala.Predef..MODULE$.require(funcs.length() == argOffsets.length, () -> "argOffsets should have the same length as funcs");
      this.conf = SparkEnv$.MODULE$.get().conf();
      this.bufferSize = BoxesRunTime.unboxToInt(this.conf().get(package$.MODULE$.BUFFER_SIZE()));
      this.timelyFlushEnabled = false;
      this.timelyFlushTimeoutNanos = 0L;
      this.authSocketTimeout = BoxesRunTime.unboxToLong(this.conf().get(Python$.MODULE$.PYTHON_AUTH_SOCKET_TIMEOUT()));
      this.org$apache$spark$api$python$BasePythonRunner$$reuseWorker = BoxesRunTime.unboxToBoolean(this.conf().get(Python$.MODULE$.PYTHON_WORKER_REUSE()));
      this.faultHandlerEnabled = BoxesRunTime.unboxToBoolean(this.conf().get(Python$.MODULE$.PYTHON_WORKER_FAULTHANLDER_ENABLED()));
      this.idleTimeoutSeconds = BoxesRunTime.unboxToLong(this.conf().get(Python$.MODULE$.PYTHON_WORKER_IDLE_TIMEOUT_SECONDS()));
      this.hideTraceback = false;
      this.simplifiedTraceback = false;
      this.envVars = ((PythonFunction)((ChainedPythonFunctions)funcs.head()).funcs().head()).envVars();
      this.pythonExec = ((PythonFunction)((ChainedPythonFunctions)funcs.head()).funcs().head()).pythonExec();
      this.pythonVer = ((PythonFunction)((ChainedPythonFunctions)funcs.head()).funcs().head()).pythonVer();
      this.batchSizeForPythonUDF = 100;
      this.org$apache$spark$api$python$BasePythonRunner$$daemonModule = (String)((Option)this.conf().get((ConfigEntry)Python$.MODULE$.PYTHON_DAEMON_MODULE())).map((value) -> {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Python daemon module in PySpark is set to "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"[", "] in '", "', using this to start the daemon up. Note that this "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, value), new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, Python$.MODULE$.PYTHON_DAEMON_MODULE().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"configuration only has an effect when '", "' is enabled and the platform is not Windows."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, Python$.MODULE$.PYTHON_USE_DAEMON().key())}))))));
         return value;
      }).getOrElse(() -> "pyspark.daemon");
      this.org$apache$spark$api$python$BasePythonRunner$$workerModule = (String)((Option)this.conf().get((ConfigEntry)Python$.MODULE$.PYTHON_WORKER_MODULE())).map((value) -> {
         this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Python worker module in PySpark is set to ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.VALUE..MODULE$, value)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"in ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, Python$.MODULE$.PYTHON_WORKER_MODULE().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"using this to start the worker up. Note that this configuration only has "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"an effect when ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, Python$.MODULE$.PYTHON_USE_DAEMON().key())})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is disabled or the platform is Windows."})))).log(scala.collection.immutable.Nil..MODULE$))));
         return value;
      }).getOrElse(() -> "pyspark.worker");
      this.accumulator = ((PythonFunction)((ChainedPythonFunctions)funcs.head()).funcs().head()).accumulator();
      this.org$apache$spark$api$python$BasePythonRunner$$maybeAccumulator = .MODULE$.apply(this.accumulator());
      this.serverSocket = scala.None..MODULE$;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class Writer {
      private final SparkEnv env;
      private final PythonWorker worker;
      private final int partitionIndex;
      private final TaskContext context;
      private volatile Throwable _exception;
      private final Set pythonIncludes;
      private final Seq broadcastVars;
      // $FF: synthetic field
      public final BasePythonRunner $outer;

      private Throwable _exception() {
         return this._exception;
      }

      private void _exception_$eq(final Throwable x$1) {
         this._exception = x$1;
      }

      private Set pythonIncludes() {
         return this.pythonIncludes;
      }

      private Seq broadcastVars() {
         return this.broadcastVars;
      }

      public Option exception() {
         return .MODULE$.apply(this._exception());
      }

      public abstract void writeCommand(final DataOutputStream dataOut);

      public abstract boolean writeNextInputToStream(final DataOutputStream dataOut);

      public void open(final DataOutputStream dataOut) {
         Utils$.MODULE$.logUncaughtExceptions(() -> {
            Object var10000;
            try {
               dataOut.writeInt(this.partitionIndex);
               PythonWorkerUtils$.MODULE$.writePythonVersion(this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().pythonVer(), dataOut);
               boolean isBarrier = this.context instanceof BarrierTaskContext;
               if (isBarrier) {
                  this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket_$eq(new Some(new ServerSocket(0, 1, InetAddress.getByName("localhost"))));
                  this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket().foreach((x$13) -> {
                     $anonfun$open$2(x$13);
                     return BoxedUnit.UNIT;
                  });
                  (new Thread() {
                     // $FF: synthetic field
                     private final Writer $outer;

                     public void run() {
                        while(!((ServerSocket)this.$outer.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket().get()).isClosed()) {
                           Socket sock = null;

                           try {
                              sock = ((ServerSocket)this.$outer.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket().get()).accept();
                              sock.setSoTimeout(10000);
                              this.$outer.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().org$apache$spark$api$python$BasePythonRunner$$authHelper().authClient(sock);
                              DataInputStream input = new DataInputStream(sock.getInputStream());
                              int requestMethod = input.readInt();
                              sock.setSoTimeout(0);
                              if (BarrierTaskContextMessageProtocol$.MODULE$.BARRIER_FUNCTION() == requestMethod) {
                                 this.$outer.barrierAndServe(requestMethod, sock, this.$outer.barrierAndServe$default$3());
                                 BoxedUnit var19 = BoxedUnit.UNIT;
                              } else if (BarrierTaskContextMessageProtocol$.MODULE$.ALL_GATHER_FUNCTION() == requestMethod) {
                                 String message = PythonWorkerUtils$.MODULE$.readUTF(input);
                                 this.$outer.barrierAndServe(requestMethod, sock, message);
                                 BoxedUnit var18 = BoxedUnit.UNIT;
                              } else {
                                 DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
                                 this.$outer.writeUTF(BarrierTaskContextMessageProtocol$.MODULE$.ERROR_UNRECOGNIZED_FUNCTION(), out);
                                 BoxedUnit var17 = BoxedUnit.UNIT;
                              }
                           } catch (Throwable var15) {
                              if (var15 instanceof SocketException var11) {
                                 if (var11.getMessage().contains("Socket closed")) {
                                    BoxedUnit var10000 = BoxedUnit.UNIT;
                                    continue;
                                 }
                              }

                              throw var15;
                           } finally {
                              if (sock != null) {
                                 sock.close();
                              }

                           }
                        }

                     }

                     public {
                        if (Writer.this == null) {
                           throw null;
                        } else {
                           this.$outer = Writer.this;
                           this.setDaemon(true);
                        }
                     }
                  }).start();
               }

               String secret = isBarrier ? this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().org$apache$spark$api$python$BasePythonRunner$$authHelper().secret() : "";
               if (isBarrier) {
                  this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket().foreach((server) -> this.context.addTaskCompletionListener((Function1)((x$14) -> {
                        $anonfun$open$4(server, x$14);
                        return BoxedUnit.UNIT;
                     })));
                  int boundPort = BoxesRunTime.unboxToInt(this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket().map((x$15) -> BoxesRunTime.boxToInteger($anonfun$open$5(x$15))).getOrElse((JFunction0.mcI.sp)() -> 0));
                  if (boundPort == -1) {
                     String message = "ServerSocket failed to bind to Java side.";
                     this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().logError((Function0)(() -> message));
                     throw new SparkException(message);
                  }

                  this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().logDebug((Function0)(() -> "Started ServerSocket on port " + boundPort + "."));
                  dataOut.writeBoolean(true);
                  dataOut.writeInt(boundPort);
               } else {
                  dataOut.writeBoolean(false);
                  dataOut.writeInt(0);
               }

               byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
               dataOut.writeInt(secretBytes.length);
               dataOut.write(secretBytes, 0, secretBytes.length);
               dataOut.writeInt(this.context.stageId());
               dataOut.writeInt(this.context.partitionId());
               dataOut.writeInt(this.context.attemptNumber());
               dataOut.writeLong(this.context.taskAttemptId());
               dataOut.writeInt(this.context.cpus());
               Map resources = this.context.resources();
               dataOut.writeInt(resources.size());
               resources.foreach((x0$1) -> {
                  $anonfun$open$9(dataOut, x0$1);
                  return BoxedUnit.UNIT;
               });
               scala.collection.mutable.Map localProps = scala.jdk.CollectionConverters..MODULE$.PropertiesHasAsScala(this.context.getLocalProperties()).asScala();
               dataOut.writeInt(localProps.size());
               localProps.foreach((x0$3) -> {
                  $anonfun$open$11(dataOut, x0$3);
                  return BoxedUnit.UNIT;
               });
               PythonWorkerUtils$.MODULE$.writeSparkFiles(this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().jobArtifactUUID(), this.pythonIncludes(), dataOut);
               PythonWorkerUtils$.MODULE$.writeBroadcasts(this.broadcastVars(), this.worker, this.env, dataOut);
               dataOut.writeInt(this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().evalType());
               this.writeCommand(dataOut);
               dataOut.flush();
               var10000 = BoxedUnit.UNIT;
            } catch (Throwable var13) {
               if (var13 == null || !scala.util.control.NonFatal..MODULE$.apply(var13) && !(var13 instanceof Exception)) {
                  throw var13;
               }

               if (!this.context.isCompleted() && !this.context.isInterrupted()) {
                  this._exception_$eq(var13);
                  var10000 = this.worker.channel().isConnected() ? Utils$.MODULE$.tryLog(() -> this.worker.channel().shutdownOutput()) : BoxedUnit.UNIT;
               } else {
                  this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().logDebug((Function0)(() -> "Exception/NonFatal Error thrown after task completion (likely due to cleanup)"), var13);
                  var10000 = this.worker.channel().isConnected() ? Utils$.MODULE$.tryLog(() -> this.worker.channel().shutdownOutput()) : BoxedUnit.UNIT;
               }
            }

            return var10000;
         });
      }

      public void close(final DataOutputStream dataOut) {
         dataOut.writeInt(SpecialLengths$.MODULE$.END_OF_STREAM());
         dataOut.flush();
      }

      public void barrierAndServe(final int requestMethod, final Socket sock, final String message) {
         scala.Predef..MODULE$.require(this.org$apache$spark$api$python$BasePythonRunner$Writer$$$outer().serverSocket().isDefined(), () -> "No available ServerSocket to redirect the BarrierTaskContext method call.");
         DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));

         try {
            String[] var10000;
            if (BarrierTaskContextMessageProtocol$.MODULE$.BARRIER_FUNCTION() == requestMethod) {
               ((BarrierTaskContext)this.context).barrier();
               var10000 = (String[])((Object[])(new String[]{BarrierTaskContextMessageProtocol$.MODULE$.BARRIER_RESULT_SUCCESS()}));
            } else {
               if (BarrierTaskContextMessageProtocol$.MODULE$.ALL_GATHER_FUNCTION() != requestMethod) {
                  throw new MatchError(BoxesRunTime.boxToInteger(requestMethod));
               }

               var10000 = ((BarrierTaskContext)this.context).allGather(message);
            }

            String[] messages = var10000;
            out.writeInt(messages.length);
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])messages), (x$16) -> {
               $anonfun$barrierAndServe$2(this, out, x$16);
               return BoxedUnit.UNIT;
            });
         } catch (SparkException var12) {
            this.writeUTF(var12.getMessage(), out);
         } finally {
            out.close();
         }

      }

      public String barrierAndServe$default$3() {
         return "";
      }

      public void writeUTF(final String str, final DataOutputStream dataOut) {
         PythonWorkerUtils$.MODULE$.writeUTF(str, dataOut);
      }

      // $FF: synthetic method
      public BasePythonRunner org$apache$spark$api$python$BasePythonRunner$Writer$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$open$2(final ServerSocket x$13) {
         x$13.setSoTimeout(0);
      }

      // $FF: synthetic method
      public static final void $anonfun$open$4(final ServerSocket server$1, final TaskContext x$14) {
         server$1.close();
      }

      // $FF: synthetic method
      public static final int $anonfun$open$5(final ServerSocket x$15) {
         return x$15.getLocalPort();
      }

      // $FF: synthetic method
      public static final void $anonfun$open$10(final DataOutputStream dataOut$1, final String x0$2) {
         PythonRDD$.MODULE$.writeUTF(x0$2, dataOut$1);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      // $FF: synthetic method
      public static final void $anonfun$open$9(final DataOutputStream dataOut$1, final Tuple2 x0$1) {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            ResourceInformation v = (ResourceInformation)x0$1._2();
            PythonRDD$.MODULE$.writeUTF(k, dataOut$1);
            PythonRDD$.MODULE$.writeUTF(v.name(), dataOut$1);
            dataOut$1.writeInt(v.addresses().length);
            scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])v.addresses()), (x0$2) -> {
               $anonfun$open$10(dataOut$1, x0$2);
               return BoxedUnit.UNIT;
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$open$11(final DataOutputStream dataOut$1, final Tuple2 x0$3) {
         if (x0$3 != null) {
            String k = (String)x0$3._1();
            String v = (String)x0$3._2();
            PythonRDD$.MODULE$.writeUTF(k, dataOut$1);
            PythonRDD$.MODULE$.writeUTF(v, dataOut$1);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$3);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$barrierAndServe$2(final Writer $this, final DataOutputStream out$1, final String x$16) {
         $this.writeUTF(x$16, out$1);
      }

      public Writer(final SparkEnv env, final PythonWorker worker, final Iterator inputIterator, final int partitionIndex, final TaskContext context) {
         this.env = env;
         this.worker = worker;
         this.partitionIndex = partitionIndex;
         this.context = context;
         if (BasePythonRunner.this == null) {
            throw null;
         } else {
            this.$outer = BasePythonRunner.this;
            super();
            this.pythonIncludes = ((IterableOnceOps)BasePythonRunner.this.funcs().flatMap((x$9) -> (Seq)x$9.funcs().flatMap((x$10) -> scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(x$10.pythonIncludes()).asScala()))).toSet();
            this.broadcastVars = (Seq)BasePythonRunner.this.funcs().flatMap((x$11) -> (Seq)x$11.funcs().flatMap((x$12) -> scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(x$12.broadcastVars()).asScala()));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public abstract class ReaderIterator implements Iterator {
      private final DataInputStream stream;
      public final Writer org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer;
      private final long startTime;
      private final SparkEnv env;
      private final PythonWorker worker;
      public final Option org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid;
      private final AtomicBoolean releasedOrClosed;
      public final TaskContext org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context;
      private Object nextObj;
      private boolean eos;
      private final PartialFunction handleException;
      // $FF: synthetic field
      public final BasePythonRunner $outer;

      /** @deprecated */
      public final boolean hasDefiniteSize() {
         return Iterator.hasDefiniteSize$(this);
      }

      public final Iterator iterator() {
         return Iterator.iterator$(this);
      }

      public Option nextOption() {
         return Iterator.nextOption$(this);
      }

      public boolean contains(final Object elem) {
         return Iterator.contains$(this, elem);
      }

      public BufferedIterator buffered() {
         return Iterator.buffered$(this);
      }

      public Iterator padTo(final int len, final Object elem) {
         return Iterator.padTo$(this, len, elem);
      }

      public Tuple2 partition(final Function1 p) {
         return Iterator.partition$(this, p);
      }

      public Iterator.GroupedIterator grouped(final int size) {
         return Iterator.grouped$(this, size);
      }

      public Iterator.GroupedIterator sliding(final int size, final int step) {
         return Iterator.sliding$(this, size, step);
      }

      public int sliding$default$2() {
         return Iterator.sliding$default$2$(this);
      }

      public Iterator scanLeft(final Object z, final Function2 op) {
         return Iterator.scanLeft$(this, z, op);
      }

      /** @deprecated */
      public Iterator scanRight(final Object z, final Function2 op) {
         return Iterator.scanRight$(this, z, op);
      }

      public int indexWhere(final Function1 p, final int from) {
         return Iterator.indexWhere$(this, p, from);
      }

      public int indexWhere$default$2() {
         return Iterator.indexWhere$default$2$(this);
      }

      public int indexOf(final Object elem) {
         return Iterator.indexOf$(this, elem);
      }

      public int indexOf(final Object elem, final int from) {
         return Iterator.indexOf$(this, elem, from);
      }

      public final int length() {
         return Iterator.length$(this);
      }

      public boolean isEmpty() {
         return Iterator.isEmpty$(this);
      }

      public Iterator filter(final Function1 p) {
         return Iterator.filter$(this, p);
      }

      public Iterator filterNot(final Function1 p) {
         return Iterator.filterNot$(this, p);
      }

      public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
         return Iterator.filterImpl$(this, p, isFlipped);
      }

      public Iterator withFilter(final Function1 p) {
         return Iterator.withFilter$(this, p);
      }

      public Iterator collect(final PartialFunction pf) {
         return Iterator.collect$(this, pf);
      }

      public Iterator distinct() {
         return Iterator.distinct$(this);
      }

      public Iterator distinctBy(final Function1 f) {
         return Iterator.distinctBy$(this, f);
      }

      public Iterator map(final Function1 f) {
         return Iterator.map$(this, f);
      }

      public Iterator flatMap(final Function1 f) {
         return Iterator.flatMap$(this, f);
      }

      public Iterator flatten(final Function1 ev) {
         return Iterator.flatten$(this, ev);
      }

      public Iterator concat(final Function0 xs) {
         return Iterator.concat$(this, xs);
      }

      public final Iterator $plus$plus(final Function0 xs) {
         return Iterator.$plus$plus$(this, xs);
      }

      public Iterator take(final int n) {
         return Iterator.take$(this, n);
      }

      public Iterator takeWhile(final Function1 p) {
         return Iterator.takeWhile$(this, p);
      }

      public Iterator drop(final int n) {
         return Iterator.drop$(this, n);
      }

      public Iterator dropWhile(final Function1 p) {
         return Iterator.dropWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return Iterator.span$(this, p);
      }

      public Iterator slice(final int from, final int until) {
         return Iterator.slice$(this, from, until);
      }

      public Iterator sliceIterator(final int from, final int until) {
         return Iterator.sliceIterator$(this, from, until);
      }

      public Iterator zip(final IterableOnce that) {
         return Iterator.zip$(this, that);
      }

      public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
         return Iterator.zipAll$(this, that, thisElem, thatElem);
      }

      public Iterator zipWithIndex() {
         return Iterator.zipWithIndex$(this);
      }

      public boolean sameElements(final IterableOnce that) {
         return Iterator.sameElements$(this, that);
      }

      public Tuple2 duplicate() {
         return Iterator.duplicate$(this);
      }

      public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
         return Iterator.patch$(this, from, patchElems, replaced);
      }

      public Iterator tapEach(final Function1 f) {
         return Iterator.tapEach$(this, f);
      }

      public String toString() {
         return Iterator.toString$(this);
      }

      /** @deprecated */
      public Iterator seq() {
         return Iterator.seq$(this);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOnceOps.splitAt$(this, n);
      }

      public boolean isTraversableAgain() {
         return IterableOnceOps.isTraversableAgain$(this);
      }

      public void foreach(final Function1 f) {
         IterableOnceOps.foreach$(this, f);
      }

      public boolean forall(final Function1 p) {
         return IterableOnceOps.forall$(this, p);
      }

      public boolean exists(final Function1 p) {
         return IterableOnceOps.exists$(this, p);
      }

      public int count(final Function1 p) {
         return IterableOnceOps.count$(this, p);
      }

      public Option find(final Function1 p) {
         return IterableOnceOps.find$(this, p);
      }

      public Object foldLeft(final Object z, final Function2 op) {
         return IterableOnceOps.foldLeft$(this, z, op);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IterableOnceOps.foldRight$(this, z, op);
      }

      /** @deprecated */
      public final Object $div$colon(final Object z, final Function2 op) {
         return IterableOnceOps.$div$colon$(this, z, op);
      }

      /** @deprecated */
      public final Object $colon$bslash(final Object z, final Function2 op) {
         return IterableOnceOps.$colon$bslash$(this, z, op);
      }

      public Object fold(final Object z, final Function2 op) {
         return IterableOnceOps.fold$(this, z, op);
      }

      public Object reduce(final Function2 op) {
         return IterableOnceOps.reduce$(this, op);
      }

      public Option reduceOption(final Function2 op) {
         return IterableOnceOps.reduceOption$(this, op);
      }

      public Object reduceLeft(final Function2 op) {
         return IterableOnceOps.reduceLeft$(this, op);
      }

      public Object reduceRight(final Function2 op) {
         return IterableOnceOps.reduceRight$(this, op);
      }

      public Option reduceLeftOption(final Function2 op) {
         return IterableOnceOps.reduceLeftOption$(this, op);
      }

      public Option reduceRightOption(final Function2 op) {
         return IterableOnceOps.reduceRightOption$(this, op);
      }

      public boolean nonEmpty() {
         return IterableOnceOps.nonEmpty$(this);
      }

      public int size() {
         return IterableOnceOps.size$(this);
      }

      /** @deprecated */
      public final void copyToBuffer(final Buffer dest) {
         IterableOnceOps.copyToBuffer$(this, dest);
      }

      public int copyToArray(final Object xs) {
         return IterableOnceOps.copyToArray$(this, xs);
      }

      public int copyToArray(final Object xs, final int start) {
         return IterableOnceOps.copyToArray$(this, xs, start);
      }

      public int copyToArray(final Object xs, final int start, final int len) {
         return IterableOnceOps.copyToArray$(this, xs, start, len);
      }

      public Object sum(final Numeric num) {
         return IterableOnceOps.sum$(this, num);
      }

      public Object product(final Numeric num) {
         return IterableOnceOps.product$(this, num);
      }

      public Object min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      public Option minOption(final Ordering ord) {
         return IterableOnceOps.minOption$(this, ord);
      }

      public Object max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      public Option maxOption(final Ordering ord) {
         return IterableOnceOps.maxOption$(this, ord);
      }

      public Object maxBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxBy$(this, f, ord);
      }

      public Option maxByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.maxByOption$(this, f, ord);
      }

      public Object minBy(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minBy$(this, f, ord);
      }

      public Option minByOption(final Function1 f, final Ordering ord) {
         return IterableOnceOps.minByOption$(this, f, ord);
      }

      public Option collectFirst(final PartialFunction pf) {
         return IterableOnceOps.collectFirst$(this, pf);
      }

      /** @deprecated */
      public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
         return IterableOnceOps.aggregate$(this, z, seqop, combop);
      }

      public boolean corresponds(final IterableOnce that, final Function2 p) {
         return IterableOnceOps.corresponds$(this, that, p);
      }

      public final String mkString(final String start, final String sep, final String end) {
         return IterableOnceOps.mkString$(this, start, sep, end);
      }

      public final String mkString(final String sep) {
         return IterableOnceOps.mkString$(this, sep);
      }

      public final String mkString() {
         return IterableOnceOps.mkString$(this);
      }

      public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
         return IterableOnceOps.addString$(this, b, start, sep, end);
      }

      public final StringBuilder addString(final StringBuilder b, final String sep) {
         return IterableOnceOps.addString$(this, b, sep);
      }

      public final StringBuilder addString(final StringBuilder b) {
         return IterableOnceOps.addString$(this, b);
      }

      public Object to(final Factory factory) {
         return IterableOnceOps.to$(this, factory);
      }

      /** @deprecated */
      public final Iterator toIterator() {
         return IterableOnceOps.toIterator$(this);
      }

      public List toList() {
         return IterableOnceOps.toList$(this);
      }

      public Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public Map toMap(final scala..less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
      }

      public Seq toSeq() {
         return IterableOnceOps.toSeq$(this);
      }

      public IndexedSeq toIndexedSeq() {
         return IterableOnceOps.toIndexedSeq$(this);
      }

      /** @deprecated */
      public final Stream toStream() {
         return IterableOnceOps.toStream$(this);
      }

      public final Buffer toBuffer() {
         return IterableOnceOps.toBuffer$(this);
      }

      public Object toArray(final ClassTag evidence$2) {
         return IterableOnceOps.toArray$(this, evidence$2);
      }

      public Iterable reversed() {
         return IterableOnceOps.reversed$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IterableOnce.stepper$(this, shape);
      }

      public int knownSize() {
         return IterableOnce.knownSize$(this);
      }

      private Object nextObj() {
         return this.nextObj;
      }

      private void nextObj_$eq(final Object x$1) {
         this.nextObj = x$1;
      }

      private boolean eos() {
         return this.eos;
      }

      private void eos_$eq(final boolean x$1) {
         this.eos = x$1;
      }

      public boolean hasNext() {
         boolean var10000;
         if (this.nextObj() == null) {
            label29: {
               if (!this.eos()) {
                  this.nextObj_$eq(this.read());
                  if (this.hasNext()) {
                     break label29;
                  }
               } else if (false) {
                  break label29;
               }

               var10000 = false;
               return var10000;
            }
         }

         var10000 = true;
         return var10000;
      }

      public Object next() {
         if (this.hasNext()) {
            Object obj = this.nextObj();
            this.nextObj_$eq((Object)null);
            return obj;
         } else {
            return scala.package..MODULE$.Iterator().empty().next();
         }
      }

      public abstract Object read();

      public void handleTimingData() {
         long bootTime = this.stream.readLong();
         long initTime = this.stream.readLong();
         long finishTime = this.stream.readLong();
         long boot = bootTime - this.startTime;
         long init = initTime - bootTime;
         long finish = finishTime - initTime;
         long total = finishTime - this.startTime;
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Times: total = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TOTAL_TIME..MODULE$, BoxesRunTime.boxToLong(total))}))).$plus(this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"boot = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.BOOT_TIME..MODULE$, BoxesRunTime.boxToLong(boot))})))).$plus(this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"init = ", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INIT_TIME..MODULE$, BoxesRunTime.boxToLong(init))})))).$plus(this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"finish = ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FINISH_TIME..MODULE$, BoxesRunTime.boxToLong(finish))}))))));
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().metrics().get("pythonBootTime").foreach((x$18) -> {
            $anonfun$handleTimingData$2(boot, x$18);
            return BoxedUnit.UNIT;
         });
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().metrics().get("pythonInitTime").foreach((x$19) -> {
            $anonfun$handleTimingData$3(init, x$19);
            return BoxedUnit.UNIT;
         });
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().metrics().get("pythonTotalTime").foreach((x$20) -> {
            $anonfun$handleTimingData$4(total, x$20);
            return BoxedUnit.UNIT;
         });
         long memoryBytesSpilled = this.stream.readLong();
         long diskBytesSpilled = this.stream.readLong();
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context.taskMetrics().incMemoryBytesSpilled(memoryBytesSpilled);
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context.taskMetrics().incDiskBytesSpilled(diskBytesSpilled);
      }

      public PythonException handlePythonException() {
         String msg = PythonWorkerUtils$.MODULE$.readUTF(this.stream);
         return new PythonException(msg, (Throwable)this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer.exception().orNull(scala..less.colon.less..MODULE$.refl()));
      }

      public void handleEndOfDataSection() {
         PythonWorkerUtils$.MODULE$.receiveAccumulatorUpdates(this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().org$apache$spark$api$python$BasePythonRunner$$maybeAccumulator(), this.stream);
         if (this.stream.readInt() == SpecialLengths$.MODULE$.END_OF_STREAM() && this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().org$apache$spark$api$python$BasePythonRunner$$reuseWorker() && this.releasedOrClosed.compareAndSet(false, true)) {
            this.env.releasePythonWorker(this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().pythonExec(), this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().org$apache$spark$api$python$BasePythonRunner$$workerModule(), this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().org$apache$spark$api$python$BasePythonRunner$$daemonModule(), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().envVars()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), this.worker);
         }

         this.eos_$eq(true);
      }

      public PartialFunction handleException() {
         return this.handleException;
      }

      // $FF: synthetic method
      public BasePythonRunner org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$handleTimingData$2(final long boot$1, final AccumulatorV2 x$18) {
         x$18.add(BoxesRunTime.boxToLong(boot$1));
      }

      // $FF: synthetic method
      public static final void $anonfun$handleTimingData$3(final long init$1, final AccumulatorV2 x$19) {
         x$19.add(BoxesRunTime.boxToLong(init$1));
      }

      // $FF: synthetic method
      public static final void $anonfun$handleTimingData$4(final long total$1, final AccumulatorV2 x$20) {
         x$20.add(BoxesRunTime.boxToLong(total$1));
      }

      public ReaderIterator(final DataInputStream stream, final Writer writer, final long startTime, final SparkEnv env, final PythonWorker worker, final Option pid, final AtomicBoolean releasedOrClosed, final TaskContext context) {
         this.stream = stream;
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer = writer;
         this.startTime = startTime;
         this.env = env;
         this.worker = worker;
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid = pid;
         this.releasedOrClosed = releasedOrClosed;
         this.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context = context;
         if (BasePythonRunner.this == null) {
            throw null;
         } else {
            this.$outer = BasePythonRunner.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.eos = false;
            this.handleException = new Serializable() {
               private static final long serialVersionUID = 0L;
               // $FF: synthetic field
               private final ReaderIterator $outer;

               public final Object applyOrElse(final Throwable x1, final Function1 default) {
                  boolean var4 = false;
                  Exception var5 = null;
                  boolean var6 = false;
                  IOException var7 = null;
                  if (x1 instanceof Exception) {
                     var4 = true;
                     var5 = (Exception)x1;
                     if (this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context.isInterrupted()) {
                        this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().logDebug((Function0)(() -> "Exception thrown after task interruption"), var5);
                        throw new TaskKilledException((String)this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context.getKillReason().getOrElse(() -> "unknown reason"));
                     }
                  }

                  if (var4 && this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer.exception().isDefined()) {
                     this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().logError((Function0)(() -> "Python worker exited unexpectedly (crashed)"), var5);
                     this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().logError((Function0)(() -> "This may have been caused by a prior exception:"), (Throwable)this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer.exception().get());
                     throw (Throwable)this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer.exception().get();
                  } else {
                     if (x1 instanceof IOException) {
                        var6 = true;
                        var7 = (IOException)x1;
                        if (this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().faultHandlerEnabled() && this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid.isDefined() && Files.exists(BasePythonRunner$.MODULE$.faultHandlerLogPath(BoxesRunTime.unboxToInt(this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid.get())), new LinkOption[0])) {
                           Path path = BasePythonRunner$.MODULE$.faultHandlerLogPath(BoxesRunTime.unboxToInt(this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid.get()));
                           String error = String.join("\n", Files.readAllLines(path)) + "\n";
                           Files.deleteIfExists(path);
                           throw new SparkException("Python worker exited unexpectedly (crashed): " + error, var7);
                        }
                     }

                     if (var6 && !this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().faultHandlerEnabled()) {
                        throw new SparkException("Python worker exited unexpectedly (crashed). Consider setting 'spark.sql.execution.pyspark.udf.faulthandler.enabled' or'" + Python$.MODULE$.PYTHON_WORKER_FAULTHANLDER_ENABLED().key() + "' configuration to 'true' for the better Python traceback.", var7);
                     } else if (var6) {
                        throw new SparkException("Python worker exited unexpectedly (crashed)", var7);
                     } else {
                        return default.apply(x1);
                     }
                  }
               }

               public final boolean isDefinedAt(final Throwable x1) {
                  boolean var3 = false;
                  Object var4 = null;
                  boolean var5 = false;
                  Object var6 = null;
                  if (x1 instanceof Exception) {
                     var3 = true;
                     Exception var8 = (Exception)x1;
                     if (this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$context.isInterrupted()) {
                        return true;
                     }
                  }

                  if (var3 && this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$writer.exception().isDefined()) {
                     return true;
                  } else {
                     if (x1 instanceof IOException) {
                        var5 = true;
                        IOException var9 = (IOException)x1;
                        if (this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().faultHandlerEnabled() && this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid.isDefined() && Files.exists(BasePythonRunner$.MODULE$.faultHandlerLogPath(BoxesRunTime.unboxToInt(this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$pid.get())), new LinkOption[0])) {
                           return true;
                        }
                     }

                     if (var5 && !this.$outer.org$apache$spark$api$python$BasePythonRunner$ReaderIterator$$$outer().faultHandlerEnabled()) {
                        return true;
                     } else {
                        return var5;
                     }
                  }
               }

               public {
                  if (ReaderIterator.this == null) {
                     throw null;
                  } else {
                     this.$outer = ReaderIterator.this;
                  }
               }

               // $FF: synthetic method
               private static Object $deserializeLambda$(SerializedLambda var0) {
                  return Class.lambdaDeserialize<invokedynamic>(var0);
               }
            };
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class MonitorThread extends Thread {
      private final SparkEnv env;
      private final PythonWorker worker;
      private final TaskContext context;
      private final long taskKillTimeout;
      // $FF: synthetic field
      public final BasePythonRunner $outer;

      private long taskKillTimeout() {
         return this.taskKillTimeout;
      }

      private void monitorWorker() {
         while(!this.context.isInterrupted() && !this.context.isCompleted()) {
            Thread.sleep(2000L);
         }

         if (!this.context.isCompleted()) {
            Thread.sleep(this.taskKillTimeout());
            if (!this.context.isCompleted()) {
               try {
                  int var10000 = this.context.partitionId();
                  String taskName = var10000 + "." + this.context.attemptNumber() + " in stage " + this.context.stageId() + " (TID " + this.context.taskAttemptId() + ")";
                  this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Incomplete task ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_NAME..MODULE$, taskName)}))).$plus(this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"interrupted: Attempting to kill Python Worker"})))).log(scala.collection.immutable.Nil..MODULE$))));
                  this.env.destroyPythonWorker(this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().pythonExec(), this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().org$apache$spark$api$python$BasePythonRunner$$workerModule(), this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().org$apache$spark$api$python$BasePythonRunner$$daemonModule(), scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().envVars()).asScala().toMap(scala..less.colon.less..MODULE$.refl()), this.worker);
               } catch (Exception var3) {
                  this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().logError((Function0)(() -> "Exception when trying to kill worker"), var3);
               }

            }
         }
      }

      public void run() {
         try {
            this.monitorWorker();
         } finally {
            if (this.org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer().org$apache$spark$api$python$BasePythonRunner$$reuseWorker()) {
               Tuple2 key = new Tuple2(this.worker, BoxesRunTime.boxToLong(this.context.taskAttemptId()));
               PythonRunner$.MODULE$.runningMonitorThreads().remove(key);
            }

         }

      }

      // $FF: synthetic method
      public BasePythonRunner org$apache$spark$api$python$BasePythonRunner$MonitorThread$$$outer() {
         return this.$outer;
      }

      public MonitorThread(final SparkEnv env, final PythonWorker worker, final TaskContext context) {
         this.env = env;
         this.worker = worker;
         this.context = context;
         if (BasePythonRunner.this == null) {
            throw null;
         } else {
            this.$outer = BasePythonRunner.this;
            super("Worker Monitor for " + BasePythonRunner.this.pythonExec());
            this.taskKillTimeout = BoxesRunTime.unboxToLong(env.conf().get(Python$.MODULE$.PYTHON_TASK_KILL_TIMEOUT()));
            this.setDaemon(true);
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ReaderInputStream extends InputStream {
      private final PythonWorker worker;
      private final Writer writer;
      private final Option handle;
      private final long idleTimeoutSeconds;
      private Object writerIfbhThreadLocalValue;
      private final byte[] temp;
      private final DirectByteBufferOutputStream bufferStream;
      private ByteBuffer buffer;
      private boolean hasInput;
      private final long idleTimeoutMillis;
      private long lastFlushTime;
      // $FF: synthetic field
      public final BasePythonRunner $outer;

      public int read() {
         int n = this.read(this.temp);
         return n <= 0 ? -1 : this.temp[0] & 255;
      }

      public int read(final byte[] b, final int off, final int len) {
         ByteBuffer buf = ByteBuffer.wrap(b, off, len);
         int n = 0;

         while(n == 0) {
            long start = System.currentTimeMillis();
            int selected = this.worker.selector().select(this.idleTimeoutMillis);
            long end = System.currentTimeMillis();
            if (selected == 0 && this.idleTimeoutMillis > 0L && end - start >= this.idleTimeoutMillis) {
               this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Idle timeout reached for Python worker (timeout: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " seconds). "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PYTHON_WORKER_IDLE_TIMEOUT..MODULE$, BoxesRunTime.boxToLong(this.idleTimeoutSeconds))})))).$plus(this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"No data received from the worker process: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(BasePythonRunner$.MODULE$.pythonWorkerStatusMessageWithContext(this.handle, this.worker, this.hasInput || this.buffer.hasRemaining()))));
            }

            if (this.worker.selectionKey().isReadable()) {
               n = this.worker.channel().read(buf);
            }

            if (this.worker.selectionKey().isWritable()) {
               Object mainIfbhThreadLocalValue = InputFileBlockHolder$.MODULE$.getThreadLocalValue();
               if (this.writerIfbhThreadLocalValue == null) {
                  try {
                     this.writeAdditionalInputToPythonWorker();
                  } finally {
                     Object maybeNewIfbh = InputFileBlockHolder$.MODULE$.getThreadLocalValue();
                     if (maybeNewIfbh != mainIfbhThreadLocalValue) {
                        this.writerIfbhThreadLocalValue = maybeNewIfbh;
                        InputFileBlockHolder$.MODULE$.setThreadLocalValue(mainIfbhThreadLocalValue);
                     }

                  }
               } else {
                  try {
                     InputFileBlockHolder$.MODULE$.setThreadLocalValue(this.writerIfbhThreadLocalValue);

                     try {
                        this.writeAdditionalInputToPythonWorker();
                     } finally {
                        this.writerIfbhThreadLocalValue = InputFileBlockHolder$.MODULE$.getThreadLocalValue();
                     }
                  } finally {
                     InputFileBlockHolder$.MODULE$.setThreadLocalValue(mainIfbhThreadLocalValue);
                  }
               }
            }
         }

         return n;
      }

      private long lastFlushTime() {
         return this.lastFlushTime;
      }

      private void lastFlushTime_$eq(final long x$1) {
         this.lastFlushTime = x$1;
      }

      private boolean shouldFlush() {
         if (!this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().timelyFlushEnabled()) {
            return false;
         } else {
            long currentTime = System.nanoTime();
            if (currentTime - this.lastFlushTime() > this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().timelyFlushTimeoutNanos()) {
               this.lastFlushTime_$eq(currentTime);
               return this.bufferStream.size() > 0;
            } else {
               return false;
            }
         }
      }

      private void writeAdditionalInputToPythonWorker() {
         boolean acceptsInput = true;

         while(acceptsInput && (this.hasInput || this.buffer.hasRemaining())) {
            if (!this.buffer.hasRemaining() && this.hasInput) {
               this.bufferStream.reset();
               this.buffer = null;

               DataOutputStream dataOut;
               for(dataOut = new DataOutputStream(this.bufferStream); this.bufferStream.size() < this.org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer().bufferSize() && this.hasInput && !this.shouldFlush(); this.hasInput = this.writer.writeNextInputToStream(dataOut)) {
               }

               if (!this.hasInput) {
                  this.writer.close(dataOut);
               }

               this.buffer = this.bufferStream.toByteBuffer();
            }

            while(this.buffer.hasRemaining() && acceptsInput) {
               int n = this.worker.channel().write(this.buffer);
               acceptsInput = n > 0;
            }
         }

         if (!this.hasInput && !this.buffer.hasRemaining()) {
            this.worker.selectionKey().interestOps(1);
            this.bufferStream.close();
         }
      }

      // $FF: synthetic method
      public BasePythonRunner org$apache$spark$api$python$BasePythonRunner$ReaderInputStream$$$outer() {
         return this.$outer;
      }

      public ReaderInputStream(final PythonWorker worker, final Writer writer, final Option handle, final long idleTimeoutSeconds) {
         this.worker = worker;
         this.writer = writer;
         this.handle = handle;
         this.idleTimeoutSeconds = idleTimeoutSeconds;
         if (BasePythonRunner.this == null) {
            throw null;
         } else {
            this.$outer = BasePythonRunner.this;
            super();
            this.writerIfbhThreadLocalValue = null;
            this.temp = new byte[1];
            this.bufferStream = new DirectByteBufferOutputStream();
            this.hasInput = true;
            writer.open(new DataOutputStream(this.bufferStream));
            this.buffer = this.bufferStream.toByteBuffer();
            this.idleTimeoutMillis = TimeUnit.SECONDS.toMillis(idleTimeoutSeconds);
            this.lastFlushTime = System.nanoTime();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
