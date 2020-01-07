package com.lsg.wechatPay;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lsg.utils.wechat.HttpUtils;
import com.lsg.utils.wechat.PayUtil;
import com.lsg.utils.wechat.RSAUtils;
import com.lsg.utils.wechat.WxBase64;
import com.lsg.utils.wechat.XmlUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

import javax.crypto.Cipher;
import javax.net.ssl.SSLContext;
import javax.xml.parsers.DocumentBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;



public class WeiXinUtil {


	   //微信提现到个人银行卡
	   public static Map<String, String> wxCashBank(String applyId, String cardNo, String accountName, String bankCode, BigDecimal amout, String mchId, String mchKey) throws Exception{
		  
	      Map<String, String> resultMap = new HashMap<String, String>();
	      //String pfxPath=PropertyConfigurerUtil.getContextProperty("wx.zs")+mchId+".p12";;

	      HashMap<String, String> map = new HashMap<String, String>();

	      //1900008751
	      map.put("mch_id", "1900008971");
	      map.put("nonce_str", PayUtil.getNonceStr()); //随机字符串
		  //parm.put("partner_trade_no", PayUtil.getTransferNo()); //商户订单号
	      //注意 这里的  pksc8_public.pem  是上一步获取微信支付公钥后经openssl 转化成PKCS8格式的公钥
          //String keyfile = "d:/zd/pksc8_public.pem"; //读取PKCS8密钥文件
	     // PublicKey pub=getPubKey(keyfile,"RSA");
	     // String rsa ="RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";
	     // byte[] estr=encrypt("12312312".getBytes("UTF-8"),pub,2048, 11,rsa);   //对银行账号进行加密
	     // String encBankAcctNo =WxBase64.encode(estr);//并转为base64格式----  调用付款需要传的 银行卡号

//	      byte[] name=encrypt("asdasdasd".getBytes("UTF-8"),pub,2048, 11,rsa);   //对银行账号进行加密
//	      String encBankAcctName =WxBase64.encode(name);//并转为base64格式--调用付款需要传的 用户名
//	      map.put("encBankAcctNo", encBankAcctNo);
//	      map.put("encBankAcctName", encBankAcctName);
	     // map.put("amount", "1");
	      //map.put("desc", "转账");
	      //3ACA91426F056322E053645AA8C0CC12 商户KEY，获取签名
	      map.put("sign", PayUtil.getSign(map, "1e0860380afc96fd4cd07bbf1bc68e29"));
	     // map.put("sign", "3ACA91426F056322E053645AA8C0CC12");
	      map.put("amount", "1");
	      String content =  XmlUtil.xmlFormat(map, false);
	      
	      String url="https://api.mch.weixin.qq.com/sandboxnew/mmpaysptrans/pay_bank";
	      //String url="https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
	      
	      String restxml = HttpUtils.posts(url, content);
			
			
//	      BasicHttpClientConnectionManager connManager;
//	      connManager =
//
//	    		  new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", SSLConnectionSocketFactory.getSocketFactory())
//                      .build(), null, null, null);
//	      CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(connManager).build();
//	      
//	   
//	      
//	      HttpPost httpost=new HttpPost(url);
//	      httpost.setEntity(new StringEntity(content, "UTF-8"));
//	      
//	      HttpResponse weixinResponse = httpclient.execute(httpost);
//	      String jsonStr = EntityUtils.toString(weixinResponse.getEntity(), "UTF-8");
//	      KeyStore keyStore  = KeyStore.getInstance("PKCS12");
//	      FileInputStream instream = new FileInputStream(new File(pfxPath));
//	      try {
//	         keyStore.load(instream, mchId.toCharArray());
//	      } finally {
//	         instream.close();
//	      }
	      //SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mchId.toCharArray()).build();
	     // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	     // CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf) .build();
	     
	    
	      Map<String, String> cbMap = XmlUtil.xmlParse(restxml);
	      
	      if (cbMap.get("return_code").equals("SUCCESS") && cbMap.get("result_code").equals("SUCCESS")) {
	         resultMap.put("result_code","SUCCESS");
	         resultMap.put("payment_no",cbMap.get("payment_no")+"");
	      }else{
	         resultMap.put("result_code","FAIL");
	         resultMap.put("errorMsg",cbMap.get("return_msg")+"："+cbMap.get("err_code")+"："+cbMap.get("err_code_des"));
	       //  logger.error("微信提现银行卡错误："+cbMap.get("return_msg")+"："+cbMap.get("err_code")+"："+cbMap.get("err_code_des"));
	      }
	      return resultMap;
	   }

	   //微信签名（需要用户 商户后台 key)
	   public static String getSign(Map<String, Object> map){
	      ArrayList<String> list = new ArrayList<String>();
	      for(Map.Entry<String,Object> entry:map.entrySet()){
	         if(entry.getValue()!="" && !entry.toString().equals("return_code") && !entry.toString().equals("return_msg") && !entry.toString().equals("result_code")){
	            System.out.println();
	            list.add(entry.getKey() + "=" + entry.getValue() + "&");
	         }
	      }
	      int size = list.size();
	      String [] arrayToSort = list.toArray(new String[size]);
	      Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
	      StringBuilder sb = new StringBuilder();
	      for(int i = 0; i < size; i ++) {
	         sb.append(arrayToSort[i]);
	      }

	      String result = sb.toString();
	      result += "key=商户平台，账户中心,API安全 API密钥";
	      //logger.info(result);
	      //Util.log("Sign Before MD5:" + result);
	      try{
	         MessageDigest e = MessageDigest.getInstance("MD5");
	        // result = byteArrayToHexString(e.digest(result.getBytes("utf-8"))).toUpperCase();
	      }catch (Exception e){
	         e.printStackTrace();
	      }
	      //result = MD5.MD5Encode(result).toUpperCase();
	      //Util.log("Sign Result:" + result);
	      return result;
	   }

	   public static String getRandomStringByLength(int len){
	      String base = "abcdefghijklmnopqrstuvwxyz0123456789";
	      Random random = new Random();
	      StringBuffer sb = new StringBuffer();
	      for (int i = 0; i < len; i++) {
	         int number = random.nextInt(base.length());
	         sb.append(base.charAt(number));
	      }
	      return sb.toString();
	   }
	   
	   public  static String getXMLStringForObj(Object obj) {
	        //解决XStream对出现双下划线的bug
	        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	        xStreamForRequestPostData.alias("xml", obj.getClass());
	        xStreamForRequestPostData.processAnnotations(obj.getClass());
	        //将要提交给API的数据对象转换成XML格式数据Post给API
	        return xStreamForRequestPostData.toXML(obj);
	    }
	   
	   public static String generateSignature(final Map<String, String> data, String key, String signType) throws Exception {
	        Set<String> keySet = data.keySet();
	        String[] keyArray = keySet.toArray(new String[keySet.size()]);
	        Arrays.sort(keyArray);
	        StringBuilder sb = new StringBuilder();
	        for (String k : keyArray) {
	            if (k.equals("sign")) {
	                continue;
	            }
	            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
	                sb.append(k).append("=").append(data.get(k).trim()).append("&");
	        }
	        sb.append("key=").append(key);
	        if ("MD5".equals(signType)) {
	            return MD5(sb.toString()).toUpperCase();
	        } else if ("HMACSHA256".equals(signType)) {
	           // return HMACSHA256(sb.toString(), key);
	        } else {
	            throw new Exception(String.format("Invalid sign_type: %s", signType));
	        }
			return signType;
	    }
	   
	   
	   public static String MD5(String data) throws Exception {
	        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(data.getBytes("UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        for (byte item : array) {
	            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
	        }
	        return sb.toString().toUpperCase();
	   }
	   
	   public static byte[] encrypt(byte[] plainBytes, PublicKey publicKey, int keyLength, int reserveSize, String cipherAlgorithm) throws Exception {
	        int keyByteSize = keyLength / 8;
	        int encryptBlockSize = keyByteSize - reserveSize;
	        int nBlock = plainBytes.length / encryptBlockSize;
	        if ((plainBytes.length % encryptBlockSize) != 0) {
	            nBlock += 1;
	        }
	        ByteArrayOutputStream outbuf = null;
	        try {
	            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
	            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

	            outbuf = new ByteArrayOutputStream(nBlock * keyByteSize);
	            for (int offset = 0; offset < plainBytes.length; offset += encryptBlockSize) {
	                int inputLen = plainBytes.length - offset;
	                if (inputLen > encryptBlockSize) {
	                    inputLen = encryptBlockSize;
	                }
	                byte[] encryptedBlock = cipher.doFinal(plainBytes, offset, inputLen);
	                outbuf.write(encryptedBlock);
	            }
	            outbuf.flush();
	            return outbuf.toByteArray();
	        } catch (Exception e) {
	            throw new Exception("ENCRYPT ERROR:", e);
	        } finally {
	            try{
	                if(outbuf != null){
	                    outbuf.close();
	                }
	            }catch (Exception e){
	                outbuf = null;
	                throw new Exception("CLOSE ByteArrayOutputStream ERROR:", e);
	            }
	        }
	    }
	   
	   public static PublicKey getPubKey(String publicKeyPath,String keyAlgorithm){
	        PublicKey publicKey = null;
	        InputStream inputStream = null;
	        try
	        {
	            inputStream = new FileInputStream(publicKeyPath);
	            publicKey = getPublicKey(inputStream,keyAlgorithm);
	        } catch (Exception e) {
	            e.printStackTrace();//EAD PUBLIC KEY ERROR
	        } finally {
	            if (inputStream != null){
	                try {
	                    inputStream.close();
	                }catch (Exception e){
	                    e.printStackTrace();
	                }
	            }
	        }
	        return publicKey;
	    }
	    public static PublicKey getPublicKey(InputStream inputStream, String keyAlgorithm) throws Exception {
	        try
	        {

	            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	            StringBuilder sb = new StringBuilder();
	            String readLine = null;
	            while ((readLine = br.readLine()) != null) {
	                if (readLine.charAt(0) == '-') {
	                    continue;
	                } else {
	                    sb.append(readLine);
	                    sb.append('\r');
	                }
	            }
	            X509EncodedKeySpec pubX509 = new X509EncodedKeySpec(decodeBase64(sb.toString()));
	            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
	            //下行出错  java.security.spec.InvalidKeySpecException: java.security.InvalidKeyException: IOException: DerInputStream.getLength(): lengthTag=127, too big.
	            PublicKey publicKey = keyFactory.generatePublic(pubX509);
	            return publicKey;
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new Exception("READ PUBLIC KEY ERROR:", e);
	        } finally {
	            try {
	                if (inputStream != null) {
	                    inputStream.close();
	                }
	            } catch (IOException e) {
	                inputStream = null;
	                throw new Exception("INPUT STREAM CLOSE ERROR:", e);
	            }
	        }
	    }
	    
	    /***
	     * decode by Base64
	     */
	    public static byte[] decodeBase64(String input) throws Exception{
	        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
	        Method mainMethod= clazz.getMethod("decode", String.class);
	        mainMethod.setAccessible(true);
	        Object retObj=mainMethod.invoke(null, input);
	        return (byte[])retObj;
	    }
}
