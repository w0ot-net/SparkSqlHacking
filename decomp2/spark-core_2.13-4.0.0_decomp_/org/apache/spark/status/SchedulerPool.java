package org.apache.spark.status;

import scala.Predef.;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r3Aa\u0002\u0005\u0005#!Aa\u0003\u0001B\u0001B\u0003%q\u0003C\u0003%\u0001\u0011\u0005Q\u0005C\u0004)\u0001\u0001\u0007I\u0011A\u0015\t\u000fY\u0002\u0001\u0019!C\u0001o!1Q\b\u0001Q!\n)BQA\u0010\u0001\u0005R}\u0012QbU2iK\u0012,H.\u001a:Q_>d'BA\u0005\u000b\u0003\u0019\u0019H/\u0019;vg*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0005\u0001\u0011\u0002CA\n\u0015\u001b\u0005A\u0011BA\u000b\t\u0005)a\u0015N^3F]RLG/_\u0001\u0005]\u0006lW\r\u0005\u0002\u0019C9\u0011\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00039A\ta\u0001\u0010:p_Rt$\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\u0012A\u0002)sK\u0012,g-\u0003\u0002#G\t11\u000b\u001e:j]\u001eT!\u0001I\u000f\u0002\rqJg.\u001b;?)\t1s\u0005\u0005\u0002\u0014\u0001!)aC\u0001a\u0001/\u0005A1\u000f^1hK&#7/F\u0001+!\rY\u0003GM\u0007\u0002Y)\u0011QFL\u0001\nS6lW\u000f^1cY\u0016T!aL\u000f\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u00022Y\t\u00191+\u001a;\u0011\u0005M\"T\"A\u000f\n\u0005Uj\"aA%oi\u0006a1\u000f^1hK&#7o\u0018\u0013fcR\u0011\u0001h\u000f\t\u0003geJ!AO\u000f\u0003\tUs\u0017\u000e\u001e\u0005\by\u0011\t\t\u00111\u0001+\u0003\rAH%M\u0001\ngR\fw-Z%eg\u0002\n\u0001\u0002Z8Va\u0012\fG/\u001a\u000b\u0002\u0001B\u00111'Q\u0005\u0003\u0005v\u00111!\u00118z\u0001"
)
public class SchedulerPool extends LiveEntity {
   private final String name;
   private Set stageIds;

   public Set stageIds() {
      return this.stageIds;
   }

   public void stageIds_$eq(final Set x$1) {
      this.stageIds = x$1;
   }

   public Object doUpdate() {
      return new PoolData(this.name, this.stageIds());
   }

   public SchedulerPool(final String name) {
      this.name = name;
      this.stageIds = (Set).MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$);
   }
}
