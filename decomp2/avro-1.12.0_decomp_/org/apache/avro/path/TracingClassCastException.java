package org.apache.avro.path;

import java.util.ArrayList;
import java.util.List;
import org.apache.avro.Schema;
import org.apache.avro.util.SchemaUtil;

public class TracingClassCastException extends ClassCastException implements PathTracingException {
   private final ClassCastException cause;
   private final Object datum;
   private final Schema expected;
   private final boolean customCoderUsed;
   private final List reversePath;

   public TracingClassCastException(ClassCastException cause, Object datum, Schema expected, boolean customCoderUsed) {
      this.cause = cause;
      this.datum = datum;
      this.expected = expected;
      this.customCoderUsed = customCoderUsed;
      this.reversePath = new ArrayList(3);
   }

   public void tracePath(PathElement step) {
      this.reversePath.add(step);
   }

   public synchronized ClassCastException getCause() {
      return this.cause;
   }

   public ClassCastException summarize(Schema root) {
      StringBuilder sb = new StringBuilder();
      sb.append("value ").append(SchemaUtil.describe(this.datum));
      sb.append(" cannot be cast to expected type ").append(SchemaUtil.describe(this.expected));
      if (this.reversePath != null && !this.reversePath.isEmpty()) {
         sb.append(" at ");
         if (root != null) {
            sb.append(SchemaUtil.describe(root));
         }

         for(int i = this.reversePath.size() - 1; i >= 0; --i) {
            PathElement step = (PathElement)this.reversePath.get(i);
            sb.append(step.toString());
         }
      } else if (this.customCoderUsed) {
         sb.append(". No further details available as custom coders were used");
      }

      ClassCastException summary = new ClassCastException(sb.toString());
      summary.initCause(this.cause);
      return summary;
   }
}
