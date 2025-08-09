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
   bytes = "\u0006\u0005\t}c\u0001\u0002\u0013&\u0001*B\u0001\u0002\u0016\u0001\u0003\u0016\u0004%\t!\u0016\u0005\t-\u0002\u0011\t\u0012)A\u0005m!)q\u000b\u0001C\u00011\")!\f\u0001C\u00017\")A\f\u0001C\u0001;\")\u0001\u000f\u0001C\u0001c\"I\u0011\u0011\u0002\u0001\u0002\u0002\u0013\u0005\u00111\u0002\u0005\n\u00037\u0001\u0011\u0013!C\u0001\u0003;A\u0011\"!\u000f\u0001\u0003\u0003%\t%a\u000f\t\u0013\u00055\u0003!!A\u0005\u0002\u0005=\u0003\"CA,\u0001\u0005\u0005I\u0011AA-\u0011%\ty\u0006AA\u0001\n\u0003\n\t\u0007C\u0005\u0002n\u0001\t\t\u0011\"\u0001\u0002p!I\u0011\u0011\u0010\u0001\u0002\u0002\u0013\u0005\u00131\u0010\u0005\n\u0003\u007f\u0002\u0011\u0011!C!\u0003\u0003C\u0011\"a!\u0001\u0003\u0003%\t%!\"\t\u0013\u0005\u001d\u0005!!A\u0005B\u0005%uaBAGK!\u0005\u0011q\u0012\u0004\u0007I\u0015B\t!!%\t\r]\u001bB\u0011AAO\r\u0019\tyj\u0005\u0001\u0002\"\"IA+\u0006BC\u0002\u0013\u0005\u00111\u0018\u0005\n-V\u0011\t\u0011)A\u0005\u0003{CaaV\u000b\u0005\u0002\u0005\r\u0007bBAf+\u0011\u0005\u0013q\n\u0005\b\u0003\u001b,B\u0011IAh\u0011\u001d\t\to\u0005C\u0002\u0003G4a!a>\u0014\u0003\u0005e\bBCA\u007f9\t\u0005\t\u0015!\u0003\u0002\u0000\"1q\u000b\bC\u0001\u0005\u0013AqAa\u0004\u001d\t\u0003\u0011\t\u0002C\u0005\u0003\u0018M\t\t\u0011b\u0001\u0003\u001a!I\u0011QZ\n\u0002\u0002\u0013\u0005%1\u0006\u0005\n\u0005w\u0019\u0012\u0011!CA\u0005{A\u0011B!\u0016\u0014\u0003\u0003%IAa\u0016\u0003\u001f\t\u0013x.\u00193dCN$X\r\u001a*poNT!AJ\u0014\u0002\r1Lg.\u00197h\u0015\u0005A\u0013A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007-B$iE\u0003\u0001YI*\u0005\n\u0005\u0002.a5\taFC\u00010\u0003\u0015\u00198-\u00197b\u0013\t\tdF\u0001\u0004B]f\u0014VM\u001a\t\u0006gQ2\u0014\tR\u0007\u0002K%\u0011Q'\n\u0002\u0010\u0005J|\u0017\rZ2bgR,G\rT5lKB\u0011q\u0007\u000f\u0007\u0001\t\u0015I\u0004A1\u0001;\u0005\u0005!\u0016CA\u001e?!\tiC(\u0003\u0002>]\t9aj\u001c;iS:<\u0007CA\u0017@\u0013\t\u0001eFA\u0002B]f\u0004\"a\u000e\"\u0005\u000b\r\u0003!\u0019\u0001\u001e\u0003\u000fI{w\u000fV=qKB!1\u0007\u0001\u001cB!\tic)\u0003\u0002H]\t9\u0001K]8ek\u000e$\bCA%R\u001d\tQuJ\u0004\u0002L\u001d6\tAJ\u0003\u0002NS\u00051AH]8pizJ\u0011aL\u0005\u0003!:\nq\u0001]1dW\u0006<W-\u0003\u0002S'\na1+\u001a:jC2L'0\u00192mK*\u0011\u0001KL\u0001\u000bk:$WM\u001d7zS:<W#\u0001\u001c\u0002\u0017UtG-\u001a:ms&tw\rI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0011K\u0006\"\u0002+\u0004\u0001\u00041\u0014\u0001\u0002:faJ,\u0012\u0001R\u0001\tSR,'/\u0019;peR\u0011a,\u0019\t\u0004\u0013~\u000b\u0015B\u00011T\u0005!IE/\u001a:bi>\u0014\b\"\u00022\u0006\u0001\b\u0019\u0017AD2b]&#XM]1uK\u0006C\u0018n\u001d\t\u0006I\u001e4\u0014.Q\u0007\u0002K*\u0011a-J\u0001\bgV\u0004\bo\u001c:u\u0013\tAWM\u0001\bDC:LE/\u001a:bi\u0016\f\u00050[:\u000f\u0005)lgBA\u001al\u0013\taW%\u0001\u0003Bq&\u001c\u0018B\u00018p\u0003\ty\u0016G\u0003\u0002mK\u0005Aam\u001c7e\u0019\u00164G/\u0006\u0002smR\u00191/!\u0002\u0015\u0005QlHCA;y!\t9d\u000fB\u0003x\r\t\u0007!HA\u0001C\u0011\u0015Ih\u0001q\u0001{\u0003=\u0019\u0017M\u001c+sCZ,'o]3Bq&\u001c\b#\u00023|m%\f\u0015B\u0001?f\u0005=\u0019\u0015M\u001c+sCZ,'o]3Bq&\u001c\b\"\u0002@\u0007\u0001\u0004y\u0018!\u00014\u0011\r5\n\t!^!v\u0013\r\t\u0019A\f\u0002\n\rVt7\r^5p]JBa!a\u0002\u0007\u0001\u0004)\u0018!\u0001>\u0002\t\r|\u0007/_\u000b\u0007\u0003\u001b\t\u0019\"a\u0006\u0015\t\u0005=\u0011\u0011\u0004\t\u0007g\u0001\t\t\"!\u0006\u0011\u0007]\n\u0019\u0002B\u0003:\u000f\t\u0007!\bE\u00028\u0003/!QaQ\u0004C\u0002iB\u0001\u0002V\u0004\u0011\u0002\u0003\u0007\u0011\u0011C\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00132+\u0019\ty\"!\u000e\u00028U\u0011\u0011\u0011\u0005\u0016\u0004m\u0005\r2FAA\u0013!\u0011\t9#!\r\u000e\u0005\u0005%\"\u0002BA\u0016\u0003[\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005=b&\u0001\u0006b]:|G/\u0019;j_:LA!a\r\u0002*\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000beB!\u0019\u0001\u001e\u0005\u000b\rC!\u0019\u0001\u001e\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\ti\u0004\u0005\u0003\u0002@\u0005%SBAA!\u0015\u0011\t\u0019%!\u0012\u0002\t1\fgn\u001a\u0006\u0003\u0003\u000f\nAA[1wC&!\u00111JA!\u0005\u0019\u0019FO]5oO\u0006a\u0001O]8ek\u000e$\u0018I]5usV\u0011\u0011\u0011\u000b\t\u0004[\u0005M\u0013bAA+]\t\u0019\u0011J\u001c;\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR\u0019a(a\u0017\t\u0013\u0005u3\"!AA\u0002\u0005E\u0013a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002dA)\u0011QMA6}5\u0011\u0011q\r\u0006\u0004\u0003Sr\u0013AC2pY2,7\r^5p]&\u0019\u0001-a\u001a\u0002\u0011\r\fg.R9vC2$B!!\u001d\u0002xA\u0019Q&a\u001d\n\u0007\u0005UdFA\u0004C_>dW-\u00198\t\u0011\u0005uS\"!AA\u0002y\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011QHA?\u0011%\tiFDA\u0001\u0002\u0004\t\t&\u0001\u0005iCND7i\u001c3f)\t\t\t&\u0001\u0005u_N#(/\u001b8h)\t\ti$\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003c\nY\t\u0003\u0005\u0002^E\t\t\u00111\u0001?\u0003=\u0011%o\\1eG\u0006\u001cH/\u001a3S_^\u001c\bCA\u001a\u0014'\u0011\u0019B&a%\u0011\t\u0005U\u00151T\u0007\u0003\u0003/SA!!'\u0002F\u0005\u0011\u0011n\\\u0005\u0004%\u0006]ECAAH\u0005U\u0011%o\\1eG\u0006\u001cH/\u001a3E\u001bJ{wo]%TKF,B!a)\u0002:N)Q\u0003LAS\u0011B)\u0011*a*\u0002,&\u0019\u0011\u0011V*\u0003\u0015%sG-\u001a=fIN+\u0017\u000fE\u00034\u0003[\u000b\t,C\u0002\u00020\u0016\u0012\u0011\u0002\u0016:b]N\u0004xn]3\u0011\u000bM\n\u0019,a.\n\u0007\u0005UVEA\u0006EK:\u001cXMV3di>\u0014\bcA\u001c\u0002:\u0012)\u0011(\u0006b\u0001uU\u0011\u0011Q\u0018\t\u0006g\u0005}\u0016qW\u0005\u0004\u0003\u0003,#a\u0003#f]N,W*\u0019;sSb$B!!2\u0002JB)\u0011qY\u000b\u000286\t1\u0003\u0003\u0004U1\u0001\u0007\u0011QX\u0001\u0007Y\u0016tw\r\u001e5\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\u0005-\u0016\u0011\u001b\u0005\b\u0003'T\u0002\u0019AA)\u0003\rIG\r\u001f\u0015\b+\u0005]\u0017Q\\Ap!\ri\u0013\u0011\\\u0005\u0004\u00037t#\u0001E*fe&\fGNV3sg&|g.V%E\u0003\u00151\u0018\r\\;f=\u0005\t\u0011AD:dC2\f'o\u00144`\u0005J{wo]\u000b\u0007\u0003K\f\t0!>\u0016\u0005\u0005\u001d\bc\u00023\u0002j\u00065\u00181_\u0005\u0004\u0003W,'\u0001C*dC2\f'o\u00144\u0011\rM\u0002\u0011q^Az!\r9\u0014\u0011\u001f\u0003\u0006sm\u0011\rA\u000f\t\u0004o\u0005UH!B\"\u001c\u0005\u0004Q$a\u0007\"s_\u0006$7-Y:u%><8\u000fR'U_&sG-\u001a=fIN+\u0017/\u0006\u0003\u0002|\n\u00151C\u0001\u000f-\u0003\t\u00117\r\u0005\u00044\u0001\t\u0005!q\u0001\t\u0006g\u0005}&1\u0001\t\u0004o\t\u0015A!B\u001d\u001d\u0005\u0004Q\u0004#B\u001a\u00024\n\rA\u0003\u0002B\u0006\u0005\u001b\u0001R!a2\u001d\u0005\u0007Aq!!@\u001f\u0001\u0004\ty0\u0001\u0007u_&sG-\u001a=fIN+\u0017/\u0006\u0002\u0003\u0014A)\u0011*a*\u0003\u0016A)1'!,\u0003\b\u0005Y\"I]8bI\u000e\f7\u000f\u001e*poN$U\nV8J]\u0012,\u00070\u001a3TKF,BAa\u0007\u0003\"Q!!Q\u0004B\u0012!\u0015\t9\r\bB\u0010!\r9$\u0011\u0005\u0003\u0006s\u0001\u0012\rA\u000f\u0005\b\u0003{\u0004\u0003\u0019\u0001B\u0013!\u0019\u0019\u0004Aa\n\u0003*A)1'a0\u0003 A)1'a-\u0003 U1!Q\u0006B\u001a\u0005o!BAa\f\u0003:A11\u0007\u0001B\u0019\u0005k\u00012a\u000eB\u001a\t\u0015I\u0014E1\u0001;!\r9$q\u0007\u0003\u0006\u0007\u0006\u0012\rA\u000f\u0005\u0007)\u0006\u0002\rA!\r\u0002\u000fUt\u0017\r\u001d9msV1!q\bB%\u0005'\"BA!\u0011\u0003LA)QFa\u0011\u0003H%\u0019!Q\t\u0018\u0003\r=\u0003H/[8o!\r9$\u0011\n\u0003\u0006s\t\u0012\rA\u000f\u0005\n\u0005\u001b\u0012\u0013\u0011!a\u0001\u0005\u001f\n1\u0001\u001f\u00131!\u0019\u0019\u0004Aa\u0012\u0003RA\u0019qGa\u0015\u0005\u000b\r\u0013#\u0019\u0001\u001e\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\te\u0003\u0003BA \u00057JAA!\u0018\u0002B\t1qJ\u00196fGR\u0004"
)
public class BroadcastedRows implements BroadcastedLike, Product, Serializable {
   private final Object underlying;

   public static Option unapply(final BroadcastedRows x$0) {
      return BroadcastedRows$.MODULE$.unapply(x$0);
   }

   public static BroadcastedRows apply(final Object underlying) {
      return BroadcastedRows$.MODULE$.apply(underlying);
   }

   public static BroadcastRowsDMToIndexedSeq BroadcastRowsDMToIndexedSeq(final BroadcastedRows bc) {
      return BroadcastedRows$.MODULE$.BroadcastRowsDMToIndexedSeq(bc);
   }

   public static ScalarOf scalarOf_BRows() {
      return BroadcastedRows$.MODULE$.scalarOf_BRows();
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

   public BroadcastedRows repr() {
      return this;
   }

   public Iterator iterator(final CanIterateAxis canIterateAxis) {
      return canIterateAxis.apply(this.underlying(), Axis._1$.MODULE$);
   }

   public Object foldLeft(final Object z, final Function2 f, final CanTraverseAxis canTraverseAxis) {
      ObjectRef acc = ObjectRef.create(z);
      canTraverseAxis.apply(this.underlying(), Axis._1$.MODULE$, (c) -> {
         $anonfun$foldLeft$1(acc, f, c);
         return BoxedUnit.UNIT;
      });
      return acc.elem;
   }

   public BroadcastedRows copy(final Object underlying) {
      return new BroadcastedRows(underlying);
   }

   public Object copy$default$1() {
      return this.underlying();
   }

   public String productPrefix() {
      return "BroadcastedRows";
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
      return x$1 instanceof BroadcastedRows;
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
            if (x$1 instanceof BroadcastedRows) {
               var2 = true;
            } else {
               var2 = false;
            }

            if (var2) {
               BroadcastedRows var4 = (BroadcastedRows)x$1;
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

   public BroadcastedRows(final Object underlying) {
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

   public static class BroadcastedDMRowsISeq implements IndexedSeq, Serializable {
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
         return this.underlying().rows();
      }

      public Transpose apply(final int idx) {
         return (Transpose)this.underlying().apply(BoxesRunTime.boxToInteger(idx), scala.package..MODULE$.$colon$colon(), HasOps$.MODULE$.canSliceRow());
      }

      public BroadcastedDMRowsISeq(final DenseMatrix underlying) {
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

   public static class BroadcastRowsDMToIndexedSeq {
      private final BroadcastedRows bc;

      public IndexedSeq toIndexedSeq() {
         return new BroadcastedDMRowsISeq((DenseMatrix)this.bc.underlying());
      }

      public BroadcastRowsDMToIndexedSeq(final BroadcastedRows bc) {
         this.bc = bc;
      }
   }
}
