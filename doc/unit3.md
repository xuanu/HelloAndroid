# 在build.gradle里可以定义变量值，在程序运行时，可以用于替换AndroidManifest.xml里的内容
> 主要用于一些自定义内容的替换
> 也可用以多渠道打包

1. 在app的build.gradle里的
```
android {
    。。。
    defaultConfig {
        ...//原本内容
        manifestPlaceholders = [
                custom_key_1: "key1",
                custom_key_2: 2
        ]
    }
}
```    
2. 在AndroidManifest.xml这样使用   
```
    <application
    <meta-data
            android:name="test_custom_key_1"
            android:value="${custom_key_1}"></meta-data>
        <meta-data
            android:name="test_custom_key_2"
            android:value="${custom_key_2}"></meta-data>
    </application>
```    

打包后要查看效果,成功获取    

# 多渠道打包   
> 不同的渠道,有不同的内容时，就可以使用多渠道打包。   

1. 同样在app的build.gradle里添加
```
android {
    ...
        defaultConfig {
            flavorDimensions "versionCode"//3.0版本以上需要加这句
        }
    productFlavors {
        channel_1 {
            manifestPlaceholders = [
                    custom_key_3: 'channel_1_value_3'
            ]
        }
        channel_2 {
            manifestPlaceholders = [
                    custom_key_3: "channel_2_value_3"
            ]
        }
    }
}
```    

2. 在AndroidManifest.xml里使用：
```
        <meta-data
            android:name="test_custom_key_3"
            android:value="${custom_key_3}"></meta-data>
```    

打包时选择不同的渠道版本，进行测试 


