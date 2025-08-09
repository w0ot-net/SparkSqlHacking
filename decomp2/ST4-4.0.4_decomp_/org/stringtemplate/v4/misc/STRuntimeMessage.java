package org.stringtemplate.v4.misc;

import org.stringtemplate.v4.InstanceScope;
import org.stringtemplate.v4.Interpreter;
import org.stringtemplate.v4.ST;

public class STRuntimeMessage extends STMessage {
   Interpreter interp;
   public int ip;
   public InstanceScope scope;

   public STRuntimeMessage(Interpreter interp, ErrorType error, int ip) {
      this(interp, error, ip, (ST)null);
   }

   public STRuntimeMessage(Interpreter interp, ErrorType error, int ip, ST self) {
      this(interp, error, ip, self, (Object)null);
   }

   public STRuntimeMessage(Interpreter interp, ErrorType error, int ip, ST self, Object arg) {
      this(interp, error, ip, self, (Throwable)null, arg, (Object)null);
   }

   public STRuntimeMessage(Interpreter interp, ErrorType error, int ip, ST self, Throwable e, Object arg) {
      this(interp, error, ip, self, e, arg, (Object)null);
   }

   public STRuntimeMessage(Interpreter interp, ErrorType error, int ip, ST self, Throwable e, Object arg, Object arg2) {
      this(interp, error, ip, self, e, arg, arg2, (Object)null);
   }

   public STRuntimeMessage(Interpreter interp, ErrorType error, int ip, ST self, Throwable e, Object arg, Object arg2, Object arg3) {
      super(error, self, e, arg, arg2, arg3);
      this.ip = -1;
      this.interp = interp;
      this.ip = ip;
      if (interp != null) {
         this.scope = interp.currentScope;
      }

   }

   public String getSourceLocation() {
      if (this.ip >= 0 && this.self.impl != null) {
         Interval I = this.self.impl.sourceMap[this.ip];
         if (I == null) {
            return null;
         } else {
            int i = I.a;
            Coordinate loc = Misc.getLineCharPosition(this.self.impl.template, i);
            return loc.toString();
         }
      } else {
         return null;
      }
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      String loc = this.getSourceLocation();
      if (this.self != null) {
         buf.append("context [");
         if (this.interp != null) {
            buf.append(Interpreter.getEnclosingInstanceStackString(this.scope));
         }

         buf.append("]");
      }

      if (loc != null) {
         buf.append(" " + loc);
      }

      buf.append(" " + super.toString());
      return buf.toString();
   }
}
