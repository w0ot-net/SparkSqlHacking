package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.avro.SchemaNormalization;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.MessageWithContext;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.ConfigReader;
import org.apache.spark.internal.config.Kryo$;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.OptionalConfigEntry;
import org.apache.spark.internal.config.SparkConfigProvider;
import org.apache.spark.serializer.KryoSerializer;
import org.apache.spark.util.Utils$;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.Option.;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.LinkedHashSet;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011\re\u0001B:u\u0001mD!\"a\u000e\u0001\u0005\u0003\u0005\u000b\u0011BA\u001d\u0011\u001d\ty\u0004\u0001C\u0001\u0003\u0003Bq!a\u0010\u0001\t\u0003\t9\u0005C\u0005\u0002J\u0001\u0011\r\u0011\"\u0003\u0002L!A\u0011\u0011\u000f\u0001!\u0002\u0013\ti\u0005\u0003\u0006\u0002t\u0001A)\u0019!C\u0005\u0003kB\u0001\"a#\u0001\t\u0003!\u0018Q\u0012\u0005\b\u0003'\u0003A\u0011AAK\u0011!\t\u0019\n\u0001C\u0001i\u0006}\u0005\u0002CAJ\u0001\u0011\u0005A/a*\t\u0011\u0005M\u0005\u0001\"\u0001u\u0003\u001bDq!a8\u0001\t\u0003\t\t\u000fC\u0004\u0002h\u0002!\t!!;\t\u000f\u0005=\b\u0001\"\u0001\u0002r\"9\u0011q\u001e\u0001\u0005\u0002\u0005u\bb\u0002B\u0004\u0001\u0011\u0005!\u0011\u0002\u0005\b\u0005\u000f\u0001A\u0011\u0001B\t\u0011\u001d\u00119\u0001\u0001C\u0001\u0005?AqA!\n\u0001\t\u0003\u00119\u0003C\u0004\u0003.\u0001!\tAa\f\t\u000f\te\u0002\u0001\"\u0001\u0003<!A!\u0011\b\u0001\u0005\u0002Q\u0014\t\u0005\u0003\u0005\u0003:\u0001!\t\u0001\u001eB(\u0011\u001d\u0011i\u0006\u0001C\u0001\u0005?B\u0011B!\u001e\u0001\u0005\u0004%iAa\u001e\t\u0011\t}\u0004\u0001)A\u0007\u0005sBqA!!\u0001\t\u0003\u0011\u0019\tC\u0004\u0003\u001c\u0002!\tA!(\t\u000f\t-\u0006\u0001\"\u0001\u0003.\"A!1\u0016\u0001\u0005\u0002Q\u0014\t\f\u0003\u0005\u0003@\u0002!\t\u0001\u001eBa\u0011\u001d\u0011i\r\u0001C\u0001\u0005\u001fD\u0001B!7\u0001\t\u0003!(1\u001c\u0005\b\u0005?\u0004A\u0011\u0001Bq\u0011\u001d\u0011\u0019\u000f\u0001C\u0001\u0005KDqAa;\u0001\t\u0003\u0011i\u000fC\u0004\u0003p\u0002!\tA!=\t\u000f\tM\b\u0001\"\u0001\u0003v\"9!\u0011 \u0001\u0005B\u0005\u001d\u0003\u0002\u0003B~\u0001\u0011\u0005AO!@\t\u0011\r\u0005\u0001\u0001\"\u0001u\u0007\u0007Aqaa\u0003\u0001\t\u0003\u0011\tp\u0002\u0005\u0004\u000eQD\t\u0001^B\b\r\u001d\u0019H\u000f#\u0001u\u0007#Aq!a\u0010-\t\u0003\u0019i\u0002C\u0005\u0004 1\u0012\r\u0011\"\u0003\u0004\"!A1Q\u0017\u0017!\u0002\u0013\u0019\u0019\u0003C\u0005\u000482\u0012\r\u0011\"\u0003\u0004:\"A1q \u0017!\u0002\u0013\u0019Y\fC\u0005\u0005\u00021\u0012\r\u0011\"\u0003\u0005\u0004!AA\u0011\u0002\u0017!\u0002\u0013!)\u0001C\u0004\u0005\f1\"\t\u0001\"\u0004\t\u000f\u0011EA\u0006\"\u0001\u0005\u0014!9Aq\u0003\u0017\u0005\u0002\u0011e\u0001b\u0002C\u0014Y\u0011\u0005A\u0011\u0006\u0004\u0007\u0007SaCia\u000b\t\u0015\u0005e\u0005H!f\u0001\n\u0003\u0011\t\u0010\u0003\u0006\u00044a\u0012\t\u0012)A\u0005\u0003CB!b!\u000e9\u0005+\u0007I\u0011\u0001By\u0011)\u00199\u0004\u000fB\tB\u0003%\u0011\u0011\r\u0005\u000b\u0007sA$Q3A\u0005\u0002\tE\bBCB\u001eq\tE\t\u0015!\u0003\u0002b!9\u0011q\b\u001d\u0005\u0002\ru\u0002\"CB#q\u0005\u0005I\u0011AB$\u0011%\u0019y\u0005OI\u0001\n\u0003\u0019\t\u0006C\u0005\u0004ha\n\n\u0011\"\u0001\u0004R!I1\u0011\u000e\u001d\u0012\u0002\u0013\u00051\u0011\u000b\u0005\n\u0007WB\u0014\u0011!C!\u0007[B\u0011b!\u001f9\u0003\u0003%\taa\u001f\t\u0013\r\r\u0005(!A\u0005\u0002\r\u0015\u0005\"CBFq\u0005\u0005I\u0011IBG\u0011%\u0019Y\nOA\u0001\n\u0003\u0019i\nC\u0005\u0004\"b\n\t\u0011\"\u0011\u0004$\"I1q\u0015\u001d\u0002\u0002\u0013\u00053\u0011\u0016\u0005\n\u0007WC\u0014\u0011!C!\u0007[C\u0011ba,9\u0003\u0003%\te!-\b\u0013\u00115B&!A\t\n\u0011=b!CB\u0015Y\u0005\u0005\t\u0012\u0002C\u0019\u0011\u001d\tyD\u0014C\u0001\t\u007fA\u0011ba+O\u0003\u0003%)e!,\t\u0013\u0011\u0005c*!A\u0005\u0002\u0012\r\u0003\"\u0003C&\u001d\u0006\u0005I\u0011\u0011C'\u0011%!YFTA\u0001\n\u0013!iF\u0002\u0004\u0004L2\"5Q\u001a\u0005\u000b\u00033#&Q3A\u0005\u0002\tE\bBCB\u001a)\nE\t\u0015!\u0003\u0002b!Q1Q\u0007+\u0003\u0016\u0004%\tA!=\t\u0015\r]BK!E!\u0002\u0013\t\t\u0007\u0003\u0006\u0004PR\u0013)\u001a!C\u0001\u0007#D!b!7U\u0005#\u0005\u000b\u0011BBj\u0011\u001d\ty\u0004\u0016C\u0001\u00077D\u0011b!\u0012U\u0003\u0003%\taa9\t\u0013\r=C+%A\u0005\u0002\rE\u0003\"CB4)F\u0005I\u0011AB)\u0011%\u0019I\u0007VI\u0001\n\u0003\u0019Y\u000fC\u0005\u0004lQ\u000b\t\u0011\"\u0011\u0004n!I1\u0011\u0010+\u0002\u0002\u0013\u000511\u0010\u0005\n\u0007\u0007#\u0016\u0011!C\u0001\u0007_D\u0011ba#U\u0003\u0003%\te!$\t\u0013\rmE+!A\u0005\u0002\rM\b\"CBQ)\u0006\u0005I\u0011IB|\u0011%\u00199\u000bVA\u0001\n\u0003\u001aI\u000bC\u0005\u0004,R\u000b\t\u0011\"\u0011\u0004.\"I1q\u0016+\u0002\u0002\u0013\u000531`\u0004\n\tKb\u0013\u0011!E\u0005\tO2\u0011ba3-\u0003\u0003EI\u0001\"\u001b\t\u000f\u0005}\"\u000e\"\u0001\u0005n!I11\u00166\u0002\u0002\u0013\u00153Q\u0016\u0005\n\t\u0003R\u0017\u0011!CA\t_B\u0011\u0002b\u001ek#\u0003%\taa;\t\u0013\u0011-#.!A\u0005\u0002\u0012e\u0004\"\u0003CAUF\u0005I\u0011ABv\u0011%!YF[A\u0001\n\u0013!i\u0006C\u0005\u0005\\1\n\t\u0011\"\u0003\u0005^\tI1\u000b]1sW\u000e{gN\u001a\u0006\u0003kZ\fQa\u001d9be.T!a\u001e=\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005I\u0018aA8sO\u000e\u00011C\u0003\u0001}\u0003\u000b\ti!!\n\u00022A\u0019Q0!\u0001\u000e\u0003yT\u0011a`\u0001\u0006g\u000e\fG.Y\u0005\u0004\u0003\u0007q(AB!osJ+g\r\u0005\u0003\u0002\b\u0005%Q\"\u0001;\n\u0007\u0005-AOA\tSK\u0006$wJ\u001c7z'B\f'o[\"p]\u001a\u0004B!a\u0004\u0002 9!\u0011\u0011CA\u000e\u001d\u0011\t\u0019\"!\u0007\u000e\u0005\u0005U!bAA\fu\u00061AH]8pizJ\u0011a`\u0005\u0004\u0003;q\u0018a\u00029bG.\fw-Z\u0005\u0005\u0003C\t\u0019CA\u0005DY>tW-\u00192mK*\u0019\u0011Q\u0004@\u0011\t\u0005\u001d\u0012QF\u0007\u0003\u0003SQ1!a\u000bu\u0003!Ig\u000e^3s]\u0006d\u0017\u0002BA\u0018\u0003S\u0011q\u0001T8hO&tw\r\u0005\u0003\u0002\u0010\u0005M\u0012\u0002BA\u001b\u0003G\u0011AbU3sS\u0006d\u0017N_1cY\u0016\fA\u0002\\8bI\u0012+g-Y;miN\u00042!`A\u001e\u0013\r\tiD \u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q!\u00111IA#!\r\t9\u0001\u0001\u0005\b\u0003o\u0011\u0001\u0019AA\u001d)\t\t\u0019%\u0001\u0005tKR$\u0018N\\4t+\t\ti\u0005\u0005\u0005\u0002P\u0005u\u0013\u0011MA1\u001b\t\t\tF\u0003\u0003\u0002T\u0005U\u0013AC2p]\u000e,(O]3oi*!\u0011qKA-\u0003\u0011)H/\u001b7\u000b\u0005\u0005m\u0013\u0001\u00026bm\u0006LA!a\u0018\u0002R\t\t2i\u001c8dkJ\u0014XM\u001c;ICNDW*\u00199\u0011\t\u0005\r\u00141\u000e\b\u0005\u0003K\n9\u0007E\u0002\u0002\u0014yL1!!\u001b\u007f\u0003\u0019\u0001&/\u001a3fM&!\u0011QNA8\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\u000e@\u0002\u0013M,G\u000f^5oON\u0004\u0013A\u0002:fC\u0012,'/\u0006\u0002\u0002xA!\u0011\u0011PA@\u001b\t\tYH\u0003\u0003\u0002~\u0005%\u0012AB2p]\u001aLw-\u0003\u0003\u0002\u0002\u0006m$\u0001D\"p]\u001aLwMU3bI\u0016\u0014\bf\u0001\u0004\u0002\u0006B\u0019Q0a\"\n\u0007\u0005%ePA\u0005ue\u0006t7/[3oi\u0006ABn\\1e\rJ|WnU=ti\u0016l\u0007K]8qKJ$\u0018.Z:\u0015\t\u0005\r\u0013q\u0012\u0005\b\u0003#;\u0001\u0019AA\u001d\u0003\u0019\u0019\u0018\u000e\\3oi\u0006\u00191/\u001a;\u0015\r\u0005\r\u0013qSAN\u0011\u001d\tI\n\u0003a\u0001\u0003C\n1a[3z\u0011\u001d\ti\n\u0003a\u0001\u0003C\nQA^1mk\u0016$\u0002\"a\u0011\u0002\"\u0006\r\u0016Q\u0015\u0005\b\u00033K\u0001\u0019AA1\u0011\u001d\ti*\u0003a\u0001\u0003CBq!!%\n\u0001\u0004\tI$\u0006\u0003\u0002*\u0006eFCBA\"\u0003W\u000bY\rC\u0004\u0002.*\u0001\r!a,\u0002\u000b\u0015tGO]=\u0011\r\u0005e\u0014\u0011WA[\u0013\u0011\t\u0019,a\u001f\u0003\u0017\r{gNZ5h\u000b:$(/\u001f\t\u0005\u0003o\u000bI\f\u0004\u0001\u0005\u000f\u0005m&B1\u0001\u0002>\n\tA+\u0005\u0003\u0002@\u0006\u0015\u0007cA?\u0002B&\u0019\u00111\u0019@\u0003\u000f9{G\u000f[5oOB\u0019Q0a2\n\u0007\u0005%gPA\u0002B]fDq!!(\u000b\u0001\u0004\t),\u0006\u0003\u0002P\u0006mGCBA\"\u0003#\fi\u000eC\u0004\u0002..\u0001\r!a5\u0011\r\u0005e\u0014Q[Am\u0013\u0011\t9.a\u001f\u0003'=\u0003H/[8oC2\u001cuN\u001c4jO\u0016sGO]=\u0011\t\u0005]\u00161\u001c\u0003\b\u0003w[!\u0019AA_\u0011\u001d\tij\u0003a\u0001\u00033\f\u0011b]3u\u001b\u0006\u001cH/\u001a:\u0015\t\u0005\r\u00131\u001d\u0005\b\u0003Kd\u0001\u0019AA1\u0003\u0019i\u0017m\u001d;fe\u0006Q1/\u001a;BaBt\u0015-\\3\u0015\t\u0005\r\u00131\u001e\u0005\b\u0003[l\u0001\u0019AA1\u0003\u0011q\u0017-\\3\u0002\u000fM,GOS1sgR!\u00111IAz\u0011\u001d\t)P\u0004a\u0001\u0003o\fAA[1sgB1\u0011qBA}\u0003CJA!a?\u0002$\t\u00191+Z9\u0015\t\u0005\r\u0013q \u0005\b\u0003k|\u0001\u0019\u0001B\u0001!\u0015i(1AA1\u0013\r\u0011)A \u0002\u0006\u0003J\u0014\u0018-_\u0001\u000fg\u0016$X\t_3dkR|'/\u00128w)\u0019\t\u0019Ea\u0003\u0003\u0010!9!Q\u0002\tA\u0002\u0005\u0005\u0014\u0001\u0003<be&\f'\r\\3\t\u000f\u0005u\u0005\u00031\u0001\u0002bQ!\u00111\tB\n\u0011\u001d\u0011)\"\u0005a\u0001\u0005/\t\u0011B^1sS\u0006\u0014G.Z:\u0011\r\u0005=\u0011\u0011 B\r!\u001di(1DA1\u0003CJ1A!\b\u007f\u0005\u0019!V\u000f\u001d7feQ!\u00111\tB\u0011\u0011\u001d\u0011)B\u0005a\u0001\u0005G\u0001R! B\u0002\u00053\tAb]3u'B\f'o\u001b%p[\u0016$B!a\u0011\u0003*!9!1F\nA\u0002\u0005\u0005\u0014\u0001\u00025p[\u0016\faa]3u\u00032dG\u0003BA\"\u0005cAq!!\u0013\u0015\u0001\u0004\u0011\u0019\u0004\u0005\u0004\u0002\u0010\tU\"\u0011D\u0005\u0005\u0005o\t\u0019C\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u00031\u0019X\r^%g\u001b&\u001c8/\u001b8h)\u0019\t\u0019E!\u0010\u0003@!9\u0011\u0011T\u000bA\u0002\u0005\u0005\u0004bBAO+\u0001\u0007\u0011\u0011M\u000b\u0005\u0005\u0007\u0012Y\u0005\u0006\u0004\u0002D\t\u0015#Q\n\u0005\b\u0003[3\u0002\u0019\u0001B$!\u0019\tI(!-\u0003JA!\u0011q\u0017B&\t\u001d\tYL\u0006b\u0001\u0003{Cq!!(\u0017\u0001\u0004\u0011I%\u0006\u0003\u0003R\teCCBA\"\u0005'\u0012Y\u0006C\u0004\u0002.^\u0001\rA!\u0016\u0011\r\u0005e\u0014Q\u001bB,!\u0011\t9L!\u0017\u0005\u000f\u0005mvC1\u0001\u0002>\"9\u0011QT\fA\u0002\t]\u0013a\u0005:fO&\u001cH/\u001a:Lef|7\t\\1tg\u0016\u001cH\u0003BA\"\u0005CBqAa\u0019\u0019\u0001\u0004\u0011)'A\u0004dY\u0006\u001c8/Z:\u0011\u000bu\u0014\u0019Aa\u001a1\t\t%$\u0011\u000f\t\u0007\u0003G\u0012YGa\u001c\n\t\t5\u0014q\u000e\u0002\u0006\u00072\f7o\u001d\t\u0005\u0003o\u0013\t\b\u0002\u0007\u0003t\t\u0005\u0014\u0011!A\u0001\u0006\u0003\tiLA\u0002`II\nQ\"\u0019<s_:\u000bW.Z:qC\u000e,WC\u0001B=\u001f\t\u0011Y(\t\u0002\u0003~\u0005a\u0011M\u001e:p]M\u001c\u0007.Z7b]\u0005q\u0011M\u001e:p\u001d\u0006lWm\u001d9bG\u0016\u0004\u0013a\u0005:fO&\u001cH/\u001a:BmJ|7k\u00195f[\u0006\u001cH\u0003BA\"\u0005\u000bCqAa\"\u001c\u0001\u0004\u0011I)A\u0004tG\",W.Y:\u0011\u000bu\u0014YIa$\n\u0007\t5eP\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u0002BA!%\u0003\u00186\u0011!1\u0013\u0006\u0004\u0005+3\u0018\u0001B1we>LAA!'\u0003\u0014\n11k\u00195f[\u0006\fQbZ3u\u0003Z\u0014xnU2iK6\fWC\u0001BP!!\t\u0019G!)\u0003&\u0006\u0005\u0014\u0002\u0002BR\u0003_\u00121!T1q!\ri(qU\u0005\u0004\u0005Ss(\u0001\u0002'p]\u001e\faA]3n_Z,G\u0003BA\"\u0005_Cq!!'\u001e\u0001\u0004\t\t\u0007\u0006\u0003\u0002D\tM\u0006bBAW=\u0001\u0007!Q\u0017\u0019\u0005\u0005o\u0013Y\f\u0005\u0004\u0002z\u0005E&\u0011\u0018\t\u0005\u0003o\u0013Y\f\u0002\u0007\u0003>\nM\u0016\u0011!A\u0001\u0006\u0003\tiLA\u0002`IM\n1aZ3u+\u0011\u0011\u0019Ma2\u0015\t\t\u0015'\u0011\u001a\t\u0005\u0003o\u00139\rB\u0004\u0002<~\u0011\r!!0\t\u000f\u00055v\u00041\u0001\u0003LB1\u0011\u0011PAY\u0005\u000b\f\u0011bZ3u\u001fB$\u0018n\u001c8\u0015\t\tE'q\u001b\t\u0006{\nM\u0017\u0011M\u0005\u0004\u0005+t(AB(qi&|g\u000eC\u0004\u0002\u001a\u0002\u0002\r!!\u0019\u0002'\u001d,GoV5uQN+(m\u001d;jiV$\u0018n\u001c8\u0015\t\tE'Q\u001c\u0005\b\u00033\u000b\u0003\u0019AA1\u0003\u00199W\r^!mYV\u0011!1E\u0001\u0011O\u0016$\u0018\t\u001c7XSRD\u0007K]3gSb$BAa\t\u0003h\"9!\u0011^\u0012A\u0002\u0005\u0005\u0014A\u00029sK\u001aL\u00070\u0001\bhKR,\u00050Z2vi>\u0014XI\u001c<\u0016\u0005\t]\u0011\u0001C4fi\u0006\u0003\b/\u00133\u0016\u0005\u0005\u0005\u0014\u0001C2p]R\f\u0017N\\:\u0015\t\u0005e\"q\u001f\u0005\b\u000333\u0003\u0019AA1\u0003\u0015\u0019Gn\u001c8f\u0003\u00199W\r^3omR!\u0011\u0011\rB\u0000\u0011\u001d\ti\u000f\u000ba\u0001\u0003C\n\u0001C^1mS\u0012\fG/Z*fiRLgnZ:\u0015\u0005\r\u0015\u0001cA?\u0004\b%\u00191\u0011\u0002@\u0003\tUs\u0017\u000e^\u0001\u000ei>$UMY;h'R\u0014\u0018N\\4\u0002\u0013M\u0003\u0018M]6D_:4\u0007cAA\u0004YM1A\u0006`A\u0013\u0007'\u0001Ba!\u0006\u0004\u001c5\u00111q\u0003\u0006\u0005\u00073\tI&\u0001\u0002j_&!\u0011QGB\f)\t\u0019y!A\teKB\u0014XmY1uK\u0012\u001cuN\u001c4jON,\"aa\t\u0011\u0011\u0005\r$\u0011UA1\u0007K\u00012aa\n9\u001b\u0005a#\u0001\u0005#faJ,7-\u0019;fI\u000e{gNZ5h'\u0019ADp!\f\u00022A\u0019Qpa\f\n\u0007\rEbPA\u0004Qe>$Wo\u0019;\u0002\t-,\u0017\u0010I\u0001\bm\u0016\u00148/[8o\u0003!1XM]:j_:\u0004\u0013A\u00053faJ,7-\u0019;j_:lUm]:bO\u0016\f1\u0003Z3qe\u0016\u001c\u0017\r^5p]6+7o]1hK\u0002\"\u0002b!\n\u0004@\r\u000531\t\u0005\b\u00033{\u0004\u0019AA1\u0011\u001d\u0019)d\u0010a\u0001\u0003CBqa!\u000f@\u0001\u0004\t\t'\u0001\u0003d_BLH\u0003CB\u0013\u0007\u0013\u001aYe!\u0014\t\u0013\u0005e\u0005\t%AA\u0002\u0005\u0005\u0004\"CB\u001b\u0001B\u0005\t\u0019AA1\u0011%\u0019I\u0004\u0011I\u0001\u0002\u0004\t\t'\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\rM#\u0006BA1\u0007+Z#aa\u0016\u0011\t\re31M\u0007\u0003\u00077RAa!\u0018\u0004`\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0007Cr\u0018AC1o]>$\u0018\r^5p]&!1QMB.\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIM\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAB8!\u0011\u0019\tha\u001e\u000e\u0005\rM$\u0002BB;\u00033\nA\u0001\\1oO&!\u0011QNB:\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\u0019i\bE\u0002~\u0007\u007fJ1a!!\u007f\u0005\rIe\u000e^\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\t)ma\"\t\u0013\r%e)!AA\u0002\ru\u0014a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0004\u0010B11\u0011SBL\u0003\u000bl!aa%\u000b\u0007\rUe0\u0001\u0006d_2dWm\u0019;j_:LAa!'\u0004\u0014\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\tIda(\t\u0013\r%\u0005*!AA\u0002\u0005\u0015\u0017A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$Baa\u001c\u0004&\"I1\u0011R%\u0002\u0002\u0003\u00071QP\u0001\tQ\u0006\u001c\bnQ8eKR\u00111QP\u0001\ti>\u001cFO]5oOR\u00111qN\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005e21\u0017\u0005\n\u0007\u0013c\u0015\u0011!a\u0001\u0003\u000b\f!\u0003Z3qe\u0016\u001c\u0017\r^3e\u0007>tg-[4tA\u000592m\u001c8gS\u001e\u001cx+\u001b;i\u00032$XM\u001d8bi&4Xm]\u000b\u0003\u0007w\u0003\u0002b!0\u0004D\u000e=4QY\u0007\u0003\u0007\u007fSAa!1\u0004\u0014\u0006I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0005G\u001by\f\u0005\u0004\u0004>\u000e\u001d7\u0011Z\u0005\u0005\u0003w\u001cy\fE\u0002\u0004(Q\u0013q\"\u00117uKJt\u0017\r^3D_:4\u0017nZ\n\u0007)r\u001ci#!\r\u0002\u0017Q\u0014\u0018M\\:mCRLwN\\\u000b\u0003\u0007'\u0004r!`Bk\u0003C\n\t'C\u0002\u0004Xz\u0014\u0011BR;oGRLwN\\\u0019\u0002\u0019Q\u0014\u0018M\\:mCRLwN\u001c\u0011\u0015\u0011\r%7Q\\Bp\u0007CDq!!'\\\u0001\u0004\t\t\u0007C\u0004\u00046m\u0003\r!!\u0019\t\u0013\r=7\f%AA\u0002\rMG\u0003CBe\u0007K\u001c9o!;\t\u0013\u0005eE\f%AA\u0002\u0005\u0005\u0004\"CB\u001b9B\u0005\t\u0019AA1\u0011%\u0019y\r\u0018I\u0001\u0002\u0004\u0019\u0019.\u0006\u0002\u0004n*\"11[B+)\u0011\t)m!=\t\u0013\r%%-!AA\u0002\ruD\u0003BA\u001d\u0007kD\u0011b!#e\u0003\u0003\u0005\r!!2\u0015\t\r=4\u0011 \u0005\n\u0007\u0013+\u0017\u0011!a\u0001\u0007{\"B!!\u000f\u0004~\"I1\u0011\u00125\u0002\u0002\u0003\u0007\u0011QY\u0001\u0019G>tg-[4t/&$\b.\u00117uKJt\u0017\r^5wKN\u0004\u0013aD1mY\u0006cG/\u001a:oCRLg/Z:\u0016\u0005\u0011\u0015\u0001\u0003CA2\u0005C\u000b\t\u0007b\u0002\u0011\u000fu\u0014Y\"!\u0019\u0004J\u0006\u0001\u0012\r\u001c7BYR,'O\\1uSZ,7\u000fI\u0001\u0016SN,\u00050Z2vi>\u00148\u000b^1siV\u00048i\u001c8g)\u0011\tI\u0004b\u0004\t\u000f\u00055H\u00071\u0001\u0002b\u0005y\u0011n]*qCJ\\\u0007k\u001c:u\u0007>tg\r\u0006\u0003\u0002:\u0011U\u0001bBAwk\u0001\u0007\u0011\u0011M\u0001\u0014O\u0016$H)\u001a9sK\u000e\fG/\u001a3D_:4\u0017n\u001a\u000b\u0007\u0005#$Y\u0002\"\b\t\u000f\u0005ee\u00071\u0001\u0002b!9Aq\u0004\u001cA\u0002\u0011\u0005\u0012\u0001B2p]\u001a\u0004\u0002\u0002b\t\u0005&\u0005\u0005\u0014\u0011M\u0007\u0003\u0003+JAAa)\u0002V\u0005)Bn\\4EKB\u0014XmY1uS>tw+\u0019:oS:<G\u0003BB\u0003\tWAq!!'8\u0001\u0004\t\t'\u0001\tEKB\u0014XmY1uK\u0012\u001cuN\u001c4jOB\u00191q\u0005(\u0014\u000b9#\u0019da\u0005\u0011\u0019\u0011UB1HA1\u0003C\n\tg!\n\u000e\u0005\u0011]\"b\u0001C\u001d}\u00069!/\u001e8uS6,\u0017\u0002\u0002C\u001f\to\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c84)\t!y#A\u0003baBd\u0017\u0010\u0006\u0005\u0004&\u0011\u0015Cq\tC%\u0011\u001d\tI*\u0015a\u0001\u0003CBqa!\u000eR\u0001\u0004\t\t\u0007C\u0004\u0004:E\u0003\r!!\u0019\u0002\u000fUt\u0017\r\u001d9msR!Aq\nC,!\u0015i(1\u001bC)!%iH1KA1\u0003C\n\t'C\u0002\u0005Vy\u0014a\u0001V;qY\u0016\u001c\u0004\"\u0003C-%\u0006\u0005\t\u0019AB\u0013\u0003\rAH\u0005M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\t?\u0002Ba!\u001d\u0005b%!A1MB:\u0005\u0019y%M[3di\u0006y\u0011\t\u001c;fe:\fG/Z\"p]\u001aLw\rE\u0002\u0004()\u001cRA\u001bC6\u0007'\u0001B\u0002\"\u000e\u0005<\u0005\u0005\u0014\u0011MBj\u0007\u0013$\"\u0001b\u001a\u0015\u0011\r%G\u0011\u000fC:\tkBq!!'n\u0001\u0004\t\t\u0007C\u0004\u000465\u0004\r!!\u0019\t\u0013\r=W\u000e%AA\u0002\rM\u0017aD1qa2LH\u0005Z3gCVdG\u000fJ\u001a\u0015\t\u0011mDq\u0010\t\u0006{\nMGQ\u0010\t\n{\u0012M\u0013\u0011MA1\u0007'D\u0011\u0002\"\u0017p\u0003\u0003\u0005\ra!3\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134\u0001"
)
public class SparkConf implements ReadOnlySparkConf, Cloneable, Logging, Serializable {
   private transient ConfigReader reader;
   private final ConcurrentHashMap settings;
   private transient Logger org$apache$spark$internal$Logging$$log_;
   private transient volatile boolean bitmap$trans$0;

   public static void logDeprecationWarning(final String key) {
      SparkConf$.MODULE$.logDeprecationWarning(key);
   }

   public static Option getDeprecatedConfig(final String key, final Map conf) {
      return SparkConf$.MODULE$.getDeprecatedConfig(key, conf);
   }

   public static boolean isSparkPortConf(final String name) {
      return SparkConf$.MODULE$.isSparkPortConf(name);
   }

   public static boolean isExecutorStartupConf(final String name) {
      return SparkConf$.MODULE$.isExecutorStartupConf(name);
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

   public String get(final String key) {
      return ReadOnlySparkConf.get$(this, key);
   }

   public String get(final String key, final String defaultValue) {
      return ReadOnlySparkConf.get$(this, key, defaultValue);
   }

   public long getTimeAsSeconds(final String key) {
      return ReadOnlySparkConf.getTimeAsSeconds$(this, key);
   }

   public long getTimeAsSeconds(final String key, final String defaultValue) {
      return ReadOnlySparkConf.getTimeAsSeconds$(this, key, defaultValue);
   }

   public long getTimeAsMs(final String key) {
      return ReadOnlySparkConf.getTimeAsMs$(this, key);
   }

   public long getTimeAsMs(final String key, final String defaultValue) {
      return ReadOnlySparkConf.getTimeAsMs$(this, key, defaultValue);
   }

   public long getSizeAsBytes(final String key) {
      return ReadOnlySparkConf.getSizeAsBytes$(this, key);
   }

   public long getSizeAsBytes(final String key, final String defaultValue) {
      return ReadOnlySparkConf.getSizeAsBytes$(this, key, defaultValue);
   }

   public long getSizeAsBytes(final String key, final long defaultValue) {
      return ReadOnlySparkConf.getSizeAsBytes$(this, key, defaultValue);
   }

   public long getSizeAsKb(final String key) {
      return ReadOnlySparkConf.getSizeAsKb$(this, key);
   }

   public long getSizeAsKb(final String key, final String defaultValue) {
      return ReadOnlySparkConf.getSizeAsKb$(this, key, defaultValue);
   }

   public long getSizeAsMb(final String key) {
      return ReadOnlySparkConf.getSizeAsMb$(this, key);
   }

   public long getSizeAsMb(final String key, final String defaultValue) {
      return ReadOnlySparkConf.getSizeAsMb$(this, key, defaultValue);
   }

   public long getSizeAsGb(final String key) {
      return ReadOnlySparkConf.getSizeAsGb$(this, key);
   }

   public long getSizeAsGb(final String key, final String defaultValue) {
      return ReadOnlySparkConf.getSizeAsGb$(this, key, defaultValue);
   }

   public int getInt(final String key, final int defaultValue) {
      return ReadOnlySparkConf.getInt$(this, key, defaultValue);
   }

   public long getLong(final String key, final long defaultValue) {
      return ReadOnlySparkConf.getLong$(this, key, defaultValue);
   }

   public double getDouble(final String key, final double defaultValue) {
      return ReadOnlySparkConf.getDouble$(this, key, defaultValue);
   }

   public boolean getBoolean(final String key, final boolean defaultValue) {
      return ReadOnlySparkConf.getBoolean$(this, key, defaultValue);
   }

   public boolean contains(final ConfigEntry entry) {
      return ReadOnlySparkConf.contains$(this, entry);
   }

   public Object catchIllegalValue(final String key, final Function0 getValue) {
      return ReadOnlySparkConf.catchIllegalValue$(this, key, getValue);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private ConcurrentHashMap settings() {
      return this.settings;
   }

   private ConfigReader reader$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            ConfigReader _reader = new ConfigReader(new SparkConfigProvider(this.settings()));
            _reader.bindEnv((key) -> .MODULE$.apply(this.getenv(key)));
            this.reader = _reader;
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.reader;
   }

   private ConfigReader reader() {
      return !this.bitmap$trans$0 ? this.reader$lzycompute() : this.reader;
   }

   public SparkConf loadFromSystemProperties(final boolean silent) {
      Utils$.MODULE$.getSystemProperties().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$loadFromSystemProperties$1(check$ifrefutable$1))).withFilter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$loadFromSystemProperties$2(x$5))).foreach((x$6) -> {
         if (x$6 != null) {
            String key = (String)x$6._1();
            String value = (String)x$6._2();
            return this.set(key, value, silent);
         } else {
            throw new MatchError(x$6);
         }
      });
      return this;
   }

   public SparkConf set(final String key, final String value) {
      return this.set(key, value, false);
   }

   public SparkConf set(final String key, final String value, final boolean silent) {
      if (key == null) {
         throw new NullPointerException("null key");
      } else if (value == null) {
         throw new NullPointerException("null value for " + key);
      } else {
         if (!silent) {
            SparkConf$.MODULE$.logDeprecationWarning(key);
         }

         this.settings().put(key, value);
         return this;
      }
   }

   public SparkConf set(final ConfigEntry entry, final Object value) {
      this.set(entry.key(), (String)entry.stringConverter().apply(value));
      return this;
   }

   public SparkConf set(final OptionalConfigEntry entry, final Object value) {
      this.set(entry.key(), (String)entry.rawStringConverter().apply(value));
      return this;
   }

   public SparkConf setMaster(final String master) {
      return this.set("spark.master", master);
   }

   public SparkConf setAppName(final String name) {
      return this.set("spark.app.name", name);
   }

   public SparkConf setJars(final Seq jars) {
      jars.withFilter((jar) -> BoxesRunTime.boxToBoolean($anonfun$setJars$1(jar))).foreach((jar) -> {
         $anonfun$setJars$2(this, jar);
         return BoxedUnit.UNIT;
      });
      return this.set(org.apache.spark.internal.config.package$.MODULE$.JARS(), jars.filter((x$7) -> BoxesRunTime.boxToBoolean($anonfun$setJars$4(x$7))));
   }

   public SparkConf setJars(final String[] jars) {
      return this.setJars((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(jars).toImmutableArraySeq());
   }

   public SparkConf setExecutorEnv(final String variable, final String value) {
      return this.set("spark.executorEnv." + variable, value);
   }

   public SparkConf setExecutorEnv(final Seq variables) {
      variables.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$setExecutorEnv$1(check$ifrefutable$2))).foreach((x$8) -> {
         if (x$8 != null) {
            String k = (String)x$8._1();
            String v = (String)x$8._2();
            return this.setExecutorEnv(k, v);
         } else {
            throw new MatchError(x$8);
         }
      });
      return this;
   }

   public SparkConf setExecutorEnv(final Tuple2[] variables) {
      return this.setExecutorEnv((Seq)org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(variables).toImmutableArraySeq());
   }

   public SparkConf setSparkHome(final String home) {
      return this.set("spark.home", home);
   }

   public SparkConf setAll(final Iterable settings) {
      settings.foreach((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return this.set(k, v);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return this;
   }

   public SparkConf setIfMissing(final String key, final String value) {
      if (this.settings().putIfAbsent(key, value) == null) {
         SparkConf$.MODULE$.logDeprecationWarning(key);
      }

      return this;
   }

   public SparkConf setIfMissing(final ConfigEntry entry, final Object value) {
      if (this.settings().putIfAbsent(entry.key(), entry.stringConverter().apply(value)) == null) {
         SparkConf$.MODULE$.logDeprecationWarning(entry.key());
      }

      return this;
   }

   public SparkConf setIfMissing(final OptionalConfigEntry entry, final Object value) {
      if (this.settings().putIfAbsent(entry.key(), entry.rawStringConverter().apply(value)) == null) {
         SparkConf$.MODULE$.logDeprecationWarning(entry.key());
      }

      return this;
   }

   public SparkConf registerKryoClasses(final Class[] classes) {
      LinkedHashSet allClassNames = new LinkedHashSet();
      allClassNames.$plus$plus$eq((IterableOnce)((IterableOps)((IterableOps)this.get(Kryo$.MODULE$.KRYO_CLASSES_TO_REGISTER())).map((x$9) -> x$9.trim())).filter((x$10) -> BoxesRunTime.boxToBoolean($anonfun$registerKryoClasses$2(x$10))));
      allClassNames.$plus$plus$eq(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])classes), (x$11) -> x$11.getName(), scala.reflect.ClassTag..MODULE$.apply(String.class))));
      this.set((ConfigEntry)Kryo$.MODULE$.KRYO_CLASSES_TO_REGISTER(), (Object)allClassNames.toSeq());
      this.set((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.SERIALIZER(), (Object)KryoSerializer.class.getName());
      return this;
   }

   private final String avroNamespace() {
      return "avro.schema.";
   }

   public SparkConf registerAvroSchemas(final Seq schemas) {
      schemas.foreach((schema) -> this.set("avro.schema." + SchemaNormalization.parsingFingerprint64(schema), schema.toString()));
      return this;
   }

   public scala.collection.immutable.Map getAvroSchema() {
      return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getAll()), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getAvroSchema$1(x0$1)))), (x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return new Tuple2(BoxesRunTime.boxToLong(scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(k.substring("avro.schema.".length())))), v);
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public SparkConf remove(final String key) {
      this.settings().remove(key);
      return this;
   }

   public SparkConf remove(final ConfigEntry entry) {
      return this.remove(entry.key());
   }

   public Object get(final ConfigEntry entry) {
      return entry.readFrom(this.reader());
   }

   public Option getOption(final String key) {
      return .MODULE$.apply(this.settings().get(key)).orElse(() -> SparkConf$.MODULE$.getDeprecatedConfig(key, this.settings()));
   }

   public Option getWithSubstitution(final String key) {
      return this.getOption(key).map((input) -> this.reader().substitute(input));
   }

   public Tuple2[] getAll() {
      return (Tuple2[])((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(this.settings().entrySet()).asScala().map((x) -> new Tuple2(x.getKey(), x.getValue()))).toArray(scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public Tuple2[] getAllWithPrefix(final String prefix) {
      return (Tuple2[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.getAll()), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getAllWithPrefix$1(prefix, x0$1)))), (x0$2) -> {
         if (x0$2 != null) {
            String k = (String)x0$2._1();
            String v = (String)x0$2._2();
            return new Tuple2(k.substring(prefix.length()), v);
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public Seq getExecutorEnv() {
      return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.getAllWithPrefix("spark.executorEnv.")).toImmutableArraySeq();
   }

   public String getAppId() {
      return this.get("spark.app.id");
   }

   public boolean contains(final String key) {
      return this.settings().containsKey(key) || ((IterableOnceOps).MODULE$.option2Iterable(SparkConf$.MODULE$.org$apache$spark$SparkConf$$configsWithAlternatives().get(key)).toSeq().flatten(scala.Predef..MODULE$.$conforms())).exists((alt) -> BoxesRunTime.boxToBoolean($anonfun$contains$1(this, alt)));
   }

   public SparkConf clone() {
      SparkConf cloned = new SparkConf(false);
      scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(this.settings().entrySet()).asScala().foreach((e) -> cloned.set((String)e.getKey(), (String)e.getValue(), true));
      return cloned;
   }

   public String getenv(final String name) {
      return System.getenv(name);
   }

   public void validateSettings() {
      if (this.contains("spark.local.dir")) {
         String msg = "Note that spark.local.dir will be overridden by the value set by the cluster manager (via SPARK_LOCAL_DIRS in standalone/kubernetes and LOCAL_DIRS in YARN).";
         this.logWarning((Function0)(() -> msg));
      }

      scala.sys.package..MODULE$.props().get("spark.driver.libraryPath").foreach((value) -> {
         $anonfun$validateSettings$2(this, value);
         return BoxedUnit.UNIT;
      });
      (new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_JAVA_OPTIONS().key(), new scala.collection.immutable..colon.colon("spark.executor.defaultJavaOptions", scala.collection.immutable.Nil..MODULE$))).foreach((executorOptsKey) -> {
         $anonfun$validateSettings$4(this, executorOptsKey);
         return BoxedUnit.UNIT;
      });
      (new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MEMORY_FRACTION().key(), new scala.collection.immutable..colon.colon(org.apache.spark.internal.config.package$.MODULE$.MEMORY_STORAGE_FRACTION().key(), scala.collection.immutable.Nil..MODULE$))).foreach((key) -> {
         $anonfun$validateSettings$6(this, key);
         return BoxedUnit.UNIT;
      });
      if (this.contains(org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE())) {
         String var2 = (String)this.get(org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE());
         switch (var2 == null ? 0 : var2.hashCode()) {
            case -1357712437:
               if (!"client".equals(var2)) {
                  throw new SparkException("INVALID_SPARK_CONFIG.INVALID_SPARK_SUBMIT_DEPLOY_MODE_KEY", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sparkSubmitDeployModeKey"), org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE().key())}))), (Throwable)null);
               }
               break;
            case 872092154:
               if (!"cluster".equals(var2)) {
                  throw new SparkException("INVALID_SPARK_CONFIG.INVALID_SPARK_SUBMIT_DEPLOY_MODE_KEY", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sparkSubmitDeployModeKey"), org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE().key())}))), (Throwable)null);
               }
               break;
            default:
               throw new SparkException("INVALID_SPARK_CONFIG.INVALID_SPARK_SUBMIT_DEPLOY_MODE_KEY", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("sparkSubmitDeployModeKey"), org.apache.spark.internal.config.package$.MODULE$.SUBMIT_DEPLOY_MODE().key())}))), (Throwable)null);
         }
      }

      if (this.contains((ConfigEntry)org.apache.spark.internal.config.package$.MODULE$.CORES_MAX()) && this.contains(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_CORES())) {
         int totalCores = this.getInt(org.apache.spark.internal.config.package$.MODULE$.CORES_MAX().key(), 1);
         int executorCores = BoxesRunTime.unboxToInt(this.get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_CORES()));
         int leftCores = totalCores % executorCores;
         if (leftCores != 0) {
            this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Total executor cores: "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_CORES_TOTAL..MODULE$, BoxesRunTime.boxToInteger(totalCores))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"is not divisible by cores per executor: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ", "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_CORES..MODULE$, BoxesRunTime.boxToInteger(executorCores))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"the left cores: "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_EXECUTOR_CORES_REMAINING..MODULE$, BoxesRunTime.boxToInteger(leftCores))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"will not be allocated"})))).log(scala.collection.immutable.Nil..MODULE$))));
         }
      }

      boolean encryptionEnabled = BoxesRunTime.unboxToBoolean(this.get(Network$.MODULE$.NETWORK_CRYPTO_ENABLED())) || BoxesRunTime.unboxToBoolean(this.get(org.apache.spark.internal.config.package$.MODULE$.SASL_ENCRYPTION_ENABLED()));
      org.apache.spark.SparkException..MODULE$.require(!encryptionEnabled || BoxesRunTime.unboxToBoolean(this.get(org.apache.spark.internal.config.package$.MODULE$.NETWORK_AUTH_ENABLED())), "INVALID_SPARK_CONFIG.NETWORK_AUTH_MUST_BE_ENABLED", () -> (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("networkAuthEnabledConf"), org.apache.spark.internal.config.package$.MODULE$.NETWORK_AUTH_ENABLED().key())}))));
      long executorTimeoutThresholdMs = BoxesRunTime.unboxToLong(this.get(Network$.MODULE$.NETWORK_TIMEOUT())) * 1000L;
      long executorHeartbeatIntervalMs = BoxesRunTime.unboxToLong(this.get(org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL()));
      org.apache.spark.SparkException..MODULE$.require(executorTimeoutThresholdMs > executorHeartbeatIntervalMs, "INVALID_SPARK_CONFIG.INVALID_EXECUTOR_HEARTBEAT_INTERVAL", () -> (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("networkTimeoutKey"), Network$.MODULE$.NETWORK_TIMEOUT().key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("networkTimeoutValue"), Long.toString(executorTimeoutThresholdMs)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executorHeartbeatIntervalKey"), org.apache.spark.internal.config.package$.MODULE$.EXECUTOR_HEARTBEAT_INTERVAL().key()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executorHeartbeatIntervalValue"), Long.toString(executorHeartbeatIntervalMs))}))));
   }

   public String toDebugString() {
      return ((IterableOnceOps)((IterableOps)Utils$.MODULE$.redact((SparkConf)this, (scala.collection.Seq)scala.Predef..MODULE$.wrapRefArray((Object[])this.getAll())).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$))).map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            return k + "=" + v;
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("\n");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadFromSystemProperties$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$loadFromSystemProperties$2(final Tuple2 x$5) {
      if (x$5 != null) {
         String key = (String)x$5._1();
         return key.startsWith("spark.");
      } else {
         throw new MatchError(x$5);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setJars$1(final String jar) {
      return jar == null;
   }

   // $FF: synthetic method
   public static final void $anonfun$setJars$2(final SparkConf $this, final String jar) {
      $this.logWarning((Function0)(() -> "null jar passed to SparkContext constructor"));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setJars$4(final String x$7) {
      return x$7 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$setExecutorEnv$1(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$registerKryoClasses$2(final String x$10) {
      return !x$10.isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAvroSchema$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith("avro.schema.");
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAllWithPrefix$1(final String prefix$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return k.startsWith(prefix$1);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$contains$1(final SparkConf $this, final AlternateConfig alt) {
      return $this.contains(alt.key());
   }

   // $FF: synthetic method
   public static final void $anonfun$validateSettings$2(final SparkConf $this, final String value) {
      MessageWithContext warning = $this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"\n          |spark.driver.libraryPath was detected (set to '", "').\n          |This is deprecated in Spark 1.2+.\n          |\n          |Please instead use: ", "\n        "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.CONFIG..MODULE$, value), new MDC(org.apache.spark.internal.LogKeys.CONFIG2..MODULE$, org.apache.spark.internal.config.package$.MODULE$.DRIVER_LIBRARY_PATH().key())}))).stripMargin();
      $this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> warning));
   }

   // $FF: synthetic method
   public static final void $anonfun$validateSettings$5(final String executorOptsKey$1, final String javaOpts) {
      if (javaOpts.contains("-Dspark")) {
         throw new SparkException("INVALID_SPARK_CONFIG.INVALID_EXECUTOR_SPARK_OPTIONS", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executorOptsKey"), executorOptsKey$1), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("javaOpts"), javaOpts)}))), (Throwable)null);
      } else if (javaOpts.contains("-Xmx")) {
         throw new SparkException("INVALID_SPARK_CONFIG.INVALID_EXECUTOR_MEMORY_OPTIONS", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("executorOptsKey"), executorOptsKey$1), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("javaOpts"), javaOpts)}))), (Throwable)null);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$validateSettings$4(final SparkConf $this, final String executorOptsKey) {
      $this.getOption(executorOptsKey).foreach((javaOpts) -> {
         $anonfun$validateSettings$5(executorOptsKey, javaOpts);
         return BoxedUnit.UNIT;
      });
   }

   // $FF: synthetic method
   public static final void $anonfun$validateSettings$6(final SparkConf $this, final String key) {
      double value = $this.getDouble(key, (double)0.5F);
      if (!(value > (double)1) && !(value < (double)0)) {
         org.apache.spark.SparkException..MODULE$.require(value >= (double)0 && value <= (double)1, "INVALID_SPARK_CONFIG.INVALID_MEMORY_FRACTION", () -> (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryFractionKey"), key), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("memoryFractionValue"), Double.toString(value))}))));
      } else {
         throw new IllegalArgumentException(key + " should be between 0 and 1 (was '" + value + "').");
      }
   }

   public SparkConf(final boolean loadDefaults) {
      ReadOnlySparkConf.$init$(this);
      Logging.$init$(this);
      this.settings = new ConcurrentHashMap();
      if (loadDefaults) {
         this.loadFromSystemProperties(false);
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

   }

   public SparkConf() {
      this(true);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class DeprecatedConfig implements Product, Serializable {
      private final String key;
      private final String version;
      private final String deprecationMessage;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String key() {
         return this.key;
      }

      public String version() {
         return this.version;
      }

      public String deprecationMessage() {
         return this.deprecationMessage;
      }

      public DeprecatedConfig copy(final String key, final String version, final String deprecationMessage) {
         return new DeprecatedConfig(key, version, deprecationMessage);
      }

      public String copy$default$1() {
         return this.key();
      }

      public String copy$default$2() {
         return this.version();
      }

      public String copy$default$3() {
         return this.deprecationMessage();
      }

      public String productPrefix() {
         return "DeprecatedConfig";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.key();
            }
            case 1 -> {
               return this.version();
            }
            case 2 -> {
               return this.deprecationMessage();
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
         return x$1 instanceof DeprecatedConfig;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "key";
            }
            case 1 -> {
               return "version";
            }
            case 2 -> {
               return "deprecationMessage";
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
         boolean var10;
         if (this != x$1) {
            label63: {
               if (x$1 instanceof DeprecatedConfig) {
                  label56: {
                     DeprecatedConfig var4 = (DeprecatedConfig)x$1;
                     String var10000 = this.key();
                     String var5 = var4.key();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     var10000 = this.version();
                     String var6 = var4.version();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label56;
                     }

                     var10000 = this.deprecationMessage();
                     String var7 = var4.deprecationMessage();
                     if (var10000 == null) {
                        if (var7 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var7)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
                     }
                  }
               }

               var10 = false;
               return var10;
            }
         }

         var10 = true;
         return var10;
      }

      public DeprecatedConfig(final String key, final String version, final String deprecationMessage) {
         this.key = key;
         this.version = version;
         this.deprecationMessage = deprecationMessage;
         Product.$init$(this);
      }
   }

   private static class DeprecatedConfig$ extends AbstractFunction3 implements Serializable {
      public static final DeprecatedConfig$ MODULE$ = new DeprecatedConfig$();

      public final String toString() {
         return "DeprecatedConfig";
      }

      public DeprecatedConfig apply(final String key, final String version, final String deprecationMessage) {
         return new DeprecatedConfig(key, version, deprecationMessage);
      }

      public Option unapply(final DeprecatedConfig x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.key(), x$0.version(), x$0.deprecationMessage())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DeprecatedConfig$.class);
      }

      public DeprecatedConfig$() {
      }
   }

   private static class AlternateConfig implements Product, Serializable {
      private final String key;
      private final String version;
      private final Function1 translation;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public String key() {
         return this.key;
      }

      public String version() {
         return this.version;
      }

      public Function1 translation() {
         return this.translation;
      }

      public AlternateConfig copy(final String key, final String version, final Function1 translation) {
         return new AlternateConfig(key, version, translation);
      }

      public String copy$default$1() {
         return this.key();
      }

      public String copy$default$2() {
         return this.version();
      }

      public Function1 copy$default$3() {
         return this.translation();
      }

      public String productPrefix() {
         return "AlternateConfig";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.key();
            }
            case 1 -> {
               return this.version();
            }
            case 2 -> {
               return this.translation();
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
         return x$1 instanceof AlternateConfig;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "key";
            }
            case 1 -> {
               return "version";
            }
            case 2 -> {
               return "translation";
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
         boolean var10;
         if (this != x$1) {
            label63: {
               if (x$1 instanceof AlternateConfig) {
                  label56: {
                     AlternateConfig var4 = (AlternateConfig)x$1;
                     String var10000 = this.key();
                     String var5 = var4.key();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label56;
                     }

                     var10000 = this.version();
                     String var6 = var4.version();
                     if (var10000 == null) {
                        if (var6 != null) {
                           break label56;
                        }
                     } else if (!var10000.equals(var6)) {
                        break label56;
                     }

                     Function1 var9 = this.translation();
                     Function1 var7 = var4.translation();
                     if (var9 == null) {
                        if (var7 != null) {
                           break label56;
                        }
                     } else if (!var9.equals(var7)) {
                        break label56;
                     }

                     if (var4.canEqual(this)) {
                        break label63;
                     }
                  }
               }

               var10 = false;
               return var10;
            }
         }

         var10 = true;
         return var10;
      }

      public AlternateConfig(final String key, final String version, final Function1 translation) {
         this.key = key;
         this.version = version;
         this.translation = translation;
         Product.$init$(this);
      }
   }

   private static class AlternateConfig$ extends AbstractFunction3 implements Serializable {
      public static final AlternateConfig$ MODULE$ = new AlternateConfig$();

      public Function1 $lessinit$greater$default$3() {
         return null;
      }

      public final String toString() {
         return "AlternateConfig";
      }

      public AlternateConfig apply(final String key, final String version, final Function1 translation) {
         return new AlternateConfig(key, version, translation);
      }

      public Function1 apply$default$3() {
         return null;
      }

      public Option unapply(final AlternateConfig x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(x$0.key(), x$0.version(), x$0.translation())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AlternateConfig$.class);
      }

      public AlternateConfig$() {
      }
   }
}
