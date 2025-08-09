package scala.xml.parsing;

import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Seq;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Queue;
import scala.collection.mutable.StringBuilder;
import scala.io.Source;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.java8.JFunction0;
import scala.xml.Comment;
import scala.xml.Document;
import scala.xml.EntityRef;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.Node;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.NodeSeq$;
import scala.xml.Null$;
import scala.xml.PCData$;
import scala.xml.PrefixedAttribute;
import scala.xml.ProcInstr;
import scala.xml.SpecialNode;
import scala.xml.Text$;
import scala.xml.TextBuffer$;
import scala.xml.TopScope$;
import scala.xml.UnprefixedAttribute;
import scala.xml.Utility;
import scala.xml.Utility$;
import scala.xml.dtd.AttrDecl;
import scala.xml.dtd.DEFAULT;
import scala.xml.dtd.DTD;
import scala.xml.dtd.DefaultDecl;
import scala.xml.dtd.ExtDef;
import scala.xml.dtd.ExternalID;
import scala.xml.dtd.IMPLIED$;
import scala.xml.dtd.IntDef;
import scala.xml.dtd.PublicID;
import scala.xml.dtd.REQUIRED$;
import scala.xml.dtd.SystemID;

@ScalaSignature(
   bytes = "\u0006\u0005\r\u0005b!C+W!\u0003\r\t!XB\r\u0011\u0015I\u0007\u0001\"\u0001k\u000b\u0011q\u0007\u0001I8\u0006\tI\u0004\u0001e]\u0003\u0005s\u0002\u0001#0\u0002\u0003\u007f\u0001\u0001zXABA\t\u0001\u0001\nY\u0001C\u0004\u0002\u0014\u0001!\t%!\u0006\t\u000f\u0005]\u0002\u0001\"\u0011\u0002:!9\u0011q\b\u0001\u0005B\u0005\u0005\u0003\"CA(\u0001\t\u0007i\u0011AA)\u0011%\t\u0019\u0006\u0001b\u0001\u000e\u0003\t)\u0006C\u0004\u0002^\u00011\t!a\u0018\t\u0013\u0005\u0015\u0004\u00011A\u0005\u0012\u0005E\u0003\"CA4\u0001\u0001\u0007I\u0011CA5\r\u001d\ty\u0007\u0001\u0001W\u0003cB\u0011\"a\u001d\u0010\u0005\u0003\u0005\u000b\u0011B:\t\u000f\u0005Ut\u0002\"\u0001\u0002x!I\u0011qP\bC\u0002\u0013%\u0011\u0011\u0011\u0005\t\u0003'{\u0001\u0015!\u0003\u0002\u0004\"9\u0011QS\b\u0005\u0002\u0005]\u0005\"CAT\u001f\t\u0007I\u0011IAU\u0011!\t\tl\u0004Q\u0001\n\u0005-\u0006bBAK\u0001\u0011\u0005\u0013q\u0013\u0005\n\u0003g\u0003!\u0019!C\u0005\u0003kC\u0011\"!0\u0001\u0001\u0004%\t!a0\t\u0013\u0005\u001d\u0007\u00011A\u0005\u0002\u0005%\u0007bCAg\u0001\u0001\u0007\t\u0019!C\u0001\u0003\u001fD1\"!5\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0002T\"I\u0011q\u001b\u0001A\u0002\u0013\u0005\u0011q\u001a\u0005\n\u00033\u0004\u0001\u0019!C\u0001\u00037D1\"a8\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0002P\"Y\u0011\u0011\u001d\u0001A\u0002\u0003\u0007I\u0011AAr\u0011%\t9\u000f\u0001a\u0001\n\u0003\t)\u0006C\u0005\u0002j\u0002\u0001\r\u0011\"\u0001\u0002l\"I\u0011q\u001e\u0001A\u0002\u0013\u0005\u0011Q\u000b\u0005\n\u0003c\u0004\u0001\u0019!C\u0001\u0003gD1\"a>\u0001\u0001\u0004\u0005\r\u0011\"\u0001\u0002z\"Y\u00111 \u0001A\u0002\u0003\u0007I\u0011AA\u007f\u0011\u001d\u0011\t\u0001\u0001C!\u0003sD\u0011Ba\u0001\u0001\u0005\u0004%\tB!\u0002\t\u0017\t5\u0001\u00011AA\u0002\u0013\u0005!q\u0002\u0005\f\u00057\u0001\u0001\u0019!a\u0001\n\u0003\u0011i\u0002C\u0006\u0003\"\u0001\u0001\r\u00111A\u0005\u0012\t\r\u0002b\u0003B\u0016\u0001\u0001\u0007\t\u0019!C\t\u0005[AqA!\r\u0001\t\u0003\n)\u0006C\u0004\u00034\u0001!\tA!\u000e\t\u000f\t]\u0002\u0001\"\u0003\u0003:!9!Q\n\u0001\u0005\u0002\t=\u0003b\u0002B)\u0001\u0011\u0005!1\u000b\u0005\b\u0005/\u0002A\u0011\u0001B-\u0011\u001d\u0011Y\u0006\u0001C\t\u0005;BqAa\u0019\u0001\t\u0003\u0011)\u0007C\u0004\u0003h\u0001!\t&!?\t\u000f\t%\u0004\u0001\"\u0011\u0003l!9!q\u000f\u0001\u0005B\te\u0004B\u0002BD\u0001\u0011\u0005#\u000eC\u0004\u0003\n\u0002!\tAa#\t\u000f\t=\u0005\u0001\"\u0001\u0003\u0012\"9!1\u0013\u0001\u0005\u0002\tU\u0005b\u0002BL\u0001\u0011\u0005!Q\u0013\u0005\b\u00053\u0003A\u0011\u0001BN\u0011\u001d\u0011i\u000b\u0001C\u0001\u0005_CqA!.\u0001\t\u0003\u00119\fC\u0004\u0003<\u0002!\tA!0\t\r\t\u0015\u0007\u0001\"\u0001k\u0011\u001d\u00119\r\u0001C\u0001\u0005\u0013DqA!4\u0001\t\u0003\u0011y\rC\u0004\u0003T\u0002!IA!6\t\u000f\u0005\r\u0004\u0001\"\u0001\u0003\u0012\"9!q\u001b\u0001\u0005\u0002\tE\u0005B\u0002Bm\u0001\u0011\u0005!\u000eC\u0004\u0003\\\u0002!\tA!8\t\r\t\u0015\b\u0001\"\u0001k\u0011\u0019\u00119\u000f\u0001C\u0001U\"1!\u0011\u001e\u0001\u0005\u0002)DaAa;\u0001\t\u0003Q\u0007B\u0002Bw\u0001\u0011\u0005!\u000e\u0003\u0004\u0003p\u0002!\tA\u001b\u0005\b\u0005c\u0004A\u0011\tBz\u0011\u001d\u0011\t\u0010\u0001C!\u0005wDqAa@\u0001\t\u0003\u0019\t\u0001C\u0004\u0004\b\u0001!\ta!\u0003\t\u000f\r=\u0001\u0001\"\u0001\u0004\u0012!11q\u0003\u0001\u0005\u0002)\u0014A\"T1sWV\u0004\b+\u0019:tKJT!a\u0016-\u0002\u000fA\f'o]5oO*\u0011\u0011LW\u0001\u0004q6d'\"A.\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M!\u0001A\u00182g!\ty\u0006-D\u0001[\u0013\t\t'L\u0001\u0004B]f\u0014VM\u001a\t\u0003G\u0012l\u0011AV\u0005\u0003KZ\u0013!#T1sWV\u0004\b+\u0019:tKJ\u001cu.\\7p]B\u00111mZ\u0005\u0003QZ\u0013!\u0002V8lK:$Vm\u001d;t\u0003\u0019!\u0013N\\5uIQ\t1\u000e\u0005\u0002`Y&\u0011QN\u0017\u0002\u0005+:LGO\u0001\u0007Q_NLG/[8o)f\u0004X\r\u0005\u0002`a&\u0011\u0011O\u0017\u0002\u0004\u0013:$(!C%oaV$H+\u001f9f!\t!x/D\u0001v\u0015\t1(,\u0001\u0002j_&\u0011\u00010\u001e\u0002\u0007'>,(oY3\u0003\u0017\u0015cW-\\3oiRK\b/\u001a\t\u0003wrl\u0011\u0001W\u0005\u0003{b\u0013qAT8eKN+\u0017O\u0001\bBiR\u0014\u0018NY;uKN$\u0016\u0010]3\u0011\u000f}\u000b\t!!\u0002\u0002\f%\u0019\u00111\u0001.\u0003\rQ+\b\u000f\\33!\rY\u0018qA\u0005\u0004\u0003\u0013A&\u0001C'fi\u0006$\u0015\r^1\u0011\u0007m\fi!C\u0002\u0002\u0010a\u0013\u0001CT1nKN\u0004\u0018mY3CS:$\u0017N\\4\u0003\u001b9\u000bW.Z:qC\u000e,G+\u001f9f\u00039!(/\u001e8dCR,G-\u0012:s_J$B!a\u0006\u0002\u001eA\u0019q,!\u0007\n\u0007\u0005m!LA\u0004O_RD\u0017N\\4\t\u000f\u0005}q\u00011\u0001\u0002\"\u0005\u0019Qn]4\u0011\t\u0005\r\u0012\u0011\u0007\b\u0005\u0003K\ti\u0003E\u0002\u0002(ik!!!\u000b\u000b\u0007\u0005-B,\u0001\u0004=e>|GOP\u0005\u0004\u0003_Q\u0016A\u0002)sK\u0012,g-\u0003\u0003\u00024\u0005U\"AB*ue&twMC\u0002\u00020i\u000b!\"\u001a:s_Jtu.\u00128e)\u0011\t9\"a\u000f\t\u000f\u0005u\u0002\u00021\u0001\u0002\"\u0005\u0019A/Y4\u0002\u0019aD\u0015M\u001c3mK\u0016\u0013(o\u001c:\u0015\u000b-\f\u0019%!\u0014\t\u000f\u0005\u0015\u0013\u00021\u0001\u0002H\u0005!A\u000f[1u!\ry\u0016\u0011J\u0005\u0004\u0003\u0017R&\u0001B\"iCJDq!a\b\n\u0001\u0004\t\t#A\u0003j]B,H/F\u0001t\u0003)\u0001(/Z:feZ,wkU\u000b\u0003\u0003/\u00022aXA-\u0013\r\tYF\u0017\u0002\b\u0005>|G.Z1o\u00039)\u0007\u0010^3s]\u0006d7k\\;sG\u0016$2a]A1\u0011\u001d\t\u0019\u0007\u0004a\u0001\u0003C\tQb]=ti\u0016lG*\u001b;fe\u0006d\u0017\u0001C2ve&s\u0007/\u001e;\u0002\u0019\r,(/\u00138qkR|F%Z9\u0015\u0007-\fY\u0007\u0003\u0005\u0002n9\t\t\u00111\u0001t\u0003\rAH%\r\u0002\u000e/&$\b\u000eT8pW\u0006CW-\u00193\u0014\u0005=\u0019\u0018AC;oI\u0016\u0014H._5oO\u00061A(\u001b8jiz\"B!!\u001f\u0002~A\u0019\u00111P\b\u000e\u0003\u0001Aa!a\u001d\u0012\u0001\u0004\u0019\u0018!B9vKV,WCAAB!\u0019\t))a$\u0002H5\u0011\u0011q\u0011\u0006\u0005\u0003\u0013\u000bY)A\u0004nkR\f'\r\\3\u000b\u0007\u00055%,\u0001\u0006d_2dWm\u0019;j_:LA!!%\u0002\b\n)\u0011+^3vK\u00061\u0011/^3vK\u0002\n\u0011\u0002\\8pW\u0006DW-\u00193\u0015\u0005\u0005e\u0005CBAN\u0003C\u000b9ED\u0002`\u0003;K1!a([\u0003\u001d\u0001\u0018mY6bO\u0016LA!a)\u0002&\n\u0001\")\u001e4gKJ,G-\u0013;fe\u0006$xN\u001d\u0006\u0004\u0003?S\u0016\u0001B5uKJ,\"!a+\u0011\r\u0005m\u0015QVA$\u0013\u0011\ty+!*\u0003\u0011%#XM]1u_J\fQ!\u001b;fe\u0002\na\u0001[1oI2,WCAA\\!\r\u0019\u0017\u0011X\u0005\u0004\u0003w3&!D'be.,\b\u000fS1oI2,'/\u0001\u0005j]B\u001cF/Y2l+\t\t\t\rE\u0003\u0002\u001c\u0006\r7/\u0003\u0003\u0002F\u0006\u0015&\u0001\u0002'jgR\fA\"\u001b8q'R\f7m[0%KF$2a[Af\u0011%\tiGGA\u0001\u0002\u0004\t\t-A\u0002q_N,\u0012a\\\u0001\ba>\u001cx\fJ3r)\rY\u0017Q\u001b\u0005\t\u0003[b\u0012\u0011!a\u0001_\u0006AQ\r\u001f;J]\u0012,\u00070\u0001\u0007fqRLe\u000eZ3y?\u0012*\u0017\u000fF\u0002l\u0003;D\u0001\"!\u001c\u001f\u0003\u0003\u0005\ra\\\u0001\u0007i6\u0004\bo\\:\u0002\u0015Ql\u0007\u000f]8t?\u0012*\u0017\u000fF\u0002l\u0003KD\u0001\"!\u001c!\u0003\u0003\u0005\ra\\\u0001\r]\u0016DHo\u00115OK\u0016$W\rZ\u0001\u0011]\u0016DHo\u00115OK\u0016$W\rZ0%KF$2a[Aw\u0011%\tiGIA\u0001\u0002\u0004\t9&\u0001\u0006sK\u0006\u001c\u0007.\u001a3F_\u001a\faB]3bG\",G-R8g?\u0012*\u0017\u000fF\u0002l\u0003kD\u0011\"!\u001c%\u0003\u0003\u0005\r!a\u0016\u0002\u00151\f7\u000f^\"i%\u0016\fG-\u0006\u0002\u0002H\u0005qA.Y:u\u0007\"\u0014V-\u00193`I\u0015\fHcA6\u0002\u0000\"I\u0011Q\u000e\u0014\u0002\u0002\u0003\u0007\u0011qI\u0001\u0003G\"\fAa\u00192vMV\u0011!q\u0001\t\u0005\u00037\u0013I!\u0003\u0003\u0003\f\u0005\u0015&!D*ue&twMQ;jY\u0012,'/A\u0002ei\u0012,\"A!\u0005\u0011\t\tM!qC\u0007\u0003\u0005+Q1A!\u0004Y\u0013\u0011\u0011IB!\u0006\u0003\u0007\u0011#F)A\u0004ei\u0012|F%Z9\u0015\u0007-\u0014y\u0002C\u0005\u0002n)\n\t\u00111\u0001\u0003\u0012\u0005\u0019Am\\2\u0016\u0005\t\u0015\u0002cA>\u0003(%\u0019!\u0011\u0006-\u0003\u0011\u0011{7-^7f]R\fq\u0001Z8d?\u0012*\u0017\u000fF\u0002l\u0005_A\u0011\"!\u001c-\u0003\u0003\u0005\rA!\n\u0002\u0007\u0015|g-\u0001\u0007y[2\u0004&o\\2J]N$(\u000f\u0006\u0002\u0002\u0006\u0005\u0001\u0002O]8m_\u001e|%\u000fV3yi\u0012+7\r\u001c\u000b\u0005\u0005w\u0011I\u0005E\u0005`\u0005{\u0011\tE!\u0011\u0003H%\u0019!q\b.\u0003\rQ+\b\u000f\\34!\u0015y&1IA\u0011\u0013\r\u0011)E\u0017\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u000b}\u0013\u0019%a\u0016\t\u000f\t-s\u00061\u0001\u0002X\u0005A\u0011n\u001d)s_2|w-\u0001\u0004qe>dwn\u001a\u000b\u0003\u0005w\t\u0001\u0002^3yi\u0012+7\r\u001c\u000b\u0003\u0005+\u0002raXA\u0001\u0005\u0003\u0012\t%\u0001\u0005e_\u000e,X.\u001a8u)\t\u0011)#A\u0004qkR\u001c\u0005.\u0019:\u0015\t\t\u001d!q\f\u0005\b\u0005C\u001a\u0004\u0019AA$\u0003\u0005\u0019\u0017AC5oSRL\u0017\r\\5{KV\u0011\u00111P\u0001\u0014G\"|&/\u001a;ve:LgnZ0oKb$8\r[\u0001\r[.\fE\u000f\u001e:jEV$Xm\u001d\u000b\u0007\u0005[\u0012yGa\u001d\u0011\u0007\u0005mT\u0001C\u0004\u0003rY\u0002\r!!\t\u0002\t9\fW.\u001a\u0005\b\u0005k2\u0004\u0019AA\u0006\u0003\u0019\u00018oY8qK\u0006YQn\u001b)s_\u000eLen\u001d;s)!\u0011YH! \u0003\u0002\n\r\u0005cAA>\t!1!qP\u001cA\u0002=\f\u0001\u0002]8tSRLwN\u001c\u0005\b\u0005c:\u0004\u0019AA\u0011\u0011\u001d\u0011)i\u000ea\u0001\u0003C\tA\u0001^3yi\u00061a.\u001a=uG\"\f1\u0002_!uiJL'-\u001e;fgR\u0019qP!$\t\u000f\tU\u0014\b1\u0001\u0002\f\u0005a\u00010\u00128uSRLh+\u00197vKR\u0011\u0011\u0011E\u0001\nq\u000eC\u0017M\u001d#bi\u0006,\u0012A_\u0001\tq\u000e{W.\\3oi\u0006Q\u0011\r\u001d9f]\u0012$V\r\u001f;\u0015\u000f-\u0014iJa(\u0003*\"1\u0011QZ\u001fA\u0002=DqA!)>\u0001\u0004\u0011\u0019+\u0001\u0002ugB\u00191P!*\n\u0007\t\u001d\u0006L\u0001\u0006O_\u0012,')\u001e4gKJDqAa+>\u0001\u0004\t\t#A\u0002uqR\f\u0001bY8oi\u0016tG/\r\u000b\u0006W\nE&1\u0017\u0005\b\u0005kr\u0004\u0019AA\u0006\u0011\u001d\u0011\tK\u0010a\u0001\u0005G\u000bqaY8oi\u0016tG\u000fF\u0002{\u0005sCqA!\u001e@\u0001\u0004\tY!\u0001\u0006fqR,'O\\1m\u0013\u0012#\"Aa0\u0011\t\tM!\u0011Y\u0005\u0005\u0005\u0007\u0014)B\u0001\u0006FqR,'O\\1m\u0013\u0012\u000b\u0001\u0002]1sg\u0016$E\u000bR\u0001\bK2,W.\u001a8u)\rQ(1\u001a\u0005\b\u0005k\u0012\u0005\u0019AA\u0006\u0003!)G.Z7f]R\fDc\u0001>\u0003R\"9!QO\"A\u0002\u0005-\u0011!\u0002=UKb$XCAA\u0011\u00031\u0001XOY5e\u0019&$XM]1m\u0003%)\u0007\u0010^*vEN,G/A\u0006nCJ\\W\u000f\u001d#fG2\fDC\u0001Bp!\ry&\u0011]\u0005\u0004\u0005GT&aA!os\u0006QQ.\u0019:lkB$Um\u00197\u0002\u0013%tGoU;cg\u0016$\u0018aC3mK6,g\u000e\u001e#fG2\f\u0001\"\u0019;ue\u0012+7\r\\\u0001\u000bK:$\u0018\u000e^=EK\u000ed\u0017\u0001\u00048pi\u0006$\u0018n\u001c8EK\u000ed\u0017!\u0005:fa>\u0014HoU=oi\u0006DXI\u001d:peR)1N!>\u0003x\"1\u0011QZ(A\u0002=DqA!?P\u0001\u0004\t\t#A\u0002tiJ$2a\u001bB\u007f\u0011\u001d\u0011I\u0010\u0015a\u0001\u0003C\tQC]3q_J$h+\u00197jI\u0006$\u0018n\u001c8FeJ|'\u000fF\u0003l\u0007\u0007\u0019)\u0001\u0003\u0004\u0002NF\u0003\ra\u001c\u0005\b\u0005s\f\u0006\u0019AA\u0011\u0003\u0011\u0001Xo\u001d5\u0015\u0007-\u001cY\u0001C\u0004\u0004\u000eI\u0003\r!!\t\u0002\u0015\u0015tG/\u001b;z\u001d\u0006lW-\u0001\u0007qkNDW\t\u001f;fe:\fG\u000eF\u0002l\u0007'Aqa!\u0006T\u0001\u0004\t\t#\u0001\u0005tsN$X-\\%e\u0003\r\u0001x\u000e\u001d\n\u0007\u00077\u0019y\"a.\u0007\r\ru\u0001\u0001AB\r\u00051a$/\u001a4j]\u0016lWM\u001c;?!\t\u0019\u0007\u0001"
)
public interface MarkupParser extends MarkupParserCommon {
   void scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq(final MarkupHandler x$1);

   void scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(final StringBuilder x$1);

   // $FF: synthetic method
   static Nothing truncatedError$(final MarkupParser $this, final String msg) {
      return $this.truncatedError(msg);
   }

   default Nothing truncatedError(final String msg) {
      throw new FatalError(msg);
   }

   // $FF: synthetic method
   static Nothing errorNoEnd$(final MarkupParser $this, final String tag) {
      return $this.errorNoEnd(tag);
   }

   default Nothing errorNoEnd(final String tag) {
      throw new FatalError((new java.lang.StringBuilder(24)).append("expected closing tag of ").append(tag).toString());
   }

   // $FF: synthetic method
   static void xHandleError$(final MarkupParser $this, final char that, final String msg) {
      $this.xHandleError(that, msg);
   }

   default void xHandleError(final char that, final String msg) {
      this.reportSyntaxError(msg);
   }

   Source input();

   boolean preserveWS();

   Source externalSource(final String systemLiteral);

   Source curInput();

   void curInput_$eq(final Source x$1);

   // $FF: synthetic method
   static BufferedIterator lookahead$(final MarkupParser $this) {
      return $this.lookahead();
   }

   default BufferedIterator lookahead() {
      Source var2 = this.curInput();
      if (var2 instanceof WithLookAhead && ((WithLookAhead)var2).scala$xml$parsing$MarkupParser$WithLookAhead$$$outer() == this) {
         WithLookAhead var3 = (WithLookAhead)var2;
         return var3.lookahead();
      } else {
         WithLookAhead newInput = (MarkupHandler)this.new WithLookAhead(this.curInput());
         this.curInput_$eq(newInput);
         return newInput.lookahead();
      }
   }

   MarkupHandler scala$xml$parsing$MarkupParser$$handle();

   List inpStack();

   void inpStack_$eq(final List x$1);

   int pos();

   void pos_$eq(final int x$1);

   int extIndex();

   void extIndex_$eq(final int x$1);

   int tmppos();

   void tmppos_$eq(final int x$1);

   boolean nextChNeeded();

   void nextChNeeded_$eq(final boolean x$1);

   boolean reachedEof();

   void reachedEof_$eq(final boolean x$1);

   char lastChRead();

   void lastChRead_$eq(final char x$1);

   // $FF: synthetic method
   static char ch$(final MarkupParser $this) {
      return $this.ch();
   }

   default char ch() {
      if (this.nextChNeeded()) {
         if (this.curInput().hasNext()) {
            this.lastChRead_$eq(this.curInput().next());
            this.pos_$eq(this.curInput().pos());
         } else {
            int ilen = this.inpStack().length();
            if (ilen != this.extIndex() && ilen > 0) {
               this.pop();
            } else {
               this.reachedEof_$eq(true);
               this.lastChRead_$eq((char)0);
            }
         }

         this.nextChNeeded_$eq(false);
      }

      return this.lastChRead();
   }

   StringBuilder cbuf();

   DTD dtd();

   void dtd_$eq(final DTD x$1);

   Document doc();

   void doc_$eq(final Document x$1);

   // $FF: synthetic method
   static boolean eof$(final MarkupParser $this) {
      return $this.eof();
   }

   default boolean eof() {
      this.ch();
      return this.reachedEof();
   }

   // $FF: synthetic method
   static MetaData xmlProcInstr$(final MarkupParser $this) {
      return $this.xmlProcInstr();
   }

   default MetaData xmlProcInstr() {
      this.xToken(.MODULE$.wrapString("xml"));
      this.xSpace();
      Tuple2 var3 = this.xAttributes(TopScope$.MODULE$);
      if (var3 != null) {
         MetaData md = (MetaData)var3._1();
         NamespaceBinding scp = (NamespaceBinding)var3._2();
         if (md != null && scp != null) {
            MetaData md;
            label22: {
               Tuple2 var2 = new Tuple2(md, scp);
               md = (MetaData)var2._1();
               NamespaceBinding scp = (NamespaceBinding)var2._2();
               TopScope$ var10 = TopScope$.MODULE$;
               if (scp == null) {
                  if (var10 == null) {
                     break label22;
                  }
               } else if (scp.equals(var10)) {
                  break label22;
               }

               this.reportSyntaxError("no xmlns definitions here, please.");
            }

            this.xToken('?');
            this.xToken('>');
            return md;
         }
      }

      throw new MatchError(var3);
   }

   private Tuple3 prologOrTextDecl(final boolean isProlog) {
      Option info_ver = scala.None..MODULE$;
      Option info_enc = scala.None..MODULE$;
      Option info_stdl = scala.None..MODULE$;
      MetaData m = this.xmlProcInstr();
      int n = 0;
      if (isProlog) {
         this.xSpaceOpt();
      }

      Seq var10 = m.apply("version");
      if (var10 == null) {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         label85: {
            if (var10 != null) {
               Option var11 = Text$.MODULE$.unapply(var10);
               if (!var11.isEmpty()) {
                  String var12 = (String)var11.get();
                  if ("1.0".equals(var12)) {
                     info_ver = new Some("1.0");
                     ++n;
                     BoxedUnit var23 = BoxedUnit.UNIT;
                     break label85;
                  }
               }
            }

            this.reportSyntaxError("cannot deal with versions != 1.0");
            BoxedUnit var22 = BoxedUnit.UNIT;
         }
      }

      Seq var13 = m.apply("encoding");
      if (var13 == null) {
         BoxedUnit var24 = BoxedUnit.UNIT;
      } else {
         if (var13 == null) {
            throw new MatchError(var13);
         }

         Option var14 = Text$.MODULE$.unapply(var13);
         if (var14.isEmpty()) {
            throw new MatchError(var13);
         }

         String enc = (String)var14.get();
         if (!this.isValidIANAEncoding(.MODULE$.wrapString(enc))) {
            this.reportSyntaxError((new java.lang.StringBuilder(26)).append("\"").append(enc).append("\" is not a valid encoding").toString());
            BoxedUnit var25 = BoxedUnit.UNIT;
         } else {
            info_enc = new Some(enc);
            ++n;
            BoxedUnit var26 = BoxedUnit.UNIT;
         }
      }

      if (isProlog) {
         Seq var16 = m.apply("standalone");
         if (var16 == null) {
            BoxedUnit var27 = BoxedUnit.UNIT;
         } else {
            label92: {
               if (var16 != null) {
                  Option var17 = Text$.MODULE$.unapply(var16);
                  if (!var17.isEmpty()) {
                     String var18 = (String)var17.get();
                     if ("yes".equals(var18)) {
                        info_stdl = new Some(BoxesRunTime.boxToBoolean(true));
                        ++n;
                        BoxedUnit var30 = BoxedUnit.UNIT;
                        break label92;
                     }
                  }
               }

               if (var16 != null) {
                  Option var19 = Text$.MODULE$.unapply(var16);
                  if (!var19.isEmpty()) {
                     String var20 = (String)var19.get();
                     if ("no".equals(var20)) {
                        info_stdl = new Some(BoxesRunTime.boxToBoolean(false));
                        ++n;
                        BoxedUnit var29 = BoxedUnit.UNIT;
                        break label92;
                     }
                  }
               }

               this.reportSyntaxError("either 'yes' or 'no' expected");
               BoxedUnit var28 = BoxedUnit.UNIT;
            }
         }
      }

      if (m.length() - n != 0) {
         String s = isProlog ? "SDDecl? " : "";
         this.reportSyntaxError((new java.lang.StringBuilder(44)).append("VersionInfo EncodingDecl? ").append(s).append(" or '?>' expected!").toString());
      }

      return new Tuple3(info_ver, info_enc, info_stdl);
   }

   // $FF: synthetic method
   static Tuple3 prolog$(final MarkupParser $this) {
      return $this.prolog();
   }

   default Tuple3 prolog() {
      return this.prologOrTextDecl(true);
   }

   // $FF: synthetic method
   static Tuple2 textDecl$(final MarkupParser $this) {
      return $this.textDecl();
   }

   default Tuple2 textDecl() {
      Tuple3 var2 = this.prologOrTextDecl(false);
      if (var2 != null) {
         Option x1 = (Option)var2._1();
         Option x2 = (Option)var2._2();
         return new Tuple2(x1, x2);
      } else {
         throw new MatchError(var2);
      }
   }

   // $FF: synthetic method
   static Document document$(final MarkupParser $this) {
      return $this.document();
   }

   default Document document() {
      this.doc_$eq(new Document());
      this.dtd_$eq((DTD)null);
      new Tuple3(scala.None..MODULE$, scala.None..MODULE$, scala.None..MODULE$);
      if ('<' != this.ch()) {
         this.reportSyntaxError("< expected");
         return null;
      } else {
         this.nextch();
         NodeSeq children = null;
         if ('?' == this.ch()) {
            this.nextch();
            Tuple3 info_prolog = this.prolog();
            this.doc().version_$eq((Option)info_prolog._1());
            this.doc().encoding_$eq((Option)info_prolog._2());
            this.doc().standAlone_$eq((Option)info_prolog._3());
            children = this.content(TopScope$.MODULE$);
         } else {
            NodeBuffer ts = new NodeBuffer();
            this.content1(TopScope$.MODULE$, ts);
            ts.$amp$plus(this.content(TopScope$.MODULE$));
            children = NodeSeq$.MODULE$.fromSeq(ts);
         }

         IntRef elemCount = IntRef.create(0);
         ObjectRef theNode = ObjectRef.create((Object)null);
         children.foreach((c) -> {
            $anonfun$document$1(this, elemCount, theNode, c);
            return BoxedUnit.UNIT;
         });
         if (1 != elemCount.elem) {
            this.reportSyntaxError("document must contain exactly one element");
         }

         this.doc().children_$eq(children);
         this.doc().docElem_$eq((Node)theNode.elem);
         return this.doc();
      }
   }

   // $FF: synthetic method
   static StringBuilder putChar$(final MarkupParser $this, final char c) {
      return $this.putChar(c);
   }

   default StringBuilder putChar(final char c) {
      return this.cbuf().append(c);
   }

   // $FF: synthetic method
   static MarkupHandler initialize$(final MarkupParser $this) {
      return $this.initialize();
   }

   default MarkupHandler initialize() {
      this.nextch();
      return (MarkupHandler)this;
   }

   // $FF: synthetic method
   static char ch_returning_nextch$(final MarkupParser $this) {
      return $this.ch_returning_nextch();
   }

   default char ch_returning_nextch() {
      char res = this.ch();
      this.nextch();
      return res;
   }

   // $FF: synthetic method
   static Tuple2 mkAttributes$(final MarkupParser $this, final String name, final NamespaceBinding pscope) {
      return $this.mkAttributes(name, pscope);
   }

   default Tuple2 mkAttributes(final String name, final NamespaceBinding pscope) {
      return this.isNameStart(this.ch()) ? this.xAttributes(pscope) : new Tuple2(Null$.MODULE$, pscope);
   }

   // $FF: synthetic method
   static NodeSeq mkProcInstr$(final MarkupParser $this, final int position, final String name, final String text) {
      return $this.mkProcInstr(position, name, text);
   }

   default NodeSeq mkProcInstr(final int position, final String name, final String text) {
      return this.scala$xml$parsing$MarkupParser$$handle().procInstr(position, name, text);
   }

   // $FF: synthetic method
   static void nextch$(final MarkupParser $this) {
      $this.nextch();
   }

   default void nextch() {
      this.ch();
      this.nextChNeeded_$eq(true);
   }

   // $FF: synthetic method
   static Tuple2 xAttributes$(final MarkupParser $this, final NamespaceBinding pscope) {
      return $this.xAttributes(pscope);
   }

   default Tuple2 xAttributes(final NamespaceBinding pscope) {
      NamespaceBinding scope = pscope;
      MetaData aMap = Null$.MODULE$;

      while(this.isNameStart(this.ch())) {
         label46: {
            String qname = this.xName();
            this.xEQ();
            String value = this.xAttributeValue();
            boolean var7 = false;
            Some var8 = null;
            Option var9 = Utility$.MODULE$.prefix(qname);
            if (var9 instanceof Some) {
               var7 = true;
               var8 = (Some)var9;
               String var10 = (String)var8.value();
               if ("xmlns".equals(var10)) {
                  String prefix = qname.substring(6, qname.length());
                  scope = new NamespaceBinding(prefix, value, scope);
                  BoxedUnit var17 = BoxedUnit.UNIT;
                  break label46;
               }
            }

            if (var7) {
               String prefix = (String)var8.value();
               String key = qname.substring(prefix.length() + 1, qname.length());
               aMap = new PrefixedAttribute(prefix, key, Text$.MODULE$.apply(value), aMap);
               BoxedUnit var10000 = BoxedUnit.UNIT;
            } else {
               label41: {
                  label40: {
                     String var14 = "xmlns";
                     if (qname == null) {
                        if (var14 == null) {
                           break label40;
                        }
                     } else if (qname.equals(var14)) {
                        break label40;
                     }

                     aMap = new UnprefixedAttribute(qname, Text$.MODULE$.apply(value), aMap);
                     BoxedUnit var15 = BoxedUnit.UNIT;
                     break label41;
                  }

                  scope = new NamespaceBinding((String)null, value, scope);
                  BoxedUnit var16 = BoxedUnit.UNIT;
               }
            }
         }

         if (this.ch() != '/' && this.ch() != '>' && '?' != this.ch()) {
            this.xSpace();
         }
      }

      if (!aMap.wellformed(scope)) {
         this.reportSyntaxError("double attribute");
      }

      return new Tuple2(aMap.reverse(), scope);
   }

   // $FF: synthetic method
   static String xEntityValue$(final MarkupParser $this) {
      return $this.xEntityValue();
   }

   default String xEntityValue() {
      char endch = this.ch();
      this.nextch();

      while(this.ch() != endch && !this.eof()) {
         this.putChar(this.ch());
         this.nextch();
      }

      this.nextch();
      String str = this.cbuf().toString();
      this.cbuf().setLength(0);
      return str;
   }

   // $FF: synthetic method
   static NodeSeq xCharData$(final MarkupParser $this) {
      return $this.xCharData();
   }

   default NodeSeq xCharData() {
      this.xToken(.MODULE$.wrapString("[CDATA["));
      return (NodeSeq)this.xTakeUntil((pos, s) -> $anonfun$xCharData$1(this, BoxesRunTime.unboxToInt(pos), s), (JFunction0.mcI.sp)() -> this.pos(), "]]>");
   }

   // $FF: synthetic method
   static NodeSeq xComment$(final MarkupParser $this) {
      return $this.xComment();
   }

   default NodeSeq xComment() {
      StringBuilder sb = new StringBuilder();
      this.xToken(.MODULE$.wrapString("--"));

      while(!this.eof()) {
         if (this.ch() == '-') {
            sb.append(this.ch());
            this.nextch();
            if (this.ch() == '-') {
               sb.setLength(sb.length() - 1);
               this.nextch();
               this.xToken('>');
               return this.scala$xml$parsing$MarkupParser$$handle().comment(this.pos(), sb.toString());
            }
         }

         sb.append(this.ch());
         this.nextch();
      }

      throw this.truncatedError("broken comment");
   }

   // $FF: synthetic method
   static void appendText$(final MarkupParser $this, final int pos, final NodeBuffer ts, final String txt) {
      $this.appendText(pos, ts, txt);
   }

   default void appendText(final int pos, final NodeBuffer ts, final String txt) {
      if (this.preserveWS()) {
         ts.$amp$plus(this.scala$xml$parsing$MarkupParser$$handle().text(pos, txt));
      } else {
         TextBuffer$.MODULE$.fromString(txt).toText().foreach((t) -> ts.$amp$plus(this.scala$xml$parsing$MarkupParser$$handle().text(pos, t.text())));
      }
   }

   // $FF: synthetic method
   static void content1$(final MarkupParser $this, final NamespaceBinding pscope, final NodeBuffer ts) {
      $this.content1(pscope, ts);
   }

   default void content1(final NamespaceBinding pscope, final NodeBuffer ts) {
      char var3 = this.ch();
      switch (var3) {
         case '!':
            this.nextch();
            if ('[' == this.ch()) {
               ts.$amp$plus(this.xCharData());
               return;
            } else {
               if ('D' == this.ch()) {
                  this.parseDTD();
                  return;
               }

               ts.$amp$plus(this.xComment());
               return;
            }
         case '?':
            this.nextch();
            ts.$amp$plus(this.xProcInstr());
            return;
         default:
            ts.$amp$plus(this.element1(pscope));
      }
   }

   // $FF: synthetic method
   static NodeSeq content$(final MarkupParser $this, final NamespaceBinding pscope) {
      return $this.content(pscope);
   }

   default NodeSeq content(final NamespaceBinding pscope) {
      NodeBuffer ts = new NodeBuffer();
      boolean exit = this.eof();

      while(!exit) {
         this.tmppos_$eq(this.pos());
         exit = this.eof();
         if (this.eof()) {
            return done$1(ts);
         }

         char var4 = this.ch();
         switch (var4) {
            case '&':
               this.nextch();
               char var6 = this.ch();
               switch (var6) {
                  case '#':
                     this.nextch();
                     NodeSeq theChar = this.scala$xml$parsing$MarkupParser$$handle().text(this.tmppos(), this.xCharRef((JFunction0.mcC.sp)() -> this.ch(), (JFunction0.mcV.sp)() -> this.nextch()));
                     this.xToken(';');
                     ts.$amp$plus(theChar);
                     continue;
                  default:
                     String n = this.xName();
                     this.xToken(';');
                     if (Utility.Escapes$.MODULE$.pairs().contains(n)) {
                        this.scala$xml$parsing$MarkupParser$$handle().entityRef(this.tmppos(), n);
                        ts.$amp$plus(Utility.Escapes$.MODULE$.pairs().apply(n));
                     } else {
                        this.push(n);
                        BoxedUnit var9 = BoxedUnit.UNIT;
                     }
                     continue;
               }
            case '<':
               this.nextch();
               char var5 = this.ch();
               switch (var5) {
                  case '/':
                     exit = true;
                     break;
                  default:
                     this.content1(pscope, ts);
               }

               BoxedUnit var10000 = BoxedUnit.UNIT;
               break;
            default:
               this.appendText(this.tmppos(), ts, this.xText());
               BoxedUnit var10 = BoxedUnit.UNIT;
         }
      }

      return done$1(ts);
   }

   // $FF: synthetic method
   static ExternalID externalID$(final MarkupParser $this) {
      return $this.externalID();
   }

   default ExternalID externalID() {
      char var1 = this.ch();
      switch (var1) {
         case 'P':
            this.nextch();
            this.xToken(.MODULE$.wrapString("UBLIC"));
            this.xSpace();
            String pubID = this.pubidLiteral();
            this.xSpace();
            String sysID = this.systemLiteral();
            return new PublicID(pubID, sysID);
         case 'S':
            this.nextch();
            this.xToken(.MODULE$.wrapString("YSTEM"));
            this.xSpace();
            String sysID = this.systemLiteral();
            return new SystemID(sysID);
         default:
            throw new MatchError(BoxesRunTime.boxToCharacter(var1));
      }
   }

   // $FF: synthetic method
   static void parseDTD$(final MarkupParser $this) {
      $this.parseDTD();
   }

   default void parseDTD() {
      ObjectRef extID = ObjectRef.create((Object)null);
      if (this.dtd() != null) {
         this.reportSyntaxError("unexpected character (DOCTYPE already defined");
      }

      this.xToken(.MODULE$.wrapString("DOCTYPE"));
      this.xSpace();
      String n = this.xName();
      this.xSpaceOpt();
      if ('S' == this.ch() || 'P' == this.ch()) {
         extID.elem = this.externalID();
         this.xSpaceOpt();
      }

      if ((ExternalID)extID.elem != null && ((MarkupHandler)this).isValidating()) {
         this.pushExternal(((ExternalID)extID.elem).systemId());
         this.extIndex_$eq(this.inpStack().length());
         this.extSubset();
         this.pop();
         this.extIndex_$eq(-1);
      }

      if ('[' == this.ch()) {
         this.nextch();
         this.intSubset();
         this.xToken(']');
         this.xSpaceOpt();
      }

      this.xToken('>');
      this.dtd_$eq(new DTD(extID) {
         public {
            this.externalID_$eq((ExternalID)extID$1.elem);
            this.decls_$eq(((MarkupParser)MarkupParser.this).scala$xml$parsing$MarkupParser$$handle().decls().reverse());
         }
      });
      if (this.doc() != null) {
         this.doc().dtd_$eq(this.dtd());
      }

      this.scala$xml$parsing$MarkupParser$$handle().endDTD(n);
   }

   // $FF: synthetic method
   static NodeSeq element$(final MarkupParser $this, final NamespaceBinding pscope) {
      return $this.element(pscope);
   }

   default NodeSeq element(final NamespaceBinding pscope) {
      this.xToken('<');
      return this.element1(pscope);
   }

   // $FF: synthetic method
   static NodeSeq element1$(final MarkupParser $this, final NamespaceBinding pscope) {
      return $this.element1(pscope);
   }

   default NodeSeq element1(final NamespaceBinding pscope) {
      int pos = this.pos();
      Tuple2 var6 = this.xTag(pscope);
      if (var6 != null) {
         String qname = (String)var6._1();
         Tuple2 var8 = (Tuple2)var6._2();
         if (qname != null && var8 != null) {
            MetaData aMap = (MetaData)var8._1();
            NamespaceBinding scope = (NamespaceBinding)var8._2();
            if (aMap != null && scope != null) {
               Tuple3 var5 = new Tuple3(qname, aMap, scope);
               String qname = (String)var5._1();
               MetaData aMap = (MetaData)var5._2();
               NamespaceBinding scope = (NamespaceBinding)var5._3();
               Tuple2 var18 = Utility$.MODULE$.splitName(qname);
               if (var18 != null) {
                  Option pre = (Option)var18._1();
                  String local = (String)var18._2();
                  if (pre != null && local != null) {
                     Tuple2 var17 = new Tuple2(pre, local);
                     Option pre = (Option)var17._1();
                     String local = (String)var17._2();
                     NodeSeq var10000;
                     if (this.ch() == '/') {
                        this.xToken(.MODULE$.wrapString("/>"));
                        this.scala$xml$parsing$MarkupParser$$handle().elemStart(pos, (String)pre.orNull(scala..less.colon.less..MODULE$.refl()), local, aMap, scope);
                        var10000 = NodeSeq$.MODULE$.Empty();
                     } else {
                        this.xToken('>');
                        this.scala$xml$parsing$MarkupParser$$handle().elemStart(pos, (String)pre.orNull(scala..less.colon.less..MODULE$.refl()), local, aMap, scope);
                        NodeSeq tmp = this.content(scope);
                        this.xEndTag(qname);
                        var10000 = tmp;
                     }

                     NodeSeq ts;
                     String var10002;
                     boolean var10006;
                     label41: {
                        label40: {
                           ts = var10000;
                           var29 = this.scala$xml$parsing$MarkupParser$$handle();
                           var10002 = (String)pre.orNull(scala..less.colon.less..MODULE$.refl());
                           NodeSeq var28 = NodeSeq$.MODULE$.Empty();
                           if (ts == null) {
                              if (var28 == null) {
                                 break label40;
                              }
                           } else if (ts.equals(var28)) {
                              break label40;
                           }

                           var10006 = false;
                           break label41;
                        }

                        var10006 = true;
                     }

                     NodeSeq res = var29.elem(pos, var10002, local, aMap, scope, var10006, ts);
                     this.scala$xml$parsing$MarkupParser$$handle().elemEnd(pos, (String)pre.orNull(scala..less.colon.less..MODULE$.refl()), local);
                     return res;
                  }
               }

               throw new MatchError(var18);
            }
         }
      }

      throw new MatchError(var6);
   }

   private String xText() {
      for(boolean exit = false; !exit; exit = this.eof() || this.ch() == '<' || this.ch() == '&') {
         this.putChar(this.ch());
         this.nextch();
      }

      String str = this.cbuf().toString();
      this.cbuf().setLength(0);
      return str;
   }

   // $FF: synthetic method
   static String systemLiteral$(final MarkupParser $this) {
      return $this.systemLiteral();
   }

   default String systemLiteral() {
      char endch = this.ch();
      if (this.ch() != '\'' && this.ch() != '"') {
         this.reportSyntaxError("quote ' or \" expected");
      }

      this.nextch();

      while(this.ch() != endch && !this.eof()) {
         this.putChar(this.ch());
         this.nextch();
      }

      this.nextch();
      String str = this.cbuf().toString();
      this.cbuf().setLength(0);
      return str;
   }

   // $FF: synthetic method
   static String pubidLiteral$(final MarkupParser $this) {
      return $this.pubidLiteral();
   }

   default String pubidLiteral() {
      char endch = this.ch();
      if (this.ch() != '\'' && this.ch() != '"') {
         this.reportSyntaxError("quote ' or \" expected");
      }

      this.nextch();

      for(; this.ch() != endch && !this.eof(); this.nextch()) {
         this.putChar(this.ch());
         if (!this.isPubIDChar(this.ch())) {
            this.reportSyntaxError((new java.lang.StringBuilder(35)).append("char '").append(this.ch()).append("' is not allowed in public id").toString());
         }
      }

      this.nextch();
      String str = this.cbuf().toString();
      this.cbuf().setLength(0);
      return str;
   }

   // $FF: synthetic method
   static void extSubset$(final MarkupParser $this) {
      $this.extSubset();
   }

   default void extSubset() {
      Tuple2 textdecl = null;
      if (this.ch() == '<') {
         this.nextch();
         if (this.ch() == '?') {
            this.nextch();
            textdecl = this.textDecl();
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            this.markupDecl1();
         }
      } else {
         BoxedUnit var3 = BoxedUnit.UNIT;
      }

      while(!this.eof()) {
         this.markupDecl();
      }

   }

   // $FF: synthetic method
   static Object markupDecl1$(final MarkupParser $this) {
      return $this.markupDecl1();
   }

   default Object markupDecl1() {
      if ('?' == this.ch()) {
         this.nextch();
         return this.xProcInstr();
      } else {
         this.xToken('!');
         char var1 = this.ch();
         switch (var1) {
            case '-':
               return this.xComment();
            case 'A':
               this.nextch();
               this.attrDecl();
               return BoxedUnit.UNIT;
            case 'E':
               this.nextch();
               if ('L' == this.ch()) {
                  this.nextch();
                  this.elementDecl();
                  return BoxedUnit.UNIT;
               }

               this.entityDecl();
               return BoxedUnit.UNIT;
            case 'N':
               this.nextch();
               this.notationDecl();
               return BoxedUnit.UNIT;
            case '[':
               if (this.inpStack().length() >= this.extIndex()) {
                  this.nextch();
                  this.xSpaceOpt();
                  char var2 = this.ch();
                  label55:
                  switch (var2) {
                     case '%':
                        this.nextch();
                        String ent = this.xName();
                        this.xToken(';');
                        this.xSpaceOpt();
                        this.push(ent);
                        this.xSpaceOpt();
                        String stmt = this.xName();
                        this.xSpaceOpt();
                        switch (stmt == null ? 0 : stmt.hashCode()) {
                           case -2137067054:
                              if (!"IGNORE".equals(stmt)) {
                                 throw new MatchError(stmt);
                              }

                              this.doIgnore$1();
                              break label55;
                           case -1634410360:
                              if ("INCLUDE".equals(stmt)) {
                                 this.doInclude$1();
                                 break label55;
                              }

                              throw new MatchError(stmt);
                           default:
                              throw new MatchError(stmt);
                        }
                     case 'I':
                        this.nextch();
                        char var6 = this.ch();
                        switch (var6) {
                           case 'G':
                              this.nextch();
                              this.xToken(.MODULE$.wrapString("NORE"));
                              this.xSpaceOpt();
                              this.doIgnore$1();
                              break label55;
                           case 'N':
                              this.nextch();
                              this.xToken(.MODULE$.wrapString("NCLUDE"));
                              this.doInclude$1();
                              break label55;
                           default:
                              throw new MatchError(BoxesRunTime.boxToCharacter(var6));
                        }
                     default:
                        throw new MatchError(BoxesRunTime.boxToCharacter(var2));
                  }

                  this.xToken(']');
                  this.xToken('>');
                  return BoxedUnit.UNIT;
               }
            default:
               Source qual$1 = this.curInput();
               int x$1 = this.pos();
               String x$2 = (new java.lang.StringBuilder(49)).append("unexpected character '").append(this.ch()).append("', expected some markupdecl").toString();
               PrintStream x$3 = qual$1.reportError$default$3();
               qual$1.reportError(x$1, x$2, x$3);

               while(this.ch() != '>' && !this.eof()) {
                  this.nextch();
               }

               return BoxedUnit.UNIT;
         }
      }
   }

   // $FF: synthetic method
   static void markupDecl$(final MarkupParser $this) {
      $this.markupDecl();
   }

   default void markupDecl() {
      char var1 = this.ch();
      switch (var1) {
         case '%':
            this.nextch();
            String ent = this.xName();
            this.xToken(';');
            if (!((MarkupHandler)this).isValidating()) {
               this.scala$xml$parsing$MarkupParser$$handle().peReference(ent);
               return;
            }

            this.push(ent);
            return;
         case '<':
            this.nextch();
            this.markupDecl1();
            return;
         default:
            if (this.isSpace(this.ch())) {
               this.xSpace();
            } else {
               this.reportSyntaxError((new java.lang.StringBuilder(37)).append("markupdecl: unexpected character '").append(this.ch()).append("' #").append(this.ch()).toString());
               this.nextch();
            }
      }
   }

   // $FF: synthetic method
   static void intSubset$(final MarkupParser $this) {
      $this.intSubset();
   }

   default void intSubset() {
      this.xSpace();

      while(']' != this.ch() && !this.eof()) {
         this.markupDecl();
      }

   }

   // $FF: synthetic method
   static void elementDecl$(final MarkupParser $this) {
      $this.elementDecl();
   }

   default void elementDecl() {
      this.xToken(.MODULE$.wrapString("EMENT"));
      this.xSpace();
      String n = this.xName();
      this.xSpace();

      while('>' != this.ch() && !this.eof()) {
         this.putChar(this.ch());
         this.nextch();
      }

      this.nextch();
      String cmstr = this.cbuf().toString();
      this.cbuf().setLength(0);
      this.scala$xml$parsing$MarkupParser$$handle().elemDecl(n, cmstr);
   }

   // $FF: synthetic method
   static void attrDecl$(final MarkupParser $this) {
      $this.attrDecl();
   }

   default void attrDecl() {
      this.xToken(.MODULE$.wrapString("TTLIST"));
      this.xSpace();
      String n = this.xName();
      this.xSpace();
      List attList = scala.collection.immutable.Nil..MODULE$;

      while('>' != this.ch() && !this.eof()) {
         String aname = this.xName();
         this.xSpace();

         for(; '"' != this.ch() && '\'' != this.ch() && '#' != this.ch() && '<' != this.ch(); this.nextch()) {
            if (!this.isSpace(this.ch())) {
               this.cbuf().append(this.ch());
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }
         }

         String atpe;
         Object var9;
         atpe = this.cbuf().toString();
         this.cbuf().setLength(0);
         char var7 = this.ch();
         label52:
         switch (var7) {
            case '"':
            case '\'':
               var9 = new DEFAULT(false, this.xAttributeValue());
               break;
            case '#':
               this.nextch();
               String var8 = this.xName();
               switch (var8 == null ? 0 : var8.hashCode()) {
                  case -1651045240:
                     if (!"IMPLIED".equals(var8)) {
                        throw new MatchError(var8);
                     }

                     var9 = IMPLIED$.MODULE$;
                     break label52;
                  case 66907988:
                     if (!"FIXED".equals(var8)) {
                        throw new MatchError(var8);
                     }

                     this.xSpace();
                     var9 = new DEFAULT(true, this.xAttributeValue());
                     break label52;
                  case 389487519:
                     if ("REQUIRED".equals(var8)) {
                        var9 = REQUIRED$.MODULE$;
                        break label52;
                     }

                     throw new MatchError(var8);
                  default:
                     throw new MatchError(var8);
               }
            default:
               var9 = null;
         }

         DefaultDecl defdecl = (DefaultDecl)var9;
         this.xSpaceOpt();
         attList = attList.$colon$colon(new AttrDecl(aname, atpe, defdecl));
         this.cbuf().setLength(0);
      }

      this.nextch();
      this.scala$xml$parsing$MarkupParser$$handle().attListDecl(n, attList.reverse());
   }

   // $FF: synthetic method
   static void entityDecl$(final MarkupParser $this) {
      $this.entityDecl();
   }

   default void entityDecl() {
      boolean isParameterEntity = false;
      this.xToken(.MODULE$.wrapString("NTITY"));
      this.xSpace();
      if ('%' == this.ch()) {
         this.nextch();
         isParameterEntity = true;
         this.xSpace();
      }

      String n = this.xName();
      this.xSpace();
      char var3 = this.ch();
      switch (var3) {
         case '"':
         case '\'':
            String av = this.xEntityValue();
            this.xSpaceOpt();
            this.xToken('>');
            if (isParameterEntity) {
               this.scala$xml$parsing$MarkupParser$$handle().parameterEntityDecl(n, new IntDef(av));
            } else {
               this.scala$xml$parsing$MarkupParser$$handle().parsedEntityDecl(n, new IntDef(av));
            }
            break;
         case 'P':
         case 'S':
            ExternalID extID = this.externalID();
            if (isParameterEntity) {
               this.xSpaceOpt();
               this.xToken('>');
               this.scala$xml$parsing$MarkupParser$$handle().parameterEntityDecl(n, new ExtDef(extID));
            } else {
               this.xSpace();
               if ('>' != this.ch()) {
                  this.xToken(.MODULE$.wrapString("NDATA"));
                  this.xSpace();
                  String notat = this.xName();
                  this.xSpaceOpt();
                  this.xToken('>');
                  this.scala$xml$parsing$MarkupParser$$handle().unparsedEntityDecl(n, extID, notat);
               } else {
                  this.nextch();
                  this.scala$xml$parsing$MarkupParser$$handle().parsedEntityDecl(n, new ExtDef(extID));
               }
            }
            break;
         default:
            throw new MatchError(BoxesRunTime.boxToCharacter(var3));
      }

   }

   // $FF: synthetic method
   static void notationDecl$(final MarkupParser $this) {
      $this.notationDecl();
   }

   default void notationDecl() {
      this.xToken(.MODULE$.wrapString("OTATION"));
      this.xSpace();
      String notat = this.xName();
      this.xSpace();
      Object var10000;
      if (this.ch() == 'S') {
         var10000 = this.externalID();
      } else {
         if (this.ch() != 'P') {
            this.reportSyntaxError("PUBLIC or SYSTEM expected");
            throw this.truncatedError("died parsing notationdecl");
         }

         this.nextch();
         this.xToken(.MODULE$.wrapString("UBLIC"));
         this.xSpace();
         String pubID = this.pubidLiteral();
         this.xSpaceOpt();
         String sysID = this.ch() != '>' ? this.systemLiteral() : null;
         var10000 = new PublicID(pubID, sysID);
      }

      ExternalID extID = (ExternalID)var10000;
      this.xSpaceOpt();
      this.xToken('>');
      this.scala$xml$parsing$MarkupParser$$handle().notationDecl(notat, extID);
   }

   // $FF: synthetic method
   static void reportSyntaxError$(final MarkupParser $this, final int pos, final String str) {
      $this.reportSyntaxError(pos, str);
   }

   default void reportSyntaxError(final int pos, final String str) {
      Source qual$1 = this.curInput();
      PrintStream x$3 = qual$1.reportError$default$3();
      qual$1.reportError(pos, str, x$3);
   }

   // $FF: synthetic method
   static void reportSyntaxError$(final MarkupParser $this, final String str) {
      $this.reportSyntaxError(str);
   }

   default void reportSyntaxError(final String str) {
      this.reportSyntaxError(this.pos(), str);
   }

   // $FF: synthetic method
   static void reportValidationError$(final MarkupParser $this, final int pos, final String str) {
      $this.reportValidationError(pos, str);
   }

   default void reportValidationError(final int pos, final String str) {
      this.reportSyntaxError(pos, str);
   }

   // $FF: synthetic method
   static void push$(final MarkupParser $this, final String entityName) {
      $this.push(entityName);
   }

   default void push(final String entityName) {
      if (!this.eof()) {
         Source var2 = this.curInput();
         this.inpStack_$eq(this.inpStack().$colon$colon(var2));
      }

      this.ch();
      this.curInput_$eq(((MarkupHandler)this).replacementText(entityName));
      this.nextch();
   }

   // $FF: synthetic method
   static void pushExternal$(final MarkupParser $this, final String systemId) {
      $this.pushExternal(systemId);
   }

   default void pushExternal(final String systemId) {
      if (!this.eof()) {
         Source var2 = this.curInput();
         this.inpStack_$eq(this.inpStack().$colon$colon(var2));
      }

      this.ch();
      this.curInput_$eq(this.externalSource(systemId));
      this.nextch();
   }

   // $FF: synthetic method
   static void pop$(final MarkupParser $this) {
      $this.pop();
   }

   default void pop() {
      this.curInput_$eq((Source)this.inpStack().head());
      this.inpStack_$eq((List)this.inpStack().tail());
      this.lastChRead_$eq(this.curInput().ch());
      this.nextChNeeded_$eq(false);
      this.pos_$eq(this.curInput().pos());
      this.reachedEof_$eq(false);
   }

   // $FF: synthetic method
   static void $anonfun$document$1(final MarkupParser $this, final IntRef elemCount$1, final ObjectRef theNode$1, final Node c) {
      if (c instanceof ProcInstr) {
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (c instanceof Comment) {
         BoxedUnit var11 = BoxedUnit.UNIT;
      } else if (c instanceof EntityRef) {
         $this.reportSyntaxError("no entity references allowed here");
         BoxedUnit var10 = BoxedUnit.UNIT;
      } else if (c instanceof SpecialNode) {
         SpecialNode var6 = (SpecialNode)c;
         if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(var6.toString().trim()))) {
            elemCount$1.elem += 2;
            BoxedUnit var9 = BoxedUnit.UNIT;
         } else {
            BoxedUnit var8 = BoxedUnit.UNIT;
         }
      } else if (c != null) {
         ++elemCount$1.elem;
         theNode$1.elem = c;
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(c);
      }
   }

   private NodeSeq mkResult$1(final int pos, final String s) {
      this.scala$xml$parsing$MarkupParser$$handle().text(pos, s);
      return PCData$.MODULE$.apply(s);
   }

   // $FF: synthetic method
   static NodeSeq $anonfun$xCharData$1(final MarkupParser $this, final int pos, final String s) {
      return $this.mkResult$1(pos, s);
   }

   private static NodeSeq done$1(final NodeBuffer ts$2) {
      return NodeSeq$.MODULE$.fromSeq(ts$2.toList());
   }

   private void doInclude$1() {
      this.xToken('[');

      while(']' != this.ch() && !this.eof()) {
         this.markupDecl();
      }

      this.nextch();
   }

   private void doIgnore$1() {
      this.xToken('[');

      while(']' != this.ch() && !this.eof()) {
         this.nextch();
      }

      this.nextch();
   }

   static void $init$(final MarkupParser $this) {
      $this.curInput_$eq($this.input());
      $this.scala$xml$parsing$MarkupParser$_setter_$scala$xml$parsing$MarkupParser$$handle_$eq((MarkupHandler)$this);
      $this.inpStack_$eq(scala.collection.immutable.Nil..MODULE$);
      $this.extIndex_$eq(-1);
      $this.nextChNeeded_$eq(false);
      $this.reachedEof_$eq(false);
      $this.scala$xml$parsing$MarkupParser$_setter_$cbuf_$eq(new StringBuilder());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class WithLookAhead extends Source {
      public final Source scala$xml$parsing$MarkupParser$WithLookAhead$$underlying;
      private final Queue scala$xml$parsing$MarkupParser$WithLookAhead$$queue;
      private final Iterator iter;
      // $FF: synthetic field
      public final MarkupHandler $outer;

      public Queue scala$xml$parsing$MarkupParser$WithLookAhead$$queue() {
         return this.scala$xml$parsing$MarkupParser$WithLookAhead$$queue;
      }

      public BufferedIterator lookahead() {
         Iterator iter = this.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().iterator().$plus$plus(() -> new Iterator() {
               // $FF: synthetic field
               private final WithLookAhead $outer;

               /** @deprecated */
               public final boolean hasDefiniteSize() {
                  return Iterator.hasDefiniteSize$(this);
               }

               public final Iterator iterator() {
                  return Iterator.iterator$(this);
               }

               public Option nextOption() {
                  return Iterator.nextOption$(this);
               }

               public boolean contains(final Object elem) {
                  return Iterator.contains$(this, elem);
               }

               public BufferedIterator buffered() {
                  return Iterator.buffered$(this);
               }

               public Iterator padTo(final int len, final Object elem) {
                  return Iterator.padTo$(this, len, elem);
               }

               public Tuple2 partition(final Function1 p) {
                  return Iterator.partition$(this, p);
               }

               public Iterator.GroupedIterator grouped(final int size) {
                  return Iterator.grouped$(this, size);
               }

               public Iterator.GroupedIterator sliding(final int size, final int step) {
                  return Iterator.sliding$(this, size, step);
               }

               public int sliding$default$2() {
                  return Iterator.sliding$default$2$(this);
               }

               public Iterator scanLeft(final Object z, final Function2 op) {
                  return Iterator.scanLeft$(this, z, op);
               }

               /** @deprecated */
               public Iterator scanRight(final Object z, final Function2 op) {
                  return Iterator.scanRight$(this, z, op);
               }

               public int indexWhere(final Function1 p, final int from) {
                  return Iterator.indexWhere$(this, p, from);
               }

               public int indexWhere$default$2() {
                  return Iterator.indexWhere$default$2$(this);
               }

               public int indexOf(final Object elem) {
                  return Iterator.indexOf$(this, elem);
               }

               public int indexOf(final Object elem, final int from) {
                  return Iterator.indexOf$(this, elem, from);
               }

               public final int length() {
                  return Iterator.length$(this);
               }

               public boolean isEmpty() {
                  return Iterator.isEmpty$(this);
               }

               public Iterator filter(final Function1 p) {
                  return Iterator.filter$(this, p);
               }

               public Iterator filterNot(final Function1 p) {
                  return Iterator.filterNot$(this, p);
               }

               public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
                  return Iterator.filterImpl$(this, p, isFlipped);
               }

               public Iterator withFilter(final Function1 p) {
                  return Iterator.withFilter$(this, p);
               }

               public Iterator collect(final PartialFunction pf) {
                  return Iterator.collect$(this, pf);
               }

               public Iterator distinct() {
                  return Iterator.distinct$(this);
               }

               public Iterator distinctBy(final Function1 f) {
                  return Iterator.distinctBy$(this, f);
               }

               public Iterator map(final Function1 f) {
                  return Iterator.map$(this, f);
               }

               public Iterator flatMap(final Function1 f) {
                  return Iterator.flatMap$(this, f);
               }

               public Iterator flatten(final Function1 ev) {
                  return Iterator.flatten$(this, ev);
               }

               public Iterator concat(final Function0 xs) {
                  return Iterator.concat$(this, xs);
               }

               public final Iterator $plus$plus(final Function0 xs) {
                  return Iterator.$plus$plus$(this, xs);
               }

               public Iterator take(final int n) {
                  return Iterator.take$(this, n);
               }

               public Iterator takeWhile(final Function1 p) {
                  return Iterator.takeWhile$(this, p);
               }

               public Iterator drop(final int n) {
                  return Iterator.drop$(this, n);
               }

               public Iterator dropWhile(final Function1 p) {
                  return Iterator.dropWhile$(this, p);
               }

               public Tuple2 span(final Function1 p) {
                  return Iterator.span$(this, p);
               }

               public Iterator slice(final int from, final int until) {
                  return Iterator.slice$(this, from, until);
               }

               public Iterator sliceIterator(final int from, final int until) {
                  return Iterator.sliceIterator$(this, from, until);
               }

               public Iterator zip(final IterableOnce that) {
                  return Iterator.zip$(this, that);
               }

               public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
                  return Iterator.zipAll$(this, that, thisElem, thatElem);
               }

               public Iterator zipWithIndex() {
                  return Iterator.zipWithIndex$(this);
               }

               public boolean sameElements(final IterableOnce that) {
                  return Iterator.sameElements$(this, that);
               }

               public Tuple2 duplicate() {
                  return Iterator.duplicate$(this);
               }

               public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
                  return Iterator.patch$(this, from, patchElems, replaced);
               }

               public Iterator tapEach(final Function1 f) {
                  return Iterator.tapEach$(this, f);
               }

               public String toString() {
                  return Iterator.toString$(this);
               }

               /** @deprecated */
               public Iterator seq() {
                  return Iterator.seq$(this);
               }

               public Tuple2 splitAt(final int n) {
                  return IterableOnceOps.splitAt$(this, n);
               }

               public boolean isTraversableAgain() {
                  return IterableOnceOps.isTraversableAgain$(this);
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

               public int size() {
                  return IterableOnceOps.size$(this);
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

               public Map toMap(final scala..less.colon.less ev) {
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

               public Stepper stepper(final StepperShape shape) {
                  return IterableOnce.stepper$(this, shape);
               }

               public int knownSize() {
                  return IterableOnce.knownSize$(this);
               }

               public boolean hasNext() {
                  return this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.hasNext();
               }

               public char next() {
                  char x = this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.next();
                  this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().$plus$eq(BoxesRunTime.boxToCharacter(x));
                  return x;
               }

               public {
                  if (WithLookAhead.this == null) {
                     throw null;
                  } else {
                     this.$outer = WithLookAhead.this;
                     IterableOnce.$init$(this);
                     IterableOnceOps.$init$(this);
                     Iterator.$init$(this);
                  }
               }
            });
         return iter.buffered();
      }

      public Iterator iter() {
         return this.iter;
      }

      // $FF: synthetic method
      public MarkupHandler scala$xml$parsing$MarkupParser$WithLookAhead$$$outer() {
         return this.$outer;
      }

      public WithLookAhead(final Source underlying) {
         this.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying = underlying;
         if (MarkupParser.this == null) {
            throw null;
         } else {
            this.$outer = MarkupParser.this;
            super();
            this.scala$xml$parsing$MarkupParser$WithLookAhead$$queue = (Queue)scala.collection.mutable.Queue..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
            this.iter = new Iterator() {
               // $FF: synthetic field
               private final WithLookAhead $outer;

               /** @deprecated */
               public final boolean hasDefiniteSize() {
                  return Iterator.hasDefiniteSize$(this);
               }

               public final Iterator iterator() {
                  return Iterator.iterator$(this);
               }

               public Option nextOption() {
                  return Iterator.nextOption$(this);
               }

               public boolean contains(final Object elem) {
                  return Iterator.contains$(this, elem);
               }

               public BufferedIterator buffered() {
                  return Iterator.buffered$(this);
               }

               public Iterator padTo(final int len, final Object elem) {
                  return Iterator.padTo$(this, len, elem);
               }

               public Tuple2 partition(final Function1 p) {
                  return Iterator.partition$(this, p);
               }

               public Iterator.GroupedIterator grouped(final int size) {
                  return Iterator.grouped$(this, size);
               }

               public Iterator.GroupedIterator sliding(final int size, final int step) {
                  return Iterator.sliding$(this, size, step);
               }

               public int sliding$default$2() {
                  return Iterator.sliding$default$2$(this);
               }

               public Iterator scanLeft(final Object z, final Function2 op) {
                  return Iterator.scanLeft$(this, z, op);
               }

               /** @deprecated */
               public Iterator scanRight(final Object z, final Function2 op) {
                  return Iterator.scanRight$(this, z, op);
               }

               public int indexWhere(final Function1 p, final int from) {
                  return Iterator.indexWhere$(this, p, from);
               }

               public int indexWhere$default$2() {
                  return Iterator.indexWhere$default$2$(this);
               }

               public int indexOf(final Object elem) {
                  return Iterator.indexOf$(this, elem);
               }

               public int indexOf(final Object elem, final int from) {
                  return Iterator.indexOf$(this, elem, from);
               }

               public final int length() {
                  return Iterator.length$(this);
               }

               public boolean isEmpty() {
                  return Iterator.isEmpty$(this);
               }

               public Iterator filter(final Function1 p) {
                  return Iterator.filter$(this, p);
               }

               public Iterator filterNot(final Function1 p) {
                  return Iterator.filterNot$(this, p);
               }

               public Iterator filterImpl(final Function1 p, final boolean isFlipped) {
                  return Iterator.filterImpl$(this, p, isFlipped);
               }

               public Iterator withFilter(final Function1 p) {
                  return Iterator.withFilter$(this, p);
               }

               public Iterator collect(final PartialFunction pf) {
                  return Iterator.collect$(this, pf);
               }

               public Iterator distinct() {
                  return Iterator.distinct$(this);
               }

               public Iterator distinctBy(final Function1 f) {
                  return Iterator.distinctBy$(this, f);
               }

               public Iterator map(final Function1 f) {
                  return Iterator.map$(this, f);
               }

               public Iterator flatMap(final Function1 f) {
                  return Iterator.flatMap$(this, f);
               }

               public Iterator flatten(final Function1 ev) {
                  return Iterator.flatten$(this, ev);
               }

               public Iterator concat(final Function0 xs) {
                  return Iterator.concat$(this, xs);
               }

               public final Iterator $plus$plus(final Function0 xs) {
                  return Iterator.$plus$plus$(this, xs);
               }

               public Iterator take(final int n) {
                  return Iterator.take$(this, n);
               }

               public Iterator takeWhile(final Function1 p) {
                  return Iterator.takeWhile$(this, p);
               }

               public Iterator drop(final int n) {
                  return Iterator.drop$(this, n);
               }

               public Iterator dropWhile(final Function1 p) {
                  return Iterator.dropWhile$(this, p);
               }

               public Tuple2 span(final Function1 p) {
                  return Iterator.span$(this, p);
               }

               public Iterator slice(final int from, final int until) {
                  return Iterator.slice$(this, from, until);
               }

               public Iterator sliceIterator(final int from, final int until) {
                  return Iterator.sliceIterator$(this, from, until);
               }

               public Iterator zip(final IterableOnce that) {
                  return Iterator.zip$(this, that);
               }

               public Iterator zipAll(final IterableOnce that, final Object thisElem, final Object thatElem) {
                  return Iterator.zipAll$(this, that, thisElem, thatElem);
               }

               public Iterator zipWithIndex() {
                  return Iterator.zipWithIndex$(this);
               }

               public boolean sameElements(final IterableOnce that) {
                  return Iterator.sameElements$(this, that);
               }

               public Tuple2 duplicate() {
                  return Iterator.duplicate$(this);
               }

               public Iterator patch(final int from, final Iterator patchElems, final int replaced) {
                  return Iterator.patch$(this, from, patchElems, replaced);
               }

               public Iterator tapEach(final Function1 f) {
                  return Iterator.tapEach$(this, f);
               }

               public String toString() {
                  return Iterator.toString$(this);
               }

               /** @deprecated */
               public Iterator seq() {
                  return Iterator.seq$(this);
               }

               public Tuple2 splitAt(final int n) {
                  return IterableOnceOps.splitAt$(this, n);
               }

               public boolean isTraversableAgain() {
                  return IterableOnceOps.isTraversableAgain$(this);
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

               public int size() {
                  return IterableOnceOps.size$(this);
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

               public Map toMap(final scala..less.colon.less ev) {
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

               public Stepper stepper(final StepperShape shape) {
                  return IterableOnce.stepper$(this, shape);
               }

               public int knownSize() {
                  return IterableOnce.knownSize$(this);
               }

               public boolean hasNext() {
                  return this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.hasNext() || this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().nonEmpty();
               }

               public char next() {
                  return this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().nonEmpty() ? BoxesRunTime.unboxToChar(this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$queue().dequeue()) : this.$outer.scala$xml$parsing$MarkupParser$WithLookAhead$$underlying.next();
               }

               public {
                  if (WithLookAhead.this == null) {
                     throw null;
                  } else {
                     this.$outer = WithLookAhead.this;
                     IterableOnce.$init$(this);
                     IterableOnceOps.$init$(this);
                     Iterator.$init$(this);
                  }
               }
            };
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
