package breeze.math;

import breeze.generic.UFunc;
import breeze.numerics.package$floor$floorDoubleImpl$;
import breeze.storage.Zero;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.collection.Iterator;
import scala.math.Fractional;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.math.package.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Nothing;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005%\rba\u0002BM\u00057\u0003%Q\u0015\u0005\u000b\u0005#\u0004!Q3A\u0005\u0002\tM\u0007B\u0003Bn\u0001\tE\t\u0015!\u0003\u0003V\"Q!Q\u001c\u0001\u0003\u0016\u0004%\tAa5\t\u0015\t}\u0007A!E!\u0002\u0013\u0011)\u000eC\u0004\u0003b\u0002!\tAa9\t\u000f\t5\b\u0001\"\u0011\u0003p\"91\u0011\u0001\u0001\u0005\u0002\r\r\u0001bBB\u0003\u0001\u0011\u000511\u0001\u0005\b\u0007\u000f\u0001A\u0011AB\u0005\u0011\u001d\u00199\u0001\u0001C\u0001\u0007\u001fAqaa\u0002\u0001\t\u0003\u0019I\u0002C\u0004\u0004\b\u0001!\taa\t\t\u000f\r\u001d\u0002\u0001\"\u0001\u0004*!91q\u0005\u0001\u0005\u0002\r5\u0002bBB\u0014\u0001\u0011\u00051\u0011\u0007\u0005\b\u0007O\u0001A\u0011AB\u001b\u0011\u001d\u0019I\u0004\u0001C\u0001\u0007wAqa!\u000f\u0001\t\u0003\u0019y\u0004C\u0004\u0004:\u0001!\taa\u0011\t\u000f\re\u0002\u0001\"\u0001\u0004H!911\n\u0001\u0005\u0002\r5\u0003bBB&\u0001\u0011\u00051\u0011\u000b\u0005\b\u0007\u0017\u0002A\u0011AB+\u0011\u001d\u0019Y\u0005\u0001C\u0001\u00073Bqa!\u0018\u0001\t\u0003\u0019y\u0006C\u0004\u0004^\u0001!\taa\u0019\t\u000f\ru\u0003\u0001\"\u0001\u0004h!91Q\f\u0001\u0005\u0002\r-\u0004bBB8\u0001\u0011\u00051\u0011\u000f\u0005\b\u0007g\u0002A\u0011\u0001Bj\u0011\u001d\u0019)\b\u0001C\u0001\u0007cBqaa\u001e\u0001\t\u0003\u0019\t\bC\u0004\u0004z\u0001!\ta!\u001d\t\u000f\rm\u0004\u0001\"\u0001\u0004~!911\u0010\u0001\u0005\u0002\r\r\u0005bBBD\u0001\u0011\u00053\u0011\u0012\u0005\b\u00073\u0003A\u0011IBN\u0011%\u0019i\nAA\u0001\n\u0003\u0019y\nC\u0005\u0004&\u0002\t\n\u0011\"\u0001\u0004(\"I1Q\u0018\u0001\u0012\u0002\u0013\u00051q\u0015\u0005\n\u0007\u007f\u0003\u0011\u0011!C!\u0007\u0003D\u0011b!5\u0001\u0003\u0003%\taa5\t\u0013\rU\u0007!!A\u0005\u0002\r]\u0007\"CBo\u0001\u0005\u0005I\u0011IBp\u0011%\u0019i\u000fAA\u0001\n\u0003\u0019y\u000fC\u0005\u0004t\u0002\t\t\u0011\"\u0011\u0004v\u001eA1\u0011 BN\u0011\u0003\u0019YP\u0002\u0005\u0003\u001a\nm\u0005\u0012AB\u007f\u0011\u001d\u0011\t\u000f\rC\u0001\t\u0013A\u0011\u0002b\u00031\u0005\u0004%\ta!\u001d\t\u0011\u00115\u0001\u0007)A\u0005\u0005KD\u0011\u0002b\u00041\u0005\u0004%\ta!\u001d\t\u0011\u0011E\u0001\u0007)A\u0005\u0005KD\u0011\u0002b\u00051\u0005\u0004%\ta!\u001d\t\u0011\u0011U\u0001\u0007)A\u0005\u0005KD\u0011\u0002b\u00061\u0005\u0004%\ta!\u001d\t\u0011\u0011e\u0001\u0007)A\u0005\u0005K<q\u0001b\u00071\u0011\u0007!iBB\u0004\u0005\"AB\t\u0001b\t\t\u000f\t\u00058\b\"\u0001\u0005,!9A1B\u001e\u0005\u0002\rE\u0004b\u0002C\bw\u0011\u00051\u0011\u000f\u0005\b\t'YD\u0011AB9\u0011\u001d!ic\u000fC\u0001\t_Aq\u0001b\u000e<\t\u0003!I\u0004C\u0004\u0005@m\"\t\u0001\"\u0011\t\u000f\u0011\u001d3\b\"\u0001\u0005J!9AqJ\u001e\u0005\u0002\u0011E\u0003b\u0002C,w\u0011\u0005A\u0011\f\u0005\b\u0007\u000fYD\u0011\u0001C0\u0011\u001d\u00199c\u000fC\u0001\tKBqa!\u000f<\t\u0003!Y\u0007C\u0004\u0004Lm\"\t\u0001\"\u001d\t\u000f\u0011]4\b\"\u0001\u0005z!9A1Q\u001e\u0005\u0002\u0011\u0015\u0005\"\u0003CEw\t\u0007I\u0011\u0001CF\u0011!!Ij\u000fQ\u0001\n\u00115\u0005\"\u0003CNw\t\u0007I\u0011\u0001CO\u0011!!Yk\u000fQ\u0001\n\u0011}\u0005\"\u0003CWw\t\u0007I1\u0001CX\u0011!!ym\u000fQ\u0001\n\u0011E\u0006b\u0002Ciw\u0011\u0005C1\u001b\u0005\b\u0007wZD\u0011\u0001Co\u0011\u001d\u0019if\u000fC\u0001\tGD\u0011\u0002\";<\u0003\u0003%I\u0001b;\t\u0013\u0011M\bG1A\u0005\u0004\u0011U\b\u0002\u0003C\u007fa\u0001\u0006I\u0001b>\t\u0013\u0011}\bG1A\u0005\u0004\u0011u\u0005\u0002CC\u0001a\u0001\u0006I\u0001b(\b\u000f\u0015\r\u0001\u0007c\u0001\u0006\u0006\u00199Qq\u0001\u0019\t\u0002\u0015%\u0001b\u0002Bq7\u0012\u0005Q\u0011\u0004\u0005\b\u000b7YF\u0011AC\u000f\u0011%!IoWA\u0001\n\u0013!YoB\u0004\u0006$AB\u0019!\"\n\u0007\u000f\u0015\u001d\u0002\u0007#\u0001\u0006*!9!\u0011\u001d1\u0005\u0002\u0015]\u0002bBC\u000eA\u0012\u0005Q\u0011\b\u0005\n\tS\u0004\u0017\u0011!C\u0005\tW<q!b\u00101\u0011\u0007)\tEB\u0004\u0006DAB\t!\"\u0012\t\u000f\t\u0005X\r\"\u0001\u0006J!9Q1D3\u0005\u0002\u0015-\u0003\"\u0003CuK\u0006\u0005I\u0011\u0002Cv\u000f\u001d)\t\u0006\rE\u0002\u000b'2q!\"\u00161\u0011\u0003)9\u0006C\u0004\u0003b*$\t!b\u0017\t\u000f\u0015m!\u000e\"\u0001\u0006^!IA\u0011\u001e6\u0002\u0002\u0013%A1^\u0004\b\u000bG\u0002\u00042AC3\r\u001d)9\u0007\rE\u0001\u000bSBqA!9p\t\u0003)i\u0007C\u0004\u0006\u001c=$\t!b\u001c\t\u0013\u0011%x.!A\u0005\n\u0011-xaBC;a!\rQq\u000f\u0004\b\u000bs\u0002\u0004\u0012AC>\u0011\u001d\u0011\t\u000f\u001eC\u0001\u000b\u007fBq!b\u0007u\t\u0003)\t\tC\u0005\u0005jR\f\t\u0011\"\u0003\u0005l\u001e9Qq\u0011\u0019\t\u0004\u0015%eaBCFa!\u0005QQ\u0012\u0005\b\u0005CLH\u0011ACI\u0011\u001d)Y\"\u001fC\u0001\u000b'C\u0011\u0002\";z\u0003\u0003%I\u0001b;\b\u000f\u0015e\u0005\u0007c\u0001\u0006\u001c\u001a9QQ\u0014\u0019\t\u0002\u0015}\u0005b\u0002Bq}\u0012\u0005Q1\u0015\u0005\b\u000b7qH\u0011ACS\u0011%!IO`A\u0001\n\u0013!YoB\u0004\u0006,BB\u0019!\",\u0007\u000f\u0015=\u0006\u0007#\u0001\u00062\"A!\u0011]A\u0004\t\u0003)Y\f\u0003\u0005\u0006\u001c\u0005\u001dA\u0011AC_\u0011)!I/a\u0002\u0002\u0002\u0013%A1^\u0004\b\u000b\u0007\u0004\u00042ACc\r\u001d)9\r\rE\u0001\u000b\u0013D\u0001B!9\u0002\u0012\u0011\u0005QQ\u001a\u0005\t\u000b7\t\t\u0002\"\u0001\u0006P\"QA\u0011^A\t\u0003\u0003%I\u0001b;\b\u000f\u0015U\u0007\u0007c\u0001\u0006X\u001a9Q\u0011\u001c\u0019\t\u0002\u0015m\u0007\u0002\u0003Bq\u00037!\t!b8\t\u0011\u0015m\u00111\u0004C\u0001\u000bCD!\u0002\";\u0002\u001c\u0005\u0005I\u0011\u0002Cv\u000f\u001d)9\u000f\rE\u0002\u000bS4q!b;1\u0011\u0003)i\u000f\u0003\u0005\u0003b\u0006\u0015B\u0011ACy\u0011!)Y\"!\n\u0005\u0002\u0015M\bB\u0003Cu\u0003K\t\t\u0011\"\u0003\u0005l\u001e9Q\u0011 \u0019\t\u0004\u0015mhaBC\u007fa!\u0005Qq \u0005\t\u0005C\fy\u0003\"\u0001\u0007\u0004!AQ1DA\u0018\t\u00031)\u0001\u0003\u0006\u0005j\u0006=\u0012\u0011!C\u0005\tW<qAb\u00031\u0011\u00071iAB\u0004\u0007\u0010AB\tA\"\u0005\t\u0011\t\u0005\u0018\u0011\bC\u0001\r+A\u0001\"b\u0007\u0002:\u0011\u0005aq\u0003\u0005\u000b\tS\fI$!A\u0005\n\u0011-xa\u0002D\u000fa!\raq\u0004\u0004\b\rC\u0001\u0004\u0012\u0001D\u0012\u0011!\u0011\t/a\u0011\u0005\u0002\u0019\u001d\u0002\u0002CC\u000e\u0003\u0007\"\tA\"\u000b\t\u0015\u0011%\u00181IA\u0001\n\u0013!YoB\u0004\u00070AB\u0019A\"\r\u0007\u000f\u0019M\u0002\u0007#\u0001\u00076!A!\u0011]A'\t\u00031y\u0004\u0003\u0005\u0006\u001c\u00055C\u0011\u0001D!\u0011)!I/!\u0014\u0002\u0002\u0013%A1^\u0004\b\r\u000f\u0002\u00042\u0001D%\r\u001d1Y\u0005\rE\u0001\r\u001bB\u0001B!9\u0002X\u0011\u0005a\u0011\u000b\u0005\t\u000b7\t9\u0006\"\u0001\u0007T!QA\u0011^A,\u0003\u0003%I\u0001b;\b\u000f\u0019e\u0003\u0007c\u0001\u0007\\\u00199aQ\f\u0019\t\u0002\u0019}\u0003\u0002\u0003Bq\u0003C\"\tAb\u0019\t\u0011\u0015m\u0011\u0011\rC\u0001\rKB!\u0002\";\u0002b\u0005\u0005I\u0011\u0002Cv\u000f\u001d1Y\u0007\rE\u0002\r[2qAb\u001c1\u0011\u00031\t\b\u0003\u0005\u0003b\u0006-D\u0011\u0001D;\u0011!)Y\"a\u001b\u0005\u0002\u0019]\u0004B\u0003Cu\u0003W\n\t\u0011\"\u0003\u0005l\u001e9aQ\u0010\u0019\t\u0004\u0019}da\u0002DAa!\u0005a1\u0011\u0005\t\u0005C\f)\b\"\u0001\u0007\b\"AQ1DA;\t\u00031I\t\u0003\u0006\u0005j\u0006U\u0014\u0011!C\u0005\tW<qAb$1\u0011\u00071\tJB\u0004\u0007\u0014BB\tA\"&\t\u0011\t\u0005\u0018q\u0010C\u0001\r3C\u0001\"b\u0007\u0002\u0000\u0011\u0005a1\u0014\u0005\u000b\tS\fy(!A\u0005\n\u0011-xa\u0002DQa!\ra1\u0015\u0004\b\rK\u0003\u0004\u0012\u0001DT\u0011!\u0011\t/!#\u0005\u0002\u0019-\u0006\u0002CC\u000e\u0003\u0013#\tA\",\t\u0015\u0011%\u0018\u0011RA\u0001\n\u0013!YoB\u0004\u00074BB\u0019A\".\u0007\u000f\u0019]\u0006\u0007#\u0001\u0007:\"A!\u0011]AJ\t\u00031\u0019\r\u0003\u0005\u0006\u001c\u0005ME\u0011\u0001Dc\u0011)!I/a%\u0002\u0002\u0013%A1^\u0004\b\r\u0017\u0004\u00042\u0001Dg\r\u001d1y\r\rE\u0001\r#D\u0001B!9\u0002\u001e\u0012\u0005aQ\u001b\u0005\t\u000b7\ti\n\"\u0001\u0007X\"QA\u0011^AO\u0003\u0003%I\u0001b;\b\u000f\u0019u\u0007\u0007c\u0001\u0007`\u001a9a\u0011\u001d\u0019\t\u0002\u0019\r\b\u0002\u0003Bq\u0003O#\tAb:\t\u0011\u0015m\u0011q\u0015C\u0001\rSD!\u0002\";\u0002(\u0006\u0005I\u0011\u0002Cv\u000f\u001d1y\u000f\rE\u0002\rc4qAb=1\u0011\u00031)\u0010\u0003\u0005\u0003b\u0006EF\u0011\u0001D}\u0011!)Y\"!-\u0005\u0002\u0019m\bB\u0003Cu\u0003c\u000b\t\u0011\"\u0003\u0005l\u001e9q\u0011\u0001\u0019\t\u0004\u001d\raaBD\u0003a!\u0005qq\u0001\u0005\t\u0005C\fY\f\"\u0001\b\f!AQ1DA^\t\u00039i\u0001\u0003\u0006\u0005j\u0006m\u0016\u0011!C\u0005\tW<qab\u00051\u0011\u00079)BB\u0004\b\u0018AB\ta\"\u0007\t\u0011\t\u0005\u0018Q\u0019C\u0001\u000f;A\u0001\"b\u0007\u0002F\u0012\u0005qq\u0004\u0005\u000b\tS\f)-!A\u0005\n\u0011-xaBD\u0013a!\rqq\u0005\u0004\b\u000fS\u0001\u0004\u0012AD\u0016\u0011!\u0011\t/a4\u0005\u0002\u001d=\u0002\u0002CC\u000e\u0003\u001f$\ta\"\r\t\u0015\u0011%\u0018qZA\u0001\n\u0013!YoB\u0004\b8AB\u0019a\"\u000f\u0007\u000f\u001dm\u0002\u0007#\u0001\b>!A!\u0011]Am\t\u000399\u0005\u0003\u0005\u0006\u001c\u0005eG\u0011AD%\u0011)!I/!7\u0002\u0002\u0013%A1^\u0004\b\u000f\u001f\u0002\u00042AD)\r\u001d9\u0019\u0006\rE\u0001\u000f+B\u0001B!9\u0002d\u0012\u0005q\u0011\f\u0005\t\u000b7\t\u0019\u000f\"\u0001\b\\!QA\u0011^Ar\u0003\u0003%I\u0001b;\b\u000f\u001d\u0005\u0004\u0007c\u0001\bd\u00199qQ\r\u0019\t\u0002\u001d\u001d\u0004\u0002\u0003Bq\u0003[$\tab\u001b\t\u0011\u0015m\u0011Q\u001eC\u0001\u000f[B!\u0002\";\u0002n\u0006\u0005I\u0011\u0002Cv\u000f\u001d9\u0019\b\rE\u0002\u000fk2qab\u001e1\u0011\u00039I\b\u0003\u0005\u0003b\u0006]H\u0011AD?\u0011!)Y\"a>\u0005\u0002\u001d}\u0004B\u0003Cu\u0003o\f\t\u0011\"\u0003\u0005l\u001e9qQ\u0011\u0019\t\u0004\u001d\u001deaBDEa!\u0005q1\u0012\u0005\t\u0005C\u0014\t\u0001\"\u0001\b\u0010\"AQ1\u0004B\u0001\t\u00039\t\n\u0003\u0006\u0005j\n\u0005\u0011\u0011!C\u0005\tW<qab&1\u0011\u00079IJB\u0004\b\u001cBB\ta\"(\t\u0011\t\u0005(1\u0002C\u0001\u000fCC\u0001\"b\u0007\u0003\f\u0011\u0005q1\u0015\u0005\u000b\tS\u0014Y!!A\u0005\n\u0011-xaBDUa!\rq1\u0016\u0004\b\u000f[\u0003\u0004\u0012ADX\u0011!\u0011\tO!\u0006\u0005\u0002\u001dM\u0006\u0002CC\u000e\u0005+!\ta\".\t\u0015\u0011%(QCA\u0001\n\u0013!YoB\u0004\b<BB\u0019a\"0\u0007\u000f\u001d}\u0006\u0007#\u0001\bB\"A!\u0011\u001dB\u0010\t\u00039Y\r\u0003\u0005\u0006\u001c\t}A\u0011ADg\u0011)!IOa\b\u0002\u0002\u0013%A1^\u0004\b\u000f'\u0004\u00042ADk\r\u001d99\u000e\rE\u0001\u000f3D\u0001B!9\u0003*\u0011\u0005qQ\u001c\u0005\t\u000b7\u0011I\u0003\"\u0001\b`\"QA\u0011\u001eB\u0015\u0003\u0003%I\u0001b;\u0007\u0013\u001d\u0015\b\u0007%A\u0002\u0002\u001d\u001d\b\u0002CDx\u0005c!\ta\"=\t\u0011\u001de(\u0011\u0007C\u0001\u000fwD\u0001\u0002#\u0002\u00032\u0011\u0005\u0001r\u0001\u0005\t\u0011\u001b\u0011\t\u0004\"\u0001\t\u0010!A\u0001R\u0003B\u0019\t\u0003A9\u0002\u0003\u0005\t\u001c\tEB\u0011\u0001E\u000f\u0011!A\tC!\r\u0005\u0002!\r\u0002\u0002\u0003E\u0014\u0005c!\t\u0001#\u000b\t\u0011!M\"\u0011\u0007C\u0001\u0011kA\u0001\u0002b\u001e\u00032\u0011\u0005\u0001\u0012\b\u0005\t\u0011{\u0011\t\u0004\"\u0003\t@\u0019I\u00012\t\u0019\u0011\u0002\u0007\u0005\u0001R\t\u0005\t\u000f_\u0014I\u0005\"\u0001\br\"A\u0001r\nB%\t\u0003A\tFB\u0005\tXA\u0002\n1!\u0001\tZ!Aqq\u001eB(\t\u00039\t\u0010\u0003\u0005\tb\t=C\u0011\tE2\u000f\u001dAI\u0007\rE\u0002\u0011W2q\u0001c\u00111\u0011\u0003Ai\u0007\u0003\u0005\u0003b\n]C\u0011\u0001E:\u0011!A)Ha\u0016\u0005\u0002!]\u0004b\u0003EB\u0005/B)\u0019!C\u0005\u0011\u000bC!\u0002\";\u0003X\u0005\u0005I\u0011\u0002Cv\u000f\u001dA)\n\rE\u0002\u0011/3q\u0001#'1\u0011\u0003AY\n\u0003\u0005\u0003b\n\rD\u0011\u0001E[\u0011!)YBa\u0019\u0005\u0002!]\u0006B\u0003Cu\u0005G\n\t\u0011\"\u0003\u0005l\u001e9\u00012\u0018\u0019\t\u0004!ufa\u0002E`a!\u0005\u0001\u0012\u0019\u0005\t\u0005C\u0014i\u0007\"\u0001\tJ\"AQ1\u0004B7\t\u0003AY\r\u0003\u0006\u0005j\n5\u0014\u0011!C\u0005\tW<q\u0001c41\u0011\u0007A\tNB\u0004\tTBB\t\u0001#6\t\u0011\t\u0005(q\u000fC\u0001\u0011;D\u0001\"b\u0007\u0003x\u0011\u0005\u0001r\u001c\u0005\u000b\tS\u00149(!A\u0005\n\u0011-xa\u0002Era!\r\u0001R\u001d\u0004\b\u0011O\u0004\u0004\u0012\u0001Eu\u0011!\u0011\tO!!\u0005\u0002!E\b\u0002CC\u000e\u0005\u0003#\t\u0001c=\t\u0015\u0011%(\u0011QA\u0001\n\u0013!YoB\u0004\t|BB\u0019\u0001#@\u0007\u000f!}\b\u0007#\u0001\n\u0002!A!\u0011\u001dBF\t\u0003I)\u0001\u0003\u0005\u0006\u001c\t-E\u0011AE\u0004\u0011)!IOa#\u0002\u0002\u0013%A1\u001e\u0005\n\u000b7\u0001\u0014\u0011!CA\u0013\u001bA\u0011\"c\u00051\u0003\u0003%\t)#\u0006\t\u0013\u0011%\b'!A\u0005\n\u0011-(aB\"p[BdW\r\u001f\u0006\u0005\u0005;\u0013y*\u0001\u0003nCRD'B\u0001BQ\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0002\u0001\u0003(\nM&\u0011\u0018\t\u0005\u0005S\u0013y+\u0004\u0002\u0003,*\u0011!QV\u0001\u0006g\u000e\fG.Y\u0005\u0005\u0005c\u0013YK\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0005S\u0013),\u0003\u0003\u00038\n-&a\u0002)s_\u0012,8\r\u001e\t\u0005\u0005w\u0013YM\u0004\u0003\u0003>\n\u001dg\u0002\u0002B`\u0005\u000bl!A!1\u000b\t\t\r'1U\u0001\u0007yI|w\u000e\u001e \n\u0005\t5\u0016\u0002\u0002Be\u0005W\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0003N\n='\u0001D*fe&\fG.\u001b>bE2,'\u0002\u0002Be\u0005W\u000bAA]3bYV\u0011!Q\u001b\t\u0005\u0005S\u00139.\u0003\u0003\u0003Z\n-&A\u0002#pk\ndW-A\u0003sK\u0006d\u0007%\u0001\u0003j[\u0006<\u0017!B5nC\u001e\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0004\u0003f\n%(1\u001e\t\u0004\u0005O\u0004QB\u0001BN\u0011\u001d\u0011\t.\u0002a\u0001\u0005+DqA!8\u0006\u0001\u0004\u0011).\u0001\u0005u_N#(/\u001b8h)\t\u0011\t\u0010\u0005\u0003\u0003t\nmh\u0002\u0002B{\u0005o\u0004BAa0\u0003,&!!\u0011 BV\u0003\u0019\u0001&/\u001a3fM&!!Q B\u0000\u0005\u0019\u0019FO]5oO*!!\u0011 BV\u0003\t\u0011X\r\u0006\u0002\u0003V\u0006\u0011\u0011.\\\u0001\u0006IAdWo\u001d\u000b\u0005\u0005K\u001cY\u0001C\u0004\u0004\u000e%\u0001\rA!:\u0002\tQD\u0017\r\u001e\u000b\u0005\u0005K\u001c\t\u0002C\u0004\u0004\u000e)\u0001\raa\u0005\u0011\t\t%6QC\u0005\u0005\u0007/\u0011YKA\u0002J]R$BA!:\u0004\u001c!91QB\u0006A\u0002\ru\u0001\u0003\u0002BU\u0007?IAa!\t\u0003,\n)a\t\\8biR!!Q]B\u0013\u0011\u001d\u0019i\u0001\u0004a\u0001\u0005+\fa\u0001J7j]V\u001cH\u0003\u0002Bs\u0007WAqa!\u0004\u000e\u0001\u0004\u0011)\u000f\u0006\u0003\u0003f\u000e=\u0002bBB\u0007\u001d\u0001\u000711\u0003\u000b\u0005\u0005K\u001c\u0019\u0004C\u0004\u0004\u000e=\u0001\ra!\b\u0015\t\t\u00158q\u0007\u0005\b\u0007\u001b\u0001\u0002\u0019\u0001Bk\u0003\u0019!C/[7fgR!!Q]B\u001f\u0011\u001d\u0019i!\u0005a\u0001\u0005K$BA!:\u0004B!91Q\u0002\nA\u0002\rMA\u0003\u0002Bs\u0007\u000bBqa!\u0004\u0014\u0001\u0004\u0019i\u0002\u0006\u0003\u0003f\u000e%\u0003bBB\u0007)\u0001\u0007!Q[\u0001\u0005I\u0011Lg\u000f\u0006\u0003\u0003f\u000e=\u0003bBB\u0007+\u0001\u0007!Q\u001d\u000b\u0005\u0005K\u001c\u0019\u0006C\u0004\u0004\u000eY\u0001\raa\u0005\u0015\t\t\u00158q\u000b\u0005\b\u0007\u001b9\u0002\u0019AB\u000f)\u0011\u0011)oa\u0017\t\u000f\r5\u0001\u00041\u0001\u0003V\u0006AA\u0005]3sG\u0016tG\u000f\u0006\u0003\u0003f\u000e\u0005\u0004bBB\u00073\u0001\u0007!Q\u001d\u000b\u0005\u0005K\u001c)\u0007C\u0004\u0004\u000ei\u0001\raa\u0005\u0015\t\t\u00158\u0011\u000e\u0005\b\u0007\u001bY\u0002\u0019AB\u000f)\u0011\u0011)o!\u001c\t\u000f\r5A\u00041\u0001\u0003V\u0006aQO\\1ss~#S.\u001b8vgV\u0011!Q]\u0001\u0004C\n\u001c\u0018!C2p]*,x-\u0019;f\u0003\rawnZ\u0001\u0004Kb\u0004\u0018a\u00019poR!!Q]B@\u0011\u001d\u0019\tI\ta\u0001\u0005+\f\u0011A\u0019\u000b\u0005\u0005K\u001c)\tC\u0004\u0004\u0002\u000e\u0002\rA!:\u0002\r\u0015\fX/\u00197t)\u0011\u0019Yi!%\u0011\t\t%6QR\u0005\u0005\u0007\u001f\u0013YKA\u0004C_>dW-\u00198\t\u000f\r5A\u00051\u0001\u0004\u0014B!!\u0011VBK\u0013\u0011\u00199Ja+\u0003\u0007\u0005s\u00170\u0001\u0005iCND7i\u001c3f)\t\u0019\u0019\"\u0001\u0003d_BLHC\u0002Bs\u0007C\u001b\u0019\u000bC\u0005\u0003R\u001a\u0002\n\u00111\u0001\u0003V\"I!Q\u001c\u0014\u0011\u0002\u0003\u0007!Q[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\u0019IK\u000b\u0003\u0003V\u000e-6FABW!\u0011\u0019yk!/\u000e\u0005\rE&\u0002BBZ\u0007k\u000b\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\t\r]&1V\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BB^\u0007c\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCABb!\u0011\u0019)ma4\u000e\u0005\r\u001d'\u0002BBe\u0007\u0017\fA\u0001\\1oO*\u00111QZ\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003~\u000e\u001d\u0017\u0001\u00049s_\u0012,8\r^!sSRLXCAB\n\u00039\u0001(o\u001c3vGR,E.Z7f]R$Baa%\u0004Z\"I11\\\u0016\u0002\u0002\u0003\u000711C\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\r\u0005\bCBBr\u0007S\u001c\u0019*\u0004\u0002\u0004f*!1q\u001dBV\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0007W\u001c)O\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BBF\u0007cD\u0011ba7.\u0003\u0003\u0005\raa%\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0007\u0007\u001c9\u0010C\u0005\u0004\\:\n\t\u00111\u0001\u0004\u0014\u000591i\\7qY\u0016D\bc\u0001BtaM)\u0001Ga*\u0004\u0000B!A\u0011\u0001C\u0004\u001b\t!\u0019A\u0003\u0003\u0005\u0006\r-\u0017AA5p\u0013\u0011\u0011i\rb\u0001\u0015\u0005\rm\u0018\u0001\u0002>fe>\fQA_3s_\u0002\n1a\u001c8f\u0003\u0011yg.\u001a\u0011\u0002\u00079\fg.\u0001\u0003oC:\u0004\u0013!A5\u0002\u0005%\u0004\u0013AB:dC2\f'\u000fE\u0002\u0005 mj\u0011\u0001\r\u0002\u0007g\u000e\fG.\u0019:\u0014\u000bm\u00129\u000b\"\n\u0011\r\t\u001dHq\u0005Bs\u0013\u0011!ICa'\u0003\u000b\u0019KW\r\u001c3\u0015\u0005\u0011u\u0011A\u0002\u0013fc\u0012*\u0017\u000f\u0006\u0004\u0004\f\u0012EBQ\u0007\u0005\b\tg\u0001\u0005\u0019\u0001Bs\u0003\u0005\t\u0007bBBA\u0001\u0002\u0007!Q]\u0001\tI\t\fgn\u001a\u0013fcR111\u0012C\u001e\t{Aq\u0001b\rB\u0001\u0004\u0011)\u000fC\u0004\u0004\u0002\u0006\u0003\rA!:\u0002\u0011\u0011:'/Z1uKJ$baa#\u0005D\u0011\u0015\u0003b\u0002C\u001a\u0005\u0002\u0007!Q\u001d\u0005\b\u0007\u0003\u0013\u0005\u0019\u0001Bs\u0003-!sM]3bi\u0016\u0014H%Z9\u0015\r\r-E1\nC'\u0011\u001d!\u0019d\u0011a\u0001\u0005KDqa!!D\u0001\u0004\u0011)/A\u0003%Y\u0016\u001c8\u000f\u0006\u0004\u0004\f\u0012MCQ\u000b\u0005\b\tg!\u0005\u0019\u0001Bs\u0011\u001d\u0019\t\t\u0012a\u0001\u0005K\f\u0001\u0002\n7fgN$S-\u001d\u000b\u0007\u0007\u0017#Y\u0006\"\u0018\t\u000f\u0011MR\t1\u0001\u0003f\"91\u0011Q#A\u0002\t\u0015HC\u0002Bs\tC\"\u0019\u0007C\u0004\u00054\u0019\u0003\rA!:\t\u000f\r\u0005e\t1\u0001\u0003fR1!Q\u001dC4\tSBq\u0001b\rH\u0001\u0004\u0011)\u000fC\u0004\u0004\u0002\u001e\u0003\rA!:\u0015\r\t\u0015HQ\u000eC8\u0011\u001d!\u0019\u0004\u0013a\u0001\u0005KDqa!!I\u0001\u0004\u0011)\u000f\u0006\u0004\u0003f\u0012MDQ\u000f\u0005\b\tgI\u0005\u0019\u0001Bs\u0011\u001d\u0019\t)\u0013a\u0001\u0005K\f\u0001\u0002^8E_V\u0014G.\u001a\u000b\u0005\tw\"\t\t\u0005\u0003\u0003*\u0012u\u0014\u0002\u0002C@\u0005W\u0013qAT8uQ&tw\rC\u0004\u00054)\u0003\rA!:\u0002\u000b%\u001ch*\u0019(\u0015\t\r-Eq\u0011\u0005\b\tgY\u0005\u0019\u0001Bs\u0003!i\u0017M\\5gKN$XC\u0001CG!\u0019!y\t\"&\u0003f6\u0011A\u0011\u0013\u0006\u0005\t'\u0013Y+A\u0004sK\u001adWm\u0019;\n\t\u0011]E\u0011\u0013\u0002\t\u00072\f7o\u001d+bO\u0006IQ.\u00198jM\u0016\u001cH\u000fI\u0001\u0012I\u00164\u0017-\u001e7u\u0003J\u0014\u0018-\u001f,bYV,WC\u0001CP!\u0019!\t\u000bb*\u0003f6\u0011A1\u0015\u0006\u0005\tK\u0013y*A\u0004ti>\u0014\u0018mZ3\n\t\u0011%F1\u0015\u0002\u00055\u0016\u0014x.\u0001\neK\u001a\fW\u000f\u001c;BeJ\f\u0017PV1mk\u0016\u0004\u0013\u0001\u00038pe6LU\u000e\u001d7\u0016\u0005\u0011E\u0006\u0003\u0003CZ\t\u0007\u0014)O!6\u000f\t\u0011UFQ\u0018\b\u0005\to#I,\u0004\u0002\u0003 &!A1\u0018BP\u0003\u0019a\u0017N\\1mO&!Aq\u0018Ca\u0003\u0011qwN]7\u000b\t\u0011m&qT\u0005\u0005\t\u000b$9M\u0001\u0003J[Bd\u0017\u0002\u0002Ce\t\u0017\u0014Q!\u0016$v]\u000eTA\u0001\"4\u0003 \u00069q-\u001a8fe&\u001c\u0017!\u00038pe6LU\u000e\u001d7!\u0003\u0015\u0019Gn\\:f)!\u0019Y\t\"6\u0005X\u0012e\u0007b\u0002C\u001a%\u0002\u0007!Q\u001d\u0005\b\u0007\u0003\u0013\u0006\u0019\u0001Bs\u0011%!YN\u0015I\u0001\u0002\u0004\u0011).A\u0005u_2,'/\u00198dKR1!Q\u001dCp\tCDq\u0001b\rT\u0001\u0004\u0011)\u000fC\u0004\u0004\u0002N\u0003\rA!:\u0015\r\t\u0015HQ\u001dCt\u0011\u001d!\u0019\u0004\u0016a\u0001\u0005KDqa!!U\u0001\u0004\u0011)/\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0005nB!1Q\u0019Cx\u0013\u0011!\tpa2\u0003\r=\u0013'.Z2u\u0003-\u0019w.\u001c9mKbtuN]7\u0016\u0005\u0011]\b\u0003\u0003C}\t\u0007\u0014)O!6\u000f\t\u0011mHQX\u0007\u0003\t\u0003\fAbY8na2,\u0007PT8s[\u0002\n1bQ8na2,\u0007PW3s_\u0006a1i\\7qY\u0016D(,\u001a:pA\u0005\u0019a*Z4\u0011\u0007\u0011}1LA\u0002OK\u001e\u001cRa\u0017BT\u000b\u0017\u0001\u0002\"\"\u0004\u0005D\n\u0015(Q\u001d\b\u0005\u000b\u001f))\"\u0004\u0002\u0006\u0012)!Q1\u0003Ca\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0003\u0006\u0018\u0015E\u0011!B(q\u001d\u0016<GCAC\u0003\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\u0011)/b\b\t\u000f\u0015\u0005R\f1\u0001\u0003f\u0006\ta/A\u0003BI\u0012\u001c5\tE\u0002\u0005 \u0001\u0014Q!\u00113e\u0007\u000e\u001bR\u0001\u0019BT\u000bW\u0001\"\"\"\f\u00064\t\u0015(Q\u001dBs\u001d\u0011)y!b\f\n\t\u0015ER\u0011C\u0001\u0006\u001fB\fE\rZ\u0005\u0005\u000bk!9MA\u0003J[Bd'\u0007\u0006\u0002\u0006&Q1!Q]C\u001e\u000b{Aq\u0001b\rc\u0001\u0004\u0011)\u000fC\u0004\u0004\u0002\n\u0004\rA!:\u0002\u000b\u0005#G-S\"\u0011\u0007\u0011}QMA\u0003BI\u0012L5iE\u0003f\u0005O+9\u0005\u0005\u0006\u0006.\u0015M21\u0003Bs\u0005K$\"!\"\u0011\u0015\r\t\u0015XQJC(\u0011\u001d!\u0019d\u001aa\u0001\u0007'Aqa!!h\u0001\u0004\u0011)/A\u0003BI\u001245\tE\u0002\u0005 )\u0014Q!\u00113e\r\u000e\u001bRA\u001bBT\u000b3\u0002\"\"\"\f\u00064\ru!Q\u001dBs)\t)\u0019\u0006\u0006\u0004\u0003f\u0016}S\u0011\r\u0005\b\tga\u0007\u0019AB\u000f\u0011\u001d\u0019\t\t\u001ca\u0001\u0005K\fQ!\u00113e\t\u000e\u00032\u0001b\bp\u0005\u0015\tE\r\u001a#D'\u0015y'qUC6!))i#b\r\u0003V\n\u0015(Q\u001d\u000b\u0003\u000bK\"bA!:\u0006r\u0015M\u0004b\u0002C\u001ac\u0002\u0007!Q\u001b\u0005\b\u0007\u0003\u000b\b\u0019\u0001Bs\u0003\u0015\tE\rZ\"J!\r!y\u0002\u001e\u0002\u0006\u0003\u0012$7)S\n\u0006i\n\u001dVQ\u0010\t\u000b\u000b[)\u0019D!:\u0004\u0014\t\u0015HCAC<)\u0019\u0011)/b!\u0006\u0006\"9A1\u0007<A\u0002\t\u0015\bbBBAm\u0002\u000711C\u0001\u0006\u0003\u0012$7I\u0012\t\u0004\t?I(!B!eI\u000e35#B=\u0003(\u0016=\u0005CCC\u0017\u000bg\u0011)o!\b\u0003fR\u0011Q\u0011\u0012\u000b\u0007\u0005K,)*b&\t\u000f\u0011M2\u00101\u0001\u0003f\"91\u0011Q>A\u0002\ru\u0011!B!eI\u000e#\u0005c\u0001C\u0010}\n)\u0011\t\u001a3D\tN)aPa*\u0006\"BQQQFC\u001a\u0005K\u0014)N!:\u0015\u0005\u0015mEC\u0002Bs\u000bO+I\u000b\u0003\u0005\u00054\u0005\u0005\u0001\u0019\u0001Bs\u0011!\u0019\t)!\u0001A\u0002\tU\u0017!B*vE\u000e\u001b\u0005\u0003\u0002C\u0010\u0003\u000f\u0011QaU;c\u0007\u000e\u001bb!a\u0002\u0003(\u0016M\u0006CCC[\u000bg\u0011)O!:\u0003f:!QqBC\\\u0013\u0011)I,\"\u0005\u0002\u000b=\u00038+\u001e2\u0015\u0005\u00155FC\u0002Bs\u000b\u007f+\t\r\u0003\u0005\u00054\u0005-\u0001\u0019\u0001Bs\u0011!\u0019\t)a\u0003A\u0002\t\u0015\u0018!B*vE&\u001b\u0005\u0003\u0002C\u0010\u0003#\u0011QaU;c\u0013\u000e\u001bb!!\u0005\u0003(\u0016-\u0007CCC[\u000bg\u0019\u0019B!:\u0003fR\u0011QQ\u0019\u000b\u0007\u0005K,\t.b5\t\u0011\u0011M\u0012Q\u0003a\u0001\u0007'A\u0001b!!\u0002\u0016\u0001\u0007!Q]\u0001\u0006'V\u0014gi\u0011\t\u0005\t?\tYBA\u0003Tk\n45i\u0005\u0004\u0002\u001c\t\u001dVQ\u001c\t\u000b\u000bk+\u0019d!\b\u0003f\n\u0015HCACl)\u0019\u0011)/b9\u0006f\"AA1GA\u0010\u0001\u0004\u0019i\u0002\u0003\u0005\u0004\u0002\u0006}\u0001\u0019\u0001Bs\u0003\u0015\u0019VO\u0019#D!\u0011!y\"!\n\u0003\u000bM+(\rR\"\u0014\r\u0005\u0015\"qUCx!))),b\r\u0003V\n\u0015(Q\u001d\u000b\u0003\u000bS$bA!:\u0006v\u0016]\b\u0002\u0003C\u001a\u0003S\u0001\rA!6\t\u0011\r\u0005\u0015\u0011\u0006a\u0001\u0005K\fQaU;c\u0007&\u0003B\u0001b\b\u00020\t)1+\u001e2D\u0013N1\u0011q\u0006BT\r\u0003\u0001\"\"\".\u00064\t\u001581\u0003Bs)\t)Y\u0010\u0006\u0004\u0003f\u001a\u001da\u0011\u0002\u0005\t\tg\t\u0019\u00041\u0001\u0003f\"A1\u0011QA\u001a\u0001\u0004\u0019\u0019\"A\u0003Tk\n\u001ce\t\u0005\u0003\u0005 \u0005e\"!B*vE\u000e35CBA\u001d\u0005O3\u0019\u0002\u0005\u0006\u00066\u0016M\"Q]B\u000f\u0005K$\"A\"\u0004\u0015\r\t\u0015h\u0011\u0004D\u000e\u0011!!\u0019$!\u0010A\u0002\t\u0015\b\u0002CBA\u0003{\u0001\ra!\b\u0002\u000bM+(m\u0011#\u0011\t\u0011}\u00111\t\u0002\u0006'V\u00147\tR\n\u0007\u0003\u0007\u00129K\"\n\u0011\u0015\u0015UV1\u0007Bs\u0005+\u0014)\u000f\u0006\u0002\u0007 Q1!Q\u001dD\u0016\r[A\u0001\u0002b\r\u0002H\u0001\u0007!Q\u001d\u0005\t\u0007\u0003\u000b9\u00051\u0001\u0003V\u0006)Q*\u001e7D\u0007B!AqDA'\u0005\u0015iU\u000f\\\"D'\u0019\tiEa*\u00078AQa\u0011HC\u001a\u0005K\u0014)O!:\u000f\t\u0015=a1H\u0005\u0005\r{)\t\"A\u0006Pa6+H.T1ue&DHC\u0001D\u0019)\u0019\u0011)Ob\u0011\u0007F!AA1GA)\u0001\u0004\u0011)\u000f\u0003\u0005\u0004\u0002\u0006E\u0003\u0019\u0001Bs\u0003\u0015iU\u000f\\%D!\u0011!y\"a\u0016\u0003\u000b5+H.S\"\u0014\r\u0005]#q\u0015D(!)1I$b\r\u0004\u0014\t\u0015(Q\u001d\u000b\u0003\r\u0013\"bA!:\u0007V\u0019]\u0003\u0002\u0003C\u001a\u00037\u0002\raa\u0005\t\u0011\r\u0005\u00151\fa\u0001\u0005K\fQ!T;m\r\u000e\u0003B\u0001b\b\u0002b\t)Q*\u001e7G\u0007N1\u0011\u0011\rBT\rC\u0002\"B\"\u000f\u00064\ru!Q\u001dBs)\t1Y\u0006\u0006\u0004\u0003f\u001a\u001dd\u0011\u000e\u0005\t\tg\t)\u00071\u0001\u0004\u001e!A1\u0011QA3\u0001\u0004\u0011)/A\u0003Nk2$5\t\u0005\u0003\u0005 \u0005-$!B'vY\u0012\u001b5CBA6\u0005O3\u0019\b\u0005\u0006\u0007:\u0015M\"Q\u001bBs\u0005K$\"A\"\u001c\u0015\r\t\u0015h\u0011\u0010D>\u0011!!\u0019$a\u001cA\u0002\tU\u0007\u0002CBA\u0003_\u0002\rA!:\u0002\u000b5+HnQ%\u0011\t\u0011}\u0011Q\u000f\u0002\u0006\u001bVd7)S\n\u0007\u0003k\u00129K\"\"\u0011\u0015\u0019eR1\u0007Bs\u0007'\u0011)\u000f\u0006\u0002\u0007\u0000Q1!Q\u001dDF\r\u001bC\u0001\u0002b\r\u0002z\u0001\u0007!Q\u001d\u0005\t\u0007\u0003\u000bI\b1\u0001\u0004\u0014\u0005)Q*\u001e7D\rB!AqDA@\u0005\u0015iU\u000f\\\"G'\u0019\tyHa*\u0007\u0018BQa\u0011HC\u001a\u0005K\u001ciB!:\u0015\u0005\u0019EEC\u0002Bs\r;3y\n\u0003\u0005\u00054\u0005\r\u0005\u0019\u0001Bs\u0011!\u0019\t)a!A\u0002\ru\u0011!B'vY\u000e#\u0005\u0003\u0002C\u0010\u0003\u0013\u0013Q!T;m\u0007\u0012\u001bb!!#\u0003(\u001a%\u0006C\u0003D\u001d\u000bg\u0011)O!6\u0003fR\u0011a1\u0015\u000b\u0007\u0005K4yK\"-\t\u0011\u0011M\u0012Q\u0012a\u0001\u0005KD\u0001b!!\u0002\u000e\u0002\u0007!Q[\u0001\u0006\t&48i\u0011\t\u0005\t?\t\u0019JA\u0003ESZ\u001c5i\u0005\u0004\u0002\u0014\n\u001df1\u0018\t\u000b\r{+\u0019D!:\u0003f\n\u0015h\u0002BC\b\r\u007fKAA\"1\u0006\u0012\u0005)q\n\u001d#jmR\u0011aQ\u0017\u000b\u0007\u0005K49M\"3\t\u0011\u0011M\u0012q\u0013a\u0001\u0005KD\u0001b!!\u0002\u0018\u0002\u0007!Q]\u0001\u0006\t&4\u0018j\u0011\t\u0005\t?\tiJA\u0003ESZL5i\u0005\u0004\u0002\u001e\n\u001df1\u001b\t\u000b\r{+\u0019da\u0005\u0003f\n\u0015HC\u0001Dg)\u0019\u0011)O\"7\u0007\\\"AA1GAQ\u0001\u0004\u0019\u0019\u0002\u0003\u0005\u0004\u0002\u0006\u0005\u0006\u0019\u0001Bs\u0003\u0015!\u0015N\u001e$D!\u0011!y\"a*\u0003\u000b\u0011KgOR\"\u0014\r\u0005\u001d&q\u0015Ds!)1i,b\r\u0004\u001e\t\u0015(Q\u001d\u000b\u0003\r?$bA!:\u0007l\u001a5\b\u0002\u0003C\u001a\u0003W\u0003\ra!\b\t\u0011\r\u0005\u00151\u0016a\u0001\u0005K\fQ\u0001R5w\t\u000e\u0003B\u0001b\b\u00022\n)A)\u001b<E\u0007N1\u0011\u0011\u0017BT\ro\u0004\"B\"0\u00064\tU'Q\u001dBs)\t1\t\u0010\u0006\u0004\u0003f\u001auhq \u0005\t\tg\t)\f1\u0001\u0003V\"A1\u0011QA[\u0001\u0004\u0011)/A\u0003ESZ\u001c\u0015\n\u0005\u0003\u0005 \u0005m&!\u0002#jm\u000eK5CBA^\u0005O;I\u0001\u0005\u0006\u0007>\u0016M\"Q]B\n\u0005K$\"ab\u0001\u0015\r\t\u0015xqBD\t\u0011!!\u0019$a0A\u0002\t\u0015\b\u0002CBA\u0003\u007f\u0003\raa\u0005\u0002\u000b\u0011Kgo\u0011$\u0011\t\u0011}\u0011Q\u0019\u0002\u0006\t&48IR\n\u0007\u0003\u000b\u00149kb\u0007\u0011\u0015\u0019uV1\u0007Bs\u0007;\u0011)\u000f\u0006\u0002\b\u0016Q1!Q]D\u0011\u000fGA\u0001\u0002b\r\u0002J\u0002\u0007!Q\u001d\u0005\t\u0007\u0003\u000bI\r1\u0001\u0004\u001e\u0005)A)\u001b<D\tB!AqDAh\u0005\u0015!\u0015N^\"E'\u0019\tyMa*\b.AQaQXC\u001a\u0005K\u0014)N!:\u0015\u0005\u001d\u001dBC\u0002Bs\u000fg9)\u0004\u0003\u0005\u00054\u0005M\u0007\u0019\u0001Bs\u0011!\u0019\t)a5A\u0002\tU\u0017!B'pI\u000e#\u0005\u0003\u0002C\u0010\u00033\u0014Q!T8e\u0007\u0012\u001bb!!7\u0003(\u001e}\u0002CCD!\u000bg\u0011)O!6\u0003f:!QqBD\"\u0013\u00119)%\"\u0005\u0002\u000b=\u0003Xj\u001c3\u0015\u0005\u001deBC\u0002Bs\u000f\u0017:i\u0005\u0003\u0005\u00054\u0005u\u0007\u0019\u0001Bs\u0011!\u0019\t)!8A\u0002\tU\u0017!B'pI\u000e\u001b\u0005\u0003\u0002C\u0010\u0003G\u0014Q!T8e\u0007\u000e\u001bb!a9\u0003(\u001e]\u0003CCD!\u000bg\u0011)O!:\u0003fR\u0011q\u0011\u000b\u000b\u0007\u0005K<ifb\u0018\t\u0011\u0011M\u0012q\u001da\u0001\u0005KD\u0001b!!\u0002h\u0002\u0007!Q]\u0001\u0006\u001b>$7I\u0012\t\u0005\t?\tiOA\u0003N_\u0012\u001cei\u0005\u0004\u0002n\n\u001dv\u0011\u000e\t\u000b\u000f\u0003*\u0019D!:\u0004\u001e\t\u0015HCAD2)\u0019\u0011)ob\u001c\br!AA1GAy\u0001\u0004\u0011)\u000f\u0003\u0005\u0004\u0002\u0006E\b\u0019AB\u000f\u0003\u0015iu\u000eZ\"J!\u0011!y\"a>\u0003\u000b5{GmQ%\u0014\r\u0005](qUD>!)9\t%b\r\u0003f\u000eM!Q\u001d\u000b\u0003\u000fk\"bA!:\b\u0002\u001e\r\u0005\u0002\u0003C\u001a\u0003w\u0004\rA!:\t\u0011\r\u0005\u00151 a\u0001\u0007'\tQ!T8e\t\u000e\u0003B\u0001b\b\u0003\u0002\t)Qj\u001c3E\u0007N1!\u0011\u0001BT\u000f\u001b\u0003\"b\"\u0011\u00064\tU'Q\u001dBs)\t99\t\u0006\u0004\u0003f\u001eMuQ\u0013\u0005\t\tg\u0011)\u00011\u0001\u0003V\"A1\u0011\u0011B\u0003\u0001\u0004\u0011)/A\u0003N_\u0012L5\t\u0005\u0003\u0005 \t-!!B'pI&\u001b5C\u0002B\u0006\u0005O;y\n\u0005\u0006\bB\u0015M21\u0003Bs\u0005K$\"a\"'\u0015\r\t\u0015xQUDT\u0011!!\u0019Da\u0004A\u0002\rM\u0001\u0002CBA\u0005\u001f\u0001\rA!:\u0002\u000b5{GMR\"\u0011\t\u0011}!Q\u0003\u0002\u0006\u001b>$giQ\n\u0007\u0005+\u00119k\"-\u0011\u0015\u001d\u0005S1GB\u000f\u0005K\u0014)\u000f\u0006\u0002\b,R1!Q]D\\\u000fsC\u0001\u0002b\r\u0003\u001a\u0001\u00071Q\u0004\u0005\t\u0007\u0003\u0013I\u00021\u0001\u0003f\u0006)\u0001k\\<D\tB!Aq\u0004B\u0010\u0005\u0015\u0001vn^\"E'\u0019\u0011yBa*\bDBQqQYC\u001a\u0005K\u0014)N!:\u000f\t\u0015=qqY\u0005\u0005\u000f\u0013,\t\"A\u0003PaB{w\u000f\u0006\u0002\b>R1!Q]Dh\u000f#D\u0001\u0002b\r\u0003$\u0001\u0007!Q\u001d\u0005\t\u0007\u0003\u0013\u0019\u00031\u0001\u0003V\u0006)\u0001k\\<D\u0007B!Aq\u0004B\u0015\u0005\u0015\u0001vn^\"D'\u0019\u0011ICa*\b\\BQqQYC\u001a\u0005K\u0014)O!:\u0015\u0005\u001dUGC\u0002Bs\u000fC<\u0019\u000f\u0003\u0005\u00054\t5\u0002\u0019\u0001Bs\u0011!\u0019\tI!\fA\u0002\t\u0015(aE\"p[BdW\r_%t\u0007>tg\r\\5di\u0016$7C\u0002B\u0019\t[<I\u000f\u0005\u0004\u0003<\u001e-(Q]\u0005\u0005\u000f[\u0014yMA\u0004Ok6,'/[2\u0002\r\u0011Jg.\u001b;%)\t9\u0019\u0010\u0005\u0003\u0003*\u001eU\u0018\u0002BD|\u0005W\u0013A!\u00168ji\u0006!\u0001\u000f\\;t)\u0019\u0011)o\"@\t\u0002!Aqq B\u001b\u0001\u0004\u0011)/A\u0001y\u0011!A\u0019A!\u000eA\u0002\t\u0015\u0018!A=\u0002\u000b5Lg.^:\u0015\r\t\u0015\b\u0012\u0002E\u0006\u0011!9yPa\u000eA\u0002\t\u0015\b\u0002\u0003E\u0002\u0005o\u0001\rA!:\u0002\u000bQLW.Z:\u0015\r\t\u0015\b\u0012\u0003E\n\u0011!9yP!\u000fA\u0002\t\u0015\b\u0002\u0003E\u0002\u0005s\u0001\rA!:\u0002\r9,w-\u0019;f)\u0011\u0011)\u000f#\u0007\t\u0011\u001d}(1\ba\u0001\u0005K\fqA\u001a:p[&sG\u000f\u0006\u0003\u0003f\"}\u0001\u0002CD\u0000\u0005{\u0001\raa\u0005\u0002\u000bQ|\u0017J\u001c;\u0015\t\rM\u0001R\u0005\u0005\t\u000f\u007f\u0014y\u00041\u0001\u0003f\u00061Ao\u001c'p]\u001e$B\u0001c\u000b\t2A!!\u0011\u0016E\u0017\u0013\u0011AyCa+\u0003\t1{gn\u001a\u0005\t\u000f\u007f\u0014\t\u00051\u0001\u0003f\u00069Ao\u001c$m_\u0006$H\u0003BB\u000f\u0011oA\u0001bb@\u0003D\u0001\u0007!Q\u001d\u000b\u0005\u0005+DY\u0004\u0003\u0005\b\u0000\n\u0015\u0003\u0019\u0001Bs\u00031\u0019HO]5di2L(+Z1m)\u0011\u0011)\u000e#\u0011\t\u0011\u001d}(q\ta\u0001\u0005K\u00141cQ8na2,\u00070S:Ge\u0006\u001cG/[8oC2\u001c\u0002B!\u0013\u0005n\"\u001d\u0003\u0012\n\t\u0005\t?\u0011\t\u0004\u0005\u0004\u0003<\"-#Q]\u0005\u0005\u0011\u001b\u0012yM\u0001\u0006Ge\u0006\u001cG/[8oC2\f1\u0001Z5w)\u0019\u0011)\u000fc\u0015\tV!Aqq B'\u0001\u0004\u0011)\u000f\u0003\u0005\t\u0004\t5\u0003\u0019\u0001Bs\u0005=\u0019u.\u001c9mKb|%\u000fZ3sS:<7C\u0002B(\t[DY\u0006\u0005\u0004\u0003<\"u#Q]\u0005\u0005\u0011?\u0012yM\u0001\u0005Pe\u0012,'/\u001b8h\u0003\u001d\u0019w.\u001c9be\u0016$baa\u0005\tf!\u001d\u0004\u0002\u0003C\u001a\u0005'\u0002\rA!:\t\u0011\r\u0005%1\u000ba\u0001\u0005K\f1cQ8na2,\u00070S:Ge\u0006\u001cG/[8oC2\u0004B\u0001b\b\u0003XMA!q\u000bCw\u0011_B\t\b\u0005\u0003\u0005 \t%\u0003\u0003\u0002C\u0010\u0005\u001f\"\"\u0001c\u001b\u0002\u0017A\f'o]3TiJLgn\u001a\u000b\u0005\u0011sBy\b\u0005\u0004\u0003*\"m$Q]\u0005\u0005\u0011{\u0012YK\u0001\u0004PaRLwN\u001c\u0005\t\u0011\u0003\u0013Y\u00061\u0001\u0003r\u0006\u00191\u000f\u001e:\u0002\u000bI,w-\u001a=\u0016\u0005!\u001d\u0005\u0003\u0002EE\u0011#k!\u0001c#\u000b\t!\r\u0005R\u0012\u0006\u0005\u0011\u001f\u001bY-\u0001\u0003vi&d\u0017\u0002\u0002EJ\u0011\u0017\u0013q\u0001U1ui\u0016\u0014h.\u0001\bm_\u001e\u001cu.\u001c9mKbLU\u000e\u001d7\u0011\t\u0011}!1\r\u0002\u000fY><7i\\7qY\u0016D\u0018*\u001c9m'\u0019\u0011\u0019Ga*\t\u001eBA\u0001r\u0014Cb\u0005K\u0014)O\u0004\u0003\t\"\"Ef\u0002\u0002ER\u0011[sA\u0001#*\t*:!!q\u0018ET\u0013\t\u0011\t+\u0003\u0003\t,\n}\u0015\u0001\u00038v[\u0016\u0014\u0018nY:\n\t\t%\u0007r\u0016\u0006\u0005\u0011W\u0013y*\u0003\u0003\u0004x!M&\u0002\u0002Be\u0011_#\"\u0001c&\u0015\t\t\u0015\b\u0012\u0018\u0005\t\u000bC\u00119\u00071\u0001\u0003f\u0006qQ\r\u001f9D_6\u0004H.\u001a=J[Bd\u0007\u0003\u0002C\u0010\u0005[\u0012a\"\u001a=q\u0007>l\u0007\u000f\\3y\u00136\u0004Hn\u0005\u0004\u0003n\t\u001d\u00062\u0019\t\t\u0011\u000b$\u0019M!:\u0003f:!\u0001\u0012\u0015Ed\u0013\u0011\u0019I\bc-\u0015\u0005!uF\u0003\u0002Bs\u0011\u001bD\u0001\"\"\t\u0003r\u0001\u0007!Q]\u0001\u000fC\n\u001c8i\\7qY\u0016D\u0018*\u001c9m!\u0011!yBa\u001e\u0003\u001d\u0005\u00147oQ8na2,\u00070S7qYN1!q\u000fBT\u0011/\u0004\u0002\u0002#7\u0005D\n\u0015(Q\u001b\b\u0005\u0011CCY.\u0003\u0003\u0004t!MFC\u0001Ei)\u0011\u0011)\u000e#9\t\u0011\u0015\u0005\"1\u0010a\u0001\u0005K\fA\u0003]8x\u0007>l\u0007\u000f\\3y\t>,(\r\\3J[Bd\u0007\u0003\u0002C\u0010\u0005\u0003\u0013A\u0003]8x\u0007>l\u0007\u000f\\3y\t>,(\r\\3J[Bd7C\u0002BA\u0005OCY\u000f\u0005\u0006\tn\u0016M\"Q\u001dBk\u0005KtA\u0001#)\tp&!11\u0010EZ)\tA)\u000f\u0006\u0004\u0003f\"U\br\u001f\u0005\t\u000bC\u0011)\t1\u0001\u0003f\"A\u0001\u0012 BC\u0001\u0004\u0011).A\u0001e\u0003U\u0001xn^\"p[BdW\r_\"p[BdW\r_%na2\u0004B\u0001b\b\u0003\f\n)\u0002o\\<D_6\u0004H.\u001a=D_6\u0004H.\u001a=J[Bd7C\u0002BF\u0005OK\u0019\u0001\u0005\u0006\tn\u0016M\"Q\u001dBs\u0005K$\"\u0001#@\u0015\r\t\u0015\u0018\u0012BE\u0006\u0011!)\tCa$A\u0002\t\u0015\b\u0002\u0003E}\u0005\u001f\u0003\rA!:\u0015\r\t\u0015\u0018rBE\t\u0011!\u0011\tNa%A\u0002\tU\u0007\u0002\u0003Bo\u0005'\u0003\rA!6\u0002\u000fUt\u0017\r\u001d9msR!\u0011rCE\u0010!\u0019\u0011I\u000bc\u001f\n\u001aAA!\u0011VE\u000e\u0005+\u0014).\u0003\u0003\n\u001e\t-&A\u0002+va2,'\u0007\u0003\u0006\n\"\tU\u0015\u0011!a\u0001\u0005K\f1\u0001\u001f\u00131\u0001"
)
public class Complex implements Product, Serializable {
   private final double real;
   private final double imag;

   public static Option unapply(final Complex x$0) {
      return Complex$.MODULE$.unapply(x$0);
   }

   public static Complex apply(final double real, final double imag) {
      return Complex$.MODULE$.apply(real, imag);
   }

   public static Zero ComplexZero() {
      return Complex$.MODULE$.ComplexZero();
   }

   public static UFunc.UImpl complexNorm() {
      return Complex$.MODULE$.complexNorm();
   }

   public static Complex i() {
      return Complex$.MODULE$.i();
   }

   public static Complex nan() {
      return Complex$.MODULE$.nan();
   }

   public static Complex one() {
      return Complex$.MODULE$.one();
   }

   public static Complex zero() {
      return Complex$.MODULE$.zero();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public double real() {
      return this.real;
   }

   public double imag() {
      return this.imag;
   }

   public String toString() {
      return (new StringBuilder(4)).append(this.real()).append(" + ").append(this.imag()).append("i").toString();
   }

   public double re() {
      return this.real();
   }

   public double im() {
      return this.imag();
   }

   public Complex $plus(final Complex that) {
      return new Complex(this.real() + that.real(), this.imag() + that.imag());
   }

   public Complex $plus(final int that) {
      return new Complex(this.real() + (double)that, this.imag());
   }

   public Complex $plus(final float that) {
      return new Complex(this.real() + (double)that, this.imag());
   }

   public Complex $plus(final double that) {
      return new Complex(this.real() + that, this.imag());
   }

   public Complex $minus(final Complex that) {
      return new Complex(this.real() - that.real(), this.imag() - that.imag());
   }

   public Complex $minus(final int that) {
      return new Complex(this.real() - (double)that, this.imag());
   }

   public Complex $minus(final float that) {
      return new Complex(this.real() - (double)that, this.imag());
   }

   public Complex $minus(final double that) {
      return new Complex(this.real() - that, this.imag());
   }

   public Complex $times(final Complex that) {
      return new Complex(this.real() * that.real() - this.imag() * that.imag(), this.real() * that.imag() + this.imag() * that.real());
   }

   public Complex $times(final int that) {
      return new Complex(this.real() * (double)that, this.imag() * (double)that);
   }

   public Complex $times(final float that) {
      return new Complex(this.real() * (double)that, this.imag() * (double)that);
   }

   public Complex $times(final double that) {
      return new Complex(this.real() * that, this.imag() * that);
   }

   public Complex $div(final Complex that) {
      double denom = that.real() * that.real() + that.imag() * that.imag();
      return new Complex((this.real() * that.real() + this.imag() * that.imag()) / denom, (this.imag() * that.real() - this.real() * that.imag()) / denom);
   }

   public Complex $div(final int that) {
      return new Complex(this.real() / (double)that, this.imag() / (double)that);
   }

   public Complex $div(final float that) {
      return new Complex(this.real() / (double)that, this.imag() / (double)that);
   }

   public Complex $div(final double that) {
      return new Complex(this.real() / that, this.imag() / that);
   }

   public Complex $percent(final Complex that) {
      Complex div = this.$div(that);
      return this.$minus((new Complex(breeze.numerics.package.floor$.MODULE$.apply$mDDc$sp(div.re(), package$floor$floorDoubleImpl$.MODULE$), breeze.numerics.package.floor$.MODULE$.apply$mDDc$sp(div.im(), package$floor$floorDoubleImpl$.MODULE$))).$times(div));
   }

   public Complex $percent(final int that) {
      return this.$percent(new Complex((double)that, (double)0.0F));
   }

   public Complex $percent(final float that) {
      return this.$percent(new Complex((double)that, (double)0.0F));
   }

   public Complex $percent(final double that) {
      return this.$percent(new Complex(that, (double)0.0F));
   }

   public Complex unary_$minus() {
      return new Complex(-this.real(), -this.imag());
   }

   public double abs() {
      return .MODULE$.sqrt(this.real() * this.real() + this.imag() * this.imag());
   }

   public Complex conjugate() {
      return new Complex(this.real(), -this.imag());
   }

   public Complex log() {
      return new Complex(.MODULE$.log(this.abs()), .MODULE$.atan2(this.imag(), this.real()));
   }

   public Complex exp() {
      double expreal = .MODULE$.exp(this.real());
      return new Complex(expreal * .MODULE$.cos(this.imag()), expreal * .MODULE$.sin(this.imag()));
   }

   public Complex pow(final double b) {
      return this.pow(new Complex(b, (double)0.0F));
   }

   public Complex pow(final Complex b) {
      Complex var10000;
      label51: {
         Complex var2 = Complex$.MODULE$.zero();
         if (b == null) {
            if (var2 == null) {
               break label51;
            }
         } else if (b.equals(var2)) {
            break label51;
         }

         label52: {
            Complex var3 = Complex$.MODULE$.zero();
            if (this == null) {
               if (var3 != null) {
                  break label52;
               }
            } else if (!this.equals(var3)) {
               break label52;
            }

            if (b.imag() == (double)0.0F && !(b.real() < (double)0.0F)) {
               var10000 = Complex$.MODULE$.zero();
               return var10000;
            }

            var10000 = Complex$.MODULE$.nan();
            return var10000;
         }

         Complex c = this.log().$times(b);
         double expReal = .MODULE$.exp(c.real());
         var10000 = new Complex(expReal * .MODULE$.cos(c.imag()), expReal * .MODULE$.sin(c.imag()));
         return var10000;
      }

      var10000 = Complex$.MODULE$.one();
      return var10000;
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Complex) {
         Complex var4 = (Complex)that;
         var2 = this.real() == var4.real() && this.imag() == var4.imag();
      } else if (that instanceof Double) {
         double var5 = BoxesRunTime.unboxToDouble(that);
         var2 = this.real() == var5 && this.imag() == (double)0;
      } else if (that instanceof Integer) {
         int var7 = BoxesRunTime.unboxToInt(that);
         var2 = this.real() == (double)var7 && this.imag() == (double)0;
      } else if (that instanceof Short) {
         short var8 = BoxesRunTime.unboxToShort(that);
         var2 = this.real() == (double)var8 && this.imag() == (double)0;
      } else if (that instanceof Long) {
         long var9 = BoxesRunTime.unboxToLong(that);
         var2 = this.real() == (double)var9 && this.imag() == (double)0;
      } else if (that instanceof Float) {
         float var11 = BoxesRunTime.unboxToFloat(that);
         var2 = this.real() == (double)var11 && this.imag() == (double)0;
      } else {
         var2 = false;
      }

      return var2;
   }

   public int hashCode() {
      return Statics.doubleHash(this.real()) ^ Statics.doubleHash(this.imag());
   }

   public Complex copy(final double real, final double imag) {
      return new Complex(real, imag);
   }

   public double copy$default$1() {
      return this.real();
   }

   public double copy$default$2() {
      return this.imag();
   }

   public String productPrefix() {
      return "Complex";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.real());
            break;
         case 1:
            var10000 = BoxesRunTime.boxToDouble(this.imag());
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Complex;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "real";
            break;
         case 1:
            var10000 = "imag";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Complex(final double real, final double imag) {
      this.real = real;
      this.imag = imag;
      Product.$init$(this);
   }

   public static class scalar$ implements Field {
      public static final scalar$ MODULE$ = new scalar$();
      private static final ClassTag manifest;
      private static final Zero defaultArrayValue;
      private static final UFunc.UImpl normImpl;

      static {
         Semiring.$init$(MODULE$);
         Ring.$init$(MODULE$);
         Field.$init$(MODULE$);
         manifest = (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.apply(Complex.class));
         defaultArrayValue = new Zero(new Complex((double)0.0F, (double)0.0F));
         normImpl = new UFunc.UImpl() {
            public double apply$mcDD$sp(final double v) {
               return UFunc.UImpl.apply$mcDD$sp$(this, v);
            }

            public float apply$mcDF$sp(final double v) {
               return UFunc.UImpl.apply$mcDF$sp$(this, v);
            }

            public int apply$mcDI$sp(final double v) {
               return UFunc.UImpl.apply$mcDI$sp$(this, v);
            }

            public double apply$mcFD$sp(final float v) {
               return UFunc.UImpl.apply$mcFD$sp$(this, v);
            }

            public float apply$mcFF$sp(final float v) {
               return UFunc.UImpl.apply$mcFF$sp$(this, v);
            }

            public int apply$mcFI$sp(final float v) {
               return UFunc.UImpl.apply$mcFI$sp$(this, v);
            }

            public double apply$mcID$sp(final int v) {
               return UFunc.UImpl.apply$mcID$sp$(this, v);
            }

            public float apply$mcIF$sp(final int v) {
               return UFunc.UImpl.apply$mcIF$sp$(this, v);
            }

            public int apply$mcII$sp(final int v) {
               return UFunc.UImpl.apply$mcII$sp$(this, v);
            }

            public double apply(final Complex v) {
               return v.abs();
            }
         };
      }

      public double $div$mcD$sp(final double a, final double b) {
         return Field.$div$mcD$sp$(this, a, b);
      }

      public float $div$mcF$sp(final float a, final float b) {
         return Field.$div$mcF$sp$(this, a, b);
      }

      public int $div$mcI$sp(final int a, final int b) {
         return Field.$div$mcI$sp$(this, a, b);
      }

      public long $div$mcJ$sp(final long a, final long b) {
         return Field.$div$mcJ$sp$(this, a, b);
      }

      public short $div$mcS$sp(final short a, final short b) {
         return Field.$div$mcS$sp$(this, a, b);
      }

      public Object inverse(final Object a) {
         return Field.inverse$(this, a);
      }

      public double inverse$mcD$sp(final double a) {
         return Field.inverse$mcD$sp$(this, a);
      }

      public float inverse$mcF$sp(final float a) {
         return Field.inverse$mcF$sp$(this, a);
      }

      public int inverse$mcI$sp(final int a) {
         return Field.inverse$mcI$sp$(this, a);
      }

      public long inverse$mcJ$sp(final long a) {
         return Field.inverse$mcJ$sp$(this, a);
      }

      public short inverse$mcS$sp(final short a) {
         return Field.inverse$mcS$sp$(this, a);
      }

      public double pow$mcD$sp(final double a, final double b) {
         return Field.pow$mcD$sp$(this, a, b);
      }

      public float pow$mcF$sp(final float a, final float b) {
         return Field.pow$mcF$sp$(this, a, b);
      }

      public int pow$mcI$sp(final int a, final int b) {
         return Field.pow$mcI$sp$(this, a, b);
      }

      public long pow$mcJ$sp(final long a, final long b) {
         return Field.pow$mcJ$sp$(this, a, b);
      }

      public short pow$mcS$sp(final short a, final short b) {
         return Field.pow$mcS$sp$(this, a, b);
      }

      public double $minus$mcD$sp(final double a, final double b) {
         return Ring.$minus$mcD$sp$(this, a, b);
      }

      public float $minus$mcF$sp(final float a, final float b) {
         return Ring.$minus$mcF$sp$(this, a, b);
      }

      public int $minus$mcI$sp(final int a, final int b) {
         return Ring.$minus$mcI$sp$(this, a, b);
      }

      public long $minus$mcJ$sp(final long a, final long b) {
         return Ring.$minus$mcJ$sp$(this, a, b);
      }

      public short $minus$mcS$sp(final short a, final short b) {
         return Ring.$minus$mcS$sp$(this, a, b);
      }

      public Object negate(final Object s) {
         return Ring.negate$(this, s);
      }

      public double negate$mcD$sp(final double s) {
         return Ring.negate$mcD$sp$(this, s);
      }

      public float negate$mcF$sp(final float s) {
         return Ring.negate$mcF$sp$(this, s);
      }

      public int negate$mcI$sp(final int s) {
         return Ring.negate$mcI$sp$(this, s);
      }

      public long negate$mcJ$sp(final long s) {
         return Ring.negate$mcJ$sp$(this, s);
      }

      public short negate$mcS$sp(final short s) {
         return Ring.negate$mcS$sp$(this, s);
      }

      public double $percent$mcD$sp(final double a, final double b) {
         return Ring.$percent$mcD$sp$(this, a, b);
      }

      public float $percent$mcF$sp(final float a, final float b) {
         return Ring.$percent$mcF$sp$(this, a, b);
      }

      public int $percent$mcI$sp(final int a, final int b) {
         return Ring.$percent$mcI$sp$(this, a, b);
      }

      public long $percent$mcJ$sp(final long a, final long b) {
         return Ring.$percent$mcJ$sp$(this, a, b);
      }

      public short $percent$mcS$sp(final short a, final short b) {
         return Ring.$percent$mcS$sp$(this, a, b);
      }

      public double sNorm(final Object a) {
         return Ring.sNorm$(this, a);
      }

      public double sNorm$mcD$sp(final double a) {
         return Ring.sNorm$mcD$sp$(this, a);
      }

      public double sNorm$mcF$sp(final float a) {
         return Ring.sNorm$mcF$sp$(this, a);
      }

      public double sNorm$mcI$sp(final int a) {
         return Ring.sNorm$mcI$sp$(this, a);
      }

      public double sNorm$mcJ$sp(final long a) {
         return Ring.sNorm$mcJ$sp$(this, a);
      }

      public double sNorm$mcS$sp(final short a) {
         return Ring.sNorm$mcS$sp$(this, a);
      }

      public boolean specInstance$() {
         return Ring.specInstance$$(this);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public Complex zero() {
         return Complex$.MODULE$.zero();
      }

      public Complex one() {
         return Complex$.MODULE$.one();
      }

      public Complex nan() {
         return Complex$.MODULE$.nan();
      }

      public boolean $eq$eq(final Complex a, final Complex b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b == null) {
                  break label23;
               }
            } else if (a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public boolean $bang$eq(final Complex a, final Complex b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b != null) {
                  break label23;
               }
            } else if (!a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public boolean $greater(final Complex a, final Complex b) {
         return a.real() > b.real() || a.real() == b.real() && a.imag() > b.imag();
      }

      public boolean $greater$eq(final Complex a, final Complex b) {
         return a.real() >= b.real() || a.real() == b.real() && a.imag() >= b.imag();
      }

      public boolean $less(final Complex a, final Complex b) {
         return a.real() < b.real() || a.real() == b.real() && a.imag() < b.imag();
      }

      public boolean $less$eq(final Complex a, final Complex b) {
         return a.real() <= b.real() || a.real() == b.real() && a.imag() <= b.imag();
      }

      public Complex $plus(final Complex a, final Complex b) {
         return a.$plus(b);
      }

      public Complex $minus(final Complex a, final Complex b) {
         return a.$minus(b);
      }

      public Complex $times(final Complex a, final Complex b) {
         return a.$times(b);
      }

      public Complex $div(final Complex a, final Complex b) {
         return a.$div(b);
      }

      public Nothing toDouble(final Complex a) {
         throw new UnsupportedOperationException("Cannot automatically convert complex numbers to doubles");
      }

      public boolean isNaN(final Complex a) {
         return Double.isNaN(a.real()) || Double.isNaN(a.imag());
      }

      public ClassTag manifest() {
         return manifest;
      }

      public Zero defaultArrayValue() {
         return defaultArrayValue;
      }

      public UFunc.UImpl normImpl() {
         return normImpl;
      }

      public boolean close(final Complex a, final Complex b, final double tolerance) {
         return this.sNorm(a.$minus(b)) <= tolerance * .MODULE$.max(this.sNorm(a), this.sNorm(b));
      }

      public Complex pow(final Complex a, final Complex b) {
         return a.pow(b);
      }

      public Complex $percent(final Complex a, final Complex b) {
         return a.$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(scalar$.class);
      }
   }

   public static class Neg$ implements UFunc.UImpl {
      public static final Neg$ MODULE$ = new Neg$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public Complex apply(final Complex v) {
         return v.unary_$minus();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(Neg$.class);
      }
   }

   public static class AddCC$ implements UFunc.UImpl2 {
      public static final AddCC$ MODULE$ = new AddCC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final Complex b) {
         return a.$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddCC$.class);
      }
   }

   public static class AddIC$ implements UFunc.UImpl2 {
      public static final AddIC$ MODULE$ = new AddIC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final int a, final Complex b) {
         return package$.MODULE$.richInt(a).$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddIC$.class);
      }
   }

   public static class AddFC$ implements UFunc.UImpl2 {
      public static final AddFC$ MODULE$ = new AddFC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final float a, final Complex b) {
         return package$.MODULE$.richFloat(a).$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddFC$.class);
      }
   }

   public static class AddDC$ implements UFunc.UImpl2 {
      public static final AddDC$ MODULE$ = new AddDC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final double a, final Complex b) {
         return package$.MODULE$.RichField(a).$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddDC$.class);
      }
   }

   public static class AddCI$ implements UFunc.UImpl2 {
      public static final AddCI$ MODULE$ = new AddCI$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final int b) {
         return a.$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddCI$.class);
      }
   }

   public static class AddCF$ implements UFunc.UImpl2 {
      public static final AddCF$ MODULE$ = new AddCF$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final float b) {
         return a.$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddCF$.class);
      }
   }

   public static class AddCD$ implements UFunc.UImpl2 {
      public static final AddCD$ MODULE$ = new AddCD$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final double b) {
         return a.$plus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(AddCD$.class);
      }
   }

   public static class SubCC$ implements UFunc.UImpl2 {
      public static final SubCC$ MODULE$ = new SubCC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final Complex b) {
         return a.$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubCC$.class);
      }
   }

   public static class SubIC$ implements UFunc.UImpl2 {
      public static final SubIC$ MODULE$ = new SubIC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final int a, final Complex b) {
         return package$.MODULE$.richInt(a).$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubIC$.class);
      }
   }

   public static class SubFC$ implements UFunc.UImpl2 {
      public static final SubFC$ MODULE$ = new SubFC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final float a, final Complex b) {
         return package$.MODULE$.richFloat(a).$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubFC$.class);
      }
   }

   public static class SubDC$ implements UFunc.UImpl2 {
      public static final SubDC$ MODULE$ = new SubDC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final double a, final Complex b) {
         return package$.MODULE$.RichField(a).$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubDC$.class);
      }
   }

   public static class SubCI$ implements UFunc.UImpl2 {
      public static final SubCI$ MODULE$ = new SubCI$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final int b) {
         return a.$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubCI$.class);
      }
   }

   public static class SubCF$ implements UFunc.UImpl2 {
      public static final SubCF$ MODULE$ = new SubCF$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final float b) {
         return a.$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubCF$.class);
      }
   }

   public static class SubCD$ implements UFunc.UImpl2 {
      public static final SubCD$ MODULE$ = new SubCD$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final double b) {
         return a.$minus(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SubCD$.class);
      }
   }

   public static class MulCC$ implements UFunc.UImpl2 {
      public static final MulCC$ MODULE$ = new MulCC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final Complex b) {
         return a.$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulCC$.class);
      }
   }

   public static class MulIC$ implements UFunc.UImpl2 {
      public static final MulIC$ MODULE$ = new MulIC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final int a, final Complex b) {
         return package$.MODULE$.richInt(a).$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulIC$.class);
      }
   }

   public static class MulFC$ implements UFunc.UImpl2 {
      public static final MulFC$ MODULE$ = new MulFC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final float a, final Complex b) {
         return package$.MODULE$.richFloat(a).$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulFC$.class);
      }
   }

   public static class MulDC$ implements UFunc.UImpl2 {
      public static final MulDC$ MODULE$ = new MulDC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final double a, final Complex b) {
         return package$.MODULE$.RichField(a).$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulDC$.class);
      }
   }

   public static class MulCI$ implements UFunc.UImpl2 {
      public static final MulCI$ MODULE$ = new MulCI$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final int b) {
         return a.$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulCI$.class);
      }
   }

   public static class MulCF$ implements UFunc.UImpl2 {
      public static final MulCF$ MODULE$ = new MulCF$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final float b) {
         return a.$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulCF$.class);
      }
   }

   public static class MulCD$ implements UFunc.UImpl2 {
      public static final MulCD$ MODULE$ = new MulCD$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final double b) {
         return a.$times(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(MulCD$.class);
      }
   }

   public static class DivCC$ implements UFunc.UImpl2 {
      public static final DivCC$ MODULE$ = new DivCC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final Complex b) {
         return a.$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivCC$.class);
      }
   }

   public static class DivIC$ implements UFunc.UImpl2 {
      public static final DivIC$ MODULE$ = new DivIC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final int a, final Complex b) {
         return package$.MODULE$.richInt(a).$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivIC$.class);
      }
   }

   public static class DivFC$ implements UFunc.UImpl2 {
      public static final DivFC$ MODULE$ = new DivFC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final float a, final Complex b) {
         return package$.MODULE$.richFloat(a).$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivFC$.class);
      }
   }

   public static class DivDC$ implements UFunc.UImpl2 {
      public static final DivDC$ MODULE$ = new DivDC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final double a, final Complex b) {
         return package$.MODULE$.RichField(a).$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivDC$.class);
      }
   }

   public static class DivCI$ implements UFunc.UImpl2 {
      public static final DivCI$ MODULE$ = new DivCI$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final int b) {
         return a.$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivCI$.class);
      }
   }

   public static class DivCF$ implements UFunc.UImpl2 {
      public static final DivCF$ MODULE$ = new DivCF$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final float b) {
         return a.$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivCF$.class);
      }
   }

   public static class DivCD$ implements UFunc.UImpl2 {
      public static final DivCD$ MODULE$ = new DivCD$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final double b) {
         return a.$div(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DivCD$.class);
      }
   }

   public static class ModCD$ implements UFunc.UImpl2 {
      public static final ModCD$ MODULE$ = new ModCD$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final double b) {
         return a.$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModCD$.class);
      }
   }

   public static class ModCC$ implements UFunc.UImpl2 {
      public static final ModCC$ MODULE$ = new ModCC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final Complex b) {
         return a.$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModCC$.class);
      }
   }

   public static class ModCF$ implements UFunc.UImpl2 {
      public static final ModCF$ MODULE$ = new ModCF$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final float b) {
         return a.$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModCF$.class);
      }
   }

   public static class ModCI$ implements UFunc.UImpl2 {
      public static final ModCI$ MODULE$ = new ModCI$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final int b) {
         return a.$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModCI$.class);
      }
   }

   public static class ModDC$ implements UFunc.UImpl2 {
      public static final ModDC$ MODULE$ = new ModDC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final double a, final Complex b) {
         return package$.MODULE$.RichField(a).$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModDC$.class);
      }
   }

   public static class ModIC$ implements UFunc.UImpl2 {
      public static final ModIC$ MODULE$ = new ModIC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final int a, final Complex b) {
         return package$.MODULE$.richInt(a).$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModIC$.class);
      }
   }

   public static class ModFC$ implements UFunc.UImpl2 {
      public static final ModFC$ MODULE$ = new ModFC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final float a, final Complex b) {
         return package$.MODULE$.richFloat(a).$percent(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ModFC$.class);
      }
   }

   public static class PowCD$ implements UFunc.UImpl2 {
      public static final PowCD$ MODULE$ = new PowCD$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final double b) {
         return a.pow(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(PowCD$.class);
      }
   }

   public static class PowCC$ implements UFunc.UImpl2 {
      public static final PowCC$ MODULE$ = new PowCC$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex a, final Complex b) {
         return a.pow(b);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(PowCC$.class);
      }
   }

   public interface ComplexIsConflicted extends Numeric {
      default Complex plus(final Complex x, final Complex y) {
         return x.$plus(y);
      }

      default Complex minus(final Complex x, final Complex y) {
         return x.$minus(y);
      }

      default Complex times(final Complex x, final Complex y) {
         return x.$times(y);
      }

      default Complex negate(final Complex x) {
         return x.unary_$minus();
      }

      default Complex fromInt(final int x) {
         return new Complex((double)x, (double)0.0F);
      }

      default int toInt(final Complex x) {
         return (int)this.strictlyReal(x);
      }

      default long toLong(final Complex x) {
         return (long)this.strictlyReal(x);
      }

      default float toFloat(final Complex x) {
         return (float)this.strictlyReal(x);
      }

      default double toDouble(final Complex x) {
         return this.strictlyReal(x);
      }

      private double strictlyReal(final Complex x) {
         scala.Predef..MODULE$.require(x.imag() == (double)0.0F);
         return x.real();
      }

      static void $init$(final ComplexIsConflicted $this) {
      }
   }

   public interface ComplexIsFractional extends ComplexIsConflicted, Fractional {
      default Complex div(final Complex x, final Complex y) {
         return x.$div(y);
      }

      static void $init$(final ComplexIsFractional $this) {
      }
   }

   public interface ComplexOrdering extends Ordering {
      default int compare(final Complex a, final Complex b) {
         return a.real() < b.real() ? -1 : (a.real() > b.real() ? 1 : (a.imag() < b.imag() ? -1 : (a.imag() > b.imag() ? 1 : 0)));
      }

      static void $init$(final ComplexOrdering $this) {
      }
   }

   public static class ComplexIsFractional$ implements ComplexIsFractional, ComplexOrdering {
      public static final ComplexIsFractional$ MODULE$ = new ComplexIsFractional$();
      private static Pattern regex;
      private static volatile boolean bitmap$0;

      static {
         PartialOrdering.$init$(MODULE$);
         Ordering.$init$(MODULE$);
         Numeric.$init$(MODULE$);
         Complex.ComplexIsConflicted.$init$(MODULE$);
         Fractional.$init$(MODULE$);
         Complex.ComplexIsFractional.$init$(MODULE$);
         Complex.ComplexOrdering.$init$(MODULE$);
      }

      public int compare(final Complex a, final Complex b) {
         return Complex.ComplexOrdering.super.compare(a, b);
      }

      public Complex div(final Complex x, final Complex y) {
         return Complex.ComplexIsFractional.super.div(x, y);
      }

      public Fractional.FractionalOps mkNumericOps(final Object lhs) {
         return Fractional.mkNumericOps$(this, lhs);
      }

      public Complex plus(final Complex x, final Complex y) {
         return Complex.ComplexIsConflicted.super.plus(x, y);
      }

      public Complex minus(final Complex x, final Complex y) {
         return Complex.ComplexIsConflicted.super.minus(x, y);
      }

      public Complex times(final Complex x, final Complex y) {
         return Complex.ComplexIsConflicted.super.times(x, y);
      }

      public Complex negate(final Complex x) {
         return Complex.ComplexIsConflicted.super.negate(x);
      }

      public Complex fromInt(final int x) {
         return Complex.ComplexIsConflicted.super.fromInt(x);
      }

      public int toInt(final Complex x) {
         return Complex.ComplexIsConflicted.super.toInt(x);
      }

      public long toLong(final Complex x) {
         return Complex.ComplexIsConflicted.super.toLong(x);
      }

      public float toFloat(final Complex x) {
         return Complex.ComplexIsConflicted.super.toFloat(x);
      }

      public double toDouble(final Complex x) {
         return Complex.ComplexIsConflicted.super.toDouble(x);
      }

      public Object zero() {
         return Numeric.zero$(this);
      }

      public Object one() {
         return Numeric.one$(this);
      }

      public Object abs(final Object x) {
         return Numeric.abs$(this, x);
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.signum$(this, x);
      }

      public Object sign(final Object x) {
         return Numeric.sign$(this, x);
      }

      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
      }

      public boolean lteq(final Object x, final Object y) {
         return Ordering.lteq$(this, x, y);
      }

      public boolean gteq(final Object x, final Object y) {
         return Ordering.gteq$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Ordering.lt$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Ordering.gt$(this, x, y);
      }

      public boolean equiv(final Object x, final Object y) {
         return Ordering.equiv$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Ordering.max$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Ordering.min$(this, x, y);
      }

      public Ordering reverse() {
         return Ordering.reverse$(this);
      }

      public boolean isReverseOf(final Ordering other) {
         return Ordering.isReverseOf$(this, other);
      }

      public Ordering on(final Function1 f) {
         return Ordering.on$(this, f);
      }

      public Ordering orElse(final Ordering other) {
         return Ordering.orElse$(this, other);
      }

      public Ordering orElseBy(final Function1 f, final Ordering ord) {
         return Ordering.orElseBy$(this, f, ord);
      }

      public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
         return Ordering.mkOrderingOps$(this, lhs);
      }

      public Option parseString(final String str) {
         Matcher m = this.regex().matcher(str);
         return (Option)(!m.matches() ? scala.None..MODULE$ : new Some(new Complex(toDouble$1(m.group(1)), toDouble$1(m.group(2)))));
      }

      private Pattern regex$lzycompute() {
         synchronized(this){}

         try {
            if (!bitmap$0) {
               regex = Pattern.compile(scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n        |^\n        | (?= [iI.\\d+-] )               # Assertion that keeps it from matching empty string\n        | (                             # (1 start), Real\n        |      [+-]?\n        |      (?:\n        |           \\d+\n        |           (?: \\. \\d* )?\n        |        |  \\. \\d+\n        |      )\n        |      (?: [eE] [+-]? \\d+ )?\n        |      (?! [iI.\\d] )            # Assertion that separates real/imaginary\n        | )?                            # (1 end)\n        | (                             # (2 start), Imaginary\n        |      \\w*[+-]?\\w*\n        |      (?:\n        |           (?:\n        |                \\d+\n        |                (?: \\. \\d* )?\n        |             |  \\. \\d+\n        |           )\n        |           (?: [eE] [+-]? \\d+ )?\n        |      )?\n        |      [iI]\n        | )?                            # (2 end)\n        | $\n      ")), 4);
               bitmap$0 = true;
            }
         } catch (Throwable var3) {
            throw var3;
         }

         return regex;
      }

      private Pattern regex() {
         return !bitmap$0 ? this.regex$lzycompute() : regex;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ComplexIsFractional$.class);
      }

      private static final double toDouble$1(final String g) {
         return g.isEmpty() ? (double)0.0F : scala.collection.StringOps..MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(g));
      }
   }

   public static class logComplexImpl$ implements UFunc.UImpl {
      public static final logComplexImpl$ MODULE$ = new logComplexImpl$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public Complex apply(final Complex v) {
         return v.log();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(logComplexImpl$.class);
      }
   }

   public static class expComplexImpl$ implements UFunc.UImpl {
      public static final expComplexImpl$ MODULE$ = new expComplexImpl$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public Complex apply(final Complex v) {
         return v.exp();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(expComplexImpl$.class);
      }
   }

   public static class absComplexImpl$ implements UFunc.UImpl {
      public static final absComplexImpl$ MODULE$ = new absComplexImpl$();

      public double apply$mcDD$sp(final double v) {
         return UFunc.UImpl.apply$mcDD$sp$(this, v);
      }

      public float apply$mcDF$sp(final double v) {
         return UFunc.UImpl.apply$mcDF$sp$(this, v);
      }

      public int apply$mcDI$sp(final double v) {
         return UFunc.UImpl.apply$mcDI$sp$(this, v);
      }

      public double apply$mcFD$sp(final float v) {
         return UFunc.UImpl.apply$mcFD$sp$(this, v);
      }

      public float apply$mcFF$sp(final float v) {
         return UFunc.UImpl.apply$mcFF$sp$(this, v);
      }

      public int apply$mcFI$sp(final float v) {
         return UFunc.UImpl.apply$mcFI$sp$(this, v);
      }

      public double apply$mcID$sp(final int v) {
         return UFunc.UImpl.apply$mcID$sp$(this, v);
      }

      public float apply$mcIF$sp(final int v) {
         return UFunc.UImpl.apply$mcIF$sp$(this, v);
      }

      public int apply$mcII$sp(final int v) {
         return UFunc.UImpl.apply$mcII$sp$(this, v);
      }

      public double apply(final Complex v) {
         return v.abs();
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(absComplexImpl$.class);
      }
   }

   public static class powComplexDoubleImpl$ implements UFunc.UImpl2 {
      public static final powComplexDoubleImpl$ MODULE$ = new powComplexDoubleImpl$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex v, final double d) {
         return v.pow(d);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(powComplexDoubleImpl$.class);
      }
   }

   public static class powComplexComplexImpl$ implements UFunc.UImpl2 {
      public static final powComplexComplexImpl$ MODULE$ = new powComplexComplexImpl$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public Complex apply(final Complex v, final Complex d) {
         return v.pow(d);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(powComplexComplexImpl$.class);
      }
   }
}
