package org.glassfish.jaxb.runtime.v2.model.impl;

import jakarta.xml.bind.annotation.XmlElementDecl;
import java.util.LinkedHashSet;
import java.util.Set;
import org.glassfish.jaxb.core.v2.model.annotation.Locatable;
import org.glassfish.jaxb.core.v2.model.core.RegistryInfo;
import org.glassfish.jaxb.core.v2.model.nav.Navigator;
import org.glassfish.jaxb.core.v2.runtime.IllegalAnnotationException;
import org.glassfish.jaxb.core.v2.runtime.Location;
import org.glassfish.jaxb.runtime.v2.model.annotation.MethodLocatable;

final class RegistryInfoImpl implements Locatable, RegistryInfo {
   final Object registryClass;
   private final Locatable upstream;
   private final Navigator nav;
   private final Set references = new LinkedHashSet();

   RegistryInfoImpl(ModelBuilder builder, Locatable upstream, Object registryClass) {
      this.nav = builder.nav;
      this.registryClass = registryClass;
      this.upstream = upstream;
      builder.registries.put(this.getPackageName(), this);
      if (this.nav.getDeclaredField(registryClass, "_useJAXBProperties") != null) {
         builder.reportError(new IllegalAnnotationException(Messages.MISSING_JAXB_PROPERTIES.format(this.getPackageName()), this));
      } else {
         for(Object m : this.nav.getDeclaredMethods(registryClass)) {
            XmlElementDecl em = (XmlElementDecl)builder.reader.getMethodAnnotation(XmlElementDecl.class, m, this);
            if (em == null) {
               if (this.nav.getMethodName(m).startsWith("create")) {
                  this.references.add(builder.getTypeInfo(this.nav.getReturnType(m), new MethodLocatable(this, m, this.nav)));
               }
            } else {
               ElementInfoImpl<T, C, F, M> ei;
               try {
                  ei = (ElementInfoImpl)builder.createElementInfo(this, m);
               } catch (IllegalAnnotationException e) {
                  builder.reportError(e);
                  continue;
               }

               builder.typeInfoSet.add(ei, builder);
               this.references.add(ei);
            }
         }

      }
   }

   public Locatable getUpstream() {
      return this.upstream;
   }

   public Location getLocation() {
      return this.nav.getClassLocation(this.registryClass);
   }

   public Set getReferences() {
      return this.references;
   }

   public String getPackageName() {
      return this.nav.getPackageName(this.registryClass);
   }

   public Object getClazz() {
      return this.registryClass;
   }
}
