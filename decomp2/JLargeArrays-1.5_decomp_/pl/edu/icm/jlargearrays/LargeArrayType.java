package pl.edu.icm.jlargearrays;

public enum LargeArrayType {
   LOGIC,
   BYTE {
      public boolean isNumericType() {
         return true;
      }

      public boolean isIntegerNumericType() {
         return true;
      }
   },
   UNSIGNED_BYTE {
      public boolean isNumericType() {
         return true;
      }

      public boolean isIntegerNumericType() {
         return true;
      }
   },
   SHORT {
      public long sizeOf() {
         return 2L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isIntegerNumericType() {
         return true;
      }
   },
   INT {
      public long sizeOf() {
         return 4L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isIntegerNumericType() {
         return true;
      }
   },
   LONG {
      public long sizeOf() {
         return 8L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isIntegerNumericType() {
         return true;
      }
   },
   FLOAT {
      public long sizeOf() {
         return 4L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isRealNumericType() {
         return true;
      }
   },
   DOUBLE {
      public long sizeOf() {
         return 8L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isRealNumericType() {
         return true;
      }
   },
   COMPLEX_FLOAT {
      public long sizeOf() {
         return 4L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isComplexNumericType() {
         return true;
      }
   },
   COMPLEX_DOUBLE {
      public long sizeOf() {
         return 8L;
      }

      public boolean isNumericType() {
         return true;
      }

      public boolean isComplexNumericType() {
         return true;
      }
   },
   STRING,
   OBJECT;

   private LargeArrayType() {
   }

   public long sizeOf() {
      return 1L;
   }

   public boolean isNumericType() {
      return false;
   }

   public boolean isIntegerNumericType() {
      return false;
   }

   public boolean isRealNumericType() {
      return false;
   }

   public boolean isComplexNumericType() {
      return false;
   }
}
