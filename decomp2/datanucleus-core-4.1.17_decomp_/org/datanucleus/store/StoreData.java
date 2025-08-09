package org.datanucleus.store;

import java.util.HashMap;
import java.util.Map;
import org.datanucleus.metadata.AbstractMemberMetaData;
import org.datanucleus.metadata.ClassMetaData;
import org.datanucleus.metadata.MetaData;
import org.datanucleus.store.schema.table.Table;
import org.datanucleus.util.Localiser;

public class StoreData {
   public static final int FCO_TYPE = 1;
   public static final int SCO_TYPE = 2;
   protected final String name;
   protected final int type;
   protected MetaData metadata;
   protected String interfaceName;
   protected Table table;
   protected Map properties;

   public StoreData(String name, int type) {
      this(name, (MetaData)null, type, (String)null);
   }

   public StoreData(String name, MetaData metadata, int type, String interfaceName) {
      this.properties = new HashMap();
      this.name = name;
      this.type = type;
      this.metadata = metadata;
      this.interfaceName = interfaceName;
   }

   public String getName() {
      return this.name;
   }

   public MetaData getMetaData() {
      return this.metadata;
   }

   public void setMetaData(MetaData md) {
      this.metadata = md;
   }

   public boolean isFCO() {
      return this.type == 1;
   }

   public boolean isSCO() {
      return this.type == 2;
   }

   public int getType() {
      return this.type;
   }

   public String getInterfaceName() {
      return this.interfaceName;
   }

   public void setTable(Table tbl) {
      this.table = tbl;
   }

   public Table getTable() {
      return this.table;
   }

   public void addProperty(String key, Object value) {
      this.properties.put(key, value);
   }

   public Object getProperty(String key) {
      return this.properties.get(key);
   }

   public Map getProperties() {
      return this.properties;
   }

   public String toString() {
      MetaData metadata = this.getMetaData();
      if (metadata instanceof ClassMetaData) {
         ClassMetaData cmd = (ClassMetaData)metadata;
         return Localiser.msg("035004", this.name, "(none)", cmd.getInheritanceMetaData().getStrategy().toString());
      } else {
         return metadata instanceof AbstractMemberMetaData ? Localiser.msg("035003", this.name, null) : Localiser.msg("035002", this.name, null);
      }
   }
}
