package org.glassfish.jaxb.core.v2.model.core;

import java.util.Collection;

public interface ElementInfo extends Element {
   ElementPropertyInfo getProperty();

   NonElement getContentType();

   Object getContentInMemoryType();

   Object getType();

   ElementInfo getSubstitutionHead();

   Collection getSubstitutionMembers();
}
