package org.apache.avro.path;

import java.util.ArrayList;
import java.util.List;
import org.apache.avro.Schema;
import org.apache.avro.util.SchemaUtil;

public class TracingNullPointException extends NullPointerException implements PathTracingException {
   private final NullPointerException cause;
   private final Schema expected;
   private final boolean customCoderUsed;
   private final List reversePath;

   public TracingNullPointException(NullPointerException cause, Schema expected, boolean customCoderUsed) {
      this.cause = cause;
      this.expected = expected;
      this.customCoderUsed = customCoderUsed;
      this.reversePath = new ArrayList(3);
   }

   public void tracePath(PathElement step) {
      this.reversePath.add(step);
   }

   public synchronized NullPointerException getCause() {
      return this.cause;
   }

   public NullPointerException summarize(Schema root) {
      StringBuilder sb = new StringBuilder();
      sb.append("null value for (non-nullable) ");
      if (this.reversePath != null && !this.reversePath.isEmpty()) {
         PathElement innerMostElement = (PathElement)this.reversePath.get(0);
         boolean isNullMapKey = innerMostElement instanceof MapKeyPredicate && ((MapKeyPredicate)innerMostElement).getKey() == null;
         if (isNullMapKey) {
            sb.delete(0, sb.length());
            sb.append("null key in map");
         } else {
            sb.append(SchemaUtil.describe(this.expected));
         }

         sb.append(" at ");
         if (root != null) {
            sb.append(SchemaUtil.describe(root));
         }

         for(int i = this.reversePath.size() - 1; i >= 0; --i) {
            PathElement step = (PathElement)this.reversePath.get(i);
            sb.append(step.toString());
         }
      } else if (this.customCoderUsed) {
         sb.append("field or map key. No further details available as custom coders were used");
      } else {
         sb.append(SchemaUtil.describe(this.expected));
      }

      NullPointerException summary = new NullPointerException(sb.toString());
      summary.initCause(this.cause);
      return summary;
   }
}
