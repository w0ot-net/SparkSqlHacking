package org.apache.spark.ml.feature;

import scala.MatchError;
import scala.collection.immutable.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000592\u0001\u0002B\u0003\u0011\u0002\u0007\u0005ra\u0004\u0005\u00065\u0001!\t\u0001\b\u0005\u0006A\u0001!\t!\t\u0005\u0006K\u0001!\tE\n\u0002\u0011\u0013:$XM]1di\u0006\u0014G.\u001a+fe6T!AB\u0004\u0002\u000f\u0019,\u0017\r^;sK*\u0011\u0001\"C\u0001\u0003[2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0004\u0001A1\u0002CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\r\u0005\u0002\u001815\tQ!\u0003\u0002\u001a\u000b\t!A+\u001a:n\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u000f\u0011\u0005Eq\u0012BA\u0010\u0013\u0005\u0011)f.\u001b;\u0002\u001b\u0005\u001c\u0018J\u001c;fe\u0006\u001cG/[8o+\u0005\u0011\u0003CA\f$\u0013\t!SAA\tD_2,XN\\%oi\u0016\u0014\u0018m\u0019;j_:\f\u0001\"\u001b8uKJ\f7\r\u001e\u000b\u0003-\u001dBQ\u0001K\u0002A\u0002Y\tQa\u001c;iKJL3\u0001\u0001\u0016-\u0013\tYSAA\u0005D_2,XN\u001c*fM*\u0011Q&B\u0001\u0004\t>$\b"
)
public interface InteractableTerm extends Term {
   // $FF: synthetic method
   static ColumnInteraction asInteraction$(final InteractableTerm $this) {
      return $this.asInteraction();
   }

   default ColumnInteraction asInteraction() {
      return new ColumnInteraction(new .colon.colon(this, scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   static Term interact$(final InteractableTerm $this, final Term other) {
      return $this.interact(other);
   }

   default Term interact(final Term other) {
      if (other instanceof InteractableTerm var4) {
         return this.asInteraction().interact(var4.asInteraction());
      } else if (other instanceof ColumnInteraction var5) {
         return this.asInteraction().interact(var5);
      } else if (other instanceof Terms var6) {
         return this.asTerms().interact(var6);
      } else if (other != null) {
         return other.interact(this);
      } else {
         throw new MatchError(other);
      }
   }

   static void $init$(final InteractableTerm $this) {
   }
}
