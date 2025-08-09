package org.apache.spark.ml.feature;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005Y4\u0001BD\b\u0011\u0002\u0007\u0005q\"\u0007\u0005\u0006e\u0001!\t\u0001\u000e\u0005\bq\u0001\u0011\r\u0011\"\u0002:\u0011\u00151\u0005\u0001\"\u0001H\u0011\u001da\u0005A1A\u0005\u00065CQA\u0015\u0001\u0005\u0002MCq\u0001\u0017\u0001C\u0002\u0013\u0015Q\nC\u0003[\u0001\u0011\u00051\u000bC\u0004]\u0001\t\u0007IQA'\t\u000by\u0003A\u0011A*\t\u000f}\u0003!\u0019!C\u0003\u001b\")\u0011\r\u0001C\u0001'\"9!\r\u0001b\u0001\n\u000b\u0019\u0007\"B:\u0001\t\u0003!(AD*fY\u0016\u001cGo\u001c:QCJ\fWn\u001d\u0006\u0003!E\tqAZ3biV\u0014XM\u0003\u0002\u0013'\u0005\u0011Q\u000e\u001c\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sON1\u0001A\u0007\u0011'Y=\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0007CA\u0011%\u001b\u0005\u0011#BA\u0012\u0012\u0003\u0015\u0001\u0018M]1n\u0013\t)#E\u0001\u0004QCJ\fWn\u001d\t\u0003O)j\u0011\u0001\u000b\u0006\u0003S\t\naa\u001d5be\u0016$\u0017BA\u0016)\u00059A\u0015m\u001d$fCR,(/Z:D_2\u0004\"aJ\u0017\n\u00059B#a\u0003%bg2\u000b'-\u001a7D_2\u0004\"a\n\u0019\n\u0005EB#\u0001\u0004%bg>+H\u000f];u\u0007>d\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003U\u0002\"a\u0007\u001c\n\u0005]b\"\u0001B+oSR\faB\\;n)>\u0004h)Z1ukJ,7/F\u0001;!\t\t3(\u0003\u0002=E\tA\u0011J\u001c;QCJ\fW\u000eK\u0002\u0003}\u0011\u0003\"a\u0010\"\u000e\u0003\u0001S!!Q\n\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002D\u0001\n)1+\u001b8dK\u0006\nQ)A\u00034]Er\u0003'A\thKRtU/\u001c+pa\u001a+\u0017\r^;sKN,\u0012\u0001\u0013\t\u00037%K!A\u0013\u000f\u0003\u0007%sG\u000fK\u0002\u0004}\u0011\u000b!\u0002]3sG\u0016tG/\u001b7f+\u0005q\u0005CA\u0011P\u0013\t\u0001&EA\u0006E_V\u0014G.\u001a)be\u0006l\u0007f\u0001\u0003?\t\u0006iq-\u001a;QKJ\u001cWM\u001c;jY\u0016,\u0012\u0001\u0016\t\u00037UK!A\u0016\u000f\u0003\r\u0011{WO\u00197fQ\r)a\bR\u0001\u0004MB\u0014\bf\u0001\u0004?\t\u00061q-\u001a;GaJD3a\u0002 E\u0003\r1GM\u001d\u0015\u0004\u0011y\"\u0015AB4fi\u001a#'/A\u0002go\u0016D3A\u0003 E\u0003\u00199W\r\u001e$xK\u0006a1/\u001a7fGR|'\u000fV=qKV\tA\rE\u0002\"K\u001eL!A\u001a\u0012\u0003\u000bA\u000b'/Y7\u0011\u0005!|gBA5n!\tQG$D\u0001l\u0015\ta7'\u0001\u0004=e>|GOP\u0005\u0003]r\ta\u0001\u0015:fI\u00164\u0017B\u00019r\u0005\u0019\u0019FO]5oO*\u0011a\u000e\b\u0015\u0004\u0019y\"\u0015aD4fiN+G.Z2u_J$\u0016\u0010]3\u0016\u0003\u001dD3!\u0004 E\u0001"
)
public interface SelectorParams extends HasFeaturesCol, HasLabelCol, HasOutputCol {
   void org$apache$spark$ml$feature$SelectorParams$_setter_$numTopFeatures_$eq(final IntParam x$1);

   void org$apache$spark$ml$feature$SelectorParams$_setter_$percentile_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$SelectorParams$_setter_$fpr_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$SelectorParams$_setter_$fdr_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$SelectorParams$_setter_$fwe_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$feature$SelectorParams$_setter_$selectorType_$eq(final Param x$1);

   IntParam numTopFeatures();

   // $FF: synthetic method
   static int getNumTopFeatures$(final SelectorParams $this) {
      return $this.getNumTopFeatures();
   }

   default int getNumTopFeatures() {
      return BoxesRunTime.unboxToInt(this.$(this.numTopFeatures()));
   }

   DoubleParam percentile();

   // $FF: synthetic method
   static double getPercentile$(final SelectorParams $this) {
      return $this.getPercentile();
   }

   default double getPercentile() {
      return BoxesRunTime.unboxToDouble(this.$(this.percentile()));
   }

   DoubleParam fpr();

   // $FF: synthetic method
   static double getFpr$(final SelectorParams $this) {
      return $this.getFpr();
   }

   default double getFpr() {
      return BoxesRunTime.unboxToDouble(this.$(this.fpr()));
   }

   DoubleParam fdr();

   // $FF: synthetic method
   static double getFdr$(final SelectorParams $this) {
      return $this.getFdr();
   }

   default double getFdr() {
      return BoxesRunTime.unboxToDouble(this.$(this.fdr()));
   }

   DoubleParam fwe();

   // $FF: synthetic method
   static double getFwe$(final SelectorParams $this) {
      return $this.getFwe();
   }

   default double getFwe() {
      return BoxesRunTime.unboxToDouble(this.$(this.fwe()));
   }

   Param selectorType();

   // $FF: synthetic method
   static String getSelectorType$(final SelectorParams $this) {
      return $this.getSelectorType();
   }

   default String getSelectorType() {
      return (String)this.$(this.selectorType());
   }

   static void $init$(final SelectorParams $this) {
      $this.org$apache$spark$ml$feature$SelectorParams$_setter_$numTopFeatures_$eq(new IntParam($this, "numTopFeatures", "Number of features that selector will select, ordered by ascending p-value. If the number of features is < numTopFeatures, then this will select all features.", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$feature$SelectorParams$_setter_$percentile_$eq(new DoubleParam($this, "percentile", "Percentile of features that selector will select, ordered by ascending p-value.", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.org$apache$spark$ml$feature$SelectorParams$_setter_$fpr_$eq(new DoubleParam($this, "fpr", "The highest p-value for features to be kept.", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.org$apache$spark$ml$feature$SelectorParams$_setter_$fdr_$eq(new DoubleParam($this, "fdr", "The upper bound of the expected false discovery rate.", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.org$apache$spark$ml$feature$SelectorParams$_setter_$fwe_$eq(new DoubleParam($this, "fwe", "The upper bound of the expected family-wise error rate.", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F)));
      $this.org$apache$spark$ml$feature$SelectorParams$_setter_$selectorType_$eq(new Param($this, "selectorType", "The selector type. Supported options: numTopFeatures, percentile, fpr, fdr, fwe", ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"numTopFeatures", "percentile", "fpr", "fdr", "fwe"}))), .MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.numTopFeatures().$minus$greater(BoxesRunTime.boxToInteger(50)), $this.percentile().$minus$greater(BoxesRunTime.boxToDouble(0.1)), $this.fpr().$minus$greater(BoxesRunTime.boxToDouble(0.05)), $this.fdr().$minus$greater(BoxesRunTime.boxToDouble(0.05)), $this.fwe().$minus$greater(BoxesRunTime.boxToDouble(0.05)), $this.selectorType().$minus$greater("numTopFeatures")}));
   }
}
