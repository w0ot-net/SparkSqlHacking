package org.datanucleus.validation;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import org.datanucleus.ClassLoaderResolver;
import org.datanucleus.Configuration;
import org.datanucleus.ExecutionContext;
import org.datanucleus.state.CallbackHandler;
import org.datanucleus.util.StringUtils;

public class BeanValidatorHandler implements CallbackHandler {
   Validator validator;
   ClassLoaderResolver clr;
   Configuration conf;

   public BeanValidatorHandler(ExecutionContext ec, ValidatorFactory factory) {
      this.conf = ec.getNucleusContext().getConfiguration();
      this.clr = ec.getClassLoaderResolver();
      this.validator = factory.usingContext().traversableResolver(new PersistenceTraversalResolver(ec)).getValidator();
   }

   public void validate(Object pc, String callbackName, Class[] groups) {
      if (this.validator != null) {
         Set<ConstraintViolation<Object>> violations = this.validator.validate(pc, groups);
         if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for " + StringUtils.toJVMIDString(pc) + " during " + callbackName + " for groups " + StringUtils.objectArrayToString(groups) + " - exceptions are attached", violations);
         }
      }
   }

   public void preDelete(Object pc) {
      Class<?>[] groups = this.getGroups(this.conf.getStringProperty("datanucleus.validation.group.pre-remove"), "pre-remove");
      if (groups != null) {
         this.validate(pc, "pre-remove", groups);
      }

   }

   public void preStore(Object pc) {
      Class<?>[] groups = this.getGroups(this.conf.getStringProperty("datanucleus.validation.group.pre-update"), "pre-update");
      if (groups != null) {
         this.validate(pc, "pre-update", groups);
      }

   }

   public void prePersist(Object pc) {
      Class<?>[] groups = this.getGroups(this.conf.getStringProperty("datanucleus.validation.group.pre-persist"), "pre-persist");
      if (groups != null) {
         this.validate(pc, "pre-persist", groups);
      }

   }

   public void close() {
   }

   public void setValidationListener(CallbackHandler handler) {
   }

   public void addListener(Object listener, Class[] classes) {
   }

   public void removeListener(Object listener) {
   }

   public void postAttach(Object pc, Object detachedPC) {
   }

   public void postClear(Object pc) {
   }

   public void postCreate(Object pc) {
   }

   public void postDelete(Object pc) {
   }

   public void postDetach(Object pc, Object detachedPC) {
   }

   public void postDirty(Object pc) {
   }

   public void postLoad(Object pc) {
   }

   public void postRefresh(Object pc) {
   }

   public void postStore(Object pc) {
   }

   public void preAttach(Object detachedPC) {
   }

   public void preClear(Object pc) {
   }

   public void preDetach(Object pc) {
   }

   public void preDirty(Object pc) {
   }

   private Class[] getGroups(String property, String eventName) {
      if (property != null && property.trim().length() != 0) {
         String[] classNames = property.trim().split(",");
         Class<?>[] groups = new Class[classNames.length];

         for(int i = 0; i < classNames.length; ++i) {
            groups[i] = this.clr.classForName(classNames[i].trim());
         }

         return groups;
      } else {
         return !eventName.equals("pre-persist") && !eventName.equals("pre-update") ? null : new Class[]{Default.class};
      }
   }
}
