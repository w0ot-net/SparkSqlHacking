package org.sparkproject.jpmml.model.visitors;

import java.util.Iterator;
import java.util.List;
import org.sparkproject.dmg.pmml.Row;
import org.sparkproject.dmg.pmml.VisitorAction;

public class RowCleaner extends AbstractVisitor {
   public VisitorAction visit(Row row) {
      if (row.hasContent()) {
         List<Object> content = row.getContent();
         Iterator<?> it = content.iterator();

         while(it.hasNext()) {
            Object object = it.next();
            if (object instanceof String) {
               String string = (String)object;
               string = string.trim();
               if ("".equals(string)) {
                  it.remove();
               }
            }
         }
      }

      return super.visit(row);
   }
}
