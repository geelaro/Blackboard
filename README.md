### Blackboard黑板报 ,一个信息聚合App
- V2.0版本重构中....

- [Google Play](https://play.google.com/store/apps/details?id=com.geelaro.blackboard) | [App下载地址](https://github.com/geelaro/Blackboard/releases).  

- 首页分为四大模块，包括：天气、新闻、电影和图片。而天气模块可展示未来7天天气预报；新闻包括最近热点新闻、NBA、汽车和笑话；电影包括豆瓣热映榜、TOP250.

- 首页界面风格采用Material Design设计规范，使用了TabLayout和ViewPager配合，ViewPager切换Fragment，整体界面模仿了网易新闻.

- 项目整体使用MVP结构，网络采用了OkHttp的缓存设置，实现无网络状态下浏览，其中天气板块数据采用ContentProvider + LoaderManager框架对其持久化.

- 抽屉导航栏采用的是DrawerLayout和NavigationView配合，使用的icon来自[Material Design Icon](https://material.io/icons/).

- 设计思路参考了开源Repo.

### Screenshot

<a href="screenshot/weather.png"><img src="screenshot/weather.png" width="30%"/></a> <a href="screenshot/news.png"><img src="screenshot/news.png" width="30%"/></a>

<a href="screenshot/images.png"><img src="screenshot/images.png" width="30%"/></a> <a href="screenshot/nav.png"><img src="screenshot/nav.png" width="30%"/></a>

<a href="screenshot/movies.png"><img src="screenshot/movies.png" width="30%"/></a> <a href="screenshot/settings.png"><img src="screenshot/settings.png" width="30%"/></a>


### 数据源
- 天气数据使用 [OpenWeatherMap](http://openweathermap.org/).
- 新闻数据来源于网易新闻.
- 电影数据来源[豆瓣API v2](https://developers.douban.com/wiki/?title=api_v2),归豆瓣所有.

### 感谢
- [Glide](https://github.com/bumptech/glide)
- [OkHttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)
- [PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)

### About Me
- Blog: [geelaro.github.com](http://geelaro.github.com)
- Email: [gmail](mailto:geelaro.li@gmail.com)

### License (MIT)

```
MTI LICENSE

Copyright (c) 2017 geelaro
```
