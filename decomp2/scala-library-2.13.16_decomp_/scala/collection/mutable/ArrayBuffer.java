package scala.collection.mutable;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Array;
import java.util.Arrays;
import scala.Array$;
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
import scala.collection.IndexedSeqView;
import scala.collection.IterableFactory;
import scala.collection.IterableOnce;
import scala.collection.IterableOnce$;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedSeqOps;
import scala.collection.View;
import scala.collection.convert.impl.ObjectArrayStepper;
import scala.collection.generic.CommonErrors$;
import scala.collection.generic.DefaultSerializable;
import scala.math.Integral;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;
import scala.runtime.RichInt$;
import scala.util.Sorting$;

@ScalaSignature(
   bytes = "\u0006\u0005\rug\u0001B\u001f?\u0001\u0015C\u0001\u0002\u001c\u0001\u0003\u0002\u0003\u0006I!\u001c\u0005\tg\u0002\u0011\t\u0011)A\u0005i\")q\u000f\u0001C\u0005q\")q\u000f\u0001C\u0001w\")q\u000f\u0001C\u0001y\"1a\u0010\u0001Q!\nQD!\"a\u0002\u0001\u0001\u0004%\t\u0002QA\u0005\u0011)\tY\u0001\u0001a\u0001\n#\u0001\u0015Q\u0002\u0005\b\u00033\u0001\u0001\u0015)\u0003n\u0011%\tY\u0002\u0001a\u0001\n#\ti\u0002C\u0005\u0002 \u0001\u0001\r\u0011\"\u0005\u0002\"!9\u0011Q\u0005\u0001!B\u0013!\bbBA\u0014\u0001\u0011\u0005\u0013\u0011\u0006\u0005\b\u0003_\u0002A\u0011IA\u000f\u0011\u001d\t\t\b\u0001C\t\u0003gBq!!\u001f\u0001\t\u0003\tY\bC\u0004\u0002\u0002\u0002!I!a!\t\u000f\u0005\u001d\u0005\u0001\"\u0001\u0002\n\"9\u00111\u0012\u0001\u0005\n\u00055\u0005bBAJ\u0001\u0011%\u0011Q\u0013\u0005\b\u0003O\u0003A\u0011AAU\u0011\u001d\ti\u000b\u0001C\u0001\u0003_Cq!a2\u0001\t\u0003\ti\u0002C\u0004\u0002J\u0002!\t%a3\t\u000f\u0005M\u0007\u0001\"\u0011\u0002V\"9\u0011Q\u001c\u0001\u0005\u0002\u0005%\u0005bBAp\u0001\u0011\u0005\u0011\u0011\u001d\u0005\n\u0003O\u0004\u0011\u0013!C\u0001\u0003SDq!a@\u0001\t\u0003\u0011\t\u0001C\u0004\u0003\u0006\u0001!\tEa\u0002\t\u000f\tM\u0001\u0001\"\u0001\u0003\u0016!9!Q\u0004\u0001\u0005\u0002\t}\u0001b\u0002B\u0012\u0001\u0011\u0005!Q\u0005\u0005\b\u0005[\u0001A\u0011\u0001B\u0018\u0011\u001d\u0011i\u0003\u0001C\u0001\u0005kAqAa\u0010\u0001\t\u0003\u0011\t\u0005C\u0004\u0003b\u0001!\tAa\u0019\t\u0011\t\u001d\u0005\u0001)C)\u0005\u0013CqAa'\u0001\t\u0003\u0012i\nC\u0004\u00038\u0002!\tE!/\t\u000f\tU\u0007\u0001\"\u0003\u0003X\"9!Q \u0001\u0005\n\t}\bbBB\n\u0001\u0011\u00053Q\u0003\u0005\b\u0007K\u0001A\u0011IB\u0014\u0011\u001d\u00199\u0004\u0001C!\u0007sAqa!\u0012\u0001\t\u0003\u001a9eB\u0004\u0004`yB\ta!\u0019\u0007\rur\u0004\u0012AB2\u0011\u00199\b\u0007\"\u0001\u0004l!I1Q\u000e\u0019C\u0002\u0013\u00151q\u000e\u0005\t\u0007k\u0002\u0004\u0015!\u0004\u0004r!91q\u000f\u0019!\u0002\u0013i\u0007bBB=a\u0011\u000511\u0010\u0005\b\u0007\u0017\u0003D\u0011ABG\u0011\u001d\u0019I\n\rC\u0001\u00077C\u0001b!*1\t\u0003q4q\u0015\u0005\b\u0003c\u0002D\u0011BBY\u0011\u001d\u0019i\f\rC\u0005\u0007\u007fCqa!21\t\u0013\u00199\rC\u0005\u0004NB\n\t\u0011\"\u0003\u0004P\nY\u0011I\u001d:bs\n+hMZ3s\u0015\ty\u0004)A\u0004nkR\f'\r\\3\u000b\u0005\u0005\u0013\u0015AC2pY2,7\r^5p]*\t1)A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0005\u0019k5c\u0002\u0001H/j{6M\u001a\t\u0004\u0011&[U\"\u0001 \n\u0005)s$AD!cgR\u0014\u0018m\u0019;Ck\u001a4WM\u001d\t\u0003\u00196c\u0001\u0001B\u0003O\u0001\t\u0007qJA\u0001B#\t\u0001F\u000b\u0005\u0002R%6\t!)\u0003\u0002T\u0005\n9aj\u001c;iS:<\u0007CA)V\u0013\t1&IA\u0002B]f\u00042\u0001\u0013-L\u0013\tIfHA\u0007J]\u0012,\u00070\u001a3Ck\u001a4WM\u001d\t\u0006\u0011n[ULX\u0005\u00039z\u0012Q\"\u00138eKb,GmU3r\u001fB\u001c\bC\u0001%\u0001!\rA\u0005a\u0013\t\u0006A\u0006\\ULX\u0007\u0002\u0001&\u0011!\r\u0011\u0002\u0016'R\u0014\u0018n\u0019;PaRLW.\u001b>fIN+\u0017o\u00149t!\u0011\u0001GmS/\n\u0005\u0015\u0004%aF%uKJ\f'\r\\3GC\u000e$xN]=EK\u001a\fW\u000f\u001c;t!\t9'.D\u0001i\u0015\tI\u0007)A\u0004hK:,'/[2\n\u0005-D'a\u0005#fM\u0006,H\u000e^*fe&\fG.\u001b>bE2,\u0017aD5oSRL\u0017\r\\#mK6,g\u000e^:\u0011\u0007Es\u0007/\u0003\u0002p\u0005\n)\u0011I\u001d:bsB\u0011\u0011+]\u0005\u0003e\n\u0013a!\u00118z%\u00164\u0017aC5oSRL\u0017\r\\*ju\u0016\u0004\"!U;\n\u0005Y\u0014%aA%oi\u00061A(\u001b8jiz\"2AX={\u0011\u0015a7\u00011\u0001n\u0011\u0015\u00198\u00011\u0001u)\u0005qFC\u00010~\u0011\u0015\u0019X\u00011\u0001u\u00035iW\u000f^1uS>t7i\\;oi\"\u001aa!!\u0001\u0011\u0007E\u000b\u0019!C\u0002\u0002\u0006\t\u0013\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u000b\u0005\u0014(/Y=\u0016\u00035\f\u0011\"\u0019:sCf|F%Z9\u0015\t\u0005=\u0011Q\u0003\t\u0004#\u0006E\u0011bAA\n\u0005\n!QK\\5u\u0011!\t9\u0002CA\u0001\u0002\u0004i\u0017a\u0001=%c\u00051\u0011M\u001d:bs\u0002\nQa]5{KB*\u0012\u0001^\u0001\ng&TX\rM0%KF$B!a\u0004\u0002$!A\u0011qC\u0006\u0002\u0002\u0003\u0007A/\u0001\u0004tSj,\u0007\u0007I\u0001\bgR,\u0007\u000f]3s+\u0011\tY#!\u000e\u0015\t\u00055\u0012Q\r\n\u0007\u0003_\t\u0019$!\u0013\u0007\r\u0005E\u0002\u0001AA\u0017\u00051a$/\u001a4j]\u0016lWM\u001c;?!\ra\u0015Q\u0007\u0003\b\u0003oi!\u0019AA\u001d\u0005\u0005\u0019\u0016c\u0001)\u0002<A\"\u0011QHA#!\u0015\u0001\u0017qHA\"\u0013\r\t\t\u0005\u0011\u0002\b'R,\u0007\u000f]3s!\ra\u0015Q\t\u0003\f\u0003\u000f\n)$!A\u0001\u0002\u000b\u0005qJA\u0002`IE\u0002B!a\u0013\u0002`9!\u0011QJA.\u001d\u0011\ty%!\u0017\u000f\t\u0005E\u0013qK\u0007\u0003\u0003'R1!!\u0016E\u0003\u0019a$o\\8u}%\t1)\u0003\u0002B\u0005&\u0019\u0011Q\f!\u0002\u000fM#X\r\u001d9fe&!\u0011\u0011MA2\u00059)eMZ5dS\u0016tGo\u00159mSRT1!!\u0018A\u0011\u001d\t9'\u0004a\u0002\u0003S\nQa\u001d5ba\u0016\u0004b\u0001YA6\u0017\u0006M\u0012bAA7\u0001\na1\u000b^3qa\u0016\u00148\u000b[1qK\u0006I1N\\8x]NK'0Z\u0001\u000bK:\u001cXO]3TSj,G\u0003BA\b\u0003kBa!a\u001e\u0010\u0001\u0004!\u0018!\u00018\u0002\u0011ML'0\u001a%j]R$B!a\u0004\u0002~!1\u0011q\u0010\tA\u0002Q\fAa]5{K\u0006a!/\u001a3vG\u0016$vnU5{KR!\u0011qBAC\u0011\u0019\t9(\u0005a\u0001i\u0006QAO]5n)>\u001c\u0016N_3\u0015\u0005\u0005=\u0011A\u0002:fg&TX\r\u0006\u0003\u0002\u0010\u0005=\u0005BBAI'\u0001\u0007A/\u0001\bsKF,\u0018N]3e\u0019\u0016tw\r\u001e5\u0002#\rDWmY6XSRD\u0017N\u001c\"pk:$7\u000f\u0006\u0004\u0002\u0010\u0005]\u00151\u0014\u0005\u0007\u00033#\u0002\u0019\u0001;\u0002\u00051|\u0007BBAO)\u0001\u0007A/\u0001\u0002iS\"\u001aA#!)\u0011\u0007E\u000b\u0019+C\u0002\u0002&\n\u0013a!\u001b8mS:,\u0017!B1qa2LHcA&\u0002,\"1\u0011qO\u000bA\u0002Q\fa!\u001e9eCR,GCBA\b\u0003c\u000b\u0019\r\u0003\u0004\u00024Z\u0001\r\u0001^\u0001\u0006S:$W\r\u001f\u0015\t\u0003c\u000b9,!0\u0002@B\u0019\u0011+!/\n\u0007\u0005m&I\u0001\beKB\u0014XmY1uK\u0012t\u0015-\\3\"\u0005\u0005]\u0014EAAa\u0003\u0019\u0011d&M\u001a/a!1\u0011Q\u0019\fA\u0002-\u000bA!\u001a7f[\u00061A.\u001a8hi\"\fAA^5foV\u0011\u0011Q\u001a\t\u0005\u0011\u0006=7*C\u0002\u0002Rz\u0012q\"\u0011:sCf\u0014UO\u001a4feZKWm^\u0001\u0010SR,'/\u00192mK\u001a\u000b7\r^8ssV\u0011\u0011q\u001b\t\u0005A\u0006eW,C\u0002\u0002\\\u0002\u0013!bU3r\r\u0006\u001cGo\u001c:z\u0003\u0015\u0019G.Z1s\u00039\u0019G.Z1s\u0003:$7\u000b\u001b:j].$B!a9\u0002f6\t\u0001\u0001\u0003\u0005\u0002\u0000m\u0001\n\u00111\u0001u\u0003a\u0019G.Z1s\u0003:$7\u000b\u001b:j].$C-\u001a4bk2$H%M\u000b\u0003\u0003WT3\u0001^AwW\t\ty\u000f\u0005\u0003\u0002r\u0006mXBAAz\u0015\u0011\t)0a>\u0002\u0013Ut7\r[3dW\u0016$'bAA}\u0005\u0006Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\u0005u\u00181\u001f\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017AB1eI>sW\r\u0006\u0003\u0002d\n\r\u0001BBAc;\u0001\u00071*\u0001\u0004bI\u0012\fE\u000e\u001c\u000b\u0005\u0003G\u0014I\u0001C\u0004\u0003\fy\u0001\rA!\u0004\u0002\u000b\u0015dW-\\:\u0011\t\u0001\u0014yaS\u0005\u0004\u0005#\u0001%\u0001D%uKJ\f'\r\\3P]\u000e,\u0017AB5og\u0016\u0014H\u000f\u0006\u0004\u0002\u0010\t]!1\u0004\u0005\u0007\u0003g{\u0002\u0019\u0001;)\u0011\t]\u0011qWA_\u0003\u007fCa!!2 \u0001\u0004Y\u0015a\u00029sKB,g\u000e\u001a\u000b\u0005\u0003G\u0014\t\u0003\u0003\u0004\u0002F\u0002\u0002\raS\u0001\nS:\u001cXM\u001d;BY2$b!a\u0004\u0003(\t-\u0002BBAZC\u0001\u0007A\u000f\u000b\u0005\u0003(\u0005]\u0016QXA`\u0011\u001d\u0011Y!\ta\u0001\u0005\u001b\taA]3n_Z,GcA&\u00032!1\u00111\u0017\u0012A\u0002QD\u0003B!\r\u00028\u0006u\u0016q\u0018\u000b\u0007\u0003\u001f\u00119Da\u000f\t\r\u0005M6\u00051\u0001uQ!\u00119$a.\u0002>\u0006}\u0006B\u0002B\u001fG\u0001\u0007A/A\u0003d_VtG/\u0001\u0004sKN,H\u000e\u001e\u000b\u0003\u0003GD3\u0002\nB#\u0005\u0017\u0012iE!\u0015\u0002@B\u0019\u0011Ka\u0012\n\u0007\t%#I\u0001\u0006eKB\u0014XmY1uK\u0012\fq!\\3tg\u0006<W-\t\u0002\u0003P\u0005YRk]3!OQD\u0017n]\u0014!S:\u001cH/\u00198dK\u0002Jgn\u001d;fC\u0012\fQa]5oG\u0016D3\u0002\nB+\u0005\u0017\u0012YF!\u0015\u0002@B\u0019\u0011Ka\u0016\n\u0007\te#I\u0001\u000beKB\u0014XmY1uK\u0012|e/\u001a:sS\u0012LgnZ\u0011\u0003\u0005;\n1(\u0011:sCf\u0014UO\u001a4fen\u000bU\f\t8pA1|gnZ3sA\u0015DH/\u001a8eg\u0002\u0012U/\u001b7eKJ\\\u0016\t\f\u0011BeJ\f\u0017PQ;gM\u0016\u00148,Q/^Q\r!\u0013\u0011U\u0001\n[\u0006\u0004(+Z:vYR,BA!\u001a\u0003pQ!!q\rB:!\u0019A%\u0011N&\u0003n%\u0019!1\u000e \u0003\u000f\t+\u0018\u000e\u001c3feB\u0019AJa\u001c\u0005\r\tETE1\u0001P\u0005\u0015qUm\u001e+p\u0011\u001d\u0011)(\na\u0001\u0005o\n\u0011A\u001a\t\u0007#\nedL!\u001c\n\u0007\tm$IA\u0005Gk:\u001cG/[8oc!ZQE!\u0012\u0003L\t}$\u0011KA`C\t\u0011\t)\u0001\u001bVg\u0016\u0004sE\\3xA\u001d\u0013xn^1cY\u0016\u0014U/\u001b7eKJDC\u000f[5tS9j\u0017\r\u001d*fgVdG\u000f\u000b4*O\u0001Jgn\u001d;fC\u0012D3\"\nB+\u0005\u0017\u0012YF!\u0015\u0002@\"\u001aQ%!)\u0002\u0019M$(/\u001b8h!J,g-\u001b=\u0016\u0005\t-\u0005\u0003\u0002BG\u0005/k!Aa$\u000b\t\tE%1S\u0001\u0005Y\u0006twM\u0003\u0002\u0003\u0016\u0006!!.\u0019<b\u0013\u0011\u0011IJa$\u0003\rM#(/\u001b8h\u0003-\u0019w\u000e]=U_\u0006\u0013(/Y=\u0016\t\t}%\u0011\u0016\u000b\bi\n\u0005&q\u0016BZ\u0011\u001d\u0011\u0019k\na\u0001\u0005K\u000b!\u0001_:\u0011\tEs'q\u0015\t\u0004\u0019\n%Fa\u0002BVO\t\u0007!Q\u0016\u0002\u0002\u0005F\u00111\n\u0016\u0005\u0007\u0005c;\u0003\u0019\u0001;\u0002\u000bM$\u0018M\u001d;\t\r\tUv\u00051\u0001u\u0003\raWM\\\u0001\fg>\u0014H/\u00138QY\u0006\u001cW-\u0006\u0003\u0003<\nMGC\u0001B_)\u0011\t\u0019Oa0\t\u000f\t\u0005\u0007\u0006q\u0001\u0003D\u0006\u0019qN\u001d3\u0011\r\t\u0015'1\u001aBi\u001d\r\t&qY\u0005\u0004\u0005\u0013\u0014\u0015a\u00029bG.\fw-Z\u0005\u0005\u0005\u001b\u0014yM\u0001\u0005Pe\u0012,'/\u001b8h\u0015\r\u0011IM\u0011\t\u0004\u0019\nMGa\u0002BVQ\t\u0007!QV\u0001\u0006M>dG\r\\\u000b\u0005\u00053\u0014i\u000e\u0006\u0006\u0003\\\n}'\u0011\u001dBs\u0005S\u00042\u0001\u0014Bo\t\u0019\u0011Y+\u000bb\u0001\u001f\"1!\u0011W\u0015A\u0002QDaAa9*\u0001\u0004!\u0018aA3oI\"9!q]\u0015A\u0002\tm\u0017!\u0001>\t\u000f\t-\u0018\u00061\u0001\u0003n\u0006\u0011q\u000e\u001d\t\t#\n=(1\\&\u0003\\&\u0019!\u0011\u001f\"\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004fA\u0015\u0003vB!!q\u001fB}\u001b\t\t90\u0003\u0003\u0003|\u0006](a\u0002;bS2\u0014XmY\u0001\u0006M>dGM]\u000b\u0005\u0007\u0003\u0019)\u0001\u0006\u0006\u0004\u0004\r\u001d1\u0011BB\u0006\u0007\u001b\u00012\u0001TB\u0003\t\u0019\u0011YK\u000bb\u0001\u001f\"1!\u0011\u0017\u0016A\u0002QDaAa9+\u0001\u0004!\bb\u0002BtU\u0001\u000711\u0001\u0005\b\u0005WT\u0003\u0019AB\b!!\t&q^&\u0004\u0004\r\r\u0001f\u0001\u0016\u0003v\u0006Aam\u001c7e\u0019\u00164G/\u0006\u0003\u0004\u0018\ruA\u0003BB\r\u0007G!Baa\u0007\u0004 A\u0019Aj!\b\u0005\r\t-6F1\u0001P\u0011\u001d\u0011Yo\u000ba\u0001\u0007C\u0001\u0002\"\u0015Bx\u00077Y51\u0004\u0005\b\u0005O\\\u0003\u0019AB\u000e\u0003%1w\u000e\u001c3SS\u001eDG/\u0006\u0003\u0004*\r=B\u0003BB\u0016\u0007k!Ba!\f\u00042A\u0019Aja\f\u0005\r\t-FF1\u0001P\u0011\u001d\u0011Y\u000f\fa\u0001\u0007g\u0001\u0002\"\u0015Bx\u0017\u000e52Q\u0006\u0005\b\u0005Od\u0003\u0019AB\u0017\u0003)\u0011X\rZ;dK2+g\r^\u000b\u0005\u0007w\u0019y\u0004\u0006\u0003\u0004>\r\u0005\u0003c\u0001'\u0004@\u00119!1V\u0017C\u0002\t5\u0006b\u0002Bv[\u0001\u000711\t\t\t#\n=8QH&\u0004>\u0005Y!/\u001a3vG\u0016\u0014\u0016n\u001a5u+\u0011\u0019Ie!\u0014\u0015\t\r-3q\n\t\u0004\u0019\u000e5Ca\u0002BV]\t\u0007!Q\u0016\u0005\b\u0005Wt\u0003\u0019AB)!!\t&q^&\u0004L\r-\u0003f\u0002\u0001\u0004V\rm3Q\f\t\u0004#\u000e]\u0013bAB-\u0005\n\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\tU,!\u0011b,\t \u0011\u0006Y\u0011I\u001d:bs\n+hMZ3s!\tA\u0005g\u0005\u00031a\u000e\u0015\u0004\u0003\u00021\u0004huK1a!\u001bA\u0005e\u0019FO]5di>\u0003H/[7ju\u0016$7+Z9GC\u000e$xN]=\u0015\u0005\r\u0005\u0014A\u0005#fM\u0006,H\u000e^%oSRL\u0017\r\\*ju\u0016,\"a!\u001d\u0010\u0005\rMT$\u0001\t\u0002'\u0011+g-Y;mi&s\u0017\u000e^5bYNK'0\u001a\u0011\u0002\u0015\u0015l\u0007\u000f^=BeJ\f\u00170\u0001\u0003ge>lW\u0003BB?\u0007\u0007#Baa \u0004\u0006B!\u0001\nABA!\ra51\u0011\u0003\u0007\u0005W+$\u0019A(\t\u000f\r\u001dU\u00071\u0001\u0004\n\u0006!1m\u001c7m!\u0015\u0001'qBBA\u0003)qWm\u001e\"vS2$WM]\u000b\u0005\u0007\u001f\u001b)*\u0006\u0002\u0004\u0012B9\u0001J!\u001b\u0004\u0014\u000e]\u0005c\u0001'\u0004\u0016\u0012)aJ\u000eb\u0001\u001fB!\u0001\nABJ\u0003\u0015)W\u000e\u001d;z+\u0011\u0019ija)\u0016\u0005\r}\u0005\u0003\u0002%\u0001\u0007C\u00032\u0001TBR\t\u0015quG1\u0001P\u0003!\u0011Xm]5{KV\u0003H#\u0002;\u0004*\u000e5\u0006BBBVq\u0001\u0007A/\u0001\u0005beJ\f\u0017\u0010T3o\u0011\u0019\u0019y\u000b\u000fa\u0001i\u0006IA/\u0019:hKRdUM\u001c\u000b\b[\u000eM6QWB]\u0011\u0019\t9!\u000fa\u0001[\"11qW\u001dA\u0002Q\fqaY;s'&TX\r\u0003\u0004\u0004<f\u0002\r\u0001^\u0001\u000bi\u0006\u0014x-\u001a;TSj,\u0017A\u0003:fg&TX\rR8x]R)Ao!1\u0004D\"111\u0016\u001eA\u0002QDaaa,;\u0001\u0004!\u0018\u0001\u00033po:\u001c\u0018N_3\u0015\u000b5\u001cIma3\t\r\u0005\u001d1\b1\u0001n\u0011\u0019\u0019Yl\u000fa\u0001i\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u00111\u0011\u001b\t\u0005\u0005\u001b\u001b\u0019.\u0003\u0003\u0004V\n=%AB(cU\u0016\u001cG\u000fK\u00041\u0007+\u001aYf!7\u001f\u0003\rAsaLB+\u00077\u001aI\u000e"
)
public class ArrayBuffer extends AbstractBuffer implements IndexedBuffer, StrictOptimizedSeqOps, DefaultSerializable {
   private static final long serialVersionUID = -1582447879429021880L;
   private transient int mutationCount;
   private Object[] array;
   private int size0;

   public static Builder newBuilder() {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      return new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
   }

   public static ArrayBuffer from(final IterableOnce coll) {
      return ArrayBuffer$.MODULE$.from(coll);
   }

   public static int DefaultInitialSize() {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      return 16;
   }

   public static scala.collection.SeqOps tabulate(final int n, final Function1 f) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      tabulate_b.sizeHint(n);

      for(int tabulate_i = 0; tabulate_i < n; ++tabulate_i) {
         Object tabulate_$plus$eq_elem = f.apply(tabulate_i);
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static scala.collection.SeqOps fill(final int n, final Function0 elem) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      fill_b.sizeHint(n);

      for(int fill_i = 0; fill_i < n; ++fill_i) {
         Object fill_$plus$eq_elem = elem.apply();
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static scala.collection.SeqOps unapplySeq(final scala.collection.SeqOps x) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      return x;
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayBuffer)this.elems()).sizeHint(size);
               }

               public {
                  ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
               }
            };
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayBuffer)this.elems()).sizeHint(size);
                  }

                  public {
                     ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
                  }
               };
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Builder tabulate_b = new GrowableBuilder() {
                     public void sizeHint(final int size) {
                        ((ArrayBuffer)this.elems()).sizeHint(size);
                     }

                     public {
                        ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
                     }
                  };
                  tabulate_b.sizeHint(n5);

                  for(int tabulate_i = 0; tabulate_i < n5; ++tabulate_i) {
                     Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                     tabulate_b.addOne(tabulate_$plus$eq_elem);
                     tabulate_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var34 = (scala.collection.SeqOps)tabulate_b.result();
                  tabulate_b = null;
                  Object var33 = null;
                  Object tabulate_$plus$eq_elem = var34;
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var30 = null;
               Object tabulate_$plus$eq_elem = var35;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var27 = null;
            Object tabulate_$plus$eq_elem = var36;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var24 = null;
         Object tabulate_$plus$eq_elem = var37;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayBuffer)this.elems()).sizeHint(size);
               }

               public {
                  ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
               }
            };
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Builder tabulate_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayBuffer)this.elems()).sizeHint(size);
                  }

                  public {
                     ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
                  }
               };
               tabulate_b.sizeHint(n4);

               for(int tabulate_i = 0; tabulate_i < n4; ++tabulate_i) {
                  Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i, tabulate_i);
                  tabulate_b.addOne(tabulate_$plus$eq_elem);
                  tabulate_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var27 = (scala.collection.SeqOps)tabulate_b.result();
               tabulate_b = null;
               Object var26 = null;
               Object tabulate_$plus$eq_elem = var27;
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var23 = null;
            Object tabulate_$plus$eq_elem = var28;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var20 = null;
         Object tabulate_$plus$eq_elem = var29;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Builder tabulate_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayBuffer)this.elems()).sizeHint(size);
               }

               public {
                  ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
               }
            };
            tabulate_b.sizeHint(n3);

            for(int tabulate_i = 0; tabulate_i < n3; ++tabulate_i) {
               Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i, tabulate_i);
               tabulate_b.addOne(tabulate_$plus$eq_elem);
               tabulate_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var20 = (scala.collection.SeqOps)tabulate_b.result();
            tabulate_b = null;
            Object var19 = null;
            Object tabulate_$plus$eq_elem = var20;
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var16 = null;
         Object tabulate_$plus$eq_elem = var21;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder tabulate_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      tabulate_b.sizeHint(n1);

      for(int tabulate_i = 0; tabulate_i < n1; ++tabulate_i) {
         Builder tabulate_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         tabulate_b.sizeHint(n2);

         for(int tabulate_i = 0; tabulate_i < n2; ++tabulate_i) {
            Object tabulate_$plus$eq_elem = f.apply(tabulate_i, tabulate_i);
            tabulate_b.addOne(tabulate_$plus$eq_elem);
            tabulate_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var13 = (scala.collection.SeqOps)tabulate_b.result();
         tabulate_b = null;
         Object var12 = null;
         Object tabulate_$plus$eq_elem = var13;
         tabulate_b.addOne(tabulate_$plus$eq_elem);
         tabulate_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)tabulate_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayBuffer)this.elems()).sizeHint(size);
               }

               public {
                  ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
               }
            };
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayBuffer)this.elems()).sizeHint(size);
                  }

                  public {
                     ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
                  }
               };
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Builder fill_b = new GrowableBuilder() {
                     public void sizeHint(final int size) {
                        ((ArrayBuffer)this.elems()).sizeHint(size);
                     }

                     public {
                        ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
                     }
                  };
                  fill_b.sizeHint(n5);

                  for(int fill_i = 0; fill_i < n5; ++fill_i) {
                     Object fill_$plus$eq_elem = elem.apply();
                     fill_b.addOne(fill_$plus$eq_elem);
                     fill_$plus$eq_elem = null;
                  }

                  scala.collection.SeqOps var34 = (scala.collection.SeqOps)fill_b.result();
                  fill_b = null;
                  Object var33 = null;
                  Object fill_$plus$eq_elem = var34;
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var35 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var30 = null;
               Object fill_$plus$eq_elem = var35;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var36 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var27 = null;
            Object fill_$plus$eq_elem = var36;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var37 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var24 = null;
         Object fill_$plus$eq_elem = var37;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayBuffer)this.elems()).sizeHint(size);
               }

               public {
                  ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
               }
            };
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Builder fill_b = new GrowableBuilder() {
                  public void sizeHint(final int size) {
                     ((ArrayBuffer)this.elems()).sizeHint(size);
                  }

                  public {
                     ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
                  }
               };
               fill_b.sizeHint(n4);

               for(int fill_i = 0; fill_i < n4; ++fill_i) {
                  Object fill_$plus$eq_elem = elem.apply();
                  fill_b.addOne(fill_$plus$eq_elem);
                  fill_$plus$eq_elem = null;
               }

               scala.collection.SeqOps var27 = (scala.collection.SeqOps)fill_b.result();
               fill_b = null;
               Object var26 = null;
               Object fill_$plus$eq_elem = var27;
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var28 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var23 = null;
            Object fill_$plus$eq_elem = var28;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var29 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var20 = null;
         Object fill_$plus$eq_elem = var29;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Builder fill_b = new GrowableBuilder() {
               public void sizeHint(final int size) {
                  ((ArrayBuffer)this.elems()).sizeHint(size);
               }

               public {
                  ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
               }
            };
            fill_b.sizeHint(n3);

            for(int fill_i = 0; fill_i < n3; ++fill_i) {
               Object fill_$plus$eq_elem = elem.apply();
               fill_b.addOne(fill_$plus$eq_elem);
               fill_$plus$eq_elem = null;
            }

            scala.collection.SeqOps var20 = (scala.collection.SeqOps)fill_b.result();
            fill_b = null;
            Object var19 = null;
            Object fill_$plus$eq_elem = var20;
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var21 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var16 = null;
         Object fill_$plus$eq_elem = var21;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      ArrayBuffer$ var10000 = ArrayBuffer$.MODULE$;
      Builder fill_b = new GrowableBuilder() {
         public void sizeHint(final int size) {
            ((ArrayBuffer)this.elems()).sizeHint(size);
         }

         public {
            ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
         }
      };
      fill_b.sizeHint(n1);

      for(int fill_i = 0; fill_i < n1; ++fill_i) {
         Builder fill_b = new GrowableBuilder() {
            public void sizeHint(final int size) {
               ((ArrayBuffer)this.elems()).sizeHint(size);
            }

            public {
               ArrayBuffer$ var10001 = ArrayBuffer$.MODULE$;
            }
         };
         fill_b.sizeHint(n2);

         for(int fill_i = 0; fill_i < n2; ++fill_i) {
            Object fill_$plus$eq_elem = elem.apply();
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         scala.collection.SeqOps var13 = (scala.collection.SeqOps)fill_b.result();
         fill_b = null;
         Object var12 = null;
         Object fill_$plus$eq_elem = var13;
         fill_b.addOne(fill_$plus$eq_elem);
         fill_$plus$eq_elem = null;
      }

      return (scala.collection.SeqOps)fill_b.result();
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return IterableFactory.range$(ArrayBuffer$.MODULE$, start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return IterableFactory.range$(ArrayBuffer$.MODULE$, start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      ArrayBuffer$ unfold_this = ArrayBuffer$.MODULE$;
      IterableOnce from_source = new View.Unfold(init, f);
      return unfold_this.from(from_source);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      ArrayBuffer$ iterate_this = ArrayBuffer$.MODULE$;
      IterableOnce from_source = new View.Iterate(start, len, f);
      return iterate_this.from(from_source);
   }

   public Object writeReplace() {
      return DefaultSerializable.writeReplace$(this);
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

   public IndexedBuffer flatMapInPlace(final Function1 f) {
      return IndexedBuffer.flatMapInPlace$(this, f);
   }

   public IndexedBuffer filterInPlace(final Function1 p) {
      return IndexedBuffer.filterInPlace$(this, p);
   }

   public IndexedBuffer patchInPlace(final int from, final IterableOnce patch, final int replaced) {
      return IndexedBuffer.patchInPlace$(this, from, patch, replaced);
   }

   public IndexedSeqOps mapInPlace(final Function1 f) {
      return IndexedSeqOps.mapInPlace$(this, f);
   }

   public IndexedSeqOps sortInPlaceWith(final Function2 lt) {
      return IndexedSeqOps.sortInPlaceWith$(this, lt);
   }

   public IndexedSeqOps sortInPlaceBy(final Function1 f, final Ordering ord) {
      return IndexedSeqOps.sortInPlaceBy$(this, f, ord);
   }

   public Iterator iterator() {
      return scala.collection.IndexedSeqOps.iterator$(this);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object take(final int n) {
      return scala.collection.IndexedSeqOps.take$(this, n);
   }

   public Object drop(final int n) {
      return scala.collection.IndexedSeqOps.drop$(this, n);
   }

   public Object reverse() {
      return scala.collection.IndexedSeqOps.reverse$(this);
   }

   public Object slice(final int from, final int until) {
      return scala.collection.IndexedSeqOps.slice$(this, from, until);
   }

   public Object head() {
      return scala.collection.IndexedSeqOps.head$(this);
   }

   public Option headOption() {
      return scala.collection.IndexedSeqOps.headOption$(this);
   }

   public Object last() {
      return scala.collection.IndexedSeqOps.last$(this);
   }

   public final int lengthCompare(final int len) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, len);
   }

   public final int lengthCompare(final scala.collection.Iterable that) {
      return scala.collection.IndexedSeqOps.lengthCompare$(this, that);
   }

   public Searching.SearchResult search(final Object elem, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, ord);
   }

   public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
      return scala.collection.IndexedSeqOps.search$(this, elem, from, to, ord);
   }

   public Object[] array() {
      return this.array;
   }

   public void array_$eq(final Object[] x$1) {
      this.array = x$1;
   }

   public int size0() {
      return this.size0;
   }

   public void size0_$eq(final int x$1) {
      this.size0 = x$1;
   }

   public Stepper stepper(final StepperShape shape) {
      return shape.parUnbox(new ObjectArrayStepper(this.array(), 0, this.length()));
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public void ensureSize(final int n) {
      this.array_$eq(ArrayBuffer$.MODULE$.scala$collection$mutable$ArrayBuffer$$ensureSize(this.array(), this.size0(), n));
   }

   public void sizeHint(final int size) {
      if (size > this.length() && size >= 1) {
         this.ensureSize(size);
      }
   }

   private void reduceToSize(final int n) {
      ++this.mutationCount;
      Arrays.fill(this.array(), n, this.size0(), (Object)null);
      this.size0_$eq(n);
   }

   public void trimToSize() {
      this.resize(this.length());
   }

   private void resize(final int requiredLength) {
      this.array_$eq(ArrayBuffer$.MODULE$.scala$collection$mutable$ArrayBuffer$$downsize(this.array(), requiredLength));
   }

   private void checkWithinBounds(final int lo, final int hi) {
      if (lo < 0) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(lo, this.size0() - 1);
      } else if (hi > this.size0()) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(hi - 1, this.size0() - 1);
      }
   }

   public Object apply(final int n) {
      int checkWithinBounds_hi = n + 1;
      if (n < 0) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(n, this.size0() - 1);
      } else if (checkWithinBounds_hi > this.size0()) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(checkWithinBounds_hi - 1, this.size0() - 1);
      } else {
         return this.array()[n];
      }
   }

   public void update(final int index, final Object elem) {
      int checkWithinBounds_hi = index + 1;
      if (index < 0) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.size0() - 1);
      } else if (checkWithinBounds_hi > this.size0()) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(checkWithinBounds_hi - 1, this.size0() - 1);
      } else {
         ++this.mutationCount;
         this.array()[index] = elem;
      }
   }

   public int length() {
      return this.size0();
   }

   public ArrayBufferView view() {
      return new ArrayBufferView(this, () -> this.mutationCount);
   }

   public SeqFactory iterableFactory() {
      return ArrayBuffer$.MODULE$;
   }

   public void clear() {
      this.reduceToSize(0);
   }

   public ArrayBuffer clearAndShrink(final int size) {
      this.clear();
      this.resize(size);
      return this;
   }

   public int clearAndShrink$default$1() {
      return 16;
   }

   public ArrayBuffer addOne(final Object elem) {
      ++this.mutationCount;
      int newSize = this.size0() + 1;
      this.ensureSize(newSize);
      this.size0_$eq(newSize);
      this.update(this.size0() - 1, elem);
      return this;
   }

   public ArrayBuffer addAll(final IterableOnce elems) {
      if (elems instanceof ArrayBuffer) {
         ArrayBuffer var2 = (ArrayBuffer)elems;
         int elemsLength = var2.size0();
         if (elemsLength > 0) {
            ++this.mutationCount;
            this.ensureSize(this.size0() + elemsLength);
            Array$.MODULE$.copy(var2.array(), 0, this.array(), this.length(), elemsLength);
            this.size0_$eq(this.length() + elemsLength);
         }
      } else {
         Growable.addAll$(this, elems);
      }

      return this;
   }

   public void insert(final int index, final Object elem) {
      if (index < 0) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.size0() - 1);
      } else if (index > this.size0()) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index - 1, this.size0() - 1);
      } else {
         ++this.mutationCount;
         this.ensureSize(this.size0() + 1);
         Array$.MODULE$.copy(this.array(), index, this.array(), index + 1, this.size0() - index);
         this.size0_$eq(this.size0() + 1);
         this.update(index, elem);
      }
   }

   public ArrayBuffer prepend(final Object elem) {
      this.insert(0, elem);
      return this;
   }

   public void insertAll(final int index, final IterableOnce elems) {
      if (index < 0) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.size0() - 1);
      } else if (index > this.size0()) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index - 1, this.size0() - 1);
      } else if (elems instanceof scala.collection.Iterable) {
         scala.collection.Iterable var3 = (scala.collection.Iterable)elems;
         int elemsLength = var3.size();
         if (elemsLength > 0) {
            ++this.mutationCount;
            this.ensureSize(this.size0() + elemsLength);
            int len = this.size0();
            Array$.MODULE$.copy(this.array(), index, this.array(), index + elemsLength, len - index);
            IterableOnce$ var10000 = IterableOnce$.MODULE$;
            Object copyElemsToArray_xs = this.array();
            int var9 = var3.copyToArray(copyElemsToArray_xs, index, elemsLength);
            copyElemsToArray_xs = null;
            int actual = var9;
            if (actual != elemsLength) {
               throw new IllegalStateException((new java.lang.StringBuilder(11)).append("Copied ").append(actual).append(" of ").append(elemsLength).toString());
            } else {
               this.size0_$eq(len + elemsLength);
            }
         }
      } else {
         this.insertAll(index, ArrayBuffer$.MODULE$.from(elems));
      }
   }

   public Object remove(final int index) {
      int checkWithinBounds_hi = index + 1;
      if (index < 0) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.size0() - 1);
      } else if (checkWithinBounds_hi > this.size0()) {
         throw CommonErrors$.MODULE$.indexOutOfBounds(checkWithinBounds_hi - 1, this.size0() - 1);
      } else {
         Object res = this.apply(index);
         Array$.MODULE$.copy(this.array(), index + 1, this.array(), index, this.size0() - (index + 1));
         this.reduceToSize(this.size0() - 1);
         return res;
      }
   }

   public void remove(final int index, final int count) {
      if (count > 0) {
         int checkWithinBounds_hi = index + count;
         if (index < 0) {
            throw CommonErrors$.MODULE$.indexOutOfBounds(index, this.size0() - 1);
         } else if (checkWithinBounds_hi > this.size0()) {
            throw CommonErrors$.MODULE$.indexOutOfBounds(checkWithinBounds_hi - 1, this.size0() - 1);
         } else {
            Array$.MODULE$.copy(this.array(), index + count, this.array(), index, this.size0() - (index + count));
            this.reduceToSize(this.size0() - count);
         }
      } else if (count < 0) {
         throw new IllegalArgumentException((new java.lang.StringBuilder(38)).append("removing negative number of elements: ").append(count).toString());
      }
   }

   /** @deprecated */
   public ArrayBuffer result() {
      return this;
   }

   /** @deprecated */
   public Builder mapResult(final Function1 f) {
      return (new GrowableBuilder(this)).mapResult(f);
   }

   public String stringPrefix() {
      return "ArrayBuffer";
   }

   public int copyToArray(final Object xs, final int start, final int len) {
      IterableOnce$ var10000 = IterableOnce$.MODULE$;
      int var7 = this.length();
      int elemsToCopyToArray_destLen = Array.getLength(xs);
      int elemsToCopyToArray_srcLen = var7;
      scala.math.package$ var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      var8 = scala.math.package$.MODULE$;
      int copied = Math.max(Math.min(Math.min(len, elemsToCopyToArray_srcLen), elemsToCopyToArray_destLen - start), 0);
      if (copied > 0) {
         Array$.MODULE$.copy(this.array(), 0, xs, start, copied);
      }

      return copied;
   }

   public ArrayBuffer sortInPlace(final Ordering ord) {
      if (this.length() > 1) {
         ++this.mutationCount;
         Sorting$.MODULE$.stableSort(this.array(), 0, this.length(), ord);
      }

      return this;
   }

   private Object foldl(final int start, final int end, final Object z, final Function2 op) {
      while(start != end) {
         int var10000 = start + 1;
         Object var10002 = op.apply(z, this.array()[start]);
         op = op;
         z = var10002;
         end = end;
         start = var10000;
      }

      return z;
   }

   private Object foldr(final int start, final int end, final Object z, final Function2 op) {
      while(start != end) {
         int var10001 = end - 1;
         Object var10002 = op.apply(this.array()[end - 1], z);
         op = op;
         z = var10002;
         end = var10001;
         start = start;
      }

      return z;
   }

   public Object foldLeft(final Object z, final Function2 op) {
      int var10001 = this.length();
      Object foldl_z = z;
      int foldl_end = var10001;

      int var10000;
      for(int foldl_start = 0; foldl_start != foldl_end; foldl_start = var10000) {
         var10000 = foldl_start + 1;
         foldl_z = op.apply(foldl_z, this.array()[foldl_start]);
         foldl_end = foldl_end;
      }

      return foldl_z;
   }

   public Object foldRight(final Object z, final Function2 op) {
      int var10001 = this.length();
      Object foldr_z = z;
      int foldr_end = var10001;

      for(int foldr_start = 0; foldr_start != foldr_end; foldr_start = foldr_start) {
         var10001 = foldr_end - 1;
         foldr_z = op.apply(this.array()[foldr_end - 1], foldr_z);
         foldr_end = var10001;
      }

      return foldr_z;
   }

   public Object reduceLeft(final Function2 op) {
      if (this.length() > 0) {
         int var12 = this.length();
         Object foldl_z = this.array()[0];
         int foldl_end = var12;

         int var11;
         for(int foldl_start = 1; foldl_start != foldl_end; foldl_start = var11) {
            var11 = foldl_start + 1;
            foldl_z = op.apply(foldl_z, this.array()[foldl_start]);
            foldl_end = foldl_end;
         }

         return foldl_z;
      } else if (this.length() > 0) {
         Object reduceLeft_foldl_z = this.apply(0);
         int var10001 = this.length();
         Object reduceLeft_foldl_loop$1_acc = reduceLeft_foldl_z;
         int reduceLeft_foldl_loop$1_end = var10001;

         int var10000;
         for(int reduceLeft_foldl_loop$1_at = 1; reduceLeft_foldl_loop$1_at != reduceLeft_foldl_loop$1_end; reduceLeft_foldl_loop$1_at = var10000) {
            var10000 = reduceLeft_foldl_loop$1_at + 1;
            reduceLeft_foldl_loop$1_acc = op.apply(reduceLeft_foldl_loop$1_acc, this.apply(reduceLeft_foldl_loop$1_at));
            reduceLeft_foldl_loop$1_end = reduceLeft_foldl_loop$1_end;
         }

         return reduceLeft_foldl_loop$1_acc;
      } else if (this.knownSize() == 0) {
         throw new UnsupportedOperationException("empty.reduceLeft");
      } else {
         Iterator reduceLeft_reduceLeftIterator_it = this.iterator();
         if (!reduceLeft_reduceLeftIterator_it.hasNext()) {
            throw new UnsupportedOperationException("empty.reduceLeft");
         } else {
            Object reduceLeft_reduceLeftIterator_acc;
            for(reduceLeft_reduceLeftIterator_acc = reduceLeft_reduceLeftIterator_it.next(); reduceLeft_reduceLeftIterator_it.hasNext(); reduceLeft_reduceLeftIterator_acc = op.apply(reduceLeft_reduceLeftIterator_acc, reduceLeft_reduceLeftIterator_it.next())) {
            }

            return reduceLeft_reduceLeftIterator_acc;
         }
      }
   }

   public Object reduceRight(final Function2 op) {
      if (this.length() > 0) {
         int var10001 = this.length() - 1;
         Object foldr_z = this.array()[this.length() - 1];
         int foldr_end = var10001;

         for(int foldr_start = 0; foldr_start != foldr_end; foldr_start = foldr_start) {
            var10001 = foldr_end - 1;
            foldr_z = op.apply(this.array()[foldr_end - 1], foldr_z);
            foldr_end = var10001;
         }

         return foldr_z;
      } else if (this.length() <= 0) {
         if (this.knownSize() == 0) {
            throw new UnsupportedOperationException("empty.reduceRight");
         } else {
            return this.reversed().reduceLeft(IterableOnceOps::$anonfun$reduceRight$1);
         }
      } else {
         int var10000 = this.length() - 1;
         Object reduceRight_foldr_loop$2_acc = this.apply(this.length() - 1);

         for(int reduceRight_foldr_loop$2_at = var10000; reduceRight_foldr_loop$2_at != 0; reduceRight_foldr_loop$2_at = var10000) {
            var10000 = reduceRight_foldr_loop$2_at - 1;
            reduceRight_foldr_loop$2_acc = op.apply(this.apply(reduceRight_foldr_loop$2_at - 1), reduceRight_foldr_loop$2_acc);
         }

         return reduceRight_foldr_loop$2_acc;
      }
   }

   public ArrayBuffer(final Object[] initialElements, final int initialSize) {
      this.mutationCount = 0;
      this.array = initialElements;
      this.size0 = initialSize;
   }

   public ArrayBuffer() {
      this(new Object[16], 0);
   }

   public ArrayBuffer(final int initialSize) {
      RichInt$ var10001 = RichInt$.MODULE$;
      int max$extension_that = 1;
      scala.math.package$ var3 = scala.math.package$.MODULE$;
      this(new Object[Math.max(initialSize, max$extension_that)], 0);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
