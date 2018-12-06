package exercise1;

import java.util.*;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {
    private String name;
    private String registrationumNber;
    HashMap<String , Integer > ScoreByCourse = new HashMap<>();   //mettre integer et pas int car il faut mettre une classe dans la syntaxe


    /**
     * Creates a new Student.
     *
     * @throws NullPointerException if one of the parameter is null.
     */
    public Student(String name, String registrationumNber) {
        this.name=name;
        this.registrationumNber=registrationumNber;
    }

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    public void setScore(String course, int score) {
        this.ScoreByCourse.put(course,score);     //pas vraiment besoin du this
    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */

    public OptionalInt getScore(String course) {             /*optional?*/
        Integer nullableScore=ScoreByCourse.get(course);
        return nullableScore!=null?  OptionalInt.of(nullableScore):OptionalInt.empty();      /*le get(String cours) va retouner toutes les valeurs associées à la clé cours
                                                                        OptionalInt.of() c'est pour transformer l'entier ScoreByCourse.get(course) en un objet de la classe Integer
                                                                           optional sert à retourner nal si ScoreByCourse.get(course) ne signifie rien (tout ceci est predefini dans la classe Integer)
                                                                           si nullableScore!=null on retourne le score*/
    }

    /**
     * Returns the average score.
     *
     * @return the average score or 0 if there is none.
     */
    public double averageScore() {
        /*int count=0;
        for (Integer score : ScoreByCourse.values()) {
            count++;
            totalScore+=score;
        }
        return totalScore/count;*/

        return ScoreByCourse.values().stream() //on recupère les valeurs de la map dans un flux
            .mapToInt(Integer :: intValue)   //on converti en entiers
            .average()        //on calcule la moyenne avec une foction average predefinie
            .orElse(0.0);
    }

    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {
        /*int bestScore=0;
        string bestCourse;
        for (String course : ScoreByCourse.keySet()) {
            if(getScore(course)>bestScore) {
                bestScore=getScore(course);
                bestCourse=course;
            }
        }

            return bestCourse;*/

            return ScoreByCourse.entrySet().stream()        //metre la map dans un flux
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))  //trier le fux par odre decroissant
            .map(Map.Entry::getKey)
            .findFirst;    //le premier elt ets le plus grand recherché
    }

    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {
        return ScoreByCourse.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))  //trier le fux par odre decroissant
            .mapToInt(Map.Entry::getValue)
            .findFirst
            .orElse(0);
    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {
      /*  return ScoreByCourse.entrySet().stream()
            .filter(entry -> entry.getValue()<12)
            .sorted(Map.Entry.comparingByValue(comparator.reverseOrder()))
            .collect(collectors.toCollection(LinkedHashSet::new));
            */

      List<Map.Entry<String, Integer>> fiteredEntries= new ArrayList<>();
      for(Map.Entry<String, Integer> entry:: ScoreByCourse.entrySet()){
          if(entry.getValue()<12){
              fiteredEntries.add(entry);
          }
        }
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
        return false;
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() { return null; }

    public String getName() {
        return null;
    }

    public String getRegistrationNumber() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
