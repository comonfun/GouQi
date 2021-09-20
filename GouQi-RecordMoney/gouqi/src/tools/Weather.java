package tools;

import java.util.List;

public class Weather {


    /**
     * code : 0
     * data : {"city":{"cityId":285129,"counname":"中国","ianatimezone":"Asia/Shanghai","name":"宝安区","pname":"广东省","secondaryname":"深圳市","timezone":"8"},"forecast":[{"conditionDay":"多云","conditionIdDay":"1","conditionIdNight":"31","conditionNight":"多云","humidity":"73","pop":"20","predictDate":"2021-03-09","qpf":"0.0","tempDay":"24","tempNight":"18","updatetime":"2021-03-09 18:09:00","uvi":"7","windDegreesDay":"0","windDegreesNight":"0","windDirDay":"微风","windDirNight":"微风","windLevelDay":"2","windLevelNight":"2"},{"conditionDay":"阴","conditionIdDay":"2","conditionIdNight":"31","conditionNight":"多云","humidity":"76","pop":"20","predictDate":"2021-03-10","qpf":"0.0","tempDay":"23","tempNight":"18","updatetime":"2021-03-09 18:09:00","uvi":"9","windDegreesDay":"90","windDegreesNight":"90","windDirDay":"东风","windDirNight":"东风","windLevelDay":"3-4","windLevelNight":"3-4"},{"conditionDay":"多云","conditionIdDay":"1","conditionIdNight":"31","conditionNight":"多云","humidity":"73","pop":"20","predictDate":"2021-03-11","qpf":"0.0","tempDay":"25","tempNight":"19","updatetime":"2021-03-09 18:09:00","uvi":"6","windDegreesDay":"0","windDegreesNight":"0","windDirDay":"微风","windDirNight":"微风","windLevelDay":"2","windLevelNight":"1"}]}
     * msg : success
     * rc : {"c":0,"p":"success"}
     */

    private int code;
    /**
     * city : {"cityId":285129,"counname":"中国","ianatimezone":"Asia/Shanghai","name":"宝安区","pname":"广东省","secondaryname":"深圳市","timezone":"8"}
     * forecast : [{"conditionDay":"多云","conditionIdDay":"1","conditionIdNight":"31","conditionNight":"多云","humidity":"73","pop":"20","predictDate":"2021-03-09","qpf":"0.0","tempDay":"24","tempNight":"18","updatetime":"2021-03-09 18:09:00","uvi":"7","windDegreesDay":"0","windDegreesNight":"0","windDirDay":"微风","windDirNight":"微风","windLevelDay":"2","windLevelNight":"2"},{"conditionDay":"阴","conditionIdDay":"2","conditionIdNight":"31","conditionNight":"多云","humidity":"76","pop":"20","predictDate":"2021-03-10","qpf":"0.0","tempDay":"23","tempNight":"18","updatetime":"2021-03-09 18:09:00","uvi":"9","windDegreesDay":"90","windDegreesNight":"90","windDirDay":"东风","windDirNight":"东风","windLevelDay":"3-4","windLevelNight":"3-4"},{"conditionDay":"多云","conditionIdDay":"1","conditionIdNight":"31","conditionNight":"多云","humidity":"73","pop":"20","predictDate":"2021-03-11","qpf":"0.0","tempDay":"25","tempNight":"19","updatetime":"2021-03-09 18:09:00","uvi":"6","windDegreesDay":"0","windDegreesNight":"0","windDirDay":"微风","windDirNight":"微风","windLevelDay":"2","windLevelNight":"1"}]
     */

    private DataBean data;
    private String msg;
    /**
     * c : 0
     * p : success
     */

    private RcBean rc;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RcBean getRc() {
        return rc;
    }

    public void setRc(RcBean rc) {
        this.rc = rc;
    }

    public static class DataBean {
        /**
         * cityId : 285129
         * counname : 中国
         * ianatimezone : Asia/Shanghai
         * name : 宝安区
         * pname : 广东省
         * secondaryname : 深圳市
         * timezone : 8
         */

        private CityBean city;
        /**
         * conditionDay : 多云
         * conditionIdDay : 1
         * conditionIdNight : 31
         * conditionNight : 多云
         * humidity : 73
         * pop : 20
         * predictDate : 2021-03-09
         * qpf : 0.0
         * tempDay : 24
         * tempNight : 18
         * updatetime : 2021-03-09 18:09:00
         * uvi : 7
         * windDegreesDay : 0
         * windDegreesNight : 0
         * windDirDay : 微风
         * windDirNight : 微风
         * windLevelDay : 2
         * windLevelNight : 2
         */

        private List<ForecastBean> forecast;

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class CityBean {
            private int cityId;
            private String counname;
            private String ianatimezone;
            private String name;
            private String pname;
            private String secondaryname;
            private String timezone;

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public String getCounname() {
                return counname;
            }

            public void setCounname(String counname) {
                this.counname = counname;
            }

            public String getIanatimezone() {
                return ianatimezone;
            }

            public void setIanatimezone(String ianatimezone) {
                this.ianatimezone = ianatimezone;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPname() {
                return pname;
            }

            public void setPname(String pname) {
                this.pname = pname;
            }

            public String getSecondaryname() {
                return secondaryname;
            }

            public void setSecondaryname(String secondaryname) {
                this.secondaryname = secondaryname;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }
        }

        public static class ForecastBean {
            private String conditionDay;
            private String conditionIdDay;
            private String conditionIdNight;
            private String conditionNight;
            private String humidity;
            private String pop;
            private String predictDate;
            private String qpf;
            private String tempDay;
            private String tempNight;
            private String updatetime;
            private String uvi;
            private String windDegreesDay;
            private String windDegreesNight;
            private String windDirDay;
            private String windDirNight;
            private String windLevelDay;
            private String windLevelNight;

            public String getConditionDay() {
                return conditionDay;
            }

            public void setConditionDay(String conditionDay) {
                this.conditionDay = conditionDay;
            }

            public String getConditionIdDay() {
                return conditionIdDay;
            }

            public void setConditionIdDay(String conditionIdDay) {
                this.conditionIdDay = conditionIdDay;
            }

            public String getConditionIdNight() {
                return conditionIdNight;
            }

            public void setConditionIdNight(String conditionIdNight) {
                this.conditionIdNight = conditionIdNight;
            }

            public String getConditionNight() {
                return conditionNight;
            }

            public void setConditionNight(String conditionNight) {
                this.conditionNight = conditionNight;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPredictDate() {
                return predictDate;
            }

            public void setPredictDate(String predictDate) {
                this.predictDate = predictDate;
            }

            public String getQpf() {
                return qpf;
            }

            public void setQpf(String qpf) {
                this.qpf = qpf;
            }

            public String getTempDay() {
                return tempDay;
            }

            public void setTempDay(String tempDay) {
                this.tempDay = tempDay;
            }

            public String getTempNight() {
                return tempNight;
            }

            public void setTempNight(String tempNight) {
                this.tempNight = tempNight;
            }

            public String getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(String updatetime) {
                this.updatetime = updatetime;
            }

            public String getUvi() {
                return uvi;
            }

            public void setUvi(String uvi) {
                this.uvi = uvi;
            }

            public String getWindDegreesDay() {
                return windDegreesDay;
            }

            public void setWindDegreesDay(String windDegreesDay) {
                this.windDegreesDay = windDegreesDay;
            }

            public String getWindDegreesNight() {
                return windDegreesNight;
            }

            public void setWindDegreesNight(String windDegreesNight) {
                this.windDegreesNight = windDegreesNight;
            }

            public String getWindDirDay() {
                return windDirDay;
            }

            public void setWindDirDay(String windDirDay) {
                this.windDirDay = windDirDay;
            }

            public String getWindDirNight() {
                return windDirNight;
            }

            public void setWindDirNight(String windDirNight) {
                this.windDirNight = windDirNight;
            }

            public String getWindLevelDay() {
                return windLevelDay;
            }

            public void setWindLevelDay(String windLevelDay) {
                this.windLevelDay = windLevelDay;
            }

            public String getWindLevelNight() {
                return windLevelNight;
            }

            public void setWindLevelNight(String windLevelNight) {
                this.windLevelNight = windLevelNight;
            }
        }
    }

    public static class RcBean {
        private int c;
        private String p;

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public String getP() {
            return p;
        }

        public void setP(String p) {
            this.p = p;
        }
    }
}
