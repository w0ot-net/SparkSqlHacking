package breeze.linalg;

import breeze.generic.UFunc;
import breeze.stats.distributions.RandBasis;
import scala.;
import scala.collection.BuildFrom;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005t!B\u0005\u000b\u0011\u0003ya!B\t\u000b\u0011\u0003\u0011\u0002\"B\u0010\u0002\t\u0003\u0001\u0003\"B\u0011\u0002\t\u0007\u0011\u0003\"\u0002%\u0002\t\u0007I\u0005\"\u0002.\u0002\t\u0007Y\u0006\"B3\u0002\t\u00071\u0007bBA\u0012\u0003\u0011\r\u0011Q\u0005\u0005\b\u0003\u0007\nA1AA#\u0003\u001d\u0019\b.\u001e4gY\u0016T!a\u0003\u0007\u0002\r1Lg.\u00197h\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005A\tQ\"\u0001\u0006\u0003\u000fMDWO\u001a4mKN\u0019\u0011aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQR$D\u0001\u001c\u0015\taB\"A\u0004hK:,'/[2\n\u0005yY\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u0010\u0003YIW\u000e\u001d7TQV4g\r\\3`\u0003J\u0014x,Z9`\u0003J\u0014XCA\u0012.)\r!cG\u0010\t\u0005K\u0019B\u0003&D\u0001\u0002\u0013\t9SD\u0001\u0003J[Bd\u0007c\u0001\u000b*W%\u0011!&\u0006\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003Y5b\u0001\u0001B\u0003/\u0007\t\u0007qFA\u0001U#\t\u00014\u0007\u0005\u0002\u0015c%\u0011!'\u0006\u0002\b\u001d>$\b.\u001b8h!\t!B'\u0003\u00026+\t\u0019\u0011I\\=\t\u000b]\u001a\u00019\u0001\u001d\u0002\u0005\r$\bcA\u001d=W5\t!H\u0003\u0002<+\u00059!/\u001a4mK\u000e$\u0018BA\u001f;\u0005!\u0019E.Y:t)\u0006<\u0007\"B \u0004\u0001\b\u0001\u0015A\u0001:c!\t\te)D\u0001C\u0015\t\u0019E)A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003\u000b2\tQa\u001d;biNL!a\u0012\"\u0003\u0013I\u000bg\u000e\u001a\"bg&\u001c\u0018AI5na2\u001c\u0006.\u001e4gY\u0016|\u0016I\u001d:`\u0003J\u0014xLQ8pY\u0016\fgnX3r?\u0006\u0013(/\u0006\u0002K!R\u00111\n\u0017\t\u0007K1s\u0015+\u0016(\n\u00055k\"!B%na2\u001c\u0004c\u0001\u000b*\u001fB\u0011A\u0006\u0015\u0003\u0006]\u0011\u0011\ra\f\t\u0004)%\u0012\u0006C\u0001\u000bT\u0013\t!VCA\u0002J]R\u0004\"\u0001\u0006,\n\u0005]+\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006o\u0011\u0001\u001d!\u0017\t\u0004sqz\u0015AG5na2\u001c\u0006.\u001e4gY\u0016|\u0016I\u001d:`\u0003J\u0014x,Z9`\u0003J\u0014XC\u0001/c)\ti6\rE\u0003&=\u0002\f\u0006-\u0003\u0002`;\t)\u0011*\u001c9meA\u0019A#K1\u0011\u00051\u0012G!\u0002\u0018\u0006\u0005\u0004y\u0003\"B\u001c\u0006\u0001\b!\u0007cA\u001d=C\u0006A\u0012.\u001c9m'\",hM\u001a7f?\u000e{G\u000e\\0fc~\u001bu\u000e\u001c7\u0016\t\u001dT70\u001c\u000b\u0006Q>d\u0018\u0011\u0005\t\u0005K\u0019JG\u000e\u0005\u0002-U\u0012)1N\u0002b\u0001_\t!1i\u001c7m!\taS\u000eB\u0003o\r\t\u0007qFA\u0004D_2d'+Z:\t\u000bA4\u00019A9\u0002\tYLWm\u001e\t\u0005)ILG/\u0003\u0002t+\t\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\u0004kbTX\"\u0001<\u000b\u0005],\u0012AC2pY2,7\r^5p]&\u0011\u0011P\u001e\u0002\u000b\u0013:$W\r_3e'\u0016\f\bC\u0001\u0017|\t\u0015qcA1\u00010\u0011\u0015ih\u0001q\u0001\u007f\u0003\r\u0019'M\u001a\t\u0007\u007f\u0006m\u0011N\u001f7\u000f\t\u0005\u0005\u0011Q\u0003\b\u0005\u0003\u0007\t\tB\u0004\u0003\u0002\u0006\u0005=a\u0002BA\u0004\u0003\u001bi!!!\u0003\u000b\u0007\u0005-a\"\u0001\u0004=e>|GOP\u0005\u0002-%\u0011q/F\u0005\u0004\u0003'1\u0018AB2p[B\fG/\u0003\u0003\u0002\u0018\u0005e\u0011a\u00029bG.\fw-\u001a\u0006\u0004\u0003'1\u0018\u0002BA\u000f\u0003?\u0011\u0011BQ;jY\u00124%o\\7\u000b\t\u0005]\u0011\u0011\u0004\u0005\u0006\u007f\u0019\u0001\u001d\u0001Q\u0001\u0015S6\u0004Hn\u00155vM\u001adWm\u0018#W?\u0016\fx\f\u0012,\u0016\t\u0005\u001d\u00121\u0007\u000b\t\u0003S\t)$!\u0010\u0002BA1QEJA\u0016\u0003W\u0001R\u0001EA\u0017\u0003cI1!a\f\u000b\u0005-!UM\\:f-\u0016\u001cGo\u001c:\u0011\u00071\n\u0019\u0004B\u0003/\u000f\t\u0007q\u0006C\u0004\u00028\u001d\u0001\u001d!!\u000f\u0002\u000f\u0005\u0014(/S7qYB1QEJA\u001e\u0003w\u0001B\u0001F\u0015\u00022!1qg\u0002a\u0002\u0003\u007f\u0001B!\u000f\u001f\u00022!)qh\u0002a\u0002\u0001\u0006!\u0012.\u001c9m'\",hM\u001a7f?\u0012ku,Z9`\t6+B!a\u0012\u0002TQA\u0011\u0011JA+\u00037\ny\u0006\u0005\u0004&M\u0005-\u00131\n\t\u0006!\u00055\u0013\u0011K\u0005\u0004\u0003\u001fR!a\u0003#f]N,W*\u0019;sSb\u00042\u0001LA*\t\u0015q\u0003B1\u00010\u0011\u001d\t9\u0004\u0003a\u0002\u0003/\u0002b!\n\u0014\u0002Z\u0005e\u0003\u0003\u0002\u000b*\u0003#Baa\u000e\u0005A\u0004\u0005u\u0003\u0003B\u001d=\u0003#BQa\u0010\u0005A\u0004\u0001\u0003"
)
public final class shuffle {
   public static UFunc.UImpl implShuffle_DM_eq_DM(final UFunc.UImpl arrImpl, final ClassTag ct, final RandBasis rb) {
      return shuffle$.MODULE$.implShuffle_DM_eq_DM(arrImpl, ct, rb);
   }

   public static UFunc.UImpl implShuffle_DV_eq_DV(final UFunc.UImpl arrImpl, final ClassTag ct, final RandBasis rb) {
      return shuffle$.MODULE$.implShuffle_DV_eq_DV(arrImpl, ct, rb);
   }

   public static UFunc.UImpl implShuffle_Coll_eq_Coll(final .less.colon.less view, final BuildFrom cbf, final RandBasis rb) {
      return shuffle$.MODULE$.implShuffle_Coll_eq_Coll(view, cbf, rb);
   }

   public static UFunc.UImpl2 implShuffle_Arr_Arr_eq_Arr(final ClassTag ct) {
      return shuffle$.MODULE$.implShuffle_Arr_Arr_eq_Arr(ct);
   }

   public static UFunc.UImpl3 implShuffle_Arr_Arr_Boolean_eq_Arr(final ClassTag ct) {
      return shuffle$.MODULE$.implShuffle_Arr_Arr_Boolean_eq_Arr(ct);
   }

   public static UFunc.UImpl implShuffle_Arr_eq_Arr(final ClassTag ct, final RandBasis rb) {
      return shuffle$.MODULE$.implShuffle_Arr_eq_Arr(ct, rb);
   }

   public static Object withSink(final Object s) {
      return shuffle$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return shuffle$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return shuffle$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return shuffle$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return shuffle$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return shuffle$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return shuffle$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return shuffle$.MODULE$.apply(v, impl);
   }
}
