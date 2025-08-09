package scala.collection.immutable;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import java.util.NoSuchElementException;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.MatchError;
import scala.None;
import scala.PartialFunction;
import scala.Some;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.Iterator;
import scala.collection.Set;
import scala.collection.SetOps;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.Hashing.;
import scala.collection.mutable.Builder;
import scala.math.Integral;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015]d\u0001CA\u0002\u0003\u000b\t\t#a\u0005\t\u000f\u0005u\u0003\u0001\"\u0001\u0002`!A\u0011\u0011\r\u0001!\n#\n\u0019\u0007C\u0004\u0002|\u0001!\t%! \t\u000f\u0011m\u0007\u0001\"\u0001\u0005^\"9A\u0011\u001d\u0001\u0005\u0002\u0011\r\bb\u0002Ct\u0001\u0011\u0005A\u0011\u001e\u0005\b\t[\u0004A\u0011\tCx\u0011\u001d!I\u0010\u0001C!\twDq!\"\u0001\u0001\t\u0003*\u0019\u0001C\u0004\u0006\b\u0001!\t%\"\u0003\t\u000f\u00155\u0001\u0001\"\u0011\u0006\u0010!9QQ\u0003\u0001\u0005B\u0015]\u0001b\u0002B\u0011\u0001\u0011\u0005S1\u0004\u0005\b\u0005K\u0001A\u0011IC\u000e\u0011\u001d\u0011I\u0003\u0001D\t\u000b;A\u0011Ba\u0011\u0001\r#\tI!\"\n\t\u000f\t%\u0006A\"\u0005\u0006.!9!\u0011\u001c\u0001\u0007\u0012\u0015U\u0002b\u0002B\u001d\u0001\u0019EQ1\t\u0005\n\u0005{\u0004a\u0011AA\u0003\u000b\u0013BqA!@\u0001\r#)9\u0006C\u0004\u0004\u0010\u00011\t\"\"\u0019\t\u000f\rm\u0001A\"\u0005\u0006l\u001dAQ\u0011KA\u0003\u0011\u0003\tyH\u0002\u0005\u0002\u0004\u0005\u0015\u0001\u0012AAA\u0011\u001d\ti&\u0007C\u0001\u0003\u001fCq!!%\u001a\t\u0003\t\u0019\nC\u0004\u0002(f!\t!!+\t\u000f\u0005M\u0016\u0004\"\u0001\u00026\u001e9\u00111Z\r\t\n\u00055gaBAi3!%\u00111\u001b\u0005\b\u0003;zB\u0011AAl\u0011\u001d\tIn\bC\u0001\u00037Dq!a9 \t\u0003\n)\u000fC\u0004\u0002n~!\t%a<\t\u000f\u0005]x\u0004\"\u0011\u0002z\"9!1C\u0010\u0005B\tU\u0001b\u0002B\f?\u0011\u0005#\u0011\u0004\u0005\b\u0005CyB\u0011\tB\u0012\u0011\u001d\u0011)c\bC!\u0005GAqAa\n \t\u0003\ny\u000fC\u0004\u0003*}!\tBa\u000b\t\u000f\ter\u0004\"\u0005\u0003<!I!1I\u0010\u0005\u0012\u0005%!Q\t\u0005\b\u0005S{B\u0011CBW\u0011%\u0011ip\bC\u0001\u0003\u000b\u0019)\fC\u0004\u0003~~!\tb!0\t\u000f\r=q\u0004\"\u0005\u0004J\"911D\u0010\u0005\u0012\rM\u0007b\u0002Bm?\u0011E1Q\u001c\u0005\n\u0007W|\u0012\u0011!C\u0005\u0007[4\u0011B!\u0015\u001a\u0003C\t)Aa\u0015\t\u000f\u0005uC\u0007\"\u0001\u0003^!A!1\u0007\u001b\u0007\u0002e\tyO\u0002\u0005\u0003Je\u0011\u0011Q\u0001B&\u0011-\u0011Ij\u000eBC\u0002\u0013\u0005\u0011da\u000b\t\u0015\r5rG!A!\u0002\u0013\u00199\u0003C\u0006\u00034]\u0012)\u0019!C\u00013\u0005=\bB\u0003B8o\t\u0005\t\u0015!\u0003\u0002r\"9\u0011QL\u001c\u0005\u0002\r=\u0002bBAro\u0011\u0005\u0013Q\u001d\u0005\b\u0003[<D\u0011IAx\u0011\u001d\tIn\u000eC\u0001\u0007oAq!a>8\t\u0003\u001aY\u0004C\u0004\u0003\u0014]\"\tea\u000b\t\u000f\t]q\u0007\"\u0011\u0004H!9!\u0011E\u001c\u0005B\r=\u0003bBB*o\u0011\u000531\u0006\u0005\b\u0005K9D\u0011IB(\u0011\u001d\u00119c\u000eC!\u0003_DqA!\u000b8\t#\u0019)\u0006C\u0005\u0003D]\"\t\"!\u0003\u0004^!9!\u0011V\u001c\u0005\u0012\r\u0015\u0004b\u0002B\u001do\u0011E1Q\u000e\u0005\n\u0005{<D\u0011AA\u0003\u0007gBqA!@8\t#\u0019I\bC\u0004\u0004\u0010]\"\tb!\"\t\u000f\rmq\u0007\"\u0005\u0004\u0010\"9!\u0011\\\u001c\u0005\u0012\ree\u0001\u0003B23\t\t)A!\u001a\t\u0017\tM\u0002K!b\u0001\n\u0003I\u0012q\u001e\u0005\u000b\u0005_\u0002&\u0011!Q\u0001\n\u0005E\bB\u0003B9!\n\u0015\r\u0011\"\u0001\u0003t!Q!1\u0010)\u0003\u0002\u0003\u0006IA!\u001e\t\u000f\u0005u\u0003\u000b\"\u0001\u0003~!9\u00111\u001d)\u0005B\u0005\u0015\bb\u0002B\u0014!\u0012\u0005\u0013q\u001e\u0005\b\u00033\u0004F\u0011\u0001BC\u0011\u001d\t9\u0010\u0015C!\u0005\u0013CqA!\u000bQ\t#\u0011)\nC\u0005\u0003DA#\t\"!\u0003\u0003 \"9!\u0011\u0016)\u0005\u0012\t-\u0006b\u0002BZ!\u0012%!Q\u0017\u0005\b\u0005\u0017\u0004F\u0011\u0002Bg\u0011\u001d\u0011I\u000e\u0015C\t\u00057DqA!\u000fQ\t#\u00119\u0010C\u0005\u0003~B#\t!!\u0002\u0003\u0000\"9!Q )\u0005\u0012\r\u0015\u0001bBB\b!\u0012E1\u0011\u0003\u0005\b\u00077\u0001F\u0011CB\u000f\r!\u0019Y0\u0007\u0002\u0002\n\ru\bB\u0003C\u0004K\n\u0015\r\u0011\"\u0003\u0002p\"QA\u0011B3\u0003\u0002\u0003\u0006I!!=\t\u0019\u0011-QM!b\u0001\n\u0003\tI\u0001\"\u0004\t\u0015\u0011EQM!A!\u0002\u0013!y\u0001\u0003\u0006\u0005\u0014\u0015\u0014)\u0019!C\u0005\u0003_D!\u0002\"\u0006f\u0005\u0003\u0005\u000b\u0011BAy\u0011\u001d\ti&\u001aC\u0001\t/AqAa\nf\t\u0003\ny\u000fC\u0004\u0002d\u0016$\t%!:\t\u000f\u00055X\r\"\u0011\u0002p\"9\u0011\u0011\\3\u0005\u0002\u0011\u0005\u0002bBA|K\u0012\u0005CQ\u0005\u0005\b\u0005S)G\u0011\u0003C\u0019\u0011%\u0011\u0019%\u001aC\t\u0003\u0013!I\u0004C\u0004\u0003*\u0016$\t\u0002\"\u0011\t\u0013\tuX\r\"\u0001\u0002\u0006\u0011%\u0003b\u0002B\u007fK\u0012EA\u0011\u000b\u0005\b\u0007\u001f)G\u0011\u0003C.\u0011\u001d\u0019Y\"\u001aC\t\tKBqA!\u000ff\t#!y\u0007C\u0004\u0003Z\u0016$\t\u0002\"\u001e\t\u000f\u0011=\u0015\u0004\"\u0003\u0005\u0012\"9AqV\r\u0005\n\u0011E\u0006b\u0002C_3\u0011%Aq\u0018\u0005\t\u0005gK\u0002\u0015\"\u0003\u0005P\"A!1Z\r!\n\u0013!\u0019\u000eC\u0005\u0004lf\t\t\u0011\"\u0003\u0004n\nQq\n\u001c3ICND7+\u001a;\u000b\t\u0005\u001d\u0011\u0011B\u0001\nS6lW\u000f^1cY\u0016TA!a\u0003\u0002\u000e\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0005\u0005=\u0011!B:dC2\f7\u0001A\u000b\u0005\u0003+\t\u0019cE\u0006\u0001\u0003/\t9$!\u0011\u0002J\u0005=\u0003CBA\r\u00037\ty\"\u0004\u0002\u0002\u0006%!\u0011QDA\u0003\u0005-\t%m\u001d;sC\u000e$8+\u001a;\u0011\t\u0005\u0005\u00121\u0005\u0007\u0001\t\u001d\t)\u0003\u0001b\u0001\u0003O\u0011\u0011!Q\t\u0005\u0003S\t\t\u0004\u0005\u0003\u0002,\u00055RBAA\u0007\u0013\u0011\ty#!\u0004\u0003\u000f9{G\u000f[5oOB!\u00111FA\u001a\u0013\u0011\t)$!\u0004\u0003\u0007\u0005s\u0017\u0010\u0005\u0006\u0002\u001a\u0005e\u0012qDA\u001f\u0003\u007fIA!a\u000f\u0002\u0006\t11+\u001a;PaN\u00042!!\u0007\u0001!\u0015\tI\u0002AA\u0010!)\t\u0019%!\u0012\u0002 \u0005u\u0012qH\u0007\u0003\u0003\u0013IA!a\u0012\u0002\n\tQ2\u000b\u001e:jGR|\u0005\u000f^5nSj,G-\u0013;fe\u0006\u0014G.Z(qgBA\u00111IA&\u0003?\ti$\u0003\u0003\u0002N\u0005%!aF%uKJ\f'\r\\3GC\u000e$xN]=EK\u001a\fW\u000f\u001c;t!\u0011\t\t&a\u0016\u000f\t\u0005-\u00121K\u0005\u0005\u0003+\ni!A\u0004qC\u000e\\\u0017mZ3\n\t\u0005e\u00131\f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0005\u0003+\ni!\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003\u007f\t\u0011b\u00197bgNt\u0015-\\3\u0016\u0005\u0005\u0015\u0004\u0003BA4\u0003krA!!\u001b\u0002rA!\u00111NA\u0007\u001b\t\tiG\u0003\u0003\u0002p\u0005E\u0011A\u0002\u001fs_>$h(\u0003\u0003\u0002t\u00055\u0011A\u0002)sK\u0012,g-\u0003\u0003\u0002x\u0005e$AB*ue&twM\u0003\u0003\u0002t\u00055\u0011aD5uKJ\f'\r\\3GC\u000e$xN]=\u0016\u0005\u0005}\u0004cAA\r3M)\u0011$a!\u0002\nB!\u00111FAC\u0013\u0011\t9)!\u0004\u0003\r\u0005s\u0017PU3g!\u0019\t\u0019%a#\u0002>%!\u0011QRA\u0005\u0005=IE/\u001a:bE2,g)Y2u_JLHCAA@\u0003\u00111'o\\7\u0016\t\u0005U\u00151\u0014\u000b\u0005\u0003/\u000bi\nE\u0003\u0002\u001a\u0001\tI\n\u0005\u0003\u0002\"\u0005mEaBA\u00137\t\u0007\u0011q\u0005\u0005\b\u0003?[\u0002\u0019AAQ\u0003\tIG\u000f\u0005\u0004\u0002D\u0005\r\u0016\u0011T\u0005\u0005\u0003K\u000bIA\u0001\u0007Ji\u0016\u0014\u0018M\u00197f\u001f:\u001cW-A\u0003f[B$\u00180\u0006\u0003\u0002,\u0006EVCAAW!\u0015\tI\u0002AAX!\u0011\t\t#!-\u0005\u000f\u0005\u0015BD1\u0001\u0002(\u0005Qa.Z<Ck&dG-\u001a:\u0016\t\u0005]\u0016qY\u000b\u0003\u0003s\u0003\u0002\"a/\u0002B\u0006\u0015\u0017\u0011Z\u0007\u0003\u0003{SA!a0\u0002\n\u00059Q.\u001e;bE2,\u0017\u0002BAb\u0003{\u0013qAQ;jY\u0012,'\u000f\u0005\u0003\u0002\"\u0005\u001dGaBA\u0013;\t\u0007\u0011q\u0005\t\u0006\u00033\u0001\u0011QY\u0001\u0010\u000b6\u0004H/_(mI\"\u000b7\u000f[*fiB\u0019\u0011qZ\u0010\u000e\u0003e\u0011q\"R7qif|E\u000e\u001a%bg\"\u001cV\r^\n\u0004?\u0005U\u0007#BA\r\u0001\u0005EBCAAg\u0003!IG/\u001a:bi>\u0014XCAAo!\u0019\t\u0019%a8\u00022%!\u0011\u0011]A\u0005\u0005!IE/\u001a:bi>\u0014\u0018aB5t\u000b6\u0004H/_\u000b\u0003\u0003O\u0004B!a\u000b\u0002j&!\u00111^A\u0007\u0005\u001d\u0011un\u001c7fC:\f\u0011b\u001b8po:\u001c\u0016N_3\u0016\u0005\u0005E\b\u0003BA\u0016\u0003gLA!!>\u0002\u000e\t\u0019\u0011J\u001c;\u0002\u000f\u0019|'/Z1dQV!\u00111 B\b)\u0011\tiPa\u0001\u0011\t\u0005-\u0012q`\u0005\u0005\u0005\u0003\tiA\u0001\u0003V]&$\bb\u0002B\u0003I\u0001\u0007!qA\u0001\u0002MBA\u00111\u0006B\u0005\u0003c\u0011i!\u0003\u0003\u0003\f\u00055!!\u0003$v]\u000e$\u0018n\u001c82!\u0011\t\tCa\u0004\u0005\u000f\tEAE1\u0001\u0002(\t\tQ+\u0001\u0003iK\u0006$WCAA\u0019\u0003)AW-\u00193PaRLwN\\\u000b\u0003\u00057qA!a\u000b\u0003\u001e%!!qDA\u0007\u0003\u0011quN\\3\u0002\tQ\f\u0017\u000e\\\u000b\u0003\u0003+\fA!\u001b8ji\u0006!1/\u001b>f\u0003\u00119W\r\u001e\u0019\u0015\u0011\u0005\u001d(Q\u0006B\u0019\u0005kAqAa\f+\u0001\u0004\t\t$\u0001\u0003fY\u0016l\u0007b\u0002B\u001aU\u0001\u0007\u0011\u0011_\u0001\u0005Q\u0006\u001c\b\u000eC\u0004\u00038)\u0002\r!!=\u0002\u000b1,g/\u001a7\u0002\u0013M,(m]3u\u001f\u001a\u0004DCBAt\u0005{\u0011\t\u0005C\u0004\u0003@-\u0002\r!!6\u0002\tQD\u0017\r\u001e\u0005\b\u0005oY\u0003\u0019AAy\u0003!)\b\u000fZ1uK\u0012\u0004D\u0003\u0003B$\u0007O\u001bIka+\u0011\u000b\u0005=w'!\r\u0003\u0017=cG\rS1tQN+G/M\u000b\u0005\u0005\u001b\u001aIcE\u00028\u0005\u001f\u0002R!a45\u0007O\u0011a\u0002T3bM>cG\rS1tQN+G/\u0006\u0003\u0003V\tm3c\u0001\u001b\u0003XA)\u0011\u0011\u0004\u0001\u0003ZA!\u0011\u0011\u0005B.\t\u001d\t)\u0003\u000eb\u0001\u0003O!\"Aa\u0018\u0011\u000b\u0005=GG!\u0017*\u0007Q:\u0004K\u0001\u000bPY\u0012D\u0015m\u001d5TKR\u001cu\u000e\u001c7jg&|g.M\u000b\u0005\u0005O\u0012igE\u0002Q\u0005S\u0002R!a45\u0005W\u0002B!!\t\u0003n\u00119\u0011Q\u0005)C\u0002\u0005\u001d\u0012!\u00025bg\"\u0004\u0013AA6t+\t\u0011)\b\u0005\u0004\u0002\u001a\t]$1N\u0005\u0005\u0005s\n)AA\u0004MSN$8+\u001a;\u0002\u0007-\u001c\b\u0005\u0006\u0004\u0003\u0000\t\u0005%1\u0011\t\u0006\u0003\u001f\u0004&1\u000e\u0005\b\u0005g)\u0006\u0019AAy\u0011\u001d\u0011\t(\u0016a\u0001\u0005k*\"Aa\"\u0011\r\u0005\r\u0013q\u001cB6+\u0011\u0011YIa%\u0015\t\u0005u(Q\u0012\u0005\b\u0005\u000bI\u0006\u0019\u0001BH!!\tYC!\u0003\u0003l\tE\u0005\u0003BA\u0011\u0005'#qA!\u0005Z\u0005\u0004\t9\u0003\u0006\u0005\u0002h\n]%1\u0014BO\u0011\u001d\u0011IJ\u0017a\u0001\u0005W\n1a[3z\u0011\u001d\u0011\u0019D\u0017a\u0001\u0003cDqAa\u000e[\u0001\u0004\t\t\u0010\u0006\u0005\u0003\"\n\r&Q\u0015BT!\u0015\tI\u0002\u0001B6\u0011\u001d\u0011Ij\u0017a\u0001\u0005WBqAa\r\\\u0001\u0004\t\t\u0010C\u0004\u00038m\u0003\r!!=\u0002\u0011I,Wn\u001c<fIB\"\u0002B!)\u0003.\n=&\u0011\u0017\u0005\b\u00053c\u0006\u0019\u0001B6\u0011\u001d\u0011\u0019\u0004\u0018a\u0001\u0003cDqAa\u000e]\u0001\u0004\t\t0A\u0006xe&$Xm\u00142kK\u000e$H\u0003BA\u007f\u0005oCqA!/^\u0001\u0004\u0011Y,A\u0002pkR\u0004BA!0\u0003H6\u0011!q\u0018\u0006\u0005\u0005\u0003\u0014\u0019-\u0001\u0002j_*\u0011!QY\u0001\u0005U\u00064\u0018-\u0003\u0003\u0003J\n}&AE(cU\u0016\u001cGoT;uaV$8\u000b\u001e:fC6\f!B]3bI>\u0013'.Z2u)\u0011\tiPa4\t\u000f\tEg\f1\u0001\u0003T\u0006\u0011\u0011N\u001c\t\u0005\u0005{\u0013).\u0003\u0003\u0003X\n}&!E(cU\u0016\u001cG/\u00138qkR\u001cFO]3b[\u00069a-\u001b7uKJ\u0004D\u0003\u0004BQ\u0005;\u0014\u0019Oa:\u0003j\nM\bb\u0002Bp?\u0002\u0007!\u0011]\u0001\u0002aBA\u00111\u0006B\u0005\u0005W\n9\u000fC\u0004\u0003f~\u0003\r!a:\u0002\r9,w-\u0019;f\u0011\u001d\u00119d\u0018a\u0001\u0003cDqAa;`\u0001\u0004\u0011i/\u0001\u0004ck\u001a4WM\u001d\t\u0007\u0003W\u0011yO!)\n\t\tE\u0018Q\u0002\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\b\u0005k|\u0006\u0019AAy\u0003\u001dygMZ:fiB\"b!a:\u0003z\nm\bb\u0002B A\u0002\u0007!\u0011\u0015\u0005\b\u0005o\u0001\u0007\u0019AAy\u0003\u0019)h.[8oaQ1!\u0011UB\u0001\u0007\u0007AqAa\u0010b\u0001\u0004\u0011I\u0007C\u0004\u00038\u0005\u0004\r!!=\u0015\u0015\t\u00056qAB\u0005\u0007\u0017\u0019i\u0001C\u0004\u0003@\t\u0004\rA!)\t\u000f\t]\"\r1\u0001\u0002r\"9!1\u001e2A\u0002\t5\bb\u0002B{E\u0002\u0007\u0011\u0011_\u0001\u000bS:$XM]:fGR\u0004DC\u0003BQ\u0007'\u0019)ba\u0006\u0004\u001a!9!qH2A\u0002\t\u0005\u0006b\u0002B\u001cG\u0002\u0007\u0011\u0011\u001f\u0005\b\u0005W\u001c\u0007\u0019\u0001Bw\u0011\u001d\u0011)p\u0019a\u0001\u0003c\fQ\u0001Z5gMB\"\"B!)\u0004 \r\u000521EB\u0013\u0011\u001d\u0011y\u0004\u001aa\u0001\u0005CCqAa\u000ee\u0001\u0004\t\t\u0010C\u0004\u0003l\u0012\u0004\rA!<\t\u000f\tUH\r1\u0001\u0002rB!\u0011\u0011EB\u0015\t\u001d\t)c\u000eb\u0001\u0003O)\"aa\n\u0002\t-,\u0017\u0010\t\u000b\u0007\u0007c\u0019\u0019d!\u000e\u0011\u000b\u0005=wga\n\t\u000f\teE\b1\u0001\u0004(!9!1\u0007\u001fA\u0002\u0005EXCAB\u001d!\u0019\t\u0019%a8\u0004(U!1QHB#)\u0011\tipa\u0010\t\u000f\t\u0015\u0001\t1\u0001\u0004BAA\u00111\u0006B\u0005\u0007O\u0019\u0019\u0005\u0005\u0003\u0002\"\r\u0015Ca\u0002B\t\u0001\n\u0007\u0011qE\u000b\u0003\u0007\u0013\u0002b!a\u000b\u0004L\r\u001d\u0012\u0002BB'\u0003\u001b\u0011AaU8nKV\u00111\u0011\u000b\t\u0006\u00033\u00011qE\u0001\u0005Y\u0006\u001cH\u000f\u0006\u0005\u0002h\u000e]3\u0011LB.\u0011\u001d\u0011Ij\u0012a\u0001\u0007OAqAa\rH\u0001\u0004\t\t\u0010C\u0004\u00038\u001d\u0003\r!!=\u0015\u0011\rE3qLB1\u0007GBqA!'I\u0001\u0004\u00199\u0003C\u0004\u00034!\u0003\r!!=\t\u000f\t]\u0002\n1\u0001\u0002rRA1\u0011KB4\u0007S\u001aY\u0007C\u0004\u0003\u001a&\u0003\raa\n\t\u000f\tM\u0012\n1\u0001\u0002r\"9!qG%A\u0002\u0005EHCBAt\u0007_\u001a\t\bC\u0004\u0003@)\u0003\ra!\u0015\t\u000f\t]\"\n1\u0001\u0002rR11\u0011KB;\u0007oBqAa\u0010L\u0001\u0004\u0011y\u0005C\u0004\u00038-\u0003\r!!=\u0015\u0015\rE31PB?\u0007\u007f\u001a\u0019\tC\u0004\u0003@1\u0003\ra!\u0015\t\u000f\t]B\n1\u0001\u0002r\"9!1\u001e'A\u0002\r\u0005\u0005CBA\u0016\u0005_\u001c\t\u0006C\u0004\u0003v2\u0003\r!!=\u0015\u0015\rE3qQBE\u0007\u0017\u001bi\tC\u0004\u0003@5\u0003\ra!\u0015\t\u000f\t]R\n1\u0001\u0002r\"9!1^'A\u0002\r\u0005\u0005b\u0002B{\u001b\u0002\u0007\u0011\u0011\u001f\u000b\u000b\u0007#\u001a\tja%\u0004\u0016\u000e]\u0005b\u0002B \u001d\u0002\u00071\u0011\u000b\u0005\b\u0005oq\u0005\u0019AAy\u0011\u001d\u0011YO\u0014a\u0001\u0007\u0003CqA!>O\u0001\u0004\t\t\u0010\u0006\u0007\u0004R\rm5qTBQ\u0007G\u001b)\u000bC\u0004\u0003`>\u0003\ra!(\u0011\u0011\u0005-\"\u0011BB\u0014\u0003ODqA!:P\u0001\u0004\t9\u000fC\u0004\u00038=\u0003\r!!=\t\u000f\t-x\n1\u0001\u0004\u0002\"9!Q_(A\u0002\u0005E\bb\u0002B\u0018Y\u0001\u0007\u0011\u0011\u0007\u0005\b\u0005ga\u0003\u0019AAy\u0011\u001d\u00119\u0004\fa\u0001\u0003c$\u0002\"!4\u00040\u000eE61\u0017\u0005\b\u00053k\u0003\u0019AA\u0019\u0011\u001d\u0011\u0019$\fa\u0001\u0003cDqAa\u000e.\u0001\u0004\t\t\u0010\u0006\u0004\u0002V\u000e]61\u0018\u0005\b\u0005\u007fq\u0003\u0019AB]!\u0015\ty\rNA\u0019\u0011\u001d\u00119D\fa\u0001\u0003c$\"\"!6\u0004@\u000e\u000571YBd\u0011\u001d\u0011yd\fa\u0001\u0003+DqAa\u000e0\u0001\u0004\t\t\u0010C\u0004\u0003l>\u0002\ra!2\u0011\r\u0005-\"q^Ak\u0011\u001d\u0011)p\fa\u0001\u0003c$\"\"!6\u0004L\u000e57qZBi\u0011\u001d\u0011y\u0004\ra\u0001\u0003+DqAa\u000e1\u0001\u0004\t\t\u0010C\u0004\u0003lB\u0002\ra!2\t\u000f\tU\b\u00071\u0001\u0002rRQ\u0011Q[Bk\u0007/\u001cIna7\t\u000f\t}\u0012\u00071\u0001\u0002V\"9!qG\u0019A\u0002\u0005E\bb\u0002Bvc\u0001\u00071Q\u0019\u0005\b\u0005k\f\u0004\u0019AAy)1\t)na8\u0004d\u000e\u00158q]Bu\u0011\u001d\u0011yN\ra\u0001\u0007C\u0004\u0002\"a\u000b\u0003\n\u0005E\u0012q\u001d\u0005\b\u0005K\u0014\u0004\u0019AAt\u0011\u001d\u00119D\ra\u0001\u0003cDqAa;3\u0001\u0004\u0019)\rC\u0004\u0003vJ\u0002\r!!=\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\r=\b\u0003BBy\u0007ol!aa=\u000b\t\rU(1Y\u0001\u0005Y\u0006tw-\u0003\u0003\u0004z\u000eM(AB(cU\u0016\u001cGOA\u0006ICNDGK]5f'\u0016$X\u0003BB\u0000\t\u000b\u00192!\u001aC\u0001!\u0015\tI\u0002\u0001C\u0002!\u0011\t\t\u0003\"\u0002\u0005\u000f\u0005\u0015RM1\u0001\u0002(\u00051!-\u001b;nCB\fqAY5u[\u0006\u0004\b%A\u0003fY\u0016l7/\u0006\u0002\u0005\u0010A1\u00111\u0006Bx\t\u0003\ta!\u001a7f[N\u0004\u0013!B:ju\u0016\u0004\u0014AB:ju\u0016\u0004\u0004\u0005\u0006\u0005\u0005\u001a\u0011mAQ\u0004C\u0010!\u0015\ty-\u001aC\u0002\u0011\u001d!9\u0001\u001ca\u0001\u0003cDq\u0001b\u0003m\u0001\u0004!y\u0001C\u0004\u0005\u00141\u0004\r!!=\u0016\u0005\u0011\r\u0002CBA\"\u0003?$\u0019!\u0006\u0003\u0005(\u0011=B\u0003BA\u007f\tSAqA!\u0002r\u0001\u0004!Y\u0003\u0005\u0005\u0002,\t%A1\u0001C\u0017!\u0011\t\t\u0003b\f\u0005\u000f\tE\u0011O1\u0001\u0002(QA\u0011q\u001dC\u001a\tk!9\u0004C\u0004\u0003\u001aJ\u0004\r\u0001b\u0001\t\u000f\tM\"\u000f1\u0001\u0002r\"9!q\u0007:A\u0002\u0005EH\u0003\u0003C\u0001\tw!i\u0004b\u0010\t\u000f\te5\u000f1\u0001\u0005\u0004!9!1G:A\u0002\u0005E\bb\u0002B\u001cg\u0002\u0007\u0011\u0011\u001f\u000b\t\t\u0003!\u0019\u0005\"\u0012\u0005H!9!\u0011\u0014;A\u0002\u0011\r\u0001b\u0002B\u001ai\u0002\u0007\u0011\u0011\u001f\u0005\b\u0005o!\b\u0019AAy)\u0019!\t\u0001b\u0013\u0005P!9!qH;A\u0002\u00115\u0003#BAhi\u0011\r\u0001b\u0002B\u001ck\u0002\u0007\u0011\u0011\u001f\u000b\u000b\t\u0003!\u0019\u0006\"\u0016\u0005X\u0011e\u0003b\u0002B m\u0002\u0007A\u0011\u0001\u0005\b\u0005o1\b\u0019AAy\u0011\u001d\u0011YO\u001ea\u0001\t\u001fAqA!>w\u0001\u0004\t\t\u0010\u0006\u0006\u0005\u0002\u0011uCq\fC1\tGBqAa\u0010x\u0001\u0004!\t\u0001C\u0004\u00038]\u0004\r!!=\t\u000f\t-x\u000f1\u0001\u0005\u0010!9!Q_<A\u0002\u0005EHC\u0003C\u0001\tO\"I\u0007b\u001b\u0005n!9!q\b=A\u0002\u0011\u0005\u0001b\u0002B\u001cq\u0002\u0007\u0011\u0011\u001f\u0005\b\u0005WD\b\u0019\u0001C\b\u0011\u001d\u0011)\u0010\u001fa\u0001\u0003c$b!a:\u0005r\u0011M\u0004b\u0002B s\u0002\u0007A\u0011\u0001\u0005\b\u0005oI\b\u0019AAy)1!\t\u0001b\u001e\u0005|\u0011uDq\u0010CA\u0011\u001d\u0011yN\u001fa\u0001\ts\u0002\u0002\"a\u000b\u0003\n\u0011\r\u0011q\u001d\u0005\b\u0005KT\b\u0019AAt\u0011\u001d\u00119D\u001fa\u0001\u0003cDqAa;{\u0001\u0004!y\u0001C\u0004\u0003vj\u0004\r!!=)\u000f\u0015$)\tb#\u0005\u000eB!\u00111\u0006CD\u0013\u0011!I)!\u0004\u0003!M+'/[1m-\u0016\u00148/[8o+&#\u0015!\u0002<bYV,g\u0004\u0003rN\u000b\u001cr\u000b7'R\u0002\u001f5\f7.\u001a%bg\"$&/[3TKR,B\u0001b%\u0005\u001aRaAQ\u0013CN\t?#)\u000b\"+\u0005.B)\u0011qZ3\u0005\u0018B!\u0011\u0011\u0005CM\t\u001d\t)c\u001fb\u0001\u0003OAq\u0001\"(|\u0001\u0004\t\t0A\u0003iCND\u0007\u0007C\u0004\u0005\"n\u0004\r\u0001b)\u0002\u000b\u0015dW-\u001c\u0019\u0011\u000b\u0005e\u0001\u0001b&\t\u000f\u0011\u001d6\u00101\u0001\u0002r\u0006)\u0001.Y:ic!9A1V>A\u0002\u0011\r\u0016!B3mK6\f\u0004b\u0002B\u001cw\u0002\u0007\u0011\u0011_\u0001\u000bEV4g-\u001a:TSj,G\u0003BAy\tgCqAa\n}\u0001\u0004\t\t\u0010K\u0002}\to\u0003B!a\u000b\u0005:&!A1XA\u0007\u0005\u0019Ig\u000e\\5oK\u0006Ya.\u001e7m)>,U\u000e\u001d;z+\u0011!\t\rb2\u0015\t\u0011\rG\u0011\u001a\t\u0006\u00033\u0001AQ\u0019\t\u0005\u0003C!9\rB\u0004\u0002&u\u0014\r!a\n\t\u000f\u0011-W\u00101\u0001\u0005D\u0006\t1\u000fK\u0002~\to#B!!@\u0005R\"9!\u0011\u0018@A\u0002\tmF\u0003BA\u007f\t+DqA!5\u0000\u0001\u0004\u0011\u0019\u000eK\u0004\u001a\t\u000b#Y\t\"7\u001f\u0003\r\t\u0001bY8oi\u0006Lgn\u001d\u000b\u0005\u0003O$y\u000eC\u0004\u00030\u0011\u0001\r!a\b\u0002\t%t7\r\u001c\u000b\u0005\u0003\u007f!)\u000fC\u0004\u00030\u0015\u0001\r!a\b\u0002\t\u0015D8\r\u001c\u000b\u0005\u0003\u007f!Y\u000fC\u0004\u00030\u0019\u0001\r!a\b\u0002\u0011M,(m]3u\u001f\u001a$B!a:\u0005r\"9!qH\u0004A\u0002\u0011M\bCBA\"\tk\fy\"\u0003\u0003\u0005x\u0006%!aA*fi\u000611m\u001c8dCR$B!a\u0010\u0005~\"9!q\b\u0005A\u0002\u0011}\bCBA\"\u0003G\u000by\"A\u0005j]R,'o]3diR!\u0011qHC\u0003\u0011\u001d\u0011y$\u0003a\u0001\tg\fA\u0001Z5gMR!\u0011qHC\u0006\u0011\u001d\u0011yD\u0003a\u0001\tg\faAZ5mi\u0016\u0014H\u0003BA \u000b#AqAa8\f\u0001\u0004)\u0019\u0002\u0005\u0005\u0002,\t%\u0011qDAt\u0003%1\u0017\u000e\u001c;fe:{G\u000f\u0006\u0003\u0002@\u0015e\u0001b\u0002Bp\u0019\u0001\u0007Q1C\u000b\u0003\u0003\u007f!\u0002\"a:\u0006 \u0015\u0005R1\u0005\u0005\b\u00053{\u0001\u0019AA\u0010\u0011\u001d\u0011\u0019d\u0004a\u0001\u0003cDqAa\u000e\u0010\u0001\u0004\t\t\u0010\u0006\u0005\u0002@\u0015\u001dR\u0011FC\u0016\u0011\u001d\u0011I\n\u0005a\u0001\u0003?AqAa\r\u0011\u0001\u0004\t\t\u0010C\u0004\u00038A\u0001\r!!=\u0015\u0011\u0005}RqFC\u0019\u000bgAqA!'\u0012\u0001\u0004\ty\u0002C\u0004\u00034E\u0001\r!!=\t\u000f\t]\u0012\u00031\u0001\u0002rRa\u0011qHC\u001c\u000bs)Y$\"\u0010\u0006B!9!q\u001c\nA\u0002\u0015M\u0001b\u0002Bs%\u0001\u0007\u0011q\u001d\u0005\b\u0005o\u0011\u0002\u0019AAy\u0011\u001d\u0011YO\u0005a\u0001\u000b\u007f\u0001b!a\u000b\u0003p\u0006}\u0002b\u0002B{%\u0001\u0007\u0011\u0011\u001f\u000b\u0007\u0003O,)%b\u0012\t\u000f\t}2\u00031\u0001\u0002@!9!qG\nA\u0002\u0005EHCBA \u000b\u0017*)\u0006C\u0004\u0003@Q\u0001\r!\"\u0014\u0011\u000b\u0015=C'a\b\u000f\u0007\u0005e\u0001$\u0001\u0006PY\u0012D\u0015m\u001d5TKRDs\u0001\u0007CC\t\u0017#I\u000eC\u0004\u00038Q\u0001\r!!=\u0015\u0015\u0005}R\u0011LC.\u000b;*y\u0006C\u0004\u0003@U\u0001\r!a\u0010\t\u000f\t]R\u00031\u0001\u0002r\"9!1^\u000bA\u0002\u0015}\u0002b\u0002B{+\u0001\u0007\u0011\u0011\u001f\u000b\u000b\u0003\u007f)\u0019'\"\u001a\u0006h\u0015%\u0004b\u0002B -\u0001\u0007\u0011q\b\u0005\b\u0005o1\u0002\u0019AAy\u0011\u001d\u0011YO\u0006a\u0001\u000b\u007fAqA!>\u0017\u0001\u0004\t\t\u0010\u0006\u0006\u0002@\u00155TqNC9\u000bgBqAa\u0010\u0018\u0001\u0004\ty\u0004C\u0004\u00038]\u0001\r!!=\t\u000f\t-x\u00031\u0001\u0006@!9!Q_\fA\u0002\u0005E\u0018\u0006\u0002\u0001 KR\u0002"
)
public abstract class OldHashSet extends AbstractSet implements StrictOptimizedIterableOps, Serializable {
   public static Builder newBuilder() {
      return OldHashSet$.MODULE$.newBuilder();
   }

   public static OldHashSet from(final IterableOnce it) {
      return OldHashSet$.MODULE$.from(it);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final int n5, final Function5 f) {
      return OldHashSet$.MODULE$.tabulate(n1, n2, n3, n4, n5, f);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final int n4, final Function4 f) {
      return OldHashSet$.MODULE$.tabulate(n1, n2, n3, n4, f);
   }

   public static Object tabulate(final int n1, final int n2, final int n3, final Function3 f) {
      return OldHashSet$.MODULE$.tabulate(n1, n2, n3, f);
   }

   public static Object tabulate(final int n1, final int n2, final Function2 f) {
      return OldHashSet$.MODULE$.tabulate(n1, n2, f);
   }

   public static Object tabulate(final int n, final Function1 f) {
      return OldHashSet$.MODULE$.tabulate(n, f);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final int n5, final Function0 elem) {
      return OldHashSet$.MODULE$.fill(n1, n2, n3, n4, n5, elem);
   }

   public static Object fill(final int n1, final int n2, final int n3, final int n4, final Function0 elem) {
      return OldHashSet$.MODULE$.fill(n1, n2, n3, n4, elem);
   }

   public static Object fill(final int n1, final int n2, final int n3, final Function0 elem) {
      return OldHashSet$.MODULE$.fill(n1, n2, n3, elem);
   }

   public static Object fill(final int n1, final int n2, final Function0 elem) {
      return OldHashSet$.MODULE$.fill(n1, n2, elem);
   }

   public static Object fill(final int n, final Function0 elem) {
      return OldHashSet$.MODULE$.fill(n, elem);
   }

   public static Object range(final Object start, final Object end, final Object step, final Integral evidence$4) {
      return OldHashSet$.MODULE$.range(start, end, step, evidence$4);
   }

   public static Object range(final Object start, final Object end, final Integral evidence$3) {
      return OldHashSet$.MODULE$.range(start, end, evidence$3);
   }

   public static Object unfold(final Object init, final Function1 f) {
      return OldHashSet$.MODULE$.unfold(init, f);
   }

   public static Object iterate(final Object start, final int len, final Function1 f) {
      return OldHashSet$.MODULE$.iterate(start, len, f);
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

   public String className() {
      return "OldHashSet";
   }

   public OldHashSet$ iterableFactory() {
      return OldHashSet$.MODULE$;
   }

   public boolean contains(final Object elem) {
      return this.get0(elem, .MODULE$.computeHash(elem), 0);
   }

   public OldHashSet incl(final Object elem) {
      return this.updated0(elem, .MODULE$.computeHash(elem), 0);
   }

   public OldHashSet excl(final Object elem) {
      return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$nullToEmpty(this.removed0(elem, .MODULE$.computeHash(elem), 0));
   }

   public boolean subsetOf(final Set that) {
      if (that instanceof OldHashSet) {
         OldHashSet var4 = (OldHashSet)that;
         return this.subsetOf0(var4, 0);
      } else {
         return SetOps.subsetOf$(this, that);
      }
   }

   public OldHashSet concat(final IterableOnce that) {
      if (that instanceof OldHashSet) {
         OldHashSet var4 = (OldHashSet)that;
         OldHashSet[] buffer = new OldHashSet[OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$bufferSize(this.size() + var4.size())];
         return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$nullToEmpty(this.union0(var4, 0, buffer, 0));
      } else {
         return (OldHashSet)SetOps.concat$(this, that);
      }
   }

   public OldHashSet intersect(final Set that) {
      if (that instanceof OldHashSet) {
         OldHashSet var4 = (OldHashSet)that;
         OldHashSet[] buffer = new OldHashSet[OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$bufferSize(scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(this.size()), var4.size()))];
         return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$nullToEmpty(this.intersect0(var4, 0, buffer, 0));
      } else {
         return (OldHashSet)SetOps.intersect$(this, that);
      }
   }

   public OldHashSet diff(final Set that) {
      if (that instanceof OldHashSet) {
         OldHashSet var4 = (OldHashSet)that;
         OldHashSet[] buffer = new OldHashSet[OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$bufferSize(this.size())];
         return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$nullToEmpty(this.diff0(var4, 0, buffer, 0));
      } else {
         return (OldHashSet)scala.collection.immutable.SetOps.diff$(this, that);
      }
   }

   public OldHashSet filter(final Function1 p) {
      OldHashSet[] buffer = new OldHashSet[OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$bufferSize(this.size())];
      return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$nullToEmpty(this.filter0(p, false, 0, buffer, 0));
   }

   public OldHashSet filterNot(final Function1 p) {
      OldHashSet[] buffer = new OldHashSet[OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$bufferSize(this.size())];
      return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$nullToEmpty(this.filter0(p, true, 0, buffer, 0));
   }

   public OldHashSet tail() {
      return (OldHashSet)this.$minus(this.head());
   }

   public OldHashSet init() {
      return (OldHashSet)this.$minus(this.last());
   }

   public abstract boolean get0(final Object key, final int hash, final int level);

   public abstract OldHashSet updated0(final Object key, final int hash, final int level);

   public abstract OldHashSet removed0(final Object key, final int hash, final int level);

   public abstract OldHashSet filter0(final Function1 p, final boolean negate, final int level, final OldHashSet[] buffer, final int offset0);

   public abstract boolean subsetOf0(final OldHashSet that, final int level);

   public abstract OldHashSet union0(final LeafOldHashSet that, final int level);

   public abstract OldHashSet union0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0);

   public abstract OldHashSet intersect0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0);

   public abstract OldHashSet diff0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0);

   public OldHashSet() {
      StrictOptimizedIterableOps.$init$(this);
   }

   private static class EmptyOldHashSet$ extends OldHashSet {
      public static final EmptyOldHashSet$ MODULE$ = new EmptyOldHashSet$();

      public Iterator iterator() {
         return scala.collection.Iterator..MODULE$.empty();
      }

      public boolean isEmpty() {
         return true;
      }

      public int knownSize() {
         return 0;
      }

      public void foreach(final Function1 f) {
      }

      public Object head() {
         throw new NoSuchElementException("Empty Set");
      }

      public None headOption() {
         return scala.None..MODULE$;
      }

      public OldHashSet tail() {
         throw new NoSuchElementException("Empty Set");
      }

      public OldHashSet init() {
         throw new NoSuchElementException("Empty Set");
      }

      public int size() {
         return 0;
      }

      public boolean get0(final Object elem, final int hash, final int level) {
         return false;
      }

      public boolean subsetOf0(final OldHashSet that, final int level) {
         return true;
      }

      public OldHashSet1 updated0(final Object elem, final int hash, final int level) {
         return new OldHashSet1(elem, hash);
      }

      public EmptyOldHashSet$ removed0(final Object key, final int hash, final int level) {
         return this;
      }

      public OldHashSet union0(final LeafOldHashSet that, final int level) {
         return that;
      }

      public OldHashSet union0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         return that;
      }

      public OldHashSet intersect0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         return null;
      }

      public OldHashSet diff0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         return null;
      }

      public OldHashSet filter0(final Function1 p, final boolean negate, final int level, final OldHashSet[] buffer, final int offset0) {
         return null;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EmptyOldHashSet$.class);
      }

      public EmptyOldHashSet$() {
      }
   }

   public abstract static class LeafOldHashSet extends OldHashSet {
      public abstract int hash();
   }

   public static final class OldHashSet1 extends LeafOldHashSet {
      private final Object key;
      private final int hash;

      public Object key() {
         return this.key;
      }

      public int hash() {
         return this.hash;
      }

      public boolean isEmpty() {
         return false;
      }

      public int knownSize() {
         return 1;
      }

      public Iterator iterator() {
         return scala.collection.Iterator..MODULE$.single(this.key());
      }

      public void foreach(final Function1 f) {
         f.apply(this.key());
      }

      public Object head() {
         return this.key();
      }

      public Some headOption() {
         return new Some(this.key());
      }

      public OldHashSet tail() {
         return OldHashSet$.MODULE$.empty();
      }

      public Object last() {
         return this.key();
      }

      public OldHashSet init() {
         return OldHashSet$.MODULE$.empty();
      }

      public int size() {
         return 1;
      }

      public boolean get0(final Object key, final int hash, final int level) {
         return hash == this.hash() && BoxesRunTime.equals(key, this.key());
      }

      public OldHashSet updated0(final Object key, final int hash, final int level) {
         if (hash == this.hash() && BoxesRunTime.equals(key, this.key())) {
            return this;
         } else {
            return (OldHashSet)(hash != this.hash() ? OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$makeHashTrieSet(this.hash(), this, hash, new OldHashSet1(key, hash), level) : new OldHashSetCollision1(hash, (ListSet)scala.collection.immutable.ListSet..MODULE$.empty().$plus(this.key()).$plus(key)));
         }
      }

      public OldHashSet removed0(final Object key, final int hash, final int level) {
         return hash == this.hash() && BoxesRunTime.equals(key, this.key()) ? null : this;
      }

      public boolean subsetOf0(final OldHashSet that, final int level) {
         return that.get0(this.key(), this.hash(), level);
      }

      public OldHashSet union0(final LeafOldHashSet that, final int level) {
         if (that.hash() != this.hash()) {
            return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$makeHashTrieSet(this.hash(), this, that.hash(), that, level);
         } else if (that instanceof OldHashSet1) {
            OldHashSet1 var5 = (OldHashSet1)that;
            return (OldHashSet)(BoxesRunTime.equals(this.key(), var5.key()) ? this : new OldHashSetCollision1(this.hash(), (ListSet)scala.collection.immutable.ListSet..MODULE$.empty().$plus(this.key()).$plus(var5.key())));
         } else if (that instanceof OldHashSetCollision1) {
            OldHashSetCollision1 var6 = (OldHashSetCollision1)that;
            ListSet ks1 = (ListSet)var6.ks().$plus(this.key());
            return ks1.size() == var6.ks().size() ? var6 : new OldHashSetCollision1(this.hash(), ks1);
         } else {
            throw new MatchError(that);
         }
      }

      public OldHashSet union0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         return that.union0(this, level);
      }

      public OldHashSet intersect0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         return that.get0(this.key(), this.hash(), level) ? this : null;
      }

      public OldHashSet diff0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         return that.get0(this.key(), this.hash(), level) ? null : this;
      }

      public OldHashSet filter0(final Function1 p, final boolean negate, final int level, final OldHashSet[] buffer, final int offset0) {
         return negate ^ BoxesRunTime.unboxToBoolean(p.apply(this.key())) ? this : null;
      }

      public OldHashSet1(final Object key, final int hash) {
         this.key = key;
         this.hash = hash;
      }
   }

   public static final class OldHashSetCollision1 extends LeafOldHashSet {
      private final int hash;
      private final ListSet ks;

      public int hash() {
         return this.hash;
      }

      public ListSet ks() {
         return this.ks;
      }

      public boolean isEmpty() {
         return false;
      }

      public int size() {
         return this.ks().size();
      }

      public Iterator iterator() {
         return this.ks().iterator();
      }

      public void foreach(final Function1 f) {
         this.ks().foreach(f);
      }

      public boolean get0(final Object key, final int hash, final int level) {
         return hash == this.hash() ? this.ks().contains(key) : false;
      }

      public OldHashSet updated0(final Object key, final int hash, final int level) {
         return (OldHashSet)(hash == this.hash() ? new OldHashSetCollision1(hash, (ListSet)this.ks().$plus(key)) : OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$makeHashTrieSet(this.hash(), this, hash, new OldHashSet1(key, hash), level));
      }

      public OldHashSet removed0(final Object key, final int hash, final int level) {
         if (hash == this.hash()) {
            ListSet ks1 = (ListSet)this.ks().$minus(key);
            int var5 = ks1.size();
            switch (var5) {
               case 0:
                  return null;
               case 1:
                  return new OldHashSet1(ks1.head(), hash);
               default:
                  return var5 == this.ks().size() ? this : new OldHashSetCollision1(hash, ks1);
            }
         } else {
            return this;
         }
      }

      private void writeObject(final ObjectOutputStream out) {
         throw scala.sys.package..MODULE$.error("cannot serialize an immutable.OldHashSet where all items have the same 32-bit hash code");
      }

      private void readObject(final ObjectInputStream in) {
         throw scala.sys.package..MODULE$.error("cannot deserialize an immutable.OldHashSet where all items have the same 32-bit hash code");
      }

      public OldHashSet filter0(final Function1 p, final boolean negate, final int level, final OldHashSet[] buffer, final int offset0) {
         ListSet ks1 = negate ? (ListSet)this.ks().filterNot(p) : (ListSet)this.ks().filter(p);
         int var7 = ks1.size();
         switch (var7) {
            case 0:
               return null;
            case 1:
               return new OldHashSet1(ks1.head(), this.hash());
            default:
               return var7 == this.ks().size() ? this : new OldHashSetCollision1(this.hash(), ks1);
         }
      }

      public boolean subsetOf0(final OldHashSet that, final int level) {
         return this.ks().forall((key) -> BoxesRunTime.boxToBoolean($anonfun$subsetOf0$1(this, that, level, key)));
      }

      public OldHashSet union0(final LeafOldHashSet that, final int level) {
         if (that.hash() != this.hash()) {
            return OldHashSet$.MODULE$.scala$collection$immutable$OldHashSet$$makeHashTrieSet(this.hash(), this, that.hash(), that, level);
         } else if (that instanceof OldHashSet1) {
            OldHashSet1 var5 = (OldHashSet1)that;
            ListSet ks1 = (ListSet)this.ks().$plus(var5.key());
            return ks1.size() == this.ks().size() ? this : new OldHashSetCollision1(this.hash(), ks1);
         } else if (that instanceof OldHashSetCollision1) {
            OldHashSetCollision1 var7 = (OldHashSetCollision1)that;
            ListSet ks1 = (ListSet)this.ks().$plus$plus(var7.ks());
            int var9 = ks1.size();
            switch (var9) {
               default:
                  if (var9 == this.ks().size()) {
                     return this;
                  } else {
                     return var9 == var7.ks().size() ? var7 : new OldHashSetCollision1(this.hash(), ks1);
                  }
            }
         } else {
            throw new MatchError(that);
         }
      }

      public OldHashSet union0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         if (that instanceof LeafOldHashSet) {
            LeafOldHashSet var7 = (LeafOldHashSet)that;
            return this.union0(var7, level);
         } else if (that instanceof HashTrieSet) {
            HashTrieSet var8 = (HashTrieSet)that;
            return var8.union0(this, level);
         } else {
            return this;
         }
      }

      public OldHashSet intersect0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         ListSet ks1 = (ListSet)this.ks().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$intersect0$1(this, that, level, x$1)));
         int var7 = ks1.size();
         if (0 == var7) {
            return null;
         } else if (var7 == this.size()) {
            return this;
         } else if (var7 == that.size()) {
            return that;
         } else {
            return (OldHashSet)(1 == var7 ? new OldHashSet1(ks1.head(), this.hash()) : new OldHashSetCollision1(this.hash(), ks1));
         }
      }

      public OldHashSet diff0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         ListSet ks1 = (ListSet)this.ks().filterNot((x$2) -> BoxesRunTime.boxToBoolean($anonfun$diff0$1(this, that, level, x$2)));
         int var7 = ks1.size();
         if (0 == var7) {
            return null;
         } else if (var7 == this.size()) {
            return this;
         } else {
            return (OldHashSet)(1 == var7 ? new OldHashSet1(ks1.head(), this.hash()) : new OldHashSetCollision1(this.hash(), ks1));
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$subsetOf0$1(final OldHashSetCollision1 $this, final OldHashSet that$1, final int level$1, final Object key) {
         return that$1.get0(key, $this.hash(), level$1);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$intersect0$1(final OldHashSetCollision1 $this, final OldHashSet that$2, final int level$2, final Object x$1) {
         return that$2.get0(x$1, $this.hash(), level$2);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$diff0$1(final OldHashSetCollision1 $this, final OldHashSet that$3, final int level$3, final Object x$2) {
         return that$3.get0(x$2, $this.hash(), level$3);
      }

      public OldHashSetCollision1(final int hash, final ListSet ks) {
         this.hash = hash;
         this.ks = ks;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static final class HashTrieSet extends OldHashSet {
      private static final long serialVersionUID = -2139837478000879198L;
      private final int bitmap;
      private final OldHashSet[] elems;
      private final int size0;

      private int bitmap() {
         return this.bitmap;
      }

      public OldHashSet[] elems() {
         return this.elems;
      }

      private int size0() {
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

      public Iterator iterator() {
         return new TrieIterator() {
            public final Object getElem(final Object cc) {
               return ((OldHashSet1)cc).key();
            }
         };
      }

      public void foreach(final Function1 f) {
         for(int i = 0; i < this.elems().length; ++i) {
            this.elems()[i].foreach(f);
         }

      }

      public boolean get0(final Object key, final int hash, final int level) {
         int index = hash >>> level & 31;
         int mask = 1 << index;
         if (this.bitmap() == -1) {
            return this.elems()[index & 31].get0(key, hash, level + 5);
         } else if ((this.bitmap() & mask) != 0) {
            int offset = Integer.bitCount(this.bitmap() & mask - 1);
            return this.elems()[offset].get0(key, hash, level + 5);
         } else {
            return false;
         }
      }

      public OldHashSet updated0(final Object key, final int hash, final int level) {
         int index = hash >>> level & 31;
         int mask = 1 << index;
         int offset = Integer.bitCount(this.bitmap() & mask - 1);
         if ((this.bitmap() & mask) != 0) {
            OldHashSet sub = this.elems()[offset];
            OldHashSet subNew = sub.updated0(key, hash, level + 5);
            if (sub == subNew) {
               return this;
            } else {
               OldHashSet[] elemsNew = (OldHashSet[])Arrays.copyOf((Object[])this.elems(), this.elems().length);
               elemsNew[offset] = subNew;
               return new HashTrieSet(this.bitmap(), elemsNew, this.size() + (subNew.size() - sub.size()));
            }
         } else {
            OldHashSet[] elemsNew = new OldHashSet[this.elems().length + 1];
            scala.Array..MODULE$.copy(this.elems(), 0, elemsNew, 0, offset);
            elemsNew[offset] = new OldHashSet1(key, hash);
            scala.Array..MODULE$.copy(this.elems(), offset, elemsNew, offset + 1, this.elems().length - offset);
            int bitmapNew = this.bitmap() | mask;
            return new HashTrieSet(bitmapNew, elemsNew, this.size() + 1);
         }
      }

      public OldHashSet removed0(final Object key, final int hash, final int level) {
         int index = hash >>> level & 31;
         int mask = 1 << index;
         int offset = Integer.bitCount(this.bitmap() & mask - 1);
         if ((this.bitmap() & mask) != 0) {
            OldHashSet sub = this.elems()[offset];
            OldHashSet subNew = sub.removed0(key, hash, level + 5);
            if (sub == subNew) {
               return this;
            } else if (subNew == null) {
               int bitmapNew = this.bitmap() ^ mask;
               if (bitmapNew != 0) {
                  OldHashSet[] elemsNew = new OldHashSet[this.elems().length - 1];
                  scala.Array..MODULE$.copy(this.elems(), 0, elemsNew, 0, offset);
                  scala.Array..MODULE$.copy(this.elems(), offset + 1, elemsNew, offset, this.elems().length - offset - 1);
                  int sizeNew = this.size() - sub.size();
                  return (OldHashSet)(elemsNew.length == 1 && !(elemsNew[0] instanceof HashTrieSet) ? elemsNew[0] : new HashTrieSet(bitmapNew, elemsNew, sizeNew));
               } else {
                  return null;
               }
            } else if (this.elems().length == 1 && !(subNew instanceof HashTrieSet)) {
               return subNew;
            } else {
               OldHashSet[] elemsNew = (OldHashSet[])Arrays.copyOf((Object[])this.elems(), this.elems().length);
               elemsNew[offset] = subNew;
               int sizeNew = this.size() + (subNew.size() - sub.size());
               return new HashTrieSet(this.bitmap(), elemsNew, sizeNew);
            }
         } else {
            return this;
         }
      }

      public OldHashSet union0(final LeafOldHashSet that, final int level) {
         int index = that.hash() >>> level & 31;
         int mask = 1 << index;
         int offset = Integer.bitCount(this.bitmap() & mask - 1);
         if ((this.bitmap() & mask) != 0) {
            OldHashSet sub = this.elems()[offset];
            OldHashSet sub1 = sub.union0(that, level + 5);
            if (sub == sub1) {
               return this;
            } else {
               OldHashSet[] elems1 = new OldHashSet[this.elems().length];
               scala.Array..MODULE$.copy(this.elems(), 0, elems1, 0, this.elems().length);
               elems1[offset] = sub1;
               return new HashTrieSet(this.bitmap(), elems1, this.size() + (sub1.size() - sub.size()));
            }
         } else {
            OldHashSet[] elems1 = new OldHashSet[this.elems().length + 1];
            scala.Array..MODULE$.copy(this.elems(), 0, elems1, 0, offset);
            elems1[offset] = that;
            scala.Array..MODULE$.copy(this.elems(), offset, elems1, offset + 1, this.elems().length - offset);
            int bitmap1 = this.bitmap() | mask;
            return new HashTrieSet(bitmap1, elems1, this.size() + that.size());
         }
      }

      public OldHashSet union0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         if (that == this) {
            return this;
         } else if (that instanceof LeafOldHashSet) {
            LeafOldHashSet var7 = (LeafOldHashSet)that;
            return this.union0(var7, level);
         } else if (that instanceof HashTrieSet) {
            HashTrieSet var8 = (HashTrieSet)that;
            OldHashSet[] a = this.elems();
            int abm = this.bitmap();
            int ai = 0;
            OldHashSet[] b = var8.elems();
            int bbm = var8.bitmap();
            int bi = 0;
            int offset = offset0;
            int rs = 0;

            while((abm | bbm) != 0) {
               int alsb = abm ^ abm & abm - 1;
               int blsb = bbm ^ bbm & bbm - 1;
               if (alsb == blsb) {
                  OldHashSet sub1 = a[ai].union0(b[bi], level + 5, buffer, offset);
                  rs += sub1.size();
                  buffer[offset] = sub1;
                  ++offset;
                  abm &= ~alsb;
                  ++ai;
                  bbm &= ~blsb;
                  ++bi;
               } else if (scala.collection.generic.BitOperations.Int..MODULE$.unsignedCompare(alsb - 1, blsb - 1)) {
                  OldHashSet sub1 = a[ai];
                  rs += sub1.size();
                  buffer[offset] = sub1;
                  ++offset;
                  abm &= ~alsb;
                  ++ai;
               } else {
                  OldHashSet sub1 = b[bi];
                  rs += sub1.size();
                  buffer[offset] = sub1;
                  ++offset;
                  bbm &= ~blsb;
                  ++bi;
               }
            }

            if (rs == this.size()) {
               return this;
            } else if (rs == var8.size()) {
               return var8;
            } else {
               int length = offset - offset0;
               OldHashSet[] elems = new OldHashSet[length];
               System.arraycopy(buffer, offset0, elems, 0, length);
               return new HashTrieSet(this.bitmap() | var8.bitmap(), elems, rs);
            }
         } else {
            return this;
         }
      }

      public OldHashSet intersect0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         if (that == this) {
            return this;
         } else if (that instanceof LeafOldHashSet) {
            LeafOldHashSet var7 = (LeafOldHashSet)that;
            return var7.intersect0(this, level, buffer, offset0);
         } else if (that instanceof HashTrieSet) {
            HashTrieSet var8 = (HashTrieSet)that;
            OldHashSet[] a = this.elems();
            int abm = this.bitmap();
            int ai = 0;
            OldHashSet[] b = var8.elems();
            int bbm = var8.bitmap();
            int bi = 0;
            if ((abm & bbm) == 0) {
               return null;
            } else {
               int offset = offset0;
               int rs = 0;
               int rbm = 0;

               while((abm & bbm) != 0) {
                  int alsb = abm ^ abm & abm - 1;
                  int blsb = bbm ^ bbm & bbm - 1;
                  if (alsb == blsb) {
                     OldHashSet sub1 = a[ai].intersect0(b[bi], level + 5, buffer, offset);
                     if (sub1 != null) {
                        rs += sub1.size();
                        rbm |= alsb;
                        buffer[offset] = sub1;
                        ++offset;
                     }

                     abm &= ~alsb;
                     ++ai;
                     bbm &= ~blsb;
                     ++bi;
                  } else if (scala.collection.generic.BitOperations.Int..MODULE$.unsignedCompare(alsb - 1, blsb - 1)) {
                     abm &= ~alsb;
                     ++ai;
                  } else {
                     bbm &= ~blsb;
                     ++bi;
                  }
               }

               if (rbm == 0) {
                  return null;
               } else if (rs == this.size0()) {
                  return this;
               } else if (rs == var8.size0()) {
                  return var8;
               } else {
                  int length = offset - offset0;
                  if (length == 1 && !(buffer[offset0] instanceof HashTrieSet)) {
                     return buffer[offset0];
                  } else {
                     OldHashSet[] elems = new OldHashSet[length];
                     System.arraycopy(buffer, offset0, elems, 0, length);
                     return new HashTrieSet(rbm, elems, rs);
                  }
               }
            }
         } else {
            return null;
         }
      }

      public OldHashSet diff0(final OldHashSet that, final int level, final OldHashSet[] buffer, final int offset0) {
         if (that == this) {
            return null;
         } else if (that instanceof OldHashSet1) {
            OldHashSet1 var7 = (OldHashSet1)that;
            return this.removed0(var7.key(), var7.hash(), level);
         } else if (that instanceof HashTrieSet) {
            HashTrieSet var8 = (HashTrieSet)that;
            OldHashSet[] a = this.elems();
            int abm = this.bitmap();
            int ai = 0;
            OldHashSet[] b = var8.elems();
            int bbm = var8.bitmap();
            int bi = 0;
            int offset = offset0;
            int rs = 0;
            int rbm = 0;

            while(abm != 0) {
               int alsb = abm ^ abm & abm - 1;
               int blsb = bbm ^ bbm & bbm - 1;
               if (alsb == blsb) {
                  OldHashSet sub1 = a[ai].diff0(b[bi], level + 5, buffer, offset);
                  if (sub1 != null) {
                     rs += sub1.size();
                     rbm |= alsb;
                     buffer[offset] = sub1;
                     ++offset;
                  }

                  abm &= ~alsb;
                  ++ai;
                  bbm &= ~blsb;
                  ++bi;
               } else if (scala.collection.generic.BitOperations.Int..MODULE$.unsignedCompare(alsb - 1, blsb - 1)) {
                  OldHashSet sub1 = a[ai];
                  rs += sub1.size();
                  rbm |= alsb;
                  buffer[offset] = sub1;
                  ++offset;
                  abm &= ~alsb;
                  ++ai;
               } else {
                  bbm &= ~blsb;
                  ++bi;
               }
            }

            if (rbm == 0) {
               return null;
            } else if (rs == this.size0()) {
               return this;
            } else {
               int length = offset - offset0;
               if (length == 1 && !(buffer[offset0] instanceof HashTrieSet)) {
                  return buffer[offset0];
               } else {
                  OldHashSet[] elems = new OldHashSet[length];
                  System.arraycopy(buffer, offset0, elems, 0, length);
                  return new HashTrieSet(rbm, elems, rs);
               }
            }
         } else if (that instanceof OldHashSetCollision1) {
            OldHashSetCollision1 var24 = (OldHashSetCollision1)that;
            return this.removeAll$1(this, var24.ks(), var24, level);
         } else {
            return this;
         }
      }

      public boolean subsetOf0(final OldHashSet that, final int level) {
         if (that == this) {
            return true;
         } else {
            if (that instanceof HashTrieSet) {
               HashTrieSet var5 = (HashTrieSet)that;
               if (this.size0() <= var5.size0()) {
                  int abm = this.bitmap();
                  OldHashSet[] a = this.elems();
                  int ai = 0;
                  OldHashSet[] b = var5.elems();
                  int bbm = var5.bitmap();
                  int bi = 0;
                  if ((abm & bbm) == abm) {
                     while(abm != 0) {
                        int alsb = abm ^ abm & abm - 1;
                        int blsb = bbm ^ bbm & bbm - 1;
                        if (alsb == blsb) {
                           if (!a[ai].subsetOf0(b[bi], level + 5)) {
                              return false;
                           }

                           abm &= ~alsb;
                           ++ai;
                        }

                        bbm &= ~blsb;
                        ++bi;
                     }

                     return true;
                  }

                  return false;
               }
            }

            return false;
         }
      }

      public OldHashSet filter0(final Function1 p, final boolean negate, final int level, final OldHashSet[] buffer, final int offset0) {
         int offset = offset0;
         int rs = 0;
         int kept = 0;

         for(int i = 0; i < this.elems().length; ++i) {
            OldHashSet result = this.elems()[i].filter0(p, negate, level + 5, buffer, offset);
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
         } else if (offset == offset0 + 1 && !(buffer[offset0] instanceof HashTrieSet)) {
            return buffer[offset0];
         } else {
            int length = offset - offset0;
            OldHashSet[] elems1 = new OldHashSet[length];
            System.arraycopy(buffer, offset0, elems1, 0, length);
            int bitmap1 = length == this.elems().length ? this.bitmap() : .MODULE$.keepBits(this.bitmap(), kept);
            return new HashTrieSet(bitmap1, elems1, rs);
         }
      }

      private final OldHashSet removeAll$1(final OldHashSet s, final ListSet r, final OldHashSetCollision1 x4$1, final int level$4) {
         while(!r.isEmpty() && s != null) {
            OldHashSet var10000 = s.removed0(r.head(), x4$1.hash(), level$4);
            r = (ListSet)r.tail();
            s = var10000;
         }

         return s;
      }

      public HashTrieSet(final int bitmap, final OldHashSet[] elems, final int size0) {
         this.bitmap = bitmap;
         this.elems = elems;
         this.size0 = size0;
         scala.Predef..MODULE$.assert(Integer.bitCount(bitmap) == elems.length);
      }
   }
}
