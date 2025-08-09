package org.apache.avro.path;

import java.util.ArrayList;
import java.util.List;
import org.apache.avro.AvroTypeException;
import org.apache.avro.Schema;
import org.apache.avro.util.SchemaUtil;

public class TracingAvroTypeException extends AvroTypeException implements PathTracingException {
   private final List reversePath = new ArrayList(3);

   public TracingAvroTypeException(AvroTypeException cause) {
      super(cause.getMessage(), cause);
   }

   public void tracePath(PathElement step) {
      this.reversePath.add(step);
   }

   public AvroTypeException summarize(Schema root) {
      AvroTypeException cause = (AvroTypeException)this.getCause();
      StringBuilder sb = new StringBuilder();
      sb.append(cause.getMessage());
      if (this.reversePath != null && !this.reversePath.isEmpty()) {
         sb.append(" at ");
         if (root != null) {
            sb.append(SchemaUtil.describe(root));
         }

         for(int i = this.reversePath.size() - 1; i >= 0; --i) {
            PathElement step = (PathElement)this.reversePath.get(i);
            sb.append(step.toString());
         }
      }

      AvroTypeException summary = new AvroTypeException(sb.toString());
      summary.initCause(cause);
      return summary;
   }
}
