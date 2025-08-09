package org.apache.hadoop.hive.metastore.client.builder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.utils.SecurityUtils;
import org.apache.thrift.TException;

public class DatabaseBuilder {
   private String name;
   private String description;
   private String location;
   private Map params = new HashMap();
   private String ownerName;
   private PrincipalType ownerType;

   public DatabaseBuilder setName(String name) {
      this.name = name;
      return this;
   }

   public DatabaseBuilder setDescription(String description) {
      this.description = description;
      return this;
   }

   public DatabaseBuilder setLocation(String location) {
      this.location = location;
      return this;
   }

   public DatabaseBuilder setParams(Map params) {
      this.params = params;
      return this;
   }

   public DatabaseBuilder addParam(String key, String value) {
      this.params.put(key, value);
      return this;
   }

   public DatabaseBuilder setOwnerName(String ownerName) {
      this.ownerName = ownerName;
      return this;
   }

   public DatabaseBuilder setOwnerType(PrincipalType ownerType) {
      this.ownerType = ownerType;
      return this;
   }

   public Database build() throws TException {
      if (this.name == null) {
         throw new MetaException("You must name the database");
      } else {
         Database db = new Database(this.name, this.description, this.location, this.params);

         try {
            if (this.ownerName != null) {
               this.ownerName = SecurityUtils.getUser();
            }

            db.setOwnerName(this.ownerName);
            if (this.ownerType == null) {
               this.ownerType = PrincipalType.USER;
            }

            db.setOwnerType(this.ownerType);
            return db;
         } catch (IOException e) {
            throw new MetaException(e.getMessage());
         }
      }
   }
}
