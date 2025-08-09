package org.apache.hadoop.hive.metastore.client.builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.metastore.api.Function;
import org.apache.hadoop.hive.metastore.api.FunctionType;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.PrincipalType;
import org.apache.hadoop.hive.metastore.api.ResourceUri;
import org.apache.hadoop.hive.metastore.utils.SecurityUtils;

public class FunctionBuilder {
   private String dbName = "default";
   private String funcName = null;
   private String className = null;
   private String owner = null;
   private PrincipalType ownerType;
   private int createTime;
   private FunctionType funcType;
   private List resourceUris;

   public FunctionBuilder() {
      this.ownerType = PrincipalType.USER;
      this.createTime = (int)(System.currentTimeMillis() / 1000L);
      this.funcType = FunctionType.JAVA;
      this.resourceUris = new ArrayList();
   }

   public FunctionBuilder setDbName(String dbName) {
      this.dbName = dbName;
      return this;
   }

   public FunctionBuilder setDbName(Database db) {
      this.dbName = db.getName();
      return this;
   }

   public FunctionBuilder setName(String funcName) {
      this.funcName = funcName;
      return this;
   }

   public FunctionBuilder setClass(String className) {
      this.className = className;
      return this;
   }

   public FunctionBuilder setOwner(String owner) {
      this.owner = owner;
      return this;
   }

   public FunctionBuilder setOwnerType(PrincipalType ownerType) {
      this.ownerType = ownerType;
      return this;
   }

   public FunctionBuilder setCreateTime(int createTime) {
      this.createTime = createTime;
      return this;
   }

   public FunctionBuilder setFunctionType(FunctionType funcType) {
      this.funcType = funcType;
      return this;
   }

   public FunctionBuilder setResourceUris(List resourceUris) {
      this.resourceUris = resourceUris;
      return this;
   }

   public FunctionBuilder addResourceUri(ResourceUri resourceUri) {
      this.resourceUris.add(resourceUri);
      return this;
   }

   public Function build() throws MetaException {
      try {
         if (this.owner != null) {
            this.owner = SecurityUtils.getUser();
         }
      } catch (IOException e) {
         throw new MetaException(e.getMessage());
      }

      return new Function(this.funcName, this.dbName, this.className, this.owner, this.ownerType, this.createTime, this.funcType, this.resourceUris);
   }
}
