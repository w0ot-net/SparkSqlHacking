package spire.math;

import algebra.ring.CommutativeRing;
import algebra.ring.Field;
import algebra.ring.Rig;
import algebra.ring.Ring;
import algebra.ring.Rng;
import algebra.ring.Semiring;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.Builder;
import scala.collection.mutable.ListBuffer;
import scala.collection.mutable.Map.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.NonLocalReturnControl;
import scala.runtime.Nothing;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import spire.math.poly.PolyDense;
import spire.math.poly.PolySparse;
import spire.math.poly.RootFinder;
import spire.math.poly.Roots;
import spire.math.poly.Term;
import spire.math.poly.Term$;

@ScalaSignature(
   bytes = "\u0006\u0005\u00195w!B$I\u0011\u0003ie!B(I\u0011\u0003\u0001\u0006\"\u0002.\u0002\t\u0003Y\u0006\"\u0002/\u0002\t\u0003i\u0006bBA\"\u0003\u0011\u0005\u0011Q\t\u0005\b\u0003\u000f\u000bA\u0011AAE\u0011\u001d\t9)\u0001C\u0001\u0003cCq!a\"\u0002\t\u0003\tY\u000fC\u0004\u0002\b\u0006!\tAa\u0006\t\u000f\u0011\u0015\u0015\u0001\"\u0001\u0005\b\"9A\u0011V\u0001\u0005\u0002\u0011-\u0006b\u0002Ci\u0003\u0011\u0005A1\u001b\u0005\b\t#\fA\u0011\u0001C}\u0011\u001d))#\u0001C\u0001\u000bOAq!\"\n\u0002\t\u0003)y\u0005C\u0004\u0006&\u0005!\t!\"\u001e\t\u000f\u0015\u0005\u0016\u0001\"\u0001\u0006$\"9Q\u0011U\u0001\u0005\u0002\u0015%\u0007bBC|\u0003\u0011\u0005Q\u0011 \u0005\b\u0007\u0003\tA\u0011\u0001D\u000e\u0011\u001d1i$\u0001C\u0001\r\u007fA\u0001B\"\u0019\u0002A\u0003%a1\r\u0005\t\rg\n\u0001\u0015!\u0003\u0007d!AaQO\u0001\u0005\u0002)39\bC\u0004\u0007|\u0005!iA\" \t\u000f\u0019\u0005\u0016\u0001\"\u0001\u0007$\u001aAq\n\u0013I\u0001\u0004\u0003\u0011Y\u0002C\u0004\u0003 i!\tA!\t\t\u000f\t%\"Db\u0001\u0003,!9!\u0011\b\u000e\u0007\u0002\tm\u0002b\u0002B&5\u0019\u0005!Q\n\u0005\b\u0005+Rb\u0011\u0001B,\u0011\u001d\u0011YG\u0007C\u0001\u0005[BqAa \u001b\r\u0003\u0011\t\tC\u0004\u0002Xj!\tAa\"\t\u000f\tU%D\"\u0001\u0003\u0018\"9\u0011q\u000e\u000e\u0005\u0002\t}\u0005b\u0002BT5\u0011\u0005!\u0011\u0016\u0005\b\u0005wSb\u0011\u0001B_\u0011\u001d\u00119M\u0007C\u0001\u0005\u0013DqA!4\u001b\t\u0003\u0011y\rC\u0004\u0003Vj!\tAa6\t\u000f\t}'D\"\u0001\u0003b\"9!1\u001d\u000e\u0007\u0002\t\u0015\bb\u0002Bu5\u0019\u0005!1\u001e\u0005\b\u0005kTb\u0011\u0001Bl\u0011\u001d\t9I\u0007D\u0001\u0005oDqaa\u0001\u001b\t\u0003\u0019)\u0001C\u0004\u00040i!\ta!\r\t\u000f\r\r#\u0004\"\u0001\u0004F!91q\u000b\u000e\u0005\u0002\re\u0003bBB35\u0019\u00051q\r\u0005\b\u0007[Rb\u0011AB8\u0011\u001d\u00199H\u0007C\u0001\u0007sBqa!%\u001b\t\u0003\u0019\u0019\nC\u0004\u0004\u001aj!\taa'\t\u000f\r}&\u0004\"\u0001\u0004B\"91Q\u001d\u000e\u0005\u0002\r\u001d\bbBBz5\u0011\u00051Q\u001f\u0005\b\u0007wTb\u0011AB\u007f\u0011\u001d!\tA\u0007D\u0001\t\u0007Aq\u0001b\u0004\u001b\t\u0003!\t\u0002C\u0004\u0005\u001ci1\t\u0001\"\b\t\u000f\u0011\u001d\"\u0004\"\u0001\u0005*!9AQ\u0007\u000e\u0005\u0002\u0011]\u0002b\u0002C!5\u0019\u0005A1\t\u0005\b\t\u001bRB\u0011\u0001C(\u0011\u001d!IF\u0007C\u0001\t7Bq\u0001\"\u001a\u001b\t\u0003\"9\u0007C\u0004\u0005ji!\t\u0005b\u001b\t\u000f\u0011E$\u0004\"\u0011\u0005t\u0005Q\u0001k\u001c7z]>l\u0017.\u00197\u000b\u0005%S\u0015\u0001B7bi\"T\u0011aS\u0001\u0006gBL'/Z\u0002\u0001!\tq\u0015!D\u0001I\u0005)\u0001v\u000e\\=o_6L\u0017\r\\\n\u0004\u0003E;\u0006C\u0001*V\u001b\u0005\u0019&\"\u0001+\u0002\u000bM\u001c\u0017\r\\1\n\u0005Y\u001b&AB!osJ+g\r\u0005\u0002O1&\u0011\u0011\f\u0013\u0002\u0014!>d\u0017P\\8nS\u0006d\u0017J\\:uC:\u001cWm]\u0001\u0007y%t\u0017\u000e\u001e \u0015\u00035\u000bQ\u0001Z3og\u0016,\"A\u00185\u0015\u0007}\u000bI\u0004\u0006\u0004a\u007f\u0006u\u0011q\u0005\t\u0004C\u00124W\"\u00012\u000b\u0005\rD\u0015\u0001\u00029pYfL!!\u001a2\u0003\u0013A{G.\u001f#f]N,\u0007CA4i\u0019\u0001!\u0011\"[\u0002!\u0002\u0003\u0005)\u0019\u00016\u0003\u0003\r\u000b\"a\u001b8\u0011\u0005Ic\u0017BA7T\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AU8\n\u0005A\u001c&aA!os\"\u001a\u0001N];\u0011\u0005I\u001b\u0018B\u0001;T\u0005-\u0019\b/Z2jC2L'0\u001a32\u000b\r2x/\u001f=\u000f\u0005I;\u0018B\u0001=T\u0003\u0019!u.\u001e2mKF\"AE\u001f@U\u001d\tYh0D\u0001}\u0015\tiH*\u0001\u0004=e>|GOP\u0005\u0002)\"I\u0011\u0011A\u0002\u0002\u0002\u0003\u000f\u00111A\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004#BA\u0003\u0003/1g\u0002BA\u0004\u0003#qA!!\u0003\u0002\u000e9\u001910a\u0003\n\u0003-K1!a\u0004K\u0003\u001d\tGnZ3ce\u0006LA!a\u0005\u0002\u0016\u00059\u0001/Y2lC\u001e,'bAA\b\u0015&!\u0011\u0011DA\u000e\u0005!\u0019V-\\5sS:<'\u0002BA\n\u0003+A\u0011\"a\b\u0004\u0003\u0003\u0005\u001d!!\t\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0003\u0002\u0006\u0005\rb-\u0003\u0003\u0002&\u0005m!AA#r\u0011%\tIcAA\u0001\u0002\b\tY#\u0001\u0006fm&$WM\\2fIM\u0002R!!\f\u00024\u0019tA!a\f\u000225\t!*C\u0002\u0002\u0014)KA!!\u000e\u00028\tA1\t\\1tgR\u000bwMC\u0002\u0002\u0014)Cq!a\u000f\u0004\u0001\u0004\ti$\u0001\u0004d_\u00164gm\u001d\t\u0005%\u0006}b-C\u0002\u0002BM\u0013Q!\u0011:sCf\faa\u001d9beN,W\u0003BA$\u0003'\"B!!\u0013\u0002nQA\u00111JA.\u0003C\n9\u0007E\u0003b\u0003\u001b\n\t&C\u0002\u0002P\t\u0014!\u0002U8msN\u0003\u0018M]:f!\r9\u00171\u000b\u0003\nS\u0012\u0001\u000b\u0011!AC\u0002)DS!a\u0015s\u0003/\nda\t<x\u00033B\u0018\u0007\u0002\u0013{}RC\u0011\"!\u0018\u0005\u0003\u0003\u0005\u001d!a\u0018\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$C\u0007\u0005\u0004\u0002\u0006\u0005]\u0011\u0011\u000b\u0005\n\u0003G\"\u0011\u0011!a\u0002\u0003K\n!\"\u001a<jI\u0016t7-\u001a\u00136!\u0019\t)!a\t\u0002R!I\u0011\u0011\u000e\u0003\u0002\u0002\u0003\u000f\u00111N\u0001\u000bKZLG-\u001a8dK\u00122\u0004CBA\u0017\u0003g\t\t\u0006C\u0004\u0002p\u0011\u0001\r!!\u001d\u0002\t\u0011\fG/\u0019\t\t\u0003g\nY(!!\u0002R9!\u0011QOA<!\tY8+C\u0002\u0002zM\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA?\u0003\u007f\u00121!T1q\u0015\r\tIh\u0015\t\u0004%\u0006\r\u0015bAAC'\n\u0019\u0011J\u001c;\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\t\u0005-\u00151\u0013\u000b\u0005\u0003\u001b\u000bi\u000b\u0006\u0005\u0002\u0010\u0006m\u0015\u0011UAT!\u0015\t\u0017QJAI!\r9\u00171\u0013\u0003\nS\u0016\u0001\u000b\u0011!AC\u0002)DS!a%s\u0003/\u000bda\t<x\u00033C\u0018\u0007\u0002\u0013{}RC\u0011\"!(\u0006\u0003\u0003\u0005\u001d!a(\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007\u0005\u0004\u0002\u0006\u0005]\u0011\u0011\u0013\u0005\n\u0003G+\u0011\u0011!a\u0002\u0003K\u000b!\"\u001a<jI\u0016t7-\u001a\u00139!\u0019\t)!a\t\u0002\u0012\"I\u0011\u0011V\u0003\u0002\u0002\u0003\u000f\u00111V\u0001\u000bKZLG-\u001a8dK\u0012J\u0004CBA\u0017\u0003g\t\t\nC\u0004\u0002p\u0015\u0001\r!a,\u0011\u0011\u0005M\u00141PAA\u0003#+B!a-\u0002<R!\u0011QWAk)!\t9,a1\u0002J\u0006=\u0007#B1\u0002N\u0005e\u0006cA4\u0002<\u0012I\u0011N\u0002Q\u0001\u0002\u0003\u0015\rA\u001b\u0015\u0006\u0003w\u0013\u0018qX\u0019\u0007GY<\u0018\u0011\u0019=2\t\u0011Rh\u0010\u0016\u0005\n\u0003\u000b4\u0011\u0011!a\u0002\u0003\u000f\f1\"\u001a<jI\u0016t7-\u001a\u00132aA1\u0011QAA\f\u0003sC\u0011\"a3\u0007\u0003\u0003\u0005\u001d!!4\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\r\t\u0007\u0003\u000b\t\u0019#!/\t\u0013\u0005Eg!!AA\u0004\u0005M\u0017aC3wS\u0012,gnY3%cI\u0002b!!\f\u00024\u0005e\u0006bBAl\r\u0001\u0007\u0011\u0011\\\u0001\u0006i\u0016\u0014Xn\u001d\t\u0007\u00037\fy.!:\u000f\u0007i\fi.C\u0002\u0002\u0014MKA!!9\u0002d\na\u0011\n^3sC\ndWm\u00148dK*\u0019\u00111C*\u0011\u000b\u0005\f9/!/\n\u0007\u0005%(M\u0001\u0003UKJlW\u0003BAw\u0003k$b!a<\u0003\u0010\tMA\u0003CAy\u0003{\u0014\u0019A!\u0003\u0011\u000b\u0005\fi%a=\u0011\u0007\u001d\f)\u0010B\u0005j\u000f\u0001\u0006\t\u0011!b\u0001U\"*\u0011Q\u001f:\u0002zF21E^<\u0002|b\fD\u0001\n>\u007f)\"I\u0011q`\u0004\u0002\u0002\u0003\u000f!\u0011A\u0001\fKZLG-\u001a8dK\u0012\n4\u0007\u0005\u0004\u0002\u0006\u0005]\u00111\u001f\u0005\n\u0005\u000b9\u0011\u0011!a\u0002\u0005\u000f\t1\"\u001a<jI\u0016t7-\u001a\u00132iA1\u0011QAA\u0012\u0003gD\u0011Ba\u0003\b\u0003\u0003\u0005\u001dA!\u0004\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$\u0013'\u000e\t\u0007\u0003[\t\u0019$a=\t\u000f\tEq\u00011\u0001\u0002t\u0006\t1\rC\u0004\u0003\u0016\u001d\u0001\r!!!\u0002\u0003\u0015$BA!\u0007\u0005\u0002B!aJ\u0007C>+\u0011\u0011iB!\r\u0014\u0005i\t\u0016A\u0002\u0013j]&$H\u0005\u0006\u0002\u0003$A\u0019!K!\n\n\u0007\t\u001d2K\u0001\u0003V]&$\u0018AA2u+\t\u0011i\u0003\u0005\u0004\u0002.\u0005M\"q\u0006\t\u0004O\nEB!C5\u001bA\u0003\u0005\tQ1\u0001kQ\u0015\u0011\tD\u001dB\u001bc\u0019\u0019co\u001eB\u001cqF\"AE\u001f@U\u0003\u001d!x\u000eR3og\u0016$bA!\u0010\u0003@\t\u0015\u0003\u0003B1e\u0005_AqA!\u0011\u001e\u0001\b\u0011\u0019%\u0001\u0003sS:<\u0007CBA\u0003\u0003/\u0011y\u0003C\u0004\u0003Hu\u0001\u001dA!\u0013\u0002\u0005\u0015\f\bCBA\u0003\u0003G\u0011y#\u0001\u0005u_N\u0003\u0018M]:f)\u0019\u0011yE!\u0015\u0003TA)\u0011-!\u0014\u00030!9!\u0011\t\u0010A\u0004\t\r\u0003b\u0002B$=\u0001\u000f!\u0011J\u0001\bM>\u0014X-Y2i+\u0011\u0011IFa\u001a\u0015\t\t\r\"1\f\u0005\b\u0005;z\u0002\u0019\u0001B0\u0003\u00051\u0007#\u0003*\u0003b\u0005\u0005%q\u0006B3\u0013\r\u0011\u0019g\u0015\u0002\n\rVt7\r^5p]J\u00022a\u001aB4\t\u0019\u0011Ig\bb\u0001U\n\tQ+\u0001\bg_J,\u0017m\u00195O_:TVM]8\u0016\t\t=$Q\u0010\u000b\u0005\u0005c\u00129\b\u0006\u0004\u0003$\tM$Q\u000f\u0005\b\u0005\u0003\u0002\u00039\u0001B\"\u0011\u001d\u00119\u0005\ta\u0002\u0005\u0013BqA!\u0018!\u0001\u0004\u0011I\bE\u0005S\u0005C\n\tIa\f\u0003|A\u0019qM! \u0005\r\t%\u0004E1\u0001k\u0003-\u0019w.\u001a4gg\u0006\u0013(/Y=\u0015\t\t\r%Q\u0011\t\u0006%\u0006}\"q\u0006\u0005\b\u0005\u0003\n\u00039\u0001B\")\u0019\u0011II!%\u0003\u0014B1\u00111\u001cBF\u0005\u001fKAA!$\u0002d\n!A*[:u!\u0015\t\u0017q\u001dB\u0018\u0011\u001d\u0011\tE\ta\u0002\u0005\u0007BqAa\u0012#\u0001\b\u0011I%A\u0007uKJl7/\u0013;fe\u0006$xN]\u000b\u0003\u00053\u0003b!a7\u0003\u001c\n=\u0015\u0002\u0002BO\u0003G\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u000b\u0007\u0005C\u0013\u0019K!*\u0011\u0011\u0005M\u00141PAA\u0005_AqA!\u0011%\u0001\b\u0011\u0019\u0005C\u0004\u0003H\u0011\u0002\u001dA!\u0013\u0002\u000bI|w\u000e^:\u0015\t\t-&\u0011\u0017\t\u0006C\n5&qF\u0005\u0004\u0005_\u0013'!\u0002*p_R\u001c\bb\u0002BZK\u0001\u000f!QW\u0001\u0007M&tG-\u001a:\u0011\u000b\u0005\u00149La\f\n\u0007\te&M\u0001\u0006S_>$h)\u001b8eKJ\f1A\u001c;i)\u0011\u0011yLa1\u0015\t\t=\"\u0011\u0019\u0005\b\u0005\u00032\u00039\u0001B\"\u0011\u001d\u0011)M\na\u0001\u0003\u0003\u000b\u0011A\\\u0001\b[\u0006DH+\u001a:n)\u0011\u0011yIa3\t\u000f\t\u0005s\u0005q\u0001\u0003D\u00059Q.\u001b8UKJlGC\u0002BH\u0005#\u0014\u0019\u000eC\u0004\u0003B!\u0002\u001dAa\u0011\t\u000f\t\u001d\u0003\u0006q\u0001\u0003J\u0005Q\u0011n]\"p]N$\u0018M\u001c;\u0016\u0005\te\u0007c\u0001*\u0003\\&\u0019!Q\\*\u0003\u000f\t{w\u000e\\3b]\u00061A-Z4sK\u0016,\"!!!\u0002#5\f\u0007p\u0014:eKJ$VM]7D_\u00164g\r\u0006\u0003\u00030\t\u001d\bb\u0002B!W\u0001\u000f!1I\u0001\te\u0016$Wo\u0019;v[RA!Q\u001eBx\u0005c\u0014\u0019\u0010\u0005\u0003O5\t=\u0002b\u0002B\u000bY\u0001\u000f!\u0011\n\u0005\b\u0005\u0003b\u00039\u0001B\"\u0011\u001d\u0011I\u0003\fa\u0002\u0005[\ta![:[KJ|G\u0003\u0002B}\u0005\u007f$BAa\f\u0003|\"9!Q \u0018A\u0004\t\r\u0013!\u0001:\t\u000f\r\u0005a\u00061\u0001\u00030\u0005\t\u00010\u0001\u0005fm\u0006dw+\u001b;i+\u0011\u00199aa\u0004\u0015\t\r%1Q\u0006\u000b\u0005\u0007\u0017\u0019)\u0003\u0006\u0005\u0004\u000e\rM1\u0011DB\u0010!\r97q\u0002\u0003\u0007\u0007#y#\u0019\u00016\u0003\u0003\u0005C\u0011b!\u00060\u0003\u0003\u0005\u001daa\u0006\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$SG\u000e\t\u0007\u0003\u000b\t9b!\u0004\t\u0013\rmq&!AA\u0004\ru\u0011aC3wS\u0012,gnY3%k]\u0002b!!\u0002\u0002$\r5\u0001\"CB\u0011_\u0005\u0005\t9AB\u0012\u0003-)g/\u001b3f]\u000e,G%\u000e\u001d\u0011\r\u00055\u00121GB\u0007\u0011\u001d\u0011if\fa\u0001\u0007O\u0001rAUB\u0015\u0005_\u0019i!C\u0002\u0004,M\u0013\u0011BR;oGRLwN\\\u0019\t\u000f\r\u0005q\u00061\u0001\u0004\u000e\u000591m\\7q_N,G\u0003BB\u001a\u0007\u007f!bA!<\u00046\ru\u0002b\u0002B!a\u0001\u000f1q\u0007\t\u0007\u0003\u000b\u0019IDa\f\n\t\rm\u00121\u0004\u0002\u0004%&<\u0007b\u0002B$a\u0001\u000f!\u0011\n\u0005\b\u0007\u0003\u0002\u0004\u0019\u0001Bw\u0003\u0005I\u0018!B:iS\u001a$H\u0003BB$\u0007'\"bA!<\u0004J\rE\u0003b\u0002B!c\u0001\u000f11\n\t\u0007\u0003\u000b\u0019iEa\f\n\t\r=\u00131\u0004\u0002\u0005%&tw\rC\u0004\u0003HE\u0002\u001dA!\u0013\t\u000f\rU\u0013\u00071\u0001\u00030\u0005\t\u0001.A\u0003n_:L7\r\u0006\u0004\u0003n\u000em31\r\u0005\b\u0005;\u0012\u00049AB/!\u0019\t)aa\u0018\u00030%!1\u0011MA\u000e\u0005\u00151\u0015.\u001a7e\u0011\u001d\u00119E\ra\u0002\u0005\u0013\n!\u0002Z3sSZ\fG/\u001b<f)\u0019\u0011io!\u001b\u0004l!9!\u0011I\u001aA\u0004\r-\u0003b\u0002B$g\u0001\u000f!\u0011J\u0001\tS:$Xm\u001a:bYR1!Q^B9\u0007kBqaa\u001d5\u0001\b\u0019i&A\u0003gS\u0016dG\rC\u0004\u0003HQ\u0002\u001dA!\u0013\u0002\u001dMLwM\u001c,be&\fG/[8ogRA\u0011\u0011QB>\u0007{\u001a9\tC\u0004\u0003BU\u0002\u001dAa\u0011\t\u000f\r}T\u0007q\u0001\u0004\u0002\u0006)qN\u001d3feB1\u0011QABB\u0005_IAa!\"\u0002\u001c\t)qJ\u001d3fe\"91\u0011R\u001bA\u0004\r-\u0015AB:jO:,G\r\u0005\u0004\u0002\u0006\r5%qF\u0005\u0005\u0007\u001f\u000bYB\u0001\u0004TS\u001etW\rZ\u0001\u0010e\u0016lwN^3[KJ|'k\\8ugR1!Q^BK\u0007/CqA!\u00117\u0001\b\u0011\u0019\u0005C\u0004\u0003HY\u0002\u001dA!\u0013\u0002\u00075\f\u0007/\u0006\u0003\u0004\u001e\u000e\u0015F\u0003BBP\u0007w#\u0002b!)\u0004*\u000e=6Q\u0017\t\u0005\u001dj\u0019\u0019\u000bE\u0002h\u0007K#aaa*8\u0005\u0004Q'!\u0001#\t\u0013\r-v'!AA\u0004\r5\u0016aC3wS\u0012,gnY3%ke\u0002b!!\u0002\u0002\u0018\r\r\u0006\"CBYo\u0005\u0005\t9ABZ\u0003-)g/\u001b3f]\u000e,GE\u000e\u0019\u0011\r\u0005\u0015\u00111EBR\u0011%\u00199lNA\u0001\u0002\b\u0019I,A\u0006fm&$WM\\2fIY\n\u0004CBA\u0017\u0003g\u0019\u0019\u000bC\u0004\u0003^]\u0002\ra!0\u0011\u000fI\u001bICa\f\u0004$\u0006AQ.\u00199UKJl7/\u0006\u0003\u0004D\u000e-G\u0003BBc\u0007?$\u0002ba2\u0004N\u000eM7\u0011\u001c\t\u0005\u001dj\u0019I\rE\u0002h\u0007\u0017$aaa*9\u0005\u0004Q\u0007\"CBhq\u0005\u0005\t9ABi\u0003-)g/\u001b3f]\u000e,GE\u000e\u001a\u0011\r\u0005\u0015\u0011qCBe\u0011%\u0019)\u000eOA\u0001\u0002\b\u00199.A\u0006fm&$WM\\2fIY\u001a\u0004CBA\u0003\u0003G\u0019I\rC\u0005\u0004\\b\n\t\u0011q\u0001\u0004^\u0006YQM^5eK:\u001cW\r\n\u001c5!\u0019\ti#a\r\u0004J\"9!Q\f\u001dA\u0002\r\u0005\bc\u0002*\u0004*\t=51\u001d\t\u0006C\u0006\u001d8\u0011Z\u0001\u0005M2L\u0007\u000f\u0006\u0004\u0003n\u000e%8\u0011\u001f\u0005\b\u0005\u0003J\u00049ABv!\u0019\t)a!<\u00030%!1q^A\u000e\u0005\r\u0011fn\u001a\u0005\b\u0005\u000fJ\u00049\u0001B%\u0003)\u0011XmY5qe>\u001c\u0017\r\u001c\u000b\u0007\u0005[\u001c9p!?\t\u000f\t\u0005#\bq\u0001\u0003D!9!q\t\u001eA\u0004\t%\u0013\u0001D;oCJLx\fJ7j]V\u001cH\u0003\u0002Bw\u0007\u007fDqA!\u0011<\u0001\b\u0019Y/A\u0003%a2,8\u000f\u0006\u0003\u0005\u0006\u0011-AC\u0002Bw\t\u000f!I\u0001C\u0004\u0003Bq\u0002\u001dAa\u0011\t\u000f\t\u001dC\bq\u0001\u0003J!9AQ\u0002\u001fA\u0002\t5\u0018a\u0001:ig\u00061A%\\5okN$B\u0001b\u0005\u0005\u001aQ1!Q\u001eC\u000b\t/AqA!\u0011>\u0001\b\u0019Y\u000fC\u0004\u0003Hu\u0002\u001dA!\u0013\t\u000f\u00115Q\b1\u0001\u0003n\u00061A\u0005^5nKN$B\u0001b\b\u0005&Q1!Q\u001eC\u0011\tGAqA!\u0011?\u0001\b\u0011\u0019\u0005C\u0004\u0003Hy\u0002\u001dA!\u0013\t\u000f\u00115a\b1\u0001\u0003n\u0006aA\u0005^5nKN$C/[7fgR!A1\u0006C\u0019)\u0019\u0011i\u000f\"\f\u00050!9!\u0011I A\u0004\r]\u0002b\u0002B$\u007f\u0001\u000f!\u0011\n\u0005\b\tgy\u0004\u0019AAA\u0003\u0005Y\u0017a\u00019poR!A\u0011\bC )\u0019\u0011i\u000fb\u000f\u0005>!9!\u0011\t!A\u0004\r]\u0002b\u0002B$\u0001\u0002\u000f!\u0011\n\u0005\b\tg\u0001\u0005\u0019AAA\u00031!C/[7fg\u0012\u001aw\u000e\\8o)\u0011!)\u0005b\u0013\u0015\r\t5Hq\tC%\u0011\u001d\u0011\t%\u0011a\u0002\u0005\u0007BqAa\u0012B\u0001\b\u0011I\u0005C\u0004\u00054\u0005\u0003\rAa\f\u0002\u0019\u0011\u001aw\u000e\\8oIQLW.Z:\u0015\t\u0011ECq\u000b\u000b\u0007\u0005[$\u0019\u0006\"\u0016\t\u000f\t\u0005#\tq\u0001\u0003D!9!q\t\"A\u0004\t%\u0003b\u0002C\u001a\u0005\u0002\u0007!qF\u0001\u000bI\r|Gn\u001c8%I&4H\u0003\u0002C/\tG\"bA!<\u0005`\u0011\u0005\u0004bBB:\u0007\u0002\u000f1Q\f\u0005\b\u0005\u000f\u001a\u00059\u0001B%\u0011\u001d!\u0019d\u0011a\u0001\u0005_\t\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0003\u0003\u0003\u000ba!Z9vC2\u001cH\u0003\u0002Bm\t[Ba\u0001b\u001cF\u0001\u0004q\u0017\u0001\u0002;iCR\f\u0001\u0002^8TiJLgn\u001a\u000b\u0003\tk\u0002B!a\u001d\u0005x%!A\u0011PA@\u0005\u0019\u0019FO]5oOB\u0019a\n\" \n\u0007\u0011}\u0004J\u0001\u0005SCRLwN\\1m\u0011\u001d!\u0019\t\u0003a\u0001\tk\n\u0011a]\u0001\u0005u\u0016\u0014x.\u0006\u0003\u0005\n\u0012=E\u0003\u0003CF\t/#i\nb)\u0011\t9SBQ\u0012\t\u0004O\u0012=E!C5\nA\u0003\u0005\tQ1\u0001kQ\u0015!yI\u001dCJc\u0019\u0019co\u001eCKqF\"AE\u001f@U\u0011%!I*CA\u0001\u0002\b!Y*A\u0006fm&$WM\\2fIE2\u0004CBA\u0003\u0003G!i\tC\u0005\u0005 &\t\t\u0011q\u0001\u0005\"\u0006YQM^5eK:\u001cW\rJ\u00198!\u0019\t)!a\u0006\u0005\u000e\"IAQU\u0005\u0002\u0002\u0003\u000fAqU\u0001\fKZLG-\u001a8dK\u0012\n\u0004\b\u0005\u0004\u0002.\u0005MBQR\u0001\tG>t7\u000f^1oiV!AQ\u0016C[)\u0011!y\u000bb4\u0015\u0011\u0011EFQ\u0018Cb\t\u0013\u0004BA\u0014\u000e\u00054B\u0019q\r\".\u0005\u0013%T\u0001\u0015!A\u0001\u0006\u0004Q\u0007&\u0002C[e\u0012e\u0016GB\u0012wo\u0012m\u00060\r\u0003%uz$\u0006\"\u0003C`\u0015\u0005\u0005\t9\u0001Ca\u0003-)g/\u001b3f]\u000e,G%M\u001d\u0011\r\u0005\u0015\u00111\u0005CZ\u0011%!)MCA\u0001\u0002\b!9-A\u0006fm&$WM\\2fII\u0002\u0004CBA\u0003\u0003/!\u0019\fC\u0005\u0005L*\t\t\u0011q\u0001\u0005N\u0006YQM^5eK:\u001cW\r\n\u001a2!\u0019\ti#a\r\u00054\"9!\u0011\u0003\u0006A\u0002\u0011M\u0016A\u00027j]\u0016\f'/\u0006\u0003\u0005V\u0012uG\u0003\u0002Cl\to$\u0002\u0002\"7\u0005f\u0012-H\u0011\u001f\t\u0005\u001dj!Y\u000eE\u0002h\t;$\u0011\"[\u0006!\u0002\u0003\u0005)\u0019\u00016)\u000b\u0011u'\u000f\"92\r\r2x\u000fb9yc\u0011!#P +\t\u0013\u0011\u001d8\"!AA\u0004\u0011%\u0018aC3wS\u0012,gnY3%eI\u0002b!!\u0002\u0002$\u0011m\u0007\"\u0003Cw\u0017\u0005\u0005\t9\u0001Cx\u0003-)g/\u001b3f]\u000e,GEM\u001a\u0011\r\u0005\u0015\u0011q\u0003Cn\u0011%!\u0019pCA\u0001\u0002\b!)0A\u0006fm&$WM\\2fII\"\u0004CBA\u0017\u0003g!Y\u000eC\u0004\u0003\u0012-\u0001\r\u0001b7\u0016\t\u0011mX1\u0001\u000b\u0007\t{,i\"\"\t\u0015\u0011\u0011}X1BC\t\u000b/\u0001BA\u0014\u000e\u0006\u0002A\u0019q-b\u0001\u0005\u0013%d\u0001\u0015!A\u0001\u0006\u0004Q\u0007&BC\u0002e\u0016\u001d\u0011GB\u0012wo\u0016%\u00010\r\u0003%uz$\u0006\"CC\u0007\u0019\u0005\u0005\t9AC\b\u0003-)g/\u001b3f]\u000e,GEM\u001b\u0011\r\u0005\u0015\u00111EC\u0001\u0011%)\u0019\u0002DA\u0001\u0002\b))\"A\u0006fm&$WM\\2fII2\u0004CBA\u0003\u0003/)\t\u0001C\u0005\u0006\u001a1\t\t\u0011q\u0001\u0006\u001c\u0005YQM^5eK:\u001cW\r\n\u001a8!\u0019\ti#a\r\u0006\u0002!9Qq\u0004\u0007A\u0002\u0015\u0005\u0011AA22\u0011\u001d)\u0019\u0003\u0004a\u0001\u000b\u0003\t!a\u0019\u0019\u0002\u0013E,\u0018\r\u001a:bi&\u001cW\u0003BC\u0015\u000bc!b!b\u000b\u0006L\u00155C\u0003CC\u0017\u000bs)y$\"\u0012\u0011\t9SRq\u0006\t\u0004O\u0016EB!C5\u000eA\u0003\u0005\tQ1\u0001kQ\u0015)\tD]C\u001bc\u0019\u0019co^C\u001cqF\"AE\u001f@U\u0011%)Y$DA\u0001\u0002\b)i$A\u0006fm&$WM\\2fIIB\u0004CBA\u0003\u0003G)y\u0003C\u0005\u0006B5\t\t\u0011q\u0001\u0006D\u0005YQM^5eK:\u001cW\r\n\u001a:!\u0019\t)!a\u0006\u00060!IQqI\u0007\u0002\u0002\u0003\u000fQ\u0011J\u0001\fKZLG-\u001a8dK\u0012\u001a\u0004\u0007\u0005\u0004\u0002.\u0005MRq\u0006\u0005\b\u000b?i\u0001\u0019AC\u0018\u0011\u001d)\u0019#\u0004a\u0001\u000b_)B!\"\u0015\u0006ZQ!Q1KC:)!))&\"\u0019\u0006h\u00155\u0004\u0003\u0002(\u001b\u000b/\u00022aZC-\t%Ig\u0002)A\u0001\u0002\u000b\u0007!\u000eK\u0003\u0006ZI,i&\r\u0004$m^,y\u0006_\u0019\u0005IitH\u000bC\u0005\u0006d9\t\t\u0011q\u0001\u0006f\u0005YQM^5eK:\u001cW\rJ\u001a2!\u0019\t)!a\t\u0006X!IQ\u0011\u000e\b\u0002\u0002\u0003\u000fQ1N\u0001\fKZLG-\u001a8dK\u0012\u001a$\u0007\u0005\u0004\u0002\u0006\u0005]Qq\u000b\u0005\n\u000b_r\u0011\u0011!a\u0002\u000bc\n1\"\u001a<jI\u0016t7-\u001a\u00134gA1\u0011QFA\u001a\u000b/BqA!\u0005\u000f\u0001\u0004)9&\u0006\u0003\u0006x\u0015}D\u0003CC=\u000b3+i*b(\u0015\u0011\u0015mTqQCG\u000b'\u0003BA\u0014\u000e\u0006~A\u0019q-b \u0005\u0013%|\u0001\u0015!A\u0001\u0006\u0004Q\u0007&BC@e\u0016\r\u0015GB\u0012wo\u0016\u0015\u00050\r\u0003%uz$\u0006\"CCE\u001f\u0005\u0005\t9ACF\u0003-)g/\u001b3f]\u000e,Ge\r\u001b\u0011\r\u0005\u0015\u00111EC?\u0011%)yiDA\u0001\u0002\b)\t*A\u0006fm&$WM\\2fIM*\u0004CBA\u0003\u0003/)i\bC\u0005\u0006\u0016>\t\t\u0011q\u0001\u0006\u0018\u0006YQM^5eK:\u001cW\rJ\u001a7!\u0019\ti#a\r\u0006~!9Q1T\bA\u0002\u0015u\u0014AA23\u0011\u001d)yb\u0004a\u0001\u000b{Bq!b\t\u0010\u0001\u0004)i(A\u0003dk\nL7-\u0006\u0003\u0006&\u00165F\u0003BCT\u000b\u000f$\u0002\"\"+\u00066\u0016mV\u0011\u0019\t\u0005\u001dj)Y\u000bE\u0002h\u000b[#\u0011\"\u001b\t!\u0002\u0003\u0005)\u0019\u00016)\u000b\u00155&/\"-2\r\r2x/b-yc\u0011!#P +\t\u0013\u0015]\u0006#!AA\u0004\u0015e\u0016aC3wS\u0012,gnY3%g]\u0002b!!\u0002\u0002$\u0015-\u0006\"CC_!\u0005\u0005\t9AC`\u0003-)g/\u001b3f]\u000e,Ge\r\u001d\u0011\r\u0005\u0015\u0011qCCV\u0011%)\u0019\rEA\u0001\u0002\b))-A\u0006fm&$WM\\2fIMJ\u0004CBA\u0017\u0003g)Y\u000bC\u0004\u0003\u0012A\u0001\r!b+\u0016\t\u0015-W1\u001b\u000b\u000b\u000b\u001b,i/\"=\u0006t\u0016UH\u0003CCh\u000b7,\t/b:\u0011\t9SR\u0011\u001b\t\u0004O\u0016MG!C5\u0012A\u0003\u0005\tQ1\u0001kQ\u0015)\u0019N]Clc\u0019\u0019co^CmqF\"AE\u001f@U\u0011%)i.EA\u0001\u0002\b)y.A\u0006fm&$WM\\2fIQ\u0002\u0004CBA\u0003\u0003G)\t\u000eC\u0005\u0006dF\t\t\u0011q\u0001\u0006f\u0006YQM^5eK:\u001cW\r\n\u001b2!\u0019\t)!a\u0006\u0006R\"IQ\u0011^\t\u0002\u0002\u0003\u000fQ1^\u0001\fKZLG-\u001a8dK\u0012\"$\u0007\u0005\u0004\u0002.\u0005MR\u0011\u001b\u0005\b\u000b_\f\u0002\u0019ACi\u0003\t\u00197\u0007C\u0004\u0006\u001cF\u0001\r!\"5\t\u000f\u0015}\u0011\u00031\u0001\u0006R\"9Q1E\tA\u0002\u0015E\u0017aA8oKV!Q1 D\u0001)!)iP\"\u0003\u0007\u0010\u0019U\u0001\u0003\u0002(\u001b\u000b\u007f\u00042a\u001aD\u0001\t%I'\u0003)A\u0001\u0002\u000b\u0007!\u000eK\u0003\u0007\u0002I4)!\r\u0004$m^49\u0001_\u0019\u0005IitH\u000bC\u0005\u0007\fI\t\t\u0011q\u0001\u0007\u000e\u0005YQM^5eK:\u001cW\r\n\u001b4!\u0019\t)!a\t\u0006\u0000\"Ia\u0011\u0003\n\u0002\u0002\u0003\u000fa1C\u0001\fKZLG-\u001a8dK\u0012\"D\u0007\u0005\u0004\u0002\u0006\reRq \u0005\n\r/\u0011\u0012\u0011!a\u0002\r3\t1\"\u001a<jI\u0016t7-\u001a\u00135kA1\u0011QFA\u001a\u000b\u007f,BA\"\b\u0007$QAaq\u0004D\u0016\rc19\u0004\u0005\u0003O5\u0019\u0005\u0002cA4\u0007$\u0011I\u0011n\u0005Q\u0001\u0002\u0003\u0015\rA\u001b\u0015\u0006\rG\u0011hqE\u0019\u0007GY<h\u0011\u0006=2\t\u0011Rh\u0010\u0016\u0005\n\r[\u0019\u0012\u0011!a\u0002\r_\t1\"\u001a<jI\u0016t7-\u001a\u00135mA1\u0011QAA\u0012\rCA\u0011Bb\r\u0014\u0003\u0003\u0005\u001dA\"\u000e\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$Cg\u000e\t\u0007\u0003\u000b\u0019ID\"\t\t\u0013\u0019e2#!AA\u0004\u0019m\u0012aC3wS\u0012,gnY3%ia\u0002b!!\f\u00024\u0019\u0005\u0012\u0001\u0002;x_b,BA\"\u0011\u0007HQAa1\tD(\r+2Y\u0006\u0005\u0003O5\u0019\u0015\u0003cA4\u0007H\u0011I\u0011\u000e\u0006Q\u0001\u0002\u0003\u0015\rA\u001b\u0015\u0006\r\u000f\u0012h1J\u0019\u0007GY<hQ\n=2\t\u0011Rh\u0010\u0016\u0005\n\r#\"\u0012\u0011!a\u0002\r'\n1\"\u001a<jI\u0016t7-\u001a\u00135sA1\u0011QAA\u0012\r\u000bB\u0011Bb\u0016\u0015\u0003\u0003\u0005\u001dA\"\u0017\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$S\u0007\r\t\u0007\u0003\u000b\u0019ID\"\u0012\t\u0013\u0019uC#!AA\u0004\u0019}\u0013aC3wS\u0012,gnY3%kE\u0002b!!\f\u00024\u0019\u0015\u0013A\u0002;fe6\u0014V\r\u0005\u0003\u0007f\u0019=TB\u0001D4\u0015\u00111IGb\u001b\u0002\u00115\fGo\u00195j]\u001eT1A\"\u001cT\u0003\u0011)H/\u001b7\n\t\u0019Edq\r\u0002\u0006%\u0016<W\r_\u0001\u0007_B,'OU3\u0002\u000bA\f'o]3\u0015\t\tea\u0011\u0010\u0005\b\t\u0007;\u0002\u0019\u0001C;\u0003\u0015\u0019\b\u000f\\5u+\u00111yHb$\u0015\t\u0019\u0005eQ\u0014\u000b\u0005\r\u000739\nE\u0004S\r\u000b3IIb#\n\u0007\u0019\u001d5K\u0001\u0004UkBdWM\r\t\u0006%\u0006}\u0012\u0011\u0011\t\u0006%\u0006}bQ\u0012\t\u0004O\u001a=E!C5\u0019A\u0003\u0005\tQ1\u0001kQ\u00151yI\u001dDJc\u0019\u0019co\u001eDKqF\"AE\u001f@U\u0011%1I\nGA\u0001\u0002\b1Y*A\u0006fm&$WM\\2fIU\u0012\u0004CBA\u0017\u0003g1i\t\u0003\u0004d1\u0001\u0007aq\u0014\t\u0005\u001dj1i)A\u0006j]R,'\u000f]8mCR,W\u0003\u0002DS\r[#BAb*\u0007BRAa\u0011\u0016DX\rk3Y\f\u0005\u0003O5\u0019-\u0006cA4\u0007.\u0012)\u0011.\u0007b\u0001U\"Ia\u0011W\r\u0002\u0002\u0003\u000fa1W\u0001\fKZLG-\u001a8dK\u0012*4\u0007\u0005\u0004\u0002\u0006\r}c1\u0016\u0005\n\roK\u0012\u0011!a\u0002\rs\u000b1\"\u001a<jI\u0016t7-\u001a\u00136iA1\u0011QAA\u0012\rWC\u0011B\"0\u001a\u0003\u0003\u0005\u001dAb0\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$S'\u000e\t\u0007\u0003[\t\u0019Db+\t\u000f\u0019\r\u0017\u00041\u0001\u0007F\u00061\u0001o\\5oiN\u0004RA\u0015Dd\r\u0017L1A\"3T\u0005)a$/\u001a9fCR,GM\u0010\t\b%\u001a\u0015e1\u0016DV\u0001"
)
public interface Polynomial {
   static Polynomial interpolate(final Seq points, final Field evidence$53, final Eq evidence$54, final ClassTag evidence$55) {
      return Polynomial$.MODULE$.interpolate(points, evidence$53, evidence$54, evidence$55);
   }

   static Polynomial twox(final Eq evidence$49, final Rig evidence$50, final ClassTag evidence$51) {
      return Polynomial$.MODULE$.twox(evidence$49, evidence$50, evidence$51);
   }

   static Polynomial x(final Eq evidence$46, final Rig evidence$47, final ClassTag evidence$48) {
      return Polynomial$.MODULE$.x(evidence$46, evidence$47, evidence$48);
   }

   static Polynomial one(final Eq evidence$43, final Rig evidence$44, final ClassTag evidence$45) {
      return Polynomial$.MODULE$.one(evidence$43, evidence$44, evidence$45);
   }

   static Polynomial cubic(final Object c3, final Object c2, final Object c1, final Object c0, final Eq evidence$40, final Semiring evidence$41, final ClassTag evidence$42) {
      return Polynomial$.MODULE$.cubic(c3, c2, c1, c0, evidence$40, evidence$41, evidence$42);
   }

   static Polynomial cubic(final Object c, final Eq evidence$37, final Semiring evidence$38, final ClassTag evidence$39) {
      return Polynomial$.MODULE$.cubic(c, evidence$37, evidence$38, evidence$39);
   }

   static Polynomial quadratic(final Object c2, final Object c1, final Object c0, final Eq evidence$34, final Semiring evidence$35, final ClassTag evidence$36) {
      return Polynomial$.MODULE$.quadratic(c2, c1, c0, evidence$34, evidence$35, evidence$36);
   }

   static Polynomial quadratic(final Object c, final Eq evidence$31, final Semiring evidence$32, final ClassTag evidence$33) {
      return Polynomial$.MODULE$.quadratic(c, evidence$31, evidence$32, evidence$33);
   }

   static Polynomial quadratic(final Object c1, final Object c0, final Eq evidence$28, final Semiring evidence$29, final ClassTag evidence$30) {
      return Polynomial$.MODULE$.quadratic(c1, c0, evidence$28, evidence$29, evidence$30);
   }

   static Polynomial linear(final Object c1, final Object c0, final Eq evidence$25, final Semiring evidence$26, final ClassTag evidence$27) {
      return Polynomial$.MODULE$.linear(c1, c0, evidence$25, evidence$26, evidence$27);
   }

   static Polynomial linear(final Object c, final Eq evidence$22, final Semiring evidence$23, final ClassTag evidence$24) {
      return Polynomial$.MODULE$.linear(c, evidence$22, evidence$23, evidence$24);
   }

   static Polynomial constant(final Object c, final Eq evidence$19, final Semiring evidence$20, final ClassTag evidence$21) {
      return Polynomial$.MODULE$.constant(c, evidence$19, evidence$20, evidence$21);
   }

   static Polynomial zero(final Eq evidence$16, final Semiring evidence$17, final ClassTag evidence$18) {
      return Polynomial$.MODULE$.zero(evidence$16, evidence$17, evidence$18);
   }

   static PolySparse sparse(final Map data, final Semiring evidence$4, final Eq evidence$5, final ClassTag evidence$6) {
      return Polynomial$.MODULE$.sparse(data, evidence$4, evidence$5, evidence$6);
   }

   static PolyDense dense(final Object coeffs, final Semiring evidence$1, final Eq evidence$2, final ClassTag evidence$3) {
      return Polynomial$.MODULE$.dense(coeffs, evidence$1, evidence$2, evidence$3);
   }

   static PolynomialOverField overField(final ClassTag evidence$83, final Field evidence$84, final Eq evidence$85) {
      return Polynomial$.MODULE$.overField(evidence$83, evidence$84, evidence$85);
   }

   static PolynomialOverCRing overCRing(final ClassTag evidence$80, final CommutativeRing evidence$81, final Eq evidence$82) {
      return Polynomial$.MODULE$.overCRing(evidence$80, evidence$81, evidence$82);
   }

   static PolynomialOverRing overRing(final ClassTag evidence$77, final Ring evidence$78, final Eq evidence$79) {
      return Polynomial$.MODULE$.overRing(evidence$77, evidence$78, evidence$79);
   }

   static PolynomialOverRng overRng(final ClassTag evidence$74, final Rng evidence$75, final Eq evidence$76) {
      return Polynomial$.MODULE$.overRng(evidence$74, evidence$75, evidence$76);
   }

   static PolynomialOverRig overRig(final ClassTag evidence$71, final Rig evidence$72, final Eq evidence$73) {
      return Polynomial$.MODULE$.overRig(evidence$71, evidence$72, evidence$73);
   }

   static PolynomialOverSemiring overSemiring(final ClassTag evidence$65, final Semiring evidence$66, final Eq evidence$67) {
      return Polynomial$.MODULE$.overSemiring(evidence$65, evidence$66, evidence$67);
   }

   ClassTag ct();

   PolyDense toDense(final Semiring ring, final Eq eq);

   PolySparse toSparse(final Semiring ring, final Eq eq);

   void foreach(final Function2 f);

   // $FF: synthetic method
   static void foreachNonZero$(final Polynomial $this, final Function2 f, final Semiring ring, final Eq eq) {
      $this.foreachNonZero(f, ring, eq);
   }

   default void foreachNonZero(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreach((e, c) -> $anonfun$foreachNonZero$1(eq, ring, f, BoxesRunTime.unboxToInt(e), c));
   }

   Object coeffsArray(final Semiring ring);

   // $FF: synthetic method
   static List terms$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.terms(ring, eq);
   }

   default List terms(final Semiring ring, final Eq eq) {
      ListBuffer lb = new ListBuffer();
      this.foreachNonZero((e, c) -> $anonfun$terms$1(lb, BoxesRunTime.unboxToInt(e), c), ring, eq);
      return lb.result();
   }

   Iterator termsIterator();

   // $FF: synthetic method
   static Map data$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.data(ring, eq);
   }

   default Map data(final Semiring ring, final Eq eq) {
      Builder bldr = .MODULE$.newBuilder();
      this.foreachNonZero((e, c) -> $anonfun$data$1(bldr, BoxesRunTime.unboxToInt(e), c), ring, eq);
      return ((IterableOnceOps)bldr.result()).toMap(scala..less.colon.less..MODULE$.refl());
   }

   // $FF: synthetic method
   static Roots roots$(final Polynomial $this, final RootFinder finder) {
      return $this.roots(finder);
   }

   default Roots roots(final RootFinder finder) {
      return finder.findRoots(this);
   }

   Object nth(final int n, final Semiring ring);

   // $FF: synthetic method
   static Term maxTerm$(final Polynomial $this, final Semiring ring) {
      return $this.maxTerm(ring);
   }

   default Term maxTerm(final Semiring ring) {
      return new Term(this.maxOrderTermCoeff(ring), this.degree());
   }

   // $FF: synthetic method
   static Term minTerm$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.minTerm(ring, eq);
   }

   default Term minTerm(final Semiring ring, final Eq eq) {
      Object var3 = new Object();

      Term var10000;
      try {
         this.foreachNonZero((n, c) -> $anonfun$minTerm$1(var3, BoxesRunTime.unboxToInt(n), c), ring, eq);
         var10000 = new Term(ring.zero(), 0);
      } catch (NonLocalReturnControl var5) {
         if (var5.key() != var3) {
            throw var5;
         }

         var10000 = (Term)var5.value();
      }

      return var10000;
   }

   // $FF: synthetic method
   static boolean isConstant$(final Polynomial $this) {
      return $this.isConstant();
   }

   default boolean isConstant() {
      return this.degree() == 0;
   }

   int degree();

   Object maxOrderTermCoeff(final Semiring ring);

   Polynomial reductum(final Eq e, final Semiring ring, final ClassTag ct);

   boolean isZero();

   Object apply(final Object x, final Semiring r);

   // $FF: synthetic method
   static Object evalWith$(final Polynomial $this, final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return $this.evalWith(x, f, evidence$56, evidence$57, evidence$58);
   }

   default Object evalWith(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return this.map(f, evidence$56, evidence$57, evidence$58).apply(x, evidence$56);
   }

   // $FF: synthetic method
   static Polynomial compose$(final Polynomial $this, final Polynomial y, final Rig ring, final Eq eq) {
      return $this.compose(y, ring, eq);
   }

   default Polynomial compose(final Polynomial y, final Rig ring, final Eq eq) {
      ObjectRef polynomial = ObjectRef.create(Polynomial$.MODULE$.zero(eq, ring, this.ct()));
      this.foreachNonZero((e, c) -> {
         $anonfun$compose$1(y, ring, eq, polynomial, BoxesRunTime.unboxToInt(e), c);
         return BoxedUnit.UNIT;
      }, ring, eq);
      return (Polynomial)polynomial.elem;
   }

   // $FF: synthetic method
   static Polynomial shift$(final Polynomial $this, final Object h, final Ring ring, final Eq eq) {
      return $this.shift(h, ring, eq);
   }

   default Polynomial shift(final Object h, final Ring ring, final Eq eq) {
      Object coeffs = scala.runtime.ScalaRunTime..MODULE$.array_clone(this.coeffsArray(ring));
      this.foreachNonZero((deg, c) -> {
         $anonfun$shift$1(this, ring, h, coeffs, BoxesRunTime.unboxToInt(deg), c);
         return BoxedUnit.UNIT;
      }, ring, eq);
      return Polynomial$.MODULE$.dense(coeffs, ring, eq, this.ct());
   }

   // $FF: synthetic method
   static Polynomial monic$(final Polynomial $this, final Field f, final Eq eq) {
      return $this.monic(f, eq);
   }

   default Polynomial monic(final Field f, final Eq eq) {
      return this.$colon$div(this.maxOrderTermCoeff(f), f, eq);
   }

   Polynomial derivative(final Ring ring, final Eq eq);

   Polynomial integral(final Field field, final Eq eq);

   // $FF: synthetic method
   static int signVariations$(final Polynomial $this, final Semiring ring, final Order order, final Signed signed) {
      return $this.signVariations(ring, order, signed);
   }

   default int signVariations(final Semiring ring, final Order order, final Signed signed) {
      ObjectRef prevSign = ObjectRef.create(algebra.ring.Signed.Zero..MODULE$);
      IntRef variations = IntRef.create(0);
      this.foreachNonZero((x$5, c) -> {
         $anonfun$signVariations$1(signed, prevSign, variations, BoxesRunTime.unboxToInt(x$5), c);
         return BoxedUnit.UNIT;
      }, ring, order);
      return variations.elem;
   }

   // $FF: synthetic method
   static Polynomial removeZeroRoots$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.removeZeroRoots(ring, eq);
   }

   default Polynomial removeZeroRoots(final Semiring ring, final Eq eq) {
      Term var5 = this.minTerm(ring, eq);
      if (var5 != null) {
         int k = var5.exp();
         return this.mapTerms((x0$1) -> {
            if (x0$1 != null) {
               Object c = x0$1.coeff();
               int n = x0$1.exp();
               Term var2 = new Term(c, n - k);
               return var2;
            } else {
               throw new MatchError(x0$1);
            }
         }, ring, eq, this.ct());
      } else {
         throw new MatchError(var5);
      }
   }

   // $FF: synthetic method
   static Polynomial map$(final Polynomial $this, final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return $this.map(f, evidence$59, evidence$60, evidence$61);
   }

   default Polynomial map(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return this.mapTerms((x0$1) -> {
         if (x0$1 != null) {
            Object c = x0$1.coeff();
            int n = x0$1.exp();
            Term var2 = new Term(f.apply(c), n);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, evidence$59, evidence$60, evidence$61);
   }

   // $FF: synthetic method
   static Polynomial mapTerms$(final Polynomial $this, final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return $this.mapTerms(f, evidence$62, evidence$63, evidence$64);
   }

   default Polynomial mapTerms(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return Polynomial$.MODULE$.apply((IterableOnce)this.termsIterator().map(f), evidence$62, evidence$63, evidence$64);
   }

   // $FF: synthetic method
   static Polynomial flip$(final Polynomial $this, final Rng ring, final Eq eq) {
      return $this.flip(ring, eq);
   }

   default Polynomial flip(final Rng ring, final Eq eq) {
      return this.mapTerms((x0$1) -> {
         if (x0$1 != null) {
            Object coeff = x0$1.coeff();
            int exp = x0$1.exp();
            Term var2 = exp % 2 == 0 ? x0$1 : new Term(ring.negate(coeff), exp);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, ring, eq, this.ct());
   }

   // $FF: synthetic method
   static Polynomial reciprocal$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.reciprocal(ring, eq);
   }

   default Polynomial reciprocal(final Semiring ring, final Eq eq) {
      int d = this.degree();
      return this.mapTerms((x0$1) -> {
         if (x0$1 != null) {
            Object coeff = x0$1.coeff();
            int exp = x0$1.exp();
            Term var2 = new Term(coeff, d - exp);
            return var2;
         } else {
            throw new MatchError(x0$1);
         }
      }, ring, eq, this.ct());
   }

   Polynomial unary_$minus(final Rng ring);

   Polynomial $plus(final Polynomial rhs, final Semiring ring, final Eq eq);

   // $FF: synthetic method
   static Polynomial $minus$(final Polynomial $this, final Polynomial rhs, final Rng ring, final Eq eq) {
      return $this.$minus(rhs, ring, eq);
   }

   default Polynomial $minus(final Polynomial rhs, final Rng ring, final Eq eq) {
      return this.$plus(rhs.unary_$minus(ring), ring, eq);
   }

   Polynomial $times(final Polynomial rhs, final Semiring ring, final Eq eq);

   // $FF: synthetic method
   static Polynomial $times$times$(final Polynomial $this, final int k, final Rig ring, final Eq eq) {
      return $this.$times$times(k, ring, eq);
   }

   default Polynomial $times$times(final int k, final Rig ring, final Eq eq) {
      return this.pow(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial pow$(final Polynomial $this, final int k, final Rig ring, final Eq eq) {
      return $this.pow(k, ring, eq);
   }

   default Polynomial pow(final int k, final Rig ring, final Eq eq) {
      if (k < 0) {
         throw new IllegalArgumentException("negative exponent");
      } else {
         return k == 0 ? Polynomial$.MODULE$.one(eq, ring, this.ct()) : (k == 1 ? this : this.loop$3(this, k - 1, this, ring, eq));
      }
   }

   Polynomial $times$colon(final Object k, final Semiring ring, final Eq eq);

   // $FF: synthetic method
   static Polynomial $colon$times$(final Polynomial $this, final Object k, final Semiring ring, final Eq eq) {
      return $this.$colon$times(k, ring, eq);
   }

   default Polynomial $colon$times(final Object k, final Semiring ring, final Eq eq) {
      return this.$times$colon(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $colon$div$(final Polynomial $this, final Object k, final Field field, final Eq eq) {
      return $this.$colon$div(k, field, eq);
   }

   default Polynomial $colon$div(final Object k, final Field field, final Eq eq) {
      return this.$colon$times(field.reciprocal(k), field, eq);
   }

   // $FF: synthetic method
   static int hashCode$(final Polynomial $this) {
      return $this.hashCode();
   }

   default int hashCode() {
      Iterator it = this.termsIterator();
      return this.loop$4(0, it);
   }

   // $FF: synthetic method
   static boolean equals$(final Polynomial $this, final Object that) {
      return $this.equals(that);
   }

   default boolean equals(final Object that) {
      boolean var4 = false;
      Polynomial var5 = null;
      boolean var2;
      if (that instanceof Polynomial) {
         var4 = true;
         var5 = (Polynomial)that;
         if (this.degree() == var5.degree()) {
            Iterator it1 = this.termsIterator();
            Iterator it2 = var5.termsIterator();
            var2 = this.loop$5(it1, it2);
            return var2;
         }
      }

      if (var4) {
         var2 = false;
      } else if (this.isZero()) {
         var2 = BoxesRunTime.equals(that, BoxesRunTime.boxToInteger(0));
      } else if (this.degree() == 0) {
         Tuple2 var10 = Polynomial$.MODULE$.spire$math$Polynomial$$split(this, this.ct());
         if (var10 == null) {
            throw new MatchError(var10);
         }

         Object lcs = var10._2();
         var2 = BoxesRunTime.equals(scala.runtime.ScalaRunTime..MODULE$.array_apply(lcs, 0), that);
      } else {
         var2 = false;
      }

      return var2;
   }

   // $FF: synthetic method
   static String toString$(final Polynomial $this) {
      return $this.toString();
   }

   default String toString() {
      String var10000;
      if (this.isZero()) {
         var10000 = "(0)";
      } else {
         String var6;
         label20: {
            String s;
            label19: {
               ArrayBuilder bldr = spire.scalacompat.package$.MODULE$.arrayBuilderMake(scala.reflect.ClassTag..MODULE$.apply(Term.class));
               this.foreach((e, c) -> $anonfun$toString$1(bldr, BoxesRunTime.unboxToInt(e), c));
               Term[] ts = (Term[])bldr.result();
               QuickSort$.MODULE$.sort(ts, spire.algebra.package$.MODULE$.Order().reverse(spire.algebra.package$.MODULE$.Order().apply(Term$.MODULE$.ordering())), (ClassTag)scala.Predef..MODULE$.implicitly(scala.reflect.ClassTag..MODULE$.apply(Term.class)));
               s = scala.Predef..MODULE$.wrapRefArray(ts).mkString();
               var5 = (new StringBuilder(2)).append("(");
               var6 = scala.collection.StringOps..MODULE$.take$extension(scala.Predef..MODULE$.augmentString(s), 3);
               String var4 = " - ";
               if (var6 == null) {
                  if (var4 == null) {
                     break label19;
                  }
               } else if (var6.equals(var4)) {
                  break label19;
               }

               var6 = scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(s), 3);
               break label20;
            }

            var6 = (new StringBuilder(1)).append("-").append(scala.collection.StringOps..MODULE$.drop$extension(scala.Predef..MODULE$.augmentString(s), 3)).toString();
         }

         var10000 = var5.append(var6).append(")").toString();
      }

      return var10000;
   }

   // $FF: synthetic method
   static PolyDense toDense$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.toDense$mcD$sp(ring, eq);
   }

   default PolyDense toDense$mcD$sp(final Semiring ring, final Eq eq) {
      return this.toDense(ring, eq);
   }

   // $FF: synthetic method
   static PolySparse toSparse$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.toSparse$mcD$sp(ring, eq);
   }

   default PolySparse toSparse$mcD$sp(final Semiring ring, final Eq eq) {
      return this.toSparse(ring, eq);
   }

   // $FF: synthetic method
   static void foreach$mcD$sp$(final Polynomial $this, final Function2 f) {
      $this.foreach$mcD$sp(f);
   }

   default void foreach$mcD$sp(final Function2 f) {
      this.foreach(f);
   }

   // $FF: synthetic method
   static void foreachNonZero$mcD$sp$(final Polynomial $this, final Function2 f, final Semiring ring, final Eq eq) {
      $this.foreachNonZero$mcD$sp(f, ring, eq);
   }

   default void foreachNonZero$mcD$sp(final Function2 f, final Semiring ring, final Eq eq) {
      this.foreachNonZero(f, ring, eq);
   }

   // $FF: synthetic method
   static double[] coeffsArray$mcD$sp$(final Polynomial $this, final Semiring ring) {
      return $this.coeffsArray$mcD$sp(ring);
   }

   default double[] coeffsArray$mcD$sp(final Semiring ring) {
      return (double[])this.coeffsArray(ring);
   }

   // $FF: synthetic method
   static List terms$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.terms$mcD$sp(ring, eq);
   }

   default List terms$mcD$sp(final Semiring ring, final Eq eq) {
      return this.terms(ring, eq);
   }

   // $FF: synthetic method
   static Map data$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.data$mcD$sp(ring, eq);
   }

   default Map data$mcD$sp(final Semiring ring, final Eq eq) {
      return this.data(ring, eq);
   }

   // $FF: synthetic method
   static double nth$mcD$sp$(final Polynomial $this, final int n, final Semiring ring) {
      return $this.nth$mcD$sp(n, ring);
   }

   default double nth$mcD$sp(final int n, final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.nth(n, ring));
   }

   // $FF: synthetic method
   static Term maxTerm$mcD$sp$(final Polynomial $this, final Semiring ring) {
      return $this.maxTerm$mcD$sp(ring);
   }

   default Term maxTerm$mcD$sp(final Semiring ring) {
      return this.maxTerm(ring);
   }

   // $FF: synthetic method
   static Term minTerm$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.minTerm$mcD$sp(ring, eq);
   }

   default Term minTerm$mcD$sp(final Semiring ring, final Eq eq) {
      return this.minTerm(ring, eq);
   }

   // $FF: synthetic method
   static double maxOrderTermCoeff$mcD$sp$(final Polynomial $this, final Semiring ring) {
      return $this.maxOrderTermCoeff$mcD$sp(ring);
   }

   default double maxOrderTermCoeff$mcD$sp(final Semiring ring) {
      return BoxesRunTime.unboxToDouble(this.maxOrderTermCoeff(ring));
   }

   // $FF: synthetic method
   static Polynomial reductum$mcD$sp$(final Polynomial $this, final Eq e, final Semiring ring, final ClassTag ct) {
      return $this.reductum$mcD$sp(e, ring, ct);
   }

   default Polynomial reductum$mcD$sp(final Eq e, final Semiring ring, final ClassTag ct) {
      return this.reductum(e, ring, ct);
   }

   // $FF: synthetic method
   static double apply$mcD$sp$(final Polynomial $this, final double x, final Semiring r) {
      return $this.apply$mcD$sp(x, r);
   }

   default double apply$mcD$sp(final double x, final Semiring r) {
      return BoxesRunTime.unboxToDouble(this.apply(BoxesRunTime.boxToDouble(x), r));
   }

   // $FF: synthetic method
   static Object evalWith$mcD$sp$(final Polynomial $this, final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return $this.evalWith$mcD$sp(x, f, evidence$56, evidence$57, evidence$58);
   }

   default Object evalWith$mcD$sp(final Object x, final Function1 f, final Semiring evidence$56, final Eq evidence$57, final ClassTag evidence$58) {
      return this.evalWith(x, f, evidence$56, evidence$57, evidence$58);
   }

   // $FF: synthetic method
   static Polynomial compose$mcD$sp$(final Polynomial $this, final Polynomial y, final Rig ring, final Eq eq) {
      return $this.compose$mcD$sp(y, ring, eq);
   }

   default Polynomial compose$mcD$sp(final Polynomial y, final Rig ring, final Eq eq) {
      return this.compose(y, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial shift$mcD$sp$(final Polynomial $this, final double h, final Ring ring, final Eq eq) {
      return $this.shift$mcD$sp(h, ring, eq);
   }

   default Polynomial shift$mcD$sp(final double h, final Ring ring, final Eq eq) {
      return this.shift(BoxesRunTime.boxToDouble(h), ring, eq);
   }

   // $FF: synthetic method
   static Polynomial monic$mcD$sp$(final Polynomial $this, final Field f, final Eq eq) {
      return $this.monic$mcD$sp(f, eq);
   }

   default Polynomial monic$mcD$sp(final Field f, final Eq eq) {
      return this.monic(f, eq);
   }

   // $FF: synthetic method
   static Polynomial derivative$mcD$sp$(final Polynomial $this, final Ring ring, final Eq eq) {
      return $this.derivative$mcD$sp(ring, eq);
   }

   default Polynomial derivative$mcD$sp(final Ring ring, final Eq eq) {
      return this.derivative(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial integral$mcD$sp$(final Polynomial $this, final Field field, final Eq eq) {
      return $this.integral$mcD$sp(field, eq);
   }

   default Polynomial integral$mcD$sp(final Field field, final Eq eq) {
      return this.integral(field, eq);
   }

   // $FF: synthetic method
   static int signVariations$mcD$sp$(final Polynomial $this, final Semiring ring, final Order order, final Signed signed) {
      return $this.signVariations$mcD$sp(ring, order, signed);
   }

   default int signVariations$mcD$sp(final Semiring ring, final Order order, final Signed signed) {
      return this.signVariations(ring, order, signed);
   }

   // $FF: synthetic method
   static Polynomial removeZeroRoots$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.removeZeroRoots$mcD$sp(ring, eq);
   }

   default Polynomial removeZeroRoots$mcD$sp(final Semiring ring, final Eq eq) {
      return this.removeZeroRoots(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial map$mcD$sp$(final Polynomial $this, final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return $this.map$mcD$sp(f, evidence$59, evidence$60, evidence$61);
   }

   default Polynomial map$mcD$sp(final Function1 f, final Semiring evidence$59, final Eq evidence$60, final ClassTag evidence$61) {
      return this.map(f, evidence$59, evidence$60, evidence$61);
   }

   // $FF: synthetic method
   static Polynomial mapTerms$mcD$sp$(final Polynomial $this, final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return $this.mapTerms$mcD$sp(f, evidence$62, evidence$63, evidence$64);
   }

   default Polynomial mapTerms$mcD$sp(final Function1 f, final Semiring evidence$62, final Eq evidence$63, final ClassTag evidence$64) {
      return this.mapTerms(f, evidence$62, evidence$63, evidence$64);
   }

   // $FF: synthetic method
   static Polynomial flip$mcD$sp$(final Polynomial $this, final Rng ring, final Eq eq) {
      return $this.flip$mcD$sp(ring, eq);
   }

   default Polynomial flip$mcD$sp(final Rng ring, final Eq eq) {
      return this.flip(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial reciprocal$mcD$sp$(final Polynomial $this, final Semiring ring, final Eq eq) {
      return $this.reciprocal$mcD$sp(ring, eq);
   }

   default Polynomial reciprocal$mcD$sp(final Semiring ring, final Eq eq) {
      return this.reciprocal(ring, eq);
   }

   // $FF: synthetic method
   static Polynomial unary_$minus$mcD$sp$(final Polynomial $this, final Rng ring) {
      return $this.unary_$minus$mcD$sp(ring);
   }

   default Polynomial unary_$minus$mcD$sp(final Rng ring) {
      return this.unary_$minus(ring);
   }

   // $FF: synthetic method
   static Polynomial $plus$mcD$sp$(final Polynomial $this, final Polynomial rhs, final Semiring ring, final Eq eq) {
      return $this.$plus$mcD$sp(rhs, ring, eq);
   }

   default Polynomial $plus$mcD$sp(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return this.$plus(rhs, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $minus$mcD$sp$(final Polynomial $this, final Polynomial rhs, final Rng ring, final Eq eq) {
      return $this.$minus$mcD$sp(rhs, ring, eq);
   }

   default Polynomial $minus$mcD$sp(final Polynomial rhs, final Rng ring, final Eq eq) {
      return this.$minus(rhs, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $times$mcD$sp$(final Polynomial $this, final Polynomial rhs, final Semiring ring, final Eq eq) {
      return $this.$times$mcD$sp(rhs, ring, eq);
   }

   default Polynomial $times$mcD$sp(final Polynomial rhs, final Semiring ring, final Eq eq) {
      return this.$times(rhs, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $times$times$mcD$sp$(final Polynomial $this, final int k, final Rig ring, final Eq eq) {
      return $this.$times$times$mcD$sp(k, ring, eq);
   }

   default Polynomial $times$times$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return this.$times$times(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial pow$mcD$sp$(final Polynomial $this, final int k, final Rig ring, final Eq eq) {
      return $this.pow$mcD$sp(k, ring, eq);
   }

   default Polynomial pow$mcD$sp(final int k, final Rig ring, final Eq eq) {
      return this.pow(k, ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $times$colon$mcD$sp$(final Polynomial $this, final double k, final Semiring ring, final Eq eq) {
      return $this.$times$colon$mcD$sp(k, ring, eq);
   }

   default Polynomial $times$colon$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return this.$times$colon(BoxesRunTime.boxToDouble(k), ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $colon$times$mcD$sp$(final Polynomial $this, final double k, final Semiring ring, final Eq eq) {
      return $this.$colon$times$mcD$sp(k, ring, eq);
   }

   default Polynomial $colon$times$mcD$sp(final double k, final Semiring ring, final Eq eq) {
      return this.$colon$times(BoxesRunTime.boxToDouble(k), ring, eq);
   }

   // $FF: synthetic method
   static Polynomial $colon$div$mcD$sp$(final Polynomial $this, final double k, final Field field, final Eq eq) {
      return $this.$colon$div$mcD$sp(k, field, eq);
   }

   default Polynomial $colon$div$mcD$sp(final double k, final Field field, final Eq eq) {
      return this.$colon$div(BoxesRunTime.boxToDouble(k), field, eq);
   }

   // $FF: synthetic method
   static Object $anonfun$foreachNonZero$1(final Eq eq$1, final Semiring ring$1, final Function2 f$1, final int e, final Object c) {
      return eq$1.neqv(c, ring$1.zero()) ? f$1.apply(BoxesRunTime.boxToInteger(e), c) : BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   static ListBuffer $anonfun$terms$1(final ListBuffer lb$1, final int e, final Object c) {
      return (ListBuffer)lb$1.$plus$eq(new Term(c, e));
   }

   // $FF: synthetic method
   static Builder $anonfun$data$1(final Builder bldr$1, final int e, final Object c) {
      return (Builder)bldr$1.$plus$eq(new Tuple2(BoxesRunTime.boxToInteger(e), c));
   }

   // $FF: synthetic method
   static Nothing $anonfun$minTerm$1(final Object nonLocalReturnKey1$1, final int n, final Object c) {
      throw new NonLocalReturnControl(nonLocalReturnKey1$1, new Term(c, n));
   }

   // $FF: synthetic method
   static void $anonfun$compose$1(final Polynomial y$1, final Rig ring$2, final Eq eq$2, final ObjectRef polynomial$1, final int e, final Object c) {
      Polynomial z = y$1.pow(e, ring$2, eq$2).$colon$times(c, ring$2, eq$2);
      polynomial$1.elem = ((Polynomial)polynomial$1.elem).$plus(z, ring$2, eq$2);
   }

   private Object loop$2(final Object k, final SafeLong y, final Object acc, final Ring ring$3, final long mask$1, final Object d$1) {
      while(!y.isValidInt()) {
         SafeLong z = y.$greater$greater(30);
         Object r = ring$3.fromInt(y.$amp(mask$1).toInt());
         Object var10000 = ring$3.times(d$1, k);
         acc = ring$3.plus(ring$3.times(k, r), acc);
         y = z;
         k = var10000;
      }

      return ring$3.plus(ring$3.times(k, ring$3.fromInt(y.toInt())), acc);
   }

   private Object fromSafeLong$1(final SafeLong x, final Ring ring$3) {
      Object var10000;
      if (x.isValidInt()) {
         var10000 = ring$3.fromInt(x.toInt());
      } else {
         Object d = ring$3.fromInt(1073741824);
         long mask = 1073741823L;
         var10000 = this.loop$2(ring$3.one(), x, ring$3.zero(), ring$3, mask, d);
      }

      return var10000;
   }

   // $FF: synthetic method
   static void $anonfun$shift$1(final Polynomial $this, final Ring ring$3, final Object h$1, final Object coeffs$1, final int deg, final Object c) {
      int i = 1;
      int d = deg - 1;
      SafeLong m = SafeLong$.MODULE$.apply(1L);

      for(Object k = c; d >= 0; ++i) {
         m = m.$times((long)(d + 1)).$div((long)i);
         k = ring$3.times(k, h$1);
         scala.runtime.ScalaRunTime..MODULE$.array_update(coeffs$1, d, ring$3.plus(scala.runtime.ScalaRunTime..MODULE$.array_apply(coeffs$1, d), ring$3.times($this.fromSafeLong$1(m, ring$3), k)));
         --d;
      }

   }

   // $FF: synthetic method
   static void $anonfun$signVariations$1(final Signed signed$1, final ObjectRef prevSign$1, final IntRef variations$1, final int x$5, final Object c) {
      Signed.Sign sign = signed$1.sign(c);
      if (!algebra.ring.Signed.Zero..MODULE$.equals((Signed.Sign)prevSign$1.elem)) {
         label15: {
            Signed.Sign var6 = (Signed.Sign)prevSign$1.elem;
            if (sign == null) {
               if (var6 == null) {
                  break label15;
               }
            } else if (sign.equals(var6)) {
               break label15;
            }

            ++variations$1.elem;
         }
      }

      prevSign$1.elem = sign;
   }

   private Polynomial loop$3(final Polynomial b, final int k, final Polynomial extra, final Rig ring$5, final Eq eq$3) {
      while(k != 1) {
         Polynomial var10000 = b.$times(b, ring$5, eq$3);
         int var10001 = k >>> 1;
         extra = (k & 1) == 1 ? b.$times(extra, ring$5, eq$3) : extra;
         k = var10001;
         b = var10000;
      }

      return b.$times(extra, ring$5, eq$3);
   }

   private int loop$4(final int n, final Iterator it$1) {
      while(it$1.hasNext()) {
         Term term = (Term)it$1.next();
         n ^= -18017705 * term.exp() ^ Statics.anyHash(term.coeff());
      }

      return n;
   }

   private boolean loop$5(final Iterator it1$1, final Iterator it2$1) {
      boolean var10000;
      while(true) {
         boolean has1 = it1$1.hasNext();
         boolean has2 = it2$1.hasNext();
         if (has1 && has2) {
            if (BoxesRunTime.equals(it1$1.next(), it2$1.next())) {
               continue;
            }

            var10000 = false;
            break;
         }

         var10000 = has1 == has2;
         break;
      }

      return var10000;
   }

   // $FF: synthetic method
   static ArrayBuilder $anonfun$toString$1(final ArrayBuilder bldr$2, final int e, final Object c) {
      return (ArrayBuilder)bldr$2.$plus$eq(new Term(c, e));
   }

   static void $init$(final Polynomial $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
