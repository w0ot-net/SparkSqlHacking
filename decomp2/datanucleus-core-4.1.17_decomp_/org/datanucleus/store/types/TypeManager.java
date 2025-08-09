package org.datanucleus.store.types;

import java.util.Collection;
import java.util.Set;
import org.datanucleus.store.types.converters.TypeConverter;

public interface TypeManager {
   Set getSupportedSecondClassTypes();

   boolean isSupportedSecondClassType(String var1);

   String[] filterOutSupportedSecondClassNames(String[] var1);

   boolean isDefaultPersistent(Class var1);

   boolean isDefaultFetchGroup(Class var1);

   boolean isDefaultFetchGroupForCollection(Class var1, Class var2);

   boolean isDefaultEmbeddedType(Class var1);

   boolean isSecondClassMutableType(String var1);

   Class getWrapperTypeForType(String var1);

   Class getWrappedTypeBackedForType(String var1);

   boolean isSecondClassWrapper(String var1);

   Class getTypeForSecondClassWrapper(String var1);

   TypeConverter getTypeConverterForName(String var1);

   void registerConverter(String var1, TypeConverter var2, boolean var3, String var4);

   void registerConverter(String var1, TypeConverter var2);

   TypeConverter getAutoApplyTypeConverterForType(Class var1);

   TypeConverter getDefaultTypeConverterForType(Class var1);

   TypeConverter getTypeConverterForType(Class var1, Class var2);

   Collection getTypeConvertersForType(Class var1);
}
