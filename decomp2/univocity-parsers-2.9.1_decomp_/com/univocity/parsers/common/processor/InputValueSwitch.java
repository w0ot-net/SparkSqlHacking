package com.univocity.parsers.common.processor;

import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.ParsingContextWrapper;
import com.univocity.parsers.common.processor.core.AbstractInputValueSwitch;

public class InputValueSwitch extends AbstractInputValueSwitch implements RowProcessor {
   public InputValueSwitch() {
      this(0);
   }

   public InputValueSwitch(int columnIndex) {
      super(columnIndex);
   }

   public InputValueSwitch(String columnName) {
      super(columnName);
   }

   protected final ParsingContext wrapContext(ParsingContext context) {
      return new ParsingContextWrapper(context) {
         private final String[] fieldNames = InputValueSwitch.this.getHeaders();
         private final int[] indexes = InputValueSwitch.this.getIndexes();

         public String[] headers() {
            return this.fieldNames != null && this.fieldNames.length != 0 ? this.fieldNames : ((ParsingContext)this.context).headers();
         }

         public int[] extractedFieldIndexes() {
            return this.indexes != null && this.indexes.length != 0 ? this.indexes : ((ParsingContext)this.context).extractedFieldIndexes();
         }
      };
   }
}
