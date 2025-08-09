package org.apache.spark.internal;

import java.lang.invoke.SerializedLambda;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.CloseableThreadContext;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.Filter.Result;
import org.apache.logging.log4j.core.LifeCycle.State;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.spark.util.SparkClassUtils$;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\rMbaB)S!\u0003\r\ta\u0017\u0005\u0006E\u0002!\ta\u0019\u0005\bO\u0002\u0001\r\u0011\"\u0003i\u0011\u001d\u0019\b\u00011A\u0005\nQDQa\u001e\u0001\u0005\u0012aDa!a\u0001\u0001\t#AgABA\u0003\u0001\u0005\t9\u0001\u0003\u0006\u0002\n\u0019\u0011)\u0019!C\u0001\u0003\u0017A!\"a\u0005\u0007\u0005\u0003\u0005\u000b\u0011BA\u0007\u0011\u001d\t)B\u0002C\u0001\u0003/Aq!a\u0001\u0007\t\u0003\ty\u0002C\u0005\u0002:\u0001\t\t\u0011b\u0001\u0002<!9\u0011q\b\u0001\u0005\u0012\u0005\u0005\u0003bBA:\u0001\u0011E\u0011Q\u000f\u0005\b\u0003g\u0002A\u0011CA?\u0011\u001d\t\u0019\b\u0001C\t\u0003\u0013Cq!a)\u0001\t#\t)\u000bC\u0004\u0002$\u0002!\t\"!+\t\u000f\u0005\r\u0006\u0001\"\u0005\u0002.\"9\u00111\u0017\u0001\u0005\u0012\u0005U\u0006bBAZ\u0001\u0011E\u0011\u0011\u0018\u0005\b\u0003g\u0003A\u0011CA_\u0011\u001d\t\u0019\r\u0001C\t\u0003\u000bDq!a1\u0001\t#\tI\rC\u0004\u0002D\u0002!\t\"!4\t\u000f\u0005M\u0007\u0001\"\u0005\u0002V\"9\u00111\u001b\u0001\u0005\u0012\u0005e\u0007bBAj\u0001\u0011E\u0011Q\u001c\u0005\b\u0003g\u0002A\u0011CAr\u0011\u001d\t\u0019\u000b\u0001C\t\u0003SDq!a-\u0001\t#\ty\u000fC\u0004\u0002D\u0002!\t\"!>\t\u000f\u0005M\u0007\u0001\"\u0005\u0002|\"9!\u0011\u0001\u0001\u0005\u0012\t\r\u0001b\u0002B\u0006\u0001\u0011E!Q\u0002\u0005\b\u0005\u0017\u0001A\u0011\u0003B\n\u0011%\u0011Y\u0002AI\u0001\n#\u0011i\u0002\u0003\u0005\u00034\u0001!\t\u0001\u0016B\u001b\u0011\u001d\u0011Y\u0004\u0001C\u0005\u0005{9\u0001Ba\u0011S\u0011\u0003!&Q\t\u0004\b#JC\t\u0001\u0016B$\u0011\u001d\t)\u0002\u000bC\u0001\u0005\u0013B\u0011Ba\u0013)\u0001\u0004%IA!\u0014\t\u0013\t=\u0003\u00061A\u0005\n\tE\u0003\u0002\u0003B+Q\u0001\u0006KA!\u0002\t\u0013\t}\u0003\u00061A\u0005\n\t\u0005\u0004\"\u0003B:Q\u0001\u0007I\u0011\u0002B;\u0011!\u0011I\b\u000bQ!\n\t\r\u0004\"\u0003B?Q\u0001\u0007I\u0011\u0002B'\u0011%\u0011y\b\u000ba\u0001\n\u0013\u0011\t\t\u0003\u0005\u0003\u0006\"\u0002\u000b\u0015\u0002B\u0003\u0011%\u0011I\t\u000ba\u0001\n\u0013\u0011i\u0005C\u0005\u0003\f\"\u0002\r\u0011\"\u0003\u0003\u000e\"A!\u0011\u0013\u0015!B\u0013\u0011)\u0001\u0003\u0006\u0003\u0016\"\u0002\r\u0011\"\u0001U\u0005CB!Ba&)\u0001\u0004%\t\u0001\u0016BM\u0011!\u0011i\n\u000bQ!\n\t\r\u0004B\u0003BQQ\u0001\u0007I\u0011\u0001+\u0003N!Q!1\u0015\u0015A\u0002\u0013\u0005AK!*\t\u0011\t%\u0006\u0006)Q\u0005\u0005\u000bA\u0011B!,)\u0005\u0004%\tAa,\t\u0011\t]\u0006\u0006)A\u0005\u0005cCaA!/)\t\u0003\u0019\u0007b\u0002B^Q\u0011%!1\u0001\u0005\b\u0005{CC\u0011\u0002B`\u0011!\u0011y\r\u000bC\u0001)\n\r\u0001b\u0002BiQ\u0011\u0005Ak\u0019\u0005\b\u0005'DC\u0011\u0001+d\u0011!\u0011)\u000e\u000bC\u0001)\n5ca\u0002BlQ\u0001!&\u0011\u001c\u0005\b\u0003+)E\u0011\u0001Bt\u0011%\u0011i/\u0012a\u0001\n\u0013\u0011y\u000fC\u0005\u0003~\u0016\u0003\r\u0011\"\u0003\u0003\u0000\"A11A#!B\u0013\u0011\t\u0010C\u0004\u0003b\u0016#\te!\u0002\t\u000f\r}Q\t\"\u0011\u0004\"!11\u0011F#\u0005B\rDaaa\u000bF\t\u0003\u001a\u0007BBB\u0017\u000b\u0012\u00053\rC\u0004\u00040\u0015#\tEa\u0001\t\u000f\rER\t\"\u0011\u0003\u0004\t9Aj\\4hS:<'BA*U\u0003!Ig\u000e^3s]\u0006d'BA+W\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0006,\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0006\u0019qN]4\u0004\u0001M\u0011\u0001\u0001\u0018\t\u0003;\u0002l\u0011A\u0018\u0006\u0002?\u0006)1oY1mC&\u0011\u0011M\u0018\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005!\u0007CA/f\u0013\t1gL\u0001\u0003V]&$\u0018\u0001\u00027pO~+\u0012!\u001b\t\u0003U6l\u0011a\u001b\u0006\u0003Yb\u000bQa\u001d7gi)L!A\\6\u0003\r1{wmZ3sQ\t\u0011\u0001\u000f\u0005\u0002^c&\u0011!O\u0018\u0002\niJ\fgn]5f]R\f\u0001\u0002\\8h?~#S-\u001d\u000b\u0003IVDqA^\u0002\u0002\u0002\u0003\u0007\u0011.A\u0002yIE\nq\u0001\\8h\u001d\u0006lW-F\u0001z!\tQx0D\u0001|\u0015\taX0\u0001\u0003mC:<'\"\u0001@\u0002\t)\fg/Y\u0005\u0004\u0003\u0003Y(AB*ue&tw-A\u0002m_\u001e\u0014\u0001\u0003T8h'R\u0014\u0018N\\4D_:$X\r\u001f;\u0014\u0005\u0019a\u0016AA:d+\t\ti\u0001E\u0002^\u0003\u001fI1!!\u0005_\u00055\u0019FO]5oO\u000e{g\u000e^3yi\u0006\u00191o\u0019\u0011\u0002\rqJg.\u001b;?)\u0011\tI\"!\b\u0011\u0007\u0005ma!D\u0001\u0001\u0011\u001d\tI!\u0003a\u0001\u0003\u001b!B!!\t\u0002*A!\u00111EA\u0013\u001b\u0005\u0011\u0016bAA\u0014%\n\u0011R*Z:tC\u001e,w+\u001b;i\u0007>tG/\u001a=u\u0011\u001d\tYC\u0003a\u0001\u0003[\tA!\u0019:hgB)Q,a\f\u00024%\u0019\u0011\u0011\u00070\u0003\u0015q\u0012X\r]3bi\u0016$g\b\u0005\u0003\u0002$\u0005U\u0012bAA\u001c%\n\u0019Q\nR\"\u0002!1{wm\u0015;sS:<7i\u001c8uKb$H\u0003BA\r\u0003{Aq!!\u0003\f\u0001\u0004\ti!\u0001\bxSRDGj\\4D_:$X\r\u001f;\u0015\t\u0005\r\u0013q\n\u000b\u0004I\u0006\u0015\u0003\u0002CA$\u0019\u0011\u0005\r!!\u0013\u0002\t\t|G-\u001f\t\u0005;\u0006-C-C\u0002\u0002Ny\u0013\u0001\u0002\u00102z]\u0006lWM\u0010\u0005\b\u0003#b\u0001\u0019AA*\u0003\u001d\u0019wN\u001c;fqR\u0004\u0002\"!\u0016\u0002\\\u0005}\u0013qL\u0007\u0003\u0003/R1!!\u0017~\u0003\u0011)H/\u001b7\n\t\u0005u\u0013q\u000b\u0002\u0004\u001b\u0006\u0004\b\u0003BA1\u0003_rA!a\u0019\u0002lA\u0019\u0011Q\r0\u000e\u0005\u0005\u001d$bAA55\u00061AH]8pizJ1!!\u001c_\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011AA9\u0015\r\tiGX\u0001\bY><\u0017J\u001c4p)\r!\u0017q\u000f\u0005\t\u0003sjA\u00111\u0001\u0002|\u0005\u0019Qn]4\u0011\u000bu\u000bY%a\u0018\u0015\u0007\u0011\fy\bC\u0004\u0002\u0002:\u0001\r!a!\u0002\u000b\u0015tGO]=\u0011\t\u0005\r\u0012QQ\u0005\u0004\u0003\u000f\u0013&\u0001\u0003'pO\u0016sGO]=\u0015\u000b\u0011\fY)!$\t\u000f\u0005\u0005u\u00021\u0001\u0002\u0004\"9\u0011qR\bA\u0002\u0005E\u0015!\u0003;ie><\u0018M\u00197f!\u0011\t\u0019*!(\u000f\t\u0005U\u0015\u0011\u0014\b\u0005\u0003K\n9*C\u0001`\u0013\r\tYJX\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\ty*!)\u0003\u0013QC'o\\<bE2,'bAAN=\u0006AAn\\4EK\n,x\rF\u0002e\u0003OC\u0001\"!\u001f\u0011\t\u0003\u0007\u00111\u0010\u000b\u0004I\u0006-\u0006bBAA#\u0001\u0007\u00111\u0011\u000b\u0006I\u0006=\u0016\u0011\u0017\u0005\b\u0003\u0003\u0013\u0002\u0019AAB\u0011\u001d\tyI\u0005a\u0001\u0003#\u000b\u0001\u0002\\8h)J\f7-\u001a\u000b\u0004I\u0006]\u0006\u0002CA='\u0011\u0005\r!a\u001f\u0015\u0007\u0011\fY\fC\u0004\u0002\u0002R\u0001\r!a!\u0015\u000b\u0011\fy,!1\t\u000f\u0005\u0005U\u00031\u0001\u0002\u0004\"9\u0011qR\u000bA\u0002\u0005E\u0015A\u00037pO^\u000b'O\\5oOR\u0019A-a2\t\u0011\u0005ed\u0003\"a\u0001\u0003w\"2\u0001ZAf\u0011\u001d\t\ti\u0006a\u0001\u0003\u0007#R\u0001ZAh\u0003#Dq!!!\u0019\u0001\u0004\t\u0019\tC\u0004\u0002\u0010b\u0001\r!!%\u0002\u00111|w-\u0012:s_J$2\u0001ZAl\u0011!\tI(\u0007CA\u0002\u0005mDc\u00013\u0002\\\"9\u0011\u0011\u0011\u000eA\u0002\u0005\rE#\u00023\u0002`\u0006\u0005\bbBAA7\u0001\u0007\u00111\u0011\u0005\b\u0003\u001f[\u0002\u0019AAI)\u0015!\u0017Q]At\u0011!\tI\b\bCA\u0002\u0005m\u0004bBAH9\u0001\u0007\u0011\u0011\u0013\u000b\u0006I\u0006-\u0018Q\u001e\u0005\t\u0003sjB\u00111\u0001\u0002|!9\u0011qR\u000fA\u0002\u0005EE#\u00023\u0002r\u0006M\b\u0002CA==\u0011\u0005\r!a\u001f\t\u000f\u0005=e\u00041\u0001\u0002\u0012R)A-a>\u0002z\"A\u0011\u0011P\u0010\u0005\u0002\u0004\tY\bC\u0004\u0002\u0010~\u0001\r!!%\u0015\u000b\u0011\fi0a@\t\u0011\u0005e\u0004\u0005\"a\u0001\u0003wBq!a$!\u0001\u0004\t\t*\u0001\bjgR\u0013\u0018mY3F]\u0006\u0014G.\u001a3\u0015\u0005\t\u0015\u0001cA/\u0003\b%\u0019!\u0011\u00020\u0003\u000f\t{w\u000e\\3b]\u0006A\u0012N\\5uS\u0006d\u0017N_3M_\u001eLeMT3dKN\u001c\u0018M]=\u0015\u0007\u0011\u0014y\u0001C\u0004\u0003\u0012\t\u0002\rA!\u0002\u0002\u001b%\u001c\u0018J\u001c;feB\u0014X\r^3s)\u0019\u0011)A!\u0006\u0003\u0018!9!\u0011C\u0012A\u0002\t\u0015\u0001\"\u0003B\rGA\u0005\t\u0019\u0001B\u0003\u0003\u0019\u0019\u0018\u000e\\3oi\u0006\u0011\u0013N\\5uS\u0006d\u0017N_3M_\u001eLeMT3dKN\u001c\u0018M]=%I\u00164\u0017-\u001e7uII*\"Aa\b+\t\t\u0015!\u0011E\u0016\u0003\u0005G\u0001BA!\n\u000305\u0011!q\u0005\u0006\u0005\u0005S\u0011Y#A\u0005v]\u000eDWmY6fI*\u0019!Q\u00060\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u00032\t\u001d\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006!\u0012N\\5uS\u0006d\u0017N_3G_J\u001cWMZ;mYf$R\u0001\u001aB\u001c\u0005sAqA!\u0005&\u0001\u0004\u0011)\u0001C\u0004\u0003\u001a\u0015\u0002\rA!\u0002\u0002#%t\u0017\u000e^5bY&TX\rT8hO&tw\rF\u0003e\u0005\u007f\u0011\t\u0005C\u0004\u0003\u0012\u0019\u0002\rA!\u0002\t\u000f\tea\u00051\u0001\u0003\u0006\u00059Aj\\4hS:<\u0007cAA\u0012QM\u0011\u0001\u0006\u0018\u000b\u0003\u0005\u000b\n1\"\u001b8ji&\fG.\u001b>fIV\u0011!QA\u0001\u0010S:LG/[1mSj,Gm\u0018\u0013fcR\u0019AMa\u0015\t\u0011Y\\\u0013\u0011!a\u0001\u0005\u000b\tA\"\u001b8ji&\fG.\u001b>fI\u0002B3\u0001\fB-!\ri&1L\u0005\u0004\u0005;r&\u0001\u0003<pY\u0006$\u0018\u000e\\3\u0002!\u0011,g-Y;miJ{w\u000e\u001e'fm\u0016dWC\u0001B2!\u0011\u0011)Ga\u001c\u000e\u0005\t\u001d$\u0002\u0002B5\u0005W\nQ\u0001\\8hi)T1A!\u001cW\u0003\u001dawnZ4j]\u001eLAA!\u001d\u0003h\t)A*\u001a<fY\u0006!B-\u001a4bk2$(k\\8u\u0019\u00164X\r\\0%KF$2\u0001\u001aB<\u0011!1h&!AA\u0002\t\r\u0014!\u00053fM\u0006,H\u000e\u001e*p_RdUM^3mA!\u001aqF!\u0017\u0002/\u0011,g-Y;miN\u0003\u0018M]6M_\u001e$$nQ8oM&<\u0017a\u00073fM\u0006,H\u000e^*qCJ\\Gj\\45U\u000e{gNZ5h?\u0012*\u0017\u000fF\u0002e\u0005\u0007C\u0001B^\u0019\u0002\u0002\u0003\u0007!QA\u0001\u0019I\u00164\u0017-\u001e7u'B\f'o\u001b'pORR7i\u001c8gS\u001e\u0004\u0003f\u0001\u001a\u0003Z\u0005A2\u000f\u001e:vGR,(/\u001a3M_\u001e<\u0017N\\4F]\u0006\u0014G.\u001a3\u00029M$(/^2ukJ,G\rT8hO&tw-\u00128bE2,Gm\u0018\u0013fcR\u0019AMa$\t\u0011Y$\u0014\u0011!a\u0001\u0005\u000b\t\u0011d\u001d;sk\u000e$XO]3e\u0019><w-\u001b8h\u000b:\f'\r\\3eA!\u001aQG!\u0017\u00021M\u0004\u0018M]6TQ\u0016dG\u000e\u00165sKNDw\u000e\u001c3MKZ,G.\u0001\u000fta\u0006\u00148n\u00155fY2$\u0006N]3tQ>dG\rT3wK2|F%Z9\u0015\u0007\u0011\u0014Y\n\u0003\u0005wo\u0005\u0005\t\u0019\u0001B2\u0003e\u0019\b/\u0019:l'\",G\u000e\u001c+ie\u0016\u001c\bn\u001c7e\u0019\u00164X\r\u001c\u0011)\u0007a\u0012I&\u0001\ntKRdun\u001a'fm\u0016d\u0007K]5oi\u0016$\u0017AF:fi2{w\rT3wK2\u0004&/\u001b8uK\u0012|F%Z9\u0015\u0007\u0011\u00149\u000b\u0003\u0005wu\u0005\u0005\t\u0019\u0001B\u0003\u0003M\u0019X\r\u001e'pO2+g/\u001a7Qe&tG/\u001a3!Q\rY$\u0011L\u0001\tS:LG\u000fT8dWV\u0011!\u0011\u0017\t\u0004u\nM\u0016b\u0001B[w\n1qJ\u00196fGR\f\u0011\"\u001b8ji2{7m\u001b\u0011\u0002\u0019Ut\u0017N\\5uS\u0006d\u0017N_3\u0002\u0011%\u001cHj\\45UJ\na\u0003\\8hO\u0016\u0014x+\u001b;i\u0007V\u001cHo\\7D_:4\u0017n\u001a\u000b\u0005\u0005\u000b\u0011\t\rC\u0004\u0003D\u0002\u0003\rA!2\u0002\r1|wmZ3s!\u0011\u00119M!4\u000e\u0005\t%'\u0002\u0002Bf\u0005O\nAaY8sK&\u0019aN!3\u00023%\u001cHn\\45UJ\"UMZ1vYR\u001cuN\u001c4jOV\u0014X\rZ\u0001\u0018K:\f'\r\\3TiJ,8\r^;sK\u0012dunZ4j]\u001e\f\u0001\u0004Z5tC\ndWm\u0015;sk\u000e$XO]3e\u0019><w-\u001b8h\u0003iI7o\u0015;sk\u000e$XO]3e\u0019><w-\u001b8h\u000b:\f'\r\\3e\u0005]\u0019\u0006/\u0019:l'\",G\u000e\u001c'pO\u001eLgn\u001a$jYR,'oE\u0002F\u00057\u0004BA!8\u0003d6\u0011!q\u001c\u0006\u0005\u0005C\u0014I-\u0001\u0004gS2$XM]\u0005\u0005\u0005K\u0014yN\u0001\bBEN$(/Y2u\r&dG/\u001a:\u0015\u0005\t%\bc\u0001Bv\u000b6\t\u0001&\u0001\u0004ti\u0006$Xo]\u000b\u0003\u0005c\u0004BAa=\u0003z6\u0011!Q\u001f\u0006\u0005\u0005o\u0014I-A\u0005MS\u001a,7)_2mK&!!1 B{\u0005\u0015\u0019F/\u0019;f\u0003)\u0019H/\u0019;vg~#S-\u001d\u000b\u0004I\u000e\u0005\u0001\u0002\u0003<I\u0003\u0003\u0005\rA!=\u0002\u000fM$\u0018\r^;tAQ!1qAB\u000b!\u0011\u0019Iaa\u0004\u000f\t\t\u001d71B\u0005\u0005\u0007\u001b\u0011I-\u0001\u0004GS2$XM]\u0005\u0005\u0007#\u0019\u0019B\u0001\u0004SKN,H\u000e\u001e\u0006\u0005\u0007\u001b\u0011I\rC\u0004\u0004\u0018)\u0003\ra!\u0007\u0002\u00111|w-\u0012<f]R\u0004BAa2\u0004\u001c%!1Q\u0004Be\u0005!aunZ#wK:$\u0018\u0001C4fiN#\u0018\r^3\u0015\u0005\r\r\u0002\u0003BB\u0013\u0005stAAa2\u0004(%!!q\u001fBe\u0003)Ig.\u001b;jC2L'0Z\u0001\u0006gR\f'\u000f^\u0001\u0005gR|\u0007/A\u0005jgN#\u0018M\u001d;fI\u0006I\u0011n]*u_B\u0004X\r\u001a"
)
public interface Logging {
   static void uninitialize() {
      Logging$.MODULE$.uninitialize();
   }

   static Object initLock() {
      return Logging$.MODULE$.initLock();
   }

   Logger org$apache$spark$internal$Logging$$log_();

   void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1);

   // $FF: synthetic method
   static String logName$(final Logging $this) {
      return $this.logName();
   }

   default String logName() {
      return .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getName()), "$");
   }

   // $FF: synthetic method
   static Logger log$(final Logging $this) {
      return $this.log();
   }

   default Logger log() {
      if (this.org$apache$spark$internal$Logging$$log_() == null) {
         this.initializeLogIfNecessary(false);
         this.org$apache$spark$internal$Logging$$log__$eq(LoggerFactory.getLogger(this.logName()));
      }

      return this.org$apache$spark$internal$Logging$$log_();
   }

   // $FF: synthetic method
   static LogStringContext LogStringContext$(final Logging $this, final StringContext sc) {
      return $this.LogStringContext(sc);
   }

   default LogStringContext LogStringContext(final StringContext sc) {
      return new LogStringContext(sc);
   }

   // $FF: synthetic method
   static void withLogContext$(final Logging $this, final Map context, final Function0 body) {
      $this.withLogContext(context, body);
   }

   default void withLogContext(final Map context, final Function0 body) {
      Option closeableThreadContextOpt = (Option)(Logging$.MODULE$.isStructuredLoggingEnabled() ? new Some(CloseableThreadContext.putAll(context)) : scala.None..MODULE$);

      try {
         body.apply$mcV$sp();
      } finally {
         closeableThreadContextOpt.foreach((x$1) -> {
            $anonfun$withLogContext$1(x$1);
            return BoxedUnit.UNIT;
         });
      }

   }

   // $FF: synthetic method
   static void logInfo$(final Logging $this, final Function0 msg) {
      $this.logInfo(msg);
   }

   default void logInfo(final Function0 msg) {
      if (this.log().isInfoEnabled()) {
         this.log().info((String)msg.apply());
      }
   }

   // $FF: synthetic method
   static void logInfo$(final Logging $this, final LogEntry entry) {
      $this.logInfo(entry);
   }

   default void logInfo(final LogEntry entry) {
      if (this.log().isInfoEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().info(entry.message()));
      }
   }

   // $FF: synthetic method
   static void logInfo$(final Logging $this, final LogEntry entry, final Throwable throwable) {
      $this.logInfo(entry, throwable);
   }

   default void logInfo(final LogEntry entry, final Throwable throwable) {
      if (this.log().isInfoEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().info(entry.message(), throwable));
      }
   }

   // $FF: synthetic method
   static void logDebug$(final Logging $this, final Function0 msg) {
      $this.logDebug(msg);
   }

   default void logDebug(final Function0 msg) {
      if (this.log().isDebugEnabled()) {
         this.log().debug((String)msg.apply());
      }
   }

   // $FF: synthetic method
   static void logDebug$(final Logging $this, final LogEntry entry) {
      $this.logDebug(entry);
   }

   default void logDebug(final LogEntry entry) {
      if (this.log().isDebugEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().debug(entry.message()));
      }
   }

   // $FF: synthetic method
   static void logDebug$(final Logging $this, final LogEntry entry, final Throwable throwable) {
      $this.logDebug(entry, throwable);
   }

   default void logDebug(final LogEntry entry, final Throwable throwable) {
      if (this.log().isDebugEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().debug(entry.message(), throwable));
      }
   }

   // $FF: synthetic method
   static void logTrace$(final Logging $this, final Function0 msg) {
      $this.logTrace(msg);
   }

   default void logTrace(final Function0 msg) {
      if (this.log().isTraceEnabled()) {
         this.log().trace((String)msg.apply());
      }
   }

   // $FF: synthetic method
   static void logTrace$(final Logging $this, final LogEntry entry) {
      $this.logTrace(entry);
   }

   default void logTrace(final LogEntry entry) {
      if (this.log().isTraceEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().trace(entry.message()));
      }
   }

   // $FF: synthetic method
   static void logTrace$(final Logging $this, final LogEntry entry, final Throwable throwable) {
      $this.logTrace(entry, throwable);
   }

   default void logTrace(final LogEntry entry, final Throwable throwable) {
      if (this.log().isTraceEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().trace(entry.message(), throwable));
      }
   }

   // $FF: synthetic method
   static void logWarning$(final Logging $this, final Function0 msg) {
      $this.logWarning(msg);
   }

   default void logWarning(final Function0 msg) {
      if (this.log().isWarnEnabled()) {
         this.log().warn((String)msg.apply());
      }
   }

   // $FF: synthetic method
   static void logWarning$(final Logging $this, final LogEntry entry) {
      $this.logWarning(entry);
   }

   default void logWarning(final LogEntry entry) {
      if (this.log().isWarnEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().warn(entry.message()));
      }
   }

   // $FF: synthetic method
   static void logWarning$(final Logging $this, final LogEntry entry, final Throwable throwable) {
      $this.logWarning(entry, throwable);
   }

   default void logWarning(final LogEntry entry, final Throwable throwable) {
      if (this.log().isWarnEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().warn(entry.message(), throwable));
      }
   }

   // $FF: synthetic method
   static void logError$(final Logging $this, final Function0 msg) {
      $this.logError(msg);
   }

   default void logError(final Function0 msg) {
      if (this.log().isErrorEnabled()) {
         this.log().error((String)msg.apply());
      }
   }

   // $FF: synthetic method
   static void logError$(final Logging $this, final LogEntry entry) {
      $this.logError(entry);
   }

   default void logError(final LogEntry entry) {
      if (this.log().isErrorEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().error(entry.message()));
      }
   }

   // $FF: synthetic method
   static void logError$(final Logging $this, final LogEntry entry, final Throwable throwable) {
      $this.logError(entry, throwable);
   }

   default void logError(final LogEntry entry, final Throwable throwable) {
      if (this.log().isErrorEnabled()) {
         this.withLogContext(entry.context(), (JFunction0.mcV.sp)() -> this.log().error(entry.message(), throwable));
      }
   }

   // $FF: synthetic method
   static void logInfo$(final Logging $this, final Function0 msg, final Throwable throwable) {
      $this.logInfo(msg, throwable);
   }

   default void logInfo(final Function0 msg, final Throwable throwable) {
      if (this.log().isInfoEnabled()) {
         this.log().info((String)msg.apply(), throwable);
      }
   }

   // $FF: synthetic method
   static void logDebug$(final Logging $this, final Function0 msg, final Throwable throwable) {
      $this.logDebug(msg, throwable);
   }

   default void logDebug(final Function0 msg, final Throwable throwable) {
      if (this.log().isDebugEnabled()) {
         this.log().debug((String)msg.apply(), throwable);
      }
   }

   // $FF: synthetic method
   static void logTrace$(final Logging $this, final Function0 msg, final Throwable throwable) {
      $this.logTrace(msg, throwable);
   }

   default void logTrace(final Function0 msg, final Throwable throwable) {
      if (this.log().isTraceEnabled()) {
         this.log().trace((String)msg.apply(), throwable);
      }
   }

   // $FF: synthetic method
   static void logWarning$(final Logging $this, final Function0 msg, final Throwable throwable) {
      $this.logWarning(msg, throwable);
   }

   default void logWarning(final Function0 msg, final Throwable throwable) {
      if (this.log().isWarnEnabled()) {
         this.log().warn((String)msg.apply(), throwable);
      }
   }

   // $FF: synthetic method
   static void logError$(final Logging $this, final Function0 msg, final Throwable throwable) {
      $this.logError(msg, throwable);
   }

   default void logError(final Function0 msg, final Throwable throwable) {
      if (this.log().isErrorEnabled()) {
         this.log().error((String)msg.apply(), throwable);
      }
   }

   // $FF: synthetic method
   static boolean isTraceEnabled$(final Logging $this) {
      return $this.isTraceEnabled();
   }

   default boolean isTraceEnabled() {
      return this.log().isTraceEnabled();
   }

   // $FF: synthetic method
   static void initializeLogIfNecessary$(final Logging $this, final boolean isInterpreter) {
      $this.initializeLogIfNecessary(isInterpreter);
   }

   default void initializeLogIfNecessary(final boolean isInterpreter) {
      this.initializeLogIfNecessary(isInterpreter, false);
   }

   // $FF: synthetic method
   static boolean initializeLogIfNecessary$(final Logging $this, final boolean isInterpreter, final boolean silent) {
      return $this.initializeLogIfNecessary(isInterpreter, silent);
   }

   default boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      if (!Logging$.MODULE$.org$apache$spark$internal$Logging$$initialized()) {
         synchronized(Logging$.MODULE$.initLock()){}

         try {
            if (!Logging$.MODULE$.org$apache$spark$internal$Logging$$initialized()) {
               this.initializeLogging(isInterpreter, silent);
               boolean var4 = true;
               return var4;
            }
         } catch (Throwable var6) {
            throw var6;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static boolean initializeLogIfNecessary$default$2$(final Logging $this) {
      return $this.initializeLogIfNecessary$default$2();
   }

   default boolean initializeLogIfNecessary$default$2() {
      return false;
   }

   // $FF: synthetic method
   static void initializeForcefully$(final Logging $this, final boolean isInterpreter, final boolean silent) {
      $this.initializeForcefully(isInterpreter, silent);
   }

   default void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      this.initializeLogging(isInterpreter, silent);
   }

   private void initializeLogging(final boolean isInterpreter, final boolean silent) {
      if (Logging$.MODULE$.org$apache$spark$internal$Logging$$isLog4j2()) {
         org.apache.logging.log4j.core.Logger rootLogger = (org.apache.logging.log4j.core.Logger)LogManager.getRootLogger();
         if (Logging$.MODULE$.org$apache$spark$internal$Logging$$defaultSparkLog4jConfig() || Logging$.MODULE$.islog4j2DefaultConfigured()) {
            Logging$.MODULE$.org$apache$spark$internal$Logging$$defaultSparkLog4jConfig_$eq(true);
            String defaultLogProps = Logging$.MODULE$.isStructuredLoggingEnabled() ? "org/apache/spark/log4j2-json-layout.properties" : "org/apache/spark/log4j2-defaults.properties";
            Option var6 = scala.Option..MODULE$.apply(SparkClassUtils$.MODULE$.getSparkClassLoader().getResource(defaultLogProps));
            if (var6 instanceof Some) {
               Some var7 = (Some)var6;
               URL url = (URL)var7.value();
               LoggerContext context = (LoggerContext)LogManager.getContext(false);
               context.setConfigLocation(url.toURI());
               if (!silent) {
                  System.err.println("Using Spark's default log4j profile: " + defaultLogProps);
                  Logging$.MODULE$.setLogLevelPrinted_$eq(true);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var13 = BoxedUnit.UNIT;
               }
            } else {
               if (!scala.None..MODULE$.equals(var6)) {
                  throw new MatchError(var6);
               }

               System.err.println("Spark was unable to load " + defaultLogProps);
               BoxedUnit var14 = BoxedUnit.UNIT;
            }
         }

         if (Logging$.MODULE$.org$apache$spark$internal$Logging$$defaultRootLevel() == null) {
            Logging$.MODULE$.org$apache$spark$internal$Logging$$defaultRootLevel_$eq(rootLogger.getLevel());
         }

         if (isInterpreter) {
            label62: {
               org.apache.logging.log4j.core.Logger replLogger = (org.apache.logging.log4j.core.Logger)LogManager.getLogger(this.logName());
               Level replLevel = Logging$.MODULE$.org$apache$spark$internal$Logging$$loggerWithCustomConfig(replLogger) ? replLogger.getLevel() : Level.WARN;
               Level var12 = rootLogger.getLevel();
               if (replLevel == null) {
                  if (var12 == null) {
                     break label62;
                  }
               } else if (replLevel.equals(var12)) {
                  break label62;
               }

               if (!silent) {
                  System.err.printf("Setting default log level to \"%s\".\n", replLevel);
                  System.err.println("To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).");
                  Logging$.MODULE$.setLogLevelPrinted_$eq(true);
               }

               Logging$.MODULE$.sparkShellThresholdLevel_$eq(replLevel);
               scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(rootLogger.getAppenders()).asScala().foreach((x0$1) -> {
                  $anonfun$initializeLogging$1(x0$1);
                  return BoxedUnit.UNIT;
               });
            }
         }
      }

      Logging$.MODULE$.org$apache$spark$internal$Logging$$initialized_$eq(true);
      this.log();
   }

   // $FF: synthetic method
   static void $anonfun$withLogContext$1(final CloseableThreadContext.Instance x$1) {
      x$1.close();
   }

   // $FF: synthetic method
   static void $anonfun$initializeLogging$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         Appender ca = (Appender)x0$1._2();
         if (ca instanceof ConsoleAppender) {
            ConsoleAppender var4 = (ConsoleAppender)ca;
            var4.addFilter(new SparkShellLoggingFilter());
            BoxedUnit var5 = BoxedUnit.UNIT;
            return;
         }
      }

      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   static void $init$(final Logging $this) {
      $this.org$apache$spark$internal$Logging$$log__$eq((Logger)null);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class LogStringContext {
      private final StringContext sc;
      // $FF: synthetic field
      public final Logging $outer;

      public StringContext sc() {
         return this.sc;
      }

      public MessageWithContext log(final Seq args) {
         Iterator processedParts = this.sc().parts().iterator();
         StringBuilder sb = new StringBuilder(scala.StringContext..MODULE$.processEscapes((String)processedParts.next()));
         HashMap context = new HashMap();
         args.foreach((mdc) -> {
            String value = mdc.value() != null ? mdc.value().toString() : null;
            sb.append(value);
            if (Logging$.MODULE$.isStructuredLoggingEnabled()) {
               context.put(mdc.key().name(), value);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return processedParts.hasNext() ? sb.append(scala.StringContext..MODULE$.processEscapes((String)processedParts.next())) : BoxedUnit.UNIT;
         });
         return new MessageWithContext(sb.toString(), context);
      }

      // $FF: synthetic method
      public Logging org$apache$spark$internal$Logging$LogStringContext$$$outer() {
         return this.$outer;
      }

      public LogStringContext(final StringContext sc) {
         this.sc = sc;
         if (Logging.this == null) {
            throw null;
         } else {
            this.$outer = Logging.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class SparkShellLoggingFilter extends AbstractFilter {
      private LifeCycle.State status;

      private LifeCycle.State status() {
         return this.status;
      }

      private void status_$eq(final LifeCycle.State x$1) {
         this.status = x$1;
      }

      public Filter.Result filter(final LogEvent logEvent) {
         if (Logging$.MODULE$.sparkShellThresholdLevel() == null) {
            return Result.NEUTRAL;
         } else if (logEvent.getLevel().isMoreSpecificThan(Logging$.MODULE$.sparkShellThresholdLevel())) {
            return Result.NEUTRAL;
         } else {
            org.apache.logging.log4j.core.Logger logger = (org.apache.logging.log4j.core.Logger)LogManager.getLogger(logEvent.getLoggerName());
            return Logging$.MODULE$.org$apache$spark$internal$Logging$$loggerWithCustomConfig(logger) ? Result.NEUTRAL : Result.DENY;
         }
      }

      public LifeCycle.State getState() {
         return this.status();
      }

      public void initialize() {
         this.status_$eq(State.INITIALIZED);
      }

      public void start() {
         this.status_$eq(State.STARTED);
      }

      public void stop() {
         this.status_$eq(State.STOPPED);
      }

      public boolean isStarted() {
         boolean var2;
         label23: {
            LifeCycle.State var10000 = this.status();
            LifeCycle.State var1 = State.STARTED;
            if (var10000 == null) {
               if (var1 == null) {
                  break label23;
               }
            } else if (var10000.equals(var1)) {
               break label23;
            }

            var2 = false;
            return var2;
         }

         var2 = true;
         return var2;
      }

      public boolean isStopped() {
         boolean var2;
         label23: {
            LifeCycle.State var10000 = this.status();
            LifeCycle.State var1 = State.STOPPED;
            if (var10000 == null) {
               if (var1 == null) {
                  break label23;
               }
            } else if (var10000.equals(var1)) {
               break label23;
            }

            var2 = false;
            return var2;
         }

         var2 = true;
         return var2;
      }

      public SparkShellLoggingFilter() {
         this.status = State.INITIALIZING;
      }
   }
}
