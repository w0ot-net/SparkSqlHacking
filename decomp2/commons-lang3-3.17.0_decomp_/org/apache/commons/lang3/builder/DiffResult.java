package org.apache.commons.lang3.builder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DiffResult implements Iterable {
   public static final String OBJECTS_SAME_STRING = "";
   private final List diffList;
   private final Object lhs;
   private final Object rhs;
   private final ToStringStyle style;
   private final String toStringFormat;

   DiffResult(Object lhs, Object rhs, List diffList, ToStringStyle style, String toStringFormat) {
      this.diffList = (List)Objects.requireNonNull(diffList, "diffList");
      this.lhs = Objects.requireNonNull(lhs, "lhs");
      this.rhs = Objects.requireNonNull(rhs, "rhs");
      this.style = (ToStringStyle)Objects.requireNonNull(style, "style");
      this.toStringFormat = (String)Objects.requireNonNull(toStringFormat, "toStringFormat");
   }

   public List getDiffs() {
      return Collections.unmodifiableList(this.diffList);
   }

   public Object getLeft() {
      return this.lhs;
   }

   public int getNumberOfDiffs() {
      return this.diffList.size();
   }

   public Object getRight() {
      return this.rhs;
   }

   public ToStringStyle getToStringStyle() {
      return this.style;
   }

   public Iterator iterator() {
      return this.diffList.iterator();
   }

   public String toString() {
      return this.toString(this.style);
   }

   public String toString(ToStringStyle style) {
      if (this.diffList.isEmpty()) {
         return "";
      } else {
         ToStringBuilder lhsBuilder = new ToStringBuilder(this.lhs, style);
         ToStringBuilder rhsBuilder = new ToStringBuilder(this.rhs, style);
         this.diffList.forEach((diff) -> {
            lhsBuilder.append(diff.getFieldName(), diff.getLeft());
            rhsBuilder.append(diff.getFieldName(), diff.getRight());
         });
         return String.format(this.toStringFormat, lhsBuilder.build(), rhsBuilder.build());
      }
   }
}
