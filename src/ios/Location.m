//
//  Location.m
//  GoodJob
//
//  Created by 梁仲太 on 2018/5/22.
//

#import "Location.h"
#import "NSLocationManager.h"

@interface Location()

@property(nonatomic,copy)NSString *callbackId;

@end

@implementation Location

-(void)coolMethod:(CDVInvokedUrlCommand *)command{
    NSLog(@"定位---cool");
     self.callbackId = command.callbackId;
    //获取经纬度
    __block  BOOL isOnece = YES;
    [NSLocationManager getMoLocationWithSuccess:^(double lat, double lng, double alt){
        isOnece = NO;
        //只打印一次经纬度海拔
        NSLog(@"经纬度海拔 lat lng alt(%f, %f,%f)", lat, lng, alt);
        //将结果回传给js
        [self successWithMessage:@[[NSNumber numberWithDouble:lat],[NSNumber numberWithDouble:lng],[NSNumber numberWithDouble:alt]]];
        if (!isOnece) {
            [NSLocationManager stop];
        }
    } Failure:^(NSError *error){
        isOnece = NO;
        [self faileWithMessage:@"定位失败!"];
        if (!isOnece) {
            [NSLocationManager stop];
        }
    }];
}

-(void)successWithMessage:(NSArray *)messages{
    if(self.callbackId==nil)return;
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:messages];
    [self.commandDelegate sendPluginResult:result callbackId:self.callbackId];
    
}

-(void)faileWithMessage:(NSString *)message{
    if(self.callbackId==nil)return;
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:message];
    [self.commandDelegate sendPluginResult:result callbackId:self.callbackId];
}

@end
