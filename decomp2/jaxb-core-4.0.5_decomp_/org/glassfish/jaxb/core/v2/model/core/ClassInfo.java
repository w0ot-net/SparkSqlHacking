package org.glassfish.jaxb.core.v2.model.core;

import java.util.List;

public interface ClassInfo extends MaybeElement {
   ClassInfo getBaseClass();

   Object getClazz();

   String getName();

   List getProperties();

   boolean hasValueProperty();

   PropertyInfo getProperty(String var1);

   boolean hasProperties();

   boolean isAbstract();

   boolean isOrdered();

   boolean isFinal();

   boolean hasSubClasses();

   boolean hasAttributeWildcard();

   boolean inheritsAttributeWildcard();

   boolean declaresAttributeWildcard();
}
