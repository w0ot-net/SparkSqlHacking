package org.apache.parquet.example;

import org.apache.parquet.example.data.Group;
import org.apache.parquet.example.data.simple.SimpleGroup;
import org.apache.parquet.schema.GroupType;
import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.PrimitiveType;
import org.apache.parquet.schema.Type;

public class Paper {
   public static final MessageType schema;
   public static final MessageType schema2;
   public static final MessageType schema3;
   public static final SimpleGroup r1;
   public static final SimpleGroup r2;
   public static final SimpleGroup pr1;
   public static final SimpleGroup pr2;

   static {
      schema = new MessageType("Document", new Type[]{new PrimitiveType(Type.Repetition.REQUIRED, PrimitiveType.PrimitiveTypeName.INT64, "DocId"), new GroupType(Type.Repetition.OPTIONAL, "Links", new Type[]{new PrimitiveType(Type.Repetition.REPEATED, PrimitiveType.PrimitiveTypeName.INT64, "Backward"), new PrimitiveType(Type.Repetition.REPEATED, PrimitiveType.PrimitiveTypeName.INT64, "Forward")}), new GroupType(Type.Repetition.REPEATED, "Name", new Type[]{new GroupType(Type.Repetition.REPEATED, "Language", new Type[]{new PrimitiveType(Type.Repetition.REQUIRED, PrimitiveType.PrimitiveTypeName.BINARY, "Code"), new PrimitiveType(Type.Repetition.OPTIONAL, PrimitiveType.PrimitiveTypeName.BINARY, "Country")}), new PrimitiveType(Type.Repetition.OPTIONAL, PrimitiveType.PrimitiveTypeName.BINARY, "Url")})});
      schema2 = new MessageType("Document", new Type[]{new PrimitiveType(Type.Repetition.REQUIRED, PrimitiveType.PrimitiveTypeName.INT64, "DocId"), new GroupType(Type.Repetition.REPEATED, "Name", new Type[]{new GroupType(Type.Repetition.REPEATED, "Language", new Type[]{new PrimitiveType(Type.Repetition.OPTIONAL, PrimitiveType.PrimitiveTypeName.BINARY, "Country")})})});
      schema3 = new MessageType("Document", new Type[]{new PrimitiveType(Type.Repetition.REQUIRED, PrimitiveType.PrimitiveTypeName.INT64, "DocId"), new GroupType(Type.Repetition.OPTIONAL, "Links", new Type[]{new PrimitiveType(Type.Repetition.REPEATED, PrimitiveType.PrimitiveTypeName.INT64, "Backward"), new PrimitiveType(Type.Repetition.REPEATED, PrimitiveType.PrimitiveTypeName.INT64, "Forward")})});
      r1 = new SimpleGroup(schema);
      r2 = new SimpleGroup(schema);
      r1.add("DocId", 10L);
      r1.addGroup("Links").append("Forward", 20L).append("Forward", 40L).append("Forward", 60L);
      Group name = r1.addGroup("Name");
      name.addGroup("Language").append("Code", "en-us").append("Country", "us");
      name.addGroup("Language").append("Code", "en");
      name.append("Url", "http://A");
      name = r1.addGroup("Name");
      name.append("Url", "http://B");
      name = r1.addGroup("Name");
      name.addGroup("Language").append("Code", "en-gb").append("Country", "gb");
      r2.add("DocId", 20L);
      r2.addGroup("Links").append("Backward", 10L).append("Backward", 30L).append("Forward", 80L);
      r2.addGroup("Name").append("Url", "http://C");
      pr1 = new SimpleGroup(schema2);
      pr2 = new SimpleGroup(schema2);
      pr1.add("DocId", 10L);
      name = pr1.addGroup("Name");
      name.addGroup("Language").append("Country", "us");
      name.addGroup("Language");
      name = pr1.addGroup("Name");
      name = pr1.addGroup("Name");
      name.addGroup("Language").append("Country", "gb");
      pr2.add("DocId", 20L);
      pr2.addGroup("Name");
   }
}
