package org.apache.spark.resource;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkException;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MDC;
import org.apache.spark.internal.config.package$;
import org.slf4j.Logger;
import scala.Function0;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple6;
import scala.Tuple7;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.MapOps;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction6;
import scala.runtime.AbstractFunction7;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction0;

@Evolving
@ScalaSignature(
   bytes = "\u0006\u0005\u0015}aaBA'\u0003\u001f\u0002\u0011\u0011\r\u0005\u000b\u0003'\u0003!Q1A\u0005\u0002\u0005U\u0005BCA[\u0001\t\u0005\t\u0015!\u0003\u0002\u0018\"Q\u0011q\u0017\u0001\u0003\u0006\u0004%\t!!/\t\u0015\u0005\r\u0007A!A!\u0002\u0013\tY\fC\u0004\u0002F\u0002!\t!a2\t\u0013\u0005=\u0007\u00011A\u0005\n\u0005E\u0007\"CAm\u0001\u0001\u0007I\u0011BAn\u0011!\t9\u000f\u0001Q!\n\u0005M\u0007\"CAu\u0001\u0001\u0007I\u0011BAv\u0011%\t)\u0010\u0001a\u0001\n\u0013\t9\u0010\u0003\u0005\u0002|\u0002\u0001\u000b\u0015BAw\u0011%\ti\u0010\u0001a\u0001\n\u0013\ty\u0010C\u0005\u0003\u0004\u0001\u0001\r\u0011\"\u0003\u0003\u0006!A!\u0011\u0002\u0001!B\u0013\u0011\t\u0001C\u0005\u0003\f\u0001\u0001\r\u0011\"\u0003\u0003\u000e!I!\u0011\u0003\u0001A\u0002\u0013%!1\u0003\u0005\t\u0005/\u0001\u0001\u0015)\u0003\u0003\u0010!I!\u0011\u0004\u0001A\u0002\u0013%!1\u0004\u0005\n\u0005G\u0001\u0001\u0019!C\u0005\u0005KA\u0001B!\u000b\u0001A\u0003&!Q\u0004\u0005\b\u0005W\u0001A\u0011\u0003B\u0017\u0011\u001d\u0011y\u0003\u0001C\u0001\u0003#DqA!\r\u0001\t\u0003\u0011\u0019\u0004C\u0004\u0003D\u0001!\tA!\u0012\t\u0013\t%\u0003\u0001\"\u0001\u0002T\t5\u0001\"\u0003B&\u0001\u0011\u0005\u00111\u000bB\u0007\u0011%\u0011i\u0005\u0001C\u0001\u0003'\u0012y\u0005C\u0005\u0003Z\u0001!\t!a\u0015\u0003P!I!1\f\u0001\u0005\u0002\u0005M#Q\f\u0005\n\u0005?\u0002A\u0011CA*\u0005CB\u0011Ba\u0019\u0001\t\u0003\t\u0019F!\u001a\t\u0013\t%\u0004\u0001\"\u0001\u0002T\t-\u0004\"\u0003B>\u0001\u0011\u0005\u00111\u000bB?\u0011%\u0011\t\t\u0001C\u0001\u0003'\u0012Y\u0002C\u0005\u0003\u0004\u0002!\t!a\u0015\u0003\u0006\"9!\u0011\u0012\u0001\u0005\n\t-\u0005b\u0002BH\u0001\u0011%!\u0011\u0013\u0005\n\u0005+\u0003A\u0011AA*\u0005/C\u0011Ba'\u0001\t\u0003\t\u0019F!\f\t\u000f\tu\u0005\u0001\"\u0011\u0003 \"I!1\u0016\u0001\u0005\u0002\u0005M#Q\u0016\u0005\b\u0005g\u0003A\u0011\tB[\u0011\u001d\u00119\f\u0001C!\u0005s;\u0001B!6\u0002P!\u0005!q\u001b\u0004\t\u0003\u001b\ny\u0005#\u0001\u0003Z\"9\u0011QY\u0017\u0005\u0002\t\u0015\b\"\u0003Bt[\t\u0007I\u0011\u0001Bu\u0011!\u0011)0\fQ\u0001\n\t-\b\"\u0003B|[\t\u0007I\u0011\u0001Bu\u0011!\u0011I0\fQ\u0001\n\t-\b\"\u0003B~[\t\u0007I\u0011\u0001Bu\u0011!\u0011i0\fQ\u0001\n\t-\b\"\u0003B\u0000[\t\u0007I\u0011\u0001Bu\u0011!\u0019\t!\fQ\u0001\n\t-\b\"CB\u0002[\t\u0007I\u0011\u0001Bu\u0011!\u0019)!\fQ\u0001\n\t-\b\"CB\u0004[\t\u0007I\u0011\u0001Bu\u0011!\u0019I!\fQ\u0001\n\t-\bbBB\u0006[\u0011\u00051Q\u0002\u0005\n\u0007+i#\u0019!C\u0001\u0003#D\u0001ba\u0006.A\u0003%\u00111\u001b\u0005\n\u00073i#\u0019!C\u0001\u0003#D\u0001ba\u0007.A\u0003%\u00111\u001b\u0005\u000b\u0007;i\u0003R1A\u0005\n\r}\u0001\"CB\u0019[\t\u0007I\u0011BB\u001a\u0011!\u0019Y$\fQ\u0001\n\rU\u0002\"CB\u001f[\u0001\u0007I\u0011BB \u0011%\u0019\u0019%\fa\u0001\n\u0013\u0019)\u0005\u0003\u0005\u0004J5\u0002\u000b\u0015BB!\u0011%\u0019\t'\fa\u0001\n\u0013\u0019\u0019\u0007C\u0005\u0005\u00065\u0002\r\u0011\"\u0003\u0005\b!AA1B\u0017!B\u0013\u0019)\u0007C\u0005\u0005\u000e5\"\t!a\u0015\u0002R\"IAqB\u0017\u0005\u0002\u0005MC\u0011\u0003\u0005\n\t/iC\u0011AA*\t3Aq\u0001\"\b.\t\u0013!y\u0002C\u0004\u0005$5\"I\u0001\"\n\t\u0013\u0011%R\u0006\"\u0001\u0002T\u0011-\u0002\"\u0003C\u0018[\u0011\u0005\u00111\u000bB\u0017\u0011%!\t$\fC\u0001\u0003'\"\u0019\u0004C\u0005\u0005:5\"\t!a\u0015\u0005<\u0019AA1I\u0017A\u0003'\")\u0005\u0003\u0006\u0004vI\u0013)\u001a!C\u0001\u0005\u001bA!ba\u001eS\u0005#\u0005\u000b\u0011\u0002B\b\u0011)\u0019IH\u0015BK\u0002\u0013\u000511\u0010\u0005\u000b\u0007{\u0012&\u0011#Q\u0001\n\tM\u0003BCB@%\nU\r\u0011\"\u0001\u0004|!Q1\u0011\u0011*\u0003\u0012\u0003\u0006IAa\u0015\t\u0015\r\r%K!f\u0001\n\u0003\u0019Y\b\u0003\u0006\u0004\u0006J\u0013\t\u0012)A\u0005\u0005'B!ba\"S\u0005+\u0007I\u0011AB>\u0011)\u0019II\u0015B\tB\u0003%!1\u000b\u0005\u000b\t\u000f\u0012&Q3A\u0005\u0002\rm\u0004B\u0003C%%\nE\t\u0015!\u0003\u0003T!Q11\u0012*\u0003\u0016\u0004%\t!!&\t\u0015\r5%K!E!\u0002\u0013\t9\nC\u0004\u0002FJ#\t\u0001b\u0013\t\u0013\ru%+!A\u0005\u0002\u0011u\u0003\"CBW%F\u0005I\u0011ABX\u0011%\u0019\u0019MUI\u0001\n\u0003\u0019)\rC\u0005\u0004JJ\u000b\n\u0011\"\u0001\u0004F\"I11\u001a*\u0012\u0002\u0013\u00051Q\u0019\u0005\n\u0007#\u0014\u0016\u0013!C\u0001\u0007\u000bD\u0011ba5S#\u0003%\ta!2\t\u0013\u00115$+%A\u0005\u0002\rU\u0007\"CBm%\u0006\u0005I\u0011\tBu\u0011%\u0019YNUA\u0001\n\u0003\t\t\u000eC\u0005\u0004^J\u000b\t\u0011\"\u0001\u0005p!I11\u001d*\u0002\u0002\u0013\u00053Q\u001d\u0005\n\u0007g\u0014\u0016\u0011!C\u0001\tgB\u0011b!?S\u0003\u0003%\t\u0005b\u001e\t\u0013\tM&+!A\u0005B\tU\u0006\"\u0003B\\%\u0006\u0005I\u0011IB\u0000\u0011%\u0011iJUA\u0001\n\u0003\"YhB\u0006\u0005\u00005\n\t\u0011#\u0001\u0002T\u0011\u0005ea\u0003C\"[\u0005\u0005\t\u0012AA*\t\u0007Cq!!2u\t\u0003!\t\nC\u0005\u00038R\f\t\u0011\"\u0012\u0004\u0000\"IA1\u0013;\u0002\u0002\u0013\u0005EQ\u0013\u0005\n\tK#\u0018\u0011!CA\tOC\u0011\u0002\".u\u0003\u0003%I\u0001b.\u0007\u0011\r-T\u0006QA*\u0007[B!b!\u001e{\u0005+\u0007I\u0011\u0001B\u0007\u0011)\u00199H\u001fB\tB\u0003%!q\u0002\u0005\u000b\u0007sR(Q3A\u0005\u0002\rm\u0004BCB?u\nE\t\u0015!\u0003\u0003T!Q1q\u0010>\u0003\u0016\u0004%\taa\u001f\t\u0015\r\u0005%P!E!\u0002\u0013\u0011\u0019\u0006\u0003\u0006\u0004\u0004j\u0014)\u001a!C\u0001\u0005\u001fB!b!\"{\u0005#\u0005\u000b\u0011\u0002B)\u0011)\u00199I\u001fBK\u0002\u0013\u0005!q\n\u0005\u000b\u0007\u0013S(\u0011#Q\u0001\n\tE\u0003BCBFu\nU\r\u0011\"\u0001\u0002\u0016\"Q1Q\u0012>\u0003\u0012\u0003\u0006I!a&\t\u000f\u0005\u0015'\u0010\"\u0001\u0004\u0010\"I1Q\u0014>\u0002\u0002\u0013\u00051q\u0014\u0005\n\u0007[S\u0018\u0013!C\u0001\u0007_C\u0011ba1{#\u0003%\ta!2\t\u0013\r%'0%A\u0005\u0002\r\u0015\u0007\"CBfuF\u0005I\u0011ABg\u0011%\u0019\tN_I\u0001\n\u0003\u0019i\rC\u0005\u0004Tj\f\n\u0011\"\u0001\u0004V\"I1\u0011\u001c>\u0002\u0002\u0013\u0005#\u0011\u001e\u0005\n\u00077T\u0018\u0011!C\u0001\u0003#D\u0011b!8{\u0003\u0003%\taa8\t\u0013\r\r(0!A\u0005B\r\u0015\b\"CBzu\u0006\u0005I\u0011AB{\u0011%\u0019IP_A\u0001\n\u0003\u001aY\u0010C\u0005\u00034j\f\t\u0011\"\u0011\u00036\"I!q\u0017>\u0002\u0002\u0013\u00053q \u0005\n\u0005;S\u0018\u0011!C!\t\u000391\u0002\"/.\u0003\u0003E\t!a\u0015\u0005<\u001aY11N\u0017\u0002\u0002#\u0005\u00111\u000bC_\u0011!\t)-a\r\u0005\u0002\u0011\u0015\u0007B\u0003B\\\u0003g\t\t\u0011\"\u0012\u0004\u0000\"QA1SA\u001a\u0003\u0003%\t\tb2\t\u0015\u0011\u0015\u00161GA\u0001\n\u0003#)\u000e\u0003\u0006\u00056\u0006M\u0012\u0011!C\u0005\toC\u0011\u0002\"9.\t\u0003\t\u0019\u0006b9\t\u0013\u0011eX\u0006\"\u0001\u0002T\u0011m\bbCC\f[\t\u0007I\u0011AA*\u0005SD\u0001\"\"\u0007.A\u0003%!1\u001e\u0005\f\u000b7i#\u0019!C\u0001\u0003'\u0012I\u000f\u0003\u0005\u0006\u001e5\u0002\u000b\u0011\u0002Bv\u0011%!),LA\u0001\n\u0013!9LA\bSKN|WO]2f!J|g-\u001b7f\u0015\u0011\t\t&a\u0015\u0002\u0011I,7o\\;sG\u0016TA!!\u0016\u0002X\u0005)1\u000f]1sW*!\u0011\u0011LA.\u0003\u0019\t\u0007/Y2iK*\u0011\u0011QL\u0001\u0004_J<7\u0001A\n\b\u0001\u0005\r\u0014qNAD!\u0011\t)'a\u001b\u000e\u0005\u0005\u001d$BAA5\u0003\u0015\u00198-\u00197b\u0013\u0011\ti'a\u001a\u0003\r\u0005s\u0017PU3g!\u0011\t\t(!!\u000f\t\u0005M\u0014Q\u0010\b\u0005\u0003k\nY(\u0004\u0002\u0002x)!\u0011\u0011PA0\u0003\u0019a$o\\8u}%\u0011\u0011\u0011N\u0005\u0005\u0003\u007f\n9'A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\r\u0015Q\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0005\u0003\u007f\n9\u0007\u0005\u0003\u0002\n\u0006=UBAAF\u0015\u0011\ti)a\u0015\u0002\u0011%tG/\u001a:oC2LA!!%\u0002\f\n9Aj\\4hS:<\u0017!E3yK\u000e,Ho\u001c:SKN|WO]2fgV\u0011\u0011q\u0013\t\t\u00033\u000b\t+a*\u0002.:!\u00111TAO!\u0011\t)(a\u001a\n\t\u0005}\u0015qM\u0001\u0007!J,G-\u001a4\n\t\u0005\r\u0016Q\u0015\u0002\u0004\u001b\u0006\u0004(\u0002BAP\u0003O\u0002B!!'\u0002*&!\u00111VAS\u0005\u0019\u0019FO]5oOB!\u0011qVAY\u001b\t\ty%\u0003\u0003\u00024\u0006=#aF#yK\u000e,Ho\u001c:SKN|WO]2f%\u0016\fX/Z:u\u0003I)\u00070Z2vi>\u0014(+Z:pkJ\u001cWm\u001d\u0011\u0002\u001bQ\f7o\u001b*fg>,(oY3t+\t\tY\f\u0005\u0005\u0002\u001a\u0006\u0005\u0016qUA_!\u0011\ty+a0\n\t\u0005\u0005\u0017q\n\u0002\u0014)\u0006\u001c8NU3t_V\u00148-\u001a*fcV,7\u000f^\u0001\u000fi\u0006\u001c8NU3t_V\u00148-Z:!\u0003\u0019a\u0014N\\5u}Q1\u0011\u0011ZAf\u0003\u001b\u00042!a,\u0001\u0011\u001d\t\u0019*\u0002a\u0001\u0003/Cq!a.\u0006\u0001\u0004\tY,A\u0002`S\u0012,\"!a5\u0011\t\u0005\u0015\u0014Q[\u0005\u0005\u0003/\f9GA\u0002J]R\fqaX5e?\u0012*\u0017\u000f\u0006\u0003\u0002^\u0006\r\b\u0003BA3\u0003?LA!!9\u0002h\t!QK\\5u\u0011%\t)oBA\u0001\u0002\u0004\t\u0019.A\u0002yIE\nAaX5eA\u0005ir,\u001a=fGV$xN\u001d*fg>,(oY3TY>$8\u000fU3s\u0003\u0012$'/\u0006\u0002\u0002nB1\u0011QMAx\u0003gLA!!=\u0002h\t1q\n\u001d;j_:\u0004\u0002\"!'\u0002\"\u0006\u001d\u00161[\u0001\"?\u0016DXmY;u_J\u0014Vm]8ve\u000e,7\u000b\\8ugB+'/\u00113ee~#S-\u001d\u000b\u0005\u0003;\fI\u0010C\u0005\u0002f*\t\t\u00111\u0001\u0002n\u0006qr,\u001a=fGV$xN\u001d*fg>,(oY3TY>$8\u000fU3s\u0003\u0012$'\u000fI\u0001\u0012?2LW.\u001b;j]\u001e\u0014Vm]8ve\u000e,WC\u0001B\u0001!\u0019\t)'a<\u0002(\u0006)r\f\\5nSRLgn\u001a*fg>,(oY3`I\u0015\fH\u0003BAo\u0005\u000fA\u0011\"!:\u000e\u0003\u0003\u0005\rA!\u0001\u0002%}c\u0017.\\5uS:<'+Z:pkJ\u001cW\rI\u0001\u0015?6\f\u0007\u0010V1tWN\u0004VM]#yK\u000e,Ho\u001c:\u0016\u0005\t=\u0001CBA3\u0003_\f\u0019.\u0001\r`[\u0006DH+Y:lgB+'/\u0012=fGV$xN]0%KF$B!!8\u0003\u0016!I\u0011Q\u001d\t\u0002\u0002\u0003\u0007!qB\u0001\u0016?6\f\u0007\u0010V1tWN\u0004VM]#yK\u000e,Ho\u001c:!\u0003Ay6m\u001c:fg2KW.\u001b;L]><h.\u0006\u0002\u0003\u001eA!\u0011Q\rB\u0010\u0013\u0011\u0011\t#a\u001a\u0003\u000f\t{w\u000e\\3b]\u0006!rlY8sKNd\u0015.\\5u\u0017:|wO\\0%KF$B!!8\u0003(!I\u0011Q]\n\u0002\u0002\u0003\u0007!QD\u0001\u0012?\u000e|'/Z:MS6LGo\u00138po:\u0004\u0013\u0001\u0003<bY&$\u0017\r^3\u0015\u0005\u0005u\u0017AA5e\u0003E!\u0018m]6SKN|WO]2fg*k\u0015\r]\u000b\u0003\u0005k\u0001\u0002Ba\u000e\u0003B\u0005\u001d\u0016QX\u0007\u0003\u0005sQAAa\u000f\u0003>\u0005!Q\u000f^5m\u0015\t\u0011y$\u0001\u0003kCZ\f\u0017\u0002BAR\u0005s\tQ#\u001a=fGV$xN\u001d*fg>,(oY3t\u00156\u000b\u0007/\u0006\u0002\u0003HAA!q\u0007B!\u0003O\u000bi+\u0001\thKR,\u00050Z2vi>\u00148i\u001c:fg\u0006Yq-\u001a;UCN\\7\t];t\u0003A9W\r\u001e)z'B\f'o['f[>\u0014\u00180\u0006\u0002\u0003RA1\u0011QMAx\u0005'\u0002B!!\u001a\u0003V%!!qKA4\u0005\u0011auN\\4\u0002#\u001d,G/\u0012=fGV$xN]'f[>\u0014\u00180\u0001\fhKR\u001cUo\u001d;p[R\u000b7o\u001b*fg>,(oY3t)\t\tY,\u0001\u000ehKR\u001cUo\u001d;p[\u0016CXmY;u_J\u0014Vm]8ve\u000e,7\u000f\u0006\u0002\u0002\u0018\u0006qr-\u001a;TG\",G-\u001e7feR\u000b7o\u001b*fg>,(oY3B[>,h\u000e\u001e\u000b\u0005\u0003'\u00149\u0007C\u0004\u0002R}\u0001\r!a*\u0002+\u001d,GOT;n'2|Go\u001d)fe\u0006#GM]3tgR1\u00111\u001bB7\u0005_Bq!!\u0015!\u0001\u0004\t9\u000bC\u0004\u0003r\u0001\u0002\rAa\u001d\u0002\u0013M\u0004\u0018M]6D_:4\u0007\u0003\u0002B;\u0005oj!!a\u0015\n\t\te\u00141\u000b\u0002\n'B\f'o[\"p]\u001a\f1#\\1y)\u0006\u001c8n\u001d)fe\u0016CXmY;u_J$B!a5\u0003\u0000!9!\u0011O\u0011A\u0002\tM\u0014!E5t\u0007>\u0014Xm\u001d'j[&$8J\\8x]\u0006\u0001B.[7ji&twMU3t_V\u00148-\u001a\u000b\u0005\u0003O\u00139\tC\u0004\u0003r\r\u0002\rAa\u001d\u00021MDw.\u001e7e\u0007\",7m[#yK\u000e,Ho\u001c:D_J,7\u000f\u0006\u0003\u0003\u001e\t5\u0005b\u0002B9I\u0001\u0007!1O\u0001\"G\u0006d7-\u001e7bi\u0016$\u0016m]6t\u0003:$G*[7ji&twMU3t_V\u00148-\u001a\u000b\u0005\u0003;\u0014\u0019\nC\u0004\u0003r\u0015\u0002\rAa\u001d\u0002)M,GOU3t_V\u00148-\u001a)s_\u001aLG.Z%e)\u0011\tiN!'\t\u000f\t=b\u00051\u0001\u0002T\u0006\u00192/\u001a;U_\u0012+g-Y;miB\u0013xNZ5mK\u00061Q-];bYN$BA!\b\u0003\"\"9!1\u0015\u0015A\u0002\t\u0015\u0016aA8cUB!\u0011Q\rBT\u0013\u0011\u0011I+a\u001a\u0003\u0007\u0005s\u00170\u0001\bsKN|WO]2fg\u0016\u000bX/\u00197\u0015\t\tu!q\u0016\u0005\b\u0005cK\u0003\u0019AAe\u0003\t\u0011\b/\u0001\u0005iCND7i\u001c3f)\t\t\u0019.\u0001\u0005u_N#(/\u001b8h)\t\t9\u000bK\u0002\u0001\u0005{\u0003BAa0\u0003F6\u0011!\u0011\u0019\u0006\u0005\u0005\u0007\f\u0019&\u0001\u0006b]:|G/\u0019;j_:LAAa2\u0003B\nAQI^8mm&tw\rK\u0003\u0001\u0005\u0017\u0014\t\u000e\u0005\u0003\u0003@\n5\u0017\u0002\u0002Bh\u0005\u0003\u0014QaU5oG\u0016\f#Aa5\u0002\u000bMr\u0013G\f\u0019\u0002\u001fI+7o\\;sG\u0016\u0004&o\u001c4jY\u0016\u00042!a,.'\u001di\u00131MAD\u00057\u0004BA!8\u0003d6\u0011!q\u001c\u0006\u0005\u0005C\u0014i$\u0001\u0002j_&!\u00111\u0011Bp)\t\u00119.\u0001\u0003D!V\u001bVC\u0001Bv!\u0011\u0011iOa=\u000e\u0005\t=(\u0002\u0002By\u0005{\tA\u0001\\1oO&!\u00111\u0016Bx\u0003\u0015\u0019\u0005+V*!\u0003\u0015\u0019uJU#T\u0003\u0019\u0019uJU#TA\u00051Q*R'P%f\u000bq!T#N\u001fJK\u0006%A\u0006P\r\u001aCU)\u0011)`\u001b\u0016k\u0015\u0001D(G\r\"+\u0015\tU0N\u000b6\u0003\u0013\u0001D(W\u000bJCU)\u0011#`\u001b\u0016k\u0015!D(W\u000bJCU)\u0011#`\u001b\u0016k\u0005%A\u0006Q3N\u0003\u0016IU&`\u001b\u0016k\u0015\u0001\u0004)Z'B\u000b%kS0N\u000b6\u0003\u0013!H1mYN+\b\u000f]8si\u0016$W\t_3dkR|'OU3t_V\u00148-Z:\u0016\u0005\r=\u0001CBA3\u0007#\t9+\u0003\u0003\u0004\u0014\u0005\u001d$!B!se\u0006L\u0018aG+O\u0017:{uKT0S\u000bN{UKU\"F?B\u0013vJR%M\u000b~KE)\u0001\u000fV\u001d.suj\u0016(`%\u0016\u001bv*\u0016*D\u000b~\u0003&k\u0014$J\u0019\u0016{\u0016\n\u0012\u0011\u00027\u0011+e)Q+M)~\u0013ViU(V%\u000e+u\f\u0015*P\r&cUiX%E\u0003q!UIR!V\u0019R{&+R*P+J\u001bUi\u0018)S\u001f\u001aKE*R0J\t\u0002\nQB\\3yiB\u0013xNZ5mK&#WCAB\u0011!\u0011\u0019\u0019c!\f\u000e\u0005\r\u0015\"\u0002BB\u0014\u0007S\ta!\u0019;p[&\u001c'\u0002BB\u0016\u0005s\t!bY8oGV\u0014(/\u001a8u\u0013\u0011\u0019yc!\n\u0003\u001b\u0005#x.\\5d\u0013:$XmZ3s\u0003Q!UIR!V\u0019R{\u0006KU(G\u00132+u\fT(D\u0017V\u00111Q\u0007\t\u0005\u0005[\u001c9$\u0003\u0003\u0004:\t=(AB(cU\u0016\u001cG/A\u000bE\u000b\u001a\u000bU\u000b\u0014+`!J{e)\u0013'F?2{5i\u0013\u0011\u0002\u001d\u0011,g-Y;miB\u0013xNZ5mKV\u00111\u0011\t\t\u0007\u0003K\ny/!3\u0002%\u0011,g-Y;miB\u0013xNZ5mK~#S-\u001d\u000b\u0005\u0003;\u001c9\u0005C\u0005\u0002f\u0012\u000b\t\u00111\u0001\u0004B\u0005yA-\u001a4bk2$\bK]8gS2,\u0007\u0005K\u0004F\u0007\u001b\u001aifa\u0018\u0011\t\r=3\u0011L\u0007\u0003\u0007#RAaa\u000b\u0004T)!!1YB+\u0015\t\u00199&A\u0003kCZ\f\u00070\u0003\u0003\u0004\\\rE#!C$vCJ$W\r\u001a\"z\u0003\u00151\u0018\r\\;fC\t\u0019\t$A\u0010eK\u001a\fW\u000f\u001c;Qe>4\u0017\u000e\\3Fq\u0016\u001cW\u000f^8s%\u0016\u001cx.\u001e:dKN,\"a!\u001a\u0011\r\u0005\u0015\u0014q^B4!\r\u0019IG_\u0007\u0002[\tyB)\u001a4bk2$\bK]8gS2,W\t_3dkR|'OU3t_V\u00148-Z:\u0014\u000fi\f\u0019ga\u001c\u0002pA!\u0011QMB9\u0013\u0011\u0019\u0019(a\u001a\u0003\u000fA\u0013x\u000eZ;di\u0006)1m\u001c:fg\u000611m\u001c:fg\u0002\n\u0011#\u001a=fGV$xN]'f[>\u0014\u00180T5C+\t\u0011\u0019&\u0001\nfq\u0016\u001cW\u000f^8s\u001b\u0016lwN]=NS\n\u0003\u0013\u0001E7f[>\u0014\u0018p\u00144g\u0011\u0016\f\u0007/T5C\u0003EiW-\\8ss>3g\rS3ba6K'\tI\u0001\u0011af\u001c\b/\u0019:l\u001b\u0016lwN]=NS\n\u000b\u0011\u0003]=ta\u0006\u00148.T3n_JLX*\u001b\"!\u0003EiW-\\8ss>3XM\u001d5fC\u0012l\u0015NQ\u0001\u0013[\u0016lwN]=Pm\u0016\u0014\b.Z1e\u001b&\u0014\u0005%A\bdkN$x.\u001c*fg>,(oY3t\u0003A\u0019Wo\u001d;p[J+7o\\;sG\u0016\u001c\b\u0005\u0006\b\u0004h\rE51SBK\u0007/\u001bIja'\t\u0011\rU\u0014q\u0002a\u0001\u0005\u001fA\u0001b!\u001f\u0002\u0010\u0001\u0007!1\u000b\u0005\t\u0007\u007f\ny\u00011\u0001\u0003T!A11QA\b\u0001\u0004\u0011\t\u0006\u0003\u0005\u0004\b\u0006=\u0001\u0019\u0001B)\u0011!\u0019Y)a\u0004A\u0002\u0005]\u0015\u0001B2paf$bba\u001a\u0004\"\u000e\r6QUBT\u0007S\u001bY\u000b\u0003\u0006\u0004v\u0005E\u0001\u0013!a\u0001\u0005\u001fA!b!\u001f\u0002\u0012A\u0005\t\u0019\u0001B*\u0011)\u0019y(!\u0005\u0011\u0002\u0003\u0007!1\u000b\u0005\u000b\u0007\u0007\u000b\t\u0002%AA\u0002\tE\u0003BCBD\u0003#\u0001\n\u00111\u0001\u0003R!Q11RA\t!\u0003\u0005\r!a&\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u00111\u0011\u0017\u0016\u0005\u0005\u001f\u0019\u0019l\u000b\u0002\u00046B!1qWB`\u001b\t\u0019IL\u0003\u0003\u0004<\u000eu\u0016!C;oG\",7m[3e\u0015\u0011\u0011\u0019-a\u001a\n\t\r\u00057\u0011\u0018\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AD2paf$C-\u001a4bk2$HEM\u000b\u0003\u0007\u000fTCAa\u0015\u00044\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001a\u0014AD2paf$C-\u001a4bk2$H\u0005N\u000b\u0003\u0007\u001fTCA!\u0015\u00044\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012*\u0014AD2paf$C-\u001a4bk2$HEN\u000b\u0003\u0007/TC!a&\u00044\u0006i\u0001O]8ek\u000e$\bK]3gSb\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003&\u000e\u0005\bBCAs\u0003G\t\t\u00111\u0001\u0002T\u0006y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0004hB11\u0011^Bx\u0005Kk!aa;\u000b\t\r5\u0018qM\u0001\u000bG>dG.Z2uS>t\u0017\u0002BBy\u0007W\u0014\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!!QDB|\u0011)\t)/a\n\u0002\u0002\u0003\u0007!QU\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0003l\u000eu\bBCAs\u0003S\t\t\u00111\u0001\u0002TR\u0011!1\u001e\u000b\u0005\u0005;!\u0019\u0001\u0003\u0006\u0002f\u0006=\u0012\u0011!a\u0001\u0005K\u000b1\u0005Z3gCVdG\u000f\u0015:pM&dW-\u0012=fGV$xN\u001d*fg>,(oY3t?\u0012*\u0017\u000f\u0006\u0003\u0002^\u0012%\u0001\"CAs\u000f\u0006\u0005\t\u0019AB3\u0003\u0001\"WMZ1vYR\u0004&o\u001c4jY\u0016,\u00050Z2vi>\u0014(+Z:pkJ\u001cWm\u001d\u0011\u0002!\u001d,GOT3yiB\u0013xNZ5mK&#\u0017!G4fi>\u00138I]3bi\u0016$UMZ1vYR\u0004&o\u001c4jY\u0016$B!!3\u0005\u0014!9AQ\u0003&A\u0002\tM\u0014\u0001B2p]\u001a\f!eZ3u\t\u00164\u0017-\u001e7u!J|g-\u001b7f\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u001cH\u0003BB4\t7Aq\u0001\"\u0006L\u0001\u0004\u0011\u0019(A\fhKR$UMZ1vYR$\u0016m]6SKN|WO]2fgR!\u00111\u0018C\u0011\u0011\u001d!)\u0002\u0014a\u0001\u0005g\n1dZ3u\t\u00164\u0017-\u001e7u\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u001cH\u0003BAL\tOAq\u0001\"\u0006N\u0001\u0004\u0011\u0019(\u0001\u000bsK&s\u0017\u000e\u001e#fM\u0006,H\u000e\u001e)s_\u001aLG.\u001a\u000b\u0005\u0003\u0013$i\u0003C\u0004\u0005\u00169\u0003\rAa\u001d\u0002'\rdW-\u0019:EK\u001a\fW\u000f\u001c;Qe>4\u0017\u000e\\3\u0002=\u001d,G\u000fV1tW\u000e\u0003Xo](s\t\u00164\u0017-\u001e7u\r>\u0014\bK]8gS2,GCBAj\tk!9\u0004C\u0004\u00032B\u0003\r!!3\t\u000f\u0011U\u0001\u000b1\u0001\u0003t\u0005iR\r_3dkR|'o\u00144g\u0011\u0016\f\u0007/T3n_JL8+\u001b>f\u0003Nl%\r\u0006\u0004\u0003T\u0011uBq\b\u0005\b\u0005c\n\u0006\u0019\u0001B:\u0011\u001d!\t%\u0015a\u0001\u0003[\u000b1\"\u001a=fGJ+\u0017/^3ti\nYR\t_3dkR|'OU3t_V\u00148-Z:Pe\u0012+g-Y;miN\u001crAUA2\u0007_\ny'A\u0006u_R\fG.T3n\u001b&\u0014\u0015\u0001\u0004;pi\u0006dW*Z7NS\n\u0003C\u0003\u0005C'\t\u001f\"\t\u0006b\u0015\u0005V\u0011]C\u0011\fC.!\r\u0019IG\u0015\u0005\b\u0007k\n\u0007\u0019\u0001B\b\u0011\u001d\u0019I(\u0019a\u0001\u0005'Bqaa b\u0001\u0004\u0011\u0019\u0006C\u0004\u0004\u0004\u0006\u0004\rAa\u0015\t\u000f\r\u001d\u0015\r1\u0001\u0003T!9AqI1A\u0002\tM\u0003bBBFC\u0002\u0007\u0011q\u0013\u000b\u0011\t\u001b\"y\u0006\"\u0019\u0005d\u0011\u0015Dq\rC5\tWB\u0011b!\u001ec!\u0003\u0005\rAa\u0004\t\u0013\re$\r%AA\u0002\tM\u0003\"CB@EB\u0005\t\u0019\u0001B*\u0011%\u0019\u0019I\u0019I\u0001\u0002\u0004\u0011\u0019\u0006C\u0005\u0004\b\n\u0004\n\u00111\u0001\u0003T!IAq\t2\u0011\u0002\u0003\u0007!1\u000b\u0005\n\u0007\u0017\u0013\u0007\u0013!a\u0001\u0003/\u000babY8qs\u0012\"WMZ1vYR$s\u0007\u0006\u0003\u0003&\u0012E\u0004\"CAsY\u0006\u0005\t\u0019AAj)\u0011\u0011i\u0002\"\u001e\t\u0013\u0005\u0015h.!AA\u0002\t\u0015F\u0003\u0002Bv\tsB\u0011\"!:p\u0003\u0003\u0005\r!a5\u0015\t\tuAQ\u0010\u0005\n\u0003K\u0014\u0018\u0011!a\u0001\u0005K\u000b1$\u0012=fGV$xN\u001d*fg>,(oY3t\u001fJ$UMZ1vYR\u001c\bcAB5iN)A\u000f\"\"\u0003\\B!Bq\u0011CG\u0005\u001f\u0011\u0019Fa\u0015\u0003T\tM#1KAL\t\u001bj!\u0001\"#\u000b\t\u0011-\u0015qM\u0001\beVtG/[7f\u0013\u0011!y\t\"#\u0003#\u0005\u00137\u000f\u001e:bGR4UO\\2uS>tw\u0007\u0006\u0002\u0005\u0002\u0006)\u0011\r\u001d9msR\u0001BQ\nCL\t3#Y\n\"(\u0005 \u0012\u0005F1\u0015\u0005\b\u0007k:\b\u0019\u0001B\b\u0011\u001d\u0019Ih\u001ea\u0001\u0005'Bqaa x\u0001\u0004\u0011\u0019\u0006C\u0004\u0004\u0004^\u0004\rAa\u0015\t\u000f\r\u001du\u000f1\u0001\u0003T!9AqI<A\u0002\tM\u0003bBBFo\u0002\u0007\u0011qS\u0001\bk:\f\u0007\u000f\u001d7z)\u0011!I\u000b\"-\u0011\r\u0005\u0015\u0014q\u001eCV!I\t)\u0007\",\u0003\u0010\tM#1\u000bB*\u0005'\u0012\u0019&a&\n\t\u0011=\u0016q\r\u0002\u0007)V\u0004H.Z\u001c\t\u0013\u0011M\u00060!AA\u0002\u00115\u0013a\u0001=%a\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u00111QG\u0001 \t\u00164\u0017-\u001e7u!J|g-\u001b7f\u000bb,7-\u001e;peJ+7o\\;sG\u0016\u001c\b\u0003BB5\u0003g\u0019b!a\r\u0005@\nm\u0007C\u0005CD\t\u0003\u0014yAa\u0015\u0003T\tE#\u0011KAL\u0007OJA\u0001b1\u0005\n\n\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\u001c\u001c\u0015\u0005\u0011mFCDB4\t\u0013$Y\r\"4\u0005P\u0012EG1\u001b\u0005\t\u0007k\nI\u00041\u0001\u0003\u0010!A1\u0011PA\u001d\u0001\u0004\u0011\u0019\u0006\u0003\u0005\u0004\u0000\u0005e\u0002\u0019\u0001B*\u0011!\u0019\u0019)!\u000fA\u0002\tE\u0003\u0002CBD\u0003s\u0001\rA!\u0015\t\u0011\r-\u0015\u0011\ba\u0001\u0003/#B\u0001b6\u0005`B1\u0011QMAx\t3\u0004\u0002#!\u001a\u0005\\\n=!1\u000bB*\u0005#\u0012\t&a&\n\t\u0011u\u0017q\r\u0002\u0007)V\u0004H.\u001a\u001c\t\u0015\u0011M\u00161HA\u0001\u0002\u0004\u00199'A\fdC2\u001cW\u000f\\1uK>3XM\u001d%fC\u0012lU-\\8ssRQ!1\u000bCs\tS$i\u000fb<\t\u0011\u0011\u001d\u0018q\ba\u0001\u0005#\n1c\u001c<fe\"+\u0017\rZ'f[\u001a\u0013x.\\\"p]\u001aD\u0001\u0002b;\u0002@\u0001\u0007!1K\u0001\u001e[&t\u0017.\\;n\u001fZ,'\u000fS3bI6+Wn\u001c:z\rJ|WnQ8oM\"A1\u0011PA \u0001\u0004\u0011\u0019\u0006\u0003\u0005\u0005r\u0006}\u0002\u0019\u0001Cz\u00039yg/\u001a:iK\u0006$g)Y2u_J\u0004B!!\u001a\u0005v&!Aq_A4\u0005\u0019!u.\u001e2mK\u0006ir-\u001a;SKN|WO]2fg\u001a{'o\u00117vgR,'/T1oC\u001e,'\u000f\u0006\t\u0005N\u0011uX\u0011AC\u0003\u000b\u0013)Y!\"\u0004\u0006\u0012!AAq`A!\u0001\u0004\t\u0019.\u0001\u0003sa&#\u0007\u0002CC\u0002\u0003\u0003\u0002\r!a&\u0002\u001b\u0015DXm\u0019*fg>,(oY3t\u0011!)9!!\u0011A\u0002\tM\u0013!F7j]&lW/\\(wKJDW-\u00193NK6|'/\u001f\u0005\t\tc\f\t\u00051\u0001\u0005t\"AAQCA!\u0001\u0004\u0011\u0019\b\u0003\u0005\u0006\u0010\u0005\u0005\u0003\u0019\u0001B\u000f\u0003-I7\u000fU=uQ>t\u0017\t\u001d9\t\u0011\u0015M\u0011\u0011\ta\u0001\u000b+\t\u0001C]3t_V\u00148-Z'baBLgnZ:\u0011\u0011\u0005e\u0015\u0011UAT\u0003O\u000bQ\u0004U-T!\u0006\u00136jX'F\u001b>\u0013\u0016l\u0018'P\u0007\u0006cu\f\u0015*P!\u0016\u0013F+W\u0001\u001f!f\u001b\u0006+\u0011*L?6+Uj\u0014*Z?2{5)\u0011'`!J{\u0005+\u0012*U3\u0002\nQ$\u0012-F\u0007V#vJU0D\u001fJ+5k\u0018'P\u0007\u0006cu\f\u0015*P!\u0016\u0013F+W\u0001\u001f\u000bb+5)\u0016+P%~\u001buJU#T?2{5)\u0011'`!J{\u0005+\u0012*U3\u0002\u0002"
)
public class ResourceProfile implements Serializable, Logging {
   private final Map executorResources;
   private final Map taskResources;
   private int _id;
   private Option _executorResourceSlotsPerAddr;
   private Option _limitingResource;
   private Option _maxTasksPerExecutor;
   private boolean _coresLimitKnown;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public static int DEFAULT_RESOURCE_PROFILE_ID() {
      return ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID();
   }

   public static int UNKNOWN_RESOURCE_PROFILE_ID() {
      return ResourceProfile$.MODULE$.UNKNOWN_RESOURCE_PROFILE_ID();
   }

   public static String[] allSupportedExecutorResources() {
      return ResourceProfile$.MODULE$.allSupportedExecutorResources();
   }

   public static String PYSPARK_MEM() {
      return ResourceProfile$.MODULE$.PYSPARK_MEM();
   }

   public static String OVERHEAD_MEM() {
      return ResourceProfile$.MODULE$.OVERHEAD_MEM();
   }

   public static String OFFHEAP_MEM() {
      return ResourceProfile$.MODULE$.OFFHEAP_MEM();
   }

   public static String MEMORY() {
      return ResourceProfile$.MODULE$.MEMORY();
   }

   public static String CORES() {
      return ResourceProfile$.MODULE$.CORES();
   }

   public static String CPUS() {
      return ResourceProfile$.MODULE$.CPUS();
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

   public void withLogContext(final java.util.Map context, final Function0 body) {
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

   public Map executorResources() {
      return this.executorResources;
   }

   public Map taskResources() {
      return this.taskResources;
   }

   private int _id() {
      return this._id;
   }

   private void _id_$eq(final int x$1) {
      this._id = x$1;
   }

   private Option _executorResourceSlotsPerAddr() {
      return this._executorResourceSlotsPerAddr;
   }

   private void _executorResourceSlotsPerAddr_$eq(final Option x$1) {
      this._executorResourceSlotsPerAddr = x$1;
   }

   private Option _limitingResource() {
      return this._limitingResource;
   }

   private void _limitingResource_$eq(final Option x$1) {
      this._limitingResource = x$1;
   }

   private Option _maxTasksPerExecutor() {
      return this._maxTasksPerExecutor;
   }

   private void _maxTasksPerExecutor_$eq(final Option x$1) {
      this._maxTasksPerExecutor = x$1;
   }

   private boolean _coresLimitKnown() {
      return this._coresLimitKnown;
   }

   private void _coresLimitKnown_$eq(final boolean x$1) {
      this._coresLimitKnown = x$1;
   }

   public void validate() {
      this.taskResources().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$validate$1(check$ifrefutable$1))).foreach((x$1) -> {
         $anonfun$validate$2(x$1);
         return BoxedUnit.UNIT;
      });
   }

   public int id() {
      return this._id();
   }

   public java.util.Map taskResourcesJMap() {
      return .MODULE$.MapHasAsJava(this.taskResources()).asJava();
   }

   public java.util.Map executorResourcesJMap() {
      return .MODULE$.MapHasAsJava(this.executorResources()).asJava();
   }

   public Option getExecutorCores() {
      return this.executorResources().get(ResourceProfile$.MODULE$.CORES()).map((x$2) -> BoxesRunTime.boxToInteger($anonfun$getExecutorCores$1(x$2)));
   }

   public Option getTaskCpus() {
      return this.taskResources().get(ResourceProfile$.MODULE$.CPUS()).map((x$3) -> BoxesRunTime.boxToInteger($anonfun$getTaskCpus$1(x$3)));
   }

   public Option getPySparkMemory() {
      return this.executorResources().get(ResourceProfile$.MODULE$.PYSPARK_MEM()).map((x$4) -> BoxesRunTime.boxToLong($anonfun$getPySparkMemory$1(x$4)));
   }

   public Option getExecutorMemory() {
      return this.executorResources().get(ResourceProfile$.MODULE$.MEMORY()).map((x$5) -> BoxesRunTime.boxToLong($anonfun$getExecutorMemory$1(x$5)));
   }

   public Map getCustomTaskResources() {
      return (Map)this.taskResources().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getCustomTaskResources$1(x0$1)));
   }

   public Map getCustomExecutorResources() {
      return (Map)this.executorResources().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$getCustomExecutorResources$1(x0$1)));
   }

   public int getSchedulerTaskResourceAmount(final String resource) {
      TaskResourceRequest taskAmount = (TaskResourceRequest)this.taskResources().getOrElse(resource, () -> {
         throw new SparkException("Resource " + resource + " doesn't exist in profile id: " + this.id());
      });
      return taskAmount.amount() < (double)1 ? 1 : (int)taskAmount.amount();
   }

   public int getNumSlotsPerAddress(final String resource, final SparkConf sparkConf) {
      this._executorResourceSlotsPerAddr().getOrElse((JFunction0.mcV.sp)() -> this.calculateTasksAndLimitingResource(sparkConf));
      return BoxesRunTime.unboxToInt(((MapOps)this._executorResourceSlotsPerAddr().get()).getOrElse(resource, () -> {
         throw new SparkException("Resource " + resource + " doesn't exist in profile id: " + this.id());
      }));
   }

   public int maxTasksPerExecutor(final SparkConf sparkConf) {
      return BoxesRunTime.unboxToInt(this._maxTasksPerExecutor().getOrElse((JFunction0.mcI.sp)() -> {
         this.calculateTasksAndLimitingResource(sparkConf);
         return BoxesRunTime.unboxToInt(this._maxTasksPerExecutor().get());
      }));
   }

   public boolean isCoresLimitKnown() {
      return this._coresLimitKnown();
   }

   public String limitingResource(final SparkConf sparkConf) {
      return (String)this._limitingResource().getOrElse(() -> {
         this.calculateTasksAndLimitingResource(sparkConf);
         return (String)this._limitingResource().get();
      });
   }

   private boolean shouldCheckExecutorCores(final SparkConf sparkConf) {
      Option master = sparkConf.getOption("spark.master");
      return sparkConf.contains(package$.MODULE$.EXECUTOR_CORES()) || master.isDefined() && (((String)master.get()).equalsIgnoreCase("yarn") || ((String)master.get()).startsWith("k8s"));
   }

   private synchronized void calculateTasksAndLimitingResource(final SparkConf sparkConf) {
      boolean shouldCheckExecCores = this.shouldCheckExecutorCores(sparkConf);
      Tuple2 var10000;
      if (shouldCheckExecCores) {
         int cpusPerTask = (int)BoxesRunTime.unboxToDouble(this.taskResources().get(ResourceProfile$.MODULE$.CPUS()).map((x$6) -> BoxesRunTime.boxToDouble($anonfun$calculateTasksAndLimitingResource$1(x$6))).getOrElse((JFunction0.mcD.sp)() -> (double)BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.CPUS_PER_TASK()))));
         scala.Predef..MODULE$.assert(cpusPerTask > 0, () -> "CPUs per task configuration has to be > 0");
         int coresPerExecutor = BoxesRunTime.unboxToInt(this.getExecutorCores().getOrElse((JFunction0.mcI.sp)() -> BoxesRunTime.unboxToInt(sparkConf.get(package$.MODULE$.EXECUTOR_CORES()))));
         this._coresLimitKnown_$eq(true);
         ResourceUtils$.MODULE$.validateTaskCpusLargeEnough(sparkConf, coresPerExecutor, cpusPerTask);
         int tasksBasedOnCores = coresPerExecutor / cpusPerTask;
         var10000 = new Tuple2(BoxesRunTime.boxToInteger(tasksBasedOnCores), ResourceProfile$.MODULE$.CPUS());
      } else {
         var10000 = new Tuple2(BoxesRunTime.boxToInteger(-1), "");
      }

      Tuple2 var5 = var10000;
      if (var5 != null) {
         int taskLimit = var5._1$mcI$sp();
         String limitingResource = (String)var5._2();
         Tuple2 var4 = new Tuple2(BoxesRunTime.boxToInteger(taskLimit), limitingResource);
         IntRef taskLimit = IntRef.create(var4._1$mcI$sp());
         ObjectRef limitingResource = ObjectRef.create((String)var4._2());
         HashMap numPartsPerResourceMap = new HashMap();
         numPartsPerResourceMap.update(ResourceProfile$.MODULE$.CORES(), BoxesRunTime.boxToInteger(1));
         HashMap taskResourcesToCheck = new HashMap();
         taskResourcesToCheck.$plus$plus$eq(this.getCustomTaskResources());
         Map execResourceToCheck = this.getCustomExecutorResources();
         execResourceToCheck.foreach((x0$1) -> {
            if (x0$1 != null) {
               String rName = (String)x0$1._1();
               ExecutorResourceRequest execReq = (ExecutorResourceRequest)x0$1._2();
               double taskReq = BoxesRunTime.unboxToDouble(this.taskResources().get(rName).map((x$8) -> BoxesRunTime.boxToDouble($anonfun$calculateTasksAndLimitingResource$6(x$8))).getOrElse((JFunction0.mcD.sp)() -> (double)0.0F));
               numPartsPerResourceMap.update(rName, BoxesRunTime.boxToInteger(1));
               if (!(taskReq > (double)0.0F)) {
                  this.logWarning(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"The executor resource config for resource: ", " "})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE_NAME..MODULE$, rName)}))).$plus(this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"was specified but no corresponding task resource request was specified."})))).log(scala.collection.immutable.Nil..MODULE$))));
                  return BoxedUnit.UNIT;
               } else if (taskReq > (double)execReq.amount()) {
                  throw new SparkException("The executor resource: " + rName + ", amount: " + execReq.amount() + " needs to be >= the task resource request amount of " + taskReq);
               } else {
                  Tuple2 var14 = ResourceUtils$.MODULE$.calculateAmountAndPartsForFraction(taskReq);
                  if (var14 == null) {
                     throw new MatchError(var14);
                  } else {
                     int numPerTask = var14._1$mcI$sp();
                     int parts = var14._2$mcI$sp();
                     Tuple2.mcII.sp var13 = new Tuple2.mcII.sp(numPerTask, parts);
                     int numPerTaskx = ((Tuple2)var13)._1$mcI$sp();
                     int partsx = ((Tuple2)var13)._2$mcI$sp();
                     numPartsPerResourceMap.update(rName, BoxesRunTime.boxToInteger(partsx));
                     int numTasks = (int)(execReq.amount() * (long)partsx / (long)numPerTaskx);
                     if (taskLimit.elem == -1 || numTasks < taskLimit.elem) {
                        limitingResource.elem = rName;
                        taskLimit.elem = numTasks;
                     }

                     return taskResourcesToCheck.$minus$eq(rName);
                  }
               }
            } else {
               throw new MatchError(x0$1);
            }
         });
         if (taskResourcesToCheck.nonEmpty()) {
            Iterable var10002 = taskResourcesToCheck.keys();
            throw new SparkException("No executor resource configs were specified for the following task configs: " + var10002.mkString(","));
         } else {
            String limiting = taskLimit.elem == -1 ? "cpu" : (String)limitingResource.elem + " at " + taskLimit.elem + " tasks per executor";
            this.logInfo(org.apache.spark.internal.LogEntry..MODULE$.from(() -> this.LogStringContext(new StringContext(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"Limiting resource is ", ""})))).log(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new MDC[]{new MDC(org.apache.spark.internal.LogKeys.RESOURCE..MODULE$, limiting)})))));
            this._executorResourceSlotsPerAddr_$eq(new Some(numPartsPerResourceMap.toMap(scala..less.colon.less..MODULE$.refl())));
            this._maxTasksPerExecutor_$eq(taskLimit.elem == -1 ? new Some(BoxesRunTime.boxToInteger(1)) : new Some(BoxesRunTime.boxToInteger(taskLimit.elem)));
            this._limitingResource_$eq(new Some((String)limitingResource.elem));
            if (shouldCheckExecCores) {
               ResourceUtils$.MODULE$.warnOnWastedResources(this, sparkConf, ResourceUtils$.MODULE$.warnOnWastedResources$default$3());
            }
         }
      } else {
         throw new MatchError(var5);
      }
   }

   public void setResourceProfileId(final int id) {
      this._id_$eq(id);
   }

   public void setToDefaultProfile() {
      this._id_$eq(ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID());
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof ResourceProfile var4)) {
         return false;
      } else {
         boolean var10;
         label52: {
            label45: {
               Class var10000 = var4.getClass();
               Class var5 = this.getClass();
               if (var10000 == null) {
                  if (var5 != null) {
                     break label45;
                  }
               } else if (!var10000.equals(var5)) {
                  break label45;
               }

               if (var4.id() == this._id()) {
                  label47: {
                     Map var8 = var4.taskResources();
                     Map var6 = this.taskResources();
                     if (var8 == null) {
                        if (var6 != null) {
                           break label47;
                        }
                     } else if (!var8.equals(var6)) {
                        break label47;
                     }

                     var8 = var4.executorResources();
                     Map var7 = this.executorResources();
                     if (var8 == null) {
                        if (var7 == null) {
                           break label52;
                        }
                     } else if (var8.equals(var7)) {
                        break label52;
                     }
                  }
               }
            }

            var10 = false;
            return var10;
         }

         var10 = true;
         return var10;
      }
   }

   public boolean resourcesEqual(final ResourceProfile rp) {
      boolean var7;
      label41: {
         label36: {
            Map var10000 = rp.taskResources();
            Map var2 = this.taskResources();
            if (var10000 == null) {
               if (var2 != null) {
                  break label36;
               }
            } else if (!var10000.equals(var2)) {
               break label36;
            }

            var10000 = rp.executorResources();
            Map var3 = this.executorResources();
            if (var10000 == null) {
               if (var3 != null) {
                  break label36;
               }
            } else if (!var10000.equals(var3)) {
               break label36;
            }

            Class var6 = rp.getClass();
            Class var4 = this.getClass();
            if (var6 == null) {
               if (var4 == null) {
                  break label41;
               }
            } else if (var6.equals(var4)) {
               break label41;
            }
         }

         var7 = false;
         return var7;
      }

      var7 = true;
      return var7;
   }

   public int hashCode() {
      return (new scala.collection.immutable..colon.colon(this.taskResources(), new scala.collection.immutable..colon.colon(this.executorResources(), scala.collection.immutable.Nil..MODULE$))).hashCode();
   }

   public String toString() {
      int var10000 = this._id();
      return "Profile: id = " + var10000 + ", executor resources: " + this.executorResources().mkString(",") + ", task resources: " + this.taskResources().mkString(",");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$validate$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$validate$2(final Tuple2 x$1) {
      if (x$1 == null) {
         throw new MatchError(x$1);
      } else {
         TaskResourceRequest taskReq = (TaskResourceRequest)x$1._2();
         double taskAmount = taskReq.amount();
         scala.Predef..MODULE$.assert(taskAmount <= (double)0.5F || taskAmount % (double)1 == (double)0, () -> "The task resource amount " + taskAmount + " must be either <= 0.5, or a whole number.");
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$getExecutorCores$1(final ExecutorResourceRequest x$2) {
      return (int)x$2.amount();
   }

   // $FF: synthetic method
   public static final int $anonfun$getTaskCpus$1(final TaskResourceRequest x$3) {
      return (int)x$3.amount();
   }

   // $FF: synthetic method
   public static final long $anonfun$getPySparkMemory$1(final ExecutorResourceRequest x$4) {
      return x$4.amount();
   }

   // $FF: synthetic method
   public static final long $anonfun$getExecutorMemory$1(final ExecutorResourceRequest x$5) {
      return x$5.amount();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCustomTaskResources$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return !k.equals(ResourceProfile$.MODULE$.CPUS());
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getCustomExecutorResources$1(final Tuple2 x0$1) {
      if (x0$1 != null) {
         String k = (String)x0$1._1();
         return !scala.collection.ArrayOps..MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])ResourceProfile$.MODULE$.allSupportedExecutorResources()), k);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$calculateTasksAndLimitingResource$1(final TaskResourceRequest x$6) {
      return x$6.amount();
   }

   // $FF: synthetic method
   public static final double $anonfun$calculateTasksAndLimitingResource$6(final TaskResourceRequest x$8) {
      return x$8.amount();
   }

   public ResourceProfile(final Map executorResources, final Map taskResources) {
      this.executorResources = executorResources;
      this.taskResources = taskResources;
      Logging.$init$(this);
      this.validate();
      this._id = ResourceProfile$.MODULE$.getNextProfileId();
      this._executorResourceSlotsPerAddr = scala.None..MODULE$;
      this._limitingResource = scala.None..MODULE$;
      this._maxTasksPerExecutor = scala.None..MODULE$;
      this._coresLimitKnown = false;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class ExecutorResourcesOrDefaults implements Product, Serializable {
      private final Option cores;
      private final long executorMemoryMiB;
      private final long memoryOffHeapMiB;
      private final long pysparkMemoryMiB;
      private final long memoryOverheadMiB;
      private final long totalMemMiB;
      private final Map customResources;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Option cores() {
         return this.cores;
      }

      public long executorMemoryMiB() {
         return this.executorMemoryMiB;
      }

      public long memoryOffHeapMiB() {
         return this.memoryOffHeapMiB;
      }

      public long pysparkMemoryMiB() {
         return this.pysparkMemoryMiB;
      }

      public long memoryOverheadMiB() {
         return this.memoryOverheadMiB;
      }

      public long totalMemMiB() {
         return this.totalMemMiB;
      }

      public Map customResources() {
         return this.customResources;
      }

      public ExecutorResourcesOrDefaults copy(final Option cores, final long executorMemoryMiB, final long memoryOffHeapMiB, final long pysparkMemoryMiB, final long memoryOverheadMiB, final long totalMemMiB, final Map customResources) {
         return new ExecutorResourcesOrDefaults(cores, executorMemoryMiB, memoryOffHeapMiB, pysparkMemoryMiB, memoryOverheadMiB, totalMemMiB, customResources);
      }

      public Option copy$default$1() {
         return this.cores();
      }

      public long copy$default$2() {
         return this.executorMemoryMiB();
      }

      public long copy$default$3() {
         return this.memoryOffHeapMiB();
      }

      public long copy$default$4() {
         return this.pysparkMemoryMiB();
      }

      public long copy$default$5() {
         return this.memoryOverheadMiB();
      }

      public long copy$default$6() {
         return this.totalMemMiB();
      }

      public Map copy$default$7() {
         return this.customResources();
      }

      public String productPrefix() {
         return "ExecutorResourcesOrDefaults";
      }

      public int productArity() {
         return 7;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.cores();
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.executorMemoryMiB());
            }
            case 2 -> {
               return BoxesRunTime.boxToLong(this.memoryOffHeapMiB());
            }
            case 3 -> {
               return BoxesRunTime.boxToLong(this.pysparkMemoryMiB());
            }
            case 4 -> {
               return BoxesRunTime.boxToLong(this.memoryOverheadMiB());
            }
            case 5 -> {
               return BoxesRunTime.boxToLong(this.totalMemMiB());
            }
            case 6 -> {
               return this.customResources();
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
         return x$1 instanceof ExecutorResourcesOrDefaults;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "cores";
            }
            case 1 -> {
               return "executorMemoryMiB";
            }
            case 2 -> {
               return "memoryOffHeapMiB";
            }
            case 3 -> {
               return "pysparkMemoryMiB";
            }
            case 4 -> {
               return "memoryOverheadMiB";
            }
            case 5 -> {
               return "totalMemMiB";
            }
            case 6 -> {
               return "customResources";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.cores()));
         var1 = Statics.mix(var1, Statics.longHash(this.executorMemoryMiB()));
         var1 = Statics.mix(var1, Statics.longHash(this.memoryOffHeapMiB()));
         var1 = Statics.mix(var1, Statics.longHash(this.pysparkMemoryMiB()));
         var1 = Statics.mix(var1, Statics.longHash(this.memoryOverheadMiB()));
         var1 = Statics.mix(var1, Statics.longHash(this.totalMemMiB()));
         var1 = Statics.mix(var1, Statics.anyHash(this.customResources()));
         return Statics.finalizeHash(var1, 7);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var8;
         if (this != x$1) {
            label75: {
               if (x$1 instanceof ExecutorResourcesOrDefaults) {
                  ExecutorResourcesOrDefaults var4 = (ExecutorResourcesOrDefaults)x$1;
                  if (this.executorMemoryMiB() == var4.executorMemoryMiB() && this.memoryOffHeapMiB() == var4.memoryOffHeapMiB() && this.pysparkMemoryMiB() == var4.pysparkMemoryMiB() && this.memoryOverheadMiB() == var4.memoryOverheadMiB() && this.totalMemMiB() == var4.totalMemMiB()) {
                     label68: {
                        Option var10000 = this.cores();
                        Option var5 = var4.cores();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label68;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label68;
                        }

                        Map var7 = this.customResources();
                        Map var6 = var4.customResources();
                        if (var7 == null) {
                           if (var6 != null) {
                              break label68;
                           }
                        } else if (!var7.equals(var6)) {
                           break label68;
                        }

                        if (var4.canEqual(this)) {
                           break label75;
                        }
                     }
                  }
               }

               var8 = false;
               return var8;
            }
         }

         var8 = true;
         return var8;
      }

      public ExecutorResourcesOrDefaults(final Option cores, final long executorMemoryMiB, final long memoryOffHeapMiB, final long pysparkMemoryMiB, final long memoryOverheadMiB, final long totalMemMiB, final Map customResources) {
         this.cores = cores;
         this.executorMemoryMiB = executorMemoryMiB;
         this.memoryOffHeapMiB = memoryOffHeapMiB;
         this.pysparkMemoryMiB = pysparkMemoryMiB;
         this.memoryOverheadMiB = memoryOverheadMiB;
         this.totalMemMiB = totalMemMiB;
         this.customResources = customResources;
         Product.$init$(this);
      }
   }

   public static class ExecutorResourcesOrDefaults$ extends AbstractFunction7 implements Serializable {
      public static final ExecutorResourcesOrDefaults$ MODULE$ = new ExecutorResourcesOrDefaults$();

      public final String toString() {
         return "ExecutorResourcesOrDefaults";
      }

      public ExecutorResourcesOrDefaults apply(final Option cores, final long executorMemoryMiB, final long memoryOffHeapMiB, final long pysparkMemoryMiB, final long memoryOverheadMiB, final long totalMemMiB, final Map customResources) {
         return new ExecutorResourcesOrDefaults(cores, executorMemoryMiB, memoryOffHeapMiB, pysparkMemoryMiB, memoryOverheadMiB, totalMemMiB, customResources);
      }

      public Option unapply(final ExecutorResourcesOrDefaults x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple7(x$0.cores(), BoxesRunTime.boxToLong(x$0.executorMemoryMiB()), BoxesRunTime.boxToLong(x$0.memoryOffHeapMiB()), BoxesRunTime.boxToLong(x$0.pysparkMemoryMiB()), BoxesRunTime.boxToLong(x$0.memoryOverheadMiB()), BoxesRunTime.boxToLong(x$0.totalMemMiB()), x$0.customResources())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(ExecutorResourcesOrDefaults$.class);
      }
   }

   public static class DefaultProfileExecutorResources implements Product, Serializable {
      private final Option cores;
      private final long executorMemoryMiB;
      private final long memoryOffHeapMiB;
      private final Option pysparkMemoryMiB;
      private final Option memoryOverheadMiB;
      private final Map customResources;

      public Iterator productElementNames() {
         return Product.productElementNames$(this);
      }

      public Option cores() {
         return this.cores;
      }

      public long executorMemoryMiB() {
         return this.executorMemoryMiB;
      }

      public long memoryOffHeapMiB() {
         return this.memoryOffHeapMiB;
      }

      public Option pysparkMemoryMiB() {
         return this.pysparkMemoryMiB;
      }

      public Option memoryOverheadMiB() {
         return this.memoryOverheadMiB;
      }

      public Map customResources() {
         return this.customResources;
      }

      public DefaultProfileExecutorResources copy(final Option cores, final long executorMemoryMiB, final long memoryOffHeapMiB, final Option pysparkMemoryMiB, final Option memoryOverheadMiB, final Map customResources) {
         return new DefaultProfileExecutorResources(cores, executorMemoryMiB, memoryOffHeapMiB, pysparkMemoryMiB, memoryOverheadMiB, customResources);
      }

      public Option copy$default$1() {
         return this.cores();
      }

      public long copy$default$2() {
         return this.executorMemoryMiB();
      }

      public long copy$default$3() {
         return this.memoryOffHeapMiB();
      }

      public Option copy$default$4() {
         return this.pysparkMemoryMiB();
      }

      public Option copy$default$5() {
         return this.memoryOverheadMiB();
      }

      public Map copy$default$6() {
         return this.customResources();
      }

      public String productPrefix() {
         return "DefaultProfileExecutorResources";
      }

      public int productArity() {
         return 6;
      }

      public Object productElement(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return this.cores();
            }
            case 1 -> {
               return BoxesRunTime.boxToLong(this.executorMemoryMiB());
            }
            case 2 -> {
               return BoxesRunTime.boxToLong(this.memoryOffHeapMiB());
            }
            case 3 -> {
               return this.pysparkMemoryMiB();
            }
            case 4 -> {
               return this.memoryOverheadMiB();
            }
            case 5 -> {
               return this.customResources();
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
         return x$1 instanceof DefaultProfileExecutorResources;
      }

      public String productElementName(final int x$1) {
         switch (x$1) {
            case 0 -> {
               return "cores";
            }
            case 1 -> {
               return "executorMemoryMiB";
            }
            case 2 -> {
               return "memoryOffHeapMiB";
            }
            case 3 -> {
               return "pysparkMemoryMiB";
            }
            case 4 -> {
               return "memoryOverheadMiB";
            }
            case 5 -> {
               return "customResources";
            }
            default -> {
               return (String)Statics.ioobe(x$1);
            }
         }
      }

      public int hashCode() {
         int var1 = -889275714;
         var1 = Statics.mix(var1, this.productPrefix().hashCode());
         var1 = Statics.mix(var1, Statics.anyHash(this.cores()));
         var1 = Statics.mix(var1, Statics.longHash(this.executorMemoryMiB()));
         var1 = Statics.mix(var1, Statics.longHash(this.memoryOffHeapMiB()));
         var1 = Statics.mix(var1, Statics.anyHash(this.pysparkMemoryMiB()));
         var1 = Statics.mix(var1, Statics.anyHash(this.memoryOverheadMiB()));
         var1 = Statics.mix(var1, Statics.anyHash(this.customResources()));
         return Statics.finalizeHash(var1, 6);
      }

      public String toString() {
         return scala.runtime.ScalaRunTime..MODULE$._toString(this);
      }

      public boolean equals(final Object x$1) {
         boolean var12;
         if (this != x$1) {
            label79: {
               if (x$1 instanceof DefaultProfileExecutorResources) {
                  DefaultProfileExecutorResources var4 = (DefaultProfileExecutorResources)x$1;
                  if (this.executorMemoryMiB() == var4.executorMemoryMiB() && this.memoryOffHeapMiB() == var4.memoryOffHeapMiB()) {
                     label72: {
                        Option var10000 = this.cores();
                        Option var5 = var4.cores();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label72;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label72;
                        }

                        var10000 = this.pysparkMemoryMiB();
                        Option var6 = var4.pysparkMemoryMiB();
                        if (var10000 == null) {
                           if (var6 != null) {
                              break label72;
                           }
                        } else if (!var10000.equals(var6)) {
                           break label72;
                        }

                        var10000 = this.memoryOverheadMiB();
                        Option var7 = var4.memoryOverheadMiB();
                        if (var10000 == null) {
                           if (var7 != null) {
                              break label72;
                           }
                        } else if (!var10000.equals(var7)) {
                           break label72;
                        }

                        Map var11 = this.customResources();
                        Map var8 = var4.customResources();
                        if (var11 == null) {
                           if (var8 != null) {
                              break label72;
                           }
                        } else if (!var11.equals(var8)) {
                           break label72;
                        }

                        if (var4.canEqual(this)) {
                           break label79;
                        }
                     }
                  }
               }

               var12 = false;
               return var12;
            }
         }

         var12 = true;
         return var12;
      }

      public DefaultProfileExecutorResources(final Option cores, final long executorMemoryMiB, final long memoryOffHeapMiB, final Option pysparkMemoryMiB, final Option memoryOverheadMiB, final Map customResources) {
         this.cores = cores;
         this.executorMemoryMiB = executorMemoryMiB;
         this.memoryOffHeapMiB = memoryOffHeapMiB;
         this.pysparkMemoryMiB = pysparkMemoryMiB;
         this.memoryOverheadMiB = memoryOverheadMiB;
         this.customResources = customResources;
         Product.$init$(this);
      }
   }

   public static class DefaultProfileExecutorResources$ extends AbstractFunction6 implements Serializable {
      public static final DefaultProfileExecutorResources$ MODULE$ = new DefaultProfileExecutorResources$();

      public final String toString() {
         return "DefaultProfileExecutorResources";
      }

      public DefaultProfileExecutorResources apply(final Option cores, final long executorMemoryMiB, final long memoryOffHeapMiB, final Option pysparkMemoryMiB, final Option memoryOverheadMiB, final Map customResources) {
         return new DefaultProfileExecutorResources(cores, executorMemoryMiB, memoryOffHeapMiB, pysparkMemoryMiB, memoryOverheadMiB, customResources);
      }

      public Option unapply(final DefaultProfileExecutorResources x$0) {
         return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(new Tuple6(x$0.cores(), BoxesRunTime.boxToLong(x$0.executorMemoryMiB()), BoxesRunTime.boxToLong(x$0.memoryOffHeapMiB()), x$0.pysparkMemoryMiB(), x$0.memoryOverheadMiB(), x$0.customResources())));
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DefaultProfileExecutorResources$.class);
      }
   }
}
