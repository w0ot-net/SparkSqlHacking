package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.Random;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.ml.util.Instrumentation;
import org.apache.spark.mllib.linalg.BLAS$;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.util.MLUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple4;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Set;
import scala.collection.mutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011%a\u0001B2e\u0001=D\u0001\u0002 \u0001\u0003\u0002\u0004%I! \u0005\u000b\u0003\u0007\u0001!\u00111A\u0005\n\u0005\u0015\u0001\"CA\t\u0001\t\u0005\t\u0015)\u0003\u007f\u0011%\t\u0019\u0002\u0001BA\u0002\u0013%Q\u0010\u0003\u0006\u0002\u0016\u0001\u0011\t\u0019!C\u0005\u0003/A\u0011\"a\u0007\u0001\u0005\u0003\u0005\u000b\u0015\u0002@\t\u0015\u0005u\u0001A!a\u0001\n\u0013\ty\u0002\u0003\u0006\u0002(\u0001\u0011\t\u0019!C\u0005\u0003SA!\"!\f\u0001\u0005\u0003\u0005\u000b\u0015BA\u0011\u0011)\ty\u0003\u0001BA\u0002\u0013%\u0011\u0011\u0007\u0005\u000b\u0003s\u0001!\u00111A\u0005\n\u0005m\u0002BCA \u0001\t\u0005\t\u0015)\u0003\u00024!Q\u0011\u0011\t\u0001\u0003\u0002\u0004%I!a\u0011\t\u0015\u0005m\u0003A!a\u0001\n\u0013\ti\u0006\u0003\u0006\u0002b\u0001\u0011\t\u0011)Q\u0005\u0003\u000bBq!a\u0019\u0001\t\u0013\t)\u0007C\u0004\u0002d\u0001!\t!!\u001e\t\u000f\u0005%\u0005\u0001\"\u0001\u0002\f\"1\u00111\u0013\u0001\u0005\u0002uDq!a&\u0001\t\u0003\tI\n\u0003\u0004\u0002 \u0002!\t! \u0005\b\u0003G\u0003A\u0011AAS\u0011\u001d\tY\u000b\u0001C\u0001\u0003?Aq!a,\u0001\t\u0003\t\t\fC\u0004\u00028\u0002!\t!!\r\t\u000f\u0005m\u0006\u0001\"\u0001\u0002D!9\u00111\u0019\u0001\u0005\u0002\u0005\u0015\u0007\u0002CAf\u0001\u0011\u0005\u0001.!4\t\u000f\tm\u0001\u0001\"\u0001\u0003\u001e!9!1\u0004\u0001\u0005\u0002\t\u001dra\u0002B I\"%!\u0011\t\u0004\u0007G\u0012DIAa\u0011\t\u000f\u0005\r\u0004\u0005\"\u0001\u0003X!I!\u0011\f\u0011C\u0002\u0013%\u0011\u0011\u0007\u0005\t\u00057\u0002\u0003\u0015!\u0003\u00024!I!Q\f\u0011C\u0002\u0013%\u0011\u0011\u0007\u0005\t\u0005?\u0002\u0003\u0015!\u0003\u00024!I!\u0011\r\u0011C\u0002\u0013%\u0011q\u0004\u0005\t\u0005G\u0002\u0003\u0015!\u0003\u0002\"!9!Q\r\u0011\u0005\n\t\u001d\u0004b\u0002B7A\u0011%!q\u000e\u0005\b\u0005g\u0002C\u0011\u0002B;\u0011\u001d\u0011I\b\tC\u0005\u0005w2aaa\u0010!\t\r\u0005\u0003\"CB\u0017Y\t\u0015\r\u0011\"\u0001~\u0011%\u0019\u0019\u0005\fB\u0001B\u0003%a\u0010\u0003\u0006\u0002B1\u0012)\u0019!C\u0001\u0007\u000bB!\"!\u0019-\u0005\u0003\u0005\u000b\u0011BB\u001d\u0011\u001d\t\u0019\u0007\fC\u0001\u0007\u000fB\u0011ba\u0014-\u0001\u0004%I!!\r\t\u0013\rEC\u00061A\u0005\n\rM\u0003\u0002CB,Y\u0001\u0006K!a\r\t\u0013\tUE\u00061A\u0005\n\u0005}\u0001\"CB-Y\u0001\u0007I\u0011BB.\u0011!\u00119\n\fQ!\n\u0005\u0005\u0002\"CB0Y\t\u0007I\u0011BB1\u0011!\u0019\u0019\u0007\fQ\u0001\n\u0005-\b\"CB3Y\u0001\u0007I\u0011BA\u0010\u0011%\u00199\u0007\fa\u0001\n\u0013\u0019I\u0007\u0003\u0005\u0004n1\u0002\u000b\u0015BA\u0011\u0011\u001d\u0019y\u0007\fC\u0001\u0007cBqa!\u001f-\t\u0003\u0019Y\bC\u0004\u0004\u00022\"\taa!\t\u000f\r\u0015\u0005\u0005\"\u0003\u0004\b\"91Q\u0014\u0011\u0005\n\r}\u0005bBB[A\u0011%1q\u0017\u0004\u0007\u0005\u000f\u0003CI!#\t\u0015\tE5I!f\u0001\n\u0003\t\t\u0004\u0003\u0006\u0003\u0014\u000e\u0013\t\u0012)A\u0005\u0003gA!B!&D\u0005+\u0007I\u0011AA\u0010\u0011)\u00119j\u0011B\tB\u0003%\u0011\u0011\u0005\u0005\u000b\u00053\u001b%Q3A\u0005\u0002\tm\u0005B\u0003BR\u0007\nE\t\u0015!\u0003\u0003\u001e\"Q!QU\"\u0003\u0016\u0004%\t!a\b\t\u0015\t\u001d6I!E!\u0002\u0013\t\t\u0003C\u0004\u0002d\r#\tA!+\t\u0013\tM6)!A\u0005\u0002\tU\u0006\"\u0003B`\u0007F\u0005I\u0011\u0001Ba\u0011%\u0011)nQI\u0001\n\u0003\u00119\u000eC\u0005\u0003\\\u000e\u000b\n\u0011\"\u0001\u0003^\"I!\u0011]\"\u0012\u0002\u0013\u0005!q\u001b\u0005\n\u0005G\u001c\u0015\u0011!C!\u0005KD\u0001Ba=D\u0003\u0003%\t! \u0005\n\u0005k\u001c\u0015\u0011!C\u0001\u0005oD\u0011b!\u0001D\u0003\u0003%\tea\u0001\t\u0013\rE1)!A\u0005\u0002\rM\u0001\"CB\f\u0007\u0006\u0005I\u0011IB\r\u0011%\u0019ibQA\u0001\n\u0003\u001ay\u0002C\u0005\u0004\"\r\u000b\t\u0011\"\u0011\u0004$!I1QE\"\u0002\u0002\u0013\u00053qE\u0004\n\u0007\u000b\u0004\u0013\u0011!E\u0005\u0007\u000f4\u0011Ba\"!\u0003\u0003EIa!3\t\u000f\u0005\rD\f\"\u0001\u0004b\"I1\u0011\u0005/\u0002\u0002\u0013\u001531\u0005\u0005\n\u0007Gd\u0016\u0011!CA\u0007KD\u0011ba<]\u0003\u0003%\ti!=\t\u0013\r}H,!A\u0005\n\u0011\u0005\u0001\"CB\u0000A\u0005\u0005I\u0011\u0002C\u0001\u0005=\u0011\u0015n]3di&twmS'fC:\u001c(BA3g\u0003)\u0019G.^:uKJLgn\u001a\u0006\u0003O\"\fQ!\u001c7mS\nT!!\u001b6\u0002\u000bM\u0004\u0018M]6\u000b\u0005-d\u0017AB1qC\u000eDWMC\u0001n\u0003\ry'oZ\u0002\u0001'\r\u0001\u0001O\u001e\t\u0003cRl\u0011A\u001d\u0006\u0002g\u0006)1oY1mC&\u0011QO\u001d\u0002\u0007\u0003:L(+\u001a4\u0011\u0005]TX\"\u0001=\u000b\u0005eD\u0017\u0001C5oi\u0016\u0014h.\u00197\n\u0005mD(a\u0002'pO\u001eLgnZ\u0001\u0002WV\ta\u0010\u0005\u0002r\u007f&\u0019\u0011\u0011\u0001:\u0003\u0007%sG/A\u0003l?\u0012*\u0017\u000f\u0006\u0003\u0002\b\u00055\u0001cA9\u0002\n%\u0019\u00111\u0002:\u0003\tUs\u0017\u000e\u001e\u0005\t\u0003\u001f\u0011\u0011\u0011!a\u0001}\u0006\u0019\u0001\u0010J\u0019\u0002\u0005-\u0004\u0013!D7bq&#XM]1uS>t7/A\tnCbLE/\u001a:bi&|gn]0%KF$B!a\u0002\u0002\u001a!A\u0011qB\u0003\u0002\u0002\u0003\u0007a0\u0001\bnCbLE/\u001a:bi&|gn\u001d\u0011\u0002/5Lg\u000eR5wSNL'\r\\3DYV\u001cH/\u001a:TSj,WCAA\u0011!\r\t\u00181E\u0005\u0004\u0003K\u0011(A\u0002#pk\ndW-A\u000enS:$\u0015N^5tS\ndWm\u00117vgR,'oU5{K~#S-\u001d\u000b\u0005\u0003\u000f\tY\u0003C\u0005\u0002\u0010!\t\t\u00111\u0001\u0002\"\u0005AR.\u001b8ESZL7/\u001b2mK\u000ecWo\u001d;feNK'0\u001a\u0011\u0002\tM,W\rZ\u000b\u0003\u0003g\u00012!]A\u001b\u0013\r\t9D\u001d\u0002\u0005\u0019>tw-\u0001\u0005tK\u0016$w\fJ3r)\u0011\t9!!\u0010\t\u0013\u0005=1\"!AA\u0002\u0005M\u0012!B:fK\u0012\u0004\u0013a\u00043jgR\fgnY3NK\u0006\u001cXO]3\u0016\u0005\u0005\u0015\u0003\u0003BA$\u0003+rA!!\u0013\u0002RA\u0019\u00111\n:\u000e\u0005\u00055#bAA(]\u00061AH]8pizJ1!a\u0015s\u0003\u0019\u0001&/\u001a3fM&!\u0011qKA-\u0005\u0019\u0019FO]5oO*\u0019\u00111\u000b:\u0002'\u0011L7\u000f^1oG\u0016lU-Y:ve\u0016|F%Z9\u0015\t\u0005\u001d\u0011q\f\u0005\n\u0003\u001fq\u0011\u0011!a\u0001\u0003\u000b\n\u0001\u0003Z5ti\u0006t7-Z'fCN,(/\u001a\u0011\u0002\rqJg.\u001b;?)1\t9'a\u001b\u0002n\u0005=\u0014\u0011OA:!\r\tI\u0007A\u0007\u0002I\")A\u0010\u0005a\u0001}\"1\u00111\u0003\tA\u0002yDq!!\b\u0011\u0001\u0004\t\t\u0003C\u0004\u00020A\u0001\r!a\r\t\u000f\u0005\u0005\u0003\u00031\u0001\u0002FQ\u0011\u0011q\r\u0015\u0006#\u0005e\u0014Q\u0011\t\u0005\u0003w\n\t)\u0004\u0002\u0002~)\u0019\u0011q\u00105\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\u0004\u0006u$!B*j]\u000e,\u0017EAAD\u0003\u0015\tdF\u000e\u00181\u0003\u0011\u0019X\r^&\u0015\t\u00055\u0015qR\u0007\u0002\u0001!)AP\u0005a\u0001}\"*!#!\u001f\u0002\u0006\u0006!q-\u001a;LQ\u0015\u0019\u0012\u0011PAC\u0003A\u0019X\r^'bq&#XM]1uS>t7\u000f\u0006\u0003\u0002\u000e\u0006m\u0005BBA\n)\u0001\u0007a\u0010K\u0003\u0015\u0003s\n))\u0001\thKRl\u0015\r_%uKJ\fG/[8og\"*Q#!\u001f\u0002\u0006\u0006Q2/\u001a;NS:$\u0015N^5tS\ndWm\u00117vgR,'oU5{KR!\u0011QRAT\u0011\u001d\tiB\u0006a\u0001\u0003CASAFA=\u0003\u000b\u000b!dZ3u\u001b&tG)\u001b<jg&\u0014G.Z\"mkN$XM]*ju\u0016DSaFA=\u0003\u000b\u000bqa]3u'\u0016,G\r\u0006\u0003\u0002\u000e\u0006M\u0006bBA\u00181\u0001\u0007\u00111\u0007\u0015\u00061\u0005e\u0014QQ\u0001\bO\u0016$8+Z3eQ\u0015I\u0012\u0011PAC\u0003I9W\r\u001e#jgR\fgnY3NK\u0006\u001cXO]3)\u000bi\tI(a0\"\u0005\u0005\u0005\u0017!\u0002\u001a/i9\u0002\u0014AE:fi\u0012K7\u000f^1oG\u0016lU-Y:ve\u0016$B!!$\u0002H\"9\u0011\u0011I\u000eA\u0002\u0005\u0015\u0003&B\u000e\u0002z\u0005}\u0016!\u0004:v]^KG\u000f[,fS\u001eDG\u000f\u0006\u0005\u0002P\u0006U\u0017q\u001fB\u0001!\u0011\tI'!5\n\u0007\u0005MGM\u0001\u000bCSN,7\r^5oO.kU-\u00198t\u001b>$W\r\u001c\u0005\b\u0003/d\u0002\u0019AAm\u0003%Ign\u001d;b]\u000e,7\u000f\u0005\u0004\u0002\\\u0006\u0005\u0018Q]\u0007\u0003\u0003;T1!a8i\u0003\r\u0011H\rZ\u0005\u0005\u0003G\fiNA\u0002S\t\u0012\u0003r!]At\u0003W\f\t#C\u0002\u0002jJ\u0014a\u0001V;qY\u0016\u0014\u0004\u0003BAw\u0003gl!!a<\u000b\u0007\u0005Eh-\u0001\u0004mS:\fGnZ\u0005\u0005\u0003k\fyO\u0001\u0004WK\u000e$xN\u001d\u0005\b\u0003sd\u0002\u0019AA~\u0003EA\u0017M\u001c3mKB+'o]5ti\u0016t7-\u001a\t\u0004c\u0006u\u0018bAA\u0000e\n9!i\\8mK\u0006t\u0007b\u0002B\u00029\u0001\u0007!QA\u0001\u0006S:\u001cHO\u001d\t\u0006c\n\u001d!1B\u0005\u0004\u0005\u0013\u0011(AB(qi&|g\u000e\u0005\u0003\u0003\u000e\t]QB\u0001B\b\u0015\u0011\u0011\tBa\u0005\u0002\tU$\u0018\u000e\u001c\u0006\u0004\u0005+A\u0017AA7m\u0013\u0011\u0011IBa\u0004\u0003\u001f%s7\u000f\u001e:v[\u0016tG/\u0019;j_:\f1A];o)\u0011\tyMa\b\t\u000f\t\u0005R\u00041\u0001\u0003$\u0005)\u0011N\u001c9viB1\u00111\\Aq\u0003WDS!HA=\u0003\u000b#B!a4\u0003*!9!1\u0006\u0010A\u0002\t5\u0012\u0001\u00023bi\u0006\u0004bAa\f\u0003:\u0005-XB\u0001B\u0019\u0015\u0011\u0011\u0019D!\u000e\u0002\t)\fg/\u0019\u0006\u0004\u0005oA\u0017aA1qS&!!1\bB\u0019\u0005\u001dQ\u0015M^1S\t\u0012CS\u0001AA=\u0003\u000b\u000bqBQ5tK\u000e$\u0018N\\4L\u001b\u0016\fgn\u001d\t\u0004\u0003S\u00023\u0003\u0002\u0011q\u0005\u000b\u0002BAa\u0012\u0003R9!!\u0011\nB'\u001d\u0011\tYEa\u0013\n\u0003ML1Aa\u0014s\u0003\u001d\u0001\u0018mY6bO\u0016LAAa\u0015\u0003V\ta1+\u001a:jC2L'0\u00192mK*\u0019!q\n:\u0015\u0005\t\u0005\u0013A\u0003*P\u001fR{\u0016J\u0014#F1\u0006Y!kT(U?&sE)\u0012-!\u0003mi\u0015\tW0E\u0013ZK5+\u0013\"M\u000b~\u001bE*V*U\u000bJ{\u0016J\u0014#F1\u0006aR*\u0011-`\t&3\u0016jU%C\u0019\u0016{6\tT+T)\u0016\u0013v,\u0013(E\u000bb\u0003\u0013a\u0003'F-\u0016cu\fT%N\u0013R\u000bA\u0002T#W\u000b2{F*S'J)\u0002\na\u0002\\3gi\u000eC\u0017\u000e\u001c3J]\u0012,\u0007\u0010\u0006\u0003\u00024\t%\u0004b\u0002B6Q\u0001\u0007\u00111G\u0001\u0006S:$W\r_\u0001\u0010e&<\u0007\u000e^\"iS2$\u0017J\u001c3fqR!\u00111\u0007B9\u0011\u001d\u0011Y'\u000ba\u0001\u0003g\t1\u0002]1sK:$\u0018J\u001c3fqR!\u00111\u0007B<\u0011\u001d\u0011YG\u000ba\u0001\u0003g\t\u0011b];n[\u0006\u0014\u0018N_3\u0015\u0011\tu41FB\u0018\u0007o\u0001\u0002\"a\u0012\u0003\u0000\u0005M\"1Q\u0005\u0005\u0005\u0003\u000bIFA\u0002NCB\u00042A!\"D\u001b\u0005\u0001#AD\"mkN$XM]*v[6\f'/_\n\u0007\u0007B\u0014YI!\u0012\u0011\u0007E\u0014i)C\u0002\u0003\u0010J\u0014q\u0001\u0015:pIV\u001cG/\u0001\u0003tSj,\u0017!B:ju\u0016\u0004\u0013!C<fS\u001eDGoU;n\u0003)9X-[4iiN+X\u000eI\u0001\u0007G\u0016tG/\u001a:\u0016\u0005\tu\u0005\u0003BA5\u0005?K1A!)e\u000591Vm\u0019;pe^KG\u000f\u001b(pe6\fqaY3oi\u0016\u0014\b%\u0001\u0003d_N$\u0018!B2pgR\u0004CC\u0003BB\u0005W\u0013iKa,\u00032\"9!\u0011\u0013'A\u0002\u0005M\u0002b\u0002BK\u0019\u0002\u0007\u0011\u0011\u0005\u0005\b\u00053c\u0005\u0019\u0001BO\u0011\u001d\u0011)\u000b\u0014a\u0001\u0003C\tAaY8qsRQ!1\u0011B\\\u0005s\u0013YL!0\t\u0013\tEU\n%AA\u0002\u0005M\u0002\"\u0003BK\u001bB\u0005\t\u0019AA\u0011\u0011%\u0011I*\u0014I\u0001\u0002\u0004\u0011i\nC\u0005\u0003&6\u0003\n\u00111\u0001\u0002\"\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTC\u0001BbU\u0011\t\u0019D!2,\u0005\t\u001d\u0007\u0003\u0002Be\u0005#l!Aa3\u000b\t\t5'qZ\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a s\u0013\u0011\u0011\u0019Na3\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\te'\u0006BA\u0011\u0005\u000b\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0003`*\"!Q\u0014Bc\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIQ\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXC\u0001Bt!\u0011\u0011IO!=\u000e\u0005\t-(\u0002\u0002Bw\u0005_\fA\u0001\\1oO*\u0011!1G\u0005\u0005\u0003/\u0012Y/\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\te(q \t\u0004c\nm\u0018b\u0001B\u007fe\n\u0019\u0011I\\=\t\u0011\u0005=A+!AA\u0002y\fq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0007\u000b\u0001baa\u0002\u0004\u000e\teXBAB\u0005\u0015\r\u0019YA]\u0001\u000bG>dG.Z2uS>t\u0017\u0002BB\b\u0007\u0013\u0011\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u00111`B\u000b\u0011%\tyAVA\u0001\u0002\u0004\u0011I0\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003\u0002Bt\u00077A\u0001\"a\u0004X\u0003\u0003\u0005\rA`\u0001\tQ\u0006\u001c\bnQ8eKR\ta0\u0001\u0005u_N#(/\u001b8h)\t\u00119/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003w\u001cI\u0003C\u0005\u0002\u0010i\u000b\t\u00111\u0001\u0003z\"11QF\u0016A\u0002y\f\u0011\u0001\u001a\u0005\b\u0007cY\u0003\u0019AB\u001a\u0003-\t7o]5h]6,g\u000e^:\u0011\r\u0005m\u0017\u0011]B\u001b!\u001d\t\u0018q]A\u001a\u0005;Cq!!\u0011,\u0001\u0004\u0019I\u0004\u0005\u0003\u0002j\rm\u0012bAB\u001fI\nyA)[:uC:\u001cW-T3bgV\u0014XM\u0001\rDYV\u001cH/\u001a:Tk6l\u0017M]=BO\u001e\u0014XmZ1u_J\u001cB\u0001\f9\u0003F\u0005\u0011A\rI\u000b\u0003\u0007s!ba!\u0013\u0004L\r5\u0003c\u0001BCY!11QF\u0019A\u0002yDq!!\u00112\u0001\u0004\u0019I$A\u0001o\u0003\u0015qw\fJ3r)\u0011\t9a!\u0016\t\u0013\u0005=1'!AA\u0002\u0005M\u0012A\u00018!\u000359X-[4iiN+Xn\u0018\u0013fcR!\u0011qAB/\u0011%\tyANA\u0001\u0002\u0004\t\t#A\u0002tk6,\"!a;\u0002\tM,X\u000eI\u0001\u0006gVl7+]\u0001\ngVl7+]0%KF$B!a\u0002\u0004l!I\u0011qB\u001e\u0002\u0002\u0003\u0007\u0011\u0011E\u0001\u0007gVl7+\u001d\u0011\u0002\u0007\u0005$G\r\u0006\u0003\u0004t\rUT\"\u0001\u0017\t\u000f\r]T\b1\u0001\u0003\u001e\u0006\ta/A\u0003nKJ<W\r\u0006\u0003\u0004t\ru\u0004bBB@}\u0001\u00071\u0011J\u0001\u0006_RDWM]\u0001\bgVlW.\u0019:z+\t\u0011\u0019)A\u0006ta2LGoQ3oi\u0016\u0014H\u0003CBE\u0007\u0017\u001biia'\u0011\u000fE\f9O!(\u0003\u001e\"9!\u0011\u0014!A\u0002\tu\u0005bBBH\u0001\u0002\u00071\u0011S\u0001\u0007e\u0006tGm\\7\u0011\t\rM5qS\u0007\u0003\u0007+SAA!\u0005\u0003p&!1\u0011TBK\u0005\u0019\u0011\u0016M\u001c3p[\"9\u0011\u0011\t!A\u0002\re\u0012!E;qI\u0006$X-Q:tS\u001etW.\u001a8ugRQ11GBQ\u0007G\u001bika-\t\u000f\rE\u0012\t1\u0001\u00044!91QU!A\u0002\r\u001d\u0016\u0001\u00053jm&\u001c\u0018N\u00197f\u0013:$\u0017nY3t!\u0019\t9e!+\u00024%!11VA-\u0005\r\u0019V\r\u001e\u0005\b\u0007_\u000b\u0005\u0019ABY\u0003EqWm^\"mkN$XM]\"f]R,'o\u001d\t\t\u0003\u000f\u0012y(a\r\u0003\u001e\"9\u0011\u0011I!A\u0002\re\u0012!\u00032vS2$GK]3f)\u0019\u0019Ila0\u0004DB!\u0011\u0011NB^\u0013\r\u0019i\f\u001a\u0002\u0013\u00072,8\u000f^3sS:<GK]3f\u001d>$W\rC\u0004\u0004B\n\u0003\rA! \u0002\u0011\rdWo\u001d;feNDq!!\u0011C\u0001\u0004\u0019I$\u0001\bDYV\u001cH/\u001a:Tk6l\u0017M]=\u0011\u0007\t\u0015ElE\u0003]\u0007\u0017\u001c9\u000e\u0005\b\u0004N\u000eM\u00171GA\u0011\u0005;\u000b\tCa!\u000e\u0005\r='bABie\u00069!/\u001e8uS6,\u0017\u0002BBk\u0007\u001f\u0014\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c85!\u0011\u0019Ina8\u000e\u0005\rm'\u0002BBo\u0005_\f!![8\n\t\tM31\u001c\u000b\u0003\u0007\u000f\fQ!\u00199qYf$\"Ba!\u0004h\u000e%81^Bw\u0011\u001d\u0011\tj\u0018a\u0001\u0003gAqA!&`\u0001\u0004\t\t\u0003C\u0004\u0003\u001a~\u0003\rA!(\t\u000f\t\u0015v\f1\u0001\u0002\"\u00059QO\\1qa2LH\u0003BBz\u0007w\u0004R!\u001dB\u0004\u0007k\u00042\"]B|\u0003g\t\tC!(\u0002\"%\u00191\u0011 :\u0003\rQ+\b\u000f\\35\u0011%\u0019i\u0010YA\u0001\u0002\u0004\u0011\u0019)A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"\u0001b\u0001\u0011\t\t%HQA\u0005\u0005\t\u000f\u0011YO\u0001\u0004PE*,7\r\u001e"
)
public class BisectingKMeans implements Logging {
   private int k;
   private int maxIterations;
   private double minDivisibleClusterSize;
   private long seed;
   private String distanceMeasure;
   private transient Logger org$apache$spark$internal$Logging$$log_;

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

   private int k() {
      return this.k;
   }

   private void k_$eq(final int x$1) {
      this.k = x$1;
   }

   private int maxIterations() {
      return this.maxIterations;
   }

   private void maxIterations_$eq(final int x$1) {
      this.maxIterations = x$1;
   }

   private double minDivisibleClusterSize() {
      return this.minDivisibleClusterSize;
   }

   private void minDivisibleClusterSize_$eq(final double x$1) {
      this.minDivisibleClusterSize = x$1;
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   private String distanceMeasure() {
      return this.distanceMeasure;
   }

   private void distanceMeasure_$eq(final String x$1) {
      this.distanceMeasure = x$1;
   }

   public BisectingKMeans setK(final int k) {
      .MODULE$.require(k > 0, () -> "k must be positive but got " + k + ".");
      this.k_$eq(k);
      return this;
   }

   public int getK() {
      return this.k();
   }

   public BisectingKMeans setMaxIterations(final int maxIterations) {
      .MODULE$.require(maxIterations > 0, () -> "maxIterations must be positive but got " + maxIterations + ".");
      this.maxIterations_$eq(maxIterations);
      return this;
   }

   public int getMaxIterations() {
      return this.maxIterations();
   }

   public BisectingKMeans setMinDivisibleClusterSize(final double minDivisibleClusterSize) {
      .MODULE$.require(minDivisibleClusterSize > (double)0.0F, () -> "minDivisibleClusterSize must be positive but got " + minDivisibleClusterSize + ".");
      this.minDivisibleClusterSize_$eq(minDivisibleClusterSize);
      return this;
   }

   public double getMinDivisibleClusterSize() {
      return this.minDivisibleClusterSize();
   }

   public BisectingKMeans setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public long getSeed() {
      return this.seed();
   }

   public String getDistanceMeasure() {
      return this.distanceMeasure();
   }

   public BisectingKMeans setDistanceMeasure(final String distanceMeasure) {
      DistanceMeasure$.MODULE$.validateDistanceMeasure(distanceMeasure);
      this.distanceMeasure_$eq(distanceMeasure);
      return this;
   }

   public BisectingKMeansModel runWithWeight(final RDD instances, final boolean handlePersistence, final Option instr) {
      int d = BoxesRunTime.unboxToInt(instances.map((x$1x) -> BoxesRunTime.boxToInteger($anonfun$runWithWeight$1(x$1x)), scala.reflect.ClassTag..MODULE$.Int()).first());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Feature dimension: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FEATURE_DIMENSION..MODULE$, BoxesRunTime.boxToInteger(d))})))));
      DistanceMeasure dMeasure = DistanceMeasure$.MODULE$.decodeFromString(this.distanceMeasure());
      RDD norms = instances.map((dx) -> BoxesRunTime.boxToDouble($anonfun$runWithWeight$3(dx)), scala.reflect.ClassTag..MODULE$.Double());
      RDD vectors = instances.zip(norms, scala.reflect.ClassTag..MODULE$.Double()).map((x0$1) -> {
         if (x0$1 != null) {
            Tuple2 var3 = (Tuple2)x0$1._1();
            double norm = x0$1._2$mcD$sp();
            if (var3 != null) {
               Vector x = (Vector)var3._1();
               double weight = var3._2$mcD$sp();
               return new VectorWithNorm(x, norm, weight);
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
      if (handlePersistence) {
         vectors.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      } else {
         norms.persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
      }

      ObjectRef assignments = ObjectRef.create(vectors.map((v) -> new Tuple2(BoxesRunTime.boxToLong(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$ROOT_INDEX()), v), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)));
      ObjectRef activeClusters = ObjectRef.create(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$summarize(d, (RDD)assignments.elem, dMeasure));
      instr.foreach((x$2x) -> {
         $anonfun$runWithWeight$6(activeClusters, x$2x);
         return BoxedUnit.UNIT;
      });
      instr.foreach((x$4) -> {
         $anonfun$runWithWeight$8(activeClusters, x$4);
         return BoxedUnit.UNIT;
      });
      ClusterSummary rootSummary = (ClusterSummary)((scala.collection.immutable.Map)activeClusters.elem).apply(BoxesRunTime.boxToLong(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$ROOT_INDEX()));
      long n = rootSummary.size();
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Number of points: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_POINT..MODULE$, BoxesRunTime.boxToLong(n))})))));
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Initial cost: ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.COST..MODULE$, BoxesRunTime.boxToDouble(rootSummary.cost()))})))));
      long minSize = this.minDivisibleClusterSize() >= (double)1.0F ? (long)scala.math.package..MODULE$.ceil(this.minDivisibleClusterSize()) : (long)scala.math.package..MODULE$.ceil(this.minDivisibleClusterSize() * (double)n);
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The minimum number of points of a divisible cluster is "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.MIN_POINT_PER_CLUSTER..MODULE$, BoxesRunTime.boxToLong(minSize))}))))));
      Seq inactiveClusters = (Seq)scala.collection.mutable.Seq..MODULE$.empty();
      Random random = new Random(this.seed());
      int numLeafClustersNeeded = this.k() - 1;
      IntRef level = IntRef.create(1);
      RDD preIndices = null;

      RDD indices;
      for(indices = null; ((scala.collection.immutable.Map)activeClusters.elem).nonEmpty() && numLeafClustersNeeded > 0 && (double)level.elem < BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$LEVEL_LIMIT(); ++level.elem) {
         scala.collection.immutable.Map divisibleClusters = (scala.collection.immutable.Map)((scala.collection.immutable.Map)activeClusters.elem).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$runWithWeight$13(minSize, x0$2)));
         if (divisibleClusters.size() > numLeafClustersNeeded) {
            divisibleClusters = ((IterableOnceOps)((IterableOps)divisibleClusters.toSeq().sortBy((x0$3) -> BoxesRunTime.boxToLong($anonfun$runWithWeight$14(x0$3)), scala.math.Ordering.Long..MODULE$)).take(numLeafClustersNeeded)).toMap(scala..less.colon.less..MODULE$.refl());
         }

         if (divisibleClusters.nonEmpty()) {
            Set divisibleIndices = divisibleClusters.keys().toSet();
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Dividing ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.DIVISIBLE_CLUSTER_INDICES_SIZE..MODULE$, BoxesRunTime.boxToInteger(divisibleIndices.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{" clusters on level ", "."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLUSTER_LEVEL..MODULE$, BoxesRunTime.boxToInteger(level.elem))}))))));
            ObjectRef newClusterCenters = ObjectRef.create((scala.collection.immutable.Map)((MapOps)divisibleClusters.flatMap((x0$4) -> {
               if (x0$4 != null) {
                  long index = x0$4._1$mcJ$sp();
                  ClusterSummary summary = (ClusterSummary)x0$4._2();
                  Tuple2 var10 = BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$splitCenter(summary.center(), random, dMeasure);
                  if (var10 != null) {
                     VectorWithNorm left = (VectorWithNorm)var10._1();
                     VectorWithNorm right = (VectorWithNorm)var10._2();
                     Tuple2 var9 = new Tuple2(left, right);
                     VectorWithNorm leftx = (VectorWithNorm)var9._1();
                     VectorWithNorm rightx = (VectorWithNorm)var9._2();
                     return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{new Tuple2(BoxesRunTime.boxToLong(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$leftChildIndex(index)), leftx), new Tuple2(BoxesRunTime.boxToLong(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$rightChildIndex(index)), rightx)})));
                  } else {
                     throw new MatchError(var10);
                  }
               } else {
                  throw new MatchError(x0$4);
               }
            })).map((x) -> (Tuple2).MODULE$.identity(x)));
            ObjectRef newClusters = ObjectRef.create((Object)null);
            ObjectRef newAssignments = ObjectRef.create((Object)null);
            scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.maxIterations()).foreach$mVc$sp((JFunction1.mcVI.sp)(iter) -> {
               newAssignments.elem = BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$updateAssignments((RDD)assignments.elem, divisibleIndices, (scala.collection.immutable.Map)newClusterCenters.elem, dMeasure).filter((x0$5) -> BoxesRunTime.boxToBoolean($anonfun$runWithWeight$19(divisibleIndices, x0$5)));
               newClusters.elem = BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$summarize(d, (RDD)newAssignments.elem, dMeasure);
               newClusterCenters.elem = (scala.collection.immutable.Map)((scala.collection.immutable.Map)newClusters.elem).transform((x$7, v) -> $anonfun$runWithWeight$20(BoxesRunTime.unboxToLong(x$7), v)).map((x) -> (Tuple2).MODULE$.identity(x));
            });
            if (preIndices != null) {
               boolean x$1 = preIndices.unpersist$default$1();
               preIndices.unpersist(x$1);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            preIndices = indices;
            indices = org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$updateAssignments((RDD)assignments.elem, divisibleIndices, (scala.collection.immutable.Map)newClusterCenters.elem, dMeasure), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class), scala.math.Ordering.Long..MODULE$).keys().persist(org.apache.spark.storage.StorageLevel..MODULE$.MEMORY_AND_DISK());
            assignments.elem = indices.zip(vectors, scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
            inactiveClusters = (Seq)inactiveClusters.$plus$plus((scala.collection.immutable.Map)activeClusters.elem);
            activeClusters.elem = (scala.collection.immutable.Map)newClusters.elem;
            numLeafClustersNeeded -= divisibleClusters.size();
         } else {
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"None active and divisible clusters left "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"on level ", ". Stop iterations."})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CLUSTER_LEVEL..MODULE$, BoxesRunTime.boxToInteger(level.elem))}))))));
            inactiveClusters = (Seq)inactiveClusters.$plus$plus((scala.collection.immutable.Map)activeClusters.elem);
            activeClusters.elem = .MODULE$.Map().empty();
         }
      }

      if (preIndices != null) {
         boolean x$2 = preIndices.unpersist$default$1();
         preIndices.unpersist(x$2);
      } else {
         BoxedUnit var36 = BoxedUnit.UNIT;
      }

      if (indices != null) {
         boolean x$3 = indices.unpersist$default$1();
         indices.unpersist(x$3);
      } else {
         BoxedUnit var37 = BoxedUnit.UNIT;
      }

      if (handlePersistence) {
         vectors.unpersist(vectors.unpersist$default$1());
      } else {
         norms.unpersist(norms.unpersist$default$1());
      }

      scala.collection.immutable.Map clusters = (scala.collection.immutable.Map)((scala.collection.immutable.Map)activeClusters.elem).$plus$plus(inactiveClusters);
      ClusteringTreeNode root = BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$buildTree(clusters, dMeasure);
      double totalCost = BoxesRunTime.unboxToDouble(.MODULE$.wrapDoubleArray((double[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps(root.leafNodes()), (x$8) -> BoxesRunTime.boxToDouble($anonfun$runWithWeight$23(x$8)), scala.reflect.ClassTag..MODULE$.Double())).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      return new BisectingKMeansModel(root, this.distanceMeasure(), totalCost);
   }

   public BisectingKMeansModel run(final RDD input) {
      RDD instances;
      boolean var5;
      label17: {
         label16: {
            instances = input.map((point) -> new Tuple2(point, BoxesRunTime.boxToDouble((double)1.0F)), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
            StorageLevel var10000 = input.getStorageLevel();
            StorageLevel var4 = org.apache.spark.storage.StorageLevel..MODULE$.NONE();
            if (var10000 == null) {
               if (var4 == null) {
                  break label16;
               }
            } else if (var10000.equals(var4)) {
               break label16;
            }

            var5 = false;
            break label17;
         }

         var5 = true;
      }

      boolean handlePersistence = var5;
      return this.runWithWeight(instances, handlePersistence, scala.None..MODULE$);
   }

   public BisectingKMeansModel run(final JavaRDD data) {
      return this.run(data.rdd());
   }

   // $FF: synthetic method
   public static final int $anonfun$runWithWeight$1(final Tuple2 x$1) {
      return ((Vector)x$1._1()).size();
   }

   // $FF: synthetic method
   public static final double $anonfun$runWithWeight$3(final Tuple2 d) {
      return Vectors$.MODULE$.norm((Vector)d._1(), (double)2.0F);
   }

   // $FF: synthetic method
   public static final long $anonfun$runWithWeight$7(final ClusterSummary x$3) {
      return x$3.size();
   }

   // $FF: synthetic method
   public static final void $anonfun$runWithWeight$6(final ObjectRef activeClusters$1, final Instrumentation x$2) {
      x$2.logNumExamples(BoxesRunTime.unboxToLong(((IterableOnceOps)((scala.collection.immutable.Map)activeClusters$1.elem).values().map((x$3) -> BoxesRunTime.boxToLong($anonfun$runWithWeight$7(x$3)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$)));
   }

   // $FF: synthetic method
   public static final double $anonfun$runWithWeight$9(final ClusterSummary x$5) {
      return x$5.weightSum();
   }

   // $FF: synthetic method
   public static final void $anonfun$runWithWeight$8(final ObjectRef activeClusters$1, final Instrumentation x$4) {
      x$4.logSumOfWeights(BoxesRunTime.unboxToDouble(((IterableOnceOps)((scala.collection.immutable.Map)activeClusters$1.elem).values().map((x$5) -> BoxesRunTime.boxToDouble($anonfun$runWithWeight$9(x$5)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$runWithWeight$13(final long minSize$1, final Tuple2 x0$2) {
      if (x0$2 == null) {
         throw new MatchError(x0$2);
      } else {
         ClusterSummary summary = (ClusterSummary)x0$2._2();
         return summary.size() >= minSize$1 && summary.cost() > MLUtils$.MODULE$.EPSILON() * (double)summary.size();
      }
   }

   // $FF: synthetic method
   public static final long $anonfun$runWithWeight$14(final Tuple2 x0$3) {
      if (x0$3 != null) {
         ClusterSummary summary = (ClusterSummary)x0$3._2();
         return -summary.size();
      } else {
         throw new MatchError(x0$3);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$runWithWeight$19(final Set divisibleIndices$1, final Tuple2 x0$5) {
      if (x0$5 != null) {
         long index = x0$5._1$mcJ$sp();
         return divisibleIndices$1.contains(BoxesRunTime.boxToLong(BisectingKMeans$.MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$parentIndex(index)));
      } else {
         throw new MatchError(x0$5);
      }
   }

   // $FF: synthetic method
   public static final VectorWithNorm $anonfun$runWithWeight$20(final long x$7, final ClusterSummary v) {
      return v.center();
   }

   // $FF: synthetic method
   public static final double $anonfun$runWithWeight$23(final ClusteringTreeNode x$8) {
      return x$8.cost();
   }

   private BisectingKMeans(final int k, final int maxIterations, final double minDivisibleClusterSize, final long seed, final String distanceMeasure) {
      this.k = k;
      this.maxIterations = maxIterations;
      this.minDivisibleClusterSize = minDivisibleClusterSize;
      this.seed = seed;
      this.distanceMeasure = distanceMeasure;
      super();
      Logging.$init$(this);
   }

   public BisectingKMeans() {
      this(4, 20, (double)1.0F, (long)Statics.anyHash(BisectingKMeans.class.getName()), DistanceMeasure$.MODULE$.EUCLIDEAN());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class ClusterSummaryAggregator implements Serializable {
      private final int d;
      private final DistanceMeasure distanceMeasure;
      private long n;
      private double weightSum;
      private final Vector sum;
      private double sumSq;

      public int d() {
         return this.d;
      }

      public DistanceMeasure distanceMeasure() {
         return this.distanceMeasure;
      }

      private long n() {
         return this.n;
      }

      private void n_$eq(final long x$1) {
         this.n = x$1;
      }

      private double weightSum() {
         return this.weightSum;
      }

      private void weightSum_$eq(final double x$1) {
         this.weightSum = x$1;
      }

      private Vector sum() {
         return this.sum;
      }

      private double sumSq() {
         return this.sumSq;
      }

      private void sumSq_$eq(final double x$1) {
         this.sumSq = x$1;
      }

      public ClusterSummaryAggregator add(final VectorWithNorm v) {
         this.n_$eq(this.n() + 1L);
         this.weightSum_$eq(this.weightSum() + v.weight());
         this.sumSq_$eq(this.sumSq() + v.norm() * v.norm() * v.weight());
         this.distanceMeasure().updateClusterSum(v, this.sum());
         return this;
      }

      public ClusterSummaryAggregator merge(final ClusterSummaryAggregator other) {
         this.n_$eq(this.n() + other.n());
         this.weightSum_$eq(this.weightSum() + other.weightSum());
         this.sumSq_$eq(this.sumSq() + other.sumSq());
         BLAS$.MODULE$.axpy((double)1.0F, other.sum(), this.sum());
         return this;
      }

      public ClusterSummary summary() {
         VectorWithNorm center = this.distanceMeasure().centroid(this.sum().copy(), this.weightSum());
         double cost = this.distanceMeasure().clusterCost(center, new VectorWithNorm(this.sum()), this.weightSum(), this.sumSq());
         return new ClusterSummary(this.n(), this.weightSum(), center, cost);
      }

      public ClusterSummaryAggregator(final int d, final DistanceMeasure distanceMeasure) {
         this.d = d;
         this.distanceMeasure = distanceMeasure;
         this.n = 0L;
         this.weightSum = (double)0.0F;
         this.sum = Vectors$.MODULE$.zeros(d);
         this.sumSq = (double)0.0F;
      }
   }

   private static class ClusterSummary implements Product, Serializable {
      private final long size;
      private final double weightSum;
      private final VectorWithNorm center;
      private final double cost;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long size() {
         return this.size;
      }

      public double weightSum() {
         return this.weightSum;
      }

      public VectorWithNorm center() {
         return this.center;
      }

      public double cost() {
         return this.cost;
      }

      public ClusterSummary copy(final long size, final double weightSum, final VectorWithNorm center, final double cost) {
         return new ClusterSummary(size, weightSum, center, cost);
      }

      public long copy$default$1() {
         return this.size();
      }

      public double copy$default$2() {
         return this.weightSum();
      }

      public VectorWithNorm copy$default$3() {
         return this.center();
      }

      public double copy$default$4() {
         return this.cost();
      }

      public String productPrefix() {
         return "ClusterSummary";
      }

      public int productArity() {
         return 4;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.size());
            }
            case 1 -> {
               return BoxesRunTime.boxToDouble(this.weightSum());
            }
            case 2 -> {
               return this.center();
            }
            case 3 -> {
               return BoxesRunTime.boxToDouble(this.cost());
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
         return x$1 instanceof ClusterSummary;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "size";
            }
            case 1 -> {
               return "weightSum";
            }
            case 2 -> {
               return "center";
            }
            case 3 -> {
               return "cost";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.size()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.weightSum()));
         var1 = Statics.mix(var1, Statics.anyHash(this.center()));
         var1 = Statics.mix(var1, Statics.doubleHash(this.cost()));
         return Statics.finalizeHash(var1, 4);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label59: {
               if (x$1 instanceof ClusterSummary) {
                  ClusterSummary var4 = (ClusterSummary)x$1;
                  if (this.size() == var4.size() && this.weightSum() == var4.weightSum() && this.cost() == var4.cost()) {
                     label52: {
                        VectorWithNorm var10000 = this.center();
                        VectorWithNorm var5 = var4.center();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label52;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label52;
                        }

                        if (var4.canEqual(this)) {
                           break label59;
                        }
                     }
                  }
               }

               var6 = false;
               return var6;
            }
         }

         var6 = true;
         return var6;
      }

      public ClusterSummary(final long size, final double weightSum, final VectorWithNorm center, final double cost) {
         this.size = size;
         this.weightSum = weightSum;
         this.center = center;
         this.cost = cost;
         Product.$init$(this);
      }
   }

   private static class ClusterSummary$ extends AbstractFunction4 implements Serializable {
      public static final ClusterSummary$ MODULE$ = new ClusterSummary$();

      public final String toString() {
         return "ClusterSummary";
      }

      public ClusterSummary apply(final long size, final double weightSum, final VectorWithNorm center, final double cost) {
         return new ClusterSummary(size, weightSum, center, cost);
      }

      public Option unapply(final ClusterSummary x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple4(BoxesRunTime.boxToLong(x$0.size()), BoxesRunTime.boxToDouble(x$0.weightSum()), x$0.center(), BoxesRunTime.boxToDouble(x$0.cost()))));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ClusterSummary$.class);
      }

      public ClusterSummary$() {
      }
   }
}
