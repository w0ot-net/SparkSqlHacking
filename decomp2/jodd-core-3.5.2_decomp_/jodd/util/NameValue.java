package jodd.util;

public class NameValue {
   protected Object name;
   protected Object value;

   public NameValue() {
   }

   public NameValue(Object name, Object value) {
      this.name = name;
      this.value = value;
   }

   public void setName(Object name) {
      this.name = name;
   }

   public Object getName() {
      return this.name;
   }

   public Object getValue() {
      return this.value;
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public boolean equals(Object o) {
      if (!(o instanceof NameValue)) {
         return false;
      } else {
         NameValue that = (NameValue)o;
         Object n1 = this.getName();
         Object n2 = that.getName();
         if (n1 == n2 || n1 != null && n1.equals(n2)) {
            Object v1 = this.getValue();
            Object v2 = that.getValue();
            if (v1 == v2 || v1 != null && v1.equals(v2)) {
               return true;
            }
         }

         return false;
      }
   }

   public int hashCode() {
      return (this.name == null ? 0 : this.name.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
   }
}
