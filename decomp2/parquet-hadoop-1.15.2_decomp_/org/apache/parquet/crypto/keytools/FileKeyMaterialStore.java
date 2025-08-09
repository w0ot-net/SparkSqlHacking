package org.apache.parquet.crypto.keytools;

import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public interface FileKeyMaterialStore {
   void initialize(Path var1, Configuration var2, boolean var3);

   void addKeyMaterial(String var1, String var2);

   void saveMaterial();

   String getKeyMaterial(String var1);

   Set getKeyIDSet();

   void removeMaterial();

   void moveMaterialTo(FileKeyMaterialStore var1);
}
