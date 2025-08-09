package org.apache.spark.streaming.util;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RejectedExecutionException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContextExecutorService;
import scala.concurrent.Future;
import scala.concurrent.duration.package;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction3;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\rMd!\u0002'N\u0001=;\u0006\u0002\u00032\u0001\u0005\u0003\u0005\u000b\u0011\u00023\t\u0011!\u0004!\u0011!Q\u0001\n%D\u0001B\u001e\u0001\u0003\u0002\u0003\u0006Ia\u001e\u0005\t}\u0002\u0011\t\u0011)A\u0005\u007f\"I\u0011q\u0001\u0001\u0003\u0002\u0003\u0006Ia \u0005\u000b\u0003\u0013\u0001!\u0011!Q\u0001\n\u0005-\u0001bBA\t\u0001\u0011\u0005\u00111\u0003\u0005\n\u0003G\u0001!\u0019!C\u0005\u0003KA\u0001B!1\u0001A\u0003%\u0011q\u0005\u0005\n\u0005\u0007\u0004!\u0019!C\u0005\u0005\u000bD\u0001Ba2\u0001A\u0003%!1\r\u0005\n\u0005\u0013\u0004!\u0019!C\u0005\u0003gC\u0001Ba3\u0001A\u0003%\u0011Q\u0017\u0005\n\u0005\u001b\u0004!\u0019!C\u0005\u0005\u001fD\u0001B!8\u0001A\u0003%!\u0011\u001b\u0005\n\u0005;\u0003!\u0019!C\u0005\u0005?D\u0001Ba:\u0001A\u0003%!\u0011\u001d\u0005\b\u0005S\u0004A\u0011KAZ\u0011%\u0011Y\u000f\u0001a\u0001\n\u0013\u0011)\rC\u0005\u0003n\u0002\u0001\r\u0011\"\u0003\u0003p\"A!\u0011 \u0001!B\u0013\u0011\u0019\u0007C\u0005\u0003|\u0002\u0001\r\u0011\"\u0003\u0003~\"I1Q\u0001\u0001A\u0002\u0013%1q\u0001\u0005\t\u0007\u0017\u0001\u0001\u0015)\u0003\u0003\u0000\"I1Q\u0002\u0001A\u0002\u0013%\u0011q\r\u0005\n\u0007\u001f\u0001\u0001\u0019!C\u0005\u0007#A\u0001b!\u0006\u0001A\u0003&\u0011\u0011\u000e\u0005\n\u0007/\u0001\u0001\u0019!C\u0005\u0003OB\u0011b!\u0007\u0001\u0001\u0004%Iaa\u0007\t\u0011\r}\u0001\u0001)Q\u0005\u0003SBqa!\t\u0001\t\u0003\u0019\u0019\u0003C\u0004\u0004@\u0001!\ta!\u0011\t\u000f\r5\u0003\u0001\"\u0001\u0004P!91q\u000b\u0001\u0005\u0002\re\u0003bBB2\u0001\u0011\u00051Q\r\u0005\b\u0007O\u0002A\u0011BB5\u0011\u001d\u0019y\u0007\u0001C\u0005\u0007KBqa!\u001d\u0001\t\u0013\u0019)g\u0002\u0005\u0002<5C\taTA\u001f\r\u001daU\n#\u0001P\u0003\u007fAq!!\u0005)\t\u0003\t9E\u0002\u0004\u0002J!\u0002\u00151\n\u0005\u000b\u0003KR#Q3A\u0005\u0002\u0005\u001d\u0004BCA8U\tE\t\u0015!\u0003\u0002j!Q\u0011\u0011\u000f\u0016\u0003\u0016\u0004%\t!a\u001a\t\u0015\u0005M$F!E!\u0002\u0013\tI\u0007\u0003\u0006\u0002v)\u0012)\u001a!C\u0001\u0003oB\u0011\"!\u001f+\u0005#\u0005\u000b\u0011B5\t\u000f\u0005E!\u0006\"\u0001\u0002|!I\u0011q\u0011\u0016\u0002\u0002\u0013\u0005\u0011\u0011\u0012\u0005\n\u0003#S\u0013\u0013!C\u0001\u0003'C\u0011\"!++#\u0003%\t!a%\t\u0013\u0005-&&%A\u0005\u0002\u00055\u0006\"CAYU\u0005\u0005I\u0011IAZ\u0011%\t\u0019MKA\u0001\n\u0003\t)\rC\u0005\u0002H*\n\t\u0011\"\u0001\u0002J\"I\u0011Q\u001b\u0016\u0002\u0002\u0013\u0005\u0013q\u001b\u0005\n\u0003CT\u0013\u0011!C\u0001\u0003GD\u0011\"a:+\u0003\u0003%\t%!;\t\u0013\u00055(&!A\u0005B\u0005=\b\"CAyU\u0005\u0005I\u0011IAz\u0011%\t)PKA\u0001\n\u0003\n9pB\u0005\u0002|\"\n\t\u0011#\u0001\u0002~\u001aI\u0011\u0011\n\u0015\u0002\u0002#\u0005\u0011q \u0005\b\u0003#\u0001E\u0011\u0001B\f\u0011%\t\t\u0010QA\u0001\n\u000b\n\u0019\u0010C\u0005\u0003\u001a\u0001\u000b\t\u0011\"!\u0003\u001c!I!1\u0005!\u0002\u0002\u0013\u0005%Q\u0005\u0005\n\u0005o\u0001\u0015\u0011!C\u0005\u0005sA\u0011B!\u0011)\u0005\u0004%\tAa\u0011\t\u0011\tM\u0003\u0006)A\u0005\u0005\u000bBqA!\u0016)\t\u0003\u00119\u0006C\u0004\u0003`!\"\tA!\u0019\t\u000f\t\u0015\u0004\u0006\"\u0001\u0003h!9!\u0011\u0011\u0015\u0005\u0002\t\r%A\u0006$jY\u0016\u0014\u0015m]3e/JLG/Z!iK\u0006$Gj\\4\u000b\u00059{\u0015\u0001B;uS2T!\u0001U)\u0002\u0013M$(/Z1nS:<'B\u0001*T\u0003\u0015\u0019\b/\u0019:l\u0015\t!V+\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002-\u0006\u0019qN]4\u0014\u0007\u0001AF\f\u0005\u0002Z56\tQ*\u0003\u0002\\\u001b\niqK]5uK\u0006CW-\u00193M_\u001e\u0004\"!\u00181\u000e\u0003yS!aX)\u0002\u0011%tG/\u001a:oC2L!!\u00190\u0003\u000f1{wmZ5oO\u0006!1m\u001c8g\u0007\u0001\u0001\"!\u001a4\u000e\u0003EK!aZ)\u0003\u0013M\u0003\u0018M]6D_:4\u0017\u0001\u00047pO\u0012K'/Z2u_JL\bC\u00016t\u001d\tY\u0017\u000f\u0005\u0002m_6\tQN\u0003\u0002oG\u00061AH]8pizR\u0011\u0001]\u0001\u0006g\u000e\fG.Y\u0005\u0003e>\fa\u0001\u0015:fI\u00164\u0017B\u0001;v\u0005\u0019\u0019FO]5oO*\u0011!o\\\u0001\u000bQ\u0006$wn\u001c9D_:4\u0007C\u0001=}\u001b\u0005I(B\u00012{\u0015\tY8+\u0001\u0004iC\u0012|w\u000e]\u0005\u0003{f\u0014QbQ8oM&<WO]1uS>t\u0017a\u0005:pY2LgnZ%oi\u0016\u0014h/\u00197TK\u000e\u001c\b\u0003BA\u0001\u0003\u0007i\u0011a\\\u0005\u0004\u0003\u000by'aA%oi\u0006YQ.\u0019=GC&dWO]3t\u0003M\u0019Gn\\:f\r&dW-\u00114uKJ<&/\u001b;f!\u0011\t\t!!\u0004\n\u0007\u0005=qNA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)9\t)\"a\u0006\u0002\u001a\u0005m\u0011QDA\u0010\u0003C\u0001\"!\u0017\u0001\t\u000b\t<\u0001\u0019\u00013\t\u000b!<\u0001\u0019A5\t\u000bY<\u0001\u0019A<\t\u000by<\u0001\u0019A@\t\r\u0005\u001dq\u00011\u0001\u0000\u0011\u001d\tIa\u0002a\u0001\u0003\u0017\t\u0001\u0002]1ti2{wm]\u000b\u0003\u0003O\u0001b!!\u000b\u00024\u0005]RBAA\u0016\u0015\u0011\ti#a\f\u0002\u000f5,H/\u00192mK*\u0019\u0011\u0011G8\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u00026\u0005-\"aC!se\u0006L()\u001e4gKJ\u00042!!\u000f+\u001d\tIv%\u0001\fGS2,')Y:fI^\u0013\u0018\u000e^3BQ\u0016\fG\rT8h!\tI\u0006fE\u0002)\u0003\u0003\u0002B!!\u0001\u0002D%\u0019\u0011QI8\u0003\r\u0005s\u0017PU3g)\t\tiDA\u0004M_\u001eLeNZ8\u0014\u000f)\n\t%!\u0014\u0002TA!\u0011\u0011AA(\u0013\r\t\tf\u001c\u0002\b!J|G-^2u!\u0011\t)&a\u0018\u000f\t\u0005]\u00131\f\b\u0004Y\u0006e\u0013\"\u00019\n\u0007\u0005us.A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\u0005\u00141\r\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0004\u0003;z\u0017!C:uCJ$H+[7f+\t\tI\u0007\u0005\u0003\u0002\u0002\u0005-\u0014bAA7_\n!Aj\u001c8h\u0003)\u0019H/\u0019:u)&lW\rI\u0001\bK:$G+[7f\u0003!)g\u000e\u001a+j[\u0016\u0004\u0013\u0001\u00029bi\",\u0012![\u0001\u0006a\u0006$\b\u000e\t\u000b\t\u0003{\n\t)a!\u0002\u0006B\u0019\u0011q\u0010\u0016\u000e\u0003!Bq!!\u001a2\u0001\u0004\tI\u0007C\u0004\u0002rE\u0002\r!!\u001b\t\r\u0005U\u0014\u00071\u0001j\u0003\u0011\u0019w\u000e]=\u0015\u0011\u0005u\u00141RAG\u0003\u001fC\u0011\"!\u001a3!\u0003\u0005\r!!\u001b\t\u0013\u0005E$\u0007%AA\u0002\u0005%\u0004\u0002CA;eA\u0005\t\u0019A5\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u0011Q\u0013\u0016\u0005\u0003S\n9j\u000b\u0002\u0002\u001aB!\u00111TAS\u001b\t\tiJ\u0003\u0003\u0002 \u0006\u0005\u0016!C;oG\",7m[3e\u0015\r\t\u0019k\\\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAT\u0003;\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uII\nabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u00020*\u001a\u0011.a&\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\t)\f\u0005\u0003\u00028\u0006\u0005WBAA]\u0015\u0011\tY,!0\u0002\t1\fgn\u001a\u0006\u0003\u0003\u007f\u000bAA[1wC&\u0019A/!/\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0016\u0003}\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0002L\u0006E\u0007\u0003BA\u0001\u0003\u001bL1!a4p\u0005\r\te.\u001f\u0005\t\u0003'D\u0014\u0011!a\u0001\u007f\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!!7\u0011\r\u0005m\u0017Q\\Af\u001b\t\ty#\u0003\u0003\u0002`\u0006=\"\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$B!a\u0003\u0002f\"I\u00111\u001b\u001e\u0002\u0002\u0003\u0007\u00111Z\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u00026\u0006-\b\u0002CAjw\u0005\u0005\t\u0019A@\u0002\u0011!\f7\u000f[\"pI\u0016$\u0012a`\u0001\ti>\u001cFO]5oOR\u0011\u0011QW\u0001\u0007KF,\u0018\r\\:\u0015\t\u0005-\u0011\u0011 \u0005\n\u0003't\u0014\u0011!a\u0001\u0003\u0017\fq\u0001T8h\u0013:4w\u000eE\u0002\u0002\u0000\u0001\u001bR\u0001\u0011B\u0001\u0005\u001b\u00012Ba\u0001\u0003\n\u0005%\u0014\u0011N5\u0002~5\u0011!Q\u0001\u0006\u0004\u0005\u000fy\u0017a\u0002:v]RLW.Z\u0005\u0005\u0005\u0017\u0011)AA\tBEN$(/Y2u\rVt7\r^5p]N\u0002BAa\u0004\u0003\u00165\u0011!\u0011\u0003\u0006\u0005\u0005'\ti,\u0001\u0002j_&!\u0011\u0011\rB\t)\t\ti0A\u0003baBd\u0017\u0010\u0006\u0005\u0002~\tu!q\u0004B\u0011\u0011\u001d\t)g\u0011a\u0001\u0003SBq!!\u001dD\u0001\u0004\tI\u0007\u0003\u0004\u0002v\r\u0003\r![\u0001\bk:\f\u0007\u000f\u001d7z)\u0011\u00119Ca\r\u0011\r\u0005\u0005!\u0011\u0006B\u0017\u0013\r\u0011Yc\u001c\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0013\u0005\u0005!qFA5\u0003SJ\u0017b\u0001B\u0019_\n1A+\u001e9mKNB\u0011B!\u000eE\u0003\u0003\u0005\r!! \u0002\u0007a$\u0003'\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003<A!\u0011q\u0017B\u001f\u0013\u0011\u0011y$!/\u0003\r=\u0013'.Z2u\u00031awn\u001a$jY\u0016\u0014VmZ3y+\t\u0011)\u0005\u0005\u0003\u0003H\t=SB\u0001B%\u0015\u0011\u0011YE!\u0014\u0002\u00115\fGo\u00195j]\u001eT!AT8\n\t\tE#\u0011\n\u0002\u0006%\u0016<W\r_\u0001\u000eY><g)\u001b7f%\u0016<W\r\u001f\u0011\u0002\u001bQLW.\u001a+p\u0019><g)\u001b7f)\u0015I'\u0011\fB.\u0011\u001d\t)\u0007\u0013a\u0001\u0003SBqA!\u0018I\u0001\u0004\tI'\u0001\u0005ti>\u0004H+[7f\u000359W\r^\"bY2,'OT1nKR\u0011!1\r\t\u0006\u0003\u0003\u0011I#[\u0001\u0012Y><g)\u001b7fgR{Gn\\4J]\u001a|G\u0003\u0002B5\u0005_\u0002b!!\u0016\u0003l\u0005u\u0014\u0002\u0002B7\u0003G\u00121aU3r\u0011\u001d\u0011\tH\u0013a\u0001\u0005g\nQAZ5mKN\u0004b!!\u0016\u0003l\tU\u0004\u0003\u0002B<\u0005{j!A!\u001f\u000b\u0007\tm$0\u0001\u0002gg&!!q\u0010B=\u0005\u0011\u0001\u0016\r\u001e5\u0002!M,\u0017\u000fV8QCJLE/\u001a:bi>\u0014XC\u0002BC\u0005g\u0013y\t\u0006\u0005\u0003\b\nm%1\u0016B\\!\u0019\t)F!#\u0003\f&!\u0011q\\A2!\u0011\u0011iIa$\r\u0001\u00119!\u0011S&C\u0002\tM%!A(\u0012\t\tU\u00151\u001a\t\u0005\u0003\u0003\u00119*C\u0002\u0003\u001a>\u0014qAT8uQ&tw\rC\u0004\u0003\u001e.\u0003\rAa(\u0002!\u0015DXmY;uS>t7i\u001c8uKb$\b\u0003\u0002BQ\u0005Ok!Aa)\u000b\u0007\t\u0015v.\u0001\u0006d_:\u001cWO\u001d:f]RLAA!+\u0003$\n\u0001R\t_3dkRLwN\\\"p]R,\u0007\u0010\u001e\u0005\b\u0005[[\u0005\u0019\u0001BX\u0003\u0019\u0019x.\u001e:dKB1\u0011Q\u000bB6\u0005c\u0003BA!$\u00034\u00129!QW&C\u0002\tM%!A%\t\u000f\te6\n1\u0001\u0003<\u00069\u0001.\u00198eY\u0016\u0014\b\u0003CA\u0001\u0005{\u0013\tLa\"\n\u0007\t}vNA\u0005Gk:\u001cG/[8oc\u0005I\u0001/Y:u\u0019><7\u000fI\u0001\u000bG\u0006dG.\u001a:OC6,WC\u0001B2\u0003-\u0019\u0017\r\u001c7fe:\u000bW.\u001a\u0011\u0002\u001dQD'/Z1ea>|GNT1nK\u0006yA\u000f\u001b:fC\u0012\u0004xn\u001c7OC6,\u0007%\u0001\u0007g_J\\'j\\5o!>|G.\u0006\u0002\u0003RB!!1\u001bBm\u001b\t\u0011)N\u0003\u0003\u0003&\n]'b\u0001(\u0002>&!!1\u001cBk\u000511uN]6K_&t\u0007k\\8m\u000351wN]6K_&t\u0007k\\8mAU\u0011!\u0011\u001d\t\u0005\u0005C\u0013\u0019/\u0003\u0003\u0003f\n\r&aH#yK\u000e,H/[8o\u0007>tG/\u001a=u\u000bb,7-\u001e;peN+'O^5dK\u0006\tR\r_3dkRLwN\\\"p]R,\u0007\u0010\u001e\u0011\u0002\u000f1|wMT1nK\u0006q1-\u001e:sK:$Hj\\4QCRD\u0017AE2veJ,g\u000e\u001e'pOB\u000bG\u000f[0%KF$BA!=\u0003xB!\u0011\u0011\u0001Bz\u0013\r\u0011)p\u001c\u0002\u0005+:LG\u000fC\u0005\u0002TR\t\t\u00111\u0001\u0003d\u0005y1-\u001e:sK:$Hj\\4QCRD\u0007%\u0001\tdkJ\u0014XM\u001c;M_\u001e<&/\u001b;feV\u0011!q \t\u00043\u000e\u0005\u0011bAB\u0002\u001b\nab)\u001b7f\u0005\u0006\u001cX\rZ,sSR,\u0017\t[3bI2{wm\u0016:ji\u0016\u0014\u0018\u0001F2veJ,g\u000e\u001e'pO^\u0013\u0018\u000e^3s?\u0012*\u0017\u000f\u0006\u0003\u0003r\u000e%\u0001\"CAj/\u0005\u0005\t\u0019\u0001B\u0000\u0003E\u0019WO\u001d:f]RdunZ,sSR,'\u000fI\u0001\u001aGV\u0014(/\u001a8u\u0019><wK]5uKJ\u001cF/\u0019:u)&lW-A\u000fdkJ\u0014XM\u001c;M_\u001e<&/\u001b;feN#\u0018M\u001d;US6,w\fJ3r)\u0011\u0011\tpa\u0005\t\u0013\u0005M'$!AA\u0002\u0005%\u0014AG2veJ,g\u000e\u001e'pO^\u0013\u0018\u000e^3s'R\f'\u000f\u001e+j[\u0016\u0004\u0013\u0001G2veJ,g\u000e\u001e'pO^\u0013\u0018\u000e^3s'R|\u0007\u000fV5nK\u0006a2-\u001e:sK:$Hj\\4Xe&$XM]*u_B$\u0016.\\3`I\u0015\fH\u0003\u0002By\u0007;A\u0011\"a5\u001e\u0003\u0003\u0005\r!!\u001b\u00023\r,(O]3oi2{wm\u0016:ji\u0016\u00148\u000b^8q)&lW\rI\u0001\u0006oJLG/\u001a\u000b\u0007\u0007K\u0019Yca\u000f\u0011\u0007e\u001b9#C\u0002\u0004*5\u0013QDR5mK\n\u000b7/\u001a3Xe&$X-\u00115fC\u0012dunZ*fO6,g\u000e\u001e\u0005\b\u0007[y\u0002\u0019AB\u0018\u0003)\u0011\u0017\u0010^3Ck\u001a4WM\u001d\t\u0005\u0007c\u00199$\u0004\u0002\u00044)!1QGA_\u0003\rq\u0017n\\\u0005\u0005\u0007s\u0019\u0019D\u0001\u0006CsR,')\u001e4gKJDqa!\u0010 \u0001\u0004\tI'\u0001\u0003uS6,\u0017\u0001\u0002:fC\u0012$Baa\f\u0004D!91Q\t\u0011A\u0002\r\u001d\u0013aB:fO6,g\u000e\u001e\t\u00043\u000e%\u0013bAB&\u001b\nIrK]5uK\u0006CW-\u00193M_\u001e\u0014VmY8sI\"\u000bg\u000e\u001a7f\u0003\u001d\u0011X-\u00193BY2$\"a!\u0015\u0011\r\rM3QKB\u0018\u001b\t\u00119.\u0003\u0003\u0002`\n]\u0017!B2mK\u0006tGC\u0002By\u00077\u001ay\u0006C\u0004\u0004^\t\u0002\r!!\u001b\u0002\u0015QD'/Z:i)&lW\rC\u0004\u0004b\t\u0002\r!a\u0003\u0002#]\f\u0017\u000e\u001e$pe\u000e{W\u000e\u001d7fi&|g.A\u0003dY>\u001cX\r\u0006\u0002\u0003r\u0006aq-\u001a;M_\u001e<&/\u001b;feR!!q`B6\u0011\u001d\u0019i\u0007\na\u0001\u0003S\n1bY;se\u0016tG\u000fV5nK\u0006\u0019\u0012N\\5uS\u0006d\u0017N_3PeJ+7m\u001c<fe\u0006Y!/Z:fi^\u0013\u0018\u000e^3s\u0001"
)
public class FileBasedWriteAheadLog extends WriteAheadLog implements Logging {
   private final String logDirectory;
   private final Configuration hadoopConf;
   private final int rollingIntervalSecs;
   private final int maxFailures;
   private final boolean closeFileAfterWrite;
   private final ArrayBuffer pastLogs;
   private final Option callerName;
   private final String threadpoolName;
   private final ForkJoinPool forkJoinPool;
   private final ExecutionContextExecutorService executionContext;
   private Option currentLogPath;
   private FileBasedWriteAheadLogWriter currentLogWriter;
   private long currentLogWriterStartTime;
   private long currentLogWriterStopTime;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static Iterator seqToParIterator(final ExecutionContext executionContext, final Seq source, final Function1 handler) {
      return FileBasedWriteAheadLog$.MODULE$.seqToParIterator(executionContext, source, handler);
   }

   public static Seq logFilesTologInfo(final Seq files) {
      return FileBasedWriteAheadLog$.MODULE$.logFilesTologInfo(files);
   }

   public static Option getCallerName() {
      return FileBasedWriteAheadLog$.MODULE$.getCallerName();
   }

   public static String timeToLogFile(final long startTime, final long stopTime) {
      return FileBasedWriteAheadLog$.MODULE$.timeToLogFile(startTime, stopTime);
   }

   public static Regex logFileRegex() {
      return FileBasedWriteAheadLog$.MODULE$.logFileRegex();
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

   private ArrayBuffer pastLogs() {
      return this.pastLogs;
   }

   private Option callerName() {
      return this.callerName;
   }

   private String threadpoolName() {
      return this.threadpoolName;
   }

   private ForkJoinPool forkJoinPool() {
      return this.forkJoinPool;
   }

   private ExecutionContextExecutorService executionContext() {
      return this.executionContext;
   }

   public String logName() {
      String var10000 = .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(this.getClass().getName()), "$");
      return var10000 + ((String)this.callerName().map((x$1) -> "_" + x$1).getOrElse(() -> "")).replaceAll("[ ]", "_");
   }

   private Option currentLogPath() {
      return this.currentLogPath;
   }

   private void currentLogPath_$eq(final Option x$1) {
      this.currentLogPath = x$1;
   }

   private FileBasedWriteAheadLogWriter currentLogWriter() {
      return this.currentLogWriter;
   }

   private void currentLogWriter_$eq(final FileBasedWriteAheadLogWriter x$1) {
      this.currentLogWriter = x$1;
   }

   private long currentLogWriterStartTime() {
      return this.currentLogWriterStartTime;
   }

   private void currentLogWriterStartTime_$eq(final long x$1) {
      this.currentLogWriterStartTime = x$1;
   }

   private long currentLogWriterStopTime() {
      return this.currentLogWriterStopTime;
   }

   private void currentLogWriterStopTime_$eq(final long x$1) {
      this.currentLogWriterStopTime = x$1;
   }

   public synchronized FileBasedWriteAheadLogSegment write(final ByteBuffer byteBuffer, final long time) {
      FileBasedWriteAheadLogSegment fileSegment = null;
      IntRef failures = IntRef.create(0);
      Exception lastException = null;
      boolean succeeded = false;

      while(!succeeded && failures.elem < this.maxFailures) {
         try {
            fileSegment = this.getLogWriter(time).write(byteBuffer);
            if (this.closeFileAfterWrite) {
               this.resetWriter();
            }

            succeeded = true;
         } catch (Exception var9) {
            lastException = var9;
            this.logWarning((Function0)(() -> "Failed to write to write ahead log"));
            this.resetWriter();
            ++failures.elem;
         }
      }

      if (fileSegment == null) {
         this.logError(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Failed to write to write ahead log after ", " failures"})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RETRY..MODULE$, BoxesRunTime.boxToInteger(failures.elem))})))));
         throw lastException;
      } else {
         return fileSegment;
      }
   }

   public ByteBuffer read(final WriteAheadLogRecordHandle segment) {
      FileBasedWriteAheadLogSegment fileSegment = (FileBasedWriteAheadLogSegment)segment;
      FileBasedWriteAheadLogRandomReader reader = null;
      ByteBuffer byteBuffer = null;

      try {
         reader = new FileBasedWriteAheadLogRandomReader(fileSegment.path(), this.hadoopConf);
         byteBuffer = reader.read(fileSegment);
      } finally {
         reader.close();
      }

      return byteBuffer;
   }

   public synchronized java.util.Iterator readAll() {
      ArrayBuffer logFilesToRead = (ArrayBuffer)((IterableOps)this.pastLogs().map((x$2) -> x$2.path())).$plus$plus(this.currentLogPath());
      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Reading from the logs:\\n"})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATHS..MODULE$, logFilesToRead.mkString("\n"))}))))));
      return !this.closeFileAfterWrite ? scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(logFilesToRead.iterator().flatMap((file) -> this.readFile$1(file))).asJava() : scala.jdk.CollectionConverters..MODULE$.IteratorHasAsJava(FileBasedWriteAheadLog$.MODULE$.seqToParIterator(this.executionContext(), logFilesToRead.toSeq(), (file) -> this.readFile$1(file))).asJava();
   }

   public void clean(final long threshTime, final boolean waitForCompletion) {
      synchronized(this){}

      ArrayBuffer var6;
      try {
         ArrayBuffer expiredLogs = (ArrayBuffer)this.pastLogs().filter((x$3) -> BoxesRunTime.boxToBoolean($anonfun$clean$1(threshTime, x$3)));
         this.pastLogs().$minus$minus$eq(expiredLogs);
         var6 = expiredLogs;
      } catch (Throwable var9) {
         throw var9;
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Attempting to clear ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_RECORDS_READ..MODULE$, BoxesRunTime.boxToInteger(var6.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"old log files in "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", " older than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.logDirectory)})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ": "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THRESHOLD..MODULE$, BoxesRunTime.boxToLong(threshTime))})))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.FILES..MODULE$, ((IterableOnceOps)var6.map((x$4) -> x$4.path())).mkString("\n"))}))))));
      var6.foreach((logInfo) -> {
         if (!this.executionContext().isShutdown()) {
            Object var10000;
            try {
               Future f = scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> this.deleteFile$1(logInfo, threshTime), this.executionContext());
               var10000 = waitForCompletion ? scala.concurrent.Await..MODULE$.ready(f, (new package.DurationInt(scala.concurrent.duration.package..MODULE$.DurationInt(1))).second()) : BoxedUnit.UNIT;
            } catch (RejectedExecutionException var7) {
               this.logWarning((Function0)(() -> "Execution context shutdown before deleting old WriteAheadLogs. This would not affect recovery correctness."), var7);
               var10000 = BoxedUnit.UNIT;
            }

            return var10000;
         } else {
            return BoxedUnit.UNIT;
         }
      });
   }

   public synchronized void close() {
      if (!this.executionContext().isShutdown()) {
         if (this.currentLogWriter() != null) {
            this.currentLogWriter().close();
         }

         this.executionContext().shutdown();
      }

      this.logInfo((Function0)(() -> "Stopped write ahead log manager"));
   }

   private synchronized FileBasedWriteAheadLogWriter getLogWriter(final long currentTime) {
      if (this.currentLogWriter() == null || currentTime > this.currentLogWriterStopTime()) {
         this.resetWriter();
         this.currentLogPath().foreach((x$5) -> (ArrayBuffer)this.pastLogs().$plus$eq(new LogInfo(this.currentLogWriterStartTime(), this.currentLogWriterStopTime(), x$5)));
         this.currentLogWriterStartTime_$eq(currentTime);
         this.currentLogWriterStopTime_$eq(currentTime + (long)this.rollingIntervalSecs * 1000L);
         Path newLogPath = new Path(this.logDirectory, FileBasedWriteAheadLog$.MODULE$.timeToLogFile(this.currentLogWriterStartTime(), this.currentLogWriterStopTime()));
         this.currentLogPath_$eq(new Some(newLogPath.toString()));
         this.currentLogWriter_$eq(new FileBasedWriteAheadLogWriter((String)this.currentLogPath().get(), this.hadoopConf));
      }

      return this.currentLogWriter();
   }

   private synchronized void initializeOrRecover() {
      Path logDirectoryPath = new Path(this.logDirectory);
      FileSystem fileSystem = HdfsUtils$.MODULE$.getFileSystemForPath(logDirectoryPath, this.hadoopConf);

      try {
         if (fileSystem.getFileStatus(logDirectoryPath).isDirectory()) {
            Seq logFileInfo = FileBasedWriteAheadLog$.MODULE$.logFilesTologInfo(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])fileSystem.listStatus(logDirectoryPath)), (x$6) -> x$6.getPath(), scala.reflect.ClassTag..MODULE$.apply(Path.class))).toImmutableArraySeq());
            this.pastLogs().clear();
            this.pastLogs().$plus$plus$eq(logFileInfo);
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Recovered ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.NUM_FILES..MODULE$, BoxesRunTime.boxToInteger(logFileInfo.size()))}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"write ahead log files from "})))).log(scala.collection.immutable.Nil..MODULE$)).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.logDirectory)}))))));
            this.logDebug((Function0)(() -> {
               IterableOnceOps var10000 = (IterableOnceOps)logFileInfo.map((x$7) -> x$7.path());
               return "Recovered files are:\n" + var10000.mkString("\n");
            }));
         }
      } catch (FileNotFoundException var4) {
      }

   }

   private synchronized void resetWriter() {
      if (this.currentLogWriter() != null) {
         this.currentLogWriter().close();
         this.currentLogWriter_$eq((FileBasedWriteAheadLogWriter)null);
      }
   }

   private final Iterator readFile$1(final String file) {
      this.logDebug((Function0)(() -> "Creating log reader with " + file));
      FileBasedWriteAheadLogReader reader = new FileBasedWriteAheadLogReader(file, this.hadoopConf);
      return org.apache.spark.util.CompletionIterator..MODULE$.apply(reader, (JFunction0.mcV.sp)() -> reader.close());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$clean$1(final long threshTime$1, final LogInfo x$3) {
      return x$3.endTime() < threshTime$1;
   }

   private final void deleteFile$1(final LogInfo walInfo, final long threshTime$1) {
      try {
         Path path = new Path(walInfo.path());
         FileSystem fs = HdfsUtils$.MODULE$.getFileSystemForPath(path, this.hadoopConf);
         fs.delete(path, true);
         this.logDebug((Function0)(() -> "Cleared log file " + walInfo));
      } catch (Exception var7) {
         this.logWarning((LogEntry)org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Error clearing write ahead log file "})))).log(scala.collection.immutable.Nil..MODULE$).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.WRITE_AHEAD_LOG_INFO..MODULE$, walInfo)}))))), var7);
      }

      this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Cleared log files in ", " older than "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.PATH..MODULE$, this.logDirectory)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.THRESH_TIME..MODULE$, BoxesRunTime.boxToLong(threshTime$1))}))))));
   }

   public FileBasedWriteAheadLog(final SparkConf conf, final String logDirectory, final Configuration hadoopConf, final int rollingIntervalSecs, final int maxFailures, final boolean closeFileAfterWrite) {
      this.logDirectory = logDirectory;
      this.hadoopConf = hadoopConf;
      this.rollingIntervalSecs = rollingIntervalSecs;
      this.maxFailures = maxFailures;
      this.closeFileAfterWrite = closeFileAfterWrite;
      Logging.$init$(this);
      this.pastLogs = new ArrayBuffer();
      this.callerName = FileBasedWriteAheadLog$.MODULE$.getCallerName();
      Option var10001 = this.callerName().map((c) -> " for " + c);
      this.threadpoolName = "WriteAheadLogManager" + var10001.getOrElse(() -> "");
      this.forkJoinPool = org.apache.spark.util.ThreadUtils..MODULE$.newForkJoinPool(this.threadpoolName(), 20);
      this.executionContext = scala.concurrent.ExecutionContext..MODULE$.fromExecutorService(this.forkJoinPool());
      this.currentLogPath = scala.None..MODULE$;
      this.currentLogWriter = null;
      this.currentLogWriterStartTime = -1L;
      this.currentLogWriterStopTime = -1L;
      this.initializeOrRecover();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class LogInfo implements Product, Serializable {
      private final long startTime;
      private final long endTime;
      private final String path;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public long startTime() {
         return this.startTime;
      }

      public long endTime() {
         return this.endTime;
      }

      public String path() {
         return this.path;
      }

      public LogInfo copy(final long startTime, final long endTime, final String path) {
         return new LogInfo(startTime, endTime, path);
      }

      public long copy$default$1() {
         return this.startTime();
      }

      public long copy$default$2() {
         return this.endTime();
      }

      public String copy$default$3() {
         return this.path();
      }

      public String productPrefix() {
         return "LogInfo";
      }

      public int productArity() {
         return 3;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return BoxesRunTime.boxToLong(this.startTime());
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.endTime());
            }
            case 2 -> {
               return this.path();
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
         return x$1 instanceof LogInfo;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "startTime";
            }
            case 1 -> {
               return "endTime";
            }
            case 2 -> {
               return "path";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.longHash(this.startTime()));
         var1 = Statics.mix(var1, Statics.longHash(this.endTime()));
         var1 = Statics.mix(var1, Statics.anyHash(this.path()));
         return Statics.finalizeHash(var1, 3);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var6;
         if (this != x$1) {
            label55: {
               if (x$1 instanceof LogInfo) {
                  LogInfo var4 = (LogInfo)x$1;
                  if (this.startTime() == var4.startTime() && this.endTime() == var4.endTime()) {
                     label48: {
                        String var10000 = this.path();
                        String var5 = var4.path();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label48;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label48;
                        }

                        if (var4.canEqual(this)) {
                           break label55;
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

      public LogInfo(final long startTime, final long endTime, final String path) {
         this.startTime = startTime;
         this.endTime = endTime;
         this.path = path;
         Product.$init$(this);
      }
   }

   public static class LogInfo$ extends AbstractFunction3 implements Serializable {
      public static final LogInfo$ MODULE$ = new LogInfo$();

      public final String toString() {
         return "LogInfo";
      }

      public LogInfo apply(final long startTime, final long endTime, final String path) {
         return new LogInfo(startTime, endTime, path);
      }

      public Option unapply(final LogInfo x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple3(BoxesRunTime.boxToLong(x$0.startTime()), BoxesRunTime.boxToLong(x$0.endTime()), x$0.path())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(LogInfo$.class);
      }
   }
}
