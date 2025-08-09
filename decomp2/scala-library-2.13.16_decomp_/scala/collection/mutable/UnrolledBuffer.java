package scala.collection.mutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import scala.Array$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.PartialFunction;
import scala.Predef$;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.ArrayOps$;
import scala.collection.ClassTagIterableFactory;
import scala.collection.EvidenceIterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.SeqFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.StringOps$;
import scala.collection.View;
import scala.collection.generic.CommonErrors$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil$;
import scala.math.Integral;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Null$;
import scala.runtime.ObjectRef;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011]c\u0001B2e!-D!\"!\u0010\u0001\u0005\u000b\u0007I1AA \u0011)\t\u0019\u0005\u0001B\u0001B\u0003%\u0011\u0011\t\u0005\b\u0003\u000b\u0002A\u0011AA$\u0011%\ti\u0005\u0001a\u0001\n\u0013\ty\u0005C\u0005\u0004Z\u0001\u0001\r\u0011\"\u0003\u0004\\!A1q\f\u0001!B\u0013\t\t\u0006C\u0005\u0004j\u0001\u0001\r\u0011\"\u0003\u0002P!I11\u000e\u0001A\u0002\u0013%1Q\u000e\u0005\t\u0007c\u0002\u0001\u0015)\u0003\u0002R!I1Q\u000f\u0001A\u0002\u0013%\u00111\u0018\u0005\n\u0007o\u0002\u0001\u0019!C\u0005\u0007sB\u0001b! \u0001A\u0003&\u0011Q\u0018\u0005\t\u0007\u0003\u0003A\u0011\u00014\u0002P!A11\u0011\u0001\u0005\u0002\u0019\u001c)\t\u0003\u0005\u0004\f\u0002!\tAZA(\u0011!\u0019i\t\u0001C\u0001M\u000e=\u0005\u0002CAw\u0001\u0011\u0005am!&\t\u000f\rm\u0005\u0001\"\u0005\u0004\u001e\"91q\u0014\u0001\u0005\u0012\u0005}\u0002bBBQ\u0001\u0011\u0005\u00131\u000e\u0005\b\u0007G\u0003A\u0011CA(\u0011!\u0019)\u000b\u0001C\u0001M\u000e\u001d\u0006bBBV\u0001\u0011\u00051Q\u0014\u0005\b\u0007[\u0003A\u0011ABX\u0011\u001d\u0019)\f\u0001C\u0001\u0007oCqa!0\u0001\t\u0003\u0011i\u000bC\u0004\u0004@\u0002!\ta!1\t\u000f\t%\u0004\u0001\"\u0011\u0004J\"91Q\u001b\u0001\u0005\u0002\r]\u0007bBBm\u0001\u0011\u0005\u00111\u0018\u0005\b\u00077\u0004A\u0011IA^\u0011\u001d\u00119\t\u0001C\u0001\u0007;DqA!%\u0001\t\u0003\u0019\t\u000fC\u0004\u0003\u0000\u0001!\taa:\t\u000f\t=\u0006\u0001\"\u0001\u0004n\"9!q\u0016\u0001\u0005\u0006\rE\bb\u0002BS\u0001\u0011\u000511 \u0005\b\u0007\u007f\u0004A\u0011\u0001C\u0001\u0011\u001d\u00119\u000e\u0001C\u0001\t\u000fAqAa/\u0001\t\u0003\"\t\u0002C\u0004\u0005\u0016\u0001!\t\u0001b\u0006\t\u000f\u0011\r\u0002\u0001\"\u0003\u0005&!9Aq\u0007\u0001\u0005\n\u0011e\u0002b\u0002C#\u0001\u0011\u00053q\u001b\u0005\t\t\u000f\u0002\u0001\u0015\"\u0015\u0005J\u001d9\u0011Q\u000b3\t\u0002\u0005]cAB2e\u0011\u0003\tI\u0006C\u0004\u0002F=\"\t!a\u001a\t\u0013\u0005%tF1A\u0005\u0002\u0005-\u0004\u0002CA:_\u0001\u0006I!!\u001c\t\u000f\u0005Ut\u0006\"\u0001\u0002x!9\u0011\u0011R\u0018\u0005\u0002\u0005-\u0005bBAT_\u0011\u0005\u0011\u0011\u0016\u0005\n\u0003s{#\u0019!C\u0003\u0003wC\u0001\"a10A\u00035\u0011Q\u0018\u0005\b\u0003\u000b|CQAA^\u0011%\t9m\fb\u0001\n\u000b\tY\f\u0003\u0005\u0002^>\u0002\u000bQBA_\u0011)\t\to\fb\u0001\n\u00031\u00171\u0018\u0005\t\u0003G|\u0003\u0015!\u0003\u0002>\u001a1\u0011Q]\u0018\u0001\u0003OD!\"a;>\u0005\u0003\u0007I\u0011AA^\u0011)\ti/\u0010BA\u0002\u0013\u0005\u0011q\u001e\u0005\u000b\u0003wl$\u0011!Q!\n\u0005u\u0006BCA\u007f{\t\u0005\r\u0011\"\u0001\u0002\u0000\"Q!1B\u001f\u0003\u0002\u0004%\tA!\u0004\t\u0015\tEQH!A!B\u0013\u0011\t\u0001\u0003\u0006\u0003\u0014u\u0012\t\u0019!C\u0001\u0005+A!Ba\u0007>\u0005\u0003\u0007I\u0011\u0001B\u000f\u0011)\u0011\t#\u0010B\u0001B\u0003&!q\u0003\u0005\u000b\u0005Gi$Q1A\u0005\u0002\t\u0015\u0002B\u0003B\u0015{\t\u0005\t\u0015!\u0003\u0003(!Q!1F\u001f\u0003\u0004\u0003\u0006YA!\f\t\u0011\u0005\u0015S\b\"\u0001g\u0005_A\u0001\"!\u0012>\t\u00031'Q\b\u0005\t\u0003\u000bjD\u0011\u00014\u0003F!9!\u0011K\u001f\u0005\n\u0005m\u0006b\u0002B*{\u0011\u0015!Q\u000b\u0005\b\u0005SjD\u0011\u0001B6\u0011\u001d\u0011y(\u0010C\u0001\u0005\u0003CqAa\">\t\u000b\u0011I\tC\u0004\u0003\u0012v\")Aa%\t\u000f\tuU\b\"\u0002\u0003 \"9!QU\u001f\u0005\u0002\t\u001d\u0006b\u0002BV{\u0011%!Q\u0016\u0005\b\u0005_kDQ\u0001BY\u0011\u001d\u0011Y,\u0010C\u0003\u0005{CqAa3>\t\u0013\u0011i\rC\u0004\u0003Tv\"\tB!6\t\u000f\t]W\b\"\u0002\u0003Z\"9!q]\u001f\u0005\n\t%\bb\u0002By{\u0011\u0005!1\u001f\u0005\b\u0005slD\u0011\tB~\u000f%\u0019\u0019bLA\u0001\u0012\u0003\u0019)BB\u0005\u0002f>\n\t\u0011#\u0001\u0004\u0018!9\u0011QI0\u0005\u0002\re\u0001BCB\u000e?F\u0005I\u0011\u00014\u0004\u001e!I1qG\u0018\u0002\u0002\u0013%1\u0011\b\u0002\u000f+:\u0014x\u000e\u001c7fI\n+hMZ3s\u0015\t)g-A\u0004nkR\f'\r\\3\u000b\u0005\u001dD\u0017AC2pY2,7\r^5p]*\t\u0011.A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u00051\u001c8c\u0004\u0001n{\u0006\u0005\u0011qAA\t\u00033\tY#!\r\u0011\u00079|\u0017/D\u0001e\u0013\t\u0001HM\u0001\bBEN$(/Y2u\u0005V4g-\u001a:\u0011\u0005I\u001cH\u0002\u0001\u0003\u0006i\u0002\u0011\r!\u001e\u0002\u0002)F\u0011aO\u001f\t\u0003obl\u0011\u0001[\u0005\u0003s\"\u0014qAT8uQ&tw\r\u0005\u0002xw&\u0011A\u0010\u001b\u0002\u0004\u0003:L\bc\u00018\u007fc&\u0011q\u0010\u001a\u0002\u0007\u0005V4g-\u001a:\u0011\t9\f\u0019!]\u0005\u0004\u0003\u000b!'aA*fcBAa.!\u0003r\u0003\u001b\ty!C\u0002\u0002\f\u0011\u0014aaU3r\u001fB\u001c\bC\u00018\u0001!\rq\u0007!\u001d\t\n\u0003'\t)\"]A\u0007\u0003\u001fi\u0011AZ\u0005\u0004\u0003/1'!F*ue&\u001cGo\u00149uS6L'0\u001a3TKF|\u0005o\u001d\t\n\u0003'\tY\"]A\u0007\u0003?I1!!\bg\u0005})e/\u001b3f]\u000e,\u0017\n^3sC\ndWMR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0005\u0003C\t9#\u0004\u0002\u0002$)\u0019\u0011Q\u00055\u0002\u000fI,g\r\\3di&!\u0011\u0011FA\u0012\u0005!\u0019E.Y:t)\u0006<\u0007C\u00028\u0002.E\fy!C\u0002\u00020\u0011\u0014qAQ;jY\u0012,'\u000f\u0005\u0003\u00024\u0005eRBAA\u001b\u0015\r\t9DZ\u0001\bO\u0016tWM]5d\u0013\u0011\tY$!\u000e\u0003'\u0011+g-Y;miN+'/[1mSj\f'\r\\3\u0002\u0007Q\fw-\u0006\u0002\u0002BA)\u0011\u0011EA\u0014c\u0006!A/Y4!\u0003\u0019a\u0014N\\5u}Q\u0011\u0011\u0011\n\u000b\u0005\u0003\u001f\tY\u0005C\u0004\u0002>\r\u0001\u001d!!\u0011\u0002\u000f!,\u0017\r\u001a9ueV\u0011\u0011\u0011\u000b\t\u0005\u0003'j\u0014O\u0004\u0002o]\u0005qQK\u001c:pY2,GMQ;gM\u0016\u0014\bC\u000180'\u0015y\u00131LA1!\r9\u0018QL\u0005\u0004\u0003?B'AB!osJ+g\r\u0005\u0004\u0002\u0014\u0005\r\u0014QB\u0005\u0004\u0003K2'!I*ue&\u001cGo\u00149uS6L'0\u001a3DY\u0006\u001c8\u000fV1h'\u0016\fh)Y2u_JLHCAA,\u0003!)h\u000e^1hO\u0016$WCAA7!\u0019\t\u0019\"a\u001c\u0002\u000e%\u0019\u0011\u0011\u000f4\u0003\u0015M+\u0017OR1di>\u0014\u00180A\u0005v]R\fwmZ3eA\u0005)Q-\u001c9usV!\u0011\u0011PA@)\u0011\tY(a!\u0011\t9\u0004\u0011Q\u0010\t\u0004e\u0006}DABAAg\t\u0007QOA\u0001B\u0011%\t)iMA\u0001\u0002\b\t9)\u0001\u0006fm&$WM\\2fIE\u0002b!!\t\u0002(\u0005u\u0014\u0001\u00024s_6,B!!$\u0002\u0016R!\u0011qRAO)\u0011\t\t*a&\u0011\t9\u0004\u00111\u0013\t\u0004e\u0006UEABAAi\t\u0007Q\u000fC\u0005\u0002\u001aR\n\t\u0011q\u0001\u0002\u001c\u0006QQM^5eK:\u001cW\r\n\u001a\u0011\r\u0005\u0005\u0012qEAJ\u0011\u001d\ty\n\u000ea\u0001\u0003C\u000baa]8ve\u000e,\u0007CBA\n\u0003G\u000b\u0019*C\u0002\u0002&\u001a\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!B\\3x\u0005VLG\u000eZ3s+\u0011\tY+!-\u0015\t\u00055\u00161\u0017\t\u0005]\u0002\ty\u000bE\u0002s\u0003c#a!!!6\u0005\u0004)\b\"CA[k\u0005\u0005\t9AA\\\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003C\t9#a,\u0002\u0013]\fG/\u001a:mS:,WCAA_!\r9\u0018qX\u0005\u0004\u0003\u0003D'aA%oi\u0006Qq/\u0019;fe2Lg.\u001a\u0011\u0002\u001d]\fG/\u001a:mS:,G)\u001a8p[\u0006qq/\u0019;fe2Lg.\u001a#fY&l\u0007fC\u001d\u0002L\u0006E\u00171[Al\u00033\u00042a^Ag\u0013\r\ty\r\u001b\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0003+\f1$V:fA]\fG/\u001a:mS:,G)\u001a8p[\u0002Jgn\u001d;fC\u0012t\u0013!B:j]\u000e,\u0017EAAn\u0003\u0019\u0011d&M\u001a/a\u0005yq/\u0019;fe2Lg.\u001a#fY&l\u0007\u0005K\u0006;\u0003\u0017\f\t.a5\u0002X\u0006e\u0017AD;oe>dG.\u001a3mK:<G\u000f[\u0001\u0010k:\u0014x\u000e\u001c7fI2,gn\u001a;iA\tAQK\u001c:pY2,G-\u0006\u0003\u0002j\n%1cA\u001f\u0002\\\u0005!1/\u001b>f\u0003!\u0019\u0018N_3`I\u0015\fH\u0003BAy\u0003o\u00042a^Az\u0013\r\t)\u0010\u001b\u0002\u0005+:LG\u000fC\u0005\u0002z~\n\t\u00111\u0001\u0002>\u0006\u0019\u0001\u0010J\u0019\u0002\u000bML'0\u001a\u0011\u0002\u000b\u0005\u0014(/Y=\u0016\u0005\t\u0005\u0001#B<\u0003\u0004\t\u001d\u0011b\u0001B\u0003Q\n)\u0011I\u001d:bsB\u0019!O!\u0003\u0005\u000bQl$\u0019A;\u0002\u0013\u0005\u0014(/Y=`I\u0015\fH\u0003BAy\u0005\u001fA\u0011\"!?C\u0003\u0003\u0005\rA!\u0001\u0002\r\u0005\u0014(/Y=!\u0003\u0011qW\r\u001f;\u0016\u0005\t]\u0001#\u0002B\r{\t\u001dQ\"A\u0018\u0002\u00119,\u0007\u0010^0%KF$B!!=\u0003 !I\u0011\u0011`#\u0002\u0002\u0003\u0007!qC\u0001\u0006]\u0016DH\u000fI\u0001\u0005EV4g-\u0006\u0002\u0003(A!a\u000e\u0001B\u0004\u0003\u0015\u0011WO\u001a4!\u0003))g/\u001b3f]\u000e,G\u0005\u000e\t\u0007\u0003C\t9Ca\u0002\u0015\u0015\tE\"Q\u0007B\u001c\u0005s\u0011Y\u0004\u0006\u0003\u0003\u0018\tM\u0002b\u0002B\u0016\u0015\u0002\u000f!Q\u0006\u0005\b\u0003WT\u0005\u0019AA_\u0011\u001d\tiP\u0013a\u0001\u0005\u0003AqAa\u0005K\u0001\u0004\u00119\u0002C\u0005\u0003$)\u0003\n\u00111\u0001\u0003(Q\u0011!q\b\u000b\u0005\u0005/\u0011\t\u0005C\u0005\u0003D-\u000b\t\u0011q\u0001\u0003.\u0005QQM^5eK:\u001cW\rJ\u001b\u0015\t\t\u001d#Q\n\u000b\u0005\u0005/\u0011I\u0005C\u0005\u0003L1\u000b\t\u0011q\u0001\u0003.\u0005QQM^5eK:\u001cW\r\n\u001c\t\u000f\t=C\n1\u0001\u0003(\u0005\t!-\u0001\u0006oKb$H.\u001a8hi\"\fa!\u00199qK:$G\u0003\u0002B\f\u0005/BqA!\u0017O\u0001\u0004\u00119!\u0001\u0003fY\u0016l\u0007f\u0001(\u0003^A!!q\fB3\u001b\t\u0011\tGC\u0002\u0003d!\f!\"\u00198o_R\fG/[8o\u0013\u0011\u00119G!\u0019\u0003\u000fQ\f\u0017\u000e\u001c:fG\u00069am\u001c:fC\u000eDW\u0003\u0002B7\u0005w\"B!!=\u0003p!9!\u0011O(A\u0002\tM\u0014!\u00014\u0011\u000f]\u0014)Ha\u0002\u0003z%\u0019!q\u000f5\u0003\u0013\u0019+hn\u0019;j_:\f\u0004c\u0001:\u0003|\u00111!QP(C\u0002U\u0014\u0011!V\u0001\u000b[\u0006\u0004\u0018J\u001c)mC\u000e,G\u0003BAy\u0005\u0007CqA!\u001dQ\u0001\u0004\u0011)\tE\u0004x\u0005k\u00129Aa\u0002\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\t\u001d!1\u0012\u0005\b\u0005\u001b\u000b\u0006\u0019AA_\u0003\rIG\r\u001f\u0015\u0004#\nu\u0013AB;qI\u0006$X\r\u0006\u0004\u0002r\nU%q\u0013\u0005\b\u0005\u001b\u0013\u0006\u0019AA_\u0011\u001d\u0011IJ\u0015a\u0001\u0005\u000f\tqA\\3xK2,W\u000eK\u0002S\u0005;\na\u0001\\8dCR,G\u0003\u0002B\f\u0005CCqA!$T\u0001\u0004\ti\fK\u0002T\u0005;\nq\u0001\u001d:fa\u0016tG\r\u0006\u0003\u0003\u0018\t%\u0006b\u0002B-)\u0002\u0007!qA\u0001\u000bg\"Lg\r\u001e:jO\"$HCAAy\u0003\u0019\u0011X-\\8wKR1!q\u0001BZ\u0005kCqA!$W\u0001\u0004\ti\fC\u0004\u00038Z\u0003\rAa\n\u0002\r\t,hMZ3sQ\r1&QL\u0001\fgV\u0014GO]1di>sW\r\u0006\u0004\u0003@\n\u0015'q\u0019\t\u0004o\n\u0005\u0017b\u0001BbQ\n9!i\\8mK\u0006t\u0007b\u0002B-/\u0002\u0007!q\u0001\u0005\b\u0005o;\u0006\u0019\u0001B\u0014Q\r9&QL\u0001\ng\"Lg\r\u001e7fMR$B!!=\u0003P\"9!\u0011\u001b-A\u0002\u0005u\u0016!\u00027fMR\u0014\u0017\u0001\u0005;ss6+'oZ3XSRDg*\u001a=u)\t\u0011y,A\u0005j]N,'\u000f^!mYRA\u0011Q\u0018Bn\u0005;\u0014\u0019\u000fC\u0004\u0003\u000ej\u0003\r!!0\t\u000f\t}'\f1\u0001\u0003b\u0006\tA\u000f\u0005\u0004\u0002\u0014\u0005\r&q\u0001\u0005\b\u0005oS\u0006\u0019\u0001B\u0014Q\rQ&QL\u0001\b]VdGn\\;u)\u0019\t\tPa;\u0003n\"9\u0011\u0011R.A\u0002\u0005u\u0006b\u0002Bx7\u0002\u0007\u0011QX\u0001\u0006k:$\u0018\u000e\\\u0001\u0005E&tG\r\u0006\u0003\u0003@\nU\bb\u0002B|9\u0002\u0007!qC\u0001\ti\"\fG\u000f[3bI\u0006AAo\\*ue&tw\r\u0006\u0002\u0003~B!!q`B\u0007\u001d\u0011\u0019\ta!\u0003\u0011\u0007\r\r\u0001.\u0004\u0002\u0004\u0006)\u00191q\u00016\u0002\rq\u0012xn\u001c;?\u0013\r\u0019Y\u0001[\u0001\u0007!J,G-\u001a4\n\t\r=1\u0011\u0003\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\r-\u0001.\u0001\u0005V]J|G\u000e\\3e!\r\u0011IbX\n\u0004?\u0006mCCAB\u000b\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%iU!1qDB\u001b+\t\u0019\tC\u000b\u0003\u0004$\r\u001drBAB\u0013E\u0001Y#a!\u000b\u0011\t\r-2\u0011G\u0007\u0003\u0007[QAaa\f\u0003b\u0005IQO\\2iK\u000e\\W\rZ\u0005\u0005\u0007g\u0019iCA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$Q\u0001^1C\u0002U\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"aa\u000f\u0011\t\ru2qI\u0007\u0003\u0007\u007fQAa!\u0011\u0004D\u0005!A.\u00198h\u0015\t\u0019)%\u0001\u0003kCZ\f\u0017\u0002BB%\u0007\u007f\u0011aa\u00142kK\u000e$\bfB\u0018\u0004N\rM3Q\u000b\t\u0004o\u000e=\u0013bAB)Q\n\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0007!:af!\u0014\u0004T\rU\u0013a\u00035fC\u0012\u0004HO]0%KF$B!!=\u0004^!I\u0011\u0011`\u0003\u0002\u0002\u0003\u0007\u0011\u0011K\u0001\tQ\u0016\fG\r\u001d;sA!\u001aaaa\u0019\u0011\u0007]\u001c)'C\u0002\u0004h!\u0014\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u000f1\f7\u000f\u001e9ue\u0006YA.Y:uaR\u0014x\fJ3r)\u0011\t\tpa\u001c\t\u0013\u0005e\b\"!AA\u0002\u0005E\u0013\u0001\u00037bgR\u0004HO\u001d\u0011)\u0007%\u0019\u0019'\u0001\u0002tu\u000611O_0%KF$B!!=\u0004|!I\u0011\u0011`\u0006\u0002\u0002\u0003\u0007\u0011QX\u0001\u0004gj\u0004\u0003f\u0001\u0007\u0004d\u00059\u0001.Z1e!R\u0014\u0018a\u00035fC\u0012\u0004FO]0%KF$B!!=\u0004\b\"91\u0011\u0012\bA\u0002\u0005E\u0013\u0001\u00025fC\u0012\fq\u0001\\1tiB#(/A\u0006mCN$\b\u000b\u001e:`I\u0015\fH\u0003BAy\u0007#Cqaa%\u0011\u0001\u0004\t\t&\u0001\u0003mCN$H\u0003BAy\u0007/Cqa!'\u0012\u0001\u0004\ti,A\u0001t\u0003])g/\u001b3f]\u000e,\u0017\n^3sC\ndWMR1di>\u0014\u00180\u0006\u0002\u0002T\u0005\u0001\u0012\u000e^3sC\ndW-\u0012<jI\u0016t7-Z\u0001\u0010SR,'/\u00192mK\u001a\u000b7\r^8ss\u0006Ya.Z<V]J|G\u000e\\3e\u00039\u0019\u0017\r\\2OKb$H*\u001a8hi\"$B!!0\u0004*\"91Q\u000f\fA\u0002\u0005u\u0016!E2mCN\u001cH+Y4D_6\u0004\u0018M\\5p]\u000611m\u001c8dCR$B!a\u0004\u00042\"911\u0017\rA\u0002\u0005=\u0011\u0001\u0002;iCR\fa!\u00193e\u001f:,G\u0003BB]\u0007wk\u0011\u0001\u0001\u0005\u0007\u00053J\u0002\u0019A9\u0002\u000b\rdW-\u0019:\u0002\u0011%$XM]1u_J,\"aa1\u0011\u000b\u0005M1QY9\n\u0007\r\u001dgM\u0001\u0005Ji\u0016\u0014\u0018\r^8s+\u0011\u0019Yma5\u0015\t\u0005E8Q\u001a\u0005\b\u0005cb\u0002\u0019ABh!\u00199(QO9\u0004RB\u0019!oa5\u0005\r\tuDD1\u0001v\u0003\u0019\u0011Xm];miR\u0011\u0011qB\u0001\u0007Y\u0016tw\r\u001e5\u0002\u0013-twn\u001e8TSj,GcA9\u0004`\"9!Q\u0012\u0011A\u0002\u0005uFCBAy\u0007G\u001c)\u000fC\u0004\u0003\u000e\u0006\u0002\r!!0\t\r\te\u0015\u00051\u0001r)\u0011\u0019Il!;\t\u000f\tE$\u00051\u0001\u0004lB)qO!\u001ercR\u0019\u0011oa<\t\u000f\t55\u00051\u0001\u0002>R1\u0011\u0011_Bz\u0007kDqA!$%\u0001\u0004\ti\fC\u0004\u0004x\u0012\u0002\r!!0\u0002\u000b\r|WO\u001c;)\u0007\u0011\u0012i\u0006\u0006\u0003\u0004:\u000eu\bB\u0002B-K\u0001\u0007\u0011/\u0001\u0004j]N,'\u000f\u001e\u000b\u0007\u0003c$\u0019\u0001\"\u0002\t\u000f\t5e\u00051\u0001\u0002>\"1!\u0011\f\u0014A\u0002E$b!!=\u0005\n\u0011-\u0001b\u0002BGO\u0001\u0007\u0011Q\u0018\u0005\b\t\u001b9\u0003\u0019\u0001C\b\u0003\u0015)G.Z7t!\u0015\t\u0019\"a)r)\u0011\u0019I\fb\u0005\t\r\te\u0003\u00061\u0001r\u00031\u0001\u0018\r^2i\u0013:\u0004F.Y2f)!\u0019I\f\"\u0007\u0005\u001c\u0011}\u0001bBAES\u0001\u0007\u0011Q\u0018\u0005\b\t;I\u0003\u0019\u0001C\b\u0003\u0015\u0001\u0018\r^2i\u0011\u001d!\t#\u000ba\u0001\u0003{\u000b\u0001B]3qY\u0006\u001cW\rZ\u0001\foJLG/Z(cU\u0016\u001cG\u000f\u0006\u0003\u0002r\u0012\u001d\u0002b\u0002C\u0015U\u0001\u0007A1F\u0001\u0004_V$\b\u0003\u0002C\u0017\tgi!\u0001b\f\u000b\t\u0011E21I\u0001\u0003S>LA\u0001\"\u000e\u00050\t\u0011rJ\u00196fGR|U\u000f\u001e9viN#(/Z1n\u0003)\u0011X-\u00193PE*,7\r\u001e\u000b\u0005\u0003c$Y\u0004C\u0004\u0005>-\u0002\r\u0001b\u0010\u0002\u0005%t\u0007\u0003\u0002C\u0017\t\u0003JA\u0001b\u0011\u00050\t\trJ\u00196fGRLe\u000e];u'R\u0014X-Y7\u0002\u000b\rdwN\\3\u0002\u0013\rd\u0017m]:OC6,WC\u0001C&!\u0011\u0019i\u0004\"\u0014\n\t\r=1qH\u0015\u0004\u0001\u0011E\u0013b\u0001C*I\n1Bi\\;cY&tw-\u00168s_2dW\r\u001a\"vM\u001a,'\u000fK\u0004\u0001\u0007\u001b\u001a\u0019f!\u0016"
)
public class UnrolledBuffer extends AbstractBuffer implements StrictOptimizedSeqOps, EvidenceIterableFactoryDefaults, Builder, DefaultSerializable {
   private static final long serialVersionUID = 3L;
   private final ClassTag tag;
   private transient Unrolled scala$collection$mutable$UnrolledBuffer$$headptr;
   private transient Unrolled lastptr;
   private transient int sz;

   /** @deprecated */
   public static int waterlineDelim() {
      return UnrolledBuffer$.MODULE$.waterlineDelim();
   }

   public static int waterlineDenom() {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      return 100;
   }

   public static int waterline() {
      return UnrolledBuffer$.MODULE$.waterline();
   }

   public static UnrolledBuffer newBuilder(final ClassTag evidence$3) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      return new UnrolledBuffer(evidence$3);
   }

   public static UnrolledBuffer from(final IterableOnce source, final ClassTag evidence$2) {
      return UnrolledBuffer$.MODULE$.from(source, evidence$2);
   }

   public static SeqFactory untagged() {
      return UnrolledBuffer$.MODULE$.untagged();
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f, final ClassTag evidence$35) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Builder tabulate_b = new UnrolledBuffer(evidence$35);
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem, final ClassTag evidence$34) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Builder fill_b = new UnrolledBuffer(evidence$34);
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final ClassTag evidence$33) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
         Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
            Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
               Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = new UnrolledBuffer(evidence$33);
                  tabulate_b.sizeHint(n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     tabulate_b.addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var42 = (scala.collection.SeqOps)tabulate_b.result();
                  tabulate_b = null;
                  Object var41 = null;
                  Object tabulate_$plus$eq_elem = var42;
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var43 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var38 = null;
               tabulate_evidence$9 = null;
               Object tabulate_$plus$eq_elem = var43;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var44 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var34 = null;
            tabulate_evidence$9 = null;
            Object tabulate_$plus$eq_elem = var44;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var45 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var30 = null;
         tabulate_evidence$9 = null;
         Object tabulate_$plus$eq_elem = var45;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final ClassTag evidence$32) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
         Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
            Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new UnrolledBuffer(evidence$32);
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var33 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var32 = null;
               Object tabulate_$plus$eq_elem = var33;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var34 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var29 = null;
            tabulate_evidence$9 = null;
            Object tabulate_$plus$eq_elem = var34;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var35 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var25 = null;
         tabulate_evidence$9 = null;
         Object tabulate_$plus$eq_elem = var35;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f, final ClassTag evidence$31) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
         Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new UnrolledBuffer(evidence$31);
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var24 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var23 = null;
            Object tabulate_$plus$eq_elem = var24;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var25 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var20 = null;
         tabulate_evidence$9 = null;
         Object tabulate_$plus$eq_elem = var25;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f, final ClassTag evidence$30) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object tabulate_evidence$9 = ClassTag$.MODULE$.AnyRef();
      Builder tabulate_b = new UnrolledBuffer((ClassTag)tabulate_evidence$9);
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new UnrolledBuffer(evidence$30);
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var15 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var14 = null;
         Object tabulate_$plus$eq_elem = var15;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final ClassTag evidence$29) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
         Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
            Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
               Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = new UnrolledBuffer(evidence$29);
                  fill_b.sizeHint(n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     fill_b.addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var42 = (scala.collection.SeqOps)fill_b.result();
                  fill_b = null;
                  Object var41 = null;
                  Object fill_$plus$eq_elem = var42;
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var43 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var38 = null;
               fill_evidence$8 = null;
               Object fill_$plus$eq_elem = var43;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var44 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var34 = null;
            fill_evidence$8 = null;
            Object fill_$plus$eq_elem = var44;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var45 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var30 = null;
         fill_evidence$8 = null;
         Object fill_$plus$eq_elem = var45;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final ClassTag evidence$28) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
         Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
            Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new UnrolledBuffer(evidence$28);
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var33 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var32 = null;
               Object fill_$plus$eq_elem = var33;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var34 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var29 = null;
            fill_evidence$8 = null;
            Object fill_$plus$eq_elem = var34;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var35 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var25 = null;
         fill_evidence$8 = null;
         Object fill_$plus$eq_elem = var35;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem, final ClassTag evidence$27) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
         Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new UnrolledBuffer(evidence$27);
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var24 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var23 = null;
            Object fill_$plus$eq_elem = var24;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var25 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var20 = null;
         fill_evidence$8 = null;
         Object fill_$plus$eq_elem = var25;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem, final ClassTag evidence$26) {
      UnrolledBuffer$ var10000 = UnrolledBuffer$.MODULE$;
      Object fill_evidence$8 = ClassTag$.MODULE$.AnyRef();
      Builder fill_b = new UnrolledBuffer((ClassTag)fill_evidence$8);
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new UnrolledBuffer(evidence$26);
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var15 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var14 = null;
         Object fill_$plus$eq_elem = var15;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$24, final ClassTag evidence$25) {
      return ClassTagIterableFactory.range$(UnrolledBuffer$.MODULE$, start, end, step, evidence$24, evidence$25);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$22, final ClassTag evidence$23) {
      return ClassTagIterableFactory.range$(UnrolledBuffer$.MODULE$, start, end, evidence$22, evidence$23);
   }

   public static Object unfold(final Object init, final Function1 f, final Object evidence$11) {
      UnrolledBuffer$ unfold_this = UnrolledBuffer$.MODULE$;
      IterableOnce from_it = new View.Unfold(init, f);
      return unfold_this.from(from_it, (ClassTag)evidence$11);
   }

   public static Object iterate(final Object start, final int len, final Function1 f, final Object evidence$10) {
      UnrolledBuffer$ iterate_this = UnrolledBuffer$.MODULE$;
      IterableOnce from_it = new View.Iterate(start, len, f);
      return iterate_this.from(from_it, (ClassTag)evidence$10);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public void sizeHint(final int size) {
      Builder.sizeHint$(this, size);
   }

   public final void sizeHint(final IterableOnce coll, final int delta) {
      Builder.sizeHint$(this, coll, delta);
   }

   public final int sizeHint$default$2() {
      return Builder.sizeHint$default$2$(this);
   }

   public final void sizeHintBounded(final int size, final scala.collection.Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return EvidenceIterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return EvidenceIterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return EvidenceIterableFactoryDefaults.empty$(this);
   }

   public Object distinctBy(final Function1 f) {
      return StrictOptimizedSeqOps.distinctBy$(this, f);
   }

   public Object prepended(final Object elem) {
      return StrictOptimizedSeqOps.prepended$(this, elem);
   }

   public Object appended(final Object elem) {
      return StrictOptimizedSeqOps.appended$(this, elem);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return StrictOptimizedSeqOps.appendedAll$(this, suffix);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return StrictOptimizedSeqOps.prependedAll$(this, prefix);
   }

   public Object padTo(final int len, final Object elem) {
      return StrictOptimizedSeqOps.padTo$(this, len, elem);
   }

   public Object diff(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return StrictOptimizedSeqOps.intersect$(this, that);
   }

   public Tuple2 partition(final Function1 p) {
      return StrictOptimizedIterableOps.partition$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return StrictOptimizedIterableOps.span$(this, p);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return StrictOptimizedIterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return StrictOptimizedIterableOps.unzip3$(this, asTriple);
   }

   public Object map(final Function1 f) {
      return StrictOptimizedIterableOps.map$(this, f);
   }

   public final Object strictOptimizedMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedMap$(this, b, f);
   }

   public Object flatMap(final Function1 f) {
      return StrictOptimizedIterableOps.flatMap$(this, f);
   }

   public final Object strictOptimizedFlatMap(final Builder b, final Function1 f) {
      return StrictOptimizedIterableOps.strictOptimizedFlatMap$(this, b, f);
   }

   public final Object strictOptimizedConcat(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedConcat$(this, that, b);
   }

   public Object collect(final PartialFunction pf) {
      return StrictOptimizedIterableOps.collect$(this, pf);
   }

   public final Object strictOptimizedCollect(final Builder b, final PartialFunction pf) {
      return StrictOptimizedIterableOps.strictOptimizedCollect$(this, b, pf);
   }

   public Object flatten(final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.flatten$(this, toIterableOnce);
   }

   public final Object strictOptimizedFlatten(final Builder b, final Function1 toIterableOnce) {
      return StrictOptimizedIterableOps.strictOptimizedFlatten$(this, b, toIterableOnce);
   }

   public Object zip(final IterableOnce that) {
      return StrictOptimizedIterableOps.zip$(this, that);
   }

   public final Object strictOptimizedZip(final IterableOnce that, final Builder b) {
      return StrictOptimizedIterableOps.strictOptimizedZip$(this, that, b);
   }

   public Object zipWithIndex() {
      return StrictOptimizedIterableOps.zipWithIndex$(this);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return StrictOptimizedIterableOps.scanLeft$(this, z, op);
   }

   public Object filter(final Function1 pred) {
      return StrictOptimizedIterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return StrictOptimizedIterableOps.filterNot$(this, pred);
   }

   public Object filterImpl(final Function1 pred, final boolean isFlipped) {
      return StrictOptimizedIterableOps.filterImpl$(this, pred, isFlipped);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return StrictOptimizedIterableOps.partitionMap$(this, f);
   }

   public Object tapEach(final Function1 f) {
      return StrictOptimizedIterableOps.tapEach$(this, f);
   }

   public Object takeRight(final int n) {
      return StrictOptimizedIterableOps.takeRight$(this, n);
   }

   public Object dropRight(final int n) {
      return StrictOptimizedIterableOps.dropRight$(this, n);
   }

   public ClassTag tag() {
      return this.tag;
   }

   public Unrolled scala$collection$mutable$UnrolledBuffer$$headptr() {
      return this.scala$collection$mutable$UnrolledBuffer$$headptr;
   }

   private void headptr_$eq(final Unrolled x$1) {
      this.scala$collection$mutable$UnrolledBuffer$$headptr = x$1;
   }

   private Unrolled lastptr() {
      return this.lastptr;
   }

   private void lastptr_$eq(final Unrolled x$1) {
      this.lastptr = x$1;
   }

   private int sz() {
      return this.sz;
   }

   private void sz_$eq(final int x$1) {
      this.sz = x$1;
   }

   public Unrolled headPtr() {
      return this.scala$collection$mutable$UnrolledBuffer$$headptr();
   }

   public void headPtr_$eq(final Unrolled head) {
      this.headptr_$eq(head);
   }

   public Unrolled lastPtr() {
      return this.lastptr();
   }

   public void lastPtr_$eq(final Unrolled last) {
      this.lastptr_$eq(last);
   }

   public void size_$eq(final int s) {
      this.sz_$eq(s);
   }

   public UnrolledBuffer$ evidenceIterableFactory() {
      return UnrolledBuffer$.MODULE$;
   }

   public ClassTag iterableEvidence() {
      return this.tag();
   }

   public SeqFactory iterableFactory() {
      return UnrolledBuffer$.MODULE$.untagged();
   }

   public Unrolled newUnrolled() {
      return new Unrolled(this, this.tag());
   }

   public int calcNextLength(final int sz) {
      return sz;
   }

   public UnrolledBuffer$ classTagCompanion() {
      return UnrolledBuffer$.MODULE$;
   }

   public UnrolledBuffer concat(final UnrolledBuffer that) {
      if (!this.lastptr().bind(that.scala$collection$mutable$UnrolledBuffer$$headptr())) {
         this.lastptr_$eq(that.lastPtr());
      }

      this.sz_$eq(this.sz() + that.sz());
      that.clear();
      return this;
   }

   public UnrolledBuffer addOne(final Object elem) {
      this.lastptr_$eq(this.lastptr().append(elem));
      this.sz_$eq(this.sz() + 1);
      return this;
   }

   public void clear() {
      this.headptr_$eq(this.newUnrolled());
      this.lastptr_$eq(this.scala$collection$mutable$UnrolledBuffer$$headptr());
      this.sz_$eq(0);
   }

   public Iterator iterator() {
      return new AbstractIterator() {
         private int pos = -1;
         private Unrolled node = UnrolledBuffer.this.scala$collection$mutable$UnrolledBuffer$$headptr();

         private int pos() {
            return this.pos;
         }

         private void pos_$eq(final int x$1) {
            this.pos = x$1;
         }

         private Unrolled node() {
            return this.node;
         }

         private void node_$eq(final Unrolled x$1) {
            this.node = x$1;
         }

         private void scan() {
            this.pos_$eq(this.pos() + 1);

            while(this.pos() >= this.node().size()) {
               this.pos_$eq(0);
               this.node_$eq(this.node().next());
               if (this.node() == null) {
                  return;
               }
            }

         }

         public boolean hasNext() {
            return this.node() != null;
         }

         public Object next() {
            if (this.hasNext()) {
               Object r = ScalaRunTime$.MODULE$.array_apply(this.node().array(), this.pos());
               this.scan();
               return r;
            } else {
               Iterator$ var10000 = Iterator$.MODULE$;
               return Iterator$.scala$collection$Iterator$$_empty.next();
            }
         }

         public {
            this.scan();
         }
      };
   }

   public void foreach(final Function1 f) {
      this.scala$collection$mutable$UnrolledBuffer$$headptr().foreach(f);
   }

   public UnrolledBuffer result() {
      return this;
   }

   public int length() {
      return this.sz();
   }

   public int knownSize() {
      return this.sz();
   }

   public Object apply(final int idx) {
      if (idx >= 0 && idx < this.sz()) {
         return this.scala$collection$mutable$UnrolledBuffer$$headptr().apply(idx);
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.sz() - 1);
      }
   }

   public void update(final int idx, final Object newelem) {
      if (idx >= 0 && idx < this.sz()) {
         this.scala$collection$mutable$UnrolledBuffer$$headptr().update(idx, newelem);
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.sz() - 1);
      }
   }

   public UnrolledBuffer mapInPlace(final Function1 f) {
      this.scala$collection$mutable$UnrolledBuffer$$headptr().mapInPlace(f);
      return this;
   }

   public Object remove(final int idx) {
      if (idx >= 0 && idx < this.sz()) {
         this.sz_$eq(this.sz() - 1);
         return this.scala$collection$mutable$UnrolledBuffer$$headptr().remove(idx, this);
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.sz() - 1);
      }
   }

   public final void remove(final int idx, final int count) {
      while(count > 0) {
         this.remove(idx);
         --count;
         idx = idx;
      }

   }

   public UnrolledBuffer prepend(final Object elem) {
      this.headptr_$eq(this.scala$collection$mutable$UnrolledBuffer$$headptr().prepend(elem));
      this.sz_$eq(this.sz() + 1);
      return this;
   }

   public void insert(final int idx, final Object elem) {
      List $colon$colon_this = Nil$.MODULE$;
      $colon$colon var10002 = new $colon$colon(elem, $colon$colon_this);
      $colon$colon_this = null;
      this.insertAll(idx, var10002);
   }

   public void insertAll(final int idx, final IterableOnce elems) {
      if (idx >= 0 && idx <= this.sz()) {
         this.sz_$eq(this.sz() + this.scala$collection$mutable$UnrolledBuffer$$headptr().insertAll(idx, elems, this));
      } else {
         throw CommonErrors$.MODULE$.indexOutOfBounds(idx, this.sz() - 1);
      }
   }

   public UnrolledBuffer subtractOne(final Object elem) {
      if (this.scala$collection$mutable$UnrolledBuffer$$headptr().subtractOne(elem, this)) {
         this.sz_$eq(this.sz() - 1);
      }

      return this;
   }

   public UnrolledBuffer patchInPlace(final int from, final IterableOnce patch, final int replaced) {
      this.remove(from, replaced);
      this.insertAll(from, patch);
      return this;
   }

   private void writeObject(final ObjectOutputStream out) {
      out.defaultWriteObject();
      out.writeInt(this.sz());
      this.foreach((elem) -> {
         $anonfun$writeObject$1(out, elem);
         return BoxedUnit.UNIT;
      });
   }

   private void readObject(final ObjectInputStream in) {
      in.defaultReadObject();
      int num = in.readInt();
      this.headPtr_$eq(this.newUnrolled());
      this.lastPtr_$eq(this.headPtr());
      this.sz_$eq(0);

      for(int i = 0; i < num; ++i) {
         Object $plus$eq_elem = in.readObject();
         this.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

   }

   public UnrolledBuffer clone() {
      return (UnrolledBuffer)(new UnrolledBuffer(this.tag())).addAll(this);
   }

   public String className() {
      return "UnrolledBuffer";
   }

   // $FF: synthetic method
   public static final void $anonfun$writeObject$1(final ObjectOutputStream out$1, final Object elem) {
      out$1.writeObject(elem);
   }

   public UnrolledBuffer(final ClassTag tag) {
      this.tag = tag;
      this.scala$collection$mutable$UnrolledBuffer$$headptr = this.newUnrolled();
      this.lastptr = this.scala$collection$mutable$UnrolledBuffer$$headptr();
      this.sz = 0;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class Unrolled {
      private int size;
      private Object array;
      private Unrolled next;
      private final UnrolledBuffer buff;
      private final ClassTag evidence$4;

      public int size() {
         return this.size;
      }

      public void size_$eq(final int x$1) {
         this.size = x$1;
      }

      public Object array() {
         return this.array;
      }

      public void array_$eq(final Object x$1) {
         this.array = x$1;
      }

      public Unrolled next() {
         return this.next;
      }

      public void next_$eq(final Unrolled x$1) {
         this.next = x$1;
      }

      public UnrolledBuffer buff() {
         return this.buff;
      }

      private int nextlength() {
         return this.buff() == null ? UnrolledBuffer$.MODULE$.unrolledlength() : this.buff().calcNextLength(Array.getLength(this.array()));
      }

      public final Unrolled append(final Object elem) {
         while(this.size() >= Array.getLength(this.array())) {
            this.next_$eq(new Unrolled(0, this.evidence$4.newArray(this.nextlength()), (Unrolled)null, this.buff(), this.evidence$4));
            Unrolled var10000 = this.next();
            elem = elem;
            this = var10000;
         }

         ScalaRunTime$.MODULE$.array_update(this.array(), this.size(), elem);
         this.size_$eq(this.size() + 1);
         return this;
      }

      public void foreach(final Function1 f) {
         Unrolled unrolled = this;

         for(int i = 0; unrolled != null; unrolled = unrolled.next()) {
            Object chunkarr = unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               Object elem = ScalaRunTime$.MODULE$.array_apply(chunkarr, i);
               f.apply(elem);
            }

            i = 0;
         }

      }

      public void mapInPlace(final Function1 f) {
         Unrolled unrolled = this;

         for(int i = 0; unrolled != null; unrolled = unrolled.next()) {
            Object chunkarr = unrolled.array();

            for(int chunksz = unrolled.size(); i < chunksz; ++i) {
               Object elem = ScalaRunTime$.MODULE$.array_apply(chunkarr, i);
               ScalaRunTime$.MODULE$.array_update(chunkarr, i, f.apply(elem));
            }

            i = 0;
         }

      }

      public final Object apply(final int idx) {
         while(idx >= this.size()) {
            Unrolled var10000 = this.next();
            idx -= this.size();
            this = var10000;
         }

         return ScalaRunTime$.MODULE$.array_apply(this.array(), idx);
      }

      public final void update(final int idx, final Object newelem) {
         while(idx >= this.size()) {
            Unrolled var10000 = this.next();
            int var10001 = idx - this.size();
            newelem = newelem;
            idx = var10001;
            this = var10000;
         }

         ScalaRunTime$.MODULE$.array_update(this.array(), idx, newelem);
      }

      public final Unrolled locate(final int idx) {
         while(idx >= this.size()) {
            Unrolled var10000 = this.next();
            idx -= this.size();
            this = var10000;
         }

         return this;
      }

      public Unrolled prepend(final Object elem) {
         if (this.size() < Array.getLength(this.array())) {
            this.shiftright();
            ScalaRunTime$.MODULE$.array_update(this.array(), 0, elem);
            this.size_$eq(this.size() + 1);
            return this;
         } else {
            Unrolled newhead = new Unrolled(this.buff(), this.evidence$4);
            newhead.append(elem);
            newhead.next_$eq(this);
            return newhead;
         }
      }

      private void shiftright() {
         for(int i = this.size() - 1; i >= 0; --i) {
            ScalaRunTime$.MODULE$.array_update(this.array(), i + 1, ScalaRunTime$.MODULE$.array_apply(this.array(), i));
         }

      }

      public final Object remove(final int idx, final UnrolledBuffer buffer) {
         while(idx >= this.size()) {
            Unrolled var10000 = this.next();
            int var10001 = idx - this.size();
            buffer = buffer;
            idx = var10001;
            this = var10000;
         }

         Object r = ScalaRunTime$.MODULE$.array_apply(this.array(), idx);
         this.shiftleft(idx);
         this.size_$eq(this.size() - 1);
         if (this.tryMergeWithNext()) {
            buffer.lastPtr_$eq(this);
         }

         return r;
      }

      public final boolean subtractOne(final Object elem, final UnrolledBuffer buffer) {
         while(true) {
            for(int i = 0; i < this.size(); ++i) {
               if (BoxesRunTime.equals(ScalaRunTime$.MODULE$.array_apply(this.array(), i), elem)) {
                  this.remove(i, buffer);
                  return true;
               }
            }

            if (this.next() == null) {
               return false;
            }

            Unrolled var10000 = this.next();
            buffer = buffer;
            elem = elem;
            this = var10000;
         }
      }

      private void shiftleft(final int leftb) {
         int i;
         for(i = leftb; i < this.size() - 1; ++i) {
            ScalaRunTime$.MODULE$.array_update(this.array(), i, ScalaRunTime$.MODULE$.array_apply(this.array(), i + 1));
         }

         this.nullout(i, i + 1);
      }

      public boolean tryMergeWithNext() {
         if (this.next() != null) {
            int var10000 = this.size() + this.next().size();
            int var10001 = Array.getLength(this.array()) * UnrolledBuffer$.MODULE$.waterline();
            UnrolledBuffer$ var10002 = UnrolledBuffer$.MODULE$;
            if (var10000 < var10001 / 100) {
               Array$.MODULE$.copy(this.next().array(), 0, this.array(), this.size(), this.next().size());
               this.size_$eq(this.size() + this.next().size());
               this.next_$eq(this.next().next());
               if (this.next() == null) {
                  return true;
               }

               return false;
            }
         }

         return false;
      }

      public final int insertAll(final int idx, final IterableOnce t, final UnrolledBuffer buffer) {
         while(idx >= this.size()) {
            if (idx == this.size() || this.next() == null) {
               ObjectRef curr = new ObjectRef(this);
               int create_e = 0;
               IntRef appended = new IntRef(create_e);
               t.iterator().foreach((elem) -> {
                  $anonfun$insertAll$2(curr, appended, elem);
                  return BoxedUnit.UNIT;
               });
               return appended.elem;
            }

            Unrolled var10000 = this.next();
            int var10001 = idx - this.size();
            buffer = buffer;
            t = t;
            idx = var10001;
            this = var10000;
         }

         Unrolled newnextnode = new Unrolled(0, this.evidence$4.newArray(Array.getLength(this.array())), (Unrolled)null, this.buff(), this.evidence$4);
         Array$.MODULE$.copy(this.array(), idx, newnextnode.array(), 0, this.size() - idx);
         newnextnode.size_$eq(this.size() - idx);
         newnextnode.next_$eq(this.next());
         this.nullout(idx, this.size());
         this.size_$eq(idx);
         this.next_$eq((Unrolled)null);
         ObjectRef curr = new ObjectRef(this);
         int create_e = 0;
         IntRef appended = new IntRef(create_e);
         t.iterator().foreach((elem) -> {
            $anonfun$insertAll$1(curr, appended, elem);
            return BoxedUnit.UNIT;
         });
         ((Unrolled)curr.elem).next_$eq(newnextnode);
         if (((Unrolled)curr.elem).tryMergeWithNext()) {
            buffer.lastPtr_$eq((Unrolled)curr.elem);
         } else if (newnextnode.next() == null) {
            buffer.lastPtr_$eq(newnextnode);
         }

         return appended.elem;
      }

      private void nullout(final int from, final int until) {
         for(int idx = from; idx < until; ++idx) {
            ScalaRunTime$.MODULE$.array_update(this.array(), idx, (Object)null);
         }

      }

      public boolean bind(final Unrolled thathead) {
         Predef$.MODULE$.assert(this.next() == null);
         this.next_$eq(thathead);
         return this.tryMergeWithNext();
      }

      public String toString() {
         java.lang.StringBuilder var10000 = new java.lang.StringBuilder(4);
         Predef$ var10001 = Predef$.MODULE$;
         ArrayOps$ var10002 = ArrayOps$.MODULE$;
         Object var10003 = this.array();
         int take$extension_n = this.size();
         ArraySeq var8 = var10001.genericWrapArray(var10002.slice$extension(var10003, 0, take$extension_n));
         String var10 = (new java.lang.StringBuilder(4)).append(StringOps$.MODULE$.format$extension("Unrolled@%08x", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{System.identityHashCode(this)}))).append("[").append(this.size()).append("/").append(Array.getLength(this.array())).append("](").toString();
         String mkString_end = ")";
         String mkString_sep = ", ";
         String mkString_start = var10;
         if (var8 == null) {
            throw null;
         } else {
            String var9 = IterableOnceOps.mkString$(var8, mkString_start, mkString_sep, mkString_end);
            Object var5 = null;
            Object var6 = null;
            Object var7 = null;
            return var10000.append(var9).append(" -> ").append(this.next() != null ? this.next().toString() : "").toString();
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$insertAll$1(final ObjectRef curr$1, final IntRef appended$1, final Object elem) {
         curr$1.elem = ((Unrolled)curr$1.elem).append(elem);
         ++appended$1.elem;
      }

      // $FF: synthetic method
      public static final void $anonfun$insertAll$2(final ObjectRef curr$2, final IntRef appended$2, final Object elem) {
         curr$2.elem = ((Unrolled)curr$2.elem).append(elem);
         ++appended$2.elem;
      }

      public Unrolled(final int size, final Object array, final Unrolled next, final UnrolledBuffer buff, final ClassTag evidence$4) {
         this.size = size;
         this.array = array;
         this.next = next;
         this.buff = buff;
         this.evidence$4 = evidence$4;
         super();
      }

      public Unrolled(final ClassTag evidence$5) {
         this(0, evidence$5.newArray(UnrolledBuffer$.MODULE$.unrolledlength()), (Unrolled)null, (UnrolledBuffer)null, evidence$5);
      }

      public Unrolled(final UnrolledBuffer b, final ClassTag evidence$6) {
         this(0, evidence$6.newArray(UnrolledBuffer$.MODULE$.unrolledlength()), (Unrolled)null, b, evidence$6);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class Unrolled$ {
      public static final Unrolled$ MODULE$ = new Unrolled$();

      public Null$ $lessinit$greater$default$4() {
         return null;
      }
   }
}
