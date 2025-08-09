package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.math.BigInteger;
import org.apache.spark.QueryContext;
import org.apache.spark.SparkArithmeticException;
import org.apache.spark.annotation.Unstable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.apache.spark.sql.internal.SqlApiConf$;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.Fractional;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordered;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction1;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005\u00155aaBA\n\u0003+\u0011\u00111\u0006\u0005\b\u00037\u0002A\u0011AA/\u0011%\ty\u0006\u0001a\u0001\n\u0013\t\t\u0007C\u0005\u0002j\u0001\u0001\r\u0011\"\u0003\u0002l!A\u0011q\u000f\u0001!B\u0013\t\u0019\u0007C\u0005\u0002z\u0001\u0001\r\u0011\"\u0003\u0002|!I\u00111\u0011\u0001A\u0002\u0013%\u0011Q\u0011\u0005\t\u0003\u0013\u0003\u0001\u0015)\u0003\u0002~!I\u00111\u0012\u0001A\u0002\u0013%\u0011Q\u0012\u0005\n\u0003+\u0003\u0001\u0019!C\u0005\u0003/C\u0001\"a'\u0001A\u0003&\u0011q\u0012\u0005\n\u0003;\u0003\u0001\u0019!C\u0005\u0003\u001bC\u0011\"a(\u0001\u0001\u0004%I!!)\t\u0011\u0005\u0015\u0006\u0001)Q\u0005\u0003\u001fCq!a*\u0001\t\u0003\ti\tC\u0004\u0002*\u0002!\t!!$\t\u000f\u0005-\u0006\u0001\"\u0001\u0002.\"9\u00111\u0016\u0001\u0005\u0002\u0005E\u0006bBAV\u0001\u0011\u0005\u0011q\u0017\u0005\b\u0003\u0003\u0004A\u0011AAb\u0011\u001d\tY\u000b\u0001C\u0001\u0003\u0017Dq!a+\u0001\t\u0003\t)\u000eC\u0004\u0002,\u0002!\t!!7\t\u000f\u0005-\u0006\u0001\"\u0001\u0002p\"9\u00111\u001f\u0001\u0005\u0002\u0005\u0005\u0004bBA{\u0001\u0011\u0005\u0011q\u001f\u0005\b\u0003{\u0004A\u0011AA\u0000\u0011\u001d\u00119\u0001\u0001C\u0001\u0005\u0013AqAa\u0003\u0001\t\u0003\tY\bC\u0004\u0003\u000e\u0001!\tEa\u0004\t\u000f\t\u0005\u0002\u0001\"\u0001\u0003$!9!Q\u0005\u0001\u0005\u0002\t\r\u0002b\u0002B\u0014\u0001\u0011\u0005!\u0011\u0006\u0005\b\u0005c\u0001A\u0011\u0001B\u001a\u0011\u001d\u0011Y\u0004\u0001C\u0005\u0003wBqA!\u0010\u0001\t\u0003\tY\bC\u0004\u0003@\u0001!\t!!$\t\u000f\t\u0005\u0003\u0001\"\u0001\u0003D!9!1\n\u0001\u0005\u0002\t5\u0003\"\u0003B+\u0001\u0011\u0005\u0011\u0011\u0004B,\u0011%\u0011I\u0006\u0001C\u0001\u00033\u0011Y\u0006C\u0005\u0003^\u0001!\t!!\u0007\u0003`!9!\u0011\r\u0001\u0005\n\t\r\u0002b\u0002B2\u0001\u0011%!Q\r\u0005\n\u0005K\u0003A\u0011AA\r\u0005OCqA!+\u0001\t\u0003\u0011Y\u000bC\u0005\u00038\u0002!\t!!\u0007\u0003:\"Y!Q\u001e\u0001\u0012\u0002\u0013\u0005\u0011\u0011\u0004Bx\u0011-\u0019)\u0001AI\u0001\n\u0003\tIba\u0002\t\u0017\r-\u0001!%A\u0005\u0002\u0005e1Q\u0002\u0005\n\u0005S\u0003A\u0011AA\r\u0007#Aqa!\u0007\u0001\t\u0003\ni\u0006C\u0004\u0004\u001c\u0001!\te!\b\t\u000f\r\r\u0002\u0001\"\u0011\u0004&!91q\u0006\u0001\u0005B\t}\u0003bBB\u0019\u0001\u0011\u000511\u0007\u0005\b\u0007k\u0001A\u0011AB\u001c\u0011\u001d\u0019i\u0004\u0001C\u0001\u0007\u007fAqaa\u0011\u0001\t\u0003\u0019)\u0005C\u0004\u0004J\u0001!\taa\u0013\t\u000f\r=\u0003\u0001\"\u0001\u0004R!91Q\u000b\u0001\u0005\u0002\r]\u0003bBB.\u0001\u0011\u00051Q\f\u0005\b\u0007C\u0002A\u0011AB2\u0011\u001d\u0019)\u0007\u0001C\u0001\u0007GBqaa\u001a\u0001\t\u0003\u0019\u0019\u0007C\u0004\u0004j\u0001!\taa\u0019\b\u0011\r]\u0014Q\u0003E\u0001\u0007s2\u0001\"a\u0005\u0002\u0016!\u000511\u0010\u0005\b\u00037\"E\u0011ABD\u0011%\u0019I\t\u0012b\u0001\n\u0003\u0019Y\t\u0003\u0005\u0004\u000e\u0012\u0003\u000b\u0011\u0002Bb\u0011%\u0019y\t\u0012b\u0001\n\u0003\u0019Y\t\u0003\u0005\u0004\u0012\u0012\u0003\u000b\u0011\u0002Bb\u0011%\u0019\u0019\n\u0012b\u0001\n\u0003\u0019Y\t\u0003\u0005\u0004\u0016\u0012\u0003\u000b\u0011\u0002Bb\u0011%\u00199\n\u0012b\u0001\n\u0003\u0019Y\t\u0003\u0005\u0004\u001a\u0012\u0003\u000b\u0011\u0002Bb\u0011%\u0019Y\n\u0012b\u0001\n\u0003\ti\t\u0003\u0005\u0004\u001e\u0012\u0003\u000b\u0011BAH\u0011%\u0019y\n\u0012b\u0001\n\u0003\ti\t\u0003\u0005\u0004\"\u0012\u0003\u000b\u0011BAH\u0011%\u0019\u0019\u000b\u0012b\u0001\n\u0003\u0019)\u000b\u0003\u0005\u0004.\u0012\u0003\u000b\u0011BBT\u0011%\u0019y\u000b\u0012b\u0001\n\u0013\u0019\t\f\u0003\u0005\u0004:\u0012\u0003\u000b\u0011BBZ\u0011-\u0019Y\f\u0012b\u0001\n\u0003\tIba\u0019\t\u0011\ruF\t)A\u0005\u0003#B1ba0E\u0005\u0004%\t!!\u0007\u0004d!A1\u0011\u0019#!\u0002\u0013\t\t\u0006C\u0004\u0004D\u0012#\ta!2\t\u000f\r\rG\t\"\u0001\u0004L\"911\u0019#\u0005\u0002\r=\u0007bBBb\t\u0012\u000511\u001b\u0005\b\u0007\u0007$E\u0011ABl\u0011\u001d\u0019\u0019\r\u0012C\u0001\u00077Dqaa1E\t\u0003\u0019y\u000eC\u0004\u0004D\u0012#\ta!;\t\u000f\r\rG\t\"\u0001\u0004r\"911\u0019#\u0005\u0002\re\bbBBb\t\u0012\u0005A\u0011\u0001\u0005\b\t\u000b!E\u0011\u0001C\u0004\u0011\u001d!Y\u0001\u0012C\u0005\t\u001bAq\u0001b\u0005E\t\u0013!)\u0002C\u0004\u0005*\u0011#\t\u0001b\u000b\t\u000f\u0011=B\t\"\u0001\u00052!IA\u0011\t#\u0012\u0002\u0013\u0005A1\t\u0005\n\t\u000f\"\u0015\u0013!C\u0001\u0007\u001bAq\u0001\"\u0013E\t\u0003!Y\u0005C\u0004\u0005T\u0011#\t\u0001\"\u0016\t\u0015\u0011mC\t#b\u0001\n\u0003!i\u0006C\u0004\u0005b\u0011#I\u0001b\u0019\u0007\u0017\u0011\u001dD\t%A\u0002\u0002\u0005eA\u0011\u000e\u0005\b\t{\u0002H\u0011\u0001C@\u0011\u001d!\t\t\u001dC!\t\u0007Cq\u0001\"$q\t\u0003\"y\tC\u0004\u0005\u0016B$\t\u0005b&\t\u000f\u0011u\u0005\u000f\"\u0011\u0005 \"9!q\u00059\u0005B\u0011\r\u0006b\u0002B\u0019a\u0012\u0005Cq\u0015\u0005\b\u0005\u007f\u0001H\u0011\tCV\u0011\u001d\u0011i\u0004\u001dC!\t_Cq\u0001b-q\t\u0003\")\fC\u0004\u0004\u001cA$\t\u0005\"/\t\u000f\u0011}\u0006\u000f\"\u0011\u0005B\u001eIA1\u001a#\t\u0002\u0005eAQ\u001a\u0004\n\t#$\u0005\u0012AA\r\t'Dq!a\u0017\u007f\t\u0003!i\u000eC\u0004\u0005`z$\t\u0005\"9\t\u0013\u0011\u001dh0!A\u0005\n\u0011%x!\u0003Cv\t\"\u0005\u0011\u0011\u0004Cw\r%!y\u000f\u0012E\u0001\u00033!\t\u0010\u0003\u0005\u0002\\\u0005\u001dA\u0011\u0001C}\u0011!\u0019)&a\u0002\u0005B\u0011m\b\u0002CC\u0001\u0003\u000f!\t%b\u0001\t\u0015\u0011\u001d\u0018qAA\u0001\n\u0013!I\u000fC\u0005\u0005h\u0012\u000b\t\u0011\"\u0003\u0005j\n9A)Z2j[\u0006d'\u0002BA\f\u00033\tQ\u0001^=qKNTA!a\u0007\u0002\u001e\u0005\u00191/\u001d7\u000b\t\u0005}\u0011\u0011E\u0001\u0006gB\f'o\u001b\u0006\u0005\u0003G\t)#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0003\u0003O\t1a\u001c:h\u0007\u0001\u0019r\u0001AA\u0017\u0003s\t)\u0006\u0005\u0003\u00020\u0005URBAA\u0019\u0015\t\t\u0019$A\u0003tG\u0006d\u0017-\u0003\u0003\u00028\u0005E\"AB!osJ+g\r\u0005\u0004\u0002<\u0005-\u0013\u0011\u000b\b\u0005\u0003{\t9E\u0004\u0003\u0002@\u0005\u0015SBAA!\u0015\u0011\t\u0019%!\u000b\u0002\rq\u0012xn\u001c;?\u0013\t\t\u0019$\u0003\u0003\u0002J\u0005E\u0012a\u00029bG.\fw-Z\u0005\u0005\u0003\u001b\nyEA\u0004Pe\u0012,'/\u001a3\u000b\t\u0005%\u0013\u0011\u0007\t\u0004\u0003'\u0002QBAA\u000b!\u0011\tY$a\u0016\n\t\u0005e\u0013q\n\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005E\u0013A\u00033fG&l\u0017\r\u001c,bYV\u0011\u00111\r\t\u0005\u0003w\t)'\u0003\u0003\u0002h\u0005=#A\u0003\"jO\u0012+7-[7bY\u0006qA-Z2j[\u0006dg+\u00197`I\u0015\fH\u0003BA7\u0003g\u0002B!a\f\u0002p%!\u0011\u0011OA\u0019\u0005\u0011)f.\u001b;\t\u0013\u0005U4!!AA\u0002\u0005\r\u0014a\u0001=%c\u0005YA-Z2j[\u0006dg+\u00197!\u0003\u001dawN\\4WC2,\"!! \u0011\t\u0005=\u0012qP\u0005\u0005\u0003\u0003\u000b\tD\u0001\u0003M_:<\u0017a\u00037p]\u001e4\u0016\r\\0%KF$B!!\u001c\u0002\b\"I\u0011Q\u000f\u0004\u0002\u0002\u0003\u0007\u0011QP\u0001\tY>twMV1mA\u0005Qq\f\u001d:fG&\u001c\u0018n\u001c8\u0016\u0005\u0005=\u0005\u0003BA\u0018\u0003#KA!a%\u00022\t\u0019\u0011J\u001c;\u0002\u001d}\u0003(/Z2jg&|gn\u0018\u0013fcR!\u0011QNAM\u0011%\t)(CA\u0001\u0002\u0004\ty)A\u0006`aJ,7-[:j_:\u0004\u0013AB0tG\u0006dW-\u0001\u0006`g\u000e\fG.Z0%KF$B!!\u001c\u0002$\"I\u0011Q\u000f\u0007\u0002\u0002\u0003\u0007\u0011qR\u0001\b?N\u001c\u0017\r\\3!\u0003%\u0001(/Z2jg&|g.A\u0003tG\u0006dW-A\u0002tKR$B!!\u0015\u00020\"9\u0011\u0011\u0010\tA\u0002\u0005uD\u0003BA)\u0003gCq!!.\u0012\u0001\u0004\ty)\u0001\u0004j]R4\u0016\r\u001c\u000b\t\u0003#\nI,!0\u0002@\"9\u00111\u0018\nA\u0002\u0005u\u0014\u0001C;og\u000e\fG.\u001a3\t\u000f\u0005\u001d&\u00031\u0001\u0002\u0010\"9\u0011\u0011\u0016\nA\u0002\u0005=\u0015!C:fi>\u0013h*\u001e7m)!\t\t&!2\u0002H\u0006%\u0007bBA^'\u0001\u0007\u0011Q\u0010\u0005\b\u0003O\u001b\u0002\u0019AAH\u0011\u001d\tIk\u0005a\u0001\u0003\u001f#\u0002\"!\u0015\u0002N\u0006E\u00171\u001b\u0005\b\u0003\u001f$\u0002\u0019AA2\u0003\u001d!WmY5nC2Dq!a*\u0015\u0001\u0004\ty\tC\u0004\u0002*R\u0001\r!a$\u0015\t\u0005E\u0013q\u001b\u0005\b\u0003\u001f,\u0002\u0019AA2)\u0011\t\t&a7\t\u000f\u0005ug\u00031\u0001\u0002`\u0006I!-[4j]R4\u0018\r\u001c\t\u0005\u0003C\fY/\u0004\u0002\u0002d*!\u0011Q]At\u0003\u0011i\u0017\r\u001e5\u000b\u0005\u0005%\u0018\u0001\u00026bm\u0006LA!!<\u0002d\nQ!)[4J]R,w-\u001a:\u0015\t\u0005E\u0013\u0011\u001f\u0005\b\u0003\u001f<\u0002\u0019AA)\u00031!xNQ5h\t\u0016\u001c\u0017.\\1m\u0003A!xNS1wC\nKw\rR3dS6\fG.\u0006\u0002\u0002zB!\u0011\u0011]A~\u0013\u0011\t9'a9\u0002\u001bQ|7kY1mC\nKw-\u00138u+\t\u0011\t\u0001\u0005\u0003\u0002<\t\r\u0011\u0002\u0002B\u0003\u0003\u001f\u0012aAQ5h\u0013:$\u0018\u0001\u0005;p\u0015\u00064\u0018MQ5h\u0013:$XmZ3s+\t\ty.\u0001\bu_Vs7oY1mK\u0012duN\\4\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"A!\u0005\u0011\t\tM!1\u0004\b\u0005\u0005+\u00119\u0002\u0005\u0003\u0002@\u0005E\u0012\u0002\u0002B\r\u0003c\ta\u0001\u0015:fI\u00164\u0017\u0002\u0002B\u000f\u0005?\u0011aa\u0015;sS:<'\u0002\u0002B\r\u0003c\tQ\u0002^8QY\u0006Lgn\u0015;sS:<WC\u0001B\t\u00035!x\u000eR3ck\u001e\u001cFO]5oO\u0006AAo\u001c#pk\ndW-\u0006\u0002\u0003,A!\u0011q\u0006B\u0017\u0013\u0011\u0011y#!\r\u0003\r\u0011{WO\u00197f\u0003\u001d!xN\u00127pCR,\"A!\u000e\u0011\t\u0005=\"qG\u0005\u0005\u0005s\t\tDA\u0003GY>\fG/A\u0007bGR,\u0018\r\u001c'p]\u001e4\u0016\r\\\u0001\u0007i>duN\\4\u0002\u000bQ|\u0017J\u001c;\u0002\u000fQ|7\u000b[8siV\u0011!Q\t\t\u0005\u0003_\u00119%\u0003\u0003\u0003J\u0005E\"!B*i_J$\u0018A\u0002;p\u0005f$X-\u0006\u0002\u0003PA!\u0011q\u0006B)\u0013\u0011\u0011\u0019&!\r\u0003\t\tKH/Z\u0001\fe>,h\u000e\u001a+p\u0005f$X\r\u0006\u0002\u0003P\u0005a!o\\;oIR{7\u000b[8siR\u0011!QI\u0001\u000be>,h\u000e\u001a+p\u0013:$HCAAH\u0003)!xnU9m-\u0006dW/Z\u0001\u000fe>,h\u000e\u001a+p\u001dVlWM]5d+\u0011\u00119G!\u001d\u0015\u0011\t%$1\u0013BO\u0005C#BAa\u001b\u0003\u000eR!!Q\u000eBB!\u0011\u0011yG!\u001d\r\u0001\u00119!1O\u0016C\u0002\tU$!\u0001+\u0012\t\t]$Q\u0010\t\u0005\u0003_\u0011I(\u0003\u0003\u0003|\u0005E\"a\u0002(pi\"Lgn\u001a\t\u0005\u0003_\u0011y(\u0003\u0003\u0003\u0002\u0006E\"AB!osZ\u000bG\u000eC\u0004\u0003\u0006.\u0002\rAa\"\u0002\u0005\u0019\u0014\u0004\u0003CA\u0018\u0005\u0013\u0013YC!\u001c\n\t\t-\u0015\u0011\u0007\u0002\n\rVt7\r^5p]FBqAa$,\u0001\u0004\u0011\t*\u0001\u0002gcAA\u0011q\u0006BE\u0003{\u0012i\u0007C\u0004\u0003\u0016.\u0002\rAa&\u0002\u0019%tG/Z4sC2$\u0016\u0010]3\u0011\t\u0005M#\u0011T\u0005\u0005\u00057\u000b)B\u0001\u0007J]R,wM]1m)f\u0004X\rC\u0004\u0003 .\u0002\r!a$\u0002\u00115\f\u0007PV1mk\u0016DqAa),\u0001\u0004\ty)\u0001\u0005nS:4\u0016\r\\;f\u0003-\u0011x.\u001e8e)>duN\\4\u0015\u0005\u0005u\u0014aD2iC:<W\r\u0015:fG&\u001c\u0018n\u001c8\u0015\r\t5&1\u0017B[!\u0011\tyCa,\n\t\tE\u0016\u0011\u0007\u0002\b\u0005>|G.Z1o\u0011\u001d\t9+\fa\u0001\u0003\u001fCq!!+.\u0001\u0004\ty)A\u0006u_B\u0013XmY5tS>tG\u0003DA)\u0005w\u0013iLa0\u0003^\n\u0005\bbBAT]\u0001\u0007\u0011q\u0012\u0005\b\u0003Ss\u0003\u0019AAH\u0011%\u0011\tM\fI\u0001\u0002\u0004\u0011\u0019-A\u0005s_VtG-T8eKB!!Q\u0019Bk\u001d\u0011\u00119M!4\u000f\t\u0005m\"\u0011Z\u0005\u0005\u0005\u0017\fy%\u0001\u0006CS\u001e$UmY5nC2LAAa4\u0003R\u0006a!k\\;oI&tw-T8eK*!!1\u001aBj\u0015\u0011\t)/!\r\n\t\t]'\u0011\u001c\u0002\u0006-\u0006dW/Z\u0005\u0005\u00057\f\tDA\u0006F]VlWM]1uS>t\u0007\"\u0003Bp]A\u0005\t\u0019\u0001BW\u00039qW\u000f\u001c7P]>3XM\u001d4m_^D\u0011Ba9/!\u0003\u0005\rA!:\u0002\u000f\r|g\u000e^3yiB!!q\u001dBu\u001b\t\ti\"\u0003\u0003\u0003l\u0006u!\u0001D)vKJL8i\u001c8uKb$\u0018!\u0006;p!J,7-[:j_:$C-\u001a4bk2$HeM\u000b\u0003\u0005cTCAa1\u0003t.\u0012!Q\u001f\t\u0005\u0005o\u001c\t!\u0004\u0002\u0003z*!!1 B\u007f\u0003%)hn\u00195fG.,GM\u0003\u0003\u0003\u0000\u0006E\u0012AC1o]>$\u0018\r^5p]&!11\u0001B}\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0016i>\u0004&/Z2jg&|g\u000e\n3fM\u0006,H\u000e\u001e\u00135+\t\u0019IA\u000b\u0003\u0003.\nM\u0018!\u0006;p!J,7-[:j_:$C-\u001a4bk2$H%N\u000b\u0003\u0007\u001fQCA!:\u0003tRA!QVB\n\u0007+\u00199\u0002C\u0004\u0002(J\u0002\r!a$\t\u000f\u0005%&\u00071\u0001\u0002\u0010\"9!\u0011\u0019\u001aA\u0002\t\r\u0017!B2m_:,\u0017aB2p[B\f'/\u001a\u000b\u0005\u0003\u001f\u001by\u0002C\u0004\u0004\"Q\u0002\r!!\u0015\u0002\u000b=$\b.\u001a:\u0002\r\u0015\fX/\u00197t)\u0011\u0011ika\n\t\u000f\r\u0005R\u00071\u0001\u0004*A!\u0011qFB\u0016\u0013\u0011\u0019i#!\r\u0003\u0007\u0005s\u00170\u0001\u0005iCND7i\u001c3f\u0003\u0019I7OW3s_V\u0011!QV\u0001\u0006IAdWo\u001d\u000b\u0005\u0003#\u001aI\u0004C\u0004\u0004<a\u0002\r!!\u0015\u0002\tQD\u0017\r^\u0001\u0007I5Lg.^:\u0015\t\u0005E3\u0011\t\u0005\b\u0007wI\u0004\u0019AA)\u0003\u0019!C/[7fgR!\u0011\u0011KB$\u0011\u001d\u0019YD\u000fa\u0001\u0003#\nA\u0001\n3jmR!\u0011\u0011KB'\u0011\u001d\u0019Yd\u000fa\u0001\u0003#\n\u0001\u0002\n9fe\u000e,g\u000e\u001e\u000b\u0005\u0003#\u001a\u0019\u0006C\u0004\u0004<q\u0002\r!!\u0015\u0002\tE,x\u000e\u001e\u000b\u0005\u0003#\u001aI\u0006C\u0004\u0004<u\u0002\r!!\u0015\u0002\u0013I,W.Y5oI\u0016\u0014H\u0003BA)\u0007?Bqaa\u000f?\u0001\u0004\t\t&\u0001\u0007v]\u0006\u0014\u0018p\u0018\u0013nS:,8/\u0006\u0002\u0002R\u0005\u0019\u0011MY:\u0002\u000b\u0019dwn\u001c:\u0002\t\r,\u0017\u000e\u001c\u0015\u0004\u0001\r5\u0004\u0003BB8\u0007gj!a!\u001d\u000b\t\t}\u0018QD\u0005\u0005\u0007k\u001a\tH\u0001\u0005V]N$\u0018M\u00197f\u0003\u001d!UmY5nC2\u00042!a\u0015E'\u0015!\u0015QFB?!\u0011\u0019yh!\"\u000e\u0005\r\u0005%\u0002BBB\u0003O\f!![8\n\t\u0005e3\u0011\u0011\u000b\u0003\u0007s\nQBU(V\u001d\u0012{\u0006*\u0011'G?V\u0003VC\u0001Bb\u00039\u0011v*\u0016(E?\"\u000bEJR0V!\u0002\nqBU(V\u001d\u0012{\u0006*\u0011'G?\u00163VIT\u0001\u0011%>+f\nR0I\u000323u,\u0012,F\u001d\u0002\nQBU(V\u001d\u0012{6)R%M\u0013:;\u0015A\u0004*P+:#ulQ#J\u0019&su\tI\u0001\f%>+f\nR0G\u0019>{%+\u0001\u0007S\u001fVsEi\u0018$M\u001f>\u0013\u0006%\u0001\bN\u0003b{\u0016J\u0014+`\t&;\u0015\nV*\u0002\u001f5\u000b\u0005lX%O)~#\u0015jR%U'\u0002\nq\"T!Y?2{ejR0E\u0013\u001eKEkU\u0001\u0011\u001b\u0006Cv\fT(O\u000f~#\u0015jR%U'\u0002\na\u0001U(X?F\u0002TCABT!\u0019\tyc!+\u0002~%!11VA\u0019\u0005\u0015\t%O]1z\u0003\u001d\u0001vjV02a\u0001\nA\"T!U\u0011~\u001buJ\u0014+F1R+\"aa-\u0011\t\u0005\u00058QW\u0005\u0005\u0007o\u000b\u0019OA\u0006NCRD7i\u001c8uKb$\u0018!D'B)\"{6i\u0014(U\u000bb#\u0006%\u0001\u0003[\u000bJ{\u0015!\u0002.F%>\u0003\u0013aA(O\u000b\u0006!qJT#!\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t\tfa2\t\u000f\r%'\f1\u0001\u0003,\u0005)a/\u00197vKR!\u0011\u0011KBg\u0011\u001d\u0019Im\u0017a\u0001\u0003{\"B!!\u0015\u0004R\"91\u0011\u001a/A\u0002\u0005=E\u0003BA)\u0007+Dqa!3^\u0001\u0004\t\u0019\u0007\u0006\u0003\u0002R\re\u0007bBBe=\u0002\u0007\u0011\u0011 \u000b\u0005\u0003#\u001ai\u000eC\u0004\u0004J~\u0003\r!a8\u0015\t\u0005E3\u0011\u001d\u0005\b\u0007\u0013\u0004\u0007\u0019ABr!\u0011\u0019)oa:\u000e\u0005\tM\u0017\u0002\u0002B\u0003\u0005'$\u0002\"!\u0015\u0004l\u000e58q\u001e\u0005\b\u0007\u0013\f\u0007\u0019AA2\u0011\u001d\t9+\u0019a\u0001\u0003\u001fCq!!+b\u0001\u0004\ty\t\u0006\u0005\u0002R\rM8Q_B|\u0011\u001d\u0019IM\u0019a\u0001\u0003sDq!a*c\u0001\u0004\ty\tC\u0004\u0002*\n\u0004\r!a$\u0015\u0011\u0005E31`B\u007f\u0007\u007fDq!a/d\u0001\u0004\ti\bC\u0004\u0002(\u000e\u0004\r!a$\t\u000f\u0005%6\r1\u0001\u0002\u0010R!\u0011\u0011\u000bC\u0002\u0011\u001d\u0019I\r\u001aa\u0001\u0005#\t1B\u001a:p[\u0012+7-[7bYR!\u0011\u0011\u000bC\u0005\u0011\u001d\u0019I-\u001aa\u0001\u0007S\tqC\\;n\t&<\u0017\u000e^:J]&sG/Z4sC2\u0004\u0016M\u001d;\u0015\t\u0005=Eq\u0002\u0005\b\t#1\u0007\u0019AA}\u0003)\u0011\u0017n\u001a#fG&l\u0017\r\\\u0001\u0017gR\u0014\u0018N\\4U_*\u000bg/\u0019\"jO\u0012+7-[7bYR!\u0011\u0011 C\f\u0011\u001d!Ib\u001aa\u0001\t7\t1a\u001d;s!\u0011!i\u0002\"\n\u000e\u0005\u0011}!\u0002BA\f\tCQA\u0001b\t\u0002\u001e\u00051QO\\:bM\u0016LA\u0001b\n\u0005 \tQQ\u000b\u0016$9'R\u0014\u0018N\\4\u0002\u0015\u0019\u0014x.\\*ue&tw\r\u0006\u0003\u0002R\u00115\u0002b\u0002C\rQ\u0002\u0007A1D\u0001\u000fMJ|Wn\u0015;sS:<\u0017IT*J)!\t\t\u0006b\r\u00056\u0011}\u0002b\u0002C\rS\u0002\u0007A1\u0004\u0005\n\toI\u0007\u0013!a\u0001\ts\t!\u0001^8\u0011\t\u0005MC1H\u0005\u0005\t{\t)BA\u0006EK\u000eLW.\u00197UsB,\u0007\"\u0003BrSB\u0005\t\u0019\u0001Bs\u0003a1'o\\7TiJLgnZ!O'&#C-\u001a4bk2$HEM\u000b\u0003\t\u000bRC\u0001\"\u000f\u0003t\u0006AbM]8n'R\u0014\u0018N\\4B\u001dNKE\u0005Z3gCVdG\u000fJ\u001a\u0002\u0019\r\u0014X-\u0019;f+:\u001c\u0018MZ3\u0015\u0011\u0005ECQ\nC(\t#Bq!a/m\u0001\u0004\ti\bC\u0004\u0002(2\u0004\r!a$\t\u000f\u0005%F\u000e1\u0001\u0002\u0010\u0006!R.\u0019=Qe\u0016\u001c\u0017n]5p]\u001a{'OQ=uKN$B!a$\u0005X!9A\u0011L7A\u0002\u0005=\u0015\u0001\u00038v[\nKH/Z:\u0002)5LgNQ=uKN4uN\u001d)sK\u000eL7/[8o+\t!y\u0006\u0005\u0004\u00020\r%\u0016qR\u0001\u001cG>l\u0007/\u001e;f\u001b&t')\u001f;fg\u001a{'\u000f\u0015:fG&\u001c\u0018n\u001c8\u0015\t\u0005=EQ\r\u0005\b\u0003O{\u0007\u0019AAH\u0005M!UmY5nC2L5oQ8oM2L7\r^3e'\u0015\u0001H1\u000eC<!\u0011!i\u0007b\u001d\u000e\u0005\u0011=$\u0002\u0002C9\u0003O\fA\u0001\\1oO&!AQ\u000fC8\u0005\u0019y%M[3diB1\u00111\bC=\u0003#JA\u0001b\u001f\u0002P\t9a*^7fe&\u001c\u0017A\u0002\u0013j]&$H\u0005\u0006\u0002\u0002n\u0005!\u0001\u000f\\;t)\u0019\t\t\u0006\"\"\u0005\n\"9Aq\u0011:A\u0002\u0005E\u0013!\u0001=\t\u000f\u0011-%\u000f1\u0001\u0002R\u0005\t\u00110A\u0003uS6,7\u000f\u0006\u0004\u0002R\u0011EE1\u0013\u0005\b\t\u000f\u001b\b\u0019AA)\u0011\u001d!Yi\u001da\u0001\u0003#\nQ!\\5okN$b!!\u0015\u0005\u001a\u0012m\u0005b\u0002CDi\u0002\u0007\u0011\u0011\u000b\u0005\b\t\u0017#\b\u0019AA)\u0003\u0019qWmZ1uKR!\u0011\u0011\u000bCQ\u0011\u001d!9)\u001ea\u0001\u0003#\"BAa\u000b\u0005&\"9Aq\u0011<A\u0002\u0005EC\u0003\u0002B\u001b\tSCq\u0001b\"x\u0001\u0004\t\t\u0006\u0006\u0003\u0002\u0010\u00125\u0006b\u0002CDq\u0002\u0007\u0011\u0011\u000b\u000b\u0005\u0003{\"\t\fC\u0004\u0005\bf\u0004\r!!\u0015\u0002\u000f\u0019\u0014x.\\%oiR!\u0011\u0011\u000bC\\\u0011\u001d!9I\u001fa\u0001\u0003\u001f#b!a$\u0005<\u0012u\u0006b\u0002CDw\u0002\u0007\u0011\u0011\u000b\u0005\b\t\u0017[\b\u0019AA)\u0003-\u0001\u0018M]:f'R\u0014\u0018N\\4\u0015\t\u0011\rG\u0011\u001a\t\u0007\u0003_!)-!\u0015\n\t\u0011\u001d\u0017\u0011\u0007\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0011eA\u00101\u0001\u0003\u0012\u0005\u0019B)Z2j[\u0006d\u0017j\u001d$sC\u000e$\u0018n\u001c8bYB\u0019Aq\u001a@\u000e\u0003\u0011\u00131\u0003R3dS6\fG.S:Ge\u0006\u001cG/[8oC2\u001crA C6\t+$9\u000eE\u0002\u0005PB\u0004b!a\u000f\u0005Z\u0006E\u0013\u0002\u0002Cn\u0003\u001f\u0012!B\u0012:bGRLwN\\1m)\t!i-A\u0002eSZ$b!!\u0015\u0005d\u0012\u0015\b\u0002\u0003CD\u0003\u0003\u0001\r!!\u0015\t\u0011\u0011-\u0015\u0011\u0001a\u0001\u0003#\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"\u0001b\u001b\u0002'\u0011+7-[7bY\u0006\u001b\u0018JZ%oi\u0016<'/\u00197\u0011\t\u0011=\u0017q\u0001\u0002\u0014\t\u0016\u001c\u0017.\\1m\u0003NLe-\u00138uK\u001e\u0014\u0018\r\\\n\t\u0003\u000f!Y\u0007\"6\u0005tB1\u00111\bC{\u0003#JA\u0001b>\u0002P\tA\u0011J\u001c;fOJ\fG\u000e\u0006\u0002\u0005nR1\u0011\u0011\u000bC\u007f\t\u007fD\u0001\u0002b\"\u0002\f\u0001\u0007\u0011\u0011\u000b\u0005\t\t\u0017\u000bY\u00011\u0001\u0002R\u0005\u0019!/Z7\u0015\r\u0005ESQAC\u0004\u0011!!9)!\u0004A\u0002\u0005E\u0003\u0002\u0003CF\u0003\u001b\u0001\r!!\u0015)\u0007\u0011\u001bi\u0007K\u0002D\u0007[\u0002"
)
public final class Decimal implements Ordered, Serializable {
   private BigDecimal decimalVal;
   private long org$apache$spark$sql$types$Decimal$$longVal;
   private int org$apache$spark$sql$types$Decimal$$_precision;
   private int org$apache$spark$sql$types$Decimal$$_scale;

   public static int[] minBytesForPrecision() {
      return Decimal$.MODULE$.minBytesForPrecision();
   }

   public static int maxPrecisionForBytes(final int numBytes) {
      return Decimal$.MODULE$.maxPrecisionForBytes(numBytes);
   }

   public static Decimal createUnsafe(final long unscaled, final int precision, final int scale) {
      return Decimal$.MODULE$.createUnsafe(unscaled, precision, scale);
   }

   public static QueryContext fromStringANSI$default$3() {
      return Decimal$.MODULE$.fromStringANSI$default$3();
   }

   public static DecimalType fromStringANSI$default$2() {
      return Decimal$.MODULE$.fromStringANSI$default$2();
   }

   public static Decimal fromStringANSI(final UTF8String str, final DecimalType to, final QueryContext context) {
      return Decimal$.MODULE$.fromStringANSI(str, to, context);
   }

   public static Decimal fromString(final UTF8String str) {
      return Decimal$.MODULE$.fromString(str);
   }

   public static Decimal fromDecimal(final Object value) {
      return Decimal$.MODULE$.fromDecimal(value);
   }

   public static Decimal apply(final String value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final long unscaled, final int precision, final int scale) {
      return Decimal$.MODULE$.apply(unscaled, precision, scale);
   }

   public static Decimal apply(final java.math.BigDecimal value, final int precision, final int scale) {
      return Decimal$.MODULE$.apply(value, precision, scale);
   }

   public static Decimal apply(final BigDecimal value, final int precision, final int scale) {
      return Decimal$.MODULE$.apply(value, precision, scale);
   }

   public static Decimal apply(final BigInt value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final BigInteger value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final java.math.BigDecimal value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final BigDecimal value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final int value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final long value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static Decimal apply(final double value) {
      return Decimal$.MODULE$.apply(value);
   }

   public static long[] POW_10() {
      return Decimal$.MODULE$.POW_10();
   }

   public static int MAX_LONG_DIGITS() {
      return Decimal$.MODULE$.MAX_LONG_DIGITS();
   }

   public static int MAX_INT_DIGITS() {
      return Decimal$.MODULE$.MAX_INT_DIGITS();
   }

   public static Enumeration.Value ROUND_FLOOR() {
      return Decimal$.MODULE$.ROUND_FLOOR();
   }

   public static Enumeration.Value ROUND_CEILING() {
      return Decimal$.MODULE$.ROUND_CEILING();
   }

   public static Enumeration.Value ROUND_HALF_EVEN() {
      return Decimal$.MODULE$.ROUND_HALF_EVEN();
   }

   public static Enumeration.Value ROUND_HALF_UP() {
      return Decimal$.MODULE$.ROUND_HALF_UP();
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   private BigDecimal decimalVal() {
      return this.decimalVal;
   }

   private void decimalVal_$eq(final BigDecimal x$1) {
      this.decimalVal = x$1;
   }

   private long longVal() {
      return this.org$apache$spark$sql$types$Decimal$$longVal;
   }

   public void org$apache$spark$sql$types$Decimal$$longVal_$eq(final long x$1) {
      this.org$apache$spark$sql$types$Decimal$$longVal = x$1;
   }

   private int _precision() {
      return this.org$apache$spark$sql$types$Decimal$$_precision;
   }

   public void org$apache$spark$sql$types$Decimal$$_precision_$eq(final int x$1) {
      this.org$apache$spark$sql$types$Decimal$$_precision = x$1;
   }

   private int _scale() {
      return this.org$apache$spark$sql$types$Decimal$$_scale;
   }

   public void org$apache$spark$sql$types$Decimal$$_scale_$eq(final int x$1) {
      this.org$apache$spark$sql$types$Decimal$$_scale = x$1;
   }

   public int precision() {
      return this._precision();
   }

   public int scale() {
      return this._scale();
   }

   public Decimal set(final long longVal) {
      if (longVal > -Decimal$.MODULE$.POW_10()[Decimal$.MODULE$.MAX_LONG_DIGITS()] && longVal < Decimal$.MODULE$.POW_10()[Decimal$.MODULE$.MAX_LONG_DIGITS()]) {
         this.decimalVal_$eq((BigDecimal)null);
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(longVal);
      } else {
         this.decimalVal_$eq(.MODULE$.BigDecimal().apply(longVal));
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(0L);
      }

      this.org$apache$spark$sql$types$Decimal$$_precision_$eq(20);
      this.org$apache$spark$sql$types$Decimal$$_scale_$eq(0);
      return this;
   }

   public Decimal set(final int intVal) {
      this.decimalVal_$eq((BigDecimal)null);
      this.org$apache$spark$sql$types$Decimal$$longVal_$eq((long)intVal);
      this.org$apache$spark$sql$types$Decimal$$_precision_$eq(10);
      this.org$apache$spark$sql$types$Decimal$$_scale_$eq(0);
      return this;
   }

   public Decimal set(final long unscaled, final int precision, final int scale) {
      if (this.setOrNull(unscaled, precision, scale) == null) {
         throw DataTypeErrors$.MODULE$.unscaledValueTooLargeForPrecisionError(this, precision, scale, DataTypeErrors$.MODULE$.unscaledValueTooLargeForPrecisionError$default$4());
      } else {
         return this;
      }
   }

   public Decimal setOrNull(final long unscaled, final int precision, final int scale) {
      DecimalType$.MODULE$.checkNegativeScale(scale);
      if (unscaled > -Decimal$.MODULE$.POW_10()[Decimal$.MODULE$.MAX_LONG_DIGITS()] && unscaled < Decimal$.MODULE$.POW_10()[Decimal$.MODULE$.MAX_LONG_DIGITS()]) {
         long p = Decimal$.MODULE$.POW_10()[scala.math.package..MODULE$.min(precision, Decimal$.MODULE$.MAX_LONG_DIGITS())];
         if (unscaled <= -p || unscaled >= p) {
            return null;
         }

         this.decimalVal_$eq((BigDecimal)null);
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(unscaled);
      } else {
         if (precision < 19) {
            return null;
         }

         this.decimalVal_$eq(.MODULE$.BigDecimal().apply(unscaled, scale));
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(0L);
      }

      this.org$apache$spark$sql$types$Decimal$$_precision_$eq(precision);
      this.org$apache$spark$sql$types$Decimal$$_scale_$eq(scale);
      return this;
   }

   public Decimal set(final BigDecimal decimal, final int precision, final int scale) {
      DecimalType$.MODULE$.checkNegativeScale(scale);
      this.decimalVal_$eq(decimal.setScale(scale, Decimal$.MODULE$.ROUND_HALF_UP()));
      if (this.decimalVal().precision() > precision) {
         throw new SparkArithmeticException("NUMERIC_VALUE_OUT_OF_RANGE.WITHOUT_SUGGESTION", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("roundedValue"), this.decimalVal().toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("originalValue"), decimal.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("precision"), Integer.toString(precision)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("scale"), Integer.toString(scale))}))), (QueryContext[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)));
      } else {
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(0L);
         this.org$apache$spark$sql$types$Decimal$$_precision_$eq(precision);
         this.org$apache$spark$sql$types$Decimal$$_scale_$eq(scale);
         return this;
      }
   }

   public Decimal set(final BigDecimal decimal) {
      this.decimalVal_$eq(decimal);
      this.org$apache$spark$sql$types$Decimal$$longVal_$eq(0L);
      if (decimal.precision() < decimal.scale()) {
         this.org$apache$spark$sql$types$Decimal$$_precision_$eq(decimal.scale());
         this.org$apache$spark$sql$types$Decimal$$_scale_$eq(decimal.scale());
      } else if (decimal.scale() < 0 && !SqlApiConf$.MODULE$.get().allowNegativeScaleOfDecimalEnabled()) {
         this.org$apache$spark$sql$types$Decimal$$_precision_$eq(decimal.precision() - decimal.scale());
         this.org$apache$spark$sql$types$Decimal$$_scale_$eq(0);
         this.decimalVal_$eq(decimal.setScale(0));
      } else {
         this.org$apache$spark$sql$types$Decimal$$_precision_$eq(decimal.precision());
         this.org$apache$spark$sql$types$Decimal$$_scale_$eq(decimal.scale());
      }

      return this;
   }

   public Decimal set(final BigInteger bigintval) {
      Decimal var10000;
      try {
         this.decimalVal_$eq((BigDecimal)null);
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(bigintval.longValueExact());
         this.org$apache$spark$sql$types$Decimal$$_precision_$eq(DecimalType$.MODULE$.MAX_PRECISION());
         this.org$apache$spark$sql$types$Decimal$$_scale_$eq(0);
         var10000 = this;
      } catch (ArithmeticException var2) {
         var10000 = this.set(.MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(bigintval)));
      }

      return var10000;
   }

   public Decimal set(final Decimal decimal) {
      this.decimalVal_$eq(decimal.decimalVal());
      this.org$apache$spark$sql$types$Decimal$$longVal_$eq(decimal.longVal());
      this.org$apache$spark$sql$types$Decimal$$_precision_$eq(decimal._precision());
      this.org$apache$spark$sql$types$Decimal$$_scale_$eq(decimal._scale());
      return this;
   }

   public BigDecimal toBigDecimal() {
      return this.decimalVal() != null ? this.decimalVal() : .MODULE$.BigDecimal().apply(this.longVal(), this._scale());
   }

   public java.math.BigDecimal toJavaBigDecimal() {
      return this.decimalVal() != null ? this.decimalVal().underlying() : java.math.BigDecimal.valueOf(this.longVal(), this._scale());
   }

   public BigInt toScalaBigInt() {
      return this.decimalVal() != null ? this.decimalVal().toBigInt() : .MODULE$.BigInt().apply(this.actualLongVal());
   }

   public BigInteger toJavaBigInteger() {
      return this.decimalVal() != null ? this.decimalVal().underlying().toBigInteger() : BigInteger.valueOf(this.actualLongVal());
   }

   public long toUnscaledLong() {
      return this.decimalVal() != null ? this.decimalVal().underlying().unscaledValue().longValueExact() : this.longVal();
   }

   public String toString() {
      return this.toBigDecimal().toString();
   }

   public String toPlainString() {
      return this.toJavaBigDecimal().toPlainString();
   }

   public String toDebugString() {
      if (this.decimalVal() != null) {
         BigDecimal var1 = this.decimalVal();
         return "Decimal(expanded, " + var1 + ", " + this.precision() + ", " + this.scale() + ")";
      } else {
         long var10000 = this.longVal();
         return "Decimal(compact, " + var10000 + ", " + this.precision() + ", " + this.scale() + ")";
      }
   }

   public double toDouble() {
      return this.toBigDecimal().doubleValue();
   }

   public float toFloat() {
      return this.toBigDecimal().floatValue();
   }

   private long actualLongVal() {
      return this.longVal() / Decimal$.MODULE$.POW_10()[this._scale()];
   }

   public long toLong() {
      return this.decimalVal() == null ? this.actualLongVal() : this.decimalVal().longValue();
   }

   public int toInt() {
      return (int)this.toLong();
   }

   public short toShort() {
      return (short)((int)this.toLong());
   }

   public byte toByte() {
      return (byte)((int)this.toLong());
   }

   public byte roundToByte() {
      return BoxesRunTime.unboxToByte(this.roundToNumeric(ByteType$.MODULE$, 127, -128, (x$1) -> BoxesRunTime.boxToByte($anonfun$roundToByte$1(BoxesRunTime.unboxToLong(x$1))), (x$2) -> BoxesRunTime.boxToByte($anonfun$roundToByte$2(BoxesRunTime.unboxToDouble(x$2)))));
   }

   public short roundToShort() {
      return BoxesRunTime.unboxToShort(this.roundToNumeric(ShortType$.MODULE$, 32767, -32768, (x$3) -> BoxesRunTime.boxToShort($anonfun$roundToShort$1(BoxesRunTime.unboxToLong(x$3))), (x$4) -> BoxesRunTime.boxToShort($anonfun$roundToShort$2(BoxesRunTime.unboxToDouble(x$4)))));
   }

   public int roundToInt() {
      return BoxesRunTime.unboxToInt(this.roundToNumeric(IntegerType$.MODULE$, Integer.MAX_VALUE, Integer.MIN_VALUE, (JFunction1.mcIJ.sp)(x$5) -> (int)x$5, (JFunction1.mcID.sp)(x$6) -> (int)x$6));
   }

   private String toSqlValue() {
      return this.toString() + "BD";
   }

   private Object roundToNumeric(final IntegralType integralType, final int maxValue, final int minValue, final Function1 f1, final Function1 f2) {
      if (this.decimalVal() == null) {
         Object numericVal = f1.apply(BoxesRunTime.boxToLong(this.actualLongVal()));
         if (BoxesRunTime.equals(BoxesRunTime.boxToLong(this.actualLongVal()), numericVal)) {
            return numericVal;
         } else {
            throw DataTypeErrors$.MODULE$.castingCauseOverflowError(this.toSqlValue(), new DecimalType(this.precision(), this.scale()), integralType);
         }
      } else {
         double doubleVal = this.decimalVal().toDouble();
         if (Math.floor(doubleVal) <= (double)maxValue && Math.ceil(doubleVal) >= (double)minValue) {
            return f2.apply(BoxesRunTime.boxToDouble(doubleVal));
         } else {
            throw DataTypeErrors$.MODULE$.castingCauseOverflowError(this.toSqlValue(), new DecimalType(this.precision(), this.scale()), integralType);
         }
      }
   }

   public long roundToLong() {
      if (this.decimalVal() == null) {
         return this.actualLongVal();
      } else {
         try {
            return this.decimalVal().bigDecimal().toBigInteger().longValueExact();
         } catch (ArithmeticException var1) {
            throw DataTypeErrors$.MODULE$.castingCauseOverflowError(this.toSqlValue(), new DecimalType(this.precision(), this.scale()), LongType$.MODULE$);
         }
      }
   }

   public boolean changePrecision(final int precision, final int scale) {
      return this.changePrecision(precision, scale, Decimal$.MODULE$.ROUND_HALF_UP());
   }

   public Decimal toPrecision(final int precision, final int scale, final Enumeration.Value roundMode, final boolean nullOnOverflow, final QueryContext context) {
      Decimal copy = this.clone();
      if (copy.changePrecision(precision, scale, roundMode)) {
         return copy;
      } else if (nullOnOverflow) {
         return null;
      } else {
         throw DataTypeErrors$.MODULE$.cannotChangeDecimalPrecisionError(this, precision, scale, context);
      }
   }

   public Enumeration.Value toPrecision$default$3() {
      return Decimal$.MODULE$.ROUND_HALF_UP();
   }

   public boolean toPrecision$default$4() {
      return true;
   }

   public QueryContext toPrecision$default$5() {
      return null;
   }

   public boolean changePrecision(final int precision, final int scale, final Enumeration.Value roundMode) {
      if (precision == this.precision() && scale == this.scale()) {
         return true;
      } else {
         DecimalType$.MODULE$.checkNegativeScale(scale);
         long lv = this.longVal();
         BigDecimal dv = this.decimalVal();
         if (dv == null) {
            if (scale < this._scale()) {
               int diff = this._scale() - scale;
               if (diff > Decimal$.MODULE$.MAX_LONG_DIGITS()) {
                  long var49;
                  label164: {
                     label207: {
                        Enumeration.Value var44 = Decimal$.MODULE$.ROUND_FLOOR();
                        if (var44 == null) {
                           if (roundMode == null) {
                              break label207;
                           }
                        } else if (var44.equals(roundMode)) {
                           break label207;
                        }

                        label208: {
                           var44 = Decimal$.MODULE$.ROUND_CEILING();
                           if (var44 == null) {
                              if (roundMode == null) {
                                 break label208;
                              }
                           } else if (var44.equals(roundMode)) {
                              break label208;
                           }

                           label150: {
                              label209: {
                                 var44 = Decimal$.MODULE$.ROUND_HALF_UP();
                                 if (var44 == null) {
                                    if (roundMode == null) {
                                       break label209;
                                    }
                                 } else if (var44.equals(roundMode)) {
                                    break label209;
                                 }

                                 label142: {
                                    var44 = Decimal$.MODULE$.ROUND_HALF_EVEN();
                                    if (var44 == null) {
                                       if (roundMode == null) {
                                          break label142;
                                       }
                                    } else if (var44.equals(roundMode)) {
                                       break label142;
                                    }

                                    var48 = false;
                                    break label150;
                                 }

                                 var48 = true;
                                 break label150;
                              }

                              var48 = true;
                           }

                           if (!var48) {
                              throw DataTypeErrors$.MODULE$.unsupportedRoundingMode(roundMode);
                           }

                           var49 = 0L;
                           break label164;
                        }

                        var49 = lv > 0L ? 1L : 0L;
                        break label164;
                     }

                     var49 = lv < 0L ? -1L : 0L;
                  }

                  lv = var49;
               } else {
                  label223: {
                     long droppedDigits;
                     label210: {
                        long pow10diff = Decimal$.MODULE$.POW_10()[diff];
                        droppedDigits = lv % pow10diff;
                        lv /= pow10diff;
                        Enumeration.Value var10000 = Decimal$.MODULE$.ROUND_FLOOR();
                        if (var10000 == null) {
                           if (roundMode == null) {
                              break label210;
                           }
                        } else if (var10000.equals(roundMode)) {
                           break label210;
                        }

                        label211: {
                           var10000 = Decimal$.MODULE$.ROUND_CEILING();
                           if (var10000 == null) {
                              if (roundMode == null) {
                                 break label211;
                              }
                           } else if (var10000.equals(roundMode)) {
                              break label211;
                           }

                           label226: {
                              var10000 = Decimal$.MODULE$.ROUND_HALF_UP();
                              if (var10000 == null) {
                                 if (roundMode == null) {
                                    break label226;
                                 }
                              } else if (var10000.equals(roundMode)) {
                                 break label226;
                              }

                              var10000 = Decimal$.MODULE$.ROUND_HALF_EVEN();
                              if (var10000 == null) {
                                 if (roundMode != null) {
                                    throw DataTypeErrors$.MODULE$.unsupportedRoundingMode(roundMode);
                                 }
                              } else if (!var10000.equals(roundMode)) {
                                 throw DataTypeErrors$.MODULE$.unsupportedRoundingMode(roundMode);
                              }

                              long doubled = scala.math.package..MODULE$.abs(droppedDigits) * 2L;
                              if (doubled > pow10diff || doubled == pow10diff && lv % 2L != 0L) {
                                 lv += droppedDigits < 0L ? -1L : 1L;
                                 BoxedUnit var37 = BoxedUnit.UNIT;
                                 break label223;
                              }

                              BoxedUnit var36 = BoxedUnit.UNIT;
                              break label223;
                           }

                           if (scala.math.package..MODULE$.abs(droppedDigits) * 2L >= pow10diff) {
                              lv += droppedDigits < 0L ? -1L : 1L;
                              BoxedUnit var38 = BoxedUnit.UNIT;
                           } else {
                              BoxedUnit var39 = BoxedUnit.UNIT;
                           }
                           break label223;
                        }

                        if (droppedDigits > 0L) {
                           ++lv;
                           BoxedUnit var40 = BoxedUnit.UNIT;
                        } else {
                           BoxedUnit var41 = BoxedUnit.UNIT;
                        }
                        break label223;
                     }

                     if (droppedDigits < 0L) {
                        lv += -1L;
                        BoxedUnit var42 = BoxedUnit.UNIT;
                     } else {
                        BoxedUnit var43 = BoxedUnit.UNIT;
                     }
                  }
               }
            } else if (scale > this._scale()) {
               int diff = scale - this._scale();
               long p = Decimal$.MODULE$.POW_10()[scala.math.package..MODULE$.max(Decimal$.MODULE$.MAX_LONG_DIGITS() - diff, 0)];
               if (diff <= Decimal$.MODULE$.MAX_LONG_DIGITS() && lv > -p && lv < p) {
                  lv *= Decimal$.MODULE$.POW_10()[diff];
               } else {
                  dv = .MODULE$.BigDecimal().apply(lv, this._scale());
               }
            }
         }

         if (dv != null) {
            dv = dv.setScale(scale, roundMode);
            if (dv.precision() > precision) {
               return false;
            }
         } else {
            long p = Decimal$.MODULE$.POW_10()[scala.math.package..MODULE$.min(precision, Decimal$.MODULE$.MAX_LONG_DIGITS())];
            if (lv <= -p || lv >= p) {
               return false;
            }
         }

         this.decimalVal_$eq(dv);
         this.org$apache$spark$sql$types$Decimal$$longVal_$eq(lv);
         this.org$apache$spark$sql$types$Decimal$$_precision_$eq(precision);
         this.org$apache$spark$sql$types$Decimal$$_scale_$eq(scale);
         return true;
      }
   }

   public Decimal clone() {
      return (new Decimal()).set(this);
   }

   public int compare(final Decimal other) {
      if (this.decimalVal() == null && other.decimalVal() == null && this._scale() == other._scale()) {
         if (this.longVal() < other.longVal()) {
            return -1;
         } else {
            return this.longVal() == other.longVal() ? 0 : 1;
         }
      } else {
         return this.toBigDecimal().compare(other.toBigDecimal());
      }
   }

   public boolean equals(final Object other) {
      if (other instanceof Decimal var4) {
         return this.compare(var4) == 0;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.toBigDecimal().hashCode();
   }

   public boolean isZero() {
      if (this.decimalVal() != null) {
         return this.decimalVal().signum() == 0;
      } else {
         return this.longVal() == 0L;
      }
   }

   public Decimal $plus(final Decimal that) {
      return this.decimalVal() == null && that.decimalVal() == null && this.scale() == that.scale() ? Decimal$.MODULE$.apply(this.longVal() + that.longVal(), Math.max(this.precision(), that.precision()) + 1, this.scale()) : Decimal$.MODULE$.apply(this.toJavaBigDecimal().add(that.toJavaBigDecimal()));
   }

   public Decimal $minus(final Decimal that) {
      return this.decimalVal() == null && that.decimalVal() == null && this.scale() == that.scale() ? Decimal$.MODULE$.apply(this.longVal() - that.longVal(), Math.max(this.precision(), that.precision()) + 1, this.scale()) : Decimal$.MODULE$.apply(this.toJavaBigDecimal().subtract(that.toJavaBigDecimal()));
   }

   public Decimal $times(final Decimal that) {
      return Decimal$.MODULE$.apply(this.toJavaBigDecimal().multiply(that.toJavaBigDecimal(), Decimal$.MODULE$.org$apache$spark$sql$types$Decimal$$MATH_CONTEXT()));
   }

   public Decimal $div(final Decimal that) {
      return that.isZero() ? null : Decimal$.MODULE$.apply(this.toJavaBigDecimal().divide(that.toJavaBigDecimal(), DecimalType$.MODULE$.MAX_SCALE() + 1, Decimal$.MODULE$.org$apache$spark$sql$types$Decimal$$MATH_CONTEXT().getRoundingMode()));
   }

   public Decimal $percent(final Decimal that) {
      return that.isZero() ? null : Decimal$.MODULE$.apply(this.toJavaBigDecimal().remainder(that.toJavaBigDecimal(), Decimal$.MODULE$.org$apache$spark$sql$types$Decimal$$MATH_CONTEXT()));
   }

   public Decimal quot(final Decimal that) {
      return that.isZero() ? null : Decimal$.MODULE$.apply(this.toJavaBigDecimal().divideToIntegralValue(that.toJavaBigDecimal(), Decimal$.MODULE$.org$apache$spark$sql$types$Decimal$$MATH_CONTEXT()));
   }

   public Decimal remainder(final Decimal that) {
      return this.$percent(that);
   }

   public Decimal unary_$minus() {
      return this.decimalVal() != null ? Decimal$.MODULE$.apply(this.decimalVal().unary_$minus(), this.precision(), this.scale()) : Decimal$.MODULE$.apply(-this.longVal(), this.precision(), this.scale());
   }

   public Decimal abs() {
      return this.$less(Decimal$.MODULE$.ZERO()) ? this.unary_$minus() : this;
   }

   public Decimal floor() {
      if (this.scale() == 0) {
         return this;
      } else {
         int newPrecision = DecimalType$.MODULE$.bounded(this.precision() - this.scale() + 1, 0).precision();
         return this.toPrecision(newPrecision, 0, Decimal$.MODULE$.ROUND_FLOOR(), false, this.toPrecision$default$5());
      }
   }

   public Decimal ceil() {
      if (this.scale() == 0) {
         return this;
      } else {
         int newPrecision = DecimalType$.MODULE$.bounded(this.precision() - this.scale() + 1, 0).precision();
         return this.toPrecision(newPrecision, 0, Decimal$.MODULE$.ROUND_CEILING(), false, this.toPrecision$default$5());
      }
   }

   // $FF: synthetic method
   public static final byte $anonfun$roundToByte$1(final long x$1) {
      return (byte)((int)x$1);
   }

   // $FF: synthetic method
   public static final byte $anonfun$roundToByte$2(final double x$2) {
      return (byte)((int)x$2);
   }

   // $FF: synthetic method
   public static final short $anonfun$roundToShort$1(final long x$3) {
      return (short)((int)x$3);
   }

   // $FF: synthetic method
   public static final short $anonfun$roundToShort$2(final double x$4) {
      return (short)((int)x$4);
   }

   public Decimal() {
      Ordered.$init$(this);
      this.decimalVal = null;
      this.org$apache$spark$sql$types$Decimal$$longVal = 0L;
      this.org$apache$spark$sql$types$Decimal$$_precision = 1;
      this.org$apache$spark$sql$types$Decimal$$_scale = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface DecimalIsConflicted extends Numeric {
      default Decimal plus(final Decimal x, final Decimal y) {
         return x.$plus(y);
      }

      default Decimal times(final Decimal x, final Decimal y) {
         return x.$times(y);
      }

      default Decimal minus(final Decimal x, final Decimal y) {
         return x.$minus(y);
      }

      default Decimal negate(final Decimal x) {
         return x.unary_$minus();
      }

      default double toDouble(final Decimal x) {
         return x.toDouble();
      }

      default float toFloat(final Decimal x) {
         return x.toFloat();
      }

      default int toInt(final Decimal x) {
         return x.toInt();
      }

      default long toLong(final Decimal x) {
         return x.toLong();
      }

      default Decimal fromInt(final int x) {
         return (new Decimal()).set(x);
      }

      default int compare(final Decimal x, final Decimal y) {
         return x.compare(y);
      }

      default Option parseString(final String str) {
         return scala.util.Try..MODULE$.apply(() -> Decimal$.MODULE$.apply(str)).toOption();
      }

      static void $init$(final DecimalIsConflicted $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class DecimalIsFractional$ implements DecimalIsConflicted, Fractional {
      public static final DecimalIsFractional$ MODULE$ = new DecimalIsFractional$();

      static {
         PartialOrdering.$init$(MODULE$);
         Ordering.$init$(MODULE$);
         Numeric.$init$(MODULE$);
         Decimal.DecimalIsConflicted.$init$(MODULE$);
         Fractional.$init$(MODULE$);
      }

      public Fractional.FractionalOps mkNumericOps(final Object lhs) {
         return Fractional.mkNumericOps$(this, lhs);
      }

      public Decimal plus(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.plus(x, y);
      }

      public Decimal times(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.times(x, y);
      }

      public Decimal minus(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.minus(x, y);
      }

      public Decimal negate(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.negate(x);
      }

      public double toDouble(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toDouble(x);
      }

      public float toFloat(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toFloat(x);
      }

      public int toInt(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toInt(x);
      }

      public long toLong(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toLong(x);
      }

      public Decimal fromInt(final int x) {
         return Decimal.DecimalIsConflicted.super.fromInt(x);
      }

      public int compare(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.compare(x, y);
      }

      public Option parseString(final String str) {
         return Decimal.DecimalIsConflicted.super.parseString(str);
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

      public Decimal div(final Decimal x, final Decimal y) {
         return x.$div(y);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DecimalIsFractional$.class);
      }
   }

   public static class DecimalAsIfIntegral$ implements DecimalIsConflicted, Integral {
      public static final DecimalAsIfIntegral$ MODULE$ = new DecimalAsIfIntegral$();

      static {
         PartialOrdering.$init$(MODULE$);
         Ordering.$init$(MODULE$);
         Numeric.$init$(MODULE$);
         Decimal.DecimalIsConflicted.$init$(MODULE$);
         Integral.$init$(MODULE$);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public Decimal plus(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.plus(x, y);
      }

      public Decimal times(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.times(x, y);
      }

      public Decimal minus(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.minus(x, y);
      }

      public Decimal negate(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.negate(x);
      }

      public double toDouble(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toDouble(x);
      }

      public float toFloat(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toFloat(x);
      }

      public int toInt(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toInt(x);
      }

      public long toLong(final Decimal x) {
         return Decimal.DecimalIsConflicted.super.toLong(x);
      }

      public Decimal fromInt(final int x) {
         return Decimal.DecimalIsConflicted.super.fromInt(x);
      }

      public int compare(final Decimal x, final Decimal y) {
         return Decimal.DecimalIsConflicted.super.compare(x, y);
      }

      public Option parseString(final String str) {
         return Decimal.DecimalIsConflicted.super.parseString(str);
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

      public Decimal quot(final Decimal x, final Decimal y) {
         return x.quot(y);
      }

      public Decimal rem(final Decimal x, final Decimal y) {
         return x.$percent(y);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DecimalAsIfIntegral$.class);
      }
   }
}
