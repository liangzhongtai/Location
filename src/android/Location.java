package com.chinamobile.loc;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Trace;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PermissionHelper;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by liangzhongtai on 2018/5/21.
 */

public class Location extends CordovaPlugin {
    public final static String TAG = "Location_Plugin";
    public final static int RESULTCODE_PERMISSION = 100;
    public final static int RESULTCODE_LOCATION_PROVIDER = 200;

    public CordovaInterface cordova;
    public CordovaWebView webView;
    public boolean first = true;
    private CallbackContext callbackContext;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.cordova = cordova;
        this.webView = webView;
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        Log.d(TAG,"执行方法location");
        if ("coolMethod".equals(action)) {
            //权限
            try {
                if (!PermissionHelper.hasPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        || !PermissionHelper.hasPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    PermissionHelper.requestPermissions(this, RESULTCODE_PERMISSION, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    });
                } else {
                    startWork(true);

                }
            }catch (Exception e){
                //权限异常
                callbackContext.error("定位功能异常");
                manager(false);
                return true;
            }

            return true;
        }
        return super.execute(action, args, callbackContext);
    }

    @Override
    public Bundle onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext) {
        this.callbackContext = callbackContext;
    }

    @Override
    public void onRequestPermissionResult(int requestCode, String[] permissions,
                                          int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == PackageManager.PERMISSION_DENIED) {
                callbackContext.error("缺少权限,无法打开定位服务");
                return;
            }
        }
        switch (requestCode) {
            case RESULTCODE_PERMISSION:
               startWork(true);
                break;
            default:
                break;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == RESULTCODE_LOCATION_PROVIDER) {
            startWork(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance(cordova.getActivity()).removeLocationUpdatesListener();
    }

    private void startWork(boolean showLocationSetting) {
        if(LocationUtils.getInstance(cordova.getActivity()).checkLocationProviders()) {
            if(first)
            LocationUtils.getInstance(cordova.getActivity()).start();
            //打开传感器
            if(!first)manager(true);
            first = false;

            android.location.Location location = LocationUtils.getInstance(cordova.getActivity()).showLocation();
            if(location==null){
                LocationUtils.getInstance(cordova.getActivity()).listener = new LocationListener() {
                    @Override
                    public void sendLocationMessage(android.location.Location location) {
                        sendLocationMessage(location);
                    }
                };
            }else{
                //经度-维度-海拔
                sendLocationMessage(location);
            }
        }else {
            if(showLocationSetting)LocationUtils.showLocationSetting(cordova,this,RESULTCODE_LOCATION_PROVIDER);
            callbackContext.error("无法定位，请打开定位服务");
        }
    }


    private void manager(boolean start){
        if(start){
            LocationUtils.getInstance(cordova.getActivity()).start();
        }else {
            LocationUtils.getInstance(cordova.getActivity()).stop();
        }
    }

    public void sendLocationMessage(android.location.Location location) {
        Log.d(TAG,"location="+location);
        JSONArray array = new JSONArray();
        try {
            array.put(0,location.getLatitude());
            array.put(1,location.getLongitude());
            array.put(2,location.getBearing());
            callbackContext.success(array);
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("经纬度获取失败!");
        }
        manager(false);
    }

    public static interface LocationListener{
        void sendLocationMessage(android.location.Location location);
    }
}
