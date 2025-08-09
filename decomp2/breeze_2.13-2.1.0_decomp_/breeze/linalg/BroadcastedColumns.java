package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.operators.HasOps$;
import breeze.linalg.support.CanForeachValues;
import breeze.linalg.support.CanIterateAxis;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.linalg.support.CanTraverseAxis;
import breeze.linalg.support.ScalarOf;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.PartialFunction;
import scala.Product;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Factory;
import scala.collection.IndexedSeqOps;
import scala.collection.IndexedSeqView;
import scala.collection.Iterable;
import scala.collection.IterableFactory;
import scala.collection.IterableFactoryDefaults;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.LazyZip2;
import scala.collection.Searching;
import scala.collection.Seq;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.WithFilter;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.List;
import scala.collection.immutable.Range;
import scala.collection.immutable.Set;
import scala.collection.immutable.Stream;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.Builder;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.ObjectRef;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\tec\u0001\u0002\u0013&\u0001*B\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\t-\u0002\u0011\t\u0012)A\u0005m!)q\u000b\u0001C\u00011\")!\f\u0001C\u00017\")A\f\u0001C\u0001;\")\u0001\u000f\u0001C\u0001c\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u00037\u0001\u0011\u0013!C\u0001\u0003;A\u0011\"!\u000f\u0001\u0003\u0003%\t%a\u000f\t\u0013\u00055\u0003!!A\u0005\u0002\u0005=\u0003\"CA,\u0001\u0005\u0005I\u0011AA-\u0011%\ty\u0006AA\u0001\n\u0003\n\t\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0001\u0002p!I\u0011\u0011\u0010\u0001\u0002\u0002\u0013\u0005\u00131\u0010\u0005\n\u0003\u007f\u0002\u0011\u0011!C!\u0003\u0003C\u0011\"a!\u0001\u0003\u0003%\t%!\"\t\u0013\u0005\u001d\u0005!!A\u0005B\u0005%uaBAGK!\u0005\u0011q\u0012\u0004\u0007I\u0015B\t!!%\t\r]\u001bB\u0011AAO\r\u0019\tyj\u0005\u0001\u0002\"\"IA+\u0006BC\u0002\u0013\u0005\u0011Q\u0017\u0005\n-V\u0011\t\u0011)A\u0005\u0003oCaaV\u000b\u0005\u0002\u0005u\u0006bBAc+\u0011\u0005\u0013q\n\u0005\b\u0003\u000f,B\u0011IAe\u0011\u001d\tYn\u0005C\u0002\u0003;4a!a=\u0014\u0003\u0005U\bBCA}9\t\u0005\t\u0015!\u0003\u0002|\"1q\u000b\bC\u0001\u0005\u000bAqAa\u0003\u001d\t\u0003\u0011i\u0001C\u0005\u0003\u0012M\t\t\u0011b\u0001\u0003\u0014!I\u0011qY\n\u0002\u0002\u0013\u0005%Q\u0005\u0005\n\u0005k\u0019\u0012\u0011!CA\u0005oA\u0011Ba\u0014\u0014\u0003\u0003%IA!\u0015\u0003%\t\u0013x.\u00193dCN$X\rZ\"pYVlgn\u001d\u0006\u0003M\u001d\na\u0001\\5oC2<'\"\u0001\u0015\u0002\r\t\u0014X-\u001a>f\u0007\u0001)2a\u000b\u001dC'\u0015\u0001AFM#I!\ti\u0003'D\u0001/\u0015\u0005y\u0013!B:dC2\f\u0017BA\u0019/\u0005\u0019\te.\u001f*fMB)1\u0007\u000e\u001cB\t6\tQ%\u0003\u00026K\ty!I]8bI\u000e\f7\u000f^3e\u0019&\\W\r\u0005\u00028q1\u0001A!B\u001d\u0001\u0005\u0004Q$!\u0001+\u0012\u0005mr\u0004CA\u0017=\u0013\tidFA\u0004O_RD\u0017N\\4\u0011\u00055z\u0014B\u0001!/\u0005\r\te.\u001f\t\u0003o\t#Qa\u0011\u0001C\u0002i\u0012qaQ8m)f\u0004X\r\u0005\u00034\u0001Y\n\u0005CA\u0017G\u0013\t9eFA\u0004Qe>$Wo\u0019;\u0011\u0005%\u000bfB\u0001&P\u001d\tYe*D\u0001M\u0015\ti\u0015&\u0001\u0004=e>|GOP\u0005\u0002_%\u0011\u0001KL\u0001\ba\u0006\u001c7.Y4f\u0013\t\u00116K\u0001\u0007TKJL\u0017\r\\5{C\ndWM\u0003\u0002Q]\u0005QQO\u001c3fe2L\u0018N\\4\u0016\u0003Y\n1\"\u001e8eKJd\u00170\u001b8hA\u00051A(\u001b8jiz\"\"\u0001R-\t\u000bQ\u001b\u0001\u0019\u0001\u001c\u0002\tI,\u0007O]\u000b\u0002\t\u0006A\u0011\u000e^3sCR|'\u000f\u0006\u0002_CB\u0019\u0011jX!\n\u0005\u0001\u001c&\u0001C%uKJ\fGo\u001c:\t\u000b\t,\u00019A2\u0002\u001d\r\fg.\u0013;fe\u0006$X-\u0011=jgB)Am\u001a\u001cj\u00036\tQM\u0003\u0002gK\u000591/\u001e9q_J$\u0018B\u00015f\u00059\u0019\u0015M\\%uKJ\fG/Z!ySNt!A[7\u000f\u0005MZ\u0017B\u00017&\u0003\u0011\t\u00050[:\n\u00059|\u0017AA01\u0015\taW%\u0001\u0005g_2$G*\u001a4u+\t\u0011h\u000fF\u0002t\u0003\u000b!\"\u0001^?\u0015\u0005UD\bCA\u001cw\t\u00159hA1\u0001;\u0005\u0005\u0011\u0005\"B=\u0007\u0001\bQ\u0018aD2b]R\u0013\u0018M^3sg\u0016\f\u00050[:\u0011\u000b\u0011\\h'[!\n\u0005q,'aD\"b]R\u0013\u0018M^3sg\u0016\f\u00050[:\t\u000by4\u0001\u0019A@\u0002\u0003\u0019\u0004b!LA\u0001k\u0006+\u0018bAA\u0002]\tIa)\u001e8di&|gN\r\u0005\u0007\u0003\u000f1\u0001\u0019A;\u0002\u0003i\fAaY8qsV1\u0011QBA\n\u0003/!B!a\u0004\u0002\u001aA11\u0007AA\t\u0003+\u00012aNA\n\t\u0015ItA1\u0001;!\r9\u0014q\u0003\u0003\u0006\u0007\u001e\u0011\rA\u000f\u0005\t)\u001e\u0001\n\u00111\u0001\u0002\u0012\u0005q1m\u001c9zI\u0011,g-Y;mi\u0012\nTCBA\u0010\u0003k\t9$\u0006\u0002\u0002\")\u001aa'a\t,\u0005\u0005\u0015\u0002\u0003BA\u0014\u0003ci!!!\u000b\u000b\t\u0005-\u0012QF\u0001\nk:\u001c\u0007.Z2lK\u0012T1!a\f/\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003g\tICA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016$Q!\u000f\u0005C\u0002i\"Qa\u0011\u0005C\u0002i\nQ\u0002\u001d:pIV\u001cG\u000f\u0015:fM&DXCAA\u001f!\u0011\ty$!\u0013\u000e\u0005\u0005\u0005#\u0002BA\"\u0003\u000b\nA\u0001\\1oO*\u0011\u0011qI\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002L\u0005\u0005#AB*ue&tw-\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0006\u0002\u0002RA\u0019Q&a\u0015\n\u0007\u0005UcFA\u0002J]R\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000fF\u0002?\u00037B\u0011\"!\u0018\f\u0003\u0003\u0005\r!!\u0015\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t\u0019\u0007E\u0003\u0002f\u0005-d(\u0004\u0002\u0002h)\u0019\u0011\u0011\u000e\u0018\u0002\u0015\r|G\u000e\\3di&|g.C\u0002a\u0003O\n\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0005\u0003c\n9\bE\u0002.\u0003gJ1!!\u001e/\u0005\u001d\u0011un\u001c7fC:D\u0001\"!\u0018\u000e\u0003\u0003\u0005\rAP\u0001\u0013aJ|G-^2u\u000b2,W.\u001a8u\u001d\u0006lW\r\u0006\u0003\u0002>\u0005u\u0004\"CA/\u001d\u0005\u0005\t\u0019AA)\u0003!A\u0017m\u001d5D_\u0012,GCAA)\u0003!!xn\u0015;sS:<GCAA\u001f\u0003\u0019)\u0017/^1mgR!\u0011\u0011OAF\u0011!\ti&EA\u0001\u0002\u0004q\u0014A\u0005\"s_\u0006$7-Y:uK\u0012\u001cu\u000e\\;n]N\u0004\"aM\n\u0014\tMa\u00131\u0013\t\u0005\u0003+\u000bY*\u0004\u0002\u0002\u0018*!\u0011\u0011TA#\u0003\tIw.C\u0002S\u0003/#\"!a$\u0003+\t\u0013x.\u00193dCN$X\r\u001a#N\u0007>d7/S*fcV!\u00111UAZ'\u0015)B&!*I!\u0015I\u0015qUAV\u0013\r\tIk\u0015\u0002\u000b\u0013:$W\r_3e'\u0016\f\b#B\u001a\u0002.\u0006E\u0016bAAXK\tYA)\u001a8tKZ+7\r^8s!\r9\u00141\u0017\u0003\u0006sU\u0011\rAO\u000b\u0003\u0003o\u0003RaMA]\u0003cK1!a/&\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0015\t\u0005}\u00161\u0019\t\u0006\u0003\u0003,\u0012\u0011W\u0007\u0002'!1A\u000b\u0007a\u0001\u0003o\u000ba\u0001\\3oORD\u0017!B1qa2LH\u0003BAV\u0003\u0017Dq!!4\u001b\u0001\u0004\t\t&A\u0002jIbDs!FAi\u0003/\fI\u000eE\u0002.\u0003'L1!!6/\u0005A\u0019VM]5bYZ+'o]5p]VKE)A\u0003wC2,XMH\u0001\u0002\u0003!\u00198-\u00197be>3WCBAp\u0003W\fy/\u0006\u0002\u0002bB9A-a9\u0002h\u00065\u0018bAAsK\nA1kY1mCJ|e\r\u0005\u00044\u0001\u0005%\u0018Q\u001e\t\u0004o\u0005-H!B\u001d\u001c\u0005\u0004Q\u0004cA\u001c\u0002p\u00121\u0011\u0011_\u000eC\u0002i\u0012!bQ8mk6tG+\u001f9f\u0005y\u0011%o\\1eG\u0006\u001cHoQ8mk6t7\u000fR'U_&sG-\u001a=fIN+\u0017/\u0006\u0003\u0002x\n\u00051C\u0001\u000f-\u0003\t\u00117\r\u0005\u00044\u0001\u0005u(1\u0001\t\u0006g\u0005e\u0016q \t\u0004o\t\u0005A!B\u001d\u001d\u0005\u0004Q\u0004#B\u001a\u0002.\u0006}H\u0003\u0002B\u0004\u0005\u0013\u0001R!!1\u001d\u0003\u007fDq!!?\u001f\u0001\u0004\tY0\u0001\u0007u_&sG-\u001a=fIN+\u0017/\u0006\u0002\u0003\u0010A)\u0011*a*\u0003\u0004\u0005q\"I]8bI\u000e\f7\u000f^\"pYVlgn\u001d#N)>Le\u000eZ3yK\u0012\u001cV-]\u000b\u0005\u0005+\u0011Y\u0002\u0006\u0003\u0003\u0018\tu\u0001#BAa9\te\u0001cA\u001c\u0003\u001c\u0011)\u0011\b\tb\u0001u!9\u0011\u0011 \u0011A\u0002\t}\u0001CB\u001a\u0001\u0005C\u0011\u0019\u0003E\u00034\u0003s\u0013I\u0002E\u00034\u0003[\u0013I\"\u0006\u0004\u0003(\t5\"\u0011\u0007\u000b\u0005\u0005S\u0011\u0019\u0004\u0005\u00044\u0001\t-\"q\u0006\t\u0004o\t5B!B\u001d\"\u0005\u0004Q\u0004cA\u001c\u00032\u0011)1)\tb\u0001u!1A+\ta\u0001\u0005W\tq!\u001e8baBd\u00170\u0006\u0004\u0003:\t\r#Q\n\u000b\u0005\u0005w\u0011)\u0005E\u0003.\u0005{\u0011\t%C\u0002\u0003@9\u0012aa\u00149uS>t\u0007cA\u001c\u0003D\u0011)\u0011H\tb\u0001u!I!q\t\u0012\u0002\u0002\u0003\u0007!\u0011J\u0001\u0004q\u0012\u0002\u0004CB\u001a\u0001\u0005\u0003\u0012Y\u0005E\u00028\u0005\u001b\"Qa\u0011\u0012C\u0002i\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"Aa\u0015\u0011\t\u0005}\"QK\u0005\u0005\u0005/\n\tE\u0001\u0004PE*,7\r\u001e"
)
public class BroadcastedColumns implements BroadcastedLike, Product, Serializable {
   private final Object underlying;

   public static Option unapply(final BroadcastedColumns x$0) {
      return BroadcastedColumns$.MODULE$.unapply(x$0);
   }

   public static BroadcastedColumns apply(final Object underlying) {
      return BroadcastedColumns$.MODULE$.apply(underlying);
   }

   public static BroadcastColumnsDMToIndexedSeq BroadcastColumnsDMToIndexedSeq(final BroadcastedColumns bc) {
      return BroadcastedColumns$.MODULE$.BroadcastColumnsDMToIndexedSeq(bc);
   }

   public static ScalarOf scalarOf() {
      return BroadcastedColumns$.MODULE$.scalarOf();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Object map(final Function1 f, final CanMapValues cmv) {
      return BroadcastedLike.map$(this, f, cmv);
   }

   public void foreach(final Function1 f, final CanForeachValues cmv) {
      BroadcastedLike.foreach$(this, f, cmv);
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public Object underlying() {
      return this.underlying;
   }

   public BroadcastedColumns repr() {
      return this;
   }

   public Iterator iterator(final CanIterateAxis canIterateAxis) {
      return canIterateAxis.apply(this.underlying(), Axis._0$.MODULE$);
   }

   public Object foldLeft(final Object z, final Function2 f, final CanTraverseAxis canTraverseAxis) {
      ObjectRef acc = ObjectRef.create(z);
      canTraverseAxis.apply(this.underlying(), Axis._0$.MODULE$, (c) -> {
         $anonfun$foldLeft$1(acc, f, c);
         return BoxedUnit.UNIT;
      });
      return acc.elem;
   }

   public BroadcastedColumns copy(final Object underlying) {
      return new BroadcastedColumns(underlying);
   }

   public Object copy$default$1() {
      return this.underlying();
   }

   public String productPrefix() {
      return "BroadcastedColumns";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.underlying();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof BroadcastedColumns;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "underlying";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return .MODULE$._hashCode(this);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var10000;
      if (this != x$1) {
         label49: {
            boolean var2;
            if (x$1 instanceof BroadcastedColumns) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               BroadcastedColumns var4 = (BroadcastedColumns)x$1;
               if (BoxesRunTime.equals(this.underlying(), var4.underlying()) && var4.canEqual(this)) {
                  break label49;
               }
            }

            var10000 = false;
            return var10000;
         }
      }

      var10000 = true;
      return var10000;
   }

   // $FF: synthetic method
   public static final void $anonfun$foldLeft$1(final ObjectRef acc$1, final Function2 f$1, final Object c) {
      acc$1.elem = f$1.apply(acc$1.elem, c);
   }

   public BroadcastedColumns(final Object underlying) {
      this.underlying = underlying;
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      BroadcastedLike.$init$(this);
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class BroadcastedDMColsISeq implements IndexedSeq, Serializable {
      private static final long serialVersionUID = 1L;
      private final DenseMatrix underlying;

      // $FF: synthetic method
      public boolean scala$collection$immutable$IndexedSeq$$super$canEqual(final Object that) {
         return Seq.canEqual$(this, that);
      }

      // $FF: synthetic method
      public boolean scala$collection$immutable$IndexedSeq$$super$sameElements(final IterableOnce that) {
         return SeqOps.sameElements$(this, that);
      }

      public final IndexedSeq toIndexedSeq() {
         return IndexedSeq.toIndexedSeq$(this);
      }

      public boolean canEqual(final Object that) {
         return IndexedSeq.canEqual$(this, that);
      }

      public boolean sameElements(final IterableOnce o) {
         return IndexedSeq.sameElements$(this, o);
      }

      public int applyPreferredMaxLength() {
         return IndexedSeq.applyPreferredMaxLength$(this);
      }

      public SeqFactory iterableFactory() {
         return IndexedSeq.iterableFactory$(this);
      }

      // $FF: synthetic method
      public Object scala$collection$immutable$IndexedSeqOps$$super$slice(final int from, final int until) {
         return IndexedSeqOps.slice$(this, from, until);
      }

      public Object slice(final int from, final int until) {
         return scala.collection.immutable.IndexedSeqOps.slice$(this, from, until);
      }

      public String stringPrefix() {
         return scala.collection.IndexedSeq.stringPrefix$(this);
      }

      public Iterator iterator() {
         return IndexedSeqOps.iterator$(this);
      }

      public Stepper stepper(final StepperShape shape) {
         return IndexedSeqOps.stepper$(this, shape);
      }

      public Iterator reverseIterator() {
         return IndexedSeqOps.reverseIterator$(this);
      }

      public Object foldRight(final Object z, final Function2 op) {
         return IndexedSeqOps.foldRight$(this, z, op);
      }

      public IndexedSeqView view() {
         return IndexedSeqOps.view$(this);
      }

      /** @deprecated */
      public IndexedSeqView view(final int from, final int until) {
         return IndexedSeqOps.view$(this, from, until);
      }

      public Iterable reversed() {
         return IndexedSeqOps.reversed$(this);
      }

      public Object prepended(final Object elem) {
         return IndexedSeqOps.prepended$(this, elem);
      }

      public Object take(final int n) {
         return IndexedSeqOps.take$(this, n);
      }

      public Object takeRight(final int n) {
         return IndexedSeqOps.takeRight$(this, n);
      }

      public Object drop(final int n) {
         return IndexedSeqOps.drop$(this, n);
      }

      public Object dropRight(final int n) {
         return IndexedSeqOps.dropRight$(this, n);
      }

      public Object map(final Function1 f) {
         return IndexedSeqOps.map$(this, f);
      }

      public Object reverse() {
         return IndexedSeqOps.reverse$(this);
      }

      public Object head() {
         return IndexedSeqOps.head$(this);
      }

      public Option headOption() {
         return IndexedSeqOps.headOption$(this);
      }

      public Object last() {
         return IndexedSeqOps.last$(this);
      }

      public final int lengthCompare(final int len) {
         return IndexedSeqOps.lengthCompare$(this, len);
      }

      public int knownSize() {
         return IndexedSeqOps.knownSize$(this);
      }

      public final int lengthCompare(final Iterable that) {
         return IndexedSeqOps.lengthCompare$(this, that);
      }

      public Searching.SearchResult search(final Object elem, final Ordering ord) {
         return IndexedSeqOps.search$(this, elem, ord);
      }

      public Searching.SearchResult search(final Object elem, final int from, final int to, final Ordering ord) {
         return IndexedSeqOps.search$(this, elem, from, to, ord);
      }

      public final scala.collection.immutable.Seq toSeq() {
         return scala.collection.immutable.Seq.toSeq$(this);
      }

      public boolean equals(final Object o) {
         return Seq.equals$(this, o);
      }

      public int hashCode() {
         return Seq.hashCode$(this);
      }

      public String toString() {
         return Seq.toString$(this);
      }

      // $FF: synthetic method
      public Object scala$collection$SeqOps$$super$concat(final IterableOnce suffix) {
         return IterableOps.concat$(this, suffix);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final int otherSize) {
         return IterableOps.sizeCompare$(this, otherSize);
      }

      // $FF: synthetic method
      public int scala$collection$SeqOps$$super$sizeCompare(final Iterable that) {
         return IterableOps.sizeCompare$(this, that);
      }

      public final Object $plus$colon(final Object elem) {
         return SeqOps.$plus$colon$(this, elem);
      }

      public Object appended(final Object elem) {
         return SeqOps.appended$(this, elem);
      }

      public final Object $colon$plus(final Object elem) {
         return SeqOps.$colon$plus$(this, elem);
      }

      public Object prependedAll(final IterableOnce prefix) {
         return SeqOps.prependedAll$(this, prefix);
      }

      public final Object $plus$plus$colon(final IterableOnce prefix) {
         return SeqOps.$plus$plus$colon$(this, prefix);
      }

      public Object appendedAll(final IterableOnce suffix) {
         return SeqOps.appendedAll$(this, suffix);
      }

      public final Object $colon$plus$plus(final IterableOnce suffix) {
         return SeqOps.$colon$plus$plus$(this, suffix);
      }

      public final Object concat(final IterableOnce suffix) {
         return SeqOps.concat$(this, suffix);
      }

      /** @deprecated */
      public final Object union(final Seq that) {
         return SeqOps.union$(this, that);
      }

      public final int size() {
         return SeqOps.size$(this);
      }

      public Object distinct() {
         return SeqOps.distinct$(this);
      }

      public Object distinctBy(final Function1 f) {
         return SeqOps.distinctBy$(this, f);
      }

      public boolean startsWith(final IterableOnce that, final int offset) {
         return SeqOps.startsWith$(this, that, offset);
      }

      public int startsWith$default$2() {
         return SeqOps.startsWith$default$2$(this);
      }

      public boolean endsWith(final Iterable that) {
         return SeqOps.endsWith$(this, that);
      }

      public boolean isDefinedAt(final int idx) {
         return SeqOps.isDefinedAt$(this, idx);
      }

      public Object padTo(final int len, final Object elem) {
         return SeqOps.padTo$(this, len, elem);
      }

      public final int segmentLength(final Function1 p) {
         return SeqOps.segmentLength$(this, p);
      }

      public int segmentLength(final Function1 p, final int from) {
         return SeqOps.segmentLength$(this, p, from);
      }

      /** @deprecated */
      public final int prefixLength(final Function1 p) {
         return SeqOps.prefixLength$(this, p);
      }

      public int indexWhere(final Function1 p, final int from) {
         return SeqOps.indexWhere$(this, p, from);
      }

      public int indexWhere(final Function1 p) {
         return SeqOps.indexWhere$(this, p);
      }

      public int indexOf(final Object elem, final int from) {
         return SeqOps.indexOf$(this, elem, from);
      }

      public int indexOf(final Object elem) {
         return SeqOps.indexOf$(this, elem);
      }

      public int lastIndexOf(final Object elem, final int end) {
         return SeqOps.lastIndexOf$(this, elem, end);
      }

      public int lastIndexOf$default$2() {
         return SeqOps.lastIndexOf$default$2$(this);
      }

      public int lastIndexWhere(final Function1 p, final int end) {
         return SeqOps.lastIndexWhere$(this, p, end);
      }

      public int lastIndexWhere(final Function1 p) {
         return SeqOps.lastIndexWhere$(this, p);
      }

      public int indexOfSlice(final Seq that, final int from) {
         return SeqOps.indexOfSlice$(this, that, from);
      }

      public int indexOfSlice(final Seq that) {
         return SeqOps.indexOfSlice$(this, that);
      }

      public int lastIndexOfSlice(final Seq that, final int end) {
         return SeqOps.lastIndexOfSlice$(this, that, end);
      }

      public int lastIndexOfSlice(final Seq that) {
         return SeqOps.lastIndexOfSlice$(this, that);
      }

      public Option findLast(final Function1 p) {
         return SeqOps.findLast$(this, p);
      }

      public boolean containsSlice(final Seq that) {
         return SeqOps.containsSlice$(this, that);
      }

      public boolean contains(final Object elem) {
         return SeqOps.contains$(this, elem);
      }

      /** @deprecated */
      public Object reverseMap(final Function1 f) {
         return SeqOps.reverseMap$(this, f);
      }

      public Iterator permutations() {
         return SeqOps.permutations$(this);
      }

      public Iterator combinations(final int n) {
         return SeqOps.combinations$(this, n);
      }

      public Object sorted(final Ordering ord) {
         return SeqOps.sorted$(this, ord);
      }

      public Object sortWith(final Function2 lt) {
         return SeqOps.sortWith$(this, lt);
      }

      public Object sortBy(final Function1 f, final Ordering ord) {
         return SeqOps.sortBy$(this, f, ord);
      }

      public Range indices() {
         return SeqOps.indices$(this);
      }

      public final int sizeCompare(final int otherSize) {
         return SeqOps.sizeCompare$(this, otherSize);
      }

      public final int sizeCompare(final Iterable that) {
         return SeqOps.sizeCompare$(this, that);
      }

      public final IterableOps lengthIs() {
         return SeqOps.lengthIs$(this);
      }

      public boolean isEmpty() {
         return SeqOps.isEmpty$(this);
      }

      public boolean corresponds(final Seq that, final Function2 p) {
         return SeqOps.corresponds$(this, that, p);
      }

      public Object diff(final Seq that) {
         return SeqOps.diff$(this, that);
      }

      public Object intersect(final Seq that) {
         return SeqOps.intersect$(this, that);
      }

      public Object patch(final int from, final IterableOnce other, final int replaced) {
         return SeqOps.patch$(this, from, other, replaced);
      }

      public Object updated(final int index, final Object elem) {
         return SeqOps.updated$(this, index, elem);
      }

      public Map occCounts(final Seq sq) {
         return SeqOps.occCounts$(this, sq);
      }

      public Option unapply(final Object a) {
         return PartialFunction.unapply$(this, a);
      }

      public PartialFunction elementWise() {
         return PartialFunction.elementWise$(this);
      }

      public PartialFunction orElse(final PartialFunction that) {
         return PartialFunction.orElse$(this, that);
      }

      public PartialFunction andThen(final Function1 k) {
         return PartialFunction.andThen$(this, k);
      }

      public PartialFunction andThen(final PartialFunction k) {
         return PartialFunction.andThen$(this, k);
      }

      public PartialFunction compose(final PartialFunction k) {
         return PartialFunction.compose$(this, k);
      }

      public Function1 lift() {
         return PartialFunction.lift$(this);
      }

      public Object applyOrElse(final Object x, final Function1 default) {
         return PartialFunction.applyOrElse$(this, x, default);
      }

      public Function1 runWith(final Function1 action) {
         return PartialFunction.runWith$(this, action);
      }

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

      /** @deprecated */
      public final Iterable toIterable() {
         return Iterable.toIterable$(this);
      }

      public final Iterable coll() {
         return Iterable.coll$(this);
      }

      /** @deprecated */
      public Iterable seq() {
         return Iterable.seq$(this);
      }

      public String className() {
         return Iterable.className$(this);
      }

      public final String collectionClassName() {
         return Iterable.collectionClassName$(this);
      }

      public LazyZip2 lazyZip(final Iterable that) {
         return Iterable.lazyZip$(this, that);
      }

      public IterableOps fromSpecific(final IterableOnce coll) {
         return IterableFactoryDefaults.fromSpecific$(this, coll);
      }

      public Builder newSpecificBuilder() {
         return IterableFactoryDefaults.newSpecificBuilder$(this);
      }

      public IterableOps empty() {
         return IterableFactoryDefaults.empty$(this);
      }

      /** @deprecated */
      public final Iterable toTraversable() {
         return IterableOps.toTraversable$(this);
      }

      public boolean isTraversableAgain() {
         return IterableOps.isTraversableAgain$(this);
      }

      /** @deprecated */
      public final Object repr() {
         return IterableOps.repr$(this);
      }

      /** @deprecated */
      public IterableFactory companion() {
         return IterableOps.companion$(this);
      }

      public Option lastOption() {
         return IterableOps.lastOption$(this);
      }

      public final IterableOps sizeIs() {
         return IterableOps.sizeIs$(this);
      }

      public Object transpose(final Function1 asIterable) {
         return IterableOps.transpose$(this, asIterable);
      }

      public Object filter(final Function1 pred) {
         return IterableOps.filter$(this, pred);
      }

      public Object filterNot(final Function1 pred) {
         return IterableOps.filterNot$(this, pred);
      }

      public WithFilter withFilter(final Function1 p) {
         return IterableOps.withFilter$(this, p);
      }

      public Tuple2 partition(final Function1 p) {
         return IterableOps.partition$(this, p);
      }

      public Tuple2 splitAt(final int n) {
         return IterableOps.splitAt$(this, n);
      }

      public Object takeWhile(final Function1 p) {
         return IterableOps.takeWhile$(this, p);
      }

      public Tuple2 span(final Function1 p) {
         return IterableOps.span$(this, p);
      }

      public Object dropWhile(final Function1 p) {
         return IterableOps.dropWhile$(this, p);
      }

      public Iterator grouped(final int size) {
         return IterableOps.grouped$(this, size);
      }

      public Iterator sliding(final int size) {
         return IterableOps.sliding$(this, size);
      }

      public Iterator sliding(final int size, final int step) {
         return IterableOps.sliding$(this, size, step);
      }

      public Object tail() {
         return IterableOps.tail$(this);
      }

      public Object init() {
         return IterableOps.init$(this);
      }

      public scala.collection.immutable.Map groupBy(final Function1 f) {
         return IterableOps.groupBy$(this, f);
      }

      public scala.collection.immutable.Map groupMap(final Function1 key, final Function1 f) {
         return IterableOps.groupMap$(this, key, f);
      }

      public scala.collection.immutable.Map groupMapReduce(final Function1 key, final Function1 f, final Function2 reduce) {
         return IterableOps.groupMapReduce$(this, key, f, reduce);
      }

      public Object scan(final Object z, final Function2 op) {
         return IterableOps.scan$(this, z, op);
      }

      public Object scanLeft(final Object z, final Function2 op) {
         return IterableOps.scanLeft$(this, z, op);
      }

      public Object scanRight(final Object z, final Function2 op) {
         return IterableOps.scanRight$(this, z, op);
      }

      public Object flatMap(final Function1 f) {
         return IterableOps.flatMap$(this, f);
      }

      public Object flatten(final Function1 asIterable) {
         return IterableOps.flatten$(this, asIterable);
      }

      public Object collect(final PartialFunction pf) {
         return IterableOps.collect$(this, pf);
      }

      public Tuple2 partitionMap(final Function1 f) {
         return IterableOps.partitionMap$(this, f);
      }

      public final Object $plus$plus(final IterableOnce suffix) {
         return IterableOps.$plus$plus$(this, suffix);
      }

      public Object zip(final IterableOnce that) {
         return IterableOps.zip$(this, that);
      }

      public Object zipWithIndex() {
         return IterableOps.zipWithIndex$(this);
      }

      public Object zipAll(final Iterable that, final Object thisElem, final Object thatElem) {
         return IterableOps.zipAll$(this, that, thisElem, thatElem);
      }

      public Tuple2 unzip(final Function1 asPair) {
         return IterableOps.unzip$(this, asPair);
      }

      public Tuple3 unzip3(final Function1 asTriple) {
         return IterableOps.unzip3$(this, asTriple);
      }

      public Iterator tails() {
         return IterableOps.tails$(this);
      }

      public Iterator inits() {
         return IterableOps.inits$(this);
      }

      public Object tapEach(final Function1 f) {
         return IterableOps.tapEach$(this, f);
      }

      /** @deprecated */
      public boolean hasDefiniteSize() {
         return IterableOnceOps.hasDefiniteSize$(this);
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

      public Object maxBy(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.maxBy$(this, f, cmp);
      }

      public Option maxByOption(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.maxByOption$(this, f, cmp);
      }

      public Object minBy(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.minBy$(this, f, cmp);
      }

      public Option minByOption(final Function1 f, final Ordering cmp) {
         return IterableOnceOps.minByOption$(this, f, cmp);
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

      public scala.collection.immutable.Vector toVector() {
         return IterableOnceOps.toVector$(this);
      }

      public scala.collection.immutable.Map toMap(final scala..less.colon.less ev) {
         return IterableOnceOps.toMap$(this, ev);
      }

      public Set toSet() {
         return IterableOnceOps.toSet$(this);
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

      public DenseMatrix underlying() {
         return this.underlying;
      }

      public int length() {
         return this.underlying().cols();
      }

      public DenseVector apply(final int idx) {
         return (DenseVector)this.underlying().apply(scala.package..MODULE$.$colon$colon(), BoxesRunTime.boxToInteger(idx), HasOps$.MODULE$.canSliceCol());
      }

      public BroadcastedDMColsISeq(final DenseMatrix underlying) {
         this.underlying = underlying;
         IterableOnce.$init$(this);
         IterableOnceOps.$init$(this);
         IterableOps.$init$(this);
         IterableFactoryDefaults.$init$(this);
         Iterable.$init$(this);
         scala.collection.immutable.Iterable.$init$(this);
         Function1.$init$(this);
         PartialFunction.$init$(this);
         SeqOps.$init$(this);
         Seq.$init$(this);
         scala.collection.immutable.Seq.$init$(this);
         IndexedSeqOps.$init$(this);
         scala.collection.IndexedSeq.$init$(this);
         scala.collection.immutable.IndexedSeqOps.$init$(this);
         IndexedSeq.$init$(this);
      }
   }

   public static class BroadcastColumnsDMToIndexedSeq {
      private final BroadcastedColumns bc;

      public IndexedSeq toIndexedSeq() {
         return new BroadcastedDMColsISeq((DenseMatrix)this.bc.underlying());
      }

      public BroadcastColumnsDMToIndexedSeq(final BroadcastedColumns bc) {
         this.bc = bc;
      }
   }
}
