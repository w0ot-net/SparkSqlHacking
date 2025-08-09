package py4j.model;

public abstract class Py4JMember implements Comparable {
   private final String javadoc;
   private final String name;

   public Py4JMember(String name, String javadoc) {
      this.name = name;
      this.javadoc = javadoc;
   }

   public int compareTo(Py4JMember o) {
      return this.getName().compareTo(o.getName());
   }

   public boolean equals(Object obj) {
      return obj != null && obj instanceof Py4JMember ? this.getSignature(false).equals(((Py4JMember)obj).getSignature(false)) : false;
   }

   public int hashCode() {
      return this.getSignature(false).hashCode();
   }

   public String getJavadoc() {
      return this.javadoc;
   }

   public String getName() {
      return this.name;
   }

   public abstract String getSignature(boolean var1);
}
