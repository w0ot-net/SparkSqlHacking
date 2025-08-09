package org.apache.arrow.vector.util;

import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.compare.TypeEqualsVisitor;

public class VectorSchemaRootAppender {
   public static void append(boolean checkSchema, VectorSchemaRoot targetRoot, VectorSchemaRoot... rootsToAppend) {
      VectorAppender[] appenders = new VectorAppender[targetRoot.getFieldVectors().size()];

      for(int i = 0; i < appenders.length; ++i) {
         appenders[i] = new VectorAppender(targetRoot.getVector(i));
      }

      TypeEqualsVisitor[] typeCheckers = null;
      if (checkSchema) {
         typeCheckers = new TypeEqualsVisitor[targetRoot.getFieldVectors().size()];

         for(int i = 0; i < typeCheckers.length; ++i) {
            typeCheckers[i] = new TypeEqualsVisitor(targetRoot.getVector(i), false, false);
         }
      }

      for(VectorSchemaRoot delta : rootsToAppend) {
         if (checkSchema) {
            if (delta.getFieldVectors().size() != targetRoot.getFieldVectors().size()) {
               throw new IllegalArgumentException("Vector schema roots have different numbers of child vectors.");
            }

            for(int i = 0; i < typeCheckers.length; ++i) {
               if (!typeCheckers[i].equals(delta.getVector(i))) {
                  throw new IllegalArgumentException("Vector schema roots have different schemas.");
               }
            }
         }

         for(int i = 0; i < appenders.length; ++i) {
            delta.getVector(i).accept(appenders[i], (Object)null);
         }

         targetRoot.setRowCount(targetRoot.getRowCount() + delta.getRowCount());
      }

   }

   public static void append(VectorSchemaRoot targetRoot, VectorSchemaRoot... rootsToAppend) {
      append(true, targetRoot, rootsToAppend);
   }
}
