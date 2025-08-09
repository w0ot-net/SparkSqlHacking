package org.checkerframework.framework.qual;

public enum TypeKind {
   BOOLEAN,
   BYTE,
   SHORT,
   INT,
   LONG,
   CHAR,
   FLOAT,
   DOUBLE,
   VOID,
   NONE,
   NULL,
   ARRAY,
   DECLARED,
   ERROR,
   TYPEVAR,
   WILDCARD,
   PACKAGE,
   EXECUTABLE,
   OTHER,
   UNION,
   INTERSECTION;

   // $FF: synthetic method
   private static TypeKind[] $values() {
      return new TypeKind[]{BOOLEAN, BYTE, SHORT, INT, LONG, CHAR, FLOAT, DOUBLE, VOID, NONE, NULL, ARRAY, DECLARED, ERROR, TYPEVAR, WILDCARD, PACKAGE, EXECUTABLE, OTHER, UNION, INTERSECTION};
   }
}
