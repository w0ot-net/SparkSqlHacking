package spire.random;

import java.lang.invoke.SerializedLambda;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.java8.JFunction1;
import spire.math.UInt$;
import spire.math.ULong$;
import spire.random.rng.SyncGenerator;

@ScalaSignature(
   bytes = "\u0006\u0005\r5c!\u0002\u001f>\u0003\u0003\u0011\u0005\"B%\u0001\t\u0003Q\u0005bB'\u0001\u0001\u0004%\tB\u0014\u0005\b%\u0002\u0001\r\u0011\"\u0005T\u0011\u0019I\u0006\u0001)Q\u0005\u001f\"9!\f\u0001a\u0001\n#Y\u0006bB0\u0001\u0001\u0004%\t\u0002\u0019\u0005\u0007E\u0002\u0001\u000b\u0015\u0002/\t\u000b\r\u0004A\u0011\u00013\t\r\u0015\u0004\u0001U\"\u0005e\u0011\u00151\u0007\u0001\"\u0001h\u0011\u0015q\u0007A\"\u0001p\u0011\u00151\bA\"\u0001x\u0011\u0015Q\bA\"\u0001|\u0011\u0019y\bA\"\u0001\u0002\u0002!9\u0011\u0011\u0002\u0001\u0005\u0002\u0005-\u0001bBA\u0017\u0001\u0011\u0005\u0011q\u0006\u0005\b\u0003'\u0002A\u0011AA+\u0011\u0019Q\b\u0001\"\u0001\u0002\\!9\u0011q\f\u0001\u0005\u000e\u0005\u0005\u0004B\u0002>\u0001\t\u0003\t\u0019\b\u0003\u0004\u0000\u0001\u0011\u0005\u0011Q\u0010\u0005\b\u0003?\u0002AQBAA\u0011\u0019y\b\u0001\"\u0001\u0002\f\"9\u0011\u0011\u0013\u0001\u0005\u0002\u0005M\u0005bBAK\u0001\u0011\u0005\u0011q\u0013\u0005\b\u0003+\u0003A\u0011AAP\u0011\u001d\t)\n\u0001C\u0001\u0003GCq!a+\u0001\t\u0003\ti\u000bC\u0004\u0002,\u0002!\t!a,\t\u000f\u0005-\u0006\u0001\"\u0001\u00024\"9\u0011\u0011\u0018\u0001\u0005\u0002\u0005m\u0006bBAa\u0001\u0011\u0005\u00111\u0019\u0005\b\u0003\u0013\u0004A\u0011AAf\u0011\u001d\t\t\u000e\u0001C\u0001\u0003'Dq!a6\u0001\t\u0003\tI\u000eC\u0004\u0002f\u0002!\t!a:\t\u000f\u0005-\b\u0001\"\u0001\u0002n\"9\u0011\u0011\u001f\u0001\u0005\u0002\u0005M\bbBA|\u0001\u0011\u0005\u0011\u0011 \u0005\b\u0005O\u0001A\u0011\u0001B\u0015\u0011\u001d\u0011y\u0004\u0001C\u0001\u0005\u0003BqAa\u0015\u0001\t\u0003\u0011)\u0006C\u0004\u0003j\u0001!\tAa\u001b\t\u000f\t\u0005\u0005\u0001\"\u0001\u0003\u0004\"9!q\u0013\u0001\u0005\u0002\te\u0005b\u0002B[\u0001\u0011\u0005!q\u0017\u0005\b\u0005'\u0004A\u0011\u0001Bk\u0011\u001d\u0019)\u0001\u0001C\u0001\u0007\u000fAaa!\u0007\u0001\t\u0003Y\u0006bBB\r\u0001\u0011\u000511\u0004\u0005\b\u0007K\u0001A\u0011AB\u0014\u0011\u001d\u0019)\u0003\u0001C\u0001\u0007[Aqa!\u000e\u0001\t\u0003\u00199\u0004C\u0004\u00046\u0001!\taa\u000f\b\u000f\r\rS\b#\u0001\u0004F\u00191A(\u0010E\u0001\u0007\u000fBa!\u0013\u001d\u0005\u0002\r%\u0003bB69\u0005\u0004%\u0019\u0001\u001a\u0005\b\u0007\u0017B\u0004\u0015!\u0003L\u0005%9UM\\3sCR|'O\u0003\u0002?\u007f\u00051!/\u00198e_6T\u0011\u0001Q\u0001\u0006gBL'/Z\u0002\u0001'\t\u00011\t\u0005\u0002E\u000f6\tQIC\u0001G\u0003\u0015\u00198-\u00197b\u0013\tAUI\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003-\u0003\"\u0001\u0014\u0001\u000e\u0003u\nQ!\u001a=ue\u0006,\u0012a\u0014\t\u0003\tBK!!U#\u0003\u000f\t{w\u000e\\3b]\u0006IQ\r\u001f;sC~#S-\u001d\u000b\u0003)^\u0003\"\u0001R+\n\u0005Y+%\u0001B+oSRDq\u0001W\u0002\u0002\u0002\u0003\u0007q*A\u0002yIE\na!\u001a=ue\u0006\u0004\u0013!\u0002<bYV,W#\u0001/\u0011\u0005\u0011k\u0016B\u00010F\u0005\u0019!u.\u001e2mK\u0006Ia/\u00197vK~#S-\u001d\u000b\u0003)\u0006Dq\u0001\u0017\u0004\u0002\u0002\u0003\u0007A,\u0001\u0004wC2,X\rI\u0001\u0005G>\u0004\u00180F\u0001L\u0003!\u0019w\u000e]=J]&$\u0018\u0001B:z]\u000e,\u0012\u0001\u001b\t\u0003S2l\u0011A\u001b\u0006\u0003Wv\n1A\u001d8h\u0013\ti'NA\u0007Ts:\u001cw)\u001a8fe\u0006$xN]\u0001\rO\u0016$8+Z3e\u0005f$Xm]\u000b\u0002aB\u0019A)]:\n\u0005I,%!B!se\u0006L\bC\u0001#u\u0013\t)XI\u0001\u0003CsR,\u0017\u0001D:fiN+W\r\u001a\"zi\u0016\u001cHC\u0001+y\u0011\u0015IH\u00021\u0001q\u0003\u0015\u0011\u0017\u0010^3t\u0003\u001dqW\r\u001f;J]R$\u0012\u0001 \t\u0003\tvL!A`#\u0003\u0007%sG/\u0001\u0005oKb$Hj\u001c8h)\t\t\u0019\u0001E\u0002E\u0003\u000bI1!a\u0002F\u0005\u0011auN\\4\u0002\t9,\u0007\u0010^\u000b\u0005\u0003\u001b\t\u0019\u0002\u0006\u0003\u0002\u0010\u0005\u0015\u0002\u0003BA\t\u0003'a\u0001\u0001B\u0004\u0002\u0016=\u0011\r!a\u0006\u0003\u0003\u0005\u000bB!!\u0007\u0002 A\u0019A)a\u0007\n\u0007\u0005uQIA\u0004O_RD\u0017N\\4\u0011\u0007\u0011\u000b\t#C\u0002\u0002$\u0015\u00131!\u00118z\u0011\u001d\tIa\u0004a\u0002\u0003O\u0001R\u0001TA\u0015\u0003\u001fI1!a\u000b>\u0005\u0011!\u0015n\u001d;\u0002\u0011%$XM]1u_J,B!!\r\u0002NQ!\u00111GA(!\u0019\t)$!\u0012\u0002L9!\u0011qGA!\u001d\u0011\tI$a\u0010\u000e\u0005\u0005m\"bAA\u001f\u0003\u00061AH]8pizJ\u0011AR\u0005\u0004\u0003\u0007*\u0015a\u00029bG.\fw-Z\u0005\u0005\u0003\u000f\nIE\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\r\t\u0019%\u0012\t\u0005\u0003#\ti\u0005B\u0004\u0002\u0016A\u0011\r!a\u0006\t\u000f\u0005%\u0001\u0003q\u0001\u0002RA)A*!\u000b\u0002L\u0005Aa.\u001a=u\u0005&$8\u000fF\u0002}\u0003/Ba!!\u0017\u0012\u0001\u0004a\u0018!\u00018\u0015\u0007q\fi\u0006\u0003\u0004\u0002ZI\u0001\r\u0001`\u0001\te\u0016$(/_\"baR!\u00111MA8!\u0011\t)'a\u001b\u000e\u0005\u0005\u001d$bAA5\u007f\u0005!Q.\u0019;i\u0013\u0011\ti'a\u001a\u0003\tUKe\u000e\u001e\u0005\b\u0003c\u001a\u0002\u0019AA2\u0003\u00159\u0018\u000e\u001a;i)\u0015a\u0018QOA=\u0011\u0019\t9\b\u0006a\u0001y\u0006!aM]8n\u0011\u0019\tY\b\u0006a\u0001y\u0006\u0011Ao\u001c\u000b\u0005\u0003\u0007\ty\bC\u0004\u0002ZU\u0001\r!a\u0001\u0015\t\u0005\r\u0015\u0011\u0012\t\u0005\u0003K\n))\u0003\u0003\u0002\b\u0006\u001d$!B+M_:<\u0007bBA9-\u0001\u0007\u00111\u0011\u000b\u0007\u0003\u0007\ti)a$\t\u000f\u0005]t\u00031\u0001\u0002\u0004!9\u00111P\fA\u0002\u0005\r\u0011a\u00038fqR\u0014un\u001c7fC:$\u0012aT\u0001\n]\u0016DHO\u00127pCR$\"!!'\u0011\u0007\u0011\u000bY*C\u0002\u0002\u001e\u0016\u0013QA\u00127pCR$B!!'\u0002\"\"9\u0011\u0011\f\u000eA\u0002\u0005eECBAM\u0003K\u000b9\u000bC\u0004\u0002xm\u0001\r!!'\t\u000f\u0005%6\u00041\u0001\u0002\u001a\u0006)QO\u001c;jY\u0006Qa.\u001a=u\t>,(\r\\3\u0015\u0003q#2\u0001XAY\u0011\u0019\tI&\ba\u00019R)A,!.\u00028\"1\u0011q\u000f\u0010A\u0002qCa!!+\u001f\u0001\u0004a\u0016!D4f]\u0016\u0014\u0018\r^3M_:<7\u000f\u0006\u0003\u0002>\u0006}\u0006\u0003\u0002#r\u0003\u0007Aa!!\u0017 \u0001\u0004a\u0018!\u00034jY2duN\\4t)\r!\u0016Q\u0019\u0005\b\u0003\u000f\u0004\u0003\u0019AA_\u0003\r\t'O]\u0001\rO\u0016tWM]1uK&sGo\u001d\u000b\u0005\u0003\u001b\fy\rE\u0002EcrDa!!\u0017\"\u0001\u0004a\u0018\u0001\u00034jY2Le\u000e^:\u0015\u0007Q\u000b)\u000eC\u0004\u0002H\n\u0002\r!!4\u0002\u001d\u001d,g.\u001a:bi\u0016\u001c\u0006n\u001c:ugR!\u00111\\Ar!\u0011!\u0015/!8\u0011\u0007\u0011\u000by.C\u0002\u0002b\u0016\u0013Qa\u00155peRDa!!\u0017$\u0001\u0004a\u0018A\u00034jY2\u001c\u0006n\u001c:ugR\u0019A+!;\t\u000f\u0005\u001dG\u00051\u0001\u0002\\\u0006iq-\u001a8fe\u0006$XMQ=uKN$2\u0001]Ax\u0011\u0019\tI&\na\u0001y\u0006Ia-\u001b7m\u0005f$Xm\u001d\u000b\u0004)\u0006U\bBBAdM\u0001\u0007\u0001/A\u0007hK:,'/\u0019;f\u0003J\u0014\u0018-_\u000b\u0005\u0003w\u0014\u0019\u0001\u0006\u0003\u0002~\n\u0015BCBA\u0000\u0005\u001b\u0011\u0019\u0002\u0005\u0003Ec\n\u0005\u0001\u0003BA\t\u0005\u0007!1\"!\u0006(A\u0003\u0005\tQ1\u0001\u0002\u0018!\"!1\u0001B\u0004!\r!%\u0011B\u0005\u0004\u0005\u0017)%aC:qK\u000eL\u0017\r\\5{K\u0012D\u0011Ba\u0004(\u0003\u0003\u0005\u001dA!\u0005\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0003M\u0003S\u0011\t\u0001C\u0005\u0003\u0016\u001d\n\t\u0011q\u0001\u0003\u0018\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\r\te!q\u0004B\u0001\u001d\u0011\u0011YB!\b\u000e\u0003}J1!a\u0011@\u0013\u0011\u0011\tCa\t\u0003\u0011\rc\u0017m]:UC\u001eT1!a\u0011@\u0011\u0019\tIf\na\u0001y\u0006Ia-\u001b7m\u0003J\u0014\u0018-_\u000b\u0005\u0005W\u00119\u0004\u0006\u0003\u0003.\tmBc\u0001+\u00030!I!\u0011\u0007\u0015\u0002\u0002\u0003\u000f!1G\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004#\u0002'\u0002*\tU\u0002\u0003BA\t\u0005o!1\"!\u0006)A\u0003\u0005\tQ1\u0001\u0002\u0018!\"!q\u0007B\u0004\u0011\u001d\t9\r\u000ba\u0001\u0005{\u0001B\u0001R9\u00036\u0005)qN\\3PMV!!1\tB$)\u0011\u0011)E!\u0013\u0011\t\u0005E!q\t\u0003\b\u0003+I#\u0019AA\f\u0011\u001d\u0011Y%\u000ba\u0001\u0005\u001b\n!!Y:\u0011\u000b\u0011\u0013yE!\u0012\n\u0007\tESI\u0001\u0006=e\u0016\u0004X-\u0019;fIz\nqb\u00195p_N,gI]8n\u0003J\u0014\u0018-_\u000b\u0005\u0005/\u0012i\u0006\u0006\u0003\u0003Z\t\u0015D\u0003\u0002B.\u0005C\u0002B!!\u0005\u0003^\u0011Y\u0011Q\u0003\u0016!\u0002\u0003\u0005)\u0019AA\fQ\u0011\u0011iFa\u0002\t\r\t\r$\u0006q\u0001L\u0003\r9WM\u001c\u0005\b\u0003\u000fT\u0003\u0019\u0001B4!\u0011!\u0015Oa\u0017\u0002\u001b\rDwn\\:f\rJ|WnU3r+\u0011\u0011iGa\u001d\u0015\t\t=$q\u000f\u000b\u0005\u0005c\u0012)\b\u0005\u0003\u0002\u0012\tMDaBA\u000bW\t\u0007\u0011q\u0003\u0005\u0007\u0005GZ\u00039A&\t\u000f\te4\u00061\u0001\u0003|\u0005\u00191/Z9\u0011\r\u0005U\"Q\u0010B9\u0013\u0011\u0011y(!\u0013\u0003\u0007M+\u0017/\u0001\ndQ>|7/\u001a$s_6LE/\u001a:bE2,W\u0003\u0002BC\u0005\u0017#BAa\"\u0003\u0010R!!\u0011\u0012BG!\u0011\t\tBa#\u0005\u000f\u0005UAF1\u0001\u0002\u0018!1!1\r\u0017A\u0004-CqAa\u0013-\u0001\u0004\u0011\t\n\u0005\u0004\u00026\tM%\u0011R\u0005\u0005\u0005+\u000bIE\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0003=\u0019\u0018-\u001c9mK\u001a\u0013x.\\!se\u0006LX\u0003\u0002BN\u0005G#bA!(\u00030\nEFC\u0002BP\u0005O\u0013i\u000b\u0005\u0003Ec\n\u0005\u0006\u0003BA\t\u0005G#1\"!\u0006.A\u0003\u0005\tQ1\u0001\u0002\u0018!\"!1\u0015B\u0004\u0011%\u0011I+LA\u0001\u0002\b\u0011Y+\u0001\u0006fm&$WM\\2fIQ\u0002bA!\u0007\u0003 \t\u0005\u0006B\u0002B2[\u0001\u000f1\nC\u0004\u0003L5\u0002\rAa(\t\r\tMV\u00061\u0001}\u0003\u0011\u0019\u0018N_3\u0002%M\fW\u000e\u001d7f\rJ|W.\u0013;fe\u0006\u0014G.Z\u000b\u0005\u0005s\u0013\t\r\u0006\u0004\u0003<\n5'\u0011\u001b\u000b\u0007\u0005{\u0013)Ma3\u0011\t\u0011\u000b(q\u0018\t\u0005\u0003#\u0011\t\rB\u0006\u0002\u00169\u0002\u000b\u0011!AC\u0002\u0005]\u0001\u0006\u0002Ba\u0005\u000fA\u0011Ba2/\u0003\u0003\u0005\u001dA!3\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007\u0005\u0004\u0003\u001a\t}!q\u0018\u0005\u0007\u0005Gr\u00039A&\t\u000f\t-c\u00061\u0001\u0003PB1\u0011Q\u0007BJ\u0005\u007fCaAa-/\u0001\u0004a\u0018!F:b[BdWM\u0012:p[R\u0013\u0018M^3sg\u0006\u0014G.Z\u000b\u0005\u0005/\u0014y\u000e\u0006\u0004\u0003Z\n-(q\u001e\u000b\u0007\u00057\u0014\u0019O!;\u0011\t\u0011\u000b(Q\u001c\t\u0005\u0003#\u0011y\u000eB\u0006\u0002\u0016=\u0002\u000b\u0011!AC\u0002\u0005]\u0001\u0006\u0002Bp\u0005\u000fA\u0011B!:0\u0003\u0003\u0005\u001dAa:\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$c\u0007\u0005\u0004\u0003\u001a\t}!Q\u001c\u0005\u0007\u0005Gz\u00039A&\t\u000f\t-s\u00061\u0001\u0003nB1\u0011Q\u0007BJ\u0005;DaAa-0\u0001\u0004a\bfC\u0018\u0003t\ne(1 B\u0000\u0007\u0003\u00012\u0001\u0012B{\u0013\r\u001190\u0012\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0003\u0005{\fa$^:fAM\fW\u000e\u001d7f\rJ|W.\u0013;fe\u0006\u0014G.\u001a\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0005\r\r\u0011A\u0002\u0019/car\u0003'A\u0004tQV4g\r\\3\u0016\t\r%1Q\u0003\u000b\u0005\u0007\u0017\u0019y\u0001F\u0002U\u0007\u001bAaAa\u00191\u0001\bY\u0005b\u0002B&a\u0001\u00071\u0011\u0003\t\u0005\tF\u001c\u0019\u0002\u0005\u0003\u0002\u0012\rUAaCA\u000ba\u0001\u0006\t\u0011!b\u0001\u0003/ACa!\u0006\u0003\b\u0005aa.\u001a=u\u000f\u0006,8o]5b]R)Al!\b\u0004\"!11q\u0004\u001aA\u0002q\u000bA!\\3b]\"111\u0005\u001aA\u0002q\u000baa\u001d;eI\u00164\u0018!\u00044jY2<\u0015-^:tS\u0006t7\u000fF\u0002U\u0007SAq!a24\u0001\u0004\u0019Y\u0003E\u0002Ecr#r\u0001VB\u0018\u0007c\u0019\u0019\u0004C\u0004\u0002HR\u0002\raa\u000b\t\r\r}A\u00071\u0001]\u0011\u0019\u0019\u0019\u0003\u000ea\u00019\u0006\tr-\u001a8fe\u0006$XmR1vgNL\u0017M\\:\u0015\t\r-2\u0011\b\u0005\u0007\u00033*\u0004\u0019\u0001?\u0015\u0011\r-2QHB \u0007\u0003Ba!!\u00177\u0001\u0004a\bBBB\u0010m\u0001\u0007A\f\u0003\u0004\u0004$Y\u0002\r\u0001X\u0001\n\u000f\u0016tWM]1u_J\u0004\"\u0001\u0014\u001d\u0014\u0005a\u001aECAB#\u0003\u0011\u0011hn\u001a\u0011"
)
public abstract class Generator {
   private boolean extra = false;
   private double value = (double)0.0F;

   public static Generator rng() {
      return Generator$.MODULE$.rng();
   }

   public boolean extra() {
      return this.extra;
   }

   public void extra_$eq(final boolean x$1) {
      this.extra = x$1;
   }

   public double value() {
      return this.value;
   }

   public void value_$eq(final double x$1) {
      this.value = x$1;
   }

   public Generator copy() {
      Generator gen = this.copyInit();
      gen.extra_$eq(this.extra());
      gen.value_$eq(this.value());
      return gen;
   }

   public abstract Generator copyInit();

   public SyncGenerator sync() {
      return new SyncGenerator(this.copy());
   }

   public abstract byte[] getSeedBytes();

   public abstract void setSeedBytes(final byte[] bytes);

   public abstract int nextInt();

   public abstract long nextLong();

   public Object next(final Dist next) {
      return next.apply(this);
   }

   public Iterator iterator(final Dist next) {
      return new DistIterator(next, this);
   }

   public int nextBits(final int n) {
      return this.nextInt() >>> 32 - n;
   }

   public int nextInt(final int n) {
      if (n < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("argument must be positive %d"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(n)})));
      } else {
         return (n & -n) == n ? (int)((long)n * (long)(this.nextInt() >>> 1) >>> 31) : this.loop$1(this.nextInt() >>> 1, n);
      }
   }

   private final int retryCap(final int width) {
      int q = UInt$.MODULE$.$div$extension(UInt$.MODULE$.apply(Integer.MIN_VALUE), width);
      int r = UInt$.MODULE$.$percent$extension(UInt$.MODULE$.apply(Integer.MIN_VALUE), width);
      int n = UInt$.MODULE$.$plus$extension(UInt$.MODULE$.$less$less$extension(q, 1), UInt$.MODULE$.$div$extension(UInt$.MODULE$.$less$less$extension(r, 1), width));
      return UInt$.MODULE$.$times$extension(n, width);
   }

   public int nextInt(final int from, final int to) {
      int width = UInt$.MODULE$.apply(to - from + 1);
      int var10000;
      if (UInt$.MODULE$.$eq$eq$extension(width, UInt$.MODULE$.apply(0))) {
         var10000 = this.nextInt();
      } else {
         int cap = UInt$.MODULE$.$greater$extension(width, UInt$.MODULE$.apply(Integer.MIN_VALUE)) ? width : this.retryCap(width);
         if (UInt$.MODULE$.$eq$eq$extension(cap, UInt$.MODULE$.apply(0))) {
            int x = UInt$.MODULE$.apply(this.nextInt());
            var10000 = from + UInt$.MODULE$.$percent$extension(x, width);
         } else {
            var10000 = this.loop$2(cap, width, from);
         }
      }

      return var10000;
   }

   public long nextLong(final long n) {
      if (n < 1L) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("argument must be positive %d"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToLong(n)})));
      } else {
         return (n & -n) == n ? this.nextLong() & n - 1L : this.loop$3(this.nextLong() >>> 1, n);
      }
   }

   private final long retryCap(final long width) {
      long q = ULong$.MODULE$.$div$extension(ULong$.MODULE$.apply(Long.MIN_VALUE), width);
      long r = ULong$.MODULE$.$percent$extension(ULong$.MODULE$.apply(Long.MIN_VALUE), width);
      long n = ULong$.MODULE$.$plus$extension(ULong$.MODULE$.$less$less$extension(q, 1), ULong$.MODULE$.$div$extension(ULong$.MODULE$.$less$less$extension(r, 1), width));
      return ULong$.MODULE$.$times$extension(n, width);
   }

   public long nextLong(final long from, final long to) {
      long width = ULong$.MODULE$.apply(to - from + 1L);
      long var10000;
      if (ULong$.MODULE$.$eq$eq$extension(width, ULong$.MODULE$.apply(0L))) {
         var10000 = this.nextLong();
      } else {
         long cap = ULong$.MODULE$.$greater$extension(width, ULong$.MODULE$.apply(Long.MIN_VALUE)) ? width : this.retryCap(width);
         if (ULong$.MODULE$.$eq$eq$extension(cap, ULong$.MODULE$.apply(0L))) {
            long x = ULong$.MODULE$.apply(this.nextLong());
            var10000 = from + ULong$.MODULE$.$percent$extension(x, width);
         } else {
            var10000 = this.loop$4(cap, width, from);
         }
      }

      return var10000;
   }

   public boolean nextBoolean() {
      return (this.nextInt() & 1) != 0;
   }

   public float nextFloat() {
      return (float)(this.nextInt() >>> 8) * 5.9604645E-8F;
   }

   public float nextFloat(final float n) {
      return this.nextFloat() * n;
   }

   public float nextFloat(final float from, final float until) {
      return from + (until - from) * this.nextFloat();
   }

   public double nextDouble() {
      return (double)(this.nextLong() >>> 11) * (double)1.110223E-16F;
   }

   public double nextDouble(final double n) {
      return this.nextDouble() * n;
   }

   public double nextDouble(final double from, final double until) {
      return from + (until - from) * this.nextDouble();
   }

   public long[] generateLongs(final int n) {
      long[] arr = new long[n];
      this.fillLongs(arr);
      return arr;
   }

   public void fillLongs(final long[] arr) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = this.nextLong();
      }

   }

   public int[] generateInts(final int n) {
      int[] arr = new int[n];
      this.fillInts(arr);
      return arr;
   }

   public void fillInts(final int[] arr) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = this.nextInt();
      }

   }

   public short[] generateShorts(final int n) {
      short[] arr = new short[n];
      this.fillShorts(arr);
      return arr;
   }

   public void fillShorts(final short[] arr) {
      int i = 0;
      int len = arr.length;

      int llen;
      for(llen = len & -2; i < llen; i += 2) {
         int n = this.nextInt();
         arr[i] = (short)(n & '\uffff');
         arr[i + 1] = (short)(n >>> 16 & '\uffff');
      }

      if (len != llen) {
         arr[i] = (short)(this.nextInt() & '\uffff');
      }

   }

   public byte[] generateBytes(final int n) {
      byte[] arr = new byte[n];
      this.fillBytes(arr);
      return arr;
   }

   public void fillBytes(final byte[] arr) {
      int i = 0;
      int len = arr.length;

      for(int llen = len & -4; i < llen; i += 4) {
         int n = this.nextInt();
         arr[i] = (byte)(n & 255);
         arr[i + 1] = (byte)(n >>> 8 & 255);
         arr[i + 2] = (byte)(n >>> 16 & 255);
         arr[i + 3] = (byte)(n >>> 24 & 255);
      }

      if (i < len) {
         for(int n = this.nextInt(); i < len; ++i) {
            arr[i] = (byte)(n & 255);
            n >>>= 8;
         }
      }

   }

   public Object generateArray(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      Object arr = evidence$2.newArray(n);
      this.fillArray(arr, evidence$1);
      return arr;
   }

   public void fillArray(final Object arr, final Dist evidence$3) {
      int i = 0;

      for(int len = scala.runtime.ScalaRunTime..MODULE$.array_length(arr); i < len; ++i) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(arr, i, this.next(evidence$3));
      }

   }

   public Object oneOf(final Seq as) {
      return this.chooseFromSeq(as, this);
   }

   public Object chooseFromArray(final Object arr, final Generator gen) {
      return scala.runtime.ScalaRunTime..MODULE$.array_apply(arr, gen.nextInt(scala.runtime.ScalaRunTime..MODULE$.array_length(arr)));
   }

   public Object chooseFromSeq(final Seq seq, final Generator gen) {
      return seq.apply(gen.nextInt(seq.length()));
   }

   public Object chooseFromIterable(final Iterable as, final Generator gen) {
      return as.iterator().drop(gen.nextInt(as.size())).next();
   }

   public Object sampleFromArray(final Object as, final int size, final ClassTag evidence$4, final Generator gen) {
      Object chosen = evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < scala.runtime.ScalaRunTime..MODULE$.array_length(as)) {
            for(int i = 0; i < scala.runtime.ScalaRunTime..MODULE$.array_length(as); ++i) {
               if (i < size) {
                  scala.runtime.ScalaRunTime..MODULE$.array_update(chosen, i, scala.runtime.ScalaRunTime..MODULE$.array_apply(as, i));
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     scala.runtime.ScalaRunTime..MODULE$.array_update(chosen, n, scala.runtime.ScalaRunTime..MODULE$.array_apply(as, i));
                  }
               }
            }
         } else {
            if (size != scala.runtime.ScalaRunTime..MODULE$.array_length(as)) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(scala.runtime.ScalaRunTime..MODULE$.array_length(as))})));
            }

            System.arraycopy(as, 0, chosen, 0, scala.runtime.ScalaRunTime..MODULE$.array_length(as));
            this.shuffle(chosen, gen);
         }

         return chosen;
      }
   }

   public Object sampleFromIterable(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      Object chosen = evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((a) -> {
         $anonfun$sampleFromIterable$1(i, size, chosen, gen, a);
         return BoxedUnit.UNIT;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   /** @deprecated */
   public Object sampleFromTraversable(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable(as, size, evidence$6, gen);
   }

   public void shuffle(final Object as, final Generator gen) {
      for(int i = scala.runtime.ScalaRunTime..MODULE$.array_length(as) - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         Object tmp = scala.runtime.ScalaRunTime..MODULE$.array_apply(as, i);
         scala.runtime.ScalaRunTime..MODULE$.array_update(as, i, scala.runtime.ScalaRunTime..MODULE$.array_apply(as, n));
         scala.runtime.ScalaRunTime..MODULE$.array_update(as, n, tmp);
      }

   }

   public double nextGaussian() {
      double var10000;
      if (this.extra()) {
         this.extra_$eq(false);
         var10000 = this.value();
      } else {
         var10000 = this.loop$5(this.nextDouble() * (double)2 - (double)1, this.nextDouble() * (double)2 - (double)1);
      }

      return var10000;
   }

   public double nextGaussian(final double mean, final double stddev) {
      return this.nextGaussian() * stddev + mean;
   }

   public void fillGaussians(final double[] arr) {
      this.fillGaussians(arr, (double)0.0F, (double)1.0F);
   }

   public void fillGaussians(final double[] arr, final double mean, final double stddev) {
      int i = 0;

      int len;
      for(len = arr.length & -2; i < len; i += 2) {
         this.loop$6(i, this.nextDouble() * (double)2 - (double)1, this.nextDouble() * (double)2 - (double)1, arr, stddev, mean);
      }

      if (len < arr.length) {
         arr[len] = this.nextGaussian() * stddev + mean;
      }

   }

   public double[] generateGaussians(final int n) {
      double[] arr = new double[n];
      this.fillGaussians(arr);
      return arr;
   }

   public double[] generateGaussians(final int n, final double mean, final double stddev) {
      double[] arr = new double[n];
      this.fillGaussians(arr, mean, stddev);
      return arr;
   }

   public boolean[] generateArray$mZc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      boolean[] arr = (boolean[])evidence$2.newArray(n);
      this.fillArray$mZc$sp(arr, evidence$1);
      return arr;
   }

   public byte[] generateArray$mBc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      byte[] arr = (byte[])evidence$2.newArray(n);
      this.fillArray$mBc$sp(arr, evidence$1);
      return arr;
   }

   public char[] generateArray$mCc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      char[] arr = (char[])evidence$2.newArray(n);
      this.fillArray$mCc$sp(arr, evidence$1);
      return arr;
   }

   public double[] generateArray$mDc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      double[] arr = (double[])evidence$2.newArray(n);
      this.fillArray$mDc$sp(arr, evidence$1);
      return arr;
   }

   public float[] generateArray$mFc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      float[] arr = (float[])evidence$2.newArray(n);
      this.fillArray$mFc$sp(arr, evidence$1);
      return arr;
   }

   public int[] generateArray$mIc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      int[] arr = (int[])evidence$2.newArray(n);
      this.fillArray$mIc$sp(arr, evidence$1);
      return arr;
   }

   public long[] generateArray$mJc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      long[] arr = (long[])evidence$2.newArray(n);
      this.fillArray$mJc$sp(arr, evidence$1);
      return arr;
   }

   public short[] generateArray$mSc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      short[] arr = (short[])evidence$2.newArray(n);
      this.fillArray$mSc$sp(arr, evidence$1);
      return arr;
   }

   public BoxedUnit[] generateArray$mVc$sp(final int n, final Dist evidence$1, final ClassTag evidence$2) {
      BoxedUnit[] arr = (BoxedUnit[])evidence$2.newArray(n);
      this.fillArray$mVc$sp(arr, evidence$1);
      return arr;
   }

   public void fillArray$mZc$sp(final boolean[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToBoolean(this.next(evidence$3));
      }

   }

   public void fillArray$mBc$sp(final byte[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToByte(this.next(evidence$3));
      }

   }

   public void fillArray$mCc$sp(final char[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToChar(this.next(evidence$3));
      }

   }

   public void fillArray$mDc$sp(final double[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToDouble(this.next(evidence$3));
      }

   }

   public void fillArray$mFc$sp(final float[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToFloat(this.next(evidence$3));
      }

   }

   public void fillArray$mIc$sp(final int[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToInt(this.next(evidence$3));
      }

   }

   public void fillArray$mJc$sp(final long[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToLong(this.next(evidence$3));
      }

   }

   public void fillArray$mSc$sp(final short[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = BoxesRunTime.unboxToShort(this.next(evidence$3));
      }

   }

   public void fillArray$mVc$sp(final BoxedUnit[] arr, final Dist evidence$3) {
      int i = 0;

      for(int len = arr.length; i < len; ++i) {
         arr[i] = (BoxedUnit)this.next(evidence$3);
      }

   }

   public boolean chooseFromArray$mZc$sp(final boolean[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public byte chooseFromArray$mBc$sp(final byte[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public char chooseFromArray$mCc$sp(final char[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public double chooseFromArray$mDc$sp(final double[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public float chooseFromArray$mFc$sp(final float[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public int chooseFromArray$mIc$sp(final int[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public long chooseFromArray$mJc$sp(final long[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public short chooseFromArray$mSc$sp(final short[] arr, final Generator gen) {
      return arr[gen.nextInt(arr.length)];
   }

   public void chooseFromArray$mVc$sp(final BoxedUnit[] arr, final Generator gen) {
      BoxedUnit var10000 = arr[gen.nextInt(arr.length)];
   }

   public boolean[] sampleFromArray$mZc$sp(final boolean[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      boolean[] chosen = (boolean[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mZc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public byte[] sampleFromArray$mBc$sp(final byte[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      byte[] chosen = (byte[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mBc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public char[] sampleFromArray$mCc$sp(final char[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      char[] chosen = (char[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mCc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public double[] sampleFromArray$mDc$sp(final double[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      double[] chosen = (double[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mDc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public float[] sampleFromArray$mFc$sp(final float[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      float[] chosen = (float[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mFc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public int[] sampleFromArray$mIc$sp(final int[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      int[] chosen = (int[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mIc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public long[] sampleFromArray$mJc$sp(final long[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      long[] chosen = (long[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mJc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public short[] sampleFromArray$mSc$sp(final short[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      short[] chosen = (short[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mSc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public BoxedUnit[] sampleFromArray$mVc$sp(final BoxedUnit[] as, final int size, final ClassTag evidence$4, final Generator gen) {
      BoxedUnit[] chosen = (BoxedUnit[])evidence$4.newArray(size);
      if (size < 1) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("illegal sample size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size)})));
      } else {
         if (size < as.length) {
            for(int i = 0; i < as.length; ++i) {
               if (i < size) {
                  chosen[i] = as[i];
               } else {
                  int n = gen.nextInt(i + 1);
                  if (n < size) {
                     chosen[n] = as[i];
                  }
               }
            }
         } else {
            if (size != as.length) {
               throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(as.length)})));
            }

            System.arraycopy(as, 0, chosen, 0, as.length);
            this.shuffle$mVc$sp(chosen, gen);
         }

         return chosen;
      }
   }

   public boolean[] sampleFromIterable$mZc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      boolean[] chosen = (boolean[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((a) -> {
         $anonfun$sampleFromIterable$2(i, size, chosen, gen, BoxesRunTime.unboxToBoolean(a));
         return BoxedUnit.UNIT;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public byte[] sampleFromIterable$mBc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      byte[] chosen = (byte[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((a) -> {
         $anonfun$sampleFromIterable$3(i, size, chosen, gen, BoxesRunTime.unboxToByte(a));
         return BoxedUnit.UNIT;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public char[] sampleFromIterable$mCc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      char[] chosen = (char[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((a) -> {
         $anonfun$sampleFromIterable$4(i, size, chosen, gen, BoxesRunTime.unboxToChar(a));
         return BoxedUnit.UNIT;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public double[] sampleFromIterable$mDc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      double[] chosen = (double[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((JFunction1.mcVD.sp)(a) -> {
         if (i.elem < size) {
            chosen[i.elem] = a;
         } else {
            int n = gen.nextInt(i.elem + 1);
            if (n < size) {
               chosen[n] = a;
            }
         }

         ++i.elem;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public float[] sampleFromIterable$mFc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      float[] chosen = (float[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((JFunction1.mcVF.sp)(a) -> {
         if (i.elem < size) {
            chosen[i.elem] = a;
         } else {
            int n = gen.nextInt(i.elem + 1);
            if (n < size) {
               chosen[n] = a;
            }
         }

         ++i.elem;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public int[] sampleFromIterable$mIc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      int[] chosen = (int[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((JFunction1.mcVI.sp)(a) -> {
         if (i.elem < size) {
            chosen[i.elem] = a;
         } else {
            int n = gen.nextInt(i.elem + 1);
            if (n < size) {
               chosen[n] = a;
            }
         }

         ++i.elem;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public long[] sampleFromIterable$mJc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      long[] chosen = (long[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((JFunction1.mcVJ.sp)(a) -> {
         if (i.elem < size) {
            chosen[i.elem] = a;
         } else {
            int n = gen.nextInt(i.elem + 1);
            if (n < size) {
               chosen[n] = a;
            }
         }

         ++i.elem;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public short[] sampleFromIterable$mSc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      short[] chosen = (short[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((a) -> {
         $anonfun$sampleFromIterable$9(i, size, chosen, gen, BoxesRunTime.unboxToShort(a));
         return BoxedUnit.UNIT;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   public BoxedUnit[] sampleFromIterable$mVc$sp(final Iterable as, final int size, final ClassTag evidence$5, final Generator gen) {
      BoxedUnit[] chosen = (BoxedUnit[])evidence$5.newArray(size);
      IntRef i = IntRef.create(0);
      as.foreach((a) -> {
         $anonfun$sampleFromIterable$10(i, size, chosen, gen, a);
         return BoxedUnit.UNIT;
      });
      if (i.elem < size) {
         throw new IllegalArgumentException(.MODULE$.format$extension(scala.Predef..MODULE$.augmentString("sample size (%d) exceeds input size (%d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(size), BoxesRunTime.boxToInteger(i.elem)})));
      } else {
         return chosen;
      }
   }

   /** @deprecated */
   public boolean[] sampleFromTraversable$mZc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mZc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public byte[] sampleFromTraversable$mBc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mBc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public char[] sampleFromTraversable$mCc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mCc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public double[] sampleFromTraversable$mDc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mDc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public float[] sampleFromTraversable$mFc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mFc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public int[] sampleFromTraversable$mIc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mIc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public long[] sampleFromTraversable$mJc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mJc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public short[] sampleFromTraversable$mSc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mSc$sp(as, size, evidence$6, gen);
   }

   /** @deprecated */
   public BoxedUnit[] sampleFromTraversable$mVc$sp(final Iterable as, final int size, final ClassTag evidence$6, final Generator gen) {
      return this.sampleFromIterable$mVc$sp(as, size, evidence$6, gen);
   }

   public void shuffle$mZc$sp(final boolean[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         boolean tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mBc$sp(final byte[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         byte tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mCc$sp(final char[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         char tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mDc$sp(final double[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         double tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mFc$sp(final float[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         float tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mIc$sp(final int[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         int tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mJc$sp(final long[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         long tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mSc$sp(final short[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         short tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   public void shuffle$mVc$sp(final BoxedUnit[] as, final Generator gen) {
      for(int i = as.length - 1; i > 0; --i) {
         int n = gen.nextInt(i);
         BoxedUnit tmp = as[i];
         as[i] = as[n];
         as[n] = tmp;
      }

   }

   private final int loop$1(final int b, final int n$1) {
      while(true) {
         int v = b % n$1;
         if (b - v + (n$1 - 1) >= 0) {
            return v;
         }

         b = this.nextInt() >>> 1;
      }
   }

   private final int loop$2(final int cap$1, final int width$1, final int from$1) {
      int x;
      do {
         x = UInt$.MODULE$.apply(this.nextInt());
      } while(!UInt$.MODULE$.$less$eq$extension(x, cap$1));

      return UInt$.MODULE$.$percent$extension(x, width$1) + from$1;
   }

   private final long loop$3(final long b, final long n$2) {
      while(true) {
         long v = b % n$2;
         if (b - v + (n$2 - 1L) >= 0L) {
            return v;
         }

         b = this.nextLong() >>> 1;
      }
   }

   private final long loop$4(final long cap$2, final long width$2, final long from$2) {
      long x;
      do {
         x = ULong$.MODULE$.apply(this.nextLong());
      } while(!ULong$.MODULE$.$less$eq$extension(x, cap$2));

      return ULong$.MODULE$.$percent$extension(x, width$2) + from$2;
   }

   // $FF: synthetic method
   public static final void $anonfun$sampleFromIterable$1(final IntRef i$1, final int size$1, final Object chosen$1, final Generator gen$1, final Object a) {
      if (i$1.elem < size$1) {
         scala.runtime.ScalaRunTime..MODULE$.array_update(chosen$1, i$1.elem, a);
      } else {
         int n = gen$1.nextInt(i$1.elem + 1);
         if (n < size$1) {
            scala.runtime.ScalaRunTime..MODULE$.array_update(chosen$1, n, a);
         }
      }

      ++i$1.elem;
   }

   private final double loop$5(final double x, final double y) {
      while(true) {
         double s = x * x + y * y;
         if (!(s >= (double)1.0F) && s != (double)0.0F) {
            double scale = Math.sqrt((double)-2.0F * Math.log(s) / s);
            this.extra_$eq(true);
            this.value_$eq(y * scale);
            return x * scale;
         }

         x = y = this.nextDouble() * (double)2 - (double)1;
      }
   }

   private final void loop$6(final int i, final double x, final double y, final double[] arr$1, final double stddev$1, final double mean$1) {
      while(true) {
         double s = x * x + y * y;
         if (!(s >= (double)1.0F) && s != (double)0.0F) {
            double scale = Math.sqrt((double)-2.0F * Math.log(s) / s);
            arr$1[i] = x * scale * stddev$1 + mean$1;
            arr$1[i + 1] = y * scale * stddev$1 + mean$1;
            BoxedUnit var10000 = BoxedUnit.UNIT;
            return;
         }

         x = y = this.nextDouble() * (double)2 - (double)1;
         i = i;
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$sampleFromIterable$2(final IntRef i$2, final int size$2, final boolean[] chosen$2, final Generator gen$2, final boolean a) {
      if (i$2.elem < size$2) {
         chosen$2[i$2.elem] = a;
      } else {
         int n = gen$2.nextInt(i$2.elem + 1);
         if (n < size$2) {
            chosen$2[n] = a;
         }
      }

      ++i$2.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$sampleFromIterable$3(final IntRef i$3, final int size$3, final byte[] chosen$3, final Generator gen$3, final byte a) {
      if (i$3.elem < size$3) {
         chosen$3[i$3.elem] = a;
      } else {
         int n = gen$3.nextInt(i$3.elem + 1);
         if (n < size$3) {
            chosen$3[n] = a;
         }
      }

      ++i$3.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$sampleFromIterable$4(final IntRef i$4, final int size$4, final char[] chosen$4, final Generator gen$4, final char a) {
      if (i$4.elem < size$4) {
         chosen$4[i$4.elem] = a;
      } else {
         int n = gen$4.nextInt(i$4.elem + 1);
         if (n < size$4) {
            chosen$4[n] = a;
         }
      }

      ++i$4.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$sampleFromIterable$9(final IntRef i$9, final int size$9, final short[] chosen$9, final Generator gen$9, final short a) {
      if (i$9.elem < size$9) {
         chosen$9[i$9.elem] = a;
      } else {
         int n = gen$9.nextInt(i$9.elem + 1);
         if (n < size$9) {
            chosen$9[n] = a;
         }
      }

      ++i$9.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$sampleFromIterable$10(final IntRef i$10, final int size$10, final BoxedUnit[] chosen$10, final Generator gen$10, final BoxedUnit a) {
      if (i$10.elem < size$10) {
         chosen$10[i$10.elem] = a;
      } else {
         int n = gen$10.nextInt(i$10.elem + 1);
         if (n < size$10) {
            chosen$10[n] = a;
         }
      }

      ++i$10.elem;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
