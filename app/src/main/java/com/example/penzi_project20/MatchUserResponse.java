package com.example.penzi_project20;

class MatchUserResponse {

    private Integer age;
    private String county;
    private String description;
    private String education_level;
    private String gender;
    private Integer id;
    private String marital_status;
    private String matched_by;
    private String name;
    private String number;
    private String profession;
    private String religion;
    private String status;
    private String time_of_registry;
    private String town;
    private String tribe;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getMatched_by() {
        return matched_by;
    }

    public void setMatched_by(String matched_by) {
        this.matched_by = matched_by;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime_of_registry() {
        return time_of_registry;
    }

    public void setTime_of_registry(String time_of_registry) {
        this.time_of_registry = time_of_registry;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }


    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                ", county='" + county + '\'' +
                ", description='" + description + '\'' +
                ", education_level='" + education_level + '\'' +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", marital_status='" + marital_status + '\'' +
                ", matched_by='" + matched_by + '\'' +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", profession='" + profession + '\'' +
                ", religion='" + religion + '\'' +
                ", status='" + status + '\'' +
                ", time_of_registry='" + time_of_registry + '\'' +
                ", town='" + town + '\'' +
                ", tribe='" + tribe + '\'' +
                '}';
    }

}
