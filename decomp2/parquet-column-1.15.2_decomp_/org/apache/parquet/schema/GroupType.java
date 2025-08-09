package org.apache.parquet.schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.apache.parquet.io.InvalidRecordException;

public class GroupType extends Type {
   private final List fields;
   private final Map indexByName;

   public GroupType(Type.Repetition repetition, String name, List fields) {
      this(repetition, name, (LogicalTypeAnnotation)((LogicalTypeAnnotation)null), fields, (Type.ID)null);
   }

   public GroupType(Type.Repetition repetition, String name, Type... fields) {
      this(repetition, name, Arrays.asList(fields));
   }

   /** @deprecated */
   @Deprecated
   public GroupType(Type.Repetition repetition, String name, OriginalType originalType, Type... fields) {
      this(repetition, name, originalType, Arrays.asList(fields));
   }

   GroupType(Type.Repetition repetition, String name, LogicalTypeAnnotation logicalTypeAnnotation, Type... fields) {
      this(repetition, name, logicalTypeAnnotation, Arrays.asList(fields));
   }

   /** @deprecated */
   @Deprecated
   public GroupType(Type.Repetition repetition, String name, OriginalType originalType, List fields) {
      this(repetition, name, (OriginalType)originalType, fields, (Type.ID)null);
   }

   GroupType(Type.Repetition repetition, String name, LogicalTypeAnnotation logicalTypeAnnotation, List fields) {
      this(repetition, name, (LogicalTypeAnnotation)logicalTypeAnnotation, fields, (Type.ID)null);
   }

   GroupType(Type.Repetition repetition, String name, OriginalType originalType, List fields, Type.ID id) {
      super(name, repetition, originalType, id);
      this.fields = fields;
      this.indexByName = new HashMap();

      for(int i = 0; i < fields.size(); ++i) {
         this.indexByName.put(((Type)fields.get(i)).getName(), i);
      }

   }

   GroupType(Type.Repetition repetition, String name, LogicalTypeAnnotation logicalTypeAnnotation, List fields, Type.ID id) {
      super(name, repetition, logicalTypeAnnotation, id);
      this.fields = fields;
      this.indexByName = new HashMap();

      for(int i = 0; i < fields.size(); ++i) {
         this.indexByName.put(((Type)fields.get(i)).getName(), i);
      }

   }

   public GroupType withId(int id) {
      return new GroupType(this.getRepetition(), this.getName(), this.getLogicalTypeAnnotation(), this.fields, new Type.ID(id));
   }

   public GroupType withNewFields(List newFields) {
      return new GroupType(this.getRepetition(), this.getName(), this.getLogicalTypeAnnotation(), newFields, this.getId());
   }

   public GroupType withNewFields(Type... newFields) {
      return this.withNewFields(Arrays.asList(newFields));
   }

   public String getFieldName(int index) {
      return ((Type)this.fields.get(index)).getName();
   }

   public boolean containsField(String name) {
      return this.indexByName.containsKey(name);
   }

   public int getFieldIndex(String name) {
      Integer i = (Integer)this.indexByName.get(name);
      if (i == null) {
         throw new InvalidRecordException(name + " not found in " + this);
      } else {
         return i;
      }
   }

   public List getFields() {
      return this.fields;
   }

   public int getFieldCount() {
      return this.fields.size();
   }

   public boolean isPrimitive() {
      return false;
   }

   public Type getType(String fieldName) {
      return this.getType(this.getFieldIndex(fieldName));
   }

   public Type getType(int index) {
      return (Type)this.fields.get(index);
   }

   void membersDisplayString(StringBuilder sb, String indent) {
      for(Type field : this.fields) {
         field.writeToStringBuilder(sb, indent);
         if (field.isPrimitive()) {
            sb.append(";");
         }

         sb.append("\n");
      }

   }

   public void writeToStringBuilder(StringBuilder sb, String indent) {
      sb.append(indent).append(this.getRepetition().name().toLowerCase(Locale.ENGLISH)).append(" group ").append(this.getName()).append(this.getLogicalTypeAnnotation() == null ? "" : " (" + this.getLogicalTypeAnnotation().toString() + ")").append(this.getId() == null ? "" : " = " + this.getId()).append(" {\n");
      this.membersDisplayString(sb, indent + "  ");
      sb.append(indent).append("}");
   }

   public void accept(TypeVisitor visitor) {
      visitor.visit(this);
   }

   /** @deprecated */
   @Deprecated
   protected int typeHashCode() {
      return this.hashCode();
   }

   /** @deprecated */
   @Deprecated
   protected boolean typeEquals(Type other) {
      return this.equals(other);
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.getLogicalTypeAnnotation(), this.getFields()});
   }

   protected boolean equals(Type otherType) {
      return !otherType.isPrimitive() && super.equals(otherType) && Objects.equals(this.getLogicalTypeAnnotation(), otherType.getLogicalTypeAnnotation()) && this.getFields().equals(otherType.asGroupType().getFields());
   }

   protected int getMaxRepetitionLevel(String[] path, int depth) {
      int myVal = this.isRepetition(Type.Repetition.REPEATED) ? 1 : 0;
      return depth == path.length ? myVal : myVal + this.getType(path[depth]).getMaxRepetitionLevel(path, depth + 1);
   }

   protected int getMaxDefinitionLevel(String[] path, int depth) {
      int myVal = !this.isRepetition(Type.Repetition.REQUIRED) ? 1 : 0;
      return depth == path.length ? myVal : myVal + this.getType(path[depth]).getMaxDefinitionLevel(path, depth + 1);
   }

   protected Type getType(String[] path, int depth) {
      return (Type)(depth == path.length ? this : this.getType(path[depth]).getType(path, depth + 1));
   }

   protected boolean containsPath(String[] path, int depth) {
      if (depth == path.length) {
         return false;
      } else {
         return this.containsField(path[depth]) && this.getType(path[depth]).containsPath(path, depth + 1);
      }
   }

   protected List getPaths(int depth) {
      List<String[]> result = new ArrayList();

      for(Type field : this.fields) {
         for(String[] path : field.getPaths(depth + 1)) {
            path[depth] = field.getName();
            result.add(path);
         }
      }

      return result;
   }

   void checkContains(Type subType) {
      super.checkContains(subType);
      this.checkGroupContains(subType);
   }

   void checkGroupContains(Type subType) {
      if (subType.isPrimitive()) {
         throw new InvalidRecordException(subType + " found: expected " + this);
      } else {
         for(Type otherType : subType.asGroupType().getFields()) {
            Type thisType = this.getType(otherType.getName());
            thisType.checkContains(otherType);
         }

      }
   }

   Object convert(List path, TypeConverter converter) {
      List<GroupType> childrenPath = new ArrayList(path);
      childrenPath.add(this);
      List<T> children = this.convertChildren(childrenPath, converter);
      return converter.convertGroupType(path, this, children);
   }

   protected List convertChildren(List path, TypeConverter converter) {
      List<T> children = new ArrayList(this.fields.size());

      for(Type field : this.fields) {
         children.add(field.convert(path, converter));
      }

      return children;
   }

   protected Type union(Type toMerge) {
      return this.union(toMerge, true);
   }

   protected Type union(Type toMerge, boolean strict) {
      if (toMerge.isPrimitive()) {
         throw new IncompatibleSchemaModificationException("can not merge primitive type " + toMerge + " into group type " + this);
      } else {
         return new GroupType(toMerge.getRepetition(), this.getName(), toMerge.getLogicalTypeAnnotation(), this.mergeFields(toMerge.asGroupType()), this.getId());
      }
   }

   List mergeFields(GroupType toMerge) {
      return this.mergeFields(toMerge, true);
   }

   List mergeFields(GroupType toMerge, boolean strict) {
      List<Type> newFields = new ArrayList();

      for(Type type : this.getFields()) {
         Type merged;
         if (toMerge.containsField(type.getName())) {
            Type fieldToMerge = toMerge.getType(type.getName());
            if (type.getLogicalTypeAnnotation() != null && !type.getLogicalTypeAnnotation().equals(fieldToMerge.getLogicalTypeAnnotation())) {
               throw new IncompatibleSchemaModificationException("cannot merge logical type " + fieldToMerge.getLogicalTypeAnnotation() + " into " + type.getLogicalTypeAnnotation());
            }

            merged = type.union(fieldToMerge, strict);
         } else {
            merged = type;
         }

         newFields.add(merged);
      }

      for(Type type : toMerge.getFields()) {
         if (!this.containsField(type.getName())) {
            newFields.add(type);
         }
      }

      return newFields;
   }
}
