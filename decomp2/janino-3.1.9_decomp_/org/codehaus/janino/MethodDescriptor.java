package org.codehaus.janino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.codehaus.commons.compiler.InternalCompilerException;
import org.codehaus.commons.nullanalysis.Nullable;

public class MethodDescriptor {
   public final String[] parameterFds;
   public final String returnFd;

   public MethodDescriptor(String returnFd, String... parameterFds) {
      this.parameterFds = parameterFds;
      this.returnFd = returnFd;
   }

   public MethodDescriptor(String s) {
      if (s.charAt(0) != '(') {
         throw new InternalCompilerException();
      } else {
         int from = 1;

         List<String> parameterFDs;
         int to;
         for(parameterFDs = new ArrayList(); s.charAt(from) != ')'; from = to) {
            for(to = from; s.charAt(to) == '['; ++to) {
            }

            if ("BCDFIJSZ".indexOf(s.charAt(to)) != -1) {
               ++to;
            } else {
               if (s.charAt(to) != 'L') {
                  throw new InternalCompilerException();
               }

               ++to;

               while(s.charAt(to) != ';') {
                  ++to;
               }

               ++to;
            }

            parameterFDs.add(s.substring(from, to));
         }

         this.parameterFds = (String[])parameterFDs.toArray(new String[parameterFDs.size()]);
         ++from;
         this.returnFd = s.substring(from);
      }
   }

   public boolean equals(@Nullable Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof MethodDescriptor)) {
         return false;
      } else {
         MethodDescriptor that = (MethodDescriptor)obj;
         return Arrays.equals(this.parameterFds, that.parameterFds) && this.returnFd.equals(that.returnFd);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(this.parameterFds) ^ this.returnFd.hashCode();
   }

   public String toString() {
      StringBuilder sb = new StringBuilder("(");

      for(String parameterFd : this.parameterFds) {
         sb.append(parameterFd);
      }

      return sb.append(')').append(this.returnFd).toString();
   }

   public MethodDescriptor prependParameter(String parameterFd) {
      String[] tmp = new String[1 + this.parameterFds.length];
      tmp[0] = parameterFd;
      System.arraycopy(this.parameterFds, 0, tmp, 1, this.parameterFds.length);
      return new MethodDescriptor(this.returnFd, tmp);
   }
}
