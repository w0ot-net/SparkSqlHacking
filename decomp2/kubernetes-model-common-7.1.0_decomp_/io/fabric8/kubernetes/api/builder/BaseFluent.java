package io.fabric8.kubernetes.api.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseFluent {
   public static final String VISIT = "visit";
   public final VisitableMap _visitables = new VisitableMap();

   public static VisitableBuilder builderOf(Object item) {
      if (item instanceof Editable) {
         Object editor = ((Editable)item).edit();
         if (editor instanceof VisitableBuilder) {
            return (VisitableBuilder)editor;
         }
      }

      try {
         return (VisitableBuilder)Class.forName(item.getClass().getName() + "Builder", true, item.getClass().getClassLoader()).getConstructor(item.getClass()).newInstance(item);
      } catch (Exception var4) {
         try {
            return (VisitableBuilder)Class.forName(item.getClass().getName() + "Builder").getConstructor(item.getClass()).newInstance(item);
         } catch (Exception e1) {
            throw new IllegalStateException("Failed to create builder for: " + item.getClass(), e1);
         }
      }
   }

   public static List build(List list) {
      return list == null ? null : (List)list.stream().map(Builder::build).collect(Collectors.toList());
   }

   public static Set build(Set set) {
      return set == null ? null : new LinkedHashSet((Collection)set.stream().map(Builder::build).collect(Collectors.toSet()));
   }

   public static List aggregate(List... lists) {
      return new ArrayList((Collection)Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));
   }

   public static Set aggregate(Set... sets) {
      return new LinkedHashSet((Collection)Arrays.stream(sets).filter(Objects::nonNull).collect(Collectors.toSet()));
   }

   public Optional getVisitableMap() {
      return Optional.of(this._visitables);
   }

   public int hashCode() {
      int prime = 31;
      int result = 1;
      result = 31 * result + 0;
      return result;
   }

   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else {
         return this.getClass() == obj.getClass();
      }
   }
}
