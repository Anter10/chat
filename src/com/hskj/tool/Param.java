package com.hskj.tool;

 
 
import java.util.regex.Matcher;  

import java.util.regex.Pattern; 
import java.io.*; 
 
import java.util.*;
 

import java.net.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
 
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
 
public class Param {
	private static final String ALIDM_SMTP_HOST = "smtpdm.aliyun.com";
    private static final int ALIDM_SMTP_PORT = 25;//或80
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    public static int webcount = 0;
    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAI3vOaTlcUbNQQ";
    static final String accessKeySecret = "pUwd0GaZIZKyo4cBXOWy7oMmrV9qdA";
    
    
    public static boolean hasUrl(String turl) {  
	    URL url;  
	    try {  
	         url = new URL(turl);  
	         InputStream in = url.openStream();  
	         System.out.println("连接可用"); 
	         return true;
	    } catch (Exception e1) {  
	         System.out.println("连接打不开!");  
	         url = null;  
	         return false;
	    }  
	}  
    
    
    
//    public static boolean sendSms(String Phone,String Name, String code) throws ClientException {
//
//        //可自助调整超时时间
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//
//        //初始化acsClient,暂不支持region化
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//
//        //组装请求对象-具体描述见控制台-文档部分内容
//        SendSmsRequest request = new SendSmsRequest();
//        //必填:待发送手机号
//        request.setPhoneNumbers(Phone);
//        //必填:短信签名-可在短信控制台中找到
//        request.setSignName("黑色科技");
//        //必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode("SMS_53070214");
//        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"name\":\""+Name+"\", \"code\":\""+code+"\"}");
//        
//       
//        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
//        //request.setSmsUpExtendCode("90997");
//
//        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");
//
//        //hint 此处可能会抛出异常，注意catch
//        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//        System.out.println("\n发送绘制的消息="+sendSmsResponse.getCode());
//        System.out.println("\n"+sendSmsResponse.getCode());
//        	try {
//        		
//        } catch (ServiceException se) {
//            
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(sendSmsResponse.getMessage().equals("OK")) {
//        	return true;
//        }else {
//        	return false;
//        }
//        
//    }

    
	 public static String getNowDate() {
		  Date currentTime = new Date();
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String dateString = formatter.format(currentTime);
		  return dateString;
	 }
	  
//	public static void sendEmail(String toemail,String title, String body) {
//		 // 配置发送邮件的环境属性
////        final Properties props = new Properties();
////        // 表示SMTP发送邮件，需要进行身份验证
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
////        props.put("mail.smtp.port", ALIDM_SMTP_PORT);
////        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
////        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
////        // props.put("mail.smtp.socketFactory.port", "465");
////        // props.put("mail.smtp.port", "465");
////        // 发件人的账号
////        props.put("mail.user", "***");
////        // 访问SMTP服务时需要提供的密码
////        props.put("mail.password", "***");
////        // 构建授权信息，用于进行SMTP进行身份验证
////        Authenticator authenticator = new Authenticator() {
////            @Override
////            protected PasswordAuthentication getPasswordAuthentication() {
////                // 用户名、密码
////                String userName = props.getProperty("mail.user");
////                String password = props.getProperty("mail.password");
////                return new PasswordAuthentication(userName, password);
////            }
////        };
////        // 使用环境属性和授权信息，创建邮件会话
////        Session mailSession = Session.getInstance(props, authenticator);
//////        mailSession.setDebug(true);
////        // 创建邮件消息
////        MimeMessage message = new MimeMessage(mailSession);
////        try {
////        // 设置发件人
////        InternetAddress from = new InternetAddress("***");
////        message.setFrom(from);
////        Address[] a = new Address[1];
////        a[0] = new InternetAddress("***");
////        message.setReplyTo(a);
////        // 设置收件人
////        InternetAddress to = new InternetAddress("***");
////        message.setRecipient(MimeMessage.RecipientType.TO, to);
////        // 设置邮件标题
////        message.setSubject("测试邮件");
////        // 设置邮件的内容体
////        message.setContent("测试的HTML邮件", "text/html;charset=UTF-8");
////        // 发送邮件
////        Transport.send(message);
////        }
////        catch (MessagingException e) {
////            String err = e.getMessage();
////            // 在这里处理message内容， 格式是固定的
////            System.out.println(err);
////        }
//		IClientProfile profile = DefaultProfile.getProfile("ap-southeast-1", "LTAI3vOaTlcUbNQQ", "pUwd0GaZIZKyo4cBXOWy7oMmrV9qdA");
//		IAcsClient client = new DefaultAcsClient(profile);
//        SingleSendMailRequest request = new SingleSendMailRequest();
////        try {
////           DefaultProfile.addEndpoint("dm.ap-southeast-1.aliyuncs.com", "ap-southeast-1", "Dm",  "dm.ap-southeast-1.aliyuncs.com");
////        } catch (ClientException e) {
////           e.printStackTrace();
////        }
//        try {
////            request.setVersion("2017-06-22");// 如果是除杭州region外的其它region（如新加坡region）,必须指定为2017-06-22
//               request.setActionName("SingleSendMail");
//               request.setAccountName("tzeb@heisekeji.net");
//               request.setFromAlias("黑色科技");
//               request.setAddressType(1);
//               request.setTagName("usermessage");
//               request.setReplyToAddress(false);
//               request.setToAddress(toemail);
//               request.setSubject(title);
//               request.setHtmlBody(body);
//               SingleSendMailResponse httpResponse = client.getAcsResponse(request);
//               System.out.print("发送邮件后的回调数据 = "+httpResponse);
//           } catch (ServerException e) {
//               e.printStackTrace();
//           }
//           catch (ClientException e) {
//               e.printStackTrace();
//           }
//	}
//	 
//	public static String getErrorData(ErrorMessage.Errors er) throws JSONException{
//		JSONObject obj = new JSONObject();
//		obj.put("errorcode", true);
//		obj.put("info", er.getInfo());
//		obj.put("msg", er.getMessage());
//		obj.put("eid", er.getEid());
//		return obj.toString();
//	}
	
//	public static JSONObject getErrorObjData(ErrorMessage.Errors er) throws JSONException{
//		JSONObject obj = new JSONObject();
//		obj.put("errorcode", true);
//		obj.put("info", er.getInfo());
//		obj.put("msg", er.getMessage());
//		obj.put("eid", er.getEid());
//		return obj;
//	}

	public static boolean isMobileNO(String mobiles){  
	 Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
	 Matcher m = p.matcher(mobiles);   
	 System.out.println(m.matches()+"---");  
	 return m.matches();  
    }  
	
	public static boolean checkEmail(String email) {
	    boolean flag = false;
	    try {
	        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	        Pattern regex = Pattern.compile(check);
	        Matcher matcher = regex.matcher(email);
	        flag = matcher.matches();
	    } catch (Exception e) {
	        flag = false;
	    }
	    return flag;
	}
	
  // 公司邮箱地址
  public static String COMPANYEAMIL = "892466942@qq.com";
  

  //  上传参数字段
  public static String PJ = "pj";
  //  成功返回参数字段
  public static String PS = "ps";
  //  失败返回参数字段
  public static String PE = "pe";
  public static String[] allspstr = {"\n","\r"};
  // 相关数据类型
  public static int INT          =    4;
  public static int VARCHAR      =    12;
  public static int DOUBLE       =    8;  
  public static int FLOAT        =    7;
  public static int YEAR         =    91;
 
  public static String errorKey  =    "error";
  
  public static String showkey   =    "show";
  public static String successKey  =    "success";
  // 获得当前系统时间
  public static long getCurentServerTime(){
	  long curTime = System.currentTimeMillis();
	  return curTime;
  }
  
  public static int getSecondTimestamp(){  
	    String timestamp = String.valueOf(System.currentTimeMillis()/1000);  
	    return Integer.valueOf(timestamp);  
	}  
  
//  得到时间
  public static String getTime(){
	  Date now = new Date(); 
	  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
      String hehe = dateFormat.format( now ); 
	  return hehe;
  }
//  public static JSONArray addMessage;
  public static int MAXNUM = 0;
  public static String updateDataSql;
  public static String addDataSql;
//  public static JSONArray updateModelData;
  public static String getSysYMD(){
	  String     ftime     =    "";
	  Calendar   now       =    Calendar.getInstance();  
	  String     year      =    now.get(Calendar.YEAR)         +    "年";
	  String     month     =    (now.get(Calendar.MONTH) + 1)  +    "月" ;
      String     day       =    now.get(Calendar.DAY_OF_MONTH) +    "日";
      String     time      =    now.get(Calendar.HOUR_OF_DAY)  +    "时";
      String     minute    =    now.get(Calendar.MINUTE)       +    "分";
      String     second    =    now.get(Calendar.SECOND)       +    "秒";
      ftime                =    year + month + day + time + minute + second;
      return     ftime;
  }
  //  失败的相关数据代号
//  public static JSONObject ERROR_CODE = new JSONObject();
//  
  
  // 需要导出单条记录的表 
  public static String [] SingleTable = {
	"SystemIntensifyData"	  
  };
  // 所有表名
  public static String [] allgametable = {
     "SystemTavernData","SystemShowWorldData","SystemMapChapterTask","SystemMapChapterData","SystemStrangeData","SystemDevelopmentData", "needdatas","work","SystemChessData","txt","ChatSystemData","SystemEmailTypeData","PropCombineData","SystemAwardTypeData","AllLanguageData","SystemSkillUpLevelData","SystemHeroPromotionData","AllUpLevelData","AllDialogData","RolesAllDialogData","SystemIntensifyData","ProperityEffectData","SystemSpecialSkillData","SystemBattleFunctionThingsData","AllHerosData","TerritoryCellData","TerritoryMapData", "RoleSuitData", "SystemEquipData","LoadingLayerData","GameTimes","LinkActionData","SystemChapterData","HeroStatues","HeroStatueConvert","SystemBattleUnitData","BattleSceneParamData","AllSpecialData","RoleSpecialData","BattleRoleSpecialGrowup","CommonActionData","SystemBuffData","SystemSkillData","SystemSonSkillData","DialogViewData","SoltNameData","SystemBuyDiamondData","SystemPropData","SystemChatTypeData","SystemEmailData","SystemPropTypeData","SystemGameViewsData","SystemUIAudoEffect","SystemRolesAudioEffect","SystemGameMusic",
     "MainCityResData","MainCityBuilds","SystemBuildLevelData","SystemColor","SystemTextFontData","SystemFontData","SystemFontUseData","SystemIconBottom","SystemIconData","SystemIconFramData","SystemShopTypeData","SystemHeroRandomActions",
     "SystemSkillAudioEffect","SystemRolesAudioEffect","SystemUIAudoEffect","SystemOnlyUiTextData","BattleBackgroundMapData","UINpcData","SystemTerritoryMapTreasureData","systemdandata","BattlePVEAreanPosData","systemmonsterdata","systembattlenpcdata","equipitemdata"
  };
  
  public static String [] allheroproperty = {
		  "100101","100201","100301","100401","100501","100601","100701","100801","100901","101001","101101","101201","101301","101401","101501","101601",
			"101701","101801","101901","102001","160101","160201","160301","160401","160501","160601","160701","160801","160901","161001","180101","191001",
			"192001","193001"
  };
  
  
//  public static String verIdCard(String id, String name) {
////	  ShowapiRequest req = new ShowapiRequest(
////				"请求地址，例如http://ali-weather.showapi.com/ip-to-weather","e5110f684dd14b95a2a0a0f572475e39");
//	  String host = "http://idcard3.market.alicloudapi.com";
//	    String path = "/idcardAudit";
//	    String method = "GET";
//	    String appcode = "e5110f684dd14b95a2a0a0f572475e39";
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("idcard", id);
//	    querys.put("name", name);
//
//
////	    try {
//	    	/**
//	    	* 重要提示如下:
//	    	* HttpUtils请从
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//	    	* 下载
//	    	*
//	    	* 相应的依赖请参照
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//	    	*/
////	    	HttpResponse response = (HttpResponse) HttpUtils.doGet(host, path, method, headers, querys);
//////	    	System.out.println("返回的数据  = "+response.toString());
////	    	//获取response的body
////	    
////	    	return EntityUtils.toString(response.getEntity()); 
////	    } catch (Exception e) {
////	    	e.printStackTrace();
////	    	return null; 
////	    }
//	    
//  }
  
//  public static String someIdCard(String id, String name) {
//		ShowapiRequest req = new ShowapiRequest(
//				"http://idinfo.market.alicloudapi.com/idcardAudit","e5110f684dd14b95a2a0a0f572475e39");
//		byte b[] = req.addTextPara("idcard",id)
//					  .addTextPara("name",name)
//					  .getAsByte();
//		//打印返回头
//		Map map = req.getRes_headMap();
//		Iterator it = map.keySet().iterator();
//		while (it.hasNext()) {
//			Object k = it.next();
//			System.out.println(k + "          " + map.get(k));
//		}
//		//打印返回体
//		String res = null;
//		try {
//			res = new String(b,"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//下面是使用阿里的fastjson包解析。如果不需要，请使用自己的解析包
//		com.alibaba.fastjson.JSONObject json= com.alibaba.fastjson.JSONObject.parseObject(res);
//		System.out.println(json.toString());
//		return json.toString();
//  }
  
//  public static String getCard(String idcard) {
//	    String host = "http://idinfo.market.alicloudapi.com";
//	    String path = "/getIDcard";
//	    String method = "GET";
//	    String appcode = "e5110f684dd14b95a2a0a0f572475e39";
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("ID", idcard);
//	    System.out.println(idcard.toString());
//
////	    try {
////	    	/**
////	    	* 重要提示如下:
////	    	* HttpUtils请从
////	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
////	    	* 下载
////	    	*
////	    	* 相应的依赖请参照
////	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
////	    	*/
////	    	HttpResponse response = (HttpResponse) HttpUtils.doGet(host, path, method, headers, querys);
////	    	System.out.println(EntityUtils.toString(((org.apache.http.HttpResponse) response).getEntity()));
////	    System.out.println(EntityUtils.toString(((org.apache.http.HttpResponse) response).getEntity()));
////	    	return response.toString();
////	    	//获取response的body
////	  
////	    } catch (Exception e) {
////	    	e.printStackTrace();
////	    }
////	    return null;
//	}

//  public static String getCard(String idcard, String name) {
//	   String host = "http://idcard.market.alicloudapi.com";
//	    String path = "/lianzhuo/idcard";
//	    String method = "GET";
//	    String appcode = "e5110f684dd14b95a2a0a0f572475e39";
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("cardno", idcard);
//	    querys.put("name", name);
//
//	    System.out.print(name + idcard);
//	    try {
//	    	/**
//	    	* 重要提示如下:
//	    	* HttpUtils请从
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//	    	* 下载
//	    	*
//	    	* 相应的依赖请参照
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//	    	*/
//	    	 System.out.print(name + idcard+"end1");
//	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//	    	//System.out.println(response.toString());
//	    	//获取response的body
//	    	 System.out.print(EntityUtils.toString(response.getEntity()));
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    }
//	    return null;
//	}
 
  
//  public static String getRelayCard(String idcard, String name) {
//	  String host = "http://1.api.apistore.cn";
//	    String path = "/idcard";
//	    String method = "GET";
//	    String appcode = "e5110f684dd14b95a2a0a0f572475e39";
//	    Map<String, String> headers = new HashMap<String, String>();
//	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//	    headers.put("Authorization", "APPCODE " + appcode);
//	    Map<String, String> querys = new HashMap<String, String>();
//	    querys.put("cardNo", idcard);
//	    querys.put("realName", name);
//
//
//	    try {
//	    	/**
//	    	* 重要提示如下:
//	    	* HttpUtils请从
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//	    	* 下载
//	    	*
//	    	* 相应的依赖请参照
//	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//	    	*/
//	    	HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
//	    	System.out.println(response.toString());
//	    	//获取response的body
//	    	 System.out.println(EntityUtils.toString(response.getEntity()));
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    }
//	    return null;
//	}
//
//
//  
  // 发送数据
  public static void sendData(String data,PrintWriter pw){
	  pw.write(data);
	  pw.flush();
	  pw.close();
  }
  
//  public static String [] alluserdata = {
//		   };
//  // 获的JSON对象
//  public static JSONObject getJsonObj(){
//	  return new JSONObject();
//  }
//  // 获的JSON数组
//  public static JSONArray getJSONArray(){
//	  return new JSONArray();
//  }
//  // 发送数据
//  public static void getDatas(String data,PrintWriter pw){
//	  pw.write(data);
//	  pw.flush();
//	  pw.close();
//  }
// 
//  // 产生随机数
  public static String getYZM(int number){
	  String randStr = "";
	  randStr += (int)(Math.random()*9+1);
	  for(int i = 0; i < number; i++){
          randStr += (int)(Math.random()*10);
	  }
      return randStr;
  }
//  
//  public static void sendMSGBYAlm(String param, String Phone){
//	  try {
//	        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI3vOaTlcUbNQQ", "pUwd0GaZIZKyo4cBXOWy7oMmrV9qdA");
//	         DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Sms",  "sms.aliyuncs.com");
//	        IAcsClient client = new DefaultAcsClient(profile);
//	        SingleSendSmsRequest request = new SingleSendSmsRequest();
//	            request.setSignName("黑色科技");//控制台创建的签名名称
//	             request.setTemplateCode("SMS_53070214");//控制台创建的模板CODE
//	            request.setParamString(param);//短信模板中的变量；数字需要转换为字符串；个人用户每个变量长度必须小于15个字符。"
//	            //request.setParamString("{}");
//	            request.setRecNum(Phone);//接收号码
//	            SingleSendSmsResponse httpResponse = client.getAcsResponse(request);
//	            System.out.print(httpResponse.getRequestId());
//	        } catch (ServerException e) {
//	            e.printStackTrace();
//	        }
//	        catch (ClientException e) {
//	            e.printStackTrace();
//	        }
//  }
//  
//  
//  //  发送邮件的方法
//  public static boolean SendEmail(String emailaddress,String emailtitle,String message,PrintWriter pw) throws MessagingException{
//	  boolean hassend = false;
//	  String qm ="xzntxMM323425_"; //您的QQ密码
//	  String tu = "qq.com"; //你邮箱的后缀域名
//	  Properties props=new Properties();
//      props.setProperty("www.anters.net","smtp."+tu);//发信的主机，这里我填写的是我们公司的主机！可以不用修改！
//      props.setProperty("mail.smtp.auth","true");
//      Session s = Session.getInstance(props);
//      s.setDebug(true);
//      MimeMessage msg = new MimeMessage(s);
//      //给消息对象设置发件人/收件人/主题/发信时间
//      InternetAddress from= new InternetAddress("892466942@qq.com"); //这里的115798090 改为您发信的QQ号
//      msg.setFrom(from);
//      InternetAddress to=new InternetAddress(emailaddress);
//      msg.setRecipient(Message.RecipientType.TO,to);
//      msg.setSubject(emailtitle);
//      msg.setSentDate(new Date());
//      //给消息对象设置内容
//      BodyPart mdp=new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
//      mdp.setContent(message,"text/html;charset=UTF-8");//给BodyPart对象设置内容和格式/编码方式
//      MimeMultipart mm = new MimeMultipart();//新建一个MimeMultipart对象用来存放BodyPart对
//      //象(事实上可以存放多个)
//      mm.addBodyPart(mdp);//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
//      msg.setContent(mm);//把mm作为消息对象的内容
//      msg.saveChanges();
//      Transport transport=s.getTransport("smtp");
//      transport.connect("smtp."+tu,"892466942",qm); //这里的115798090也要修改为您的QQ号码
//      try{
//    	  transport.sendMessage(msg,msg.getAllRecipients()); 
//          transport.close();
//          hassend = true;
//      }catch(SendFailedException ex){
//    	  JSONObject errorObj = new JSONObject();
//		  try {
//			errorObj.put(Param.errorKey, "验证码已经发到你的邮箱, 请注意查收");
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		  Param.sendData(errorObj.toString(), pw);
//		  hassend = false;
//      }
//      System.out.print("邮箱发送成功  ＝"+hassend);
//	  return hassend;
//  }
//  
//  // 使用阿里大鱼短信平台发送
//  public static JSONObject sendMSGBydy(String toPhone, String msg) throws ApiException{
////	                                                 http://gw.api.taobao.com/router/rest
//	  TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23406282", "261aac88b72a489a5f515dc6359e54f4");
//      AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//	  req.setExtend("123456");
//	  req.setSmsType("normal");
//	  req.setSmsFreeSignName("郭有超");
//	  req.setSmsParamString(  "{code:'"+msg+"'}"  );
//	  req.setRecNum( "15010215839" );
//	  req.setSmsTemplateCode( "SMS_12200925" );
//	  AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
//	  System.out.println(rsp.getBody());
//      
//	  JSONObject obj = null;
//		try {
//			System.out.println("发送短信返回数据 ＝ "+rsp.getBody());
//			obj = new JSONObject( rsp.getBody());
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    return obj; 
//  }
//  
//  
//  
//  // 用手机号发送短信
//  public static JSONObject sendMessageToPhoneNumber(String toPhone, String message){
//	  
//	  String httpUrl = "http://apis.baidu.com/kingtto_media/106sms/106sms";
//	  String httpArg = "mobile="+toPhone+"&content="+message+"&tag=2";
//	  String jsonResult = request(httpUrl, httpArg);
//	  
//	  JSONObject obj = null;
//	try {
//		obj = new JSONObject(jsonResult);
//	} catch (JSONException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	System.out.println(obj.toString());
//	  return obj;
//  }
//  
//  public static String request(String name,String cardno) {
//      BufferedReader reader = null;
//      String result = null;
//      StringBuffer sbf = new StringBuffer();
//      String httpUrl = "http://apis.baidu.com/apix/idauth/idauth" + "?" + "name="+name+"cardno="+cardno;
//
//      try {
//          URL url = new URL(httpUrl);
//          HttpURLConnection connection = (HttpURLConnection) url
//                  .openConnection();
//          connection.setRequestMethod("GET");
//          // 填入apikey到HTTP header
//          connection.setRequestProperty("apikey",  "586e025c76f3d64c1048d1fc642fdb83");
//          connection.connect();
//          InputStream is = connection.getInputStream();
//          reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//          String strRead = null;
//          while ((strRead = reader.readLine()) != null) {
//              sbf.append(strRead);
//              sbf.append("\r\n");
//          }
//          reader.close();
//          result = sbf.toString();
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//      return result;
//  }
//  
//  public static void Log(String key,String msg){
//	  System.out.println(key + " = " + msg);
//  }
//  public static void sendPhone(String Phone,String Name, String code){
//	  /**
//       * Step 1. 获取主题引用
//       */                                                                                           
//	  CloudAccount account = new CloudAccount("LTAI3vOaTlcUbNQQ", "pUwd0GaZIZKyo4cBXOWy7oMmrV9qdA", "https://1630782170870867.mns.cn-hangzhou.aliyuncs.com/");
//      MNSClient client = account.getMNSClient();
//      
//      CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");
//      
//     
//      /**
//       * Step 2. 设置SMS消息体（必须）
//       *
//       * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
//       */
//      RawTopicMessage msg = new RawTopicMessage();
//      msg.setMessageBody("sms-subscription");
//      /**
//       * Step 3. 生成SMS消息属性
//       */
//      MessageAttributes messageAttributes = new MessageAttributes();
//      BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
//      // 3.1 设置发送短信的签名（SMSSignName）
//      batchSmsAttributes.setFreeSignName("黑色科技");
//      
//      // 3.2 设置发送短信使用的模板（SMSTempateCode）
//      batchSmsAttributes.setTemplateCode("SMS_53070214");
//      // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
//      BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
//      smsReceiverParams.setParam("name", ""+Name.toString());
//      smsReceiverParams.setParam("code", ""+code.toString());
//      // 3.4 增加接收短信的号码
//      batchSmsAttributes.addSmsReceiver(""+Phone.toString(), smsReceiverParams);
////      batchSmsAttributes.addSmsReceiver("$YourReceiverPhoneNumber2", smsReceiverParams);
//      messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
//      try {
//          /**
//           * Step 4. 发布SMS消息
//           */
//          TopicMessage ret = topic.publishMessage(msg, messageAttributes);
//       
//          System.out.println("$MessageId: " + Phone + Name + code);
//          System.out.println("MessageMD5: " + msg.getMessageBody());
//      } catch (ServiceException se) {
//          System.out.println(se.getErrorCode() + se.getRequestId());
//          System.out.println(se.getMessage());
//          se.printStackTrace();
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//      client.close();
//  }
  
//  public static String sendPhoneDZMSG(String name, String phone ,String money, String method) throws ClientException, JSONException {
//	  System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//	  System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//	  //初始化ascClient需要的几个参数
//	  final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
//	  final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//	  //替换成你的AK
//	  final String accessKeyId = "LTAI3vOaTlcUbNQQ";//你的accessKeyId,参考本文档步骤2
//	  final String accessKeySecret = "pUwd0GaZIZKyo4cBXOWy7oMmrV9qdA";//你的accessKeySecret，参考本文档步骤2
//	  //初始化ascClient,暂时不支持多region（请勿修改）
//	  IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
//	  accessKeySecret);
//	  DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//	  IAcsClient acsClient = new DefaultAcsClient(profile);
//	   //组装请求对象
//	   SendSmsRequest request = new SendSmsRequest();
//	   //使用post提交
//	   request.setMethod(MethodType.POST);
//	   //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
//	   request.setPhoneNumbers(phone);
//	   //必填:短信签名-可在短信控制台中找到
//	   request.setSignName("黑色科技");
//	   //必填:短信模板-可在短信控制台中找到
//	   request.setTemplateCode("SMS_134324605");
//	   //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//	   //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//	   JSONObject  obj = new JSONObject();
//	   obj.put("name", name);
//	   obj.put("money", money);
//	   obj.put("method", method);
//	   
//	   request.setTemplateParam(obj.toString());
//	    
//	   //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
//	   //request.setSmsUpExtendCode("90997");
//	   //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//	   request.setOutId("heisekeji.cpm1");
//	  //请求失败这里会抛ClientException异常
//	  SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//	  if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//	  //请求成功
//		  System.out.print("phone1 + "+phone);
//		  return "1";
//	  }
//	  System.out.print("phone2 + "+phone+sendSmsResponse.getCode()+sendSmsResponse.getMessage());
//	  return "0";
//  }
  
//  public static String sendNewPhone(String name, String phone , String code) throws ClientException, JSONException {
//	  System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//	  System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//	  //初始化ascClient需要的几个参数
//	  final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
//	  final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
//	  //替换成你的AK
//	  final String accessKeyId = "LTAI3vOaTlcUbNQQ";//你的accessKeyId,参考本文档步骤2
//	  final String accessKeySecret = "pUwd0GaZIZKyo4cBXOWy7oMmrV9qdA";//你的accessKeySecret，参考本文档步骤2
//	  //初始化ascClient,暂时不支持多region（请勿修改）
//	  IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
//	  accessKeySecret);
//	  DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//	  IAcsClient acsClient = new DefaultAcsClient(profile);
//	   //组装请求对象
//	   SendSmsRequest request = new SendSmsRequest();
//	   //使用post提交
//	   request.setMethod(MethodType.POST);
//	   //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
//	   request.setPhoneNumbers(phone);
//	   //必填:短信签名-可在短信控制台中找到
//	   request.setSignName("黑色科技");
//	   //必填:短信模板-可在短信控制台中找到
//	   request.setTemplateCode("SMS_53070214");
//	   //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//	   //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
//	   JSONObject  obj = new JSONObject();
//	   obj.put("name", name);
//	   obj.put("code", code);
//	   request.setTemplateParam(obj.toString());
//	    
//	   //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
//	   //request.setSmsUpExtendCode("90997");
//	   //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//	   request.setOutId("heisekeji.cpm");
//	  //请求失败这里会抛ClientException异常
//	  SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//	  if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//	  //请求成功
//		  return "1";
//	  }
//	  return "0";
//  }
//  
//  
//
//  /**
//   * @param urlAll
//   *            :请求接口
//   * @param httpArg
//   *            :参数
//   * @return 返回结果
//   */
//  public static String veriIdCard(String httpUrl, String httpArg) {
//      BufferedReader reader = null;
//      String result = null;
//      StringBuffer sbf = new StringBuffer();
//      httpUrl = httpUrl + "?" + httpArg;
//
//      try {
//          URL url = new URL(httpUrl);
//          HttpURLConnection connection = (HttpURLConnection) url
//                  .openConnection();
//          connection.setRequestMethod("GET");
//          // 填入apikey到HTTP header
//          connection.setRequestProperty("apikey",  "586e025c76f3d64c1048d1fc642fdb83");
//          connection.connect();
//          InputStream is = connection.getInputStream();
//          reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//          String strRead = null;
//          while ((strRead = reader.readLine()) != null) {
//              sbf.append(strRead);
//              sbf.append("\r\n");
//          }
//          reader.close();
//          result = sbf.toString();
//      } catch (Exception e) {
//          e.printStackTrace();
//      }
//      return result;
//  }
   
  public static boolean idVail(String vaiid) {
	     String certificateNo = vaiid;//身份证号码
         if(certificateNo.length() < 18 || certificateNo.length() > 18){
            return false;
         }else if(certificateNo.length() == 18){
            String address = certificateNo.substring(0,6);//6位，地区代码
            String birthday = certificateNo.substring(6,14);//8位，出生日期
            String sequenceCode =  certificateNo.substring(14,17);//3位，顺序码：奇为男，偶为女
            String checkCode =  certificateNo.substring(17);//1位，校验码：检验位
            System.out.println("身份证号码:"+certificateNo+"、地区代码:"+address+"、出生日期:"+birthday+"、顺序码:"+sequenceCode+"、校验码:"+checkCode+"\n");            
            String [] provinceArray = {"11:北京","12:天津","13:河北","14:山西","15:内蒙古","21:辽宁","22:吉林","23:黑龙江","31:上海","32:江苏","33:浙江","34:安徽", "35:福建","36:江西","37:山东","41:河南","42:湖北 ","43:湖南","44:广东","45:广西","46:海南","50:重庆","51:四川","52:贵州","53:云南","54:西藏 ","61:陕西","62:甘肃","63:青海","64:宁夏", "65:新疆","71:台湾","81:香港","82:澳门","91:国外"};
            boolean valideAddress =false;
            for(int i = 0; i < provinceArray.length; i++) {
               String provinceKey =provinceArray[i].split(":")[0];
               if(provinceKey.equals(address.substring(0,2))){
                 valideAddress = true;
               }
            }
            if (valideAddress) {
               String year =  birthday.substring(0,4);  
               String month =birthday.substring(4,6);  
               String day =birthday.substring(6);
               Date tempDate = new Date(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day));
               if((tempDate.getYear()!=Integer.parseInt(year)|| tempDate.getMonth()!=Integer.parseInt(month)-1 || tempDate.getDate()!=Integer.parseInt(day))){//避免千年虫问题
                  return false;
               }else{
                  int [] weightedFactors = { 7, 9, 10, 5, 8, 4, 2,1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };//加权因子  
                  int [] valideCode = { 1, 0, 10, 9, 8, 7, 6, 5,4, 3, 2 };//身份证验证位值，其中10代表X
                  int sum = 0;//声明加权求和变量
                  String []certificateNoArray =new String[certificateNo.length()];
                  for(int i = 0; i < certificateNo.length(); i++) {
                     certificateNoArray[i] =String.valueOf(certificateNo.charAt(i));
                  }                     
                  if("x".equals(certificateNoArray[17].toLowerCase())) {
                    certificateNoArray[17] ="10";//将最后位为x的验证码替换为10 
                  }
                  for(int i = 0; i < 17; i++) {
                    sum += weightedFactors[i]* Integer.parseInt(certificateNoArray[i]);//加权求和  
                  }
                  int valCodePosition = sum % 11;//得到验证码所在位置
                  if(Integer.parseInt(certificateNoArray[17])== valideCode[valCodePosition]) {
                    return true;
                  }else {
                    return false;
                  }
              }
            }
          }
          return false;
         }
}
