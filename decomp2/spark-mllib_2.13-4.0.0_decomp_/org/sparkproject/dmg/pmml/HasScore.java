package org.sparkproject.dmg.pmml;

public interface HasScore {
   default boolean hasScore() {
      Object score = this.getScore();
      return score != null;
   }

   Object getScore();

   PMMLObject setScore(Object var1);
}
