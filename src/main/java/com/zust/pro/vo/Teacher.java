package com.zust.pro.vo;

/**
 * @author 86178
 */
public class Teacher extends User{
        private String tec_Name;
        //教师生日
        private String tec_Birth;
        //教师性别
        private String tec_Sex;

        public String getTec_Name() {
            return tec_Name;
        }

        public void setTec_Name(String tec_Name) {
            this.tec_Name = tec_Name;
        }

         public String getTec_Birth() {
            return tec_Birth;
        }

        public void setTec_Birth(String tec_Birth) {
            this.tec_Birth = tec_Birth;
        }

        public String getTec_Sex() {
            return tec_Sex;
        }

        public void setTec_Sex(String tec_Sex) {
            this.tec_Sex = tec_Sex;
        }

        public Teacher() {
        }

    @Override
    public String toString() {
        return "Teacher{" +
                "tec_Name='" + tec_Name + '\'' +
                ", tec_Birth='" + tec_Birth + '\'' +
                ", tec_Sex='" + tec_Sex + '\'' +
                ", id=" + id ;
    }
}
