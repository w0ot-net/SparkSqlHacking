package org.apache.parquet.schema;

import java.util.ArrayList;
import java.util.List;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.io.InvalidRecordException;

public final class MessageType extends GroupType {
   public MessageType(String name, Type... fields) {
      super(Type.Repetition.REPEATED, name, fields);
   }

   public MessageType(String name, List fields) {
      super(Type.Repetition.REPEATED, name, fields);
   }

   public void accept(TypeVisitor visitor) {
      visitor.visit(this);
   }

   public void writeToStringBuilder(StringBuilder sb, String indent) {
      sb.append("message ").append(this.getName()).append(this.getLogicalTypeAnnotation() == null ? "" : " (" + this.getLogicalTypeAnnotation().toString() + ")").append(" {\n");
      this.membersDisplayString(sb, "  ");
      sb.append("}\n");
   }

   public int getMaxRepetitionLevel(String... path) {
      return this.getMaxRepetitionLevel(path, 0) - 1;
   }

   public int getMaxDefinitionLevel(String... path) {
      return this.getMaxDefinitionLevel(path, 0) - 1;
   }

   public Type getType(String... path) {
      return this.getType(path, 0);
   }

   public ColumnDescriptor getColumnDescription(String[] path) {
      int maxRep = this.getMaxRepetitionLevel(path);
      int maxDef = this.getMaxDefinitionLevel(path);
      PrimitiveType type = this.getType(path).asPrimitiveType();
      return new ColumnDescriptor(path, type, maxRep, maxDef);
   }

   public List getPaths() {
      return this.getPaths(0);
   }

   public List getColumns() {
      List<String[]> paths = this.getPaths(0);
      List<ColumnDescriptor> columns = new ArrayList(paths.size());

      for(String[] path : paths) {
         PrimitiveType primitiveType = this.getType(path).asPrimitiveType();
         columns.add(new ColumnDescriptor(path, primitiveType, this.getMaxRepetitionLevel(path), this.getMaxDefinitionLevel(path)));
      }

      return columns;
   }

   public void checkContains(Type subType) {
      if (!(subType instanceof MessageType)) {
         throw new InvalidRecordException(subType + " found: expected " + this);
      } else {
         this.checkGroupContains(subType);
      }
   }

   public Object convertWith(TypeConverter converter) {
      ArrayList<GroupType> path = new ArrayList();
      path.add(this);
      return converter.convertMessageType(this, this.convertChildren(path, converter));
   }

   public boolean containsPath(String[] path) {
      return this.containsPath(path, 0);
   }

   public MessageType union(MessageType toMerge) {
      return this.union(toMerge, true);
   }

   public MessageType union(MessageType toMerge, boolean strict) {
      return new MessageType(this.getName(), this.mergeFields(toMerge, strict));
   }
}
