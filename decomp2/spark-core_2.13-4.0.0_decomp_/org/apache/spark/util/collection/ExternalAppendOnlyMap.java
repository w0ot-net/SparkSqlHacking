package org.apache.spark.util.collection;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.TaskContext$;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.ShuffleWriteMetrics;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.apache.spark.serializer.DeserializationStream;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerInstance;
import org.apache.spark.serializer.SerializerManager;
import org.apache.spark.storage.BlockId;
import org.apache.spark.storage.BlockManager;
import org.apache.spark.storage.DiskBlockManager;
import org.apache.spark.storage.DiskBlockObjectWriter;
import org.apache.spark.storage.FileSegment;
import org.apache.spark.storage.TempLocalBlockId;
import org.apache.spark.util.CompletionIterator;
import org.apache.spark.util.CompletionIterator$;
import org.sparkproject.guava.io.ByteStreams;
import scala.;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Product2;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.PriorityQueue;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction2;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0015eaaBA\u0005\u0003\u0017\u0001\u0011\u0011\u0005\u0005\u000b\u0003\u001f\u0003!\u0011!Q\u0001\n\u0005E\u0005BCAO\u0001\t\u0005\t\u0015!\u0003\u0002 \"Q\u0011Q\u0015\u0001\u0003\u0002\u0003\u0006I!a*\t\u0015\u0005%\u0006A!A!\u0002\u0013\tY\u000b\u0003\u0006\u00026\u0002\u0011\t\u0011)A\u0005\u0003oC!\"a1\u0001\u0005\u0003\u0005\u000b\u0011BAc\u0011)\ti\r\u0001B\u0001B\u0003%\u0011q\u001a\u0005\b\u0003+\u0004A\u0011AAl\u0011\u001d\t)\u000e\u0001C\u0001\u0003SD1\"!>\u0001\u0001\u0004%\t!a\u0003\u0002x\"Y\u0011q \u0001A\u0002\u0013\u0005\u00111\u0002B\u0001\u0011!\u0011i\u0001\u0001Q!\n\u0005e\b\"\u0003B\f\u0001\t\u0007I\u0011\u0002B\r\u0011!\u0011I\r\u0001Q\u0001\n\tm\u0001\"\u0003Bf\u0001\t\u0007I\u0011\u0002Bg\u0011!\u0011)\u000e\u0001Q\u0001\n\t=\u0007\"\u0003Bl\u0001\t\u0007I\u0011\u0002Bm\u0011!\u0011\t\u000f\u0001Q\u0001\n\tm\u0007\"\u0003Br\u0001\t\u0007I\u0011\u0002Bs\u0011!\u00119\u000f\u0001Q\u0001\n\tE\u0003\"\u0003Bu\u0001\u0001\u0007I\u0011\u0002Bs\u0011%\u0011Y\u000f\u0001a\u0001\n\u0013\u0011i\u000f\u0003\u0005\u0003r\u0002\u0001\u000b\u0015\u0002B)\u0011\u001d\u0011\u0019\u0010\u0001C\u0001\u0005KD\u0011B!>\u0001\u0005\u0004%IAa\u001a\t\u0011\t]\b\u0001)A\u0005\u0005SB\u0011B!?\u0001\u0005\u0004%IAa?\t\u0011\r%\u0001\u0001)A\u0005\u0005{D\u0011ba\u0003\u0001\u0001\u0004%IA!:\t\u0013\r5\u0001\u00011A\u0005\n\r=\u0001\u0002CB\n\u0001\u0001\u0006KA!\u0015\t\u000f\rU\u0001\u0001\"\u0001\u0003f\"I1q\u0003\u0001C\u0002\u0013%1\u0011\u0004\u0005\t\u0007\u0017\u0004\u0001\u0015!\u0003\u0004\u001c!I1Q\u001a\u0001C\u0002\u0013%1q\u001a\u0005\t\u0007/\u0004\u0001\u0015!\u0003\u0004R\"I1\u0011\u001c\u0001A\u0002\u0013%11\u001c\u0005\n\tC\u0001\u0001\u0019!C\u0005\tGA\u0001\u0002b\n\u0001A\u0003&1Q\u001c\u0005\n\tW\u0001A\u0011AA\u0006\u0005OBq\u0001\"\f\u0001\t\u0003!y\u0003C\u0004\u0005:\u0001!\t\u0001b\u000f\t\u000f\u0011e\u0002\u0001\"\u0001\u0005J!AAQ\u0002\u0001!\n#\"y\u0005\u0003\u0005\u0005T\u0001\u0001K\u0011\u000bC\b\u0011!!)\u0006\u0001Q\u0005\n\u0011]\u0003b\u0002C/\u0001\u0011\u0005Aq\f\u0005\b\tG\u0002A\u0011\tBO\u0011\u001d!)\u0007\u0001C\u0005\u0005\u000f4a\u0001b\u001a\u0001\t\u0011%\u0004bBAke\u0011\u0005A1\u000e\u0005\n\t_\u0012$\u0019!C\u0005\tcB\u0001\u0002\",3A\u0003%A1\u000f\u0005\n\t_\u0013$\u0019!C\u0005\u0005;C\u0001\u0002\"-3A\u0003%!q\u0007\u0005\n\tg\u0013$\u0019!C\u0005\tkC\u0001\u0002b13A\u0003%Aq\u0017\u0005\b\t\u000b\u0014D\u0011\u0002Cd\u0011\u001d!\tN\rC\u0005\t'Dq\u0001b83\t\u0013!\t\u000fC\u0004\u0003:J\"\tEa/\t\u000f\t\r'\u0007\"\u0011\u00038\u001a1AQ\u0010\u001a\u0005\t\u007fB!\u0002b\u0019@\u0005\u000b\u0007I\u0011\u0001CD\u0011)!\tj\u0010B\u0001B\u0003%A\u0011\u0012\u0005\u000b\t'{$Q1A\u0005\u0002\u0011U\u0005B\u0003CM\u007f\t\u0005\t\u0015!\u0003\u0005\u0018\"9\u0011Q[ \u0005\u0002\u0011m\u0005b\u0002CQ\u007f\u0011\u0005!1\u0018\u0005\b\tG{D\u0011\u0001B4\u0011\u001d!)k\u0010C!\tO3aA!\f\u0001\t\t=\u0002B\u0003B\u001f\u0011\n\u0005\t\u0015!\u0003\u0003@!Q!Q\t%\u0003\u0002\u0003\u0006IAa\u0012\t\u0015\t5\u0003J!A!\u0002\u0013\u0011y\u0005C\u0004\u0002V\"#\tAa\u0016\t\u0013\t}\u0003J1A\u0005\n\t\u0005\u0004\u0002\u0003B2\u0011\u0002\u0006IAa\u0014\t\u0013\t\u0015\u0004\n1A\u0005\n\t\u001d\u0004\"\u0003B8\u0011\u0002\u0007I\u0011\u0002B9\u0011!\u0011)\b\u0013Q!\n\t%\u0004\"\u0003B<\u0011\u0002\u0007I\u0011\u0002B=\u0011%\u0011\t\t\u0013a\u0001\n\u0013\u0011\u0019\t\u0003\u0005\u0003\b\"\u0003\u000b\u0015\u0002B>\u0011%\u0011I\t\u0013a\u0001\n\u0013\u0011Y\tC\u0005\u0003\u0014\"\u0003\r\u0011\"\u0003\u0003\u0016\"A!\u0011\u0014%!B\u0013\u0011i\tC\u0005\u0003\u001c\"\u0003\r\u0011\"\u0003\u0003\u001e\"I!q\u0014%A\u0002\u0013%!\u0011\u0015\u0005\t\u0005KC\u0005\u0015)\u0003\u00038!I!q\u0015%A\u0002\u0013%!q\r\u0005\n\u0005SC\u0005\u0019!C\u0005\u0005WC\u0001Ba,IA\u0003&!\u0011\u000e\u0005\b\u0005cCE\u0011\u0002BZ\u0011\u001d\u0011)\f\u0013C\u0005\u0005oCqA!/I\t\u0003\u0012Y\fC\u0004\u0003D\"#\tEa.\t\u000f\t\u0015\u0007\n\"\u0003\u0003H\u001a11q\u001c\u0001\u0005\u0007CD!ba9d\u0005\u0003\u0007I\u0011\u0001BO\u0011)\u0019)o\u0019BA\u0002\u0013\u00051q\u001d\u0005\u000b\u0007W\u001c'\u0011!Q!\n\t]\u0002bBAkG\u0012\u00051Q\u001e\u0005\n\u0007c\u001c'\u0019!C\u0005\u0007gD\u0001b!>dA\u0003%1\u0011\u000b\u0005\n\u0007o\u001c\u0007\u0019!C\u0005\u0007sD\u0011ba?d\u0001\u0004%Ia!@\t\u0011\u0011\u00051\r)Q\u0005\u0003WB\u0011\u0002b\u0001d\u0001\u0004%IAa/\t\u0013\u0011\u00151\r1A\u0005\n\u0011\u001d\u0001\u0002\u0003C\u0006G\u0002\u0006KA!0\t\u000f\u001151\r\"\u0001\u0005\u0010!9A\u0011C2\u0005\n\t\u001d\u0007b\u0002C\nG\u0012\u0005AQ\u0003\u0005\b\t?\u0019G\u0011\u0001B\\\u0011\u001d\u0011Il\u0019C!\u0005wCqAa1d\t\u0003\u00129\fC\u0004\u0005r\u0002!I\u0001b=\t\u000f\u0011e\b\u0001\"\u0011\u0005|\u001eQ11GA\u0006\u0011\u0003\t\u0019b!\u000e\u0007\u0015\u0005%\u00111\u0002E\u0001\u0003'\u00199\u0004C\u0004\u0002Vf$\ta!\u000f\t\u000f\rm\u0012\u0010\"\u0003\u0004>\u0019111J=\u0005\u0007\u001bBq!!6}\t\u0003\u0019Y\u0007C\u0004\u0004rq$\taa\u001d\t\u0013\ru\u00140%A\u0005\u0002\r}\u0004\"CBOsF\u0005I\u0011ABP\u0011%\u0019Y+_I\u0001\n\u0003\u0019i\u000bC\u0005\u0004:f\f\n\u0011\"\u0001\u0004<\"I1qY=\u0002\u0002\u0013%1\u0011\u001a\u0002\u0016\u000bb$XM\u001d8bY\u0006\u0003\b/\u001a8e\u001f:d\u00170T1q\u0015\u0011\ti!a\u0004\u0002\u0015\r|G\u000e\\3di&|gN\u0003\u0003\u0002\u0012\u0005M\u0011\u0001B;uS2TA!!\u0006\u0002\u0018\u0005)1\u000f]1sW*!\u0011\u0011DA\u000e\u0003\u0019\t\u0007/Y2iK*\u0011\u0011QD\u0001\u0004_J<7\u0001A\u000b\t\u0003G\t9(!'\u0002\fNI\u0001!!\n\u00024\u0005\r\u0013q\n\t\u0007\u0003O\tI#!\f\u000e\u0005\u0005-\u0011\u0002BA\u0016\u0003\u0017\u0011\u0011b\u00159jY2\f'\r\\3\u0011\t\u0005\u001d\u0012qF\u0005\u0005\u0003c\tYAA\u0006TSj,GK]1dW\u0016\u0014\b\u0003BA\u001b\u0003\u007fi!!a\u000e\u000b\t\u0005e\u00121H\u0001\u0003S>T!!!\u0010\u0002\t)\fg/Y\u0005\u0005\u0003\u0003\n9D\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0005\u0003\u0002F\u0005-SBAA$\u0015\u0011\tI%a\u0005\u0002\u0011%tG/\u001a:oC2LA!!\u0014\u0002H\t9Aj\\4hS:<\u0007CBA)\u0003K\nYG\u0004\u0003\u0002T\u0005}c\u0002BA+\u00037j!!a\u0016\u000b\t\u0005e\u0013qD\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005u\u0013!B:dC2\f\u0017\u0002BA1\u0003G\nq\u0001]1dW\u0006<WM\u0003\u0002\u0002^%!\u0011qMA5\u0005!IE/\u001a:bE2,'\u0002BA1\u0003G\u0002\u0002\"!\u001c\u0002p\u0005M\u0014\u0011R\u0007\u0003\u0003GJA!!\u001d\u0002d\t1A+\u001e9mKJ\u0002B!!\u001e\u0002x1\u0001AaBA=\u0001\t\u0007\u00111\u0010\u0002\u0002\u0017F!\u0011QPAB!\u0011\ti'a \n\t\u0005\u0005\u00151\r\u0002\b\u001d>$\b.\u001b8h!\u0011\ti'!\"\n\t\u0005\u001d\u00151\r\u0002\u0004\u0003:L\b\u0003BA;\u0003\u0017#q!!$\u0001\u0005\u0004\tYHA\u0001D\u00039\u0019'/Z1uK\u000e{WNY5oKJ\u0004\u0002\"!\u001c\u0002\u0014\u0006]\u0015\u0011R\u0005\u0005\u0003+\u000b\u0019GA\u0005Gk:\u001cG/[8ocA!\u0011QOAM\t\u001d\tY\n\u0001b\u0001\u0003w\u0012\u0011AV\u0001\u000b[\u0016\u0014x-\u001a,bYV,\u0007CCA7\u0003C\u000bI)a&\u0002\n&!\u00111UA2\u0005%1UO\\2uS>t''\u0001\bnKJ<WmQ8nE&tWM]:\u0011\u0015\u00055\u0014\u0011UAE\u0003\u0013\u000bI)\u0001\u0006tKJL\u0017\r\\5{KJ\u0004B!!,\u000226\u0011\u0011q\u0016\u0006\u0005\u0003S\u000b\u0019\"\u0003\u0003\u00024\u0006=&AC*fe&\fG.\u001b>fe\u0006a!\r\\8dW6\u000bg.Y4feB!\u0011\u0011XA`\u001b\t\tYL\u0003\u0003\u0002>\u0006M\u0011aB:u_J\fw-Z\u0005\u0005\u0003\u0003\fYL\u0001\u0007CY>\u001c7.T1oC\u001e,'/A\u0004d_:$X\r\u001f;\u0011\t\u0005\u001d\u0017\u0011Z\u0007\u0003\u0003'IA!a3\u0002\u0014\tYA+Y:l\u0007>tG/\u001a=u\u0003E\u0019XM]5bY&TXM]'b]\u0006<WM\u001d\t\u0005\u0003[\u000b\t.\u0003\u0003\u0002T\u0006=&!E*fe&\fG.\u001b>fe6\u000bg.Y4fe\u00061A(\u001b8jiz\"\u0002#!7\u0002\\\u0006u\u0017q\\Aq\u0003G\f)/a:\u0011\u0013\u0005\u001d\u0002!a\u001d\u0002\u0018\u0006%\u0005bBAH\u0011\u0001\u0007\u0011\u0011\u0013\u0005\b\u0003;C\u0001\u0019AAP\u0011\u001d\t)\u000b\u0003a\u0001\u0003OC\u0011\"!+\t!\u0003\u0005\r!a+\t\u0013\u0005U\u0006\u0002%AA\u0002\u0005]\u0006\"CAb\u0011A\u0005\t\u0019AAc\u0011%\ti\r\u0003I\u0001\u0002\u0004\ty\r\u0006\u0007\u0002Z\u0006-\u0018Q^Ax\u0003c\f\u0019\u0010C\u0004\u0002\u0010&\u0001\r!!%\t\u000f\u0005u\u0015\u00021\u0001\u0002 \"9\u0011QU\u0005A\u0002\u0005\u001d\u0006bBAU\u0013\u0001\u0007\u00111\u0016\u0005\b\u0003kK\u0001\u0019AA\\\u0003)\u0019WO\u001d:f]Rl\u0015\r]\u000b\u0003\u0003s\u0004\u0002\"a\n\u0002|\u0006M\u0014\u0011R\u0005\u0005\u0003{\fYAA\rTSj,GK]1dW&tw-\u00119qK:$wJ\u001c7z\u001b\u0006\u0004\u0018AD2veJ,g\u000e^'ba~#S-\u001d\u000b\u0005\u0005\u0007\u0011I\u0001\u0005\u0003\u0002n\t\u0015\u0011\u0002\u0002B\u0004\u0003G\u0012A!\u00168ji\"I!1B\u0006\u0002\u0002\u0003\u0007\u0011\u0011`\u0001\u0004q\u0012\n\u0014aC2veJ,g\u000e^'ba\u0002B3\u0001\u0004B\t!\u0011\tiGa\u0005\n\t\tU\u00111\r\u0002\tm>d\u0017\r^5mK\u0006Y1\u000f]5mY\u0016$W*\u00199t+\t\u0011Y\u0002\u0005\u0004\u0003\u001e\t\u0015\"\u0011F\u0007\u0003\u0005?QAA!\t\u0003$\u00059Q.\u001e;bE2,'\u0002BA\u0007\u0003GJAAa\n\u0003 \tY\u0011I\u001d:bs\n+hMZ3s!\r\u0011Y\u0003S\u0007\u0002\u0001\tyA)[:l\u001b\u0006\u0004\u0018\n^3sCR|'oE\u0003I\u0005c\u00119\u0004\u0005\u0003\u0002n\tM\u0012\u0002\u0002B\u001b\u0003G\u0012a!\u00118z%\u00164\u0007CBA)\u0005s\tY'\u0003\u0003\u0003<\u0005%$\u0001C%uKJ\fGo\u001c:\u0002\t\u0019LG.\u001a\t\u0005\u0003k\u0011\t%\u0003\u0003\u0003D\u0005]\"\u0001\u0002$jY\u0016\fqA\u00197pG.LE\r\u0005\u0003\u0002:\n%\u0013\u0002\u0002B&\u0003w\u0013qA\u00117pG.LE-\u0001\u0006cCR\u001c\u0007nU5{KN\u0004bA!\b\u0003&\tE\u0003\u0003BA7\u0005'JAA!\u0016\u0002d\t!Aj\u001c8h)!\u0011IC!\u0017\u0003\\\tu\u0003b\u0002B\u001f\u0019\u0002\u0007!q\b\u0005\b\u0005\u000bb\u0005\u0019\u0001B$\u0011\u001d\u0011i\u0005\u0014a\u0001\u0005\u001f\nABY1uG\"|eMZ:fiN,\"Aa\u0014\u0002\u001b\t\fGo\u00195PM\u001a\u001cX\r^:!\u0003)\u0011\u0017\r^2i\u0013:$W\r_\u000b\u0003\u0005S\u0002B!!\u001c\u0003l%!!QNA2\u0005\rIe\u000e^\u0001\u000fE\u0006$8\r[%oI\u0016Dx\fJ3r)\u0011\u0011\u0019Aa\u001d\t\u0013\t-\u0001+!AA\u0002\t%\u0014a\u00032bi\u000eD\u0017J\u001c3fq\u0002\n!BZ5mKN#(/Z1n+\t\u0011Y\b\u0005\u0003\u00026\tu\u0014\u0002\u0002B@\u0003o\u0011qBR5mK&s\u0007/\u001e;TiJ,\u0017-\\\u0001\u000fM&dWm\u0015;sK\u0006lw\fJ3r)\u0011\u0011\u0019A!\"\t\u0013\t-1+!AA\u0002\tm\u0014a\u00034jY\u0016\u001cFO]3b[\u0002\n\u0011\u0003Z3tKJL\u0017\r\\5{KN#(/Z1n+\t\u0011i\t\u0005\u0003\u0002.\n=\u0015\u0002\u0002BI\u0003_\u0013Q\u0003R3tKJL\u0017\r\\5{CRLwN\\*ue\u0016\fW.A\u000beKN,'/[1mSj,7\u000b\u001e:fC6|F%Z9\u0015\t\t\r!q\u0013\u0005\n\u0005\u00171\u0016\u0011!a\u0001\u0005\u001b\u000b!\u0003Z3tKJL\u0017\r\\5{KN#(/Z1nA\u0005i!-\u0019;dQ&#XM]1u_J,\"Aa\u000e\u0002#\t\fGo\u00195Ji\u0016\u0014\u0018\r^8s?\u0012*\u0017\u000f\u0006\u0003\u0003\u0004\t\r\u0006\"\u0003B\u00063\u0006\u0005\t\u0019\u0001B\u001c\u00039\u0011\u0017\r^2i\u0013R,'/\u0019;pe\u0002\n1b\u001c2kK\u000e$8OU3bI\u0006yqN\u00196fGR\u001c(+Z1e?\u0012*\u0017\u000f\u0006\u0003\u0003\u0004\t5\u0006\"\u0003B\u00069\u0006\u0005\t\u0019\u0001B5\u00031y'M[3diN\u0014V-\u00193!\u0003EqW\r\u001f;CCR\u001c\u0007.\u0013;fe\u0006$xN\u001d\u000b\u0003\u0005o\tAB]3bI:+\u0007\u0010^%uK6$\"!a\u001b\u0002\u000f!\f7OT3yiV\u0011!Q\u0018\t\u0005\u0003[\u0012y,\u0003\u0003\u0003B\u0006\r$a\u0002\"p_2,\u0017M\\\u0001\u0005]\u0016DH/A\u0004dY\u0016\fg.\u001e9\u0015\u0005\t\r\u0011\u0001D:qS2dW\rZ'baN\u0004\u0013!C:qCJ\\7i\u001c8g+\t\u0011y\r\u0005\u0003\u0002H\nE\u0017\u0002\u0002Bj\u0003'\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\u0015M\u0004\u0018M]6D_:4\u0007%\u0001\teSN\\'\t\\8dW6\u000bg.Y4feV\u0011!1\u001c\t\u0005\u0003s\u0013i.\u0003\u0003\u0003`\u0006m&\u0001\u0005#jg.\u0014En\\2l\u001b\u0006t\u0017mZ3s\u0003E!\u0017n]6CY>\u001c7.T1oC\u001e,'\u000fI\u0001\u0014g\u0016\u0014\u0018.\u00197ju\u0016\u0014()\u0019;dQNK'0Z\u000b\u0003\u0005#\nAc]3sS\u0006d\u0017N_3s\u0005\u0006$8\r[*ju\u0016\u0004\u0013!E0eSN\\')\u001f;fgN\u0003\u0018\u000e\u001c7fI\u0006)r\fZ5tW\nKH/Z:Ta&dG.\u001a3`I\u0015\fH\u0003\u0002B\u0002\u0005_D\u0011Ba\u0003\u0017\u0003\u0003\u0005\rA!\u0015\u0002%}#\u0017n]6CsR,7o\u00159jY2,G\rI\u0001\u0011I&\u001c8NQ=uKN\u001c\u0006/\u001b7mK\u0012\faBZ5mK\n+hMZ3s'&TX-A\bgS2,')\u001e4gKJ\u001c\u0016N_3!\u000319(/\u001b;f\u001b\u0016$(/[2t+\t\u0011i\u0010\u0005\u0003\u0003\u0000\u000e\u0015QBAB\u0001\u0015\u0011\u0019\u0019!a\u0005\u0002\u0011\u0015DXmY;u_JLAaa\u0002\u0004\u0002\t\u00192\u000b[;gM2,wK]5uK6+GO]5dg\u0006iqO]5uK6+GO]5dg\u0002\nAc\u00189fC.lU-\\8ssV\u001bX\r\u001a\"zi\u0016\u001c\u0018\u0001G0qK\u0006\\W*Z7pef,6/\u001a3CsR,7o\u0018\u0013fcR!!1AB\t\u0011%\u0011YAHA\u0001\u0002\u0004\u0011\t&A\u000b`a\u0016\f7.T3n_JLXk]3e\u0005f$Xm\u001d\u0011\u0002'A,\u0017m['f[>\u0014\u00180V:fI\nKH/Z:\u0002\u001b-,\u0017pQ8na\u0006\u0014\u0018\r^8s+\t\u0019Y\u0002E\u0003\u0004\u001eq\f\u0019HD\u0002\u0004 atAa!\t\u000429!11EB\u0018\u001d\u0011\u0019)c!\f\u000f\t\r\u001d21\u0006\b\u0005\u0003+\u001aI#\u0003\u0002\u0002\u001e%!\u0011\u0011DA\u000e\u0013\u0011\t)\"a\u0006\n\t\u0005E\u00111C\u0005\u0005\u0003\u001b\ty!A\u000bFqR,'O\\1m\u0003B\u0004XM\u001c3P]2LX*\u00199\u0011\u0007\u0005\u001d\u0012pE\u0003z\u0005c\t\u0019\u0004\u0006\u0002\u00046\u0005!\u0001.Y:i+\u0011\u0019yda\u0012\u0015\t\t%4\u0011\t\u0005\b\u0007\u0007Z\b\u0019AB#\u0003\ry'M\u001b\t\u0005\u0003k\u001a9\u0005B\u0004\u0004Jm\u0014\r!a\u001f\u0003\u0003Q\u0013a\u0002S1tQ\u000e{W\u000e]1sCR|'/\u0006\u0003\u0004P\r%4#\u0002?\u0004R\ru\u0003\u0003BB*\u00073j!a!\u0016\u000b\t\r]\u00131H\u0001\u0005Y\u0006tw-\u0003\u0003\u0004\\\rU#AB(cU\u0016\u001cG\u000f\u0005\u0004\u0004`\r\r4qM\u0007\u0003\u0007CRA!!\u0005\u0002<%!1QMB1\u0005)\u0019u.\u001c9be\u0006$xN\u001d\t\u0005\u0003k\u001aI\u0007B\u0004\u0002zq\u0014\r!a\u001f\u0015\u0005\r5\u0004#BB8y\u000e\u001dT\"A=\u0002\u000f\r|W\u000e]1sKR1!\u0011NB;\u0007sBqaa\u001e\u007f\u0001\u0004\u00199'\u0001\u0003lKf\f\u0004bBB>}\u0002\u00071qM\u0001\u0005W\u0016L('A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005N\u000b\t\u0007\u0003\u001b9j!'\u0004\u001cV\u001111\u0011\u0016\u0005\u0003W\u001b)i\u000b\u0002\u0004\bB!1\u0011RBJ\u001b\t\u0019YI\u0003\u0003\u0004\u000e\u000e=\u0015!C;oG\",7m[3e\u0015\u0011\u0019\t*a\u0019\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0004\u0016\u000e-%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u00129\u0011\u0011P@C\u0002\u0005mDaBAN\u007f\n\u0007\u00111\u0010\u0003\b\u0003\u001b{(\u0019AA>\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%kUA1\u0011UBS\u0007O\u001bI+\u0006\u0002\u0004$*\"\u0011qWBC\t!\tI(!\u0001C\u0002\u0005mD\u0001CAN\u0003\u0003\u0011\r!a\u001f\u0005\u0011\u00055\u0015\u0011\u0001b\u0001\u0003w\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u00122T\u0003CBX\u0007g\u001b)la.\u0016\u0005\rE&\u0006BAc\u0007\u000b#\u0001\"!\u001f\u0002\u0004\t\u0007\u00111\u0010\u0003\t\u00037\u000b\u0019A1\u0001\u0002|\u0011A\u0011QRA\u0002\u0005\u0004\tY(A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeN\u000b\t\u0007{\u001b\tma1\u0004FV\u00111q\u0018\u0016\u0005\u0003\u001f\u001c)\t\u0002\u0005\u0002z\u0005\u0015!\u0019AA>\t!\tY*!\u0002C\u0002\u0005mD\u0001CAG\u0003\u000b\u0011\r!a\u001f\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\rE\u0013AD6fs\u000e{W\u000e]1sCR|'\u000fI\u0001\u0004g\u0016\u0014XCABi!\u0011\tika5\n\t\rU\u0017q\u0016\u0002\u0013'\u0016\u0014\u0018.\u00197ju\u0016\u0014\u0018J\\:uC:\u001cW-\u0001\u0003tKJ\u0004\u0013a\u0004:fC\u0012LgnZ%uKJ\fGo\u001c:\u0016\u0005\ru\u0007c\u0001B\u0016G\n\t2\u000b]5mY\u0006\u0014G.Z%uKJ\fGo\u001c:\u0014\u000b\r\u0014\tDa\u000e\u0002\u0011U\u00048\u000f\u001e:fC6\fA\"\u001e9tiJ,\u0017-\\0%KF$BAa\u0001\u0004j\"I!1B3\u0002\u0002\u0003\u0007!qG\u0001\nkB\u001cHO]3b[\u0002\"Ba!8\u0004p\"911]4A\u0002\t]\u0012AC*Q\u00132cu\fT(D\u0017V\u00111\u0011K\u0001\f'BKE\nT0M\u001f\u000e[\u0005%A\u0002dkJ,\"!a\u001b\u0002\u000f\r,(o\u0018\u0013fcR!!1AB\u0000\u0011%\u0011Ya[A\u0001\u0002\u0004\tY'\u0001\u0003dkJ\u0004\u0013A\u00035bgN\u0003\u0018\u000e\u001c7fI\u0006q\u0001.Y:Ta&dG.\u001a3`I\u0015\fH\u0003\u0002B\u0002\t\u0013A\u0011Ba\u0003o\u0003\u0003\u0005\rA!0\u0002\u0017!\f7o\u00159jY2,G\rI\u0001\u0006gBLG\u000e\u001c\u000b\u0003\u0005{\u000bq\u0001Z3tiJ|\u00170\u0001\u000bu_\u000e{W\u000e\u001d7fi&|g.\u0013;fe\u0006$xN]\u000b\u0003\t/\u0001\u0002\u0002\"\u0007\u0005\u001c\u0005-4Q\\\u0007\u0003\u0003\u001fIA\u0001\"\b\u0002\u0010\t\u00112i\\7qY\u0016$\u0018n\u001c8Ji\u0016\u0014\u0018\r^8s\u0003!\u0011X-\u00193OKb$\u0018a\u0005:fC\u0012LgnZ%uKJ\fGo\u001c:`I\u0015\fH\u0003\u0002B\u0002\tKA\u0011Ba\u0003'\u0003\u0003\u0005\ra!8\u0002!I,\u0017\rZ5oO&#XM]1u_J\u0004\u0003fA\u0014\u0003\u0012\u0005Ia.^7Ta&dGn]\u0001\u0007S:\u001cXM\u001d;\u0015\r\t\rA\u0011\u0007C\u001b\u0011\u001d!\u0019$\u000ba\u0001\u0003g\n1a[3z\u0011\u001d!9$\u000ba\u0001\u0003/\u000bQA^1mk\u0016\f\u0011\"\u001b8tKJ$\u0018\t\u001c7\u0015\t\t\rAQ\b\u0005\b\t\u007fQ\u0003\u0019\u0001C!\u0003\u001d)g\u000e\u001e:jKN\u0004b!!\u0015\u0003:\u0011\r\u0003\u0003CA7\t\u000b\n\u0019(a&\n\t\u0011\u001d\u00131\r\u0002\t!J|G-^2ueQ!!1\u0001C&\u0011\u001d!yd\u000ba\u0001\t\u001b\u0002b!!\u0015\u0002f\u0011\rC\u0003\u0002B\u0002\t#Bq!!\u0004-\u0001\u0004\ti#\u0001\u0006g_J\u001cWm\u00159jY2\f\u0011d\u001d9jY2lU-\\8ss&#XM]1u_J$v\u000eR5tWR!!\u0011\u0006C-\u0011\u001d!YF\fa\u0001\u0005o\t\u0001#\u001b8NK6|'/_%uKJ\fGo\u001c:\u0002'\u0011,7\u000f\u001e:vGRLg/Z%uKJ\fGo\u001c:\u0015\t\t]B\u0011\r\u0005\b\t7z\u0003\u0019\u0001B\u001c\u0003!IG/\u001a:bi>\u0014\u0018A\u00044sK\u0016\u001cUO\u001d:f]Rl\u0015\r\u001d\u0002\u0011\u000bb$XM\u001d8bY&#XM]1u_J\u001cRA\rB\u0019\u0005o!\"\u0001\"\u001c\u0011\u0007\t-\"'A\u0005nKJ<W\rS3baV\u0011A1\u000f\t\u0007\u0005;!)\b\"\u001f\n\t\u0011]$q\u0004\u0002\u000e!JLwN]5usF+X-^3\u0011\u0007\u0011mt(D\u00013\u00051\u0019FO]3b[\n+hMZ3s'\u0015y$\u0011\u0007CA!\u0019\u0019\u0019\u0006b!\u0005z%!AQQB+\u0005)\u0019u.\u001c9be\u0006\u0014G.Z\u000b\u0003\t\u0013\u0003b\u0001b#\u0005\u000e\u0006-TB\u0001B\u0012\u0013\u0011!yIa\t\u0003!\t+hMZ3sK\u0012LE/\u001a:bi>\u0014\u0018!C5uKJ\fGo\u001c:!\u0003\u0015\u0001\u0018-\u001b:t+\t!9\n\u0005\u0004\u0003\u001e\t\u0015\u00121N\u0001\u0007a\u0006L'o\u001d\u0011\u0015\r\u0011eDQ\u0014CP\u0011\u001d!\u0019\u0007\u0012a\u0001\t\u0013Cq\u0001b%E\u0001\u0004!9*A\u0004jg\u0016k\u0007\u000f^=\u0002\u00155LgnS3z\u0011\u0006\u001c\b.A\u0005d_6\u0004\u0018M]3U_R!!\u0011\u000eCU\u0011\u001d!Yk\u0012a\u0001\ts\nQa\u001c;iKJ\f!\"\\3sO\u0016DU-\u00199!\u0003%\u0019xN\u001d;fI6\u000b\u0007/\u0001\u0006t_J$X\rZ'ba\u0002\nA\"\u001b8qkR\u001cFO]3b[N,\"\u0001b.\u0011\r\u0011eFq\u0018CE\u001b\t!YL\u0003\u0003\u0005>\n\r\u0012!C5n[V$\u0018M\u00197f\u0013\u0011!\t\rb/\u0003\u0007M+\u0017/A\u0007j]B,Ho\u0015;sK\u0006l7\u000fI\u0001\u0011e\u0016\fGMT3yi\"\u000b7\u000f[\"pI\u0016$bAa\u0001\u0005J\u00125\u0007b\u0002Cfu\u0001\u0007A\u0011R\u0001\u0003SRDq\u0001b4;\u0001\u0004!9*A\u0002ck\u001a\f\u0001#\\3sO\u0016LemS3z\u000bbL7\u000f^:\u0015\u0011\u0005%EQ\u001bCl\t7Dq\u0001b\r<\u0001\u0004\t\u0019\bC\u0004\u0005Zn\u0002\r!!#\u0002\u0019\t\f7/Z\"p[\nLg.\u001a:\t\u000f\u0011u7\b1\u0001\u0005z\u00051!-\u001e4gKJ\f\u0001C]3n_Z,gI]8n\u0005V4g-\u001a:\u0016\t\u0011\rHq\u001d\u000b\u0007\tK$I\u000f\"<\u0011\t\u0005UDq\u001d\u0003\b\u0007\u0013b$\u0019AA>\u0011\u001d!i\u000e\u0010a\u0001\tW\u0004bA!\b\u0003&\u0011\u0015\bb\u0002Cxy\u0001\u0007!\u0011N\u0001\u0006S:$W\r_\u0001\bQ\u0006\u001c\bnS3z)\u0011\u0011I\u0007\">\t\u000f\u0011]h\u000f1\u0001\u0002l\u0005\u00111nY\u0001\ti>\u001cFO]5oOR\u0011AQ \t\u0005\t\u007f,9A\u0004\u0003\u0006\u0002\u0015\r\u0001\u0003BA+\u0003GJA!\"\u0002\u0002d\u00051\u0001K]3eK\u001aLA!\"\u0003\u0006\f\t11\u000b\u001e:j]\u001eTA!\"\u0002\u0002d!\u001a\u0001!b\u0004\u0011\t\u0015EQQC\u0007\u0003\u000b'QAa!%\u0002\u0014%!QqCC\n\u00051!UM^3m_B,'/\u00119j\u0001"
)
public class ExternalAppendOnlyMap extends Spillable implements Serializable, Iterable {
   private final Function1 createCombiner;
   private final Function2 mergeValue;
   public final Function2 org$apache$spark$util$collection$ExternalAppendOnlyMap$$mergeCombiners;
   private final BlockManager blockManager;
   public final TaskContext org$apache$spark$util$collection$ExternalAppendOnlyMap$$context;
   public final SerializerManager org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerManager;
   private volatile SizeTrackingAppendOnlyMap currentMap;
   private final ArrayBuffer org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps;
   private final SparkConf sparkConf;
   private final DiskBlockManager diskBlockManager;
   private final long org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerBatchSize;
   private long _diskBytesSpilled;
   private final int fileBufferSize;
   private final ShuffleWriteMetrics writeMetrics;
   private long _peakMemoryUsedBytes;
   private final HashComparator org$apache$spark$util$collection$ExternalAppendOnlyMap$$keyComparator;
   private final SerializerInstance org$apache$spark$util$collection$ExternalAppendOnlyMap$$ser;
   private volatile SpillableIterator readingIterator;

   public static SerializerManager $lessinit$greater$default$7() {
      return ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$7();
   }

   public static TaskContext $lessinit$greater$default$6() {
      return ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$6();
   }

   public static BlockManager $lessinit$greater$default$5() {
      return ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$5();
   }

   public static Serializer $lessinit$greater$default$4() {
      return ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$4();
   }

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   public IterableFactory iterableFactory() {
      return Iterable.iterableFactory$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public String stringPrefix() {
      return Iterable.stringPrefix$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return IterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return IterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return IterableFactoryDefaults.empty$(this);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public View view() {
      return IterableOps.view$(this);
   }

   public int sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   public int sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public Object concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public Object $plus$plus$colon(final IterableOnce that) {
      return IterableOps.$plus$plus$colon$(this, that);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
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

   public boolean isEmpty() {
      return IterableOnceOps.isEmpty$(this);
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

   public Map toMap(final .less.colon.less ev) {
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

   public SizeTrackingAppendOnlyMap currentMap() {
      return this.currentMap;
   }

   public void currentMap_$eq(final SizeTrackingAppendOnlyMap x$1) {
      this.currentMap = x$1;
   }

   public ArrayBuffer org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps() {
      return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps;
   }

   private SparkConf sparkConf() {
      return this.sparkConf;
   }

   private DiskBlockManager diskBlockManager() {
      return this.diskBlockManager;
   }

   public long org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerBatchSize() {
      return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerBatchSize;
   }

   private long _diskBytesSpilled() {
      return this._diskBytesSpilled;
   }

   private void _diskBytesSpilled_$eq(final long x$1) {
      this._diskBytesSpilled = x$1;
   }

   public long diskBytesSpilled() {
      return this._diskBytesSpilled();
   }

   private int fileBufferSize() {
      return this.fileBufferSize;
   }

   private ShuffleWriteMetrics writeMetrics() {
      return this.writeMetrics;
   }

   private long _peakMemoryUsedBytes() {
      return this._peakMemoryUsedBytes;
   }

   private void _peakMemoryUsedBytes_$eq(final long x$1) {
      this._peakMemoryUsedBytes = x$1;
   }

   public long peakMemoryUsedBytes() {
      return this._peakMemoryUsedBytes();
   }

   public HashComparator org$apache$spark$util$collection$ExternalAppendOnlyMap$$keyComparator() {
      return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$keyComparator;
   }

   public SerializerInstance org$apache$spark$util$collection$ExternalAppendOnlyMap$$ser() {
      return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$ser;
   }

   private SpillableIterator readingIterator() {
      return this.readingIterator;
   }

   private void readingIterator_$eq(final SpillableIterator x$1) {
      this.readingIterator = x$1;
   }

   public int numSpills() {
      return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps().size();
   }

   public void insert(final Object key, final Object value) {
      this.insertAll(scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(key, value)}))));
   }

   public void insertAll(final Iterator entries) {
      if (this.currentMap() == null) {
         throw new IllegalStateException("Cannot insert new elements into a map after calling iterator");
      } else {
         ObjectRef curEntry = ObjectRef.create((Object)null);
         Function2 update = (hadVal, oldVal) -> $anonfun$insertAll$1(this, curEntry, BoxesRunTime.unboxToBoolean(hadVal), oldVal);

         while(entries.hasNext()) {
            curEntry.elem = (Product2)entries.next();
            long estimatedSize = this.currentMap().estimateSize();
            if (estimatedSize > this._peakMemoryUsedBytes()) {
               this._peakMemoryUsedBytes_$eq(estimatedSize);
            }

            if (this.maybeSpill(this.currentMap(), estimatedSize)) {
               this.currentMap_$eq(new SizeTrackingAppendOnlyMap());
            }

            this.currentMap().changeValue(((Product2)curEntry.elem)._1(), update);
            this.addElementsRead();
         }

      }
   }

   public void insertAll(final Iterable entries) {
      this.insertAll(entries.iterator());
   }

   public void spill(final SizeTracker collection) {
      Iterator inMemoryIterator = this.currentMap().destructiveSortedIterator(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$keyComparator());
      DiskMapIterator diskMapIterator = this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spillMemoryIteratorToDisk(inMemoryIterator);
      this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps().$plus$eq(diskMapIterator);
   }

   public boolean forceSpill() {
      if (this.readingIterator() != null) {
         boolean isSpilled = this.readingIterator().spill();
         if (isSpilled) {
            this.currentMap_$eq((SizeTrackingAppendOnlyMap)null);
         }

         return isSpilled;
      } else if (this.currentMap().size() > 0) {
         this.spill((SizeTracker)this.currentMap());
         this.currentMap_$eq(new SizeTrackingAppendOnlyMap());
         return true;
      } else {
         return false;
      }
   }

   public DiskMapIterator org$apache$spark$util$collection$ExternalAppendOnlyMap$$spillMemoryIteratorToDisk(final Iterator inMemoryIterator) {
      Tuple2 var4 = this.diskBlockManager().createTempLocalBlock();
      if (var4 == null) {
         throw new MatchError(var4);
      } else {
         TempLocalBlockId blockId = (TempLocalBlockId)var4._1();
         File file = (File)var4._2();
         Tuple2 var3 = new Tuple2(blockId, file);
         TempLocalBlockId blockId = (TempLocalBlockId)var3._1();
         File file = (File)var3._2();
         DiskBlockObjectWriter writer = this.blockManager.getDiskWriter(blockId, file, this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$ser(), this.fileBufferSize(), this.writeMetrics());
         IntRef objectsWritten = IntRef.create(0);
         ArrayBuffer batchSizes = new ArrayBuffer();
         boolean success = false;

         try {
            while(inMemoryIterator.hasNext()) {
               Tuple2 kv = (Tuple2)inMemoryIterator.next();
               writer.write(kv._1(), kv._2());
               ++objectsWritten.elem;
               if ((long)objectsWritten.elem == this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerBatchSize()) {
                  this.flush$1(writer, batchSizes, objectsWritten);
               }
            }

            if (objectsWritten.elem > 0) {
               this.flush$1(writer, batchSizes, objectsWritten);
               writer.close();
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               writer.revertPartialWritesAndClose();
            }

            success = true;
         } finally {
            if (!success) {
               writer.closeAndDelete();
            }

         }

         return new DiskMapIterator(file, blockId, batchSizes);
      }
   }

   public Iterator destructiveIterator(final Iterator inMemoryIterator) {
      this.readingIterator_$eq(new SpillableIterator(inMemoryIterator));
      return this.readingIterator().toCompletionIterator();
   }

   public Iterator iterator() {
      if (this.currentMap() == null) {
         throw new IllegalStateException("ExternalAppendOnlyMap.iterator is destructive and should only be called once.");
      } else {
         return (Iterator)(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps().isEmpty() ? this.destructiveIterator(this.currentMap().iterator()) : new ExternalIterator());
      }
   }

   public void org$apache$spark$util$collection$ExternalAppendOnlyMap$$freeCurrentMap() {
      if (this.currentMap() != null) {
         this.currentMap_$eq((SizeTrackingAppendOnlyMap)null);
         this.releaseMemory();
      }
   }

   public int org$apache$spark$util$collection$ExternalAppendOnlyMap$$hashKey(final Tuple2 kc) {
      return ExternalAppendOnlyMap$.MODULE$.org$apache$spark$util$collection$ExternalAppendOnlyMap$$hash(kc._1());
   }

   public String toString() {
      String var10000 = this.getClass().getName();
      return var10000 + "@" + Integer.toHexString(this.hashCode());
   }

   // $FF: synthetic method
   public static final Object $anonfun$insertAll$1(final ExternalAppendOnlyMap $this, final ObjectRef curEntry$1, final boolean hadVal, final Object oldVal) {
      return hadVal ? $this.mergeValue.apply(oldVal, ((Product2)curEntry$1.elem)._2()) : $this.createCombiner.apply(((Product2)curEntry$1.elem)._2());
   }

   private final void flush$1(final DiskBlockObjectWriter writer$1, final ArrayBuffer batchSizes$1, final IntRef objectsWritten$1) {
      FileSegment segment = writer$1.commitAndGet();
      batchSizes$1.$plus$eq(BoxesRunTime.boxToLong(segment.length()));
      this._diskBytesSpilled_$eq(this._diskBytesSpilled() + segment.length());
      objectsWritten$1.elem = 0;
   }

   public ExternalAppendOnlyMap(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiners, final Serializer serializer, final BlockManager blockManager, final TaskContext context, final SerializerManager serializerManager) {
      super(context.taskMemoryManager());
      this.createCombiner = createCombiner;
      this.mergeValue = mergeValue;
      this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$mergeCombiners = mergeCombiners;
      this.blockManager = blockManager;
      this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$context = context;
      this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerManager = serializerManager;
      IterableOnce.$init$(this);
      IterableOnceOps.$init$(this);
      IterableOps.$init$(this);
      IterableFactoryDefaults.$init$(this);
      Iterable.$init$(this);
      if (context == null) {
         throw new IllegalStateException("Spillable collections should not be instantiated outside of tasks");
      } else {
         this.currentMap = new SizeTrackingAppendOnlyMap();
         this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps = new ArrayBuffer();
         this.sparkConf = SparkEnv$.MODULE$.get().conf();
         this.diskBlockManager = blockManager.diskBlockManager();
         this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerBatchSize = BoxesRunTime.unboxToLong(this.sparkConf().get(package$.MODULE$.SHUFFLE_SPILL_BATCH_SIZE()));
         this._diskBytesSpilled = 0L;
         this.fileBufferSize = (int)BoxesRunTime.unboxToLong(this.sparkConf().get(package$.MODULE$.SHUFFLE_FILE_BUFFER_SIZE())) * 1024;
         this.writeMetrics = new ShuffleWriteMetrics();
         this._peakMemoryUsedBytes = 0L;
         this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$keyComparator = new HashComparator();
         this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$ser = serializer.newInstance();
         this.readingIterator = null;
      }
   }

   public ExternalAppendOnlyMap(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiners, final Serializer serializer, final BlockManager blockManager) {
      this(createCombiner, mergeValue, mergeCombiners, serializer, blockManager, TaskContext$.MODULE$.get(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$7());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ExternalIterator implements Iterator {
      private final PriorityQueue mergeHeap;
      private final Iterator sortedMap;
      private final Seq inputStreams;
      // $FF: synthetic field
      public final ExternalAppendOnlyMap $outer;

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

      public Map toMap(final .less.colon.less ev) {
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

      private PriorityQueue mergeHeap() {
         return this.mergeHeap;
      }

      private Iterator sortedMap() {
         return this.sortedMap;
      }

      private Seq inputStreams() {
         return this.inputStreams;
      }

      private void readNextHashCode(final BufferedIterator it, final ArrayBuffer buf) {
         if (it.hasNext()) {
            Tuple2 kc = (Tuple2)it.next();
            buf.$plus$eq(kc);
            int minHash = this.org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$hashKey(kc);

            while(it.hasNext() && ((Tuple2)it.head())._1().hashCode() == minHash) {
               kc = (Tuple2)it.next();
               buf.$plus$eq(kc);
            }

         }
      }

      private Object mergeIfKeyExists(final Object key, final Object baseCombiner, final StreamBuffer buffer) {
         for(int i = 0; i < buffer.pairs().length(); ++i) {
            Tuple2 pair = (Tuple2)buffer.pairs().apply(i);
            if (BoxesRunTime.equals(pair._1(), key)) {
               this.removeFromBuffer(buffer.pairs(), i);
               return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$mergeCombiners.apply(baseCombiner, pair._2());
            }
         }

         return baseCombiner;
      }

      private Object removeFromBuffer(final ArrayBuffer buffer, final int index) {
         Object elem = buffer.apply(index);
         buffer.update(index, buffer.apply(buffer.size() - 1));
         buffer.dropRightInPlace(1);
         return elem;
      }

      public boolean hasNext() {
         return this.mergeHeap().nonEmpty();
      }

      public Tuple2 next() {
         if (this.mergeHeap().isEmpty()) {
            throw new NoSuchElementException();
         } else {
            StreamBuffer minBuffer = (StreamBuffer)this.mergeHeap().dequeue();
            ArrayBuffer minPairs = minBuffer.pairs();
            int minHash = minBuffer.minKeyHash();
            Tuple2 minPair = (Tuple2)this.removeFromBuffer(minPairs, 0);
            Object minKey = minPair._1();
            Object minCombiner = minPair._2();
            scala.Predef..MODULE$.assert(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$hashKey(minPair) == minHash);
            ArrayBuffer mergedBuffers = (ArrayBuffer)scala.collection.mutable.ArrayBuffer..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new StreamBuffer[]{minBuffer}));

            while(this.mergeHeap().nonEmpty() && ((StreamBuffer)this.mergeHeap().head()).minKeyHash() == minHash) {
               StreamBuffer newBuffer = (StreamBuffer)this.mergeHeap().dequeue();
               minCombiner = this.mergeIfKeyExists(minKey, minCombiner, newBuffer);
               mergedBuffers.$plus$eq(newBuffer);
            }

            mergedBuffers.foreach((buffer) -> {
               $anonfun$next$1(this, buffer);
               return BoxedUnit.UNIT;
            });
            return new Tuple2(minKey, minCombiner);
         }
      }

      // $FF: synthetic method
      public ExternalAppendOnlyMap org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$new$1(final ExternalIterator $this, final BufferedIterator it) {
         ArrayBuffer kcPairs = new ArrayBuffer();
         $this.readNextHashCode(it, kcPairs);
         if (kcPairs.length() > 0) {
            $this.mergeHeap().enqueue(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new StreamBuffer[]{$this.new StreamBuffer(it, kcPairs)}));
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$next$1(final ExternalIterator $this, final StreamBuffer buffer) {
         if (buffer.isEmpty()) {
            $this.readNextHashCode(buffer.iterator(), buffer.pairs());
         }

         if (!buffer.isEmpty()) {
            $this.mergeHeap().enqueue(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new StreamBuffer[]{buffer}));
         }
      }

      public ExternalIterator() {
         if (ExternalAppendOnlyMap.this == null) {
            throw null;
         } else {
            this.$outer = ExternalAppendOnlyMap.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.mergeHeap = new PriorityQueue(scala.math.Ordering..MODULE$.ordered(scala.Predef..MODULE$.$conforms()));
            this.sortedMap = ExternalAppendOnlyMap.this.destructiveIterator(ExternalAppendOnlyMap.this.currentMap().destructiveSortedIterator(ExternalAppendOnlyMap.this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$keyComparator()));
            this.inputStreams = (Seq)((IterableOps)(new scala.collection.immutable..colon.colon(this.sortedMap(), scala.collection.immutable.Nil..MODULE$)).$plus$plus(ExternalAppendOnlyMap.this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$spilledMaps())).map((it) -> it.buffered());
            this.inputStreams().foreach((it) -> {
               $anonfun$new$1(this, it);
               return BoxedUnit.UNIT;
            });
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }

      private class StreamBuffer implements Comparable {
         private final BufferedIterator iterator;
         private final ArrayBuffer pairs;
         // $FF: synthetic field
         public final ExternalIterator $outer;

         public BufferedIterator iterator() {
            return this.iterator;
         }

         public ArrayBuffer pairs() {
            return this.pairs;
         }

         public boolean isEmpty() {
            return this.pairs().length() == 0;
         }

         public int minKeyHash() {
            scala.Predef..MODULE$.assert(this.pairs().length() > 0);
            return this.org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$StreamBuffer$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$hashKey((Tuple2)this.pairs().head());
         }

         public int compareTo(final StreamBuffer other) {
            if (other.minKeyHash() < this.minKeyHash()) {
               return -1;
            } else {
               return other.minKeyHash() == this.minKeyHash() ? 0 : 1;
            }
         }

         // $FF: synthetic method
         public ExternalIterator org$apache$spark$util$collection$ExternalAppendOnlyMap$ExternalIterator$StreamBuffer$$$outer() {
            return this.$outer;
         }

         public StreamBuffer(final BufferedIterator iterator, final ArrayBuffer pairs) {
            this.iterator = iterator;
            this.pairs = pairs;
            if (ExternalIterator.this == null) {
               throw null;
            } else {
               this.$outer = ExternalIterator.this;
               super();
            }
         }
      }
   }

   private class DiskMapIterator implements Iterator {
      private final File file;
      private final BlockId blockId;
      private final ArrayBuffer batchOffsets;
      private int batchIndex;
      private FileInputStream fileStream;
      private DeserializationStream deserializeStream;
      private Iterator batchIterator;
      private int objectsRead;
      // $FF: synthetic field
      public final ExternalAppendOnlyMap $outer;

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

      public Map toMap(final .less.colon.less ev) {
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

      private ArrayBuffer batchOffsets() {
         return this.batchOffsets;
      }

      private int batchIndex() {
         return this.batchIndex;
      }

      private void batchIndex_$eq(final int x$1) {
         this.batchIndex = x$1;
      }

      private FileInputStream fileStream() {
         return this.fileStream;
      }

      private void fileStream_$eq(final FileInputStream x$1) {
         this.fileStream = x$1;
      }

      private DeserializationStream deserializeStream() {
         return this.deserializeStream;
      }

      private void deserializeStream_$eq(final DeserializationStream x$1) {
         this.deserializeStream = x$1;
      }

      private Iterator batchIterator() {
         return this.batchIterator;
      }

      private void batchIterator_$eq(final Iterator x$1) {
         this.batchIterator = x$1;
      }

      private int objectsRead() {
         return this.objectsRead;
      }

      private void objectsRead_$eq(final int x$1) {
         this.objectsRead = x$1;
      }

      private Iterator nextBatchIterator() {
         if (this.batchIndex() < this.batchOffsets().length() - 1) {
            if (this.deserializeStream() != null) {
               this.deserializeStream().close();
               this.fileStream().close();
               this.deserializeStream_$eq((DeserializationStream)null);
               this.fileStream_$eq((FileInputStream)null);
            }

            long start = BoxesRunTime.unboxToLong(this.batchOffsets().apply(this.batchIndex()));
            this.fileStream_$eq(new FileInputStream(this.file));
            this.fileStream().getChannel().position(start);
            this.batchIndex_$eq(this.batchIndex() + 1);
            long end = BoxesRunTime.unboxToLong(this.batchOffsets().apply(this.batchIndex()));
            scala.Predef..MODULE$.assert(end >= start, () -> "start = " + start + ", end = " + end + ", batchOffsets = " + this.batchOffsets().mkString("[", ", ", "]"));
            BufferedInputStream bufferedStream = new BufferedInputStream(ByteStreams.limit(this.fileStream(), end - start));
            InputStream wrappedStream = this.org$apache$spark$util$collection$ExternalAppendOnlyMap$DiskMapIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerManager.wrapStream(this.blockId, (InputStream)bufferedStream);
            this.deserializeStream_$eq(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$DiskMapIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$ser().deserializeStream(wrappedStream));
            return this.deserializeStream().asKeyValueIterator();
         } else {
            this.cleanup();
            return null;
         }
      }

      private Tuple2 readNextItem() {
         Tuple2 item = (Tuple2)this.batchIterator().next();
         this.objectsRead_$eq(this.objectsRead() + 1);
         if ((long)this.objectsRead() == this.org$apache$spark$util$collection$ExternalAppendOnlyMap$DiskMapIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$serializerBatchSize()) {
            this.objectsRead_$eq(0);
            this.batchIterator_$eq(this.nextBatchIterator());
         }

         return item;
      }

      public boolean hasNext() {
         if (this.batchIterator() == null) {
            this.batchIterator_$eq(this.nextBatchIterator());
            if (this.batchIterator() == null) {
               return false;
            }
         }

         return this.batchIterator().hasNext();
      }

      public Tuple2 next() {
         if (!this.hasNext()) {
            throw new NoSuchElementException();
         } else {
            return this.readNextItem();
         }
      }

      private void cleanup() {
         this.batchIndex_$eq(this.batchOffsets().length());
         if (this.deserializeStream() != null) {
            this.deserializeStream().close();
            this.deserializeStream_$eq((DeserializationStream)null);
         }

         if (this.fileStream() != null) {
            this.fileStream().close();
            this.fileStream_$eq((FileInputStream)null);
         }

         if (this.file.exists()) {
            if (!this.file.delete()) {
               this.org$apache$spark$util$collection$ExternalAppendOnlyMap$DiskMapIterator$$$outer().logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$util$collection$ExternalAppendOnlyMap$DiskMapIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error deleting ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILE_NAME..MODULE$, this.file)})))));
            }
         }
      }

      // $FF: synthetic method
      public ExternalAppendOnlyMap org$apache$spark$util$collection$ExternalAppendOnlyMap$DiskMapIterator$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$new$3(final DiskMapIterator $this, final TaskContext context) {
         $this.cleanup();
      }

      public DiskMapIterator(final File file, final BlockId blockId, final ArrayBuffer batchSizes) {
         this.file = file;
         this.blockId = blockId;
         if (ExternalAppendOnlyMap.this == null) {
            throw null;
         } else {
            this.$outer = ExternalAppendOnlyMap.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.batchOffsets = (ArrayBuffer)batchSizes.scanLeft(BoxesRunTime.boxToLong(0L), (JFunction2.mcJJJ.sp)(x$2, x$3) -> x$2 + x$3);
            scala.Predef..MODULE$.assert(file.length() == BoxesRunTime.unboxToLong(this.batchOffsets().last()), () -> {
               long var10000 = this.file.length();
               return "File length is not equal to the last batch offset:\n    file length = " + var10000 + "\n    last batch offset = " + this.batchOffsets().last() + "\n    all batch offsets = " + this.batchOffsets().mkString(",");
            });
            this.batchIndex = 0;
            this.fileStream = null;
            this.deserializeStream = null;
            this.batchIterator = null;
            this.objectsRead = 0;
            ExternalAppendOnlyMap.this.org$apache$spark$util$collection$ExternalAppendOnlyMap$$context.addTaskCompletionListener((Function1)((context) -> {
               $anonfun$new$3(this, context);
               return BoxedUnit.UNIT;
            }));
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private class SpillableIterator implements Iterator {
      private Iterator upstream;
      private final Object SPILL_LOCK;
      private Tuple2 cur;
      private boolean hasSpilled;
      // $FF: synthetic field
      public final ExternalAppendOnlyMap $outer;

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

      public Map toMap(final .less.colon.less ev) {
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

      public Iterator upstream() {
         return this.upstream;
      }

      public void upstream_$eq(final Iterator x$1) {
         this.upstream = x$1;
      }

      private Object SPILL_LOCK() {
         return this.SPILL_LOCK;
      }

      private Tuple2 cur() {
         return this.cur;
      }

      private void cur_$eq(final Tuple2 x$1) {
         this.cur = x$1;
      }

      private boolean hasSpilled() {
         return this.hasSpilled;
      }

      private void hasSpilled_$eq(final boolean x$1) {
         this.hasSpilled = x$1;
      }

      public boolean spill() {
         synchronized(this.SPILL_LOCK()){}

         boolean var2;
         try {
            boolean var10000;
            if (this.hasSpilled()) {
               var10000 = false;
            } else {
               this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Task ", " force spilling"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.TASK_ATTEMPT_ID..MODULE$, BoxesRunTime.boxToLong(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$context.taskAttemptId()))}))).$plus(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" in-memory map to disk and it will release "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " memory"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_BYTES..MODULE$, org.apache.spark.util.Utils$.MODULE$.bytesToString(this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().getUsed()))}))))));
               DiskMapIterator nextUpstream = this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$spillMemoryIteratorToDisk(this.upstream());
               scala.Predef..MODULE$.assert(!this.upstream().hasNext());
               this.hasSpilled_$eq(true);
               this.upstream_$eq(nextUpstream);
               var10000 = true;
            }

            var2 = var10000;
         } catch (Throwable var5) {
            throw var5;
         }

         return var2;
      }

      private void destroy() {
         this.org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer().org$apache$spark$util$collection$ExternalAppendOnlyMap$$freeCurrentMap();
         this.upstream_$eq(scala.package..MODULE$.Iterator().empty());
      }

      public CompletionIterator toCompletionIterator() {
         return CompletionIterator$.MODULE$.apply(this, (JFunction0.mcV.sp)() -> this.destroy());
      }

      public Tuple2 readNext() {
         synchronized(this.SPILL_LOCK()){}

         Tuple2 var2;
         try {
            var2 = this.upstream().hasNext() ? (Tuple2)this.upstream().next() : null;
         } catch (Throwable var4) {
            throw var4;
         }

         return var2;
      }

      public boolean hasNext() {
         return this.cur() != null;
      }

      public Tuple2 next() {
         Tuple2 r = this.cur();
         this.cur_$eq(this.readNext());
         return r;
      }

      // $FF: synthetic method
      public ExternalAppendOnlyMap org$apache$spark$util$collection$ExternalAppendOnlyMap$SpillableIterator$$$outer() {
         return this.$outer;
      }

      public SpillableIterator(final Iterator upstream) {
         this.upstream = upstream;
         if (ExternalAppendOnlyMap.this == null) {
            throw null;
         } else {
            this.$outer = ExternalAppendOnlyMap.this;
            super();
            IterableOnce.$init$(this);
            IterableOnceOps.$init$(this);
            Iterator.$init$(this);
            this.SPILL_LOCK = new Object();
            this.cur = this.readNext();
            this.hasSpilled = false;
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   private static class HashComparator implements Comparator {
      public Comparator reversed() {
         return super.reversed();
      }

      public Comparator thenComparing(final Comparator x$1) {
         return super.thenComparing(x$1);
      }

      public Comparator thenComparing(final Function x$1, final Comparator x$2) {
         return super.thenComparing(x$1, x$2);
      }

      public Comparator thenComparing(final Function x$1) {
         return super.thenComparing(x$1);
      }

      public Comparator thenComparingInt(final ToIntFunction x$1) {
         return super.thenComparingInt(x$1);
      }

      public Comparator thenComparingLong(final ToLongFunction x$1) {
         return super.thenComparingLong(x$1);
      }

      public Comparator thenComparingDouble(final ToDoubleFunction x$1) {
         return super.thenComparingDouble(x$1);
      }

      public int compare(final Object key1, final Object key2) {
         int hash1 = ExternalAppendOnlyMap$.MODULE$.org$apache$spark$util$collection$ExternalAppendOnlyMap$$hash(key1);
         int hash2 = ExternalAppendOnlyMap$.MODULE$.org$apache$spark$util$collection$ExternalAppendOnlyMap$$hash(key2);
         if (hash1 < hash2) {
            return -1;
         } else {
            return hash1 == hash2 ? 0 : 1;
         }
      }

      public HashComparator() {
      }
   }
}
