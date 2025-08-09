package scala.collection;

import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.collection.convert.impl.AnyIteratorStepper;
import scala.collection.convert.impl.DoubleIteratorStepper;
import scala.collection.convert.impl.IntIteratorStepper;
import scala.collection.convert.impl.LongIteratorStepper;
import scala.collection.generic.DefaultSerializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011}fa\u0002\u001b6!\u0003\r\tA\u000f\u0005\u0006=\u0002!\ta\u0018\u0005\u0006G\u0002!\t\u0005\u001a\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006w\u0002!\t\u0001 \u0005\b\u0003#\u0001AQCA\n\u0011\u001d\t\u0019\u0007\u0001D\u0001\u0003KBq!a\u001c\u0001\r\u0003\t\t\bC\u0004\u0002~\u0001!\t!a \t\u000f\u0005]\u0005\u0001\"\u0001\u0002\u001a\"9\u0011q\u001e\u0001\u0005B\u0005E\bb\u0002B\u0007\u0001\u0011\u0005!q\u0002\u0004\u0007\u0005/\u0001\u0001B!\u0007\t\u000f\t\u0005D\u0002\"\u0001\u0003d!9!q\r\u0007\u0005\u0002\t%da\u0003B\u0013\u0001A\u0005\u0019\u0011\u0003B\u0014\u0005\u001fBQAX\b\u0005\u0002}CqA!\u000b\u0010\t\u0003\u0011Y\u0003C\u0004\u00034=!\tA!\u000e\t\u000f\t}r\u0002\"\u0011\u0003B!9!\u0011J\b\u0005B\t\u0005\u0003b\u0002B&\u001f\u0011\u0005#Q\n\u0005\b\u0005_\u0002A\u0011\u0001B9\u0011\u001d\u0011I\t\u0001C\u0001\u0005\u0017CqAa$\u0001\t\u0003\u0011Y\u0003C\u0004\u0003\u0012\u0002!\tAa%\t\u000f\t]\u0005\u0001\"\u0001\u0003\u001a\"9!Q\u0016\u0001\u0005\u0002\t=\u0006b\u0002Bd\u0001\u0011\u0005!\u0011\u001a\u0005\b\u0003\u001f\u0003A\u0011\u0001Bp\u0011\u001d\u0011\u0019\u0004\u0001C\u0001\u0005_DqAa=\u0001\t\u0003\u0011)\u0010C\u0004\u0003z\u0002!\tAa?\t\u000f\r=\u0001\u0001\"\u0001\u0004\u0012!91q\u0005\u0001\u0005\u0002\r%\u0002bBB\"\u0001\u0011\u00051Q\t\u0005\b\u0007/\u0002A\u0011AB-\u0011\u001d\u0019Y\u0007\u0001C!\u0007[Bqa!$\u0001\t\u0003\u0019y\tC\u0004\u0004\u000e\u0002!\ta!*\t\u000f\r%\u0007\u0001\"\u0001\u0004L\"91\u0011\u001c\u0001\u0005\u0002\rmwaBByk!\u000511\u001f\u0004\u0007iUB\ta!>\t\u000f\t\u00054\u0006\"\u0001\u0004x\u001a11\u0011`\u0016\u0001\u0007wD!\u0002b\n.\u0005\u0003\u0005\u000b\u0011\u0002C\u0015\u0011)\u0011\u0019,\fB\u0001B\u0003%A\u0011\r\u0005\b\u0005CjC\u0011\u0001C2\u0011\u001d\u0011I0\fC\u0001\t\u0003Cqaa\n.\t\u0003!)\nC\u0004\u0005,6\"\t\u0005\",\u0003\r5\u000b\u0007o\u00149t\u0015\t1t'\u0001\u0006d_2dWm\u0019;j_:T\u0011\u0001O\u0001\u0006g\u000e\fG.Y\u0002\u0001+\u0019Y\u0014jUA\r3N!\u0001\u0001\u0010!\\!\tid(D\u00018\u0013\tytG\u0001\u0004B]f\u0014VM\u001a\t\u0006\u0003\n#U\u000bW\u0007\u0002k%\u00111)\u000e\u0002\f\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0003>\u000b\u001e\u0013\u0016B\u0001$8\u0005\u0019!V\u000f\u001d7feA\u0011\u0001*\u0013\u0007\u0001\t\u0015Q\u0005A1\u0001L\u0005\u0005Y\u0015C\u0001'P!\tiT*\u0003\u0002Oo\t9aj\u001c;iS:<\u0007CA\u001fQ\u0013\t\tvGA\u0002B]f\u0004\"\u0001S*\u0005\rQ\u0003AQ1\u0001L\u0005\u00051\u0006CA!W\u0013\t9VG\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\tA\u0015\f\u0002\u0004[\u0001\u0011\u0015\ra\u0013\u0002\u0002\u0007B!Q\bX$S\u0013\tivGA\bQCJ$\u0018.\u00197Gk:\u001cG/[8o\u0003\u0019!\u0013N\\5uIQ\t\u0001\r\u0005\u0002>C&\u0011!m\u000e\u0002\u0005+:LG/\u0001\u0003wS\u0016<X#A3\u0011\t\u00053wIU\u0005\u0003OV\u0012q!T1q-&,w/\u0001\u0006lKf\u001cF/\u001a9qKJ,\"A\u001b7\u0015\u0005-4\bC\u0001%m\t\u0015i7A1\u0001o\u0005\u0005\u0019\u0016C\u0001'pa\t\u0001H\u000fE\u0002BcNL!A]\u001b\u0003\u000fM#X\r\u001d9feB\u0011\u0001\n\u001e\u0003\nk2\f\t\u0011!A\u0003\u0002-\u00131a\u0018\u00134\u0011\u001598\u0001q\u0001y\u0003\u0015\u0019\b.\u00199f!\u0011\t\u0015pR6\n\u0005i,$\u0001D*uKB\u0004XM]*iCB,\u0017\u0001\u0004<bYV,7\u000b^3qa\u0016\u0014XCA?\u0000)\rq\u0018Q\u0002\t\u0003\u0011~$a!\u001c\u0003C\u0002\u0005\u0005\u0011c\u0001'\u0002\u0004A\"\u0011QAA\u0005!\u0011\t\u0015/a\u0002\u0011\u0007!\u000bI\u0001\u0002\u0006\u0002\f}\f\t\u0011!A\u0003\u0002-\u00131a\u0018\u00135\u0011\u00199H\u0001q\u0001\u0002\u0010A!\u0011)\u001f*\u007f\u0003=i\u0017\r\u001d$s_6LE/\u001a:bE2,WCBA\u000b\u0003\u0013\ny\u0005\u0006\u0003\u0002\u0018\u0005M\u0003c\u0002%\u0002\u001a\u0005\u001d\u0013Q\n\u0003\t\u00037\u0001AQ1\u0001\u0002\u001e\t\u00111iQ\u000b\u0007\u0003?\ty$a\u0011\u0012\u00071\u000b\t\u0003\r\u0004\u0002$\u0005\u001d\u00121\b\t\t\u0003\n\u000b)#a\u000b\u0002:A\u0019\u0001*a\n\u0005\u0017\u0005%\u0012\u0011DA\u0001\u0002\u0003\u0015\ta\u0013\u0002\u0004?\u0012\n\u0004\u0003BA\u0017\u0003gq1!QA\u0018\u0013\r\t\t$N\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t)$a\u000e\u0003\u0013\u0005s\u0017pQ8ogR\u0014(bAA\u0019kA\u0019\u0001*a\u000f\u0005\u0017\u0005u\u0012\u0011DA\u0001\u0002\u0003\u0015\ta\u0013\u0002\u0004?\u0012\u0012DaBA!\u00033\u0011\ra\u0013\u0002\u0005?\u0012\"\u0013\u0007B\u0004\u0002F\u0005e!\u0019A&\u0003\t}#CE\r\t\u0004\u0011\u0006%CABA&\u000b\t\u00071J\u0001\u0002LeA\u0019\u0001*a\u0014\u0005\r\u0005ESA1\u0001L\u0005\t1&\u0007C\u0004\u0002V\u0015\u0001\r!a\u0016\u0002\u0005%$\b\u0003B!W\u00033\u0002b!P#\u0002H\u00055\u0003fA\u0003\u0002^A\u0019Q(a\u0018\n\u0007\u0005\u0005tG\u0001\u0004j]2Lg.Z\u0001\u000b[\u0006\u0004h)Y2u_JLXCAA4!\u0015\t\u0015\u0011NA7\u0013\r\tY'\u000e\u0002\u000b\u001b\u0006\u0004h)Y2u_JL\bc\u0001%\u0002\u001a\u0005\u0019q-\u001a;\u0015\t\u0005M\u0014\u0011\u0010\t\u0005{\u0005U$+C\u0002\u0002x]\u0012aa\u00149uS>t\u0007BBA>\u000f\u0001\u0007q)A\u0002lKf\f\u0011bZ3u\u001fJ,En]3\u0016\t\u0005\u0005\u0015Q\u0011\u000b\u0007\u0003\u0007\u000bY)!$\u0011\u0007!\u000b)\tB\u0004\u0002\b\"\u0011\r!!#\u0003\u0005Y\u000b\u0014C\u0001*P\u0011\u0019\tY\b\u0003a\u0001\u000f\"A\u0011q\u0012\u0005\u0005\u0002\u0004\t\t*A\u0004eK\u001a\fW\u000f\u001c;\u0011\u000bu\n\u0019*a!\n\u0007\u0005UuG\u0001\u0005=Eft\u0017-\\3?\u0003\u0015\t\u0007\u000f\u001d7z)\r\u0011\u00161\u0014\u0005\u0007\u0003wJ\u0001\u0019A$)\u000b%\ty*!-\u0011\u000bu\n\t+!*\n\u0007\u0005\rvG\u0001\u0004uQJ|wo\u001d\t\u0005\u0003O\u000bYKD\u0002>\u0003SK1!!\r8\u0013\u0011\ti+a,\u0003-9{7+^2i\u000b2,W.\u001a8u\u000bb\u001cW\r\u001d;j_:T1!!\r8c\u001dq\u00121WAe\u0003[\u0004B!!.\u0002D:!\u0011qWA`!\r\tIlN\u0007\u0003\u0003wS1!!0:\u0003\u0019a$o\\8u}%\u0019\u0011\u0011Y\u001c\u0002\rA\u0013X\rZ3g\u0013\u0011\t)-a2\u0003\rM#(/\u001b8h\u0015\r\t\tmN\u0019\nG\u0005-\u00171[Ar\u0003+,B!!4\u0002PV\u0011\u00111\u0017\u0003\b\u0003#L$\u0019AAn\u0005\u0005!\u0016\u0002BAk\u0003/\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\n$bAAmo\u00051A\u000f\u001b:poN\f2\u0001TAo!\u0011\t9+a8\n\t\u0005\u0005\u0018q\u0016\u0002\n)\"\u0014xn^1cY\u0016\f\u0014bIAs\u0003O\fI/!7\u000f\u0007u\n9/C\u0002\u0002Z^\nTAI\u001f8\u0003W\u0014Qa]2bY\u0006\f4AJAS\u0003-\t\u0007\u000f\u001d7z\u001fJ,En]3\u0016\r\u0005M\u0018q`A|)\u0019\t)0!?\u0003\u0006A\u0019\u0001*a>\u0005\u000f\u0005\u001d%B1\u0001\u0002\n\"9\u00111 \u0006A\u0002\u0005u\u0018!\u0001=\u0011\u0007!\u000by\u0010B\u0004\u0003\u0002)\u0011\rAa\u0001\u0003\u0005-\u000b\u0014C\u0001'H\u0011\u001d\tyI\u0003a\u0001\u0005\u000f\u0001r!\u0010B\u0005\u0003{\f)0C\u0002\u0003\f]\u0012\u0011BR;oGRLwN\\\u0019\u0002\r-,\u0017pU3u+\t\u0011\t\u0002\u0005\u0003B\u0005'9\u0015b\u0001B\u000bk\t\u00191+\u001a;\u0003\r-+\u0017pU3u'\u001da!1\u0004B\u0011\u0005+\u0002B!\u0011B\u000f\u000f&\u0019!qD\u001b\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV\r\u001e\t\u0004\u0005GyQ\"\u0001\u0001\u0003\u0013\u001d+gnS3z'\u0016$8CA\b=\u0003!IG/\u001a:bi>\u0014XC\u0001B\u0017!\u0011\t%qF$\n\u0007\tERG\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019wN\u001c;bS:\u001cH\u0003\u0002B\u001c\u0005{\u00012!\u0010B\u001d\u0013\r\u0011Yd\u000e\u0002\b\u0005>|G.Z1o\u0011\u0019\tYH\u0005a\u0001\u000f\u0006!1/\u001b>f+\t\u0011\u0019\u0005E\u0002>\u0005\u000bJ1Aa\u00128\u0005\rIe\u000e^\u0001\nW:|wO\\*ju\u0016\fq![:F[B$\u00180\u0006\u0002\u00038I1!\u0011\u000bB\u0011\u0005#1aAa\u0015\u0001\u0001\t=#\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004\u0003\u0002B,\u0005;j!A!\u0017\u000b\u0007\tmS'A\u0004hK:,'/[2\n\t\t}#\u0011\f\u0002\u0014\t\u00164\u0017-\u001e7u'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\u0015\u0004c\u0001B\u0012\u0019\u0005!A-\u001b4g)\u0011\u0011\tBa\u001b\t\u000f\t5d\u00021\u0001\u0003\u0012\u0005!A\u000f[1u\u0003\u0011YW-_:\u0016\u0005\tM\u0004cA!W\u000f\"ZaCa\u001e\u0003~\t}$1\u0011BC!\ri$\u0011P\u0005\u0004\u0005w:$\u0001\u00063faJ,7-\u0019;fI>3XM\u001d:jI&tw-A\u0004nKN\u001c\u0018mZ3\"\u0005\t\u0005\u0015!\u000b+iSN\u0004S.\u001a;i_\u0012\u00043\u000f[8vY\u0012\u0004#-\u001a\u0011b]\u0002\nG.[1tA\u0019|'\u000fI6fsN+G/A\u0003tS:\u001cW-\t\u0002\u0003\b\u00069!GL\u00194]E\u001a\u0014A\u0002<bYV,7/\u0006\u0002\u0003\u000eB\u0019\u0011I\u0016*\u0002\u0019-,\u0017p]%uKJ\fGo\u001c:\u0002\u001dY\fG.^3t\u0013R,'/\u0019;peV\u0011!Q\u0013\t\u0005\u0003\n=\"+\u0001\u0007g_J,\u0017m\u00195F]R\u0014\u00180\u0006\u0003\u0003\u001c\n%Fc\u00011\u0003\u001e\"9!q\u0014\u000eA\u0002\t\u0005\u0016!\u00014\u0011\u000fu\u0012\u0019k\u0012*\u0003(&\u0019!QU\u001c\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004c\u0001%\u0003*\u00121!1\u0016\u000eC\u0002-\u0013\u0011!V\u0001\u000bM&dG/\u001a:LKf\u001cHcA3\u00032\"9!1W\u000eA\u0002\tU\u0016!\u00019\u0011\ru\u0012Ia\u0012B\u001cQ-Y\"\u0011\u0018B?\u0005\u007f\u0013\u0019Ia1\u0011\u0007u\u0012Y,C\u0002\u0003>^\u0012!\u0002Z3qe\u0016\u001c\u0017\r^3eC\t\u0011\t-\u0001?Vg\u0016\u0004cF^5fo:2\u0017\u000e\u001c;fe.+\u0017p\u001d\u0015gS9\u0002\u0013\t\t4viV\u0014X\r\t<feNLwN\u001c\u0011xS2d\u0007%\u001b8dYV$W\rI1!gR\u0014\u0018n\u0019;!m\u0016\u00148/[8oA=4\u0007\u0005\u001e5jg\u0002jW\r\u001e5pI\u0002Bcm\u001c:!]><H\u0006\t\u0018wS\u0016<hFZ5mi\u0016\u00148*Z=tQALc\u0006^8NCBLc&\t\u0002\u0003F\u00061!GL\u00194]A\n\u0011\"\\1q-\u0006dW/Z:\u0016\t\t-'\u0011\u001b\u000b\u0005\u0005\u001b\u0014)\u000eE\u0003BM\u001e\u0013y\rE\u0002I\u0005#$aAa5\u001d\u0005\u0004Y%!A,\t\u000f\t}E\u00041\u0001\u0003XB1QH!\u0003S\u0005\u001fD3\u0002\bB]\u0005{\u0012YNa!\u0003D\u0006\u0012!Q\\\u0001{+N,\u0007E\f<jK^tS.\u00199WC2,Xm\u001d\u0015gS9\u0002\u0013\t\t4viV\u0014X\r\t<feNLwN\u001c\u0011xS2d\u0007%\u001b8dYV$W\rI1!gR\u0014\u0018n\u0019;!m\u0016\u00148/[8oA=4\u0007\u0005\u001e5jg\u0002jW\r\u001e5pI\u0002Bcm\u001c:!]><H\u0006\t\u0018wS\u0016<h&\\1q-\u0006dW/Z:)M&rCo\\'ba&rCc\u0001*\u0003b\"1\u00111P\u000fA\u0002\u001dCS!HAP\u0005K\ftAHAZ\u0005O\u0014i/M\u0005$\u0003\u0017\f\u0019N!;\u0002VFJ1%!:\u0002h\n-\u0018\u0011\\\u0019\u0006Eu:\u00141^\u0019\u0004M\u0005\u0015F\u0003\u0002B\u001c\u0005cDa!a\u001f\u001f\u0001\u00049\u0015aC5t\t\u00164\u0017N\\3e\u0003R$BAa\u000e\u0003x\"1\u00111P\u0010A\u0002\u001d\u000b1!\\1q+\u0019\u0011ipa\u0001\u0004\bQ!!q`B\u0005!\u001dA\u0015\u0011DB\u0001\u0007\u000b\u00012\u0001SB\u0002\t\u0019\tY\u0005\tb\u0001\u0017B\u0019\u0001ja\u0002\u0005\r\u0005E\u0003E1\u0001L\u0011\u001d\u0011y\n\ta\u0001\u0007\u0017\u0001b!\u0010B\u0005\t\u000e5\u0001CB\u001fF\u0007\u0003\u0019)!A\u0004d_2dWm\u0019;\u0016\r\rM1\u0011DB\u000f)\u0011\u0019)ba\b\u0011\u000f!\u000bIba\u0006\u0004\u001cA\u0019\u0001j!\u0007\u0005\r\u0005-\u0013E1\u0001L!\rA5Q\u0004\u0003\u0007\u0003#\n#\u0019A&\t\u000f\r\u0005\u0012\u00051\u0001\u0004$\u0005\u0011\u0001O\u001a\t\u0006{q#5Q\u0005\t\u0007{\u0015\u001b9ba\u0007\u0002\u000f\u0019d\u0017\r^'baV111FB\u0019\u0007k!Ba!\f\u00048A9\u0001*!\u0007\u00040\rM\u0002c\u0001%\u00042\u00111\u00111\n\u0012C\u0002-\u00032\u0001SB\u001b\t\u0019\t\tF\tb\u0001\u0017\"9!q\u0014\u0012A\u0002\re\u0002CB\u001f\u0003\n\u0011\u001bY\u0004E\u0003B\u0007{\u0019\t%C\u0002\u0004@U\u0012A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\u0004b!P#\u00040\rM\u0012AB2p]\u000e\fG/\u0006\u0003\u0004H\r5C\u0003BB%\u0007\u001f\u0002b\u0001SA\r\u000f\u000e-\u0003c\u0001%\u0004N\u00119\u0011\u0011K\u0012C\u0002\u0005%\u0005bBB)G\u0001\u000711K\u0001\u0007gV4g-\u001b=\u0011\u000b\u0005\u001bid!\u0016\u0011\u000bu*uia\u0013\u0002\u0015\u0011\u0002H.^:%a2,8/\u0006\u0003\u0004\\\r\u0005D\u0003BB/\u0007G\u0002b\u0001SA\r\u000f\u000e}\u0003c\u0001%\u0004b\u00119\u0011\u0011\u000b\u0013C\u0002\u0005%\u0005bBB3I\u0001\u00071qM\u0001\u0003qN\u0004R!QB\u001f\u0007S\u0002R!P#H\u0007?\n\u0011\"\u00193e'R\u0014\u0018N\\4\u0015\u0015\r=4\u0011OBA\u0007\u000b\u001bIID\u0002I\u0007cBqaa\u001d&\u0001\u0004\u0019)(\u0001\u0002tEB!1qOB?\u001b\t\u0019IHC\u0002\u0004|U\nq!\\;uC\ndW-\u0003\u0003\u0004\u0000\re$!D*ue&twMQ;jY\u0012,'\u000fC\u0004\u0004\u0004\u0016\u0002\r!a-\u0002\u000bM$\u0018M\u001d;\t\u000f\r\u001dU\u00051\u0001\u00024\u0006\u00191/\u001a9\t\u000f\r-U\u00051\u0001\u00024\u0006\u0019QM\u001c3\u0002\u000b\u0011\u0002H.^:\u0016\t\rE5q\u0013\u000b\u0005\u0007'\u001bI\n\u0005\u0004I\u0003395Q\u0013\t\u0004\u0011\u000e]EaBADM\t\u0007\u0011\u0011\u0012\u0005\b\u000773\u0003\u0019ABO\u0003\tYg\u000fE\u0003>\u000b\u001e\u001b)\nK\u0006'\u0005s\u0013ih!)\u0003\u0004\n\r\u0017EABR\u0003}\u001auN\\:jI\u0016\u0014\bE]3rk&\u0014\u0018N\\4!C:\u0004\u0013.\\7vi\u0006\u0014G.\u001a\u0011NCB\u0004sN\u001d\u0011gC2d\u0007EY1dW\u0002\"x\u000eI'ba:\u001awN\\2bi:*Baa*\u0004.RA1\u0011VBX\u0007k\u001bI\f\u0005\u0004I\u00033951\u0016\t\u0004\u0011\u000e5FaBADO\t\u0007\u0011\u0011\u0012\u0005\b\u0007c;\u0003\u0019ABZ\u0003\u0015)G.Z72!\u0015iTiRBV\u0011\u001d\u00199l\na\u0001\u0007g\u000bQ!\u001a7f[JBqaa/(\u0001\u0004\u0019i,A\u0003fY\u0016l7\u000fE\u0003>\u0007\u007f\u001b\u0019,C\u0002\u0004B^\u0012!\u0002\u0010:fa\u0016\fG/\u001a3?Q-9#\u0011\u0018B?\u0007\u000b\u0014\u0019Ia1\"\u0005\r\u001d\u0017!R+tK\u0002Z3\u0006I<ji\"\u0004\u0013M\u001c\u0011fqBd\u0017nY5uA\r|G\u000e\\3di&|g\u000eI1sOVlWM\u001c;!S:\u001cH/Z1eA=4\u0007e\u000b\u0011xSRD\u0007E^1sCJ<7/\u0001\u0007%[&tWo\u001d\u0013nS:,8\u000fF\u0002Y\u0007\u001bDqAa\u001c)\u0001\u0004\u0019y\r\u0005\u0003B\u0007{9\u0005f\u0003\u0015\u0003:\nu41\u001bBB\u0005\u0007\f#a!6\u0002I\r{gn]5eKJ\u0004#/Z9vSJLgn\u001a\u0011b]\u0002JW.\\;uC\ndW\rI'ba:B3\u0001KA/\u0003A!\u0003\u000f\\;tIAdWo\u001d\u0013d_2|g.\u0006\u0003\u0004^\u000e\rH\u0003BBp\u0007K\u0004b\u0001SA\r\u000f\u000e\u0005\bc\u0001%\u0004d\u00129\u0011qQ\u0015C\u0002\u0005%\u0005b\u0002B7S\u0001\u00071q\u001d\t\u0006\u0003\u000eu2\u0011\u001e\t\u0006{\u0015;5\u0011\u001d\u0015\fS\te&QPBw\u0005\u0007\u0013\u0019-\t\u0002\u0004p\u00061Tk]3!W-\u0002\u0013N\\:uK\u0006$\u0007e\u001c4!W-R\u0004EZ8sA\r|G\u000e\\3di&|gn\u001d\u0011pM\u0002\"\u0018\u0010]3!\u0013R,'/\u00192mK\u00061Q*\u00199PaN\u0004\"!Q\u0016\u0014\u0005-bDCABz\u0005)9\u0016\u000e\u001e5GS2$XM]\u000b\u000b\u0007{$y\u0001b\u0005\u0005\u0018\u0011M2#B\u0017\u0004\u0000\u0012\u0005\u0002\u0003\u0003C\u0001\t\u000f!Y\u0001\"\u0006\u000f\u0007\u0005#\u0019!C\u0002\u0005\u0006U\n1\"\u0013;fe\u0006\u0014G.Z(qg&!1\u0011 C\u0005\u0015\r!)!\u000e\t\u0007{\u0015#i\u0001\"\u0005\u0011\u0007!#y\u0001B\u0003K[\t\u00071\nE\u0002I\t'!a\u0001V\u0017\u0005\u0006\u0004Y\u0005c\u0001%\u0005\u0018\u0011AA\u0011D\u0017\u0005\u0006\u0004!YB\u0001\u0006Ji\u0016\u0014\u0018M\u00197f\u0007\u000e+2a\u0013C\u000f\t\u001d!y\u0002b\u0006C\u0002-\u0013Aa\u0018\u0013%gA!\u0011q\u0015C\u0012\u0013\u0011!)#a,\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\tM,GN\u001a\n\u0007\tW!i\u0003b\u0016\u0007\r\tM3\u0006\u0001C\u0015a\u0011!y\u0003b\u0015\u0011\u0015\u0005\u0003AQ\u0002C\t\tc!\t\u0006E\u0002I\tg!\u0001\"a\u0007.\t\u000b\u0007AQG\u000b\u0007\to!I\u0005\"\u0014\u0012\u00071#I\u0004\r\u0004\u0005<\u0011}BQ\t\t\t\u0003\n#i$a\u000b\u0005DA\u0019\u0001\nb\u0010\u0005\u0017\u0011\u0005C1GA\u0001\u0002\u0003\u0015\ta\u0013\u0002\u0004?\u0012*\u0004c\u0001%\u0005F\u0011YAq\tC\u001a\u0003\u0003\u0005\tQ!\u0001L\u0005\ryFE\u000e\u0003\b\t\u0017\"\u0019D1\u0001L\u0005\u0011yF\u0005\n\u001b\u0005\u000f\u0011=C1\u0007b\u0001\u0017\n!q\f\n\u00136!\rAE1\u000b\u0003\u000b\t+r\u0013\u0011!A\u0001\u0006\u0003Y%aA0%oA\"A\u0011\fC/!!\t%\tb\u0003\u0005\u0016\u0011m\u0003c\u0001%\u0005^\u0011QAq\f\u0018\u0002\u0002\u0003\u0005)\u0011A&\u0003\u0007}#\u0003\bE\u0004>\u0005\u0013!YAa\u000e\u0015\r\u0011\u0015D\u0011\u000eC@!-!9'\fC\u0007\t#!)\u0002\"\r\u000e\u0003-Bq\u0001b\n1\u0001\u0004!YG\u0005\u0004\u0005n\u0011=Dq\u000f\u0004\u0007\u0005'Z\u0003\u0001b\u001b1\t\u0011EDQ\u000f\t\u000b\u0003\u0002!i\u0001\"\u0005\u00052\u0011M\u0004c\u0001%\u0005v\u0011YAQ\u000bC5\u0003\u0003\u0005\tQ!\u0001La\u0011!I\b\" \u0011\u0011\u0005\u0013E1\u0002C\u000b\tw\u00022\u0001\u0013C?\t-!y\u0006\"\u001b\u0002\u0002\u0003\u0005)\u0011A&\t\u000f\tM\u0006\u00071\u0001\u0005bU1A1\u0011CE\t\u001b#B\u0001\"\"\u0005\u0010B9\u0001\nb\r\u0005\b\u0012-\u0005c\u0001%\u0005\n\u00121\u00111J\u0019C\u0002-\u00032\u0001\u0013CG\t\u0019\t\t&\rb\u0001\u0017\"9!qT\u0019A\u0002\u0011E\u0005cB\u001f\u0003\n\u0011-A1\u0013\t\u0007{\u0015#9\tb#\u0016\r\u0011]EQ\u0014CQ)\u0011!I\nb)\u0011\u000f!#\u0019\u0004b'\u0005 B\u0019\u0001\n\"(\u0005\r\u0005-#G1\u0001L!\rAE\u0011\u0015\u0003\u0007\u0003#\u0012$\u0019A&\t\u000f\t}%\u00071\u0001\u0005&B9QH!\u0003\u0005\f\u0011\u001d\u0006#B!\u0004>\u0011%\u0006CB\u001fF\t7#y*\u0001\u0006xSRDg)\u001b7uKJ$B\u0001\"\u001a\u00050\"9A\u0011W\u001aA\u0002\u0011\u0005\u0014!A9)\u000f5\")\fb/\u0005>B\u0019Q\bb.\n\u0007\u0011evG\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\t\u0006)a/\u00197vKz\t1\u0001"
)
public interface MapOps extends IterableOps, PartialFunction {
   // $FF: synthetic method
   static MapView view$(final MapOps $this) {
      return $this.view();
   }

   default MapView view() {
      return new MapView.Id(this);
   }

   // $FF: synthetic method
   static Stepper keyStepper$(final MapOps $this, final StepperShape shape) {
      return $this.keyStepper(shape);
   }

   default Stepper keyStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntIteratorStepper(this.keysIterator());
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongIteratorStepper(this.keysIterator());
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleIteratorStepper(this.keysIterator()) : shape.seqUnbox(new AnyIteratorStepper(this.keysIterator())));
      }
   }

   // $FF: synthetic method
   static Stepper valueStepper$(final MapOps $this, final StepperShape shape) {
      return $this.valueStepper(shape);
   }

   default Stepper valueStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntIteratorStepper(this.valuesIterator());
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongIteratorStepper(this.valuesIterator());
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleIteratorStepper(this.valuesIterator()) : shape.seqUnbox(new AnyIteratorStepper(this.valuesIterator())));
      }
   }

   // $FF: synthetic method
   static IterableOps mapFromIterable$(final MapOps $this, final Iterable it) {
      return $this.mapFromIterable(it);
   }

   default IterableOps mapFromIterable(final Iterable it) {
      return (IterableOps)this.mapFactory().from(it);
   }

   MapFactory mapFactory();

   Option get(final Object key);

   // $FF: synthetic method
   static Object getOrElse$(final MapOps $this, final Object key, final Function0 default) {
      return $this.getOrElse(key, default);
   }

   default Object getOrElse(final Object key, final Function0 default) {
      Option var3 = this.get(key);
      if (var3 instanceof Some) {
         return ((Some)var3).value();
      } else if (None$.MODULE$.equals(var3)) {
         return default.apply();
      } else {
         throw new MatchError(var3);
      }
   }

   // $FF: synthetic method
   static Object apply$(final MapOps $this, final Object key) {
      return $this.apply(key);
   }

   default Object apply(final Object key) throws NoSuchElementException {
      Option var2 = this.get(key);
      if (None$.MODULE$.equals(var2)) {
         return this.default(key);
      } else if (var2 instanceof Some) {
         return ((Some)var2).value();
      } else {
         throw new MatchError(var2);
      }
   }

   // $FF: synthetic method
   static Object applyOrElse$(final MapOps $this, final Object x, final Function1 default) {
      return $this.applyOrElse(x, default);
   }

   default Object applyOrElse(final Object x, final Function1 default) {
      return this.getOrElse(x, () -> default.apply(x));
   }

   // $FF: synthetic method
   static Set keySet$(final MapOps $this) {
      return $this.keySet();
   }

   default Set keySet() {
      return new KeySet();
   }

   // $FF: synthetic method
   static Iterable keys$(final MapOps $this) {
      return $this.keys();
   }

   default Iterable keys() {
      return this.keySet();
   }

   // $FF: synthetic method
   static Iterable values$(final MapOps $this) {
      return $this.values();
   }

   default Iterable values() {
      return new DefaultSerializable() {
         // $FF: synthetic field
         private final MapOps $outer;

         public Object writeReplace() {
            return DefaultSerializable.writeReplace$(this);
         }

         public int knownSize() {
            return this.$outer.knownSize();
         }

         public Iterator iterator() {
            return this.$outer.valuesIterator();
         }

         public {
            if (MapOps.this == null) {
               throw null;
            } else {
               this.$outer = MapOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static Iterator keysIterator$(final MapOps $this) {
      return $this.keysIterator();
   }

   default Iterator keysIterator() {
      return new AbstractIterator() {
         private final Iterator iter = MapOps.this.iterator();

         private Iterator iter() {
            return this.iter;
         }

         public boolean hasNext() {
            return this.iter().hasNext();
         }

         public Object next() {
            return ((Tuple2)this.iter().next())._1();
         }
      };
   }

   // $FF: synthetic method
   static Iterator valuesIterator$(final MapOps $this) {
      return $this.valuesIterator();
   }

   default Iterator valuesIterator() {
      return new AbstractIterator() {
         private final Iterator iter = MapOps.this.iterator();

         private Iterator iter() {
            return this.iter;
         }

         public boolean hasNext() {
            return this.iter().hasNext();
         }

         public Object next() {
            return ((Tuple2)this.iter().next())._2();
         }
      };
   }

   // $FF: synthetic method
   static void foreachEntry$(final MapOps $this, final Function2 f) {
      $this.foreachEntry(f);
   }

   default void foreachEntry(final Function2 f) {
      Iterator it = this.iterator();

      while(it.hasNext()) {
         Tuple2 next = (Tuple2)it.next();
         f.apply(next._1(), next._2());
      }

   }

   // $FF: synthetic method
   static MapView filterKeys$(final MapOps $this, final Function1 p) {
      return $this.filterKeys(p);
   }

   /** @deprecated */
   default MapView filterKeys(final Function1 p) {
      return new MapView.FilterKeys(this, p);
   }

   // $FF: synthetic method
   static MapView mapValues$(final MapOps $this, final Function1 f) {
      return $this.mapValues(f);
   }

   /** @deprecated */
   default MapView mapValues(final Function1 f) {
      return new MapView.MapValues(this, f);
   }

   // $FF: synthetic method
   static Object default$(final MapOps $this, final Object key) {
      return $this.default(key);
   }

   default Object default(final Object key) throws NoSuchElementException {
      throw new NoSuchElementException((new StringBuilder(15)).append("key not found: ").append(key).toString());
   }

   // $FF: synthetic method
   static boolean contains$(final MapOps $this, final Object key) {
      return $this.contains(key);
   }

   default boolean contains(final Object key) {
      return this.get(key).isDefined();
   }

   // $FF: synthetic method
   static boolean isDefinedAt$(final MapOps $this, final Object key) {
      return $this.isDefinedAt(key);
   }

   default boolean isDefinedAt(final Object key) {
      return this.contains(key);
   }

   // $FF: synthetic method
   static IterableOps map$(final MapOps $this, final Function1 f) {
      return $this.map(f);
   }

   default IterableOps map(final Function1 f) {
      return (IterableOps)this.mapFactory().from(new View.Map(this, f));
   }

   // $FF: synthetic method
   static IterableOps collect$(final MapOps $this, final PartialFunction pf) {
      return $this.collect(pf);
   }

   default IterableOps collect(final PartialFunction pf) {
      return (IterableOps)this.mapFactory().from(new View.Collect(this, pf));
   }

   // $FF: synthetic method
   static IterableOps flatMap$(final MapOps $this, final Function1 f) {
      return $this.flatMap(f);
   }

   default IterableOps flatMap(final Function1 f) {
      return (IterableOps)this.mapFactory().from(new View.FlatMap(this, f));
   }

   // $FF: synthetic method
   static IterableOps concat$(final MapOps $this, final IterableOnce suffix) {
      return $this.concat(suffix);
   }

   default IterableOps concat(final IterableOnce suffix) {
      MapFactory var10000 = this.mapFactory();
      Object var10001;
      if (suffix instanceof Iterable) {
         Iterable var2 = (Iterable)suffix;
         var10001 = new View.Concat(this, var2);
      } else {
         var10001 = this.iterator().concat(() -> suffix.iterator());
      }

      return (IterableOps)var10000.from((IterableOnce)var10001);
   }

   // $FF: synthetic method
   static IterableOps $plus$plus$(final MapOps $this, final IterableOnce xs) {
      return $this.$plus$plus(xs);
   }

   default IterableOps $plus$plus(final IterableOnce xs) {
      return this.concat(xs);
   }

   // $FF: synthetic method
   static scala.collection.mutable.StringBuilder addString$(final MapOps $this, final scala.collection.mutable.StringBuilder sb, final String start, final String sep, final String end) {
      return $this.addString(sb, start, sep, end);
   }

   default scala.collection.mutable.StringBuilder addString(final scala.collection.mutable.StringBuilder sb, final String start, final String sep, final String end) {
      return this.iterator().map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            return (new StringBuilder(4)).append(k).append(" -> ").append(v).toString();
         } else {
            throw new MatchError((Object)null);
         }
      }).addString(sb, start, sep, end);
   }

   // $FF: synthetic method
   static IterableOps $plus$(final MapOps $this, final Tuple2 kv) {
      return $this.$plus(kv);
   }

   /** @deprecated */
   default IterableOps $plus(final Tuple2 kv) {
      return (IterableOps)this.mapFactory().from(new View.Appended(this, kv));
   }

   // $FF: synthetic method
   static IterableOps $plus$(final MapOps $this, final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return $this.$plus(elem1, elem2, elems);
   }

   /** @deprecated */
   default IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return (IterableOps)this.mapFactory().from(new View.Concat(new View.Appended(new View.Appended(this, elem1), elem2), elems));
   }

   // $FF: synthetic method
   static Object $minus$minus$(final MapOps $this, final IterableOnce keys) {
      return $this.$minus$minus(keys);
   }

   /** @deprecated */
   default Object $minus$minus(final IterableOnce keys) {
      LazyRef keysSet$lzy = new LazyRef();
      return this.fromSpecific(this.view().filterKeys((k) -> BoxesRunTime.boxToBoolean($anonfun$$minus$minus$1(keysSet$lzy, keys, k))));
   }

   // $FF: synthetic method
   static IterableOps $plus$plus$colon$(final MapOps $this, final IterableOnce that) {
      return $this.$plus$plus$colon(that);
   }

   /** @deprecated */
   default IterableOps $plus$plus$colon(final IterableOnce that) {
      Iterable thatIterable = (Iterable)(that instanceof Iterable ? (Iterable)that : View$.MODULE$.from(that));
      return (IterableOps)this.mapFactory().from(new View.Concat(thatIterable, this));
   }

   // $FF: synthetic method
   private static scala.collection.immutable.Set keysSet$lzycompute$1(final LazyRef keysSet$lzy$1, final IterableOnce keys$1) {
      synchronized(keysSet$lzy$1){}

      scala.collection.immutable.Set var2;
      try {
         scala.collection.immutable.Set var10000;
         if (keysSet$lzy$1.initialized()) {
            var10000 = (scala.collection.immutable.Set)keysSet$lzy$1.value();
         } else {
            Iterator var10001 = keys$1.iterator();
            IterableFactory$ var10002 = IterableFactory$.MODULE$;
            IterableFactory toFactory_factory = scala.collection.immutable.Set$.MODULE$;
            IterableFactory.ToFactory var7 = new IterableFactory.ToFactory(toFactory_factory);
            toFactory_factory = null;
            var10000 = (scala.collection.immutable.Set)keysSet$lzy$1.initialize(var10001.to(var7));
         }

         var2 = var10000;
      } catch (Throwable var5) {
         throw var5;
      }

      return var2;
   }

   private static scala.collection.immutable.Set keysSet$1(final LazyRef keysSet$lzy$1, final IterableOnce keys$1) {
      return keysSet$lzy$1.initialized() ? (scala.collection.immutable.Set)keysSet$lzy$1.value() : keysSet$lzycompute$1(keysSet$lzy$1, keys$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$$minus$minus$1(final LazyRef keysSet$lzy$1, final IterableOnce keys$1, final Object k) {
      return !keysSet$1(keysSet$lzy$1, keys$1).contains(k);
   }

   static void $init$(final MapOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class KeySet extends AbstractSet implements GenKeySet, DefaultSerializable {
      // $FF: synthetic field
      public final MapOps $outer;

      public Object writeReplace() {
         return DefaultSerializable.writeReplace$(this);
      }

      public Iterator iterator() {
         return MapOps.GenKeySet.super.iterator();
      }

      public boolean contains(final Object key) {
         return MapOps.GenKeySet.super.contains(key);
      }

      public int size() {
         return MapOps.GenKeySet.super.size();
      }

      public int knownSize() {
         return MapOps.GenKeySet.super.knownSize();
      }

      public boolean isEmpty() {
         return MapOps.GenKeySet.super.isEmpty();
      }

      public Set diff(final Set that) {
         return (Set)this.fromSpecific((IterableOnce)this.view().filterNot(that));
      }

      // $FF: synthetic method
      public MapOps scala$collection$MapOps$KeySet$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public MapOps scala$collection$MapOps$GenKeySet$$$outer() {
         return this.scala$collection$MapOps$KeySet$$$outer();
      }

      public KeySet() {
         if (MapOps.this == null) {
            throw null;
         } else {
            this.$outer = MapOps.this;
            super();
         }
      }
   }

   public interface GenKeySet {
      default Iterator iterator() {
         return this.scala$collection$MapOps$GenKeySet$$$outer().keysIterator();
      }

      default boolean contains(final Object key) {
         return this.scala$collection$MapOps$GenKeySet$$$outer().contains(key);
      }

      default int size() {
         return this.scala$collection$MapOps$GenKeySet$$$outer().size();
      }

      default int knownSize() {
         return this.scala$collection$MapOps$GenKeySet$$$outer().knownSize();
      }

      default boolean isEmpty() {
         return this.scala$collection$MapOps$GenKeySet$$$outer().isEmpty();
      }

      // $FF: synthetic method
      MapOps scala$collection$MapOps$GenKeySet$$$outer();

      static void $init$(final GenKeySet $this) {
      }
   }

   public static class WithFilter extends IterableOps.WithFilter {
      private static final long serialVersionUID = 3L;
      private final MapOps self;
      private final Function1 p;

      public IterableOps map(final Function1 f) {
         return (IterableOps)this.self.mapFactory().from(new View.Map(this.filtered(), f));
      }

      public IterableOps flatMap(final Function1 f) {
         return (IterableOps)this.self.mapFactory().from(new View.FlatMap(this.filtered(), f));
      }

      public WithFilter withFilter(final Function1 q) {
         return new WithFilter(this.self, (kv) -> BoxesRunTime.boxToBoolean($anonfun$withFilter$1(this, q, kv)));
      }

      // $FF: synthetic method
      public static final boolean $anonfun$withFilter$1(final WithFilter $this, final Function1 q$1, final Tuple2 kv) {
         return BoxesRunTime.unboxToBoolean($this.p.apply(kv)) && BoxesRunTime.unboxToBoolean(q$1.apply(kv));
      }

      public WithFilter(final MapOps self, final Function1 p) {
         super(self, p);
         this.self = self;
         this.p = p;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
