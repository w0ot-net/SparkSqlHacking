package scala;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import scala.collection.ArrayOps$;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SortedIterableFactory;
import scala.collection.SortedOps;
import scala.collection.SortedSetFactoryDefaults;
import scala.collection.SortedSetOps;
import scala.collection.SpecificIterableFactory;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.StringOps$;
import scala.collection.View;
import scala.collection.immutable.AbstractSet;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.BitSet$;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.SetOps;
import scala.collection.immutable.SortedSet;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.BitSet;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Growable;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.math.Ordered;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;
import scala.util.matching.Regex$;

@ScalaSignature(
   bytes = "\u0006\u0005\rUg!B/_\u0003\u0003\t\u0007\u0002C7\u0001\u0005\u0003\u0005\u000b\u0011\u00028\t\u000bE\u0004A\u0011\u0001:\t\u000bE\u0004A\u0011A;\t\u000bY\u0004A\u0011C<\t\u000ba\u0004A\u0011I=\t\u0013\u0005-\u0001A1A\u0005\n\u00055\u0001\u0002\u0003Bk\u0001\u0001\u0006I!a\u0004\t\u0013\t]\u0007\u00011A\u0005\n\u0005E\u0006\"\u0003Bm\u0001\u0001\u0007I\u0011\u0002Bn\u0011!\u00119\u000f\u0001Q!\n\u0005e\u0003\"\u0003Bv\u0001\u0001\u0007I\u0011BA\\\u0011%\u0011i\u000f\u0001a\u0001\n\u0013\u0011y\u000f\u0003\u0005\u0003t\u0002\u0001\u000b\u0015BA!\u0011!\u0011y\u0010\u0001Q\u0001\n\r\u0005\u0001bBB\u0002\u0001\u0011\u0005\u0011\u0011\u0017\u0005\n\u0007\u000b\u0001\u0001\u0019!C\t\u0003cA\u0011ba\u0002\u0001\u0001\u0004%\tb!\u0003\t\u000f\r5\u0001\u0001)Q\u0005]\"Y1q\u0002\u0001A\u0002\u0003\u0007I\u0011CB\t\u0011-\u0019)\u0002\u0001a\u0001\u0002\u0004%\tba\u0006\t\u0017\rm\u0001\u00011A\u0001B\u0003&11\u0003\u0005\b\u0007;\u0001A\u0011BAr\u0011\u001d\u0019y\u0002\u0001Q!\n9Dqa!\t\u0001A\u0003&a\u000eC\u0004\u0004$\u0001!)!!\r\t\u000f\r\u0015\u0002\u0001\"\u0002\u0004(!91Q\u0006\u0001\u0005\u0006\r=\u0002bBB\u001b\u0001\u0011U1q\u0007\u0005\b\u0007k\u0001AQCB\u001d\u0011\u001d\u0019)\u0004\u0001C\u000b\u0007\u007fAqa!\u000e\u0001\t+\u0019)\u0005C\u0004\u0004L\u0001!Ia!\u0014\t\u000f\r=\u0003\u0001\"\u0003\u0004R\u00199\u00111\u0005\u0001\u0002\u0002\u0005\u0015\u0002BB9#\t\u0003\ti\u0003C\u0004\u00020\t2\t!!\r\t\u0015\u0005M\"E!A\u0001B\u0003%1\u000fC\u0004\u00026\t\"\t%a\u000e\t\u000f\u0005u\"\u0005\"\u0011\u0002@!9\u0011\u0011\u000b\u0012\u0005B\u0005M\u0003bBA+E\u0011\u0005\u0011q\u000b\u0005\r\u0005\u001b\u0014#\u0011!b\u0001\n\u0003\u0001!q\u001a\u0004\u0007\u0007+\u0002\u0001ba\u0016\t\u0013\ru2F!A!\u0002\u0013q\u0007\"CB\"W\t\u0005\t\u0015!\u0003{\u0011\u0019\t8\u0006\"\u0001\u0004Z!1\u0011o\u000bC\u0001\u0007CBa!]\u0016\u0005\u0002\r\u0015\u0004BB9,\t\u0003\u0019I\u0007C\u0004\u00020-\"\t!!\r\t\u000ba\\C\u0011I=\t\u000bY\\C\u0011C<\b\u000f\r=\u0004\u0001c\u0001\u0004r\u0019911\u000f\u0001\t\u0002\rU\u0004BB97\t\u0003\u00199\tC\u0004\u00026Y\"\ta!#\u0007\r\u0005m\u0003\u0001AA/\u0011)\t9)\u000fB\u0001B\u0003&\u0011\u0011\u0012\u0005\bcf\"\t!OAH\u0011\u001d\t\u0019*\u000fC\u0002\u0003+Cq!!(:\t\u0003\ty\nC\u0004\u00020f\"\t%!-\t\u000f\u0005M\u0016\b\"\u0011\u00022!9\u0011QW\u001d\u0005B\u0005]\u0006bBA]s\u0011\u0005\u00111\u0018\u0005\b\u0003\u0003LD\u0011AAb\u0011\u001d\tI-\u000fC\u0001\u0003\u0017Dq!a4:\t\u0003\t\t\u000eC\u0004\u0002Zf\"\t%a7\t\u000f\u0005\u0005\u0018\b\"\u0011\u0002d\"9\u0011Q]\u001d\u0005\u0002\u0005\u001d\bbBA{s\u0011E\u0013q\u001f\u0005\b\u0005\u0007ID\u0011\u000bB\u0003\u0011\u001d\u0011i!\u000fC\u0001\u0005\u001fAqAa\u0007:\t\u0003\u0011i\u0002C\u0004\u0003\u000ee\"\tEa\t\t\u000f\tm\u0011\b\"\u0011\u0003Z!9!\u0011O\u001d\u0005B\tM\u0004b\u0002BJs\u0011\u0005#Q\u0013\u0005\r\u0005cK$\u0011#b\u0001\n\u0003\u0001!1W\u0004\b\u0007#\u0003\u0001\u0012ABJ\r\u001d\tY\u0006\u0001E\u0001\u0007+Ca!\u001d*\u0005\u0002\r\u001d\u0006\"CBU%\n\u0007IQBBV\u0011!\u0019yK\u0015Q\u0001\u000e\r5\u0006\"CBY%\n\u0007IQBBZ\u0011!\u00199L\u0015Q\u0001\u000e\rU\u0006\"CAX%\n\u0007I\u0011AAY\u0011!\u0019IL\u0015Q\u0001\n\u0005e\u0003bBB^%\u0012\u00051Q\u0018\u0005\b\u0007\u0007\u0014F\u0011\u0001B\u0003\u0011\u001d\t)P\u0015C\u0001\u0007\u000b\u00141\"\u00128v[\u0016\u0014\u0018\r^5p]*\tq,A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0007\u0001\u0011g\r\u0005\u0002dI6\ta,\u0003\u0002f=\n1\u0011I\\=SK\u001a\u0004\"a\u001a6\u000f\u0005\rD\u0017BA5_\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u001b7\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005%t\u0016aB5oSRL\u0017\r\u001c\t\u0003G>L!\u0001\u001d0\u0003\u0007%sG/\u0001\u0004=S:LGO\u0010\u000b\u0003gR\u0004\"a\u0019\u0001\t\u000b5\u0014\u0001\u0019\u00018\u0015\u0003M\f1B]3bIJ+7o\u001c7wKR\t!-\u0001\u0005u_N#(/\u001b8h)\u0005Q\bcA>\u0002\u00069\u0019A0!\u0001\u0011\u0005utV\"\u0001@\u000b\u0005}\u0004\u0017A\u0002\u001fs_>$h(C\u0002\u0002\u0004y\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\u0004\u0003\u0013\u0011aa\u0015;sS:<'bAA\u0002=\u0006!a/\\1q+\t\ty\u0001E\u0004\u0002\u0012\u0005ma.a\b\u000e\u0005\u0005M!\u0002BA\u000b\u0003/\tq!\\;uC\ndWMC\u0002\u0002\u001ay\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\ti\"a\u0005\u0003\u00075\u000b\u0007\u000fE\u0002\u0002\"\tj\u0011\u0001\u0001\u0002\u0006-\u0006dW/Z\n\u0006E\t\f9C\u001a\t\u0006O\u0006%\u0012qD\u0005\u0004\u0003Wa'aB(sI\u0016\u0014X\r\u001a\u000b\u0003\u0003?\t!!\u001b3\u0016\u00039\fQd]2bY\u0006$SI\\;nKJ\fG/[8oI\u0011zW\u000f^3s\u000b:,X\u000eI\u0001\bG>l\u0007/\u0019:f)\rq\u0017\u0011\b\u0005\b\u0003w1\u0003\u0019AA\u0010\u0003\u0011!\b.\u0019;\u0002\r\u0015\fX/\u00197t)\u0011\t\t%a\u0012\u0011\u0007\r\f\u0019%C\u0002\u0002Fy\u0013qAQ8pY\u0016\fg\u000eC\u0004\u0002J\u001d\u0002\r!a\u0013\u0002\u000b=$\b.\u001a:\u0011\u0007\r\fi%C\u0002\u0002Py\u00131!\u00118z\u0003!A\u0017m\u001d5D_\u0012,G#\u00018\u0002\u000b\u0011\u0002H.^:\u0015\t\u0005e#1\u001a\t\u0004\u0003CI$\u0001\u0003,bYV,7+\u001a;\u0014\u0015e\ny&a\u001b\u0002r\u0005ed\r\u0005\u0004\u0002b\u0005\u001d\u0014qD\u0007\u0003\u0003GRA!!\u001a\u0002\u0018\u0005I\u0011.\\7vi\u0006\u0014G.Z\u0005\u0005\u0003S\n\u0019GA\u0006BEN$(/Y2u'\u0016$\bCBA1\u0003[\ny\"\u0003\u0003\u0002p\u0005\r$!C*peR,GmU3u!)\t\t'a\u001d\u0002 \u0005]\u0014\u0011L\u0005\u0005\u0003k\n\u0019G\u0001\u0007T_J$X\rZ*fi>\u00038\u000f\u0005\u0003\u0002b\u00055\u0004CCA>\u0003{\ny\"!!\u0002Z5\u0011\u0011qC\u0005\u0005\u0003\u007f\n9B\u0001\u000eTiJL7\r^(qi&l\u0017N_3e\u0013R,'/\u00192mK>\u00038\u000f\u0005\u0003\u0002b\u0005\r\u0015\u0002BAC\u0003G\u00121aU3u\u0003\u0015qg.\u00133t!\u0011\t\t'a#\n\t\u00055\u00151\r\u0002\u0007\u0005&$8+\u001a;\u0015\t\u0005e\u0013\u0011\u0013\u0005\b\u0003\u000f[\u0004\u0019AAE\u0003!y'\u000fZ3sS:<WCAAL!\u00159\u0017\u0011TA\u0010\u0013\r\tY\n\u001c\u0002\t\u001fJ$WM]5oO\u0006I!/\u00198hK&k\u0007\u000f\u001c\u000b\u0007\u00033\n\t+a+\t\u000f\u0005\rV\b1\u0001\u0002&\u0006!aM]8n!\u0015\u0019\u0017qUA\u0010\u0013\r\tIK\u0018\u0002\u0007\u001fB$\u0018n\u001c8\t\u000f\u00055V\b1\u0001\u0002&\u0006)QO\u001c;jY\u0006)Q-\u001c9usV\u0011\u0011\u0011L\u0001\nW:|wO\\*ju\u0016\fq![:F[B$\u00180\u0006\u0002\u0002B\u0005A1m\u001c8uC&t7\u000f\u0006\u0003\u0002B\u0005u\u0006bBA`\u0003\u0002\u0007\u0011qD\u0001\u0002m\u0006!\u0011N\\2m)\u0011\tI&!2\t\u000f\u0005\u001d'\t1\u0001\u0002 \u0005)a/\u00197vK\u0006!Q\r_2m)\u0011\tI&!4\t\u000f\u0005\u001d7\t1\u0001\u0002 \u0005A\u0011\u000e^3sCR|'/\u0006\u0002\u0002TB)q-!6\u0002 %\u0019\u0011q\u001b7\u0003\u0011%#XM]1u_J\fA\"\u001b;fe\u0006$xN\u001d$s_6$B!a5\u0002^\"9\u0011q\\#A\u0002\u0005}\u0011!B:uCJ$\u0018!C2mCN\u001ch*Y7f+\u0005Q\u0018!\u0003;p\u0005&$X*Y:l+\t\tI\u000fE\u0003d\u0003W\fy/C\u0002\u0002nz\u0013Q!\u0011:sCf\u00042aYAy\u0013\r\t\u0019P\u0018\u0002\u0005\u0019>tw-\u0001\u0007ge>l7\u000b]3dS\u001aL7\r\u0006\u0003\u0002Z\u0005e\bbBA~\u0011\u0002\u0007\u0011Q`\u0001\u0005G>dG\u000eE\u0003h\u0003\u007f\fy\"C\u0002\u0003\u00021\u0014A\"\u0013;fe\u0006\u0014G.Z(oG\u0016\f!C\\3x'B,7-\u001b4jG\n+\u0018\u000e\u001c3feV\u0011!q\u0001\t\t\u0003#\u0011I!a\b\u0002Z%!!1BA\n\u0005\u001d\u0011U/\u001b7eKJ\f1!\\1q)\u0011\tIF!\u0005\t\u000f\tM!\n1\u0001\u0003\u0016\u0005\ta\rE\u0004d\u0005/\ty\"a\b\n\u0007\teaLA\u0005Gk:\u001cG/[8oc\u00059a\r\\1u\u001b\u0006\u0004H\u0003BA-\u0005?AqAa\u0005L\u0001\u0004\u0011\t\u0003E\u0004d\u0005/\ty\"!@\u0016\t\t\u0015\"q\u0006\u000b\u0005\u0005O\u0011)\u0006\u0006\u0003\u0003*\tm\u0002CBA1\u0003[\u0012Y\u0003\u0005\u0003\u0003.\t=B\u0002\u0001\u0003\b\u0005ca%\u0019\u0001B\u001a\u0005\u0005\u0011\u0015\u0003\u0002B\u001b\u0003\u0017\u00022a\u0019B\u001c\u0013\r\u0011ID\u0018\u0002\b\u001d>$\b.\u001b8h\u0011\u001d\u0011i\u0004\u0014a\u0002\u0005\u007f\t!!\u001a<\u0011\u000b\u001d\fIJa\u000b)\u0011\tm\"1\tB(\u0005#\u0002BA!\u0012\u0003L5\u0011!q\t\u0006\u0004\u0005\u0013r\u0016AC1o]>$\u0018\r^5p]&!!Q\nB$\u0005AIW\u000e\u001d7jG&$hj\u001c;G_VtG-A\u0002ng\u001e\f#Aa\u0015\u0002\u0003\u0003qu\u000eI5na2L7-\u001b;!\u001fJ$WM]5oOn#3PQ?^A\u0019|WO\u001c3!i>\u0004#-^5mI\u0002\n\u0007eU8si\u0016$7+\u001a;\\Im\u0014U0\u0018\u0018!3>,\b%\\1zA]\fg\u000e\u001e\u0011u_\u0002*\boY1ti\u0002\"x\u000eI1!'\u0016$8LV1mk\u0016l\u0006EZ5sgR\u0004#-\u001f\u0011dC2d\u0017N\\4!AVt7o\u001c:uK\u0012\u0004g\u0006C\u0004\u0003\u00141\u0003\rAa\u0016\u0011\u000f\r\u00149\"a\b\u0003,U!!1\fB2)\u0011\u0011iFa\u001b\u0015\t\t}#Q\r\t\u0007\u0003C\niG!\u0019\u0011\t\t5\"1\r\u0003\b\u0005ci%\u0019\u0001B\u001a\u0011\u001d\u0011i$\u0014a\u0002\u0005O\u0002RaZAM\u0005CB\u0003B!\u001a\u0003D\t=#\u0011\u000b\u0005\b\u0005'i\u0005\u0019\u0001B7!\u001d\u0019'qCA\u0010\u0005_\u0002RaZA\u0000\u0005C\n1A_5q+\u0011\u0011)Ha!\u0015\t\t]$q\u0012\u000b\u0005\u0005s\u0012)\t\u0005\u0004\u0002b\u00055$1\u0010\t\bG\nu\u0014q\u0004BA\u0013\r\u0011yH\u0018\u0002\u0007)V\u0004H.\u001a\u001a\u0011\t\t5\"1\u0011\u0003\b\u0005cq%\u0019\u0001B\u001a\u0011\u001d\u0011iD\u0014a\u0002\u0005\u000f\u0003RaZAM\u0005wB\u0003B!\"\u0003D\t=#1R\u0011\u0003\u0005\u001b\u000b\u00111\u0003(pA%l\u0007\u000f\\5dSR\u0004sJ\u001d3fe&twm\u0017\u0013|\u0005vl\u0006EZ8v]\u0012\u0004Co\u001c\u0011ck&dG\rI1!'>\u0014H/\u001a3TKR\\\u0006FV1mk\u0016d\u0003\u0005J>C{&jf\u0006I-pk\u0002j\u0017-\u001f\u0011xC:$\b\u0005^8!kB\u001c\u0017m\u001d;!i>\u0004\u0013\rI*fin3\u0016\r\\;f;\u00022\u0017N]:uA\tL\beY1mY&tw\r\t1v]N|'\u000f^3eA:Bq!a\u000fO\u0001\u0004\u0011\t\nE\u0003h\u0003\u007f\u0014\t)A\u0004d_2dWm\u0019;\u0016\t\t]%q\u0014\u000b\u0005\u00053\u00139\u000b\u0006\u0003\u0003\u001c\n\u0005\u0006CBA1\u0003[\u0012i\n\u0005\u0003\u0003.\t}Ea\u0002B\u0019\u001f\n\u0007!1\u0007\u0005\b\u0005{y\u00059\u0001BR!\u00159\u0017\u0011\u0014BOQ!\u0011\tKa\u0011\u0003P\tE\u0003b\u0002BU\u001f\u0002\u0007!1V\u0001\u0003a\u001a\u0004ra\u0019BW\u0003?\u0011i*C\u0002\u00030z\u0013q\u0002U1si&\fGNR;oGRLwN\\\u0001\u001ag\u000e\fG.\u0019\u0013F]VlWM]1uS>tG\u0005\n2z\u001d\u0006lW-\u0006\u0002\u00036B11Pa.{\u0003?IA!!\b\u0002\n!\u001a\u0001Ka/\u0011\u0007\r\u0014i,C\u0002\u0003@z\u0013\u0011\u0002\u001e:b]NLWM\u001c;)\u000fe\u0012\u0019-a2\u0003JB\u00191M!2\n\u0007\t\u001dgL\u0001\tTKJL\u0017\r\u001c,feNLwN\\+J\tzAA\rVz(\u0002a{%\u0013C\u0004\u0002@&\u0002\r!a\b\u00029M\u001c\u0017\r\\1%\u000b:,X.\u001a:bi&|g\u000e\n\u0013pkR,'/\u00128v[V\t1\u000fK\u0004#\u0005\u0007\f9Ma5\u001f\u0011\tLGpLw\";E\u000bQA^7ba\u0002\nAA^:fi\u0006Aao]3u?\u0012*\u0017\u000f\u0006\u0003\u0003^\n\r\bcA2\u0003`&\u0019!\u0011\u001d0\u0003\tUs\u0017\u000e\u001e\u0005\n\u0005KL\u0011\u0011!a\u0001\u00033\n1\u0001\u001f\u00132\u0003\u001518/\u001a;!Q\rQ!1X\u0001\fmN,G\u000fR3gS:,G-A\bwg\u0016$H)\u001a4j]\u0016$w\fJ3r)\u0011\u0011iN!=\t\u0013\t\u0015H\"!AA\u0002\u0005\u0005\u0013\u0001\u0004<tKR$UMZ5oK\u0012\u0004\u0003fA\u0007\u0003<\"\u001aQB!?\u0011\u0007\r\u0014Y0C\u0002\u0003~z\u0013\u0001B^8mCRLG.Z\u0001\u0005]6\f\u0007\u000f\u0005\u0004\u0002\u0012\u0005maN_\u0001\u0007m\u0006dW/Z:\u0002\r9,\u0007\u0010^%e\u0003)qW\r\u001f;JI~#S-\u001d\u000b\u0005\u0005;\u001cY\u0001\u0003\u0005\u0003fF\t\t\u00111\u0001o\u0003\u001dqW\r\u001f;JI\u0002\n\u0001B\\3yi:\u000bW.Z\u000b\u0003\u0007'\u0001BaZAku\u0006aa.\u001a=u\u001d\u0006lWm\u0018\u0013fcR!!Q\\B\r\u0011%\u0011)\u000fFA\u0001\u0002\u0004\u0019\u0019\"A\u0005oKb$h*Y7fA\u0005qa.\u001a=u\u001d\u0006lWm\u0014:Ok2d\u0017!\u0002;pa&#\u0017\u0001\u00032piR|W.\u00133\u0002\u000b5\f\u00070\u00133\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005}1\u0011\u0006\u0005\u0007\u0007WQ\u0002\u0019\u00018\u0002\u0003a\f\u0001b^5uQ:\u000bW.\u001a\u000b\u0005\u0003?\u0019\t\u0004\u0003\u0004\u00044m\u0001\rA_\u0001\u0002g\u0006)a+\u00197vKV\u0011\u0011q\u0004\u000b\u0005\u0003?\u0019Y\u0004\u0003\u0004\u0004>u\u0001\rA\\\u0001\u0002SR!\u0011qDB!\u0011\u0019\u0019\u0019E\ba\u0001u\u0006!a.Y7f)\u0019\tyba\u0012\u0004J!11QH\u0010A\u00029Daaa\u0011 \u0001\u0004Q\u0018a\u00049paVd\u0017\r^3OC6,W*\u00199\u0015\u0005\tu\u0017A\u00028b[\u0016|e\rF\u0002{\u0007'Baa!\u0010\"\u0001\u0004q'a\u0001,bYN!1&a\bg)\u0019\u0019Yf!\u0018\u0004`A\u0019\u0011\u0011E\u0016\t\r\rub\u00061\u0001o\u0011\u0019\u0019\u0019E\fa\u0001uR!11LB2\u0011\u0019\u0019id\fa\u0001]R!11LB4\u0011\u0019\u0019\u0019\u0005\ra\u0001uR\u001111\f\u0015\bW\t\r\u0017qYB7=!y\u001dnZXJz8\u0000\u0015!\u0004,bYV,wJ\u001d3fe&tw\rE\u0002\u0002\"Y\u0012QBV1mk\u0016|%\u000fZ3sS:<7#\u0002\u001c\u0004x\u0005]\u0005\u0003BB=\u0007\u0007k!aa\u001f\u000b\t\ru4qP\u0001\u0005Y\u0006twM\u0003\u0002\u0004\u0002\u0006!!.\u0019<b\u0013\u0011\u0019)ia\u001f\u0003\r=\u0013'.Z2u)\t\u0019\t\bF\u0003o\u0007\u0017\u001bi\tC\u0004\u0004,a\u0002\r!a\b\t\u000f\r=\u0005\b1\u0001\u0002 \u0005\t\u00110\u0001\u0005WC2,XmU3u!\r\t\tCU\n\u0007%\n\u001c9j!(\u0011\u0011\u0005m4\u0011TA\u0010\u00033JAaa'\u0002\u0018\t92\u000b]3dS\u001aL7-\u0013;fe\u0006\u0014G.\u001a$bGR|'/\u001f\t\u0005\u0007?\u001b)+\u0004\u0002\u0004\"*!11UB@\u0003\tIw.C\u0002l\u0007C#\"aa%\u0002\r=\u0014H-T:h+\t\u0019ik\u0004\u0002\u0003R\u00059qN\u001d3Ng\u001e\u0004\u0013!\u0003>ja>\u0013H-T:h+\t\u0019)l\u0004\u0002\u0003\f\u0006Q!0\u001b9Pe\u0012l5o\u001a\u0011\u0002\r\u0015l\u0007\u000f^=!\u0003-1'o\\7CSRl\u0015m]6\u0015\t\u0005e3q\u0018\u0005\b\u0007\u0003T\u0006\u0019AAu\u0003\u0015)G.Z7t\u0003)qWm\u001e\"vS2$WM\u001d\u000b\u0005\u00033\u001a9\rC\u0004\u0004Jr\u0003\r!!@\u0002\u0005%$\bf\u0002*\u0003D\u0006\u001d7Q\u001a\u0010\u0002\u0007!:\u0011Ka1\u0002H\u000e5\u0007f\u0002\u0001\u0003D\u0006\u001d71\u001b\u0010\tk\u0002n]\u001c'\bZ\u001d\u0003"
)
public abstract class Enumeration implements Serializable {
   private static final long serialVersionUID = 8476000850333817230L;
   private volatile ValueOrdering$ ValueOrdering$module;
   private volatile ValueSet$ ValueSet$module;
   private final Map scala$Enumeration$$vmap;
   private transient ValueSet vset;
   private transient volatile boolean scala$Enumeration$$vsetDefined;
   private final Map nmap;
   private int nextId;
   private Iterator nextName;
   public int scala$Enumeration$$topId;
   public int scala$Enumeration$$bottomId;

   public ValueOrdering$ ValueOrdering() {
      if (this.ValueOrdering$module == null) {
         this.ValueOrdering$lzycompute$1();
      }

      return this.ValueOrdering$module;
   }

   public ValueSet$ ValueSet() {
      if (this.ValueSet$module == null) {
         this.ValueSet$lzycompute$1();
      }

      return this.ValueSet$module;
   }

   public Object readResolve() {
      return this.getClass().getField("MODULE$").get((Object)null);
   }

   public String toString() {
      ArrayOps$ var10000 = ArrayOps$.MODULE$;
      String var10001 = (String)ArrayOps$.MODULE$.last$extension(StringOps$.MODULE$.split$extension(StringOps$.MODULE$.stripSuffix$extension(this.getClass().getName(), "$"), '.'));
      Regex$ var10002 = Regex$.MODULE$;
      return (String)var10000.last$extension(var10001.split(Pattern.quote("$")));
   }

   public Map scala$Enumeration$$vmap() {
      return this.scala$Enumeration$$vmap;
   }

   private ValueSet vset() {
      return this.vset;
   }

   private void vset_$eq(final ValueSet x$1) {
      this.vset = x$1;
   }

   private boolean vsetDefined() {
      return this.scala$Enumeration$$vsetDefined;
   }

   public void scala$Enumeration$$vsetDefined_$eq(final boolean x$1) {
      this.scala$Enumeration$$vsetDefined = x$1;
   }

   public ValueSet values() {
      if (!this.vsetDefined()) {
         ValueSet$ var10001 = this.ValueSet();
         if (var10001 == null) {
            throw null;
         }

         ValueSet$ newBuilder_this = var10001;
         Builder var3 = new Builder() {
            private final BitSet b;
            // $FF: synthetic field
            private final ValueSet$ $outer;

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
            public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
               return Growable.$plus$eq$(this, elem1, elem2, elems);
            }

            public Growable addAll(final IterableOnce elems) {
               return Growable.addAll$(this, elems);
            }

            public final Growable $plus$plus$eq(final IterableOnce elems) {
               return Growable.$plus$plus$eq$(this, elems);
            }

            public int knownSize() {
               return Growable.knownSize$(this);
            }

            public <undefinedtype> addOne(final Value x) {
               BitSet var10000 = this.b;
               Integer $plus$eq_elem = x.id() - this.$outer.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
               if (var10000 == null) {
                  throw null;
               } else {
                  var10000.addOne($plus$eq_elem);
                  return this;
               }
            }

            public void clear() {
               this.b.clear();
            }

            public ValueSet result() {
               return this.$outer.scala$Enumeration$ValueSet$$$outer().new ValueSet(this.b.toImmutable());
            }

            public {
               if (Enumeration.this == null) {
                  throw null;
               } else {
                  this.$outer = Enumeration.this;
                  this.b = new BitSet();
               }
            }
         };
         newBuilder_this = null;
         this.vset_$eq((ValueSet)((Builder)Growable.addAll$(var3, this.scala$Enumeration$$vmap().values())).result());
         this.scala$Enumeration$$vsetDefined_$eq(true);
      }

      return this.vset();
   }

   public int nextId() {
      return this.nextId;
   }

   public void nextId_$eq(final int x$1) {
      this.nextId = x$1;
   }

   public Iterator nextName() {
      return this.nextName;
   }

   public void nextName_$eq(final Iterator x$1) {
      this.nextName = x$1;
   }

   public String scala$Enumeration$$nextNameOrNull() {
      return this.nextName() != null && this.nextName().hasNext() ? (String)this.nextName().next() : null;
   }

   public final int maxId() {
      return this.scala$Enumeration$$topId;
   }

   public final Value apply(final int x) {
      return (Value)this.scala$Enumeration$$vmap().apply(x);
   }

   public final Value withName(final String s) {
      return (Value)this.values().scala$Enumeration$$byName().getOrElse(s, () -> {
         throw new NoSuchElementException((new StringBuilder(21)).append("No value found for '").append(s).append("'").toString());
      });
   }

   public final Value Value() {
      return this.Value(this.nextId());
   }

   public final Value Value(final int i) {
      String Value_name = this.scala$Enumeration$$nextNameOrNull();
      return new Val(i, Value_name);
   }

   public final Value Value(final String name) {
      int Value_i = this.nextId();
      return new Val(Value_i, name);
   }

   public final Value Value(final int i, final String name) {
      return new Val(i, name);
   }

   private void populateNameMap() {
      Field[] fields;
      Object filter$extension_$this;
      Object var29;
      label135: {
         label138: {
            fields = this.getFields$1(this.getClass().getSuperclass(), this.getClass().getDeclaredFields());
            Object[] refArrayOps_xs = this.getClass().getMethods();
            Object var13 = null;
            filter$extension_$this = refArrayOps_xs;
            ArrayBuilder$ var19 = ArrayBuilder$.MODULE$;
            ClassTag filter$extension_make_evidence$1 = ClassTag$.MODULE$.apply(refArrayOps_xs.getClass().getComponentType());
            Class var8 = filter$extension_make_evidence$1.runtimeClass();
            Class var20 = java.lang.Byte.TYPE;
            if (var20 == null) {
               if (var8 == null) {
                  break label138;
               }
            } else if (var20.equals(var8)) {
               break label138;
            }

            label139: {
               var20 = java.lang.Short.TYPE;
               if (var20 == null) {
                  if (var8 == null) {
                     break label139;
                  }
               } else if (var20.equals(var8)) {
                  break label139;
               }

               label140: {
                  var20 = Character.TYPE;
                  if (var20 == null) {
                     if (var8 == null) {
                        break label140;
                     }
                  } else if (var20.equals(var8)) {
                     break label140;
                  }

                  label141: {
                     var20 = Integer.TYPE;
                     if (var20 == null) {
                        if (var8 == null) {
                           break label141;
                        }
                     } else if (var20.equals(var8)) {
                        break label141;
                     }

                     label142: {
                        var20 = java.lang.Long.TYPE;
                        if (var20 == null) {
                           if (var8 == null) {
                              break label142;
                           }
                        } else if (var20.equals(var8)) {
                           break label142;
                        }

                        label143: {
                           var20 = java.lang.Float.TYPE;
                           if (var20 == null) {
                              if (var8 == null) {
                                 break label143;
                              }
                           } else if (var20.equals(var8)) {
                              break label143;
                           }

                           label144: {
                              var20 = java.lang.Double.TYPE;
                              if (var20 == null) {
                                 if (var8 == null) {
                                    break label144;
                                 }
                              } else if (var20.equals(var8)) {
                                 break label144;
                              }

                              label145: {
                                 var20 = java.lang.Boolean.TYPE;
                                 if (var20 == null) {
                                    if (var8 == null) {
                                       break label145;
                                    }
                                 } else if (var20.equals(var8)) {
                                    break label145;
                                 }

                                 label78: {
                                    var20 = Void.TYPE;
                                    if (var20 == null) {
                                       if (var8 == null) {
                                          break label78;
                                       }
                                    } else if (var20.equals(var8)) {
                                       break label78;
                                    }

                                    var29 = new ArrayBuilder.ofRef(filter$extension_make_evidence$1);
                                    break label135;
                                 }

                                 var29 = new ArrayBuilder.ofUnit();
                                 break label135;
                              }

                              var29 = new ArrayBuilder.ofBoolean();
                              break label135;
                           }

                           var29 = new ArrayBuilder.ofDouble();
                           break label135;
                        }

                        var29 = new ArrayBuilder.ofFloat();
                        break label135;
                     }

                     var29 = new ArrayBuilder.ofLong();
                     break label135;
                  }

                  var29 = new ArrayBuilder.ofInt();
                  break label135;
               }

               var29 = new ArrayBuilder.ofChar();
               break label135;
            }

            var29 = new ArrayBuilder.ofShort();
            break label135;
         }

         var29 = new ArrayBuilder.ofByte();
      }

      Object var17 = null;
      Object var18 = null;
      ArrayBuilder filter$extension_res = (ArrayBuilder)var29;

      for(int filter$extension_i = 0; filter$extension_i < ((Object[])filter$extension_$this).length; ++filter$extension_i) {
         Object filter$extension_x = ((Object[])filter$extension_$this)[filter$extension_i];
         if ($anonfun$populateNameMap$2(fields, (Method)filter$extension_x)) {
            filter$extension_res.addOne(filter$extension_x);
         }
      }

      var29 = filter$extension_res.result();
      filter$extension_$this = null;
      filter$extension_res = null;
      Object var16 = null;

      for(Object var12 : (Method[])var29) {
         $anonfun$populateNameMap$3(this, (Method)var12);
      }

   }

   public synchronized String scala$Enumeration$$nameOf(final int i) {
      return (String)this.nmap.getOrElse(i, () -> {
         this.populateNameMap();
         return (String)this.nmap.apply(i);
      });
   }

   private final void ValueOrdering$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValueOrdering$module == null) {
            this.ValueOrdering$module = new ValueOrdering$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void ValueSet$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.ValueSet$module == null) {
            this.ValueSet$module = new ValueSet$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final Field[] getFields$1(final Class clazz, final Field[] acc) {
      while(clazz != null) {
         Class var10000 = clazz.getSuperclass();
         Field[] var10001;
         if (clazz.getDeclaredFields().length == 0) {
            var10001 = acc;
         } else {
            ArrayOps$ var7 = ArrayOps$.MODULE$;
            Field[] var10002 = clazz.getDeclaredFields();
            ClassTag $plus$plus$extension_evidence$25 = ClassTag$.MODULE$.apply(Field.class);
            Object $plus$plus$extension_xs = var10002;
            Object var8 = var7.appendedAll$extension(acc, (Object)$plus$plus$extension_xs, $plus$plus$extension_evidence$25);
            $plus$plus$extension_xs = null;
            $plus$plus$extension_evidence$25 = null;
            var10001 = (Field[])var8;
         }

         acc = var10001;
         clazz = var10000;
      }

      return acc;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$populateNameMap$1(final Method m$1, final Field fd) {
      String var10000 = fd.getName();
      String var2 = m$1.getName();
      if (var10000 == null) {
         if (var2 != null) {
            return false;
         }
      } else if (!var10000.equals(var2)) {
         return false;
      }

      Class var4 = fd.getType();
      Class var3 = m$1.getReturnType();
      if (var4 == null) {
         if (var3 == null) {
            return true;
         }
      } else if (var4.equals(var3)) {
         return true;
      }

      return false;
   }

   private static final boolean isValDef$1(final Method m, final Field[] fields$1) {
      int exists$extension_indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (exists$extension_indexWhere$extension_i >= fields$1.length) {
            var10000 = -1;
            break;
         }

         Field var3 = fields$1[exists$extension_indexWhere$extension_i];
         if ($anonfun$populateNameMap$1(m, var3)) {
            var10000 = exists$extension_indexWhere$extension_i;
            break;
         }

         ++exists$extension_indexWhere$extension_i;
      }

      return var10000 >= 0;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$populateNameMap$2(final Field[] fields$1, final Method m) {
      if (m.getParameterTypes().length == 0 && Value.class.isAssignableFrom(m.getReturnType())) {
         Class var10000 = m.getDeclaringClass();
         Class var2 = Enumeration.class;
         if (var10000 != null) {
            if (var10000.equals(var2)) {
               return false;
            }
         }

         if (isValDef$1(m, fields$1)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final Object $anonfun$populateNameMap$3(final Enumeration $this, final Method m) {
      String name = m.getName();
      Value value = (Value)m.invoke($this);
      if (value.scala$Enumeration$$outerEnum() == $this) {
         int id = value.id();
         Map var10000 = $this.nmap;
         Tuple2 $plus$eq_elem = new Tuple2(id, name);
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.addOne($plus$eq_elem);
         }
      } else {
         return BoxedUnit.UNIT;
      }
   }

   public Enumeration(final int initial) {
      this.scala$Enumeration$$vmap = new HashMap();
      this.vset = null;
      this.scala$Enumeration$$vsetDefined = false;
      this.nmap = new HashMap();
      this.nextId = initial;
      this.scala$Enumeration$$topId = initial;
      this.scala$Enumeration$$bottomId = initial < 0 ? initial : 0;
   }

   public Enumeration() {
      this(0);
   }

   // $FF: synthetic method
   public static final Object $anonfun$populateNameMap$2$adapted(final Field[] fields$1, final Method m) {
      return BoxesRunTime.boxToBoolean($anonfun$populateNameMap$2(fields$1, m));
   }

   // $FF: synthetic method
   public static final Object $anonfun$populateNameMap$1$adapted(final Method m$1, final Field fd) {
      return BoxesRunTime.boxToBoolean($anonfun$populateNameMap$1(m$1, fd));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public abstract class Value implements Ordered, Serializable {
      private static final long serialVersionUID = 7091335633555234129L;
      private final Enumeration scala$Enumeration$$outerEnum;
      // $FF: synthetic field
      public final Enumeration $outer;

      public boolean $less(final Object that) {
         return Ordered.$less$(this, that);
      }

      public boolean $greater(final Object that) {
         return Ordered.$greater$(this, that);
      }

      public boolean $less$eq(final Object that) {
         return Ordered.$less$eq$(this, that);
      }

      public boolean $greater$eq(final Object that) {
         return Ordered.$greater$eq$(this, that);
      }

      public int compareTo(final Object that) {
         return Ordered.compareTo$(this, that);
      }

      public abstract int id();

      public Enumeration scala$Enumeration$$outerEnum() {
         return this.scala$Enumeration$$outerEnum;
      }

      public int compare(final Value that) {
         if (this.id() < that.id()) {
            return -1;
         } else {
            return this.id() == that.id() ? 0 : 1;
         }
      }

      public boolean equals(final Object other) {
         if (other instanceof Value) {
            Value var2 = (Value)other;
            return this.scala$Enumeration$$outerEnum() == var2.scala$Enumeration$$outerEnum() && this.id() == var2.id();
         } else {
            return false;
         }
      }

      public int hashCode() {
         return this.id();
      }

      public ValueSet $plus(final Value v) {
         ValueSet$ var10000 = this.scala$Enumeration$Value$$$outer().ValueSet();
         ArraySeq apply_xs = ScalaRunTime$.MODULE$.wrapRefArray(new Value[]{this, v});
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.fromSpecific(apply_xs);
         }
      }

      // $FF: synthetic method
      public Enumeration scala$Enumeration$Value$$$outer() {
         return this.$outer;
      }

      public Value() {
         if (Enumeration.this == null) {
            throw null;
         } else {
            this.$outer = Enumeration.this;
            super();
            this.scala$Enumeration$$outerEnum = Enumeration.this;
         }
      }
   }

   public class Val extends Value {
      private static final long serialVersionUID = -3501153230598116017L;
      private final int i;
      private final String name;

      public int id() {
         return this.i;
      }

      public String toString() {
         if (this.name != null) {
            return this.name;
         } else {
            try {
               return this.scala$Enumeration$Val$$$outer().scala$Enumeration$$nameOf(this.i);
            } catch (NoSuchElementException var1) {
               return (new StringBuilder(30)).append("<Invalid enum: no field for #").append(this.i).append(">").toString();
            }
         }
      }

      public Object readResolve() {
         Enumeration enumeration = (Enumeration)this.scala$Enumeration$Val$$$outer().readResolve();
         return enumeration.scala$Enumeration$$vmap() == null ? this : enumeration.scala$Enumeration$$vmap().apply(this.i);
      }

      // $FF: synthetic method
      public Enumeration scala$Enumeration$Val$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final String $anonfun$new$1(final Val $this) {
         return (new StringBuilder(14)).append("Duplicate id: ").append($this.i).toString();
      }

      public Val(final int i, final String name) {
         this.i = i;
         this.name = name;
         if (Enumeration.this.scala$Enumeration$$vmap().isDefinedAt(i)) {
            throw new AssertionError((new StringBuilder(18)).append("assertion failed: ").append($anonfun$new$1(this)).toString());
         } else {
            Enumeration.this.scala$Enumeration$$vmap().update(i, this);
            Enumeration.this.scala$Enumeration$$vsetDefined_$eq(false);
            Enumeration.this.nextId_$eq(i + 1);
            if (Enumeration.this.nextId() > Enumeration.this.scala$Enumeration$$topId) {
               Enumeration.this.scala$Enumeration$$topId = Enumeration.this.nextId();
            }

            if (i < Enumeration.this.scala$Enumeration$$bottomId) {
               Enumeration.this.scala$Enumeration$$bottomId = i;
            }

         }
      }

      public Val(final int i) {
         this(i, Enumeration.this.scala$Enumeration$$nextNameOrNull());
      }

      public Val(final String name) {
         this(Enumeration.this.nextId(), name);
      }

      public Val() {
         this(Enumeration.this.nextId());
      }
   }

   public class ValueOrdering$ implements Ordering {
      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
      }

      public boolean lteq(final Object x, final Object y) {
         return Ordering.lteq$(this, x, y);
      }

      public boolean gteq(final Object x, final Object y) {
         return Ordering.gteq$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Ordering.lt$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Ordering.gt$(this, x, y);
      }

      public boolean equiv(final Object x, final Object y) {
         return Ordering.equiv$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Ordering.max$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Ordering.min$(this, x, y);
      }

      public Ordering reverse() {
         return Ordering.reverse$(this);
      }

      public boolean isReverseOf(final Ordering other) {
         return Ordering.isReverseOf$(this, other);
      }

      public Ordering on(final Function1 f) {
         return Ordering.on$(this, f);
      }

      public Ordering orElse(final Ordering other) {
         return Ordering.orElse$(this, other);
      }

      public Ordering orElseBy(final Function1 f, final Ordering ord) {
         return Ordering.orElseBy$(this, f, ord);
      }

      public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
         return Ordering.mkOrderingOps$(this, lhs);
      }

      public int compare(final Value x, final Value y) {
         return x.compare(y);
      }
   }

   public class ValueSet extends AbstractSet implements SortedSet, StrictOptimizedIterableOps, Serializable {
      private static final long serialVersionUID = 7229671200427364242L;
      private transient scala.collection.immutable.Map scala$Enumeration$$byName;
      private scala.collection.immutable.BitSet nnIds;
      private transient volatile boolean bitmap$trans$0;
      // $FF: synthetic field
      public final Enumeration $outer;

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

      public Set unsorted() {
         return SortedSet.unsorted$(this);
      }

      public SortedIterableFactory sortedIterableFactory() {
         return SortedSet.sortedIterableFactory$(this);
      }

      // $FF: synthetic method
      public boolean scala$collection$SortedSet$$super$equals(final Object that) {
         return scala.collection.Set.equals$(this, that);
      }

      public String stringPrefix() {
         return scala.collection.SortedSet.stringPrefix$(this);
      }

      public boolean equals(final Object that) {
         return scala.collection.SortedSet.equals$(this, that);
      }

      public SortedSetOps.WithFilter withFilter(final Function1 p) {
         return SortedSetFactoryDefaults.withFilter$(this, p);
      }

      // $FF: synthetic method
      public Object scala$collection$SortedSetOps$$super$min(final Ordering ord) {
         return IterableOnceOps.min$(this, ord);
      }

      // $FF: synthetic method
      public Object scala$collection$SortedSetOps$$super$max(final Ordering ord) {
         return IterableOnceOps.max$(this, ord);
      }

      /** @deprecated */
      public Iterator keysIteratorFrom(final Object start) {
         return SortedSetOps.keysIteratorFrom$(this, start);
      }

      public Object firstKey() {
         return SortedSetOps.firstKey$(this);
      }

      public Object lastKey() {
         return SortedSetOps.lastKey$(this);
      }

      public Option minAfter(final Object key) {
         return SortedSetOps.minAfter$(this, key);
      }

      public Option maxBefore(final Object key) {
         return SortedSetOps.maxBefore$(this, key);
      }

      public Object min(final Ordering ord) {
         return SortedSetOps.min$(this, ord);
      }

      public Object max(final Ordering ord) {
         return SortedSetOps.max$(this, ord);
      }

      public SortedSetOps rangeTo(final Object to) {
         return SortedSetOps.rangeTo$(this, to);
      }

      /** @deprecated */
      public int compare(final Object k0, final Object k1) {
         return SortedOps.compare$(this, k0, k1);
      }

      public Object range(final Object from, final Object until) {
         return SortedOps.range$(this, from, until);
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

      public Ordering ordering() {
         return this.scala$Enumeration$ValueSet$$$outer().ValueOrdering();
      }

      public ValueSet rangeImpl(final Option from, final Option until) {
         ValueSet var10000 = new ValueSet;
         Enumeration var10002 = this.scala$Enumeration$ValueSet$$$outer();
         scala.collection.immutable.BitSet var10003 = this.nnIds;
         if (from == null) {
            throw null;
         } else {
            Object var10004;
            if (from.isEmpty()) {
               var10004 = None$.MODULE$;
            } else {
               Value var3 = (Value)from.get();
               var10004 = new Some($anonfun$rangeImpl$1(this, var3));
            }

            if (until == null) {
               throw null;
            } else {
               Object var10005;
               if (until.isEmpty()) {
                  var10005 = None$.MODULE$;
               } else {
                  Value var4 = (Value)until.get();
                  var10005 = new Some($anonfun$rangeImpl$2(this, var4));
               }

               var10000.<init>((scala.collection.immutable.BitSet)var10003.rangeImpl((Option)var10004, (Option)var10005));
               return var10000;
            }
         }
      }

      public ValueSet empty() {
         return this.scala$Enumeration$ValueSet$$$outer().ValueSet().empty();
      }

      public int knownSize() {
         return this.nnIds.size();
      }

      public boolean isEmpty() {
         return this.nnIds.isEmpty();
      }

      public boolean contains(final Value v) {
         return this.nnIds.contains(v.id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId);
      }

      public ValueSet incl(final Value value) {
         ValueSet var10000 = new ValueSet;
         Enumeration var10002 = this.scala$Enumeration$ValueSet$$$outer();
         SetOps var10003 = this.nnIds;
         Integer $plus_elem = value.id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
         if (var10003 == null) {
            throw null;
         } else {
            var10003 = var10003.incl($plus_elem);
            Object var3 = null;
            var10000.<init>((scala.collection.immutable.BitSet)var10003);
            return var10000;
         }
      }

      public ValueSet excl(final Value value) {
         ValueSet var10000 = new ValueSet;
         Enumeration var10002 = this.scala$Enumeration$ValueSet$$$outer();
         SetOps var10003 = this.nnIds;
         Integer $minus_elem = value.id() - this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
         if (var10003 == null) {
            throw null;
         } else {
            var10003 = var10003.excl($minus_elem);
            Object var3 = null;
            var10000.<init>((scala.collection.immutable.BitSet)var10003);
            return var10000;
         }
      }

      public Iterator iterator() {
         return this.nnIds.iterator().map((id) -> $anonfun$iterator$1(this, BoxesRunTime.unboxToInt(id)));
      }

      public Iterator iteratorFrom(final Value start) {
         return this.nnIds.iteratorFrom(start.id()).map((id) -> $anonfun$iteratorFrom$1(this, BoxesRunTime.unboxToInt(id)));
      }

      public String className() {
         return (new StringBuilder(9)).append(this.scala$Enumeration$ValueSet$$$outer()).append(".ValueSet").toString();
      }

      public long[] toBitMask() {
         return this.nnIds.toBitMask();
      }

      public ValueSet fromSpecific(final IterableOnce coll) {
         return this.scala$Enumeration$ValueSet$$$outer().ValueSet().fromSpecific(coll);
      }

      public Builder newSpecificBuilder() {
         ValueSet$ var10000 = this.scala$Enumeration$ValueSet$$$outer().ValueSet();
         if (var10000 == null) {
            throw null;
         } else {
            ValueSet$ newBuilder_this = var10000;
            return new Builder() {
               private final BitSet b;
               // $FF: synthetic field
               private final ValueSet$ $outer;

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
               public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
                  return Growable.$plus$eq$(this, elem1, elem2, elems);
               }

               public Growable addAll(final IterableOnce elems) {
                  return Growable.addAll$(this, elems);
               }

               public final Growable $plus$plus$eq(final IterableOnce elems) {
                  return Growable.$plus$plus$eq$(this, elems);
               }

               public int knownSize() {
                  return Growable.knownSize$(this);
               }

               public <undefinedtype> addOne(final Value x) {
                  BitSet var10000 = this.b;
                  Integer $plus$eq_elem = x.id() - this.$outer.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
                  if (var10000 == null) {
                     throw null;
                  } else {
                     var10000.addOne($plus$eq_elem);
                     return this;
                  }
               }

               public void clear() {
                  this.b.clear();
               }

               public ValueSet result() {
                  return this.$outer.scala$Enumeration$ValueSet$$$outer().new ValueSet(this.b.toImmutable());
               }

               public {
                  if (Enumeration.this == null) {
                     throw null;
                  } else {
                     this.$outer = Enumeration.this;
                     this.b = new BitSet();
                  }
               }
            };
         }
      }

      public ValueSet map(final Function1 f) {
         return this.fromSpecific(new View.Map(this, f));
      }

      public ValueSet flatMap(final Function1 f) {
         return this.fromSpecific(new View.FlatMap(this, f));
      }

      public SortedSet map(final Function1 f, final Ordering ev) {
         return (SortedSet)SortedSetOps.map$(this, f, ev);
      }

      public SortedSet flatMap(final Function1 f, final Ordering ev) {
         return (SortedSet)SortedSetOps.flatMap$(this, f, ev);
      }

      public SortedSet zip(final IterableOnce that, final Ordering ev) {
         return (SortedSet)SortedSetOps.zip$(this, that, ev);
      }

      public SortedSet collect(final PartialFunction pf, final Ordering ev) {
         return (SortedSet)SortedSetOps.collect$(this, pf, ev);
      }

      private scala.collection.immutable.Map scala$Enumeration$$byName$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$trans$0) {
               this.scala$Enumeration$$byName = this.iterator().map((v) -> {
                  Predef.ArrowAssoc$ var10000 = Predef.ArrowAssoc$.MODULE$;
                  Object ArrowAssoc_self = v.toString();
                  var10000 = (Predef.ArrowAssoc$)ArrowAssoc_self;
                  ArrowAssoc_self = null;
                  Object $minus$greater$extension_$this = var10000;
                  return new Tuple2($minus$greater$extension_$this, v);
               }).toMap($less$colon$less$.MODULE$.refl());
               this.bitmap$trans$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.scala$Enumeration$$byName;
      }

      public scala.collection.immutable.Map scala$Enumeration$$byName() {
         return !this.bitmap$trans$0 ? this.scala$Enumeration$$byName$lzycompute() : this.scala$Enumeration$$byName;
      }

      // $FF: synthetic method
      public Enumeration scala$Enumeration$ValueSet$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final int $anonfun$rangeImpl$1(final ValueSet $this, final Value x$2) {
         return x$2.id() - $this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
      }

      // $FF: synthetic method
      public static final int $anonfun$rangeImpl$2(final ValueSet $this, final Value x$3) {
         return x$3.id() - $this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
      }

      // $FF: synthetic method
      public static final Value $anonfun$iterator$1(final ValueSet $this, final int id) {
         return $this.scala$Enumeration$ValueSet$$$outer().apply($this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId + id);
      }

      // $FF: synthetic method
      public static final Value $anonfun$iteratorFrom$1(final ValueSet $this, final int id) {
         return $this.scala$Enumeration$ValueSet$$$outer().apply($this.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId + id);
      }

      public ValueSet(final scala.collection.immutable.BitSet nnIds) {
         this.nnIds = nnIds;
         if (Enumeration.this == null) {
            throw null;
         } else {
            this.$outer = Enumeration.this;
            super();
         }
      }

      // $FF: synthetic method
      public static final Object $anonfun$rangeImpl$1$adapted(final ValueSet $this, final Value x$2) {
         return BoxesRunTime.boxToInteger($anonfun$rangeImpl$1($this, x$2));
      }

      // $FF: synthetic method
      public static final Object $anonfun$rangeImpl$2$adapted(final ValueSet $this, final Value x$3) {
         return BoxesRunTime.boxToInteger($anonfun$rangeImpl$2($this, x$3));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public class ValueSet$ implements SpecificIterableFactory, Serializable {
      private static final long serialVersionUID = 3L;
      private final ValueSet empty;
      // $FF: synthetic field
      private final Enumeration $outer;

      public Object apply(final Seq xs) {
         return SpecificIterableFactory.apply$(this, xs);
      }

      public Object fill(final int n, final Function0 elem) {
         return SpecificIterableFactory.fill$(this, n, elem);
      }

      public Factory specificIterableFactory() {
         return SpecificIterableFactory.specificIterableFactory$(this);
      }

      private final String ordMsg() {
         return "No implicit Ordering[${B}] found to build a SortedSet[${B}]. You may want to upcast to a Set[Value] first by calling `unsorted`.";
      }

      private final String zipOrdMsg() {
         return "No implicit Ordering[${B}] found to build a SortedSet[(Value, ${B})]. You may want to upcast to a Set[Value] first by calling `unsorted`.";
      }

      public ValueSet empty() {
         return this.empty;
      }

      public ValueSet fromBitMask(final long[] elems) {
         return this.$outer.new ValueSet(BitSet$.MODULE$.fromBitMask(elems));
      }

      public Builder newBuilder() {
         return new Builder() {
            private final BitSet b;
            // $FF: synthetic field
            private final ValueSet$ $outer;

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
            public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
               return Growable.$plus$eq$(this, elem1, elem2, elems);
            }

            public Growable addAll(final IterableOnce elems) {
               return Growable.addAll$(this, elems);
            }

            public final Growable $plus$plus$eq(final IterableOnce elems) {
               return Growable.$plus$plus$eq$(this, elems);
            }

            public int knownSize() {
               return Growable.knownSize$(this);
            }

            public <undefinedtype> addOne(final Value x) {
               BitSet var10000 = this.b;
               Integer $plus$eq_elem = x.id() - this.$outer.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
               if (var10000 == null) {
                  throw null;
               } else {
                  var10000.addOne($plus$eq_elem);
                  return this;
               }
            }

            public void clear() {
               this.b.clear();
            }

            public ValueSet result() {
               return this.$outer.scala$Enumeration$ValueSet$$$outer().new ValueSet(this.b.toImmutable());
            }

            public {
               if (Enumeration.this == null) {
                  throw null;
               } else {
                  this.$outer = Enumeration.this;
                  this.b = new BitSet();
               }
            }
         };
      }

      public ValueSet fromSpecific(final IterableOnce it) {
         return (ValueSet)((Builder)Growable.addAll$(new Builder() {
            private final BitSet b;
            // $FF: synthetic field
            private final ValueSet$ $outer;

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
            public final Growable $plus$eq(final Object elem1, final Object elem2, final Seq elems) {
               return Growable.$plus$eq$(this, elem1, elem2, elems);
            }

            public Growable addAll(final IterableOnce elems) {
               return Growable.addAll$(this, elems);
            }

            public final Growable $plus$plus$eq(final IterableOnce elems) {
               return Growable.$plus$plus$eq$(this, elems);
            }

            public int knownSize() {
               return Growable.knownSize$(this);
            }

            public <undefinedtype> addOne(final Value x) {
               BitSet var10000 = this.b;
               Integer $plus$eq_elem = x.id() - this.$outer.scala$Enumeration$ValueSet$$$outer().scala$Enumeration$$bottomId;
               if (var10000 == null) {
                  throw null;
               } else {
                  var10000.addOne($plus$eq_elem);
                  return this;
               }
            }

            public void clear() {
               this.b.clear();
            }

            public ValueSet result() {
               return this.$outer.scala$Enumeration$ValueSet$$$outer().new ValueSet(this.b.toImmutable());
            }

            public {
               if (Enumeration.this == null) {
                  throw null;
               } else {
                  this.$outer = Enumeration.this;
                  this.b = new BitSet();
               }
            }
         }, it)).result();
      }

      // $FF: synthetic method
      public Enumeration scala$Enumeration$ValueSet$$$outer() {
         return this.$outer;
      }

      public ValueSet$() {
         if (Enumeration.this == null) {
            throw null;
         } else {
            this.$outer = Enumeration.this;
            super();
            this.empty = Enumeration.this.new ValueSet(BitSet$.MODULE$.empty());
         }
      }
   }
}
