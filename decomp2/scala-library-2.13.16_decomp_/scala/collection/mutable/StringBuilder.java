package scala.collection.mutable;

import java.io.Serializable;
import java.util.stream.IntStream;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.collection.IndexedSeqView;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Searching;
import scala.collection.SeqFactory;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.immutable.WrappedString;
import scala.collection.immutable.WrappedString$;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\r=d\u0001\u0002'N\u0005QC!\"!\u0005\u0001\u0005\u000b\u0007I\u0011AA\n\u0011)\tI\u0002\u0001B\u0001B\u0003%\u0011Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0011\u001d\tY\u0002\u0001C\u0001\u0003CAq!a\u0007\u0001\t\u0003\t\u0019\u0003C\u0004\u0002\u001c\u0001!\t!a\f\t\u000f\u0005m\u0001\u0001\"\u0001\u00026!9\u0011q\b\u0001\u0005\u0002\u0005\u0005\u0003bBA$\u0001\u0011E\u0013\u0011\n\u0005\b\u0003+\u0002A\u0011KA,\u0011\u001d\ty\u0006\u0001C!\u0003CBq!a\u0019\u0001\t\u0003\t)\u0007C\u0004\u0002p\u0001!\t!!\u001d\t\u000f\u0005u\u0004\u0001\"\u0011\u0002\u0000!9\u0011\u0011\u0011\u0001\u0005\u0002\u0005\r\u0005bBAF\u0001\u0011\u0005\u0011Q\u0012\u0005\b\u0003\u001f\u0003A\u0011AAI\u0011\u001d\tY\n\u0001C\u0001\u0003;Cq!!)\u0001\t\u0003\t\u0019\u000bC\u0004\u0002&\u0002!\t%a*\t\u000f\u0005%\u0006\u0001\"\u0011\u0002,\"9\u0011Q\u001b\u0001\u0005\u0002\u0005]\u0007bBAn\u0001\u0011\u0005\u0011Q\u001c\u0005\b\u0003G\u0004A\u0011AAs\u0011\u001d\t\u0019\u000f\u0001C\u0001\u0003SDq!a9\u0001\t\u0003\ti\u000fC\u0004\u0002d\u0002!\t!a=\t\u000f\u0005m\u0007\u0001\"\u0001\u0002x\"9\u00111\u001c\u0001\u0005\u0002\u0005m\bbBAn\u0001\u0011\u0005\u0011q \u0005\b\u0003G\u0004A\u0011\u0001B\u0006\u0011\u001d\t\u0019\u000f\u0001C\u0001\u0005+Aq!a9\u0001\t\u0003\u0011y\u0002C\u0004\u0002d\u0002!\tA!\u000b\t\u000f\u0005\r\b\u0001\"\u0001\u0003.!9\u00111\u001d\u0001\u0005\u0002\t]\u0002bBAr\u0001\u0011\u0005!\u0011\t\u0005\b\u0003G\u0004A\u0011\u0001B&\u0011\u001d\u0011y\u0005\u0001C\u0001\u0005#BqAa\u0017\u0001\t\u0003\u0011i\u0006C\u0004\u0003f\u0001!\tAa\u001a\t\u000f\tM\u0004\u0001\"\u0001\u0003v!9!1\u000f\u0001\u0005\u0002\tm\u0004b\u0002B3\u0001\u0011\u0005!\u0011\u0011\u0005\b\u0005K\u0002A\u0011\u0001BD\u0011\u001d\u0011\u0019\b\u0001C\u0001\u0005\u001bCqAa\u001d\u0001\t\u0003\u0011\u0019\nC\u0004\u0003t\u0001!\tA!'\t\u000f\tM\u0004\u0001\"\u0001\u0003 \"9!1\u000f\u0001\u0005\u0002\t\u0015\u0006b\u0002B:\u0001\u0011\u0005!1\u0016\u0005\b\u0005g\u0002A\u0011\u0001BY\u0011\u001d\u0011\u0019\b\u0001C\u0001\u0005oCqA!0\u0001\t\u0003\u0011y\fC\u0004\u0003D\u0002!\tA!2\t\u000f\t=\u0007\u0001\"\u0002\u0003R\"9!q\u001d\u0001\u0005\u0002\tE\u0007bBA\u0014\u0001\u0011\u0005\u0011q\u0010\u0005\b\u0005S\u0004A\u0011\u0001Bv\u0011\u001d\u0011\t\u0010\u0001C\u0001\u0005gDqAa>\u0001\t\u0003\u0011I\u0010C\u0004\u0003~\u0002!\tAa@\t\u000f\r\u001d\u0001\u0001\"\u0001\u0004\n!91q\u0001\u0001\u0005\u0002\r5\u0001bBB\n\u0001\u0011\u00051Q\u0003\u0005\b\u00077\u0001A\u0011AB\u000f\u0011\u001d\u0019Y\u0002\u0001C\u0001\u0007CAqa!\u000b\u0001\t\u0003\u0019Y\u0003C\u0004\u0004*\u0001!\taa\f\t\u000f\rU\u0002\u0001\"\u0011\u00048\u001d91QI'\t\u0002\r\u001dcA\u0002'N\u0011\u0003\u0019I\u0005C\u0004\u0002\u001c!#\taa\u0017\t\u000f\ru\u0003\n\"\u0001\u0002b!I1Q\r%\u0002\u0002\u0013%1q\r\u0002\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\u000b\u00059{\u0015aB7vi\u0006\u0014G.\u001a\u0006\u0003!F\u000b!bY8mY\u0016\u001cG/[8o\u0015\u0005\u0011\u0016!B:dC2\f7\u0001A\n\t\u0001Uk6N\\:x\u007fB\u0019akV-\u000e\u00035K!\u0001W'\u0003\u0017\u0005\u00137\u000f\u001e:bGR\u001cV-\u001d\t\u00035nk\u0011!U\u0005\u00039F\u0013Aa\u00115beB!aKX-a\u0013\tyVJA\bSKV\u001c\u0018M\u00197f\u0005VLG\u000eZ3s!\t\t\u0007N\u0004\u0002cMB\u00111-U\u0007\u0002I*\u0011QmU\u0001\u0007yI|w\u000e\u001e \n\u0005\u001d\f\u0016A\u0002)sK\u0012,g-\u0003\u0002jU\n11\u000b\u001e:j]\u001eT!aZ)\u0011\u0007Yc\u0017,\u0003\u0002n\u001b\nQ\u0011J\u001c3fq\u0016$7+Z9\u0011\u000bY{\u0017,\u001d:\n\u0005Al%!D%oI\u0016DX\rZ*fc>\u00038\u000f\u0005\u0002WYB\u0011a\u000b\u0001\t\u0005iVL\u0016/D\u0001P\u0013\t1xJA\fJi\u0016\u0014\u0018M\u00197f\r\u0006\u001cGo\u001c:z\t\u00164\u0017-\u001e7ugB\u0011\u00010`\u0007\u0002s*\u0011!p_\u0001\u0005Y\u0006twMC\u0001}\u0003\u0011Q\u0017M^1\n\u0005yL(\u0001D\"iCJ\u001cV-];f]\u000e,\u0007\u0003BA\u0001\u0003\u0017qA!a\u0001\u0002\b9\u00191-!\u0002\n\u0003IK1!!\u0003R\u0003\u001d\u0001\u0018mY6bO\u0016LA!!\u0004\u0002\u0010\ta1+\u001a:jC2L'0\u00192mK*\u0019\u0011\u0011B)\u0002\u0015UtG-\u001a:ms&tw-\u0006\u0002\u0002\u0016A\u0019\u00010a\u0006\n\u00051K\u0018aC;oI\u0016\u0014H._5oO\u0002\na\u0001P5oSRtDc\u0001:\u0002 !9\u0011\u0011C\u0002A\u0002\u0005UA#\u0001:\u0015\u0007I\f)\u0003C\u0004\u0002(\u0015\u0001\r!!\u000b\u0002\u0011\r\f\u0007/Y2jif\u00042AWA\u0016\u0013\r\ti#\u0015\u0002\u0004\u0013:$Hc\u0001:\u00022!1\u00111\u0007\u0004A\u0002\u0001\f1a\u001d;s)\u0015\u0011\u0018qGA\u001e\u0011\u001d\tId\u0002a\u0001\u0003S\tA\"\u001b8ji\u000e\u000b\u0007/Y2jifDa!!\u0010\b\u0001\u0004\u0001\u0017!C5oSR4\u0016\r\\;f\u0003\u0015\t\u0007\u000f\u001d7z)\rI\u00161\t\u0005\b\u0003\u000bB\u0001\u0019AA\u0015\u0003\u0005I\u0017\u0001\u00044s_6\u001c\u0006/Z2jM&\u001cGc\u0001:\u0002L!9\u0011QJ\u0005A\u0002\u0005=\u0013\u0001B2pY2\u0004B\u0001^A)3&\u0019\u00111K(\u0003\u0019%#XM]1cY\u0016|enY3\u0002%9,wo\u00159fG&4\u0017n\u0019\"vS2$WM]\u000b\u0003\u00033\u0002RAVA.3JL1!!\u0018N\u0005\u001d\u0011U/\u001b7eKJ\fQ!Z7qif,\u0012A]\u0001\u0007Y\u0016tw\r\u001e5\u0015\u0005\u0005%\u0002f\u0001\u0007\u0002jA\u0019!,a\u001b\n\u0007\u00055\u0014K\u0001\u0004j]2Lg.Z\u0001\u000bY\u0016tw\r\u001e5`I\u0015\fH\u0003BA:\u0003s\u00022AWA;\u0013\r\t9(\u0015\u0002\u0005+:LG\u000fC\u0004\u0002|5\u0001\r!!\u000b\u0002\u00039\f\u0011b\u001b8po:\u001c\u0016N_3\u0016\u0005\u0005%\u0012AB1eI>sW\r\u0006\u0003\u0002\u0006\u0006\u001dU\"\u0001\u0001\t\r\u0005%u\u00021\u0001Z\u0003\u0005A\u0018!B2mK\u0006\u0014HCAA:\u0003\u0019\tG\rZ!mYR!\u0011QQAJ\u0011\u001d\t)*\u0005a\u0001\u0003/\u000b\u0011a\u001d\t\u0004q\u0006e\u0015BA5z\u00035!\u0003\u000f\\;tIAdWo\u001d\u0013fcR!\u0011QQAP\u0011\u0019\t)J\u0005a\u0001A\u00061!/Z:vYR$\"!a&\u0002\u0011Q|7\u000b\u001e:j]\u001e$\u0012\u0001Y\u0001\bi>\f%O]1z+\u0011\ti+!/\u0015\t\u0005=\u0016Q\u0019\t\u00065\u0006E\u0016QW\u0005\u0004\u0003g\u000b&!B!se\u0006L\b\u0003BA\\\u0003sc\u0001\u0001B\u0004\u0002<V\u0011\r!!0\u0003\u0003\t\u000b2!WA`!\rQ\u0016\u0011Y\u0005\u0004\u0003\u0007\f&aA!os\"9\u0011qY\u000bA\u0004\u0005%\u0017AA2u!\u0019\tY-!5\u000266\u0011\u0011Q\u001a\u0006\u0004\u0003\u001f\f\u0016a\u0002:fM2,7\r^\u0005\u0005\u0003'\fiM\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003-!xn\u00115be\u0006\u0013(/Y=\u0016\u0005\u0005e\u0007\u0003\u0002.\u00022f\u000b\u0011\"\u00199qK:$\u0017\t\u001c7\u0015\t\u0005\u0015\u0015q\u001c\u0005\b\u0003C<\u0002\u0019AAL\u0003\tA8/\u0001\u0004baB,g\u000e\u001a\u000b\u0005\u0003\u000b\u000b9\u000fC\u0004\u0002\nb\u0001\r!a0\u0015\t\u0005\u0015\u00151\u001e\u0005\u0007\u0003+K\u0002\u0019\u00011\u0015\t\u0005\u0015\u0015q\u001e\u0005\u0007\u0003cT\u0002\u0019A<\u0002\u0005\r\u001cH\u0003BAC\u0003kDa!!&\u001c\u0001\u0004\u0011H\u0003BAC\u0003sDq!!9\u001d\u0001\u0004\ty\u0005\u0006\u0003\u0002\u0006\u0006u\bbBAq;\u0001\u0007\u0011\u0011\u001c\u000b\t\u0003\u000b\u0013\tAa\u0001\u0003\b!9\u0011\u0011\u001d\u0010A\u0002\u0005e\u0007b\u0002B\u0003=\u0001\u0007\u0011\u0011F\u0001\u0007_\u001a47/\u001a;\t\u000f\t%a\u00041\u0001\u0002*\u0005\u0019A.\u001a8\u0015\t\u0005\u0015%Q\u0002\u0005\b\u0003\u0013{\u0002\u0019\u0001B\b!\rQ&\u0011C\u0005\u0004\u0005'\t&a\u0002\"p_2,\u0017M\u001c\u000b\u0005\u0003\u000b\u00139\u0002C\u0004\u0002\n\u0002\u0002\rA!\u0007\u0011\u0007i\u0013Y\"C\u0002\u0003\u001eE\u0013AAQ=uKR!\u0011Q\u0011B\u0011\u0011\u001d\tI)\ta\u0001\u0005G\u00012A\u0017B\u0013\u0013\r\u00119#\u0015\u0002\u0006'\"|'\u000f\u001e\u000b\u0005\u0003\u000b\u0013Y\u0003C\u0004\u0002\n\n\u0002\r!!\u000b\u0015\t\u0005\u0015%q\u0006\u0005\b\u0003\u0013\u001b\u0003\u0019\u0001B\u0019!\rQ&1G\u0005\u0004\u0005k\t&\u0001\u0002'p]\u001e$B!!\"\u0003:!9\u0011\u0011\u0012\u0013A\u0002\tm\u0002c\u0001.\u0003>%\u0019!qH)\u0003\u000b\u0019cw.\u0019;\u0015\t\u0005\u0015%1\t\u0005\b\u0003\u0013+\u0003\u0019\u0001B#!\rQ&qI\u0005\u0004\u0005\u0013\n&A\u0002#pk\ndW\r\u0006\u0003\u0002\u0006\n5\u0003BBAEM\u0001\u0007\u0011,\u0001\u0004eK2,G/\u001a\u000b\u0007\u0003\u000b\u0013\u0019Fa\u0016\t\u000f\tUs\u00051\u0001\u0002*\u0005)1\u000f^1si\"9!\u0011L\u0014A\u0002\u0005%\u0012aA3oI\u00069!/\u001a9mC\u000e,G\u0003CAC\u0005?\u0012\tGa\u0019\t\u000f\tU\u0003\u00061\u0001\u0002*!9!\u0011\f\u0015A\u0002\u0005%\u0002bBA\u001aQ\u0001\u0007\u0011qS\u0001\nS:\u001cXM\u001d;BY2$\"\"!\"\u0003j\t5$q\u000eB9\u0011\u001d\u0011Y'\u000ba\u0001\u0003S\tQ!\u001b8eKbDq!a\r*\u0001\u0004\tI\u000eC\u0004\u0003\u0006%\u0002\r!!\u000b\t\u000f\t%\u0011\u00061\u0001\u0002*\u00051\u0011N\\:feR$b!!\"\u0003x\te\u0004b\u0002B6U\u0001\u0007\u0011\u0011\u0006\u0005\b\u0003\u0013S\u0003\u0019AA`)\u0019\t)I! \u0003\u0000!9!1N\u0016A\u0002\u0005%\u0002bBAEW\u0001\u0007\u0011q\u0013\u000b\u0007\u0003\u000b\u0013\u0019I!\"\t\u000f\t-D\u00061\u0001\u0002*!9\u0011\u0011\u001d\u0017A\u0002\u0005=CCBAC\u0005\u0013\u0013Y\tC\u0004\u0003l5\u0002\r!!\u000b\t\u000f\u0005\u0005X\u00061\u0001\u0002ZR1\u0011Q\u0011BH\u0005#CqAa\u001b/\u0001\u0004\tI\u0003C\u0004\u0002\n:\u0002\rAa\u0004\u0015\r\u0005\u0015%Q\u0013BL\u0011\u001d\u0011Yg\fa\u0001\u0003SAq!!#0\u0001\u0004\u0011I\u0002\u0006\u0004\u0002\u0006\nm%Q\u0014\u0005\b\u0005W\u0002\u0004\u0019AA\u0015\u0011\u001d\tI\t\ra\u0001\u0005G!b!!\"\u0003\"\n\r\u0006b\u0002B6c\u0001\u0007\u0011\u0011\u0006\u0005\b\u0003\u0013\u000b\u0004\u0019AA\u0015)\u0019\t)Ia*\u0003*\"9!1\u000e\u001aA\u0002\u0005%\u0002bBAEe\u0001\u0007!\u0011\u0007\u000b\u0007\u0003\u000b\u0013iKa,\t\u000f\t-4\u00071\u0001\u0002*!9\u0011\u0011R\u001aA\u0002\tmBCBAC\u0005g\u0013)\fC\u0004\u0003lQ\u0002\r!!\u000b\t\u000f\u0005%E\u00071\u0001\u0003FQ1\u0011Q\u0011B]\u0005wCqAa\u001b6\u0001\u0004\tI\u0003\u0003\u0004\u0002\nV\u0002\r!W\u0001\ng\u0016$H*\u001a8hi\"$B!a\u001d\u0003B\"9!\u0011\u0002\u001cA\u0002\u0005%\u0012AB;qI\u0006$X\r\u0006\u0004\u0002t\t\u001d'1\u001a\u0005\b\u0005\u0013<\u0004\u0019AA\u0015\u0003\rIG\r\u001f\u0005\u0007\u0005\u001b<\u0004\u0019A-\u0002\t\u0015dW-\\\u0001\u0010e\u00164XM]:f\u0007>tG/\u001a8ugR\u0011\u0011Q\u0011\u0015\fq\tU'1\u001cBo\u0005C\u0014\u0019\u000fE\u0002[\u0005/L1A!7R\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u0011y.\u0001\u000eVg\u0016\u0004#/\u001a<feN,\u0017J\u001c)mC\u000e,\u0007%\u001b8ti\u0016\fG-A\u0003tS:\u001cW-\t\u0002\u0003f\u00061!GL\u00194]A\naB]3wKJ\u001cX-\u00138QY\u0006\u001cW-\u0001\bf]N,(/Z\"ba\u0006\u001c\u0017\u000e^=\u0015\t\u0005M$Q\u001e\u0005\b\u0005_\\\u0004\u0019AA\u0015\u0003-qWm^\"ba\u0006\u001c\u0017\u000e^=\u0002\r\rD\u0017M]!u)\rI&Q\u001f\u0005\b\u0005Wb\u0004\u0019AA\u0015\u00031!W\r\\3uK\u000eC\u0017M]!u)\u0011\t)Ia?\t\u000f\t-T\b1\u0001\u0002*\u0005I1/\u001a;DQ\u0006\u0014\u0018\t\u001e\u000b\u0007\u0003\u000b\u001b\taa\u0001\t\u000f\t-d\b1\u0001\u0002*!11Q\u0001 A\u0002e\u000b!a\u00195\u0002\u0013M,(m\u001d;sS:<G\u0003BAL\u0007\u0017AqA!\u0016@\u0001\u0004\tI\u0003\u0006\u0004\u0002\u0018\u000e=1\u0011\u0003\u0005\b\u0005+\u0002\u0005\u0019AA\u0015\u0011\u001d\u0011I\u0006\u0011a\u0001\u0003S\t1b];c'\u0016\fX/\u001a8dKR)qoa\u0006\u0004\u001a!9!QK!A\u0002\u0005%\u0002b\u0002B-\u0003\u0002\u0007\u0011\u0011F\u0001\bS:$W\r_(g)\u0011\tIca\b\t\u000f\u0005M\"\t1\u0001\u0002\u0018R1\u0011\u0011FB\u0012\u0007KAq!a\rD\u0001\u0004\t9\nC\u0004\u0004(\r\u0003\r!!\u000b\u0002\u0013\u0019\u0014x.\\%oI\u0016D\u0018a\u00037bgRLe\u000eZ3y\u001f\u001a$B!!\u000b\u0004.!9\u00111\u0007#A\u0002\u0005]ECBA\u0015\u0007c\u0019\u0019\u0004C\u0004\u00024\u0015\u0003\r!a&\t\u000f\r\u001dR\t1\u0001\u0002*\u00059\u0011n]#naRLXC\u0001B\bQ\u001d\u000111HB!\u0007\u0007\u00022AWB\u001f\u0013\r\u0019y$\u0015\u0002\u0011'\u0016\u0014\u0018.\u00197WKJ\u001c\u0018n\u001c8V\u0013\u0012\u000bQA^1mk\u0016t\u0012aA\u0001\u000e'R\u0014\u0018N\\4Ck&dG-\u001a:\u0011\u0005YC5#\u0002%\u0004L\rE\u0003c\u0001.\u0004N%\u00191qJ)\u0003\r\u0005s\u0017PU3g!\u0011\u0019\u0019f!\u0017\u000e\u0005\rU#bAB,w\u0006\u0011\u0011n\\\u0005\u0005\u0003\u001b\u0019)\u0006\u0006\u0002\u0004H\u0005Qa.Z<Ck&dG-\u001a:)\u0017)\u0013)Na7\u0004b\t\u0005(1]\u0011\u0003\u0007G\nq(V:fA\u0001tWm\u001e\u0011TiJLgn\u001a\"vS2$WM\u001d\u0015*A\u0002Jgn\u001d;fC\u0012\u0004sN\u001a\u0011a'R\u0014\u0018N\\4Ck&dG-\u001a:/]\u0016<()^5mI\u0016\u0014\b-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0004jA\u0019\u0001pa\u001b\n\u0007\r5\u0014P\u0001\u0004PE*,7\r\u001e"
)
public final class StringBuilder extends AbstractSeq implements ReusableBuilder, IndexedSeq, CharSequence, Serializable {
   private static final long serialVersionUID = 3L;
   private final java.lang.StringBuilder underlying;

   /** @deprecated */
   public static StringBuilder newBuilder() {
      StringBuilder$ var10000 = StringBuilder$.MODULE$;
      return new StringBuilder();
   }

   public IntStream chars() {
      return super.chars();
   }

   public IntStream codePoints() {
      return super.codePoints();
   }

   public SeqFactory iterableFactory() {
      return IndexedSeq.iterableFactory$(this);
   }

   public IndexedSeqOps mapInPlace(final Function1 f) {
      return IndexedSeqOps.mapInPlace$(this, f);
   }

   public IndexedSeqOps sortInPlace(final Ordering ord) {
      return IndexedSeqOps.sortInPlace$(this, ord);
   }

   public IndexedSeqOps sortInPlaceWith(final Function2 lt) {
      return IndexedSeqOps.sortInPlaceWith$(this, lt);
   }

   public IndexedSeqOps sortInPlaceBy(final Function1 f, final Ordering ord) {
      return IndexedSeqOps.sortInPlaceBy$(this, f, ord);
   }

   public String stringPrefix() {
      return scala.collection.IndexedSeq.stringPrefix$(this);
   }

   public Iterator iterator() {
      return scala.collection.IndexedSeqOps.iterator$(this);
   }

   public Stepper stepper(final StepperShape shape) {
      return scala.collection.IndexedSeqOps.stepper$(this, shape);
   }

   public Iterator reverseIterator() {
      return scala.collection.IndexedSeqOps.reverseIterator$(this);
   }

   public Object foldRight(final Object z, final Function2 op) {
      return scala.collection.IndexedSeqOps.foldRight$(this, z, op);
   }

   public IndexedSeqView view() {
      return scala.collection.IndexedSeqOps.view$(this);
   }

   /** @deprecated */
   public IndexedSeqView view(final int from, final int until) {
      return scala.collection.IndexedSeqOps.view$(this, from, until);
   }

   public scala.collection.Iterable reversed() {
      return scala.collection.IndexedSeqOps.reversed$(this);
   }

   public Object prepended(final Object elem) {
      return scala.collection.IndexedSeqOps.prepended$(this, elem);
   }

   public Object take(final int n) {
      return scala.collection.IndexedSeqOps.take$(this, n);
   }

   public Object takeRight(final int n) {
      return scala.collection.IndexedSeqOps.takeRight$(this, n);
   }

   public Object drop(final int n) {
      return scala.collection.IndexedSeqOps.drop$(this, n);
   }

   public Object dropRight(final int n) {
      return scala.collection.IndexedSeqOps.dropRight$(this, n);
   }

   public Object map(final Function1 f) {
      return scala.collection.IndexedSeqOps.map$(this, f);
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
   public final Growable $plus$eq(final Object elem1, final Object elem2, final scala.collection.immutable.Seq elems) {
      return Growable.$plus$eq$(this, elem1, elem2, elems);
   }

   public Growable addAll(final IterableOnce elems) {
      return Growable.addAll$(this, elems);
   }

   public final Growable $plus$plus$eq(final IterableOnce elems) {
      return Growable.$plus$plus$eq$(this, elems);
   }

   public java.lang.StringBuilder underlying() {
      return this.underlying;
   }

   public char apply(final int i) {
      return this.underlying().charAt(i);
   }

   public StringBuilder fromSpecific(final IterableOnce coll) {
      return (new StringBuilder()).appendAll(coll);
   }

   public Builder newSpecificBuilder() {
      return new GrowableBuilder(new StringBuilder());
   }

   public StringBuilder empty() {
      return new StringBuilder();
   }

   public int length() {
      return this.underlying().length();
   }

   public void length_$eq(final int n) {
      this.underlying().setLength(n);
   }

   public int knownSize() {
      return scala.collection.IndexedSeqOps.knownSize$(this);
   }

   public StringBuilder addOne(final char x) {
      this.underlying().append(x);
      return this;
   }

   public void clear() {
      this.underlying().setLength(0);
   }

   public StringBuilder addAll(final String s) {
      this.underlying().append(s);
      return this;
   }

   public StringBuilder $plus$plus$eq(final String s) {
      return this.addAll(s);
   }

   public String result() {
      return this.underlying().toString();
   }

   public String toString() {
      return this.result();
   }

   public Object toArray(final ClassTag ct) {
      Class var2 = ct.runtimeClass();
      Class var10000 = Character.TYPE;
      if (var10000 == null) {
         if (var2 == null) {
            return this.toCharArray();
         }
      } else if (var10000.equals(var2)) {
         return this.toCharArray();
      }

      return IterableOnceOps.toArray$(this, ct);
   }

   public char[] toCharArray() {
      int len = this.underlying().length();
      char[] arr = new char[len];
      this.underlying().getChars(0, len, arr, 0);
      return arr;
   }

   public StringBuilder appendAll(final String xs) {
      this.underlying().append(xs);
      return this;
   }

   public StringBuilder append(final Object x) {
      this.underlying().append(String.valueOf(x));
      return this;
   }

   public StringBuilder append(final String s) {
      this.underlying().append(s);
      return this;
   }

   public StringBuilder append(final CharSequence cs) {
      this.underlying().append((CharSequence)(cs instanceof StringBuilder ? ((StringBuilder)cs).underlying() : cs));
      return this;
   }

   public StringBuilder append(final StringBuilder s) {
      this.underlying().append(s.underlying());
      return this;
   }

   public StringBuilder appendAll(final IterableOnce xs) {
      if (xs instanceof WrappedString) {
         WrappedString var2 = (WrappedString)xs;
         java.lang.StringBuilder var10000 = this.underlying();
         WrappedString.UnwrapOp$ var10001 = WrappedString.UnwrapOp$.MODULE$;
         WrappedString$ var8 = WrappedString$.MODULE$;
         var10000.append(var2.scala$collection$immutable$WrappedString$$self());
      } else if (xs instanceof ArraySeq.ofChar) {
         ArraySeq.ofChar var3 = (ArraySeq.ofChar)xs;
         this.underlying().append(var3.array());
      } else if (xs instanceof StringBuilder) {
         StringBuilder var4 = (StringBuilder)xs;
         this.underlying().append(var4.underlying());
      } else {
         int ks = xs.knownSize();
         if (ks != 0) {
            java.lang.StringBuilder b = this.underlying();
            if (ks > 0) {
               b.ensureCapacity(b.length() + ks);
            }

            Iterator it = xs.iterator();

            while(it.hasNext()) {
               b.append(BoxesRunTime.unboxToChar(it.next()));
            }
         }
      }

      return this;
   }

   public StringBuilder appendAll(final char[] xs) {
      this.underlying().append(xs);
      return this;
   }

   public StringBuilder appendAll(final char[] xs, final int offset, final int len) {
      this.underlying().append(xs, offset, len);
      return this;
   }

   public StringBuilder append(final boolean x) {
      this.underlying().append(x);
      return this;
   }

   public StringBuilder append(final byte x) {
      return this.append((int)x);
   }

   public StringBuilder append(final short x) {
      return this.append((int)x);
   }

   public StringBuilder append(final int x) {
      this.underlying().append(x);
      return this;
   }

   public StringBuilder append(final long x) {
      this.underlying().append(x);
      return this;
   }

   public StringBuilder append(final float x) {
      this.underlying().append(x);
      return this;
   }

   public StringBuilder append(final double x) {
      this.underlying().append(x);
      return this;
   }

   public StringBuilder append(final char x) {
      this.underlying().append(x);
      return this;
   }

   public StringBuilder delete(final int start, final int end) {
      this.underlying().delete(start, end);
      return this;
   }

   public StringBuilder replace(final int start, final int end, final String str) {
      this.underlying().replace(start, end, str);
      return this;
   }

   public StringBuilder insertAll(final int index, final char[] str, final int offset, final int len) {
      this.underlying().insert(index, str, offset, len);
      return this;
   }

   public StringBuilder insert(final int index, final Object x) {
      return this.insert(index, String.valueOf(x));
   }

   public StringBuilder insert(final int index, final String x) {
      this.underlying().insert(index, x);
      return this;
   }

   public StringBuilder insertAll(final int index, final IterableOnce xs) {
      Object var16;
      label113: {
         label115: {
            ArrayBuilder$ var10002 = ArrayBuilder$.MODULE$;
            ClassTag make_evidence$1 = ClassTag$.MODULE$.Char();
            Class var4 = make_evidence$1.runtimeClass();
            Class var7 = Byte.TYPE;
            if (var7 == null) {
               if (var4 == null) {
                  break label115;
               }
            } else if (var7.equals(var4)) {
               break label115;
            }

            label116: {
               var7 = Short.TYPE;
               if (var7 == null) {
                  if (var4 == null) {
                     break label116;
                  }
               } else if (var7.equals(var4)) {
                  break label116;
               }

               label117: {
                  var7 = Character.TYPE;
                  if (var7 == null) {
                     if (var4 == null) {
                        break label117;
                     }
                  } else if (var7.equals(var4)) {
                     break label117;
                  }

                  label118: {
                     var7 = Integer.TYPE;
                     if (var7 == null) {
                        if (var4 == null) {
                           break label118;
                        }
                     } else if (var7.equals(var4)) {
                        break label118;
                     }

                     label119: {
                        var7 = Long.TYPE;
                        if (var7 == null) {
                           if (var4 == null) {
                              break label119;
                           }
                        } else if (var7.equals(var4)) {
                           break label119;
                        }

                        label120: {
                           var7 = Float.TYPE;
                           if (var7 == null) {
                              if (var4 == null) {
                                 break label120;
                              }
                           } else if (var7.equals(var4)) {
                              break label120;
                           }

                           label121: {
                              var7 = Double.TYPE;
                              if (var7 == null) {
                                 if (var4 == null) {
                                    break label121;
                                 }
                              } else if (var7.equals(var4)) {
                                 break label121;
                              }

                              label122: {
                                 var7 = Boolean.TYPE;
                                 if (var7 == null) {
                                    if (var4 == null) {
                                       break label122;
                                    }
                                 } else if (var7.equals(var4)) {
                                    break label122;
                                 }

                                 label56: {
                                    var7 = Void.TYPE;
                                    if (var7 == null) {
                                       if (var4 == null) {
                                          break label56;
                                       }
                                    } else if (var7.equals(var4)) {
                                       break label56;
                                    }

                                    var16 = new ArrayBuilder.ofRef(make_evidence$1);
                                    break label113;
                                 }

                                 var16 = new ArrayBuilder.ofUnit();
                                 break label113;
                              }

                              var16 = new ArrayBuilder.ofBoolean();
                              break label113;
                           }

                           var16 = new ArrayBuilder.ofDouble();
                           break label113;
                        }

                        var16 = new ArrayBuilder.ofFloat();
                        break label113;
                     }

                     var16 = new ArrayBuilder.ofLong();
                     break label113;
                  }

                  var16 = new ArrayBuilder.ofInt();
                  break label113;
               }

               var16 = new ArrayBuilder.ofChar();
               break label113;
            }

            var16 = new ArrayBuilder.ofShort();
            break label113;
         }

         var16 = new ArrayBuilder.ofByte();
      }

      Object var5 = null;
      Object var6 = null;
      return this.insertAll(index, (char[])((ReusableBuilder)((Growable)var16).addAll(xs)).result());
   }

   public StringBuilder insertAll(final int index, final char[] xs) {
      this.underlying().insert(index, xs);
      return this;
   }

   public StringBuilder insert(final int index, final boolean x) {
      return this.insert(index, String.valueOf(x));
   }

   public StringBuilder insert(final int index, final byte x) {
      return this.insert(index, (int)x);
   }

   public StringBuilder insert(final int index, final short x) {
      return this.insert(index, (int)x);
   }

   public StringBuilder insert(final int index, final int x) {
      return this.insert(index, String.valueOf(x));
   }

   public StringBuilder insert(final int index, final long x) {
      return this.insert(index, String.valueOf(x));
   }

   public StringBuilder insert(final int index, final float x) {
      return this.insert(index, String.valueOf(x));
   }

   public StringBuilder insert(final int index, final double x) {
      return this.insert(index, String.valueOf(x));
   }

   public StringBuilder insert(final int index, final char x) {
      return this.insert(index, String.valueOf(x));
   }

   public void setLength(final int len) {
      this.underlying().setLength(len);
   }

   public void update(final int idx, final char elem) {
      this.underlying().setCharAt(idx, elem);
   }

   /** @deprecated */
   public final StringBuilder reverseContents() {
      return this.reverseInPlace();
   }

   public StringBuilder reverseInPlace() {
      this.underlying().reverse();
      return this;
   }

   public int capacity() {
      return this.underlying().capacity();
   }

   public void ensureCapacity(final int newCapacity) {
      this.underlying().ensureCapacity(newCapacity);
   }

   public char charAt(final int index) {
      return this.underlying().charAt(index);
   }

   public StringBuilder deleteCharAt(final int index) {
      this.underlying().deleteCharAt(index);
      return this;
   }

   public StringBuilder setCharAt(final int index, final char ch) {
      this.underlying().setCharAt(index, ch);
      return this;
   }

   public String substring(final int start) {
      return this.underlying().substring(start, this.underlying().length());
   }

   public String substring(final int start, final int end) {
      return this.underlying().substring(start, end);
   }

   public CharSequence subSequence(final int start, final int end) {
      return this.underlying().substring(start, end);
   }

   public int indexOf(final String str) {
      return this.underlying().indexOf(str);
   }

   public int indexOf(final String str, final int fromIndex) {
      return this.underlying().indexOf(str, fromIndex);
   }

   public int lastIndexOf(final String str) {
      return this.underlying().lastIndexOf(str);
   }

   public int lastIndexOf(final String str, final int fromIndex) {
      return this.underlying().lastIndexOf(str, fromIndex);
   }

   public boolean isEmpty() {
      return this.underlying().length() == 0;
   }

   public StringBuilder(final java.lang.StringBuilder underlying) {
      this.underlying = underlying;
   }

   public StringBuilder() {
      this(new java.lang.StringBuilder());
   }

   public StringBuilder(final int capacity) {
      this(new java.lang.StringBuilder(capacity));
   }

   public StringBuilder(final String str) {
      this(new java.lang.StringBuilder(str));
   }

   public StringBuilder(final int initCapacity, final String initValue) {
      this((new java.lang.StringBuilder(initValue.length() + initCapacity)).append(initValue));
   }
}
