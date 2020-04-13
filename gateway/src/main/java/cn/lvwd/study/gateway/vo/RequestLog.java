package cn.lvwd.study.gateway.vo;

import lombok.Data;

@Data
public class RequestLog {

    private String method;

    private String url;

    private String requestBody;

    private String mediaType;

    @Override
    public String toString() {
        return "请求：" + url + ",method：" + method + ",url：" + url + ",requestBody：" + requestBody + "，mediaType：" + mediaType;
    }
}
