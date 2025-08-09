package org.apache.spark.streaming.dstream;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.spark.HashPartitioner;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.PairRDDFunctions;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.StateSpec;
import org.apache.spark.streaming.StateSpecImpl;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.StreamingContext$;
import org.apache.spark.streaming.Time;
import org.apache.spark.util.SerializableConfiguration;
import org.apache.spark.util.SerializableJobConf;
import scala.Function1;
import scala.Function2;
import scala.Function4;
import scala.None;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ArrayBuffer.;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\rh\u0001B!C\u00015C\u0001\"\u0019\u0001\u0003\u0002\u0003\u0006IA\u0019\u0005\to\u0002\u0011\t\u0011)A\u0006q\"Aa\u0010\u0001B\u0001B\u0003-q\u0010\u0003\u0006\u0002\u0002\u0001\u0011\t\u0011)A\u0006\u0003\u0007Aq!!\u0003\u0001\t\u0003\tY\u0001\u0003\u0005\u0002\u001a\u0001!\t\u0001RA\u000e\u0011!\t)\u0003\u0001C\u0001\t\u0006\u001d\u0002\u0002CA\u0019\u0001\u0011\u0005A)a\r\t\u0015\u0005\u0015\u0003!%A\u0005\u0002\u0011\u000b9\u0005C\u0004\u0002^\u0001!\t!a\u0018\t\u000f\u0005u\u0003\u0001\"\u0001\u0002l!9\u0011Q\f\u0001\u0005\u0002\u0005=\u0004bBA>\u0001\u0011\u0005\u0011Q\u0010\u0005\b\u0003w\u0002A\u0011AAE\u0011\u001d\tY\b\u0001C\u0001\u0003\u001fCq!!&\u0001\t\u0003\t9\nC\u0005\u0002P\u0002\t\n\u0011\"\u0001\u0002R\"9\u0011\u0011\u001c\u0001\u0005\u0002\u0005m\u0007bBAm\u0001\u0011\u0005\u0011q\u001d\u0005\b\u00033\u0004A\u0011AAx\u0011\u001d\tI\u000e\u0001C\u0001\u0003oDq!a@\u0001\t\u0003\u0011\t\u0001C\u0004\u0002\u0000\u0002!\tAa\u0002\t\u000f\u0005}\b\u0001\"\u0001\u0003\u0010!9\u0011q \u0001\u0005\u0002\te\u0001bBA\u0000\u0001\u0011\u0005!1\u0005\u0005\n\u0005o\u0001\u0011\u0013!C\u0001\u0005sA\u0011B!\u0010\u0001#\u0003%\t!a\u0012\t\u0013\t}\u0002!%A\u0005\u0002\t\u0005\u0003bBA\u0000\u0001\u0011\u0005!Q\t\u0005\b\u0005'\u0002A\u0011\u0001B+\u0011\u001d\u0011\u0019\t\u0001C\u0001\u0005\u000bCqAa!\u0001\t\u0003\u0011i\u000bC\u0004\u0003\u0004\u0002!\tA!3\t\u000f\t\r\u0005\u0001\"\u0001\u0003f\"9!1\u0011\u0001\u0005\u0002\rM\u0001b\u0002BB\u0001\u0011\u00051q\b\u0005\b\u0005\u0007\u0003A\u0011AB4\u0011%\u0019)\nAI\u0001\n\u0003\u00199\nC\u0004\u0004&\u0002!\taa*\t\u000f\r\r\u0007\u0001\"\u0001\u0004F\"91Q\u001d\u0001\u0005\u0002\r\u001d\bbBBs\u0001\u0011\u0005A\u0011\u0002\u0005\b\u0007K\u0004A\u0011\u0001C\u0015\u0011\u001d!I\u0005\u0001C\u0001\t\u0017Bq\u0001\"\u0013\u0001\t\u0003!9\u0007C\u0004\u0005J\u0001!\t\u0001\"\"\t\u000f\u0011\r\u0006\u0001\"\u0001\u0005&\"9A1\u0015\u0001\u0005\u0002\u0011\r\u0007b\u0002CR\u0001\u0011\u0005A1\u001d\u0005\b\u000b\u0007\u0001A\u0011AC\u0003\u0011\u001d)\u0019\u0001\u0001C\u0001\u000bGAq!b\u0001\u0001\t\u0003)\t\u0005C\u0004\u0006`\u0001!\t!\"\u0019\t\u000f\u0015}\u0003\u0001\"\u0001\u0006\u0000!9Qq\f\u0001\u0005\u0002\u0015}\u0005bBC`\u0001\u0011\u0005Q\u0011\u0019\u0005\b\u000b\u007f\u0003A\u0011\u0001D\u0002\u0011%1)\u0006AI\u0001\n\u000319\u0006C\u0004\u0007\\\u0001!\tA\"\u0018\t\u000f\u0019m\u0003\u0001\"\u0001\u0007|!IaQ\u0019\u0001\u0012\u0002\u0013\u0005aq\u0019\u0005\b\r\u0017\u0001A\u0011\u0002Df\u0011\u001d1i\u0002\u0001C\u0005\r/\u0014A\u0003U1je\u0012\u001bFO]3b[\u001a+hn\u0019;j_:\u001c(BA\"E\u0003\u001d!7\u000f\u001e:fC6T!!\u0012$\u0002\u0013M$(/Z1nS:<'BA$I\u0003\u0015\u0019\b/\u0019:l\u0015\tI%*\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0006\u0019qN]4\u0004\u0001U\u0019aj[;\u0014\u0007\u0001yU\u000b\u0005\u0002Q'6\t\u0011KC\u0001S\u0003\u0015\u00198-\u00197b\u0013\t!\u0016K\u0001\u0004B]f\u0014VM\u001a\t\u0003-zs!a\u0016/\u000f\u0005a[V\"A-\u000b\u0005ic\u0015A\u0002\u001fs_>$h(C\u0001S\u0013\ti\u0016+A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0003'\u0001D*fe&\fG.\u001b>bE2,'BA/R\u0003\u0011\u0019X\r\u001c4\u0011\u0007\r$g-D\u0001C\u0013\t)'IA\u0004E'R\u0014X-Y7\u0011\tA;\u0017\u000e^\u0005\u0003QF\u0013a\u0001V;qY\u0016\u0014\u0004C\u00016l\u0019\u0001!Q\u0001\u001c\u0001C\u00025\u0014\u0011aS\t\u0003]F\u0004\"\u0001U8\n\u0005A\f&a\u0002(pi\"Lgn\u001a\t\u0003!JL!a])\u0003\u0007\u0005s\u0017\u0010\u0005\u0002kk\u0012)a\u000f\u0001b\u0001[\n\ta+\u0001\u0002liB\u0019\u0011\u0010`5\u000e\u0003iT!a_)\u0002\u000fI,g\r\\3di&\u0011QP\u001f\u0002\t\u00072\f7o\u001d+bO\u0006\u0011a\u000f\u001e\t\u0004sr$\u0018aA8sIB!a+!\u0002j\u0013\r\t9\u0001\u0019\u0002\t\u001fJ$WM]5oO\u00061A(\u001b8jiz\"B!!\u0004\u0002\u0018QA\u0011qBA\t\u0003'\t)\u0002\u0005\u0003d\u0001%$\b\"B<\u0006\u0001\bA\b\"\u0002@\u0006\u0001\by\bbBA\u0001\u000b\u0001\u000f\u00111\u0001\u0005\u0006C\u0016\u0001\rAY\u0001\u0004gN\u001cWCAA\u000f!\u0011\ty\"!\t\u000e\u0003\u0011K1!a\tE\u0005A\u0019FO]3b[&twmQ8oi\u0016DH/\u0001\u0007ta\u0006\u00148nQ8oi\u0016DH/\u0006\u0002\u0002*A!\u00111FA\u0017\u001b\u00051\u0015bAA\u0018\r\na1\u000b]1sW\u000e{g\u000e^3yi\u0006\u0011B-\u001a4bk2$\b+\u0019:uSRLwN\\3s)\u0011\t)$a\u000f\u0011\t\u0005-\u0012qG\u0005\u0004\u0003s1%a\u0004%bg\"\u0004\u0016M\u001d;ji&|g.\u001a:\t\u0013\u0005u\u0002\u0002%AA\u0002\u0005}\u0012!\u00048v[B\u000b'\u000f^5uS>t7\u000fE\u0002Q\u0003\u0003J1!a\u0011R\u0005\rIe\u000e^\u0001\u001dI\u00164\u0017-\u001e7u!\u0006\u0014H/\u001b;j_:,'\u000f\n3fM\u0006,H\u000e\u001e\u00132+\t\tIE\u000b\u0003\u0002@\u0005-3FAA'!\u0011\ty%!\u0017\u000e\u0005\u0005E#\u0002BA*\u0003+\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005]\u0013+\u0001\u0006b]:|G/\u0019;j_:LA!a\u0017\u0002R\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0015\u001d\u0014x.\u001e9Cs.+\u0017\u0010\u0006\u0002\u0002bA!1\rZA2!\u0015\u0001v-[A3!\u00111\u0016q\r;\n\u0007\u0005%\u0004M\u0001\u0005Ji\u0016\u0014\u0018M\u00197f)\u0011\t\t'!\u001c\t\u000f\u0005u2\u00021\u0001\u0002@Q!\u0011\u0011MA9\u0011\u001d\t\u0019\b\u0004a\u0001\u0003k\n1\u0002]1si&$\u0018n\u001c8feB!\u00111FA<\u0013\r\tIH\u0012\u0002\f!\u0006\u0014H/\u001b;j_:,'/A\u0006sK\u0012,8-\u001a\"z\u0017\u0016LHc\u00012\u0002\u0000!9\u0011\u0011Q\u0007A\u0002\u0005\r\u0015A\u0003:fIV\u001cWMR;oGB1\u0001+!\"uiRL1!a\"R\u0005%1UO\\2uS>t'\u0007F\u0003c\u0003\u0017\u000bi\tC\u0004\u0002\u0002:\u0001\r!a!\t\u000f\u0005ub\u00021\u0001\u0002@Q)!-!%\u0002\u0014\"9\u0011\u0011Q\bA\u0002\u0005\r\u0005bBA:\u001f\u0001\u0007\u0011QO\u0001\rG>l'-\u001b8f\u0005f\\U-_\u000b\u0005\u00033\u000b\u0019\u000b\u0006\u0007\u0002\u001c\u00065\u0016qWA_\u0003\u0007\f)\r\u0006\u0003\u0002\u001e\u0006\u001d\u0006\u0003B2e\u0003?\u0003R\u0001U4j\u0003C\u00032A[AR\t\u0019\t)\u000b\u0005b\u0001[\n\t1\tC\u0005\u0002*B\t\t\u0011q\u0001\u0002,\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\ted\u0018\u0011\u0015\u0005\b\u0003_\u0003\u0002\u0019AAY\u00039\u0019'/Z1uK\u000e{WNY5oKJ\u0004b\u0001UAZi\u0006\u0005\u0016bAA[#\nIa)\u001e8di&|g.\r\u0005\b\u0003s\u0003\u0002\u0019AA^\u0003)iWM]4f-\u0006dW/\u001a\t\t!\u0006\u0015\u0015\u0011\u0015;\u0002\"\"9\u0011q\u0018\tA\u0002\u0005\u0005\u0017!D7fe\u001e,7i\\7cS:,'\u000fE\u0005Q\u0003\u000b\u000b\t+!)\u0002\"\"9\u00111\u000f\tA\u0002\u0005U\u0004\"CAd!A\u0005\t\u0019AAe\u00039i\u0017\r]*jI\u0016\u001cu.\u001c2j]\u0016\u00042\u0001UAf\u0013\r\ti-\u0015\u0002\b\u0005>|G.Z1o\u0003Y\u0019w.\u001c2j]\u0016\u0014\u0015pS3zI\u0011,g-Y;mi\u0012*T\u0003BAj\u0003/,\"!!6+\t\u0005%\u00171\n\u0003\u0007\u0003K\u000b\"\u0019A7\u0002'\u001d\u0014x.\u001e9Cs.+\u00170\u00118e/&tGm\\<\u0015\t\u0005\u0005\u0014Q\u001c\u0005\b\u0003?\u0014\u0002\u0019AAq\u000399\u0018N\u001c3po\u0012+(/\u0019;j_:\u0004B!a\b\u0002d&\u0019\u0011Q\u001d#\u0003\u0011\u0011+(/\u0019;j_:$b!!\u0019\u0002j\u0006-\bbBAp'\u0001\u0007\u0011\u0011\u001d\u0005\b\u0003[\u001c\u0002\u0019AAq\u00035\u0019H.\u001b3f\tV\u0014\u0018\r^5p]RA\u0011\u0011MAy\u0003g\f)\u0010C\u0004\u0002`R\u0001\r!!9\t\u000f\u00055H\u00031\u0001\u0002b\"9\u0011Q\b\u000bA\u0002\u0005}B\u0003CA1\u0003s\fY0!@\t\u000f\u0005}W\u00031\u0001\u0002b\"9\u0011Q^\u000bA\u0002\u0005\u0005\bbBA:+\u0001\u0007\u0011QO\u0001\u0015e\u0016$WoY3Cs.+\u00170\u00118e/&tGm\\<\u0015\u000b\t\u0014\u0019A!\u0002\t\u000f\u0005\u0005e\u00031\u0001\u0002\u0004\"9\u0011q\u001c\fA\u0002\u0005\u0005Hc\u00022\u0003\n\t-!Q\u0002\u0005\b\u0003\u0003;\u0002\u0019AAB\u0011\u001d\tyn\u0006a\u0001\u0003CDq!!<\u0018\u0001\u0004\t\t\u000fF\u0005c\u0005#\u0011\u0019B!\u0006\u0003\u0018!9\u0011\u0011\u0011\rA\u0002\u0005\r\u0005bBAp1\u0001\u0007\u0011\u0011\u001d\u0005\b\u0003[D\u0002\u0019AAq\u0011\u001d\ti\u0004\u0007a\u0001\u0003\u007f!\u0012B\u0019B\u000e\u0005;\u0011yB!\t\t\u000f\u0005\u0005\u0015\u00041\u0001\u0002\u0004\"9\u0011q\\\rA\u0002\u0005\u0005\bbBAw3\u0001\u0007\u0011\u0011\u001d\u0005\b\u0003gJ\u0002\u0019AA;)5\u0011'Q\u0005B\u0014\u0005W\u0011iCa\f\u00032!9\u0011\u0011\u0011\u000eA\u0002\u0005\r\u0005b\u0002B\u00155\u0001\u0007\u00111Q\u0001\u000eS:4(+\u001a3vG\u00164UO\\2\t\u000f\u0005}'\u00041\u0001\u0002b\"I\u0011Q\u001e\u000e\u0011\u0002\u0003\u0007\u0011\u0011\u001d\u0005\n\u0003{Q\u0002\u0013!a\u0001\u0003\u007fA\u0011Ba\r\u001b!\u0003\u0005\rA!\u000e\u0002\u0015\u0019LG\u000e^3s\rVt7\r\u0005\u0004Q\u0003g3\u0017\u0011Z\u0001\u001fe\u0016$WoY3Cs.+\u00170\u00118e/&tGm\\<%I\u00164\u0017-\u001e7uIQ*\"Aa\u000f+\t\u0005\u0005\u00181J\u0001\u001fe\u0016$WoY3Cs.+\u00170\u00118e/&tGm\\<%I\u00164\u0017-\u001e7uIU\naD]3ek\u000e,')_&fs\u0006sGmV5oI><H\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\t\r#\u0006\u0002B\u001b\u0003\u0017\"RB\u0019B$\u0005\u0013\u0012YE!\u0014\u0003P\tE\u0003bBAA=\u0001\u0007\u00111\u0011\u0005\b\u0005Sq\u0002\u0019AAB\u0011\u001d\tyN\ba\u0001\u0003CDq!!<\u001f\u0001\u0004\t\t\u000fC\u0004\u0002ty\u0001\r!!\u001e\t\u000f\tMb\u00041\u0001\u00036\u0005aQ.\u00199XSRD7\u000b^1uKV1!q\u000bB2\u0005S\"BA!\u0017\u0003zQ1!1\fB7\u0005g\u0002\u0012b\u0019B/SR\u0014\tGa\u001a\n\u0007\t}#IA\nNCB<\u0016\u000e\u001e5Ti\u0006$X\rR*ue\u0016\fW\u000eE\u0002k\u0005G\"aA!\u001a \u0005\u0004i'!C*uCR,G+\u001f9f!\rQ'\u0011\u000e\u0003\u0007\u0005Wz\"\u0019A7\u0003\u00155\u000b\u0007\u000f]3e)f\u0004X\rC\u0005\u0003p}\t\t\u0011q\u0001\u0003r\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\ted(\u0011\r\u0005\n\u0005kz\u0012\u0011!a\u0002\u0005o\n!\"\u001a<jI\u0016t7-\u001a\u00134!\u0011IHPa\u001a\t\u000f\tmt\u00041\u0001\u0003~\u0005!1\u000f]3d!)\tyBa ji\n\u0005$qM\u0005\u0004\u0005\u0003#%!C*uCR,7\u000b]3d\u0003A)\b\u000fZ1uKN#\u0018\r^3Cs.+\u00170\u0006\u0003\u0003\b\nEE\u0003\u0002BE\u00057#BAa#\u0003\u0016B!1\r\u001aBG!\u0015\u0001v-\u001bBH!\rQ'\u0011\u0013\u0003\u0007\u0005'\u0003#\u0019A7\u0003\u0003MC\u0011Ba&!\u0003\u0003\u0005\u001dA!'\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0003zy\n=\u0005b\u0002BOA\u0001\u0007!qT\u0001\u000bkB$\u0017\r^3Gk:\u001c\u0007#\u0003)\u0002\u0006\n\u0005&q\u0015BT!\u00111&1\u0015;\n\u0007\t\u0015\u0006MA\u0002TKF\u0004R\u0001\u0015BU\u0005\u001fK1Aa+R\u0005\u0019y\u0005\u000f^5p]V!!q\u0016B])\u0019\u0011\tL!1\u0003HR!!1\u0017B^!\u0011\u0019GM!.\u0011\u000bA;\u0017Na.\u0011\u0007)\u0014I\f\u0002\u0004\u0003\u0014\u0006\u0012\r!\u001c\u0005\n\u0005{\u000b\u0013\u0011!a\u0002\u0005\u007f\u000b!\"\u001a<jI\u0016t7-\u001a\u00136!\u0011IHPa.\t\u000f\tu\u0015\u00051\u0001\u0003DBI\u0001+!\"\u0003\"\n\u0015'Q\u0019\t\u0006!\n%&q\u0017\u0005\b\u0003{\t\u0003\u0019AA +\u0011\u0011YM!6\u0015\r\t5'Q\u001cBr)\u0011\u0011yMa6\u0011\t\r$'\u0011\u001b\t\u0006!\u001eL'1\u001b\t\u0004U\nUGA\u0002BJE\t\u0007Q\u000eC\u0005\u0003Z\n\n\t\u0011q\u0001\u0003\\\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\ted(1\u001b\u0005\b\u0005;\u0013\u0003\u0019\u0001Bp!%\u0001\u0016Q\u0011BQ\u0005C\u0014\t\u000fE\u0003Q\u0005S\u0013\u0019\u000eC\u0004\u0002t\t\u0002\r!!\u001e\u0016\t\t\u001d(\u0011\u001f\u000b\t\u0005S\u0014Ip!\u0004\u0004\u0010Q!!1\u001eBz!\u0011\u0019GM!<\u0011\u000bA;\u0017Na<\u0011\u0007)\u0014\t\u0010\u0002\u0004\u0003\u0014\u000e\u0012\r!\u001c\u0005\n\u0005k\u001c\u0013\u0011!a\u0002\u0005o\f!\"\u001a<jI\u0016t7-\u001a\u00138!\u0011IHPa<\t\u000f\tu5\u00051\u0001\u0003|B9\u0001+a-\u0003~\u000e-\u0001#\u0002,\u0003\u0000\u000e\r\u0011bAB\u0001A\nA\u0011\n^3sCR|'\u000f\u0005\u0005Q\u0007\u000bI'\u0011UB\u0005\u0013\r\u00199!\u0015\u0002\u0007)V\u0004H.Z\u001a\u0011\u000bA\u0013IKa<\u0011\u000bY\u0013yP!<\t\u000f\u0005M4\u00051\u0001\u0002v!91\u0011C\u0012A\u0002\u0005%\u0017a\u0005:f[\u0016l'-\u001a:QCJ$\u0018\u000e^5p]\u0016\u0014X\u0003BB\u000b\u0007?!\u0002ba\u0006\u0004(\r52q\u0006\u000b\u0005\u00073\u0019\t\u0003\u0005\u0003dI\u000em\u0001#\u0002)hS\u000eu\u0001c\u00016\u0004 \u00111!1\u0013\u0013C\u00025D\u0011ba\t%\u0003\u0003\u0005\u001da!\n\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0003\b\u0005\u0003zy\u000eu\u0001b\u0002BOI\u0001\u00071\u0011\u0006\t\n!\u0006\u0015%\u0011UB\u0016\u0007W\u0001R\u0001\u0015BU\u0007;Aq!a\u001d%\u0001\u0004\t)\bC\u0004\u00042\u0011\u0002\raa\r\u0002\u0015%t\u0017\u000e^5bYJ#E\t\u0005\u0004\u00046\rm21D\u0007\u0003\u0007oQ1a!\u000fG\u0003\r\u0011H\rZ\u0005\u0005\u0007{\u00199DA\u0002S\t\u0012+Ba!\u0011\u0004LQQ11IB*\u0007?\u001a\tga\u0019\u0015\t\r\u00153Q\n\t\u0005G\u0012\u001c9\u0005E\u0003QO&\u001cI\u0005E\u0002k\u0007\u0017\"aAa%&\u0005\u0004i\u0007\"CB(K\u0005\u0005\t9AB)\u0003))g/\u001b3f]\u000e,G%\u000f\t\u0005sr\u001cI\u0005C\u0004\u0003\u001e\u0016\u0002\ra!\u0016\u0011\u000fA\u000b\u0019la\u0016\u0004^A)aKa@\u0004ZAA\u0001k!\u0002j\u0005C\u001bY\u0006E\u0003Q\u0005S\u001bI\u0005E\u0003W\u0005\u007f\u001c9\u0005C\u0004\u0002t\u0015\u0002\r!!\u001e\t\u000f\rEQ\u00051\u0001\u0002J\"91\u0011G\u0013A\u0002\r\u0015\u0004CBB\u001b\u0007w\u00199%\u0006\u0003\u0004j\rMDCCB6\u0007w\u001aYi!$\u0004\u0010R!1QNB;!\u0011\u0019Gma\u001c\u0011\u000bA;\u0017n!\u001d\u0011\u0007)\u001c\u0019\b\u0002\u0004\u0003\u0014\u001a\u0012\r!\u001c\u0005\n\u0007o2\u0013\u0011!a\u0002\u0007s\n1\"\u001a<jI\u0016t7-\u001a\u00132aA!\u0011\u0010`B9\u0011\u001d\u0011iJ\na\u0001\u0007{\u0002B\u0002UB@\u0007\u0007K'\u0011UBE\u0007\u0013K1a!!R\u0005%1UO\\2uS>tG\u0007\u0005\u0003\u0002 \r\u0015\u0015bABD\t\n!A+[7f!\u0015\u0001&\u0011VB9\u0011\u001d\t\u0019H\na\u0001\u0003kBqa!\u0005'\u0001\u0004\tI\rC\u0005\u00042\u0019\u0002\n\u00111\u0001\u0004\u0012B)\u0001K!+\u0004\u0014B11QGB\u001e\u0007_\n!$\u001e9eCR,7\u000b^1uK\nK8*Z=%I\u00164\u0017-\u001e7uIQ*Ba!'\u0004$V\u001111\u0014\u0016\u0005\u0007;\u000bYED\u0002Q\u0007?K1a!)R\u0003\u0011quN\\3\u0005\r\tMuE1\u0001n\u0003%i\u0017\r\u001d,bYV,7/\u0006\u0003\u0004*\u000eMF\u0003BBV\u0007{#Ba!,\u00048B!1\rZBX!\u0015\u0001v-[BY!\rQ71\u0017\u0003\u0007\u0007kC#\u0019A7\u0003\u0003UC\u0011b!/)\u0003\u0003\u0005\u001daa/\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0005sr\u001c\t\fC\u0004\u0004@\"\u0002\ra!1\u0002\u001b5\f\u0007OV1mk\u0016\u001ch)\u001e8d!\u0019\u0001\u00161\u0017;\u00042\u0006ia\r\\1u\u001b\u0006\u0004h+\u00197vKN,Baa2\u0004RR!1\u0011ZBm)\u0011\u0019Yma5\u0011\t\r$7Q\u001a\t\u0006!\u001eL7q\u001a\t\u0004U\u000eEGABB[S\t\u0007Q\u000eC\u0005\u0004V&\n\t\u0011q\u0001\u0004X\u0006YQM^5eK:\u001cW\rJ\u00193!\u0011IHpa4\t\u000f\rm\u0017\u00061\u0001\u0004^\u0006\tb\r\\1u\u001b\u0006\u0004h+\u00197vKN4UO\\2\u0011\rA\u000b\u0019\f^Bp!\u001516\u0011]Bh\u0013\r\u0019\u0019\u000f\u0019\u0002\r\u0013R,'/\u00192mK>s7-Z\u0001\bG><'o\\;q+\u0011\u0019Ioa>\u0015\t\r-H\u0011\u0001\u000b\u0005\u0007[\u001cY\u0010\u0005\u0003dI\u000e=\b#\u0002)hS\u000eE\bC\u0002)h\u0003K\u001a\u0019\u0010E\u0003W\u0003O\u001a)\u0010E\u0002k\u0007o$aa!?+\u0005\u0004i'!A,\t\u0013\ru(&!AA\u0004\r}\u0018aC3wS\u0012,gnY3%cM\u0002B!\u001f?\u0004v\"9A1\u0001\u0016A\u0002\u0011\u0015\u0011!B8uQ\u0016\u0014\b\u0003B2e\t\u000f\u0001R\u0001U4j\u0007k,B\u0001b\u0003\u0005\u001aQ1AQ\u0002C\u0011\tO!B\u0001b\u0004\u0005\u001cA!1\r\u001aC\t!\u0015\u0001v-\u001bC\n!\u0019\u0001v-!\u001a\u0005\u0016A)a+a\u001a\u0005\u0018A\u0019!\u000e\"\u0007\u0005\r\re8F1\u0001n\u0011%!ibKA\u0001\u0002\b!y\"A\u0006fm&$WM\\2fIE\"\u0004\u0003B=}\t/Aq\u0001b\u0001,\u0001\u0004!\u0019\u0003\u0005\u0003dI\u0012\u0015\u0002#\u0002)hS\u0012]\u0001bBA\u001fW\u0001\u0007\u0011qH\u000b\u0005\tW!I\u0004\u0006\u0004\u0005.\u0011\u0005Cq\t\u000b\u0005\t_!Y\u0004\u0005\u0003dI\u0012E\u0002#\u0002)hS\u0012M\u0002C\u0002)h\u0003K\")\u0004E\u0003W\u0003O\"9\u0004E\u0002k\ts!aa!?-\u0005\u0004i\u0007\"\u0003C\u001fY\u0005\u0005\t9\u0001C \u0003-)g/\u001b3f]\u000e,G%M\u001b\u0011\tedHq\u0007\u0005\b\t\u0007a\u0003\u0019\u0001C\"!\u0011\u0019G\r\"\u0012\u0011\u000bA;\u0017\u000eb\u000e\t\u000f\u0005MD\u00061\u0001\u0002v\u0005!!n\\5o+\u0011!i\u0005\"\u0017\u0015\t\u0011=C\u0011\r\u000b\u0005\t#\"Y\u0006\u0005\u0003dI\u0012M\u0003#\u0002)hS\u0012U\u0003#\u0002)hi\u0012]\u0003c\u00016\u0005Z\u001111\u0011`\u0017C\u00025D\u0011\u0002\"\u0018.\u0003\u0003\u0005\u001d\u0001b\u0018\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013G\u000e\t\u0005sr$9\u0006C\u0004\u0005\u00045\u0002\r\u0001b\u0019\u0011\t\r$GQ\r\t\u0006!\u001eLGqK\u000b\u0005\tS\")\b\u0006\u0004\u0005l\u0011uD1\u0011\u000b\u0005\t[\"9\b\u0005\u0003dI\u0012=\u0004#\u0002)hS\u0012E\u0004#\u0002)hi\u0012M\u0004c\u00016\u0005v\u001111\u0011 \u0018C\u00025D\u0011\u0002\"\u001f/\u0003\u0003\u0005\u001d\u0001b\u001f\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013g\u000e\t\u0005sr$\u0019\bC\u0004\u0005\u00049\u0002\r\u0001b \u0011\t\r$G\u0011\u0011\t\u0006!\u001eLG1\u000f\u0005\b\u0003{q\u0003\u0019AA +\u0011!9\tb%\u0015\r\u0011%E1\u0014CQ)\u0011!Y\t\"&\u0011\t\r$GQ\u0012\t\u0006!\u001eLGq\u0012\t\u0006!\u001e$H\u0011\u0013\t\u0004U\u0012MEABB}_\t\u0007Q\u000eC\u0005\u0005\u0018>\n\t\u0011q\u0001\u0005\u001a\u0006YQM^5eK:\u001cW\rJ\u00199!\u0011IH\u0010\"%\t\u000f\u0011\rq\u00061\u0001\u0005\u001eB!1\r\u001aCP!\u0015\u0001v-\u001bCI\u0011\u001d\t\u0019h\fa\u0001\u0003k\nQ\u0002\\3gi>+H/\u001a:K_&tW\u0003\u0002CT\tk#B\u0001\"+\u0005>R!A1\u0016C\\!\u0011\u0019G\r\",\u0011\u000bA;\u0017\u000eb,\u0011\u000bA;G\u000f\"-\u0011\u000bA\u0013I\u000bb-\u0011\u0007)$)\f\u0002\u0004\u0004zB\u0012\r!\u001c\u0005\n\ts\u0003\u0014\u0011!a\u0002\tw\u000b1\"\u001a<jI\u0016t7-\u001a\u00132sA!\u0011\u0010 CZ\u0011\u001d!\u0019\u0001\ra\u0001\t\u007f\u0003Ba\u00193\u0005BB)\u0001kZ5\u00054V!AQ\u0019Cj)\u0019!9\rb7\u0005bR!A\u0011\u001aCk!\u0011\u0019G\rb3\u0011\u000bA;\u0017\u000e\"4\u0011\u000bA;G\u000fb4\u0011\u000bA\u0013I\u000b\"5\u0011\u0007)$\u0019\u000e\u0002\u0004\u0004zF\u0012\r!\u001c\u0005\n\t/\f\u0014\u0011!a\u0002\t3\f1\"\u001a<jI\u0016t7-\u001a\u00133aA!\u0011\u0010 Ci\u0011\u001d!\u0019!\ra\u0001\t;\u0004Ba\u00193\u0005`B)\u0001kZ5\u0005R\"9\u0011QH\u0019A\u0002\u0005}R\u0003\u0002Cs\tg$b\u0001b:\u0005|\u0016\u0005A\u0003\u0002Cu\tk\u0004Ba\u00193\u0005lB)\u0001kZ5\u0005nB)\u0001k\u001a;\u0005pB)\u0001K!+\u0005rB\u0019!\u000eb=\u0005\r\re(G1\u0001n\u0011%!9PMA\u0001\u0002\b!I0A\u0006fm&$WM\\2fII\n\u0004\u0003B=}\tcDq\u0001b\u00013\u0001\u0004!i\u0010\u0005\u0003dI\u0012}\b#\u0002)hS\u0012E\bbBA:e\u0001\u0007\u0011QO\u0001\u000fe&<\u0007\u000e^(vi\u0016\u0014(j\\5o+\u0011)9!\"\u0006\u0015\t\u0015%QQ\u0004\u000b\u0005\u000b\u0017)9\u0002\u0005\u0003dI\u00165\u0001#\u0002)hS\u0016=\u0001C\u0002)h\u000b#)\u0019\u0002\u0005\u0003Q\u0005S#\bc\u00016\u0006\u0016\u001111\u0011`\u001aC\u00025D\u0011\"\"\u00074\u0003\u0003\u0005\u001d!b\u0007\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#G\r\t\u0005sr,\u0019\u0002C\u0004\u0005\u0004M\u0002\r!b\b\u0011\t\r$W\u0011\u0005\t\u0006!\u001eLW1C\u000b\u0005\u000bK)\t\u0004\u0006\u0004\u0006(\u0015eRq\b\u000b\u0005\u000bS)\u0019\u0004\u0005\u0003dI\u0016-\u0002#\u0002)hS\u00165\u0002C\u0002)h\u000b#)y\u0003E\u0002k\u000bc!aa!?5\u0005\u0004i\u0007\"CC\u001bi\u0005\u0005\t9AC\u001c\u0003-)g/\u001b3f]\u000e,GEM\u001a\u0011\tedXq\u0006\u0005\b\t\u0007!\u0004\u0019AC\u001e!\u0011\u0019G-\"\u0010\u0011\u000bA;\u0017.b\f\t\u000f\u0005uB\u00071\u0001\u0002@U!Q1IC()\u0019))%b\u0016\u0006^Q!QqIC)!\u0011\u0019G-\"\u0013\u0011\u000bA;\u0017.b\u0013\u0011\rA;W\u0011CC'!\rQWq\n\u0003\u0007\u0007s,$\u0019A7\t\u0013\u0015MS'!AA\u0004\u0015U\u0013aC3wS\u0012,gnY3%eQ\u0002B!\u001f?\u0006N!9A1A\u001bA\u0002\u0015e\u0003\u0003B2e\u000b7\u0002R\u0001U4j\u000b\u001bBq!a\u001d6\u0001\u0004\t)(A\u0007gk2dw*\u001e;fe*{\u0017N\\\u000b\u0005\u000bG*\t\b\u0006\u0003\u0006f\u0015eD\u0003BC4\u000bg\u0002Ba\u00193\u0006jA)\u0001kZ5\u0006lA1\u0001kZC\t\u000b[\u0002R\u0001\u0015BU\u000b_\u00022A[C9\t\u0019\u0019IP\u000eb\u0001[\"IQQ\u000f\u001c\u0002\u0002\u0003\u000fQqO\u0001\fKZLG-\u001a8dK\u0012\u0012T\u0007\u0005\u0003zy\u0016=\u0004b\u0002C\u0002m\u0001\u0007Q1\u0010\t\u0005G\u0012,i\bE\u0003QO&,y'\u0006\u0003\u0006\u0002\u0016=ECBCB\u000b/+i\n\u0006\u0003\u0006\u0006\u0016E\u0005\u0003B2e\u000b\u000f\u0003R\u0001U4j\u000b\u0013\u0003b\u0001U4\u0006\u0012\u0015-\u0005#\u0002)\u0003*\u00165\u0005c\u00016\u0006\u0010\u001211\u0011`\u001cC\u00025D\u0011\"b%8\u0003\u0003\u0005\u001d!\"&\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$#G\u000e\t\u0005sr,i\tC\u0004\u0005\u0004]\u0002\r!\"'\u0011\t\r$W1\u0014\t\u0006!\u001eLWQ\u0012\u0005\b\u0003{9\u0004\u0019AA +\u0011)\t+b,\u0015\r\u0015\rVqWC_)\u0011))+\"-\u0011\t\r$Wq\u0015\t\u0006!\u001eLW\u0011\u0016\t\u0007!\u001e,\t\"b+\u0011\u000bA\u0013I+\",\u0011\u0007),y\u000b\u0002\u0004\u0004zb\u0012\r!\u001c\u0005\n\u000bgC\u0014\u0011!a\u0002\u000bk\u000b1\"\u001a<jI\u0016t7-\u001a\u00133oA!\u0011\u0010`CW\u0011\u001d!\u0019\u0001\u000fa\u0001\u000bs\u0003Ba\u00193\u0006<B)\u0001kZ5\u0006.\"9\u00111\u000f\u001dA\u0002\u0005U\u0014!E:bm\u0016\f5\u000fS1e_>\u0004h)\u001b7fgV!Q1YCk)\u0019))-b;\u0006\u0000R!QqYCg!\r\u0001V\u0011Z\u0005\u0004\u000b\u0017\f&\u0001B+oSRDq!b4:\u0001\b)\t.\u0001\u0002g[B!\u0011\u0010`Cj!\rQWQ\u001b\u0003\b\u000b/L$\u0019ACm\u0005\u00051\u0015c\u00018\u0006\\B1QQ\\CtSRl!!b8\u000b\t\u0015\u0005X1]\u0001\u0007[\u0006\u0004(/\u001a3\u000b\u0007\u0015\u0015\b*\u0001\u0004iC\u0012|w\u000e]\u0005\u0005\u000bS,yN\u0001\u0007PkR\u0004X\u000f\u001e$pe6\fG\u000fC\u0004\u0006nf\u0002\r!b<\u0002\rA\u0014XMZ5y!\u0011)\t0\"?\u000f\t\u0015MXQ\u001f\t\u00031FK1!b>R\u0003\u0019\u0001&/\u001a3fM&!Q1`C\u007f\u0005\u0019\u0019FO]5oO*\u0019Qq_)\t\u000f\u0019\u0005\u0011\b1\u0001\u0006p\u000611/\u001e4gSb$b\"b2\u0007\u0006\u0019\u001da\u0011\u0002D\u000e\rS1Y\u0005C\u0004\u0006nj\u0002\r!b<\t\u000f\u0019\u0005!\b1\u0001\u0006p\"9a1\u0002\u001eA\u0002\u00195\u0011\u0001C6fs\u000ec\u0017m]:1\t\u0019=aq\u0003\t\u0007\u000bc4\tB\"\u0006\n\t\u0019MQQ \u0002\u0006\u00072\f7o\u001d\t\u0004U\u001a]Aa\u0003D\r\r\u0013\t\t\u0011!A\u0003\u00025\u00141a\u0018\u00132\u0011\u001d1iB\u000fa\u0001\r?\t!B^1mk\u0016\u001cE.Y:ta\u00111\tC\"\n\u0011\r\u0015Eh\u0011\u0003D\u0012!\rQgQ\u0005\u0003\f\rO1Y\"!A\u0001\u0002\u000b\u0005QNA\u0002`IIBqAb\u000b;\u0001\u00041i#A\tpkR\u0004X\u000f\u001e$pe6\fGo\u00117bgN\u0004DAb\f\u00074A1Q\u0011\u001fD\t\rc\u00012A\u001bD\u001a\t11)D\"\u000b\u0002\u0002\u0003\u0005)\u0011\u0001D\u001c\u0005\ryFeM\t\u0004]\u001ae\u0002G\u0002D\u001e\r\u007f19\u0005\u0005\u0005\u0006^\u0016\u001dhQ\bD#!\rQgq\b\u0003\f\r\u00032\u0019%!A\u0001\u0002\u000b\u0005QNA\u0002`IQ\"AB\"\u000e\u0007*\u0005\u0005\u0019\u0011!B\u0001\ro\u00012A\u001bD$\t-1IEb\u0011\u0002\u0002\u0003\u0005)\u0011A7\u0003\u0007}#S\u0007C\u0005\u0007Ni\u0002\n\u00111\u0001\u0007P\u0005!1m\u001c8g!\u0011)iN\"\u0015\n\t\u0019MSq\u001c\u0002\b\u0015>\u00147i\u001c8g\u0003m\u0019\u0018M^3Bg\"\u000bGm\\8q\r&dWm\u001d\u0013eK\u001a\fW\u000f\u001c;%mU\u0011a\u0011\f\u0016\u0005\r\u001f\nY%A\ftCZ,\u0017i\u001d(fo\u0006\u0003\u0016\nS1e_>\u0004h)\u001b7fgV!aq\fD5)\u00191\tGb\u001e\u0007zQ!Qq\u0019D2\u0011\u001d)y\r\u0010a\u0002\rK\u0002B!\u001f?\u0007hA\u0019!N\"\u001b\u0005\u000f\u0015]GH1\u0001\u0007lE\u0019aN\"\u001c\u0011\r\u0019=dQO5u\u001b\t1\tH\u0003\u0003\u0007t\u0015\r\u0018!C7baJ,G-^2f\u0013\u0011)IO\"\u001d\t\u000f\u00155H\b1\u0001\u0006p\"9a\u0011\u0001\u001fA\u0002\u0015=HCDCd\r{2yH\"!\u0007\u000e\u001aee\u0011\u0018\u0005\b\u000b[l\u0004\u0019ACx\u0011\u001d1\t!\u0010a\u0001\u000b_DqAb\u0003>\u0001\u00041\u0019\t\r\u0003\u0007\u0006\u001a%\u0005CBCy\r#19\tE\u0002k\r\u0013#1Bb#\u0007\u0002\u0006\u0005\t\u0011!B\u0001[\n\u0019q\f\n\u001c\t\u000f\u0019uQ\b1\u0001\u0007\u0010B\"a\u0011\u0013DK!\u0019)\tP\"\u0005\u0007\u0014B\u0019!N\"&\u0005\u0017\u0019]eQRA\u0001\u0002\u0003\u0015\t!\u001c\u0002\u0004?\u0012:\u0004b\u0002D\u0016{\u0001\u0007a1\u0014\u0019\u0005\r;3\t\u000b\u0005\u0004\u0006r\u001aEaq\u0014\t\u0004U\u001a\u0005F\u0001\u0004DR\r3\u000b\t\u0011!A\u0003\u0002\u0019\u0015&aA0%qE\u0019aNb*1\r\u0019%fQ\u0016D[!!1yG\"\u001e\u0007,\u001aM\u0006c\u00016\u0007.\u0012Yaq\u0016DY\u0003\u0003\u0005\tQ!\u0001n\u0005\ryF%\u000f\u0003\r\rG3I*!A\u0002\u0002\u000b\u0005aQ\u0015\t\u0004U\u001aUFa\u0003D\\\rc\u000b\t\u0011!A\u0003\u00025\u0014Aa\u0018\u00132a!IaQJ\u001f\u0011\u0002\u0003\u0007a1\u0018\t\u0005\r{3\t-\u0004\u0002\u0007@*!aQJCr\u0013\u00111\u0019Mb0\u0003\u001b\r{gNZ5hkJ\fG/[8o\u0003\u0005\u001a\u0018M^3Bg:+w/\u0011)J\u0011\u0006$wn\u001c9GS2,7\u000f\n3fM\u0006,H\u000e\u001e\u00137+\t1IM\u000b\u0003\u0007<\u0006-SC\u0001Dga\u00111yMb5\u0011\r\u0015Eh\u0011\u0003Di!\rQg1\u001b\u0003\u000b\r+|\u0014\u0011!A\u0001\u0006\u0003i'\u0001B0%cE*\"A\"71\t\u0019mgq\u001c\t\u0007\u000bc4\tB\"8\u0011\u0007)4y\u000e\u0002\u0006\u0007b\u0002\u000b\t\u0011!A\u0003\u00025\u0014Aa\u0018\u00132e\u0001"
)
public class PairDStreamFunctions implements Serializable {
   private final DStream self;
   private final ClassTag kt;
   private final ClassTag vt;
   private final Ordering ord;

   public StreamingContext ssc() {
      return this.self.ssc();
   }

   public SparkContext sparkContext() {
      return this.self.context().sparkContext();
   }

   public HashPartitioner defaultPartitioner(final int numPartitions) {
      return new HashPartitioner(numPartitions);
   }

   public int defaultPartitioner$default$1() {
      return this.self.ssc().sc().defaultParallelism();
   }

   public DStream groupByKey() {
      return (DStream)this.ssc().withScope(() -> this.groupByKey(this.defaultPartitioner(this.defaultPartitioner$default$1())));
   }

   public DStream groupByKey(final int numPartitions) {
      return (DStream)this.ssc().withScope(() -> this.groupByKey(this.defaultPartitioner(numPartitions)));
   }

   public DStream groupByKey(final Partitioner partitioner) {
      return (DStream)this.ssc().withScope(() -> {
         Function1 createCombiner = (v) -> (ArrayBuffer).MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{v}));
         Function2 mergeValue = (c, v) -> (ArrayBuffer)c.$plus$eq(v);
         Function2 mergeCombiner = (c1, c2) -> (ArrayBuffer)c1.$plus$plus(c2);
         return this.combineByKey(createCombiner, mergeValue, mergeCombiner, partitioner, this.combineByKey$default$5(), scala.reflect.ClassTag..MODULE$.apply(ArrayBuffer.class));
      });
   }

   public DStream reduceByKey(final Function2 reduceFunc) {
      return (DStream)this.ssc().withScope(() -> this.reduceByKey(reduceFunc, this.defaultPartitioner(this.defaultPartitioner$default$1())));
   }

   public DStream reduceByKey(final Function2 reduceFunc, final int numPartitions) {
      return (DStream)this.ssc().withScope(() -> this.reduceByKey(reduceFunc, this.defaultPartitioner(numPartitions)));
   }

   public DStream reduceByKey(final Function2 reduceFunc, final Partitioner partitioner) {
      return (DStream)this.ssc().withScope(() -> this.combineByKey((v) -> v, reduceFunc, reduceFunc, partitioner, this.combineByKey$default$5(), this.vt));
   }

   public DStream combineByKey(final Function1 createCombiner, final Function2 mergeValue, final Function2 mergeCombiner, final Partitioner partitioner, final boolean mapSideCombine, final ClassTag evidence$1) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         Function1 cleanedCreateCombiner = (Function1)qual$1.clean(createCombiner, x$2);
         SparkContext qual$2 = this.sparkContext();
         boolean x$4 = qual$2.clean$default$2();
         Function2 cleanedMergeValue = (Function2)qual$2.clean(mergeValue, x$4);
         SparkContext qual$3 = this.sparkContext();
         boolean x$6 = qual$3.clean$default$2();
         Function2 cleanedMergeCombiner = (Function2)qual$3.clean(mergeCombiner, x$6);
         return new ShuffledDStream(this.self, cleanedCreateCombiner, cleanedMergeValue, cleanedMergeCombiner, partitioner, mapSideCombine, this.kt, this.vt, evidence$1);
      });
   }

   public boolean combineByKey$default$5() {
      return true;
   }

   public DStream groupByKeyAndWindow(final Duration windowDuration) {
      return (DStream)this.ssc().withScope(() -> this.groupByKeyAndWindow(windowDuration, this.self.slideDuration(), this.defaultPartitioner(this.defaultPartitioner$default$1())));
   }

   public DStream groupByKeyAndWindow(final Duration windowDuration, final Duration slideDuration) {
      return (DStream)this.ssc().withScope(() -> this.groupByKeyAndWindow(windowDuration, slideDuration, this.defaultPartitioner(this.defaultPartitioner$default$1())));
   }

   public DStream groupByKeyAndWindow(final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      return (DStream)this.ssc().withScope(() -> this.groupByKeyAndWindow(windowDuration, slideDuration, this.defaultPartitioner(numPartitions)));
   }

   public DStream groupByKeyAndWindow(final Duration windowDuration, final Duration slideDuration, final Partitioner partitioner) {
      return (DStream)this.ssc().withScope(() -> {
         Function1 createCombiner = (v) -> (ArrayBuffer)(new ArrayBuffer()).$plus$plus$eq(v);
         Function2 mergeValue = (buf, v) -> (ArrayBuffer)buf.$plus$plus$eq(v);
         Function2 mergeCombiner = (buf1, buf2) -> (ArrayBuffer)buf1.$plus$plus$eq(buf2);
         PairDStreamFunctions qual$1 = DStream$.MODULE$.toPairDStreamFunctions(DStream$.MODULE$.toPairDStreamFunctions(this.self, this.kt, this.vt, this.ord).groupByKey(partitioner).window(windowDuration, slideDuration), this.kt, scala.reflect.ClassTag..MODULE$.apply(Iterable.class), this.ord);
         boolean x$5 = qual$1.combineByKey$default$5();
         return qual$1.combineByKey(createCombiner, mergeValue, mergeCombiner, partitioner, x$5, scala.reflect.ClassTag..MODULE$.apply(ArrayBuffer.class));
      });
   }

   public DStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration) {
      return (DStream)this.ssc().withScope(() -> this.reduceByKeyAndWindow(reduceFunc, windowDuration, this.self.slideDuration(), this.defaultPartitioner(this.defaultPartitioner$default$1())));
   }

   public DStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration) {
      return (DStream)this.ssc().withScope(() -> this.reduceByKeyAndWindow(reduceFunc, windowDuration, slideDuration, this.defaultPartitioner(this.defaultPartitioner$default$1())));
   }

   public DStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration, final int numPartitions) {
      return (DStream)this.ssc().withScope(() -> this.reduceByKeyAndWindow(reduceFunc, windowDuration, slideDuration, this.defaultPartitioner(numPartitions)));
   }

   public DStream reduceByKeyAndWindow(final Function2 reduceFunc, final Duration windowDuration, final Duration slideDuration, final Partitioner partitioner) {
      return (DStream)this.ssc().withScope(() -> DStream$.MODULE$.toPairDStreamFunctions(DStream$.MODULE$.toPairDStreamFunctions(this.self, this.kt, this.vt, this.ord).reduceByKey(reduceFunc, partitioner).window(windowDuration, slideDuration), this.kt, this.vt, this.ord).reduceByKey(reduceFunc, partitioner));
   }

   public DStream reduceByKeyAndWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration, final int numPartitions, final Function1 filterFunc) {
      return (DStream)this.ssc().withScope(() -> this.reduceByKeyAndWindow(reduceFunc, invReduceFunc, windowDuration, slideDuration, this.defaultPartitioner(numPartitions), filterFunc));
   }

   public DStream reduceByKeyAndWindow(final Function2 reduceFunc, final Function2 invReduceFunc, final Duration windowDuration, final Duration slideDuration, final Partitioner partitioner, final Function1 filterFunc) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.ssc().sc();
         boolean x$2 = qual$1.clean$default$2();
         Function2 cleanedReduceFunc = (Function2)qual$1.clean(reduceFunc, x$2);
         SparkContext qual$2 = this.ssc().sc();
         boolean x$4 = qual$2.clean$default$2();
         Function2 cleanedInvReduceFunc = (Function2)qual$2.clean(invReduceFunc, x$4);
         Object var10000;
         if (filterFunc != null) {
            SparkContext qual$3 = this.ssc().sc();
            boolean x$6 = qual$3.clean$default$2();
            var10000 = new Some(qual$3.clean(filterFunc, x$6));
         } else {
            var10000 = scala.None..MODULE$;
         }

         Option cleanedFilterFunc = (Option)var10000;
         return new ReducedWindowedDStream(this.self, cleanedReduceFunc, cleanedInvReduceFunc, cleanedFilterFunc, windowDuration, slideDuration, partitioner, this.kt, this.vt);
      });
   }

   public Duration reduceByKeyAndWindow$default$4() {
      return this.self.slideDuration();
   }

   public int reduceByKeyAndWindow$default$5() {
      return this.ssc().sc().defaultParallelism();
   }

   public Function1 reduceByKeyAndWindow$default$6() {
      return null;
   }

   public MapWithStateDStream mapWithState(final StateSpec spec, final ClassTag evidence$2, final ClassTag evidence$3) {
      return new MapWithStateDStreamImpl(this.self, (StateSpecImpl)spec, this.kt, this.vt, evidence$2, evidence$3);
   }

   public DStream updateStateByKey(final Function2 updateFunc, final ClassTag evidence$4) {
      return (DStream)this.ssc().withScope(() -> this.updateStateByKey(updateFunc, this.defaultPartitioner(this.defaultPartitioner$default$1()), evidence$4));
   }

   public DStream updateStateByKey(final Function2 updateFunc, final int numPartitions, final ClassTag evidence$5) {
      return (DStream)this.ssc().withScope(() -> this.updateStateByKey(updateFunc, this.defaultPartitioner(numPartitions), evidence$5));
   }

   public DStream updateStateByKey(final Function2 updateFunc, final Partitioner partitioner, final ClassTag evidence$6) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         Function2 cleanedUpdateF = (Function2)qual$1.clean(updateFunc, x$2);
         Function1 newUpdateFunc = (iterator) -> iterator.flatMap((t) -> ((Option)cleanedUpdateF.apply(t._2(), t._3())).map((s) -> new Tuple2(t._1(), s)));
         return this.updateStateByKey(newUpdateFunc, partitioner, true, evidence$6);
      });
   }

   public DStream updateStateByKey(final Function1 updateFunc, final Partitioner partitioner, final boolean rememberPartitioner, final ClassTag evidence$7) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.ssc().sc();
         boolean x$2 = qual$1.clean$default$2();
         Function1 cleanedFunc = (Function1)qual$1.clean(updateFunc, x$2);
         Function2 newUpdateFunc = (x$1, it) -> (Iterator)cleanedFunc.apply(it);
         return new StateDStream(this.self, newUpdateFunc, partitioner, rememberPartitioner, scala.None..MODULE$, this.kt, this.vt, evidence$7);
      });
   }

   public DStream updateStateByKey(final Function2 updateFunc, final Partitioner partitioner, final RDD initialRDD, final ClassTag evidence$8) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         Function2 cleanedUpdateF = (Function2)qual$1.clean(updateFunc, x$2);
         Function1 newUpdateFunc = (iterator) -> iterator.flatMap((t) -> ((Option)cleanedUpdateF.apply(t._2(), t._3())).map((s) -> new Tuple2(t._1(), s)));
         return this.updateStateByKey(newUpdateFunc, partitioner, true, initialRDD, evidence$8);
      });
   }

   public DStream updateStateByKey(final Function1 updateFunc, final Partitioner partitioner, final boolean rememberPartitioner, final RDD initialRDD, final ClassTag evidence$9) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.ssc().sc();
         boolean x$2 = qual$1.clean$default$2();
         Function1 cleanedFunc = (Function1)qual$1.clean(updateFunc, x$2);
         Function2 newUpdateFunc = (x$2x, it) -> (Iterator)cleanedFunc.apply(it);
         return new StateDStream(this.self, newUpdateFunc, partitioner, rememberPartitioner, new Some(initialRDD), this.kt, this.vt, evidence$9);
      });
   }

   public DStream updateStateByKey(final Function4 updateFunc, final Partitioner partitioner, final boolean rememberPartitioner, final Option initialRDD, final ClassTag evidence$10) {
      return (DStream)this.ssc().withScope(() -> {
         SparkContext qual$1 = this.ssc().sc();
         boolean x$2 = qual$1.clean$default$2();
         Function4 cleanedFunc = (Function4)qual$1.clean(updateFunc, x$2);
         Function2 newUpdateFunc = (time, iterator) -> iterator.flatMap((t) -> ((Option)cleanedFunc.apply(time, t._1(), t._2(), t._3())).map((s) -> new Tuple2(t._1(), s)));
         return new StateDStream(this.self, newUpdateFunc, partitioner, rememberPartitioner, initialRDD, this.kt, this.vt, evidence$10);
      });
   }

   public None updateStateByKey$default$4() {
      return scala.None..MODULE$;
   }

   public DStream mapValues(final Function1 mapValuesFunc, final ClassTag evidence$11) {
      return (DStream)this.ssc().withScope(() -> {
         DStream var10002 = this.self;
         SparkContext qual$1 = this.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new MapValuedDStream(var10002, (Function1)qual$1.clean(mapValuesFunc, x$2), this.kt, this.vt, evidence$11);
      });
   }

   public DStream flatMapValues(final Function1 flatMapValuesFunc, final ClassTag evidence$12) {
      return (DStream)this.ssc().withScope(() -> {
         DStream var10002 = this.self;
         SparkContext qual$1 = this.sparkContext();
         boolean x$2 = qual$1.clean$default$2();
         return new FlatMapValuedDStream(var10002, (Function1)qual$1.clean(flatMapValuesFunc, x$2), this.kt, this.vt, evidence$12);
      });
   }

   public DStream cogroup(final DStream other, final ClassTag evidence$13) {
      return (DStream)this.ssc().withScope(() -> this.cogroup(other, this.defaultPartitioner(this.defaultPartitioner$default$1()), evidence$13));
   }

   public DStream cogroup(final DStream other, final int numPartitions, final ClassTag evidence$14) {
      return (DStream)this.ssc().withScope(() -> this.cogroup(other, this.defaultPartitioner(numPartitions), evidence$14));
   }

   public DStream cogroup(final DStream other, final Partitioner partitioner, final ClassTag evidence$15) {
      return (DStream)this.ssc().withScope(() -> this.self.transformWith(other, (Function2)((rdd1, rdd2) -> org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd1, this.kt, this.vt, this.ord).cogroup(rdd2, partitioner)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   public DStream join(final DStream other, final ClassTag evidence$16) {
      return (DStream)this.ssc().withScope(() -> this.join(other, this.defaultPartitioner(this.defaultPartitioner$default$1()), evidence$16));
   }

   public DStream join(final DStream other, final int numPartitions, final ClassTag evidence$17) {
      return (DStream)this.ssc().withScope(() -> this.join(other, this.defaultPartitioner(numPartitions), evidence$17));
   }

   public DStream join(final DStream other, final Partitioner partitioner, final ClassTag evidence$18) {
      return (DStream)this.ssc().withScope(() -> this.self.transformWith(other, (Function2)((rdd1, rdd2) -> org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd1, this.kt, this.vt, this.ord).join(rdd2, partitioner)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   public DStream leftOuterJoin(final DStream other, final ClassTag evidence$19) {
      return (DStream)this.ssc().withScope(() -> this.leftOuterJoin(other, this.defaultPartitioner(this.defaultPartitioner$default$1()), evidence$19));
   }

   public DStream leftOuterJoin(final DStream other, final int numPartitions, final ClassTag evidence$20) {
      return (DStream)this.ssc().withScope(() -> this.leftOuterJoin(other, this.defaultPartitioner(numPartitions), evidence$20));
   }

   public DStream leftOuterJoin(final DStream other, final Partitioner partitioner, final ClassTag evidence$21) {
      return (DStream)this.ssc().withScope(() -> this.self.transformWith(other, (Function2)((rdd1, rdd2) -> org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd1, this.kt, this.vt, this.ord).leftOuterJoin(rdd2, partitioner)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   public DStream rightOuterJoin(final DStream other, final ClassTag evidence$22) {
      return (DStream)this.ssc().withScope(() -> this.rightOuterJoin(other, this.defaultPartitioner(this.defaultPartitioner$default$1()), evidence$22));
   }

   public DStream rightOuterJoin(final DStream other, final int numPartitions, final ClassTag evidence$23) {
      return (DStream)this.ssc().withScope(() -> this.rightOuterJoin(other, this.defaultPartitioner(numPartitions), evidence$23));
   }

   public DStream rightOuterJoin(final DStream other, final Partitioner partitioner, final ClassTag evidence$24) {
      return (DStream)this.ssc().withScope(() -> this.self.transformWith(other, (Function2)((rdd1, rdd2) -> org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd1, this.kt, this.vt, this.ord).rightOuterJoin(rdd2, partitioner)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   public DStream fullOuterJoin(final DStream other, final ClassTag evidence$25) {
      return (DStream)this.ssc().withScope(() -> this.fullOuterJoin(other, this.defaultPartitioner(this.defaultPartitioner$default$1()), evidence$25));
   }

   public DStream fullOuterJoin(final DStream other, final int numPartitions, final ClassTag evidence$26) {
      return (DStream)this.ssc().withScope(() -> this.fullOuterJoin(other, this.defaultPartitioner(numPartitions), evidence$26));
   }

   public DStream fullOuterJoin(final DStream other, final Partitioner partitioner, final ClassTag evidence$27) {
      return (DStream)this.ssc().withScope(() -> this.self.transformWith(other, (Function2)((rdd1, rdd2) -> org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd1, this.kt, this.vt, this.ord).fullOuterJoin(rdd2, partitioner)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
   }

   public void saveAsHadoopFiles(final String prefix, final String suffix, final ClassTag fm) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> this.saveAsHadoopFiles(prefix, suffix, this.keyClass(), this.valueClass(), fm.runtimeClass(), this.saveAsHadoopFiles$default$6()));
   }

   public void saveAsHadoopFiles(final String prefix, final String suffix, final Class keyClass, final Class valueClass, final Class outputFormatClass, final JobConf conf) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> {
         SerializableJobConf serializableConf = new SerializableJobConf(conf);
         Function2 saveFunc = (rdd, time) -> {
            $anonfun$saveAsHadoopFiles$3(this, prefix, suffix, keyClass, valueClass, outputFormatClass, serializableConf, rdd, time);
            return BoxedUnit.UNIT;
         };
         this.self.foreachRDD(saveFunc);
      });
   }

   public JobConf saveAsHadoopFiles$default$6() {
      return new JobConf(this.ssc().sparkContext().hadoopConfiguration());
   }

   public void saveAsNewAPIHadoopFiles(final String prefix, final String suffix, final ClassTag fm) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> this.saveAsNewAPIHadoopFiles(prefix, suffix, this.keyClass(), this.valueClass(), fm.runtimeClass(), this.saveAsNewAPIHadoopFiles$default$6()));
   }

   public void saveAsNewAPIHadoopFiles(final String prefix, final String suffix, final Class keyClass, final Class valueClass, final Class outputFormatClass, final Configuration conf) {
      this.ssc().withScope((JFunction0.mcV.sp)() -> {
         SerializableConfiguration serializableConf = new SerializableConfiguration(conf);
         Function2 saveFunc = (rdd, time) -> {
            $anonfun$saveAsNewAPIHadoopFiles$3(this, prefix, suffix, keyClass, valueClass, outputFormatClass, serializableConf, rdd, time);
            return BoxedUnit.UNIT;
         };
         this.self.foreachRDD(saveFunc);
      });
   }

   public Configuration saveAsNewAPIHadoopFiles$default$6() {
      return this.ssc().sparkContext().hadoopConfiguration();
   }

   private Class keyClass() {
      return this.kt.runtimeClass();
   }

   private Class valueClass() {
      return this.vt.runtimeClass();
   }

   // $FF: synthetic method
   public static final void $anonfun$saveAsHadoopFiles$3(final PairDStreamFunctions $this, final String prefix$2, final String suffix$2, final Class keyClass$1, final Class valueClass$1, final Class outputFormatClass$1, final SerializableJobConf serializableConf$1, final RDD rdd, final Time time) {
      String file = StreamingContext$.MODULE$.rddToFileName(prefix$2, suffix$2, time);
      PairRDDFunctions qual$1 = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd, $this.kt, $this.vt, $this.ord);
      JobConf x$5 = new JobConf(serializableConf$1.value());
      Option x$6 = qual$1.saveAsHadoopFile$default$6();
      qual$1.saveAsHadoopFile(file, keyClass$1, valueClass$1, outputFormatClass$1, x$5, x$6);
   }

   // $FF: synthetic method
   public static final void $anonfun$saveAsNewAPIHadoopFiles$3(final PairDStreamFunctions $this, final String prefix$4, final String suffix$4, final Class keyClass$2, final Class valueClass$2, final Class outputFormatClass$2, final SerializableConfiguration serializableConf$2, final RDD rdd, final Time time) {
      String file = StreamingContext$.MODULE$.rddToFileName(prefix$4, suffix$4, time);
      org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(rdd, $this.kt, $this.vt, $this.ord).saveAsNewAPIHadoopFile(file, keyClass$2, valueClass$2, outputFormatClass$2, serializableConf$2.value());
   }

   public PairDStreamFunctions(final DStream self, final ClassTag kt, final ClassTag vt, final Ordering ord) {
      this.self = self;
      this.kt = kt;
      this.vt = vt;
      this.ord = ord;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
