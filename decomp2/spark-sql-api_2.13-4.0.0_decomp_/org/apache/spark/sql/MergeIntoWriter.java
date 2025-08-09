package org.apache.spark.sql;

import org.apache.spark.annotation.Experimental;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@Experimental
@ScalaSignature(
   bytes = "\u0006\u0005\u0005ea!B\n\u0015\u0003\u0003i\u0002\"B\u0013\u0001\t\u00031\u0003b\u0002\u001b\u0001\u0001\u0004%I!\u000e\u0005\bs\u0001\u0001\r\u0011\"\u0003;\u0011\u0019\u0001\u0005\u0001)Q\u0005m!1\u0011\t\u0001C\u0001)UBQA\u0011\u0001\u0005\u0002\rCQA\u0011\u0001\u0005\u0002\u001dCQ!\u0014\u0001\u0005\u00029CQ!\u0014\u0001\u0005\u0002ICQ\u0001\u0016\u0001\u0005\u0002UCQ\u0001\u0016\u0001\u0005\u0002eCQa\u0017\u0001\u0005\u0002\u0019BQ\u0001\u0018\u0001\u0007\u0002uCaA\u0018\u0001\u0007\u0012Qy\u0006B\u00023\u0001\r#!R\r\u0003\u0004x\u0001\u0019EA\u0003\u001f\u0005\u0007y\u00021\t\u0002F?\t\u0011\u0005\r\u0001A\"\u0005\u0015\u0003\u000b\u0011q\"T3sO\u0016Le\u000e^8Xe&$XM\u001d\u0006\u0003+Y\t1a]9m\u0015\t9\u0002$A\u0003ta\u0006\u00148N\u0003\u0002\u001a5\u00051\u0011\r]1dQ\u0016T\u0011aG\u0001\u0004_J<7\u0001A\u000b\u0003=-\u001a\"\u0001A\u0010\u0011\u0005\u0001\u001aS\"A\u0011\u000b\u0003\t\nQa]2bY\u0006L!\u0001J\u0011\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}Q\tq\u0005E\u0002)\u0001%j\u0011\u0001\u0006\t\u0003U-b\u0001\u0001B\u0003-\u0001\t\u0007QFA\u0001U#\tq\u0013\u0007\u0005\u0002!_%\u0011\u0001'\t\u0002\b\u001d>$\b.\u001b8h!\t\u0001#'\u0003\u00024C\t\u0019\u0011I\\=\u0002\u001fM\u001c\u0007.Z7b\u000bZ|G.\u001e;j_:,\u0012A\u000e\t\u0003A]J!\u0001O\u0011\u0003\u000f\t{w\u000e\\3b]\u0006\u00192o\u00195f[\u0006,eo\u001c7vi&|gn\u0018\u0013fcR\u00111H\u0010\t\u0003AqJ!!P\u0011\u0003\tUs\u0017\u000e\u001e\u0005\b\u007f\r\t\t\u00111\u00017\u0003\rAH%M\u0001\u0011g\u000eDW-\\1Fm>dW\u000f^5p]\u0002\nac]2iK6\fWI^8mkRLwN\\#oC\ndW\rZ\u0001\fo\",g.T1uG\",G\rF\u0001E!\rAS)K\u0005\u0003\rR\u00111b\u00165f]6\u000bGo\u00195fIR\u0011A\t\u0013\u0005\u0006\u0013\u001e\u0001\rAS\u0001\nG>tG-\u001b;j_:\u0004\"\u0001K&\n\u00051#\"AB\"pYVlg.\u0001\bxQ\u0016tgj\u001c;NCR\u001c\u0007.\u001a3\u0015\u0003=\u00032\u0001\u000b)*\u0013\t\tFC\u0001\bXQ\u0016tgj\u001c;NCR\u001c\u0007.\u001a3\u0015\u0005=\u001b\u0006\"B%\n\u0001\u0004Q\u0015AF<iK:tu\u000e^'bi\u000eDW\r\u001a\"z'>,(oY3\u0015\u0003Y\u00032\u0001K,*\u0013\tAFC\u0001\fXQ\u0016tgj\u001c;NCR\u001c\u0007.\u001a3CsN{WO]2f)\t1&\fC\u0003J\u0017\u0001\u0007!*A\nxSRD7k\u00195f[\u0006,eo\u001c7vi&|g.A\u0003nKJ<W\rF\u0001<\u0003%Ign]3si\u0006cG\u000e\u0006\u0002(A\")\u0011J\u0004a\u0001CB\u0019\u0001E\u0019&\n\u0005\r\f#AB(qi&|g.\u0001\u0004j]N,'\u000f\u001e\u000b\u0004O\u0019<\u0007\"B%\u0010\u0001\u0004\t\u0007\"\u00025\u0010\u0001\u0004I\u0017aA7baB!!.\u001d;K\u001d\tYw\u000e\u0005\u0002mC5\tQN\u0003\u0002o9\u00051AH]8pizJ!\u0001]\u0011\u0002\rA\u0013X\rZ3g\u0013\t\u00118OA\u0002NCBT!\u0001]\u0011\u0011\u0005),\u0018B\u0001<t\u0005\u0019\u0019FO]5oO\u0006IQ\u000f\u001d3bi\u0016\fE\u000e\u001c\u000b\u0004OeT\b\"B%\u0011\u0001\u0004\t\u0007\"B>\u0011\u0001\u00041\u0014A\u00058pi6\u000bGo\u00195fI\nK8k\\;sG\u0016\fa!\u001e9eCR,G#B\u0014\u007f\u007f\u0006\u0005\u0001\"B%\u0012\u0001\u0004\t\u0007\"\u00025\u0012\u0001\u0004I\u0007\"B>\u0012\u0001\u00041\u0014A\u00023fY\u0016$X\rF\u0003(\u0003\u000f\tI\u0001C\u0003J%\u0001\u0007\u0011\rC\u0003|%\u0001\u0007a\u0007K\u0002\u0001\u0003\u001b\u0001B!a\u0004\u0002\u00165\u0011\u0011\u0011\u0003\u0006\u0004\u0003'1\u0012AC1o]>$\u0018\r^5p]&!\u0011qCA\t\u00051)\u0005\u0010]3sS6,g\u000e^1m\u0001"
)
public abstract class MergeIntoWriter {
   private boolean schemaEvolution = false;

   private boolean schemaEvolution() {
      return this.schemaEvolution;
   }

   private void schemaEvolution_$eq(final boolean x$1) {
      this.schemaEvolution = x$1;
   }

   public boolean schemaEvolutionEnabled() {
      return this.schemaEvolution();
   }

   public WhenMatched whenMatched() {
      return new WhenMatched(this, .MODULE$);
   }

   public WhenMatched whenMatched(final Column condition) {
      return new WhenMatched(this, new Some(condition));
   }

   public WhenNotMatched whenNotMatched() {
      return new WhenNotMatched(this, .MODULE$);
   }

   public WhenNotMatched whenNotMatched(final Column condition) {
      return new WhenNotMatched(this, new Some(condition));
   }

   public WhenNotMatchedBySource whenNotMatchedBySource() {
      return new WhenNotMatchedBySource(this, .MODULE$);
   }

   public WhenNotMatchedBySource whenNotMatchedBySource(final Column condition) {
      return new WhenNotMatchedBySource(this, new Some(condition));
   }

   public MergeIntoWriter withSchemaEvolution() {
      this.schemaEvolution_$eq(true);
      return this;
   }

   public abstract void merge();

   public abstract MergeIntoWriter insertAll(final Option condition);

   public abstract MergeIntoWriter insert(final Option condition, final Map map);

   public abstract MergeIntoWriter updateAll(final Option condition, final boolean notMatchedBySource);

   public abstract MergeIntoWriter update(final Option condition, final Map map, final boolean notMatchedBySource);

   public abstract MergeIntoWriter delete(final Option condition, final boolean notMatchedBySource);
}
