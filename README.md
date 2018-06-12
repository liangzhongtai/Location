##Location插件使用说明
* 版本:1.2.0

##环境配置
* npm 4.4.1 +
* node 9.8.0 +


##使用流程 
#####注意事项:ios平台,Mac系统下，如果控制台命令提示权限问题,可以在以下命令前加 sudo
######Localiztion native development region  String "请同意，开启定位服务"
######Privacy-Location Always and When in Use Usage Description String "请同意,开启定位服务"
######Privacy-Location When In Use Usage Description String "请同意，开启定位服务"

######1.进入项目的根目录，添加热更新插件:com.chinamobile.loc.location
* 为项目添加Camera插件，执行:`cordova plugin add com.chinamobile.loc.location`
* 如果要删除插件,执行:`cordova plugin add com.chinamobile.loc.Location`
* 为项目添加对应的platform平台,已添加过，此步忽略，执行:
* 安卓平台: `cordova platform add android`
* ios 平 台:`cordova platform add ios`
* 将插件添加到对应平台,执行: `cordova build`

######2.在js文件中,通过以下js方法调用插件，获取拍照的图片数据(base64格式)
*
```javascript
   location: function(){
        //向native发出定位请求
        cordova.exec(success,error,"Location","coolMethod",[]);
    }
    
   success: function(var result){
        //维度
        var Latitu  = result[0];
        //经度
        var Longitu = result[1];
        //海拔
        var Bearing = result[2];
    }

    error: function(var result){
        //定位错误的提示信息
        alert(result);
    }
```
######说明:
* 1.success函数:result是一个数组,元素0：维度，元素1：经度,元素2：海拔。


##问题反馈
在使用中有任何问题，可以用以下联系方式.

* 邮件:18520660170@139.com
* 时间:2018-5-24 15:00:00
