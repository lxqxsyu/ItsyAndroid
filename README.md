## 项目介绍

项目名称《旅途摄影》（后改名“最美摄影”）是我17年的时候业余做的一个小APP,坦白的说这个项目写的太糟糕了，现在看起来代码结构和各种需要重构的地方，后面如果有足够的时间也许会重构一下，希望这个项目对你有所帮助，可以作为你的一个练习项目。

如果你对这个项目感兴趣，并愿意花时间来完善或者重构它，非常欢迎您的参与。

## 逻辑介绍

这个项目的初衷是当时自己想搞一个可以供大家使用的图片分享APP,可以上传自己的摄影作品并且和粉丝互动，其次里面还添加了地图功能来展示附近的摄影爱好者和优秀作品。


工程Module简要介绍：

| Module | 介绍 | 项目地址 |
| :---- | :---- | :---- |
| app  | 可运行app |  /  |
| commonutils | 基础工具类，类似的工具类还很多 | / |
| takephoto_library | 一个开源的图片裁切压缩上传插件 | https://github.com/crazycodeboy/TakePhoto |
| recyclerview_gallary | 一个开源的RecyclerView实现Card Gallery效果，替代ViewPager方案 | https://github.com/huazhiyuan2008/RecyclerViewCardGallery |
| HeaderAndFooterRecyclerView | 一个开源的支持Header和Footer的RecyclerView |  https://github.com/cundong/HeaderAndFooterRecyclerView |
| colorful | 基于Theme的Android动态换肤开源库 | https://github.com/hehonghui/Colorful |
| card_slide | 模仿探探首页的卡片滑动效果 | https://github.com/xmuSistone/CardSlidePanel |
| audio_recorder | Android的精美录音机。它支持48kHz的WAV格式 | https://github.com/adrielcafe/AndroidAudioRecorder |


使用Retrofit和Okhttp网络请求，`\manage\http\` 目录下。

```java
private RetrofitManage(){
    mOkHttpClient = OkHttpManager.getInstance().getOkHttpClient();
    mRetrofit = new Retrofit.Builder()
            .baseUrl(DEFAULT_SERVER_HTTP + SERVER_PATH)
            .client(mOkHttpClient)
            .addConverterFactory(ToStringConverterFactory.create())
            .addConverterFactory(ToGsonConverterFactory.create())
            //.addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
}
```

计划使用MVP结构，目前该工程只是实现了View层，所以核心实现代码均在`\view\`目录下。

```
view目录
├─adapter  #适配器
│      AuthorListAdapter.java
│      BaseUseListAdapter.java
│      CollectionCardAdapter.java
│      CommentListAdapter.java
│      ContestListAdapter.java
│      PhotoCategoryAdapter.java
│      PhotoCollectionAdapter.java
│      PhotoDynamicAdapter.java
│      PhotoExifAdapter.java
│      WaterFallAdapter.java
│
├─base   #基础类
│      BaseActivity.java
│      BaseFragment.java
│      BaseView.java
│
├─fragment 
│      BaseMainFragment.java
│      MainFragment1.java
│      MainFragment2.java
│      MainFragment3.java
│      MainFragment4.java
│      RankFragment1.java
│      RankFragment2.java
│      RankFragment3.java
│      RankFragment4.java
│
├─main  #主界面和公共Activity
│      DebugActivity.java
│      GameActiveActivity.java
│      JudgePhotoActivity.java
│      MainActivity.java
│      PKActivity.java
│      SplashActivity.java
│      WeekCoverActivity.java
│
├─personal #我的相关Activity
│      AddPhotoActivity.java
│      AddPhotoAddressActivity.java
│      AddSecretActivity.java
│      EditPhotoCollectionActivity.java
│      FeedbackActivity.java
│      FollowdMeActivity.java
│      LoginActivity.java
│      MyFavoriteActivity.java
│      MyFollowedActivity.java
│      PersonalCollectionActivity.java
│      PersonalPageActivity.java
│      PersonalPhotoActivity.java
│      PhotoExifActivity.java
│      SettingActivity.java
│      SetUserInfoActivity.java
│
└─square #广场，共享展示相关Activity
        CommentActivity.java
        PhotoCollectionActivity.java
        PhotoDetailActivity.java
        SearchActivity.java
```

## 部分界面

闪屏界面可根据实际情况更换本周封面图片

<img src="image/splash.jpg" width=500 alt="闪屏界面" title="闪屏界面" />

----

首页可以左右滑动查看图片，并支持筛选和搜索

<img src="image/home.jpg" width=500 alt="首页" title="首页" />

----

动态可以更换列表展示样式，并安装附近、已关注、最新、最热、全部分类查看

<img src="image/dongtai.jpg" width=500 alt="动态" title="动态" />

----

榜单可以按照关注、赞赏、图集、摄影比赛来排列

<img src="image/bangdan.jpg" width=500 alt="榜单" title="榜单" />

----

上传作品、我的相关设置和一些其他个人相关信息

<img src="image/me.jpg" width=500 alt="我的" title="我的" />

