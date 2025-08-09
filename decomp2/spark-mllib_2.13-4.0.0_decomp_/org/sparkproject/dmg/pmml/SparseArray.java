package org.sparkproject.dmg.pmml;

import jakarta.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlTransient
public abstract class SparseArray extends PMMLObject {
   public abstract Integer getN();

   public abstract SparseArray setN(Integer var1);

   public abstract Number getDefaultValue();

   public abstract SparseArray setDefaultValue(Number var1);

   public abstract boolean hasIndices();

   public abstract List getIndices();

   public abstract SparseArray addIndices(Integer... var1);

   public abstract boolean hasEntries();

   public abstract List getEntries();

   public abstract SparseArray addEntries(Number... var1);
}
