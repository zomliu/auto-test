# 通知 CP 发放商品（CP 方提供）

## 1) 接口说明

​	SDK 服务器在验证**订单支付成功**后，会通过 **CP 提供的 Http 接口**通知游戏发放商品道具，通知请求中会有订单的详细信息， **CP 方在验证无误后即可发放商品，并回复 ‘success’**。SDK 服务端收到回复后，会将订单状态由**支付成功更新为交易成功**。

* 接口不做平台区分，google、ios 或者其他平台共用一个接口。


* 请在确认订单信息无误后再发放商品。
* 收到 CP 方返回为 ‘**fail**’  或者**未收到返回**时，SDK 服务器会在接下来的 24 小时内每分钟通知 CP 方一次，CP 方请保证单一订单勿重复发放商品。
* CP 无论是以同步还是异步方式处理通知请求，务必保证对于回复了 ‘success’ 的订单，玩家尽量快的收到相应商品。

## 2)  接口要求
Http Method : **Post**

Request Params :


|    参数名称     | 参数类型 |                           参数描述                           | 是否必传 |
| :-------------: | :------: | :----------------------------------------------------------: | :------: |
|    order_id     |   Int   |              平台订单id，例 : 872282619197394944              |    Yes    |
|     app_id      |   Int   |                    平台 应用 id，例 : 1001                    |   Yes    |
|    app_channel    |  String  |                         平台渠道 id                         |   Yes    |
|       uid       |   Int   |       平台提供给 CP 方 uid ，一般是 8 位数值型，例 : 18734638       |   Yes    |
|   amt   |   Int   |               订单金额，**美分**为单位，例 : 0.99               |   Yes    |
|    goods_id     |  String  |        商品 id ，例 : com.kingsoftgame.xsjtest.iap.tier60        |   Yes    |
| third_order_id | String | CP 订单号 | Yes |
| pay_item |  String  |      CP 传递给 SDK 的透传字段，一般用于 CP 方做订单验证      |   Yes    |
|     zone_id     |  String  |                          游戏提供的服务器 id + 角色 id，范例 : 1_10001                          |   Yes    |
|   order_type    |   Int   | 设备类型<br/>1 : android<br>2 : ios<br/>3 : h5Mobile<br/>999 : 暂未未识别的设备类型 |   Yes    |
|  pay_time  |  String  |                  支付时间，格式 : UNIX 时间戳                  |   Yes    |
|      sign       |  String  |          参数签名，建议 CP 方做签名验证保证接口安全          |   Yes    |

Response Example : 

````java
//成功返回
success
//失败
fail
````

额外说明：

关于 zone_id，pay_item 字段的数据流： CP 游戏客户端 -> 平台SDK -> 平台服务器 -> CP服务器。平台对这 2 个数据只用于存储订单信息，不做额外处理。CP 方可通过这 2 个透传字段做订单信息验证，比如 pay_item 存放游戏验证字符串或者其他信息；

zone_id  ，CP 方请传入正确的角色 id 和 服务器 id，便于后续的数据分析。

------

# 平台登录身份信息验证 （SG 平台提供）

## 1) 接口说明：

CP 端某些重要操作需要对用户的平台登录身份状态进行验证，可通过此接口进行验证。

## 2) 接口要求：

URL : /v2/uout/cp/token/check

Method : **Post**

Request Param :


|  参数名称  | 参数类型 |                          描述                           | 是否必须 |
| :--------: | :------: | :-----------------------------------------------------: | :------: |
|   app_id   |   Int    |                  平台 应用 id，例:1001                  |   Yes    |
|    uid     |  Int  | 平台提供给CP方 uid ，一般是 8 位字符串型，例 : 18734638 |   Yes    |
| channel_id |   Int    |                     渠道 ID                     |   Yes    |
|   token    |  String  | 平台用户 token ，在 SDK 客户端登录后会回传给游戏客户端  |   Yes    |
|    sign    |  String  |     参数签名，签名规则详见 “ SGSDK - Sign签名.pdf ”     |   Yes    |

http response example :

````json
//通过验证,code = 0 时通过验证
{"code":0,"reason":"success"} 
//未通过验证
{"code":xxx,"reason":"xxxx"} 
````

------

# 用户是否绑定手机号校验 （SG 平台提供）

## 1) 接口说明：

 CP 方需要校验用户是否绑定手机号信息，可通过此接口进行验证。

## 2) 接口要求：

URL : /v2/uout/cp/checkUserPhoneBindState

Method : **Post**

Request Param :

|  参数名称  | 参数类型 |                        描述                        | 是否必须 |
| :--------: | :------: | :------------------------------------------------: | :------: |
|   app_id   |   Int    |                  应用 id，例:1001                  |   Yes    |
| channel_id |   Int    |                       渠道id                       |   Yes    |
|    uid     |   Int    | 平台提供给CP方 uid ,一般是8位字符串型，例:18734638 |   Yes    |
|    sign    |  String  |                      参数签名                      |   Yes    |

Response Example :

```json
//用户已绑定手机号
{
	"code": 2031,
	"reason": "already bind phone number"
}
//用户未绑定手机号
{
	"code": 0,
	"reason": "success"
}
```

------

# 查询订单当前支付状态接口 （SG 平台提供）

## 1) 接口说明：

CP 获取平台订单当前支付状态接口。

## 2) 接口要求：

URL : /v2/pout/cp/selectOrderStatus

Http Method : **Post**

Http Param : 

|   参数名称   | 参数类型 |                     描述                     | 是否必须 |
| :----------: | :------: | :------------------------------------------: | :------: |
|     app_id     |   Int    |               应用 id，例:1001               |   Yes    |
|  app_channel   |   Int    |                   渠道 id                   |   Yes    |
|      uid       |   Int    | 平台提供给 CP 方 uid ,一般是8位字符串型，例:18734638 |   Yes    |
| third_order_id |  String  |                 CP 方支付订单号               |   Yes    |
|      sign      |  String  |                   参数签名                   |   Yes    |

Response Example :

```json
//正确返回结果
{
	"code": 0,
	"result": {
		"order_status": 100
        /*
         * 订单状态说明:
         * 0：等待支付
         * 50：支付成功
         * 100：交易完成
         * -50：支付失败
         * -100：取消订单
         */
	}
}
```

------