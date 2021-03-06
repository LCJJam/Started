package org.techtown.started.ui.team;

public class Team {
    public Team(String name, String backNumber, String phoneNumber, String profileImage) {
        Name = name;
        BackNumber = backNumber;
        PhoneNumber = phoneNumber;
        ProfileImage = profileImage;
    }

    private String Name; // 로그인한 아이디
    private String BackNumber; // 게시글 사진
    private String PhoneNumber; // 게시글사진 이름(사진삭제할때 필요, 절대경로를 뜻함)
    private String ProfileImage; // 회원가입시 프로필사진

    public Team(){ }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBackNumber() {
        return BackNumber;
    }

    public void setBackNumber(String backNumber) {
        BackNumber = backNumber;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;

    }
}
