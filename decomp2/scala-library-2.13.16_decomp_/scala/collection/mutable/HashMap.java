package scala.collection.mutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.AbstractIterator;
import scala.collection.Factory;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.MapFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.convert.impl.AnyTableStepper;
import scala.collection.convert.impl.DoubleTableStepper;
import scala.collection.convert.impl.IntTableStepper;
import scala.collection.convert.impl.LongTableStepper;
import scala.collection.generic.DefaultSerializationProxy;
import scala.collection.immutable.BitmapIndexedMapNode;
import scala.collection.immutable.BitmapIndexedSetNode;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.RichInt$;
import scala.runtime.Statics;
import scala.util.hashing.MurmurHash3$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015Ub\u0001\u00021b\u0001!D!\"!\u0010\u0001\u0005\u0003\u0005\u000b\u0011BA \u0011)\t)\u0005\u0001B\u0001B\u0003%\u0011q\t\u0005\b\u0003\u001b\u0002A\u0011AA(\u0011\u001d\ti\u0005\u0001C\u0001\u0003+Ba\"a\u0016\u0001\t\u0003\u0005)\u0011!A!B\u0013\tI\u0006\u0003\u0005\u0003H\u0002\u0001\u000b\u0015BA \u0011!\u0011I\r\u0001Q!\n\u0005}\u0002b\u0002Bf\u0001\u0011\u0005\u0013q\u001b\u0005\t\u0005\u001b\u0004A\u0011A2\u0003P\"q!Q\u001c\u0001\u0005\u0002\u0003\u0015\t\u0011!Q\u0005\n\t}\u0007B\u0004Bt\u0001\u0011\u0005\tQ!A\u0001B\u0013%!\u0011\u001e\u0005\u000f\u0005c\u0004A\u0011!A\u0003\u0002\u0003\u0005K\u0011\u0002Bz\u0011\u001d\u0011I\u0010\u0001C!\u0005wD\u0001B!\u0018\u0001A\u0013%1Q\u0001\u0005\b\u0007\u0017\u0001A\u0011IB\u0007\u0011\u001d\u0019\t\u0002\u0001C!\u0007'Aqa!\b\u0001\t\u0003\u001ay\u0002C\u0004\u00042\u0001!\tea\r\t\u0011\re\u0002\u0001)C\u0005\u0007wA\u0001b!\u000f\u0001A\u0013%1Q\n\u0005\t\u0007s\u0001\u0001\u0015\"\u0003\u0004V!911\r\u0001\u0005\n\r\u0015\u0004\u0002CB2\u0001\u0001&Iaa\u001b\u0007\u0011\rE\u0004\u0001iA\u0005\u0007gBq!!\u0014\u0019\t\u0003\u0019\u0019\t\u0003\u0005\u0004\bb\u0001\u000b\u0015BA \u0011!\u0019I\t\u0007Q!\n\u0005}\u0003\u0002CBF1\u0001\u0006I!a\u0010\t\u0011\r5\u0005\u0004)D\t\u0007\u001fCqa!&\u0019\t\u0003\u00199\nC\u0004\u0003Ra!\ta!'\t\u000f\rm\u0005\u0001\"\u0011\u0004\u001e\"91Q\u0015\u0001\u0005B\r\u001d\u0006bBBV\u0001\u0011\u00053Q\u0016\u0005\t\u0007c\u0003A\u0011A2\u00044\"91q\u0017\u0001\u0005B\re\u0006bBB{\u0001\u0011\u00053q\u001f\u0005\b\t'\u0001A\u0011\tC\u000b\u0011!!\t\u0004\u0001Q\u0005\n\u0011M\u0002\u0002\u0003C\u001d\u0001\u0001&I\u0001b\u000f\t\u0011\u0011\u0005\u0003\u0001)C\u0005\t\u0007Bq\u0001b\u0012\u0001\t\u0003\"I\u0005C\u0004\u0005L\u0001!\t\u0001\"\u0014\t\u000f\u0011E\u0003\u0001\"\u0011\u0005T!9AQ\u0014\u0001\u0005B\u0011}\u0005b\u0002C\\\u0001\u0011\u0005C\u0011\u0018\u0005\b\t\u0007\u0004A\u0011\tCc\u0011\u001d!Y\r\u0001C!\t\u001bDq\u0001\"5\u0001\t\u0003\"\u0019\u000eC\u0004\u0005Z\u0002!\t\u0001b7\t\u000f\u0011}\u0007\u0001\"\u0001\u0005b\"9AQ\u001d\u0001\u0005B\u0005]\u0007b\u0002Ct\u0001\u0011\u00053q\u0013\u0005\b\u0005o\u0002A\u0011\tCu\u0011\u001d\u0011\t\n\u0001C!\tkD\u0001B!/\u0001A\u0013EQ\u0011\u0001\u0005\b\u000b\u0007\u0001A\u0011IC\u0003\u0011!)i\u0001\u0001C\u0001C\u0016=\u0001bBC\u000b\u0001\u0011\u0005Sq\u0003\u0005\t\u000b3\u0001\u0001\u0015\"\u0015\u0006\u001c!9QQ\u0004\u0001\u0005B\u0015}qaBA2C\"\u0005\u0011Q\r\u0004\u0007A\u0006D\t!a\u001a\t\u000f\u00055s\b\"\u0001\u0002v!9\u0011qO \u0005\u0002\u0005e\u0004bBAD\u007f\u0011\u0005\u0011\u0011\u0012\u0005\b\u0003G{D\u0011AAS\u0011\u001d\t\u0019k\u0010C\u0001\u0003wCq!!5@\t\u000b\t\u0019\u000eC\u0004\u0002V~\")!a6\u0007\r\u0005ewHBAn\u0011)\t\tp\u0012BC\u0002\u0013\u0005\u0011q\u001b\u0005\u000b\u0003g<%\u0011!Q\u0001\n\u0005}\u0002BCA#\u000f\n\u0015\r\u0011\"\u0001\u0002T\"Q\u0011Q_$\u0003\u0002\u0003\u0006I!a\u0012\t\u000f\u00055s\t\"\u0001\u0002x\"9!\u0011A$\u0005\u0002\t\r\u0001bBAR\u000f\u0012\u0005!\u0011\u0002\u0004\b\u00053y$a\u0019B\u000e\u0011)\u0011yb\u0014B\u0001B\u0003%!\u0011\u0005\u0005\u000b\u0005Ky%\u0011!Q\u0001\n\u0005}\u0002B\u0003B\u0014\u001f\n\u0005\t\u0015)\u0003\u0003*!Q!QF(\u0003\u0002\u0003\u0006KAa\f\t\u000f\u00055s\n\"\u0001\u00032!9!1H(\u0005\u0002\tu\u0002b\u0002B \u001f\u0012\u0005\u0011q\u001b\u0005\b\u0005+yE\u0011\u0001B!\u0011\u001d\u0011\u0019e\u0014C\u0001\u0005\u000bBqA!\u0015P\t\u0003\u0011\u0019\u0006C\u0004\u0003V=#\tAa\u0016\t\u000f\tus\n\"\u0001\u0003`!9!qO(\u0005\u0002\te\u0004b\u0002BI\u001f\u0012\u0005!1\u0013\u0005\b\u0005K{E\u0011\tBT\u0011%\u0011IlPA\u0001\n\u0013\u0011YLA\u0004ICNDW*\u00199\u000b\u0005\t\u001c\u0017aB7vi\u0006\u0014G.\u001a\u0006\u0003I\u0016\f!bY8mY\u0016\u001cG/[8o\u0015\u00051\u0017!B:dC2\f7\u0001A\u000b\u0004SB\\8c\u0003\u0001k{\u0006\u0015\u0011\u0011DA\u0010\u0003K\u0001Ba\u001b7ou6\t\u0011-\u0003\u0002nC\nY\u0011IY:ue\u0006\u001cG/T1q!\ty\u0007\u000f\u0004\u0001\u0005\u000bE\u0004!\u0019\u0001:\u0003\u0003-\u000b\"a]<\u0011\u0005Q,X\"A3\n\u0005Y,'a\u0002(pi\"Lgn\u001a\t\u0003ibL!!_3\u0003\u0007\u0005s\u0017\u0010\u0005\u0002pw\u0012)A\u0010\u0001b\u0001e\n\ta\u000b\u0005\u0005l}:T\u0018\u0011AA\u0002\u0013\ty\u0018M\u0001\u0004NCB|\u0005o\u001d\t\u0003W\u0002\u0001Ba\u001b\u0001ouBQ\u0011qAA\u0005\u0003\u001b\t\u0019\"a\u0001\u000e\u0003\rL1!a\u0003d\u0005i\u0019FO]5di>\u0003H/[7ju\u0016$\u0017\n^3sC\ndWm\u00149t!\u0015!\u0018q\u00028{\u0013\r\t\t\"\u001a\u0002\u0007)V\u0004H.\u001a\u001a\u0011\u0007-\f)\"C\u0002\u0002\u0018\u0005\u0014\u0001\"\u0013;fe\u0006\u0014G.\u001a\t\u000b\u0003\u000f\tYB\u001c>\u0002\u0002\u0005\r\u0011bAA\u000fG\n)2\u000b\u001e:jGR|\u0005\u000f^5nSj,G-T1q\u001fB\u001c\bCCA\u0004\u0003Cq'0!\u0001\u0002\u0014%\u0019\u00111E2\u0003%5\u000b\u0007OR1di>\u0014\u0018\u0010R3gCVdGo\u001d\t\u0005\u0003O\t9D\u0004\u0003\u0002*\u0005Mb\u0002BA\u0016\u0003ci!!!\f\u000b\u0007\u0005=r-\u0001\u0004=e>|GOP\u0005\u0002M&\u0019\u0011QG3\u0002\u000fA\f7m[1hK&!\u0011\u0011HA\u001e\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\r\t)$Z\u0001\u0010S:LG/[1m\u0007\u0006\u0004\u0018mY5usB\u0019A/!\u0011\n\u0007\u0005\rSMA\u0002J]R\f!\u0002\\8bI\u001a\u000b7\r^8s!\r!\u0018\u0011J\u0005\u0004\u0003\u0017*'A\u0002#pk\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0007\u0003\u0007\t\t&a\u0015\t\u000f\u0005u2\u00011\u0001\u0002@!9\u0011QI\u0002A\u0002\u0005\u001dCCAA\u0002\u0003\u001d\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013ICNDW*\u00199%IQ\f'\r\\3\u0011\u000bQ\fY&a\u0018\n\u0007\u0005uSMA\u0003BeJ\f\u0017\u0010E\u0003\u0002b=s'P\u0004\u0002l}\u00059\u0001*Y:i\u001b\u0006\u0004\bCA6@'\u0015y\u0014\u0011NA8!\r!\u00181N\u0005\u0004\u0003[*'AB!osJ+g\r\u0005\u0004\u0002\b\u0005E\u0014\u0011A\u0005\u0004\u0003g\u001a'AC'ba\u001a\u000b7\r^8ssR\u0011\u0011QM\u0001\u0006K6\u0004H/_\u000b\u0007\u0003w\n\t)!\"\u0016\u0005\u0005u\u0004CB6\u0001\u0003\u007f\n\u0019\tE\u0002p\u0003\u0003#Q!]!C\u0002I\u00042a\\AC\t\u0015a\u0018I1\u0001s\u0003\u00111'o\\7\u0016\r\u0005-\u0015\u0011SAK)\u0011\ti)a&\u0011\r-\u0004\u0011qRAJ!\ry\u0017\u0011\u0013\u0003\u0006c\n\u0013\rA\u001d\t\u0004_\u0006UE!\u0002?C\u0005\u0004\u0011\bbBAM\u0005\u0002\u0007\u00111T\u0001\u0003SR\u0004b!a\u0002\u0002\u001e\u0006\u0005\u0016bAAPG\na\u0011\n^3sC\ndWm\u00148dKB9A/a\u0004\u0002\u0010\u0006M\u0015A\u00038fo\n+\u0018\u000e\u001c3feV1\u0011qUAZ\u0003o+\"!!+\u0011\u000f-\fY+a,\u0002:&\u0019\u0011QV1\u0003\u000f\t+\u0018\u000e\u001c3feB9A/a\u0004\u00022\u0006U\u0006cA8\u00024\u0012)\u0011o\u0011b\u0001eB\u0019q.a.\u0005\u000bq\u001c%\u0019\u0001:\u0011\r-\u0004\u0011\u0011WA[+\u0019\ti,!2\u0002JR1\u0011qXAg\u0003\u001f\u0004ra[AV\u0003\u0003\fY\rE\u0004u\u0003\u001f\t\u0019-a2\u0011\u0007=\f)\rB\u0003r\t\n\u0007!\u000fE\u0002p\u0003\u0013$Q\u0001 #C\u0002I\u0004ba\u001b\u0001\u0002D\u0006\u001d\u0007bBA\u001f\t\u0002\u0007\u0011q\b\u0005\b\u0003\u000b\"\u0005\u0019AA$\u0003E!WMZ1vYRdu.\u00193GC\u000e$xN]\u000b\u0003\u0003\u000f\na\u0003Z3gCVdG/\u00138ji&\fGnQ1qC\u000eLG/_\u000b\u0003\u0003\u007f\u0011a\u0003R3tKJL\u0017\r\\5{CRLwN\u001c$bGR|'/_\u000b\u0007\u0003;\fI/!<\u0014\u000f\u001d\u000bI'a8\u0002&AA\u0011qAAq\u0003K\fy/C\u0002\u0002d\u000e\u0014qAR1di>\u0014\u0018\u0010E\u0004u\u0003\u001f\t9/a;\u0011\u0007=\fI\u000fB\u0003r\u000f\n\u0007!\u000fE\u0002p\u0003[$Q\u0001`$C\u0002I\u0004ba\u001b\u0001\u0002h\u0006-\u0018a\u0003;bE2,G*\u001a8hi\"\fA\u0002^1cY\u0016dUM\\4uQ\u0002\n1\u0002\\8bI\u001a\u000b7\r^8sAQ1\u0011\u0011`A\u007f\u0003\u007f\u0004r!a?H\u0003O\fY/D\u0001@\u0011\u001d\t\t\u0010\u0014a\u0001\u0003\u007fAq!!\u0012M\u0001\u0004\t9%\u0001\u0007ge>l7\u000b]3dS\u001aL7\r\u0006\u0003\u0002p\n\u0015\u0001bBAM\u001b\u0002\u0007!q\u0001\t\u0007\u0003\u000f\ti*!:\u0016\u0005\t-\u0001cB6\u0002,\u0006\u0015\u0018q\u001e\u0015\b\u000f\n=!Q\u0003B\f!\r!(\u0011C\u0005\u0004\u0005')'\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\u0019!\u0001\u0002(pI\u0016,bA!\b\u0003$\t-2cA(\u0002j\u0005!ql[3z!\ry'1\u0005\u0003\u0006c>\u0013\rA]\u0001\u0006?\"\f7\u000f[\u0001\u0007?Z\fG.^3\u0011\u0007=\u0014Y\u0003B\u0003}\u001f\n\u0007!/A\u0003`]\u0016DH\u000fE\u0004\u0002|>\u0013\tC!\u000b\u0015\u0015\t=\"1\u0007B\u001b\u0005o\u0011I\u0004C\u0004\u0003 Q\u0003\rA!\t\t\u000f\t\u0015B\u000b1\u0001\u0002@!9!q\u0005+A\u0002\t%\u0002b\u0002B\u0017)\u0002\u0007!qF\u0001\u0004W\u0016LXC\u0001B\u0011\u0003\u0011A\u0017m\u001d5\u0016\u0005\t%\u0012!\u0003<bYV,w\fJ3r)\u0011\u00119E!\u0014\u0011\u0007Q\u0014I%C\u0002\u0003L\u0015\u0014A!\u00168ji\"9!q\n-A\u0002\t%\u0012!\u0001<\u0002\t9,\u0007\u0010^\u000b\u0003\u0005_\t\u0001B\\3yi~#S-\u001d\u000b\u0005\u0005\u000f\u0012I\u0006C\u0004\u0003\\i\u0003\rAa\f\u0002\u00039\f\u0001BZ5oI:{G-\u001a\u000b\u0007\u0005_\u0011\tG!\u001a\t\u000f\t\r4\f1\u0001\u0003\"\u0005\t1\u000eC\u0004\u0003hm\u0003\r!a\u0010\u0002\u0003!D3a\u0017B6!\u0011\u0011iGa\u001d\u000e\u0005\t=$b\u0001B9K\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\tU$q\u000e\u0002\bi\u0006LGN]3d\u0003\u001d1wN]3bG\",BAa\u001f\u0003\fR!!q\tB?\u0011\u001d\u0011y\b\u0018a\u0001\u0005\u0003\u000b\u0011A\u001a\t\bi\n\r%q\u0011BE\u0013\r\u0011))\u001a\u0002\n\rVt7\r^5p]F\u0002r\u0001^A\b\u0005C\u0011I\u0003E\u0002p\u0005\u0017#aA!$]\u0005\u0004\u0011(!A+)\u0007q\u0013Y'\u0001\u0007g_J,\u0017m\u00195F]R\u0014\u00180\u0006\u0003\u0003\u0016\n\u0005F\u0003\u0002B$\u0005/CqAa ^\u0001\u0004\u0011I\nE\u0005u\u00057\u0013\tC!\u000b\u0003 &\u0019!QT3\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004cA8\u0003\"\u00121!QR/C\u0002ID3!\u0018B6\u0003!!xn\u0015;sS:<GC\u0001BU!\u0011\u0011YK!.\u000e\u0005\t5&\u0002\u0002BX\u0005c\u000bA\u0001\\1oO*\u0011!1W\u0001\u0005U\u00064\u0018-\u0003\u0003\u00038\n5&AB*ue&tw-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0003>B!!1\u0016B`\u0013\u0011\u0011\tM!,\u0003\r=\u0013'.Z2uQ\u001dy$q\u0002B\u000b\u0005/AsA\u0010B\b\u0005+\u00119\"A\u0005uQJ,7\u000f[8mI\u0006Y1m\u001c8uK:$8+\u001b>f\u0003\u0011\u0019\u0018N_3\u0002\u001bUt\u0017.\u001c9s_Z,\u0007*Y:i)\u0011\tyD!5\t\u000f\tM\u0017\u00021\u0001\u0002@\u0005a\u0011.\u001c9s_Z,G\rS1tQ\"\u001a\u0011Ba6\u0011\u0007Q\u0014I.C\u0002\u0003\\\u0016\u0014a!\u001b8mS:,\u0017!L:dC2\fGeY8mY\u0016\u001cG/[8oI5,H/\u00192mK\u0012B\u0015m\u001d5NCB$C%[7qe>4X\rS1tQR!\u0011q\bBq\u0011\u001d\u0011\u0019O\u0003a\u0001\u0003\u007f\tAb\u001c:jO&t\u0017\r\u001c%bg\"D3A\u0003Bl\u00035\u001a8-\u00197bI\r|G\u000e\\3di&|g\u000eJ7vi\u0006\u0014G.\u001a\u0013ICNDW*\u00199%I\r|W\u000e];uK\"\u000b7\u000f\u001b\u000b\u0005\u0003\u007f\u0011Y\u000f\u0003\u0004\u0003n.\u0001\rA\\\u0001\u0002_\"\u001a1Ba6\u0002OM\u001c\u0017\r\\1%G>dG.Z2uS>tG%\\;uC\ndW\r\n%bg\"l\u0015\r\u001d\u0013%S:$W\r\u001f\u000b\u0005\u0003\u007f\u0011)\u0010C\u0004\u0003@1\u0001\r!a\u0010)\u00071\u00119.\u0001\u0005d_:$\u0018-\u001b8t)\u0011\u0011ipa\u0001\u0011\u0007Q\u0014y0C\u0002\u0004\u0002\u0015\u0014qAQ8pY\u0016\fg\u000e\u0003\u0004\u0003<5\u0001\rA\u001c\u000b\u0005\u0003?\u001a9\u0001\u0003\u0004\u0003<9\u0001\rA\u001c\u0015\u0004\u001d\t]\u0017\u0001C:ju\u0016D\u0015N\u001c;\u0015\t\t\u001d3q\u0002\u0005\b\u0005\u0017|\u0001\u0019AA \u0003\u0019\tG\rZ!mYR!1QCB\f\u001b\u0005\u0001\u0001bBB\r!\u0001\u000711D\u0001\u0003qN\u0004b!a\u0002\u0002\u001e\u00065\u0011AC;qI\u0006$XmV5uQR!1\u0011EB\u0018)\u0011\u0019\u0019c!\u000b\u0011\tQ\u001c)C_\u0005\u0004\u0007O)'AB(qi&|g\u000eC\u0004\u0004,E\u0001\ra!\f\u0002#I,W.\u00199qS:<g)\u001e8di&|g\u000eE\u0004u\u0005\u0007\u001b\u0019ca\t\t\r\tm\u0012\u00031\u0001o\u0003-\u0019XO\u0019;sC\u000e$\u0018\t\u001c7\u0015\t\rU1Q\u0007\u0005\b\u00073\u0011\u0002\u0019AB\u001c!\u0015\t9!!(o\u0003\u0011\u0001X\u000f\u001e\u0019\u0015\u0015\ru21IB#\u0007\u000f\u001aI\u0005\u0005\u0003u\u0007\u007fQ\u0018bAB!K\n!1k\\7f\u0011\u0019\u0011Yd\u0005a\u0001]\"1!QC\nA\u0002iDqAa\u0010\u0014\u0001\u0004\ty\u0004C\u0004\u0004LM\u0001\rA!@\u0002\r\u001d,Go\u00147e)!\u0019ida\u0014\u0004R\rM\u0003B\u0002B\u001e)\u0001\u0007a\u000e\u0003\u0004\u0003\u0016Q\u0001\rA\u001f\u0005\b\u0007\u0017\"\u0002\u0019\u0001B\u007f)1\u0019ida\u0016\u0004Z\rm3QLB0\u0011\u0019\u0011Y$\u0006a\u0001]\"1!QC\u000bA\u0002iDqaa\u0013\u0016\u0001\u0004\u0011i\u0010C\u0004\u0003@U\u0001\r!a\u0010\t\u000f\r\u0005T\u00031\u0001\u0002@\u0005\u0019\u0011\u000e\u001a=\u0002\u000fI,Wn\u001c<faQ!\u0011qLB4\u0011\u0019\u0019IG\u0006a\u0001]\u0006!Q\r\\3n)\u0019\tyf!\u001c\u0004p!11\u0011N\fA\u00029DqAa\u0010\u0018\u0001\u0004\tyDA\bICNDW*\u00199Ji\u0016\u0014\u0018\r^8s+\u0011\u0019)ha \u0014\u0007a\u00199\b\u0005\u0004\u0002\b\re4QP\u0005\u0004\u0007w\u001a'\u0001E!cgR\u0014\u0018m\u0019;Ji\u0016\u0014\u0018\r^8s!\ry7q\u0010\u0003\u0007\u0007\u0003C\"\u0019\u0001:\u0003\u0003\u0005#\"a!\"\u0011\u000b\rU\u0001d! \u0002\u0003%\fAA\\8eK\u0006\u0019A.\u001a8\u0002\u000f\u0015DHO]1diR!1QPBI\u0011\u001d\u0019\u0019*\ba\u0001\u0003?\n!A\u001c3\u0002\u000f!\f7OT3yiV\u0011!Q \u000b\u0003\u0007{\n\u0001\"\u001b;fe\u0006$xN]\u000b\u0003\u0007?\u0003b!a\u0002\u0004\"\u00065\u0011bABRG\nA\u0011\n^3sCR|'/\u0001\u0007lKf\u001c\u0018\n^3sCR|'/\u0006\u0002\u0004*B)\u0011qABQ]\u0006qa/\u00197vKNLE/\u001a:bi>\u0014XCABX!\u0015\t9a!){\u00031qw\u000eZ3Ji\u0016\u0014\u0018\r^8s+\t\u0019)\f\u0005\u0004\u0002\b\r\u0005\u0016qL\u0001\bgR,\u0007\u000f]3s+\u0011\u0019Yl!2\u0015\t\ru61\u001e\n\u0007\u0007\u007f\u001b\u0019m!7\u0007\r\r\u0005\u0007\u0001AB_\u00051a$/\u001a4j]\u0016lWM\u001c;?!\ry7Q\u0019\u0003\b\u0007\u000f$#\u0019ABe\u0005\u0005\u0019\u0016cA:\u0004LB\"1QZBk!\u0019\t9aa4\u0004T&\u00191\u0011[2\u0003\u000fM#X\r\u001d9feB\u0019qn!6\u0005\u0017\r]7QYA\u0001\u0002\u0003\u0015\tA\u001d\u0002\u0004?\u0012\u001a\u0004\u0003BBn\u0007KtAa!8\u0004b:!\u0011\u0011FBp\u0013\t!W-C\u0002\u0004d\u000e\fqa\u0015;faB,'/\u0003\u0003\u0004h\u000e%(AD#gM&\u001c\u0017.\u001a8u'Bd\u0017\u000e\u001e\u0006\u0004\u0007G\u001c\u0007bBBwI\u0001\u000f1q^\u0001\u0006g\"\f\u0007/\u001a\t\t\u0003\u000f\u0019\t0!\u0004\u0004D&\u001911_2\u0003\u0019M#X\r\u001d9feNC\u0017\r]3\u0002\u0015-,\u0017p\u0015;faB,'/\u0006\u0003\u0004z\u0012\u0005A\u0003BB~\t\u001f\u0011ba!@\u0004\u0000\u000eegABBa\u0001\u0001\u0019Y\u0010E\u0002p\t\u0003!qaa2&\u0005\u0004!\u0019!E\u0002t\t\u000b\u0001D\u0001b\u0002\u0005\fA1\u0011qABh\t\u0013\u00012a\u001cC\u0006\t-!i\u0001\"\u0001\u0002\u0002\u0003\u0005)\u0011\u0001:\u0003\u0007}#C\u0007C\u0004\u0004n\u0016\u0002\u001d\u0001\"\u0005\u0011\u000f\u0005\u001d1\u0011\u001f8\u0004\u0000\u0006aa/\u00197vKN#X\r\u001d9feV!Aq\u0003C\u0010)\u0011!I\u0002\"\f\u0013\r\u0011mAQDBm\r\u0019\u0019\t\r\u0001\u0001\u0005\u001aA\u0019q\u000eb\b\u0005\u000f\r\u001dgE1\u0001\u0005\"E\u00191\u000fb\t1\t\u0011\u0015B\u0011\u0006\t\u0007\u0003\u000f\u0019y\rb\n\u0011\u0007=$I\u0003B\u0006\u0005,\u0011}\u0011\u0011!A\u0001\u0006\u0003\u0011(aA0%k!91Q\u001e\u0014A\u0004\u0011=\u0002cBA\u0004\u0007cTHQD\u0001\nOJ|w\u000fV1cY\u0016$BAa\u0012\u00056!9AqG\u0014A\u0002\u0005}\u0012A\u00028fo2,g.\u0001\u0007uC\ndWmU5{K\u001a{'\u000f\u0006\u0003\u0002@\u0011u\u0002b\u0002C Q\u0001\u0007\u0011qH\u0001\tG\u0006\u0004\u0018mY5us\u0006aa.Z<UQJ,7\u000f[8mIR!\u0011q\bC#\u0011\u001d\u0011Y-\u000ba\u0001\u0003\u007f\tQa\u00197fCJ$\"Aa\u0012\u0002\u0007\u001d,G\u000f\u0006\u0003\u0004$\u0011=\u0003B\u0002B\u001eW\u0001\u0007a.A\u0003baBd\u0017\u0010F\u0002{\t+BaAa\u000f-\u0001\u0004q\u0007&\u0002\u0017\u0005Z\u0011\u0015\u0004#\u0002;\u0005\\\u0011}\u0013b\u0001C/K\n1A\u000f\u001b:poN\u0004B!a\n\u0005b%!A1MA\u001e\u0005YqunU;dQ\u0016cW-\\3oi\u0016C8-\u001a9uS>t\u0017g\u0002\u0010\u0005h\u0011UD1\u0014\t\u0005\tS\"\tH\u0004\u0003\u0005l\u00115\u0004cAA\u0016K&\u0019AqN3\u0002\rA\u0013X\rZ3g\u0013\u0011\u00119\fb\u001d\u000b\u0007\u0011=T-M\u0005$\to\"y\b\"%\u0005\u0002V!A\u0011\u0010C>+\t!9\u0007B\u0004\u0005~\u001d\u0014\r\u0001b\"\u0003\u0003QKA\u0001\"!\u0005\u0004\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIER1\u0001\"\"f\u0003\u0019!\bN]8xgF\u00191\u000f\"#\u0011\t\u0011-EQ\u0012\b\u0004i\u0006M\u0012\u0002\u0002CH\u0003w\u0011\u0011\u0002\u00165s_^\f'\r\\32\u0013\r\"\u0019\n\"&\u0005\u0018\u0012\u0015eb\u0001;\u0005\u0016&\u0019AQQ32\u000b\t\"X\r\"'\u0003\u000bM\u001c\u0017\r\\12\u0007\u0019\"y&A\u0005hKR|%/\u00127tKV!A\u0011\u0015CS)\u0019!\u0019\u000bb+\u0005.B\u0019q\u000e\"*\u0005\u000f\u0011\u001dVF1\u0001\u0005*\n\u0011a+M\t\u0003u^DaAa\u000f.\u0001\u0004q\u0007\u0002\u0003CX[\u0011\u0005\r\u0001\"-\u0002\u000f\u0011,g-Y;miB)A\u000fb-\u0005$&\u0019AQW3\u0003\u0011q\u0012\u0017P\\1nKz\nqbZ3u\u001fJ,En]3Va\u0012\fG/\u001a\u000b\u0006u\u0012mFQ\u0018\u0005\u0007\u0005wq\u0003\u0019\u00018\t\u0011\u0011}f\u0006\"a\u0001\t\u0003\fA\u0002Z3gCVdGOV1mk\u0016\u0004B\u0001\u001eCZu\u0006\u0019\u0001/\u001e;\u0015\r\r\rBq\u0019Ce\u0011\u0019\u0011Yd\fa\u0001]\"1!QC\u0018A\u0002i\faA]3n_Z,G\u0003BB\u0012\t\u001fDaAa\u000f1\u0001\u0004q\u0017AB;qI\u0006$X\r\u0006\u0004\u0003H\u0011UGq\u001b\u0005\u0007\u0005w\t\u0004\u0019\u00018\t\r\tU\u0011\u00071\u0001{\u0003\u0019\tG\rZ(oKR!1Q\u0003Co\u0011\u001d\u0019IG\ra\u0001\u0003\u001b\t1b];ciJ\f7\r^(oKR!1Q\u0003Cr\u0011\u0019\u0019Ig\ra\u0001]\u0006I1N\\8x]NK'0Z\u0001\bSN,U\u000e\u001d;z+\u0011!Y\u000fb=\u0015\t\t\u001dCQ\u001e\u0005\b\u0005\u007f2\u0004\u0019\u0001Cx!\u001d!(1QA\u0007\tc\u00042a\u001cCz\t\u0019\u0011iI\u000eb\u0001eV!Aq\u001fC\u0000)\u0011\u00119\u0005\"?\t\u000f\t}t\u00071\u0001\u0005|B9AOa'ou\u0012u\bcA8\u0005\u0000\u00121!QR\u001cC\u0002I$\"!!\u001b\u0002\u001b\u0019LG\u000e^3s\u0013:\u0004F.Y2f)\u0011\u0019)\"b\u0002\t\u000f\u0015%\u0011\b1\u0001\u0006\f\u0005\t\u0001\u000fE\u0004u\u00057s'P!@\u0002)5\f\u0007OV1mk\u0016\u001c\u0018J\u001c)mC\u000e,\u0017*\u001c9m)\u0011\u0019)\"\"\u0005\t\u000f\t}$\b1\u0001\u0006\u0014A1AOa'ouj\f!\"\\1q\r\u0006\u001cGo\u001c:z+\t\ty'\u0001\u0007tiJLgn\u001a)sK\u001aL\u00070\u0006\u0002\u0003*\u0006A\u0001.Y:i\u0007>$W\r\u0006\u0002\u0002@!Z\u0001!b\t\u0006*\u0015-RqFC\u0019!\r!XQE\u0005\u0004\u000bO)'!\u00063faJ,7-\u0019;fI&s\u0007.\u001a:ji\u0006t7-Z\u0001\b[\u0016\u001c8/Y4fC\t)i#A1ICNDW*\u00199!o&dG\u000e\t2fA5\fG-\u001a\u0011gS:\fGn\u000f\u0011vg\u0016\u0004cf^5uQ\u0012+g-Y;mi\u00022wN\u001d\u0011uQ\u0016\u00043m\\7n_:\u0004So]3!G\u0006\u001cX\rI8gA\r|W\u000e];uS:<\u0007%\u0019\u0011eK\u001a\fW\u000f\u001c;!m\u0006dW/Z\u0001\u0006g&t7-Z\u0011\u0003\u000bg\taA\r\u00182g9\u0002\u0004"
)
public class HashMap extends AbstractMap implements StrictOptimizedMapOps, Serializable {
   private final double loadFactor;
   public Node[] scala$collection$mutable$HashMap$$table;
   private int threshold;
   private int contentSize;

   public static int defaultInitialCapacity() {
      HashMap$ var10000 = HashMap$.MODULE$;
      return 16;
   }

   public static double defaultLoadFactor() {
      HashMap$ var10000 = HashMap$.MODULE$;
      return (double)0.75F;
   }

   public static Builder newBuilder(final int initialCapacity, final double loadFactor) {
      HashMap$ var10000 = HashMap$.MODULE$;
      return new GrowableBuilder(initialCapacity, loadFactor) {
         public void sizeHint(final int size) {
            ((HashMap)this.elems()).sizeHint(size);
         }
      };
   }

   public static Builder newBuilder() {
      return HashMap$.MODULE$.newBuilder();
   }

   public static HashMap from(final IterableOnce it) {
      return HashMap$.MODULE$.from(it);
   }

   public IterableOps map(final Function1 f) {
      return StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps concat(final IterableOnce suffix) {
      return StrictOptimizedMapOps.concat$(this, suffix);
   }

   public IterableOps collect(final PartialFunction pf) {
      return StrictOptimizedMapOps.collect$(this, pf);
   }

   /** @deprecated */
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final scala.collection.immutable.Seq elems) {
      return StrictOptimizedMapOps.$plus$(this, elem1, elem2, elems);
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

   public int size() {
      return this.contentSize;
   }

   public int unimproveHash(final int improvedHash) {
      return improvedHash ^ improvedHash >>> 16;
   }

   public int scala$collection$mutable$HashMap$$improveHash(final int originalHash) {
      return originalHash ^ originalHash >>> 16;
   }

   public int scala$collection$mutable$HashMap$$computeHash(final Object o) {
      int scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(o);
      return scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
   }

   public int scala$collection$mutable$HashMap$$index(final int hash) {
      return hash & this.scala$collection$mutable$HashMap$$table.length - 1;
   }

   public boolean contains(final Object key) {
      int findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
      int findNode_hash = findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
      Node var3 = this.scala$collection$mutable$HashMap$$table[findNode_hash & this.scala$collection$mutable$HashMap$$table.length - 1];
      Node var10000 = var3 == null ? null : var3.findNode(key, findNode_hash);
      Object var5 = null;
      return var10000 != null;
   }

   private Node findNode(final Object key) {
      int scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
      int hash = scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
      Node var3 = this.scala$collection$mutable$HashMap$$table[hash & this.scala$collection$mutable$HashMap$$table.length - 1];
      return var3 == null ? null : var3.findNode(key, hash);
   }

   public void sizeHint(final int size) {
      int target = this.tableSizeFor((int)((double)(size + 1) / this.loadFactor));
      if (target > this.scala$collection$mutable$HashMap$$table.length) {
         this.growTable(target);
      }
   }

   public HashMap addAll(final IterableOnce xs) {
      int sizeHint_delta = 0;
      Builder.sizeHint$(this, xs, sizeHint_delta);
      if (xs instanceof scala.collection.immutable.HashMap) {
         scala.collection.immutable.HashMap var2 = (scala.collection.immutable.HashMap)xs;
         Function3 foreachWithHash_f = (k, v, h) -> {
            $anonfun$addAll$1(this, k, v, BoxesRunTime.unboxToInt(h));
            return BoxedUnit.UNIT;
         };
         if (var2 == null) {
            throw null;
         } else {
            BitmapIndexedMapNode var10000 = var2.rootNode();
            if (var10000 == null) {
               throw null;
            } else {
               BitmapIndexedMapNode foreachWithHash_foreachWithHash_this = var10000;
               int foreachWithHash_foreachWithHash_i = 0;

               for(int foreachWithHash_foreachWithHash_iN = foreachWithHash_foreachWithHash_this.payloadArity(); foreachWithHash_foreachWithHash_i < foreachWithHash_foreachWithHash_iN; ++foreachWithHash_foreachWithHash_i) {
                  var10000 = (BitmapIndexedMapNode)foreachWithHash_foreachWithHash_this.content()[2 * foreachWithHash_foreachWithHash_i];
                  Object var10001 = foreachWithHash_foreachWithHash_this.content()[2 * foreachWithHash_foreachWithHash_i + 1];
                  int foreachWithHash_foreachWithHash_boxToInteger_i = foreachWithHash_foreachWithHash_this.originalHashes()[foreachWithHash_foreachWithHash_i];
                  Object var16 = var10001;
                  Object var15 = var10000;
                  $anonfun$addAll$1(this, var15, var16, foreachWithHash_foreachWithHash_boxToInteger_i);
               }

               int foreachWithHash_foreachWithHash_jN = foreachWithHash_foreachWithHash_this.nodeArity();

               for(int foreachWithHash_foreachWithHash_j = 0; foreachWithHash_foreachWithHash_j < foreachWithHash_foreachWithHash_jN; ++foreachWithHash_foreachWithHash_j) {
                  foreachWithHash_foreachWithHash_this.getNode(foreachWithHash_foreachWithHash_j).foreachWithHash(foreachWithHash_f);
               }

               return this;
            }
         }
      } else if (xs instanceof HashMap) {
         Iterator iter = ((HashMap)xs).nodeIterator();

         while(iter.hasNext()) {
            Node next = (Node)iter.next();
            this.put0(next.key(), next.value(), next.hash(), false);
         }

         return this;
      } else if (!(xs instanceof LinkedHashMap)) {
         if (xs instanceof Map) {
            ((Map)xs).foreachEntry((key, value) -> {
               int scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
               return this.put0(key, value, scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16, false);
            });
            return this;
         } else {
            return (HashMap)Growable.addAll$(this, xs);
         }
      } else {
         Iterator iter = ((LinkedHashMap)xs).entryIterator();

         while(iter.hasNext()) {
            LinkedHashMap.LinkedEntry entry = (LinkedHashMap.LinkedEntry)iter.next();
            this.put0(entry.key(), entry.value(), entry.hash(), false);
         }

         return this;
      }
   }

   public Option updateWith(final Object key, final Function1 remappingFunction) {
      Class var10000 = this.getClass();
      Class var3 = HashMap.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            int scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
            int hash = scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
            int indexedHash = hash & this.scala$collection$mutable$HashMap$$table.length - 1;
            Node var23 = null;
            Node var24 = null;
            Node var6 = this.scala$collection$mutable$HashMap$$table[indexedHash];
            if (var6 != null) {
               Node findNode$1_nd = var6;
               Node findNode$1_prev = null;

               while(true) {
                  if (hash == findNode$1_nd.hash() && BoxesRunTime.equals(key, findNode$1_nd.key())) {
                     var24 = findNode$1_prev;
                     var23 = findNode$1_nd;
                     break;
                  }

                  if (findNode$1_nd.next() == null || findNode$1_nd.hash() > hash) {
                     break;
                  }

                  Node var27 = findNode$1_nd;
                  findNode$1_nd = findNode$1_nd.next();
                  findNode$1_prev = var27;
               }

               findNode$1_prev = null;
               Object var26 = null;
            }

            Node var8 = var23;
            Option previousValue = (Option)(var8 == null ? None$.MODULE$ : new Some(var8.value()));
            Option nextValue = (Option)remappingFunction.apply(previousValue);
            Tuple2 var10 = new Tuple2(previousValue, nextValue);
            Option var11 = previousValue;
            if (!None$.MODULE$.equals(var11) || !None$.MODULE$.equals(nextValue)) {
               if ((Option)previousValue instanceof Some && None$.MODULE$.equals(nextValue)) {
                  if ((Node)var24 != null) {
                     ((Node)var24).next_$eq(((Node)var23).next());
                  } else {
                     this.scala$collection$mutable$HashMap$$table[indexedHash] = ((Node)var23).next();
                  }

                  --this.contentSize;
               } else {
                  Option var12 = previousValue;
                  if (None$.MODULE$.equals(var12) && nextValue instanceof Some) {
                     Object value = ((Some)nextValue).value();
                     int var28;
                     if (this.contentSize + 1 >= this.threshold) {
                        this.growTable(this.scala$collection$mutable$HashMap$$table.length * 2);
                        var28 = hash & this.scala$collection$mutable$HashMap$$table.length - 1;
                     } else {
                        var28 = indexedHash;
                     }

                     int newIndexedHash = var28;
                     this.put0(key, value, false, hash, newIndexedHash);
                  } else {
                     if (!((Option)previousValue instanceof Some) || !(nextValue instanceof Some)) {
                        throw new MatchError(var10);
                     }

                     Object newValue = ((Some)nextValue).value();
                     ((Node)var23).value_$eq(newValue);
                  }
               }
            }

            return nextValue;
         }
      }

      Option updateWith_previousValue = this.get(key);
      Option updateWith_nextValue = (Option)remappingFunction.apply(updateWith_previousValue);
      Tuple2 var21 = new Tuple2(updateWith_previousValue, updateWith_nextValue);
      if (!None$.MODULE$.equals(updateWith_previousValue) || !None$.MODULE$.equals(updateWith_nextValue)) {
         if (updateWith_previousValue instanceof Some && None$.MODULE$.equals(updateWith_nextValue)) {
            this.remove(key);
         } else {
            if (!(updateWith_nextValue instanceof Some)) {
               throw new MatchError(var21);
            }

            Object updateWith_v = ((Some)updateWith_nextValue).value();
            this.update(key, updateWith_v);
         }
      }

      return updateWith_nextValue;
   }

   public HashMap subtractAll(final IterableOnce xs) {
      if (this.size() == 0) {
         return this;
      } else if (!(xs instanceof scala.collection.immutable.HashSet)) {
         if (xs instanceof HashSet) {
            HashSet var3 = (HashSet)xs;
            if (var3 == null) {
               throw null;
            } else {
               Iterator iter = var3.new HashSetIterator() {
                  public Node extract(final Node nd) {
                     return nd;
                  }
               };

               while(iter.hasNext()) {
                  HashSet.Node next = (HashSet.Node)iter.next();
                  this.remove0(next.key(), next.hash());
                  if (this.size() == 0) {
                     return this;
                  }
               }

               return this;
            }
         } else if (xs instanceof LinkedHashSet) {
            Iterator iter = ((LinkedHashSet)xs).entryIterator();

            while(iter.hasNext()) {
               LinkedHashSet.Entry next = (LinkedHashSet.Entry)iter.next();
               this.remove0(next.key(), next.hash());
               if (this.size() == 0) {
                  return this;
               }
            }

            return this;
         } else {
            return (HashMap)Shrinkable.subtractAll$(this, xs);
         }
      } else {
         scala.collection.immutable.HashSet var2 = (scala.collection.immutable.HashSet)xs;
         Function2 foreachWithHashWhile_f = (k, h) -> BoxesRunTime.boxToBoolean($anonfun$subtractAll$1(this, k, BoxesRunTime.unboxToInt(h)));
         if (var2 == null) {
            throw null;
         } else {
            BitmapIndexedSetNode var10000 = var2.rootNode();
            if (var10000 == null) {
               throw null;
            } else {
               BitmapIndexedSetNode foreachWithHashWhile_foreachWithHashWhile_this = var10000;
               int foreachWithHashWhile_foreachWithHashWhile_thisPayloadArity = foreachWithHashWhile_foreachWithHashWhile_this.payloadArity();
               boolean foreachWithHashWhile_foreachWithHashWhile_pass = true;

               for(int foreachWithHashWhile_foreachWithHashWhile_i = 0; foreachWithHashWhile_foreachWithHashWhile_i < foreachWithHashWhile_foreachWithHashWhile_thisPayloadArity && foreachWithHashWhile_foreachWithHashWhile_pass; ++foreachWithHashWhile_foreachWithHashWhile_i) {
                  label87: {
                     if (foreachWithHashWhile_foreachWithHashWhile_pass) {
                        var10000 = (BitmapIndexedSetNode)foreachWithHashWhile_foreachWithHashWhile_this.content()[foreachWithHashWhile_foreachWithHashWhile_i];
                        int foreachWithHashWhile_foreachWithHashWhile_boxToInteger_i = foreachWithHashWhile_foreachWithHashWhile_this.originalHashes()[foreachWithHashWhile_foreachWithHashWhile_i];
                        Object var16 = var10000;
                        if ($anonfun$subtractAll$1(this, var16, foreachWithHashWhile_foreachWithHashWhile_boxToInteger_i)) {
                           var18 = true;
                           break label87;
                        }
                     }

                     var18 = false;
                  }

                  foreachWithHashWhile_foreachWithHashWhile_pass = var18;
               }

               int foreachWithHashWhile_foreachWithHashWhile_thisNodeArity = foreachWithHashWhile_foreachWithHashWhile_this.nodeArity();

               for(int foreachWithHashWhile_foreachWithHashWhile_j = 0; foreachWithHashWhile_foreachWithHashWhile_j < foreachWithHashWhile_foreachWithHashWhile_thisNodeArity && foreachWithHashWhile_foreachWithHashWhile_pass; ++foreachWithHashWhile_foreachWithHashWhile_j) {
                  foreachWithHashWhile_foreachWithHashWhile_pass = foreachWithHashWhile_foreachWithHashWhile_pass && foreachWithHashWhile_foreachWithHashWhile_this.getNode(foreachWithHashWhile_foreachWithHashWhile_j).foreachWithHashWhile(foreachWithHashWhile_f);
               }

               return this;
            }
         }
      }
   }

   private Some put0(final Object key, final Object value, final int hash, final boolean getOld) {
      if (this.contentSize + 1 >= this.threshold) {
         this.growTable(this.scala$collection$mutable$HashMap$$table.length * 2);
      }

      int idx = hash & this.scala$collection$mutable$HashMap$$table.length - 1;
      return this.put0(key, value, getOld, hash, idx);
   }

   private Some put0(final Object key, final Object value, final boolean getOld) {
      if (this.contentSize + 1 >= this.threshold) {
         this.growTable(this.scala$collection$mutable$HashMap$$table.length * 2);
      }

      int scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
      int hash = scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
      int idx = hash & this.scala$collection$mutable$HashMap$$table.length - 1;
      return this.put0(key, value, getOld, hash, idx);
   }

   private Some put0(final Object key, final Object value, final boolean getOld, final int hash, final int idx) {
      Node var6 = this.scala$collection$mutable$HashMap$$table[idx];
      if (var6 == null) {
         this.scala$collection$mutable$HashMap$$table[idx] = new Node(key, hash, value, (Node)null);
      } else {
         Node prev = null;

         for(Node n = var6; n != null && n.hash() <= hash; n = n.next()) {
            if (n.hash() == hash && BoxesRunTime.equals(key, n.key())) {
               Object old = n.value();
               n.value_$eq(value);
               if (getOld) {
                  return new Some(old);
               }

               return null;
            }

            prev = n;
         }

         if (prev == null) {
            this.scala$collection$mutable$HashMap$$table[idx] = new Node(key, hash, value, var6);
         } else {
            prev.next_$eq(new Node(key, hash, value, prev.next()));
         }
      }

      ++this.contentSize;
      return null;
   }

   private Node remove0(final Object elem) {
      int scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(elem);
      return this.remove0(elem, scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16);
   }

   private Node remove0(final Object elem, final int hash) {
      int idx = hash & this.scala$collection$mutable$HashMap$$table.length - 1;
      Node var4 = this.scala$collection$mutable$HashMap$$table[idx];
      if (var4 == null) {
         return null;
      } else if (var4.hash() == hash && BoxesRunTime.equals(var4.key(), elem)) {
         this.scala$collection$mutable$HashMap$$table[idx] = var4.next();
         --this.contentSize;
         return var4;
      } else {
         Node prev = var4;

         for(Node next = var4.next(); next != null && next.hash() <= hash; next = next.next()) {
            if (next.hash() == hash && BoxesRunTime.equals(next.key(), elem)) {
               prev.next_$eq(next.next());
               --this.contentSize;
               return next;
            }

            prev = next;
         }

         return null;
      }
   }

   public Iterator iterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new HashMapIterator() {
            public Tuple2 extract(final Node nd) {
               return new Tuple2(nd.key(), nd.value());
            }
         };
      }
   }

   public Iterator keysIterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new HashMapIterator() {
            public Object extract(final Node nd) {
               return nd.key();
            }
         };
      }
   }

   public Iterator valuesIterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new HashMapIterator() {
            public Object extract(final Node nd) {
               return nd.value();
            }
         };
      }
   }

   public Iterator nodeIterator() {
      if (this.size() == 0) {
         Iterator$ var10000 = Iterator$.MODULE$;
         return Iterator$.scala$collection$Iterator$$_empty;
      } else {
         return new HashMapIterator() {
            public Node extract(final Node nd) {
               return nd;
            }
         };
      }
   }

   public Stepper stepper(final StepperShape shape) {
      return shape.parUnbox(new AnyTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$1) -> x$1.next(), (node) -> new Tuple2(node.key(), node.value()), 0, this.scala$collection$mutable$HashMap$$table.length));
   }

   public Stepper keyStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$2) -> x$2.next(), (x$3) -> BoxesRunTime.boxToInteger($anonfun$keyStepper$2(x$3)), 0, this.scala$collection$mutable$HashMap$$table.length);
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$4) -> x$4.next(), (x$5) -> BoxesRunTime.boxToLong($anonfun$keyStepper$4(x$5)), 0, this.scala$collection$mutable$HashMap$$table.length);
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$6) -> x$6.next(), (x$7) -> BoxesRunTime.boxToDouble($anonfun$keyStepper$6(x$7)), 0, this.scala$collection$mutable$HashMap$$table.length) : shape.parUnbox(new AnyTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$8) -> x$8.next(), (x$9) -> x$9.key(), 0, this.scala$collection$mutable$HashMap$$table.length)));
      }
   }

   public Stepper valueStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         return new IntTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$10) -> x$10.next(), (x$11) -> BoxesRunTime.boxToInteger($anonfun$valueStepper$2(x$11)), 0, this.scala$collection$mutable$HashMap$$table.length);
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         return new LongTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$12) -> x$12.next(), (x$13) -> BoxesRunTime.boxToLong($anonfun$valueStepper$4(x$13)), 0, this.scala$collection$mutable$HashMap$$table.length);
      } else {
         return (Stepper)(StepperShape$.MODULE$.DoubleShape() == var2 ? new DoubleTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$14) -> x$14.next(), (x$15) -> BoxesRunTime.boxToDouble($anonfun$valueStepper$6(x$15)), 0, this.scala$collection$mutable$HashMap$$table.length) : shape.parUnbox(new AnyTableStepper(this.size(), this.scala$collection$mutable$HashMap$$table, (x$16) -> x$16.next(), (x$17) -> x$17.value(), 0, this.scala$collection$mutable$HashMap$$table.length)));
      }
   }

   private void growTable(final int newlen) {
      if (newlen < 0) {
         throw new RuntimeException((new java.lang.StringBuilder(39)).append("new HashMap table size ").append(newlen).append(" exceeds maximum").toString());
      } else {
         int oldlen = this.scala$collection$mutable$HashMap$$table.length;
         this.threshold = this.newThreshold(newlen);
         if (this.size() == 0) {
            this.scala$collection$mutable$HashMap$$table = new Node[newlen];
         } else {
            this.scala$collection$mutable$HashMap$$table = (Node[])Arrays.copyOf(this.scala$collection$mutable$HashMap$$table, newlen);
            Node preLow = new Node((Object)null, 0, (Object)null, (Node)null);

            for(Node preHigh = new Node((Object)null, 0, (Object)null, (Node)null); oldlen < newlen; oldlen *= 2) {
               for(int i = 0; i < oldlen; ++i) {
                  Node old = this.scala$collection$mutable$HashMap$$table[i];
                  if (old != null) {
                     preLow.next_$eq((Node)null);
                     preHigh.next_$eq((Node)null);
                     Node lastLow = preLow;
                     Node lastHigh = preHigh;

                     Node next;
                     for(Node n = old; n != null; n = next) {
                        next = n.next();
                        if ((n.hash() & oldlen) == 0) {
                           lastLow.next_$eq(n);
                           lastLow = n;
                        } else {
                           lastHigh.next_$eq(n);
                           lastHigh = n;
                        }
                     }

                     lastLow.next_$eq((Node)null);
                     if (old != preLow.next()) {
                        this.scala$collection$mutable$HashMap$$table[i] = preLow.next();
                     }

                     if (preHigh.next() != null) {
                        this.scala$collection$mutable$HashMap$$table[i + oldlen] = preHigh.next();
                        lastHigh.next_$eq((Node)null);
                     }
                  }
               }
            }

         }
      }
   }

   private int tableSizeFor(final int capacity) {
      RichInt$ var10000 = RichInt$.MODULE$;
      var10000 = RichInt$.MODULE$;
      int var2 = capacity - 1;
      int max$extension_that = 4;
      scala.math.package$ var7 = scala.math.package$.MODULE$;
      int var3 = Integer.highestOneBit(Math.max(var2, max$extension_that)) * 2;
      int min$extension_that = 1073741824;
      var7 = scala.math.package$.MODULE$;
      return Math.min(var3, min$extension_that);
   }

   private int newThreshold(final int size) {
      return (int)((double)size * this.loadFactor);
   }

   public void clear() {
      Arrays.fill(this.scala$collection$mutable$HashMap$$table, (Object)null);
      this.contentSize = 0;
   }

   public Option get(final Object key) {
      int findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
      int findNode_hash = findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
      Node var4 = this.scala$collection$mutable$HashMap$$table[findNode_hash & this.scala$collection$mutable$HashMap$$table.length - 1];
      Node var10000 = var4 == null ? null : var4.findNode(key, findNode_hash);
      Object var6 = null;
      Node var2 = var10000;
      return (Option)(var2 == null ? None$.MODULE$ : new Some(var2.value()));
   }

   public Object apply(final Object key) throws NoSuchElementException {
      int findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
      int findNode_hash = findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
      Node var4 = this.scala$collection$mutable$HashMap$$table[findNode_hash & this.scala$collection$mutable$HashMap$$table.length - 1];
      Node var10000 = var4 == null ? null : var4.findNode(key, findNode_hash);
      Object var6 = null;
      Node var2 = var10000;
      return var2 == null ? this.default(key) : var2.value();
   }

   public Object getOrElse(final Object key, final Function0 default) {
      Class var10000 = this.getClass();
      Class var3 = HashMap.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            int findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
            int findNode_hash = findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ findNode_scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
            Node var6 = this.scala$collection$mutable$HashMap$$table[findNode_hash & this.scala$collection$mutable$HashMap$$table.length - 1];
            Node var10 = var6 == null ? null : var6.findNode(key, findNode_hash);
            Object var9 = null;
            Node nd = var10;
            if (nd == null) {
               return default.apply();
            }

            return nd.value();
         }
      }

      Option var8 = this.get(key);
      if (var8 instanceof Some) {
         return ((Some)var8).value();
      } else if (None$.MODULE$.equals(var8)) {
         return default.apply();
      } else {
         throw new MatchError(var8);
      }
   }

   public Object getOrElseUpdate(final Object key, final Function0 defaultValue) {
      Class var10000 = this.getClass();
      Class var3 = HashMap.class;
      if (var10000 != null) {
         if (var10000.equals(var3)) {
            int scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash = Statics.anyHash(key);
            int hash = scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash ^ scala$collection$mutable$HashMap$$computeHash_scala$collection$mutable$HashMap$$improveHash_originalHash >>> 16;
            int idx = hash & this.scala$collection$mutable$HashMap$$table.length - 1;
            Node var7 = this.scala$collection$mutable$HashMap$$table[idx];
            Node nd = var7 == null ? null : var7.findNode(key, hash);
            if (nd != null) {
               return nd.value();
            }

            Node[] table0 = this.scala$collection$mutable$HashMap$$table;
            Object var9 = defaultValue.apply();
            if (this.contentSize + 1 >= this.threshold) {
               this.growTable(this.scala$collection$mutable$HashMap$$table.length * 2);
            }

            int newIdx = table0 == this.scala$collection$mutable$HashMap$$table ? idx : hash & this.scala$collection$mutable$HashMap$$table.length - 1;
            this.put0(key, var9, false, hash, newIdx);
            return var9;
         }
      }

      Option var12 = this.get(key);
      if (var12 instanceof Some) {
         return ((Some)var12).value();
      } else if (None$.MODULE$.equals(var12)) {
         Object getOrElseUpdate_d = defaultValue.apply();
         this.update(key, getOrElseUpdate_d);
         return getOrElseUpdate_d;
      } else {
         throw new MatchError(var12);
      }
   }

   public Option put(final Object key, final Object value) {
      Some var3 = this.put0(key, value, true);
      return (Option)(var3 == null ? None$.MODULE$ : var3);
   }

   public Option remove(final Object key) {
      Node var2 = this.remove0(key);
      return (Option)(var2 == null ? None$.MODULE$ : new Some(var2.value()));
   }

   public void update(final Object key, final Object value) {
      this.put0(key, value, false);
   }

   public HashMap addOne(final Tuple2 elem) {
      this.put0(elem._1(), elem._2(), false);
      return this;
   }

   public HashMap subtractOne(final Object elem) {
      this.remove0(elem);
      return this;
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public void foreach(final Function1 f) {
      int len = this.scala$collection$mutable$HashMap$$table.length;

      for(int i = 0; i < len; ++i) {
         Node n = this.scala$collection$mutable$HashMap$$table[i];
         if (n != null) {
            n.foreach(f);
         }
      }

   }

   public void foreachEntry(final Function2 f) {
      int len = this.scala$collection$mutable$HashMap$$table.length;

      for(int i = 0; i < len; ++i) {
         Node n = this.scala$collection$mutable$HashMap$$table[i];
         if (n != null) {
            n.foreachEntry(f);
         }
      }

   }

   public Object writeReplace() {
      return new DefaultSerializationProxy(new DeserializationFactory(this.scala$collection$mutable$HashMap$$table.length, this.loadFactor), this);
   }

   public HashMap filterInPlace(final Function2 p) {
      if (this.nonEmpty()) {
         for(int bucket = 0; bucket < this.scala$collection$mutable$HashMap$$table.length; ++bucket) {
            Node head;
            for(head = this.scala$collection$mutable$HashMap$$table[bucket]; head != null && !BoxesRunTime.unboxToBoolean(p.apply(head.key(), head.value())); --this.contentSize) {
               head = head.next();
            }

            if (head != null) {
               Node prev = head;

               for(Node next = head.next(); next != null; next = next.next()) {
                  if (BoxesRunTime.unboxToBoolean(p.apply(next.key(), next.value()))) {
                     prev = next;
                  } else {
                     prev.next_$eq(next.next());
                     --this.contentSize;
                  }
               }
            }

            this.scala$collection$mutable$HashMap$$table[bucket] = head;
         }
      }

      return this;
   }

   public HashMap mapValuesInPlaceImpl(final Function2 f) {
      int len = this.scala$collection$mutable$HashMap$$table.length;

      for(int i = 0; i < len; ++i) {
         for(Node n = this.scala$collection$mutable$HashMap$$table[i]; n != null; n = n.next()) {
            n.value_$eq(f.apply(n.key(), n.value()));
         }
      }

      return this;
   }

   public MapFactory mapFactory() {
      return HashMap$.MODULE$;
   }

   public String stringPrefix() {
      return "HashMap";
   }

   public int hashCode() {
      if (this.isEmpty()) {
         return MurmurHash3$.MODULE$.emptyMapHash();
      } else {
         HashMapIterator tupleHashIterator = new HashMapIterator() {
            private int hash;
            // $FF: synthetic field
            private final HashMap $outer;

            public int hash() {
               return this.hash;
            }

            public void hash_$eq(final int x$1) {
               this.hash = x$1;
            }

            public int hashCode() {
               return this.hash();
            }

            public Object extract(final Node nd) {
               this.hash_$eq(MurmurHash3$.MODULE$.tuple2Hash(this.$outer.unimproveHash(nd.hash()), Statics.anyHash(nd.value())));
               return this;
            }

            public {
               if (HashMap.this == null) {
                  throw null;
               } else {
                  this.$outer = HashMap.this;
                  this.hash = 0;
               }
            }
         };
         return MurmurHash3$.MODULE$.unorderedHash(tupleHashIterator, MurmurHash3$.MODULE$.mapSeed());
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$addAll$1(final HashMap $this, final Object k, final Object v, final int h) {
      $this.put0(k, v, h ^ h >>> 16, false);
   }

   private final void findNode$1(final Node prev, final Node nd, final Object k, final int h, final ObjectRef previousNode$1, final ObjectRef foundNode$1) {
      while(h != nd.hash() || !BoxesRunTime.equals(k, nd.key())) {
         if (nd.next() == null || nd.hash() > h) {
            return;
         }

         Node var10000 = nd;
         Node var10001 = nd.next();
         h = h;
         k = k;
         nd = var10001;
         prev = var10000;
      }

      previousNode$1.elem = prev;
      foundNode$1.elem = nd;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$subtractAll$1(final HashMap $this, final Object k, final int h) {
      $this.remove0(k, h ^ h >>> 16);
      return $this.size() > 0;
   }

   // $FF: synthetic method
   public static final int $anonfun$keyStepper$2(final Node x$3) {
      return BoxesRunTime.unboxToInt(x$3.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$keyStepper$4(final Node x$5) {
      return BoxesRunTime.unboxToLong(x$5.key());
   }

   // $FF: synthetic method
   public static final double $anonfun$keyStepper$6(final Node x$7) {
      return BoxesRunTime.unboxToDouble(x$7.key());
   }

   // $FF: synthetic method
   public static final int $anonfun$valueStepper$2(final Node x$11) {
      return BoxesRunTime.unboxToInt(x$11.value());
   }

   // $FF: synthetic method
   public static final long $anonfun$valueStepper$4(final Node x$13) {
      return BoxesRunTime.unboxToLong(x$13.value());
   }

   // $FF: synthetic method
   public static final double $anonfun$valueStepper$6(final Node x$15) {
      return BoxesRunTime.unboxToDouble(x$15.value());
   }

   public HashMap(final int initialCapacity, final double loadFactor) {
      this.loadFactor = loadFactor;
      this.scala$collection$mutable$HashMap$$table = new Node[this.tableSizeFor(initialCapacity)];
      this.threshold = this.newThreshold(this.scala$collection$mutable$HashMap$$table.length);
      this.contentSize = 0;
   }

   public HashMap() {
      HashMap$ var10001 = HashMap$.MODULE$;
      HashMap$ var10002 = HashMap$.MODULE$;
      this(16, (double)0.75F);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private abstract class HashMapIterator extends AbstractIterator {
      private int i;
      private Node node;
      private final int len;
      // $FF: synthetic field
      public final HashMap $outer;

      public abstract Object extract(final Node nd);

      public boolean hasNext() {
         if (this.node != null) {
            return true;
         } else {
            while(this.i < this.len) {
               Node n = this.scala$collection$mutable$HashMap$HashMapIterator$$$outer().scala$collection$mutable$HashMap$$table[this.i];
               ++this.i;
               if (n != null) {
                  this.node = n;
                  return true;
               }
            }

            return false;
         }
      }

      public Object next() {
         if (!this.hasNext()) {
            Iterator$ var10000 = Iterator$.MODULE$;
            return Iterator$.scala$collection$Iterator$$_empty.next();
         } else {
            Object r = this.extract(this.node);
            this.node = this.node.next();
            return r;
         }
      }

      // $FF: synthetic method
      public HashMap scala$collection$mutable$HashMap$HashMapIterator$$$outer() {
         return this.$outer;
      }

      public HashMapIterator() {
         if (HashMap.this == null) {
            throw null;
         } else {
            this.$outer = HashMap.this;
            super();
            this.i = 0;
            this.node = null;
            this.len = HashMap.this.scala$collection$mutable$HashMap$$table.length;
         }
      }
   }

   private static final class DeserializationFactory implements Factory, Serializable {
      private static final long serialVersionUID = 3L;
      private final int tableLength;
      private final double loadFactor;

      public int tableLength() {
         return this.tableLength;
      }

      public double loadFactor() {
         return this.loadFactor;
      }

      public HashMap fromSpecific(final IterableOnce it) {
         return (new HashMap(this.tableLength(), this.loadFactor())).addAll(it);
      }

      public Builder newBuilder() {
         HashMap$ var10000 = HashMap$.MODULE$;
         int var4 = this.tableLength();
         double newBuilder_loadFactor = this.loadFactor();
         int newBuilder_initialCapacity = var4;
         return new GrowableBuilder(newBuilder_initialCapacity, newBuilder_loadFactor) {
            public void sizeHint(final int size) {
               ((HashMap)this.elems()).sizeHint(size);
            }
         };
      }

      public DeserializationFactory(final int tableLength, final double loadFactor) {
         this.tableLength = tableLength;
         this.loadFactor = loadFactor;
      }
   }

   public static final class Node {
      private final Object _key;
      private final int _hash;
      private Object _value;
      private Node _next;

      public Object key() {
         return this._key;
      }

      public int hash() {
         return this._hash;
      }

      public Object value() {
         return this._value;
      }

      public void value_$eq(final Object v) {
         this._value = v;
      }

      public Node next() {
         return this._next;
      }

      public void next_$eq(final Node n) {
         this._next = n;
      }

      public Node findNode(final Object k, final int h) {
         while(h != this._hash || !BoxesRunTime.equals(k, this._key)) {
            if (this._next == null || this._hash > h) {
               return null;
            }

            h = h;
            k = k;
            this = this._next;
         }

         return this;
      }

      public void foreach(final Function1 f) {
         while(true) {
            f.apply(new Tuple2(this._key, this._value));
            if (this._next == null) {
               return;
            }

            f = f;
            this = this._next;
         }
      }

      public void foreachEntry(final Function2 f) {
         while(true) {
            f.apply(this._key, this._value);
            if (this._next == null) {
               return;
            }

            f = f;
            this = this._next;
         }
      }

      public String toString() {
         return (new java.lang.StringBuilder(14)).append("Node(").append(this.key()).append(", ").append(this.value()).append(", ").append(this.hash()).append(") -> ").append(this.next()).toString();
      }

      public Node(final Object _key, final int _hash, final Object _value, final Node _next) {
         this._key = _key;
         this._hash = _hash;
         this._value = _value;
         this._next = _next;
         super();
      }
   }
}
