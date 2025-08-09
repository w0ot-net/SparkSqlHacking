package jodd.mutable;

public class ValueHolder {
   protected Object value;

   public ValueHolder() {
   }

   public ValueHolder(Object value) {
      this.value = value;
   }

   public Object getValue() {
      return this.value;
   }

   public void setValue(Object value) {
      this.value = value;
   }

   public boolean isNull() {
      return this.value == null;
   }

   public String toString() {
      return this.value == null ? "{" + null + '}' : '{' + this.value.toString() + '}';
   }
}
