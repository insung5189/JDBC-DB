package org.example;

import java.util.Map;

public class Rq { // 입력값을 가공하려고 쓰는 클래스
    String url;
    Map<String, String> params;
    String urlPath;

    public Rq(String url) {
        this.url = url;
        params = Util.getUrlParamsFromUrl(this.url);
        urlPath = Util.getUrlPathFromUrl(this.url);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String geturlPath() {
        return urlPath;
    }

}
