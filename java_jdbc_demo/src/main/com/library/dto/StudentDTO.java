package main.com.library.dto;

public class StudentDTO {
  private int  studentId;
  private String  studentName;
  private String studentEmail;

    public StudentDTO(){}
    public StudentDTO(int studentId, String studentName, String studentEmail) {
      this.studentId = studentId;
      this.studentName = studentName;
      this.studentEmail = studentEmail;
    }

    public int getStudentId(){
        return studentId;
    }
    public String getStudentName(){
        return studentName;
    }
    public String getStudentEmail(){
        return studentEmail;
    }

    public  void setStudentId(int studentId){
        this.studentId = studentId;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "StudentDTO [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail +"]";
    }

}
