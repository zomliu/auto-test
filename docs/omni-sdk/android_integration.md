OmniSDK Android 接入指南
====

<!-- TOC -->

- [简介](#简介)
- [SDK获取](#sdk获取)
- [对接须知](#对接须知)
- [集成开发配置](#集成开发配置)
    - [1. 拷贝Gradle文件和集成参数配置文件](#1-拷贝gradle文件和集成参数配置文件)
    - [2. 配置Gradle脚本](#2-配置gradle脚本)
    - [3. 初始化](#3-初始化)
        - [1. Application（必接）](#1-application必接)
        - [2. Activity生命周期方法接入 (必接)](#2-activity生命周期方法接入-必接)
        - [3. 渠道统计接口 (必接)](#3-渠道统计接口-必接)
            - [3.1 创建角色](#31-创建角色)
            - [3.2 角色升级](#32-角色升级)
            - [3.3 进入游戏](#33-进入游戏)
    - [5. API接口说明（可选功能）](#5-api接口说明可选功能)
        - [全部接口](#全部接口)
            - [1. 账号](#1-账号)
            - [2. 支付](#2-支付)
            - [3. 社交](#3-社交)
            - [4. 特定功能](#4-特定功能)
            - [5. 数据统计](#5-数据统计)
            - [6. 通用方法](#6-通用方法)
    - [6. 混淆配置](#6-混淆配置)
        - [OmniSDK 混淆配置](#omnisdk-混淆配置)
        - [第三方依赖库混淆配置说明](#第三方依赖库混淆配置说明)
        - [第三方依赖库混淆配置方法（建议）](#第三方依赖库混淆配置方法建议)
- [附录](#附录)
    - [版本最低兼容问题](#版本最低兼容问题)
    - [配置文件申请指南](#配置文件申请指南)
    - [SDK 常见状态码](#sdk-常见状态码)

<!-- /TOC -->

# 简介
金山世游 OmniSDK Android 是一个聚合类型SDK，提供了账号，支付，广告，事件统计等功能模块。游戏CP方完成一次对接后即可在各个应用商店平台和第三方发布渠道进行游戏发布。

# SDK获取
1. 请CP对接方联系我方游戏项目负责人获取最新版本SDK
2. [OmniSDK-Demo](https://github.com/kingsoftgames/omnisdk-android-demo)

# 版本记录
[版本记录](CHANGELOG.md)

# 对接须知
- 推荐使用Android Studio对接SDK
- OmniSDK最低兼容版本为 Android 5.0(API Level 21)，阅读[版本最低兼容问题](#版本最低兼容问题)。

# 集成开发配置
## 1. 拷贝Gradle文件和集成参数配置文件
* 将SDK ZIP解压后的 **kssyOmni.gradle** 和 **kssyOmniRoot.gradle** 文件拷贝到游戏自身应用模块根目录下。
* 将SDK ZIP解压后的 **project_config.json** 拷贝到游戏应用模块(app-level or libs-level)的 **/src/main/assets/shiyou/** 目录下
   
## 2. 配置Gradle脚本
1. 在游戏项目工程(root-level)根目录下的 ***build.gradle*** ，添加如下配置:
    ```groovy
   // 引入渠道仓库配置
    apply from: ("${rootProject.rootDir}/kssyOmniRoot.gradle")

    buildscript {
        ext.kotlin_version = "1.4.31"
        repositories {
            google()
            jcenter()
            // 金山世游仓库
            maven {
                url "https://maven.shiyou.kingsoft.com/repository/public/"
            }
        }
        dependencies {
            classpath "com.android.tools.build:gradle:${your_version}"
            // kotlin 环境
            classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
            // 编译插件
            classpath "com.kingsoft.shiyou.omnisdk.build:plugin:1.0.2"
        }
    }

    allprojects {
        repositories {
            google()
            jcenter()
        }
    }
    ```
2. 在游戏应用模块(app-level or libs-level)根目录下的 ***build.gradle*** ，添加如下配置:
    ```groovy
    apply from: ("${rootProject.rootDir}/kssyOmni.gradle")
    ```
  
3. `sync gradle`，即点击`Sync Now` 或 Android Studio 菜单栏的“大象”图标。依赖库同步成功后，即可往下集成开发。


## 3. 初始化
### 1. Application（必接）
* 若游戏应用无自定义的Application，则在游戏应用工程 **AndroidManifest.xml** 配置文件的  **&lt;application&gt;  tag** 中声明如下 ***android:name*** 的值:
    ```xml
    <application
        android:name="com.kingsoft.shiyou.omnisdk.project.OmniApplication"
        ...
    />
    ```

* 若游戏应用已经有自身定义的Application并继承自android.app.Application,则请将继承类改为 **com.kingsoft.shiyou.omnisdk.project.OmniApplication** 即可,如下所示:
    ```java
    import com.kingsoft.shiyou.omnisdk.project.OmniApplication;

    public class GameApplication extends OmniApplication {
        //...
    }   
    ```
   
* 若游戏应用已经有自身定义的Application并继承其他的android.app.Application子类，则需要在其自定义Application的相应方法中添加如下代码:
    ```java
    @Override
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(context); // 64k方法数
        // your code goes here
    }

    @Override
    public void onCreate() {
        super.onCreate();
        OmniSDK.getInstance().onApplicationCreate(this);
        // your code goes here
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        OmniSDK.getInstance().onApplicationLowMemory();
        // your code goes here
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        OmniSDK.getInstance().onApplicationTrimMemory();
        // your code goes here
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        OmniSDK.getInstance().onApplicationTerminate();
        // your code goes here
    }
    ```

### 2. Activity生命周期方法接入 (必接)
* 请在游戏自身Activity的相应生命周期方法中添加如下代码:
    ```java

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OmniSDK.getInstance().onCreate(this, savedInstanceState);
        // your code goes here
    }

    @Override
    public void onStart() {
        super.onStart();
        OmniSDK.getInstance().onStart(this);
        // your code goes here
    }

    @Override
    public void onRestart() {
        super.onRestart();
        OmniSDK.getInstance().onRestart(this);
        // your code goes here
    }

    @Override
    public void onResume() {
        super.onResume();
        OmniSDK.getInstance().onResume(this);
        // your code goes here
    }

    @Override
    public void onPause() {
        super.onPause();
        OmniSDK.getInstance().onPause(this);
        // your code goes here
    }

    @Override
    public void onStop() {
        super.onStop();
        OmniSDK.getInstance().onStop(this);
        // your code goes here
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OmniSDK.getInstance().onDestroy(this);
        // your code goes here
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        OmniSDK.getInstance().onNewIntent(this, intent);
        // your code goes here
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        OmniSDK.getInstance().onSaveInstanceState(this, outState);
        // your code goes here
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        OmniSDK.getInstance().onRestoreInstanceState(this, savedInstanceState);
        // your code goes here
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        OmniSDK.getInstance().onConfigurationChanged(this, newConfig);
        // your code goes here
    }

    @Override
    public void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        OmniSDK.getInstance().onActivityResult(
                this,
                requestCode,
                resultCode,
                data);
        // your code goes here
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        OmniSDK.getInstance().onRequestPermissionsResult(
                this, requestCode,
                permissions,
                grantResults);
        // your code goes here
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OmniSDK.getInstance().onBackPressed(this);
        // your code goes here
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (OmniSDK.getInstance().onKeyDown(this, keyCode, event)) {
            return true;
        }
        // your code goes here
        return super.onKeyDown(keyCode, event);
    }
    ```

### 3. 渠道统计接口 (必接)
> 1. **如果游戏没有角色这一特征，就不需接入；**
> 2. **目前海外渠道要求接入的有 vivo，应用宝。如果不发行这些渠道，可以不用接入。**

详情阅读 [数据统计接口](#5-数据统计)
##### 3.1 创建角色

**应用场景**

游戏在完成创建角色信息后，调用此接口传入角色信息。

**方法定义**

```java
onCreateRole(RoleInfo)
```

**参数说明**

详情阅读 [RoleInfo](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.core.entity/[android-jvm]-role-info/index.html)

- 关于 RoleInfo 的说明
    1. **所有必接字段必须进行接入，否则会导致统计不完全，部分渠道审核无法通过！**
    2. **请严格按照规定的字段长度进行设置，否则可能发生游戏服务器端长度不够问题。**


**代码示例**

```java
OmniSDK.getInstance().onCreateRole(roleInfo);
```

##### 3.2 角色升级

**应用场景**

角色等级接口，角色等级提升时调用。

**方法定义**

```java
onRoleLevelUp(RoleInfo)
```

**参数说明**

详情阅读 [RoleInfo](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.core.entity/[android-jvm]-role-info/index.html)

**代码示例**

```java
roleInfo.setRoleLevel("2");
OmniSDK.getInstance().onRoleLevelUp(roleInfo);
```



##### 3.3 进入游戏

**应用场景**

在角色信息都获取到以后，进行调用。

**方法定义**

```java
onEnterGame(RoleInfo)
```

**参数说明**

详情阅读 [RoleInfo](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.core.entity/[android-jvm]-role-info/index.html)

**代码示例**

```java
OmniSDK.getInstance().onEnterGame(roleInfo)
```

## 5. API接口说明（可选功能）
**注意:** 由于各个对接游戏需求不同，下面所有接口并不是都必须接入.请CP对接方务必先确定游戏对接需求然后集成所需接口API。
### 全部接口
详情阅读API接口文档-[`OmniSDK`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api/-omni-s-d-k/index.html)
#### 1. 账号
详情阅读API接口文档-[`IAccount`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api.interfaces/-i-account/index.html)

**重要提示**
>接入的账号类型包含游客类型，**绑定账号**接口为必接；
>游戏最好提供绑定按钮，让玩家可以主动绑定账号，防止帐号数据丢失。

#### 2. 支付
详情阅读API接口文档-[`IPay`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api.interfaces/-i-pay/index.html)

#### 3. 社交
详情阅读API接口文档-[`ISocial`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api.interfaces/-i-social/index.html)

#### 4. 特定功能
详情阅读API接口文档-[`IAction`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api.interfaces/-i-action/index.html)

#### 5. 数据统计
详情阅读API接口文档-[`IDataMonitor`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api.interfaces/-i-data-monitor/index.html)

#### 6. 通用方法
详情阅读API接口文档-[`IMethod`](./api/html/-omni-s-d-k/com.kingsoft.shiyou.omnisdk.api.interfaces/-i-method/index.html)

## 6. 混淆配置
### OmniSDK 混淆配置
OmniSDK 混淆配置集成在自身依赖包内，编译时自动配置。
  
### 第三方依赖库混淆配置说明
第三方依赖库混淆配置，防止配置冲突由游戏应用配置，目前用到的如下：
- Gson [必选](../proguard/gson-rules.pro)
  
### 第三方依赖库混淆配置方法（建议）
- 在游戏应用工程建立文件夹`proguard`。
- 将相应的第三方依赖库混淆配置各按独立文件存放，比如`gson-rules.pro`。
- 在游戏应用模块(app-level)根目录下的 ***build.gradle*** ，增加如下配置: 
    
    ```groovy
    android {
        buildTypes {
            release {
                // 增加独立文件混淆配置
                proguardFiles file("${rootProject.rootDir}/proguard").listFiles().toList().toArray()
            }
        }
    }
    ```
  
# 附录
## 版本最低兼容问题
SGSDK 目前还支持Android 4.4(API Level 19)，但是 OmniSDK 对 Android 5.0(API Level 21) 以下将不再支持。

原因有以下几个：
1. 海外，Google 统计  Android 5.0以下的占有率已经非常低，并且机型内存太低。
2. 国内重要渠道如小米、华为、应用宝等，都要求 API Level 24。
3. OmniSDK 使用的网络库OkHttp，现在是 Android 5.0以上的底层网络库；很多第三方库的新版本都在引用，而且他们积极更新。
如果不使用第三方SDK的高版本，比如 Facebook，有些功能可能会逐步无法使用，低版本的一些`API`被限制或废弃。
4. 重要的第三方SDK（比如Facebook）、渠道SDK，最新版本都做了一些最低版本兼容要求。
OmniSDK 现在使用的是他们的最新版本，如果降级去支付Android 5.0(API Level 21) 以下，会出现其他兼容性问题。
5. 审核问题，比如 TLS 1.2问题，GooglePlay 2020-12-16已经禁止上线；如果不随审核升级而升级相应SDK新版本，渠道审核可能就无法通过。


## 配置文件申请指南
- 与管理员、产品确认需要申请的参数，参考[配置文件申请指南](https://d7n9vj8ces.feishu.cn/docs/doccn5apz08CeDpLCDTtshsSKmd)，在后台填写参数。
- 完成后通知 Omni 出包验证，参数无误，会发送所有的文件。

## SDK 常见状态码
详情阅读 [状态码](status_code.md)