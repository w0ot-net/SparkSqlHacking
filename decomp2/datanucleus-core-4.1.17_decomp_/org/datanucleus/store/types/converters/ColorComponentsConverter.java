package org.datanucleus.store.types.converters;

import java.awt.Color;

public class ColorComponentsConverter implements TypeConverter, MultiColumnConverter {
   private static final long serialVersionUID = -9008045063095458023L;

   public int[] toDatastoreType(Color memberValue) {
      return memberValue == null ? null : new int[]{memberValue.getRed(), memberValue.getGreen(), memberValue.getBlue(), memberValue.getAlpha()};
   }

   public Color toMemberType(int[] datastoreValue) {
      return datastoreValue == null ? null : new Color(datastoreValue[0], datastoreValue[1], datastoreValue[2], datastoreValue[3]);
   }

   public Class[] getDatastoreColumnTypes() {
      return new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE};
   }
}
