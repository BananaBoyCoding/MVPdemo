package com.wyx.mvp.demo;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * author   : BananaBoy
 * edition  : 1.0
 * time     : 2019/5/24
 * describe :
 */

public class Certificate {

    //连接时长，单位，毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位，毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    /*//设置缓存
    File cacheFile = new File(App.getmContext().getCacheDir(), "cache");
    //容量——100M
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);*/

    //从server.crt中读取出的字符串
    String CER_CLIENT = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDXTCCAkUCCQDNhr7+xMtU3jANBgkqhkiG9w0BAQUFADBoMQswCQYDVQQGEwJD\n" +
            "TjELMAkGA1UECAwCeDExCzAJBgNVBAcMAngyMQswCQYDVQQKDAJ4MzELMAkGA1UE\n" +
            "CwwCeDUxCzAJBgNVBAMMAmJ3MRgwFgYJKoZIhvcNAQkBFgkxQDE2My5jb20wHhcN\n" +
            "MTgwOTE3MTEyNjI2WhcNMjgwOTE0MTEyNjI2WjB5MQswCQYDVQQGEwJDTjELMAkG\n" +
            "A1UECAwCeDExCzAJBgNVBAcMAngyMQ4wDAYDVQQKDAViYXdlaTEPMA0GA1UECwwG\n" +
            "YmF3ZWkyMQswCQYDVQQDDAJidzEiMCAGCSqGSIb3DQEJARYTMTg2MDAxNTE1NjhA\n" +
            "MTYzLmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMRn5BnuG5Qm\n" +
            "GBJV+aBdVpCPkXNs7sCZPpKT6K6gi1t2L88DOiZqsIi+06KsN55w/51YeuAHYq9w\n" +
            "QFx9X34eFB/n//SKA/qkWznxZdtsAJUD3hkKkR3jhj+JP1EZWxwgIP5Dp1RyuBxH\n" +
            "gEoe7UmK9o/V2hJ3HTAYF20vQquFltucl5svnmtQvF4aofhFQ3gqXYvXD6pxcIuI\n" +
            "UOePK49hnlxz7v5t5s/0VXHHz+5THsEg14oW+kAPFKVPS59tjQV7LzDMXjunEBzc\n" +
            "A/Jslafx32BF4Fy1aCbWCmIJSKou9MBnrP1MuheIpMO1qEMBXx/9MuLMFdnyj20N\n" +
            "9M+WlaMBiMUCAwEAATANBgkqhkiG9w0BAQUFAAOCAQEAJf/W2zTuf9D36js7766T\n" +
            "xpfWCVy0POqkdXNKvPThd/U6Qwi2QXc0CmNvr02lfVRu11cX4inR9RiJUXWoeG7J\n" +
            "DDWBSBPKTpeF8+k2w+DjDAkE3mj3iCQdeydkhCUYquSxtFNC6mFZ9zrkMs7sGuBc\n" +
            "GoDnueL8B2IiNfLtA3vUzvAkqh9b7rOBk1VXem4JFnIoisFufdzH1RhNWxZTgtlG\n" +
            "+Po5VSrMpKgtPYLHFIprMIUwGfW7j36hhvnEArEVXLWjY3hhNvyJ4jBf0WRp44GA\n" +
            "8OZ1zDEyVxxtOAQXXlfiYusPuy5Wup2P7RYo17xMVoHeQg6yF+iszlBHoJ5250iv\n" +
            "kA==\n" +
            "-----END CERTIFICATE-----";

    class MyTrustManager implements X509TrustManager {

        X509Certificate cert;

        public MyTrustManager(X509Certificate cert) {
            this.cert = cert;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            //我们在客户端只做服务器端证书校验
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String s) throws CertificateException {
            //确认服务端证书和代码中hard code的CRT整数相同
            if (chain[0].equals(this.cert)) {
                Log.i("Jin", "checkServerTrusted Certificate from server is valid! ");
                return;
            }
            throw new CertificateException("checkServerTrusted No trusted server cert found!");
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    //访问测试
    public OkHttpClient testHttps() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLS");
        //信任证书管理,这个是由我们自己生成的，信任我们自己的服务器证书
        TrustManager myTrustManager = new MyTrustManager(readCert(CER_CLIENT));
        sc.init(null, new TrustManager[]{
                myTrustManager
        }, null);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                // .sslSocketFactory(sc.getSocketFactory(), (X509TrustManager) myTrustManager)
                //.hostnameVerifier(hostnameVerifier )
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                //配置此客户端，以便在遇到连接问题时重试或不重试。默认情况下，
                //*该客户端从以下问题中悄悄恢复
                .retryOnConnectionFailure(true)
                /*.addInterceptor(new OKLogInterceptor())
                .addInterceptor(new RewriteCacheControlInterceptor())
                .addInterceptor(new OKHeaderInterceptor())
                .addNetworkInterceptor(new RewriteCacheControlInterceptor())
                .cache(cache)*/
                .build();
    }

    //主机地址验证
    final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return s.equals("mobile.bwstudent.com");
        }
    };

    //根据字符串读取证书
    private static X509Certificate readCert(String cer) {
        if (cer == null || cer.trim().isEmpty())
            return null;
        InputStream caInput = new ByteArrayInputStream(cer.getBytes());
        X509Certificate cert = null;
        try {
            CertificateFactory instance = CertificateFactory.getInstance("X.509");
            cert = (X509Certificate) instance.generateCertificate(caInput);
        } catch (CertificateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (caInput != null) {
                    caInput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cert;
    }

}
