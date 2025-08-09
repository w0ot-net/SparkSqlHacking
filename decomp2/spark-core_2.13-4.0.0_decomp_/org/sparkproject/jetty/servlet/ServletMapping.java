package org.sparkproject.jetty.servlet;

import java.io.IOException;
import java.util.Arrays;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;

@ManagedObject("Servlet Mapping")
public class ServletMapping {
   private String[] _pathSpecs;
   private String _servletName;
   private boolean _default;
   private Source _source;

   public ServletMapping() {
      this(Source.EMBEDDED);
   }

   public ServletMapping(Source source) {
      this._source = source;
   }

   @ManagedAttribute(
      value = "url patterns",
      readonly = true
   )
   public String[] getPathSpecs() {
      return this._pathSpecs;
   }

   @ManagedAttribute(
      value = "servlet name",
      readonly = true
   )
   public String getServletName() {
      return this._servletName;
   }

   public void setPathSpecs(String[] pathSpecs) {
      this._pathSpecs = pathSpecs;
   }

   public boolean containsPathSpec(String pathSpec) {
      if (this._pathSpecs != null && this._pathSpecs.length != 0) {
         for(String p : this._pathSpecs) {
            if (p.equals(pathSpec)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void setPathSpec(String pathSpec) {
      this._pathSpecs = new String[]{pathSpec};
   }

   public void setServletName(String servletName) {
      this._servletName = servletName;
   }

   @ManagedAttribute(
      value = "default",
      readonly = true
   )
   public boolean isFromDefaultDescriptor() {
      return this._default;
   }

   public void setFromDefaultDescriptor(boolean fromDefault) {
      this._default = fromDefault;
   }

   public Source getSource() {
      return this._source;
   }

   public String toString() {
      String var10000 = this._pathSpecs == null ? "[]" : Arrays.asList(this._pathSpecs).toString();
      return var10000 + "=>" + this._servletName;
   }

   public void dump(Appendable out, String indent) throws IOException {
      out.append(String.valueOf(this)).append("\n");
   }
}
