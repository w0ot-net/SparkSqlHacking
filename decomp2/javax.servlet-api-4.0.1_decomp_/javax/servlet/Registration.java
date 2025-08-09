package javax.servlet;

import java.util.Map;
import java.util.Set;

public interface Registration {
   String getName();

   String getClassName();

   boolean setInitParameter(String var1, String var2);

   String getInitParameter(String var1);

   Set setInitParameters(Map var1);

   Map getInitParameters();

   public interface Dynamic extends Registration {
      void setAsyncSupported(boolean var1);
   }
}
