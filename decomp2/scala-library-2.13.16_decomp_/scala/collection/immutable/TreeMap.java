package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Option$;
import scala.PartialFunction;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SortedMapFactory;
import scala.collection.SortedMapFactoryDefaults;
import scala.collection.SortedOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.convert.impl.AnyBinaryTreeStepper;
import scala.collection.convert.impl.AnyBinaryTreeStepper$;
import scala.collection.convert.impl.BinaryTreeStepper$;
import scala.collection.convert.impl.DoubleBinaryTreeStepper;
import scala.collection.convert.impl.DoubleBinaryTreeStepper$;
import scala.collection.convert.impl.IntBinaryTreeStepper;
import scala.collection.convert.impl.IntBinaryTreeStepper$;
import scala.collection.convert.impl.LongBinaryTreeStepper;
import scala.collection.convert.impl.LongBinaryTreeStepper$;
import scala.collection.generic.DefaultSerializable;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.ReusableBuilder;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011}g\u0001\u0002,X\u0005yC!\"a\u0006\u0001\u0005\u000b\u0007I\u0011BA\r\u0011)\tI\u0003\u0001B\u0001B\u0003%\u00111\u0004\u0005\u000b\u0003W\u0001!Q1A\u0005\u0004\u00055\u0002BCA\u001f\u0001\t\u0005\t\u0015!\u0003\u00020!9\u0011q\b\u0001\u0005\n\u0005\u0005\u0003bBA \u0001\u0011\u0005\u0011\u0011\n\u0005\t\u0003\u001f\u0002A\u0011A,\u0002\u001a!A\u0011\u0011\u000b\u0001!\n\u0013\t\u0019\u0006C\u0004\u0002h\u0001!\t%!\u001b\t\u000f\u0005E\u0004\u0001\"\u0001\u0002t!9\u0011\u0011\u0011\u0001\u0005\u0002\u0005\r\u0005bBAF\u0001\u0011\u0005\u0013Q\u0012\u0005\b\u0003+\u0003A\u0011AAL\u0011\u001d\tY\n\u0001C!\u0003;Cq!a)\u0001\t\u0003\n)\u000bC\u0004\u0002l\u0002!\t%!<\t\u000f\t%\u0001\u0001\"\u0011\u0003\f!9!q\u0005\u0001\u0005\u0002\t%\u0002b\u0002B\u001b\u0001\u0011\u0005#q\u0007\u0005\b\u0005\u0017\u0002A\u0011\u0001B'\u0011\u001d\u0011\t\u0006\u0001C\u0001\u0005'BqAa\u0019\u0001\t\u0003\u0012)\u0007C\u0004\u0003|\u0001!\tE! \t\u000f\t\u0015\u0005\u0001\"\u0001\u0003\b\"9!\u0011\u0016\u0001\u0005\u0002\t-\u0006b\u0002B\\\u0001\u0011\u0005#\u0011\u0018\u0005\b\u0005\u007f\u0003A\u0011\tBa\u0011\u001d\u0011)\r\u0001C!\u0005\u000fDqA!4\u0001\t\u0003\u0012y\rC\u0004\u0003j\u0002!\tEa;\t\u000f\tm\b\u0001\"\u0011\u0003~\"91Q\u0001\u0001\u0005B\tu\bbBB\u0004\u0001\u0011\u00053\u0011\u0002\u0005\b\u0007#\u0001A\u0011IB\n\u0011\u001d\u0019)\u0002\u0001C!\u0007'Aqaa\u0006\u0001\t\u0003\u001aI\u0002C\u0004\u0004\u001c\u0001!\te!\u0007\t\u000f\ru\u0001\u0001\"\u0011\u0004 !91\u0011\u0005\u0001\u0005B\r}\u0001bBB\u0012\u0001\u0011\u00053Q\u0005\u0005\b\u0007W\u0001A\u0011IB\u0017\u0011\u001d\u0019\t\u0004\u0001C!\u0007gAqa!\u000f\u0001\t\u0003\u001aY\u0004C\u0004\u0004@\u0001!\te!\u0011\t\u0011\r\u0015\u0003\u0001)C\u0005\u0007\u000fBqaa\u0014\u0001\t\u0003\u001a\t\u0006C\u0004\u0004V\u0001!\tea\u0016\t\u000f\rm\u0003\u0001\"\u0011\u0004^!911\r\u0001\u0005B\r\u0015\u0004bBB5\u0001\u0011\u000531\u000e\u0005\b\u0007_\u0002A\u0011IB9\r\u0019\u0019\t\t\u0001\u0004\u0004\u0004\"9\u0011q\b\u001b\u0005\u0002\r]\u0005\"CBOi\u0001\u0007I\u0011BBP\u0011%\u0019\u0019\u000b\u000ea\u0001\n\u0013\u0019)\u000b\u0003\u0005\u0004,R\u0002\u000b\u0015BBQ\u0011\u001d\u0019i\u000b\u000eC\u0001\u0007?Cqaa,5\t\u0003\u001a\t\fC\u0004\u00048R\"\ta!/\t\u000f\rM\u0007\u0001\"\u0011\u0004V\"A11\u001c\u0001!\n#\u001ainB\u0004\u0004p^C\ta!=\u0007\rY;\u0006\u0012ABz\u0011\u001d\tyd\u0010C\u0001\u0007wDqa!@@\t\u0003\u0019y\u0010C\u0004\u00030~\"\t\u0001b\u0005\t\u000f\u0011=r\b\"\u0001\u00052\u00191A\u0011K \u0005\t'BA\"a\u000bE\u0005\u0003\u0005\u000b1\u0002C4\tSBq!a\u0010E\t\u0003!y'\u0002\u0004\u0002&\u0011\u0003A\u0011\u0010\u0005\n\u0003/!\u0005\u0019!C\u0005\twB\u0011\u0002\"!E\u0001\u0004%I\u0001b!\t\u0011\u0005%B\t)Q\u0005\t{Bq\u0001b\"E\t\u0003!IiB\u0004\u0005\u0010\u0012CI\u0001\"%\u0007\u000f\u0011ME\t#\u0003\u0005\u0016\"9\u0011qH'\u0005\u0002\u0011\r\u0006\u0002\u0003CS\u001b\u0002\u0006K\u0001\" \t\u000f\u0011\u001dV\n\"\u0001\u0005*\"91qV'\u0005B\u0011M\u0006bBB\\\t\u0012\u0005C\u0011\u0018\u0005\b\t\u0003$E\u0011\tCb\u0011\u001d!)\r\u0012C!\t\u000fD\u0011\u0002\"3@\u0003\u0003%I\u0001b3\u0003\u000fQ\u0013X-Z'ba*\u0011\u0001,W\u0001\nS6lW\u000f^1cY\u0016T!AW.\u0002\u0015\r|G\u000e\\3di&|gNC\u0001]\u0003\u0015\u00198-\u00197b\u0007\u0001)2a\u00184r'\u001d\u0001\u0001m\u001d<|\u0003\u0017\u0001B!\u00192ea6\tq+\u0003\u0002d/\nY\u0011IY:ue\u0006\u001cG/T1q!\t)g\r\u0004\u0001\u0005\u000b\u001d\u0004!\u0019\u00015\u0003\u0003-\u000b\"![7\u0011\u0005)\\W\"A.\n\u00051\\&a\u0002(pi\"Lgn\u001a\t\u0003U:L!a\\.\u0003\u0007\u0005s\u0017\u0010\u0005\u0002fc\u00121!\u000f\u0001CC\u0002!\u0014\u0011A\u0016\t\u0005CR$\u0007/\u0003\u0002v/\nI1k\u001c:uK\u0012l\u0015\r\u001d\t\u0007C^$\u0007/\u001f>\n\u0005a<&aG*ue&\u001cGo\u00149uS6L'0\u001a3T_J$X\rZ'ba>\u00038\u000f\u0005\u0002b\u0001A!\u0011\r\u00013q!!aX\u0010\u001a9z\u007f\u0006\u0015Q\"A-\n\u0005yL&\u0001G*peR,G-T1q\r\u0006\u001cGo\u001c:z\t\u00164\u0017-\u001e7ugB\u0019\u0011-!\u0001\n\u0007\u0005\rqK\u0001\u0005Ji\u0016\u0014\u0018M\u00197f!\r\t\u0017qA\u0005\u0004\u0003\u00139&aA'baB!\u0011QBA\n\u001b\t\tyAC\u0002\u0002\u0012e\u000bqaZ3oKJL7-\u0003\u0003\u0002\u0016\u0005=!a\u0005#fM\u0006,H\u000e^*fe&\fG.\u001b>bE2,\u0017\u0001\u0002;sK\u0016,\"!a\u0007\u0011\r\u0005u\u00111\u00053q\u001d\r\t\u0017qD\u0005\u0004\u0003C9\u0016\u0001\u0004*fI\nc\u0017mY6Ue\u0016,\u0017\u0002BA\u0013\u0003O\u0011A\u0001\u0016:fK*\u0019\u0011\u0011E,\u0002\u000bQ\u0014X-\u001a\u0011\u0002\u0011=\u0014H-\u001a:j]\u001e,\"!a\f\u0011\u000b\u0005E\u0012q\u00073\u000f\u0007)\f\u0019$C\u0002\u00026m\u000bq\u0001]1dW\u0006<W-\u0003\u0003\u0002:\u0005m\"\u0001C(sI\u0016\u0014\u0018N\\4\u000b\u0007\u0005U2,A\u0005pe\u0012,'/\u001b8hA\u00051A(\u001b8jiz\"B!a\u0011\u0002HQ\u0019!0!\u0012\t\u000f\u0005-R\u0001q\u0001\u00020!9\u0011qC\u0003A\u0002\u0005mACAA&)\rQ\u0018Q\n\u0005\b\u0003W1\u00019AA\u0018\u0003\u0015!(/Z31\u00031qWm^'ba>\u00138+\u001a7g+\u0011\t)&a\u0017\u0015\t\u0005]\u0013\u0011\r\t\u0006C\u0002!\u0017\u0011\f\t\u0004K\u0006mCaBA/\u0011\t\u0007\u0011q\f\u0002\u0003-F\n\"\u0001]7\t\u000f\u0005\r\u0004\u00021\u0001\u0002f\u0005\tA\u000fE\u0004\u0002\u001e\u0005\rB-!\u0017\u0002!M|'\u000f^3e\u001b\u0006\u0004h)Y2u_JLXCAA6!\u0011a\u0018QN=\n\u0007\u0005=\u0014L\u0001\tT_J$X\rZ'ba\u001a\u000b7\r^8ss\u0006A\u0011\u000e^3sCR|'/\u0006\u0002\u0002vA)A0a\u001e\u0002|%\u0019\u0011\u0011P-\u0003\u0011%#XM]1u_J\u0004RA[A?IBL1!a \\\u0005\u0019!V\u000f\u001d7fe\u0005\u00012.Z=t\u0013R,'/\u0019;pe\u001a\u0013x.\u001c\u000b\u0005\u0003\u000b\u000b9\t\u0005\u0003}\u0003o\"\u0007BBAE\u0017\u0001\u0007A-A\u0003ti\u0006\u0014H/\u0001\u0004lKf\u001cV\r^\u000b\u0003\u0003\u001f\u0003B!YAII&\u0019\u00111S,\u0003\u000fQ\u0013X-Z*fi\u0006a\u0011\u000e^3sCR|'O\u0012:p[R!\u0011QOAM\u0011\u0019\tI)\u0004a\u0001I\u0006\u0011b/\u00197vKNLE/\u001a:bi>\u0014hI]8n)\u0011\ty*!)\u0011\tq\f9\b\u001d\u0005\u0007\u0003\u0013s\u0001\u0019\u00013\u0002\u000fM$X\r\u001d9feV!\u0011qUAY)\u0011\tI+!9\u0013\r\u0005-\u0016qVAc\r\u0019\ti\u000b\u0001\u0001\u0002*\naAH]3gS:,W.\u001a8u}A\u0019Q-!-\u0005\u000f\u0005MvB1\u0001\u00026\n\t1+E\u0002j\u0003o\u0003D!!/\u0002BB)A0a/\u0002@&\u0019\u0011QX-\u0003\u000fM#X\r\u001d9feB\u0019Q-!1\u0005\u0017\u0005\r\u0017\u0011WA\u0001\u0002\u0003\u0015\t\u0001\u001b\u0002\u0004?\u0012\n\u0004\u0003BAd\u00037tA!!3\u0002X:!\u00111ZAk\u001d\u0011\ti-a5\u000e\u0005\u0005='bAAi;\u00061AH]8pizJ\u0011\u0001X\u0005\u00035nK1!!7Z\u0003\u001d\u0019F/\u001a9qKJLA!!8\u0002`\nqQI\u001a4jG&,g\u000e^*qY&$(bAAm3\"9\u00111]\bA\u0004\u0005\u0015\u0018!B:iCB,\u0007c\u0002?\u0002h\u0006m\u0014qV\u0005\u0004\u0003SL&\u0001D*uKB\u0004XM]*iCB,\u0017AC6fsN#X\r\u001d9feV!\u0011q^A|)\u0011\t\tP!\u0002\u0013\r\u0005M\u0018Q_Ac\r\u0019\ti\u000b\u0001\u0001\u0002rB\u0019Q-a>\u0005\u000f\u0005M\u0006C1\u0001\u0002zF\u0019\u0011.a?1\t\u0005u(\u0011\u0001\t\u0006y\u0006m\u0016q \t\u0004K\n\u0005Aa\u0003B\u0002\u0003o\f\t\u0011!A\u0003\u0002!\u00141a\u0018\u00133\u0011\u001d\t\u0019\u000f\u0005a\u0002\u0005\u000f\u0001b\u0001`AtI\u0006U\u0018\u0001\u0004<bYV,7\u000b^3qa\u0016\u0014X\u0003\u0002B\u0007\u0005+!BAa\u0004\u0003$I1!\u0011\u0003B\n\u0003\u000b4a!!,\u0001\u0001\t=\u0001cA3\u0003\u0016\u00119\u00111W\tC\u0002\t]\u0011cA5\u0003\u001aA\"!1\u0004B\u0010!\u0015a\u00181\u0018B\u000f!\r)'q\u0004\u0003\f\u0005C\u0011)\"!A\u0001\u0002\u000b\u0005\u0001NA\u0002`IMBq!a9\u0012\u0001\b\u0011)\u0003\u0005\u0004}\u0003O\u0004(1C\u0001\u0004O\u0016$H\u0003\u0002B\u0016\u0005c\u0001BA\u001bB\u0017a&\u0019!qF.\u0003\r=\u0003H/[8o\u0011\u0019\u0011\u0019D\u0005a\u0001I\u0006\u00191.Z=\u0002\u0013\u001d,Go\u0014:FYN,W\u0003\u0002B\u001d\u0005{!bAa\u000f\u0003@\t\u0005\u0003cA3\u0003>\u00119\u0011QL\nC\u0002\u0005}\u0003B\u0002B\u001a'\u0001\u0007A\r\u0003\u0005\u0003DM!\t\u0019\u0001B#\u0003\u001d!WMZ1vYR\u0004RA\u001bB$\u0005wI1A!\u0013\\\u0005!a$-\u001f8b[\u0016t\u0014a\u0002:f[>4X\r\u001a\u000b\u0004u\n=\u0003B\u0002B\u001a)\u0001\u0007A-A\u0004va\u0012\fG/\u001a3\u0016\t\tU#1\f\u000b\u0007\u0005/\u0012iFa\u0018\u0011\u000b\u0005\u0004AM!\u0017\u0011\u0007\u0015\u0014Y\u0006B\u0004\u0002^U\u0011\r!a\u0018\t\r\tMR\u00031\u0001e\u0011\u001d\u0011\t'\u0006a\u0001\u00053\nQA^1mk\u0016\faaY8oG\u0006$X\u0003\u0002B4\u0005[\"BA!\u001b\u0003pA)\u0011\r\u00013\u0003lA\u0019QM!\u001c\u0005\u000f\u0005ucC1\u0001\u0002`!9!\u0011\u000f\fA\u0002\tM\u0014\u0001\u0002;iCR\u0004R\u0001 B;\u0005sJ1Aa\u001eZ\u00051IE/\u001a:bE2,wJ\\2f!\u0019Q\u0017Q\u00103\u0003l\u0005Q!/Z7pm\u0016$\u0017\t\u001c7\u0015\u0007i\u0014y\bC\u0004\u0003\u0002^\u0001\rAa!\u0002\t-,\u0017p\u001d\t\u0005y\nUD-\u0001\u0004j]N,'\u000f^\u000b\u0005\u0005\u0013\u0013y\t\u0006\u0004\u0003\f\nE%1\u0013\t\u0006C\u0002!'Q\u0012\t\u0004K\n=EaBA/1\t\u0007\u0011q\f\u0005\u0007\u0005gA\u0002\u0019\u00013\t\u000f\t\u0005\u0004\u00041\u0001\u0003\u000e\"Z\u0001Da&\u0003\u001e\n}%1\u0015BS!\rQ'\u0011T\u0005\u0004\u00057[&A\u00033faJ,7-\u0019;fI\u00069Q.Z:tC\u001e,\u0017E\u0001BQ\u0003U)6/\u001a\u0011akB$\u0017\r^3eA\u0002Jgn\u001d;fC\u0012\fQa]5oG\u0016\f#Aa*\u0002\rIr\u0013g\r\u00181\u0003%\u0011\u0018M\\4f\u00136\u0004H\u000eF\u0003{\u0005[\u0013\u0019\fC\u0004\u00030f\u0001\rA!-\u0002\t\u0019\u0014x.\u001c\t\u0005U\n5B\rC\u0004\u00036f\u0001\rA!-\u0002\u000bUtG/\u001b7\u0002\u00115Lg.\u00114uKJ$BAa/\u0003>B)!N!\f\u0002|!1!1\u0007\u000eA\u0002\u0011\f\u0011\"\\1y\u0005\u00164wN]3\u0015\t\tm&1\u0019\u0005\u0007\u0005gY\u0002\u0019\u00013\u0002\u000bI\fgnZ3\u0015\u000bi\u0014IMa3\t\r\t=F\u00041\u0001e\u0011\u0019\u0011)\f\ba\u0001I\u00069am\u001c:fC\u000eDW\u0003\u0002Bi\u0005K$BAa5\u0003ZB\u0019!N!6\n\u0007\t]7L\u0001\u0003V]&$\bb\u0002Bn;\u0001\u0007!Q\\\u0001\u0002MB9!Na8\u0002|\t\r\u0018b\u0001Bq7\nIa)\u001e8di&|g.\r\t\u0004K\n\u0015HA\u0002Bt;\t\u0007\u0001NA\u0001V\u000311wN]3bG\",e\u000e\u001e:z+\u0011\u0011iO!?\u0015\t\tM'q\u001e\u0005\b\u00057t\u0002\u0019\u0001By!\u001dQ'1\u001f3q\u0005oL1A!>\\\u0005%1UO\\2uS>t'\u0007E\u0002f\u0005s$aAa:\u001f\u0005\u0004A\u0017\u0001B:ju\u0016,\"Aa@\u0011\u0007)\u001c\t!C\u0002\u0004\u0004m\u00131!\u00138u\u0003%Ygn\\<o'&TX-A\u0004jg\u0016k\u0007\u000f^=\u0016\u0005\r-\u0001c\u00016\u0004\u000e%\u00191qB.\u0003\u000f\t{w\u000e\\3b]\u0006Aa-\u001b:ti.+\u00170F\u0001e\u0003\u001da\u0017m\u001d;LKf\fA\u0001[3bIV\u0011\u00111P\u0001\u0005Y\u0006\u001cH/\u0001\u0003uC&dW#\u0001>\u0002\t%t\u0017\u000e^\u0001\u0005IJ|\u0007\u000fF\u0002{\u0007OAqa!\u000b)\u0001\u0004\u0011y0A\u0001o\u0003\u0011!\u0018m[3\u0015\u0007i\u001cy\u0003C\u0004\u0004*%\u0002\rAa@\u0002\u000bMd\u0017nY3\u0015\u000bi\u001c)da\u000e\t\u000f\t=&\u00061\u0001\u0003\u0000\"9!Q\u0017\u0016A\u0002\t}\u0018!\u00033s_B\u0014\u0016n\u001a5u)\rQ8Q\b\u0005\b\u0007SY\u0003\u0019\u0001B\u0000\u0003%!\u0018m[3SS\u001eDG\u000fF\u0002{\u0007\u0007Bqa!\u000b-\u0001\u0004\u0011y0\u0001\u0006d_VtGo\u00165jY\u0016$BAa@\u0004J!911J\u0017A\u0002\r5\u0013!\u00019\u0011\u000f)\u0014y.a\u001f\u0004\f\u0005IAM]8q/\"LG.\u001a\u000b\u0004u\u000eM\u0003bBB&]\u0001\u00071QJ\u0001\ni\u0006\\Wm\u00165jY\u0016$2A_B-\u0011\u001d\u0019Ye\fa\u0001\u0007\u001b\nAa\u001d9b]R!1qLB1!\u0015Q\u0017Q\u0010>{\u0011\u001d\u0019Y\u0005\ra\u0001\u0007\u001b\naAZ5mi\u0016\u0014Hc\u0001>\u0004h!9!1\\\u0019A\u0002\r5\u0013!\u00039beRLG/[8o)\u0011\u0019yf!\u001c\t\u000f\r-#\u00071\u0001\u0004N\u0005IAO]1og\u001a|'/\\\u000b\u0005\u0007g\u001aI\b\u0006\u0003\u0004v\ru\u0004#B1\u0001I\u000e]\u0004cA3\u0004z\u0011111P\u001aC\u0002!\u0014\u0011a\u0016\u0005\b\u00057\u001c\u0004\u0019AB@!\u001dQ'1\u001f3q\u0007o\u0012Q!\u00113eKJ,Ba!\"\u0004\u0010N)Aga\"\u0004\u0014B9\u0011QDBEI\u000e5\u0015\u0002BBF\u0003O\u0011\u0011\"T1q\u0011\u0016d\u0007/\u001a:\u0011\u0007\u0015\u001cy\tB\u0004\u0004\u0012R\u0012\r!a\u0018\u0003\u0005\t\u000b\u0004c\u00026\u0003`\u000eU%1\u001b\t\u0007U\u0006uDm!$\u0015\u0005\re\u0005#BBNi\r5U\"\u0001\u0001\u0002%\r,(O]3oi6+H/\u00192mKR\u0013X-Z\u000b\u0003\u0007C\u0003r!!\b\u0002$\u0011\u001ci)\u0001\fdkJ\u0014XM\u001c;NkR\f'\r\\3Ue\u0016,w\fJ3r)\u0011\u0011\u0019na*\t\u0013\r%v'!AA\u0002\r\u0005\u0016a\u0001=%c\u0005\u00192-\u001e:sK:$X*\u001e;bE2,GK]3fA\u0005Ia-\u001b8bYR\u0013X-Z\u0001\u0006CB\u0004H.\u001f\u000b\u0005\u0005'\u001c\u0019\fC\u0004\u00046j\u0002\ra!&\u0002\u0005-4\u0018AB1eI\u0006cG\u000e\u0006\u0003\u0003T\u000em\u0006bBB_w\u0001\u00071qX\u0001\u0003YN\u0004R!YBa\u0007+K1aa1X\u0005%a\u0015N\\3beN+\u0017\u000fK\u0002<\u0007\u000f\u0004Ba!3\u0004P6\u001111\u001a\u0006\u0004\u0007\u001b\\\u0016AC1o]>$\u0018\r^5p]&!1\u0011[Bf\u0005\u001d!\u0018-\u001b7sK\u000e\fa!Z9vC2\u001cH\u0003BB\u0006\u0007/Daa!7=\u0001\u0004i\u0017aA8cU\u0006I1\r\\1tg:\u000bW.Z\u000b\u0003\u0007?\u0004Ba!9\u0004l6\u001111\u001d\u0006\u0005\u0007K\u001c9/\u0001\u0003mC:<'BABu\u0003\u0011Q\u0017M^1\n\t\r581\u001d\u0002\u0007'R\u0014\u0018N\\4\u0002\u000fQ\u0013X-Z'baB\u0011\u0011mP\n\u0006\u007f\rU\u00181\u000e\t\u0004U\u000e]\u0018bAB}7\n1\u0011I\\=SK\u001a$\"a!=\u0002\u000b\u0015l\u0007\u000f^=\u0016\r\u0011\u0005Aq\u0001C\u0006)\u0011!\u0019\u0001\"\u0004\u0011\r\u0005\u0004AQ\u0001C\u0005!\r)Gq\u0001\u0003\u0006O\u0006\u0013\r\u0001\u001b\t\u0004K\u0012-A!\u0002:B\u0005\u0004A\u0007\"\u0003C\b\u0003\u0006\u0005\t9\u0001C\t\u0003))g/\u001b3f]\u000e,G%\r\t\u0007\u0003c\t9\u0004\"\u0002\u0016\r\u0011UAQ\u0004C\u0011)\u0011!9\u0002b\n\u0015\t\u0011eA1\u0005\t\u0007C\u0002!Y\u0002b\b\u0011\u0007\u0015$i\u0002B\u0003h\u0005\n\u0007\u0001\u000eE\u0002f\tC!QA\u001d\"C\u0002!Dq!a\u000bC\u0001\b!)\u0003\u0005\u0004\u00022\u0005]B1\u0004\u0005\b\tS\u0011\u0005\u0019\u0001C\u0016\u0003\tIG\u000fE\u0003}\u0005k\"i\u0003E\u0004k\u0003{\"Y\u0002b\b\u0002\u00159,wOQ;jY\u0012,'/\u0006\u0004\u00054\u0011\u0015C\u0011\n\u000b\u0005\tk!i\u0005\u0005\u0005\u00058\u0011uB\u0011\tC&\u001b\t!IDC\u0002\u0005<e\u000bq!\\;uC\ndW-\u0003\u0003\u0005@\u0011e\"a\u0004*fkN\f'\r\\3Ck&dG-\u001a:\u0011\u000f)\fi\bb\u0011\u0005HA\u0019Q\r\"\u0012\u0005\u000b\u001d\u001c%\u0019\u00015\u0011\u0007\u0015$I\u0005B\u0003s\u0007\n\u0007\u0001\u000e\u0005\u0004b\u0001\u0011\rCq\t\u0005\b\u0003W\u0019\u00059\u0001C(!\u0019\t\t$a\u000e\u0005D\tqAK]3f\u001b\u0006\u0004()^5mI\u0016\u0014XC\u0002C+\t7\"yfE\u0003E\t/\"\t\u0007\u0005\u0005\u0002\u001e\r%E\u0011\fC/!\r)G1\f\u0003\u0006O\u0012\u0013\r\u0001\u001b\t\u0004K\u0012}C!\u0002:E\u0005\u0004A\u0007\u0003\u0003C\u001c\t{!\u0019\u0007\"\u001a\u0011\u000f)\fi\b\"\u0017\u0005^A1\u0011\r\u0001C-\t;\u0002b!!\r\u00028\u0011e\u0013\u0002BA\u0016\tWJA\u0001\"\u001c\u0002(\t1\u0001*\u001a7qKJ$\"\u0001\"\u001d\u0015\t\u0011MDq\u000f\t\b\tk\"E\u0011\fC/\u001b\u0005y\u0004bBA\u0016\r\u0002\u000fAq\r\t\t\u0003;\t\u0019\u0003\"\u0017\u0005^U\u0011AQ\u0010\t\u0004\t\u007f:U\"\u0001#\u0002\u0011Q\u0014X-Z0%KF$BAa5\u0005\u0006\"I1\u0011V%\u0002\u0002\u0003\u0007AQP\u0001\u0007C\u0012$wJ\\3\u0015\t\u0011}D1\u0012\u0005\b\t\u001b[\u0005\u0019\u0001C2\u0003\u0011)G.Z7\u0002\u000b\u0005$G-\u001a:\u0011\u0007\u0011}TJA\u0003bI\u0012,'oE\u0002N\t/\u0003\"\u0002\"'\u0005 \u0012eCQ\fBj\u001b\t!YJC\u0002\u0005\u001en\u000bqA];oi&lW-\u0003\u0003\u0005\"\u0012m%!E!cgR\u0014\u0018m\u0019;Gk:\u001cG/[8oeQ\u0011A\u0011S\u0001\fC\u000e\u001cW/\\;mCR|'/\u0001\u0006bI\u00124uN]#bG\"$BAa5\u0005,\"9AQ\u0016)A\u0002\u0011=\u0016A\u00035bg\u001a{'/R1dQB9A\u0010\"-\u0005Z\u0011u\u0013bAA\u00053R1!1\u001bC[\toCqAa\rR\u0001\u0004!I\u0006C\u0004\u0003bE\u0003\r\u0001\"\u0018\u0015\t\u0011}D1\u0018\u0005\b\t{\u0013\u0006\u0019\u0001C`\u0003\tA8\u000fE\u0003}\u0005k\"\u0019'A\u0003dY\u0016\f'\u000f\u0006\u0002\u0003T\u00061!/Z:vYR$\"\u0001\"\u001a\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00115\u0007\u0003BBq\t\u001fLA\u0001\"5\u0004d\n1qJ\u00196fGRDsa\u0010Ck\u0005C\"Y\u000eE\u0002k\t/L1\u0001\"7\\\u0005A\u0019VM]5bYZ+'o]5p]VKEIH\u0001\u0004Q\u001dqDQ\u001bB1\t7\u0004"
)
public final class TreeMap extends AbstractMap implements SortedMap, StrictOptimizedSortedMapOps, DefaultSerializable {
   private final RedBlackTree.Tree tree;
   private final Ordering ordering;

   public static ReusableBuilder newBuilder(final Ordering ordering) {
      TreeMap$ var10000 = TreeMap$.MODULE$;
      return new TreeMapBuilder(ordering);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
   }

   public scala.collection.Map map(final Function1 f, final Ordering ordering) {
      return scala.collection.StrictOptimizedSortedMapOps.map$(this, f, ordering);
   }

   public scala.collection.Map flatMap(final Function1 f, final Ordering ordering) {
      return scala.collection.StrictOptimizedSortedMapOps.flatMap$(this, f, ordering);
   }

   public scala.collection.Map collect(final PartialFunction pf, final Ordering ordering) {
      return scala.collection.StrictOptimizedSortedMapOps.collect$(this, pf, ordering);
   }

   /** @deprecated */
   public scala.collection.Map $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
      return scala.collection.StrictOptimizedSortedMapOps.$plus$(this, elem1, elem2, elems);
   }

   public IterableOps map(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.map$(this, f);
   }

   public IterableOps flatMap(final Function1 f) {
      return scala.collection.StrictOptimizedMapOps.flatMap$(this, f);
   }

   public IterableOps collect(final PartialFunction pf) {
      return scala.collection.StrictOptimizedMapOps.collect$(this, pf);
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

   public Map unsorted() {
      return SortedMap.unsorted$(this);
   }

   public SortedMap withDefault(final Function1 d) {
      return SortedMap.withDefault$(this, d);
   }

   public SortedMap withDefaultValue(final Object d) {
      return SortedMap.withDefaultValue$(this, d);
   }

   public final Map $plus(final Tuple2 kv) {
      return SortedMapOps.$plus$(this, kv);
   }

   public Map updatedWith(final Object key, final Function1 remappingFunction) {
      return SortedMapOps.updatedWith$(this, key, remappingFunction);
   }

   // $FF: synthetic method
   public boolean scala$collection$SortedMap$$super$equals(final Object o) {
      return scala.collection.Map.equals$(this, o);
   }

   public String stringPrefix() {
      return scala.collection.SortedMap.stringPrefix$(this);
   }

   public scala.collection.SortedMapOps empty() {
      return SortedMapFactoryDefaults.empty$(this);
   }

   public scala.collection.SortedMapOps fromSpecific(final IterableOnce coll) {
      return SortedMapFactoryDefaults.fromSpecific$(this, coll);
   }

   public Builder newSpecificBuilder() {
      return SortedMapFactoryDefaults.newSpecificBuilder$(this);
   }

   public scala.collection.SortedMapOps.WithFilter withFilter(final Function1 p) {
      return SortedMapFactoryDefaults.withFilter$(this, p);
   }

   public final scala.collection.Map sortedMapFromIterable(final scala.collection.Iterable it, final Ordering ordering) {
      return scala.collection.SortedMapOps.sortedMapFromIterable$(this, it, ordering);
   }

   public scala.collection.SortedMapOps rangeTo(final Object to) {
      return scala.collection.SortedMapOps.rangeTo$(this, to);
   }

   public final scala.collection.Map $plus$plus(final IterableOnce xs) {
      return scala.collection.SortedMapOps.$plus$plus$(this, xs);
   }

   /** @deprecated */
   public int compare(final Object k0, final Object k1) {
      return SortedOps.compare$(this, k0, k1);
   }

   /** @deprecated */
   public final Object from(final Object from) {
      return SortedOps.from$(this, from);
   }

   public Object rangeFrom(final Object from) {
      return SortedOps.rangeFrom$(this, from);
   }

   /** @deprecated */
   public final Object until(final Object until) {
      return SortedOps.until$(this, until);
   }

   public Object rangeUntil(final Object until) {
      return SortedOps.rangeUntil$(this, until);
   }

   /** @deprecated */
   public final Object to(final Object to) {
      return SortedOps.to$(this, to);
   }

   private RedBlackTree.Tree tree() {
      return this.tree;
   }

   public Ordering ordering() {
      return this.ordering;
   }

   public RedBlackTree.Tree tree0() {
      return this.tree();
   }

   private TreeMap newMapOrSelf(final RedBlackTree.Tree t) {
      return t == this.tree() ? this : new TreeMap(t, this.ordering());
   }

   public SortedMapFactory sortedMapFactory() {
      return TreeMap$.MODULE$;
   }

   public Iterator iterator() {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var4 = this.tree();
      RedBlackTree$ var10001 = RedBlackTree$.MODULE$;
      None$ var5 = None$.MODULE$;
      Ordering iterator_evidence$16 = this.ordering();
      None$ iterator_start = var5;
      RedBlackTree.Tree iterator_tree = var4;
      return new RedBlackTree.EntriesIterator(iterator_tree, iterator_start, iterator_evidence$16);
   }

   public Iterator keysIteratorFrom(final Object start) {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var5 = this.tree();
      Some var10001 = new Some(start);
      Ordering keysIterator_evidence$17 = this.ordering();
      Some keysIterator_start = var10001;
      RedBlackTree.Tree keysIterator_tree = var5;
      return new RedBlackTree.KeysIterator(keysIterator_tree, keysIterator_start, keysIterator_evidence$17);
   }

   public TreeSet keySet() {
      return new TreeSet(this.tree(), this.ordering());
   }

   public Iterator iteratorFrom(final Object start) {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var5 = this.tree();
      Some var10001 = new Some(start);
      Ordering iterator_evidence$16 = this.ordering();
      Some iterator_start = var10001;
      RedBlackTree.Tree iterator_tree = var5;
      return new RedBlackTree.EntriesIterator(iterator_tree, iterator_start, iterator_evidence$16);
   }

   public Iterator valuesIteratorFrom(final Object start) {
      RedBlackTree$ var10000 = RedBlackTree$.MODULE$;
      RedBlackTree.Tree var5 = this.tree();
      Some var10001 = new Some(start);
      Ordering valuesIterator_evidence$18 = this.ordering();
      Some valuesIterator_start = var10001;
      RedBlackTree.Tree valuesIterator_tree = var5;
      return new RedBlackTree.ValuesIterator(valuesIterator_tree, valuesIterator_start, valuesIterator_evidence$18);
   }

   public Stepper stepper(final StepperShape shape) {
      AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
      int var13 = this.size();
      RedBlackTree.Tree var10002 = this.tree();
      Function1 var10003 = (x$1) -> x$1.left();
      Function1 var10004 = (x$2) -> x$2.right();
      Function1 from_extract = (x) -> new Tuple2(x.scala$collection$immutable$RedBlackTree$Tree$$_key, x.scala$collection$immutable$RedBlackTree$Tree$$_value);
      Function1 from_right = var10004;
      Function1 from_left = var10003;
      RedBlackTree.Tree from_root = var10002;
      int from_maxLength = var13;
      AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
      from_ans.initialize(from_root, from_maxLength);
      AnyBinaryTreeStepper var14 = from_ans;
      from_root = null;
      from_left = null;
      from_right = null;
      from_extract = null;
      Object var12 = null;
      return shape.parUnbox(var14);
   }

   public Stepper keyStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntBinaryTreeStepper$ var35 = IntBinaryTreeStepper$.MODULE$;
         int var36 = this.size();
         RedBlackTree.Tree var41 = this.tree();
         Function1 var44 = (x$3) -> x$3.left();
         Function1 var47 = (x$4) -> x$4.right();
         Function1 from_extract = (x$5) -> BoxesRunTime.boxToInteger($anonfun$keyStepper$3(x$5));
         Function1 from_right = var47;
         Function1 from_left = var44;
         RedBlackTree.Tree from_root = var41;
         int from_maxLength = var36;
         IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongBinaryTreeStepper$ var33 = LongBinaryTreeStepper$.MODULE$;
         int var34 = this.size();
         RedBlackTree.Tree var40 = this.tree();
         Function1 var43 = (x$6) -> x$6.left();
         Function1 var46 = (x$7) -> x$7.right();
         Function1 from_extract = (x$8) -> BoxesRunTime.boxToLong($anonfun$keyStepper$6(x$8));
         Function1 from_right = var46;
         Function1 from_left = var43;
         RedBlackTree.Tree from_root = var40;
         int from_maxLength = var34;
         LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleBinaryTreeStepper$ var10000 = DoubleBinaryTreeStepper$.MODULE$;
         int var32 = this.size();
         RedBlackTree.Tree var39 = this.tree();
         Function1 var42 = (x$9) -> x$9.left();
         Function1 var45 = (x$10) -> x$10.right();
         Function1 from_extract = (x$11) -> BoxesRunTime.boxToDouble($anonfun$keyStepper$9(x$11));
         Function1 from_right = var45;
         Function1 from_left = var42;
         RedBlackTree.Tree from_root = var39;
         int from_maxLength = var32;
         DoubleBinaryTreeStepper from_ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else {
         AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
         int var37 = this.size();
         RedBlackTree.Tree var10002 = this.tree();
         Function1 var10003 = (x$12) -> x$12.left();
         Function1 var10004 = (x$13) -> x$13.right();
         Function1 from_extract = (x$14) -> x$14.key();
         Function1 from_right = var10004;
         Function1 from_left = var10003;
         RedBlackTree.Tree from_root = var10002;
         int from_maxLength = var37;
         AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         AnyBinaryTreeStepper var38 = from_ans;
         from_root = null;
         from_left = null;
         from_right = null;
         from_extract = null;
         Object var31 = null;
         return shape.parUnbox(var38);
      }
   }

   public Stepper valueStepper(final StepperShape shape) {
      int var2 = shape.shape();
      if (StepperShape$.MODULE$.IntShape() == var2) {
         IntBinaryTreeStepper$ var35 = IntBinaryTreeStepper$.MODULE$;
         int var36 = this.size();
         RedBlackTree.Tree var41 = this.tree();
         Function1 var44 = (x$15) -> x$15.left();
         Function1 var47 = (x$16) -> x$16.right();
         Function1 from_extract = (x$17) -> BoxesRunTime.boxToInteger($anonfun$valueStepper$3(x$17));
         Function1 from_right = var47;
         Function1 from_left = var44;
         RedBlackTree.Tree from_root = var41;
         int from_maxLength = var36;
         IntBinaryTreeStepper from_ans = new IntBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.LongShape() == var2) {
         LongBinaryTreeStepper$ var33 = LongBinaryTreeStepper$.MODULE$;
         int var34 = this.size();
         RedBlackTree.Tree var40 = this.tree();
         Function1 var43 = (x$18) -> x$18.left();
         Function1 var46 = (x$19) -> x$19.right();
         Function1 from_extract = (x$20) -> BoxesRunTime.boxToLong($anonfun$valueStepper$6(x$20));
         Function1 from_right = var46;
         Function1 from_left = var43;
         RedBlackTree.Tree from_root = var40;
         int from_maxLength = var34;
         LongBinaryTreeStepper from_ans = new LongBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else if (StepperShape$.MODULE$.DoubleShape() == var2) {
         DoubleBinaryTreeStepper$ var10000 = DoubleBinaryTreeStepper$.MODULE$;
         int var32 = this.size();
         RedBlackTree.Tree var39 = this.tree();
         Function1 var42 = (x$21) -> x$21.left();
         Function1 var45 = (x$22) -> x$22.right();
         Function1 from_extract = (x$23) -> BoxesRunTime.boxToDouble($anonfun$valueStepper$9(x$23));
         Function1 from_right = var45;
         Function1 from_left = var42;
         RedBlackTree.Tree from_root = var39;
         int from_maxLength = var32;
         DoubleBinaryTreeStepper from_ans = new DoubleBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         return from_ans;
      } else {
         AnyBinaryTreeStepper$ var10001 = AnyBinaryTreeStepper$.MODULE$;
         int var37 = this.size();
         RedBlackTree.Tree var10002 = this.tree();
         Function1 var10003 = (x$24) -> x$24.left();
         Function1 var10004 = (x$25) -> x$25.right();
         Function1 from_extract = (x$26) -> x$26.value();
         Function1 from_right = var10004;
         Function1 from_left = var10003;
         RedBlackTree.Tree from_root = var10002;
         int from_maxLength = var37;
         AnyBinaryTreeStepper from_ans = new AnyBinaryTreeStepper(0, (Object)null, BinaryTreeStepper$.MODULE$.emptyStack(), -1, from_left, from_right, from_extract);
         from_ans.initialize(from_root, from_maxLength);
         AnyBinaryTreeStepper var38 = from_ans;
         from_root = null;
         from_left = null;
         from_right = null;
         from_extract = null;
         Object var31 = null;
         return shape.parUnbox(var38);
      }
   }

   public Option get(final Object key) {
      return RedBlackTree$.MODULE$.get(this.tree(), key, this.ordering());
   }

   public Object getOrElse(final Object key, final Function0 default) {
      RedBlackTree.Tree resultOrNull = RedBlackTree$.MODULE$.lookup(this.tree(), key, this.ordering());
      return resultOrNull == null ? default.apply() : resultOrNull.scala$collection$immutable$RedBlackTree$Tree$$_value;
   }

   public TreeMap removed(final Object key) {
      return this.newMapOrSelf(RedBlackTree$.MODULE$.delete(this.tree(), key, this.ordering()));
   }

   public TreeMap updated(final Object key, final Object value) {
      return this.newMapOrSelf(RedBlackTree$.MODULE$.update(this.tree(), key, value, true, this.ordering()));
   }

   public TreeMap concat(final IterableOnce that) {
      TreeMap var2;
      RedBlackTree.Tree var8;
      label40: {
         if (that instanceof TreeMap) {
            var2 = (TreeMap)that;
            Ordering var10001 = this.ordering();
            Ordering var3 = var2.ordering();
            if (var10001 == null) {
               if (var3 == null) {
                  break label40;
               }
            } else if (var10001.equals(var3)) {
               break label40;
            }
         }

         if (that instanceof LinearSeq) {
            LinearSeq var4 = (LinearSeq)that;
            if (var4.isEmpty()) {
               var8 = this.tree();
            } else {
               Adder adder = new Adder();
               adder.addAll(var4);
               var8 = adder.finalTree();
            }

            return this.newMapOrSelf(var8);
         } else {
            Adder adder = new Adder();
            Iterator it = that.iterator();

            while(it.hasNext()) {
               adder.apply((Tuple2)it.next());
            }

            var8 = adder.finalTree();
            return this.newMapOrSelf(var8);
         }
      }

      var8 = RedBlackTree$.MODULE$.union(this.tree(), var2.tree(), this.ordering());
      return this.newMapOrSelf(var8);
   }

   public TreeMap removedAll(final IterableOnce keys) {
      if (keys instanceof TreeSet) {
         TreeSet var2 = (TreeSet)keys;
         Ordering var10000 = this.ordering();
         Ordering var3 = var2.ordering();
         if (var10000 == null) {
            if (var3 == null) {
               return this.newMapOrSelf(RedBlackTree$.MODULE$.difference(this.tree(), var2.tree(), this.ordering()));
            }
         } else if (var10000.equals(var3)) {
            return this.newMapOrSelf(RedBlackTree$.MODULE$.difference(this.tree(), var2.tree(), this.ordering()));
         }
      }

      return (TreeMap)MapOps.removedAll$(this, keys);
   }

   /** @deprecated */
   public TreeMap insert(final Object key, final Object value) {
      Predef$.MODULE$.assert(!RedBlackTree$.MODULE$.contains(this.tree(), key, this.ordering()));
      return this.updated(key, value);
   }

   public TreeMap rangeImpl(final Option from, final Option until) {
      return this.newMapOrSelf(RedBlackTree$.MODULE$.rangeImpl(this.tree(), from, until, this.ordering()));
   }

   public Option minAfter(final Object key) {
      RedBlackTree.Tree var2 = RedBlackTree$.MODULE$.minAfter(this.tree(), key, this.ordering());
      if (var2 == null) {
         Option$ var10000 = Option$.MODULE$;
         return None$.MODULE$;
      } else {
         return new Some(new Tuple2(var2.scala$collection$immutable$RedBlackTree$Tree$$_key, var2.scala$collection$immutable$RedBlackTree$Tree$$_value));
      }
   }

   public Option maxBefore(final Object key) {
      RedBlackTree.Tree var2 = RedBlackTree$.MODULE$.maxBefore(this.tree(), key, this.ordering());
      if (var2 == null) {
         Option$ var10000 = Option$.MODULE$;
         return None$.MODULE$;
      } else {
         return new Some(new Tuple2(var2.scala$collection$immutable$RedBlackTree$Tree$$_key, var2.scala$collection$immutable$RedBlackTree$Tree$$_value));
      }
   }

   public TreeMap range(final Object from, final Object until) {
      return this.newMapOrSelf(RedBlackTree$.MODULE$.range(this.tree(), from, until, this.ordering()));
   }

   public void foreach(final Function1 f) {
      RedBlackTree$.MODULE$.foreach(this.tree(), f);
   }

   public void foreachEntry(final Function2 f) {
      RedBlackTree$.MODULE$.foreachEntry(this.tree(), f);
   }

   public int size() {
      return RedBlackTree$.MODULE$.count(this.tree());
   }

   public int knownSize() {
      return this.size();
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public Object firstKey() {
      RedBlackTree.Tree var10000 = RedBlackTree$.MODULE$.smallest(this.tree());
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.scala$collection$immutable$RedBlackTree$Tree$$_key;
      }
   }

   public Object lastKey() {
      RedBlackTree.Tree var10000 = RedBlackTree$.MODULE$.greatest(this.tree());
      if (var10000 == null) {
         throw null;
      } else {
         return var10000.scala$collection$immutable$RedBlackTree$Tree$$_key;
      }
   }

   public Tuple2 head() {
      RedBlackTree.Tree smallest = RedBlackTree$.MODULE$.smallest(this.tree());
      Tuple2 var10000 = new Tuple2;
      if (smallest == null) {
         throw null;
      } else {
         var10000.<init>(smallest.scala$collection$immutable$RedBlackTree$Tree$$_key, smallest.scala$collection$immutable$RedBlackTree$Tree$$_value);
         return var10000;
      }
   }

   public Tuple2 last() {
      RedBlackTree.Tree greatest = RedBlackTree$.MODULE$.greatest(this.tree());
      Tuple2 var10000 = new Tuple2;
      if (greatest == null) {
         throw null;
      } else {
         var10000.<init>(greatest.scala$collection$immutable$RedBlackTree$Tree$$_key, greatest.scala$collection$immutable$RedBlackTree$Tree$$_value);
         return var10000;
      }
   }

   public TreeMap tail() {
      return new TreeMap(RedBlackTree$.MODULE$.tail(this.tree()), this.ordering());
   }

   public TreeMap init() {
      return new TreeMap(RedBlackTree$.MODULE$.init(this.tree()), this.ordering());
   }

   public TreeMap drop(final int n) {
      if (n <= 0) {
         return this;
      } else {
         return n >= this.size() ? (TreeMap)SortedMapFactoryDefaults.empty$(this) : new TreeMap(RedBlackTree$.MODULE$.drop(this.tree(), n, this.ordering()), this.ordering());
      }
   }

   public TreeMap take(final int n) {
      if (n <= 0) {
         return (TreeMap)SortedMapFactoryDefaults.empty$(this);
      } else {
         return n >= this.size() ? this : new TreeMap(RedBlackTree$.MODULE$.take(this.tree(), n, this.ordering()), this.ordering());
      }
   }

   public TreeMap slice(final int from, final int until) {
      if (until <= from) {
         return (TreeMap)SortedMapFactoryDefaults.empty$(this);
      } else if (from <= 0) {
         return this.take(until);
      } else {
         return until >= this.size() ? this.drop(from) : new TreeMap(RedBlackTree$.MODULE$.slice(this.tree(), from, until, this.ordering()), this.ordering());
      }
   }

   public TreeMap dropRight(final int n) {
      int var10001 = this.size();
      scala.math.package$ var10002 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.take(var10001 - Math.max(n, max_y));
   }

   public TreeMap takeRight(final int n) {
      int var10001 = this.size();
      scala.math.package$ var10002 = scala.math.package$.MODULE$;
      int max_y = 0;
      return this.drop(var10001 - Math.max(n, max_y));
   }

   private int countWhile(final Function1 p) {
      int result = 0;

      for(Iterator it = this.iterator(); it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(it.next())); ++result) {
      }

      return result;
   }

   public TreeMap dropWhile(final Function1 p) {
      int countWhile_result = 0;

      for(Iterator countWhile_it = this.iterator(); countWhile_it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(countWhile_it.next())); ++countWhile_result) {
      }

      Object var4 = null;
      return this.drop(countWhile_result);
   }

   public TreeMap takeWhile(final Function1 p) {
      int countWhile_result = 0;

      for(Iterator countWhile_it = this.iterator(); countWhile_it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(countWhile_it.next())); ++countWhile_result) {
      }

      Object var4 = null;
      return this.take(countWhile_result);
   }

   public Tuple2 span(final Function1 p) {
      int countWhile_result = 0;

      for(Iterator countWhile_it = this.iterator(); countWhile_it.hasNext() && BoxesRunTime.unboxToBoolean(p.apply(countWhile_it.next())); ++countWhile_result) {
      }

      Object var4 = null;
      return IterableOps.splitAt$(this, countWhile_result);
   }

   public TreeMap filter(final Function1 f) {
      return this.newMapOrSelf(RedBlackTree$.MODULE$.filterEntries(this.tree(), (k, v) -> BoxesRunTime.boxToBoolean($anonfun$filter$1(f, k, v))));
   }

   public Tuple2 partition(final Function1 p) {
      Tuple2 var2 = RedBlackTree$.MODULE$.partitionEntries(this.tree(), (k, v) -> BoxesRunTime.boxToBoolean($anonfun$partition$1(p, k, v)));
      if (var2 != null) {
         RedBlackTree.Tree l = (RedBlackTree.Tree)var2._1();
         RedBlackTree.Tree r = (RedBlackTree.Tree)var2._2();
         return new Tuple2(this.newMapOrSelf(l), this.newMapOrSelf(r));
      } else {
         throw new MatchError((Object)null);
      }
   }

   public TreeMap transform(final Function2 f) {
      RedBlackTree.Tree t2 = RedBlackTree$.MODULE$.transform(this.tree(), f);
      return t2 == this.tree() ? this : new TreeMap(t2, this.ordering());
   }

   public boolean equals(final Object obj) {
      if (obj instanceof TreeMap) {
         TreeMap var2 = (TreeMap)obj;
         Ordering var10000 = this.ordering();
         Ordering var3 = var2.ordering();
         if (var10000 == null) {
            if (var3 == null) {
               return RedBlackTree$.MODULE$.entriesEqual(this.tree(), var2.tree(), this.ordering());
            }
         } else if (var10000.equals(var3)) {
            return RedBlackTree$.MODULE$.entriesEqual(this.tree(), var2.tree(), this.ordering());
         }
      }

      return scala.collection.SortedMap.equals$(this, obj);
   }

   public String className() {
      return "TreeMap";
   }

   // $FF: synthetic method
   public static final int $anonfun$keyStepper$3(final RedBlackTree.Tree x$5) {
      return BoxesRunTime.unboxToInt(x$5.key());
   }

   // $FF: synthetic method
   public static final long $anonfun$keyStepper$6(final RedBlackTree.Tree x$8) {
      return BoxesRunTime.unboxToLong(x$8.key());
   }

   // $FF: synthetic method
   public static final double $anonfun$keyStepper$9(final RedBlackTree.Tree x$11) {
      return BoxesRunTime.unboxToDouble(x$11.key());
   }

   // $FF: synthetic method
   public static final int $anonfun$valueStepper$3(final RedBlackTree.Tree x$17) {
      return BoxesRunTime.unboxToInt(x$17.value());
   }

   // $FF: synthetic method
   public static final long $anonfun$valueStepper$6(final RedBlackTree.Tree x$20) {
      return BoxesRunTime.unboxToLong(x$20.value());
   }

   // $FF: synthetic method
   public static final double $anonfun$valueStepper$9(final RedBlackTree.Tree x$23) {
      return BoxesRunTime.unboxToDouble(x$23.value());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filter$1(final Function1 f$1, final Object k, final Object v) {
      return BoxesRunTime.unboxToBoolean(f$1.apply(new Tuple2(k, v)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$partition$1(final Function1 p$1, final Object k, final Object v) {
      return BoxesRunTime.unboxToBoolean(p$1.apply(new Tuple2(k, v)));
   }

   public TreeMap(final RedBlackTree.Tree tree, final Ordering ordering) {
      this.tree = tree;
      this.ordering = ordering;
   }

   public TreeMap(final Ordering ordering) {
      this((RedBlackTree.Tree)null, ordering);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private final class Adder extends RedBlackTree.MapHelper implements Function1 {
      private RedBlackTree.Tree currentMutableTree = TreeMap.this.tree0();

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

      public Function1 andThen(final Function1 g) {
         return Function1.andThen$(this, g);
      }

      public String toString() {
         return Function1.toString$(this);
      }

      private RedBlackTree.Tree currentMutableTree() {
         return this.currentMutableTree;
      }

      private void currentMutableTree_$eq(final RedBlackTree.Tree x$1) {
         this.currentMutableTree = x$1;
      }

      public RedBlackTree.Tree finalTree() {
         return this.beforePublish(this.currentMutableTree());
      }

      public void apply(final Tuple2 kv) {
         this.currentMutableTree_$eq(this.mutableUpd(this.currentMutableTree(), kv._1(), kv._2()));
      }

      public void addAll(final LinearSeq ls) {
         while(!ls.isEmpty()) {
            Tuple2 kv = (Tuple2)ls.head();
            this.currentMutableTree_$eq(this.mutableUpd(this.currentMutableTree(), kv._1(), kv._2()));
            ls = (LinearSeq)ls.tail();
         }

      }

      public Adder() {
         super(TreeMap.this.ordering());
      }
   }

   private static class TreeMapBuilder extends RedBlackTree.MapHelper implements ReusableBuilder {
      private volatile adder$ adder$module;
      private RedBlackTree.Tree scala$collection$immutable$TreeMap$TreeMapBuilder$$tree = null;

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

      public final Growable $plus$eq(final Object elem) {
         return Growable.$plus$eq$(this, elem);
      }

      /** @deprecated */
      public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
         return Growable.$plus$eq$(this, elem1, elem2, elems);
      }

      public final Growable $plus$plus$eq(final IterableOnce elems) {
         return Growable.$plus$plus$eq$(this, elems);
      }

      public int knownSize() {
         return Growable.knownSize$(this);
      }

      private adder$ adder() {
         if (this.adder$module == null) {
            this.adder$lzycompute$1();
         }

         return this.adder$module;
      }

      public RedBlackTree.Tree scala$collection$immutable$TreeMap$TreeMapBuilder$$tree() {
         return this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree;
      }

      public void scala$collection$immutable$TreeMap$TreeMapBuilder$$tree_$eq(final RedBlackTree.Tree x$1) {
         this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree = x$1;
      }

      public TreeMapBuilder addOne(final Tuple2 elem) {
         this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree_$eq(this.mutableUpd(this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree(), elem._1(), elem._2()));
         return this;
      }

      public TreeMapBuilder addAll(final IterableOnce xs) {
         if (xs instanceof TreeMap) {
            label35: {
               TreeMap var2 = (TreeMap)xs;
               Ordering var10000 = var2.ordering();
               Ordering var3 = super.ordering();
               if (var10000 == null) {
                  if (var3 != null) {
                     break label35;
                  }
               } else if (!var10000.equals(var3)) {
                  break label35;
               }

               if (this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree() == null) {
                  this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree_$eq(var2.tree0());
               } else {
                  this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree_$eq(RedBlackTree$.MODULE$.union(this.beforePublish(this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree()), var2.tree0(), super.ordering()));
               }

               return this;
            }
         }

         if (xs instanceof scala.collection.Map) {
            scala.collection.Map var4 = (scala.collection.Map)xs;
            this.adder().addForEach(var4);
         } else {
            Growable.addAll$(this, xs);
         }

         return this;
      }

      public void clear() {
         this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree_$eq((RedBlackTree.Tree)null);
      }

      public TreeMap result() {
         return new TreeMap(this.beforePublish(this.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree()), super.ordering());
      }

      private final void adder$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.adder$module == null) {
               this.adder$module = new adder$();
            }
         } catch (Throwable var2) {
            throw var2;
         }

      }

      public TreeMapBuilder(final Ordering ordering) {
         super(ordering);
      }

      private class adder$ extends AbstractFunction2 {
         private RedBlackTree.Tree accumulator;
         // $FF: synthetic field
         private final TreeMapBuilder $outer;

         public void addForEach(final scala.collection.Map hasForEach) {
            this.accumulator = this.$outer.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree();
            hasForEach.foreachEntry(this);
            this.$outer.scala$collection$immutable$TreeMap$TreeMapBuilder$$tree_$eq(this.accumulator);
            this.accumulator = null;
         }

         public void apply(final Object key, final Object value) {
            this.accumulator = this.$outer.mutableUpd(this.accumulator, key, value);
         }

         public adder$() {
            if (TreeMapBuilder.this == null) {
               throw null;
            } else {
               this.$outer = TreeMapBuilder.this;
               super();
               this.accumulator = null;
            }
         }
      }
   }
}
