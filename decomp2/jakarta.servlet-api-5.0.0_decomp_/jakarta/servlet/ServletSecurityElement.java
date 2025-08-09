package jakarta.servlet;

import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class ServletSecurityElement extends HttpConstraintElement {
   private Collection methodNames;
   private Collection methodConstraints;

   public ServletSecurityElement() {
      this.methodConstraints = new HashSet();
      this.methodNames = Collections.emptySet();
   }

   public ServletSecurityElement(HttpConstraintElement constraint) {
      super(constraint.getEmptyRoleSemantic(), constraint.getTransportGuarantee(), constraint.getRolesAllowed());
      this.methodConstraints = new HashSet();
      this.methodNames = Collections.emptySet();
   }

   public ServletSecurityElement(Collection methodConstraints) {
      this.methodConstraints = (Collection)(methodConstraints == null ? new HashSet() : methodConstraints);
      this.methodNames = this.checkMethodNames(this.methodConstraints);
   }

   public ServletSecurityElement(HttpConstraintElement constraint, Collection methodConstraints) {
      super(constraint.getEmptyRoleSemantic(), constraint.getTransportGuarantee(), constraint.getRolesAllowed());
      this.methodConstraints = (Collection)(methodConstraints == null ? new HashSet() : methodConstraints);
      this.methodNames = this.checkMethodNames(this.methodConstraints);
   }

   public ServletSecurityElement(ServletSecurity annotation) {
      super(annotation.value().value(), annotation.value().transportGuarantee(), annotation.value().rolesAllowed());
      this.methodConstraints = new HashSet();

      for(HttpMethodConstraint constraint : annotation.httpMethodConstraints()) {
         this.methodConstraints.add(new HttpMethodConstraintElement(constraint.value(), new HttpConstraintElement(constraint.emptyRoleSemantic(), constraint.transportGuarantee(), constraint.rolesAllowed())));
      }

      this.methodNames = this.checkMethodNames(this.methodConstraints);
   }

   public Collection getHttpMethodConstraints() {
      return Collections.unmodifiableCollection(this.methodConstraints);
   }

   public Collection getMethodNames() {
      return Collections.unmodifiableCollection(this.methodNames);
   }

   private Collection checkMethodNames(Collection methodConstraints) {
      Collection<String> methodNames = new HashSet();

      for(HttpMethodConstraintElement methodConstraint : methodConstraints) {
         String methodName = methodConstraint.getMethodName();
         if (!methodNames.add(methodName)) {
            throw new IllegalArgumentException("Duplicate HTTP method name: " + methodName);
         }
      }

      return methodNames;
   }
}
