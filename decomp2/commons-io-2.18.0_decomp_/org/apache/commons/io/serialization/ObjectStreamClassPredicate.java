package org.apache.commons.io.serialization;

import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ObjectStreamClassPredicate implements Predicate {
   private final List acceptMatchers = new ArrayList();
   private final List rejectMatchers = new ArrayList();

   public ObjectStreamClassPredicate accept(Class... classes) {
      Stream var10000 = Stream.of(classes).map((c) -> new FullClassNameMatcher(new String[]{c.getName()}));
      List var10001 = this.acceptMatchers;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      return this;
   }

   public ObjectStreamClassPredicate accept(ClassNameMatcher matcher) {
      this.acceptMatchers.add(matcher);
      return this;
   }

   public ObjectStreamClassPredicate accept(Pattern pattern) {
      this.acceptMatchers.add(new RegexpClassNameMatcher(pattern));
      return this;
   }

   public ObjectStreamClassPredicate accept(String... patterns) {
      Stream var10000 = Stream.of(patterns).map(WildcardClassNameMatcher::new);
      List var10001 = this.acceptMatchers;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      return this;
   }

   public ObjectStreamClassPredicate reject(Class... classes) {
      Stream var10000 = Stream.of(classes).map((c) -> new FullClassNameMatcher(new String[]{c.getName()}));
      List var10001 = this.rejectMatchers;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      return this;
   }

   public ObjectStreamClassPredicate reject(ClassNameMatcher m) {
      this.rejectMatchers.add(m);
      return this;
   }

   public ObjectStreamClassPredicate reject(Pattern pattern) {
      this.rejectMatchers.add(new RegexpClassNameMatcher(pattern));
      return this;
   }

   public ObjectStreamClassPredicate reject(String... patterns) {
      Stream var10000 = Stream.of(patterns).map(WildcardClassNameMatcher::new);
      List var10001 = this.rejectMatchers;
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::add);
      return this;
   }

   public boolean test(ObjectStreamClass objectStreamClass) {
      return this.test(objectStreamClass.getName());
   }

   public boolean test(String name) {
      for(ClassNameMatcher m : this.rejectMatchers) {
         if (m.matches(name)) {
            return false;
         }
      }

      for(ClassNameMatcher m : this.acceptMatchers) {
         if (m.matches(name)) {
            return true;
         }
      }

      return false;
   }
}
