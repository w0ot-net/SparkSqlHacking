package scala.collection.immutable;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.None;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.MapFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StrictOptimizedMapOps;
import scala.collection.Hashing.;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0019}dA\u0002?~\u0003C\tI\u0001C\u0004\u0002l\u0001!\t!!\u001c\t\u000f\u0005=\u0004\u0001\"\u0011\u0002r!9\u0011\u0011\u0010\u0001\u0005\u0006\u0005m\u0004bBAA\u0001\u0011\u0015\u00111\u0011\u0005\b\u0003/\u0003AQIAM\u0011\u001d\t\t\f\u0001C\u0001\u0003gCq!!0\u0001\t\u0003\ty\fC\u0004\u0002H\u0002!\t!!3\t\u0011\ru\u0001A\"\u0005\u0000\r\u0003Aqa!\u0010\u0001\r#1Y\u0002\u0003\u0005\u0004\u0004\u00011\tb D\u0012\u0011\u001d\u0019I\n\u0001D\t\rWAqa!\u0013\u0001\r#1i\u0004C\u0004\u0004\u0014\u00011\tB\"\u0015\t\u000f\u0019e\u0003\u0001\"\u0012\u0007\\!9AQ\u000e\u0001\u0005B\u0019}\u0003b\u0002C:\u0001\u0011\u0005cq\f\u0005\b\rC\u0002A\u0011\tD2\u0011\u001d1I\u0007\u0001C!\rWB\u0001Bb\u001c\u0001A\u0013Ec\u0011O\u0004\b\u0003;l\b\u0012AAp\r\u0019aX\u0010#\u0001\u0002b\"9\u00111\u000e\f\u0005\u0002\u0005%\bbBAv-\u0011\u0005\u0011Q\u001e\u0005\b\u0003w4B\u0011AA\u007f\u0011\u001d\u00119B\u0006C\u0001\u000531\u0001B!\u000e\u0017\u0003\u0003y(q\u0007\u0005\b\u0003WZB\u0011\u0001B\u001e\u0011\u001d\u0011ie\u0007D\u0001\u0005\u001fBqAa\u0017\u001c\r\u0003\u0011i&\u0002\u0004\u0003`Y!!\u0011\r\u0005\b\u0005o2B\u0011\u0002B=\u0011!\u0011YI\u0006Q\u0001\n\t5\u0005\u0002\u0003BH-\u0001&IA!%\t\u000f\t\rf\u0003\"\u0003\u0003&\"91Q\u001a\f\u0005\n\r=\u0007bBBk-\u0011%1q[\u0004\b\u0007W4\u0002\u0012BBw\r\u001d\u0019yO\u0006E\u0005\u0007cDq!a\u001b(\t\u0003\u0019)\u0010C\u0004\u0003x\u001e\"\tE!?\t\u000f\r\u0005q\u0005\"\u0011\u0003R\"A1QD\u0014\u0005\u0012}\u001c9\u0010C\u0004\u0004>\u001d\"\t\u0002\"\u0005\t\u0011\r\rq\u0005\"\u0005\u0000\t3Aqa!\u0013(\t#!\u0019\u0003C\u0004\u0004\u0014\u001d\"\t\u0002\"\u000e\t\u000f\reu\u0005\"\u0005\u0005>!91qM\u0014\u0005\u0002\u0011=\u0003bBB9O\u0011\u0005C1\u000b\u0005\b\t?:C\u0011\tC1\u0011\u001d!\u0019g\nC!\tKBq\u0001\"\u001c(\t\u0003\"y\u0007C\u0004\u0005r\u001d\"\t\u0005\"\u0019\t\u000f\u0011Mt\u0005\"\u0011\u0005p!IAQO\u0014\u0002\u0002\u0013%Aq\u000f\u0004\u0007\t\u00133\"\u0001b#\t\u0017\u0005}\u0014H!b\u0001\n\u0003yH\u0011\u0014\u0005\u000b\t7K$\u0011!Q\u0001\n\u0011E\u0005bCB\u0007s\t\u0015\r\u0011\"\u0001\u0000\u0005#D!\u0002\"(:\u0005\u0003\u0005\u000b\u0011\u0002Bj\u0011-\t)*\u000fBC\u0002\u0013\u0005q\u0010b(\t\u0015\u0011\u0005\u0016H!A!\u0002\u0013!)\nC\u0006\u0002&f\u0012\t\u0019!C\u0001\u007f\u0012\r\u0006b\u0003CUs\t\u0005\r\u0011\"\u0001\u0000\tWC!\u0002\"-:\u0005\u0003\u0005\u000b\u0015\u0002CS\u0011\u001d\tY'\u000fC\u0001\tgCqAa>:\t\u0003\u0012I\u0010C\u0004\u0004he\"\t\u0001b0\t\u0011\r\r\u0011\b\"\u0005\u0000\t\u000bDqA!>:\t\u0003\u0012\t\u000eC\u0004\u0004\u0002e\"\tE!5\t\u0011\u0011=\u0017\b\"\u0001\u0000\t3C\u0001\u0002\"5:\t\u0003y(\u0011\u001b\u0005\t\t'LD\u0011A@\u0005V\"911C\u001d\u0005\u0012\u0011m\u0007\u0002CB\u000fs\u0011Eq\u0010b9\t\u000f\ru\u0012\b\"\u0005\u0005\u0000\"91\u0011J\u001d\u0005\u0012\u0015\u001d\u0001bBB9s\u0011\u0005S\u0011\u0004\u0005\t\u000bKID\u0011\u0001\f\u0006(!91\u0011T\u001d\u0005\u0012\u0015%baBC\u001e-\u0001yXQ\b\u0005\f\u0007\u001b\u0019&Q1A\u0005\u0002}\u0014\t\u000e\u0003\u0006\u0005\u001eN\u0013\t\u0011)A\u0005\u0005'D!\"\"\u0014T\u0005\u000b\u0007I\u0011AC(\u0011))9f\u0015B\u0001B\u0003%Q\u0011\u000b\u0005\b\u0003W\u001aF\u0011AC-\u0011\u001d\u0011)p\u0015C!\u0005#DqAa>T\t\u0003\u0012I\u0010\u0003\u0005\u0004\u0004M#\tb`C1\u0011\u001d\u0019\u0019b\u0015C\t\u000bWB\u0001b!\bT\t#zX1\u000f\u0005\b\u0007{\u0019F\u0011ICH\u0011\u001d\u0019Ie\u0015C)\u000b3Cqa!'T\t#)Y\u000bC\u0004\u0004hM#\t%\"0\t\u000f\rE4\u000b\"\u0011\u0006B\"9\u0011QX*\u0005B\u00155gA\u0002BV-\t\u0011i\u000bC\u0006\u0003P\u0012\u0014)\u0019!C\u0001\u007f\nE\u0007B\u0003BmI\n\u0005\t\u0015!\u0003\u0003T\"Y!1\u001c3\u0003\u0006\u0004%\ta Bo\u0011)\u0011)\u000f\u001aB\u0001B\u0003%!q\u001c\u0005\f\u0005O$'Q1A\u0005\u0002}\u0014\t\u000e\u0003\u0006\u0003j\u0012\u0014\t\u0011)A\u0005\u0005'Dq!a\u001be\t\u0003\u0011Y\u000fC\u0004\u0003v\u0012$\tE!5\t\u000f\t]H\r\"\u0011\u0003z\"91\u0011\u00013\u0005B\tE\u0007\u0002CB\u0002I\u0012Eqp!\u0002\t\u000f\rMA\r\"\u0005\u0004\u0016!A1Q\u00043\u0005\u0012}\u001cy\u0002C\u0004\u0004>\u0011$\tea\u0010\t\u000f\r%C\r\"\u0005\u0004L!91q\r3\u0005B\r%\u0004bBB9I\u0012\u000531\u000f\u0005\b\u0007\u0013#G\u0011BBF\u0011\u001d\ti\f\u001aC!\u0007+Cqa!'e\t#\u0019Y\n\u0003\u0005\u0006RZ\u0001K\u0011BCj\u0011!))O\u0006Q\u0005\n\u0015\u001d\b\"\u0003C;-\u0005\u0005I\u0011\u0002C<\u0005)yE\u000e\u001a%bg\"l\u0015\r\u001d\u0006\u0003}~\f\u0011\"[7nkR\f'\r\\3\u000b\t\u0005\u0005\u00111A\u0001\u000bG>dG.Z2uS>t'BAA\u0003\u0003\u0015\u00198-\u00197b\u0007\u0001)b!a\u0003\u0002\u001a\u0005=2#\u0004\u0001\u0002\u000e\u0005M\u0012QHA)\u0003/\ni\u0006\u0005\u0005\u0002\u0010\u0005E\u0011QCA\u0017\u001b\u0005i\u0018bAA\n{\nY\u0011IY:ue\u0006\u001cG/T1q!\u0011\t9\"!\u0007\r\u0001\u00119\u00111\u0004\u0001C\u0002\u0005u!!A&\u0012\t\u0005}\u0011q\u0005\t\u0005\u0003C\t\u0019#\u0004\u0002\u0002\u0004%!\u0011QEA\u0002\u0005\u001dqu\u000e\u001e5j]\u001e\u0004B!!\t\u0002*%!\u00111FA\u0002\u0005\r\te.\u001f\t\u0005\u0003/\ty\u0003\u0002\u0005\u00022\u0001!)\u0019AA\u000f\u0005\u00051\u0006\u0003DA\b\u0003k\t)\"!\f\u0002:\u0005m\u0012bAA\u001c{\n1Q*\u00199PaN\u00042!a\u0004\u0001!\u001d\ty\u0001AA\u000b\u0003[\u0001\"\"a\u0010\u0002B\u0005\u0015\u00131JA\u001e\u001b\u0005y\u0018bAA\"\u007f\nQ2\u000b\u001e:jGR|\u0005\u000f^5nSj,G-\u0013;fe\u0006\u0014G.Z(qgBA\u0011\u0011EA$\u0003+\ti#\u0003\u0003\u0002J\u0005\r!A\u0002+va2,'\u0007\u0005\u0003\u0002\u0010\u00055\u0013bAA({\nA\u0011\n^3sC\ndW\r\u0005\u0007\u0002@\u0005M\u0013QCA\u0017\u0003s\tY$C\u0002\u0002V}\u0014Qc\u0015;sS\u000e$x\n\u001d;j[&TX\rZ'ba>\u00038\u000f\u0005\u0007\u0002@\u0005e\u0013QCA\u0017\u0003s\tY%C\u0002\u0002\\}\u0014!#T1q\r\u0006\u001cGo\u001c:z\t\u00164\u0017-\u001e7ugB!\u0011qLA3\u001d\u0011\t\t#!\u0019\n\t\u0005\r\u00141A\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\t9'!\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\t\u0005\r\u00141A\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005m\u0012AC7ba\u001a\u000b7\r^8ssV\u0011\u00111\u000f\t\u0007\u0003\u007f\t)(!\u000f\n\u0007\u0005]tP\u0001\u0006NCB4\u0015m\u0019;pef\fqA]3n_Z,G\r\u0006\u0003\u0002<\u0005u\u0004bBA@\u0007\u0001\u0007\u0011QC\u0001\u0004W\u0016L\u0018aB;qI\u0006$X\rZ\u000b\u0005\u0003\u000b\u000bY\t\u0006\u0004\u0002\b\u0006E\u00151\u0013\t\b\u0003\u001f\u0001\u0011QCAE!\u0011\t9\"a#\u0005\u000f\u00055EA1\u0001\u0002\u0010\n\u0011a+M\t\u0005\u0003[\t9\u0003C\u0004\u0002\u0000\u0011\u0001\r!!\u0006\t\u000f\u0005UE\u00011\u0001\u0002\n\u0006)a/\u00197vK\u0006)A\u0005\u001d7vgV!\u00111TAQ)\u0011\ti*a)\u0011\u000f\u0005=\u0001!!\u0006\u0002 B!\u0011qCAQ\t\u001d\ti)\u0002b\u0001\u0003\u001fCq!!*\u0006\u0001\u0004\t9+\u0001\u0002lmBA\u0011\u0011EA$\u0003+\ty\nK\u0002\u0006\u0003W\u0003B!!\t\u0002.&!\u0011qVA\u0002\u0005\u0019Ig\u000e\\5oK\u0006\u0019q-\u001a;\u0015\t\u0005U\u00161\u0018\t\u0007\u0003C\t9,!\f\n\t\u0005e\u00161\u0001\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u0005}d\u00011\u0001\u0002\u0016\u0005)1\u000f\u001d7jiV\u0011\u0011\u0011\u0019\t\u0007\u0003\u001f\t\u0019-a\u000f\n\u0007\u0005\u0015WPA\u0002TKF\fa!\\3sO\u0016$W\u0003BAf\u0003'$B!!4\u0006\u0000R!\u0011qZAk!\u001d\ty\u0001AA\u000b\u0003#\u0004B!a\u0006\u0002T\u00129\u0011Q\u0012\u0005C\u0002\u0005=\u0005bBAl\u0011\u0001\u0007\u0011\u0011\\\u0001\u0007[\u0016\u0014x-\u001a4\u0011\u000f\u0005mw$!\u0006\u0002R:\u0019\u0011qB\u000b\u0002\u0015=cG\rS1tQ6\u000b\u0007\u000fE\u0002\u0002\u0010Y\u0019RAFAr\u0003g\u0002B!!\t\u0002f&!\u0011q]A\u0002\u0005\u0019\te.\u001f*fMR\u0011\u0011q\\\u0001\u0006K6\u0004H/_\u000b\u0007\u0003_\f)0!?\u0016\u0005\u0005E\bcBA\b\u0001\u0005M\u0018q\u001f\t\u0005\u0003/\t)\u0010B\u0004\u0002\u001ca\u0011\r!!\b\u0011\t\u0005]\u0011\u0011 \u0003\b\u0003cA\"\u0019AA\u000f\u0003\u00111'o\\7\u0016\r\u0005}(Q\u0001B\u0005)\u0011\u0011\tAa\u0003\u0011\u000f\u0005=\u0001Aa\u0001\u0003\bA!\u0011q\u0003B\u0003\t\u001d\tY\"\u0007b\u0001\u0003;\u0001B!a\u0006\u0003\n\u00119\u0011\u0011G\rC\u0002\u0005u\u0001b\u0002B\u00073\u0001\u0007!qB\u0001\u0003SR\u0004b!a\u0010\u0003\u0012\tU\u0011b\u0001B\n\u007f\na\u0011\n^3sC\ndWm\u00148dKBA\u0011\u0011EA$\u0005\u0007\u00119!\u0001\u0006oK^\u0014U/\u001b7eKJ,bAa\u0007\u0003.\tERC\u0001B\u000f!!\u0011yB!\n\u0003*\tMRB\u0001B\u0011\u0015\r\u0011\u0019c`\u0001\b[V$\u0018M\u00197f\u0013\u0011\u00119C!\t\u0003\u000f\t+\u0018\u000e\u001c3feBA\u0011\u0011EA$\u0005W\u0011y\u0003\u0005\u0003\u0002\u0018\t5BaBA\u000e5\t\u0007\u0011Q\u0004\t\u0005\u0003/\u0011\t\u0004B\u0004\u00022i\u0011\r!!\b\u0011\u000f\u0005=\u0001Aa\u000b\u00030\t1Q*\u001a:hKJ,bA!\u000f\u0003D\t%3cA\u000e\u0002dR\u0011!Q\b\t\b\u0005\u007fY\"\u0011\tB$\u001b\u00051\u0002\u0003BA\f\u0005\u0007\"qA!\u0012\u001c\u0005\u0004\tiBA\u0001B!\u0011\t9B!\u0013\u0005\u000f\t-3D1\u0001\u0002\u001e\t\t!)A\u0003baBd\u0017\u0010\u0006\u0004\u0003R\tM#q\u000b\t\t\u0003C\t9E!\u0011\u0003H!9!QK\u000fA\u0002\tE\u0013aA6wc!9!\u0011L\u000fA\u0002\tE\u0013aA6we\u00051\u0011N\u001c<feR,\"A!\u0010\u0003\u001b5+'oZ3Gk:\u001cG/[8o+\u0019\u0011\u0019G!\u001c\u0003tAQ\u0011\u0011\u0005B3\u0005S\u0012IG!\u001b\n\t\t\u001d\u00141\u0001\u0002\n\rVt7\r^5p]J\u0002\u0002\"!\t\u0002H\t-$\u0011\u000f\t\u0005\u0003/\u0011i\u0007B\u0004\u0003p}\u0011\r!!\b\u0003\u0005\u0005\u000b\u0004\u0003BA\f\u0005g\"qA!\u001e \u0005\u0004\tiB\u0001\u0002Cc\u0005QA.\u001b4u\u001b\u0016\u0014x-\u001a:\u0016\r\tm$\u0011\u0011BC)\u0011\u0011iHa\"\u0011\u000f\t}2Da \u0003\u0004B!\u0011q\u0003BA\t\u001d\u0011y\u0007\tb\u0001\u0003;\u0001B!a\u0006\u0003\u0006\u00129!Q\u000f\u0011C\u0002\u0005u\u0001bBAlA\u0001\u0007!\u0011\u0012\t\b\u0005\u007fy\"q\u0010BB\u00035!WMZ1vYRlUM]4feB9!qH\u000e\u0002(\u0005\u001d\u0012a\u00037jMRlUM]4feB*bAa%\u0003\u001a\nuE\u0003\u0002BK\u0005?\u0003rAa\u0010\u001c\u0005/\u0013Y\n\u0005\u0003\u0002\u0018\teEa\u0002B8E\t\u0007\u0011Q\u0004\t\u0005\u0003/\u0011i\nB\u0004\u0003v\t\u0012\r!!\b\t\u000f\u0005]'\u00051\u0001\u0003\"B9!qH\u0010\u0003\u0018\nm\u0015aD7bW\u0016D\u0015m\u001d5Ue&,W*\u00199\u0016\r\t\u001d6\u0011WB[)9\u0011Ika.\u0004<\u000e\u00057QYBe\u0007\u0017\u0004rAa\u0010e\u0007_\u001b\u0019LA\u0006ICNDGK]5f\u001b\u0006\u0004XC\u0002BX\u0005k\u0013YlE\u0002e\u0005c\u0003r!a\u0004\u0001\u0005g\u00139\f\u0005\u0003\u0002\u0018\tUFaBA\u000eI\n\u0007\u0011Q\u0004\u0016\u0005\u0005s\u0013i\f\u0005\u0003\u0002\u0018\tmF\u0001CA\u0019I\u0012\u0015\r!!\b,\u0005\t}\u0006\u0003\u0002Ba\u0005\u0017l!Aa1\u000b\t\t\u0015'qY\u0001\nk:\u001c\u0007.Z2lK\u0012TAA!3\u0002\u0004\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\t\t5'1\u0019\u0002\u0012k:\u001c\u0007.Z2lK\u00124\u0016M]5b]\u000e,\u0017A\u00022ji6\f\u0007/\u0006\u0002\u0003TB!\u0011\u0011\u0005Bk\u0013\u0011\u00119.a\u0001\u0003\u0007%sG/A\u0004cSRl\u0017\r\u001d\u0011\u0002\u000b\u0015dW-\\:\u0016\u0005\t}\u0007CBA\u0011\u0005C\u0014\t,\u0003\u0003\u0003d\u0006\r!!B!se\u0006L\u0018AB3mK6\u001c\b%A\u0003tSj,\u0007'\u0001\u0004tSj,\u0007\u0007\t\u000b\t\u0005[\u0014yO!=\u0003tB9!q\b3\u00034\ne\u0006b\u0002BhW\u0002\u0007!1\u001b\u0005\b\u00057\\\u0007\u0019\u0001Bp\u0011\u001d\u00119o\u001ba\u0001\u0005'\fAa]5{K\u00069\u0011n]#naRLXC\u0001B~!\u0011\t\tC!@\n\t\t}\u00181\u0001\u0002\b\u0005>|G.Z1o\u0003%Ygn\\<o'&TX-\u0001\u0003hKR\u0004D\u0003CB\u0004\u0007\u0013\u0019Yaa\u0004\u0011\r\u0005\u0005\u0012q\u0017B]\u0011\u001d\tyh\u001ca\u0001\u0005gCqa!\u0004p\u0001\u0004\u0011\u0019.\u0001\u0003iCND\u0007bBB\t_\u0002\u0007!1[\u0001\u0006Y\u00164X\r\\\u0001\nG>tG/Y5ogB\"\u0002Ba?\u0004\u0018\re11\u0004\u0005\b\u0003\u007f\u0002\b\u0019\u0001BZ\u0011\u001d\u0019i\u0001\u001da\u0001\u0005'Dqa!\u0005q\u0001\u0004\u0011\u0019.\u0001\u0005va\u0012\fG/\u001a31+\u0011\u0019\tca\n\u0015\u001d\r\r21FB\u0017\u0007_\u0019\tda\r\u00048A9\u0011q\u0002\u0001\u00034\u000e\u0015\u0002\u0003BA\f\u0007O!q!!$r\u0005\u0004\u0019I#\u0005\u0003\u0003:\u0006\u001d\u0002bBA@c\u0002\u0007!1\u0017\u0005\b\u0007\u001b\t\b\u0019\u0001Bj\u0011\u001d\u0019\t\"\u001da\u0001\u0005'Dq!!&r\u0001\u0004\u0019)\u0003C\u0004\u0002&F\u0004\ra!\u000e\u0011\u0011\u0005\u0005\u0012q\tBZ\u0007KAqa!\u000fr\u0001\u0004\u0019Y$\u0001\u0004nKJ<WM\u001d\t\b\u0005\u007fY\"1WB\u0013\u0003!\u0011X-\\8wK\u0012\u0004D\u0003CB!\u0007\u0007\u001a)ea\u0012\u0011\u000f\u0005=\u0001Aa-\u0003:\"9\u0011q\u0010:A\u0002\tM\u0006bBB\u0007e\u0002\u0007!1\u001b\u0005\b\u0007#\u0011\b\u0019\u0001Bj\u0003\u001d1\u0017\u000e\u001c;feB\"Bb!\u0011\u0004N\re3QLB0\u0007GBqaa\u0014t\u0001\u0004\u0019\t&A\u0001q!!\t\tca\u0015\u0004X\tm\u0018\u0002BB+\u0003\u0007\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0011\u0005\u0005\u0012q\tBZ\u0005sCqaa\u0017t\u0001\u0004\u0011Y0\u0001\u0004oK\u001e\fG/\u001a\u0005\b\u0007#\u0019\b\u0019\u0001Bj\u0011\u001d\u0019\tg\u001da\u0001\u0005?\faAY;gM\u0016\u0014\bbBB3g\u0002\u0007!1[\u0001\b_\u001a47/\u001a;1\u0003!IG/\u001a:bi>\u0014XCAB6!\u0019\tyd!\u001c\u0004X%\u00191qN@\u0003\u0011%#XM]1u_J\fqAZ8sK\u0006\u001c\u0007.\u0006\u0003\u0004v\r\u0015E\u0003BB<\u0007{\u0002B!!\t\u0004z%!11PA\u0002\u0005\u0011)f.\u001b;\t\u000f\r}T\u000f1\u0001\u0004\u0002\u0006\ta\r\u0005\u0005\u0002\"\rM3qKBB!\u0011\t9b!\"\u0005\u000f\r\u001dUO1\u0001\u0002\u001e\t\tQ+A\u0003q_N|e\r\u0006\u0004\u0003T\u000e55\u0011\u0013\u0005\b\u0007\u001f3\b\u0019\u0001Bj\u0003\u0005q\u0007bBBJm\u0002\u0007!1[\u0001\u0003E6,\"aa&\u0011\r\u0005=\u00111YB!\u0003\u0019iWM]4faU!1QTBR)!\u0019yj!*\u0004*\u000e-\u0006cBA\b\u0001\tM6\u0011\u0015\t\u0005\u0003/\u0019\u0019\u000bB\u0004\u0002\u000eb\u0014\ra!\u000b\t\u000f\r\u001d\u0006\u00101\u0001\u0004 \u0006!A\u000f[1u\u0011\u001d\u0019\t\u0002\u001fa\u0001\u0005'Dqa!\u000fy\u0001\u0004\u0019i\u000bE\u0004\u0003@m\u0011\u0019l!)\u0011\t\u0005]1\u0011\u0017\u0003\b\u0005\u000b\u001a#\u0019AA\u000f!\u0011\t9b!.\u0005\u000f\t-3E1\u0001\u0002\u001e!91\u0011X\u0012A\u0002\tM\u0017!\u00025bg\"\u0004\u0004bBB_G\u0001\u00071qX\u0001\u0006K2,W\u000e\r\t\b\u0003\u001f\u00011qVBZ\u0011\u001d\u0019\u0019m\ta\u0001\u0005'\fQ\u0001[1tQFBqaa2$\u0001\u0004\u0019y,A\u0003fY\u0016l\u0017\u0007C\u0004\u0004\u0012\r\u0002\rAa5\t\u000f\tU8\u00051\u0001\u0003T\u0006Q!-\u001e4gKJ\u001c\u0016N_3\u0015\t\tM7\u0011\u001b\u0005\b\u0005k$\u0003\u0019\u0001BjQ\r!\u00131V\u0001\f]VdG\u000eV8F[B$\u00180\u0006\u0004\u0004Z\u000e}71\u001d\u000b\u0005\u00077\u001c)\u000fE\u0004\u0002\u0010\u0001\u0019in!9\u0011\t\u0005]1q\u001c\u0003\b\u0005\u000b*#\u0019AA\u000f!\u0011\t9ba9\u0005\u000f\t-SE1\u0001\u0002\u001e!91q]\u0013A\u0002\rm\u0017!A7)\u0007\u0015\nY+A\bF[B$\u0018p\u00147e\u0011\u0006\u001c\b.T1q!\r\u0011yd\n\u0002\u0010\u000b6\u0004H/_(mI\"\u000b7\u000f['baN\u0019qea=\u0011\u000f\u0005=\u0001!a\n\u0002 Q\u00111Q^\u000b\u0005\u0007s\u001cy\u0010\u0006\b\u0004|\u0012\u0005A1\u0001C\u0003\t\u000f!I\u0001\"\u0004\u0011\u000f\u0005=\u0001!a\n\u0004~B!\u0011qCB\u0000\t\u001d\tii\u000bb\u0001\u0003;Aq!a ,\u0001\u0004\t9\u0003C\u0004\u0004\u000e-\u0002\rAa5\t\u000f\rE1\u00061\u0001\u0003T\"9\u0011QS\u0016A\u0002\ru\bbBASW\u0001\u0007A1\u0002\t\t\u0003C\t9%a\n\u0004~\"91\u0011H\u0016A\u0002\u0011=\u0001c\u0002B 7\u0005\u001d2Q \u000b\t\u0007g$\u0019\u0002\"\u0006\u0005\u0018!9\u0011q\u0010\u0017A\u0002\u0005\u001d\u0002bBB\u0007Y\u0001\u0007!1\u001b\u0005\b\u0007#a\u0003\u0019\u0001Bj)!!Y\u0002\"\b\u0005 \u0011\u0005\u0002CBA\u0011\u0003o\u000by\u0002C\u0004\u0002\u00005\u0002\r!a\n\t\u000f\r5Q\u00061\u0001\u0003T\"91\u0011C\u0017A\u0002\tMG\u0003DBz\tK!Y\u0003\"\f\u00050\u0011M\u0002bBB(]\u0001\u0007Aq\u0005\t\t\u0003C\u0019\u0019\u0006\"\u000b\u0003|BA\u0011\u0011EA$\u0003O\ty\u0002C\u0004\u0004\\9\u0002\rAa?\t\u000f\rEa\u00061\u0001\u0003T\"91\u0011\r\u0018A\u0002\u0011E\u0002CBA\u0011\u0005C\u001c\u0019\u0010C\u0004\u0004f9\u0002\rAa5\u0015\u0011\tmHq\u0007C\u001d\twAq!a 0\u0001\u0004\t9\u0003C\u0004\u0004\u000e=\u0002\rAa5\t\u000f\rEq\u00061\u0001\u0003TV!Aq\bC#)!!\t\u0005b\u0012\u0005J\u0011-\u0003cBA\b\u0001\u0005\u001dB1\t\t\u0005\u0003/!)\u0005B\u0004\u0002\u000eB\u0012\r!!\b\t\u000f\r\u001d\u0006\u00071\u0001\u0005B!91\u0011\u0003\u0019A\u0002\tM\u0007bBB\u001da\u0001\u0007AQ\n\t\b\u0005\u007fY\u0012q\u0005C\"+\t!\t\u0006\u0005\u0004\u0002@\r5D\u0011F\u000b\u0005\t+\"i\u0006\u0006\u0003\u0004x\u0011]\u0003bBB@e\u0001\u0007A\u0011\f\t\t\u0003C\u0019\u0019\u0006\"\u000b\u0005\\A!\u0011q\u0003C/\t\u001d\u00199I\rb\u0001\u0003;\tA\u0001[3bIV\u0011A\u0011F\u0001\u000bQ\u0016\fGm\u00149uS>tWC\u0001C4\u001d\u0011\t\t\u0003\"\u001b\n\t\u0011-\u00141A\u0001\u0005\u001d>tW-\u0001\u0003uC&dWCABz\u0003\u0011a\u0017m\u001d;\u0002\t%t\u0017\u000e^\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\ts\u0002B\u0001b\u001f\u0005\u00066\u0011AQ\u0010\u0006\u0005\t\u007f\"\t)\u0001\u0003mC:<'B\u0001CB\u0003\u0011Q\u0017M^1\n\t\u0011\u001dEQ\u0010\u0002\u0007\u001f\nTWm\u0019;\u0003\u0017=cG\rS1tQ6\u000b\u0007/M\u000b\u0007\t\u001b#\u0019\nb&\u0014\u0007e\"y\tE\u0004\u0002\u0010\u0001!\t\n\"&\u0011\t\u0005]A1\u0013\u0003\b\u00037I$\u0019AA\u000f!\u0011\t9\u0002b&\u0005\u0011\u0005E\u0012\b\"b\u0001\u0003;)\"\u0001\"%\u0002\t-,\u0017\u0010I\u0001\u0006Q\u0006\u001c\b\u000eI\u000b\u0003\t+\u000baA^1mk\u0016\u0004SC\u0001CS!!\t\t#a\u0012\u0005\u0012\u0012\u001d&\u0006\u0002CK\u0005{\u000baa\u001b<`I\u0015\fH\u0003BB<\t[C\u0011\u0002b,B\u0003\u0003\u0005\r\u0001\"*\u0002\u0007a$\u0013'A\u0002lm\u0002\"\"\u0002\".\u00058\u0012eF1\u0018C_!\u001d\u0011y$\u000fCI\t+Cq!a D\u0001\u0004!\t\nC\u0004\u0004\u000e\r\u0003\rAa5\t\u000f\u0005U5\t1\u0001\u0005\u0016\"9\u0011QU\"A\u0002\u0011\u0015VC\u0001Ca!\u0019\tyd!\u001c\u0005DBA\u0011\u0011EA$\t##)\n\u0006\u0005\u0005H\u0012%G1\u001aCg!\u0019\t\t#a.\u0005\u0016\"9\u0011q\u0010$A\u0002\u0011E\u0005bBB\u0007\r\u0002\u0007!1\u001b\u0005\b\u0007#1\u0005\u0019\u0001Bj\u0003\u00199W\r^&fs\u00069q-\u001a;ICND\u0017AD2p[B,H/\u001a%bg\"4uN\u001d\u000b\u0005\u0005'$9\u000eC\u0004\u0005Z.\u0003\r\u0001\"%\u0002\u0003-$\u0002Ba?\u0005^\u0012}G\u0011\u001d\u0005\b\u0003\u007fb\u0005\u0019\u0001CI\u0011\u001d\u0019i\u0001\u0014a\u0001\u0005'Dqa!\u0005M\u0001\u0004\u0011\u0019.\u0006\u0003\u0005f\u0012-HC\u0004Ct\t_$\t\u0010b=\u0005v\u0012]H1 \t\b\u0003\u001f\u0001A\u0011\u0013Cu!\u0011\t9\u0002b;\u0005\u000f\u00055UJ1\u0001\u0005nF!AQSA\u0014\u0011\u001d\ty(\u0014a\u0001\t#Cqa!\u0004N\u0001\u0004\u0011\u0019\u000eC\u0004\u0004\u00125\u0003\rAa5\t\u000f\u0005UU\n1\u0001\u0005j\"9\u0011QU'A\u0002\u0011e\b\u0003CA\u0011\u0003\u000f\"\t\n\";\t\u000f\reR\n1\u0001\u0005~B9!qH\u000e\u0005\u0012\u0012%H\u0003\u0003CH\u000b\u0003)\u0019!\"\u0002\t\u000f\u0005}d\n1\u0001\u0005\u0012\"91Q\u0002(A\u0002\tM\u0007bBB\t\u001d\u0002\u0007!1\u001b\u000b\r\t\u001f+I!\"\u0004\u0006\u0010\u0015EQq\u0003\u0005\b\u0007\u001fz\u0005\u0019AC\u0006!!\t\tca\u0015\u0005D\nm\bbBB.\u001f\u0002\u0007!1 \u0005\b\u0007#y\u0005\u0019\u0001Bj\u0011\u001d\u0019\tg\u0014a\u0001\u000b'\u0001b!!\t\u0003b\u0016U\u0001cBA\b\u0001\u0011EEq\u0015\u0005\b\u0007Kz\u0005\u0019\u0001Bj+\u0011)Y\"b\t\u0015\t\r]TQ\u0004\u0005\b\u0007\u007f\u0002\u0006\u0019AC\u0010!!\t\tca\u0015\u0005D\u0016\u0005\u0002\u0003BA\f\u000bG!qaa\"Q\u0005\u0004\ti\"\u0001\u0006f]N,(/\u001a)bSJ,\"\u0001b1\u0016\t\u0015-R\u0011\u0007\u000b\t\u000b[)\u0019$\"\u000e\u00068A9\u0011q\u0002\u0001\u0005\u0012\u0016=\u0002\u0003BA\f\u000bc!q!!$S\u0005\u0004!i\u000fC\u0004\u0004(J\u0003\r!\"\f\t\u000f\rE!\u000b1\u0001\u0003T\"91\u0011\b*A\u0002\u0015e\u0002c\u0002B 7\u0011EUq\u0006\u0002\u0015\u001f2$\u0007*Y:i\u001b\u0006\u00048i\u001c7mSNLwN\\\u0019\u0016\r\u0015}RQIC&'\r\u0019V\u0011\t\t\b\u0003\u001f\u0001Q1IC$!\u0011\t9\"\"\u0012\u0005\u000f\u0005m1K1\u0001\u0002\u001e)\"Q\u0011\nB_!\u0011\t9\"b\u0013\u0005\u0011\u0005E2\u000b\"b\u0001\u0003;\t1a\u001b<t+\t)\t\u0006\u0005\u0005\u0002\u0010\u0015MS1IC$\u0013\r))& \u0002\b\u0019&\u001cH/T1q\u0003\u0011Ygo\u001d\u0011\u0015\r\u0015mSQLC0!\u001d\u0011ydUC\"\u000b\u0013Bqa!\u0004Y\u0001\u0004\u0011\u0019\u000eC\u0004\u0006Na\u0003\r!\"\u0015\u0015\u0011\u0015\rTQMC4\u000bS\u0002b!!\t\u00028\u0016%\u0003bBA@7\u0002\u0007Q1\t\u0005\b\u0007\u001bY\u0006\u0019\u0001Bj\u0011\u001d\u0019\tb\u0017a\u0001\u0005'$\u0002Ba?\u0006n\u0015=T\u0011\u000f\u0005\b\u0003\u007fb\u0006\u0019AC\"\u0011\u001d\u0019i\u0001\u0018a\u0001\u0005'Dqa!\u0005]\u0001\u0004\u0011\u0019.\u0006\u0003\u0006v\u0015mDCDC<\u000b\u007f*\t)b!\u0006\u0006\u0016\u001dU1\u0012\t\b\u0003\u001f\u0001Q1IC=!\u0011\t9\"b\u001f\u0005\u000f\tUTL1\u0001\u0006~E!Q\u0011JA\u0014\u0011\u001d\ty(\u0018a\u0001\u000b\u0007Bqa!\u0004^\u0001\u0004\u0011\u0019\u000eC\u0004\u0004\u0012u\u0003\rAa5\t\u000f\u0005UU\f1\u0001\u0006z!9\u0011QU/A\u0002\u0015%\u0005\u0003CA\u0011\u0003\u000f*\u0019%\"\u001f\t\u000f\reR\f1\u0001\u0006\u000eB9!qH\u000e\u0006D\u0015eD\u0003CCI\u000b'+)*b&\u0011\u000f\u0005=\u0001!b\u0011\u0006J!9\u0011q\u00100A\u0002\u0015\r\u0003bBB\u0007=\u0002\u0007!1\u001b\u0005\b\u0007#q\u0006\u0019\u0001Bj)1)\t*b'\u0006\"\u0016\rVQUCU\u0011\u001d\u0019ye\u0018a\u0001\u000b;\u0003\u0002\"!\t\u0004T\u0015}%1 \t\t\u0003C\t9%b\u0011\u0006J!911L0A\u0002\tm\bbBB\t?\u0002\u0007!1\u001b\u0005\b\u0007Cz\u0006\u0019ACT!\u0019\t\tC!9\u0006B!91QM0A\u0002\tMW\u0003BCW\u000bg#\u0002\"b,\u00066\u0016]V\u0011\u0018\t\b\u0003\u001f\u0001Q1ICY!\u0011\t9\"b-\u0005\u000f\u00055\u0005M1\u0001\u0006~!91q\u00151A\u0002\u0015=\u0006bBB\tA\u0002\u0007!1\u001b\u0005\b\u0007s\u0001\u0007\u0019AC^!\u001d\u0011ydGC\"\u000bc+\"!b0\u0011\r\u0005}2QNCP+\u0011)\u0019-b3\u0015\t\r]TQ\u0019\u0005\b\u0007\u007f\u0012\u0007\u0019ACd!!\t\tca\u0015\u0006 \u0016%\u0007\u0003BA\f\u000b\u0017$qaa\"c\u0005\u0004\ti\"\u0006\u0002\u0006PB1\u0011qBAb\u000b#\u000b1b\u001e:ji\u0016|%M[3diR!1qOCk\u0011\u001d)9.\u001fa\u0001\u000b3\f1a\\;u!\u0011)Y.\"9\u000e\u0005\u0015u'\u0002BCp\t\u0003\u000b!![8\n\t\u0015\rXQ\u001c\u0002\u0013\u001f\nTWm\u0019;PkR\u0004X\u000f^*ue\u0016\fW.\u0001\u0006sK\u0006$wJ\u00196fGR$Baa\u001e\u0006j\"9Q1\u001e>A\u0002\u00155\u0018AA5o!\u0011)Y.b<\n\t\u0015EXQ\u001c\u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0007f\u0002\f\u0006v\u0006UU1 \t\u0005\u0003C)90\u0003\u0003\u0006z\u0006\r!\u0001E*fe&\fGNV3sg&|g.V%E=\u0005\u0019\u0001fB\u000b\u0006v\u0006UU1 \u0005\b\u0007OC\u0001\u0019AAh+\u00111\u0019A\"\u0003\u0015\u001d\u0019\u0015a1\u0002D\u0007\r\u001f1\tBb\u0005\u0007\u0018A9\u0011q\u0002\u0001\u0002\u0016\u0019\u001d\u0001\u0003BA\f\r\u0013!q!!$\n\u0005\u0004\ty\tC\u0004\u0002\u0000%\u0001\r!!\u0006\t\u000f\r5\u0011\u00021\u0001\u0003T\"91\u0011C\u0005A\u0002\tM\u0007bBAK\u0013\u0001\u0007aq\u0001\u0005\b\u0003KK\u0001\u0019\u0001D\u000b!!\t\t#a\u0012\u0002\u0016\u0019\u001d\u0001bBB\u001d\u0013\u0001\u0007a\u0011\u0004\t\b\u00037\\\u0012Q\u0003D\u0004)!\tYD\"\b\u0007 \u0019\u0005\u0002bBA@\u0015\u0001\u0007\u0011Q\u0003\u0005\b\u0007\u001bQ\u0001\u0019\u0001Bj\u0011\u001d\u0019\tB\u0003a\u0001\u0005'$\u0002\"!.\u0007&\u0019\u001db\u0011\u0006\u0005\b\u0003\u007fZ\u0001\u0019AA\u000b\u0011\u001d\u0019ia\u0003a\u0001\u0005'Dqa!\u0005\f\u0001\u0004\u0011\u0019.\u0006\u0003\u0007.\u0019MB\u0003\u0003D\u0018\rk19D\"\u000f\u0011\u000f\u0005=\u0001!!\u0006\u00072A!\u0011q\u0003D\u001a\t\u001d\ti\t\u0004b\u0001\u0003\u001fCqaa*\r\u0001\u00041y\u0003C\u0004\u0004\u00121\u0001\rAa5\t\u000f\reB\u00021\u0001\u0007<A9\u00111\\\u000e\u0002\u0016\u0019EB\u0003DA\u001e\r\u007f1\u0019E\"\u0012\u0007H\u0019=\u0003bBB(\u001b\u0001\u0007a\u0011\t\t\t\u0003C\u0019\u0019&!\u0012\u0003|\"911L\u0007A\u0002\tm\bbBB\t\u001b\u0001\u0007!1\u001b\u0005\b\u0007Cj\u0001\u0019\u0001D%!\u0019\t\tC!9\u0007LA9\u0011q\u0002\u0001\u0002\u0016\u00195#\u0006BA\u0017\u0005{Cqa!\u001a\u000e\u0001\u0004\u0011\u0019\u000e\u0006\u0005\u0003|\u001aMcQ\u000bD,\u0011\u001d\tyH\u0004a\u0001\u0003+Aqa!\u0004\u000f\u0001\u0004\u0011\u0019\u000eC\u0004\u0004\u00129\u0001\rAa5\u0002\u0011\r|g\u000e^1j]N$BAa?\u0007^!9\u0011qP\bA\u0002\u0005UQCAA\u001e\u0003\u00191\u0017\u000e\u001c;feR!\u00111\bD3\u0011\u001d19G\u0005a\u0001\r\u0003\nA\u0001\u001d:fI\u0006Ia-\u001b7uKJtu\u000e\u001e\u000b\u0005\u0003w1i\u0007C\u0004\u0007hM\u0001\rA\"\u0011\u0002\u0013\rd\u0017m]:OC6,WC\u0001D:!\u0011!YH\"\u001e\n\t\u0019]DQ\u0010\u0002\u0007'R\u0014\u0018N\\4*\u000b\u00019C-O*)\u000f\u0001))0!&\u0007~yAa\\\u0016Ds\\;V>\t"
)
public abstract class OldHashMap extends AbstractMap implements StrictOptimizedMapOps, Serializable {
   private static final long serialVersionUID = -2425602972438308285L;

   public static Builder newBuilder() {
      return OldHashMap$.MODULE$.newBuilder();
   }

   public static OldHashMap from(final IterableOnce it) {
      return OldHashMap$.MODULE$.from(it);
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
   public IterableOps $plus(final Tuple2 elem1, final Tuple2 elem2, final Seq elems) {
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

   public MapFactory mapFactory() {
      return OldHashMap$.MODULE$;
   }

   public final OldHashMap removed(final Object key) {
      return this.removed0(key, .MODULE$.computeHash(key), 0);
   }

   public final OldHashMap updated(final Object key, final Object value) {
      return this.updated0(key, .MODULE$.computeHash(key), 0, value, (Tuple2)null, (Merger)null);
   }

   public final OldHashMap $plus(final Tuple2 kv) {
      return this.updated(kv._1(), kv._2());
   }

   public Option get(final Object key) {
      return this.get0(key, .MODULE$.computeHash(key), 0);
   }

   public Seq split() {
      return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
   }

   public OldHashMap merged(final OldHashMap that, final Function2 mergef) {
      return this.merge0(that, 0, OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$liftMerger(mergef));
   }

   public abstract OldHashMap updated0(final Object key, final int hash, final int level, final Object value, final Tuple2 kv, final Merger merger);

   public abstract OldHashMap removed0(final Object key, final int hash, final int level);

   public abstract Option get0(final Object key, final int hash, final int level);

   public abstract OldHashMap merge0(final OldHashMap that, final int level, final Merger merger);

   public abstract OldHashMap filter0(final Function1 p, final boolean negate, final int level, final OldHashMap[] buffer, final int offset0);

   public abstract boolean contains0(final Object key, final int hash, final int level);

   public final boolean contains(final Object key) {
      return this.contains0(key, .MODULE$.computeHash(key), 0);
   }

   public OldHashMap tail() {
      return (OldHashMap)this.$minus(((Tuple2)this.head())._1());
   }

   public OldHashMap init() {
      return (OldHashMap)this.$minus(((Tuple2)this.last())._1());
   }

   public OldHashMap filter(final Function1 pred) {
      OldHashMap[] buffer = new OldHashMap[OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$bufferSize(this.size())];
      return OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$nullToEmpty(this.filter0(pred, false, 0, buffer, 0));
   }

   public OldHashMap filterNot(final Function1 pred) {
      OldHashMap[] buffer = new OldHashMap[OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$bufferSize(this.size())];
      return OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$nullToEmpty(this.filter0(pred, true, 0, buffer, 0));
   }

   public String className() {
      return "OldHashMap";
   }

   public OldHashMap() {
      StrictOptimizedIterableOps.$init$(this);
      StrictOptimizedMapOps.$init$(this);
   }

   public abstract static class Merger {
      public abstract Tuple2 apply(final Tuple2 kv1, final Tuple2 kv2);

      public abstract Merger invert();
   }

   private static class EmptyOldHashMap$ extends OldHashMap {
      public static final EmptyOldHashMap$ MODULE$ = new EmptyOldHashMap$();

      public boolean isEmpty() {
         return true;
      }

      public int knownSize() {
         return 0;
      }

      public OldHashMap updated0(final Object key, final int hash, final int level, final Object value, final Tuple2 kv, final Merger merger) {
         return new OldHashMap1(key, hash, value, kv);
      }

      public OldHashMap removed0(final Object key, final int hash, final int level) {
         return this;
      }

      public Option get0(final Object key, final int hash, final int level) {
         return scala.None..MODULE$;
      }

      public OldHashMap filter0(final Function1 p, final boolean negate, final int level, final OldHashMap[] buffer, final int offset0) {
         return null;
      }

      public boolean contains0(final Object key, final int hash, final int level) {
         return false;
      }

      public OldHashMap merge0(final OldHashMap that, final int level, final Merger merger) {
         return that;
      }

      public Iterator iterator() {
         return scala.collection.Iterator..MODULE$.empty();
      }

      public void foreach(final Function1 f) {
      }

      public Tuple2 head() {
         throw new NoSuchElementException("Empty Map");
      }

      public None headOption() {
         return scala.None..MODULE$;
      }

      public OldHashMap tail() {
         throw new NoSuchElementException("Empty Map");
      }

      public Tuple2 last() {
         throw new NoSuchElementException("Empty Map");
      }

      public OldHashMap init() {
         throw new NoSuchElementException("Empty Map");
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EmptyOldHashMap$.class);
      }

      public EmptyOldHashMap$() {
      }
   }

   public static final class OldHashMap1 extends OldHashMap {
      private final Object key;
      private final int hash;
      private final Object value;
      private Tuple2 kv;

      public Object key() {
         return this.key;
      }

      public int hash() {
         return this.hash;
      }

      public Object value() {
         return this.value;
      }

      public Tuple2 kv() {
         return this.kv;
      }

      public void kv_$eq(final Tuple2 x$1) {
         this.kv = x$1;
      }

      public boolean isEmpty() {
         return false;
      }

      public Iterator iterator() {
         return scala.collection.Iterator..MODULE$.single(this.ensurePair());
      }

      public Option get0(final Object key, final int hash, final int level) {
         return (Option)(hash == this.hash() && BoxesRunTime.equals(key, this.key()) ? new Some(this.value()) : scala.None..MODULE$);
      }

      public int size() {
         return 1;
      }

      public int knownSize() {
         return 1;
      }

      public Object getKey() {
         return this.key();
      }

      public int getHash() {
         return this.hash();
      }

      public int computeHashFor(final Object k) {
         return .MODULE$.computeHash(k);
      }

      public boolean contains0(final Object key, final int hash, final int level) {
         return hash == this.hash() && BoxesRunTime.equals(key, this.key());
      }

      public OldHashMap updated0(final Object key, final int hash, final int level, final Object value, final Tuple2 kv, final Merger merger) {
         if (hash == this.hash() && BoxesRunTime.equals(key, this.key())) {
            if (merger == null) {
               return this.value() == value ? this : new OldHashMap1(key, hash, value, kv);
            } else {
               Tuple2 nkv = merger.apply(this.ensurePair(), kv != null ? kv : new Tuple2(key, value));
               return new OldHashMap1(nkv._1(), hash, nkv._2(), nkv);
            }
         } else if (hash != this.hash()) {
            OldHashMap1 that = new OldHashMap1(key, hash, value, kv);
            return OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$makeHashTrieMap(this.hash(), this, hash, that, level, 2);
         } else {
            return new OldHashMapCollision1(hash, scala.collection.immutable.ListMap..MODULE$.empty().updated(this.key(), this.value()).updated(key, value));
         }
      }

      public OldHashMap removed0(final Object key, final int hash, final int level) {
         return (OldHashMap)(hash == this.hash() && BoxesRunTime.equals(key, this.key()) ? OldHashMap$.MODULE$.empty() : this);
      }

      public OldHashMap filter0(final Function1 p, final boolean negate, final int level, final OldHashMap[] buffer, final int offset0) {
         return negate ^ BoxesRunTime.unboxToBoolean(p.apply(this.ensurePair())) ? this : null;
      }

      public void foreach(final Function1 f) {
         f.apply(this.ensurePair());
      }

      public Tuple2 ensurePair() {
         if (this.kv() != null) {
            return this.kv();
         } else {
            this.kv_$eq(new Tuple2(this.key(), this.value()));
            return this.kv();
         }
      }

      public OldHashMap merge0(final OldHashMap that, final int level, final Merger merger) {
         return that.updated0(this.key(), this.hash(), level, this.value(), this.kv(), merger.invert());
      }

      public OldHashMap1(final Object key, final int hash, final Object value, final Tuple2 kv) {
         this.key = key;
         this.hash = hash;
         this.value = value;
         this.kv = kv;
         super();
      }
   }

   public static class OldHashMapCollision1 extends OldHashMap {
      private final int hash;
      private final ListMap kvs;

      public int hash() {
         return this.hash;
      }

      public ListMap kvs() {
         return this.kvs;
      }

      public int size() {
         return this.kvs().size();
      }

      public boolean isEmpty() {
         return false;
      }

      public Option get0(final Object key, final int hash, final int level) {
         return (Option)(hash == this.hash() ? this.kvs().get(key) : scala.None..MODULE$);
      }

      public boolean contains0(final Object key, final int hash, final int level) {
         return hash == this.hash() && this.kvs().contains(key);
      }

      public OldHashMap updated0(final Object key, final int hash, final int level, final Object value, final Tuple2 kv, final Merger merger) {
         if (hash == this.hash()) {
            return merger != null && this.kvs().contains(key) ? new OldHashMapCollision1(hash, (ListMap)this.kvs().$plus(merger.apply(new Tuple2(key, this.kvs().apply(key)), kv))) : new OldHashMapCollision1(hash, this.kvs().updated(key, value));
         } else {
            OldHashMap1 that = new OldHashMap1(key, hash, value, kv);
            return OldHashMap$.MODULE$.scala$collection$immutable$OldHashMap$$makeHashTrieMap(this.hash(), this, hash, that, level, this.size() + 1);
         }
      }

      public OldHashMap removed0(final Object key, final int hash, final int level) {
         if (hash == this.hash()) {
            ListMap kvs1 = (ListMap)this.kvs().$minus(key);
            int var5 = kvs1.size();
            switch (var5) {
               case 0:
                  return OldHashMap$.MODULE$.empty();
               case 1:
                  Tuple2 kv = (Tuple2)kvs1.head();
                  return new OldHashMap1(kv._1(), hash, kv._2(), kv);
               default:
                  return var5 == this.kvs().size() ? this : new OldHashMapCollision1(hash, kvs1);
            }
         } else {
            return this;
         }
      }

      public OldHashMap filter0(final Function1 p, final boolean negate, final int level, final OldHashMap[] buffer, final int offset0) {
         ListMap kvs1 = negate ? (ListMap)this.kvs().filterNot(p) : (ListMap)this.kvs().filter(p);
         int var8 = kvs1.size();
         switch (var8) {
            case 0:
               return null;
            case 1:
               Tuple2 var10 = (Tuple2)kvs1.head();
               if (var10 != null) {
                  Object k = var10._1();
                  Object v = var10._2();
                  Tuple3 var9 = new Tuple3(var10, k, v);
                  Tuple2 kv = (Tuple2)var9._1();
                  Object k = var9._2();
                  Object v = var9._3();
                  return new OldHashMap1(k, this.hash(), v, kv);
               }

               throw new MatchError(var10);
            default:
               return var8 == this.kvs().size() ? this : new OldHashMapCollision1(this.hash(), kvs1);
         }
      }

      public OldHashMap merge0(final OldHashMap that, final int level, final Merger merger) {
         ObjectRef m = ObjectRef.create(that);
         this.kvs().foreach((p) -> {
            $anonfun$merge0$1(this, m, level, merger, p);
            return BoxedUnit.UNIT;
         });
         return (OldHashMap)m.elem;
      }

      public Iterator iterator() {
         return this.kvs().iterator();
      }

      public void foreach(final Function1 f) {
         this.kvs().foreach(f);
      }

      public Seq split() {
         Tuple2 var3 = this.kvs().splitAt(this.kvs().size() / 2);
         if (var3 != null) {
            ListMap x = (ListMap)var3._1();
            ListMap y = (ListMap)var3._2();
            Tuple2 var2 = new Tuple2(x, y);
            ListMap x = (ListMap)var2._1();
            ListMap y = (ListMap)var2._2();
            return new scala.collection.immutable..colon.colon(this.newhm$1(x), new scala.collection.immutable..colon.colon(this.newhm$1(y), scala.collection.immutable.Nil..MODULE$));
         } else {
            throw new MatchError(var3);
         }
      }

      // $FF: synthetic method
      public static final void $anonfun$merge0$1(final OldHashMapCollision1 $this, final ObjectRef m$1, final int level$1, final Merger merger$1, final Tuple2 p) {
         m$1.elem = ((OldHashMap)m$1.elem).updated0(p._1(), $this.hash(), level$1, p._2(), p, merger$1.invert());
      }

      private final OldHashMapCollision1 newhm$1(final ListMap lm) {
         return new OldHashMapCollision1(this.hash(), lm);
      }

      public OldHashMapCollision1(final int hash, final ListMap kvs) {
         this.hash = hash;
         this.kvs = kvs;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static final class HashTrieMap extends OldHashMap {
      private final int bitmap;
      private final OldHashMap[] elems;
      private final int size0;

      public int bitmap() {
         return this.bitmap;
      }

      public OldHashMap[] elems() {
         return this.elems;
      }

      public int size0() {
         return this.size0;
      }

      public int size() {
         return this.size0();
      }

      public boolean isEmpty() {
         return false;
      }

      public int knownSize() {
         return this.size();
      }

      public Option get0(final Object key, final int hash, final int level) {
         int index = hash >>> level & 31;
         if (this.bitmap() == -1) {
            return this.elems()[index].get0(key, hash, level + 5);
         } else {
            int mask = 1 << index;
            if ((this.bitmap() & mask) != 0) {
               int offset = Integer.bitCount(this.bitmap() & mask - 1);
               return this.elems()[offset].get0(key, hash, level + 5);
            } else {
               return scala.None..MODULE$;
            }
         }
      }

      public boolean contains0(final Object key, final int hash, final int level) {
         int index = hash >>> level & 31;
         if (this.bitmap() == -1) {
            return this.elems()[index].contains0(key, hash, level + 5);
         } else {
            int mask = 1 << index;
            if ((this.bitmap() & mask) != 0) {
               int offset = Integer.bitCount(this.bitmap() & mask - 1);
               return this.elems()[offset].contains0(key, hash, level + 5);
            } else {
               return false;
            }
         }
      }

      public OldHashMap updated0(final Object key, final int hash, final int level, final Object value, final Tuple2 kv, final Merger merger) {
         int index = hash >>> level & 31;
         int mask = 1 << index;
         int offset = Integer.bitCount(this.bitmap() & mask - 1);
         if ((this.bitmap() & mask) != 0) {
            OldHashMap sub = this.elems()[offset];
            OldHashMap subNew = sub.updated0(key, hash, level + 5, value, kv, merger);
            if (subNew == sub) {
               return this;
            } else {
               OldHashMap[] elemsNew = new OldHashMap[this.elems().length];
               scala.Array..MODULE$.copy(this.elems(), 0, elemsNew, 0, this.elems().length);
               elemsNew[offset] = subNew;
               return new HashTrieMap(this.bitmap(), elemsNew, this.size() + (subNew.size() - sub.size()));
            }
         } else {
            OldHashMap[] elemsNew = new OldHashMap[this.elems().length + 1];
            scala.Array..MODULE$.copy(this.elems(), 0, elemsNew, 0, offset);
            elemsNew[offset] = new OldHashMap1(key, hash, value, kv);
            scala.Array..MODULE$.copy(this.elems(), offset, elemsNew, offset + 1, this.elems().length - offset);
            return new HashTrieMap(this.bitmap() | mask, elemsNew, this.size() + 1);
         }
      }

      public OldHashMap removed0(final Object key, final int hash, final int level) {
         int index = hash >>> level & 31;
         int mask = 1 << index;
         int offset = Integer.bitCount(this.bitmap() & mask - 1);
         if ((this.bitmap() & mask) != 0) {
            OldHashMap sub = this.elems()[offset];
            OldHashMap subNew = sub.removed0(key, hash, level + 5);
            if (subNew == sub) {
               return this;
            } else if (subNew.isEmpty()) {
               int bitmapNew = this.bitmap() ^ mask;
               if (bitmapNew != 0) {
                  OldHashMap[] elemsNew = new OldHashMap[this.elems().length - 1];
                  scala.Array..MODULE$.copy(this.elems(), 0, elemsNew, 0, offset);
                  scala.Array..MODULE$.copy(this.elems(), offset + 1, elemsNew, offset, this.elems().length - offset - 1);
                  int sizeNew = this.size() - sub.size();
                  return (OldHashMap)(elemsNew.length == 1 && !(elemsNew[0] instanceof HashTrieMap) ? elemsNew[0] : new HashTrieMap(bitmapNew, elemsNew, sizeNew));
               } else {
                  return OldHashMap$.MODULE$.empty();
               }
            } else if (this.elems().length == 1 && !(subNew instanceof HashTrieMap)) {
               return subNew;
            } else {
               OldHashMap[] elemsNew = (OldHashMap[])Arrays.copyOf((Object[])this.elems(), this.elems().length);
               elemsNew[offset] = subNew;
               int sizeNew = this.size() + (subNew.size() - sub.size());
               return new HashTrieMap(this.bitmap(), elemsNew, sizeNew);
            }
         } else {
            return this;
         }
      }

      public OldHashMap filter0(final Function1 p, final boolean negate, final int level, final OldHashMap[] buffer, final int offset0) {
         int offset = offset0;
         int rs = 0;
         int kept = 0;

         for(int i = 0; i < this.elems().length; ++i) {
            OldHashMap result = this.elems()[i].filter0(p, negate, level + 5, buffer, offset);
            if (result != null) {
               buffer[offset] = result;
               ++offset;
               rs += result.size();
               kept |= 1 << i;
            }
         }

         if (offset == offset0) {
            return null;
         } else if (rs == this.size0()) {
            return this;
         } else if (offset == offset0 + 1 && !(buffer[offset0] instanceof HashTrieMap)) {
            return buffer[offset0];
         } else {
            int length = offset - offset0;
            OldHashMap[] elems1 = new OldHashMap[length];
            System.arraycopy(buffer, offset0, elems1, 0, length);
            int bitmap1 = length == this.elems().length ? this.bitmap() : .MODULE$.keepBits(this.bitmap(), kept);
            return new HashTrieMap(bitmap1, elems1, rs);
         }
      }

      public Iterator iterator() {
         return new TrieIterator() {
            public final Tuple2 getElem(final Object cc) {
               return ((OldHashMap1)cc).ensurePair();
            }
         };
      }

      public void foreach(final Function1 f) {
         for(int i = 0; i < this.elems().length; ++i) {
            this.elems()[i].foreach(f);
         }

      }

      private int posOf(final int n, final int bm) {
         int left = n;
         int i = -1;

         for(int b = bm; left >= 0; b >>>= 1) {
            ++i;
            if ((b & 1) != 0) {
               --left;
            }
         }

         return i;
      }

      public Seq split() {
         if (this.size() == 1) {
            return new scala.collection.immutable..colon.colon(this, scala.collection.immutable.Nil..MODULE$);
         } else {
            int nodesize = Integer.bitCount(this.bitmap());
            if (nodesize > 1) {
               int splitpoint = nodesize / 2;
               int bitsplitpoint = this.posOf(nodesize / 2, this.bitmap());
               int bm1 = this.bitmap() & -1 << bitsplitpoint;
               int bm2 = this.bitmap() & -1 >>> 32 - bitsplitpoint;
               Tuple2 var8 = scala.collection.ArrayOps..MODULE$.splitAt$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.elems()), splitpoint);
               if (var8 != null) {
                  OldHashMap[] e1 = (OldHashMap[])var8._1();
                  OldHashMap[] e2 = (OldHashMap[])var8._2();
                  Tuple2 var7 = new Tuple2(e1, e2);
                  OldHashMap[] e1 = (OldHashMap[])var7._1();
                  OldHashMap[] e2 = (OldHashMap[])var7._2();
                  HashTrieMap hm1 = new HashTrieMap(bm1, e1, BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])e1), BoxesRunTime.boxToInteger(0), (x$4, x$5) -> BoxesRunTime.boxToInteger($anonfun$split$1(BoxesRunTime.unboxToInt(x$4), x$5)))));
                  HashTrieMap hm2 = new HashTrieMap(bm2, e2, BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.foldLeft$extension(scala.Predef..MODULE$.refArrayOps((Object[])e2), BoxesRunTime.boxToInteger(0), (x$6, x$7) -> BoxesRunTime.boxToInteger($anonfun$split$2(BoxesRunTime.unboxToInt(x$6), x$7)))));
                  return new scala.collection.immutable..colon.colon(hm1, new scala.collection.immutable..colon.colon(hm2, scala.collection.immutable.Nil..MODULE$));
               } else {
                  throw new MatchError(var8);
               }
            } else {
               return this.elems()[0].split();
            }
         }
      }

      public OldHashMap merge0(final OldHashMap that, final int level, final Merger merger) {
         if (that instanceof OldHashMap1) {
            OldHashMap1 var6 = (OldHashMap1)that;
            return this.updated0(var6.key(), var6.hash(), level, var6.value(), var6.kv(), merger);
         } else if (that instanceof HashTrieMap) {
            HashTrieMap that = (HashTrieMap)that;
            OldHashMap[] thiselems = this.elems();
            OldHashMap[] thatelems = that.elems();
            int thisbm = this.bitmap();
            int thatbm = that.bitmap();
            int subcount = Integer.bitCount(thisbm | thatbm);
            OldHashMap[] merged = new OldHashMap[subcount];
            int i = 0;
            int thisi = 0;
            int thati = 0;

            int totalelems;
            for(totalelems = 0; i < subcount; ++i) {
               int thislsb = thisbm ^ thisbm & thisbm - 1;
               int thatlsb = thatbm ^ thatbm & thatbm - 1;
               if (thislsb == thatlsb) {
                  OldHashMap m = thiselems[thisi].merge0(thatelems[thati], level + 5, merger);
                  totalelems += m.size();
                  merged[i] = m;
                  thisbm &= ~thislsb;
                  thatbm &= ~thatlsb;
                  ++thati;
                  ++thisi;
               } else if (Integer.compareUnsigned(thislsb - 1, thatlsb - 1) < 0) {
                  OldHashMap m = thiselems[thisi];
                  totalelems += m.size();
                  merged[i] = m;
                  thisbm &= ~thislsb;
                  ++thisi;
               } else {
                  OldHashMap m = thatelems[thati];
                  totalelems += m.size();
                  merged[i] = m;
                  thatbm &= ~thatlsb;
                  ++thati;
               }
            }

            return new HashTrieMap(this.bitmap() | that.bitmap(), merged, totalelems);
         } else if (that instanceof OldHashMapCollision1) {
            return that.merge0(this, level, merger.invert());
         } else if (that instanceof OldHashMap) {
            return this;
         } else {
            throw new MatchError(that);
         }
      }

      // $FF: synthetic method
      public static final int $anonfun$split$1(final int x$4, final OldHashMap x$5) {
         return x$4 + x$5.size();
      }

      // $FF: synthetic method
      public static final int $anonfun$split$2(final int x$6, final OldHashMap x$7) {
         return x$6 + x$7.size();
      }

      public HashTrieMap(final int bitmap, final OldHashMap[] elems, final int size0) {
         this.bitmap = bitmap;
         this.elems = elems;
         this.size0 = size0;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
