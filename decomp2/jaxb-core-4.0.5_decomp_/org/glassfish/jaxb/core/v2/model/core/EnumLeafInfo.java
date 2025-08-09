package org.glassfish.jaxb.core.v2.model.core;

public interface EnumLeafInfo extends LeafInfo {
   Object getClazz();

   NonElement getBaseType();

   Iterable getConstants();
}
