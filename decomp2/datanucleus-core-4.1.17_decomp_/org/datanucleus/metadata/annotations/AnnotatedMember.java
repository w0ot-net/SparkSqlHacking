package org.datanucleus.metadata.annotations;

class AnnotatedMember {
   Member member;
   AnnotationObject[] annotations;

   public AnnotatedMember(Member field, AnnotationObject[] annotations) {
      this.member = field;
      this.annotations = annotations;
   }

   public String getName() {
      return this.member.getName();
   }

   public Member getMember() {
      return this.member;
   }

   public AnnotationObject[] getAnnotations() {
      return this.annotations;
   }

   public void addAnnotations(AnnotationObject[] annotations) {
      if (this.annotations == null) {
         this.annotations = annotations;
      } else {
         AnnotationObject[] newAnnotations = new AnnotationObject[this.annotations.length + annotations.length];
         int pos = 0;

         for(int i = 0; i < this.annotations.length; ++i) {
            newAnnotations[pos++] = this.annotations[i];
         }

         for(int i = 0; i < annotations.length; ++i) {
            newAnnotations[pos++] = annotations[i];
         }

         this.annotations = newAnnotations;
      }

   }

   public String toString() {
      return this.member.getName();
   }
}
