package com.lst.burns.weather;

public class WeatherUtil {
    final static String APIKey = "2cfe285890da4c14bc77bb079c7bebd6";
    final static String RegularUrl = "https://free-api.heweather.com/s6/weather?location=";
    final static String key = "&key=";
    /*
    https://free-api.heweather.com/s6/weather?location=%E5%8C%97%E4%BA%AC&key=2cfe285890da4c14bc77bb079c7bebd6
     */

    /*
    {

"HeWeather6":[
	{"basic":
		{"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国",
		"lat":"39.90498734","lon":"116.4052887","tz":"+8.0"
		},
	"update":{"loc":"2017-12-18 13:51","utc":"2017-12-18 05:51"},
	"status":"ok",
	"now":{"cloud":"0","cond_code":"100","cond_txt":"晴","fl":"-7","hum":"18","pcpn":"0.0","pres":"1030","tmp":"5","vis":"8","wind_deg":"297","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"15"},
	"daily_forecast":[
		{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2017-12-18","hum":"22","mr":"07:06","ms":"17:07","pcpn":"0.0","pop":"0","pres":"1032","sr":"07:29","ss":"16:53","tmp_max":"6","tmp_min":"-6","uv_index":"1","vis":"20","wind_deg":"301","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"13"},
		{"cond_code_d":"101","cond_code_n":"104","cond_txt_d":"多云","cond_txt_n":"阴","date":"2017-12-19","hum":"26","mr":"07:57","ms":"17:53","pcpn":"0.0","pop":"0","pres":"1034","sr":"07:30","ss":"16:53","tmp_max":"4","tmp_min":"-4","uv_index":"1","vis":"20","wind_deg":"249","wind_dir":"西南风","wind_sc":"微风","wind_spd":"6"},
		{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2017-12-20","hum":"28","mr":"08:45","ms":"18:42","pcpn":"0.0","pop":"0","pres":"1033","sr":"07:30","ss":"16:53","tmp_max":"6","tmp_min":"-6","uv_index":"1","vis":"20","wind_deg":"352","wind_dir":"北风","wind_sc":"微风","wind_spd":"4"}
		],
	"lifestyle":[
		{"brf":"较不舒适","txt":"白天天气晴好，但仍会使您感觉偏冷，不很舒适，请注意适时添加衣物，以防感冒。","type":"comf"},
		{"brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。","type":"drsg"},
		{"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。","type":"flu"},
		{"brf":"较适宜","txt":"天气较好，但考虑风力较强且气温较低，推荐您进行室内运动，若在户外运动注意防风并适当增减衣物。","type":"sport"},
		{"brf":"一般","txt":"天气较好，温度稍低，加之风稍大，让人感觉有点凉，会对外出有一定影响，外出注意防风保暖。","type":"trav"},
		{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。","type":"uv"},
		{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。","type":"cw"},
		{"brf":"中","txt":"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。","type":"air"}
	]
  }
  ]
}
     */
    public static String getWeatherUrl(String location){
        return RegularUrl + location + key + APIKey;
    }
}