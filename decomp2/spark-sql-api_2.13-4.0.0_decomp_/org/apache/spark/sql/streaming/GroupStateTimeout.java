package org.apache.spark.sql.streaming;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.catalyst.plans.logical.EventTimeTimeout$;
import org.apache.spark.sql.catalyst.plans.logical.NoTimeout$;
import org.apache.spark.sql.catalyst.plans.logical.ProcessingTimeTimeout$;

@Evolving
public class GroupStateTimeout {
   public static GroupStateTimeout ProcessingTimeTimeout() {
      return ProcessingTimeTimeout$.MODULE$;
   }

   public static GroupStateTimeout EventTimeTimeout() {
      return EventTimeTimeout$.MODULE$;
   }

   public static GroupStateTimeout NoTimeout() {
      return NoTimeout$.MODULE$;
   }
}
