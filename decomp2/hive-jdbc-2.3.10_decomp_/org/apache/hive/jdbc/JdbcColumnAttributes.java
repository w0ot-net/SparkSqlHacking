package org.apache.hive.jdbc;

class JdbcColumnAttributes {
   public int precision = 0;
   public int scale = 0;

   public JdbcColumnAttributes() {
   }

   public JdbcColumnAttributes(int precision, int scale) {
      this.precision = precision;
      this.scale = scale;
   }

   public String toString() {
      return "(" + this.precision + "," + this.scale + ")";
   }
}
