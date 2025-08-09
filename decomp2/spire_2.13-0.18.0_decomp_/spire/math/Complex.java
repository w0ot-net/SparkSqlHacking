package spire.math;

import algebra.ring.AdditiveMonoid;
import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.math.ScalaNumericConversions;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;
import spire.algebra.IsReal;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019\u0015s!\u00020`\u0011\u0003!g!\u00024`\u0011\u00039\u0007\"B=\u0002\t\u0003Q\b\"B>\u0002\t\u0003a\bb\u0002Ct\u0003\u0011\u0005A\u0011\u001e\u0005\b\u000b\u0003\tA\u0011AC\u0002\u0011\u001d)Y\"\u0001C\u0001\u000b;Aq!\"\u000f\u0002\t\u0007)Y\u0004C\u0004\u0006B\u0005!\u0019!b\u0011\t\u000f\u0015\u001d\u0013\u0001b\u0001\u0006J!9QqJ\u0001\u0005\u0004\u0015E\u0003bBC+\u0003\u0011\rQq\u000b\u0005\b\u000bS\nA1AC6\u0011\u001d)y'\u0001C\u0001\u000bcBq!b'\u0002\t\u0003)i\nC\u0004\u0006<\u0006!\t!\"0\t\u000f\u0015\u0005\u0018\u0001\"\u0001\u0006d\"IQ1T\u0001\u0002\u0002\u0013\u0005e\u0011\u0002\u0005\n\rC\t\u0011\u0011!CA\rGA\u0011Bb\u0011\u0002\u0003\u0003%Ia!9\u0007\t\u0019|&i \u0005\u000b\u0003_!\"Q3A\u0005\u0002\u0005E\u0002BCA3)\tE\t\u0015!\u0003\u00024!Q\u0011q\r\u000b\u0003\u0016\u0004%\t!!\r\t\u0015\u0005%DC!E!\u0002\u0013\t\u0019\u0004\u0003\u0004z)\u0011\u0005\u00111\u000e\u0005\b\u0003g\"B\u0011AA;\u0011\u001d\t\u0019\f\u0006C\u0001\u0003kCq!a0\u0015\t\u0003\t\t\rC\u0004\u0002NR!\t!a4\t\u000f\u0005}G\u0003\"\u0001\u0002b\"9\u00111\u001e\u000b\u0005\u0002\u00055\bbBAy)\u0011\u0005\u00111\u001f\u0005\b\u0003w$B\u0011AA\u007f\u0011\u001d\u0011I\u0001\u0006C\u0001\u0005\u0017AqA!\u0006\u0015\t\u0003\u00119\u0002C\u0004\u0003\u001cQ!\tA!\b\t\u000f\t\u0005B\u0003\"\u0001\u0003$!9!1\u0007\u000b\u0005\u0002\tU\u0002b\u0002B\u001f)\u0011\u0005!q\b\u0005\b\u0005\u0007\"B\u0011\u0001B#\u0011\u001d\u0011y\u0005\u0006C\u0001\u0005#BqA!\u0017\u0015\t\u0003\u0011Y\u0006C\u0004\u0003dQ!\tA!\u001a\t\u000f\t5D\u0003\"\u0001\u0003p!9!\u0011\u0011\u000b\u0005\u0002\t\r\u0005b\u0002B\")\u0011\u0005!1\u0013\u0005\b\u0005\u001f\"B\u0011\u0001BN\u0011\u001d\u0011I\u0006\u0006C\u0001\u0005GCqAa\u0019\u0015\t\u0003\u0011Y\u000bC\u0004\u0003nQ!\tAa.\t\u000f\t5G\u0003\"\u0001\u0003P\"9!\u0011\u0011\u000b\u0005\u0002\t\u0005\bb\u0002B7)\u0011\u0005!\u0011\u001f\u0005\b\u0005\u0003#B\u0011AB\u0001\u0011\u001d\u0019\t\u0002\u0006C\u0001\u0007'Aqaa\b\u0015\t\u0003\u0019\t\u0003C\u0004\u0004.Q!\taa\f\t\u000f\reB\u0003\"\u0001\u0004<!91q\b\u000b\u0005\u0002\r\u0005\u0003bBB#)\u0011\u00051q\t\u0005\b\u0007+\"B\u0011AB,\u0011\u001d\u0019\u0019\u0007\u0006C\u0001\u0007KBqa!\u001d\u0015\t\u0003\u0019\u0019\bC\u0004\u0004zQ!\taa\u001f\t\u000f\r\u0005E\u0003\"\u0001\u0004\u0004\"91\u0011\u0012\u000b\u0005\u0002\r-\u0005bBBI)\u0011\u000511\u0013\u0005\b\u00073#B\u0011ABN\u0011\u001d\u0019\t\u000b\u0006C\u0001\u0007GCqa!+\u0015\t\u0003\u0019Y\u000bC\u0004\u00044R!\ta!.\t\u000f\ruF\u0003\"\u0011\u0004@\"91q\u0019\u000b\u0005B\r%\u0007bBBi)\u0011\u000511\u001b\u0005\b\u0007+$B\u0011IBl\u0011\u001d\u0019y\u000e\u0006C\u0001\u0007CDqaa<\u0015\t\u0003\u0019\t\u0010C\u0004\u0004tR!)e!=\t\u000f\rUH\u0003\"\u0011\u0004x\"91\u0011 \u000b\u0005B\rm\bb\u0002C\u0001)\u0011\u0005A1\u0001\u0005\b\t#!B\u0011\u0001C\n\u0011\u001d!\t\u0003\u0006C!\tGAq\u0001\"\u000e\u0015\t\u0003!9\u0004C\u0005\u0005JQ\t\t\u0011\"\u0001\u0005L!IA1\r\u000b\u0012\u0002\u0013\u0005AQ\r\u0005\n\t\u0013#\u0012\u0013!C\u0001\t\u0017C\u0011\u0002\"'\u0015\u0003\u0003%\t\u0005b'\t\u0013\u0011\u0005F#!A\u0005\u0002\rM\u0007\"\u0003CR)\u0005\u0005I\u0011\u0001CS\u0011%!Y\u000bFA\u0001\n\u0003\"i\u000bC\u0005\u0005<R\t\t\u0011\"\u0001\u0005>\"IA\u0011\u0019\u000b\u0002\u0002\u0013\u0005C1Y\u0001\b\u0007>l\u0007\u000f\\3y\u0015\t\u0001\u0017-\u0001\u0003nCRD'\"\u00012\u0002\u000bM\u0004\u0018N]3\u0004\u0001A\u0011Q-A\u0007\u0002?\n91i\\7qY\u0016D8\u0003B\u0001i]F\u0004\"!\u001b7\u000e\u0003)T\u0011a[\u0001\u0006g\u000e\fG.Y\u0005\u0003[*\u0014a!\u00118z%\u00164\u0007CA3p\u0013\t\u0001xL\u0001\tD_6\u0004H.\u001a=J]N$\u0018M\\2fgB\u0011!o^\u0007\u0002g*\u0011A/^\u0001\u0003S>T\u0011A^\u0001\u0005U\u00064\u0018-\u0003\u0002yg\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012\u0001Z\u0001\u0002SV\u0019Q\u0010\"6\u0015\u0007y$\t\u000f\u0005\u0003f)\u0011MW\u0003BA\u0001\u0003o\u0019\u0012\u0002FA\u0002\u0003\u001b\t\u0019\"!\u000b\u0011\t\u0005\u0015\u0011\u0011B\u0007\u0003\u0003\u000fQ!\u0001\u00196\n\t\u0005-\u0011q\u0001\u0002\f'\u000e\fG.\u0019(v[\n,'\u000f\u0005\u0003\u0002\u0006\u0005=\u0011\u0002BA\t\u0003\u000f\u0011qcU2bY\u0006tU/\\3sS\u000e\u001cuN\u001c<feNLwN\\:\u0011\t\u0005U\u0011Q\u0005\b\u0005\u0003/\t\tC\u0004\u0003\u0002\u001a\u0005}QBAA\u000e\u0015\r\tibY\u0001\u0007yI|w\u000e\u001e \n\u0003-L1!a\tk\u0003\u001d\u0001\u0018mY6bO\u0016L1\u0001_A\u0014\u0015\r\t\u0019C\u001b\t\u0004S\u0006-\u0012bAA\u0017U\n9\u0001K]8ek\u000e$\u0018\u0001\u0002:fC2,\"!a\r\u0011\t\u0005U\u0012q\u0007\u0007\u0001\t-\tI\u0004\u0006Q\u0001\u0002\u0003\u0015\r!a\u000f\u0003\u0003Q\u000bB!!\u0010\u0002DA\u0019\u0011.a\u0010\n\u0007\u0005\u0005#NA\u0004O_RD\u0017N\\4\u0011\u0007%\f)%C\u0002\u0002H)\u00141!\u00118zQ!\t9$a\u0013\u0002R\u0005m\u0003cA5\u0002N%\u0019\u0011q\n6\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\nG\u0005M\u0013QKA-\u0003/r1![A+\u0013\r\t9F[\u0001\u0006\r2|\u0017\r^\u0019\u0007I\u0005]\u0011qD62\u0013\r\ni&a\u0018\u0002d\u0005\u0005dbA5\u0002`%\u0019\u0011\u0011\r6\u0002\r\u0011{WO\u00197fc\u0019!\u0013qCA\u0010W\u0006)!/Z1mA\u0005!\u0011.\\1h\u0003\u0015IW.Y4!)\u0019\ti'a\u001c\u0002rA!Q\rFA\u001a\u0011\u001d\ty#\u0007a\u0001\u0003gAq!a\u001a\u001a\u0001\u0004\t\u0019$A\u0007d_6\u0004H.\u001a=TS\u001etW/\u001c\u000b\u000b\u0003[\n9(a%\u0002 \u0006%\u0006bBA=5\u0001\u000f\u00111P\u0001\u0002MB1\u0011QPAG\u0003gqA!a \u0002\n:!\u0011\u0011QAC\u001d\u0011\tI\"a!\n\u0003\tL1!a\"b\u0003\u001d\tGnZ3ce\u0006LA!a\t\u0002\f*\u0019\u0011qQ1\n\t\u0005=\u0015\u0011\u0013\u0002\u0006\r&,G\u000e\u001a\u0006\u0005\u0003G\tY\tC\u0004\u0002\u0016j\u0001\u001d!a&\u0002\u00039\u0004b!!'\u0002\u001c\u0006MRBAAF\u0013\u0011\ti*a#\u0003\u000b9\u0013vn\u001c;\t\u000f\u0005\u0005&\u0004q\u0001\u0002$\u0006\tq\u000e\u0005\u0004\u0002~\u0005\u0015\u00161G\u0005\u0005\u0003O\u000b\tJA\u0003Pe\u0012,'\u000fC\u0004\u0002,j\u0001\u001d!!,\u0002\u0003M\u0004b!! \u00020\u0006M\u0012\u0002BAY\u0003#\u0013aaU5h]\u0016$\u0017aA1cgRQ\u00111GA\\\u0003s\u000bY,!0\t\u000f\u0005e4\u0004q\u0001\u0002|!9\u0011QS\u000eA\u0004\u0005]\u0005bBAQ7\u0001\u000f\u00111\u0015\u0005\b\u0003W[\u00029AAW\u0003%\t'm]*rk\u0006\u0014X\r\u0006\u0003\u00024\u0005\r\u0007bBAc9\u0001\u000f\u0011qY\u0001\u0002eB1\u0011QPAe\u0003gIA!a3\u0002\u0012\n)1IU5oO\u0006\u0019\u0011M]4\u0015\u0011\u0005M\u0012\u0011[Aj\u0003+Dq!!\u001f\u001e\u0001\b\tY\bC\u0004\u0002,v\u0001\u001d!!,\t\u000f\u0005]W\u0004q\u0001\u0002Z\u0006\tA\u000f\u0005\u0004\u0002\u001a\u0006m\u00171G\u0005\u0005\u0003;\fYI\u0001\u0003Ue&<\u0017\u0001\u00028pe6$\"\"a\r\u0002d\u0006\u0015\u0018q]Au\u0011\u001d\tIH\ba\u0002\u0003wBq!!&\u001f\u0001\b\t9\nC\u0004\u0002\"z\u0001\u001d!a)\t\u000f\u0005-f\u0004q\u0001\u0002.\u0006I1m\u001c8kk\u001e\fG/\u001a\u000b\u0005\u0003[\ny\u000fC\u0004\u0002z}\u0001\u001d!a2\u0002\u000f\u0005\u001cH+\u001e9mKV\u0011\u0011Q\u001f\t\bS\u0006]\u00181GA\u001a\u0013\r\tIP\u001b\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u0019\u0005\u001c\bk\u001c7beR+\b\u000f\\3\u0015\u0019\u0005U\u0018q B\u0001\u0005\u0007\u0011)Aa\u0002\t\u000f\u0005e\u0014\u0005q\u0001\u0002|!9\u0011QS\u0011A\u0004\u0005]\u0005bBAQC\u0001\u000f\u00111\u0015\u0005\b\u0003W\u000b\u00039AAW\u0011\u001d\t9.\ta\u0002\u00033\fa![:[KJ|G\u0003\u0002B\u0007\u0005'\u00012!\u001bB\b\u0013\r\u0011\tB\u001b\u0002\b\u0005>|G.Z1o\u0011\u001d\tYK\ta\u0002\u0003[\u000b1\"[:J[\u0006<\u0017N\\1ssR!!Q\u0002B\r\u0011\u001d\tYk\ta\u0002\u0003[\u000ba![:SK\u0006dG\u0003\u0002B\u0007\u0005?Aq!a+%\u0001\b\ti+A\u0002fcZ$BA!\n\u00030Q!!Q\u0002B\u0014\u0011\u001d\t\t+\na\u0002\u0005S\u0001b!! \u0003,\u0005M\u0012\u0002\u0002B\u0017\u0003#\u0013!!R9\t\u000f\tER\u00051\u0001\u0002n\u0005\t!-\u0001\u0003oKF4H\u0003\u0002B\u001c\u0005w!BA!\u0004\u0003:!9\u0011\u0011\u0015\u0014A\u0004\t%\u0002b\u0002B\u0019M\u0001\u0007\u0011QN\u0001\rk:\f'/_0%[&tWo\u001d\u000b\u0005\u0003[\u0012\t\u0005C\u0004\u0002F\u001e\u0002\u001d!a2\u0002\u000b\u0011\u0002H.^:\u0015\t\t\u001d#1\n\u000b\u0005\u0003[\u0012I\u0005C\u0004\u0002F\"\u0002\u001d!a2\t\u000f\t5\u0003\u00061\u0001\u00024\u0005\u0019!\u000f[:\u0002\r\u0011j\u0017N\\;t)\u0011\u0011\u0019Fa\u0016\u0015\t\u00055$Q\u000b\u0005\b\u0003\u000bL\u00039AAd\u0011\u001d\u0011i%\u000ba\u0001\u0003g\ta\u0001\n;j[\u0016\u001cH\u0003\u0002B/\u0005C\"B!!\u001c\u0003`!9\u0011Q\u0019\u0016A\u0004\u0005\u001d\u0007b\u0002B'U\u0001\u0007\u00111G\u0001\u0005I\u0011Lg\u000f\u0006\u0003\u0003h\t-D\u0003BA7\u0005SBq!!2,\u0001\b\tY\bC\u0004\u0003N-\u0002\r!a\r\u0002\u0019\u0011\"\u0018.\\3tIQLW.Z:\u0015\t\tE$Q\u0010\u000b\r\u0003[\u0012\u0019H!\u001e\u0003x\te$1\u0010\u0005\b\u0003sb\u00039AA>\u0011\u001d\t)\n\fa\u0002\u0003/Cq!!)-\u0001\b\t\u0019\u000bC\u0004\u0002,2\u0002\u001d!!,\t\u000f\u0005]G\u0006q\u0001\u0002Z\"9!q\u0010\u0017A\u0002\u0005M\u0012!A3\u0002\u0007A|w\u000f\u0006\u0003\u0003\u0006\nEE\u0003DA7\u0005\u000f\u0013IIa#\u0003\u000e\n=\u0005bBA=[\u0001\u000f\u00111\u0010\u0005\b\u0003+k\u00039AAL\u0011\u001d\t\t+\fa\u0002\u0003GCq!a+.\u0001\b\ti\u000bC\u0004\u0002X6\u0002\u001d!!7\t\u000f\t}T\u00061\u0001\u00024Q!!Q\u0013BM)\u0011\tiGa&\t\u000f\u0005\u0015g\u0006q\u0001\u0002H\"9!\u0011\u0007\u0018A\u0002\u00055D\u0003\u0002BO\u0005C#B!!\u001c\u0003 \"9\u0011QY\u0018A\u0004\u0005\u001d\u0007b\u0002B\u0019_\u0001\u0007\u0011Q\u000e\u000b\u0005\u0005K\u0013I\u000b\u0006\u0003\u0002n\t\u001d\u0006bBAca\u0001\u000f\u0011q\u0019\u0005\b\u0005c\u0001\u0004\u0019AA7)\u0011\u0011iK!.\u0015\u0011\u00055$q\u0016BY\u0005gCq!!\u001f2\u0001\b\tY\bC\u0004\u0002\"F\u0002\u001d!a)\t\u000f\u0005-\u0016\u0007q\u0001\u0002.\"9!\u0011G\u0019A\u0002\u00055D\u0003\u0002B]\u0005\u000b$B\"!\u001c\u0003<\nu&q\u0018Ba\u0005\u0007Dq!!\u001f3\u0001\b\tY\bC\u0004\u0002\u0016J\u0002\u001d!a&\t\u000f\u0005\u0005&\u0007q\u0001\u0002$\"9\u00111\u0016\u001aA\u0004\u00055\u0006bBAle\u0001\u000f\u0011\u0011\u001c\u0005\b\u0005c\u0011\u0004\u0019\u0001Bd!\rI'\u0011Z\u0005\u0004\u0005\u0017T'aA%oi\u0006)aN]8piR!!\u0011\u001bBo)1\tiGa5\u0003V\n]'\u0011\u001cBn\u0011\u001d\tIh\ra\u0002\u0003wBq!!&4\u0001\b\t9\nC\u0004\u0002\"N\u0002\u001d!a)\t\u000f\u0005-6\u0007q\u0001\u0002.\"9\u0011q[\u001aA\u0004\u0005e\u0007b\u0002Bpg\u0001\u0007!qY\u0001\u0002WR!!1\u001dBx)1\tiG!:\u0003h\n%(1\u001eBw\u0011\u001d\tI\b\u000ea\u0002\u0003wBq!!&5\u0001\b\t9\nC\u0004\u0002\"R\u0002\u001d!a)\t\u000f\u0005-F\u0007q\u0001\u0002.\"9\u0011q\u001b\u001bA\u0004\u0005e\u0007b\u0002B\u0019i\u0001\u0007!q\u0019\u000b\u0005\u0005g\u0014y\u0010\u0006\u0007\u0002n\tU(q\u001fB}\u0005w\u0014i\u0010C\u0004\u0002zU\u0002\u001d!a\u001f\t\u000f\u0005UU\u0007q\u0001\u0002\u0018\"9\u0011\u0011U\u001bA\u0004\u0005\r\u0006bBAVk\u0001\u000f\u0011Q\u0016\u0005\b\u0003/,\u00049AAm\u0011\u001d\u0011\t$\u000ea\u0001\u0003[\"Baa\u0001\u0004\u0010Qa\u0011QNB\u0003\u0007\u000f\u0019Iaa\u0003\u0004\u000e!9\u0011\u0011\u0010\u001cA\u0004\u0005m\u0004bBAKm\u0001\u000f\u0011q\u0013\u0005\b\u0003C3\u00049AAR\u0011\u001d\tYK\u000ea\u0002\u0003[Cq!a67\u0001\b\tI\u000eC\u0004\u00032Y\u0002\r!!\u001c\u0002\u00071|w\r\u0006\u0007\u0002n\rU1qCB\r\u00077\u0019i\u0002C\u0004\u0002z]\u0002\u001d!a\u001f\t\u000f\u0005Uu\u0007q\u0001\u0002\u0018\"9\u0011\u0011U\u001cA\u0004\u0005\r\u0006bBAlo\u0001\u000f\u0011\u0011\u001c\u0005\b\u0003W;\u00049AAW\u0003\u0011\u0019\u0018O\u001d;\u0015\u0015\u0005541EB\u0013\u0007S\u0019Y\u0003C\u0004\u0002za\u0002\u001d!a\u001f\t\u000f\r\u001d\u0002\bq\u0001\u0002\u0018\u0006\u0011a\u000e\r\u0005\b\u0003CC\u00049AAR\u0011\u001d\tY\u000b\u000fa\u0002\u0003[\u000bQA\u001a7p_J$B!!\u001c\u00042!9\u0011\u0011U\u001dA\u0004\rM\u0002CBAM\u0007k\t\u0019$\u0003\u0003\u00048\u0005-%AB%t%\u0016\fG.\u0001\u0003dK&dG\u0003BA7\u0007{Aq!!);\u0001\b\u0019\u0019$A\u0003s_VtG\r\u0006\u0003\u0002n\r\r\u0003bBAQw\u0001\u000f11G\u0001\u0005C\u000e|7\u000f\u0006\u0007\u0002n\r%31JB'\u0007\u001f\u001a\t\u0006C\u0004\u0002zq\u0002\u001d!a\u001f\t\u000f\u0005UE\bq\u0001\u0002\u0018\"9\u0011\u0011\u0015\u001fA\u0004\u0005\r\u0006bBAly\u0001\u000f\u0011\u0011\u001c\u0005\b\u0007'b\u00049AAW\u0003\t\u0019\b'\u0001\u0003bg&tG\u0003DA7\u00073\u001aYf!\u0018\u0004`\r\u0005\u0004bBA={\u0001\u000f\u00111\u0010\u0005\b\u0003+k\u00049AAL\u0011\u001d\t\t+\u0010a\u0002\u0003GCq!a6>\u0001\b\tI\u000eC\u0004\u0004Tu\u0002\u001d!!,\u0002\t\u0005$\u0018M\u001c\u000b\r\u0003[\u001a9g!\u001b\u0004l\r54q\u000e\u0005\b\u0003sr\u00049AA>\u0011\u001d\t\tK\u0010a\u0002\u0003GCq!!2?\u0001\b\t9\nC\u0004\u0002,z\u0002\u001d!!,\t\u000f\u0005]g\bq\u0001\u0002Z\u0006\u0019Q\r\u001f9\u0015\r\u000554QOB<\u0011\u001d\tIh\u0010a\u0002\u0003wBq!a6@\u0001\b\tI.A\u0002tS:$b!!\u001c\u0004~\r}\u0004bBA=\u0001\u0002\u000f\u00111\u0010\u0005\b\u0003/\u0004\u00059AAm\u0003\u0011\u0019\u0018N\u001c5\u0015\r\u000554QQBD\u0011\u001d\tI(\u0011a\u0002\u0003wBq!a6B\u0001\b\tI.A\u0002d_N$b!!\u001c\u0004\u000e\u000e=\u0005bBA=\u0005\u0002\u000f\u00111\u0010\u0005\b\u0003/\u0014\u00059AAm\u0003\u0011\u0019wn\u001d5\u0015\r\u000554QSBL\u0011\u001d\tIh\u0011a\u0002\u0003wBq!a6D\u0001\b\tI.A\u0002uC:$b!!\u001c\u0004\u001e\u000e}\u0005bBA=\t\u0002\u000f\u00111\u0010\u0005\b\u0003/$\u00059AAm\u0003\u0011!\u0018M\u001c5\u0015\r\u000554QUBT\u0011\u001d\tI(\u0012a\u0002\u0003wBq!a6F\u0001\b\tI.\u0001\u0006gY>\fGOV1mk\u0016,\"a!,\u0011\u0007%\u001cy+C\u0002\u00042*\u0014QA\u00127pCR\f1\u0002Z8vE2,g+\u00197vKV\u00111q\u0017\t\u0004S\u000ee\u0016bAB^U\n1Ai\\;cY\u0016\f\u0011BY=uKZ\u000bG.^3\u0016\u0005\r\u0005\u0007cA5\u0004D&\u00191Q\u00196\u0003\t\tKH/Z\u0001\u000bg\"|'\u000f\u001e,bYV,WCABf!\rI7QZ\u0005\u0004\u0007\u001fT'!B*i_J$\u0018\u0001C5oiZ\u000bG.^3\u0016\u0005\t\u001d\u0017!\u00037p]\u001e4\u0016\r\\;f+\t\u0019I\u000eE\u0002j\u00077L1a!8k\u0005\u0011auN\\4\u0002\u0015UtG-\u001a:ms&tw\r\u0006\u0002\u0004dB!1Q]Bv\u001b\t\u00199OC\u0002\u0004jV\fA\u0001\\1oO&!1Q^Bt\u0005\u0019y%M[3di\u00069\u0011n],i_2,WC\u0001B\u0007\u0003)I7OV1mS\u0012Le\u000e^\u0001\tQ\u0006\u001c\bnQ8eKR\u0011!qY\u0001\u0007KF,\u0018\r\\:\u0015\t\t51Q \u0005\b\u0007\u007f\u0004\u0006\u0019AA\"\u0003\u0011!\b.\u0019;\u0002\u0013\u0011*\u0017\u000fJ3rI\u0015\fH\u0003\u0002B\u0007\t\u000bAqaa@R\u0001\u0004!9\u0001\r\u0003\u0005\n\u00115\u0001\u0003B3\u0015\t\u0017\u0001B!!\u000e\u0005\u000e\u0011aAq\u0002C\u0003\u0003\u0003\u0005\tQ!\u0001\u0002<\t\u0019q\fJ\u0019\u0002\u0017\u0011*\u0017\u000f\n2b]\u001e$S-\u001d\u000b\u0005\u0005\u001b!)\u0002C\u0004\u0004\u0000J\u0003\r\u0001b\u00061\t\u0011eAQ\u0004\t\u0005KR!Y\u0002\u0005\u0003\u00026\u0011uA\u0001\u0004C\u0010\t+\t\t\u0011!A\u0003\u0002\u0005m\"aA0%e\u0005AAo\\*ue&tw\r\u0006\u0002\u0005&A!Aq\u0005C\u0018\u001d\u0011!I\u0003b\u000b\u0011\u0007\u0005e!.C\u0002\u0005.)\fa\u0001\u0015:fI\u00164\u0017\u0002\u0002C\u0019\tg\u0011aa\u0015;sS:<'b\u0001C\u0017U\u0006aAo\\)vCR,'O\\5p]R!A\u0011\bC !\u0015)G1HA\u001a\u0013\r!id\u0018\u0002\u000b#V\fG/\u001a:oS>t\u0007b\u0002C!)\u0002\u000fA1I\u0001\u0003KZ\u0004b!! \u0005F\u0005M\u0012\u0002\u0002C$\u0003#\u0013a\"\u00113eSRLg/Z'p]>LG-\u0001\u0003d_BLX\u0003\u0002C'\t'\"b\u0001b\u0014\u0005`\u0011\u0005\u0004\u0003B3\u0015\t#\u0002B!!\u000e\u0005T\u0011Y\u0011\u0011H+!\u0002\u0003\u0005)\u0019AA\u001eQ!!\u0019&a\u0013\u0005X\u0011m\u0013'C\u0012\u0002T\u0005UC\u0011LA,c\u0019!\u0013qCA\u0010WFJ1%!\u0018\u0002`\u0011u\u0013\u0011M\u0019\u0007I\u0005]\u0011qD6\t\u0013\u0005=R\u000b%AA\u0002\u0011E\u0003\"CA4+B\u0005\t\u0019\u0001C)\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIE*B\u0001b\u001a\u0005~U\u0011A\u0011\u000e\u0016\u0005\u0003g!Yg\u000b\u0002\u0005nA!Aq\u000eC=\u001b\t!\tH\u0003\u0003\u0005t\u0011U\u0014!C;oG\",7m[3e\u0015\r!9H[\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002\u0002C>\tc\u0012\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t-\tID\u0016Q\u0001\u0002\u0003\u0015\r!a\u000f)\u0011\u0011u\u00141\nCA\t\u000b\u000b\u0014bIA*\u0003+\"\u0019)a\u00162\r\u0011\n9\"a\blc%\u0019\u0013QLA0\t\u000f\u000b\t'\r\u0004%\u0003/\tyb[\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00133+\u0011!9\u0007\"$\u0005\u0017\u0005er\u000b)A\u0001\u0002\u000b\u0007\u00111\b\u0015\t\t\u001b\u000bY\u0005\"%\u0005\u0016FJ1%a\u0015\u0002V\u0011M\u0015qK\u0019\u0007I\u0005]\u0011qD62\u0013\r\ni&a\u0018\u0005\u0018\u0006\u0005\u0014G\u0002\u0013\u0002\u0018\u0005}1.A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\t;\u0003Ba!:\u0005 &!A\u0011GBt\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$B!a\u0011\u0005(\"IA\u0011\u0016.\u0002\u0002\u0003\u0007!qY\u0001\u0004q\u0012\n\u0014a\u00049s_\u0012,8\r^%uKJ\fGo\u001c:\u0016\u0005\u0011=\u0006C\u0002CY\to\u000b\u0019%\u0004\u0002\u00054*\u0019AQ\u00176\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0003\u0005:\u0012M&\u0001C%uKJ\fGo\u001c:\u0002\u0011\r\fg.R9vC2$BA!\u0004\u0005@\"IA\u0011\u0016/\u0002\u0002\u0003\u0007\u00111I\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0005\u001e\u0012\u0015\u0007\"\u0003CU;\u0006\u0005\t\u0019\u0001BdQ\u001d!B\u0011\u001aCh\t#\u00042!\u001bCf\u0013\r!iM\u001b\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012\u0001\u0001\t\u0005\u0003k!)\u000eB\u0006\u0002:\r\u0001\u000b\u0011!AC\u0002\u0005m\u0002\u0006\u0003Ck\u0003\u0017\"I\u000e\"82\u0013\r\n\u0019&!\u0016\u0005\\\u0006]\u0013G\u0002\u0013\u0002\u0018\u0005}1.M\u0005$\u0003;\ny\u0006b8\u0002bE2A%a\u0006\u0002 -Dq\u0001b9\u0004\u0001\b!)/A\u0001U!\u0019\ti(!3\u0005T\u0006\u0019qN\\3\u0016\t\u0011-H\u0011\u001f\u000b\u0005\t[$i\u0010\u0005\u0003f)\u0011=\b\u0003BA\u001b\tc$1\"!\u000f\u0005A\u0003\u0005\tQ1\u0001\u0002<!BA\u0011_A&\tk$I0M\u0005$\u0003'\n)\u0006b>\u0002XE2A%a\u0006\u0002 -\f\u0014bIA/\u0003?\"Y0!\u00192\r\u0011\n9\"a\bl\u0011\u001d!\u0019\u000f\u0002a\u0002\t\u007f\u0004b!! \u0002J\u0012=\u0018\u0001\u0002>fe>,B!\"\u0002\u0006\fQ!QqAC\f!\u0011)G#\"\u0003\u0011\t\u0005UR1\u0002\u0003\f\u0003s)\u0001\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0005\u0006\f\u0005-SqBC\nc%\u0019\u00131KA+\u000b#\t9&\r\u0004%\u0003/\tyb[\u0019\nG\u0005u\u0013qLC\u000b\u0003C\nd\u0001JA\f\u0003?Y\u0007b\u0002Cr\u000b\u0001\u000fQ\u0011\u0004\t\u0007\u0003{\nI-\"\u0003\u0002\u000f\u0019\u0014x.\\%oiV!QqDC\u0014)\u0011)\t#b\u000e\u0015\t\u0015\rR1\u0007\t\u0005KR))\u0003\u0005\u0003\u00026\u0015\u001dBaCA\u001d\r\u0001\u0006\t\u0011!b\u0001\u0003wA\u0003\"b\n\u0002L\u0015-RqF\u0019\nG\u0005M\u0013QKC\u0017\u0003/\nd\u0001JA\f\u0003?Y\u0017'C\u0012\u0002^\u0005}S\u0011GA1c\u0019!\u0013qCA\u0010W\"9\u0011\u0011\u0010\u0004A\u0004\u0015U\u0002CBA?\u0003\u0013,)\u0003C\u0004\u0002\u0016\u001a\u0001\rAa2\u0002\u0019%tG\u000fV8D_6\u0004H.\u001a=\u0015\t\u0015uRq\b\t\u0005KR\u00199\fC\u0004\u0002\u0016\u001e\u0001\rAa2\u0002\u001b1|gn\u001a+p\u0007>l\u0007\u000f\\3y)\u0011)i$\"\u0012\t\u000f\u0005U\u0005\u00021\u0001\u0004Z\u0006qa\r\\8biR{7i\\7qY\u0016DH\u0003BC&\u000b\u001b\u0002B!\u001a\u000b\u0004.\"9\u0011QS\u0005A\u0002\r5\u0016a\u00043pk\ndW\rV8D_6\u0004H.\u001a=\u0015\t\u0015uR1\u000b\u0005\b\u0003+S\u0001\u0019AB\\\u0003=\u0011\u0017nZ%oiR{7i\\7qY\u0016DH\u0003BC-\u000bC\u0002B!\u001a\u000b\u0006\\A!\u0011QCC/\u0013\u0011)y&a\n\u0003\u0015\tKw\rR3dS6\fG\u000eC\u0004\u0002\u0016.\u0001\r!b\u0019\u0011\t\u0005UQQM\u0005\u0005\u000bO\n9C\u0001\u0004CS\u001eLe\u000e^\u0001\u0014E&<G)Z2j[\u0006dGk\\\"p[BdW\r\u001f\u000b\u0005\u000b3*i\u0007C\u0004\u0002\u00162\u0001\r!b\u0017\u0002\u000bA|G.\u0019:\u0016\t\u0015MT1\u0010\u000b\u0007\u000bk*\u0019*b&\u0015\r\u0015]TqQCG!\u0011)G#\"\u001f\u0011\t\u0005UR1\u0010\u0003\f\u0003si\u0001\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0005\u0006|\u0005-SqPCBc%\u0019\u00131KA+\u000b\u0003\u000b9&\r\u0004%\u0003/\tyb[\u0019\nG\u0005u\u0013qLCC\u0003C\nd\u0001JA\f\u0003?Y\u0007\"CCE\u001b\u0005\u0005\t9ACF\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003{\ni)\"\u001f\t\u0013\u0015=U\"!AA\u0004\u0015E\u0015AC3wS\u0012,gnY3%eA1\u0011\u0011TAn\u000bsBq!\"&\u000e\u0001\u0004)I(A\u0005nC\u001et\u0017\u000e^;eK\"9Q\u0011T\u0007A\u0002\u0015e\u0014!B1oO2,\u0017!B1qa2LX\u0003BCP\u000bO#B!\")\u0006:R!Q1UCZ!\u0011)G#\"*\u0011\t\u0005URq\u0015\u0003\f\u0003sq\u0001\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0005\u0006(\u0006-S1VCXc%\u0019\u00131KA+\u000b[\u000b9&\r\u0004%\u0003/\tyb[\u0019\nG\u0005u\u0013qLCY\u0003C\nd\u0001JA\f\u0003?Y\u0007\"CC[\u001d\u0005\u0005\t9AC\\\u0003))g/\u001b3f]\u000e,Ge\r\t\u0007\u0003{\nI-\"*\t\u000f\u0005=b\u00021\u0001\u0006&\u0006Y!o\\8u\u001f\u001a,f.\u001b;z+\u0011)y,b2\u0015\r\u0015\u0005W1\\Co)\u0019)\u0019-b5\u0006XB!Q\rFCc!\u0011\t)$b2\u0005\u0017\u0005er\u0002)A\u0001\u0002\u000b\u0007\u00111\b\u0015\t\u000b\u000f\fY%b3\u0006PFJ1%a\u0015\u0002V\u00155\u0017qK\u0019\u0007I\u0005]\u0011qD62\u0013\r\ni&a\u0018\u0006R\u0006\u0005\u0014G\u0002\u0013\u0002\u0018\u0005}1\u000eC\u0004\u0002z=\u0001\u001d!\"6\u0011\r\u0005u\u0014QRCc\u0011\u001d\t9n\u0004a\u0002\u000b3\u0004b!!'\u0002\\\u0016\u0015\u0007bBAK\u001f\u0001\u0007!q\u0019\u0005\b\u000b?|\u0001\u0019\u0001Bd\u0003\u0005A\u0018\u0001\u0004:p_R\u001cxJZ+oSRLX\u0003BCs\u000bg$B!b:\u0007\bQ1Q\u0011^C\u0000\r\u0007\u0001R![Cv\u000b_L1!\"<k\u0005\u0015\t%O]1z!\u0011)G#\"=\u0011\t\u0005UR1\u001f\u0003\f\u0003s\u0001\u0002\u0015!A\u0001\u0006\u0004\tY\u0004\u000b\u0005\u0006t\u0006-Sq_C~c%\u0019\u00131KA+\u000bs\f9&\r\u0004%\u0003/\tyb[\u0019\nG\u0005u\u0013qLC\u007f\u0003C\nd\u0001JA\f\u0003?Y\u0007bBA=!\u0001\u000fa\u0011\u0001\t\u0007\u0003{\ni)\"=\t\u000f\u0005]\u0007\u0003q\u0001\u0007\u0006A1\u0011\u0011TAn\u000bcDq!!&\u0011\u0001\u0004\u00119-\u0006\u0003\u0007\f\u0019EAC\u0002D\u0007\r;1y\u0002\u0005\u0003f)\u0019=\u0001\u0003BA\u001b\r#!1\"!\u000f\u0012A\u0003\u0005\tQ1\u0001\u0002<!Ba\u0011CA&\r+1I\"M\u0005$\u0003'\n)Fb\u0006\u0002XE2A%a\u0006\u0002 -\f\u0014bIA/\u0003?2Y\"!\u00192\r\u0011\n9\"a\bl\u0011\u001d\ty#\u0005a\u0001\r\u001fAq!a\u001a\u0012\u0001\u00041y!A\u0004v]\u0006\u0004\b\u000f\\=\u0016\t\u0019\u0015b\u0011\u0007\u000b\u0005\rO1i\u0004E\u0003j\rS1i#C\u0002\u0007,)\u0014aa\u00149uS>t\u0007cB5\u0002x\u001a=bq\u0006\t\u0005\u0003k1\t\u0004B\u0006\u0002:I\u0001\u000b\u0011!AC\u0002\u0005m\u0002\u0006\u0003D\u0019\u0003\u00172)D\"\u000f2\u0013\r\n\u0019&!\u0016\u00078\u0005]\u0013G\u0002\u0013\u0002\u0018\u0005}1.M\u0005$\u0003;\nyFb\u000f\u0002bE2A%a\u0006\u0002 -D\u0011Bb\u0010\u0013\u0003\u0003\u0005\rA\"\u0011\u0002\u0007a$\u0003\u0007\u0005\u0003f)\u0019=\u0012\u0001D<sSR,'+\u001a9mC\u000e,\u0007"
)
public class Complex extends ScalaNumber implements ScalaNumericConversions, Product {
   private static final long serialVersionUID = 0L;
   public final Object real;
   public final Object imag;

   public static Option unapply(final Complex x$0) {
      return Complex$.MODULE$.unapply(x$0);
   }

   public static Complex apply(final Object real, final Object imag) {
      return Complex$.MODULE$.apply(real, imag);
   }

   public static Complex[] rootsOfUnity(final int n, final Field f, final Trig t) {
      return Complex$.MODULE$.rootsOfUnity(n, f, t);
   }

   public static Complex rootOfUnity(final int n, final int x, final Field f, final Trig t) {
      return Complex$.MODULE$.rootOfUnity(n, x, f, t);
   }

   public static Complex apply(final Object real, final CommutativeRing evidence$3) {
      return Complex$.MODULE$.apply(real, evidence$3);
   }

   public static Complex polar(final Object magnitude, final Object angle, final Field evidence$1, final Trig evidence$2) {
      return Complex$.MODULE$.polar(magnitude, angle, evidence$1, evidence$2);
   }

   public static Complex bigDecimalToComplex(final BigDecimal n) {
      return Complex$.MODULE$.bigDecimalToComplex(n);
   }

   public static Complex bigIntToComplex(final BigInt n) {
      return Complex$.MODULE$.bigIntToComplex(n);
   }

   public static Complex doubleToComplex(final double n) {
      return Complex$.MODULE$.doubleToComplex(n);
   }

   public static Complex floatToComplex(final float n) {
      return Complex$.MODULE$.floatToComplex(n);
   }

   public static Complex longToComplex(final long n) {
      return Complex$.MODULE$.longToComplex(n);
   }

   public static Complex intToComplex(final int n) {
      return Complex$.MODULE$.intToComplex(n);
   }

   public static Complex fromInt(final int n, final CommutativeRing f) {
      return Complex$.MODULE$.fromInt(n, f);
   }

   public static Complex zero(final CommutativeRing T) {
      return Complex$.MODULE$.zero(T);
   }

   public static Complex one(final CommutativeRing T) {
      return Complex$.MODULE$.one(T);
   }

   public static Complex i(final CommutativeRing T) {
      return Complex$.MODULE$.i(T);
   }

   public static Eq ComplexEq(final Eq evidence$13) {
      return Complex$.MODULE$.ComplexEq(evidence$13);
   }

   public static ComplexOnTrigImpl ComplexOnTrig(final Fractional evidence$9, final Order evidence$10, final Trig evidence$11, final Signed evidence$12) {
      return Complex$.MODULE$.ComplexOnTrig(evidence$9, evidence$10, evidence$11, evidence$12);
   }

   public static ComplexOnField ComplexOnField(final Field evidence$6, final Order evidence$7, final Signed evidence$8) {
      return Complex$.MODULE$.ComplexOnField(evidence$6, evidence$7, evidence$8);
   }

   public static ComplexOnCRing ComplexOnCRing(final CommutativeRing evidence$4, final Signed evidence$5) {
      return Complex$.MODULE$.ComplexOnCRing(evidence$4, evidence$5);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public boolean isValidByte() {
      return ScalaNumericAnyConversions.isValidByte$(this);
   }

   public boolean isValidShort() {
      return ScalaNumericAnyConversions.isValidShort$(this);
   }

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public Object real() {
      return this.real;
   }

   public Object imag() {
      return this.imag;
   }

   public Complex complexSignum(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.isZero(s) ? this : this.$div(this.abs(f, n, o, s), f);
   }

   public Object abs(final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot(this.real(), this.imag(), f, n, o, s);
   }

   public Object absSquare(final CommutativeRing r) {
      return r.plus(r.times(this.real(), this.real()), r.times(this.imag(), this.imag()));
   }

   public Object arg(final Field f, final Signed s, final Trig t) {
      return this.isZero(s) ? f.zero() : t.atan2(this.imag(), this.real());
   }

   public Object norm(final Field f, final NRoot n, final Order o, final Signed s) {
      return package$.MODULE$.hypot(this.real(), this.imag(), f, n, o, s);
   }

   public Complex conjugate(final CommutativeRing f) {
      return new Complex(this.real(), f.negate(this.imag()));
   }

   public Tuple2 asTuple() {
      return new Tuple2(this.real(), this.imag());
   }

   public Tuple2 asPolarTuple(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return new Tuple2(this.abs(f, n, o, s), this.arg(f, s, t));
   }

   public boolean isZero(final Signed s) {
      return s.isSignZero(this.real()) && s.isSignZero(this.imag());
   }

   public boolean isImaginary(final Signed s) {
      return s.isSignZero(this.real());
   }

   public boolean isReal(final Signed s) {
      return s.isSignZero(this.imag());
   }

   public boolean eqv(final Complex b, final Eq o) {
      return o.eqv(this.real(), b.real()) && o.eqv(this.imag(), b.imag());
   }

   public boolean neqv(final Complex b, final Eq o) {
      return o.neqv(this.real(), b.real()) || o.neqv(this.imag(), b.imag());
   }

   public Complex unary_$minus(final CommutativeRing r) {
      return new Complex(r.negate(this.real()), r.negate(this.imag()));
   }

   public Complex $plus(final Object rhs, final CommutativeRing r) {
      return new Complex(r.plus(this.real(), rhs), this.imag());
   }

   public Complex $minus(final Object rhs, final CommutativeRing r) {
      return new Complex(r.minus(this.real(), rhs), this.imag());
   }

   public Complex $times(final Object rhs, final CommutativeRing r) {
      return new Complex(r.times(this.real(), rhs), r.times(this.imag(), rhs));
   }

   public Complex $div(final Object rhs, final Field r) {
      return new Complex(r.div(this.real(), rhs), r.div(this.imag(), rhs));
   }

   public Complex $times$times(final Object e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(e, f, n, o, s, t);
   }

   public Complex pow(final Object e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      Complex var10000;
      if (s.isSignZero(e)) {
         var10000 = Complex$.MODULE$.one(f);
      } else if (this.isZero(s)) {
         if (o.lt(e, f.zero())) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = Complex$.MODULE$.zero(f);
      } else {
         var10000 = Complex$.MODULE$.polar(n.fpow(this.abs(f, n, o, s), e), f.times(this.arg(f, s, t), e), f, t);
      }

      return var10000;
   }

   public Complex $plus(final Complex b, final CommutativeRing r) {
      return new Complex(r.plus(this.real(), b.real()), r.plus(this.imag(), b.imag()));
   }

   public Complex $minus(final Complex b, final CommutativeRing r) {
      return new Complex(r.minus(this.real(), b.real()), r.minus(this.imag(), b.imag()));
   }

   public Complex $times(final Complex b, final CommutativeRing r) {
      return new Complex(r.minus(r.times(this.real(), b.real()), r.times(this.imag(), b.imag())), r.plus(r.times(this.imag(), b.real()), r.times(this.real(), b.imag())));
   }

   public Complex $div(final Complex b, final Field f, final Order o, final Signed s) {
      Object abs_breal = s.abs(b.real());
      Object abs_bimag = s.abs(b.imag());
      Complex var10000;
      if (o.gteqv(abs_breal, abs_bimag)) {
         if (o.eqv(abs_breal, f.zero())) {
            throw new Exception("/ by zero");
         }

         Object ratio = f.div(b.imag(), b.real());
         Object denom = f.plus(b.real(), f.times(b.imag(), ratio));
         var10000 = new Complex(f.div(f.plus(this.real(), f.times(this.imag(), ratio)), denom), f.div(f.minus(this.imag(), f.times(this.real(), ratio)), denom));
      } else {
         if (o.eqv(abs_bimag, f.zero())) {
            throw new Exception("/ by zero");
         }

         Object ratio = f.div(b.real(), b.imag());
         Object denom = f.plus(f.times(b.real(), ratio), b.imag());
         var10000 = new Complex(f.div(f.plus(f.times(this.real(), ratio), this.imag()), denom), f.div(f.minus(f.times(this.imag(), ratio), this.real()), denom));
      }

      return var10000;
   }

   public Complex $times$times(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(b, f, n, o, s, t);
   }

   public Complex nroot(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.isZero(s) ? Complex$.MODULE$.zero(f) : this.pow(new Complex(f.reciprocal(f.fromInt(k)), f.zero()), f, n, o, s, t);
   }

   public Complex pow(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.isZero(s) ? Complex$.MODULE$.zero(f) : Complex$.MODULE$.polar(f.pow(this.abs(f, n, o, s), b), f.times(this.arg(f, s, t), f.fromInt(b)), f, t);
   }

   public Complex $times$times(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(b, f, n, o, s, t);
   }

   public Complex pow(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      Complex var10000;
      if (b.isZero(s)) {
         var10000 = Complex$.MODULE$.one(f);
      } else if (this.isZero(s)) {
         if (o.neqv(b.imag(), f.zero()) || o.lt(b.real(), f.zero())) {
            throw new Exception("raising 0 to negative/complex power");
         }

         var10000 = Complex$.MODULE$.zero(f);
      } else if (o.neqv(b.imag(), f.zero())) {
         Object len = f.div(n.fpow(this.abs(f, n, o, s), b.real()), t.exp(f.times(this.arg(f, s, t), b.imag())));
         Object phase = f.plus(f.times(this.arg(f, s, t), b.real()), f.times(t.log(this.abs(f, n, o, s)), b.imag()));
         var10000 = Complex$.MODULE$.polar(len, phase, f, t);
      } else {
         var10000 = Complex$.MODULE$.polar(n.fpow(this.abs(f, n, o, s), b.real()), f.times(this.arg(f, s, t), b.real()), f, t);
      }

      return var10000;
   }

   public Complex log(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      if (this.isZero(s)) {
         throw new IllegalArgumentException("log(0) undefined");
      } else {
         return new Complex(t.log(this.abs(f, n, o, s)), this.arg(f, s, t));
      }
   }

   public Complex sqrt(final Field f, final NRoot n0, final Order o, final Signed s) {
      Complex var10000;
      if (this.isZero(s)) {
         var10000 = this;
      } else if (s.isSignZero(this.imag())) {
         var10000 = s.isSignNegative(this.real()) ? new Complex(f.zero(), n0.sqrt(s.abs(this.real()))) : new Complex(n0.sqrt(s.abs(this.real())), f.zero());
      } else {
         Object two = f.fromInt(2);
         Object abs = this.abs(f, n0, o, s);
         Object a = n0.sqrt(f.div(f.plus(abs, this.real()), two));
         Object b = n0.sqrt(f.div(f.minus(abs, this.real()), two));
         var10000 = s.isSignNegative(this.imag()) ? new Complex(a, f.negate(b)) : new Complex(a, b);
      }

      return var10000;
   }

   public Complex floor(final IsReal o) {
      return new Complex(o.floor(this.real()), o.floor(this.imag()));
   }

   public Complex ceil(final IsReal o) {
      return new Complex(o.ceil(this.real()), o.ceil(this.imag()));
   }

   public Complex round(final IsReal o) {
      return new Complex(o.round(this.real()), o.round(this.imag()));
   }

   public Complex acos(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      Complex z2 = this.$times((Complex)this, f);
      Complex s = (new Complex(f.minus(f.one(), z2.real()), f.negate(z2.imag()))).sqrt(f, n, o, s0);
      Complex l = (new Complex(f.plus(this.real(), s.imag()), f.plus(this.imag(), s.real()))).log(f, n, o, t, s0);
      return new Complex(l.imag(), f.negate(l.real()));
   }

   public Complex asin(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      Complex z2 = this.$times((Complex)this, f);
      Complex s = (new Complex(f.minus(f.one(), z2.real()), f.negate(z2.imag()))).sqrt(f, n, o, s0);
      Complex l = (new Complex(f.plus(s.real(), f.negate(this.imag())), f.plus(s.imag(), this.real()))).log(f, n, o, t, s0);
      return new Complex(l.imag(), f.negate(l.real()));
   }

   public Complex atan(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      Complex n = new Complex(this.real(), f.plus(this.imag(), f.one()));
      Complex d = new Complex(f.negate(this.real()), f.minus(f.one(), this.imag()));
      Complex l = n.$div(d, f, o, s).log(f, r, o, t, s);
      return new Complex(f.div(l.imag(), f.fromInt(-2)), f.div(l.real(), f.fromInt(2)));
   }

   public Complex exp(final Field f, final Trig t) {
      return new Complex(f.times(t.exp(this.real()), t.cos(this.imag())), f.times(t.exp(this.real()), t.sin(this.imag())));
   }

   public Complex sin(final Field f, final Trig t) {
      return new Complex(f.times(t.sin(this.real()), t.cosh(this.imag())), f.times(t.cos(this.real()), t.sinh(this.imag())));
   }

   public Complex sinh(final Field f, final Trig t) {
      return new Complex(f.times(t.sinh(this.real()), t.cos(this.imag())), f.times(t.cosh(this.real()), t.sin(this.imag())));
   }

   public Complex cos(final Field f, final Trig t) {
      return new Complex(f.times(t.cos(this.real()), t.cosh(this.imag())), f.times(f.negate(t.sin(this.real())), t.sinh(this.imag())));
   }

   public Complex cosh(final Field f, final Trig t) {
      return new Complex(f.times(t.cosh(this.real()), t.cos(this.imag())), f.times(t.sinh(this.real()), t.sin(this.imag())));
   }

   public Complex tan(final Field f, final Trig t) {
      Object r2 = f.plus(this.real(), this.real());
      Object i2 = f.plus(this.imag(), this.imag());
      Object d = f.plus(t.cos(r2), t.cosh(i2));
      return new Complex(f.div(t.sin(r2), d), f.div(t.sinh(i2), d));
   }

   public Complex tanh(final Field f, final Trig t) {
      Object r2 = f.plus(this.real(), this.real());
      Object i2 = f.plus(this.imag(), this.imag());
      Object d = f.plus(t.cos(r2), t.cosh(i2));
      return new Complex(f.div(t.sinh(r2), d), f.div(t.sin(i2), d));
   }

   public float floatValue() {
      return (float)this.doubleValue();
   }

   public double doubleValue() {
      return package$.MODULE$.anyToDouble(this.real());
   }

   public byte byteValue() {
      return (byte)((int)this.longValue());
   }

   public short shortValue() {
      return (short)((int)this.longValue());
   }

   public int intValue() {
      return (int)this.longValue();
   }

   public long longValue() {
      return package$.MODULE$.anyToLong(this.real());
   }

   public Object underlying() {
      return this;
   }

   public boolean isWhole() {
      return package$.MODULE$.anyIsZero(this.imag()) && package$.MODULE$.anyIsWhole(this.real());
   }

   public final boolean isValidInt() {
      return package$.MODULE$.anyIsZero(this.imag()) && package$.MODULE$.anyIsValidInt(this.real());
   }

   public int hashCode() {
      return package$.MODULE$.anyIsZero(this.imag()) ? Statics.anyHash(this.real()) : 19 * Statics.anyHash(this.real()) + 41 * Statics.anyHash(this.imag()) + 97;
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Complex) {
         Complex var4 = (Complex)that;
         var2 = this.$eq$eq$eq(var4);
      } else if (that instanceof Quaternion) {
         Quaternion var5 = (Quaternion)that;
         var2 = BoxesRunTime.equals(this.real(), var5.r()) && BoxesRunTime.equals(this.imag(), var5.i()) && package$.MODULE$.anyIsZero(var5.j()) && package$.MODULE$.anyIsZero(var5.k());
      } else {
         var2 = package$.MODULE$.anyIsZero(this.imag()) && BoxesRunTime.equals(this.real(), that);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Complex that) {
      return BoxesRunTime.equals(this.real(), that.real()) && BoxesRunTime.equals(this.imag(), that.imag());
   }

   public boolean $eq$bang$eq(final Complex that) {
      return !this.$eq$eq$eq(that);
   }

   public String toString() {
      return (new StringBuilder(6)).append("(").append(this.real()).append(" + ").append(this.imag()).append("i)").toString();
   }

   public Quaternion toQuaternion(final AdditiveMonoid ev) {
      return new Quaternion(this.real(), this.imag(), ev.zero(), ev.zero());
   }

   public Complex copy(final Object real, final Object imag) {
      return new Complex(real, imag);
   }

   public Object copy$default$1() {
      return this.real();
   }

   public Object copy$default$2() {
      return this.imag();
   }

   public String productPrefix() {
      return "Complex";
   }

   public int productArity() {
      return 2;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.real();
            break;
         case 1:
            var10000 = this.imag();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof Complex;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "real";
            break;
         case 1:
            var10000 = "imag";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public double real$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.real());
   }

   public float real$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.real());
   }

   public double imag$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.imag());
   }

   public float imag$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.imag());
   }

   public Complex complexSignum$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.complexSignum(f, n, o, s);
   }

   public Complex complexSignum$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return this.complexSignum(f, n, o, s);
   }

   public double abs$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return BoxesRunTime.unboxToDouble(this.abs(f, n, o, s));
   }

   public float abs$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return BoxesRunTime.unboxToFloat(this.abs(f, n, o, s));
   }

   public double absSquare$mcD$sp(final CommutativeRing r) {
      return BoxesRunTime.unboxToDouble(this.absSquare(r));
   }

   public float absSquare$mcF$sp(final CommutativeRing r) {
      return BoxesRunTime.unboxToFloat(this.absSquare(r));
   }

   public double arg$mcD$sp(final Field f, final Signed s, final Trig t) {
      return BoxesRunTime.unboxToDouble(this.arg(f, s, t));
   }

   public float arg$mcF$sp(final Field f, final Signed s, final Trig t) {
      return BoxesRunTime.unboxToFloat(this.arg(f, s, t));
   }

   public double norm$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return BoxesRunTime.unboxToDouble(this.norm(f, n, o, s));
   }

   public float norm$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s) {
      return BoxesRunTime.unboxToFloat(this.norm(f, n, o, s));
   }

   public Complex conjugate$mcD$sp(final CommutativeRing f) {
      return this.conjugate(f);
   }

   public Complex conjugate$mcF$sp(final CommutativeRing f) {
      return this.conjugate(f);
   }

   public Tuple2 asTuple$mcD$sp() {
      return this.asTuple();
   }

   public Tuple2 asTuple$mcF$sp() {
      return this.asTuple();
   }

   public Tuple2 asPolarTuple$mcD$sp(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.asPolarTuple(f, n, o, s, t);
   }

   public Tuple2 asPolarTuple$mcF$sp(final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.asPolarTuple(f, n, o, s, t);
   }

   public boolean isZero$mcD$sp(final Signed s) {
      return this.isZero(s);
   }

   public boolean isZero$mcF$sp(final Signed s) {
      return this.isZero(s);
   }

   public boolean isImaginary$mcD$sp(final Signed s) {
      return this.isImaginary(s);
   }

   public boolean isImaginary$mcF$sp(final Signed s) {
      return this.isImaginary(s);
   }

   public boolean isReal$mcD$sp(final Signed s) {
      return this.isReal(s);
   }

   public boolean isReal$mcF$sp(final Signed s) {
      return this.isReal(s);
   }

   public boolean eqv$mcD$sp(final Complex b, final Eq o) {
      return this.eqv(b, o);
   }

   public boolean eqv$mcF$sp(final Complex b, final Eq o) {
      return this.eqv(b, o);
   }

   public boolean neqv$mcD$sp(final Complex b, final Eq o) {
      return this.neqv(b, o);
   }

   public boolean neqv$mcF$sp(final Complex b, final Eq o) {
      return this.neqv(b, o);
   }

   public Complex unary_$minus$mcD$sp(final CommutativeRing r) {
      return this.unary_$minus(r);
   }

   public Complex unary_$minus$mcF$sp(final CommutativeRing r) {
      return this.unary_$minus(r);
   }

   public Complex $plus$mcD$sp(final double rhs, final CommutativeRing r) {
      return this.$plus((Object)BoxesRunTime.boxToDouble(rhs), r);
   }

   public Complex $plus$mcF$sp(final float rhs, final CommutativeRing r) {
      return this.$plus((Object)BoxesRunTime.boxToFloat(rhs), r);
   }

   public Complex $minus$mcD$sp(final double rhs, final CommutativeRing r) {
      return this.$minus((Object)BoxesRunTime.boxToDouble(rhs), r);
   }

   public Complex $minus$mcF$sp(final float rhs, final CommutativeRing r) {
      return this.$minus((Object)BoxesRunTime.boxToFloat(rhs), r);
   }

   public Complex $times$mcD$sp(final double rhs, final CommutativeRing r) {
      return this.$times((Object)BoxesRunTime.boxToDouble(rhs), r);
   }

   public Complex $times$mcF$sp(final float rhs, final CommutativeRing r) {
      return this.$times((Object)BoxesRunTime.boxToFloat(rhs), r);
   }

   public Complex $div$mcD$sp(final double rhs, final Field r) {
      return this.$div(BoxesRunTime.boxToDouble(rhs), r);
   }

   public Complex $div$mcF$sp(final float rhs, final Field r) {
      return this.$div(BoxesRunTime.boxToFloat(rhs), r);
   }

   public Complex $times$times$mcD$sp(final double e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times((Object)BoxesRunTime.boxToDouble(e), f, n, o, s, t);
   }

   public Complex $times$times$mcF$sp(final float e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times((Object)BoxesRunTime.boxToFloat(e), f, n, o, s, t);
   }

   public Complex pow$mcD$sp(final double e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow((Object)BoxesRunTime.boxToDouble(e), f, n, o, s, t);
   }

   public Complex pow$mcF$sp(final float e, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow((Object)BoxesRunTime.boxToFloat(e), f, n, o, s, t);
   }

   public Complex $plus$mcD$sp(final Complex b, final CommutativeRing r) {
      return this.$plus(b, r);
   }

   public Complex $plus$mcF$sp(final Complex b, final CommutativeRing r) {
      return this.$plus(b, r);
   }

   public Complex $minus$mcD$sp(final Complex b, final CommutativeRing r) {
      return this.$minus(b, r);
   }

   public Complex $minus$mcF$sp(final Complex b, final CommutativeRing r) {
      return this.$minus(b, r);
   }

   public Complex $times$mcD$sp(final Complex b, final CommutativeRing r) {
      return this.$times(b, r);
   }

   public Complex $times$mcF$sp(final Complex b, final CommutativeRing r) {
      return this.$times(b, r);
   }

   public Complex $div$mcD$sp(final Complex b, final Field f, final Order o, final Signed s) {
      return this.$div(b, f, o, s);
   }

   public Complex $div$mcF$sp(final Complex b, final Field f, final Order o, final Signed s) {
      return this.$div(b, f, o, s);
   }

   public Complex $times$times$mcD$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times(b, f, n, o, s, t);
   }

   public Complex $times$times$mcF$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times(b, f, n, o, s, t);
   }

   public Complex nroot$mcD$sp(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.nroot(k, f, n, o, s, t);
   }

   public Complex nroot$mcF$sp(final int k, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.nroot(k, f, n, o, s, t);
   }

   public Complex pow$mcD$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(b, f, n, o, s, t);
   }

   public Complex pow$mcF$sp(final int b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(b, f, n, o, s, t);
   }

   public Complex $times$times$mcD$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times(b, f, n, o, s, t);
   }

   public Complex $times$times$mcF$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.$times$times(b, f, n, o, s, t);
   }

   public Complex pow$mcD$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(b, f, n, o, s, t);
   }

   public Complex pow$mcF$sp(final Complex b, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return this.pow(b, f, n, o, s, t);
   }

   public Complex log$mcD$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      return this.log(f, n, o, t, s);
   }

   public Complex log$mcF$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s) {
      return this.log(f, n, o, t, s);
   }

   public Complex sqrt$mcD$sp(final Field f, final NRoot n0, final Order o, final Signed s) {
      return this.sqrt(f, n0, o, s);
   }

   public Complex sqrt$mcF$sp(final Field f, final NRoot n0, final Order o, final Signed s) {
      return this.sqrt(f, n0, o, s);
   }

   public Complex floor$mcD$sp(final IsReal o) {
      return this.floor(o);
   }

   public Complex floor$mcF$sp(final IsReal o) {
      return this.floor(o);
   }

   public Complex ceil$mcD$sp(final IsReal o) {
      return this.ceil(o);
   }

   public Complex ceil$mcF$sp(final IsReal o) {
      return this.ceil(o);
   }

   public Complex round$mcD$sp(final IsReal o) {
      return this.round(o);
   }

   public Complex round$mcF$sp(final IsReal o) {
      return this.round(o);
   }

   public Complex acos$mcD$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.acos(f, n, o, t, s0);
   }

   public Complex acos$mcF$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.acos(f, n, o, t, s0);
   }

   public Complex asin$mcD$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.asin(f, n, o, t, s0);
   }

   public Complex asin$mcF$sp(final Field f, final NRoot n, final Order o, final Trig t, final Signed s0) {
      return this.asin(f, n, o, t, s0);
   }

   public Complex atan$mcD$sp(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      return this.atan(f, o, r, s, t);
   }

   public Complex atan$mcF$sp(final Field f, final Order o, final NRoot r, final Signed s, final Trig t) {
      return this.atan(f, o, r, s, t);
   }

   public Complex exp$mcD$sp(final Field f, final Trig t) {
      return this.exp(f, t);
   }

   public Complex exp$mcF$sp(final Field f, final Trig t) {
      return this.exp(f, t);
   }

   public Complex sin$mcD$sp(final Field f, final Trig t) {
      return this.sin(f, t);
   }

   public Complex sin$mcF$sp(final Field f, final Trig t) {
      return this.sin(f, t);
   }

   public Complex sinh$mcD$sp(final Field f, final Trig t) {
      return this.sinh(f, t);
   }

   public Complex sinh$mcF$sp(final Field f, final Trig t) {
      return this.sinh(f, t);
   }

   public Complex cos$mcD$sp(final Field f, final Trig t) {
      return this.cos(f, t);
   }

   public Complex cos$mcF$sp(final Field f, final Trig t) {
      return this.cos(f, t);
   }

   public Complex cosh$mcD$sp(final Field f, final Trig t) {
      return this.cosh(f, t);
   }

   public Complex cosh$mcF$sp(final Field f, final Trig t) {
      return this.cosh(f, t);
   }

   public Complex tan$mcD$sp(final Field f, final Trig t) {
      return this.tan(f, t);
   }

   public Complex tan$mcF$sp(final Field f, final Trig t) {
      return this.tan(f, t);
   }

   public Complex tanh$mcD$sp(final Field f, final Trig t) {
      return this.tanh(f, t);
   }

   public Complex tanh$mcF$sp(final Field f, final Trig t) {
      return this.tanh(f, t);
   }

   public Quaternion toQuaternion$mcD$sp(final AdditiveMonoid ev) {
      return this.toQuaternion(ev);
   }

   public Quaternion toQuaternion$mcF$sp(final AdditiveMonoid ev) {
      return this.toQuaternion(ev);
   }

   public Complex copy$mDc$sp(final double real, final double imag) {
      return new Complex$mcD$sp(real, imag);
   }

   public Complex copy$mFc$sp(final float real, final float imag) {
      return new Complex$mcF$sp(real, imag);
   }

   public double copy$default$1$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$1());
   }

   public float copy$default$1$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$1());
   }

   public double copy$default$2$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.copy$default$2());
   }

   public float copy$default$2$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.copy$default$2());
   }

   public boolean specInstance$() {
      return false;
   }

   public Complex(final Object real, final Object imag) {
      this.real = real;
      this.imag = imag;
      ScalaNumericAnyConversions.$init$(this);
      Product.$init$(this);
   }
}
