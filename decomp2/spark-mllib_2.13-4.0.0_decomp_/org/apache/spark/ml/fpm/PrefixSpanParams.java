package org.apache.spark.ml.fpm;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.LongParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.Params;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005!4\u0001BC\u0006\u0011\u0002\u0007\u00051\"\u0006\u0005\u0006E\u0001!\t\u0001\n\u0005\bQ\u0001\u0011\r\u0011\"\u0001*\u0011\u00151\u0004\u0001\"\u00018\u0011\u001da\u0004A1A\u0005\u0002uBQA\u0011\u0001\u0005\u0002\rCq\u0001\u0013\u0001C\u0002\u0013\u0005\u0011\nC\u0003O\u0001\u0011\u0005q\nC\u0004U\u0001\t\u0007I\u0011A+\t\u000b\u0015\u0004A\u0011\u00014\u0003!A\u0013XMZ5y'B\fg\u000eU1sC6\u001c(B\u0001\u0007\u000e\u0003\r1\u0007/\u001c\u0006\u0003\u001d=\t!!\u001c7\u000b\u0005A\t\u0012!B:qCJ\\'B\u0001\n\u0014\u0003\u0019\t\u0007/Y2iK*\tA#A\u0002pe\u001e\u001c2\u0001\u0001\f\u001d!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0011Q\u0004I\u0007\u0002=)\u0011q$D\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003Cy\u0011a\u0001U1sC6\u001c\u0018A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003\u0015\u0002\"a\u0006\u0014\n\u0005\u001dB\"\u0001B+oSR\f!\"\\5o'V\u0004\bo\u001c:u+\u0005Q\u0003CA\u000f,\u0013\tacDA\u0006E_V\u0014G.\u001a)be\u0006l\u0007f\u0001\u0002/iA\u0011qFM\u0007\u0002a)\u0011\u0011gD\u0001\u000bC:tw\u000e^1uS>t\u0017BA\u001a1\u0005\u0015\u0019\u0016N\\2fC\u0005)\u0014!\u0002\u001a/i9\u0002\u0014!D4fi6KgnU;qa>\u0014H/F\u00019!\t9\u0012(\u0003\u0002;1\t1Ai\\;cY\u0016D3a\u0001\u00185\u0003Ai\u0017\r\u001f)biR,'O\u001c'f]\u001e$\b.F\u0001?!\tir(\u0003\u0002A=\tA\u0011J\u001c;QCJ\fW\u000eK\u0002\u0005]Q\n1cZ3u\u001b\u0006D\b+\u0019;uKJtG*\u001a8hi\",\u0012\u0001\u0012\t\u0003/\u0015K!A\u0012\r\u0003\u0007%sG\u000fK\u0002\u0006]Q\n!#\\1y\u0019>\u001c\u0017\r\u001c)s_*$%iU5{KV\t!\n\u0005\u0002\u001e\u0017&\u0011AJ\b\u0002\n\u0019>tw\rU1sC6D3A\u0002\u00185\u0003U9W\r^'bq2{7-\u00197Qe>TGIQ*ju\u0016,\u0012\u0001\u0015\t\u0003/EK!A\u0015\r\u0003\t1{gn\u001a\u0015\u0004\u000f9\"\u0014aC:fcV,gnY3D_2,\u0012A\u0016\t\u0004;]K\u0016B\u0001-\u001f\u0005\u0015\u0001\u0016M]1n!\tQ\u0016M\u0004\u0002\\?B\u0011A\fG\u0007\u0002;*\u0011alI\u0001\u0007yI|w\u000e\u001e \n\u0005\u0001D\u0012A\u0002)sK\u0012,g-\u0003\u0002cG\n11\u000b\u001e:j]\u001eT!\u0001\u0019\r)\u0007!qC'\u0001\bhKR\u001cV-];f]\u000e,7i\u001c7\u0016\u0003eC3!\u0003\u00185\u0001"
)
public interface PrefixSpanParams extends Params {
   void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$minSupport_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$maxPatternLength_$eq(final IntParam x$1);

   void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$maxLocalProjDBSize_$eq(final LongParam x$1);

   void org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$sequenceCol_$eq(final Param x$1);

   DoubleParam minSupport();

   // $FF: synthetic method
   static double getMinSupport$(final PrefixSpanParams $this) {
      return $this.getMinSupport();
   }

   default double getMinSupport() {
      return BoxesRunTime.unboxToDouble(this.$(this.minSupport()));
   }

   IntParam maxPatternLength();

   // $FF: synthetic method
   static int getMaxPatternLength$(final PrefixSpanParams $this) {
      return $this.getMaxPatternLength();
   }

   default int getMaxPatternLength() {
      return BoxesRunTime.unboxToInt(this.$(this.maxPatternLength()));
   }

   LongParam maxLocalProjDBSize();

   // $FF: synthetic method
   static long getMaxLocalProjDBSize$(final PrefixSpanParams $this) {
      return $this.getMaxLocalProjDBSize();
   }

   default long getMaxLocalProjDBSize() {
      return BoxesRunTime.unboxToLong(this.$(this.maxLocalProjDBSize()));
   }

   Param sequenceCol();

   // $FF: synthetic method
   static String getSequenceCol$(final PrefixSpanParams $this) {
      return $this.getSequenceCol();
   }

   default String getSequenceCol() {
      return (String)this.$(this.sequenceCol());
   }

   static void $init$(final PrefixSpanParams $this) {
      $this.org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$minSupport_$eq(new DoubleParam($this, "minSupport", "The minimal support level of the sequential pattern. Sequential pattern that appears more than (minSupport * size-of-the-dataset) times will be output.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$maxPatternLength_$eq(new IntParam($this, "maxPatternLength", "The maximal length of the sequential pattern.", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$maxLocalProjDBSize_$eq(new LongParam($this, "maxLocalProjDBSize", "The maximum number of items (including delimiters used in the internal storage format) allowed in a projected database before local processing. If a projected database exceeds this size, another iteration of distributed prefix growth is run.", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$fpm$PrefixSpanParams$_setter_$sequenceCol_$eq(new Param($this, "sequenceCol", "The name of the sequence column in dataset, rows with nulls in this column are ignored.", .MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.minSupport().$minus$greater(BoxesRunTime.boxToDouble(0.1)), $this.maxPatternLength().$minus$greater(BoxesRunTime.boxToInteger(10)), $this.maxLocalProjDBSize().$minus$greater(BoxesRunTime.boxToLong(32000000L)), $this.sequenceCol().$minus$greater("sequence")}));
   }
}
