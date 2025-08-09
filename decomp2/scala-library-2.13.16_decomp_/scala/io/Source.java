package scala.io;

import java.io.Closeable;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.invoke.SerializedLambda;
import java.net.URI;
import java.net.URL;
import scala.$less$colon$less;
import scala.Console$;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Tuple2;
import scala.package$;
import scala.collection.AbstractIterator;
import scala.collection.BufferedIterator;
import scala.collection.Factory;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StringOps$;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.immutable.Vector;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011-q!B6m\u0011\u0003\th!B:m\u0011\u0003!\b\"B=\u0002\t\u0003Q\bbB>\u0002\u0005\u0004%\t\u0001 \u0005\b\u0003\u0003\t\u0001\u0015!\u0003~\u0011\u001d\t\u0019!\u0001C\u0001\u0003\u000bAq!!\u0004\u0002\t\u0003\ty\u0001C\u0004\u0003J\u0006!\tAa3\t\u000f\tE\u0017\u0001\"\u0001\u0003T\"9!q\\\u0001\u0005\u0002\t\u0005\bb\u0002Bt\u0003\u0011\u0005!\u0011\u001e\u0005\b\u0005O\fA\u0011\u0001B~\u0011\u001d\u00119/\u0001C\u0001\u0007\u0007AqAa:\u0002\t\u0003\u0019I\u0002C\u0004\u0003h\u0006!\taa\b\t\u000f\t\u001d\u0018\u0001\"\u0001\u00040!9!q]\u0001\u0005\u0002\rU\u0002b\u0002Bt\u0003\u0011\u00051q\b\u0005\b\u0007\u0013\nA\u0011AB&\u0011\u001d\u0019I%\u0001C\u0001\u0007;Bqaa\u0019\u0002\t\u0003\u0019)\u0007C\u0004\u0004~\u0005!\taa \t\u000f\r\u001d\u0015\u0001\"\u0001\u0004\n\"91qQ\u0001\u0005\u0002\r=\u0005bBBD\u0003\u0011\u00051q\u0013\u0005\b\u0007\u000f\u000bA\u0011ABS\u0011\u001d\u0019i+\u0001C\u0001\u0007_C\u0011b!2\u0002#\u0003%\taa2\t\u0013\r-\u0017!%A\u0005\u0002\r5\u0007\"CBi\u0003E\u0005I\u0011ABj\u0011\u001d\u00199.\u0001C\u0001\u00073Dqaa6\u0002\t\u0003\u0019\t\u000fC\u0004\u0004j\u0006!\taa;\t\u0013\u0011\u0015\u0011!%A\u0005\u0002\u0011\u001daAB:m\u0003\u0003\t\u0019\u0002\u0003\u0004zE\u0011\u0005\u0011q\u0007\u0005\n\u0003s\u0011#\u0019!D\t\u0003wA\u0011\"!\u0010#\u0001\u0004%\t!a\u0010\t\u0013\u0005]#\u00051A\u0005\u0002\u0005e\u0003\u0002CA3E\u0001\u0006K!!\u0011\t\u0011\u0005\u001d$\u00051A\u0005\u0002qD\u0011\"!\u001b#\u0001\u0004%\t!a\u001b\t\u000f\u0005=$\u0005)Q\u0005{\"A\u0011\u0011\u000f\u0012A\u0002\u0013\u0005A\u0010C\u0005\u0002t\t\u0002\r\u0011\"\u0001\u0002v!9\u0011\u0011\u0010\u0012!B\u0013i\bbBA>E\u0011%\u0011Q\u0010\u0004\u0007\u0003\u0007\u0013\u0003!!\"\t\re|C\u0011AAK\u0011!\tYj\fQ\u0001\n\u0005u\u0005BCA\u001d_!\u0015\r\u0011\"\u0001\u0002*\"9\u0011\u0011W\u0018\u0005\u0002\u0005M\u0006bBA`_\u0011\u0005\u0011\u0011\u0019\u0005\b\u0003\u0007|C\u0011AAc\u0011\u001d\t9m\fC\u0001\u0003\u0013Dq!a3#\t\u0003\ti\rC\u0004\u0002D\n\"\t!!2\t\u000f\u0005\u001d'\u0005\"\u0001\u0002P\u001a1\u0011\u0011\u001b\u0012\u0001\u0003'D!\"!6;\u0005\u0003\u0005\u000b\u0011BAl\u0011\u0019I(\b\"\u0001\u0002^\"1\u0011P\u000fC\u0001\u0003GD1\"!0;\u0001\u0004\u0005\r\u0011\"\u0001\u0002f\"Y\u0011q\u001d\u001eA\u0002\u0003\u0007I\u0011AAu\u0011-\tiO\u000fa\u0001\u0002\u0003\u0006K!a\t\t\u0011\u0005=(\b1A\u0005\u0002qD\u0011\"!=;\u0001\u0004%\t!a=\t\u000f\u0005](\b)Q\u0005{\"A\u0011\u0011 \u001eA\u0002\u0013\u0005A\u0010C\u0005\u0002|j\u0002\r\u0011\"\u0001\u0002~\"9!\u0011\u0001\u001e!B\u0013i\b\u0002\u0003B\u0002u\u0001\u0007I\u0011\u0001?\t\u0013\t\u0015!\b1A\u0005\u0002\t\u001d\u0001b\u0002B\u0006u\u0001\u0006K! \u0005\t\u0005\u001bQ\u0004\u0019!C\u0001y\"I!q\u0002\u001eA\u0002\u0013\u0005!\u0011\u0003\u0005\b\u0005+Q\u0004\u0015)\u0003~\u0011\u001d\t9M\u000fC\u0001\u0003\u001f<qAa\u0006#\u0011\u0003\u0011IBB\u0004\u0003\u001c\tB\tA!\b\t\re|E\u0011\u0001B\u0010\u0011\u001d\u0011\tc\u0014C\u0001\u0005G9qAa\u000b#\u0011\u0003\u0011iCB\u0004\u00030\tB\tA!\r\t\re\u001cF\u0011\u0001B\u001a\u000f\u001d\u0011)D\tE\u0001\u0005o1qA!\u000f#\u0011\u0003\u0011Y\u0004\u0003\u0004z-\u0012\u0005!Q\b\u0005\b\u0003\u000f4F\u0011IAh\u0011\u001d\tiL\tC\u0001\u0003KDa!a<#\t\u0003a\bb\u0002B E\u0011\u0005!\u0011\t\u0005\n\u0005'\u0012\u0013\u0013!C\u0001\u0005+BqAa\u001b#\t\u0013\u0011i\u0007C\u0004\u0003t\t\"\tA!\u001e\t\u000f\tu$\u0005\"\u0001\u0003\u0000!I!q\u0011\u0012\u0012\u0002\u0013\u0005!Q\u000b\u0005\t\u0005\u0013\u0013\u0003\u0015)\u0003\u0003\f\"A!\u0011\u0013\u0012!B\u0013\u0011\u0019\n\u0003\u0005\u0003\u0016\n\u0002\u000b\u0015BAp\u0011\u001d\u00119J\tC\u0001\u00053CqAa(#\t\u0003\u0011\t\u000bC\u0004\u0003&\n\"\tAa*\t\u000f\t5&\u0005\"\u0001\u00030\"9!Q\u0016\u0012\u0005\u0002\tU\u0006b\u0002B]E\u0011\u0005!1\u0018\u0005\b\u0005{\u0013C\u0011AA\u001c\u0003\u0019\u0019v.\u001e:dK*\u0011QN\\\u0001\u0003S>T\u0011a\\\u0001\u0006g\u000e\fG.Y\u0002\u0001!\t\u0011\u0018!D\u0001m\u0005\u0019\u0019v.\u001e:dKN\u0011\u0011!\u001e\t\u0003m^l\u0011A\\\u0005\u0003q:\u0014a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001r\u00039!UMZ1vYR\u0014UOZ*ju\u0016,\u0012! \t\u0003mzL!a 8\u0003\u0007%sG/A\bEK\u001a\fW\u000f\u001c;Ck\u001a\u001c\u0016N_3!\u0003\u0015\u0019H\u000fZ5o+\t\t9\u0001E\u0002s\u0003\u0013I1!a\u0003m\u00059\u0011UO\u001a4fe\u0016$7k\\;sG\u0016\fAB\u001a:p[&#XM]1cY\u0016$B!!\u0005\u0003@B\u0011!OI\n\u0007EU\f)\"!\u000b\u0011\r\u0005]\u0011QDA\u0012\u001d\r1\u0018\u0011D\u0005\u0004\u00037q\u0017a\u00029bG.\fw-Z\u0005\u0005\u0003?\t\tC\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\r\tYB\u001c\t\u0004m\u0006\u0015\u0012bAA\u0014]\n!1\t[1s!\u0011\tY#a\r\u000e\u0005\u00055\"bA7\u00020)\u0011\u0011\u0011G\u0001\u0005U\u00064\u0018-\u0003\u0003\u00026\u00055\"!C\"m_N,\u0017M\u00197f)\t\t\t\"\u0001\u0003ji\u0016\u0014XCAA\u000b\u0003\u0015!Wm]2s+\t\t\t\u0005\u0005\u0003\u0002D\u0005Ec\u0002BA#\u0003\u001b\u00022!a\u0012o\u001b\t\tIEC\u0002\u0002LA\fa\u0001\u0010:p_Rt\u0014bAA(]\u00061\u0001K]3eK\u001aLA!a\u0015\u0002V\t11\u000b\u001e:j]\u001eT1!a\u0014o\u0003%!Wm]2s?\u0012*\u0017\u000f\u0006\u0003\u0002\\\u0005\u0005\u0004c\u0001<\u0002^%\u0019\u0011q\f8\u0003\tUs\u0017\u000e\u001e\u0005\n\u0003G2\u0013\u0011!a\u0001\u0003\u0003\n1\u0001\u001f\u00132\u0003\u0019!Wm]2sA\u00059a.\u001a:s_J\u001c\u0018a\u00038feJ|'o]0%KF$B!a\u0017\u0002n!A\u00111M\u0015\u0002\u0002\u0003\u0007Q0\u0001\u0005oKJ\u0014xN]:!\u0003%qw/\u0019:oS:<7/A\u0007oo\u0006\u0014h.\u001b8hg~#S-\u001d\u000b\u0005\u00037\n9\b\u0003\u0005\u0002d1\n\t\u00111\u0001~\u0003)qw/\u0019:oS:<7\u000fI\u0001\bY&tWMT;n)\u0011\t\t%a \t\r\u0005\u0005e\u00061\u0001~\u0003\u0011a\u0017N\\3\u0003\u00191Kg.Z%uKJ\fGo\u001c:\u0014\u000b=\n9)a%\u0011\r\u0005%\u0015qRA!\u001b\t\tYIC\u0002\u0002\u000e:\f!bY8mY\u0016\u001cG/[8o\u0013\u0011\t\t*a#\u0003!\u0005\u00137\u000f\u001e:bGRLE/\u001a:bi>\u0014\bCBA\f\u0003;\t\t\u0005\u0006\u0002\u0002\u0018B\u0019\u0011\u0011T\u0018\u000e\u0003\t\n!a\u001d2\u0011\t\u0005}\u0015QU\u0007\u0003\u0003CSA!a)\u0002\f\u00069Q.\u001e;bE2,\u0017\u0002BAT\u0003C\u0013Qb\u0015;sS:<')^5mI\u0016\u0014XCAAV!\u0019\tI)!,\u0002$%!\u0011qVAF\u0005A\u0011UO\u001a4fe\u0016$\u0017\n^3sCR|'/A\u0005jg:+w\u000f\\5oKR!\u0011QWA^!\r1\u0018qW\u0005\u0004\u0003ss'a\u0002\"p_2,\u0017M\u001c\u0005\b\u0003{\u001b\u0004\u0019AA\u0012\u0003\t\u0019\u0007.\u0001\u0003hKR\u001cGCAA[\u0003\u001dA\u0017m\u001d(fqR,\"!!.\u0002\t9,\u0007\u0010\u001e\u000b\u0003\u0003\u0003\n\u0001bZ3u\u0019&tWm\u001d\u000b\u0003\u0003'#\"!a\t\u0003\u0015A{7/\u001b;j_:,'o\u0005\u0002;k\u00069QM\\2pI\u0016\u0014\bc\u0001:\u0002Z&\u0019\u00111\u001c7\u0003\u0011A{7/\u001b;j_:$B!a8\u0002bB\u0019\u0011\u0011\u0014\u001e\t\u000f\u0005UG\b1\u0001\u0002XR\u0011\u0011q\\\u000b\u0003\u0003G\taa\u00195`I\u0015\fH\u0003BA.\u0003WD\u0011\"a\u0019@\u0003\u0003\u0005\r!a\t\u0002\u0007\rD\u0007%A\u0002q_N\fq\u0001]8t?\u0012*\u0017\u000f\u0006\u0003\u0002\\\u0005U\b\u0002CA2\u0005\u0006\u0005\t\u0019A?\u0002\tA|7\u000fI\u0001\u0006G2Lg.Z\u0001\nG2Lg.Z0%KF$B!a\u0017\u0002\u0000\"A\u00111M#\u0002\u0002\u0003\u0007Q0\u0001\u0004dY&tW\rI\u0001\u0005G\u000e|G.\u0001\u0005dG>dw\fJ3r)\u0011\tYF!\u0003\t\u0011\u0005\r\u0004*!AA\u0002u\fQaY2pY\u0002\na\u0001^1cS:\u001c\u0017A\u0003;bE&t7m\u0018\u0013fcR!\u00111\fB\n\u0011!\t\u0019gSA\u0001\u0002\u0004i\u0018a\u0002;bE&t7\rI\u0001\u0010%\u0016d\u0017\r_3e!>\u001c\u0018\u000e^5p]B\u0019\u0011\u0011T(\u0003\u001fI+G.\u0019=fIB{7/\u001b;j_:\u001c2aTAl)\t\u0011I\"\u0001\u0006dQ\u0016\u001c7.\u00138qkR$b!a\u0017\u0003&\t\u001d\u0002BBAA#\u0002\u0007Q\u0010\u0003\u0004\u0003*E\u0003\r!`\u0001\u0007G>dW/\u001c8\u0002#I+G.\u0019=fIB{7/\u001b;j_:,'\u000fE\u0002\u0002\u001aN\u0013\u0011CU3mCb,G\rU8tSRLwN\\3s'\r\u0019\u0016q\u001c\u000b\u0003\u0005[\tABT8Q_NLG/[8oKJ\u00042!!'W\u00051qu\u000eU8tSRLwN\\3s'\r1\u0016q\u001c\u000b\u0003\u0005o\t1B]3q_J$XI\u001d:peRA\u00111\fB\"\u0005\u000b\u0012I\u0005\u0003\u0004\u0002pn\u0003\r! \u0005\b\u0005\u000fZ\u0006\u0019AA!\u0003\ri7o\u001a\u0005\n\u0005\u0017Z\u0006\u0013!a\u0001\u0005\u001b\n1a\\;u!\u0011\tYCa\u0014\n\t\tE\u0013Q\u0006\u0002\f!JLg\u000e^*ue\u0016\fW.A\u000bsKB|'\u000f^#se>\u0014H\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\t]#\u0006\u0002B'\u00053Z#Aa\u0017\u0011\t\tu#qM\u0007\u0003\u0005?RAA!\u0019\u0003d\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0005Kr\u0017AC1o]>$\u0018\r^5p]&!!\u0011\u000eB0\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0007gB\f7-Z:\u0015\t\u0005\u0005#q\u000e\u0005\u0007\u0005cj\u0006\u0019A?\u0002\u00039\faA]3q_J$H\u0003CA.\u0005o\u0012IHa\u001f\t\r\u0005=h\f1\u0001~\u0011\u001d\u00119E\u0018a\u0001\u0003\u0003BqAa\u0013_\u0001\u0004\u0011i%A\u0007sKB|'\u000f^,be:Lgn\u001a\u000b\t\u00037\u0012\tIa!\u0003\u0006\"1\u0011q^0A\u0002uDqAa\u0012`\u0001\u0004\t\t\u0005C\u0005\u0003L}\u0003\n\u00111\u0001\u0003N\u00059\"/\u001a9peR<\u0016M\u001d8j]\u001e$C-\u001a4bk2$HeM\u0001\u000ee\u0016\u001cX\r\u001e$v]\u000e$\u0018n\u001c8\u0011\u000bY\u0014i)!\u0005\n\u0007\t=eNA\u0005Gk:\u001cG/[8oa\u0005i1\r\\8tK\u001a+hn\u0019;j_:\u0004RA\u001eBG\u00037\n!\u0002]8tSRLwN\\3s\u0003%9\u0018\u000e\u001e5SKN,G\u000f\u0006\u0003\u0002\u001a\nm\u0005b\u0002BOI\u0002\u0007!1R\u0001\u0002M\u0006Iq/\u001b;i\u00072|7/\u001a\u000b\u0005\u00033\u0013\u0019\u000bC\u0004\u0003\u001e\u0016\u0004\rAa%\u0002\u001f]LG\u000f\u001b#fg\u000e\u0014\u0018\u000e\u001d;j_:$B!!'\u0003*\"9!1\u00164A\u0002\u0005\u0005\u0013\u0001\u0002;fqR\fqb^5uQB{7/\u001b;j_:Lgn\u001a\u000b\u0005\u00033\u0013\t\fC\u0004\u00034\u001e\u0004\r!!.\u0002\u0005=tG\u0003BAM\u0005oCq!a<i\u0001\u0004\ty.A\u0003dY>\u001cX\r\u0006\u0002\u0002\\\u0005)!/Z:fi\"9!\u0011\u0019\u0004A\u0002\t\r\u0017\u0001C5uKJ\f'\r\\3\u0011\r\u0005]!QYA\u0012\u0013\u0011\u00119-!\t\u0003\u0011%#XM]1cY\u0016\f\u0001B\u001a:p[\u000eC\u0017M\u001d\u000b\u0005\u0003#\u0011i\rC\u0004\u0003P\u001e\u0001\r!a\t\u0002\u0003\r\f\u0011B\u001a:p[\u000eC\u0017M]:\u0015\t\u0005E!Q\u001b\u0005\b\u0005/D\u0001\u0019\u0001Bm\u0003\u0015\u0019\u0007.\u0019:t!\u00151(1\\A\u0012\u0013\r\u0011iN\u001c\u0002\u0006\u0003J\u0014\u0018-_\u0001\u000bMJ|Wn\u0015;sS:<G\u0003BA\t\u0005GDqA!:\n\u0001\u0004\t\t%A\u0001t\u0003!1'o\\7GS2,G\u0003\u0002Bv\u0005o$B!a\u0002\u0003n\"9!q\u001e\u0006A\u0004\tE\u0018!B2pI\u0016\u001c\u0007c\u0001:\u0003t&\u0019!Q\u001f7\u0003\u000b\r{G-Z2\t\u000f\te(\u00021\u0001\u0002B\u0005!a.Y7f)\u0019\t9A!@\u0003\u0000\"9!\u0011`\u0006A\u0002\u0005\u0005\u0003bBB\u0001\u0017\u0001\u0007\u0011\u0011I\u0001\u0004K:\u001cG\u0003BB\u0003\u0007\u0013!B!a\u0002\u0004\b!9!q\u001e\u0007A\u0004\tE\bbBB\u0006\u0019\u0001\u00071QB\u0001\u0004kJL\u0007\u0003BB\b\u0007+i!a!\u0005\u000b\t\rM\u0011qF\u0001\u0004]\u0016$\u0018\u0002BB\f\u0007#\u00111!\u0016*J)\u0019\t9aa\u0007\u0004\u001e!911B\u0007A\u0002\r5\u0001bBB\u0001\u001b\u0001\u0007\u0011\u0011\t\u000b\u0005\u0007C\u0019)\u0003\u0006\u0003\u0002\b\r\r\u0002b\u0002Bx\u001d\u0001\u000f!\u0011\u001f\u0005\b\u0007Oq\u0001\u0019AB\u0015\u0003\u00111\u0017\u000e\\3\u0011\t\u0005-21F\u0005\u0005\u0007[\tiC\u0001\u0003GS2,GCBA\u0004\u0007c\u0019\u0019\u0004C\u0004\u0004(=\u0001\ra!\u000b\t\u000f\r\u0005q\u00021\u0001\u0002BQA\u0011qAB\u001c\u0007s\u0019Y\u0004C\u0004\u0004(A\u0001\ra!\u000b\t\u000f\r\u0005\u0001\u00031\u0001\u0002B!11Q\b\tA\u0002u\f!BY;gM\u0016\u00148+\u001b>f)\u0019\u0019\te!\u0012\u0004HQ!\u0011qAB\"\u0011\u001d\u0011y/\u0005a\u0002\u0005cDqaa\n\u0012\u0001\u0004\u0019I\u0003\u0003\u0004\u0004>E\u0001\r!`\u0001\nMJ|WNQ=uKN$Ba!\u0014\u0004RQ!\u0011\u0011CB(\u0011\u001d\u0011yO\u0005a\u0002\u0005cDqaa\u0015\u0013\u0001\u0004\u0019)&A\u0003csR,7\u000fE\u0003w\u00057\u001c9\u0006E\u0002w\u00073J1aa\u0017o\u0005\u0011\u0011\u0015\u0010^3\u0015\r\u0005E1qLB1\u0011\u001d\u0019\u0019f\u0005a\u0001\u0007+Bqa!\u0001\u0014\u0001\u0004\t\t%\u0001\u0007ge>l'+Y<CsR,7\u000f\u0006\u0003\u0002\u0012\r\u001d\u0004bBB*)\u0001\u00071Q\u000b\u0015\f)\r-4\u0011OB:\u0007o\u001aI\bE\u0002w\u0007[J1aa\u001co\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\t\u0019)(A\u0014Vg\u0016\u0004\u0003M\u001a:p[\nKH/Z:aA\u0005tG\rI:qK\u000eLg-\u001f\u0011b]\u0002*gnY8eS:<\u0017!B:j]\u000e,\u0017EAB>\u0003\u0019\u0011d&M\u001a/s\u00059aM]8n+JKE\u0003BBA\u0007\u000b#B!a\u0002\u0004\u0004\"9!q^\u000bA\u0004\tE\bbBB\u0006+\u0001\u00071QB\u0001\bMJ|W.\u0016*M)\u0019\t9aa#\u0004\u000e\"9!Q\u001d\fA\u0002\u0005\u0005\u0003bBB\u0001-\u0001\u0007\u0011\u0011\t\u000b\u0005\u0007#\u001b)\n\u0006\u0003\u0002\b\rM\u0005b\u0002Bx/\u0001\u000f!\u0011\u001f\u0005\b\u0005K<\u0002\u0019AA!)\u0019\t9a!'\u0004$\"911\u0014\rA\u0002\ru\u0015aA;sYB!1qBBP\u0013\u0011\u0019\tk!\u0005\u0003\u0007U\u0013F\nC\u0004\u0004\u0002a\u0001\r!!\u0011\u0015\t\r\u001d61\u0016\u000b\u0005\u0003\u000f\u0019I\u000bC\u0004\u0003pf\u0001\u001dA!=\t\u000f\rm\u0015\u00041\u0001\u0004\u001e\u0006!2M]3bi\u0016\u0014UO\u001a4fe\u0016$7k\\;sG\u0016$\"b!-\u00046\u000e}6\u0011YBb)\u0011\t9aa-\t\u000f\t=(\u0004q\u0001\u0003r\"91q\u0017\u000eA\u0002\re\u0016aC5oaV$8\u000b\u001e:fC6\u0004B!a\u000b\u0004<&!1QXA\u0017\u0005-Ie\u000e];u'R\u0014X-Y7\t\u0011\ru\"\u0004%AA\u0002uD\u0011B!0\u001b!\u0003\u0005\rAa#\t\u0013\te&\u0004%AA\u0002\tM\u0015AH2sK\u0006$XMQ;gM\u0016\u0014X\rZ*pkJ\u001cW\r\n3fM\u0006,H\u000e\u001e\u00133+\t\u0019IMK\u0002~\u00053\nad\u0019:fCR,')\u001e4gKJ,GmU8ve\u000e,G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\r='\u0006\u0002BF\u00053\nad\u0019:fCR,')\u001e4gKJ,GmU8ve\u000e,G\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\rU'\u0006\u0002BJ\u00053\nqB\u001a:p[&s\u0007/\u001e;TiJ,\u0017-\u001c\u000b\u0007\u0003\u000f\u0019Yna8\t\u000f\rug\u00041\u0001\u0004:\u0006\u0011\u0011n\u001d\u0005\b\u0007\u0003q\u0002\u0019AA!)\u0011\u0019\u0019oa:\u0015\t\u0005\u001d1Q\u001d\u0005\b\u0005_|\u00029\u0001By\u0011\u001d\u0019in\ba\u0001\u0007s\u000bAB\u001a:p[J+7o\\;sG\u0016$ba!<\u0004r\u000eUH\u0003BA\u0004\u0007_DqAa<!\u0001\b\u0011\t\u0010C\u0004\u0004t\u0002\u0002\r!!\u0011\u0002\u0011I,7o\\;sG\u0016D\u0011ba>!!\u0003\u0005\ra!?\u0002\u0017\rd\u0017m]:M_\u0006$WM\u001d\t\u0005\u0007w$\t!\u0004\u0002\u0004~*!1q`A\u0018\u0003\u0011a\u0017M\\4\n\t\u0011\r1Q \u0002\f\u00072\f7o\u001d'pC\u0012,'/\u0001\fge>l'+Z:pkJ\u001cW\r\n3fM\u0006,H\u000e\u001e\u00133+\t!IA\u000b\u0003\u0004z\ne\u0003"
)
public abstract class Source implements Iterator, Closeable {
   private volatile RelaxedPosition$ RelaxedPosition$module;
   private volatile RelaxedPositioner$ RelaxedPositioner$module;
   private volatile NoPositioner$ NoPositioner$module;
   private String descr = "";
   private int nerrors = 0;
   private int nwarnings = 0;
   private Function0 resetFunction = null;
   private Function0 closeFunction = null;
   private Positioner positioner = this.RelaxedPositioner();

   public static ClassLoader fromResource$default$2() {
      return Source$.MODULE$.fromResource$default$2();
   }

   public static BufferedSource fromResource(final String resource, final ClassLoader classLoader, final Codec codec) {
      return Source$.MODULE$.fromResource(resource, classLoader, codec);
   }

   public static BufferedSource fromInputStream(final InputStream is, final Codec codec) {
      return Source$.MODULE$.fromInputStream(is, codec);
   }

   public static BufferedSource fromInputStream(final InputStream is, final String enc) {
      return Source$.MODULE$.fromInputStream(is, enc);
   }

   public static Function0 createBufferedSource$default$4() {
      Source$ var10000 = Source$.MODULE$;
      return null;
   }

   public static Function0 createBufferedSource$default$3() {
      Source$ var10000 = Source$.MODULE$;
      return null;
   }

   public static int createBufferedSource$default$2() {
      return Source$.MODULE$.DefaultBufSize();
   }

   public static BufferedSource createBufferedSource(final InputStream inputStream, final int bufferSize, final Function0 reset, final Function0 close, final Codec codec) {
      Source$ var10000 = Source$.MODULE$;
      Function0 createBufferedSource_resetFn = reset == null ? Source$::$anonfun$createBufferedSource$1 : reset;
      return (BufferedSource)(new BufferedSource(inputStream, bufferSize, codec)).withReset(createBufferedSource_resetFn).withClose(close);
   }

   public static BufferedSource fromURL(final URL url, final Codec codec) {
      return Source$.MODULE$.fromURL(url, codec);
   }

   public static BufferedSource fromURL(final URL url, final String enc) {
      return Source$.MODULE$.fromURL(url, enc);
   }

   public static BufferedSource fromURL(final String s, final Codec codec) {
      return Source$.MODULE$.fromURL(s, codec);
   }

   public static BufferedSource fromURL(final String s, final String enc) {
      return Source$.MODULE$.fromURL(s, enc);
   }

   public static BufferedSource fromURI(final URI uri, final Codec codec) {
      return Source$.MODULE$.fromURI(uri, codec);
   }

   /** @deprecated */
   public static Source fromRawBytes(final byte[] bytes) {
      return Source$.MODULE$.fromRawBytes(bytes);
   }

   public static Source fromBytes(final byte[] bytes, final String enc) {
      return Source$.MODULE$.fromBytes(bytes, enc);
   }

   public static Source fromBytes(final byte[] bytes, final Codec codec) {
      return Source$.MODULE$.fromBytes(bytes, codec);
   }

   public static BufferedSource fromFile(final File file, final int bufferSize, final Codec codec) {
      return Source$.MODULE$.fromFile(file, bufferSize, codec);
   }

   public static BufferedSource fromFile(final File file, final String enc, final int bufferSize) {
      return Source$.MODULE$.fromFile(file, enc, bufferSize);
   }

   public static BufferedSource fromFile(final File file, final String enc) {
      return Source$.MODULE$.fromFile(file, enc);
   }

   public static BufferedSource fromFile(final File file, final Codec codec) {
      return Source$.MODULE$.fromFile(file, codec);
   }

   public static BufferedSource fromFile(final URI uri, final String enc) {
      return Source$.MODULE$.fromFile(uri, enc);
   }

   public static BufferedSource fromFile(final URI uri, final Codec codec) {
      return Source$.MODULE$.fromFile(uri, codec);
   }

   public static BufferedSource fromFile(final String name, final String enc) {
      return Source$.MODULE$.fromFile(name, enc);
   }

   public static BufferedSource fromFile(final String name, final Codec codec) {
      return Source$.MODULE$.fromFile(name, codec);
   }

   public static Source fromString(final String s) {
      return Source$.MODULE$.fromString(s);
   }

   public static Source fromChars(final char[] chars) {
      return Source$.MODULE$.fromChars(chars);
   }

   public static Source fromChar(final char c) {
      return Source$.MODULE$.fromChar(c);
   }

   public static Source fromIterable(final Iterable iterable) {
      return Source$.MODULE$.fromIterable(iterable);
   }

   public static BufferedSource stdin() {
      return Source$.MODULE$.stdin();
   }

   public static int DefaultBufSize() {
      return Source$.MODULE$.DefaultBufSize();
   }

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

   public Map toMap(final $less$colon$less ev) {
      return IterableOnceOps.toMap$(this, ev);
   }

   public Set toSet() {
      return IterableOnceOps.toSet$(this);
   }

   public Seq toSeq() {
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

   public RelaxedPosition$ RelaxedPosition() {
      if (this.RelaxedPosition$module == null) {
         this.RelaxedPosition$lzycompute$1();
      }

      return this.RelaxedPosition$module;
   }

   public RelaxedPositioner$ RelaxedPositioner() {
      if (this.RelaxedPositioner$module == null) {
         this.RelaxedPositioner$lzycompute$1();
      }

      return this.RelaxedPositioner$module;
   }

   public NoPositioner$ NoPositioner() {
      if (this.NoPositioner$module == null) {
         this.NoPositioner$lzycompute$1();
      }

      return this.NoPositioner$module;
   }

   public abstract Iterator iter();

   public String descr() {
      return this.descr;
   }

   public void descr_$eq(final String x$1) {
      this.descr = x$1;
   }

   public int nerrors() {
      return this.nerrors;
   }

   public void nerrors_$eq(final int x$1) {
      this.nerrors = x$1;
   }

   public int nwarnings() {
      return this.nwarnings;
   }

   public void nwarnings_$eq(final int x$1) {
      this.nwarnings = x$1;
   }

   private String lineNum(final int line) {
      Iterator var10000 = this.getLines().drop(line - 1).take(1);
      if (var10000 == null) {
         throw null;
      } else {
         IterableOnceOps mkString_this = var10000;
         String mkString_mkString_sep = "";
         return mkString_this.mkString("", mkString_mkString_sep, "");
      }
   }

   public Iterator getLines() {
      return new LineIterator();
   }

   public boolean hasNext() {
      return this.iter().hasNext();
   }

   public char next() {
      return this.positioner.next();
   }

   public char ch() {
      return this.positioner.ch();
   }

   public int pos() {
      return this.positioner.pos();
   }

   public void reportError(final int pos, final String msg, final PrintStream out) {
      this.nerrors_$eq(this.nerrors() + 1);
      this.report(pos, msg, out);
   }

   private String spaces(final int n) {
      if (package$.MODULE$.List() == null) {
         throw null;
      } else {
         Builder fill_b = new ListBuffer();
         fill_b.sizeHint(n);

         for(int fill_i = 0; fill_i < n; ++fill_i) {
            Object fill_$plus$eq_elem = ' ';
            fill_b.addOne(fill_$plus$eq_elem);
            fill_$plus$eq_elem = null;
         }

         SeqOps var10000 = (SeqOps)fill_b.result();
         fill_b = null;
         Object var9 = null;
         if (var10000 == null) {
            throw null;
         } else {
            IterableOnceOps mkString_this = var10000;
            String mkString_mkString_sep = "";
            return mkString_this.mkString("", mkString_mkString_sep, "");
         }
      }
   }

   public void report(final int pos, final String msg, final PrintStream out) {
      Position$ var10000 = Position$.MODULE$;
      int line = pos >> 11 & 1048575;
      var10000 = Position$.MODULE$;
      int col = pos & 2047;
      out.println(StringOps$.MODULE$.format$extension("%s:%d:%d: %s%s%s^", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{this.descr(), line, col, msg, this.lineNum(line), this.spaces(col - 1)})));
   }

   public PrintStream reportError$default$3() {
      return Console$.MODULE$.err();
   }

   public void reportWarning(final int pos, final String msg, final PrintStream out) {
      this.nwarnings_$eq(this.nwarnings() + 1);
      this.report(pos, (new java.lang.StringBuilder(9)).append("warning! ").append(msg).toString(), out);
   }

   public PrintStream reportWarning$default$3() {
      return Console$.MODULE$.out();
   }

   public Source withReset(final Function0 f) {
      this.resetFunction = f;
      return this;
   }

   public Source withClose(final Function0 f) {
      this.closeFunction = f;
      return this;
   }

   public Source withDescription(final String text) {
      this.descr_$eq(text);
      return this;
   }

   public Source withPositioning(final boolean on) {
      this.positioner = (Positioner)(on ? this.RelaxedPositioner() : this.NoPositioner());
      return this;
   }

   public Source withPositioning(final Positioner pos) {
      this.positioner = pos;
      return this;
   }

   public void close() {
      if (this.closeFunction != null) {
         this.closeFunction.apply$mcV$sp();
      }
   }

   public Source reset() {
      if (this.resetFunction != null) {
         return (Source)this.resetFunction.apply();
      } else {
         throw new UnsupportedOperationException("Source's reset() method was not set.");
      }
   }

   private final void RelaxedPosition$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RelaxedPosition$module == null) {
            this.RelaxedPosition$module = new RelaxedPosition$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void RelaxedPositioner$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.RelaxedPositioner$module == null) {
            this.RelaxedPositioner$module = new RelaxedPositioner$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   private final void NoPositioner$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.NoPositioner$module == null) {
            this.NoPositioner$module = new NoPositioner$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   // $FF: synthetic method
   public static final char $anonfun$spaces$1() {
      return ' ';
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public class LineIterator extends AbstractIterator {
      private BufferedIterator iter;
      private final StringBuilder sb;
      private volatile boolean bitmap$0;
      // $FF: synthetic field
      public final Source $outer;

      private BufferedIterator iter$lzycompute() {
         synchronized(this){}

         try {
            if (!this.bitmap$0) {
               this.iter = this.scala$io$Source$LineIterator$$$outer().iter().buffered();
               this.bitmap$0 = true;
            }
         } catch (Throwable var2) {
            throw var2;
         }

         return this.iter;
      }

      public BufferedIterator iter() {
         return !this.bitmap$0 ? this.iter$lzycompute() : this.iter;
      }

      public boolean isNewline(final char ch) {
         return ch == '\r' || ch == '\n';
      }

      public boolean getc() {
         if (this.iter().hasNext()) {
            char ch = BoxesRunTime.unboxToChar(this.iter().next());
            if (ch != '\n') {
               if (ch != '\r') {
                  this.sb.append(ch);
                  return true;
               }

               if (this.iter().hasNext() && BoxesRunTime.unboxToChar(this.iter().head()) == '\n') {
                  this.iter().next();
               }
            }
         }

         return false;
      }

      public boolean hasNext() {
         return this.iter().hasNext();
      }

      public String next() {
         this.sb.clear();

         while(this.getc()) {
         }

         StringBuilder var10000 = this.sb;
         if (var10000 == null) {
            throw null;
         } else {
            return var10000.result();
         }
      }

      // $FF: synthetic method
      public Source scala$io$Source$LineIterator$$$outer() {
         return this.$outer;
      }

      public LineIterator() {
         if (Source.this == null) {
            throw null;
         } else {
            this.$outer = Source.this;
            super();
            this.sb = new StringBuilder();
         }
      }
   }

   public class Positioner {
      private final Position encoder;
      private char ch;
      private int pos;
      private int cline;
      private int ccol;
      private int tabinc;
      // $FF: synthetic field
      public final Source $outer;

      public char ch() {
         return this.ch;
      }

      public void ch_$eq(final char x$1) {
         this.ch = x$1;
      }

      public int pos() {
         return this.pos;
      }

      public void pos_$eq(final int x$1) {
         this.pos = x$1;
      }

      public int cline() {
         return this.cline;
      }

      public void cline_$eq(final int x$1) {
         this.cline = x$1;
      }

      public int ccol() {
         return this.ccol;
      }

      public void ccol_$eq(final int x$1) {
         this.ccol = x$1;
      }

      public int tabinc() {
         return this.tabinc;
      }

      public void tabinc_$eq(final int x$1) {
         this.tabinc = x$1;
      }

      public char next() {
         this.ch_$eq(BoxesRunTime.unboxToChar(this.scala$io$Source$Positioner$$$outer().iter().next()));
         this.pos_$eq(this.encoder.encode(this.cline(), this.ccol()));
         switch (this.ch()) {
            case '\t':
               this.ccol_$eq(this.ccol() + this.tabinc());
               break;
            case '\n':
               this.ccol_$eq(1);
               this.cline_$eq(this.cline() + 1);
               break;
            default:
               this.ccol_$eq(this.ccol() + 1);
         }

         return this.ch();
      }

      // $FF: synthetic method
      public Source scala$io$Source$Positioner$$$outer() {
         return this.$outer;
      }

      public Positioner(final Position encoder) {
         this.encoder = encoder;
         if (Source.this == null) {
            throw null;
         } else {
            this.$outer = Source.this;
            super();
            this.pos = 0;
            this.cline = 1;
            this.ccol = 1;
            this.tabinc = 4;
         }
      }

      public Positioner() {
         this(Source.this.RelaxedPosition());
      }
   }

   public class RelaxedPosition$ extends Position {
      public void checkInput(final int line, final int column) {
      }
   }

   public class RelaxedPositioner$ extends Positioner {
      public RelaxedPositioner$() {
         super(Source.this.RelaxedPosition());
      }
   }

   public class NoPositioner$ extends Positioner {
      public char next() {
         return BoxesRunTime.unboxToChar(this.scala$io$Source$NoPositioner$$$outer().iter().next());
      }

      // $FF: synthetic method
      public Source scala$io$Source$NoPositioner$$$outer() {
         return this.$outer;
      }

      public NoPositioner$() {
         super(Position$.MODULE$);
      }
   }
}
