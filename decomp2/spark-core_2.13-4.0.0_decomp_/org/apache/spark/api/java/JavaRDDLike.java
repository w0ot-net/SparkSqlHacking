package org.apache.spark.api.java;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.apache.spark.JavaFutureActionWrapper;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.function.DoubleFlatMapFunction;
import org.apache.spark.api.java.function.DoubleFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.FlatMapFunction2;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.partial.PartialResult;
import org.apache.spark.rdd.RDD;
import org.apache.spark.rdd.RDD$;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.jdk.CollectionConverters.;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015=ea\u0002+V!\u0003\r\t\u0001\u0019\u0005\u0006i\u0002!\t!\u001e\u0005\u0006s\u00021\tA\u001f\u0005\n\u0003O\u0001!\u0019!D\u0002\u0003SAq!a\u0007\u0001\r\u0003\t9\u0004C\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005M\u0003\u0001\"\u0001\u0002V!9\u0011q\u000e\u0001\u0005\u0002\u0005E\u0004bBA@\u0001\u0011\u0005\u0011\u0011\u0011\u0005\b\u0003\u0013\u0003A\u0011AA+\u0011\u001d\tY\t\u0001C\u0001\u0003\u001bCq!a'\u0001\t\u0003\ti\nC\u0004\u00024\u0002!\t!!.\t\u000f\u0005U\u0007\u0001\"\u0001\u0002X\"I!\u0011\u0001\u0001\u0012\u0002\u0013\u0005!1\u0001\u0005\b\u00057\u0001A\u0011\u0001B\u000f\u0011\u001d\u0011\t\u0004\u0001C\u0001\u0005gAqA!\u0015\u0001\t\u0003\u0011\u0019\u0006C\u0004\u0003h\u0001!\tA!\u001b\t\u000f\tM\u0004\u0001\"\u0001\u0003v!9!1\u0012\u0001\u0005\u0002\t5\u0005b\u0002BF\u0001\u0011\u0005!1\u0014\u0005\b\u0005W\u0003A\u0011\u0001BW\u0011\u001d\u0011\u0019\f\u0001C\u0001\u0005kCqAa+\u0001\t\u0003\u00119\rC\u0004\u00034\u0002!\tA!4\t\u000f\t\u0005\b\u0001\"\u0001\u0003d\"9!Q\u001e\u0001\u0005\u0002\t=\bb\u0002B{\u0001\u0011\u0005!q\u001f\u0005\b\u0007\u001f\u0001A\u0011AB\t\u0011\u001d\u0019y\u0001\u0001C\u0001\u0007KAqaa\u000e\u0001\t\u0003\u0019I\u0004C\u0004\u00048\u0001!\ta!\u0015\t\u000f\r]\u0002\u0001\"\u0001\u0004X!91q\u0007\u0001\u0005\u0002\r\u0015\u0004bBB\u001c\u0001\u0011\u000511\u000f\u0005\b\u0007\u0003\u0003A\u0011ABB\u0011\u001d\u0019I\n\u0001C\u0001\u00077Cqa!1\u0001\t\u0003\u0019\u0019\rC\u0004\u0004N\u0002!\taa1\t\u000f\r=\u0007\u0001\"\u0001\u0004R\"91q\u001b\u0001\u0005\u0002\re\u0007bBBn\u0001\u0011\u00051Q\u001c\u0005\b\u0007?\u0004A\u0011ABq\u0011\u001d\u0019y\u000f\u0001C\u0001\u0007cDqaa>\u0001\t\u0003\u0019I\u0010C\u0004\u0004x\u0002!\t\u0001\"\u0001\t\u000f\u0011\u0015\u0001\u0001\"\u0001\u0005\b!9A\u0011\u0003\u0001\u0005\u0002\u0011M\u0001b\u0002C\u0016\u0001\u0011\u0005AQ\u0006\u0005\b\tW\u0001A\u0011\u0001C!\u0011\u001d!Y\u0003\u0001C\u0001\t'Bq\u0001b\u001b\u0001\t\u0003!i\u0007C\u0004\u0005t\u0001!\t\u0001\"\u001e\t\u000f\u0011M\u0004\u0001\"\u0001\u0005\u0018\"9A1\u0014\u0001\u0005\u0002\u0011u\u0005b\u0002CQ\u0001\u0011\u0005A1\u0015\u0005\b\tC\u0003A\u0011\u0001CW\u0011\u001d!\t\f\u0001C\u0001\tgCq\u0001\"/\u0001\t\u0003!Y\fC\u0004\u0005:\u0002!\t\u0001b1\t\u000f\u00115\u0007\u0001\"\u0001\u0005P\"9A\u0011\u001b\u0001\u0005\u0002\u0011M\u0007b\u0002Ck\u0001\u0011\u0005Aq\u001b\u0005\b\t+\u0004A\u0011\u0001Co\u0011\u001d)I\u0001\u0001C\u0001\u000b\u0017Aq!b\u0004\u0001\t\u0003)\t\u0002\u0003\u0004\u0006 \u0001!\t!\u001e\u0005\b\u000bC\u0001A\u0011AC\u0012\u0011\u001d))\u0003\u0001C\u0001\u000bOAq!b\u000b\u0001\t\u0003)i\u0003C\u0004\u00060\u0001!\t!\"\r\t\u000f\u0015=\u0002\u0001\"\u0001\u0006@!9Q1\t\u0001\u0005\u0002\u0015\u0015\u0003bBC&\u0001\u0011\u0005QQ\n\u0005\b\u000b#\u0002A\u0011AC*\u0011\u001d)\u0019\u0005\u0001C\u0001\u000b/Bq!b\u0017\u0001\t\u0003)i\u0006C\u0004\u0006d\u0001!\t!\"\f\t\u000f\u0015\u0015\u0004\u0001\"\u0001\u0006h!9Qq\u000e\u0001\u0005\u0002\u0015E\u0004bBC;\u0001\u0011\u0005Qq\u000f\u0005\b\u000bw\u0002A\u0011AC?\u0011\u001d)I\t\u0001C\u0001\u000b\u0017\u00131BS1wCJ#E\tT5lK*\u0011akV\u0001\u0005U\u00064\u0018M\u0003\u0002Y3\u0006\u0019\u0011\r]5\u000b\u0005i[\u0016!B:qCJ\\'B\u0001/^\u0003\u0019\t\u0007/Y2iK*\ta,A\u0002pe\u001e\u001c\u0001!\u0006\u0003b\u0003\u001bi8c\u0001\u0001cQB\u00111MZ\u0007\u0002I*\tQ-A\u0003tG\u0006d\u0017-\u0003\u0002hI\n1\u0011I\\=SK\u001a\u0004\"![9\u000f\u0005)|gBA6o\u001b\u0005a'BA7`\u0003\u0019a$o\\8u}%\tQ-\u0003\u0002qI\u00069\u0001/Y2lC\u001e,\u0017B\u0001:t\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\u0001H-\u0001\u0004%S:LG\u000f\n\u000b\u0002mB\u00111m^\u0005\u0003q\u0012\u0014A!\u00168ji\u00069qO]1q%\u0012#EcA>\u0002\u001aA\u0011A0 \u0007\u0001\t\u0015q\bA1\u0001\u0000\u0005\u0011!\u0006.[:\u0012\t\u0005\u0005\u0011q\u0001\t\u0004G\u0006\r\u0011bAA\u0003I\n9aj\u001c;iS:<\u0007CBA\u0005\u0001\u0005-10D\u0001V!\ra\u0018Q\u0002\u0003\b\u0003\u001f\u0001!\u0019AA\t\u0005\u0005!\u0016\u0003BA\u0001\u0003'\u00012aYA\u000b\u0013\r\t9\u0002\u001a\u0002\u0004\u0003:L\bbBA\u000e\u0005\u0001\u0007\u0011QD\u0001\u0004e\u0012$\u0007CBA\u0010\u0003G\tY!\u0004\u0002\u0002\")\u0019\u00111D-\n\t\u0005\u0015\u0012\u0011\u0005\u0002\u0004%\u0012#\u0015\u0001C2mCN\u001cH+Y4\u0016\u0005\u0005-\u0002CBA\u0017\u0003g\tY!\u0004\u0002\u00020)\u0019\u0011\u0011\u00073\u0002\u000fI,g\r\\3di&!\u0011QGA\u0018\u0005!\u0019E.Y:t)\u0006<WCAA\u000f\u0003)\u0001\u0018M\u001d;ji&|gn]\u000b\u0003\u0003{\u0001b!a\u0010\u0002H\u0005-SBAA!\u0015\u0011\t\u0019%!\u0012\u0002\tU$\u0018\u000e\u001c\u0006\u0002-&!\u0011\u0011JA!\u0005\u0011a\u0015n\u001d;\u0011\t\u00055\u0013qJ\u0007\u00023&\u0019\u0011\u0011K-\u0003\u0013A\u000b'\u000f^5uS>t\u0017\u0001E4fi:+X\u000eU1si&$\u0018n\u001c8t+\t\t9\u0006E\u0002d\u00033J1!a\u0017e\u0005\rIe\u000e\u001e\u0015\u0006\r\u0005}\u00131\u000e\t\u0005\u0003C\n9'\u0004\u0002\u0002d)\u0019\u0011QM-\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002j\u0005\r$!B*j]\u000e,\u0017EAA7\u0003\u0015\tdF\u000e\u00181\u0003-\u0001\u0018M\u001d;ji&|g.\u001a:\u0016\u0005\u0005M\u0004CBA\u0005\u0003k\nI(C\u0002\u0002xU\u0013\u0001b\u00149uS>t\u0017\r\u001c\t\u0005\u0003\u001b\nY(C\u0002\u0002~e\u00131\u0002U1si&$\u0018n\u001c8fe\u000691m\u001c8uKb$XCAAB!\u0011\ti%!\"\n\u0007\u0005\u001d\u0015L\u0001\u0007Ta\u0006\u00148nQ8oi\u0016DH/\u0001\u0002jI\u0006yq-\u001a;Ti>\u0014\u0018mZ3MKZ,G.\u0006\u0002\u0002\u0010B!\u0011\u0011SAL\u001b\t\t\u0019JC\u0002\u0002\u0016f\u000bqa\u001d;pe\u0006<W-\u0003\u0003\u0002\u001a\u0006M%\u0001D*u_J\fw-\u001a'fm\u0016d\u0017\u0001C5uKJ\fGo\u001c:\u0015\r\u0005}\u0015QUAU!\u0019\ty$!)\u0002\f%!\u00111UA!\u0005!IE/\u001a:bi>\u0014\bbBAT\u0017\u0001\u0007\u00111J\u0001\u0006gBd\u0017\u000e\u001e\u0005\b\u0003W[\u0001\u0019AAW\u0003-!\u0018m]6D_:$X\r\u001f;\u0011\t\u00055\u0013qV\u0005\u0004\u0003cK&a\u0003+bg.\u001cuN\u001c;fqR\f1!\\1q+\u0011\t9,!1\u0015\t\u0005e\u0016Q\u0019\t\u0007\u0003\u0013\tY,a0\n\u0007\u0005uVKA\u0004KCZ\f'\u000b\u0012#\u0011\u0007q\f\t\rB\u0004\u0002D2\u0011\r!!\u0005\u0003\u0003ICq!a2\r\u0001\u0004\tI-A\u0001g!!\tY-!5\u0002\f\u0005}VBAAg\u0015\r\ty-V\u0001\tMVt7\r^5p]&!\u00111[Ag\u0005!1UO\\2uS>t\u0017AF7baB\u000b'\u000f^5uS>t7oV5uQ&sG-\u001a=\u0016\t\u0005e\u0017q\u001c\u000b\u0007\u00037\f\t/a>\u0011\r\u0005%\u00111XAo!\ra\u0018q\u001c\u0003\b\u0003\u0007l!\u0019AA\t\u0011\u001d\t9-\u0004a\u0001\u0003G\u0004\"\"a3\u0002f\u0006%\u0018qTA{\u0013\u0011\t9/!4\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004\u0003BAv\u0003cl!!!<\u000b\t\u0005=\u0018QI\u0001\u0005Y\u0006tw-\u0003\u0003\u0002t\u00065(aB%oi\u0016<WM\u001d\t\u0007\u0003\u007f\t\t+!8\t\u0013\u0005eX\u0002%AA\u0002\u0005m\u0018!\u00069sKN,'O^3t!\u0006\u0014H/\u001b;j_:Lgn\u001a\t\u0004G\u0006u\u0018bAA\u0000I\n9!i\\8mK\u0006t\u0017\u0001I7baB\u000b'\u000f^5uS>t7oV5uQ&sG-\u001a=%I\u00164\u0017-\u001e7uII*BA!\u0002\u0003\u001aU\u0011!q\u0001\u0016\u0005\u0003w\u0014Ia\u000b\u0002\u0003\fA!!Q\u0002B\u000b\u001b\t\u0011yA\u0003\u0003\u0003\u0012\tM\u0011!C;oG\",7m[3e\u0015\r\t)\u0007Z\u0005\u0005\u0005/\u0011yAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$q!a1\u000f\u0005\u0004\t\t\"A\u0006nCB$v\u000eR8vE2,W\u0003\u0002B\u0010\u0005_!BA!\t\u0003(A!\u0011\u0011\u0002B\u0012\u0013\r\u0011)#\u0016\u0002\u000e\u0015\u00064\u0018\rR8vE2,'\u000b\u0012#\t\u000f\u0005\u001dw\u00021\u0001\u0003*A1\u00111\u001aB\u0016\u0003\u0017IAA!\f\u0002N\nqAi\\;cY\u00164UO\\2uS>tGaBAb\u001f\t\u0007\u0011\u0011C\u0001\n[\u0006\u0004Hk\u001c)bSJ,bA!\u000e\u0003@\t\u0015C\u0003\u0002B\u001c\u0005\u0013\u0002\u0002\"!\u0003\u0003:\tu\"1I\u0005\u0004\u0005w)&a\u0003&bm\u0006\u0004\u0016-\u001b:S\t\u0012\u00032\u0001 B \t\u001d\u0011\t\u0005\u0005b\u0001\u0003#\u0011!a\u0013\u001a\u0011\u0007q\u0014)\u0005B\u0004\u0003HA\u0011\r!!\u0005\u0003\u0005Y\u0013\u0004bBAd!\u0001\u0007!1\n\t\u000b\u0003\u0017\u0014i%a\u0003\u0003>\t\r\u0013\u0002\u0002B(\u0003\u001b\u0014A\u0002U1je\u001a+hn\u0019;j_:\fqA\u001a7bi6\u000b\u0007/\u0006\u0003\u0003V\tmC\u0003\u0002B,\u0005?\u0002b!!\u0003\u0002<\ne\u0003c\u0001?\u0003\\\u00119!QL\tC\u0002\u0005E!!A+\t\u000f\u0005\u001d\u0017\u00031\u0001\u0003bAA\u00111\u001aB2\u0003\u0017\u0011I&\u0003\u0003\u0003f\u00055'a\u0004$mCRl\u0015\r\u001d$v]\u000e$\u0018n\u001c8\u0002\u001f\u0019d\u0017\r^'baR{Gi\\;cY\u0016$BA!\t\u0003l!9\u0011q\u0019\nA\u0002\t5\u0004CBAf\u0005_\nY!\u0003\u0003\u0003r\u00055'!\u0006#pk\ndWM\u00127bi6\u000b\u0007OR;oGRLwN\\\u0001\u000eM2\fG/T1q)>\u0004\u0016-\u001b:\u0016\r\t]$Q\u0010BA)\u0011\u0011IHa!\u0011\u0011\u0005%!\u0011\bB>\u0005\u007f\u00022\u0001 B?\t\u001d\u0011\te\u0005b\u0001\u0003#\u00012\u0001 BA\t\u001d\u00119e\u0005b\u0001\u0003#Aq!a2\u0014\u0001\u0004\u0011)\t\u0005\u0006\u0002L\n\u001d\u00151\u0002B>\u0005\u007fJAA!#\u0002N\n\u0019\u0002+Y5s\r2\fG/T1q\rVt7\r^5p]\u0006iQ.\u00199QCJ$\u0018\u000e^5p]N,BAa$\u0003\u0016R!!\u0011\u0013BL!\u0019\tI!a/\u0003\u0014B\u0019AP!&\u0005\u000f\tuCC1\u0001\u0002\u0012!9\u0011q\u0019\u000bA\u0002\te\u0005\u0003CAf\u0005G\nyJa%\u0016\t\tu%1\u0015\u000b\u0007\u0005?\u0013)K!+\u0011\r\u0005%\u00111\u0018BQ!\ra(1\u0015\u0003\b\u0005;*\"\u0019AA\t\u0011\u001d\t9-\u0006a\u0001\u0005O\u0003\u0002\"a3\u0003d\u0005}%\u0011\u0015\u0005\b\u0003s,\u0002\u0019AA~\u0003Ui\u0017\r\u001d)beRLG/[8ogR{Gi\\;cY\u0016$BA!\t\u00030\"9\u0011q\u0019\fA\u0002\tE\u0006CBAf\u0005_\ny*A\nnCB\u0004\u0016M\u001d;ji&|gn\u001d+p!\u0006L'/\u0006\u0004\u00038\nu&\u0011\u0019\u000b\u0005\u0005s\u0013\u0019\r\u0005\u0005\u0002\n\te\"1\u0018B`!\ra(Q\u0018\u0003\b\u0005\u0003:\"\u0019AA\t!\ra(\u0011\u0019\u0003\b\u0005\u000f:\"\u0019AA\t\u0011\u001d\t9m\u0006a\u0001\u0005\u000b\u0004\"\"a3\u0003\b\u0006}%1\u0018B`)\u0019\u0011\tC!3\u0003L\"9\u0011q\u0019\rA\u0002\tE\u0006bBA}1\u0001\u0007\u00111`\u000b\u0007\u0005\u001f\u0014)N!7\u0015\r\tE'1\u001cBp!!\tIA!\u000f\u0003T\n]\u0007c\u0001?\u0003V\u00129!\u0011I\rC\u0002\u0005E\u0001c\u0001?\u0003Z\u00129!qI\rC\u0002\u0005E\u0001bBAd3\u0001\u0007!Q\u001c\t\u000b\u0003\u0017\u00149)a(\u0003T\n]\u0007bBA}3\u0001\u0007\u00111`\u0001\u0011M>\u0014X-Y2i!\u0006\u0014H/\u001b;j_:$2A\u001eBs\u0011\u001d\t9M\u0007a\u0001\u0005O\u0004b!a3\u0003j\u0006}\u0015\u0002\u0002Bv\u0003\u001b\u0014ABV8jI\u001a+hn\u0019;j_:\fAa\u001a7p[R\u0011!\u0011\u001f\t\u0007\u0003\u0013\tYLa=\u0011\r\u0005}\u0012qIA\u0006\u0003%\u0019\u0017M\u001d;fg&\fg.\u0006\u0003\u0003z\n}H\u0003\u0002B~\u0007\u0003\u0001\u0002\"!\u0003\u0003:\u0005-!Q \t\u0004y\n}Ha\u0002B/9\t\u0007\u0011\u0011\u0003\u0005\b\u0007\u0007a\u0002\u0019AB\u0003\u0003\u0015yG\u000f[3sa\u0011\u00199aa\u0003\u0011\u000f\u0005%\u0001A!@\u0004\nA\u0019Apa\u0003\u0005\u0019\r51\u0011AA\u0001\u0002\u0003\u0015\t!!\u0005\u0003\u0007}#\u0013'A\u0004he>,\bOQ=\u0016\t\rM1\u0011\u0004\u000b\u0005\u0007+\u0019\t\u0003\u0005\u0005\u0002\n\te2qCB\u000e!\ra8\u0011\u0004\u0003\b\u0005;j\"\u0019AA\t!\u0019\tYo!\b\u0002\f%!1qDAw\u0005!IE/\u001a:bE2,\u0007bBAd;\u0001\u000711\u0005\t\t\u0003\u0017\f\t.a\u0003\u0004\u0018U!1qEB\u0017)\u0019\u0019Ica\f\u00044AA\u0011\u0011\u0002B\u001d\u0007W\u0019Y\u0002E\u0002}\u0007[!qA!\u0018\u001f\u0005\u0004\t\t\u0002C\u0004\u0002Hz\u0001\ra!\r\u0011\u0011\u0005-\u0017\u0011[A\u0006\u0007WAqa!\u000e\u001f\u0001\u0004\t9&A\u0007ok6\u0004\u0016M\u001d;ji&|gn]\u0001\u0005a&\u0004X\r\u0006\u0003\u0004<\r5\u0003CBA\u0005\u0003w\u001bi\u0004\u0005\u0003\u0004@\r\u001dc\u0002BB!\u0007\u0007\u0002\"a\u001b3\n\u0007\r\u0015C-\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0007\u0013\u001aYE\u0001\u0004TiJLgn\u001a\u0006\u0004\u0007\u000b\"\u0007bBB(?\u0001\u00071QH\u0001\bG>lW.\u00198e)\u0011\u0019Yda\u0015\t\u000f\r=\u0003\u00051\u0001\u0004VA1\u0011qHA$\u0007{!baa\u000f\u0004Z\rm\u0003bBB(C\u0001\u00071Q\u000b\u0005\b\u0007;\n\u0003\u0019AB0\u0003\r)gN\u001e\t\t\u0003\u007f\u0019\tg!\u0010\u0004>%!11MA!\u0005\ri\u0015\r\u001d\u000b\u000b\u0007w\u00199g!\u001b\u0004l\r=\u0004bBB(E\u0001\u00071Q\u000b\u0005\b\u0007;\u0012\u0003\u0019AB0\u0011\u001d\u0019iG\ta\u0001\u0003w\f!c]3qCJ\fG/Z,pe.Lgn\u001a#je\"91\u0011\u000f\u0012A\u0002\u0005]\u0013A\u00032vM\u001a,'oU5{KRa11HB;\u0007o\u001aIha\u001f\u0004~!91qJ\u0012A\u0002\rU\u0003bBB/G\u0001\u00071q\f\u0005\b\u0007[\u001a\u0003\u0019AA~\u0011\u001d\u0019\th\ta\u0001\u0003/Bqaa $\u0001\u0004\u0019i$\u0001\u0005f]\u000e|G-\u001b8h\u0003\rQ\u0018\u000e]\u000b\u0005\u0007\u000b\u001bY\t\u0006\u0003\u0004\b\u000e5\u0005\u0003CA\u0005\u0005s\tYa!#\u0011\u0007q\u001cY\tB\u0004\u0003^\u0011\u0012\r!!\u0005\t\u000f\r\rA\u00051\u0001\u0004\u0010B\"1\u0011SBK!\u001d\tI\u0001ABE\u0007'\u00032\u0001`BK\t1\u00199j!$\u0002\u0002\u0003\u0005)\u0011AA\t\u0005\ryFEM\u0001\u000eu&\u0004\b+\u0019:uSRLwN\\:\u0016\r\ru5qVBR)\u0019\u0019yja*\u00048B1\u0011\u0011BA^\u0007C\u00032\u0001`BR\t\u001d\u0019)+\nb\u0001\u0003#\u0011\u0011A\u0016\u0005\b\u0007\u0007)\u0003\u0019ABUa\u0011\u0019Yka-\u0011\u000f\u0005%\u0001a!,\u00042B\u0019Apa,\u0005\u000f\tuSE1\u0001\u0002\u0012A\u0019Apa-\u0005\u0019\rU6qUA\u0001\u0002\u0003\u0015\t!!\u0005\u0003\u0007}#3\u0007C\u0004\u0002H\u0016\u0002\ra!/\u0011\u0015\u0005-71XAP\u0007\u007f\u001b\t+\u0003\u0003\u0004>\u00065'\u0001\u0005$mCRl\u0015\r\u001d$v]\u000e$\u0018n\u001c83!\u0019\ty$!)\u0004.\u0006y!0\u001b9XSRDWK\\5rk\u0016LE\r\u0006\u0002\u0004FBA\u0011\u0011\u0002B\u001d\u0003\u0017\u00199\r\u0005\u0003\u0002l\u000e%\u0017\u0002BBf\u0003[\u0014A\u0001T8oO\u0006a!0\u001b9XSRD\u0017J\u001c3fq\u00069am\u001c:fC\u000eDGc\u0001<\u0004T\"9\u0011q\u0019\u0015A\u0002\rU\u0007CBAf\u0005S\fY!A\u0004d_2dWm\u0019;\u0015\u0005\tM\u0018a\u0004;p\u0019>\u001c\u0017\r\\%uKJ\fGo\u001c:\u0015\u0005\u0005}\u0015!E2pY2,7\r\u001e)beRLG/[8ogR!11]Bu!\u0015\u00197Q\u001dBz\u0013\r\u00199\u000f\u001a\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0007W\\\u0003\u0019ABw\u00031\u0001\u0018M\u001d;ji&|g.\u00133t!\u0015\u00197Q]A,\u0003\u0019\u0011X\rZ;dKR!\u00111BBz\u0011\u001d\t9\r\fa\u0001\u0007k\u0004\"\"a3\u0002f\u0006-\u00111BA\u0006\u0003)!(/Z3SK\u0012,8-\u001a\u000b\u0007\u0003\u0017\u0019Yp!@\t\u000f\u0005\u001dW\u00061\u0001\u0004v\"91q`\u0017A\u0002\u0005]\u0013!\u00023faRDG\u0003BA\u0006\t\u0007Aq!a2/\u0001\u0004\u0019)0\u0001\u0003g_2$G\u0003\u0002C\u0005\t\u001b!B!a\u0003\u0005\f!9\u0011qY\u0018A\u0002\rU\bb\u0002C\b_\u0001\u0007\u00111B\u0001\nu\u0016\u0014xNV1mk\u0016\f\u0011\"Y4he\u0016<\u0017\r^3\u0016\t\u0011UA1\u0004\u000b\u0005\t/!I\u0003\u0006\u0004\u0005\u001a\u0011uA1\u0005\t\u0004y\u0012mAa\u0002B/a\t\u0007\u0011\u0011\u0003\u0005\b\t?\u0001\u0004\u0019\u0001C\u0011\u0003\u0015\u0019X-](q!)\tY-!:\u0005\u001a\u0005-A\u0011\u0004\u0005\b\tK\u0001\u0004\u0019\u0001C\u0014\u0003\u0019\u0019w.\u001c2PaBQ\u00111ZAs\t3!I\u0002\"\u0007\t\u000f\u0011=\u0001\u00071\u0001\u0005\u001a\u0005iAO]3f\u0003\u001e<'/Z4bi\u0016,B\u0001b\f\u00054QQA\u0011\u0007C\u001b\to!Y\u0004b\u0010\u0011\u0007q$\u0019\u0004B\u0004\u0003^E\u0012\r!!\u0005\t\u000f\u0011=\u0011\u00071\u0001\u00052!9AqD\u0019A\u0002\u0011e\u0002CCAf\u0003K$\t$a\u0003\u00052!9AQE\u0019A\u0002\u0011u\u0002CCAf\u0003K$\t\u0004\"\r\u00052!91q`\u0019A\u0002\u0005]S\u0003\u0002C\"\t\u000f\"\u0002\u0002\"\u0012\u0005J\u0011-Cq\n\t\u0004y\u0012\u001dCa\u0002B/e\t\u0007\u0011\u0011\u0003\u0005\b\t\u001f\u0011\u0004\u0019\u0001C#\u0011\u001d!yB\ra\u0001\t\u001b\u0002\"\"a3\u0002f\u0012\u0015\u00131\u0002C#\u0011\u001d!)C\ra\u0001\t#\u0002\"\"a3\u0002f\u0012\u0015CQ\tC#+\u0011!)\u0006\"\u0017\u0015\u0019\u0011]C1\fC/\tC\")\u0007b\u001a\u0011\u0007q$I\u0006B\u0004\u0003^M\u0012\r!!\u0005\t\u000f\u0011=1\u00071\u0001\u0005X!9AqD\u001aA\u0002\u0011}\u0003CCAf\u0003K$9&a\u0003\u0005X!9AQE\u001aA\u0002\u0011\r\u0004CCAf\u0003K$9\u0006b\u0016\u0005X!91q`\u001aA\u0002\u0005]\u0003b\u0002C5g\u0001\u0007\u00111`\u0001\u0019M&t\u0017\r\\!hOJ,w-\u0019;f\u001f:,\u00050Z2vi>\u0014\u0018!B2pk:$HC\u0001C8!\r\u0019G\u0011O\u0005\u0004\u0007\u0017$\u0017aC2pk:$\u0018\t\u001d9s_b$b\u0001b\u001e\u0005\n\u00125\u0005C\u0002C=\t\u007f\"\u0019)\u0004\u0002\u0005|)\u0019AQP-\u0002\u000fA\f'\u000f^5bY&!A\u0011\u0011C>\u00055\u0001\u0016M\u001d;jC2\u0014Vm];miB!A\u0011\u0010CC\u0013\u0011!9\tb\u001f\u0003\u001b\t{WO\u001c3fI\u0012{WO\u00197f\u0011\u001d!Y)\u000ea\u0001\t_\nq\u0001^5nK>,H\u000fC\u0004\u0005\u0010V\u0002\r\u0001\"%\u0002\u0015\r|gNZ5eK:\u001cW\rE\u0002d\t'K1\u0001\"&e\u0005\u0019!u.\u001e2mKR!Aq\u000fCM\u0011\u001d!YI\u000ea\u0001\t_\nAbY8v]R\u0014\u0015PV1mk\u0016$\"\u0001b(\u0011\u0011\u0005}2\u0011MA\u0006\u0007\u000f\f!cY8v]R\u0014\u0015PV1mk\u0016\f\u0005\u000f\u001d:pqR1AQ\u0015CU\tW\u0003b\u0001\"\u001f\u0005\u0000\u0011\u001d\u0006\u0003CA \u0007C\nY\u0001b!\t\u000f\u0011-\u0005\b1\u0001\u0005p!9Aq\u0012\u001dA\u0002\u0011EE\u0003\u0002CS\t_Cq\u0001b#:\u0001\u0004!y'\u0001\u0003uC.,G\u0003\u0002Bz\tkCq\u0001b.;\u0001\u0004\t9&A\u0002ok6\f!\u0002^1lKN\u000bW\u000e\u001d7f)\u0019\u0011\u0019\u0010\"0\u0005B\"9AqX\u001eA\u0002\u0005m\u0018aD<ji\"\u0014V\r\u001d7bG\u0016lWM\u001c;\t\u000f\u0011]6\b1\u0001\u0002XQA!1\u001fCc\t\u000f$I\rC\u0004\u0005@r\u0002\r!a?\t\u000f\u0011]F\b1\u0001\u0002X!9A1\u001a\u001fA\u0002\u0011=\u0014\u0001B:fK\u0012\fQAZ5sgR$\"!a\u0003\u0002\u000f%\u001cX)\u001c9usR\u0011\u00111`\u0001\u000fg\u00064X-Q:UKb$h)\u001b7f)\r1H\u0011\u001c\u0005\b\t7|\u0004\u0019AB\u001f\u0003\u0011\u0001\u0018\r\u001e5\u0015\u000bY$y\u000e\"9\t\u000f\u0011m\u0007\t1\u0001\u0004>!9A1\u001d!A\u0002\u0011\u0015\u0018!B2pI\u0016\u001c\u0007\u0007\u0002Ct\t_\u0004baa\u0010\u0005j\u00125\u0018\u0002\u0002Cv\u0007\u0017\u0012Qa\u00117bgN\u00042\u0001 Cx\t1!\t\u0010\"9\u0002\u0002\u0003\u0005)\u0011\u0001Cz\u0005\ryF\u0005N\t\u0005\u0003\u0003!)\u0010\u0005\u0003\u0005x\u0016\u0015QB\u0001C}\u0015\u0011!Y\u0010\"@\u0002\u0011\r|W\u000e\u001d:fgNTA\u0001b@\u0006\u0002\u0005\u0011\u0011n\u001c\u0006\u0004\u000b\u0007Y\u0016A\u00025bI>|\u0007/\u0003\u0003\u0006\b\u0011e(\u0001E\"p[B\u0014Xm]:j_:\u001cu\u000eZ3d\u0003A\u0019\u0018M^3Bg>\u0013'.Z2u\r&dW\rF\u0002w\u000b\u001bAq\u0001b7B\u0001\u0004\u0019i$A\u0003lKf\u0014\u00150\u0006\u0003\u0006\u0014\u0015eA\u0003BC\u000b\u000b7\u0001\u0002\"!\u0003\u0003:\u0015]\u00111\u0002\t\u0004y\u0016eAa\u0002B/\u0005\n\u0007\u0011\u0011\u0003\u0005\b\u0003\u000f\u0014\u0005\u0019AC\u000f!!\tY-!5\u0002\f\u0015]\u0011AC2iK\u000e\\\u0007o\\5oi\u0006q\u0011n]\"iK\u000e\\\u0007o\\5oi\u0016$WCAA~\u0003E9W\r^\"iK\u000e\\\u0007o\\5oi\u001aKG.\u001a\u000b\u0003\u000bS\u0001b!!\u0003\u0002v\ru\u0012!\u0004;p\t\u0016\u0014WoZ*ue&tw\r\u0006\u0002\u0004>\u0005\u0019Ao\u001c9\u0015\r\tMX1GC\u001b\u0011\u001d!9l\u0012a\u0001\u0003/Bq!b\u000eH\u0001\u0004)I$\u0001\u0003d_6\u0004\bCBA \u000bw\tY!\u0003\u0003\u0006>\u0005\u0005#AC\"p[B\f'/\u0019;peR!!1_C!\u0011\u001d!9\f\u0013a\u0001\u0003/\n1\u0002^1lK>\u0013H-\u001a:fIR1!1_C$\u000b\u0013Bq\u0001b.J\u0001\u0004\t9\u0006C\u0004\u00068%\u0003\r!\"\u000f\u0002\u00075\f\u0007\u0010\u0006\u0003\u0002\f\u0015=\u0003bBC\u001c\u0015\u0002\u0007Q\u0011H\u0001\u0004[&tG\u0003BA\u0006\u000b+Bq!b\u000eL\u0001\u0004)I\u0004\u0006\u0003\u0003t\u0016e\u0003b\u0002C\\\u0019\u0002\u0007\u0011qK\u0001\u0014G>,h\u000e^!qaJ|\u0007\u0010R5ti&t7\r\u001e\u000b\u0005\t_*y\u0006C\u0004\u0006b5\u0003\r\u0001\"%\u0002\u0015I,G.\u0019;jm\u0016\u001cF)\u0001\u0003oC6,\u0017AC2pk:$\u0018i]=oGR\u0011Q\u0011\u000e\t\u0007\u0003\u0013)Yga2\n\u0007\u00155TK\u0001\tKCZ\fg)\u001e;ve\u0016\f5\r^5p]\u0006a1m\u001c7mK\u000e$\u0018i]=oGR\u0011Q1\u000f\t\u0007\u0003\u0013)YGa=\u0002\u0013Q\f7.Z!ts:\u001cG\u0003BC:\u000bsBq\u0001b.R\u0001\u0004\t9&\u0001\u0007g_J,\u0017m\u00195Bgft7\r\u0006\u0003\u0006\u0000\u0015\u001d\u0005CBA\u0005\u000bW*\t\t\u0005\u0003\u0002l\u0016\r\u0015\u0002BCC\u0003[\u0014AAV8jI\"9\u0011q\u0019*A\u0002\rU\u0017!\u00064pe\u0016\f7\r\u001b)beRLG/[8o\u0003NLhn\u0019\u000b\u0005\u000b\u007f*i\tC\u0004\u0002HN\u0003\rAa:"
)
public interface JavaRDDLike extends Serializable {
   JavaRDDLike wrapRDD(final RDD rdd);

   ClassTag classTag();

   RDD rdd();

   // $FF: synthetic method
   static List partitions$(final JavaRDDLike $this) {
      return $this.partitions();
   }

   default List partitions() {
      return .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.rdd().partitions()).toImmutableArraySeq()).asJava();
   }

   // $FF: synthetic method
   static int getNumPartitions$(final JavaRDDLike $this) {
      return $this.getNumPartitions();
   }

   default int getNumPartitions() {
      return this.rdd().getNumPartitions();
   }

   // $FF: synthetic method
   static Optional partitioner$(final JavaRDDLike $this) {
      return $this.partitioner();
   }

   default Optional partitioner() {
      return JavaUtils$.MODULE$.optionToOptional(this.rdd().partitioner());
   }

   // $FF: synthetic method
   static SparkContext context$(final JavaRDDLike $this) {
      return $this.context();
   }

   default SparkContext context() {
      return this.rdd().context();
   }

   // $FF: synthetic method
   static int id$(final JavaRDDLike $this) {
      return $this.id();
   }

   default int id() {
      return this.rdd().id();
   }

   // $FF: synthetic method
   static StorageLevel getStorageLevel$(final JavaRDDLike $this) {
      return $this.getStorageLevel();
   }

   default StorageLevel getStorageLevel() {
      return this.rdd().getStorageLevel();
   }

   // $FF: synthetic method
   static Iterator iterator$(final JavaRDDLike $this, final Partition split, final TaskContext taskContext) {
      return $this.iterator(split, taskContext);
   }

   default Iterator iterator(final Partition split, final TaskContext taskContext) {
      return .MODULE$.IteratorHasAsJava(this.rdd().iterator(split, taskContext)).asJava();
   }

   // $FF: synthetic method
   static JavaRDD map$(final JavaRDDLike $this, final Function f) {
      return $this.map(f);
   }

   default JavaRDD map(final Function f) {
      return new JavaRDD(this.rdd().map(JavaPairRDD$.MODULE$.toScalaFunction(f), JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaRDD mapPartitionsWithIndex$(final JavaRDDLike $this, final Function2 f, final boolean preservesPartitioning) {
      return $this.mapPartitionsWithIndex(f, preservesPartitioning);
   }

   default JavaRDD mapPartitionsWithIndex(final Function2 f, final boolean preservesPartitioning) {
      return new JavaRDD(this.rdd().mapPartitionsWithIndex((a, b) -> $anonfun$mapPartitionsWithIndex$1(f, BoxesRunTime.unboxToInt(a), b), preservesPartitioning, JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaDoubleRDD mapToDouble$(final JavaRDDLike $this, final DoubleFunction f) {
      return $this.mapToDouble(f);
   }

   default JavaDoubleRDD mapToDouble(final DoubleFunction f) {
      return new JavaDoubleRDD(this.rdd().map((x$1) -> BoxesRunTime.boxToDouble($anonfun$mapToDouble$1(f, x$1)), scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   static JavaPairRDD mapToPair$(final JavaRDDLike $this, final PairFunction f) {
      return $this.mapToPair(f);
   }

   default JavaPairRDD mapToPair(final PairFunction f) {
      return new JavaPairRDD(this.rdd().map(JavaPairRDD$.MODULE$.pairFunToScalaFun(f), cm$1()), JavaSparkContext$.MODULE$.fakeClassTag(), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaRDD flatMap$(final JavaRDDLike $this, final FlatMapFunction f) {
      return $this.flatMap(f);
   }

   default JavaRDD flatMap(final FlatMapFunction f) {
      return JavaRDD$.MODULE$.fromRDD(this.rdd().flatMap(fn$1(f), JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaDoubleRDD flatMapToDouble$(final JavaRDDLike $this, final DoubleFlatMapFunction f) {
      return $this.flatMapToDouble(f);
   }

   default JavaDoubleRDD flatMapToDouble(final DoubleFlatMapFunction f) {
      return new JavaDoubleRDD(this.rdd().flatMap(fn$2(f), scala.reflect.ClassTag..MODULE$.apply(Double.class)).map((x$2) -> BoxesRunTime.boxToDouble($anonfun$flatMapToDouble$2(x$2)), scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   static JavaPairRDD flatMapToPair$(final JavaRDDLike $this, final PairFlatMapFunction f) {
      return $this.flatMapToPair(f);
   }

   default JavaPairRDD flatMapToPair(final PairFlatMapFunction f) {
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().flatMap(fn$3(f), cm$2()), JavaSparkContext$.MODULE$.fakeClassTag(), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaRDD mapPartitions$(final JavaRDDLike $this, final FlatMapFunction f) {
      return $this.mapPartitions(f);
   }

   default JavaRDD mapPartitions(final FlatMapFunction f) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      RDD qual$1 = this.rdd();
      Function1 x$1 = fn$4(f);
      boolean x$2 = qual$1.mapPartitions$default$2();
      ClassTag x$3 = JavaSparkContext$.MODULE$.fakeClassTag();
      return var10000.fromRDD(qual$1.mapPartitions(x$1, x$2, x$3), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaRDD mapPartitions$(final JavaRDDLike $this, final FlatMapFunction f, final boolean preservesPartitioning) {
      return $this.mapPartitions(f, preservesPartitioning);
   }

   default JavaRDD mapPartitions(final FlatMapFunction f, final boolean preservesPartitioning) {
      return JavaRDD$.MODULE$.fromRDD(this.rdd().mapPartitions(fn$5(f), preservesPartitioning, JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static boolean mapPartitionsWithIndex$default$2$(final JavaRDDLike $this) {
      return $this.mapPartitionsWithIndex$default$2();
   }

   default boolean mapPartitionsWithIndex$default$2() {
      return false;
   }

   // $FF: synthetic method
   static JavaDoubleRDD mapPartitionsToDouble$(final JavaRDDLike $this, final DoubleFlatMapFunction f) {
      return $this.mapPartitionsToDouble(f);
   }

   default JavaDoubleRDD mapPartitionsToDouble(final DoubleFlatMapFunction f) {
      RDD qual$1 = this.rdd();
      Function1 x$1 = fn$6(f);
      boolean x$2 = qual$1.mapPartitions$default$2();
      return new JavaDoubleRDD(qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Double.class)).map((x$3) -> BoxesRunTime.boxToDouble($anonfun$mapPartitionsToDouble$2(x$3)), scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   static JavaPairRDD mapPartitionsToPair$(final JavaRDDLike $this, final PairFlatMapFunction f) {
      return $this.mapPartitionsToPair(f);
   }

   default JavaPairRDD mapPartitionsToPair(final PairFlatMapFunction f) {
      JavaPairRDD$ var10000 = JavaPairRDD$.MODULE$;
      RDD qual$1 = this.rdd();
      Function1 x$1 = fn$7(f);
      boolean x$2 = qual$1.mapPartitions$default$2();
      return var10000.fromRDD(qual$1.mapPartitions(x$1, x$2, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), JavaSparkContext$.MODULE$.fakeClassTag(), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaDoubleRDD mapPartitionsToDouble$(final JavaRDDLike $this, final DoubleFlatMapFunction f, final boolean preservesPartitioning) {
      return $this.mapPartitionsToDouble(f, preservesPartitioning);
   }

   default JavaDoubleRDD mapPartitionsToDouble(final DoubleFlatMapFunction f, final boolean preservesPartitioning) {
      return new JavaDoubleRDD(this.rdd().mapPartitions(fn$8(f), preservesPartitioning, scala.reflect.ClassTag..MODULE$.apply(Double.class)).map((x$4) -> BoxesRunTime.boxToDouble($anonfun$mapPartitionsToDouble$4(x$4)), scala.reflect.ClassTag..MODULE$.Double()));
   }

   // $FF: synthetic method
   static JavaPairRDD mapPartitionsToPair$(final JavaRDDLike $this, final PairFlatMapFunction f, final boolean preservesPartitioning) {
      return $this.mapPartitionsToPair(f, preservesPartitioning);
   }

   default JavaPairRDD mapPartitionsToPair(final PairFlatMapFunction f, final boolean preservesPartitioning) {
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().mapPartitions(fn$9(f), preservesPartitioning, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), JavaSparkContext$.MODULE$.fakeClassTag(), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static void foreachPartition$(final JavaRDDLike $this, final VoidFunction f) {
      $this.foreachPartition(f);
   }

   default void foreachPartition(final VoidFunction f) {
      this.rdd().foreachPartition((x) -> {
         $anonfun$foreachPartition$1(f, x);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static JavaRDD glom$(final JavaRDDLike $this) {
      return $this.glom();
   }

   default JavaRDD glom() {
      return new JavaRDD(this.rdd().glom().map((x$5) -> .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$5).toImmutableArraySeq()).asJava(), scala.reflect.ClassTag..MODULE$.apply(List.class)), scala.reflect.ClassTag..MODULE$.apply(List.class));
   }

   // $FF: synthetic method
   static JavaPairRDD cartesian$(final JavaRDDLike $this, final JavaRDDLike other) {
      return $this.cartesian(other);
   }

   default JavaPairRDD cartesian(final JavaRDDLike other) {
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().cartesian(other.rdd(), other.classTag()), this.classTag(), other.classTag());
   }

   // $FF: synthetic method
   static JavaPairRDD groupBy$(final JavaRDDLike $this, final Function f) {
      return $this.groupBy(f);
   }

   default JavaPairRDD groupBy(final Function f) {
      ClassTag ctagK = JavaSparkContext$.MODULE$.fakeClassTag();
      ClassTag ctagV = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaPairRDD$.MODULE$.fromRDD(JavaPairRDD$.MODULE$.groupByResultToJava(this.rdd().groupBy(JavaPairRDD$.MODULE$.toScalaFunction(f), JavaSparkContext$.MODULE$.fakeClassTag()), ctagK), ctagK, scala.reflect.ClassTag..MODULE$.apply(Iterable.class));
   }

   // $FF: synthetic method
   static JavaPairRDD groupBy$(final JavaRDDLike $this, final Function f, final int numPartitions) {
      return $this.groupBy(f, numPartitions);
   }

   default JavaPairRDD groupBy(final Function f, final int numPartitions) {
      ClassTag ctagK = JavaSparkContext$.MODULE$.fakeClassTag();
      ClassTag ctagV = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaPairRDD$.MODULE$.fromRDD(JavaPairRDD$.MODULE$.groupByResultToJava(this.rdd().groupBy(JavaPairRDD$.MODULE$.toScalaFunction(f), numPartitions, JavaSparkContext$.MODULE$.fakeClassTag()), ctagK), ctagK, scala.reflect.ClassTag..MODULE$.apply(Iterable.class));
   }

   // $FF: synthetic method
   static JavaRDD pipe$(final JavaRDDLike $this, final String command) {
      return $this.pipe(command);
   }

   default JavaRDD pipe(final String command) {
      return JavaRDD$.MODULE$.fromRDD(this.rdd().pipe(command), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   static JavaRDD pipe$(final JavaRDDLike $this, final List command) {
      return $this.pipe(command);
   }

   default JavaRDD pipe(final List command) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      RDD qual$1 = this.rdd();
      Seq x$1 = .MODULE$.ListHasAsScala(command).asScala().toSeq();
      Map x$2 = qual$1.pipe$default$2();
      Function1 x$3 = qual$1.pipe$default$3();
      scala.Function2 x$4 = qual$1.pipe$default$4();
      boolean x$5 = qual$1.pipe$default$5();
      int x$6 = qual$1.pipe$default$6();
      String x$7 = qual$1.pipe$default$7();
      return var10000.fromRDD(qual$1.pipe(x$1, x$2, x$3, x$4, x$5, x$6, x$7), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   static JavaRDD pipe$(final JavaRDDLike $this, final List command, final java.util.Map env) {
      return $this.pipe(command, env);
   }

   default JavaRDD pipe(final List command, final java.util.Map env) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      RDD qual$1 = this.rdd();
      Seq x$1 = .MODULE$.ListHasAsScala(command).asScala().toSeq();
      scala.collection.mutable.Map x$2 = .MODULE$.MapHasAsScala(env).asScala();
      Function1 x$3 = qual$1.pipe$default$3();
      scala.Function2 x$4 = qual$1.pipe$default$4();
      boolean x$5 = qual$1.pipe$default$5();
      int x$6 = qual$1.pipe$default$6();
      String x$7 = qual$1.pipe$default$7();
      return var10000.fromRDD(qual$1.pipe(x$1, x$2, x$3, x$4, x$5, x$6, x$7), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   static JavaRDD pipe$(final JavaRDDLike $this, final List command, final java.util.Map env, final boolean separateWorkingDir, final int bufferSize) {
      return $this.pipe(command, env, separateWorkingDir, bufferSize);
   }

   default JavaRDD pipe(final List command, final java.util.Map env, final boolean separateWorkingDir, final int bufferSize) {
      JavaRDD$ var10000 = JavaRDD$.MODULE$;
      RDD qual$1 = this.rdd();
      Seq x$1 = .MODULE$.ListHasAsScala(command).asScala().toSeq();
      scala.collection.mutable.Map x$2 = .MODULE$.MapHasAsScala(env).asScala();
      Null x$3 = null;
      Null x$4 = null;
      String x$7 = qual$1.pipe$default$7();
      return var10000.fromRDD(qual$1.pipe(x$1, x$2, (Function1)null, (scala.Function2)null, separateWorkingDir, bufferSize, x$7), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   static JavaRDD pipe$(final JavaRDDLike $this, final List command, final java.util.Map env, final boolean separateWorkingDir, final int bufferSize, final String encoding) {
      return $this.pipe(command, env, separateWorkingDir, bufferSize, encoding);
   }

   default JavaRDD pipe(final List command, final java.util.Map env, final boolean separateWorkingDir, final int bufferSize, final String encoding) {
      return JavaRDD$.MODULE$.fromRDD(this.rdd().pipe(.MODULE$.ListHasAsScala(command).asScala().toSeq(), .MODULE$.MapHasAsScala(env).asScala(), (Function1)null, (scala.Function2)null, separateWorkingDir, bufferSize, encoding), scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   static JavaPairRDD zip$(final JavaRDDLike $this, final JavaRDDLike other) {
      return $this.zip(other);
   }

   default JavaPairRDD zip(final JavaRDDLike other) {
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().zip(other.rdd(), other.classTag()), this.classTag(), other.classTag());
   }

   // $FF: synthetic method
   static JavaRDD zipPartitions$(final JavaRDDLike $this, final JavaRDDLike other, final FlatMapFunction2 f) {
      return $this.zipPartitions(other, f);
   }

   default JavaRDD zipPartitions(final JavaRDDLike other, final FlatMapFunction2 f) {
      return JavaRDD$.MODULE$.fromRDD(this.rdd().zipPartitions(other.rdd(), fn$10(f), other.classTag(), JavaSparkContext$.MODULE$.fakeClassTag()), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static JavaPairRDD zipWithUniqueId$(final JavaRDDLike $this) {
      return $this.zipWithUniqueId();
   }

   default JavaPairRDD zipWithUniqueId() {
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().zipWithUniqueId(), this.classTag(), scala.reflect.ClassTag..MODULE$.Long());
   }

   // $FF: synthetic method
   static JavaPairRDD zipWithIndex$(final JavaRDDLike $this) {
      return $this.zipWithIndex();
   }

   default JavaPairRDD zipWithIndex() {
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().zipWithIndex(), this.classTag(), scala.reflect.ClassTag..MODULE$.Long());
   }

   // $FF: synthetic method
   static void foreach$(final JavaRDDLike $this, final VoidFunction f) {
      $this.foreach(f);
   }

   default void foreach(final VoidFunction f) {
      this.rdd().foreach((x) -> {
         $anonfun$foreach$1(f, x);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   static List collect$(final JavaRDDLike $this) {
      return $this.collect();
   }

   default List collect() {
      return .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.rdd().collect()).toImmutableArraySeq()).asJava();
   }

   // $FF: synthetic method
   static Iterator toLocalIterator$(final JavaRDDLike $this) {
      return $this.toLocalIterator();
   }

   default Iterator toLocalIterator() {
      return .MODULE$.IteratorHasAsJava(this.rdd().toLocalIterator()).asJava();
   }

   // $FF: synthetic method
   static List[] collectPartitions$(final JavaRDDLike $this, final int[] partitionIds) {
      return $this.collectPartitions(partitionIds);
   }

   default List[] collectPartitions(final int[] partitionIds) {
      Object[] res = this.context().runJob(this.rdd(), (Function1)((it) -> it.toArray(this.classTag())), (Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(partitionIds).toImmutableArraySeq(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(this.classTag().runtimeClass())));
      return (List[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(res), (x$6) -> .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(x$6).toImmutableArraySeq()).asJava(), scala.reflect.ClassTag..MODULE$.apply(List.class));
   }

   // $FF: synthetic method
   static Object reduce$(final JavaRDDLike $this, final Function2 f) {
      return $this.reduce(f);
   }

   default Object reduce(final Function2 f) {
      return this.rdd().reduce(JavaPairRDD$.MODULE$.toScalaFunction2(f));
   }

   // $FF: synthetic method
   static Object treeReduce$(final JavaRDDLike $this, final Function2 f, final int depth) {
      return $this.treeReduce(f, depth);
   }

   default Object treeReduce(final Function2 f, final int depth) {
      return this.rdd().treeReduce(JavaPairRDD$.MODULE$.toScalaFunction2(f), depth);
   }

   // $FF: synthetic method
   static Object treeReduce$(final JavaRDDLike $this, final Function2 f) {
      return $this.treeReduce(f);
   }

   default Object treeReduce(final Function2 f) {
      return this.treeReduce(f, 2);
   }

   // $FF: synthetic method
   static Object fold$(final JavaRDDLike $this, final Object zeroValue, final Function2 f) {
      return $this.fold(zeroValue, f);
   }

   default Object fold(final Object zeroValue, final Function2 f) {
      return this.rdd().fold(zeroValue, JavaPairRDD$.MODULE$.toScalaFunction2(f));
   }

   // $FF: synthetic method
   static Object aggregate$(final JavaRDDLike $this, final Object zeroValue, final Function2 seqOp, final Function2 combOp) {
      return $this.aggregate(zeroValue, seqOp, combOp);
   }

   default Object aggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp) {
      return this.rdd().aggregate(zeroValue, JavaPairRDD$.MODULE$.toScalaFunction2(seqOp), JavaPairRDD$.MODULE$.toScalaFunction2(combOp), JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static Object treeAggregate$(final JavaRDDLike $this, final Object zeroValue, final Function2 seqOp, final Function2 combOp, final int depth) {
      return $this.treeAggregate(zeroValue, seqOp, combOp, depth);
   }

   default Object treeAggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp, final int depth) {
      return this.rdd().treeAggregate(zeroValue, JavaPairRDD$.MODULE$.toScalaFunction2(seqOp), JavaPairRDD$.MODULE$.toScalaFunction2(combOp), depth, JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static Object treeAggregate$(final JavaRDDLike $this, final Object zeroValue, final Function2 seqOp, final Function2 combOp) {
      return $this.treeAggregate(zeroValue, seqOp, combOp);
   }

   default Object treeAggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp) {
      return this.treeAggregate(zeroValue, seqOp, combOp, 2);
   }

   // $FF: synthetic method
   static Object treeAggregate$(final JavaRDDLike $this, final Object zeroValue, final Function2 seqOp, final Function2 combOp, final int depth, final boolean finalAggregateOnExecutor) {
      return $this.treeAggregate(zeroValue, seqOp, combOp, depth, finalAggregateOnExecutor);
   }

   default Object treeAggregate(final Object zeroValue, final Function2 seqOp, final Function2 combOp, final int depth, final boolean finalAggregateOnExecutor) {
      return this.rdd().treeAggregate(zeroValue, JavaPairRDD$.MODULE$.toScalaFunction2(seqOp), JavaPairRDD$.MODULE$.toScalaFunction2(combOp), depth, finalAggregateOnExecutor, JavaSparkContext$.MODULE$.fakeClassTag());
   }

   // $FF: synthetic method
   static long count$(final JavaRDDLike $this) {
      return $this.count();
   }

   default long count() {
      return this.rdd().count();
   }

   // $FF: synthetic method
   static PartialResult countApprox$(final JavaRDDLike $this, final long timeout, final double confidence) {
      return $this.countApprox(timeout, confidence);
   }

   default PartialResult countApprox(final long timeout, final double confidence) {
      return this.rdd().countApprox(timeout, confidence);
   }

   // $FF: synthetic method
   static PartialResult countApprox$(final JavaRDDLike $this, final long timeout) {
      return $this.countApprox(timeout);
   }

   default PartialResult countApprox(final long timeout) {
      RDD qual$1 = this.rdd();
      double x$2 = qual$1.countApprox$default$2();
      return qual$1.countApprox(timeout, x$2);
   }

   // $FF: synthetic method
   static java.util.Map countByValue$(final JavaRDDLike $this) {
      return $this.countByValue();
   }

   default java.util.Map countByValue() {
      JavaUtils$ var10000 = JavaUtils$.MODULE$;
      RDD qual$1 = this.rdd();
      Ordering x$1 = qual$1.countByValue$default$1();
      return var10000.mapAsSerializableJavaMap(qual$1.countByValue(x$1));
   }

   // $FF: synthetic method
   static PartialResult countByValueApprox$(final JavaRDDLike $this, final long timeout, final double confidence) {
      return $this.countByValueApprox(timeout, confidence);
   }

   default PartialResult countByValueApprox(final long timeout, final double confidence) {
      RDD qual$1 = this.rdd();
      Ordering x$3 = qual$1.countByValueApprox$default$3(timeout, confidence);
      return qual$1.countByValueApprox(timeout, confidence, x$3).map((underlying) -> JavaUtils$.MODULE$.mapAsSerializableJavaMap(underlying));
   }

   // $FF: synthetic method
   static PartialResult countByValueApprox$(final JavaRDDLike $this, final long timeout) {
      return $this.countByValueApprox(timeout);
   }

   default PartialResult countByValueApprox(final long timeout) {
      RDD qual$1 = this.rdd();
      double x$2 = qual$1.countByValueApprox$default$2();
      Ordering x$5 = qual$1.countByValueApprox$default$3(timeout, x$2);
      return qual$1.countByValueApprox(timeout, x$2, x$5).map((underlying) -> JavaUtils$.MODULE$.mapAsSerializableJavaMap(underlying));
   }

   // $FF: synthetic method
   static List take$(final JavaRDDLike $this, final int num) {
      return $this.take(num);
   }

   default List take(final int num) {
      return .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.rdd().take(num)).toImmutableArraySeq()).asJava();
   }

   // $FF: synthetic method
   static List takeSample$(final JavaRDDLike $this, final boolean withReplacement, final int num) {
      return $this.takeSample(withReplacement, num);
   }

   default List takeSample(final boolean withReplacement, final int num) {
      return this.takeSample(withReplacement, num, Utils$.MODULE$.random().nextLong());
   }

   // $FF: synthetic method
   static List takeSample$(final JavaRDDLike $this, final boolean withReplacement, final int num, final long seed) {
      return $this.takeSample(withReplacement, num, seed);
   }

   default List takeSample(final boolean withReplacement, final int num, final long seed) {
      return .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.rdd().takeSample(withReplacement, num, seed)).toImmutableArraySeq()).asJava();
   }

   // $FF: synthetic method
   static Object first$(final JavaRDDLike $this) {
      return $this.first();
   }

   default Object first() {
      return this.rdd().first();
   }

   // $FF: synthetic method
   static boolean isEmpty$(final JavaRDDLike $this) {
      return $this.isEmpty();
   }

   default boolean isEmpty() {
      return this.rdd().isEmpty();
   }

   // $FF: synthetic method
   static void saveAsTextFile$(final JavaRDDLike $this, final String path) {
      $this.saveAsTextFile(path);
   }

   default void saveAsTextFile(final String path) {
      this.rdd().saveAsTextFile(path);
   }

   // $FF: synthetic method
   static void saveAsTextFile$(final JavaRDDLike $this, final String path, final Class codec) {
      $this.saveAsTextFile(path, codec);
   }

   default void saveAsTextFile(final String path, final Class codec) {
      this.rdd().saveAsTextFile(path, codec);
   }

   // $FF: synthetic method
   static void saveAsObjectFile$(final JavaRDDLike $this, final String path) {
      $this.saveAsObjectFile(path);
   }

   default void saveAsObjectFile(final String path) {
      this.rdd().saveAsObjectFile(path);
   }

   // $FF: synthetic method
   static JavaPairRDD keyBy$(final JavaRDDLike $this, final Function f) {
      return $this.keyBy(f);
   }

   default JavaPairRDD keyBy(final Function f) {
      ClassTag ctag = JavaSparkContext$.MODULE$.fakeClassTag();
      return JavaPairRDD$.MODULE$.fromRDD(this.rdd().keyBy(JavaPairRDD$.MODULE$.toScalaFunction(f)), ctag, this.classTag());
   }

   // $FF: synthetic method
   static void checkpoint$(final JavaRDDLike $this) {
      $this.checkpoint();
   }

   default void checkpoint() {
      this.rdd().checkpoint();
   }

   // $FF: synthetic method
   static boolean isCheckpointed$(final JavaRDDLike $this) {
      return $this.isCheckpointed();
   }

   default boolean isCheckpointed() {
      return this.rdd().isCheckpointed();
   }

   // $FF: synthetic method
   static Optional getCheckpointFile$(final JavaRDDLike $this) {
      return $this.getCheckpointFile();
   }

   default Optional getCheckpointFile() {
      return JavaUtils$.MODULE$.optionToOptional(this.rdd().getCheckpointFile());
   }

   // $FF: synthetic method
   static String toDebugString$(final JavaRDDLike $this) {
      return $this.toDebugString();
   }

   default String toDebugString() {
      return this.rdd().toDebugString();
   }

   // $FF: synthetic method
   static List top$(final JavaRDDLike $this, final int num, final Comparator comp) {
      return $this.top(num, comp);
   }

   default List top(final int num, final Comparator comp) {
      return .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.rdd().top(num, scala.package..MODULE$.Ordering().comparatorToOrdering(comp))).toImmutableArraySeq()).asJava();
   }

   // $FF: synthetic method
   static List top$(final JavaRDDLike $this, final int num) {
      return $this.top(num);
   }

   default List top(final int num) {
      Comparator comp = org.sparkproject.guava.collect.Ordering.natural();
      return this.top(num, comp);
   }

   // $FF: synthetic method
   static List takeOrdered$(final JavaRDDLike $this, final int num, final Comparator comp) {
      return $this.takeOrdered(num, comp);
   }

   default List takeOrdered(final int num, final Comparator comp) {
      return .MODULE$.SeqHasAsJava(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.rdd().takeOrdered(num, scala.package..MODULE$.Ordering().comparatorToOrdering(comp))).toImmutableArraySeq()).asJava();
   }

   // $FF: synthetic method
   static Object max$(final JavaRDDLike $this, final Comparator comp) {
      return $this.max(comp);
   }

   default Object max(final Comparator comp) {
      return this.rdd().max(scala.package..MODULE$.Ordering().comparatorToOrdering(comp));
   }

   // $FF: synthetic method
   static Object min$(final JavaRDDLike $this, final Comparator comp) {
      return $this.min(comp);
   }

   default Object min(final Comparator comp) {
      return this.rdd().min(scala.package..MODULE$.Ordering().comparatorToOrdering(comp));
   }

   // $FF: synthetic method
   static List takeOrdered$(final JavaRDDLike $this, final int num) {
      return $this.takeOrdered(num);
   }

   default List takeOrdered(final int num) {
      Comparator comp = org.sparkproject.guava.collect.Ordering.natural();
      return this.takeOrdered(num, comp);
   }

   // $FF: synthetic method
   static long countApproxDistinct$(final JavaRDDLike $this, final double relativeSD) {
      return $this.countApproxDistinct(relativeSD);
   }

   default long countApproxDistinct(final double relativeSD) {
      return this.rdd().countApproxDistinct(relativeSD);
   }

   // $FF: synthetic method
   static String name$(final JavaRDDLike $this) {
      return $this.name();
   }

   default String name() {
      return this.rdd().name();
   }

   // $FF: synthetic method
   static JavaFutureAction countAsync$(final JavaRDDLike $this) {
      return $this.countAsync();
   }

   default JavaFutureAction countAsync() {
      return new JavaFutureActionWrapper(RDD$.MODULE$.rddToAsyncRDDActions(this.rdd(), this.classTag()).countAsync(), (x$1) -> $anonfun$countAsync$1(BoxesRunTime.unboxToLong(x$1)));
   }

   // $FF: synthetic method
   static JavaFutureAction collectAsync$(final JavaRDDLike $this) {
      return $this.collectAsync();
   }

   default JavaFutureAction collectAsync() {
      return new JavaFutureActionWrapper(RDD$.MODULE$.rddToAsyncRDDActions(this.rdd(), this.classTag()).collectAsync(), (x) -> .MODULE$.SeqHasAsJava(x).asJava());
   }

   // $FF: synthetic method
   static JavaFutureAction takeAsync$(final JavaRDDLike $this, final int num) {
      return $this.takeAsync(num);
   }

   default JavaFutureAction takeAsync(final int num) {
      return new JavaFutureActionWrapper(RDD$.MODULE$.rddToAsyncRDDActions(this.rdd(), this.classTag()).takeAsync(num), (x) -> .MODULE$.SeqHasAsJava(x).asJava());
   }

   // $FF: synthetic method
   static JavaFutureAction foreachAsync$(final JavaRDDLike $this, final VoidFunction f) {
      return $this.foreachAsync(f);
   }

   default JavaFutureAction foreachAsync(final VoidFunction f) {
      return new JavaFutureActionWrapper(RDD$.MODULE$.rddToAsyncRDDActions(this.rdd(), this.classTag()).foreachAsync((x) -> {
         $anonfun$foreachAsync$1(f, x);
         return BoxedUnit.UNIT;
      }), (x) -> null);
   }

   // $FF: synthetic method
   static JavaFutureAction foreachPartitionAsync$(final JavaRDDLike $this, final VoidFunction f) {
      return $this.foreachPartitionAsync(f);
   }

   default JavaFutureAction foreachPartitionAsync(final VoidFunction f) {
      return new JavaFutureActionWrapper(RDD$.MODULE$.rddToAsyncRDDActions(this.rdd(), this.classTag()).foreachPartitionAsync((x) -> {
         $anonfun$foreachPartitionAsync$1(f, x);
         return BoxedUnit.UNIT;
      }), (x) -> null);
   }

   // $FF: synthetic method
   static scala.collection.Iterator $anonfun$mapPartitionsWithIndex$1(final Function2 f$1, final int a, final scala.collection.Iterator b) {
      return .MODULE$.IteratorHasAsScala((Iterator)f$1.call(scala.Predef..MODULE$.int2Integer(a), .MODULE$.IteratorHasAsJava(b).asJava())).asScala();
   }

   // $FF: synthetic method
   static double $anonfun$mapToDouble$1(final DoubleFunction f$2, final Object x$1) {
      return f$2.call(x$1);
   }

   private static ClassTag cm$1() {
      return (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   private static Function1 fn$1(final FlatMapFunction f$3) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$3.call(x)).asScala();
   }

   private static Function1 fn$2(final DoubleFlatMapFunction f$4) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$4.call(x)).asScala();
   }

   // $FF: synthetic method
   static double $anonfun$flatMapToDouble$2(final Double x$2) {
      return x$2;
   }

   private static Function1 fn$3(final PairFlatMapFunction f$5) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$5.call(x)).asScala();
   }

   private static ClassTag cm$2() {
      return (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   private static Function1 fn$4(final FlatMapFunction f$6) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$6.call(.MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   private static Function1 fn$5(final FlatMapFunction f$7) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$7.call(.MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   private static Function1 fn$6(final DoubleFlatMapFunction f$8) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$8.call(.MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   // $FF: synthetic method
   static double $anonfun$mapPartitionsToDouble$2(final Double x$3) {
      return x$3;
   }

   private static Function1 fn$7(final PairFlatMapFunction f$9) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$9.call(.MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   private static Function1 fn$8(final DoubleFlatMapFunction f$10) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$10.call(.MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   // $FF: synthetic method
   static double $anonfun$mapPartitionsToDouble$4(final Double x$4) {
      return x$4;
   }

   private static Function1 fn$9(final PairFlatMapFunction f$11) {
      return (x) -> .MODULE$.IteratorHasAsScala(f$11.call(.MODULE$.IteratorHasAsJava(x).asJava())).asScala();
   }

   // $FF: synthetic method
   static void $anonfun$foreachPartition$1(final VoidFunction f$12, final scala.collection.Iterator x) {
      f$12.call(.MODULE$.IteratorHasAsJava(x).asJava());
   }

   private static scala.Function2 fn$10(final FlatMapFunction2 f$13) {
      return (x, y) -> .MODULE$.IteratorHasAsScala(f$13.call(.MODULE$.IteratorHasAsJava(x).asJava(), .MODULE$.IteratorHasAsJava(y).asJava())).asScala();
   }

   // $FF: synthetic method
   static void $anonfun$foreach$1(final VoidFunction f$14, final Object x) {
      f$14.call(x);
   }

   // $FF: synthetic method
   static Long $anonfun$countAsync$1(final long x$1) {
      return x$1;
   }

   // $FF: synthetic method
   static void $anonfun$foreachAsync$1(final VoidFunction f$15, final Object x) {
      f$15.call(x);
   }

   // $FF: synthetic method
   static void $anonfun$foreachPartitionAsync$1(final VoidFunction f$16, final scala.collection.Iterator x) {
      f$16.call(.MODULE$.IteratorHasAsJava(x).asJava());
   }

   static void $init$(final JavaRDDLike $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
