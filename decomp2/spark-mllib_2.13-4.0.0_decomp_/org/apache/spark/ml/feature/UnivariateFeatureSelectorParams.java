package org.apache.spark.ml.feature;

import org.apache.spark.ml.param.DoubleParam;
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
   bytes = "\u0006\u0005\r4\u0001BC\u0006\u0011\u0002\u0007\u00051\"\u0006\u0005\u0006]\u0001!\t\u0001\r\u0005\bi\u0001\u0011\r\u0011\"\u00026\u0011\u0015i\u0005\u0001\"\u0001O\u0011\u001d\u0001\u0006A1A\u0005\u0006UBQA\u0015\u0001\u0005\u00029Cq\u0001\u0016\u0001C\u0002\u0013\u0015Q\u0007C\u0003W\u0001\u0011\u0005a\nC\u0004Y\u0001\t\u0007IQA-\t\u000by\u0003A\u0011A0\u0003?Us\u0017N^1sS\u0006$XMR3biV\u0014XmU3mK\u000e$xN\u001d)be\u0006l7O\u0003\u0002\r\u001b\u00059a-Z1ukJ,'B\u0001\b\u0010\u0003\tiGN\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h'\u0019\u0001a\u0003\b\u0012)WA\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\u0004\"!\b\u0011\u000e\u0003yQ!aH\u0007\u0002\u000bA\f'/Y7\n\u0005\u0005r\"A\u0002)be\u0006l7\u000f\u0005\u0002$M5\tAE\u0003\u0002&=\u000511\u000f[1sK\u0012L!a\n\u0013\u0003\u001d!\u000b7OR3biV\u0014Xm]\"pYB\u00111%K\u0005\u0003U\u0011\u00121\u0002S1t\u0019\u0006\u0014W\r\\\"pYB\u00111\u0005L\u0005\u0003[\u0011\u0012A\u0002S1t\u001fV$\b/\u001e;D_2\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002cA\u0011qCM\u0005\u0003ga\u0011A!\u00168ji\u0006Ya-Z1ukJ,G+\u001f9f+\u00051\u0004cA\u000f8s%\u0011\u0001H\b\u0002\u0006!\u0006\u0014\u0018-\u001c\t\u0003u\u0005s!aO \u0011\u0005qBR\"A\u001f\u000b\u0005yz\u0013A\u0002\u001fs_>$h(\u0003\u0002A1\u00051\u0001K]3eK\u001aL!AQ\"\u0003\rM#(/\u001b8h\u0015\t\u0001\u0005\u0004K\u0002\u0003\u000b.\u0003\"AR%\u000e\u0003\u001dS!\u0001S\b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002K\u000f\n)1+\u001b8dK\u0006\nA*A\u00034]Er\u0013'\u0001\bhKR4U-\u0019;ve\u0016$\u0016\u0010]3\u0016\u0003eB3aA#L\u0003%a\u0017MY3m)f\u0004X\rK\u0002\u0005\u000b.\u000bAbZ3u\u0019\u0006\u0014W\r\u001c+za\u0016D3!B#L\u00035\u0019X\r\\3di&|g.T8eK\"\u001aa!R&\u0002!\u001d,GoU3mK\u000e$\u0018n\u001c8N_\u0012,\u0007fA\u0004F\u0017\u0006\u00112/\u001a7fGRLwN\u001c+ie\u0016\u001c\bn\u001c7e+\u0005Q\u0006CA\u000f\\\u0013\tafDA\u0006E_V\u0014G.\u001a)be\u0006l\u0007f\u0001\u0005F\u0017\u0006)r-\u001a;TK2,7\r^5p]RC'/Z:i_2$W#\u00011\u0011\u0005]\t\u0017B\u00012\u0019\u0005\u0019!u.\u001e2mK\u0002"
)
public interface UnivariateFeatureSelectorParams extends HasFeaturesCol, HasLabelCol, HasOutputCol {
   void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$featureType_$eq(final Param x$1);

   void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$labelType_$eq(final Param x$1);

   void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionMode_$eq(final Param x$1);

   void org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionThreshold_$eq(final DoubleParam x$1);

   Param featureType();

   // $FF: synthetic method
   static String getFeatureType$(final UnivariateFeatureSelectorParams $this) {
      return $this.getFeatureType();
   }

   default String getFeatureType() {
      return (String)this.$(this.featureType());
   }

   Param labelType();

   // $FF: synthetic method
   static String getLabelType$(final UnivariateFeatureSelectorParams $this) {
      return $this.getLabelType();
   }

   default String getLabelType() {
      return (String)this.$(this.labelType());
   }

   Param selectionMode();

   // $FF: synthetic method
   static String getSelectionMode$(final UnivariateFeatureSelectorParams $this) {
      return $this.getSelectionMode();
   }

   default String getSelectionMode() {
      return (String)this.$(this.selectionMode());
   }

   DoubleParam selectionThreshold();

   // $FF: synthetic method
   static double getSelectionThreshold$(final UnivariateFeatureSelectorParams $this) {
      return $this.getSelectionThreshold();
   }

   default double getSelectionThreshold() {
      return BoxesRunTime.unboxToDouble(this.$(this.selectionThreshold()));
   }

   static void $init$(final UnivariateFeatureSelectorParams $this) {
      $this.org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$featureType_$eq(new Param($this, "featureType", "Feature type. Supported options: categorical, continuous.", ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"categorical", "continuous"}))), .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$labelType_$eq(new Param($this, "labelType", "Label type. Supported options: categorical, continuous.", ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"categorical", "continuous"}))), .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionMode_$eq(new Param($this, "selectionMode", "The selection mode. Supported options: numTopFeatures, percentile, fpr, fdr, fwe", ParamValidators$.MODULE$.inArray((Object)((Object[])(new String[]{"numTopFeatures", "percentile", "fpr", "fdr", "fwe"}))), .MODULE$.apply(String.class)));
      $this.org$apache$spark$ml$feature$UnivariateFeatureSelectorParams$_setter_$selectionThreshold_$eq(new DoubleParam($this, "selectionThreshold", "The upper bound of the features that selector will select."));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.selectionMode().$minus$greater("numTopFeatures")}));
   }
}
