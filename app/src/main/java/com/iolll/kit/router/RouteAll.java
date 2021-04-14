package com.iolll.kit.router;

/**
 * Created by LiuBo on 2019-05-09.
 */
public enum RouteAll {
    ROUTER_MAIN("/route/main","路由首页"),
    ROUTER_NICEBACK("/route/niceback","路由返回页"),
    RECYCLERVIEW_ONTOP("/recyclerview/ontop","RV分组吸顶"),
    ROUTER_FIiLTER_BAR("/route/filter","筛选Bar页面"),
    ROUTER_NICE_ANIM("/route/niceAnim","View转场动画"),
    ROUTER_TAG("/route/tag","TagView"),
    ;
    private String uri;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    RouteAll(String path, String name) {
        uri = path;this.name = name;
    }}
