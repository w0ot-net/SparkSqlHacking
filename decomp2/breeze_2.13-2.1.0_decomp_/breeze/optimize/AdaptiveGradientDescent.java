package breeze.optimize;

import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.norm$;
import breeze.linalg.operators.HasOps$;
import breeze.math.MutableFiniteCoordinateField;
import breeze.numerics.package$signum$signumDoubleImpl$;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import breeze.stats.distributions.RandBasis;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Some;
import scala.Tuple2;
import scala.Predef.ArrowAssoc.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\rmt!B.]\u0011\u0003\tg!B2]\u0011\u0003!\u0007\"B6\u0002\t\u0003ag\u0001B7\u0002\u00019D\u0001B`\u0002\u0003\u0006\u0004%\ta \u0005\u000b\u0003\u000f\u0019!\u0011!Q\u0001\n\u0005\u0005\u0001\u0002DA\u0005\u0007\t\u0005\t\u0015!\u0003\u0002\u0002\u0005-\u0001\u0002DA\b\u0007\t\u0005\t\u0015!\u0003\u0002\u0012\u0005]\u0001BCA\r\u0007\t\u0005\t\u0015!\u0003\u0002\u0002!Q\u00111D\u0002\u0003\u0002\u0003\u0006I!!\u0005\t\u0019\u0005u1A!A!\u0002\u0017\ty\"a\r\t\u0015\u0005U2A!A!\u0002\u0017\t9\u0004\u0003\u0004l\u0007\u0011\u0005\u0011q\t\u0005\t\u0003K\u001a!\u0019!C\u0001\u007f\"A\u0011qM\u0002!\u0002\u0013\t\tA\u0002\u0004\u0002j\r\u0001\u00151\u000e\u0005\u000b\u0003\u0017{!Q3A\u0005\u0002\u00055\u0005\"CAH\u001f\tE\t\u0015!\u0003t\u0011\u0019Yw\u0002\"\u0001\u0002\u0012\"I\u0011\u0011T\b\u0002\u0002\u0013\u0005\u00111\u0014\u0005\n\u0003?{\u0011\u0013!C\u0001\u0003CC\u0011\"a.\u0010\u0003\u0003%\t%!/\t\u0013\u0005-w\"!A\u0005\u0002\u00055\u0007\"CAh\u001f\u0005\u0005I\u0011AAi\u0011%\t9nDA\u0001\n\u0003\nI\u000eC\u0005\u0002h>\t\t\u0011\"\u0001\u0002j\"I\u00111_\b\u0002\u0002\u0013\u0005\u0013Q\u001f\u0005\n\u0003s|\u0011\u0011!C!\u0003wD\u0011\"!@\u0010\u0003\u0003%\t%a@\t\u0013\t\u0005q\"!A\u0005B\t\rq!\u0003B\u0004\u0007\u0005\u0005\t\u0012\u0001B\u0005\r%\tIgAA\u0001\u0012\u0003\u0011Y\u0001\u0003\u0004l?\u0011\u0005!1\u0005\u0005\n\u0003{|\u0012\u0011!C#\u0003\u007fD\u0011B!\n \u0003\u0003%\tIa\n\t\u0013\t-r$!A\u0005\u0002\n5\u0002b\u0002B\u001d\u0007\u0011\u0005#1\b\u0005\b\u0005\u0017\u001aA\u0011\tB'\u0011\u001d\u0011Yg\u0001C)\u0005[BqA!\u001f\u0004\t\u0003\u0012Y\bC\u0004\u0003\u0004\u000e!\tF!\"\b\u0013\tU\u0015!!A\t\u0002\t]e\u0001C7\u0002\u0003\u0003E\tA!'\t\r-TC\u0011\u0001BN\u0011%\u0011iJKI\u0001\n\u0003\u0011y\nC\u0005\u0003(*\n\n\u0011\"\u0001\u0003*\"I!Q\u0016\u0016\u0012\u0002\u0013\u0005!q\u0016\u0005\n\u0005oS\u0013\u0011!C\u0005\u0005s3aA!1\u0002\u0001\t\r\u0007\"\u0003Bga\t\u0015\r\u0011\"\u0001\u0000\u0011)\u0011y\r\rB\u0001B\u0003%\u0011\u0011\u0001\u0005\u000b\u0003K\u0002$\u0011!Q\u0001\n\u0005\u0005\u0001\u0002\u0004Bia\t\u0005\t\u0015!\u0003\u0002\u0002\u0005-\u0001\u0002DA\ba\t\u0005\t\u0015!\u0003\u0002\u0012\u0005]\u0001\u0002\u0004Bja\t\u0005\t\u0015a\u0003\u0003V\u0006M\u0002BCA\u001ba\t\u0005\t\u0015a\u0003\u00028!11\u000e\rC\u0001\u0005?4a!!\u001b1\u0001\ne\bBCAFs\tU\r\u0011\"\u0001\u0003|\"Q\u0011qR\u001d\u0003\u0012\u0003\u0006IA!3\t\r-LD\u0011\u0001B\u007f\u0011%\tI*OA\u0001\n\u0003\u0019)\u0001C\u0005\u0002 f\n\n\u0011\"\u0001\u0004\n!I\u0011qW\u001d\u0002\u0002\u0013\u0005\u0013\u0011\u0018\u0005\n\u0003\u0017L\u0014\u0011!C\u0001\u0003\u001bD\u0011\"a4:\u0003\u0003%\ta!\u0004\t\u0013\u0005]\u0017(!A\u0005B\u0005e\u0007\"CAts\u0005\u0005I\u0011AB\t\u0011%\t\u00190OA\u0001\n\u0003\u001a)\u0002C\u0005\u0002zf\n\t\u0011\"\u0011\u0002|\"I\u0011Q`\u001d\u0002\u0002\u0013\u0005\u0013q \u0005\n\u0005\u0003I\u0014\u0011!C!\u000739\u0011Ba\u00021\u0003\u0003E\ta!\b\u0007\u0013\u0005%\u0004'!A\t\u0002\r}\u0001BB6J\t\u0003\u0019\u0019\u0003C\u0005\u0002~&\u000b\t\u0011\"\u0012\u0002\u0000\"I!QE%\u0002\u0002\u0013\u00055Q\u0005\u0005\n\u0005WI\u0015\u0011!CA\u0007SAqA!\u000f1\t\u0003\u0019y\u0003C\u0004\u0003LA\"\tea\u000e\t\u000f\t-\u0004\u0007\"\u0015\u0004F!9!\u0011\u0010\u0019\u0005B\r5\u0003b\u0002BBa\u0011E3QK\u0004\n\u0007?\n\u0011\u0011!E\u0001\u0007C2\u0011B!1\u0002\u0003\u0003E\taa\u0019\t\r-$F\u0011AB3\u0011%\u0011i\nVI\u0001\n\u0003\u00199\u0007C\u0005\u0004lQ\u000b\n\u0011\"\u0001\u0004n!I1\u0011\u000f+\u0012\u0002\u0013\u000511\u000f\u0005\n\u0005O#\u0016\u0013!C\u0001\u0007oB\u0011Ba.U\u0003\u0003%IA!/\u0002/\u0005#\u0017\r\u001d;jm\u0016<%/\u00193jK:$H)Z:dK:$(BA/_\u0003!y\u0007\u000f^5nSj,'\"A0\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"AY\u0001\u000e\u0003q\u0013q#\u00113baRLg/Z$sC\u0012LWM\u001c;EKN\u001cWM\u001c;\u0014\u0005\u0005)\u0007C\u00014j\u001b\u00059'\"\u00015\u0002\u000bM\u001c\u0017\r\\1\n\u0005)<'AB!osJ+g-\u0001\u0004=S:LGO\u0010\u000b\u0002C\n\u0001BJ\r*fOVd\u0017M]5{CRLwN\\\u000b\u0003_V\u001c\"a\u00019\u0011\u0007\t\f8/\u0003\u0002s9\nI2\u000b^8dQ\u0006\u001cH/[2He\u0006$\u0017.\u001a8u\t\u0016\u001c8-\u001a8u!\t!X\u000f\u0004\u0001\u0005\u000bY\u001c!\u0019A<\u0003\u0003Q\u000b\"\u0001_>\u0011\u0005\u0019L\u0018B\u0001>h\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u001a?\n\u0005u<'aA!os\u00061\"/Z4vY\u0006\u0014\u0018N_1uS>t7i\u001c8ti\u0006tG/\u0006\u0002\u0002\u0002A\u0019a-a\u0001\n\u0007\u0005\u0015qM\u0001\u0004E_V\u0014G.Z\u0001\u0018e\u0016<W\u000f\\1sSj\fG/[8o\u0007>t7\u000f^1oi\u0002\n\u0001b\u001d;faNK'0Z\u0005\u0004\u0003\u001b\t\u0018a\u00043fM\u0006,H\u000e^*uKB\u001c\u0016N_3\u0002\u000f5\f\u00070\u0013;feB\u0019a-a\u0005\n\u0007\u0005UqMA\u0002J]RL1!a\u0004r\u0003%!x\u000e\\3sC:\u001cW-\u0001\u000bnS:LU\u000e\u001d:pm\u0016lWM\u001c;XS:$wn^\u0001\u0007mN\u0004\u0018mY31\t\u0005\u0005\u0012q\u0006\t\n\u0003G\tIc]A\u0017\u0003\u0003i!!!\n\u000b\u0007\u0005\u001db,\u0001\u0003nCRD\u0017\u0002BA\u0016\u0003K\u0011A$T;uC\ndWMR5oSR,7i\\8sI&t\u0017\r^3GS\u0016dG\rE\u0002u\u0003_!!\"!\r\u000b\u0003\u0003\u0005\tQ!\u0001x\u0005\ryF%M\u0005\u0004\u0003;\t\u0018\u0001\u0002:b]\u0012\u0004B!!\u000f\u0002D5\u0011\u00111\b\u0006\u0005\u0003{\ty$A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0004\u0003\u0003r\u0016!B:uCR\u001c\u0018\u0002BA#\u0003w\u0011\u0011BU1oI\n\u000b7/[:\u0015\u0019\u0005%\u00131LA/\u0003?\n\t'a\u0019\u0015\r\u0005-\u0013qJA-!\u0011\tieA:\u000e\u0003\u0005Aq!!\b\r\u0001\b\t\t\u0006\r\u0003\u0002T\u0005]\u0003#CA\u0012\u0003S\u0019\u0018QKA\u0001!\r!\u0018q\u000b\u0003\f\u0003c\ty%!A\u0001\u0002\u000b\u0005q\u000fC\u0004\u000261\u0001\u001d!a\u000e\t\u0011yd\u0001\u0013!a\u0001\u0003\u0003Aq!!\u0003\r\u0001\u0004\t\t\u0001C\u0004\u0002\u00101\u0001\r!!\u0005\t\u0013\u0005eA\u0002%AA\u0002\u0005\u0005\u0001\"CA\u000e\u0019A\u0005\t\u0019AA\t\u0003\u0015!W\r\u001c;b\u0003\u0019!W\r\u001c;bA\t9\u0001*[:u_JL8CB\bf\u0003[\n\u0019\bE\u0002g\u0003_J1!!\u001dh\u0005\u001d\u0001&o\u001c3vGR\u0004B!!\u001e\u0002\u0006:!\u0011qOAA\u001d\u0011\tI(a \u000e\u0005\u0005m$bAA?A\u00061AH]8pizJ\u0011\u0001[\u0005\u0004\u0003\u0007;\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003\u000f\u000bII\u0001\u0007TKJL\u0017\r\\5{C\ndWMC\u0002\u0002\u0004\u001e\fQc];n\u001f\u001a\u001c\u0016/^1sK\u0012<%/\u00193jK:$8/F\u0001t\u0003Y\u0019X/\\(g'F,\u0018M]3e\u000fJ\fG-[3oiN\u0004C\u0003BAJ\u0003/\u00032!!&\u0010\u001b\u0005\u0019\u0001BBAF%\u0001\u00071/\u0001\u0003d_BLH\u0003BAJ\u0003;C\u0001\"a#\u0014!\u0003\u0005\ra]\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\t\t\u0019KK\u0002t\u0003K[#!a*\u0011\t\u0005%\u00161W\u0007\u0003\u0003WSA!!,\u00020\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003c;\u0017AC1o]>$\u0018\r^5p]&!\u0011QWAV\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u0005m\u0006\u0003BA_\u0003\u000fl!!a0\u000b\t\u0005\u0005\u00171Y\u0001\u0005Y\u0006twM\u0003\u0002\u0002F\u0006!!.\u0019<b\u0013\u0011\tI-a0\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z+\t\t\t\"\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\u0007m\f\u0019\u000eC\u0005\u0002V^\t\t\u00111\u0001\u0002\u0012\u0005\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"!a7\u0011\u000b\u0005u\u00171]>\u000e\u0005\u0005}'bAAqO\u0006Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u0018q\u001c\u0002\t\u0013R,'/\u0019;pe\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0002l\u0006E\bc\u00014\u0002n&\u0019\u0011q^4\u0003\u000f\t{w\u000e\\3b]\"A\u0011Q[\r\u0002\u0002\u0003\u000710\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BA^\u0003oD\u0011\"!6\u001b\u0003\u0003\u0005\r!!\u0005\u0002\u0011!\f7\u000f[\"pI\u0016$\"!!\u0005\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!a/\u0002\r\u0015\fX/\u00197t)\u0011\tYO!\u0002\t\u0011\u0005UW$!AA\u0002m\fq\u0001S5ti>\u0014\u0018\u0010E\u0002\u0002\u0016~\u0019Ra\bB\u0007\u00053\u0001rAa\u0004\u0003\u0016M\f\u0019*\u0004\u0002\u0003\u0012)\u0019!1C4\u0002\u000fI,h\u000e^5nK&!!q\u0003B\t\u0005E\t%m\u001d;sC\u000e$h)\u001e8di&|g.\r\t\u0005\u00057\u0011\t#\u0004\u0002\u0003\u001e)!!qDAb\u0003\tIw.\u0003\u0003\u0002\b\nuAC\u0001B\u0005\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t\u0019J!\u000b\t\r\u0005-%\u00051\u0001t\u0003\u001d)h.\u00199qYf$BAa\f\u00036A!aM!\rt\u0013\r\u0011\u0019d\u001a\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\t]2%!AA\u0002\u0005M\u0015a\u0001=%a\u0005q\u0011N\\5uS\u0006d\u0007*[:u_JLHCBAJ\u0005{\u00119\u0005C\u0004\u0003@\u0011\u0002\rA!\u0011\u0002\u0003\u0019\u0004BA\u0019B\"g&\u0019!Q\t/\u0003-M#xn\u00195bgRL7\rR5gM\u001a+hn\u0019;j_:DaA!\u0013%\u0001\u0004\u0019\u0018\u0001B5oSR\fQ\"\u001e9eCR,\u0007*[:u_JLH\u0003DAJ\u0005\u001f\u0012\u0019Fa\u0016\u0003\\\tu\u0003B\u0002B)K\u0001\u00071/\u0001\u0003oK^D\u0006B\u0002B+K\u0001\u00071/A\u0004oK^<%/\u00193\t\u000f\teS\u00051\u0001\u0002\u0002\u0005Aa.Z<WC2,X\rC\u0004\u0003@\u0015\u0002\rA!\u0011\t\u000f\t}S\u00051\u0001\u0003b\u0005Aq\u000e\u001c3Ti\u0006$X\r\u0005\u0003\u0002\u0016\n\r\u0014\u0002\u0002B3\u0005O\u0012Qa\u0015;bi\u0016L1A!\u001b]\u0005M1\u0015N]:u\u001fJ$WM]'j]&l\u0017N_3s\u0003!!\u0018m[3Ti\u0016\u0004HcB:\u0003p\tM$q\u000f\u0005\b\u0005c2\u0003\u0019\u0001B1\u0003\u0015\u0019H/\u0019;f\u0011\u0019\u0011)H\na\u0001g\u0006\u0019A-\u001b:\t\u000f\u0005%a\u00051\u0001\u0002\u0002\u0005\tB-\u001a;fe6Lg.Z*uKB\u001c\u0016N_3\u0015\u0011\u0005\u0005!Q\u0010B@\u0005\u0003CqA!\u001d(\u0001\u0004\u0011\t\u0007C\u0004\u0003@\u001d\u0002\rA!\u0011\t\r\tUt\u00051\u0001t\u0003\u0019\tGM[;tiRA!q\u0011BG\u0005\u001f\u0013\t\n\u0005\u0004g\u0005\u0013\u000b\ta]\u0005\u0004\u0005\u0017;'A\u0002+va2,'\u0007\u0003\u0004\u0003R!\u0002\ra\u001d\u0005\u0007\u0005+B\u0003\u0019A:\t\u000f\tM\u0005\u00061\u0001\u0002\u0002\u00051a.Z<WC2\f\u0001\u0003\u0014\u001aSK\u001e,H.\u0019:ju\u0006$\u0018n\u001c8\u0011\u0007\u00055#f\u0005\u0003+K\neAC\u0001BL\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU!!\u0011\u0015BS+\t\u0011\u0019K\u000b\u0003\u0002\u0002\u0005\u0015F!\u0002<-\u0005\u00049\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$C'\u0006\u0003\u0003\"\n-F!\u0002<.\u0005\u00049\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$S'\u0006\u0003\u00032\nUVC\u0001BZU\u0011\t\t\"!*\u0005\u000bYt#\u0019A<\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\tm\u0006\u0003BA_\u0005{KAAa0\u0002@\n1qJ\u00196fGR\u0014\u0001\u0003T\u0019SK\u001e,H.\u0019:ju\u0006$\u0018n\u001c8\u0016\t\t\u0015'1Z\n\u0004a\t\u001d\u0007\u0003\u00022r\u0005\u0013\u00042\u0001\u001eBf\t\u00151\bG1\u0001x\u0003\u0019a\u0017-\u001c2eC\u00069A.Y7cI\u0006\u0004\u0013aA3uC\u0006)1\u000f]1dKB\"!q\u001bBn!)\t\u0019#!\u000b\u0003J\ne\u0017\u0011\u0001\t\u0004i\nmGA\u0003Bom\u0005\u0005\t\u0011!B\u0001o\n\u0019q\f\n\u001a\u0015\u0015\t\u0005(\u0011\u001fBz\u0005k\u00149\u0010\u0006\u0004\u0003d\n\u0015(q\u001e\t\u0006\u0003\u001b\u0002$\u0011\u001a\u0005\b\u0005'D\u00049\u0001Bta\u0011\u0011IO!<\u0011\u0015\u0005\r\u0012\u0011\u0006Be\u0005W\f\t\u0001E\u0002u\u0005[$1B!8\u0003f\u0006\u0005\t\u0011!B\u0001o\"9\u0011Q\u0007\u001dA\u0004\u0005]\u0002\"\u0003BgqA\u0005\t\u0019AA\u0001\u0011%\t)\u0007\u000fI\u0001\u0002\u0004\t\t\u0001C\u0005\u0003Rb\u0002\n\u00111\u0001\u0002\u0002!I\u0011q\u0002\u001d\u0011\u0002\u0003\u0007\u0011\u0011C\n\u0007s\u0015\fi'a\u001d\u0016\u0005\t%G\u0003\u0002B\u0000\u0007\u0007\u00012a!\u0001:\u001b\u0005\u0001\u0004bBAFy\u0001\u0007!\u0011\u001a\u000b\u0005\u0005\u007f\u001c9\u0001C\u0005\u0002\fv\u0002\n\u00111\u0001\u0003JV\u001111\u0002\u0016\u0005\u0005\u0013\f)\u000bF\u0002|\u0007\u001fA\u0011\"!6B\u0003\u0003\u0005\r!!\u0005\u0015\t\u0005-81\u0003\u0005\t\u0003+\u001c\u0015\u0011!a\u0001wR!\u00111XB\f\u0011%\t)\u000eRA\u0001\u0002\u0004\t\t\u0002\u0006\u0003\u0002l\u000em\u0001\u0002CAk\u000f\u0006\u0005\t\u0019A>\u0011\u0007\r\u0005\u0011jE\u0003J\u0007C\u0011I\u0002\u0005\u0005\u0003\u0010\tU!\u0011\u001aB\u0000)\t\u0019i\u0002\u0006\u0003\u0003\u0000\u000e\u001d\u0002bBAF\u0019\u0002\u0007!\u0011\u001a\u000b\u0005\u0007W\u0019i\u0003E\u0003g\u0005c\u0011I\rC\u0005\u000385\u000b\t\u00111\u0001\u0003\u0000R1!q`B\u0019\u0007kAqAa\u0010O\u0001\u0004\u0019\u0019\u0004E\u0003c\u0005\u0007\u0012I\rC\u0004\u0003J9\u0003\rA!3\u0015\u0019\t}8\u0011HB\u001e\u0007{\u0019yd!\u0011\t\u000f\tEs\n1\u0001\u0003J\"9!QK(A\u0002\t%\u0007b\u0002B-\u001f\u0002\u0007\u0011\u0011\u0001\u0005\b\u0005\u007fy\u0005\u0019AB\u001a\u0011\u001d\u0011yf\u0014a\u0001\u0007\u0007\u0002Ba!\u0001\u0003dQA!\u0011ZB$\u0007\u0013\u001aY\u0005C\u0004\u0003rA\u0003\raa\u0011\t\u000f\tU\u0004\u000b1\u0001\u0003J\"9\u0011\u0011\u0002)A\u0002\u0005\u0005A\u0003CA\u0001\u0007\u001f\u001a\tfa\u0015\t\u000f\tE\u0014\u000b1\u0001\u0004D!9!qH)A\u0002\rM\u0002b\u0002B;#\u0002\u0007!\u0011\u001a\u000b\t\u0007/\u001aIfa\u0017\u0004^A9aM!#\u0002\u0002\t%\u0007b\u0002B)%\u0002\u0007!\u0011\u001a\u0005\b\u0005+\u0012\u0006\u0019\u0001Be\u0011\u001d\u0011\u0019J\u0015a\u0001\u0003\u0003\t\u0001\u0003T\u0019SK\u001e,H.\u0019:ju\u0006$\u0018n\u001c8\u0011\u0007\u00055Ck\u0005\u0003UK\neACAB1+\u0011\u0011\tk!\u001b\u0005\u000bY4&\u0019A<\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00133+\u0011\u0011\tka\u001c\u0005\u000bY<&\u0019A<\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00134+\u0011\u0011\tk!\u001e\u0005\u000bYD&\u0019A<\u0016\t\tE6\u0011\u0010\u0003\u0006mf\u0013\ra\u001e"
)
public final class AdaptiveGradientDescent {
   public static class L2Regularization$ implements Serializable {
      public static final L2Regularization$ MODULE$ = new L2Regularization$();

      public double $lessinit$greater$default$1() {
         return (double)1.0F;
      }

      public double $lessinit$greater$default$4() {
         return 1.0E-8;
      }

      public int $lessinit$greater$default$5() {
         return 50;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(L2Regularization$.class);
      }
   }

   public static class L2Regularization extends StochasticGradientDescent {
      private volatile History$ History$module;
      private final double regularizationConstant;
      private final double delta;

      public History$ History() {
         if (this.History$module == null) {
            this.History$lzycompute$1();
         }

         return this.History$module;
      }

      public double regularizationConstant() {
         return this.regularizationConstant;
      }

      public double delta() {
         return this.delta;
      }

      public History initialHistory(final StochasticDiffFunction f, final Object init) {
         return new History(((MutableFiniteCoordinateField)super.vspace()).zeroLike().apply(init));
      }

      public History updateHistory(final Object newX, final Object newGrad, final double newValue, final StochasticDiffFunction f, final FirstOrderMinimizer.State oldState) {
         History oldHistory = (History)oldState.history();
         Object newG = ((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(oldState.grad())).$times$colon$times(oldState.grad(), ((MutableFiniteCoordinateField)super.vspace()).mulVV());
         double maxAge = (double)1000.0F;
         if ((double)oldState.iter() > maxAge) {
            ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newG)).$times$eq(BoxesRunTime.boxToDouble((double)1 / maxAge), ((MutableFiniteCoordinateField)super.vspace()).mulIntoVS());
            breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble((maxAge - (double)1) / maxAge), oldHistory.sumOfSquaredGradients(), newG, ((MutableFiniteCoordinateField)super.vspace()).scaleAddVV());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newG)).$plus$eq(oldHistory.sumOfSquaredGradients(), ((MutableFiniteCoordinateField)super.vspace()).addIntoVV());
         }

         return new History(newG);
      }

      public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
         Object s = breeze.numerics.package.sqrt$.MODULE$.apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((History)state.history()).sumOfSquaredGradients())).$plus$colon$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(state.grad())).$times$colon$times(state.grad(), ((MutableFiniteCoordinateField)super.vspace()).mulVV()), ((MutableFiniteCoordinateField)super.vspace()).addVV()), HasOps$.MODULE$.fromLowOrderCanMapActiveValues(((MutableFiniteCoordinateField)super.vspace()).scalarOf(), package$sqrt$sqrtDoubleImpl$.MODULE$, ((MutableFiniteCoordinateField)super.vspace()).mapValues()));
         Object newx = ((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(state.x())).$times$colon$times(s, ((MutableFiniteCoordinateField)super.vspace()).mulVV());
         breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble(stepSize), dir, newx, ((MutableFiniteCoordinateField)super.vspace()).scaleAddVV());
         ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(s)).$plus$eq(BoxesRunTime.boxToDouble(this.delta() + this.regularizationConstant() * stepSize), ((MutableFiniteCoordinateField)super.vspace()).addIntoVS());
         ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newx)).$colon$div$eq(s, ((MutableFiniteCoordinateField)super.vspace()).divIntoVV());
         return newx;
      }

      public double determineStepSize(final FirstOrderMinimizer.State state, final StochasticDiffFunction f, final Object dir) {
         return this.defaultStepSize();
      }

      public Tuple2 adjust(final Object newX, final Object newGrad, final double newVal) {
         double av = newVal + BoxesRunTime.unboxToDouble(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newX)).dot(newX, ((MutableFiniteCoordinateField)super.vspace()).dotVV())) * this.regularizationConstant() / (double)2.0F;
         Object ag = ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newGrad)).$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newX)).$times(BoxesRunTime.boxToDouble(this.regularizationConstant()), ((MutableFiniteCoordinateField)super.vspace()).mulVS_M()), ((MutableFiniteCoordinateField)super.vspace()).addVV());
         return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(av)), ag);
      }

      private final void History$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.History$module == null) {
               this.History$module = new History$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public L2Regularization(final double regularizationConstant, final double stepSize, final int maxIter, final double tolerance, final int minImprovementWindow, final MutableFiniteCoordinateField vspace, final RandBasis rand) {
         super(stepSize, maxIter, tolerance, minImprovementWindow, vspace);
         this.regularizationConstant = regularizationConstant;
         this.delta = 1.0E-4;
      }

      public class History implements Product, Serializable {
         private final Object sumOfSquaredGradients;
         // $FF: synthetic field
         public final L2Regularization $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Object sumOfSquaredGradients() {
            return this.sumOfSquaredGradients;
         }

         public History copy(final Object sumOfSquaredGradients) {
            return this.breeze$optimize$AdaptiveGradientDescent$L2Regularization$History$$$outer().new History(sumOfSquaredGradients);
         }

         public Object copy$default$1() {
            return this.sumOfSquaredGradients();
         }

         public String productPrefix() {
            return "History";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            Object var10000;
            switch (x$1) {
               case 0:
                  var10000 = this.sumOfSquaredGradients();
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
            return x$1 instanceof History;
         }

         public String productElementName(final int x$1) {
            String var10000;
            switch (x$1) {
               case 0:
                  var10000 = "sumOfSquaredGradients";
                  break;
               default:
                  var10000 = (String)Statics.ioobe(x$1);
            }

            return var10000;
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label54: {
                  boolean var2;
                  if (x$1 instanceof History && ((History)x$1).breeze$optimize$AdaptiveGradientDescent$L2Regularization$History$$$outer() == this.breeze$optimize$AdaptiveGradientDescent$L2Regularization$History$$$outer()) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     History var4 = (History)x$1;
                     if (BoxesRunTime.equals(this.sumOfSquaredGradients(), var4.sumOfSquaredGradients()) && var4.canEqual(this)) {
                        break label54;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public L2Regularization breeze$optimize$AdaptiveGradientDescent$L2Regularization$History$$$outer() {
            return this.$outer;
         }

         public History(final Object sumOfSquaredGradients) {
            this.sumOfSquaredGradients = sumOfSquaredGradients;
            if (L2Regularization.this == null) {
               throw null;
            } else {
               this.$outer = L2Regularization.this;
               super();
               Product.$init$(this);
            }
         }
      }

      public class History$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final L2Regularization $outer;

         public final String toString() {
            return "History";
         }

         public History apply(final Object sumOfSquaredGradients) {
            return this.$outer.new History(sumOfSquaredGradients);
         }

         public Option unapply(final History x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.sumOfSquaredGradients()));
         }

         public History$() {
            if (L2Regularization.this == null) {
               throw null;
            } else {
               this.$outer = L2Regularization.this;
               super();
            }
         }
      }
   }

   public static class L1Regularization$ implements Serializable {
      public static final L1Regularization$ MODULE$ = new L1Regularization$();

      public double $lessinit$greater$default$1() {
         return (double)1.0F;
      }

      public double $lessinit$greater$default$2() {
         return 1.0E-5;
      }

      public double $lessinit$greater$default$3() {
         return (double)4.0F;
      }

      public int $lessinit$greater$default$4() {
         return 100;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(L1Regularization$.class);
      }
   }

   public static class L1Regularization extends StochasticGradientDescent {
      private volatile History$ History$module;
      private final double lambda;
      private final double delta;

      public History$ History() {
         if (this.History$module == null) {
            this.History$lzycompute$2();
         }

         return this.History$module;
      }

      public double lambda() {
         return this.lambda;
      }

      public History initialHistory(final StochasticDiffFunction f, final Object init) {
         return new History(((MutableFiniteCoordinateField)super.vspace()).zeroLike().apply(init));
      }

      public History updateHistory(final Object newX, final Object newGrad, final double newValue, final StochasticDiffFunction f, final FirstOrderMinimizer.State oldState) {
         History oldHistory = (History)oldState.history();
         Object newG = ((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(oldState.grad())).$times$colon$times(oldState.grad(), ((MutableFiniteCoordinateField)super.vspace()).mulVV());
         double maxAge = (double)200.0F;
         if ((double)oldState.iter() > maxAge) {
            ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newG)).$times$eq(BoxesRunTime.boxToDouble((double)1 / maxAge), ((MutableFiniteCoordinateField)super.vspace()).mulIntoVS());
            breeze.linalg.package$.MODULE$.axpy(BoxesRunTime.boxToDouble((maxAge - (double)1) / maxAge), oldHistory.sumOfSquaredGradients(), newG, ((MutableFiniteCoordinateField)super.vspace()).scaleAddVV());
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newG)).$plus$eq(oldHistory.sumOfSquaredGradients(), ((MutableFiniteCoordinateField)super.vspace()).addIntoVV());
         }

         return new History(newG);
      }

      public Object takeStep(final FirstOrderMinimizer.State state, final Object dir, final double stepSize) {
         Object s = breeze.numerics.package.sqrt$.MODULE$.apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((History)state.history()).sumOfSquaredGradients())).$plus$colon$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(state.grad())).$times$colon$times(state.grad(), ((MutableFiniteCoordinateField)super.vspace()).mulVV()), ((MutableFiniteCoordinateField)super.vspace()).addVV()))).$plus$colon$plus(BoxesRunTime.boxToDouble(this.delta), ((MutableFiniteCoordinateField)super.vspace()).addVS()), HasOps$.MODULE$.fromLowOrderCanMapActiveValues(((MutableFiniteCoordinateField)super.vspace()).scalarOf(), package$sqrt$sqrtDoubleImpl$.MODULE$, ((MutableFiniteCoordinateField)super.vspace()).mapValues()));
         Object res = ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(state.x())).$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(dir)).$times$colon$times(BoxesRunTime.boxToDouble(stepSize), ((MutableFiniteCoordinateField)super.vspace()).mulVS()))).$div$colon$div(s, ((MutableFiniteCoordinateField)super.vspace()).divVV()), ((MutableFiniteCoordinateField)super.vspace()).addVV());
         double tlambda = this.lambda() * stepSize;
         return ((MutableFiniteCoordinateField)super.vspace()).zipMapValues().map$mcDD$sp(res, s, (JFunction2.mcDDD.sp)(x0$1, x1$1) -> {
            Tuple2.mcDD.sp var8 = new Tuple2.mcDD.sp(x0$1, x1$1);
            if (var8 != null) {
               double x_half = ((Tuple2)var8)._1$mcD$sp();
               double s_i = ((Tuple2)var8)._2$mcD$sp();
               double var6 = scala.runtime.RichDouble..MODULE$.abs$extension(scala.Predef..MODULE$.doubleWrapper(x_half)) < tlambda / s_i ? (double)0.0F : x_half - scala.math.package..MODULE$.signum(x_half) * tlambda / s_i;
               return var6;
            } else {
               throw new MatchError(var8);
            }
         });
      }

      public double determineStepSize(final FirstOrderMinimizer.State state, final StochasticDiffFunction f, final Object dir) {
         return this.defaultStepSize();
      }

      public Tuple2 adjust(final Object newX, final Object newGrad, final double newVal) {
         double av = newVal + BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(newX, BoxesRunTime.boxToDouble((double)1.0F), ((MutableFiniteCoordinateField)super.vspace()).normImpl2())) * this.lambda();
         Object ag = ((NumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(newGrad)).$plus(((ImmutableNumericOps)((MutableFiniteCoordinateField)super.vspace()).hasOps().apply(breeze.numerics.package.signum$.MODULE$.apply(newX, HasOps$.MODULE$.fromLowOrderCanMapActiveValues(((MutableFiniteCoordinateField)super.vspace()).scalarOf(), package$signum$signumDoubleImpl$.MODULE$, ((MutableFiniteCoordinateField)super.vspace()).mapValues())))).$times$colon$times(BoxesRunTime.boxToDouble(this.lambda()), ((MutableFiniteCoordinateField)super.vspace()).mulVS()), ((MutableFiniteCoordinateField)super.vspace()).addVV());
         return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(av)), ag);
      }

      private final void History$lzycompute$2() {
         synchronized(this){}

         try {
            if (this.History$module == null) {
               this.History$module = new History$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public L1Regularization(final double lambda, final double delta, final double eta, final int maxIter, final MutableFiniteCoordinateField space, final RandBasis rand) {
         super(eta, maxIter, StochasticGradientDescent$.MODULE$.$lessinit$greater$default$3(), StochasticGradientDescent$.MODULE$.$lessinit$greater$default$4(), space);
         this.lambda = lambda;
         this.delta = delta;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }

      public class History implements Product, Serializable {
         private final Object sumOfSquaredGradients;
         // $FF: synthetic field
         public final L1Regularization $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Object sumOfSquaredGradients() {
            return this.sumOfSquaredGradients;
         }

         public History copy(final Object sumOfSquaredGradients) {
            return this.breeze$optimize$AdaptiveGradientDescent$L1Regularization$History$$$outer().new History(sumOfSquaredGradients);
         }

         public Object copy$default$1() {
            return this.sumOfSquaredGradients();
         }

         public String productPrefix() {
            return "History";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            Object var10000;
            switch (x$1) {
               case 0:
                  var10000 = this.sumOfSquaredGradients();
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
            return x$1 instanceof History;
         }

         public String productElementName(final int x$1) {
            String var10000;
            switch (x$1) {
               case 0:
                  var10000 = "sumOfSquaredGradients";
                  break;
               default:
                  var10000 = (String)Statics.ioobe(x$1);
            }

            return var10000;
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var10000;
            if (this != x$1) {
               label54: {
                  boolean var2;
                  if (x$1 instanceof History && ((History)x$1).breeze$optimize$AdaptiveGradientDescent$L1Regularization$History$$$outer() == this.breeze$optimize$AdaptiveGradientDescent$L1Regularization$History$$$outer()) {
                     var2 = true;
                  } else {
                     var2 = false;
                  }

                  if (var2) {
                     History var4 = (History)x$1;
                     if (BoxesRunTime.equals(this.sumOfSquaredGradients(), var4.sumOfSquaredGradients()) && var4.canEqual(this)) {
                        break label54;
                     }
                  }

                  var10000 = false;
                  return var10000;
               }
            }

            var10000 = true;
            return var10000;
         }

         // $FF: synthetic method
         public L1Regularization breeze$optimize$AdaptiveGradientDescent$L1Regularization$History$$$outer() {
            return this.$outer;
         }

         public History(final Object sumOfSquaredGradients) {
            this.sumOfSquaredGradients = sumOfSquaredGradients;
            if (L1Regularization.this == null) {
               throw null;
            } else {
               this.$outer = L1Regularization.this;
               super();
               Product.$init$(this);
            }
         }
      }

      public class History$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final L1Regularization $outer;

         public final String toString() {
            return "History";
         }

         public History apply(final Object sumOfSquaredGradients) {
            return this.$outer.new History(sumOfSquaredGradients);
         }

         public Option unapply(final History x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.sumOfSquaredGradients()));
         }

         public History$() {
            if (L1Regularization.this == null) {
               throw null;
            } else {
               this.$outer = L1Regularization.this;
               super();
            }
         }
      }
   }
}
