package org.apache.zookeeper;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.jute.Record;
import org.apache.zookeeper.common.PathUtils;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.proto.CheckVersionRequest;
import org.apache.zookeeper.proto.CreateRequest;
import org.apache.zookeeper.proto.CreateTTLRequest;
import org.apache.zookeeper.proto.DeleteRequest;
import org.apache.zookeeper.proto.GetChildrenRequest;
import org.apache.zookeeper.proto.GetDataRequest;
import org.apache.zookeeper.proto.SetDataRequest;
import org.apache.zookeeper.server.EphemeralType;

public abstract class Op {
   private int type;
   private String path;
   private OpKind opKind;

   private Op(int type, String path, OpKind opKind) {
      this.type = type;
      this.path = path;
      this.opKind = opKind;
   }

   public static Op create(String path, byte[] data, List acl, int flags) {
      return new Create(path, data, acl, flags);
   }

   public static Op create(String path, byte[] data, List acl, int flags, long ttl) {
      CreateMode createMode = CreateMode.fromFlag(flags, CreateMode.PERSISTENT);
      return (Op)(createMode.isTTL() ? new CreateTTL(path, data, acl, createMode, ttl) : new Create(path, data, acl, flags));
   }

   public static Op create(String path, byte[] data, List acl, CreateMode createMode) {
      return new Create(path, data, acl, createMode);
   }

   public static Op create(String path, byte[] data, List acl, CreateMode createMode, long ttl) {
      return (Op)(createMode.isTTL() ? new CreateTTL(path, data, acl, createMode, ttl) : new Create(path, data, acl, createMode));
   }

   static Op create(String path, byte[] data, CreateOptions options, int defaultOpCode) {
      return (Op)(options.getCreateMode().isTTL() ? new CreateTTL(path, data, options.getAcl(), options.getCreateMode(), options.getTtl()) : new Create(path, data, options.getAcl(), options.getCreateMode(), defaultOpCode));
   }

   public static Op create(String path, byte[] data, CreateOptions options) {
      return create(path, data, (CreateOptions)options, 15);
   }

   public static Op delete(String path, int version) {
      return new Delete(path, version);
   }

   public static Op setData(String path, byte[] data, int version) {
      return new SetData(path, data, version);
   }

   public static Op check(String path, int version) {
      return new Check(path, version);
   }

   public static Op getChildren(String path) {
      return new GetChildren(path);
   }

   public static Op getData(String path) {
      return new GetData(path);
   }

   public int getType() {
      return this.type;
   }

   public String getPath() {
      return this.path;
   }

   public OpKind getKind() {
      return this.opKind;
   }

   public abstract Record toRequestRecord();

   abstract Op withChroot(String var1);

   void validate() throws KeeperException {
      PathUtils.validatePath(this.path);
   }

   public static enum OpKind {
      TRANSACTION,
      READ;
   }

   public static class Create extends Op {
      protected byte[] data;
      protected List acl;
      protected int flags;

      private Create(String path, byte[] data, List acl, int flags) {
         this(path, data, acl, flags, 1);
      }

      private Create(String path, byte[] data, List acl, int flags, int defaultOpCode) {
         super(getOpcode(CreateMode.fromFlag(flags, CreateMode.PERSISTENT), defaultOpCode), path, Op.OpKind.TRANSACTION, null);
         this.data = data;
         this.acl = acl;
         this.flags = flags;
      }

      private static int getOpcode(CreateMode createMode, int defaultOpCode) {
         if (createMode.isTTL()) {
            return 21;
         } else {
            return createMode.isContainer() ? 19 : defaultOpCode;
         }
      }

      private Create(String path, byte[] data, List acl, CreateMode createMode) {
         this(path, data, acl, createMode, 1);
      }

      private Create(String path, byte[] data, List acl, CreateMode createMode, int defaultOpCode) {
         super(getOpcode(createMode, defaultOpCode), path, Op.OpKind.TRANSACTION, null);
         this.data = data;
         this.acl = acl;
         this.flags = createMode.toFlag();
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof Create)) {
            return false;
         } else {
            Create op = (Create)o;
            boolean aclEquals = true;
            Iterator<ACL> i = op.acl.iterator();

            for(ACL acl : op.acl) {
               boolean hasMoreData = i.hasNext();
               if (!hasMoreData) {
                  aclEquals = false;
                  break;
               }

               ACL otherAcl = (ACL)i.next();
               if (!acl.equals(otherAcl)) {
                  aclEquals = false;
                  break;
               }
            }

            return !i.hasNext() && this.getType() == op.getType() && Arrays.equals(this.data, op.data) && this.flags == op.flags && aclEquals;
         }
      }

      public int hashCode() {
         return this.getType() + this.getPath().hashCode() + Arrays.hashCode(this.data);
      }

      public Record toRequestRecord() {
         return new CreateRequest(this.getPath(), this.data, this.acl, this.flags);
      }

      Op withChroot(String path) {
         return new Create(path, this.data, this.acl, this.flags);
      }

      void validate() throws KeeperException {
         CreateMode createMode = CreateMode.fromFlag(this.flags);
         PathUtils.validatePath(this.getPath(), createMode.isSequential());
         EphemeralType.validateTTL(createMode, -1L);
      }
   }

   public static class CreateTTL extends Create {
      private final long ttl;

      private CreateTTL(String path, byte[] data, List acl, int flags, long ttl) {
         super(path, data, acl, flags, null);
         this.ttl = ttl;
      }

      private CreateTTL(String path, byte[] data, List acl, CreateMode createMode, long ttl) {
         super(path, data, acl, createMode, null);
         this.ttl = ttl;
      }

      public boolean equals(Object o) {
         return super.equals(o) && o instanceof CreateTTL && this.ttl == ((CreateTTL)o).ttl;
      }

      public int hashCode() {
         return super.hashCode() + (int)(this.ttl ^ this.ttl >>> 32);
      }

      public Record toRequestRecord() {
         return new CreateTTLRequest(this.getPath(), this.data, this.acl, this.flags, this.ttl);
      }

      Op withChroot(String path) {
         return new CreateTTL(path, this.data, this.acl, this.flags, this.ttl);
      }

      void validate() throws KeeperException {
         CreateMode createMode = CreateMode.fromFlag(this.flags);
         PathUtils.validatePath(this.getPath(), createMode.isSequential());
         EphemeralType.validateTTL(createMode, this.ttl);
      }
   }

   public static class Delete extends Op {
      private int version;

      private Delete(String path, int version) {
         super(2, path, Op.OpKind.TRANSACTION, null);
         this.version = version;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof Delete)) {
            return false;
         } else {
            Delete op = (Delete)o;
            return this.getType() == op.getType() && this.version == op.version && this.getPath().equals(op.getPath());
         }
      }

      public int hashCode() {
         return this.getType() + this.getPath().hashCode() + this.version;
      }

      public Record toRequestRecord() {
         return new DeleteRequest(this.getPath(), this.version);
      }

      Op withChroot(String path) {
         return new Delete(path, this.version);
      }
   }

   public static class SetData extends Op {
      private byte[] data;
      private int version;

      private SetData(String path, byte[] data, int version) {
         super(5, path, Op.OpKind.TRANSACTION, null);
         this.data = data;
         this.version = version;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof SetData)) {
            return false;
         } else {
            SetData op = (SetData)o;
            return this.getType() == op.getType() && this.version == op.version && this.getPath().equals(op.getPath()) && Arrays.equals(this.data, op.data);
         }
      }

      public int hashCode() {
         return this.getType() + this.getPath().hashCode() + Arrays.hashCode(this.data) + this.version;
      }

      public Record toRequestRecord() {
         return new SetDataRequest(this.getPath(), this.data, this.version);
      }

      Op withChroot(String path) {
         return new SetData(path, this.data, this.version);
      }
   }

   public static class Check extends Op {
      private int version;

      private Check(String path, int version) {
         super(13, path, Op.OpKind.TRANSACTION, null);
         this.version = version;
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof Check)) {
            return false;
         } else {
            Check op = (Check)o;
            return this.getType() == op.getType() && this.getPath().equals(op.getPath()) && this.version == op.version;
         }
      }

      public int hashCode() {
         return this.getType() + this.getPath().hashCode() + this.version;
      }

      public Record toRequestRecord() {
         return new CheckVersionRequest(this.getPath(), this.version);
      }

      Op withChroot(String path) {
         return new Check(path, this.version);
      }
   }

   public static class GetChildren extends Op {
      GetChildren(String path) {
         super(8, path, Op.OpKind.READ, null);
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof GetChildren)) {
            return false;
         } else {
            GetChildren op = (GetChildren)o;
            return this.getType() == op.getType() && this.getPath().equals(op.getPath());
         }
      }

      public int hashCode() {
         return this.getType() + this.getPath().hashCode();
      }

      public Record toRequestRecord() {
         return new GetChildrenRequest(this.getPath(), false);
      }

      Op withChroot(String path) {
         return new GetChildren(path);
      }
   }

   public static class GetData extends Op {
      GetData(String path) {
         super(4, path, Op.OpKind.READ, null);
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof GetData)) {
            return false;
         } else {
            GetData op = (GetData)o;
            return this.getType() == op.getType() && this.getPath().equals(op.getPath());
         }
      }

      public int hashCode() {
         return this.getType() + this.getPath().hashCode();
      }

      public Record toRequestRecord() {
         return new GetDataRequest(this.getPath(), false);
      }

      Op withChroot(String path) {
         return new GetData(path);
      }
   }
}
