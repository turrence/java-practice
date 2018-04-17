import java.time.LocalTime;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   // additional likely methods not defined since they are not needed for testing

   public boolean equals(Object o){
        if (o == null)
            return false;
        if (o.getClass() != getClass())
            return false;
        CourseSection other = (CourseSection) o;
        return
            ( (prefix == null) ? other.prefix == null : (prefix.equals(other.prefix)) ) &&
            ( (number == null) ? other.number == null : (number.equals(other.number)) ) &&
            ( (startTime == null) ? other.startTime == null : startTime.equals(other.startTime) ) &&
            ( (endTime == null) ? other.endTime == null : endTime.equals(other.endTime) ) &&
            enrollment == other.enrollment;
   }

   public int hashCode(){
       int result = 1;
       result = 37 *  result + ((prefix == null) ? 0 : prefix.hashCode());
       result = 37 *  result + ((number == null) ? 0 : number.hashCode());
       result = 37 *  result + enrollment;
       result = 37 *  result + ((startTime == null) ? 0 : startTime.hashCode());
       result = 37 *  result + ((endTime == null) ? 0 : endTime.hashCode());
       return result;
   }


}
