package org.sparkproject.jetty.security;

import jakarta.servlet.HttpConstraintElement;
import jakarta.servlet.HttpMethodConstraintElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.ServletSecurity.EmptyRoleSemantic;
import jakarta.servlet.annotation.ServletSecurity.TransportGuarantee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.pathmap.MappedResource;
import org.sparkproject.jetty.http.pathmap.MatchedResource;
import org.sparkproject.jetty.http.pathmap.PathMappings;
import org.sparkproject.jetty.http.pathmap.PathSpec;
import org.sparkproject.jetty.server.HttpConfiguration;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Response;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.security.Constraint;

public class ConstraintSecurityHandler extends SecurityHandler implements ConstraintAware {
   private static final Logger LOG = LoggerFactory.getLogger(SecurityHandler.class);
   private static final String OMISSION_SUFFIX = ".omission";
   private static final String ALL_METHODS = "*";
   private final List _constraintMappings = new CopyOnWriteArrayList();
   private final List _durableConstraintMappings = new CopyOnWriteArrayList();
   private final Set _roles = new CopyOnWriteArraySet();
   private final PathMappings _constraintRoles = new PathMappings();
   private boolean _denyUncoveredMethods = false;

   public static Constraint createConstraint() {
      return new Constraint();
   }

   public static Constraint createConstraint(Constraint constraint) {
      try {
         return (Constraint)constraint.clone();
      } catch (CloneNotSupportedException e) {
         throw new IllegalStateException(e);
      }
   }

   public static Constraint createConstraint(String name, boolean authenticate, String[] roles, int dataConstraint) {
      Constraint constraint = createConstraint();
      if (name != null) {
         constraint.setName(name);
      }

      constraint.setAuthenticate(authenticate);
      constraint.setRoles(roles);
      constraint.setDataConstraint(dataConstraint);
      return constraint;
   }

   public static Constraint createConstraint(String name, HttpConstraintElement element) {
      return createConstraint(name, element.getRolesAllowed(), element.getEmptyRoleSemantic(), element.getTransportGuarantee());
   }

   public static Constraint createConstraint(String name, String[] rolesAllowed, ServletSecurity.EmptyRoleSemantic permitOrDeny, ServletSecurity.TransportGuarantee transport) {
      Constraint constraint = createConstraint();
      if (rolesAllowed != null && rolesAllowed.length != 0) {
         constraint.setAuthenticate(true);
         constraint.setRoles(rolesAllowed);
         constraint.setName(name + "-RolesAllowed");
      } else if (permitOrDeny.equals(EmptyRoleSemantic.DENY)) {
         constraint.setName(name + "-Deny");
         constraint.setAuthenticate(true);
      } else {
         constraint.setName(name + "-Permit");
         constraint.setAuthenticate(false);
      }

      constraint.setDataConstraint(transport.equals(TransportGuarantee.CONFIDENTIAL) ? 2 : 0);
      return constraint;
   }

   public static List getConstraintMappingsForPath(String pathSpec, List constraintMappings) {
      if (pathSpec != null && !"".equals(pathSpec.trim()) && constraintMappings != null && constraintMappings.size() != 0) {
         List<ConstraintMapping> mappings = new ArrayList();

         for(ConstraintMapping mapping : constraintMappings) {
            if (pathSpec.equals(mapping.getPathSpec())) {
               mappings.add(mapping);
            }
         }

         return mappings;
      } else {
         return Collections.emptyList();
      }
   }

   public static List removeConstraintMappingsForPath(String pathSpec, List constraintMappings) {
      if (pathSpec != null && !"".equals(pathSpec.trim()) && constraintMappings != null && constraintMappings.size() != 0) {
         List<ConstraintMapping> mappings = new ArrayList();

         for(ConstraintMapping mapping : constraintMappings) {
            if (!pathSpec.equals(mapping.getPathSpec())) {
               mappings.add(mapping);
            }
         }

         return mappings;
      } else {
         return Collections.emptyList();
      }
   }

   public static List createConstraintsWithMappingsForPath(String name, String pathSpec, ServletSecurityElement securityElement) {
      List<ConstraintMapping> mappings = new ArrayList();
      ConstraintMapping httpConstraintMapping = null;
      if (securityElement.getEmptyRoleSemantic() != EmptyRoleSemantic.PERMIT || securityElement.getRolesAllowed().length != 0 || securityElement.getTransportGuarantee() != TransportGuarantee.NONE) {
         Constraint httpConstraint = createConstraint(name, securityElement);
         httpConstraintMapping = new ConstraintMapping();
         httpConstraintMapping.setPathSpec(pathSpec);
         httpConstraintMapping.setConstraint(httpConstraint);
         mappings.add(httpConstraintMapping);
      }

      List<String> methodOmissions = new ArrayList();
      Collection<HttpMethodConstraintElement> methodConstraintElements = securityElement.getHttpMethodConstraints();
      if (methodConstraintElements != null) {
         for(HttpMethodConstraintElement methodConstraintElement : methodConstraintElements) {
            Constraint methodConstraint = createConstraint(name, methodConstraintElement);
            ConstraintMapping mapping = new ConstraintMapping();
            mapping.setConstraint(methodConstraint);
            mapping.setPathSpec(pathSpec);
            if (methodConstraintElement.getMethodName() != null) {
               mapping.setMethod(methodConstraintElement.getMethodName());
               methodOmissions.add(methodConstraintElement.getMethodName());
            }

            mappings.add(mapping);
         }
      }

      if (methodOmissions.size() > 0 && httpConstraintMapping != null) {
         httpConstraintMapping.setMethodOmissions((String[])methodOmissions.toArray(new String[0]));
      }

      return mappings;
   }

   public List getConstraintMappings() {
      return this._constraintMappings;
   }

   public Set getRoles() {
      return this._roles;
   }

   public void setConstraintMappings(List constraintMappings) {
      this.setConstraintMappings(constraintMappings, (Set)null);
   }

   public void setConstraintMappings(ConstraintMapping[] constraintMappings) {
      this.setConstraintMappings(Arrays.asList(constraintMappings), (Set)null);
   }

   public void setConstraintMappings(List constraintMappings, Set roles) {
      this._constraintMappings.clear();
      this._constraintMappings.addAll(constraintMappings);
      this._durableConstraintMappings.clear();
      if (this.isInDurableState()) {
         this._durableConstraintMappings.addAll(constraintMappings);
      }

      if (roles == null) {
         roles = new HashSet();

         for(ConstraintMapping cm : constraintMappings) {
            String[] cmr = cm.getConstraint().getRoles();
            if (cmr != null) {
               for(String r : cmr) {
                  if (!"*".equals(r)) {
                     roles.add(r);
                  }
               }
            }
         }
      }

      this.setRoles(roles);
      if (this.isStarted()) {
         this._constraintMappings.stream().forEach((m) -> this.processConstraintMapping(m));
      }

   }

   public void setRoles(Set roles) {
      this._roles.clear();
      this._roles.addAll(roles);
   }

   public void addConstraintMapping(ConstraintMapping mapping) {
      this._constraintMappings.add(mapping);
      if (this.isInDurableState()) {
         this._durableConstraintMappings.add(mapping);
      }

      if (mapping.getConstraint() != null && mapping.getConstraint().getRoles() != null) {
         for(String role : mapping.getConstraint().getRoles()) {
            if (!"*".equals(role) && !"**".equals(role)) {
               this.addRole(role);
            }
         }
      }

      if (this.isStarted()) {
         this.processConstraintMapping(mapping);
      }

   }

   public void addRole(String role) {
      boolean modified = this._roles.add(role);
      if (this.isStarted() && modified) {
         for(MappedResource map : this._constraintRoles) {
            for(RoleInfo info : ((Map)map.getResource()).values()) {
               if (info.isAnyRole()) {
                  info.addRole(role);
               }
            }
         }
      }

   }

   protected void doStart() throws Exception {
      this._constraintRoles.reset();
      this._constraintMappings.forEach(this::processConstraintMapping);
      this.checkPathsWithUncoveredHttpMethods();
      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
      this._constraintRoles.reset();
      this._constraintMappings.clear();
      this._constraintMappings.addAll(this._durableConstraintMappings);
   }

   protected void processConstraintMapping(ConstraintMapping mapping) {
      Map<String, RoleInfo> mappings = (Map)this._constraintRoles.get(this.asPathSpec(mapping));
      if (mappings == null) {
         mappings = new HashMap();
         this._constraintRoles.put((String)mapping.getPathSpec(), mappings);
      }

      RoleInfo allMethodsRoleInfo = (RoleInfo)mappings.get("*");
      if (allMethodsRoleInfo == null || !allMethodsRoleInfo.isForbidden()) {
         if (mapping.getMethodOmissions() != null && mapping.getMethodOmissions().length > 0) {
            this.processConstraintMappingWithMethodOmissions(mapping, mappings);
         } else {
            String httpMethod = mapping.getMethod();
            if (httpMethod == null) {
               httpMethod = "*";
            }

            RoleInfo roleInfo = (RoleInfo)mappings.get(httpMethod);
            if (roleInfo == null) {
               roleInfo = new RoleInfo();
               mappings.put(httpMethod, roleInfo);
               if (allMethodsRoleInfo != null) {
                  roleInfo.combine(allMethodsRoleInfo);
               }
            }

            if (!roleInfo.isForbidden()) {
               this.configureRoleInfo(roleInfo, mapping);
               if (roleInfo.isForbidden() && httpMethod.equals("*")) {
                  mappings.clear();
                  mappings.put("*", roleInfo);
               }

            }
         }
      }
   }

   protected PathSpec asPathSpec(ConstraintMapping mapping) {
      return PathMappings.asPathSpec(mapping.getPathSpec());
   }

   protected void processConstraintMappingWithMethodOmissions(ConstraintMapping mapping, Map mappings) {
      String[] omissions = mapping.getMethodOmissions();
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < omissions.length; ++i) {
         if (i > 0) {
            sb.append(".");
         }

         sb.append(omissions[i]);
      }

      sb.append(".omission");
      RoleInfo ri = new RoleInfo();
      mappings.put(sb.toString(), ri);
      this.configureRoleInfo(ri, mapping);
   }

   protected void configureRoleInfo(RoleInfo ri, ConstraintMapping mapping) {
      Constraint constraint = mapping.getConstraint();
      boolean forbidden = constraint.isForbidden();
      ri.setForbidden(forbidden);
      UserDataConstraint userDataConstraint = UserDataConstraint.get(mapping.getConstraint().getDataConstraint());
      ri.setUserDataConstraint(userDataConstraint);
      if (!ri.isForbidden()) {
         boolean checked = mapping.getConstraint().getAuthenticate();
         ri.setChecked(checked);
         if (ri.isChecked()) {
            if (mapping.getConstraint().isAnyRole()) {
               for(String role : this._roles) {
                  ri.addRole(role);
               }

               ri.setAnyRole(true);
            } else if (mapping.getConstraint().isAnyAuth()) {
               ri.setAnyAuth(true);
            } else {
               String[] newRoles = mapping.getConstraint().getRoles();

               for(String role : newRoles) {
                  if (!this._roles.contains(role)) {
                     throw new IllegalArgumentException("Attempt to use undeclared role: " + role + ", known roles: " + String.valueOf(this._roles));
                  }

                  ri.addRole(role);
               }
            }
         }
      }

   }

   protected RoleInfo prepareConstraintInfo(String pathInContext, Request request) {
      MatchedResource<Map<String, RoleInfo>> resource = this._constraintRoles.getMatched(pathInContext);
      if (resource == null) {
         return null;
      } else {
         Map<String, RoleInfo> mappings = (Map)resource.getResource();
         if (mappings == null) {
            return null;
         } else {
            String httpMethod = request.getMethod();
            RoleInfo roleInfo = (RoleInfo)mappings.get(httpMethod);
            if (roleInfo == null) {
               List<RoleInfo> applicableConstraints = new ArrayList();
               RoleInfo all = (RoleInfo)mappings.get("*");
               if (all != null) {
                  applicableConstraints.add(all);
               }

               for(Map.Entry entry : mappings.entrySet()) {
                  if (entry.getKey() != null && ((String)entry.getKey()).endsWith(".omission") && !((String)entry.getKey()).contains(httpMethod)) {
                     applicableConstraints.add((RoleInfo)entry.getValue());
                  }
               }

               if (applicableConstraints.size() == 0 && this.isDenyUncoveredHttpMethods()) {
                  roleInfo = new RoleInfo();
                  roleInfo.setForbidden(true);
               } else if (applicableConstraints.size() == 1) {
                  roleInfo = (RoleInfo)applicableConstraints.get(0);
               } else {
                  roleInfo = new RoleInfo();
                  roleInfo.setUserDataConstraint(UserDataConstraint.None);

                  for(RoleInfo r : applicableConstraints) {
                     roleInfo.combine(r);
                  }
               }
            }

            return roleInfo;
         }
      }
   }

   protected boolean checkUserDataPermissions(String pathInContext, Request request, Response response, RoleInfo roleInfo) throws IOException {
      if (roleInfo == null) {
         return true;
      } else if (roleInfo.isForbidden()) {
         return false;
      } else {
         UserDataConstraint dataConstraint = roleInfo.getUserDataConstraint();
         if (dataConstraint != null && dataConstraint != UserDataConstraint.None) {
            Request baseRequest = Request.getBaseRequest(request);
            HttpConfiguration httpConfig = baseRequest.getHttpChannel().getHttpConfiguration();
            if (dataConstraint != UserDataConstraint.Confidential && dataConstraint != UserDataConstraint.Integral) {
               throw new IllegalArgumentException("Invalid dataConstraint value: " + String.valueOf(dataConstraint));
            } else if (request.isSecure()) {
               return true;
            } else {
               if (httpConfig.getSecurePort() > 0) {
                  String scheme = httpConfig.getSecureScheme();
                  int port = httpConfig.getSecurePort();
                  String url = URIUtil.newURI(scheme, request.getServerName(), port, request.getRequestURI(), request.getQueryString());
                  response.setContentLength(0);
                  response.sendRedirect(url, true);
               } else {
                  response.sendError(403, "!Secure");
               }

               request.setHandled(true);
               return false;
            }
         } else {
            return true;
         }
      }
   }

   protected boolean isAuthMandatory(Request baseRequest, Response baseResponse, Object constraintInfo) {
      return constraintInfo != null && ((RoleInfo)constraintInfo).isChecked();
   }

   protected boolean checkWebResourcePermissions(String pathInContext, Request request, Response response, Object constraintInfo, UserIdentity userIdentity) throws IOException {
      if (constraintInfo == null) {
         return true;
      } else {
         RoleInfo roleInfo = (RoleInfo)constraintInfo;
         if (!roleInfo.isChecked()) {
            return true;
         } else if (roleInfo.isAnyAuth() && request.getUserPrincipal() != null) {
            return true;
         } else {
            boolean isUserInRole = false;

            for(String role : roleInfo.getRoles()) {
               if (userIdentity.isUserInRole(role, (UserIdentity.Scope)null)) {
                  isUserInRole = true;
                  break;
               }
            }

            if (roleInfo.isAnyRole() && request.getUserPrincipal() != null && isUserInRole) {
               return true;
            } else {
               return isUserInRole;
            }
         }
      }
   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent, new Object[]{DumpableCollection.from("roles", (Collection)this._roles), DumpableCollection.from("constraints", (Collection)this._constraintMappings)});
   }

   public void setDenyUncoveredHttpMethods(boolean deny) {
      this._denyUncoveredMethods = deny;
   }

   public boolean isDenyUncoveredHttpMethods() {
      return this._denyUncoveredMethods;
   }

   public boolean checkPathsWithUncoveredHttpMethods() {
      Set<String> paths = this.getPathsWithUncoveredHttpMethods();
      if (paths != null && !paths.isEmpty()) {
         LOG.warn("{} has uncovered HTTP methods for the following paths: {}", ContextHandler.getCurrentContext(), paths);
         return true;
      } else {
         return false;
      }
   }

   public Set getPathsWithUncoveredHttpMethods() {
      if (this._denyUncoveredMethods) {
         return Collections.emptySet();
      } else {
         Set<String> uncoveredPaths = new HashSet();

         for(MappedResource resource : this._constraintRoles) {
            String path = resource.getPathSpec().getDeclaration();
            Map<String, RoleInfo> methodMappings = (Map)resource.getResource();
            if (methodMappings.get("*") == null) {
               boolean hasOmissions = this.omissionsExist(path, methodMappings);

               for(String method : methodMappings.keySet()) {
                  if (method.endsWith(".omission")) {
                     for(String m : this.getOmittedMethods(method)) {
                        if (!methodMappings.containsKey(m)) {
                           uncoveredPaths.add(path);
                        }
                     }
                  } else if (!hasOmissions) {
                     uncoveredPaths.add(path);
                  }
               }
            }
         }

         return uncoveredPaths;
      }
   }

   protected boolean omissionsExist(String path, Map methodMappings) {
      if (methodMappings == null) {
         return false;
      } else {
         boolean hasOmissions = false;

         for(String m : methodMappings.keySet()) {
            if (m.endsWith(".omission")) {
               hasOmissions = true;
            }
         }

         return hasOmissions;
      }
   }

   protected Set getOmittedMethods(String omission) {
      if (omission != null && omission.endsWith(".omission")) {
         String[] strings = omission.split("\\.");
         Set<String> methods = new HashSet();

         for(int i = 0; i < strings.length - 1; ++i) {
            methods.add(strings[i]);
         }

         return methods;
      } else {
         return Collections.emptySet();
      }
   }

   private boolean isInDurableState() {
      ContextHandler context = ContextHandler.getContextHandler((ServletContext)null);
      Server server = this.getServer();
      return context == null && server == null || context != null && !context.isRunning() || context == null && server != null && !server.isRunning();
   }
}
