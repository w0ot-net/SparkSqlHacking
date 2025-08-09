package org.apache.ivy.ant;

import org.apache.ivy.core.retrieve.FileNameMapper;
import org.apache.tools.ant.types.Mapper;

class MapperAdapter implements FileNameMapper {
   private static final String[] EMPTY = new String[0];
   private Mapper mapper;

   MapperAdapter(Mapper mapper) {
      this.mapper = mapper;
   }

   public String[] mapFileName(String fileName) {
      String[] result = this.mapper.getImplementation().mapFileName(fileName);
      if (result == null) {
         result = EMPTY;
      }

      return result;
   }
}
