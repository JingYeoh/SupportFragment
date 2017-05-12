# SupportFragment
## 简介
  这是一个Fragment封装库，解决Fragment在使用过程中遇到的常见问题，并封装了Fragment和Activity的基类，并针对Fragment常见使用场景封装了一些常用操作。
帮助项目支持单Activity+多Fragment或者多Activity+多Fragment架构。

## Demo演示
  Demo为仿照探探做的App，整体架构采用了单Activity+多Fragment，使用MVP+MVVM框架，采用ARouter路由框架进行界面跳转，使用EventBus作为消息总线通知框架，
后续会推出该demo相关的wiki。  
<img src="/gif/auth.gif" width="280px"/>

## 最新版本
模块|supportfragment
---|---
最新版本|![Download](https://api.bintray.com/packages/jkb/maven/supportfragment/images/download.svg)

## 集成
#### Maven集成
```xml
<dependency>
  <groupId>com.justkiddingbaby</groupId>
  <artifactId>supportfragment</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```
#### JCenter集成
第一步 在项目build.gradle中添加
```gradle
repositories {
    jcenter()
}
```
第一步 在module的build.gradle中添加
```gradle
compile 'com.justkiddingbaby:supportfragment:最新版本'
```
## 用法
#### 为Activity添加支持
##### TestAvtivity.java
```java
public class TestActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public int getFragmentContentId() {
        return R.id.main_content;
    }
}
```
##### main_content.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/mainFrameContent"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```
#### 为Fragment添加支持
##### TestFragment.java
```java
public class TestFragment extends SupportFragment {

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        return fragment;
    }
}
```
Fragment中使用时和正常使用Fragment时候一样，只是父类改为了SupportFragment
## 发布历史
#### v1.0.2(2017/5/8)
1、修复Support方法中事物保存机制，使用队列对SupportTransaction进行存储及恢复。
#### v1.0.1(2017/5/4)
1、修改Support框架中的showFragment场景，支持added过的Fragment进行showFragment方法的使用。
#### v1.0(2017/5/3)
1、SupportFragment框架分布，封装各种Fragment场景。  
2、封装框架使用场景Demo，仿探探App。