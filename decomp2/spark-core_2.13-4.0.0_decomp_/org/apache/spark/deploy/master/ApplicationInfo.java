package org.apache.spark.deploy.master;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;
import org.apache.spark.deploy.ApplicationDescription;
import org.apache.spark.resource.ResourceProfile;
import org.apache.spark.resource.ResourceProfile$;
import org.apache.spark.resource.ResourceUtils$;
import org.apache.spark.rpc.RpcEndpointRef;
import org.apache.spark.util.Utils$;
import scala.Enumeration;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.Set;
import scala.collection.mutable.Shrinkable;
import scala.collection.mutable.HashSet.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\rEa!B%K\u00019#\u0006\u0002\u00035\u0001\u0005\u000b\u0007I\u0011A5\t\u00115\u0004!\u0011!Q\u0001\n)D\u0001B\u001c\u0001\u0003\u0006\u0004%\ta\u001c\u0005\tq\u0002\u0011\t\u0011)A\u0005a\"A\u0011\u0010\u0001BC\u0002\u0013\u0005!\u0010\u0003\u0005\u0000\u0001\t\u0005\t\u0015!\u0003|\u0011)\t\t\u0001\u0001BC\u0002\u0013\u0005\u00111\u0001\u0005\u000b\u0003+\u0001!\u0011!Q\u0001\n\u0005\u0015\u0001BCA\f\u0001\t\u0015\r\u0011\"\u0001\u0002\u001a!Q\u0011q\u0005\u0001\u0003\u0002\u0003\u0006I!a\u0007\t\u0015\u0005%\u0002A!A!\u0002\u0013\tY\u0003C\u0004\u00022\u0001!\t!a\r\t\u0017\u0005\u0015\u0003\u00011AA\u0002\u0013\u0005\u0011q\t\u0005\f\u00033\u0002\u0001\u0019!a\u0001\n\u0003\tY\u0006C\u0006\u0002h\u0001\u0001\r\u0011!Q!\n\u0005%\u0003bCA9\u0001\u0001\u0007\t\u0019!C\u0001\u0003gB1\"a#\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0002\u000e\"Y\u0011\u0011\u0013\u0001A\u0002\u0003\u0005\u000b\u0015BA;\u0011-\t)\n\u0001a\u0001\u0002\u0004%\t!a&\t\u0017\u0005}\u0005\u00011AA\u0002\u0013\u0005\u0011\u0011\u0015\u0005\f\u0003K\u0003\u0001\u0019!A!B\u0013\tI\nC\u0006\u0002*\u0002\u0001\r\u00111A\u0005\u0002\u0005-\u0006bCAW\u0001\u0001\u0007\t\u0019!C\u0001\u0003_C1\"a-\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002,!Q\u0011q\u0017\u0001A\u0002\u0003\u0007I\u0011A5\t\u0017\u0005e\u0006\u00011AA\u0002\u0013\u0005\u00111\u0018\u0005\u000b\u0003\u007f\u0003\u0001\u0019!A!B\u0013Q\u0007bCAb\u0001\u0001\u0007\t\u0019!C\u0001\u0003\u000bD1\"!4\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0002P\"Y\u00111\u001b\u0001A\u0002\u0003\u0005\u000b\u0015BAd\u0011-\t9\u000e\u0001a\u0001\u0002\u0004%I!!7\t\u0017\u0005\r\b\u00011AA\u0002\u0013%\u0011Q\u001d\u0005\f\u0003S\u0004\u0001\u0019!A!B\u0013\tY\u000eC\u0006\u0002n\u0002\u0001\r\u00111A\u0005\n\u0005=\bbCAz\u0001\u0001\u0007\t\u0019!C\u0005\u0003kD1\"!?\u0001\u0001\u0004\u0005\t\u0015)\u0003\u0002r\"Y\u0011Q \u0001A\u0002\u0003\u0007I\u0011BA\u0000\u0011-\u0011y\u0001\u0001a\u0001\u0002\u0004%IA!\u0005\t\u0017\tU\u0001\u00011A\u0001B\u0003&!\u0011\u0001\u0005\f\u00053\u0001\u0001\u0019!a\u0001\n\u0013\u0011Y\u0002C\u0006\u0003&\u0001\u0001\r\u00111A\u0005\n\t\u001d\u0002b\u0003B\u0016\u0001\u0001\u0007\t\u0011)Q\u0005\u0005;A1Ba\f\u0001\u0001\u0004\u0005\r\u0011\"\u0003\u0002,\"Y!\u0011\u0007\u0001A\u0002\u0003\u0007I\u0011\u0002B\u001a\u0011-\u00119\u0004\u0001a\u0001\u0002\u0003\u0006K!a\u000b\t\u000f\tm\u0002\u0001\"\u0003\u0003>!9!q\n\u0001\u0005\n\tE\u0003\u0002\u0003B*\u0001\u0011\u0005AJ!\u0016\t\u0011\tm\u0003\u0001\"\u0001M\u0005;B\u0001B!\u0019\u0001\t\u0003a%1\r\u0005\b\u0005W\u0002A\u0011\u0002B7\u0011!\u0011\u0019\b\u0001C\u0001\u0019\nU\u0004\u0002\u0003B=\u0001\u0011\u0005AJa\u001f\t\u0011\t\u001d\u0005\u0001\"\u0001M\u0005\u0013CqA!$\u0001\t\u0013\u0011y\tC\u0005\u0003\u001c\u0002\t\n\u0011\"\u0003\u0003\u001e\"A!1\u0017\u0001\u0005\u0002)\u0013)\f\u0003\u0006\u0003Z\u0002\t\n\u0011\"\u0001K\u0005;C\u0001Ba7\u0001\t\u0003Q%Q\u001c\u0005\n\u0005G\u0004!\u0019!C\u0005\u0003WC\u0001B!:\u0001A\u0003%\u00111\u0006\u0005\t\u0005O\u0004A\u0011\u0001&\u0002,\"I!\u0011\u001e\u0001A\u0002\u0013%\u00111\u0016\u0005\n\u0005W\u0004\u0001\u0019!C\u0005\u0005[D\u0001B!=\u0001A\u0003&\u00111\u0006\u0005\t\u0005g\u0004A\u0011\u0001&\u0002,\"A!Q\u001f\u0001\u0005\u0002)\u00139\u0010\u0003\u0005\u0003z\u0002!\tA\u0013B)\u0011!\u0011Y\u0010\u0001C\u0001\u0015\nu\b\u0002CB\u0002\u0001\u0011\u0005!j!\u0002\t\u0011\r5\u0001\u0001\"\u0001M\u0003WCaaa\u0004\u0001\t\u0003I'aD!qa2L7-\u0019;j_:LeNZ8\u000b\u0005-c\u0015AB7bgR,'O\u0003\u0002N\u001d\u00061A-\u001a9m_fT!a\u0014)\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0013\u0016AB1qC\u000eDWMC\u0001T\u0003\ry'oZ\n\u0004\u0001U[\u0006C\u0001,Z\u001b\u00059&\"\u0001-\u0002\u000bM\u001c\u0017\r\\1\n\u0005i;&AB!osJ+g\r\u0005\u0002]K:\u0011Ql\u0019\b\u0003=\nl\u0011a\u0018\u0006\u0003A\u0006\fa\u0001\u0010:p_Rt4\u0001A\u0005\u00021&\u0011AmV\u0001\ba\u0006\u001c7.Y4f\u0013\t1wM\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002e/\u0006I1\u000f^1siRKW.Z\u000b\u0002UB\u0011ak[\u0005\u0003Y^\u0013A\u0001T8oO\u0006Q1\u000f^1siRKW.\u001a\u0011\u0002\u0005%$W#\u00019\u0011\u0005E,hB\u0001:t!\tqv+\u0003\u0002u/\u00061\u0001K]3eK\u001aL!A^<\u0003\rM#(/\u001b8h\u0015\t!x+A\u0002jI\u0002\nA\u0001Z3tGV\t1\u0010\u0005\u0002}{6\tA*\u0003\u0002\u007f\u0019\n1\u0012\t\u001d9mS\u000e\fG/[8o\t\u0016\u001c8M]5qi&|g.A\u0003eKN\u001c\u0007%\u0001\u0006tk\nl\u0017\u000e\u001e#bi\u0016,\"!!\u0002\u0011\t\u0005\u001d\u0011\u0011C\u0007\u0003\u0003\u0013QA!a\u0003\u0002\u000e\u0005!Q\u000f^5m\u0015\t\ty!\u0001\u0003kCZ\f\u0017\u0002BA\n\u0003\u0013\u0011A\u0001R1uK\u0006Y1/\u001e2nSR$\u0015\r^3!\u0003\u0019!'/\u001b<feV\u0011\u00111\u0004\t\u0005\u0003;\t\u0019#\u0004\u0002\u0002 )\u0019\u0011\u0011\u0005(\u0002\u0007I\u00048-\u0003\u0003\u0002&\u0005}!A\u0004*qG\u0016sG\r]8j]R\u0014VMZ\u0001\bIJLg/\u001a:!\u00031!WMZ1vYR\u001cuN]3t!\r1\u0016QF\u0005\u0004\u0003_9&aA%oi\u00061A(\u001b8jiz\"b\"!\u000e\u0002:\u0005m\u0012QHA \u0003\u0003\n\u0019\u0005E\u0002\u00028\u0001i\u0011A\u0013\u0005\u0006Q2\u0001\rA\u001b\u0005\u0006]2\u0001\r\u0001\u001d\u0005\u0006s2\u0001\ra\u001f\u0005\b\u0003\u0003a\u0001\u0019AA\u0003\u0011\u001d\t9\u0002\u0004a\u0001\u00037Aq!!\u000b\r\u0001\u0004\tY#A\u0003ti\u0006$X-\u0006\u0002\u0002JA!\u00111JA)\u001d\u0011\t9$!\u0014\n\u0007\u0005=#*\u0001\tBaBd\u0017nY1uS>t7\u000b^1uK&!\u00111KA+\u0005\u00151\u0016\r\\;f\u0013\r\t9f\u0016\u0002\f\u000b:,X.\u001a:bi&|g.A\u0005ti\u0006$Xm\u0018\u0013fcR!\u0011QLA2!\r1\u0016qL\u0005\u0004\u0003C:&\u0001B+oSRD\u0011\"!\u001a\u000f\u0003\u0003\u0005\r!!\u0013\u0002\u0007a$\u0013'\u0001\u0004ti\u0006$X\r\t\u0015\u0004\u001f\u0005-\u0004c\u0001,\u0002n%\u0019\u0011qN,\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!C3yK\u000e,Ho\u001c:t+\t\t)\b\u0005\u0005\u0002x\u0005\u0005\u00151FAC\u001b\t\tIH\u0003\u0003\u0002|\u0005u\u0014aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003\u007f:\u0016AC2pY2,7\r^5p]&!\u00111QA=\u0005\u001dA\u0015m\u001d5NCB\u0004B!a\u000e\u0002\b&\u0019\u0011\u0011\u0012&\u0003\u0019\u0015CXmY;u_J$Um]2\u0002\u001b\u0015DXmY;u_J\u001cx\fJ3r)\u0011\ti&a$\t\u0013\u0005\u0015\u0014#!AA\u0002\u0005U\u0014AC3yK\u000e,Ho\u001c:tA!\u001a!#a\u001b\u0002!I,Wn\u001c<fI\u0016CXmY;u_J\u001cXCAAM!\u0019\t9(a'\u0002\u0006&!\u0011QTA=\u0005-\t%O]1z\u0005V4g-\u001a:\u0002)I,Wn\u001c<fI\u0016CXmY;u_J\u001cx\fJ3r)\u0011\ti&a)\t\u0013\u0005\u0015D#!AA\u0002\u0005e\u0015!\u0005:f[>4X\rZ#yK\u000e,Ho\u001c:tA!\u001aQ#a\u001b\u0002\u0019\r|'/Z:He\u0006tG/\u001a3\u0016\u0005\u0005-\u0012\u0001E2pe\u0016\u001cxI]1oi\u0016$w\fJ3r)\u0011\ti&!-\t\u0013\u0005\u0015t#!AA\u0002\u0005-\u0012!D2pe\u0016\u001cxI]1oi\u0016$\u0007\u0005K\u0002\u0019\u0003W\nq!\u001a8e)&lW-A\u0006f]\u0012$\u0016.\\3`I\u0015\fH\u0003BA/\u0003{C\u0001\"!\u001a\u001b\u0003\u0003\u0005\rA[\u0001\tK:$G+[7fA!\u001a1$a\u001b\u0002\u0013\u0005\u0004\boU8ve\u000e,WCAAd!\u0011\t9$!3\n\u0007\u0005-'JA\tBaBd\u0017nY1uS>t7k\\;sG\u0016\fQ\"\u00199q'>,(oY3`I\u0015\fH\u0003BA/\u0003#D\u0011\"!\u001a\u001e\u0003\u0003\u0005\r!a2\u0002\u0015\u0005\u0004\boU8ve\u000e,\u0007\u0005K\u0002\u001f\u0003W\nQ$\u001a=fGV$xN]:QKJ\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\nZ\u000b\u0003\u00037\u0004\u0002\"a\u001e\u0002\u0002\u0006-\u0012Q\u001c\t\u0007\u0003o\ny.a\u000b\n\t\u0005\u0005\u0018\u0011\u0010\u0002\u0004'\u0016$\u0018!I3yK\u000e,Ho\u001c:t!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133`I\u0015\fH\u0003BA/\u0003OD\u0011\"!\u001a!\u0003\u0003\u0005\r!a7\u0002=\u0015DXmY;u_J\u001c\b+\u001a:SKN|WO]2f!J|g-\u001b7f\u0013\u0012\u0004\u0003fA\u0011\u0002l\u00051C/\u0019:hKRtU/\\#yK\u000e,Ho\u001c:t!\u0016\u0014(+Z:pkJ\u001cW\r\u0015:pM&dW-\u00133\u0016\u0005\u0005E\b\u0003CA<\u0003\u0003\u000bY#a\u000b\u0002UQ\f'oZ3u\u001dVlW\t_3dkR|'o\u001d)feJ+7o\\;sG\u0016\u0004&o\u001c4jY\u0016LEm\u0018\u0013fcR!\u0011QLA|\u0011%\t)gIA\u0001\u0002\u0004\t\t0A\u0014uCJ<W\r\u001e(v[\u0016CXmY;u_J\u001c\b+\u001a:SKN|WO]2f!J|g-\u001b7f\u0013\u0012\u0004\u0003f\u0001\u0013\u0002l\u0005)\"\u000f]%e)>\u0014Vm]8ve\u000e,\u0007K]8gS2,WC\u0001B\u0001!!\t9(!!\u0002,\t\r\u0001\u0003\u0002B\u0003\u0005\u0017i!Aa\u0002\u000b\u0007\t%a*\u0001\u0005sKN|WO]2f\u0013\u0011\u0011iAa\u0002\u0003\u001fI+7o\\;sG\u0016\u0004&o\u001c4jY\u0016\f\u0011D\u001d9JIR{'+Z:pkJ\u001cW\r\u0015:pM&dWm\u0018\u0013fcR!\u0011Q\fB\n\u0011%\t)GJA\u0001\u0002\u0004\u0011\t!\u0001\fsa&#Gk\u001c*fg>,(oY3Qe>4\u0017\u000e\\3!Q\r9\u00131N\u0001\u0013eBLE\rV8SKN|WO]2f\t\u0016\u001c8-\u0006\u0002\u0003\u001eAA\u0011qOAA\u0003W\u0011y\u0002\u0005\u0003\u00028\t\u0005\u0012b\u0001B\u0012\u0015\nYR\t_3dkR|'OU3t_V\u00148-\u001a#fg\u000e\u0014\u0018\u000e\u001d;j_:\faC\u001d9JIR{'+Z:pkJ\u001cW\rR3tG~#S-\u001d\u000b\u0005\u0003;\u0012I\u0003C\u0005\u0002f%\n\t\u00111\u0001\u0003\u001e\u0005\u0019\"\u000f]%e)>\u0014Vm]8ve\u000e,G)Z:dA!\u001a!&a\u001b\u0002\u001d9,\u0007\u0010^#yK\u000e,Ho\u001c:JI\u0006\u0011b.\u001a=u\u000bb,7-\u001e;pe&#w\fJ3r)\u0011\tiF!\u000e\t\u0013\u0005\u0015D&!AA\u0002\u0005-\u0012a\u00048fqR,\u00050Z2vi>\u0014\u0018\n\u001a\u0011)\u00075\nY'\u0001\u0006sK\u0006$wJ\u00196fGR$B!!\u0018\u0003@!9!\u0011\t\u0018A\u0002\t\r\u0013AA5o!\u0011\u0011)Ea\u0013\u000e\u0005\t\u001d#\u0002\u0002B%\u0003\u001b\t!![8\n\t\t5#q\t\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0017\u0001B5oSR$\"!!\u0018\u00027\u001d,Go\u0014:Va\u0012\fG/Z#yK\u000e,Ho\u001c:t\r>\u0014(\u000bU%e)\u0011\tiNa\u0016\t\u000f\te\u0003\u00071\u0001\u0002,\u0005!!\u000f]%e\u0003m9W\r\u001e+be\u001e,G/\u0012=fGV$xN\u001d(v[\u001a{'O\u0015)JIR!\u00111\u0006B0\u0011\u001d\u0011I&\ra\u0001\u0003W\t\u0011cZ3u%\u0016\fX/Z:uK\u0012\u0014\u0006+\u00133t)\t\u0011)\u0007E\u0003]\u0005O\nY#C\u0002\u0003j\u001d\u00141aU3r\u0003\u0011\u001a'/Z1uKJ+7o\\;sG\u0016$Um]2G_J\u0014Vm]8ve\u000e,\u0007K]8gS2,G\u0003BA/\u0005_BqA!\u001d4\u0001\u0004\u0011\u0019!A\bsKN|WO]2f!J|g-\u001b7f\u0003u9W\r\u001e*fg>,(oY3EKN\u001c'/\u001b9uS>tgi\u001c:Sa&#G\u0003\u0002B\u0010\u0005oBqA!\u00175\u0001\u0004\tY#\u0001\tsKF,Xm\u001d;Fq\u0016\u001cW\u000f^8sgR!\u0011Q\fB?\u0011\u001d\u0011y(\u000ea\u0001\u0005\u0003\u000b1D]3t_V\u00148-\u001a)s_\u001aLG.\u001a+p)>$\u0018\r\\#yK\u000e\u001c\bcB9\u0003\u0004\n\r\u00111F\u0005\u0004\u0005\u000b;(aA'ba\u00061r-\u001a;SKN|WO]2f!J|g-\u001b7f\u0005fLE\r\u0006\u0003\u0003\u0004\t-\u0005b\u0002B-m\u0001\u0007\u00111F\u0001\u000e]\u0016<X\t_3dkR|'/\u00133\u0015\t\u0005-\"\u0011\u0013\u0005\n\u0005';\u0004\u0013!a\u0001\u0005+\u000bQ!^:f\u0013\u0012\u0003RA\u0016BL\u0003WI1A!'X\u0005\u0019y\u0005\u000f^5p]\u00069b.Z<Fq\u0016\u001cW\u000f^8s\u0013\u0012$C-\u001a4bk2$H%M\u000b\u0003\u0005?SCA!&\u0003\".\u0012!1\u0015\t\u0005\u0005K\u0013y+\u0004\u0002\u0003(*!!\u0011\u0016BV\u0003%)hn\u00195fG.,GMC\u0002\u0003.^\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0011\tLa*\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0006bI\u0012,\u00050Z2vi>\u0014HCDAC\u0005o\u0013\tM!2\u0003J\nU'q\u001b\u0005\b\u0005sK\u0004\u0019\u0001B^\u0003\u00199xN]6feB!\u0011q\u0007B_\u0013\r\u0011yL\u0013\u0002\u000b/>\u00148.\u001a:J]\u001a|\u0007b\u0002Bbs\u0001\u0007\u00111F\u0001\u0006G>\u0014Xm\u001d\u0005\b\u0005\u000fL\u0004\u0019AA\u0016\u0003!iW-\\8ss6\u0013\u0007b\u0002Bfs\u0001\u0007!QZ\u0001\ne\u0016\u001cx.\u001e:dKN\u0004b!\u001dBBa\n=\u0007\u0003\u0002B\u0003\u0005#LAAa5\u0003\b\t\u0019\"+Z:pkJ\u001cW-\u00138g_Jl\u0017\r^5p]\"9!\u0011L\u001dA\u0002\u0005-\u0002\"\u0003BJsA\u0005\t\u0019\u0001BK\u0003U\tG\rZ#yK\u000e,Ho\u001c:%I\u00164\u0017-\u001e7uIY\naB]3n_Z,W\t_3dkR|'\u000f\u0006\u0003\u0002^\t}\u0007b\u0002Bqw\u0001\u0007\u0011QQ\u0001\u0005Kb,7-\u0001\bsKF,Xm\u001d;fI\u000e{'/Z:\u0002\u001fI,\u0017/^3ti\u0016$7i\u001c:fg\u0002\n\u0011bY8sKNdUM\u001a;\u0002\u0017}\u0013X\r\u001e:z\u0007>,h\u000e^\u0001\u0010?J,GO]=D_VtGo\u0018\u0013fcR!\u0011Q\fBx\u0011%\t)\u0007QA\u0001\u0002\u0004\tY#\u0001\u0007`e\u0016$(/_\"pk:$\b%\u0001\u0006sKR\u0014\u0018pQ8v]R\f1#\u001b8de\u0016lWM\u001c;SKR\u0014\u0018pQ8v]R$\"!a\u000b\u0002\u001fI,7/\u001a;SKR\u0014\u0018pQ8v]R\fA\"\\1sW\u001aKg.[:iK\u0012$B!!\u0018\u0003\u0000\"91\u0011A#A\u0002\u0005%\u0013\u0001C3oIN#\u0018\r^3\u0002\u0015%\u001ch)\u001b8jg\",G-\u0006\u0002\u0004\bA\u0019ak!\u0003\n\u0007\r-qKA\u0004C_>dW-\u00198\u0002!\u001d,G/\u0012=fGV$xN\u001d'j[&$\u0018\u0001\u00033ve\u0006$\u0018n\u001c8"
)
public class ApplicationInfo implements Serializable {
   private final long startTime;
   private final String id;
   private final ApplicationDescription desc;
   private final Date submitDate;
   private final RpcEndpointRef driver;
   private final int defaultCores;
   private transient Enumeration.Value state;
   private transient HashMap executors;
   private transient ArrayBuffer removedExecutors;
   private transient int coresGranted;
   private transient long endTime;
   private transient ApplicationSource appSource;
   private transient HashMap executorsPerResourceProfileId;
   private transient HashMap targetNumExecutorsPerResourceProfileId;
   private transient HashMap rpIdToResourceProfile;
   private transient HashMap rpIdToResourceDesc;
   private transient int nextExecutorId;
   private final int requestedCores;
   private int _retryCount;

   public long startTime() {
      return this.startTime;
   }

   public String id() {
      return this.id;
   }

   public ApplicationDescription desc() {
      return this.desc;
   }

   public Date submitDate() {
      return this.submitDate;
   }

   public RpcEndpointRef driver() {
      return this.driver;
   }

   public Enumeration.Value state() {
      return this.state;
   }

   public void state_$eq(final Enumeration.Value x$1) {
      this.state = x$1;
   }

   public HashMap executors() {
      return this.executors;
   }

   public void executors_$eq(final HashMap x$1) {
      this.executors = x$1;
   }

   public ArrayBuffer removedExecutors() {
      return this.removedExecutors;
   }

   public void removedExecutors_$eq(final ArrayBuffer x$1) {
      this.removedExecutors = x$1;
   }

   public int coresGranted() {
      return this.coresGranted;
   }

   public void coresGranted_$eq(final int x$1) {
      this.coresGranted = x$1;
   }

   public long endTime() {
      return this.endTime;
   }

   public void endTime_$eq(final long x$1) {
      this.endTime = x$1;
   }

   public ApplicationSource appSource() {
      return this.appSource;
   }

   public void appSource_$eq(final ApplicationSource x$1) {
      this.appSource = x$1;
   }

   private HashMap executorsPerResourceProfileId() {
      return this.executorsPerResourceProfileId;
   }

   private void executorsPerResourceProfileId_$eq(final HashMap x$1) {
      this.executorsPerResourceProfileId = x$1;
   }

   private HashMap targetNumExecutorsPerResourceProfileId() {
      return this.targetNumExecutorsPerResourceProfileId;
   }

   private void targetNumExecutorsPerResourceProfileId_$eq(final HashMap x$1) {
      this.targetNumExecutorsPerResourceProfileId = x$1;
   }

   private HashMap rpIdToResourceProfile() {
      return this.rpIdToResourceProfile;
   }

   private void rpIdToResourceProfile_$eq(final HashMap x$1) {
      this.rpIdToResourceProfile = x$1;
   }

   private HashMap rpIdToResourceDesc() {
      return this.rpIdToResourceDesc;
   }

   private void rpIdToResourceDesc_$eq(final HashMap x$1) {
      this.rpIdToResourceDesc = x$1;
   }

   private int nextExecutorId() {
      return this.nextExecutorId;
   }

   private void nextExecutorId_$eq(final int x$1) {
      this.nextExecutorId = x$1;
   }

   private void readObject(final ObjectInputStream in) {
      Utils$.MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         in.defaultReadObject();
         this.init();
      });
   }

   private void init() {
      this.state_$eq(ApplicationState$.MODULE$.WAITING());
      this.executors_$eq(new HashMap());
      this.coresGranted_$eq(0);
      this.endTime_$eq(-1L);
      this.appSource_$eq(new ApplicationSource(this));
      this.nextExecutorId_$eq(0);
      this.removedExecutors_$eq(new ArrayBuffer());
      int initialExecutorLimit = BoxesRunTime.unboxToInt(this.desc().initialExecutorLimit().getOrElse((JFunction0.mcI.sp)() -> Integer.MAX_VALUE));
      this.rpIdToResourceProfile_$eq(new HashMap());
      this.rpIdToResourceProfile().update(BoxesRunTime.boxToInteger(ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), this.desc().defaultProfile());
      this.rpIdToResourceDesc_$eq(new HashMap());
      this.createResourceDescForResourceProfile(this.desc().defaultProfile());
      this.targetNumExecutorsPerResourceProfileId_$eq(new HashMap());
      this.targetNumExecutorsPerResourceProfileId().update(BoxesRunTime.boxToInteger(ResourceProfile$.MODULE$.DEFAULT_RESOURCE_PROFILE_ID()), BoxesRunTime.boxToInteger(initialExecutorLimit));
      this.executorsPerResourceProfileId_$eq(new HashMap());
   }

   public Set getOrUpdateExecutorsForRPId(final int rpId) {
      return (Set)this.executorsPerResourceProfileId().getOrElseUpdate(BoxesRunTime.boxToInteger(rpId), () -> (HashSet).MODULE$.apply(scala.collection.immutable.Nil..MODULE$));
   }

   public int getTargetExecutorNumForRPId(final int rpId) {
      return BoxesRunTime.unboxToInt(this.targetNumExecutorsPerResourceProfileId().getOrElse(BoxesRunTime.boxToInteger(rpId), (JFunction0.mcI.sp)() -> 0));
   }

   public Seq getRequestedRPIds() {
      return (Seq)this.rpIdToResourceProfile().keys().toSeq().sorted(scala.math.Ordering.Int..MODULE$);
   }

   private void createResourceDescForResourceProfile(final ResourceProfile resourceProfile) {
      if (!this.rpIdToResourceDesc().contains(BoxesRunTime.boxToInteger(resourceProfile.id()))) {
         int defaultMemoryMbPerExecutor = this.desc().memoryPerExecutorMB();
         Option defaultCoresPerExecutor = this.desc().coresPerExecutor();
         Option coresPerExecutor = resourceProfile.getExecutorCores().orElse(() -> defaultCoresPerExecutor);
         int memoryMbPerExecutor = BoxesRunTime.unboxToInt(resourceProfile.getExecutorMemory().map((JFunction1.mcIJ.sp)(x$12) -> (int)x$12).getOrElse((JFunction0.mcI.sp)() -> defaultMemoryMbPerExecutor));
         Seq customResources = ResourceUtils$.MODULE$.executorResourceRequestToRequirement((Seq)resourceProfile.getCustomExecutorResources().values().toSeq().sortBy((x$13) -> x$13.resourceName(), scala.math.Ordering.String..MODULE$));
         this.rpIdToResourceDesc().update(BoxesRunTime.boxToInteger(resourceProfile.id()), new ExecutorResourceDescription(coresPerExecutor, memoryMbPerExecutor, customResources));
      }
   }

   public ExecutorResourceDescription getResourceDescriptionForRpId(final int rpId) {
      return (ExecutorResourceDescription)this.rpIdToResourceDesc().apply(BoxesRunTime.boxToInteger(rpId));
   }

   public void requestExecutors(final Map resourceProfileToTotalExecs) {
      resourceProfileToTotalExecs.foreach((x0$1) -> {
         $anonfun$requestExecutors$1(this, x0$1);
         return BoxedUnit.UNIT;
      });
   }

   public ResourceProfile getResourceProfileById(final int rpId) {
      return (ResourceProfile)this.rpIdToResourceProfile().apply(BoxesRunTime.boxToInteger(rpId));
   }

   private int newExecutorId(final Option useID) {
      if (useID instanceof Some var4) {
         int id = BoxesRunTime.unboxToInt(var4.value());
         this.nextExecutorId_$eq(scala.math.package..MODULE$.max(this.nextExecutorId(), id + 1));
         return id;
      } else if (scala.None..MODULE$.equals(useID)) {
         int id = this.nextExecutorId();
         this.nextExecutorId_$eq(this.nextExecutorId() + 1);
         return id;
      } else {
         throw new MatchError(useID);
      }
   }

   private Option newExecutorId$default$1() {
      return scala.None..MODULE$;
   }

   public ExecutorDesc addExecutor(final WorkerInfo worker, final int cores, final int memoryMb, final Map resources, final int rpId, final Option useID) {
      ExecutorDesc exec = new ExecutorDesc(this.newExecutorId(useID), this, worker, cores, memoryMb, resources, rpId);
      this.executors().update(BoxesRunTime.boxToInteger(exec.id()), exec);
      this.getOrUpdateExecutorsForRPId(rpId).add(BoxesRunTime.boxToInteger(exec.id()));
      this.coresGranted_$eq(this.coresGranted() + cores);
      return exec;
   }

   public Option addExecutor$default$6() {
      return scala.None..MODULE$;
   }

   public void removeExecutor(final ExecutorDesc exec) {
      if (this.executors().contains(BoxesRunTime.boxToInteger(exec.id()))) {
         this.removedExecutors().$plus$eq(this.executors().apply(BoxesRunTime.boxToInteger(exec.id())));
         this.executors().$minus$eq(BoxesRunTime.boxToInteger(exec.id()));
         ((Shrinkable)this.executorsPerResourceProfileId().apply(BoxesRunTime.boxToInteger(exec.rpId()))).$minus$eq(BoxesRunTime.boxToInteger(exec.id()));
         this.coresGranted_$eq(this.coresGranted() - exec.cores());
      }
   }

   private int requestedCores() {
      return this.requestedCores;
   }

   public int coresLeft() {
      return this.requestedCores() - this.coresGranted();
   }

   private int _retryCount() {
      return this._retryCount;
   }

   private void _retryCount_$eq(final int x$1) {
      this._retryCount = x$1;
   }

   public int retryCount() {
      return this._retryCount();
   }

   public int incrementRetryCount() {
      this._retryCount_$eq(this._retryCount() + 1);
      return this._retryCount();
   }

   public void resetRetryCount() {
      this._retryCount_$eq(0);
   }

   public void markFinished(final Enumeration.Value endState) {
      this.state_$eq(endState);
      this.endTime_$eq(System.currentTimeMillis());
   }

   public boolean isFinished() {
      boolean var4;
      label32: {
         label24: {
            Enumeration.Value var10000 = this.state();
            Enumeration.Value var1 = ApplicationState$.MODULE$.WAITING();
            if (var10000 == null) {
               if (var1 == null) {
                  break label24;
               }
            } else if (var10000.equals(var1)) {
               break label24;
            }

            var10000 = this.state();
            Enumeration.Value var2 = ApplicationState$.MODULE$.RUNNING();
            if (var10000 == null) {
               if (var2 != null) {
                  break label32;
               }
            } else if (!var10000.equals(var2)) {
               break label32;
            }
         }

         var4 = false;
         return var4;
      }

      var4 = true;
      return var4;
   }

   public int getExecutorLimit() {
      return BoxesRunTime.unboxToInt(this.targetNumExecutorsPerResourceProfileId().values().sum(scala.math.Numeric.IntIsIntegral..MODULE$));
   }

   public long duration() {
      return this.endTime() != -1L ? this.endTime() - this.startTime() : System.currentTimeMillis() - this.startTime();
   }

   // $FF: synthetic method
   public static final void $anonfun$requestExecutors$1(final ApplicationInfo $this, final Tuple2 x0$1) {
      if (x0$1 != null) {
         ResourceProfile rp = (ResourceProfile)x0$1._1();
         int num = x0$1._2$mcI$sp();
         $this.createResourceDescForResourceProfile(rp);
         if (!$this.rpIdToResourceProfile().contains(BoxesRunTime.boxToInteger(rp.id()))) {
            $this.rpIdToResourceProfile().update(BoxesRunTime.boxToInteger(rp.id()), rp);
         }

         $this.targetNumExecutorsPerResourceProfileId().update(BoxesRunTime.boxToInteger(rp.id()), BoxesRunTime.boxToInteger(num));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   public ApplicationInfo(final long startTime, final String id, final ApplicationDescription desc, final Date submitDate, final RpcEndpointRef driver, final int defaultCores) {
      this.startTime = startTime;
      this.id = id;
      this.desc = desc;
      this.submitDate = submitDate;
      this.driver = driver;
      this.defaultCores = defaultCores;
      this.init();
      this.requestedCores = BoxesRunTime.unboxToInt(desc.maxCores().getOrElse((JFunction0.mcI.sp)() -> this.defaultCores));
      this._retryCount = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
