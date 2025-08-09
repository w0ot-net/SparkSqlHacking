package org.apache.thrift.partial;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ThriftField {
   public final String name;
   public final List fields;
   private int hashcode;

   ThriftField(String name, List fields) {
      this.hashcode = 0;
      Validate.checkNotNullAndNotEmpty(name, "name");
      Validate.checkNotNull(fields, "fields");
      this.name = name;
      this.fields = Collections.unmodifiableList(fields);
   }

   ThriftField(String name) {
      this(name, Collections.emptyList());
   }

   ThriftField(String name, List fields, boolean allowFieldAdds) {
      this.hashcode = 0;
      Validate.checkNotNullAndNotEmpty(name, "name");
      Validate.checkNotNull(fields, "fields");
      this.name = name;
      this.fields = fields;
   }

   public int hashCode() {
      if (this.hashcode == 0) {
         int hc = this.name.toLowerCase().hashCode();

         for(ThriftField subField : this.fields) {
            hc ^= subField.hashCode();
         }

         this.hashcode = hc;
      }

      return this.hashcode;
   }

   public boolean equals(Object o) {
      if (o == null) {
         return false;
      } else if (!(o instanceof ThriftField)) {
         return false;
      } else {
         ThriftField other = (ThriftField)o;
         if (!this.name.equalsIgnoreCase(other.name)) {
            return false;
         } else if (this.fields.size() != other.fields.size()) {
            return false;
         } else {
            for(int i = 0; i < this.fields.size(); ++i) {
               if (!((ThriftField)this.fields.get(i)).equals(other.fields.get(i))) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public String toString() {
      return String.join(", ", this.getFieldNames());
   }

   public List getFieldNames() {
      List<String> fieldsList = new ArrayList();
      if (this.fields.size() == 0) {
         fieldsList.add(this.name);
      } else {
         for(ThriftField f : this.fields) {
            for(String subF : f.getFieldNames()) {
               fieldsList.add(this.name + "." + subF);
            }
         }
      }

      return fieldsList;
   }

   public static List fromNames(Collection fieldNames) {
      Validate.checkNotNullAndNotEmpty((Iterable)fieldNames, "fieldNames");
      List<String> fieldNamesList = new ArrayList(fieldNames);
      Collections.sort(fieldNamesList, String.CASE_INSENSITIVE_ORDER);
      List<ThriftField> fields = new ArrayList();

      for(String fieldName : fieldNamesList) {
         List<ThriftField> tfields = fields;
         String[] tokens = fieldName.split("\\.");

         for(String token : tokens) {
            ThriftField field = findField(token, tfields);
            if (field == null) {
               field = new ThriftField(token, new ArrayList(), true);
               tfields.add(field);
            }

            tfields = field.fields;
         }
      }

      return makeReadOnly(fields);
   }

   private static ThriftField findField(String name, List fields) {
      for(ThriftField field : fields) {
         if (field.name.equalsIgnoreCase(name)) {
            return field;
         }
      }

      return null;
   }

   private static List makeReadOnly(List fields) {
      List<ThriftField> result = new ArrayList(fields.size());

      for(ThriftField field : fields) {
         ThriftField copy = new ThriftField(field.name, makeReadOnly(field.fields));
         result.add(copy);
      }

      return Collections.unmodifiableList(result);
   }
}
