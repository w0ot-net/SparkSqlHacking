package org.stringtemplate.v4.misc;

import java.util.ArrayList;
import java.util.List;
import org.stringtemplate.v4.STErrorListener;

public class ErrorBuffer implements STErrorListener {
   public List errors = new ArrayList();

   public void compileTimeError(STMessage msg) {
      this.errors.add(msg);
   }

   public void runTimeError(STMessage msg) {
      if (msg.error != ErrorType.NO_SUCH_PROPERTY) {
         this.errors.add(msg);
      }

   }

   public void IOError(STMessage msg) {
      this.errors.add(msg);
   }

   public void internalError(STMessage msg) {
      this.errors.add(msg);
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();

      for(STMessage m : this.errors) {
         buf.append(m.toString() + Misc.newline);
      }

      return buf.toString();
   }
}
