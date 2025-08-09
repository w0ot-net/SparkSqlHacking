package org.sparkproject.jetty.plus.jndi;

import javax.naming.InitialContext;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.component.Dumpable;

public class NamingDump implements Dumpable {
   private final ClassLoader _loader;
   private final String _name;

   public NamingDump() {
      this((ClassLoader)null, "");
   }

   public NamingDump(ClassLoader loader, String name) {
      this._loader = loader;
      this._name = name;
   }

   public void dump(Appendable out, String indent) {
      ClassLoader loader = Thread.currentThread().getContextClassLoader();

      try {
         if (!StringUtil.isBlank(this._name)) {
            out.append(this._name).append(" ");
         }

         if (this._loader != null) {
            Thread.currentThread().setContextClassLoader(this._loader);
         }

         Object context = (new InitialContext()).lookup(this._name);
         if (context instanceof Dumpable) {
            ((Dumpable)context).dump(out, indent);
         } else {
            Dumpable.dumpObjects(out, indent, context);
         }
      } catch (Throwable th) {
         throw new RuntimeException(th);
      } finally {
         if (this._loader != null) {
            Thread.currentThread().setContextClassLoader(loader);
         }

      }

   }
}
