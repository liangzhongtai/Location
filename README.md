##Location���ʹ��˵��
* �汾:1.2.0

##��������
* npm 4.4.1 +
* node 9.8.0 +


##ʹ������ 
#####ע������:iosƽ̨,Macϵͳ�£��������̨������ʾȨ������,��������������ǰ�� sudo
######Localiztion native development region  String "��ͬ�⣬������λ����"
######Privacy-Location Always and When in Use Usage Description String "��ͬ��,������λ����"
######Privacy-Location When In Use Usage Description String "��ͬ�⣬������λ����"

######1.������Ŀ�ĸ�Ŀ¼������ȸ��²��:com.chinamobile.loc.location
* Ϊ��Ŀ���Camera�����ִ��:`cordova plugin add com.chinamobile.loc.location`
* ���Ҫɾ�����,ִ��:`cordova plugin add com.chinamobile.loc.Location`
* Ϊ��Ŀ��Ӷ�Ӧ��platformƽ̨,����ӹ����˲����ԣ�ִ��:
* ��׿ƽ̨: `cordova platform add android`
* ios ƽ ̨:`cordova platform add ios`
* �������ӵ���Ӧƽ̨,ִ��: `cordova build`

######2.��js�ļ���,ͨ������js�������ò������ȡ���յ�ͼƬ����(base64��ʽ)
*
```javascript
   location: function(){
        //��native������λ����
        cordova.exec(success,error,"Location","coolMethod",[]);
    }
    
   success: function(var result){
        //ά��
        var Latitu  = result[0];
        //����
        var Longitu = result[1];
        //����
        var Bearing = result[2];
    }

    error: function(var result){
        //��λ�������ʾ��Ϣ
        alert(result);
    }
```
######˵��:
* 1.success����:result��һ������,Ԫ��0��ά�ȣ�Ԫ��1������,Ԫ��2�����Ρ�


##���ⷴ��
��ʹ�������κ����⣬������������ϵ��ʽ.

* �ʼ�:18520660170@139.com
* ʱ��:2018-5-24 15:00:00
