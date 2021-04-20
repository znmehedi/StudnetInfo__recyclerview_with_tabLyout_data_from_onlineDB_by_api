package com.example.studnetinfo;


public class Student_data_private_info {
    public String StudentId,Name,Father_Name,Mother_Name,Address;

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getFather_Name() {
        return Father_Name;
    }

    public void setFather_Name(String Father_Name) {
        this.Father_Name = Father_Name;
    }

    public String getMother_Name() {
        return Mother_Name;
    }

    public void setMother_Name(String Mother_Name) {
        this.Mother_Name = Mother_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
}
