package org.apache.spark.rdd;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.BlockMissingException;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.InputSplitWithLocationInfo;
import org.apache.hadoop.mapred.InvalidInputException;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.CombineFileSplit;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.deploy.SparkHadoopUtil$;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.executor.InputMetrics;
import org.apache.spark.internal.MDC;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.NextIterator;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.ShutdownHookManager$;
import org.apache.spark.util.Utils$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\r=e\u0001B\u001f?\u0001\u001dC\u0001b\u001a\u0001\u0003\u0002\u0003\u0006I\u0001\u001b\u0005\tY\u0002\u0011\t\u0011)A\u0005[\"A\u0011\u0010\u0001B\u0001B\u0003%!\u0010\u0003\u0006\u0002\u0018\u0001\u0011\t\u0011)A\u0005\u00033A!\"a\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA!\u0011)\t\u0019\u0005\u0001B\u0001B\u0003%\u0011Q\t\u0005\u000b\u0003\u000f\u0002!\u0011!Q\u0001\n\u0005%\u0003BCA(\u0001\t\u0005\t\u0015!\u0003\u0002R!Q\u0011q\u000b\u0001\u0003\u0002\u0003\u0006I!!\u0015\t\u000f\u0005e\u0003\u0001\"\u0001\u0002\\!9\u0011\u0011\f\u0001\u0005\u0002\u0005e\u0004bBA-\u0001\u0011\u0005\u00111\u0013\u0005\n\u0003[\u0003!\u0019!C\t\u0003_C\u0001\"a.\u0001A\u0003%\u0011\u0011\u0017\u0005\n\u0003s\u0003!\u0019!C\t\u0003_C\u0001\"a/\u0001A\u0003%\u0011\u0011\u0017\u0005\n\u0003{\u0003!\u0019!C\u0005\u0003\u007fC\u0001\"a4\u0001A\u0003%\u0011\u0011\u0019\u0005\n\u0003#\u0004!\u0019!C\u0005\u0003'D\u0001\"!6\u0001A\u0003%\u0011\u0011\u000b\u0005\n\u0003/\u0004!\u0019!C\u0005\u0003'D\u0001\"!7\u0001A\u0003%\u0011\u0011\u000b\u0005\b\u00037\u0004A\u0011CAo\u0011\u001d\ty\u000e\u0001C\t\u0003CDq!!:\u0001\t\u0003\n9\u000fC\u0004\u0002v\u0002!\t%a>\t\u000f\t5\u0001\u0001\"\u0001\u0003\u0010!I!1\r\u0001\u0012\u0002\u0013\u0005!Q\r\u0005\b\u0005{\u0002A\u0011\tB@\u0011\u001d\u0011Y\t\u0001C!\u0005\u001bCqAa$\u0001\t\u0003\u0012\t\nC\u0004\u0003&\u0002!\tAa*\b\u0011\tUf\b#\u0001A\u0005o3q!\u0010 \t\u0002\u0001\u0013I\fC\u0004\u0002Z\t\"\tA!4\t\u0013\t='E1A\u0005\u0002\tE\u0007\u0002\u0003BpE\u0001\u0006IAa5\t\u0013\t\u0005(E1A\u0005\n\t\r\b\u0002\u0003B{E\u0001\u0006IA!:\t\u000f\t](\u0005\"\u0001\u0003z\"9!q \u0012\u0005\n\r\u0005\u0001bBB\u0005E\u0011\u000511\u0002\u0004\b\u0007?\u0011\u0003\u0001QB\u0011\u0011)\u0019Yc\u000bB\u0001B\u0003%1Q\u0006\u0005\u000b\u0005_Y#\u0011!Q\u0001\n\rU\u0002B\u0003B*W\t\u0005\t\u0015!\u0003\u0002R!Q11H\u0016\u0003\u0004\u0003\u0006Ya!\u0010\t\u0015\r}2FaA!\u0002\u0017\u0019\t\u0005C\u0004\u0002Z-\"\taa\u0011\t\u0013\rU3F1A\u0005B\r]\u0003\u0002CB1W\u0001\u0006Ia!\u0017\t\u000f\u0005\u00158\u0006\"\u0011\u0002h\"9\u0011Q_\u0016\u0005B\r\rtACB5E\u0005\u0005\t\u0012\u0001!\u0004l\u0019Q1q\u0004\u0012\u0002\u0002#\u0005\u0001i!\u001c\t\u000f\u0005es\u0007\"\u0001\u0004p!I1\u0011O\u001c\u0012\u0002\u0013\u000511\u000f\u0005\n\u0007s:\u0014\u0011!C\u0005\u0007wB\u0001b! #\t\u0003\u00015q\u0010\u0005\n\u0007s\u0012\u0013\u0011!C\u0005\u0007w\u0012\u0011\u0002S1e_>\u0004(\u000b\u0012#\u000b\u0005}\u0002\u0015a\u0001:eI*\u0011\u0011IQ\u0001\u0006gB\f'o\u001b\u0006\u0003\u0007\u0012\u000ba!\u00199bG\",'\"A#\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0007!+vlE\u0002\u0001\u0013\u0006\u00042AS&N\u001b\u0005q\u0014B\u0001'?\u0005\r\u0011F\t\u0012\t\u0005\u001dF\u001bf,D\u0001P\u0015\u0005\u0001\u0016!B:dC2\f\u0017B\u0001*P\u0005\u0019!V\u000f\u001d7feA\u0011A+\u0016\u0007\u0001\t\u00151\u0006A1\u0001X\u0005\u0005Y\u0015C\u0001-\\!\tq\u0015,\u0003\u0002[\u001f\n9aj\u001c;iS:<\u0007C\u0001(]\u0013\tivJA\u0002B]f\u0004\"\u0001V0\u0005\u000b\u0001\u0004!\u0019A,\u0003\u0003Y\u0003\"AY3\u000e\u0003\rT!\u0001\u001a!\u0002\u0011%tG/\u001a:oC2L!AZ2\u0003\u000f1{wmZ5oO\u0006\u00111o\u0019\t\u0003S*l\u0011\u0001Q\u0005\u0003W\u0002\u0013Ab\u00159be.\u001cuN\u001c;fqR\fqB\u0019:pC\u0012\u001c\u0017m\u001d;fI\u000e{gN\u001a\t\u0004]F\u001cX\"A8\u000b\u0005A\u0004\u0015!\u00032s_\u0006$7-Y:u\u0013\t\u0011xNA\u0005Ce>\fGmY1tiB\u0011Ao^\u0007\u0002k*\u0011a\u000fQ\u0001\u0005kRLG.\u0003\u0002yk\nI2+\u001a:jC2L'0\u00192mK\u000e{gNZ5hkJ\fG/[8o\u0003]Ig.\u001b;M_\u000e\fGNS8c\u0007>tgMR;oG>\u0003H\u000fE\u0002OwvL!\u0001`(\u0003\r=\u0003H/[8o!\u0019qe0!\u0001\u0002\u0012%\u0011qp\u0014\u0002\n\rVt7\r^5p]F\u0002B!a\u0001\u0002\u000e5\u0011\u0011Q\u0001\u0006\u0005\u0003\u000f\tI!\u0001\u0004nCB\u0014X\r\u001a\u0006\u0004\u0003\u0017\u0011\u0015A\u00025bI>|\u0007/\u0003\u0003\u0002\u0010\u0005\u0015!a\u0002&pE\u000e{gN\u001a\t\u0004\u001d\u0006M\u0011bAA\u000b\u001f\n!QK\\5u\u0003AIg\u000e];u\r>\u0014X.\u0019;DY\u0006\u001c8\u000f\r\u0003\u0002\u001c\u0005M\u0002CBA\u000f\u0003W\t\tD\u0004\u0003\u0002 \u0005\u001d\u0002cAA\u0011\u001f6\u0011\u00111\u0005\u0006\u0004\u0003K1\u0015A\u0002\u001fs_>$h(C\u0002\u0002*=\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\u0017\u0003_\u0011Qa\u00117bgNT1!!\u000bP!\r!\u00161\u0007\u0003\f\u0003k!\u0011\u0011!A\u0001\u0006\u0003\t9DA\u0002`IE\n2\u0001WA\u001d!\u0019\t\u0019!a\u000fT=&!\u0011QHA\u0003\u0005-Ie\u000e];u\r>\u0014X.\u0019;\u0002\u0011-,\u0017p\u00117bgN\u0004R!!\b\u0002,M\u000b!B^1mk\u0016\u001cE.Y:t!\u0015\ti\"a\u000b_\u00035i\u0017N\u001c)beRLG/[8ogB\u0019a*a\u0013\n\u0007\u00055sJA\u0002J]R\f!#[4o_J,7i\u001c:skB$h)\u001b7fgB\u0019a*a\u0015\n\u0007\u0005UsJA\u0004C_>dW-\u00198\u0002%%<gn\u001c:f\u001b&\u001c8/\u001b8h\r&dWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015)\u0005u\u0013qLA1\u0003G\n)'a\u001c\u0002r\u0005M\u0014QOA<!\u0011Q\u0005a\u00150\t\u000b\u001dT\u0001\u0019\u00015\t\u000b1T\u0001\u0019A7\t\u000beT\u0001\u0019\u0001>\t\u000f\u0005]!\u00021\u0001\u0002hA\"\u0011\u0011NA7!\u0019\ti\"a\u000b\u0002lA\u0019A+!\u001c\u0005\u0019\u0005U\u0012QMA\u0001\u0002\u0003\u0015\t!a\u000e\t\u000f\u0005}\"\u00021\u0001\u0002B!9\u00111\t\u0006A\u0002\u0005\u0015\u0003bBA$\u0015\u0001\u0007\u0011\u0011\n\u0005\b\u0003\u001fR\u0001\u0019AA)\u0011\u001d\t9F\u0003a\u0001\u0003#\"\u0002#!\u0018\u0002|\u0005u\u0014qPAA\u0003\u001b\u000by)!%\t\u000b\u001d\\\u0001\u0019\u00015\t\u000b1\\\u0001\u0019A7\t\u000be\\\u0001\u0019\u0001>\t\u000f\u0005]1\u00021\u0001\u0002\u0004B\"\u0011QQAE!\u0019\ti\"a\u000b\u0002\bB\u0019A+!#\u0005\u0019\u0005-\u0015\u0011QA\u0001\u0002\u0003\u0015\t!a\u000e\u0003\u0007}##\u0007C\u0004\u0002@-\u0001\r!!\u0011\t\u000f\u0005\r3\u00021\u0001\u0002F!9\u0011qI\u0006A\u0002\u0005%CCDA/\u0003+\u000b9*a'\u0002(\u0006%\u00161\u0016\u0005\u0006O2\u0001\r\u0001\u001b\u0005\b\u00033c\u0001\u0019AA\u0001\u0003\u0011\u0019wN\u001c4\t\u000f\u0005]A\u00021\u0001\u0002\u001eB\"\u0011qTAR!\u0019\ti\"a\u000b\u0002\"B\u0019A+a)\u0005\u0019\u0005\u0015\u00161TA\u0001\u0002\u0003\u0015\t!a\u000e\u0003\u0007}#3\u0007C\u0004\u0002@1\u0001\r!!\u0011\t\u000f\u0005\rC\u00021\u0001\u0002F!9\u0011q\t\u0007A\u0002\u0005%\u0013a\u00046pE\u000e{gNZ\"bG\",7*Z=\u0016\u0005\u0005E\u0006\u0003BA\u000f\u0003gKA!!.\u00020\t11\u000b\u001e:j]\u001e\f\u0001C[8c\u0007>tgmQ1dQ\u0016\\U-\u001f\u0011\u0002'%t\u0007/\u001e;G_Jl\u0017\r^\"bG\",7*Z=\u0002)%t\u0007/\u001e;G_Jl\u0017\r^\"bG\",7*Z=!\u0003)\u0019'/Z1uKRKW.Z\u000b\u0003\u0003\u0003\u0004B!a1\u0002L6\u0011\u0011Q\u0019\u0006\u0004m\u0006\u001d'BAAe\u0003\u0011Q\u0017M^1\n\t\u00055\u0017Q\u0019\u0002\u0005\t\u0006$X-A\u0006de\u0016\fG/\u001a+j[\u0016\u0004\u0013AE:i_VdGm\u00117p]\u0016TuNY\"p]\u001a,\"!!\u0015\u0002'MDw.\u001e7e\u00072|g.\u001a&pE\u000e{gN\u001a\u0011\u0002#%<gn\u001c:f\u000b6\u0004H/_*qY&$8/\u0001\njO:|'/Z#naRL8\u000b\u001d7jiN\u0004\u0013AC4fi*{'mQ8oMR\u0011\u0011\u0011A\u0001\u000fO\u0016$\u0018J\u001c9vi\u001a{'/\\1u)\u0011\tI$a9\t\u000f\u0005e\u0005\u00041\u0001\u0002\u0002\u0005iq-\u001a;QCJ$\u0018\u000e^5p]N,\"!!;\u0011\u000b9\u000bY/a<\n\u0007\u00055xJA\u0003BeJ\f\u0017\u0010E\u0002j\u0003cL1!a=A\u0005%\u0001\u0016M\u001d;ji&|g.A\u0004d_6\u0004X\u000f^3\u0015\r\u0005e\u0018q B\u0002!\u0011I\u00171`'\n\u0007\u0005u\bIA\u000bJ]R,'O];qi&\u0014G.Z%uKJ\fGo\u001c:\t\u000f\t\u0005!\u00041\u0001\u0002p\u0006AA\u000f[3Ta2LG\u000fC\u0004\u0003\u0006i\u0001\rAa\u0002\u0002\u000f\r|g\u000e^3yiB\u0019\u0011N!\u0003\n\u0007\t-\u0001IA\u0006UCN\\7i\u001c8uKb$\u0018aG7baB\u000b'\u000f^5uS>t7oV5uQ&s\u0007/\u001e;Ta2LG/\u0006\u0003\u0003\u0012\teAC\u0002B\n\u0005[\u0011\t\u0006\u0006\u0003\u0003\u0016\tu\u0001\u0003\u0002&L\u0005/\u00012\u0001\u0016B\r\t\u0019\u0011Yb\u0007b\u0001/\n\tQ\u000bC\u0005\u0003 m\t\t\u0011q\u0001\u0003\"\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\r\t\r\"\u0011\u0006B\f\u001b\t\u0011)CC\u0002\u0003(=\u000bqA]3gY\u0016\u001cG/\u0003\u0003\u0003,\t\u0015\"\u0001C\"mCN\u001cH+Y4\t\u000f\t=2\u00041\u0001\u00032\u0005\ta\rE\u0005O\u0005g\u00119D!\u0010\u0003P%\u0019!QG(\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004\u0003BA\u0002\u0005sIAAa\u000f\u0002\u0006\tQ\u0011J\u001c9viN\u0003H.\u001b;\u0011\u000b\t}\"\u0011J'\u000f\t\t\u0005#Q\t\b\u0005\u0003C\u0011\u0019%C\u0001Q\u0013\r\u00119eT\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\u0011YE!\u0014\u0003\u0011%#XM]1u_JT1Aa\u0012P!\u0019\u0011yD!\u0013\u0003\u0018!I!1K\u000e\u0011\u0002\u0003\u0007\u0011\u0011K\u0001\u0016aJ,7/\u001a:wKN\u0004\u0016M\u001d;ji&|g.\u001b8hQ\rY\"q\u000b\t\u0005\u00053\u0012y&\u0004\u0002\u0003\\)\u0019!Q\f!\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003b\tm#\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017!J7baB\u000b'\u000f^5uS>t7oV5uQ&s\u0007/\u001e;Ta2LG\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u00119Ga\u001f\u0016\u0005\t%$\u0006BA)\u0005WZ#A!\u001c\u0011\t\t=$qO\u0007\u0003\u0005cRAAa\u001d\u0003v\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005;z\u0015\u0002\u0002B=\u0005c\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0019\u0011Y\u0002\bb\u0001/\u0006)r-\u001a;Qe\u00164WM\u001d:fI2{7-\u0019;j_:\u001cH\u0003\u0002BA\u0005\u000f\u0003bAa\u0010\u0003\u0004\u0006E\u0016\u0002\u0002BC\u0005\u001b\u00121aU3r\u0011\u001d\u0011I)\ba\u0001\u0003_\fQa\u001d9mSR\f!b\u00195fG.\u0004x.\u001b8u)\t\t\t\"A\u0004qKJ\u001c\u0018n\u001d;\u0015\t\tM%QS\u0007\u0002\u0001!9!qS\u0010A\u0002\te\u0015\u0001D:u_J\fw-\u001a'fm\u0016d\u0007\u0003\u0002BN\u0005Ck!A!(\u000b\u0007\t}\u0005)A\u0004ti>\u0014\u0018mZ3\n\t\t\r&Q\u0014\u0002\r'R|'/Y4f\u0019\u00164X\r\\\u0001\bO\u0016$8i\u001c8g+\t\u0011I\u000b\u0005\u0003\u0003,\n=VB\u0001BW\u0015\u0011\tI*!\u0003\n\t\tE&Q\u0016\u0002\u000e\u0007>tg-[4ve\u0006$\u0018n\u001c8)\u0007\u0001\u00119&A\u0005IC\u0012|w\u000e\u001d*E\tB\u0011!JI\n\u0007E\tm\u0016M!1\u0011\u00079\u0013i,C\u0002\u0003@>\u0013a!\u00118z%\u00164\u0007\u0003\u0002Bb\u0005\u0013l!A!2\u000b\t\t\u001d\u0017qY\u0001\u0003S>LAAa3\u0003F\na1+\u001a:jC2L'0\u00192mKR\u0011!qW\u0001!\u0007>se)S$V%\u0006#\u0016j\u0014(`\u0013:\u001bF+\u0011(U\u0013\u0006#\u0016j\u0014(`\u0019>\u001b5*\u0006\u0002\u0003TB!!Q\u001bBn\u001b\t\u00119N\u0003\u0003\u0003Z\u0006\u001d\u0017\u0001\u00027b]\u001eLAA!8\u0003X\n1qJ\u00196fGR\f\u0011eQ(O\r&;UKU!U\u0013>su,\u0013(T)\u0006sE+S!U\u0013>su\fT(D\u0017\u0002\n1\u0003R!U\u000b~#\u0016*T#`\r>\u0013V*\u0011+U\u000bJ+\"A!:\u0011\t\t\u001d(\u0011_\u0007\u0003\u0005STAAa;\u0003n\u00061am\u001c:nCRTAAa<\u0002H\u0006!A/[7f\u0013\u0011\u0011\u0019P!;\u0003#\u0011\u000bG/\u001a+j[\u00164uN]7biR,'/\u0001\u000bE\u0003R+u\fV%N\u000b~3uJU'B)R+%\u000bI\u0001\u0012O\u0016$8)Y2iK\u0012lU\r^1eCR\fG\u0003\u0002B^\u0005wDqA!@)\u0001\u0004\t\t,A\u0002lKf\f\u0011\u0003];u\u0007\u0006\u001c\u0007.\u001a3NKR\fG-\u0019;b)\u0019\t\tba\u0001\u0004\u0006!9!Q`\u0015A\u0002\u0005E\u0006bBB\u0004S\u0001\u0007!1X\u0001\u0006m\u0006dW/Z\u0001\u0016C\u0012$Gj\\2bY\u000e{gNZ5hkJ\fG/[8o)1\t\tb!\u0004\u0004\u0012\rU1\u0011DB\u000f\u0011\u001d\u0019yA\u000ba\u0001\u0003c\u000bAB[8c)J\f7m[3s\u0013\u0012Dqaa\u0005+\u0001\u0004\tI%A\u0003k_\nLE\rC\u0004\u0004\u0018)\u0002\r!!\u0013\u0002\u000fM\u0004H.\u001b;JI\"911\u0004\u0016A\u0002\u0005%\u0013!C1ui\u0016l\u0007\u000f^%e\u0011\u001d\tIJ\u000ba\u0001\u0003\u0003\u0011q\u0004S1e_>\u0004X*\u00199QCJ$\u0018\u000e^5p]N<\u0016\u000e\u001e5Ta2LGO\u0015#E+\u0019\u0019\u0019c!\u000b\u00042M\u00191f!\n\u0011\t)[5q\u0005\t\u0004)\u000e%BA\u0002B\u000eW\t\u0007q+\u0001\u0003qe\u00164\b\u0003\u0002&L\u0007_\u00012\u0001VB\u0019\t\u0019\u0019\u0019d\u000bb\u0001/\n\tA\u000bE\u0005O\u0005g\u00119da\u000e\u0004:A1!q\bB%\u0007_\u0001bAa\u0010\u0003J\r\u001d\u0012AC3wS\u0012,gnY3%eA1!1\u0005B\u0015\u0007O\t!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\u0011\u0019C!\u000b\u00040QA1QIB(\u0007#\u001a\u0019\u0006\u0006\u0004\u0004H\r-3Q\n\t\b\u0007\u0013Z3qEB\u0018\u001b\u0005\u0011\u0003bBB\u001ec\u0001\u000f1Q\b\u0005\b\u0007\u007f\t\u00049AB!\u0011\u001d\u0019Y#\ra\u0001\u0007[AqAa\f2\u0001\u0004\u0019)\u0004C\u0005\u0003TE\u0002\n\u00111\u0001\u0002R\u0005Y\u0001/\u0019:uSRLwN\\3s+\t\u0019I\u0006\u0005\u0003Ow\u000em\u0003cA5\u0004^%\u00191q\f!\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\ra\u0006\u0014H/\u001b;j_:,'\u000f\t\u000b\u0007\u0007s\u0019)ga\u001a\t\u000f\t%U\u00071\u0001\u0002p\"9!QA\u001bA\u0002\t\u001d\u0011a\b%bI>|\u0007/T1q!\u0006\u0014H/\u001b;j_:\u001cx+\u001b;i'Bd\u0017\u000e\u001e*E\tB\u00191\u0011J\u001c\u0014\u000b]\u0012YL!1\u0015\u0005\r-\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0006\u0004\u0003h\rU4q\u000f\u0003\u0007\u00057I$\u0019A,\u0005\r\rM\u0012H1\u0001X\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\u0011\u0019.\u0001\rd_:4XM\u001d;Ta2LG\u000fT8dCRLwN\\%oM>$Ba!!\u0004\u0004B!aj\u001fBA\u0011\u001d\u0019)i\u000fa\u0001\u0007\u000f\u000bQ!\u001b8g_N\u0004RATAv\u0007\u0013\u0003B!a\u0001\u0004\f&!1QRA\u0003\u0005E\u0019\u0006\u000f\\5u\u0019>\u001c\u0017\r^5p]&sgm\u001c"
)
public class HadoopRDD extends RDD {
   private final Broadcast broadcastedConf;
   private final Option initLocalJobConfFuncOpt;
   private final Class inputFormatClass;
   private final int minPartitions;
   public final boolean org$apache$spark$rdd$HadoopRDD$$ignoreCorruptFiles;
   public final boolean org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles;
   private final String jobConfCacheKey;
   private final String inputFormatCacheKey;
   private final Date org$apache$spark$rdd$HadoopRDD$$createTime;
   private final boolean shouldCloneJobConf;
   private final boolean ignoreEmptySplits;

   public static void addLocalConfiguration(final String jobTrackerId, final int jobId, final int splitId, final int attemptId, final JobConf conf) {
      HadoopRDD$.MODULE$.addLocalConfiguration(jobTrackerId, jobId, splitId, attemptId, conf);
   }

   public static Object getCachedMetadata(final String key) {
      return HadoopRDD$.MODULE$.getCachedMetadata(key);
   }

   public static Object CONFIGURATION_INSTANTIATION_LOCK() {
      return HadoopRDD$.MODULE$.CONFIGURATION_INSTANTIATION_LOCK();
   }

   public String jobConfCacheKey() {
      return this.jobConfCacheKey;
   }

   public String inputFormatCacheKey() {
      return this.inputFormatCacheKey;
   }

   public Date org$apache$spark$rdd$HadoopRDD$$createTime() {
      return this.org$apache$spark$rdd$HadoopRDD$$createTime;
   }

   private boolean shouldCloneJobConf() {
      return this.shouldCloneJobConf;
   }

   private boolean ignoreEmptySplits() {
      return this.ignoreEmptySplits;
   }

   public JobConf getJobConf() {
      Configuration conf = ((SerializableConfiguration)this.broadcastedConf.value()).value();
      if (this.shouldCloneJobConf()) {
         synchronized(HadoopRDD$.MODULE$.CONFIGURATION_INSTANTIATION_LOCK()){}

         JobConf var4;
         try {
            this.logDebug(() -> "Cloning Hadoop Configuration");
            JobConf newJobConf = new JobConf(conf);
            if (!(conf instanceof JobConf)) {
               this.initLocalJobConfFuncOpt.foreach((f) -> {
                  $anonfun$getJobConf$2(newJobConf, f);
                  return BoxedUnit.UNIT;
               });
            }

            var4 = newJobConf;
         } catch (Throwable var9) {
            throw var9;
         }

         return var4;
      } else if (conf instanceof JobConf) {
         JobConf var7 = (JobConf)conf;
         this.logDebug(() -> "Re-using user-broadcasted JobConf");
         return var7;
      } else {
         return (JobConf).MODULE$.apply(HadoopRDD$.MODULE$.getCachedMetadata(this.jobConfCacheKey())).map((confx) -> {
            this.logDebug(() -> "Re-using cached JobConf");
            return (JobConf)confx;
         }).getOrElse(() -> {
            synchronized(HadoopRDD$.MODULE$.CONFIGURATION_INSTANTIATION_LOCK()){}

            JobConf var3;
            try {
               this.logDebug(() -> "Creating new JobConf and caching it for later re-use");
               JobConf newJobConf = new JobConf(conf);
               this.initLocalJobConfFuncOpt.foreach((f) -> {
                  $anonfun$getJobConf$8(newJobConf, f);
                  return BoxedUnit.UNIT;
               });
               HadoopRDD$.MODULE$.org$apache$spark$rdd$HadoopRDD$$putCachedMetadata(this.jobConfCacheKey(), newJobConf);
               var3 = newJobConf;
            } catch (Throwable var6) {
               throw var6;
            }

            return var3;
         });
      }
   }

   public InputFormat getInputFormat(final JobConf conf) {
      InputFormat newInputFormat = (InputFormat)ReflectionUtils.newInstance(this.inputFormatClass, conf);
      if (newInputFormat instanceof Configurable) {
         ((Configurable)newInputFormat).setConf(conf);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      return newInputFormat;
   }

   public Partition[] getPartitions() {
      JobConf jobConf = this.getJobConf();
      SparkHadoopUtil$.MODULE$.get().addCredentials(jobConf);

      Partition[] var10000;
      try {
         InputSplit[] allInputSplits = this.getInputFormat(jobConf).getSplits(jobConf, this.minPartitions);
         InputSplit[] inputSplits = this.ignoreEmptySplits() ? (InputSplit[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])allInputSplits), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$1(x$1))) : allInputSplits;
         if (inputSplits.length == 1 && inputSplits[0] instanceof FileSplit) {
            FileSplit fileSplit = (FileSplit)inputSplits[0];
            Path path = fileSplit.getPath();
            if (fileSplit.getLength() > BoxesRunTime.unboxToLong(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.IO_WARNING_LARGEFILETHRESHOLD()))) {
               CompressionCodecFactory codecFactory = new CompressionCodecFactory(jobConf);
               if (Utils$.MODULE$.isFileSplittable(path, codecFactory)) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Loading one large file ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path.toString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with only one partition, "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"we can increase partition numbers for improving performance."})))).log(scala.collection.immutable.Nil..MODULE$))));
               } else {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Loading one large unsplittable file ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, path.toString())}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"with only one "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"partition, because the file is compressed by unsplittable compression codec."})))).log(scala.collection.immutable.Nil..MODULE$))));
               }
            }
         }

         Partition[] array = new Partition[scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputSplits))];
         scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), scala.collection.ArrayOps..MODULE$.size$extension(scala.Predef..MODULE$.refArrayOps((Object[])inputSplits))).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> array[i] = new HadoopPartition(this.id(), i, inputSplits[i]));
         var10000 = array;
      } catch (Throwable var14) {
         if (var14 instanceof InvalidInputException var11) {
            if (this.org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles) {
               this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, jobConf.get("mapreduce.input.fileinputformat.inputdir"))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"doesn't exist and no partitions returned from this path."})))).log(scala.collection.immutable.Nil..MODULE$))), var11);
               var10000 = (Partition[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
               return var10000;
            }
         }

         if (var14 instanceof IOException var12) {
            if (var12.getMessage().startsWith("Not a file:")) {
               String path = ((String[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])var12.getMessage().split(":")), (x$2) -> x$2.trim(), scala.reflect.ClassTag..MODULE$.apply(String.class)))[2];
               throw SparkCoreErrors$.MODULE$.pathNotSupportedError(path);
            }
         }

         throw var14;
      }

      return var10000;
   }

   public InterruptibleIterator compute(final Partition theSplit, final TaskContext context) {
      NextIterator iter = new NextIterator(context, theSplit) {
         private final HadoopPartition split;
         private final JobConf jobConf;
         private final InputMetrics inputMetrics;
         private final long existingBytesRead;
         private final Option getBytesReadCallback;
         private RecordReader reader;
         private final InputFormat inputFormat;
         private final Object key;
         private final Object value;
         // $FF: synthetic field
         private final HadoopRDD $outer;

         private HadoopPartition split() {
            return this.split;
         }

         private JobConf jobConf() {
            return this.jobConf;
         }

         private InputMetrics inputMetrics() {
            return this.inputMetrics;
         }

         private long existingBytesRead() {
            return this.existingBytesRead;
         }

         private Option getBytesReadCallback() {
            return this.getBytesReadCallback;
         }

         private void updateBytesRead() {
            this.getBytesReadCallback().foreach((getBytesRead) -> {
               $anonfun$updateBytesRead$1(this, getBytesRead);
               return BoxedUnit.UNIT;
            });
         }

         private RecordReader reader() {
            return this.reader;
         }

         private void reader_$eq(final RecordReader x$1) {
            this.reader = x$1;
         }

         private InputFormat inputFormat() {
            return this.inputFormat;
         }

         private Object key() {
            return this.key;
         }

         private Object value() {
            return this.value;
         }

         public Tuple2 getNext() {
            try {
               this.finished_$eq(!this.reader().next(this.key(), this.value()));
            } catch (Throwable var8) {
               label73: {
                  boolean var4 = false;
                  FileNotFoundException var5 = null;
                  if (var8 instanceof FileNotFoundException) {
                     var4 = true;
                     var5 = (FileNotFoundException)var8;
                     if (this.$outer.org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles) {
                        this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped missing file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().inputSplit())})))), var5);
                        this.finished_$eq(true);
                        BoxedUnit var9 = BoxedUnit.UNIT;
                        break label73;
                     }
                  }

                  if (var4 && !this.$outer.org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles) {
                     throw var5;
                  }

                  if (var8 instanceof AccessControlException ? true : var8 instanceof BlockMissingException) {
                     throw var8;
                  }

                  if (var8 instanceof IOException var7) {
                     if (this.$outer.org$apache$spark$rdd$HadoopRDD$$ignoreCorruptFiles) {
                        this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped the rest content in the corrupted file: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().inputSplit())}))))), var7);
                        this.finished_$eq(true);
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                        break label73;
                     }
                  }

                  throw var8;
               }
            }

            if (!this.finished()) {
               this.inputMetrics().incRecordsRead(1L);
            }

            if (this.inputMetrics().recordsRead() % (long)SparkHadoopUtil$.MODULE$.UPDATE_INPUT_METRICS_INTERVAL_RECORDS() == 0L) {
               this.updateBytesRead();
            }

            return new Tuple2(this.key(), this.value());
         }

         public void close() {
            if (this.reader() != null) {
               InputFileBlockHolder$.MODULE$.unset();

               try {
                  this.reader().close();
               } catch (Exception var8) {
                  if (!ShutdownHookManager$.MODULE$.inShutdown()) {
                     this.$outer.logWarning(() -> "Exception in RecordReader.close()", var8);
                  }
               } finally {
                  this.reader_$eq((RecordReader)null);
               }

               if (this.getBytesReadCallback().isDefined()) {
                  this.updateBytesRead();
               } else if (this.split().inputSplit().value() instanceof FileSplit || this.split().inputSplit().value() instanceof CombineFileSplit) {
                  try {
                     this.inputMetrics().incBytesRead(((InputSplit)this.split().inputSplit().value()).getLength());
                  } catch (IOException var7) {
                     this.$outer.logWarning(() -> "Unable to get input size to set InputMetrics for task", var7);
                  }

               }
            }
         }

         // $FF: synthetic method
         public static final void $anonfun$updateBytesRead$1(final Object $this, final Function0 getBytesRead) {
            $this.inputMetrics().setBytesRead($this.existingBytesRead() + getBytesRead.apply$mcJ$sp());
         }

         // $FF: synthetic method
         private final RecordReader liftedTree1$1() {
            RecordReader var10000;
            try {
               var10000 = this.inputFormat().getRecordReader((InputSplit)this.split().inputSplit().value(), this.jobConf(), Reporter.NULL);
            } catch (Throwable var8) {
               boolean var4 = false;
               FileNotFoundException var5 = null;
               if (var8 instanceof FileNotFoundException) {
                  var4 = true;
                  var5 = (FileNotFoundException)var8;
                  if (this.$outer.org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles) {
                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped missing file: ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().inputSplit())})))), var5);
                     this.finished_$eq(true);
                     var10000 = null;
                     return var10000;
                  }
               }

               if (var4 && !this.$outer.org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles) {
                  throw var5;
               }

               if (var8 instanceof AccessControlException ? true : var8 instanceof BlockMissingException) {
                  throw var8;
               }

               if (var8 instanceof IOException var7) {
                  if (this.$outer.org$apache$spark$rdd$HadoopRDD$$ignoreCorruptFiles) {
                     this.$outer.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Skipped the rest content in the corrupted file: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.split().inputSplit())}))))), var7);
                     this.finished_$eq(true);
                     var10000 = null;
                     return var10000;
                  }
               }

               throw var8;
            }

            return var10000;
         }

         // $FF: synthetic method
         public static final void $anonfun$new$4(final Object $this, final TaskContext context) {
            $this.updateBytesRead();
            $this.closeIfNeeded();
         }

         public {
            if (HadoopRDD.this == null) {
               throw null;
            } else {
               this.$outer = HadoopRDD.this;
               this.split = (HadoopPartition)theSplit$1;
               HadoopRDD.this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task (TID ", ") input split: "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ID..MODULE$, BoxesRunTime.boxToLong(context$1.taskAttemptId()))}))).$plus(this.$outer.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.INPUT_SPLIT..MODULE$, this.split().inputSplit())}))))));
               this.jobConf = HadoopRDD.this.getJobConf();
               this.inputMetrics = context$1.taskMetrics().inputMetrics();
               this.existingBytesRead = this.inputMetrics().bytesRead();
               InputSplit var7 = (InputSplit)this.split().inputSplit().value();
               if (var7 instanceof FileSplit) {
                  FileSplit var8 = (FileSplit)var7;
                  InputFileBlockHolder$.MODULE$.set(var8.getPath().toString(), var8.getStart(), var8.getLength());
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  InputFileBlockHolder$.MODULE$.unset();
                  BoxedUnit var10 = BoxedUnit.UNIT;
               }

               InputSplit var9 = (InputSplit)this.split().inputSplit().value();
               this.getBytesReadCallback = (Option)((var9 instanceof FileSplit ? true : var9 instanceof CombineFileSplit) ? new Some(SparkHadoopUtil$.MODULE$.get().getFSBytesReadOnThreadCallback()) : scala.None..MODULE$);
               this.reader = null;
               this.inputFormat = HadoopRDD.this.getInputFormat(this.jobConf());
               HadoopRDD$.MODULE$.addLocalConfiguration(HadoopRDD$.MODULE$.org$apache$spark$rdd$HadoopRDD$$DATE_TIME_FORMATTER().format(HadoopRDD.this.org$apache$spark$rdd$HadoopRDD$$createTime().toInstant()), context$1.stageId(), theSplit$1.index(), context$1.attemptNumber(), this.jobConf());
               this.reader_$eq(this.liftedTree1$1());
               context$1.addTaskCompletionListener((Function1)((context) -> {
                  $anonfun$new$4(this, context);
                  return BoxedUnit.UNIT;
               }));
               this.key = this.reader() == null ? null : this.reader().createKey();
               this.value = this.reader() == null ? null : this.reader().createValue();
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
      return new InterruptibleIterator(context, iter);
   }

   @DeveloperApi
   public RDD mapPartitionsWithInputSplit(final Function2 f, final boolean preservesPartitioning, final ClassTag evidence$1) {
      return new HadoopMapPartitionsWithSplitRDD(this, f, preservesPartitioning, evidence$1, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public boolean mapPartitionsWithInputSplit$default$2() {
      return false;
   }

   public Seq getPreferredLocations(final Partition split) {
      InputSplit hsplit = (InputSplit)((HadoopPartition)split).inputSplit().value();
      Object var10000;
      if (hsplit instanceof InputSplitWithLocationInfo var6) {
         var10000 = HadoopRDD$.MODULE$.convertSplitLocationInfo(var6.getLocationInfo());
      } else {
         var10000 = scala.None..MODULE$;
      }

      Option locs = (Option)var10000;
      return (Seq)locs.getOrElse(() -> org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])hsplit.getLocations()), (x$3) -> BoxesRunTime.boxToBoolean($anonfun$getPreferredLocations$2(x$3)))).toImmutableArraySeq());
   }

   public void checkpoint() {
   }

   public HadoopRDD persist(final StorageLevel storageLevel) {
      if (storageLevel.deserialized()) {
         this.logWarning(() -> "Caching HadoopRDDs as deserialized objects usually leads to undesired behavior because Hadoop's RecordReader reuses the same Writable object for all records. Use a map transformation to make copies of the records.");
      }

      return (HadoopRDD)super.persist(storageLevel);
   }

   public Configuration getConf() {
      return this.getJobConf();
   }

   // $FF: synthetic method
   public static final void $anonfun$getJobConf$2(final JobConf newJobConf$1, final Function1 f) {
      f.apply(newJobConf$1);
   }

   // $FF: synthetic method
   public static final void $anonfun$getJobConf$8(final JobConf newJobConf$2, final Function1 f) {
      f.apply(newJobConf$2);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$1(final InputSplit x$1) {
      return x$1.getLength() > 0L;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPreferredLocations$2(final String x$3) {
      boolean var10000;
      label23: {
         String var1 = "localhost";
         if (x$3 == null) {
            if (var1 != null) {
               break label23;
            }
         } else if (!x$3.equals(var1)) {
            break label23;
         }

         var10000 = false;
         return var10000;
      }

      var10000 = true;
      return var10000;
   }

   public HadoopRDD(final SparkContext sc, final Broadcast broadcastedConf, final Option initLocalJobConfFuncOpt, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions, final boolean ignoreCorruptFiles, final boolean ignoreMissingFiles) {
      super(sc, scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.broadcastedConf = broadcastedConf;
      this.initLocalJobConfFuncOpt = initLocalJobConfFuncOpt;
      this.inputFormatClass = inputFormatClass;
      this.minPartitions = minPartitions;
      this.org$apache$spark$rdd$HadoopRDD$$ignoreCorruptFiles = ignoreCorruptFiles;
      this.org$apache$spark$rdd$HadoopRDD$$ignoreMissingFiles = ignoreMissingFiles;
      if (initLocalJobConfFuncOpt.isDefined()) {
         SparkContext qual$1 = this.sparkContext();
         Function1 x$1 = (Function1)initLocalJobConfFuncOpt.get();
         boolean x$2 = qual$1.clean$default$2();
         qual$1.clean(x$1, x$2);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.jobConfCacheKey = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("rdd_%d_job_conf"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.id())}));
      this.inputFormatCacheKey = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("rdd_%d_input_format"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(this.id())}));
      this.org$apache$spark$rdd$HadoopRDD$$createTime = new Date();
      this.shouldCloneJobConf = this.sparkContext().conf().getBoolean("spark.hadoop.cloneConf", false);
      this.ignoreEmptySplits = BoxesRunTime.unboxToBoolean(this.sparkContext().conf().get(org.apache.spark.internal.config.package$.MODULE$.HADOOP_RDD_IGNORE_EMPTY_SPLITS()));
   }

   public HadoopRDD(final SparkContext sc, final Broadcast broadcastedConf, final Option initLocalJobConfFuncOpt, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions) {
      this(sc, broadcastedConf, initLocalJobConfFuncOpt, inputFormatClass, keyClass, valueClass, minPartitions, BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.IGNORE_CORRUPT_FILES())), BoxesRunTime.unboxToBoolean(sc.conf().get(org.apache.spark.internal.config.package$.MODULE$.IGNORE_MISSING_FILES())));
   }

   public HadoopRDD(final SparkContext sc, final JobConf conf, final Class inputFormatClass, final Class keyClass, final Class valueClass, final int minPartitions) {
      this(sc, sc.broadcast(new SerializableConfiguration(conf), scala.reflect.ClassTag..MODULE$.apply(SerializableConfiguration.class)), scala.None..MODULE$, inputFormatClass, keyClass, valueClass, minPartitions);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class HadoopMapPartitionsWithSplitRDD$ implements Serializable {
      public static final HadoopMapPartitionsWithSplitRDD$ MODULE$ = new HadoopMapPartitionsWithSplitRDD$();

      public boolean $lessinit$greater$default$3() {
         return false;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(HadoopMapPartitionsWithSplitRDD$.class);
      }
   }

   public static class HadoopMapPartitionsWithSplitRDD extends RDD {
      private final Function2 f;
      private final ClassTag evidence$3;
      private final Option partitioner;

      public Option partitioner() {
         return this.partitioner;
      }

      public Partition[] getPartitions() {
         return this.firstParent(this.evidence$3).partitions();
      }

      public Iterator compute(final Partition split, final TaskContext context) {
         HadoopPartition partition = (HadoopPartition)split;
         InputSplit inputSplit = (InputSplit)partition.inputSplit().value();
         return (Iterator)this.f.apply(inputSplit, this.firstParent(this.evidence$3).iterator(split, context));
      }

      public HadoopMapPartitionsWithSplitRDD(final RDD prev, final Function2 f, final boolean preservesPartitioning, final ClassTag evidence$2, final ClassTag evidence$3) {
         super(prev, evidence$2);
         this.f = f;
         this.evidence$3 = evidence$3;
         this.partitioner = (Option)(preservesPartitioning ? this.firstParent(evidence$3).partitioner() : scala.None..MODULE$);
      }
   }
}
