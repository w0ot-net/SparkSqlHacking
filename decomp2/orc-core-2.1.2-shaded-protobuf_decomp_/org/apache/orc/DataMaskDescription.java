package org.apache.orc;

public interface DataMaskDescription {
   String getName();

   String[] getParameters();

   TypeDescription[] getColumns();
}
