package scala.jdk;

import java.lang.invoke.SerializedLambda;
import scala.$less$colon$less;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactory$;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.SeqView;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.View;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.SeqOps;
import scala.collection.mutable.StringBuilder;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015%c!B!C\u0003\u00039\u0005BB@\u0001\t\u0003\t\t\u0001\u0003\u0006\u0002\u001a\u0001\u0001\r\u0011\"\u0001C\u00037A!\"a\t\u0001\u0001\u0004%\tAQA\u0013\u0011!\t\t\u0004\u0001Q!\n\u0005u\u0001BCA\u001a\u0001\u0001\u0007I\u0011\u0001\"\u0002\u001c!Q\u0011Q\u0007\u0001A\u0002\u0013\u0005!)a\u000e\t\u0011\u0005m\u0002\u0001)Q\u0005\u0003;A!\"!\u0010\u0001\u0001\u0004%\tAQA \u0011)\t9\u0005\u0001a\u0001\n\u0003\u0011\u0015\u0011\n\u0005\t\u0003\u001b\u0002\u0001\u0015)\u0003\u0002B!A\u0011q\n\u0001\u0007\u0002\t\u000b\t\u0006\u0003\u0005\u0002X\u0001!\tAQA\u000e\u0011\u001d\tI\u0006\u0001D\t\u00037Bq!!'\u0001\t\u000b\nY\nC\u0004\u00028\u0002!)%a\u0007\t\u000f\u0005e\u0006\u0001\"\u0012\u0002\u001c!9\u00111\u0018\u0001\u0005\u0006\u0005}\u0002bBA_\u0001\u0011\u0005\u0011q\u0018\u0005\t\u0003\u0003\u0004A\u0011\u0001\"\u0002D\u001e9\u0011\u0011\u001a\"\t\u0002\u0005-gAB!C\u0011\u0003\ti\r\u0003\u0004\u0000+\u0011\u0005\u0011q\u001a\u0005\b\u0003#,B1AAj\u0011\u001d\u00119#\u0006C\u0001\u0005SAqAa\u0001\u0016\t\u0003\u0011i\u0005C\u0004\u0003^U!\tAa\u0018\t\u000f\tmT\u0003\"\u0001\u0003~!9!1U\u000b\u0005\u0002\t\u0015\u0006b\u0002Bi+\u0011\u0005!1\u001b\u0005\b\u0005#,B\u0011\u0001B{\u0011\u001d\u0019)\"\u0006C\u0001\u0007/Aqa!\u000b\u0016\t\u0003\u0019Y\u0003C\u0004\u0004*U!\ta!\u0014\t\u000f\r%R\u0003\"\u0001\u0004t!91\u0011F\u000b\u0005\u0002\r]\u0005bBB\u0015+\u0011\u00051q\u0018\u0005\b\u0007W,B\u0011ABw\u0011\u001d\u0019Y/\u0006C\u0001\t\u000fAqaa;\u0016\t\u0003!I\u0003C\u0004\u0004lV!\t\u0001b\u0014\t\u000f\r-X\u0003\"\u0001\u0005z!9AqU\u000b\u0005\u0002\u0011%f!CAx+A\u0005\u0019\u0013EAy\u0011\u001d\t)p\u000bD\u0001\u0003oDqAa\u0001,\r\u0003\u0011)aB\u0004\u0005HVA\t\u0001\"3\u0007\u000f\u0005=X\u0003#\u0001\u0005L\"1qp\fC\u0001\tcD\u0011\u0002b=0\u0005\u0004%\u0019\u0001\">\t\u0011\u0015\u0015q\u0006)A\u0005\toD\u0011\"b\u00020\u0005\u0004%\u0019!\"\u0003\t\u0011\u0015Mq\u0006)A\u0005\u000b\u0017A\u0011\"\"\u00060\u0005\u0004%\u0019!b\u0006\t\u0011\u0015\u0005r\u0006)A\u0005\u000b3A\u0011\"b\t0\u0005\u0004%\u0019!\"\n\t\u0011\u00155r\u0006)A\u0005\u000bOA\u0011\"b\f0\u0005\u0004%\u0019!\"\r\t\u0011\u0015mr\u0006)A\u0005\u000bgA\u0011\"\"\u00100\u0005\u0004%\u0019!b\u0010\t\u0011\u0015\u001ds\u0006)A\u0005\u000b\u00032\u0011\u0002b4\u0016!\u0003\r\t\u0003\"5\t\u000f\u0011MW\b\"\u0001\u0002@\"9AQ[\u001f\u0005\u0004\u0011]\u0007\"\u0003Cr{\t\u0007I\u0011\u0002Cs\u0005-\t5mY;nk2\fGo\u001c:\u000b\u0005\r#\u0015a\u00016eW*\tQ)A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u000b!;\u0016\u0011\u0002?\u0014\t\u0001IU\n\u001f\t\u0003\u0015.k\u0011\u0001R\u0005\u0003\u0019\u0012\u0013a!\u00118z%\u00164\u0007c\u0001(T+6\tqJ\u0003\u0002Q#\u00069Q.\u001e;bE2,'B\u0001*E\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003)>\u00131aU3r!\t1v\u000b\u0004\u0001\u0005\u0013a\u0003\u0001\u0015!A\u0001\u0006\u0004I&!A!\u0012\u0005ik\u0006C\u0001&\\\u0013\taFIA\u0004O_RD\u0017N\\4\u0011\u0005)s\u0016BA0E\u0005\r\te.\u001f\u0015\u0006/\u0006$gn\u001d\t\u0003\u0015\nL!a\u0019#\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\u00154\u0007n\u001a\b\u0003\u0015\u001aL!a\u001a#\u0002\r\u0011{WO\u00197fc\u0011!\u0013.\\#\u000f\u0005)lW\"A6\u000b\u000514\u0015A\u0002\u001fs_>$h(C\u0001Fc\u0015\u0019s\u000e\u001d:r\u001d\tQ\u0005/\u0003\u0002r\t\u0006\u0019\u0011J\u001c;2\t\u0011JW.R\u0019\u0006GQ,xO\u001e\b\u0003\u0015VL!A\u001e#\u0002\t1{gnZ\u0019\u0005I%lW\t\u0005\u0003OsV[\u0018B\u0001>P\u0005\u001d\u0011U/\u001b7eKJ\u0004\"A\u0016?\u0005\ru\u0004AQ1\u0001\u007f\u0005\u0005\u0019\u0015C\u0001.N\u0003\u0019a\u0014N\\5u}Q\u0011\u00111\u0001\t\b\u0003\u000b\u0001Q+a\u0002|\u001b\u0005\u0011\u0005c\u0001,\u0002\n\u0011A\u00111\u0002\u0001\u0005\u0006\u0004\tiA\u0001\u0002D\u0007V!\u0011qBA\u000b#\rQ\u0016\u0011\u0003\t\u0005\u001dN\u000b\u0019\u0002E\u0002W\u0003+!q!a\u0006\u0002\n\t\u0007\u0011LA\u0001Y\u0003\u0015Ig\u000eZ3y+\t\ti\u0002E\u0002K\u0003?I1!!\tE\u0005\rIe\u000e^\u0001\nS:$W\r_0%KF$B!a\n\u0002.A\u0019!*!\u000b\n\u0007\u0005-BI\u0001\u0003V]&$\b\"CA\u0018\u0007\u0005\u0005\t\u0019AA\u000f\u0003\rAH%M\u0001\u0007S:$W\r\u001f\u0011\u0002\r!Le\u000eZ3y\u0003)A\u0017J\u001c3fq~#S-\u001d\u000b\u0005\u0003O\tI\u0004C\u0005\u00020\u0019\t\t\u00111\u0001\u0002\u001e\u00059\u0001.\u00138eKb\u0004\u0013!\u0003;pi\u0006d7+\u001b>f+\t\t\t\u0005E\u0002K\u0003\u0007J1!!\u0012E\u0005\u0011auN\\4\u0002\u001bQ|G/\u00197TSj,w\fJ3r)\u0011\t9#a\u0013\t\u0013\u0005=\u0012\"!AA\u0002\u0005\u0005\u0013A\u0003;pi\u0006d7+\u001b>fA\u0005Q1-^7vY\u0006$\u0018N^3\u0015\t\u0005\u0005\u00131\u000b\u0005\b\u0003+Z\u0001\u0019AA\u000f\u0003\u0005I\u0017!\u00048fqR\u0014En\\2l'&TX-\u0001\tfM\u001aL7-[3oiN#X\r\u001d9feV!\u0011QLA4)\u0011\ty&a$\u0013\r\u0005\u0005\u0014QMA?\r\u0019\t\u0019\u0007\u0001\u0001\u0002`\taAH]3gS:,W.\u001a8u}A\u0019a+a\u001a\u0005\u000f\u0005%TB1\u0001\u0002l\t\t1+E\u0002[\u0003[\u0002D!a\u001c\u0002zA1\u0011\u0011OA:\u0003oj\u0011!U\u0005\u0004\u0003k\n&aB*uKB\u0004XM\u001d\t\u0004-\u0006eDaCA>\u0003O\n\t\u0011!A\u0003\u0002e\u00131a\u0018\u00132!\u0011\ty(!#\u000f\t\u0005\u0005\u0015Q\u0011\b\u0004S\u0006\r\u0015B\u0001*E\u0013\r\t9)U\u0001\b'R,\u0007\u000f]3s\u0013\u0011\tY)!$\u0003\u001d\u00153g-[2jK:$8\u000b\u001d7ji*\u0019\u0011qQ)\t\u000f\u0005EU\u0002q\u0001\u0002\u0014\u0006)1\u000f[1qKB9\u0011\u0011OAK+\u0006\u0015\u0014bAAL#\na1\u000b^3qa\u0016\u00148\u000b[1qK\u000691\u000f^3qa\u0016\u0014X\u0003BAO\u0003K#B!a(\u00024J1\u0011\u0011UAR\u0003{2a!a\u0019\u0001\u0001\u0005}\u0005c\u0001,\u0002&\u00129\u0011\u0011\u000e\bC\u0002\u0005\u001d\u0016c\u0001.\u0002*B\"\u00111VAX!\u0019\t\t(a\u001d\u0002.B\u0019a+a,\u0005\u0017\u0005E\u0016QUA\u0001\u0002\u0003\u0015\t!\u0017\u0002\u0004?\u0012\u0012\u0004bBAI\u001d\u0001\u000f\u0011Q\u0017\t\b\u0003c\n)*VAR\u0003\u0019aWM\\4uQ\u0006I1N\\8x]NK'0Z\u0001\tg&TX\rT8oO\u0006)1\r\\3beR\u0011\u0011qE\u0001\tg\u0016,7n\u00157piR!\u0011\u0011IAc\u0011\u001d\t9m\u0005a\u0001\u0003\u0003\n!!\u001b=\u0002\u0017\u0005\u001b7-^7vY\u0006$xN\u001d\t\u0004\u0003\u000b)2CA\u000bJ)\t\tY-A\u0005u_\u001a\u000b7\r^8ssV1\u0011Q[Aq\u0003K$B!a6\u0003\"Q!\u0011\u0011\\At!!\t\t(a7\u0002`\u0006\r\u0018bAAo#\n9a)Y2u_JL\bc\u0001,\u0002b\u0012)\u0001l\u0006b\u00013B\u0019a+!:\u0005\u000bu<\"\u0019A-\t\u000f\u0005%x\u0003q\u0001\u0002l\u0006i1-\u00198BG\u000e,X.\u001e7bi\u0016\u0004r!!<,\u0003?\f\u0019/D\u0001\u0016\u0005]\t5mY;nk2\fGo\u001c:GC\u000e$xN]=TQ\u0006\u0004X-\u0006\u0004\u0002t\u0006u(\u0011A\n\u0003W%\u000bqAZ1di>\u0014\u00180\u0006\u0002\u0002zBA\u0011\u0011OAn\u0003w\fy\u0010E\u0002W\u0003{$Q\u0001W\u0016C\u0002e\u00032A\u0016B\u0001\t\u0015i8F1\u0001Z\u0003\u0015)W\u000e\u001d;z+\t\ty0K\u0002,\u0005\u00131aAa\u0003,\u0001\t5!!\u0004\u001fm_\u000e\fG\u000eI2iS2$gh\u0005\u0004\u0003\n\t=!q\u0004\t\u0005\u0005#\u0011Y\"\u0004\u0002\u0003\u0014)!!Q\u0003B\f\u0003\u0011a\u0017M\\4\u000b\u0005\te\u0011\u0001\u00026bm\u0006LAA!\b\u0003\u0014\t1qJ\u00196fGR\u0004r!!<,\u0003w\fy\u0010C\u0004\u0003$]\u0001\rA!\n\u0002\u0005M\fgbAA\u0003)\u0005!aM]8n+\u0019\u0011YC!\u000f\u00032Q!!Q\u0006B\u001e)\u0011\u0011yCa\r\u0011\u0007Y\u0013\t\u0004B\u0003~1\t\u0007\u0011\fC\u0004\u0002jb\u0001\u001dA!\u000e\u0011\u000f\u000558Fa\u000e\u00030A\u0019aK!\u000f\u0005\u000baC\"\u0019A-\t\u000f\tu\u0002\u00041\u0001\u0003@\u000511o\\;sG\u0016\u0004bA!\u0011\u0003H\t]bbA5\u0003D%\u0019!Q\t#\u0002\u000fA\f7m[1hK&!!\u0011\nB&\u00051IE/\u001a:bE2,wJ\\2f\u0015\r\u0011)\u0005R\u000b\u0007\u0005\u001f\u0012YFa\u0015\u0015\t\tE#Q\u000b\t\u0004-\nMC!B?\u001a\u0005\u0004I\u0006bBAu3\u0001\u000f!q\u000b\t\b\u0003[\\#\u0011\fB)!\r1&1\f\u0003\u00061f\u0011\r!W\u0001\u0006CB\u0004H._\u000b\u0007\u0005C\u0012yGa\u001a\u0015\t\t\r$\u0011\u000f\u000b\u0005\u0005K\u0012I\u0007E\u0002W\u0005O\"Q! \u000eC\u0002eCq!!;\u001b\u0001\b\u0011Y\u0007E\u0004\u0002n.\u0012iG!\u001a\u0011\u0007Y\u0013y\u0007B\u0003Y5\t\u0007\u0011\fC\u0004\u0003ti\u0001\rA!\u001e\u0002\u000b\u0015dW-\\:\u0011\u000b)\u00139H!\u001c\n\u0007\teDI\u0001\u0006=e\u0016\u0004X-\u0019;fIz\nq!\u001b;fe\u0006$X-\u0006\u0004\u0003\u0000\t=%q\u0011\u000b\u0007\u0005\u0003\u0013YJa(\u0015\t\t\r%\u0011\u0013\u000b\u0005\u0005\u000b\u0013I\tE\u0002W\u0005\u000f#Q!`\u000eC\u0002eCq!!;\u001c\u0001\b\u0011Y\tE\u0004\u0002n.\u0012iI!\"\u0011\u0007Y\u0013y\tB\u0003Y7\t\u0007\u0011\fC\u0004\u0003\u0014n\u0001\rA!&\u0002\u0003\u0019\u0004rA\u0013BL\u0005\u001b\u0013i)C\u0002\u0003\u001a\u0012\u0013\u0011BR;oGRLwN\\\u0019\t\u000f\tu5\u00041\u0001\u0003\u000e\u0006)1\u000f^1si\"9!\u0011U\u000eA\u0002\u0005u\u0011a\u00017f]\u00061QO\u001c4pY\u0012,\u0002Ba*\u00038\n}&q\u0016\u000b\u0005\u0005S\u0013i\r\u0006\u0003\u0003,\neF\u0003\u0002BW\u0005c\u00032A\u0016BX\t\u0015iHD1\u0001Z\u0011\u001d\tI\u000f\ba\u0002\u0005g\u0003r!!<,\u0005k\u0013i\u000bE\u0002W\u0005o#Q\u0001\u0017\u000fC\u0002eCqAa%\u001d\u0001\u0004\u0011Y\fE\u0004K\u0005/\u0013iL!1\u0011\u0007Y\u0013y\f\u0002\u0004\u0002jq\u0011\r!\u0017\t\u0006\u0015\n\r'qY\u0005\u0004\u0005\u000b$%AB(qi&|g\u000eE\u0004K\u0005\u0013\u0014)L!0\n\u0007\t-GI\u0001\u0004UkBdWM\r\u0005\b\u0005\u001fd\u0002\u0019\u0001B_\u0003\u0011Ig.\u001b;\u0002\u000bI\fgnZ3\u0016\r\tU'\u0011\u001eBn)\u0019\u00119Na<\u0003rR1!\u0011\u001cBo\u0005W\u00042A\u0016Bn\t\u0015iXD1\u0001Z\u0011%\u0011y.HA\u0001\u0002\b\u0011\t/\u0001\u0006fm&$WM\\2fIE\u0002bA!\u0011\u0003d\n\u001d\u0018\u0002\u0002Bs\u0005\u0017\u0012\u0001\"\u00138uK\u001e\u0014\u0018\r\u001c\t\u0004-\n%H!\u0002-\u001e\u0005\u0004I\u0006bBAu;\u0001\u000f!Q\u001e\t\b\u0003[\\#q\u001dBm\u0011\u001d\u0011i*\ba\u0001\u0005ODqAa=\u001e\u0001\u0004\u00119/A\u0002f]\u0012,bAa>\u0004\b\tuH\u0003\u0003B}\u0007\u001b\u0019ya!\u0005\u0015\r\tm(q`B\u0005!\r1&Q \u0003\u0006{z\u0011\r!\u0017\u0005\n\u0007\u0003q\u0012\u0011!a\u0002\u0007\u0007\t!\"\u001a<jI\u0016t7-\u001a\u00133!\u0019\u0011\tEa9\u0004\u0006A\u0019aka\u0002\u0005\u000bas\"\u0019A-\t\u000f\u0005%h\u0004q\u0001\u0004\fA9\u0011Q^\u0016\u0004\u0006\tm\bb\u0002BO=\u0001\u00071Q\u0001\u0005\b\u0005gt\u0002\u0019AB\u0003\u0011\u001d\u0019\u0019B\ba\u0001\u0007\u000b\tAa\u001d;fa\u0006Qa.Z<Ck&dG-\u001a:\u0016\r\re1qDB\u0012)\u0011\u0019Yb!\n\u0011\r9K8QDB\u0011!\r16q\u0004\u0003\u00061~\u0011\r!\u0017\t\u0004-\u000e\rB!B? \u0005\u0004I\u0006bBAu?\u0001\u000f1q\u0005\t\b\u0003[\\3QDB\u0011\u0003\u00111\u0017\u000e\u001c7\u0016\r\r52QHB\u001b)\u0011\u0019yc!\u0013\u0015\t\rE2q\b\u000b\u0005\u0007g\u00199\u0004E\u0002W\u0007k!Q! \u0011C\u0002eCq!!;!\u0001\b\u0019I\u0004E\u0004\u0002n.\u001aYda\r\u0011\u0007Y\u001bi\u0004B\u0003YA\t\u0007\u0011\f\u0003\u0005\u0004B\u0001\"\t\u0019AB\"\u0003\u0011)G.Z7\u0011\u000b)\u001b)ea\u000f\n\u0007\r\u001dCI\u0001\u0005=Eft\u0017-\\3?\u0011\u001d\u0019Y\u0005\ta\u0001\u0003;\t\u0011A\\\u000b\u0007\u0007\u001f\u001a)g!\u0018\u0015\r\rE31NB8)\u0011\u0019\u0019fa\u001a\u0015\t\rU3q\f\t\u0007\u0003\u000b\u00199fa\u0017\n\u0007\re#I\u0001\bB]f\f5mY;nk2\fGo\u001c:\u0011\u0007Y\u001bi\u0006B\u0003~C\t\u0007\u0011\fC\u0004\u0002j\u0006\u0002\u001da!\u0019\u0011\u000f\u000558fa\u0019\u0004\\A\u0019ak!\u001a\u0005\u000ba\u000b#\u0019A-\t\u0011\r\u0005\u0013\u0005\"a\u0001\u0007S\u0002RASB#\u0007GBqa!\u001c\"\u0001\u0004\ti\"\u0001\u0002oc!91\u0011O\u0011A\u0002\u0005u\u0011A\u000183+\u0019\u0019)h!#\u0004\u0002RA1qOBH\u0007#\u001b\u0019\n\u0006\u0003\u0004z\r-E\u0003BB>\u0007\u0007\u0003b!!\u0002\u0004X\ru\u0004CBA\u0003\u0007/\u001ay\bE\u0002W\u0007\u0003#Q! \u0012C\u0002eCq!!;#\u0001\b\u0019)\tE\u0004\u0002n.\u001a9ia \u0011\u0007Y\u001bI\tB\u0003YE\t\u0007\u0011\f\u0003\u0005\u0004B\t\"\t\u0019ABG!\u0015Q5QIBD\u0011\u001d\u0019iG\ta\u0001\u0003;Aqa!\u001d#\u0001\u0004\ti\u0002C\u0004\u0004\u0016\n\u0002\r!!\b\u0002\u00059\u001cTCBBM\u0007_\u001b9\u000b\u0006\u0006\u0004\u001c\u000eU6qWB]\u0007w#Ba!(\u00042R!1qTBU!\u0019\t)aa\u0016\u0004\"B1\u0011QAB,\u0007G\u0003b!!\u0002\u0004X\r\u0015\u0006c\u0001,\u0004(\u0012)Qp\tb\u00013\"9\u0011\u0011^\u0012A\u0004\r-\u0006cBAwW\r56Q\u0015\t\u0004-\u000e=F!\u0002-$\u0005\u0004I\u0006\u0002CB!G\u0011\u0005\raa-\u0011\u000b)\u001b)e!,\t\u000f\r54\u00051\u0001\u0002\u001e!91\u0011O\u0012A\u0002\u0005u\u0001bBBKG\u0001\u0007\u0011Q\u0004\u0005\b\u0007{\u001b\u0003\u0019AA\u000f\u0003\tqG'\u0006\u0004\u0004B\u000ee7\u0011\u001b\u000b\r\u0007\u0007\u001cyn!9\u0004d\u000e\u00158q\u001d\u000b\u0005\u0007\u000b\u001cY\u000e\u0006\u0003\u0004H\u000eM\u0007CBA\u0003\u0007/\u001aI\r\u0005\u0004\u0002\u0006\r]31\u001a\t\u0007\u0003\u000b\u00199f!4\u0011\r\u0005\u00151qKBh!\r16\u0011\u001b\u0003\u0006{\u0012\u0012\r!\u0017\u0005\b\u0003S$\u00039ABk!\u001d\tioKBl\u0007\u001f\u00042AVBm\t\u0015AFE1\u0001Z\u0011!\u0019\t\u0005\nCA\u0002\ru\u0007#\u0002&\u0004F\r]\u0007bBB7I\u0001\u0007\u0011Q\u0004\u0005\b\u0007c\"\u0003\u0019AA\u000f\u0011\u001d\u0019)\n\na\u0001\u0003;Aqa!0%\u0001\u0004\ti\u0002C\u0004\u0004j\u0012\u0002\r!!\b\u0002\u00059,\u0014\u0001\u0003;bEVd\u0017\r^3\u0016\r\r=8q`B|)\u0011\u0019\t\u0010\"\u0002\u0015\t\rMH\u0011\u0001\u000b\u0005\u0007k\u001cI\u0010E\u0002W\u0007o$Q!`\u0013C\u0002eCq!!;&\u0001\b\u0019Y\u0010E\u0004\u0002n.\u001aip!>\u0011\u0007Y\u001by\u0010B\u0003YK\t\u0007\u0011\fC\u0004\u0003\u0014\u0016\u0002\r\u0001b\u0001\u0011\u000f)\u00139*!\b\u0004~\"911J\u0013A\u0002\u0005uQC\u0002C\u0005\t7!\u0019\u0002\u0006\u0004\u0005\f\u0011\u0015Bq\u0005\u000b\u0005\t\u001b!i\u0002\u0006\u0003\u0005\u0010\u0011U\u0001CBA\u0003\u0007/\"\t\u0002E\u0002W\t'!Q! \u0014C\u0002eCq!!;'\u0001\b!9\u0002E\u0004\u0002n.\"I\u0002\"\u0005\u0011\u0007Y#Y\u0002B\u0003YM\t\u0007\u0011\fC\u0004\u0003\u0014\u001a\u0002\r\u0001b\b\u0011\u0013)#\t#!\b\u0002\u001e\u0011e\u0011b\u0001C\u0012\t\nIa)\u001e8di&|gN\r\u0005\b\u0007[2\u0003\u0019AA\u000f\u0011\u001d\u0019\tH\na\u0001\u0003;)b\u0001b\u000b\u0005@\u0011]B\u0003\u0003C\u0017\t\u0013\"Y\u0005\"\u0014\u0015\t\u0011=B\u0011\t\u000b\u0005\tc!I\u0004\u0005\u0004\u0002\u0006\r]C1\u0007\t\u0007\u0003\u000b\u00199\u0006\"\u000e\u0011\u0007Y#9\u0004B\u0003~O\t\u0007\u0011\fC\u0004\u0002j\u001e\u0002\u001d\u0001b\u000f\u0011\u000f\u000558\u0006\"\u0010\u00056A\u0019a\u000bb\u0010\u0005\u000ba;#\u0019A-\t\u000f\tMu\u00051\u0001\u0005DAY!\n\"\u0012\u0002\u001e\u0005u\u0011Q\u0004C\u001f\u0013\r!9\u0005\u0012\u0002\n\rVt7\r^5p]NBqa!\u001c(\u0001\u0004\ti\u0002C\u0004\u0004r\u001d\u0002\r!!\b\t\u000f\rUu\u00051\u0001\u0002\u001eU1A\u0011\u000bC4\t?\"\"\u0002b\u0015\u0005r\u0011MDQ\u000fC<)\u0011!)\u0006\"\u001b\u0015\t\u0011]C\u0011\r\t\u0007\u0003\u000b\u00199\u0006\"\u0017\u0011\r\u0005\u00151q\u000bC.!\u0019\t)aa\u0016\u0005^A\u0019a\u000bb\u0018\u0005\u000buD#\u0019A-\t\u000f\u0005%\b\u0006q\u0001\u0005dA9\u0011Q^\u0016\u0005f\u0011u\u0003c\u0001,\u0005h\u0011)\u0001\f\u000bb\u00013\"9!1\u0013\u0015A\u0002\u0011-\u0004#\u0004&\u0005n\u0005u\u0011QDA\u000f\u0003;!)'C\u0002\u0005p\u0011\u0013\u0011BR;oGRLwN\u001c\u001b\t\u000f\r5\u0004\u00061\u0001\u0002\u001e!91\u0011\u000f\u0015A\u0002\u0005u\u0001bBBKQ\u0001\u0007\u0011Q\u0004\u0005\b\u0007{C\u0003\u0019AA\u000f+\u0019!Y\bb%\u0005\fRaAQ\u0010CO\t?#\t\u000bb)\u0005&R!Aq\u0010CK)\u0011!\t\t\"$\u0011\r\u0005\u00151q\u000bCB!\u0019\t)aa\u0016\u0005\u0006B1\u0011QAB,\t\u000f\u0003b!!\u0002\u0004X\u0011%\u0005c\u0001,\u0005\f\u0012)Q0\u000bb\u00013\"9\u0011\u0011^\u0015A\u0004\u0011=\u0005cBAwW\u0011EE\u0011\u0012\t\u0004-\u0012ME!\u0002-*\u0005\u0004I\u0006b\u0002BJS\u0001\u0007Aq\u0013\t\u0010\u0015\u0012e\u0015QDA\u000f\u0003;\ti\"!\b\u0005\u0012&\u0019A1\u0014#\u0003\u0013\u0019+hn\u0019;j_:,\u0004bBB7S\u0001\u0007\u0011Q\u0004\u0005\b\u0007cJ\u0003\u0019AA\u000f\u0011\u001d\u0019)*\u000ba\u0001\u0003;Aqa!0*\u0001\u0004\ti\u0002C\u0004\u0004j&\u0002\r!!\b\u0002\r\r|gnY1u+\u0019!Y\u000b\"/\u00052R!AQ\u0016C^)\u0011!y\u000bb-\u0011\u0007Y#\t\fB\u0003~U\t\u0007\u0011\fC\u0004\u0002j*\u0002\u001d\u0001\".\u0011\u000f\u000558\u0006b.\u00050B\u0019a\u000b\"/\u0005\u000baS#\u0019A-\t\u000f\u0011u&\u00061\u0001\u0005@\u0006\u0019\u0001p]:\u0011\u000b)\u00139\b\"1\u0011\r\t\u0005C1\u0019C\\\u0013\u0011!)Ma\u0013\u0003\u0011%#XM]1cY\u0016\fq#Q2dk6,H.\u0019;pe\u001a\u000b7\r^8ssNC\u0017\r]3\u0011\u0007\u00055xf\u0005\u00030\u0013\u00125\u0007cAAw{\t\u0011Cj\\<Qe&|'/\u001b;z\u0003\u000e\u001cW/\\;mCR|'OR1di>\u0014\u0018p\u00155ba\u0016\u001c\"!P%\u0002\r\u0011Jg.\u001b;%\u0003i\tg._!dGVlW\u000f\\1u_J4\u0015m\u0019;pef\u001c\u0006.\u00199f+\u0011!I\u000eb8\u0016\u0005\u0011m\u0007cBAwW\u0011uG\u0011\u001d\t\u0004-\u0012}G!\u0002-@\u0005\u0004I\u0006CBA\u0003\u0007/\"i.A\u0012b]f\f5mY;nk2\fGo\u001c:GC\u000e$xN]=TQ\u0006\u0004X\r\u0015:pi>$\u0018\u0010]3\u0016\u0005\u0011\u001d(#\u0002Cu\u0013\u0012-hABA2\u0001\u0002!9\u000f\u0005\u0004\u0002n.JEQ\u001e\t\u0006\u0003\u000b\u00199&S\u0015\u0003{=\"\"\u0001\"3\u0002;\u0011|WO\u00197f\u0003\u000e\u001cW/\\;mCR|'OR1di>\u0014\u0018p\u00155ba\u0016,\"\u0001b>\u0011\u000f\u000558\u0006\"?\u0005\u0000B\u0019!\nb?\n\u0007\u0011uHI\u0001\u0004E_V\u0014G.\u001a\t\u0005\u0003\u000b)\t!C\u0002\u0006\u0004\t\u0013\u0011\u0003R8vE2,\u0017iY2v[Vd\u0017\r^8s\u0003y!w.\u001e2mK\u0006\u001b7-^7vY\u0006$xN\u001d$bGR|'/_*iCB,\u0007%\u0001\u000ej]R\f5mY;nk2\fGo\u001c:GC\u000e$xN]=TQ\u0006\u0004X-\u0006\u0002\u0006\fA9\u0011Q^\u0016\u0002\u001e\u00155\u0001\u0003BA\u0003\u000b\u001fI1!\"\u0005C\u00059Ie\u000e^!dGVlW\u000f\\1u_J\f1$\u001b8u\u0003\u000e\u001cW/\\;mCR|'OR1di>\u0014\u0018p\u00155ba\u0016\u0004\u0013a\u00077p]\u001e\f5mY;nk2\fGo\u001c:GC\u000e$xN]=TQ\u0006\u0004X-\u0006\u0002\u0006\u001aA9\u0011Q^\u0016\u0002B\u0015m\u0001\u0003BA\u0003\u000b;I1!b\bC\u0005=auN\\4BG\u000e,X.\u001e7bi>\u0014\u0018\u0001\b7p]\u001e\f5mY;nk2\fGo\u001c:GC\u000e$xN]=TQ\u0006\u0004X\rI\u0001\u001fU\u0012{WO\u00197f\u0003\u000e\u001cW/\\;mCR|'OR1di>\u0014\u0018p\u00155ba\u0016,\"!b\n\u0011\u000f\u000558&\"\u000b\u0005\u0000B!!\u0011CC\u0016\u0013\u0011!iPa\u0005\u0002?)$u.\u001e2mK\u0006\u001b7-^7vY\u0006$xN\u001d$bGR|'/_*iCB,\u0007%A\u0010k\u0013:$XmZ3s\u0003\u000e\u001cW/\\;mCR|'OR1di>\u0014\u0018p\u00155ba\u0016,\"!b\r\u0011\u000f\u000558&\"\u000e\u0006\u000eA!!\u0011CC\u001c\u0013\u0011)IDa\u0005\u0003\u000f%sG/Z4fe\u0006\u0001#.\u00138uK\u001e,'/Q2dk6,H.\u0019;pe\u001a\u000b7\r^8ssNC\u0017\r]3!\u0003qQGj\u001c8h\u0003\u000e\u001cW/\\;mCR|'OR1di>\u0014\u0018p\u00155ba\u0016,\"!\"\u0011\u0011\u000f\u000558&b\u0011\u0006\u001cA!!\u0011CC#\u0013\u0011\t)Ea\u0005\u0002;)duN\\4BG\u000e,X.\u001e7bi>\u0014h)Y2u_JL8\u000b[1qK\u0002\u0002"
)
public abstract class Accumulator implements Seq, Builder {
   private int index = 0;
   private int hIndex = 0;
   private long totalSize = 0L;

   public static AnyAccumulator tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ tabulate_this = Accumulator$.MODULE$;
      Function1 var10000 = Accumulator$::$anonfun$tabulate$7$adapted;
      AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var11 = null;
      AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var10000;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n1, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   public static AnyAccumulator tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ tabulate_this = Accumulator$.MODULE$;
      Function1 var10000 = Accumulator$::$anonfun$tabulate$5$adapted;
      AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var10 = null;
      AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var10000;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n1, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   public static AnyAccumulator tabulate(final int n1, final int n2, final int n3, final Function3 f, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ tabulate_this = Accumulator$.MODULE$;
      Function1 var10000 = Accumulator$::$anonfun$tabulate$3$adapted;
      AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var9 = null;
      AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var10000;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n1, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   public static AnyAccumulator tabulate(final int n1, final int n2, final Function2 f, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ tabulate_this = Accumulator$.MODULE$;
      Function1 var10000 = Accumulator$::$anonfun$tabulate$1$adapted;
      AccumulatorFactoryShape$ tabulate_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = tabulate_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var8 = null;
      AccumulatorFactoryShape tabulate_tabulate_canAccumulate = var10001;
      Function1 tabulate_tabulate_f = var10000;
      return (AnyAccumulator)tabulate_this.from(new View.Tabulate(n1, tabulate_tabulate_f), tabulate_tabulate_canAccumulate);
   }

   public static Object tabulate(final int n, final Function1 f, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.from(new View.Tabulate(n, f), canAccumulate);
   }

   public static AnyAccumulator fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ fill_this = Accumulator$.MODULE$;
      Function0 var10000 = Accumulator$::$anonfun$fill$4;
      AccumulatorFactoryShape$ fill_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = fill_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var11 = null;
      AccumulatorFactoryShape fill_fill_canAccumulate = var10001;
      Function0 fill_fill_elem = var10000;
      return (AnyAccumulator)fill_this.from(new View.Fill(n1, fill_fill_elem), fill_fill_canAccumulate);
   }

   public static AnyAccumulator fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ fill_this = Accumulator$.MODULE$;
      Function0 var10000 = Accumulator$::$anonfun$fill$3;
      AccumulatorFactoryShape$ fill_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = fill_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var10 = null;
      AccumulatorFactoryShape fill_fill_canAccumulate = var10001;
      Function0 fill_fill_elem = var10000;
      return (AnyAccumulator)fill_this.from(new View.Fill(n1, fill_fill_elem), fill_fill_canAccumulate);
   }

   public static AnyAccumulator fill(final int n1, final int n2, final int n3, final Function0 elem, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ fill_this = Accumulator$.MODULE$;
      Function0 var10000 = Accumulator$::$anonfun$fill$2;
      AccumulatorFactoryShape$ fill_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = fill_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var9 = null;
      AccumulatorFactoryShape fill_fill_canAccumulate = var10001;
      Function0 fill_fill_elem = var10000;
      return (AnyAccumulator)fill_this.from(new View.Fill(n1, fill_fill_elem), fill_fill_canAccumulate);
   }

   public static AnyAccumulator fill(final int n1, final int n2, final Function0 elem, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ fill_this = Accumulator$.MODULE$;
      Function0 var10000 = Accumulator$::$anonfun$fill$1;
      AccumulatorFactoryShape$ fill_anyAccumulatorFactoryShape_this = Accumulator.AccumulatorFactoryShape$.MODULE$;
      AccumulatorFactoryShape var10001 = fill_anyAccumulatorFactoryShape_this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      Object var8 = null;
      AccumulatorFactoryShape fill_fill_canAccumulate = var10001;
      Function0 fill_fill_elem = var10000;
      return (AnyAccumulator)fill_this.from(new View.Fill(n1, fill_fill_elem), fill_fill_canAccumulate);
   }

   public static Object fill(final int n, final Function0 elem, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.from(new View.Fill(n, elem), canAccumulate);
   }

   public static Builder newBuilder(final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.newBuilder(canAccumulate);
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$2, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.range(start, end, step, evidence$2, canAccumulate);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$1, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.range(start, end, evidence$1, canAccumulate);
   }

   public static Object unfold(final Object init, final Function1 f, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.from(new View.Unfold(init, f), canAccumulate);
   }

   public static Object iterate(final Object start, final int len, final Function1 f, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.from(new View.Iterate(start, len, f), canAccumulate);
   }

   public static Object from(final IterableOnce source, final AccumulatorFactoryShape canAccumulate) {
      return Accumulator$.MODULE$.from(source, canAccumulate);
   }

   public static Factory toFactory(final Accumulator$ sa, final AccumulatorFactoryShape canAccumulate) {
      Accumulator$ var10000 = Accumulator$.MODULE$;
      return canAccumulate.factory();
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

   public final void sizeHintBounded(final int size, final Iterable boundingColl) {
      Builder.sizeHintBounded$(this, size, boundingColl);
   }

   public Builder mapResult(final Function1 f) {
      return Builder.mapResult$(this, f);
   }

   public final Growable $plus$eq(final Object elem) {
      return Growable.$plus$eq$(this, elem);
   }

   /** @deprecated */
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public SeqFactory iterableFactory() {
      return Seq.iterableFactory$(this);
   }

   public Object clone() {
      return SeqOps.clone$(this);
   }

   /** @deprecated */
   public final SeqOps transform(final Function1 f) {
      return SeqOps.transform$(this, f);
   }

   // $FF: synthetic method
   public Object scala$collection$mutable$Cloneable$$super$clone() {
      return super.clone();
   }

   public boolean canEqual(final Object that) {
      return scala.collection.Seq.canEqual$(this, that);
   }

   public boolean equals(final Object o) {
      return scala.collection.Seq.equals$(this, o);
   }

   public int hashCode() {
      return scala.collection.Seq.hashCode$(this);
   }

   public String toString() {
      return scala.collection.Seq.toString$(this);
   }

   public String stringPrefix() {
      return scala.collection.Seq.stringPrefix$(this);
   }

   // $FF: synthetic method
   public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
      return IterableOps.concat$(this, suffix);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
      return IterableOps.sizeCompare$(this, otherSize);
   }

   // $FF: synthetic method
   public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
      return IterableOps.sizeCompare$(this, that);
   }

   public SeqView view() {
      return scala.collection.SeqOps.view$(this);
   }

   public Object prepended(final Object elem) {
      return scala.collection.SeqOps.prepended$(this, elem);
   }

   public final Object $plus$colon(final Object elem) {
      return scala.collection.SeqOps.$plus$colon$(this, elem);
   }

   public Object appended(final Object elem) {
      return scala.collection.SeqOps.appended$(this, elem);
   }

   public final Object $colon$plus(final Object elem) {
      return scala.collection.SeqOps.$colon$plus$(this, elem);
   }

   public Object prependedAll(final IterableOnce prefix) {
      return scala.collection.SeqOps.prependedAll$(this, prefix);
   }

   public final Object $plus$plus$colon(final IterableOnce prefix) {
      return scala.collection.SeqOps.$plus$plus$colon$(this, prefix);
   }

   public Object appendedAll(final IterableOnce suffix) {
      return scala.collection.SeqOps.appendedAll$(this, suffix);
   }

   public final Object $colon$plus$plus(final IterableOnce suffix) {
      return scala.collection.SeqOps.$colon$plus$plus$(this, suffix);
   }

   public final Object concat(final IterableOnce suffix) {
      return scala.collection.SeqOps.concat$(this, suffix);
   }

   /** @deprecated */
   public final Object union(final scala.collection.Seq that) {
      return scala.collection.SeqOps.union$(this, that);
   }

   public final int size() {
      return scala.collection.SeqOps.size$(this);
   }

   public Object distinct() {
      return scala.collection.SeqOps.distinct$(this);
   }

   public Object distinctBy(final Function1 f) {
      return scala.collection.SeqOps.distinctBy$(this, f);
   }

   public Object reverse() {
      return scala.collection.SeqOps.reverse$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.SeqOps.reverseIterator$(this);
   }

   public boolean startsWith(final IterableOnce that, final int offset) {
      return scala.collection.SeqOps.startsWith$(this, that, offset);
   }

   public int startsWith$default$2() {
      return scala.collection.SeqOps.startsWith$default$2$(this);
   }

   public boolean endsWith(final Iterable that) {
      return scala.collection.SeqOps.endsWith$(this, that);
   }

   public boolean isDefinedAt(final int idx) {
      return scala.collection.SeqOps.isDefinedAt$(this, idx);
   }

   public Object padTo(final int len, final Object elem) {
      return scala.collection.SeqOps.padTo$(this, len, elem);
   }

   public final int segmentLength(final Function1 p) {
      return scala.collection.SeqOps.segmentLength$(this, p);
   }

   public int segmentLength(final Function1 p, final int from) {
      return scala.collection.SeqOps.segmentLength$(this, p, from);
   }

   /** @deprecated */
   public final int prefixLength(final Function1 p) {
      return scala.collection.SeqOps.prefixLength$(this, p);
   }

   public int indexWhere(final Function1 p, final int from) {
      return scala.collection.SeqOps.indexWhere$(this, p, from);
   }

   public int indexWhere(final Function1 p) {
      return scala.collection.SeqOps.indexWhere$(this, p);
   }

   public int indexOf(final Object elem, final int from) {
      return scala.collection.SeqOps.indexOf$(this, elem, from);
   }

   public int indexOf(final Object elem) {
      return scala.collection.SeqOps.indexOf$(this, elem);
   }

   public int lastIndexOf(final Object elem, final int end) {
      return scala.collection.SeqOps.lastIndexOf$(this, elem, end);
   }

   public int lastIndexOf$default$2() {
      return scala.collection.SeqOps.lastIndexOf$default$2$(this);
   }

   public int lastIndexWhere(final Function1 p, final int end) {
      return scala.collection.SeqOps.lastIndexWhere$(this, p, end);
   }

   public int lastIndexWhere(final Function1 p) {
      return scala.collection.SeqOps.lastIndexWhere$(this, p);
   }

   public int indexOfSlice(final scala.collection.Seq that, final int from) {
      return scala.collection.SeqOps.indexOfSlice$(this, that, from);
   }

   public int indexOfSlice(final scala.collection.Seq that) {
      return scala.collection.SeqOps.indexOfSlice$(this, that);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that, final int end) {
      return scala.collection.SeqOps.lastIndexOfSlice$(this, that, end);
   }

   public int lastIndexOfSlice(final scala.collection.Seq that) {
      return scala.collection.SeqOps.lastIndexOfSlice$(this, that);
   }

   public Option findLast(final Function1 p) {
      return scala.collection.SeqOps.findLast$(this, p);
   }

   public boolean containsSlice(final scala.collection.Seq that) {
      return scala.collection.SeqOps.containsSlice$(this, that);
   }

   public boolean contains(final Object elem) {
      return scala.collection.SeqOps.contains$(this, elem);
   }

   /** @deprecated */
   public Object reverseMap(final Function1 f) {
      return scala.collection.SeqOps.reverseMap$(this, f);
   }

   public Iterator permutations() {
      return scala.collection.SeqOps.permutations$(this);
   }

   public Iterator combinations(final int n) {
      return scala.collection.SeqOps.combinations$(this, n);
   }

   public Object sorted(final Ordering ord) {
      return scala.collection.SeqOps.sorted$(this, ord);
   }

   public Object sortWith(final Function2 lt) {
      return scala.collection.SeqOps.sortWith$(this, lt);
   }

   public Object sortBy(final Function1 f, final Ordering ord) {
      return scala.collection.SeqOps.sortBy$(this, f, ord);
   }

   public Range indices() {
      return scala.collection.SeqOps.indices$(this);
   }

   public final int sizeCompare(final int otherSize) {
      return scala.collection.SeqOps.sizeCompare$(this, otherSize);
   }

   public int lengthCompare(final int len) {
      return scala.collection.SeqOps.lengthCompare$(this, len);
   }

   public final int sizeCompare(final Iterable that) {
      return scala.collection.SeqOps.sizeCompare$(this, that);
   }

   public int lengthCompare(final Iterable that) {
      return scala.collection.SeqOps.lengthCompare$(this, that);
   }

   public final IterableOps lengthIs() {
      return scala.collection.SeqOps.lengthIs$(this);
   }

   public boolean isEmpty() {
      return scala.collection.SeqOps.isEmpty$(this);
   }

   public boolean sameElements(final IterableOnce that) {
      return scala.collection.SeqOps.sameElements$(this, that);
   }

   public boolean corresponds(final scala.collection.Seq that, final Function2 p) {
      return scala.collection.SeqOps.corresponds$(this, that, p);
   }

   public Object diff(final scala.collection.Seq that) {
      return scala.collection.SeqOps.diff$(this, that);
   }

   public Object intersect(final scala.collection.Seq that) {
      return scala.collection.SeqOps.intersect$(this, that);
   }

   public Object patch(final int from, final IterableOnce other, final int replaced) {
      return scala.collection.SeqOps.patch$(this, from, other, replaced);
   }

   public Object updated(final int index, final Object elem) {
      return scala.collection.SeqOps.updated$(this, index, elem);
   }

   public Map occCounts(final scala.collection.Seq sq) {
      return scala.collection.SeqOps.occCounts$(this, sq);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.SeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.SeqOps.search$(this, elem, from, to, ord);
   }

   public Option unapply(final Object a) {
      return PartialFunction.unapply$(this, a);
   }

   public PartialFunction elementWise() {
      return PartialFunction.elementWise$(this);
   }

   public PartialFunction orElse(final PartialFunction that) {
      return PartialFunction.orElse$(this, that);
   }

   public PartialFunction andThen(final Function1 k) {
      return PartialFunction.andThen$(this, (Function1)k);
   }

   public PartialFunction andThen(final PartialFunction k) {
      return PartialFunction.andThen$(this, (PartialFunction)k);
   }

   public PartialFunction compose(final PartialFunction k) {
      return PartialFunction.compose$(this, k);
   }

   public Function1 lift() {
      return PartialFunction.lift$(this);
   }

   public Object applyOrElse(final Object x, final Function1 default) {
      return PartialFunction.applyOrElse$(this, x, default);
   }

   public Function1 runWith(final Function1 action) {
      return PartialFunction.runWith$(this, action);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   /** @deprecated */
   public final Iterable toIterable() {
      return Iterable.toIterable$(this);
   }

   public final Iterable coll() {
      return Iterable.coll$(this);
   }

   /** @deprecated */
   public Iterable seq() {
      return Iterable.seq$(this);
   }

   public String className() {
      return Iterable.className$(this);
   }

   public final String collectionClassName() {
      return Iterable.collectionClassName$(this);
   }

   public LazyZip2 lazyZip(final Iterable that) {
      return Iterable.lazyZip$(this, that);
   }

   public IterableOps fromSpecific(final IterableOnce coll) {
      return IterableFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return IterableFactoryDefaults.newSpecificBuilder$(this);
   }

   public IterableOps empty() {
      return IterableFactoryDefaults.empty$(this);
   }

   /** @deprecated */
   public final Iterable toTraversable() {
      return IterableOps.toTraversable$(this);
   }

   public boolean isTraversableAgain() {
      return IterableOps.isTraversableAgain$(this);
   }

   /** @deprecated */
   public final Object repr() {
      return IterableOps.repr$(this);
   }

   /** @deprecated */
   public IterableFactory companion() {
      return IterableOps.companion$(this);
   }

   public Object head() {
      return IterableOps.head$(this);
   }

   public Option headOption() {
      return IterableOps.headOption$(this);
   }

   public Object last() {
      return IterableOps.last$(this);
   }

   public Option lastOption() {
      return IterableOps.lastOption$(this);
   }

   public final IterableOps sizeIs() {
      return IterableOps.sizeIs$(this);
   }

   /** @deprecated */
   public View view(final int from, final int until) {
      return IterableOps.view$(this, from, until);
   }

   public Object transpose(final Function1 asIterable) {
      return IterableOps.transpose$(this, asIterable);
   }

   public Object filter(final Function1 pred) {
      return IterableOps.filter$(this, pred);
   }

   public Object filterNot(final Function1 pred) {
      return IterableOps.filterNot$(this, pred);
   }

   public scala.collection.WithFilter withFilter(final Function1 p) {
      return IterableOps.withFilter$(this, p);
   }

   public Tuple2 partition(final Function1 p) {
      return IterableOps.partition$(this, p);
   }

   public Tuple2 splitAt(final int n) {
      return IterableOps.splitAt$(this, n);
   }

   public Object take(final int n) {
      return IterableOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return IterableOps.takeRight$(this, n);
   }

   public Object takeWhile(final Function1 p) {
      return IterableOps.takeWhile$(this, p);
   }

   public Tuple2 span(final Function1 p) {
      return IterableOps.span$(this, p);
   }

   public Object drop(final int n) {
      return IterableOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return IterableOps.dropRight$(this, n);
   }

   public Object dropWhile(final Function1 p) {
      return IterableOps.dropWhile$(this, p);
   }

   public Iterator grouped(final int size) {
      return IterableOps.grouped$(this, size);
   }

   public Iterator sliding(final int size) {
      return IterableOps.sliding$(this, size);
   }

   public Iterator sliding(final int size, final int step) {
      return IterableOps.sliding$(this, size, step);
   }

   public Object tail() {
      return IterableOps.tail$(this);
   }

   public Object init() {
      return IterableOps.init$(this);
   }

   public Object slice(final int from, final int until) {
      return IterableOps.slice$(this, from, until);
   }

   public scala.collection.immutable.Map groupBy(final Function1 f) {
      return IterableOps.groupBy$(this, f);
   }

   public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
      return IterableOps.groupMap$(this, key, f);
   }

   public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
      return IterableOps.groupMapReduce$(this, key, f, reduce);
   }

   public Object scan(final Object z, final Function2 op) {
      return IterableOps.scan$(this, z, op);
   }

   public Object scanLeft(final Object z, final Function2 op) {
      return IterableOps.scanLeft$(this, z, op);
   }

   public Object scanRight(final Object z, final Function2 op) {
      return IterableOps.scanRight$(this, z, op);
   }

   public Object map(final Function1 f) {
      return IterableOps.map$(this, f);
   }

   public Object flatMap(final Function1 f) {
      return IterableOps.flatMap$(this, f);
   }

   public Object flatten(final Function1 asIterable) {
      return IterableOps.flatten$(this, asIterable);
   }

   public Object collect(final PartialFunction pf) {
      return IterableOps.collect$(this, pf);
   }

   public Tuple2 partitionMap(final Function1 f) {
      return IterableOps.partitionMap$(this, f);
   }

   public final Object $plus$plus(final IterableOnce suffix) {
      return IterableOps.$plus$plus$(this, suffix);
   }

   public Object zip(final IterableOnce that) {
      return IterableOps.zip$(this, that);
   }

   public Object zipWithIndex() {
      return IterableOps.zipWithIndex$(this);
   }

   public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
      return IterableOps.zipAll$(this, that, thisElem, thatElem);
   }

   public Tuple2 unzip(final Function1 asPair) {
      return IterableOps.unzip$(this, asPair);
   }

   public Tuple3 unzip3(final Function1 asTriple) {
      return IterableOps.unzip3$(this, asTriple);
   }

   public Iterator tails() {
      return IterableOps.tails$(this);
   }

   public Iterator inits() {
      return IterableOps.inits$(this);
   }

   public Object tapEach(final Function1 f) {
      return IterableOps.tapEach$(this, f);
   }

   /** @deprecated */
   public boolean hasDefiniteSize() {
      return IterableOnceOps.hasDefiniteSize$(this);
   }

   public void foreach(final Function1 f) {
      IterableOnceOps.foreach$(this, f);
   }

   public boolean forall(final Function1 p) {
      return IterableOnceOps.forall$(this, p);
   }

   public boolean exists(final Function1 p) {
      return IterableOnceOps.exists$(this, p);
   }

   public int count(final Function1 p) {
      return IterableOnceOps.count$(this, p);
   }

   public Option find(final Function1 p) {
      return IterableOnceOps.find$(this, p);
   }

   public Object foldLeft(final Object z, final Function2 op) {
      return IterableOnceOps.foldLeft$(this, z, op);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return IterableOnceOps.foldRight$(this, z, op);
   }

   /** @deprecated */
   public final Object $div$colon(final Object z, final Function2 op) {
      return IterableOnceOps.$div$colon$(this, z, op);
   }

   /** @deprecated */
   public final Object $colon$bslash(final Object z, final Function2 op) {
      return IterableOnceOps.$colon$bslash$(this, z, op);
   }

   public Object fold(final Object z, final Function2 op) {
      return IterableOnceOps.fold$(this, z, op);
   }

   public Object reduce(final Function2 op) {
      return IterableOnceOps.reduce$(this, op);
   }

   public Option reduceOption(final Function2 op) {
      return IterableOnceOps.reduceOption$(this, op);
   }

   public Object reduceLeft(final Function2 op) {
      return IterableOnceOps.reduceLeft$(this, op);
   }

   public Object reduceRight(final Function2 op) {
      return IterableOnceOps.reduceRight$(this, op);
   }

   public Option reduceLeftOption(final Function2 op) {
      return IterableOnceOps.reduceLeftOption$(this, op);
   }

   public Option reduceRightOption(final Function2 op) {
      return IterableOnceOps.reduceRightOption$(this, op);
   }

   public boolean nonEmpty() {
      return IterableOnceOps.nonEmpty$(this);
   }

   /** @deprecated */
   public final void copyToBuffer(final Buffer dest) {
      IterableOnceOps.copyToBuffer$(this, dest);
   }

   public int copyToArray(final Object xs) {
      return IterableOnceOps.copyToArray$(this, xs);
   }

   public int copyToArray(final Object xs, final int start) {
      return IterableOnceOps.copyToArray$(this, xs, start);
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      return IterableOnceOps.copyToArray$(this, xs, start, len);
   }

   public Object sum(final Numeric num) {
      return IterableOnceOps.sum$(this, num);
   }

   public Object product(final Numeric num) {
      return IterableOnceOps.product$(this, num);
   }

   public Object min(final Ordering ord) {
      return IterableOnceOps.min$(this, ord);
   }

   public Option minOption(final Ordering ord) {
      return IterableOnceOps.minOption$(this, ord);
   }

   public Object max(final Ordering ord) {
      return IterableOnceOps.max$(this, ord);
   }

   public Option maxOption(final Ordering ord) {
      return IterableOnceOps.maxOption$(this, ord);
   }

   public Object maxBy(final Function1 f, final Ordering ord) {
      return IterableOnceOps.maxBy$(this, f, ord);
   }

   public Option maxByOption(final Function1 f, final Ordering ord) {
      return IterableOnceOps.maxByOption$(this, f, ord);
   }

   public Object minBy(final Function1 f, final Ordering ord) {
      return IterableOnceOps.minBy$(this, f, ord);
   }

   public Option minByOption(final Function1 f, final Ordering ord) {
      return IterableOnceOps.minByOption$(this, f, ord);
   }

   public Option collectFirst(final PartialFunction pf) {
      return IterableOnceOps.collectFirst$(this, pf);
   }

   /** @deprecated */
   public Object aggregate(final Function0 z, final Function2 seqop, final Function2 combop) {
      return IterableOnceOps.aggregate$(this, z, seqop, combop);
   }

   public boolean corresponds(final IterableOnce that, final Function2 p) {
      return IterableOnceOps.corresponds$(this, that, p);
   }

   public final String mkString(final String start, final String sep, final String end) {
      return IterableOnceOps.mkString$(this, start, sep, end);
   }

   public final String mkString(final String sep) {
      return IterableOnceOps.mkString$(this, sep);
   }

   public final String mkString() {
      return IterableOnceOps.mkString$(this);
   }

   public StringBuilder addString(final StringBuilder b, final String start, final String sep, final String end) {
      return IterableOnceOps.addString$(this, b, start, sep, end);
   }

   public final StringBuilder addString(final StringBuilder b, final String sep) {
      return IterableOnceOps.addString$(this, b, sep);
   }

   public final StringBuilder addString(final StringBuilder b) {
      return IterableOnceOps.addString$(this, b);
   }

   public Object to(final Factory factory) {
      return IterableOnceOps.to$(this, factory);
   }

   /** @deprecated */
   public final Iterator toIterator() {
      return IterableOnceOps.toIterator$(this);
   }

   public List toList() {
      return IterableOnceOps.toList$(this);
   }

   public Vector toVector() {
      return IterableOnceOps.toVector$(this);
   }

   public scala.collection.immutable.Map toMap(final $less$colon$less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public Set toSet() {
      return IterableOnceOps.toSet$(this);
   }

   public scala.collection.immutable.Seq toSeq() {
      return IterableOnceOps.toSeq$(this);
   }

   public IndexedSeq toIndexedSeq() {
      return IterableOnceOps.toIndexedSeq$(this);
   }

   /** @deprecated */
   public final Stream toStream() {
      return IterableOnceOps.toStream$(this);
   }

   public final Buffer toBuffer() {
      return IterableOnceOps.toBuffer$(this);
   }

   public Object toArray(final ClassTag evidence$2) {
      return IterableOnceOps.toArray$(this, evidence$2);
   }

   public Iterable reversed() {
      return IterableOnceOps.reversed$(this);
   }

   public int index() {
      return this.index;
   }

   public void index_$eq(final int x$1) {
      this.index = x$1;
   }

   public int hIndex() {
      return this.hIndex;
   }

   public void hIndex_$eq(final int x$1) {
      this.hIndex = x$1;
   }

   public long totalSize() {
      return this.totalSize;
   }

   public void totalSize_$eq(final long x$1) {
      this.totalSize = x$1;
   }

   public abstract long cumulative(final int i);

   public int nextBlockSize() {
      if (this.totalSize() < 32L) {
         return 16;
      } else if (this.totalSize() <= 2147483647L) {
         int bit = 64 - Long.numberOfLeadingZeros(this.totalSize());
         return 1 << bit - (bit >> 2);
      } else {
         return 16777216;
      }
   }

   public abstract Stepper efficientStepper(final StepperShape shape);

   public final Stepper stepper(final StepperShape shape) {
      return this.efficientStepper(shape);
   }

   public final int length() {
      if (this.totalSize() < 2147483647L) {
         return (int)this.totalSize();
      } else {
         throw new IllegalArgumentException((new java.lang.StringBuilder(27)).append("Size too large for an Int: ").append(this.totalSize()).toString());
      }
   }

   public final int knownSize() {
      return this.totalSize() < 2147483647L ? this.length() : -1;
   }

   public final long sizeLong() {
      return this.totalSize();
   }

   public void clear() {
      this.index_$eq(0);
      this.hIndex_$eq(0);
      this.totalSize_$eq(0L);
   }

   public long seekSlot(final long ix) {
      int lo = -1;
      int hi = this.hIndex();

      while(lo + 1 < hi) {
         int m = lo + hi >>> 1;
         if (this.cumulative(m) > ix) {
            hi = m;
         } else {
            lo = m;
         }
      }

      return (long)hi << 32 | (long)((int)(hi == 0 ? ix : ix - this.cumulative(hi - 1)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class AccumulatorFactoryShape$ implements LowPriorityAccumulatorFactoryShape {
      public static final AccumulatorFactoryShape$ MODULE$ = new AccumulatorFactoryShape$();
      private static final AccumulatorFactoryShape doubleAccumulatorFactoryShape;
      private static final AccumulatorFactoryShape intAccumulatorFactoryShape;
      private static final AccumulatorFactoryShape longAccumulatorFactoryShape;
      private static final AccumulatorFactoryShape jDoubleAccumulatorFactoryShape;
      private static final AccumulatorFactoryShape jIntegerAccumulatorFactoryShape;
      private static final AccumulatorFactoryShape jLongAccumulatorFactoryShape;
      private static AccumulatorFactoryShape scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype;

      static {
         Accumulator.LowPriorityAccumulatorFactoryShape.$init$(MODULE$);
         doubleAccumulatorFactoryShape = new AccumulatorFactoryShape() {
            public Factory factory() {
               return DoubleAccumulator$.MODULE$;
            }

            public DoubleAccumulator empty() {
               DoubleAccumulator$ var10000 = DoubleAccumulator$.MODULE$;
               return new DoubleAccumulator();
            }
         };
         intAccumulatorFactoryShape = new AccumulatorFactoryShape() {
            public Factory factory() {
               return IntAccumulator$.MODULE$;
            }

            public IntAccumulator empty() {
               IntAccumulator$ var10000 = IntAccumulator$.MODULE$;
               return new IntAccumulator();
            }
         };
         longAccumulatorFactoryShape = new AccumulatorFactoryShape() {
            public Factory factory() {
               return LongAccumulator$.MODULE$;
            }

            public LongAccumulator empty() {
               LongAccumulator$ var10000 = LongAccumulator$.MODULE$;
               return new LongAccumulator();
            }
         };
         jDoubleAccumulatorFactoryShape = MODULE$.doubleAccumulatorFactoryShape();
         jIntegerAccumulatorFactoryShape = MODULE$.intAccumulatorFactoryShape();
         jLongAccumulatorFactoryShape = MODULE$.longAccumulatorFactoryShape();
      }

      public AccumulatorFactoryShape anyAccumulatorFactoryShape() {
         return Accumulator.LowPriorityAccumulatorFactoryShape.super.anyAccumulatorFactoryShape();
      }

      public AccumulatorFactoryShape scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype() {
         return scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype;
      }

      public final void scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$_setter_$scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype_$eq(final AccumulatorFactoryShape x$1) {
         scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype = x$1;
      }

      public AccumulatorFactoryShape doubleAccumulatorFactoryShape() {
         return doubleAccumulatorFactoryShape;
      }

      public AccumulatorFactoryShape intAccumulatorFactoryShape() {
         return intAccumulatorFactoryShape;
      }

      public AccumulatorFactoryShape longAccumulatorFactoryShape() {
         return longAccumulatorFactoryShape;
      }

      public AccumulatorFactoryShape jDoubleAccumulatorFactoryShape() {
         return jDoubleAccumulatorFactoryShape;
      }

      public AccumulatorFactoryShape jIntegerAccumulatorFactoryShape() {
         return jIntegerAccumulatorFactoryShape;
      }

      public AccumulatorFactoryShape jLongAccumulatorFactoryShape() {
         return jLongAccumulatorFactoryShape;
      }
   }

   public interface LowPriorityAccumulatorFactoryShape {
      void scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$_setter_$scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype_$eq(final AccumulatorFactoryShape x$1);

      default AccumulatorFactoryShape anyAccumulatorFactoryShape() {
         return this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();
      }

      AccumulatorFactoryShape scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype();

      static void $init$(final LowPriorityAccumulatorFactoryShape $this) {
         $this.scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$_setter_$scala$jdk$Accumulator$LowPriorityAccumulatorFactoryShape$$anyAccumulatorFactoryShapePrototype_$eq(new AccumulatorFactoryShape() {
            public Factory factory() {
               IterableFactory$ var10000 = IterableFactory$.MODULE$;
               IterableFactory toFactory_factory = AnyAccumulator$.MODULE$;
               return new IterableFactory.ToFactory(toFactory_factory);
            }

            public AnyAccumulator empty() {
               AnyAccumulator$ var10000 = AnyAccumulator$.MODULE$;
               return new AnyAccumulator();
            }
         });
      }
   }

   public interface AccumulatorFactoryShape {
      Factory factory();

      Object empty();
   }
}
