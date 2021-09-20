package tools;

public class Position {


    /**
     * code : 100
     * message : success
     * ip : 183.17.59.55
     * result : {"en_short":"CN","en_name":"China","nation":"中国","province":"广东省","city":"深圳市","district":"宝安区","adcode":440306,"lat":22.55329,"lng":113.88308}
     */

    private int code;
    private String message;
    private String ip;
    /**
     * en_short : CN
     * en_name : China
     * nation : 中国
     * province : 广东省
     * city : 深圳市
     * district : 宝安区
     * adcode : 440306
     * lat : 22.55329
     * lng : 113.88308
     */

    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String en_short;
        private String en_name;
        private String nation;
        private String province;
        private String city;
        private String district;
        private int adcode;
        private double lat;
        private double lng;

        public String getEn_short() {
            return en_short;
        }

        public void setEn_short(String en_short) {
            this.en_short = en_short;
        }

        public String getEn_name() {
            return en_name;
        }

        public void setEn_name(String en_name) {
            this.en_name = en_name;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public int getAdcode() {
            return adcode;
        }

        public void setAdcode(int adcode) {
            this.adcode = adcode;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}
