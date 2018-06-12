//
//  Location.h
//  GoodJob
//
//  Created by 梁仲太 on 2018/5/22.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>
#import <CoreLocation/CLLocationManager.h>
#import <Cordova/CDVPlugin.h>
#import <Cordova/CDV.h>

@interface Location : CDVPlugin

-(void)coolMethod:(CDVInvokedUrlCommand *)command;
-(void)successWithMessage:(NSArray *)messages;
-(void)faileWithMessage:(NSString *)message;


@end
