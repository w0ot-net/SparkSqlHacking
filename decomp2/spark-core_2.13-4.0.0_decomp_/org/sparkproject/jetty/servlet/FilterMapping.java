package org.sparkproject.jetty.servlet;

import jakarta.servlet.DispatcherType;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import java.util.stream.Collectors;
import org.sparkproject.jetty.http.pathmap.ServletPathSpec;
import org.sparkproject.jetty.util.TypeUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Dumpable;

@ManagedObject("Filter Mappings")
public class FilterMapping implements Dumpable {
   public static final int DEFAULT = 0;
   public static final int REQUEST = 1;
   public static final int FORWARD = 2;
   public static final int INCLUDE = 4;
   public static final int ERROR = 8;
   public static final int ASYNC = 16;
   public static final int ALL = 31;
   private int _dispatches = 0;
   private String _filterName;
   private FilterHolder _holder;
   private String[] _pathSpecs;
   private String[] _servletNames;

   public static DispatcherType dispatch(String type) {
      if ("request".equalsIgnoreCase(type)) {
         return DispatcherType.REQUEST;
      } else if ("forward".equalsIgnoreCase(type)) {
         return DispatcherType.FORWARD;
      } else if ("include".equalsIgnoreCase(type)) {
         return DispatcherType.INCLUDE;
      } else if ("error".equalsIgnoreCase(type)) {
         return DispatcherType.ERROR;
      } else if ("async".equalsIgnoreCase(type)) {
         return DispatcherType.ASYNC;
      } else {
         throw new IllegalArgumentException(type);
      }
   }

   public static int dispatch(DispatcherType type) {
      switch (type) {
         case REQUEST:
            return 1;
         case ASYNC:
            return 16;
         case FORWARD:
            return 2;
         case INCLUDE:
            return 4;
         case ERROR:
            return 8;
         default:
            throw new IllegalStateException(type.toString());
      }
   }

   public static DispatcherType dispatch(int type) {
      switch (type) {
         case 1:
            return DispatcherType.REQUEST;
         case 2:
            return DispatcherType.FORWARD;
         case 4:
            return DispatcherType.INCLUDE;
         case 8:
            return DispatcherType.ERROR;
         case 16:
            return DispatcherType.ASYNC;
         default:
            throw new IllegalArgumentException(Integer.toString(type));
      }
   }

   boolean appliesTo(String path, int type) {
      if (this.appliesTo(type)) {
         for(int i = 0; i < this._pathSpecs.length; ++i) {
            if (this._pathSpecs[i] != null && ServletPathSpec.match(this._pathSpecs[i], path, true)) {
               return true;
            }
         }
      }

      return false;
   }

   boolean appliesTo(int type) {
      FilterHolder holder = this._holder;
      if (this._holder == null) {
         return false;
      } else if (this._dispatches != 0) {
         return (this._dispatches & type) != 0;
      } else {
         return type == 1 || type == 16 && this._holder != null && this._holder.isAsyncSupported();
      }
   }

   public boolean appliesTo(DispatcherType t) {
      return this.appliesTo(dispatch(t));
   }

   public boolean isDefaultDispatches() {
      return this._dispatches == 0;
   }

   @ManagedAttribute(
      value = "filter name",
      readonly = true
   )
   public String getFilterName() {
      return this._filterName;
   }

   FilterHolder getFilterHolder() {
      return this._holder;
   }

   @ManagedAttribute(
      value = "url patterns",
      readonly = true
   )
   public String[] getPathSpecs() {
      return this._pathSpecs;
   }

   public void setDispatcherTypes(EnumSet dispatcherTypes) {
      this._dispatches = 0;
      if (dispatcherTypes != null) {
         if (dispatcherTypes.contains(DispatcherType.ERROR)) {
            this._dispatches |= 8;
         }

         if (dispatcherTypes.contains(DispatcherType.FORWARD)) {
            this._dispatches |= 2;
         }

         if (dispatcherTypes.contains(DispatcherType.INCLUDE)) {
            this._dispatches |= 4;
         }

         if (dispatcherTypes.contains(DispatcherType.REQUEST)) {
            this._dispatches |= 1;
         }

         if (dispatcherTypes.contains(DispatcherType.ASYNC)) {
            this._dispatches |= 16;
         }
      }

   }

   public EnumSet getDispatcherTypes() {
      EnumSet<DispatcherType> dispatcherTypes = EnumSet.noneOf(DispatcherType.class);
      if ((this._dispatches & 8) == 8) {
         dispatcherTypes.add(DispatcherType.ERROR);
      }

      if ((this._dispatches & 2) == 2) {
         dispatcherTypes.add(DispatcherType.FORWARD);
      }

      if ((this._dispatches & 4) == 4) {
         dispatcherTypes.add(DispatcherType.INCLUDE);
      }

      if ((this._dispatches & 1) == 1) {
         dispatcherTypes.add(DispatcherType.REQUEST);
      }

      if ((this._dispatches & 16) == 16) {
         dispatcherTypes.add(DispatcherType.ASYNC);
      }

      return dispatcherTypes;
   }

   public void setDispatches(int dispatches) {
      this._dispatches = dispatches;
   }

   public void setFilterName(String filterName) {
      this._filterName = (String)Objects.requireNonNull(filterName);
   }

   void setFilterHolder(FilterHolder holder) {
      this._holder = (FilterHolder)Objects.requireNonNull(holder);
      this.setFilterName(holder.getName());
   }

   public void setPathSpecs(String[] pathSpecs) {
      this._pathSpecs = pathSpecs;
   }

   public void setPathSpec(String pathSpec) {
      this._pathSpecs = new String[]{pathSpec};
   }

   @ManagedAttribute(
      value = "servlet names",
      readonly = true
   )
   public String[] getServletNames() {
      return this._servletNames;
   }

   public void setServletNames(String[] servletNames) {
      this._servletNames = servletNames;
   }

   public void setServletName(String servletName) {
      this._servletNames = new String[]{servletName};
   }

   public String toString() {
      String var10000 = String.valueOf(TypeUtil.asList(this._pathSpecs));
      return var10000 + "/" + String.valueOf(TypeUtil.asList(this._servletNames)) + "/" + String.valueOf(Arrays.stream(DispatcherType.values()).filter(this::appliesTo).collect(Collectors.toSet())) + "=>" + this._filterName;
   }

   public void dump(Appendable out, String indent) throws IOException {
      out.append(String.valueOf(this)).append("\n");
   }

   public String dump() {
      return Dumpable.dump(this);
   }
}
