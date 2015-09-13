package com.flysoloing.common.extention;

import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.config.*;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <br>
 * User: flysoloing <br>
 * Date: 15-2-10 <br>
 * Time: 下午10:12
 */
public class MultiThreadHttpClient extends CloseableHttpClient implements DisposableBean, InitializingBean {

    private static int DEFAULT_CONNECTION_REQUEST_TIMEOUT = 1000;
    private static int DEFAULT_CONNECTION_TIMEOUT = 2000;
    private static int DEFAULT_SOCKET_TIMEOUT = 3000;

    private static int DEFAULT_MAX_TOTAL = 1000;
    private static int DEFAULT_MAX_PER_ROUTE = 20;

    //等待链接时间
    private int connectionRequestTimeout = DEFAULT_CONNECTION_REQUEST_TIMEOUT;
    //连接时间
    private int connectTimeout = DEFAULT_CONNECTION_TIMEOUT;
    //传输时间
    private int socketTimeout = DEFAULT_SOCKET_TIMEOUT;
    //最大连接数
    private int maxTotal = DEFAULT_MAX_TOTAL;
    //每个Route的最大连接数
    private int maxPerRoute = DEFAULT_MAX_PER_ROUTE;
    //主机列表
    private List<HttpHost> httpHosts;
    //另一种主机列表配置方式，遵循：协议,主机名,端口
    private List<String> httpHostConfs;

    private CloseableHttpClient httpClient;

    public MultiThreadHttpClient(){}

    @Override
    protected CloseableHttpResponse doExecute(HttpHost target, HttpRequest request, HttpContext context) throws IOException, ClientProtocolException {
        return httpClient.execute(target, request, context);
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

    @Override
    public HttpParams getParams() {
        return null;
    }

    @Override
    public ClientConnectionManager getConnectionManager() {
        return null;
    }

    @Override
    public void destroy() throws Exception {
        if (httpClient != null) {
            httpClient.close();
            httpClient = null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (httpClient == null) {

            SSLContext sslContext = SSLContexts.createSystemDefault();
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();

            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslContext, hostnameVerifier))
                    .build();

            SocketConfig socketConfig = SocketConfig.custom()
                    .setTcpNoDelay(true)
                    .build();

            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            connectionManager.setDefaultSocketConfig(socketConfig);

            MessageConstraints messageConstraints = MessageConstraints.custom()
                    .setMaxHeaderCount(200)
                    .setMaxLineLength(2000)
                    .build();

            ConnectionConfig connectionConfig = ConnectionConfig.custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints)
                    .build();

            connectionManager.setDefaultConnectionConfig(connectionConfig);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setExpectContinueEnabled(true)
                    .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .build();

            connectionManager.setMaxTotal(maxTotal);
            connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);

            for (HttpHost httpHost : httpHosts) {
                connectionManager.setSocketConfig(httpHost, socketConfig);
                connectionManager.setConnectionConfig(httpHost, ConnectionConfig.DEFAULT);
                connectionManager.setMaxPerRoute(new HttpRoute(httpHost), maxPerRoute);
            }

            httpClient = HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig)
                    .build();
        }

    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public List<HttpHost> getHttpHosts() {
        return httpHosts;
    }

    public void setHttpHosts(List<HttpHost> httpHosts) {
        this.httpHosts = httpHosts;
    }

    public List<String> getHttpHostConfs() {
        return httpHostConfs;
    }

    public void setHttpHostConfs(List<String> httpHostConfs) {
        this.httpHostConfs = httpHostConfs;
        List<HttpHost> httpHostList = new ArrayList<HttpHost>();
        for (String conf : this.httpHostConfs) {
            String[] confs = conf.split(",");
            String scheme = confs[0];
            String hostname = confs[1];
            String port = confs[2];
            HttpHost httpHost = new HttpHost(hostname, Integer.valueOf(port), scheme);
            httpHostList.add(httpHost);
        }
        setHttpHosts(httpHostList);
    }
}
