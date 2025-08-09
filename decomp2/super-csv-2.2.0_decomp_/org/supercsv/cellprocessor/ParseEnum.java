package org.supercsv.cellprocessor;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.cellprocessor.ift.StringCellProcessor;
import org.supercsv.exception.SuperCsvCellProcessorException;
import org.supercsv.util.CsvContext;

public class ParseEnum extends CellProcessorAdaptor implements StringCellProcessor {
   private final Class enumClass;
   private final boolean ignoreCase;

   public ParseEnum(Class enumClass) {
      checkPreconditions(enumClass);
      this.enumClass = enumClass;
      this.ignoreCase = false;
   }

   public ParseEnum(Class enumClass, boolean ignoreCase) {
      checkPreconditions(enumClass);
      this.enumClass = enumClass;
      this.ignoreCase = ignoreCase;
   }

   public ParseEnum(Class enumClass, CellProcessor next) {
      super(next);
      checkPreconditions(enumClass);
      this.enumClass = enumClass;
      this.ignoreCase = false;
   }

   public ParseEnum(Class enumClass, boolean ignoreCase, CellProcessor next) {
      super(next);
      checkPreconditions(enumClass);
      this.enumClass = enumClass;
      this.ignoreCase = ignoreCase;
   }

   private static void checkPreconditions(Class enumClass) {
      if (enumClass == null) {
         throw new NullPointerException("enumClass should not be null");
      }
   }

   public Object execute(Object value, CsvContext context) {
      this.validateInputNotNull(value, context);
      String inputString = value.toString();
      Enum[] var4 = (Enum[])this.enumClass.getEnumConstants();
      int var5 = var4.length;
      int var6 = 0;

      Enum<?> enumConstant;
      while(true) {
         if (var6 >= var5) {
            throw new SuperCsvCellProcessorException(String.format("'%s' could not be parsed as a enum of type %s", value, this.enumClass.getName()), context, this);
         }

         enumConstant = var4[var6];
         String constantName = enumConstant.name();
         if (this.ignoreCase) {
            if (constantName.equalsIgnoreCase(inputString)) {
               break;
            }
         } else if (constantName.equals(inputString)) {
            break;
         }

         ++var6;
      }

      return enumConstant;
   }
}
