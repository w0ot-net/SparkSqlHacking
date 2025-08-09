package org.apache.spark.streaming.api.java;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.Optional;
import org.apache.spark.api.java.JavaPairRDD.;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StateSpec;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.dstream.DStream;
import org.apache.spark.streaming.dstream.DStream$;
import org.apache.spark.streaming.dstream.PairDStreamFunctions;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Null;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u001df\u0001B'O\u0001mC\u0001\u0002 \u0001\u0003\u0006\u0004%\t! \u0005\n\u0003\u000f\u0001!\u0011!Q\u0001\nyD!\"!\u0003\u0001\u0005\u000b\u0007I1AA\u0006\u0011)\tI\u0002\u0001B\u0001B\u0003%\u0011Q\u0002\u0005\u000b\u00037\u0001!Q1A\u0005\u0004\u0005u\u0001BCA\u0011\u0001\t\u0005\t\u0015!\u0003\u0002 !9\u00111\u0005\u0001\u0005\u0002\u0005\u0015\u0002bBA\u0018\u0001\u0011\u0005\u0013\u0011\u0007\u0005\b\u0003\u0003\u0002A\u0011AA\"\u0011\u001d\t\u0019\u0007\u0001C\u0001\u0003KBq!a\u001a\u0001\t\u0003\t)\u0007C\u0004\u0002h\u0001!\t!!\u001b\t\u000f\u0005m\u0004\u0001\"\u0001\u0002~!9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-\u0005bBAM\u0001\u0011\u0005\u00111\u0014\u0005\b\u00033\u0003A\u0011AAT\u0011\u001d\ty\u000b\u0001C\u0001\u0003cCq!a.\u0001\t\u0003\tI\fC\u0004\u00028\u0002!\t!a1\t\u000f\u0005]\u0006\u0001\"\u0001\u0002H\"9\u0011Q\u001b\u0001\u0005\u0002\u0005]\u0007bBAk\u0001\u0011\u0005\u00111\u001d\u0005\b\u0003+\u0004A\u0011AAu\u0011\u001d\ty\u000f\u0001C\u0001\u0003cDq!a<\u0001\t\u0003\u0011\t\u0002C\u0004\u00032\u0001!\tAa\r\t\u000f\tE\u0002\u0001\"\u0001\u00038!9!\u0011\u0007\u0001\u0005\u0002\tu\u0002b\u0002B\u0019\u0001\u0011\u0005!Q\t\u0005\b\u0005\u001b\u0002A\u0011\u0001B(\u0011\u001d\u0011i\u0005\u0001C\u0001\u0005/BqA!\u0014\u0001\t\u0003\u0011y\u0006C\u0004\u0003N\u0001!\tA!\u001b\t\u000f\t5\u0003\u0001\"\u0001\u0003t!9!Q\n\u0001\u0005\u0002\t}\u0004b\u0002B'\u0001\u0011\u0005!q\u0012\u0005\b\u0005;\u0003A\u0011\u0001BP\u0011\u001d\u0011y\f\u0001C\u0005\u0005\u0003Dqa!\u0002\u0001\t\u0003\u00199\u0001C\u0004\u0004\u0006\u0001!\ta!\u0007\t\u000f\r\u0015\u0001\u0001\"\u0001\u0004,!91Q\u0001\u0001\u0005\u0002\ru\u0002bBB+\u0001\u0011\u00051q\u000b\u0005\b\u0007O\u0002A\u0011AB5\u0011\u001d\u0019Y\b\u0001C\u0001\u0007{Bqaa\u001f\u0001\t\u0003\u0019\u0019\nC\u0004\u0004|\u0001!\taa*\t\u000f\rm\u0006\u0001\"\u0001\u0004>\"911\u0018\u0001\u0005\u0002\r5\u0007bBB^\u0001\u0011\u00051q\u001c\u0005\b\u0007c\u0004A\u0011ABz\u0011\u001d\u0019\t\u0010\u0001C\u0001\t\u000bAqa!=\u0001\t\u0003!I\u0002C\u0004\u0005.\u0001!\t\u0001b\f\t\u000f\u00115\u0002\u0001\"\u0001\u0005B!9AQ\u0006\u0001\u0005\u0002\u0011M\u0003b\u0002C3\u0001\u0011\u0005Aq\r\u0005\b\tK\u0002A\u0011\u0001C=\u0011\u001d!)\u0007\u0001C\u0001\t\u001bCq\u0001\")\u0001\t\u0003!\u0019\u000bC\u0004\u0005\"\u0002!\t\u0001b1\t\u000f\u0011\u0005\u0006\u0001\"\u0001\u0006\u0018!9Q1\f\u0001\u0005\u0002\u0015u\u0003bBC.\u0001\u0011\u0005Q1\r\u0005\b\u000b7\u0002A\u0011ACS\u0011%)Y\u000fAI\u0001\n\u0003)i\u000fC\u0004\u0007\u0016\u0001!\tAb\u0006\t\u0013\u0019}\u0001A1A\u0005B\u0019\u0005\u0002\u0002\u0003D\u0013\u0001\u0001\u0006IAb\t\b\u000f\u0019\u001db\n#\u0001\u0007*\u00191QJ\u0014E\u0001\rWAq!a\tH\t\u00031y\u0004C\u0004\u0007B\u001d#\u0019Ab\u0011\t\u000f\u0019\u0015t\t\"\u0001\u0007h!9a1P$\u0005\u0002\u0019u\u0004\"\u0003DO\u000f\u0006\u0005I\u0011\u0002DP\u0005=Q\u0015M^1QC&\u0014Hi\u0015;sK\u0006l'BA(Q\u0003\u0011Q\u0017M^1\u000b\u0005E\u0013\u0016aA1qS*\u00111\u000bV\u0001\ngR\u0014X-Y7j]\u001eT!!\u0016,\u0002\u000bM\u0004\u0018M]6\u000b\u0005]C\u0016AB1qC\u000eDWMC\u0001Z\u0003\ry'oZ\u0002\u0001+\ra\u0016n]\n\u0003\u0001u\u0003RAX0bkZl\u0011AT\u0005\u0003A:\u0013q#\u00112tiJ\f7\r\u001e&bm\u0006$5\u000b\u001e:fC6d\u0015n[3\u0011\t\t,wM]\u0007\u0002G*\tA-A\u0003tG\u0006d\u0017-\u0003\u0002gG\n1A+\u001e9mKJ\u0002\"\u0001[5\r\u0001\u0011)!\u000e\u0001b\u0001W\n\t1*\u0005\u0002m_B\u0011!-\\\u0005\u0003]\u000e\u0014qAT8uQ&tw\r\u0005\u0002ca&\u0011\u0011o\u0019\u0002\u0004\u0003:L\bC\u00015t\t\u0015!\bA1\u0001l\u0005\u00051\u0006\u0003\u00020\u0001OJ\u0004Ba\u001e>he6\t\u0001P\u0003\u0002Ps*\u0011\u0011\u000bV\u0005\u0003wb\u00141BS1wCB\u000b\u0017N\u001d*E\t\u00069Am\u001d;sK\u0006lW#\u0001@\u0011\t}\f\u0019!Y\u0007\u0003\u0003\u0003Q!\u0001 *\n\t\u0005\u0015\u0011\u0011\u0001\u0002\b\tN#(/Z1n\u0003!!7\u000f\u001e:fC6\u0004\u0013!C6NC:Lg-Z:u+\t\ti\u0001E\u0003\u0002\u0010\u0005Uq-\u0004\u0002\u0002\u0012)\u0019\u00111C2\u0002\u000fI,g\r\\3di&!\u0011qCA\t\u0005!\u0019E.Y:t)\u0006<\u0017AC6NC:Lg-Z:uA\u0005Ia/T1oS\u001a,7\u000f^\u000b\u0003\u0003?\u0001R!a\u0004\u0002\u0016I\f!B^'b]&4Wm\u001d;!\u0003\u0019a\u0014N\\5u}Q!\u0011qEA\u0017)\u0015)\u0018\u0011FA\u0016\u0011\u001d\tIa\u0002a\u0002\u0003\u001bAq!a\u0007\b\u0001\b\ty\u0002C\u0003}\u000f\u0001\u0007a0A\u0004xe\u0006\u0004(\u000b\u0012#\u0015\u0007Y\f\u0019\u0004C\u0004\u00026!\u0001\r!a\u000e\u0002\u0007I$G\rE\u0003\u0002:\u0005u\u0012-\u0004\u0002\u0002<)\u0019\u0011Q\u0007+\n\t\u0005}\u00121\b\u0002\u0004%\u0012#\u0015A\u00024jYR,'\u000fF\u0002v\u0003\u000bBq!a\u0012\n\u0001\u0004\tI%A\u0001g!\u001d\tY%!\u0015b\u0003+j!!!\u0014\u000b\u0007\u0005=\u00030\u0001\u0005gk:\u001cG/[8o\u0013\u0011\t\u0019&!\u0014\u0003\u0011\u0019+hn\u0019;j_:\u0004B!a\u0016\u0002`5\u0011\u0011\u0011\f\u0006\u0005\u00037\ni&\u0001\u0003mC:<'\"A(\n\t\u0005\u0005\u0014\u0011\f\u0002\b\u0005>|G.Z1o\u0003\u0015\u0019\u0017m\u00195f)\u0005)\u0018a\u00029feNL7\u000f\u001e\u000b\u0004k\u0006-\u0004bBA7\u0019\u0001\u0007\u0011qN\u0001\rgR|'/Y4f\u0019\u00164X\r\u001c\t\u0005\u0003c\n9(\u0004\u0002\u0002t)\u0019\u0011Q\u000f+\u0002\u000fM$xN]1hK&!\u0011\u0011PA:\u00051\u0019Fo\u001c:bO\u0016dUM^3m\u0003-\u0011X\r]1si&$\u0018n\u001c8\u0015\u0007U\fy\bC\u0004\u0002\u00026\u0001\r!a!\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t!\r\u0011\u0017QQ\u0005\u0004\u0003\u000f\u001b'aA%oi\u000691m\\7qkR,Gc\u0001<\u0002\u000e\"9\u0011q\u0012\bA\u0002\u0005E\u0015!\u0003<bY&$G+[7f!\u0011\t\u0019*!&\u000e\u0003IK1!a&S\u0005\u0011!\u0016.\\3\u0002\r]Lg\u000eZ8x)\r)\u0018Q\u0014\u0005\b\u0003?{\u0001\u0019AAQ\u000399\u0018N\u001c3po\u0012+(/\u0019;j_:\u0004B!a%\u0002$&\u0019\u0011Q\u0015*\u0003\u0011\u0011+(/\u0019;j_:$R!^AU\u0003WCq!a(\u0011\u0001\u0004\t\t\u000bC\u0004\u0002.B\u0001\r!!)\u0002\u001bMd\u0017\u000eZ3EkJ\fG/[8o\u0003\u0015)h.[8o)\r)\u00181\u0017\u0005\u0007\u0003k\u000b\u0002\u0019A;\u0002\tQD\u0017\r^\u0001\u000bOJ|W\u000f\u001d\"z\u0017\u0016LHCAA^!\u0015q\u0006aZA_!\u0015\t9&a0s\u0013\u0011\t\t-!\u0017\u0003\u0011%#XM]1cY\u0016$B!a/\u0002F\"9\u0011\u0011Q\nA\u0002\u0005\rE\u0003BA^\u0003\u0013Dq!a3\u0015\u0001\u0004\ti-A\u0006qCJ$\u0018\u000e^5p]\u0016\u0014\b\u0003BAh\u0003#l\u0011\u0001V\u0005\u0004\u0003'$&a\u0003)beRLG/[8oKJ\f1B]3ek\u000e,')_&fsR\u0019Q/!7\t\u000f\u0005mW\u00031\u0001\u0002^\u0006!a-\u001e8d!\u001d\tY%a8seJLA!!9\u0002N\tIa)\u001e8di&|gN\r\u000b\u0006k\u0006\u0015\u0018q\u001d\u0005\b\u000374\u0002\u0019AAo\u0011\u001d\t\tI\u0006a\u0001\u0003\u0007#R!^Av\u0003[Dq!a7\u0018\u0001\u0004\ti\u000eC\u0004\u0002L^\u0001\r!!4\u0002\u0019\r|WNY5oK\nK8*Z=\u0016\t\u0005M\u0018\u0011 \u000b\u000b\u0003k\fiPa\u0001\u0003\n\t=\u0001#\u00020\u0001O\u0006]\bc\u00015\u0002z\u00121\u00111 \rC\u0002-\u0014\u0011a\u0011\u0005\b\u0003\u007fD\u0002\u0019\u0001B\u0001\u00039\u0019'/Z1uK\u000e{WNY5oKJ\u0004r!a\u0013\u0002RI\f9\u0010C\u0004\u0003\u0006a\u0001\rAa\u0002\u0002\u00155,'oZ3WC2,X\rE\u0005\u0002L\u0005}\u0017q\u001f:\u0002x\"9!1\u0002\rA\u0002\t5\u0011AD7fe\u001e,7i\\7cS:,'o\u001d\t\u000b\u0003\u0017\ny.a>\u0002x\u0006]\bbBAf1\u0001\u0007\u0011QZ\u000b\u0005\u0005'\u0011I\u0002\u0006\u0007\u0003\u0016\tm!q\u0004B\u0012\u0005O\u0011I\u0003E\u0003_\u0001\u001d\u00149\u0002E\u0002i\u00053!a!a?\u001a\u0005\u0004Y\u0007bBA\u00003\u0001\u0007!Q\u0004\t\b\u0003\u0017\n\tF\u001dB\f\u0011\u001d\u0011)!\u0007a\u0001\u0005C\u0001\u0012\"a\u0013\u0002`\n]!Oa\u0006\t\u000f\t-\u0011\u00041\u0001\u0003&AQ\u00111JAp\u0005/\u00119Ba\u0006\t\u000f\u0005-\u0017\u00041\u0001\u0002N\"9!1F\rA\u0002\t5\u0012AD7baNKG-Z\"p[\nLg.\u001a\t\u0004E\n=\u0012bAA1G\u0006\u0019rM]8va\nK8*Z=B]\u0012<\u0016N\u001c3poR!\u00111\u0018B\u001b\u0011\u001d\tyJ\u0007a\u0001\u0003C#b!a/\u0003:\tm\u0002bBAP7\u0001\u0007\u0011\u0011\u0015\u0005\b\u0003[[\u0002\u0019AAQ)!\tYLa\u0010\u0003B\t\r\u0003bBAP9\u0001\u0007\u0011\u0011\u0015\u0005\b\u0003[c\u0002\u0019AAQ\u0011\u001d\t\t\t\ba\u0001\u0003\u0007#\u0002\"a/\u0003H\t%#1\n\u0005\b\u0003?k\u0002\u0019AAQ\u0011\u001d\ti+\ba\u0001\u0003CCq!a3\u001e\u0001\u0004\ti-\u0001\u000bsK\u0012,8-\u001a\"z\u0017\u0016L\u0018I\u001c3XS:$wn\u001e\u000b\u0006k\nE#Q\u000b\u0005\b\u0005'r\u0002\u0019AAo\u0003)\u0011X\rZ;dK\u001a+hn\u0019\u0005\b\u0003?s\u0002\u0019AAQ)\u001d)(\u0011\fB.\u0005;BqAa\u0015 \u0001\u0004\ti\u000eC\u0004\u0002 ~\u0001\r!!)\t\u000f\u00055v\u00041\u0001\u0002\"RIQO!\u0019\u0003d\t\u0015$q\r\u0005\b\u0005'\u0002\u0003\u0019AAo\u0011\u001d\ty\n\ta\u0001\u0003CCq!!,!\u0001\u0004\t\t\u000bC\u0004\u0002\u0002\u0002\u0002\r!a!\u0015\u0013U\u0014YG!\u001c\u0003p\tE\u0004b\u0002B*C\u0001\u0007\u0011Q\u001c\u0005\b\u0003?\u000b\u0003\u0019AAQ\u0011\u001d\ti+\ta\u0001\u0003CCq!a3\"\u0001\u0004\ti\rF\u0005v\u0005k\u00129Ha\u001f\u0003~!9!1\u000b\u0012A\u0002\u0005u\u0007b\u0002B=E\u0001\u0007\u0011Q\\\u0001\u000eS:4(+\u001a3vG\u00164UO\\2\t\u000f\u0005}%\u00051\u0001\u0002\"\"9\u0011Q\u0016\u0012A\u0002\u0005\u0005F#D;\u0003\u0002\n\r%Q\u0011BD\u0005\u0013\u0013Y\tC\u0004\u0003T\r\u0002\r!!8\t\u000f\te4\u00051\u0001\u0002^\"9\u0011qT\u0012A\u0002\u0005\u0005\u0006bBAWG\u0001\u0007\u0011\u0011\u0015\u0005\b\u0003\u0003\u001b\u0003\u0019AAB\u0011\u001d\u0011ii\ta\u0001\u0003\u0013\n!BZ5mi\u0016\u0014h)\u001e8d)5)(\u0011\u0013BJ\u0005+\u00139J!'\u0003\u001c\"9!1\u000b\u0013A\u0002\u0005u\u0007b\u0002B=I\u0001\u0007\u0011Q\u001c\u0005\b\u0003?#\u0003\u0019AAQ\u0011\u001d\ti\u000b\na\u0001\u0003CCq!a3%\u0001\u0004\ti\rC\u0004\u0003\u000e\u0012\u0002\r!!\u0013\u0002\u00195\f\u0007oV5uQN#\u0018\r^3\u0016\r\t\u0005&1\u0016BY)\u0011\u0011\u0019K!.\u0011\u0013y\u0013)k\u001a:\u0003*\n=\u0016b\u0001BT\u001d\n9\"*\u0019<b\u001b\u0006\u0004x+\u001b;i'R\fG/\u001a#TiJ,\u0017-\u001c\t\u0004Q\n-FA\u0002BWK\t\u00071NA\u0005Ti\u0006$X\rV=qKB\u0019\u0001N!-\u0005\r\tMVE1\u0001l\u0005)i\u0015\r\u001d9fIRK\b/\u001a\u0005\b\u0005o+\u0003\u0019\u0001B]\u0003\u0011\u0019\b/Z2\u0011\u0015\u0005M%1X4s\u0005S\u0013y+C\u0002\u0003>J\u0013\u0011b\u0015;bi\u0016\u001c\u0006/Z2\u00025\r|gN^3siV\u0003H-\u0019;f'R\fG/\u001a$v]\u000e$\u0018n\u001c8\u0016\t\t\r'\u0011\u001e\u000b\u0005\u0005\u000b\u0014i\u000fE\u0005c\u0005\u000f\u0014IM!9\u0003b&\u0019\u0011\u0011]2\u0011\u000b\t-'1\u001c:\u000f\t\t5'q\u001b\b\u0005\u0005\u001f\u0014).\u0004\u0002\u0003R*\u0019!1\u001b.\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0017b\u0001BmG\u00069\u0001/Y2lC\u001e,\u0017\u0002\u0002Bo\u0005?\u00141aU3r\u0015\r\u0011In\u0019\t\u0006E\n\r(q]\u0005\u0004\u0005K\u001c'AB(qi&|g\u000eE\u0002i\u0005S$aAa;'\u0005\u0004Y'!A*\t\u000f\t=h\u00051\u0001\u0003r\u0006\u0011\u0011N\u001c\t\u000b\u0003\u0017\nyNa=\u0003\u0000\n}\b#\u0002B{\u0005w\u0014XB\u0001B|\u0015\u0011\u0011I0!\u0018\u0002\tU$\u0018\u000e\\\u0005\u0005\u0005{\u00149P\u0001\u0003MSN$\b#B<\u0004\u0002\t\u001d\u0018bAB\u0002q\nAq\n\u001d;j_:\fG.\u0001\tva\u0012\fG/Z*uCR,')_&fsV!1\u0011BB\b)\u0011\u0019Ya!\u0005\u0011\u000by\u0003qm!\u0004\u0011\u0007!\u001cy\u0001\u0002\u0004\u0003l\u001e\u0012\ra\u001b\u0005\b\u0007'9\u0003\u0019AB\u000b\u0003))\b\u000fZ1uK\u001a+hn\u0019\t\u000b\u0003\u0017\nyNa=\u0004\u0018\r]\u0001#B<\u0004\u0002\r5Q\u0003BB\u000e\u0007C!ba!\b\u0004$\r%\u0002#\u00020\u0001O\u000e}\u0001c\u00015\u0004\"\u00111!1\u001e\u0015C\u0002-Dqaa\u0005)\u0001\u0004\u0019)\u0003\u0005\u0006\u0002L\u0005}'1_B\u0014\u0007O\u0001Ra^B\u0001\u0007?Aq!!!)\u0001\u0004\t\u0019)\u0006\u0003\u0004.\rMBCBB\u0018\u0007k\u0019Y\u0004E\u0003_\u0001\u001d\u001c\t\u0004E\u0002i\u0007g!aAa;*\u0005\u0004Y\u0007bBB\nS\u0001\u00071q\u0007\t\u000b\u0003\u0017\nyNa=\u0004:\re\u0002#B<\u0004\u0002\rE\u0002bBAfS\u0001\u0007\u0011QZ\u000b\u0005\u0007\u007f\u0019)\u0005\u0006\u0005\u0004B\r\u001d3QJB(!\u0015q\u0006aZB\"!\rA7Q\t\u0003\u0007\u0005WT#\u0019A6\t\u000f\rM!\u00061\u0001\u0004JAQ\u00111JAp\u0005g\u001cYea\u0013\u0011\u000b]\u001c\taa\u0011\t\u000f\u0005-'\u00061\u0001\u0002N\"91\u0011\u000b\u0016A\u0002\rM\u0013AC5oSRL\u0017\r\u001c*E\tB)qO_4\u0004D\u0005IQ.\u00199WC2,Xm]\u000b\u0005\u00073\u001ay\u0006\u0006\u0003\u0004\\\r\r\u0004#\u00020\u0001O\u000eu\u0003c\u00015\u0004`\u001111\u0011M\u0016C\u0002-\u0014\u0011!\u0016\u0005\b\u0003\u000fZ\u0003\u0019AB3!\u001d\tY%!\u0015s\u0007;\nQB\u001a7bi6\u000b\u0007OV1mk\u0016\u001cX\u0003BB6\u0007c\"Ba!\u001c\u0004tA)a\fA4\u0004pA\u0019\u0001n!\u001d\u0005\r\r\u0005DF1\u0001l\u0011\u001d\t9\u0005\fa\u0001\u0007k\u0002r!a\u0013\u0004xI\u001cy'\u0003\u0003\u0004z\u00055#a\u0004$mCRl\u0015\r\u001d$v]\u000e$\u0018n\u001c8\u0002\u000f\r|wM]8vaV!1qPBE)\u0011\u0019\ti!$\u0011\u000by\u0003qma!\u0011\r\t,\u0017QXBC!\u0019\t9&a0\u0004\bB\u0019\u0001n!#\u0005\r\r-UF1\u0001l\u0005\u00059\u0006bBBH[\u0001\u00071\u0011S\u0001\u0006_RDWM\u001d\t\u0006=\u000297qQ\u000b\u0005\u0007+\u001by\n\u0006\u0004\u0004\u0018\u000e\u00056Q\u0015\t\u0006=\u000297\u0011\u0014\t\u0007E\u0016\fila'\u0011\r\u0005]\u0013qXBO!\rA7q\u0014\u0003\u0007\u0007\u0017s#\u0019A6\t\u000f\r=e\u00061\u0001\u0004$B)a\fA4\u0004\u001e\"9\u0011\u0011\u0011\u0018A\u0002\u0005\rU\u0003BBU\u0007g#baa+\u00046\u000ee\u0006#\u00020\u0001O\u000e5\u0006C\u00022f\u0003{\u001by\u000b\u0005\u0004\u0002X\u0005}6\u0011\u0017\t\u0004Q\u000eMFABBF_\t\u00071\u000eC\u0004\u0004\u0010>\u0002\raa.\u0011\u000by\u0003qm!-\t\u000f\u0005-w\u00061\u0001\u0002N\u0006!!n\\5o+\u0011\u0019yla2\u0015\t\r\u00057\u0011\u001a\t\u0006=\u0002971\u0019\t\u0006E\u0016\u00148Q\u0019\t\u0004Q\u000e\u001dGABBFa\t\u00071\u000eC\u0004\u0004\u0010B\u0002\raa3\u0011\u000by\u0003qm!2\u0016\t\r=7q\u001b\u000b\u0007\u0007#\u001cIn!8\u0011\u000by\u0003qma5\u0011\u000b\t,'o!6\u0011\u0007!\u001c9\u000e\u0002\u0004\u0004\fF\u0012\ra\u001b\u0005\b\u0007\u001f\u000b\u0004\u0019ABn!\u0015q\u0006aZBk\u0011\u001d\t\t)\ra\u0001\u0003\u0007+Ba!9\u0004jR111]Bv\u0007_\u0004RA\u0018\u0001h\u0007K\u0004RAY3s\u0007O\u00042\u0001[Bu\t\u0019\u0019YI\rb\u0001W\"91q\u0012\u001aA\u0002\r5\b#\u00020\u0001O\u000e\u001d\bbBAfe\u0001\u0007\u0011QZ\u0001\u000eY\u00164GoT;uKJTu.\u001b8\u0016\t\rU8q \u000b\u0005\u0007o$\t\u0001E\u0003_\u0001\u001d\u001cI\u0010E\u0003cKJ\u001cY\u0010E\u0003x\u0007\u0003\u0019i\u0010E\u0002i\u0007\u007f$aaa#4\u0005\u0004Y\u0007bBBHg\u0001\u0007A1\u0001\t\u0006=\u000297Q`\u000b\u0005\t\u000f!\t\u0002\u0006\u0004\u0005\n\u0011MAq\u0003\t\u0006=\u00029G1\u0002\t\u0006E\u0016\u0014HQ\u0002\t\u0006o\u000e\u0005Aq\u0002\t\u0004Q\u0012EAABBFi\t\u00071\u000eC\u0004\u0004\u0010R\u0002\r\u0001\"\u0006\u0011\u000by\u0003q\rb\u0004\t\u000f\u0005\u0005E\u00071\u0001\u0002\u0004V!A1\u0004C\u0013)\u0019!i\u0002b\n\u0005,A)a\fA4\u0005 A)!-\u001a:\u0005\"A)qo!\u0001\u0005$A\u0019\u0001\u000e\"\n\u0005\r\r-UG1\u0001l\u0011\u001d\u0019y)\u000ea\u0001\tS\u0001RA\u0018\u0001h\tGAq!a36\u0001\u0004\ti-\u0001\bsS\u001eDGoT;uKJTu.\u001b8\u0016\t\u0011EB1\b\u000b\u0005\tg!i\u0004E\u0003_\u0001\u001d$)\u0004\u0005\u0004cK\u0012]B\u0011\b\t\u0005o\u000e\u0005!\u000fE\u0002i\tw!aaa#7\u0005\u0004Y\u0007bBBHm\u0001\u0007Aq\b\t\u0006=\u00029G\u0011H\u000b\u0005\t\u0007\"Y\u0005\u0006\u0004\u0005F\u00115C\u0011\u000b\t\u0006=\u00029Gq\t\t\u0007E\u0016$9\u0004\"\u0013\u0011\u0007!$Y\u0005\u0002\u0004\u0004\f^\u0012\ra\u001b\u0005\b\u0007\u001f;\u0004\u0019\u0001C(!\u0015q\u0006a\u001aC%\u0011\u001d\t\ti\u000ea\u0001\u0003\u0007+B\u0001\"\u0016\u0005^Q1Aq\u000bC0\tG\u0002RA\u0018\u0001h\t3\u0002bAY3\u00058\u0011m\u0003c\u00015\u0005^\u0011111\u0012\u001dC\u0002-Dqaa$9\u0001\u0004!\t\u0007E\u0003_\u0001\u001d$Y\u0006C\u0004\u0002Lb\u0002\r!!4\u0002\u001b\u0019,H\u000e\\(vi\u0016\u0014(j\\5o+\u0011!I\u0007b\u001d\u0015\t\u0011-DQ\u000f\t\u0006=\u00029GQ\u000e\t\u0007E\u0016$9\u0004b\u001c\u0011\u000b]\u001c\t\u0001\"\u001d\u0011\u0007!$\u0019\b\u0002\u0004\u0004\ff\u0012\ra\u001b\u0005\b\u0007\u001fK\u0004\u0019\u0001C<!\u0015q\u0006a\u001aC9+\u0011!Y\b\"\"\u0015\r\u0011uDq\u0011CF!\u0015q\u0006a\u001aC@!\u0019\u0011W\rb\u000e\u0005\u0002B)qo!\u0001\u0005\u0004B\u0019\u0001\u000e\"\"\u0005\r\r-%H1\u0001l\u0011\u001d\u0019yI\u000fa\u0001\t\u0013\u0003RA\u0018\u0001h\t\u0007Cq!!!;\u0001\u0004\t\u0019)\u0006\u0003\u0005\u0010\u0012eEC\u0002CI\t7#y\nE\u0003_\u0001\u001d$\u0019\n\u0005\u0004cK\u0012]BQ\u0013\t\u0006o\u000e\u0005Aq\u0013\t\u0004Q\u0012eEABBFw\t\u00071\u000eC\u0004\u0004\u0010n\u0002\r\u0001\"(\u0011\u000by\u0003q\rb&\t\u000f\u0005-7\b1\u0001\u0002N\u0006\t2/\u0019<f\u0003ND\u0015\rZ8pa\u001aKG.Z:\u0015\r\u0011\u0015F1\u0016C`!\r\u0011GqU\u0005\u0004\tS\u001b'\u0001B+oSRDq\u0001\",=\u0001\u0004!y+\u0001\u0004qe\u00164\u0017\u000e\u001f\t\u0005\tc#IL\u0004\u0003\u00054\u0012U\u0006c\u0001BhG&\u0019AqW2\u0002\rA\u0013X\rZ3g\u0013\u0011!Y\f\"0\u0003\rM#(/\u001b8h\u0015\r!9l\u0019\u0005\b\t\u0003d\u0004\u0019\u0001CX\u0003\u0019\u0019XO\u001a4jqV!AQ\u0019Cz)1!)\u000bb2\u0005J\u0012-GQ\u001cCv\u0011\u001d!i+\u0010a\u0001\t_Cq\u0001\"1>\u0001\u0004!y\u000bC\u0004\u0005Nv\u0002\r\u0001b4\u0002\u0011-,\u0017p\u00117bgN\u0004D\u0001\"5\u0005ZB1A\u0011\u0017Cj\t/LA\u0001\"6\u0005>\n)1\t\\1tgB\u0019\u0001\u000e\"7\u0005\u0017\u0011mG1ZA\u0001\u0002\u0003\u0015\ta\u001b\u0002\u0004?\u0012\u001a\u0004b\u0002Cp{\u0001\u0007A\u0011]\u0001\u000bm\u0006dW/Z\"mCN\u001c\b\u0007\u0002Cr\tO\u0004b\u0001\"-\u0005T\u0012\u0015\bc\u00015\u0005h\u0012YA\u0011\u001eCo\u0003\u0003\u0005\tQ!\u0001l\u0005\ryF\u0005\u000e\u0005\b\t[l\u0004\u0019\u0001Cx\u0003EyW\u000f\u001e9vi\u001a{'/\\1u\u00072\f7o\u001d\t\u0007\tc#\u0019\u000e\"=\u0011\u0007!$\u0019\u0010B\u0004\u0005vv\u0012\r\u0001b>\u0003\u0003\u0019\u000b2\u0001\u001cC}a\u0019!Y0\"\u0004\u0006\u0014AAAQ`C\u0004\u000b\u0017)\t\"\u0004\u0002\u0005\u0000*!Q\u0011AC\u0002\u0003\u0019i\u0017\r\u001d:fI*\u0019QQ\u0001,\u0002\r!\fGm\\8q\u0013\u0011)I\u0001b@\u0003\u0019=+H\u000f];u\r>\u0014X.\u0019;\u0011\u0007!,i\u0001B\u0006\u0006\u0010\u0011M\u0018\u0011!A\u0001\u0006\u0003Y'aA0%cA\u0019\u0001.b\u0005\u0005\u0017\u0015UA1_A\u0001\u0002\u0003\u0015\ta\u001b\u0002\u0004?\u0012\u0012T\u0003BC\r\u000b{!b\u0002\"*\u0006\u001c\u0015uQqDC\u0016\u000bo)\t\u0006C\u0004\u0005.z\u0002\r\u0001b,\t\u000f\u0011\u0005g\b1\u0001\u00050\"9AQ\u001a A\u0002\u0015\u0005\u0002\u0007BC\u0012\u000bO\u0001b\u0001\"-\u0005T\u0016\u0015\u0002c\u00015\u0006(\u0011YQ\u0011FC\u0010\u0003\u0003\u0005\tQ!\u0001l\u0005\ryFe\u000e\u0005\b\t?t\u0004\u0019AC\u0017a\u0011)y#b\r\u0011\r\u0011EF1[C\u0019!\rAW1\u0007\u0003\f\u000bk)Y#!A\u0001\u0002\u000b\u00051NA\u0002`IaBq\u0001\"<?\u0001\u0004)I\u0004\u0005\u0004\u00052\u0012MW1\b\t\u0004Q\u0016uBa\u0002C{}\t\u0007QqH\t\u0004Y\u0016\u0005\u0003GBC\"\u000b\u000f*i\u0005\u0005\u0005\u0005~\u0016\u001dQQIC&!\rAWq\t\u0003\f\u000b\u0013*i$!A\u0001\u0002\u000b\u00051NA\u0002`IU\u00022\u0001[C'\t-)y%\"\u0010\u0002\u0002\u0003\u0005)\u0011A6\u0003\u0007}#c\u0007C\u0004\u0006Ty\u0002\r!\"\u0016\u0002\t\r|gN\u001a\t\u0005\t{,9&\u0003\u0003\u0006Z\u0011}(a\u0002&pE\u000e{gNZ\u0001\u0018g\u00064X-Q:OK^\f\u0005+\u0013%bI>|\u0007OR5mKN$b\u0001\"*\u0006`\u0015\u0005\u0004b\u0002CW\u007f\u0001\u0007Aq\u0016\u0005\b\t\u0003|\u0004\u0019\u0001CX+\u0011))'\"#\u0015\u0019\u0011\u0015VqMC5\u000bW*9(b!\t\u000f\u00115\u0006\t1\u0001\u00050\"9A\u0011\u0019!A\u0002\u0011=\u0006b\u0002Cg\u0001\u0002\u0007QQ\u000e\u0019\u0005\u000b_*\u0019\b\u0005\u0004\u00052\u0012MW\u0011\u000f\t\u0004Q\u0016MDaCC;\u000bW\n\t\u0011!A\u0003\u0002-\u0014Aa\u0018\u00132c!9Aq\u001c!A\u0002\u0015e\u0004\u0007BC>\u000b\u007f\u0002b\u0001\"-\u0005T\u0016u\u0004c\u00015\u0006\u0000\u0011YQ\u0011QC<\u0003\u0003\u0005\tQ!\u0001l\u0005\u0011yF%\r\u001a\t\u000f\u00115\b\t1\u0001\u0006\u0006B1A\u0011\u0017Cj\u000b\u000f\u00032\u0001[CE\t\u001d!)\u0010\u0011b\u0001\u000b\u0017\u000b2\u0001\\CGa\u0019)y)b'\u0006\"BAQ\u0011SCL\u000b3+y*\u0004\u0002\u0006\u0014*!QQSC\u0002\u0003%i\u0017\r\u001d:fIV\u001cW-\u0003\u0003\u0006\n\u0015M\u0005c\u00015\u0006\u001c\u0012YQQTCE\u0003\u0003\u0005\tQ!\u0001l\u0005\ryF%\u000f\t\u0004Q\u0016\u0005FaCCR\u000b\u0013\u000b\t\u0011!A\u0003\u0002-\u0014Aa\u0018\u00132aU!QqUCf)9!)+\"+\u0006,\u00165V\u0011XCc\u000b?Dq\u0001\",B\u0001\u0004!y\u000bC\u0004\u0005B\u0006\u0003\r\u0001b,\t\u000f\u00115\u0017\t1\u0001\u00060B\"Q\u0011WC[!\u0019!\t\fb5\u00064B\u0019\u0001.\".\u0005\u0017\u0015]VQVA\u0001\u0002\u0003\u0015\ta\u001b\u0002\u0005?\u0012\nT\u0007C\u0004\u0005`\u0006\u0003\r!b/1\t\u0015uV\u0011\u0019\t\u0007\tc#\u0019.b0\u0011\u0007!,\t\rB\u0006\u0006D\u0016e\u0016\u0011!A\u0001\u0006\u0003Y'\u0001B0%cYBq\u0001\"<B\u0001\u0004)9\r\u0005\u0004\u00052\u0012MW\u0011\u001a\t\u0004Q\u0016-Ga\u0002C{\u0003\n\u0007QQZ\t\u0004Y\u0016=\u0007GBCi\u000b+,Y\u000e\u0005\u0005\u0006\u0012\u0016]U1[Cm!\rAWQ\u001b\u0003\f\u000b/,Y-!A\u0001\u0002\u000b\u00051N\u0001\u0003`IE\u001a\u0004c\u00015\u0006\\\u0012YQQ\\Cf\u0003\u0003\u0005\tQ!\u0001l\u0005\u0011yF%\r\u001b\t\u0013\u0015M\u0013\t%AA\u0002\u0015\u0005\b\u0003BCr\u000bOl!!\":\u000b\t\u0015MS1A\u0005\u0005\u000bS,)OA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\"g\u00064X-Q:OK^\f\u0005+\u0013%bI>|\u0007OR5mKN$C-\u001a4bk2$HEN\u000b\u0005\u000b_4)!\u0006\u0002\u0006r*\"Q\u0011]CzW\t))\u0010\u0005\u0003\u0006x\u001a\u0005QBAC}\u0015\u0011)Y0\"@\u0002\u0013Ut7\r[3dW\u0016$'bAC\u0000G\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0019\rQ\u0011 \u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,Ga\u0002C{\u0005\n\u0007aqA\t\u0004Y\u001a%\u0001G\u0002D\u0006\r\u001f1\u0019\u0002\u0005\u0005\u0006\u0012\u0016]eQ\u0002D\t!\rAgq\u0002\u0003\f\u000b/4)!!A\u0001\u0002\u000b\u00051\u000eE\u0002i\r'!1\"\"8\u0007\u0006\u0005\u0005\t\u0011!B\u0001W\u0006iAo\u001c&bm\u0006$5\u000b\u001e:fC6$\"A\"\u0007\u0011\ty3Y\"Y\u0005\u0004\r;q%a\u0003&bm\u0006$5\u000b\u001e:fC6\f\u0001b\u00197bgN$\u0016mZ\u000b\u0003\rG\u0001R!a\u0004\u0002\u0016\u0005\f\u0011b\u00197bgN$\u0016m\u001a\u0011\u0002\u001f)\u000bg/\u0019)bSJ$5\u000b\u001e:fC6\u0004\"AX$\u0014\u000b\u001d3iCb\r\u0011\u0007\t4y#C\u0002\u00072\r\u0014a!\u00118z%\u00164\u0007\u0003\u0002D\u001b\rwi!Ab\u000e\u000b\t\u0019e\u0012QL\u0001\u0003S>LAA\"\u0010\u00078\ta1+\u001a:jC2L'0\u00192mKR\u0011a\u0011F\u0001\u0010MJ|W\u000eU1je\u0012\u001bFO]3b[V1aQ\tD'\r#\"BAb\u0012\u0007`Q1a\u0011\nD*\r3\u0002bA\u0018\u0001\u0007L\u0019=\u0003c\u00015\u0007N\u0011)!.\u0013b\u0001WB\u0019\u0001N\"\u0015\u0005\u000bQL%\u0019A6\t\u0013\u0019U\u0013*!AA\u0004\u0019]\u0013AC3wS\u0012,gnY3%cA1\u0011qBA\u000b\r\u0017B\u0011Bb\u0017J\u0003\u0003\u0005\u001dA\"\u0018\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007\u0005\u0004\u0002\u0010\u0005Uaq\n\u0005\u0007y&\u0003\rA\"\u0019\u0011\u000b}\f\u0019Ab\u0019\u0011\r\t,g1\nD(\u0003=1'o\\7KCZ\fGi\u0015;sK\u0006lWC\u0002D5\r_2\u0019\b\u0006\u0003\u0007l\u0019U\u0004C\u00020\u0001\r[2\t\bE\u0002i\r_\"QA\u001b&C\u0002-\u00042\u0001\u001bD:\t\u0015!(J1\u0001l\u0011\u0019a(\n1\u0001\u0007xA)aLb\u0007\u0007zA1!-\u001aD7\rc\nqb]2bY\u0006$vNS1wC2{gnZ\u000b\u0005\r\u007f29\t\u0006\u0003\u0007\u0002\u001aUE\u0003\u0002DB\r\u001f\u0003bA\u0018\u0001\u0007\u0006\u001a%\u0005c\u00015\u0007\b\u0012)!n\u0013b\u0001WB!\u0011q\u000bDF\u0013\u00111i)!\u0017\u0003\t1{gn\u001a\u0005\n\r#[\u0015\u0011!a\u0002\r'\u000b!\"\u001a<jI\u0016t7-\u001a\u00134!\u0019\ty!!\u0006\u0007\u0006\"1Ap\u0013a\u0001\r/\u0003bA\u0018\u0001\u0007\u0006\u001ae\u0005c\u00012\u0007\u001c&\u0019aQR2\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0019\u0005\u0006\u0003BA,\rGKAA\"*\u0002Z\t1qJ\u00196fGR\u0004"
)
public class JavaPairDStream extends AbstractJavaDStreamLike {
   private final DStream dstream;
   private final ClassTag kManifest;
   private final ClassTag vManifest;
   private final ClassTag classTag;

   public static JavaPairDStream scalaToJavaLong(final JavaPairDStream dstream, final ClassTag evidence$3) {
      return JavaPairDStream$.MODULE$.scalaToJavaLong(dstream, evidence$3);
   }

   public static JavaPairDStream fromJavaDStream(final JavaDStream dstream) {
      return JavaPairDStream$.MODULE$.fromJavaDStream(dstream);
   }

   public static JavaPairDStream fromPairDStream(final DStream dstream, final ClassTag evidence$1, final ClassTag evidence$2) {
      return JavaPairDStream$.MODULE$.fromPairDStream(dstream, evidence$1, evidence$2);
   }

   public DStream dstream() {
      return this.dstream;
   }

   public ClassTag kManifest() {
      return this.kManifest;
   }

   public ClassTag vManifest() {
      return this.vManifest;
   }

   public JavaPairRDD wrapRDD(final RDD rdd) {
      return .MODULE$.fromRDD(rdd, this.kManifest(), this.vManifest());
   }

   public JavaPairDStream filter(final Function f) {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().filter((x) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(f, x))), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream cache() {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().cache(), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream persist() {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().persist(), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream persist(final StorageLevel storageLevel) {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().persist(storageLevel), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream repartition(final int numPartitions) {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().repartition(numPartitions), this.kManifest(), this.vManifest());
   }

   public JavaPairRDD compute(final Time validTime) {
      Option var3 = this.dstream().compute(validTime);
      if (var3 instanceof Some var4) {
         RDD rdd = (RDD)var4.value();
         return new JavaPairRDD(rdd, this.kManifest(), this.vManifest());
      } else if (scala.None..MODULE$.equals(var3)) {
         return null;
      } else {
         throw new MatchError(var3);
      }
   }

   public JavaPairDStream window(final Duration windowDuration) {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().window(windowDuration), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream window(final Duration windowDuration, final Duration slideDuration) {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().window(windowDuration, slideDuration), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream union(final JavaPairDStream that) {
      return JavaPairDStream$.MODULE$.fromPairDStream(this.dstream().union(that.dstream()), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream groupByKey() {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKey();
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$1x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$1x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream groupByKey(final int numPartitions) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKey(numPartitions);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$2x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$2x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream groupByKey(final Partitioner partitioner) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKey(partitioner);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$3x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$3x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream reduceByKey(final Function2 func) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKey(.MODULE$.toScalaFunction2(func)), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKey(final Function2 func, final int numPartitions) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKey(.MODULE$.toScalaFunction2(func), numPartitions), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKey(final Function2 func, final Partitioner partitioner) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKey(.MODULE$.toScalaFunction2(func), partitioner), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream combineByKey(final Function createCombiner, final Function2 mergeValue, final Function2 mergeCombiners, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      PairDStreamFunctions qual$1 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null);
      Function1 x$5 = .MODULE$.toScalaFunction(createCombiner);
      scala.Function2 x$6 = .MODULE$.toScalaFunction2(mergeValue);
      scala.Function2 x$7 = .MODULE$.toScalaFunction2(mergeCombiners);
      boolean x$9 = qual$1.combineByKey$default$5();
      return JavaPairDStream$.MODULE$.fromPairDStream(qual$1.combineByKey(x$5, x$6, x$7, partitioner, x$9, cm), this.kManifest(), cm);
   }

   public JavaPairDStream combineByKey(final Function createCombiner, final Function2 mergeValue, final Function2 mergeCombiners, final Partitioner partitioner, final boolean mapSideCombine) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).combineByKey(.MODULE$.toScalaFunction(createCombiner), .MODULE$.toScalaFunction2(mergeValue), .MODULE$.toScalaFunction2(mergeCombiners), partitioner, mapSideCombine, cm), this.kManifest(), cm);
   }

   public JavaPairDStream groupByKeyAndWindow(final Duration windowDuration) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKeyAndWindow(windowDuration);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$4x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$4x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream groupByKeyAndWindow(final Duration windowDuration, final Duration slideDuration) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKeyAndWindow(windowDuration, slideDuration);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$5x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$5x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream groupByKeyAndWindow(final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKeyAndWindow(windowDuration, slideDuration, numPartitions);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$6x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$6x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream groupByKeyAndWindow(final Duration windowDuration, final Duration slideDuration, final Partitioner partitioner) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).groupByKeyAndWindow(windowDuration, slideDuration, partitioner);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Iterable.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((x$7x) -> scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava(x$7x).asJava(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(java.lang.Iterable.class));
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKeyAndWindow(.MODULE$.toScalaFunction2(reduceFunc), windowDuration), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKeyAndWindow(.MODULE$.toScalaFunction2(reduceFunc), windowDuration, slideDuration), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKeyAndWindow(.MODULE$.toScalaFunction2(reduceFunc), windowDuration, slideDuration, numPartitions), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration, final Partitioner partitioner) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKeyAndWindow(.MODULE$.toScalaFunction2(reduceFunc), windowDuration, slideDuration, partitioner), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      PairDStreamFunctions qual$1 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null);
      scala.Function2 x$5 = .MODULE$.toScalaFunction2(reduceFunc);
      scala.Function2 x$6 = .MODULE$.toScalaFunction2(invReduceFunc);
      int x$9 = qual$1.reduceByKeyAndWindow$default$5();
      Function1 x$10 = qual$1.reduceByKeyAndWindow$default$6();
      return var10000.fromPairDStream(qual$1.reduceByKeyAndWindow(x$5, x$6, windowDuration, slideDuration, x$9, x$10), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration, final int numPartitions, final Function filterFunc) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKeyAndWindow(.MODULE$.toScalaFunction2(reduceFunc), .MODULE$.toScalaFunction2(invReduceFunc), windowDuration, slideDuration, numPartitions, (p) -> BoxesRunTime.boxToBoolean($anonfun$reduceByKeyAndWindow$1(filterFunc, p))), this.kManifest(), this.vManifest());
   }

   public JavaPairDStream reduceByKeyAndWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration, final Partitioner partitioner, final Function filterFunc) {
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).reduceByKeyAndWindow(.MODULE$.toScalaFunction2(reduceFunc), .MODULE$.toScalaFunction2(invReduceFunc), windowDuration, slideDuration, partitioner, (p) -> BoxesRunTime.boxToBoolean($anonfun$reduceByKeyAndWindow$2(filterFunc, p))), this.kManifest(), this.vManifest());
   }

   public JavaMapWithStateDStream mapWithState(final StateSpec spec) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return new JavaMapWithStateDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).mapWithState(spec, org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag(), org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag()));
   }

   private scala.Function2 convertUpdateStateFunction(final Function2 in) {
      scala.Function2 scalaFunc = (values, state) -> {
         List list = scala.jdk.CollectionConverters..MODULE$.SeqHasAsJava(values).asJava();
         Optional scalaState = org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(state);
         Optional result = (Optional).MODULE$.toScalaFunction2(in).apply(list, scalaState);
         return (Option)(result.isPresent() ? new Some(result.get()) : scala.None..MODULE$);
      };
      return scalaFunc;
   }

   public JavaPairDStream updateStateByKey(final Function2 updateFunc) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).updateStateByKey(this.convertUpdateStateFunction(updateFunc), cm), this.kManifest(), cm);
   }

   public JavaPairDStream updateStateByKey(final Function2 updateFunc, final int numPartitions) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).updateStateByKey(this.convertUpdateStateFunction(updateFunc), numPartitions, cm), this.kManifest(), cm);
   }

   public JavaPairDStream updateStateByKey(final Function2 updateFunc, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).updateStateByKey(this.convertUpdateStateFunction(updateFunc), partitioner, cm), this.kManifest(), cm);
   }

   public JavaPairDStream updateStateByKey(final Function2 updateFunc, final Partitioner partitioner, final JavaPairRDD initialRDD) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).updateStateByKey(this.convertUpdateStateFunction(updateFunc), partitioner, .MODULE$.toRDD(initialRDD), cm), this.kManifest(), cm);
   }

   public JavaPairDStream mapValues(final Function f) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).mapValues(.MODULE$.toScalaFunction(f), cm), this.kManifest(), cm);
   }

   public JavaPairDStream flatMapValues(final FlatMapFunction f) {
      ClassTag cm = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.AnyRef());
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).flatMapValues(fn$1(f), cm), this.kManifest(), cm);
   }

   public JavaPairDStream cogroup(final JavaPairDStream other) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).cogroup(other.dstream(), cm);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((t) -> new Tuple2(scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)t._1()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)t._2()).asJava()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream cogroup(final JavaPairDStream other, final int numPartitions) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).cogroup(other.dstream(), numPartitions, cm);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((t) -> new Tuple2(scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)t._1()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)t._2()).asJava()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream cogroup(final JavaPairDStream other, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream x$5 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).cogroup(other.dstream(), partitioner, cm);
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$5);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$5, x$6, x$7, (Ordering)null).mapValues((t) -> new Tuple2(scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)t._1()).asJava(), scala.jdk.CollectionConverters..MODULE$.IterableHasAsJava((Iterable)t._2()).asJava()), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream join(final JavaPairDStream other) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).join(other.dstream(), cm), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream join(final JavaPairDStream other, final int numPartitions) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).join(other.dstream(), numPartitions, cm), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream join(final JavaPairDStream other, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).join(other.dstream(), partitioner, cm), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream leftOuterJoin(final JavaPairDStream other) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).leftOuterJoin(other.dstream(), cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Object v = x0$1._1();
            Option w = (Option)x0$1._2();
            return new Tuple2(v, org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(w));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream leftOuterJoin(final JavaPairDStream other, final int numPartitions) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).leftOuterJoin(other.dstream(), numPartitions, cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Object v = x0$1._1();
            Option w = (Option)x0$1._2();
            return new Tuple2(v, org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(w));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream leftOuterJoin(final JavaPairDStream other, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).leftOuterJoin(other.dstream(), partitioner, cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Object v = x0$1._1();
            Option w = (Option)x0$1._2();
            return new Tuple2(v, org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(w));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream rightOuterJoin(final JavaPairDStream other) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).rightOuterJoin(other.dstream(), cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Option v = (Option)x0$1._1();
            Object w = x0$1._2();
            return new Tuple2(org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), w);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream rightOuterJoin(final JavaPairDStream other, final int numPartitions) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).rightOuterJoin(other.dstream(), numPartitions, cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Option v = (Option)x0$1._1();
            Object w = x0$1._2();
            return new Tuple2(org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), w);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream rightOuterJoin(final JavaPairDStream other, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).rightOuterJoin(other.dstream(), partitioner, cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Option v = (Option)x0$1._1();
            Object w = x0$1._2();
            return new Tuple2(org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), w);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream fullOuterJoin(final JavaPairDStream other) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).fullOuterJoin(other.dstream(), cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Option v = (Option)x0$1._1();
            Option w = (Option)x0$1._2();
            return new Tuple2(org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(w));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream fullOuterJoin(final JavaPairDStream other, final int numPartitions) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).fullOuterJoin(other.dstream(), numPartitions, cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Option v = (Option)x0$1._1();
            Option w = (Option)x0$1._2();
            return new Tuple2(org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(w));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public JavaPairDStream fullOuterJoin(final JavaPairDStream other, final Partitioner partitioner) {
      ClassTag cm = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream joinResult = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).fullOuterJoin(other.dstream(), partitioner, cm);
      JavaPairDStream$ var10000 = JavaPairDStream$.MODULE$;
      ClassTag x$6 = this.kManifest();
      ClassTag x$7 = scala.reflect.ClassTag..MODULE$.apply(Tuple2.class);
      Null x$8 = DStream$.MODULE$.toPairDStreamFunctions$default$4(joinResult);
      return var10000.fromPairDStream(DStream$.MODULE$.toPairDStreamFunctions(joinResult, x$6, x$7, (Ordering)null).mapValues((x0$1) -> {
         if (x0$1 != null) {
            Option v = (Option)x0$1._1();
            Option w = (Option)x0$1._2();
            return new Tuple2(org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(v), org.apache.spark.api.java.JavaUtils..MODULE$.optionToOptional(w));
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)), this.kManifest(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public void saveAsHadoopFiles(final String prefix, final String suffix) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).saveAsHadoopFiles(prefix, suffix, scala.reflect.ClassTag..MODULE$.Nothing());
   }

   public void saveAsHadoopFiles(final String prefix, final String suffix, final Class keyClass, final Class valueClass, final Class outputFormatClass) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      PairDStreamFunctions qual$1 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null);
      JobConf x$10 = qual$1.saveAsHadoopFiles$default$6();
      qual$1.saveAsHadoopFiles(prefix, suffix, keyClass, valueClass, outputFormatClass, x$10);
   }

   public void saveAsHadoopFiles(final String prefix, final String suffix, final Class keyClass, final Class valueClass, final Class outputFormatClass, final JobConf conf) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).saveAsHadoopFiles(prefix, suffix, keyClass, valueClass, outputFormatClass, conf);
   }

   public void saveAsNewAPIHadoopFiles(final String prefix, final String suffix) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).saveAsNewAPIHadoopFiles(prefix, suffix, scala.reflect.ClassTag..MODULE$.Nothing());
   }

   public void saveAsNewAPIHadoopFiles(final String prefix, final String suffix, final Class keyClass, final Class valueClass, final Class outputFormatClass) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      PairDStreamFunctions qual$1 = DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null);
      Configuration x$10 = qual$1.saveAsNewAPIHadoopFiles$default$6();
      qual$1.saveAsNewAPIHadoopFiles(prefix, suffix, keyClass, valueClass, outputFormatClass, x$10);
   }

   public void saveAsNewAPIHadoopFiles(final String prefix, final String suffix, final Class keyClass, final Class valueClass, final Class outputFormatClass, final Configuration conf) {
      DStream x$1 = this.dstream();
      ClassTag x$2 = this.kManifest();
      ClassTag x$3 = this.vManifest();
      Null x$4 = DStream$.MODULE$.toPairDStreamFunctions$default$4(x$1);
      DStream$.MODULE$.toPairDStreamFunctions(x$1, x$2, x$3, (Ordering)null).saveAsNewAPIHadoopFiles(prefix, suffix, keyClass, valueClass, outputFormatClass, conf);
   }

   public Configuration saveAsNewAPIHadoopFiles$default$6() {
      return this.dstream().context().sparkContext().hadoopConfiguration();
   }

   public JavaDStream toJavaDStream() {
      return new JavaDStream(this.dstream(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public ClassTag classTag() {
      return this.classTag;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$1(final Function f$1, final Tuple2 x) {
      return (Boolean)f$1.call(x);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reduceByKeyAndWindow$1(final Function filterFunc$1, final Tuple2 p) {
      return (Boolean).MODULE$.toScalaFunction(filterFunc$1).apply(p);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$reduceByKeyAndWindow$2(final Function filterFunc$2, final Tuple2 p) {
      return (Boolean).MODULE$.toScalaFunction(filterFunc$2).apply(p);
   }

   private static final Function1 fn$1(final FlatMapFunction f$2) {
      return (x) -> scala.jdk.CollectionConverters..MODULE$.IteratorHasAsScala(f$2.call(x)).asScala();
   }

   public JavaPairDStream(final DStream dstream, final ClassTag kManifest, final ClassTag vManifest) {
      this.dstream = dstream;
      this.kManifest = kManifest;
      this.vManifest = vManifest;
      this.classTag = org.apache.spark.api.java.JavaSparkContext..MODULE$.fakeClassTag();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
